<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="/WEB-INF/tlds/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/tlds/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/tlds/struts-html.tld" prefix="html"%>
<%@ taglib uri="http://ckeditor.com" prefix="ckeditor"%>
<%@page import="java.util.Map"%>
<%@page import="com.ckeditor.*"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<link type="text/css" rel="stylesheet" href="ckeditor/_samples/sample.css" />
<script type="text/javascript" src="ckeditor/ckeditor.js"></script>
</head>
<body>
<div id="section" >
	<html:form action="emailAlert" styleId="createEmail" method="post" enctype="multipart/form-data">
 	<div class="section">
 				<logic:empty property="emailTemplateList" name="alertForm">
					<h2>Create Email Template</h2>
				</logic:empty>
				<logic:notEmpty property="emailTemplateList" name="alertForm">
					<h2>Update Email Template</h2>
				</logic:notEmpty>
				<span class="error_message"><html:errors property="emailTemplate" /></span>
				<span class="success_message"><html:messages property="appMessage" message="true" id="message"/></span>
   			<div class="inner_section">
  				 <div class="inner_left_lead marginleft10 floatl relative">
  				 
  				
   				 <p class="floatl"><strong>Project Type</strong> 
   				<logic:empty name="alertForm" property="emailTemplatePojo">  
    				<LABEL class="label_radio" for="internal">
    					<html:radio	styleId="internal" name="alertForm" property="projectType" value="I"	onclick="populateList(this.value)"> Internal</html:radio>
    				</LABEL> 
    				<LABEL class="label_radio" for="external">
    					<html:radio	styleId="external" name="alertForm" property="projectType" value="E"	onclick="populateList(this.value)"> External</html:radio>
    				</LABEL>
    			</logic:empty>
    			<logic:notEmpty name="alertForm" property="emailTemplatePojo">
    					<html:hidden name="alertForm" property="projectType" value="${alertForm.projectType}"/>
						<span class="showTextbox">
						<logic:equal name="alertForm" property="projectType" value="I">Internal</logic:equal>
						<logic:equal name="alertForm" property="projectType" value="E">External</logic:equal> 
						</span>
				</logic:notEmpty>
    				
    			</p>

				<p class="floatl marginleft30"><strong>Template Name</strong>
     		<logic:empty name="alertForm" property="emailTemplatePojo">     			
			 	<html:text	styleId="emailTemplateName" property="templateName" styleClass="textbox"></html:text>
			</logic:empty>
			<logic:notEmpty name="alertForm" property="emailTemplatePojo">				
					<html:text	styleId="emailTemplateName" name="alertForm" property="templateName" styleClass="textbox" value="${alertForm.templateName}" readonly="true"></html:text>
			</logic:notEmpty>
    	 	</p>

			 <p class="floatl marginleft30"><strong>Email Server</strong>
				 	 <span class="dropdownWithoutJs">
				 	 	<html:select styleId="email_Server" name="alertForm" property="emailServer" value='${alertForm.emailServer}'>
						<html:option value="0">Please Select</html:option>
								<logic:notEmpty name="alertForm" property="emailServers">
								<html:optionsCollection name="alertForm" property="emailServers" label="subCategory" value="alias"></html:optionsCollection>
								</logic:notEmpty>
						</html:select>
						</span>
		 	 </p>

    				<logic:empty name="alertForm" property="emailTemplatePojo">
    				<logic:empty name="alertForm" property="projectType">  
					
    				 <p id="pName" style="display: none;"  class="floatl clear">
					 <strong>Project Name</strong>
				 	 	<span class="dropdownWithoutJs">
				 	 		<html:select styleId="sel" property="projectId" onchange="getParameter(this.value)"></html:select>
				 	 	</span>
				 	 </p>
				 	 </logic:empty>
				 	 <logic:notEmpty name="alertForm" property="projectType">
				 	 	<logic:notEmpty name="alertForm" property="projects">
				 	 	 <p id="pName" style="display: block;"  class="floatl clear">
						 <strong>Project Name</strong>
				 	 	<span class="dropdownWithoutJs">
				 	 		<html:select styleId="sel" property="projectId" onchange="getParameter(this.value)">
				 	 			<html:optionsCollection name="alertForm" property="projects" label="projectName" value="projectId"/>
				 	 		</html:select>
				 	 	</span>
				 	 	</p>
				 	 	</logic:notEmpty>
				 	 </logic:notEmpty>
				 	 </logic:empty>
				 	 <logic:notEmpty name="alertForm" property="emailTemplatePojo">
				 	 	<html:hidden name="alertForm" property="projectId" value="${alertForm.projectId}"/>
				 	 	<html:hidden name="alertForm" property="projectName" value="${alertForm.projectName}"/>
	 						<p  class="floatl clear"><strong>Project Name</strong>
									<span class="showTextbox">
									<bean:write name="alertForm" property="projectName"/>
									</span>
	 						</p>
					 </logic:notEmpty>
   				
     		 
    	 	
              
         <p class="clear"></p> 

		  <!-- email template-->
<div class="relative paddingtop10 clear">
     <div class="emailTemplate ">
     
     <p class="cc_bcc">
      	 <a href="javascript:void();" id="add_cc">Cc</a>
 		<a href="javascript:void();" class="marginleft13" id="add_bcc">Bcc</a>
 	</p>
      <p><strong>From:</strong>  
     	<html:text styleId="emailFrom" styleClass="emailTextbox" property="emailFrom" />
      </p>
      <logic:empty name="alertForm" property="emailCc">
      <p id="cc"><strong>Cc:</strong>
      	 <html:text styleClass="emailTextbox" name="alertForm" property="emailCc"></html:text>
	 </p>
	 </logic:empty>
	 <logic:notEmpty name="alertForm" property="emailCc">
	 	<p id="display_cc"><strong>Cc:</strong>
      	 <html:text styleClass="emailTextbox" name="alertForm" property="emailCc" value="${alertForm.emailCc}"></html:text>
	 </p>
	 </logic:notEmpty>
	 <logic:empty name="alertForm" property="emailBcc">
      <p id="bcc"><strong>Bcc:</strong>
      	<html:text styleClass="emailTextbox" name="alertForm" property="emailBcc"></html:text>
      </p>
      </logic:empty>
      <logic:notEmpty name="alertForm" property="emailBcc">
      	<p id="display_bcc"><strong>Bcc:</strong>
      	<html:text styleClass="emailTextbox" name="alertForm" property="emailBcc" value="${alertForm.emailBcc}"></html:text>
      </p>
      </logic:notEmpty>
      <p class="noborder"><strong>Subject:</strong>  
      	<html:text styleClass="emailTextbox" styleId="emailSubject" name="alertForm" property="emailSubject"  />
      <p class=" ckeditor">
       <span>Insert text below:</span><br/>
       <html:textarea styleId="emailTemplateBody"	styleClass="template_body" name="alertForm" property="emailTemplateBody"></html:textarea>
      </p>
     </div> 
    
    <div class="inner_right_top">
    
   
       <span class="right_click ">
       <a href="javascript:void(0)" id="idSubjectParam" onclick="additem(this.id)"><img src="images/bg-${initParam.client }/right-arrow.png" title="Subject Parameter" /></a>
       <br/>
       <a href="javascript:void(0)" id="idEmailParam"  onclick="additem(this.id)"><img src="images/bg-${initParam.client }/right-arrow.png" title="Email Body Parameter" class="margintop20" /></a>
     </span>	
     	<div class="listbox "><font class="bold">Select Parameter</font>
     	   <span class="multiple_dropdown">
      		<html:select styleId="emailParameter" property="parameter" size="5">
      			<logic:notEmpty name="alertForm" property="parameterList">
    				<html:optionsCollection name="alertForm" property="parameterList" label="parameterName" value="parameterName"/>
    			</logic:notEmpty>
      		</html:select>
      		</span>		
        </div>
        <span class="import_template">
	     <input type="file" id="idTemplateFile" name="idTemplateFile" onchange="javascript:importTemplate(event,'emailTemplateBody','E','idTemplateFile')"/> 
         <a href="#" id="import_createEmail" class="grey_big_button marginleft6 import_createEmail"><span>Import</span></a>
	
	    </span>
	<p class="clear"></p>
	
</div>
</div>
 <!-- email template-->
    </div>
    	<div class="floatr inner_right">
    	<logic:empty property="emailTemplateList" name="alertForm">
				<html:link href="#" styleId="createEmailTemplate" styleClass="main_button_multiple click">Create Template</html:link>
			</logic:empty>
			<logic:notEmpty property="emailTemplateList" name="alertForm">
				<html:link href="#" styleId="updateEmailTemplate" styleClass="main_button_multiple click">Update Template</html:link>
			</logic:notEmpty>
    	</div>
    <p class="clear"></p>
     </div>
   <div>
   </div>
 </div>
 
 </html:form>
</div>
<%
	    CKEditorConfig settings = new CKEditorConfig();
	    settings.addConfigValue( "width", "570");	    
	    //settings.addConfigValue("toolbar","[{name: 'document', items: ['Source','-','Cut','Copy','Paste','-','Undo','Redo','RemoveFormat','-','Link','Unlink','Anchor','-','Image','Table','HorizontalRule','SpecialChar']},'/', {name: 'styles', items: ['Format','Templates','Bold','Italic','Underline','-','Superscript','-',['JustifyLeft','JustifyCenter','JustifyRight','JustifyBlock'],'-','NumberedList','BulletedList','-','Outdent','Indent']]} ]");
	    //settings.removeConfigValue("[{name: 'document', items: ['Source', '-', 'NewPage']},'/', {name: 'styles', items: ['Styles','Format']} ]");
	    //EventHandler eventHandler = new EventHandler();
	    //eventHandler.addEventHandler("instanceReady","function (ev) { alert(\"Loaded: \" + ev.editor.name); }");
	    //GlobalEventHandler globalEventHandler = new GlobalEventHandler();
	    //globalEventHandler.addEventHandler("dialogDefinition","function (ev) { alert(\"Loading dialog window: \" + ev.data.name); }");
	    //CKEDITOR.config.toolbar_MA=[['Source','-','Cut','Copy','Paste','-','Undo','Redo','RemoveFormat','-','Link','Unlink','Anchor','-','Image','Table','HorizontalRule','SpecialChar'],'/', ['Format','Templates','Bold','Italic','Underline','-','Superscript','-',['JustifyLeft','JustifyCenter','JustifyRight','JustifyBlock'],'-','NumberedList','BulletedList','-','Outdent','Indent']]];
	%>
	<ckeditor:replace replace="emailTemplateBody" basePath="ckeditor/" config="<%=settings%>"/>
</body>

</html>
