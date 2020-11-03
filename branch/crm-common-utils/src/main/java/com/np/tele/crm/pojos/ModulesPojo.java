package com.np.tele.crm.pojos;

import java.util.List;

public class ModulesPojo
{
    private long         moduleId;
    private String       moduleName;
    private String       moduleDisplay;
    private String       moduleUrl;
    private boolean      selected;
    private String       styleClass;
    private List<String> roles;
    private int          moduleLevel    = 0;
    private int          moduleSize     = 0;
    private long         parentModuleId = 0;

    public ModulesPojo()
    {
    }

    public ModulesPojo( long inModuleId, String inModuleDisplay, String inModuleUrl, boolean inSelected )
    {
        super();
        moduleId = inModuleId;
        moduleDisplay = inModuleDisplay;
        moduleUrl = inModuleUrl;
        selected = inSelected;
    }

    public ModulesPojo( long inModuleId,
                        String inModuleDisplay,
                        String inModuleUrl,
                        boolean inSelected,
                        int inModuleLevel )
    {
        super();
        moduleId = inModuleId;
        moduleDisplay = inModuleDisplay;
        moduleUrl = inModuleUrl;
        selected = inSelected;
        moduleLevel = inModuleLevel;
    }

    public long getModuleId()
    {
        return moduleId;
    }

    public void setModuleId( long inModuleId )
    {
        moduleId = inModuleId;
    }

    public String getModuleName()
    {
        return moduleName;
    }

    public void setModuleName( String inModuleName )
    {
        moduleName = inModuleName;
    }

    public String getModuleDisplay()
    {
        return moduleDisplay;
    }

    public void setModuleDisplay( String inModuleDisplay )
    {
        moduleDisplay = inModuleDisplay;
    }

    public String getModuleUrl()
    {
        return moduleUrl;
    }

    public void setModuleUrl( String inModuleUrl )
    {
        moduleUrl = inModuleUrl;
    }

    public boolean isSelected()
    {
        return selected;
    }

    public void setSelected( boolean inSelected )
    {
        selected = inSelected;
    }

    public String getStyleClass()
    {
        return styleClass;
    }

    public void setStyleClass( String inStyleClass )
    {
        styleClass = inStyleClass;
    }

    public List<String> getRoles()
    {
        return roles;
    }

    public void setRoles( List<String> inRoles )
    {
        roles = inRoles;
    }

    public int getModuleLevel()
    {
        return moduleLevel;
    }

    public void setModuleLevel( int inModuleLevel )
    {
        moduleLevel = inModuleLevel;
    }

    public int getModuleSize()
    {
        return moduleSize;
    }

    public void setModuleSize( int inModuleSize )
    {
        moduleSize = inModuleSize;
    }

    public long getParentModuleId()
    {
        return parentModuleId;
    }

    public void setParentModuleId( long inParentModuleId )
    {
        parentModuleId = inParentModuleId;
    }

    @Override
    public int hashCode()
    {
        final int prime = 31;
        int result = 1;
        result = prime * result + (int) ( moduleId ^ ( moduleId >>> 32 ) );
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
        ModulesPojo other = (ModulesPojo) obj;
        if ( moduleId != other.moduleId )
            return false;
        return true;
    }

    @Override
    public String toString()
    {
        StringBuilder builder = new StringBuilder();
        builder.append( "ModulesPojo [moduleId=" ).append( moduleId ).append( ", moduleName=" ).append( moduleName )
                .append( ", moduleDisplay=" ).append( moduleDisplay ).append( ", moduleUrl=" ).append( moduleUrl )
                .append( ", selected=" ).append( selected ).append( ", styleClass=" ).append( styleClass ).append( "]" );
        return builder.toString();
    }
}
