package com.np.tele.crm.usrmngmnt.bm;

import com.np.tele.crm.service.client.CrmuserDetailsDto;
import com.np.tele.crm.usrmngmnt.forms.EWUserMappingForm;

public interface IEWUserMappingMgr
{
    CrmuserDetailsDto searchUserById( EWUserMappingForm inMappingForm );

    CrmuserDetailsDto searchEWUserMapping( EWUserMappingForm inMappingForm );

    CrmuserDetailsDto createEWUserMapping( EWUserMappingForm inMappingForm, String inString );
}
