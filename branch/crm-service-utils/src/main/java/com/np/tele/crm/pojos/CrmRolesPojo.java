package com.np.tele.crm.pojos;

// Generated 5 Jul, 2013 11:15:37 AM by Hibernate Tools 3.4.0.CR1
import java.util.Date;
import java.util.Set;

/**
 * CrmRoles generated by hbm2java
 */
public class CrmRolesPojo
    implements java.io.Serializable
{
    private long   roleId;
    private String roleName;
    private String roleDescription;
    private Short  hierarchy;
    private String createdBy;
    private Date   createdTime;
    private String lastModifiedBy;
    private Date   lastModifiedTime;
    private String status;
    private String displayCreatedTime;
    private String displayLastModifiedTime;
    private String userRoleDescription;

    /*private Set    userRoleses  = new HashSet( 0 );
    private Set    accessGroups = new HashSet( 0 );*/
    public CrmRolesPojo()
    {
    }

    public CrmRolesPojo( Long roleId, String roleName, String createdBy, String lastModifiedBy, Date lastModifiedTime )
    {
        this.roleId = roleId;
        this.roleName = roleName;
        this.createdBy = createdBy;
        this.lastModifiedBy = lastModifiedBy;
        this.lastModifiedTime = lastModifiedTime;
    }

    public CrmRolesPojo( Long roleId,
                         String roleName,
                         String roleDescription,
                         Short hierarchy,
                         String createdBy,
                         Date createdTime,
                         String lastModifiedBy,
                         Date lastModifiedTime,
                         String status,
                         Set userRoleses,
                         Set accessGroups )
    {
        this.roleId = roleId;
        this.roleName = roleName;
        this.roleDescription = roleDescription;
        this.hierarchy = hierarchy;
        this.createdBy = createdBy;
        this.createdTime = createdTime;
        this.lastModifiedBy = lastModifiedBy;
        this.lastModifiedTime = lastModifiedTime;
        this.status = status;
        /*this.userRoleses = userRoleses;
        this.accessGroups = accessGroups;*/
    }

    public long getRoleId()
    {
        return roleId;
    }

    public void setRoleId( long roleId )
    {
        this.roleId = roleId;
    }

    public Short getHierarchy()
    {
        return hierarchy;
    }

    public void setHierarchy( Short hierarchy )
    {
        this.hierarchy = hierarchy;
    }

    public String getRoleName()
    {
        return this.roleName;
    }

    public void setRoleName( String roleName )
    {
        this.roleName = roleName;
    }

    public String getRoleDescription()
    {
        return this.roleDescription;
    }

    public void setRoleDescription( String roleDescription )
    {
        this.roleDescription = roleDescription;
    }

    public String getCreatedBy()
    {
        return this.createdBy;
    }

    public void setCreatedBy( String createdBy )
    {
        this.createdBy = createdBy;
    }

    public Date getCreatedTime()
    {
        return this.createdTime;
    }

    public void setCreatedTime( Date inCreatedTime )
    {
        createdTime = inCreatedTime;
        /*if ( StringUtils.isEmpty( displayCreatedTime ) && StringUtils.isValidObj( createdTime ) )
        {
            displayCreatedTime = IDateConstants.SDF_DD_MMM_YYYY_HH_MM_SS.format( createdTime );
        }*/
    }

    public String getLastModifiedBy()
    {
        return this.lastModifiedBy;
    }

    public void setLastModifiedBy( String lastModifiedBy )
    {
        this.lastModifiedBy = lastModifiedBy;
    }

    public Date getLastModifiedTime()
    {
        return this.lastModifiedTime;
    }

    public void setLastModifiedTime( Date inLastModifiedTime )
    {
        lastModifiedTime = inLastModifiedTime;
       /* if ( StringUtils.isEmpty( displayLastModifiedTime ) && StringUtils.isValidObj( lastModifiedTime ) )
        {
            displayLastModifiedTime = IDateConstants.SDF_DD_MMM_YYYY_HH_MM_SS.format( lastModifiedTime );
        }*/
    }

    public String getStatus()
    {
        return this.status;
    }

    public void setStatus( String status )
    {
        this.status = status;
    }

    public String getDisplayCreatedTime()
    {
        return displayCreatedTime;
    }

    public void setDisplayCreatedTime( String inDisplayCreatedTime )
    {
        displayCreatedTime = inDisplayCreatedTime;
    }

    public String getDisplayLastModifiedTime()
    {
        return displayLastModifiedTime;
    }

    public void setDisplayLastModifiedTime( String inDisplayLastModifiedTime )
    {
        displayLastModifiedTime = inDisplayLastModifiedTime;
    }

    public String getUserRoleDescription()
    {
        return userRoleDescription;
    }

    public void setUserRoleDescription( String inUserRoleDescription )
    {
        userRoleDescription = inUserRoleDescription;
    }

    @Override
    public int hashCode()
    {
        final int prime = 31;
        int result = 1;
        result = prime * result + (int) ( roleId ^ ( roleId >>> 32 ) );
        return result;
    }

    @Override
    public boolean equals( Object obj )
    {
        if ( this == obj )
            return true;
        if ( obj == null )
            return false;
        if ( getClass() != obj.getClass() )
            return false;
        CrmRolesPojo other = (CrmRolesPojo) obj;
        if ( roleId != other.roleId )
            return false;
        return true;
    }

    @Override
    public String toString()
    {
        StringBuilder builder = new StringBuilder();
        builder.append( "CrmRolesPojo [roleId=" ).append( roleId ).append( ", roleName=" ).append( roleName )
                .append( ", roleDescription=" ).append( roleDescription ).append( ", hierarchy=" ).append( hierarchy )
                .append( ", createdBy=" ).append( createdBy ).append( ", createdTime=" ).append( createdTime )
                .append( ", lastModifiedBy=" ).append( lastModifiedBy ).append( ", lastModifiedTime=" )
                .append( lastModifiedTime ).append( ", status=" ).append( status ).append( "]" );
        return builder.toString();
    }
}
