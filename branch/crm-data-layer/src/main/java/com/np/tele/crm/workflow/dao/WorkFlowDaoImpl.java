package com.np.tele.crm.workflow.dao;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.soap.SOAPException;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import com.np.tele.crm.billing.manager.ICrmBillingManager;
import com.np.tele.crm.config.dao.IConfigManagerDao;
import com.np.tele.crm.constants.CRMCustomerActivityActions;
import com.np.tele.crm.constants.CRMDisplayListConstants;
import com.np.tele.crm.constants.CRMFunctionalBinStages;
import com.np.tele.crm.constants.CRMOperationStages;
import com.np.tele.crm.constants.CRMParameter;
import com.np.tele.crm.constants.CRMRCAReason;
import com.np.tele.crm.constants.CRMRequestType;
import com.np.tele.crm.constants.CRMServiceCode;
import com.np.tele.crm.constants.CRMStatusCode;
import com.np.tele.crm.constants.IAppConstants;
import com.np.tele.crm.dto.ConfigDto;
import com.np.tele.crm.dto.CrmBillingDto;
import com.np.tele.crm.dto.CrmQrcDto;
import com.np.tele.crm.ina.dao.ICAPOperationDao;
import com.np.tele.crm.lms.dao.ILMSOperationDao;
import com.np.tele.crm.pojos.CrmCustWaiverPojo;
import com.np.tele.crm.pojos.CrmCustomerActivityPojo;
import com.np.tele.crm.pojos.CrmCustomerDetailsPojo;
import com.np.tele.crm.pojos.CrmInboxPojo;
import com.np.tele.crm.pojos.CrmNetworkConfigurationsPojo;
import com.np.tele.crm.pojos.CrmPartnerNetworkConfigPojo;
import com.np.tele.crm.pojos.CrmPlanDetailsPojo;
import com.np.tele.crm.pojos.CrmRcaReasonPojo;
import com.np.tele.crm.pojos.CrmShiftingWorkflowPojo;
import com.np.tele.crm.pojos.CrmUserPojo;
import com.np.tele.crm.pojos.CrmWorkflowSequence;
import com.np.tele.crm.pojos.PartnerPojo;
import com.np.tele.crm.pojos.RemarksPojo;
import com.np.tele.crm.qrc.dao.IQrcManagerDao;
import com.np.tele.crm.utils.CRMServiceUtils;
import com.np.tele.crm.utils.CRMUtils;
import com.np.tele.crm.utils.HibernateUtil;
import com.np.tele.crm.utils.StringUtils;
import com.np.validator.util.CommonValidator;

public class WorkFlowDaoImpl
    implements IWorkFlowDao
{
    private static final Logger LOGGER            = Logger.getLogger( WorkFlowDaoImpl.class );
    private ICrmBillingManager  crmBillingManager = null;
    private IConfigManagerDao   configManagerDao  = null;
    private ICAPOperationDao    capOperationDao   = null;
    private IQrcManagerDao      qrcManagerDao     = null;
    private ILMSOperationDao    lmsOperationDao   = null;

    public ICAPOperationDao getCapOperationDao()
    {
        return capOperationDao;
    }

    public void setCapOperationDao( ICAPOperationDao inCapOperationDao )
    {
        capOperationDao = inCapOperationDao;
    }

    public ICrmBillingManager getCrmBillingManager()
    {
        return crmBillingManager;
    }

    public void setCrmBillingManager( ICrmBillingManager crmBillingManager )
    {
        this.crmBillingManager = crmBillingManager;
    }

    public IQrcManagerDao getQrcManagerDao()
    {
        return qrcManagerDao;
    }

    public void setQrcManagerDao( IQrcManagerDao inQrcManagerDao )
    {
        qrcManagerDao = inQrcManagerDao;
    }

    public IConfigManagerDao getConfigManagerDao()
    {
        return configManagerDao;
    }

    public void setConfigManagerDao( IConfigManagerDao inConfigManagerDao )
    {
        configManagerDao = inConfigManagerDao;
    }

    public ILMSOperationDao getLmsOperationDao()
    {
        return lmsOperationDao;
    }

    public void setLmsOperationDao( ILMSOperationDao inLmsOperationDao )
    {
        lmsOperationDao = inLmsOperationDao;
    }

    @Override
    public CrmQrcDto saveShiftingAddressStages( CrmQrcDto inCrmQrcDto )
    {
        LOGGER.info( "Method  saveShiftingAddressStages" );
        SessionFactory sessionFactory = null;
        Session session = null;
        Transaction transaction = null;
        String toStage = null;
        Map<String, Long> inEvicts = null;
        CrmShiftingWorkflowPojo shiftingWorkflowPojo = null;
        CrmCustomerDetailsPojo customerPojo = null;
        CrmNetworkConfigurationsPojo networkConfigurationsPojo = null;
        try
        {
            inEvicts = new HashMap<String, Long>();
            sessionFactory = HibernateUtil.getSessionFactory();
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            if ( StringUtils.equals( inCrmQrcDto.getCrmShiftingWorkflowPojo().getWorkflowStage(),
                                     CRMParameter.INITIATE.getParameter() ) )
            {
                CrmShiftingWorkflowPojo workflowPojo;
                if ( CheckDuplicateShifting( inCrmQrcDto.getCrmShiftingWorkflowPojo().getCustomerId() ) )
                {
                    CrmWorkflowSequence workflowSequence = new CrmWorkflowSequence();
                    Long workflowId = (Long) session.save( workflowSequence );
                    workflowPojo = inCrmQrcDto.getCrmShiftingWorkflowPojo();
                    workflowPojo.setWorkflowId( "W" + workflowId );
                    workflowPojo.setCreatedBy( inCrmQrcDto.getUserId() );
                    workflowPojo.setWorkflowStage( CRMOperationStages.FT_LEVEL1.getCode() );
                    Long catID = getFunctionBin( CRMFunctionalBinStages.FULFILLMENT_TEAM.getFunctionalBin(), session );
                    LOGGER.info( "Functional Bin ID" + catID );
                    if ( catID > 0 )
                    {
                        workflowPojo.setCurrentBin( String.valueOf( catID ) );
                    }
                    workflowPojo.setStatus( CRMStatusCode.INPROCESS.getStatusCode() );
                    Long id = (Long) session.save( workflowPojo );
                    if ( id >= 0 )
                    {
                        inEvicts.put( CrmShiftingWorkflowPojo.class.getName(), null );
                        generateInitiateInbox( inCrmQrcDto, session, inEvicts );
                        inCrmQrcDto.getRemarksPojo().setActions( CRMParameter.INITIATE.getParameter() );
                        saveRemarks( session, inCrmQrcDto, inEvicts );
                    }
                }
                else
                {
                    inCrmQrcDto.setStatusCode( CRMServiceCode.CRM436.getStatusCode() );
                    inCrmQrcDto.setStatusDesc( CRMServiceCode.CRM436.getStatusDesc() );
                    return inCrmQrcDto;
                }
            }
            else if ( StringUtils.equals( inCrmQrcDto.getCrmShiftingWorkflowPojo().getWorkflowStage(),
                                          CRMParameter.FT_LEVEL1.getParameter() ) )
            {
                Long catID = 0L;
                inCrmQrcDto.getCrmShiftingWorkflowPojo()
                        .setPreviousBin( getFunctionBin( CRMFunctionalBinStages.FULFILLMENT_TEAM.getFunctionalBin(),
                                                         session ) + "" );
                if ( !StringUtils.equals( inCrmQrcDto.getCrmShiftingWorkflowPojo().getPreviousNetwork(), inCrmQrcDto
                        .getCrmShiftingWorkflowPojo().getProduct() ) )
                {
                    toStage = CRMOperationStages.CSD_LEVEL2.getCode();
                    catID = getFunctionBin( CRMFunctionalBinStages.CSD_OUTCALL.getFunctionalBin(), session );
                }
                else
                {
                    toStage = CRMOperationStages.IFR_EOC_LEVEL1.getCode();
                    if ( StringUtils.equals( inCrmQrcDto.getCrmShiftingWorkflowPojo().getProduct(),
                                             CRMDisplayListConstants.BROADBAND.getCode() ) )
                    {
                        catID = getFunctionBin( CRMFunctionalBinStages.I_AND_FR.getFunctionalBin(), session );
                    }
                    else
                    {
                        catID = getFunctionBin( CRMFunctionalBinStages.EOC_NETWORK.getFunctionalBin(), session );
                    }
                }
                if ( catID > 0 )
                {
                    inCrmQrcDto.getCrmShiftingWorkflowPojo().setCurrentBin( String.valueOf( catID ) );
                    updateShiftingDetailsWithInboxChange( inCrmQrcDto, session, toStage, inEvicts );
                }
            }
            else if ( StringUtils.equals( inCrmQrcDto.getCrmShiftingWorkflowPojo().getWorkflowStage(),
                                          CRMParameter.CSD_OUTCALL_LEVEL2.getParameter() ) )
            {
                if ( StringUtils.isNotBlank( inCrmQrcDto.getCrmShiftingWorkflowPojo().getCloserReason() ) )
                {
                    toStage = CRMOperationStages.CLOSE.getCode();
                    updateShiftingDetailsWithInboxChange( inCrmQrcDto, session, toStage, inEvicts );
                }
                else
                {
                    Long catID = 0L;
                    toStage = CRMOperationStages.IFR_EOC_LEVEL1.getCode();
                    inCrmQrcDto.getCrmShiftingWorkflowPojo()
                            .setPreviousBin( getFunctionBin( CRMFunctionalBinStages.CSD_OUTCALL.getFunctionalBin(),
                                                             session ) + "" );
                    if ( StringUtils.equals( inCrmQrcDto.getCrmShiftingWorkflowPojo().getProduct(),
                                             CRMDisplayListConstants.BROADBAND.getCode() ) )
                    {
                        catID = getFunctionBin( CRMFunctionalBinStages.I_AND_FR.getFunctionalBin(), session );
                    }
                    else
                    {
                        catID = getFunctionBin( CRMFunctionalBinStages.EOC_NETWORK.getFunctionalBin(), session );
                    }
                    if ( catID > 0 )
                    {
                        inCrmQrcDto.getCrmShiftingWorkflowPojo().setCurrentBin( String.valueOf( catID ) );
                        updateShiftingDetailsWithInboxChange( inCrmQrcDto, session, toStage, inEvicts );
                    }
                }
            }
            else if ( StringUtils.equals( inCrmQrcDto.getCrmShiftingWorkflowPojo().getWorkflowStage(),
                                          CRMParameter.IFR_EOC_LEVEL1.getParameter() ) )
            {
                toStage = CRMOperationStages.CSD_LEVEL3.getCode();
                inCrmQrcDto.getCrmShiftingWorkflowPojo().setPreviousBin( inCrmQrcDto.getCrmShiftingWorkflowPojo()
                                                                                 .getCurrentBin() );
                if ( ( StringUtils.equals( IAppConstants.NO_CHAR, inCrmQrcDto.getCrmShiftingWorkflowPojo()
                        .getCpeAvailable() ) ) )
                {
                    inCrmQrcDto.getCrmShiftingWorkflowPojo()
                            .setCurrentBin( String.valueOf( getFunctionBin( CRMFunctionalBinStages.CSD_OUTCALL
                                                    .getFunctionalBin(), session ) ) );
                }
                else if ( StringUtils.equals( IAppConstants.NO_CHAR, inCrmQrcDto.getCrmShiftingWorkflowPojo()
                        .getCustomerRefusal() ) )
                {
                    if ( StringUtils.equals( IAppConstants.YES_CHAR, inCrmQrcDto.getCrmShiftingWorkflowPojo()
                            .getPhysicalInstallation() )
                            && StringUtils.equals( CRMDisplayListConstants.BROADBAND.getCode(), inCrmQrcDto
                                    .getCrmShiftingWorkflowPojo().getProduct() ) )
                    {
                        if ( StringUtils.equals( IAppConstants.YES_CHAR, inCrmQrcDto.getCrmShiftingWorkflowPojo()
                                .getCpeAvailable() ) )
                        {
                            toStage = CRMOperationStages.NOC_LEVEL1.getCode();
                            inCrmQrcDto.getCrmShiftingWorkflowPojo()
                                    .setCurrentBin( String.valueOf( getFunctionBin( CRMFunctionalBinStages.TIMBL_NOC
                                                            .getFunctionalBin(), session ) ) );
                        }
                    }
                    else if ( StringUtils.equals( IAppConstants.YES_CHAR, inCrmQrcDto.getCrmShiftingWorkflowPojo()
                            .getPhysicalInstallation() )
                            && StringUtils.equals( CRMDisplayListConstants.ETHERNET_ON_CABLE.getCode(), inCrmQrcDto
                                    .getCrmShiftingWorkflowPojo().getProduct() ) )
                    {
                        toStage = CRMOperationStages.NOC_LEVEL1.getCode();
                        inCrmQrcDto.getCrmShiftingWorkflowPojo()
                                .setCurrentBin( String.valueOf( getFunctionBin( CRMFunctionalBinStages.TIMBL_NOC
                                                        .getFunctionalBin(), session ) ) );
                    }
                    else
                    {
                        inCrmQrcDto.getCrmShiftingWorkflowPojo()
                                .setCurrentBin( String.valueOf( getFunctionBin( CRMFunctionalBinStages.CSD_OUTCALL
                                                        .getFunctionalBin(), session ) ) );
                    }
                }
                else
                {
                    inCrmQrcDto.getCrmShiftingWorkflowPojo()
                            .setCurrentBin( String.valueOf( getFunctionBin( CRMFunctionalBinStages.CSD_OUTCALL
                                                    .getFunctionalBin(), session ) ) );
                }
                updateShiftingDetailsWithInboxChange( inCrmQrcDto, session, toStage, inEvicts );
            }
            else if ( StringUtils.equals( inCrmQrcDto.getCrmShiftingWorkflowPojo().getWorkflowStage(),
                                          CRMParameter.CSD_OUTCALL_LEVEL3.getParameter() ) )
            {
                if ( StringUtils.isNotBlank( inCrmQrcDto.getCrmShiftingWorkflowPojo().getCloserReason() ) )
                {
                    toStage = CRMOperationStages.CLOSE.getCode();
                    updateShiftingDetailsWithInboxChange( inCrmQrcDto, session, toStage, inEvicts );
                }
                else
                {
                    toStage = CRMOperationStages.IFR_EOC_LEVEL1.getCode();
                    String currentBin = inCrmQrcDto.getCrmShiftingWorkflowPojo().getPreviousBin();
                    inCrmQrcDto.getCrmShiftingWorkflowPojo()
                            .setPreviousBin( getFunctionBin( CRMFunctionalBinStages.CSD_OUTCALL.getFunctionalBin(),
                                                             session ) + "" );
                    inCrmQrcDto.getCrmShiftingWorkflowPojo().setCurrentBin( currentBin );
                    updateShiftingDetailsWithInboxChange( inCrmQrcDto, session, toStage, inEvicts );
                }
            }
            else if ( StringUtils.equals( inCrmQrcDto.getCrmShiftingWorkflowPojo().getWorkflowStage(),
                                          CRMParameter.NOC_LEVEL1.getParameter() ) )
            {
                toStage = CRMOperationStages.IFR_EOC_LEVEL2.getCode();
                LOGGER.info( "Customer ID::" + inCrmQrcDto.getCustomerId() );
                customerPojo = CRMServiceUtils.getDBValues( CrmCustomerDetailsPojo.class, "customerId",
                                                            inCrmQrcDto.getCustomerId(), session );
                Map<String, Object> criteriaMap = new HashMap<String, Object>();
                criteriaMap.put( "workflowId", inCrmQrcDto.getCrmShiftingWorkflowPojo().getWorkflowId() );
                criteriaMap.put( "customerId", inCrmQrcDto.getCustomerId() );
                criteriaMap.put( "status", CRMStatusCode.INPROCESS.getStatusCode() );
                shiftingWorkflowPojo = CRMServiceUtils.getDBValue( CrmShiftingWorkflowPojo.class, criteriaMap, null,
                                                                   false, session );
                LOGGER.info( "shiftingWorkflowPojo:: " + shiftingWorkflowPojo.toString() );
                if ( StringUtils.isValidObj( inCrmQrcDto.getNetworkConfigurationsPojo() )
                        && inCrmQrcDto.getNetworkConfigurationsPojo().getRecordId() > 0 )
                {
                    networkConfigurationsPojo = (CrmNetworkConfigurationsPojo) session
                            .get( CrmNetworkConfigurationsPojo.class, inCrmQrcDto.getNetworkConfigurationsPojo()
                                    .getRecordId() );
                }
                LOGGER.info( "networkConfigurationsPojo::" + networkConfigurationsPojo.toString() );
                if ( StringUtils.isValidObj( customerPojo ) && StringUtils.isValidObj( shiftingWorkflowPojo ) )
                {
                    LOGGER.info( "Valid Object" );
                    inCrmQrcDto.setStatusCode( CRMServiceCode.CRM001.getStatusCode() );
                    if ( !StringUtils.equals( customerPojo.getProduct(), shiftingWorkflowPojo.getProduct() ) )
                    {
                        // Product change Billing API Call
                        if ( StringUtils.equals( shiftingWorkflowPojo.getServiceChange(), IAppConstants.NO_CHAR ) )
                        {
                            /*inCrmQrcDto = updateCustomerShifting( inCrmQrcDto, customerPojo, shiftingWorkflowPojo,
                                                                  session );*/
                        }
                        if ( StringUtils.equals( inCrmQrcDto.getStatusCode(), CRMServiceCode.CRM001.getStatusCode() )
                                && StringUtils.equals( shiftingWorkflowPojo.getPlanChange(), IAppConstants.NO_CHAR ) )
                        {
                            //plan change billing API 
                            //inCrmQrcDto = changeShiftingPlan( inCrmQrcDto, customerPojo, shiftingWorkflowPojo, session );
                        }
                    }
                    else
                    {
                        if ( customerPojo.getNpId() != shiftingWorkflowPojo.getNpId() )
                        {
                            //partner change Billing API Call
                            inCrmQrcDto = updateCustomerShifting( inCrmQrcDto, customerPojo, shiftingWorkflowPojo,
                                                                  session );
                        }
                    }
                    if ( StringUtils.equals( inCrmQrcDto.getStatusCode(), CRMServiceCode.CRM001.getStatusCode() )
                            && StringUtils.equals( shiftingWorkflowPojo.getNassportChange(), IAppConstants.NO_CHAR ) )
                    {
                        if ( StringUtils.isValidObj( networkConfigurationsPojo )
                                && StringUtils.isNotBlank( shiftingWorkflowPojo.getOption82() )
                                && !StringUtils.equals( networkConfigurationsPojo.getOption82(),
                                                        shiftingWorkflowPojo.getOption82() ) )
                        {
                            LOGGER.info( "bindOption82 Call.........." );
                            inCrmQrcDto = bindOption82( inCrmQrcDto, shiftingWorkflowPojo, networkConfigurationsPojo,
                                                        customerPojo, session );
                        }
                    }
                    if ( StringUtils.equals( inCrmQrcDto.getStatusCode(), CRMServiceCode.CRM001.getStatusCode() )
                            && ( StringUtils.equals( shiftingWorkflowPojo.getMacBind(), IAppConstants.NO_CHAR ) ) )
                    {
                        if ( StringUtils.isValidObj( networkConfigurationsPojo )
                                && StringUtils.isNotBlank( inCrmQrcDto.getCrmShiftingWorkflowPojo()
                                        .getCurrentCPeMacId() )
                                && !StringUtils.equals( networkConfigurationsPojo.getCurrentCpeMacId(), inCrmQrcDto
                                        .getCrmShiftingWorkflowPojo().getCurrentCPeMacId() ) )
                        {
                            LOGGER.info( "MAC Bind  Call.........." );
                            inCrmQrcDto = changeMacDetails( inCrmQrcDto, shiftingWorkflowPojo,
                                                            networkConfigurationsPojo, session );
                        }
                    }
                    if ( StringUtils.isValidObj( inCrmQrcDto )
                            && StringUtils.equals( inCrmQrcDto.getStatusCode(), CRMServiceCode.CRM001.getStatusCode() ) )
                    {
                        inCrmQrcDto.getCrmShiftingWorkflowPojo().setPreviousBin( inCrmQrcDto
                                                                                         .getCrmShiftingWorkflowPojo()
                                                                                         .getCurrentBin() );
                        if ( StringUtils.equals( inCrmQrcDto.getCrmShiftingWorkflowPojo().getProduct(),
                                                 CRMDisplayListConstants.ETHERNET_ON_CABLE.getCode() ) )
                        {
                            inCrmQrcDto.getCrmShiftingWorkflowPojo()
                                    .setCurrentBin( getFunctionBin( CRMFunctionalBinStages.EOC_NETWORK
                                                            .getFunctionalBin(), session )
                                                            + "" );
                        }
                        else if ( StringUtils.equals( inCrmQrcDto.getCrmShiftingWorkflowPojo().getProduct(),
                                                      CRMDisplayListConstants.BROADBAND.getCode() ) )
                        {
                            inCrmQrcDto.getCrmShiftingWorkflowPojo()
                                    .setCurrentBin( getFunctionBin( CRMFunctionalBinStages.I_AND_FR.getFunctionalBin(),
                                                                    session ) + "" );
                        }
                        updateShiftingDetailsWithInboxChange( inCrmQrcDto, session, toStage, inEvicts );
                    }
                }
            }
            else if ( StringUtils.equals( inCrmQrcDto.getCrmShiftingWorkflowPojo().getWorkflowStage(),
                                          CRMParameter.IFR_EOC_LEVEL2.getParameter() ) )
            {
                inCrmQrcDto = getQrcManagerDao().modifyInstallationAddresses( inCrmQrcDto );
                if ( StringUtils.isValidObj( inCrmQrcDto )
                        && StringUtils.equals( inCrmQrcDto.getStatusCode(), CRMServiceCode.CRM001.getStatusCode() ) )
                {
                    inCrmQrcDto.getCrmShiftingWorkflowPojo().setPreviousBin( inCrmQrcDto.getCrmShiftingWorkflowPojo()
                                                                                     .getCurrentBin() );
                    toStage = CRMOperationStages.CLOSE.getCode();
                    updateShiftingDetailsWithInboxChange( inCrmQrcDto, session, toStage, inEvicts );
                }
            }
            else if ( StringUtils.equals( inCrmQrcDto.getCrmShiftingWorkflowPojo().getWorkflowStage(),
                                          CRMParameter.NETWORK_PARTNER.getParameter() ) )
            {
                toStage = CRMOperationStages.IFR_AD.getCode();
                updateShiftingDetailsWithInboxChange( inCrmQrcDto, session, toStage, inEvicts );
            }
            if ( StringUtils.equals( inCrmQrcDto.getStatusCode(), CRMServiceCode.CRM001.getStatusCode() ) )
            {
                transaction.commit();
                transaction = null;
                LOGGER.info( "Successfully Save OR Update Workflow Shifting Details" );
            }
        }
        catch ( HibernateException ex )
        {
            LOGGER.error( "HibernateException in Save Workflow Shifting Method ::", ex );
            inCrmQrcDto.setStatusCode( CRMServiceCode.CRM999.getStatusCode() );
            CRMServiceUtils.rollback( transaction );
            CRMServiceUtils.closeSessionOnException( session );
        }
        catch ( Exception ex )
        {
            LOGGER.error( "Error in Save Workflow Shifting Method :: ", ex );
            inCrmQrcDto.setStatusCode( CRMServiceCode.CRM999.getStatusCode() );
            inCrmQrcDto.setStatusDesc( CRMServiceCode.CRM999.getStatusDesc() );
        }
        finally
        {
            CRMServiceUtils.rollback( transaction );
            CRMServiceUtils.closeSession( session );
            if ( StringUtils.equals( inCrmQrcDto.getStatusCode(), CRMServiceCode.CRM001.getStatusCode() ) )
            {
                HibernateUtil.evictAll( inEvicts );
            }
        }
        return inCrmQrcDto;
    }

    private CrmQrcDto updateCustomerShifting( CrmQrcDto inCrmQrcDto,
                                              CrmCustomerDetailsPojo inCustomerPojo,
                                              CrmShiftingWorkflowPojo inShiftingWorkflowPojo,
                                              Session inSession )
    {
        Transaction transaction = null;
        CRMServiceCode crmServiceCode = CRMServiceCode.CRM999;
        CrmBillingDto billingDto = new CrmBillingDto();
        CrmCustomerActivityPojo activityLog = null;
        try
        {
            inSession = HibernateUtil.getCurrentSession();
            transaction = inSession.beginTransaction();
            LOGGER.info( "update Customer Shifting request call :: " );
            PartnerPojo newPartner = CRMServiceUtils.getDBValues( PartnerPojo.class, inShiftingWorkflowPojo.getNpId() );
            PartnerPojo oldPartner = CRMServiceUtils.getDBValues( PartnerPojo.class, inCustomerPojo.getNpId() );
            inCustomerPojo.setNewNetworkPartner( newPartner.getCrmPartnerCode() );
            inCustomerPojo.setOldNetworkPartner( oldPartner.getCrmPartnerCode() );
            activityLog = new CrmCustomerActivityPojo();
            activityLog.setCustomerId( inShiftingWorkflowPojo.getCustomerId() );
            activityLog.setOldValue( oldPartner.getPartnerName() );
            activityLog.setNewValue( newPartner.getPartnerName() );
            activityLog.setAction( CRMCustomerActivityActions.PARTNER_CHANGE.getActionDesc() );
            activityLog.setReason( CRMCustomerActivityActions.PARTNER_CHANGE.getActionDesc() );
            activityLog.setCreatedBy( inCrmQrcDto.getUserId() );
            activityLog.setServiceIp( CRMUtils.getServerIp() );
            activityLog.setClientIp( inCrmQrcDto.getClientIPAddress() );
            activityLog.setServerIp( inCrmQrcDto.getServerIPAddress() );
            if ( StringUtils.equals( inShiftingWorkflowPojo.getProduct(),
                                     CRMDisplayListConstants.ETHERNET_ON_CABLE.getCode() ) )
            {
                if ( StringUtils.isNotBlank( inCrmQrcDto.getCrmShiftingWorkflowPojo().getCurrentSlaveMacId() ) )
                {
                    inCustomerPojo.setSecondaryMacId( inCrmQrcDto.getCrmShiftingWorkflowPojo().getCurrentSlaveMacId() );
                }
                else
                {
                    inCustomerPojo
                            .setSecondaryMacId( inCrmQrcDto.getNetworkConfigurationsPojo().getCurrentSlaveMacId() );
                }
            }
            else
            {
                inCustomerPojo.setSecondaryMacId( inCrmQrcDto.getCrmShiftingWorkflowPojo().getCurrentCPeMacId() );
            }
            billingDto = getCrmBillingManager().updateCustomer( inCustomerPojo,
                                                                Long.parseLong( inCustomerPojo.getCustomerId() ) );
            if ( StringUtils.equals( billingDto.getStatusCode(), CRMServiceCode.CRM001.getStatusCode() ) )
            {
                inCustomerPojo.setNpId( inShiftingWorkflowPojo.getNpId() );
                inCustomerPojo.setLastModifiedBy( inCrmQrcDto.getUserId() );
                inCustomerPojo.setLastModifiedTime( Calendar.getInstance().getTime() );
                inSession.merge( inCustomerPojo );
                inShiftingWorkflowPojo.setServiceChange( IAppConstants.YES_CHAR );
                inSession.merge( inShiftingWorkflowPojo );
                transaction.commit();
                crmServiceCode = CRMServiceCode.CRM001;
                CRMServiceUtils.createActivityLog( activityLog );
            }
            else
            {
                crmServiceCode = CRMServiceCode.valueOf( billingDto.getStatusCode() );
                inCrmQrcDto.setBillingErrorCode( billingDto.getBillingErrorCode() );
            }
        }
        catch ( HibernateException ex )
        {
            LOGGER.error( "HibernateException  updateCustomer Shifting:", ex );
            CRMServiceUtils.rollback( transaction );
            CRMServiceUtils.closeSessionOnException( inSession );
        }
        catch ( Exception ex )
        {
            LOGGER.error( "Getting Error while update Customer Shifting: ", ex );
        }
        finally
        {
            CRMServiceUtils.rollback( transaction );
            CRMServiceUtils.closeSession( inSession );
            LOGGER.info( "Status Code ::" + crmServiceCode );
            inCrmQrcDto.setStatusCode( crmServiceCode.getStatusCode() );
            inCrmQrcDto.setStatusDesc( crmServiceCode.getStatusDesc() );
        }
        return inCrmQrcDto;
    }

    private CrmQrcDto changeShiftingPlan( CrmQrcDto inCrmQrcDto,
                                          CrmCustomerDetailsPojo inCustomerPojo,
                                          CrmShiftingWorkflowPojo inShiftingWorkflowPojo,
                                          Session inSession )
    {
        Transaction transaction = null;
        CRMServiceCode crmServiceCode = CRMServiceCode.CRM999;
        CrmPlanDetailsPojo crmPlanDetailsPojoDB = null;
        Criteria criteria = null;
        try
        {
            inSession = HibernateUtil.getCurrentSession();
            transaction = inSession.beginTransaction();
            LOGGER.info( "ChangeShiftingPlan request call :: " );
            criteria = inSession.createCriteria( CrmPlanDetailsPojo.class );
            criteria.createCriteria( "crmCustomerDetails", "cust" );
            criteria.add( Restrictions.eq( "cust.recordId", inCustomerPojo.getRecordId() ) );
            List<CrmPlanDetailsPojo> plan = criteria.list();
            if ( CommonValidator.isValidCollection( plan ) )
            {
                crmPlanDetailsPojoDB = (CrmPlanDetailsPojo) plan.get( 0 );
                crmPlanDetailsPojoDB.setBasePlanCode( inShiftingWorkflowPojo.getBasePlanCode() );
                if ( StringUtils.isNotEmpty( inShiftingWorkflowPojo.getAddOnPlanCode() ) )
                {
                    crmPlanDetailsPojoDB.setAddOnPlanCode( inShiftingWorkflowPojo.getAddOnPlanCode() );
                }
                crmPlanDetailsPojoDB.setLastModifiedBy( inCrmQrcDto.getUserId() );
                crmPlanDetailsPojoDB.setLastModifiedTime( Calendar.getInstance().getTime() );
                inCrmQrcDto.setCrmPlanDetailsPojo( crmPlanDetailsPojoDB );
                //ChangeOffer Billing API
                getCrmBillingManager().instantOfferRenew( inCrmQrcDto );
                if ( StringUtils.equals( inCrmQrcDto.getStatusCode(), CRMServiceCode.CRM001.getStatusCode() ) )
                {
                    LOGGER.info( "changeShiftingPlan Service Code::" + crmServiceCode );
                    inShiftingWorkflowPojo.setPlanChange( IAppConstants.YES_CHAR );
                    inSession.merge( inShiftingWorkflowPojo );
                    inSession.update( crmPlanDetailsPojoDB );
                    transaction.commit();
                    crmServiceCode = CRMServiceCode.CRM001;
                }
                else
                {
                    crmServiceCode = CRMServiceCode.valueOf( inCrmQrcDto.getStatusCode() );
                }
            }
            else
            {
                crmServiceCode = CRMServiceCode.CRM014;
            }
        }
        catch ( HibernateException ex )
        {
            LOGGER.error( "HibernateException  changeShiftingPlan:", ex );
            CRMServiceUtils.rollback( transaction );
            CRMServiceUtils.closeSessionOnException( inSession );
        }
        catch ( Exception ex )
        {
            LOGGER.error( "Getting Error while changeShiftingPlan: ", ex );
        }
        finally
        {
            CRMServiceUtils.rollback( transaction );
            CRMServiceUtils.closeSession( inSession );
            LOGGER.info( "Service Code:" + crmServiceCode.getStatusCode() );
            inCrmQrcDto.setStatusCode( crmServiceCode.getStatusCode() );
            inCrmQrcDto.setStatusDesc( crmServiceCode.getStatusDesc() );
        }
        return inCrmQrcDto;
    }

    private CrmQrcDto changeMacDetails( CrmQrcDto inCrmQrcDto,
                                        CrmShiftingWorkflowPojo inShiftingWorkflowPojo,
                                        CrmNetworkConfigurationsPojo inNetworkConfigurationsPojo,
                                        Session session )
    {
        Transaction transaction = null;
        CRMServiceCode crmServiceCode = CRMServiceCode.CRM999;
        CrmCustomerActivityPojo activityLog = null;
        try
        {
            session = HibernateUtil.getCurrentSession();
            transaction = session.beginTransaction();
            LOGGER.info( "MAC Change request call :: " );
            activityLog = new CrmCustomerActivityPojo();
            activityLog.setCustomerId( inShiftingWorkflowPojo.getCustomerId() );
            if ( StringUtils.equals( inCrmQrcDto.getCustomerDetailsPojo().getProduct(),
                                     CRMDisplayListConstants.ETHERNET_ON_CABLE.getCode() ) )
            {
                activityLog.setOldValue( inNetworkConfigurationsPojo.getCurrentCpeMacId() + IAppConstants.COMMA
                        + inNetworkConfigurationsPojo.getCurrentSlaveMacId() );
                activityLog.setNewValue( inCrmQrcDto.getCrmShiftingWorkflowPojo().getCurrentCPeMacId()
                        + IAppConstants.COMMA + inCrmQrcDto.getCrmShiftingWorkflowPojo().getCurrentSlaveMacId() );
            }
            else
            {
                activityLog.setOldValue( inNetworkConfigurationsPojo.getCurrentCpeMacId() );
                activityLog.setNewValue( inCrmQrcDto.getCrmShiftingWorkflowPojo().getCurrentCPeMacId() );
            }
            activityLog.setAction( CRMCustomerActivityActions.MAC_CHANGE.getActionDesc() );
            activityLog.setReason( CRMCustomerActivityActions.MAC_CHANGE.getActionDesc() );
            activityLog.setCreatedBy( inCrmQrcDto.getUserId() );
            activityLog.setServiceIp( CRMUtils.getServerIp() );
            activityLog.setClientIp( inCrmQrcDto.getClientIPAddress() );
            activityLog.setServerIp( inCrmQrcDto.getServerIPAddress() );
            inCrmQrcDto.setActivityAction( CRMCustomerActivityActions.MAC_CHANGE.getActionDesc() );
            if ( StringUtils.equals( inShiftingWorkflowPojo.getProduct(),
                                     CRMDisplayListConstants.ETHERNET_ON_CABLE.getCode() ) )
            {
                inNetworkConfigurationsPojo.setCurrentCpeMacId( inCrmQrcDto.getCrmShiftingWorkflowPojo()
                        .getCurrentCPeMacId() );
                inNetworkConfigurationsPojo.setCurrentSlaveMacId( inCrmQrcDto.getCrmShiftingWorkflowPojo()
                        .getCurrentSlaveMacId() );
                inCrmQrcDto.setMacFaulty( true );
            }
            else
            {
                inNetworkConfigurationsPojo.setCurrentCpeMacId( inCrmQrcDto.getCrmShiftingWorkflowPojo()
                        .getCurrentCPeMacId() );
            }
            inCrmQrcDto.setNetworkConfigurationsPojo( inNetworkConfigurationsPojo );
            CrmBillingDto billingDto = getCrmBillingManager().changeDeviceDetails( inCrmQrcDto );
            crmServiceCode = CRMServiceCode.valueOf( billingDto.getStatusCode() );
            inCrmQrcDto.setBillingErrorCode( billingDto.getBillingErrorCode() );
            LOGGER.info( "billing error code in dao:" + inCrmQrcDto.getBillingErrorCode() + ",crmServiceCode:"
                    + crmServiceCode );
            if ( crmServiceCode == CRMServiceCode.CRM001 )
            {
                inShiftingWorkflowPojo.setMacBind( IAppConstants.YES_CHAR );
                session.merge( inShiftingWorkflowPojo );
                session.merge( inNetworkConfigurationsPojo );
                transaction.commit();
                crmServiceCode = CRMServiceCode.CRM001;
                CRMServiceUtils.createActivityLog( activityLog );
            }
        }
        catch ( HibernateException ex )
        {
            LOGGER.error( "HibernateException  changeMacDetails:", ex );
            CRMServiceUtils.rollback( transaction );
            CRMServiceUtils.closeSessionOnException( session );
        }
        catch ( Exception ex )
        {
            LOGGER.error( "Getting Error while changeMacDetails: ", ex );
        }
        finally
        {
            CRMServiceUtils.rollback( transaction );
            CRMServiceUtils.closeSession( session );
            LOGGER.info( "changeMacDetails : Service Code :" + crmServiceCode.getStatusCode() );
            inCrmQrcDto.setStatusCode( crmServiceCode.getStatusCode() );
            inCrmQrcDto.setStatusDesc( crmServiceCode.getStatusDesc() );
        }
        return inCrmQrcDto;
    }

    private CrmQrcDto bindOption82( CrmQrcDto inCrmQrcDto,
                                    CrmShiftingWorkflowPojo inShiftingWorkflowPojo,
                                    CrmNetworkConfigurationsPojo inNetworkConfigurationsPojo,
                                    CrmCustomerDetailsPojo inCustomerPojo,
                                    Session session )
    {
        Transaction transaction = null;
        CRMServiceCode crmServiceCode = CRMServiceCode.CRM999;
        CrmCustomerActivityPojo activityLog = null;
        try
        {
            session = HibernateUtil.getCurrentSession();
            transaction = session.beginTransaction();
            CrmPartnerNetworkConfigPojo crmPartnerNetworkConfigPojo = null;
            LOGGER.info( "IN Method bindOption82" );
            activityLog = new CrmCustomerActivityPojo();
            activityLog.setCustomerId( inShiftingWorkflowPojo.getCustomerId() );
            if ( StringUtils.equals( inCrmQrcDto.getCustomerDetailsPojo().getProduct(),
                                     CRMDisplayListConstants.ETHERNET_ON_CABLE.getCode() ) )
            {
                CrmPartnerNetworkConfigPojo newCrmPartnerNetworkConfigPojo = null;
                if ( StringUtils.isNumeric( inNetworkConfigurationsPojo.getOption82() ) )
                {
                    crmPartnerNetworkConfigPojo = (CrmPartnerNetworkConfigPojo) session
                            .get( CrmPartnerNetworkConfigPojo.class,
                                  StringUtils.numericValue( inNetworkConfigurationsPojo.getOption82() ) );
                }
                if ( StringUtils.isNumeric( inShiftingWorkflowPojo.getOption82() ) )
                {
                    newCrmPartnerNetworkConfigPojo = (CrmPartnerNetworkConfigPojo) session
                            .get( CrmPartnerNetworkConfigPojo.class,
                                  StringUtils.numericValue( inShiftingWorkflowPojo.getOption82() ) );
                }
                if ( StringUtils.isValidObj( crmPartnerNetworkConfigPojo )
                        && StringUtils.isNotBlank( crmPartnerNetworkConfigPojo.getNasPortId() ) )
                {
                    activityLog.setOldValue( crmPartnerNetworkConfigPojo.getNasPortId() );
                }
                activityLog.setNewValue( newCrmPartnerNetworkConfigPojo.getNasPortId() );
            }
            else
            {
                activityLog.setOldValue( inNetworkConfigurationsPojo.getOption82() );
                activityLog.setNewValue( inShiftingWorkflowPojo.getOption82() );
            }
            activityLog.setAction( CRMCustomerActivityActions.OPTION_82_CHANGE.getActionDesc() );
            activityLog.setReason( CRMCustomerActivityActions.OPTION_82_CHANGE.getActionDesc() );
            activityLog.setCreatedBy( inCrmQrcDto.getUserId() );
            activityLog.setServiceIp( CRMUtils.getServerIp() );
            activityLog.setClientIp( inCrmQrcDto.getClientIPAddress() );
            activityLog.setServerIp( inCrmQrcDto.getServerIPAddress() );
            if ( StringUtils.equals( inShiftingWorkflowPojo.getProduct(),
                                     CRMDisplayListConstants.ETHERNET_ON_CABLE.getCode() ) )
            {
                if ( StringUtils.isNumeric( inShiftingWorkflowPojo.getOption82() ) )
                {
                    crmPartnerNetworkConfigPojo = (CrmPartnerNetworkConfigPojo) session
                            .get( CrmPartnerNetworkConfigPojo.class,
                                  StringUtils.numericValue( inShiftingWorkflowPojo.getOption82() ) );
                }
            }
            inNetworkConfigurationsPojo.setOption82( inCrmQrcDto.getNetworkConfigurationsPojo().getOption82() );
            //CRMUtils.copyPropertyValue( inNetworkConfigurationsPojo, inCrmQrcDto.getNetworkConfigurationsPojo(), null );
            inNetworkConfigurationsPojo.setLastModifiedBy( inCrmQrcDto.getUserId() );
            inNetworkConfigurationsPojo.setLastModifiedTime( Calendar.getInstance().getTime() );
            inCrmQrcDto.setNetworkConfigurationsPojo( inNetworkConfigurationsPojo );
            inCrmQrcDto.setActivityAction( CRMCustomerActivityActions.OPTION_82_CHANGE.getActionDesc() );
            inCrmQrcDto.setCrmPartnerNetworkConfigPojo( crmPartnerNetworkConfigPojo );
            LOGGER.info( "PARTNER NETWORK CONFIG POJO:" + inCrmQrcDto.getCrmPartnerNetworkConfigPojo() );
            //Call changeDeviceDetails API
            CrmBillingDto billingDto = getCrmBillingManager().changeDeviceDetails( inCrmQrcDto );
            crmServiceCode = CRMServiceCode.valueOf( billingDto.getStatusCode() );
            inCrmQrcDto.setBillingErrorCode( billingDto.getBillingErrorCode() );
            LOGGER.info( "billing error code in dao:" + inCrmQrcDto.getBillingErrorCode() + ",crmServiceCode:"
                    + crmServiceCode );
            if ( crmServiceCode == CRMServiceCode.CRM001 )
            {
                LOGGER.info( "crmServiceCode :" + crmServiceCode );
                inShiftingWorkflowPojo.setNassportChange( IAppConstants.YES_CHAR );
                session.merge( inShiftingWorkflowPojo );
                session.merge( inNetworkConfigurationsPojo );
                transaction.commit();
                crmServiceCode = CRMServiceCode.CRM001;
                CRMServiceUtils.createActivityLog( activityLog );
            }
        }
        catch ( HibernateException ex )
        {
            LOGGER.error( "HibernateException  bindOption82:", ex );
            CRMServiceUtils.rollback( transaction );
            CRMServiceUtils.closeSessionOnException( session );
        }
        catch ( Exception ex )
        {
            LOGGER.error( "Getting Error while bindOption82: ", ex );
        }
        finally
        {
            CRMServiceUtils.rollback( transaction );
            CRMServiceUtils.closeSession( session );
            LOGGER.info( "crmServiceCode s:" + crmServiceCode.getStatusCode() );
            inCrmQrcDto.setStatusCode( crmServiceCode.getStatusCode() );
            inCrmQrcDto.setStatusDesc( crmServiceCode.getStatusDesc() );
        }
        return inCrmQrcDto;
    }

    private long getFunctionBin( String inFunctionalBin, Session inSession )
    {
        long binID = 0L;
        Criteria crit = inSession.createCriteria( CrmRcaReasonPojo.class );
        crit.add( Restrictions.eq( "categoryValue", inFunctionalBin ) );
        List<CrmRcaReasonPojo> pojos = crit.list();
        binID = pojos.get( 0 ).getCategoryId();
        return binID;
    }

    private void updateShiftingDetailsWithInboxChange( CrmQrcDto inCrmQrcDto,
                                                       Session session,
                                                       String toStage,
                                                       Map<String, Long> inEvicts )
        throws SOAPException
    {
        LOGGER.info( "updateShiftingDetailsWithInboxChange ::" + toStage );
        // CrmCustomerDetailsPojo customerPojo = null;
        Criteria criteria;
        criteria = session.createCriteria( CrmShiftingWorkflowPojo.class );
        criteria.add( Restrictions.eq( "workflowId", inCrmQrcDto.getCrmShiftingWorkflowPojo().getWorkflowId() ) );
        criteria.add( Restrictions.eq( "customerId", inCrmQrcDto.getCrmShiftingWorkflowPojo().getCustomerId() ) );
        criteria.add( Restrictions.eq( "status", CRMStatusCode.INPROCESS.getStatusCode() ) );
        List<CrmShiftingWorkflowPojo> shiftingWorkflowPojos = criteria.list();
        if ( CommonValidator.isValidCollection( shiftingWorkflowPojos ) )
        {
            CrmShiftingWorkflowPojo shiftingWorkflowPojo = shiftingWorkflowPojos.get( 0 );
            if ( StringUtils.equals( inCrmQrcDto.getCrmShiftingWorkflowPojo().getWorkflowStage(),
                                     CRMParameter.FT_LEVEL1.getParameter() ) )
            {
                /*customerPojo = CRMServiceUtils.getDBValues( CrmCustomerDetailsPojo.class, "customerId", inCrmQrcDto
                        .getCrmShiftingWorkflowPojo().getCustomerId(), session );*/
                shiftingWorkflowPojo.setNpId( inCrmQrcDto.getCrmShiftingWorkflowPojo().getNpId() );
                shiftingWorkflowPojo.setProduct( inCrmQrcDto.getCrmShiftingWorkflowPojo().getProduct() );
                shiftingWorkflowPojo.setPreviousNetwork( inCrmQrcDto.getCrmShiftingWorkflowPojo().getPreviousNetwork() );
                shiftingWorkflowPojo.setPreviousNpId( inCrmQrcDto.getCrmShiftingWorkflowPojo().getPreviousNpId() );
                inCrmQrcDto.getRemarksPojo().setActions( CRMOperationStages.FT_LEVEL1.getDesc() );
            }
            if ( StringUtils.equals( inCrmQrcDto.getCrmShiftingWorkflowPojo().getWorkflowStage(),
                                     CRMParameter.IFR_EOC_LEVEL2.getParameter() ) )
            {
                shiftingWorkflowPojo.setAddressLine1( inCrmQrcDto.getCrmShiftingWorkflowPojo().getAddressLine1() );
                shiftingWorkflowPojo.setAddressLine2( inCrmQrcDto.getCrmShiftingWorkflowPojo().getAddressLine2() );
                shiftingWorkflowPojo.setAddressLine3( inCrmQrcDto.getCrmShiftingWorkflowPojo().getAddressLine3() );
                shiftingWorkflowPojo.setHouseNumber( inCrmQrcDto.getCrmShiftingWorkflowPojo().getHouseNumber() );
                shiftingWorkflowPojo.setLandmark( inCrmQrcDto.getCrmShiftingWorkflowPojo().getLandmark() );
                shiftingWorkflowPojo.setShiftingAddress( inCrmQrcDto.getCrmShiftingWorkflowPojo().getAddressLine1()
                        + "," + inCrmQrcDto.getCrmShiftingWorkflowPojo().getAddressLine2() + ","
                        + inCrmQrcDto.getCrmShiftingWorkflowPojo().getAddressLine3() + " "
                        + inCrmQrcDto.getCrmShiftingWorkflowPojo().getPincode() );
                inCrmQrcDto.getRemarksPojo().setActions( CRMOperationStages.IFR_EOC_LEVEL2.getDesc() );
            }
            if ( StringUtils.equals( inCrmQrcDto.getCrmShiftingWorkflowPojo().getWorkflowStage(),
                                     CRMParameter.IFR_EOC_LEVEL1.getParameter() ) )
            {
                shiftingWorkflowPojo.setPhysicalInstallation( inCrmQrcDto.getCrmShiftingWorkflowPojo()
                        .getPhysicalInstallation() );
                shiftingWorkflowPojo.setOption82( inCrmQrcDto.getCrmShiftingWorkflowPojo().getOption82() );
                inCrmQrcDto.getRemarksPojo().setActions( CRMOperationStages.IFR_EOC_LEVEL1.getDesc() );
            }
            if ( StringUtils.equals( inCrmQrcDto.getCrmShiftingWorkflowPojo().getWorkflowStage(),
                                     CRMParameter.NOC_LEVEL1.getParameter() ) )
            {
                if ( StringUtils
                        .equals( shiftingWorkflowPojo.getProduct(), CRMDisplayListConstants.BROADBAND.getCode() ) )
                {
                    shiftingWorkflowPojo.setCurrentCPeMacId( inCrmQrcDto.getCrmShiftingWorkflowPojo()
                            .getCurrentCPeMacId() );
                }
                if ( StringUtils.equals( shiftingWorkflowPojo.getProduct(),
                                         CRMDisplayListConstants.ETHERNET_ON_CABLE.getCode() ) )
                {
                    shiftingWorkflowPojo.setCurrentCPeMacId( inCrmQrcDto.getCrmShiftingWorkflowPojo()
                            .getCurrentCPeMacId() );
                    shiftingWorkflowPojo.setCurrentSlaveMacId( inCrmQrcDto.getCrmShiftingWorkflowPojo()
                            .getCurrentSlaveMacId() );
                }
                inCrmQrcDto.getRemarksPojo().setActions( CRMOperationStages.NOC_LEVEL1.getDesc() );
            }
            if ( StringUtils.equals( inCrmQrcDto.getCrmShiftingWorkflowPojo().getWorkflowStage(),
                                     CRMParameter.CSD_OUTCALL_LEVEL2.getParameter() ) )
            {
                shiftingWorkflowPojo.setBasePlanCode( inCrmQrcDto.getCrmShiftingWorkflowPojo().getBasePlanCode() );
                shiftingWorkflowPojo.setAddOnPlanCode( inCrmQrcDto.getCrmShiftingWorkflowPojo().getAddOnPlanCode() );
                inCrmQrcDto.getRemarksPojo().setActions( CRMOperationStages.CSD_LEVEL2.getDesc() );
            }
            if ( StringUtils.equals( inCrmQrcDto.getCrmShiftingWorkflowPojo().getWorkflowStage(),
                                     CRMParameter.IFR_EOC_LEVEL1.getParameter() )
                    || StringUtils.equals( inCrmQrcDto.getCrmShiftingWorkflowPojo().getWorkflowStage(),
                                           CRMParameter.CSD_OUTCALL_LEVEL3.getParameter() ) )
            {
                shiftingWorkflowPojo.setCustomerRefusal( inCrmQrcDto.getCrmShiftingWorkflowPojo().getCustomerRefusal() );
                shiftingWorkflowPojo.setCpeAvailable( inCrmQrcDto.getCrmShiftingWorkflowPojo().getCpeAvailable() );
                if ( StringUtils.equals( inCrmQrcDto.getCrmShiftingWorkflowPojo().getWorkflowStage(),
                                         CRMParameter.IFR_EOC_LEVEL1.getParameter() ) )
                {
                    inCrmQrcDto.getRemarksPojo().setActions( CRMOperationStages.IFR_EOC_LEVEL1.getDesc() );
                }
                if ( StringUtils.equals( inCrmQrcDto.getCrmShiftingWorkflowPojo().getWorkflowStage(),
                                         CRMParameter.CSD_OUTCALL_LEVEL3.getParameter() ) )
                {
                    inCrmQrcDto.getRemarksPojo().setActions( CRMOperationStages.CSD_LEVEL3.getDesc() );
                }
            }
            if ( StringUtils.isValidObj( inCrmQrcDto.getCrmShiftingWorkflowPojo().getPreviousNpId() )
                    && inCrmQrcDto.getCrmShiftingWorkflowPojo().getPreviousNpId() > 0 )
            {
                shiftingWorkflowPojo.setPreviousNpId( inCrmQrcDto.getCrmShiftingWorkflowPojo().getPreviousNpId() );
            }
            if ( StringUtils.equals( shiftingWorkflowPojo.getProduct(), CRMDisplayListConstants.BROADBAND.getCode() )
                    && StringUtils.isValidObj( inCrmQrcDto.getCrmShiftingWorkflowPojo().getCpeAvailable() ) )
            {
                shiftingWorkflowPojo.setCpeAvailable( inCrmQrcDto.getCrmShiftingWorkflowPojo().getCpeAvailable() );
            }
            shiftingWorkflowPojo.setProduct( inCrmQrcDto.getCrmShiftingWorkflowPojo().getProduct() );
            shiftingWorkflowPojo.setPreviousNetwork( inCrmQrcDto.getCrmShiftingWorkflowPojo().getPreviousNetwork() );
            shiftingWorkflowPojo.setPreviousBin( inCrmQrcDto.getCrmShiftingWorkflowPojo().getPreviousBin() );
            shiftingWorkflowPojo.setCurrentBin( inCrmQrcDto.getCrmShiftingWorkflowPojo().getCurrentBin() );
            shiftingWorkflowPojo.setPreviousStage( inCrmQrcDto.getCrmShiftingWorkflowPojo().getWorkflowStage() );
            shiftingWorkflowPojo.setLastModifiedBy( inCrmQrcDto.getUserId() );
            shiftingWorkflowPojo.setLastModifiedTime( Calendar.getInstance().getTime() );
            shiftingWorkflowPojo.setWorkflowStage( toStage );
            if ( StringUtils.equals( toStage, CRMOperationStages.CLOSE.getCode() ) )
            {
                shiftingWorkflowPojo.setStatus( CRMStatusCode.PROCESSED.getStatusCode() );
                shiftingWorkflowPojo.setCloserReason( inCrmQrcDto.getCrmShiftingWorkflowPojo().getCloserReason() );
                if ( StringUtils.isValidObj( inCrmQrcDto.getCrmShiftingWorkflowPojo().getCloserReason() )
                        && Long.parseLong( inCrmQrcDto.getCrmShiftingWorkflowPojo().getCloserReason() ) > 0 )
                {
                    inCrmQrcDto.getRemarksPojo().setReasonId( Long.parseLong( inCrmQrcDto.getCrmShiftingWorkflowPojo()
                                                                      .getCloserReason() ) );
                }
                inCrmQrcDto.getRemarksPojo().setActions( CRMOperationStages.CLOSE.getDesc() );
            }
            session.update( shiftingWorkflowPojo );
            inEvicts.put( CrmShiftingWorkflowPojo.class.getName(), shiftingWorkflowPojo.getShiftingId() );
            LOGGER.info( "WorkFlow To Stage:: " + shiftingWorkflowPojo.getWorkflowStage() );
            LOGGER.info( "WorkFlow ID:: " + shiftingWorkflowPojo.getWorkflowId() );
            saveRemarks( session, inCrmQrcDto, inEvicts );
            changeInboxBinOFShifting( inCrmQrcDto, shiftingWorkflowPojo );
            inCrmQrcDto.setCrmShiftingWorkflowPojo( shiftingWorkflowPojo );
        }
        else
        {
            LOGGER.info( "shiftingWorkflowPojos is not valid" );
        }
    }

    private CrmQrcDto generateInitiateInbox( CrmQrcDto inCrmQrcDto, Session session, Map<String, Long> inEvicts )
    {
        try
        {
            List<CrmInboxPojo> crmInboxPojos = null;
            Criteria criteria = null;
            criteria = session
                    .createCriteria( CrmInboxPojo.class )
                    .add( Restrictions.eq( "requestType", CRMRequestType.SHIFTING.getRequestCode() ) )
                    .add( Restrictions.eq( "stage", CRMOperationStages.FT_LEVEL1.getCode() ) )
                    .add( Restrictions.eq( "mappingId", inCrmQrcDto.getCrmShiftingWorkflowPojo().getWorkflowId() + "" ) );
            criteria.setCacheable( true );
            criteria.setCacheRegion( CrmInboxPojo.class.getName() );
            crmInboxPojos = criteria.list();
            if ( !CommonValidator.isValidCollection( crmInboxPojos ) )
            {
                criteria = session.createCriteria( CrmShiftingWorkflowPojo.class );
                criteria.add( Restrictions.eq( "shiftingId", inCrmQrcDto.getCrmShiftingWorkflowPojo().getShiftingId() ) );
                List<CrmShiftingWorkflowPojo> crmShiftingWorkflowPojos = criteria.list();
                if ( CommonValidator.isValidCollection( crmShiftingWorkflowPojos ) )
                {
                    inCrmQrcDto.setCrmShiftingWorkflowPojo( crmShiftingWorkflowPojos.get( 0 ) );
                }
                CrmInboxPojo crmInboxPojo = new CrmInboxPojo();
                crmInboxPojo.setRequestType( CRMRequestType.SHIFTING.getRequestCode() );
                crmInboxPojo.setMappingId( inCrmQrcDto.getCrmShiftingWorkflowPojo().getWorkflowId() + "" );
                crmInboxPojo.setStage( CRMOperationStages.FT_LEVEL1.getCode() );
                crmInboxPojo.setCreatedBy( inCrmQrcDto.getUserId() );
                crmInboxPojo.setCreatedTime( Calendar.getInstance().getTime() );
                crmInboxPojo.setStatus( CRMStatusCode.ACTIVE.getStatusCode() );
                long id = (Long) session.save( crmInboxPojo );
                inEvicts.put( CrmInboxPojo.class.getName(), null );
            }
            else if ( CommonValidator.isValidCollection( crmInboxPojos ) )
            {
                CrmInboxPojo crmInboxPojo = crmInboxPojos.get( 0 );
                crmInboxPojo.setUserId( inCrmQrcDto.getUserId() );
                crmInboxPojo.setLastModifiedBy( inCrmQrcDto.getUserId() );
                crmInboxPojo.setLastModifiedTime( Calendar.getInstance().getTime() );
                crmInboxPojo.setStatus( CRMStatusCode.ACTIVE.getStatusCode() );
                session.merge( crmInboxPojo );
            }
            inCrmQrcDto.setStatusCode( CRMServiceCode.CRM001.getStatusCode() );
            inCrmQrcDto.setStatusDesc( CRMServiceCode.CRM001.getStatusDesc() );
        }
        catch ( Exception ex )
        {
            LOGGER.error( "Error in Save CRF Method :: ", ex );
            inCrmQrcDto.setStatusCode( CRMServiceCode.CRM999.getStatusCode() );
            inCrmQrcDto.setStatusDesc( CRMServiceCode.CRM999.getStatusDesc() );
        }
        return inCrmQrcDto;
    }

    private CrmQrcDto changeInboxBinOFShifting( CrmQrcDto inCrmQrcDto, CrmShiftingWorkflowPojo shiftingWorkflowPojo )
        throws SOAPException
    {
        CRMServiceCode crmServiceCode = CRMServiceCode.CRM999;
        //going for forward CRF to Another User when user is forwarding docket from BP to FT then insert initiate entry in inbox for inactive status
        if ( StringUtils.isValidObj( shiftingWorkflowPojo ) )
        {
            ConfigDto configDto = new ConfigDto();
            configDto.setRequestType( CRMRequestType.SHIFTING.getRequestCode() );
            configDto.setFromStage( inCrmQrcDto.getCrmShiftingWorkflowPojo().getWorkflowStage() );
            configDto.setTostage( shiftingWorkflowPojo.getWorkflowStage() );
            configDto.setMappingId( shiftingWorkflowPojo.getWorkflowId() );
            configDto.setUserId( inCrmQrcDto.getUserId() );
            configDto.setFromUserId( inCrmQrcDto.getUserId() );
            configDto.setUserIdChange( IAppConstants.NO );
            boolean updated = getConfigManagerDao().changeInboxBin( configDto );
            if ( updated )
            {
                LOGGER.info( "Successfull changed Bin For Shifting Workflow ::" + shiftingWorkflowPojo.getWorkflowId() );
                crmServiceCode = CRMServiceCode.CRM001;
            }
        }
        else
        {
            LOGGER.info( "Not valid Object" );
        }
        inCrmQrcDto.setStatusCode( crmServiceCode.getStatusCode() );
        inCrmQrcDto.setStatusDesc( crmServiceCode.getStatusDesc() );
        return inCrmQrcDto;
    }

    @Override
    public boolean changeInboxBin( ConfigDto inConfigDto, Session inSession )
    {
        Criteria criteria = null;
        List<CrmInboxPojo> crmInboxPojos = null;
        boolean result = false;
        try
        {
            if ( StringUtils.isNotBlank( inConfigDto.getFromStage() ) )
            {
                criteria = inSession.createCriteria( CrmInboxPojo.class )
                        .add( Restrictions.eq( IAppConstants.PARAM_MAPPING_ID, inConfigDto.getMappingId() ) )
                        .add( Restrictions.eq( "requestType", inConfigDto.getRequestType() ) )
                        .add( Restrictions.eq( "stage", inConfigDto.getFromStage() ) );
                crmInboxPojos = criteria.list();
                if ( crmInboxPojos.size() > 0 )
                {
                    CrmInboxPojo crmInboxPojo = crmInboxPojos.get( 0 );
                    crmInboxPojo.setLastModifiedBy( inConfigDto.getUserId() );
                    crmInboxPojo.setLastModifiedTime( Calendar.getInstance().getTime() );
                    crmInboxPojo.setStatus( CRMStatusCode.INACTIVE.getStatusCode() );
                    inSession.update( crmInboxPojo );
                }
                else
                {
                    CrmInboxPojo crmInboxPojoForInsert = new CrmInboxPojo();
                    crmInboxPojoForInsert.setMappingId( inConfigDto.getMappingId() );
                    crmInboxPojoForInsert.setRequestType( inConfigDto.getRequestType() );
                    crmInboxPojoForInsert.setUserId( inConfigDto.getFromUserId() );
                    crmInboxPojoForInsert.setStage( inConfigDto.getFromStage() );
                    crmInboxPojoForInsert.setCreatedBy( inConfigDto.getUserId() );
                    crmInboxPojoForInsert.setPartnerId( inConfigDto.getPartnerId() );
                    crmInboxPojoForInsert.setStatus( CRMStatusCode.INACTIVE.getStatusCode() );
                    crmInboxPojoForInsert.setCreatedTime( Calendar.getInstance().getTime() );
                    inSession.save( crmInboxPojoForInsert );
                }
            }
            if ( ! ( StringUtils.equals( CRMOperationStages.CLOSE.getCode(), inConfigDto.getTostage() ) ) )
            {
                criteria = inSession.createCriteria( CrmInboxPojo.class )
                        .add( Restrictions.eq( IAppConstants.PARAM_MAPPING_ID, inConfigDto.getMappingId() ) )
                        .add( Restrictions.eq( "requestType", inConfigDto.getRequestType() ) )
                        .add( Restrictions.eq( "stage", inConfigDto.getTostage() ) );
                List<CrmInboxPojo> crmInboxPojosForTo = criteria.list();
                if ( !crmInboxPojosForTo.isEmpty() )
                {
                    CrmInboxPojo crmInboxPojoForTo = crmInboxPojosForTo.get( 0 );
                    crmInboxPojoForTo.setLastModifiedBy( inConfigDto.getUserId() );
                    crmInboxPojoForTo.setLastModifiedTime( Calendar.getInstance().getTime() );
                    crmInboxPojoForTo.setStatus( CRMStatusCode.ACTIVE.getStatusCode() );
                    if ( StringUtils.equals( inConfigDto.getUserIdChange(), IAppConstants.NO ) )
                    {
                        LOGGER.info( "No Need To Change User ID" );
                    }
                    else
                    {
                        LOGGER.info( "Need To update User Id" );
                        crmInboxPojoForTo.setUserId( inConfigDto.getToUserId() );
                    }
                    crmInboxPojoForTo.setUnRead( true );
                    if ( inConfigDto.getPartnerId() > 0 )
                    {
                        crmInboxPojoForTo.setPartnerId( inConfigDto.getPartnerId() );
                    }
                    crmInboxPojoForTo.setStage( inConfigDto.getTostage() );
                    inSession.update( crmInboxPojoForTo );
                }
                else
                {
                    CrmInboxPojo crmInboxPojoForInsert = new CrmInboxPojo();
                    crmInboxPojoForInsert.setMappingId( inConfigDto.getMappingId() );
                    crmInboxPojoForInsert.setRequestType( inConfigDto.getRequestType() );
                    if ( StringUtils.isNotBlank( inConfigDto.getToUserId() ) )
                    {
                        crmInboxPojoForInsert.setUserId( inConfigDto.getToUserId() );
                    }
                    if ( inConfigDto.getPartnerId() > 0 )
                    {
                        crmInboxPojoForInsert.setPartnerId( inConfigDto.getPartnerId() );
                        if ( !StringUtils.equals( inConfigDto.getRequestType(),
                                                  CRMRequestType.SHIFTING.getRequestCode() ) )
                        {
                            crmInboxPojoForInsert.setPartnerType( CRMFunctionalBinStages
                                    .getStageBySubStage( inConfigDto.getTostage() ) );
                        }
                    }
                    crmInboxPojoForInsert.setStage( inConfigDto.getTostage() );
                    crmInboxPojoForInsert.setCreatedBy( inConfigDto.getUserId() );
                    crmInboxPojoForInsert.setStatus( CRMStatusCode.ACTIVE.getStatusCode() );
                    crmInboxPojoForInsert.setCreatedTime( Calendar.getInstance().getTime() );
                    inSession.save( crmInboxPojoForInsert );
                }
            }
            LOGGER.info( "Successfully Transaction for change Bin Done" );
            result = true;
        }
        catch ( Exception ex )
        {
            LOGGER.error( "Getting Error in changeInboxBin method", ex );
            result = false;
        }
        finally
        {
            if ( result )
            {
                HibernateUtil.evictAll( CrmInboxPojo.class.getName(), null, null );
            }
        }
        return result;
    }

    @Override
    public CrmQrcDto approveRejectWaiver( CrmQrcDto inCrmQrcDto )
    {
        LOGGER.info( "Inside WorkFlowDaoImpl, approveRejectWaiver" );
        Session session = null;
        Transaction transaction = null;
        CrmCustomerActivityPojo activityLog = null;
        Map<String, Long> inEvicts = null;
        CRMServiceCode crmServiceCode = CRMServiceCode.CRM999;
        try
        {
            inEvicts = new HashMap<String, Long>();
            session = HibernateUtil.getCurrentSession();
            transaction = session.beginTransaction();
            activityLog = new CrmCustomerActivityPojo();
            inCrmQrcDto.setCustomerId( inCrmQrcDto.getCrmCustWaiverPojo().getCustomerId() );
            activityLog.setAction( CRMCustomerActivityActions.WAIVER.getActionCode() );
            inCrmQrcDto.getRemarksPojo().setActions( CRMCustomerActivityActions.WAIVER.getActionDesc() );
            if ( StringUtils.isValidObj( inCrmQrcDto.getCrmCustWaiverPojo() ) )
            {
                LOGGER.info( "WorkFlow Id" + inCrmQrcDto.getCrmCustWaiverPojo().getWorkflowId() );
                CrmCustWaiverPojo waiverPojo = CRMServiceUtils.getDBValues( CrmCustWaiverPojo.class, "workflowId",
                                                                            inCrmQrcDto.getCrmCustWaiverPojo()
                                                                                    .getWorkflowId(), session );
                LOGGER.info( "***********" + inCrmQrcDto.getCrmCustWaiverPojo().getStatus() );
                waiverPojo.setPreviousStage( waiverPojo.getWorkflowStage() );
                if ( StringUtils.equals( inCrmQrcDto.getCrmCustWaiverPojo().getStatus(),
                                         CRMStatusCode.REJECTED.getStatusCode() ) )
                {
                    inCrmQrcDto.setActivityAction( IAppConstants.NO );
                    if ( StringUtils.isValidObj( waiverPojo ) )
                    {
                        crmServiceCode = updateWaiver( inCrmQrcDto, session, waiverPojo, inEvicts );
                        if ( StringUtils.equals( crmServiceCode.getStatusCode(), CRMServiceCode.CRM001.getStatusCode() ) )
                        {
                            changeInBoxBinForWaiver( inCrmQrcDto, waiverPojo.getWorkflowStage(), session );
                            if ( StringUtils
                                    .equals( inCrmQrcDto.getStatusCode(), CRMServiceCode.CRM001.getStatusCode() ) )
                            {
                                inEvicts.put( CrmInboxPojo.class.getName(), null );
                            }
                        }
                        if ( StringUtils.equals( inCrmQrcDto.getStatusCode(), CRMServiceCode.CRM001.getStatusCode() ) )
                        {
                            inCrmQrcDto.getRemarksPojo().setActions( CRMCustomerActivityActions.WAIVER.getActionDesc() );
                            crmServiceCode = saveRemarks( session, inCrmQrcDto, inEvicts );
                            transaction.commit();
                            crmServiceCode = CRMServiceCode.CRM001;
                        }
                    }
                }
                else if ( StringUtils.equals( inCrmQrcDto.getCrmCustWaiverPojo().getStatus(),
                                              CRMStatusCode.WAIVER_APPROVE.getStatusCode() ) )
                {
                    String stage = getForwardStage( inCrmQrcDto, session );
                    if ( StringUtils.isValidObj( stage ) )
                    {
                        // inCrmQrcDto.getCrmCustWaiverPojo().setWorkflowStage( stage );
                        waiverPojo.setWorkflowStage( stage );
                        crmServiceCode = updateWaiver( inCrmQrcDto, session, waiverPojo, inEvicts );
                        if ( StringUtils.equals( crmServiceCode.getStatusCode(), CRMServiceCode.CRM001.getStatusCode() ) )
                        {
                            inCrmQrcDto = changeInBoxBinForWaiver( inCrmQrcDto, stage, session );
                        }
                        if ( StringUtils.equals( inCrmQrcDto.getStatusCode(), CRMServiceCode.CRM001.getStatusCode() ) )
                        {
                            crmServiceCode = saveRemarks( session, inCrmQrcDto, inEvicts );
                            transaction.commit();
                            crmServiceCode = CRMServiceCode.CRM001;
                        }
                    }
                    else
                    {
                        inCrmQrcDto.setActivityAction( IAppConstants.NO );
                        LOGGER.info( "Posting  Case:::" );
                        //  CrmBillingDto billingDto = new CrmBillingDto();
                        // billingDto.setStatusCode( CRMServiceCode.CRM001.getStatusCode() );
                        CrmBillingDto billingDto = getCrmBillingManager().postWaiver( inCrmQrcDto );
                        crmServiceCode = CRMServiceCode.valueOf( billingDto.getStatusCode() );
                        inCrmQrcDto.setBillingErrorCode( billingDto.getBillingErrorCode() );
                        LOGGER.info( " Billing Code" + billingDto.getStatusCode() );
                        if ( StringUtils.equals( CRMServiceCode.CRM001.getStatusCode(), billingDto.getStatusCode() ) )
                        {
                            waiverPojo.setStatus( CRMStatusCode.WAIVER_POSTED.getStatusCode() );
                            LOGGER.info( waiverPojo.getStatus() );
                            waiverPojo.setWaiverPostedDate( Calendar.getInstance().getTime() );
                            updateWaiver( inCrmQrcDto, session, waiverPojo, inEvicts );
                            if ( StringUtils.equals( crmServiceCode.getStatusCode(),
                                                     CRMServiceCode.CRM001.getStatusCode() ) )
                                changeInBoxBinForWaiver( inCrmQrcDto, waiverPojo.getWorkflowStage(), session );
                            if ( StringUtils
                                    .equals( inCrmQrcDto.getStatusCode(), CRMServiceCode.CRM001.getStatusCode() ) )
                                saveRemarks( session, inCrmQrcDto, inEvicts );
                            if ( StringUtils.equals( crmServiceCode.getStatusCode(),
                                                     CRMServiceCode.CRM001.getStatusCode() ) )
                                activityLog.setReason( waiverPojo.getWaiverType() + "-" + waiverPojo.getWaiverHead() );
                            generateActivity( inCrmQrcDto, activityLog );
                            transaction.commit();
                            crmServiceCode = CRMServiceCode.CRM001;
                        }
                        else
                        {
                            crmServiceCode = CRMServiceCode.valueOf( billingDto.getStatusCode() );
                            inCrmQrcDto.setBillingErrorCode( billingDto.getBillingErrorCode() );
                            LOGGER.info( "Status Code ::" + crmServiceCode );
                        }
                    }
                }
            }
        }
        catch ( HibernateException ex )
        {
            LOGGER.error( "HibernateException occured:::", ex );
            CRMServiceUtils.rollback( transaction );
            CRMServiceUtils.closeSessionOnException( session );
        }
        catch ( Exception ex )
        {
            LOGGER.error( "Exception occured:::", ex );
        }
        finally
        {
            CRMServiceUtils.rollback( transaction );
            CRMServiceUtils.closeSession( session );
            inCrmQrcDto.setStatusCode( crmServiceCode.getStatusCode() );
            inCrmQrcDto.setStatusDesc( crmServiceCode.getStatusDesc() );
            if ( CRMServiceCode.CRM001 == crmServiceCode )
            {
                HibernateUtil.evictAll( inEvicts );
            }
        }
        return inCrmQrcDto;
    }

    private String getForwardStage( CrmQrcDto inCrmQrcDto, Session session )
    {
        List<CrmRcaReasonPojo> waiverFunctionalBinList = getQrcManagerDao().getWaiverFunctionBin();
        CrmUserPojo crmUserPojo = CRMServiceUtils.getDBValues( CrmUserPojo.class, "userId", inCrmQrcDto.getUserId(),
                                                               session );
        List<String> userBin = Arrays.asList( crmUserPojo.getFunctionalBin().split( "," ) );
        List<CrmRcaReasonPojo> sortedlist = getQrcManagerDao().getBinIdForPostWaiver( waiverFunctionalBinList,
                                                                                      inCrmQrcDto
                                                                                              .getCrmCustWaiverPojo()
                                                                                              .getWaiverAmount() );
        String stage = getNextLevelFunctionalBin( sortedlist, userBin );
        return stage;
    }

    private String getNextLevelFunctionalBin( List<CrmRcaReasonPojo> inWaiverFunctionalBinList, List<String> inUserBin )
    {
        for ( CrmRcaReasonPojo waiverBin : inWaiverFunctionalBinList )
        {
            if ( inUserBin.contains( waiverBin.getCategoryId() + "" ) )
            {
                return null;
            }
        }
        return inWaiverFunctionalBinList.get( 0 ).getCategoryId() + "";
    }

    private CRMServiceCode updateWaiver( CrmQrcDto inCrmQrcDto,
                                         Session session,
                                         CrmCustWaiverPojo waiverPojo,
                                         Map<String, Long> inEvicts )
    {
        LOGGER.info( "updateWaiver method called:::" );
        CRMServiceCode crmServiceCode = CRMServiceCode.CRM999;
        try
        {
            LOGGER.info( "Waiver Status:::" + waiverPojo.getStatus() );
            if ( !StringUtils.equals( inCrmQrcDto.getCrmCustWaiverPojo().getStatus(),
                                      CRMStatusCode.WAIVER_APPROVE.getStatusCode() ) )
            {
                waiverPojo.setStatus( inCrmQrcDto.getCrmCustWaiverPojo().getStatus() );
                waiverPojo.setRejectionReason( inCrmQrcDto.getCrmCustWaiverPojo().getRejectionReason() );
                waiverPojo.setRejectedBy( inCrmQrcDto.getUserId() );
            }
            else
            {
                if ( StringUtils.isBlank( waiverPojo.getApprovedBy() ) )
                {
                    waiverPojo.setApprovedBy( inCrmQrcDto.getUserId() );
                    LOGGER.info( " Approved By" + waiverPojo.getApprovedBy() );
                }
                else
                {
                    waiverPojo.setFinalApprovedBy( inCrmQrcDto.getUserId() );
                    LOGGER.info( "Final Approved By" + waiverPojo.getFinalApprovedBy() );
                }
            }
            waiverPojo.setLastModifiedBy( inCrmQrcDto.getUserId() );
            waiverPojo.setLastModifiedTime( Calendar.getInstance().getTime() );
            session.update( waiverPojo );
            inEvicts.put( CrmCustWaiverPojo.class.getName(), waiverPojo.getWaiverId() );
            crmServiceCode = CRMServiceCode.CRM001;
        }
        catch ( HibernateException ex )
        {
            LOGGER.error( "Exception occured", ex );
        }
        return crmServiceCode;
    }

    private CrmQrcDto changeInBoxBinForWaiver( CrmQrcDto inCrmQrcDto, String stage, Session session )
    {
        CRMServiceCode crmServiceCode = CRMServiceCode.CRM999;
        String lastQRCStage = null;
        if ( StringUtils.isNotBlank( stage ) )
        {
            lastQRCStage = stage;
        }
        else
        {
            lastQRCStage = CRMOperationStages.INITIATE.getCode();
        }
        LOGGER.info( "WorkFlow Stage :::" + lastQRCStage );
        //going for forward CRF to Another User when user is forwarding docket from BP to FT then insert initiate entry in inbox for inactive status
        ConfigDto configDto = new ConfigDto();
        configDto.setFromStage( inCrmQrcDto.getCrmCustWaiverPojo().getWorkflowStage() );
        configDto.setTostage( lastQRCStage );
        configDto.setFromUserId( inCrmQrcDto.getUserId() );
        configDto.setRequestType( CRMRequestType.WAIVER.getRequestCode() );
        configDto.setMappingId( inCrmQrcDto.getCrmCustWaiverPojo().getWorkflowId() + "" );
        configDto.setUserIdChange( IAppConstants.NO );
        if ( StringUtils.equals( inCrmQrcDto.getActivityAction(), IAppConstants.NO ) )
        {
            configDto.setTostage( CRMOperationStages.CLOSE.getCode() );
        }
        /* if ( !StringUtils.equals( inCrmQrcDto.getActivityAction(), IAppConstants.NO ) )
        {
            LOGGER.info("New User Id"+inCrmQrcDto.getUserId());
            configDto.setUserId( inCrmQrcDto.getUserId() );
        }*/
        configDto.setUserId( inCrmQrcDto.getUserId() );
        boolean updated = getConfigManagerDao().changeInboxBin( configDto, session );
        if ( updated )
        {
            LOGGER.info( "Successfull changed Bin For WAIVER ::" + inCrmQrcDto.getCrmCustWaiverPojo().getWorkflowId() );
            crmServiceCode = CRMServiceCode.CRM001;
        }
        inCrmQrcDto.setStatusCode( crmServiceCode.getStatusCode() );
        inCrmQrcDto.setStatusDesc( crmServiceCode.getStatusDesc() );
        return inCrmQrcDto;
    }

    @Override
    public CrmQrcDto getShiftingDetails( CrmQrcDto inCrmQrcDto )
    {
        LOGGER.info( "Inside QrcManagerDaoImpl : getShiftingDetails" );
        CRMServiceCode crmServiceCode = CRMServiceCode.CRM999;
        List<CrmShiftingWorkflowPojo> shiftingWorkflowPojos = null;
        Session session = null;
        Criteria criteria = null;
        if ( StringUtils.isValidObj( inCrmQrcDto.getCustomerId() ) )
        {
            try
            {
                session = HibernateUtil.getCurrentSession();
                criteria = session.createCriteria( CrmShiftingWorkflowPojo.class );
                if ( StringUtils.isNotBlank( inCrmQrcDto.getCustomerId() ) )
                {
                    criteria.add( Restrictions.eq( "customerId", inCrmQrcDto.getCustomerId() ) );
                }
                if ( StringUtils.isValidObj( inCrmQrcDto.getCrmShiftingWorkflowPojo() )
                        && StringUtils.isNotBlank( inCrmQrcDto.getCrmShiftingWorkflowPojo().getWorkflowId() ) )
                {
                    criteria.add( Restrictions.eq( "workflowId", inCrmQrcDto.getCrmShiftingWorkflowPojo()
                            .getWorkflowId() ) );
                }
                if ( StringUtils.isValidObj( inCrmQrcDto.getCrmShiftingWorkflowPojo() )
                        && StringUtils.isNotBlank( inCrmQrcDto.getCrmShiftingWorkflowPojo().getStatus() ) )
                {
                    criteria.add( Restrictions.eq( "status", inCrmQrcDto.getCrmShiftingWorkflowPojo().getStatus() ) );
                }
                criteria.setCacheable( true );
                criteria.setCacheRegion( CrmShiftingWorkflowPojo.class.getName() );
                shiftingWorkflowPojos = criteria.list();
                if ( CommonValidator.isValidCollection( shiftingWorkflowPojos ) )
                {
                    inCrmQrcDto.setCrmShiftingWorkflowPojos( shiftingWorkflowPojos );
                    crmServiceCode = CRMServiceCode.CRM001;
                }
                else
                {
                    inCrmQrcDto.setCrmShiftingWorkflowPojos( new ArrayList<CrmShiftingWorkflowPojo>() );
                }
            }
            catch ( Exception ex )
            {
                LOGGER.error( "Exception while retreiving shifting details ", ex );
            }
            finally
            {
                CRMServiceUtils.closeSession( session );
                inCrmQrcDto.setStatusCode( crmServiceCode.getStatusCode() );
                inCrmQrcDto.setStatusDesc( crmServiceCode.getStatusDesc() );
            }
        }
        return inCrmQrcDto;
    }

    @Override
    public CrmQrcDto updateCustomerDetails( CrmQrcDto inCrmQrcDto )
    {
        SessionFactory sessionFactory = null;
        Session session = null;
        Transaction transaction = null;
        CRMServiceCode code = CRMServiceCode.CRM997;
        try
        {
            sessionFactory = HibernateUtil.getSessionFactory();
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            CrmCustomerDetailsPojo customerPojo = CRMServiceUtils.getDBValues( CrmCustomerDetailsPojo.class,
                                                                               "customerId", inCrmQrcDto
                                                                                       .getCrmShiftingWorkflowPojo()
                                                                                       .getCustomerId(), session );
            customerPojo.setNpId( inCrmQrcDto.getCrmShiftingWorkflowPojo().getNpId() );
            if ( !StringUtils.equals( inCrmQrcDto.getCrmShiftingWorkflowPojo().getProduct(), customerPojo.getProduct() ) )
            {
                customerPojo.setProduct( inCrmQrcDto.getCrmShiftingWorkflowPojo().getProduct() );
            }
            customerPojo.setLastModifiedBy( inCrmQrcDto.getUserId() );
            session.merge( customerPojo );
            transaction.commit();
            code = CRMServiceCode.CRM001;
        }
        catch ( Exception ex )
        {
            code = CRMServiceCode.CRM997;
            ex.printStackTrace();
        }
        finally
        {
            inCrmQrcDto.setStatusCode( code.getStatusCode() );
            inCrmQrcDto.setStatusDesc( code.getStatusDesc() );
        }
        return inCrmQrcDto;
    }

    private CRMServiceCode saveRemarks( Session session, CrmQrcDto inCrmQrcDto, Map<String, Long> inEvicts )
    {
        CRMServiceCode crmCode = CRMServiceCode.CRM999;
        try
        {
            LOGGER.info( "saveRemarks:::" );
            if ( StringUtils.isValidObj( inCrmQrcDto.getRemarksPojo() ) )
            {
                if ( StringUtils.equals( inCrmQrcDto.getRemarksPojo().getActions(),
                                         CRMCustomerActivityActions.WAIVER.getActionDesc() ) )
                {
                    inCrmQrcDto.getRemarksPojo().setMappingId( inCrmQrcDto.getCrmCustWaiverPojo().getWorkflowId() );
                }
                else
                {
                    inCrmQrcDto.getRemarksPojo().setMappingId( inCrmQrcDto.getCrmShiftingWorkflowPojo().getWorkflowId()
                                                                       + "" );
                }
                inCrmQrcDto.getRemarksPojo().setMappingType( CRMRCAReason.QRC.getStatusCode() );
                inCrmQrcDto.getRemarksPojo().setCreatedBy( inCrmQrcDto.getUserId() );
                inCrmQrcDto.getRemarksPojo().setCreatedTime( Calendar.getInstance().getTime() );
                long id = (Long) session.save( inCrmQrcDto.getRemarksPojo() );
                if ( id > 0 )
                {
                    crmCode = CRMServiceCode.CRM001;
                    inEvicts.put( RemarksPojo.class.getName(), null );
                }
            }
        }
        catch ( HibernateException ex )
        {
            LOGGER.error( "Exception occured", ex );
        }
        return crmCode;
    }

    private void generateActivity( CrmQrcDto inCrmQrcDto, CrmCustomerActivityPojo activityLog )
    {
        activityLog.setCustomerId( inCrmQrcDto.getCustomerId() );
        activityLog.setNewValue( inCrmQrcDto.getCrmCustWaiverPojo().getWaiverAmount() + "" );
        activityLog.setCreatedBy( inCrmQrcDto.getUserId() );
        activityLog.setServiceIp( CRMUtils.getServerIp() );
        activityLog.setClientIp( inCrmQrcDto.getClientIPAddress() );
        activityLog.setServerIp( inCrmQrcDto.getServerIPAddress() );
        CRMServiceUtils.createActivityLog( activityLog );
    }

    private boolean CheckDuplicateShifting( String inCustomerId )
    {
        CrmQrcDto dto = new CrmQrcDto();
        dto.setCustomerId( inCustomerId );
        CrmShiftingWorkflowPojo pojo = new CrmShiftingWorkflowPojo();
        pojo.setStatus( CRMStatusCode.INPROCESS.getStatusCode() );
        dto.setCrmShiftingWorkflowPojo( pojo );
        dto = getShiftingDetails( dto );
        if ( StringUtils.equals( dto.getStatusCode(), CRMServiceCode.CRM001.getStatusCode() ) )
        {
            if ( !StringUtils.isValidObj( dto.getCrmShiftingWorkflowPojos() ) )
            {
                return true;
            }
        }
        else if ( dto.getCrmShiftingWorkflowPojos().isEmpty() )
        {
            return true;
        }
        return false;
    }
}