<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib uri="/WEB-INF/tlds/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/tlds/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/tlds/displaytag.tld" prefix="display"%>
<link href="../../../css/color-${initParam.client }.css" rel="stylesheet" media="screen">
<link href="../../../css/disaplyTableHelper.css" rel="stylesheet" media="screen" />
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="css/color-${initParam.client }.css" rel="stylesheet" media="screen" />
<link href="css/lms.css" rel="stylesheet" media="screen" />
<link href="css/displaytag.css" rel="stylesheet" type="text/css" />
</head>
<logic:notEmpty name="lmsForm" property="appointmentPojoList">
	<display:table id="leadData" name="lmsForm.appointmentPojoList" class="dataTable" style="width:100%" requestURI="" pagesize="10">
		<display:setProperty name="paging.banner.placement" value="bottom" />
		<display:column title="Sr.No" style="width:3%;">
			<bean:write name="leadData_rowNum" />
		</display:column>
		<display:column title="Appointment Date" property="displayDate" style="width:15%;"></display:column>
		<display:column title="Appointment Time" style="width:15%;">
			<logic:notEmpty name="leadData" property="appointmentTime">
				<bean:write name="crmRoles" property="displayEnum(appointmentTime,${leadData.appointmentTime})" scope="session" />
			</logic:notEmpty>
		</display:column>
		<display:column title="Mode Of Contact" style="width:10%;">
			<logic:notEmpty name="leadData" property="modeOfContact">
				<bean:write name="crmRoles" property="displayEnum(modeOfAppointment,${leadData.modeOfContact})" scope="session" />
			</logic:notEmpty>
		</display:column>
		<display:column title="Contact No." style="width:10%;" property="contactNumber"></display:column>
		<display:column title="Appointment Address" property="appointmentAddress" style="width:15%;"></display:column>
		<display:column title="Created By" style="width:10%;" property="createdBy"></display:column>
		<display:column title="Created Time" style="width:15%;" property="displayCreatedTime"></display:column>
		<display:column title="remarks" style="width:15%;" property="remarks"></display:column>
	</display:table>
</logic:notEmpty>
<logic:empty name="lmsForm" property="appointmentPojoList">Nothing found to display.</logic:empty>
