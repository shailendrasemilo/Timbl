package com.np.tele.crm.qrc.dao;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.soap.SOAPException;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.LogicalExpression;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;
import org.jboss.logging.MDC;

import com.np.tele.crm.billing.manager.ICrmBillingManager;
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
import com.np.tele.crm.constants.IGlobalConstants;
import com.np.tele.crm.constants.QRCRolCategories;
import com.np.tele.crm.constants.ServiceParameter;
import com.np.tele.crm.dto.ConfigDto;
import com.np.tele.crm.dto.CrmBillingDto;
import com.np.tele.crm.dto.CrmCapDto;
import com.np.tele.crm.dto.CrmQrcDto;
import com.np.tele.crm.dto.MasterDataDto;
import com.np.tele.crm.ext.pojos.CrmCustAdditionalDetails;
import com.np.tele.crm.ext.pojos.PlanMigrationPolicyPojo;
import com.np.tele.crm.ina.dao.CAPOperationDaoImpl;
import com.np.tele.crm.locator.CRMServicesProxy;
import com.np.tele.crm.massoutage.dao.IMassOutageDao;
import com.np.tele.crm.masterdata.dao.IMasterDataDao;
import com.np.tele.crm.pojos.CityPojo;
import com.np.tele.crm.pojos.ContentPojo;
import com.np.tele.crm.pojos.CrmAddressDetailsPojo;
import com.np.tele.crm.pojos.CrmBillingPlanRequestPojo;
import com.np.tele.crm.pojos.CrmCustAssetDetailsPojo;
import com.np.tele.crm.pojos.CrmCustInteractionsPojo;
import com.np.tele.crm.pojos.CrmCustMyAccountPojo;
import com.np.tele.crm.pojos.CrmCustWaiverPojo;
import com.np.tele.crm.pojos.CrmCustomerActivityPojo;
import com.np.tele.crm.pojos.CrmCustomerDetailsPojo;
import com.np.tele.crm.pojos.CrmInboxPojo;
import com.np.tele.crm.pojos.CrmInvoiceDetailsPojo;
import com.np.tele.crm.pojos.CrmNetworkConfigurationsPojo;
import com.np.tele.crm.pojos.CrmOrderDetailsPojo;
import com.np.tele.crm.pojos.CrmPartnerNetworkConfigPojo;
import com.np.tele.crm.pojos.CrmPlanDetailsPojo;
import com.np.tele.crm.pojos.CrmPlanMasterPojo;
import com.np.tele.crm.pojos.CrmQrcActionTakenPojo;
import com.np.tele.crm.pojos.CrmQrcCategoriesPojo;
import com.np.tele.crm.pojos.CrmQrcCategoryBinMappingPojo;
import com.np.tele.crm.pojos.CrmQrcCommonPojo;
import com.np.tele.crm.pojos.CrmQrcRootCausePojo;
import com.np.tele.crm.pojos.CrmQrcSubCategoriesPojo;
import com.np.tele.crm.pojos.CrmQrcSubSubCategoriesPojo;
import com.np.tele.crm.pojos.CrmQrcWhitelistPojo;
import com.np.tele.crm.pojos.CrmRcaReasonPojo;
import com.np.tele.crm.pojos.CrmSelfcareCategoriesPojo;
import com.np.tele.crm.pojos.CrmSrTicketsPojo;
import com.np.tele.crm.pojos.CrmTicketHistoryPojo;
import com.np.tele.crm.pojos.CrmUserPojo;
import com.np.tele.crm.pojos.CrmWorkflowSequence;
import com.np.tele.crm.pojos.LmsCrfMappingPojo;
import com.np.tele.crm.pojos.RemarksPojo;
import com.np.tele.crm.pojos.StatePojo;
import com.np.tele.crm.utils.CRMServiceUtils;
import com.np.tele.crm.utils.CRMUtils;
import com.np.tele.crm.utils.DateUtils;
import com.np.tele.crm.utils.EncryptionUtil;
import com.np.tele.crm.utils.HibernateUtil;
import com.np.tele.crm.utils.ICRMValidationCriteriaUtil;
import com.np.tele.crm.utils.SortingUtil;
import com.np.tele.crm.utils.StringUtils;
import com.np.validator.util.CommonValidator;
import com.np.validator.util.ValidationPojoUtil;

public class QrcManagerDaoImpl
    implements IQrcManagerDao
{
    private static final Logger LOGGER            = Logger.getLogger( QrcManagerDaoImpl.class );
    private ICrmBillingManager  crmBillingManager = null;
    private IMasterDataDao      masterDataDao     = null;
    private IMassOutageDao      massOutageDao     = null;
    private IBillingDataDao     billingDao        = null;

    public ICrmBillingManager getCrmBillingManager()
    {
        return crmBillingManager;
    }

    public void setCrmBillingManager( ICrmBillingManager crmBillingManager )
    {
        this.crmBillingManager = crmBillingManager;
    }

    public IMasterDataDao getMasterDataDao()
    {
        return masterDataDao;
    }

    public void setMasterDataDao( IMasterDataDao masterDataDao )
    {
        this.masterDataDao = masterDataDao;
    }

    public IMassOutageDao getMassOutageDao()
    {
        return massOutageDao;
    }

    public void setMassOutageDao( IMassOutageDao inMassOutageDao )
    {
        massOutageDao = inMassOutageDao;
    }

    public IBillingDataDao getBillingDao()
    {
        return billingDao;
    }

    public void setBillingDao( IBillingDataDao inBillingDao )
    {
        billingDao = inBillingDao;
    }

    @Override
    public CrmQrcDto getQrcCategories( CrmQrcDto inCrmQrcDto )
    {
        LOGGER.info( "Inside QrcManagerDaoImpl, getQrcCategories" );
        SessionFactory sessionFactory = null;
        Session session = null;
        Criteria criteria = null;
        CRMServiceCode crmServiceCode = CRMServiceCode.CRM999;
        try
        {
            MDC.put( "KEY", "CUSTID:" + inCrmQrcDto.getCustomerId() + ", USERID:" + inCrmQrcDto.getUserId() );
            sessionFactory = HibernateUtil.getSessionFactory();
            session = sessionFactory.openSession();
            criteria = session.createCriteria( CrmQrcCategoriesPojo.class );
            if ( StringUtils.isValidObj( inCrmQrcDto.getCrmQrcCategoriesPojo() ) )
            {
                if ( StringUtils.isNotBlank( inCrmQrcDto.getCrmQrcCategoriesPojo().getQrcCategory() ) )
                {
                    criteria.add( Restrictions.eq( "qrcCategory", inCrmQrcDto.getCrmQrcCategoriesPojo()
                            .getQrcCategory() ) );
                }
                if ( inCrmQrcDto.getCrmQrcCategoriesPojo().getQrcCategoryId() > 0 )
                {
                    criteria.add( Restrictions.eq( "qrcCategoryId", inCrmQrcDto.getCrmQrcCategoriesPojo()
                            .getQrcCategoryId() ) );
                }
                if ( StringUtils.isNotBlank( inCrmQrcDto.getCrmQrcCategoriesPojo().getStatus() ) )
                {
                    criteria.add( Restrictions.eq( "status", inCrmQrcDto.getCrmQrcCategoriesPojo().getStatus() ) );
                    session.enableFilter( "ActiveSubCategories" ).setParameter( "catStatus",
                                                                                inCrmQrcDto.getCrmQrcCategoriesPojo()
                                                                                        .getStatus() );
                    /*session.enableFilter( "ActiveRCA" )
                            .setParameter( "catStatus", inCrmQrcDto.getCrmQrcCategoriesPojo().getStatus() );*/
                    session.enableFilter( "ActiveSubSubCategories" ).setParameter( "catStatus",
                                                                                   inCrmQrcDto
                                                                                           .getCrmQrcCategoriesPojo()
                                                                                           .getStatus() );
                    /*session.enableFilter( "ActiveBinMapping" ).setParameter( "catStatus",
                                                                             inCrmQrcDto.getCrmQrcCategoriesPojo()
                                                                                     .getStatus() );*/
                }
                if ( StringUtils.isNotBlank( inCrmQrcDto.getCrmQrcCategoriesPojo().getFunctionalBin() ) )
                {
                    criteria.add( Restrictions.eq( "functionalBin", inCrmQrcDto.getCrmQrcCategoriesPojo()
                            .getFunctionalBin() ) );
                }
            }
            criteria.setCacheable( true );
            criteria.setCacheRegion( CrmQrcCategoriesPojo.class.getName() );
            List<CrmQrcCategoriesPojo> categoriesPojos = criteria.list();
            //            System.out.println( "Size of list::" + categoriesPojos.size() );
            // System.out.println( "Size of 2 nd list::" + categoriesPojos.get( 0 ).getCrmQrcSubCategorieses().size() );
            inCrmQrcDto.setCrmQrcCategoriesPojos( categoriesPojos );
            crmServiceCode = CRMServiceCode.CRM001;
        }
        catch ( Exception ex )
        {
            LOGGER.error( "Getting Error while getQrcCategories: ", ex );
        }
        finally
        {
            CRMServiceUtils.closeSession( session );
            inCrmQrcDto.setStatusCode( crmServiceCode.getStatusCode() );
            inCrmQrcDto.setStatusDesc( crmServiceCode.getStatusDesc() );
            MDC.remove( "KEY" );
        }
        return inCrmQrcDto;
    }

    @Override
    public CrmQrcDto getQrcSubSubCategories( CrmQrcDto inCrmQrcDto )
    {
        LOGGER.info( "Inside QrcManagerDaoImpl, getQrcSubSubCategories" );
        SessionFactory sessionFactory = null;
        Session session = null;
        Criteria criteria = null;
        CRMServiceCode crmServiceCode = CRMServiceCode.CRM999;
        try
        {
            MDC.put( "KEY", "CUSTID:" + inCrmQrcDto.getCustomerId() + ", USERID:" + inCrmQrcDto.getUserId() );
            sessionFactory = HibernateUtil.getSessionFactory();
            session = sessionFactory.openSession();
            criteria = session.createCriteria( CrmQrcSubSubCategoriesPojo.class );
            if ( StringUtils.isValidObj( inCrmQrcDto.getCrmQrcSubSubCategoriesPojo() ) )
            {
                if ( StringUtils.isNotBlank( inCrmQrcDto.getCrmQrcSubSubCategoriesPojo().getQrcSubSubCategory() ) )
                {
                    criteria.add( Restrictions.eq( "qrcSubSubCategory", inCrmQrcDto.getCrmQrcSubSubCategoriesPojo()
                            .getQrcSubSubCategory() ) );
                }
                if ( inCrmQrcDto.getCrmQrcSubSubCategoriesPojo().getQrcSubSubCategoryId() > 0 )
                {
                    criteria.add( Restrictions.eq( "qrcSubSubCategoryId", inCrmQrcDto.getCrmQrcSubSubCategoriesPojo()
                            .getQrcSubSubCategoryId() ) );
                }
                if ( StringUtils.isNotBlank( inCrmQrcDto.getCrmQrcSubCategoriesPojo().getStatus() ) )
                {
                    criteria.add( Restrictions.eq( "status", inCrmQrcDto.getCrmQrcSubCategoriesPojo().getStatus() ) );
                    session.enableFilter( "ActiveSubSubCategories" )
                            .setParameter( "catStatus", CRMStatusCode.ACTIVE.getStatusCode() );
                }
            }
            criteria.setCacheable( true );
            criteria.setCacheRegion( CrmQrcSubSubCategoriesPojo.class.getName() );
            List<CrmQrcSubSubCategoriesPojo> subSubCategoriesPojos = criteria.list();
            LOGGER.info( "Size of list::" + subSubCategoriesPojos.size() );
            // System.out.println( "Size of 2 nd list::" + categoriesPojos.get( 0 ).getCrmQrcSubCategorieses().size() );
            inCrmQrcDto.setCrmQrcSubSubCategoriesPojos( subSubCategoriesPojos );
            crmServiceCode = CRMServiceCode.CRM001;
        }
        catch ( Exception ex )
        {
            LOGGER.error( "Getting Error while getQrcCategories: ", ex );
        }
        finally
        {
            CRMServiceUtils.closeSession( session );
            inCrmQrcDto.setStatusCode( crmServiceCode.getStatusCode() );
            inCrmQrcDto.setStatusDesc( crmServiceCode.getStatusDesc() );
            MDC.remove( "KEY" );
        }
        return inCrmQrcDto;
    }

    @Override
    public CrmQrcDto getQrcSubCategories( CrmQrcDto inCrmQrcDto )
    {
        LOGGER.info( "Inside getQrcSubCategories" );
        SessionFactory sessionFactory = null;
        Session session = null;
        Criteria criteria = null;
        CRMServiceCode crmServiceCode = CRMServiceCode.CRM999;
        try
        {
            MDC.put( "KEY", "CUSTID:" + inCrmQrcDto.getCustomerId() + ", USERID:" + inCrmQrcDto.getUserId() );
            sessionFactory = HibernateUtil.getSessionFactory();
            session = sessionFactory.openSession();
            criteria = session.createCriteria( CrmQrcSubCategoriesPojo.class );
            if ( StringUtils.isValidObj( inCrmQrcDto.getCrmQrcSubCategoriesPojo() ) )
            {
                if ( StringUtils.isNotBlank( inCrmQrcDto.getCrmQrcSubCategoriesPojo().getQrcSubCategory() ) )
                {
                    criteria.add( Restrictions.eq( "qrcSubCategory", inCrmQrcDto.getCrmQrcSubCategoriesPojo()
                            .getQrcSubCategory() ) );
                }
                if ( inCrmQrcDto.getCrmQrcSubCategoriesPojo().getQrcSubCategoryId() > 0 )
                {
                    criteria.add( Restrictions.eq( "qrcSubCategoryId", inCrmQrcDto.getCrmQrcSubCategoriesPojo()
                            .getQrcSubCategoryId() ) );
                }
                if ( StringUtils.isNotBlank( inCrmQrcDto.getCrmQrcSubCategoriesPojo().getStatus() ) )
                {
                    criteria.add( Restrictions.eq( "status", inCrmQrcDto.getCrmQrcSubCategoriesPojo().getStatus() ) );
                    session.enableFilter( "ActiveSubSubCategories" )
                            .setParameter( "catStatus", CRMStatusCode.ACTIVE.getStatusCode() );
                }
            }
            criteria.setCacheable( true );
            criteria.setCacheRegion( CrmQrcSubCategoriesPojo.class.getName() );
            List<CrmQrcSubCategoriesPojo> subCategoriesPojos = criteria.list();
            inCrmQrcDto.setCrmQrcSubCategoriesPojos( subCategoriesPojos );
            crmServiceCode = CRMServiceCode.CRM001;
        }
        catch ( Exception ex )
        {
            LOGGER.error( "Getting Error while getQrcSubCategories: ", ex );
        }
        finally
        {
            CRMServiceUtils.closeSession( session );
            inCrmQrcDto.setStatusCode( crmServiceCode.getStatusCode() );
            inCrmQrcDto.setStatusDesc( crmServiceCode.getStatusDesc() );
            MDC.remove( "KEY" );
        }
        return inCrmQrcDto;
    }

    public List<CrmQrcCategoriesPojo> getQrcCategoriesID( String category, String subCategory, String subSubCategory )
    {
        LOGGER.info( "Inside getQrcSUBSUBCategories" );
        Session session = null;
        Criteria criteria = null;
        List<CrmQrcCategoriesPojo> qrcCategory = null;
        try
        {
            session = HibernateUtil.getCurrentSession();
            criteria = session.createCriteria( CrmQrcCategoriesPojo.class );
            String str[] =
            { category, subCategory, subSubCategory };
            if ( StringUtils.checkAllvalidObj( str, false ) )
            {
                criteria.add( Restrictions.eq( "qrcCategory", category ) );
                criteria.add( Restrictions.eq( "status", CRMStatusCode.ACTIVE.getStatusCode() ) );
                session.enableFilter( "catqrcSubCategory" ).setParameter( "qrcSubCategory", subCategory );
                session.enableFilter( "catqrcSubSubCategory" ).setParameter( "qrcSubSubCategory", subSubCategory );
                session.enableFilter( "qrcType" ).setParameter( "qrcType", CRMDisplayListConstants.REQUEST.getCode() );
            }
            criteria.setCacheable( true );
            criteria.setCacheRegion( CrmQrcCategoriesPojo.class.getName() );
            qrcCategory = criteria.list();
            LOGGER.info( "Qrc List Size" + qrcCategory.size() );
        }
        catch ( Exception ex )
        {
            LOGGER.error( "Getting Error while get categories id: ", ex );
        }
        finally
        {
            CRMServiceUtils.closeSession( session );
        }
        return qrcCategory;
    }

    /**
     * Never filter on the basis of resolutionType. This will break the functionality.
     */
    @Override
    public CrmQrcDto viewCustomerTickets( CrmQrcDto inCrmQrcDto )
    {
        LOGGER.info( "Inside QrcManagerDaoImpl, viewCustomerTickets" );
        Session session = null;
        Criteria criteria = null;
        Criteria criteriaLms = null;
        String LeadID = null;
        CRMServiceCode crmServiceCode = CRMServiceCode.CRM999;
        try
        {
            MDC.put( "KEY", "CUSTID:" + inCrmQrcDto.getCustomerId() + ", USERID:" + inCrmQrcDto.getUserId() );
            session = HibernateUtil.getCurrentSession();
            criteria = session.createCriteria( CrmSrTicketsPojo.class );
            if ( StringUtils.isNotEmpty( inCrmQrcDto.getActivityAction() ) )
            {
                CrmSrTicketsPojo crmSrTicketsPojo = preparedQrcTicketPojo( inCrmQrcDto.getCustomerId(),
                                                                           inCrmQrcDto.getActivityAction(),
                                                                           inCrmQrcDto.getCrmSrTicketsPojo() );
                inCrmQrcDto.setCrmSrTicketsPojo( crmSrTicketsPojo );
            }
            if ( StringUtils.isValidObj( inCrmQrcDto.getCustomerDetailsPojo() ) )
            {
                criteriaLms = session.createCriteria( LmsCrfMappingPojo.class );
                criteriaLms.add( Restrictions.eq( "crfValue", inCrmQrcDto.getCustomerDetailsPojo().getCrfId() ) );
                List<LmsCrfMappingPojo> crmLmsMappingPojo = criteriaLms.list();
                if ( CommonValidator.isValidCollection( crmLmsMappingPojo ) )
                {
                    LeadID = "L" + crmLmsMappingPojo.get( 0 ).getLmsId();
                    LOGGER.info( "Lead ID:" + LeadID );
                }
            }
            if ( StringUtils.isValidObj( inCrmQrcDto.getCrmSrTicketsPojo() ) )
            {
                if ( StringUtils.isNotBlank( inCrmQrcDto.getCrmSrTicketsPojo().getSrId() ) )
                {
                    criteria.add( Restrictions.eq( "srId", inCrmQrcDto.getCrmSrTicketsPojo().getSrId() ) );
                }
                if ( StringUtils.isNotBlank( inCrmQrcDto.getCrmSrTicketsPojo().getMappingId() ) )
                {
                    Criterion criterion = Restrictions.eq( "mappingId", inCrmQrcDto.getCrmSrTicketsPojo()
                            .getMappingId() );
                    Criterion lmsCriterion = Restrictions.eq( "mappingId", LeadID );
                    LogicalExpression logicalExpression = Restrictions.or( criterion, lmsCriterion );
                    criteria.add( logicalExpression );
                    //criteria.add( Restrictions.eq( "mappingId", inCrmQrcDto.getCrmSrTicketsPojo().getMappingId() ) );
                }
                if ( inCrmQrcDto.getCrmSrTicketsPojo().getQrcCategoryId() > 0 )
                {
                    criteria.add( Restrictions.eq( "qrcCategoryId", inCrmQrcDto.getCrmSrTicketsPojo()
                            .getQrcCategoryId() ) );
                }
                if ( inCrmQrcDto.getCrmSrTicketsPojo().getQrcSubCategoryId() > 0 )
                {
                    criteria.add( Restrictions.eq( "qrcSubCategoryId", inCrmQrcDto.getCrmSrTicketsPojo()
                            .getQrcSubCategoryId() ) );
                }
                if ( inCrmQrcDto.getCrmSrTicketsPojo().getQrcSubSubCategoryId() > 0 )
                {
                    criteria.add( Restrictions.eq( "qrcSubSubCategoryId", inCrmQrcDto.getCrmSrTicketsPojo()
                            .getQrcSubSubCategoryId() ) );
                }
                if ( StringUtils.isNotBlank( inCrmQrcDto.getCrmSrTicketsPojo().getStatus() ) )
                {
                    if ( StringUtils.equals( inCrmQrcDto.getCrmSrTicketsPojo().getStatus(),
                                             CRMStatusCode.OPEN.getStatusCode() )
                            || StringUtils.equals( inCrmQrcDto.getCrmSrTicketsPojo().getStatus(),
                                                   CRMStatusCode.REOPEN.getStatusCode() ) )
                    {
                        Criterion statusOpen = Restrictions.eq( "status", CRMStatusCode.OPEN.getStatusCode() );
                        Criterion statusReopen = Restrictions.eq( "status", CRMStatusCode.REOPEN.getStatusCode() );
                        LogicalExpression logicalExpression = Restrictions.or( statusOpen, statusReopen );
                        criteria.add( logicalExpression );
                    }
                    else if ( StringUtils.equals( inCrmQrcDto.getCrmSrTicketsPojo().getStatus(),
                                                  CRMStatusCode.RESOLVED.getStatusCode() ) )
                    {
                        criteria.add( Restrictions.eq( "status", inCrmQrcDto.getCrmSrTicketsPojo().getStatus() ) );
                        if ( StringUtils.isValidObj( inCrmQrcDto.getCrmSrTicketsPojo().getSrResolvedOn() ) )
                        {
                            criteria.add( Restrictions.le( "srResolvedOn", inCrmQrcDto.getCrmSrTicketsPojo()
                                    .getSrResolvedOn() ) );
                        }
                    }
                    else
                    {
                        criteria.add( Restrictions.eq( "status", inCrmQrcDto.getCrmSrTicketsPojo().getStatus() ) );
                    }
                }
                if ( StringUtils.isValidObj( inCrmQrcDto.getCrmSrTicketsPojo().getFollowupOn() ) )
                {
                    criteria.add( Restrictions.eq( "followupOn", inCrmQrcDto.getCrmSrTicketsPojo().getFollowupOn() ) );
                }
                if ( StringUtils.isValidObj( inCrmQrcDto.getCrmSrTicketsPojo().getProcessingDate() ) )
                {
                    criteria.add( Restrictions.lt( "processingDate", inCrmQrcDto.getCrmSrTicketsPojo()
                            .getProcessingDate() ) );
                }
                if ( StringUtils.isNotBlank( inCrmQrcDto.getCrmSrTicketsPojo().getQrcType() ) )
                {
                    criteria.add( Restrictions.eq( "qrcType", inCrmQrcDto.getCrmSrTicketsPojo().getQrcType() ) );
                }
                if ( StringUtils.isValidObj( inCrmQrcDto.getCrmSrTicketsPojo().getCreatedTime() )
                        && StringUtils.isValidObj( inCrmQrcDto.getCrmSrTicketsPojo().getLastModifiedTime() ) )
                {
                    criteria.add( Restrictions.between( "createdTime", inCrmQrcDto.getCrmSrTicketsPojo()
                            .getCreatedTime(), inCrmQrcDto.getCrmSrTicketsPojo().getLastModifiedTime() ) );
                }
                else if ( StringUtils.isValidObj( inCrmQrcDto.getCrmSrTicketsPojo().getCreatedTime() )
                        && !StringUtils.isValidObj( inCrmQrcDto.getCrmSrTicketsPojo().getLastModifiedTime() ) )
                {
                    criteria.add( Restrictions.ge( "createdTime", inCrmQrcDto.getCrmSrTicketsPojo().getCreatedTime() ) );
                }
                else if ( !StringUtils.isValidObj( inCrmQrcDto.getCrmSrTicketsPojo().getCreatedTime() )
                        && StringUtils.isValidObj( inCrmQrcDto.getCrmSrTicketsPojo().getLastModifiedTime() ) )
                {
                    criteria.add( Restrictions.le( "createdTime", inCrmQrcDto.getCrmSrTicketsPojo()
                            .getLastModifiedTime() ) );
                }
                if ( inCrmQrcDto.getMaxResultSize() > 0 )
                {
                    criteria.setMaxResults( inCrmQrcDto.getMaxResultSize() );
                }
                criteria.addOrder( Order.asc( "createdTime" ) );
                criteria.setCacheable( true );
                criteria.setCacheRegion( CrmSrTicketsPojo.class.getName() );
                List<CrmSrTicketsPojo> crmSrTicketsPojos = criteria.list();
                if ( CommonValidator.isValidCollection( crmSrTicketsPojos ) )
                {
                    LOGGER.info( "TicketsPojo Size::" + crmSrTicketsPojos.size() );
                    for ( CrmSrTicketsPojo crmSrTicketsPojo : crmSrTicketsPojos )
                    {
                        if ( StringUtils.equals( crmSrTicketsPojo.getStatus(), CRMStatusCode.REOPEN.getStatusCode() )
                                || StringUtils
                                        .equals( crmSrTicketsPojo.getStatus(), CRMStatusCode.OPEN.getStatusCode() ) )
                        {
                            criteria = session.createCriteria( CrmInboxPojo.class );
                            criteria.add( Restrictions.eq( "mappingId", crmSrTicketsPojo.getSrId() ) )
                                    .add( Restrictions.eq( "status", CRMStatusCode.ACTIVE.getStatusCode() ) );
                            criteria.setCacheable( true );
                            criteria.setCacheRegion( CrmInboxPojo.class.getName() );
                            List<CrmInboxPojo> inboxList = criteria.list();
                            if ( CommonValidator.isValidCollection( inboxList ) )
                            {
                                if ( StringUtils.isNotEmpty( inboxList.get( 0 ).getUserId() ) )
                                {
                                    crmSrTicketsPojo.setCurrentUser( inboxList.get( 0 ).getUserId() );
                                }
                                else
                                    crmSrTicketsPojo.setCurrentUser( "-" );
                            }
                            else
                                crmSrTicketsPojo.setCurrentUser( "-" );
                        }
                    }
                }
                inCrmQrcDto.setCrmSrTicketsPojos( crmSrTicketsPojos );
                crmServiceCode = CRMServiceCode.CRM001;
            }
        }
        catch ( Exception ex )
        {
            LOGGER.error( "Getting Error while view SR tickets: ", ex );
        }
        finally
        {
            CRMServiceUtils.closeSession( session );
            inCrmQrcDto.setStatusCode( crmServiceCode.getStatusCode() );
            inCrmQrcDto.setStatusDesc( crmServiceCode.getStatusDesc() );
            MDC.remove( "KEY" );
        }
        return inCrmQrcDto;
    }

    @Override
    public CrmQrcDto getWhiteListCustomers( CrmQrcDto inCrmQrcDto )
    {
        Session session = null;
        Criteria criteria = null;
        try
        {
            MDC.put( "KEY", "CUSTID:" + inCrmQrcDto.getCustomerId() + ", USERID:" + inCrmQrcDto.getUserId() );
            session = HibernateUtil.getCurrentSession();
            criteria = session.createCriteria( CrmQrcWhitelistPojo.class );
            if ( StringUtils.isNotBlank( inCrmQrcDto.getCrmQrcWhitelistPojo().getCustomerId() ) )
            {
                criteria.add( Restrictions.eq( "customerId", inCrmQrcDto.getCrmQrcWhitelistPojo().getCustomerId() ) );
            }
            if ( StringUtils.isNotBlank( inCrmQrcDto.getCrmQrcWhitelistPojo().getWhitelistType() ) )
            {
                criteria.add( Restrictions
                        .eq( "whitelistType", inCrmQrcDto.getCrmQrcWhitelistPojo().getWhitelistType() ) );
            }
            criteria.setCacheable( true );
            criteria.setCacheRegion( CrmQrcWhitelistPojo.class.getName() );
            List<CrmQrcWhitelistPojo> crmWhiteListPojos = criteria.list();
            if ( CommonValidator.isValidCollection( crmWhiteListPojos ) )
            {
                inCrmQrcDto.setCrmQrcWhitelistPojoList( crmWhiteListPojos );
                inCrmQrcDto.setStatusCode( CRMServiceCode.CRM001.getStatusCode() );
            }
        }
        catch ( Exception ex )
        {
            inCrmQrcDto.setStatusCode( CRMServiceCode.CRM999.getStatusCode() );
            LOGGER.error( "Getting Error while Listing whitelist customer: ", ex );
        }
        finally
        {
            CRMServiceUtils.closeSession( session );
            MDC.remove( "KEY" );
        }
        return inCrmQrcDto;
    }

    @Override
    public CrmQrcDto addUpdateRemoveWhiteList( CrmQrcDto inCrmQrcDto )
    {
        Session session = null;
        Transaction transaction = null;
        Criteria criteria = null;
        CrmCustomerActivityPojo activityLog = null;
        CRMServiceCode crmServiceCode = CRMServiceCode.CRM999;
        Long id = null;
        String action = "ADD";
        boolean billingFlag = false;
        Map<String, Long> evicts = new HashMap<String, Long>();
        try
        {
            MDC.put( "KEY", "CUSTID:" + inCrmQrcDto.getCustomerId() + ", USERID:" + inCrmQrcDto.getUserId() );
            session = HibernateUtil.getCurrentSession();
            transaction = session.beginTransaction();
            activityLog = new CrmCustomerActivityPojo();
            activityLog.setReason( action );
            criteria = session.createCriteria( CrmQrcWhitelistPojo.class );
            criteria.add( Restrictions.eq( "customerId", inCrmQrcDto.getCrmQrcWhitelistPojo().getCustomerId() ) );
            if ( StringUtils.isNotBlank( inCrmQrcDto.getCrmQrcWhitelistPojo().getWhitelistType() ) )
            {
                LOGGER.info( "Whitelist Type: " + inCrmQrcDto.getCrmQrcWhitelistPojo().getWhitelistType() );
                criteria.add( Restrictions
                        .eq( "whitelistType", inCrmQrcDto.getCrmQrcWhitelistPojo().getWhitelistType() ) );
            }
            criteria.setCacheable( true );
            criteria.setCacheRegion( CrmQrcWhitelistPojo.class.getName() );
            List<CrmQrcWhitelistPojo> crmWhiteListPojos = criteria.list();
            if ( CommonValidator.isValidCollection( crmWhiteListPojos ) )
            {
                if ( inCrmQrcDto.getCrmQrcWhitelistPojo().getEndDate().compareTo( DateUtils.getCurrentDate() ) <= 0 )
                {
                    action = "REMOVE";
                    activityLog.setReason( action );
                }
                CRMUtils.copyPropertyValue( crmWhiteListPojos.get( 0 ), inCrmQrcDto.getCrmQrcWhitelistPojo(), null );
                activityLog.setOldValue( IAppConstants.DASH );
                activityLog.setNewValue( DateUtils.getFormattedDate( inCrmQrcDto.getCrmQrcWhitelistPojo().getEndDate(),
                                                                     IDateConstants.SDF_DD_MMM_YYYY_HH_MM_SS ) );
                inCrmQrcDto.getCrmQrcWhitelistPojo().setLastModifiedBy( inCrmQrcDto.getUserId() );
                inCrmQrcDto.getCrmQrcWhitelistPojo().setLastModifiedTime( Calendar.getInstance().getTime() );
                session.update( crmWhiteListPojos.get( 0 ) );
                evicts.put( CrmQrcWhitelistPojo.class.getName(), crmWhiteListPojos.get( 0 ).getRecordId() );
                id = crmWhiteListPojos.get( 0 ).getRecordId();
                inCrmQrcDto.getCrmQrcWhitelistPojo().setRecordId( id );
            }
            else
            {
                inCrmQrcDto.getCrmQrcWhitelistPojo().setCreatedBy( inCrmQrcDto.getUserId() );
                inCrmQrcDto.getCrmQrcWhitelistPojo().setCreatedTime( Calendar.getInstance().getTime() );
                session.save( inCrmQrcDto.getCrmQrcWhitelistPojo() );
                evicts.put( CrmQrcWhitelistPojo.class.getName(), null );
            }
            // Calling Billing API
            billingFlag = billingBarringUnbarring( inCrmQrcDto, action );
            if ( billingFlag )
            {
                //calling activity log
                long activityId = 0;
                if ( StringUtils.equals( inCrmQrcDto.getCrmQrcWhitelistPojo().getWhitelistType(),
                                         CRMStatusCode.UNBARRED.getStatusDesc() )
                        && CommonValidator.isValidCollection( crmWhiteListPojos ) )
                {
                    LOGGER.info( "whitelist type " + inCrmQrcDto.getCrmQrcWhitelistPojo().getWhitelistType() );
                    activityLog.setAction( CRMCustomerActivityActions.UNBARRING_EXCEPTION.getActionDesc() );
                    activityLog.setCustomerId( inCrmQrcDto.getCrmQrcWhitelistPojo().getCustomerId() );
                    activityLog.setServiceIp( CRMUtils.getServerIp() );
                    activityLog.setClientIp( inCrmQrcDto.getClientIPAddress() );
                    activityLog.setServerIp( inCrmQrcDto.getServerIPAddress() );
                    activityLog.setCreatedBy( inCrmQrcDto.getUserId() );
                    activityId = CRMServiceUtils.createActivityLog( activityLog );
                }
                inCrmQrcDto.getRemarksPojo().setActivityId( activityId );
                saveRemarksDetails( session, inCrmQrcDto, evicts );
                crmServiceCode = CRMServiceCode.CRM001;
                transaction.commit();
            }
        }
        catch ( HibernateException ex )
        {
            LOGGER.error( "HibernateException while Add update Remove whitelist customer:", ex );
            crmServiceCode = CRMServiceCode.CRM999;
            CRMServiceUtils.rollback( transaction );
            CRMServiceUtils.closeSessionOnException( session );
        }
        catch ( Exception ex )
        {
            LOGGER.error( "Getting Error while Add update Remove whitelist customer: ", ex );
            crmServiceCode = CRMServiceCode.CRM999;
        }
        finally
        {
            CRMServiceUtils.rollback( transaction );
            CRMServiceUtils.closeSession( session );
            if ( StringUtils.isEmpty( inCrmQrcDto.getStatusCode() ) )
            {
                inCrmQrcDto.setStatusCode( crmServiceCode.getStatusCode() );
                inCrmQrcDto.setStatusDesc( crmServiceCode.getStatusDesc() );
            }
            if ( CRMServiceCode.CRM001 == crmServiceCode )
            {
                HibernateUtil.evictAll( evicts );
            }
            MDC.remove( "KEY" );
        }
        return inCrmQrcDto;
    }

    private boolean billingBarringUnbarring( CrmQrcDto inCrmQrcDto, String action )
    {
        CrmBillingDto crmBillingDto = null;
        if ( StringUtils.equals( inCrmQrcDto.getCrmQrcWhitelistPojo().getWhitelistType(),
                                 CRMStatusCode.BARRED.getStatusDesc() ) )
        {
            LOGGER.info( "calling billing barringException. action = " + action );
            crmBillingDto = getCrmBillingManager().barringException( inCrmQrcDto, action );
        }
        else
        {
            LOGGER.info( "calling billing unbarringException. action = " + action );
            crmBillingDto = getCrmBillingManager().unbarringException( inCrmQrcDto, action );
        }
        LOGGER.info( "crmBillingDto.getStatusCode():" + crmBillingDto.getStatusCode() );
        inCrmQrcDto.setBillingErrorCode( crmBillingDto.getBillingErrorCode() );
        inCrmQrcDto.setStatusCode( crmBillingDto.getStatusCode() );
        inCrmQrcDto.setStatusDesc( crmBillingDto.getStatusDesc() );
        return StringUtils.equals( crmBillingDto.getStatusCode(), CRMServiceCode.CRM001.getStatusCode() ) ? true
                                                                                                         : false;
    }

    private boolean isCustAlreadyInWhiteList( CrmQrcWhitelistPojo crmWhiteListPojos )
    {
        boolean flag = false;;
        if ( StringUtils.isValidObj( crmWhiteListPojos.getEndDate() ) )
        {
            String today = DateUtils.getCurrentDateStr();
            try
            {
                Date date1 = IDateConstants.SDF_DD_MMM_YYYY.parse( today );
                Date date2 = crmWhiteListPojos.getEndDate();
                LOGGER.info( "Today Date::" + date1 );
                LOGGER.info( "Compare Date:: " + date2 );
                if ( date2.compareTo( date1 ) > 0 )
                {
                    flag = true;
                    LOGGER.info( "Customer already in " + crmWhiteListPojos.getWhitelistType() + " Exception List" );
                }
            }
            catch ( ParseException ex )
            {
                LOGGER.error( "getting error while parsing date  in isCustAlreadyInWhiteList ", ex );
            }
        }
        return flag;
    }

    private void saveRemarksDetails( Session inSession, CrmQrcDto inCrmQrcDto, Map<String, Long> inEvicts )
    {
        if ( StringUtils.isValidObj( inCrmQrcDto.getRemarksPojo() ) )
        {
            RemarksPojo remarksPojo = new RemarksPojo();
            remarksPojo.setRemarks( inCrmQrcDto.getRemarksPojo().getRemarks() );
            LOGGER.info( "Remarks mapping id:: " + inCrmQrcDto.getCrmQrcWhitelistPojo().getRecordId() );
            remarksPojo.setMappingId( inCrmQrcDto.getCrmQrcWhitelistPojo().getRecordId() + "" );
            remarksPojo.setReasonId( inCrmQrcDto.getCrmQrcWhitelistPojo().getReason() );
            remarksPojo.setActions( inCrmQrcDto.getCrmQrcWhitelistPojo().getWhitelistType() );
            remarksPojo.setCreatedBy( inCrmQrcDto.getUserId() );
            remarksPojo.setCreatedTime( Calendar.getInstance().getTime() );
            remarksPojo.setActivityId( inCrmQrcDto.getRemarksPojo().getActivityId() );
            remarksPojo.setMappingType( CRMRCAReason.QRC.getStatusCode() );
            Long id = (Long) inSession.save( remarksPojo );
            inEvicts.put( RemarksPojo.class.getName(), null );
            LOGGER.info( "Generated remarks id:: " + id );
        }
    }

    private void saveRemarksDetail( CrmQrcDto inCrmQrcDto, long inActivityId )
    {
        if ( StringUtils.isValidObj( inCrmQrcDto.getCrmQrcCommonPojo().getRemarksPojo() ) )
        {
            RemarksPojo remarksPojo = inCrmQrcDto.getCrmQrcCommonPojo().getRemarksPojo();
            remarksPojo.setMappingId( inCrmQrcDto.getCrmQrcCommonPojo().getCustomerId() );
            remarksPojo.setActions( inCrmQrcDto.getCrmQrcCommonPojo().getAction() );
            remarksPojo.setCreatedBy( inCrmQrcDto.getUserId() );
            remarksPojo.setActivityId( inActivityId );
            remarksPojo.setReasonId( inCrmQrcDto.getCrmQrcCommonPojo().getRemarksPojo().getReasonId() );
            remarksPojo.setMappingType( CRMRCAReason.QRC.getStatusCode() );
            remarksPojo.setCreatedTime( Calendar.getInstance().getTime() );
            CRMServiceUtils.saveDBValues( remarksPojo );
        }
    }

    @Override
    public CrmQrcDto bulkWhiteList( CrmQrcDto inCrmQrcDto )
    {
        Session session = null;
        Criteria criteria = null;
        Long id = null;
        CRMServiceCode crmServiceCode = CRMServiceCode.CRM999;
        try
        {
            MDC.put( "KEY", "CUSTID:" + inCrmQrcDto.getCustomerId() + ", USERID:" + inCrmQrcDto.getUserId() );
            session = HibernateUtil.getCurrentSession();
            List<CrmQrcWhitelistPojo> actualWhitelistPojosList = getCustomersByStatus( inCrmQrcDto, criteria, session );
            if ( CommonValidator.isValidCollection( actualWhitelistPojosList ) )
            {
                CrmQrcDto crmQrcDto = new CrmQrcDto();
                crmQrcDto.setUserId( inCrmQrcDto.getUserId() );
                for ( CrmQrcWhitelistPojo crmQrcWhitelistPojo : actualWhitelistPojosList )
                {
                    crmQrcDto.setCrmQrcWhitelistPojo( crmQrcWhitelistPojo );
                    crmQrcDto.setRemarksPojo( inCrmQrcDto.getRemarksPojo() );
                    addUpdateRemoveWhiteList( crmQrcDto );
                    if ( !StringUtils.equals( CRMServiceCode.CRM001.getStatusCode(), crmQrcDto.getStatusCode() ) )
                    {
                        String messsage = crmQrcDto.getStatusDesc();
                        if ( StringUtils.isNotBlank( crmQrcDto.getBillingErrorCode() ) )
                        {
                            messsage += crmQrcDto.getBillingErrorCode();
                        }
                        inCrmQrcDto.getFailedCustomerMapList().put( crmQrcWhitelistPojo.getCustomerId(), messsage );
                    }
                }
            }
            crmServiceCode = CRMServiceCode.CRM001;
        }
        catch ( Exception ex )
        {
            LOGGER.error( "Getting Error while Add bulk whitelist customer: ", ex );
            crmServiceCode = CRMServiceCode.CRM999;
        }
        finally
        {
            CRMServiceUtils.closeSession( session );
            inCrmQrcDto.setStatusCode( crmServiceCode.getStatusCode() );
            inCrmQrcDto.setStatusDesc( crmServiceCode.getStatusDesc() );
            if ( CRMServiceCode.CRM001 == crmServiceCode )
            {
                HibernateUtil.evictAll( CrmQrcWhitelistPojo.class.getName(), null, id );
            }
            MDC.remove( "KEY" );
        }
        return inCrmQrcDto;
    }

    private boolean updateAccesories( CrmCustAssetDetailsPojo crmCustAssetDetailsPojo, String param, String userId )
    {
        CrmCustAssetDetailsPojo dbCrmCustAssetPojo = CRMServiceUtils.getDBValues( CrmCustAssetDetailsPojo.class,
                                                                                  crmCustAssetDetailsPojo
                                                                                          .getAssetDetailsId() );
        if ( StringUtils.isValidObj( dbCrmCustAssetPojo ) )
        {
            dbCrmCustAssetPojo.setCategoryValue( crmCustAssetDetailsPojo.getCategoryValue() );
            dbCrmCustAssetPojo.setCategoryAmount( crmCustAssetDetailsPojo.getCategoryAmount() );
            dbCrmCustAssetPojo.setCategoryCount( (byte) 1 );
            dbCrmCustAssetPojo.setStatus( crmCustAssetDetailsPojo.getStatus() );
            dbCrmCustAssetPojo.setLastModifiedTime( Calendar.getInstance().getTime() );
            dbCrmCustAssetPojo.setLastModifiedBy( userId );
            CRMServiceUtils.mergeDBValues( dbCrmCustAssetPojo );
            return true;
        }
        else
        {
            LOGGER.info( "asset details not found in DB by asset id ::" + crmCustAssetDetailsPojo.getAssetDetailsId() );
            return false;
        }
    }

    private boolean createAccessories( CrmCustAssetDetailsPojo crmCustAssetDetailsPojo, String param, String userId )
    {
        boolean returnValue = false;
        crmCustAssetDetailsPojo.setCreatedTime( Calendar.getInstance().getTime() );
        crmCustAssetDetailsPojo.setCreatedBy( userId );
        crmCustAssetDetailsPojo.setCategoryName( param );
        if ( StringUtils.equals( param, CRMParameter.STATIC_IP.getParameter() ) )
        {
            crmCustAssetDetailsPojo.setCategoryCount( (byte) 1 );
            CRMServiceUtils.saveDBValues( crmCustAssetDetailsPojo );
            returnValue = true;
        }
        else
        {
            crmCustAssetDetailsPojo.setStatus( CRMStatusCode.ACTIVE.getStatusCode() );
            CRMServiceUtils.saveDBValues( crmCustAssetDetailsPojo );
            returnValue = true;
        }
        return returnValue;
    }

    @Override
    public CrmQrcDto saveAccessories( CrmQrcDto inCrmQrcDto, String param )
    {
        Session session = null;
        Transaction transaction = null;
        boolean successFlag = false;
        CrmCustomerActivityPojo activityLog = null;
        Criteria criteria = null;
        List<CrmCustAssetDetailsPojo> oldAssetDetailsPojos = null;
        CrmCustAssetDetailsPojo oldAssetDetailsPojo = null;
        StringBuilder oldIPBuilder = new StringBuilder();
        CRMServiceCode crmServiceCode = CRMServiceCode.CRM999;
        try
        {
            MDC.put( "KEY", "CUSTID:" + inCrmQrcDto.getCustomerId() + ", USERID:" + inCrmQrcDto.getUserId() );
            session = HibernateUtil.getCurrentSession();
            transaction = session.beginTransaction();
            activityLog = new CrmCustomerActivityPojo();
            CrmCustAssetDetailsPojo assetDetailsPojo = null;
            CRMCustomerActivityActions activityAction = null;
            if ( StringUtils.isNotEmpty( inCrmQrcDto.getSrTicketNo() ) )
            {
                if ( !isValidTicketNo( inCrmQrcDto.getSrTicketNo(), inCrmQrcDto.getCustomerId() ) )
                {
                    crmServiceCode = CRMServiceCode.CRM402;
                }
                else
                {
                    activityLog.setTicketId( inCrmQrcDto.getSrTicketNo() );
                }
            }
            if ( !StringUtils.equals( crmServiceCode.getStatusCode(), CRMServiceCode.CRM402.getStatusCode() ) )
            {
                CrmBillingDto billingDto = new CrmBillingDto();
                billingDto.setCustomerId( inCrmQrcDto.getCustomerId() );
                //getCustomerDetail
                CrmCustomerDetailsPojo customerPojo = CRMServiceUtils
                        .getDBValues( CrmCustomerDetailsPojo.class, "customerId", inCrmQrcDto.getCustomerId(), session );
                if ( StringUtils.equals( param, CRMParameter.STATIC_IP.getParameter() ) )
                {
                    oldAssetDetailsPojos = CRMServiceUtils.getDBValueList( CrmCustAssetDetailsPojo.class,
                                                                           "customerRecordId",
                                                                           customerPojo.getRecordId(), session );
                    if ( CommonValidator.isValidCollection( oldAssetDetailsPojos ) )
                    {
                        for ( CrmCustAssetDetailsPojo pojo : oldAssetDetailsPojos )
                        {
                            oldIPBuilder.append( pojo.getCategoryValue() );
                            oldIPBuilder.append( IAppConstants.COMMA );
                        }
                    }
                    activityAction = CRMCustomerActivityActions.STATIC_IP;
                    inCrmQrcDto.setActivityAction( CRMCustomerActivityActions.STATIC_IP.getActionCode() );
                    if ( CommonValidator.isValidCollection( inCrmQrcDto.getCrmCustAssetDetailsPojos() ) )
                    {
                        LOGGER.info( "CustAssetDetailsPojos size::" + inCrmQrcDto.getCrmCustAssetDetailsPojos().size() );
                        for ( CrmCustAssetDetailsPojo assetPojo : inCrmQrcDto.getCrmCustAssetDetailsPojos() )
                        {
                            String assetPojoValid = ValidationPojoUtil
                                    .validatePojo( assetPojo, ICRMValidationCriteriaUtil.QRC_ADD_STATICIP_POJO );
                            if ( StringUtils.isValidObj( assetPojoValid ) )
                            {
                                successFlag = false;
                                LOGGER.info( "assett pojo value:" + assetPojoValid );
                                crmServiceCode = CRMServiceCode.valueOf( assetPojoValid );
                                inCrmQrcDto.setStatusCode( crmServiceCode.getStatusCode() );
                                break;
                            }
                            else
                            {
                                criteria = session.createCriteria( CrmCustAssetDetailsPojo.class );
                                criteria.add( Restrictions.eq( "categoryName", param ) );
                                criteria.add( Restrictions.eq( "categoryValue", assetPojo.getCategoryValue() ) );
                                criteria.add( Restrictions.eq( "status", assetPojo.getStatus() ) );
                                criteria.add( Restrictions.ne( IAppConstants.PARAM_CUSTOMER_RECORD_ID,
                                                               assetPojo.getCustomerRecordId() ) );
                                criteria.setCacheable( true );
                                criteria.setCacheRegion( CrmCustAssetDetailsPojo.class.getName() );
                                List<CrmCustAssetDetailsPojo> dbCrmCustAssetPojos = criteria.list();
                                if ( CommonValidator.isValidCollection( dbCrmCustAssetPojos )
                                        && ! ( StringUtils.equals( customerPojo.getStatus(),
                                                                   CRMStatusCode.PDC.getStatusCode() ) ) )
                                {
                                    crmServiceCode = CRMServiceCode.CRM319;
                                    LOGGER.info( "Active IP is associated with any other customer."
                                            + assetPojo.getCategoryValue() );
                                    break;
                                }
                                else
                                {
                                    crmServiceCode = CRMServiceCode.CRM001;
                                    assetDetailsPojo = new CrmCustAssetDetailsPojo();
                                    assetDetailsPojo.setCategoryValue( assetPojo.getCategoryValue() );
                                    inCrmQrcDto.setCrmCustAssetDetailsPojo( assetDetailsPojo );
                                    LOGGER.info( "Asset object status:: " + assetPojo.getStatus() );
                                    if ( StringUtils.equals( assetPojo.getStatus(), "A" ) )
                                    {
                                        billingDto = getCrmBillingManager().changeDeviceDetails( inCrmQrcDto );
                                        inCrmQrcDto.setCrmCustAssetDetailsPojo( null );
                                    }
                                    ///success code
                                    if ( StringUtils.equals( CRMServiceCode.CRM001.getStatusCode(),
                                                             crmServiceCode.getStatusCode() ) )
                                    {
                                        if ( assetPojo.getAssetDetailsId() > 0 )
                                        {
                                            successFlag = updateAccesories( assetPojo, param, inCrmQrcDto.getUserId() );
                                        }
                                        else
                                        {
                                            successFlag = createAccessories( assetPojo, param, inCrmQrcDto.getUserId() );
                                        }
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
                    else
                    {
                        successFlag = false;
                        LOGGER.info( "pojos not found in DTO" );
                        crmServiceCode = CRMServiceCode.CRM997;
                    }
                }
                else
                {
                    LOGGER.info( "Wiring and  Static IP charges" );
                    if ( StringUtils.isValidObj( inCrmQrcDto.getCrmCustAssetDetailsPojo() ) )
                    {
                        assetDetailsPojo = inCrmQrcDto.getCrmCustAssetDetailsPojo();
                        String assetPojoValid = ValidationPojoUtil
                                .validatePojo( assetDetailsPojo, ICRMValidationCriteriaUtil.QRC_COMMON_ADD_CHARGE );
                        if ( StringUtils.isValidObj( assetPojoValid ) )
                        {
                            successFlag = false;
                            crmServiceCode = CRMServiceCode.valueOf( assetPojoValid );
                            inCrmQrcDto.setStatusCode( crmServiceCode.getStatusCode() );
                        }
                        else if ( StringUtils.equals( inCrmQrcDto.getCustomerDetailsPojo().getServiceType(),
                                                      CRMDisplayListConstants.PRE_PAID.getCode() )
                                && StringUtils.isValidObj( inCrmQrcDto.getCrmShiftingWorkflowPojo() ) )
                        {
                            LOGGER.info( "Customer is prepaid..." );
                            inCrmQrcDto.setStatusCode( CRMServiceCode.CRM001.getStatusCode() );
                            successFlag = createAccessories( assetDetailsPojo, param, inCrmQrcDto.getUserId() );
                            activityAction = CRMCustomerActivityActions.WIRING_CHARGES;
                            if ( !successFlag )
                            {
                                crmServiceCode = CRMServiceCode.CRM308;
                                inCrmQrcDto.setCrmCustAssetDetailsPojo( assetDetailsPojo );
                            }
                        }
                        else
                        {
                            LOGGER.info( "customer is postpaid" );
                            /** billing call for Wiring/Static IP Charge **/
                            if ( StringUtils.equals( param, CRMParameter.WIRING.getParameter() ) )
                            {
                                if ( StringUtils.equals( customerPojo.getServiceType(),
                                                         CRMDisplayListConstants.POST_PAID.getCode() ) )
                                {
                                    billingDto.setChargeName( "Wiring Charges_POST" );
                                }
                                else if ( StringUtils.equals( customerPojo.getServiceType(),
                                                              CRMDisplayListConstants.PRE_PAID.getCode() ) )
                                {
                                    billingDto.setChargeName( "Installation Charges" );
                                }
                                activityAction = CRMCustomerActivityActions.WIRING_CHARGES;
                            }
                            else
                            {
                                //added charges of PRE& POST
                                if ( StringUtils.equals( customerPojo.getServiceType(),
                                                         CRMDisplayListConstants.POST_PAID.getCode() ) )
                                {
                                    billingDto.setChargeName( "Static Charges_POST" );
                                }
                                else if ( StringUtils.equals( customerPojo.getServiceType(),
                                                              CRMDisplayListConstants.PRE_PAID.getCode() ) )
                                {
                                    billingDto.setChargeName( "Static IP Charges" );
                                }
                                activityAction = CRMCustomerActivityActions.STATIC_IP_CHARGES;
                            }
                            billingDto.setChargeAmount( new BigDecimal( assetDetailsPojo.getCategoryAmount() ) );
                            getCrmBillingManager().addCharge( billingDto );
                            if ( StringUtils.equals( CRMServiceCode.CRM001.getStatusCode(), billingDto.getStatusCode() ) )
                            {
                                successFlag = createAccessories( assetDetailsPojo, param, inCrmQrcDto.getUserId() );
                            }
                            else
                            {
                                crmServiceCode = CRMServiceCode.valueOf( billingDto.getStatusCode() );
                                inCrmQrcDto.setBillingErrorCode( billingDto.getBillingErrorCode() );
                                LOGGER.info( "Status Code ::" + crmServiceCode );
                            }
                        }
                    }
                    else
                    {
                        successFlag = false;
                        LOGGER.info( "CustAssetDetailsPojo empty @server" );
                        crmServiceCode = CRMServiceCode.CRM992;
                    }
                }
                if ( successFlag )
                {
                    inCrmQrcDto.getRemarksPojo().setActions( activityAction.getActionDesc() );
                    if ( StringUtils.isEmpty( activityLog.getTicketId() )
                            && StringUtils.equals( param, CRMParameter.STATIC_IP.getParameter() ) )
                    {
                        CrmQrcDto tmpDto = CRMServiceUtils
                                .generateAutoTicket( inCrmQrcDto.getCustomerId(),
                                                     QRCRolCategories.ADD_OR_REMOVE_ACCESSORY, null,
                                                     inCrmQrcDto.getUserId(), inCrmQrcDto.getRemarksPojo().getRemarks() );
                        if ( StringUtils.equals( CRMServiceCode.CRM001.getStatusCode(), tmpDto.getStatusCode() ) )
                        {
                            activityLog.setTicketId( tmpDto.getCrmSrTicketsPojo().getSrId() );
                            inCrmQrcDto.setSrTicketNo( tmpDto.getCrmSrTicketsPojo().getSrId() );
                        }
                    }
                    else
                    {
                        if ( StringUtils.isValidObj( inCrmQrcDto.getRemarksPojo() ) )
                        {
                            if ( StringUtils.isBlank( inCrmQrcDto.getRemarksPojo().getMappingId() ) )
                            {
                                inCrmQrcDto.getRemarksPojo().setMappingId( inCrmQrcDto.getCustomerId() );
                            }
                            inCrmQrcDto.getRemarksPojo().setCreatedBy( inCrmQrcDto.getUserId() );
                            inCrmQrcDto.getRemarksPojo().setCreatedTime( Calendar.getInstance().getTime() );
                            inCrmQrcDto.getRemarksPojo().setMappingType( CRMRCAReason.QRC.getStatusCode() );
                            inCrmQrcDto.getRemarksPojo().setReasonId( inCrmQrcDto.getRemarksPojo().getReasonId() );
                            session.save( inCrmQrcDto.getRemarksPojo() );
                        }
                    }
                    if ( StringUtils.isValidObj( activityAction ) && StringUtils.isValidObj( assetDetailsPojo ) )
                    {
                        /** generate Customer Activity **/
                        String oldIPList = oldIPBuilder.toString();
                        if ( oldIPList.contains( IAppConstants.COMMA ) )
                        {
                            oldIPList = StringUtils.removeEnd( oldIPList, IAppConstants.COMMA );
                            oldAssetDetailsPojo = new CrmCustAssetDetailsPojo();
                            oldAssetDetailsPojo.setCategoryValue( oldIPList );
                        }
                        CRMServiceUtils.fillActivityDetails( oldAssetDetailsPojo, assetDetailsPojo, activityLog,
                                                             activityAction );
                        generateCustomerActivity( inCrmQrcDto, activityLog, activityAction );
                    }
                    transaction.commit();
                    crmServiceCode = CRMServiceCode.CRM001;
                }
            }
        }
        catch ( HibernateException ex )
        {
            LOGGER.error( "HibernateException while adding/updating staticIp/wiring ", ex );
            crmServiceCode = CRMServiceCode.CRM999;
            CRMServiceUtils.rollback( transaction );
            CRMServiceUtils.closeSessionOnException( session );
        }
        catch ( Exception ex )
        {
            LOGGER.error( "Getting Error while adding/updating staticIp/wiring ", ex );
            crmServiceCode = CRMServiceCode.CRM999;
        }
        finally
        {
            CRMServiceUtils.rollback( transaction );
            CRMServiceUtils.closeSession( session );
            LOGGER.info( "setting status desc:" + crmServiceCode.getStatusDesc() );
            inCrmQrcDto.setStatusCode( crmServiceCode.getStatusCode() );
            inCrmQrcDto.setStatusDesc( crmServiceCode.getStatusDesc() );
            if ( crmServiceCode == CRMServiceCode.CRM001 )
            {
                HibernateUtil.evictAll( CrmCustAssetDetailsPojo.class.getName(), null, null );
                HibernateUtil.evictAll( RemarksPojo.class.getName(), null, null );
            }
            MDC.remove( "KEY" );
        }
        return inCrmQrcDto;
    }

    private List<CrmQrcWhitelistPojo> getCustomersByStatus( CrmQrcDto inCrmQrcDto, Criteria criteria, Session session )
    {
        Map<String, String> failedCustomerMapList = new HashMap<String, String>();
        List<CrmQrcWhitelistPojo> saveCustomerInWhiteList = new ArrayList<CrmQrcWhitelistPojo>();
        if ( CommonValidator.isValidCollection( inCrmQrcDto.getCrmQrcWhitelistPojoList() ) )
        {
            for ( CrmQrcWhitelistPojo crmQrcWhitelistPojo : inCrmQrcDto.getCrmQrcWhitelistPojoList() )
            {
                criteria = session.createCriteria( CrmCustomerDetailsPojo.class );
                criteria.add( Restrictions.eq( "customerId", crmQrcWhitelistPojo.getCustomerId() ) );
                criteria.setCacheable( true );
                criteria.setCacheRegion( CrmCustomerDetailsPojo.class.getName() );
                List<CrmCustomerDetailsPojo> crmCustomerDetailsPojos = criteria.list();
                if ( CommonValidator.isValidCollection( crmCustomerDetailsPojos ) )
                {
                    if ( StringUtils.equals( crmQrcWhitelistPojo.getWhitelistType(),
                                             CRMStatusCode.BARRED.getStatusDesc() ) )
                    {
                        if ( StringUtils.equals( crmCustomerDetailsPojos.get( 0 ).getServiceType(),
                                                 CRMDisplayListConstants.PRE_PAID.getCode() ) )
                        {
                            failedCustomerMapList.put( crmQrcWhitelistPojo.getCustomerId(), "Customer service type is "
                                    + CRMDisplayListConstants.PRE_PAID.getValue() );
                        }
                        else if ( crmCustomerDetailsPojos.get( 0 ).getStatus()
                                .equals( CRMStatusCode.ACTIVE.getStatusCode() ) )
                        {
                            saveCustomerInWhiteList.add( crmQrcWhitelistPojo );
                        }
                        else
                        {
                            failedCustomerMapList.put( crmQrcWhitelistPojo.getCustomerId(),
                                                       "Customer status not active" );
                        }
                    }
                    else if ( StringUtils.equals( crmQrcWhitelistPojo.getWhitelistType(),
                                                  CRMStatusCode.UNBARRED.getStatusDesc() ) )
                    {
                        if ( StringUtils.equals( crmCustomerDetailsPojos.get( 0 ).getServiceType(),
                                                 CRMDisplayListConstants.PRE_PAID.getCode() ) )
                        {
                            failedCustomerMapList.put( crmQrcWhitelistPojo.getCustomerId(), "Customer service type is "
                                    + CRMDisplayListConstants.PRE_PAID.getValue() );
                        }
                        else if ( crmCustomerDetailsPojos.get( 0 ).equals( CRMStatusCode.BARRED.getStatusCode() ) )
                        {
                            saveCustomerInWhiteList.add( crmQrcWhitelistPojo );
                        }
                        else
                        {
                            failedCustomerMapList.put( crmQrcWhitelistPojo.getCustomerId(),
                                                       "Customer status not barred" );
                        }
                    }
                }
                else
                {
                    failedCustomerMapList.put( crmQrcWhitelistPojo.getCustomerId(), "Customer not exist" );
                }
            }
        }
        LOGGER.info( "Failed whitelist Customer map size:: " + failedCustomerMapList.size() );
        LOGGER.info( "Success whitelist Customer map size:: " + saveCustomerInWhiteList.size() );
        inCrmQrcDto.setFailedCustomerMapList( failedCustomerMapList );
        return saveCustomerInWhiteList;
    }

    @Override
    public CrmQrcDto barringUnbarringCustService( CrmQrcDto inCrmQrcDto )
    {
        Session session = null;
        Transaction transaction = null;
        CrmCustomerActivityPojo activityLog = null;
        CRMServiceCode crmServiceCode = CRMServiceCode.CRM999;
        try
        {
            MDC.put( "KEY", "CUSTID:" + inCrmQrcDto.getCustomerId() + ", USERID:" + inCrmQrcDto.getUserId() );
            session = HibernateUtil.getCurrentSession();
            transaction = session.beginTransaction();
            activityLog = new CrmCustomerActivityPojo();
            if ( StringUtils.isNotEmpty( inCrmQrcDto.getSrTicketNo() ) )
            {
                if ( !isValidTicketNo( inCrmQrcDto.getSrTicketNo(), inCrmQrcDto.getCrmQrcCommonPojo().getCustomerId() ) )
                {
                    crmServiceCode = CRMServiceCode.CRM402;
                }
                else
                {
                    activityLog.setTicketId( inCrmQrcDto.getSrTicketNo() );
                }
            }
            if ( !StringUtils.equals( crmServiceCode.getStatusCode(), CRMServiceCode.CRM402.getStatusCode() ) )
            {
                CrmCustomerDetailsPojo crmCustomerDetailsPojo = (CrmCustomerDetailsPojo) session
                        .get( CrmCustomerDetailsPojo.class, inCrmQrcDto.getCrmQrcCommonPojo().getCustomerRecordId() );
                if ( StringUtils.isValidObj( crmCustomerDetailsPojo ) )
                {
                    if ( StringUtils.equals( crmCustomerDetailsPojo.getServiceType(),
                                             CRMDisplayListConstants.POST_PAID.getCode() ) )
                        checkCustomerInWhitelist( inCrmQrcDto, crmCustomerDetailsPojo );
                    else
                        inCrmQrcDto.setCrmQrcWhitelistPojo( new CrmQrcWhitelistPojo() );
                    if ( !StringUtils.equals( inCrmQrcDto.getStatusCode(), CRMServiceCode.CRM403.getStatusCode() ) )
                    {
                        if ( StringUtils.equals( crmCustomerDetailsPojo.getStatus(),
                                                 CRMStatusCode.ACTIVE.getStatusCode() ) )
                        {
                            crmCustomerDetailsPojo.setStatus( CRMStatusCode.BARRED.getStatusCode() );
                            crmCustomerDetailsPojo.setBarringDate( Calendar.getInstance().getTime() );
                            inCrmQrcDto.getCrmQrcWhitelistPojo().setWhitelistType( CRMStatusCode.UNBARRED
                                                                                           .getStatusDesc() );
                            activityLog.setOldValue( CRMStatusCode.ACTIVE.getStatusDesc() );
                            activityLog.setNewValue( CRMStatusCode.BARRED.getStatusDesc() );
                            activityLog.setAction( CRMCustomerActivityActions.BARRING.getActionCode() );
                        }
                        else if ( StringUtils.equals( crmCustomerDetailsPojo.getStatus(),
                                                      CRMStatusCode.BARRED.getStatusCode() ) )
                        {
                            crmCustomerDetailsPojo.setStatus( CRMStatusCode.ACTIVE.getStatusCode() );
                            crmCustomerDetailsPojo.setUnBarringDate( Calendar.getInstance().getTime() );
                            inCrmQrcDto.getCrmQrcWhitelistPojo()
                                    .setWhitelistType( CRMStatusCode.BARRED.getStatusDesc() );
                            activityLog.setOldValue( CRMStatusCode.BARRED.getStatusDesc() );
                            activityLog.setNewValue( CRMStatusCode.ACTIVE.getStatusDesc() );
                            activityLog.setAction( CRMCustomerActivityActions.UNBARRING.getActionCode() );
                        }
                        // calling Update Customer Status Web Service API
                        boolean toCommit = false;
                        CrmBillingDto billingDto = new CrmBillingDto();
                        billingDto.setCustomerId( inCrmQrcDto.getCrmQrcCommonPojo().getCustomerId() );
                        billingDto.setStatus( crmCustomerDetailsPojo.getStatus() );
                        billingDto.setRemarks( inCrmQrcDto.getRemarksPojo().getRemarks() );
                        getCrmBillingManager().changeStatus( billingDto );
                        LOGGER.info( "Status Code::" + billingDto.getStatusCode() );
                        if ( StringUtils.equals( CRMServiceCode.CRM001.getStatusCode(), billingDto.getStatusCode() ) )
                        {
                            crmCustomerDetailsPojo.setLastModifiedBy( inCrmQrcDto.getUserId() );
                            crmCustomerDetailsPojo.setLastModifiedTime( Calendar.getInstance().getTime() );
                            toCommit = true;
                        }
                        else
                        {
                            crmServiceCode = CRMServiceCode.valueOf( billingDto.getStatusCode() );
                            inCrmQrcDto.setBillingErrorCode( billingDto.getBillingErrorCode() );
                        }
                        if ( toCommit )
                        {
                            session.update( crmCustomerDetailsPojo );
                            transaction.commit();
                            if ( StringUtils.equals( inCrmQrcDto.getCrmQrcCommonPojo().getAction(),
                                                     CRMCustomerActivityActions.UNBARRING.getActionDesc() ) )
                            {
                                if ( StringUtils.isEmpty( activityLog.getTicketId() ) )
                                {
                                    if ( StringUtils.isValidObj( inCrmQrcDto.getCrmQrcCommonPojo().getRcaReasonPojo() ) )
                                    {
                                        QRCRolCategories action = QRCRolCategories.getQRCCategoriesByEvent( inCrmQrcDto
                                                .getCrmQrcCommonPojo().getRcaReasonPojo().getCategoryValue() );
                                        LOGGER.info( "Action:: " + action );
                                        if ( StringUtils.isValidObj( action ) )
                                        {
                                            CrmQrcDto tmpDto = CRMServiceUtils
                                                    .generateAutoTicket( inCrmQrcDto.getCustomerId(), action, null,
                                                                         inCrmQrcDto.getUserId(), inCrmQrcDto
                                                                                 .getCrmQrcCommonPojo()
                                                                                 .getRemarksPojo().getRemarks() );
                                            if ( StringUtils.equals( CRMServiceCode.CRM001.getStatusCode(),
                                                                     tmpDto.getStatusCode() ) )
                                            {
                                                activityLog.setTicketId( tmpDto.getCrmSrTicketsPojo().getSrId() );
                                                inCrmQrcDto.setSrTicketNo( tmpDto.getCrmSrTicketsPojo().getSrId() );
                                            }
                                        }
                                    }
                                }
                            }
                            preparedCustomerActivityLog( inCrmQrcDto, activityLog, crmCustomerDetailsPojo );
                            long activityId = CRMServiceUtils.createActivityLog( activityLog );
                            saveRemarksDetail( inCrmQrcDto, activityId );
                            if ( StringUtils.equals( crmCustomerDetailsPojo.getServiceType(),
                                                     CRMDisplayListConstants.POST_PAID.getCode() ) )
                                insertWhitelist( inCrmQrcDto );
                            crmServiceCode = CRMServiceCode.CRM001;
                        }
                    }
                    else
                    {
                        crmServiceCode = CRMServiceCode.CRM403;
                    }
                }
                else
                    crmServiceCode = CRMServiceCode.CRM408;
            }
        }
        catch ( HibernateException ex )
        {
            LOGGER.error( "HibernateException while barring Unbarring CustService:", ex );
            crmServiceCode = CRMServiceCode.CRM999;
            CRMServiceUtils.rollback( transaction );
            CRMServiceUtils.closeSessionOnException( session );
        }
        catch ( Exception ex )
        {
            LOGGER.error( "Getting Error while barring Unbarring CustService: ", ex );
            crmServiceCode = CRMServiceCode.CRM999;
        }
        finally
        {
            CRMServiceUtils.rollback( transaction );
            CRMServiceUtils.closeSession( session );
            if ( StringUtils.isValidObj( crmServiceCode ) )
            {
                inCrmQrcDto.setStatusCode( crmServiceCode.getStatusCode() );
                inCrmQrcDto.setStatusDesc( crmServiceCode.getStatusDesc() );
            }
            if ( crmServiceCode == CRMServiceCode.CRM001 )
            {
                HibernateUtil.evictAll( CrmCustomerDetailsPojo.class.getName(), null, inCrmQrcDto.getCrmQrcCommonPojo()
                        .getCustomerId() );
                HibernateUtil.evictAll( RemarksPojo.class.getName(), null, null );
            }
            MDC.remove( "KEY" );
        }
        return inCrmQrcDto;
    }

    //    @Override
    //    public CrmSrTicketsPojo preparedQrcTicketPojo( String customerId, String categoryValue )
    //    {
    //        return preparedQrcTicketPojo( customerId, categoryValue, null );
    //    }
    private CrmSrTicketsPojo preparedQrcTicketPojo( String customerId,
                                                    String categoryValue,
                                                    CrmSrTicketsPojo ticketsPojo )
    {
        LOGGER.info( "Inside QrcManagerDaoImpl, preparedQrcTicketPojo" );
        QRCRolCategories action = QRCRolCategories.getQRCCategoriesByEvent( categoryValue );
        CrmQrcCategoriesPojo crmCategoriesPojo = callingToGetCategoryId( action );
        if ( StringUtils.isValidObj( crmCategoriesPojo ) )
        {
            if ( !StringUtils.isValidObj( ticketsPojo ) )
            {
                ticketsPojo = new CrmSrTicketsPojo();
                ticketsPojo.setResolutionType( (byte) action.getResolutionType() );
                ticketsPojo.setQrcType( action.getQrcType() );
                ticketsPojo.setStatus( CRMStatusCode.OPEN.getStatusCode() );
            }
            ticketsPojo.setMappingId( customerId );
            ticketsPojo.setQrcCategoryId( crmCategoriesPojo.getQrcCategoryId() );
            ticketsPojo.setModuleType( CRMRequestType.QRC.getRequestCode() );
            LOGGER.info( "QRC Category Id:: " + crmCategoriesPojo.getQrcCategoryId() );
            for ( CrmQrcSubCategoriesPojo crmQrcCategoriesPojo : crmCategoriesPojo.getCrmQrcSubCategorieses() )
            {
                ticketsPojo.setQrcSubCategoryId( crmQrcCategoriesPojo.getQrcSubCategoryId() );
                LOGGER.info( "QRC Sub Category Id:: " + crmQrcCategoriesPojo.getQrcSubCategoryId() );
                for ( CrmQrcSubSubCategoriesPojo subSubCategoriesPojo : crmQrcCategoriesPojo
                        .getCrmQrcSubSubCategorieses() )
                {
                    LOGGER.info( "QRC Sub Sub Category Id:: " + subSubCategoriesPojo.getQrcSubSubCategoryId() );
                    ticketsPojo.setQrcSubSubCategoryId( subSubCategoriesPojo.getQrcSubSubCategoryId() );
                    ticketsPojo.setFunctionalbinId( subSubCategoriesPojo.getFunctionalBinId() );
                }
            }
        }
        return ticketsPojo;
    }

    private CrmQrcCategoriesPojo callingToGetCategoryId( QRCRolCategories inAction )
    {
        LOGGER.info( "Inside QrcManagerDaoImpl, callingToGetCategoryId" );
        LOGGER.info( "RCA Category Action : " + inAction );
        List<CrmQrcCategoriesPojo> crmQrcCategoriesPojos = null;
        if ( StringUtils.isValidObj( inAction ) )
        {
            String category = inAction.getCategory();
            String subCategory = inAction.getSubCategory();
            String subSubCategory = inAction.getSubSubCategory();
            LOGGER.info( "Category: \"" + category + "\" Sub-Category: \"" + subCategory + "\" Sub-Sub-Category: \""
                    + subSubCategory );
            if ( StringUtils.isNotBlank( category ) && StringUtils.isNotBlank( subCategory )
                    && StringUtils.isNotBlank( subSubCategory ) )
            {
                crmQrcCategoriesPojos = getQrcCategoriesID( category, subCategory, subSubCategory );
            }
        }
        if ( CommonValidator.isValidCollection( crmQrcCategoriesPojos ) )
        {
            LOGGER.info( "Category ID::" + crmQrcCategoriesPojos.get( 0 ).getQrcCategoryId() );
            return crmQrcCategoriesPojos.get( 0 );
        }
        return null;
    }

    private void preparedCustomerActivityLog( CrmQrcDto inCrmQrcDto,
                                              CrmCustomerActivityPojo activityLog,
                                              CrmCustomerDetailsPojo crmCustomerDetailsPojo )
    {
        activityLog.setCustomerId( crmCustomerDetailsPojo.getCustomerId() );
        activityLog.setReason( inCrmQrcDto.getCrmQrcCommonPojo().getRcaReasonPojo().getCategoryValue() );
        activityLog.setServiceIp( CRMUtils.getServerIp() );
        activityLog.setClientIp( inCrmQrcDto.getClientIPAddress() );
        activityLog.setServerIp( inCrmQrcDto.getServerIPAddress() );
        activityLog.setCreatedBy( inCrmQrcDto.getUserId() );
    }

    private void generateCustomerActivity( CrmQrcDto inCrmQrcDto,
                                           CrmCustomerActivityPojo activityLog,
                                           CRMCustomerActivityActions inActivityAction )
    {
        activityLog.setCustomerId( inCrmQrcDto.getCustomerId() );
        activityLog.setAction( inActivityAction.getActionCode() );
        activityLog.setCreatedBy( inCrmQrcDto.getUserId() );
        activityLog.setServiceIp( CRMUtils.getServerIp() );
        activityLog.setClientIp( inCrmQrcDto.getClientIPAddress() );
        activityLog.setServerIp( inCrmQrcDto.getServerIPAddress() );
        CRMServiceUtils.createActivityLog( activityLog );
    }

    private void checkCustomerInWhitelist( CrmQrcDto inCrmQrcDto, CrmCustomerDetailsPojo inCrmCustomerDetailsPojo )
    {
        CrmQrcWhitelistPojo crmQrcWhitelistPojo = new CrmQrcWhitelistPojo();
        crmQrcWhitelistPojo.setCustomerId( inCrmQrcDto.getCrmQrcCommonPojo().getCustomerId() );
        if ( StringUtils.equals( inCrmCustomerDetailsPojo.getStatus(), CRMStatusCode.ACTIVE.getStatusCode() ) )
        {
            crmQrcWhitelistPojo.setWhitelistType( CRMStatusCode.BARRED.getStatusDesc() );
        }
        else if ( StringUtils.equals( inCrmCustomerDetailsPojo.getStatus(), CRMStatusCode.BARRED.getStatusCode() ) )
        {
            crmQrcWhitelistPojo.setWhitelistType( CRMStatusCode.UNBARRED.getStatusDesc() );
        }
        inCrmQrcDto.setCrmQrcWhitelistPojo( crmQrcWhitelistPojo );
        getWhiteListCustomers( inCrmQrcDto );
        if ( StringUtils.equals( inCrmQrcDto.getStatusCode(), CRMServiceCode.CRM001.getStatusCode() ) )
        {
            if ( CommonValidator.isValidCollection( inCrmQrcDto.getCrmQrcWhitelistPojoList() ) )
            {
                crmQrcWhitelistPojo = inCrmQrcDto.getCrmQrcWhitelistPojoList().get( 0 );
                if ( isCustAlreadyInWhiteList( crmQrcWhitelistPojo ) )
                {
                    inCrmQrcDto.setStatusCode( CRMServiceCode.CRM403.getStatusCode() );
                    inCrmQrcDto.setStatusDesc( CRMServiceCode.CRM403.getStatusDesc() );
                }
            }
        }
    }

    private void insertWhitelist( CrmQrcDto inCrmQrcDto )
    {
        LOGGER.info( "Insert White LIst" );
        if ( StringUtils.isValidObj( inCrmQrcDto.getCrmQrcCommonPojo().getRcaReasonPojo() ) )
        {
            LOGGER.info( "RCA reason pojo is valid object" );
            Long reasonTimePeriod = StringUtils.numericValue( inCrmQrcDto.getCrmQrcCommonPojo().getRcaReasonPojo()
                    .getValueAlias() );
            LOGGER.info( "Reason time frame:: " + reasonTimePeriod );
            if ( reasonTimePeriod > 0 )
            {
                String endDate = IDateConstants.SDF_DD_MMM_YYYY_HH_MM_SS.format( DateUtils
                        .getFutureEndDate( reasonTimePeriod.intValue(), Calendar.DAY_OF_YEAR ).getTime() );
                try
                {
                    inCrmQrcDto.getCrmQrcWhitelistPojo().setEndDate( IDateConstants.SDF_DD_MMM_YYYY_HH_MM_SS
                                                                             .parse( endDate ) );
                    inCrmQrcDto.getCrmQrcWhitelistPojo().setReason( inCrmQrcDto.getCrmQrcCommonPojo()
                                                                            .getRcaReasonPojo().getCategoryId() );
                    inCrmQrcDto.setRemarksPojo( inCrmQrcDto.getCrmQrcCommonPojo().getRemarksPojo() );
                }
                catch ( ParseException ex )
                {
                    LOGGER.error( "Date Parsing Exception", ex );
                }
                addUpdateRemoveWhiteList( inCrmQrcDto );
            }
        }
    }

    @Override
    public CrmQrcDto secondLevelAndFurtherBins( CrmQrcDto inCrmQrcDto )
    {
        LOGGER.info( "Inside QrcManagerDaoImpl : secondLevelAndFurtherBins" );
        List<CrmQrcCategoryBinMappingPojo> binMappingPojos = null;
        CrmSrTicketsPojo ticketsPojo = null;
        Session session = null;
        Criteria criteria = null;
        if ( StringUtils.isValidObj( inCrmQrcDto.getCrmSrTicketsPojo() ) )
        {
            ticketsPojo = inCrmQrcDto.getCrmSrTicketsPojo();
            if ( ticketsPojo.getQrcSubSubCategoryId() > 0 && ticketsPojo.getFunctionalbinId() > 0 )
            {
                try
                {
                    session = HibernateUtil.getCurrentSession();
                    criteria = session.createCriteria( CrmQrcCategoryBinMappingPojo.class );
                    criteria.add( Restrictions.eq( "qrcSubSubCategoryId", ticketsPojo.getQrcSubSubCategoryId() ) );
                    criteria.add( Restrictions.eq( "fromBinId", ticketsPojo.getFunctionalbinId() ) );
                    criteria.add( Restrictions.eq( "status", CRMStatusCode.ACTIVE.getStatusCode() ) );
                    criteria.setCacheable( true );
                    criteria.setCacheRegion( CrmQrcCategoryBinMappingPojo.class.getName() );
                    binMappingPojos = criteria.list();
                    if ( CommonValidator.isValidCollection( binMappingPojos ) )
                    {
                        inCrmQrcDto.setCrmQrcCategoryBinMappingPojos( binMappingPojos );
                    }
                }
                catch ( Exception ex )
                {
                    LOGGER.error( "Exception while retreiving second or further level bins ", ex );
                }
                finally
                {
                    CRMServiceUtils.closeSession( session );
                }
            }
        }
        return inCrmQrcDto;
    }

    @Override
    public CrmQrcDto qrcActionTakenList( CrmQrcDto inCrmQrcDto )
    {
        LOGGER.info( "Inside QrcManagerDaoImpl : qrcRcaList" );
        CRMServiceCode crmServiceCode = CRMServiceCode.CRM999;
        List<CrmQrcActionTakenPojo> qrcRcaPojos = null;
        CrmSrTicketsPojo ticketsPojo = null;
        Session session = null;
        Criteria criteria = null;
        if ( StringUtils.isValidObj( inCrmQrcDto.getCrmSrTicketsPojo() )
                && StringUtils.isValidObj( inCrmQrcDto.getCrmSrTicketsPojo().getCustomerDetailsPojo() ) )
        {
            ticketsPojo = inCrmQrcDto.getCrmSrTicketsPojo();
            if ( ticketsPojo.getQrcCategoryId() > 0
                    && !StringUtils.isEmpty( ticketsPojo.getCustomerDetailsPojo().getProduct() ) )
            {
                try
                {
                    session = HibernateUtil.getCurrentSession();
                    criteria = session.createCriteria( CrmQrcActionTakenPojo.class );
                    criteria.add( Restrictions.eq( "qrcCategoryId", ticketsPojo.getQrcCategoryId() ) );
                    criteria.add( Restrictions.eq( "serviceName", ticketsPojo.getCustomerDetailsPojo().getProduct() ) );
                    criteria.add( Restrictions.eq( "status", CRMStatusCode.ACTIVE.getStatusCode() ) );
                    criteria.setCacheable( true );
                    criteria.setCacheRegion( CrmQrcActionTakenPojo.class.getName() );
                    qrcRcaPojos = criteria.list();
                    if ( CommonValidator.isValidCollection( qrcRcaPojos ) )
                    {
                        inCrmQrcDto.setQrcActionTakenPojos( qrcRcaPojos );
                    }
                    crmServiceCode = CRMServiceCode.CRM001;
                }
                catch ( Exception ex )
                {
                    LOGGER.error( "Exception while retreiving RCA List ", ex );
                }
                finally
                {
                    CRMServiceUtils.closeSession( session );
                    inCrmQrcDto.setStatusCode( crmServiceCode.getStatusCode() );
                    inCrmQrcDto.setStatusDesc( crmServiceCode.getStatusDesc() );
                }
            }
        }
        return inCrmQrcDto;
    }

    @Override
    public CrmQrcDto qrcRootCauseList( CrmQrcDto inCrmQrcDto )
    {
        LOGGER.info( "Inside QrcManagerDaoImpl : qrcRcaReasonList" );
        CRMServiceCode crmServiceCode = CRMServiceCode.CRM999;
        List<CrmQrcRootCausePojo> resolutionCodePojos = null;
        Session session = null;
        Criteria criteria = null;
        if ( StringUtils.isValidObj( inCrmQrcDto.getQrcActionTakenPojo() ) )
        {
            try
            {
                session = HibernateUtil.getCurrentSession();
                criteria = session.createCriteria( CrmQrcRootCausePojo.class );
                criteria.add( Restrictions.eq( "actionId", inCrmQrcDto.getQrcActionTakenPojo().getActionId() ) );
                criteria.add( Restrictions.eq( "status", CRMStatusCode.ACTIVE.getStatusCode() ) );
                criteria.setCacheable( true );
                criteria.setCacheRegion( CrmQrcRootCausePojo.class.getName() );
                resolutionCodePojos = criteria.list();
                if ( CommonValidator.isValidCollection( resolutionCodePojos ) )
                {
                    inCrmQrcDto.setRootCausePojos( resolutionCodePojos );
                }
            }
            catch ( Exception ex )
            {
                LOGGER.error( "Exception while retreiving Resolution Code List ", ex );
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
    public CrmQrcDto resetMyAccountPassword( CrmQrcDto inCrmQrcDto )
    {
        Session session = null;
        Transaction transaction = null;
        Criteria criteria = null;
        try
        {
            MDC.put( "KEY", "CUSTID:" + inCrmQrcDto.getCustomerId() + ", USERID:" + inCrmQrcDto.getUserId() );
            session = HibernateUtil.getCurrentSession();
            criteria = session.createCriteria( CrmCustMyAccountPojo.class );
            criteria.add( Restrictions.eq( "customerId", inCrmQrcDto.getCustomerId() ) );
            List<CrmCustMyAccountPojo> crList = criteria.list();
            if ( crList.size() > 0 )
            {
                CrmCustMyAccountPojo crmCustMyAccountPojo = crList.get( 0 );
                String password = CRMServiceUtils.getMyAccountRandomPassword();
                crmCustMyAccountPojo.setPassword3( crmCustMyAccountPojo.getPassword2() );
                crmCustMyAccountPojo.setPassword2( crmCustMyAccountPojo.getPassword1() );
                crmCustMyAccountPojo.setPassword1( crmCustMyAccountPojo.getPassword() );
                crmCustMyAccountPojo.setPassword( EncryptionUtil.encrypt( password ) );
                crmCustMyAccountPojo.setLastModifiedTime( Calendar.getInstance().getTime() );
                crmCustMyAccountPojo.setLastModifiedBy( inCrmQrcDto.getUserId() );
                crmCustMyAccountPojo.setStatus( CRMStatusCode.NEW.getStatusCode() );
                transaction = session.beginTransaction();
                session.update( crmCustMyAccountPojo );
                transaction.commit();
                String remarks = "Reset My Account Password";
                CrmQrcDto tmpDto = CRMServiceUtils.generateAutoTicket( inCrmQrcDto.getCustomerId(),
                                                                       QRCRolCategories.RESET_SELF_CARE_PASSWORD, null,
                                                                       inCrmQrcDto.getUserId(), remarks );
                if ( StringUtils.equals( CRMServiceCode.CRM001.getStatusCode(), tmpDto.getStatusCode() ) )
                {
                    inCrmQrcDto.setSrTicketNo( tmpDto.getCrmSrTicketsPojo().getSrId() );
                    inCrmQrcDto.setCrmSrTicketsPojo( tmpDto.getCrmSrTicketsPojo() );
                    if ( CRMServiceUtils.sendAlerts( CRMEvents.RESET_MY_ACCOUNT_PASSWORD, CRMRequestType.CUSTOMER_ID,
                                                     inCrmQrcDto.getCustomerId(), null ) )
                    {
                        CrmCustomerActivityPojo activityLog = new CrmCustomerActivityPojo();
                        CRMServiceUtils.fillActivityDetails( null, null, activityLog,
                                                             CRMCustomerActivityActions.RESET_PASSWORD );
                        activityLog.setTicketId( inCrmQrcDto.getCrmSrTicketsPojo().getSrId() );
                        preparedActivityLogPojo( activityLog, inCrmQrcDto.getCustomerId(), inCrmQrcDto.getUserId(),
                                                 CRMCustomerActivityActions.RESET_PASSWORD.getActionDesc(),
                                                 CRMCustomerActivityActions
                                                         .getActionByCode( CRMCustomerActivityActions.RESET_PASSWORD
                                                                 .getActionDesc() ), inCrmQrcDto.getClientIPAddress(),
                                                 inCrmQrcDto.getServerIPAddress() );
                        CRMServiceUtils.createActivityLog( activityLog );
                        inCrmQrcDto.setStatusCode( CRMServiceCode.CRM001.getStatusCode() );
                    }
                }
            }
        }
        catch ( HibernateException ex )
        {
            LOGGER.error( "HibernateException while Reset My Account Password: ", ex );
            inCrmQrcDto.setStatusCode( CRMServiceCode.CRM999.getStatusCode() );
            CRMServiceUtils.rollback( transaction );
            CRMServiceUtils.closeSessionOnException( session );
        }
        catch ( Exception ex )
        {
            inCrmQrcDto.setStatusCode( CRMServiceCode.CRM999.getStatusCode() );
            LOGGER.error( "Getting Error while Reset My Account Password: ", ex );
        }
        finally
        {
            CRMServiceUtils.rollback( transaction );
            CRMServiceUtils.closeSession( session );
            if ( StringUtils.equals( CRMServiceCode.CRM001.getStatusCode(), inCrmQrcDto.getStatusCode() ) )
            {
                HibernateUtil.evictAll( CrmCustMyAccountPojo.class.getName(), null, null );
            }
            MDC.remove( "KEY" );
        }
        return inCrmQrcDto;
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
    public CrmQrcDto searchCustomerInteraction( CrmQrcDto inCrmQrcDto )
    {
        LOGGER.info( "Inside QrcManagerDaoImpl : searchCustomerInteraction" );
        CRMServiceCode crmServiceCode = CRMServiceCode.CRM999;
        List<CrmCustInteractionsPojo> custInteractionPojos = null;
        SessionFactory sessionFactory = null;
        Session session = null;
        Criteria criteria = null;
        if ( StringUtils.isValidObj( inCrmQrcDto ) )
        {
            try
            {
                sessionFactory = HibernateUtil.getSessionFactory();
                session = sessionFactory.openSession();
                criteria = session.createCriteria( CrmCustInteractionsPojo.class );
                if ( ( StringUtils.isValidObj( inCrmQrcDto.getFromDate() ) )
                        && ( StringUtils.isValidObj( inCrmQrcDto.getToDate() ) ) )
                {
                    String fromDateStr = inCrmQrcDto.getFromDate();
                    String toDateStr = inCrmQrcDto.getToDate();
                    fromDateStr += IAppConstants.START_TIME;
                    toDateStr += IAppConstants.END_TIME;
                    LOGGER.info( "from Date:::;" + fromDateStr );
                    LOGGER.info( "To Date:::;" + toDateStr );
                    SimpleDateFormat formatter = new SimpleDateFormat( "dd-MMM-yyyy HH:mm:ss" );
                    Date fromDate = formatter.parse( fromDateStr );
                    Date toDate = formatter.parse( toDateStr );
                    criteria.add( Restrictions.between( "createdTime", fromDate, toDate ) );
                }
                if ( StringUtils.isValidObj( inCrmQrcDto.getCustInteractionsPojo() ) )
                {
                    if ( StringUtils.isNotEmpty( inCrmQrcDto.getCustInteractionsPojo().getCustomerId() ) )
                    {
                        criteria.add( Restrictions.eq( "customerId", inCrmQrcDto.getCustInteractionsPojo()
                                .getCustomerId() ) );
                    }
                    if ( StringUtils.isNotEmpty( inCrmQrcDto.getCustInteractionsPojo().getInteractionCategory() ) )
                    {
                        criteria.add( Restrictions.eq( "interactionCategory", inCrmQrcDto.getCustInteractionsPojo()
                                .getInteractionCategory() ) );
                    }
                    if ( StringUtils.isNotEmpty( inCrmQrcDto.getCustInteractionsPojo().getInteractionSubCategory() )
                            && ( !StringUtils
                                    .equals( inCrmQrcDto.getCustInteractionsPojo().getInteractionSubCategory(), "0" ) ) )
                    {
                        criteria.add( Restrictions.eq( "interactionSubCategory", inCrmQrcDto.getCustInteractionsPojo()
                                .getInteractionSubCategory() ) );
                    }
                }
                criteria.setCacheable( true );
                criteria.setCacheRegion( CrmCustInteractionsPojo.class.getName() );
                custInteractionPojos = criteria.list();
                crmServiceCode = CRMServiceCode.CRM001;
                if ( CommonValidator.isValidCollection( custInteractionPojos ) )
                {
                    inCrmQrcDto.setCustInteractionsPojos( custInteractionPojos );
                }
                else
                {
                    custInteractionPojos = new ArrayList<CrmCustInteractionsPojo>();
                    inCrmQrcDto.setCustInteractionsPojos( custInteractionPojos );
                }
            }
            catch ( Exception ex )
            {
                LOGGER.error( "Exception while retreiving Customer Interaction List ", ex );
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
    public CrmQrcDto createCustomerInteraction( CrmQrcDto inCrmQrcDto )
    {
        LOGGER.info( "Inside QrcManagerDaoImpl : createCustomerInteraction" );
        CRMServiceCode crmServiceCode = CRMServiceCode.CRM999;
        SessionFactory sessionFactory = null;
        Session session = null;
        Transaction transaction = null;
        Long custInteractionID = null;
        if ( StringUtils.isValidObj( inCrmQrcDto.getCustInteractionsPojo() ) )
        {
            try
            {
                sessionFactory = HibernateUtil.getSessionFactory();
                session = sessionFactory.openSession();
                transaction = session.beginTransaction();
                inCrmQrcDto.getCustInteractionsPojo().setCreatedTime( Calendar.getInstance().getTime() );
                custInteractionID = (Long) session.save( inCrmQrcDto.getCustInteractionsPojo() );
                LOGGER.info( "Generated Customer Interaction ID : " + custInteractionID );
                transaction.commit();
                crmServiceCode = CRMServiceCode.CRM001;
            }
            catch ( HibernateException ex )
            {
                LOGGER.error( "HibernateException while creating Customer Interaction ", ex );
                CRMServiceUtils.rollback( transaction );
                CRMServiceUtils.closeSessionOnException( session );
            }
            catch ( Exception ex )
            {
                LOGGER.error( "Exception while creating Customer Interaction ", ex );
            }
            finally
            {
                CRMServiceUtils.closeSession( session );
                inCrmQrcDto.setStatusCode( crmServiceCode.getStatusCode() );
                inCrmQrcDto.setStatusDesc( crmServiceCode.getStatusDesc() );
                if ( crmServiceCode == CRMServiceCode.CRM001 )
                {
                    HibernateUtil.evictAll( CrmCustInteractionsPojo.class.getName(), null, null );
                }
            }
        }
        return inCrmQrcDto;
    }

    @Override
    public boolean isValidTicketNo( String generatedTicketId, String customerId )
    {
        Session session = null;
        Criteria criteria = null;
        try
        {
            session = HibernateUtil.getCurrentSession();
            if ( StringUtils.startsWith( generatedTicketId, customerId ) )
            {
                criteria = session.createCriteria( CrmSrTicketsPojo.class );
                criteria.add( Restrictions.eq( "mappingId", customerId ) );
                criteria.add( Restrictions.eq( "srId", generatedTicketId ) );
                Criterion statusOpen = Restrictions.eq( "status", CRMStatusCode.OPEN.getStatusCode() );
                Criterion statusReopen = Restrictions.eq( "status", CRMStatusCode.REOPEN.getStatusCode() );
                LogicalExpression logicalExpression = Restrictions.or( statusOpen, statusReopen );
                criteria.add( logicalExpression );
                criteria.setCacheable( true );
                criteria.setCacheRegion( CrmSrTicketsPojo.class.getName() );
                List<CrmSrTicketsPojo> crmSrTicketsPojos = criteria.list();
                if ( CommonValidator.isValidCollection( crmSrTicketsPojos ) )
                {
                    return true;
                }
            }
        }
        catch ( Exception ex )
        {
            LOGGER.error( "Getting Error while check valid ticket no: ", ex );
        }
        finally
        {
            CRMServiceUtils.closeSession( session );
        }
        return false;
    }

    @Override
    public CrmQrcDto updateCustomerStatus( CrmQrcDto inCrmQrcDto )
    {
        LOGGER.info( "Inside QrcManagerDaoImpl, updateCustomerStatus" );
        Session session = null;
        Transaction transaction = null;
        CrmCustomerActivityPojo activityLog = null;
        CRMServiceCode crmServiceCode = CRMServiceCode.CRM999;
        try
        {
            MDC.put( "KEY", "CUSTID:" + inCrmQrcDto.getCustomerId() + ", USERID:" + inCrmQrcDto.getUserId() );
            Calendar cal = Calendar.getInstance();
            session = HibernateUtil.getCurrentSession();
            transaction = session.beginTransaction();
            activityLog = new CrmCustomerActivityPojo();
            CRMCustomerActivityActions ativityAction = null;
            if ( StringUtils.isNotEmpty( inCrmQrcDto.getSrTicketNo() ) )
            {
                /*      if ( StringUtils.equals( inCrmQrcDto.getCustomerDetailsPojo().getStatus(),
                                               CRMStatusCode.TDC.getStatusCode() ) )
                      {
                          if ( !isValidTicket( inCrmQrcDto.getSrTicketNo(), inCrmQrcDto.getCustomerId() ) )
                          {
                              crmServiceCode = CRMServiceCode.CRM439;
                          }
                          else
                          {
                              activityLog.setTicketId( inCrmQrcDto.getSrTicketNo() );
                          }
                      }
                      else
                      {
                          if ( !isValidTicketNo( inCrmQrcDto.getSrTicketNo(), inCrmQrcDto.getCustomerId() ) )
                          {
                              crmServiceCode = CRMServiceCode.CRM402;
                          }
                          else
                              activityLog.setTicketId( inCrmQrcDto.getSrTicketNo() );
                      }*/
                activityLog.setTicketId( inCrmQrcDto.getSrTicketNo() );
            }
            if ( !StringUtils.equals( crmServiceCode.getStatusCode(), CRMServiceCode.CRM402.getStatusCode() )
                    && !StringUtils.equals( crmServiceCode.getStatusCode(), CRMServiceCode.CRM439.getStatusCode() ) )
            {
                if ( inCrmQrcDto.getCustomerRecordId() > 0 )
                {
                    LOGGER.info( "Change Status To:::" + inCrmQrcDto.getCustomerDetailsPojo().getStatus() );
                    CrmCustomerDetailsPojo crmCustomerDetailsPojo = (CrmCustomerDetailsPojo) session
                            .get( CrmCustomerDetailsPojo.class, inCrmQrcDto.getCustomerRecordId() );
                    if ( StringUtils.isValidObj( crmCustomerDetailsPojo ) )
                    {
                        if ( StringUtils.equals( inCrmQrcDto.getCustomerDetailsPojo().getStatus(),
                                                 CRMStatusCode.TDC.getStatusCode() ) )
                        {
                            ativityAction = CRMCustomerActivityActions.TDC;
                            crmCustomerDetailsPojo.setTempDisconnectDate( cal.getTime() );
                        }
                        else if ( StringUtils.equals( inCrmQrcDto.getCustomerDetailsPojo().getStatus(),
                                                      CRMStatusCode.PDC.getStatusCode() ) )
                        {
                            ativityAction = CRMCustomerActivityActions.PDC;
                            crmCustomerDetailsPojo.setPermanentDisconnectDate( cal.getTime() );
                        }
                        else if ( StringUtils.equals( inCrmQrcDto.getCustomerDetailsPojo().getStatus(),
                                                      CRMStatusCode.SC.getStatusCode() ) )
                        {
                            crmCustomerDetailsPojo.setSafeCustodyDate( cal.getTime() );
                            ativityAction = CRMCustomerActivityActions.SAFE_CUSTODY;
                        }
                        CRMServiceUtils.fillActivityDetails( crmCustomerDetailsPojo,
                                                             inCrmQrcDto.getCustomerDetailsPojo(), activityLog,
                                                             ativityAction );
                        crmCustomerDetailsPojo.setStatus( inCrmQrcDto.getCustomerDetailsPojo().getStatus() );
                        //Calling billing API :- Update Customer Status Web Service API
                        boolean toCommit = false;
                        CrmBillingDto billingDto = new CrmBillingDto();
                        billingDto.setCustomerId( crmCustomerDetailsPojo.getCustomerId() );
                        billingDto.setStatus( crmCustomerDetailsPojo.getStatus() );
                        billingDto.setRemarks( inCrmQrcDto.getRemarksPojo().getRemarks() );
                        getCrmBillingManager().changeStatus( billingDto );
                        if ( StringUtils.equals( CRMServiceCode.CRM001.getStatusCode(), billingDto.getStatusCode() ) )
                        {
                            if ( StringUtils.equals( inCrmQrcDto.getCustomerDetailsPojo().getStatus(),
                                                     CRMStatusCode.TDC.getStatusCode() ) )
                            {
                                try
                                {
                                    CrmQrcDto tmpDto = CRMServiceUtils
                                            .generateAutoTicket( inCrmQrcDto.getCustomerId(),
                                                                 QRCRolCategories.TDC_SLAVE_RECOVERY, null,
                                                                 inCrmQrcDto.getUserId() );
                                    if ( StringUtils.equals( CRMServiceCode.CRM001.getStatusCode(),
                                                             tmpDto.getStatusCode() ) )
                                    {
                                        inCrmQrcDto.setCrmSrTicketsPojo( tmpDto.getCrmSrTicketsPojo() );
                                        inCrmQrcDto.setSrTicketNo( tmpDto.getCrmSrTicketsPojo().getSrId() );
                                    }
                                }
                                catch ( SOAPException ex )
                                {
                                    LOGGER.error( "Unable to generate ticket for "
                                            + QRCRolCategories.TDC_SLAVE_RECOVERY, ex );
                                }
                            }
                            LOGGER.info( "Status Code::" + billingDto.getStatusCode() );
                            crmCustomerDetailsPojo.setLastModifiedBy( inCrmQrcDto.getUserId() );
                            crmCustomerDetailsPojo.setLastModifiedTime( Calendar.getInstance().getTime() );
                            session.merge( crmCustomerDetailsPojo );
                            toCommit = true;
                        }
                        else
                        {
                            crmServiceCode = CRMServiceCode.valueOf( billingDto.getStatusCode() );
                            inCrmQrcDto.setBillingErrorCode( billingDto.getBillingErrorCode() );
                            LOGGER.info( "Status Code ::" + crmServiceCode );
                        }
                        if ( toCommit )
                        {
                            String reason = ativityAction.getActionDesc();
                            if ( StringUtils.isValidObj( inCrmQrcDto.getRemarksPojo() )
                                    && inCrmQrcDto.getRemarksPojo().getReasonId() > 0 )
                            {
                                CrmRcaReasonPojo reasonPojo = CRMServiceUtils.getDBValues( CrmRcaReasonPojo.class,
                                                                                           inCrmQrcDto.getRemarksPojo()
                                                                                                   .getReasonId() );
                                if ( StringUtils.isValidObj( reasonPojo ) )
                                {
                                    reason = reasonPojo.getCategoryValue();
                                }
                            }
                            preparedActivityLogPojo( activityLog, inCrmQrcDto.getCustomerId(), inCrmQrcDto.getUserId(),
                                                     ativityAction.getActionCode(), reason,
                                                     inCrmQrcDto.getClientIPAddress(), inCrmQrcDto.getServerIPAddress() );
                            String churnType = activityLog.getReason().concat( " ~ " + inCrmQrcDto.getChurnType() );
                            activityLog.setReason( churnType );
                            long activityId = CRMServiceUtils.createActivityLog( activityLog );
                            if ( StringUtils.isValidObj( inCrmQrcDto.getRemarksPojo() ) )
                            {
                                RemarksPojo remarksPojo = new RemarksPojo();
                                remarksPojo.setRemarks( inCrmQrcDto.getRemarksPojo().getRemarks() );
                                remarksPojo.setReasonId( inCrmQrcDto.getRemarksPojo().getReasonId() );
                                remarksPojo.setMappingId( inCrmQrcDto.getCustomerId() );
                                remarksPojo.setActions( ativityAction.getActionDesc() );
                                remarksPojo.setCreatedBy( inCrmQrcDto.getUserId() );
                                remarksPojo.setCreatedTime( Calendar.getInstance().getTime() );
                                remarksPojo.setActivityId( activityId );
                                remarksPojo.setMappingType( CRMRCAReason.QRC.getStatusCode() );
                                session.save( remarksPojo );
                            }
                            transaction.commit();
                            crmServiceCode = CRMServiceCode.CRM001;
                        }
                    }
                    else
                        crmServiceCode = CRMServiceCode.CRM408;
                }
            }
        }
        catch ( HibernateException ex )
        {
            LOGGER.error( "HibernateException while update customer Status:", ex );
            crmServiceCode = CRMServiceCode.CRM999;
            CRMServiceUtils.rollback( transaction );
            CRMServiceUtils.closeSessionOnException( session );
        }
        catch ( Exception ex )
        {
            LOGGER.error( "Getting Error while update customer Status: ", ex );
            crmServiceCode = CRMServiceCode.CRM999;
        }
        finally
        {
            CRMServiceUtils.rollback( transaction );
            CRMServiceUtils.closeSession( session );
            if ( StringUtils.isEmpty( inCrmQrcDto.getStatusCode() ) )
            {
                inCrmQrcDto.setStatusCode( crmServiceCode.getStatusCode() );
                inCrmQrcDto.setStatusDesc( crmServiceCode.getStatusDesc() );
            }
            if ( crmServiceCode == CRMServiceCode.CRM001 )
            {
                HibernateUtil.evictAll( CrmCustomerDetailsPojo.class.getName(), null, null );
                HibernateUtil.evictAll( RemarksPojo.class.getName(), null, null );
            }
            MDC.remove( "KEY" );
        }
        return inCrmQrcDto;
    }

    @Override
    public CrmQrcDto modifyInstallationAddresses( CrmQrcDto inCrmQrcDto )
    {
        Session session = null;
        Transaction transaction = null;
        CrmCustomerActivityPojo activityLog = null;
        CRMServiceCode crmServiceCode = CRMServiceCode.CRM999;
        CrmAddressDetailsPojo crmAddrDetailsPojo = null;
        CrmBillingDto crmBillingDto = null;
        try
        {
            MDC.put( "KEY", "CUSTID:" + inCrmQrcDto.getCustomerId() + ", USERID:" + inCrmQrcDto.getUserId() );
            session = HibernateUtil.getCurrentSession();
            transaction = session.beginTransaction();
            activityLog = new CrmCustomerActivityPojo();
            if ( StringUtils.isValidObj( inCrmQrcDto.getCrmShiftingWorkflowPojo() )
                    && StringUtils.isValidObj( inCrmQrcDto )
                    && inCrmQrcDto.getCrmAddressDetailsPojo().getRecordId() > 0 )
            {
                crmAddrDetailsPojo = (CrmAddressDetailsPojo) session.get( CrmAddressDetailsPojo.class, inCrmQrcDto
                        .getCrmAddressDetailsPojo().getRecordId() );
                if ( StringUtils.isValidObj( crmAddrDetailsPojo )
                        && StringUtils.isValidObj( inCrmQrcDto.getCrmShiftingWorkflowPojo().getStateId() )
                        && inCrmQrcDto.getCrmShiftingWorkflowPojo().getStateId() > 0 )
                {
                    CRMServiceUtils.fillActivityDetails( crmAddrDetailsPojo, inCrmQrcDto.getCrmAddressDetailsPojo(),
                                                         activityLog,
                                                         CRMCustomerActivityActions.INSTALLAITION_ADDRESS_CHANGE );
                    crmAddrDetailsPojo.setPincode( inCrmQrcDto.getCrmShiftingWorkflowPojo().getPincode() );
                    crmAddrDetailsPojo.setAddLine1( inCrmQrcDto.getCrmShiftingWorkflowPojo().getAddressLine1() );
                    crmAddrDetailsPojo.setAddLine2( inCrmQrcDto.getCrmShiftingWorkflowPojo().getAddressLine2() );
                    StatePojo stPojo = CRMServiceUtils.getDBValues( StatePojo.class, inCrmQrcDto
                            .getCrmShiftingWorkflowPojo().getStateId() );
                    if ( StringUtils.isValidObj( stPojo ) )
                    {
                        crmAddrDetailsPojo.setStateName( stPojo.getStateName() );
                        if ( StringUtils.isValidObj( inCrmQrcDto.getCrmShiftingWorkflowPojo().getCityId() )
                                && inCrmQrcDto.getCrmShiftingWorkflowPojo().getCityId() > 0 )
                        {
                            CityPojo cityPojo = CRMServiceUtils.getDBValues( CityPojo.class, inCrmQrcDto
                                    .getCrmShiftingWorkflowPojo().getCityId() );
                            if ( StringUtils.isValidObj( cityPojo ) )
                            {
                                crmAddrDetailsPojo.setCityName( cityPojo.getCityName() );
                            }
                        }
                    }
                }
                // ------ Billing API :- Address Correction Web Service API call 
                crmBillingDto = getCrmBillingManager().changeAddress( crmAddrDetailsPojo );
                if ( StringUtils.isValidObj( crmBillingDto )
                        && StringUtils.equals( crmBillingDto.getStatusCode(), CRMServiceCode.CRM001.getStatusCode() ) )
                {
                    CRMUtils.copyPropertyValue( crmAddrDetailsPojo, inCrmQrcDto.getCrmAddressDetailsPojo(), null );
                    crmAddrDetailsPojo.setLastModifiedBy( inCrmQrcDto.getUserId() );
                    crmAddrDetailsPojo.setLastModifiedTime( Calendar.getInstance().getTime() );
                    session.merge( crmAddrDetailsPojo );
                    long activityId = saveCustomerActivity( activityLog, inCrmQrcDto,
                                                            CRMCustomerActivityActions.INSTALLAITION_ADDRESS_CHANGE );
                    if ( StringUtils.isValidObj( inCrmQrcDto.getRemarksPojo() ) )
                    {
                        inCrmQrcDto.getRemarksPojo().setMappingId( inCrmQrcDto.getCrmShiftingWorkflowPojo()
                                                                           .getWorkflowId() );
                        inCrmQrcDto.getRemarksPojo()
                                .setActions( CRMCustomerActivityActions.INSTALLAITION_ADDRESS_CHANGE.getActionDesc() );
                        inCrmQrcDto.getRemarksPojo().setCreatedBy( inCrmQrcDto.getUserId() );
                        inCrmQrcDto.getRemarksPojo().setCreatedTime( Calendar.getInstance().getTime() );
                        inCrmQrcDto.getRemarksPojo().setActivityId( activityId );
                        inCrmQrcDto.getRemarksPojo().setMappingType( CRMRCAReason.QRC.getStatusCode() );
                        inCrmQrcDto.getRemarksPojo().setReasonId( inCrmQrcDto.getRemarksPojo().getReasonId() );
                        session.save( inCrmQrcDto.getRemarksPojo() );
                    }
                    transaction.commit();
                    crmServiceCode = CRMServiceCode.CRM001;
                }
                else
                {
                    inCrmQrcDto.setBillingErrorCode( crmBillingDto.getBillingErrorCode() );
                    crmServiceCode = CRMServiceCode.valueOf( crmBillingDto.getStatusCode() );
                }
            }
        }
        catch ( HibernateException ex )
        {
            LOGGER.error( "HibernateException while modify customer Installation address: ", ex );
            CRMServiceUtils.rollback( transaction );
            CRMServiceUtils.closeSessionOnException( session );
        }
        catch ( Exception ex )
        {
            LOGGER.error( "Getting Error while modify customer Installation address: ", ex );
            CRMServiceUtils.rollback( transaction );
        }
        finally
        {
            CRMServiceUtils.closeSession( session );
            inCrmQrcDto.setStatusCode( crmServiceCode.getStatusCode() );
            inCrmQrcDto.setStatusDesc( crmServiceCode.getStatusDesc() );
            if ( crmServiceCode == CRMServiceCode.CRM001 )
            {
                HibernateUtil.evictAll( CrmAddressDetailsPojo.class.getName(), null, null );
                HibernateUtil.evictAll( RemarksPojo.class.getName(), null, null );
            }
            MDC.remove( "KEY" );
        }
        return inCrmQrcDto;
    }

    @Override
    public CrmQrcDto postWaiver( CrmQrcDto inCrmQrcDto )
    {
        Session session = null;
        Transaction transaction = null;
        CrmCustomerActivityPojo activityLog = null;
        //CrmAuditLogPojo crmAuditLogPojo = null;
        CRMServiceCode crmServiceCode = CRMServiceCode.CRM999;
        String stage = null;
        Map<String, Long> evicts = new HashMap<String, Long>();
        try
        {
            session = HibernateUtil.getCurrentSession();
            transaction = session.beginTransaction();
            activityLog = new CrmCustomerActivityPojo();
            if ( StringUtils.isNotEmpty( inCrmQrcDto.getSrTicketNo() ) )
            {
                if ( !isValidTicketNo( inCrmQrcDto.getSrTicketNo(), inCrmQrcDto.getCrmCustWaiverPojo().getCustomerId() ) )
                {
                    LOGGER.info( "Ticket no::: " + inCrmQrcDto.getSrTicketNo() );
                    crmServiceCode = CRMServiceCode.CRM402;
                }
                else
                    activityLog.setTicketId( inCrmQrcDto.getSrTicketNo() );
            }
            if ( !StringUtils.equals( crmServiceCode.getStatusCode(), CRMServiceCode.CRM402.getStatusCode() ) )
            {
                // crmAuditLogPojo = new CrmAuditLogPojo();
                crmServiceCode = allowedWaiver( inCrmQrcDto.getCrmCustWaiverPojo(), session );
                if ( StringUtils.equals( crmServiceCode.getStatusCode(), CRMServiceCode.CRM001.getStatusCode() ) )
                {
                    List<CrmRcaReasonPojo> waiverFunctionalBinList = getWaiverFunctionBin();
                    CrmUserPojo crmUserPojo = CRMServiceUtils.getDBValues( CrmUserPojo.class, "userId",
                                                                           inCrmQrcDto.getUserId(), session );
                    List<String> userBin = Arrays.asList( crmUserPojo.getFunctionalBin().split( "," ) );
                    List<CrmRcaReasonPojo> sortedlist = getBinIdForPostWaiver( waiverFunctionalBinList, inCrmQrcDto
                            .getCrmCustWaiverPojo().getWaiverAmount() );
                    if ( !sortedlist.isEmpty() )
                    {
                        stage = getInitiateddBin( userBin, waiverFunctionalBinList, inCrmQrcDto.getCrmCustWaiverPojo()
                                .getWaiverAmount() );
                        if ( StringUtils.isValidObj( stage ) )
                        {
                            inCrmQrcDto.getCrmCustWaiverPojo().setStatus( CRMStatusCode.OPEN.getStatusCode() );
                            inCrmQrcDto.getCrmCustWaiverPojo().setWorkflowStage( stage );
                            crmServiceCode = applyWaiver( inCrmQrcDto, session, evicts );
                            if ( StringUtils.equals( crmServiceCode.getStatusCode(),
                                                     CRMServiceCode.CRM001.getStatusCode() ) )
                                crmServiceCode = saveInbox( inCrmQrcDto, session, stage, evicts );
                            if ( StringUtils.equals( crmServiceCode.getStatusCode(),
                                                     CRMServiceCode.CRM001.getStatusCode() ) )
                                crmServiceCode = saveRemark( session, inCrmQrcDto, evicts );
                            if ( StringUtils.equals( crmServiceCode.getStatusCode(),
                                                     CRMServiceCode.CRM001.getStatusCode() ) )
                            {
                                inCrmQrcDto.setCustomerId( inCrmQrcDto.getCrmCustWaiverPojo().getCustomerId() );
                                /* crmAuditLogPojo.setOldValues( IAppConstants.DASH + IAppConstants.COMMA );
                                 crmAuditLogPojo.setNewValues( inCrmQrcDto.getCrmCustWaiverPojo().getWaiverAmount()
                                         .toEngineeringString()
                                         + IAppConstants.COMMA );
                                 crmAuditLogPojo.setModule( CRMRequestType.WAIVER.getRequestCode() );
                                 crmAuditLogPojo.setMappingId( inCrmQrcDto.getCrmCustWaiverPojo().getWorkflowId() );
                                 crmAuditLogPojo.setRemarks( inCrmQrcDto.getRemarksPojo().getRemarks() );
                                 crmAuditLogPojo.setToBin( stage );
                                 crmAuditLogPojo.setCreatedBy( inCrmQrcDto.getUserId() );
                                 crmAuditLogPojo.setEvents( CrmActionEnum.GENERATED.getActionCode() );
                                 crmAuditLogPojo.setClientIp( inCrmQrcDto.getClientIPAddress() );
                                 crmAuditLogPojo.setServerIp( inCrmQrcDto.getServerIPAddress() );
                                 CRMServiceUtils.createAuditLog( crmAuditLogPojo );*/
                                /*activityLog.setAction( CRMCustomerActivityActions.WAIVER.getActionCode() );
                                activityLog.setReason( CRMCustomerActivityActions
                                        .getActionByCode( CRMCustomerActivityActions.WAIVER.getActionCode() ) );*/
                                transaction.commit();
                                crmServiceCode = CRMServiceCode.CRM001;
                            }
                        }
                        else
                        {
                            inCrmQrcDto.getCrmCustWaiverPojo().setStatus( CRMStatusCode.WAIVER_POSTED.getStatusCode() );
                            inCrmQrcDto.getCrmCustWaiverPojo().setWorkflowStage( CRMOperationStages.INITIATE.getCode() );
                            inCrmQrcDto.getCrmCustWaiverPojo().setApprovedBy( inCrmQrcDto.getUserId() );
                            inCrmQrcDto.getCrmCustWaiverPojo().setWaiverPostedDate( Calendar.getInstance().getTime() );
                            CrmBillingDto billingDto = getCrmBillingManager().postWaiver( inCrmQrcDto );
                            crmServiceCode = CRMServiceCode.valueOf( billingDto.getStatusCode() );
                            inCrmQrcDto.setBillingErrorCode( billingDto.getBillingErrorCode() );
                            LOGGER.info( " Billing Code" + billingDto.getStatusCode() );
                            if ( StringUtils.equals( CRMServiceCode.CRM001.getStatusCode(), billingDto.getStatusCode() ) )
                            {
                                applyWaiver( inCrmQrcDto, session, evicts );
                                if ( StringUtils.equals( crmServiceCode.getStatusCode(),
                                                         CRMServiceCode.CRM001.getStatusCode() ) )
                                    crmServiceCode = saveRemark( session, inCrmQrcDto, evicts );
                                if ( StringUtils.equals( crmServiceCode.getStatusCode(),
                                                         CRMServiceCode.CRM001.getStatusCode() ) )
                                {
                                    inCrmQrcDto.setCustomerId( inCrmQrcDto.getCrmCustWaiverPojo().getCustomerId() );
                                    activityLog.setAction( CRMCustomerActivityActions.WAIVER.getActionCode() );
                                    activityLog.setReason( inCrmQrcDto.getCrmCustWaiverPojo().getWaiverType() + "-"
                                            + inCrmQrcDto.getCrmCustWaiverPojo().getWaiverHead() );
                                    generateActivityPojo( inCrmQrcDto, activityLog );
                                    //Audit log
                                    /*crmAuditLogPojo.setOldValues( IAppConstants.DASH + IAppConstants.COMMA );
                                    crmAuditLogPojo.setNewValues( inCrmQrcDto.getCrmCustWaiverPojo().getWaiverAmount()
                                            .toEngineeringString()
                                            + IAppConstants.COMMA );
                                    crmAuditLogPojo.setModule( CRMRequestType.WAIVER.getRequestCode() );
                                    crmAuditLogPojo.setMappingId( inCrmQrcDto.getCrmCustWaiverPojo().getWorkflowId() );
                                    crmAuditLogPojo.setRemarks( inCrmQrcDto.getRemarksPojo().getRemarks() );
                                    crmAuditLogPojo.setCreatedBy( inCrmQrcDto.getUserId() );
                                    crmAuditLogPojo.setEvents( CrmActionEnum.WAIVER_POSTED.getActionCode() );
                                    crmAuditLogPojo.setClientIp( inCrmQrcDto.getClientIPAddress() );
                                    crmAuditLogPojo.setServerIp( inCrmQrcDto.getServerIPAddress() );
                                    CRMServiceUtils.createAuditLog( crmAuditLogPojo );*/
                                    transaction.commit();
                                    crmServiceCode = CRMServiceCode.CRM001;
                                }
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
        }
        catch ( HibernateException ex )
        {
            LOGGER.error( "HibernateException while while post waiver:", ex );
            CRMServiceUtils.rollback( transaction );
            CRMServiceUtils.closeSessionOnException( session );
        }
        catch ( Exception ex )
        {
            LOGGER.error( "Getting Error while post waiver: ", ex );
        }
        finally
        {
            CRMServiceUtils.rollback( transaction );
            CRMServiceUtils.closeSession( session );
            inCrmQrcDto.setStatusCode( crmServiceCode.getStatusCode() );
            inCrmQrcDto.setStatusDesc( crmServiceCode.getStatusDesc() );
            if ( crmServiceCode == CRMServiceCode.CRM001 )
            {
                HibernateUtil.evictAll( evicts );
            }
        }
        return inCrmQrcDto;
    }

    private void generateActivityPojo( CrmQrcDto inCrmQrcDto, CrmCustomerActivityPojo activityLog )
    {
        activityLog.setCustomerId( inCrmQrcDto.getCustomerId() );
        activityLog.setNewValue( inCrmQrcDto.getCrmCustWaiverPojo().getWaiverAmount() + "" );
        activityLog.setCreatedBy( inCrmQrcDto.getUserId() );
        activityLog.setServiceIp( CRMUtils.getServerIp() );
        activityLog.setClientIp( inCrmQrcDto.getClientIPAddress() );
        activityLog.setServerIp( inCrmQrcDto.getServerIPAddress() );
        CRMServiceUtils.createActivityLog( activityLog );
    }

    private CRMServiceCode applyWaiver( CrmQrcDto inCrmQrcDto, Session session, Map<String, Long> inEvicts )
    {
        CRMServiceCode crmCode = CRMServiceCode.CRM999;
        try
        {
            if ( StringUtils.isNotBlank( inCrmQrcDto.getSrTicketNo() ) )
            {
                inCrmQrcDto.getCrmCustWaiverPojo().setSrTicketId( inCrmQrcDto.getSrTicketNo() );
            }
            CrmWorkflowSequence workflowSequence = new CrmWorkflowSequence();
            Long workflowId = (Long) session.save( workflowSequence );
            inCrmQrcDto.getCrmCustWaiverPojo().setCreatedBy( inCrmQrcDto.getUserId() );
            inCrmQrcDto.getCrmCustWaiverPojo().setCreatedTime( Calendar.getInstance().getTime() );
            inCrmQrcDto.getCrmCustWaiverPojo().setWorkflowId( "W" + workflowId );
            long id = (Long) session.save( inCrmQrcDto.getCrmCustWaiverPojo() );
            inEvicts.put( CrmCustWaiverPojo.class.getName(), null );
            LOGGER.info( "Save id:::" + id );
            inCrmQrcDto.getCrmCustWaiverPojo().setWorkflowId( IAppConstants.WORKFLOW_PREFIX + id );
            crmCode = CRMServiceCode.CRM001;
        }
        catch ( Exception e )
        {
            crmCode = CRMServiceCode.CRM999;
            LOGGER.error( "Exception", e );
        }
        return crmCode;
    }

    @Override
    public List<CrmRcaReasonPojo> getBinIdForPostWaiver( List<CrmRcaReasonPojo> waiverFunctionalBinList,
                                                         Double waiverAmount )
    {
        List<CrmRcaReasonPojo> greaterAmtBin = new ArrayList<CrmRcaReasonPojo>();
        CrmRcaReasonPojo maxWaiverBin = null;
        SortingUtil<CrmRcaReasonPojo, Double> sorter = new SortingUtil<CrmRcaReasonPojo, Double>( "valueAlias", 0.0 );
        Double maxAmount = waiverAmount;
        Collections.sort( waiverFunctionalBinList, sorter );
        sorter = null;
        for ( CrmRcaReasonPojo crmRcaReasonPojo : waiverFunctionalBinList )
        {
            Double va = StringUtils.decimalValue( crmRcaReasonPojo.getValueAlias() );
            if ( va >= waiverAmount )
            {
                greaterAmtBin.add( crmRcaReasonPojo );
                maxAmount = va;
            }
            else if ( va.doubleValue() == new BigDecimal( -1 ).doubleValue() )
            {
                maxWaiverBin = crmRcaReasonPojo;
            }
        }
        maxWaiverBin.setValueAlias( maxAmount.longValue() + "" );
        greaterAmtBin.add( maxWaiverBin );
        return greaterAmtBin;
    }

    private String getInitiateddBin( List<String> userBin,
                                     List<CrmRcaReasonPojo> waiverFunctionalBinList,
                                     Double waiverAmount )
    {
        String stage = null;
        int counter = 0;
        SortingUtil<CrmRcaReasonPojo, BigDecimal> sorter = new SortingUtil<CrmRcaReasonPojo, BigDecimal>( "valueAlias",
                                                                                                          new BigDecimal( 0 ) );
        Collections.sort( waiverFunctionalBinList, sorter );
        String userbinId = getmaxBinFromUserBin( userBin, waiverFunctionalBinList );
        if ( StringUtils.isNotBlank( userbinId ) )
        {
            for ( CrmRcaReasonPojo waiverBin : waiverFunctionalBinList )
            {
                if ( counter < waiverFunctionalBinList.size() )
                {
                    counter++;
                }
                if ( waiverBin.getCategoryId() == Long.parseLong( userbinId ) )
                {
                    if ( waiverAmount.doubleValue() <= StringUtils.decimalValue( waiverBin.getValueAlias() )
                            .doubleValue() || StringUtils.equals( waiverBin.getCategoryValue(), "Waiver - Buss. Head" ) )
                    {
                        stage = null;
                    }
                    else
                    {
                        LOGGER.info( "Counter step:: " + counter );
                        stage = waiverFunctionalBinList.get( counter ).getCategoryId() + "";
                    }
                    break;
                }
            }
        }
        else
            stage = waiverFunctionalBinList.get( 0 ).getCategoryId() + "";
        LOGGER.info( "Final Stage:: " + stage );
        return stage;
    }

    private String getmaxBinFromUserBin( List<String> userBin, List<CrmRcaReasonPojo> waiverFunctionalBinList )
    {
        String FBbin = null;
        for ( CrmRcaReasonPojo crmRcaReasonPojo : waiverFunctionalBinList )
        {
            if ( userBin.contains( crmRcaReasonPojo.getCategoryId() + "" ) )
            {
                FBbin = crmRcaReasonPojo.getCategoryId() + "";
            }
        }
        LOGGER.info( "User Bin Match:: " + FBbin );
        return FBbin;
    }

    /**
     * @param inCrmQrcDto
     * @param session
     * @param stage
     */
    private CrmInboxPojo preparedInbox( CrmQrcDto inCrmQrcDto, String stage )
    {
        LOGGER.info( "Prepared Inbox method  " + stage );
        CrmInboxPojo pojo = new CrmInboxPojo();
        pojo.setCreatedBy( inCrmQrcDto.getUserId() );
        pojo.setCreatedTime( Calendar.getInstance().getTime() );
        pojo.setStatus( CRMStatusCode.ACTIVE.getStatusCode() );
        pojo.setRequestType( CRMRequestType.WAIVER.getRequestCode() );
        pojo.setMappingId( inCrmQrcDto.getCrmCustWaiverPojo().getWorkflowId() );
        pojo.setStage( stage );
        return pojo;
    }

    private CRMServiceCode saveInbox( CrmQrcDto inCrmQrcDto, Session session, String stage, Map<String, Long> inEvicts )
    {
        CRMServiceCode crmCode = CRMServiceCode.CRM999;
        try
        {
            CrmInboxPojo inBoxPojo = preparedInbox( inCrmQrcDto, stage );
            session.save( inBoxPojo );
            inEvicts.put( CrmInboxPojo.class.getName(), null );
            crmCode = CRMServiceCode.CRM001;
        }
        catch ( Exception e )
        {
            LOGGER.info( "Exception Occured:", e );
        }
        return crmCode;
    }

    private CRMServiceCode saveRemark( Session session, CrmQrcDto inCrmQrcDto, Map<String, Long> inEvicts )
    {
        CRMServiceCode crmCode = CRMServiceCode.CRM999;
        if ( StringUtils.isValidObj( inCrmQrcDto.getRemarksPojo() ) )
        {
            inCrmQrcDto.getRemarksPojo().setMappingId( inCrmQrcDto.getCrmCustWaiverPojo().getWorkflowId() );
            inCrmQrcDto.getRemarksPojo().setActions( CRMCustomerActivityActions.WAIVER.getActionDesc() );
            inCrmQrcDto.getRemarksPojo().setCreatedBy( inCrmQrcDto.getUserId() );
            inCrmQrcDto.getRemarksPojo().setCreatedTime( Calendar.getInstance().getTime() );
            session.save( inCrmQrcDto.getRemarksPojo() );
            inEvicts.put( RemarksPojo.class.getName(), null );
            crmCode = CRMServiceCode.CRM001;
        }
        return crmCode;
    }

    @Override
    public List<CrmRcaReasonPojo> getWaiverFunctionBin()
    {
        MasterDataDto inMasterDataDto = new MasterDataDto();
        CrmRcaReasonPojo crmRcaReasonPojo = new CrmRcaReasonPojo();
        crmRcaReasonPojo.setCategory( CRMRCAReason.CRM.getStatusCode() );
        crmRcaReasonPojo.setSubCategory( CRMRCAReason.CRM_MASTER.getStatusCode() );
        crmRcaReasonPojo.setSubSubCategory( CRMRCAReason.FUNCTIONAL_BIN.getStatusCode() );
        inMasterDataDto.setCategoryValuePrefix( "Waiver_" );
        inMasterDataDto.setCrmRcaReason( crmRcaReasonPojo );
        inMasterDataDto = getMasterDataDao().searchAllCategoryValue( inMasterDataDto );
        List<CrmRcaReasonPojo> list = inMasterDataDto.getCrmRcaReasonsList();
        SortingUtil<CrmRcaReasonPojo, BigDecimal> sorter = new SortingUtil<CrmRcaReasonPojo, BigDecimal>( "valueAlias",
                                                                                                          new BigDecimal( 0 ) );
        Collections.sort( list, sorter );
        return list;
    }

    @Override
    public CrmQrcDto searchCustomerWaiverList( CrmQrcDto inCrmQrcDto )
    {
        LOGGER.info( "Inside QrcManagerDaoImpl : searchCustomerWaiverList" );
        CRMServiceCode crmServiceCode = CRMServiceCode.CRM999;
        List<CrmCustWaiverPojo> custWaiverPojos = null;
        Session session = null;
        Criteria criteria = null;
        if ( StringUtils.isValidObj( inCrmQrcDto.getCustomerId() ) )
        {
            try
            {
                MDC.put( "KEY", "CUSTID:" + inCrmQrcDto.getCustomerId() + ", USERID:" + inCrmQrcDto.getUserId() );
                session = HibernateUtil.getCurrentSession();
                criteria = session.createCriteria( CrmCustWaiverPojo.class );
                if ( StringUtils.isNotBlank( inCrmQrcDto.getCustomerId() ) )
                {
                    criteria.add( Restrictions.eq( "customerId", inCrmQrcDto.getCustomerId() ) );
                }
                if ( StringUtils.isNotBlank( inCrmQrcDto.getCrmCustWaiverPojo().getWorkflowId() ) )
                {
                    criteria.add( Restrictions.eq( "workflowId", inCrmQrcDto.getCrmCustWaiverPojo().getWorkflowId() ) );
                }
                criteria.setCacheable( true );
                criteria.setCacheRegion( CrmCustWaiverPojo.class.getName() );
                custWaiverPojos = criteria.list();
                if ( CommonValidator.isValidCollection( custWaiverPojos ) )
                {
                    inCrmQrcDto.setCrmCustWaiverPojosList( custWaiverPojos );
                    crmServiceCode = CRMServiceCode.CRM001;
                }
            }
            catch ( Exception ex )
            {
                LOGGER.error( "Exception while retreiving Customer Waiver List ", ex );
            }
            finally
            {
                CRMServiceUtils.closeSession( session );
                inCrmQrcDto.setStatusCode( crmServiceCode.getStatusCode() );
                inCrmQrcDto.setStatusDesc( crmServiceCode.getStatusDesc() );
                MDC.remove( "KEY" );
            }
        }
        return inCrmQrcDto;
    }

    @Override
    public CrmQrcDto modifyDeviceAndNetworkDetails( CrmQrcDto inCrmQrcDto )
    {
        Session session = null;
        Transaction transaction = null;
        CrmCustomerActivityPojo activityLog = null;
        CrmCustomerActivityPojo macActivity = null;
        CRMServiceCode crmServiceCode = CRMServiceCode.CRM999;
        try
        {
            MDC.put( "KEY", "CUSTID:" + inCrmQrcDto.getCustomerId() + ", USERID:" + inCrmQrcDto.getUserId() );
            session = HibernateUtil.getCurrentSession();
            transaction = session.beginTransaction();
            activityLog = new CrmCustomerActivityPojo();
            CrmNetworkConfigurationsPojo networkConfigurationsPojo = null;
            CrmPartnerNetworkConfigPojo crmPartnerNetworkConfigPojo = null;
            CrmOrderDetailsPojo crmOrderDetailsPojo = null;
            LOGGER.info( "RECIEVED ACTIVITY ACTION IS:" + inCrmQrcDto.getActivityAction() );
            LOGGER.info( "RECIEVED USER ID:" + inCrmQrcDto.getUserId() );
            if ( StringUtils.isNotEmpty( inCrmQrcDto.getSrTicketNo() ) )
            {
                if ( !isValidTicketNo( inCrmQrcDto.getSrTicketNo(), inCrmQrcDto.getCustomerId() ) )
                {
                    LOGGER.info( "Ticket no::: " + inCrmQrcDto.getSrTicketNo() );
                    crmServiceCode = CRMServiceCode.CRM402;
                }
                else
                    activityLog.setTicketId( inCrmQrcDto.getSrTicketNo() );
            }
            if ( !StringUtils.equals( crmServiceCode.getStatusCode(), CRMServiceCode.CRM402.getStatusCode() ) )
            {
                CRMCustomerActivityActions activityActions = CRMCustomerActivityActions.getObjByActionDesc( inCrmQrcDto
                        .getActivityAction() );
                if ( StringUtils.equals( CRMCustomerActivityActions.CPE_OWNERSHIP_CHANGE.getActionDesc(),
                                         inCrmQrcDto.getActivityAction() ) )
                {
                    crmOrderDetailsPojo = (CrmOrderDetailsPojo) session.get( CrmOrderDetailsPojo.class, inCrmQrcDto
                            .getOrderDetailsPojo().getRecordId() );
                    CRMServiceUtils.fillActivityDetails( crmOrderDetailsPojo, inCrmQrcDto.getOrderDetailsPojo(),
                                                         activityLog, activityActions );
                    CRMUtils.copyPropertyValue( crmOrderDetailsPojo, inCrmQrcDto.getOrderDetailsPojo(), null );
                    crmOrderDetailsPojo.setLastModifiedBy( inCrmQrcDto.getUserId() );
                    crmOrderDetailsPojo.setLastModifiedTime( Calendar.getInstance().getTime() );
                    crmOrderDetailsPojo = (CrmOrderDetailsPojo) session.merge( crmOrderDetailsPojo );
                    //
                    macActivity = new CrmCustomerActivityPojo();
                    macActivity.setTicketId( inCrmQrcDto.getSrTicketNo() );
                    networkConfigurationsPojo = (CrmNetworkConfigurationsPojo) session
                            .get( CrmNetworkConfigurationsPojo.class, inCrmQrcDto.getNetworkConfigurationsPojo()
                                    .getRecordId() );
                    CRMServiceUtils.fillActivityDetails( networkConfigurationsPojo,
                                                         inCrmQrcDto.getNetworkConfigurationsPojo(), macActivity,
                                                         CRMCustomerActivityActions.MAC_CHANGE );
                    CRMUtils.copyPropertyValue( networkConfigurationsPojo, inCrmQrcDto.getNetworkConfigurationsPojo(),
                                                null );
                    networkConfigurationsPojo.setLastModifiedBy( inCrmQrcDto.getUserId() );
                    networkConfigurationsPojo.setLastModifiedTime( Calendar.getInstance().getTime() );
                    networkConfigurationsPojo = (CrmNetworkConfigurationsPojo) session
                            .merge( networkConfigurationsPojo );
                    if ( StringUtils.equals( inCrmQrcDto.getCustomerDetailsPojo().getProduct(),
                                             CRMDisplayListConstants.ETHERNET_ON_CABLE.getCode() ) )
                    {
                        crmPartnerNetworkConfigPojo = (CrmPartnerNetworkConfigPojo) session
                                .get( CrmPartnerNetworkConfigPojo.class,
                                      Long.parseLong( networkConfigurationsPojo.getOption82() ) );
                    }
                    inCrmQrcDto.setNetworkConfigurationsPojo( networkConfigurationsPojo );
                }
                else if ( StringUtils.equals( CRMCustomerActivityActions.MAC_CHANGE.getActionDesc(),
                                              inCrmQrcDto.getActivityAction() ) )
                {
                    LOGGER.info( "MAC Change request:: " );
                    networkConfigurationsPojo = (CrmNetworkConfigurationsPojo) session
                            .get( CrmNetworkConfigurationsPojo.class, inCrmQrcDto.getNetworkConfigurationsPojo()
                                    .getRecordId() );
                    CRMServiceUtils.fillActivityDetails( networkConfigurationsPojo,
                                                         inCrmQrcDto.getNetworkConfigurationsPojo(), activityLog,
                                                         activityActions );
                    CRMUtils.copyPropertyValue( networkConfigurationsPojo, inCrmQrcDto.getNetworkConfigurationsPojo(),
                                                null );
                    session.merge( networkConfigurationsPojo );
                }
                else
                {
                    networkConfigurationsPojo = (CrmNetworkConfigurationsPojo) session
                            .get( CrmNetworkConfigurationsPojo.class, inCrmQrcDto.getNetworkConfigurationsPojo()
                                    .getRecordId() );
                    if ( StringUtils.equals( inCrmQrcDto.getActivityAction(),
                                             CRMCustomerActivityActions.OPTION_82_CHANGE.getActionDesc() ) )
                    {
                        if ( StringUtils.equals( inCrmQrcDto.getCustomerDetailsPojo().getProduct(),
                                                 CRMDisplayListConstants.ETHERNET_ON_CABLE.getCode() ) )
                        {
                            CrmPartnerNetworkConfigPojo newCrmPartnerNetworkConfigPojo = null;
                            if ( StringUtils.isNumeric( networkConfigurationsPojo.getOption82() ) )
                            {
                                crmPartnerNetworkConfigPojo = (CrmPartnerNetworkConfigPojo) session
                                        .get( CrmPartnerNetworkConfigPojo.class,
                                              StringUtils.numericValue( networkConfigurationsPojo.getOption82() ) );
                            }
                            if ( StringUtils.isNumeric( inCrmQrcDto.getNetworkConfigurationsPojo().getOption82() ) )
                            {
                                newCrmPartnerNetworkConfigPojo = (CrmPartnerNetworkConfigPojo) session
                                        .get( CrmPartnerNetworkConfigPojo.class, StringUtils.numericValue( inCrmQrcDto
                                                .getNetworkConfigurationsPojo().getOption82() ) );
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
                            activityLog.setOldValue( networkConfigurationsPojo.getOption82() );
                            activityLog.setNewValue( inCrmQrcDto.getNetworkConfigurationsPojo().getOption82() );
                        }
                    }
                    else
                        CRMServiceUtils.fillActivityDetails( networkConfigurationsPojo,
                                                             inCrmQrcDto.getNetworkConfigurationsPojo(), activityLog,
                                                             activityActions );
                    LOGGER.info( "networkkk record id is:" + inCrmQrcDto.getNetworkConfigurationsPojo().getRecordId() );
                    LOGGER.info( "NETTTTWORK CONFIGURATION POJO:" + networkConfigurationsPojo );
                    /* if ( StringUtils.equals( CRMCustomerActivityActions.MAC_CHANGE.getActionDesc(),
                                              inCrmQrcDto.getActivityAction() ) )
                     {
                         CRMServiceUtils.fillActivityDetails( networkConfigurationsPojo,
                                                              inCrmQrcDto.getNetworkConfigurationsPojo(), activityLog,
                                                              CRMCustomerActivityActions.MAC_CHANGE );
                     }
                     else
                     {
                         CRMServiceUtils.fillActivityDetails( networkConfigurationsPojo,
                                                              inCrmQrcDto.getNetworkConfigurationsPojo(), activityLog,
                                                              CRMCustomerActivityActions.OPTION_82_CHANGE );
                     }*/
                    CRMUtils.copyPropertyValue( networkConfigurationsPojo, inCrmQrcDto.getNetworkConfigurationsPojo(),
                                                null );
                    networkConfigurationsPojo.setLastModifiedBy( inCrmQrcDto.getUserId() );
                    networkConfigurationsPojo.setLastModifiedTime( Calendar.getInstance().getTime() );
                    networkConfigurationsPojo = (CrmNetworkConfigurationsPojo) session
                            .merge( networkConfigurationsPojo );
                    if ( StringUtils.equals( inCrmQrcDto.getCustomerDetailsPojo().getProduct(),
                                             CRMDisplayListConstants.ETHERNET_ON_CABLE.getCode() ) )
                    {
                        crmPartnerNetworkConfigPojo = (CrmPartnerNetworkConfigPojo) session
                                .get( CrmPartnerNetworkConfigPojo.class,
                                      Long.parseLong( networkConfigurationsPojo.getOption82() ) );
                    }
                    inCrmQrcDto.setNetworkConfigurationsPojo( networkConfigurationsPojo );
                }
                if ( StringUtils.isValidObj( networkConfigurationsPojo )
                        || StringUtils.isValidObj( crmOrderDetailsPojo ) )
                {
                    //cpeownership- crmorderdetails
                    inCrmQrcDto.setOrderDetailsPojo( crmOrderDetailsPojo );
                    inCrmQrcDto.setCrmPartnerNetworkConfigPojo( crmPartnerNetworkConfigPojo );
                    LOGGER.info( "PARTNER NETWORK CONFIG POJO:" + inCrmQrcDto.getCrmPartnerNetworkConfigPojo() );
                    CrmBillingDto billingDto = getCrmBillingManager().changeDeviceDetails( inCrmQrcDto );
                    crmServiceCode = CRMServiceCode.valueOf( billingDto.getStatusCode() );
                    inCrmQrcDto.setBillingErrorCode( billingDto.getBillingErrorCode() );
                    LOGGER.info( "billing error code in dao:" + inCrmQrcDto.getBillingErrorCode() + ",crmServiceCode:"
                            + crmServiceCode );
                    if ( crmServiceCode == CRMServiceCode.CRM001 )
                    {
                        preparedActivityLogPojo( activityLog, inCrmQrcDto.getCustomerId(), inCrmQrcDto.getUserId(),
                                                 inCrmQrcDto.getActivityAction(), null,
                                                 inCrmQrcDto.getClientIPAddress(), inCrmQrcDto.getServerIPAddress() );
                        long activityId = CRMServiceUtils.createActivityLog( activityLog );
                        if ( StringUtils.isValidObj( inCrmQrcDto.getRemarksPojo() ) )
                        {
                            if ( StringUtils.isBlank( inCrmQrcDto.getRemarksPojo().getMappingId() ) )
                            {
                                inCrmQrcDto.getRemarksPojo().setMappingId( inCrmQrcDto.getCustomerId() );
                            }
                            inCrmQrcDto.getRemarksPojo().setActions( inCrmQrcDto.getActivityAction() );
                            inCrmQrcDto.getRemarksPojo().setCreatedBy( inCrmQrcDto.getUserId() );
                            inCrmQrcDto.getRemarksPojo().setCreatedTime( Calendar.getInstance().getTime() );
                            inCrmQrcDto.getRemarksPojo().setActivityId( activityId );
                            inCrmQrcDto.getRemarksPojo().setMappingType( CRMRCAReason.QRC.getStatusCode() );
                            inCrmQrcDto.getRemarksPojo().setReasonId( inCrmQrcDto.getRemarksPojo().getReasonId() );
                            session.save( inCrmQrcDto.getRemarksPojo() );
                        }
                        if ( StringUtils.isValidObj( macActivity ) )
                        {
                            preparedActivityLogPojo( macActivity, inCrmQrcDto.getCustomerId(), inCrmQrcDto.getUserId(),
                                                     CRMCustomerActivityActions.MAC_CHANGE.getActionDesc(), null,
                                                     inCrmQrcDto.getClientIPAddress(), inCrmQrcDto.getServerIPAddress() );
                            activityId = CRMServiceUtils.createActivityLog( macActivity );
                            if ( StringUtils.isValidObj( inCrmQrcDto.getRemarksPojo() ) )
                            {
                                if ( StringUtils.isBlank( inCrmQrcDto.getRemarksPojo().getMappingId() ) )
                                {
                                    inCrmQrcDto.getRemarksPojo().setMappingId( inCrmQrcDto.getCustomerId() );
                                }
                                inCrmQrcDto.getRemarksPojo().setActions( inCrmQrcDto.getActivityAction() );
                                inCrmQrcDto.getRemarksPojo().setCreatedBy( inCrmQrcDto.getUserId() );
                                inCrmQrcDto.getRemarksPojo().setCreatedTime( Calendar.getInstance().getTime() );
                                inCrmQrcDto.getRemarksPojo().setActivityId( activityId );
                                inCrmQrcDto.getRemarksPojo().setMappingType( CRMRCAReason.QRC.getStatusCode() );
                                session.save( inCrmQrcDto.getRemarksPojo() );
                            }
                        }
                        LOGGER.info( "crmServiceCode :" + crmServiceCode );
                        transaction.commit();
                        crmServiceCode = CRMServiceCode.CRM001;
                    }
                }
                else
                    crmServiceCode = CRMServiceCode.CRM014;
            }
        }
        catch ( HibernateException ex )
        {
            LOGGER.error( "HibernateException  modify Device And Network Details:", ex );
            CRMServiceUtils.rollback( transaction );
            CRMServiceUtils.closeSessionOnException( session );
        }
        catch ( Exception ex )
        {
            LOGGER.error( "Getting Error while modify Device And Network Details: ", ex );
        }
        finally
        {
            CRMServiceUtils.rollback( transaction );
            CRMServiceUtils.closeSession( session );
            LOGGER.info( "inCrmQrcDto::" + inCrmQrcDto.getStatusCode() );
            if ( StringUtils.isEmpty( inCrmQrcDto.getStatusCode() ) )
            {
                LOGGER.info( "crmServiceCode s:" + crmServiceCode.getStatusCode() );
                inCrmQrcDto.setStatusCode( crmServiceCode.getStatusCode() );
                inCrmQrcDto.setStatusDesc( crmServiceCode.getStatusDesc() );
                if ( crmServiceCode == CRMServiceCode.CRM001 )
                {
                    HibernateUtil.evictAll( RemarksPojo.class.getName(), null, null );
                }
            }
            MDC.remove( "KEY" );
        }
        return inCrmQrcDto;
    }

    @Override
    public CrmQrcDto searchAccessories( CrmQrcDto inCrmQrcDto, String inParam )
    {
        LOGGER.info( "Inside QrcManagerDaoImpl : searchACCESSORIES" );
        CRMServiceCode crmServiceCode = CRMServiceCode.CRM999;
        List<CrmCustAssetDetailsPojo> custAssetPojos = new ArrayList<CrmCustAssetDetailsPojo>();
        Session session = null;
        Criteria criteria = null;
        if ( StringUtils.isValidObj( inCrmQrcDto.getCustomerRecordId() ) )
        {
            try
            {
                session = HibernateUtil.getCurrentSession();
                criteria = session.createCriteria( CrmCustAssetDetailsPojo.class );
                criteria.add( Restrictions.eq( "customerRecordId", inCrmQrcDto.getCustomerRecordId() ) );
                criteria.add( Restrictions.eq( "categoryName", inParam ) );
                criteria.setCacheable( true );
                criteria.setCacheRegion( CrmCustAssetDetailsPojo.class.getName() );
                custAssetPojos = criteria.list();
                if ( CommonValidator.isValidCollection( custAssetPojos ) )
                {
                    inCrmQrcDto.setCrmCustAssetDetailsPojos( custAssetPojos );
                    LOGGER.info( "size is:" + custAssetPojos.size() );
                    crmServiceCode = CRMServiceCode.CRM001;
                }
            }
            catch ( Exception ex )
            {
                LOGGER.error( "Exception while retreiving Customer Asset List ", ex );
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
    public CrmQrcDto activateCustomerBasePlan( CrmQrcDto inCrmQrcDto )
    {
        LOGGER.info( "Inside QrcManagerDaoImpl : activateCustomerBasePlan" );
        if ( StringUtils.isValidObj( inCrmQrcDto.getCustomerId() ) )
        {
            cancelOldRequestInBilllingIfExist( inCrmQrcDto );
            if ( StringUtils.equals( inCrmQrcDto.getPlanCategory(), "Base Plan Change" ) )
            {
                if ( StringUtils.isEmpty( inCrmQrcDto.getBillingErrorCode() ) )
                {
                    basePlanChangeOperation( inCrmQrcDto );
                }
            }
            else if ( StringUtils.equals( inCrmQrcDto.getPlanCategory(), IAppConstants.CHANGE_ADDON ) )
            {
                if ( StringUtils.isEmpty( inCrmQrcDto.getBillingErrorCode() ) )
                {
                    addOnAddedOperation( inCrmQrcDto );
                }
            }
            else if ( StringUtils.equals( inCrmQrcDto.getPlanCategory(), IAppConstants.REMOVE_ADDON ) )
            {
                if ( StringUtils.isEmpty( inCrmQrcDto.getBillingErrorCode() ) )
                {
                    addOnRemovalOperation( inCrmQrcDto, true );
                }
            }
        }
        return inCrmQrcDto;
    }

    private void addOnRemovalOperation( CrmQrcDto inCrmQrcDto, boolean isAutoRemoval )
    {
        LOGGER.info( "Inside addOnRemovalOperation" );
        Session session = null;
        Transaction transaction = null;
        CrmBillingDto crmBillingDto = null;
        CrmCustomerActivityPojo activityLog = null;
        CRMServiceCode crmServiceCode = CRMServiceCode.CRM999;
        Map<String, Long> evicts = new HashMap<String, Long>();
        try
        {
            MDC.put( "KEY", "CUSTID:" + inCrmQrcDto.getCustomerId() + ", USERID:" + inCrmQrcDto.getUserId() );
            session = HibernateUtil.getCurrentSession();
            transaction = session.beginTransaction();
            if ( StringUtils.equals( inCrmQrcDto.getActivationTime(), IAppConstants.IMMEDIATE_ACTIVATION ) )
            {
                activityLog = new CrmCustomerActivityPojo();
                CrmPlanDetailsPojo crmPlanDetailsPojoDB = (CrmPlanDetailsPojo) session
                        .get( CrmPlanDetailsPojo.class, inCrmQrcDto.getCrmPlanDetailsPojo().getRecordId() );
                CRMServiceUtils.fillActivityDetails( inCrmQrcDto.getCrmPlanDetailsPojo(), null, activityLog,
                                                     CRMCustomerActivityActions.ADDON_PLAN_REMOVAL );
                crmPlanDetailsPojoDB.setLastModifiedBy( inCrmQrcDto.getUserId() );
                crmPlanDetailsPojoDB.setLastModifiedTime( Calendar.getInstance().getTime() );
                // Remove AddOn Plan API
                crmBillingDto = new CrmBillingDto();
                crmBillingDto.setCustomerId( inCrmQrcDto.getCustomerDetailsPojo().getCustomerId() );
                crmBillingDto.setParamValue( inCrmQrcDto.getCrmPlanDetailsPojo().getAddOnPlanCode() );
                crmBillingDto.setBoolValue( true );
                getCrmBillingManager().cancelAdditionalPlan( crmBillingDto );
                if ( StringUtils.equals( crmBillingDto.getStatusCode(), CRMServiceCode.CRM001.getStatusCode() ) )
                {
                    crmPlanDetailsPojoDB.setAddOnPlanCode( null );
                    session.update( crmPlanDetailsPojoDB );
                    evicts.put( CrmPlanDetailsPojo.class.getName(), null );
                    inCrmQrcDto.setCrmPlanDetailsPojo( crmPlanDetailsPojoDB );
                    if ( isAutoRemoval )
                    {
                        immediateTicketOperation( inCrmQrcDto );
                        activityLog.setTicketId( inCrmQrcDto.getCrmSrTicketsPojo().getSrId() );
                        long activityId = saveCustomerActivity( activityLog, inCrmQrcDto,
                                                                CRMCustomerActivityActions.ADDON_PLAN_REMOVAL );
                        if ( StringUtils.isValidObj( inCrmQrcDto.getRemarksPojo() ) )
                        {
                            inCrmQrcDto.getRemarksPojo().setMappingId( inCrmQrcDto.getCustomerId() );
                            inCrmQrcDto.getRemarksPojo().setActions( inCrmQrcDto.getActivityAction() );
                            inCrmQrcDto.getRemarksPojo().setCreatedBy( inCrmQrcDto.getUserId() );
                            inCrmQrcDto.getRemarksPojo().setCreatedTime( Calendar.getInstance().getTime() );
                            inCrmQrcDto.getRemarksPojo().setActivityId( activityId );
                            inCrmQrcDto.getRemarksPojo().setMappingType( CRMRCAReason.QRC.getStatusCode() );
                            inCrmQrcDto.getRemarksPojo().setReasonId( inCrmQrcDto.getRemarksPojo().getReasonId() );
                            session.save( inCrmQrcDto.getRemarksPojo() );
                            evicts.put( RemarksPojo.class.getName(), null );
                        }
                    }
                    transaction.commit();
                    crmServiceCode = CRMServiceCode.CRM001;
                }
                else
                {
                    crmServiceCode = CRMServiceCode.valueOf( crmBillingDto.getStatusCode() );
                    inCrmQrcDto.setBillingErrorCode( crmBillingDto.getBillingErrorCode() );
                }
            }
            else
            {
                Date activationTime = null;
                if ( StringUtils.equals( inCrmQrcDto.getCustomerDetailsPojo().getServiceType(),
                                         CRMDisplayListConstants.PRE_PAID.getCode() ) )
                {
                    activationTime = DateUtils.getNextBillingDate( inCrmQrcDto.getExpiryDate(), 1, Calendar.DATE );
                }
                else
                {
                    activationTime = DateUtils.getNextBillingDate( inCrmQrcDto.getCustomerDetailsPojo().getBillDate() );
                }
                crmBillingDto = new CrmBillingDto();
                crmBillingDto.setCustomerId( inCrmQrcDto.getCustomerDetailsPojo().getCustomerId() );
                crmBillingDto.setParamValue( inCrmQrcDto.getCrmPlanDetailsPojo().getAddOnPlanCode() );
                crmBillingDto.setBoolValue( false );
                getCrmBillingManager().cancelAdditionalPlan( crmBillingDto );
                if ( StringUtils.equals( crmBillingDto.getStatusCode(), CRMServiceCode.CRM001.getStatusCode() ) )
                {
                    long transactionId = Long.valueOf( crmBillingDto.getParamValue() );
                    NBCTicketOperation( inCrmQrcDto, transactionId, activationTime );
                    crmBillingDto.setParamValue( null );
                    //                    if ( StringUtils.isValidObj( inCrmQrcDto.getRemarksPojo() ) )
                    //                    {
                    //                        inCrmQrcDto.getRemarksPojo().setMappingId( inCrmQrcDto.getCustomerId() );
                    //                        inCrmQrcDto.getRemarksPojo().setActions( inCrmQrcDto.getActivityAction() );
                    //                        inCrmQrcDto.getRemarksPojo().setCreatedBy( inCrmQrcDto.getUserId() );
                    //                        inCrmQrcDto.getRemarksPojo().setCreatedTime( Calendar.getInstance().getTime() );
                    //                        session.save( inCrmQrcDto.getRemarksPojo() );
                    //                    }
                    transaction.commit();
                    crmServiceCode = CRMServiceCode.CRM001;
                }
                else
                {
                    crmServiceCode = CRMServiceCode.valueOf( crmBillingDto.getStatusCode() );
                    inCrmQrcDto.setBillingErrorCode( crmBillingDto.getBillingErrorCode() );
                }
            }
        }
        catch ( HibernateException ex )
        {
            LOGGER.error( "HibernateException while saving customer AddOn Removal Operation", ex );
            CRMServiceUtils.rollback( transaction );
            CRMServiceUtils.closeSessionOnException( session );
        }
        catch ( Exception ex )
        {
            LOGGER.error( "Exception while saving customer AddOn Removal Operation ", ex );
        }
        finally
        {
            CRMServiceUtils.closeSession( session );
            inCrmQrcDto.setStatusCode( crmServiceCode.getStatusCode() );
            inCrmQrcDto.setStatusDesc( crmServiceCode.getStatusDesc() );
            if ( crmServiceCode == CRMServiceCode.CRM001 )
            {
                HibernateUtil.evictAll( evicts );
            }
            MDC.remove( "KEY" );
        }
    }

    private void addOnAddedOperation( CrmQrcDto inCrmQrcDto )
    {
        LOGGER.info( "Inside addOnAddedOperation" );
        Session session = null;
        Transaction transaction = null;
        CrmCustomerActivityPojo activityLog = null;
        CRMServiceCode crmServiceCode = CRMServiceCode.CRM999;
        Map<String, Long> evicts = new HashMap<String, Long>();
        try
        {
            MDC.put( "KEY", "CUSTID:" + inCrmQrcDto.getCustomerId() + ", USERID:" + inCrmQrcDto.getUserId() );
            session = HibernateUtil.getCurrentSession();
            transaction = session.beginTransaction();
            if ( StringUtils.equals( inCrmQrcDto.getActivationTime(), IAppConstants.IMMEDIATE_ACTIVATION ) )
            {
                activityLog = new CrmCustomerActivityPojo();
                CrmPlanDetailsPojo crmPlanDetailsPojoDB = (CrmPlanDetailsPojo) session
                        .get( CrmPlanDetailsPojo.class, inCrmQrcDto.getCrmPlanDetailsPojo().getRecordId() );
                if ( StringUtils.isValidObj( inCrmQrcDto.getAddOnPlanPojo() )
                        && StringUtils.isValidObj( crmPlanDetailsPojoDB ) )
                {
                    LOGGER.info( "Addon is valid Object Plan Code:: " + inCrmQrcDto.getAddOnPlanPojo().getPlanCode() );
                    if ( StringUtils.isNotBlank( inCrmQrcDto.getCrmPlanDetailsPojo().getAddOnPlanCode() ) )
                    {
                        CrmPlanMasterPojo addOnPlanMasterPojo = CRMServiceUtils
                                .getDBValues( CrmPlanMasterPojo.class, "planCode", inCrmQrcDto.getCrmPlanDetailsPojo()
                                        .getAddOnPlanCode() );
                        if ( StringUtils.isValidObj( addOnPlanMasterPojo ) )
                        {
                            activityLog.setOldValue( addOnPlanMasterPojo.getPlanName() );
                        }
                    }
                    activityLog.setNewValue( inCrmQrcDto.getAddOnPlanPojo().getPlanName() );
                    crmPlanDetailsPojoDB.setAddOnPlanCode( inCrmQrcDto.getAddOnPlanPojo().getPlanCode() );
                    crmPlanDetailsPojoDB.setLastModifiedBy( inCrmQrcDto.getUserId() );
                    crmPlanDetailsPojoDB.setLastModifiedTime( Calendar.getInstance().getTime() );
                    //Add Plan Billing API
                    CrmBillingDto crmBillingDto = getCrmBillingManager().addPlan( inCrmQrcDto,
                                                                                  inCrmQrcDto.getAddOnPlanPojo()
                                                                                          .getPlanCode(), true );
                    if ( StringUtils.equals( crmBillingDto.getStatusCode(), CRMServiceCode.CRM001.getStatusCode() ) )
                    {
                        session.update( crmPlanDetailsPojoDB );
                        evicts.put( CrmPlanDetailsPojo.class.getName(), null );
                        inCrmQrcDto.setCrmPlanDetailsPojo( crmPlanDetailsPojoDB );
                        immediateTicketOperation( inCrmQrcDto );
                        activityLog.setTicketId( inCrmQrcDto.getCrmSrTicketsPojo().getSrId() );
                        long activityId = saveCustomerActivity( activityLog, inCrmQrcDto,
                                                                CRMCustomerActivityActions.ADDON_PLAN_CHANGE );
                        if ( StringUtils.isValidObj( inCrmQrcDto.getRemarksPojo() ) )
                        {
                            inCrmQrcDto.getRemarksPojo().setMappingId( inCrmQrcDto.getCustomerId() );
                            inCrmQrcDto.getRemarksPojo().setActions( inCrmQrcDto.getActivityAction() );
                            inCrmQrcDto.getRemarksPojo().setCreatedBy( inCrmQrcDto.getUserId() );
                            inCrmQrcDto.getRemarksPojo().setCreatedTime( Calendar.getInstance().getTime() );
                            inCrmQrcDto.getRemarksPojo().setActivityId( activityId );
                            inCrmQrcDto.getRemarksPojo().setMappingType( CRMRCAReason.QRC.getStatusCode() );
                            inCrmQrcDto.getRemarksPojo().setReasonId( inCrmQrcDto.getRemarksPojo().getReasonId() );
                            session.save( inCrmQrcDto.getRemarksPojo() );
                            evicts.put( RemarksPojo.class.getName(), null );
                        }
                        transaction.commit();
                        crmServiceCode = CRMServiceCode.CRM001;
                    }
                    else
                    {
                        crmServiceCode = CRMServiceCode.valueOf( crmBillingDto.getStatusCode() );
                        inCrmQrcDto.setBillingErrorCode( crmBillingDto.getBillingErrorCode() );
                    }
                }
            }
            else
            {
                Date activationTime = null;
                if ( StringUtils.equals( inCrmQrcDto.getCustomerDetailsPojo().getServiceType(),
                                         CRMDisplayListConstants.PRE_PAID.getCode() ) )
                {
                    activationTime = DateUtils.getNextBillingDate( inCrmQrcDto.getExpiryDate(), 1, Calendar.DATE );
                }
                else
                {
                    activationTime = DateUtils.getNextBillingDate( inCrmQrcDto.getCustomerDetailsPojo().getBillDate() );
                }
                //Add Plan Billing API
                CrmBillingDto crmBillingDto = getCrmBillingManager().addPlan( inCrmQrcDto,
                                                                              inCrmQrcDto.getAddOnPlanPojo()
                                                                                      .getPlanCode(), false );
                if ( StringUtils.equals( crmBillingDto.getStatusCode(), CRMServiceCode.CRM001.getStatusCode() ) )
                {
                    long transactionId = Long.valueOf( crmBillingDto.getParamValue() );
                    NBCTicketOperation( inCrmQrcDto, transactionId, activationTime );
                    crmBillingDto.setParamValue( null );
                    //                    if ( StringUtils.isValidObj( inCrmQrcDto.getRemarksPojo() ) )
                    //                    {
                    //                        inCrmQrcDto.getRemarksPojo().setMappingId( inCrmQrcDto.getCustomerId() );
                    //                        inCrmQrcDto.getRemarksPojo().setActions( inCrmQrcDto.getActivityAction() );
                    //                        inCrmQrcDto.getRemarksPojo().setCreatedBy( inCrmQrcDto.getUserId() );
                    //                        inCrmQrcDto.getRemarksPojo().setCreatedTime( Calendar.getInstance().getTime() );
                    //                        session.save( inCrmQrcDto.getRemarksPojo() );
                    //                    }
                    transaction.commit();
                    crmServiceCode = CRMServiceCode.CRM001;
                }
                else
                {
                    crmServiceCode = CRMServiceCode.valueOf( crmBillingDto.getStatusCode() );
                    inCrmQrcDto.setBillingErrorCode( crmBillingDto.getBillingErrorCode() );
                }
            }
        }
        catch ( HibernateException ex )
        {
            LOGGER.error( "HibernateException while saving customer AddOn Added Operation ", ex );
            CRMServiceUtils.rollback( transaction );
            CRMServiceUtils.closeSessionOnException( session );
        }
        catch ( Exception ex )
        {
            LOGGER.error( "Exception while saving customer AddOn Added Operation ", ex );
        }
        finally
        {
            CRMServiceUtils.closeSession( session );
            if ( StringUtils.isBlank( inCrmQrcDto.getStatusCode() )
                    || StringUtils.equals( CRMServiceCode.CRM405.getStatusCode(), inCrmQrcDto.getStatusCode() ) )
            {
                inCrmQrcDto.setStatusCode( crmServiceCode.getStatusCode() );
                inCrmQrcDto.setStatusDesc( crmServiceCode.getStatusDesc() );
            }
            if ( crmServiceCode == CRMServiceCode.CRM001 )
            {
                HibernateUtil.evictAll( evicts );
            }
            MDC.remove( "KEY" );
        }
    }

    private void basePlanChangeOperation( CrmQrcDto inCrmQrcDto )
    {
        LOGGER.info( "Inside basePlanChangeOperation" );
        Session session = null;
        Transaction transaction = null;
        CrmCustomerActivityPojo activityLog = null;
        String basePlanCode = null;
        boolean removalStatus = true;
        CRMServiceCode crmServiceCode = CRMServiceCode.CRM997;
        Map<String, Long> evicts = new HashMap<String, Long>();
        try
        {
            MDC.put( "KEY", "CUSTID:" + inCrmQrcDto.getCustomerId() + ", USERID:" + inCrmQrcDto.getUserId() );
            activityLog = new CrmCustomerActivityPojo();
            session = HibernateUtil.getCurrentSession();
            transaction = session.beginTransaction();
            basePlanCode = inCrmQrcDto.getCrmPlanDetailsPojo().getBasePlanCode();
            if ( StringUtils.equals( inCrmQrcDto.getActivationTime(), IAppConstants.IMMEDIATE_ACTIVATION ) )
            {
                LOGGER.info( "Going to migrate plan for immediate action" );
                CrmPlanDetailsPojo crmPlanDetailsPojoDB = (CrmPlanDetailsPojo) session
                        .get( CrmPlanDetailsPojo.class, inCrmQrcDto.getCrmPlanDetailsPojo().getRecordId() );
                if ( StringUtils.isValidObj( crmPlanDetailsPojoDB ) )
                {
                    CrmPlanMasterPojo oldBasePlanMasterPojo = CRMServiceUtils.getDBValues( CrmPlanMasterPojo.class,
                                                                                           "planCode",
                                                                                           crmPlanDetailsPojoDB
                                                                                                   .getBasePlanCode() );
                    activityLog.setOldValue( oldBasePlanMasterPojo.getPlanName() );
                    activityLog.setNewValue( inCrmQrcDto.getPlanMasterPojo().getPlanName() );
                    if ( StringUtils.equals( oldBasePlanMasterPojo.getPlanName(), inCrmQrcDto.getPlanMasterPojo()
                            .getPlanName() ) )
                    {
                        LOGGER.info( "Customer ID:: " + inCrmQrcDto.getCustomerId() + " Old Plan Name::"
                                + oldBasePlanMasterPojo.getPlanName() + ", New Plan Name::"
                                + inCrmQrcDto.getPlanMasterPojo().getPlanName() );
                    }
                    crmPlanDetailsPojoDB.setBasePlanCode( inCrmQrcDto.getPlanMasterPojo().getPlanCode() );
                    if ( StringUtils
                            .equals( inCrmQrcDto.getPlanMasterPojo().getAddonAllowedYn(), IAppConstants.NO_CHAR )
                            && StringUtils.isNotEmpty( inCrmQrcDto.getCrmPlanDetailsPojo().getAddOnPlanCode() ) )
                    {
                        addOnRemovalOperation( inCrmQrcDto, false );
                        if ( StringUtils.equals( CRMServiceCode.CRM001.getStatusCode(), inCrmQrcDto.getStatusCode() ) )
                            removalStatus = true;
                        else
                            removalStatus = false;
                    }
                    if ( removalStatus )
                    {
                        crmPlanDetailsPojoDB.setLastModifiedBy( inCrmQrcDto.getUserId() );
                        crmPlanDetailsPojoDB.setLastModifiedTime( Calendar.getInstance().getTime() );
                        //ChangeOffer Billing API
                        CrmPlanDetailsPojo crmPlanDetailsPojo = new CrmPlanDetailsPojo();
                        crmPlanDetailsPojo.setBasePlanCode( inCrmQrcDto.getPlanMasterPojo().getPlanCode() );
                        inCrmQrcDto.setCrmPlanDetailsPojo( crmPlanDetailsPojo );
                        if ( StringUtils.equals( inCrmQrcDto.getCustomerDetailsPojo().getServiceType(),
                                                 CRMDisplayListConstants.PRE_PAID.getCode() ) )
                        {
                            getCrmBillingManager().instantPrepaidOfferRenew( inCrmQrcDto );
                        }
                        else
                        {
                            getCrmBillingManager().instantOfferRenew( inCrmQrcDto );
                        }
                        if ( StringUtils.equals( inCrmQrcDto.getStatusCode(), CRMServiceCode.CRM001.getStatusCode() ) )
                        {
                            session.update( crmPlanDetailsPojoDB );
                            evicts.put( CrmPlanDetailsPojo.class.getName(), null );
                            inCrmQrcDto.setCrmPlanDetailsPojo( crmPlanDetailsPojoDB );
                            immediateTicketOperation( inCrmQrcDto );
                            long activityId = 0;
                            activityLog.setTicketId( inCrmQrcDto.getCrmSrTicketsPojo().getSrId() );
                            activityId = saveCustomerActivity( activityLog, inCrmQrcDto,
                                                               CRMCustomerActivityActions.BASE_PLAN_MIGRATION );
                            if ( StringUtils.isValidObj( inCrmQrcDto.getRemarksPojo() ) )
                            {
                                inCrmQrcDto.getRemarksPojo().setMappingId( inCrmQrcDto.getCustomerId() );
                                inCrmQrcDto.getRemarksPojo().setActions( inCrmQrcDto.getActivityAction() );
                                inCrmQrcDto.getRemarksPojo().setCreatedBy( inCrmQrcDto.getUserId() );
                                inCrmQrcDto.getRemarksPojo().setCreatedTime( Calendar.getInstance().getTime() );
                                inCrmQrcDto.getRemarksPojo().setActivityId( activityId );
                                inCrmQrcDto.getRemarksPojo().setMappingType( CRMRCAReason.QRC.getStatusCode() );
                                inCrmQrcDto.getRemarksPojo().setReasonId( inCrmQrcDto.getRemarksPojo().getReasonId() );
                                session.save( inCrmQrcDto.getRemarksPojo() );
                                evicts.put( RemarksPojo.class.getName(), null );
                            }
                            crmServiceCode = changeCustomerBrand( inCrmQrcDto, session, Calendar.getInstance()
                                    .getTime(), evicts );
                            if ( crmServiceCode == CRMServiceCode.CRM001 )
                                transaction.commit();
                        }
                        else
                        {
                            crmServiceCode = CRMServiceCode.valueOf( inCrmQrcDto.getStatusCode() );
                            inCrmQrcDto.setCrmPlanDetailsPojo( crmPlanDetailsPojoDB );
                        }
                    }
                }
            }
            else
            {
                LOGGER.info( "Going to migrate plan for NBC action" );
                Date activationTime = null;
                if ( StringUtils.equals( inCrmQrcDto.getCustomerDetailsPojo().getServiceType(),
                                         CRMDisplayListConstants.PRE_PAID.getCode() ) )
                {
                    activationTime = DateUtils.getNextBillingDate( inCrmQrcDto.getExpiryDate(), 1, Calendar.DATE );
                }
                else
                {
                    activationTime = DateUtils.getNextBillingDate( inCrmQrcDto.getCustomerDetailsPojo().getBillDate() );
                }
                //ChangeOffer Billing API
                CrmPlanDetailsPojo crmPlanDetailsPojo = new CrmPlanDetailsPojo();
                crmPlanDetailsPojo.setBasePlanCode( inCrmQrcDto.getPlanMasterPojo().getPlanCode() );
                inCrmQrcDto.setCrmPlanDetailsPojo( crmPlanDetailsPojo );
                getCrmBillingManager().changeOffer( inCrmQrcDto, activationTime );
                if ( StringUtils.equals( inCrmQrcDto.getStatusCode(), CRMServiceCode.CRM001.getStatusCode() ) )
                {
                    long transactionId = Long.valueOf( inCrmQrcDto.getSrTicketNo() );
                    NBCTicketOperation( inCrmQrcDto, transactionId, activationTime );
                    inCrmQrcDto.setSrTicketNo( null );
                    //speed up-gradation for Fiber Customer
                    speedUpGradation( inCrmQrcDto, session, basePlanCode );
                    //                    if ( StringUtils.isValidObj( inCrmQrcDto.getRemarksPojo() ) )
                    //                    {
                    //                        inCrmQrcDto.getRemarksPojo().setMappingId( inCrmQrcDto.getCustomerId() );
                    //                        inCrmQrcDto.getRemarksPojo().setActions( inCrmQrcDto.getActivityAction() );
                    //                        inCrmQrcDto.getRemarksPojo().setCreatedBy( inCrmQrcDto.getUserId() );
                    //                        inCrmQrcDto.getRemarksPojo().setCreatedTime( Calendar.getInstance().getTime() );
                    //                        session.save( inCrmQrcDto.getRemarksPojo() );
                    //                    }
                    transaction.commit();
                    crmServiceCode = CRMServiceCode.CRM001;
                }
                else
                {
                    crmServiceCode = CRMServiceCode.valueOf( inCrmQrcDto.getStatusCode() );
                }
            }
        }
        catch ( HibernateException ex )
        {
            LOGGER.error( "HibernateException while basePlanChangeOperation ", ex );
            CRMServiceUtils.rollback( transaction );
            CRMServiceUtils.closeSessionOnException( session );
        }
        catch ( Exception ex )
        {
            LOGGER.error( "Exception while basePlanChangeOperation ", ex );
        }
        finally
        {
            CRMServiceUtils.closeSession( session );
            if ( StringUtils.isBlank( inCrmQrcDto.getStatusCode() )
                    || StringUtils.equals( CRMServiceCode.CRM405.getStatusCode(), inCrmQrcDto.getStatusCode() ) )
            {
                inCrmQrcDto.setStatusCode( crmServiceCode.getStatusCode() );
                inCrmQrcDto.setStatusDesc( crmServiceCode.getStatusDesc() );
            }
            if ( crmServiceCode == CRMServiceCode.CRM001 )
            {
                HibernateUtil.evictAll( evicts );
            }
            LOGGER.info( "Status Code:: " + inCrmQrcDto.getStatusCode() );
            MDC.remove( "KEY" );
        }
    }

    private void cancelOldRequestInBilllingIfExist( CrmQrcDto inCrmQrcDto )
    {
        LOGGER.info( "Inside cancelOldRequestInBilllingIfExist" );
        CrmBillingDto crmBillingDto = null;
        if ( CommonValidator.isValidCollection( inCrmQrcDto.getCrmSrTicketsPojos() ) )
        {
            for ( CrmSrTicketsPojo crmSrTicketsPojo : inCrmQrcDto.getCrmSrTicketsPojos() )
            {
                if ( StringUtils.isNotEmpty( crmSrTicketsPojo.getAPITransactionId() ) )
                {
                    crmBillingDto = getCrmBillingManager().cancelPlanOfferMigration( crmSrTicketsPojo
                                                                                             .getAPITransactionId() );
                    if ( !StringUtils.equals( crmBillingDto.getStatusCode(), CRMServiceCode.CRM001.getStatusCode() ) )
                    {
                        inCrmQrcDto.setStatusCode( crmBillingDto.getStatusCode() );
                        inCrmQrcDto.setStatusDesc( crmBillingDto.getStatusDesc() );
                        inCrmQrcDto.setBillingErrorCode( crmBillingDto.getBillingErrorCode() );
                    }
                }
            }
        }
    }

    private long saveCustomerActivity( CrmCustomerActivityPojo activityLog,
                                       CrmQrcDto inCrmQrcDto,
                                       CRMCustomerActivityActions customerActivityActions )
    {
        activityLog.setCustomerId( inCrmQrcDto.getCustomerId() );
        activityLog.setAction( customerActivityActions.getActionDesc() );
        activityLog.setReason( customerActivityActions.getActionDesc() );
        activityLog.setCreatedBy( inCrmQrcDto.getUserId() );
        activityLog.setServiceIp( CRMUtils.getServerIp() );
        activityLog.setClientIp( inCrmQrcDto.getClientIPAddress() );
        activityLog.setServerIp( inCrmQrcDto.getServerIPAddress() );
        return CRMServiceUtils.createActivityLog( activityLog );
    }

    private void speedUpGradation( CrmQrcDto inCrmQrcDto, Session session, String basePlanCode )
    {
        Criteria criteria;
        if ( StringUtils.isValidObj( inCrmQrcDto.getCustomerDetailsPojo() )
                && inCrmQrcDto.getCustomerDetailsPojo().getProduct()
                        .equals( CRMDisplayListConstants.BROADBAND.getCode() ) )
        {
            criteria = session.createCriteria( CrmPlanMasterPojo.class );
            criteria.add( Restrictions.eq( "planCode", basePlanCode ) );
            criteria.setCacheable( true );
            criteria.setCacheRegion( CrmPlanMasterPojo.class.getName() );
            List<CrmPlanMasterPojo> crmPlanMasterPojos = criteria.list();
            if ( CommonValidator.isValidCollection( crmPlanMasterPojos ) )
            {
                CrmPlanMasterPojo crmPlanMasterPojo = crmPlanMasterPojos.get( 0 );
                if ( crmPlanMasterPojo.getPrimarySpeed() > inCrmQrcDto.getPlanMasterPojo().getPrimarySpeed() )
                {
                    try
                    {
                        CrmQrcDto tmpDto = CRMServiceUtils.generateAutoTicket( inCrmQrcDto.getCustomerId(),
                                                                               QRCRolCategories.SPEED_UP_GRADATION,
                                                                               null, inCrmQrcDto.getUserId() );
                        if ( StringUtils.equals( CRMServiceCode.CRM001.getStatusCode(), tmpDto.getStatusCode() ) )
                        {
                            inCrmQrcDto.setCrmSrTicketsPojo( tmpDto.getCrmSrTicketsPojo() );
                            inCrmQrcDto.setSrTicketNo( tmpDto.getCrmSrTicketsPojo().getSrId() );
                        }
                    }
                    catch ( SOAPException ex )
                    {
                        LOGGER.error( "Unable to generate ticket for " + QRCRolCategories.SPEED_UP_GRADATION, ex );
                    }
                }
            }
        }
    }

    /**
     * @param inCrmQrcDto
     * @param transactionId
     * @param activationTime
     */
    private void NBCTicketOperation( CrmQrcDto inCrmQrcDto, long transactionId, Date activationTime )
    {
        LOGGER.info( "Inside NBCTicketOperation" );
        boolean generateTicketFlag = true;
        if ( CommonValidator.isValidCollection( inCrmQrcDto.getCrmSrTicketsPojos() ) )
        {
            for ( CrmSrTicketsPojo srTicketsPojo : inCrmQrcDto.getCrmSrTicketsPojos() )
            {
                if ( srTicketsPojo.isEditable() )
                {
                    generateTicketFlag = false;
                    srTicketsPojo.setAPITransactionId( String.valueOf( transactionId ) );
                    srTicketsPojo.setFollowupOn( activationTime );
                    srTicketsPojo.setProcessingDate( DateUtils.getModifiedDate( activationTime, 30, Calendar.MINUTE ) );
                    LOGGER.info( "Editable Ticket No:: " + srTicketsPojo.getSrId() );
                    srTicketsPojo.setLastModifiedBy( inCrmQrcDto.getUserId() );
                    srTicketsPojo.setLastModifiedTime( Calendar.getInstance().getTime() );
                    inCrmQrcDto.setCrmSrTicketsPojo( srTicketsPojo );
                    CrmTicketHistoryPojo ticketHistory = new CrmTicketHistoryPojo();
                    ticketHistory.setCreatedBy( inCrmQrcDto.getUserId() );
                    ticketHistory.setAction( CrmActionEnum.FOLLOW_UP.getActionCode() );
                    if ( StringUtils.isValidObj( inCrmQrcDto.getRemarksPojo() ) )
                    {
                        ticketHistory.setRemarks( inCrmQrcDto.getRemarksPojo().getRemarks() );
                    }
                    else
                    {
                        ticketHistory.setRemarks( "Ticket followup for Plan Migration NBC." );
                    }
                    inCrmQrcDto.setTicketHistory( ticketHistory );
                    try
                    {
                        inCrmQrcDto = CRMServicesProxy
                                .getInstance()
                                .getCrmQrcService( IGlobalConstants.REMOTE, IGlobalConstants.APP )
                                .ticketOperations( CrmActionEnum.FOLLOW_UP.getActionCode(),
                                                   CrmSrTicketsPojo.class.getSimpleName(), inCrmQrcDto );
                    }
                    catch ( SOAPException ex )
                    {
                        LOGGER.error( "Exception while resolving ticket for immediateTicketOperation:"
                                              + srTicketsPojo.getSrId(), ex );
                    }
                    break;
                }
            }
        }
        if ( generateTicketFlag )
        {
            QRCRolCategories action = QRCRolCategories.getQRCCategoriesByEvent( inCrmQrcDto.getActivityAction() );
            CrmSrTicketsPojo crmSrTicketsPojo = new CrmSrTicketsPojo();
            crmSrTicketsPojo.setAPITransactionId( String.valueOf( transactionId ) );
            crmSrTicketsPojo.setFollowupOn( activationTime );
            crmSrTicketsPojo.setProcessingDate( DateUtils.getModifiedDate( activationTime, 30, Calendar.MINUTE ) );
            crmSrTicketsPojo.setResolutionType( (byte) 1 );
            try
            {
                CrmQrcDto tmpDto = CRMServiceUtils.generateAutoTicket( crmSrTicketsPojo, inCrmQrcDto.getCustomerId(),
                                                                       action, null, inCrmQrcDto.getUserId(),
                                                                       inCrmQrcDto.getRemarksPojo().getRemarks() );
                if ( StringUtils.equals( CRMServiceCode.CRM001.getStatusCode(), tmpDto.getStatusCode() ) )
                {
                    inCrmQrcDto.setSrTicketNo( tmpDto.getCrmSrTicketsPojo().getSrId() );
                    inCrmQrcDto.setCrmSrTicketsPojo( tmpDto.getCrmSrTicketsPojo() );
                }
            }
            catch ( SOAPException ex )
            {
                LOGGER.error( "Unable to generate ticket in NBC Ticket operation.", ex );
            }
        }
    }

    private void immediateTicketOperation( CrmQrcDto inCrmQrcDto )
    {
        boolean generateTicketFlag = true;
        if ( CommonValidator.isValidCollection( inCrmQrcDto.getCrmSrTicketsPojos() ) )
        {
            LOGGER.info( "Ticket List valid Object size:: " + inCrmQrcDto.getCrmSrTicketsPojos().size() );
            for ( CrmSrTicketsPojo srTicketsPojo : inCrmQrcDto.getCrmSrTicketsPojos() )
            {
                if ( srTicketsPojo.isEditable() )
                {
                    LOGGER.info( "Editable Ticket No:: " + srTicketsPojo.getSrId() );
                    generateTicketFlag = false;
                    srTicketsPojo.setStatus( CRMStatusCode.RESOLVED.getStatusCode() );
                    srTicketsPojo.setLastModifiedBy( inCrmQrcDto.getUserId() );
                    srTicketsPojo.setLastModifiedTime( Calendar.getInstance().getTime() );
                    CrmTicketHistoryPojo ticketHistory = new CrmTicketHistoryPojo();
                    ticketHistory.setCreatedBy( inCrmQrcDto.getUserId() );
                    ticketHistory.setAction( CrmActionEnum.RESOLVED.getActionCode() );
                    ticketHistory.setTicketId( srTicketsPojo.getSrId() );
                    if ( StringUtils.isValidObj( inCrmQrcDto.getRemarksPojo() ) )
                    {
                        ticketHistory.setRemarks( inCrmQrcDto.getRemarksPojo().getRemarks() );
                    }
                    else
                    {
                        ticketHistory.setRemarks( "Ticket resolved for immediate Plan Migration." );
                    }
                    CrmQrcDto qrcDto = new CrmQrcDto();
                    qrcDto.setTicketHistory( ticketHistory );
                    qrcDto.setCrmSrTicketsPojo( srTicketsPojo );
                    try
                    {
                        qrcDto = CRMServicesProxy
                                .getInstance()
                                .getCrmQrcService( IGlobalConstants.REMOTE, IGlobalConstants.APP )
                                .ticketOperations( CrmActionEnum.RESOLVED.getActionCode(),
                                                   CrmSrTicketsPojo.class.getSimpleName(), qrcDto );
                        inCrmQrcDto.setStatusCode( qrcDto.getStatusCode() );
                        inCrmQrcDto.setStatusDesc( qrcDto.getStatusDesc() );
                        inCrmQrcDto.setCrmSrTicketsPojo( qrcDto.getCrmSrTicketsPojo() );
                    }
                    catch ( SOAPException ex )
                    {
                        LOGGER.error( "Exception while resolving ticket for immediateTicketOperation:"
                                              + srTicketsPojo.getSrId(), ex );
                    }
                    break;
                }
            }
        }
        if ( generateTicketFlag )
        {
            QRCRolCategories action = QRCRolCategories.getQRCCategoriesByEvent( inCrmQrcDto.getActivityAction() );
            CrmSrTicketsPojo crmSrTicketsPojo = new CrmSrTicketsPojo();
            crmSrTicketsPojo.setResolutionType( (byte) 0 );
            try
            {
                String remarks = null;
                if ( StringUtils.isValidObj( inCrmQrcDto.getRemarksPojo() ) )
                {
                    remarks = inCrmQrcDto.getRemarksPojo().getRemarks();
                }
                else
                {
                    remarks = "Immediate ticket operation";
                }
                CrmQrcDto tmpDto = CRMServiceUtils.generateAutoTicket( crmSrTicketsPojo, inCrmQrcDto.getCustomerId(),
                                                                       action, null, inCrmQrcDto.getUserId(), remarks );
                if ( StringUtils.equals( CRMServiceCode.CRM001.getStatusCode(), tmpDto.getStatusCode() ) )
                {
                    inCrmQrcDto.setSrTicketNo( tmpDto.getCrmSrTicketsPojo().getSrId() );
                    inCrmQrcDto.setCrmSrTicketsPojo( tmpDto.getCrmSrTicketsPojo() );
                }
            }
            catch ( SOAPException ex )
            {
                LOGGER.error( "Unable to generate ticket in Immediate Ticket operation.", ex );
            }
        }
    }

    @Override
    public CrmQrcDto getSelfcareSubjects( CrmQrcDto inCrmQrcDto )
    {
        Session session = null;
        Query query = null;
        try
        {
            List<String> subjects = new ArrayList<String>();
            session = HibernateUtil.getSessionFactory().openSession();
            query = session.getNamedQuery( "SelfcareSubjects" );
            query.setString( "STATUS", CRMStatusCode.ACTIVE.getStatusCode() );
            query.setResultTransformer( Transformers.TO_LIST );
            List<List<String>> data = query.list();
            for ( List<String> list : data )
            {
                subjects.addAll( list );
            }
            inCrmQrcDto.setSelfcareSubjects( subjects );
            inCrmQrcDto.setStatusCode( CRMServiceCode.CRM001.getStatusCode() );
            inCrmQrcDto.setStatusDesc( CRMServiceCode.CRM001.getStatusDesc() );
        }
        catch ( HibernateException ex )
        {
            LOGGER.error( "error getting selfcare subjects", ex );
            inCrmQrcDto.setStatusCode( CRMServiceCode.CRM999.getStatusCode() );
            inCrmQrcDto.setStatusDesc( CRMServiceCode.CRM999.getStatusDesc() );
        }
        finally
        {
            CRMServiceUtils.closeSession( session );
        }
        return inCrmQrcDto;
    }

    @Override
    public CrmQrcDto getSelfcareCategories( CrmQrcDto inCrmQrcDto )
    {
        Session session = null;
        Criteria criteria = null;
        try
        {
            session = HibernateUtil.getSessionFactory().openSession();
            criteria = session.createCriteria( CrmSelfcareCategoriesPojo.class );
            criteria.add( Restrictions.eq( "subject", inCrmQrcDto.getCrmSelfcareCategoriesPojo().getSubject() ) );
            criteria.setCacheable( true );
            criteria.setCacheRegion( CrmSelfcareCategoriesPojo.class.getName() );
            inCrmQrcDto.setCrmSelfcareCategoriesPojos( criteria.list() );
            LOGGER.info( "categories size:: " + inCrmQrcDto.getCrmSelfcareCategoriesPojos().size() );
            inCrmQrcDto.setStatusCode( CRMServiceCode.CRM001.getStatusCode() );
            inCrmQrcDto.setStatusDesc( CRMServiceCode.CRM001.getStatusDesc() );
        }
        catch ( HibernateException ex )
        {
            LOGGER.error( "error getting selfcare categories", ex );
            inCrmQrcDto.setStatusCode( CRMServiceCode.CRM999.getStatusCode() );
            inCrmQrcDto.setStatusDesc( CRMServiceCode.CRM999.getStatusDesc() );
        }
        finally
        {
            CRMServiceUtils.closeSession( session );
        }
        return inCrmQrcDto;
    }

    @Override
    public CrmQrcDto mountBoosterPlan( CrmQrcDto inCrmQrcDto )
    {
        LOGGER.info( "Inside QrcManagerDaoImpl, mountBoosterPlan" );
        CRMServiceCode crmServiceCode = CRMServiceCode.CRM999;
        Session session = null;
        Transaction transaction = null;
        CrmBillingDto crmBillingDto = null;
        Boolean processTicket = false;
        Criteria criteria = null;
        List<CrmCustomerActivityPojo> cuActivityPojosList = null;
        try
        {
            session = HibernateUtil.getCurrentSession();
            transaction = session.beginTransaction();
            crmServiceCode = CRMServiceCode.CRM997;
            if ( CommonValidator.isValidCollection( inCrmQrcDto.getCrmCustAssetDetailsPojos() ) )
            {
                int boosterMounted = 0;
                cuActivityPojosList = new ArrayList<CrmCustomerActivityPojo>();
                for ( CrmCustAssetDetailsPojo assetDetailsPojo : inCrmQrcDto.getCrmCustAssetDetailsPojos() )
                {
                    CrmCustomerActivityPojo boosterActivityLog = null;
                    LOGGER.info( "CrmCustAssetDetailsPojo : " + assetDetailsPojo );
                    boolean isValid = ValidationPojoUtil
                            .validatePojo( assetDetailsPojo,
                                           ICRMValidationCriteriaUtil.CUSTOMER_PLAN_BOOSTER_CREATE_CRITERIA ) == null;
                    if ( isValid )
                    {
                        int sameBoosterMounted = 0;
                        LOGGER.info( "Category Count : " + assetDetailsPojo.getCategoryCount() );
                        for ( int i = 0; i < assetDetailsPojo.getCategoryCount(); i++ )
                        {
                            // Add Plan Billing API
                            crmBillingDto = getCrmBillingManager().addPlan( inCrmQrcDto,
                                                                            assetDetailsPojo.getCategoryValue(), true );
                            if ( StringUtils.equals( crmBillingDto.getStatusCode(),
                                                     CRMServiceCode.CRM001.getStatusCode() ) ) // On success response from billing
                            {
                                criteria = session.createCriteria( CrmPlanMasterPojo.class );
                                criteria.add( Restrictions.eq( "planCode", assetDetailsPojo.getCategoryValue() ) );
                                List<CrmPlanMasterPojo> boosterPlanMaster = criteria.list();
                                if ( CommonValidator.isValidCollection( boosterPlanMaster ) )
                                {
                                    boosterActivityLog = new CrmCustomerActivityPojo();
                                    boosterActivityLog.setOldValue( "NA" );
                                    boosterActivityLog.setNewValue( boosterPlanMaster.get( 0 ).getPlanName() );
                                    boosterActivityLog.setCustomerId( inCrmQrcDto.getCustomerDetailsPojo()
                                            .getCustomerId() );
                                    boosterActivityLog.setAction( CRMCustomerActivityActions.BOOSTER_PLAN
                                            .getActionDesc() );
                                    boosterActivityLog.setCreatedBy( inCrmQrcDto.getUserId() );
                                    boosterActivityLog.setClientIp( inCrmQrcDto.getClientIPAddress() );
                                    boosterActivityLog.setServerIp( inCrmQrcDto.getServerIPAddress() );
                                    boosterActivityLog.setServiceIp( inCrmQrcDto.getServerIPAddress() );
                                    cuActivityPojosList.add( boosterActivityLog );
                                }
                                sameBoosterMounted++;
                            }
                            else
                            {
                                inCrmQrcDto.setBillingErrorCode( crmBillingDto.getBillingErrorCode() );
                            }
                        }
                        if ( sameBoosterMounted > 0 )
                        {
                            LOGGER.info( "Same Booster Mounted : " + sameBoosterMounted );
                            assetDetailsPojo.setCategoryCount( (byte) sameBoosterMounted );
                            assetDetailsPojo.setCreatedTime( Calendar.getInstance().getTime() );
                            Long assetID = (Long) session.save( assetDetailsPojo );
                            LOGGER.info( "Customer Plan Booster Mounted, Asset ID : " + assetID );
                        }
                        boosterMounted += sameBoosterMounted;
                    }
                }
                if ( boosterMounted > 0 )
                {
                    LOGGER.info( "Booster Mounted : " + boosterMounted );
                    crmServiceCode = CRMServiceCode.CRM001;
                    processTicket = true;
                }
                else
                {
                    crmServiceCode = CRMServiceCode.CRM523;
                }
                if ( processTicket )
                {
                    inCrmQrcDto.setCustomerId( inCrmQrcDto.getCustomerDetailsPojo().getCustomerId() );
                    immediateTicketOperation( inCrmQrcDto );
                }
                if ( crmServiceCode == CRMServiceCode.CRM001 )
                {
                    for ( CrmCustomerActivityPojo crmCustomerActivityPojo : cuActivityPojosList )
                    {
                        crmCustomerActivityPojo.setTicketId( inCrmQrcDto.getCrmSrTicketsPojo().getSrId() );
                        if ( CRMServiceUtils.createActivityLog( crmCustomerActivityPojo ) > 0 )
                        {
                            LOGGER.info( "Customer Activity successfully created" );
                        }
                    }
                }
            }
            transaction.commit();
        }
        catch ( HibernateException ex )
        {
            LOGGER.error( "HibernateException while mounting booster plan", ex );
            crmServiceCode = CRMServiceCode.CRM999;
            CRMServiceUtils.rollback( transaction );
            CRMServiceUtils.closeSessionOnException( session );
        }
        catch ( Exception ex )
        {
            crmServiceCode = CRMServiceCode.CRM999;
            LOGGER.error( "Exception while mounting booster plan ", ex );
        }
        finally
        {
            CRMServiceUtils.rollback( transaction );
            CRMServiceUtils.closeSession( session );
            inCrmQrcDto.setStatusCode( crmServiceCode.getStatusCode() );
            inCrmQrcDto.setStatusDesc( crmServiceCode.getStatusDesc() );
            if ( crmServiceCode == CRMServiceCode.CRM001 )
            {
                HibernateUtil.evictAll( CrmCustAssetDetailsPojo.class.getName(), null, null );
            }
        }
        return inCrmQrcDto;
    }

    @Override
    public CrmQrcDto bulkMountBoosterPlan( CrmQrcDto inCrmQrcDto )
    {
        LOGGER.info( "Inside QrcManagerDaoImpl, bulkMountBoosterPlan" );
        CRMServiceCode crmServiceCode = CRMServiceCode.CRM999;
        Session session = null;
        Transaction transaction = null;
        CrmBillingDto crmBillingDto = null;
        CrmCustomerDetailsPojo customerDetailsPojo = null;
        CrmPlanMasterPojo crmPlanMasterPojo = null;
        String customerId = "";
        Map<String, String> failedCustomerMapList = new HashMap<String, String>();
        List<CrmCustomerActivityPojo> cuActivityPojosList = null;
        try
        {
            session = HibernateUtil.getCurrentSession();
            transaction = session.beginTransaction();
            crmServiceCode = CRMServiceCode.CRM997;
            if ( CommonValidator.isValidCollection( inCrmQrcDto.getCrmCustAssetDetailsPojos() ) )
            {
                cuActivityPojosList = new ArrayList<CrmCustomerActivityPojo>();
                for ( CrmCustAssetDetailsPojo assetDetailsPojo : inCrmQrcDto.getCrmCustAssetDetailsPojos() )
                {
                    customerId = assetDetailsPojo.getCategoryName();
                    CrmCustomerActivityPojo boosterActivityLog = null;
                    LOGGER.info( "CrmCustAssetDetailsPojo : " + assetDetailsPojo );
                    boolean isValid = ValidationPojoUtil
                            .validatePojo( assetDetailsPojo,
                                           ICRMValidationCriteriaUtil.CUSTOMER_BULK_PLAN_BOOSTER_CREATE_CRITERIA ) == null;
                    if ( isValid )
                    {
                        int sameBoosterMounted = 0;
                        customerDetailsPojo = CRMServiceUtils.getDBValues( CrmCustomerDetailsPojo.class, "customerId",
                                                                           assetDetailsPojo.getCategoryName() );
                        LOGGER.info( "Category Count : " + assetDetailsPojo.getCategoryCount() );
                        if ( StringUtils.isValidObj( customerDetailsPojo ) )
                        {
                            inCrmQrcDto.setCustomerDetailsPojo( customerDetailsPojo );
                            for ( int i = 0; i < assetDetailsPojo.getCategoryCount(); i++ )
                            {
                                // Add Plan Billing API
                                crmBillingDto = getCrmBillingManager().addPlan( inCrmQrcDto,
                                                                                assetDetailsPojo.getCategoryValue(),
                                                                                true );
                                if ( StringUtils.equals( crmBillingDto.getStatusCode(),
                                                         CRMServiceCode.CRM001.getStatusCode() ) ) // On success response from billing
                                {
                                    crmPlanMasterPojo = CRMServiceUtils.getDBValues( CrmPlanMasterPojo.class,
                                                                                     "planCode", assetDetailsPojo
                                                                                             .getCategoryValue() );
                                    if ( StringUtils.isValidObj( crmPlanMasterPojo ) )
                                    {
                                        boosterActivityLog = new CrmCustomerActivityPojo();
                                        boosterActivityLog.setOldValue( "NA" );
                                        boosterActivityLog.setNewValue( crmPlanMasterPojo.getPlanName() );
                                        boosterActivityLog.setCustomerId( assetDetailsPojo.getCategoryName() );
                                        boosterActivityLog.setAction( CRMCustomerActivityActions.BOOSTER_PLAN
                                                .getActionDesc() );
                                        boosterActivityLog.setCreatedBy( inCrmQrcDto.getUserId() );
                                        boosterActivityLog.setClientIp( inCrmQrcDto.getClientIPAddress() );
                                        boosterActivityLog.setServerIp( inCrmQrcDto.getServerIPAddress() );
                                        boosterActivityLog.setServiceIp( inCrmQrcDto.getServerIPAddress() );
                                        cuActivityPojosList.add( boosterActivityLog );
                                    }
                                    sameBoosterMounted++;
                                }
                                else
                                {
                                    LOGGER.info( "Billing Error Code ::" + crmBillingDto.getBillingErrorCode() );
                                    inCrmQrcDto.setBillingErrorCode( crmBillingDto.getBillingErrorCode() );
                                    crmServiceCode = CRMServiceCode.CRM523;
                                    String messsage = crmServiceCode.getStatusDesc();
                                    messsage += crmBillingDto.getBillingErrorCode();
                                    LOGGER.info( "Error Code ::" + messsage );
                                    failedCustomerMapList.put( customerId, messsage );
                                    inCrmQrcDto.setFailedCustomerMapList( failedCustomerMapList );
                                }
                            }
                            if ( sameBoosterMounted > 0 )
                            {
                                String categoryName = "";
                                LOGGER.info( "Same Booster Mounted : " + sameBoosterMounted );
                                assetDetailsPojo.setCategoryCount( (byte) sameBoosterMounted );
                                assetDetailsPojo.setCreatedTime( Calendar.getInstance().getTime() );
                                assetDetailsPojo.setCreatedBy( inCrmQrcDto.getUserId() );
                                assetDetailsPojo.setStatus( CRMStatusCode.ACTIVE.getStatusCode() );
                                assetDetailsPojo.setCustomerRecordId( customerDetailsPojo.getRecordId() );
                                assetDetailsPojo.setCategoryAmount( crmPlanMasterPojo.getRentInclTax() );
                                if ( crmPlanMasterPojo.getRentInclTax() > 0 )
                                {
                                    categoryName = crmPlanMasterPojo.getPlanType() + " "
                                            + crmPlanMasterPojo.getPlanCategory() + " " + "PAID";
                                }
                                else
                                {
                                    categoryName = crmPlanMasterPojo.getPlanType() + " "
                                            + crmPlanMasterPojo.getPlanCategory() + " " + "FOC";
                                }
                                assetDetailsPojo.setCategoryName( categoryName );
                                Long assetID = (Long) session.save( assetDetailsPojo );
                                LOGGER.info( "Customer Plan Booster Mounted, Asset ID : " + assetID );
                                if ( assetID > 0 )
                                {
                                    inCrmQrcDto.setCustomerId( customerId );
                                    inCrmQrcDto.setActivityAction( CRMCustomerActivityActions.BOOSTER_PLAN
                                            .getActionDesc() );
                                    //Ticket Creation 
                                    immediateTicketOperation( inCrmQrcDto );
                                    //Save Remarks 
                                    inCrmQrcDto.getRemarksPojo().setMappingId( customerId );
                                    inCrmQrcDto.getRemarksPojo().setActions( CRMCustomerActivityActions.BOOSTER_PLAN
                                                                                     .getActionDesc() );
                                    inCrmQrcDto.getRemarksPojo().setCreatedBy( inCrmQrcDto.getUserId() );
                                    inCrmQrcDto.getRemarksPojo().setActivityId( 0 );
                                    inCrmQrcDto.getRemarksPojo().setReasonId( 0 );
                                    inCrmQrcDto.getRemarksPojo().setMappingType( CRMRCAReason.QRC.getStatusCode() );
                                    inCrmQrcDto.getRemarksPojo().setCreatedTime( Calendar.getInstance().getTime() );
                                    session.save( inCrmQrcDto.getRemarksPojo() );
                                    crmServiceCode = CRMServiceCode.CRM001;
                                }
                                else
                                {
                                    crmServiceCode = CRMServiceCode.CRM523;
                                    String messsage = crmServiceCode.getStatusDesc();
                                    if ( StringUtils.isNotBlank( inCrmQrcDto.getBillingErrorCode() ) )
                                    {
                                        messsage += inCrmQrcDto.getBillingErrorCode();
                                    }
                                    failedCustomerMapList.put( customerId, messsage );
                                    inCrmQrcDto.setFailedCustomerMapList( failedCustomerMapList );
                                }
                            }
                        }
                    }
                }
                if ( crmServiceCode == CRMServiceCode.CRM001 )
                {
                    for ( CrmCustomerActivityPojo crmCustomerActivityPojo : cuActivityPojosList )
                    {
                        crmCustomerActivityPojo.setTicketId( inCrmQrcDto.getCrmSrTicketsPojo().getSrId() );
                        if ( CRMServiceUtils.createActivityLog( crmCustomerActivityPojo ) > 0 )
                        {
                            LOGGER.info( "Customer Activity successfully created" );
                        }
                    }
                }
            }
            transaction.commit();
        }
        catch ( HibernateException ex )
        {
            LOGGER.error( "HibernateException while bulk mounting booster plan", ex );
            crmServiceCode = CRMServiceCode.CRM999;
            CRMServiceUtils.rollback( transaction );
            CRMServiceUtils.closeSessionOnException( session );
        }
        catch ( Exception ex )
        {
            crmServiceCode = CRMServiceCode.CRM999;
            LOGGER.error( "Exception while bulk mounting booster plan ", ex );
        }
        finally
        {
            CRMServiceUtils.rollback( transaction );
            CRMServiceUtils.closeSession( session );
            inCrmQrcDto.setStatusCode( crmServiceCode.getStatusCode() );
            inCrmQrcDto.setStatusDesc( crmServiceCode.getStatusDesc() );
            if ( crmServiceCode == CRMServiceCode.CRM001 )
            {
                HibernateUtil.evictAll( CrmCustAssetDetailsPojo.class.getName(), null, null );
            }
        }
        return inCrmQrcDto;
    }

    @Override
    public CrmQrcDto fetchActivatedVAS( CrmQrcDto inCrmQrcDto )
    {
        LOGGER.info( "Inside QrcManagerDaoImpl : fetchActivatedVAS" );
        CRMServiceCode crmServiceCode = CRMServiceCode.CRM999;
        List<CrmCustAssetDetailsPojo> activatedVASList = null;
        SessionFactory sessionFactory = null;
        Session session = null;
        Criteria criteria = null;
        if ( StringUtils.isValidObj( inCrmQrcDto.getCrmCustAssetDetailsPojo() ) )
        {
            try
            {
                sessionFactory = HibernateUtil.getSessionFactory();
                session = sessionFactory.openSession();
                criteria = session.createCriteria( CrmCustAssetDetailsPojo.class );
                if ( inCrmQrcDto.getCrmCustAssetDetailsPojo().getCustomerRecordId() > 0 )
                {
                    criteria.add( Restrictions.eq( "customerRecordId", inCrmQrcDto.getCrmCustAssetDetailsPojo()
                            .getCustomerRecordId() ) );
                }
                if ( StringUtils.isNotBlank( inCrmQrcDto.getCrmCustAssetDetailsPojo().getCategoryName() ) )
                {
                    criteria.add( Restrictions.eq( "categoryName", inCrmQrcDto.getCrmCustAssetDetailsPojo()
                            .getCategoryName() ) );
                }
                if ( StringUtils.isNotBlank( inCrmQrcDto.getCrmCustAssetDetailsPojo().getStatus() ) )
                {
                    criteria.add( Restrictions.eq( "status", inCrmQrcDto.getCrmCustAssetDetailsPojo().getStatus() ) );
                }
                criteria.setCacheable( true );
                criteria.setCacheRegion( CrmCustAssetDetailsPojo.class.getName() );
                activatedVASList = criteria.list();
                if ( CommonValidator.isValidCollection( activatedVASList ) )
                {
                    inCrmQrcDto.setCrmCustAssetDetailsPojos( activatedVASList );
                    crmServiceCode = CRMServiceCode.CRM001;
                }
            }
            catch ( Exception ex )
            {
                LOGGER.error( "Exception while retreiving Activated VAS for Customer ", ex );
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
    public CrmQrcDto activateDeactivateVAS( CrmQrcDto inCrmQrcDto )
    {
        LOGGER.info( "Inside QrcManagerDaoImpl, activateDeactivateVAS" );
        CRMServiceCode crmServiceCode = CRMServiceCode.CRM999;
        Session session = null;
        Transaction transaction = null;
        Boolean processTicket = false;
        String actionDesc = null;
        Long assetID = null;
        Criteria criteria = null;
        boolean activation = false;
        List<CrmCustomerActivityPojo> crActivityPojos = null;
        try
        {
            MDC.put( "KEY", "CUSTID:" + inCrmQrcDto.getCustomerId() + ", USERID:" + inCrmQrcDto.getUserId() );
            session = HibernateUtil.getCurrentSession();
            transaction = session.beginTransaction();
            crmServiceCode = CRMServiceCode.CRM997;
            if ( CommonValidator.isValidCollection( inCrmQrcDto.getCrmCustAssetDetailsPojos() ) )
            {
                crActivityPojos = new ArrayList<CrmCustomerActivityPojo>();
                if ( StringUtils.equals( inCrmQrcDto.getCrmCustAssetDetailsPojos().get( 0 ).getCategoryName(),
                                         CRMParameter.VAS_DEACTIVATION_FOC.getParameter() )
                        || StringUtils.equals( inCrmQrcDto.getCrmCustAssetDetailsPojos().get( 0 ).getCategoryName(),
                                               CRMParameter.VAS_DEACTIVATION_PAID.getParameter() ) )
                {
                    actionDesc = CRMCustomerActivityActions.SMART_BROADBAND_DEACTIVATION.getActionDesc();
                    inCrmQrcDto.setActivityAction( actionDesc );
                }
                else if ( StringUtils.equals( inCrmQrcDto.getCrmCustAssetDetailsPojos().get( 0 ).getCategoryName(),
                                              CRMParameter.VAS_ACTIVATION_PAID.getParameter() )
                        || StringUtils.equals( inCrmQrcDto.getCrmCustAssetDetailsPojos().get( 0 ).getCategoryName(),
                                               CRMParameter.VAS_ACTIVATION_FOC.getParameter() ) )
                {
                    activation = true;
                    actionDesc = CRMCustomerActivityActions.SMART_BROADBAND_ACTIVATION.getActionDesc();
                    inCrmQrcDto.setActivityAction( actionDesc );
                }
                LOGGER.info( "Resolved Action " + actionDesc );
                int requestProcessed = 0;
                CrmBillingDto crmBillingDto = null;
                for ( CrmCustAssetDetailsPojo assetDetailsPojo : inCrmQrcDto.getCrmCustAssetDetailsPojos() )
                {
                    CrmCustomerActivityPojo vasCustomerActivityPojo = null;
                    boolean isValid = ValidationPojoUtil
                            .validatePojo( assetDetailsPojo,
                                           ICRMValidationCriteriaUtil.CUSTOMER_PLAN_BOOSTER_CREATE_CRITERIA ) == null;
                    if ( isValid )
                    {
                        // Billing calls to Activate / Deactivate VAS
                        if ( StringUtils.equals( assetDetailsPojo.getStatus(), CRMStatusCode.ACTIVE.getStatusCode() ) )
                        {
                            crmBillingDto = getCrmBillingManager().addPlan( inCrmQrcDto,
                                                                            assetDetailsPojo.getCategoryValue(), true );
                        }
                        else
                        {
                            crmBillingDto = new CrmBillingDto();
                            crmBillingDto.setCustomerId( inCrmQrcDto.getCustomerDetailsPojo().getCustomerId() );
                            crmBillingDto.setParamValue( assetDetailsPojo.getCategoryValue() );
                            crmBillingDto.setBoolValue( true );
                            getCrmBillingManager().cancelAdditionalPlan( crmBillingDto );
                        }
                        if ( StringUtils.equals( crmBillingDto.getStatusCode(), CRMServiceCode.CRM001.getStatusCode() ) )
                        {
                            requestProcessed++;
                            if ( assetDetailsPojo.getAssetDetailsId() > 0 )
                            {
                                assetDetailsPojo.setLastModifiedTime( Calendar.getInstance().getTime() );
                                session.update( assetDetailsPojo );
                                assetID = assetDetailsPojo.getAssetDetailsId();
                            }
                            else
                            {
                                assetDetailsPojo.setCreatedTime( Calendar.getInstance().getTime() );
                                session.save( assetDetailsPojo );
                            }
                            criteria = session.createCriteria( CrmPlanMasterPojo.class );
                            criteria.add( Restrictions.eq( "planCode", assetDetailsPojo.getCategoryValue() ) );
                            List<CrmPlanMasterPojo> vasPlanMaster = criteria.list();
                            if ( CommonValidator.isValidCollection( vasPlanMaster ) )
                            {
                                vasCustomerActivityPojo = new CrmCustomerActivityPojo();
                                if ( activation )
                                {
                                    vasCustomerActivityPojo.setOldValue( IAppConstants.NOTAPPLICABLE );
                                    vasCustomerActivityPojo.setNewValue( vasPlanMaster.get( 0 ).getPlanName() );
                                }
                                else
                                {
                                    vasCustomerActivityPojo.setOldValue( vasPlanMaster.get( 0 ).getPlanName() );
                                    vasCustomerActivityPojo.setNewValue( IAppConstants.NOTAPPLICABLE );
                                }
                                vasCustomerActivityPojo.setCustomerId( inCrmQrcDto.getCustomerDetailsPojo()
                                        .getCustomerId() );
                                vasCustomerActivityPojo.setAction( actionDesc );
                                vasCustomerActivityPojo.setCreatedBy( inCrmQrcDto.getUserId() );
                                vasCustomerActivityPojo.setClientIp( inCrmQrcDto.getClientIPAddress() );
                                vasCustomerActivityPojo.setServerIp( inCrmQrcDto.getServerIPAddress() );
                                vasCustomerActivityPojo.setServiceIp( inCrmQrcDto.getServerIPAddress() );
                                crActivityPojos.add( vasCustomerActivityPojo );
                            }
                        }
                        else
                        {
                            inCrmQrcDto.setBillingErrorCode( crmBillingDto.getBillingErrorCode() );
                        }
                    }
                }
                if ( requestProcessed > 0 )
                {
                    crmServiceCode = CRMServiceCode.CRM001;
                    processTicket = true;
                }
                else
                {
                    crmServiceCode = CRMServiceCode.CRM516;
                }
                if ( processTicket )
                {
                    inCrmQrcDto.setCustomerId( inCrmQrcDto.getCustomerDetailsPojo().getCustomerId() );
                    immediateTicketOperation( inCrmQrcDto );
                }
                if ( crmServiceCode == CRMServiceCode.CRM001 )
                {
                    for ( CrmCustomerActivityPojo crmCustomerActivityPojo : crActivityPojos )
                    {
                        crmCustomerActivityPojo.setTicketId( inCrmQrcDto.getCrmSrTicketsPojo().getSrId() );
                        if ( CRMServiceUtils.createActivityLog( crmCustomerActivityPojo ) > 0 )
                        {
                            LOGGER.info( "Customer Activity successfully created" );
                        }
                    }
                    if ( StringUtils.isValidObj( inCrmQrcDto.getRemarksPojo() ) )
                    {
                        inCrmQrcDto.getRemarksPojo()
                                .setMappingId( inCrmQrcDto.getCustomerDetailsPojo().getCustomerId() );
                        inCrmQrcDto.getRemarksPojo().setActions( inCrmQrcDto.getActivityAction() );
                        inCrmQrcDto.getRemarksPojo().setCreatedBy( inCrmQrcDto.getUserId() );
                        inCrmQrcDto.getRemarksPojo().setCreatedTime( Calendar.getInstance().getTime() );
                        inCrmQrcDto.getRemarksPojo().setMappingType( CRMRCAReason.QRC.getStatusCode() );
                        inCrmQrcDto.getRemarksPojo().setReasonId( inCrmQrcDto.getRemarksPojo().getReasonId() );
                        session.save( inCrmQrcDto.getRemarksPojo() );
                    }
                }
            }
            transaction.commit();
        }
        catch ( HibernateException ex )
        {
            LOGGER.error( "HibernateException while activating / deactivating VAS plan ", ex );
            crmServiceCode = CRMServiceCode.CRM999;
            CRMServiceUtils.rollback( transaction );
            CRMServiceUtils.closeSessionOnException( session );
        }
        catch ( Exception ex )
        {
            crmServiceCode = CRMServiceCode.CRM999;
            LOGGER.error( "Exception while activating / deactivating VAS plan ", ex );
        }
        finally
        {
            CRMServiceUtils.rollback( transaction );
            CRMServiceUtils.closeSession( session );
            inCrmQrcDto.setStatusCode( crmServiceCode.getStatusCode() );
            inCrmQrcDto.setStatusDesc( crmServiceCode.getStatusDesc() );
            if ( crmServiceCode == CRMServiceCode.CRM001 )
            {
                HibernateUtil.evictAll( CrmCustAssetDetailsPojo.class.getName(), null, assetID );
            }
            MDC.remove( "KEY" );
        }
        return inCrmQrcDto;
    }

    @Override
    public CrmQrcDto customerPlanReactivation( CrmQrcDto inCrmQrcDto )
    {
        LOGGER.info( "Inside QrcManagerDaoImpl : customerPlanReactivation" );
        CRMServiceCode crmServiceCode = CRMServiceCode.CRM999;
        Session session = null;
        Transaction transaction = null;
        CrmCustomerActivityPojo activityLog = null;
        Map<String, Long> evicts = new HashMap<String, Long>();
        if ( StringUtils.isValidObj( inCrmQrcDto.getCustomerId() ) )
        {
            try
            {
                session = HibernateUtil.getCurrentSession();
                transaction = session.beginTransaction();
                activityLog = new CrmCustomerActivityPojo();
                CrmCustomerDetailsPojo crmCustomerDetailsPojo = (CrmCustomerDetailsPojo) session
                        .get( CrmCustomerDetailsPojo.class, inCrmQrcDto.getCustomerDetailsPojo().getRecordId() );
                if ( StringUtils.isValidObj( crmCustomerDetailsPojo ) )
                {
                    String TicketID = inCrmQrcDto.getSrTicketNo();
                    if ( StringUtils.isNotBlank( TicketID ) )
                    {
                        LOGGER.info( "Ticket ID : " + TicketID );
                        if ( !isValidTicketNo( TicketID, crmCustomerDetailsPojo.getCustomerId() ) )
                        {
                            crmServiceCode = CRMServiceCode.CRM402;
                        }
                    }
                    LOGGER.info( "Current Status : " + crmServiceCode );
                    if ( !StringUtils.equals( crmServiceCode.getStatusCode(), CRMServiceCode.CRM402.getStatusCode() ) )
                    {
                        CrmPlanDetailsPojo crmPlanDetailsPojoDB = (CrmPlanDetailsPojo) session
                                .get( CrmPlanDetailsPojo.class, inCrmQrcDto.getCrmPlanDetailsPojo().getRecordId() );
                        crmPlanDetailsPojoDB.setBasePlanCode( inCrmQrcDto.getPlanMasterPojo().getPlanCode() );
                        if ( StringUtils.equals( inCrmQrcDto.getPlanMasterPojo().getAddonAllowedYn(),
                                                 IAppConstants.NO_CHAR )
                                && StringUtils.isNotEmpty( inCrmQrcDto.getCrmPlanDetailsPojo().getAddOnPlanCode() ) )
                        {
                            crmPlanDetailsPojoDB.setAddOnPlanCode( null );
                        }
                        CrmPlanMasterPojo basePlanMasterPojo = CRMServiceUtils
                                .getDBValues( CrmPlanMasterPojo.class, "planCode", inCrmQrcDto.getCrmPlanDetailsPojo()
                                        .getBasePlanCode() );
                        if ( StringUtils.isValidObj( basePlanMasterPojo ) )
                        {
                            activityLog.setOldValue( basePlanMasterPojo.getPlanName() );
                        }
                        else
                        {
                            activityLog.setOldValue( IAppConstants.DASH );
                        }
                        activityLog.setNewValue( inCrmQrcDto.getPlanMasterPojo().getPlanName() );
                        crmPlanDetailsPojoDB.setLastModifiedBy( inCrmQrcDto.getUserId() );
                        crmPlanDetailsPojoDB.setLastModifiedTime( Calendar.getInstance().getTime() );
                        inCrmQrcDto.setCrmPlanDetailsPojo( crmPlanDetailsPojoDB );
                        //ChangeOffer Billing API
                        getCrmBillingManager().instantOfferRenew( inCrmQrcDto );
                        if ( StringUtils.equals( inCrmQrcDto.getStatusCode(), CRMServiceCode.CRM001.getStatusCode() ) )
                        {
                            session.update( crmPlanDetailsPojoDB );
                            evicts.put( CrmPlanDetailsPojo.class.getName(), null );
                            activityLog.setTicketId( TicketID );
                            long activityId = saveCustomerActivity( activityLog, inCrmQrcDto,
                                                                    CRMCustomerActivityActions.PLAN_REACTIVATION_BASE );
                            if ( StringUtils.isValidObj( inCrmQrcDto.getRemarksPojo() ) )
                            {
                                inCrmQrcDto.getRemarksPojo().setMappingId( inCrmQrcDto.getCustomerId() );
                                inCrmQrcDto.getRemarksPojo().setActions( inCrmQrcDto.getActivityAction() );
                                inCrmQrcDto.getRemarksPojo().setCreatedBy( inCrmQrcDto.getUserId() );
                                inCrmQrcDto.getRemarksPojo().setCreatedTime( Calendar.getInstance().getTime() );
                                inCrmQrcDto.getRemarksPojo().setActivityId( activityId );
                                inCrmQrcDto.getRemarksPojo().setReasonId( inCrmQrcDto.getRemarksPojo().getReasonId() );
                                inCrmQrcDto.getRemarksPojo().setMappingType( CRMRCAReason.QRC.getStatusCode() );
                                session.save( inCrmQrcDto.getRemarksPojo() );
                                evicts.put( RemarksPojo.class.getName(), null );
                            }
                            transaction.commit();
                        }
                        else
                        {
                            crmServiceCode = CRMServiceCode.valueOf( inCrmQrcDto.getStatusCode() );
                        }
                    }
                }
            }
            catch ( HibernateException ex )
            {
                LOGGER.error( "HibernateException while saving customer plan reactivation ", ex );
                CRMServiceUtils.rollback( transaction );
                CRMServiceUtils.closeSessionOnException( session );
            }
            catch ( Exception ex )
            {
                LOGGER.error( "Exception while saving customer plan reactivation ", ex );
            }
            finally
            {
                CRMServiceUtils.closeSession( session );
                if ( StringUtils.isEmpty( inCrmQrcDto.getStatusCode() ) )
                {
                    inCrmQrcDto.setStatusCode( crmServiceCode.getStatusCode() );
                    inCrmQrcDto.setStatusDesc( crmServiceCode.getStatusDesc() );
                }
                if ( crmServiceCode == CRMServiceCode.CRM001 )
                {
                    HibernateUtil.evictAll( evicts );
                }
            }
            LOGGER.info( "Status Code::" + inCrmQrcDto.getStatusCode() + " DESC:: " + inCrmQrcDto.getStatusDesc()
                    + "Billing  Code::" + inCrmQrcDto.getBillingErrorCode() );
        }
        return inCrmQrcDto;
    }

    @Override
    public CrmQrcDto getBillingDetails( CrmQrcDto inCrmQrcDto )
    {
        LOGGER.info( "Inside QrcManagerDaoImpl : getBillingDetails" );
        CRMServiceCode crmServiceCode = null;
        try
        {
            MDC.put( "KEY", "CUSTID:" + inCrmQrcDto.getCustomerId() + ", USERID:" + inCrmQrcDto.getUserId() );
            if ( StringUtils.isNotBlank( inCrmQrcDto.getCustomerId() )
                    && StringUtils.equals( CRMDisplayListConstants.POST_PAID.getCode(), inCrmQrcDto.getServiceType() ) )
            {
                getCrmBillingManager().getBillingDetails( inCrmQrcDto );
            }
            else if ( StringUtils.isNotBlank( inCrmQrcDto.getCustomerId() )
                    && StringUtils.equals( CRMDisplayListConstants.PRE_PAID.getCode(), inCrmQrcDto.getServiceType() ) )
            {
                getInvoiceDetails( inCrmQrcDto );
            }
            else
            {
                crmServiceCode = CRMServiceCode.CRM997;
            }
        }
        catch ( Exception ex )
        {
            crmServiceCode = CRMServiceCode.CRM999;
            LOGGER.error( "Exception while retreiving Customer Waiver List ", ex );
        }
        finally
        {
            if ( StringUtils.isValidObj( crmServiceCode ) )
            {
                inCrmQrcDto.setStatusCode( crmServiceCode.getStatusCode() );
                inCrmQrcDto.setStatusDesc( crmServiceCode.getStatusDesc() );
                MDC.remove( "KEY" );
            }
        }
        return inCrmQrcDto;
    }

    private void getInvoiceDetails( CrmQrcDto inCrmQrcDto )
    {
        LOGGER.info( "Inside QrcManagerDaoImpl : getInvoiceDetails" );
        Session session = null;
        Criteria criteria = null;
        try
        {
            session = HibernateUtil.getCurrentSession();
            criteria = session.createCriteria( CrmInvoiceDetailsPojo.class );
            criteria.add( Restrictions.eq( "customerId", inCrmQrcDto.getCustomerId() ) );
            List<CrmInvoiceDetailsPojo> invoiceDetailsPojos = criteria.list();
            inCrmQrcDto.setInvoiceDetailsPojos( invoiceDetailsPojos );
            inCrmQrcDto.setStatusCode( CRMServiceCode.CRM001.getStatusCode() );
            inCrmQrcDto.setStatusDesc( CRMServiceCode.CRM001.getStatusDesc() );
        }
        catch ( Exception ex )
        {
            inCrmQrcDto.setStatusCode( CRMServiceCode.CRM999.getStatusCode() );
            inCrmQrcDto.setStatusDesc( CRMServiceCode.CRM999.getStatusDesc() );
            LOGGER.error( "Exception while getInvoiceDetails ", ex );
        }
        finally
        {
            CRMServiceUtils.closeSession( session );
        }
    }

    @Override
    public CrmQrcDto getPOPlanMigrationPolicy( CrmQrcDto inCrmQrcDto )
    {
        LOGGER.info( "Inside QrcManagerDaoImpl : getPOPlanMigrationPolicy" );
        CRMServiceCode crmServiceCode = CRMServiceCode.CRM999;
        Session session = null;
        Criteria criteria = null;
        CrmPlanMasterPojo oldBasePlanPOJO = null;
        CrmPlanMasterPojo newBasePlanPOJO = null;
        CrmPlanMasterPojo oldAddonPlanPOJO = null;
        CrmPlanMasterPojo newAddonPlanPOJO = null;
        try
        {
            session = HibernateUtil.getCurrentSession();
            criteria = session.createCriteria( CrmPlanMasterPojo.class );
            if ( StringUtils.isNotEmpty( inCrmQrcDto.getOldBasePlanCode() ) )
            {
                criteria = session.createCriteria( CrmPlanMasterPojo.class );
                criteria.add( Restrictions.eq( "planCode", inCrmQrcDto.getOldBasePlanCode() ) );
                oldBasePlanPOJO = CommonValidator.isValidCollection( criteria.list() ) ? (CrmPlanMasterPojo) criteria
                        .list().get( 0 ) : null;
            }
            if ( StringUtils.isNotEmpty( inCrmQrcDto.getNewBasePlanCode() ) )
            {
                criteria = session.createCriteria( CrmPlanMasterPojo.class );
                criteria.add( Restrictions.eq( "planCode", inCrmQrcDto.getNewBasePlanCode() ) );
                newBasePlanPOJO = (CrmPlanMasterPojo) criteria.list().get( 0 );
            }
            if ( StringUtils.isNotEmpty( inCrmQrcDto.getOldAddonPlanCode() ) )
            {
                criteria = session.createCriteria( CrmPlanMasterPojo.class );
                criteria.add( Restrictions.eq( "planCode", inCrmQrcDto.getOldAddonPlanCode() ) );
                criteria.setCacheable( true );
                criteria.setCacheRegion( CrmPlanMasterPojo.class.getName() );
                oldAddonPlanPOJO = CommonValidator.isValidCollection( criteria.list() ) ? (CrmPlanMasterPojo) criteria
                        .list().get( 0 ) : null;
            }
            if ( StringUtils.isNotEmpty( inCrmQrcDto.getNewAddonPlanCode() ) )
            {
                LOGGER.info( "new addon plan code :: " + inCrmQrcDto.getNewAddonPlanCode() );
                criteria = session.createCriteria( CrmPlanMasterPojo.class );
                criteria.add( Restrictions.eq( "planCode", inCrmQrcDto.getNewAddonPlanCode() ) );
                criteria.setCacheable( true );
                criteria.setCacheRegion( CrmPlanMasterPojo.class.getName() );
                newAddonPlanPOJO = (CrmPlanMasterPojo) criteria.list().get( 0 );
            }
            if ( StringUtils.isValidObj( oldBasePlanPOJO ) && StringUtils.isValidObj( newBasePlanPOJO )
                    && StringUtils.isValidObj( inCrmQrcDto.getCustAdditionalDetails() ) )
            {
                LOGGER.info( "Old plan usage type:: " + oldBasePlanPOJO.getPlanUsageType() + " New plan usage type:: "
                        + newBasePlanPOJO.getPlanUsageType() );
                //Calling Unlimited to Unlimited
                double actualUsage = inCrmQrcDto.getCustAdditionalDetails().getUsedVolumeQuota();
                LOGGER.info( "Actual Usage:: " + actualUsage );
                if ( StringUtils.equals( oldBasePlanPOJO.getPlanUsageType(), "U" )
                        && StringUtils.equals( newBasePlanPOJO.getPlanUsageType(), "U" ) )
                {
                    List<ContentPojo> policyAdviceList = getPOU2UPlanChangePolicyAdvice( oldBasePlanPOJO,
                                                                                         newBasePlanPOJO,
                                                                                         oldAddonPlanPOJO,
                                                                                         newAddonPlanPOJO, actualUsage,
                                                                                         inCrmQrcDto.isWebRequest() );
                    inCrmQrcDto.setContentPojos( policyAdviceList );
                }
                //Calling Unlimited to Limited
                else if ( StringUtils.equals( oldBasePlanPOJO.getPlanUsageType(), "U" )
                        && StringUtils.equals( newBasePlanPOJO.getPlanUsageType(), "L" ) )
                {
                    List<ContentPojo> policyAdviceList = getPOU2LPlanChangePolicyAdvice( oldBasePlanPOJO,
                                                                                         newBasePlanPOJO, actualUsage );
                    inCrmQrcDto.setContentPojos( policyAdviceList );
                }
                //Calling Limited to Limited
                else if ( StringUtils.equals( oldBasePlanPOJO.getPlanUsageType(), "L" )
                        && StringUtils.equals( newBasePlanPOJO.getPlanUsageType(), "L" ) )
                {
                    List<ContentPojo> policyAdviceList = getPOL2LPlanChangePolicyAdvice( oldBasePlanPOJO,
                                                                                         newBasePlanPOJO, actualUsage );
                    inCrmQrcDto.setContentPojos( policyAdviceList );
                }
                //Calling Limited to Unlimited
                else if ( StringUtils.equals( oldBasePlanPOJO.getPlanUsageType(), "L" )
                        && StringUtils.equals( newBasePlanPOJO.getPlanUsageType(), "U" ) )
                {
                    List<ContentPojo> policyAdviceList = getPOL2UPlanChangePolicyAdvice( oldBasePlanPOJO,
                                                                                         newBasePlanPOJO,
                                                                                         newAddonPlanPOJO, actualUsage,
                                                                                         inCrmQrcDto.isWebRequest() );
                    inCrmQrcDto.setContentPojos( policyAdviceList );
                }
            }
            crmServiceCode = CRMServiceCode.CRM001;
        }
        catch ( Exception ex )
        {
            LOGGER.error( "Exception while getPOPlanMigrationPolicy ", ex );
        }
        finally
        {
            CRMServiceUtils.closeSession( session );
            inCrmQrcDto.setStatusCode( crmServiceCode.getStatusCode() );
            inCrmQrcDto.setStatusDesc( crmServiceCode.getStatusDesc() );
        }
        return inCrmQrcDto;
    }

    private List<ContentPojo> getPOL2UPlanChangePolicyAdvice( CrmPlanMasterPojo oldBasePlanPOJO,
                                                              CrmPlanMasterPojo newBasePlanPOJO,
                                                              CrmPlanMasterPojo newAddonPlanPOJO,
                                                              double actualUsage,
                                                              boolean webRequest )
    {
        List<ContentPojo> adivceList = new ArrayList<ContentPojo>();
        PlanMigrationPolicyPojo planMigrationPolicyPojo = getPlanChangePolicyMasterObject( "PO", "LU" );
        //Decrease Speed
        if ( oldBasePlanPOJO.getPrimarySpeed() > newBasePlanPOJO.getPrimarySpeed() )
        {
            //next bill cycle
            LOGGER.info( "Decrease Speed" );
            ContentPojo contentPojo = new ContentPojo();
            contentPojo.setContentName( IAppConstants.NBC );
            adivceList.add( contentPojo );
        }
        //Increase Speed
        else if ( newBasePlanPOJO.getPrimarySpeed() >= oldBasePlanPOJO.getPrimarySpeed() )
        {
            LOGGER.info( "Increase Speed" );
            double totalDUL = newBasePlanPOJO.getPrimaryQuota();
            if ( StringUtils.equals( newBasePlanPOJO.getAddonAllowedYn(), IAppConstants.Y ) )
            {
                if ( webRequest )
                {
                    if ( StringUtils.isValidObj( newAddonPlanPOJO ) )
                    {
                        totalDUL = totalDUL + newAddonPlanPOJO.getPrimaryQuota();
                    }
                }
            }
            LOGGER.info( "Total DUL:: " + totalDUL );
            if ( actualUsage <= totalDUL )
            {
                //Immediate with advice
                LOGGER.info( "Actual usage <= new DUL" );
                ContentPojo contentPojo = new ContentPojo();
                contentPojo.setContentName( IAppConstants.IMMEDIATE_ACTIVATION );
                contentPojo.setContentValue( planMigrationPolicyPojo.getIncSpeedIMMAdvice() );
                ContentPojo contentPojo1 = new ContentPojo();
                contentPojo1.setContentName( IAppConstants.NBC );
                adivceList.add( contentPojo );
                adivceList.add( contentPojo1 );
            }
            else
            {
                //next bill cycle
                LOGGER.info( "Actual usage greater DUL" );
                ContentPojo contentPojo = new ContentPojo();
                contentPojo.setContentName( IAppConstants.NBC );
                contentPojo.setContentValue( planMigrationPolicyPojo.getIncSpeedNBCAdvice() );
                adivceList.add( contentPojo );
            }
        }
        return adivceList;
    }

    private List<ContentPojo> getPOL2LPlanChangePolicyAdvice( CrmPlanMasterPojo oldBasePlanPOJO,
                                                              CrmPlanMasterPojo newBasePlanPOJO,
                                                              double actualUsage )
    {
        List<ContentPojo> adivceList = new ArrayList<ContentPojo>();
        PlanMigrationPolicyPojo planMigrationPolicyPojo = getPlanChangePolicyMasterObject( "PO", "LL" );
        // Inc DUL
        if ( oldBasePlanPOJO.getPrimaryQuota() < newBasePlanPOJO.getPrimaryQuota() )
        {
            //            double totalDUL = oldBasePlanPOJO.getPrimaryQuota();
            //            if ( actualUsage <= totalDUL )
            //            {
            //Immediate
            ContentPojo contentPojo = new ContentPojo();
            contentPojo.setContentName( IAppConstants.IMMEDIATE_ACTIVATION );
            ContentPojo contentPojo1 = new ContentPojo();
            contentPojo1.setContentName( IAppConstants.NBC );
            adivceList.add( contentPojo );
            adivceList.add( contentPojo1 );
            //            }
            //            else if ( actualUsage > totalDUL )
            //            {
            //                //Immediate with advice
            //                ContentPojo contentPojo = new ContentPojo();
            //                contentPojo.setContentName( IAppConstants.IMMEDIATE_ACTIVATION );
            //                contentPojo.setContentValue( planMigrationPolicyPojo.getIncDULIMMAdvice() );
            //                ContentPojo contentPojo1 = new ContentPojo();
            //                contentPojo1.setContentName( IAppConstants.NBC );
            //                adivceList.add( contentPojo );
            //                adivceList.add( contentPojo1 );
            //            }
        }
        //Dec speed
        else if ( newBasePlanPOJO.getPrimarySpeed() < oldBasePlanPOJO.getPrimarySpeed() )
        {
            //next bill cycle
            ContentPojo contentPojo = new ContentPojo();
            contentPojo.setContentName( IAppConstants.NBC );
            adivceList.add( contentPojo );
        }
        //Dec DUL
        else if ( oldBasePlanPOJO.getPrimaryQuota() > newBasePlanPOJO.getPrimaryQuota() )
        {
            double totalDUL = newBasePlanPOJO.getPrimaryQuota();
            if ( actualUsage <= totalDUL )
            {
                //Immediate with advice
                ContentPojo contentPojo = new ContentPojo();
                contentPojo.setContentName( IAppConstants.IMMEDIATE_ACTIVATION );
                ContentPojo contentPojo1 = new ContentPojo();
                contentPojo1.setContentName( IAppConstants.NBC );
                adivceList.add( contentPojo );
                adivceList.add( contentPojo1 );
            }
            else if ( actualUsage > totalDUL )
            {
                //next bill cycle with advice
                ContentPojo contentPojo = new ContentPojo();
                contentPojo.setContentName( IAppConstants.NBC );
                adivceList.add( contentPojo );
            }
        }
        else
        {
            LOGGER.info( "Same Speed" );
            ContentPojo contentPojo = new ContentPojo();
            contentPojo.setContentName( IAppConstants.IMMEDIATE_ACTIVATION );
            ContentPojo contentPojo1 = new ContentPojo();
            contentPojo1.setContentName( IAppConstants.NBC );
            adivceList.add( contentPojo );
            adivceList.add( contentPojo1 );
        }
        return adivceList;
    }

    private List<ContentPojo> getPOU2LPlanChangePolicyAdvice( CrmPlanMasterPojo oldBasePlanPOJO,
                                                              CrmPlanMasterPojo newBasePlanPOJO,
                                                              double actualUsage )
    {
        List<ContentPojo> adivceList = new ArrayList<ContentPojo>();
        PlanMigrationPolicyPojo planMigrationPolicyPojo = getPlanChangePolicyMasterObject( "PO", "UL" );
        //Decrease Speed
        if ( oldBasePlanPOJO.getPrimarySpeed() > newBasePlanPOJO.getPrimarySpeed() )
        {
            //next bill cycle
            ContentPojo contentPojo = new ContentPojo();
            contentPojo.setContentName( IAppConstants.NBC );
            adivceList.add( contentPojo );
        }
        //Increase Speed
        else if ( oldBasePlanPOJO.getPrimarySpeed() <= newBasePlanPOJO.getPrimarySpeed() )
        {
            double totalDUL = newBasePlanPOJO.getPrimaryQuota();
            if ( actualUsage <= totalDUL )
            {
                //Immediate with Advice
                ContentPojo contentPojo = new ContentPojo();
                contentPojo.setContentName( IAppConstants.IMMEDIATE_ACTIVATION );
                contentPojo.setContentValue( planMigrationPolicyPojo.getIncSpeedIMMAdvice() );
                ContentPojo contentPojo2 = new ContentPojo();
                contentPojo2.setContentName( IAppConstants.NBC );
                adivceList.add( contentPojo );
                adivceList.add( contentPojo2 );
            }
            else if ( actualUsage > totalDUL )
            {
                //Next Bill Cycle with Advice
                ContentPojo contentPojo = new ContentPojo();
                contentPojo.setContentName( IAppConstants.NBC );
                //                contentPojo.setContentValue( planMigrationPolicyPojo.getIncSpeedNBCAdvice() );
                adivceList.add( contentPojo );
            }
        }
        return adivceList;
    }

    private PlanMigrationPolicyPojo getPlanChangePolicyMasterObject( String category, String subCategory )
    {
        PlanMigrationPolicyPojo planMigrationPolicyPojo = new PlanMigrationPolicyPojo();
        planMigrationPolicyPojo.setCategory( category );
        planMigrationPolicyPojo.setSubCategory( subCategory );
        ConfigDto configDto = new ConfigDto();
        configDto.setPlanMigrationPolicyPojo( planMigrationPolicyPojo );
        try
        {
            configDto = CRMServicesProxy
                    .getInstance()
                    .getCRMConfigService( IGlobalConstants.REMOTE, IGlobalConstants.APP )
                    .configOperations( ServiceParameter.LIST.getParameter(),
                                       PlanMigrationPolicyPojo.class.getSimpleName(), configDto );
        }
        catch ( SOAPException ex )
        {
            LOGGER.error( "Getting Error while call getPlanChangePolicyMasterObject", ex );
        }
        if ( StringUtils.isValidObj( configDto.getPlanMigrationPolicyPojo() ) )
        {
            return configDto.getPlanMigrationPolicyPojo();
        }
        else
        {
            return new PlanMigrationPolicyPojo();
        }
    }

    private List<ContentPojo> getPOU2UPlanChangePolicyAdvice( CrmPlanMasterPojo oldBasePlanPOJO,
                                                              CrmPlanMasterPojo newBasePlanPOJO,
                                                              CrmPlanMasterPojo oldAddonPlanPOJO,
                                                              CrmPlanMasterPojo newAddonPlanPOJO,
                                                              double actualUsage,
                                                              boolean webRequest )
    {
        LOGGER.info( "Plan change from one unlimited to another unlimited" );
        List<ContentPojo> adivceList = new ArrayList<ContentPojo>();
        PlanMigrationPolicyPojo planMigrationPolicyPojo = getPlanChangePolicyMasterObject( "PO", "UU" );
        double oldQuota = oldBasePlanPOJO.getPrimaryQuota();
        double newQuota = newBasePlanPOJO.getPrimaryQuota();
        if ( StringUtils.isValidObj( oldAddonPlanPOJO ) )
        {
            oldQuota = oldQuota + oldAddonPlanPOJO.getPrimaryQuota();
            if ( StringUtils.equals( newBasePlanPOJO.getAddonAllowedYn(), IAppConstants.Y ) )
            {
                if ( webRequest )
                {
                    if ( StringUtils.isValidObj( newAddonPlanPOJO ) )
                    {
                        newQuota = newQuota + newAddonPlanPOJO.getPrimaryQuota();
                    }
                }
                else
                {
                    newQuota = newQuota + oldAddonPlanPOJO.getPrimaryQuota();
                }
                LOGGER.info( "new quota :: " + newQuota );
            }
        }
        if ( newBasePlanPOJO.getPrimarySpeed() < oldBasePlanPOJO.getPrimarySpeed() )
        //Dec speed
        {
            LOGGER.info( "DECcrease Speed Block" );
            //next bill cycle
            ContentPojo contentPojo = new ContentPojo();
            contentPojo.setContentName( IAppConstants.NBC );
            adivceList.add( contentPojo );
        }
        //Dec DUL
        else if ( newQuota < oldQuota )
        {
            LOGGER.info( "Deccrease DUL Block" );
            if ( actualUsage <= newQuota )
            {
                //Immediate with advice
                ContentPojo contentPojo = new ContentPojo();
                contentPojo.setContentName( IAppConstants.IMMEDIATE_ACTIVATION );
                contentPojo.setContentValue( planMigrationPolicyPojo.getDecDULIMMAdvice() );
                ContentPojo contentPojo1 = new ContentPojo();
                contentPojo1.setContentName( IAppConstants.NBC );
                adivceList.add( contentPojo );
                adivceList.add( contentPojo1 );
            }
            else if ( actualUsage > newQuota )
            {
                //next bill cycle with advice
                ContentPojo contentPojo = new ContentPojo();
                contentPojo.setContentName( IAppConstants.NBC );
                contentPojo.setContentValue( planMigrationPolicyPojo.getDecDULNBCAdvice() );
                adivceList.add( contentPojo );
            }
        }
        // Inc DUL
        else if ( newQuota > oldQuota )
        {
            LOGGER.info( "Increase DUL Block" );
            //Immediate with advice
            ContentPojo contentPojo = new ContentPojo();
            contentPojo.setContentName( IAppConstants.IMMEDIATE_ACTIVATION );
            contentPojo.setContentValue( planMigrationPolicyPojo.getIncDULIMMAdvice() );
            ContentPojo contentPojo1 = new ContentPojo();
            contentPojo1.setContentName( IAppConstants.NBC );
            adivceList.add( contentPojo );
            adivceList.add( contentPojo1 );
        }
        //Inc Speed
        else if ( oldBasePlanPOJO.getPrimarySpeed() < newBasePlanPOJO.getPrimarySpeed() )
        {
            LOGGER.info( "Increase Speed Block" );
            float DUL80 = (float) ( newQuota * 80 / 100 );
            float DUL100 = (float) ( newQuota * 100 / 100 );
            if ( actualUsage <= DUL80 )
            {
                //Immediate
                LOGGER.info( "Actual Usage less DUL80" );
                ContentPojo contentPojo = new ContentPojo();
                contentPojo.setContentName( IAppConstants.IMMEDIATE_ACTIVATION );
                ContentPojo contentPojo1 = new ContentPojo();
                contentPojo1.setContentName( IAppConstants.NBC );
                adivceList.add( contentPojo );
                adivceList.add( contentPojo1 );
            }
            else if ( actualUsage <= DUL100 )
            {
                //Immediate with advice
                LOGGER.info( "Actual Usage less DUL100" );
                ContentPojo contentPojo = new ContentPojo();
                contentPojo.setContentName( IAppConstants.IMMEDIATE_ACTIVATION );
                contentPojo.setContentValue( planMigrationPolicyPojo.getIncSpeedIMMAdvice() );
                ContentPojo contentPojo1 = new ContentPojo();
                contentPojo1.setContentName( IAppConstants.NBC );
                adivceList.add( contentPojo );
                adivceList.add( contentPojo1 );
            }
            else
            {
                //next bill cycle with advice
                LOGGER.info( "Actual Usage greater DUL" );
                ContentPojo contentPojo = new ContentPojo();
                contentPojo.setContentName( IAppConstants.NBC );
                contentPojo.setContentValue( planMigrationPolicyPojo.getIncSpeedNBCAdvice() );
                adivceList.add( contentPojo );
            }
        }
        else
        {
            LOGGER.info( "Same Speed" );
            ContentPojo contentPojo = new ContentPojo();
            contentPojo.setContentName( IAppConstants.IMMEDIATE_ACTIVATION );
            ContentPojo contentPojo1 = new ContentPojo();
            contentPojo1.setContentName( IAppConstants.NBC );
            adivceList.add( contentPojo );
            adivceList.add( contentPojo1 );
        }
        return adivceList;
    }

    @Override
    public CrmQrcDto getPRPlanMigrationPolicy( CrmQrcDto inCrmQrcDto )
    {
        LOGGER.info( "Inside QrcManagerDaoImpl : getPRPlanMigrationPolicy" );
        CRMServiceCode crmServiceCode = CRMServiceCode.CRM999;
        Session session = null;
        Criteria criteria = null;
        CrmPlanMasterPojo oldBasePlanPOJO = null;
        CrmPlanMasterPojo newBasePlanPOJO = null;
        CrmPlanMasterPojo oldAddonPlanPOJO = null;
        CrmPlanMasterPojo newAddonPlanPOJO = null;
        try
        {
            session = HibernateUtil.getCurrentSession();
            if ( StringUtils.isNotEmpty( inCrmQrcDto.getOldBasePlanCode() ) )
            {
                LOGGER.info( "old base plan code :: " + inCrmQrcDto.getOldBasePlanCode() );
                criteria = session.createCriteria( CrmPlanMasterPojo.class );
                criteria.add( Restrictions.eq( "planCode", inCrmQrcDto.getOldBasePlanCode() ) );
                criteria.setCacheable( true );
                criteria.setCacheRegion( CrmPlanMasterPojo.class.getName() );
                oldBasePlanPOJO = (CrmPlanMasterPojo) criteria.list().get( 0 );
            }
            if ( StringUtils.isNotEmpty( inCrmQrcDto.getNewBasePlanCode() ) )
            {
                LOGGER.info( "new base plan code :: " + inCrmQrcDto.getNewBasePlanCode() );
                criteria = session.createCriteria( CrmPlanMasterPojo.class );
                criteria.add( Restrictions.eq( "planCode", inCrmQrcDto.getNewBasePlanCode() ) );
                criteria.setCacheable( true );
                criteria.setCacheRegion( CrmPlanMasterPojo.class.getName() );
                newBasePlanPOJO = (CrmPlanMasterPojo) criteria.list().get( 0 );
            }
            if ( StringUtils.isNotEmpty( inCrmQrcDto.getOldAddonPlanCode() ) )
            {
                LOGGER.info( "old addon plan code :: " + inCrmQrcDto.getOldAddonPlanCode() );
                criteria = session.createCriteria( CrmPlanMasterPojo.class );
                criteria.add( Restrictions.eq( "planCode", inCrmQrcDto.getOldAddonPlanCode() ) );
                criteria.setCacheable( true );
                criteria.setCacheRegion( CrmPlanMasterPojo.class.getName() );
                oldAddonPlanPOJO = (CrmPlanMasterPojo) criteria.list().get( 0 );
            }
            if ( StringUtils.isNotEmpty( inCrmQrcDto.getNewAddonPlanCode() ) )
            {
                LOGGER.info( "new addon plan code :: " + inCrmQrcDto.getNewAddonPlanCode() );
                criteria = session.createCriteria( CrmPlanMasterPojo.class );
                criteria.add( Restrictions.eq( "planCode", inCrmQrcDto.getNewAddonPlanCode() ) );
                criteria.setCacheable( true );
                criteria.setCacheRegion( CrmPlanMasterPojo.class.getName() );
                newAddonPlanPOJO = (CrmPlanMasterPojo) criteria.list().get( 0 );
            }
            if ( StringUtils.isValidObj( oldBasePlanPOJO ) && StringUtils.isValidObj( newBasePlanPOJO )
                    && StringUtils.isValidObj( inCrmQrcDto.getCustAdditionalDetails() ) )
            {
                /*if ( StringUtils.equals( oldBasePlanPOJO.getQuotaType(), "TUL" )
                        || StringUtils.equals( newBasePlanPOJO.getQuotaType(), "TUL" ) )
                {
                    List<ContentPojo> adivceList = new ArrayList<ContentPojo>();
                    if ( newBasePlanPOJO.getPrimarySpeed() < oldBasePlanPOJO.getPrimarySpeed() )
                    {
                        //next bill cycle
                        ContentPojo contentPojoNbc = new ContentPojo();
                        contentPojoNbc.setContentName( IAppConstants.NBC );
                        adivceList.add( contentPojoNbc );
                    }
                    else
                    {
                        ContentPojo contentPojoIme = new ContentPojo();
                        contentPojoIme.setContentName( IAppConstants.IMMEDIATE_ACTIVATION );
                        adivceList.add( contentPojoIme );
                        ContentPojo contentPojoNbc = new ContentPojo();
                        contentPojoNbc.setContentName( IAppConstants.NBC );
                        adivceList.add( contentPojoNbc );
                        inCrmQrcDto.setContentPojos( adivceList );
                    }
                }*/
                double actualUsage = inCrmQrcDto.getCustAdditionalDetails().getUsedVolumeQuota();
                LOGGER.info( "Actual Usage:: " + actualUsage );
                LOGGER.info( "Request from:: " + inCrmQrcDto.isWebRequest() );
                //Calling Unlimited to Unlimited
                if ( StringUtils.equals( oldBasePlanPOJO.getPlanUsageType(), "U" )
                        && StringUtils.equals( newBasePlanPOJO.getPlanUsageType(), "U" ) )
                {
                    List<ContentPojo> policyAdviceList = getPRU2UPlanChangePolicyAdvice( oldBasePlanPOJO,
                                                                                         newBasePlanPOJO,
                                                                                         oldAddonPlanPOJO,
                                                                                         newAddonPlanPOJO, actualUsage,
                                                                                         inCrmQrcDto.isWebRequest() );
                    inCrmQrcDto.setContentPojos( policyAdviceList );
                }
                //Calling Unlimited to Limited
                else if ( StringUtils.equals( oldBasePlanPOJO.getPlanUsageType(), "U" )
                        && StringUtils.equals( newBasePlanPOJO.getPlanUsageType(), "L" ) )
                {
                    List<ContentPojo> policyAdviceList = getPRU2LPlanChangePolicyAdvice( oldBasePlanPOJO,
                                                                                         newBasePlanPOJO, actualUsage );
                    inCrmQrcDto.setContentPojos( policyAdviceList );
                }
                //Calling Limited to Limited
                else if ( StringUtils.equals( oldBasePlanPOJO.getPlanUsageType(), "L" )
                        && StringUtils.equals( newBasePlanPOJO.getPlanUsageType(), "L" ) )
                {
                    List<ContentPojo> policyAdviceList = getPRL2LPlanChangePolicyAdvice( oldBasePlanPOJO,
                                                                                         newBasePlanPOJO, actualUsage );
                    inCrmQrcDto.setContentPojos( policyAdviceList );
                }
                //Calling Limited to Unlimited
                else if ( StringUtils.equals( oldBasePlanPOJO.getPlanUsageType(), "L" )
                        && StringUtils.equals( newBasePlanPOJO.getPlanUsageType(), "U" ) )
                {
                    List<ContentPojo> policyAdviceList = getPRL2UPlanChangePolicyAdvice( oldBasePlanPOJO,
                                                                                         newBasePlanPOJO,
                                                                                         newAddonPlanPOJO, actualUsage,
                                                                                         inCrmQrcDto.isWebRequest() );
                    inCrmQrcDto.setContentPojos( policyAdviceList );
                }
            }
            crmServiceCode = CRMServiceCode.CRM001;
        }
        catch ( Exception ex )
        {
            LOGGER.error( "Exception while getPRPlanMigrationPolicy", ex );
        }
        finally
        {
            CRMServiceUtils.closeSession( session );
            inCrmQrcDto.setStatusCode( crmServiceCode.getStatusCode() );
            inCrmQrcDto.setStatusDesc( crmServiceCode.getStatusDesc() );
        }
        return inCrmQrcDto;
    }

    private List<ContentPojo> getPRL2UPlanChangePolicyAdvice( CrmPlanMasterPojo oldBasePlanPOJO,
                                                              CrmPlanMasterPojo newBasePlanPOJO,
                                                              CrmPlanMasterPojo newAddonPlanPOJO,
                                                              double actualUsage,
                                                              boolean webRequest )
    {
        List<ContentPojo> adivceList = new ArrayList<ContentPojo>();
        PlanMigrationPolicyPojo planMigrationPolicyPojo = getPlanChangePolicyMasterObject( "PR", "LU" );
        //Decrease Speed
        if ( oldBasePlanPOJO.getPrimarySpeed() > newBasePlanPOJO.getPrimarySpeed() )
        {
            //next bill cycle
            ContentPojo contentPojo = new ContentPojo();
            contentPojo.setContentName( IAppConstants.NBC );
            adivceList.add( contentPojo );
        }
        //Increase Speed
        else if ( newBasePlanPOJO.getPrimarySpeed() >= oldBasePlanPOJO.getPrimarySpeed() )
        {
            double totalDUL = newBasePlanPOJO.getPrimaryQuota();
            if ( StringUtils.equals( newBasePlanPOJO.getAddonAllowedYn(), IAppConstants.Y ) )
            {
                if ( webRequest )
                {
                    if ( StringUtils.isValidObj( newAddonPlanPOJO ) )
                    {
                        totalDUL = totalDUL + newAddonPlanPOJO.getPrimaryQuota();
                    }
                }
            }
            //            float DUL80 = (float) ( totalDUL * 80 / 100 );
            //            float DUL100 = (float) ( totalDUL * 100 / 100 );
            //            if ( actualUsage <= DUL80 )
            //            {
            //                //Immediate
            //                ContentPojo contentPojo = new ContentPojo();
            //                contentPojo.setContentName( IAppConstants.IMMEDIATE_ACTIVATION );
            //                ContentPojo contentPojo1 = new ContentPojo();
            //                contentPojo1.setContentName( IAppConstants.NBC );
            //                adivceList.add( contentPojo );
            //                adivceList.add( contentPojo1 );
            //            }
            //            else 
            if ( actualUsage <= totalDUL )
            {
                //Immediate with advice
                ContentPojo contentPojo = new ContentPojo();
                contentPojo.setContentName( IAppConstants.IMMEDIATE_ACTIVATION );
                contentPojo.setContentValue( planMigrationPolicyPojo.getIncSpeedIMMAdvice() );
                ContentPojo contentPojo1 = new ContentPojo();
                contentPojo1.setContentName( IAppConstants.NBC );
                adivceList.add( contentPojo );
                adivceList.add( contentPojo1 );
            }
            else
            {
                //next bill cycle
                ContentPojo contentPojo = new ContentPojo();
                contentPojo.setContentName( IAppConstants.NBC );
                contentPojo.setContentValue( planMigrationPolicyPojo.getIncSpeedNBCAdvice() );
                adivceList.add( contentPojo );
            }
        }
        return adivceList;
    }

    private List<ContentPojo> getPRL2LPlanChangePolicyAdvice( CrmPlanMasterPojo oldBasePlanPOJO,
                                                              CrmPlanMasterPojo newBasePlanPOJO,
                                                              double actualUsage )
    {
        List<ContentPojo> adivceList = new ArrayList<ContentPojo>();
        // PlanMigrationPolicyPojo planMigrationPolicyPojo = getPlanChangePolicyMasterObject( "PR", "LL" );
        // Inc DUL
        if ( oldBasePlanPOJO.getPrimaryQuota() < newBasePlanPOJO.getPrimaryQuota() )
        {
            //Immediate
            ContentPojo contentPojo = new ContentPojo();
            contentPojo.setContentName( IAppConstants.IMMEDIATE_ACTIVATION );
            ContentPojo contentPojo1 = new ContentPojo();
            contentPojo1.setContentName( IAppConstants.NBC );
            adivceList.add( contentPojo );
            adivceList.add( contentPojo1 );
        }
        // Dec Speed
        else if ( newBasePlanPOJO.getPrimarySpeed() < oldBasePlanPOJO.getPrimarySpeed() )
        {
            ContentPojo contentPojo = new ContentPojo();
            contentPojo.setContentName( IAppConstants.NBC );
            adivceList.add( contentPojo );
        }
        //Dec DUL 
        else if ( oldBasePlanPOJO.getPrimaryQuota() > newBasePlanPOJO.getPrimaryQuota() )
        {
            double totalDUL = newBasePlanPOJO.getPrimaryQuota();
            if ( actualUsage <= totalDUL )
            {
                //Immediate with advice
                ContentPojo contentPojo = new ContentPojo();
                contentPojo.setContentName( IAppConstants.IMMEDIATE_ACTIVATION );
                ContentPojo contentPojo1 = new ContentPojo();
                contentPojo1.setContentName( IAppConstants.NBC );
                adivceList.add( contentPojo );
                adivceList.add( contentPojo1 );
            }
            else if ( actualUsage > totalDUL )
            {
                //next bill cycle with advice
                ContentPojo contentPojo = new ContentPojo();
                contentPojo.setContentName( IAppConstants.NBC );
                adivceList.add( contentPojo );
            }
        }
        else
        {
            LOGGER.info( "Same Speed" );
            ContentPojo contentPojo = new ContentPojo();
            contentPojo.setContentName( IAppConstants.IMMEDIATE_ACTIVATION );
            ContentPojo contentPojo1 = new ContentPojo();
            contentPojo1.setContentName( IAppConstants.NBC );
            adivceList.add( contentPojo );
            adivceList.add( contentPojo1 );
        }
        return adivceList;
    }

    private List<ContentPojo> getPRU2LPlanChangePolicyAdvice( CrmPlanMasterPojo oldBasePlanPOJO,
                                                              CrmPlanMasterPojo newBasePlanPOJO,
                                                              double actualUsage )
    {
        List<ContentPojo> adivceList = new ArrayList<ContentPojo>();
        PlanMigrationPolicyPojo planMigrationPolicyPojo = getPlanChangePolicyMasterObject( "PR", "UL" );
        //Decrease Speed
        if ( oldBasePlanPOJO.getPrimarySpeed() > newBasePlanPOJO.getPrimarySpeed() )
        {
            //next bill cycle
            ContentPojo contentPojo = new ContentPojo();
            contentPojo.setContentName( IAppConstants.NBC );
            adivceList.add( contentPojo );
        }
        //Increase Speed
        else if ( oldBasePlanPOJO.getPrimarySpeed() <= newBasePlanPOJO.getPrimarySpeed() )
        {
            double totalDUL = newBasePlanPOJO.getPrimaryQuota();
            if ( actualUsage <= totalDUL )
            {
                //Immediate with Advice
                ContentPojo contentPojo = new ContentPojo();
                contentPojo.setContentName( IAppConstants.IMMEDIATE_ACTIVATION );
                contentPojo.setContentValue( planMigrationPolicyPojo.getIncSpeedIMMAdvice() );
                ContentPojo contentPojo2 = new ContentPojo();
                contentPojo2.setContentName( IAppConstants.NBC );
                adivceList.add( contentPojo );
                adivceList.add( contentPojo2 );
            }
            else if ( actualUsage > totalDUL )
            {
                //Next Bill Cycle with Advice
                ContentPojo contentPojo = new ContentPojo();
                contentPojo.setContentName( IAppConstants.NBC );
                //                contentPojo.setContentValue( planMigrationPolicyPojo.getIncSpeedNBCAdvice() );
                adivceList.add( contentPojo );
            }
        }
        return adivceList;
    }

    private List<ContentPojo> getPRU2UPlanChangePolicyAdvice( CrmPlanMasterPojo oldBasePlanPOJO,
                                                              CrmPlanMasterPojo newBasePlanPOJO,
                                                              CrmPlanMasterPojo oldAddonPlanPOJO,
                                                              CrmPlanMasterPojo newAddonPlanPOJO,
                                                              double actualUsage,
                                                              boolean webRequest )
    {
        List<ContentPojo> adivceList = new ArrayList<ContentPojo>();
        PlanMigrationPolicyPojo planMigrationPolicyPojo = getPlanChangePolicyMasterObject( "PR", "UU" );
        double oldQuota = oldBasePlanPOJO.getPrimaryQuota();
        double newQuota = newBasePlanPOJO.getPrimaryQuota();
        if ( StringUtils.isValidObj( oldAddonPlanPOJO ) )
        {
            oldQuota = oldQuota + oldAddonPlanPOJO.getPrimaryQuota();
            LOGGER.info( "old quota :: " + oldQuota );
            if ( StringUtils.equals( newBasePlanPOJO.getAddonAllowedYn(), IAppConstants.Y ) )
            {
                if ( webRequest )
                {
                    if ( StringUtils.isValidObj( newAddonPlanPOJO ) )
                    {
                        newQuota = newQuota + newAddonPlanPOJO.getPrimaryQuota();
                    }
                }
                else
                {
                    newQuota = newQuota + oldAddonPlanPOJO.getPrimaryQuota();
                }
                LOGGER.info( "new quota :: " + newQuota );
            }
        }
        //Dec speed
        if ( newBasePlanPOJO.getPrimarySpeed() < oldBasePlanPOJO.getPrimarySpeed() )
        {
            //next bill cycle
            ContentPojo contentPojo = new ContentPojo();
            contentPojo.setContentName( IAppConstants.NBC );
            adivceList.add( contentPojo );
        }
        //Dec DUL
        else if ( newQuota < oldQuota )
        {
            if ( actualUsage <= newQuota )
            {
                //Immediate with advice
                ContentPojo contentPojo = new ContentPojo();
                contentPojo.setContentName( IAppConstants.IMMEDIATE_ACTIVATION );
                contentPojo.setContentValue( planMigrationPolicyPojo.getDecDULIMMAdvice() );
                ContentPojo contentPojo1 = new ContentPojo();
                contentPojo1.setContentName( IAppConstants.NBC );
                adivceList.add( contentPojo );
                adivceList.add( contentPojo1 );
            }
            else if ( actualUsage > newQuota )
            {
                //next bill cycle with advice
                ContentPojo contentPojo = new ContentPojo();
                contentPojo.setContentName( IAppConstants.NBC );
                contentPojo.setContentValue( planMigrationPolicyPojo.getDecDULNBCAdvice() );
                adivceList.add( contentPojo );
            }
        }
        // Inc DUL
        else if ( newQuota > oldQuota )
        {
            //Immediate with advice
            ContentPojo contentPojo = new ContentPojo();
            contentPojo.setContentName( IAppConstants.IMMEDIATE_ACTIVATION );
            contentPojo.setContentValue( planMigrationPolicyPojo.getIncDULIMMAdvice() );
            ContentPojo contentPojo1 = new ContentPojo();
            contentPojo1.setContentName( IAppConstants.NBC );
            adivceList.add( contentPojo );
            adivceList.add( contentPojo1 );
        }
        //Inc Speed
        else if ( oldBasePlanPOJO.getPrimarySpeed() < newBasePlanPOJO.getPrimarySpeed() )
        {
            float DUL80 = (float) ( newQuota * 80 / 100 );
            float DUL100 = (float) ( newQuota * 100 / 100 );
            if ( actualUsage <= DUL80 )
            {
                //Immediate
                ContentPojo contentPojo = new ContentPojo();
                contentPojo.setContentName( IAppConstants.IMMEDIATE_ACTIVATION );
                ContentPojo contentPojo1 = new ContentPojo();
                contentPojo1.setContentName( IAppConstants.NBC );
                adivceList.add( contentPojo );
                adivceList.add( contentPojo1 );
            }
            else if ( actualUsage <= DUL100 )
            {
                //Immediate with advice
                ContentPojo contentPojo = new ContentPojo();
                contentPojo.setContentName( IAppConstants.IMMEDIATE_ACTIVATION );
                contentPojo.setContentValue( planMigrationPolicyPojo.getIncSpeedIMMAdvice() );
                ContentPojo contentPojo1 = new ContentPojo();
                contentPojo1.setContentName( IAppConstants.NBC );
                adivceList.add( contentPojo );
                adivceList.add( contentPojo1 );
            }
            else
            {
                //next bill cycle with advice
                ContentPojo contentPojo = new ContentPojo();
                contentPojo.setContentName( IAppConstants.NBC );
                contentPojo.setContentValue( planMigrationPolicyPojo.getIncSpeedNBCAdvice() );
                adivceList.add( contentPojo );
            }
        }
        else
        {
            LOGGER.info( "Same Speed" );
            ContentPojo contentPojo = new ContentPojo();
            contentPojo.setContentName( IAppConstants.IMMEDIATE_ACTIVATION );
            ContentPojo contentPojo1 = new ContentPojo();
            contentPojo1.setContentName( IAppConstants.NBC );
            adivceList.add( contentPojo );
            adivceList.add( contentPojo1 );
        }
        return adivceList;
    }

    @Override
    public CrmQrcDto customerPlanRenewal( CrmQrcDto inCrmQrcDto )
    {
        LOGGER.info( "Inside QrcManagerDaoImpl : customerPlanRenewal" );
        CRMServiceCode crmServiceCode = CRMServiceCode.CRM999;
        Session session = null;
        Transaction transaction = null;
        CrmCustomerActivityPojo activityLog = null;
        CrmPlanMasterPojo crmPlanMasterPojo = null;
        CrmBillingDto crmBillingDto = null;
        Map<String, Long> evicts = new HashMap<String, Long>();
        if ( StringUtils.isValidObj( inCrmQrcDto.getCustomerId() ) )
        {
            try
            {
                session = HibernateUtil.getCurrentSession();
                transaction = session.beginTransaction();
                LOGGER.info( "Check Addon Action::" + inCrmQrcDto.getAddonAction() );
                activityLog = new CrmCustomerActivityPojo();
                CrmPlanDetailsPojo crmPlanDetailsPojoDB = (CrmPlanDetailsPojo) session
                        .get( CrmPlanDetailsPojo.class, inCrmQrcDto.getCrmPlanDetailsPojo().getRecordId() );
                crmPlanMasterPojo = CRMServiceUtils.getDBValues( CrmPlanMasterPojo.class, "planCode", inCrmQrcDto
                        .getCrmPlanDetailsPojo().getBasePlanCode() );
                if ( StringUtils.isValidObj( crmPlanMasterPojo ) )
                {
                    activityLog.setOldValue( crmPlanMasterPojo.getPlanName() );
                }
                else
                {
                    activityLog.setOldValue( IAppConstants.DASH );
                }
                activityLog.setNewValue( inCrmQrcDto.getPlanMasterPojo().getPlanName() );
                crmPlanDetailsPojoDB.setBasePlanCode( inCrmQrcDto.getPlanMasterPojo().getPlanCode() );
                if ( StringUtils.equals( inCrmQrcDto.getAddonAction(), IAppConstants.WITHOUT_ADDON ) )
                {
                    crmPlanDetailsPojoDB.setAddOnPlanCode( null );
                }
                if ( StringUtils.equals( inCrmQrcDto.getPlanMasterPojo().getAddonAllowedYn(), IAppConstants.NO_CHAR )
                        && StringUtils.isNotEmpty( inCrmQrcDto.getCrmPlanDetailsPojo().getAddOnPlanCode() ) )
                {
                    crmPlanDetailsPojoDB.setAddOnPlanCode( null );
                }
                crmPlanDetailsPojoDB.setLastModifiedBy( inCrmQrcDto.getUserId() );
                crmPlanDetailsPojoDB.setLastModifiedTime( Calendar.getInstance().getTime() );
                inCrmQrcDto.setCrmPlanDetailsPojo( crmPlanDetailsPojoDB );
                //Billing API Call
                getCrmBillingManager().instantOfferRenew( inCrmQrcDto );
                if ( StringUtils.equals( inCrmQrcDto.getStatusCode(), CRMServiceCode.CRM001.getStatusCode() ) )
                {
                    session.update( crmPlanDetailsPojoDB );
                    evicts.put( CrmPlanDetailsPojo.class.getName(), null );
                    immediateTicketOperation( inCrmQrcDto );
                    activityLog.setTicketId( inCrmQrcDto.getCrmSrTicketsPojo().getSrId() );
                    long activityId = saveCustomerActivity( activityLog, inCrmQrcDto,
                                                            CRMCustomerActivityActions.PLAN_RENEW_BASE );
                    if ( StringUtils.isValidObj( inCrmQrcDto.getRemarksPojo() ) )
                    {
                        inCrmQrcDto.getRemarksPojo().setMappingId( inCrmQrcDto.getCustomerId() );
                        inCrmQrcDto.getRemarksPojo().setActions( inCrmQrcDto.getActivityAction() );
                        inCrmQrcDto.getRemarksPojo().setCreatedBy( inCrmQrcDto.getUserId() );
                        inCrmQrcDto.getRemarksPojo().setCreatedTime( Calendar.getInstance().getTime() );
                        inCrmQrcDto.getRemarksPojo().setActivityId( activityId );
                        inCrmQrcDto.getRemarksPojo().setMappingType( CRMRCAReason.QRC.getStatusCode() );
                        inCrmQrcDto.getRemarksPojo().setReasonId( inCrmQrcDto.getRemarksPojo().getReasonId() );
                        session.save( inCrmQrcDto.getRemarksPojo() );
                        evicts.put( RemarksPojo.class.getName(), null );
                        crmServiceCode = CRMServiceCode.CRM001;
                    }
                    /*crmServiceCode = changeCustomerBrand( inCrmQrcDto, session, Calendar.getInstance().getTime(),
                                                          evicts );*/
                    if ( crmServiceCode == CRMServiceCode.CRM001 )
                        transaction.commit();
                }
                else
                {
                    crmServiceCode = CRMServiceCode.valueOf( inCrmQrcDto.getStatusCode() );
                }
            }
            catch ( HibernateException ex )
            {
                LOGGER.error( "HibernateException while saving customer plan renewal ", ex );
                CRMServiceUtils.rollback( transaction );
                CRMServiceUtils.closeSessionOnException( session );
            }
            catch ( Exception ex )
            {
                LOGGER.error( "Exception while saving customer plan renewal ", ex );
            }
            finally
            {
                CRMServiceUtils.closeSession( session );
                if ( StringUtils.equals( crmServiceCode.getStatusCode(), CRMServiceCode.CRM515.getStatusCode() ) )
                {
                    inCrmQrcDto.setBillingErrorCode( crmBillingDto.getBillingErrorCode() );
                }
                inCrmQrcDto.setStatusCode( crmServiceCode.getStatusCode() );
                inCrmQrcDto.setStatusDesc( crmServiceCode.getStatusDesc() );
                if ( crmServiceCode == CRMServiceCode.CRM001 )
                {
                    HibernateUtil.evictAll( evicts );
                }
            }
        }
        return inCrmQrcDto;
    }

    private CRMServiceCode changeCustomerBrand( CrmQrcDto inCrmQrcDto,
                                                Session session,
                                                Date now,
                                                Map<String, Long> inEvicts )
    {
        LOGGER.info( "customer brand :: " + inCrmQrcDto.getCustomerDetailsPojo().getBrand() + " >> plan brand :: "
                + inCrmQrcDto.getPlanMasterPojo().getBrand() );
        CRMServiceCode serviceCode = CRMServiceCode.CRM001;
        try
        {
            boolean sendAlert = true;
            String custId = inCrmQrcDto.getCustomerDetailsPojo().getCustomerId();
            if ( !StringUtils.equals( inCrmQrcDto.getCustomerDetailsPojo().getBrand(), inCrmQrcDto.getPlanMasterPojo()
                    .getBrand() ) )
            {
                // INITIA to NEXTRA
                if ( StringUtils.equals( inCrmQrcDto.getCustomerDetailsPojo().getBrand(), IAppConstants.BRAND_INITIA ) )
                {
                    CrmCustMyAccountPojo custMyAccountPojoDb = CRMServiceUtils.getDBValues( CrmCustMyAccountPojo.class,
                                                                                            "customerId", custId,
                                                                                            session );
                    if ( StringUtils.isValidObj( custMyAccountPojoDb ) )
                    {
                        custMyAccountPojoDb.setStatus( CRMStatusCode.NEW.getStatusCode() );
                        custMyAccountPojoDb.setLastModifiedBy( inCrmQrcDto.getUserId() );
                        custMyAccountPojoDb.setLastModifiedTime( now );
                        session.update( custMyAccountPojoDb );
                        inEvicts.put( CrmCustMyAccountPojo.class.getName(), null );
                    }
                    else
                    {
                        long recId = createMyAccount( inCrmQrcDto, session );
                        sendAlert = recId > 0;
                    }
                    CrmCustomerDetailsPojo customerDetailsPojoDb = CRMServiceUtils
                            .getDBValues( CrmCustomerDetailsPojo.class, "customerId", custId, session );
                    customerDetailsPojoDb.setBrand( inCrmQrcDto.getPlanMasterPojo().getBrand() );
                    customerDetailsPojoDb.setLastModifiedBy( inCrmQrcDto.getUserId() );
                    customerDetailsPojoDb.setLastModifiedTime( now );
                    session.update( customerDetailsPojoDb );
                    inEvicts.put( CrmCustomerDetailsPojo.class.getName(), null );
                    if ( sendAlert )
                    {
                        CRMServiceUtils.sendAlerts( CRMEvents.MY_ACCOUNT_CREATION, CRMRequestType.CUSTOMER_ID, custId,
                                                    null );
                    }
                }
                else
                {
                    CrmCustMyAccountPojo custMyAccountPojoDb = CRMServiceUtils.getDBValues( CrmCustMyAccountPojo.class,
                                                                                            "customerId", custId,
                                                                                            session );
                    if ( StringUtils.isValidObj( custMyAccountPojoDb ) )
                    {
                        custMyAccountPojoDb.setStatus( CRMStatusCode.INACTIVE.getStatusCode() );
                        custMyAccountPojoDb.setLastModifiedBy( inCrmQrcDto.getUserId() );
                        custMyAccountPojoDb.setLastModifiedTime( now );
                        session.update( custMyAccountPojoDb );
                        inEvicts.put( CrmCustMyAccountPojo.class.getName(), null );
                    }
                    CrmCustomerDetailsPojo customerDetailsPojoDb = CRMServiceUtils
                            .getDBValues( CrmCustomerDetailsPojo.class, "customerId", custId, session );
                    customerDetailsPojoDb.setBrand( inCrmQrcDto.getPlanMasterPojo().getBrand() );
                    customerDetailsPojoDb.setLastModifiedBy( inCrmQrcDto.getUserId() );
                    customerDetailsPojoDb.setLastModifiedTime( now );
                    session.update( customerDetailsPojoDb );
                    inEvicts.put( CrmCustomerDetailsPojo.class.getName(), null );
                }
            }
        }
        catch ( Exception ex )
        {
            LOGGER.error( "exception while checking for customer brand change", ex );
            serviceCode = CRMServiceCode.CRM323;
        }
        return serviceCode;
    }

    private long createMyAccount( CrmQrcDto inQrcDto, Session session )
        throws Exception
    {
        Long generatedId = 0l;
        String status = CRMStatusCode.NEW.getStatusCode();
        CrmCustomerDetailsPojo customerDetailsPojo = inQrcDto.getCustomerDetailsPojo();
        if ( StringUtils.equals( customerDetailsPojo.getBrand(), IAppConstants.BRAND_INITIA ) )
        {
            status = CRMStatusCode.INACTIVE.getStatusCode();
        }
        CrmCustMyAccountPojo crmCustMyAccountPojo = new CrmCustMyAccountPojo();
        crmCustMyAccountPojo.setCustomerId( customerDetailsPojo.getCustomerId() );
        crmCustMyAccountPojo.setPassword( EncryptionUtil.encrypt( CRMServiceUtils.getMyAccountRandomPassword() ) );
        crmCustMyAccountPojo.setCreatedTime( Calendar.getInstance().getTime() );
        crmCustMyAccountPojo.setCreatedBy( inQrcDto.getUserId() );
        crmCustMyAccountPojo.setStatus( status );
        generatedId = (Long) session.save( crmCustMyAccountPojo );
        LOGGER.info( "Generated My Account ID '" + generatedId + "' for Customer ID: "
                + customerDetailsPojo.getCustomerId() );
        HibernateUtil.evictAll( CrmCustMyAccountPojo.class.getName(), null, null );
        return generatedId;
    }

    @Override
    public CrmQrcDto setBillPdfPaths( CrmQrcDto inCrmQrcDto )
    {
        Session session = null;
        CrmInvoiceDetailsPojo crmInvoiceDetailsPojo = null;
        try
        {
            session = HibernateUtil.getCurrentSession();
            if ( CommonValidator.isValidCollection( inCrmQrcDto.getInvoiceDetailsPojos() ) )
            {
                for ( CrmInvoiceDetailsPojo detailsPojo : inCrmQrcDto.getInvoiceDetailsPojos() )
                {
                    crmInvoiceDetailsPojo = (CrmInvoiceDetailsPojo) session.get( CrmInvoiceDetailsPojo.class,
                                                                                 detailsPojo.getBillNumber() );
                    if ( StringUtils.isValidObj( crmInvoiceDetailsPojo ) )
                    {
                        detailsPojo.setNopasswordPdfFile( crmInvoiceDetailsPojo.getNopasswordPdfFile() );
                        detailsPojo.setPasswordPdfFile( crmInvoiceDetailsPojo.getPasswordPdfFile() );
                        LOGGER.info( "NopasswordPdfFile::" + crmInvoiceDetailsPojo.getNopasswordPdfFile() );
                    }
                }
            }
        }
        catch ( Exception ex )
        {
            LOGGER.error( "Getting Error while setBillPdfPaths: ", ex );
        }
        finally
        {
            CRMServiceUtils.closeSession( session );
        }
        return inCrmQrcDto;
    }

    @Override
    public CrmBillingDto getAdditionalDetails( String inCustomerId, String inServiceType )
    {
        boolean isPostPaid = StringUtils.isValidObj( inServiceType )
                && StringUtils.equals( inServiceType, CRMDisplayListConstants.POST_PAID.getCode() ) ? true : false;
        return getCrmBillingManager().getCustomerUsageDetail( inCustomerId, isPostPaid );
    }

    @Override
    public CrmQrcDto bulkUpdateCustomerStatus( CrmQrcDto inCrmQrcDto )
    {
        CrmQrcCommonPojo crmQrcCommonPojo = null;
        CrmBillingDto billingDto = null;
        String billingCode = "";
        CRMServiceCode crmServiceCode = CRMServiceCode.CRM999;
        try
        {
            MDC.put( "KEY", "CUSTID:" + inCrmQrcDto.getCustomerId() + ", USERID:" + inCrmQrcDto.getUserId() );
            crmQrcCommonPojo = new CrmQrcCommonPojo();
            Map<String, String> failedCustomerMapList = new HashMap<String, String>();
            if ( CommonValidator.isValidCollection( inCrmQrcDto.getCustomerDetailsPojos() ) )
            {
                for ( CrmCustomerDetailsPojo crmCustFileDetailsPojo : inCrmQrcDto.getCustomerDetailsPojos() )
                {
                    LOGGER.info( "CustomerId::" + crmCustFileDetailsPojo.getCustomerId() + ",Status::"
                            + crmCustFileDetailsPojo.getStatus() );
                    CrmCustomerDetailsPojo crmCustomerDetailsPojo = CRMServiceUtils
                            .getDBValues( CrmCustomerDetailsPojo.class, "customerId",
                                          crmCustFileDetailsPojo.getCustomerId() );
                    if ( StringUtils.isValidObj( crmCustomerDetailsPojo ) )
                    {
                        if ( StringUtils.equals( crmCustFileDetailsPojo.getStatus(),
                                                 CRMStatusCode.BARRED.getStatusCode() ) )
                        {
                            if ( StringUtils.equals( crmCustomerDetailsPojo.getStatus(),
                                                     CRMStatusCode.ACTIVE.getStatusCode() ) )
                            {
                                for ( CrmRcaReasonPojo crmRcaReasonPojo : inCrmQrcDto.getCrmRcaReasonPojos() )
                                {
                                    if ( crmRcaReasonPojo.getCategoryId() == inCrmQrcDto.getRemarksPojo().getReasonId() )
                                    {
                                        crmQrcCommonPojo.setRcaReasonPojo( crmRcaReasonPojo );
                                        break;
                                    }
                                }
                                processBarUnbarCustService( inCrmQrcDto, crmCustomerDetailsPojo, crmQrcCommonPojo,
                                                            failedCustomerMapList );
                            }
                            else
                            {
                                LOGGER.info( "customer is not active" );
                                failedCustomerMapList.put( crmCustFileDetailsPojo.getCustomerId(), "Unable to "
                                        + CRMStatusCode.BARRED.getStatusDesc() + " customer. Customer status is "
                                        + CRMStatusCode.getStatus( crmCustomerDetailsPojo.getStatus() ) );
                                continue;
                            }
                        }
                        else if ( StringUtils.equals( crmCustFileDetailsPojo.getStatus(),
                                                      CRMStatusCode.UNBARRED.getStatusCode() ) )
                        {
                            if ( StringUtils.equals( crmCustomerDetailsPojo.getStatus(),
                                                     CRMStatusCode.BARRED.getStatusCode() ) )
                            {
                                crmQrcCommonPojo.setAction( CRMCustomerActivityActions.UNBARRING_EXCEPTION
                                        .getActionDesc() );
                                for ( CrmRcaReasonPojo crmRcaReasonPojo : inCrmQrcDto.getCrmRcaReasonPojos() )
                                {
                                    if ( crmRcaReasonPojo.getCategoryId() == inCrmQrcDto.getRemarksPojo().getReasonId() )
                                    {
                                        crmQrcCommonPojo.setRcaReasonPojo( crmRcaReasonPojo );
                                        break;
                                    }
                                }
                                processBarUnbarCustService( inCrmQrcDto, crmCustomerDetailsPojo, crmQrcCommonPojo,
                                                            failedCustomerMapList );
                            }
                            else
                            {
                                LOGGER.info( "customer is not barred" );
                                failedCustomerMapList.put( crmCustFileDetailsPojo.getCustomerId(), "Unable to "
                                        + CRMStatusCode.UNBARRED.getStatusDesc() + " customer. Customer status is "
                                        + CRMStatusCode.getStatus( crmCustomerDetailsPojo.getStatus() ) );
                                continue;
                            }
                        }
                        else
                        {
                            inCrmQrcDto.setCustomerRecordId( crmCustomerDetailsPojo.getRecordId() );
                            inCrmQrcDto.setCustomerDetailsPojo( crmCustFileDetailsPojo );
                            inCrmQrcDto.setCustomerId( crmCustFileDetailsPojo.getCustomerId() );
                            if ( StringUtils.equals( crmCustFileDetailsPojo.getStatus(),
                                                     crmCustomerDetailsPojo.getStatus() ) )
                            {
                                failedCustomerMapList.put( crmCustFileDetailsPojo.getCustomerId(),
                                                           "Customer status is already in-"
                                                                   + CRMStatusCode.getStatus( crmCustomerDetailsPojo
                                                                           .getStatus() ) );
                                continue;
                            }
                            else if ( StringUtils.equals( crmCustFileDetailsPojo.getStatus(),
                                                          CRMStatusCode.SC.getStatusCode() ) )
                            {
                                billingDto = getAdditionalDetails( crmCustomerDetailsPojo.getCustomerId(),
                                                                   crmCustomerDetailsPojo.getServiceType() );
                                if ( StringUtils.equals( crmCustomerDetailsPojo.getServiceType(),
                                                         CRMDisplayListConstants.POST_PAID.getCode() )
                                        && StringUtils.isValidObj( billingDto ) )
                                {
                                    if ( StringUtils.isValidObj( billingDto.getCustAdditionalDetails() )
                                            && billingDto.getCustAdditionalDetails().getBalance() > 0 )
                                    {
                                        failedCustomerMapList.put( crmCustFileDetailsPojo.getCustomerId(),
                                                                   "You are not allowed for safe custody because you have outstanding balance Rs."
                                                                           + IAppConstants.SPACE
                                                                           + billingDto.getCustAdditionalDetails()
                                                                                   .getBalance() );
                                        continue;
                                    }
                                    else
                                    {
                                        inCrmQrcDto = updateCustomerStatus( inCrmQrcDto );
                                    }
                                }
                                else if ( StringUtils.equals( crmCustomerDetailsPojo.getServiceType(),
                                                              CRMDisplayListConstants.PRE_PAID.getCode() )
                                        && StringUtils.isValidObj( billingDto ) )
                                {
                                    if ( StringUtils.isValidObj( billingDto.getCustAdditionalDetails() )
                                            && billingDto.getCustAdditionalDetails().getBalance() < 0 )
                                    {
                                        failedCustomerMapList.put( crmCustFileDetailsPojo.getCustomerId(),
                                                                   "You are not allowed for safe custody because you have outstanding balance Rs."
                                                                           + IAppConstants.SPACE
                                                                           + billingDto.getCustAdditionalDetails()
                                                                                   .getBalance() );
                                        continue;
                                    }
                                    else
                                    {
                                        inCrmQrcDto = updateCustomerStatus( inCrmQrcDto );
                                    }
                                }
                            }
                            else if ( StringUtils.equals( crmCustFileDetailsPojo.getStatus(),
                                                          CRMStatusCode.TDC.getStatusCode() ) )
                            {
                                if ( StringUtils.equals( crmCustomerDetailsPojo.getStatus(),
                                                         CRMStatusCode.ACTIVE.getStatusCode() )
                                        || ( StringUtils.equals( crmCustomerDetailsPojo.getStatus(),
                                                                 CRMStatusCode.BARRED.getStatusCode() ) ) )
                                {
                                    inCrmQrcDto = updateCustomerStatus( inCrmQrcDto );
                                }
                                else
                                {
                                    failedCustomerMapList.put( crmCustFileDetailsPojo.getCustomerId(),
                                                               "Only Active/Barred customers allowed for "
                                                                       + IAppConstants.SPACE
                                                                       + CRMStatusCode
                                                                               .getStatus( crmCustFileDetailsPojo
                                                                                       .getStatus() ) );
                                    continue;
                                }
                            }
                            else if ( StringUtils.equals( crmCustFileDetailsPojo.getStatus(),
                                                          CRMStatusCode.PDC.getStatusCode() ) )
                            {
                                if ( StringUtils.equals( crmCustomerDetailsPojo.getStatus(),
                                                         CRMStatusCode.SC.getStatusCode() )
                                        || ( StringUtils.equals( crmCustomerDetailsPojo.getStatus(),
                                                                 CRMStatusCode.TDC.getStatusCode() ) ) )
                                {
                                    inCrmQrcDto = updateCustomerStatus( inCrmQrcDto );
                                }
                                else
                                {
                                    failedCustomerMapList.put( crmCustFileDetailsPojo.getCustomerId(),
                                                               "Only SC/TDC customers allowed for"
                                                                       + IAppConstants.SPACE
                                                                       + CRMStatusCode
                                                                               .getStatus( crmCustFileDetailsPojo
                                                                                       .getStatus() ) );
                                    continue;
                                }
                            }
                        }
                        if ( !StringUtils.equals( inCrmQrcDto.getStatusCode(), CRMServiceCode.CRM001.getStatusCode() ) )
                        {
                            if ( StringUtils.isNotBlank( inCrmQrcDto.getBillingErrorCode() ) )
                            {
                                billingCode = inCrmQrcDto.getBillingErrorCode();
                            }
                            failedCustomerMapList.put( crmCustFileDetailsPojo.getCustomerId(),
                                                       inCrmQrcDto.getStatusDesc() + IAppConstants.SPACE + billingCode );
                        }
                    }
                    else
                    {
                        failedCustomerMapList.put( crmCustFileDetailsPojo.getCustomerId(), "Customer not exist" );
                    }
                }
                inCrmQrcDto.setFailedCustomerMapList( failedCustomerMapList );
                crmServiceCode = CRMServiceCode.CRM001;
            }
        }
        catch ( Exception ex )
        {
            LOGGER.error( "Getting Error while update customer status: ", ex );
            crmServiceCode = CRMServiceCode.CRM999;
        }
        finally
        {
            inCrmQrcDto.setStatusCode( crmServiceCode.getStatusCode() );
            inCrmQrcDto.setStatusDesc( crmServiceCode.getStatusDesc() );
            MDC.remove( "KEY" );
        }
        return inCrmQrcDto;
    }

    private void processBarUnbarCustService( CrmQrcDto inCrmQrcDto,
                                             CrmCustomerDetailsPojo crmCustomerDetailsPojo,
                                             CrmQrcCommonPojo crmQrcCommonPojo,
                                             Map<String, String> failedCustomerMapList )
    {
        String billingCode = "";
        crmQrcCommonPojo.setCustomerRecordId( crmCustomerDetailsPojo.getRecordId() );
        crmQrcCommonPojo.setCustomerId( crmCustomerDetailsPojo.getCustomerId() );
        crmQrcCommonPojo.setRemarksPojo( inCrmQrcDto.getRemarksPojo() );
        inCrmQrcDto.setCrmQrcCommonPojo( crmQrcCommonPojo );
        inCrmQrcDto = barringUnbarringCustService( inCrmQrcDto );
        if ( !StringUtils.equals( inCrmQrcDto.getStatusCode(), CRMServiceCode.CRM001.getStatusCode() ) )
        {
            if ( StringUtils.isNotBlank( inCrmQrcDto.getBillingErrorCode() ) )
            {
                billingCode = inCrmQrcDto.getBillingErrorCode();
            }
            if ( StringUtils.equals( crmCustomerDetailsPojo.getStatus(), CRMStatusCode.ACTIVE.getStatusCode() ) )
            {
                failedCustomerMapList.put( crmCustomerDetailsPojo.getCustomerId(), inCrmQrcDto.getStatusDesc()
                        + IAppConstants.SPACE + billingCode );
            }
            else if ( StringUtils.equals( crmCustomerDetailsPojo.getStatus(), CRMStatusCode.BARRED.getStatusCode() ) )
            {
                failedCustomerMapList.put( crmCustomerDetailsPojo.getCustomerId(),
                                           "Can not unbarr the services, as Customer is in Unbarred Exception List."
                                                   + IAppConstants.SPACE + billingCode );
            }
        }
    }

    @Override
    public CrmQrcDto ticketIDProfileSearch( CrmQrcDto inCrmQrcDto )
    {
        SessionFactory sessionFactory = null;
        Session session = null;
        Criteria criteria = null;
        try
        {
            MDC.put( "KEY", "CUSTID:" + inCrmQrcDto.getCustomerId() + ", USERID:" + inCrmQrcDto.getUserId() );
            sessionFactory = HibernateUtil.getSessionFactory();
            session = sessionFactory.openSession();
            criteria = session.createCriteria( CrmSrTicketsPojo.class );
            if ( StringUtils.isNotBlank( inCrmQrcDto.getCrmSrTicketsPojo().getSrId() ) )
            {
                LOGGER.info( "Ticket ID::" + inCrmQrcDto.getCrmSrTicketsPojo().getSrId() );
                criteria.add( Restrictions.ilike( "srId", inCrmQrcDto.getCrmSrTicketsPojo().getSrId(),
                                                  MatchMode.ANYWHERE ) );
            }
            criteria.setCacheable( true );
            criteria.setCacheRegion( CrmSrTicketsPojo.class.getName() );
            List<CrmSrTicketsPojo> crmSrTicketsPojos = criteria.list();
            if ( CommonValidator.isValidCollection( crmSrTicketsPojos ) )
            {
                LOGGER.info( "TicketsPojo Size::" + crmSrTicketsPojos.size() );
                for ( CrmSrTicketsPojo crmSrTicketsPojo : crmSrTicketsPojos )
                {
                    criteria = session.createCriteria( CrmInboxPojo.class );
                    criteria.add( Restrictions.eq( "mappingId", crmSrTicketsPojo.getSrId() ) )
                            .add( Restrictions.eq( "status", CRMStatusCode.ACTIVE.getStatusCode() ) );
                    criteria.setCacheable( true );
                    criteria.setCacheRegion( CrmInboxPojo.class.getName() );
                    List<CrmInboxPojo> inboxList = criteria.list();
                    if ( CommonValidator.isValidCollection( inboxList ) )
                    {
                        if ( StringUtils.isNotEmpty( inboxList.get( 0 ).getUserId() ) )
                        {
                            crmSrTicketsPojo.setCurrentUser( inboxList.get( 0 ).getUserId() );
                        }
                        else
                            crmSrTicketsPojo.setCurrentUser( "-" );
                    }
                    else
                        crmSrTicketsPojo.setCurrentUser( "-" );
                }
                inCrmQrcDto.setCrmSrTicketsPojos( crmSrTicketsPojos );
                inCrmQrcDto.setStatusCode( CRMServiceCode.CRM001.getStatusCode() );
                inCrmQrcDto.setStatusDesc( CRMServiceCode.CRM001.getStatusDesc() );
            }
            else
            {
                inCrmQrcDto.setStatusCode( CRMServiceCode.CRM042.getStatusCode() );
                inCrmQrcDto.setStatusDesc( CRMServiceCode.CRM042.getStatusDesc() );
            }
        }
        catch ( Exception ex )
        {
            LOGGER.error( "Exception while Customer profile search for ticket : ", ex );
            inCrmQrcDto.setStatusCode( CRMServiceCode.CRM999.getStatusCode() );
            inCrmQrcDto.setStatusDesc( CRMServiceCode.CRM999.getStatusDesc() );
        }
        finally
        {
            CRMServiceUtils.closeSession( session );
            MDC.remove( "KEY" );
        }
        return inCrmQrcDto;
    }

    @Override
    public boolean updateCustomerPlanDetailsInCRM( String inMappingId, String inAuthority )
    {
        LOGGER.info( "QrcManagerDaoImpl, updateCustomerPlanDetailsInCRM" );
        CrmCustAdditionalDetails custAdditionalDetails = null;
        CrmPlanDetailsPojo crmPlanDetailsPojo = null;
        CrmBillingDto crmBillingDto = null;
        CrmCapDto crmCapDto = null;
        SessionFactory sessionFactory = null;
        Session session = null;
        Map<String, Long> evicts = new HashMap<String, Long>();
        try
        {
            crmBillingDto = getAdditionalDetails( inMappingId, null ); //needs only base n addon plan code.
            if ( StringUtils.isValidObj( crmBillingDto ) )
            {
                custAdditionalDetails = crmBillingDto.getCustAdditionalDetails();
            }
            crmCapDto = new CAPOperationDaoImpl().getCustomerDetails( 0, null, inMappingId );
            if ( StringUtils.isValidObj( crmCapDto ) && StringUtils.isValidObj( crmCapDto.getCustomerDetailsPojo() )
                    && CommonValidator.isValidCollection( crmCapDto.getCustomerDetailsPojo().getCrmPlanDetailses() ) )
            {
                for ( CrmPlanDetailsPojo planDetailsPojo : crmCapDto.getCustomerDetailsPojo().getCrmPlanDetailses() )
                {
                    crmPlanDetailsPojo = planDetailsPojo;
                    break;
                }
            }
            if ( StringUtils.isValidObj( custAdditionalDetails ) && StringUtils.isValidObj( crmPlanDetailsPojo ) )
            {
                sessionFactory = HibernateUtil.getSessionFactory();
                session = sessionFactory.openSession();
                crmPlanDetailsPojo.setAddOnPlanCode( custAdditionalDetails.getAddonPlanCode() );
                crmPlanDetailsPojo.setBasePlanCode( custAdditionalDetails.getPlanCode() );
                crmPlanDetailsPojo.setLastModifiedTime( Calendar.getInstance().getTime() );
                crmPlanDetailsPojo.setLastModifiedBy( inAuthority );
                session.merge( crmPlanDetailsPojo );
                CrmPlanMasterPojo planMasterPojo = CRMServiceUtils.getDBValues( CrmPlanMasterPojo.class, "planCode",
                                                                                custAdditionalDetails.getPlanCode(),
                                                                                session );
                CrmQrcDto qrcDto = new CrmQrcDto();
                qrcDto.setCustomerDetailsPojo( crmCapDto.getCustomerDetailsPojo() );
                qrcDto.setPlanMasterPojo( planMasterPojo );
                qrcDto.setUserId( inAuthority );
                if ( changeCustomerBrand( qrcDto, session, Calendar.getInstance().getTime(), evicts ) == CRMServiceCode.CRM001 )
                    return true;
            }
        }
        catch ( Exception ex )
        {
            LOGGER.error( "Exception while updating customer plan in CRM ", ex );
        }
        finally
        {
            CRMServiceUtils.closeSession( session );
            HibernateUtil.evictAll( evicts );
        }
        return false;
    }

    private CRMServiceCode allowedWaiver( CrmCustWaiverPojo inCrmCustWaiverPojo, Session session )
    {
        CRMServiceCode code = CRMServiceCode.CRM001;
        LOGGER.info( "Requested Pojo" + inCrmCustWaiverPojo );
        List<CrmCustWaiverPojo> listCustomerWaiver = getWaiverList( inCrmCustWaiverPojo, session );
        if ( StringUtils.isValidObj( listCustomerWaiver ) && !listCustomerWaiver.isEmpty() )
        {
            LOGGER.info( "list size ::" + listCustomerWaiver.size() );
            code = CRMServiceCode.CRM432;
        }
        if ( StringUtils
                .equals( inCrmCustWaiverPojo.getWaiverType(), QRCRolCategories.GOODWILL_WAIVER.getSubCategory() )
                && !StringUtils.equals( code.getStatusCode(), CRMServiceCode.CRM432.getStatusCode() ) )
        {
            Date currentDate = new Date();
            LOGGER.info( "Second Call::::::" );
            CrmCustWaiverPojo pojo = new CrmCustWaiverPojo();
            pojo.setCustomerId( inCrmCustWaiverPojo.getCustomerId() );
            pojo.setWaiverType( inCrmCustWaiverPojo.getWaiverType() );
            List<CrmCustWaiverPojo> lastPostedPojos = getWaiverList( pojo, session );
            if ( StringUtils.isValidObj( lastPostedPojos ) && lastPostedPojos.size() > 1 )
            {
                int count = 1;
                for ( CrmCustWaiverPojo lastPostedPojo : lastPostedPojos )
                {
                    long days = ( ( currentDate.getTime() - lastPostedPojo.getCreatedTime().getTime() ) / ( 1000 * 60 * 60 * 24 ) );
                    LOGGER.info( "days :::" + days );
                    if ( days < 180 && count > 1 )
                    {
                        code = CRMServiceCode.CRM431;
                        break;
                    }
                    count++;
                }
            }
        }
        return code;
    }

    @Override
    public CrmQrcDto getBillingPlanRequestList( CrmQrcDto inCrmQrcDto )
    {
        SessionFactory sessionFactory = null;
        Session session = null;
        Criteria criteria = null;
        CRMServiceCode serviceCode = CRMServiceCode.CRM997;
        try
        {
            MDC.put( "KEY", "CUSTID:" + inCrmQrcDto.getCustomerId() + ", USERID:" + inCrmQrcDto.getUserId() );
            sessionFactory = HibernateUtil.getSessionFactory();
            session = sessionFactory.openSession();
            if ( StringUtils.isValidObj( inCrmQrcDto ) )
            {
                criteria = session.createCriteria( CrmBillingPlanRequestPojo.class );
                criteria.add( Restrictions.eq( "status", CRMStatusCode.PENDING.getStatusCode() ) );
                inCrmQrcDto.setBillingPlanRequestPojos( criteria.list() );
                serviceCode = CRMServiceCode.CRM001;
            }
        }
        catch ( Exception ex )
        {
            LOGGER.error( "Getting Error in getBillingPlanRequest method", ex );
            serviceCode = CRMServiceCode.CRM999;
        }
        finally
        {
            CRMServiceUtils.closeSession( session );
            MDC.remove( "KEY" );
        }
        inCrmQrcDto.setStatusCode( serviceCode.getStatusCode() );
        inCrmQrcDto.setStatusDesc( serviceCode.getStatusDesc() );
        return inCrmQrcDto;
    }

    @Override
    public CrmQrcDto updateBillingPlanRequest( CrmQrcDto inCrmQrcDto )
    {
        SessionFactory sessionFactory = null;
        Session session = null;
        Transaction transaction = null;
        CrmBillingPlanRequestPojo billingPlanRequestPojo = null;
        CRMServiceCode serviceCode = CRMServiceCode.CRM997;
        try
        {
            MDC.put( "KEY", "CUSTID:" + inCrmQrcDto.getCustomerId() + ", USERID:" + inCrmQrcDto.getUserId() );
            sessionFactory = HibernateUtil.getSessionFactory();
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            if ( StringUtils.isValidObj( inCrmQrcDto.getBillingPlanRequestPojo() ) )
            {
                billingPlanRequestPojo = inCrmQrcDto.getBillingPlanRequestPojo();
                inCrmQrcDto.setCustomerId( billingPlanRequestPojo.getCustomerId() );
                updatePlansFromBilling( inCrmQrcDto );
                serviceCode = CRMServiceCode.valueOf( inCrmQrcDto.getStatusCode() );
                if ( StringUtils.equals( inCrmQrcDto.getStatusCode(), CRMServiceCode.CRM001.getStatusCode() )
                        || StringUtils.equals( inCrmQrcDto.getStatusCode(), CRMServiceCode.CRM619.getStatusCode() )
                        && StringUtils.isValidObj( inCrmQrcDto.getCrmPlanDetailsPojo() )
                        && billingPlanRequestPojo.getRecordId() > 0 )
                {
                    if ( StringUtils.equals( inCrmQrcDto.getStatusCode(), CRMServiceCode.CRM619.getStatusCode() ) )
                    {
                        billingPlanRequestPojo.setStatus( CRMStatusCode.MATCHED.getStatusCode() );
                    }
                    else
                        billingPlanRequestPojo.setStatus( CRMStatusCode.PROCESSED.getStatusCode() );
                    billingPlanRequestPojo.setLastModifiedBy( inCrmQrcDto.getUserId() );
                    billingPlanRequestPojo.setLastModifiedTime( Calendar.getInstance().getTime() );
                    billingPlanRequestPojo.setAddOnPlanCode( inCrmQrcDto.getCrmPlanDetailsPojo().getAddOnPlanCode() );
                    billingPlanRequestPojo.setBasePlanCode( inCrmQrcDto.getCrmPlanDetailsPojo().getBasePlanCode() );
                    session.update( billingPlanRequestPojo );
                    transaction.commit();
                    serviceCode = CRMServiceCode.CRM001;
                }
            }
        }
        catch ( Exception ex )
        {
            LOGGER.error( "Getting Error in updateBillingPlanRequest method", ex );
            CRMServiceUtils.rollback( transaction );
            serviceCode = CRMServiceCode.CRM999;
        }
        finally
        {
            CRMServiceUtils.closeSession( session );
            MDC.remove( "KEY" );
        }
        inCrmQrcDto.setStatusCode( serviceCode.getStatusCode() );
        inCrmQrcDto.setStatusDesc( serviceCode.getStatusDesc() );
        return inCrmQrcDto;
    }

    @Override
    public CrmQrcDto cancelPlanMigrationRequest( CrmQrcDto inCrmQrcDto )
    {
        cancelOldRequestInBilllingIfExist( inCrmQrcDto );
        if ( StringUtils.isEmpty( inCrmQrcDto.getBillingErrorCode() ) )
        {
            inCrmQrcDto.setCrmSrTicketsPojo( inCrmQrcDto.getCrmSrTicketsPojos().get( 0 ) );
            try
            {
                CrmTicketHistoryPojo ticketHistory = new CrmTicketHistoryPojo();
                ticketHistory.setCreatedBy( inCrmQrcDto.getUserId() );
                ticketHistory.setAction( CrmActionEnum.RESOLVED.getActionCode() );
                ticketHistory.setRemarks( inCrmQrcDto.getRemarksPojo().getRemarks() );
                inCrmQrcDto.setTicketHistory( ticketHistory );
                inCrmQrcDto = CRMServicesProxy
                        .getInstance()
                        .getCrmQrcService( IGlobalConstants.REMOTE, IGlobalConstants.APP )
                        .ticketOperations( CrmActionEnum.RESOLVED.getActionCode(),
                                           CrmSrTicketsPojo.class.getSimpleName(), inCrmQrcDto );
                if ( StringUtils.equals( inCrmQrcDto.getStatusCode(), CRMServiceCode.CRM001.getStatusCode() )
                        && StringUtils.isValidObj( inCrmQrcDto.getRemarksPojo() ) )
                {
                    CRMServiceCode crmServiceCode = CRMServiceCode.CRM997;
                    Session session = null;
                    Transaction transaction = null;
                    try
                    {
                        session = HibernateUtil.getCurrentSession();
                        transaction = session.beginTransaction();
                        inCrmQrcDto.getRemarksPojo().setMappingId( inCrmQrcDto.getCustomerId() );
                        inCrmQrcDto.getRemarksPojo().setActions( "Cancel Plan Request" );
                        inCrmQrcDto.getRemarksPojo().setCreatedBy( inCrmQrcDto.getUserId() );
                        inCrmQrcDto.getRemarksPojo().setCreatedTime( Calendar.getInstance().getTime() );
                        inCrmQrcDto.getRemarksPojo().setMappingType( CRMRCAReason.QRC.getStatusCode() );
                        inCrmQrcDto.getRemarksPojo().setReasonId( inCrmQrcDto.getRemarksPojo().getReasonId() );
                        session.save( inCrmQrcDto.getRemarksPojo() );
                        transaction.commit();
                        crmServiceCode = CRMServiceCode.CRM001;
                    }
                    catch ( Exception ex )
                    {
                        LOGGER.error( "Exception while saving customer AddOn Added Operation ", ex );
                        crmServiceCode = CRMServiceCode.CRM999;
                    }
                    finally
                    {
                        CRMServiceUtils.rollback( transaction );
                        CRMServiceUtils.closeSession( session );
                        inCrmQrcDto.setStatusCode( crmServiceCode.getStatusCode() );
                        inCrmQrcDto.setStatusDesc( crmServiceCode.getStatusDesc() );
                        HibernateUtil.evictAll( RemarksPojo.class.getName(), null, null );
                    }
                }
            }
            catch ( SOAPException ex )
            {
                LOGGER.error( "Unable to change the status of Ticket" + inCrmQrcDto.getCrmSrTicketsPojo(), ex );
            }
        }
        return inCrmQrcDto;
    }

    private List<CrmCustWaiverPojo> getWaiverList( CrmCustWaiverPojo inCrmCustWaiverPojo, Session session )
    {
        Criteria criteria = session.createCriteria( CrmCustWaiverPojo.class );
        if ( StringUtils.isNotBlank( inCrmCustWaiverPojo.getCustomerId() ) )
        {
            criteria.add( Restrictions.eq( "customerId", inCrmCustWaiverPojo.getCustomerId() ) );
        }
        if ( StringUtils.isNotBlank( inCrmCustWaiverPojo.getBillNo() ) )
        {
            criteria.add( Restrictions.eq( "billNo", inCrmCustWaiverPojo.getBillNo() ) );
        }
        if ( StringUtils.isNotBlank( inCrmCustWaiverPojo.getWaiverType() ) )
        {
            criteria.add( Restrictions.eq( "waiverType", inCrmCustWaiverPojo.getWaiverType() ) );
        }
        criteria.addOrder( Order.desc( "createdTime" ) );
        criteria.setCacheable( true );
        criteria.setCacheRegion( CrmCustWaiverPojo.class.getName() );
        List<CrmCustWaiverPojo> listCustomerWaiver = criteria.list();
        return listCustomerWaiver;
    }

    @Override
    public CrmQrcDto getPOAddOnPlanMigrationPolicy( CrmQrcDto inCrmQrcDto )
    {
        LOGGER.info( "Inside QrcManagerDaoImpl : getPOAddOnPlanMigrationPolicy" );
        CRMServiceCode crmServiceCode = CRMServiceCode.CRM999;
        Session session = null;
        Criteria criteria = null;
        CrmPlanMasterPojo oldBasePlanPOJO = null;
        CrmPlanMasterPojo newAddonPlanPOJO = null;
        CrmPlanMasterPojo oldAddonPlanPOJO = null;
        //CrmPlanMasterPojo newAddonPlanPOJO = null;
        try
        {
            session = HibernateUtil.getCurrentSession();
            if ( StringUtils.isNotEmpty( inCrmQrcDto.getOldBasePlanCode() ) )
            {
                criteria = session.createCriteria( CrmPlanMasterPojo.class );
                criteria.add( Restrictions.eq( "planCode", inCrmQrcDto.getOldBasePlanCode() ) );
                criteria.setCacheable( true );
                criteria.setCacheRegion( CrmPlanMasterPojo.class.getName() );
                oldBasePlanPOJO = CommonValidator.isValidCollection( criteria.list() ) ? (CrmPlanMasterPojo) criteria
                        .list().get( 0 ) : null;
            }
            if ( StringUtils.isNotEmpty( inCrmQrcDto.getNewAddonPlanCode() ) )
            {
                criteria = session.createCriteria( CrmPlanMasterPojo.class );
                criteria.add( Restrictions.eq( "planCode", inCrmQrcDto.getNewAddonPlanCode() ) );
                criteria.setCacheable( true );
                criteria.setCacheRegion( CrmPlanMasterPojo.class.getName() );
                newAddonPlanPOJO = (CrmPlanMasterPojo) criteria.list().get( 0 );
            }
            if ( StringUtils.isNotEmpty( inCrmQrcDto.getOldAddonPlanCode() ) )
            {
                criteria = session.createCriteria( CrmPlanMasterPojo.class );
                criteria.setCacheable( true );
                criteria.setCacheRegion( CrmPlanMasterPojo.class.getName() );
                criteria.add( Restrictions.eq( "planCode", inCrmQrcDto.getOldAddonPlanCode() ) );
                oldAddonPlanPOJO = CommonValidator.isValidCollection( criteria.list() ) ? (CrmPlanMasterPojo) criteria
                        .list().get( 0 ) : null;
            }
            if ( StringUtils.isValidObj( oldBasePlanPOJO )
                    && StringUtils.isValidObj( inCrmQrcDto.getCustAdditionalDetails() ) )
            {
                //Calling Unlimited to Unlimited
                double actualUsage = inCrmQrcDto.getCustAdditionalDetails().getUsedVolumeQuota();
                LOGGER.info( "Actual Usage:: " + actualUsage );
                List<ContentPojo> policyAdviceList = getPOAddOnU2UPlanChangePolicyAdvice( oldBasePlanPOJO,
                                                                                          newAddonPlanPOJO,
                                                                                          oldAddonPlanPOJO, actualUsage );
                inCrmQrcDto.setContentPojos( policyAdviceList );
            }
            crmServiceCode = CRMServiceCode.CRM001;
        }
        catch ( Exception ex )
        {
            LOGGER.error( "Exception while getPOPlanMigrationPolicy ", ex );
        }
        finally
        {
            CRMServiceUtils.closeSession( session );
            inCrmQrcDto.setStatusCode( crmServiceCode.getStatusCode() );
            inCrmQrcDto.setStatusDesc( crmServiceCode.getStatusDesc() );
        }
        return inCrmQrcDto;
    }

    private List<ContentPojo> getPOAddOnU2UPlanChangePolicyAdvice( CrmPlanMasterPojo oldBasePlanPOJO,
                                                                   CrmPlanMasterPojo newAddonPlanPOJO,
                                                                   CrmPlanMasterPojo oldAddonPlanPOJO,
                                                                   double actualUsage )
    {
        LOGGER.info( "Addon Plan change from one unlimited to another unlimited" );
        List<ContentPojo> adivceList = new ArrayList<ContentPojo>();
        PlanMigrationPolicyPojo planMigrationPolicyPojo = getPlanChangePolicyMasterObject( "PO", "UU" );
        double oldQuota = oldBasePlanPOJO.getPrimaryQuota();
        double newQuota = oldBasePlanPOJO.getPrimaryQuota();
        if ( StringUtils.isValidObj( oldAddonPlanPOJO ) )
        {
            oldQuota = oldQuota + oldAddonPlanPOJO.getPrimaryQuota();
        }
        if ( StringUtils.isValidObj( newAddonPlanPOJO ) )
        {
            newQuota = newQuota + newAddonPlanPOJO.getPrimaryQuota();
        }
        //Dec DUL
        if ( newQuota < oldQuota )
        {
            /*if ( StringUtils.isValidObj( newAddonPlanPOJO ) )
            {
                ContentPojo contentPojo = new ContentPojo();
                contentPojo.setContentName( IAppConstants.NBC );
                adivceList.add( contentPojo );
            }*/
            LOGGER.info( "Deccrease DUL Block" );
            if ( actualUsage <= newQuota )
            {
                //Immediate with advice
                ContentPojo contentPojo = new ContentPojo();
                contentPojo.setContentName( IAppConstants.IMMEDIATE_ACTIVATION );
                contentPojo.setContentValue( planMigrationPolicyPojo.getDecDULIMMAdvice() );
                ContentPojo contentPojo1 = new ContentPojo();
                contentPojo1.setContentName( IAppConstants.NBC );
                adivceList.add( contentPojo );
                adivceList.add( contentPojo1 );
            }
            else if ( actualUsage > newQuota )
            {
                //next bill cycle with advice
                ContentPojo contentPojo = new ContentPojo();
                contentPojo.setContentName( IAppConstants.NBC );
                contentPojo.setContentValue( planMigrationPolicyPojo.getDecDULNBCAdvice() );
                adivceList.add( contentPojo );
            }
        }
        // Inc DUL
        else if ( newQuota > oldQuota )
        {
            LOGGER.info( "Increase DUL Block" );
            //Immediate with advice
            ContentPojo contentPojo = new ContentPojo();
            contentPojo.setContentName( IAppConstants.IMMEDIATE_ACTIVATION );
            contentPojo.setContentValue( planMigrationPolicyPojo.getIncDULIMMAdvice() );
            ContentPojo contentPojo1 = new ContentPojo();
            contentPojo1.setContentName( IAppConstants.NBC );
            adivceList.add( contentPojo );
            adivceList.add( contentPojo1 );
        }
        return adivceList;
    }

    @Override
    public CrmQrcDto getPRAddOnPlanMigrationPolicy( CrmQrcDto inCrmQrcDto )
    {
        LOGGER.info( "Inside QrcManagerDaoImpl : getPRAddOnPlanMigrationPolicy" );
        CRMServiceCode crmServiceCode = CRMServiceCode.CRM999;
        Session session = null;
        Criteria criteria = null;
        CrmPlanMasterPojo oldBasePlanPOJO = null;
        CrmPlanMasterPojo newAddOnPlanPOJO = null;
        CrmPlanMasterPojo oldAddonPlanPOJO = null;
        try
        {
            session = HibernateUtil.getCurrentSession();
            if ( StringUtils.isNotEmpty( inCrmQrcDto.getOldBasePlanCode() ) )
            {
                criteria = session.createCriteria( CrmPlanMasterPojo.class );
                criteria.add( Restrictions.eq( "planCode", inCrmQrcDto.getOldBasePlanCode() ) );
                criteria.setCacheable( true );
                criteria.setCacheRegion( CrmPlanMasterPojo.class.getName() );
                oldBasePlanPOJO = (CrmPlanMasterPojo) criteria.list().get( 0 );
            }
            if ( StringUtils.isNotEmpty( inCrmQrcDto.getNewAddonPlanCode() ) )
            {
                criteria = session.createCriteria( CrmPlanMasterPojo.class );
                criteria.add( Restrictions.eq( "planCode", inCrmQrcDto.getNewAddonPlanCode() ) );
                criteria.setCacheable( true );
                criteria.setCacheRegion( CrmPlanMasterPojo.class.getName() );
                newAddOnPlanPOJO = (CrmPlanMasterPojo) criteria.list().get( 0 );
            }
            if ( StringUtils.isNotEmpty( inCrmQrcDto.getOldAddonPlanCode() ) )
            {
                criteria = session.createCriteria( CrmPlanMasterPojo.class );
                criteria.add( Restrictions.eq( "planCode", inCrmQrcDto.getOldAddonPlanCode() ) );
                criteria.setCacheable( true );
                criteria.setCacheRegion( CrmPlanMasterPojo.class.getName() );
                oldAddonPlanPOJO = (CrmPlanMasterPojo) criteria.list().get( 0 );
            }
            if ( StringUtils.isValidObj( oldBasePlanPOJO )
                    && StringUtils.isValidObj( inCrmQrcDto.getCustAdditionalDetails() ) )
            {
                double actualUsage = inCrmQrcDto.getCustAdditionalDetails().getUsedVolumeQuota();
                LOGGER.info( "Actual Usage:: " + actualUsage );
                //Calling Unlimited to Unlimited
                List<ContentPojo> policyAdviceList = getPRAddOnU2UPlanChangePolicyAdvice( oldBasePlanPOJO,
                                                                                          newAddOnPlanPOJO,
                                                                                          oldAddonPlanPOJO, actualUsage );
                inCrmQrcDto.setContentPojos( policyAdviceList );
            }
            crmServiceCode = CRMServiceCode.CRM001;
        }
        catch ( Exception ex )
        {
            LOGGER.error( "Exception while saving customer plan reactivation ", ex );
        }
        finally
        {
            CRMServiceUtils.closeSession( session );
            inCrmQrcDto.setStatusCode( crmServiceCode.getStatusCode() );
            inCrmQrcDto.setStatusDesc( crmServiceCode.getStatusDesc() );
        }
        return inCrmQrcDto;
    }

    private List<ContentPojo> getPRAddOnU2UPlanChangePolicyAdvice( CrmPlanMasterPojo oldBasePlanPOJO,
                                                                   CrmPlanMasterPojo newAddOnPlanPOJO,
                                                                   CrmPlanMasterPojo oldAddonPlanPOJO,
                                                                   double actualUsage )
    {
        List<ContentPojo> adivceList = new ArrayList<ContentPojo>();
        PlanMigrationPolicyPojo planMigrationPolicyPojo = getPlanChangePolicyMasterObject( "PR", "UU" );
        double oldQuota = oldBasePlanPOJO.getPrimaryQuota();
        double newQuota = oldBasePlanPOJO.getPrimaryQuota();
        if ( StringUtils.isValidObj( oldAddonPlanPOJO ) )
        {
            oldQuota = oldQuota + oldAddonPlanPOJO.getPrimaryQuota();
        }
        if ( StringUtils.isValidObj( newAddOnPlanPOJO ) )
        {
            newQuota = newQuota + newAddOnPlanPOJO.getPrimaryQuota();
        }
        //Dec DUL
        if ( newQuota < oldQuota )
        {
            /*if ( StringUtils.isValidObj( newAddOnPlanPOJO ) )
            {
                ContentPojo contentPojo = new ContentPojo();
                contentPojo.setContentName( IAppConstants.NBC );
                adivceList.add( contentPojo );
            }*/
            if ( actualUsage <= newQuota )
            {
                //Immediate with advice
                ContentPojo contentPojo = new ContentPojo();
                contentPojo.setContentName( IAppConstants.IMMEDIATE_ACTIVATION );
                contentPojo.setContentValue( planMigrationPolicyPojo.getDecDULIMMAdvice() );
                ContentPojo contentPojo1 = new ContentPojo();
                contentPojo1.setContentName( IAppConstants.NBC );
                adivceList.add( contentPojo );
                adivceList.add( contentPojo1 );
            }
            else if ( actualUsage > newQuota )
            {
                //next bill cycle with advice
                ContentPojo contentPojo = new ContentPojo();
                contentPojo.setContentName( IAppConstants.NBC );
                contentPojo.setContentValue( planMigrationPolicyPojo.getDecDULNBCAdvice() );
                adivceList.add( contentPojo );
            }
        }
        // Inc DUL
        else if ( newQuota > oldQuota )
        {
            //Immediate with advice
            ContentPojo contentPojo = new ContentPojo();
            contentPojo.setContentName( IAppConstants.IMMEDIATE_ACTIVATION );
            contentPojo.setContentValue( planMigrationPolicyPojo.getIncDULIMMAdvice() );
            ContentPojo contentPojo1 = new ContentPojo();
            contentPojo1.setContentName( IAppConstants.NBC );
            adivceList.add( contentPojo );
            adivceList.add( contentPojo1 );
        }
        return adivceList;
    }

    @Override
    public CrmQrcDto updatePlansFromBilling( CrmQrcDto inCrmQrcDto )
    {
        String customerId = inCrmQrcDto.getCustomerId();
        SessionFactory sessionFactory = null;
        Session session = null;
        Transaction transaction = null;
        CrmBillingDto crmBillingDto = null;
        CRMServiceCode serviceCode = CRMServiceCode.CRM997;
        Map<String, Long> evicts = new HashMap<String, Long>();
        try
        {
            MDC.put( "KEY", "CUSTID:" + inCrmQrcDto.getCustomerId() + ", USERID:" + inCrmQrcDto.getUserId() );
            sessionFactory = HibernateUtil.getSessionFactory();
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            if ( StringUtils.isNotBlank( customerId ) )
            {
                CrmCustomerDetailsPojo dbPojo = CRMServiceUtils.getDBValues( CrmCustomerDetailsPojo.class,
                                                                             "customerId", customerId, session );
                if ( StringUtils.isValidObj( dbPojo ) )
                {
                    if ( StringUtils.equals( dbPojo.getStatus(), CRMStatusCode.ACTIVE.getStatusCode() ) )
                    {
                        CrmPlanDetailsPojo planDetails = null;
                        for ( CrmPlanDetailsPojo plan : dbPojo.getCrmPlanDetailses() )
                        {
                            planDetails = plan;
                            break;
                        }
                        if ( StringUtils.isValidObj( planDetails ) )
                        {
                            inCrmQrcDto.setCrmPlanDetailsPojo( planDetails );
                            crmBillingDto = getAdditionalDetails( customerId, dbPojo.getServiceType() );
                            if ( StringUtils.isValidObj( crmBillingDto.getCustAdditionalDetails() ) )
                            {
                                String oldValues = null;
                                String newValues = null;
                                CRMCustomerActivityActions action = null;
                                if ( StringUtils.isNotBlank( inCrmQrcDto.getActivityAction() ) )
                                {
                                    action = CRMCustomerActivityActions.getActivityByActionCode( inCrmQrcDto
                                            .getActivityAction() );
                                    if ( CRMCustomerActivityActions.BASE_PLAN_MIGRATION == action )
                                    {
                                        oldValues = planDetails.getBasePlanCode();
                                        newValues = crmBillingDto.getCustAdditionalDetails().getPlanCode();
                                    }
                                    else if ( CRMCustomerActivityActions.ADDON_PLAN_CHANGE == action
                                            || CRMCustomerActivityActions.ADDON_PLAN_REMOVAL == action )
                                    {
                                        oldValues = planDetails.getAddOnPlanCode();
                                        newValues = crmBillingDto.getCustAdditionalDetails().getAddonPlanCode();
                                    }
                                }
                                LOGGER.info( "NBC Plan Migration customer ID:: " + customerId + " Old Plan Code:: "
                                        + oldValues + " New Plan Code :: " + newValues );
                                if ( !StringUtils.equals( oldValues, newValues ) )
                                {
                                    planDetails.setAddOnPlanCode( crmBillingDto.getCustAdditionalDetails()
                                            .getAddonPlanCode() );
                                    planDetails
                                            .setBasePlanCode( crmBillingDto.getCustAdditionalDetails().getPlanCode() );
                                    planDetails.setLastModifiedBy( inCrmQrcDto.getUserId() );
                                    planDetails.setLastModifiedTime( Calendar.getInstance().getTime() );
                                    session.update( planDetails );
                                    evicts.put( CrmPlanDetailsPojo.class.getName(), null );
                                    transaction.commit();
                                    serviceCode = CRMServiceCode.CRM001;
                                    if ( StringUtils.isNotBlank( inCrmQrcDto.getActivityAction() )
                                            && StringUtils.isValidObj( inCrmQrcDto.getCrmSrTicketsPojo() ) )
                                    {
                                        CrmPlanMasterPojo planMasterPojo = null;
                                        if ( StringUtils.isNotBlank( oldValues ) )
                                        {
                                            planMasterPojo = CRMServiceUtils.getDBValues( CrmPlanMasterPojo.class,
                                                                                          "planCode", oldValues );
                                            if ( StringUtils.isValidObj( planMasterPojo ) )
                                            {
                                                oldValues = planMasterPojo.getPlanName();
                                            }
                                            else
                                            {
                                                oldValues = null;
                                            }
                                        }
                                        if ( StringUtils.isNotBlank( newValues ) )
                                        {
                                            planMasterPojo = CRMServiceUtils.getDBValues( CrmPlanMasterPojo.class,
                                                                                          "planCode", newValues );
                                            if ( StringUtils.isValidObj( planMasterPojo ) )
                                            {
                                                newValues = planMasterPojo.getPlanName();
                                            }
                                            else
                                            {
                                                newValues = null;
                                            }
                                        }
                                        CRMServiceUtils.createActivityLog( oldValues, newValues, customerId,
                                                                           inCrmQrcDto.getUserId(), inCrmQrcDto
                                                                                   .getCrmSrTicketsPojo().getSrId(),
                                                                           inCrmQrcDto.getActivityAction(), null,
                                                                           inCrmQrcDto.getClientIPAddress(),
                                                                           inCrmQrcDto.getServerIPAddress() );
                                        if ( CRMCustomerActivityActions.BASE_PLAN_MIGRATION == action
                                                && StringUtils.isNotBlank( newValues ) )
                                        {
                                            inCrmQrcDto.setCustomerDetailsPojo( dbPojo );
                                            inCrmQrcDto.setPlanMasterPojo( planMasterPojo );
                                            serviceCode = changeCustomerBrand( inCrmQrcDto, session, Calendar
                                                    .getInstance().getTime(), evicts );
                                        }
                                    }
                                }
                                else
                                {
                                    serviceCode = CRMServiceCode.CRM619;
                                }
                            }
                        }
                    }
                    else
                    {
                        serviceCode = CRMServiceCode.CRM322;
                        if ( StringUtils.isValidObj( inCrmQrcDto.getCrmSrTicketsPojo() ) )
                        {
                            CrmRcaReasonPojo masterConfig = CRMServiceUtils.getDBValues( CrmRcaReasonPojo.class,
                                                                                         "valueAlias",
                                                                                         CRMOperationStages.CSD_BILLING
                                                                                                 .getCode(), session );
                            if ( StringUtils.isValidObj( masterConfig )
                                    && inCrmQrcDto.getCrmSrTicketsPojo().getFunctionalbinId() != masterConfig
                                            .getCategoryId() )
                            {
                                CrmQrcDto qrcDto = new CrmQrcDto();
                                CrmTicketHistoryPojo ticketHistory = new CrmTicketHistoryPojo();
                                ticketHistory.setRemarks( "Customer status is '"
                                        + CRMStatusCode.getStatus( dbPojo.getStatus() ) + "'. Forwarding ticket to '"
                                        + CRMOperationStages.CSD_BILLING.getDesc() + "'." );
                                qrcDto.setCrmSrTicketsPojo( inCrmQrcDto.getCrmSrTicketsPojo() );
                                qrcDto.setTicketHistory( ticketHistory );
                                qrcDto.setFutureStage( masterConfig.getCategoryId() + "" );
                                qrcDto = CRMServicesProxy
                                        .getInstance()
                                        .getCrmQrcService( IGlobalConstants.REMOTE, IGlobalConstants.APP )
                                        .ticketOperations( CrmActionEnum.FORWARDED.getActionCode(),
                                                           CrmSrTicketsPojo.class.getSimpleName(), qrcDto );
                                serviceCode = CRMServiceCode.valueOf( qrcDto.getStatusCode() );
                            }
                        }
                    }
                }
            }
        }
        catch ( HibernateException ex )
        {
            LOGGER.error( "HibernateException in updateBillingPlanRequest method", ex );
            serviceCode = CRMServiceCode.CRM999;
            CRMServiceUtils.rollback( transaction );
            CRMServiceUtils.closeSessionOnException( session );
        }
        catch ( Exception ex )
        {
            LOGGER.error( "Getting Error in updateBillingPlanRequest method", ex );
            serviceCode = CRMServiceCode.CRM999;
        }
        finally
        {
            CRMServiceUtils.rollback( transaction );
            CRMServiceUtils.closeSession( session );
            if ( serviceCode == CRMServiceCode.CRM001 )
            {
                HibernateUtil.evictAll( evicts );
            }
            MDC.remove( "KEY" );
        }
        inCrmQrcDto.setStatusCode( serviceCode.getStatusCode() );
        inCrmQrcDto.setStatusDesc( serviceCode.getStatusDesc() );
        return inCrmQrcDto;
    }

    @Override
    public boolean isValidTicket( String generatedTicketId, String customerId )
    {
        Session session = null;
        Criteria criteria = null;
        try
        {
            session = HibernateUtil.getCurrentSession();
            if ( StringUtils.startsWith( generatedTicketId, customerId ) )
            {
                criteria = session.createCriteria( CrmSrTicketsPojo.class );
                criteria.add( Restrictions.eq( "mappingId", customerId ) );
                criteria.add( Restrictions.eq( "srId", generatedTicketId ) );
                criteria.add( Restrictions.in( "status",
                                               new String[]
                                               { CRMStatusCode.OPEN.getStatusCode(),
                                                       CRMStatusCode.REOPEN.getStatusCode(),
                                                       CRMStatusCode.RESOLVED.getStatusCode(),
                                                       CRMStatusCode.CLOSE.getStatusCode() } ) );
                criteria.setCacheable( true );
                criteria.setCacheRegion( CrmSrTicketsPojo.class.getName() );
                List<CrmSrTicketsPojo> crmSrTicketsPojos = criteria.list();
                if ( CommonValidator.isValidCollection( crmSrTicketsPojos ) )
                {
                    return true;
                }
            }
        }
        catch ( Exception ex )
        {
            LOGGER.error( "Getting Error while check valid ticket no: ", ex );
        }
        finally
        {
            CRMServiceUtils.closeSession( session );
        }
        return false;
    }

    @Override
    public CrmQrcDto saveCustomerFeedback( CrmQrcDto inCrmQrcDto )
    {
        Session session = null;
        Transaction transaction = null;
        CrmCustAssetDetailsPojo crmCustAssetDetailsPojo = new CrmCustAssetDetailsPojo();
        CrmCustAssetDetailsPojo oldCrmCustAssetDetailsPojo = new CrmCustAssetDetailsPojo();
        CRMServiceCode crmServiceCode = null;
        SessionFactory sessionFactory = null;
        long assetDetailsId = 0l;
        try
        {
            MDC.put( "KEY", "CUSTID:" + inCrmQrcDto.getCustomerId() + ", USERID:" + inCrmQrcDto.getUserId() );
            crmServiceCode = CRMServiceCode.CRM997;
            if ( StringUtils.isValidObj( inCrmQrcDto ) && StringUtils.isNotBlank( inCrmQrcDto.getCustomerId() ) )
            {
                sessionFactory = HibernateUtil.getSessionFactory();
                session = sessionFactory.openSession();
                transaction = session.beginTransaction();
                oldCrmCustAssetDetailsPojo = inCrmQrcDto.getCrmCustAssetDetailsPojo();
                CrmCustomerDetailsPojo customerPojo = CRMServiceUtils
                        .getDBValues( CrmCustomerDetailsPojo.class, "customerId", inCrmQrcDto.getCustomerId(), session );
                if ( StringUtils.isValidObj( customerPojo ) && customerPojo.getRecordId() > 0 )
                {
                    inCrmQrcDto.setCustomerRecordId( customerPojo.getRecordId() );
                }
                inCrmQrcDto = getCustomerFeedback( inCrmQrcDto );
                LOGGER.info( "Status Code::" + inCrmQrcDto.getStatusCode() );
                if ( StringUtils.equals( inCrmQrcDto.getStatusCode(), CRMServiceCode.CRM001.getStatusCode() ) )
                {
                    if ( StringUtils.isValidObj( inCrmQrcDto.getCrmCustAssetDetailsPojo() )
                            && StringUtils.equals( inCrmQrcDto.getCrmCustAssetDetailsPojo().getCategoryValue(), "Y" ) )
                    {
                        crmServiceCode = CRMServiceCode.CRM070;
                    }
                    else
                    {
                        crmCustAssetDetailsPojo.setAssetDetailsId( inCrmQrcDto.getCrmCustAssetDetailsPojo()
                                .getAssetDetailsId() );
                        crmCustAssetDetailsPojo.setCustomerRecordId( customerPojo.getRecordId() );
                        crmCustAssetDetailsPojo.setCategoryName( oldCrmCustAssetDetailsPojo.getCategoryName() );
                        if ( StringUtils.equalsIgnoreCase( oldCrmCustAssetDetailsPojo.getCategoryValue(), "Yes" ) )
                        {
                            crmCustAssetDetailsPojo.setCategoryValue( "Y" );
                        }
                        else
                        {
                            crmCustAssetDetailsPojo.setCategoryValue( "N" );
                        }
                        crmCustAssetDetailsPojo.setStatus( CRMStatusCode.ACTIVE.getStatusCode() );
                        crmCustAssetDetailsPojo.setCreatedBy( inCrmQrcDto.getUserId() );
                        crmCustAssetDetailsPojo.setCreatedTime( Calendar.getInstance().getTime() );
                        session.update( crmCustAssetDetailsPojo );
                        transaction.commit();
                        crmServiceCode = CRMServiceCode.CRM001;
                    }
                }
                else
                {
                    crmCustAssetDetailsPojo = inCrmQrcDto.getCrmCustAssetDetailsPojo();
                    if ( StringUtils.isValidObj( customerPojo ) && customerPojo.getRecordId() > 0 )
                    {
                        crmCustAssetDetailsPojo.setCustomerRecordId( customerPojo.getRecordId() );
                        boolean isValid = ValidationPojoUtil
                                .validatePojo( crmCustAssetDetailsPojo,
                                               ICRMValidationCriteriaUtil.CUSTOMER_FEEDBACK_CREATE_CRITERIA ) == null;
                        if ( isValid )
                        {
                            if ( StringUtils.isValidObj( crmCustAssetDetailsPojo ) )
                            {
                                if ( StringUtils.equalsIgnoreCase( crmCustAssetDetailsPojo.getCategoryValue(), "Yes" ) )
                                {
                                    crmCustAssetDetailsPojo.setCategoryValue( "Y" );
                                }
                                else
                                {
                                    crmCustAssetDetailsPojo.setCategoryValue( "N" );
                                }
                                crmCustAssetDetailsPojo.setStatus( CRMStatusCode.ACTIVE.getStatusCode() );
                                crmCustAssetDetailsPojo.setCreatedBy( inCrmQrcDto.getUserId() );
                                crmCustAssetDetailsPojo.setCreatedTime( Calendar.getInstance().getTime() );
                                assetDetailsId = (Long) session.save( crmCustAssetDetailsPojo );
                            }
                            if ( assetDetailsId > 0 )
                            {
                                crmServiceCode = CRMServiceCode.CRM001;
                                transaction.commit();
                            }
                        }
                    }
                }
            }
        }
        catch ( HibernateException ex )
        {
            LOGGER.error( "HibernateException while saveCustomerFeedback :", ex );
            crmServiceCode = CRMServiceCode.CRM999;
            CRMServiceUtils.rollback( transaction );
            CRMServiceUtils.closeSessionOnException( session );
        }
        catch ( Exception ex )
        {
            LOGGER.error( "Getting Error while saveCustomerFeedback: ", ex );
            crmServiceCode = CRMServiceCode.CRM999;
        }
        finally
        {
            CRMServiceUtils.closeSession( session );
            inCrmQrcDto.setStatusCode( crmServiceCode.getStatusCode() );
            inCrmQrcDto.setStatusDesc( crmServiceCode.getStatusDesc() );
            if ( crmServiceCode == CRMServiceCode.CRM001 )
            {
                HibernateUtil.evictAll( CrmCustAssetDetailsPojo.class.getName(), null, null );
            }
            MDC.remove( "KEY" );
        }
        return inCrmQrcDto;
    }

    @Override
    public CrmQrcDto getCustomerFeedback( CrmQrcDto inCrmQrcDto )
    {
        LOGGER.info( "Inside QrcManagerDaoImpl : getCustomerFeedback" );
        CRMServiceCode crmServiceCode = CRMServiceCode.CRM999;
        SessionFactory sessionFactory = null;
        Session session = null;
        Criteria criteria = null;
        List<CrmCustAssetDetailsPojo> crmCustAssetDetailsList = null;
        try
        {
            MDC.put( "KEY", "CUSTID:" + inCrmQrcDto.getCustomerId() + ", USERID:" + inCrmQrcDto.getUserId() );
            sessionFactory = HibernateUtil.getSessionFactory();
            session = sessionFactory.openSession();
            criteria = session.createCriteria( CrmCustAssetDetailsPojo.class );
            criteria.add( Restrictions.eq( "customerRecordId", inCrmQrcDto.getCustomerRecordId() ) );
            criteria.add( Restrictions.eq( "categoryName", "Feedback" ) );
            crmCustAssetDetailsList = criteria.list();
            LOGGER.info( "Total Size of list::" + crmCustAssetDetailsList.size() );
            if ( CommonValidator.isValidCollection( crmCustAssetDetailsList ) )
            {
                inCrmQrcDto.setCrmCustAssetDetailsPojo( crmCustAssetDetailsList.get( 0 ) );
                crmServiceCode = CRMServiceCode.CRM001;
            }
        }
        catch ( HibernateException ex )
        {
            LOGGER.error( "HibernateException while saveCustomerFeedback :", ex );
            crmServiceCode = CRMServiceCode.CRM999;
            CRMServiceUtils.closeSessionOnException( session );
        }
        catch ( Exception ex )
        {
            LOGGER.error( "Getting Error while saveCustomerFeedback: ", ex );
            crmServiceCode = CRMServiceCode.CRM999;
        }
        finally
        {
            CRMServiceUtils.closeSession( session );
            inCrmQrcDto.setStatusCode( crmServiceCode.getStatusCode() );
            inCrmQrcDto.setStatusDesc( crmServiceCode.getStatusDesc() );
            MDC.remove( "KEY" );
        }
        return inCrmQrcDto;
    }
}
