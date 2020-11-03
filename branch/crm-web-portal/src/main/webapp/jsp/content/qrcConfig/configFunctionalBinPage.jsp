<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib uri="/WEB-INF/tlds/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/tlds/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/tlds/displaytag.tld" prefix="display"%>
<%@taglib uri="/WEB-INF/tlds/struts-html.tld" prefix="html"%> 
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<script type="text/javascript" src="javascript/jquery-ui.js"></script>
<div class="loadingPopup hidden"></div>
<div id="section"  align="center">
<html:form action="/qrcConfig"  method="post" styleId="qrcBinMapping">
 <div class="section">
   <h2>Manage Bin Mapping</h2>
   <!-- Success Messages Starts -->
	<div class="success_message" >
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
    <strong>QRC Type <span class="error_message verticalalignTop">*</span></strong>
	<label class="label_radio" for="qrcTypeRequest">
	  <html:radio name="qrcConfigForm" property ="qrcType" styleId="qrcTypeRequest" value="R" onclick="populateSubSubCategoriesforRadio('binCategoryID','binSubSubCategoryId',binSubCategoryId);" >Request</html:radio>
	</label>
	<label  class="label_radio" for="qrcTypeComplaint">
	  <html:radio name="qrcConfigForm"  property="qrcType" styleId="qrcTypeComplaint" value="C" onclick="populateSubSubCategoriesforRadio('binCategoryID','binSubSubCategoryId',binSubCategoryId);">Complaint</html:radio>
	</label>
	<font></font>
   </p>
   <p class="floatl marginleft30">
  <logic:notEmpty name="qrcConfigForm" property="resolutionStr">
    <strong> Resolution Type </strong>
    <bean:write name="qrcConfigForm" property="resolutionStr"/>
    </logic:notEmpty>
    <html:hidden name="qrcConfigForm" property="resolutionStr" styleId="dataActions"/>
   </p>
   <p class="floatl clear">
	<strong>Category <span class="error_message verticalalignTop">*</span></strong> 
	 <span class="qrcConfigDropdownWithoutJs"> 
	  <html:select property="qrcCategoryId" name="qrcConfigForm" styleId="binCategoryID" onchange="populateSubCategoriesforFunctionBin('binSubCategoryId',this.value,'binSubSubCategoryId');">
	  <html:option value="0">Please Select</html:option>
        <html:optionsCollection name="qrcConfigForm" property="qrcCategories" value="qrcCategoryId" label="qrcCategory" />
      </html:select>
      <font></font>
     </span>
	</p>
    <p  class="floatl marginleft10">
      <strong> Sub Category <span class="error_message verticalalignTop">*</span> </strong> 
      <span class="qrcConfigDropdownWithoutJs"> 
       <html:select name="qrcConfigForm" styleId="binSubCategoryId" styleClass="" property="qrcSubCategoryId" onchange="populateSubSubCategories('binCategoryID','binSubSubCategoryId',this.value);">
       		<html:option value="0">Please Select</html:option>
       		<logic:notEmpty name="qrcConfigForm" property="qrcSubCategoriesPojos">
              	<html:optionsCollection name="qrcConfigForm" property="qrcSubCategoriesPojos" label="qrcSubCategory" value="qrcSubCategoryId" />
            </logic:notEmpty>
       </html:select>
       <font></font>
      </span>
    </p>
    <p  class="floatl marginleft10">
      <strong> Sub Sub Category <span class="error_message verticalalignTop">*</span> </strong> 
      <span class="qrcConfigDropdownWithoutJs"> 
       <html:select name="qrcConfigForm" styleId="binSubSubCategoryId" styleClass="" property="qrcSubSubCategoryId" onchange="listActions('dataActions',this.value);">
       <html:option value="0">Please Select</html:option>
       		<logic:notEmpty name="qrcConfigForm" property="qrcSubSubCategoriesPojos">
              	<html:optionsCollection name="qrcConfigForm" property="qrcSubSubCategoriesPojos" label="qrcSubSubCategory" value="qrcSubSubCategoryId" />
            </logic:notEmpty>
       </html:select>
       <font></font>
      </span>
    </p>

   <br class="clear"/>
   <div id="role_group_view1" class="relative margintop20" style="width: 745px;">
    <span class="addbtn_role_group"><a href="javascript:void(0)" id="addRowBinConfiguration" class="grey_button_add" ><span>Add</span></a></span>
    <div class="display_group" style="height: 250px;">
      <div class="stays-at-top">
        <span class="heading_text">  Bin Mapping</span> 
     </div>
     <logic:notEmpty name="qrcConfigForm" property="categoryBinMappingPojos">
     <div id="functionalBinList">
       <ul class="add_rcaValue">
         <c:forEach items="${ qrcConfigForm.categoryBinMappingPojos }" var="categoryBinMappingPojos" varStatus="current">
           <li class="${ current.index mod 2 eq 0 ? 'first' : 'second' }">
            <span class="manageRcaReason floatl"> 
             <html:checkbox name="categoryBinMappingPojos" property="editable" value="true" indexed="true" onclick="${ categoryBinMappingPojos.editable ? ( categoryBinMappingPojos.mappingId gt 0 ? 'toggleEditableBinMapping('.concat(current.index).concat(');') : 'return false;' ) : 'toggleEditableBinMapping('.concat(current.index).concat(');') }"/>
            </span> 
            <span class="mappingDropdown">
            	<html:select styleClass="select" name="categoryBinMappingPojos" property="fromBinId"  indexed="true" disabled="${ categoryBinMappingPojos.editable ? 'false' : 'true' }">										
				  <html:optionsCollection name="qrcConfigForm" property="functionBins" label="categoryValue" value="categoryId"/>
				</html:select> 
            </span> 
            <span class="mappingDropdown marginleft13">
            	<html:select styleClass="select" name="categoryBinMappingPojos" property="toBinId"  indexed="true" disabled="${ categoryBinMappingPojos.editable ? 'false' : 'true' }">										
				  <html:optionsCollection name="qrcConfigForm" property="functionBins" label="categoryValue" value="categoryId"/>
				</html:select> 
            </span> 
            <span class="marginleft13"> 
             <html:radio name="categoryBinMappingPojos" property="status" value="A" indexed="true" onclick="${ categoryBinMappingPojos.editable ? '' : 'return false;' }">Active</html:radio> 
             <html:radio name="categoryBinMappingPojos" property="status" value="I" indexed="true" onclick="${ categoryBinMappingPojos.editable ? '' : 'return false;' }">Inactive</html:radio>
            </span> 
            <c:if test="${ categoryBinMappingPojos.editable and (categoryBinMappingPojos.mappingId eq 0) }">
              <span class="marginleft13"> 
               <html:link href="javascript:removeBinConfiguration(${ current.index })" styleClass="close">
               	<img src="images/bg/delete.png" align="absmiddle" title="delete" />
               </html:link>
              </span>
            </c:if></li>
            <%j=j+1; %>
          </c:forEach>
        </ul>
        </div>
     </logic:notEmpty>
     <logic:empty name="qrcConfigForm" property="categoryBinMappingPojos">
           <span class="error_message"> &nbsp;Please click on 'Add' button to add Bin Mapping. </span>
     </logic:empty>
     </div>
     </div>
    </div>
    <div class="floatr inner_right">
      <logic:equal name="crmRoles" property="available(create_qrc_cnf,update_qrc_cnf)" value="true" scope="session">
     <a href="javascript:qrcBinMapping(<%=j %>)"  class="main_button" >Configure</a>
     </logic:equal>
    </div>
    <p class="clear"></p>
     </div>
     <font></font>
   </div>
  <p class="clear"></p>
  
 </html:form>
 
</div>
