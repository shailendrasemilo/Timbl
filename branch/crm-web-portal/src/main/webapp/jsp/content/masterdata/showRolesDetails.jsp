<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib uri="/WEB-INF/tlds/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/tlds/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/tlds/displaytag.tld" prefix="display"%>
<%@taglib uri="/WEB-INF/tlds/struts-html.tld" prefix="html"%>
<html>
<head>
<link href="css/common.css" rel="stylesheet" media="screen" />
<link href="css/color-${initParam.client }.css" rel="stylesheet" media="screen" />
<link href="css/masterdata.css" rel="stylesheet"	media="screen" />
<link href="css/userManagement.css" rel="stylesheet"	media="screen">
<link href="css/emailManagement.css" rel="stylesheet" media="screen">
<link href="css/lms.css" rel="stylesheet" media="screen">
<script src="javascript/jquery.min.js" type="text/javascript"></script>
<script src="javascript/jquery.validate.js" type="text/javascript"></script>
<script src="javascript/masterdata.js" type="text/javascript"></script>
<link href="css/displaytag.css" rel="stylesheet" type="text/css" />
</head>
<body>
	<div id="popupSection">
		<div class="popupSection">
			<h2>CRM Role Details</h2>
			<logic:present name="CrmRolesPojo">
				<display:table id="role" name="${requestScope.CrmRolesPojo}" class="dataTable" style="width:100%" requestURI="">
					<display:column title="Sr.No" maxLength="5"><bean:write name="role_rowNum" /></display:column>
					<display:column title="Role Description" property="roleDescription"></display:column>
					<display:column title="User Role Description" >
						<p align="left" class="marginleft30"><bean:write name="role" property="userRoleDescription" filter="false" /></p>
					</display:column>
				</display:table>
			</logic:present>
		</div>
	</div>
</body>
</html>