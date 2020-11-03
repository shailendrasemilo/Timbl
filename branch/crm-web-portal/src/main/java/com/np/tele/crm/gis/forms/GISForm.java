package com.np.tele.crm.gis.forms;

import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.upload.FormFile;

import com.np.tele.crm.constants.IAppConstants;
import com.np.tele.crm.gis.bm.GISUtils;
import com.np.tele.crm.gis.pojo.GISUploadPojo;
import com.np.tele.crm.pojos.ContentPojo;
import com.np.tele.crm.service.client.AreaPojo;
import com.np.tele.crm.service.client.CityPojo;
import com.np.tele.crm.service.client.CountryPojo;
import com.np.tele.crm.service.client.LocalityPojo;
import com.np.tele.crm.service.client.PartnerPojo;
import com.np.tele.crm.service.client.SocietyNetworkPartnerPojo;
import com.np.tele.crm.service.client.SocietyPojo;
import com.np.tele.crm.service.client.StatePojo;

public class GISForm
    extends ActionForm
{
    private static final long              serialVersionUID = 1L;
    private static final Logger            LOGGER           = Logger.getLogger( GISForm.class );
    private String                         country;
    private String                         state;
    private String                         city;
    private String                         area;
    private String                         locality;
    private String                         society;
    private FormFile                       formFile;
    private int                            networkPartner;
    private String                         product;
    private String                         message;
    private SocietyPojo                    societyPojo;
    private SocietyPojo                    oldSocietyPojo;
    private List<CountryPojo>              countryPojoList;
    private List<StatePojo>                stateDataList;
    private List<StatePojo>                statePojoList;
    private List<CityPojo>                 cityList;
    private List<CityPojo>                 cityDataList;
    private List<AreaPojo>                 areaList;
    private List<AreaPojo>                 areaDataList;
    private List<LocalityPojo>             localityList;
    private List<SocietyPojo>              searchSocietyList;
    private List<PartnerPojo>              partnerPojoList;
    private long                           stateId;
    private long                           stateIdForArea;
    private long                           cityId;
    private long                           stateIdForLoc;
    private long                           cityIdForLoc;
    private long                           areaId;
    private long                           localityId;
    private String                         searchStatus;
    private String                         manageGis;
    private boolean                        editable;
    private String                         stateStatus;
    private String                         cityStatus;
    private String                         areaStatus;
    private String                         localityStatus;
    private long                           hiddenGisId;
    private String                         hiddenGisOperation;
    private long                           partnerId;
    private long                           societyId;
    private List<ContentPojo>              statusList;
    private List<ContentPojo>              productList;
    private List<ContentPojo>              fieldTypeList;
    private String                         partnerName;
    private String                         stateName;
    private String                         cityName;
    private String                         status;
    private int                            rowCounter;
    private List<PartnerPojo>              partnerList;
    private String[]                       productArray;
    private SocietyNetworkPartnerPojo      networkPartnerPojo;
    private SocietyNetworkPartnerPojo      OldNetworkPartnerPojo;
    private Set<SocietyNetworkPartnerPojo> societyNetworkPartners;
    private List<GISUploadPojo>            configureGisUpload;

    public String getCountry()
    {
        return country;
    }

    public void setCountry( String inCountry )
    {
        country = inCountry;
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

    public String getArea()
    {
        return area;
    }

    public void setArea( String inArea )
    {
        area = inArea;
    }

    public String getLocality()
    {
        return locality;
    }

    public void setLocality( String inLocality )
    {
        locality = inLocality;
    }

    public String getSociety()
    {
        return society;
    }

    public void setSociety( String inSociety )
    {
        society = inSociety;
    }

    public FormFile getFormFile()
    {
        return formFile;
    }

    public void setFormFile( FormFile inFormFile )
    {
        formFile = inFormFile;
    }

    public int getNetworkPartner()
    {
        return networkPartner;
    }

    public void setNetworkPartner( int inNetworkPartner )
    {
        networkPartner = inNetworkPartner;
    }

    public String getProduct()
    {
        return product;
    }

    public void setProduct( String inProduct )
    {
        product = inProduct;
    }

    public String getMessage()
    {
        return message;
    }

    public void setMessage( String inMessage )
    {
        message = inMessage;
    }

    public SocietyPojo getSocietyPojo()
    {
        return societyPojo;
    }

    public void setSocietyPojo( SocietyPojo inSocietyPojo )
    {
        societyPojo = inSocietyPojo;
    }

    public SocietyPojo getOldSocietyPojo()
    {
        return oldSocietyPojo;
    }

    public void setOldSocietyPojo( SocietyPojo inOldSocietyPojo )
    {
        oldSocietyPojo = inOldSocietyPojo;
    }

    public List<CountryPojo> getCountryPojoList()
    {
        return countryPojoList;
    }

    public void setCountryPojoList( List<CountryPojo> inCountryPojoList )
    {
        countryPojoList = inCountryPojoList;
    }

    public List<StatePojo> getStateDataList()
    {
        return stateDataList;
    }

    public void setStateDataList( List<StatePojo> inStateDataList )
    {
        stateDataList = inStateDataList;
    }

    public List<StatePojo> getStatePojoList()
    {
        return statePojoList;
    }

    public void setStatePojoList( List<StatePojo> inStatePojoList )
    {
        statePojoList = inStatePojoList;
    }

    public List<CityPojo> getCityList()
    {
        return cityList;
    }

    public void setCityList( List<CityPojo> inCityList )
    {
        cityList = inCityList;
    }

    public List<CityPojo> getCityDataList()
    {
        return cityDataList;
    }

    public void setCityDataList( List<CityPojo> inCityDataList )
    {
        cityDataList = inCityDataList;
    }

    public List<AreaPojo> getAreaList()
    {
        return areaList;
    }

    public void setAreaList( List<AreaPojo> inAreaList )
    {
        areaList = inAreaList;
    }

    public List<AreaPojo> getAreaDataList()
    {
        return areaDataList;
    }

    public void setAreaDataList( List<AreaPojo> inAreaDataList )
    {
        areaDataList = inAreaDataList;
    }

    public List<LocalityPojo> getLocalityList()
    {
        return localityList;
    }

    public void setLocalityList( List<LocalityPojo> inLocalityList )
    {
        localityList = inLocalityList;
    }

    public List<SocietyPojo> getSearchSocietyList()
    {
        return searchSocietyList;
    }

    public void setSearchSocietyList( List<SocietyPojo> inSearchSocietyList )
    {
        searchSocietyList = inSearchSocietyList;
    }

    public List<PartnerPojo> getPartnerPojoList()
    {
        return partnerPojoList;
    }

    public void setPartnerPojoList( List<PartnerPojo> inPartnerPojoList )
    {
        partnerPojoList = inPartnerPojoList;
    }

    public long getStateId()
    {
        return stateId;
    }

    public void setStateId( long inStateId )
    {
        stateId = inStateId;
    }

    public long getStateIdForArea()
    {
        return stateIdForArea;
    }

    public void setStateIdForArea( long inStateIdForArea )
    {
        stateIdForArea = inStateIdForArea;
    }

    public long getCityId()
    {
        return cityId;
    }

    public void setCityId( long inCityId )
    {
        cityId = inCityId;
    }

    public long getStateIdForLoc()
    {
        return stateIdForLoc;
    }

    public void setStateIdForLoc( long inStateIdForLoc )
    {
        stateIdForLoc = inStateIdForLoc;
    }

    public long getCityIdForLoc()
    {
        return cityIdForLoc;
    }

    public void setCityIdForLoc( long inCityIdForLoc )
    {
        cityIdForLoc = inCityIdForLoc;
    }

    public long getAreaId()
    {
        return areaId;
    }

    public void setAreaId( long inAreaId )
    {
        areaId = inAreaId;
    }

    public long getLocalityId()
    {
        return localityId;
    }

    public void setLocalityId( long inLocalityId )
    {
        localityId = inLocalityId;
    }

    public String getSearchStatus()
    {
        return searchStatus;
    }

    public void setSearchStatus( String inSearchStatus )
    {
        searchStatus = inSearchStatus;
    }

    public String getManageGis()
    {
        return manageGis;
    }

    public void setManageGis( String inManageGis )
    {
        manageGis = inManageGis;
    }

    public boolean isEditable()
    {
        return editable;
    }

    public void setEditable( boolean inEditable )
    {
        editable = inEditable;
    }

    public String getStateStatus()
    {
        return stateStatus;
    }

    public void setStateStatus( String inStateStatus )
    {
        stateStatus = inStateStatus;
    }

    public String getCityStatus()
    {
        return cityStatus;
    }

    public void setCityStatus( String inCityStatus )
    {
        cityStatus = inCityStatus;
    }

    public String getAreaStatus()
    {
        return areaStatus;
    }

    public void setAreaStatus( String inAreaStatus )
    {
        areaStatus = inAreaStatus;
    }

    public String getLocalityStatus()
    {
        return localityStatus;
    }

    public void setLocalityStatus( String inLocalityStatus )
    {
        localityStatus = inLocalityStatus;
    }

    public long getHiddenGisId()
    {
        return hiddenGisId;
    }

    public void setHiddenGisId( long inHiddenGisId )
    {
        hiddenGisId = inHiddenGisId;
    }

    public String getHiddenGisOperation()
    {
        return hiddenGisOperation;
    }

    public void setHiddenGisOperation( String inHiddenGisOperation )
    {
        hiddenGisOperation = inHiddenGisOperation;
    }

    public long getPartnerId()
    {
        return partnerId;
    }

    public void setPartnerId( long inPartnerId )
    {
        partnerId = inPartnerId;
    }

    public long getSocietyId()
    {
        return societyId;
    }

    public void setSocietyId( long inSocietyId )
    {
        societyId = inSocietyId;
    }

    public List<ContentPojo> getStatusList()
    {
        return statusList;
    }

    public void setStatusList( List<ContentPojo> inStatusList )
    {
        statusList = inStatusList;
    }

    public List<ContentPojo> getProductList()
    {
        return productList;
    }

    public void setProductList( List<ContentPojo> inProductList )
    {
        productList = inProductList;
    }

    public List<ContentPojo> getFieldTypeList()
    {
        return fieldTypeList;
    }

    public void setFieldTypeList( List<ContentPojo> inFieldTypeList )
    {
        fieldTypeList = inFieldTypeList;
    }

    public String getPartnerName()
    {
        return partnerName;
    }

    public void setPartnerName( String inPartnerName )
    {
        partnerName = inPartnerName;
    }

    public String getStateName()
    {
        return stateName;
    }

    public void setStateName( String inStateName )
    {
        stateName = inStateName;
    }

    public String getCityName()
    {
        return cityName;
    }

    public void setCityName( String inCityName )
    {
        cityName = inCityName;
    }

    public String getStatus()
    {
        return status;
    }

    public void setStatus( String inStatus )
    {
        status = inStatus;
    }

    public int getRowCounter()
    {
        return rowCounter;
    }

    public void setRowCounter( int rowCounter )
    {
        this.rowCounter = rowCounter;
    }

    public List<PartnerPojo> getPartnerList()
    {
        return partnerList;
    }

    public void setPartnerList( List<PartnerPojo> inPartnerList )
    {
        partnerList = inPartnerList;
    }

    public String[] getProductArray()
    {
        return productArray;
    }

    public void setProductArray( String[] productArray )
    {
        this.productArray = productArray;
    }

    public SocietyNetworkPartnerPojo getNetworkPartnerPojo()
    {
        return networkPartnerPojo;
    }

    public void setNetworkPartnerPojo( SocietyNetworkPartnerPojo inNetworkPartnerPojo )
    {
        networkPartnerPojo = inNetworkPartnerPojo;
    }

    public Set<SocietyNetworkPartnerPojo> getSocietyNetworkPartners()
    {
        return societyNetworkPartners;
    }

    public void setSocietyNetworkPartners( Set<SocietyNetworkPartnerPojo> inSocietyNetworkPartners )
    {
        societyNetworkPartners = inSocietyNetworkPartners;
    }

    public List<GISUploadPojo> getConfigureGisUpload()
    {
        return configureGisUpload;
    }

    public void setConfigureGisUpload( List<GISUploadPojo> configureGisUpload )
    {
        this.configureGisUpload = configureGisUpload;
    }

    public SocietyNetworkPartnerPojo getOldNetworkPartnerPojo()
    {
        return OldNetworkPartnerPojo;
    }

    public void setOldNetworkPartnerPojo( SocietyNetworkPartnerPojo inOldNetworkPartnerPojo )
    {
        OldNetworkPartnerPojo = inOldNetworkPartnerPojo;
    }
    
    @Override
    public void reset( ActionMapping inMapping, HttpServletRequest inRequest )
    {
        LOGGER.info( "Master Data Reset Start" );
        String methodName = inRequest.getParameter( IAppConstants.PARAMETER_NAME );
        GISFormHelper.resetGisFormList( methodName, this );
    }

    @Override
    public ActionErrors validate( ActionMapping inMapping, HttpServletRequest inRequest )
    {
        String method = inRequest.getParameter( IAppConstants.PARAMETER_NAME );
        LOGGER.info( "In validate method........" + method );
        ActionErrors actionError = new ActionErrors();
        GISFormHelper.validateGisForm( method, actionError, this );
        if ( !actionError.isEmpty() )
        {
            setStatePojoList( GISUtils.getActiveStates( IAppConstants.COUNTRY_INDIA ) );
        }
        return actionError;
    }
}
