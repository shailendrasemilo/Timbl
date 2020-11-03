<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="/WEB-INF/tlds/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/tlds/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/tlds/displaytag.tld" prefix="display"%>
<%@taglib uri="/WEB-INF/tlds/struts-html.tld" prefix="html"%>
<html>

<body>


<!----------------- Section ------------------------------->

<div id="section" >
<html:form action="/exProjectParam" styleId="registerProjectAddParameter"  method="post">
 <div class="section" id="user_management">
    <h2><strong>Add parameter </strong> - External Project</h2>
   <div>
        <span>
        	Project Name<html:text styleId="template_name" property="extProjectName" styleClass="textbox" />
		</span>
   </div>
   <p class="textright"><b>Add Parameter</b><a href="javascript:void(0)" id="add" ><img src="images/bg/add.png" /></a></p>
   <div id="role_group_view">
     <p class="add_role_group">
       <span class="floatl"><strong>Parameter Name:</strong><br>
      	 <html:text property="parameterName" styleClass="textbox" maxlength="128"/>
       </span>
       <span class="dropdownWithoutJs marginleft30 floatl"  >
            <br><html:select  property="parameterType">
                 <html:option value="0">Parameter Type</html:option>
                 <html:option value="1">int</html:option>
                 <html:option value="2">float</html:option>
                 <html:option value="3">string</html:option>
            </html:select>
        </span>
        <span class="marginleft30 floatl"><strong>Parameter Length:</strong><br>
        	<html:text property="parameterLength" styleClass="textbox" maxlength="10"/></span>
        <span class="marginleft30 floatl"><a href="javascript:void(0)" class="close"><img src="images/bg/close.png" /></a></span>
     </p>
   </div>
    <p class="textcenter"><html:link href="#" style="cursor: pointer" styleId="regProjectAddParameter" styleClass="button"><span>Save</span></html:link></p>
 </div>
 </html:form>
</div>


</body>
</html>