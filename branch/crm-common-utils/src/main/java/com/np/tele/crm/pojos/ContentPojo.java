package com.np.tele.crm.pojos;

public class ContentPojo
{
    private String  contentName;
    private String  contentValue;
    private String  moduleName;
    private boolean selected;

    public ContentPojo()
    {
    }

    public ContentPojo( String contentName, String contentValue )
    {
        this.contentName = contentName;
        this.contentValue = contentValue;
    }

    public ContentPojo( String contentName, String contentValue, String moduleName )
    {
        this.contentName = contentName;
        this.contentValue = contentValue;
        this.moduleName = moduleName;
    }

    public String getContentName()
    {
        return contentName;
    }

    public void setContentName( String inContentName )
    {
        contentName = inContentName;
    }

    public String getContentValue()
    {
        return contentValue;
    }

    public void setContentValue( String inContentValue )
    {
        contentValue = inContentValue;
    }

    public boolean isSelected()
    {
        return selected;
    }

    public void setSelected( boolean inSelected )
    {
        selected = inSelected;
    }

    public String getModuleName()
    {
        return moduleName;
    }

    public void setModuleName( String inModuleName )
    {
        moduleName = inModuleName;
    }

    @Override
    public int hashCode()
    {
        final int prime = 31;
        int result = 1;
        result = prime * result + ( ( contentValue == null ) ? 0 : contentValue.hashCode() );
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
        ContentPojo other = (ContentPojo) obj;
        if ( contentValue == null )
        {
            if ( other.contentValue != null )
                return false;
        }
        else if ( !contentValue.equals( other.contentValue ) )
            return false;
        return true;
    }

    @Override
    public String toString()
    {
        StringBuilder builder = new StringBuilder();
        builder.append( "ContentPojo [contentName=" ).append( contentName ).append( ", contentValue=" )
                .append( contentValue ).append( ", selected=" ).append( selected ).append( "]" );
        return builder.toString();
    }
}
