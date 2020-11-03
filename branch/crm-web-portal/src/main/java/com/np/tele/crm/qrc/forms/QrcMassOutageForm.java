package com.np.tele.crm.qrc.forms;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

import com.np.tele.crm.constants.IAppConstants;
import com.np.tele.crm.pojos.ContentPojo;
import com.np.tele.crm.service.client.AreaPojo;
import com.np.tele.crm.service.client.CityPojo;
import com.np.tele.crm.service.client.CrmMassOutagePojo;
import com.np.tele.crm.service.client.CrmRcaReasonPojo;
import com.np.tele.crm.service.client.LocalityPojo;
import com.np.tele.crm.service.client.PartnerPojo;
import com.np.tele.crm.service.client.SocietyPojo;
import com.np.tele.crm.service.client.StatePojo;
import com.np.tele.crm.utils.StringUtils;
import com.np.validator.util.CommonValidator;

public class QrcMassOutageForm
    extends ActionForm
{
    private static final long       serialVersionUID = 1L;
    private static final Logger     LOGGER           = Logger.getLogger( QrcMassOutageForm.class );
    private List<StatePojo>         statePojos;
    private List<PartnerPojo>       networkPartnersEoc;
    private List<PartnerPojo>       networkPartnersFbr;
    private List<PartnerPojo>       networkPartnersRf;
    private List<String>            npEoc;
    private List<String>            npFbr;
    private String                  product;
    private List<ContentPojo>       productTypeList;
    private CrmMassOutagePojo       massOutagePojo;
    private String                  massOutageType;
    private String                  state;
    private String                  city;
    private String                  area;
    private String                  locality;
    private String                  society;
    private String                  networkPartner;
    private List<CityPojo>          cityPojoList;
    private List<AreaPojo>          areaList;
    private List<LocalityPojo>      localityList;
    private List<SocietyPojo>       societyList;
    private List<CrmMassOutagePojo> massOutagePojos;
    private List<PartnerPojo>       networkPartners;
    private String                  nassPortId;
    private String                  outageStartTime;
    private List<CrmRcaReasonPojo>  reasons;

    public List<StatePojo> getStatePojos()
    {
        return statePojos;
    }

    public void setStatePojos( List<StatePojo> statePojos )
    {
        this.statePojos = statePojos;
    }

    public List<PartnerPojo> getNetworkPartnersEoc()
    {
        return networkPartnersEoc;
    }

    public void setNetworkPartnersEoc( List<PartnerPojo> networkPartnersEoc )
    {
        this.networkPartnersEoc = networkPartnersEoc;
    }

    public List<PartnerPojo> getNetworkPartnersFbr()
    {
        return networkPartnersFbr;
    }

    public void setNetworkPartnersFbr( List<PartnerPojo> networkPartnersFbr )
    {
        this.networkPartnersFbr = networkPartnersFbr;
    }

    public List<PartnerPojo> getNetworkPartnersRf()
    {
        return networkPartnersRf;
    }

    public void setNetworkPartnersRf( List<PartnerPojo> networkPartnersRf )
    {
        this.networkPartnersRf = networkPartnersRf;
    }

    public List<String> getNpEoc()
    {
        return npEoc;
    }

    public void setNpEoc( List<String> npEoc )
    {
        this.npEoc = npEoc;
    }

    public List<String> getNpFbr()
    {
        return npFbr;
    }

    public void setNpFbr( List<String> npFbr )
    {
        this.npFbr = npFbr;
    }

    public String getProduct()
    {
        return product;
    }

    public void setProduct( String product )
    {
        this.product = product;
    }

    public List<ContentPojo> getProductTypeList()
    {
        return productTypeList;
    }

    public void setProductTypeList( List<ContentPojo> productTypeList )
    {
        this.productTypeList = productTypeList;
    }

    public CrmMassOutagePojo getMassOutagePojo()
    {
        return massOutagePojo;
    }

    public void setMassOutagePojo( CrmMassOutagePojo massOutagePojo )
    {
        this.massOutagePojo = massOutagePojo;
    }

    public String getMassOutageType()
    {
        return massOutageType;
    }

    public void setMassOutageType( String massOutageType )
    {
        this.massOutageType = massOutageType;
    }

    public String getState()
    {
        return state;
    }

    public void setState( String state )
    {
        this.state = state;
    }

    public String getCity()
    {
        return city;
    }

    public void setCity( String city )
    {
        this.city = city;
    }

    public String getArea()
    {
        return area;
    }

    public void setArea( String area )
    {
        this.area = area;
    }

    public String getLocality()
    {
        return locality;
    }

    public void setLocality( String locality )
    {
        this.locality = locality;
    }

    public String getSociety()
    {
        return society;
    }

    public void setSociety( String society )
    {
        this.society = society;
    }

    public String getNetworkPartner()
    {
        return networkPartner;
    }

    public void setNetworkPartner( String networkPartner )
    {
        this.networkPartner = networkPartner;
    }

    public List<CityPojo> getCityPojoList()
    {
        return cityPojoList;
    }

    public void setCityPojoList( List<CityPojo> cityPojoList )
    {
        this.cityPojoList = cityPojoList;
    }

    public List<AreaPojo> getAreaList()
    {
        return areaList;
    }

    public void setAreaList( List<AreaPojo> areaList )
    {
        this.areaList = areaList;
    }

    public List<LocalityPojo> getLocalityList()
    {
        return localityList;
    }

    public void setLocalityList( List<LocalityPojo> localityList )
    {
        this.localityList = localityList;
    }

    public List<SocietyPojo> getSocietyList()
    {
        return societyList;
    }

    public void setSocietyList( List<SocietyPojo> societyList )
    {
        this.societyList = societyList;
    }

    public List<CrmMassOutagePojo> getMassOutagePojos()
    {
        return massOutagePojos;
    }

    public void setMassOutagePojos( List<CrmMassOutagePojo> massOutagePojos )
    {
        this.massOutagePojos = massOutagePojos;
    }

    public List<PartnerPojo> getNetworkPartners()
    {
        return networkPartners;
    }

    public void setNetworkPartners( List<PartnerPojo> networkPartners )
    {
        this.networkPartners = networkPartners;
    }

    public String getNassPortId()
    {
        return nassPortId;
    }

    public void setNassPortId( String nassPortId )
    {
        this.nassPortId = nassPortId;
    }

    public String getOutageStartTime()
    {
        return outageStartTime;
    }

    public void setOutageStartTime( String outageStartTime )
    {
        this.outageStartTime = outageStartTime;
    }

    public List<CrmRcaReasonPojo> getReasons()
    {
        return reasons;
    }

    public void setReasons( List<CrmRcaReasonPojo> inReasons )
    {
        reasons = inReasons;
    }

    @Override
    public void reset( ActionMapping inMapping, HttpServletRequest inRequest )
    {
        LOGGER.info( "QrcMassOutageForm::reset()" );
        String method = inRequest.getParameter( IAppConstants.PARAMETER_NAME );
        QrcMassOutageHelper.reset( this, method );
        Date date = new Date();
        if ( StringUtils.equals( method, IAppConstants.METHOD_UPDATE_MASS_OUTAGE ) )
        {
            if ( StringUtils.isValidObj( this.getMassOutagePojo() ) )
            {
                this.getMassOutagePojo().setSendSms( false );
            }
        }
        if ( StringUtils.equals( method, "massOutage" ) || StringUtils.equals( method, "massOutagePage" ) )
        {
            if ( CommonValidator.isValidCollection( statePojos ) )
            {
                for ( StatePojo statePojo : statePojos )
                {
                    for ( CityPojo cityPojo : statePojo.getCities() )
                    {
                        for ( AreaPojo areaPojo : cityPojo.getAreas() )
                        {
                            for ( SocietyPojo societyPojo : areaPojo.getSocieties() )
                            {
                                societyPojo.setEditable( false );
                            }
                            areaPojo.setEditable( false );
                        }
                        cityPojo.setEditable( false );
                    }
                    statePojo.setEditable( false );
                }
            }
        }
    }
    /* @Override
     public ActionErrors validate( ActionMapping inMapping, HttpServletRequest inRequest )
     {
         String method = inRequest.getParameter( IAppConstants.PARAMETER_NAME );
         ActionErrors errors = new ActionErrors();
         QrcMassOutageHelper.validate( this, method, errors );
         return errors;
     }*/
}
