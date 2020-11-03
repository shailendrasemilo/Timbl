package com.np.tele.crm.qrc.dao;

import org.hibernate.Session;

import com.np.tele.crm.constants.CRMServiceCode;
import com.np.tele.crm.dto.CrmQrcDto;
import com.np.tele.crm.pojos.CrmQrcCategoriesPojo;
import com.np.tele.crm.pojos.CrmSrTicketsPojo;
import com.np.tele.crm.pojos.CrmTicketHistoryPojo;

public interface ICrmTicketDao
{
    CRMServiceCode openTicket( CrmQrcDto inCrmQrcDto, CrmTicketHistoryPojo inTicketHistory );

    CRMServiceCode modifyFollowUpTime( CrmQrcDto inCrmQrcDto, CrmTicketHistoryPojo inTicketHistory );

    CRMServiceCode resolveTicket( CrmQrcDto inCrmQrcDto, CrmTicketHistoryPojo inTicketHistory );

    CRMServiceCode closeTicket( CrmSrTicketsPojo inTicket, CrmTicketHistoryPojo inTicketHistory );

    CRMServiceCode forwardTicket( CrmQrcDto inCrmQrcDto, CrmSrTicketsPojo inTicket,
                                  CrmTicketHistoryPojo inTicketHistory,
                                  String inStage,
                                  String inOwner );

    CRMServiceCode saveTicketHistory( CrmTicketHistoryPojo inTicketHistory, Session inSession );

    void preparedQrcTicketPojo( CrmSrTicketsPojo inTicket );

    CrmQrcCategoriesPojo getQrcCategoriesID( String inCategory,
                                             String inSubCategory,
                                             String inSubSubCategory,
                                             String inQrcType );

    CRMServiceCode listTickets( CrmQrcDto inCrmQrcDto );

    CRMServiceCode listTicketHistory( CrmQrcDto inCrmQrcDto );

    CRMServiceCode updateTicket( CrmSrTicketsPojo inTicket );

    CRMServiceCode reopenedTicket( CrmQrcDto inCrmQrcDto, CrmTicketHistoryPojo ticketHistory );
}
