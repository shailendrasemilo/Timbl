package com.np.tele.selfcare.common.utils;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.commons.validator.EmailValidator;
import org.apache.log4j.Logger;

import com.np.tele.crm.constants.CRMCustomerActivityActions;
import com.np.tele.crm.constants.CRMServiceCode;
import com.np.tele.crm.constants.CRMStatusCode;
import com.np.tele.crm.constants.IAppConstants;
import com.np.tele.crm.constants.QRCRolCategories;
import com.np.tele.crm.service.client.CityPojo;
import com.np.tele.crm.service.client.CrmAddressDetailsPojo;
import com.np.tele.crm.service.client.CrmCapDto;
import com.np.tele.crm.service.client.CrmCustomerDetailsPojo;
import com.np.tele.crm.service.client.CrmPlanDetailsPojo;
import com.np.tele.crm.service.client.CrmQrcDto;
import com.np.tele.crm.service.client.CrmSelfcareCategoriesPojo;
import com.np.tele.crm.utils.StringUtils;
import com.np.tele.selfcare.bm.ISelfcareManager;
import com.np.tele.selfcare.bm.ISelfcarePlanMigrationMgr;
import com.np.tele.selfcare.forms.SelfcareForm;
import com.np.validator.util.CommonValidator;

public class SelfcareDwrManagerImpl
    implements ISelfcareDwrManager
{
    private static final Logger LOGGER = Logger.getLogger( SelfcareDwrManagerImpl.class );

    public ISelfcareManager getSelfcareManager()
    {
        return (ISelfcareManager) IAppConstants.flyWeightBeanMap.get( IAppConstants.SELFCARE_MANAGER );
    }

    public ISelfcarePlanMigrationMgr getSelfcarePlanMigrationMgr()
    {
        return (ISelfcarePlanMigrationMgr) IAppConstants.flyWeightBeanMap
                .get( IAppConstants.SELFCARE_PLAN_MIGRATION_MANAGER );
    }

    @Override
    public List<CrmSelfcareCategoriesPojo> getSelfcareCategories( String inSubject )
    {
        LOGGER.info( "in SelfcareDwrManagerImpl:getSelfcareCategories  " + inSubject );
        SelfcareForm selfcareForm = new SelfcareForm();
        selfcareForm.setCrmSelfcareCategoriesPojo( new CrmSelfcareCategoriesPojo() );
        selfcareForm.getCrmSelfcareCategoriesPojo().setSubject( inSubject );
        CrmQrcDto crmQrcDto = getSelfcareManager().getSelfcareCategories( selfcareForm );
        return crmQrcDto.getCrmSelfcareCategoriesPojos();
    }

    @Override
    public String[] saveCustomerProfileDetails( String inRecordID,
                                                String inCustID,
                                                String inRolCategory,
                                                String inNewValue )
    {
        LOGGER.info( "in selfcareDWRimpl : savecustomerdetails" );
        boolean processRequest = true;
        CrmCapDto crmCapDto = new CrmCapDto();
        String msg = "Required details are not present.";
        String[] str =
        { inRecordID, inCustID, inRolCategory, inNewValue };
        if ( StringUtils.checkAllvalidObj( str, false ) )
        {
            LOGGER.info( "recid:: " + inRecordID + " custid:: " + inCustID + " rolcat:: " + inRolCategory
                    + " newval:: " + inNewValue );
            if ( StringUtils.equals( inRolCategory, QRCRolCategories.RMN_MODIFY.getEvent() ) )
            {
                LOGGER.info( "event: " + QRCRolCategories.RMN_MODIFY.getEvent() );
                CrmCustomerDetailsPojo crmCustomerDetailsPojo = new CrmCustomerDetailsPojo();
                crmCustomerDetailsPojo.setRmn( Long.parseLong( inNewValue ) );
                crmCustomerDetailsPojo.setRecordId( Long.parseLong( inRecordID ) );
                crmCustomerDetailsPojo.setLastModifiedBy( inCustID );
                crmCapDto.setActivityAction( CRMCustomerActivityActions.RMN_MODIFY.getActionDesc() );
                crmCapDto.setCustomerDetailsPojo( crmCustomerDetailsPojo );
            }
            else if ( StringUtils.equals( inRolCategory, QRCRolCategories.RTN_MODIFY.getEvent() ) )
            {
                LOGGER.info( "event: " + QRCRolCategories.RTN_MODIFY.getEvent() );
                CrmCustomerDetailsPojo crmCustomerDetailsPojo = new CrmCustomerDetailsPojo();
                crmCustomerDetailsPojo.setRtn( Long.parseLong( inNewValue ) );
                crmCustomerDetailsPojo.setRecordId( Long.parseLong( inRecordID ) );
                crmCustomerDetailsPojo.setLastModifiedBy( inCustID );
                crmCapDto.setActivityAction( CRMCustomerActivityActions.RTN_MODIFY.getActionDesc() );
                crmCapDto.setCustomerDetailsPojo( crmCustomerDetailsPojo );
            }
            else if ( StringUtils.equals( inRolCategory, QRCRolCategories.ALT_CONTACT_NO.getEvent() ) )
            {
                LOGGER.info( "event: " + QRCRolCategories.ALT_CONTACT_NO.getEvent() );
                CrmCustomerDetailsPojo crmCustomerDetailsPojo = new CrmCustomerDetailsPojo();
                crmCustomerDetailsPojo.setRecordId( Long.parseLong( inRecordID ) );
                crmCustomerDetailsPojo.setCustMobileNo( Long.parseLong( inNewValue ) );
                crmCustomerDetailsPojo.setLastModifiedBy( inCustID );
                crmCapDto.setActivityAction( CRMCustomerActivityActions.ALTERNATE_NUMBER_MODIFY.getActionDesc() );
                crmCapDto.setCustomerDetailsPojo( crmCustomerDetailsPojo );
            }
            else if ( StringUtils.equals( inRolCategory, QRCRolCategories.REGISTERED_EMAIL_ID.getEvent() ) )
            {
                LOGGER.info( "event: " + QRCRolCategories.REGISTERED_EMAIL_ID.getEvent() );
                if ( EmailValidator.getInstance().isValid( inNewValue ) )
                {
                    CrmCustomerDetailsPojo crmCustomerDetailsPojo = new CrmCustomerDetailsPojo();
                    crmCustomerDetailsPojo.setRecordId( Long.parseLong( inRecordID ) );
                    crmCustomerDetailsPojo.setCustEmailId( inNewValue );
                    crmCustomerDetailsPojo.setLastModifiedBy( inCustID );
                    crmCapDto.setActivityAction( CRMCustomerActivityActions.EMAIL_MODIFY.getActionDesc() );
                    crmCapDto.setCustomerDetailsPojo( crmCustomerDetailsPojo );
                }
                else
                {
                    processRequest = false;
                    msg = "Invalid email address.";
                }
            }
            else if ( StringUtils.equals( inRolCategory, QRCRolCategories.ALTERNATE_EMAIL_MODIFY.getEvent() ) )
            {
                LOGGER.info( "event: " + QRCRolCategories.ALTERNATE_EMAIL_MODIFY.getEvent() );
                LOGGER.info( "alt. email: " + inNewValue );
                if ( EmailValidator.getInstance().isValid( inNewValue ) )
                {
                    CrmCustomerDetailsPojo crmCustomerDetailsPojo = new CrmCustomerDetailsPojo();
                    crmCustomerDetailsPojo.setRecordId( Long.parseLong( inRecordID ) );
                    crmCustomerDetailsPojo.setAltEmailId( inNewValue );
                    crmCustomerDetailsPojo.setLastModifiedBy( inCustID );
                    crmCapDto.setActivityAction( CRMCustomerActivityActions.ALTERNATE_EMAIL_MODIFY.getActionDesc() );
                    crmCapDto.setCustomerDetailsPojo( crmCustomerDetailsPojo );
                }
                else
                {
                    processRequest = false;
                    msg = "Invalid email address.";
                }
            }
            else if ( StringUtils.equals( inRolCategory, QRCRolCategories.PAPERBILL_TO_EBILL.getEvent() )
                    || StringUtils.equals( inRolCategory, QRCRolCategories.EBILL_TO_PAPERBILL.getEvent() ) )
            {
                LOGGER.info( "event: " + inRolCategory );
                CrmPlanDetailsPojo planDetailsPojo = new CrmPlanDetailsPojo();
                planDetailsPojo.setBillMode( inNewValue );
                planDetailsPojo.setRecordId( Long.parseLong( inRecordID ) );
                planDetailsPojo.setLastModifiedBy( inCustID );
                crmCapDto.setActivityAction( CRMCustomerActivityActions.BILL_MODE_MODIFY.getActionDesc() );
                crmCapDto.setPlanDetailsPojo( planDetailsPojo );
            }
        }
        if ( processRequest )
        {
            crmCapDto.setChangeRequest( inRolCategory );
            crmCapDto.setCustomerId( inCustID );
            crmCapDto.setUserId( inCustID );
            crmCapDto = getSelfcareManager().saveCustomerProfileDetails( crmCapDto );
            if ( StringUtils.equals( CRMServiceCode.CRM001.getStatusCode(), crmCapDto.getStatusCode() ) )
                return new String[]
                { "success", crmCapDto.getStatusDesc() + " Generated Ticket ID: " + crmCapDto.getGeneratedTicketId() };
            else
                msg = crmCapDto.getStatusDesc();
        }
        return new String[]
        { "error", msg };
    }

    @Override
    public List<com.np.tele.crm.service.client.ContentPojo> getMigrationPolicy( String oldBasePlanCode,
                                                                                  String newBasePlanCode,
                                                                                  String oldAddonPlanCode,
                                                                                  String newAddonPlanCode,
                                                                                  String serviceType,
                                                                                  String customerId )
    {
        CrmQrcDto crmQrcDto = new CrmQrcDto();
        crmQrcDto.setOldBasePlanCode( oldBasePlanCode );
        crmQrcDto.setNewBasePlanCode( newBasePlanCode );
        crmQrcDto.setNewAddonPlanCode( newAddonPlanCode );
        crmQrcDto.setOldAddonPlanCode( oldAddonPlanCode );
        crmQrcDto.setServiceType( serviceType );
        crmQrcDto.setCustomerId( customerId );
        try
        {
            LOGGER.info( "Old Base Plan Code:: " + oldBasePlanCode );
            LOGGER.info( "New Base Plan Code:: " + newBasePlanCode );
            LOGGER.info( "New Addon Plan Code:: " + newAddonPlanCode );
            LOGGER.info( "Old Addon Plan Code:: " + oldAddonPlanCode );
            LOGGER.info( "Service Type:: " + serviceType );
            LOGGER.info( "Customer ID:: " + customerId );
            crmQrcDto = getSelfcarePlanMigrationMgr().getMigrationPolicy( crmQrcDto );
            if ( CommonValidator.isValidCollection( crmQrcDto.getContentPojos() ) )
            {
                LOGGER.info( "Size of policy list:" + crmQrcDto.getContentPojos().size() );
            }
        }
        catch ( Exception ex )
        {
            LOGGER.error( "Error while fetching getMigrationPolicy ", ex );
        }
        if ( CommonValidator.isValidCollection( crmQrcDto.getContentPojos() ) )
        {
            return crmQrcDto.getContentPojos();
        }
        else
            return null;
    }

    @Override
    public String[] sendEmailVerificationLink( HttpSession inSession )
    {
        LOGGER.info( "in sendEmailVerificationLink( HttpSession inSession )" );
        if ( StringUtils.isValidObj( inSession ) && StringUtils.isValidObj( inSession.getAttribute( "selfcareForm" ) ) )
        {
            SelfcareForm selfcareForm = (SelfcareForm) inSession.getAttribute( "selfcareForm" );
            CrmCustomerDetailsPojo customerDetailsPojo = selfcareForm.getCustomerDetailsPojo();
            if ( StringUtils.isValidObj( customerDetailsPojo ) )
            {
                if ( StringUtils.equals( customerDetailsPojo.getEmailValidationFlag(), IAppConstants.NO_CHAR ) )
                {
                    CrmCapDto capDto = new CrmCapDto();
                    capDto.setCustomerDetailsPojo( customerDetailsPojo );
                    capDto = getSelfcareManager().sendVerificationLink( capDto );
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
            LOGGER.info( "session = " + inSession + " || selfcareForm = " + inSession.getAttribute( "selfcareForm" ) );
        }
        return null;
    }
}
