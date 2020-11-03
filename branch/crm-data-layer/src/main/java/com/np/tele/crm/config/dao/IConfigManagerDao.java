package com.np.tele.crm.config.dao;

import java.util.List;

import org.hibernate.Session;

import com.np.tele.crm.constants.CRMStatusCode;
import com.np.tele.crm.dto.ConfigDto;
import com.np.tele.crm.pojos.AppointmentPojo;
import com.np.tele.crm.pojos.CrmInboxPojo;
import com.np.tele.crm.pojos.CrmMasterPojo;
import com.np.tele.crm.pojos.CrmRcaReasonPojo;
import com.np.tele.crm.pojos.EventsPojo;
import com.np.tele.crm.pojos.RemarksPojo;

public interface IConfigManagerDao
{
    List<CrmMasterPojo> getConfiguration( String inConfigType, String inConfigSubType );

    List<CrmRcaReasonPojo> getRCAReasonList( String inConfigType, String inConfigSubType );

    EventsPojo getEventsPojoByName( String inEventName );

    List<CrmMasterPojo> getMasterConfiguration( String inAlias, CRMStatusCode inCRMStatusCode );

    List<AppointmentPojo> getMappingIdtByAppointMents( String inMappingId );

    List<RemarksPojo> geRemarksByMappingId( String inMappingId );

    //    boolean changeInboxBin( String inMappingId,
    //                            String inRequesType,
    //                            String inFromUserId,
    //                            String inToUserId,
    //                            String inFromStage,
    //                            String inToStage,
    //                            String inUserId,
    //                            long inPartnerId );
    boolean changeInboxBin( ConfigDto inConfigDto, Session inSession );

    boolean saveAppointment( AppointmentPojo inAppointmentPojo );

    ConfigDto getMappingUsers( ConfigDto inConfigDto );

    ConfigDto insertAuditLog( ConfigDto inConfigDto );

    ConfigDto searchAuditLog( ConfigDto inConfigDto );

    ConfigDto getCustomerDetails( ConfigDto inConfigDto );

    ConfigDto getEvents( ConfigDto inConfigDto );

    ConfigDto insertCustomerActivity( ConfigDto inConfigDto );

    void searchCustomerActivity( ConfigDto inConfigDto );

    ConfigDto getInbox( ConfigDto inConfigDto );

    List<CrmInboxPojo> getPartnerIdFromInbox( ConfigDto inConfigDto );

    boolean changeInboxBin( ConfigDto inConfigDto );

    CrmMasterPojo getCMSRecipient( String inSubSubCategory );

    ConfigDto getPaymentCenters( ConfigDto inConfigDto );

    ConfigDto listUploadedFiles( ConfigDto inConfigDto );
}
