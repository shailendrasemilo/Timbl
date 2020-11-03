package com.np.tele.crm.service.comparators;

import java.util.Comparator;

import com.np.tele.crm.service.client.ProjectsPojo;
import com.np.tele.crm.utils.StringUtils;

public class ProjectPojoComparator
    implements Comparator<ProjectsPojo>
{
    @Override
    public int compare( ProjectsPojo inExist, ProjectsPojo inNew )
    {
        if ( !StringUtils.equals( inExist.getProjectDescription(), inNew.getProjectDescription() ) )
        {
            return -1;
        }
        if ( !inExist.getCrmParameterPojosSet().containsAll( inNew.getCrmParameterPojosSet() ) )
        {
            return -1;
        }
        
        return 0;
    }
    
}
