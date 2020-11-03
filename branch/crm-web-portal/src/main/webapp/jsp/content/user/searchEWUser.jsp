<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="/WEB-INF/tlds/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/tlds/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/tlds/displaytag.tld" prefix="display"%>
<%@taglib uri="/WEB-INF/tlds/struts-html.tld" prefix="html"%>
<html>
<body>
<div id="section" >
<html:form action="/searchEWUser" styleId="idSearchEWUser" method="post">
<html:hidden name="eWUserMappingForm" property="hiddenUserId" styleId="userID_searchUser" />
<html:hidden name="eWUserMappingForm" property="mappingType" styleId="mappingType_ID" />	
 <div class="section">
   <h2>Search User </h2>
   
   <input type="text" style="display: none" />
   
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
      			<html:text  styleClass="textbox" name="eWUserMappingForm" property="userId"></html:text>
				
      		</p>
       		
			<b class="space">&nbsp;</b>
			<p style="height: 20px;"></p>
    	</div>
       <logic:equal name="crmRoles" property="available(view_esc_wflow)" value="true" scope="session">
	   		<div class="floatr inner_right">
	     		<html:link href="#" styleId="submit_searchTemplate" styleClass="main_button"><span>Search</span></html:link>
	   		</div>
   		</logic:equal>
    		<br class="clear"/>
	</div>
     <div class="margintop20">
		<logic:notEmpty name="eWUserMappingForm" property="crmUserPojos" >
			<display:table export="false" id="data"
				name="sessionScope.eWUserMappingForm.crmUserPojos" requestURI="" class="dataTable"
				style="width:100%" pagesize="15">
					<display:setProperty name="paging.banner.placement" value="bottom" />
					<display:column  title="Sr.No.">
						<bean:write name="data_rowNum" />
					</display:column>
					<display:column property="userId" title="User ID" sortable="false" />
					<display:column property="firstName" title="First Name"	sortable="false" />
					<display:column property="lastName" title="Last Name" sortable="false" />
					<display:column property="mobileNo" title="Mobile Number" sortable="false" />
					<display:column  title="Functional Bins" style="width:20%" >
					<bean:write name="crmRoles" property="displayEnum(functionalBins,${data.functionalBin})" scope="session" />					
					</display:column>
					<display:column property="emailId" title="Email ID" />
					 <logic:equal name="crmRoles" property="available(view_esc_wflow)" value="true" scope="session">
							<display:column title="Manage WorkFlow" media="html">
								<logic:equal name="data" property="status" value="D">Already Deleted</logic:equal>
								<logic:notEqual name="data" property="status" value="D">
									<logic:notEqual name="data" property="userId" value="${sessionScope.userPojo.userId}" >
										<a href="javascript:userMapping('<bean:write name="data" property="userId" />', 'W')">Work Flow</a>
									</logic:notEqual>
									<logic:equal name="data" property="userId" value="${sessionScope.userPojo.userId}" >
										-
									</logic:equal>
								</logic:notEqual>
							</display:column>
							<display:column title="Manage Escalation" media="html">
								<logic:equal name="data" property="status" value="D">Already Deleted</logic:equal>
								<logic:notEqual name="data" property="status" value="D">
									<logic:notEqual name="data" property="userId" value="${sessionScope.userPojo.userId}" >
										<a href="javascript:userMapping('<bean:write name="data" property="userId" />', 'E')">Escalation</a>
									</logic:notEqual>
									<logic:equal name="data" property="userId" value="${sessionScope.userPojo.userId}" >
										-
									</logic:equal>
								</logic:notEqual>
							</display:column>
						</logic:equal>
			</display:table>
			</logic:notEmpty>
				<logic:empty name="eWUserMappingForm" property="crmUserPojos">
					<b> <html:errors  property="noRecordFound"/> </b>
				</logic:empty>
     </div>
   
 </div>
</html:form>
</div>
</body>
</html>
