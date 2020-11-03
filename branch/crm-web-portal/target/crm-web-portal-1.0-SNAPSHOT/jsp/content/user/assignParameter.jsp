<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<%@taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@taglib uri="http://struts.apache.org/tags-nested" prefix="nested"%>
<body>
<div id="section" >
<html:form action="/parameterManagement" >
 <div class="section">
   <h2>Assign Parameter</h2>
   <div class="inner_section">
   <logic:equal name="crmRoles" property="available(create_assign_roles,update_assign_roles,delete_assign_roles,recover_assign_roles)" value="true" scope="session">
    <a href="javascript:assignRoles()" class="link_button">Assign Roles</a>	
    </logic:equal>
      <p><span class="error_message">
     <html:errors property="appError" />
	</span>
	 <span class="success_message">
   <logic:messagesPresent message="true" property="appMessage">
			<html:messages id="msg" message="true" property="appMessage">
				<bean:write name="msg" />
			</html:messages>
	</logic:messagesPresent>
	</span>
	<span class="error_message">
		 <html:errors property="userRolesPojos"/>
		 </span>
		 </p>
    <div class="inner_left_lead floatl marginleft10">
   <p><strong>User ID</strong>
   <span class="showTextbox">
   <logic:notEmpty name="searchUserPojo" scope="session">
   <bean:write name="searchUserPojo" property="userId"/>
   </logic:notEmpty>
   </span>
   </p>
     
   <div id="role_group_view">
   
     <span class="addbtn_role_group"><a href="javascript:void(0)" id="idAddParameter" class="grey_button_add" ><span>Add</span></a></span>
   
    <div class="display_group">
      <div class="stays-at-top">
       
        <span class="heading_text">   Parameter Name</span> 
       
        <span class="heading_text marginleft131"> Parameter Value </span>   
        <span class="heading_text floatr"><a href="javaScript:showParameterDetails();">Show Parameter Details</a></span>
     </div>
     <ul class="add_parameter_group">
      
      <logic:notEmpty name="loginForm" property="userRolesPojos">
     
      
							<logic:iterate id="userRolesPojos" name="loginForm" property="userRolesPojos" indexId="ctr">
					 		<li class="${ctr % 2 eq 0 ? 'first' : 'second' }">
       							<logic:equal name="userRolesPojos" property="recordId" value="0">
	       							<html:hidden name="userRolesPojos" property="userId" value="${searchUserPojo.userId}" indexed="true"/>
	    							<html:hidden name="userRolesPojos" property="createdBy" value="${userPojo.userId}" indexed="true"/>
									<html:hidden name="userRolesPojos" property="editable" value="true" indexed="true" />
									<html:hidden name="userRolesPojos" property="status" value="A" indexed="true"/>
									<span class="dropdownWithoutJs">
									<html:select name="userRolesPojos" property="crmParameter.parameterId" indexed="true">
												<html:optionsCollection name="loginForm" property="crmParameterPojos" label="parameterName" value="parameterId" />
										</html:select>
									</span>
									<span class="marginleft13 inline">
	       					 			<html:text name="userRolesPojos" property="parameterValue" styleClass="textbox"  indexed="true">
										</html:text> 
									</span>									
									<span class="marginleft13 inline">
	        							<html:link href="javascript:deleteParameter(${ctr})"  styleClass="close" >
	        								<img src="images/bg/delete.png" align="absmiddle" title="delete"/>
	        							</html:link>
	        						</span>
								</logic:equal>								
								<logic:notEqual name="userRolesPojos" property="recordId" value="0">
									<html:hidden name="userRolesPojos" property="userId" value="${searchUserPojo.userId}" indexed="true"/>
									<html:hidden name="userRolesPojos" property="lastModifiedBy" value="${userPojo.userId}" indexed="true"/>
									<html:hidden name="userRolesPojos" property="editable" value="true" indexed="true" />
									<span class="showDropbox">
	            					<bean:write name="userRolesPojos" property="crmParameter.parameterName" />	            					
	            					</span>
	            					<html:hidden name="userRolesPojos" property="crmParameter.parameterName" value="${userRolesPojos.crmParameter.parameterName}" indexed="true" />
	            					<html:hidden name="userRolesPojos" property="crmParameter.parameterId" value="${userRolesPojos.crmParameter.parameterId}" indexed="true" />
	            					<html:hidden name="userRolesPojos" property="crmParameter.status" value="${userRolesPojos.crmParameter.status}" indexed="true" />
	            					<html:hidden name="userRolesPojos" property="recordId" value="${userRolesPojos.recordId}" indexed="true" />
									<span class="marginleft13 inline">
	       					 			<html:text name="userRolesPojos" property="parameterValue" styleClass="textbox"  indexed="true">
										</html:text> 
									</span>
									<span class="padding"> 
									<html:radio name="userRolesPojos" property="status" value="A" indexed="true">Active</html:radio>
									<html:radio name="userRolesPojos" property="status" value="I" indexed="true">Inactive</html:radio> 
									</span> 
        						</logic:notEqual> 
     				 			</li>
     				 		
     				 				 		
							</logic:iterate>
							</logic:notEmpty>
							  <logic:empty name="loginForm" property="userRolesPojos">
							  <span class="error_message"><br/>
							    &nbsp;Please click on 'Add' button to Assign Parameter.
							  </span>
							  </logic:empty>
     </ul>
      </div>
     </div> 
    </div>
   <div class=" floatl groupSelection">
    <div class="stays-at-top"> <span>Parameter Group's Name</span></div>
  <div class="searchRoles"><input type="text" class="searchAssignRoles search_textbox"  /></div>
   <ul class="searchList" id="searchAssignRolesList" >
    <logic:notEmpty name="loginForm" property="userRolesPojosForGroup">
	<logic:iterate id="userRolesPojosForGroup" name="loginForm" property="userRolesPojosForGroup" indexId="ctr" >
   <li class="${ctr % 2 eq 0 ? 'first' : 'second' }">
    <span>
    
    <logic:equal name="userRolesPojosForGroup"  property="recordId" value="0">
	 	<html:hidden name="userRolesPojosForGroup" property="userId" value="${searchUserPojo.userId}" indexed="true"/>
	 	<html:hidden name="userRolesPojosForGroup" property="status" value="A" indexed="true"/>
	 	<html:hidden name="userRolesPojosForGroup" property="createdBy" value="${userPojo.userId}" indexed="true"/>
	 	<html:checkbox name="userRolesPojosForGroup" property="editable"  indexed="true" value="true" styleClass="marginright9">
	 		<a><bean:write name="userRolesPojosForGroup" property="groups.groupName" /></a>
	 	</html:checkbox>
	    <html:hidden name="userRolesPojosForGroup" property="groups.groupName" value="${userRolesPojosForGroup.groups.groupName}" indexed="true"/>
	    <html:hidden name="userRolesPojosForGroup" property="groups.groupId" value="${userRolesPojosForGroup.groups.groupId}" indexed="true"/>
	    <html:hidden name="userRolesPojosForGroup" property="groups.groupType" value="${userRolesPojosForGroup.groups.groupType}" indexed="true"/>
	    <html:hidden name="userRolesPojosForGroup" property="groups.groupDescription" value="${userRolesPojosForGroup.groups.groupDescription}" indexed="true"/>
	    <html:hidden name="userRolesPojosForGroup" property="groups.status" value="${userRolesPojosForGroup.groups.status}" indexed="true"/>	   
    </logic:equal>
    
    <logic:notEqual name="userRolesPojosForGroup"  property="recordId" value="0">	    
	    <html:hidden name="userRolesPojosForGroup" property="userId" value="${searchUserPojo.userId}" indexed="true"/>
	 	<html:hidden name="userRolesPojosForGroup" property="status" value="${userRolesPojosForGroup.status}" indexed="true"/>
	 	<html:hidden name="userRolesPojosForGroup" property="lastModifiedBy" value="${userPojo.userId}" indexed="true"/>
	    <html:checkbox name="userRolesPojosForGroup" property="editable"  indexed="true" value="true" styleClass="marginright9">
	    	<a><bean:write name="userRolesPojosForGroup" property="groups.groupName" /></a>
	    </html:checkbox>
	    <html:hidden name="userRolesPojosForGroup" property="groups.groupName" value="${userRolesPojosForGroup.groups.groupName}" indexed="true"/>
	    <html:hidden name="userRolesPojosForGroup" property="groups.groupId" value="${userRolesPojosForGroup.groups.groupId}" indexed="true"/>
	    <html:hidden name="userRolesPojosForGroup" property="groups.groupType" value="${userRolesPojosForGroup.groups.groupType}" indexed="true"/>
	    <html:hidden name="userRolesPojosForGroup" property="groups.groupDescription" value="${userRolesPojosForGroup.groups.groupDescription}" indexed="true"/>
	    <html:hidden name="userRolesPojosForGroup" property="groups.status" value="${userRolesPojosForGroup.groups.status}" indexed="true"/>
	    <html:hidden name="userRolesPojosForGroup" property="recordId" value="${userRolesPojosForGroup.recordId}" indexed="true" />
    </logic:notEqual>      
    </span>
    </li>
   
    </logic:iterate>
    </logic:notEmpty>       
   </ul>    
   </div>    
    <div class="floatr inner_right">
     <a href="#" id="submitAssignParameter_assignparameter" class="main_button_multiple">Assign Parameter</a>
    </div>
    <p class="clear"></p>
     </div>
   <div>
  
   </div>
   
   
  
 </div>
 
 <html:hidden name="loginForm" property="rowCounter" styleId="rowIndex_Role" />
 </html:form>
</div>
</body>


