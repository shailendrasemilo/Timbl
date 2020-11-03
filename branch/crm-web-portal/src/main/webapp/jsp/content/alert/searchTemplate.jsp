<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="/WEB-INF/tlds/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/tlds/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/tlds/displaytag.tld" prefix="display"%>
<%@taglib uri="/WEB-INF/tlds/struts-html.tld" prefix="html"%>

<html>
	<head>
	<link href="css/displaytag.css" rel="stylesheet" type="text/css" />
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	
</head>

<body>
<div class="loadingPopup hidden"></div>
<div id="section" >
<html:form action="/smsAlert" styleId="search_template"  method="post">
<html:hidden name="alertForm" property="templateId" styleId="idTemplateId"/>
<html:hidden name="alertForm" property="status" styleId="idStatus"/>
 <div class="section">
   <h2>Search Template</h2>
   <div style="color:green;font-weight:bold;">
					<logic:messagesPresent message="true">
   					<html:messages id="message" message="true">
					<bean:write name="message" />
					<br />
					</html:messages>
					</logic:messagesPresent>
	</div>
   <div class="inner_section">
   <div class="inner_left_lead marginleft10 floatl">

     <p class="floatl "><strong>Project Type</strong> 

     <LABEL class="label_radio labelRadiominWidth" for="internal">
     	<html:radio styleId="internal" property="projectType" value="I" onclick="populateList(this.value)"> Internal</html:radio>
     </LABEL> 
     <LABEL class="label_radio labelRadiominWidth" for="external">
     	<html:radio styleId="external" property="projectType" value="E" onclick="populateList(this.value)"> External</html:radio><br/>
     </LABEL>
     </p>

  <logic:empty name="alertForm" property="projectType">
     <p id="pName" class="floatl marginleft30"  style="display: none;">
	 <strong>Project Name</strong> 
					 <span class="dropdownWithoutJs">      
					  	<html:select styleId="sel" property="projectId"></html:select>
					 </span>
	 </p>
	 </logic:empty>
   	<logic:notEmpty name="alertForm" property="projectType">
   		<p id="pName" class="floatl marginleft30"   style="display: block; margin-top:14px;">
		<strong>Project Name</strong> 
					 <span class="dropdownWithoutJs">      
					  	<html:select styleId="sel" property="projectId">
					  	<logic:notEmpty  name="alertForm" property="projects">
					  		<html:optionsCollection name="alertForm" property="projects" label="projectName" value="projectId"/>
					  	</logic:notEmpty>
					  	</html:select>
					 </span>
	 </p>
   	</logic:notEmpty>


 <p class="floatl marginleft30" ><strong>Template Name</strong>
 		<html:text styleId="template_name" property="templateName" styleClass="textbox" ></html:text>
   </p>  
   
   <p class="floatl clear" ><logic:equal name="crmRoles" property="available(view_sms_template,create_sms_template,update_sms_template,view_email_template,create_email_template,update_email_template)" value="true" scope="session">
   <strong>Template Type</strong> 
     
  </logic:equal>
     <logic:equal name="crmRoles" property="available(view_sms_template,create_sms_template,update_sms_template,delete_sms_template,recover_sms_template)" value="true" scope="session">
     <LABEL class="label_radio labelRadiominWidth" for="sms"><html:radio  property="templateType" styleId="sms" value="sms">SMS</html:radio></LABEL> 
     </logic:equal>
     <logic:equal name="crmRoles" property="available(view_email_template,create_email_template,update_email_template,delete_email_template,recover_email_template)" value="true" scope="session">
     <LABEL class="label_radio labelRadiominWidth" for="email"><html:radio  property="templateType" styleId="email" value="email">Email</html:radio></LABEL>
     </logic:equal>
   </p>
  
   
   <logic:empty name="alertForm" property="templateType">
	 
	   <p class="floatl marginleft30 hide" id="display_sms"><strong>SMS Type</strong> 
	   
	     <LABEL class="label_radio labelRadiominWidth" for="promotional">
	     	<html:radio  property="smsType" styleId="promotional" value="P">Promotional</html:radio>
	     </LABEL> 
	     <LABEL class="label_radio labelRadiominWidth" for="transactional">
	    	 <html:radio  property="smsType" styleId="transactional" value="T">Transactional</html:radio>
	     </LABEL>
	   </p>
	   
   </logic:empty>
   <logic:equal name="alertForm" property="templateType" value="sms">
   	
	   <p class="floatl marginleft30" id="display_sms" ><strong>SMS type</strong> 
	     
	     <LABEL class="label_radio labelRadiominWidth" for="promotional">
	     	<html:radio  property="smsType" styleId="promotional" value="P">Promotional</html:radio>
	     </LABEL> 
	     <LABEL class="label_radio labelRadiominWidth" for="transactional">
	    	 <html:radio  property="smsType" styleId="transactional" value="T">Transactional</html:radio>
	     </LABEL>
	   </p>
	 
   </logic:equal>
   <logic:equal name="alertForm" property="templateType" value="email">
   	
	   <p class="floatl marginleft30 hide" id="display_sms"><strong>SMS type</strong> 
	    
	     <LABEL class="label_radio labelRadiominWidth" for="promotional">
	     	<html:radio  property="smsType" styleId="promotional" value="P">Promotional</html:radio>
	     </LABEL> 
	     <LABEL class="label_radio labelRadiominWidth" for="transactional">
	    	 <html:radio  property="smsType" styleId="transactional" value="T">Transactional</html:radio>
	     </LABEL>
	   </p>

   </logic:equal>
  
   <p class="floatl marginleft30" >
   	<strong>Status</strong>
   		<span class="dropdownWithoutJs"> 
 		<html:select property="statusFor">
 		<logic:notEmpty  name="alertForm" property="statusList">
 			<html:optionsCollection name="alertForm" property="statusList" label="contentName" value="contentValue"/>
 		</logic:notEmpty>
 		</html:select>
 	</span>
   </p>  
   </div>
   <div class="floatr inner_right">
     <html:link href="#" onclick="javascript:searchTemplate();" styleId="searchTemplate" styleClass="main_button"><span>Search</span></html:link>
    </div>
    <p class="clear"></p>    
   </div>   
     				<logic:equal name="alertForm" property="templateType" value="email">
					<logic:notEmpty name="alertForm" property="emailTemplateList">
						<display:table id="tableList" name="${alertForm.emailTemplateList}" class="dataTable" style="width:100%" pagesize="15"
														requestURI="">
						<display:setProperty name="paging.banner.placement"	value="bottom" />
						<display:column title="Sr.No"><bean:write name="tableList_rowNum" /></display:column>
						<display:column title="Template Name" property="emailTemplateName"></display:column>
						<display:column title="Created By">
								<bean:write name="tableList" property="createdBy" />
						</display:column>
						<display:column title="Created Date">
							<bean:write name="tableList" property="displayCreatedTime" />
						</display:column>
							<bean:define id="statusOf" name="tableList" property="status"></bean:define>
							<bean:define id="templateId" name="tableList" property="emailTemplateId"></bean:define>
							<display:column title="Status">
								<logic:notEmpty name="tableList" property="status">
								<bean:write name="crmRoles" property="displayEnum(AciveInactiveStatus,${tableList.status})" scope="session" />
							</logic:notEmpty> 			
							</display:column>
						    <logic:equal name="crmRoles" property="available(update_email_template)" value="true" scope="session">
							<display:column title="Update Status">
								<html:link href="javascript:changeStatus('${tableList.emailTemplateId}','E', 
									'activeAndDeactiveEmailTemplate','${tableList.status}')" style="cursor: pointer" title="update status">
									<logic:equal name="statusOf" value="I">Activate</logic:equal>
									<logic:equal name="statusOf" value="A">Deactivate</logic:equal>


								</html:link>
							</display:column>
							</logic:equal>	
							<logic:equal name="crmRoles" property="available(update_email_template)" value="true" scope="session">						
							<display:column title="Update Template">
								<logic:equal name="tableList" value="A" property="status">
									<html:link href="javascript:templateOperation('${tableList.emailTemplateId}','E','viewEmailTemplate')" 
														styleId="editEmailTemplate" style="cursor: pointer" title="update template">Edit</html:link>
								</logic:equal>
								<logic:notEqual name="tableList" value="A" property="status">
								     -
								</logic:notEqual>
							</display:column>
							</logic:equal>
							<logic:equal name="crmRoles" property="available(view_email_template)" value="true" scope="session">
							<display:column title="Export Template">
							<html:link href="javascript:templateOperation('${tableList.emailTemplateId}','E','exportEmailTemplate')" 
										styleId="exportEmailTemplate" style="cursor: pointer" title="export template">Export</html:link>
							</display:column>
							</logic:equal>
							<logic:equal name="crmRoles" property="available(view_email_template)" value="true" scope="session">
							<display:column title="View Template">
								<a href="#" style="cursor: pointer" title="view Template"  class="link"
									  onclick="viewTemplate( '${tableList.emailTemplateId}','${alertForm.templateType}')"> View</a>
							</display:column>
							</logic:equal>
						</display:table>
						</logic:notEmpty>
						<logic:empty name="alertForm" property="emailTemplateList">
							<b>No record found</b>
						</logic:empty>
					</logic:equal>
					<logic:equal name="alertForm" property="templateType" value="sms">
					<logic:notEmpty name="alertForm" property="smsTemplateList">
						<span class="floatl marginleft20 "> </span>
					<display:table id="tableList" name="${alertForm.smsTemplateList}" class="dataTable" style="width:100%" pagesize="15" requestURI="">
							<display:setProperty name="paging.banner.placement"	value="bottom" />
							<display:column title="Sr.No"><bean:write name="tableList_rowNum" /></display:column>
							<display:column title="Template Name" property="smsTemplateName"></display:column>
							<bean:define id="smsTypes" name="tableList" property="smsType"></bean:define>
							<display:column title="SMS Type">
								<logic:equal name="smsTypes" value="P">Promotional</logic:equal>
								<logic:equal name="smsTypes" value="T">Transactional</logic:equal>
							</display:column>
							<display:column title="Created By">
								<bean:write name="tableList" property="createdBy" />
							</display:column>
							<display:column title="Created Date">
								<bean:write name="tableList" property="displayCreatedTime"	/>
							</display:column>
							<bean:define id="statusOf" name="tableList" property="status"></bean:define>
							<bean:define id="templateId" name="tableList" property="smsTemplateId"></bean:define>
							<display:column title="Status">
							<logic:notEmpty name="tableList" property="status">
								<bean:write name="crmRoles" property="displayEnum(AciveInactiveStatus,${tableList.status})" scope="session" />
							</logic:notEmpty> 	
							</display:column>
							<logic:equal name="crmRoles" property="available(update_sms_template)" value="true" scope="session">
							<display:column title="Update Status">
								<html:link href="javascript:changeStatus('${tableList.smsTemplateId}',
																		'S', 'activeAndDeactiveSMSTemplate','${tableList.status}')"									
																			style="cursor: pointer" title="update status">
									<logic:equal name="statusOf" value="I">Activate</logic:equal>
									<logic:equal name="statusOf" value="A">Deactivate</logic:equal>
								</html:link>
							</display:column>
							</logic:equal>
							<logic:equal name="crmRoles" property="available(update_sms_template)" value="true" scope="session">				
							<display:column title="Update Template">
								<logic:equal name="tableList" value="A" property="status">
								<html:link href="javascript:templateOperation('${tableList.smsTemplateId}','S','editSMSTemplate')" 
										style="cursor: pointer" title="update template" styleId="updateSmsTemplate">Edit</html:link>
								</logic:equal>
								<logic:notEqual name="tableList" value="A" property="status">
								     -
								</logic:notEqual>
							</display:column>
							</logic:equal>
							<logic:equal name="crmRoles" property="available(view_sms_template)" value="true" scope="session">
							<display:column title="Export Template">
							<html:link href="javascript:templateOperation('${tableList.smsTemplateId}','S','exportSMSTemplate')"
									styleId="exportSMSTemplate" style="cursor: pointer" title="export template">Export</html:link>
							</display:column>
							</logic:equal>
							<logic:equal name="crmRoles" property="available(view_sms_template)" value="true" scope="session">
							<display:column title="View Template ">
								<a href="#" style="cursor: pointer" title="View Template"  class="link"  
											onclick="viewTemplate( '${tableList.smsTemplateId}','${alertForm.templateType}')"> View</a>
							</display:column>
							</logic:equal>
						</display:table>
					</logic:notEmpty>
					<logic:empty name="alertForm" property="smsTemplateList">
						<b>No record found</b>
					</logic:empty>
					</logic:equal>
 </div>
 </html:form>
</div>
<div id="lightbox-panel">
      			 <div id="pop_up" class="popUp"> 
     			<div class="closePopup"><a id="close-panel" href="#"></a></div>
      						<div id="rowDisplayData" class="popUpContainer innerPopupContainer"></div>
      			</div>			
   			</div>
	<div id="lightbox"> </div>	
</body>
</html>
