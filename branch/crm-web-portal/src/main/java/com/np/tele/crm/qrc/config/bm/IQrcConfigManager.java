package com.np.tele.crm.qrc.config.bm;

import java.util.List;

import com.np.tele.crm.qrc.config.forms.QrcConfigForm;
import com.np.tele.crm.service.client.CrmQrcActionTakenPojo;
import com.np.tele.crm.service.client.CrmQrcAttributedToPojo;
import com.np.tele.crm.service.client.CrmQrcCategoryBinMappingPojo;
import com.np.tele.crm.service.client.CrmQrcRootCausePojo;
import com.np.tele.crm.service.client.CrmQrcSubSubCategoriesPojo;
import com.np.tele.crm.service.client.QrcConfigDto;

public interface IQrcConfigManager
{
    List<CrmQrcActionTakenPojo> getQrcRcaPojos( long inCategoryId, String inService, String status );

    List<CrmQrcRootCausePojo> getCrmQrcResolutionCodePojos( long inRcaId );

    QrcConfigDto configurRCA( QrcConfigDto inQrcConfigDto );

    List<CrmQrcCategoryBinMappingPojo> getBinMappings( long inQrcSubSubCategoryId );

    List<CrmQrcAttributedToPojo> getAttributedToList( long qrcResolutionCodeId );

    QrcConfigDto configurResolutionCode( QrcConfigForm inQrcConfigForm, String inUser );

    QrcConfigDto configurAttributedTo( QrcConfigForm qrcConfigForm, String user );

    List<CrmQrcSubSubCategoriesPojo> getQrcSubSubCategories( long subCategoryId, String inModuleType );

    QrcConfigDto configurBinMapping( QrcConfigDto inQrcConfigDto );

    QrcConfigDto configureSubSubCat( QrcConfigDto qrcConfigDto );
}
