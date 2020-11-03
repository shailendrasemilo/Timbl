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
			<h2>CRM Parameter Details</h2>
			<logic:present name="CrmParameterPojo">
				<display:table id="param" name="${requestScope.CrmParameterPojo}" class="dataTable" style="width:100%" requestURI="">
					<display:column title="Parameter Name" property="parameterName"></display:column>
					<display:column title="Parameter Description" >
						<p align="left" class="marginleft30"><bean:write name="param" property="parameterDescription" filter="false" /></p>
					</display:column>
				</display:table>
			</logic:present>
		</div>
	</div>
</body>
</html>