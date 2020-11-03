package com.np.tele.crm.utils;

import java.beans.PropertyDescriptor;
import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import javax.xml.soap.SOAPException;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.np.tele.crm.billing.utils.CrmPojoToBilling;
import com.np.tele.crm.config.dao.ConfigManagerDaoImpl;
import com.np.tele.crm.config.dao.IConfigManagerDao;
import com.np.tele.crm.constants.CRMCustomerActivityActions;
import com.np.tele.crm.constants.CRMEvents;
import com.np.tele.crm.constants.CRMParameter;
import com.np.tele.crm.constants.CRMRequestType;
import com.np.tele.crm.constants.CRMServiceCode;
import com.np.tele.crm.constants.CRMStatusCode;
import com.np.tele.crm.constants.CrmActionEnum;
import com.np.tele.crm.constants.IAppConstants;
import com.np.tele.crm.constants.IGlobalConstants;
import com.np.tele.crm.constants.QRCRolCategories;
import com.np.tele.crm.constants.ServiceParameter;
import com.np.tele.crm.dto.AlertsDto;
import com.np.tele.crm.dto.ConfigDto;
import com.np.tele.crm.dto.CrmQrcDto;
import com.np.tele.crm.locator.CRMServicesProxy;
import com.np.tele.crm.pojos.AlertStatusPojo;
import com.np.tele.crm.pojos.CrmAuditLogPojo;
import com.np.tele.crm.pojos.CrmCustomerActivityPojo;
import com.np.tele.crm.pojos.CrmMasterPojo;
import com.np.tele.crm.pojos.CrmNetworkConfigurationsPojo;
import com.np.tele.crm.pojos.CrmPartnerNetworkConfigPojo;
import com.np.tele.crm.pojos.CrmPlanMasterPojo;
import com.np.tele.crm.pojos.CrmSrTicketsPojo;
import com.np.tele.crm.pojos.CrmTicketHistoryPojo;
import com.np.tele.crm.pojos.EventsPojo;
import com.np.validator.util.CommonValidator;
import com.np.tele.crm.utils.StringUtils;


public final class CRMServiceUtils
{
    private static final Logger     LOGGER           = Logger.getLogger( CRMServiceUtils.class );
    public static IConfigManagerDao configManagerDao = null;
    static
    {
        configManagerDao = new ConfigManagerDaoImpl();
    }

    //    public static final boolean sendAlerts( final CRMEvents inCrmEvents,
    //                                            final String inUserID,
    //                                            final String inLeadID,
    //                                            final String inCRFID,
    //                                            final String inCustomerID,
    //                                            final String partnerID,
    //                                            final String srNoQrc )
    //    {
    //        try
    //        {
    //            AlertsDto alertsDto = new AlertsDto();
    //            EventsPojo eventsPojo = configManagerDao.getEventsPojoByName( inCrmEvents.getEventName() );
    //            if ( StringUtils.isValidObj( eventsPojo )
    //                    && ( StringUtils.isNotBlank( inCustomerID ) || StringUtils.isNotBlank( inUserID )
    //                            || StringUtils.isNotBlank( inCRFID ) || StringUtils.isNotBlank( inLeadID ) )
    //                    || StringUtils.isNotBlank( partnerID ) || StringUtils.isNotBlank( srNoQrc ) )
    //            {
    //                alertsDto.setEventsPojo( eventsPojo );
    //                alertsDto.setUserId( inUserID );
    //                alertsDto.setCrfNumber( inCRFID );
    //                alertsDto.setCustomerId( inCustomerID );
    //                alertsDto.setLeadId( inLeadID );
    //                alertsDto.setPartnerId( partnerID );
    //                alertsDto.setSrNoQrc( srNoQrc );
    //                AlertStatusPojo alertStatusPojo = CRMServicesProxy.getInstance()
    //                        .getAlertsService( IGlobalConstants.REMOTE, IGlobalConstants.APP ).sendAlerts( alertsDto );
    //                if ( StringUtils.equals( CRMParameter.YES.getParameter(), alertStatusPojo.getEmailSent() )
    //                        || StringUtils.equals( CRMParameter.YES.getParameter(), alertStatusPojo.getSmsSent() ) )
    //                {
    //                    return true;
    //                }
    //            }
    //            else
    //            {
    //                LOGGER.info( "Required data not present" );
    //            }
    //        }
    //        catch ( Exception ex )
    //        {
    //            LOGGER.error( "Exception while Sending Alerts", ex );
    //        }
    //        return false;
    //    }
    public static final boolean sendAlerts( final CRMEvents inCrmEvents,
                                            final CRMRequestType inParam,
                                            final String inParamValue,
                                            final List<String> inAttachments,
                                            final Map<String, String> inParamValues )
    {
        try
        {
            AlertsDto alertsDto = new AlertsDto();
            EventsPojo eventsPojo = configManagerDao.getEventsPojoByName( inCrmEvents.getEventName() );
            if ( StringUtils.isValidObj( inParam ) )
            {
                alertsDto.setEventsPojo( eventsPojo );
                alertsDto.setParamName( inParam.getRequestCode() );
                alertsDto.setParamValue( inParamValue );
                switch ( inCrmEvents )
                {
                    case INVENTORY_SHORTAGE:
                        alertsDto.setEmailParameter( CRMParameter.INVENTORYEMAIL.getParameter() );
                        alertsDto.setSmsParameter( CRMParameter.INVENTORYSMS.getParameter() );
                        break;
                    case ASSIGN_EOC_SERVICE:
                        alertsDto.setSmsParameter( CRMParameter.USERMOBILENO.getParameter() );
                        break;
                    case LEAD_INTIMATION_AM:
                        alertsDto.setSmsParameter( CRMParameter.USERMOBILENO.getParameter() );
                        alertsDto.setEmailParameter( CRMParameter.USEREMAILID.getParameter() );
                        break;
                    default:
                        alertsDto.setEmailParameter( CRMParameter.CUSTOMER_EMAIL.getParameter() );
                        alertsDto.setSmsParameter( CRMParameter.CUSTOMER_RMN.getParameter() );
                        break;
                }
                if ( StringUtils.isValidObj( inParamValues ) )
                {
                    alertsDto.setParamValues( inParamValues );
                }
                alertsDto.setAttachmentPaths( inAttachments );
                AlertStatusPojo alertStatusPojo = CRMServicesProxy.getInstance()
                        .getAlertsService( IGlobalConstants.REMOTE, IGlobalConstants.APP ).sendAlerts( alertsDto );
                if ( StringUtils.isValidObj( alertStatusPojo ) )
                {
                    if ( StringUtils.equals( CRMParameter.YES.getParameter(), alertStatusPojo.getEmailSent() )
                            || StringUtils.equals( CRMParameter.YES.getParameter(), alertStatusPojo.getSmsSent() ) )
                    {
                        return true;
                    }
                }
            }
            else
            {
                LOGGER.info( "Required data not present to send alerts" );
            }
        }
        catch ( Exception ex )
        {
            LOGGER.error( "Exception while Sending Alerts", ex );
        }
        return false;
    }

    public static final boolean sendAlerts( final CRMEvents inCrmEvents,
                                            final CRMRequestType inParam,
                                            final String inParamValue,
                                            final List<String> inAttachments )
    {
        return sendAlerts( inCrmEvents, inParam, inParamValue, inAttachments, null );
    }

    public static final long getEventId( final String inString )
    {
        EventsPojo eventsPojo = null;
        try
        {
            eventsPojo = configManagerDao.getEventsPojoByName( inString );
        }
        catch ( Exception ex )
        {
            LOGGER.error( "Exception in getEvent ", ex );
        }
        return eventsPojo.getEventId();
    }

    private static final CrmAuditLogPojo auditLog( final CrmAuditLogPojo inCrmAuditLogPojo )
    {
        try
        {
            ConfigDto configDto = new ConfigDto();
            LOGGER.info( "auditLog::::" + inCrmAuditLogPojo );
            if ( StringUtils.equals( inCrmAuditLogPojo.getEvents(), CrmActionEnum.ERP.getActionCode() ) )
            {
                configDto.setAuditLogPojo( inCrmAuditLogPojo );
                configDto = CRMServicesProxy.getInstance()
                        .getCRMConfigService( IGlobalConstants.REMOTE, IGlobalConstants.APP )
                        .auditLogOperation( ServiceParameter.CREATE.getParameter(), configDto );
                return configDto.getAuditLogPojo();
            }
            else if ( StringUtils.isValidObj( inCrmAuditLogPojo )
                    && StringUtils.isNotBlank( inCrmAuditLogPojo.getEvents() )
                    && StringUtils.isNotBlank( inCrmAuditLogPojo.getMappingId() )
                    && StringUtils.isNotBlank( inCrmAuditLogPojo.getModule() )
                    && StringUtils.isNotBlank( inCrmAuditLogPojo.getNewValues() )
                    && StringUtils.isNotBlank( inCrmAuditLogPojo.getCreatedBy() ) )
            {
                configDto.setAuditLogPojo( inCrmAuditLogPojo );
                configDto = CRMServicesProxy.getInstance()
                        .getCRMConfigService( IGlobalConstants.REMOTE, IGlobalConstants.APP )
                        .auditLogOperation( ServiceParameter.CREATE.getParameter(), configDto );
                return configDto.getAuditLogPojo();
            }
            else
            {
                LOGGER.info( "Required data not present" );
            }
        }
        catch ( Exception ex )
        {
            LOGGER.error( "Exception in audit Log", ex );
        }
        return null;
    }

    public static void closeSession( Session inSession )
    {
        if ( StringUtils.isValidObj( inSession ) && ( inSession.isOpen() || inSession.isConnected() ) )
        {
            try
            {
                if ( inSession.isDirty() )
                {
                    inSession.clear();
                }
            }
            catch ( Exception ex )
            {
                LOGGER.error( "Could not perform dirtying checking " + ex.getMessage() );
            }
            inSession.flush();
            inSession.clear();
            inSession.disconnect();
            inSession.close();
        }
    }

    public static void closeSessionOnException( Session inSession )
    {
        if ( StringUtils.isValidObj( inSession ) && ( inSession.isOpen() || inSession.isConnected() ) )
        {
            inSession.clear();
            inSession.disconnect();
            inSession.close();
        }
    }

    public static void rollback( Transaction inTransaction )
    {
        if ( StringUtils.isValidObj( inTransaction ) && inTransaction.isActive() )
        {
            inTransaction.rollback();
        }
    }

    public static <E> void unmarshallMasterData( final List<CrmMasterPojo> inConfiguration, final E e )
    {
        try
        {
            CrmMasterPojo crmMasterPojo = null;
            for ( CrmMasterPojo masterPojo : inConfiguration )
            {
                crmMasterPojo = masterPojo;
                Object catValue = masterPojo.getCategoryValue();
                if ( StringUtils.isValidObj( catValue ) )
                {
                    if ( StringUtils.equals( "B", masterPojo.getDataType() ) )
                    {
                        catValue = Boolean.parseBoolean( masterPojo.getCategoryValue() );
                    }
                    else if ( StringUtils.equals( "I", masterPojo.getDataType() ) )
                    {
                        if ( StringUtils.isNumeric( masterPojo.getCategoryValue() ) )
                        {
                            catValue = Integer.parseInt( masterPojo.getCategoryValue() );
                        }
                    }
                    else if ( StringUtils.equals( "L", masterPojo.getDataType() ) )
                    {
                        if ( StringUtils.isNumeric( masterPojo.getCategoryValue() ) )
                        {
                            catValue = Long.parseLong( masterPojo.getCategoryValue() );
                        }
                    }
                    org.apache.commons.beanutils.PropertyUtils.setSimpleProperty( e, masterPojo.getSubSubCategory(),
                                                                                  catValue );
                }
            }
            if ( StringUtils.isValidObj( crmMasterPojo ) )
            {
                org.apache.commons.beanutils.PropertyUtils.setSimpleProperty( e, IAppConstants.CATEGORY,
                                                                              crmMasterPojo.getCategory() );
                org.apache.commons.beanutils.PropertyUtils.setSimpleProperty( e, IAppConstants.SUB_CATEGORY,
                                                                              crmMasterPojo.getSubCategory() );
                org.apache.commons.beanutils.PropertyUtils.setSimpleProperty( e, IAppConstants.ALIAS,
                                                                              crmMasterPojo.getCategoryAlias() );
            }
        }
        catch ( IllegalAccessException ex )
        {
            LOGGER.error( "Exception:", ex );
        }
        catch ( InvocationTargetException ex )
        {
            LOGGER.error( "Exception:", ex );
        }
        catch ( NoSuchMethodException ex )
        {
            LOGGER.error( "Exception:", ex );
        }
    }

    public static <E> boolean marshallMasterData( final List<CrmMasterPojo> inConfiguration,
                                                  final E e,
                                                  final String inUserId )
    {
        boolean toCreate = inConfiguration.isEmpty();
        try
        {
            String categoryAlias = null;
            if ( !toCreate )
            {
                Object alias = org.apache.commons.beanutils.PropertyUtils.getProperty( e, IAppConstants.ALIAS );
                if ( StringUtils.isValidObj( alias ) )
                {
                    categoryAlias = alias.toString();
                }
            }
            Object category = org.apache.commons.beanutils.PropertyUtils.getProperty( e, IAppConstants.CATEGORY );
            Object subCategory = org.apache.commons.beanutils.PropertyUtils.getProperty( e,
                                                                                         IAppConstants.SUB_CATEGORY );
            PropertyDescriptor[] prDesc = org.apache.commons.beanutils.PropertyUtils.getPropertyDescriptors( e );
            CrmMasterPojo crmMasterPojo = null;
            for ( PropertyDescriptor descriptor : prDesc )
            {
                String propertyName = descriptor.getName();
                String strCategory = null;
                String strSubCategory = null;
                if ( StringUtils.isValidObj( category ) && StringUtils.isValidObj( subCategory ) )
                {
                    strCategory = category.toString();
                    strSubCategory = subCategory.toString();
                }
                if ( StringUtils.equals( IAppConstants.ALIAS, propertyName )
                        || StringUtils.equals( IAppConstants.CATEGORY, propertyName )
                        || StringUtils.equals( IAppConstants.SUB_CATEGORY, propertyName )
                        || StringUtils.equals( "class", propertyName ) )
                {
                    continue;
                }
                if ( toCreate && StringUtils.isNotBlank( strCategory ) && StringUtils.isNotBlank( strSubCategory ) )
                {
                    String propertyType = descriptor.getPropertyType().getSimpleName();
                    crmMasterPojo = new CrmMasterPojo();
                    crmMasterPojo.setCategory( strCategory );
                    crmMasterPojo.setSubCategory( strSubCategory );
                    crmMasterPojo.setSubSubCategory( propertyName );
                    Object catValue = org.apache.commons.beanutils.PropertyUtils.getProperty( e, propertyName );
                    if ( StringUtils.isValidObj( catValue ) )
                    {
                        crmMasterPojo.setCategoryValue( catValue.toString() );
                    }
                    crmMasterPojo.setCreatedBy( inUserId );
                    crmMasterPojo.setCreatedTime( Calendar.getInstance().getTime() );
                    if ( StringUtils.isNotBlank( propertyType ) )
                    {
                        crmMasterPojo
                                .setDataType( StringUtils.upperCase( StringUtils.substring( propertyType, 0, 1 ) ) );
                    }
                    crmMasterPojo.setStatus( CRMStatusCode.PENDING.getStatusCode() );
                    inConfiguration.add( crmMasterPojo );
                }
                else if ( StringUtils.isNotBlank( categoryAlias ) )
                {
                    crmMasterPojo = new CrmMasterPojo();
                    crmMasterPojo.setCategoryAlias( categoryAlias );
                    crmMasterPojo.setSubSubCategory( propertyName );
                    int pojoIndex = inConfiguration.indexOf( crmMasterPojo );
                    LOGGER.info( "Marshalling for:" + categoryAlias + " and " + propertyName + " Pojo Index:"
                            + pojoIndex );
                    if ( pojoIndex >= 0 )
                    {
                        crmMasterPojo = inConfiguration.get( pojoIndex );
                        crmMasterPojo.setLastModifiedBy( inUserId );
                        crmMasterPojo.setLastModifiedTime( Calendar.getInstance().getTime() );
                        Object catValue = org.apache.commons.beanutils.PropertyUtils.getProperty( e, propertyName );
                        if ( StringUtils.isValidObj( catValue ) )
                        {
                            crmMasterPojo.setCategoryValue( catValue.toString() );
                        }
                    }
                    else
                    {
                        crmMasterPojo.setCategory( strCategory );
                        crmMasterPojo.setSubCategory( strSubCategory );
                        String propertyType = descriptor.getPropertyType().getSimpleName();
                        Object catValue = org.apache.commons.beanutils.PropertyUtils.getProperty( e, propertyName );
                        if ( StringUtils.isValidObj( catValue ) )
                        {
                            crmMasterPojo.setCategoryValue( catValue.toString() );
                        }
                        crmMasterPojo.setCreatedBy( inUserId );
                        crmMasterPojo.setCreatedTime( Calendar.getInstance().getTime() );
                        if ( StringUtils.isNotBlank( propertyType ) )
                        {
                            crmMasterPojo.setDataType( StringUtils
                                    .upperCase( StringUtils.substring( propertyType, 0, 1 ) ) );
                        }
                        crmMasterPojo.setStatus( CRMStatusCode.PENDING.getStatusCode() );
                        inConfiguration.add( crmMasterPojo );
                    }
                }
            }
        }
        catch ( IllegalAccessException ex )
        {
            LOGGER.error( "Exception:", ex );
        }
        catch ( InvocationTargetException ex )
        {
            LOGGER.error( "Exception:", ex );
        }
        catch ( NoSuchMethodException ex )
        {
            LOGGER.error( "Exception:", ex );
        }
        return toCreate;
    }

    public static <E> List<CrmMasterPojo> getMasterData( E e )
    {
        if ( StringUtils.isValidObj( e ) )
        {
            List<CrmMasterPojo> crmMasterPojos = null;
            String categoryAlias = null;
            try
            {
                Object alias = org.apache.commons.beanutils.PropertyUtils.getProperty( e, IAppConstants.ALIAS );
                LOGGER.info( "Alias from Pojo:" + alias );
                if ( StringUtils.isValidObj( alias ) )
                {
                    categoryAlias = alias.toString();
                }
                if ( StringUtils.isBlank( categoryAlias ) )
                {
                    Object category = org.apache.commons.beanutils.PropertyUtils.getProperty( e,
                                                                                              IAppConstants.CATEGORY );
                    Object subCategory = org.apache.commons.beanutils.PropertyUtils
                            .getProperty( e, IAppConstants.SUB_CATEGORY );
                    if ( StringUtils.isValidObj( category ) && StringUtils.isValidObj( subCategory ) )
                    {
                        categoryAlias = StringUtils.lowerCase( category.toString() ) + IAppConstants.DOT
                                + StringUtils.lowerCase( subCategory.toString() );
                    }
                }
                LOGGER.info( "Final Alias from Pojo:" + categoryAlias );
                crmMasterPojos = configManagerDao.getMasterConfiguration( categoryAlias, CRMStatusCode.ACTIVE );
                if ( StringUtils.isValidObj( crmMasterPojos ) && !crmMasterPojos.isEmpty() )
                {
                    org.apache.commons.beanutils.PropertyUtils.setSimpleProperty( e, IAppConstants.ALIAS,
                                                                                  categoryAlias );
                }
            }
            catch ( IllegalAccessException ex )
            {
                LOGGER.error( "Exception:", ex );
            }
            catch ( InvocationTargetException ex )
            {
                LOGGER.error( "Exception:", ex );
            }
            catch ( NoSuchMethodException ex )
            {
                LOGGER.error( "Exception:", ex );
            }
            return crmMasterPojos;
        }
        return null;
    }

    public static <E> Set<Long> getGISIds( List<E> inGisList, String inProperty )
    {
        Set<Long> gisIds = new HashSet<Long>();
        for ( E e : inGisList )
        {
            try
            {
                Object id = org.apache.commons.beanutils.PropertyUtils.getProperty( e, inProperty );
                if ( StringUtils.isValidObj( id ) && ( id instanceof Long || StringUtils.isNumeric( id.toString() ) ) )
                {
                    gisIds.add( Long.parseLong( id.toString() ) );
                }
            }
            catch ( IllegalAccessException ex )
            {
                LOGGER.error( "Exception:", ex );
            }
            catch ( InvocationTargetException ex )
            {
                LOGGER.error( "Exception:", ex );
            }
            catch ( NoSuchMethodException ex )
            {
                LOGGER.error( "Exception:", ex );
            }
        }
        return gisIds;
    }

    public static <E> boolean isValueModified( E oldObject, E newObject, String inProperty )
    {
        try
        {
            Object oldValue = org.apache.commons.beanutils.PropertyUtils.getProperty( oldObject, inProperty );
            Object newValue = org.apache.commons.beanutils.PropertyUtils.getProperty( newObject, inProperty );
            if ( ( StringUtils.isValidObj( oldValue ) && !StringUtils.isValidObj( newValue ) )
                    || ( !StringUtils.isValidObj( oldValue ) && StringUtils.isValidObj( newValue ) )
                    || ( !StringUtils.isValidObj( oldValue ) && !StringUtils.isValidObj( newValue ) ) )
            {
                return true;
            }
            if ( !oldValue.equals( newValue ) )
            {
                return true;
            }
        }
        catch ( IllegalAccessException ex )
        {
            LOGGER.error( "Exception:", ex );
        }
        catch ( InvocationTargetException ex )
        {
            LOGGER.error( "Exception:", ex );
        }
        catch ( NoSuchMethodException ex )
        {
            LOGGER.error( "Exception:", ex );
        }
        return false;
    }

    public static <E> boolean getAuditValues( StringBuilder oldValues,
                                              StringBuilder newValues,
                                              E oldObject,
                                              E newObject )
    {
        boolean isModified = false;
        if ( ( StringUtils.isValidObj( oldObject ) ? oldObject.getClass() : null ) == newObject.getClass() )
        {
            PropertyDescriptor[] prDesc = org.apache.commons.beanutils.PropertyUtils
                    .getPropertyDescriptors( oldObject );
            for ( PropertyDescriptor propertyDescriptor : prDesc )
            {
                String propertyName = propertyDescriptor.getName();
                if ( !IAppConstants.RESTRICTED_PROPERTIES.contains( propertyName ) )
                {
                    if ( isValueModified( oldObject, newObject, propertyName ) )
                    {
                        isModified = true;
                        try
                        {
                            Object oldValue = org.apache.commons.beanutils.PropertyUtils.getProperty( oldObject,
                                                                                                      propertyName );
                            Object newValue = org.apache.commons.beanutils.PropertyUtils.getProperty( newObject,
                                                                                                      propertyName );
                            if ( StringUtils.isNotBlank( oldValues ) && StringUtils.isValidObj( oldValue )
                                    && StringUtils.isNotBlank( oldValue.toString() ) && !oldValue.equals( 0 ) )
                            {
                                oldValues.append( IAppConstants.COMMA ).append( IAppConstants.SPACE );
                            }
                            if ( StringUtils.isNotBlank( newValues ) && StringUtils.isValidObj( newValue )
                                    && StringUtils.isNotBlank( newValue.toString() ) && !newValue.equals( 0 ) )
                            {
                                newValues.append( IAppConstants.COMMA ).append( IAppConstants.SPACE );
                            }
                            if ( StringUtils.isValidObj( oldValue ) && StringUtils.isNotBlank( oldValue.toString() )
                                    && !oldValue.toString().equals( "0" ) )
                            {
                                oldValues.append( StringUtils.capitalize( propertyName ) ).append( IAppConstants.EQUAL )
                                        .append( oldValue );
                            }
                            if ( StringUtils.isValidObj( newValue ) && StringUtils.isNotBlank( newValue.toString() )
                                    && !newValue.toString().equals( "0" ) )
                            {
                                newValues.append( StringUtils.capitalize( propertyName ) ).append( IAppConstants.EQUAL )
                                        .append( newValue );
                            }
                            //                            if ( !StringUtils.isValidObj( oldValue ) || oldValue.equals( 0 ) )
                            //                            {
                            //                                String newStr = newValue + "";
                            //                                if ( !newStr.equals( "" ) && !newStr.equals( "null" ) )
                            //                                {
                            //                                    /*oldValues.append( StringUtils.capitalize( propertyName ) )
                            //                                            .append( IAppConstants.EQUAL ).append( IAppConstants.NOTAPPLICABLE )
                            //                                            .append( IAppConstants.COMMA ).append( IAppConstants.SPACE );*/
                            //                                    newValues.append( StringUtils.capitalize( propertyName ) )
                            //                                            .append( IAppConstants.EQUAL ).append( newValue );
                            //                                }
                            //                            }
                            //                            else
                            //                            {
                            //                                oldValues.append( StringUtils.capitalize( propertyName ) ).append( IAppConstants.EQUAL )
                            //                                        .append( oldValue );
                            //                                newValues.append( StringUtils.capitalize( propertyName ) ).append( IAppConstants.EQUAL )
                            //                                        .append( newValue );
                            //                            }
                        }
                        catch ( IllegalAccessException ex )
                        {
                            LOGGER.error( "Exception:", ex );
                        }
                        catch ( InvocationTargetException ex )
                        {
                            LOGGER.error( "Exception:", ex );
                        }
                        catch ( NoSuchMethodException ex )
                        {
                            LOGGER.error( "Exception:", ex );
                        }
                    }
                }
            }
            if ( StringUtils.isBlank( oldValues ) && StringUtils.isBlank( newValues ) )
            {
                isModified = false;
            }
        }
        return isModified;
    }

    public static boolean createAuditLog( final StringBuilder oldValues,
                                          final StringBuilder newValues,
                                          final String mappingId,
                                          final String createdBy,
                                          final String inRemarks,
                                          final String inAction,
                                          final String module,
                                          final String inClientIp,
                                          final String inServerIp )
    {
        CrmAuditLogPojo auditLog = new CrmAuditLogPojo();
        auditLog.setEvents( inAction );
        auditLog.setMappingId( mappingId );
        auditLog.setModule( module );
        if ( StringUtils.contains( newValues.toString(), IAppConstants.COMMA ) )
        {
            auditLog.setNewValues( StringUtils.substring( newValues.toString(), 0,
                                                          ( newValues.lastIndexOf( newValues.toString() ) - 1 ) ) );
        }
        if ( StringUtils.contains( oldValues.toString(), IAppConstants.COMMA ) )
        {
            auditLog.setOldValues( StringUtils.substring( oldValues.toString(), 0,
                                                          ( oldValues.lastIndexOf( oldValues.toString() ) - 1 ) ) );
        }
        auditLog.setRemarks( inRemarks );
        auditLog.setCreatedBy( createdBy );
        auditLog.setServiceIp( CRMUtils.getServerIp() );
        auditLog.setClientIp( inClientIp );
        auditLog.setServerIp( inServerIp );
        CrmAuditLogPojo savedAuditLog = CRMServiceUtils.auditLog( auditLog );
        if ( StringUtils.isValidObj( savedAuditLog ) && savedAuditLog.getAuditLogId() > 0 )
        {
            return true;
        }
        else
            return false;
    }

    public static boolean createAuditLog( final StringBuilder oldValues,
                                          final StringBuilder newValues,
                                          final String mappingId,
                                          final String createdBy,
                                          final String inRemarks,
                                          final long events,
                                          final String module,
                                          final String inClientIp,
                                          final String inServerIp )
    {
        CrmAuditLogPojo auditLog = new CrmAuditLogPojo();
        auditLog.setEvents( events + "" );
        auditLog.setMappingId( mappingId );
        auditLog.setModule( module );
        if ( StringUtils.contains( newValues.toString(), IAppConstants.COMMA ) )
        {
            auditLog.setNewValues( StringUtils.substring( newValues.toString(), 0,
                                                          ( newValues.lastIndexOf( newValues.toString() ) - 1 ) ) );
        }
        if ( StringUtils.contains( oldValues.toString(), IAppConstants.COMMA ) )
        {
            auditLog.setOldValues( StringUtils.substring( oldValues.toString(), 0,
                                                          ( oldValues.lastIndexOf( oldValues.toString() ) - 1 ) ) );
        }
        auditLog.setRemarks( inRemarks );
        auditLog.setCreatedBy( createdBy );
        auditLog.setServiceIp( CRMUtils.getServerIp() );
        auditLog.setClientIp( inClientIp );
        auditLog.setServerIp( inServerIp );
        CrmAuditLogPojo savedAuditLog = CRMServiceUtils.auditLog( auditLog );
        if ( StringUtils.isValidObj( savedAuditLog ) && savedAuditLog.getAuditLogId() > 0 )
        {
            return true;
        }
        else
            return false;
    }

    public static CrmAuditLogPojo createAuditLog( final CrmAuditLogPojo inCrmAuditLogPojo )
    {
        if ( StringUtils.endsWith( inCrmAuditLogPojo.getNewValues(), IAppConstants.COMMA ) )
        {
            inCrmAuditLogPojo.setNewValues( StringUtils
                    .substring( inCrmAuditLogPojo.getNewValues(), 0,
                                ( inCrmAuditLogPojo.getNewValues().lastIndexOf( inCrmAuditLogPojo.getNewValues() )
                                        - 1 ) ) );
        }
        if ( StringUtils.endsWith( inCrmAuditLogPojo.getOldValues(), IAppConstants.COMMA ) )
        {
            inCrmAuditLogPojo.setOldValues( StringUtils
                    .substring( inCrmAuditLogPojo.getOldValues(), 0,
                                ( inCrmAuditLogPojo.getOldValues().lastIndexOf( inCrmAuditLogPojo.getOldValues() )
                                        - 1 ) ) );
        }
        inCrmAuditLogPojo.setServiceIp( CRMUtils.getServerIp() );
        return CRMServiceUtils.auditLog( inCrmAuditLogPojo );
    }

    public static long createActivityLog( final String oldValues,
                                          final String newValues,
                                          final String customerId,
                                          final String createdBy,
                                          final String ticketId,
                                          final String action,
                                          final String reason,
                                          final String inClientIp,
                                          final String inServerIp )
    {
        CrmCustomerActivityPojo activityLog = new CrmCustomerActivityPojo();
        activityLog.setOldValue( oldValues );
        activityLog.setNewValue( newValues );
        activityLog.setCustomerId( customerId );
        activityLog.setAction( action );
        activityLog.setTicketId( ticketId );
        activityLog.setReason( reason );
        activityLog.setCreatedBy( createdBy );
        activityLog.setServiceIp( CRMUtils.getServerIp() );
        activityLog.setClientIp( inClientIp );
        activityLog.setServerIp( inServerIp );
        return CRMServiceUtils.createActivityLog( activityLog );
    }

    public static long createActivityLog( final CrmCustomerActivityPojo inCrmActivityLogPojo )
    {
        ConfigDto configDto = new ConfigDto();
        configDto.setCustomerActivityPojo( inCrmActivityLogPojo );
        try
        {
            configDto = CRMServicesProxy.getInstance()
                    .getCRMConfigService( IGlobalConstants.REMOTE, IGlobalConstants.APP )
                    .customerActivityOps( ServiceParameter.CREATE.getParameter(), configDto );
            if ( StringUtils.equals( configDto.getStatusCode(), CRMServiceCode.CRM001.getStatusCode() ) )
            {
                return configDto.getCustomerActivityPojo().getActivityId();
            }
        }
        catch ( SOAPException ex )
        {
            LOGGER.error( "Exception in Activity Log", ex );
        }
        return 0;
    }

    public static <E> List<String> getAllPropertyNames( E e )
    {
        List<String> properties = null;
        PropertyDescriptor[] prDesc = org.apache.commons.beanutils.PropertyUtils.getPropertyDescriptors( e );
        if ( StringUtils.isValidObj( prDesc ) )
        {
            properties = new ArrayList<String>();
            for ( PropertyDescriptor descriptor : prDesc )
            {
                String propertyName = descriptor.getName();
                properties.add( propertyName );
            }
            if ( properties.contains( "class" ) )
            {
                properties.remove( "class" );
            }
        }
        return properties;
    }

    public static String getMyAccountRandomPassword()
    {
        String charset = "0123456789" + "abcdefghijklmnopqrstuvwxyz" + "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        Random rand = new Random( System.currentTimeMillis() );
        StringBuffer sb = new StringBuffer();
        for ( int i = 0; i < 8; i++ )
        {
            int pos = rand.nextInt( charset.length() );
            sb.append( charset.charAt( pos ) );
        }
        return sb.toString();
    }

    public static String getRandomPassword()
    {
        String charset = "!@#$%^&*()" + "0123456789" + "abcdefghijklmnopqrstuvwxyz" + "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        Random rand = new Random( System.currentTimeMillis() );
        //LOGGER.info( "rand is:" + rand );
        StringBuffer sb = new StringBuffer();
        for ( int i = 0; i <= 8; i++ )
        {
            int pos = rand.nextInt( charset.length() );
            //  LOGGER.info( "position is:" + pos );
            sb.append( charset.charAt( pos ) );
            //LOGGER.info( "final string sb is:" + sb.toString() );
        }
        return sb.toString();
    }

    public static String generateCustToken( String customerId )
    {
        StringBuffer buffer = new StringBuffer();
        String returnValue = "";
        buffer.append( customerId ).append( System.currentTimeMillis() );
        try
        {
            returnValue = URLEncoder.encode( EncryptionUtil.encrypt( buffer.toString() ), "UTF-8" );
        }
        catch ( Exception ex )
        {
            LOGGER.error( ex.getMessage(), ex );
        }
        return returnValue;
    }

    //----------------------------------------------------------------------------------------------------------------------------
    public static <E, T> E getDBValues( Class<E> cl, String inCriteriaParam, T inValue )
    {
        Session session = null;
        try
        {
            session = HibernateUtil.getSessionFactory().openSession();
            return getDBValues( cl, inCriteriaParam, inValue, session );
        }
        catch ( Exception ex )
        {
            LOGGER.error( ex.getMessage(), ex );
        }
        finally
        {
            closeSession( session );
        }
        return null;
    }

    public static <E, T> E getDBValues( Class<E> cl, String inCriteriaParam, T inValue, final Session session )
    {
        if ( StringUtils.isValidObj( inValue ) )
        {
            try
            {
                Criteria criteria = null;
                criteria = session.createCriteria( cl ).add( Restrictions.eq( inCriteriaParam, inValue ) );
                criteria.setCacheable( true );
                criteria.setCacheRegion( cl.getName() );
                List<E> details = criteria.list();
                if ( CommonValidator.isValidCollection( details ) )
                {
                    return details.get( 0 );
                }
            }
            catch ( Exception ex )
            {
                LOGGER.error( ex.getMessage(), ex );
            }
        }
        return null;
    }

    public static <E, T> List<CrmNetworkConfigurationsPojo> getDBValuesIgnoreCase( Class<E> cl,
                                                                                   String inCriteriaParam,
                                                                                   T inValue,
                                                                                   final Session session,
                                                                                   String inCriteriaParam2 )
    {
        if ( StringUtils.isValidObj( inValue ) )
        {
            try
            {
                Criteria criteria = null;
                criteria = session.createCriteria( cl );
                Criterion crit = Restrictions.ilike( inCriteriaParam, (String) inValue, MatchMode.ANYWHERE );
                Criterion crit1 = Restrictions.ilike( inCriteriaParam2, (String) inValue, MatchMode.ANYWHERE );
                criteria.add( Restrictions.or( crit, crit1 ) );
                criteria.setCacheable( true );
                criteria.setCacheRegion( cl.getName() );
                List<E> details = criteria.list();
                if ( CommonValidator.isValidCollection( details ) )
                {
                    return (List<CrmNetworkConfigurationsPojo>) details;
                }
            }
            catch ( Exception ex )
            {
                LOGGER.error( ex.getMessage(), ex );
            }
        }
        return null;
    }

    public static <E, T> List<E> getDBValueList( Class<E> cl, String inCriteriaParam, T inValue )
    {
        Session session = null;
        try
        {
            session = HibernateUtil.getSessionFactory().openSession();
            return getDBValueList( cl, inCriteriaParam, inValue, session );
        }
        catch ( Exception ex )
        {
            LOGGER.error( ex.getMessage(), ex );
        }
        finally
        {
            closeSession( session );
        }
        return null;
    }

    public static <E, T> List<E> getDBValueList( Class<E> cl, String inCriteriaParam, T inValue, final Session session )
    {
        return getDBValueList( cl, inCriteriaParam, inValue, null, true, session );
    }

    public static <E, T> List<E> getDBValueList( Class<E> cl,
                                                 String inCriteriaParam,
                                                 T inValue,
                                                 String inOrderBy,
                                                 boolean ascending,
                                                 final Session session )
    {
        try
        {
            Criteria criteria = null;
            criteria = session.createCriteria( cl ).add( Restrictions.eq( inCriteriaParam, inValue ) );
            if ( StringUtils.isNotBlank( inOrderBy ) )
            {
                if ( ascending )
                {
                    criteria.addOrder( Order.asc( inOrderBy ) );
                }
                else
                {
                    criteria.addOrder( Order.desc( inOrderBy ) );
                }
            }
            criteria.setCacheable( true );
            criteria.setCacheRegion( cl.getName() );
            List<E> details = criteria.list();
            if ( CommonValidator.isValidCollection( details ) )
            {
                return details;
            }
        }
        catch ( Exception ex )
        {
            LOGGER.error( ex.getMessage(), ex );
        }
        return null;
    }

    public static <E> E getDBValues( Class<E> cl, final Serializable inRecordId )
    {
        Session session = null;
        try
        {
            if ( StringUtils.isValidObj( inRecordId ) && StringUtils.numericValue( inRecordId.toString() ) > 0 )
            {
                session = HibernateUtil.getSessionFactory().openSession();
                E details = (E) session.get( cl, inRecordId );
                if ( StringUtils.isValidObj( details ) )
                {
                    return details;
                }
            }
        }
        catch ( Exception ex )
        {
            LOGGER.error( ex.getMessage(), ex );
        }
        finally
        {
            closeSession( session );
        }
        return null;
    }

    public static <E> List<E> getDBValueList( Class<E> cl, Map<String, Object> criteriaMap )
    {
        Session session = null;
        try
        {
            session = HibernateUtil.getSessionFactory().openSession();
            return getDBValueList( cl, criteriaMap, session );
        }
        catch ( Exception ex )
        {
            LOGGER.error( ex.getMessage(), ex );
        }
        finally
        {
            closeSession( session );
        }
        return null;
    }

    public static <E> List<E> getDBValueList( Class<E> cl, Map<String, Object> criteriaMap, final Session session )
    {
        return getDBValueList( cl, criteriaMap, null, false, session );
    }

    public static <E> List<E> getDBValueList( Class<E> cl,
                                              Map<String, Object> criteriaMap,
                                              String inOrderBy,
                                              boolean ascending )
    {
        Session session = null;
        try
        {
            session = HibernateUtil.getSessionFactory().openSession();
            return getDBValueList( cl, criteriaMap, inOrderBy, ascending, session );
        }
        catch ( Exception ex )
        {
            LOGGER.error( ex.getMessage(), ex );
        }
        finally
        {
            closeSession( session );
        }
        return null;
    }

    public static <E> List<E> getDBValueList( Class<E> cl,
                                              Map<String, Object> criteriaMap,
                                              String inOrderBy,
                                              boolean ascending,
                                              final Session session )
    {
        try
        {
            Criteria criteria = null;
            criteria = session.createCriteria( cl );
            if ( StringUtils.isValidObj( criteriaMap ) && !criteriaMap.isEmpty() )
            {
                Set<String> keySet = criteriaMap.keySet();
                for ( String key : keySet )
                {
                    Object obj = criteriaMap.get( key );
                    if ( obj instanceof List )
                    {
                        criteria.add( Restrictions.in( key, (List) obj ) );
                    }
                    else
                    {
                        criteria.add( Restrictions.eq( key, obj ) );
                    }
                }
            }
            if ( StringUtils.isNotBlank( inOrderBy ) )
            {
                if ( ascending )
                {
                    criteria.addOrder( Order.asc( inOrderBy ) );
                }
                else
                {
                    criteria.addOrder( Order.desc( inOrderBy ) );
                }
            }
            List<E> details = criteria.list();
            if ( CommonValidator.isValidCollection( details ) )
            {
                return details;
            }
        }
        catch ( Exception ex )
        {
            LOGGER.error( ex.getMessage(), ex );
        }
        return null;
    }

    public static <E> E getDBValue( Class<E> cl, Map<String, Object> criteriaMap, String inOrderBy, boolean ascending )
    {
        Session session = null;
        try
        {
            session = HibernateUtil.getSessionFactory().openSession();
            return getDBValue( cl, criteriaMap, inOrderBy, ascending, session );
        }
        catch ( Exception ex )
        {
            LOGGER.error( ex.getMessage(), ex );
        }
        finally
        {
            closeSession( session );
        }
        return null;
    }

    public static <E> E getDBValue( Class<E> cl,
                                    Map<String, Object> criteriaMap,
                                    String inOrderBy,
                                    boolean ascending,
                                    Session session )
    {
        try
        {
            Criteria criteria = null;
            criteria = session.createCriteria( cl );
            if ( StringUtils.isValidObj( criteriaMap ) && !criteriaMap.isEmpty() )
            {
                Set<String> keySet = criteriaMap.keySet();
                for ( String key : keySet )
                {
                    Object obj = criteriaMap.get( key );
                    if ( obj instanceof List )
                    {
                        criteria.add( Restrictions.in( key, (List) obj ) );
                    }
                    else
                    {
                        criteria.add( Restrictions.eq( key, obj ) );
                    }
                }
            }
            if ( StringUtils.isNotBlank( inOrderBy ) )
            {
                if ( ascending )
                {
                    criteria.addOrder( Order.asc( inOrderBy ) );
                }
                else
                {
                    criteria.addOrder( Order.desc( inOrderBy ) );
                }
            }
            criteria.setCacheable( true );
            criteria.setCacheRegion( cl.getName() );
            List<E> details = criteria.list();
            if ( CommonValidator.isValidCollection( details ) )
            {
                return details.get( 0 );
            }
        }
        catch ( Exception ex )
        {
            LOGGER.error( ex.getMessage(), ex );
        }
        return null;
    }

    public static <E> E mergeDBValues( E object )
    {
        Session session = null;
        Transaction transaction = null;
        try
        {
            session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            object = (E) session.merge( object );
            transaction.commit();
        }
        catch ( Exception ex )
        {
            LOGGER.error( ex.getMessage(), ex );
            return null;
        }
        finally
        {
            rollback( transaction );
            closeSession( session );
        }
        return object;
    }

    public static <E> long saveDBValues( E object )
    {
        Session session = null;
        Transaction transaction = null;
        long generatedId = 0l;
        if ( StringUtils.isValidObj( object ) )
        {
            try
            {
                session = HibernateUtil.getSessionFactory().openSession();
                transaction = session.beginTransaction();
                generatedId = (Long) session.save( object );
                transaction.commit();
            }
            catch ( Exception ex )
            {
                LOGGER.error( ex.getMessage(), ex );
                return 0;
            }
            finally
            {
                closeSession( session );
                if ( generatedId > 0 )
                {
                    HibernateUtil.evictAll( object.getClass().getName(), null, null );
                }
            }
        }
        return generatedId;
    }

    public static <E> void fillActivityDetails( E oldObj,
                                                E newObj,
                                                CrmCustomerActivityPojo inActivity,
                                                CRMCustomerActivityActions inActivityAction )
    {
        boolean toProcess = false;
        if ( StringUtils.isValidObj( inActivity ) && StringUtils.isValidObj( inActivityAction ) )
        {
            toProcess = true;
            if ( StringUtils.isNotBlank( inActivityAction.getOldValue() ) )
            {
                toProcess = false;
                inActivity.setOldValue( inActivityAction.getOldValue() );
            }
            if ( StringUtils.isNotBlank( inActivityAction.getNewValue() ) )
            {
                toProcess = false;
                inActivity.setNewValue( inActivityAction.getNewValue() );
            }
        }
        if ( toProcess )
        {
            if ( StringUtils.isValidObj( oldObj ) )
            {
                String property = inActivityAction.getOldProperty();
                String value = IAppConstants.DASH;
                if ( StringUtils.isNotBlank( property ) )
                {
                    if ( StringUtils.contains( property, IAppConstants.COMMA ) )
                    {
                        String[] props = property.split( IAppConstants.COMMA );
                        StringBuilder sb = new StringBuilder();
                        for ( String prop : props )
                        {
                            value = CRMUtils.getSimpleProperty( oldObj, prop );
                            if ( StringUtils.isNotBlank( inActivityAction.getPropertyDetail() ) )
                            {
                                value = getActivityValue( inActivityAction, value );
                            }
                            sb.append( value );
                            if ( StringUtils.equals( prop, "currentCpeMacId" )
                                    || StringUtils.equals( prop, "currentSlaveMacId" ) )
                            {
                                sb.append( IAppConstants.COMMA );
                            }
                            else
                            {
                                sb.append( IAppConstants.SPACE );
                            }
                        }
                        if ( StringUtils.equals( inActivityAction.getActionDesc(),
                                                 CRMCustomerActivityActions.MAC_CHANGE.getActionDesc() ) )
                        {
                            value = StringUtils.removeEnd( sb.toString(), IAppConstants.COMMA );
                        }
                        else
                        {
                            value = sb.toString();
                        }
                    }
                    else
                    {
                        value = CRMUtils.getSimpleProperty( oldObj, property );
                        if ( StringUtils.isNotBlank( inActivityAction.getPropertyDetail() ) )
                        {
                            value = getActivityValue( inActivityAction, value );
                        }
                    }
                }
                inActivity.setOldValue( value );
            }
            else
            {
                inActivity.setOldValue( IAppConstants.DASH );
            }
            if ( StringUtils.isValidObj( newObj ) )
            {
                String property = inActivityAction.getNewProperty();
                String value = IAppConstants.DASH;
                if ( StringUtils.isNotBlank( property ) )
                {
                    if ( StringUtils.contains( property, IAppConstants.COMMA ) )
                    {
                        String[] props = property.split( IAppConstants.COMMA );
                        StringBuilder sb = new StringBuilder();
                        for ( String prop : props )
                        {
                            value = CRMUtils.getSimpleProperty( newObj, prop );
                            if ( StringUtils.isNotBlank( inActivityAction.getPropertyDetail() ) )
                            {
                                value = getActivityValue( inActivityAction, value );
                            }
                            sb.append( value );
                            if ( StringUtils.equals( prop, "currentCpeMacId" )
                                    || StringUtils.equals( prop, "currentSlaveMacId" ) )
                            {
                                sb.append( IAppConstants.COMMA );
                            }
                            else
                            {
                                sb.append( IAppConstants.SPACE );
                            }
                        }
                        if ( StringUtils.equals( inActivityAction.getActionDesc(),
                                                 CRMCustomerActivityActions.MAC_CHANGE.getActionDesc() ) )
                        {
                            value = StringUtils.removeEnd( sb.toString(), IAppConstants.COMMA );
                        }
                        else
                        {
                            value = sb.toString();
                        }
                    }
                    else
                    {
                        value = CRMUtils.getSimpleProperty( newObj, property );
                        if ( StringUtils.isNotBlank( inActivityAction.getPropertyDetail() ) )
                        {
                            value = getActivityValue( inActivityAction, value );
                        }
                    }
                }
                inActivity.setNewValue( value );
            }
            else
            {
                inActivity.setNewValue( IAppConstants.DASH );
            }
        }
    }

    //        public static void main( String[] args )
    //        {
    //            CrmUserPojo oldUserPojo = new CrmUserPojo();
    //            CrmUserPojo newUserPojo = new CrmUserPojo();
    //            oldUserPojo.setEmailId( "ABCD" );
    //            newUserPojo.setEmailId( "ABC" );
    //            oldUserPojo.setRecordId( 123 );
    //            newUserPojo.setRecordId( 1234 );
    //            System.out.println( isValueModified( oldUserPojo, newUserPojo, "emailId" ) );
    //            System.out.println( isValueModified( oldUserPojo, newUserPojo, "recordId" ) );
    //        }
    private static String getActivityValue( CRMCustomerActivityActions inActivityAction, String value )
    {
        if ( StringUtils.startsWith( inActivityAction.getPropertyDetail(), CRMUtils.class.getSimpleName() ) )
        {
            String type = StringUtils.substring( inActivityAction.getPropertyDetail(),
                                                 CRMUtils.class.getSimpleName().length() + 1 );
            value = CRMUtils.getEnumValue( type, String.valueOf( value ) );
        }
        else if ( StringUtils.startsWith( inActivityAction.getPropertyDetail(),
                                          CrmPlanMasterPojo.class.getSimpleName() ) )
        {
            CrmPlanMasterPojo planMaster = getDBValues( CrmPlanMasterPojo.class, "planCode", value );
            if ( StringUtils.isValidObj( planMaster ) )
            {
                value = planMaster.getPlanName();
            }
            else
            {
                value = IAppConstants.DASH;
            }
        }
        else if ( StringUtils.startsWith( inActivityAction.getPropertyDetail(),
                                          CrmPartnerNetworkConfigPojo.class.getSimpleName() ) )
        {
            CrmPartnerNetworkConfigPojo networkConfig = getDBValues( CrmPartnerNetworkConfigPojo.class,
                                                                     StringUtils.numericValue( value ) );
            if ( StringUtils.isValidObj( networkConfig ) )
            {
                value = networkConfig.getNasPortId();
            }
            else
            {
                value = IAppConstants.DASH;
            }
        }
        else if ( StringUtils.contains( inActivityAction.getPropertyDetail(), IAppConstants.COMMA )
                && StringUtils.contains( inActivityAction.getPropertyDetail(), IAppConstants.COLON ) )
        {
            Map<String, String> possibleValueMap = CrmPojoToBilling
                    .getPossibleValueMap( inActivityAction.getPropertyDetail() );
            if ( StringUtils.isValidObj( possibleValueMap ) && possibleValueMap.containsKey( value ) )
            {
                value = possibleValueMap.get( value );
            }
            else
            {
                value = IAppConstants.DASH;
            }
        }
        else
        {
        }
        return value;
    }

    public static CrmQrcDto generateAutoTicket( String inCustomerId,
                                                QRCRolCategories autoTicket,
                                                String inSubSubCategory,
                                                String inUser )
        throws SOAPException
    {
        return generateAutoTicket( inCustomerId, autoTicket, inSubSubCategory, inUser, null );
    }

    public static CrmQrcDto generateAutoTicket( String inCustomerId,
                                                QRCRolCategories autoTicket,
                                                String inSubSubCategory,
                                                String inUser,
                                                String inRemarks )
        throws SOAPException
    {
        CrmSrTicketsPojo ticket = new CrmSrTicketsPojo();
        ticket.setResolutionType( (byte) autoTicket.getResolutionType() );
        return generateAutoTicket( ticket, inCustomerId, autoTicket, inSubSubCategory, inUser, inRemarks );
    }

    public static CrmQrcDto generateAutoTicket( CrmSrTicketsPojo inTicket,
                                                String inCustomerId,
                                                QRCRolCategories autoTicket,
                                                String inSubSubCategory,
                                                String inUser,
                                                String inRemarks )
        throws SOAPException
    {
        if ( StringUtils.isValidObj( autoTicket ) && StringUtils.isNotBlank( inCustomerId ) )
        {
            CrmQrcDto crmQrcDto = new CrmQrcDto();
            inTicket.setQrcCategory( autoTicket.getCategory() );
            inTicket.setQrcSubCategory( autoTicket.getSubCategory() );
            if ( StringUtils.isBlank( inSubSubCategory ) )
            {
                inTicket.setQrcSubSubCategory( autoTicket.getSubSubCategory() );
            }
            else
            {
                inTicket.setQrcSubSubCategory( inSubSubCategory );
            }
            inTicket.setQrcType( autoTicket.getQrcType() );
            inTicket.setMappingId( inCustomerId );
            inTicket.setModuleType( CRMRequestType.QRC.getRequestCode() );
            inTicket.setCreatedBy( inUser );
            crmQrcDto.setCrmSrTicketsPojo( inTicket );
            if ( StringUtils.isNotBlank( inRemarks ) )
            {
                CrmTicketHistoryPojo ticketHistory = new CrmTicketHistoryPojo();
                ticketHistory.setCreatedBy( inUser );
                ticketHistory.setAction( CrmActionEnum.OPENED.getActionCode() );
                ticketHistory.setRemarks( inRemarks );
                crmQrcDto.setTicketHistory( ticketHistory );
            }
            crmQrcDto = CRMServicesProxy.getInstance().getCrmQrcService( IGlobalConstants.REMOTE, IGlobalConstants.APP )
                    .ticketOperations( CrmActionEnum.OPENED.getActionCode(), CrmSrTicketsPojo.class.getSimpleName(),
                                       crmQrcDto );
            return crmQrcDto;
        }
        return null;
    }
}
