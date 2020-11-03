package com.np.tele.crm.masterdata.bm;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.np.tele.crm.constants.CRMParameter;
import com.np.tele.crm.constants.IAppConstants;
import com.np.tele.crm.constants.ServiceParameter;
import com.np.tele.crm.masterdata.forms.CrmRcaReasonForm;
import com.np.tele.crm.service.client.CrmHolidayDetails;
import com.np.tele.crm.service.client.CrmRcaReasonPojo;
import com.np.tele.crm.service.client.MasterData;
import com.np.tele.crm.service.client.MasterDataDto;
import com.np.tele.crm.service.client.SOAPException_Exception;
import com.np.tele.crm.utils.DateUtils;
import com.np.tele.crm.utils.StringUtils;

public class CrmRcaReasonMgrImpl
    implements ICrmRcaReasonMgr
{
    private static final Logger LOGGER = Logger.getLogger( CrmRcaReasonMgrImpl.class );
    private MasterData          masterDataClient;

    public MasterData getMasterDataClient()
    {
        return masterDataClient;
    }

    public void setMasterDataClient( MasterData inMasterDataClient )
    {
        masterDataClient = inMasterDataClient;
    }

    @Override
    public MasterDataDto createCategoryValues( CrmRcaReasonForm inCrmRcaReasonForm, String inUserId )
    {
        List<CrmRcaReasonPojo> crmRcaReasonsList = new ArrayList<CrmRcaReasonPojo>();
        MasterDataDto masterDataDto = new MasterDataDto();
        if ( StringUtils.isValidObj( inCrmRcaReasonForm.getCrmRcaReasonsList() ) )
        {
            for ( CrmRcaReasonPojo crmRcaReason : inCrmRcaReasonForm.getCrmRcaReasonsList() )
            {
                setCategoriesByForm( crmRcaReason, inCrmRcaReasonForm );
                if ( StringUtils.equals( crmRcaReason.getModificationAllowed(), IAppConstants.YES_CHAR )
                        && crmRcaReason.isEditable() )
                {
                    crmRcaReasonsList.add( crmRcaReason );
                    if ( crmRcaReason.getCategoryId() > 0 )
                    {
                        crmRcaReason.setLastModifiedBy( inUserId );
                    }
                    else
                    {
                        crmRcaReason.setCreatedBy( inUserId );
                    }
                }
            }
            if ( StringUtils.isValidObj( crmRcaReasonsList ) && crmRcaReasonsList.size() > 0 )
            {
                masterDataDto.getCrmRcaReasonsList().addAll( crmRcaReasonsList );
                try
                {
                    masterDataDto = getMasterDataClient().masterOperations( ServiceParameter.CREATE.getParameter(),
                                                                            CRMParameter.CRM_RCA_REASON.getParameter(),
                                                                            masterDataDto );
                }
                catch ( SOAPException_Exception ex )
                {
                    LOGGER.info( "Getting Error While, creating Category Value: " + ex );
                }
            }
        }
        return masterDataDto;
    }

    @Override
    public void setCategoriesByForm( CrmRcaReasonPojo inCrmRcaReasonPojo, CrmRcaReasonForm inCrmRcaReasonForm )
    {
        inCrmRcaReasonPojo.setCategory( inCrmRcaReasonForm.getCrmRcaReason().getCategory() );
        inCrmRcaReasonPojo.setSubCategory( inCrmRcaReasonForm.getCrmRcaReason().getSubCategory() );
        inCrmRcaReasonPojo.setSubSubCategory( inCrmRcaReasonForm.getCrmRcaReason().getSubSubCategory() );
    }

    @Override
    public MasterDataDto searchCategoryValue( CrmRcaReasonForm inCrmRcaReasonForm )
    {
        MasterDataDto masterDataDto = new MasterDataDto();
        inCrmRcaReasonForm.getCrmRcaReason().setStatus( inCrmRcaReasonForm.getStatus() );
        inCrmRcaReasonForm.getCrmRcaReason().setValueAlias( inCrmRcaReasonForm.getUsedUnusedStatus() );
        masterDataDto.setCrmRcaReason( inCrmRcaReasonForm.getCrmRcaReason() );
        if ( StringUtils.isNotBlank( inCrmRcaReasonForm.getStartRangeString() ) )
        {
            masterDataDto.setCategoryValueRangeStart( inCrmRcaReasonForm.getStartRangeString() );
            if ( StringUtils.isNotBlank( inCrmRcaReasonForm.getEndRangeString() ) )
            {
                masterDataDto.setCategoryValueRangeEnd( inCrmRcaReasonForm.getEndRangeString() );
            }
        }
        if ( StringUtils.isNotBlank( inCrmRcaReasonForm.getPrefix() ) )
        {
            masterDataDto.setCategoryValuePrefix( inCrmRcaReasonForm.getPrefix() );
        }
        try
        {
            masterDataDto = getMasterDataClient().masterOperations( ServiceParameter.LIST.getParameter(),
                                                                    CRMParameter.CRM_RCA_REASON.getParameter(),
                                                                    masterDataDto );
        }
        catch ( SOAPException_Exception ex )
        {
            LOGGER.info( "Getting Error While, Fetching Category Value: " + ex );
        }
        return masterDataDto;
    }

    @Override
    public MasterDataDto addRecietValues( List<CrmRcaReasonPojo> incrmRcaReasonsList )
    {
        MasterDataDto masterDataDto = new MasterDataDto();
        try
        {
            if ( StringUtils.isValidObj( incrmRcaReasonsList ) && !incrmRcaReasonsList.isEmpty() )
            {
                masterDataDto.getCrmRcaReasonsList().addAll( incrmRcaReasonsList );
                LOGGER.info( "Size..........." + masterDataDto.getCrmRcaReasonsList().size() );
                masterDataDto = getMasterDataClient().masterOperations( ServiceParameter.CREATE.getParameter(),
                                                                        CRMParameter.CRM_RCA_REASON.getParameter(),
                                                                        masterDataDto );
                LOGGER.info( masterDataDto.getStatusCode() );
            }
        }
        catch ( SOAPException_Exception ex )
        {
            LOGGER.info( "Getting Error While, Fetching Category Value: " + ex );
        }
        return masterDataDto;
    }

    @Override
    public MasterDataDto changeStatus( CrmRcaReasonForm inCrmRcaReasonForm, String inModifiedBy )
    {
        MasterDataDto masterDataDto = new MasterDataDto();
        try
        {
            inCrmRcaReasonForm.getCrmRcaReason().setLastModifiedBy( inModifiedBy );
            masterDataDto.getCrmRcaReasonsList().add( inCrmRcaReasonForm.getCrmRcaReason() );
            masterDataDto = getMasterDataClient().masterOperations( ServiceParameter.CREATE.getParameter(),
                                                                    CRMParameter.CRM_RCA_REASON.getParameter(),
                                                                    masterDataDto );
            LOGGER.info( masterDataDto.getStatusCode() );
        }
        catch ( SOAPException_Exception ex )
        {
            LOGGER.info( "Getting Error While, Fetching Category Value: ", ex );
        }
        return masterDataDto;
    }

    @Override
    public MasterDataDto getHolidaysList()
    {
        MasterDataDto masterDataDto = new MasterDataDto();
        try
        {
            masterDataDto = getMasterDataClient().masterOperations( ServiceParameter.LIST.getParameter(),
                                                                    CrmHolidayDetails.class.getSimpleName(),
                                                                    masterDataDto );
        }
        catch ( SOAPException_Exception ex )
        {
            LOGGER.info( "Getting Error While, Fetching holiday list: ", ex );
        }
        return masterDataDto;
    }

    @Override
    public MasterDataDto postHoliday( CrmRcaReasonForm inCrmRcaReasonForm, String inUserId )
    {
        MasterDataDto masterDataDto = new MasterDataDto();
        try
        {
            masterDataDto.setUserID( inUserId );
            inCrmRcaReasonForm.getCrmHolidayPojo().setHolidayDate( DateUtils.changeDateFormat( inCrmRcaReasonForm.getDate() ) );
            masterDataDto.setCrmHolidayPojo( inCrmRcaReasonForm.getCrmHolidayPojo() );
            masterDataDto = getMasterDataClient().masterOperations( ServiceParameter.CREATE.getParameter(),
                                                                    CrmHolidayDetails.class.getSimpleName(),
                                                                    masterDataDto );
        }
        catch ( SOAPException_Exception ex )
        {
            LOGGER.info( "Getting Error While, posting holiday: ", ex );
        }
        return masterDataDto;
    }
}
