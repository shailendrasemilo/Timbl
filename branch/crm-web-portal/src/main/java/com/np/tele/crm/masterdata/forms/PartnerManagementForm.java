package com.np.tele.crm.masterdata.forms;

import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

import com.np.tele.crm.constants.IAppConstants;
import com.np.tele.crm.pojos.ContentPojo;
import com.np.tele.crm.service.client.CityPojo;
import com.np.tele.crm.service.client.CrmNpMobilePojo;
import com.np.tele.crm.service.client.CrmPartnerDetailsPojo;
import com.np.tele.crm.service.client.CrmPartnerMappingPojo;
import com.np.tele.crm.service.client.PartnerPojo;
import com.np.tele.crm.service.client.StatePojo;

/**
 * @author khawar.nawab
 *
 */
public class PartnerManagementForm
    extends ActionForm
{
    /**
     * 
     */
    private static final long           serialVersionUID   = 1L;
    private static final Logger         LOGGER             = Logger.getLogger( PartnerManagementForm.class );
    private String                      businessType;
    private String                      partnerType;
    private String                      boardedDate;
    private String                      boardedDateFrom;                                                     // only for search page
    private String                      boardedDateTo;                                                       // only for search page
    private String                      partnerName;
    private String                      contactChiefInitial;
    private String                      chiefInitial;
    private String                      chiefExFirstName;
    private String                      chiefExLastName;
    private String                      chiefDesignation;
    private String                      chiefMobile;
    private String                      chiefEmail;
    private String                      relatedDept;
    private String                      contactInitial;
    private String                      contactFirstName;
    private String                      contactLastName;
    private String                      partnerEmail;
    private String                      partnerPhone;
    private String                      partnerMobile;
    private String                      partnerFax;
    private String                      partnerAddress1;
    private String                      partnerAddress2;
    private String                      partnerCity;
    private String                      partnerState;
    private String                      partnerPincode;
    private String                      currentStatus;
    private String                      addedInERP;
    private String                      partnerERPCode;
    private List<PartnerPojo>           partnerList;
    private String                      stdCode;
    private long                        partnerId;
    private PartnerPojo                 partnerPojo;
    private String                      searchStatus;
    private List<ContentPojo>           statusList;
    private List<StatePojo>             statePojoList;
    private List<ContentPojo>           partnerTypeList;
    private List<ContentPojo>           products;
    private List<CityPojo>              cityPojoList;
    private String                      partnerCode;
    private List<PartnerPojo>           networkPartnerList;
    private Set<PartnerPojo>            ServicePartnerList;
    private List<CrmPartnerMappingPojo> crmMappingList;
    private List<CrmPartnerMappingPojo> oldCrmMappingList;
    private String                      status;
    private int                         rowCounter;
    private String                      partnerShortName;
    private String                      businessTypeSP;
    private int                         bussinessTrueCount = 0;
    int                                 partnerTrueCount   = 0;
    private List<CrmPartnerDetailsPojo> crmPartnerDetailsPojosList;
    private List<CrmNpMobilePojo>       crmNpMobileList;
    private List<CrmNpMobilePojo>       oldCrmNpMobileList;

    public int getRowCounter()
    {
        return rowCounter;
    }

    public void setRowCounter( int rowCounter )
    {
        this.rowCounter = rowCounter;
    }

    public List<CrmPartnerMappingPojo> getCrmMappingList()
    {
        return crmMappingList;
    }

    public void setCrmMappingList( List<CrmPartnerMappingPojo> crmMappingList )
    {
        this.crmMappingList = crmMappingList;
    }

    public String getStatus()
    {
        return status;
    }

    public void setStatus( String status )
    {
        this.status = status;
    }

    public List<PartnerPojo> getNetworkPartnerList()
    {
        return networkPartnerList;
    }

    public void setNetworkPartnerList( List<PartnerPojo> networkPartnerList )
    {
        this.networkPartnerList = networkPartnerList;
    }

    public Set<PartnerPojo> getServicePartnerList()
    {
        return ServicePartnerList;
    }

    public void setServicePartnerList( Set<PartnerPojo> servicePartnerList )
    {
        ServicePartnerList = servicePartnerList;
    }

    public String getChiefInitial()
    {
        return chiefInitial;
    }

    public void setChiefInitial( String inChiefInitial )
    {
        chiefInitial = inChiefInitial;
    }

    public String getContactInitial()
    {
        return contactInitial;
    }

    public void setContactInitial( String inContactInitial )
    {
        contactInitial = inContactInitial;
    }

    public String getBusinessType()
    {
        return businessType;
    }

    public void setBusinessType( String inBusinessType )
    {
        businessType = inBusinessType;
    }

    public String getPartnerType()
    {
        return partnerType;
    }

    public void setPartnerType( String inPartnerType )
    {
        partnerType = inPartnerType;
    }

    public String getBoardedDate()
    {
        return boardedDate;
    }

    public void setBoardedDate( String inBoardedDate )
    {
        boardedDate = inBoardedDate;
    }

    public String getPartnerName()
    {
        return partnerName;
    }

    public void setPartnerName( String inPartnerName )
    {
        partnerName = inPartnerName;
    }

    public String getChiefExFirstName()
    {
        return chiefExFirstName;
    }

    public void setChiefExFirstName( String inChiefExFirstName )
    {
        chiefExFirstName = inChiefExFirstName;
    }

    public String getChiefExLastName()
    {
        return chiefExLastName;
    }

    public void setChiefExLastName( String inChiefExLastName )
    {
        chiefExLastName = inChiefExLastName;
    }

    public String getChiefDesignation()
    {
        return chiefDesignation;
    }

    public void setChiefDesignation( String inChiefDesignation )
    {
        chiefDesignation = inChiefDesignation;
    }

    public String getChiefMobile()
    {
        return chiefMobile;
    }

    public void setChiefMobile( String inChiefMobile )
    {
        chiefMobile = inChiefMobile;
    }

    public String getChiefEmail()
    {
        return chiefEmail;
    }

    public void setChiefEmail( String inChiefEmail )
    {
        chiefEmail = inChiefEmail;
    }

    public String getRelatedDept()
    {
        return relatedDept;
    }

    public void setRelatedDept( String inRelatedDept )
    {
        relatedDept = inRelatedDept;
    }

    public String getContactFirstName()
    {
        return contactFirstName;
    }

    public void setContactFirstName( String inContactFirstName )
    {
        contactFirstName = inContactFirstName;
    }

    public String getContactLastName()
    {
        return contactLastName;
    }

    public void setContactLastName( String inContactLastName )
    {
        contactLastName = inContactLastName;
    }

    public String getPartnerEmail()
    {
        return partnerEmail;
    }

    public void setPartnerEmail( String inPartnerEmail )
    {
        partnerEmail = inPartnerEmail;
    }

    public String getPartnerPhone()
    {
        return partnerPhone;
    }

    public void setPartnerPhone( String inPartnerPhone )
    {
        partnerPhone = inPartnerPhone;
    }

    public String getPartnerMobile()
    {
        return partnerMobile;
    }

    public void setPartnerMobile( String inPartnerMobile )
    {
        partnerMobile = inPartnerMobile;
    }

    public String getPartnerFax()
    {
        return partnerFax;
    }

    public void setPartnerFax( String inPartnerFax )
    {
        partnerFax = inPartnerFax;
    }

    public String getPartnerAddress1()
    {
        return partnerAddress1;
    }

    public void setPartnerAddress1( String inPartnerAddress1 )
    {
        partnerAddress1 = inPartnerAddress1;
    }

    public String getPartnerAddress2()
    {
        return partnerAddress2;
    }

    public void setPartnerAddress2( String inPartnerAddress2 )
    {
        partnerAddress2 = inPartnerAddress2;
    }

    public String getPartnerCity()
    {
        return partnerCity;
    }

    public void setPartnerCity( String inPartnerCity )
    {
        partnerCity = inPartnerCity;
    }

    public String getPartnerState()
    {
        return partnerState;
    }

    public void setPartnerState( String inPartnerState )
    {
        partnerState = inPartnerState;
    }

    public String getPartnerPincode()
    {
        return partnerPincode;
    }

    public void setPartnerPincode( String inPartnerPincode )
    {
        partnerPincode = inPartnerPincode;
    }

    public String getCurrentStatus()
    {
        return currentStatus;
    }

    public void setCurrentStatus( String inCurrentStatus )
    {
        currentStatus = inCurrentStatus;
    }

    public String getAddedInERP()
    {
        return addedInERP;
    }

    public void setAddedInERP( String inAddedInERP )
    {
        addedInERP = inAddedInERP;
    }

    public String getPartnerERPCode()
    {
        return partnerERPCode;
    }

    public void setPartnerERPCode( String inPartnerERPCode )
    {
        partnerERPCode = inPartnerERPCode;
    }

    public String getBoardedDateFrom()
    {
        return boardedDateFrom;
    }

    public void setBoardedDateFrom( String inBoardedDateFrom )
    {
        boardedDateFrom = inBoardedDateFrom;
    }

    public String getBoardedDateTo()
    {
        return boardedDateTo;
    }

    public void setBoardedDateTo( String inBoardedDateTo )
    {
        boardedDateTo = inBoardedDateTo;
    }

    public List<PartnerPojo> getPartnerList()
    {
        return partnerList;
    }

    public void setPartnerList( List<PartnerPojo> inPartnerList )
    {
        partnerList = inPartnerList;
    }

    public String getStdCode()
    {
        return stdCode;
    }

    public void setStdCode( String inStdCode )
    {
        stdCode = inStdCode;
    }

    public long getPartnerId()
    {
        return partnerId;
    }

    public void setPartnerId( long inPartnerId )
    {
        partnerId = inPartnerId;
    }

    public PartnerPojo getPartnerPojo()
    {
        return partnerPojo;
    }

    public void setPartnerPojo( PartnerPojo inPartnerPojo )
    {
        partnerPojo = inPartnerPojo;
    }

    public String getSearchStatus()
    {
        return searchStatus;
    }

    public void setSearchStatus( String inSearchStatus )
    {
        searchStatus = inSearchStatus;
    }

    public List<ContentPojo> getStatusList()
    {
        return statusList;
    }

    public void setStatusList( List<ContentPojo> inStatusList )
    {
        statusList = inStatusList;
    }

    public List<StatePojo> getStatePojoList()
    {
        return statePojoList;
    }

    public void setStatePojoList( List<StatePojo> inStatePojoList )
    {
        statePojoList = inStatePojoList;
    }

    public List<ContentPojo> getPartnerTypeList()
    {
        return partnerTypeList;
    }

    public void setPartnerTypeList( List<ContentPojo> inPartnerTypeList )
    {
        partnerTypeList = inPartnerTypeList;
    }

    public List<ContentPojo> getProducts()
    {
        return products;
    }

    public void setProducts( List<ContentPojo> inProducts )
    {
        products = inProducts;
    }

    public List<CityPojo> getCityPojoList()
    {
        return cityPojoList;
    }

    public void setCityPojoList( List<CityPojo> inCityPojoList )
    {
        cityPojoList = inCityPojoList;
    }

    public String getPartnerCode()
    {
        return partnerCode;
    }

    public void setPartnerCode( String inPartnerCode )
    {
        partnerCode = inPartnerCode;
    }

    public List<CrmPartnerMappingPojo> getOldCrmMappingList()
    {
        return oldCrmMappingList;
    }

    public void setOldCrmMappingList( List<CrmPartnerMappingPojo> oldCrmMappingList )
    {
        this.oldCrmMappingList = oldCrmMappingList;
    }

    public String getPartnerShortName()
    {
        return partnerShortName;
    }

    public void setPartnerShortName( String inPartnerShortName )
    {
        partnerShortName = inPartnerShortName;
    }

    public String getContactChiefInitial()
    {
        return contactChiefInitial;
    }

    public void setContactChiefInitial( String inContactChiefInitial )
    {
        contactChiefInitial = inContactChiefInitial;
    }

    @Override
    public ActionErrors validate( ActionMapping inMapping, HttpServletRequest inRequest )
    {
        LOGGER.info( "In validate method........" );
        String method = inRequest.getParameter( IAppConstants.PARAMETER_NAME );
        LOGGER.info( "In validate method........" + method );
        ActionErrors actionError = new ActionErrors();
        MasterFormHelper.validatePartnerForm( actionError, method, this );
        return actionError;
    }

    @Override
    public void reset( ActionMapping inMapping, HttpServletRequest inRequest )
    {
        LOGGER.info( "Master Data Reset Start" );
        String methodName = inRequest.getParameter( IAppConstants.PARAMETER_NAME );
        MasterFormHelper.resetPartner( methodName, this );
        LOGGER.info( "Master Data Reset End" );
    }

    public String getBusinessTypeSP()
    {
        return businessTypeSP;
    }

    public void setBusinessTypeSP( String businessTypeSP )
    {
        this.businessTypeSP = businessTypeSP;
    }

    public int getBussinessTrueCount()
    {
        return bussinessTrueCount;
    }

    public void setBussinessTrueCount( int inBussinessTrueCount )
    {
        bussinessTrueCount = inBussinessTrueCount;
    }

    public int getPartnerTrueCount()
    {
        return partnerTrueCount;
    }

    public void setPartnerTrueCount( int inPartnerTrueCount )
    {
        partnerTrueCount = inPartnerTrueCount;
    }

    public List<CrmPartnerDetailsPojo> getCrmPartnerDetailsPojosList()
    {
        return crmPartnerDetailsPojosList;
    }

    public void setCrmPartnerDetailsPojosList( List<CrmPartnerDetailsPojo> inCrmPartnerDetailsPojosList )
    {
        crmPartnerDetailsPojosList = inCrmPartnerDetailsPojosList;
    }

    public List<CrmNpMobilePojo> getCrmNpMobileList()
    {
        return crmNpMobileList;
    }

    public void setCrmNpMobileList( List<CrmNpMobilePojo> inCrmNpMobileList )
    {
        crmNpMobileList = inCrmNpMobileList;
    }

    public List<CrmNpMobilePojo> getOldCrmNpMobileList()
    {
        return oldCrmNpMobileList;
    }

    public void setOldCrmNpMobileList( List<CrmNpMobilePojo> inOldCrmNpMobileList )
    {
        oldCrmNpMobileList = inOldCrmNpMobileList;
    }
}