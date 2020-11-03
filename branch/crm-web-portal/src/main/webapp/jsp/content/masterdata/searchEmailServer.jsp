<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<%@taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<head>
<link href="css/gis.css" rel="stylesheet" media="screen" />
<link href="css/displaytag.css" rel="stylesheet" type="text/css" />
</head>

<body>

<!--page inherits from gis.css(the error messages 'font=gis') -->
<div id="section" align="center">
	<html:form action="/emailServer" styleId="ESsearchForm" >
				<div class="section">
				<h2>Search Email Server</h2>
					<div class="inner_section">				
				 <logic:equal name="crmRoles" property="available(create_email_smtp)" value="true" scope="session">
					<a href="emailServer.do?method=createEmailServerPage" class="link_button">Add Email Server</a>
				</logic:equal>
						</div>
						<p class="clear"></p>
						<span class="error_message"><html:errors property="appError" /></span>
					<span class="success_message">
					<logic:messagesPresent message="true" property="appMessage">
								<html:messages id="msg" message="true" property="appMessage">
								<bean:write name="msg" />
								</html:messages>
					</logic:messagesPresent>					
					</span>
						
						
						<logic:notEmpty name="masterForm" property="searchEmailServerList">
			<display:table id="data"
				name="sessionScope.masterForm.searchEmailServerList" requestURI="" class="dataTable"
				style="width:100%" pagesize="15">
					<display:setProperty name="paging.banner.placement" value="bottom" />
					<display:setProperty name="mymedia" value="true"></display:setProperty>
					<display:setProperty name="export.excel.filename" value="EmailServers-Details.xls" />
					<display:setProperty name="export.csv.filename" value="EmailServers-Details.csv" />
					<display:column title="Sr. No."><bean:write name="data_rowNum" /></display:column>
					<display:column property="subCategory" title="Email Server Name"  />
					<display:column property="host" title="SMTP Host"	 />
					<display:column property="userid" title="SMTP User"	 />
					<display:column property="from" title="From Email ID"	 />
					<display:column property="port" title="SMTP Port"  />
					<display:column title="Status"  >
					<logic:equal  name="data" property="enable" value="true">Enabled</logic:equal>
					<logic:equal  name="data" property="enable" value="false">Disabled</logic:equal>
					</display:column>	
					<display:column title="Authorization"  >
					<logic:equal  name="data" property="auth" value="true">Required</logic:equal>
					<logic:equal  name="data" property="auth" value="false">Not Required</logic:equal>
					</display:column>
				<logic:equal name="crmRoles" property="available(update_email_smtp)" value="true" scope="session">	
					<display:column  title="Edit" media="html">
					<a href="javascript:editEmailServer_ESsearchForm('<bean:write name="data" property="alias" />')" >Edit</a>
					</display:column>
				</logic:equal>
			</display:table>
			</logic:notEmpty>
						<p class="clear"></p>
					</div>
					<p class="clear"></p>
				</div>
				<html:hidden name="masterForm" property="masterAlias" styleId="alias_ESsearchForm"  />
			</html:form>
			</div>
</body>
