<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="/WEB-INF/tlds/struts-logic.tld" prefix="logic"%>
<ul class="qrc">
<logic:equal name="crmRoles" property="available(view_rpt_crf)" value="true" scope="session">
 <li class="${ activeMenu eq 'crfScanning' ? 'active' : '' }">
 <a href="capReportAction.do?method=crfScanningReportPage">
 	CAF Scanning Report</a>
 </li>


 <li class="${ activeMenu eq 'crfDetail' ? 'active' : '' }">
 <a href="capReportAction.do?method=crfDetailReportPage">
 	CAF Detail Report
 </a>
 </li>

 <li class="${ activeMenu eq 'cancellation' ? 'active' : '' }">
 <a href="capReportAction.do?method=cancellationReportPage">
 	Cancellation Report
 </a>
 </li>
 </logic:equal>

</ul>
