package com.np.tele.crm.masterdata.action;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.actions.DispatchAction;

import com.np.tele.crm.common.utils.CRMCacheManager;
import com.np.tele.crm.constants.CRMDisplayListConstants;
import com.np.tele.crm.constants.CRMParameter;
import com.np.tele.crm.constants.CRMServiceCode;
import com.np.tele.crm.constants.CRMStatusCode;
import com.np.tele.crm.constants.IActionForwardConstant;
import com.np.tele.crm.constants.IAppConstants;
import com.np.tele.crm.constants.ServiceParameter;
import com.np.tele.crm.masterdata.bm.IMasterBMngr;
import com.np.tele.crm.masterdata.forms.MasterForm;
import com.np.tele.crm.service.client.CrmParameterPojo;
import com.np.tele.crm.service.client.CrmPartnerDetailsPojo;
import com.np.tele.crm.service.client.CrmPartnerNetworkConfigPojo;
import com.np.tele.crm.service.client.CrmuserDetailsDto;
import com.np.tele.crm.service.client.MasterDataDto;
import com.np.tele.crm.service.client.PartnerPojo;
import com.np.tele.crm.service.comparators.PojoComparator;
import com.np.tele.crm.utils.CRMUtils;
import com.np.tele.crm.utils.StringUtils;
import com.np.validator.util.CommonValidator;

public class Option82ManagementAction
    extends DispatchAction
{
    private static final Logger LOGGER = Logger.getLogger( Option82ManagementAction.class );
    private IMasterBMngr        masterDataBm;

    public IMasterBMngr getMasterDataBm()
    {
        return masterDataBm;
    }

    public void setMasterDataBm( IMasterBMngr masterDataBm )
    {
        this.masterDataBm = masterDataBm;
    }

    public ActionForward option82Page( ActionMapping mapping,
                                       ActionForm form,
                                       HttpServletRequest request,
                                       HttpServletResponse response )
    {
        ActionMessages messages = getMessages( request );
        ActionMessages errors = getErrors( request );
        try
        {
            MasterForm masterForm = (MasterForm) form;
            masterForm.setPartnerList( CRMCacheManager.getNetworkPartners() );
            masterForm.setOltTypeList( CRMUtils.getOltType() );
            List<CrmParameterPojo> crmParameterList = getMasterDataBm().getParameters( 1,
                                                                                       CRMParameter.OPTION82,
                                                                                       CRMParameter.INTERNAL
                                                                                               .getParameter() );
            masterForm.setCrmParameterList( crmParameterList );
            LOGGER.info( "CRM Parameter" + crmParameterList.size() );
        }
        catch ( Exception ex )
        {
            LOGGER.error( "Exception occured:", ex );
            errors.add( IAppConstants.APP_ERROR, new ActionMessage( CRMServiceCode.CRM999.getStatusCode() ) );
        }
        saveMessages( request, messages );
        saveErrors( request, errors );
        return mapping.findForward( IActionForwardConstant.OPTION_82_PAGE );
    }

    public ActionForward searchOption82Page( ActionMapping mapping,
                                             ActionForm form,
                                             HttpServletRequest request,
                                             HttpServletResponse response )
    {
        LOGGER.info( "option82Operation" );
        ActionMessages messages = getMessages( request );
        ActionMessages errors = getErrors( request );
        MasterForm masterForm = (MasterForm) form;
        CrmuserDetailsDto userDto = (CrmuserDetailsDto) request.getSession()
                .getAttribute( IAppConstants.CRM_USER_OBJECT );
        List<CrmParameterPojo> option82PojoList = new ArrayList<CrmParameterPojo>( 7 );
        long partnerDetailsId = 0;
        MasterDataDto masterDataDto;
        CrmPartnerNetworkConfigPojo crmPartnerNetworkConfigPojo = new CrmPartnerNetworkConfigPojo();
        masterForm.setCrmPartnerNetworkConfigPojos( null );
        request.setAttribute( "showDivvValue", masterForm.getShowDivValue() );
        partnerDetailsId = getPartnerDetailsId( masterForm );
        masterForm.setProductTypeList( getMasterDataBm()
                .getProductsByPartnerId( masterForm.getPartnerId() + "",
                                         CRMDisplayListConstants.NETWORK_PARTNER.getCode() ) );
        if ( partnerDetailsId > 0 && masterForm.getPartnerId() > 0 )
        {
            LOGGER.info( "OPERATION 82 partnerDetailsId....................." + partnerDetailsId );
            crmPartnerNetworkConfigPojo.setOltType( masterForm.getOltType() );
            masterDataDto = searchOption82NassportId( partnerDetailsId, masterForm.getBusinessType(), userDto
                    .getCrmUserDetailsPojo().getUserId(), masterForm.getOltType() );
            //LOGGER.info( "OPERATION 82 SEARCHED POJOS & masterData:" + masterDataDto );
            List<CrmPartnerNetworkConfigPojo> dbCrmPartnerNetworkConfigPojos = null;
            if ( StringUtils.isValidObj( masterDataDto )
                    && CommonValidator.isValidCollection( masterDataDto.getCrmPartnerNetworkConfigPojos() ) )
            {
                dbCrmPartnerNetworkConfigPojos = masterDataDto.getCrmPartnerNetworkConfigPojos();
            }
            else if ( StringUtils.equals( masterForm.getBusinessType(), CRMDisplayListConstants.BROADBAND.getCode() ) )
            {
                List<CrmParameterPojo> crmParameterList = getMasterDataBm().getParameters( 1,
                                                                                           CRMParameter.OPTION82,
                                                                                           CRMParameter.INTERNAL
                                                                                                   .getParameter() );
                masterForm.setCrmParameterList( crmParameterList );
                dbCrmPartnerNetworkConfigPojos = new ArrayList<CrmPartnerNetworkConfigPojo>();
                for ( int i = 1; i <= 7; i++ )
                {
                    CrmPartnerNetworkConfigPojo configPojo = new CrmPartnerNetworkConfigPojo();
                    configPojo.setParameterId( 0L );
                    configPojo.setParameterSequenceNo( (byte) i );
                    configPojo.setPartnerDetailsId( partnerDetailsId );
                    configPojo.setOltType( masterForm.getOltType() );
                    dbCrmPartnerNetworkConfigPojos.add( configPojo );
                }
            }
            masterForm.setCrmPartnerNetworkConfigPojos( dbCrmPartnerNetworkConfigPojos );
        }
        masterForm.setProductTypeList( getMasterDataBm()
                .getProductsByPartnerId( masterForm.getPartnerId().toString(),
                                         CRMDisplayListConstants.NETWORK_PARTNER.getCode() ) );
        saveMessages( request, messages );
        saveErrors( request, errors );
        return mapping.findForward( IActionForwardConstant.OPTION_82_PAGE );
    }

    public ActionForward option82Operation( ActionMapping mapping,
                                            ActionForm form,
                                            HttpServletRequest request,
                                            HttpServletResponse response )
    {
        LOGGER.info( "option82Operation" );
        long partnerDetailsId = 0;
        ActionMessages messages = new ActionMessages();
        ActionMessages errors = new ActionMessages();
        String forwardPage = IActionForwardConstant.OPTION_82_PAGE;
        try
        {
            MasterForm masterForm = (MasterForm) form;
            MasterDataDto masterDataDto = new MasterDataDto();
            CrmuserDetailsDto userDto = (CrmuserDetailsDto) request.getSession()
                    .getAttribute( IAppConstants.CRM_USER_OBJECT );
            Set<CrmPartnerNetworkConfigPojo> newCrmPartnerNetworkConfigPojos = new HashSet<CrmPartnerNetworkConfigPojo>();
            Set<CrmPartnerNetworkConfigPojo> recordIdPartnerNetworkConfigPojos = new HashSet<CrmPartnerNetworkConfigPojo>();
            List<CrmPartnerNetworkConfigPojo> toUpdatePartnerNetworkConfigPojos = new ArrayList<CrmPartnerNetworkConfigPojo>();
            List<CrmPartnerNetworkConfigPojo> toCreatePartnerNetworkConfigPojos = new ArrayList<CrmPartnerNetworkConfigPojo>();
            String userId = userDto.getCrmUserDetailsPojo().getUserId();
            masterDataDto.setUserID( userId );
            masterDataDto.setPartnerId( masterForm.getPartnerId() );
            masterDataDto.setProduct( masterForm.getBusinessType() );
            partnerDetailsId = getPartnerDetailsId( masterForm );
            if ( partnerDetailsId > 0 )
            {
                if ( StringUtils.equals( masterForm.getBusinessType(), CRMDisplayListConstants.BROADBAND.getCode() ) )
                {
                    if ( CommonValidator.isValidCollection( masterForm.getCrmPartnerNetworkConfigPojos() ) )
                    {
                        newCrmPartnerNetworkConfigPojos.addAll( masterForm.getCrmPartnerNetworkConfigPojos() );
                    }
                    masterDataDto.getCrmPartnerNetworkConfigPojos().addAll( newCrmPartnerNetworkConfigPojos );
                    LOGGER.info( "PartnerNetworkConfigPojos" + masterDataDto.getCrmPartnerNetworkConfigPojos() );
                }
                else if ( StringUtils.equals( masterForm.getBusinessType(),
                                              CRMDisplayListConstants.ETHERNET_ON_CABLE.getCode() )
                        || StringUtils.equals( masterForm.getBusinessType(),
                                               CRMDisplayListConstants.RADIO_FREQUENCY.getCode() ) )
                {
                    for ( CrmPartnerNetworkConfigPojo newPartnerPojo : masterForm.getCrmPartnerNetworkConfigPojos() )
                    {
                        LOGGER.info( "OPERATION OPERATION::: data got from form for EOC:" + newPartnerPojo );
                        if ( newPartnerPojo.getRecordId() > 0 )
                        {
                            CrmPartnerNetworkConfigPojo editedPojo = newPartnerPojo;
                            recordIdPartnerNetworkConfigPojos.add( editedPojo );
                            LOGGER.info( "::pojo with record id got:" + editedPojo );
                        }
                        else
                        {
                            newPartnerPojo.setPartnerDetailsId( partnerDetailsId );
                            newPartnerPojo.setStatus( CRMStatusCode.ACTIVE.getStatusCode() );
                            newCrmPartnerNetworkConfigPojos.add( newPartnerPojo );
                            LOGGER.info( "new pojo got" + newPartnerPojo );
                        }
                    }
                    MasterDataDto dbMasterDataDto = searchOption82NassportId( partnerDetailsId,
                                                                              masterForm.getBusinessType(), userId,
                                                                              null );
                    List<CrmPartnerNetworkConfigPojo> dbCrmPartnerNetworkConfigPojos = dbMasterDataDto
                            .getCrmPartnerNetworkConfigPojos();
                    PojoComparator<CrmPartnerNetworkConfigPojo> pojoComparator = new PojoComparator<CrmPartnerNetworkConfigPojo>();
                    if ( dbCrmPartnerNetworkConfigPojos.size() > 0 )
                    {
                        for ( CrmPartnerNetworkConfigPojo dbcrmPartnerNetworkConfigPojo : dbCrmPartnerNetworkConfigPojos )
                        {
                            for ( CrmPartnerNetworkConfigPojo recordIdcrmPartnerNetworkConfigPojo : recordIdPartnerNetworkConfigPojos )
                            {
                                if ( recordIdcrmPartnerNetworkConfigPojo.getRecordId() == dbcrmPartnerNetworkConfigPojo
                                        .getRecordId() )
                                {
                                    LOGGER.info( "record id equals:"
                                            + recordIdcrmPartnerNetworkConfigPojo.getRecordId() );
                                    if ( ! ( pojoComparator.compare( recordIdcrmPartnerNetworkConfigPojo,
                                                                     dbcrmPartnerNetworkConfigPojo ) == 0 ) )
                                    {
                                        toUpdatePartnerNetworkConfigPojos.add( recordIdcrmPartnerNetworkConfigPojo );
                                    }
                                }
                            }
                            for ( CrmPartnerNetworkConfigPojo newCrmPartnerNetworkConfigPojo : newCrmPartnerNetworkConfigPojos )
                            {
                                if ( ( StringUtils.equals( newCrmPartnerNetworkConfigPojo.getMasterName(),
                                                           dbcrmPartnerNetworkConfigPojo.getMasterName() )
                                        && StringUtils.equals( newCrmPartnerNetworkConfigPojo.getPoolName(),
                                                               dbcrmPartnerNetworkConfigPojo.getPoolName() ) && StringUtils
                                            .equals( newCrmPartnerNetworkConfigPojo.getNasPortId(),
                                                     dbcrmPartnerNetworkConfigPojo.getNasPortId() ) ) )
                                {
                                    LOGGER.info( "to be removed pojo:" + newCrmPartnerNetworkConfigPojo );
                                    newCrmPartnerNetworkConfigPojos.remove( newCrmPartnerNetworkConfigPojo );
                                }
                            }
                        }
                    }
                    toCreatePartnerNetworkConfigPojos.addAll( newCrmPartnerNetworkConfigPojos );
                    masterDataDto.getCrmPartnerNetworkConfigPojos().addAll( toUpdatePartnerNetworkConfigPojos );
                    masterDataDto.getCrmPartnerNetworkConfigPojos().addAll( toCreatePartnerNetworkConfigPojos );
                    LOGGER.info( "TOTALLLL  size" + masterDataDto.getCrmPartnerNetworkConfigPojos().size() );
                }
            }
            if ( masterDataDto.getCrmPartnerNetworkConfigPojos().size() > 0 )
            {
                masterDataDto = getMasterDataBm().masterOperation( ServiceParameter.CREATE.getParameter(),
                                                                   CRMParameter.OPTION82.getParameter(), masterDataDto );
            }
            else
            {
                masterDataDto.setStatusCode( CRMServiceCode.CRM995.getStatusCode() );
            }
            if ( StringUtils.isValidObj( masterDataDto ) )
            {
                String statusCode = masterDataDto.getStatusCode();
                if ( StringUtils.equals( statusCode, CRMServiceCode.CRM001.getStatusCode() ) )
                {
                    LOGGER.info( "Result......." + statusCode );
                    messages.add( IAppConstants.APP_MESSAGE, new ActionMessage( statusCode ) );
                    forwardPage = IActionForwardConstant.SEARCH_OPTION82_REDIRECT;
                }
                else
                {
                    LOGGER.info( "Result......." + statusCode );
                    errors.add( IAppConstants.APP_ERROR, new ActionMessage( statusCode ) );
                }
            }
        }
        catch ( Exception e )
        {
            LOGGER.info( "Error occured", e );
        }
        saveMessages( request, messages );
        saveErrors( request, errors );
        return mapping.findForward( forwardPage );
    }

    public ActionForward addNassportRow( ActionMapping mapping,
                                         ActionForm form,
                                         HttpServletRequest request,
                                         HttpServletResponse response )
    {
        ActionMessages messages = new ActionMessages();
        ActionMessages errors = new ActionMessages();
        try
        {
            MasterForm masterForm = (MasterForm) form;
            CrmPartnerNetworkConfigPojo crmPartnerNetworkConfigPojo = new CrmPartnerNetworkConfigPojo();
            crmPartnerNetworkConfigPojo.setStatus( CRMStatusCode.ACTIVE.getStatusCode() );
            if ( !StringUtils.isValidObj( masterForm.getCrmPartnerNetworkConfigPojos() ) )
            {
                masterForm.setCrmPartnerNetworkConfigPojos( new ArrayList<CrmPartnerNetworkConfigPojo>() );
            }
            masterForm.getCrmPartnerNetworkConfigPojos().add( 0, crmPartnerNetworkConfigPojo );
        }
        catch ( Exception e )
        {
            LOGGER.info( "Error occured", e );
        }
        saveMessages( request, messages );
        saveErrors( request, errors );
        return mapping.findForward( IActionForwardConstant.OPTION_82_PAGE );
    }

    public ActionForward deleteNassportRow( ActionMapping mapping,
                                            ActionForm form,
                                            HttpServletRequest request,
                                            HttpServletResponse response )
    {
        ActionMessages messages = new ActionMessages();
        ActionMessages errors = new ActionMessages();
        try
        {
            MasterForm masterForm = (MasterForm) form;
            if ( StringUtils.isValidObj( masterForm.getCrmPartnerNetworkConfigPojos() ) )
            {
                masterForm.getCrmPartnerNetworkConfigPojos().remove( masterForm.getRowCounter() );
            }
        }
        catch ( Exception e )
        {
            LOGGER.info( "Error occured", e );
        }
        saveMessages( request, messages );
        saveErrors( request, errors );
        return mapping.findForward( IActionForwardConstant.OPTION_82_PAGE );
    }

    MasterDataDto searchOption82NassportId( long detailsId, String businessType, String userId, String oltType )
    {
        MasterDataDto masterDataDto = new MasterDataDto();
        masterDataDto.setProduct( businessType );
        masterDataDto.setUserID( userId );
        CrmPartnerNetworkConfigPojo crmPartnerNetworkConfigPojo = new CrmPartnerNetworkConfigPojo();
        crmPartnerNetworkConfigPojo.setPartnerDetailsId( detailsId );
        if ( StringUtils.equals( businessType, CRMDisplayListConstants.BROADBAND.getCode() ) )
        {
            crmPartnerNetworkConfigPojo.setOltType( oltType );
        }
        masterDataDto.setCrmPartnerNetworkConfigPojo( crmPartnerNetworkConfigPojo );
        masterDataDto = getMasterDataBm().masterOperation( ServiceParameter.SEARCH.getParameter(),
                                                           CRMParameter.OPTION82.getParameter(), masterDataDto );
        return masterDataDto;
    }

    private long getPartnerDetailsId( MasterForm inMasterForm )
    {
        long partnerDetailsId = 0;
        PartnerPojo pojo = new PartnerPojo();
        pojo.setPartnerId( inMasterForm.getPartnerId() );
        int pojoIndex = inMasterForm.getPartnerList().indexOf( pojo );
        if ( pojoIndex >= 0 )
        {
            pojo = inMasterForm.getPartnerList().get( pojoIndex );
            for ( CrmPartnerDetailsPojo crmPartnerDetailsPojo : pojo.getCrmPartnerDetailses() )
            {
                if ( StringUtils.equals( crmPartnerDetailsPojo.getBussinessType(), inMasterForm.getBusinessType() )
                        && StringUtils.equals( crmPartnerDetailsPojo.getPartnerType(),
                                               CRMDisplayListConstants.NETWORK_PARTNER.getCode() )
                        && StringUtils.equals( crmPartnerDetailsPojo.getStatus(), CRMStatusCode.ACTIVE.getStatusCode() ) )
                {
                    partnerDetailsId = crmPartnerDetailsPojo.getRecordId();
                    break;
                }
            }
        }
        return partnerDetailsId;
    }
}
