package com.np.tele.crm.alerts.bm;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.np.tele.crm.cap.bm.ICrmCapManager;
import com.np.tele.crm.cap.form.CrmNPCrfFormHelper;
import com.np.tele.crm.client.CRMAlertsClient;
import com.np.tele.crm.client.MasterDataClient;
import com.np.tele.crm.common.utils.CRMCacheManager;
import com.np.tele.crm.common.utils.CommonUtils;
import com.np.tele.crm.common.utils.QRCCacheManager;
import com.np.tele.crm.common.utils.ValidationUtil;
import com.np.tele.crm.config.bm.ICrmConfigManager;
import com.np.tele.crm.constants.CRMCustomerActivityActions;
import com.np.tele.crm.constants.CRMDisplayListConstants;
import com.np.tele.crm.constants.CRMOperationStages;
import com.np.tele.crm.constants.CRMParameter;
import com.np.tele.crm.constants.CRMRCAReason;
import com.np.tele.crm.constants.CRMRegex;
import com.np.tele.crm.constants.CRMRequestType;
import com.np.tele.crm.constants.CRMServiceCode;
import com.np.tele.crm.constants.CRMStatusCode;
import com.np.tele.crm.constants.IAppConstants;
import com.np.tele.crm.constants.IDateConstants;
import com.np.tele.crm.constants.QRCRolCategories;
import com.np.tele.crm.constants.ServiceParameter;
import com.np.tele.crm.finance.bm.IFinanceManager;
import com.np.tele.crm.finance.forms.FinanceForm;
import com.np.tele.crm.gis.bm.GISUtils;
import com.np.tele.crm.gis.bm.IGISManager;
import com.np.tele.crm.gis.forms.GISForm;
import com.np.tele.crm.inbox.bm.IInboxManager;
import com.np.tele.crm.inbox.form.InboxForm;
import com.np.tele.crm.lms.bm.ILmsManager;
import com.np.tele.crm.masterdata.bm.IAppDataManager;
import com.np.tele.crm.masterdata.bm.IMasterBMngr;
import com.np.tele.crm.pojos.ContentPojo;
import com.np.tele.crm.qrc.bm.IQrcManager;
import com.np.tele.crm.qrc.config.bm.IQrcConfigManager;
import com.np.tele.crm.qrc.forms.QrcForm;
import com.np.tele.crm.service.client.AlertsDto;
import com.np.tele.crm.service.client.AreaPojo;
import com.np.tele.crm.service.client.CityPojo;
import com.np.tele.crm.service.client.ConfigDto;
import com.np.tele.crm.service.client.CrmAddressDetailsPojo;
import com.np.tele.crm.service.client.CrmCapDto;
import com.np.tele.crm.service.client.CrmCmsFilePojo;
import com.np.tele.crm.service.client.CrmCustomerDetailsPojo;
import com.np.tele.crm.service.client.CrmFinanceDto;
import com.np.tele.crm.service.client.CrmNationalityDetailsPojo;
import com.np.tele.crm.service.client.CrmParameterPojo;
import com.np.tele.crm.service.client.CrmPartnerDetailsPojo;
import com.np.tele.crm.service.client.CrmPartnerMappingPojo;
import com.np.tele.crm.service.client.CrmPartnerNetworkConfigPojo;
import com.np.tele.crm.service.client.CrmPaymentDetailsPojo;
import com.np.tele.crm.service.client.CrmPlanDetailsPojo;
import com.np.tele.crm.service.client.CrmPlanMasterPojo;
import com.np.tele.crm.service.client.CrmQrcActionTakenPojo;
import com.np.tele.crm.service.client.CrmQrcCategoriesPojo;
import com.np.tele.crm.service.client.CrmQrcCategoryBinMappingPojo;
import com.np.tele.crm.service.client.CrmQrcDto;
import com.np.tele.crm.service.client.CrmQrcRootCausePojo;
import com.np.tele.crm.service.client.CrmQrcSubCategoriesPojo;
import com.np.tele.crm.service.client.CrmQrcSubSubCategoriesPojo;
import com.np.tele.crm.service.client.CrmRcaReasonPojo;
import com.np.tele.crm.service.client.CrmRolesPojo;
import com.np.tele.crm.service.client.CrmSrTicketsPojo;
import com.np.tele.crm.service.client.CrmUserPojo;
import com.np.tele.crm.service.client.CrmuserDetailsDto;
import com.np.tele.crm.service.client.EmailCcBccPojo;
import com.np.tele.crm.service.client.EmailTemplatePojo;
import com.np.tele.crm.service.client.LmsPojo;
import com.np.tele.crm.service.client.MasterDataDto;
import com.np.tele.crm.service.client.PartnerPojo;
import com.np.tele.crm.service.client.ProjectsPojo;
import com.np.tele.crm.service.client.SOAPException_Exception;
import com.np.tele.crm.service.client.SmsTemplatePojo;
import com.np.tele.crm.service.client.SocietyNetworkPartnerPojo;
import com.np.tele.crm.service.client.SocietyPojo;
import com.np.tele.crm.service.client.StatePojo;
import com.np.tele.crm.usrmngmnt.bm.IUserBMngr;
import com.np.tele.crm.utils.CRMUtils;
import com.np.tele.crm.utils.DateUtils;
import com.np.tele.crm.utils.SortingComparator;
import com.np.tele.crm.utils.StringUtils;
import com.np.validator.util.CommonValidator;

public class DwrManagerImpl
    implements IDwrManager
{
    private static final Logger LOGGER = Logger.getLogger( DwrManagerImpl.class );

    public IAppDataManager getAppDataManager()
    {
        return (IAppDataManager) IAppConstants.flyWeightBeanMap.get( IAppConstants.APP_DATA_MANAGER );
    }

    public IUserBMngr getUserMngmntBm()
    {
        return (IUserBMngr) IAppConstants.flyWeightBeanMap.get( IAppConstants.USER_MANAGEMENT );
    }

    public IMasterBMngr getMasterDataBm()
    {
        return (IMasterBMngr) IAppConstants.flyWeightBeanMap.get( IAppConstants.MASTER_DATA );
    }

    public ISmsAlert getSmsAlertImpl()
    {
        return (ISmsAlert) IAppConstants.flyWeightBeanMap.get( IAppConstants.SMS_ALERT );
    }

    public IEmailAlert getEmailAlert()
    {
        return (IEmailAlert) IAppConstants.flyWeightBeanMap.get( IAppConstants.EMAIL_ALERT );
    }

    public IGISManager getGisManagerImpl()
    {
        return (IGISManager) IAppConstants.flyWeightBeanMap.get( IAppConstants.GIS_MANAGER );
    }

    public ICrmConfigManager getConfigManagerImpl()
    {
        return (ICrmConfigManager) IAppConstants.flyWeightBeanMap.get( IAppConstants.CRM_CONFIG_MANAGER );
    }

    public IFinanceManager getFinanceManagerBm()
    {
        return (IFinanceManager) IAppConstants.flyWeightBeanMap.get( IAppConstants.FINANCE_MANAGER );
    }

    public IQrcManager getQrcManagerBm()
    {
        return (IQrcManager) IAppConstants.flyWeightBeanMap.get( IAppConstants.QRC_MANAGER );
    }

    public ILmsManager getLmsManagerBm()
    {
        return (ILmsManager) IAppConstants.flyWeightBeanMap.get( IAppConstants.LMS_MANAGER );
    }

    public ICrmCapManager getCrmCapManagerBm()
    {
        return (ICrmCapManager) IAppConstants.flyWeightBeanMap.get( IAppConstants.CRM_CAP_MANAGER );
    }

    public IQrcConfigManager getQrcConfigManagerBm()
    {
        return (IQrcConfigManager) IAppConstants.flyWeightBeanMap.get( IAppConstants.QRC_CONFIG_MANAGER );
    }

    public IInboxManager getInboxManagerImpl()
    {
        return (IInboxManager) IAppConstants.flyWeightBeanMap.get( IAppConstants.INBOX_MANAGER );
    }

    @Override
    public List<ProjectsPojo> getProject( String inProjectType )
    {
        List<ProjectsPojo> projectsPojos = getMasterDataBm().getProjectList( inProjectType );
        if ( StringUtils.isValidObj( projectsPojos ) && projectsPojos.isEmpty() )
        {
            return null;
        }
        return projectsPojos;
    }

    @Override
    public List<PartnerPojo> getPartners()
    {
        List<PartnerPojo> partnerList = CRMCacheManager.getActivePartnerList();
        if ( StringUtils.isValidObj( partnerList ) && partnerList.isEmpty() )
        {
            return null;
        }
        SortingComparator<PartnerPojo> sortComparator = new SortingComparator<PartnerPojo>( "partnerName" );
        Collections.sort( partnerList, sortComparator );
        sortComparator = null;
        return partnerList;
    }

    public List<StatePojo> getState()
    {
        return GISUtils.getActiveStates( IAppConstants.COUNTRY_INDIA );
    }

    public List<CityPojo> getCity( int stateId )
    {
        List<CityPojo> cityPojos = null;
        try
        {
            cityPojos = GISUtils.getActiveCities( IAppConstants.COUNTRY_INDIA, stateId );
            if ( cityPojos.isEmpty() )
            {
                LOGGER.info( "city list empty" );
                return null;
            }
            SortingComparator<CityPojo> sortComparator = new SortingComparator<CityPojo>( "cityName" );
            Collections.sort( cityPojos, sortComparator );
            sortComparator = null;
        }
        catch ( Exception ex )
        {
            LOGGER.error( "Error in getCity mehtod:: ", ex );
        }
        return cityPojos;
    }

    public List<CityPojo> getCityByStateName( String stateName )
    {
        List<CityPojo> cityPojos = null;
        try
        {
            cityPojos = GISUtils.getActiveCities( IAppConstants.COUNTRY_INDIA, stateName );
            if ( cityPojos.isEmpty() )
            {
                LOGGER.info( "city list empty" );
                return null;
            }
            SortingComparator<CityPojo> sortComparator = new SortingComparator<CityPojo>( "cityName" );
            Collections.sort( cityPojos, sortComparator );
            sortComparator = null;
        }
        catch ( Exception ex )
        {
            LOGGER.error( "Error in get City by state name mehtod:: ", ex );
        }
        return cityPojos;
    }

    public List<AreaPojo> getAreaByCity( String stateName, String cityName )
    {
        List<AreaPojo> areaPojos = null;
        try
        {
            areaPojos = GISUtils.getActiveAreas( IAppConstants.COUNTRY_INDIA, stateName, cityName );
            if ( areaPojos.isEmpty() )
            {
                LOGGER.info( "area list empty" );
                return null;
            }
            SortingComparator<AreaPojo> sortComparator = new SortingComparator<AreaPojo>( "area" );
            Collections.sort( areaPojos, sortComparator );
            sortComparator = null;
        }
        catch ( Exception ex )
        {
            LOGGER.error( "Error in get area by city name mehtod:: ", ex );
        }
        return areaPojos;
    }

    /*  public List<LocalityPojo> getLocalityByArea( String stateName, String cityName, String areaName )
      {
          List<LocalityPojo> localityPojos = null;
          try
          {
              localityPojos = GISUtils.getActiveLocalities( IAppConstants.COUNTRY_INDIA, stateName, cityName, areaName );
              if ( localityPojos.isEmpty() )
              {
                  LOGGER.info( "locality list empty" );
                  return null;
              }
              SortingComparator<LocalityPojo> sortComparator = new SortingComparator<LocalityPojo>( "locality" );
              Collections.sort( localityPojos, sortComparator );
              sortComparator = null;
          }
          catch ( Exception ex )
          {
              LOGGER.error( "Error in get locality by area name mehtod:: ", ex );
          }
          return localityPojos;
      } */
    public List<SocietyPojo> getSocietyByLocality( String stateName, String cityName, String areaName )
    {
        List<SocietyPojo> societyPojos = null;
        try
        {
            LOGGER.info( "getSocietyByLocality" );
            societyPojos = GISUtils.getActiveSocieties( IAppConstants.COUNTRY_INDIA, stateName, cityName, areaName );
            if ( societyPojos.isEmpty() )
            {
                LOGGER.info( "society list empty" );
                return null;
            }
            SortingComparator<SocietyPojo> sortComparator = new SortingComparator<SocietyPojo>( "societyName" );
            Collections.sort( societyPojos, sortComparator );
            sortComparator = null;
        }
        catch ( Exception ex )
        {
            LOGGER.error( "Error in get society by locality name mehtod:: ", ex );
        }
        LOGGER.info( "getSocietyByLocality" + societyPojos.size() );
        return societyPojos;
    }

    public List<SocietyPojo> getFeasibleSocieties( String stateName,
                                                   String cityName,
                                                   String areaName,
                                                   String productName )
    {
        List<SocietyPojo> societyPojos = null;
        try
        {
            societyPojos = GISUtils.getFeasibleSocieties( IAppConstants.COUNTRY_INDIA, stateName, cityName, areaName,
                                                          productName );
            if ( societyPojos.isEmpty() )
            {
                LOGGER.info( "society list empty basis of Product Name:: " + productName );
                return null;
            }
            SortingComparator<SocietyPojo> sortComparator = new SortingComparator<SocietyPojo>( "societyName" );
            Collections.sort( societyPojos, sortComparator );
            sortComparator = null;
        }
        catch ( Exception ex )
        {
            LOGGER.error( "Error in get society by product name mehtod:: ", ex );
        }
        return societyPojos;
    }

    public List<AreaPojo> getArea( int inCityId )
    {
        List<AreaPojo> areaPojos = null;
        try
        {
            areaPojos = GISUtils.getActiveAreas( IAppConstants.COUNTRY_INDIA, null, inCityId );
            if ( areaPojos.isEmpty() )
            {
                LOGGER.info( "Area list empty" );
                return null;
            }
            SortingComparator<AreaPojo> sortComparator = new SortingComparator<AreaPojo>( "area" );
            Collections.sort( areaPojos, sortComparator );
            sortComparator = null;
        }
        catch ( Exception ex )
        {
            LOGGER.error( "Error in getArea mehtod:: ", ex );
        }
        return areaPojos;
    }

    public List<AreaPojo> getFilterArea( String inState, String inCity, String inAreaFilter )
    {
        List<AreaPojo> areaPojos = null;
        List<AreaPojo> filAreaPojos = new ArrayList<AreaPojo>();
        try
        {
            areaPojos = GISUtils.getActiveAreas( IAppConstants.COUNTRY_INDIA, inState, inCity );
            if ( areaPojos.isEmpty() )
            {
                LOGGER.info( "Area list empty" );
                return null;
            }
            else
            {
                if ( !StringUtils.isEmpty( inAreaFilter ) )
                {
                    for ( AreaPojo areaPojo : areaPojos )
                    {
                        if ( StringUtils.containsIgnoreCase( areaPojo.getArea(), inAreaFilter ) )
                        {
                            filAreaPojos.add( areaPojo );
                        }
                    }
                }
                else
                {
                    filAreaPojos.addAll( areaPojos );
                }
            }
        }
        catch ( Exception ex )
        {
            LOGGER.error( "Error in getFilterArea mehtod:: ", ex );
        }
        return filAreaPojos;
    }

    //    public List<LocalityPojo> getLocality( int inAreaId )
    //    {
    //        List<LocalityPojo> localityPojos = null;
    //        try
    //        {
    //            localityPojos = GISUtils.getActiveLocalities( IAppConstants.COUNTRY_INDIA, null, null, inAreaId );
    //            if ( localityPojos.isEmpty() )
    //            {
    //                LOGGER.info( "Locality list empty" );
    //                return null;
    //            }
    //            SortingComparator<LocalityPojo> sortComparator = new SortingComparator<LocalityPojo>( "locality" );
    //            Collections.sort( localityPojos, sortComparator );
    //            sortComparator = null;
    //        }
    //        catch ( Exception ex )
    //        {
    //            LOGGER.error( "Error in getLocality mehtod:: ", ex );
    //        }
    //        return localityPojos;
    //    }
    //    public List<LocalityPojo> getFilterLocality( String inState, String inCity, String inArea, String inLocalityFilter )
    //    {
    //        List<LocalityPojo> localityPojos = null;
    //        List<LocalityPojo> filLocalityPojos = new ArrayList<LocalityPojo>();
    //        try
    //        {
    //            localityPojos = GISUtils.getActiveLocalities( IAppConstants.COUNTRY_INDIA, inState, inCity, inArea );
    //            if ( localityPojos.isEmpty() )
    //            {
    //                LOGGER.info( "Locality list empty" );
    //                return null;
    //            }
    //            else
    //            {
    //                LOGGER.info( "Filter Locality search:" + inLocalityFilter );
    //                if ( StringUtils.isValidObj( inLocalityFilter ) )
    //                {
    //                    for ( LocalityPojo localityPojo : localityPojos )
    //                    {
    //                        if ( StringUtils.containsIgnoreCase( localityPojo.getLocality(), inLocalityFilter ) )
    //                        {
    //                            filLocalityPojos.add( localityPojo );
    //                        }
    //                    }
    //                }
    //                else
    //                {
    //                    filLocalityPojos.addAll( localityPojos );
    //                }
    //            }
    //        }
    //        catch ( Exception ex )
    //        {
    //            LOGGER.error( "Error in getFilterLocality mehtod:: ", ex );
    //        }
    //        return filLocalityPojos;
    //    }
    public List<SocietyPojo> getFilterSociety( String inState, String inCity, String inArea, String inSocietyFilter )
    {
        List<SocietyPojo> societyPojos = null;
        List<SocietyPojo> filSocietyPojos = new ArrayList<SocietyPojo>();
        try
        {
            societyPojos = GISUtils.getActiveSocieties( IAppConstants.COUNTRY_INDIA, inState, inCity, inArea );
            if ( societyPojos.isEmpty() )
            {
                LOGGER.info( "Society list empty" );
                return null;
            }
            else
            {
                LOGGER.info( "Filter Society search:" + inSocietyFilter );
                if ( StringUtils.isValidObj( inSocietyFilter ) )
                {
                    for ( SocietyPojo societyPojo : societyPojos )
                    {
                        if ( StringUtils.containsIgnoreCase( societyPojo.getSocietyName(), inSocietyFilter ) )
                        {
                            filSocietyPojos.add( societyPojo );
                        }
                    }
                }
                else
                {
                    filSocietyPojos.addAll( societyPojos );
                }
            }
        }
        catch ( Exception ex )
        {
            LOGGER.error( "Error in getFilterSociety mehtod:: ", ex );
        }
        return filSocietyPojos;
    }

    public List<CrmRolesPojo> getRoles()
    {
        List<CrmRolesPojo> crmRoleList = null;
        LOGGER.info( "In DWR IMPL getRoles Method called::" );
        try
        {
            MasterDataClient masterClient = new MasterDataClient();
            MasterDataDto masterDataDto = masterClient.getMasterData( null, CRMParameter.CRM_ROLES.getParameter() );
            crmRoleList = masterDataDto.getCrmRolesPojoList();
            LOGGER.info( "CRM Role List size" + crmRoleList.size() + masterDataDto.getStatusDesc() );
        }
        catch ( SOAPException_Exception ex )
        {
            LOGGER.error( "Error in getRoles mehtod:: ", ex );
        }
        return crmRoleList;
    }

    // populate parameter list in dropdown.
    @Override
    public List<CrmParameterPojo> getParameters( String inType )
    {
        int projectId = Integer.parseInt( inType );
        List<CrmParameterPojo> parameterList = getMasterDataBm().getParameters( projectId, CRMParameter.ALERT, null );
        return parameterList;
    }

    @Override
    public String checkProjectNameAvailability( String inProjectName )
    {
        String pName = "Project Name available";
        return pName;
    }

    @Override
    public String convertToStr( Object inTmpltBody )
    {
        LOGGER.info( "Befor Coversion Template Body" + inTmpltBody );
        byte[] bodyArr = (byte[]) inTmpltBody;
        String body = new String( bodyArr );
        LOGGER.info( "Converted Template Body" + body );
        return body;
    }

    @Override
    public Map<String, String> getTemplate( Long inId, String inType )
    {
        LOGGER.info( "In Side get Template and the template type is : " + inType );
        if ( inType.equalsIgnoreCase( "sms" ) )
        {
            return getSmsTemplate( inId, inType );
        }
        else
        {
            return getEmailTemplate( inId, inType );
        }
    }

    @Override
    public Map<String, String> getSmsTemplate( Long inId, String inType )
    {
        AlertsDto alertDto = new AlertsDto();
        SmsTemplatePojo smsTemplatePojo = new SmsTemplatePojo();
        smsTemplatePojo.setSmsTemplateId( inId );
        alertDto.setSmsTemplatePojo( smsTemplatePojo );
        CRMAlertsClient crmAlertsClient = new CRMAlertsClient();
        try
        {
            alertDto = crmAlertsClient.alertTemplate( ServiceParameter.LIST.getParameter(),
                                                      CRMParameter.SMS_ALERT.getParameter(), alertDto );
        }
        catch ( Exception ex )
        {
            LOGGER.error( "Error while getting SMS Template view Through DWR", ex );
        }
        List<SmsTemplatePojo> smList = new ArrayList<SmsTemplatePojo>();
        smList = alertDto.getSmsTemplatePojos();
        smsTemplatePojo = smList.get( 0 );
        Map<String, String> map = new HashMap<String, String>();
        byte[] temp = smsTemplatePojo.getSmsTemplate();
        String smsBody = new String( temp );
        map.put( "templateName", smsTemplatePojo.getSmsTemplateName() );
        map.put( "body", smsBody );
        map.put( "status", CRMStatusCode.getStatus( smsTemplatePojo.getStatus() ) );
        map.put( "smsType", smsTemplatePojo.getSmsType() );
        return map;
    }

    @Override
    public Map<String, String> getEmailTemplate( Long inTemplateId, String inType )
    {
        EmailTemplatePojo emailTemplatePojo = getEmailAlert().searchByTemplateId( inTemplateId );
        Map<String, String> map = new HashMap<String, String>();
        byte[] body = emailTemplatePojo.getEmailTemplate();
        String emailBody = new String( body );
        List<EmailCcBccPojo> emailCcBccs = emailTemplatePojo.getEmailCcBccs();
        String strEmailCC = "";
        String strEmailBcc = "";
        if ( !emailCcBccs.isEmpty() )
        {
            StringBuilder emailCC = new StringBuilder();
            StringBuilder emailBcc = new StringBuilder();
            for ( EmailCcBccPojo ccbccPojo : emailCcBccs )
            {
                if ( StringUtils.equals( CRMParameter.CC.getParameter(), ccbccPojo.getEmailType() ) )
                {
                    emailCC.append( ccbccPojo.getEmailId() );
                    emailCC.append( IAppConstants.COMMA );
                }
                else if ( StringUtils.equals( CRMParameter.BCC.getParameter(), ccbccPojo.getEmailType() ) )
                {
                    emailBcc.append( ccbccPojo.getEmailId() );
                    emailBcc.append( IAppConstants.COMMA );
                }
            }
            if ( StringUtils.isValidObj( emailCC ) && emailCC.length() > 0 )
            {
                strEmailCC = emailCC.substring( 0, ( emailCC.lastIndexOf( IAppConstants.COMMA ) ) );
            }
            if ( StringUtils.isValidObj( emailBcc ) && emailBcc.length() > 0 )
            {
                strEmailBcc = emailBcc.substring( 0, ( emailBcc.lastIndexOf( IAppConstants.COMMA ) ) );
            }
        }
        map.put( "templateName", emailTemplatePojo.getEmailTemplateName() );
        map.put( "body", emailBody );
        map.put( "status", CRMStatusCode.getStatus( emailTemplatePojo.getStatus() ) );
        map.put( "from", emailTemplatePojo.getEmailFrom() );
        map.put( "subject", emailTemplatePojo.getEmailSubject() );
        map.put( "cc", strEmailCC );
        map.put( "bcc", strEmailBcc );
        return map;
    }

    public boolean resendActivationLink( final String userId )
    {
        return getUserMngmntBm().resendActivationLink( userId );
    }

    public String getSelectedGis()
    {
        return "state";
    }

    @Override
    public List<PartnerPojo> getBussinessPartners()
    {
        List<PartnerPojo> partnerList = CRMCacheManager.getBussinessPartners();
        if ( StringUtils.isValidObj( partnerList ) && partnerList.isEmpty() )
        {
            return null;
        }
        return partnerList;
    }

    @Override
    public List<ContentPojo> getProduct()
    {
        List<ContentPojo> productList = CRMUtils.getProducts();
        if ( StringUtils.isValidObj( productList ) && productList.isEmpty() )
        {
            return null;
        }
        return productList;
    }

    @Override
    public String getOption82String( String inPartnerId, String[] nameof82, String[] valueof82, String oltType )
    {
        StringBuffer option82 = new StringBuffer();
        String delimiter = "/";
        List<CrmPartnerNetworkConfigPojo> crmNetworkConfigPojos = null;
        try
        {
            LOGGER.info( "Partner Id::" + inPartnerId );
            LOGGER.info( "Size of nameof82::" + nameof82.length );
            MasterDataDto masterDataDto = new MasterDataDto();
            crmNetworkConfigPojos = new ArrayList<CrmPartnerNetworkConfigPojo>();
            CrmPartnerNetworkConfigPojo crmPartnerNetworkConfigPojo = new CrmPartnerNetworkConfigPojo();
            masterDataDto = getMasterDataBm().getPartnerDetailsId( inPartnerId );
            if ( CommonValidator.isValidCollection( masterDataDto.getCrmPartnerDetailsPojos() ) )
            {
                crmPartnerNetworkConfigPojo.setPartnerDetailsId( masterDataDto.getCrmPartnerDetailsPojos().get( 0 )
                        .getRecordId() );
                crmPartnerNetworkConfigPojo.setOltType( oltType );
                masterDataDto.setCrmPartnerNetworkConfigPojo( crmPartnerNetworkConfigPojo );
                masterDataDto = getMasterDataBm().masterOperation( ServiceParameter.SEARCH.getParameter(),
                                                                   CRMParameter.OPTION82.getParameter(), masterDataDto );
                if ( CommonValidator.isValidCollection( masterDataDto.getCrmPartnerNetworkConfigPojos() ) )
                {
                    LOGGER.info( "Size of PartnerNetworkPojo::"
                            + masterDataDto.getCrmPartnerNetworkConfigPojos().size() );
                    crmNetworkConfigPojos = masterDataDto.getCrmPartnerNetworkConfigPojos();
                }
            }
            List<CrmParameterPojo> crmParameterList = getMasterDataBm().getParameters( 0,
                                                                                       CRMParameter.OPTION82,
                                                                                       CRMParameter.INTERNAL
                                                                                               .getParameter() );
            LOGGER.info( "CRM Parameter" + crmParameterList.size() );
            for ( int i = 0; i < crmParameterList.size(); i++ )
            {
                outer: for ( int j = 0; j < crmNetworkConfigPojos.size(); j++ )
                {
                    if ( crmNetworkConfigPojos.get( j ).getParameterId() == crmParameterList.get( i ).getParameterId() )
                    {
                        inner: for ( int k = 0; k < nameof82.length; k++ )
                        {
                            if ( crmParameterList.get( i ).getParameterName().equals( nameof82[k] )
                                    && Integer.parseInt( valueof82[k] ) != 0 )
                            {
                                if ( StringUtils.isNotBlank( option82.toString() ) )
                                    option82.append( delimiter );
                                option82.append( valueof82[k] );
                                break inner;
                            }
                        }
                        break outer;
                    }
                }
            }
            LOGGER.info( "Final String option82 ::" + option82.toString() );
        }
        catch ( Exception ex )
        {
            LOGGER.info( "Getting Error While, Fetching Value: " + ex );
        }
        return option82.toString();
    }

    @Override
    public CrmCustomerDetailsPojo getCustomerDetails( String inCustCrfId, String idType )
    {
        ConfigDto inConfigDto = new ConfigDto();
        LOGGER.info( "inCustCrfId........" + inCustCrfId );
        LOGGER.info( "idType........." + idType );
        if ( !StringUtils.isEmpty( idType ) && ( StringUtils.equals( idType, "CRF_ID" ) ) )
        {
            CrmCustomerDetailsPojo pojo = new CrmCustomerDetailsPojo();
            pojo.setCrfId( inCustCrfId );
            inConfigDto.setCustomerDetail( pojo );
        }
        else if ( !StringUtils.isEmpty( idType ) && ( StringUtils.equals( idType, "CUST_ID" ) ) )
        {
            CrmCustomerDetailsPojo pojo = new CrmCustomerDetailsPojo();
            pojo.setCustomerId( inCustCrfId );
            inConfigDto.setCustomerDetail( pojo );
        }
        ConfigDto configDto = getConfigManagerImpl().getCustomerDetails( inConfigDto );
        LOGGER.info( "size........" + configDto.getCustomerDetailPojos().size() );
        if ( configDto.getCustomerDetailPojos().size() > 0 )
        {
            CrmCustomerDetailsPojo customerDetail = configDto.getCustomerDetailPojos().get( 0 );
            if ( StringUtils.isValidObj( customerDetail ) )
            {
                if ( StringUtils.isValidObj( customerDetail.getStatus() ) )
                {
                    customerDetail.setStatus( CRMStatusCode.getStatus( customerDetail.getStatus() ) );
                }
                if ( StringUtils.isValidObj( customerDetail.getServiceType() ) )
                {
                    customerDetail.setServiceType( ( CRMDisplayListConstants.getValueByCode( CRMParameter.SERVICE_TYPE
                            .getParameter(), customerDetail.getServiceType() ) ) );
                }
                if ( StringUtils.isValidObj( customerDetail.getActivationDate() ) )
                {
                    customerDetail.setInstallationStatus( CRMDisplayListConstants.POSTINSTALLATION.getCode() );
                }
                else
                {
                    customerDetail.setInstallationStatus( CRMDisplayListConstants.PREINSTALLATION.getCode() );
                }
            }
            return customerDetail;
        }
        else
        {
            return null;
        }
    }

    /*
     * public static void main( String[] args ) { DwrManagerImpl dwr=new
     * DwrManagerImpl(); dwr.getCustomerDetails( "RA000002", "CRF_ID" ); }
     */
    public List<CityPojo> getAllCity()
    {
        List<CityPojo> cityPojos = null;
        try
        {
            LOGGER.info( "Get All Cities List" );
            cityPojos = GISUtils.getAllActiveCities();
            if ( cityPojos.isEmpty() )
            {
                LOGGER.info( "city list empty" );
                return null;
            }
            LOGGER.info( "Cities List Size::::" + cityPojos.size() );
        }
        catch ( Exception ex )
        {
            LOGGER.error( "Error in getCity mehtod:: ", ex );
        }
        return cityPojos;
    }

    public List<String> getReceiptNo( String inPartnerId )
    {
        List<String> receiptList = new ArrayList<String>();
        MasterDataDto masterDataDto = new MasterDataDto();
        CrmRcaReasonPojo crmRcaReasonPojo = new CrmRcaReasonPojo();
        try
        {
            LOGGER.info( "Partner ID::: " + inPartnerId );
            crmRcaReasonPojo.setCategory( CRMRCAReason.INA.getStatusCode() );
            crmRcaReasonPojo.setSubCategory( "CashReceipt" );
            List<PartnerPojo> partnerList = getMasterDataBm().getPartnersById( inPartnerId );
            LOGGER.info( "partnerList Size::" + partnerList.size() );
            if ( !partnerList.isEmpty() && partnerList.size() > 0 )
            {
                LOGGER.info( "PartnerAlias" + partnerList.get( 0 ).getPartnerName() );
                crmRcaReasonPojo.setSubSubCategory( partnerList.get( 0 ).getPartnerName() );
            }
            masterDataDto.setCrmRcaReason( crmRcaReasonPojo );
            masterDataDto = getMasterDataBm().masterOperation( masterDataDto );
            LOGGER.info( "receipt list size  " + masterDataDto.getCrmRcaReasonsList().size() );
            if ( !masterDataDto.getCrmRcaReasonsList().isEmpty() && masterDataDto.getCrmRcaReasonsList().size() > 0 )
            {
                LOGGER.info( "receipt list size:   " + masterDataDto.getCrmRcaReasonsList().size() );
                for ( CrmRcaReasonPojo rcaReasonPojo : masterDataDto.getCrmRcaReasonsList() )
                {
                    receiptList.add( rcaReasonPojo.getCategoryValue() );
                }
            }
        }
        catch ( Exception ex )
        {
            LOGGER.error( "Error in get recieptNo mehtod:: ", ex );
        }
        return receiptList;
    }

    @Override
    public List<CrmCmsFilePojo> getFileDetails( String todate, String fromDate, String fileType )
    {
        LOGGER.info( "toDate..........." + todate + fromDate );
        List<CrmCmsFilePojo> fileList = new ArrayList<CrmCmsFilePojo>();
        FinanceForm financeForm = new FinanceForm();
        financeForm.setToDate( todate );
        financeForm.setFromDate( fromDate );
        CrmCmsFilePojo cmsFilePojo = new CrmCmsFilePojo();
        cmsFilePojo.setCmsFileType( fileType );
        cmsFilePojo.setStatus( CRMStatusCode.ARCHIVE.getStatusCode() );
        financeForm.setCrmCmsFilePojo( cmsFilePojo );
        LOGGER.info( "ToDate" + financeForm.getToDate() + "fromDate" + financeForm.getFromDate() );
        LOGGER.info( "Finance MAnage Object" + getFinanceManagerBm() );
        CrmFinanceDto crmFinanceDto = getFinanceManagerBm().getFileList( financeForm );
        if ( StringUtils.isValidObj( crmFinanceDto ) )
        {
            LOGGER.info( "fileList" );
            fileList = crmFinanceDto.getCmsFiles();
            LOGGER.info( "fileList.........." + fileList.size() );
        }
        return fileList;
    }

    public String bindCPEMACId( String cpeMacId, String CRFId )
    {
        // Call billing API
        String date = "D" + "," + "10-Jan-2014";
        return date;
    }

    public String saveCRFReference( String crfRefNo, String custRecordId, String inUserId )
    {
        String returnValue = "";
        CrmCapDto crmCapDto = null;
        if ( StringUtils.isNotEmpty( custRecordId ) && StringUtils.isNotEmpty( inUserId ) )
        {
            LOGGER.info( "CRF Record ID::::: " + custRecordId );
            CrmCustomerDetailsPojo crmCustomerDetailsPojo = new CrmCustomerDetailsPojo();
            try
            {
                crmCustomerDetailsPojo.setRecordId( StringUtils.numericValue( custRecordId ) );
                crmCustomerDetailsPojo.setCrfReferenceNo( crfRefNo );
                if ( CrmNPCrfFormHelper.validCRFReference( crmCustomerDetailsPojo ) )
                {
                    LOGGER.info( "Crf Reference Validated" );
                    /*     crmCapDto = getCrmCapManagerBm().getCAFReferenceNo( crmCustomerDetailsPojo );
                         if ( StringUtils.isValidObj( crmCapDto )
                                 && StringUtils.equals( CRMServiceCode.CRM001.getStatusCode(), crmCapDto.getStatusCode() )
                                 && CommonValidator.isValidCollection( crmCapDto.getCustomerDetailsPojosList() ) )
                         {
                             returnValue = "CRF Reference Number are exist or already used.";
                         }
                         else
                         {*/
                    crmCapDto = getCrmCapManagerBm().saveCRFReference( crmCustomerDetailsPojo, inUserId );
                    if ( StringUtils.isValidObj( crmCapDto )
                            && StringUtils.equals( CRMServiceCode.CRM001.getStatusCode(), crmCapDto.getStatusCode() ) )
                    {
                        returnValue = "CRF Reference Number saved successfully.";
                    }
                    else
                    {
                        returnValue = crmCapDto.getStatusDesc();
                    }
                    //}
                }
                else
                {
                    returnValue = "Please provide CRF Reference No.";
                }
            }
            catch ( Exception ex )
            {
                returnValue = "Error whie saving CRF Reference No." + ex.getMessage();
                LOGGER.error( "error occured save CRF reference in Dwr", ex );
            }
        }
        else
        {
            returnValue = "Please provide valid details.";
        }
        LOGGER.info( "Status to Display:" + returnValue );
        return returnValue;
    }

    public String saveISRReferenceNo( String isrRefNo, String inCustRecordId, String inUserId )
    {
        String returnValue = "";
        if ( StringUtils.isNotEmpty( inCustRecordId ) && StringUtils.isNotEmpty( inUserId ) )
        {
            LOGGER.info( "CRF Record ID::::: " + inCustRecordId );
            CrmCustomerDetailsPojo crmCustomerDetailsPojo = new CrmCustomerDetailsPojo();
            try
            {
                crmCustomerDetailsPojo.setRecordId( Long.parseLong( inCustRecordId ) );
                crmCustomerDetailsPojo.setIsrReferenceNo( isrRefNo );
                if ( CrmNPCrfFormHelper.validISRReferenceNo( crmCustomerDetailsPojo ) )
                {
                    LOGGER.info( "ISR Reference Validated" );
                    CrmCapDto crmCapDto = getCrmCapManagerBm().saveISRReferenceNo( crmCustomerDetailsPojo, inUserId );
                    if ( StringUtils.isValidObj( crmCapDto )
                            && StringUtils.equals( CRMServiceCode.CRM001.getStatusCode(), crmCapDto.getStatusCode() ) )
                    {
                        returnValue = "ISR Reference Number saved successfully.";
                    }
                    else
                    {
                        returnValue = crmCapDto.getStatusDesc();
                    }
                }
                else
                {
                    returnValue = "Please provide ISR Reference No.";
                }
            }
            catch ( Exception ex )
            {
                returnValue = "Error whie saving ISR Reference No." + ex.getMessage();
                LOGGER.info( "error occured save CRF reference in Dwr", ex );
            }
        }
        else
        {
            returnValue = "Please provide valid details.";
        }
        LOGGER.info( "Status to Display:" + returnValue );
        return returnValue;
    }

    @Override
    public List<PartnerPojo> getProductByNPartner( String partnerName )
    {
        LOGGER.info( " getProductByPartner " + partnerName );
        List<PartnerPojo> finalpojos = getMasterDataBm().getPartnerByName( partnerName,
                                                                           CRMDisplayListConstants.NETWORK_PARTNER
                                                                                   .getCode() );
        return finalpojos;
    }

    public List<ContentPojo> getProductByNPartnerId( String partnerId )
    {
        LOGGER.info( " getProductByPartner " + partnerId );
        List<ContentPojo> finalpojos = getMasterDataBm()
                .getProductsByPartnerId( partnerId, CRMDisplayListConstants.NETWORK_PARTNER.getCode() );
        return finalpojos;
    }

    public List<ContentPojo> getProductByNPNSociety( String partnerId, String societyId, String areaId )
    {
        List<SocietyNetworkPartnerPojo> societyNetworkPartnerPojos = new ArrayList<SocietyNetworkPartnerPojo>();
        List<ContentPojo> pojos = new ArrayList<ContentPojo>();
        String product = null;
        List<SocietyPojo> soList = GISUtils.getActiveSocieties( IAppConstants.COUNTRY_INDIA, null, null,
                                                                Long.parseLong( areaId ) );
        for ( SocietyPojo spojo : soList )
        {
            if ( spojo.getSocietyId() == Long.parseLong( societyId ) )
            {
                societyNetworkPartnerPojos = spojo.getSocietyNetworkPartners();
            }
        }
        if ( CommonValidator.isValidCollection( societyNetworkPartnerPojos ) )
        {
            for ( SocietyNetworkPartnerPojo snPojo : societyNetworkPartnerPojos )
            {
                if ( snPojo.getPartnerId() == Long.parseLong( partnerId ) )
                {
                    product = snPojo.getProductName();
                    break;
                }
            }
        }
        List<ContentPojo> finalpojos = getMasterDataBm()
                .getProductsByPartnerId( partnerId, CRMDisplayListConstants.NETWORK_PARTNER.getCode() );
        for ( ContentPojo finalpojo : finalpojos )
        {
            if ( StringUtils.equals( product, finalpojo.getContentValue() ) )
            {
                ContentPojo p = new ContentPojo();
                p.setContentName( finalpojo.getContentName() );
                p.setContentValue( finalpojo.getContentValue() );
                pojos.add( p );
            }
        }
        return pojos;
    }

    // @Override
    // public List<ContentPojo> getConditionalList( int inIndex, String inQRC,
    // String inCategory, String inSubCategory )
    // {
    // if ( !StringUtils.isEmpty( inCategory ) && !StringUtils.isEmpty(
    // inSubCategory ) )
    // {
    // return getQrcManagerBm().getDisplaySet( inIndex, inQRC, inCategory,
    // inSubCategory );
    // }
    // else if ( !StringUtils.isEmpty( inCategory ) && StringUtils.isEmpty(
    // inSubCategory ) )
    // {
    // return getQrcManagerBm().getDisplaySet( inIndex, inQRC, inCategory );
    // }
    // else
    // {
    // return getQrcManagerBm().getDisplaySet( inIndex, inQRC );
    // }
    // }
    @Override
    public int getResolutionType( long inQrcCategoryId )
    {
        int resolutionIndex = 0;
        CrmQrcCategoriesPojo qrcCategoriesPojo = getQrcManagerBm().getCrmQrcCategoriesPojo( inQrcCategoryId );
        // if ( StringUtils.isValidObj( qrcCategoriesPojo )
        // && StringUtils.isValidObj( qrcCategoriesPojo.getResolutionType() ) )
        // {
        // resolutionIndex = qrcCategoriesPojo.getResolutionType();
        // }
        return resolutionIndex;
    }

    @Override
    public List<ContentPojo> getCRFMasterCategories( String category, String subCategory )
    {
        LOGGER.info( "Calling DWR for getting Categories::" );
        LOGGER.info( "Category Value:: " + category );
        LOGGER.info( "SubCategory value:: " + subCategory );
        List<ContentPojo> categoryList = CRMUtils.getCategories( category, subCategory );
        LOGGER.info( "Categories list size:: " + categoryList.size() );
        return categoryList;
    }

    @Override
    public String crfIdMasterValidation( String inCrfId, String inProduct )
    {
        String found = "";
        LOGGER.info( "inCrfId : " + inCrfId + " inProduct : " + inProduct );
        if ( StringUtils.isNotBlank( inCrfId.trim() ) && StringUtils.isNotBlank( inProduct ) )
        {
            found = "NotInMaster";
            String status = getMasterDataBm()
                    .validateCRFInMaster( StringUtils.upperCase( StringUtils.trim( inCrfId ) ), null );
            if ( StringUtils.equals( CRMServiceCode.CRM001.getStatusCode(), status ) )
            {
                found = "InMaster";
                if ( getLmsManagerBm().checkCrfMapping( inCrfId ) )
                {
                    found = "InLms";
                }
                else
                {
                    if ( getLmsManagerBm().checkCrfMappingINA( inCrfId ) )
                    {
                        found = "InINA";
                    }
                }
                LOGGER.info( "inCrfId : " + inCrfId );
            }
        }
        LOGGER.info( "crf id found  : " + found );
        return found;
    }

    /*  @Override
      public List<String> getUsersByParameter( String inParam, String inValue, String inFunctionalBin )
      {
          return getLmsManagerBm().getUsersByParameter( inParam, inValue, inFunctionalBin );
      }*/
    // @Override
    // public String getConnectionTypeList( String inValue )
    // {
    // LOGGER.info( "Dwr inValue found  : " + inValue );
    // List<CrmRcaReasonPojo> connectionList =
    // CRMCacheManager.getConnectionTypes();
    // if ( CommonValidator.isValidCollection( connectionList ) )
    // {
    // for ( CrmRcaReasonPojo crmRcaReasonPojo : connectionList )
    // {
    // if ( StringUtils.equals( crmRcaReasonPojo.getCategoryValue(), inValue ) )
    // {
    // return crmRcaReasonPojo.getValueAlias();
    // }
    // }
    // }
    // return null;
    // }
    @Override
    public CrmPlanMasterPojo getPlanAmounts( String inPlanService, String inPlanCode, String planType )
    {
        return CommonUtils.getPlanDetails( inPlanService, inPlanCode, planType );
    }

    // @Override
    // public List<CrmPartnerNetworkConfigPojo> getNasportId( long inRecordId )
    // {
    // MasterDataDto masterDataDto = null;
    // CrmPartnerNetworkConfigPojo crmPartnerNetworkConfigPojo = null;
    // LOGGER.info( "RecordId ::" + inRecordId );
    // try
    // {
    // masterDataDto = new MasterDataDto();
    // crmPartnerNetworkConfigPojo = new CrmPartnerNetworkConfigPojo();
    // crmPartnerNetworkConfigPojo.setRecordId( inRecordId );
    // crmPartnerNetworkConfigPojo.setStatus(
    // CRMStatusCode.ACTIVE.getStatusCode() );
    // masterDataDto.setCrmPartnerNetworkConfigPojo( crmPartnerNetworkConfigPojo
    // );
    // masterDataDto = getMasterDataBm().masterOperation(
    // ServiceParameter.SEARCH.getParameter(),
    // CRMParameter.OPTION82.getParameter(), masterDataDto );
    // if ( CommonValidator.isValidCollection( (
    // masterDataDto.getCrmPartnerNetworkConfigPojos() ) ) )
    // {
    // LOGGER.info( "PartnerNetworkConfigPojos Size ::"
    // + masterDataDto.getCrmPartnerNetworkConfigPojos().size() );
    // return masterDataDto.getCrmPartnerNetworkConfigPojos();
    // }
    // }
    // catch ( Exception ex )
    // {
    // LOGGER.error( "Error in get getMasterName mehtod:: ", ex );
    // }
    // return null;
    // }
    @Override
    public List<String> getNasportId( long inPartnerDetailsId, String inMasterName )
    {
        MasterDataDto masterDataDto = null;
        LOGGER.info( "PartnerDetailsId ::" + inPartnerDetailsId );
        try
        {
            masterDataDto = new MasterDataDto();
            CrmPartnerNetworkConfigPojo crmPartnerNetworkConfigPojo = new CrmPartnerNetworkConfigPojo();
            crmPartnerNetworkConfigPojo.setPartnerDetailsId( inPartnerDetailsId );
            crmPartnerNetworkConfigPojo.setStatus( CRMStatusCode.ACTIVE.getStatusCode() );
            masterDataDto.setCrmPartnerNetworkConfigPojo( crmPartnerNetworkConfigPojo );
            masterDataDto = getMasterDataBm().masterOperation( ServiceParameter.SEARCH.getParameter(),
                                                               CRMParameter.OPTION82.getParameter(), masterDataDto );
            if ( CommonValidator.isValidCollection( masterDataDto.getCrmPartnerNetworkConfigPojos() ) )
            {
                LOGGER.info( "Size of PartnerNetworkPojo::" + masterDataDto.getCrmPartnerNetworkConfigPojos().size() );
                List<CrmPartnerNetworkConfigPojo> crmPartnerNetworkConfigPojos = masterDataDto
                        .getCrmPartnerNetworkConfigPojos();
                if ( CommonValidator.isValidCollection( crmPartnerNetworkConfigPojos ) )
                {
                    Set<String> nasportIds = new TreeSet<String>();
                    for ( CrmPartnerNetworkConfigPojo crmPartnerNetworkConfig : crmPartnerNetworkConfigPojos )
                    {
                        if ( StringUtils.equals( crmPartnerNetworkConfig.getMasterName(), inMasterName ) )
                        {
                            nasportIds.add( crmPartnerNetworkConfig.getNasPortId() );
                        }
                    }
                    if ( !nasportIds.isEmpty() )
                    {
                        return new ArrayList<String>( nasportIds );
                    }
                }
            }
        }
        catch ( Exception ex )
        {
            LOGGER.error( "Error in get getMasterName mehtod:: ", ex );
        }
        return null;
    }

    /*
     * @Override public List<CrmPartnerNetworkConfigPojo> getPoolName( long
     * inRecordId, String nasPortId ) { MasterDataDto masterDataDto = null;
     * CrmPartnerNetworkConfigPojo crmPartnerNetworkConfigPojo = null;
     * LOGGER.info( "RecordId ::" + inRecordId ); try { masterDataDto = new
     * MasterDataDto(); crmPartnerNetworkConfigPojo = new
     * CrmPartnerNetworkConfigPojo(); crmPartnerNetworkConfigPojo.setRecordId(
     * inRecordId ); crmPartnerNetworkConfigPojo.setNasPortId( nasPortId );
     * crmPartnerNetworkConfigPojo.setStatus(
     * CRMStatusCode.ACTIVE.getStatusCode() );
     * masterDataDto.setCrmPartnerNetworkConfigPojo( crmPartnerNetworkConfigPojo
     * ); masterDataDto = getMasterDataBm().masterOperation(
     * ServiceParameter.SEARCH.getParameter(),
     * CRMParameter.OPTION82.getParameter(), masterDataDto ); if (
     * CommonValidator.isValidCollection( (
     * masterDataDto.getCrmPartnerNetworkConfigPojos() ) ) ) { LOGGER.info(
     * "PartnerNetworkConfigPojos Size ::" +
     * masterDataDto.getCrmPartnerNetworkConfigPojos().size() ); return
     * masterDataDto.getCrmPartnerNetworkConfigPojos(); } } catch ( Exception ex
     * ) { LOGGER.error( "Error in get getMasterName mehtod:: ", ex ); } return
     * null; }
     */
    @Override
    public List<CrmPartnerNetworkConfigPojo> getPoolName( long inPartnerDetailsId,
                                                          String inMasterName,
                                                          String inNasPortId )
    {
        MasterDataDto masterDataDto = null;
        LOGGER.info( "PartnerDetailsId ::" + inPartnerDetailsId );
        try
        {
            masterDataDto = new MasterDataDto();
            CrmPartnerNetworkConfigPojo crmPartnerNetworkConfigPojo = new CrmPartnerNetworkConfigPojo();
            crmPartnerNetworkConfigPojo.setPartnerDetailsId( inPartnerDetailsId );
            crmPartnerNetworkConfigPojo.setStatus( CRMStatusCode.ACTIVE.getStatusCode() );
            masterDataDto.setCrmPartnerNetworkConfigPojo( crmPartnerNetworkConfigPojo );
            masterDataDto = getMasterDataBm().masterOperation( ServiceParameter.SEARCH.getParameter(),
                                                               CRMParameter.OPTION82.getParameter(), masterDataDto );
            if ( CommonValidator.isValidCollection( masterDataDto.getCrmPartnerNetworkConfigPojos() ) )
            {
                LOGGER.info( "Size of PartnerNetworkPojo::" + masterDataDto.getCrmPartnerNetworkConfigPojos().size() );
                List<CrmPartnerNetworkConfigPojo> crmPartnerNetworkConfigPojos = masterDataDto
                        .getCrmPartnerNetworkConfigPojos();
                if ( CommonValidator.isValidCollection( crmPartnerNetworkConfigPojos ) )
                {
                    List<CrmPartnerNetworkConfigPojo> poolNames = new ArrayList<CrmPartnerNetworkConfigPojo>();
                    for ( CrmPartnerNetworkConfigPojo crmPartnerNetworkConfig : crmPartnerNetworkConfigPojos )
                    {
                        if ( StringUtils.equals( crmPartnerNetworkConfig.getMasterName(), inMasterName )
                                && StringUtils.equals( crmPartnerNetworkConfig.getNasPortId(), inNasPortId ) )
                        {
                            poolNames.add( crmPartnerNetworkConfig );
                        }
                    }
                    if ( !poolNames.isEmpty() )
                    {
                        return poolNames;
                    }
                }
            }
        }
        catch ( Exception ex )
        {
            LOGGER.error( "Error in get getMasterName mehtod:: ", ex );
        }
        return null;
    }

    @Override
    public String[] saveCustomerProfileDetails( String recordId,
                                                String changePropertyValue,
                                                String rolCategory,
                                                String custId,
                                                String userId,
                                                String inBrand )
    {
        LOGGER.info( "in saveCustomerProfileDetails" );
        boolean processRequest = true;
        LOGGER.info( "Security charge:: " + changePropertyValue );
        LOGGER.info( "Payment ID:: " + recordId );
        LOGGER.info( "User ID:: " + userId );
        LOGGER.info( "ROL:: " + rolCategory );
        String errMsg = "Unable to process your request.";
        CrmCapDto crmCapDto = new CrmCapDto();
        String[] str =
        { recordId, changePropertyValue, rolCategory, userId };
        if ( StringUtils.checkAllvalidObj( str, false ) )
        {
            LOGGER.info( "received details are valid" );
            if ( StringUtils.equals( rolCategory, QRCRolCategories.NAME_CHANGE.getEvent() ) )
            {
                LOGGER.info( "event: " + QRCRolCategories.NAME_CHANGE.getEvent() );
                CrmCustomerDetailsPojo crmCustomerDetailsPojo = new CrmCustomerDetailsPojo();
                String connType = StringUtils.split( changePropertyValue, "||" )[0];
                if ( StringUtils.equals( connType, CRMDisplayListConstants.INDIVIDUAL.getCode() )
                        || StringUtils.equals( connType, CRMDisplayListConstants.PROPRIETORSHIP.getCode() ) )
                {
                    String[] name = StringUtils.split( StringUtils.split( changePropertyValue, "||" )[1], "::" );
                    crmCustomerDetailsPojo.setCustFname( name[0].toUpperCase() );
                    if ( name.length > 1 )
                    {
                        crmCustomerDetailsPojo.setCustLname( name[1].toUpperCase() );
                    }
                    else
                    {
                        crmCustomerDetailsPojo.setCustLname( IAppConstants.EMPTY_STRING );
                    }
                }
                else
                {
                    crmCustomerDetailsPojo.setCustFname( StringUtils
                            .substring( changePropertyValue, changePropertyValue.indexOf( "||" ) + 2 ).toUpperCase() );
                }
                crmCustomerDetailsPojo.setRecordId( Long.parseLong( recordId ) );
                crmCustomerDetailsPojo.setLastModifiedBy( userId );
                crmCustomerDetailsPojo.setConnectionType( connType );
                crmCapDto.setActivityAction( CRMCustomerActivityActions.NAME_CHANGE.getActionDesc() );
                crmCapDto.setCustomerDetailsPojo( crmCustomerDetailsPojo );
            }
            else if ( StringUtils.equals( rolCategory, QRCRolCategories.AUTHORIZED_PERSON.getEvent() ) )
            {
                LOGGER.info( "event: " + QRCRolCategories.AUTHORIZED_PERSON.getEvent() );
                CrmCustomerDetailsPojo customerDetailsPojo = new CrmCustomerDetailsPojo();
                String[] name = StringUtils.split( changePropertyValue, "::" );
                customerDetailsPojo.setAuthSignFname( name[0].toUpperCase() );
                customerDetailsPojo.setAuthSignLname( name[1].toUpperCase() );
                customerDetailsPojo.setRecordId( Long.parseLong( recordId ) );
                customerDetailsPojo.setLastModifiedBy( userId );
                crmCapDto.setActivityAction( CRMCustomerActivityActions.AUTHORIZED_PERSON_CHANGE.getActionDesc() );
                crmCapDto.setCustomerDetailsPojo( customerDetailsPojo );
            }
            else if ( StringUtils.equals( rolCategory, QRCRolCategories.CONTACT_PERSON.getEvent() ) )
            {
                LOGGER.info( "event: " + QRCRolCategories.CONTACT_PERSON.getEvent() );
                CrmNationalityDetailsPojo nationalityDetailsPojo = new CrmNationalityDetailsPojo();
                String[] name = StringUtils.split( changePropertyValue, "::" );
                nationalityDetailsPojo.setLocalCpFname( name[0].toUpperCase() );
                nationalityDetailsPojo.setLocalCpLname( name[1].toUpperCase() );
                nationalityDetailsPojo.setRecordId( Long.parseLong( recordId ) );
                nationalityDetailsPojo.setLastModifiedBy( userId );
                crmCapDto.setActivityAction( CRMCustomerActivityActions.CONTACT_PERSON_CHANGE.getActionDesc() );
                crmCapDto.setNationalityDetailsPojo( nationalityDetailsPojo );
            }
            else if ( StringUtils.equals( rolCategory, QRCRolCategories.RMN_MODIFY.getEvent() ) )
            {
                LOGGER.info( "event: " + QRCRolCategories.RMN_MODIFY.getEvent() );
                CrmCustomerDetailsPojo crmCustomerDetailsPojo = new CrmCustomerDetailsPojo();
                crmCustomerDetailsPojo.setRmn( Long.parseLong( changePropertyValue ) );
                crmCustomerDetailsPojo.setRecordId( Long.parseLong( recordId ) );
                crmCustomerDetailsPojo.setLastModifiedBy( userId );
                crmCapDto.setActivityAction( CRMCustomerActivityActions.RMN_MODIFY.getActionDesc() );
                crmCapDto.setCustomerDetailsPojo( crmCustomerDetailsPojo );
            }
            else if ( StringUtils.equals( rolCategory, QRCRolCategories.RTN_MODIFY.getEvent() ) )
            {
                LOGGER.info( "event: " + QRCRolCategories.RTN_MODIFY.getEvent() );
                CrmCustomerDetailsPojo crmCustomerDetailsPojo = new CrmCustomerDetailsPojo();
                crmCustomerDetailsPojo.setRtn( Long.parseLong( changePropertyValue ) );
                crmCustomerDetailsPojo.setRecordId( Long.parseLong( recordId ) );
                crmCustomerDetailsPojo.setLastModifiedBy( userId );
                crmCapDto.setActivityAction( CRMCustomerActivityActions.RTN_MODIFY.getActionDesc() );
                crmCapDto.setCustomerDetailsPojo( crmCustomerDetailsPojo );
            }
            else if ( StringUtils.equals( rolCategory, QRCRolCategories.ALT_CONTACT_NO.getEvent() ) )
            {
                LOGGER.info( "event: " + QRCRolCategories.ALT_CONTACT_NO.getEvent() );
                CrmCustomerDetailsPojo crmCustomerDetailsPojo = new CrmCustomerDetailsPojo();
                crmCustomerDetailsPojo.setRecordId( Long.parseLong( recordId ) );
                crmCustomerDetailsPojo.setCustMobileNo( Long.parseLong( changePropertyValue ) );
                crmCustomerDetailsPojo.setLastModifiedBy( userId );
                crmCapDto.setActivityAction( CRMCustomerActivityActions.ALTERNATE_NUMBER_MODIFY.getActionDesc() );
                crmCapDto.setCustomerDetailsPojo( crmCustomerDetailsPojo );
            }
            else if ( StringUtils.equals( rolCategory, QRCRolCategories.REGISTERED_EMAIL_ID.getEvent() ) )
            {
                LOGGER.info( "event: " + QRCRolCategories.REGISTERED_EMAIL_ID.getEvent() );
                if ( !StringUtils.compareRegularExp( CRMRegex.PATTERN_EMAIL.getPattern(), changePropertyValue )
                        || changePropertyValue.length() > 256 )
                {
                    processRequest = false;
                    errMsg = "Invalid email address.";
                }
                else
                {
                    CrmCustomerDetailsPojo crmCustomerDetailsPojo = new CrmCustomerDetailsPojo();
                    crmCustomerDetailsPojo.setRecordId( Long.parseLong( recordId ) );
                    crmCustomerDetailsPojo.setCustEmailId( changePropertyValue );
                    crmCustomerDetailsPojo.setLastModifiedBy( userId );
                    crmCustomerDetailsPojo.setBrand( inBrand );
                    crmCapDto.setActivityAction( CRMCustomerActivityActions.EMAIL_MODIFY.getActionDesc() );
                    crmCapDto.setCustomerDetailsPojo( crmCustomerDetailsPojo );
                }
            }
            else if ( StringUtils.equals( rolCategory, QRCRolCategories.ALTERNATE_EMAIL_MODIFY.getEvent() ) )
            {
                LOGGER.info( "event: " + QRCRolCategories.ALTERNATE_EMAIL_MODIFY.getEvent() );
                if ( !StringUtils.compareRegularExp( CRMRegex.PATTERN_EMAIL.getPattern(), changePropertyValue )
                        || changePropertyValue.length() > 256 )
                {
                    processRequest = false;
                    errMsg = "Invalid email address.";
                }
                else
                {
                    CrmCustomerDetailsPojo crmCustomerDetailsPojo = new CrmCustomerDetailsPojo();
                    crmCustomerDetailsPojo.setRecordId( Long.parseLong( recordId ) );
                    crmCustomerDetailsPojo.setAltEmailId( changePropertyValue );
                    crmCustomerDetailsPojo.setLastModifiedBy( userId );
                    crmCapDto.setActivityAction( CRMCustomerActivityActions.ALTERNATE_EMAIL_MODIFY.getActionDesc() );
                    crmCapDto.setCustomerDetailsPojo( crmCustomerDetailsPojo );
                }
            }
            else if ( StringUtils.equals( rolCategory, QRCRolCategories.PAPERBILL_TO_EBILL.getEvent() )
                    || StringUtils.equals( rolCategory, QRCRolCategories.EBILL_TO_PAPERBILL.getEvent() ) )
            {
                LOGGER.info( "event: " + rolCategory );
                CrmPlanDetailsPojo planDetailsPojo = new CrmPlanDetailsPojo();
                planDetailsPojo.setBillMode( changePropertyValue );
                planDetailsPojo.setRecordId( Long.parseLong( recordId ) );
                planDetailsPojo.setLastModifiedBy( userId );
                crmCapDto.setActivityAction( CRMCustomerActivityActions.BILL_MODE_MODIFY.getActionDesc() );
                crmCapDto.setPlanDetailsPojo( planDetailsPojo );
            }
            /*
             * else if ( StringUtils.equals( rolCategory,
             * QRCRolCategories.UPDATE_CONNECTION_TYPE.getEvent() ) ) {
             * LOGGER.info( "event: " +
             * QRCRolCategories.UPDATE_CONNECTION_TYPE.getEvent() );
             * CrmCustomerDetailsPojo crmCustomerDetailsPojo = new
             * CrmCustomerDetailsPojo();
             * crmCustomerDetailsPojo.setConnectionType( changePropertyValue );
             * crmCustomerDetailsPojo.setRecordId( Long.parseLong( recordId ) );
             * crmCustomerDetailsPojo.setLastModifiedBy( userId );
             * crmCapDto.setActivityAction(
             * CRMCustomerActivityActions.CONNECTION_TYPE_CHANGE.getActionDesc()
             * ); crmCapDto.setCustomerDetailsPojo( crmCustomerDetailsPojo ); }
             */
            else if ( StringUtils.equals( rolCategory, QRCRolCategories.ADDRESS_MODIFY_BILLING.getEvent() ) )
            {
                LOGGER.info( "event: " + QRCRolCategories.ADDRESS_MODIFY_BILLING.getEvent() );
                String add[] = changePropertyValue.split( "::" );
                CrmAddressDetailsPojo crmBillAddPojo = new CrmAddressDetailsPojo();
                crmBillAddPojo.setAddLine1( add[0].toUpperCase() );
                crmBillAddPojo.setAddLine2( add[1].toUpperCase() );
                crmBillAddPojo.setAddLine3( add[2].toUpperCase() );
                crmBillAddPojo.setStateName( add[3] );
                crmBillAddPojo.setCityName( add[4] );
                crmBillAddPojo.setPincode( Integer.parseInt( add[5] ) );
                fillStateCityIds( crmBillAddPojo );
                crmBillAddPojo.setRecordId( Long.parseLong( recordId ) );
                crmBillAddPojo.setLastModifiedBy( userId );
                crmCapDto.setActivityAction( CRMCustomerActivityActions.BILLING_ADDRESS_CHANGE.getActionDesc() );
                crmCapDto.setAddressDetailsPojo( crmBillAddPojo );
            }
            else if ( StringUtils.equals( rolCategory, QRCRolCategories.ADDRESS_MODIFY_INSTALLATION.getEvent() ) )
            {
                LOGGER.info( "event: " + QRCRolCategories.ADDRESS_MODIFY_INSTALLATION.getEvent() );
                CrmAddressDetailsPojo crmInstAddPojo = new CrmAddressDetailsPojo();
                crmInstAddPojo.setAddLine1( changePropertyValue.toUpperCase() );
                crmInstAddPojo.setRecordId( Long.parseLong( recordId ) );
                crmInstAddPojo.setLastModifiedBy( userId );
                crmCapDto.setActivityAction( CRMCustomerActivityActions.INSTALLAITION_ADDRESS_CORRECTION
                        .getActionDesc() );
                crmCapDto.setAddressDetailsPojo( crmInstAddPojo );
            }
            else if ( StringUtils.equals( rolCategory, QRCRolCategories.SECURITY_DEPOSIT.getEvent() ) )
            {
                LOGGER.info( "Security charge:: " + changePropertyValue );
                LOGGER.info( "Payment ID:: " + recordId );
                CrmPaymentDetailsPojo paymentDetailsPojo = new CrmPaymentDetailsPojo();
                paymentDetailsPojo.setPaymentId( Long.parseLong( recordId ) );
                paymentDetailsPojo.setSecurityCharges( Double.parseDouble( changePropertyValue ) );
                crmCapDto.setPaymentDetailsPojo( paymentDetailsPojo );
                crmCapDto.setActivityAction( CRMCustomerActivityActions.SECURITY_DEPOSIT_CHANGE.getActionDesc() );
            }
            if ( processRequest )
            {
                crmCapDto.setChangeRequest( rolCategory );
                crmCapDto.setCustomerId( custId );
                crmCapDto.setUserId( userId );
                crmCapDto = getQrcManagerBm().saveCustomerProfileDetails( crmCapDto );
                if ( StringUtils.equals( crmCapDto.getStatusCode(), CRMServiceCode.CRM001.getStatusCode() ) )
                {
                    if ( StringUtils.equals( rolCategory, QRCRolCategories.ADDRESS_MODIFY_INSTALLATION.getEvent() )
                            || StringUtils.equals( rolCategory, QRCRolCategories.NAME_CHANGE.getEvent() )
                            || StringUtils.equals( rolCategory, QRCRolCategories.SECURITY_DEPOSIT.getEvent() ) )
                    {
                        return new String[]
                        { "success", "Youe request has been processed succesfully." };
                    }
                    else
                    {
                        return new String[]
                        {
                                "success",
                                crmCapDto.getStatusDesc() + ", Generated Ticket ID: "
                                        + crmCapDto.getGeneratedTicketId() };
                    }
                }
                else
                {
                    return new String[]
                    { "error", crmCapDto.getStatusDesc() + " " + crmCapDto.getBillingErrorCode() };
                }
            }
            else
            {
                if ( StringUtils.isNotBlank( crmCapDto.getStatusDesc() ) )
                {
                    return new String[]
                    { "error", crmCapDto.getStatusDesc() + " " + crmCapDto.getBillingErrorCode() };
                }
                return new String[]
                { "error", errMsg };
            }
        }
        return new String[]
        { "error", errMsg };
    }

    @Override
    public String[] sendEmailVerificationLink( HttpSession inSession )
    {
        LOGGER.info( "in sendEmailVerificationLink( HttpSession inSession )" );
        if ( StringUtils.isValidObj( inSession ) && StringUtils.isValidObj( inSession.getAttribute( "qrcForm" ) ) )
        {
            QrcForm qrcForm = (QrcForm) inSession.getAttribute( "qrcForm" );
            CrmCustomerDetailsPojo customerDetailsPojo = qrcForm.getCustDetailsPojo();
            if ( StringUtils.isValidObj( customerDetailsPojo ) )
            {
                if ( StringUtils.equals( customerDetailsPojo.getEmailValidationFlag(), IAppConstants.NO_CHAR ) )
                {
                    CrmCapDto capDto = new CrmCapDto();
                    capDto.setCustomerDetailsPojo( customerDetailsPojo );
                    capDto = getQrcManagerBm().sendVerificationLink( capDto );
                    if ( StringUtils.equals( CRMServiceCode.CRM001.getStatusCode(), capDto.getStatusCode() ) )
                    {
                        return new String[]
                        { capDto.getStatusCode(), "Verification mail sent successfully." };
                    }
                    else
                    {
                        return new String[]
                        { capDto.getStatusCode(), capDto.getStatusDesc() };
                    }
                }
                else
                {
                    return new String[]
                    { "", "Email already verified." };
                }
            }
            else
            {
                LOGGER.info( "invalid customer details pojo in session > selfcareForm" );
            }
        }
        else
        {
            LOGGER.info( "session = " + inSession + " || qrcForm = " + inSession.getAttribute( "qrcForm" ) );
        }
        return null;
    }

    /**
     * 
     * State Name and City Name must need to be present in parameter
     * CrmAddressDetailsPojo.
     * 
     * @param crmBillAddPojo
     *            CrmAddressDetailsPojo
     * 
     */
    private void fillStateCityIds( CrmAddressDetailsPojo crmBillAddPojo )
    {
        StatePojo statePojo = GISUtils.getState( crmBillAddPojo.getStateName() );
        if ( StringUtils.isValidObj( statePojo ) )
        {
            crmBillAddPojo.setStateId( statePojo.getStateId() );
            CityPojo cityPojo = GISUtils.getCity( crmBillAddPojo.getCityName(), statePojo.getCities() );
            if ( StringUtils.isValidObj( cityPojo ) )
            {
                crmBillAddPojo.setCityId( cityPojo.getCityId() );
            }
        }
    }

    @Override
    public List<CrmQrcSubCategoriesPojo> getActiveQrcSubCategories( String inCategoryId )
    {
        LOGGER.info( "Inside -> getActiveQrcSubCategories( long " + inCategoryId + " )" );
        List<CrmQrcSubCategoriesPojo> subCategories = QRCCacheManager.getActiveQrcSubCategories( inCategoryId );
        if ( ValidationUtil.isValidCollection( subCategories ) )
        {
            SortingComparator<CrmQrcSubCategoriesPojo> sortComparator = new SortingComparator<CrmQrcSubCategoriesPojo>( "qrcSubCategory" );
            Collections.sort( subCategories, sortComparator );
            sortComparator = null;
            LOGGER.info( "Sub Categories Size : " + subCategories.size() );
        }
        return subCategories;
    }

    @Override
    public List<CrmQrcSubCategoriesPojo> getActiveSubCategoriesByModuleType( String inCategoryId, String inModuleType )
    {
        LOGGER.info( "Inside -> getActiveSubCategories( long " + inCategoryId + " )" + ",Module Type ::" + inModuleType );
        List<CrmQrcSubCategoriesPojo> moduleTypeQrcSubCatPojos = null;
        if ( !StringUtils.equals( "0", inModuleType ) )
        {
            if ( StringUtils.isEmpty( inModuleType ) )
            {
                inModuleType = null;
            }
            List<CrmQrcSubCategoriesPojo> subCategories = QRCCacheManager.getActiveQrcSubCategories( inCategoryId );
            if ( ValidationUtil.isValidCollection( subCategories ) )
            {
                moduleTypeQrcSubCatPojos = new ArrayList<CrmQrcSubCategoriesPojo>();
                for ( CrmQrcSubCategoriesPojo crmQrcSubCategoriesPojo : subCategories )
                {
                    if ( StringUtils.equals( inModuleType, crmQrcSubCategoriesPojo.getModuleType() )
                            && StringUtils.equals( inModuleType, CRMRequestType.LMS.getRequestCode() ) )
                    {
                        moduleTypeQrcSubCatPojos.add( crmQrcSubCategoriesPojo );
                    }
                    else if ( !StringUtils.equals( inModuleType, CRMRequestType.LMS.getRequestCode() ) )
                    {
                        moduleTypeQrcSubCatPojos.add( crmQrcSubCategoriesPojo );
                    }
                }
                LOGGER.info( "Sub Categories Size : " + subCategories.size() );
            }
        }
        return moduleTypeQrcSubCatPojos;
    }

    @Override
    public List<CrmQrcSubSubCategoriesPojo> getActiveQrcSubSubCategories( String inCategoryId, String inSubCategoryId )
    {
        LOGGER.info( "Inside -> getActiveQrcSubSubCategories( long " + inCategoryId + ", long " + inSubCategoryId
                + " )" );
        List<CrmQrcSubSubCategoriesPojo> subSubCategories = QRCCacheManager
                .getActiveQrcSubSubCategories( null, inCategoryId, inSubCategoryId );
        if ( ValidationUtil.isValidCollection( subSubCategories ) )
        {
            SortingComparator<CrmQrcSubSubCategoriesPojo> sortComparator = new SortingComparator<CrmQrcSubSubCategoriesPojo>( "qrcSubSubCategory" );
            Collections.sort( subSubCategories, sortComparator );
            sortComparator = null;
            LOGGER.info( "Sub Sub Categories Size : " + subSubCategories.size() );
        }
        return subSubCategories;
    }

    @Override
    public List<CrmQrcSubSubCategoriesPojo> getActiveQrcSubSubCategoriesByType( final String inQrcType,
                                                                                final String inCategory,
                                                                                final String inSubCategory )
    {
        LOGGER.info( "Inside -> getActiveQrcSubSubCategoriesByType( String " + inQrcType + " long " + inCategory
                + ", long " + inSubCategory + " )" );
        List<CrmQrcSubSubCategoriesPojo> subSubCategories = QRCCacheManager
                .getActiveQrcSubSubCategories( inQrcType, inCategory, inSubCategory );
        if ( StringUtils.isValidObj( subSubCategories ) )
        {
            SortingComparator<CrmQrcSubSubCategoriesPojo> sortComparator = new SortingComparator<CrmQrcSubSubCategoriesPojo>( "qrcSubSubCategory" );
            Collections.sort( subSubCategories, sortComparator );
            sortComparator = null;
            LOGGER.info( "Sub Sub Categories Size : " + subSubCategories.size() );
        }
        return subSubCategories;
    }

    @Override
    public List<ContentPojo> getQrcActionsList( long inCategoryId, long inSubCategoryId, long inSubSubCategoryId )
    {
        return QRCCacheManager.getQrcActionsList( inCategoryId, inSubCategoryId, inSubSubCategoryId );
    }

    @Override
    public String getQRCType( long inCategoryId, long inSubCategoryId, long inSubSubCategoryId )
    {
        List<CrmQrcSubSubCategoriesPojo> subSubCategories = QRCCacheManager
                .getActiveQrcSubSubCategories( null, inCategoryId, inSubCategoryId );
        CrmQrcSubSubCategoriesPojo subSubCategoriesPojo = new CrmQrcSubSubCategoriesPojo();
        subSubCategoriesPojo.setQrcSubSubCategoryId( inSubSubCategoryId );
        int pojoIndex = subSubCategories.indexOf( subSubCategoriesPojo );
        if ( pojoIndex > -1 )
        {
            subSubCategoriesPojo = subSubCategories.get( pojoIndex );
            return subSubCategoriesPojo.getQrcType();
        }
        return null;
    }

    @Override
    public long getFunctionalBinID( long inCategoryId, long inSubCategoryId, long inSubSubCategoryId )
    {
        List<CrmQrcSubSubCategoriesPojo> subSubCategories = QRCCacheManager
                .getActiveQrcSubSubCategories( null, inCategoryId, inSubCategoryId );
        CrmQrcSubSubCategoriesPojo subSubCategoriesPojo = new CrmQrcSubSubCategoriesPojo();
        subSubCategoriesPojo.setQrcSubSubCategoryId( inSubSubCategoryId );
        int pojoIndex = subSubCategories.indexOf( subSubCategoriesPojo );
        if ( pojoIndex > -1 )
        {
            subSubCategoriesPojo = subSubCategories.get( pojoIndex );
            return subSubCategoriesPojo.getFunctionalBinId();
        }
        return 0L;
    }

    @Override
    public List<CrmRcaReasonPojo> getWhitelistReasons( String inWhitelistType )
    {
        List<CrmRcaReasonPojo> crmRcaReasonPojos = null;
        if ( StringUtils.equals( inWhitelistType, CRMStatusCode.BARRED.getStatusDesc() ) )
        {
            crmRcaReasonPojos = QRCCacheManager.getWhitelistBarringReasons();
        }
        if ( StringUtils.equals( inWhitelistType, CRMStatusCode.UNBARRED.getStatusDesc() ) )
        {
            crmRcaReasonPojos = QRCCacheManager.getWhitelistUnBarringReasons();
        }
        return crmRcaReasonPojos;
    }

    @Override
    public List<ContentPojo> getFunctionalBinbyId( long inSubSubCategoryId, long inFunctionalBinId )
    {
        List<CrmQrcCategoryBinMappingPojo> binMappingPojos = null;
        List<CrmRcaReasonPojo> rcaReasonPojos = null;
        List<ContentPojo> contentPojos = null;
        CrmSrTicketsPojo srTicketsPojo = null;
        ContentPojo contentPojo = null;
        CrmQrcDto crmQrcDto = null;
        try
        {
            crmQrcDto = new CrmQrcDto();
            srTicketsPojo = new CrmSrTicketsPojo();
            srTicketsPojo.setFunctionalbinId( inFunctionalBinId );
            srTicketsPojo.setQrcSubSubCategoryId( inSubSubCategoryId );
            crmQrcDto.setCrmSrTicketsPojo( srTicketsPojo );
            crmQrcDto = getQrcManagerBm().getFunctionalBinbyId( crmQrcDto );
            binMappingPojos = crmQrcDto.getCrmQrcCategoryBinMappingPojos();
            if ( StringUtils.isValidObj( binMappingPojos ) && !binMappingPojos.isEmpty() )
            {
                contentPojos = new ArrayList<ContentPojo>();
                rcaReasonPojos = CRMCacheManager.getFunctionalBins();
                if ( StringUtils.isValidObj( rcaReasonPojos ) && !rcaReasonPojos.isEmpty() )
                {
                    for ( CrmRcaReasonPojo rcaReasonPojo : rcaReasonPojos )
                    {
                        for ( CrmQrcCategoryBinMappingPojo mappingPojo : binMappingPojos )
                        {
                            if ( rcaReasonPojo.getCategoryId() == mappingPojo.getToBinId() )
                            {
                                contentPojo = new ContentPojo( rcaReasonPojo.getCategoryValue(),
                                                               mappingPojo.getToBinId() + "" );
                                contentPojos.add( contentPojo );
                            }
                        }
                    }
                }
            }
        }
        catch ( Exception ex )
        {
            LOGGER.error( "Exception while fetching Function Bin IDs List ", ex );
        }
        return contentPojos;
    }

    @Override
    public List<String> getUsersByBinID( long inFunctionalBinId )
    {
        List<CrmUserPojo> crmUserPojos = null;
        List<String> crmUsers = null;
        CrmuserDetailsDto crmUserDetailsDto = null;
        CrmUserPojo crmUserPojo = null;
        try
        {
            crmUserDetailsDto = new CrmuserDetailsDto();
            crmUserPojo = new CrmUserPojo();
            crmUserPojo.setFunctionalBin( inFunctionalBinId + "" );
            crmUserPojo.setStatus( CRMStatusCode.ACTIVE.getStatusCode() );
            crmUserDetailsDto.setCrmUserDetailsPojo( crmUserPojo );
            crmUserDetailsDto = getUserMngmntBm().searchUser( crmUserDetailsDto );
            crmUserPojos = crmUserDetailsDto.getCrmUserDetailsPojoList();
            if ( StringUtils.isValidObj( crmUserPojos ) && !crmUserPojos.isEmpty() )
            {
                crmUsers = new ArrayList<String>();
                for ( CrmUserPojo userPojo : crmUserPojos )
                {
                    crmUsers.add( userPojo.getUserId() );
                }
            }
        }
        catch ( Exception ex )
        {
            LOGGER.error( "Exception while get users by Bin ID ", ex );
        }
        return crmUsers;
    }

    @Override
    public List<ContentPojo> getRca( final String inProduct, final long inQrcCategoryID )
    {
        List<ContentPojo> contentPojos = null;
        ContentPojo contentPojo = null;
        List<CrmQrcActionTakenPojo> rcaPojos = null;
        CrmQrcDto crmQrcDto = new CrmQrcDto();
        CrmSrTicketsPojo ticketPojo = new CrmSrTicketsPojo();
        CrmCustomerDetailsPojo customerDetailPojo = new CrmCustomerDetailsPojo();
        ticketPojo.setQrcCategoryId( inQrcCategoryID );
        customerDetailPojo.setProduct( inProduct );
        ticketPojo.setCustomerDetailsPojo( customerDetailPojo );
        crmQrcDto.setCrmSrTicketsPojo( ticketPojo );
        try
        {
            crmQrcDto = getQrcManagerBm().qrcRcaList( crmQrcDto );
        }
        catch ( Exception ex )
        {
            LOGGER.error( "Error while fetching RCA ", ex );
        }
        rcaPojos = crmQrcDto.getQrcActionTakenPojos();
        if ( CommonValidator.isValidCollection( rcaPojos ) )
        {
            LOGGER.info( "RCA list size: " + rcaPojos.size() );
            contentPojos = new ArrayList<ContentPojo>();
            for ( CrmQrcActionTakenPojo rcaPojo : rcaPojos )
            {
                contentPojo = new ContentPojo( rcaPojo.getActionTaken(), rcaPojo.getActionId() + "" );
                contentPojos.add( contentPojo );
            }
        }
        return contentPojos;
    }

    @Override
    public List<ContentPojo> getRcaReason( final long inRcaID )
    {
        List<ContentPojo> contentPojos = null;
        ContentPojo contentPojo = null;
        CrmQrcDto crmQrcDto = new CrmQrcDto();
        CrmQrcActionTakenPojo rcaPojo = new CrmQrcActionTakenPojo();
        rcaPojo.setActionId( inRcaID );
        crmQrcDto.setQrcActionTakenPojo( rcaPojo );
        try
        {
            crmQrcDto = getQrcManagerBm().qrcRcaReasonList( crmQrcDto );
        }
        catch ( Exception ex )
        {
            LOGGER.error( "Error while fetching RCAReasons ", ex );
        }
        List<CrmQrcRootCausePojo> resolutionCodePojos = crmQrcDto.getRootCausePojos();
        if ( StringUtils.isValidObj( resolutionCodePojos ) && !resolutionCodePojos.isEmpty() )
        {
            contentPojos = new ArrayList<ContentPojo>();
            for ( CrmQrcRootCausePojo resolutionCodePojo : resolutionCodePojos )
            {
                contentPojo = new ContentPojo( resolutionCodePojo.getRootCause(), resolutionCodePojo.getRootCauseId()
                        + "" );
                contentPojos.add( contentPojo );
            }
        }
        return contentPojos;
    }

    @Override
    public String getPrimaryMacAdd( String inSecondaryMacAdd )
    {
        return CommonUtils.getPrimaryMacAdd( inSecondaryMacAdd );
    }

    @Override
    public List<CrmRcaReasonPojo> getInteractionSubCategories( String inCategory )
    {
        return CRMCacheManager.getCustInteractionSubCategories( inCategory );
    }

    @Override
    public List<CrmParameterPojo> getCrmParameterListOption82( String partnerId, String productType, String oltType )
    {
        int index;
        MasterDataDto masterDataDto = null;
        PartnerPojo partnerPojoForId = null;
        List<CrmParameterPojo> crmParameterList = new ArrayList<CrmParameterPojo>();
        List<PartnerPojo> partnerList = CRMCacheManager.getActivePartnerList();
        // List<PartnerPojo> partnerList =CRMCacheManager.getPartnerbyType(
        // CRMDisplayListConstants.NETWORK_PARTNER.getCode(),
        // productType,CRMStatusCode.ACTIVE.getStatusCode() );
        if ( CommonValidator.isValidCollection( partnerList ) )
        {
            long partnerDetailsId = 0;
            LOGGER.info( "partnerList size in getOption82List is:" + partnerList.size() );
            PartnerPojo partnerPojo = new PartnerPojo();
            partnerPojo.setPartnerId( Long.parseLong( partnerId ) );
            index = partnerList.indexOf( partnerPojo );
            partnerPojoForId = partnerList.get( index );
            for ( CrmPartnerDetailsPojo crmPartnerDetailsPojo : partnerPojoForId.getCrmPartnerDetailses() )
            {
                if ( StringUtils.equals( crmPartnerDetailsPojo.getBussinessType(), productType )
                        && ( StringUtils.equals( crmPartnerDetailsPojo.getPartnerType(),
                                                 CRMDisplayListConstants.NETWORK_PARTNER.getCode() ) ) )
                {
                    partnerDetailsId = crmPartnerDetailsPojo.getRecordId();
                    LOGGER.info( " got for productType:" + productType + "partnerDetailsId:" + partnerDetailsId );
                    break;
                }
            }
            if ( partnerDetailsId > 0 )
            {
                // get CRMPARTNERCONFIG on the basis of partnerDetailsId and
                // OltType
                masterDataDto = getMasterDataBm().getNetworkPartnerList( partnerDetailsId, oltType );
                if ( StringUtils.isValidObj( masterDataDto ) )
                {
                    ArrayList<Long> ParameterIdList = new ArrayList<Long>();
                    if ( StringUtils.equals( masterDataDto.getStatusCode(), CRMServiceCode.CRM001.getStatusCode() ) )
                    {
                        List<CrmPartnerNetworkConfigPojo> networkConfigPojodb = masterDataDto
                                .getCrmPartnerNetworkConfigPojos();
                        for ( CrmPartnerNetworkConfigPojo crmPartnerNetworkConfigPojo : networkConfigPojodb )
                        {
                            if ( crmPartnerNetworkConfigPojo.getParameterId() > 0 )
                            {
                                ParameterIdList.add( crmPartnerNetworkConfigPojo.getParameterId() );
                            }
                        }
                        // return ParameterIdList;
                        List<CrmParameterPojo> crmParameterPojos = getMasterDataBm()
                                .getParameters( 0, CRMParameter.OPTION82, CRMParameter.INTERNAL.getParameter() );
                        if ( CommonValidator.isValidCollection( crmParameterPojos ) )
                        {
                            for ( CrmParameterPojo crmParameterPojo : crmParameterPojos )
                            {
                                for ( long paramId : ParameterIdList )
                                {
                                    if ( crmParameterPojo.getParameterId() == paramId )
                                    {
                                        LOGGER.info( "VALUES GOT FOR CRMPARAMETER OPTION82" );
                                        crmParameterList.add( crmParameterPojo );
                                    }
                                }
                            }
                        }
                        else
                        {
                            LOGGER.info( "CRMPARAMETER NOT PRESENT in db or an issue in DWR getOption82List" );
                        }
                    }
                    else
                    {
                        LOGGER.info( "service failure in DWR getOption82List with status code:"
                                + masterDataDto.getStatusCode() );
                    }
                }
            }
            else
            {
                LOGGER.info( "ERROR PARTNERDETAILSID is not returned,no partner present" );
            }
        }
        else
        {
            // no partners in the system
        }
        return crmParameterList;
    }

    @Override
    public List<CrmQrcSubSubCategoriesPojo> getWaiverCategories( String inType )
    {
        return getActiveQrcSubSubCategoriesByType( CRMRequestType.REQUEST.getRequestDesc(), "Billing", inType );
    }

    @Override
    public List<CrmQrcActionTakenPojo> getQrcRcaPojos( long inCategoryId, String inService )
    {
        LOGGER.info( "Inside DwrManagerImpl, getQrcRcaPojos" );
        return getQrcConfigManagerBm().getQrcRcaPojos( inCategoryId, inService, CRMStatusCode.ACTIVE.getStatusCode() );
    }

    @Override
    public List<CrmQrcRootCausePojo> getResolutionCodes( long inRcaId )
    {
        return getQrcConfigManagerBm().getCrmQrcResolutionCodePojos( inRcaId );
    }

    @Override
    public List<CrmQrcCategoriesPojo> getQrcRcaCategoryies()
    {
        return QRCCacheManager.getActiveQrcCategories();
    }

    @Override
    public List<CrmQrcCategoriesPojo> getCategoryiesByModuleType( String inModuleType )
    {
        List<CrmQrcCategoriesPojo> categoriesPojos = null;
        List<CrmQrcCategoriesPojo> moduleTypeQrcCatPojos = null;
        if ( !StringUtils.equals( "0", inModuleType ) )
        {
            if ( StringUtils.isEmpty( inModuleType ) )
            {
                inModuleType = null;
            }
            moduleTypeQrcCatPojos = new ArrayList<CrmQrcCategoriesPojo>();
            categoriesPojos = QRCCacheManager.getActiveQrcCategories();
            if ( CommonValidator.isValidCollection( categoriesPojos ) )
            {
                for ( CrmQrcCategoriesPojo crmQrcCategoriesPojo : categoriesPojos )
                {
                    if ( StringUtils.equals( inModuleType, crmQrcCategoriesPojo.getModuleType() )
                            && StringUtils.equals( inModuleType, CRMRequestType.LMS.getRequestCode() ) )
                    {
                        moduleTypeQrcCatPojos.add( crmQrcCategoriesPojo );
                    }
                    else if ( !StringUtils.equals( inModuleType, CRMRequestType.LMS.getRequestCode() ) )
                    {
                        moduleTypeQrcCatPojos.add( crmQrcCategoriesPojo );
                    }
                }
                LOGGER.info( "moduleTypeQrcCatPojos ::" + moduleTypeQrcCatPojos.size() );
            }
        }
        return moduleTypeQrcCatPojos;
    }

    @Override
    public List<ContentPojo> getServiceNames()
    {
        return CRMUtils.getProducts();
    }

    @Override
    public PartnerPojo getPartnerById( String inPartnerId )
    {
        LOGGER.info( "getPartnerById " + inPartnerId );
        if ( StringUtils.numericValue( inPartnerId ) > 0 )
        {
            List<PartnerPojo> data = getMasterDataBm().getPartnersById( inPartnerId );
            if ( CommonValidator.isValidCollection( data ) )
            {
                return data.get( 0 );
            }
        }
        return null;
    }

    @Override
    public List<ContentPojo> getServicePartner( String inNpId, String inProduct )
    {
        LOGGER.info( "getPartnerById " + inNpId + " and product is : " + inProduct );
        ContentPojo contentPojo = null;
        List<ContentPojo> contentPojos = null;
        if ( StringUtils.numericValue( inNpId ) > 0 )
        {
            List<CrmPartnerMappingPojo> mappingPojos = getMasterDataBm().getServicePartner( inNpId, inProduct );
            if ( CommonValidator.isValidCollection( mappingPojos ) )
            {
                contentPojos = new ArrayList<ContentPojo>();
                for ( CrmPartnerMappingPojo crmPartnerMappingPojo : mappingPojos )
                {
                    contentPojo = new ContentPojo( crmPartnerMappingPojo.getPartnerBySpId().getPartnerName(),
                                                   crmPartnerMappingPojo.getPartnerBySpId().getPartnerId() + "" );
                    contentPojos.add( contentPojo );
                }
            }
        }
        return contentPojos;
    }

    @Override
    public CrmUserPojo getUserById( String inUserId )
    {
        LOGGER.info( "getUserById " + inUserId );
        if ( StringUtils.isNotBlank( inUserId ) )
        {
            List<CrmUserPojo> crmUserPojos = null;
            CrmuserDetailsDto crmUserDetailsDto = null;
            try
            {
                crmUserDetailsDto = new CrmuserDetailsDto();
                CrmUserPojo crmUserPojo = new CrmUserPojo();
                crmUserPojo.setUserId( inUserId );
                crmUserPojo.setStatus( CRMStatusCode.ACTIVE.getStatusCode() );
                crmUserDetailsDto.setCrmUserDetailsPojo( crmUserPojo );
                crmUserDetailsDto = getUserMngmntBm().searchUser( crmUserDetailsDto );
                crmUserPojos = crmUserDetailsDto.getCrmUserDetailsPojoList();
                if ( CommonValidator.isValidCollection( crmUserPojos ) )
                {
                    return crmUserPojos.get( 0 );
                }
            }
            catch ( Exception ex )
            {
                LOGGER.error( "Exception while get users by Bin ID ", ex );
            }
        }
        return null;
    }

    public List<CrmPlanMasterPojo> getActivationPlan( String product, String serviceType, String planType )
    {
        CRMDisplayListConstants constant = CRMDisplayListConstants.getConstantByFilter( "PlanService", product,
                                                                                        serviceType );
        String prepaid = ( serviceType.equals( "PR" ) ? "Y" : "N" );
        LOGGER.info( "Constant value::" + constant.getCode() );
        List<CrmPlanMasterPojo> plans = CRMCacheManager.getActivationPlansList( constant.getCode(), prepaid, planType );
        SortingComparator<CrmPlanMasterPojo> sorter = new SortingComparator<CrmPlanMasterPojo>( "planName" );
        Collections.sort( plans, sorter );
        sorter = null;
        return plans;
        // CRMCacheManager.getAddonPlanDetails( constant.getCode() );
    }

    public List<CrmQrcSubSubCategoriesPojo> getBouncingReason( String installationStatus )
    {
        LOGGER.info( "Installation Status::" + installationStatus );
        QRCRolCategories qrcCategory = QRCRolCategories.CHEQUE_BOUNCE_ADVANCE_PAYMENT;
        if ( StringUtils.equals( installationStatus, CRMDisplayListConstants.POSTINSTALLATION.getCode() ) )
        {
            qrcCategory = QRCRolCategories.CHEQUE_BOUNCE_BILL_PAYMENT;
        }
        return CRMCacheManager.getChequeBouncingReasons( qrcCategory );
    }

    @Override
    public List<com.np.tele.crm.service.client.ContentPojo> getBasePlanMigrationPolicy( String oldBasePlanCode,
                                                                                        String newBasePlanCode,
                                                                                        String oldAddonPlanCode,
                                                                                        String serviceType,
                                                                                        String customerId )
    {
        CrmQrcDto crmQrcDto = new CrmQrcDto();
        crmQrcDto.setOldBasePlanCode( oldBasePlanCode );
        crmQrcDto.setNewBasePlanCode( newBasePlanCode );
        crmQrcDto.setOldAddonPlanCode( oldAddonPlanCode );
        crmQrcDto.setServiceType( serviceType );
        crmQrcDto.setCustomerId( customerId );
        try
        {
            crmQrcDto = getQrcManagerBm().getMigrationPolicy( crmQrcDto );
            if ( !CommonValidator.isValidCollection( crmQrcDto.getContentPojos() ) )
            {
                com.np.tele.crm.service.client.ContentPojo contentPojo = new com.np.tele.crm.service.client.ContentPojo();
                contentPojo.setContentName( IAppConstants.NBC );
                crmQrcDto.getContentPojos().add( contentPojo );
            }
        }
        catch ( Exception ex )
        {
            LOGGER.error( "Error while fetching RCAReasons ", ex );
        }
        return crmQrcDto.getContentPojos();
    }

    @Override
    public List<com.np.tele.crm.service.client.ContentPojo> getAddonPlanMigrationPolicy( String oldBasePlanCode,
                                                                                         String oldAddonPlanCode,
                                                                                         String newAddonPlanCode,
                                                                                         String serviceType,
                                                                                         String customerId )
    {
        CrmQrcDto crmQrcDto = new CrmQrcDto();
        crmQrcDto.setOldBasePlanCode( oldBasePlanCode );
        crmQrcDto.setNewAddonPlanCode( newAddonPlanCode );
        crmQrcDto.setOldAddonPlanCode( oldAddonPlanCode );
        crmQrcDto.setServiceType( serviceType );
        crmQrcDto.setCustomerId( customerId );
        try
        {
            crmQrcDto = getQrcManagerBm().getAddOnMigrationPolicy( crmQrcDto );
            if ( !CommonValidator.isValidCollection( crmQrcDto.getContentPojos() ) )
            {
                com.np.tele.crm.service.client.ContentPojo contentPojo = new com.np.tele.crm.service.client.ContentPojo();
                contentPojo.setContentName( IAppConstants.NBC );
                crmQrcDto.getContentPojos().add( contentPojo );
            }
        }
        catch ( Exception ex )
        {
            LOGGER.error( "Error while fetching RCAReasons ", ex );
        }
        return crmQrcDto.getContentPojos();
    }

    @Override
    public List<CrmRcaReasonPojo> getReasonsList( String inType )
    {
        List<CrmRcaReasonPojo> crmRcaReasonPojos = null;
        if ( StringUtils.equals( inType, CRMStatusCode.BARRED.getStatusCode() ) )
        {
            crmRcaReasonPojos = QRCCacheManager.getCustomerBarringReasons();
        }
        if ( StringUtils.equals( inType, CRMStatusCode.UNBARRED.getStatusCode() ) )
        {
            crmRcaReasonPojos = QRCCacheManager.getCustomerUnBarringReasons();
        }
        if ( StringUtils.equals( inType, CRMStatusCode.SC.getStatusCode() ) )
        {
            crmRcaReasonPojos = QRCCacheManager.getCustomerSafeCustodyReasons();
        }
        if ( StringUtils.equals( inType, CRMStatusCode.TDC.getStatusCode() ) )
        {
            crmRcaReasonPojos = QRCCacheManager.getTemporaryDisconnectionReasons();
        }
        if ( StringUtils.equals( inType, CRMStatusCode.PDC.getStatusCode() ) )
        {
            crmRcaReasonPojos = QRCCacheManager.getPermanentDisconnectionReasons();
        }
        return crmRcaReasonPojos;
    }

    @Override
    public boolean changeBinOwner( HttpSession inSession,
                                   HttpServletRequest inRequest,
                                   long inRowId,
                                   String inElementId,
                                   String inInboxOwner,
                                   String inCurrentStage,
                                   String inBinChangeInbox,
                                   String inInboxType )
    {
        CrmuserDetailsDto userDto = (CrmuserDetailsDto) inSession.getAttribute( IAppConstants.CRM_USER_OBJECT );
        String userId = userDto.getCrmUserDetailsPojo().getUserId();
        ConfigDto configDto = new ConfigDto();
        if ( StringUtils.isValidObj( inSession.getAttribute( "inboxForm" ) ) )
        {
            InboxForm inboxForm = (InboxForm) inSession.getAttribute( "inboxForm" );
            inboxForm.setRowID( inRowId );
            inboxForm.setElementID( inElementId );
            inboxForm.setInboxOwner( inInboxOwner );
            inboxForm.setCurrentStage( inCurrentStage );
            inboxForm.setBinChangeInbox( inBinChangeInbox );
            inboxForm.setInboxType( inInboxType );
            LOGGER.info( "Row ID: " + inboxForm.getRowID() );
            LOGGER.info( "Element ID: " + inboxForm.getElementID() );
            LOGGER.info( "Inbox Owner: " + inboxForm.getInboxOwner() );
            LOGGER.info( "Current Stage: " + inboxForm.getCurrentStage() );
            LOGGER.info( "Bin Change inbox: " + inboxForm.getBinChangeInbox() );
            LOGGER.info( "Inbox Type: " + inboxForm.getInboxType() );
            if ( StringUtils.equals( inboxForm.getBinChangeInbox(), CRMRequestType.LEAD.getRequestCode() ) )
            {
                LOGGER.info( "Start changing lead owner on request user-Id : " + userId + " for lead : "
                        + inboxForm.getElementID() );
                configDto.setMappingId( inboxForm.getElementID() );
                configDto.setRequestType( CRMRequestType.LEAD.getRequestCode() );
            }
            else if ( StringUtils.equals( inboxForm.getBinChangeInbox(), CRMRequestType.CAF.getRequestCode() ) )
            {
                LOGGER.info( "Start changing CRF owner on request user-Id : " + userId + " for CRF : "
                        + inboxForm.getElementID() );
                configDto.setMappingId( String.valueOf( inboxForm.getRowID() ) );
                configDto.setRequestType( CRMRequestType.CAF.getRequestCode() );
            }
            else if ( StringUtils.equals( inboxForm.getBinChangeInbox(), CRMRequestType.QRC.getRequestCode() ) )
            {
                LOGGER.info( "Start changing SR owner on request user-Id : " + userId + " for SR : "
                        + inboxForm.getElementID() );
                configDto.setMappingId( String.valueOf( inboxForm.getElementID() ) );
                configDto.setRequestType( CRMRequestType.QRC.getRequestCode() );
            }
            else if ( StringUtils.equals( inboxForm.getBinChangeInbox(), "WORKFLOW" ) )
            {
                LOGGER.info( "Start changing SR owner on request user-Id : " + userId + " for SR : "
                        + inboxForm.getElementID() );
                configDto.setMappingId( String.valueOf( inboxForm.getElementID() ) );
                configDto.setRequestType( inboxForm.getInboxType() );
            }
            configDto.setUserId( userId );
            if ( StringUtils.equals( inboxForm.getInboxOwner(), CRMParameter.PERSONAL.getParameter() ) )
            {
                configDto.setFromUserId( userId );
                configDto.setToUserId( "" );
                LOGGER.info( "FromUserId : " + configDto.getFromUserId() + " to : " + configDto.getToUserId() );
            }
            else if ( StringUtils.equals( inboxForm.getInboxOwner(), CRMParameter.GROUP.getParameter() ) )
            {
                configDto.setFromUserId( "" );
                configDto.setToUserId( userId );
                LOGGER.info( "FromUserId : " + configDto.getFromUserId() + " to : " + configDto.getToUserId() );
            }
            configDto.setFromStage( inboxForm.getCurrentStage() );
            configDto.setTostage( inboxForm.getCurrentStage() );
            configDto = getInboxManagerImpl().changeInboxBin( configDto );
            LOGGER.info( "changeInboxBin result :::: " + configDto.getStatusCode() + " :: " + configDto.getStatusDesc() );
            if ( StringUtils.equals( configDto.getStatusCode(), CRMServiceCode.CRM001.getStatusCode() ) )
            {
                // configDto.getStages().addAll( retrieveUserBin( inRequest,
                // configDto ) );
                // getInboxData( configDto, inboxForm, inRequest );
                inSession.setAttribute( "SuccessMsg", "Your request has been processed successfully." );
                return true;
            }
        }
        return false;
    }

    // private String resolveStageByBinID( long inParseLong )
    // {
    // List<CrmRcaReasonPojo> functionalBinCache =
    // CRMCacheManager.getFunctionalBins();
    // for ( CrmRcaReasonPojo crmRcaReasonPojo : functionalBinCache )
    // {
    // if ( crmRcaReasonPojo.getCategoryId() == inParseLong )
    // {
    // if ( StringUtils.isNumeric( crmRcaReasonPojo.getValueAlias() )
    // || crmRcaReasonPojo.getValueAlias().equals( "-1" ) )
    // {
    // return String.valueOf( inParseLong );
    // }
    // else
    // return crmRcaReasonPojo.getValueAlias();
    // }
    // }
    // return null;
    // }
    @Override
    public String[] sendCustomerUsage( HttpSession inSession,
                                       HttpServletRequest inRequest,
                                       String inUsageType,
                                       String inBillPeriod )
    {
        LOGGER.info( "Inside DwrManagerImpl : sendCustomerUsage" );
        if ( StringUtils.isValidObj( inSession ) && StringUtils.isValidObj( inRequest ) )
        {
            CrmuserDetailsDto userDto = (CrmuserDetailsDto) inRequest.getSession()
                    .getAttribute( IAppConstants.CRM_USER_OBJECT );
            QrcForm qrcForm = (QrcForm) inSession.getAttribute( "qrcForm" );
            if ( StringUtils.isNotEmpty( qrcForm.getCustomerId() ) && StringUtils.isNotBlank( inUsageType ) )
            {
            }
            CrmQrcDto crmQrcDto = new CrmQrcDto();
            if ( StringUtils.equals( inUsageType, IAppConstants.UNBILLED ) )
            {
                if ( StringUtils.equals( qrcForm.getCustDetailsPojo().getServiceType(),
                                         CRMDisplayListConstants.PRE_PAID.getCode() ) )
                {
                    if ( StringUtils.isValidObj( qrcForm.getCustAdditionalDetails() ) )
                    {
                        crmQrcDto.setUsageFormDate( DateUtils.getFormattedDate( qrcForm.getCustAdditionalDetails()
                                .getActivationDate(), IDateConstants.SDF_DD_MMM_YYYY_HH_MM_SS ) );
                        LOGGER.info( "customer service type :: " + qrcForm.getCustDetailsPojo().getServiceType()
                                + " prev. date :: " + crmQrcDto.getUsageFormDate() );
                    }
                }
                else
                {
                    crmQrcDto.setUsageFormDate( DateUtils.getFormattedDate( DateUtils.getPreviousBillingDate( qrcForm
                            .getCustDetailsPojo().getBillDate() ), IDateConstants.SDF_DD_MMM_YYYY_HH_MM_SS ) );
                    LOGGER.info( "customer service type :: " + qrcForm.getCustDetailsPojo().getServiceType()
                            + " prev. date :: " + crmQrcDto.getUsageFormDate() );
                }
                crmQrcDto.setUsageToDate( DateUtils.getCurrDateEndTime() );
            }
            else if ( StringUtils.equals( inUsageType, IAppConstants.BILLED ) )
            {
                String[] dates = StringUtils.split( inBillPeriod, " to " );
                // crmQrcDto.setUsageFormDate( dates[0] );
                //crmQrcDto.setUsageToDate( dates[1] );
                crmQrcDto.setUsageFormDate( DateUtils.getFormattedDate( dates[0],
                                                                        IDateConstants.SDF_FORMAT_DD_MMM_YYYY,
                                                                        IDateConstants.FORMAT_DD_MMM_YYYY_HH_MM_SS ) );
                crmQrcDto
                        .setUsageToDate( DateUtils.getEndTimeToDate( dates[1], IDateConstants.SDF_FORMAT_DD_MMM_YYYY ) );
            }
            crmQrcDto.setUsageType( inUsageType );
            crmQrcDto.setUserId( userDto.getCrmUserDetailsPojo().getUserId() );
            crmQrcDto.setCustomerDetailsPojo( qrcForm.getCustDetailsPojo() );
            crmQrcDto = getQrcManagerBm().sendCustomerUsage( crmQrcDto );
            LOGGER.info( "Status Code:: " + crmQrcDto.getStatusCode() );
            if ( StringUtils.equals( crmQrcDto.getStatusCode(), CRMServiceCode.CRM001.getStatusCode() ) )
            {
                if ( StringUtils.isEmpty( crmQrcDto.getSrTicketNo() ) )
                {
                    return new String[]
                    { "success", "Your request has been processed successfully." };
                }
                else
                {
                    return new String[]
                    {
                            "success",
                            "Your request has been processed successfully. Your Ticket ID is "
                                    + crmQrcDto.getSrTicketNo() + "." };
                }
            }
            else if ( StringUtils.equals( crmQrcDto.getStatusCode(), CRMServiceCode.CRM996.getStatusCode() ) )
            {
                return new String[]
                { "error", "Unable to send Email. Usage details not found." };
            }
            else
            {
                return new String[]
                { "error", crmQrcDto.getStatusDesc() };
            }
        }
        return new String[]
        { "error", "Unable to process your request." };
    }

    @Override
    public boolean isLmsDataFeasible( String inState, String inCity, String inArea, String inLocality, String inSociety )
    {
        LmsPojo lmsPojo = new LmsPojo();
        lmsPojo.setState( StringUtils.trim( inState ) );
        lmsPojo.setCity( StringUtils.trim( inCity ) );
        lmsPojo.setArea( StringUtils.trim( inArea ) );
        lmsPojo.setLocality( StringUtils.trim( inLocality ) );
        // lmsPojo.setSociety( StringUtils.trim( inSociety ) );
        return GISUtils.isSocietyFeasible( lmsPojo );
    }

    @Override
    public List<ContentPojo> getNassPortList( String networkPartnerId, String product )
    {
        LOGGER.info( "getNassPortList method called" + networkPartnerId + "********" + product );
        long recordId = 0;
        List<CrmPartnerDetailsPojo> partnerDetailList = CRMCacheManager.getPartnerDetails( networkPartnerId );
        for ( CrmPartnerDetailsPojo crmPartnerDetailsPojo : partnerDetailList )
        {
            LOGGER.info( "POjo*****" + crmPartnerDetailsPojo );
            if ( ( StringUtils.equals( crmPartnerDetailsPojo.getBussinessType(), product ) )
                    && ( StringUtils.equals( crmPartnerDetailsPojo.getPartnerType(),
                                             CRMDisplayListConstants.NETWORK_PARTNER.getCode() ) ) )
            {
                recordId = crmPartnerDetailsPojo.getRecordId();
                LOGGER.info( "RecordId:;" + recordId );
            }
        }
        MasterDataDto masterDataDto = new MasterDataDto();
        List<ContentPojo> contentPojo = new ArrayList<ContentPojo>();
        CrmPartnerNetworkConfigPojo crmPartnerNetworkConfigPojo = new CrmPartnerNetworkConfigPojo();
        crmPartnerNetworkConfigPojo.setPartnerDetailsId( recordId );
        crmPartnerNetworkConfigPojo.setStatus( CRMStatusCode.ACTIVE.getStatusCode() );
        masterDataDto.setCrmPartnerNetworkConfigPojo( crmPartnerNetworkConfigPojo );
        masterDataDto = getMasterDataBm().masterOperation( ServiceParameter.SEARCH.getParameter(),
                                                           CRMParameter.OPTION82.getParameter(), masterDataDto );
        if ( CommonValidator.isValidCollection( masterDataDto.getCrmPartnerNetworkConfigPojos() ) )
        {
            LOGGER.info( "Size of Network ConfigurationPojo::" + masterDataDto.getCrmPartnerNetworkConfigPojos().size() );
            for ( CrmPartnerNetworkConfigPojo pojo : masterDataDto.getCrmPartnerNetworkConfigPojos() )
            {
                ContentPojo conPojo = new ContentPojo();
                conPojo.setContentName( pojo.getMasterName() + "-" + pojo.getNasPortId() + "-" + pojo.getPoolName() );
                conPojo.setContentValue( pojo.getRecordId() + "" );
                contentPojo.add( conPojo );
            }
        }
        return contentPojo;
    }

    @Override
    public List<PartnerPojo> getPartnerByType( String product )
    {
        List<PartnerPojo> partnerPojo = CRMCacheManager.getPartnerbyType( CRMDisplayListConstants.NETWORK_PARTNER
                .getCode(), product, CRMStatusCode.ACTIVE.getStatusCode() );
        return partnerPojo;
    }

    @Override
    public List<ContentPojo> getPaymentChannelByPaymentMode( String inPaymentMode )
    {
        List<ContentPojo> paymentChannel = CRMUtils.getPaymentChannelsForPaymentMode( inPaymentMode );
        return paymentChannel;
    }

    /*@Override
    public List<ContentPojo> getCaseStatusByPaymentMode( String inPaymentMode )
    {
        List<ContentPojo> paymentChannel = CRMUtils.getCaseStatusByPaymentMode( inPaymentMode );
        return paymentChannel;
    }*/
    /*
     * @Override public boolean resetInboxSearch( HttpSession inSession ) {
     * boolean valid = false; LOGGER.info(
     * "in resetInboxSearch( HttpSession inSession )" ); if (
     * StringUtils.isValidObj( inSession ) && StringUtils.isValidObj(
     * inSession.getAttribute( "inboxForm" ) ) ) { InboxForm inboxForm =
     * (InboxForm) inSession.getAttribute( "inboxForm" );
     * inboxForm.setSearchCriteriaValue( null ); inboxForm.setSearchCriteria(
     * null ); valid = true; } return valid; }
     */
    @Override
    public List<CrmQrcSubCategoriesPojo> filterSubCategories( HttpSession inSession, String inSubCategoryValue )
    {
        List<CrmQrcSubCategoriesPojo> subCategories = null;
        List<CrmQrcSubCategoriesPojo> filSubcategoryPojos = new ArrayList<CrmQrcSubCategoriesPojo>();
        try
        {
            if ( StringUtils.isValidObj( inSession ) )
            {
                subCategories = QRCCacheManager
                        .getQrcSubCategory( CRMStatusCode.ACTIVE.getStatusCode(), (String) inSession
                                .getAttribute( CRMOperationStages.CUSTOMER_CARE.getCode() ) );
                if ( CommonValidator.isValidCollection( subCategories ) )
                {
                    if ( StringUtils.isNotBlank( inSubCategoryValue ) )
                    {
                        for ( CrmQrcSubCategoriesPojo subcategoryPojo : subCategories )
                        {
                            if ( StringUtils.containsIgnoreCase( subcategoryPojo.getQrcSubCategory(),
                                                                 inSubCategoryValue ) )
                            {
                                filSubcategoryPojos.add( subcategoryPojo );
                            }
                        }
                    }
                    else
                    {
                        filSubcategoryPojos.addAll( subCategories );
                    }
                }
                else
                {
                    LOGGER.info( "Subcategory list is empty" );
                    return null;
                }
            }
        }
        catch ( Exception ex )
        {
            LOGGER.error( "Error in filterSubCategories mehtod:: ", ex );
        }
        return filSubcategoryPojos;
    }

    @Override
    public List<CrmQrcSubSubCategoriesPojo> filterSubSubCategories( HttpSession inSession,
                                                                    String inCategoryId,
                                                                    String inSubCategoryId,
                                                                    String inSubSubCategoryValue )
    {
        List<CrmQrcSubSubCategoriesPojo> subSubCategories = null;
        List<CrmQrcSubSubCategoriesPojo> fillSubSubCategories = new ArrayList<CrmQrcSubSubCategoriesPojo>();
        try
        {
            subSubCategories = QRCCacheManager
                    .getQrcSubSubCategory( CRMStatusCode.ACTIVE.getStatusCode(),
                                           (String) inSession.getAttribute( CRMOperationStages.CUSTOMER_CARE.getCode() ),
                                           StringUtils.numericValue( inSubCategoryId ) );
            if ( CommonValidator.isValidCollection( subSubCategories ) )
            {
                if ( StringUtils.isNotBlank( inSubSubCategoryValue ) )
                {
                    for ( CrmQrcSubSubCategoriesPojo subSubCategoryPojos : subSubCategories )
                    {
                        if ( StringUtils.containsIgnoreCase( subSubCategoryPojos.getQrcSubSubCategory(),
                                                             inSubSubCategoryValue ) )
                        {
                            fillSubSubCategories.add( subSubCategoryPojos );
                        }
                    }
                }
                else
                {
                    fillSubSubCategories.addAll( subSubCategories );
                }
            }
            else
            {
                LOGGER.info( "Sub Sub Category List is empty" );
                return null;
            }
        }
        catch ( Exception ex )
        {
            LOGGER.info( "Error in filterSubSubCategories ::" + ex );
        }
        return fillSubSubCategories;
    }

    @Override
    public long getCategoryIdBySubcategory( HttpSession inSession, long inSubcategoryId )
    {
        List<CrmQrcSubCategoriesPojo> subCategories = null;
        try
        {
            if ( StringUtils.isValidObj( inSession ) )
            {
                subCategories = QRCCacheManager
                        .getQrcSubCategory( CRMStatusCode.ACTIVE.getStatusCode(), (String) inSession
                                .getAttribute( CRMOperationStages.CUSTOMER_CARE.getCode() ) );
                if ( CommonValidator.isValidCollection( subCategories ) )
                {
                    for ( CrmQrcSubCategoriesPojo subcategoryPojo : subCategories )
                    {
                        if ( subcategoryPojo.getQrcSubCategoryId() == inSubcategoryId )
                        {
                            return subcategoryPojo.getQrcCategoryId();
                        }
                    }
                }
            }
        }
        catch ( Exception ex )
        {
            LOGGER.error( "Error in getCategoryIdBySubcategory mehtod:: ", ex );
        }
        return 0L;
    }

    @Override
    public int checkValidCRFNo( String crfNo )
    {
        int check = 1;
        if ( StringUtils.isNotBlank( crfNo ) )
        {
            CrmCapDto crmCapDto = null;
            String status = getMasterDataBm().validateCRFInMaster( crfNo.toUpperCase(), null );
            if ( StringUtils.equals( status, CRMServiceCode.CRM001.getStatusCode() ) )
            {
                LOGGER.info( "Likned CRF Id found In Crf Master::" + crfNo );
                crmCapDto = getCrmCapManagerBm().validateCrfInCustomerDetails( crfNo );
                if ( StringUtils.equals( crmCapDto.getStatusCode(), CRMServiceCode.CRM001.getStatusCode() ) )
                {
                    // error
                    LOGGER.info( "Likned CRF Id found In Customer Details::" + crfNo );
                    check = 3;
                }
                else
                {
                    LOGGER.info( "Likned CRF Id Not found In Customer Details::" + crfNo );
                    crmCapDto = getCrmCapManagerBm().checkCrfInLinkToCrf( crfNo );
                    if ( StringUtils.equals( crmCapDto.getStatusCode(), CRMServiceCode.CRM001.getStatusCode() ) )
                    {
                        // error
                        LOGGER.info( "Linked CRF Id Linked with other CRF Linked::" + crfNo );
                        check = 2;
                    }
                    else
                    {
                        // success
                        LOGGER.info( "Likned CRF Id Not found In Linked To CRF::" + crfNo );
                        check = 1;
                    }
                }
            }
            else
            {
                // error
                LOGGER.info( "Likned CRF Id Not found In Crf Master::" + crfNo );
                check = 0;
            }
        }
        else
        {
            // error
            check = 0;
        }
        return check;
    }

    @Override
    public long getRfsDus( HttpSession session, int partnerId )
    {
        GISForm gisForm = (GISForm) session.getAttribute( "gisForm" );
        if ( CommonValidator.isValidCollection( gisForm.getSocietyNetworkPartners() ) )
        {
            for ( SocietyNetworkPartnerPojo partner : gisForm.getSocietyNetworkPartners() )
            {
                if ( partner.getPartnerId() == partnerId )
                {
                    return partner.getRfsDus();
                }
            }
        }
        return 0l;
    }

    @Override
    public LmsPojo getSocietyByPinCode( int inPinCode )
    {
        return getLmsManagerBm().getSocietyByPinCode( inPinCode );
    }

    @Override
    public List<CrmUserPojo> getAssociatedSRWithBP( String inProduct, long inPartnerId )
    {
        LOGGER.info( "PartnerId :" + inPartnerId + ",Product:" + inProduct );
        if ( inPartnerId > 0 && StringUtils.isNotBlank( inProduct ) )
        {
            return getMasterDataBm().getAssociatedSRWithBP( inProduct, inPartnerId );
        }
        return null;
    }

    @Override
    public List<String> getSecondaryMacAdd( long partnerId, String customerId )
    {
        if ( partnerId > 0 && StringUtils.isNotBlank( customerId ) )
        {
            LOGGER.info( "Partner Id" + partnerId + "Customer Id" + customerId );
            return CRMCacheManager.getSecondaryMacAdd( partnerId, customerId );
        }
        return null;
    }

    @Override
    public List<String> getUsersByParameter( String inParam,
                                             String inStateName,
                                             String inCityName,
                                             String inValue,
                                             String inFunctionalBin )
    {
        String inCountry = "India";
        String areaId = "";
        List<AreaPojo> areaList = GISUtils.getActiveAreas( inCountry, inStateName, inCityName );
        LOGGER.info( "Area List Size:::" + areaList.size() );
        for ( AreaPojo area : areaList )
        {
            if ( area.getArea().equalsIgnoreCase( inValue ) )
            {
                areaId = String.valueOf( area.getAreaId() );
                LOGGER.info( "Find the area Name basis area Id" + areaId );
                break;
            }
        }
        return getLmsManagerBm().getUsersByParameter( inParam, areaId, inFunctionalBin );
    }

    public List<CityPojo> getAllCitiesForRFS( int stateId )
    {
        List<CityPojo> cityPojos = null;
        try
        {
            cityPojos = GISUtils.getAllCities( IAppConstants.COUNTRY_INDIA, stateId );
            if ( cityPojos.isEmpty() )
            {
                LOGGER.info( "city list empty" );
                return null;
            }
            LOGGER.info( "City Pojo Size::" + cityPojos.size() );
            SortingComparator<CityPojo> sortComparator = new SortingComparator<CityPojo>( "cityName" );
            Collections.sort( cityPojos, sortComparator );
            sortComparator = null;
        }
        catch ( Exception ex )
        {
            LOGGER.error( "Error in getCity mehtod:: ", ex );
        }
        return cityPojos;
    }

    public List<AreaPojo> getAllAreaForRFS( int inCityId )
    {
        LOGGER.info( "getAreaForRFS" + inCityId );
        List<AreaPojo> areaPojos = null;
        try
        {
            areaPojos = GISUtils.getAllAreas( IAppConstants.COUNTRY_INDIA, null, inCityId );
            if ( areaPojos.isEmpty() )
            {
                LOGGER.info( "Area list empty" );
                return null;
            }
            LOGGER.info( "getAreaForRFS1" + areaPojos.size() );
            SortingComparator<AreaPojo> sortComparator = new SortingComparator<AreaPojo>( "area" );
            Collections.sort( areaPojos, sortComparator );
            sortComparator = null;
        }
        catch ( Exception ex )
        {
            LOGGER.error( "Error in getArea mehtod:: ", ex );
        }
        return areaPojos;
    }

    public List<SocietyPojo> populateAlllocalityForRFS( int areaId )
    {
        LOGGER.info( "populateAlllocalityForRFS:::" + areaId );
        List<SocietyPojo> societyPojos = null;
        try
        {
            societyPojos = GISUtils.getAllSocietybyArea( areaId );
            if ( societyPojos.isEmpty() )
            {
                LOGGER.info( "Society list empty" );
                return null;
            }
            for ( SocietyPojo societyPojo : societyPojos )
            {
                if ( StringUtils.isNotBlank( societyPojo.getSocietyName() ) )
                {
                    societyPojo.setLocalityName( societyPojo.getLocalityName().concat( IAppConstants.DASH )
                            .concat( societyPojo.getSocietyName() ) );
                }
                else
                {
                    societyPojo.setLocalityName( societyPojo.getLocalityName() );
                }
            }
            LOGGER.info( "populateAlllocalityForRFS" + societyPojos.size() );
            SortingComparator<SocietyPojo> sortComparator = new SortingComparator<SocietyPojo>( "localityName" );
            Collections.sort( societyPojos, sortComparator );
            sortComparator = null;
        }
        catch ( Exception ex )
        {
            LOGGER.error( "Error in populateAlllocalityForRFS mehtod:: ", ex );
        }
        return societyPojos;
    }

    @Override
    public List<CrmQrcSubSubCategoriesPojo> getQrcRcaSubSubCategoryies()
    {
        return QRCCacheManager.getActiveQrcSubSubCategories();
    }
}
