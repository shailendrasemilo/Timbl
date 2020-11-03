package com.np.tele.crm.masterdata.dao;

import com.np.tele.crm.dto.MasterDataDto;

public interface IBussinessPartnerMgtDao
{
    MasterDataDto createUpdatePartner( MasterDataDto inMasterDataDto );

    MasterDataDto listOFBussinessPartner( MasterDataDto inMasterDataDto );

    MasterDataDto crmPartnerMapping( MasterDataDto inMasterDataDto );

    MasterDataDto listOFPartnerMapping( MasterDataDto inMasterDataDto );

    MasterDataDto listOFPartnerDetailMapping( MasterDataDto inMasterDataDto );

    MasterDataDto saveMultipleMobileWithNP( MasterDataDto inMasterDataDto );

    MasterDataDto getNpMobileList( MasterDataDto inMasterDataDto );
}
