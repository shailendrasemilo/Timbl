package com.np.tele.crm.lms.form;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;

import com.np.tele.crm.common.utils.ValidationUtil;
import com.np.tele.crm.constants.CRMActionConstants;
import com.np.tele.crm.constants.CRMDisplayListConstants;
import com.np.tele.crm.constants.CRMOperationStages;
import com.np.tele.crm.constants.CRMParameter;
import com.np.tele.crm.constants.CRMRegex;
import com.np.tele.crm.constants.FileHeaderConstants;
import com.np.tele.crm.constants.IAppConstants;
import com.np.tele.crm.constants.IDateConstants;
import com.np.tele.crm.constants.IPropertiesConstant;
import com.np.tele.crm.gis.bm.GISUtils;
import com.np.tele.crm.pojos.ContentPojo;
import com.np.tele.crm.service.client.AppointmentPojo;
import com.np.tele.crm.service.client.CityPojo;
import com.np.tele.crm.service.client.CrmSrTicketsPojo;
import com.np.tele.crm.service.client.CrmTicketHistoryPojo;
import com.np.tele.crm.service.client.LmsPojo;
import com.np.tele.crm.service.client.PartnerPojo;
import com.np.tele.crm.service.client.RemarksPojo;
import com.np.tele.crm.service.client.StatePojo;
import com.np.tele.crm.utils.ICRMValidationCriteriaUtil;
import com.np.tele.crm.utils.StringUtils;
import com.np.validator.util.ValidationPojoUtil;

public class LmsFormHelper
{
    private static final Logger LOGGER = Logger.getLogger( LmsFormHelper.class );

    public static void resetLMSForm( LmsForm inLmsForm, String inMethodName )
    {
        LOGGER.info( "Reset LMS Form ::" + inMethodName );
        if ( StringUtils.equals( IAppConstants.METHOD_LEAD_GENEREATION, inMethodName ) )
        {
            inLmsForm.setLmsPojo( new LmsPojo() );
            inLmsForm.setRemarksPojo( new RemarksPojo() );
            inLmsForm.setAppointmentPojo( new AppointmentPojo() );
            inLmsForm.setCityPojoList( new ArrayList<CityPojo>() );
            inLmsForm.setPartnerList( new ArrayList<PartnerPojo>() );
            inLmsForm.setCityPojoList( new ArrayList<CityPojo>() );
            inLmsForm.setStatePojoList( new ArrayList<StatePojo>() );
        }
        else if ( StringUtils.equals( IAppConstants.METHOD_MODIFY_DETAILS, inMethodName )
                || StringUtils.equals( IAppConstants.METHOD_PERFORM_ACTION, inMethodName ) )
        {
            inLmsForm.setLmsPojo( new LmsPojo() );
            inLmsForm.setRemarksPojo( new RemarksPojo() );
            inLmsForm.setAppointmentPojo( new AppointmentPojo() );
            inLmsForm.setAppointmentPojoList( new ArrayList<AppointmentPojo>() );
            inLmsForm.setAppointmentTimings( new ArrayList<ContentPojo>() );
            inLmsForm.setAppointmentModes( new ArrayList<ContentPojo>() );
            inLmsForm.setPartnerList( new ArrayList<PartnerPojo>() );
            inLmsForm.setCityPojoList( new ArrayList<CityPojo>() );
            inLmsForm.setStatePojoList( new ArrayList<StatePojo>() );
        }
        else if ( StringUtils.equals( IAppConstants.METHOD_SAVE_REMARKS, inMethodName )
                || StringUtils.equals( IAppConstants.METHOD_LOG_LMS_TICKET, inMethodName ) )
        {
            inLmsForm.setRemarksPojo( new RemarksPojo() );
            inLmsForm.setLmsPojo( new LmsPojo() );
            inLmsForm.setSrTicketPojo( new CrmSrTicketsPojo() );
            inLmsForm.setTicketHistory( new CrmTicketHistoryPojo() );
        }
        else if ( StringUtils.equals( IAppConstants.METHOD_LMS_FILE_UPLOAD, inMethodName ) )
        {
            inLmsForm.setLmsPojo( new LmsPojo() );
            inLmsForm.setRemarksPojo( new RemarksPojo() );
        }
    }

    public static ActionMessages validateLmsForm( String inMethod, ActionMessages inActionError, LmsForm inLmsForm )
    {
        LOGGER.info( "Excecuting lead validation Form for method :" + inMethod );
        if ( StringUtils.isValidObj( inLmsForm.getRemarksPojo() )
                && StringUtils.isValidObj( inLmsForm.getAppointmentPojo() )
                && StringUtils.isValidObj( inLmsForm.getLmsPojo() ) )
        {
            /*
             * validation for new lead creation through CC users Starts
             */
            if ( StringUtils.equals( inMethod, IAppConstants.METHOD_LEAD_GENEREATION ) )
            {
                if ( contactNumValidation( inActionError, inLmsForm ).isEmpty() )
                {
                    if ( remainingLeadAccuracyValidation( inActionError, inLmsForm ).isEmpty() )
                    {
                        if ( remarkNAppointmentValidation( inActionError, inLmsForm ).isEmpty() )
                        {
                            feasibilityChecks( inActionError, inLmsForm ).isEmpty();
                        }
                    }
                }
            }
            /*
             * validation for new lead creation through CC users Ends
             */
            /*
             * validation for lead modification while saving Starts
             */
            else if ( StringUtils.equals( inMethod, IAppConstants.METHOD_MODIFY_DETAILS ) )
            {
                if ( contactNumValidation( inActionError, inLmsForm ).isEmpty() )
                    if ( remarkNAppointmentValidation( inActionError, inLmsForm ).isEmpty() )
                    {
                        if ( feasibilityChecks( inActionError, inLmsForm ).isEmpty() )
                        {
                            if ( !StringUtils.equals( inLmsForm.getRemarksPojo().getActions(), "" ) )
                            {
                                inActionError
                                        .add( IAppConstants.APP_ERROR,
                                              new ActionMessage( IPropertiesConstant.ERROR_LEAD_ACTION_INVALID_SAVE ) );
                            }
                        }
                    }
            }
            /*
             * validation for lead modification while saving Ends
             */
            /*
             * validation for lead submitting while forwarding/closing/maturing Starts
             */
            else if ( StringUtils.equals( inMethod, IAppConstants.METHOD_PERFORM_ACTION ) )
            {
                if ( StringUtils.equals( inLmsForm.getRemarksPojo().getActions(), "" ) )
                {
                    inActionError.add( IAppConstants.APP_ERROR,
                                       new ActionMessage( IPropertiesConstant.ERROR_LEAD_ACTION_SELECT ) );
                }
                else
                {
                    if ( StringUtils.equals( inLmsForm.getRemarksPojo().getActions(),
                                             CRMActionConstants.CLOSE_LEAD.getStoringValue() ) )
                    {
                        if ( remarkNAppointmentValidation( inActionError, inLmsForm ).isEmpty() )
                        {
                            if ( inLmsForm.getRemarksPojo().getReasonId() == 0 )
                            {
                                inActionError.add( IAppConstants.APP_ERROR,
                                                   new ActionMessage( IPropertiesConstant.ERROR_LEAD_REASON_SELECT ) );
                            }
                            else if ( StringUtils.isNotEmpty( inLmsForm.getAppointmentPojo().getDisplayDate() ) )
                            {
                                inActionError
                                        .add( IAppConstants.APP_ERROR,
                                              new ActionMessage( IPropertiesConstant.ERROR_LEAD_CLOSE_NO_APPOINT ) );
                            }
                        }
                    }
                    else if ( StringUtils.equals( inLmsForm.getRemarksPojo().getActions(),
                                                  CRMActionConstants.MATURE_LEAD.getStoringValue() ) )
                    {
                        if ( remarkNAppointmentValidation( inActionError, inLmsForm ).isEmpty() )
                        {
                            if ( StringUtils.isEmpty( inLmsForm.getCrfIds() )
                                    && ( StringUtils.equals( inLmsForm.getInaModule(), IAppConstants.Y ) ) )
                            {
                                inActionError.add( IAppConstants.APP_ERROR,
                                                   new ActionMessage( IPropertiesConstant.ERROR_LEAD_CRFIDS_MISSING ) );
                            }
                            else if ( StringUtils.isNotEmpty( inLmsForm.getAppointmentPojo().getDisplayDate() ) )
                            {
                                inActionError
                                        .add( IAppConstants.APP_ERROR,
                                              new ActionMessage( IPropertiesConstant.ERROR_LEAD_CLOSE_NO_APPOINT ) );
                            }
                        }
                    }
                    else
                    {
                        if ( StringUtils.equals( inLmsForm.getLmsPojo().getLmsStage(),
                                                 CRMOperationStages.SALES_COORDINATOR.getCode() ) )
                        {
                            if ( contactNumValidation( inActionError, inLmsForm ).isEmpty() )
                                if ( remainingLeadDataValidation( inActionError, inLmsForm ).isEmpty() )
                                    if ( remainingLeadAccuracyValidation( inActionError, inLmsForm ).isEmpty() )
                                        if ( remarkNAppointmentValidation( inActionError, inLmsForm ).isEmpty() )
                                            if ( feasibilityChecks( inActionError, inLmsForm ).isEmpty() )
                                                stageSCValidation( inActionError, inLmsForm );
                        }
                        else if ( StringUtils.equals( inLmsForm.getLmsPojo().getLmsStage(),
                                                      CRMOperationStages.AREA_MANAGER.getCode() ) )
                        {
                            if ( remarkNAppointmentValidation( inActionError, inLmsForm ).isEmpty() )
                            {
                                /*if ( StringUtils.isNotEmpty( inLmsForm.getAppointmentPojo().getDisplayDate() )
                                        && !StringUtils.equals( inLmsForm.getRemarksPojo().getActions(),
                                                                CRMActionConstants.FORWARD_Sales.getStoringValue() ) )
                                {
                                    inActionError
                                            .add( IAppConstants.APP_ERROR,
                                                  new ActionMessage( IPropertiesConstant.ERROR_LEAD_CLOSE_NO_APPOINT ) );
                                }*/
                            }
                        }
                        else if ( StringUtils.equals( inLmsForm.getLmsPojo().getLmsStage(),
                                                      CRMOperationStages.SALES.getCode() ) )
                        {
                            if ( remarkNAppointmentValidation( inActionError, inLmsForm ).isEmpty() )
                            {
                                /* if ( StringUtils.isNotEmpty( inLmsForm.getAppointmentPojo().getDisplayDate() ) )
                                 {
                                     inActionError
                                             .add( IAppConstants.APP_ERROR,
                                                   new ActionMessage( IPropertiesConstant.ERROR_LEAD_CLOSE_NO_APPOINT ) );
                                 }*/
                            }
                        }
                        else if ( StringUtils.equals( inLmsForm.getLmsPojo().getLmsStage(),
                                                      CRMOperationStages.FULFILLMENT_TEAM.getCode() ) )
                        {
                            if ( remarkNAppointmentValidation( inActionError, inLmsForm ).isEmpty() )
                                stageFTValidation( inActionError, inLmsForm );
                        }
                        else if ( StringUtils.equals( inLmsForm.getLmsPojo().getLmsStage(),
                                                      CRMOperationStages.CLUSTER_HEAD.getCode() ) )
                        {
                            if ( remarkNAppointmentValidation( inActionError, inLmsForm ).isEmpty() )
                            {
                               /* if ( StringUtils.isEmpty( inLmsForm.getAppointmentPojo().getDisplayDate() ) )
                                {
                                    inActionError
                                            .add( IAppConstants.APP_ERROR,
                                                  new ActionMessage( IPropertiesConstant.ERROR_LEAD_APPOINTMENT_REQ ) );
                                }
                                else
                                {
                                    appointmentValidation( inActionError, inLmsForm );
                                }*/
                            }
                        }
                    }
                }
            }
            /*
             * validation for lead submitting while forwarding/closing/maturing Ends
             */
        }
        LmsPojo lmsPojo = inLmsForm.getLmsPojo();
        if ( StringUtils.isValidObj( lmsPojo ) )
        {
            if ( StringUtils.isNotBlank( lmsPojo.getState() ) )
            {
                inLmsForm.setCityPojoList( GISUtils.getCities( inLmsForm.getStatePojoList(), lmsPojo.getState() ) );
                LOGGER.info( "City Pojo List Size : " + inLmsForm.getCityPojoList().size() );
            }
        }
        return inActionError;
    }

    private static ActionMessages contactNumValidation( ActionMessages inActionError, LmsForm inLmsForm )
    {
        if ( inLmsForm.getLmsPojo().getContactNumber() == 0 && inLmsForm.getLmsPojo().getCtiNumber() == 0 )
        {
            inActionError
                    .add( IAppConstants.APP_ERROR, new ActionMessage( IPropertiesConstant.ERROR_LEAD_CONTACT_CTI ) );
        }
        else if ( inLmsForm.getLmsPojo().getContactNumber() > 0 )
        {
            String contactNum = String.valueOf( inLmsForm.getLmsPojo().getContactNumber() );
            String startNos = ValidationUtil.validateInputMobile( contactNum );
            if ( !StringUtils.isNumeric( contactNum ) )
            {
                inActionError.add( IAppConstants.APP_ERROR,
                                   new ActionMessage( IPropertiesConstant.ERROR_LEAD_CONTACT_NUMERIC ) );
            }
            else if ( contactNum.length() != FileHeaderConstants.LMS2.getSize() )
            {
                inActionError.add( IAppConstants.APP_ERROR,
                                   new ActionMessage( IPropertiesConstant.ERROR_LEAD_CONTACT_LENGTH ) );
            }
            else if ( !StringUtils.isEmpty( startNos ) )
            {
                inActionError.add( IAppConstants.APP_ERROR,
                                   new ActionMessage( IPropertiesConstant.ERROR_MOBILE_NO_START_WITH, startNos ) );
            }
        }
        return inActionError;
    }

    /*lead data validation for Sales Coordinator Stage Starts*/
    private static ActionMessages remainingLeadDataValidation( ActionMessages inActionError, LmsForm inLmsForm )
    {
        if ( StringUtils.isEmpty( inLmsForm.getLmsPojo().getProduct() ) )
        {
            inActionError.add( IAppConstants.APP_ERROR, new ActionMessage( IPropertiesConstant.ERROR_LEAD_PRODUCT ) );
        }
        else if ( StringUtils.isEmpty( inLmsForm.getLmsPojo().getCustomerName() ) )
        {
            inActionError.add( IAppConstants.APP_ERROR, new ActionMessage( IPropertiesConstant.ERROR_LEAD_CUST_NAME ) );
        }
        else if ( StringUtils.isNotEmpty( inLmsForm.getLmsPojo().getCustomerName() ) )
        {
            if ( !StringUtils.compareRegularExp( CRMRegex.PATTERN_FOR_CUSTOMER_NAME.getPattern(), inLmsForm
                    .getLmsPojo().getCustomerName() ) )
            {
                inActionError.add( IAppConstants.APP_ERROR,
                                   new ActionMessage( CRMRegex.PATTERN_FOR_CUSTOMER_NAME.getErrorKey(),
                                                      IAppConstants.CUSTOMER,
                                                      IAppConstants.FIRST ) );
            }
        }
        /*else if ( StringUtils.isEmpty( inLmsForm.getLmsPojo().getEmailId() ) )
        {
            inActionError.add( IAppConstants.APP_ERROR, new ActionMessage( IPropertiesConstant.ERROR_LEAD_CUST_EMAIL ) );
        }*/
        else if ( StringUtils.isNotEmpty( inLmsForm.getLmsPojo().getEmailId() )
                && !StringUtils.compareRegularExp( CRMRegex.PATTERN_EMAIL.getPattern(), inLmsForm.getLmsPojo()
                        .getEmailId() ) )
        {
            inActionError.add( IAppConstants.APP_ERROR, new ActionMessage( CRMRegex.PATTERN_EMAIL.getErrorKey(),
                                                                           inLmsForm.getLmsPojo().getEmailId() ) );
        }
        else if ( StringUtils.isEmpty( inLmsForm.getLmsPojo().getState() ) )
        {
            inActionError.add( IAppConstants.APP_ERROR, new ActionMessage( IPropertiesConstant.ERROR_LEAD_STATE ) );
        }
        else if ( StringUtils.isEmpty( inLmsForm.getLmsPojo().getCity() ) )
        {
            inActionError.add( IAppConstants.APP_ERROR, new ActionMessage( IPropertiesConstant.ERROR_LEAD_CITY ) );
        }
        else if ( StringUtils.isEmpty( inLmsForm.getLmsPojo().getArea() ) )
        {
            inActionError.add( IAppConstants.APP_ERROR, new ActionMessage( IPropertiesConstant.ERROR_LEAD_AREA ) );
        }
        else if ( StringUtils.isEmpty( inLmsForm.getLmsPojo().getLocality() ) )
        {
            inActionError.add( IAppConstants.APP_ERROR, new ActionMessage( IPropertiesConstant.ERROR_LEAD_LOCALITY ) );
        }
        //        else if ( StringUtils.isEmpty( inLmsForm.getLmsPojo().getSociety() ) )
        //        {
        //            inActionError.add( IAppConstants.APP_ERROR, new ActionMessage( IPropertiesConstant.ERROR_LEAD_SOCIETY ) );
        //        }
        else if ( StringUtils.isEmpty( inLmsForm.getLmsPojo().getHouseNumber() ) )
        {
            inActionError.add( IAppConstants.APP_ERROR, new ActionMessage( IPropertiesConstant.ERROR_LEAD_HOUSE_NUM ) );
        }
        else if ( StringUtils.isEmpty( inLmsForm.getLmsPojo().getLandmark() ) )
        {
            inActionError.add( IAppConstants.APP_ERROR, new ActionMessage( IPropertiesConstant.ERROR_LEAD_LANDMARK ) );
        }
        else if ( inLmsForm.getLmsPojo().getPincode() == 0 )
        {
            inActionError.add( IAppConstants.APP_ERROR, new ActionMessage( IPropertiesConstant.ERROR_LEAD_PIN_REQ ) );
        }
        return inActionError;
    }/*lead data validation for Sales Coordinator Stage Ends*/

    private static ActionMessages remarkNAppointmentValidation( ActionMessages inActionError, LmsForm inLmsForm )
    {
        /*Remarks Related Checks Starts*/
        if ( StringUtils.isEmpty( inLmsForm.getRemarksPojo().getRemarks() ) )
        {
            inActionError.add( IAppConstants.APP_ERROR, new ActionMessage( IPropertiesConstant.ERROR_LEAD_REMARK_REQ ) );
        }
        else if ( StringUtils.isNotEmpty( inLmsForm.getRemarksPojo().getRemarks() )
                && ( inLmsForm.getRemarksPojo().getRemarks().length() < 2 || inLmsForm.getRemarksPojo().getRemarks()
                        .length() > 4000 ) )
        {
            inActionError.add( IAppConstants.APP_ERROR, new ActionMessage( IPropertiesConstant.ERROR_LEAD_VAR_LENGTH,
                                                                           "Remarks" ) );
        }
        /*Remarks Related Checks Ends*/
        /*Appointment Related Checks Starts*/
        else if ( StringUtils.isNotEmpty( inLmsForm.getAppointmentPojo().getDisplayDate() )
                && !StringUtils.equals( inLmsForm.getRemarksPojo().getActions(),
                                        CRMActionConstants.CLOSE_LEAD.getStoringValue() ) )
        {
            //appointmentValidation( inActionError, inLmsForm );
        }
        /*Appointment Related Checks Ends*/
        return inActionError;
    }

    /*Method to Compare incoming date with current date*/
    private static int compareDateWithCurrent( String inInputDate )
    {
        int isToday = 0;
        try
        {
            Date inputDate = IDateConstants.SDF_DD_MMM_YYYY.parse( inInputDate );
            String currentDateStr = IDateConstants.SDF_DD_MMM_YYYY.format( Calendar.getInstance().getTime() );
            Date currentDate = IDateConstants.SDF_DD_MMM_YYYY.parse( currentDateStr );
            if ( currentDate.compareTo( inputDate ) < 0 )
            {
                isToday = 1;
            }
            else if ( currentDate.compareTo( inputDate ) > 0 )
            {
                isToday = -1;
            }
        }
        catch ( Exception ex )
        {
            LOGGER.error( "Exception while compare input date with current date : ", ex );
        }
        return isToday;
    }

    /*Method to verify incoming time slot with current Hour*/
    private static boolean compareCurrentTimeSlot( String inInputTimeSlot )
    {
        boolean isValid = false;
        try
        {
            Date currentDate = Calendar.getInstance().getTime();
            String currentHour = IDateConstants.SDF_HH.format( currentDate );
            int currentHourValue = Integer.parseInt( currentHour );
            LOGGER.info( "Current Hour Value : " + currentHourValue );
            if ( currentHourValue <= 12 )
            {
                isValid = true;
            }
            else if ( currentHourValue <= 16 )
            {
                if ( !StringUtils.equals( inInputTimeSlot, CRMDisplayListConstants.MORNING.getCode() ) )
                    isValid = true;
            }
            else if ( currentHourValue <= 20 )
            {
                if ( StringUtils.equals( inInputTimeSlot, CRMDisplayListConstants.Evening.getCode() ) )
                    isValid = true;
            }
        }
        catch ( Exception ex )
        {
            LOGGER.error( "Exception while comparing input time slot with current time : ", ex );
        }
        return isValid;
    }

    private static ActionMessages remainingLeadAccuracyValidation( ActionMessages inActionError, LmsForm inLmsForm )
    {
        if ( StringUtils.isNotEmpty( inLmsForm.getLmsPojo().getCustomerName() )
                && inLmsForm.getLmsPojo().getCustomerName().length() > FileHeaderConstants.LMS1.getSize() )
        {
            inActionError.add( IAppConstants.APP_ERROR, new ActionMessage( IPropertiesConstant.ERROR_LEAD_VAR_LENGTH,
                                                                           FileHeaderConstants.LMS1.getValue(),
                                                                           FileHeaderConstants.LMS1.getSize() ) );
        }
        else if ( StringUtils.isNotEmpty( inLmsForm.getLmsPojo().getEmailId() )
                && !StringUtils.compareRegularExp( CRMRegex.PATTERN_EMAIL.getPattern(), inLmsForm.getLmsPojo()
                        .getEmailId() ) )
        {
            inActionError.add( IAppConstants.APP_ERROR, new ActionMessage( CRMRegex.PATTERN_EMAIL.getErrorKey(),
                                                                           inLmsForm.getLmsPojo().getEmailId() ) );
        }
        else if ( StringUtils.isNotEmpty( inLmsForm.getLmsPojo().getEmailId() )
                && inLmsForm.getLmsPojo().getEmailId().length() > FileHeaderConstants.LMS12.getSize() )
        {
            inActionError.add( IAppConstants.APP_ERROR, new ActionMessage( IPropertiesConstant.ERROR_LEAD_VAR_LENGTH,
                                                                           FileHeaderConstants.LMS12.getValue(),
                                                                           FileHeaderConstants.LMS12.getSize() ) );
        }
        else if ( StringUtils.isNotEmpty( inLmsForm.getLmsPojo().getArea() )
                && inLmsForm.getLmsPojo().getArea().length() > FileHeaderConstants.LMS5.getSize() )
        {
            inActionError.add( IAppConstants.APP_ERROR, new ActionMessage( IPropertiesConstant.ERROR_LEAD_VAR_LENGTH,
                                                                           FileHeaderConstants.LMS5.getValue(),
                                                                           FileHeaderConstants.LMS5.getSize() ) );
        }
        //        else if ( StringUtils.isNotEmpty( inLmsForm.getLmsPojo().getSociety() )
        //                && inLmsForm.getLmsPojo().getSociety().length() > FileHeaderConstants.LMS7.getSize() )
        //        {
        //            inActionError.add( IAppConstants.APP_ERROR, new ActionMessage( IPropertiesConstant.ERROR_LEAD_VAR_LENGTH,
        //                                                                           FileHeaderConstants.LMS7.getValue(),
        //                                                                           FileHeaderConstants.LMS7.getSize() ) );
        //        }
        else if ( StringUtils.isNotEmpty( inLmsForm.getLmsPojo().getLocality() )
                && inLmsForm.getLmsPojo().getLocality().length() > 60 )// FileHeaderConstants.LMS6.getSize()
        {
            inActionError.add( IAppConstants.APP_ERROR, new ActionMessage( IPropertiesConstant.ERROR_LEAD_VAR_LENGTH,
                                                                           "Locality / Sector - Society",
                                                                           60 ) );
        }
        else if ( StringUtils.isNotEmpty( inLmsForm.getLmsPojo().getLandmark() )
                && inLmsForm.getLmsPojo().getLandmark().length() > FileHeaderConstants.LMS9.getSize() )
        {
            inActionError.add( IAppConstants.APP_ERROR, new ActionMessage( IPropertiesConstant.ERROR_LEAD_VAR_LENGTH,
                                                                           FileHeaderConstants.LMS9.getValue(),
                                                                           FileHeaderConstants.LMS9.getSize() ) );
        }
        else if ( inLmsForm.getLmsPojo().getPincode() > 0 )
        {
            String value = String.valueOf( inLmsForm.getLmsPojo().getPincode() );
            if ( !StringUtils.isNumeric( value ) )
            {
                inActionError.add( IAppConstants.APP_ERROR,
                                   new ActionMessage( IPropertiesConstant.ERROR_LEAD_PIN_NUMERIC ) );
            }
            else if ( value.length() < 6 || value.length() > 6 )
            {
                inActionError.add( IAppConstants.APP_ERROR,
                                   new ActionMessage( IPropertiesConstant.ERROR_LEAD_PIN_LENGTH ) );
            }
        }
        return inActionError;
    }

    private static ActionMessages appointmentValidation( ActionMessages inActionError, LmsForm inLmsForm )
    {
        if ( compareDateWithCurrent( inLmsForm.getAppointmentPojo().getDisplayDate() ) == -1 )
        {
            inActionError.add( IAppConstants.APP_ERROR,
                               new ActionMessage( IPropertiesConstant.ERROR_LEAD_APPOINTMENT_BACK_DATE ) );
        }
        else if ( StringUtils.isEmpty( inLmsForm.getAppointmentPojo().getModeOfContact() ) )
        {
            inActionError.add( IAppConstants.APP_ERROR,
                               new ActionMessage( IPropertiesConstant.ERROR_LEAD_APPOINTMENT_MODE ) );
        }
        if ( compareDateWithCurrent( inLmsForm.getAppointmentPojo().getDisplayDate() ) == 0 )
        {
            if ( StringUtils.isEmpty( inLmsForm.getAppointmentPojo().getAppointmentTime() ) )
            {
                inActionError.add( IAppConstants.APP_ERROR,
                                   new ActionMessage( IPropertiesConstant.ERROR_LEAD_APPOINTMENT_TIME ) );
            }
            else if ( !compareCurrentTimeSlot( inLmsForm.getAppointmentPojo().getAppointmentTime() ) )
            {
                inActionError.add( IAppConstants.APP_ERROR,
                                   new ActionMessage( IPropertiesConstant.ERROR_LEAD_APPOINTMENT_TIME_INVALID ) );
            }
        }
        if ( inLmsForm.getAppointmentPojo().getContactNumber() > 0 && inActionError.isEmpty() )
        {
            String contactNum = String.valueOf( inLmsForm.getAppointmentPojo().getContactNumber() );
            String startNos = ValidationUtil.validateInputMobile( contactNum );
            if ( !StringUtils.isNumeric( contactNum ) )
            {
                inActionError.add( IAppConstants.APP_ERROR,
                                   new ActionMessage( IPropertiesConstant.ERROR_LEAD_CONTACT_NUMERIC ) );
            }
            else if ( contactNum.length() != FileHeaderConstants.LMS2.getSize() )
            {
                inActionError.add( IAppConstants.APP_ERROR,
                                   new ActionMessage( IPropertiesConstant.ERROR_LEAD_CONTACT_LENGTH ) );
            }
            else if ( !StringUtils.isEmpty( startNos ) )
            {
                inActionError.add( IAppConstants.APP_ERROR,
                                   new ActionMessage( IPropertiesConstant.ERROR_MOBILE_NO_START_WITH, startNos ) );
            }
        }
        if ( inActionError.isEmpty() )
        {
            if ( StringUtils.isNotEmpty( inLmsForm.getAppointmentPojo().getAppointmentAddress() )
                    && inLmsForm.getAppointmentPojo().getAppointmentAddress().length() > 256 )
            {
                inActionError.add( IAppConstants.APP_ERROR,
                                   new ActionMessage( IPropertiesConstant.ERROR_LEAD_VAR_LENGTH,
                                                      "Appointment Address",
                                                      "256" ) );
            }
            else if ( StringUtils.isNotEmpty( inLmsForm.getAppointmentPojo().getRemarks() )
                    && inLmsForm.getAppointmentPojo().getRemarks().length() < 4000 )
            {
                inActionError.add( IAppConstants.APP_ERROR,
                                   new ActionMessage( IPropertiesConstant.ERROR_LEAD_VAR_LENGTH,
                                                      "Appointment Remarks",
                                                      "4000" ) );
            }
        }
        return inActionError;
    }

    private static ActionMessages stageSCValidation( ActionMessages inActionError, LmsForm inLmsForm )
    {
        if ( StringUtils.equals( inLmsForm.getRemarksPojo().getActions(),
                                 CRMActionConstants.FORWARD_AM.getStoringValue() ) )
        {
            if ( StringUtils.equals( inLmsForm.getLmsPojo().getProduct(),
                                     CRMDisplayListConstants.RADIO_FREQUENCY.getCode() ) )
            {
                inActionError.add( IAppConstants.APP_ERROR,
                                   new ActionMessage( IPropertiesConstant.ERROR_LEAD_WRONG_FORWARD_AM ) );
            }
        }
        /* else if ( StringUtils.equals( inLmsForm.getRemarksPojo().getActions(),
                                       CRMActionConstants.FORWARD_FT.getStoringValue() ) )
         {
             if ( !StringUtils.equals( inLmsForm.getLmsPojo().getProduct(),
                                       CRMDisplayListConstants.RADIO_FREQUENCY.getCode() ) )
             {
                 inActionError.add( IAppConstants.APP_ERROR,
                                    new ActionMessage( IPropertiesConstant.ERROR_LEAD_WRONG_FORWARD_FT ) );
             }
         }*/
        return inActionError;
    }

    private static ActionMessages stageFTValidation( ActionMessages inActionError, LmsForm inLmsForm )
    {
        if ( StringUtils.isEmpty( inLmsForm.getLmsPojo().getArea() ) )
        {
            inActionError.add( IAppConstants.APP_ERROR, new ActionMessage( IPropertiesConstant.ERROR_LEAD_AREA ) );
        }
        else if ( StringUtils.equals( inLmsForm.getLmsPojo().getFeasibility(), "" ) )
        {
            inActionError.add( IAppConstants.APP_ERROR,
                               new ActionMessage( IPropertiesConstant.ERROR_LEAD_FEASIBLE_SELECT ) );
        }
        else if ( StringUtils.equals( inLmsForm.getRemarksPojo().getActions(),
                                      CRMActionConstants.FORWARD_AM.getStoringValue() )
                && StringUtils.equals( inLmsForm.getLmsPojo().getFeasibility(), CRMParameter.NO.getParameter() ) )
        {
            inActionError.add( IAppConstants.APP_ERROR,
                               new ActionMessage( IPropertiesConstant.ERROR_LEAD_WRONG_ROUTE_AM ) );
        }
        else if ( StringUtils.equals( inLmsForm.getRemarksPojo().getActions(),
                                      CRMActionConstants.BACKWARD_SC.getStoringValue() )
                && StringUtils.equals( inLmsForm.getLmsPojo().getFeasibility(), CRMParameter.YES.getParameter() ) )
        {
            inActionError.add( IAppConstants.APP_ERROR,
                               new ActionMessage( IPropertiesConstant.ERROR_LEAD_WRONG_ROUTE_SC ) );
        }
        return inActionError;
    }

    public static void validateSRTktPojo( ActionMessages inErrors, LmsForm inLmsForm )
    {
        CrmSrTicketsPojo ticketsPojo = inLmsForm.getSrTicketPojo();
        Map<String, Object[]> resultMap = ValidationPojoUtil
                .validateForm( ticketsPojo, ICRMValidationCriteriaUtil.FORM_QRCTICKET_POST_CRITERIA_SR, false );
        if ( !StringUtils.isValidObj( resultMap ) || resultMap.isEmpty() )
        {
            resultMap = ValidationPojoUtil.validateForm( inLmsForm.getTicketHistory(),
                                                         ICRMValidationCriteriaUtil.FORM_QRC_REMARKS_CHECK, false );
        }
        if ( ( StringUtils.isValidObj( resultMap ) ) && ( !resultMap.isEmpty() ) )
        {
            ValidationUtil.prepareErrorMessage( inErrors, resultMap );
        }
    }

    private static ActionMessages feasibilityChecks( ActionMessages inActionError, LmsForm inLmsForm )
    {
        StringBuffer errStr = new StringBuffer();
        //        if ( !StringUtils.isEmpty( inLmsForm.getLmsPojo().getSociety() ) )
        //        {
        //            if ( StringUtils.isEmpty( inLmsForm.getLmsPojo().getState() ) )
        //            {
        //                errStr.append( "State " );
        //            }
        //            if ( StringUtils.isEmpty( inLmsForm.getLmsPojo().getCity() ) )
        //            {
        //                errStr.append( "City " );
        //            }
        //            if ( StringUtils.isEmpty( inLmsForm.getLmsPojo().getArea() ) )
        //            {
        //                errStr.append( "Area " );
        //            }
        //            if ( StringUtils.isEmpty( inLmsForm.getLmsPojo().getLocality() ) )
        //            {
        //                errStr.append( "Locality" );
        //            }
        //        }
        //        else 
        if ( !StringUtils.isEmpty( inLmsForm.getLmsPojo().getLocality() ) )
        {
            if ( StringUtils.isEmpty( inLmsForm.getLmsPojo().getState() ) )
            {
                errStr.append( "State " );
            }
            if ( StringUtils.isEmpty( inLmsForm.getLmsPojo().getCity() ) )
            {
                errStr.append( "City " );
            }
            if ( StringUtils.isEmpty( inLmsForm.getLmsPojo().getArea() ) )
            {
                errStr.append( "Area" );
            }
        }
        else if ( !StringUtils.isEmpty( inLmsForm.getLmsPojo().getArea() ) )
        {
            if ( StringUtils.isEmpty( inLmsForm.getLmsPojo().getState() ) )
            {
                errStr.append( "State " );
            }
            if ( StringUtils.isEmpty( inLmsForm.getLmsPojo().getCity() ) )
            {
                errStr.append( "City" );
            }
        }
        else if ( !StringUtils.isEmpty( inLmsForm.getLmsPojo().getCity() ) )
        {
            if ( StringUtils.isEmpty( inLmsForm.getLmsPojo().getState() ) )
            {
                errStr.append( "State" );
            }
        }
        if ( !StringUtils.isEmpty( errStr.toString() ) )
        {
            inActionError.add( IAppConstants.APP_ERROR, new ActionMessage( IPropertiesConstant.ERROR_LEAD_FEASIBILITY,
                                                                           errStr.toString() ) );
        }
        return inActionError;
    }
}
