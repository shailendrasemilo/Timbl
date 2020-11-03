package com.np.lms.mvc.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.np.lms.rest.dto.MasterRequestDto;
import com.np.lms.rest.dto.MasterResponsedto;
import com.np.tele.crm.constants.CRMDisplayListConstants;
import com.np.tele.crm.constants.CRMFunctionalBinStages;
import com.np.tele.crm.constants.CRMParameter;
import com.np.tele.crm.constants.CRMRCAReason;
import com.np.tele.crm.constants.CRMServiceCode;
import com.np.tele.crm.constants.CRMStatusCode;
import com.np.tele.crm.constants.IAppConstants;
import com.np.tele.crm.constants.IGlobalConstants;
import com.np.tele.crm.constants.ServiceParameter;
import com.np.tele.crm.dto.ConfigDto;
import com.np.tele.crm.dto.GISDto;
import com.np.tele.crm.dto.MasterDataDto;
import com.np.tele.crm.locator.CRMServicesProxy;
import com.np.tele.crm.pojos.AreaPojo;
import com.np.tele.crm.pojos.CityPojo;
import com.np.tele.crm.pojos.CountryPojo;
import com.np.tele.crm.pojos.CrmRcaReasonPojo;
import com.np.tele.crm.pojos.LocalityPojo;
import com.np.tele.crm.pojos.PartnerPojo;
import com.np.tele.crm.pojos.SocietyPojo;
import com.np.tele.crm.pojos.StatePojo;
import com.np.tele.crm.utils.StringUtils;

@Controller()
@SuppressWarnings("rawtypes")
public class MasterController
{
    private static final Logger LOGGER              = Logger.getLogger( MasterController.class );
    private static final String URI_PATH_STATE      = "/locationAPI";
    private static final String URI_PATH_INBOX      = "/inboxAPI";
    private static final String URI_PATH_LMS_ACTION = "/actionListAPI";
    private static final String APPLICATION_JSON    = "application/json";

    @ResponseBody
    @RequestMapping(value = URI_PATH_STATE, method = RequestMethod.POST, consumes = APPLICATION_JSON)
    public MasterResponsedto locaction( @RequestHeader Map httpHeaders,
                                        @RequestBody @Valid MasterRequestDto inRequestDto,
                                        BindingResult inBindingResult )
    {
        CRMServiceCode serviceCode = CRMServiceCode.CRM997;
        GISDto dto = new GISDto();
        short countryId = 1;
        MasterResponsedto responseDto = new MasterResponsedto();
        LOGGER.info( "Inside LMSController, getState" + inRequestDto );
        try
        {
            if ( StringUtils.equals( CRMParameter.STATE.getParameter(), inRequestDto.getOperation() ) )
            {
                StatePojo statePojo = new StatePojo();
                dto = CRMServicesProxy
                        .getInstance()
                        .getGISService( IGlobalConstants.LOCAL, IGlobalConstants.APP )
                        .gisOperation( ServiceParameter.SEARCH.getParameter(), CRMParameter.COUNTRY.getParameter(), dto );
                for ( CountryPojo pojo : dto.getCountryPojosList() )
                {
                    if ( StringUtils.equals( pojo.getCountryName(), IAppConstants.COUNTRY_INDIA ) )
                    {
                        countryId = pojo.getCountryId();
                    }
                    break;
                }
                if ( countryId > 0 )
                {
                    statePojo.setCountryId( countryId );
                    dto.setStatePojo( statePojo );
                    dto = CRMServicesProxy
                            .getInstance()
                            .getGISService( IGlobalConstants.LOCAL, IGlobalConstants.APP )
                            .gisOperation( ServiceParameter.SEARCH.getParameter(), CRMParameter.STATE.getParameter(),
                                           dto );
                    for ( StatePojo pojo : dto.getStatePojosList() )
                    {
                        pojo.getCities().clear();
                    }
                    BeanUtils.copyProperties( responseDto, dto );
                }
            }
            else if ( StringUtils.equals( CRMParameter.CITY.getParameter(), inRequestDto.getOperation() ) )
            {
                if ( inRequestDto.getStateId() > 0 )
                {
                    CityPojo city = new CityPojo();
                    city.setStateId( inRequestDto.getStateId() );
                    dto.setCityPojo( city );
                    dto = CRMServicesProxy
                            .getInstance()
                            .getGISService( IGlobalConstants.LOCAL, IGlobalConstants.APP )
                            .gisOperation( ServiceParameter.SEARCH.getParameter(), CRMParameter.CITY.getParameter(),
                                           dto );
                    for ( CityPojo pojo : dto.getCityPojosList() )
                    {
                        pojo.getAreas().clear();
                    }
                    BeanUtils.copyProperties( responseDto, dto );
                }
            }
            else if ( StringUtils.equals( CRMParameter.AREA.getParameter(), inRequestDto.getOperation() ) )
            {
                if ( ( inRequestDto.getStateId() > 0 ) && ( inRequestDto.getCityId() > 0 ) )
                {
                    AreaPojo area = new AreaPojo();
                    area.setCityId( inRequestDto.getCityId() );
                    dto.setAreaPojo( area );
                    dto = CRMServicesProxy
                            .getInstance()
                            .getGISService( IGlobalConstants.LOCAL, IGlobalConstants.APP )
                            .gisOperation( ServiceParameter.SEARCH.getParameter(), CRMParameter.AREA.getParameter(),
                                           dto );
                    BeanUtils.copyProperties( responseDto, dto );
                }
            }
            else if ( StringUtils.equals( CRMParameter.LOCALITY.getParameter(), inRequestDto.getOperation() ) )
            {
                if ( ( inRequestDto.getStateId() > 0 ) && ( inRequestDto.getCityId() > 0 )
                        && ( inRequestDto.getAreaId() > 0 ) )
                {
                    LocalityPojo locality = new LocalityPojo();
                    locality.setAreaId( inRequestDto.getAreaId() );
                    dto.setLocalityPojo( locality );
                    dto = CRMServicesProxy
                            .getInstance()
                            .getGISService( IGlobalConstants.LOCAL, IGlobalConstants.APP )
                            .gisOperation( ServiceParameter.SEARCH.getParameter(),
                                           CRMParameter.LOCALITY.getParameter(), dto );
                    BeanUtils.copyProperties( responseDto, dto );
                }
            }
            else if ( StringUtils.equals( CRMParameter.SOCIETY.getParameter(), inRequestDto.getOperation() ) )
            {
                if ( ( inRequestDto.getStateId() > 0 ) && ( inRequestDto.getCityId() > 0 )
                        && ( inRequestDto.getAreaId() > 0 ) && ( inRequestDto.getLocalityId() > 0 ) )
                {
                    SocietyPojo society = new SocietyPojo();
                    //society.setLocalityId( inRequestDto.getLocalityId() );
                    dto.setSocietyPojo( society );
                    dto = CRMServicesProxy
                            .getInstance()
                            .getGISService( IGlobalConstants.LOCAL, IGlobalConstants.APP )
                            .gisOperation( ServiceParameter.SEARCH.getParameter(), CRMParameter.SOCIETY.getParameter(),
                                           dto );
                    BeanUtils.copyProperties( responseDto, dto );
                }
            }
        }
        catch ( Exception ex )
        {
            serviceCode = CRMServiceCode.CRM999;
            LOGGER.error( "Exception", ex );
        }
        finally
        {
            if ( !StringUtils.isValidObj( responseDto.getStatusCode() ) || responseDto.getStatusCode().isEmpty() )
            {
                responseDto.setStatusCode( serviceCode.getStatusCode() );
                responseDto.setStatusDesc( serviceCode.getStatusDesc() );
            }
        }
        return responseDto;
    }

    @ResponseBody
    @RequestMapping(value = URI_PATH_INBOX, method = RequestMethod.POST, consumes = APPLICATION_JSON)
    public MasterResponsedto inbox( @RequestHeader Map httpHeaders,
                                    @RequestBody @Valid MasterRequestDto inRequestDto,
                                    BindingResult inBindingResult,
                                    HttpServletRequest httpRequest )
    {
        MasterResponsedto responseDto = new MasterResponsedto();
        CRMServiceCode serviceCode = CRMServiceCode.CRM997;
        try
        {
            if ( StringUtils.equals( ServiceParameter.SEARCH.getParameter(), inRequestDto.getOperation() ) )
            {
                ConfigDto dto = new ConfigDto();
                dto.setInboxType( inRequestDto.getRequestType() );//CRMRequestType.LEAD.getRequestCode() 
                List<String> stages = new ArrayList<String>();
                for ( CRMFunctionalBinStages binStages : CRMFunctionalBinStages.values() )
                {
                    if ( inRequestDto.getFunctionalBin().contains( binStages.getFunctionalBin() ) )
                    {
                        stages.add( binStages.getStage() );
                    }
                }
                dto.setStages( stages );
                dto.setUserId( inRequestDto.getUserId() );
                dto.setInboxStatus( CRMStatusCode.ACTIVE.getStatusCode() );
                dto = CRMServicesProxy.getInstance().getCRMConfigService( IGlobalConstants.LOCAL, IGlobalConstants.APP )
                        .getInbox( dto );
                LOGGER.info( dto.getStatusCode() );
                if ( !dto.getInboxLeadPojos().isEmpty() )
                {
                    LOGGER.info( dto.getInboxLeadPojos().size() );
                }
                if ( !dto.getGroupInboxLeadPojos().isEmpty() )
                {
                    LOGGER.info( dto.getGroupInboxLeadPojos().size() );
                }
                BeanUtils.copyProperties( responseDto, dto );
            }
            else if ( StringUtils.equals( IAppConstants.ASSIGN, inRequestDto.getOperation() ) )
            {
                ConfigDto configDto = new ConfigDto();
                configDto.setMappingId( inRequestDto.getLeadId() );
                configDto.setRequestType( inRequestDto.getRequestType() );
                configDto.setFromStage( inRequestDto.getCurrentStage() );
                configDto.setTostage( inRequestDto.getCurrentStage() );
                configDto.setUserId( inRequestDto.getUserId() );
                configDto.setClientIPAddress( httpRequest.getRemoteAddr() );
                configDto.setServerIPAddress( httpRequest.getRemoteAddr() );
                if ( StringUtils.equals( inRequestDto.getServiceParam(), CRMParameter.PERSONAL.getParameter() ) )
                {
                    configDto.setFromUserId( "" );
                    configDto.setToUserId( inRequestDto.getUserId() );
                }
                else if ( StringUtils.equals( inRequestDto.getServiceParam(), CRMParameter.GROUP.getParameter() ) )
                {
                    configDto.setFromUserId( inRequestDto.getUserId() );
                    configDto.setToUserId( "" );
                    LOGGER.info( "FromUserId : " + configDto.getFromUserId() + " to : " + configDto.getToUserId() );
                }
                configDto = CRMServicesProxy.getInstance()
                        .getCRMConfigService( IGlobalConstants.LOCAL, IGlobalConstants.APP ).changeInboxBin( configDto );
                BeanUtils.copyProperties( responseDto, configDto );
            }
        }
        catch ( Exception ex )
        {
            serviceCode = CRMServiceCode.CRM999;
            LOGGER.error( "Exception", ex );
        }
        finally
        {
            if ( !StringUtils.isValidObj( responseDto.getStatusCode() ) || responseDto.getStatusCode().isEmpty() )
            {
                responseDto.setStatusCode( serviceCode.getStatusCode() );
                responseDto.setStatusDesc( serviceCode.getStatusDesc() );
            }
        }
        return responseDto;
    }

    @ResponseBody
    @RequestMapping(value = URI_PATH_LMS_ACTION, method = RequestMethod.POST, consumes = APPLICATION_JSON)
    public MasterResponsedto masterData( @RequestHeader Map httpHeaders,
                                         @RequestBody @Valid MasterRequestDto inRequestDto,
                                         BindingResult inBindingResult,
                                         HttpServletRequest httpRequest )
    {
        MasterResponsedto responseDto = new MasterResponsedto();
        MasterDataDto dto = new MasterDataDto();
        try
        {
            if ( StringUtils.equals( IAppConstants.PARTNER, inRequestDto.getOperation() ) )
            {
                PartnerPojo pojo = new PartnerPojo();
                pojo.setCurrentStatus( CRMStatusCode.ACTIVE.getStatusCode() );
                pojo.setPartnerType( CRMDisplayListConstants.BUSINESS_PARTNER.getCode() );
                dto.setPartnerPojo( pojo );
                dto = CRMServicesProxy.getInstance()
                        .getCRMMasterDataService( IGlobalConstants.LOCAL, IGlobalConstants.APP )
                        .getMasterData( dto, CRMParameter.PARTNER_POJO.getParameter() );
                BeanUtils.copyProperties( responseDto, dto );
                for ( PartnerPojo partnerPojo : responseDto.getPartnerPojoList() )
                {
                    partnerPojo.getCrmPartnerDetailses().clear();
                }
            }
            else if ( StringUtils.equals( ServiceParameter.CLOSE.getParameter(), inRequestDto.getOperation() ) )
            {
                CrmRcaReasonPojo pojo = new CrmRcaReasonPojo();
                pojo.setCategory( CRMRCAReason.LMS.getStatusCode() );
                pojo.setSubCategory( CRMRCAReason.LMS_REASON.getStatusCode() );
                pojo.setSubSubCategory( CRMRCAReason.LMS_REASON_CLOSE.getStatusCode() );
                pojo.setStatus( CRMStatusCode.ACTIVE.getStatusCode() );
                dto.setCrmRcaReason( pojo );
                dto = CRMServicesProxy
                        .getInstance()
                        .getCRMMasterDataService( IGlobalConstants.LOCAL, IGlobalConstants.APP )
                        .masterOperations( ServiceParameter.LIST.getParameter(),
                                           CRMParameter.CRM_RCA_REASON.getParameter(), dto );
                LOGGER.info( "List Size:::" + dto.getCrmRcaReasonsList().size() );
                BeanUtils.copyProperties( responseDto, dto );
            }
        }
        catch ( Exception ex )
        {
            LOGGER.error( "Exception", ex );
        }
        return responseDto;
    }
}
