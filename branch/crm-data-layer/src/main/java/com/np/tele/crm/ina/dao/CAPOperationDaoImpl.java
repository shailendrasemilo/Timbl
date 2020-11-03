package com.np.tele.crm.ina.dao;

import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.xml.soap.SOAPException;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;

import com.np.tele.crm.billing.manager.ICrmBillingManager;
import com.np.tele.crm.config.dao.IConfigManagerDao;
import com.np.tele.crm.constants.CRMCustomerActivityActions;
import com.np.tele.crm.constants.CRMDisplayListConstants;
import com.np.tele.crm.constants.CRMEvents;
import com.np.tele.crm.constants.CRMOperationStages;
import com.np.tele.crm.constants.CRMParameter;
import com.np.tele.crm.constants.CRMRCAReason;
import com.np.tele.crm.constants.CRMRequestType;
import com.np.tele.crm.constants.CRMServiceCode;
import com.np.tele.crm.constants.CRMStatusCode;
import com.np.tele.crm.constants.CrmActionEnum;
import com.np.tele.crm.constants.IAppConstants;
import com.np.tele.crm.constants.IDateConstants;
import com.np.tele.crm.constants.QRCRolCategories;
import com.np.tele.crm.dto.ConfigDto;
import com.np.tele.crm.dto.CrmBillingDto;
import com.np.tele.crm.dto.CrmCapDto;
import com.np.tele.crm.dto.CrmFinanceDto;
import com.np.tele.crm.dto.CrmQrcDto;
import com.np.tele.crm.exceptions.DuplicateRecieptException;
import com.np.tele.crm.ext.pojos.CommonCustDetailsPojo;
import com.np.tele.crm.pojos.AppointmentPojo;
import com.np.tele.crm.pojos.AreaPojo;
import com.np.tele.crm.pojos.CityPojo;
import com.np.tele.crm.pojos.CrmAdditionalDetailsPojo;
import com.np.tele.crm.pojos.CrmAddressDetailsPojo;
import com.np.tele.crm.pojos.CrmAuditLogPojo;
import com.np.tele.crm.pojos.CrmCrfAddressDetailsPojo;
import com.np.tele.crm.pojos.CrmCrfDetailsPojo;
import com.np.tele.crm.pojos.CrmCrfNationalityDetailsPojo;
import com.np.tele.crm.pojos.CrmCrfNetworkConfigPojo;
import com.np.tele.crm.pojos.CrmCrfOrderDetailsPojo;
import com.np.tele.crm.pojos.CrmCrfPlanDetailsPojo;
import com.np.tele.crm.pojos.CrmCustAadharNumberPojo;
import com.np.tele.crm.pojos.CrmCustMyAccountPojo;
import com.np.tele.crm.pojos.CrmCustomerActivityPojo;
import com.np.tele.crm.pojos.CrmCustomerDetailsPojo;
import com.np.tele.crm.pojos.CrmDocumentDetailsPojo;
import com.np.tele.crm.pojos.CrmInboxPojo;
import com.np.tele.crm.pojos.CrmNationalityDetailsPojo;
import com.np.tele.crm.pojos.CrmNetworkConfigurationsPojo;
import com.np.tele.crm.pojos.CrmOrderDetailsPojo;
import com.np.tele.crm.pojos.CrmParamDataPojo;
import com.np.tele.crm.pojos.CrmPartnerNetworkConfigPojo;
import com.np.tele.crm.pojos.CrmPaymentDetailsPojo;
import com.np.tele.crm.pojos.CrmPlanDetailsPojo;
import com.np.tele.crm.pojos.CrmRcaReasonPojo;
import com.np.tele.crm.pojos.CrmUserPojo;
import com.np.tele.crm.pojos.LMSPojo;
import com.np.tele.crm.pojos.LmsCrfMappingPojo;
import com.np.tele.crm.pojos.PartnerPojo;
import com.np.tele.crm.pojos.RemarksPojo;
import com.np.tele.crm.pojos.SocietyPojo;
import com.np.tele.crm.pojos.StatePojo;
import com.np.tele.crm.qrc.dao.IQrcManagerDao;
import com.np.tele.crm.utils.CRMServiceUtils;
import com.np.tele.crm.utils.CRMUtils;
import com.np.tele.crm.utils.CrmDaoHelper;
import com.np.tele.crm.utils.DateUtils;
import com.np.tele.crm.utils.EncryptionUtil;
import com.np.tele.crm.utils.FileUtils;
import com.np.tele.crm.utils.GlobalUtils;
import com.np.tele.crm.utils.HibernateUtil;
import com.np.tele.crm.utils.PojoUtils;
import com.np.tele.crm.utils.StringUtils;
import com.np.validator.util.CommonValidator;

public class CAPOperationDaoImpl
    implements ICAPOperationDao
{
    private static final Logger LOGGER            = Logger.getLogger( CAPOperationDaoImpl.class );
    private IConfigManagerDao   configManagerDao  = null;
    private ICrmBillingManager  crmBillingManager = null;
    private IQrcManagerDao      qrcManagerDao     = null;

    public IConfigManagerDao getConfigManagerDao()
    {
        return configManagerDao;
    }

    public void setConfigManagerDao( IConfigManagerDao inConfigManagerDao )
    {
        configManagerDao = inConfigManagerDao;
    }

    public ICrmBillingManager getCrmBillingManager()
    {
        return crmBillingManager;
    }

    public void setCrmBillingManager( ICrmBillingManager inCrmBillingManager )
    {
        crmBillingManager = inCrmBillingManager;
    }

    public IQrcManagerDao getQrcManagerDao()
    {
        return qrcManagerDao;
    }

    public void setQrcManagerDao( IQrcManagerDao qrcManagerDao )
    {
        this.qrcManagerDao = qrcManagerDao;
    }

    @Override
    public CrmCapDto getCustomerDetails( CrmCapDto inCapDto )
    {
        SessionFactory sessionFactory = null;
        Session session = null;
        Criteria criteria = null;
        CRMServiceCode statusCode = CRMServiceCode.CRM999;
        CrmCustomerDetailsPojo crmCustomerDetailsPojo = null;
        try
        {
            LOGGER.info( "Seraching CAF ID:" + inCapDto.getCustomerDetailsPojo().getCrfId() + " in Customer Details." );
            sessionFactory = HibernateUtil.getSessionFactory();
            session = sessionFactory.openSession();
            if ( inCapDto.getCustomerRecordId() > 0 )
            {
                crmCustomerDetailsPojo = (CrmCustomerDetailsPojo) session.get( CrmCustomerDetailsPojo.class,
                                                                               inCapDto.getCustomerRecordId() );
            }
            else
            {
                crmCustomerDetailsPojo = CRMServiceUtils.getDBValues( CrmCustomerDetailsPojo.class, "crfId", inCapDto
                        .getCustomerDetailsPojo().getCrfId(), session );
            }
            if ( StringUtils.isValidObj( crmCustomerDetailsPojo ) )
            {
                LOGGER.info( "CAF ID Found, Searching Details in Inbox with Record Id :: "
                        + crmCustomerDetailsPojo.getRecordId() );
                criteria = session.createCriteria( CrmInboxPojo.class )
                        .add( Restrictions.eq( "requestType", CRMRequestType.CAF.getRequestCode() ) )
                        .add( Restrictions.eq( "userId", inCapDto.getUserId() ) )
                        .add( Restrictions.eq( "mappingId", crmCustomerDetailsPojo.getRecordId() + "" ) );
                criteria.setCacheable( true );
                criteria.setCacheRegion( CrmInboxPojo.class.getName() );
                List<CrmInboxPojo> crmInboxPojos = criteria.list();
                if ( CommonValidator.isValidCollection( crmInboxPojos ) )
                {
                    if ( StringUtils.equals( CRMOperationStages.INITIATE.getCode(),
                                             crmCustomerDetailsPojo.getCrfStage() )
                            || StringUtils.equals( CRMOperationStages.FT_REJECT.getCode(),
                                                   crmCustomerDetailsPojo.getCrfStage() ) )
                    {
                        CrmInboxPojo crmInboxPojo = new CrmInboxPojo();
                        crmInboxPojo.setRequestType( CRMRequestType.CAF.getRequestCode() );
                        crmInboxPojo.setMappingId( String.valueOf( crmCustomerDetailsPojo.getRecordId() ) );
                        crmInboxPojo.setStatus( CRMStatusCode.ACTIVE.getStatusCode() );
                        crmInboxPojo.setStage( crmCustomerDetailsPojo.getCrfStage() );
                        crmInboxPojo.setUserId( inCapDto.getUserId() );
                        int indexNo = crmInboxPojos.indexOf( crmInboxPojo );
                        if ( indexNo >= 0 )
                        {
                            LOGGER.info( "Same Created User Active. User Can Modify the CAF." );
                            statusCode = CRMServiceCode.CRM305;
                        }
                        else
                        {
                            LOGGER.info( "CAF Not Active For User :: " + inCapDto.getUserId() );
                            statusCode = CRMServiceCode.CRM306;
                        }
                    }
                    else
                    {
                        LOGGER.info( "CAF Not in INITIATE OR FT_REJECT STATE " + inCapDto.getUserId() );
                        statusCode = CRMServiceCode.CRM311;
                    }
                    inCapDto.setCustomerDetailsPojo( crmCustomerDetailsPojo );
                    getCRFDetails( inCapDto, crmCustomerDetailsPojo.getRecordId(), true );
                    setStateCityNames( session, crmCustomerDetailsPojo );
                }
                else
                {
                    LOGGER.info( "CAF Persent In Customer Details Table And Punched By Another User.Can't Display OR Modify." );
                    statusCode = CRMServiceCode.CRM304;
                }
            }
            else
            {
                LOGGER.info( "Going To Check CAF In LMS" );
                LmsCrfMappingPojo crfMappingPojos = CRMServiceUtils.getDBValues( LmsCrfMappingPojo.class, "crfValue",
                                                                                 inCapDto.getCustomerDetailsPojo()
                                                                                         .getCrfId(), session );
                if ( StringUtils.isValidObj( crfMappingPojos ) )
                {
                    LOGGER.info( "Attached CAF :: " + inCapDto.getCustomerDetailsPojo().getCrfId()
                            + " :: with Lead :: L" + crfMappingPojos.getLmsId() );
                    LMSPojo lmsPojo = (LMSPojo) session.get( LMSPojo.class, crfMappingPojos.getLmsId() );
                    inCapDto.setLmsPojo( lmsPojo );
                    statusCode = CRMServiceCode.CRM302;
                }
                //here we will work for RF if crf id not present in lms then set no data found
                else if ( StringUtils.equals( CRMDisplayListConstants.RADIO_FREQUENCY.getCode(), inCapDto
                        .getCustomerDetailsPojo().getProduct() ) )
                {
                    LOGGER.info( "CAF Id Not Present In LMS For RF. CAF Id : "
                            + inCapDto.getCustomerDetailsPojo().getCrfId() );
                    statusCode = CRMServiceCode.CRM310;
                }
                else
                {
                    statusCode = CRMServiceCode.CRM001;
                }
            }
        }
        catch ( Exception ex )
        {
            LOGGER.error( "Getting Error In Validate CAF Method :: ", ex );
            statusCode = CRMServiceCode.CRM999;
        }
        finally
        {
            CRMServiceUtils.closeSession( session );
            inCapDto.setStatusCode( statusCode.getStatusCode() );
            inCapDto.setStatusDesc( statusCode.getStatusDesc() );
        }
        return inCapDto;
    }

    @Override
    public CrmCapDto getCustomerDetails( long customerRecordId, String inCRFId, String inCustomerId )
    {
        SessionFactory sessionFactory = null;
        Session session = null;
        CRMServiceCode statusCode = CRMServiceCode.CRM999;
        CrmCustomerDetailsPojo crmCustomerDetailsPojo = null;
        CrmCapDto crmCapDto = new CrmCapDto();
        Criteria criteria = null;
        try
        {
            sessionFactory = HibernateUtil.getSessionFactory();
            session = sessionFactory.openSession();
            if ( customerRecordId > 0 )
            {
                crmCustomerDetailsPojo = (CrmCustomerDetailsPojo) session.get( CrmCustomerDetailsPojo.class,
                                                                               customerRecordId );
            }
            else if ( StringUtils.isNotBlank( inCRFId ) )
            {
                crmCustomerDetailsPojo = CRMServiceUtils.getDBValues( CrmCustomerDetailsPojo.class, "crfId", inCRFId,
                                                                      session );
            }
            if ( !StringUtils.isValidObj( crmCustomerDetailsPojo ) )
            {
                crmCustomerDetailsPojo = CRMServiceUtils.getDBValues( CrmCustomerDetailsPojo.class, "customerId",
                                                                      inCustomerId, session );
            }
            if ( StringUtils.isValidObj( crmCustomerDetailsPojo ) )
            {
                criteria = session.createCriteria( CrmInboxPojo.class );
                criteria.add( Restrictions.eq( "mappingId", crmCustomerDetailsPojo.getRecordId() + "" ) )
                        .add( Restrictions.eq( "status", CRMStatusCode.ACTIVE.getStatusCode() ) );
                List<CrmInboxPojo> inboxList = criteria.list();
                if ( CommonValidator.isValidCollection( inboxList ) )
                {
                    if ( StringUtils.isNotEmpty( inboxList.get( 0 ).getUserId() ) )
                    {
                        crmCustomerDetailsPojo.setCurrentUser( inboxList.get( 0 ).getUserId() );
                    }
                    else
                        crmCustomerDetailsPojo.setCurrentUser( "-" );
                }
                else
                    crmCustomerDetailsPojo.setCurrentUser( "-" );
                crmCapDto.setCustomerDetailsPojo( crmCustomerDetailsPojo );
                getCRFDetails( crmCapDto, crmCustomerDetailsPojo.getRecordId(), true );
                setStateCityNames( session, crmCustomerDetailsPojo );
                if ( StringUtils.numericValue( crmCapDto.getCustomerDetailsPojo().getBusinessPartner() + "" ) > 0 )
                {
                    crmCapDto.setSalesPartner( (PartnerPojo) session.get( PartnerPojo.class, crmCapDto
                            .getCustomerDetailsPojo().getBusinessPartner() ) );
                }
                crmCapDto.getCustomerDetailsPojo()
                        .setSalesUser( CRMServiceUtils.getDBValues( CrmUserPojo.class, "userId", crmCapDto
                                               .getCustomerDetailsPojo().getSalesRepresentative(), session ) );
                if ( StringUtils.numericValue( crmCapDto.getCustomerDetailsPojo().getNpId() + "" ) > 0 )
                {
                    crmCapDto.setNetworkPartner( (PartnerPojo) session.get( PartnerPojo.class, crmCapDto
                            .getCustomerDetailsPojo().getNpId() ) );
                }
                statusCode = CRMServiceCode.CRM001;
            }
            else
            {
                statusCode = CRMServiceCode.CRM309;
            }
        }
        catch ( Exception ex )
        {
            LOGGER.error( "Getting Error in getCustomerDetails Method :: ", ex );
            statusCode = CRMServiceCode.CRM999;
        }
        finally
        {
            CRMServiceUtils.closeSession( session );
            crmCapDto.setStatusCode( statusCode.getStatusCode() );
            crmCapDto.setStatusDesc( statusCode.getStatusDesc() );
        }
        return crmCapDto;
    }

    @Override
    public CrmCapDto viewCRFDetails( long customerRecordId, String inCRFId, String inCustomerId )
    {
        SessionFactory sessionFactory = null;
        Session session = null;
        CRMServiceCode statusCode = CRMServiceCode.CRM999;
        CrmCustomerDetailsPojo crmCustomerDetailsPojo = null;
        CrmCapDto crmCapDto = new CrmCapDto();
        boolean isDuplicated = true;
        try
        {
            sessionFactory = HibernateUtil.getSessionFactory();
            session = sessionFactory.openSession();
            if ( customerRecordId > 0 )
            {
                crmCustomerDetailsPojo = (CrmCustomerDetailsPojo) session.get( CrmCrfDetailsPojo.class,
                                                                               customerRecordId );
                if ( !StringUtils.isValidObj( crmCustomerDetailsPojo ) )
                {
                    crmCustomerDetailsPojo = (CrmCustomerDetailsPojo) session.get( CrmCustomerDetailsPojo.class,
                                                                                   customerRecordId );
                    isDuplicated = false;
                }
            }
            else if ( StringUtils.isNotBlank( inCRFId ) )
            {
                crmCustomerDetailsPojo = CRMServiceUtils.getDBValues( CrmCrfDetailsPojo.class, "crfId", inCRFId,
                                                                      session );
                if ( !StringUtils.isValidObj( crmCustomerDetailsPojo ) )
                {
                    crmCustomerDetailsPojo = CRMServiceUtils.getDBValues( CrmCustomerDetailsPojo.class, "crfId",
                                                                          inCRFId, session );
                    isDuplicated = false;
                }
            }
            else
            {
                crmCustomerDetailsPojo = CRMServiceUtils.getDBValues( CrmCrfDetailsPojo.class, "customerId",
                                                                      inCustomerId, session );
                if ( !StringUtils.isValidObj( crmCustomerDetailsPojo ) )
                {
                    crmCustomerDetailsPojo = CRMServiceUtils.getDBValues( CrmCustomerDetailsPojo.class, "customerId",
                                                                          inCustomerId, session );
                    isDuplicated = false;
                }
            }
            if ( StringUtils.isValidObj( crmCustomerDetailsPojo ) )
            {
                crmCapDto.setCustomerDetailsPojo( crmCustomerDetailsPojo );
                if ( isDuplicated )
                {
                    viewCRFBaseDetails( crmCapDto, crmCustomerDetailsPojo.getRecordId(), true );
                }
                else
                {
                    getCRFDetails( crmCapDto, crmCustomerDetailsPojo.getRecordId(), true );
                }
                setStateCityNames( session, crmCustomerDetailsPojo );
                if ( StringUtils.numericValue( crmCapDto.getCustomerDetailsPojo().getBusinessPartner() + "" ) > 0 )
                {
                    crmCapDto.setSalesPartner( (PartnerPojo) session.get( PartnerPojo.class, crmCapDto
                            .getCustomerDetailsPojo().getBusinessPartner() ) );
                }
                crmCapDto.getCustomerDetailsPojo()
                        .setSalesUser( CRMServiceUtils.getDBValues( CrmUserPojo.class, "userId", crmCapDto
                                               .getCustomerDetailsPojo().getSalesRepresentative(), session ) );
                if ( StringUtils.numericValue( crmCapDto.getCustomerDetailsPojo().getNpId() + "" ) > 0 )
                {
                    crmCapDto.setNetworkPartner( (PartnerPojo) session.get( PartnerPojo.class, crmCapDto
                            .getCustomerDetailsPojo().getNpId() ) );
                }
                statusCode = CRMServiceCode.CRM001;
            }
            else
            {
                statusCode = CRMServiceCode.CRM309;
            }
        }
        catch ( Exception ex )
        {
            LOGGER.error( "Getting Error in getCustomerDetails Method :: ", ex );
            statusCode = CRMServiceCode.CRM999;
        }
        finally
        {
            CRMServiceUtils.closeSession( session );
            crmCapDto.setStatusCode( statusCode.getStatusCode() );
            crmCapDto.setStatusDesc( statusCode.getStatusDesc() );
        }
        return crmCapDto;
    }

    private CrmCapDto viewCRFBaseDetails( CrmCapDto inCapDto, long inCustomerRecordId, boolean toDisplay )
    {
        SessionFactory sessionFactory = null;
        Session session = null;
        try
        {
            LOGGER.info( "Going To Get Detail Of CAF " + inCapDto.getCustomerDetailsPojo().getCrfId()
                    + " and Customer Id ::" + inCustomerRecordId );
            sessionFactory = HibernateUtil.getSessionFactory();
            session = sessionFactory.openSession();
            inCapDto.setAdditionalDetailsPojo( CRMServiceUtils.getDBValues( CrmAdditionalDetailsPojo.class,
                                                                            IAppConstants.PARAM_CUSTOMER_RECORD_ID,
                                                                            inCustomerRecordId, session ) );
            inCapDto.setDocumentDetailsPojo( CRMServiceUtils.getDBValues( CrmDocumentDetailsPojo.class,
                                                                          IAppConstants.PARAM_CUSTOMER_RECORD_ID,
                                                                          inCustomerRecordId, session ) );
            inCapDto.setNationalityDetailsPojo( CRMServiceUtils.getDBValues( CrmCrfNationalityDetailsPojo.class,
                                                                             IAppConstants.PARAM_CUSTOMER_RECORD_ID,
                                                                             inCustomerRecordId, session ) );
            Map<String, Object> criteriaMap = new HashMap<String, Object>();
            criteriaMap.put( "installationStatus", CRMDisplayListConstants.PREINSTALLATION.getCode() );
            List<String> entityType = new ArrayList<String>();
            entityType.add( CRMDisplayListConstants.TELESERVICES.getCode() );
            entityType.add( CRMDisplayListConstants.TELESOLUTIONS.getCode() );
            criteriaMap.put( "entityType", entityType );
            criteriaMap.put( IAppConstants.PARAM_CUSTOMER_RECORD_ID, inCustomerRecordId );
            inCapDto.setPaymentDetailsPojosList( CRMServiceUtils.getDBValueList( CrmPaymentDetailsPojo.class,
                                                                                 criteriaMap, session ) );
            inCapDto.setOrderDetailsPojo( CRMServiceUtils.getDBValues( CrmCrfOrderDetailsPojo.class,
                                                                       IAppConstants.PARAM_CUSTOMER_RECORD_ID,
                                                                       inCustomerRecordId, session ) );
            inCapDto.setNetworkConfigurationsPojo( CRMServiceUtils.getDBValues( CrmCrfNetworkConfigPojo.class,
                                                                                IAppConstants.PARAM_CUSTOMER_RECORD_ID,
                                                                                inCustomerRecordId, session ) );
            inCapDto.setCrmParamDataPojos( CRMServiceUtils.getDBValueList( CrmParamDataPojo.class,
                                                                           IAppConstants.PARAM_MAPPING_ID,
                                                                           inCustomerRecordId + "", session ) );
            inCapDto.getCustomerDetailsPojo()
                    .getCrmAddressDetailses()
                    .addAll( CRMServiceUtils.getDBValueList( CrmCrfAddressDetailsPojo.class,
                                                             IAppConstants.PARAM_CUSTOMER_RECORD_ID,
                                                             inCustomerRecordId, session ) );
            inCapDto.getCustomerDetailsPojo()
                    .getCrmPlanDetailses()
                    .addAll( CRMServiceUtils.getDBValueList( CrmCrfPlanDetailsPojo.class,
                                                             IAppConstants.PARAM_CUSTOMER_RECORD_ID,
                                                             inCustomerRecordId, session ) );
            if ( !StringUtils.equals( CRMDisplayListConstants.BROADBAND.getCode(), inCapDto.getCustomerDetailsPojo()
                    .getProduct() ) )
            {
                if ( StringUtils.isValidObj( inCapDto.getNetworkConfigurationsPojo() ) )
                {
                    inCapDto.setCrmPartnerNetworkConfig( (CrmPartnerNetworkConfigPojo) session
                            .get( CrmPartnerNetworkConfigPojo.class,
                                  StringUtils.numericValue( inCapDto.getNetworkConfigurationsPojo().getOption82() ) ) );
                }
            }
            if ( toDisplay )
            {
                inCapDto.setAppointmentPojo( CRMServiceUtils.getDBValues( AppointmentPojo.class,
                                                                          IAppConstants.PARAM_MAPPING_ID,
                                                                          inCustomerRecordId + "", session ) );
                inCapDto.setRemarksPojos( CRMServiceUtils.getDBValueList( RemarksPojo.class,
                                                                          IAppConstants.PARAM_MAPPING_ID,
                                                                          inCustomerRecordId + "", "createdTime",
                                                                          false, session ) );
            }
            if ( StringUtils.isValidObj( inCapDto.getCustomerDetailsPojo() )
                    && StringUtils.isNotBlank( inCapDto.getCustomerDetailsPojo().getSalesRepresentative() ) )
            {
                CrmUserPojo salesUser = CRMServiceUtils.getDBValues( CrmUserPojo.class, "userId", inCapDto
                        .getCustomerDetailsPojo().getSalesRepresentative(), session );
                if ( StringUtils.isValidObj( salesUser ) )
                {
                    inCapDto.getCustomerDetailsPojo().setSalesUser( salesUser );
                }
            }
        }
        catch ( Exception ex )
        {
            LOGGER.error( "Error In Get Caf Details Method ", ex );
        }
        finally
        {
            CRMServiceUtils.closeSession( session );
        }
        return inCapDto;
    }

    private void setStateCityNames( Session session, CrmCustomerDetailsPojo crmCustomerDetailsPojo )
    {
        Set<CrmAddressDetailsPojo> addresses = crmCustomerDetailsPojo.getCrmAddressDetailses();
        for ( CrmAddressDetailsPojo crmAddressDetailsPojo : addresses )
        {
            if ( StringUtils.isValidObj( crmAddressDetailsPojo.getStateId() ) && crmAddressDetailsPojo.getStateId() > 0 )
            {
                StatePojo stPojo = (StatePojo) session.get( StatePojo.class, crmAddressDetailsPojo.getStateId() );
                {
                    crmAddressDetailsPojo.setStateName( stPojo.getStateName() );
                    if ( StringUtils.isValidObj( crmAddressDetailsPojo.getCityId() )
                            && crmAddressDetailsPojo.getCityId() > 0 )
                    {
                        CityPojo cityPojo = (CityPojo) session.get( CityPojo.class, crmAddressDetailsPojo.getCityId() );
                        if ( StringUtils.isValidObj( cityPojo ) )
                        {
                            crmAddressDetailsPojo.setCityName( cityPojo.getCityName() );
                            if ( StringUtils.isValidObj( crmAddressDetailsPojo.getInstAreaId() )
                                    && crmAddressDetailsPojo.getInstAreaId() > 0 )
                            {
                                AreaPojo areaPojo = (AreaPojo) session.get( AreaPojo.class,
                                                                            crmAddressDetailsPojo.getInstAreaId() );
                                if ( StringUtils.isValidObj( areaPojo ) )
                                {
                                    crmAddressDetailsPojo.setAreaName( areaPojo.getArea() );
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    public static void main( String[] args )
    {
        /* try
         {
             SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
             Session session = sessionFactory.openSession();
             long l = 683;
             CrmCustomerDetailsPojo crmCustomerDetailsPojo = (CrmCustomerDetailsPojo) session
                     .get( CRFBaseCustomerDetailsPojo.class, l );
             System.out.println( "crmCustomerDetailsPojo:: " + crmCustomerDetailsPojo );
         }
         catch ( Exception ex )
         {
             System.out.println( "HHHH" );
             System.out.println( ex );
             ex.printStackTrace();
         }*/
    }

    @Override
    public CrmCapDto getCRFDetails( CrmCapDto inCapDto, long inCustomerRecordId, boolean toDisplay )
    {
        SessionFactory sessionFactory = null;
        Session session = null;
        try
        {
            LOGGER.info( "Going To Get Detail Of CAF " + inCapDto.getCustomerDetailsPojo().getCrfId()
                    + " and Customer Id ::" + inCustomerRecordId );
            sessionFactory = HibernateUtil.getSessionFactory();
            session = sessionFactory.openSession();
            inCapDto.setAdditionalDetailsPojo( CRMServiceUtils.getDBValues( CrmAdditionalDetailsPojo.class,
                                                                            IAppConstants.PARAM_CUSTOMER_RECORD_ID,
                                                                            inCustomerRecordId ) );
            inCapDto.setDocumentDetailsPojo( CRMServiceUtils.getDBValues( CrmDocumentDetailsPojo.class,
                                                                          IAppConstants.PARAM_CUSTOMER_RECORD_ID,
                                                                          inCustomerRecordId ) );
            inCapDto.setNationalityDetailsPojo( CRMServiceUtils.getDBValues( CrmNationalityDetailsPojo.class,
                                                                             IAppConstants.PARAM_CUSTOMER_RECORD_ID,
                                                                             inCustomerRecordId ) );
            Map<String, Object> criteriaMap = new HashMap<String, Object>();
            criteriaMap.put( "installationStatus", CRMDisplayListConstants.PREINSTALLATION.getCode() );
            List<String> entityType = new ArrayList<String>();
            entityType.add( CRMDisplayListConstants.TELESERVICES.getCode() );
            entityType.add( CRMDisplayListConstants.TELESOLUTIONS.getCode() );
            criteriaMap.put( "entityType", entityType );
            criteriaMap.put( IAppConstants.PARAM_CUSTOMER_RECORD_ID, inCustomerRecordId );
            inCapDto.setPaymentDetailsPojosList( CRMServiceUtils.getDBValueList( CrmPaymentDetailsPojo.class,
                                                                                 criteriaMap ) );
            inCapDto.setOrderDetailsPojo( CRMServiceUtils.getDBValues( CrmOrderDetailsPojo.class,
                                                                       IAppConstants.PARAM_CUSTOMER_RECORD_ID,
                                                                       inCustomerRecordId ) );
            inCapDto.setNetworkConfigurationsPojo( CRMServiceUtils.getDBValues( CrmNetworkConfigurationsPojo.class,
                                                                                IAppConstants.PARAM_CUSTOMER_RECORD_ID,
                                                                                inCustomerRecordId ) );
            inCapDto.setCrmParamDataPojos( CRMServiceUtils.getDBValueList( CrmParamDataPojo.class,
                                                                           IAppConstants.PARAM_MAPPING_ID,
                                                                           inCustomerRecordId + "" ) );
            inCapDto.setAadharNumberPojo( CRMServiceUtils.getDBValues( CrmCustAadharNumberPojo.class,
                                                                       IAppConstants.PARAM_MAPPING_ID,
                                                                       inCustomerRecordId + "" ) );
            if ( !StringUtils.equals( CRMDisplayListConstants.BROADBAND.getCode(), inCapDto.getCustomerDetailsPojo()
                    .getProduct() ) )
            {
                if ( StringUtils.isValidObj( inCapDto.getNetworkConfigurationsPojo() ) )
                {
                    inCapDto.setCrmPartnerNetworkConfig( (CrmPartnerNetworkConfigPojo) session
                            .get( CrmPartnerNetworkConfigPojo.class,
                                  StringUtils.numericValue( inCapDto.getNetworkConfigurationsPojo().getOption82() ) ) );
                }
            }
            if ( toDisplay )
            {
                inCapDto.setAppointmentPojo( CRMServiceUtils.getDBValues( AppointmentPojo.class,
                                                                          IAppConstants.PARAM_MAPPING_ID,
                                                                          inCustomerRecordId + "" ) );
                inCapDto.setRemarksPojos( CRMServiceUtils.getDBValueList( RemarksPojo.class,
                                                                          IAppConstants.PARAM_MAPPING_ID,
                                                                          inCustomerRecordId + "", "createdTime",
                                                                          false, session ) );
            }
            if ( StringUtils.isValidObj( inCapDto.getCustomerDetailsPojo() )
                    && StringUtils.isNotBlank( inCapDto.getCustomerDetailsPojo().getSalesRepresentative() ) )
            {
                CrmUserPojo salesUser = CRMServiceUtils.getDBValues( CrmUserPojo.class, "userId", inCapDto
                        .getCustomerDetailsPojo().getSalesRepresentative() );
                if ( StringUtils.isValidObj( salesUser ) )
                {
                    inCapDto.getCustomerDetailsPojo().setSalesUser( salesUser );
                }
            }
        }
        catch ( Exception ex )
        {
            LOGGER.error( "Error In Get Caf Details Method ", ex );
        }
        finally
        {
            CRMServiceUtils.closeSession( session );
        }
        return inCapDto;
    }

    @Override
    public CrmCapDto saveCustomerInformation( CrmCapDto inCapDto, Session inSession, Map<String, Long> inEvicts )
    {
        LOGGER.info( " :: Inside saveCustomerInformation Method :: " );
        CRMServiceCode statusCode = CRMServiceCode.CRM999;
        long customerRecordId = 0;
        String userId = inCapDto.getUserId();
        try
        {
            if ( StringUtils.isValidObj( inCapDto.getCustomerDetailsPojo() ) )
            {
                customerRecordId = saveCustomerDetails( inCapDto, inSession, inEvicts );
            }
            else
            {
                customerRecordId = inCapDto.getCustomerRecordId();
            }
            if ( customerRecordId <= 0 )
            {
                throw new IllegalAccessException( "Customer Record Id is not valid." );
            }
            //save or update additional details
            saveAdditionalDetails( inSession, customerRecordId, userId, inCapDto.getAdditionalDetailsPojo(), inEvicts );
            //save or update document details
            saveDocumentDetails( inSession, customerRecordId, userId, inCapDto.getDocumentDetailsPojo(), inEvicts );
            //save or update nationality details
            saveNationalityDetails( inSession, customerRecordId, userId, inCapDto.getNationalityDetailsPojo(), inEvicts );
            //save or update network details
            //           saveNetworkDetails( inSession, customerRecordId, userId, inCapDto.getNetworkDetailsPojo() );
            //save or update Order details
            saveOrderDetails( inSession, customerRecordId, userId, inCapDto.getOrderDetailsPojo(), inEvicts );
            //save or update Plan details
            savePlanDetails( inSession, customerRecordId, userId, inCapDto.getPlanDetailsPojo(), inEvicts );
            // create and update address list
            List<CrmAddressDetailsPojo> addressDetails = inCapDto.getAddressDetailsPojosList();
            if ( CommonValidator.isValidCollection( addressDetails ) )
            {
                LOGGER.info( "Size of Address Pojos ::" + addressDetails.size() );
                for ( CrmAddressDetailsPojo crmAddressDetailsPojo : addressDetails )
                {
                    saveAddressDetails( inSession, customerRecordId, userId, crmAddressDetailsPojo );
                }
                inEvicts.put( CrmAddressDetailsPojo.class.getName(), null );
            }
            // create and update payment list
            List<CrmPaymentDetailsPojo> paymentDetails = inCapDto.getPaymentDetailsPojosList();
            if ( CommonValidator.isValidCollection( paymentDetails ) )
            {
                LOGGER.info( "Size of Payment Pojos ::" + paymentDetails.size() );
                boolean receiptCheck = true;
                LOGGER.info( "Aadhar no: " + inCapDto.getAadharNumberPojo().getAadharNumber() );
                if ( StringUtils.isNotEmpty( ( inCapDto.getAadharNumberPojo().getAadharNumber() ) ) )
                {
                    receiptCheck = false;
                }
                for ( CrmPaymentDetailsPojo crmPaymentDetailsPojo : paymentDetails )
                {
                    savePaymentDetails( inSession, customerRecordId, userId, crmPaymentDetailsPojo, inEvicts,
                                        receiptCheck );
                }
                inEvicts.put( CrmPaymentDetailsPojo.class.getName(), null );
            }
            //save modify Appointment
            saveAppointments( inSession, customerRecordId, userId, inCapDto.getAppointmentPojo(), inEvicts );
            //save modify Remarks
            inCapDto.setCustomerRecordId( customerRecordId );
            //save modify crm param data
            statusCode = CRMServiceCode.CRM001;
        }
        catch ( DuplicateRecieptException ex )
        {
            LOGGER.error( "DuplicateRecieptException for Receipt Number:: " + ex.getReceiptNo() );
            statusCode = CRMServiceCode.valueOf( ex.getMessage() );
            inCapDto.setBillingErrorCode( ex.getReceiptNo() );
        }
        catch ( Exception ex )
        {
            LOGGER.error( "Error In saveCustomerInformation Method :: ", ex );
            statusCode = CRMServiceCode.CRM999;
        }
        finally
        {
            inCapDto.setStatusCode( statusCode.getStatusCode() );
            inCapDto.setStatusDesc( statusCode.getStatusDesc() );
        }
        return inCapDto;
    }

    private void saveParamDetails( Session inSession,
                                   long customerRecordId,
                                   String userId,
                                   CrmParamDataPojo crmParamDataPojo )
        throws IllegalAccessException, InvocationTargetException, NoSuchMethodException
    {
        if ( crmParamDataPojo.getRecordId() > 0 )
        {
            CrmParamDataPojo crmParamDataPojoDB = (CrmParamDataPojo) inSession.get( CrmParamDataPojo.class,
                                                                                    crmParamDataPojo.getRecordId() );
            crmParamDataPojo.setCreatedBy( crmParamDataPojoDB.getCreatedBy() );
            crmParamDataPojo.setCreatedTime( crmParamDataPojoDB.getCreatedTime() );
            crmParamDataPojo.setLastModifiedTime( Calendar.getInstance().getTime() );
            crmParamDataPojo.setLastModifiedBy( userId );
            crmParamDataPojo.setMappingId( String.valueOf( customerRecordId ) );
            org.apache.commons.beanutils.PropertyUtils.copyProperties( crmParamDataPojoDB, crmParamDataPojo );
            inSession.update( crmParamDataPojoDB );
            LOGGER.info( "Successfully Update crm param data Pojo For Id :: " + crmParamDataPojo.getRecordId() );
        }
        else
        {
            PojoUtils<CrmParamDataPojo> pojoUtils = new PojoUtils<CrmParamDataPojo>();
            if ( !pojoUtils.checkPojoEmpty( crmParamDataPojo ) )
            {
                crmParamDataPojo.setCreatedBy( userId );
                crmParamDataPojo.setCreatedTime( Calendar.getInstance().getTime() );
                crmParamDataPojo.setMappingId( String.valueOf( customerRecordId ) );
                Long generatedId = (Long) inSession.save( crmParamDataPojo );
                LOGGER.info( "Successfully Save Crm Param Data Pojo. Generated Id :: " + generatedId );
                crmParamDataPojo.setRecordId( generatedId );
            }
        }
    }

    private void saveNetworkConfigurations( Session inSession,
                                            long customerRecordId,
                                            String userId,
                                            CrmNetworkConfigurationsPojo crmNetworkConfigurationsPojo,
                                            Map<String, Long> inEvicts )
        throws IllegalAccessException, InvocationTargetException, NoSuchMethodException
    {
        if ( StringUtils.isValidObj( crmNetworkConfigurationsPojo ) )
        {
            if ( crmNetworkConfigurationsPojo.getRecordId() > 0 )
            {
                CrmNetworkConfigurationsPojo crmNetworkConfigurationsPojoDB = (CrmNetworkConfigurationsPojo) inSession
                        .get( CrmNetworkConfigurationsPojo.class, crmNetworkConfigurationsPojo.getRecordId() );
                //                crmNetworkConfigurationsPojo.setCreatedBy( crmNetworkConfigurationsPojoDB.getCreatedBy() );
                //                crmNetworkConfigurationsPojo.setCreatedTime( crmNetworkConfigurationsPojoDB.getCreatedTime() );
                crmNetworkConfigurationsPojo.setLastModifiedTime( Calendar.getInstance().getTime() );
                crmNetworkConfigurationsPojo.setLastModifiedBy( userId );
                crmNetworkConfigurationsPojo.setCustomerRecordId( customerRecordId );
                org.apache.commons.beanutils.PropertyUtils.copyProperties( crmNetworkConfigurationsPojoDB,
                                                                           crmNetworkConfigurationsPojo );
                inSession.update( crmNetworkConfigurationsPojoDB );
                inEvicts.put( CrmNetworkConfigurationsPojo.class.getName(),
                              crmNetworkConfigurationsPojoDB.getRecordId() );
                LOGGER.info( "Network Configurations Updated Successfully Id :: "
                        + crmNetworkConfigurationsPojo.getRecordId() );
            }
            else
            {
                PojoUtils<CrmNetworkConfigurationsPojo> pojoUtils = new PojoUtils<CrmNetworkConfigurationsPojo>();
                if ( !pojoUtils.checkPojoEmpty( crmNetworkConfigurationsPojo ) )
                {
                    CrmNetworkConfigurationsPojo crmNetworkConfigurationsPojoDB = CRMServiceUtils
                            .getDBValues( CrmNetworkConfigurationsPojo.class, "customerRecordId", customerRecordId );
                    if ( StringUtils.isValidObj( crmNetworkConfigurationsPojoDB ) )
                    {
                        crmNetworkConfigurationsPojo.setLastModifiedTime( Calendar.getInstance().getTime() );
                        crmNetworkConfigurationsPojo.setLastModifiedBy( userId );
                        crmNetworkConfigurationsPojo.setCustomerRecordId( customerRecordId );
                        crmNetworkConfigurationsPojo.setRecordId( crmNetworkConfigurationsPojoDB.getRecordId() );
                        org.apache.commons.beanutils.PropertyUtils.copyProperties( crmNetworkConfigurationsPojoDB,
                                                                                   crmNetworkConfigurationsPojo );
                        inSession.update( crmNetworkConfigurationsPojoDB );
                        inEvicts.put( CrmNetworkConfigurationsPojo.class.getName(),
                                      crmNetworkConfigurationsPojoDB.getRecordId() );
                        LOGGER.info( "Network Configurations Updated Successfully Id :: "
                                + crmNetworkConfigurationsPojo.getRecordId() );
                    }
                    else
                    {
                        crmNetworkConfigurationsPojo.setCustomerRecordId( customerRecordId );
                        crmNetworkConfigurationsPojo.setCreatedBy( userId );
                        crmNetworkConfigurationsPojo.setCreatedTime( Calendar.getInstance().getTime() );
                        Long generatedId = (Long) inSession.save( crmNetworkConfigurationsPojo );
                        inEvicts.put( CrmNetworkConfigurationsPojo.class.getName(), null );
                        LOGGER.info( "Successfully Save Network Configuration Pojo. Generated Id :: " + generatedId );
                        crmNetworkConfigurationsPojo.setRecordId( generatedId );
                    }
                }
            }
        }
    }

    private void saveAppointments( Session inSession,
                                   long customerRecordId,
                                   String userId,
                                   AppointmentPojo appointmentPojo,
                                   Map<String, Long> inEvicts )
        throws IllegalAccessException, InvocationTargetException, NoSuchMethodException
    {
        if ( StringUtils.isValidObj( appointmentPojo ) )
        {
            if ( appointmentPojo.getAppointmentId() > 0 )
            {
                AppointmentPojo appointmentPojoDB = (AppointmentPojo) inSession
                        .get( AppointmentPojo.class, appointmentPojo.getAppointmentId() );
                appointmentPojo.setCreatedBy( appointmentPojoDB.getCreatedBy() );
                appointmentPojo.setCreatedTime( appointmentPojoDB.getCreatedTime() );
                appointmentPojo.setMappingId( Long.toString( customerRecordId ) );
                appointmentPojo.setModeOfContact( "F" );
                appointmentPojo.setAppointmentDate( Calendar.getInstance().getTime() );
                org.apache.commons.beanutils.PropertyUtils.copyProperties( appointmentPojoDB, appointmentPojo );
                inSession.update( appointmentPojoDB );
                inEvicts.put( AppointmentPojo.class.getName(), appointmentPojo.getAppointmentId() );
                LOGGER.info( "Successfully Update Appointment Pojo For Id :: " + appointmentPojo.getAppointmentId() );
            }
            else
            {
                PojoUtils<AppointmentPojo> pojoUtils = new PojoUtils<AppointmentPojo>();
                if ( !pojoUtils.checkPojoEmpty( appointmentPojo ) )
                {
                    appointmentPojo.setCreatedBy( userId );
                    appointmentPojo.setCreatedTime( Calendar.getInstance().getTime() );
                    appointmentPojo.setModeOfContact( "F" );
                    appointmentPojo.setAppointmentDate( Calendar.getInstance().getTime() );
                    appointmentPojo.setMappingId( Long.toString( customerRecordId ) );
                    Long generatedId = (Long) inSession.save( appointmentPojo );
                    inEvicts.put( AppointmentPojo.class.getName(), null );
                    LOGGER.info( "Successfully Save Appointment Pojo. Generated Id :: " + generatedId );
                    appointmentPojo.setAppointmentId( generatedId );
                }
            }
        }
    }

    private void savePaymentDetails( Session inSession,
                                     long customerRecordId,
                                     String userId,
                                     CrmPaymentDetailsPojo crmPaymentDetailsPojo,
                                     Map<String, Long> inEvicts,
                                     boolean receiptCheck )
        throws IllegalAccessException, InvocationTargetException, NoSuchMethodException, DuplicateRecieptException
    {
        if ( StringUtils.equals( CRMDisplayListConstants.CASH.getCode(), crmPaymentDetailsPojo.getPaymentMode() )
                && receiptCheck )
        {
            CrmPaymentDetailsPojo dbCrmPaymentDetailsPojo = CRMServiceUtils.getDBValues( CrmPaymentDetailsPojo.class,
                                                                                         "receiptNo",
                                                                                         crmPaymentDetailsPojo
                                                                                                 .getReceiptNo(),
                                                                                         inSession );
            if ( StringUtils.isValidObj( dbCrmPaymentDetailsPojo ) )
            {
                if ( dbCrmPaymentDetailsPojo.getCustomerRecordId() != customerRecordId )
                {
                    throw new DuplicateRecieptException( CRMServiceCode.CRM611.getStatusCode(),
                                                         crmPaymentDetailsPojo.getReceiptNo() );
                }
                else if ( StringUtils.equals( CRMDisplayListConstants.SECURITY_DEPOSIT.getCode(),
                                              crmPaymentDetailsPojo.getPaymentChannel() )
                        || StringUtils.equals( CRMDisplayListConstants.SECURITY_DEPOSIT.getCode(),
                                               dbCrmPaymentDetailsPojo.getPaymentChannel() ) )
                {
                    throw new DuplicateRecieptException( CRMServiceCode.CRM611.getStatusCode(),
                                                         crmPaymentDetailsPojo.getReceiptNo() );
                }
            }
        }
        if ( crmPaymentDetailsPojo.getPaymentId() > 0 )
        {
            CrmPaymentDetailsPojo crmPaymentDetailsPojoDB = (CrmPaymentDetailsPojo) inSession
                    .get( CrmPaymentDetailsPojo.class, crmPaymentDetailsPojo.getPaymentId() );
            if ( StringUtils.equals( CRMDisplayListConstants.CASH.getCode(), crmPaymentDetailsPojoDB.getPaymentMode() )
                    && !StringUtils.equals( CRMDisplayListConstants.CASH.getCode(),
                                            crmPaymentDetailsPojo.getPaymentMode() ) && receiptCheck )
            {
                CrmDaoHelper.setRecieptStatus( inSession, crmPaymentDetailsPojo, false );
            }
            crmPaymentDetailsPojo.setLastModifiedTime( Calendar.getInstance().getTime() );
            crmPaymentDetailsPojo.setLastModifiedBy( userId );
            crmPaymentDetailsPojo.setCustomerRecordId( customerRecordId );
            org.apache.commons.beanutils.PropertyUtils.copyProperties( crmPaymentDetailsPojoDB, crmPaymentDetailsPojo );
            CrmDaoHelper.setPaymentDetails( crmPaymentDetailsPojoDB );
            inSession.update( crmPaymentDetailsPojoDB );
            if ( StringUtils.equals( CRMDisplayListConstants.CASH.getCode(), crmPaymentDetailsPojoDB.getPaymentMode() )
                    && receiptCheck )
            {
                CrmDaoHelper.setRecieptStatus( inSession, crmPaymentDetailsPojoDB, true );
            }
            LOGGER.info( "Successfully Update Payment Detail Pojo For Id :: " + crmPaymentDetailsPojo.getPaymentId() );
            inEvicts.put( CrmPaymentDetailsPojo.class.getName(), crmPaymentDetailsPojo.getPaymentId() );
        }
        else
        {
            PojoUtils<CrmPaymentDetailsPojo> pojoUtils = new PojoUtils<CrmPaymentDetailsPojo>();
            if ( !pojoUtils.checkPojoEmpty( crmPaymentDetailsPojo )
                    && StringUtils.isValidObj( crmPaymentDetailsPojo.getAmount() )
                    && crmPaymentDetailsPojo.getAmount() > 0 )
            {
                //                Map<String,Object> criteriaMap = new HashMap<String, Object>();
                //                criteriaMap.put( "customerRecordId", crmPaymentDetailsPojo.getCustomerRecordId() );
                //                criteriaMap.put( "customerRecordId", crmPaymentDetailsPojo.getCustomerRecordId() );
                //                CrmPaymentDetailsPojo crmPaymentDetailsPojoDB = (CrmPaymentDetailsPojo) inSession
                //                        .get( CrmPaymentDetailsPojo.class, crmPaymentDetailsPojo.getPaymentId() );
                //                
                //                if ( StringUtils.isValidObj( crmPaymentDetailsPojoDB ) )
                //                {
                //                }
                //                else
                //                {
                CrmDaoHelper.setPaymentDetails( crmPaymentDetailsPojo );
                crmPaymentDetailsPojo.setCreatedBy( userId );
                crmPaymentDetailsPojo.setCreatedTime( Calendar.getInstance().getTime() );
                crmPaymentDetailsPojo.setCustomerRecordId( customerRecordId );
                Long generatedId = (Long) inSession.save( crmPaymentDetailsPojo );
                LOGGER.info( "Successfully Save Payment Details Pojo. Generated Id :: " + generatedId );
                crmPaymentDetailsPojo.setPaymentId( generatedId );
                if ( generatedId > 0
                        && StringUtils.equals( CRMDisplayListConstants.CASH.getCode(),
                                               crmPaymentDetailsPojo.getPaymentMode() ) && receiptCheck )
                {
                    CrmDaoHelper.setRecieptStatus( inSession, crmPaymentDetailsPojo, true );
                }
                inEvicts.put( CrmPaymentDetailsPojo.class.getName(), null );
                //                }
            }
        }
    }

    private void savePlanDetails( Session inSession,
                                  long customerRecordId,
                                  String userId,
                                  CrmPlanDetailsPojo crmPlanDetailsPojo,
                                  Map<String, Long> inEvicts )
        throws IllegalAccessException, InvocationTargetException, NoSuchMethodException
    {
        Criteria criteria = null;
        if ( StringUtils.isValidObj( crmPlanDetailsPojo ) )
        {
            LOGGER.info( "ID ::" + crmPlanDetailsPojo.getRecordId() + " :: plan Name :: "
                    + crmPlanDetailsPojo.getBasePlanCode() );
            if ( crmPlanDetailsPojo.getRecordId() > 0 )
            {
                CrmPlanDetailsPojo crmPlanDetailsPojoDB = (CrmPlanDetailsPojo) inSession.get( CrmPlanDetailsPojo.class,
                                                                                              crmPlanDetailsPojo
                                                                                                      .getRecordId() );
                crmPlanDetailsPojo.setCreatedBy( crmPlanDetailsPojoDB.getCreatedBy() );
                crmPlanDetailsPojo.setCreatedTime( crmPlanDetailsPojoDB.getCreatedTime() );
                crmPlanDetailsPojo.setLastModifiedTime( Calendar.getInstance().getTime() );
                crmPlanDetailsPojo.setLastModifiedBy( userId );
                CrmCustomerDetailsPojo crmCustomerDetailsPojoForPlan = new CrmCustomerDetailsPojo();
                crmCustomerDetailsPojoForPlan.setRecordId( customerRecordId );
                crmPlanDetailsPojo.setCrmCustomerDetails( crmCustomerDetailsPojoForPlan );
                org.apache.commons.beanutils.PropertyUtils.copyProperties( crmPlanDetailsPojoDB, crmPlanDetailsPojo );
                inSession.update( crmPlanDetailsPojoDB );
                inEvicts.put( CrmPlanDetailsPojo.class.getName(), crmPlanDetailsPojo.getRecordId() );
                LOGGER.info( "Successfully Update Plan Detail Pojo For Id :: " + crmPlanDetailsPojo.getRecordId() );
            }
            else
            {
                PojoUtils<CrmPlanDetailsPojo> pojoUtils = new PojoUtils<CrmPlanDetailsPojo>();
                if ( !pojoUtils.checkPojoEmpty( crmPlanDetailsPojo ) )
                {
                    criteria = inSession.createCriteria( CrmPlanDetailsPojo.class );
                    criteria.createCriteria( "crmCustomerDetails", "cust" );
                    criteria.add( Restrictions.eq( "cust.recordId", customerRecordId ) );
                    List<CrmPlanDetailsPojo> plan = criteria.list();
                    if ( CommonValidator.isValidCollection( plan ) )
                    {
                        CrmPlanDetailsPojo crmPlanDetailsPojoDB = (CrmPlanDetailsPojo) plan.get( 0 );
                        crmPlanDetailsPojo.setCreatedBy( crmPlanDetailsPojoDB.getCreatedBy() );
                        crmPlanDetailsPojo.setCreatedTime( crmPlanDetailsPojoDB.getCreatedTime() );
                        crmPlanDetailsPojo.setLastModifiedTime( Calendar.getInstance().getTime() );
                        crmPlanDetailsPojo.setLastModifiedBy( userId );
                        CrmCustomerDetailsPojo crmCustomerDetailsPojoForPlan = new CrmCustomerDetailsPojo();
                        crmCustomerDetailsPojoForPlan.setRecordId( customerRecordId );
                        crmPlanDetailsPojo.setCrmCustomerDetails( crmCustomerDetailsPojoForPlan );
                        org.apache.commons.beanutils.PropertyUtils.copyProperties( crmPlanDetailsPojoDB,
                                                                                   crmPlanDetailsPojo );
                        inSession.update( crmPlanDetailsPojoDB );
                        inEvicts.put( CrmPlanDetailsPojo.class.getName(), crmPlanDetailsPojo.getRecordId() );
                        LOGGER.info( "Successfully Update Plan Detail Pojo For Id :: "
                                + crmPlanDetailsPojo.getRecordId() );
                    }
                    else
                    {
                        crmPlanDetailsPojo.setCreatedBy( userId );
                        crmPlanDetailsPojo.setCreatedTime( Calendar.getInstance().getTime() );
                        CrmCustomerDetailsPojo crmCustomerDetailsPojoForPlan = new CrmCustomerDetailsPojo();
                        crmCustomerDetailsPojoForPlan.setRecordId( customerRecordId );
                        crmPlanDetailsPojo.setCrmCustomerDetails( crmCustomerDetailsPojoForPlan );
                        Long generatedId = (Long) inSession.save( crmPlanDetailsPojo );
                        inEvicts.put( CrmPlanDetailsPojo.class.getName(), null );
                        LOGGER.info( "Successfully Save Plan Details Pojo. Generated Id :: " + generatedId );
                    }
                }
            }
        }
    }

    private void saveAddressDetails( Session inSession,
                                     long customerRecordId,
                                     String userId,
                                     CrmAddressDetailsPojo crmAddressDetailsPojo )
        throws IllegalAccessException, InvocationTargetException, NoSuchMethodException
    {
        crmAddressDetailsPojo.setAddLine1( StringUtils.upperCase( crmAddressDetailsPojo.getAddLine1() ) );
        crmAddressDetailsPojo.setAddLine2( StringUtils.upperCase( crmAddressDetailsPojo.getAddLine2() ) );
        crmAddressDetailsPojo.setAddLine3( StringUtils.upperCase( crmAddressDetailsPojo.getAddLine3() ) );
        crmAddressDetailsPojo.setLandmark( StringUtils.upperCase( crmAddressDetailsPojo.getLandmark() ) );
        if ( crmAddressDetailsPojo.getRecordId() > 0 )
        {
            CrmAddressDetailsPojo crmAddressDetailsPojoDB = (CrmAddressDetailsPojo) inSession
                    .get( CrmAddressDetailsPojo.class, crmAddressDetailsPojo.getRecordId() );
            crmAddressDetailsPojo.setCreatedBy( crmAddressDetailsPojoDB.getCreatedBy() );
            crmAddressDetailsPojo.setCreatedTime( crmAddressDetailsPojoDB.getCreatedTime() );
            crmAddressDetailsPojo.setLastModifiedTime( Calendar.getInstance().getTime() );
            crmAddressDetailsPojo.setLastModifiedBy( userId );
            CrmCustomerDetailsPojo crmCustomerDetailsPojoForAdd = new CrmCustomerDetailsPojo();
            crmCustomerDetailsPojoForAdd.setRecordId( customerRecordId );
            crmAddressDetailsPojo.setCrmCustomerDetails( crmCustomerDetailsPojoForAdd );
            org.apache.commons.beanutils.PropertyUtils.copyProperties( crmAddressDetailsPojoDB, crmAddressDetailsPojo );
            inSession.update( crmAddressDetailsPojoDB );
            LOGGER.info( "Successfully Update Address Detail Pojo For Id :: " + crmAddressDetailsPojo.getRecordId() );
        }
        else
        {
            PojoUtils<CrmAddressDetailsPojo> pojoUtils = new PojoUtils<CrmAddressDetailsPojo>();
            if ( !pojoUtils.checkPojoEmpty( crmAddressDetailsPojo ) )
            {
                crmAddressDetailsPojo.setCreatedBy( userId );
                crmAddressDetailsPojo.setCreatedTime( Calendar.getInstance().getTime() );
                CrmCustomerDetailsPojo crmCustomerDetailsPojoForAdd = new CrmCustomerDetailsPojo();
                crmCustomerDetailsPojoForAdd.setRecordId( customerRecordId );
                crmAddressDetailsPojo.setCrmCustomerDetails( crmCustomerDetailsPojoForAdd );
                Long generatedId = (Long) inSession.save( crmAddressDetailsPojo );
                LOGGER.info( "Successfully Save Address Detail  Pojo. Generated Id :: " + generatedId );
            }
        }
    }

    private void saveOrderDetails( Session inSession,
                                   long customerRecordId,
                                   String userId,
                                   CrmOrderDetailsPojo crmOrderDetailsPojo,
                                   Map<String, Long> inEvicts )
        throws IllegalAccessException, InvocationTargetException, NoSuchMethodException
    {
        if ( StringUtils.isValidObj( crmOrderDetailsPojo ) )
        {
            if ( crmOrderDetailsPojo.getRecordId() > 0 )
            {
                CrmOrderDetailsPojo crmOrderDetailsPojoDB = (CrmOrderDetailsPojo) inSession
                        .get( CrmOrderDetailsPojo.class, crmOrderDetailsPojo.getRecordId() );
                crmOrderDetailsPojo.setCreatedBy( crmOrderDetailsPojoDB.getCreatedBy() );
                crmOrderDetailsPojo.setCreatedTime( crmOrderDetailsPojoDB.getCreatedTime() );
                crmOrderDetailsPojo.setLastModifiedTime( Calendar.getInstance().getTime() );
                crmOrderDetailsPojo.setLastModifiedBy( userId );
                crmOrderDetailsPojo.setCustomerRecordId( customerRecordId );
                org.apache.commons.beanutils.PropertyUtils.copyProperties( crmOrderDetailsPojoDB, crmOrderDetailsPojo );
                inSession.update( crmOrderDetailsPojoDB );
                inEvicts.put( CrmOrderDetailsPojo.class.getName(), crmOrderDetailsPojo.getRecordId() );
                LOGGER.info( "Successfully Update OrderDetails Pojo For Id :: " + crmOrderDetailsPojo.getRecordId() );
            }
            else
            {
                PojoUtils<CrmOrderDetailsPojo> pojoUtils = new PojoUtils<CrmOrderDetailsPojo>();
                if ( !pojoUtils.checkPojoEmpty( crmOrderDetailsPojo ) )
                {
                    crmOrderDetailsPojo.setCreatedBy( userId );
                    crmOrderDetailsPojo.setCreatedTime( Calendar.getInstance().getTime() );
                    crmOrderDetailsPojo.setCustomerRecordId( customerRecordId );
                    Long generatedId = (Long) inSession.save( crmOrderDetailsPojo );
                    inEvicts.put( CrmOrderDetailsPojo.class.getName(), null );
                    LOGGER.info( "Successfully Save OrderDetails Pojo. Generated Id :: " + generatedId );
                }
            }
        }
    }

    private void saveNationalityDetails( Session inSession,
                                         long customerRecordId,
                                         String userId,
                                         CrmNationalityDetailsPojo crmNationalityDetailsPojo,
                                         Map<String, Long> inEvicts )
        throws IllegalAccessException, InvocationTargetException, NoSuchMethodException
    {
        if ( StringUtils.isValidObj( crmNationalityDetailsPojo ) )
        {
            crmNationalityDetailsPojo.setLocalCpFname( StringUtils.upperCase( crmNationalityDetailsPojo
                    .getLocalCpFname() ) );
            crmNationalityDetailsPojo.setLocalCpLname( StringUtils.upperCase( crmNationalityDetailsPojo
                    .getLocalCpLname() ) );
            crmNationalityDetailsPojo
                    .setLocalCpAdd( StringUtils.upperCase( crmNationalityDetailsPojo.getLocalCpAdd() ) );
            crmNationalityDetailsPojo.setLocalCpLandmark( StringUtils.upperCase( crmNationalityDetailsPojo
                    .getLocalCpLandmark() ) );
            if ( crmNationalityDetailsPojo.getRecordId() > 0 )
            {
                CrmNationalityDetailsPojo crmNationalityDetailsPojoDB = (CrmNationalityDetailsPojo) inSession
                        .get( CrmNationalityDetailsPojo.class, crmNationalityDetailsPojo.getRecordId() );
                crmNationalityDetailsPojo.setLastModifiedTime( Calendar.getInstance().getTime() );
                crmNationalityDetailsPojo.setLastModifiedBy( userId );
                crmNationalityDetailsPojo.setCustomerRecordId( customerRecordId );
                org.apache.commons.beanutils.PropertyUtils.copyProperties( crmNationalityDetailsPojoDB,
                                                                           crmNationalityDetailsPojo );
                inSession.update( crmNationalityDetailsPojoDB );
                inEvicts.put( CrmNationalityDetailsPojo.class.getName(), crmNationalityDetailsPojo.getRecordId() );
                LOGGER.info( "Successfully Update Nationality Pojo For Id :: "
                        + crmNationalityDetailsPojo.getRecordId() );
            }
            else
            {
                PojoUtils<CrmNationalityDetailsPojo> pojoUtils = new PojoUtils<CrmNationalityDetailsPojo>();
                if ( !pojoUtils.checkPojoEmpty( crmNationalityDetailsPojo ) )
                {
                    CrmNationalityDetailsPojo crmNationalityDetailsPojoDB = CRMServiceUtils
                            .getDBValues( CrmNationalityDetailsPojo.class, "customerRecordId", customerRecordId );
                    if ( StringUtils.isValidObj( crmNationalityDetailsPojoDB ) )
                    {
                        crmNationalityDetailsPojo.setRecordId( crmNationalityDetailsPojoDB.getRecordId() );
                        crmNationalityDetailsPojo.setLastModifiedTime( Calendar.getInstance().getTime() );
                        crmNationalityDetailsPojo.setLastModifiedBy( userId );
                        crmNationalityDetailsPojo.setCustomerRecordId( customerRecordId );
                        org.apache.commons.beanutils.PropertyUtils.copyProperties( crmNationalityDetailsPojoDB,
                                                                                   crmNationalityDetailsPojo );
                        inSession.update( crmNationalityDetailsPojoDB );
                        inEvicts.put( CrmNationalityDetailsPojo.class.getName(),
                                      crmNationalityDetailsPojo.getRecordId() );
                        LOGGER.info( "Successfully Update Nationality Pojo For Id :: "
                                + crmNationalityDetailsPojo.getRecordId() );
                    }
                    else
                    {
                        crmNationalityDetailsPojo.setCreatedBy( userId );
                        crmNationalityDetailsPojo.setCreatedTime( Calendar.getInstance().getTime() );
                        crmNationalityDetailsPojo.setCustomerRecordId( customerRecordId );
                        Long generatedId = (Long) inSession.save( crmNationalityDetailsPojo );
                        inEvicts.put( CrmNationalityDetailsPojo.class.getName(), null );
                        LOGGER.info( "Successfully Save Nationality Detail. Generated Id :: " + generatedId );
                    }
                }
            }
        }
    }

    private void saveDocumentDetails( Session inSession,
                                      long customerRecordId,
                                      String userId,
                                      CrmDocumentDetailsPojo crmDocumentDetailsPojo,
                                      Map<String, Long> inEvicts )
        throws IllegalAccessException, InvocationTargetException, NoSuchMethodException
    {
        if ( StringUtils.isValidObj( crmDocumentDetailsPojo ) )
        {
            if ( crmDocumentDetailsPojo.getRecordId() > 0 )
            {
                CrmDocumentDetailsPojo crmDocumentDetailsPojoDB = (CrmDocumentDetailsPojo) inSession
                        .get( CrmDocumentDetailsPojo.class, crmDocumentDetailsPojo.getRecordId() );
                crmDocumentDetailsPojo.setLastModifiedTime( Calendar.getInstance().getTime() );
                crmDocumentDetailsPojo.setLastModifiedBy( userId );
                crmDocumentDetailsPojo.setCustomerRecordId( customerRecordId );
                org.apache.commons.beanutils.PropertyUtils.copyProperties( crmDocumentDetailsPojoDB,
                                                                           crmDocumentDetailsPojo );
                inSession.update( crmDocumentDetailsPojoDB );
                inEvicts.put( CrmDocumentDetailsPojo.class.getName(), crmDocumentDetailsPojo.getRecordId() );
                LOGGER.info( "Successfully Update Document Pojo For Id :: " + crmDocumentDetailsPojo.getRecordId() );
            }
            else
            {
                PojoUtils<CrmDocumentDetailsPojo> pojoUtils = new PojoUtils<CrmDocumentDetailsPojo>();
                if ( !pojoUtils.checkPojoEmpty( crmDocumentDetailsPojo ) )
                {
                    CrmDocumentDetailsPojo crmDocumentDetailsPojoDB = CRMServiceUtils
                            .getDBValues( CrmDocumentDetailsPojo.class, "customerRecordId", customerRecordId );
                    if ( StringUtils.isValidObj( crmDocumentDetailsPojoDB ) )
                    {
                        crmDocumentDetailsPojo.setRecordId( crmDocumentDetailsPojoDB.getRecordId() );
                        crmDocumentDetailsPojo.setLastModifiedTime( Calendar.getInstance().getTime() );
                        crmDocumentDetailsPojo.setLastModifiedBy( userId );
                        crmDocumentDetailsPojo.setCustomerRecordId( customerRecordId );
                        org.apache.commons.beanutils.PropertyUtils.copyProperties( crmDocumentDetailsPojoDB,
                                                                                   crmDocumentDetailsPojo );
                        inSession.update( crmDocumentDetailsPojoDB );
                        inEvicts.put( CrmDocumentDetailsPojo.class.getName(), crmDocumentDetailsPojo.getRecordId() );
                        LOGGER.info( "Successfully Update Document Pojo For Id :: "
                                + crmDocumentDetailsPojo.getRecordId() );
                    }
                    else
                    {
                        crmDocumentDetailsPojo.setCreatedBy( userId );
                        crmDocumentDetailsPojo.setCreatedTime( Calendar.getInstance().getTime() );
                        crmDocumentDetailsPojo.setCustomerRecordId( customerRecordId );
                        Long generatedId = (Long) inSession.save( crmDocumentDetailsPojo );
                        inEvicts.put( CrmDocumentDetailsPojo.class.getName(), null );
                        LOGGER.info( "Successfully Save Document Detail. Generated Id :: " + generatedId );
                    }
                }
            }
        }
    }

    private void saveAdditionalDetails( Session inSession,
                                        long customerRecordId,
                                        String userId,
                                        CrmAdditionalDetailsPojo additionalDetailsPojo,
                                        Map<String, Long> inEvicts )
        throws IllegalAccessException, InvocationTargetException, NoSuchMethodException
    {
        if ( StringUtils.isValidObj( additionalDetailsPojo ) )
        {
            if ( additionalDetailsPojo.getRecordId() > 0 )
            {
                CrmAdditionalDetailsPojo crmAdditionalDetailsPojoDB = (CrmAdditionalDetailsPojo) inSession
                        .get( CrmAdditionalDetailsPojo.class, additionalDetailsPojo.getRecordId() );
                additionalDetailsPojo.setCreatedBy( crmAdditionalDetailsPojoDB.getCreatedBy() );
                additionalDetailsPojo.setCreatedTime( crmAdditionalDetailsPojoDB.getCreatedTime() );
                additionalDetailsPojo.setLastModifiedTime( Calendar.getInstance().getTime() );
                additionalDetailsPojo.setLastModifiedBy( userId );
                additionalDetailsPojo.setCustomerRecordId( customerRecordId );
                org.apache.commons.beanutils.PropertyUtils.copyProperties( crmAdditionalDetailsPojoDB,
                                                                           additionalDetailsPojo );
                inSession.update( crmAdditionalDetailsPojoDB );
                inEvicts.put( CrmAdditionalDetailsPojo.class.getName(), additionalDetailsPojo.getRecordId() );
                LOGGER.info( "Successfully Update Additional Pojo For Id :: " + additionalDetailsPojo.getRecordId() );
            }
            else
            {
                PojoUtils<CrmAdditionalDetailsPojo> pojoUtils = new PojoUtils<CrmAdditionalDetailsPojo>();
                if ( !pojoUtils.checkPojoEmpty( additionalDetailsPojo ) )
                {
                    additionalDetailsPojo.setCreatedBy( userId );
                    additionalDetailsPojo.setCreatedTime( Calendar.getInstance().getTime() );
                    additionalDetailsPojo.setCustomerRecordId( customerRecordId );
                    Long generatedId = (Long) inSession.save( additionalDetailsPojo );
                    inEvicts.put( CrmAdditionalDetailsPojo.class.getName(), null );
                    LOGGER.info( "Successfully Save Additional Detail. Generated Id :: " + generatedId );
                    additionalDetailsPojo.setRecordId( generatedId );
                }
            }
        }
    }

    private long saveCustomerDetails( CrmCapDto inCapDto, Session inSession, Map<String, Long> inEvicts )
        throws IllegalAccessException, InvocationTargetException, NoSuchMethodException
    {
        //save or update customer detail
        CrmCustomerDetailsPojo crmCustomerDetailsPojo = inCapDto.getCustomerDetailsPojo();
        long customerRecordId = crmCustomerDetailsPojo.getRecordId();
        if ( StringUtils.equals( CRMOperationStages.FULFILLMENT_TEAM.getCode(), inCapDto.getToStage() ) )
        {
            if ( StringUtils.isBlank( crmCustomerDetailsPojo.getEmailValidationFlag() ) )
            {
                crmCustomerDetailsPojo.setEmailValidationFlag( "N" );
            }
        }
        CrmCustomerDetailsPojo customerDetailsPojoDB = null;
        if ( customerRecordId > 0 )
        {
            customerDetailsPojoDB = (CrmCustomerDetailsPojo) inSession.get( CrmCustomerDetailsPojo.class,
                                                                            customerRecordId );
        }
        else
        {
            customerDetailsPojoDB = CRMServiceUtils.getDBValues( CrmCustomerDetailsPojo.class, "crfId",
                                                                 crmCustomerDetailsPojo.getCrfId(), inSession );
        }
        if ( StringUtils.isValidObj( customerDetailsPojoDB ) )
        {
            LOGGER.info( "Customer Details Present in DB :: " + customerDetailsPojoDB.getRecordId() );
            customerRecordId = customerDetailsPojoDB.getRecordId();
            crmCustomerDetailsPojo.setCreatedBy( customerDetailsPojoDB.getCreatedBy() );
            crmCustomerDetailsPojo.setCreatedTime( customerDetailsPojoDB.getCreatedTime() );
            crmCustomerDetailsPojo.setLastModifiedTime( Calendar.getInstance().getTime() );
            crmCustomerDetailsPojo.setLastModifiedBy( inCapDto.getUserId() );
            //because of dynamic update in hbm
            org.apache.commons.beanutils.PropertyUtils.copyProperties( customerDetailsPojoDB, crmCustomerDetailsPojo );
            inSession.update( customerDetailsPojoDB );
            inEvicts.put( CrmCustomerDetailsPojo.class.getName(), customerRecordId );
            LOGGER.info( "Successfully Update Customer Details For Id :: " + customerRecordId );
        }
        else
        {
            PojoUtils<CrmCustomerDetailsPojo> pojoUtils = new PojoUtils<CrmCustomerDetailsPojo>();
            if ( !pojoUtils.checkPojoEmpty( crmCustomerDetailsPojo ) )
            {
                crmCustomerDetailsPojo.setCreatedBy( inCapDto.getUserId() );
                crmCustomerDetailsPojo.setCreatedTime( Calendar.getInstance().getTime() );
                crmCustomerDetailsPojo.setStatus( CRMStatusCode.ACTIVE.getStatusCode() );
                customerRecordId = (Long) inSession.save( crmCustomerDetailsPojo );
                inEvicts.put( CrmCustomerDetailsPojo.class.getName(), null );
                if ( customerRecordId > 0 )
                {
                    Map<String, Object> criteriaMap = new HashMap<String, Object>();
                    criteriaMap = new HashMap<String, Object>();
                    criteriaMap.put( "category", CRMRCAReason.INA.getStatusCode() );
                    criteriaMap.put( "subCategory", "CAF" );
                    criteriaMap.put( "categoryValue", crmCustomerDetailsPojo.getCrfId() );
                    CrmRcaReasonPojo masterCrf = CRMServiceUtils.getDBValue( CrmRcaReasonPojo.class, criteriaMap, null,
                                                                             false, inSession );
                    if ( StringUtils.isValidObj( masterCrf ) )
                    {
                        masterCrf.setValueAlias( CRMStatusCode.USED.getStatusCode() );
                        masterCrf.setLastModifiedBy( crmCustomerDetailsPojo.getCreatedBy() );
                        masterCrf.setLastModifiedTime( Calendar.getInstance().getTime() );
                        inSession.merge( masterCrf );
                    }
                }
                LOGGER.info( "Successfully Save Customer Details. Generated ID :: " + customerRecordId );
            }
        }
        inCapDto.setCustomerRecordId( customerRecordId );
        inCapDto.getCustomerDetailsPojo().setRecordId( customerRecordId );
        return customerRecordId;
    }

    @Override
    public CrmCapDto saveCRF( CrmCapDto inCapDto, boolean toSave )
    {
        SessionFactory sessionFactory = null;
        Session session = null;
        Transaction transaction = null;
        boolean success = false;
        Map<String, Long> evicts = new HashMap<String, Long>();
        try
        {
            LOGGER.info( ":: IN Save CAF Method ::" );
            sessionFactory = HibernateUtil.getSessionFactory();
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            long customerRecordId = 0l;
            if ( StringUtils.isValidObj( inCapDto.getCustomerDetailsPojo() ) )
            {
                if ( StringUtils.isBlank( inCapDto.getCustomerDetailsPojo().getCrfStage() ) )
                {
                    inCapDto.getCustomerDetailsPojo().setCrfStage( CRMOperationStages.INITIATE.getCode() );
                }
                customerRecordId = inCapDto.getCustomerDetailsPojo().getRecordId();
                LOGGER.info( "Customer ID:" + inCapDto.getCustomerDetailsPojo().getRecordId() );
            }
            if ( customerRecordId == 0 )
            {
                customerRecordId = inCapDto.getCustomerRecordId();
            }
            if ( StringUtils.equals( CRMOperationStages.CANCEL.getCode(), inCapDto.getToStage() ) )
            {
                success = true;
            }
            else if ( StringUtils.equals( CRMOperationStages.INITIATE.getCode(), inCapDto.getCustomerDetailsPojo()
                    .getCrfStage() )
                    || StringUtils.equals( CRMOperationStages.FT_REJECT.getCode(), inCapDto.getCustomerDetailsPojo()
                            .getCrfStage() ) )
            {
                inCapDto = saveCustomerInformation( inCapDto, session, evicts );
                if ( StringUtils.equals( inCapDto.getStatusCode(), CRMServiceCode.CRM001.getStatusCode() ) )
                {
                    if ( toSave )
                    {
                        saveRemarks( session, inCapDto );
                    }
                    LOGGER.info( "Check Inbox Status For Customer Id :: " + inCapDto.getCustomerRecordId() );
                    if ( customerRecordId > 0 )
                    {
                        LOGGER.info( "No Need To Change In Inbox table Because It Is Already IN Save State" );
                    }
                    else
                    {
                        inCapDto = generateInitiateInbox( inCapDto, session );
                    }
                }
            }
            else if ( StringUtils.equals( CRMOperationStages.FULFILLMENT_TEAM.getCode(), inCapDto
                    .getCustomerDetailsPojo().getCrfStage() )
                    || StringUtils.equals( CRMOperationStages.NP_REJECT.getCode(), inCapDto.getCustomerDetailsPojo()
                            .getCrfStage() ) )
            {
                saveFTRelatedInformation( inCapDto, session, evicts );
                if ( StringUtils.equals( CRMOperationStages.NP_REJECT.getCode(), inCapDto.getCustomerDetailsPojo()
                        .getCrfStage() ) )
                {
                    CrmNetworkConfigurationsPojo crmNtWorkConfigPojo = CRMServiceUtils
                            .getDBValues( CrmNetworkConfigurationsPojo.class, IAppConstants.PARAM_CUSTOMER_RECORD_ID,
                                          inCapDto.getCustomerDetailsPojo().getRecordId(), session );
                    if ( StringUtils.isValidObj( crmNtWorkConfigPojo ) )
                    {
                        crmNtWorkConfigPojo.setOption82( null );
                        CRMServiceUtils.mergeDBValues( crmNtWorkConfigPojo );
                    }
                }
            }
            else if ( StringUtils.equals( CRMOperationStages.NETWORK_PARTNER.getCode(), inCapDto
                    .getCustomerDetailsPojo().getCrfStage() )
                    || StringUtils.equals( CRMOperationStages.SP_REJECT_NP.getCode(), inCapDto.getCustomerDetailsPojo()
                            .getCrfStage() ) )
            {
                saveInstallationPartner( inCapDto, session, evicts );
                saveNetworkConfigurations( inCapDto, session, evicts );
            }
            else if ( StringUtils.equals( CRMOperationStages.SERVICE_PARTNER.getCode(), inCapDto
                    .getCustomerDetailsPojo().getCrfStage() ) )
            {
                boolean toProcess = true;
                if ( StringUtils.isValidObj( inCapDto.getPaymentDetailsPojo() )
                        && ( inCapDto.getPaymentDetailsPojo().getPaymentId() == 0 )
                        && !StringUtils.equals( CRMStatusCode.PAYMENT_POSTED.getStatusCode(), inCapDto
                                .getPaymentDetailsPojo().getStatus() )
                        && StringUtils.equals( CRMDisplayListConstants.TELESOLUTIONS.getCode(), inCapDto
                                .getPaymentDetailsPojo().getEntityType() )
                        && GlobalUtils.isValidAmount( inCapDto.getPaymentDetailsPojo().getAmount() ) )
                {
                    toProcess = genricPaymentPosting( inCapDto, session, customerRecordId, evicts );
                }
                if ( toProcess )
                {
                    if ( !StringUtils.equals( CRMOperationStages.SERVICE_PARTNER.getCode(), inCapDto.getToStage() ) )
                    {
                        //saveNetworkConfigurations( inCapDto, session, evicts );
                        saveSPDetails( inCapDto, session, evicts );
                    }
                    else if ( !StringUtils.isValidObj( inCapDto.getCustomerDetailsPojo().getActivationDate() ) )
                    {
                        // MAC Change Case not to save mac before change
                        saveNetworkConfigurations( inCapDto, session, evicts );
                    }
                    else
                    {
                        success = true;
                    }
                }
            }
            else if ( StringUtils.equals( CRMOperationStages.ISR_PUNCH.getCode(), inCapDto.getCustomerDetailsPojo()
                    .getCrfStage() ) )
            {
                saveISRInformation( inCapDto, session, evicts );
                if ( StringUtils.equals( CRMOperationStages.FT_ISR.getCode(), inCapDto.getToStage() )
                        || StringUtils.equals( CRMOperationStages.ON_BOARD.getCode(), inCapDto.getToStage() ) )
                {
                    updateCustomerDetails( inCapDto, session );
                }
            }
            else if ( StringUtils.equals( CRMOperationStages.FT_ISR.getCode(), inCapDto.getCustomerDetailsPojo()
                    .getCrfStage() ) )
            {
                updateCustomerDetails( inCapDto, session );
            }
            else
            {
                success = true;
            }
            if ( StringUtils.equals( CRMServiceCode.CRM001.getStatusCode(), inCapDto.getStatusCode() ) )
            {
                transaction.commit();
                transaction = null;
                LOGGER.info( "Successfully Save OR Update Customer Details" );
            }
            else if ( success )
            {
                inCapDto.setStatusCode( CRMServiceCode.CRM001.getStatusCode() );
                inCapDto.setStatusDesc( CRMServiceCode.CRM001.getStatusDesc() );
            }
        }
        catch ( HibernateException ex )
        {
            LOGGER.error( "HibernateException while Save CAF Method ", ex );
            inCapDto.setStatusCode( CRMServiceCode.CRM999.getStatusCode() );
            CRMServiceUtils.rollback( transaction );
            CRMServiceUtils.closeSessionOnException( session );
        }
        catch ( DuplicateRecieptException ex )
        {
            LOGGER.error( "DuplicateRecieptException for Receipt Number:: " + ex.getReceiptNo() );
            CRMServiceCode statusCode = CRMServiceCode.valueOf( ex.getMessage() );
            inCapDto.setStatusCode( statusCode.getStatusCode() );
            inCapDto.setStatusDesc( statusCode.getStatusDesc() );
            inCapDto.setBillingErrorCode( ex.getReceiptNo() );
        }
        catch ( Exception ex )
        {
            LOGGER.error( "Error in Save CAF Method :: ", ex );
            inCapDto.setStatusCode( CRMServiceCode.CRM999.getStatusCode() );
            inCapDto.setStatusDesc( CRMServiceCode.CRM999.getStatusDesc() );
        }
        finally
        {
            CRMServiceUtils.rollback( transaction );
            CRMServiceUtils.closeSession( session );
            if ( StringUtils.equals( CRMServiceCode.CRM001.getStatusCode(), inCapDto.getStatusCode() ) )
            {
                HibernateUtil.evictAll( evicts );
            }
        }
        return inCapDto;
    }

    @Override
    public boolean genricPaymentPosting( CrmCapDto inCapDto,
                                         Session session,
                                         long customerRecordId,
                                         Map<String, Long> inEvicts )
        throws IllegalAccessException, InvocationTargetException, NoSuchMethodException, DuplicateRecieptException
    {
        boolean successFlag = true;
        inCapDto.getPaymentDetailsPojo().setStatus( CRMStatusCode.PAYMENT_POSTED.getStatusCode() );
        inCapDto.getPaymentDetailsPojo().setPostingDate( Calendar.getInstance().getTime() );
        saveOrderDetails( session, customerRecordId, inCapDto.getUserId(), inCapDto.getOrderDetailsPojo(), inEvicts );
        savePaymentDetails( session, customerRecordId, inCapDto.getUserId(), inCapDto.getPaymentDetailsPojo(),
                            inEvicts, true );
        CrmFinanceDto financeDto = new CrmFinanceDto();
        financeDto.setPaymentDetailsPojo( inCapDto.getPaymentDetailsPojo() );
        getCrmBillingManager().postPayment( financeDto );
        inCapDto.setStatusCode( financeDto.getStatusCode() );
        inCapDto.setStatusDesc( financeDto.getStatusDesc() );
        if ( !StringUtils.equals( CRMServiceCode.CRM001.getStatusCode(), inCapDto.getStatusCode() ) )
        {
            inCapDto.setBillingErrorCode( financeDto.getBillingErrorCode() );
            successFlag = false;
        }
        return successFlag;
    }

    private void updateCustomerDetails( CrmCapDto inCapDto, Session inSession )
    {
        CrmCustomerDetailsPojo custPojo = inCapDto.getCustomerDetailsPojo();
        if ( StringUtils.isValidObj( custPojo ) && custPojo.getRecordId() > 0 )
        {
            CrmCustomerDetailsPojo dbPojo = (CrmCustomerDetailsPojo) inSession.get( CrmCustomerDetailsPojo.class,
                                                                                    custPojo.getRecordId() );
            if ( StringUtils.isValidObj( dbPojo ) )
            {
                dbPojo.setLastModifiedBy( inCapDto.getUserId() );
                dbPojo.setLastModifiedTime( Calendar.getInstance().getTime() );
                if ( StringUtils.isValidObj( custPojo.getDateOnIsr() ) )
                {
                    dbPojo.setDateOnIsr( custPojo.getDateOnIsr() );
                }
                boolean toUpdate = true;
                if ( StringUtils.equals( inCapDto.getCustomerDetailsPojo().getServiceType(),
                                         CRMDisplayListConstants.POST_PAID.getCode() ) )
                {
                    toUpdate = updateCustomerBillingCall( dbPojo, StringUtils.numericValue( dbPojo.getCustomerId() ),
                                                          inCapDto );
                }
                if ( toUpdate )
                {
                    saveCustDBPojo( inCapDto, inSession, dbPojo );
                }
            }
        }
    }

    private void saveCustDBPojo( CrmCapDto inCapDto, Session inSession, CrmCustomerDetailsPojo dbPojo )
    {
        inSession.merge( dbPojo );
        if ( StringUtils.isValidObj( dbPojo.getActivationDate() ) )
        {
            CrmCrfDetailsPojo crmCrfDetailsPojo = (CrmCrfDetailsPojo) inSession.get( CrmCrfDetailsPojo.class,
                                                                                     dbPojo.getRecordId() );
            if ( StringUtils.isValidObj( crmCrfDetailsPojo ) )
            {
                LOGGER.info( "CAF Details Present in DB :: " + crmCrfDetailsPojo.getCrfId() );
                crmCrfDetailsPojo.setLastModifiedBy( inCapDto.getUserId() );
                crmCrfDetailsPojo.setLastModifiedTime( Calendar.getInstance().getTime() );
                if ( StringUtils.isValidObj( dbPojo.getDateOnIsr() ) )
                {
                    crmCrfDetailsPojo.setDateOnIsr( dbPojo.getDateOnIsr() );
                }
                inSession.merge( crmCrfDetailsPojo );
            }
        }
    }

    private void saveISRInformation( CrmCapDto inCapDto, Session inSession, Map<String, Long> inEvicts )
    {
        CRMServiceCode statusCode = CRMServiceCode.CRM001;
        CrmCustomerDetailsPojo crmCustomerDetailsPojo = inCapDto.getCustomerDetailsPojo();
        long customerRecordId = crmCustomerDetailsPojo.getRecordId();
        try
        {
            List<CrmParamDataPojo> paramData = inCapDto.getCrmParamDataPojos();
            if ( StringUtils.isValidObj( paramData ) )
            {
                for ( CrmParamDataPojo crmParamDataPojo : paramData )
                {
                    saveParamDetails( inSession, customerRecordId, inCapDto.getUserId(), crmParamDataPojo );
                }
                inEvicts.put( CrmParamDataPojo.class.getName(), null );
            }
        }
        catch ( Exception ex )
        {
            LOGGER.error( "Error In saveCustomerInformation Method :: ", ex );
            statusCode = CRMServiceCode.CRM999;
        }
        finally
        {
            inCapDto.setStatusCode( statusCode.getStatusCode() );
            inCapDto.setStatusDesc( statusCode.getStatusDesc() );
        }
    }

    private void saveRemarks( Session inSession, CrmCapDto inCapDto )
    {
        CRMServiceCode statusCode = CRMServiceCode.CRM001;
        try
        {
            RemarksPojo remarksPojo = inCapDto.getRemarksPojo();
            if ( StringUtils.isValidObj( remarksPojo ) )
            {
                PojoUtils<RemarksPojo> pojoUtils = new PojoUtils<RemarksPojo>();
                if ( StringUtils.isValidObj( inCapDto.getRemarksPojo() )
                        && StringUtils.equals( inCapDto.getRemarksPojo().getActions(),
                                               CrmActionEnum.ERP.getActionCode() ) )
                {
                    remarksPojo.setCreatedBy( inCapDto.getUserId() );
                    remarksPojo.setCreatedTime( Calendar.getInstance().getTime() );
                    remarksPojo.setMappingType( CRMRCAReason.INA.getStatusCode() );
                    remarksPojo.setMappingId( String.valueOf( inCapDto.getCustomerDetailsPojo().getRecordId() ) );
                    Long generatedId = (Long) inSession.save( remarksPojo );
                    remarksPojo.setRemarkId( generatedId );
                }
                else if ( !pojoUtils.checkPojoEmpty( remarksPojo ) )
                {
                    remarksPojo.setCreatedBy( inCapDto.getUserId() );
                    remarksPojo.setCreatedTime( Calendar.getInstance().getTime() );
                    remarksPojo.setMappingType( CRMRCAReason.INA.getStatusCode() );
                    remarksPojo.setMappingId( String.valueOf( inCapDto.getCustomerDetailsPojo().getRecordId() ) );
                    Long generatedId = (Long) inSession.save( remarksPojo );
                    LOGGER.info( "Successfully Save Remarks Pojo. Generated Id :: " + generatedId );
                    remarksPojo.setRemarkId( generatedId );
                    // Eviction not Required here
                }
            }
        }
        catch ( Exception ex )
        {
            LOGGER.error( "Error In saveCustomerInformation Method :: ", ex );
            statusCode = CRMServiceCode.CRM999;
        }
        finally
        {
            inCapDto.setStatusCode( statusCode.getStatusCode() );
            inCapDto.setStatusDesc( statusCode.getStatusDesc() );
        }
    }

    @Override
    public void saveNetworkConfigurations( CrmCapDto inCapDto, Session inSession, Map<String, Long> inEvicts )
    {
        CRMServiceCode statusCode = CRMServiceCode.CRM001;
        CrmCustomerDetailsPojo crmCustomerDetailsPojo = inCapDto.getCustomerDetailsPojo();
        long customerRecordId = crmCustomerDetailsPojo.getRecordId();
        try
        {
            saveNetworkConfigurations( inSession, customerRecordId, inCapDto.getUserId(),
                                       inCapDto.getNetworkConfigurationsPojo(), inEvicts );
        }
        catch ( Exception ex )
        {
            LOGGER.error( "Error In saveCustomerInformation Method :: ", ex );
            statusCode = CRMServiceCode.CRM999;
        }
        finally
        {
            inCapDto.setStatusCode( statusCode.getStatusCode() );
            inCapDto.setStatusDesc( statusCode.getStatusDesc() );
        }
    }

    private void saveSPDetails( CrmCapDto inCapDto, Session inSession, Map<String, Long> inEvicts )
    {
        CRMServiceCode statusCode = CRMServiceCode.CRM001;
        try
        {
            if ( inCapDto.getNetworkConfigurationsPojo().getRecordId() > 0 )
            {
                CrmNetworkConfigurationsPojo crmNetworkConfigurationsPojoDB = (CrmNetworkConfigurationsPojo) inSession
                        .get( CrmNetworkConfigurationsPojo.class, inCapDto.getNetworkConfigurationsPojo().getRecordId() );
                if ( StringUtils.isValidObj( crmNetworkConfigurationsPojoDB ) )
                {
                    crmNetworkConfigurationsPojoDB.setLastModifiedTime( Calendar.getInstance().getTime() );
                    crmNetworkConfigurationsPojoDB.setLastModifiedBy( inCapDto.getUserId() );
                    crmNetworkConfigurationsPojoDB.setSpOntRgMduDone( inCapDto.getNetworkConfigurationsPojo()
                            .isSpOntRgMduDone() );
                    inSession.update( crmNetworkConfigurationsPojoDB );
                    inEvicts.put( CrmNetworkConfigurationsPojo.class.getName(),
                                  crmNetworkConfigurationsPojoDB.getRecordId() );
                    LOGGER.info( "Network Configurations Updated Successfully Id :: "
                            + inCapDto.getNetworkConfigurationsPojo().getRecordId() );
                }
            }
        }
        catch ( Exception ex )
        {
            LOGGER.error( "Error In saveSPDetails Method :: ", ex );
            statusCode = CRMServiceCode.CRM999;
        }
        finally
        {
            inCapDto.setStatusCode( statusCode.getStatusCode() );
            inCapDto.setStatusDesc( statusCode.getStatusDesc() );
        }
    }

    private void saveInstallationPartner( CrmCapDto inCapDto, Session inSession, Map<String, Long> inEvicts )
    {
        if ( StringUtils.equals( CRMOperationStages.SERVICE_PARTNER.getCode(), inCapDto.getToStage() ) )
        {
            if ( inCapDto.getCustomerDetailsPojo().getRecordId() > 0 )
            {
                CrmCustomerDetailsPojo customerDetailsPojoDB = (CrmCustomerDetailsPojo) inSession
                        .get( CrmCustomerDetailsPojo.class, inCapDto.getCustomerDetailsPojo().getRecordId() );
                if ( StringUtils.isValidObj( customerDetailsPojoDB ) )
                {
                    LOGGER.info( "Customer Details Present in DB :: " + customerDetailsPojoDB.getRecordId() );
                    customerDetailsPojoDB.setLastModifiedTime( Calendar.getInstance().getTime() );
                    customerDetailsPojoDB.setLastModifiedBy( inCapDto.getUserId() );
                    customerDetailsPojoDB.setSpId( inCapDto.getCustomerDetailsPojo().getSpId() );
                    inSession.update( customerDetailsPojoDB );
                    inEvicts.put( CrmCustomerDetailsPojo.class.getName(), customerDetailsPojoDB.getRecordId() );
                    LOGGER.info( "Successfully Update Customer Details For Id :: "
                            + inCapDto.getCustomerDetailsPojo().getRecordId() );
                }
            }
        }
    }

    private void saveFTRelatedInformation( CrmCapDto inCapDto, Session inSession, Map<String, Long> inEvicts )
    {
        CRMServiceCode statusCode = CRMServiceCode.CRM001;
        CrmCustomerDetailsPojo crmCustomerDetailsPojo = inCapDto.getCustomerDetailsPojo();
        long customerRecordId = crmCustomerDetailsPojo.getRecordId();
        try
        {
            if ( StringUtils.equals( CRMOperationStages.NETWORK_PARTNER.getCode(), inCapDto.getToStage() ) )
            {
                if ( customerRecordId > 0 )
                {
                    CrmCustomerDetailsPojo customerDetailsPojoDB = (CrmCustomerDetailsPojo) inSession
                            .get( CrmCustomerDetailsPojo.class, customerRecordId );
                    if ( StringUtils.isValidObj( customerDetailsPojoDB ) )
                    {
                        LOGGER.info( "Customer Details Present in DB :: " + customerDetailsPojoDB.getRecordId() );
                        customerDetailsPojoDB.setLastModifiedTime( Calendar.getInstance().getTime() );
                        customerDetailsPojoDB.setLastModifiedBy( inCapDto.getUserId() );
                        if ( !StringUtils.isValidObj( customerDetailsPojoDB.getFtApprovalDate() ) )
                        {
                            customerDetailsPojoDB.setFtApprovalDate( Calendar.getInstance().getTime() );
                        }
                        customerDetailsPojoDB.setNpId( crmCustomerDetailsPojo.getNpId() );
                        inSession.update( customerDetailsPojoDB );
                        inEvicts.put( CrmCustomerDetailsPojo.class.getName(), customerRecordId );
                        LOGGER.info( "Successfully Update Customer Details For Id :: " + customerRecordId );
                    }
                }
            }
        }
        catch ( Exception ex )
        {
            LOGGER.error( "Error In saveCustomerInformation Method :: ", ex );
            statusCode = CRMServiceCode.CRM999;
        }
        finally
        {
            inCapDto.setStatusCode( statusCode.getStatusCode() );
            inCapDto.setStatusDesc( statusCode.getStatusDesc() );
        }
    }

    @Override
    public CrmCapDto forwardCRF( CrmCapDto inCapDto )
    {
        SessionFactory sessionFactory = null;
        Session session = null;
        Transaction transaction = null;
        CrmActionEnum event = null;
        Map<String, Long> evicts = new HashMap<String, Long>();
        try
        {
            sessionFactory = HibernateUtil.getSessionFactory();
            session = sessionFactory.openSession();
            //check for toUserID
            // if current stage is FTR,NPR,SPR then no need to find toUser because we will assign the crf to prevous user
            //like if current stage is FT and previous stage is FTR then same user forward
            // Customer Activation
            CrmCustomerDetailsPojo oldCustDetailsPojo = CRMServiceUtils.getDBValues( CrmCustomerDetailsPojo.class,
                                                                                     inCapDto.getCustomerDetailsPojo()
                                                                                             .getRecordId() );
            if ( StringUtils.isValidObj( oldCustDetailsPojo )
                    && ( StringUtils.equals( oldCustDetailsPojo.getCrfStage(), CRMOperationStages.ON_BOARD.getCode() ) || StringUtils
                            .equals( oldCustDetailsPojo.getCrfStage(), CRMOperationStages.CANCEL.getCode() ) ) )
            {
                inCapDto.setStatusCode( CRMServiceCode.CRM325.getStatusCode() );
                inCapDto.setStatusDesc( CRMServiceCode.CRM325.getStatusDesc() );
                return inCapDto;
            }
            Long oldPartnerID = null;
            if ( StringUtils.equals( CRMOperationStages.FT_REJECT.getCode(), inCapDto.getCustomerDetailsPojo()
                    .getCrfStage() ) )
            {
                oldPartnerID = oldCustDetailsPojo.getBusinessPartner();
            }
            saveCRF( inCapDto, false );
            CRMEvents welcomeLetterEvent = null;
            if ( StringUtils.equals( CRMServiceCode.CRM001.getStatusCode(), inCapDto.getStatusCode() ) )
            {
                // Billing API for Customer Creation
                if ( ( StringUtils.equals( CRMOperationStages.INITIATE.getCode(), inCapDto.getCustomerDetailsPojo()
                        .getCrfStage() ) || StringUtils.equals( CRMOperationStages.FT_REJECT.getCode(), inCapDto
                        .getCustomerDetailsPojo().getCrfStage() ) )
                        && StringUtils.equals( CRMOperationStages.FULFILLMENT_TEAM.getCode(), inCapDto.getToStage() ) )
                {
                    CrmCapDto crmCapDto = getCustomerDetails( inCapDto.getCustomerRecordId(), null, null );
                    getCrmBillingManager().createCustomer( crmCapDto );
                    inCapDto.setCustomerDetailsPojo( crmCapDto.getCustomerDetailsPojo() );
                    inCapDto.setPaymentDetailsPojosList( crmCapDto.getPaymentDetailsPojosList() );
                    if ( StringUtils.equals( CRMServiceCode.CRM001.getStatusCode(), crmCapDto.getStatusCode() )
                            && StringUtils.equals( CRMOperationStages.FT_REJECT.getCode(), inCapDto
                                    .getCustomerDetailsPojo().getCrfStage() )
                            && inCapDto.getCustomerDetailsPojo().getBusinessPartner() != oldPartnerID.longValue() )
                    {
                        String newPartnerName = CRMServiceUtils.getDBValues( PartnerPojo.class,
                                                                             inCapDto.getCustomerDetailsPojo()
                                                                                     .getBusinessPartner() )
                                .getPartnerName();
                        String oldPartnerName = CRMServiceUtils.getDBValues( PartnerPojo.class, oldPartnerID )
                                .getPartnerName();
                        CrmBillingDto crmBillingDto = getCrmBillingManager()
                                .changeBusinessPartner( inCapDto.getCustomerDetailsPojo().getCustomerId(),
                                                        oldPartnerName, newPartnerName );
                        crmCapDto.setStatusCode( crmBillingDto.getStatusCode() );
                        crmCapDto.setStatusDesc( crmBillingDto.getStatusDesc() );
                        crmCapDto.setBillingErrorCode( crmBillingDto.getBillingErrorCode() );
                    }
                    inCapDto.setStatusCode( crmCapDto.getStatusCode() );
                    inCapDto.setStatusDesc( crmCapDto.getStatusDesc() );
                    if ( StringUtils.equals( CRMServiceCode.CRM001.getStatusCode(), inCapDto.getStatusCode() ) )
                    {
                        if ( StringUtils.numericValue( crmCapDto.getCustomerDetailsPojo().getCustomerId() ) == 0
                                && StringUtils.numericValue( crmCapDto.getCustomerId() ) > 0 )
                        {
                            inCapDto.setCustomerId( crmCapDto.getCustomerId() );
                        }
                        inCapDto.getCustomerDetailsPojo().setFtSubmittionDate( Calendar.getInstance().getTime() );
                        try
                        {
                            List<CrmPaymentDetailsPojo> paymentDetails = inCapDto.getPaymentDetailsPojosList();
                            if ( CommonValidator.isValidCollection( paymentDetails ) )
                            {
                                for ( CrmPaymentDetailsPojo crmPaymentDetailsPojo : paymentDetails )
                                {
                                    crmPaymentDetailsPojo = CRMServiceUtils.getDBValues( CrmPaymentDetailsPojo.class,
                                                                                         crmPaymentDetailsPojo
                                                                                                 .getPaymentId() );
                                    if ( StringUtils.isValidObj( crmPaymentDetailsPojo ) )
                                    {
                                        crmPaymentDetailsPojo.setStatus( CRMStatusCode.PAYMENT_POSTED.getStatusCode() );
                                        crmPaymentDetailsPojo.setPostingDate( Calendar.getInstance().getTime() );
                                        CRMServiceUtils.mergeDBValues( crmPaymentDetailsPojo );
                                    }
                                }
                            }
                        }
                        catch ( Exception ex )
                        {
                            LOGGER.error( "Error while updating payments after posted to Billing:: ", ex );
                        }
                    }
                    else
                    {
                        inCapDto.setBillingErrorCode( crmCapDto.getBillingErrorCode() );
                    }
                }
                else if ( StringUtils.equals( CRMOperationStages.FULFILLMENT_TEAM.getCode(), inCapDto
                        .getCustomerDetailsPojo().getCrfStage() )
                        && StringUtils.equals( CRMOperationStages.NETWORK_PARTNER.getCode(), inCapDto.getToStage() ) )
                {
                    LOGGER.info( "@Customer ID:" + inCapDto.getCustomerDetailsPojo().getCustomerId() + " Event: "
                            + CRMEvents.FORWARD_CRF.getEventName() );
                    if ( !StringUtils.equals( inCapDto.getCustomerDetailsPojo().getBrand(), IAppConstants.BRAND_INITIA ) )
                    {
                        CRMServiceUtils.sendAlerts( CRMEvents.FORWARD_CRF, CRMRequestType.CUSTOMER_ID, inCapDto
                                .getCustomerDetailsPojo().getCustomerId(), null );
                        LOGGER.info( "@Customer ID:" + inCapDto.getCustomerDetailsPojo().getCustomerId() + " Event: "
                                + CRMEvents.FT_APPROVAL.getEventName() );
                        CRMServiceUtils.sendAlerts( CRMEvents.FT_APPROVAL, CRMRequestType.NETWORK_PARTNER, inCapDto
                                .getCustomerDetailsPojo().getCustomerId(), null );
                        /* CrmCustomerActivityPojo activityLogPojo = new CrmCustomerActivityPojo();
                         activityLogPojo.setOldValue( IAppConstants.DASH );
                         activityLogPojo.setNewValue( CRMCustomerActivityActions.EMAIL_VERIFICATION_LINK_SENT
                                 .getActionDesc() );
                         activityLogPojo.setAction( CRMCustomerActivityActions.EMAIL_VERIFICATION_LINK_SENT
                                 .getActionCode() );
                         activityLogPojo.setCustomerId( inCapDto.getCustomerDetailsPojo().getCustomerId() );
                         activityLogPojo.setCreatedBy( inCapDto.getUserId() );
                         activityLogPojo.setReason( CRMCustomerActivityActions.EMAIL_VERIFICATION_LINK_SENT
                                 .getActionDesc() );
                         activityLogPojo.setServiceIp( CRMUtils.getServerIp() );
                         activityLogPojo.setClientIp( inCapDto.getClientIPAddress() );
                         activityLogPojo.setServerIp( inCapDto.getServerIPAddress() );
                         CRMServiceUtils.createActivityLog( activityLogPojo );
                         LOGGER.info( "@Customer ID:" + inCapDto.getCustomerDetailsPojo().getCustomerId()
                                 + " Activity: "
                                 + CRMCustomerActivityActions.EMAIL_VERIFICATION_LINK_SENT.getActionDesc() );*/
                    }
                    else
                    {
                        LOGGER.info( "@Customer ID:" + inCapDto.getCustomerDetailsPojo().getCustomerId()
                                + " Brand Name: " + inCapDto.getCustomerDetailsPojo().getBrand() );
                    }
                }
                else if ( ( StringUtils.equals( CRMOperationStages.SERVICE_PARTNER.getCode(), inCapDto
                        .getCustomerDetailsPojo().getCrfStage() ) )
                        && StringUtils.equals( CRMOperationStages.SERVICE_PARTNER.getCode(), inCapDto.getToStage() ) )
                {
                    CrmCapDto crmCapDto = getCustomerDetails( inCapDto.getCustomerDetailsPojo().getRecordId(), null,
                                                              null );
                    if ( StringUtils.isValidObj( crmCapDto.getCustomerDetailsPojo().getActivationDate() ) )
                    {
                        CrmQrcDto qrcDto = new CrmQrcDto();
                        qrcDto.setCustomerId( crmCapDto.getCustomerDetailsPojo().getCustomerId() );
                        qrcDto.setCustomerDetailsPojo( crmCapDto.getCustomerDetailsPojo() );
                        qrcDto.setNetworkConfigurationsPojo( inCapDto.getNetworkConfigurationsPojo() );
                        qrcDto.setMacFaulty( inCapDto.isMacFaulty() );
                        qrcDto.setActivityAction( CRMCustomerActivityActions.MAC_CHANGE.getActionDesc() );
                        CrmBillingDto billingDto = getCrmBillingManager().changeDeviceDetails( qrcDto );
                        inCapDto.setStatusCode( billingDto.getStatusCode() );
                        inCapDto.setStatusDesc( billingDto.getStatusDesc() );
                        inCapDto.setBillingErrorCode( billingDto.getBillingErrorCode() );
                        event = CrmActionEnum.MAC_CHNAGE;
                    }
                    else
                    {
                        if ( !StringUtils.isValidObj( crmCapDto.getNetworkConfigurationsPojo() ) )
                        {
                            crmCapDto.setNetworkConfigurationsPojo( inCapDto.getNetworkConfigurationsPojo() );
                        }
                        Map<String, Object> criteriaMap = new HashMap<String, Object>();
                        criteriaMap.put( "customerRecordId", inCapDto.getCustomerDetailsPojo().getRecordId() );
                        CrmPaymentDetailsPojo paymentDetailsPojo = CRMServiceUtils
                                .getDBValue( CrmPaymentDetailsPojo.class, criteriaMap, null, true );
                        crmCapDto.setPaymentDetailsPojo( paymentDetailsPojo );
                        getCrmBillingManager().customerActivation( crmCapDto );
                        inCapDto.setStatusCode( crmCapDto.getStatusCode() );
                        inCapDto.setStatusDesc( crmCapDto.getStatusDesc() );
                        inCapDto.setBillingErrorCode( crmCapDto.getBillingErrorCode() );
                        event = CrmActionEnum.CUSTOMER_ACTIVATED;
                    }
                    inCapDto.setCustomerDetailsPojo( crmCapDto.getCustomerDetailsPojo() );
                    transaction = session.beginTransaction();
                    if ( StringUtils.equals( CRMServiceCode.CRM001.getStatusCode(), inCapDto.getStatusCode() ) )
                    {
                        inCapDto.getNetworkConfigurationsPojo().setMacBind( IAppConstants.Y );
                        saveNetworkConfigurations( inCapDto, session, evicts );
                        if ( StringUtils.equals( CRMServiceCode.CRM001.getStatusCode(), inCapDto.getStatusCode() ) )
                        {
                            if ( !StringUtils.isValidObj( inCapDto.getCustomerDetailsPojo().getActivationDate() ) )
                            {
                                inCapDto.getCustomerDetailsPojo().setActivationDate( Calendar.getInstance().getTime() );
                                inCapDto.getCustomerDetailsPojo().setStatus( CRMStatusCode.ACTIVE.getStatusCode() );
                                if ( StringUtils.equals( CRMDisplayListConstants.POST_PAID.getCode(), inCapDto
                                        .getCustomerDetailsPojo().getServiceType() ) )
                                {
                                    setBillDate( inCapDto.getCustomerDetailsPojo(), session );
                                }
                                session.update( inCapDto.getCustomerDetailsPojo() );
                                evicts.put( CrmCustomerDetailsPojo.class.getName(), inCapDto.getCustomerDetailsPojo()
                                        .getRecordId() );
                                crmCapDto.setUserId( inCapDto.getUserId() );
                                saveDublicateCrfCustomerInfo( crmCapDto, session, evicts );
                            }
                            transaction.commit();
                            long myAccountID = createMyAccount( inCapDto );
                            Map<String, String> paramValues = new HashMap<String, String>();
                            CrmBillingDto billingDto = getCrmBillingManager()
                                    .getCustomerUsageDetail( inCapDto.getCustomerDetailsPojo().getCustomerId(), false );
                            if ( StringUtils.isValidObj( billingDto.getCustAdditionalDetails() ) )
                            {
                                paramValues.put( "BILL_DUE_DATE", DateUtils.getFormattedDate( billingDto
                                        .getCustAdditionalDetails().getExpiryDate(), IDateConstants.SDF_DD_MMM_YYYY ) );
                            }
                            if ( StringUtils.equals( inCapDto.getCustomerDetailsPojo().getBrand(),
                                                     IAppConstants.BRAND_INITIA ) )
                            {
                                CRMServiceUtils.sendAlerts( CRMEvents.BIND_MAC_ID_INITIA, CRMRequestType.CUSTOMER_ID,
                                                            inCapDto.getCustomerDetailsPojo().getCustomerId(), null,
                                                            paramValues );
                            }
                            else
                            {
                                // Alerts should be sent always after creating MyAccount.
                                CRMServiceUtils.sendAlerts( CRMEvents.BIND_MAC_ID, CRMRequestType.CUSTOMER_ID, inCapDto
                                        .getCustomerDetailsPojo().getCustomerId(), null, paramValues );
                            }
                            if ( StringUtils.equals( CRMDisplayListConstants.ETHERNET_ON_CABLE.getCode(), inCapDto
                                    .getCustomerDetailsPojo().getProduct() ) )
                            {
                                if ( StringUtils.equals( inCapDto.getCustomerDetailsPojo().getBrand(),
                                                         IAppConstants.BRAND_INITIA ) )
                                {
                                    CRMServiceUtils.sendAlerts( CRMEvents.BIND_MAC_ID_EOC_INITIA,
                                                                CRMRequestType.CUSTOMER_ID, inCapDto
                                                                        .getCustomerDetailsPojo().getCustomerId(),
                                                                null, paramValues );
                                }
                                else
                                {
                                    CRMServiceUtils.sendAlerts( CRMEvents.BIND_MAC_ID_EOC, CRMRequestType.CUSTOMER_ID,
                                                                inCapDto.getCustomerDetailsPojo().getCustomerId(),
                                                                null, paramValues );
                                }
                            }
                            if ( !StringUtils.equals( inCapDto.getCustomerDetailsPojo().getBrand(),
                                                      IAppConstants.BRAND_INITIA ) )
                            {
                                if ( myAccountID > 0 )
                                {
                                    CRMServiceUtils.sendAlerts( CRMEvents.MY_ACCOUNT_CREATION,
                                                                CRMRequestType.CUSTOMER_ID, inCapDto
                                                                        .getCustomerDetailsPojo().getCustomerId(),
                                                                null, paramValues );
                                }
                            }
                        }
                        else
                        {
                            CRMServiceUtils.rollback( transaction );
                            transaction = null;
                        }
                    }
                }
                else if ( ( StringUtils.equals( CRMOperationStages.SERVICE_PARTNER.getCode(), inCapDto
                        .getCustomerDetailsPojo().getCrfStage() )
                        || StringUtils.equals( CRMOperationStages.FT_ISR.getCode(), inCapDto.getCustomerDetailsPojo()
                                .getCrfStage() ) || StringUtils
                            .equals( CRMOperationStages.ISR_PUNCH.getCode(), inCapDto.getCustomerDetailsPojo()
                                    .getCrfStage() ) )
                        && StringUtils.equals( CRMOperationStages.ON_BOARD.getCode(), inCapDto.getToStage() ) )
                {
                    if ( StringUtils.isValidObj( inCapDto.getCustomerDetailsPojo().getActivationDate() ) )
                    {
                        welcomeLetterEvent = getWelcomeLetterEvent( inCapDto );
                    }
                    else
                    {
                        inCapDto.setStatusCode( CRMServiceCode.CRM324.getStatusCode() );
                        inCapDto.setStatusDesc( CRMServiceCode.CRM324.getStatusDesc() );
                    }
                }
                else if ( StringUtils.equals( CRMOperationStages.CANCEL.getCode(), inCapDto.getToStage() )
                        && !StringUtils.equals( CRMOperationStages.INITIATE.getCode(), inCapDto
                                .getCustomerDetailsPojo().getCrfStage() ) )
                {
                    CrmBillingDto billingDto = new CrmBillingDto();
                    billingDto.setCustomerId( inCapDto.getCustomerDetailsPojo().getCustomerId() );
                    billingDto.setStatus( inCapDto.getCustomerDetailsPojo().getStatus() );
                    billingDto.setRemarks( inCapDto.getRemarksPojo().getRemarks() );
                    getCrmBillingManager().changeStatus( billingDto );
                    inCapDto.setStatusCode( billingDto.getStatusCode() );
                    inCapDto.setStatusDesc( billingDto.getStatusDesc() );
                    inCapDto.setBillingErrorCode( billingDto.getBillingErrorCode() );
                }
                else if ( ( StringUtils.equals( CRMOperationStages.SP_REJECT_BP.getCode(), inCapDto
                        .getCustomerDetailsPojo().getCrfStage() ) || StringUtils
                        .equals( CRMOperationStages.NP_REJECT_SALES.getCode(), inCapDto.getCustomerDetailsPojo()
                                .getCrfStage() ) )
                        && StringUtils.equals( CRMOperationStages.FULFILLMENT_TEAM.getCode(), inCapDto.getToStage() ) )
                {
                    LOGGER.info( "CRF Stage ::" + inCapDto.getCustomerDetailsPojo().getCrfStage() + " To Stage : "
                            + inCapDto.getToStage() );
                    if ( StringUtils.isValidObj( inCapDto.getAddressDetailsPojo() )
                            && inCapDto.getAddressDetailsPojo().getRecordId() > 0 )
                    {
                        CrmAddressDetailsPojo crmAddressDetailsPojo = inCapDto.getAddressDetailsPojo();
                        LOGGER.info( "Record ID:" + inCapDto.getAddressDetailsPojo().getRecordId()
                                + "Inst.. Society ID::" + inCapDto.getAddressDetailsPojo().getInstSocietyId() );
                        CrmAddressDetailsPojo crmAddressDetailsPojoDB = (CrmAddressDetailsPojo) session
                                .get( CrmAddressDetailsPojo.class, crmAddressDetailsPojo.getRecordId() );
                        crmAddressDetailsPojoDB.setStateId( crmAddressDetailsPojo.getStateId() );
                        crmAddressDetailsPojoDB.setCityId( crmAddressDetailsPojo.getCityId() );
                        crmAddressDetailsPojoDB.setInstAreaId( crmAddressDetailsPojo.getInstAreaId() );
                        crmAddressDetailsPojoDB.setInstSocietyId( crmAddressDetailsPojo.getInstSocietyId() );
                        crmAddressDetailsPojoDB.setAddressType( crmAddressDetailsPojo.getAddressType() );
                        crmAddressDetailsPojoDB.setLastModifiedTime( Calendar.getInstance().getTime() );
                        crmAddressDetailsPojoDB.setLastModifiedBy( inCapDto.getUserId() );
                        session.update( crmAddressDetailsPojoDB );
                        LOGGER.info( "Successfully Update Address Detail Pojo For Id :: "
                                + crmAddressDetailsPojoDB.getRecordId() );
                    }
                }
                if ( StringUtils.equals( CRMServiceCode.CRM001.getStatusCode(), inCapDto.getStatusCode() ) )
                {
                    transaction = session.beginTransaction();
                    if ( StringUtils.isBlank( inCapDto.getCustomerDetailsPojo().getCustomerId() )
                            || ( StringUtils.numericValue( inCapDto.getCustomerDetailsPojo().getCustomerId() ) == 0 ) )
                    {
                        inCapDto.getCustomerDetailsPojo().setCustomerId( inCapDto.getCustomerId() );
                    }
                    CrmCustomerDetailsPojo custDetailsPojo = modifyCRFStage( inCapDto, session );
                    if ( StringUtils.equals( CRMServiceCode.CRM001.getStatusCode(), inCapDto.getStatusCode() ) )
                    {
                        inCapDto.setCustomerDetailsPojo( custDetailsPojo );
                        if ( !StringUtils.isValidObj( inCapDto.getRemarksPojo() )
                                || !StringUtils.equals( inCapDto.getRemarksPojo().getActions(),
                                                        CrmActionEnum.ERP.getActionCode() ) )
                        {
                            changeInboxBinOFCRF( inCapDto );
                        }
                        if ( StringUtils.equals( CRMServiceCode.CRM001.getStatusCode(), inCapDto.getStatusCode() ) )
                        {
                            transaction.commit();
                            transaction = null;
                            if ( StringUtils.equals( CRMOperationStages.CANCEL.getCode(), inCapDto.getToStage() ) )
                            {
                                CRMServiceUtils.generateAutoTicket( inCapDto.getCustomerDetailsPojo().getCustomerId(),
                                                                    QRCRolCategories.REFUND_REQUEST, null, inCapDto
                                                                            .getCustomerDetailsPojo()
                                                                            .getLastModifiedBy(), inCapDto
                                                                            .getRemarksPojo().getRemarks() );
                            }
                            if ( StringUtils.isValidObj( welcomeLetterEvent ) )
                            {
                                if ( StringUtils.contains( welcomeLetterEvent.getEventName(), "NVRFD" ) )
                                {
                                    updateToken( inCapDto.getCustomerDetailsPojo().getCustomerId(),
                                                 inCapDto.getUserId() );
                                }
                                List<String> attachments = null;
                                if ( StringUtils.equals( CRMDisplayListConstants.ETHERNET_ON_CABLE.getCode(), inCapDto
                                        .getCustomerDetailsPojo().getProduct() ) )
                                {
                                    try
                                    {
                                        String path = FileUtils.getFilePath( "Slave_Wireless_Setting.pdf" );
                                        LOGGER.info( "File path:" + path );
                                        if ( StringUtils.isNotBlank( path ) )
                                        {
                                            attachments = new ArrayList<String>();
                                            attachments.add( path );
                                        }
                                    }
                                    catch ( Exception ex )
                                    {
                                        LOGGER.warn( "Exception loading File : Slave_Wireless_Setting.pdf - " + ex, ex );
                                    }
                                }
                                Map<String, String> paramValues = new HashMap<String, String>();
                                CrmBillingDto billingDto = getCrmBillingManager()
                                        .getCustomerUsageDetail( inCapDto.getCustomerDetailsPojo().getCustomerId(),
                                                                 false );
                                if ( StringUtils.isValidObj( billingDto.getCustAdditionalDetails() ) )
                                {
                                    paramValues.put( "BILL_DUE_DATE", DateUtils
                                            .getFormattedDate( billingDto.getCustAdditionalDetails().getExpiryDate(),
                                                               IDateConstants.SDF_DD_MMM_YYYY ) );
                                }
                                CRMServiceUtils.sendAlerts( welcomeLetterEvent, CRMRequestType.CUSTOMER_ID, inCapDto
                                        .getCustomerDetailsPojo().getCustomerId(), attachments, paramValues );
                            }
                            CrmAuditLogPojo returnAuditLog = CRMServiceUtils
                                    .createAuditLog( insertCRFAuditLogs( inCapDto, null ) );
                            if ( StringUtils.isValidObj( returnAuditLog ) )
                            {
                                if ( !StringUtils.equals( CRMOperationStages.INITIATE.getCode(), inCapDto
                                        .getCustomerDetailsPojo().getCrfPreviousStage() )
                                        || StringUtils.equals( CRMOperationStages.CANCEL.getCode(),
                                                               inCapDto.getToStage() )
                                        || ( StringUtils.isValidObj( inCapDto.getRemarksPojo() ) && StringUtils
                                                .equals( inCapDto.getRemarksPojo().getActions(),
                                                         CrmActionEnum.ERP.getActionCode() ) ) )
                                {
                                    inCapDto.getRemarksPojo().setActivityId( returnAuditLog.getAuditLogId() );
                                    transaction = session.beginTransaction();
                                    saveRemarks( session, inCapDto );
                                    if ( inCapDto.getRemarksPojo().getRemarkId() > 0 )
                                    {
                                        transaction.commit();
                                    }
                                }
                                LOGGER.info( "Successfully insert in to audit Log for CAF." );
                            }
                        }
                    }
                    else if ( StringUtils.equals( CRMServiceCode.CRM313.getStatusCode(), inCapDto.getStatusCode() )
                            || StringUtils.equals( CRMServiceCode.CRM320.getStatusCode(), inCapDto.getStatusCode() ) )
                    {
                        CrmAuditLogPojo returnAuditLog = CRMServiceUtils.createAuditLog( insertCRFAuditLogs( inCapDto,
                                                                                                             event ) );
                        if ( StringUtils.isValidObj( returnAuditLog ) )
                        {
                            if ( StringUtils.isValidObj( inCapDto.getRemarksPojo() )
                                    && StringUtils.equals( inCapDto.getRemarksPojo().getActions(),
                                                           CrmActionEnum.ERP.getActionCode() ) )
                            {
                                inCapDto.getRemarksPojo().setActivityId( returnAuditLog.getAuditLogId() );
                                // transaction = session.beginTransaction();
                                LOGGER.info( "=====================RemarkId===========================" );
                                saveRemarks( session, inCapDto );
                                LOGGER.info( "RemarkId::" + inCapDto.getRemarksPojo().getRemarkId() );
                                LOGGER.info( "================================================" );
                                if ( inCapDto.getRemarksPojo().getRemarkId() > 0 )
                                {
                                    transaction.commit();
                                }
                            }
                            LOGGER.info( "Successfully insert in to audit Log for CAF 111." );
                        }
                        inCapDto.setStatusCode( CRMServiceCode.CRM001.getStatusCode() );
                        inCapDto.setStatusDesc( CRMServiceCode.CRM001.getStatusDesc() );
                    }
                    /* else if ( StringUtils.equals( CRMServiceCode.CRM320.getStatusCode(), inCapDto.getStatusCode() ) )
                     {
                         CrmAuditLogPojo returnAuditLog = CRMServiceUtils
                                 .createAuditLog( insertCRFAuditLogs( inCapDto ) );
                         if ( StringUtils.isValidObj( returnAuditLog ) )
                         {
                             LOGGER.info( "Successfully insert in to audit Log for CAF." );
                         }
                         inCapDto.setStatusCode( CRMServiceCode.CRM001.getStatusCode() );
                         inCapDto.setStatusDesc( CRMServiceCode.CRM001.getStatusDesc() );
                     }*/
                }
            }
        }
        catch ( HibernateException ex )
        {
            LOGGER.error( "HibernateException occured while Forward CAF Method ", ex );
            inCapDto.setStatusCode( CRMServiceCode.CRM999.getStatusCode() );
            CRMServiceUtils.rollback( transaction );
            CRMServiceUtils.closeSessionOnException( session );
        }
        catch ( Exception ex )
        {
            LOGGER.error( "Error in Forward CAF Method :: ", ex );
            inCapDto.setStatusCode( CRMServiceCode.CRM999.getStatusCode() );
            inCapDto.setStatusDesc( CRMServiceCode.CRM999.getStatusDesc() );
        }
        finally
        {
            CRMServiceUtils.rollback( transaction );
            CRMServiceUtils.closeSession( session );
            if ( StringUtils.equals( inCapDto.getStatusCode(), CRMServiceCode.CRM001.getStatusCode() ) )
            {
                HibernateUtil.evictAll( evicts );
            }
        }
        return inCapDto;
    }

    private void setBillDate( CrmCustomerDetailsPojo inCrmCustomerDetailsPojo, Session session )
    {
        int day = 0;
        //Don't delete the Commented Code
        //        try
        //        {
        //            Set<CrmPlanDetailsPojo> plans = inCrmCustomerDetailsPojo.getCrmPlanDetailses();
        //            for ( CrmPlanDetailsPojo crmPlanDetailsPojo : plans )
        //            {
        //                if ( StringUtils.isNotBlank( crmPlanDetailsPojo.getBasePlanCode() ) )
        //                {
        //                    CrmPlanMasterPojo planMaster = CRMServiceUtils.getDBValues( CrmPlanMasterPojo.class, "planCode",
        //                                                                                crmPlanDetailsPojo.getBasePlanCode(),
        //                                                                                session );
        //                    if ( StringUtils.isValidObj( planMaster ) && StringUtils.equals( "DEMO", planMaster.getPlanType() ) )
        //                    {
        //                        day = 1;
        //                    }
        //                }
        //            }
        //        }
        //        catch ( Exception ex )
        //        {
        //            LOGGER.error( "Error while setting Bill Date for Demo Plans", ex );
        //        }
        if ( day == 0 )
        {
            day = CrmDaoHelper.getPostpaidBillDate();
        }
        inCrmCustomerDetailsPojo.setBillDate( day + "" );
    }

    private CrmCapDto saveDublicateCrfCustomerInfo( CrmCapDto inCapDto, Session inSession, Map<String, Long> inEvicts )
        throws DuplicateRecieptException
    {
        LOGGER.info( " :: Inside saveDublicateCrfCustomerInformation Method :: " );
        CRMServiceCode statusCode = CRMServiceCode.CRM999;
        try
        {
            if ( StringUtils.isValidObj( inCapDto.getCustomerDetailsPojo() ) )
            {
                saveDublicateCRFCustomerDetails( inCapDto, inSession, inEvicts );
            }
            //save or update DublicateCrf nationality details
            saveDublicateCrfNationalityDetails( inSession, inCapDto, inEvicts );
            //save or update DublicateCrf network details
            saveDublicateCrfNetworkDetails( inSession, inCapDto, inEvicts );
            //save or update DublicateCrf Order details
            saveDublicateCrfOrderDetails( inSession, inCapDto, inEvicts );
            // create and update DublicateCrf address list
            if ( CommonValidator.isValidCollection( inCapDto.getCustomerDetailsPojo().getCrmAddressDetailses() ) )
            {
                LOGGER.info( "Size of Address Pojos ::"
                        + inCapDto.getCustomerDetailsPojo().getCrmAddressDetailses().size() );
                for ( CrmAddressDetailsPojo crmAddressDetailsPojo : inCapDto.getCustomerDetailsPojo()
                        .getCrmAddressDetailses() )
                {
                    saveDublicateCrfAddressDetails( inSession, inCapDto.getCustomerRecordId(), inCapDto.getUserId(),
                                                    crmAddressDetailsPojo, inEvicts );
                }
            }
            //save or update DublicateCrf Plan details
            if ( CommonValidator.isValidCollection( inCapDto.getCustomerDetailsPojo().getCrmPlanDetailses() ) )
            {
                LOGGER.info( "Size of PlanDetails Pojos ::"
                        + inCapDto.getCustomerDetailsPojo().getCrmPlanDetailses().size() );
                for ( CrmPlanDetailsPojo crmPlanDetailsPojo : inCapDto.getCustomerDetailsPojo().getCrmPlanDetailses() )
                {
                    saveDublicateCrfPlanDetails( inSession, inCapDto.getCustomerRecordId(), inCapDto.getUserId(),
                                                 crmPlanDetailsPojo, inEvicts );
                }
            }
            statusCode = CRMServiceCode.CRM001;
        }
        catch ( Exception ex )
        {
            LOGGER.error( "Error In saveDublicateCrfCustomerInfo Method :: ", ex );
            statusCode = CRMServiceCode.CRM999;
        }
        finally
        {
            inCapDto.setStatusCode( statusCode.getStatusCode() );
            inCapDto.setStatusDesc( statusCode.getStatusDesc() );
        }
        return inCapDto;
    }

    private void saveDublicateCrfPlanDetails( Session inSession,
                                              long inCustomerRecordId,
                                              String inUserId,
                                              CrmPlanDetailsPojo inCrmPlanDetailsPojo,
                                              Map<String, Long> inEvicts )
        throws IllegalAccessException, InvocationTargetException, NoSuchMethodException
    {
        CrmCrfPlanDetailsPojo crfPlanDetailsPojo = null;
        if ( StringUtils.isValidObj( inCrmPlanDetailsPojo ) )
        {
            crfPlanDetailsPojo = new CrmCrfPlanDetailsPojo();
            org.apache.commons.beanutils.PropertyUtils.copyProperties( crfPlanDetailsPojo, inCrmPlanDetailsPojo );
            crfPlanDetailsPojo.setCustomerRecordId( inCustomerRecordId );
            Long generatedId = (Long) inSession.save( crfPlanDetailsPojo );
            inEvicts.put( CrmCrfPlanDetailsPojo.class.getName(), null );
            LOGGER.info( "Successfully Save Plan Details Pojo. Generated Id :: " + generatedId );
        }
    }

    private void saveDublicateCrfAddressDetails( Session inSession,
                                                 long inCustomerRecordId,
                                                 String inUserId,
                                                 CrmAddressDetailsPojo inCrmAddressDetailsPojo,
                                                 Map<String, Long> inEvicts )
        throws IllegalAccessException, InvocationTargetException, NoSuchMethodException
    {
        CrmCrfAddressDetailsPojo crmCrfAddressDetailsPojo = new CrmCrfAddressDetailsPojo();
        org.apache.commons.beanutils.PropertyUtils.copyProperties( crmCrfAddressDetailsPojo, inCrmAddressDetailsPojo );
        crmCrfAddressDetailsPojo.setCustomerRecordId( inCustomerRecordId );
        Long generatedId = (Long) inSession.save( crmCrfAddressDetailsPojo );
        inEvicts.put( CrmCrfAddressDetailsPojo.class.getName(), null );
        LOGGER.info( "Successfully Save Address Detail  Pojo. Generated Id :: " + generatedId );
    }

    private void saveDublicateCrfOrderDetails( Session inSession, CrmCapDto inCapDto, Map<String, Long> inEvicts )
        throws IllegalAccessException, InvocationTargetException, NoSuchMethodException
    {
        CrmCrfOrderDetailsPojo crmCrfOrderDetailsPojo = new CrmCrfOrderDetailsPojo();
        CrmOrderDetailsPojo inOrderDetailsPojo = null;
        if ( StringUtils.isValidObj( inCapDto.getOrderDetailsPojo() ) )
        {
            inOrderDetailsPojo = inCapDto.getOrderDetailsPojo();
            org.apache.commons.beanutils.PropertyUtils.copyProperties( crmCrfOrderDetailsPojo, inOrderDetailsPojo );
            crmCrfOrderDetailsPojo.setCustomerRecordId( inCapDto.getCustomerRecordId() );
            Long generatedId = (Long) inSession.save( crmCrfOrderDetailsPojo );
            inEvicts.put( CrmCrfOrderDetailsPojo.class.getName(), null );
            LOGGER.info( "Successfully Save CRFOrderDetails Pojo. Generated Id :: " + generatedId );
        }
    }

    private void saveDublicateCrfNetworkDetails( Session inSession, CrmCapDto inCapDto, Map<String, Long> inEvicts )
        throws IllegalAccessException, InvocationTargetException, NoSuchMethodException
    {
        CrmNetworkConfigurationsPojo inNetworkConfigurationsPojo = null;
        CrmCrfNetworkConfigPojo crfNetworkConfigPojo = null;
        if ( StringUtils.isValidObj( inCapDto.getNetworkConfigurationsPojo() ) )
        {
            inNetworkConfigurationsPojo = inCapDto.getNetworkConfigurationsPojo();
            crfNetworkConfigPojo = new CrmCrfNetworkConfigPojo();
            org.apache.commons.beanutils.PropertyUtils.copyProperties( crfNetworkConfigPojo,
                                                                       inNetworkConfigurationsPojo );
            crfNetworkConfigPojo.setCustomerRecordId( inCapDto.getCustomerRecordId() );
            Long generatedId = (Long) inSession.save( crfNetworkConfigPojo );
            inEvicts.put( CrmCrfNetworkConfigPojo.class.getName(), null );
            LOGGER.info( "Successfully Save Network Configuration Pojo. Generated Id :: " + generatedId );
        }
    }

    private void saveDublicateCrfNationalityDetails( Session inSession, CrmCapDto inCapDto, Map<String, Long> inEvicts )
        throws IllegalAccessException, InvocationTargetException, NoSuchMethodException
    {
        CrmCrfNationalityDetailsPojo crmCrfNationalityPojo = new CrmCrfNationalityDetailsPojo();
        CrmNationalityDetailsPojo inNationalityDetailsPojo = null;
        if ( StringUtils.isValidObj( inCapDto.getNationalityDetailsPojo() ) )
        {
            inNationalityDetailsPojo = inCapDto.getNationalityDetailsPojo();
            org.apache.commons.beanutils.PropertyUtils.copyProperties( crmCrfNationalityPojo, inNationalityDetailsPojo );
            crmCrfNationalityPojo.setCustomerRecordId( inCapDto.getCustomerRecordId() );
            Long generatedId = (Long) inSession.save( crmCrfNationalityPojo );
            inEvicts.put( CrmCrfNationalityDetailsPojo.class.getName(), null );
            LOGGER.info( "Successfully Save Nationality Detail. Generated Id :: " + generatedId );
        }
    }

    private long saveDublicateCRFCustomerDetails( CrmCapDto inCapDto, Session inSession, Map<String, Long> inEvicts )
        throws IllegalAccessException, InvocationTargetException, NoSuchMethodException
    {
        CrmCrfDetailsPojo crmCrfDetailsPojo = new CrmCrfDetailsPojo();
        CrmCustomerDetailsPojo crmCustomerDetailsPojo = null;
        long customerRecordId = 0;
        if ( StringUtils.isValidObj( inCapDto.getCustomerDetailsPojo() ) )
        {
            crmCustomerDetailsPojo = inCapDto.getCustomerDetailsPojo();
            org.apache.commons.beanutils.PropertyUtils.copyProperties( crmCrfDetailsPojo, crmCustomerDetailsPojo );
            customerRecordId = crmCrfDetailsPojo.getRecordId();
            customerRecordId = (Long) inSession.save( crmCrfDetailsPojo );
            LOGGER.info( "Successfully Save Customer Details. Generated ID :: " + customerRecordId );
            inCapDto.setCustomerRecordId( customerRecordId );
            inEvicts.put( CrmCustomerDetailsPojo.class.getName(), null );
        }
        return customerRecordId;
    }

    private CRMEvents getWelcomeLetterEvent( CrmCapDto inCapDto )
    {
        StringBuilder welcomeLetter = new StringBuilder( "WLCM_" );
        welcomeLetter.append( inCapDto.getCustomerDetailsPojo().getProduct() + IAppConstants.UNDERSCORE );
        welcomeLetter.append( inCapDto.getCustomerDetailsPojo().getServiceType() + IAppConstants.UNDERSCORE );
        String strVrfd = "NVRFD";
        if ( StringUtils.equals( inCapDto.getCustomerDetailsPojo().getEmailValidationFlag(),
                                 CRMParameter.YES.getParameter() ) )
        {
            strVrfd = "VRFD";
        }
        if ( StringUtils.equals( inCapDto.getCustomerDetailsPojo().getBrand(), IAppConstants.BRAND_INITIA ) )
        {
            strVrfd = "VRFD";
        }
        welcomeLetter.append( strVrfd );
        if ( StringUtils.equals( inCapDto.getCustomerDetailsPojo().getBrand(), IAppConstants.BRAND_INITIA ) )
        {
            welcomeLetter.append( IAppConstants.UNDERSCORE + IAppConstants.BRAND_INITIA );
        }
        CRMEvents welcomeLetterEvent = CRMEvents.valueOf( welcomeLetter.toString() );
        return welcomeLetterEvent;
    }

    private long createMyAccount( CrmCapDto inCapDto )
    {
        CRMServiceCode serviceCode = CRMServiceCode.CRM999;
        Session session = null;
        Transaction transaction = null;
        Criteria criteria = null;
        Long generatedId = 0l;
        String status = CRMStatusCode.NEW.getStatusCode();
        try
        {
            session = HibernateUtil.getCurrentSession();
            criteria = session.createCriteria( CrmCustMyAccountPojo.class );
            criteria.add( Restrictions.eq( "customerId", inCapDto.getCustomerDetailsPojo().getCustomerId() ) );
            List<CrmCustMyAccountPojo> crList = criteria.list();
            if ( StringUtils.equals( inCapDto.getCustomerDetailsPojo().getBrand(), IAppConstants.BRAND_INITIA ) )
            {
                status = CRMStatusCode.INACTIVE.getStatusCode();
            }
            if ( !CommonValidator.isValidCollection( crList ) )
            {
                CrmCustMyAccountPojo crmCustMyAccountPojo = new CrmCustMyAccountPojo();
                crmCustMyAccountPojo.setCustomerId( inCapDto.getCustomerDetailsPojo().getCustomerId() );
                crmCustMyAccountPojo
                        .setPassword( EncryptionUtil.encrypt( CRMServiceUtils.getMyAccountRandomPassword() ) );
                crmCustMyAccountPojo.setCreatedTime( Calendar.getInstance().getTime() );
                crmCustMyAccountPojo.setCreatedBy( inCapDto.getUserId() );
                crmCustMyAccountPojo.setStatus( status );
                transaction = session.beginTransaction();
                generatedId = (Long) session.save( crmCustMyAccountPojo );
                transaction.commit();
                LOGGER.info( "Generated My Account ID " + generatedId + " for Customer ID:"
                        + inCapDto.getCustomerDetailsPojo().getCustomerId() );
            }
            serviceCode = CRMServiceCode.CRM001;
        }
        catch ( HibernateException ex )
        {
            LOGGER.error( "HibernateException while Creating My Account Password", ex );
            serviceCode = CRMServiceCode.CRM999;
            CRMServiceUtils.rollback( transaction );
            CRMServiceUtils.closeSessionOnException( session );
        }
        catch ( Exception ex )
        {
            serviceCode = CRMServiceCode.CRM999;
            LOGGER.error( "Getting Error while Creating My Account Password: ", ex );
        }
        finally
        {
            CRMServiceUtils.rollback( transaction );
            CRMServiceUtils.closeSession( session );
            inCapDto.setStatusCode( serviceCode.getStatusCode() );
            inCapDto.setStatusDesc( serviceCode.getStatusDesc() );
            if ( CRMServiceCode.CRM001 == serviceCode )
            {
                HibernateUtil.evictAll( CrmCustMyAccountPojo.class.getName(), null, null );
            }
        }
        return generatedId;
    }

    private CrmAuditLogPojo insertCRFAuditLogs( CrmCapDto inCapDto, CrmActionEnum inEvent )
    {
        StringBuilder oldValues = null;
        StringBuilder newValues = null;
        CrmAuditLogPojo crmAuditLogPojo = null;
        String mappingId = "";
        String event = null;
        String remarks = "";
        try
        {
            if ( StringUtils.isValidObj( inCapDto.getCustomerDetailsPojo() ) )
            {
                oldValues = new StringBuilder();
                newValues = new StringBuilder();
                crmAuditLogPojo = new CrmAuditLogPojo();
                mappingId = String.valueOf( inCapDto.getCustomerDetailsPojo().getRecordId() );
                if ( StringUtils.isValidObj( inEvent ) )
                {
                    if ( inEvent == CrmActionEnum.CUSTOMER_ACTIVATED )
                    {
                        oldValues.append( IAppConstants.NOTAPPLICABLE );
                        newValues.append( "Primary MAC =" )
                                .append( inCapDto.getNetworkConfigurationsPojo().getCurrentCpeMacId() )
                                .append( IAppConstants.COMMA );
                        if ( StringUtils.isValidObj( inCapDto.getNetworkConfigurationsPojo().getCurrentSlaveMacId() ) )
                        {
                            newValues.append( "Secondary MAC =" ).append( inCapDto.getNetworkConfigurationsPojo()
                                                                                  .getCurrentSlaveMacId() );
                        }
                    }
                    else if ( inEvent == CrmActionEnum.MAC_CHNAGE )
                    {
                        oldValues.append( "First Primary MAC =" )
                                .append( inCapDto.getNetworkConfigurationsPojo().getFirstCpeMacId() )
                                .append( IAppConstants.COMMA ).append( "First Secondary MAC =" )
                                .append( inCapDto.getNetworkConfigurationsPojo().getFirstSlaveMacId() );
                        newValues.append( "Primary MAC =" )
                                .append( inCapDto.getNetworkConfigurationsPojo().getCurrentCpeMacId() )
                                .append( IAppConstants.COMMA );
                        if ( StringUtils.isValidObj( inCapDto.getNetworkConfigurationsPojo().getCurrentSlaveMacId() ) )
                        {
                            newValues.append( "Secondary MAC =" ).append( inCapDto.getNetworkConfigurationsPojo()
                                                                                  .getCurrentSlaveMacId() );
                        }
                    }
                    remarks = inEvent.getActionName();
                    event = inEvent.getActionCode();
                }
                else
                {
                    LOGGER.info( "Start creation of [CrmAuditLogPojo] for CAF audits." );
                    if ( StringUtils.isNotBlank( ( inCapDto.getToStage() ) )
                            && !StringUtils.equals( inCapDto.getCustomerDetailsPojo().getCrfPreviousStage(), inCapDto
                                    .getCustomerDetailsPojo().getCrfStage() ) )
                    {
                        oldValues.append( "Stage=" ).append( inCapDto.getCustomerDetailsPojo().getCrfPreviousStage() );
                        newValues.append( "Stage=" ).append( inCapDto.getCustomerDetailsPojo().getCrfStage() );
                        crmAuditLogPojo.setFromBin( inCapDto.getCustomerDetailsPojo().getCrfPreviousStage() );
                        crmAuditLogPojo.setToBin( inCapDto.getCustomerDetailsPojo().getCrfStage() );
                    }
                    if ( StringUtils.isValidObj( inCapDto.getRemarksPojo() ) )
                    {
                        remarks = inCapDto.getRemarksPojo().getRemarks();
                        event = inCapDto.getRemarksPojo().getActions();
                    }
                    else
                    {
                        event = CrmActionEnum.FORWARDED.getActionCode();
                        remarks = CRMEvents.FORWARD_CRF.getEventDescription();
                    }
                    LOGGER.info( "[CrmAuditLogPojo] created for Forward case." );
                }
                crmAuditLogPojo.setOldValues( oldValues.toString() );
                crmAuditLogPojo.setNewValues( newValues.toString() );
                crmAuditLogPojo.setMappingId( mappingId );
                crmAuditLogPojo.setCreatedBy( inCapDto.getUserId() );
                crmAuditLogPojo.setRemarks( remarks );
                crmAuditLogPojo.setEvents( event );
                crmAuditLogPojo.setModule( CRMRequestType.CAF.getRequestCode() );
                crmAuditLogPojo.setClientIp( inCapDto.getClientIPAddress() );
                crmAuditLogPojo.setServerIp( inCapDto.getServerIPAddress() );
                LOGGER.info( "[CrmAuditLogPojo] for CAF audits created successfully." );
            }
        }
        catch ( Exception ex )
        {
            LOGGER.error( "Exception while inserting auditlog for CAF : "
                    + inCapDto.getCustomerDetailsPojo().getRecordId(), ex );
        }
        return crmAuditLogPojo;
    }

    private CrmCustomerDetailsPojo modifyCRFStage( CrmCapDto inCapDto, Session inSession )
    {
        CRMServiceCode statusCode = CRMServiceCode.CRM999;
        CrmCustomerDetailsPojo crmCustomerDetailsPojo = inCapDto.getCustomerDetailsPojo();
        try
        {
            if ( StringUtils.isValidObj( crmCustomerDetailsPojo ) )
            {
                if ( crmCustomerDetailsPojo.getRecordId() > 0 )
                {
                    CrmCustomerDetailsPojo customerDetailsPojoDB = (CrmCustomerDetailsPojo) inSession
                            .get( CrmCustomerDetailsPojo.class, crmCustomerDetailsPojo.getRecordId() );
                    if ( StringUtils.isValidObj( customerDetailsPojoDB ) )
                    {
                        LOGGER.info( "Customer Details Present in DB :: " + customerDetailsPojoDB.getRecordId() );
                        customerDetailsPojoDB.setLastModifiedTime( Calendar.getInstance().getTime() );
                        customerDetailsPojoDB.setLastModifiedBy( crmCustomerDetailsPojo.getCreatedBy() );
                        customerDetailsPojoDB.setStatus( crmCustomerDetailsPojo.getStatus() );
                        //because of dynamic update in hbm
                        if ( StringUtils.isNotBlank( inCapDto.getToStage() )
                                && !StringUtils.equals( inCapDto.getToStage(), customerDetailsPojoDB.getCrfStage() ) )
                        {
                            if ( StringUtils.equals( CRMOperationStages.CANCEL.getCode(), inCapDto.getToStage() ) )
                            {
                                customerDetailsPojoDB.setPermanentDisconnectDate( Calendar.getInstance().getTime() );
                                customerDetailsPojoDB.setLastModifiedBy( inCapDto.getUserId() );
                                if ( StringUtils.isNotEmpty( crmCustomerDetailsPojo.getLinkedCRF() ) )
                                {
                                    customerDetailsPojoDB.setLinkedCRF( crmCustomerDetailsPojo.getLinkedCRF() );
                                }
                            }
                            customerDetailsPojoDB.setCrfPreviousStage( customerDetailsPojoDB.getCrfStage() );
                            customerDetailsPojoDB.setCrfStage( inCapDto.getToStage() );
                            if ( StringUtils.isBlank( customerDetailsPojoDB.getCustomerId() )
                                    || ( StringUtils.numericValue( customerDetailsPojoDB.getCustomerId() ) == 0 ) )
                            {
                                customerDetailsPojoDB.setCustomerId( crmCustomerDetailsPojo.getCustomerId() );
                            }
                            if ( !StringUtils.isValidObj( customerDetailsPojoDB.getFtSubmittionDate() )
                                    && StringUtils.isValidObj( crmCustomerDetailsPojo.getFtSubmittionDate() ) )
                            {
                                customerDetailsPojoDB
                                        .setFtSubmittionDate( crmCustomerDetailsPojo.getFtSubmittionDate() );
                            }
                            crmCustomerDetailsPojo = (CrmCustomerDetailsPojo) inSession.merge( customerDetailsPojoDB );
                            statusCode = CRMServiceCode.CRM001;
                        }
                        else if ( StringUtils.isBlank( customerDetailsPojoDB.getCustomerId() )
                                || ( StringUtils.numericValue( customerDetailsPojoDB.getCustomerId() ) == 0 ) )
                        {
                            customerDetailsPojoDB.setCustomerId( crmCustomerDetailsPojo.getCustomerId() );
                            crmCustomerDetailsPojo = (CrmCustomerDetailsPojo) inSession.merge( customerDetailsPojoDB );
                            statusCode = CRMServiceCode.CRM001;
                        }
                        else if ( !StringUtils.isValidObj( customerDetailsPojoDB.getActivationDate() )
                                && StringUtils.isValidObj( crmCustomerDetailsPojo.getActivationDate() ) )
                        {
                            customerDetailsPojoDB.setActivationDate( crmCustomerDetailsPojo.getActivationDate() );
                            customerDetailsPojoDB.setStatus( CRMStatusCode.ACTIVE.getStatusCode() );
                            crmCustomerDetailsPojo = (CrmCustomerDetailsPojo) inSession.merge( customerDetailsPojoDB );
                            statusCode = CRMServiceCode.CRM313;
                        }
                        else
                        {
                            statusCode = CRMServiceCode.CRM320;
                        }
                    }
                }
            }
        }
        catch ( Exception ex )
        {
            LOGGER.error( "Error in Forward CAF Method :: ", ex );
            statusCode = CRMServiceCode.CRM999;
        }
        finally
        {
            inCapDto.setStatusCode( statusCode.getStatusCode() );
            inCapDto.setStatusDesc( statusCode.getStatusDesc() );
        }
        return crmCustomerDetailsPojo;
    }

    private CrmCapDto changeInboxBinOFCRF( CrmCapDto inCapDto )
        throws SOAPException
    {
        CRMServiceCode crmServiceCode = CRMServiceCode.CRM999;
        String lastCRFStage = null;
        if ( StringUtils.isNotBlank( inCapDto.getCustomerDetailsPojo().getCrfPreviousStage() ) )
        {
            lastCRFStage = inCapDto.getCustomerDetailsPojo().getCrfPreviousStage();
        }
        else
        {
            lastCRFStage = CRMOperationStages.INITIATE.getCode();
        }
        //going for forward CRF to Another User when user is forwarding docket from BP to FT then insert initiate entry in inbox for inactive status
        ConfigDto configDto = new ConfigDto();
        configDto.setFromStage( lastCRFStage );
        configDto.setTostage( inCapDto.getCustomerDetailsPojo().getCrfStage() );
        configDto.setFromUserId( inCapDto.getUserId() );
        configDto.setRequestType( CRMRequestType.CAF.getRequestCode() );
        configDto.setMappingId( inCapDto.getCustomerDetailsPojo().getRecordId() + "" );
        configDto.setUserId( inCapDto.getUserId() );
        configDto.setUserIdChange( IAppConstants.NO );
        boolean updated = getConfigManagerDao().changeInboxBin( configDto );
        if ( updated )
        {
            LOGGER.info( "Successfull changed Bin For CAF ::" + inCapDto.getCustomerDetailsPojo().getCrfId() );
            crmServiceCode = CRMServiceCode.CRM001;
        }
        inCapDto.setStatusCode( crmServiceCode.getStatusCode() );
        inCapDto.setStatusDesc( crmServiceCode.getStatusDesc() );
        return inCapDto;
    }

    @Override
    public CrmCapDto generateInitiateInbox( CrmCapDto inCapDto, Session inSession )
    {
        try
        {
            LOGGER.info( ":: IN insertInobox ::" );
            List<CrmInboxPojo> crmInboxPojos = null;
            Criteria criteria = null;
            LOGGER.info( "Check Inbox Status For Customer Id :: " + inCapDto.getCustomerRecordId() );
            criteria = inSession.createCriteria( CrmInboxPojo.class )
                    .add( Restrictions.eq( "requestType", CRMRequestType.CAF.getRequestCode() ) )
                    .add( Restrictions.eq( "stage", CRMOperationStages.INITIATE.getCode() ) )
                    .add( Restrictions.eq( "mappingId", inCapDto.getCustomerRecordId() + "" ) );
            criteria.setCacheable( true );
            criteria.setCacheRegion( CrmInboxPojo.class.getName() );
            crmInboxPojos = criteria.list();
            if ( !CommonValidator.isValidCollection( crmInboxPojos ) )
            {
                LOGGER.info( "Going To Save CAF in Inbox Pojo" );
                CrmInboxPojo crmInboxPojo = new CrmInboxPojo();
                crmInboxPojo.setUserId( inCapDto.getUserId() );
                crmInboxPojo.setRequestType( CRMRequestType.CAF.getRequestCode() );
                crmInboxPojo.setMappingId( inCapDto.getCustomerRecordId() + "" );
                crmInboxPojo.setStage( CRMOperationStages.INITIATE.getCode() );
                crmInboxPojo.setCreatedBy( inCapDto.getUserId() );
                crmInboxPojo.setCreatedTime( Calendar.getInstance().getTime() );
                crmInboxPojo.setStatus( CRMStatusCode.ACTIVE.getStatusCode() );
                inSession.save( crmInboxPojo );
            }
            else if ( CommonValidator.isValidCollection( crmInboxPojos ) )
            {
                CrmInboxPojo crmInboxPojo = crmInboxPojos.get( 0 );
                if ( StringUtils.equals( CRMOperationStages.FT_REJECT.getCode(), inCapDto.getCustomerDetailsPojo()
                        .getCrfStage() ) )
                {
                    crmInboxPojo.setUserId( null );
                }
                else
                {
                    crmInboxPojo.setUserId( inCapDto.getUserId() );
                }
                crmInboxPojo.setLastModifiedBy( inCapDto.getUserId() );
                crmInboxPojo.setLastModifiedTime( Calendar.getInstance().getTime() );
                crmInboxPojo.setStatus( CRMStatusCode.ACTIVE.getStatusCode() );
                inSession.merge( crmInboxPojo );
            }
            inCapDto.setStatusCode( CRMServiceCode.CRM001.getStatusCode() );
            inCapDto.setStatusDesc( CRMServiceCode.CRM001.getStatusDesc() );
            // Eviction is not required.
        }
        catch ( Exception ex )
        {
            LOGGER.error( "Error in Save CAF Method :: ", ex );
            inCapDto.setStatusCode( CRMServiceCode.CRM999.getStatusCode() );
            inCapDto.setStatusDesc( CRMServiceCode.CRM999.getStatusDesc() );
        }
        return inCapDto;
    }

    @Override
    public CrmCapDto saveCRForISRReferenceNo( CrmCapDto inCrmCapDto )
    {
        SessionFactory sessionFactory = null;
        Session session = null;
        Transaction transaction = null;
        try
        {
            sessionFactory = HibernateUtil.getSessionFactory();
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            if ( inCrmCapDto.getCustomerDetailsPojo().getRecordId() > 0 )
            {
                CrmCustomerDetailsPojo customerDetailsPojoDB = (CrmCustomerDetailsPojo) session
                        .get( CrmCustomerDetailsPojo.class, inCrmCapDto.getCustomerDetailsPojo().getRecordId() );
                if ( StringUtils.isValidObj( customerDetailsPojoDB ) )
                {
                    LOGGER.info( "Customer Details Present in DB :: " + customerDetailsPojoDB.getRecordId() );
                    if ( StringUtils.isNotBlank( inCrmCapDto.getCustomerDetailsPojo().getCrfReferenceNo() ) )
                    {
                        customerDetailsPojoDB.setCrfReferenceNo( inCrmCapDto.getCustomerDetailsPojo()
                                .getCrfReferenceNo() );
                    }
                    if ( StringUtils.isNotBlank( inCrmCapDto.getCustomerDetailsPojo().getIsrReferenceNo() ) )
                    {
                        customerDetailsPojoDB.setIsrReferenceNo( inCrmCapDto.getCustomerDetailsPojo()
                                .getIsrReferenceNo() );
                    }
                    customerDetailsPojoDB.setLastModifiedTime( Calendar.getInstance().getTime() );
                    customerDetailsPojoDB.setLastModifiedBy( inCrmCapDto.getUserId() );
                    session.merge( customerDetailsPojoDB );
                    if ( StringUtils.isValidObj( customerDetailsPojoDB.getActivationDate() ) )
                    {
                        CrmCrfDetailsPojo crmCrfDetailsPojo = (CrmCrfDetailsPojo) session.get( CrmCrfDetailsPojo.class,
                                                                                               customerDetailsPojoDB
                                                                                                       .getRecordId() );
                        if ( StringUtils.isValidObj( crmCrfDetailsPojo ) )
                        {
                            LOGGER.info( "CAF Details Present in DB :: " + crmCrfDetailsPojo.getCrfId() );
                            if ( StringUtils.isNotBlank( inCrmCapDto.getCustomerDetailsPojo().getCrfReferenceNo() ) )
                            {
                                crmCrfDetailsPojo.setCrfReferenceNo( inCrmCapDto.getCustomerDetailsPojo()
                                        .getCrfReferenceNo() );
                            }
                            if ( StringUtils.isNotBlank( inCrmCapDto.getCustomerDetailsPojo().getIsrReferenceNo() ) )
                            {
                                crmCrfDetailsPojo.setIsrReferenceNo( inCrmCapDto.getCustomerDetailsPojo()
                                        .getIsrReferenceNo() );
                            }
                            crmCrfDetailsPojo.setLastModifiedTime( Calendar.getInstance().getTime() );
                            crmCrfDetailsPojo.setLastModifiedBy( inCrmCapDto.getUserId() );
                            session.merge( crmCrfDetailsPojo );
                        }
                    }
                    transaction.commit();
                    LOGGER.info( "Successfully Save CAF Reference No OR ISR Reference No " );
                }
                inCrmCapDto.setStatusCode( CRMServiceCode.CRM001.getStatusCode() );
                inCrmCapDto.setStatusDesc( CRMServiceCode.CRM001.getStatusDesc() );
                // Eviction is not required.
            }
            else
            {
                inCrmCapDto.setStatusCode( CRMServiceCode.CRM997.getStatusCode() );
                inCrmCapDto.setStatusDesc( CRMServiceCode.CRM997.getStatusDesc() );
            }
        }
        catch ( HibernateException ex )
        {
            LOGGER.error( "HibernateException while Save CAF Reference No", ex );
            inCrmCapDto.setStatusCode( CRMServiceCode.CRM999.getStatusCode() );
            CRMServiceUtils.rollback( transaction );
            CRMServiceUtils.closeSessionOnException( session );
        }
        catch ( Exception ex )
        {
            LOGGER.error( "Getting Exception While Save CAF Reference No:: ", ex );
            inCrmCapDto.setStatusCode( CRMServiceCode.CRM999.getStatusCode() );
            inCrmCapDto.setStatusDesc( CRMServiceCode.CRM999.getStatusDesc() );
        }
        finally
        {
            CRMServiceUtils.rollback( transaction );
            CRMServiceUtils.closeSession( session );
        }
        return inCrmCapDto;
    }

    @Override
    public CrmCapDto searchCustomerProfile( CrmCapDto inCrmCapDto )
    {
        SessionFactory sessionFactory = null;
        Session session = null;
        Criteria criteria = null;
        try
        {
            sessionFactory = HibernateUtil.getSessionFactory();
            session = sessionFactory.openSession();
            criteria = session.createCriteria( CrmCustomerDetailsPojo.class );
            if ( StringUtils.isNotBlank( inCrmCapDto.getCustomerDetailsPojo().getCrfId() ) )
            {
                criteria.add( Restrictions.ilike( "crfId", inCrmCapDto.getCustomerDetailsPojo().getCrfId(),
                                                  MatchMode.ANYWHERE ) );
            }
            else if ( StringUtils.isNotBlank( inCrmCapDto.getCustomerDetailsPojo().getCustomerId() ) )
            {
                criteria.add( Restrictions.ilike( "customerId", inCrmCapDto.getCustomerDetailsPojo().getCustomerId(),
                                                  MatchMode.ANYWHERE ) );
            }
            else if ( StringUtils.isNotBlank( inCrmCapDto.getCustomerDetailsPojo().getCustFname() ) )
            {
                criteria.add( Restrictions.ilike( "custFname", inCrmCapDto.getCustomerDetailsPojo().getCustFname(),
                                                  MatchMode.ANYWHERE ) );
            }
            else if ( StringUtils.isNotBlank( inCrmCapDto.getCustomerDetailsPojo().getCustLname() ) )
            {
                criteria.add( Restrictions.ilike( "custLname", inCrmCapDto.getCustomerDetailsPojo().getCustLname(),
                                                  MatchMode.ANYWHERE ) );
            }
            else if ( inCrmCapDto.getCustomerDetailsPojo().getRmn() > 0 )
            {
                criteria.add( Restrictions.eq( "rmn", inCrmCapDto.getCustomerDetailsPojo().getRmn() ) );
            }
            else if ( inCrmCapDto.getCustomerDetailsPojo().getRtn() > 0 )
            {
                criteria.add( Restrictions.eq( "rtn", inCrmCapDto.getCustomerDetailsPojo().getRtn() ) );
            }
            else if ( inCrmCapDto.getCustomerDetailsPojo().getRecordId() > 0 )
            {
                criteria.add( Restrictions.eq( "recordId", inCrmCapDto.getCustomerDetailsPojo().getRecordId() ) );
            }
            else
            {
                if ( StringUtils.isValidObj( inCrmCapDto.getCustomerDetailsPojo().getSafeCustodyDate() ) )
                {
                    criteria.add( Restrictions.le( "safeCustodyDate", inCrmCapDto.getCustomerDetailsPojo()
                            .getSafeCustodyDate() ) );
                }
                if ( StringUtils.isNotBlank( inCrmCapDto.getCustomerDetailsPojo().getStatus() ) )
                {
                    criteria.add( Restrictions.eq( "status", inCrmCapDto.getCustomerDetailsPojo().getStatus() ) );
                }
                if ( StringUtils.isNotBlank( inCrmCapDto.getCustomerDetailsPojo().getCrfStage() ) )
                {
                    criteria.add( Restrictions.eq( "crfStage", inCrmCapDto.getCustomerDetailsPojo().getCrfStage() ) );
                }
            }
            //            if ( inCrmCapDto.getMaxResultSize() > 0 )
            //            {
            //                criteria.setMaxResults( inCrmCapDto.getMaxResultSize() );
            //            }
            criteria.setCacheable( true );
            criteria.setCacheRegion( CrmCustomerDetailsPojo.class.getName() );
            List<CrmCustomerDetailsPojo> crmCustomerDetailsPojos = criteria.list();
            if ( CommonValidator.isValidCollection( crmCustomerDetailsPojos ) )
            {
                crmCustomerDetailsPojos = includeLinkedCustomers( session, crmCustomerDetailsPojos );
                for ( CrmCustomerDetailsPojo crmCustomerDetailsPojo : crmCustomerDetailsPojos )
                {
                    List<CrmInboxPojo> inboxList = null;
                    if ( ! ( StringUtils.equals( crmCustomerDetailsPojo.getCrfStage(),
                                                 CRMOperationStages.ON_BOARD.getCode() ) || StringUtils
                            .equals( crmCustomerDetailsPojo.getCrfStage(), CRMOperationStages.CANCEL.getCode() ) ) )
                    {
                        criteria = session.createCriteria( CrmInboxPojo.class );
                        criteria.add( Restrictions.eq( "mappingId", crmCustomerDetailsPojo.getRecordId() + "" ) )
                                .add( Restrictions.eq( "status", CRMStatusCode.ACTIVE.getStatusCode() ) );
                        inboxList = criteria.list();
                    }
                    if ( CommonValidator.isValidCollection( inboxList ) )
                    {
                        if ( StringUtils.isNotEmpty( inboxList.get( 0 ).getUserId() ) )
                        {
                            crmCustomerDetailsPojo.setCurrentUser( inboxList.get( 0 ).getUserId() );
                        }
                        else
                            crmCustomerDetailsPojo.setCurrentUser( "-" );
                    }
                    else
                        crmCustomerDetailsPojo.setCurrentUser( "-" );
                }
                // For future if there exactly one record found
                //                if ( crmCustomerDetailsPojos.size() == 1 )
                //                {
                //                    inCrmCapDto.setOrderDetailsPojo( CRMServiceUtils
                //                            .getDBValues( CrmOrderDetailsPojo.class, IAppConstants.PARAM_CUSTOMER_RECORD_ID,
                //                                          crmCustomerDetailsPojos.get( 0 ).getRecordId(), session ) );
                //                    inCrmCapDto.setNetworkConfigurationsPojo( CRMServiceUtils
                //                            .getDBValues( CrmNetworkConfigurationsPojo.class, IAppConstants.PARAM_CUSTOMER_RECORD_ID,
                //                                          crmCustomerDetailsPojos.get( 0 ).getRecordId(), session ) );
                //                    if ( !StringUtils.equals( CRMDisplayListConstants.BROADBAND.getCode(),
                //                                              crmCustomerDetailsPojos.get( 0 ).getProduct() ) )
                //                    {
                //                        if ( StringUtils.isValidObj( inCrmCapDto.getNetworkConfigurationsPojo() ) )
                //                        {
                //                            inCrmCapDto.setCrmPartnerNetworkConfig( (CrmPartnerNetworkConfigPojo) session
                //                                    .get( CrmPartnerNetworkConfigPojo.class, StringUtils.numericValue( inCrmCapDto
                //                                            .getNetworkConfigurationsPojo().getOption82() ) ) );
                //                        }
                //                    }
                //                    inCrmCapDto.setNationalityDetailsPojo( CRMServiceUtils
                //                            .getDBValues( CrmNationalityDetailsPojo.class, IAppConstants.PARAM_CUSTOMER_RECORD_ID,
                //                                          crmCustomerDetailsPojos.get( 0 ).getRecordId(), session ) );
                //                }
                inCrmCapDto.setCustomerDetailsPojosList( crmCustomerDetailsPojos );
                inCrmCapDto.setStatusCode( CRMServiceCode.CRM001.getStatusCode() );
                inCrmCapDto.setStatusDesc( CRMServiceCode.CRM001.getStatusDesc() );
            }
            else
            {
                inCrmCapDto.setStatusCode( CRMServiceCode.CRM042.getStatusCode() );
                inCrmCapDto.setStatusDesc( CRMServiceCode.CRM042.getStatusDesc() );
            }
        }
        catch ( Exception ex )
        {
            LOGGER.error( "Error In Get Customer Search list Method ", ex );
            CRMServiceUtils.closeSession( session );
            inCrmCapDto.setStatusCode( CRMServiceCode.CRM999.getStatusCode() );
            inCrmCapDto.setStatusDesc( CRMServiceCode.CRM999.getStatusDesc() );
        }
        finally
        {
            CRMServiceUtils.closeSession( session );
        }
        return inCrmCapDto;
    }

    private List<CrmCustomerDetailsPojo> includeLinkedCustomers( Session session,
                                                                 List<CrmCustomerDetailsPojo> crmCustomerDetailsPojos )
    {
        Set<String> linkedCrfs = new HashSet<String>();
        Set<String> allCrfs = new HashSet<String>();
        for ( CrmCustomerDetailsPojo crmCustomerDetailsPojo : crmCustomerDetailsPojos )
        {
            allCrfs.add( crmCustomerDetailsPojo.getCrfId() );
            if ( StringUtils.isNotBlank( crmCustomerDetailsPojo.getLinkedCRF() ) )
            {
                linkedCrfs.add( crmCustomerDetailsPojo.getLinkedCRF() );
            }
        }
        linkedCrfs.removeAll( allCrfs );
        if ( CommonValidator.isValidCollection( linkedCrfs ) )
        {
            Criteria criteria = session.createCriteria( CrmCustomerDetailsPojo.class );
            criteria.add( Restrictions.in( "crfId", linkedCrfs ) );
            criteria.setCacheable( true );
            criteria.setCacheRegion( CrmCustomerDetailsPojo.class.getName() );
            crmCustomerDetailsPojos.addAll( criteria.list() );
        }
        return crmCustomerDetailsPojos;
    }

    @Override
    public CrmCapDto saveCustomerProfileDetails( CrmCapDto inCrmCapDto )
    {
        Session session = null;
        Transaction transaction = null;
        boolean billingFlag = false;
        try
        {
            session = HibernateUtil.getCurrentSession();
            transaction = session.beginTransaction();
            Criteria criteria = null;
            CrmCustomerActivityPojo activityLog = new CrmCustomerActivityPojo();
            LOGGER.info( "Customer id :: " + inCrmCapDto.getCustomerId() + " / Performed Action :: "
                    + inCrmCapDto.getActivityAction() );
            CRMCustomerActivityActions activityActions = CRMCustomerActivityActions.getObjByActionDesc( inCrmCapDto
                    .getActivityAction() );
            if ( StringUtils.isValidObj( inCrmCapDto.getCustomerDetailsPojo() ) )
            {
                if ( inCrmCapDto.getCustomerDetailsPojo().getRecordId() > 0 )
                {
                    CrmCustomerDetailsPojo crmCustomerDetailsPojo = (CrmCustomerDetailsPojo) session
                            .get( CrmCustomerDetailsPojo.class, inCrmCapDto.getCustomerDetailsPojo().getRecordId() );
                    if ( StringUtils.isValidObj( crmCustomerDetailsPojo ) )
                    {
                        billingFlag = updateCustomerBillingCall( inCrmCapDto.getCustomerDetailsPojo(),
                                                                 StringUtils.numericValue( inCrmCapDto.getCustomerId() ),
                                                                 inCrmCapDto );
                        if ( billingFlag )
                        {
                            CRMServiceUtils.fillActivityDetails( crmCustomerDetailsPojo,
                                                                 inCrmCapDto.getCustomerDetailsPojo(), activityLog,
                                                                 activityActions );
                            CRMUtils.copyPropertyValue( crmCustomerDetailsPojo, inCrmCapDto.getCustomerDetailsPojo(),
                                                        null );
                            crmCustomerDetailsPojo.setLastModifiedTime( Calendar.getInstance().getTime() );
                            session.merge( crmCustomerDetailsPojo );
                            // Eviction is not required.
                        }
                    }
                }
            }
            else if ( StringUtils.isValidObj( inCrmCapDto.getAddressDetailsPojo() ) )
            {
                CrmAddressDetailsPojo crmAddrDetailsPojo = null;
                if ( inCrmCapDto.getAddressDetailsPojo().getRecordId() > 0 )
                {
                    crmAddrDetailsPojo = (CrmAddressDetailsPojo) session.get( CrmAddressDetailsPojo.class, inCrmCapDto
                            .getAddressDetailsPojo().getRecordId() );
                }
                else
                {
                    criteria = session.createCriteria( CrmCustomerDetailsPojo.class );
                    criteria.add( Restrictions.eq( "customerId", inCrmCapDto.getCustomerId() ) );
                    List<CrmCustomerDetailsPojo> crmCustomerDetailsPojos = criteria.list();
                    if ( CommonValidator.isValidCollection( crmCustomerDetailsPojos ) )
                    {
                        Set<CrmAddressDetailsPojo> addressDetailsPojos = crmCustomerDetailsPojos.get( 0 )
                                .getCrmAddressDetailses();
                        if ( CommonValidator.isValidCollection( addressDetailsPojos ) )
                        {
                            for ( CrmAddressDetailsPojo addressDetailsPojo : addressDetailsPojos )
                            {
                                if ( StringUtils.equals( addressDetailsPojo.getAddressType(),
                                                         IAppConstants.ADDRESS_TYPE_BILLING ) )
                                {
                                    crmAddrDetailsPojo = addressDetailsPojo;
                                    break;
                                }
                            }
                        }
                    }
                }
                if ( StringUtils.isValidObj( crmAddrDetailsPojo ) )
                {
                    CRMServiceUtils.fillActivityDetails( crmAddrDetailsPojo, inCrmCapDto.getAddressDetailsPojo(),
                                                         activityLog, activityActions );
                    if ( StringUtils.equals( crmAddrDetailsPojo.getAddressType(),
                                             IAppConstants.ADDRESS_TYPE_INSTALLATION ) )
                    {
                        crmAddrDetailsPojo.setAddLine1( StringUtils.substring( inCrmCapDto.getAddressDetailsPojo()
                                .getAddLine1(), 0, 60 ) );
                    }
                    else
                    {
                        CRMUtils.copyPropertyValue( crmAddrDetailsPojo, inCrmCapDto.getAddressDetailsPojo(), null );
                        crmAddrDetailsPojo
                                .setAddLine1( StringUtils.substring( crmAddrDetailsPojo.getAddLine1(), 0, 60 ) );
                        crmAddrDetailsPojo
                                .setAddLine2( StringUtils.substring( crmAddrDetailsPojo.getAddLine2(), 0, 60 ) );
                        crmAddrDetailsPojo
                                .setAddLine3( StringUtils.substring( crmAddrDetailsPojo.getAddLine3(), 0, 60 ) );
                    }
                    billingFlag = updateCustomerBillingCall( crmAddrDetailsPojo,
                                                             StringUtils.numericValue( inCrmCapDto.getCustomerId() ),
                                                             inCrmCapDto );
                    if ( billingFlag )
                    {
                        crmAddrDetailsPojo.setLastModifiedTime( Calendar.getInstance().getTime() );
                        session.merge( crmAddrDetailsPojo );
                        // Eviction is not required.
                    }
                }
            }
            else if ( StringUtils.isValidObj( inCrmCapDto.getNationalityDetailsPojo() ) )
            {
                if ( inCrmCapDto.getNationalityDetailsPojo().getRecordId() > 0 )
                {
                    CrmNationalityDetailsPojo crmNationalityDetailsPojo = (CrmNationalityDetailsPojo) session
                            .get( CrmNationalityDetailsPojo.class, inCrmCapDto.getNationalityDetailsPojo()
                                    .getRecordId() );
                    if ( StringUtils.isValidObj( crmNationalityDetailsPojo ) )
                    {
                        billingFlag = updateCustomerBillingCall( inCrmCapDto.getNationalityDetailsPojo(),
                                                                 StringUtils.numericValue( inCrmCapDto.getCustomerId() ),
                                                                 inCrmCapDto );
                        if ( billingFlag )
                        {
                            CRMServiceUtils.fillActivityDetails( crmNationalityDetailsPojo,
                                                                 inCrmCapDto.getNationalityDetailsPojo(), activityLog,
                                                                 activityActions );
                            CRMUtils.copyPropertyValue( crmNationalityDetailsPojo,
                                                        inCrmCapDto.getNationalityDetailsPojo(), null );
                            crmNationalityDetailsPojo.setLastModifiedTime( Calendar.getInstance().getTime() );
                            session.merge( crmNationalityDetailsPojo );
                            // Eviction is not required.
                        }
                    }
                }
            }
            else if ( StringUtils.isValidObj( inCrmCapDto.getPlanDetailsPojo() ) )
            {
                if ( inCrmCapDto.getPlanDetailsPojo().getRecordId() > 0 )
                {
                    CrmPlanDetailsPojo crmPlanDetailsPojo = (CrmPlanDetailsPojo) session
                            .get( CrmPlanDetailsPojo.class, inCrmCapDto.getPlanDetailsPojo().getRecordId() );
                    if ( StringUtils.isValidObj( crmPlanDetailsPojo ) )
                    {
                        billingFlag = updateCustomerBillingCall( inCrmCapDto.getPlanDetailsPojo(),
                                                                 StringUtils.numericValue( inCrmCapDto.getCustomerId() ),
                                                                 inCrmCapDto );
                        if ( billingFlag )
                        {
                            CRMServiceUtils.fillActivityDetails( crmPlanDetailsPojo, inCrmCapDto.getPlanDetailsPojo(),
                                                                 activityLog, activityActions );
                            CRMUtils.copyPropertyValue( crmPlanDetailsPojo, inCrmCapDto.getPlanDetailsPojo(), null );
                            crmPlanDetailsPojo.setLastModifiedTime( Calendar.getInstance().getTime() );
                            session.merge( crmPlanDetailsPojo );
                            // Eviction is not required.
                        }
                    }
                }
            }
            else if ( StringUtils.isValidObj( inCrmCapDto.getPaymentDetailsPojo() ) )
            {
                if ( inCrmCapDto.getPaymentDetailsPojo().getPaymentId() > 0 )
                {
                    CrmPaymentDetailsPojo paymentDetailsPojo = (CrmPaymentDetailsPojo) session
                            .get( CrmPaymentDetailsPojo.class, inCrmCapDto.getPaymentDetailsPojo().getPaymentId() );
                    if ( StringUtils.isValidObj( paymentDetailsPojo ) )
                    {
                        inCrmCapDto.getPaymentDetailsPojo().setDeposit( BigDecimal.valueOf( inCrmCapDto
                                                                                .getPaymentDetailsPojo()
                                                                                .getSecurityCharges() ) );
                        billingFlag = updateCustomerBillingCall( inCrmCapDto.getPaymentDetailsPojo(),
                                                                 StringUtils.numericValue( inCrmCapDto.getCustomerId() ),
                                                                 inCrmCapDto );
                        if ( billingFlag )
                        {
                            CRMServiceUtils.fillActivityDetails( paymentDetailsPojo,
                                                                 inCrmCapDto.getPaymentDetailsPojo(), activityLog,
                                                                 activityActions );
                            paymentDetailsPojo.setSecurityCharges( inCrmCapDto.getPaymentDetailsPojo()
                                    .getSecurityCharges() );
                            paymentDetailsPojo.setLastModifiedBy( inCrmCapDto.getUserId() );
                            paymentDetailsPojo.setLastModifiedTime( Calendar.getInstance().getTime() );
                            session.merge( paymentDetailsPojo );
                            // Eviction is not required.
                        }
                    }
                }
            }
            /*Calling billing API
             *
             * Create Method to call Billing API
             *
             * */
            if ( billingFlag )
            {
                transaction.commit();
                // ---- Going to generate ROL tickets -----
                QRCRolCategories action = QRCRolCategories.getQRCCategoriesByEvent( inCrmCapDto.getChangeRequest() );
                if ( StringUtils.isValidObj( action ) && action.getResolutionType() >= 0 )
                {
                    CrmQrcDto tmpDto = CRMServiceUtils.generateAutoTicket( inCrmCapDto.getCustomerId(), action, null,
                                                                           inCrmCapDto.getUserId(),
                                                                           "Updating Customer Details" );
                    if ( StringUtils.equals( tmpDto.getStatusCode(), CRMServiceCode.CRM001.getStatusCode() ) )
                    {
                        if ( StringUtils.isValidObj( tmpDto.getCrmSrTicketsPojo() ) )
                        {
                            inCrmCapDto.setGeneratedTicketId( tmpDto.getCrmSrTicketsPojo().getSrId() );
                            activityLog.setTicketId( tmpDto.getCrmSrTicketsPojo().getSrId() );
                        }
                    }
                }
                // ---- Insert customer activity log -----
                preparedActivityLogPojo( activityLog, inCrmCapDto.getCustomerId(), inCrmCapDto.getUserId(),
                                         inCrmCapDto.getActivityAction(), null, inCrmCapDto.getClientIPAddress(),
                                         inCrmCapDto.getServerIPAddress() );
                CRMServiceUtils.createActivityLog( activityLog );
                // ----- Send Email verification -----
                if ( StringUtils.equals( inCrmCapDto.getActivityAction(),
                                         CRMCustomerActivityActions.EMAIL_MODIFY.getActionDesc() ) )
                {
                    if ( !StringUtils.equals( inCrmCapDto.getCustomerDetailsPojo().getBrand(),
                                              IAppConstants.BRAND_INITIA ) )
                    {
                        if ( updateToken( inCrmCapDto.getCustomerId(), inCrmCapDto.getUserId() )
                                && CRMServiceUtils.sendAlerts( CRMEvents.REGISTERED_EMAIL_VERIFICATION,
                                                               CRMRequestType.CUSTOMER_ID, inCrmCapDto.getCustomerId(),
                                                               null ) )
                        {
                            CrmCustomerActivityPojo activityLogPojo = new CrmCustomerActivityPojo();
                            activityLogPojo.setAction( CRMCustomerActivityActions.EMAIL_VERIFICATION_LINK_SENT
                                    .getActionCode() );
                            activityLogPojo.setCustomerId( inCrmCapDto.getCustomerId() );
                            activityLogPojo.setOldValue( IAppConstants.DASH );
                            activityLogPojo.setNewValue( CRMEvents.REGISTERED_EMAIL_VERIFICATION.getEventDescription() );
                            activityLogPojo.setCreatedBy( inCrmCapDto.getUserId() );
                            activityLogPojo.setReason( inCrmCapDto.getActivityAction() );
                            activityLogPojo.setServiceIp( CRMUtils.getServerIp() );
                            activityLogPojo.setClientIp( inCrmCapDto.getClientIPAddress() );
                            activityLogPojo.setServerIp( inCrmCapDto.getServerIPAddress() );
                            CRMServiceUtils.createActivityLog( activityLogPojo );
                        }
                    }
                }
                inCrmCapDto.setStatusCode( CRMServiceCode.CRM001.getStatusCode() );
                inCrmCapDto.setStatusDesc( CRMServiceCode.CRM001.getStatusDesc() );
            }
            else
            {
                CRMServiceUtils.rollback( transaction );
            }
        }
        catch ( HibernateException ex )
        {
            LOGGER.error( "HibernateException occured while saveCustomerProfileDetails", ex );
            inCrmCapDto.setStatusCode( CRMServiceCode.CRM999.getStatusCode() );
            CRMServiceUtils.rollback( transaction );
            CRMServiceUtils.closeSessionOnException( session );
        }
        catch ( Exception ex )
        {
            LOGGER.error( "Error In saveCustomerProfileDetails Method ", ex );
            CRMServiceUtils.rollback( transaction );
            inCrmCapDto.setStatusCode( CRMServiceCode.CRM999.getStatusCode() );
            inCrmCapDto.setStatusDesc( CRMServiceCode.CRM999.getStatusDesc() );
        }
        finally
        {
            CRMServiceUtils.closeSession( session );
        }
        return inCrmCapDto;
    }

    private <E> boolean updateCustomerBillingCall( E e, long customerNo, CrmCapDto inCrmCapDto )
    {
        CrmBillingDto crmBillingDto = null;
        if ( e instanceof CrmAddressDetailsPojo )
        {
            CrmAddressDetailsPojo crmAddressDetailsPojo = (CrmAddressDetailsPojo) e;
            if ( StringUtils.isValidObj( crmAddressDetailsPojo.getStateId() ) && crmAddressDetailsPojo.getStateId() > 0 )
            {
                StatePojo stPojo = CRMServiceUtils.getDBValues( StatePojo.class, crmAddressDetailsPojo.getStateId() );
                if ( StringUtils.isValidObj( stPojo ) )
                {
                    crmAddressDetailsPojo.setStateName( stPojo.getStateName() );
                    if ( StringUtils.isValidObj( crmAddressDetailsPojo.getCityId() )
                            && crmAddressDetailsPojo.getCityId() > 0 )
                    {
                        CityPojo cityPojo = CRMServiceUtils.getDBValues( CityPojo.class,
                                                                         crmAddressDetailsPojo.getCityId() );
                        if ( StringUtils.isValidObj( cityPojo ) )
                        {
                            crmAddressDetailsPojo.setCityName( cityPojo.getCityName() );
                        }
                    }
                }
            }
            crmBillingDto = getCrmBillingManager().changeAddress( crmAddressDetailsPojo );
        }
        else
        {
            crmBillingDto = getCrmBillingManager().updateCustomer( e, customerNo );
        }
        inCrmCapDto.setBillingErrorCode( crmBillingDto.getBillingErrorCode() );
        inCrmCapDto.setStatusCode( crmBillingDto.getStatusCode() );
        inCrmCapDto.setStatusDesc( crmBillingDto.getStatusDesc() );
        return StringUtils.equals( crmBillingDto.getStatusCode(), CRMServiceCode.CRM001.getStatusCode() ) ? true
                                                                                                         : false;
        //StringUtils.updateCustomerBillingCall(  )
    }

    private void preparedActivityLogPojo( CrmCustomerActivityPojo activityLog,
                                          String customerId,
                                          String userId,
                                          String activityAction,
                                          String reason,
                                          String clientIPAddress,
                                          String serverIPAddress )
    {
        activityLog.setCustomerId( customerId );
        activityLog.setAction( activityAction );
        activityLog.setReason( reason );
        activityLog.setCreatedBy( userId );
        activityLog.setServiceIp( CRMUtils.getServerIp() );
        activityLog.setClientIp( clientIPAddress );
        activityLog.setServerIp( serverIPAddress );
    }

    @Override
    public boolean updateToken( String inCustomerId, String inUser )
    {
        SessionFactory sessionFactory = null;
        Session session = null;
        Transaction transaction = null;
        boolean toProcess = false;
        try
        {
            sessionFactory = HibernateUtil.getSessionFactory();
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            if ( StringUtils.isNotBlank( inCustomerId ) )
            {
                CrmCustomerDetailsPojo crmCustomerDetailsPojo = CRMServiceUtils
                        .getDBValues( CrmCustomerDetailsPojo.class, "customerId", inCustomerId, session );
                if ( StringUtils.isValidObj( crmCustomerDetailsPojo ) )
                {
                    crmCustomerDetailsPojo.setLastModifiedTime( Calendar.getInstance().getTime() );
                    crmCustomerDetailsPojo.setLastModifiedBy( inUser );
                    crmCustomerDetailsPojo.setEmailValidationFlag( CRMStatusCode.NEW.getStatusCode() );
                    crmCustomerDetailsPojo.setTokenDate( Calendar.getInstance().getTime() );
                    crmCustomerDetailsPojo.setCustToken( CRMServiceUtils.generateCustToken( inCustomerId ) );
                    transaction.commit();
                    toProcess = true;
                    LOGGER.info( "Successfully Update Token" );
                }
            }
        }
        catch ( HibernateException ex )
        {
            LOGGER.error( "HibernateException occured while Update Token Method ", ex );
            CRMServiceUtils.rollback( transaction );
            CRMServiceUtils.closeSessionOnException( session );
        }
        catch ( Exception ex )
        {
            LOGGER.error( "Error In Update Token Method :: ", ex );
            CRMServiceUtils.rollback( transaction );
        }
        finally
        {
            CRMServiceUtils.closeSession( session );
        }
        return toProcess;
    }

    @Override
    public CRMServiceCode validateEmailToken( String inToken )
    {
        SessionFactory sessionFactory = null;
        Session session = null;
        Transaction transaction = null;
        CRMServiceCode statusCode = CRMServiceCode.CRM997;
        CrmBillingDto billingDto = new CrmBillingDto();
        try
        {
            sessionFactory = HibernateUtil.getSessionFactory();
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            if ( StringUtils.isNotBlank( inToken ) )
            {
                LOGGER.info( "Customer Token :: " + inToken );
                CrmCustomerDetailsPojo crmCustomerDetailsPojo = CRMServiceUtils
                        .getDBValues( CrmCustomerDetailsPojo.class, "custToken", inToken, session );
                if ( StringUtils.isValidObj( crmCustomerDetailsPojo ) )
                {
                    if ( StringUtils.equals( crmCustomerDetailsPojo.getEmailValidationFlag(), IAppConstants.Y ) )
                    {
                        LOGGER.info( "Your email id is already verified." );
                        statusCode = CRMServiceCode.CRM328;
                    }
                    else if ( DateUtils.daysDiff( crmCustomerDetailsPojo.getTokenDate(), Calendar.getInstance()
                            .getTime() ) == 0 )
                    {
                        crmCustomerDetailsPojo.setEmailValidationFlag( IAppConstants.EMAIL_FLAG_VALIDATED );
                        /*crmCustomerDetailsPojo.setCustToken( null );
                        crmCustomerDetailsPojo.setTokenDate( null );*/
                        billingDto = getCrmBillingManager().updateCustomer( crmCustomerDetailsPojo,
                                                                            Long.parseLong( crmCustomerDetailsPojo
                                                                                    .getCustomerId() ) );
                        if ( StringUtils.equals( CRMServiceCode.CRM001.getStatusCode(), billingDto.getStatusCode() ) )
                        {
                            crmCustomerDetailsPojo.setEmailValidationFlag( IAppConstants.Y );
                            transaction.commit();
                            statusCode = CRMServiceCode.CRM001;
                            LOGGER.info( "Successfully validated Token" );
                        }
                        else
                        {
                            statusCode = CRMServiceCode.valueOf( billingDto.getStatusCode() );
                            LOGGER.info( "Status Code ::" + statusCode + ",Billing Error Code::"
                                    + billingDto.getBillingErrorCode() );
                        }
                    }
                    else
                    {
                        LOGGER.info( "Token has been expired" );
                        statusCode = CRMServiceCode.CRM327;
                    }
                }
                else
                {
                    LOGGER.info( "Token is not valid" );
                    statusCode = CRMServiceCode.CRM326;
                }
            }
        }
        catch ( HibernateException ex )
        {
            LOGGER.error( "HibernateException occured while validate Token Method", ex );
            statusCode = CRMServiceCode.CRM999;
            CRMServiceUtils.rollback( transaction );
            CRMServiceUtils.closeSessionOnException( session );
        }
        catch ( Exception ex )
        {
            LOGGER.error( "Error In validate Token Method :: ", ex );
            statusCode = CRMServiceCode.CRM999;
            CRMServiceUtils.rollback( transaction );
        }
        finally
        {
            CRMServiceUtils.closeSession( session );
        }
        return statusCode;
    }

    @Override
    public CrmCapDto searchCustomerBySociety( CrmCapDto inCrmCapDto )
    {
        SessionFactory sessionFactory = null;
        Session session = null;
        CrmCustomerDetailsPojo crmCustomerDetailsPojo = null;
        List<CrmAddressDetailsPojo> crmAddressDetailsPojoList = null;
        List<CommonCustDetailsPojo> commonCustDetailsPojos = null;
        CRMServiceCode statusCode = CRMServiceCode.CRM999;
        CommonCustDetailsPojo commonCustDetailsPojo = null;
        try
        {
            sessionFactory = HibernateUtil.getSessionFactory();
            session = sessionFactory.openSession();
            commonCustDetailsPojos = new ArrayList<CommonCustDetailsPojo>();
            LOGGER.info( "Society ID:" + inCrmCapDto.getAddressDetailsPojo().getInstSocietyId() );
            Map<String, Object> criteriaMap = new HashMap<String, Object>();
            criteriaMap.put( "instSocietyId", inCrmCapDto.getAddressDetailsPojo().getInstSocietyId() );
            criteriaMap.put( "addressType", IAppConstants.ADDRESS_TYPE_INSTALLATION );
            crmAddressDetailsPojoList = CRMServiceUtils.getDBValueList( CrmAddressDetailsPojo.class, criteriaMap,
                                                                        session );
            if ( CommonValidator.isValidCollection( crmAddressDetailsPojoList ) )
            {
                LOGGER.info( "Size of AddressDetailsPojo:" + crmAddressDetailsPojoList.size() );
                for ( CrmAddressDetailsPojo addressDetailsPojo : crmAddressDetailsPojoList )
                {
                    LOGGER.info( "Customer ID:" + addressDetailsPojo.getCrmCustomerDetails().getCustomerId() );
                    crmCustomerDetailsPojo = addressDetailsPojo.getCrmCustomerDetails();
                    if ( StringUtils.equals( crmCustomerDetailsPojo.getStatus(), CRMStatusCode.BARRED.getStatusCode() )
                            || StringUtils
                                    .equals( crmCustomerDetailsPojo.getStatus(), CRMStatusCode.SC.getStatusCode() )
                            || StringUtils.equals( crmCustomerDetailsPojo.getStatus(),
                                                   CRMStatusCode.TDC.getStatusCode() ) )
                    {
                        commonCustDetailsPojo = new CommonCustDetailsPojo();
                        commonCustDetailsPojo.setInstAddress( addressDetailsPojo.getAddLine1() );
                        commonCustDetailsPojo.setCrfId( crmCustomerDetailsPojo.getCrfId() );
                        commonCustDetailsPojo
                                .setCustName( crmCustomerDetailsPojo.getCustFname()
                                        + ( StringUtils.isNotBlank( crmCustomerDetailsPojo.getCustLname() )
                                                                                                           ? ( IAppConstants.SPACE + crmCustomerDetailsPojo
                                                                                                                   .getCustLname() )
                                                                                                           : IAppConstants.SPACE ) );
                        commonCustDetailsPojo.setCustomerId( crmCustomerDetailsPojo.getCustomerId() );
                        commonCustDetailsPojo.setStatus( crmCustomerDetailsPojo.getStatus() );
                        SocietyPojo societyPojo = (SocietyPojo) session.get( SocietyPojo.class,
                                                                             addressDetailsPojo.getInstSocietyId() );
                        if ( StringUtils.isValidObj( societyPojo ) )
                        {
                            commonCustDetailsPojo.setSocietyName( societyPojo.getSocietyName() );
                        }
                        CrmNetworkConfigurationsPojo configurationsPojo = CRMServiceUtils
                                .getDBValues( CrmNetworkConfigurationsPojo.class,
                                              IAppConstants.PARAM_CUSTOMER_RECORD_ID,
                                              crmCustomerDetailsPojo.getRecordId(), session );
                        if ( StringUtils.isValidObj( configurationsPojo ) )
                        {
                            commonCustDetailsPojo.setFirstCpeMacId( configurationsPojo.getCurrentCpeMacId() );
                        }
                        commonCustDetailsPojos.add( commonCustDetailsPojo );
                    }
                }
                if ( CommonValidator.isValidCollection( commonCustDetailsPojos ) )
                {
                    LOGGER.info( "Size of CustomerDetailsPojos:" + commonCustDetailsPojos.size() );
                    inCrmCapDto.setCommonCustDetailsPojos( commonCustDetailsPojos );
                    statusCode = CRMServiceCode.CRM001;
                }
                else
                {
                    LOGGER.info( "No Customer Present In DataBase For Particular Society ::"
                            + inCrmCapDto.getAddressDetailsPojo().getInstSocietyId() );
                    statusCode = CRMServiceCode.CRM318;
                }
            }
            else
            {
                LOGGER.info( "No Customer Present In DataBase For Particular Society ::"
                        + inCrmCapDto.getAddressDetailsPojo().getInstSocietyId() );
                statusCode = CRMServiceCode.CRM318;
            }
        }
        catch ( Exception ex )
        {
            LOGGER.error( "Error In Get searchCustomerBySociety list Method ", ex );
            CRMServiceUtils.closeSession( session );
            statusCode = CRMServiceCode.CRM999;
        }
        finally
        {
            CRMServiceUtils.closeSession( session );
            inCrmCapDto.setStatusCode( statusCode.getStatusCode() );
            inCrmCapDto.setStatusDesc( statusCode.getStatusDesc() );
        }
        return inCrmCapDto;
    }

    @Override
    public CrmCapDto searchCustomerByMacAddress( CrmCapDto inCrmCapDto )
    {
        LOGGER.info( "Inside CAPOperationaDaoImpl, searchCustomerByMacAddress" );
        SessionFactory sessionFactory = null;
        Session session = null;
        Criteria criteria = null;
        try
        {
            sessionFactory = HibernateUtil.getSessionFactory();
            session = sessionFactory.openSession();
            criteria = session.createCriteria( CrmCustomerDetailsPojo.class );
            List<CrmCustomerDetailsPojo> crmCustomerDetailsPojos = new ArrayList<CrmCustomerDetailsPojo>();
            CrmNetworkConfigurationsPojo crmNetworkConfigurationsPojo = inCrmCapDto.getNetworkConfigurationsPojo();
            CrmCustomerDetailsPojo customerDetailsPojo = new CrmCustomerDetailsPojo();
            if ( StringUtils.isValidObj( crmNetworkConfigurationsPojo ) )
            {
                List<CrmNetworkConfigurationsPojo> configurationsPojos = CRMServiceUtils
                        .getDBValuesIgnoreCase( CrmNetworkConfigurationsPojo.class, "currentCpeMacId", inCrmCapDto
                                .getNetworkConfigurationsPojo().getFirstCpeMacId(), session, "currentSlaveMacId" );
                if ( StringUtils.isValidObj( configurationsPojos ) )
                {
                    for ( CrmNetworkConfigurationsPojo configurationsPojo : configurationsPojos )
                    {
                        if ( configurationsPojo.getCustomerRecordId() > 0 )
                        {
                            customerDetailsPojo = CRMServiceUtils
                                    .getDBValues( CrmCustomerDetailsPojo.class, "recordId",
                                                  configurationsPojo.getCustomerRecordId(), session );
                        }
                        crmCustomerDetailsPojos.add( customerDetailsPojo );
                    }
                    if ( CommonValidator.isValidCollection( crmCustomerDetailsPojos ) )
                    {
                        crmCustomerDetailsPojos = includeLinkedCustomers( session, crmCustomerDetailsPojos );
                        for ( CrmCustomerDetailsPojo crmCustomerDetailsPojo : crmCustomerDetailsPojos )
                        {
                            List<CrmInboxPojo> inboxList = null;
                            if ( ! ( StringUtils.equals( crmCustomerDetailsPojo.getCrfStage(),
                                                         CRMOperationStages.ON_BOARD.getCode() ) || StringUtils
                                    .equals( crmCustomerDetailsPojo.getCrfStage(), CRMOperationStages.CANCEL.getCode() ) ) )
                            {
                                criteria = session.createCriteria( CrmInboxPojo.class );
                                criteria.add( Restrictions.eq( "mappingId", crmCustomerDetailsPojo.getRecordId() + "" ) )
                                        .add( Restrictions.eq( "status", CRMStatusCode.ACTIVE.getStatusCode() ) );
                                inboxList = criteria.list();
                            }
                            if ( CommonValidator.isValidCollection( inboxList ) )
                            {
                                if ( StringUtils.isNotEmpty( inboxList.get( 0 ).getUserId() ) )
                                {
                                    crmCustomerDetailsPojo.setCurrentUser( inboxList.get( 0 ).getUserId() );
                                }
                                else
                                    crmCustomerDetailsPojo.setCurrentUser( "-" );
                            }
                            else
                                crmCustomerDetailsPojo.setCurrentUser( "-" );
                        }
                        inCrmCapDto.setCustomerDetailsPojosList( crmCustomerDetailsPojos );
                        inCrmCapDto.setStatusCode( CRMServiceCode.CRM001.getStatusCode() );
                        inCrmCapDto.setStatusDesc( CRMServiceCode.CRM001.getStatusDesc() );
                    }
                }
                else
                {
                    inCrmCapDto.setStatusCode( CRMServiceCode.CRM042.getStatusCode() );
                    inCrmCapDto.setStatusDesc( CRMServiceCode.CRM042.getStatusDesc() );
                }
            }
        }
        catch ( Exception ex )
        {
            LOGGER.error( "Error In Get Customer Search list Method ", ex );
            CRMServiceUtils.closeSession( session );
            inCrmCapDto.setStatusCode( CRMServiceCode.CRM999.getStatusCode() );
            inCrmCapDto.setStatusDesc( CRMServiceCode.CRM999.getStatusDesc() );
        }
        finally
        {
            CRMServiceUtils.closeSession( session );
        }
        return inCrmCapDto;
    }
}