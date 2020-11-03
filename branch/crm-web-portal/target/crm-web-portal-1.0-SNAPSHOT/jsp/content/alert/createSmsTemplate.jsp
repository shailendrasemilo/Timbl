<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="/WEB-INF/tlds/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/tlds/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/tlds/struts-html.tld" prefix="html"%>


<body>
<div id="section" >
<html:form action="/smsAlert" styleId="sms"  method="post" enctype="multipart/form-data">
 <div class="section">
 	<logic:empty property="smsTemplateList" name="alertForm">
				<h2>Create SMS Template</h2>
			</logic:empty>
			<logic:notEmpty property="smsTemplateList" name="alertForm">
				<h2>Update SMS Template</h2>
			</logic:notEmpty>
			<span class="error_message"><html:errors property="appError" /></span>
   <div class="inner_section">
   <div class="inner_left_lead marginleft10 floatl relative">
  
    <p class="floatl"><strong>SMS Type</strong> 
   
     		<logic:empty name="alertForm" property="smsType">
				<LABEL class="label_radio" for="promotional"><html:radio styleId="promotional" property="smsType" value="P">Promotional</html:radio></LABEL>
				<LABEL class="label_radio" for="transactional"><html:radio styleId="transactional" property="smsType" value="T">Transactional</html:radio></LABEL><br/> 
				<html:errors property="smsType" /> 
			</logic:empty>
			<logic:notEmpty name="alertForm" property="smsType">
			<span class="showTextbox">
				<logic:equal name="alertForm" property="smsType" value="P">
				
				Promotional</logic:equal>
				<logic:equal name="alertForm" property="smsType" value="T">Transactional</logic:equal>
				</span>
			</logic:notEmpty>
     
   </p>
   
    <p class="floatl marginleft30">	<strong>Project Type</strong> 
     		
     <logic:empty name="alertForm" property="projectType">
				<LABEL class="label_radio" for="internal"><html:radio styleId="internal" property="projectType" value="I"	onclick="populateList(this.value)"> Internal </html:radio></LABEL>
				<LABEL class="label_radio" for="external"><html:radio styleId="external" property="projectType" value="E" onclick="populateList(this.value)"> External </html:radio></LABEL> 
				<html:errors property="projectType" /> 
		</logic:empty>
			<logic:notEmpty name="alertForm" property="projectType">
			<span class="showTextbox">
				<logic:equal name="alertForm" property="projectType" value="I">Internal</logic:equal>
				<logic:equal name="alertForm" property="projectType" value="E">External</logic:equal> 
				</span>
			</logic:notEmpty>
     </p>
     <logic:empty name="alertForm" property="projectName">
	  <p class="floatl marginleft30" id="pName" style="display: none;">
     <strong>Project Name</strong>
					 <span class="dropdownWithoutJs">      
					  	<html:select styleId="sel" property="projectId"  onchange="getParameter(this.value)"></html:select>
					 </span>
	 </p>
	 </logic:empty>
	 <logic:notEmpty name="alertForm" property="projectName">
	  <p class="floatl marginleft30">	
	 	<strong>Project Name</strong>
		<span class="showTextbox">
      <bean:write name="alertForm" property="projectName"/>
	 	</span>
	</p>
	 </logic:notEmpty>
	 
     <p class="floatl clear"><strong>Template Name</strong>
     			<logic:empty name="alertForm" property="templateName">
					<html:text styleId="template_name" name="alertForm" property="templateName" styleClass="textbox"/>
			 		<html:errors property="templateName" /> 
				</logic:empty>
			 <logic:notEmpty name="alertForm" property="templateName">
				 	<html:text styleId="template_name" name="alertForm" property="templateName" value="${alertForm.templateName}" readonly="true" styleClass="textbox"/>
			 </logic:notEmpty>
     </p>
   
    
     <p class="floatl marginleft30"><strong>SMS Gateway</strong>
      <span class="dropdownWithoutJs">      
      		<html:select name="alertForm" property="smsGateway"  value='${alertForm.smsGateway}'>
				<html:option value="0">Please Select</html:option>
				<logic:notEmpty name="alertForm" property="smsGateways">
				<html:optionsCollection name="alertForm" property="smsGateways" label="subCategory" value="alias"></html:optionsCollection>
				</logic:notEmpty>
			</html:select>
       </span>
     </p>
   
   <p class="clear"></p>
    <!-- sms template-->
	 <div class="smsTemplate relative">
       	<span class="insert_text ">Insert text below here:</span>
       	<p class="smsTemplate_container">
        <span class="word_count">
        	<input type="text" value="0" id="counter" readonly="readonly" class="charactercount"  />
        	<input type="text"  value="0" id="messageCounter" readonly="readonly" class="messagecount" />
        </span>
  			 <html:textarea styleId="message" styleClass="sms_template_body" property="smsTemplateBody" onkeyup="textCounter(this,'counter','messageCounter', 160);" onblur="textCounter(this,'counter','messageCounter', 160);" onfocus="textCounter(this,'counter','messageCounter', 160);"></html:textarea><br />
				<html:errors property="smsTemplateBody" />
       </p>
       <div class="sms_inner_right_top">
     	<span class="right_click "><a href="javascript:void(0)" id="idSmsParam" onclick="additem(this.id)"><img src="images/bg-${initParam.client }/right-arrow.png" class="marginTop20"/></a></span>
     <div id="textarea" class="listbox " ><font class="bold ">Select Parameter</font>
    	<span class="multiple_dropdown">
    		<html:select styleId="smsparameter" property="parameter"  size="5" >
    			<logic:notEmpty name="alertForm" property="parameterList">
    				<html:optionsCollection name="alertForm" property="parameterList" label="parameterName" value="parameterName"/>
    			</logic:notEmpty>
    		</html:select>
    	</span>
	</div>
	<span class="import_template ">
	      <input type="file" id="idTemplateFile" name="idTemplateFile" onchange="javascript:importTemplate(event,'message','S','idTemplateFile')"/>
          <a href="#" id="import_createEmail" class="grey_big_button marginleft6 import_createEmail"><span>Import</span></a>
	    </span>
	 <p class="clear"></p>
</div>
     </div> 

	  <!-- sms template-->
    </div>
    <div class="floatr inner_right">
    	<logic:empty property="smsTemplateList" name="alertForm">
				<html:link href="#" styleId="createSMSTemplate" styleClass="main_button_multiple click">Create Template</html:link>
		</logic:empty>
		<logic:notEmpty property="smsTemplateList" name="alertForm">
				<html:link href="#" styleId="updateTemplate" styleClass="main_button_multiple click" >Update Template</html:link>
		</logic:notEmpty>
    </div>
    <p class="clear"></p>
     </div>
   <div>
  
   </div>
   
   
  
 </div>
 
 </html:form>
</div>
</body>
