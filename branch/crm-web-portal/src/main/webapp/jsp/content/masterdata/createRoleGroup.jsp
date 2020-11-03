<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<%@taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@taglib uri="http://struts.apache.org/tags-nested" prefix="nested"%>


<div id="section">
<html:form action="/roleGroup" styleId="roleGroup">
<logic:empty name="roleGroupForm" property="groupPojo">

 <div class="section">
   <h2>Role group</h2>
   <span class="success_message">
    <logic:messagesPresent message="true" property="appMessage">
			<html:messages id="message" message="true" property="appMessage">
				<bean:write name="message" />
			</html:messages>
		</logic:messagesPresent>
		</span>
		<span class="error_message"> <html:errors property="accessGroups" /> </span>
		<span class="error_message"> <html:errors property="appError" /> </span>
		
		
   <div class="inner_section">
  <div class="inner_left floatl createUserLeft ">
   <p class="flaot1 clear"><b>Role Group Name</b>  <html:text styleClass="textbox" property="roleGroupName" styleId="rolegroup_name" ></html:text> 
 <span class="error_message"> 
 <html:errors property="roleGroupName"/>
 </span>
   
    </p>
     <p class="flaot1 clear"><b>Role Group Description</b> <html:textarea styleClass="textarea"
			property="roleGroupDescription" styleId="rolegroup_description" cols="16" rows="8"></html:textarea> 
		<html:errors property="roleGroupDescription"/>
	</p>
     
   <div id="role_group_view">
   	 
   
     <span class="addbtn_role_group"><a href="javascript:void(0)" id="add" class="grey_button_add" ><span>Add</span></a></span>
   
    <div class="display_group">
    <div class="stays-at-top">
     
        <span class="heading_text">   Roles Name</span>
        <span class="heading_text marginleft485"><a href="javaScript:showRolesDetails();">Show Role Details</a></span>    
     </div>
     <ul class="add_role_group">
      
     <logic:notEmpty name="roleGroupForm" property="accessGroups">
		<logic:iterate id="accessGroups" name="roleGroupForm" property="accessGroups" indexId="ctr">
			
			<logic:equal name="accessGroups" property="editable" value="true">
			<li class="${ctr % 2 eq 0 ? 'first' : 'second' }">
       
    
    <html:hidden name="accessGroups" property="editable" value="true" indexed="true" /> 
    <html:hidden name="accessGroups" property="status" value="${accessGroups.status}" indexed="true"/>
        <span class="dropdownWithoutJs">
           	<html:select name="accessGroups" property="crmRoles.roleId" indexed="true">
				<html:optionsCollection name="roleGroupForm" property="crmRoles" label="roleDescription" value="roleId" />
			</html:select> 
        </span>
        
        <span class="padding"><html:checkbox name="accessGroups" property="readAccess" value="true" indexed="true" styleClass="marginright9">View</html:checkbox></span>
        <span class="padding"><html:checkbox name="accessGroups" property="createAccess" value="true"	indexed="true" styleClass="marginright9">Create</html:checkbox></span>
        <span class="padding"><html:checkbox name="accessGroups" property="updateAccess" value="true"	indexed="true" styleClass="marginright9">Update</html:checkbox></span>
        <span class="padding"><html:checkbox name="accessGroups" property="deleteAccess" value="true"	indexed="true" styleClass="marginright9">Delete</html:checkbox></span>
        <span class="padding"><html:checkbox name="accessGroups" property="recoverAccess" value="true" indexed="true" styleClass="marginright9">Recover</html:checkbox></span>
        <span>
        
        <html:link href="javascript:deleteAccessGroupFromRole(${ctr})" styleClass="close" >
        <img src="images/bg/delete.png" align="absmiddle" title="delete" /></html:link></span>
      </li>
      
      </logic:equal>
     
      </logic:iterate>
      </logic:notEmpty>
       </ul>
     
      </div>
     </div> 
    </div>
   
    <div class="floatr inner_right">
     <a href="#" id="submit_createRoleGroup" class="main_button">
     Save</a>
    </div>
    <p class="clear"></p>
     </div>
   <div>
  
   </div>
   
   
  
 </div>
 </logic:empty>
 
 <!-- ----------------------------------------------------------------------------------->
 
 
 <logic:notEmpty name="roleGroupForm" property="groupPojo">

 <div class="section">
 
    
		
   <h2>Update Role group</h2>    
		<span class="error_message"> <html:errors/> </span>
		<span class="success_message">
   				<logic:messagesPresent message="true" property="appMessage">
				<html:messages id="msg" message="true" property="appMessage">
				<bean:write name="msg" />
				</html:messages>
				</logic:messagesPresent>
	</span>
   <div class="inner_section">
   <div class="inner_left floatl createUserLeft">
   <p><b>Role Group Name:</b> 
   
   <span class="showTextbox"> <bean:write name="roleGroupForm" property="roleGroupName" /> </span>
   <html:hidden name="roleGroupForm" property="roleGroupName" value="${roleGroupForm.roleGroupName}"/>
   <html:hidden name="roleGroupForm" property="roleGroupId" value="${roleGroupForm.roleGroupId}"/>
   <html:errors property="roleGroupName"/>
   </p>
     <p><b>Role Group Description:</b> <html:textarea styleClass="textarea"
								property="roleGroupDescription"
								styleId="rolegroup_description"></html:textarea>
								<html:errors property="roleGroupDescription"/> </p>
     
   <div id="role_group_view">
   
   
   
     <span class="addbtn_role_group"><a href="javascript:void(0)" id="add" class="grey_button_add" ><span>Add</span></a></span>
   
    <div class="display_group">
    <div class="stays-at-top">
     
        <span class="heading_text">   Roles Name</span>    
     </div>
     <ul class="add_role_group">
      
      	<logic:notEmpty name="roleGroupForm" property="accessGroups">
		<logic:iterate id="accessGroups" name="roleGroupForm"
									property="accessGroups" indexId="ctr">
		<logic:equal name="accessGroups" property="editable" value="true">
	    <li class="first">
     	<!--<html:checkbox name="accessGroups"	property="editable" value="true" indexed="true"	onclick="return false"></html:checkbox>-->
    	 <html:hidden name="accessGroups" property="editable" value="true" indexed="true" /> 
        
           <logic:notEqual name="accessGroups" property="recordId" value="0">
           <span class="showDropbox">
				<bean:write name="accessGroups" property="crmRoles.roleDescription" /></span>
				<html:hidden name="accessGroups" property="recordId" indexed="true" value="${accessGroups.recordId}"/>
				<html:hidden name="accessGroups" property="crmRoles.roleId" indexed="true" value="${accessGroups.crmRoles.roleId}"/>
				<html:hidden name="accessGroups" property="crmRoles.roleName" indexed="true" value="${accessGroups.crmRoles.roleName}"/>
				<html:hidden name="accessGroups" property="crmRoles.roleDescription" indexed="true" value="${accessGroups.crmRoles.roleDescription}"/>
				<html:hidden name="accessGroups" property="crmRoles.status" indexed="true" value="${accessGroups.crmRoles.status}"/>
			</logic:notEqual> 
		
			<logic:equal name="accessGroups" property="recordId" value="0">
				<span class="dropdownWithoutJs">
					<html:select name="accessGroups" property="crmRoles.roleId" indexed="true"> 
					<html:optionsCollection name="roleGroupForm" property="crmRoles" label="roleDescription" value="roleId" />
					</html:select>
				</span>
			</logic:equal> 
        <span class="padding"><html:checkbox name="accessGroups" property="readAccess" value="true"		indexed="true" styleClass="marginright9">View</html:checkbox></span>
        <span class="padding"><html:checkbox name="accessGroups" property="createAccess" value="true"	indexed="true" styleClass="marginright9">Create</html:checkbox></span>
        <span class="padding"><html:checkbox name="accessGroups" property="updateAccess" value="true"	indexed="true" styleClass="marginright9">Update</html:checkbox></span>
        <span class="padding"><html:checkbox name="accessGroups" property="deleteAccess" value="true"	indexed="true" styleClass="marginright9">Delete</html:checkbox></span>
        <span class="padding"><html:checkbox name="accessGroups" property="recoverAccess" value="true" indexed="true" styleClass="marginright9">Recover</html:checkbox></span>
        
        <span class="padding"> 
			<html:radio name="accessGroups" property="status" value="A" indexed="true">Active</html:radio>
			<html:radio name="accessGroups" property="status" value="I" indexed="true">Inactive</html:radio> 
		</span>
  
        <logic:equal name="accessGroups" property="recordId" value="0">
        	<span>
        		<html:link href="javascript:deleteAccessGroupFromRole(${ctr})" styleClass="close" >
       		 	<img src="images/bg/delete.png" align="absmiddle" title="delete"/></html:link>
       		 </span>
        </logic:equal>
      </li>     
      </logic:equal>
      </logic:iterate>
      </logic:notEmpty>

     </ul>
     
      </div>
     </div> 
    </div>
   
    <div class="floatr inner_right">
    <a href="#" id="submit_modifyRoleGroup" class="main_button">Update</a>
    </div>
    <p class="clear"></p>
     </div>
   <div>
   </div>
   
 </div>
 </logic:notEmpty> 
 <html:hidden name="roleGroupForm" property="rowCounter" styleId="rowIndex_createRoleGroup" />
 
 </html:form>
</div>




