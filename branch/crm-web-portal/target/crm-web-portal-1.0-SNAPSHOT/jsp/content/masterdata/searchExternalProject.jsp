<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="/WEB-INF/tlds/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/tlds/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/tlds/displaytag.tld" prefix="display"%>
<%@taglib uri="/WEB-INF/tlds/struts-html.tld" prefix="html"%>

<html>
	<head>
		<link href="css/displaytag.css" rel="stylesheet" type="text/css" />
	</head>
<body>
<div class="loadingPopup hidden"></div>
<div id="section" >
	<html:form action="/crmProject" styleId="search_ext_project" method="post">
	<input type="text" style="display: none" />
 		<div class="section">
   			<h2>Search Project</h2>
   			    <span class="error_message"><html:errors property="appError" /></span>
   			     <div class="inner_section">	
   			    <span class="success_message">
					<logic:messagesPresent message="true">
						<html:messages id="message" message="true">
							<bean:write name="message" /><br />
						</html:messages>
					</logic:messagesPresent>
				</span>   
   			 <logic:equal name="crmRoles" property="available(create_external_projects)" value="true" scope="session"> 							
   				<a href="crmProject.do?method=rgstrExtrnlPrjctPg" class="link_button">Register External Project</a>
   			 </logic:equal>
   				
   				<html:hidden name="crmProjectForm" property="projectId" styleId="idProject"/>
   				<html:hidden name="crmProjectForm" property="status" styleId="idStatus"/>
   			<div class="inner_section createUserLeft">
   			 
   				<div class="inner_left_lead floatl marginleft10 createUserLeft">
   				
   					<p class="floatl clear"><strong>Project Name</strong> 
   						<html:text styleId="template_name" property="extProjectName" styleClass="textbox"></html:text>
   					</p>
   				<p class="floatl marginleft30"><strong>Status</strong>
					<span class="dropdownWithoutJs"> 
						<html:select property="searchStatus">
							<logic:notEmpty name="crmProjectForm" property="statusList">
							<html:optionsCollection name="crmProjectForm" property="statusList" label="contentName" value="contentValue"/>		
							</logic:notEmpty>
						</html:select>  
					</span>
				</p>
				<b class="space clear">&nbsp;</b> 
   				</div>
   				
   				<div class="floatr inner_right">
   					<html:link href="#" styleId="searchProject" styleClass="main_button">Search</html:link>
    			</div>
				 
    			<p class="clear"></p>
    			</div>
    			</div>
				<logic:notEmpty name="crmProjectForm" property="projectList">
					<display:table id="tableList" name="${crmProjectForm.projectList}" class="dataTable" style="width:100%" pagesize="15" requestURI="">
							<display:setProperty name="paging.banner.placement"	value="bottom" />
							<display:setProperty name="paging.banner.placement"	value="bottom" />
							<display:setProperty name="export.excel.filename" value="ExternalProject-Report.xls" />
							<display:setProperty name="export.csv.filename" value="ExternalProject-Report.csv" />
							<display:column title="Sr.No" style="width:10%;">
								<bean:write name="tableList_rowNum" />
							</display:column>
							<display:column title="Project Name" property="projectName" style="width:15%;"></display:column>
							<display:column title="Project Description"	property="projectDescription" style="width:15%;"></display:column>
							<display:column title="Status">
								<logic:notEmpty name="tableList" property="status">
									<bean:write name="crmRoles" property="displayEnum(PartialStatus,${tableList.status})" scope="session" />
								</logic:notEmpty> 
							</display:column>
							<logic:equal name="crmRoles" property="available(update_external_projects)" value="true" scope="session"> 
							<display:column title="Edit Project" media="html">
							 <logic:notEqual name="tableList" property="status" value="D">
								<html:link href="javascript:extrProjectOperation('${tableList.projectId}','editExtrnalProject')" style="cursor: pointer" title="update Project" styleId="updateExtrProject">Edit</html:link>
							 </logic:notEqual>
							 <logic:equal name="tableList" property="status" value="D">
								-
							 </logic:equal>
							</display:column>
							</logic:equal>
							<logic:equal name="crmRoles" property="available(update_external_projects,delete_external_projects,recover_external_projects)" value="true" scope="session">
								<display:column title="Update Status" media="html">
								<logic:equal name="tableList" property="status" value="A">
								<logic:equal name="crmRoles" property="available(update_external_projects)" value="true" scope="session">
									<a href="javascript:changeExtrProjectStatus('<bean:write name="tableList" property="projectId" />','I')">Deactivate</a>&nbsp;|&nbsp;
								</logic:equal>
								<logic:equal name="crmRoles" property="available(delete_external_projects)" value="true" scope="session">
									<a href="javascript:changeExtrProjectStatus('<bean:write name="tableList" property="projectId" />','D')">Delete</a>
								</logic:equal>
								</logic:equal>
								<logic:equal name="tableList" property="status" value="I">
								<logic:equal name="crmRoles" property="available(update_external_projects)" value="true" scope="session">
									<a href="javascript:changeExtrProjectStatus('<bean:write name="tableList" property="projectId" />','A')">Activate</a>&nbsp;|&nbsp;
								</logic:equal>
								<logic:equal name="crmRoles" property="available(delete_external_projects)" value="true" scope="session">
									<a href="javascript:changeExtrProjectStatus('<bean:write name="tableList" property="projectId" />','D')">Delete</a>
								</logic:equal>
								</logic:equal>
								<logic:equal name="tableList" property="status" value="D">
								<logic:equal name="crmRoles" property="available(recover_external_projects)" value="true" scope="session">
									Deleted&nbsp;(<a href="javascript:changeExtrProjectStatus('<bean:write name="tableList" property="projectId" />','A')">Recover</a>)
								</logic:equal>
								</logic:equal>
								</display:column>
							</logic:equal>
							<logic:equal name="crmRoles" property="available(view_external_projects)" value="true" scope="session">
							<display:column title="View Project" media="html">
								<bean:define id="prameterList" name="tableList" property="crmParameterPojosSet"></bean:define>
								<a href="#" style="cursor: pointer" title="view Project"  class="link" id='<logic:iterate id="paramterPojo" name="prameterList" >
								<bean:write name="paramterPojo" property="parameterName" />,
								<bean:write name="paramterPojo" property="parameterType" />,
								<bean:write name="paramterPojo" property="parameterLength" />,
								<bean:write name="paramterPojo" property="status" />;
								</logic:iterate>' onclick='viewExtrProject( this.id , "${tableList.projectName}" , "${tableList.projectDescription}" ,"${tableList.status}")'> View</a>
							</display:column>
							</logic:equal>
				</display:table>
			</logic:notEmpty>
			<logic:empty name="crmProjectForm" property="projectList">
			   <b> <html:errors property="noRecordFound"/> </b>
		   </logic:empty>
					
					
 		</div>
 	</html:form>
 	<p class="clear"></p>
</div>
			<div id="lightbox-panel">
      			 <div id="pop_up" class="popUp"> 
     			<div class="closePopup"><a id="close-panel" href="#"></a></div>
      						<div id="rowDisplayData" class="popUpContainer">
      						
      						</div>
      			</div>			
   			</div>
	<div id="lightbox"> </div>	
</body>
</html>
