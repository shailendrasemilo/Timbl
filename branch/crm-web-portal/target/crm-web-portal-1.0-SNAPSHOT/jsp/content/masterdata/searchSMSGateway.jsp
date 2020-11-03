<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<%@taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<%@taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<head>
<title>Search SMS Gateway</title>
<script src="dwr/interface/crmDwr.js" type="text/javascript"></script>
<link href="css/displaytag.css" rel="stylesheet" type="text/css" />

</head>
<body>

	
	<div id="section">
		<html:form styleId="smsform" action="/smsManagement"
			method="post">
			
			<div class="section">
				<h2>SMS Gateway</h2>			
				<div class="inner_section">
				<logic:equal name="crmRoles" property="available(create_sms_gateway)" value="true" scope="session">
					<a href="smsManagement.do?method=smsConfigurationPage"
						class="link_button">Create SMS Gateway</a>
				</logic:equal>
						<p class="clear"></p>
				<span class="error_message"><html:errors property="appError" /></span>
				<span class="success_message"> <logic:messagesPresent
						message="true" property="appMessage">
						<html:messages id="message" message="true"
							property="appMessage">
							<bean:write name="message" />
						</html:messages>
					</logic:messagesPresent>
				</span>

					

				</div>
			<html:hidden name="masterForm" property="smsAlias" styleId="smsAlias" />
				<logic:notEmpty name="masterForm" property="smsGateWayList">
					<display:table id="data"
						name="sessionScope.masterForm.smsGateWayList" requestURI="" class="dataTable"
						style="width:100%" pagesize="15">
						
						<display:setProperty name="paging.banner.placement" value="bottom" />
						<display:setProperty name="export.excel.filename" value="SMSGateway-Details.xls" />
						<display:setProperty name="export.csv.filename" value="SMSGateway-Details.csv" />
						 
						<display:column title="Sr. No.">
							<bean:write name="data_rowNum" />

						</display:column>
						<display:column title="Gateway Name" property="subCategory" />
						<display:column title="Gateway URL" property="url" />
						<display:column title="Gateway User" property="userid" />
						
						<display:column title="Response starts with" property="response" />
						<display:column title="Status"  >
						
						<logic:equal  name="data" property="enable" value="true">Enabled</logic:equal>
						<logic:equal  name="data" property="enable" value="false">Disabled</logic:equal>
						</display:column>
					<logic:equal name="crmRoles" property="available(update_sms_gateway)" value="true" scope="session">	
						<display:column title="Edit" media="html">
							<a href="javascript:updateSMSGateway('<bean:write name="data" property="alias" />')">Edit</a>
						</display:column>
					</logic:equal>

					</display:table>

				</logic:notEmpty>

			</div>

		

 </html:form>
	</div>




</body>
