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
<logic:present scope="session" name="CrmCustWaiverPojo"> 
        <display:table id="custWaivers" name="${sessionScope.CrmCustWaiverPojo}" class="dataTable tList" requestURI="" style="width: 100%;" pagesize="10">
		<display:setProperty name="paging.banner.placement" value="bottom" />
		<display:column title="Sr. No."><bean:write name="custWaivers_rowNum" /></display:column>
		<display:column title="Waiver Type" property="waiverType"/>
		<display:column title="Waiver Head" property="waiverHead"/>
		<display:column title="Waiver Amount" property="waiverAmount"/>
		<display:column title="Bill No." property="billNo"/>
		<display:column title="Ticket ID" property="srTicketId"/>
		<display:column title="Status" >
		<logic:equal name="custWaivers" property="status" value="WP" >	
		<a href="#" style="text-decoration: none;" title="Posted on <bean:write name="crmRoles" property="xmlDate(${ custWaivers.waiverPostedDate })"/>" > 
		<bean:write name="crmRoles" property="displayEnum(WaiverStatus,${custWaivers.status})" />
		</a>
		</logic:equal>
		<logic:notEqual name="custWaivers" property="status" value="WP" >	
			<bean:write name="crmRoles"  property="displayEnum(WaiverStatus,${custWaivers.status})" />		
		</logic:notEqual>		
		</display:column>
		<display:column title="Created By" property="createdBy"/>
		<display:column title="Created Date"><bean:write name="crmRoles" property="xmlDate(${ custWaivers.createdTime })"/> </display:column>
	</display:table>
</logic:present>
</body>
</html>
