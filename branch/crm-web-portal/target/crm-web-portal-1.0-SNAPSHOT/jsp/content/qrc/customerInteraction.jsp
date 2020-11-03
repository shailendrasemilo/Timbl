<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib uri="/WEB-INF/tlds/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/tlds/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/tlds/displaytag.tld" prefix="display"%>
<%@taglib uri="/WEB-INF/tlds/struts-html.tld" prefix="html"%> 
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%--    code from qrcMenu.jsp    --%>
<div class="loadingPopup hidden"></div>
<div id="section" >
 	<div class="section">
<logic:notEmpty name="qrcForm" property="custDetailsPojo.customerId">
  <jsp:include page="crfCustomerDescription.jsp"></jsp:include>
	
</logic:notEmpty>
   	<div class="inner_section ">
 	<div class="inner_left_lead floatl  qrcLeft">
      <bean:define id="activeMenu" value="customerInteraction"></bean:define>
      <%@include file="qrcMenu.jsp"%>
    </div>
   <div class=" floatl manageGISRight qrcRight">
	<html:form action="/manageQrc" method="post" styleId="generateQrcTicket" styleClass="">
	<logic:messagesPresent property="appError" >
		<bean:define id="errorPresent" value="y"/>
	</logic:messagesPresent>
	<div class="success_message" id="interactionMsg">
	<logic:messagesPresent message="true">
		<html:messages id="message" message="true">
		<bean:write name="message" />
		</html:messages>
	</logic:messagesPresent>
	</div>
     <html:hidden styleClass="textbox" name="qrcForm" property="custInteractionsPojo.customerId" value="${qrcForm.customerId}"/>   
	
	<div id="searchPage" class="searchPage ${ errorPresent eq 'y' ? 'hidden' : '' }">
		
		<div class="relative">
		<h4>Customer Interaction Search
          <logic:equal name="crmRoles" property="available(create_qrc)" value="true" scope="session"> 
            <a href="javascript:void(0);" class="yellow_button floatr margintop7 createPageBtn">Add Interaction</a>
          </logic:equal>
        </h4>
		<div class="clear marginleft10 inner_left_lead floatl">
		<p class="floatl">
			<strong>Category</strong>
			<span class="qrcticketDropdownWithoutJs">
			<html:select name="qrcForm" styleId="interactionCategorySearch" styleClass="" property="custInteractionsPojo.interactionCategory" onchange="populateInteractionSubCategory('interactionSubCategorySearch',this.value);">
		    <html:option value="">Please Select</html:option>
			<html:optionsCollection name="qrcForm" property="customerInteractionCategories" label="contentValue" value="contentValue"/>
		   </html:select>
		   <font></font>
		   </span>
	  </p>
	  <p class="floatl marginleft15">
		  <strong>Sub Category</strong> 
		 <span class="qrcticketDropdownWithoutJs"> 
		 <html:select name="qrcForm" styleId="interactionSubCategorySearch" styleClass="" property="custInteractionsPojo.interactionSubCategory">
		 <html:option value="">Please Select</html:option>
		 <html:optionsCollection name="qrcForm" property="interactionSubCategories" label="categoryValue" value="categoryValue"/>
	     </html:select>
		 <font></font>
		 </span>
	</p>
	<p id="height200" class="clear" style="height: 100px;"/>
	</div>
	<div class="floatr inner_right">
		<p class="buttonPlacement">
		<a href="javascript:searchCustomerInteraction();" id="" class="main_button marginleft10"><span>Search</span></a>
		</p>
	</div>
	<br class="clear" />
	</div>
	<div class="ticketList" id="interactionSearchList">
	
	<p style="margin-top: 10px;"><h4></h4></p>
	<div id="interactionResult">
		<logic:notEmpty name="qrcForm" property="custInteractionsPojos">
		<display:table id="custInteractionsPojo" name="${ qrcForm.custInteractionsPojos }" class="dataTable tList" requestURI="" style="width: 100%;" pagesize="15">
			<display:setProperty name="paging.banner.placement" value="bottom" />
			<display:column title="Sr. No." maxLength="5"><bean:write name="custInteractionsPojo_rowNum" /></display:column>
			<display:column title="Category" property="interactionCategory" />
			<display:column title="Sub Category" property="interactionSubCategory" />
			<display:column title="Created On">
				<bean:write name="crmRoles" property="xmlDate(${custInteractionsPojo.createdTime})" />
			</display:column>
			<display:column title="Created By" property="createdBy" />
			<display:column title="Remarks" property="remarks" />
		</display:table>
	  </logic:notEmpty>
	</div>
	<div>
		<html:errors  property="noRecordFound"/>
	<logic:messagesPresent message="true">
	</logic:messagesPresent>
	</div>
	</div>
	</div>
	<div id="createPage" class="createPage ${ errorPresent eq 'y' ? '' : 'hidden' }">
		<h4>Add Customer Interaction <a href="javascript:void(0);" class="yellow_button floatr margintop7 searchPageBtn">Search Interaction</a></h4> <!-- for error messages --> 
		<html:hidden styleClass="textbox" name="qrcForm" property="custInteractionsPojo.createdBy" value="${sessionScope.userPojo.userId}"></html:hidden>
	    <div class="error_message" id="error">
			 <html:errors property="appError" />
	    </div>
	<div class="relative">
	<div class="floatl">
	<div class="clear marginleft10 inner_left_lead floatl">
	<p class="floatl">
		<strong>Category<sup class="req">*</sup></strong> 
		<span class="qrcticketDropdownWithoutJs">
		<html:select name="qrcForm" styleId="interactionCategory" styleClass="" property="custInteractionsPojo.interactionCategory" onchange="populateInteractionSubCategory('interactionSubCategory',this.value);">
		<html:option value="0">Please Select</html:option>
		<html:optionsCollection name="qrcForm" property="customerInteractionCategories" label="contentValue" value="contentValue"/>
		</html:select>
		<font></font>
	    </span>
	</p>
	
	<p class="floatl marginleft15">
		<strong>Sub Category<sup class="req">*</sup></strong>
		 <span class="qrcticketDropdownWithoutJs">
		<html:select name="qrcForm" styleId="interactionSubCategory" styleClass="" property="custInteractionsPojo.interactionSubCategory">
		<html:option value="0">Please Select</html:option>
		<html:optionsCollection name="qrcForm" property="interactionSubCategories" label="categoryValue" value="categoryValue"/>
		</html:select>
		<font></font>
		</span>
	</p>
	
	<p class="floatl clear">
		<strong> Remarks<sup class="req">*</sup></strong>
		<html:textarea name="qrcForm" cols="27" rows="38" styleClass="LmsRemarkstextarea" property="custInteractionsPojo.remarks" styleId="remarks"></html:textarea>
		<font></font>
		</p>
  </div>
</div>
	<div class="floatr inner_right">
	<p class="buttonPlacement">
		<a href="javascript:postCustomerInteraction();" id="saveInteraction" class="main_button  "><span>Save</span></a>
	</p>
	</div>
	<br class="clear" />
 </div>
</div>
</html:form>
</div>
</div>
    <!-- end -->
<p class="clear"></p>