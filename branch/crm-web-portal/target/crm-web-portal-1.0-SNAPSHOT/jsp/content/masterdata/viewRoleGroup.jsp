<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<%@taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@taglib uri="http://struts.apache.org/tags-nested" prefix="nested"%>
<head>
 
<link href="css/common.css" rel="stylesheet" media="screen" />
<link href="css/color-${initParam.client }.css" rel="stylesheet" media="screen" />
<link href="css/masterdata.css" rel="stylesheet" media="screen" />
<link href="css/userManagement.css" rel="stylesheet" media="screen" />
<script src="javascript/jquery.min.js" type="text/javascript"></script>
<script src="javascript/jquery.validate.js" type="text/javascript"></script>
<script src="javascript/masterdata.js" type="text/javascript"></script>
<script src="javascript/common.js" type="text/javascript"></script> 

</head>
<body>
	<div id="lightboxPanel">
    
    <div class="popUp1"> 
     <div class="closePopup"><a id="closePanel" href="#"></a></div>
     <div class="popUpContainer">
       <% int i =0; %>
	   
	   <logic:notEmpty name="roleGroupForm" property="groupPojo">
	 	<p class="popUpRow<%=++i%2%>">
         	<font class="popUpHead">Role Group Name:</font>
			
			<font class="popUpText"><bean:write name="roleGroupForm" property="groupPojo.groupName" /></font>
       </p>
       <p class="popUpRow<%=++i%2%>">
         	<font class="popUpHead">Role Group Description:</font>
         	<font class="popUpText"><bean:write name="roleGroupForm"
								property="groupPojo.groupDescription" /></font>
		</p>
		<p class="popUpRow<%=++i%2%>">
         	<font class="popUpHead">Status:</font>
         	<font class="popUpText">
					<logic:notEmpty name="roleGroupForm" property="groupPojo.status">
						<bean:write name="crmRoles" property="displayEnum(PartialStatus,${roleGroupForm.groupPojo.status})" scope="session" />
					</logic:notEmpty>
         	</font>
		</p>
		</logic:notEmpty>
	   
	   
       
       <div class="roleDisplayTable widthAuto">
       <p class="headerDisplayTable"><span>Role Name</span></p>
       <div class="contentDisplayTable">
        
		
		<logic:notEmpty name="roleGroupForm" property="accessGroups">
					<logic:iterate id="accessGroups" name="roleGroupForm"
								property="accessGroups" indexId="ctr">
							
					<logic:equal name="accessGroups" property="editable"
									value="false">
						<html:hidden name="accessGroups" property="editable"
											value="true" indexed="true" onclick="return false"/>
					
					<p class="popUpRow<%=++i%2%>">
										<span><bean:write name="accessGroups" property="crmRoles.roleDescription" /></span> 
										<span class="marginleft30 roleCheck"> 
										<html:checkbox
												name="accessGroups" property="readAccess" value="true"
												indexed="true" onclick="return false" disabled="true">View</html:checkbox></span> 
										<span class="marginleft30 roleCheck"><html:checkbox name="accessGroups" property="createAccess" value="true"
												indexed="true" onclick="return false"  disabled="true">Create</html:checkbox></span>
										<span class="marginleft30 roleCheck"><html:checkbox name="accessGroups"
												property="updateAccess" value="true" indexed="true"
												onclick="return false"  disabled="true">Update</html:checkbox></span> 
										<span class="marginleft30 roleCheck"><html:checkbox name="accessGroups" property="deleteAccess" value="true"
												indexed="true" onclick="return false"  disabled="true">Delete</html:checkbox></span>
										<span class="marginleft30 roleCheck"><html:checkbox name="accessGroups"
												property="recoverAccess" value="true" indexed="true"
												onclick="return false"  disabled="true">Recover</html:checkbox></span>
					</p>
								</logic:equal>
							</logic:iterate>
		</logic:notEmpty>
						
       </div>
       </div>
     </div>
      
    </div>
  </div>

</body>