package com.np.tele.crm.usrmngmnt.bm;

import com.np.tele.crm.service.client.CrmCapDto;
import com.np.tele.crm.service.client.CrmQrcDto;
import com.np.tele.crm.service.client.LmsDto;

public interface ICustomerProfileMgr
{
    CrmCapDto CRFCustomerProfilesearch( String inProfileSearchName, String inSearchValue );

    LmsDto leadCustomerProfileSearch( String inProfileSearchName, String inSearchValue );

    CrmQrcDto ticketIDprofileSearch( String inProfileSearchName, String inSearchValue, CrmQrcDto inCrmQrcDto );
}
