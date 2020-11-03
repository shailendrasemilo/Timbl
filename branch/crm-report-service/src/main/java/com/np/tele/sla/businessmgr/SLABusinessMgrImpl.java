package com.np.tele.sla.businessmgr;

import org.apache.log4j.Logger;

import com.np.tele.crm.constants.CRMParameter;
import com.np.tele.crm.constants.CRMServiceCode;
import com.np.tele.crm.constants.ServiceParameter;
import com.np.tele.crm.dto.ReportDto;
import com.np.tele.crm.pojos.AreaPojo;
import com.np.tele.crm.sla.dao.ISLAManagerDoa;
import com.np.tele.crm.utils.CRMServiceUtils;
import com.np.tele.crm.utils.StringUtils;

public class SLABusinessMgrImpl
    implements ISLABusinessMgr
{
    private static final Logger LOGGER            = Logger.getLogger( SLABusinessMgrImpl.class );
    private ISLAManagerDoa      slaManagerDoaImpl = null;

    public ISLAManagerDoa getSlaManagerDoaImpl()
    {
        return slaManagerDoaImpl;
    }

    public void setSlaManagerDoaImpl( ISLAManagerDoa inSlaManagerDoaImpl )
    {
        slaManagerDoaImpl = inSlaManagerDoaImpl;
    }

    @Override
    public ReportDto SLAOperation( String inServiceParam, String inCrmParam, ReportDto inReportDto )
    {
        LOGGER.info( "Inside SLAOperationMgrImpl, SLAOperation" );
        LOGGER.info( "ServiceParam : " + inServiceParam + ", CrmParam : " + inCrmParam );
        if ( StringUtils.equals( inServiceParam, ServiceParameter.SLA_LMS.getParameter() ) )
        {
            if ( StringUtils.equals( inCrmParam, CRMParameter.AUDIT_LOG.getParameter() ) )
            {
                if ( StringUtils.isValidObj( inReportDto.getCrmAuditLogPojo() ) )
                {
                    inReportDto = getSlaManagerDoaImpl().getAuditLogHistory( inReportDto );
                }
            }
            if ( StringUtils.equals( inCrmParam, CRMParameter.SLA_LOG.getParameter() ) )
            {
                if ( StringUtils.isValidObj( inReportDto.getCrmSlaLogPojo() ) )
                {
                    inReportDto = getSlaManagerDoaImpl().getSlaLogHistory( inReportDto );
                }
            }
            if ( StringUtils.equals( inCrmParam, CRMParameter.REMARKS.getParameter() ) )
            {
                if ( StringUtils.isValidObj( inReportDto.getRemarksPojo() ) )
                {
                    inReportDto = getSlaManagerDoaImpl().getRemarksHistory( inReportDto );
                }
            }
            else if ( StringUtils.equals( inCrmParam, CRMParameter.SLA_ALERT.getParameter() ) )
            {
                if ( StringUtils.isValidObj( inReportDto.getCrmSlaLogPojo() )
                        && StringUtils.isValidObj( inReportDto.getParamMap() ) )
                {
                    inReportDto = getSlaManagerDoaImpl().sendSLAAlertAndSaveStatus( inReportDto );
                }
            }
            else if ( StringUtils.equals( inCrmParam, CRMParameter.SLA_FORWARD.getParameter() ) )
            {
                if ( StringUtils.isValidObj( inReportDto.getCrmSlaLogPojo() )
                        && StringUtils.isValidObj( inReportDto.getLeadPojo() ) )
                {
                    inReportDto = getSlaManagerDoaImpl().forwardAndSaveStatus( inReportDto );
                }
            }
        }
        else if ( StringUtils.equals( inServiceParam, ServiceParameter.SLA_INA.getParameter() ) )
        {
            if ( StringUtils.equals( inCrmParam, CRMParameter.SLA_LOG.getParameter() ) )
            {
                if ( StringUtils.isValidObj( inReportDto.getCrmSlaLogPojo() ) )
                {
                    inReportDto = getSlaManagerDoaImpl().getSlaLogHistory( inReportDto );
                }
            }
            if ( StringUtils.equals( inCrmParam, CRMParameter.AREA.getParameter() ) )
            {
                if ( StringUtils.isValidObj( inReportDto.getAreaPojo() ) )
                {
                    inReportDto.setAreaPojo( CRMServiceUtils.getDBValues( AreaPojo.class, inReportDto.getAreaPojo()
                            .getAreaId() ) );
                    inReportDto.setStatusCode( CRMServiceCode.CRM001.getStatusCode() );
                    inReportDto.setStatusDesc( CRMServiceCode.CRM001.getStatusDesc() );
                }
            }
            else if ( StringUtils.equals( inCrmParam, CRMParameter.SLA_ALERT.getParameter() ) )
            {
                if ( StringUtils.isValidObj( inReportDto.getCrmSlaLogPojo() )
                        && StringUtils.isValidObj( inReportDto.getParamMap() ) )
                {
                    inReportDto = getSlaManagerDoaImpl().sendSLAAlertAndSaveStatus( inReportDto );
                }
            }
        }
        else if ( StringUtils.equals( inServiceParam, ServiceParameter.SLA_QRC.getParameter() ) )
        {
            if ( StringUtils.equals( inCrmParam, CRMParameter.QRC_SUB_CATEGORY.getParameter() ) )
            {
                if ( StringUtils.isValidObj( inReportDto.getSubSubCategoriesPojo() ) )
                {
                    inReportDto = getSlaManagerDoaImpl().getQrcSubCategoriesPojos( inReportDto );
                }
            }
            if ( StringUtils.equals( inCrmParam, CRMParameter.QRC_SUB_SUB_CATEGORY.getParameter() ) )
            {
                if ( StringUtils.isValidObj( inReportDto.getSubSubCategoriesPojo() ) )
                {
                    inReportDto = getSlaManagerDoaImpl().getQrcSubSubCategoriesPojos( inReportDto );
                }
            }
            if ( StringUtils.equals( inCrmParam, CRMParameter.SLA_LOG.getParameter() ) )
            {
                if ( StringUtils.isValidObj( inReportDto.getCrmSlaLogPojo() ) )
                {
                    inReportDto = getSlaManagerDoaImpl().getSlaLogHistory( inReportDto );
                }
            }
            if ( StringUtils.equals( inCrmParam, CRMParameter.SLA_ALERT.getParameter() ) )
            {
                if ( StringUtils.isValidObj( inReportDto.getCrmSlaLogPojo() )
                        && StringUtils.isValidObj( inReportDto.getParamMap() ) )
                {
                    inReportDto = getSlaManagerDoaImpl().sendSLAAlertAndSaveStatus( inReportDto );
                }
            }
        }
        return inReportDto;
    }
}
