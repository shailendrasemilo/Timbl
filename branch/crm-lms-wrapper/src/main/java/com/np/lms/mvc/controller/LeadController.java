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

import com.np.lms.rest.dto.LeadRequestDto;
import com.np.lms.rest.dto.LeadResponsedto;
import com.np.tele.crm.constants.CRMActionConstants;
import com.np.tele.crm.constants.CRMServiceCode;
import com.np.tele.crm.constants.CRMStatusCode;
import com.np.tele.crm.constants.IAppConstants;
import com.np.tele.crm.constants.IGlobalConstants;
import com.np.tele.crm.constants.ServiceParameter;
import com.np.tele.crm.dto.LMSDto;
import com.np.tele.crm.locator.CRMServicesProxy;
import com.np.tele.crm.pojos.LMSPojo;
import com.np.tele.crm.pojos.RemarksPojo;
import com.np.tele.crm.utils.ICRMValidationCriteriaUtil;
import com.np.tele.crm.utils.StringUtils;
import com.np.validator.util.ValidationPojoUtil;

@Controller()
@SuppressWarnings("rawtypes")
public class LeadController
{
    private static final Logger LOGGER           = Logger.getLogger( LeadController.class );
    private static final String URI_PATH_LEAD    = "/lead";
    private static final String APPLICATION_JSON = "application/json";

    @ResponseBody
    @RequestMapping(value = URI_PATH_LEAD, method = RequestMethod.POST, consumes = APPLICATION_JSON)
    public LeadResponsedto lead( @RequestHeader Map httpHeaders,
                                 @RequestBody @Valid LeadRequestDto inRequestDto,
                                 BindingResult inBindingResult,
                                 HttpServletRequest httpRequest )
    {
        CRMServiceCode serviceCode = CRMServiceCode.CRM997;
        LOGGER.info( "Inside lead" );
        LeadResponsedto leadResponseDto = new LeadResponsedto();
        try
        {
            if ( StringUtils.isValidObj( inRequestDto )
                    && StringUtils.equals( ServiceParameter.CREATE.getParameter(), inRequestDto.getOperation() ) )
            {
                boolean isValid = ValidationPojoUtil.validatePojo( inRequestDto.getLeadPojo(),
                                                                   ICRMValidationCriteriaUtil.LMS_WRAPPER_CRITERIA ) == null;
                if ( isValid )
                {
                    LMSDto dto = new LMSDto();
                    BeanUtils.copyProperties( dto, inRequestDto );
                    preparedLeadPojo( inRequestDto, dto );
                    dto.setClientIPAddress( httpRequest.getRemoteAddr() );
                    dto.setServerIPAddress( httpRequest.getRemoteAddr() );
                    dto = CRMServicesProxy.getInstance().getLmsService( IGlobalConstants.LOCAL, IGlobalConstants.APP )
                            .lmsOperation( inRequestDto.getOperation(), dto );
                    BeanUtils.copyProperties( leadResponseDto, dto );
                }
            }
            else if ( StringUtils.isValidObj( inRequestDto )
                    && ( StringUtils.equals( ServiceParameter.LIST.getParameter(), inRequestDto.getOperation() ) ) )
            {
                boolean isValid = ValidationPojoUtil
                        .validatePojo( inRequestDto.getLeadPojo(),
                                       ICRMValidationCriteriaUtil.LMS_WRAPPER_SEARCH_CRITERIA ) == null;
                if ( isValid )
                {
                    LMSDto dto = new LMSDto();
                    BeanUtils.copyProperties( dto, inRequestDto );
                    dto = CRMServicesProxy.getInstance().getLmsService( IGlobalConstants.LOCAL, IGlobalConstants.APP )
                            .lmsOperation( inRequestDto.getOperation(), dto );
                    BeanUtils.copyProperties( leadResponseDto, dto );
                }
            }
            else if ( StringUtils.isValidObj( inRequestDto )
                    && ( StringUtils.equals( IAppConstants.LEAD_ACTION, inRequestDto.getOperation() ) ) )
            {
                LOGGER.info( "Operateion"+inRequestDto.getAction() );
                
                String operation=null;
                LMSDto lmsDto = new LMSDto();
                if ( StringUtils.equals( ServiceParameter.CLOSE.getParameter(), inRequestDto.getAction() ) )
                {
                    
                    inRequestDto.getRemarksPojo().setActions( CRMActionConstants.CLOSE_LEAD.getStoringValue() );
                    lmsDto.setToStage( CRMActionConstants.CLOSE_LEAD.getStoringValue() );
                  
                    operation=  ServiceParameter.CLOSE.getParameter();
                }
                else if ( StringUtils.equals( ServiceParameter.MATURE.getParameter(), inRequestDto.getAction() ) )
                {
                    inRequestDto.getRemarksPojo().setActions( CRMActionConstants.MATURE_LEAD.getStoringValue() );
                    lmsDto.setToStage( CRMActionConstants.MATURE_LEAD.getForStage() );
                    operation=  ServiceParameter.MATURE.getParameter();
                }
                else if ( StringUtils.equals( CRMActionConstants.BACKWARD_SC.getStoringValue(),
                                              inRequestDto.getAction() ) )
                {
                    inRequestDto.getRemarksPojo().setActions( CRMActionConstants.BACKWARD_SC.getStoringValue() );
                    lmsDto.setToStage( inRequestDto.getRemarksPojo().getActions() );
                    lmsDto.setToUserId( "" );
                    operation=  ServiceParameter.FORWARD.getParameter();
                }
                else if ( StringUtils.equals( CRMActionConstants.FORWARD_Sales.getStoringValue(), inRequestDto.getAction() ) )
                {
                    lmsDto.setToUserId( "" );
                    inRequestDto.getRemarksPojo().setActions( CRMActionConstants.FORWARD_Sales.getStoringValue() );
                    operation=  ServiceParameter.FORWARD.getParameter();
                }
                else if ( StringUtils.equals( CRMActionConstants.SAVE_REMARKS.getDisplayValue(),
                                              inRequestDto.getAction() ) )
                {
                  operation=  ServiceParameter.SAVE.getParameter();
                }
                lmsDto.setRemarksPojo( inRequestDto.getRemarksPojo() );
                lmsDto.setLeadPojo( inRequestDto.getLeadPojo() );
                if ( StringUtils.isNotEmpty( inRequestDto.getAppointmentPojo().getDisplayDate() ) )
                {
                    lmsDto.setAppointmentPojo( inRequestDto.getAppointmentPojo() );
                }
                
                lmsDto = CRMServicesProxy.getInstance().getLmsService( IGlobalConstants.LOCAL, IGlobalConstants.APP )
                        .lmsOperation( operation, lmsDto );
                System.out.println(lmsDto.getStatusCode());
            }
        }
        catch ( Exception ex )
        {
            LOGGER.error( "Exception", ex );
        }
        finally
        {
            if ( !StringUtils.isValidObj( leadResponseDto.getStatusCode() )
                    || leadResponseDto.getStatusCode().isEmpty() )
            {
                leadResponseDto.setStatusCode( serviceCode.getStatusCode() );
                leadResponseDto.setStatusDesc( serviceCode.getStatusDesc() );
            }
        }
        return leadResponseDto;
    }

    private void preparedLeadPojo( LeadRequestDto inRequestDto, LMSDto dto )
    {
        List<LMSPojo> pojos = new ArrayList<LMSPojo>();
        pojos.add( inRequestDto.getLeadPojo() );
        dto.setLeadPojos( pojos );
        RemarksPojo pojo = new RemarksPojo();
        pojo.setActions( CRMActionConstants.CREATE_LEAD.getStoringValue() );
        pojo.setRemarks( IAppConstants.AUTO_REMARKS );
        pojo.setCreatedBy( inRequestDto.getLeadPojo().getCreatedBy() );
        dto.setRemarksPojo( pojo );
    }
}
