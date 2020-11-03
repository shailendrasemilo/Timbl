package com.np.tele.crm.qrc.bm;

import java.util.List;

import com.np.tele.crm.qrc.forms.BulkUploadForm;

public interface IBulkUpload
{
    List<String> processMountBoosterExcel( BulkUploadForm inBulkUploadForm, String inUser, String inFilePath );

    List<String> processValidityExtensionExcel( BulkUploadForm inBulkUploadForm, String inUser, String inFilePath );
}
