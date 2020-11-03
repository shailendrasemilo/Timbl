package com.np.tele.crm.usrmngmnt.bm;

import java.util.ArrayList;
import java.util.List;

import org.directwebremoting.util.Logger;

import com.np.tele.crm.constants.CRMParameter;
import com.np.tele.crm.constants.CRMServiceCode;
import com.np.tele.crm.constants.IAppConstants;
import com.np.tele.crm.constants.ServiceParameter;
import com.np.tele.crm.service.client.CRMUserManagement;
import com.np.tele.crm.service.client.CrmUserMappingPojo;
import com.np.tele.crm.service.client.CrmUserPojo;
import com.np.tele.crm.service.client.CrmuserDetailsDto;
import com.np.tele.crm.service.client.SOAPException_Exception;
import com.np.tele.crm.usrmngmnt.forms.EWUserMappingForm;
import com.np.tele.crm.utils.StringUtils;

public class EWUserMappingImpl
    implements IEWUserMappingMgr
{
    private CRMUserManagement   crmUserClient;
    private static final Logger LOGGER = Logger.getLogger( EWUserMappingImpl.class );

    public CRMUserManagement getCrmUserClient()
    {
        return crmUserClient;
    }

    public void setCrmUserClient( CRMUserManagement inCrmUserClient )
    {
        crmUserClient = inCrmUserClient;
    }

    @Override
    public CrmuserDetailsDto searchUserById( EWUserMappingForm inMappingForm )
    {
        CrmuserDetailsDto detailsDto = new CrmuserDetailsDto();
        try
        {
            CrmUserPojo crmUserPojo = new CrmUserPojo();
            crmUserPojo.setUserId( inMappingForm.getUserId() );
            if ( StringUtils.isNotEmpty( inMappingForm.getSearchStatus() ) )
            {
                crmUserPojo.setStatus( inMappingForm.getSearchStatus() );
            }
            if ( StringUtils.isNotEmpty( inMappingForm.getFunctionalBin() ) )
            {
                crmUserPojo.setFunctionalBin( inMappingForm.getFunctionalBin() );
            }
            /*if ( StringUtils.isNotEmpty( inMappingForm.getEmpType() ) )
            {
                if ( inMappingForm.getEmpType().equals( IAppConstants.EMP_TYPE_NEXTRA ) )
                {
                    crmUserPojo.setEmpType( CRMParameter.NEXTRA.getParameter() );
                }
                else if ( inMappingForm.getEmpType().equals( IAppConstants.EMP_TYPE_PARTNER ) )
                {
                    crmUserPojo.setEmpType( CRMParameter.PARTNER.getParameter() );
                }
            }*/
            detailsDto.setCrmUserDetailsPojo( crmUserPojo );
            detailsDto = getCrmUserClient().searchUser( detailsDto );
        }
        catch ( SOAPException_Exception ex )
        {
            LOGGER.error( "Error While Calling service method for search user." + ex );
        }
        return detailsDto;
    }

    @Override
    public CrmuserDetailsDto searchEWUserMapping( EWUserMappingForm inMappingForm )
    {
        CrmuserDetailsDto crmuserDetailsDto = new CrmuserDetailsDto();
        CrmUserMappingPojo crmUserMappingPojo = new CrmUserMappingPojo();
        crmUserMappingPojo.setUserId( inMappingForm.getHiddenUserId() );
        crmUserMappingPojo.setMappingType( inMappingForm.getMappingType() );
        crmuserDetailsDto.setCrmUserMappingPojo( crmUserMappingPojo );
        LOGGER.info( "User Id:" + inMappingForm.getHiddenUserId() );
        LOGGER.info( "Mapping Type:" + inMappingForm.getMappingType() );
        try
        {
            crmuserDetailsDto = getCrmUserClient().userEscalationAndWorkflowMapping( crmuserDetailsDto,
                                                                                     ServiceParameter.LIST
                                                                                             .getParameter() );
        }
        catch ( SOAPException_Exception ex )
        {
            LOGGER.error( "Error While Calling service method for EW search user." + ex );
        }
        return crmuserDetailsDto;
    }

    @Override
    public CrmuserDetailsDto createEWUserMapping( EWUserMappingForm inMappingForm, String inUserId )
    {
        List<CrmUserMappingPojo> crMappingPojosList = new ArrayList<CrmUserMappingPojo>();
        CrmuserDetailsDto crmuserDetailsDto = new CrmuserDetailsDto();
        if ( StringUtils.isValidObj( inMappingForm.getCrmUserMappingPojosList() ) )
        {
            for ( CrmUserMappingPojo crmUserMappingPojo : inMappingForm.getCrmUserMappingPojosList() )
            {
                LOGGER.info( "User ID: " + crmUserMappingPojo.getUserId() );
                LOGGER.info( "Mapping User ID: " + crmUserMappingPojo.getMappedUserId() );
                LOGGER.info( "Mapping Type: " + crmUserMappingPojo.getMappingType() );
                LOGGER.info( "Loging User" + inUserId );
                LOGGER.info( "Editable Value" + crmUserMappingPojo.isEditable() );
                if ( crmUserMappingPojo.isEditable() )
                {
                    if ( crmUserMappingPojo.getUserMappingId() > 0 )
                        crmUserMappingPojo.setLastModifiedBy( inUserId );
                    else
                        crmUserMappingPojo.setCreatedBy( inUserId );
                    crMappingPojosList.add( crmUserMappingPojo );
                }
            }
            if ( StringUtils.isValidObj( crMappingPojosList ) && crMappingPojosList.size() > 0 )
            {
                crmuserDetailsDto.getCrMappingPojosList().addAll( crMappingPojosList );
                try
                {
                    crmuserDetailsDto = getCrmUserClient().userEscalationAndWorkflowMapping( crmuserDetailsDto,
                                                                                             ServiceParameter.CREATE
                                                                                                     .getParameter() );
                }
                catch ( SOAPException_Exception ex )
                {
                    LOGGER.error( "Error While Calling service method for create EW user mapping." + ex );
                }
            }
            else
            {
                crmuserDetailsDto.setStatusCode( CRMServiceCode.CRM993.getStatusCode() );
                crmuserDetailsDto.setStatusDesc( CRMServiceCode.CRM993.getStatusDesc() );
            }
        }
        return crmuserDetailsDto;
    }
}
