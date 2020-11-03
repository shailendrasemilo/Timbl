package com.np.tele.crm.cap.form;

import org.apache.struts.action.ActionForm;

import com.np.tele.crm.service.client.RemarksPojo;

public class CRFValidateForm
    extends ActionForm
{
    private RemarksPojo remarksPojo;

    public RemarksPojo getRemarksPojo()
    {
        return remarksPojo;
    }

    public void setRemarksPojo( RemarksPojo inRemarksPojo )
    {
        remarksPojo = inRemarksPojo;
    }
}
