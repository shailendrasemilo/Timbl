<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<%@taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<head>

<link href="css/displaytag.css" rel="stylesheet" type="text/css" />
<link href="css/common.css" rel="stylesheet" media="screen" />
<link href="css/color-${initParam.client }.css" rel="stylesheet" media="screen" />
<link href="css/masterdata.css" rel="stylesheet" media="screen" />
<link href="css/userManagement.css" rel="stylesheet" media="screen" />
<script src="javascript/jquery.min.js" type="text/javascript"></script>
<script src="javascript/jquery.validate.js" type="text/javascript"></script>
<script src="javascript/masterdata.js" type="text/javascript"></script>
<script src="javascript/common.js" type="text/javascript"></script>


<style>
ul.add_role_group {
	margin: 0;
	padding: 0;
	list-style: none;
}

ul.add_role_group li {
	float: left;
}
</style>
</head>
<body>
	

		<div class="popUp1">


			
			<div class="popUpContainer">
			<% int i =0; %>
				<logic:notEmpty name="parameterGroupForm" property="groupPojo">
				
					<p class="popUpRow<%=++i%2%>">
						<font class="popUpHead">Parameter Group Name:</font> <font
							class="popUpText"><bean:write name="parameterGroupForm"
								property="groupPojo.groupName" /></font>
					</p>
					<p class="popUpRow<%=++i%2%>">
						<font class="popUpHead">Parameter Group Description:</font> <font
							class="popUpText"><bean:write name="parameterGroupForm"
								property="groupPojo.groupDescription" /></font>
					</p>
					<p class="popUpRow<%=++i%2%>">
						<font class="popUpHead">Parameter Group Status:</font>
							<logic:notEmpty name="parameterGroupForm" property="groupPojo.status">
								<bean:write name="crmRoles" property="displayEnum(PartialStatus,${parameterGroupForm.groupPojo.status})" scope="session" />
							</logic:notEmpty>
					</p>
				</logic:notEmpty>
				<div class="roleDisplayTable widthAuto">
					<p class="headerDisplayTable">
						<span>Parameter Name</span>
						<span>Parameter Value</span>
						<span>Status</span>
						
					</p>
					<div class="contentDisplayTable">

						<logic:notEmpty name="parameterGroupForm" property="accessGroups">
							<logic:iterate id="accessGroups" name="parameterGroupForm" 
								property="accessGroups">
							<p class="popUpRow<%=++i%2%>">
								<span><bean:write name="accessGroups" property="crmParameter.parameterName" /></span>
								<span><bean:write name="accessGroups" property="parameterValue" /></span>
								<span>
									<logic:notEmpty name="accessGroups" property="status">
								<bean:write name="crmRoles" property="displayEnum(PartialStatus,${accessGroups.status})" scope="session" />
							</logic:notEmpty>
								</span>
							</p>
							</logic:iterate>



						</logic:notEmpty>
						<logic:empty name="parameterGroupForm" property="accessGroups">
				No Parameters Assigned
		
		</logic:empty>
					

				<logic:empty name="parameterGroupForm" property="groupPojo">
					<p class="popUpRow1">NO Data Found</p>
				</logic:empty>
					</div>
				</div>

			</div>

		</div>
	
</body>

