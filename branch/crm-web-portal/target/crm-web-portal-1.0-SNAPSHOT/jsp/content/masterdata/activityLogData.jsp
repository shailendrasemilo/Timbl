<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="/WEB-INF/tlds/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/tlds/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/tlds/displaytag.tld" prefix="display"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="css/common.css" rel="stylesheet" media="screen" />
<link href="css/color-${initParam.client }.css" rel="stylesheet" media="screen" />
<link href="css/userManagement.css" rel="stylesheet"
	media="screen" />
<link href="css/emailManagement.css" rel="stylesheet"
	media="screen" />
<link href="css/masterdata.css" rel="stylesheet"
	media="screen" />
<link href="css/lms.css" rel="stylesheet" media="screen" />
<link href="css/tcal.css" rel="stylesheet" type="text/css" />
<link href="css/gis.css" rel="stylesheet" media="screen" />
<link href="css/displaytag.css" rel="stylesheet" type="text/css" />
<link href="css/ina.css" rel="stylesheet" media="screen" />
<link href="css/qrc.css" rel="stylesheet" media="screen" />
<meta http-equiv="refresh" content="60" />
</head>
<body style='background: white;'>
	<logic:present scope="session" name="CrmCustomerActivityPojo">
		<display:table id="customerActiviyLogs"
			name="${sessionScope.CrmCustomerActivityPojo}" class="dataTable"
			style="width:100%" pagesize="10" requestURI="manageQrc.do">
			<display:setProperty name="paging.banner.placement" value="bottom" />
			<display:column title="Sr.No"><bean:write name="customerActiviyLogs_rowNum" /></display:column>
			<display:column title="Action Date"><bean:write name="crmRoles"	property="xmlDate(${customerActiviyLogs.createdTime})" /></display:column>
			<display:column title="Action"><bean:write name="crmRoles" property="displayEnum(CRMCustomerActivityActions,${customerActiviyLogs.action})" /></display:column>
			<display:column title="Reason" property="reason"></display:column>
			<display:column title="Ticket ID">
				<logic:equal name="crmRoles" property="available(view_qrc_tkt)"	value="true" scope="session">
					<a href="manageQrc.do?method=viewServiceRequestPage&qrcSR_No=${customerActiviyLogs.ticketId}&customerID=${customerActiviyLogs.customerId}" target="_blank"><bean:write name="customerActiviyLogs" property="ticketId" /></a>
				</logic:equal>
				<logic:notEqual name="crmRoles" property="available(view_qrc_tkt)" value="true" scope="session">
					<bean:write name="customerActiviyLogs" property="ticketId" />
				</logic:notEqual>
			</display:column>
			<display:column title="Old Value" property="oldValue"></display:column>
			<display:column title="New Value" property="newValue"></display:column>
			<display:column title="Created By" property="createdBy"></display:column>
		</display:table>
	</logic:present>
</body>
</html>
