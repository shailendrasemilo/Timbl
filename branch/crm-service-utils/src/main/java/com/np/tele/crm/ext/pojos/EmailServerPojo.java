package com.np.tele.crm.ext.pojos;

public class EmailServerPojo
    implements java.io.Serializable
{
    private String  category;
    private String  subCategory;
    private String  alias;
    private String  host;
    private int     port;
    private String  userid;
    private String  password;
    private boolean auth;
    private boolean enable;
    private int     connection_time_out;
    private int     socket_time_out;
    private String  from;
    private String  display;
    private String  replyTo;
    private int     retryValue;

    public int getRetryValue()
    {
        return retryValue;
    }

    public void setRetryValue( int inRetryValue )
    {
        retryValue = inRetryValue;
    }

    public String getCategory()
    {
        return category;
    }

    public void setCategory( String inCategory )
    {
        category = inCategory;
    }

    public String getSubCategory()
    {
        return subCategory;
    }

    public void setSubCategory( String inSubCategory )
    {
        subCategory = inSubCategory;
    }

    public String getAlias()
    {
        return alias;
    }

    public void setAlias( String inAlias )
    {
        alias = inAlias;
    }

    public String getHost()
    {
        return host;
    }

    public void setHost( String inHost )
    {
        host = inHost;
    }

    public int getPort()
    {
        return port;
    }

    public void setPort( int inPort )
    {
        port = inPort;
    }

    public String getUserid()
    {
        return userid;
    }

    public void setUserid( String inUserid )
    {
        userid = inUserid;
    }

    public String getPassword()
    {
        return password;
    }

    public void setPassword( String inPassword )
    {
        password = inPassword;
    }

    public boolean isAuth()
    {
        return auth;
    }

    public void setAuth( boolean inAuth )
    {
        auth = inAuth;
    }

    public boolean isEnable()
    {
        return enable;
    }

    public void setEnable( boolean inEnable )
    {
        enable = inEnable;
    }

    public int getConnection_time_out()
    {
        return connection_time_out;
    }

    public void setConnection_time_out( int inConnection_time_out )
    {
        connection_time_out = inConnection_time_out;
    }

    public int getSocket_time_out()
    {
        return socket_time_out;
    }

    public void setSocket_time_out( int inSocket_time_out )
    {
        socket_time_out = inSocket_time_out;
    }

    public String getFrom()
    {
        return from;
    }

    public void setFrom( String inFrom )
    {
        from = inFrom;
    }

    public String getDisplay()
    {
        return display;
    }

    public void setDisplay( String inDisplay )
    {
        display = inDisplay;
    }

    public String getReplyTo()
    {
        return replyTo;
    }

    public void setReplyTo( String inReplyTo )
    {
        replyTo = inReplyTo;
    }

    @Override
    public int hashCode()
    {
        final int prime = 31;
        int result = 1;
        result = prime * result + ( ( alias == null ) ? 0 : alias.hashCode() );
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
        EmailServerPojo other = (EmailServerPojo) obj;
        if ( alias == null )
        {
            if ( other.alias != null )
                return false;
        }
        else if ( !alias.equals( other.alias ) )
            return false;
        return true;
    }

    @Override
    public String toString()
    {
        StringBuilder builder = new StringBuilder();
        builder.append( "EmailServerPojo [category=" ).append( category ).append( ", subCategory=" )
                .append( subCategory ).append( ", alias=" ).append( alias ).append( ", host=" ).append( host )
                .append( ", port=" ).append( port ).append( ", userid=" ).append( userid ).append( ", password=" )
                .append( password ).append( ", auth=" ).append( auth ).append( ", enable=" ).append( enable )
                .append( ", connection_time_out=" ).append( connection_time_out ).append( ", socket_time_out=" )
                .append( socket_time_out ).append( ", from=" ).append( from ).append( ", display=" ).append( display )
                .append( ", replyTo=" ).append( replyTo ).append( "]" );
        return builder.toString();
    }
}
