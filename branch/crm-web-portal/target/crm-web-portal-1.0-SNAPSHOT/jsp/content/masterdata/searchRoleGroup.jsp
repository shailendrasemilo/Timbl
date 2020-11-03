<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<%@taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<%@taglib uri="http://struts.apache.org/tags-html" prefix="html"%>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link href="css/displaytag.css" rel="stylesheet" type="text/css" />
</head>

<body>
<div class="loadingPopup hidden"></div>
<div id="section" >
<html:form action="/roleGroup" styleId="searchRoleGroupForm" method="post">
<input type="text" style="display: none" />
 <div class="section">
   <h2>Search Role Group</h2>
      <span class="error_message"><html:errors property="appError" /></span>
   	<div class="inner_section">
	<span class="success_message">
		<logic:messagesPresent message="true">
			<html:messages id="message" message="true">
				<bean:write name="message" /><br />
			</html:messages>
		</logic:messagesPresent>
	</span>
	<logic:equal name="crmRoles" property="available(create_role_group)" value="true" scope="session">
   		<a href="roleGroup.do?method=createRoleGroupPage" class="link_button">Add Role Group</a>
   	</logic:equal>
   		
   		<div class="inner_left_lead floatl marginleft10 createUserLeft">
   		<p class="floatl clear"><strong>Role Group</strong> <html:text property="roleGroupName" styleId="roleGroupName" styleClass="textbox" /></p>
   		<p class="floatl marginleft30"><strong>	Status</strong>
  		
  		<span class="dropdownWithoutJs"> 
  			<html:select name="roleGroupForm"  property="searchStatus">
  			<logic:notEmpty name="roleGroupForm" property="statusList">
				<html:optionsCollection name="roleGroupForm" property="statusList" label="contentName" value="contentValue"/>			
			</logic:notEmpty>
			</html:select>
		</span>
  		</p>
		<b class="space clear">&nbsp;</b> 
		<b class="space clear">&nbsp;</b> 
  		</div>
  		
  		<div class="floatr inner_right">
     		<html:link href="#" styleId="IDsearchRoleGroup" styleClass="main_button"><span>Search</span></html:link>
    	</div>

	
    	<p class="clear"></p>
   
     </div>
       	<logic:notEmpty name="roleGroupForm" property="groupsPojos">
			<display:table id="data" name="sessionScope.roleGroupForm.groupsPojos" requestURI="" class="dataTable"
							style="width:100%" pagesize="15" >

			<display:setProperty name="paging.banner.placement" value="bottom" />
			<display:setProperty name="export.excel.filename" value="Rolegroup-Details.xls" />
			<display:setProperty name="export.csv.filename" value="Rolegroup-Details.csv" />
			<display:column title="Sr.No"><bean:write name="data_rowNum" /></display:column>
				<display:column property="groupName" title="Role Group Name"/>
				<display:column property="groupDescription" title="Role Group Description" style="word-wrap:break-word;"/>
				<display:column title="Status">
					<logic:notEmpty name="data" property="status">
						<bean:write name="crmRoles" property="displayEnum(PartialStatus,${data.status})" scope="session" />
					</logic:notEmpty>					
				</display:column>			
			<logic:equal name="crmRoles" property="available(update_role_group)" value="true" scope="session">
				<display:column title="Edit" media="html">
				  <logic:notEqual name="data" property="status" value="D"  >
					<a href="javascript:modify_RoleGroupPage('<bean:write name="data" property="groupId" />')">Edit</a>
				 </logic:notEqual>
				 <logic:equal name="data" property="status" value="D"  >
						-
				 </logic:equal>
			   </display:column>
			</logic:equal>   
		<logic:equal name="crmRoles" property="available(update_role_group,delete_role_group,recover_role_group)" value="true" scope="session">
			<display:column title="Change Status"  media="html">
			<logic:equal name="data" property="status" value="A" >
			<logic:equal name="crmRoles" property="available(update_role_group)" value="true" scope="session">
				<a href="javascript:change_RoleGroup('<bean:write name="data" property="groupId" />','I')">Deactivate</a>&nbsp;|&nbsp;
			</logic:equal>
			<logic:equal name="crmRoles" property="available(delete_role_group)" value="true" scope="session">	
				<a href="javascript:change_RoleGroup('<bean:write name="data" property="groupId" />','D')">Delete</a>
			</logic:equal>
			</logic:equal>
			<logic:equal name="data" property="status" value="I">
			<logic:equal name="crmRoles" property="available(update_role_group)" value="true" scope="session">
				<a href="javascript:change_RoleGroup('<bean:write name="data" property="groupId" />','A')">Activate</a>&nbsp;|&nbsp;
			</logic:equal>
			<logic:equal name="crmRoles" property="available(delete_role_group)" value="true" scope="session">
				<a href="javascript:change_RoleGroup('<bean:write name="data" property="groupId" />','D')">Delete</a>
			</logic:equal>
			</logic:equal>
			<logic:equal name="data" property="status" value="D">
			   <logic:equal name="crmRoles" property="available(recover_role_group)" value="true" scope="session">
					Deleted&nbsp;(<a href="javascript:change_RoleGroup('<bean:write name="data" property="groupId" />','A')">Recover</a>)
			   </logic:equal>
			</logic:equal>
			</display:column>
		</logic:equal>
			 <logic:equal name="crmRoles" property="available(view_role_group)" value="true" scope="session">
			<display:column title="View"  media="html">	
				<a href="javascript:viewRole_RoleGroup('<bean:write name="data" property="groupId" />')">View</a>
			</display:column>
			</logic:equal>
			</display:table>
		</logic:notEmpty>
		<logic:empty name="roleGroupForm" property="groupsPojos">
			<b> <html:errors  property="noRecordFound"/> </b>
		</logic:empty>
   
</div>
	<html:hidden name="roleGroupForm" property="roleGroupId" styleId="inRoleGroupId_searchUser" />
	<html:hidden name="roleGroupForm" property="status" styleId="newStatus" />
 </html:form>
</div>
</body>

