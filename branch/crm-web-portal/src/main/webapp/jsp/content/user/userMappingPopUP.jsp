<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="/WEB-INF/tlds/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/tlds/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/tlds/displaytag.tld" prefix="display"%>
<%@taglib uri="/WEB-INF/tlds/struts-html.tld" prefix="html"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link href="css/common.css" rel="stylesheet" media="screen" />
<link href="css/color-${initParam.client }.css" rel="stylesheet" media="screen" />
<link href="css/userManagement.css" rel="stylesheet" media="screen" />
<link href="css/masterdata.css" rel="stylesheet" media="screen" />
<link href="css/lms.css" rel="stylesheet" media="screen" />
<link href="css/displaytag.css" rel="stylesheet" type="text/css" />
<script src="javascript/jquery.min.js" type="text/javascript"></script>
<script src="javascript/jquery.validate.js" type="text/javascript"></script>
<script src="javascript/userManagement.js" type="text/javascript"></script>
<script src="javascript/common.js" type="text/javascript"></script>
</head>
<body>
<div id="popupSection" >
<html:form action="/searchEWUser" styleId=""  method="post">
 <div class="popupSection">
   <h2>Select User </h2>
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
      <p  class="floatl ">
      	<strong>User ID</strong>
       	<html:text  styleClass="Lmstextbox" name="eWUserMappingForm" property="userId"/>
      </p>
      <p class="floatl clear">
      	<strong>Functional Bin</strong>
       		<span class="LmsdropdownWithoutJs">
            	<html:select property="functionalBin" styleId="functionalBinID">
					<html:option value="">All</html:option>
					<logic:notEmpty name="eWUserMappingForm" property="leadStagesList">
					<html:optionsCollection name="eWUserMappingForm" property="leadStagesList" label="contentName" value="contentValue"/>
					</logic:notEmpty>
					
				</html:select>
        	</span> 
      </p>
     <%--  <p  class="floatl clear">
      	<strong>Employee Type</strong> 
      	<span class="LmsdropdownWithoutJs">
            <html:select styleId="employee_type" name="eWUserMappingForm" property="empType" >
             	<html:option value="">Select</html:option>
				<html:option value="N">Nextra</html:option>
				<html:option value="P">Partner</html:option>
            </html:select>
        </span>
       </p>--%>
      <p  class="floatl marginleft30"><strong>Status</strong>
       <span class="LmsdropdownWithoutJs">
            <html:select  name="eWUserMappingForm" property="searchStatus">
            <logic:notEmpty name="eWUserMappingForm" property="statusList">
           		<html:optionsCollection name="eWUserMappingForm" property="statusList" label="contentName" value="contentValue"/>
           	</logic:notEmpty>
         	</html:select>
        </span> 
       </p>
   </div>
   <div class="floatr inner_right">
     <html:link href="#" styleId="submit_searchUserPopUp" styleClass="main_button"><span>Search</span></html:link>
   </div>
    
<br class="clear"/>
	
</div>
        <br class="clear"/>
	<div class="clear inner_left"  >
		<logic:notEmpty name="eWUserMappingForm" property="crmUserPojos" >
			<display:table export="false" id="data"
				name="sessionScope.eWUserMappingForm.crmUserPojos" requestURI="" class="dataTable"
				style="width:100%" > 
					<display:setProperty name="paging.banner.placement" value="bottom" />
					<display:column  title="Select">
						<logic:notEqual name="data" property="userId" value="${sessionScope.userPojo.userId}" >
							<logic:notEqual name="data" property="userId" value="${eWUserMappingForm.hiddenUserId}" >
									<logic:notEqual name="data" property="status" value="D">
										<html:checkbox name="data" property="editable" value="true" styleId="edit_${data_rowNum}"></html:checkbox>
										<html:hidden name="data" property="userId" styleId="user_${data_rowNum}" value="${data.userId}"/>
								</logic:notEqual>
							</logic:notEqual>
						</logic:notEqual>
					</display:column>
					<display:column property="userId" title="User ID"/>
					<display:column property="firstName" title="First Name"/>
					<display:column property="lastName" title="Last Name"/>
					<display:column property="mobileNo" title="Mobile Number"/>
					<display:column property="emailId" title="Email ID"  />
					<display:column  title="Functional Bins" style="width:20%">
					<logic:notEmpty name="data" property="functionalBin">
					<bean:write name="crmRoles" property="displayEnum(functionalBins,${data.functionalBin})" scope="session" />
					</logic:notEmpty>
					</display:column>		
					<display:column  title="Status">
					<logic:notEmpty name="data" property="status">
						<bean:write name="crmRoles" property="displayEnum(UserStatus,${data.status})" scope="session" />
					</logic:notEmpty>
					</display:column>
			</display:table>
			<html:hidden name="eWUserMappingForm" property="userPojoSize" styleId="userPojoSize" value="${eWUserMappingForm.userPojoSize}"/> 
			<p class="clear margintop10" align="center" >
 			 <html:link href="#" styleClass="yellow_button" onclick="javascript:addSelectedUser();">Add</html:link>
  			 <html:link href="#" styleClass="yellow_button marginleft6" onclick="self.close()">Cancel</html:link>
 		</p>
			
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
