<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="/WEB-INF/tlds/struts-logic.tld" prefix="logic"%>
<ul class="qrc">
<logic:equal name="crmRoles" property="available(view_rpt_open_tkt)" value="true" scope="session">
<li class="${ activeMenu eq 'openTicket' ? 'active' : '' }">
 <a href="qrcReportAction.do?method=openTicketReport">
	Open Ticket Report
 </a>
 </li> 
</logic:equal>
<logic:equal name="crmRoles" property="available(view_rpt_qrc)" value="true" scope="session">
 <li class="${ activeMenu eq 'resolvedTicket' ? 'active' : '' }">
 <a href="qrcReportAction.do?method=resolvedTickets">
	Resolved Tickets Report
 </a>
 </li> 
  <li class="${ activeMenu eq 'ticket' ? 'active' : '' }">
 <a href="qrcReportAction.do?method=ticketReport">
 	Ticket Report
 </a>
 </li>

<li class="${ activeMenu eq 'firstAssignedBin' ? 'active' : '' }">
 <a href="qrcReportAction.do?method=firstAssignedBinTicketReport">
	 First Assigned Bin Ticket Report
 </a>
 </li> 

 <li class="${ activeMenu eq 'catWiseOTR' ? 'active' : '' }">
 <a href="qrcReportAction.do?method=catWiseOpenTicketReport">
	Category Wise Open Ticket Report
 </a>
 </li> 
 <li class="${ activeMenu eq 'UserWiseOTR' ? 'active' : '' }">
 <a href="qrcReportAction.do?method=userWiseOpenTicketReport">
	User Wise Open Ticket Report
 </a>
 </li> 
 <li class="${ activeMenu eq 'OutOfSLAOTR' ? 'active' : '' }">
 <a href="qrcReportAction.do?method=outOfSLATicketReport">
	User Wise Open Out of SLA ticket Report
 </a>
 </li>
 
 </logic:equal>
 
<!-- 
<logic:equal name="crmRoles" property="available(view_rpt_adj)" value="true" scope="session">
 <li class="${ activeMenu eq 'adjustment' ? 'active' : '' }">
 <a href="qrcReportAction.do?method=adjustmentReport">
	Adjustment Report</a>
 </li>
 </logic:equal>
 <logic:equal name="crmRoles" property="available(view_rpt_churn)" value="true" scope="session">
 <li class="${ activeMenu eq 'churn' ? 'active' : '' }">
 <a href="qrcReportAction.do?method=churnReport">
	Churn Report
 </a>
 </li>
 </logic:equal>
 <logic:equal name="crmRoles" property="available(view_rpt_interac)" value="true" scope="session">
  <li class="${ activeMenu eq 'interaction' ? 'active' : '' }">
 <a href="qrcReportAction.do?method=interactionReport">
	Interaction Report
 </a>
 </li> 
 </logic:equal>
 <logic:equal name="crmRoles" property="available(view_rpt_mig)" value="true" scope="session">
  <li class="${ activeMenu eq 'migration' ? 'active' : '' }">
 <a href="qrcReportAction.do?method=migrationReport">
	Migration Report
 </a>
 </li> 
</logic:equal>
 <logic:equal name="crmRoles" property="available(view_rpt_react)" value="true" scope="session">
  <li class="${ activeMenu eq 'reactivation' ? 'active' : '' }">
 <a href="qrcReportAction.do?method=reactivationReport">
	Reactivation Report
 </a>
 </li> 
 </logic:equal>
 <logic:equal name="crmRoles" property="available(view_rpt_valex)" value="true" scope="session">
  <li class="${ activeMenu eq 'validityExtension' ? 'active' : '' }">
 <a href="qrcReportAction.do?method=validityExtensionReport">
	Validity Extension Report
 </a>
 </li> 
 </logic:equal>
 <logic:equal name="crmRoles" property="available(view_rpt_optckt)" value="true" scope="session">
  <li class="${ activeMenu eq 'openTicket' ? 'active' : '' }">
 <a href="qrcReportAction.do?method=openTicketReport">
	Open Ticket Report
 </a>
 </li> 
 </logic:equal>
 <logic:equal name="crmRoles" property="available(view_rpt_rslvtckt)" value="true" scope="session">
  <li class="${ activeMenu eq 'resolvedTicket' ? 'active' : '' }">
 <a href="qrcReportAction.do?method=resolvedTickets">
	Resolved Tickets Report
 </a>
 </li> 
 </logic:equal>
 <logic:equal name="crmRoles" property="available(view_rpt_tag)" value="true" scope="session">
  <li class="${ activeMenu eq 'tagging' ? 'active' : '' }">
 <a href="qrcReportAction.do?method=taggingReport">
	Tagging Report
 </a>
 </li> 
 </logic:equal>
 <logic:equal name="crmRoles" property="available(view_rpt_tckt)" value="true" scope="session">
   <li class="${ activeMenu eq 'ticket' ? 'active' : '' }">
 <a href="qrcReportAction.do?method=ticketReport">
 	Ticket Report
 </a>
 </li>
  </logic:equal>
 <logic:equal name="crmRoles" property="available(view_rpt_fabtr)" value="true" scope="session">
 <li class="${ activeMenu eq 'firstAssignedBin' ? 'active' : '' }">
 <a href="qrcReportAction.do?method=firstAssignedBinTicketReport">
	 First Assigned Bin Ticket Report
 </a>
 </li> 
 </logic:equal> -->
</ul>
