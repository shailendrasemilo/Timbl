<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="/WEB-INF/tlds/struts-logic.tld" prefix="logic"%>
<ul class="qrc">
 <!--<logic:equal name="crmRoles" property="available(view_rpt_crfscn)" value="true" scope="session">
 <li class="${ activeMenu eq 'crfScanning' ? 'active' : '' }">
 <a href="capReportAction.do?method=crfScanningReportPage">
 	CAF Scanning Report</a>
 </li>
 </logic:equal>
 <logic:equal name="crmRoles" property="available(view_rpt_crfd)" value="true" scope="session">
 <li class="${ activeMenu eq 'crfDetail' ? 'active' : '' }">
 <a href="capReportAction.do?method=crfDetailReportPage">
 	CAF Detail Report
 </a>
 </li>
 </logic:equal>
 <logic:equal name="crmRoles" property="available(view_rpt_crfcncl)" value="true" scope="session">
  <li class="${ activeMenu eq 'cancellation' ? 'active' : '' }">
 <a href="capReportAction.do?method=cancellationReportPage">
 	Cancellation Report
 </a>
 </li>
 </logic:equal>-->
 <logic:equal name="crmRoles" property="available(view_rpt_ins)" value="true" scope="session">
  <li class="${ activeMenu eq 'WorkorderInstallation' ? 'active' : '' }">
 <a href="capReportAction.do?method=workorderInstallationReportPage">
 	Workorder Installation Report
 </a>
 </li>

 
  <li class="${ activeMenu eq 'WorkorderPendancy' ? 'active' : '' }">
 <a href="capReportAction.do?method=workorderPendancyReportPage">
 	Workorder Pendency Report
 </a>
 </li>
 
 
  <li class="${ activeMenu eq 'FTRejection' ? 'active' : '' }">
 <a href="capReportAction.do?method=ftRejectionReportPage">
	FT Rejection Report
 </a>
 </li>
 
  <li class="${ activeMenu eq 'ISRPendancy' ? 'active' : '' }">
 <a href="capReportAction.do?method=isrPendancyReportPage">
	ISR Pendency Report
 </a>
 </li>
 
  <li class="${ activeMenu eq 'SRP' ? 'active' : '' }">
 <a href="capReportAction.do?method=srPReportPage">
	SRP Report
 </a>
 </li>
 <li class="${ activeMenu eq 'WorkorderGeneration' ? 'active' : '' }">
 <a href="capReportAction.do?method=workorderGenerationReportPage">
	Workorder Generation Report
 </a>
 </li>
 <li class="${ activeMenu eq 'ERP' ? 'active' : '' }">
 <a href="capReportAction.do?method=erpReportPage">
	ERP Report
 </a>
 </li>
 </logic:equal>
 
 <!--<logic:equal name="crmRoles" property="available(view_rpt_kpi)" value="true" scope="session">
  <li class="${ activeMenu eq 'KPI' ? 'active' : '' }">
 <a href="capReportAction.do?method=kpiReportPage">
	KPI Report
 </a>
 </li>
 </logic:equal>-->
 
</ul>
