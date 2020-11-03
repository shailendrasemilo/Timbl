package com.np.tele.crm.lms.dao;

import com.np.tele.crm.dto.LMSDto;

public interface ILMSOperationDao
{
    LMSDto createLead( LMSDto inLMSDto );

    LMSDto saveLead( LMSDto inLMSDto );

    LMSDto forwardLead( LMSDto inLMSDto );

    LMSDto changeLeadStatus( LMSDto inLMSDto, String inOperationParam );

    String getUserIdByAreaAndStage( String inStage, String inArea, long inPartnerId );

    //String getAMByArea( List<String> inUserIDs, String inArea );
    LMSDto leadCustomerProfileSearch( LMSDto inLMSDto );

    boolean checkDuplicateCRFId( LMSDto inLMSDto );

    LMSDto searchSocietyByPinCode( LMSDto inLMSDto );

    LMSDto getAreaNameByAreaId( LMSDto inLMSDto );
}
