<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@page import="com.np.tele.crm.utils.Captcha"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<%@taglib uri="http://struts.apache.org/tags-html" prefix="html"%>


<head>
<link href="css/displaytag.css" rel="stylesheet" type="text/css" />
</head>

<body>
<div class="loadingPopup hidden"></div>
<div id="section" >
<html:form action="/parameterGroup" styleId="searchRoleGroupForm">
<input type="text" style="display: none" />
 <div class="section">
   <h2>Search Parameter Group</h2>
   <span class="error_message"><html:errors property="appError" /></span>
   <div class="inner_section">
   <span class="success_message">
 	<logic:messagesPresent message="true" >
		<html:messages id="message" message="true">
			<bean:write name="message" /><br />
		</html:messages>
	</logic:messagesPresent>
</span>
  <logic:equal name="crmRoles" property="available(create_parameter_group)" value="true" scope="session">
      <a href="parameterGroup.do?method=createParameterGroupPage" class="link_button">Add Parameter Group</a>
  </logic:equal>
   <div class="inner_left_lead floatl marginleft10 createUserLeft">
   <p class="floatl clear"><strong>Parameter Group</strong> 		<html:text property="parameterGroupName" styleId="parameterGroupName" styleClass="textbox" /></p>
    <p class="floatl marginleft30"><strong>Status</strong>
			
			<span class="dropdownWithoutJs"> 
			<html:select property="searchStatus">
			<logic:notEmpty name="parameterGroupForm" property="statusList">
				<html:optionsCollection name="parameterGroupForm" property="statusList" label="contentName" value="contentValue"/>
			</logic:notEmpty>		
			</html:select>  
			</span>
			</p>
			<b class="space clear">&nbsp;</b> 
   </div>
  
   <div class="floatr inner_right">
     <html:link href="#" styleId="IDsearchParameterGroup" styleClass="main_button"><span>Search</span></html:link>
    </div>
  
    <p class="clear"></p>
     </div>
		<logic:notEmpty name="parameterGroupForm" property="groupsPojos">
			<display:table id="data"	name="sessionScope.parameterGroupForm.groupsPojos" requestURI="" 
			class="dataTable" style="width:100%" pagesize="15">
				
				<display:setProperty name="paging.banner.placement" value="bottom" />
				<display:setProperty name="export.excel.filename" value="Parametergroup-Details.xls" />
				<display:setProperty name="export.csv.filename" value="Parametergroup-Details.csv" />
				<display:column title="Sr.No"><bean:write name="data_rowNum" /></display:column>
				<display:column property="groupName" title="Parameter Group Name"/>
				<display:column property="groupDescription" title="Group Description" style="word-wrap:break-word;"/>
				<display:column title="Status">
					<logic:notEmpty name="data" property="status">
							<bean:write name="crmRoles" property="displayEnum(PartialStatus,${data.status})" scope="session" />
					</logic:notEmpty>
				</display:column>
				<logic:equal name="crmRoles" property="available(update_parameter_group)" value="true" scope="session">		
				<display:column title="Edit"  media="html">
				<logic:notEqual name="data" property="status" value="D">
						<a	href="javascript:modify_ParameterGroupPage('<bean:write name="data" property="groupId" />')">Edit</a>
				 </logic:notEqual>
				 <logic:equal name="data" property="status" value="D">
						-
				 </logic:equal>
				</display:column>
				</logic:equal>
			<logic:equal name="crmRoles" property="available(update_parameter_group,update_parameter_group,recover_parameter_group)" value="true" scope="session">
				<display:column title="Change Status"  media="html">
					<logic:equal name="data" property="status" value="A">
					<logic:equal name="crmRoles" property="available(update_parameter_group)" value="true" scope="session">	
						<a href="javascript:change_ParameterGroup('<bean:write name="data" property="groupId" />','I')">Deactivate</a>&nbsp;|&nbsp;
					</logic:equal>
					<logic:equal name="crmRoles" property="available(delete_parameter_group)" value="true" scope="session">	
						<a href="javascript:change_ParameterGroup('<bean:write name="data" property="groupId" />','D')">Delete</a>
					</logic:equal>
					</logic:equal>
					<logic:equal name="data" property="status" value="I" >
					<logic:equal name="crmRoles" property="available(update_parameter_group)" value="true" scope="session">
						<a href="javascript:change_ParameterGroup('<bean:write name="data" property="groupId" />','A')">Activate</a>&nbsp;|&nbsp;
					</logic:equal>
					<logic:equal name="crmRoles" property="available(delete_parameter_group)" value="true" scope="session">	
						<a href="javascript:change_ParameterGroup('<bean:write name="data" property="groupId" />','D')">Delete</a>
					</logic:equal>
					</logic:equal>
					<logic:equal name="data" property="status" value="D">
					<logic:equal name="crmRoles" property="available(recover_parameter_group)" value="true" scope="session">	
						Deleted&nbsp;(<a href="javascript:change_ParameterGroup('<bean:write name="data" property="groupId" />','A')">Recover</a>)
					</logic:equal>
					</logic:equal>
				</display:column>
			</logic:equal>
			 <logic:equal name="crmRoles" property="available(view_parameter_group)" value="true" scope="session">
				<display:column title="View"  media="html">
					<a href="javascript:viewParameter_ParameterGroup('<bean:write name="data" property="groupId" />')">View</a>
				</display:column>
			</logic:equal>
			</display:table>
		</logic:notEmpty>
		<logic:empty name="parameterGroupForm" property="groupsPojos">
		<b> <html:errors  property="noRecordFound"/> </b>
		</logic:empty>

   
 </div>
 	<logic:messagesPresent message="true">
		<html:messages id="message" message="true">
			<bean:write name="message" /><br />
		</html:messages>
	</logic:messagesPresent>
		<html:hidden name="parameterGroupForm" property="parameterGroupId" styleId="inParameterGroupId_searchParameter" />
		<html:hidden name="parameterGroupForm" property="status" styleId="status" />
 
 </html:form>
</div>

</body>

