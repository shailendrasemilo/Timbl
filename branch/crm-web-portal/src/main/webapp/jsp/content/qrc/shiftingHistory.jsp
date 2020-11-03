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
<link href="css/tcal.css" rel="stylesheet" type="text/css" />
<link href="css/gis.css" rel="stylesheet" media="screen" />
<link href="css/displaytag.css" rel="stylesheet" type="text/css" />
<link href="css/ina.css" rel="stylesheet" media="screen" />
<link href="css/qrc.css" rel="stylesheet" media="screen" />
</head>
<body style='background: white;'>

	<logic:present name="qrcForm" property="crmShiftingWorkflowPojos">
		<display:table id="custShifting" name="${ qrcForm.crmShiftingWorkflowPojos }" class="dataTable tList" requestURI="" style="width: 100%;" pagesize="10">
			<display:setProperty name="paging.banner.placement" value="bottom" />
			<display:column title="Sr. No.">
				<bean:write name="custShifting_rowNum" />
			</display:column>
			<display:column title="Workflow ID" property="workflowId" />
			<%-- 
			<display:column title="Network Partner">
				<bean:write name="crmRoles" property="displayEnum(PartnerPojo,${ custShifting.npId})" scope="session" />
			</display:column>
		  --%>
			<display:column title="Stage">
				<bean:write name="crmRoles" property="displayEnum(CRMOperationStages,${custShifting.workflowStage})" scope="session" />
			</display:column>
			<display:column title="Initiated By" style="text-decoration:none">
				<logic:notEmpty name="custShifting" property="createdTime">
					<a href="#" title="Initiation Date : <bean:write name="crmRoles" property="toDate(${custShifting.createdTime})" scope="session"/>"
						style="text-decoration: none"> <bean:write name="custShifting" property="createdBy" /></a>
				</logic:notEmpty>
			</display:column>
			<display:column title="Completed By">
				<logic:equal name="custShifting" property="status" value="PS">
					<a href="#" title="Completion Date : <bean:write name="crmRoles" property="toDate(${custShifting.lastModifiedTime})" scope="session"/>"
						style="text-decoration: none"> <bean:write name="custShifting" property="lastModifiedBy" /></a>
				</logic:equal>
				<logic:notEqual name="custShifting" property="status" value="PS">
		-
		</logic:notEqual>
			</display:column>
			<display:column title="Status">
				<bean:write name="crmRoles" property="displayEnum(WorkflowStatus,${custShifting.status})" scope="session" />
			</display:column>
		</display:table>
	</logic:present>
</body>
</html>
