package com.np.tele.crm.masterdata.forms;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

import com.np.tele.crm.constants.CRMServiceCode;
import com.np.tele.crm.constants.IAppConstants;
import com.np.tele.crm.constants.IPropertiesConstant;
import com.np.tele.crm.pojos.ContentPojo;
import com.np.tele.crm.service.client.CrmHolidayDetails;
import com.np.tele.crm.service.client.CrmRcaReasonPojo;
import com.np.tele.crm.service.client.PartnerPojo;
import com.np.tele.crm.utils.StringUtils;

public class CrmRcaReasonForm
    extends ActionForm
{
    private CrmRcaReasonPojo                                  crmRcaReason;
    private List<ContentPojo>                                 categoryList;
    private List<ContentPojo>                                 subCategoryList;
    private List<ContentPojo>                                 subSubCategoryList;
    private List<CrmRcaReasonPojo>                            crmRcaReasonsList;
    private List<PartnerPojo>                                 partnerList;
    private int                                               rowCounter;
    private String                                            prefix;
    private long                                              startRange;
    private long                                              endRange;
    private String                                            requestPage;
    private List<ContentPojo>                                 productList;
    private String                                            startRangeString;
    private String                                            endRangeString;
    private String                                            status;
    private String                                            returnButton;
    private Map<String, List<Map<String, List<ContentPojo>>>> subSubCategoryDetails;
    private List<ContentPojo>                                 entityTypes;
    private List<ContentPojo>                                 aciveInactiveStatusList;
    private String                                            usedUnusedStatus;
    protected CrmHolidayDetails                               crmHolidayPojo;
    private String                                            date;
    protected List<CrmHolidayDetails>                         crmPastHolidayPojos;
    protected List<CrmHolidayDetails>                         crmFutureHolidayPojos;

    public String getReturnButton()
    {
        return returnButton;
    }

    public void setReturnButton( String returnButton )
    {
        this.returnButton = returnButton;
    }

    public String getStartRangeString()
    {
        return startRangeString;
    }

    public void setStartRangeString( String startRangeString )
    {
        this.startRangeString = startRangeString;
    }

    public String getEndRangeString()
    {
        return endRangeString;
    }

    public void setEndRangeString( String endRangeString )
    {
        this.endRangeString = endRangeString;
    }

    public List<ContentPojo> getProductList()
    {
        return productList;
    }

    public void setProductList( List<ContentPojo> productList )
    {
        this.productList = productList;
    }

    public String getRequestPage()
    {
        return requestPage;
    }

    public void setRequestPage( String requestPage )
    {
        this.requestPage = requestPage;
    }

    public List<PartnerPojo> getPartnerList()
    {
        return partnerList;
    }

    public void setPartnerList( List<PartnerPojo> partnerList )
    {
        this.partnerList = partnerList;
    }

    public CrmRcaReasonPojo getCrmRcaReason()
    {
        return crmRcaReason;
    }

    public void setCrmRcaReason( CrmRcaReasonPojo crmRcaReason )
    {
        this.crmRcaReason = crmRcaReason;
    }

    public List<ContentPojo> getCategoryList()
    {
        return categoryList;
    }

    public void setCategoryList( List<ContentPojo> inCategoryList )
    {
        categoryList = inCategoryList;
    }

    public List<ContentPojo> getSubCategoryList()
    {
        return subCategoryList;
    }

    public void setSubCategoryList( List<ContentPojo> inSubCategoryList )
    {
        subCategoryList = inSubCategoryList;
    }

    public List<ContentPojo> getSubSubCategoryList()
    {
        return subSubCategoryList;
    }

    public void setSubSubCategoryList( List<ContentPojo> inSubSubCategoryList )
    {
        subSubCategoryList = inSubSubCategoryList;
    }

    public List<CrmRcaReasonPojo> getCrmRcaReasonsList()
    {
        return crmRcaReasonsList;
    }

    public void setCrmRcaReasonsList( List<CrmRcaReasonPojo> inCrmRcaReasonsList )
    {
        crmRcaReasonsList = inCrmRcaReasonsList;
    }

    public int getRowCounter()
    {
        return rowCounter;
    }

    public void setRowCounter( int inRowCounter )
    {
        rowCounter = inRowCounter;
    }

    public String getStatus()
    {
        return status;
    }

    public void setStatus( String status )
    {
        this.status = status;
    }

    @Override
    public void reset( ActionMapping inMapping, HttpServletRequest inRequest )
    {
        String methodName = inRequest.getParameter( IAppConstants.PARAMETER_NAME );
        if ( StringUtils.equals( IAppConstants.METHOD_CREATE_CATEGORY_VALUE, methodName )
                || StringUtils.equals( IAppConstants.METHOD_ADD_RCA_REASON_ROW, methodName ) )
        {
            if ( StringUtils.isValidObj( getCrmRcaReasonsList() ) && !getCrmRcaReasonsList().isEmpty() )
            {
                for ( CrmRcaReasonPojo crmRcaReasonPojo : getCrmRcaReasonsList() )
                {
                    crmRcaReasonPojo.setEditable( false );
                }
            }
        }
        else if ( StringUtils.equals( IAppConstants.METHOD_CREATE_RCA_REASON_PAGE, methodName ) )
        {
            this.subCategoryList = new ArrayList<ContentPojo>();
            this.subSubCategoryList = new ArrayList<ContentPojo>();
            this.crmRcaReasonsList = new ArrayList<CrmRcaReasonPojo>();
        }
        else if ( StringUtils.equals( IAppConstants.METHOD_SEARCH_ADDRECIEPT_CRF_PAGE, methodName ) )
        {
            this.prefix = "";
            this.usedUnusedStatus = "";
            this.status = "";
        }
        else if ( StringUtils.equals( IAppConstants.METHOD_CREATE_RCA_REASON_PAGE, methodName ) )
        {
            if ( StringUtils.isValidObj( getCrmRcaReasonsList() ) && StringUtils.isValidObj( getCrmRcaReason() ) )
            {
                getCrmRcaReasonsList().clear();
            }
        }
        else if ( ( StringUtils.equals( IAppConstants.METHOD_ADD_RECIEPT, methodName ) )
                || ( StringUtils.equals( IAppConstants.METHOD_ADD_RECIEPT_PAGE, methodName ) )
                || ( StringUtils.equals( IAppConstants.METHOD_VIEW_ADD_RECIEPT_PAGE, methodName ) || ( StringUtils
                        .equals( IAppConstants.METHOD_SEARCH_ADDRECIEPT_CRF_PAGE, methodName ) ) ) )
        {
            this.startRangeString = "";
            this.endRangeString = "";
            this.startRange = 0;
            this.endRange = 0;
            this.returnButton = "";
            this.prefix = "";
            if ( ( StringUtils.equals( IAppConstants.METHOD_ADD_RECIEPT_PAGE, methodName ) )
                    || ( StringUtils.equals( IAppConstants.METHOD_VIEW_ADD_RECIEPT_PAGE, methodName ) ) )
            {
                this.crmRcaReasonsList = null;
            }
        }
        else if ( StringUtils.equals( "searchCategoryValues", methodName ) )
        {
            setStartRangeString( null );
            setEndRangeString( null );
            setPrefix( null );
            setStatus( null );
            setUsedUnusedStatus( null );
            setCrmRcaReason( new CrmRcaReasonPojo() );
        }
    }

    @Override
    public ActionErrors validate( ActionMapping inMapping, HttpServletRequest inRequest )
    {
        String method = inRequest.getParameter( IAppConstants.PARAMETER_NAME );
        ActionErrors actionError = new ActionErrors();
        if ( StringUtils.equals( IAppConstants.METHOD_ADD_RCA_REASON_ROW, method ) )
        {
            if ( StringUtils.isValidObj( getCrmRcaReason() ) )
            {
                if ( StringUtils.isEmpty( getCrmRcaReason().getCategory() ) )
                {
                    actionError.add( IAppConstants.APP_ERROR,
                                     new ActionMessage( IPropertiesConstant.ERROR_CATEGORY_REQUIRED ) );
                }
                else if ( StringUtils.isEmpty( getCrmRcaReason().getSubCategory() ) )
                {
                    actionError.add( IAppConstants.APP_ERROR,
                                     new ActionMessage( IPropertiesConstant.ERROR_SUBCATEGORY_REQUIRED ) );
                }
                else if ( StringUtils.isEmpty( getCrmRcaReason().getSubSubCategory() ) )
                {
                    actionError.add( IAppConstants.APP_ERROR,
                                     new ActionMessage( IPropertiesConstant.ERROR_SUBSUBCATEGORY_REQUIRED ) );
                }
            }
        }
        else if ( StringUtils.equals( IAppConstants.METHOD_CREATE_CATEGORY_VALUE, method ) )
        {
            if ( StringUtils.isValidObj( getCrmRcaReasonsList() ) )
            {
                Set<String> crmRcaReasonPojosSet = new HashSet<String>();
                for ( CrmRcaReasonPojo crmRcaReasonPojo : getCrmRcaReasonsList() )
                {
                    if ( StringUtils.isEmpty( crmRcaReasonPojo.getCategoryValue() ) )
                    {
                        actionError.add( IAppConstants.APP_ERROR,
                                         new ActionMessage( IPropertiesConstant.ERROR_CATEGORY_VALUE_REQUIRED ) );
                        break;
                    }
                    else if ( !crmRcaReasonPojosSet.add( crmRcaReasonPojo.getCategoryValue() ) )
                    {
                        actionError.add( IAppConstants.APP_ERROR,
                                         new ActionMessage( CRMServiceCode.CRM070.getStatusCode(), crmRcaReasonPojo
                                                 .getCategoryValue() ) );
                        break;
                    }
                }
            }
        }
        else if ( StringUtils.equals( IAppConstants.METHOD_ADD_RECIEPT, method ) )
        {/*
            if ( ( this.startRange == 0 ) || ( this.endRange == 0 ) )
            {
                actionError
                        .add( IAppConstants.APP_ERROR, new ActionMessage( IPropertiesConstant.ERROR_RANGE_REQUIRED ) );
            }
            else if ( ( this.startRange > 0 ) && ( this.prefix.equals( "EA" ) ) )
            {
                if ( String.valueOf( this.startRange ).length() < 4 )
                {
                    actionError.add( IAppConstants.APP_ERROR,
                                     new ActionMessage( IPropertiesConstant.ERROR_RANGE_PATTERN, this.startRange, 4 ) );
                }
            }
            else if ( ( this.endRange > 0 ) && ( this.prefix.equals( "EA" ) ) )
            {
                if ( String.valueOf( this.endRange ).length() < 4 )
                {
                    actionError.add( IAppConstants.APP_ERROR,
                                     new ActionMessage( IPropertiesConstant.ERROR_RANGE_PATTERN, this.endRange, 4 ) );
                }
            }
            else if ( ( this.startRange > 0 ) && ( this.prefix.equals( "RA" ) ) )
            {
                if ( String.valueOf( this.startRange ).length() < 6 )
                {
                    actionError.add( IAppConstants.APP_ERROR,
                                     new ActionMessage( IPropertiesConstant.ERROR_RANGE_PATTERN, this.startRange, 6 ) );
                }
            }
            else if ( ( this.endRange > 0 ) && ( this.prefix.equals( "RA" ) ) )
            {
                if ( String.valueOf( this.endRange ).length() < 6 )
                {
                    actionError.add( IAppConstants.APP_ERROR,
                                     new ActionMessage( IPropertiesConstant.ERROR_RANGE_PATTERN, this.endRange, 6 ) );
                }
            }
            else if ( this.endRange < this.startRange )
            {
                actionError.add( IAppConstants.APP_ERROR, new ActionMessage( IPropertiesConstant.ERROR_RANGE ) );
            }
         */
        }
        return actionError;
    }

    public String getPrefix()
    {
        return prefix;
    }

    public void setPrefix( String prefix )
    {
        this.prefix = prefix;
    }

    public long getStartRange()
    {
        return startRange;
    }

    public void setStartRange( long startRange )
    {
        this.startRange = startRange;
    }

    public long getEndRange()
    {
        return endRange;
    }

    public void setEndRange( long endRange )
    {
        this.endRange = endRange;
    }

    public Map<String, List<Map<String, List<ContentPojo>>>> getSubSubCategoryDetails()
    {
        return subSubCategoryDetails;
    }

    public void setSubSubCategoryDetails( Map<String, List<Map<String, List<ContentPojo>>>> inSubSubCategoryDetails )
    {
        subSubCategoryDetails = inSubSubCategoryDetails;
    }

    public List<ContentPojo> getEntityTypes()
    {
        return entityTypes;
    }

    public void setEntityTypes( List<ContentPojo> inEntityTypes )
    {
        entityTypes = inEntityTypes;
    }

    public List<ContentPojo> getAciveInactiveStatusList()
    {
        return aciveInactiveStatusList;
    }

    public void setAciveInactiveStatusList( List<ContentPojo> inAciveInactiveStatusList )
    {
        aciveInactiveStatusList = inAciveInactiveStatusList;
    }

    public String getUsedUnusedStatus()
    {
        return usedUnusedStatus;
    }

    public void setUsedUnusedStatus( String inUsedUnusedStatus )
    {
        usedUnusedStatus = inUsedUnusedStatus;
    }

    public CrmHolidayDetails getCrmHolidayPojo()
    {
        return crmHolidayPojo;
    }

    public void setCrmHolidayPojo( CrmHolidayDetails inCrmHolidayPojo )
    {
        crmHolidayPojo = inCrmHolidayPojo;
    }

    public String getDate()
    {
        return date;
    }

    public void setDate( String inDate )
    {
        date = inDate;
    }

    public List<CrmHolidayDetails> getCrmPastHolidayPojos()
    {
        return crmPastHolidayPojos;
    }

    public void setCrmPastHolidayPojos( List<CrmHolidayDetails> inCrmPastHolidayPojos )
    {
        crmPastHolidayPojos = inCrmPastHolidayPojos;
    }

    public List<CrmHolidayDetails> getCrmFutureHolidayPojos()
    {
        return crmFutureHolidayPojos;
    }

    public void setCrmFutureHolidayPojos( List<CrmHolidayDetails> inCrmFutureHolidayPojos )
    {
        crmFutureHolidayPojos = inCrmFutureHolidayPojos;
    }

    @Override
    public String toString()
    {
        StringBuilder builder = new StringBuilder();
        builder.append( "CrmRcaReasonForm [crmRcaReason=" ).append( crmRcaReason ).append( ", prefix=" )
                .append( prefix ).append( ", startRange=" ).append( startRange ).append( ", endRange=" )
                .append( endRange ).append( ", requestPage=" ).append( requestPage ).append( ", startRangeString=" )
                .append( startRangeString ).append( ", endRangeString=" ).append( endRangeString ).append( ", status=" )
                .append( status ).append( ", entityTypes=" ).append( entityTypes ).append( ", usedUnusedStatus=" )
                .append( usedUnusedStatus ).append( "]" );
        return builder.toString();
    }
}
