package com.np.tele.crm.qrc.config.dao;

import com.np.tele.crm.dto.QrcConfigDto;
import com.np.tele.crm.pojos.CrmQrcActionTakenPojo;
import com.np.tele.crm.pojos.CrmQrcCategoryBinMappingPojo;

public interface IQrcConfigManagerDao
{
    QrcConfigDto getRcaConfigurations( QrcConfigDto inQrcConfigDto );

    boolean createRcaConfiguration( CrmQrcActionTakenPojo inQrcRcaPojo );

    QrcConfigDto getQrcResolutionCodePojos( QrcConfigDto inQrcConfigDto );

//    QrcConfigDto getQrcAttributedToPojos( QrcConfigDto inQrcConfigDto );

    QrcConfigDto configureResolutionCodePojo( QrcConfigDto inQrcConfigDto );

//    QrcConfigDto createAttributedToConfiguration( QrcConfigDto inQrcConfigDto );

    QrcConfigDto getQrcBinMappingPojos( QrcConfigDto inQrcConfigDto );

    boolean createBinMapping( CrmQrcCategoryBinMappingPojo inCategoryBinMappingPojo );

    QrcConfigDto createQrcRcaPojos( QrcConfigDto inQrcConfigDto );

    QrcConfigDto createQrcBinMappingPojos( QrcConfigDto inQrcConfigDto );

    QrcConfigDto createSubSubCat( QrcConfigDto inQrcConfigDto );
}
