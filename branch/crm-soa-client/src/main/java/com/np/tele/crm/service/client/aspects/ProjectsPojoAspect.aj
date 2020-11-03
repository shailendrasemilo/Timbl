package com.np.tele.crm.service.client.aspects;

import com.np.tele.crm.service.client.ProjectsPojo;

public aspect ProjectsPojoAspect
{
    public int ProjectsPojo.hashCode()
    {
        final int prime = 31;
        int result = 1;
        result = prime * result + (int) ( this.getProjectId() ^ ( this.getProjectId() >>> 32 ) );
        return result;
    }

    public boolean ProjectsPojo.equals( Object obj )
    {
        if ( this == obj )
        {
            return true;
        }
        if ( obj == null )
        {
            return false;
        }
        if ( getClass() != obj.getClass() )
        {
            return false;
        }
        ProjectsPojo other = (ProjectsPojo) obj;
        if ( this.getProjectId() != other.getProjectId() )
        {
            return false;
        }
        return true;
    }
}
