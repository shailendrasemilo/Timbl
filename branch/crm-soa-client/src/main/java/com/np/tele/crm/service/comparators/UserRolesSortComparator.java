package com.np.tele.crm.service.comparators;

import java.util.Comparator;

import com.np.tele.crm.service.client.UserRolesPojo;

public class UserRolesSortComparator
    implements Comparator<UserRolesPojo>
{
    @Override
    public int compare( UserRolesPojo inExist, UserRolesPojo inNew )
    {
        if ( inExist.isEditable() && !inNew.isEditable() )
        {
            return -1;
        }
        if ( !inExist.isEditable() && inNew.isEditable() )
        {
            return 1;
        }
        return 0;
    }
}
