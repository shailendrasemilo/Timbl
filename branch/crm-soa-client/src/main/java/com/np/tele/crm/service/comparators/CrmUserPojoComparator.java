package com.np.tele.crm.service.comparators;

import java.util.Comparator;

import com.np.tele.crm.service.client.CrmUserPojo;
import com.np.tele.crm.utils.StringUtils;

public class CrmUserPojoComparator
    implements Comparator<CrmUserPojo>
{
    @Override
    public int compare( CrmUserPojo inExist, CrmUserPojo inNew )
    {
        if ( !StringUtils.equals( inExist.getEmailId(), inNew.getEmailId() ) )
        {
            return -1;
        }
        if ( inExist.getMobileNo() != inNew.getMobileNo() )
        {
            return -1;
        }
        if ( inExist.getPasswordExpiry() != inNew.getPasswordExpiry() )
        {
            return -1;
        }
        if ( inExist.getUserAccountExpiry() != inNew.getUserAccountExpiry() )
        {
            return -1;
        }
        if ( !StringUtils.equals( inExist.getStatus(), inNew.getStatus() ) )
        {
            return -1;
        }
        if ( !StringUtils.equals( inExist.getFunctionalBin(), inNew.getFunctionalBin() ) )
        {
            return -1;
        }
        if ( !StringUtils.equals( inExist.getSrCode(), inNew.getSrCode() ) )
        {
            return -1;
        }
        return 0;
    }
}
