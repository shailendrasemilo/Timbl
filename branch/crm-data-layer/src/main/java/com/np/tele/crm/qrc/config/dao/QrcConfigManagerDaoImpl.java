package com.np.tele.crm.qrc.config.dao;

import java.util.Calendar;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import com.np.tele.crm.constants.CRMServiceCode;
import com.np.tele.crm.constants.IAppConstants;
import com.np.tele.crm.dto.QrcConfigDto;
import com.np.tele.crm.pojos.CrmQrcActionTakenPojo;
import com.np.tele.crm.pojos.CrmQrcCategoriesPojo;
import com.np.tele.crm.pojos.CrmQrcCategoryBinMappingPojo;
import com.np.tele.crm.pojos.CrmQrcRootCausePojo;
import com.np.tele.crm.pojos.CrmQrcSubCategoriesPojo;
import com.np.tele.crm.pojos.CrmQrcSubSubCategoriesPojo;
import com.np.tele.crm.utils.CRMServiceUtils;
import com.np.tele.crm.utils.CRMUtils;
import com.np.tele.crm.utils.HibernateUtil;
import com.np.tele.crm.utils.ICRMValidationCriteriaUtil;
import com.np.tele.crm.utils.StringUtils;
import com.np.validator.util.CommonValidator;
import com.np.validator.util.ValidationPojoUtil;

public class QrcConfigManagerDaoImpl
    implements IQrcConfigManagerDao
{
    private static final Logger LOGGER = Logger.getLogger( QrcConfigManagerDaoImpl.class );

    @Override
    public QrcConfigDto getRcaConfigurations( QrcConfigDto inQrcConfigDto )
    {
        LOGGER.info( "Inside QrcConfigManagerDaoImpl : getRcaConfigurations" );
        Session session = null;
        Criteria criteria = null;
        CRMServiceCode crmServiceCode = CRMServiceCode.CRM999;
        try
        {
            session = HibernateUtil.getCurrentSession();
            criteria = session.createCriteria( CrmQrcActionTakenPojo.class );
            if ( inQrcConfigDto.getQrcActionTakenPojo().getQrcCategoryId() > 0 )
            {
                criteria.add( Restrictions.eq( "qrcCategoryId", inQrcConfigDto.getQrcActionTakenPojo()
                        .getQrcCategoryId() ) );
            }
            if ( StringUtils.isNotBlank( inQrcConfigDto.getQrcActionTakenPojo().getServiceName() ) )
            {
                criteria.add( Restrictions.eq( "serviceName", inQrcConfigDto.getQrcActionTakenPojo().getServiceName() ) );
            }
            if ( StringUtils.isValidObj( inQrcConfigDto.getQrcActionTakenPojo().getStatus() ) )
                criteria.add( Restrictions.eq( "status", inQrcConfigDto.getQrcActionTakenPojo().getStatus() ) );
            criteria.setCacheable( true );
            criteria.setCacheRegion( CrmQrcActionTakenPojo.class.getName() );
            List<CrmQrcActionTakenPojo> crmQrcRcaPojos = criteria.list();
            if ( StringUtils.isValidObj( crmQrcRcaPojos ) && !crmQrcRcaPojos.isEmpty() )
            {
                crmServiceCode = CRMServiceCode.CRM001;
                inQrcConfigDto.setQrcActionTakenPojos( crmQrcRcaPojos );
            }
            else
            {
                crmServiceCode = CRMServiceCode.CRM996;
            }
        }
        catch ( Exception ex )
        {
            LOGGER.error( "Getting Error while getting RCA Configurations: ", ex );
        }
        finally
        {
            CRMServiceUtils.closeSession( session );
            inQrcConfigDto.setStatusCode( crmServiceCode.getStatusCode() );
            inQrcConfigDto.setStatusDesc( crmServiceCode.getStatusDesc() );
        }
        return inQrcConfigDto;
    }

    @Override
    public boolean createRcaConfiguration( CrmQrcActionTakenPojo inQrcRcaPojo )
    {
        LOGGER.info( "Inside QrcConfigManagerDaoImpl : createRcaConfiguration" );
        SessionFactory sessionFactory = null;
        Session session = null;
        Transaction transaction = null;
        Long qrcRcaID = null;
        boolean configured = false;
        if ( StringUtils.isValidObj( inQrcRcaPojo ) )
        {
            try
            {
                sessionFactory = HibernateUtil.getSessionFactory();
                session = sessionFactory.openSession();
                transaction = session.beginTransaction();
                if ( inQrcRcaPojo.getActionId() > 0 )
                {
                    qrcRcaID = inQrcRcaPojo.getActionId();
                    CrmQrcActionTakenPojo qrcRcaPojoDB = (CrmQrcActionTakenPojo) session
                            .get( CrmQrcActionTakenPojo.class, qrcRcaID );
                    qrcRcaPojoDB.setActionTaken( inQrcRcaPojo.getActionTaken() );
                    qrcRcaPojoDB.setLastModifiedTime( Calendar.getInstance().getTime() );
                    qrcRcaPojoDB.setLastModifiedBy( inQrcRcaPojo.getCreatedBy() );
                    qrcRcaPojoDB.setStatus( inQrcRcaPojo.getStatus() );
                    session.merge( qrcRcaPojoDB );
                }
                else
                {
                    inQrcRcaPojo.setCreatedTime( Calendar.getInstance().getTime() );
                    qrcRcaID = (Long) session.save( inQrcRcaPojo );
                }
                if ( StringUtils.isValidObj( qrcRcaID ) && qrcRcaID > 0 )
                {
                    configured = true;
                    transaction.commit();
                }
                LOGGER.info( "Working RCA Configuration ID : " + qrcRcaID );
            }
            catch ( HibernateException ex )
            {
                LOGGER.error( "HibernateException while configuring RCA", ex );
                CRMServiceUtils.rollback( transaction );
                CRMServiceUtils.closeSessionOnException( session );
            }
            catch ( Exception ex )
            {
                LOGGER.error( "Exception while configuring RCA ", ex );
            }
            finally
            {
                CRMServiceUtils.closeSession( session );
                if ( configured )
                {
                    HibernateUtil.evictAll( CrmQrcCategoriesPojo.class.getName(), "crmQrcRcas", null );
                    HibernateUtil.evictAll( CrmQrcActionTakenPojo.class.getName(), null, null );
                }
            }
        }
        return configured;
    }

    @Override
    public QrcConfigDto getQrcResolutionCodePojos( QrcConfigDto inQrcConfigDto )
    {
        LOGGER.info( "Inside QrcConfigManagerDaoImpl : getQrcResolutionCodePojos" );
        Session session = null;
        Criteria criteria = null;
        CRMServiceCode crmServiceCode = CRMServiceCode.CRM999;
        try
        {
            session = HibernateUtil.getCurrentSession();
            criteria = session.createCriteria( CrmQrcRootCausePojo.class );
            if ( inQrcConfigDto.getQrcRootCausePojo().getActionId() > 0 )
            {
                criteria.add( Restrictions.eq( "actionId", inQrcConfigDto.getQrcRootCausePojo().getActionId() ) );
            }
            criteria.setCacheable( true );
            criteria.setCacheRegion( CrmQrcRootCausePojo.class.getName() );
            List<CrmQrcRootCausePojo> qrcResolutionCodePojos = criteria.list();
            if ( CommonValidator.isValidCollection( qrcResolutionCodePojos ) )
            {
                crmServiceCode = CRMServiceCode.CRM001;
                inQrcConfigDto.setQrcRootCausePojos( qrcResolutionCodePojos );
            }
        }
        catch ( Exception ex )
        {
            LOGGER.error( "Getting Error while getting Resolution Code: ", ex );
        }
        finally
        {
            CRMServiceUtils.closeSession( session );
            inQrcConfigDto.setStatusCode( crmServiceCode.getStatusCode() );
            inQrcConfigDto.setStatusDesc( crmServiceCode.getStatusDesc() );
        }
        return inQrcConfigDto;
    }

    //    @Override
    //    public QrcConfigDto getQrcAttributedToPojos( QrcConfigDto inQrcConfigDto )
    //    {
    //        LOGGER.info( "Inside QrcConfigManagerDaoImpl : getQrcAttributedToPojos" );
    //        Session session = null;
    //        Criteria criteria = null;
    //        CRMServiceCode crmServiceCode = CRMServiceCode.CRM999;
    //        try
    //        {
    //            session = HibernateUtil.getCurrentSession();
    //            criteria = session.createCriteria( CrmQrcAttributedToPojo.class );
    //            if ( inQrcConfigDto.getCrmQrcAttributedToPojo().getResolutionCodeId() > 0 )
    //            {
    //                criteria.add( Restrictions.eq( "resolutionCodeId", inQrcConfigDto.getCrmQrcAttributedToPojo()
    //                        .getResolutionCodeId() ) );
    //            }
    //            criteria.setCacheable( true );
    //            criteria.setCacheRegion( CrmQrcAttributedToPojo.class.getName() );
    //            List<CrmQrcAttributedToPojo> qrcAttributedToPojos = criteria.list();
    //            if ( CommonValidator.isValidCollection( qrcAttributedToPojos ) )
    //            {
    //                crmServiceCode = CRMServiceCode.CRM001;
    //                inQrcConfigDto.setQrcAttributedToPojos( qrcAttributedToPojos );
    //            }
    //            else
    //            {
    //                crmServiceCode = CRMServiceCode.CRM996;
    //            }
    //        }
    //        catch ( Exception ex )
    //        {
    //            LOGGER.error( "Getting Error while getting Attributed to: ", ex );
    //        }
    //        finally
    //        {
    //            CRMServiceUtils.closeSession( session );
    //            inQrcConfigDto.setStatusCode( crmServiceCode.getStatusCode() );
    //            inQrcConfigDto.setStatusDesc( crmServiceCode.getStatusDesc() );
    //        }
    //        return inQrcConfigDto;
    //    }
    @Override
    public QrcConfigDto configureResolutionCodePojo( QrcConfigDto inQrcConfigDto )
    {
        LOGGER.info( "Inside QrcConfigManagerDaoImpl:configureResolutionCodePojo" );
        Session session = null;
        Transaction transaction = null;
        Long qrcResolutionCodeId = null;
        Criteria criteria = null;
        int editableCounter = 0;
        boolean successFlag = false;
        CRMServiceCode crmServiceCode = CRMServiceCode.CRM999;
        try
        {
            session = HibernateUtil.getCurrentSession();
            transaction = session.beginTransaction();
            for ( CrmQrcRootCausePojo qrcResolutionCodePojo : inQrcConfigDto.getQrcRootCausePojos() )
            {
                boolean isValid = false;
                if ( StringUtils.isValidObj( qrcResolutionCodePojo ) )
                {
                    if ( qrcResolutionCodePojo.isEditable() )
                    {
                        editableCounter++;
                        isValid = ValidationPojoUtil
                                .validatePojo( qrcResolutionCodePojo,
                                               ICRMValidationCriteriaUtil.QRC_CONFIG_RESOLUTION_CODE_CRITERIA ) == null;
                        if ( isValid )
                        {
                            qrcResolutionCodePojo.setCreatedBy( inQrcConfigDto.getLoggedUser() );
                            if ( qrcResolutionCodePojo.getRootCauseId() > 0 )
                            {
                                qrcResolutionCodeId = qrcResolutionCodePojo.getRootCauseId();
                                CrmQrcRootCausePojo qrcResCodePojoDB = (CrmQrcRootCausePojo) session
                                        .get( CrmQrcRootCausePojo.class, qrcResolutionCodeId );
                                qrcResCodePojoDB.setRootCause( qrcResolutionCodePojo.getRootCause() );
                                qrcResCodePojoDB.setAttributedTo( qrcResolutionCodePojo.getAttributedTo() );
                                qrcResCodePojoDB.setLastModifiedTime( Calendar.getInstance().getTime() );
                                qrcResCodePojoDB.setLastModifiedBy( qrcResolutionCodePojo.getCreatedBy() );
                                qrcResCodePojoDB.setStatus( qrcResolutionCodePojo.getStatus() );
                                session.merge( qrcResCodePojoDB );
                                successFlag = true;
                            }
                            else
                            {
                                criteria = session.createCriteria( CrmQrcRootCausePojo.class );
                                criteria.add( Restrictions.eq( "actionId", qrcResolutionCodePojo.getActionId() ) );
                                criteria.add( Restrictions.eq( "rootCause", qrcResolutionCodePojo.getRootCause() ) );
                                criteria.setCacheable( true );
                                criteria.setCacheRegion( CrmQrcRootCausePojo.class.getName() );
                                List<CrmQrcRootCausePojo> foundedResolutionCodeList = criteria.list();
                                if ( foundedResolutionCodeList.size() < 1 )
                                {
                                    qrcResolutionCodePojo.setCreatedTime( Calendar.getInstance().getTime() );
                                    qrcResolutionCodeId = (Long) session.save( qrcResolutionCodePojo );
                                    successFlag = true;
                                }
                                else
                                {
                                    successFlag = false;
                                    crmServiceCode = CRMServiceCode.CRM308;
                                    inQrcConfigDto.setQrcRootCausePojo( qrcResolutionCodePojo );
                                    break;
                                }
                            }
                        }
                        else
                        {
                            successFlag = false;
                            crmServiceCode = CRMServiceCode.CRM997;
                            break;
                        }
                    }
                }
            }
            if ( successFlag )
            {
                transaction.commit();
                crmServiceCode = CRMServiceCode.CRM001;
            }
            if ( editableCounter <= 0 )
            {
                crmServiceCode = CRMServiceCode.CRM995;
            }
        }
        catch ( HibernateException ex )
        {
            LOGGER.error( "HibernateException while configuring Resolution Code", ex );
            CRMServiceUtils.rollback( transaction );
            CRMServiceUtils.closeSessionOnException( session );
        }
        catch ( Exception ex )
        {
            LOGGER.error( "Exception while configuring Resolution Code ", ex );
        }
        finally
        {
            CRMServiceUtils.rollback( transaction );
            CRMServiceUtils.closeSession( session );
            inQrcConfigDto.setStatusCode( crmServiceCode.getStatusCode() );
            inQrcConfigDto.setStatusDesc( crmServiceCode.getStatusDesc() );
            if ( successFlag )
            {
                HibernateUtil.evictAll( CrmQrcRootCausePojo.class.getName(), null, null );
            }
        }
        return inQrcConfigDto;
    }

    //    @Override
    //    public QrcConfigDto createAttributedToConfiguration( QrcConfigDto inQrcConfigDto )
    //    {
    //        LOGGER.info( "Inside QrcConfigManagerDaoImpl : createAttributedToConfiguration" );
    //        Session session = null;
    //        Transaction transaction = null;
    //        Long qrcAttributedToID = null;
    //        Criteria criteria = null;
    //        int editableCounter = 0;
    //        boolean successFlag = false;
    //        CRMServiceCode crmServiceCode = CRMServiceCode.CRM999;
    //        try
    //        {
    //            session = HibernateUtil.getCurrentSession();
    //            transaction = session.beginTransaction();
    //            for ( CrmQrcAttributedToPojo qrAttributedPojo : inQrcConfigDto.getQrcAttributedToPojos() )
    //            {
    //                boolean isValid = false;
    //                if ( StringUtils.isValidObj( qrAttributedPojo ) )
    //                {
    //                    if ( qrAttributedPojo.isEditable() )
    //                    {
    //                        editableCounter++;
    //                        isValid = ValidationPojoUtil
    //                                .validatePojo( qrAttributedPojo,
    //                                               ICRMValidationCriteriaUtil.QRC_CREATE_ATTRIBUTEDTO_CONFIGURATIONS_CRITERIA ) == null;
    //                        if ( isValid )
    //                        {
    //                            qrAttributedPojo.setCreatedBy( inQrcConfigDto.getLoggedUser() );
    //                            if ( qrAttributedPojo.getAttributedToId() > 0 )
    //                            {
    //                                qrcAttributedToID = qrAttributedPojo.getAttributedToId();
    //                                CrmQrcAttributedToPojo qrcAttributedPojoDB = (CrmQrcAttributedToPojo) session
    //                                        .get( CrmQrcAttributedToPojo.class, qrcAttributedToID );
    //                                qrcAttributedPojoDB.setAttributedTo( qrAttributedPojo.getAttributedTo() );
    //                                qrcAttributedPojoDB.setLastModifiedTime( Calendar.getInstance().getTime() );
    //                                qrcAttributedPojoDB.setLastModifiedBy( qrAttributedPojo.getCreatedBy() );
    //                                qrcAttributedPojoDB.setStatus( qrAttributedPojo.getStatus() );
    //                                session.merge( qrcAttributedPojoDB );
    //                                successFlag = true;
    //                            }
    //                            else
    //                            {
    //                                criteria = session.createCriteria( CrmQrcAttributedToPojo.class );
    //                                criteria.add( Restrictions.eq( "resolutionCodeId",
    //                                                               qrAttributedPojo.getResolutionCodeId() ) );
    //                                criteria.add( Restrictions.eq( "attributedTo", qrAttributedPojo.getAttributedTo() ) );
    //                                criteria.setCacheable( true );
    //                                criteria.setCacheRegion( CrmQrcAttributedToPojo.class.getName() );
    //                                List<CrmQrcAttributedToPojo> foundedAttributedToList = criteria.list();
    //                                if ( foundedAttributedToList.size() < 1 )
    //                                {
    //                                    qrAttributedPojo.setCreatedTime( Calendar.getInstance().getTime() );
    //                                    qrcAttributedToID = (Long) session.save( qrAttributedPojo );
    //                                    successFlag = true;
    //                                }
    //                                else
    //                                {
    //                                    successFlag = false;
    //                                    crmServiceCode = CRMServiceCode.CRM308;
    //                                    inQrcConfigDto.setCrmQrcAttributedToPojo( qrAttributedPojo );
    //                                    break;
    //                                }
    //                            }
    //                        }
    //                        else
    //                        {
    //                            successFlag = false;
    //                            crmServiceCode = CRMServiceCode.CRM997;
    //                            break;
    //                        }
    //                    }
    //                }
    //            }
    //            if ( successFlag )
    //            {
    //                transaction.commit();
    //                crmServiceCode = CRMServiceCode.CRM001;
    //                LOGGER.info( "Working Attributed To Configuration ID : " + qrcAttributedToID );
    //            }
    //            if ( editableCounter <= 0 )
    //            {
    //                crmServiceCode = CRMServiceCode.CRM995;
    //            }
    //        }
    //        catch ( Exception ex )
    //        {
    //            LOGGER.error( "Exception while configuring Attributed To ", ex );
    //        }
    //        finally
    //        {
    //            CRMServiceUtils.rollback( transaction );
    //            CRMServiceUtils.closeSession( session );
    //            inQrcConfigDto.setStatusCode( crmServiceCode.getStatusCode() );
    //            inQrcConfigDto.setStatusDesc( crmServiceCode.getStatusDesc() );
    //            if ( successFlag )
    //            {
    //                HibernateUtil.evictAll( CrmQrcAttributedToPojo.class.getName(), null, null );
    //            }
    //        }
    //        return inQrcConfigDto;
    //    }
    @Override
    public QrcConfigDto getQrcBinMappingPojos( QrcConfigDto inQrcConfigDto )
    {
        LOGGER.info( "Inside QrcConfigManagerDaoImpl : getQrcBinMappingPojos" );
        Session session = null;
        Criteria criteria = null;
        CRMServiceCode crmServiceCode = CRMServiceCode.CRM999;
        try
        {
            session = HibernateUtil.getCurrentSession();
            criteria = session.createCriteria( CrmQrcCategoryBinMappingPojo.class );
            if ( inQrcConfigDto.getCategoryBinMappingPojo().getQrcSubSubCategoryId() > 0 )
            {
                criteria.add( Restrictions.eq( "qrcSubSubCategoryId", inQrcConfigDto.getCategoryBinMappingPojo()
                        .getQrcSubSubCategoryId() ) );
            }
            if ( StringUtils.isNotBlank( inQrcConfigDto.getCategoryBinMappingPojo().getStatus() ) )
            {
                criteria.add( Restrictions.eq( "status", inQrcConfigDto.getCategoryBinMappingPojo().getStatus() ) );
            }
            criteria.setCacheable( true );
            criteria.setCacheRegion( CrmQrcCategoryBinMappingPojo.class.getName() );
            List<CrmQrcCategoryBinMappingPojo> qrcCategoryBinMappingPojos = criteria.list();
            if ( StringUtils.isValidObj( qrcCategoryBinMappingPojos ) && !qrcCategoryBinMappingPojos.isEmpty() )
            {
                crmServiceCode = CRMServiceCode.CRM001;
                inQrcConfigDto.setCategoryBinMappingPojos( qrcCategoryBinMappingPojos );
            }
        }
        catch ( Exception ex )
        {
            LOGGER.error( "Getting Error while getting QRC Bin Mappings Configurations: ", ex );
        }
        finally
        {
            CRMServiceUtils.closeSession( session );
            inQrcConfigDto.setStatusCode( crmServiceCode.getStatusCode() );
            inQrcConfigDto.setStatusDesc( crmServiceCode.getStatusDesc() );
        }
        return inQrcConfigDto;
    }

    @Override
    public boolean createBinMapping( CrmQrcCategoryBinMappingPojo inCategoryBinMappingPojo )
    {
        LOGGER.info( "Inside QrcConfigManagerDaoImpl : createBinMapping" );
        SessionFactory sessionFactory = null;
        Session session = null;
        Transaction transaction = null;
        Long recordID = null;
        boolean configured = false;
        if ( StringUtils.isValidObj( inCategoryBinMappingPojo ) )
        {
            try
            {
                sessionFactory = HibernateUtil.getSessionFactory();
                session = sessionFactory.openSession();
                transaction = session.beginTransaction();
                if ( inCategoryBinMappingPojo.getMappingId() > 0 )
                {
                    recordID = inCategoryBinMappingPojo.getMappingId();
                    CrmQrcCategoryBinMappingPojo categoryBinMappingPojo = (CrmQrcCategoryBinMappingPojo) session
                            .get( CrmQrcCategoryBinMappingPojo.class, recordID );
                    categoryBinMappingPojo.setFromBinId( inCategoryBinMappingPojo.getFromBinId() );
                    categoryBinMappingPojo.setToBinId( inCategoryBinMappingPojo.getToBinId() );
                    categoryBinMappingPojo.setLastModifiedTime( Calendar.getInstance().getTime() );
                    categoryBinMappingPojo.setLastModifiedBy( inCategoryBinMappingPojo.getCreatedBy() );
                    categoryBinMappingPojo.setStatus( inCategoryBinMappingPojo.getStatus() );
                    session.merge( categoryBinMappingPojo );
                }
                else
                {
                    inCategoryBinMappingPojo.setCreatedTime( Calendar.getInstance().getTime() );
                    recordID = (Long) session.save( inCategoryBinMappingPojo );
                }
                if ( StringUtils.isValidObj( recordID ) && recordID > 0 )
                {
                    configured = true;
                    transaction.commit();
                }
                LOGGER.info( "Working Bin Mapping ID : " + recordID );
            }
            catch ( HibernateException ex )
            {
                LOGGER.error( "HibernateException while configuring Bin Mapping", ex );
                CRMServiceUtils.rollback( transaction );
                CRMServiceUtils.closeSessionOnException( session );
            }
            catch ( Exception ex )
            {
                LOGGER.error( "Exception while configuring Bin Mapping ", ex );
            }
            finally
            {
                CRMServiceUtils.rollback( transaction );
                CRMServiceUtils.closeSession( session );
                if ( configured )
                {
                    HibernateUtil.evictAll( CrmQrcCategoryBinMappingPojo.class.getName(), null, null );
                }
            }
        }
        return configured;
    }

    @Override
    public QrcConfigDto createQrcRcaPojos( QrcConfigDto inQrcConfigDto )
    {
        LOGGER.info( "Inside QrcConfigManagerDaoImpl : createQrcRcaPojos" );
        CRMServiceCode crmServiceCode = CRMServiceCode.CRM999;
        SessionFactory sessionFactory = null;
        Session session = null;
        Transaction transaction = null;
        Long qrcRcaID = null;
        if ( StringUtils.isValidObj( inQrcConfigDto )
                && StringUtils.isValidObj( inQrcConfigDto.getQrcActionTakenPojos() )
                && !inQrcConfigDto.getQrcActionTakenPojos().isEmpty() )
        {
            try
            {
                sessionFactory = HibernateUtil.getSessionFactory();
                session = sessionFactory.openSession();
                transaction = session.beginTransaction();
                for ( CrmQrcActionTakenPojo qrcRcaPojo : inQrcConfigDto.getQrcActionTakenPojos() )
                {
                    boolean isValid = false;
                    if ( StringUtils.isValidObj( qrcRcaPojo ) && qrcRcaPojo.isEditable() )
                    {
                        inQrcConfigDto.setQrcActionTakenPojo( qrcRcaPojo );
                        isValid = ValidationPojoUtil
                                .validatePojo( qrcRcaPojo,
                                               ICRMValidationCriteriaUtil.QRC_CREATE_RCA_CONFIGURATIONS_CRITERIA ) == null;
                        if ( isValid )
                        {
                            if ( qrcRcaPojo.getActionId() > 0 )
                            {
                                qrcRcaID = qrcRcaPojo.getActionId();
                                qrcRcaPojo.setLastModifiedTime( Calendar.getInstance().getTime() );
                                qrcRcaPojo.setLastModifiedBy( inQrcConfigDto.getLoggedUser() );
                                CrmQrcActionTakenPojo dupPojo = checkDuplicate( session, qrcRcaPojo );
                                if ( StringUtils.isValidObj( dupPojo ) )
                                {
                                    if ( dupPojo.getActionId() == qrcRcaID )
                                    {
                                        qrcRcaPojo = (CrmQrcActionTakenPojo) session.merge( qrcRcaPojo );
                                        crmServiceCode = CRMServiceCode.CRM001;
                                    }
                                    else
                                    {
                                        crmServiceCode = CRMServiceCode.CRM416;
                                        break;
                                    }
                                }
                                else
                                {
                                    qrcRcaPojo = (CrmQrcActionTakenPojo) session.merge( qrcRcaPojo );
                                    crmServiceCode = CRMServiceCode.CRM001;
                                }
                            }
                            else
                            {
                                qrcRcaPojo.setCreatedBy( inQrcConfigDto.getLoggedUser() );
                                qrcRcaPojo.setCreatedTime( Calendar.getInstance().getTime() );
                                CrmQrcActionTakenPojo dupPojo = checkDuplicate( session, qrcRcaPojo );
                                if ( !StringUtils.isValidObj( dupPojo ) )
                                {
                                    qrcRcaID = (Long) session.save( qrcRcaPojo );
                                    crmServiceCode = CRMServiceCode.CRM001;
                                }
                                else
                                {
                                    crmServiceCode = CRMServiceCode.CRM416;
                                    break;
                                }
                            }
                            LOGGER.info( "Working RCA Configuration ID : " + qrcRcaID );
                        }
                        else
                        {
                            crmServiceCode = CRMServiceCode.CRM997;
                            break;
                        }
                    }
                }
                transaction.commit();
            }
            catch ( HibernateException ex )
            {
                LOGGER.error( "HibernateException while configuring RCA", ex );
                CRMServiceUtils.rollback( transaction );
                CRMServiceUtils.closeSessionOnException( session );
            }
            catch ( Exception ex )
            {
                LOGGER.error( "Exception while configuring RCA ", ex );
            }
            finally
            {
                CRMServiceUtils.rollback( transaction );
                CRMServiceUtils.closeSession( session );
                inQrcConfigDto.setStatusCode( crmServiceCode.getStatusCode() );
                inQrcConfigDto.setStatusDesc( crmServiceCode.getStatusDesc() );
                if ( crmServiceCode == CRMServiceCode.CRM001 )
                {
                    HibernateUtil.evictAll( CrmQrcActionTakenPojo.class.getName(), null, null );
                }
            }
        }
        return inQrcConfigDto;
    }

    @Override
    public QrcConfigDto createQrcBinMappingPojos( QrcConfigDto inQrcConfigDto )
    {
        LOGGER.info( "Inside QrcConfigManagerDaoImpl : createQrcBinMappingPojos" );
        CRMServiceCode crmServiceCode = CRMServiceCode.CRM999;
        SessionFactory sessionFactory = null;
        Session session = null;
        Transaction transaction = null;
        Long qrcBinMappingID = null;
        if ( StringUtils.isValidObj( inQrcConfigDto )
                && StringUtils.isValidObj( inQrcConfigDto.getCategoryBinMappingPojos() )
                && !inQrcConfigDto.getCategoryBinMappingPojos().isEmpty() )
        {
            try
            {
                sessionFactory = HibernateUtil.getSessionFactory();
                session = sessionFactory.openSession();
                transaction = session.beginTransaction();
                for ( CrmQrcCategoryBinMappingPojo binMappingPojo : inQrcConfigDto.getCategoryBinMappingPojos() )
                {
                    boolean isValid = false;
                    if ( StringUtils.isValidObj( binMappingPojo ) )
                    {
                        inQrcConfigDto.setCategoryBinMappingPojo( binMappingPojo );
                        isValid = ValidationPojoUtil
                                .validatePojo( binMappingPojo,
                                               ICRMValidationCriteriaUtil.QRC_CREATE_BIN_MAPPING_CRITERIA ) == null;
                        if ( isValid && binMappingPojo.isEditable() )
                        {
                            if ( binMappingPojo.getToBinId() == binMappingPojo.getFromBinId() )
                            {
                                crmServiceCode = CRMServiceCode.CRM425;
                                break;
                            }
                            else if ( binMappingPojo.getMappingId() > 0 )
                            {
                                qrcBinMappingID = binMappingPojo.getMappingId();
                                boolean checkDuplicate = checkDuplicate( session, binMappingPojo );
                                CrmQrcCategoryBinMappingPojo binMappingPojoDB = (CrmQrcCategoryBinMappingPojo) session
                                        .get( CrmQrcCategoryBinMappingPojo.class, qrcBinMappingID );
                                if ( !checkDuplicate )
                                {
                                    binMappingPojoDB.setFromBinId( binMappingPojo.getFromBinId() );
                                    binMappingPojoDB.setToBinId( binMappingPojo.getToBinId() );
                                    binMappingPojoDB.setLastModifiedTime( Calendar.getInstance().getTime() );
                                    binMappingPojoDB.setLastModifiedBy( inQrcConfigDto.getLoggedUser() );
                                    binMappingPojoDB.setStatus( binMappingPojo.getStatus() );
                                    session.merge( binMappingPojoDB );
                                    crmServiceCode = CRMServiceCode.CRM001;
                                }
                                else
                                {
                                    crmServiceCode = CRMServiceCode.CRM417;
                                    break;
                                }
                            }
                            else
                            {
                                binMappingPojo.setCreatedBy( inQrcConfigDto.getLoggedUser() );
                                binMappingPojo.setCreatedTime( Calendar.getInstance().getTime() );
                                if ( !checkDuplicate( session, binMappingPojo ) )
                                {
                                    qrcBinMappingID = (Long) session.save( binMappingPojo );
                                    crmServiceCode = CRMServiceCode.CRM001;
                                }
                                else
                                {
                                    crmServiceCode = CRMServiceCode.CRM417;
                                    break;
                                }
                            }
                            LOGGER.info( "Working Bin Mapping Configuration ID : " + qrcBinMappingID );
                        }
                    }
                }
                transaction.commit();
            }
            catch ( HibernateException ex )
            {
                LOGGER.error( "HibernateException while configuring Bin Mapping", ex );
                CRMServiceUtils.rollback( transaction );
                CRMServiceUtils.closeSessionOnException( session );
            }
            catch ( Exception ex )
            {
                LOGGER.error( "Exception while configuring Bin Mapping ", ex );
            }
            finally
            {
                CRMServiceUtils.rollback( transaction );
                CRMServiceUtils.closeSession( session );
                inQrcConfigDto.setStatusCode( crmServiceCode.getStatusCode() );
                inQrcConfigDto.setStatusDesc( crmServiceCode.getStatusDesc() );
                if ( crmServiceCode == CRMServiceCode.CRM001 )
                {
                    HibernateUtil.evictAll( CrmQrcCategoryBinMappingPojo.class.getName(), null, null );
                }
            }
        }
        return inQrcConfigDto;
    }

    private CrmQrcActionTakenPojo checkDuplicate( Session inSession, CrmQrcActionTakenPojo inQrcRcaPojo )
    {
        CrmQrcActionTakenPojo crmQrcRcaPojo = null;
        Criteria criteria = null;
        try
        {
            criteria = inSession.createCriteria( CrmQrcActionTakenPojo.class );
            if ( inQrcRcaPojo.getQrcCategoryId() > 0 )
            {
                criteria.add( Restrictions.eq( "qrcCategoryId", inQrcRcaPojo.getQrcCategoryId() ) );
            }
            if ( StringUtils.isNotBlank( inQrcRcaPojo.getServiceName() ) )
            {
                criteria.add( Restrictions.eq( "serviceName", inQrcRcaPojo.getServiceName() ) );
            }
            if ( StringUtils.isNotBlank( inQrcRcaPojo.getActionTaken() ) )
            {
                criteria.add( Restrictions.eq( "actionTaken", inQrcRcaPojo.getActionTaken() ) );
            }
            criteria.setCacheable( true );
            criteria.setCacheRegion( CrmQrcActionTakenPojo.class.getName() );
            List<CrmQrcActionTakenPojo> crmQrcRcaPojos = criteria.list();
            if ( CommonValidator.isValidCollection( crmQrcRcaPojos ) )
            {
                crmQrcRcaPojo = crmQrcRcaPojos.get( 0 );
            }
        }
        catch ( Exception ex )
        {
            LOGGER.error( "Getting Error while getting RCA Configurations: ", ex );
        }
        return crmQrcRcaPojo;
    }

    private boolean checkDuplicate( Session inSession, CrmQrcCategoryBinMappingPojo inBinMappingPojo )
    {
        Criteria criteria = null;
        boolean isDuplicate = false;
        try
        {
            criteria = inSession.createCriteria( CrmQrcCategoryBinMappingPojo.class );
            if ( inBinMappingPojo.getQrcSubSubCategoryId() > 0 )
            {
                criteria.add( Restrictions.eq( "qrcSubSubCategoryId", inBinMappingPojo.getQrcSubSubCategoryId() ) );
            }
            if ( inBinMappingPojo.getFromBinId() > 0 )
            {
                criteria.add( Restrictions.eq( "fromBinId", inBinMappingPojo.getFromBinId() ) );
            }
            if ( inBinMappingPojo.getToBinId() > 0 )
            {
                criteria.add( Restrictions.eq( "toBinId", inBinMappingPojo.getToBinId() ) );
            }
            if ( StringUtils.isNotBlank( inBinMappingPojo.getStatus() ) )
            {
                criteria.add( Restrictions.eq( "status", inBinMappingPojo.getStatus() ) );
            }
            criteria.setCacheable( true );
            criteria.setCacheRegion( CrmQrcCategoryBinMappingPojo.class.getName() );
            List<CrmQrcCategoryBinMappingPojo> categoryBinMappingPojos = criteria.list();
            if ( StringUtils.isValidObj( categoryBinMappingPojos ) && !categoryBinMappingPojos.isEmpty() )
            {
                isDuplicate = true;
            }
        }
        catch ( Exception ex )
        {
            LOGGER.error( "Getting Error while getting Bin Mapping Configurations: ", ex );
        }
        return isDuplicate;
    }

    @Override
    public QrcConfigDto createSubSubCat( QrcConfigDto inQrcConfigDto )
    {
        LOGGER.info( "Inside QrcConfigManagerDaoImpl : createSubSubCat" );
        Session session = null;
        Transaction transaction = null;
        String errorKey = null;
        Long subSubCatPojoID = null;
        //int editableCounter = 0;
        boolean successFlag = false;
        CRMServiceCode crmServiceCode = CRMServiceCode.CRM999;
        try
        {
            session = HibernateUtil.getCurrentSession();
            transaction = session.beginTransaction();
            for ( CrmQrcSubSubCategoriesPojo subSubCatPojo : inQrcConfigDto.getCrmQrcSubSubCategoriesPojos() )
            {
                boolean isValid = false;
                if ( StringUtils.isValidObj( subSubCatPojo ) )
                {
                    errorKey = ValidationPojoUtil.validatePojo( subSubCatPojo,
                                                                ICRMValidationCriteriaUtil.CONFIG_SUB_SUB_CAT_POJO );
                    isValid = errorKey == null;
                    if ( isValid )
                    {
                        //editableCounter++;
                        if ( StringUtils.equals( subSubCatPojo.getModificationAllowed(), IAppConstants.YES_CHAR ) )
                        {
                            subSubCatPojo.setCreatedBy( inQrcConfigDto.getLoggedUser() );
                            if ( subSubCatPojo.getQrcSubSubCategoryId() > 0 )
                            {
                                LOGGER.info( "to update previous:" + subSubCatPojo );
                                subSubCatPojoID = subSubCatPojo.getQrcSubSubCategoryId();
                                CrmQrcSubSubCategoriesPojo qrcSubSubCatPojo_DB = (CrmQrcSubSubCategoriesPojo) session
                                        .get( CrmQrcSubSubCategoriesPojo.class, subSubCatPojoID );
                                qrcSubSubCatPojo_DB.setQrcSubSubCategory( subSubCatPojo.getQrcSubSubCategory() );
                                qrcSubSubCatPojo_DB.setQrcType( subSubCatPojo.getQrcType() );
                                qrcSubSubCatPojo_DB.setResolutionType( subSubCatPojo.getResolutionType() );
                                qrcSubSubCatPojo_DB.setFunctionalBinId( subSubCatPojo.getFunctionalBinId() );
                                qrcSubSubCatPojo_DB.setLastModifiedTime( Calendar.getInstance().getTime() );
                                qrcSubSubCatPojo_DB.setLastModifiedBy( subSubCatPojo.getCreatedBy() );
                                qrcSubSubCatPojo_DB.setStatus( subSubCatPojo.getStatus() );
                                qrcSubSubCatPojo_DB.setModuleType( subSubCatPojo.getModuleType() );
                                LOGGER.info( "mergingggg:" + qrcSubSubCatPojo_DB );
                                session.merge( qrcSubSubCatPojo_DB );
                                successFlag = true;
                            }
                            else
                            {
                                LOGGER.info( "to update new:" + subSubCatPojo );
                                subSubCatPojo.setCreatedTime( Calendar.getInstance().getTime() );
                                subSubCatPojoID = (Long) session.save( subSubCatPojo );
                                successFlag = true;
                            }
                        }
                    }
                    else
                    {
                        successFlag = false;
                        crmServiceCode = CRMUtils.getServiceByErrorKey( errorKey );
                        break;
                    }
                }
            }
            if ( successFlag )
            {
                transaction.commit();
                crmServiceCode = CRMServiceCode.CRM001;
                //LOGGER.info( "Working Attributed To Configuration ID : " + qrcAttributedToID );
            }
            /*if ( editableCounter <= 0 )
            {
                crmServiceCode = CRMServiceCode.CRM995;
            }*/
        }
        catch ( HibernateException ex )
        {
            LOGGER.error( "HibernateException while configuring SubSubCat", ex );
            CRMServiceUtils.rollback( transaction );
            CRMServiceUtils.closeSessionOnException( session );
        }
        catch ( Exception ex )
        {
            LOGGER.error( "Exception while configuring SubSubCat", ex );
        }
        finally
        {
            CRMServiceUtils.rollback( transaction );
            CRMServiceUtils.closeSession( session );
            inQrcConfigDto.setStatusCode( crmServiceCode.getStatusCode() );
            inQrcConfigDto.setStatusDesc( crmServiceCode.getStatusDesc() );
            if ( successFlag )
            {
                HibernateUtil.evictAll( CrmQrcCategoriesPojo.class.getName(), "crmQrcSubCategorieses", null );
                HibernateUtil.evictAll( CrmQrcSubCategoriesPojo.class.getName(), "crmQrcSubSubCategorieses", null );
                // HibernateUtil.evictAll( CrmQrcSubSubCategoriesPojo.class.getName(), "crmQrcCategoryBinMappings", null );
            }
        }
        return inQrcConfigDto;
    }
}
