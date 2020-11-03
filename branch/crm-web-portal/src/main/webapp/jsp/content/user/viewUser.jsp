<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@taglib uri="http://displaytag.sf.net" prefix="display"%>
<%@taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<%@taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<html>
<head>
<link href="css/common.css" rel="stylesheet" media="screen" />
<link href="css/color-${initParam.client }.css" rel="stylesheet" media="screen" />
<link href="css/displaytag.css" rel="stylesheet" type="text/css" />
<script src="javascript/jquery.min.js" type="text/javascript"></script>
<script src="javascript/common.js" type="text/javascript"></script>

</head>
<body >
<div class="popUp1">
<div class="popUpContainer">
     <div class="contentContainer">
     <% int i =0;
     %>
       <p class="popUpRow<%=++i%2%>">
         <font class="popUpHead">User ID:</font>
         <font class="popUpText"><bean:write name="loginForm" property="crmUserPojo.userId" /></font>
       </p>
       
       <p class="popUpRow<%=++i%2%>">
         <font class="popUpHead">First Name:</font>
         <font class="popUpText"><bean:write name="loginForm" property="crmUserPojo.firstName" /></font>
       </p>
       <p class="popUpRow<%=++i%2%>">
         <font class="popUpHead">Last Name:</font>
         <font class="popUpText"><bean:write name="loginForm" property="crmUserPojo.lastName" /></font>
       </p>
       <p class="popUpRow<%=++i%2%>">
         <font class="popUpHead">Functional Bins:</font>
         <font class="popUpText">
         <bean:write name="crmRoles" property="displayEnum(functionalBins,${loginForm.crmUserPojo.functionalBin})" scope="session" />
      	</font>
       </p>
       <p class="popUpRow<%=++i%2%>">
         <font class="popUpHead">Service Names:</font>
         <font class="popUpText">
         <bean:write name="crmRoles" property="displayEnum(Product,${loginForm.serviceNames})" scope="session" />
      	</font>
       </p>
       <p class="popUpRow<%=++i%2%>">
         <font class="popUpHead">Partner Names:</font>
         <font class="popUpText">
         <bean:write name="crmRoles" property="displayEnum(PartnerPojo,${loginForm.partnerNames})" scope="session" />
      	</font>
       </p>
       <p class="popUpRow<%=++i%2%>">
         <font class="popUpHead">SR Code:</font>
         <font class="popUpText"><logic:notEmpty name="loginForm" property="crmUserPojo.srCode">
		<bean:write name="loginForm" property="crmUserPojo.srCode" /></logic:notEmpty> 
		<logic:empty name="loginForm" property="crmUserPojo.srCode" >-</logic:empty></font>
       </p>
        <p class="popUpRow<%=++i%2%>">
         <font class="popUpHead">Email ID:</font>
         <font class="popUpText"><bean:write name="loginForm"property="crmUserPojo.emailId" /></font>
       </p>
        <p class="popUpRow<%=++i%2%>">
         <font class="popUpHead">Mobile Number:</font>
         <font class="popUpText"><bean:write name="loginForm"property="crmUserPojo.mobileNo" /></font>
       </p>
       <%-- <p class="popUpRow<%=++i%2%>">
         <font class="popUpHead">Employee Type:</font>
         <font class="popUpText">
         
         <logic:equal name="loginForm" property="crmUserPojo.empType" value="N">
        	 Nextra
         </logic:equal>
          <logic:equal name="loginForm" property="crmUserPojo.empType" value="P">
        	Partner
        </logic:equal>
        <logic:equal name="loginForm" property="crmUserPojo.empType" value="">
        	NA
        </logic:equal>
        </font>
       </p> 
        <p class="popUpRow<%=++i%2%>">
       <logic:equal name="loginForm" property="crmUserPojo.empType" value="P">
			<font class="popUpHead">Partner Name:</font>
				<font class="popUpText">
					<bean:write name="loginForm"property="crmUserPojo.partner.partnerName" />
				</font>
		</logic:equal>		
       <logic:equal name="loginForm" property="crmUserPojo.empType" value="N">
         	<font class="popUpHead">Employee Code:</font>
        	<font class="popUpText">
        	<logic:equal name="loginForm" property="crmUserPojo.empCode" value="">NA</logic:equal>
			<logic:notEqual name="loginForm" property="crmUserPojo.empCode" value="">
				<bean:write name="loginForm" property="crmUserPojo.empCode" />
			</logic:notEqual>
			</font>
       </logic:equal>
       </p> --%>
       <p class="popUpRow<%=++i%2%>">
         <font class="popUpHead">Password Expiry:</font>
         <font class="popUpText"><bean:write name="loginForm" property="crmUserPojo.passwordExpiry" /></font>
       </p>
        <p class="popUpRow<%=++i%2%>">
         <font class="popUpHead">Created By:</font>
         <font class="popUpText"><bean:write name="loginForm" property="crmUserPojo.createdBy" /></font>
       </p>
        <p class="popUpRow<%=++i%2%>">
         <font class="popUpHead">Created Date &amp; Time:</font>
         <font class="popUpText">
         <bean:write name="crmRoles" property="xmlDate(${loginForm.crmUserPojo.createdTime})" scope="session"/>
         </font>
       </p>
       <p class="popUpRow<%=++i%2%>">
         <font class="popUpHead">Last Modified By:</font>
         <font class="popUpText"><logic:notEmpty name="loginForm" property="crmUserPojo.lastModifiedBy">
		<bean:write name="loginForm" property="crmUserPojo.lastModifiedBy" /></logic:notEmpty> 
		<logic:empty name="loginForm" property="crmUserPojo.lastModifiedBy" >-</logic:empty></font>
       </p>
       <p class="popUpRow<%=++i%2%>">
         <font class="popUpHead">Last Modified Date &amp; Time:</font>
         <font class="popUpText">
         <logic:notEmpty name="loginForm"property="crmUserPojo.createdTime">
         	<bean:write name="crmRoles" property="xmlDate(${loginForm.crmUserPojo.lastModifiedTime})" scope="session"/>
		</logic:notEmpty> 
		<logic:empty name="loginForm" property="crmUserPojo.lastModifiedTime" >-</logic:empty></font>
       </p>
      <p class="popUpRow<%=++i%2%>">
         <font class="popUpHead">Current User Status:</font>
         <font class="popUpText">
				<logic:notEmpty name="loginForm" property="crmUserPojo.status">
					<bean:write name="crmRoles" property="displayEnum(UserStatus,${loginForm.crmUserPojo.status})" scope="session" />
				</logic:notEmpty>     
    	</font>
    </p>
    <p class="popUpRow<%=++i%2%>">
         <font class="popUpHead">Lock Time:</font>
         <font class="popUpText"><logic:notEmpty name="loginForm" property="crmUserPojo.lockingTime">
         	<bean:write name="crmRoles" property="xmlDate(${loginForm.crmUserPojo.lockingTime})" scope="session"/>
		</logic:notEmpty> 
		<logic:empty name="loginForm" property="crmUserPojo.lockingTime" >-</logic:empty></font>
       </p>
       
      </div>
      
      <!-- table -->
      <logic:notEmpty name="loginForm"property="userRolesPojos">
        <div class="roleDisplayTable widthAuto" >
       <p class="headerDisplayTable"><span>Role Name</span> <span>Status</span></p>
       <div class="contentDisplayTable">
        <% int roleCount=0; %>
       <logic:iterate id="userRolesPojos" name="loginForm" property="userRolesPojos" indexId="ctr">
       <logic:notEmpty name="userRolesPojos" property="crmRoles">
        <%roleCount++; %>
       <% if(roleCount%2 == 0){ %>							
      			<p class="rowFirst">
      			<% } else { %>
       				<p class="rowSecond">

       			<% } %>
       
      <span><bean:write name="userRolesPojos" property="crmRoles.roleDescription" /></span>
        
        
        <span  class="roleCheck"> 
				<logic:notEmpty name="userRolesPojos" property="status">
					<bean:write name="crmRoles" property="displayEnum(AciveInactiveStatus,${userRolesPojos.status})" scope="session" />
				</logic:notEmpty>   
        </span>     
           <span  class="roleCheck"><html:checkbox name="userRolesPojos" property="readAccess" value="true"		indexed="true" styleClass="marginright9" disabled="true" >View</html:checkbox></span>
           <span  class="roleCheck"><html:checkbox name="userRolesPojos" property="createAccess" value="true"	indexed="true" styleClass="marginright9" disabled="true">Create</html:checkbox></span>
           <span  class="roleCheck"><html:checkbox name="userRolesPojos" property="updateAccess" value="true"	indexed="true" styleClass="marginright9" disabled="true">Update</html:checkbox></span>
           <span  class="roleCheck"><html:checkbox name="userRolesPojos" property="deleteAccess" value="true"	indexed="true" styleClass="marginright9" disabled="true">Delete</html:checkbox></span>
           <span  class="roleCheck"><html:checkbox name="userRolesPojos" property="recoverAccess" value="true" indexed="true" styleClass="marginright9" disabled="true">Recover</html:checkbox></span>
 			   
         
         </p>
         </logic:notEmpty>
         </logic:iterate>
         <%
         if(roleCount==0){
         %>
         <p class="rowFirst">
         	 <b>No Role assigned.</b>
         </p>
         <%
         }
         %>  
        </div>
      </div>
      <!--Parameter Name -->
      
      
      <div class="roleDisplayTable widthAuto" >
       <p class="headerDisplayTable"><span>Parameter Name</span> <span>Parameter Value</span>  <span>Status</span></p>
       <div class="contentDisplayTable">
       <%int parameterCount=0; %>
       <logic:iterate id="userRolesPojos" name="loginForm" property="parameterList" indexId="ctr">
		<logic:notEmpty name="userRolesPojos" property="crmParameter">
		<% parameterCount++;%>
        <% if(parameterCount%2 == 0){ %>								
	      <p class="rowFirst">
	      <% } else { %>
	      <p class="rowSecond">
	       	<% } %>         
         <span><bean:write name="userRolesPojos" property="crmParameter.parameterName" /></span>
         <span><bean:write name="userRolesPojos" property="parameterValue" /></span>
         <span>
         
         <logic:notEmpty name="userRolesPojos" property="status">
					<bean:write name="crmRoles" property="displayEnum(AciveInactiveStatus,${userRolesPojos.status})" scope="session" />
		</logic:notEmpty>   
         
        
		</span>
       </p>
         </logic:notEmpty>
        
         </logic:iterate>
         <%
         if(parameterCount==0){
         %>
         <p class="rowFirst">
         	<b>No parameter assigned.</b>
         </p>
         <%
         }
         %>  
        </div>
      </div>
	  
	     <div class="roleDisplayTable widthAuto" >
       <p class="headerDisplayTable"><span>State Name</span> <span>City Name</span>  
	   <span style="width:220px;">Area Name</span> 
	   <span>Status</span></p>
       <div class="contentDisplayTable">
       <%int areaCount=0; %>
	   <logic:notEmpty name="loginForm" property="areaMappingPojos">
       <logic:iterate id="areaMappingPojos" name="loginForm" property="areaMappingPojos" indexId="ctr">
		
		<% areaCount++;%>
        <% if(areaCount%2 == 0){ %>								
	      <p class="rowFirst">
	      <% } else { %>
	      <p class="rowSecond">
	       	<% } %>         
         <span>
		  <bean:write name="areaMappingPojos" property="stateName" />
		 </span>
         <span>   <bean:write name="areaMappingPojos" property="cityName" /></span>
         
		 <span style="width:220px;">   <bean:write name="areaMappingPojos" property="areaName" /></span>
         <span>
         <logic:notEmpty name="areaMappingPojos" property="status">
				 <bean:write name="crmRoles" property="displayEnum(AciveInactiveStatus,${areaMappingPojos.status})" scope="session" />
		</logic:notEmpty>   
   		</span>
       </p>
        
        
         </logic:iterate>
		  </logic:notEmpty>
         <%
         if(areaCount==0){
         %>
         <p class="rowFirst">
         	<b>No Area assigned.</b>
         </p>
         <%
         }
         %> 
        </div>
      </div>
	  
      <!-- Role Group -->
       <div class="roleDisplayTable widthAuto">
       <p class="headerDisplayTable"><span>Group Name</span> <span>Group Type</span><span>Status</span></p>
       <div class="contentDisplayTable">
     <% int groupCount=0; %>
	<logic:iterate id="userRolesPojos" name="loginForm" property="userRolesPojos" indexId="ctr">
	<logic:notEmpty name="userRolesPojos" property="groups">
	<%groupCount++; %>
	<% if(groupCount%2 == 0){ %>							
      <p class="popUpRow1">
      <% } else { %>
       <p class="popUpRow2">
       	<% } %>
       	 <span ><bean:write name="userRolesPojos" property="groups.groupName" /></span>
		<logic:equal name="userRolesPojos" property="groups.groupType" value="P"><span>Parameter Group </span></logic:equal>
		<logic:equal name="userRolesPojos" property="groups.groupType" value="R"><span>Role Group</span></logic:equal>
		<span> 
		
			 <logic:notEmpty name="userRolesPojos" property="status">
					<bean:write name="crmRoles" property="displayEnum(AciveInactiveStatus,${userRolesPojos.status})" scope="session" />
			</logic:notEmpty>  
	       
	    </span>
	    </p>
     </logic:notEmpty>
         
  </logic:iterate>
  	   <%
         if(groupCount==0){
       %>
         <p class="rowFirst">
         	 <b> No group assigned</b>
         </p>
         <%
             }
         %>  
        </div>
      </div>
      
</logic:notEmpty>
<logic:empty name="loginForm"property="userRolesPojos">
<div class="roleDisplayTable widthAuto">
 	<div class="contentDisplayTable">
  	 	<b>No access assigned.</b>
 	</div>
</div>
</logic:empty>
     <!-- table -->
</div>

         <div class="LmsTable">
				<h4>Activity Log Details <span class="lmsMinusImage floatr"></span></h4>
			  <div class="viewLmsTable margintop10">
			 <iframe src="jsp/content/masterdata/displayAuditLog.jsp" frameborder="0" scrolling="yes"
                  style="border: none; overflow: hidden; width: 100%; height:250px;" allowTransparency="true" onload="iframeLoaded(this)"></iframe>
			 </div>
   	 </div>

</div>
</body>
</html>