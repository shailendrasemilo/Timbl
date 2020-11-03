package com.np.tele.crm.cms.upload.bm;

import java.util.List;

import com.np.tele.crm.service.client.CrmCmsFilePojo;
import com.np.tele.crm.service.client.CrmCmsPaymentPojo;

public interface ICrmUploadManager
{
    CrmCmsFilePojo cmsFileOperation( CrmCmsFilePojo inCrmCmsFilePojo );

    boolean cmsPaymentOperation( List<CrmCmsPaymentPojo> cmsPaymentPojos, CrmCmsFilePojo inCrmCmsFilePojo );

    boolean cmsProcessOperation( CrmCmsFilePojo inCrmCmsFilePojo );

    List<CrmCmsFilePojo> getFilesToProcess( String inStatus );

    void sendFileStatus( Long inFileID ); 
}
