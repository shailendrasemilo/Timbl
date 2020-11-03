<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="/WEB-INF/tlds/struts-logic.tld" prefix="logic"%>
<ul class="qrc">

<logic:equal name="crmRoles" property="available(view_rpt_csd)" value="true" scope="session">
  <li class="${ activeMenu eq 'interaction' ? 'active' : '' }">
 <a href="qrcReportAction.do?method=interactionReport">
	Interaction History Report
 </a>
 </li> 
 
  <li class="${ activeMenu eq 'validityExtension' ? 'active' : '' }">
 <a href="qrcReportAction.do?method=validityExtensionReport">
	Validity Extension Report
 </a>
 </li> 
 
 <li class="${ activeMenu eq 'tagging' ? 'active' : '' }">
 <a href="qrcReportAction.do?method=taggingReport">
	Tagging Report
 </a>
 </li> 
 <li class="${ activeMenu eq 'reopenTicket' ? 'active' : '' }">
 <a href="qrcReportAction.do?method=reopenTicketReport">
	Reopen Ticket Report
 </a>
 </li>

<li class="${ activeMenu eq 'repeatTicket' ? 'active' : '' }">
 <a href="qrcReportAction.do?method=repeatTicketReport">
	Repeat Ticket Report
 </a>
 </li>
</logic:equal>
 
</ul>
