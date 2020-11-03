<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib uri="/WEB-INF/tlds/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/tlds/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/tlds/displaytag.tld" prefix="display"%>
<html>
    <head>
    	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="css/common.css" rel="stylesheet" media="screen" />
        <link href="css/color-${initParam.client }.css" rel="stylesheet" media="screen" />
		<link href="css/userManagement.css" rel="stylesheet" media="screen" />
        <link href="css/emailManagement.css" rel="stylesheet" media="screen" />
        <link href="css/masterdata.css" rel="stylesheet" media="screen" />
        <link href="css/lms.css" rel="stylesheet" media="screen" />
        <link href="css/tcal.css"  rel="stylesheet" type="text/css" />
        <link href="css/gis.css" rel="stylesheet" media="screen" />
        <link href="css/displaytag.css" rel="stylesheet" type="text/css" />
        <link href="css/ina.css" rel="stylesheet" media="screen" />
        <link href="css/qrc.css" rel="stylesheet" media="screen" />
		</head>
		<body style='background: white;'>  
		<logic:notEmpty name="logForm" property="remarksPojoList" scope="session">
		      <display:table id="remarkData" name="sessionScope.logForm.remarksPojoList" class="dataTable" style="width:100%" pagesize="10" requestURI="logAction.do">
				  	<display:setProperty name="paging.banner.placement"	value="bottom" />  
					<display:column title="Sr.No" style="width:3%;" maxLength="5"><bean:write name="remarkData_rowNum" /></display:column>				
					<display:column title="Action" style="width:12%;" >
						<bean:write name="crmRoles" property="displayEnum(CrmActionEnum,${remarkData.actions})" scope="session" />
					</display:column>
					<display:column title="Remarks" property="remarks" style="width:55%;"></display:column>
					<display:column title="Created Time" style="width:15%;">
						<bean:write name="crmRoles" property="xmlDate(${remarkData.createdTime})"/>
					</display:column>
					<display:column title="Created By" property="createdBy" style="width:15%;"></display:column>
			</display:table>
		</logic:notEmpty>
		<logic:empty name="logForm" property="remarksPojoList">
			<span class="error_message"> 
				No Remarks.
			</span>
		</logic:empty>
	</body>
</html>


