<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib uri="/WEB-INF/tlds/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/tlds/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/tlds/displaytag.tld" prefix="display"%>
<%@taglib uri="/WEB-INF/tlds/struts-html.tld" prefix="html"%> 
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<div class="loadingPopup hidden"></div>
<div id="section"  align="center">
<html:form action="/qrcConfig"  method="post" styleId="qrcRcaConfiguration">
 <div class="section">
   <h2>Manage Action</h2>
   <!-- Success Messages Starts -->
	<div class="success_message" id="configMsg">
		<logic:messagesPresent message="true">
   		<html:messages id="message" message="true">
		<bean:write name="message" />
		</html:messages>
		</logic:messagesPresent>
	</div>
<!-- Success Messages Ends -->
	<div class="error_message" id="error"><html:errors /></div>
   <div class="inner_section">
   <%int j=0; %>
   <div class="inner_left_lead floatl">
   <p class="floatl">
	<strong>Category<span class="error_message verticalalignTop">*</span></strong> 
	 <span class="qrcConfigDropdownWithoutJs"> 
	  <html:select property="qrcCategoryId" name="qrcConfigForm" styleId="rcaCategoryID" onchange="getQrcRcaConfigurations(this.value, $('#rcaServiceName').val());">
        <html:option value="0">Please Select</html:option>
        <html:optionsCollection name="qrcConfigForm" property="qrcCategories" value="qrcCategoryId" label="qrcCategory" />
      </html:select>
      <font></font>
     </span>
	</p>
    <p  class="floatl marginleft30">
      <strong>Service Name<span class="error_message verticalalignTop">*</span></strong> 
      <span class="qrcConfigDropdownWithoutJs"> 
       <html:select property="serviceName" name="qrcConfigForm" styleId="rcaServiceName" onchange="getQrcRcaConfigurations($('#rcaCategoryID').val(),this.value);">
         <html:option value="">Please Select</html:option>
         <html:optionsCollection property="productTypes" name="qrcConfigForm" value="contentValue" label="contentName" />
       </html:select>
       <font></font>
      </span>
    </p>
   <br class="clear"/>
   <div id="role_group_view1" class="relative margintop20" style="width: 745px;">
    <span class="addbtn_role_group"><a href="javascript:void(0)" id="addRowRCAConfiguration" class="grey_button_add" ><span>Add</span></a></span>
    <div class="display_group" style="height: 250px;">
      <div class="stays-at-top">
        <span class="heading_text"> Action Taken</span> 
     </div>
     <logic:notEmpty name="qrcConfigForm" property="qrcActionTakenPojos">
       <ul class="add_rcaValue">
         <c:forEach items="${ qrcConfigForm.qrcActionTakenPojos }" var="qrcActionTakenPojos" varStatus="current">
           <li class="${ current.index mod 2 eq 0 ? 'first' : 'second' }">
            <span class="manageRcaReason floatl"> 
             <html:checkbox name="qrcActionTakenPojos" property="editable" value="true" indexed="true" onclick="${ qrcActionTakenPojos.editable ? ( qrcActionTakenPojos.actionId gt 0 ? 'toggleEditableRCA('.concat(current.index).concat(');') : 'return false;' ) : 'toggleEditableRCA('.concat(current.index).concat(');') }"/>
            </span> 
            <span class=""> 
             <html:text styleClass="reasonTextbox" name="qrcActionTakenPojos" property="actionTaken" indexed="true" readonly="${ qrcActionTakenPojos.editable ? 'false' : 'true' }" maxlength="128"/><font></font>
            </span> 
            <span class="marginleft13"> 
             <html:radio name="qrcActionTakenPojos" property="status" value="A" indexed="true" onclick="${ qrcActionTakenPojos.editable ? '' : 'return false;' }">Active</html:radio> 
             <html:radio name="qrcActionTakenPojos" property="status" value="I" indexed="true" onclick="${ qrcActionTakenPojos.editable ? '' : 'return false;' }">Inactive</html:radio>
            </span> 
            <c:if test="${ qrcActionTakenPojos.editable and (qrcActionTakenPojos.actionId eq 0) }">
              <span class="marginleft13"> 
               <html:link href="javascript:removeRCAConfiguration(${ current.index })" styleClass="close">
               	<img src="images/bg/delete.png" align="absmiddle" title="delete" />
               </html:link>
              </span>
            </c:if></li>
            <%j=j+1; %>
          </c:forEach>
        </ul>
     </logic:notEmpty>
     <logic:empty name="qrcConfigForm" property="qrcActionTakenPojos">
           <span class="error_message"> &nbsp;Please click on 'Add' button to add Action. </span>
     </logic:empty>
     </div>
     </div>
    </div>
    <div class="floatr inner_right">
     <logic:equal name="crmRoles" property="available(create_qrc_cnf,update_qrc_cnf)" value="true" scope="session">
     <a href="javascript:qrcRcaConfig(<%=j %>)"  class="main_button">Configure</a>
     </logic:equal>
    </div>
    <p class="clear"></p>
     </div>
     <font></font>
   </div>
  <p class="clear"></p>
 </html:form>
 
</div>
