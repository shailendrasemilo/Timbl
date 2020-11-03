<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="/WEB-INF/tlds/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/tlds/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/tlds/struts-html.tld" prefix="html"%>
<div id="section" >
<html:form action="/crmProject" styleId="registerProject"  method="post">
			
 <div class="section">
  <logic:equal name="crmProjectForm" property="regValue" value="true">
   <h2>Register Project : Add Parameter</h2>
   </logic:equal>
    <logic:equal name="crmProjectForm" property="regValue" value="false">
   <h2>Update Project : Add Parameter</h2>
   </logic:equal>
   <div class="inner_section">
    <div class="error_message">
		<html:errors/>
	</div>
	<span class="addServerSide-error">
		<logic:messagesPresent message="true">
   			<html:messages id="message" message="true">
			<bean:write name="message" />
			<br />
			</html:messages>
		</logic:messagesPresent>
	</span>
   <div class="inner_left floatl createUserLeft">
   <logic:equal name="crmProjectForm" property="regValue" value="true">
   <p><b></b>
	</p>
      <p>
      	<b>Project Name</b>
      	<html:text styleId="extProjectName" property="extProjectName" styleClass="textbox"></html:text>
      </p>
    </logic:equal>
    <logic:equal name="crmProjectForm" property="regValue" value="false">
     	<p><b>Project Name</b>
     		<html:text styleId="extProjectName" property="extProjectName" styleClass="textbox"  value="${crmProjectForm.extProjectName}" readonly="true"></html:text>
     	</p>
    </logic:equal>
   <p style='margin-top: 22px !important;'>
   	<b>Project Description</b><html:textarea styleId="projectDesc" property="projectDesc" styleClass="textarea"></html:textarea>
   </p>
   <div id="mapping_view">
     <span class="addbtn_addParameter">
     	<html:link href="javascript:void(0)" styleId="addParameter"  styleClass="grey_button_add" ><span>Add Parameter</span></html:link>
     </span>
    <div class="display_mapping_email">
    <div class="stays-at-top">
        <span class="heading_text marginleft20">   Parameter Name</span>
        <span class="heading_text marginleft134">   Parameter Type</span>
        <span class="heading_text marginleft140">  Parameter Length</span>
     </div>
     <ul class="add_mapping">
     <logic:notEmpty name="crmProjectForm" property="crmParameters">
							<logic:iterate id="crmParameters" name="crmProjectForm" property="crmParameters" indexId="ctr">
								<logic:equal name="crmParameters" property="editable" value="true">
      						<li class="first">
     								 <span class="floatl">
										<html:hidden name="crmParameters" property="editable" value="true" indexed="true"></html:hidden> 
										<html:hidden name="crmParameters" property="createdBy" value="${crmParameters.createdBy }" indexed="true"></html:hidden>
									</span>
									<span class=" floatl marginright15">
      	 								<html:text name="crmParameters" property="parameterName" styleClass="textbox" indexed="true"/>
       								</span>
									<span class="dropdownWithoutJs floatl marginright15">
										 <html:select name="crmParameters" property="parameterType" indexed="true">
											 <html:options styleClass="select" name="crmProjectForm" property="parameterTypes"></html:options>
										 </html:select>
									</span> 
									<span class=" floatl marginleft15">
										<html:text name="crmParameters" property="parameterLength" styleClass="textbox" value="${crmParameters.parameterLength}" indexed="true" onkeyup="javascript:checkNumbers(${ctr })" onclick="removeZeroFromNumber(this);" onfocus="removeZeroFromNumber(this);"></html:text>
									</span>  
									<span class="floatl marginleft10">
										<html:hidden name="crmParameters" property="status" value="A" indexed="true"/>
										    <html:link href="javascript:deleteParameterFromProject(${ctr})" styleClass="close">
										    	<img src="images/bg/delete.png" />
										   	</html:link>
									</span>
									<p class="clear"></p>
      </li>
      </logic:equal>
								<logic:equal name="crmParameters" property="editable" value="false">
								<li>
										<html:hidden name="crmParameters" property="editable" value="false" indexed="true"></html:hidden>
										<html:hidden name="crmParameters" property="createdBy" value="${crmParameters.createdBy }" indexed="true"></html:hidden>
										<html:hidden name="crmParameters" property="parameterId" value="${crmParameters.parameterId }" indexed="true"></html:hidden>
										<span class=" floatl marginright15">
      	 									<html:text name="crmParameters" property="parameterName" styleClass="textbox" indexed="true" readonly="true"></html:text>
       									</span>
       									<span class="dropdownWithoutJs floatl marginright15">
       									     <html:select name="crmParameters" property="parameterType" indexed="true">
												<html:options  name="crmProjectForm"	property="parameterTypes"></html:options>
											</html:select>
										</span> 
										<span class=" floatl marginleft15">
										       <html:text name="crmParameters" property="parameterLength" styleClass="textbox" indexed="true" onkeyup="javascript:checkNumbers(${ctr})"></html:text>
										      
										</span> 
										<span class="marginleft9">
										 	<html:radio name="crmParameters" property="status" value="A" indexed="true">Active</html:radio>
											<html:radio name="crmParameters" property="status" value="I" indexed="true">Inactive</html:radio>
										</span>
									</li>
								</logic:equal>
								</logic:iterate>
						</logic:notEmpty>
     </ul>
      </div>
     </div> 
    <div class="floatr inner_right">
   <logic:equal name="crmProjectForm" property="regValue" value="true">
    <div class="floatr inner_right">
    	<html:link href="#" styleId="regProject" styleClass="main_button"><span>Register</span></html:link>
    </div>
 </logic:equal>
  <logic:equal name="crmProjectForm" property="regValue" value="false">
   		 <div class="floatr inner_right">
			<html:link href="javascript:updateExtrnalProject();" styleId="submit_updateParameter" styleClass="main_button"><span>Update</span></html:link>
		 </div>
  </logic:equal>
		<html:hidden name="crmProjectForm" property="rowCounter"	styleId="rowIndex_createparameter"></html:hidden>
 </div>
    
    </div>
    <p class="clear"></p>
     </div>
 </div>
 
 </html:form>
</div>
