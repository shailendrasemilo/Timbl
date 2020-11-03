package com.np.tele.crm.ext.pojos;

import java.io.Serializable;

public class UserMasterPojo
    implements Serializable
{
    private String category;
    private String subCategory;
    private String alias;
    private String state;
    private String city;
    private String country;
    private String area;
    private String village;
    private String allowed_ip;
    private String login_start_time;
    private String login_end_time;
    private int    lock_attempts;
    private int    expire_attempts;
    private int    lock_duration;
    private long   start_time_id;
    private long   end_time_id;
    private long   allowed_ip_id;
    private long   area_id;
    private long   waiver_limit_id;

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

    public String getState()
    {
        return state;
    }

    public void setState( String inState )
    {
        state = inState;
    }

    public String getCity()
    {
        return city;
    }

    public void setCity( String inCity )
    {
        city = inCity;
    }

    public String getCountry()
    {
        return country;
    }

    public void setCountry( String inCountry )
    {
        country = inCountry;
    }

    public String getArea()
    {
        return area;
    }

    public void setArea( String inArea )
    {
        area = inArea;
    }

    public String getVillage()
    {
        return village;
    }

    public void setVillage( String inVillage )
    {
        village = inVillage;
    }

    public String getAllowed_ip()
    {
        return allowed_ip;
    }

    public void setAllowed_ip( String inAllowed_ip )
    {
        allowed_ip = inAllowed_ip;
    }

    public String getLogin_start_time()
    {
        return login_start_time;
    }

    public void setLogin_start_time( String inLogin_start_time )
    {
        login_start_time = inLogin_start_time;
    }

    public String getLogin_end_time()
    {
        return login_end_time;
    }

    public void setLogin_end_time( String inLogin_end_time )
    {
        login_end_time = inLogin_end_time;
    }

    public int getLock_attempts()
    {
        return lock_attempts;
    }

    public void setLock_attempts( int inLock_attempts )
    {
        lock_attempts = inLock_attempts;
    }

    public int getExpire_attempts()
    {
        return expire_attempts;
    }

    public void setExpire_attempts( int inExpire_attempts )
    {
        expire_attempts = inExpire_attempts;
    }

    public int getLock_duration()
    {
        return lock_duration;
    }

    public void setLock_duration( int inLock_duration )
    {
        lock_duration = inLock_duration;
    }

    public long getStart_time_id()
    {
        return start_time_id;
    }

    public void setStart_time_id( long inStart_time_id )
    {
        start_time_id = inStart_time_id;
    }

    public long getEnd_time_id()
    {
        return end_time_id;
    }

    public void setEnd_time_id( long inEnd_time_id )
    {
        end_time_id = inEnd_time_id;
    }

    public long getAllowed_ip_id()
    {
        return allowed_ip_id;
    }

    public void setAllowed_ip_id( long inAllowed_ip_id )
    {
        allowed_ip_id = inAllowed_ip_id;
    }

    public long getArea_id()
    {
        return area_id;
    }

    public void setArea_id( long area_id )
    {
        this.area_id = area_id;
    }

    public long getWaiver_limit_id()
    {
        return waiver_limit_id;
    }

    public void setWaiver_limit_id( long inWaiver_limit_id )
    {
        waiver_limit_id = inWaiver_limit_id;
    }

    @Override
    public String toString()
    {
        StringBuilder builder = new StringBuilder();
        builder.append( "UserMasterPojo [login_start_time=" ).append( login_start_time ).append( ", login_end_time=" )
                .append( login_end_time ).append( ", lock_attempts=" ).append( lock_attempts )
                .append( ", expire_attempts=" ).append( expire_attempts ).append( ", lock_duration=" )
                .append( lock_duration ).append( ", start_time_id=" ).append( start_time_id ).append( ", end_time_id=" )
                .append( end_time_id ).append( ", allowed_ip_id=" ).append( allowed_ip_id ).append( ", area_id=" )
                .append( area_id ).append( ", waiver_limit_id=" ).append( waiver_limit_id ).append( "]" );
        return builder.toString();
    }
}
