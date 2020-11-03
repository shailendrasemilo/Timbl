<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" %>
<%@taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<%@taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<%@taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<head>
<title>Search User</title>
</head>
<body>
<div class="loadingPopup hidden"></div>
<div id="section" >
<html:form styleId="search_by_user" action="/userManagement" method="post">
 <div class="section">
   <h2>Search User</h2>
    <span class="error_message"><html:errors property="appError" /></span>
   <span class="success_message">
					<logic:messagesPresent message="true"  property="appmessage">
								<html:messages id="message" message="true"  property="appmessage">
									<bean:write name="message" />
								</html:messages>
					</logic:messagesPresent>
					</span>
   	<span class="error_Criteria hide1" id="unfill_searchUser">Please provide at least one criteria
   	</span>
   	<p class="error_message"><html:errors  property="errorMessage"/>
   <html:errors  property="message"/>
   	</p>
   	
   <div class="inner_section">
					
   		<div class="inner_left floatl createUserLeft">
		    <p class="floatl clear "><b>User ID</b> <html:text property="userId" styleId="userId" styleClass="textbox" /></p>
  			<p class="floatl marginleft60"><b>Functional Bin</b> 
								<span class="dropdownWithoutJs"> 
									<logic:notEmpty name="loginForm" property="leadStagesList">
										<html:select property="functionalBin" styleId="functionalBinID">
											<html:option value="">All</html:option>
											<html:optionsCollection name="loginForm" property="leadStagesList" label="categoryValue" value="categoryId"/>
										</html:select>
									</logic:notEmpty>
								</span>
								<html:errors property="funtionalBin_createUser" />
    		</p>
   			<p class="floatl clear"><b>First Name</b> <html:text property="firstName" styleId="firstName" styleClass="textbox" maxlength="30"/> </p>
  			<p class="floatl marginleft60"><b>Last Name</b> <html:text property="lastName" styleId="lastName" styleClass="textbox" maxlength="30"/></p>
   			<p class="floatl clear "><b>Email ID</b> 
   			<html:text property="emailId" styleId="emailId_searchUser" styleClass="textbox" /></p>
			<p class="floatl marginleft60"><b>Mobile Number</b> 
			<span class="mobilePrefix">[+91]</span>
  			<logic:equal name="loginForm" property="mobileNo" value="0">
   			<html:text property="mobileNo" styleId="mobileNo_searchUser" styleClass="textbox" value="" maxlength="10" onkeyup="javascript:ValidatenReplaceAlphanumeric(this);" />
   			</logic:equal>
   			<logic:notEqual name="loginForm" property="mobileNo" value="0">
   			<html:text property="mobileNo" styleId="mobileNo_searchUser" styleClass="textbox"  maxlength="10" onkeyup="javascript:ValidatenReplaceAlphanumeric(this);" />
   			</logic:notEqual>
   			<span class="Lms_Reference"></span>
   			</p>
			
   			<%-- <p class="floatl clear"><b>Employee Type</b> 
   			<span class="dropdownWithoutJs">
        		<html:select styleId="empType_searchUser" property="empType">
           					<html:option value="">Select</html:option>
							<html:option value="N">Nextra</html:option>
							<html:option value="P">Partner</html:option>
         		</html:select>
       		</span>
    		</p> --%>
			<p  class="floatl clear"><b>Status</b> 
   			<span class="dropdownWithoutJs">
        		<html:select  name="loginForm" property="searchStatus" styleId="idsearchUserStatus">
        		<logic:notEmpty name="loginForm" property="statusList">
           					<html:optionsCollection name="loginForm" property="statusList" label="contentName" value="contentValue"/>
           		</logic:notEmpty>
           					
         		</html:select>
       		</span>
    		</p>
			<%-- <p id="ecode_searchUser" class="floatl clear"><b>Employee Code</b>
    		<html:text property="empCode" styleId="empCode" maxlength="10" styleClass="textbox " />
    		<p  id="pcode_searchUser" class="floatl clear"><b>Partner Name</b> 
   			<span class="dropdownWithoutJs">
   				<html:select property="partnerName"	styleId="partnerName_createUser" title="">
   				</html:select>							
			</span>
    		</p> --%>
   		</div>
   		<div class="floatr inner_right">
     		<html:link href="#" styleId="search_userSearch" styleClass="main_button">Search</html:link>
     	</div>
    	<p class="clear"></p>
     </div>
     		<b>
     		 <html:errors  property="noRecordFound"/>
     		</b>				     
    <div id="idDisplayTable">
    	<logic:notEmpty name="loginForm" property="searchUserList">
			<display:table id="data"
				name="loginForm.searchUserList" requestURI="userManagement.do?method=searchUser" class="dataTable"
				style="width:100%" pagesize="15">
					<display:setProperty name="paging.banner.placement" value="bottom" />
					<display:setProperty name="mymedia" value="true"></display:setProperty>
				<display:setProperty name="export.excel.filename" value="Users-Detail.xls" />
				<display:setProperty name="export.csv.filename" value="Users-Detail.csv" />
					<display:column  title="Sr.No.">
						<bean:write name="data_rowNum" />
					</display:column>
					<display:column property="userId" title="User ID" sortable="false" />
					<display:column property="firstName" title="First Name"	sortable="false" />
					<display:column property="lastName" title="Last Name" sortable="false" />
					<display:column property="mobileNo" title="Mobile Number" sortable="false" />
					<%--<display:column  title="Functional Bins" style="width:20%" >
					<bean:write name="crmRoles" property="displayEnum(functionalBins,${data.functionalBin})" scope="session" />					
					</display:column>--%>
					<display:column property="emailId" title="Email ID" />
					<logic:equal name="crmRoles" property="available(update_user_profile,delete_user_profile,recover_user_profile)" value="true" scope="session">
					<display:column  title="Edit" media="html">
						<logic:equal name="data" property="status" value="D"> Deleted</logic:equal>
						<logic:notEqual name="data" property="status" value="D">
						<logic:notEqual name="data" property="userId" value="${sessionScope.userPojo.userId}" >
							<logic:lessEqual name="data" property="hierarchy" value="${sessionScope.userPojo.hierarchy}">				
			 					<a href="javascript:editUser_searchUser('<bean:write name="data" property="recordId" />')">Edit</a>
			 				</logic:lessEqual>
			 				<logic:greaterThan name="data" property="hierarchy" value="${sessionScope.userPojo.hierarchy}">
			 				-
			 				</logic:greaterThan>
						</logic:notEqual>
						<logic:equal  name="data" property="userId" value="${sessionScope.userPojo.userId}" >
								-
						</logic:equal>
						</logic:notEqual>
					</display:column>
					</logic:equal>	
					<logic:equal name="crmRoles" property="available(create_assign_roles,update_assign_roles,delete_assign_roles,recover_assign_roles)" value="true" scope="session">			
					<display:column title="Assign Roles" media="html">
					<logic:equal name="data" property="status" value="D"> Deleted</logic:equal>
					<logic:notEqual name="data" property="status" value="D">
						<logic:notEqual name="data" property="userId" value="${sessionScope.userPojo.userId}" >	
							<logic:lessEqual name="data" property="hierarchy" value="${sessionScope.userPojo.hierarchy}">					
								<a href="javascript:assignRoles_searchUser('<bean:write name="data" property="recordId" />')">Assign Roles</a>
							</logic:lessEqual>
							<logic:greaterThan name="data" property="hierarchy" value="${sessionScope.userPojo.hierarchy}">
			 				-
			 				</logic:greaterThan>
						</logic:notEqual>
						<logic:equal  name="data" property="userId" value="${sessionScope.userPojo.userId}" >
							-
						</logic:equal>
					</logic:notEqual>
					</display:column>
					</logic:equal>		
					<logic:equal name="crmRoles" property="available(create_assign_params,update_assign_params,delete_assign_params,recover_assign_params)" value="true" scope="session">			
					<display:column title="Assign Parameter" media="html">
						<logic:equal name="data" property="status" value="D"> Deleted</logic:equal>
						<logic:notEqual name="data" property="status" value="D">
						<logic:notEqual name="data" property="userId" value="${sessionScope.userPojo.userId}" >	
								<logic:lessEqual name="data" property="hierarchy" value="${sessionScope.userPojo.hierarchy}">			
									<a href="javascript:assignParameter_searchUser('<bean:write name="data" property="recordId" />')">Assign Parameter</a>
								</logic:lessEqual>	
								<logic:greaterThan name="data" property="hierarchy" value="${sessionScope.userPojo.hierarchy}">
				 				-
				 				</logic:greaterThan>					
						</logic:notEqual>
						<logic:equal name="data" property="userId" value="${sessionScope.userPojo.userId}">
							-
						</logic:equal>
						</logic:notEqual>
					</display:column>
					
					<display:column title="Assign Area" media="html">
						<logic:equal name="data" property="status" value="D"> Deleted</logic:equal>
						<logic:notEqual name="data" property="status" value="D">
						<logic:notEqual name="data" property="userId" value="${sessionScope.userPojo.userId}" >	
								<logic:lessEqual name="data" property="hierarchy" value="${sessionScope.userPojo.hierarchy}">			
									<a href="javascript:assignArea_searchUser('<bean:write name="data" property="recordId" />')" >Assign Area
						</a>
								</logic:lessEqual>	
								<logic:greaterThan name="data" property="hierarchy" value="${sessionScope.userPojo.hierarchy}">
				 				-
				 				</logic:greaterThan>					
						</logic:notEqual>
						<logic:equal name="data" property="userId" value="${sessionScope.userPojo.userId}">
							-
						</logic:equal>
						</logic:notEqual>
					</display:column>
					
					</logic:equal>
					<display:column title="View" media="html">
						<a href="javascript:viewUser_searchUser('<bean:write name="data" property="recordId" />')" >View</a>
					</display:column>
					
					
			</display:table>
			</logic:notEmpty>
			</div>			
		 </div>
			<html:hidden name="loginForm" property="recordID" styleId="recordID_searchUser" />
 </html:form>
 </div>
</body>