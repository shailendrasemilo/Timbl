package com.np.tele.crm.alert.dao;

import java.util.List;
import java.util.Map;
import java.util.Set;

import com.np.tele.crm.dto.AlertsDto;
import com.np.tele.crm.pojos.AlertStatusPojo;
import com.np.tele.crm.pojos.EmailTemplatePojo;
import com.np.tele.crm.pojos.EventTemplatesPojo;
import com.np.tele.crm.pojos.SmsTemplatePojo;

public interface IAlertTemplateDao
{
    boolean createSMSTemplate( SmsTemplatePojo inInsmsTemplatePojo );

    List<SmsTemplatePojo> listSMSTemplate( SmsTemplatePojo inInsmsTemplatePojo );

    boolean updateSMSTemplateByArchive( SmsTemplatePojo inInsmsTemplatePojo );

    boolean createEmailTemplate( EmailTemplatePojo inEmailTemplatePojo );

    List<EmailTemplatePojo> listEmailTemplate( EmailTemplatePojo inEmailTemplatePojo );

    boolean updateEmailTemplateByArchive( EmailTemplatePojo inEmailTemplatePojo );

    AlertsDto getEventTemplatesList( AlertsDto inAlertsDto );

    AlertsDto getSmsTemplateList( AlertsDto inAlertsDto );

    AlertsDto getEmailTemplateList( AlertsDto inAlertsDto );

    EventTemplatesPojo getEventTemplate( long inEventId );

    Map<String, String> getDataForParameters( Set<String> inEmailList );

    AlertsDto createTemplateMapping( AlertsDto inAlertsDto );

    AlertsDto viewEvTemplateMapping( AlertsDto inAlertsDto );

    Map<String, String> getParameterByUserId( String inUseriD );

    Map<String, String> getParameterByCustomerId( String inUseriD );

    Map<String, String> getParameterByLeadId( String inLeadId );

    List<AlertStatusPojo> getAlertStatusPojos( int inMaxRetry );

    boolean saveAlertStatus( AlertStatusPojo inAlertStatusPojo );

    Map<String, String> getParameterByPartnerId( String inPartnerId );

    Map<String, String> getParameterByCrfNumber( String inCrfId );

    Map<String, String> getParameterBySrNo( String inSrNo );

    Map<String, String> getParameterByParamValue( String inParamName, String inParamValue );

    Map<String, String> getParameterByPaymentId( String inPaymentId );

    Map<String, String> getFileStatusByFileId( String inFileID );

    List<Map<String, String>> getFileRecordsStatusByFileId( String inFileID );

    void getEmailAttachment( AlertStatusPojo inAlertStatusPojo );

    Map<String, String> getParameterByLMSSrNo( String inSrNo );

    Map<String, String> getParameterByOutageId( String mappingId );

    Map<String, String> getNetworkParameterByCustomerId( String mappingId );
}
