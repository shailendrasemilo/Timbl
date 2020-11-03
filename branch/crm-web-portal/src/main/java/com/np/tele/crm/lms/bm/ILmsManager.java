package com.np.tele.crm.lms.bm;

import java.util.List;

import com.np.tele.crm.lms.form.LmsForm;
import com.np.tele.crm.pojos.ContentPojo;
import com.np.tele.crm.service.client.ConfigDto;
import com.np.tele.crm.service.client.CrmQrcDto;
import com.np.tele.crm.service.client.CrmSrTicketsPojo;
import com.np.tele.crm.service.client.LmsDto;
import com.np.tele.crm.service.client.LmsPojo;
import com.np.tele.crm.service.client.RemarksPojo;

public interface ILmsManager
{
    LmsDto leadOperations( LmsForm inLmsForm, String inParameter );

    List<String> processLMSExcel( LmsForm inLmsForm, String inUser, String inFilePath );

    ConfigDto getAppointments( ConfigDto inConfigDto );

    ConfigDto getRemarks( ConfigDto inConfigDto );

    ConfigDto getBPs( ConfigDto inConfigDto );

    ConfigDto getReferralSources();

    //int getAppointmentsCount( LmsForm inLmsForm );
    List<ContentPojo> getActionsList( LmsForm inLmsForm );

    boolean getBackRouteSCValidation( List<RemarksPojo> inRemarksPojoList );

    ConfigDto getLeadCloseReason();

    ConfigDto getActivityLogs( ConfigDto inConfigDto );

    ConfigDto getCustomerRefuselReason();

    boolean checkCrfMapping( String inCrfId );

    List<String> getUsersByParameter( String inParam, String inValue, String inFunctionalBin );

    boolean checkCrfMappingINA( String inCrfId );

    LmsPojo getSocietyByPinCode( int pinCode );

    CrmQrcDto logLMSTicket( CrmQrcDto inQrcDto );

    CrmQrcDto getLMSTickets( CrmQrcDto inQrcDto );

    void setCategoriesNameById( List<CrmSrTicketsPojo> list );
}
