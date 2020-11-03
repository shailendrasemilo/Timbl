package com.np.tele.crm.pojos;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.xml.datatype.XMLGregorianCalendar;

import org.apache.log4j.Logger;

import com.np.tele.crm.constants.CRMStatusCode;
import com.np.tele.crm.constants.IDateConstants;
import com.np.tele.crm.utils.DateUtils;
import com.np.tele.crm.utils.StringUtils;

public class AllInboxPojo
    implements Comparable<AllInboxPojo>
{
    private static final Logger  LOGGER   = Logger.getLogger( AllInboxPojo.class );
    private String               leadIdCrfIdTicketId;
    private String               stage;
    private String               customerName;
    private long                 contactNumber;
    private String               product;
    private XMLGregorianCalendar date;
    private String               type;
    private long                 lmsIdCrfRecordId;
    private String               displayCreatedTime;
    private boolean              unRead;
    private long                 inboxId;
    private String               previousStage;
    private String               previousStageOwner;
    private String               networkPartner;
    private String               requestType;
    private String               subSubCategory;
    private XMLGregorianCalendar expectedSlaTime;
    private String               color    = "";
    private String               lmsColor = "";
    private String               status;
    private String               feasibility;
    private XMLGregorianCalendar lastModifiedTime;

    public String getNetworkPartner()
    {
        return networkPartner;
    }

    public void setNetworkPartner( String inNetworkPartner )
    {
        networkPartner = inNetworkPartner;
    }

    public String getRequestType()
    {
        return requestType;
    }

    public void setRequestType( String inRequestType )
    {
        requestType = inRequestType;
    }

    public String getSubSubCategory()
    {
        return subSubCategory;
    }

    public void setSubSubCategory( String inSubSubCategory )
    {
        subSubCategory = inSubSubCategory;
    }

    public String getLeadIdCrfIdTicketId()
    {
        return leadIdCrfIdTicketId;
    }

    public void setLeadIdCrfIdTicketId( String inLeadIdCrfIdTicketId )
    {
        leadIdCrfIdTicketId = inLeadIdCrfIdTicketId;
    }

    public String getStage()
    {
        return stage;
    }

    public void setStage( String inStage )
    {
        stage = inStage;
    }

    public String getCustomerName()
    {
        return customerName;
    }

    public void setCustomerName( String inCustomerName )
    {
        customerName = inCustomerName;
    }

    public String getProduct()
    {
        return product;
    }

    public long getContactNumber()
    {
        return contactNumber;
    }

    public void setContactNumber( long inContactNumber )
    {
        contactNumber = inContactNumber;
    }

    public void setProduct( String inProduct )
    {
        product = inProduct;
    }

    public String getType()
    {
        return type;
    }

    public void setType( String inType )
    {
        type = inType;
    }

    public XMLGregorianCalendar getDate()
    {
        return date;
    }

    public void setDate( XMLGregorianCalendar date )
    {
        this.date = date;
        if ( StringUtils.isEmpty( displayCreatedTime ) && StringUtils.isValidObj( date ) )
        {
            final SimpleDateFormat dateFormat = new SimpleDateFormat( "yyyy-MM-dd'T'HH:mm:ss" );
            Calendar cal = Calendar.getInstance();
            try
            {
                cal.setTime( dateFormat.parse( date.toString() ) );
                displayCreatedTime = IDateConstants.SDF_DD_MMM_YYYY_HH_MM_SS.format( cal.getTime() );
            }
            catch ( ParseException ex )
            {
                LOGGER.error( "Error occure while setDate ::", ex );
            }
        }
    }

    public long getLmsIdCrfRecordId()
    {
        return lmsIdCrfRecordId;
    }

    public void setLmsIdCrfRecordId( long inLmsIdCrfRecordId )
    {
        lmsIdCrfRecordId = inLmsIdCrfRecordId;
    }

    public String getDisplayCreatedTime()
    {
        return displayCreatedTime;
    }

    public void setDisplayCreatedTime( String inDisplayCreatedTime )
    {
        displayCreatedTime = inDisplayCreatedTime;
    }

    public boolean isUnRead()
    {
        return unRead;
    }

    public void setUnRead( boolean inUnRead )
    {
        unRead = inUnRead;
    }

    public long getInboxId()
    {
        return inboxId;
    }

    public void setInboxId( long inInboxId )
    {
        inboxId = inInboxId;
    }

    public String getPreviousStage()
    {
        return previousStage;
    }

    public void setPreviousStage( String previousStage )
    {
        this.previousStage = previousStage;
    }

    public String getPreviousStageOwner()
    {
        return previousStageOwner;
    }

    public void setPreviousStageOwner( String previousStageOwner )
    {
        this.previousStageOwner = previousStageOwner;
    }

    public XMLGregorianCalendar getExpectedSlaTime()
    {
        return expectedSlaTime;
    }

    public void setExpectedSlaTime( XMLGregorianCalendar inExpectedSlaTime )
    {
        expectedSlaTime = inExpectedSlaTime;
    }

    public String getStatus()
    {
        return status;
    }

    public void setStatus( String inStatus )
    {
        status = inStatus;
    }

    public String getColor()
    {
        if ( StringUtils.equals( CRMStatusCode.OPEN.getStatusCode(), this.getStatus() )
                || StringUtils.equals( CRMStatusCode.REOPEN.getStatusCode(), this.getStatus() ) )
        {
            if ( StringUtils.isValidObj( expectedSlaTime )
                    && Calendar.getInstance().getTime().after( DateUtils.convertXmlCalToDate( expectedSlaTime ) ) )
            {
                return "color_SLA";
            }
        }
        return "color_" + this.getStatus();
    }

    public String getLmsColor()
    {
        return "color_" + this.feasibility;
    }

    public String getFeasibility()
    {
        return feasibility;
    }

    public void setFeasibility( String inFeasibility )
    {
        feasibility = inFeasibility;
    }

    public XMLGregorianCalendar getLastModifiedTime()
    {
        return lastModifiedTime;
    }

    public void setLastModifiedTime( XMLGregorianCalendar inLastModifiedTime )
    {
        lastModifiedTime = inLastModifiedTime;
    }

    @Override
    public int hashCode()
    {
        final int prime = 31;
        int result = 1;
        result = prime * result + ( ( date == null ) ? 0 : date.hashCode() );
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
        AllInboxPojo other = (AllInboxPojo) obj;
        if ( date == null )
        {
            if ( other.date != null )
                return false;
        }
        else if ( !date.equals( other.date ) )
            return false;
        return true;
    }

    @Override
    public int compareTo( AllInboxPojo inO )
    {
        return this.date.compare( inO.getDate() );
    }
}
