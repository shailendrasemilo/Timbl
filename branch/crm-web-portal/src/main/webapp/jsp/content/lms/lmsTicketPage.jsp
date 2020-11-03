<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib uri="/WEB-INF/tlds/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/tlds/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/tlds/displaytag.tld" prefix="display"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<link href="../../../css/color-${initParam.client }.css" rel="stylesheet" media="screen">
<link href="../../../css/disaplyTableHelper.css" rel="stylesheet" media="screen" />
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="css/color-${initParam.client }.css" rel="stylesheet" media="screen" />
<link href="css/lms.css" rel="stylesheet" media="screen" />
<link href="css/displaytag.css" rel="stylesheet" type="text/css" />
<script src="javascript/common.js" type="text/javascript"></script>
</head>
<logic:notEmpty name="lmsForm" property="srTicketsPojos">
	<display:table id="qrcTicketTable" name="${ lmsForm.srTicketsPojos }" class="dataTable tList" requestURI="" style="width: 100%;" pagesize="15">
		<display:column title="Sr. No." class="${qrcTicketTable.color}" maxLength="5"><bean:write name="qrcTicketTable_rowNum" /></display:column>
		<display:setProperty name="paging.banner.placement" value="bottom" />
		<display:column class="${qrcTicketTable.color}" title="Ticket ID">
			<logic:equal name="crmRoles" property="available(view_qrc_tkt)" value="true" scope="session">
				<a href="javascript:viewLMSTicket('<bean:write name="qrcTicketTable" property="srId"/>')"><bean:write name="qrcTicketTable"
						property="srId" /></a>
			</logic:equal>
		</display:column>
		<display:column title="Category" class="${qrcTicketTable.color}" property="qrcCategory"></display:column>
		<display:column title="Sub Category" class="${qrcTicketTable.color}" property="qrcSubCategory"></display:column>
		<display:column title="Sub Sub Category" class="${qrcTicketTable.color}" property="qrcSubSubCategory"></display:column>
		<display:column title="QRC Type" class="${qrcTicketTable.color}">
			<logic:notEmpty name="qrcTicketTable" property="qrcType">
				<bean:write name="crmRoles" property="displayEnum(qrcType,${qrcTicketTable.qrcType})" scope="session" />
			</logic:notEmpty>
		</display:column>
		<display:column title="Resolution Type" class="${qrcTicketTable.color}">
			<bean:write name="crmRoles" property="displayEnum(ticketAction,${ qrcTicketTable.resolutionType })" />
		</display:column>
		<display:column title="Status" class="${qrcTicketTable.color}">
			<logic:notEmpty name="qrcTicketTable" property="status">
				<bean:write name="crmRoles" property="displayEnum(qrcTicketStatus,${qrcTicketTable.status})" scope="session" />
			</logic:notEmpty>
		</display:column>
		<display:column title="Created By" class="${qrcTicketTable.color}" property="createdBy"></display:column>
		<display:column title="Followup On" class="${qrcTicketTable.color}">
			<fmt:parseDate value="${qrcTicketTable.displayCreatedTime}" pattern="dd-MMM-yyyy" var="createdDate" />
			<fmt:parseDate value="${qrcTicketTable.displayFollowupOn}" pattern="dd-MMM-yyyy" var="followupOn" />
			<c:if test="${followupOn eq createdDate }">
      -
     </c:if>
			<c:if test="${followupOn ne createdDate }">
				<bean:write name="crmRoles" property="xmlDate(${qrcTicketTable.followupOn})" scope="session" />
			</c:if>
		</display:column>
		<display:column title="Created Date" class="${qrcTicketTable.color}">
			<bean:write name="crmRoles" property="xmlDate(${qrcTicketTable.createdTime})" scope="session" />
		</display:column>
	</display:table>
</logic:notEmpty>
<logic:empty name="lmsForm" property="srTicketsPojos">Nothing found to display.</logic:empty>
