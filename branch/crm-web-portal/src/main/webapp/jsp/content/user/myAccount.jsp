<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<%@taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<%@taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<style>
.showTextbox {vertical-align: middle;}
</style>

<div id="section"  align="center">
<html:form action="/login" styleId="changePasswordMyAccount">
 <div class="section">
   <h2>My Profile</h2>
   <div class="inner_section">
   <div class="inner_left floatl myAccount">

     <p><b>User ID:</b><span class="showText">
  			<bean:write name="loginForm"property="crmUserPojo.userId" /></span>
       </p>
       <p ><b>
       First Name:</b><span class="showText"><bean:write name="loginForm"property="crmUserPojo.firstName" /></span></p>
       <p ><b>
       Last Name:</b><span class="showText"><bean:write name="loginForm"property="crmUserPojo.lastName" /></span> </p>
       <p ><b>
       Email ID:</b><span class="showText"><bean:write name="loginForm"property="crmUserPojo.emailId" /></span></p>
        <p ><b>
        Mobile Number:</b><span class="showText"><bean:write name="loginForm"property="crmUserPojo.mobileNo" /></span></p>
       
       <%-- <p ><b>
        Employee Type:</b>
        <span class="showText">
        	<logic:equal name="loginForm" property="crmUserPojo.empType" value="N">Nextra</logic:equal>
         	<logic:equal name="loginForm" property="crmUserPojo.empType" value="P">Partner</logic:equal>
        	<logic:equal name="loginForm" property="crmUserPojo.empType" value="">NA</logic:equal>
      	</span></p>
       
       <p><b>
        Employee Code:</b><span class="showText">
         <logic:equal name="loginForm" property="crmUserPojo.empCode" value="">NA</logic:equal>
		<logic:notEqual name="loginForm" property="crmUserPojo.empCode" value="">
				<bean:write name="loginForm" property="crmUserPojo.empCode" />
		</logic:notEqual>
       </span></p>
       --%>
       <p><b>Password Expiry:</b><span class="showText"><bean:write name="loginForm"property="crmUserPojo.passwordExpiry" /></span></p>
        <p><b>
         Functional Bins:</b>         
         <span class="showText" style="width:300px">
         	<bean:write name="crmRoles" property="displayEnum(functionalBins,${loginForm.crmUserPojo.functionalBin})" scope="session" />
         </span>
       	</p>
       	<p><b>
         Service Names:</b>         
         <span class="showText" style="width:300px">
         	<bean:write name="crmRoles" property="displayEnum(Product,${loginForm.serviceNames})" scope="session" />
         </span>
       	</p>
       	<p><b>
         Partner Names:</b>         
         <span class="showText" style="width:300px">
         	<bean:write name="crmRoles" property="displayEnum(PartnerPojo,${loginForm.partnerNames})" scope="session" />
         </span>
       	</p>
       	<p><b>
         SR Code:</b>         
         <span class="showText">
         <logic:notEmpty name="loginForm" property="crmUserPojo.srCode">
         	<bean:write name="loginForm"property="crmUserPojo.srCode" />
         </logic:notEmpty>
          <logic:empty name="loginForm" property="crmUserPojo.srCode" >-</logic:empty>
         </span>
       	</p>
       	
        <p ><b>
        Created By:</b><span class="showText"><bean:write name="loginForm"property="crmUserPojo.createdBy" /></span></p>
        <p ><b>
        Created Date and Time:</b><span class="showText"><bean:write name="crmRoles" property="xmlDate(${loginForm.crmUserPojo.createdTime})"/>
       </span></p>
       <p ><b>
        Last Modified By:</b>
        <span class="showText">
        	 <logic:notEmpty name="loginForm"property="crmUserPojo.lastModifiedBy">
				<bean:write name="loginForm" property="crmUserPojo.lastModifiedBy" /></logic:notEmpty> 
			<logic:empty name="loginForm" property="crmUserPojo.lastModifiedBy" >-</logic:empty>
       	</span></p>
       	<p><b>
        Last Modified Date and Time:</b>
        <span class="showText">
        	 <logic:notEmpty name="loginForm"property="crmUserPojo.lastModifiedTime">
        	 	<bean:write name="crmRoles" property="xmlDate(${loginForm.crmUserPojo.lastModifiedTime})"/>
			 </logic:notEmpty> 
			<logic:empty name="loginForm" property="crmUserPojo.lastModifiedTime" >-</logic:empty>
       	</span></p>
      <p ><b>
        Current User Status:</b><span class="showText">
		<logic:notEmpty name="loginForm" property="crmUserPojo.status">
			<bean:write name="crmRoles" property="displayEnum(UserStatus,${loginForm.crmUserPojo.status})" scope="session" />
		</logic:notEmpty>
    </span></p>
    <p><b>
        Last Lock Time:</b><span class="showText">
         <logic:notEmpty name="loginForm"property="crmUserPojo.lockingTime">
         	<bean:write name="crmRoles" property="xmlDate(${loginForm.crmUserPojo.lockingTime})"/>
		</logic:notEmpty> 
		<logic:empty name="loginForm" property="crmUserPojo.lockingTime" >-</logic:empty>
       </span></p>
    </div>
	
	<div class="inner_left floatl margintop10 marginleft131">
      <span class="rockwell ">Change Password</span>
      
      <p class="serverSideMessage">
        	<span class="success_message">
        	 <logic:messagesPresent message="true" property="appMessage">
			<html:messages id="msg" message="true" property="appMessage">
			<bean:write name="msg" />
			</html:messages>
			</logic:messagesPresent>
		</span>
		<span class="error_message">
						<html:errors property="appError"/>
					</span>
		</p>
		
      <div class="borderChangePassword ">
          <p><b>Current Password:</b>
        	<html:password  styleClass="textbox" styleId="oldPassword" property="oldPassword" maxlength="15" value=""/></p>
        <p><b>New Password:</b>
      		 <html:password   styleClass="textbox"  property="newPassword" styleId="newPassword" maxlength="15" value=""/></p>
        <p><b>Confirm Password:</b>
       		 <html:password  styleClass="textbox"  property="confirmPassword" styleId="confirmNewPassword" maxlength="15" value=""/></p>
       		 <p class="clear" align="right"><span class="error_message"  style="font:9px arial">(Please use combination of numbers, uppercase, lowercase and special characters with length 8 - 15.)</span></p>
        <p><b> Verification Text:</b><img src="Captcha.jpg" border="0" Id="captcha" alt="captcha" align="absmiddle" class="captcha"/></b>
        &nbsp;&nbsp;&nbsp;&nbsp;<html:link href="#" styleClass="grey_button" onclick="return reloadCaptcha('captcha');">Reload</html:link>
        </p>
        <html:hidden property="changePasswordFlag" value="1"/>
        <logic:messagesPresent message="true" property="captchaReenter" >
        	<p class="serverSideMessage">
		 	<span class="error_message">
		 		<html:messages id="msg" property="captchaReenter" message="true">
              		<bean:write name="msg" />
        		</html:messages>
        	</span>
        	</logic:messagesPresent> 
        <p>
		<b> Enter the verification text:</b> <input class="textbox" name="answer" type="text" id="answer" /></p>
       
        <p class="clear margintop10"><br/>
		<b></b> 
        	
			<html:link href="#" styleClass="yellow_button" styleId="submit_changePasswordMyAccount" >Change Password</html:link></p>
        </div>
    </div>
    <p class="clear"></p>
     </div>

<!-- ------------------------------------------------------------------------------ -->
       <logic:notEmpty name="loginForm" property="userRolesPojos">
     <div id="role_group_view1" class="margintop20">
     <div class="display_group floatl minWidth716">
    <div class="stays-at-top">
        <span class="heading_text">   Roles Name</span>    
		  <span class="heading_text marginleft155 ">   Access Details</span>  
        <span class="heading_text marginleft260">   Status</span>    
     </div>
     <ul class="add_group">
      <% int roleCount=0; %>
     <logic:iterate id="userRolesPojos" name="loginForm" property="userRolesPojos" indexId="ctr">      
       <logic:notEmpty name="userRolesPojos" property="crmRoles">
       <%roleCount++; %>
       <% if(roleCount%2 == 0){ %>							
      			<li class="first">
      			<% } else { %>
       				<li class="second">
       			<% } %>
      <span class="showDropbox"><bean:write name="userRolesPojos" property="crmRoles.roleDescription" /></span>       
           <span ><html:checkbox name="userRolesPojos" property="readAccess" value="true"		indexed="true" styleClass="marginright9" disabled="true" >View</html:checkbox></span>
           <span  ><html:checkbox name="userRolesPojos" property="createAccess" value="true"	indexed="true" styleClass="marginright9" disabled="true">Create</html:checkbox></span>
           <span  ><html:checkbox name="userRolesPojos" property="updateAccess" value="true"	indexed="true" styleClass="marginright9" disabled="true">Update</html:checkbox></span>
           <span ><html:checkbox name="userRolesPojos" property="deleteAccess" value="true"	indexed="true" styleClass="marginright9" disabled="true">Delete</html:checkbox></span>
           <span ><html:checkbox name="userRolesPojos" property="recoverAccess" value="true" indexed="true" styleClass="marginright9" disabled="true">Recover</html:checkbox></span>
		   <span class="marginleft40" >
			 <logic:notEmpty name="userRolesPojos" property="status">
					<bean:write name="crmRoles" property="displayEnum(AciveInactiveStatus,${userRolesPojos.status})" scope="session" />
			</logic:notEmpty>
        </span>    
         </li>
          
         </logic:notEmpty>        
       </logic:iterate>
        
       
       <%if(roleCount==0){%> 
        <p class="first">
        <b>No Role assigned.</b>
        </p>
       <%} %>
         </ul>
      </div>
      <div class="display_group floatl marginleft30">
    <div class="stays-at-top">
        <span class="heading_text">   Role Groups</span>    
     </div>
     <ul class="add_group">
     <% int roleGroupCount=0; %>
 <logic:iterate id="userRolesPojos" name="loginForm" property="userRolesPojos" indexId="ctr"> 
       
	<logic:notEmpty name="userRolesPojos" property="groups">
	<% roleGroupCount++; %>
	<logic:equal name="userRolesPojos" property="groups.groupType" value="R">
	 <logic:equal name="userRolesPojos" property="status" value="A">	
		<% if(roleGroupCount%2 == 0){ %>							
     		 <li class="first">
      			<% } else { %>
       			<li class="second">
       	<% } %>
         <span>      	
	          	<span><bean:write name="userRolesPojos" property="groups.groupName" /></span>
	          		&nbsp;&nbsp;&nbsp;<span>Active</span>         
         </span>
	    </li>
	 </logic:equal>
	</logic:equal>
    </logic:notEmpty> 
      
</logic:iterate>
<%if (roleGroupCount ==0){%> 
 <p class="first">
     <b> &nbsp;&nbsp;No role group assigned.</b>
     </p>
<%} %>
	</ul>
	</div>
<p class="clear"></p>
</div>
</logic:notEmpty>
 
 <logic:notEmpty name="loginForm"property="userRolesPojos">
 <div id="role_group_view1" class="margintop20">
     <div class="display_group floatl minWidth716">
    <div class="stays-at-top">
        <span class="heading_text">   Parameter Name</span>    
        <span class="heading_text marginleft155">   Values</span>    
		  <span class="heading_text marginleft200">   Status</span>    
     </div>
     <ul class="add_group">
     <% int parameterCount=0; %>
	  <logic:notEmpty name="loginForm"property="parameterList">
     <logic:iterate id="userRolesPojos" name="loginForm" property="parameterList" indexId="ctr">
       
       <logic:notEmpty name="userRolesPojos" property="crmParameter">
      <%parameterCount++;%> 
       <% if(parameterCount%2 == 0){ %>							
      			<li class="first">
      			<% } else { %>
       				<li class="second">
       			<% } %>
      <span class="showDropbox floatl"><bean:write name="userRolesPojos" property="crmParameter.parameterName" /></span>
         <span class="showTextbox marginleft13">	<bean:write name="userRolesPojos" property="parameterValue" /></span>
         <span class="marginleft40">
		  <logic:notEmpty name="userRolesPojos" property="status">
				<bean:write name="crmRoles" property="displayEnum(AciveInactiveStatus,${userRolesPojos.status})" scope="session" />
			</logic:notEmpty>
		</span>
         </li>
         </logic:notEmpty>
        
         </logic:iterate>
         </logic:notEmpty>
        <% if(parameterCount ==0){%>
         <p class="first">
       <b>&nbsp;&nbsp;No parameter assigned.</b>
       </p>
		<%} %>
	
        </ul>
      </div>
      <div class="display_group floatl marginleft30">
    <div class="stays-at-top">
        <span class="heading_text"><span>Parameter Group </span>  
     </div>
     <ul class="add_group">
      <% int parameterGroupCount=0; %>
	    <logic:notEmpty name="loginForm"property="parameterList">
 <logic:iterate id="userRolesPojos" name="loginForm" property="userRolesPojos" indexId="ctr">
      
	<logic:notEmpty name="userRolesPojos" property="groups">
	<logic:equal name="userRolesPojos" property="groups.groupType" value="P">	
	<% parameterGroupCount++;%>
		<% if(parameterGroupCount%2 == 0){ %>							
     		 <li class="first">
      			<% } else { %>
       			<li class="second">
       	<% } %>
         <span >
         	<logic:equal name="userRolesPojos" property="status" value="A">
	          	<span><bean:write name="userRolesPojos" property="groups.groupName" /></span>
	          	<span>Active</span>
	          </logic:equal>
         </span>
	</li>
	</logic:equal>
    </logic:notEmpty>
     
</logic:iterate>
 </logic:notEmpty>

 <%if(parameterGroupCount==0){%>
  <p class="first">
     <b>&nbsp;&nbsp;No parameter group assigned.</b>
     </p>
<%} %>
   
	</ul>
	</div>
<p class="clear"></p>
</div>

</logic:notEmpty>


 
 
 <div id="role_group_view1" class="margintop20" style="width:718px;">
     <div class="display_group floatl " style="width:100%;">
    <div class="stays-at-top">
        <span class="heading_text" >   State </span>    
        <span class="heading_text " style="margin-left:162px;">   City</span>  
         <span class="heading_text " style="margin-left:160px;">   Area</span>    
		  <span class="heading_text " style="margin-left:150px;">   Status</span>    
     </div>
     <ul class="add_group">
     <% int areaCount=0; %>
     <logic:notEmpty name="loginForm"property="areaMappingPojos">
     <logic:iterate id="areaMappingPojos" name="loginForm" property="areaMappingPojos" indexId="ctr">
      
      <%areaCount++;%> 
       <% if(areaCount%2 == 0){ %>							
      			<li class="first">
      			<% } else { %>
       				<li class="second">
       			<% } %>
      <span class="showTextbox floatl" style="width:160px;"><bean:write name="areaMappingPojos" property="stateName" /></span>
         <span class="showTextbox marginleft13" style="width:160px;">	<bean:write name="areaMappingPojos" property="cityName" /></span>
           <span class="showTextbox marginleft13" style="width:160px;">	<bean:write name="areaMappingPojos" property="areaName" /></span>
         <span style="margin-left:18px;">
		  <logic:notEmpty name="areaMappingPojos" property="status">
				 <bean:write name="crmRoles" property="displayEnum(AciveInactiveStatus,${areaMappingPojos.status})" scope="session" />
		</logic:notEmpty>   
		</span>
         </li>
     
        
         </logic:iterate>
         </logic:notEmpty>
         
        <% if(areaCount ==0){%>
         <p class="first">
       <b>&nbsp;&nbsp;No Area assigned.</b>
       </p>
		<%} %>
	
        </ul>
      </div>
      <div class="display_group floatl marginleft30">
   
    
	</div>
<p class="clear"></p>
</div>




 <logic:empty name="loginForm" property="userRolesPojos">
  <p class="first">
 <b>No access assigned.</b>
 </p>
</logic:empty>
     <!-- table -->
</div>
</html:form>
</div>
