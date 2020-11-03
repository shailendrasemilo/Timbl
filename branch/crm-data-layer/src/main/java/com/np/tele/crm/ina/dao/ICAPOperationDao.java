package com.np.tele.crm.ina.dao;

import java.lang.reflect.InvocationTargetException;
import java.util.Map;

import org.hibernate.Session;

import com.np.tele.crm.constants.CRMServiceCode;
import com.np.tele.crm.dto.CrmCapDto;
import com.np.tele.crm.exceptions.DuplicateRecieptException;

public interface ICAPOperationDao
{
    CrmCapDto getCRFDetails( CrmCapDto inCapDto, long inCustomerRecordId, boolean toDisplay );

    CrmCapDto saveCRF( CrmCapDto inCapDto, boolean inToSave );

    CrmCapDto forwardCRF( CrmCapDto inCapDto );

    CrmCapDto saveCustomerInformation( CrmCapDto inCapDto, Session inSession, Map<String, Long> inEvicts );

    CrmCapDto generateInitiateInbox( CrmCapDto inCapDto, Session inSession );

    //    CrmCapDto saveValidatedCrf( CrmCapDto inCrmCapDto );
    CrmCapDto saveCRForISRReferenceNo( CrmCapDto inCrmCapDto );

    //    CrmCapDto saveNetworkConfiguration( CrmCapDto inCrmCapDto );
    CrmCapDto searchCustomerProfile( CrmCapDto inCrmCapDto );

    CrmCapDto saveCustomerProfileDetails( CrmCapDto inCrmCapDto );

    CrmCapDto getCustomerDetails( CrmCapDto inCapDto );

    CrmCapDto getCustomerDetails( long inCustomerRecordId, String inCRFId, String inCustomerId );

    boolean updateToken( String inCustomerId, String inUser );

    CRMServiceCode validateEmailToken( String inToken );

    boolean genricPaymentPosting( CrmCapDto inCapDto, Session session, long customerRecordId, Map<String, Long> inEvicts )
        throws IllegalAccessException, InvocationTargetException, NoSuchMethodException, DuplicateRecieptException;

    void saveNetworkConfigurations( CrmCapDto inCapDto, Session inSession, Map<String, Long> inEvicts );

    CrmCapDto viewCRFDetails( long recordId, String crfId, String inCustomerId );

    CrmCapDto searchCustomerBySociety( CrmCapDto inCapDto );
    
    CrmCapDto searchCustomerByMacAddress(CrmCapDto inCapDto);

}
