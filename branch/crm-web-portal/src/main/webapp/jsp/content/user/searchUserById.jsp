<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@taglib uri="http://displaytag.sf.net" prefix="display"%>
<%@taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<%@taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<body>
<div class="loadingPopup hidden"></div>
<div id="section" >
<html:form styleId="search_by_user" action="/userManagement" method="post">
<input type="text" style="display: none" />
<html:hidden name="loginForm" property="recordID" styleId="recordID_searchUser" />			
	<div class="section">
   	<h2>Search User</h2>
   	<span class="error_message"><html:errors property="appError" /></span>
		<span class="success_message">
		<logic:messagesPresent message="true" property="appMessage">
					<html:messages id="message" message="true" property="appMessage">
					<bean:write name="message" />
					</html:messages>
		</logic:messagesPresent>
		</span>
					 
	<div class="inner_section">	
   		<div class="inner_left floatl createUserLeft">
      		<p>	
      			<strong>User ID</strong>
      			<html:text property="userId" styleClass="textbox" value="${loginForm.userId}"  />
      		</p>
      		<b class="space">&nbsp;</b>
      		<p style="height: 20px;"></p>
    	</div>
   		<div class="floatr inner_right">
     		<html:link href="#"  styleClass="main_button" styleId="searchUserByIdMainButton">Search</html:link>
   		</div>
    		<br class="clear"/>
	</div>
    	<logic:notEmpty name="loginForm" property="searchUserList">
			<display:table id="data"
				name="loginForm.searchUserList" requestURI="" class="dataTable"
				style="width:100%" pagesize="15">
					<display:setProperty name="paging.banner.placement" value="bottom" />
					<display:setProperty name="export.excel.filename" value="Users-Detail.xls" />
					<display:setProperty name="export.csv.filename" value="Users-Detail.csv" />
					<display:column  title="Sr.No.">
						<bean:write name="data_rowNum" />
					</display:column>					
					<display:column property="userId" title="User ID"/>
					<display:column property="firstName" title="First Name"/>
					<display:column property="lastName" title="Last Name"/>
					<display:column property="mobileNo" title="Mobile Number"/>
					<%-- <display:column  title="Functional Bins" style="width:20%" >
						<bean:write name="crmRoles" property="displayEnum(functionalBins,${data.functionalBin})" scope="session" />	
					</display:column>--%>
					<display:column property="emailId" title="Email ID" />
					<logic:equal name="crmRoles" property="available(create_assign_roles,update_assign_roles,delete_assign_roles,recover_assign_roles)" value="true" scope="session">
					<display:column title="Assign Roles" media="html">
						<logic:equal name="data" property="status" value="D">Deleted</logic:equal>
						<logic:notEqual name="data" property="status" value="D">
							<logic:notEqual name="data" property="userId" value="${sessionScope.userPojo.userId}" >
								<logic:lessEqual name="data" property="hierarchy" value="${sessionScope.userPojo.hierarchy}">		
									<a href="javascript:assignRoles_searchUser('<bean:write name="data" property="recordId" />')">Assign Roles</a>
								</logic:lessEqual>
								<logic:greaterThan name="data" property="hierarchy" value="${sessionScope.userPojo.hierarchy}">
				 				-
				 				</logic:greaterThan>
							</logic:notEqual>
							<logic:equal name="data" property="userId" value="${sessionScope.userPojo.userId}" >
							-
							</logic:equal>
						</logic:notEqual>
					</display:column>
					</logic:equal>
					<logic:equal name="crmRoles" property="available(create_assign_params,update_assign_params,delete_assign_params,recover_assign_params)" value="true" scope="session">
					<display:column title="Assign Parameter" media="html">
						<logic:equal name="data" property="status" value="D">Deleted</logic:equal>
						<logic:notEqual name="data" property="status" value="D">
							<logic:notEqual name="data" property="userId" value="${sessionScope.userPojo.userId}" >
								<logic:lessEqual name="data" property="hierarchy" value="${sessionScope.userPojo.hierarchy}">
									<a href="javascript:assignParameter_searchUser('<bean:write name="data" property="recordId" />')">Assign Parameter</a>
								</logic:lessEqual>
								<logic:greaterThan name="data" property="hierarchy" value="${sessionScope.userPojo.hierarchy}">
				 				-
				 				</logic:greaterThan>
							</logic:notEqual>
							<logic:equal name="data" property="userId" value="${sessionScope.userPojo.userId}" >
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
		<logic:empty name="loginForm" property="searchUserList">
			<b> <html:errors  property="noRecordFound"/> </b>
		</logic:empty>
 </div>
 </html:form>
 </div>
 </body>