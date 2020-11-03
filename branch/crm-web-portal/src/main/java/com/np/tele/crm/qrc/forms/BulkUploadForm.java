package com.np.tele.crm.qrc.forms;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.upload.FormFile;

import com.np.tele.crm.constants.IAppConstants;
import com.np.tele.crm.service.client.CrmRcaReasonPojo;
import com.np.tele.crm.service.client.RemarksPojo;

public class BulkUploadForm
    extends ActionForm
{
    private final static Logger    LOGGER = Logger.getLogger( BulkUploadForm.class );
    private FormFile               mountBoosterExcelFile;
    private FormFile               validityExtensionExcelFile;
    private RemarksPojo            remarksPojo;
    private List<CrmRcaReasonPojo> crmRcaReasonPojos;
    private String                 gracePeriodReason;

    public FormFile getMountBoosterExcelFile()
    {
        return mountBoosterExcelFile;
    }

    public void setMountBoosterExcelFile( FormFile inMountBoosterExcelFile )
    {
        mountBoosterExcelFile = inMountBoosterExcelFile;
    }

    public RemarksPojo getRemarksPojo()
    {
        return remarksPojo;
    }

    public void setRemarksPojo( RemarksPojo inRemarksPojo )
    {
        remarksPojo = inRemarksPojo;
    }

    public FormFile getValidityExtensionExcelFile()
    {
        return validityExtensionExcelFile;
    }

    public void setValidityExtensionExcelFile( FormFile inValidityExtensionExcelFile )
    {
        validityExtensionExcelFile = inValidityExtensionExcelFile;
    }

    public List<CrmRcaReasonPojo> getCrmRcaReasonPojos()
    {
        return crmRcaReasonPojos;
    }

    public void setCrmRcaReasonPojos( List<CrmRcaReasonPojo> inCrmRcaReasonPojos )
    {
        crmRcaReasonPojos = inCrmRcaReasonPojos;
    }

    public String getGracePeriodReason()
    {
        return gracePeriodReason;
    }

    public void setGracePeriodReason( String inGracePeriodReason )
    {
        gracePeriodReason = inGracePeriodReason;
    }

    @Override
    public void reset( ActionMapping inMapping, HttpServletRequest inRequest )
    {
        LOGGER.info( "Bilk Upload Reset Method Call.." );
        String method = inRequest.getParameter( IAppConstants.PARAMETER_NAME );
        LOGGER.info( "Method is :" + method );
        BulkUploadFormHelper.resetBulkUploadForm( this, method );
    }
}
