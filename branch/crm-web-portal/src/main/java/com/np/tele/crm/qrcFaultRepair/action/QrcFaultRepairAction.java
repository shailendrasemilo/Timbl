package com.np.tele.crm.qrcFaultRepair.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.np.tele.crm.qrc.bm.IQrcManager;

public class QrcFaultRepairAction
    extends Action
{
    private static final Logger LOGGER       = Logger.getLogger( QrcFaultRepairAction.class );
    private IQrcManager         qrcManagerBm = null;

    public IQrcManager getQrcManagerBm()
    {
        return qrcManagerBm;
    }

    public void setQrcManagerBm( IQrcManager inQrcManagerBm )
    {
        qrcManagerBm = inQrcManagerBm;
    }

    @Override
    public ActionForward execute( ActionMapping inMapping,
                                  ActionForm inForm,
                                  HttpServletRequest inRequest,
                                  HttpServletResponse inResponse )
        throws Exception
    {
        LOGGER.info( "QrcFaultRepairAction Call..." );
        String to = inRequest.getParameter( "to" );
        String msg = inRequest.getParameter( "text" );
        String from = inRequest.getParameter( "from" );
        String oprator = inRequest.getParameter( "operator" );
        String date = inRequest.getParameter( "Date" );
        LOGGER.info( "To:" + to + "Message:" + msg + ",From:" + from + ",Oprator:" + oprator + ",Date:" + date );
        String requestUrl = inRequest.getRequestURL().toString();
        LOGGER.info( "Request Url:.." + requestUrl );
        getQrcManagerBm().callFaultRepairAPI( to, msg, from, oprator, date );
        return null;
    }
}
