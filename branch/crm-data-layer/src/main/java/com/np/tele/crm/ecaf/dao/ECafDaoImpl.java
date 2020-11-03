package com.np.tele.crm.ecaf.dao;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
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
import org.hibernate.criterion.Example;
import org.hibernate.criterion.Restrictions;

import com.np.tele.crm.constants.CRMDisplayListConstants;
import com.np.tele.crm.constants.CRMOperationStages;
import com.np.tele.crm.constants.CRMParameter;
import com.np.tele.crm.constants.CRMRCAReason;
import com.np.tele.crm.constants.CRMRequestType;
import com.np.tele.crm.constants.CRMServiceCode;
import com.np.tele.crm.constants.CRMStatusCode;
import com.np.tele.crm.constants.IAppConstants;
import com.np.tele.crm.constants.IGlobalConstants;
import com.np.tele.crm.constants.ServiceParameter;
import com.np.tele.crm.dto.ECafDto;
import com.np.tele.crm.dto.MasterDataDto;
import com.np.tele.crm.exceptions.DuplicateRecieptException;
import com.np.tele.crm.ext.pojos.FileUploader;
import com.np.tele.crm.locator.CRMServicesProxy;
import com.np.tele.crm.pojos.CrmCustAadharNumberPojo;
import com.np.tele.crm.pojos.CrmCustomerDetailsPojo;
import com.np.tele.crm.pojos.CrmDocumentDetailsPojo;
import com.np.tele.crm.pojos.CrmInboxPojo;
import com.np.tele.crm.pojos.CrmPaymentDetailsPojo;
import com.np.tele.crm.pojos.CrmPlanDetailsPojo;
import com.np.tele.crm.pojos.CrmRcaReasonPojo;
import com.np.tele.crm.pojos.NpUploads;
import com.np.tele.crm.utils.CRMServiceUtils;
import com.np.tele.crm.utils.CrmDaoHelper;
import com.np.tele.crm.utils.HibernateUtil;
import com.np.tele.crm.utils.PojoUtils;
import com.np.tele.crm.utils.PropertyUtils;
import com.np.tele.crm.utils.StringUtils;
import com.np.validator.util.CommonValidator;

public class ECafDaoImpl
    implements IECafDao
{
    private static final Logger LOGGER = Logger.getLogger( ECafDaoImpl.class );

    @Override
    public ECafDto saveECAF( ECafDto inECafDto )
    {
        LOGGER.info( "E-CAF Dao method call" );
        SessionFactory sessionFactory = null;
        Session session = null;
        Transaction transaction = null;
        MasterDataDto masterDataDto = null;
        CrmCustomerDetailsPojo customerDetailsPojo = null;
        try
        {
            sessionFactory = HibernateUtil.getSessionFactory();
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            if ( StringUtils.isValidObj( inECafDto ) )
            {
                masterDataDto = validateCRFInMaster( masterDataDto, inECafDto.getCustomerDetailsPojo().getCrfId() );
                if ( StringUtils.isValidObj( masterDataDto )
                        && StringUtils.equals( CRMServiceCode.CRM001.getStatusCode(), masterDataDto.getStatusCode() ) )
                {
                    customerDetailsPojo = CRMServiceUtils.getDBValues( CrmCustomerDetailsPojo.class, "crfId", inECafDto
                            .getCustomerDetailsPojo().getCrfId(), session );
                    if ( StringUtils.isValidObj( customerDetailsPojo ) )
                    {
                        inECafDto.setStatusCode( CRMServiceCode.CRM307.getStatusCode() );
                        inECafDto.setStatusDesc( CRMServiceCode.CRM307.getStatusDesc() );
                    }
                    else
                    {
                        inECafDto = saveECAFInformation( inECafDto, session );
                        if ( StringUtils.equals( inECafDto.getStatusCode(), CRMServiceCode.CRM001.getStatusCode() ) )
                        {
                            inECafDto = generateInitiateInbox( inECafDto, session );
                        }
                    }
                }
                else
                {
                    LOGGER.info( "CAF ID :" + inECafDto.getCustomerDetailsPojo().getCrfId() + " not found in DB" );
                    inECafDto.setStatusCode( masterDataDto.getStatusCode() );
                    inECafDto.setStatusDesc( masterDataDto.getStatusDesc() );
                }
            }
            else
            {
                inECafDto.setStatusCode( CRMServiceCode.CRM997.getStatusCode() );
                inECafDto.setStatusDesc( CRMServiceCode.CRM997.getStatusDesc() );
            }
            if ( StringUtils.equals( CRMServiceCode.CRM001.getStatusCode(), inECafDto.getStatusCode() ) )
            {
                transaction.commit();
                transaction = null;
                LOGGER.info( "Successfully Save E-CAF Details" );
            }
        }
        catch ( HibernateException ex )
        {
            LOGGER.error( "HibernateException while Save E-CAF Method ", ex );
            CRMServiceUtils.rollback( transaction );
            CRMServiceUtils.closeSessionOnException( session );
        }
        catch ( Exception ex )
        {
            LOGGER.error( "Error in Save E-CAF Method :: ", ex );
            inECafDto.setStatusCode( CRMServiceCode.CRM999.getStatusCode() );
            inECafDto.setStatusDesc( CRMServiceCode.CRM999.getStatusDesc() );
        }
        finally
        {
            CRMServiceUtils.rollback( transaction );
            CRMServiceUtils.closeSession( session );
        }
        return inECafDto;
    }

    private MasterDataDto validateCRFInMaster( MasterDataDto inMasterDataDto, String inCrfId )
    {
        inMasterDataDto = new MasterDataDto();
        CrmRcaReasonPojo crmRcaReasonPojo = new CrmRcaReasonPojo();
        crmRcaReasonPojo.setCategory( CRMRCAReason.INA.getStatusCode() );
        crmRcaReasonPojo.setSubCategory( CRMDisplayListConstants.CAF.getCode() );
        crmRcaReasonPojo.setCategoryValue( inCrfId );
        crmRcaReasonPojo.setStatus( CRMStatusCode.ACTIVE.getStatusCode() );
        inMasterDataDto.setCrmRcaReason( crmRcaReasonPojo );
        try
        {
            inMasterDataDto = CRMServicesProxy
                    .getInstance()
                    .getCRMMasterDataService( IGlobalConstants.REMOTE, IGlobalConstants.APP )
                    .masterOperations( ServiceParameter.LIST.getParameter(),
                                       CRMParameter.CRM_RCA_REASON.getParameter(), inMasterDataDto );
        }
        catch ( SOAPException ex )
        {
            LOGGER.error( "Getting Error In validateCRFInMaster Method :: ", ex );
        }
        return inMasterDataDto;
    }

    private ECafDto generateInitiateInbox( ECafDto inECafDto, Session inSession )
    {
        LOGGER.info( ":: IN insertInobox ::" );
        List<CrmInboxPojo> crmInboxPojos = null;
        Criteria criteria = null;
        try
        {
            LOGGER.info( "Check Inbox Status For Customer Id :: " + inECafDto.getCustomerDetailsPojo().getRecordId() );
            if ( inECafDto.getCustomerDetailsPojo().getRecordId() > 0 )
            {
                criteria = inSession.createCriteria( CrmInboxPojo.class )
                        .add( Restrictions.eq( "requestType", CRMRequestType.CAF.getRequestCode() ) )
                        .add( Restrictions.eq( "stage", CRMOperationStages.INITIATE.getCode() ) )
                        .add( Restrictions.eq( "mappingId", inECafDto.getCustomerDetailsPojo().getRecordId() + "" ) );
                criteria.setCacheable( true );
                criteria.setCacheRegion( CrmInboxPojo.class.getName() );
                crmInboxPojos = criteria.list();
            }
            if ( !CommonValidator.isValidCollection( crmInboxPojos ) )
            {
                LOGGER.info( "Going To Save E-CAF in Inbox Pojo" );
                CrmInboxPojo crmInboxPojo = new CrmInboxPojo();
                crmInboxPojo.setUserId( null );
                crmInboxPojo.setRequestType( CRMRequestType.CAF.getRequestCode() );
                crmInboxPojo.setMappingId( inECafDto.getCustomerDetailsPojo().getRecordId() + "" );
                crmInboxPojo.setStage( CRMOperationStages.INITIATE.getCode() );
                crmInboxPojo.setCreatedBy( inECafDto.getUserId() );
                crmInboxPojo.setCreatedTime( Calendar.getInstance().getTime() );
                crmInboxPojo.setStatus( CRMStatusCode.ACTIVE.getStatusCode() );
                inSession.save( crmInboxPojo );
            }
            inECafDto.setStatusCode( CRMServiceCode.CRM001.getStatusCode() );
            inECafDto.setStatusDesc( CRMServiceCode.CRM001.getStatusDesc() );
        }
        catch ( Exception ex )
        {
            LOGGER.error( "Error in Save E-CAF generateInitiateInbox :: ", ex );
            inECafDto.setStatusCode( CRMServiceCode.CRM999.getStatusCode() );
            inECafDto.setStatusDesc( CRMServiceCode.CRM999.getStatusDesc() );
        }
        return inECafDto;
    }

    private ECafDto saveECAFInformation( ECafDto inECafDto, Session inSession )
    {
        LOGGER.info( "Inside saveCustomerInformation Method :: " );
        CRMServiceCode statusCode = CRMServiceCode.CRM997;
        long customerRecordId = 0;
        try
        {
            //Save Customer Details
            customerRecordId = saveECAFDetails( inECafDto, inSession );
            //Save Document Details
            saveECAFDocDetails( inSession, customerRecordId, inECafDto );
            //Save Plan Details
            saveECAFPlanDetails( inSession, customerRecordId, inECafDto );
            //Save payment Details
            saveECAFPaymentDetails( inSession, customerRecordId, inECafDto );
            //Save Customer Aadhar Number
            saveECAFAadharNo( inSession, customerRecordId, inECafDto );
            //Save DMS Doc of E-CAF
            saveECAFDMSDoc( inECafDto.getFileUploaderList(), inECafDto.getCustomerDetailsPojo().getCrfId(), inSession );
            statusCode = CRMServiceCode.CRM001;
        }
        catch ( Exception ex )
        {
            LOGGER.error( "Error In saveECAFInformation Method :: ", ex );
            statusCode = CRMServiceCode.CRM999;
        }
        finally
        {
            inECafDto.setStatusCode( statusCode.getStatusCode() );
            inECafDto.setStatusDesc( statusCode.getStatusDesc() );
        }
        return inECafDto;
    }

    private void saveECAFDMSDoc( List<FileUploader> inFileUploderList, String inCrfId, Session inSession )
    {
        NpUploads npUploads = null;
        String fileName = "";
        try
        {
            if ( CommonValidator.isValidCollection( inFileUploderList ) )
            {
                String rootPath = PropertyUtils.getServiceDetails( IAppConstants.FILE_PATH );
                for ( FileUploader fileUploader : inFileUploderList )
                {
                    fileName = fileUploader.getFileName();
                    if ( StringUtils.isValidObj( fileUploader.getDataHanderFile() ) )
                    {
                        LOGGER.info( "Mapping :" + inCrfId + ",Doc Type:" + fileUploader.getDocType() );
                        LOGGER.info( "Doc Path ::" + fileUploader.getDataHanderFile() + ",File Name :" + fileName );
                        String filePath = ( rootPath + File.separator + "uploads" + File.separator
                                + CRMRequestType.INA.getRequestCode() + File.separator + inCrfId + File.separator + fileUploader
                                .getDocType() ).replaceAll( " ", "_" );
                        LOGGER.info( "file Path ::" + filePath );
                        // String filePath = "E:/uploads/" + fileName;
                        File dir = new File( filePath );
                        if ( !dir.exists() )
                        {
                            dir.mkdirs();
                        }
                        // Create the file on server
                        File serverFile = new File( dir.getAbsolutePath() + File.separator
                                + Calendar.getInstance().getTimeInMillis() + "_" + fileName.replaceAll( " ", "_" ) );
                        LOGGER.info( "serverFile::" + serverFile );
                        FileOutputStream fos = new FileOutputStream( serverFile );
                        BufferedOutputStream outputStream = new BufferedOutputStream( fos );
                        outputStream.write( fileUploader.getDataHanderFile() );
                        outputStream.close();
                        npUploads = new NpUploads( CRMRequestType.INA.getRequestCode(),
                                                   inCrfId,
                                                   fileUploader.getDocType() );
                        npUploads.setDocName( fileName );
                        npUploads.setDocPath( serverFile.getAbsolutePath() );
                        Criteria criteria = inSession.createCriteria( NpUploads.class );
                        criteria.add( Example.create( npUploads ) );
                        List<NpUploads> dmsDb = criteria.list();
                        LOGGER.info( "DMS Db Size ::" + dmsDb.size() );
                        npUploads.setVersion( dmsDb.size() + 1 );
                        Integer id = (Integer) inSession.save( npUploads );
                        if ( id > 0 )
                        {
                            LOGGER.info( "Genrated ID ::" + id );
                        }
                    }
                }
            }
        }
        catch ( Exception ex )
        {
            LOGGER.error( "Error in Save E-CAF Doc Method :: ", ex );
        }
    }

    private void saveECAFAadharNo( Session inSession, long inCustomerRecordId, ECafDto inECafDto )
    {
        if ( StringUtils.isValidObj( inECafDto.getAadharNumberPojo() ) )
        {
            CrmCustAadharNumberPojo aadharNumberPojo = inECafDto.getAadharNumberPojo();
            LOGGER.info( "Aadhar Number :: " + aadharNumberPojo.getAadharNumber() );
            PojoUtils<CrmCustAadharNumberPojo> pojoUtils = new PojoUtils<CrmCustAadharNumberPojo>();
            if ( !pojoUtils.checkPojoEmpty( aadharNumberPojo ) )
            {
                LOGGER.info( "Customer RecordId::" + inCustomerRecordId );
                aadharNumberPojo.setMappingId( inCustomerRecordId + "" );
                aadharNumberPojo.setCreatedBy( inECafDto.getUserId() );
                aadharNumberPojo.setCreatedTime( Calendar.getInstance().getTime() );
                Long generatedId = (Long) inSession.save( aadharNumberPojo );
                LOGGER.info( "Successfully Save E-CAF Aadhar No. Generated Id :: " + generatedId );
            }
        }
    }

    private void saveECAFPaymentDetails( Session inSession, long inCustomerRecordId, ECafDto inECafDto )
        throws DuplicateRecieptException
    {
        if ( StringUtils.isValidObj( inECafDto.getPaymentDetailsPojo() ) )
        {
            CrmPaymentDetailsPojo crmPaymentDetailsPojo = inECafDto.getPaymentDetailsPojo();
            PojoUtils<CrmPaymentDetailsPojo> pojoUtils = new PojoUtils<CrmPaymentDetailsPojo>();
            if ( !pojoUtils.checkPojoEmpty( crmPaymentDetailsPojo ) && crmPaymentDetailsPojo.getAmount() > 0 )
            {
                CrmDaoHelper.setPaymentDetails( crmPaymentDetailsPojo );
                crmPaymentDetailsPojo.setCreatedBy( inECafDto.getUserId() );
                crmPaymentDetailsPojo.setCreatedTime( Calendar.getInstance().getTime() );
                crmPaymentDetailsPojo.setCustomerRecordId( inCustomerRecordId );
                crmPaymentDetailsPojo.setStatus( CRMStatusCode.PAYMENT_POSTED.getStatusCode() );
                if ( StringUtils.equals( inECafDto.getCustomerDetailsPojo().getProduct(),
                                         CRMDisplayListConstants.ETHERNET_ON_CABLE.getCode() )
                        || StringUtils.equals( inECafDto.getCustomerDetailsPojo().getProduct(),
                                               CRMDisplayListConstants.BROADBAND.getCode() )
                        && StringUtils.equals( inECafDto.getCustomerDetailsPojo().getServiceType(),
                                               CRMDisplayListConstants.PRE_PAID.getCode() ) )
                {
                    crmPaymentDetailsPojo.setEntityType( CRMDisplayListConstants.TELESERVICES.getCode() );
                }
                else
                {
                    crmPaymentDetailsPojo.setEntityType( CRMDisplayListConstants.TELESOLUTIONS.getCode() );
                }
                crmPaymentDetailsPojo.setServiceType( inECafDto.getCustomerDetailsPojo().getServiceType() );
                crmPaymentDetailsPojo.setInstallationStatus( CRMDisplayListConstants.PREINSTALLATION.getCode() );
                Long generatedId = (Long) inSession.save( crmPaymentDetailsPojo );
                LOGGER.info( "Successfully Save Payment Details Pojo. Generated Id :: " + generatedId );
                crmPaymentDetailsPojo.setPaymentId( generatedId );
                /*if ( generatedId > 0
                        && StringUtils.equals( CRMDisplayListConstants.CASH.getCode(),
                                               crmPaymentDetailsPojo.getPaymentMode() ) )
                {
                    CrmDaoHelper.setRecieptStatus( inSession, crmPaymentDetailsPojo, true );
                }*/
            }
        }
    }

    private void saveECAFPlanDetails( Session inSession, long inCustomerRecordId, ECafDto inECafDto )
    {
        if ( StringUtils.isValidObj( inECafDto.getPlanDetailsPojo() ) )
        {
            CrmPlanDetailsPojo crmPlanDetailsPojo = inECafDto.getPlanDetailsPojo();
            LOGGER.info( "Plan Name :: " + crmPlanDetailsPojo.getBasePlanCode() );
            PojoUtils<CrmPlanDetailsPojo> pojoUtils = new PojoUtils<CrmPlanDetailsPojo>();
            if ( !pojoUtils.checkPojoEmpty( crmPlanDetailsPojo ) )
            {
                crmPlanDetailsPojo.setCreatedBy( inECafDto.getUserId() );
                crmPlanDetailsPojo.setCreatedTime( Calendar.getInstance().getTime() );
                crmPlanDetailsPojo.setBillMode( CRMDisplayListConstants.E_BILL.getCode() );
                CrmCustomerDetailsPojo crmCustomerDetailsPojoForPlan = new CrmCustomerDetailsPojo();
                crmCustomerDetailsPojoForPlan.setRecordId( inCustomerRecordId );
                crmPlanDetailsPojo.setCrmCustomerDetails( crmCustomerDetailsPojoForPlan );
                Long generatedId = (Long) inSession.save( crmPlanDetailsPojo );
                LOGGER.info( "Successfully Save E-CAF Plan Details Pojo. Generated Id :: " + generatedId );
            }
        }
    }

    private void saveECAFDocDetails( Session inSession, long inCustomerRecordId, ECafDto inECafDto )
    {
        if ( StringUtils.isValidObj( inECafDto.getDocumentDetailsPojo() ) )
        {
            CrmDocumentDetailsPojo documentDetailsPojo = inECafDto.getDocumentDetailsPojo();
            PojoUtils<CrmDocumentDetailsPojo> pojoUtils = new PojoUtils<CrmDocumentDetailsPojo>();
            if ( !pojoUtils.checkPojoEmpty( documentDetailsPojo ) )
            {
                documentDetailsPojo.setCreatedBy( inECafDto.getUserId() );
                documentDetailsPojo.setCreatedTime( Calendar.getInstance().getTime() );
                documentDetailsPojo.setCustomerRecordId( inCustomerRecordId );
                Long generatedId = (Long) inSession.save( documentDetailsPojo );
                LOGGER.info( "Successfully Save E-CAF Document Detail. Generated Id :: " + generatedId );
            }
        }
    }

    private long saveECAFDetails( ECafDto inECafDto, Session inSession )
    {
        long customerRecordId = 0l;
        if ( StringUtils.isValidObj( inECafDto.getCustomerDetailsPojo() ) )
        {
            CrmCustomerDetailsPojo crmCustomerDetailsPojo = inECafDto.getCustomerDetailsPojo();
            PojoUtils<CrmCustomerDetailsPojo> pojoUtils = new PojoUtils<CrmCustomerDetailsPojo>();
            if ( !pojoUtils.checkPojoEmpty( crmCustomerDetailsPojo ) )
            {
                //crmCustomerDetailsPojo.setAadharNo( inECafDto.getAadharNumberPojo().getAadharNumber() );
                crmCustomerDetailsPojo.setBrand( IAppConstants.TIMBL );
                crmCustomerDetailsPojo.setStatus( CRMStatusCode.ACTIVE.getStatusCode() );
                crmCustomerDetailsPojo.setCrfStage( CRMOperationStages.INITIATE.getCode() );
                crmCustomerDetailsPojo.setCreatedBy( inECafDto.getUserId() );
                crmCustomerDetailsPojo.setCreatedTime( Calendar.getInstance().getTime() );
                customerRecordId = (Long) inSession.save( crmCustomerDetailsPojo );
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
                LOGGER.info( "Successfully Save E-CAF. Generated ID :: " + customerRecordId );
            }
        }
        inECafDto.getCustomerDetailsPojo().setRecordId( customerRecordId );
        return customerRecordId;
    }
}
