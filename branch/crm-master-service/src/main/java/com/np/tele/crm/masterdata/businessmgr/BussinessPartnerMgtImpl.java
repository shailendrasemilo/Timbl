package com.np.tele.crm.masterdata.businessmgr;

import org.apache.log4j.Logger;

import com.np.tele.crm.constants.AppMonitor;
import com.np.tele.crm.constants.CRMServiceCode;
import com.np.tele.crm.constants.ServiceParameter;
import com.np.tele.crm.dto.MasterDataDto;
import com.np.tele.crm.masterdata.dao.IBussinessPartnerMgtDao;
import com.np.tele.crm.utils.StringUtils;

@AppMonitor
public class BussinessPartnerMgtImpl
    implements IBussinessPartnerMgt
{
    private static final Logger     LOGGER                 = Logger.getLogger( BussinessPartnerMgtImpl.class );
    private IBussinessPartnerMgtDao bussinessPartnerMgrDao = null;

    public IBussinessPartnerMgtDao getBussinessPartnerMgrDao()
    {
        return bussinessPartnerMgrDao;
    }

    public void setBussinessPartnerMgrDao( IBussinessPartnerMgtDao inBussinessPartnerMgrDao )
    {
        bussinessPartnerMgrDao = inBussinessPartnerMgrDao;
    }

    @Override
    public MasterDataDto bussinessPartnerMgt( String inServiceParam, MasterDataDto inMasterDataDto )
    {
        if ( StringUtils.isValidObj( inServiceParam ) & StringUtils.isValidObj( inMasterDataDto ) )
        {
            if ( inServiceParam.equals( ServiceParameter.CREATE.getParameter() ) )
            {
                return createAndUpdateBussinessPartner( inMasterDataDto );
            }
            else if ( inServiceParam.equals( ServiceParameter.LIST.getParameter() ) )
            {
                return listOFBussinessPartner( inMasterDataDto );
            }
            else if ( inServiceParam.equals( ServiceParameter.PARTNER_MAPPING.getParameter() ) )
            {
                return crmPartnerMapping( inMasterDataDto );
            }
            else if ( inServiceParam.equals( ServiceParameter.VIEW.getParameter() ) )
            {
                return listOFPartnerMapping( inMasterDataDto );
            }
            else if ( inServiceParam.equals( ServiceParameter.MAPPING_LIST.getParameter() ) )
            {
                return listOFPartnerDetailMapping( inMasterDataDto );
            }
            else if ( inServiceParam.equals( ServiceParameter.SAVE.getParameter() ) )
            {
                return saveMultipleMobileWithNP( inMasterDataDto );
            }
            else if ( inServiceParam.equals( ServiceParameter.SEARCH.getParameter() ) )
            {
                return getNpMobileList( inMasterDataDto );
            }
        }
        else
        {
            inMasterDataDto.setStatusCode( CRMServiceCode.CRM997.getStatusCode() );
            inMasterDataDto.setStatusDesc( CRMServiceCode.CRM997.getStatusDesc() );
        }
        return inMasterDataDto;
    }

    private MasterDataDto getNpMobileList( MasterDataDto inMasterDataDto )
    {
        if ( StringUtils.isValidObj( inMasterDataDto ) )
        {
            inMasterDataDto = getBussinessPartnerMgrDao().getNpMobileList( inMasterDataDto );
        }
        return inMasterDataDto;
    }

    private MasterDataDto saveMultipleMobileWithNP( MasterDataDto inMasterDataDto )
    {
        if ( StringUtils.isValidObj( inMasterDataDto ) )
        {
            inMasterDataDto = getBussinessPartnerMgrDao().saveMultipleMobileWithNP( inMasterDataDto );
        }
        return inMasterDataDto;
    }

    private MasterDataDto listOFPartnerDetailMapping( MasterDataDto inMasterDataDto )
    {
        if ( StringUtils.isValidObj( inMasterDataDto ) )
        {
            inMasterDataDto = getBussinessPartnerMgrDao().listOFPartnerDetailMapping( inMasterDataDto );
        }
        return inMasterDataDto;
    }

    private MasterDataDto listOFPartnerMapping( MasterDataDto inMasterDataDto )
    {
        if ( StringUtils.isValidObj( inMasterDataDto ) )
        {
            inMasterDataDto = getBussinessPartnerMgrDao().listOFPartnerMapping( inMasterDataDto );
        }
        return inMasterDataDto;
    }

    private MasterDataDto crmPartnerMapping( MasterDataDto inMasterDataDto )
    {
        if ( StringUtils.isValidObj( inMasterDataDto ) )
        {
            inMasterDataDto = getBussinessPartnerMgrDao().crmPartnerMapping( inMasterDataDto );
        }
        return inMasterDataDto;
    }

    private MasterDataDto listOFBussinessPartner( MasterDataDto inMasterDataDto )
    {
        if ( StringUtils.isValidObj( inMasterDataDto ) )
        {
            inMasterDataDto = getBussinessPartnerMgrDao().listOFBussinessPartner( inMasterDataDto );
        }
        return inMasterDataDto;
    }

    private MasterDataDto createAndUpdateBussinessPartner( MasterDataDto inMasterDataDto )
    {
        if ( StringUtils.isValidObj( inMasterDataDto.getPartnerPojo() ) )
        {
            if ( StringUtils.isEmpty( inMasterDataDto.getPartnerPojo().getPartnerName() ) )
            {
                inMasterDataDto.setStatusCode( CRMServiceCode.CRM061.getStatusCode() );
                inMasterDataDto.setStatusDesc( CRMServiceCode.CRM061.getStatusDesc() );
            }
            else if ( StringUtils.isEmpty( inMasterDataDto.getPartnerPojo().getPartnerCen() ) )
            {
                inMasterDataDto.setStatusCode( CRMServiceCode.CRM062.getStatusCode() );
                inMasterDataDto.setStatusDesc( CRMServiceCode.CRM062.getStatusDesc() );
            }
            else if ( StringUtils.isEmpty( inMasterDataDto.getPartnerPojo().getDesignation() ) )
            {
                inMasterDataDto.setStatusCode( CRMServiceCode.CRM063.getStatusCode() );
                inMasterDataDto.setStatusDesc( CRMServiceCode.CRM063.getStatusDesc() );
            }
            else if ( StringUtils.isEmpty( inMasterDataDto.getPartnerPojo().getHoCpn() ) )
            {
                inMasterDataDto.setStatusCode( CRMServiceCode.CRM066.getStatusCode() );
                inMasterDataDto.setStatusDesc( CRMServiceCode.CRM066.getStatusDesc() );
            }
            else if ( StringUtils.isEmpty( inMasterDataDto.getPartnerPojo().getRelatedDept() ) )
            {
                inMasterDataDto.setStatusCode( CRMServiceCode.CRM067.getStatusCode() );
                inMasterDataDto.setStatusDesc( CRMServiceCode.CRM067.getStatusDesc() );
            }
            else
            {
                inMasterDataDto = getBussinessPartnerMgrDao().createUpdatePartner( inMasterDataDto );
            }
        }
        else
        {
            inMasterDataDto.setStatusCode( CRMServiceCode.CRM997.getStatusCode() );
            inMasterDataDto.setStatusDesc( CRMServiceCode.CRM997.getStatusDesc() );
        }
        return inMasterDataDto;
    }
}
