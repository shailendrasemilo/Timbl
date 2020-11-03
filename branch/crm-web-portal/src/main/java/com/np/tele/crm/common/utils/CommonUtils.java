package com.np.tele.crm.common.utils;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import com.np.tele.crm.config.bm.ICrmConfigManager;
import com.np.tele.crm.constants.CRMDisplayListConstants;
import com.np.tele.crm.constants.CRMServiceCode;
import com.np.tele.crm.constants.IAppConstants;
import com.np.tele.crm.pojos.RolesPojo;
import com.np.tele.crm.service.client.ConfigDto;
import com.np.tele.crm.service.client.CrmCustomerDetailsPojo;
import com.np.tele.crm.service.client.CrmPlanMasterPojo;
import com.np.tele.crm.service.client.CrmRcaReasonPojo;
import com.np.tele.crm.service.client.PartnerPojo;
import com.np.tele.crm.utils.StringUtils;
import com.np.validator.util.CommonValidator;

public class CommonUtils
{
    public static ICrmConfigManager getConfigManagerImpl()
    {
        return (ICrmConfigManager) IAppConstants.flyWeightBeanMap.get( IAppConstants.CRM_CONFIG_MANAGER );
    }

    public static List<PartnerPojo> getPartnersByProduct( List<PartnerPojo> partners, String inBusinessType )
    {
        List<PartnerPojo> partnersListByProduct = new ArrayList<PartnerPojo>();
        for ( PartnerPojo partner : partners )
        {
            if ( StringUtils.equals( partner.getBussinessType(), inBusinessType ) )
            {
                partnersListByProduct.add( partner );
            }
        }
        return partnersListByProduct;
    }

    public static CrmPlanMasterPojo getPlanDetails( String inPlanService, String inPlanCode, String planType )
    {
        List<CrmPlanMasterPojo> plans = null;
        if ( StringUtils.equals( CRMDisplayListConstants.BASE_PLAN.getCode(), planType ) )
        {
            plans = CRMCacheManager.getBasePlanDetails( inPlanService );
        }
        else if ( StringUtils.equals( CRMDisplayListConstants.ADDON_PLAN.getCode(), planType ) )
        {
            plans = CRMCacheManager.getAddonPlanDetails( inPlanService );
        }
        if ( CommonValidator.isValidCollection( plans ) )
        {
            CrmPlanMasterPojo crmPlanMasterPojo = new CrmPlanMasterPojo();
            crmPlanMasterPojo.setPlanCode( inPlanCode );
            int index = plans.indexOf( crmPlanMasterPojo );
            if ( index >= 0 )
            {
                return plans.get( index );
            }
        }
        return null;
    }

    public static boolean isAllowed( HttpSession inHttpSession, String inString )
    {
        RolesPojo roles = (RolesPojo) inHttpSession.getAttribute( IAppConstants.CRM_ROLES );
        String available = roles.getAvailable( inString );
        if ( StringUtils.isNotBlank( available ) )
        {
            return Boolean.parseBoolean( available );
        }
        return false;
    }

    public static String getPrimaryMacAdd( String inSecondaryMacAdd )
    {
        String primaryMacAdd = "";
        if ( StringUtils.isNotBlank( inSecondaryMacAdd ) )
        {
            String hexString = StringUtils.remove( inSecondaryMacAdd, "." );
            if ( StringUtils.isNotBlank( hexString ) )
            {
                long decimal = Long.parseLong( hexString, 16 );
                decimal = decimal - 2;
                hexString = Long.toHexString( decimal );
                hexString = StringUtils.leftPad( hexString, 12, "0" );
                hexString = StringUtils.insertCharacterAtIndex( hexString, 4, "." );
                primaryMacAdd = hexString;
            }
        }
        return primaryMacAdd;
    }

    public static PartnerPojo getPartnerById( long inPartnerId )
    {
        List<PartnerPojo> partners = CRMCacheManager.getActivePartnerList();
        PartnerPojo partnerPojo = new PartnerPojo();
        partnerPojo.setPartnerId( inPartnerId );
        int index = partners.indexOf( partnerPojo );
        if ( index >= 0 )
        {
            return partners.get( index );
        }
        return null;
    }

    public static CrmPlanMasterPojo getPlanByPlanCode( String inPlanCode )
    {
        List<CrmPlanMasterPojo> plans = CRMCacheManager.getAllPlanMaster();
        CrmPlanMasterPojo planPojo = new CrmPlanMasterPojo();
        planPojo.setPlanCode( inPlanCode );
        int index = plans.indexOf( planPojo );
        if ( index >= 0 )
        {
            return plans.get( index );
        }
        return null;
    }

    public static String getPlanNameByPlanCode( String inPlanCode )
    {
        CrmPlanMasterPojo planPojo = getPlanByPlanCode( inPlanCode );
        if ( StringUtils.isValidObj( planPojo ) )
        {
            return planPojo.getPlanName();
        }
        return IAppConstants.DASH;
    }

    public static CrmRcaReasonPojo getReasonById( List<CrmRcaReasonPojo> reasons, long inReasonId )
    {
        for ( CrmRcaReasonPojo crmRcaReasonPojo : reasons )
        {
            if ( crmRcaReasonPojo.getCategoryId() == inReasonId )
            {
                return crmRcaReasonPojo;
            }
        }
        return null;
    }

    public static boolean isValidCRF( String inCRF )
    {
        boolean valid = false;
        if ( StringUtils.isNotBlank( inCRF ) )
        {
            ConfigDto inConfigDto = new ConfigDto();
            CrmCustomerDetailsPojo pojo = new CrmCustomerDetailsPojo();
            pojo.setCrfId( inCRF );
            inConfigDto.setCustomerDetail( pojo );
            ConfigDto configDto = getConfigManagerImpl().getCustomerDetails( inConfigDto );
            if ( StringUtils.equals( CRMServiceCode.CRM001.getStatusCode(), configDto.getStatusCode() )
                    && CommonValidator.isValidCollection( configDto.getCustomerDetailPojos() ) )
            {
                valid = true;
            }
        }
        return valid;
    }

    public static void setConnectionTypeDetails( CrmCustomerDetailsPojo inCustomerDetailsPojo )
    {
        if ( StringUtils.isValidObj( inCustomerDetailsPojo )
                && StringUtils.isNotBlank( inCustomerDetailsPojo.getConnectionType() ) )
        {
            if ( StringUtils.equals( CRMDisplayListConstants.INDIVIDUAL.getCode(),
                                     inCustomerDetailsPojo.getConnectionType() )
                    || StringUtils.equals( CRMDisplayListConstants.PROPRIETORSHIP.getCode(),
                                           inCustomerDetailsPojo.getConnectionType() ) )
            {
                inCustomerDetailsPojo.setAuthSignDesg( null );
                inCustomerDetailsPojo.setAuthSignFname( null );
                inCustomerDetailsPojo.setAuthSignLname( null );
                inCustomerDetailsPojo.setOrgCordFname( null );
                inCustomerDetailsPojo.setOrgCordLname( null );
            }
            else
            {
                inCustomerDetailsPojo.setCustLname( null );
                inCustomerDetailsPojo.setCustGender( null );
                inCustomerDetailsPojo.setDisplayCustDob( null );
                inCustomerDetailsPojo.setCustDob( null );
            }
        }
    }

    public static void main( String[] args )
    {
        System.out.println( getPrimaryMacAdd( "0123.0000.0000" ) );
    }
}
