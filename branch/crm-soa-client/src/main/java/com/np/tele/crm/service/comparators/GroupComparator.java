package com.np.tele.crm.service.comparators;

import java.util.Comparator;

import com.np.tele.crm.service.client.GroupsPojo;
import com.np.tele.crm.utils.StringUtils;

public class GroupComparator
    implements Comparator<GroupsPojo>
{
    @Override
    public int compare( GroupsPojo inExist, GroupsPojo inNew )
    {
        if ( !StringUtils.equals( inExist.getGroupDescription(), inNew.getGroupDescription() ) )
        {
            return -1;
        }
        if ( !inExist.getAccessGroups().containsAll( inNew.getAccessGroups() ) )
        {
            return -1;
        }
        return 0;
    }
}
