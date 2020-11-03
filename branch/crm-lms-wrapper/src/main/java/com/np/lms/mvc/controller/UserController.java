package com.np.lms.mvc.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;
import javax.xml.soap.SOAPException;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.np.lms.rest.dto.UserRequestDto;
import com.np.lms.rest.dto.UserResponseDto;
import com.np.tele.crm.constants.CRMParameter;
import com.np.tele.crm.constants.CRMRCAReason;
import com.np.tele.crm.constants.CRMServiceCode;
import com.np.tele.crm.constants.CrmActionEnum;
import com.np.tele.crm.constants.IAppConstants;
import com.np.tele.crm.constants.IGlobalConstants;
import com.np.tele.crm.constants.IPropertiesConstant;
import com.np.tele.crm.constants.ServiceParameter;
import com.np.tele.crm.dto.ConfigDto;
import com.np.tele.crm.dto.CrmuserDetailsDto;
import com.np.tele.crm.dto.MasterDataDto;
import com.np.tele.crm.locator.CRMServicesProxy;
import com.np.tele.crm.pojos.ContentPojo;
import com.np.tele.crm.pojos.CrmRcaReasonPojo;
import com.np.tele.crm.pojos.RolesPojo;
import com.np.tele.crm.utils.ICRMValidationCriteriaUtil;
import com.np.tele.crm.utils.StringUtils;
import com.np.validator.util.ValidationPojoUtil;

@Controller()
@SuppressWarnings("rawtypes")
public class UserController
{
    private static final Logger LOGGER            = Logger.getLogger( UserController.class );
    private static final String URI_PATH_LOGIN    = "/login";
    private static final String URI_PATH_PASSWORD = "/password";
    private static final String APPLICATION_JSON  = "application/json";

    @ResponseBody
    @RequestMapping(value = URI_PATH_LOGIN, method = RequestMethod.POST, consumes = APPLICATION_JSON)
    public UserResponseDto login( @RequestHeader Map httpHeaders,
                                  @RequestBody @Valid UserRequestDto inRequestDto,
                                  BindingResult inBindingResult )
    {
        CRMServiceCode serviceCode = CRMServiceCode.CRM997;
        UserResponseDto responseDto = new UserResponseDto();
        List<ContentPojo> binPojos = new ArrayList<ContentPojo>();
        CrmuserDetailsDto dto = new CrmuserDetailsDto();
        try
        {
            boolean isValid = ValidationPojoUtil.validatePojo( inRequestDto,
                                                               ICRMValidationCriteriaUtil.USER_WRAPPER_CRITERIA ) == null;
            if ( isValid )
            {
                BeanUtils.copyProperties( dto, inRequestDto );
                dto = CRMServicesProxy.getInstance()
                        .getUserManagementService( IGlobalConstants.LOCAL, IGlobalConstants.APP )
                        .loginAuthentication( dto );
                BeanUtils.copyProperties( responseDto, dto );
                getFunctionalBins( binPojos, dto );
                responseDto.setBinPojos( binPojos );
            }
        }
        catch ( Exception ex )
        {
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

    private void getFunctionalBins( List<ContentPojo> binPojos, CrmuserDetailsDto dto )
        throws SOAPException
    {
        String binArray[] = new RolesPojo().getFunctionalBin( dto.getCrmUserDetailsPojo().getFunctionalBin() )
                .split( "," );
        MasterDataDto masterdto = new MasterDataDto();
        CrmRcaReasonPojo reasonPojo = new CrmRcaReasonPojo();
        reasonPojo.setCategory( CRMRCAReason.CRM.getStatusCode() );
        reasonPojo.setSubCategory( CRMRCAReason.CRM_MASTER.getStatusCode() );
        reasonPojo.setSubSubCategory( CRMRCAReason.FUNCTIONAL_BIN.getStatusCode() );
        masterdto.setCrmRcaReason( reasonPojo );
        masterdto = CRMServicesProxy
                .getInstance()
                .getCRMMasterDataService( IGlobalConstants.LOCAL, IGlobalConstants.APP )
                .masterOperations( ServiceParameter.LIST.getParameter(), CRMParameter.CRM_RCA_REASON.getParameter(),
                                   masterdto );
        for ( CrmRcaReasonPojo pojo : masterdto.getCrmRcaReasonsList() )
        {
            if ( Arrays.asList( binArray ).contains( String.valueOf( pojo.getCategoryId() ) ) )
            {
                ContentPojo contentPojo = new ContentPojo( pojo.getCategoryValue(), String.valueOf( pojo
                        .getCategoryId() ) );
                binPojos.add( contentPojo );
            }
        }
    }

    @ResponseBody
    @RequestMapping(value = URI_PATH_PASSWORD, method = RequestMethod.POST, consumes = APPLICATION_JSON)
    public UserResponseDto password( @RequestHeader Map httpHeaders,
                                     @RequestBody @Valid UserRequestDto inRequestDto,
                                     BindingResult inBindingResult )
    {
        CRMServiceCode serviceCode = CRMServiceCode.CRM997;
        UserResponseDto responseDto = new UserResponseDto();
        CrmuserDetailsDto dto = new CrmuserDetailsDto();
        try
        {
            if ( StringUtils.isValidObj( inRequestDto )
                    && StringUtils.equals( IAppConstants.CHANGE_PASSWORD, inRequestDto.getOpretaion() ) )
            {
                boolean isValid = ValidationPojoUtil.validatePojo( inRequestDto,
                                                                   ICRMValidationCriteriaUtil.CHANGE_PASSWORD_WRAPPER_CRITERIA ) == null;;
                if ( isValid )
                {
                    BeanUtils.copyProperties( dto, inRequestDto );
                    dto = CRMServicesProxy.getInstance()
                            .getUserManagementService( IGlobalConstants.LOCAL, IGlobalConstants.APP )
                            .changePassword( dto );
                    BeanUtils.copyProperties( responseDto, dto );
                }
            }
            else if ( StringUtils.isValidObj( inRequestDto )
                    && StringUtils.equals( CRMParameter.FORGET_PASSWORD.getParameter(), inRequestDto.getOpretaion() ) )
            {
                boolean isValid = ValidationPojoUtil.validatePojo( inRequestDto.getCrmUserDetailsPojo(),
                                                                   ICRMValidationCriteriaUtil.FORGOT_PASSWORD_WRAPPER_CRITERIA ) == null;;
                if ( isValid )
                {
                    BeanUtils.copyProperties( dto, inRequestDto );
                    dto = CRMServicesProxy.getInstance()
                            .getUserManagementService( IGlobalConstants.LOCAL, IGlobalConstants.APP )
                            .userOperations( dto, inRequestDto.getOpretaion() );
                    BeanUtils.copyProperties( responseDto, dto );
                }
            }
        }
        catch ( Exception ex )
        {
            LOGGER.error( "Exception occured", ex );
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
}
