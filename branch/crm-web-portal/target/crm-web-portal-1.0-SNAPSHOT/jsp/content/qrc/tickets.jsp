<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib uri="/WEB-INF/tlds/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/tlds/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/tlds/displaytag.tld" prefix="display"%>
<%@taglib uri="/WEB-INF/tlds/struts-html.tld" prefix="html"%> 
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<style>
.ui-autocomplete {
  max-height: 150px;
  overflow-y: auto;
}
</style>
<script type="text/javascript" src="javascript/jquery-ui.js"></script>
<link rel="stylesheet" href="css/jquery-ui.css" />
<script type="text/javascript">
window.onload = function(){
	var newCategoryId=$('#categoryIdSearch').val();
	document.onclick = function(){
	val = $('#categoryIdSearch').val();
	populateTicket('subCategoryIdSearch',val,'subSubCategoryIdSearch');
	$('#categoryIdSearch').bind('change', function() {
	val = $('#categoryIdSearch').val();
	if(newCategoryId==val)	{
		populateTicket('subCategoryIdSearch',val,'subSubCategoryIdSearch');
	}
	else{
		$('#subCategoryIdSearch').val('');
		$('#subSubCategoryIdSearch').val('');
	}
	});
	};
};
/*window.onload = function(){
	document.onclick = function(){
		if ( $( "#checkForTicketPage" ).val() != undefined && $( "#checkForTicketPage" ).val() != null && $( "#checkForTicketPage" ).val() == 'true' ) {
			document.getElementById( 'showSubCategoryList' ).style.display = 'none';
			document.getElementById( 'showSubSubCategoryList' ).style.display = 'none';
		}
	};
};*/
</script><%--
<input type="hidden" id="checkForTicketPage" value="true">
 --%>
<div class="loadingPopup hidden"></div>
<div id="section" >
<div class="section">
<logic:notEmpty name="qrcForm" property="custDetailsPojo.customerId">
  <jsp:include page="crfCustomerDescription.jsp"></jsp:include>
	
</logic:notEmpty>
<div class="inner_section ">
<div class="inner_left_lead floatl  qrcLeft">
<bean:define id="activeMenu" value="tickets"/>
<%@include file="qrcMenu.jsp"%>
</div>
<div class=" floatl manageGISRight qrcRight">

<html:form action="/manageQrc" method="post" styleId="generateQrcTicket" styleClass="">


<div class="ticketSearch">
<h4 style="border:none; float:left; width:auto; position: absolute; top:-49px; right:-2px;"> <logic:equal name="crmRoles" property="available(create_qrc_tkt)" value="true" scope="session">
    <a href="javascript:void(0);" class="yellow_button floatr margintop7 generateTicketBtn">Generate New Ticket</a>
  </logic:equal></h4>
<div style="height:35px;">
 <div class="success_message" id="msgDiv" >
  <logic:messagesPresent message="true" >
	  <html:messages id="message" message="true">
	  <bean:write name="message" />
	  </html:messages>
  </logic:messagesPresent>
  </div>
 <div class="error_message" id="error"><html:errors property="appError"/></div>

</div> 	<!-- for tickets search -->
	
	
	<div style="width:900px;margin-bottom: 25px;" id="ticketListDiv">
	
<h4>Ticket List
<a class="floatr margintop7">
<span class="LmsdropdownWithoutJs"> 
<html:select property="srTicketPojo.status" name="qrcForm">
<logic:notEmpty name="qrcForm" property="ticketStatusList">
<html:optionsCollection name="qrcForm" property="ticketStatusList" label="contentName" value="contentValue"/>
</logic:notEmpty>
</html:select>
</span>
<span class="floatr">
 <input class="go_button" type="button" value="Go" onclick="javascript:searchCustomerTicket();" />
 </span>
 </a>
</h4>
<logic:notEmpty name="qrcForm" property="srTicketsPojos">
<div >
<display:table id="qrcTicketTable" name="${ qrcForm.srTicketsPojos }" class="dataTable tList" requestURI="" style="width: 100%;" pagesize="15" >
	<display:column title="Sr. No." class="${qrcTicketTable.color}" maxLength="5"><bean:write name="qrcTicketTable_rowNum" /></display:column>
	<display:setProperty name="paging.banner.placement" value="bottom" />
	<display:column class="${qrcTicketTable.color}" title="Ticket ID">
	<logic:equal name="crmRoles" property="available(view_qrc_tkt)" value="true" scope="session">
	<a href="javascript:viewQRC('<bean:write name="qrcTicketTable" property="ticketId"/>','false','qrcTicketView','0')"><bean:write name="qrcTicketTable" property="srId"/></a>
	</logic:equal>
	</display:column>
	<display:column title="Category" class="${qrcTicketTable.color}" property="qrcCategory" ></display:column>
	<display:column title="Sub Category" class="${qrcTicketTable.color}" property="qrcSubCategory" ></display:column>
	<display:column title="Sub Sub Category" class="${qrcTicketTable.color}" property="qrcSubSubCategory" ></display:column>
	<display:column title="QRC Type" class="${qrcTicketTable.color}" >
	<logic:notEmpty name="qrcTicketTable" property="qrcType">
	<bean:write name="crmRoles" property="displayEnum(qrcType,${qrcTicketTable.qrcType})" scope="session" />
	</logic:notEmpty>
	</display:column>
	<display:column title="Resolution Type" class="${qrcTicketTable.color}"><bean:write name="crmRoles" property="displayEnum(ticketAction,${ qrcTicketTable.resolutionType })"/> </display:column>
	<display:column title="Status"  class="${qrcTicketTable.color}">
	<logic:notEmpty name="qrcTicketTable" property="status">
	<bean:write name="crmRoles" property="displayEnum(qrcTicketStatus,${qrcTicketTable.status})" scope="session" />
	</logic:notEmpty>
	</display:column>
	<display:column title="Created By" class="${qrcTicketTable.color}" property="createdBy" ></display:column>
	<display:column title="Followup On" class="${qrcTicketTable.color}">
	
	<fmt:parseDate value="${qrcTicketTable.createdTime}" pattern="yyyy-MM-dd'T'HH:mm:ss" var="xmlCreatedDate"/>
	<fmt:formatDate var="createdDate" value="${xmlCreatedDate}" pattern="dd-MMM-yyyy" />
	
	<fmt:parseDate value="${qrcTicketTable.followupOn}" pattern="yyyy-MM-dd'T'HH:mm:ss" var="xmlFollowupOn"/>
	<fmt:formatDate var="followupOn" value="${xmlFollowupOn}" pattern="dd-MMM-yyyy" />
     <c:if test="${followupOn eq createdDate }">
      -
     </c:if>
     <c:if test="${followupOn ne createdDate }"> 
      <bean:write name="crmRoles" property="xmlDate(${qrcTicketTable.followupOn})" scope="session"/>
     </c:if>
	</display:column>
	<display:column title="Created Date" class="${qrcTicketTable.color}">
	<bean:write name="crmRoles" property="xmlDate(${qrcTicketTable.createdTime})" scope="session"/></display:column>
</display:table>
</div>
</logic:notEmpty>
	<div >
	<html:errors  property="noRecordFound"/>
	</div>
</div>
	
	
<div class="relative">	

<h4>Ticket Search
 
</h4>
<div class="clear marginleft10 inner_left_lead floatl">
<p class="floatl"><strong> Category </strong> 
	<span class="qrcticketDropdownWithoutJs">
		<html:select name="qrcForm" styleId="categoryIdSearch" styleClass="" property="srTicketPojo.qrcCategoryId">
		<option value="0">Please Select </option>
		<html:optionsCollection name="qrcForm" property="qrcCategoriesPojos" label="qrcCategory" value="qrcCategoryId"/>
		</html:select>
		<font></font>
	</span> 
</p>

<p class="floatl marginleft30"><strong> Sub Category  </strong> 
	<span class="dropdownWithoutJs">
		<html:text styleId="subCategoryIdSearch" styleClass="textbox" name="qrcForm" value="" property="srTicketPojo.qrcSubCategory"></html:text>
		<html:hidden name="qrcForm" property="srTicketPojo.qrcSubCategoryId" styleId="qrcSubCategoryId"/>
	</span>
	<%--<span class="qrcticketDropdownWithoutJs">
		<html:select name="qrcForm" styleId="subCategoryIdSearch" styleClass="" property="srTicketPojo.qrcSubCategoryId" onchange="populateSubSubCategories('categoryIdSearch','subSubCategoryIdSearch',this.value);">
		<html:option value="0">Please Select </html:option>
		<html:optionsCollection name="qrcForm" property="qrcSubCategoriesPojos" label="qrcSubCategory" value="qrcSubCategoryId"/>
		</html:select>
		<font></font>
	</span> --%> 
</p>
<p class="floatl marginleft30"><strong> Sub Sub Category </strong> 
	<span class="dropdownWithoutJs">
		<html:text styleId="subSubCategoryIdSearch" styleClass="textbox" name="qrcForm" value="" property="srTicketPojo.qrcSubSubCategory"></html:text>
		<html:hidden name="qrcForm" property="srTicketPojo.qrcSubSubCategoryId" styleId="qrcSubSubCategoryId"/>
	</span>
	<%--<span class="qrcticketDropdownWithoutJs">
		<html:select name="qrcForm" styleId="subSubCategoryIdSearch" styleClass="" property="srTicketPojo.qrcSubSubCategoryId"> onchange="populateActions('resolutionType',this.value);" 
		<html:option value="0">Please Select </html:option>
		<html:optionsCollection name="qrcForm" property="qrcSubSubCategoriesPojos" label="qrcSubSubCategory" value="qrcSubSubCategoryId"/>
		</html:select>
		 <font></font>
	</span>--%> 
 </p>
<p class="floatl clear margintop20">
	<strong>Start Date</strong>
	<html:text name="qrcForm" styleClass="tcal" property="srTicketPojo.displayCreatedTime" styleId="startDate"/>
	 <font></font>
</p>
 <p class="floatl marginleft30 margintop20">
	 <strong>End Date</strong>
	 <html:text name="qrcForm" styleClass="tcal" property="srTicketPojo.displayLastModifiedTime" styleId="endDate"/>
	 <font></font>
	 <font></font>
</p>	
 </div>
 
 <div class="floatr inner_right">
<p class="buttonPlacement">
	<a href="javascript:searchCustomerTicket();" id="" class="main_button marginleft10"><span>Search</span></a>
</p>
</div>
<br class="clear" />
</div>
<div>


</div>
</div>
   
<!-- for tickets creation -->   
 <div class="ticketCreation hidden">

<h4>Ticket Generation <a href="javascript:void(0);" class="yellow_button floatr margintop7 ticketSearchBtn">Search Ticket</a></h4>  
<div class="relative inner_left_lead">
<div class="floatl">
<html:hidden name="qrcForm" property="srTicketPojo.mappingId" value="${qrcForm.customerId}"/>
<html:hidden name="qrcForm" property="srTicketPojo.createdBy" value="${sessionScope.userPojo.userId}"/>
<html:hidden name="qrcForm" property="srTicketPojo.qrcType" styleId="qrcType"/>
<html:hidden name="qrcForm" property="srTicketPojo.functionalbinId" styleId="functionalbinId"/>
<html:hidden name="qrcForm" property="custDetailsPojo.product" styleId="productId"/>
<html:hidden name="qrcForm" property="srTicketPojo.createdBy" value="${sessionScope.userPojo.userId}" />
<html:hidden name="qrcForm" property="ticketHistory.createdBy" value="${sessionScope.userPojo.userId}" />
<%--
<p class="floatl">
	<strong> Category <span class="error_message verticalalignTop">*</span> </strong>
	<span class="qrcticketDropdownWithoutJs">
	<html:select name="qrcForm" styleId="categoryId" styleClass="" property="srTicketPojo.qrcCategoryId" onchange="populateTicket('subCategoryId',this.value,'subSubCategoryId');">
	<option value="0">Please Select </option>
	<html:optionsCollection name="qrcForm" property="qrcCategoriesPojos" label="qrcCategory" value="qrcCategoryId"/>
	</html:select>
	<font></font>
	</span> 
</p>
 --%>
 <html:hidden name="qrcForm" styleId="categoryId" property="srTicketPojo.qrcCategoryId"/>
<p class="floatl"><strong> Sub Category <span class="error_message verticalalignTop">*</span> </strong> 
 <html:text styleId="qrcSubCategoryTextId" styleClass="textbox" name="qrcForm" maxlength="28" value="" property="srTicketPojo.qrcSubCategory"></html:text>
   <html:hidden name="qrcForm" property="srTicketPojo.qrcSubCategoryId" styleId="subCategoryId"/>
   <%--
   <span id="showSubCategoryList" class="showList showlist_ticket">
   <html:select name="qrcForm" property="srTicketPojo.qrcSubCategoryId" styleId="subCategoryId" multiple="true" onclick="fillTextList('subCategoryId','qrcSubCategoryTextId','showSubCategoryList')" onchange="javascript:fillCategoryHiddenField();">
   </html:select>
   </span>
    --%>
   <font id="subCategoryError"></font>
</p>

<p class="floatl marginleft30"><strong> Sub Sub Category <span class="error_message verticalalignTop">*</span></strong>
 <html:text styleId="qrcSubSubCategoryTextId" styleClass="textbox" name="qrcForm" maxlength="28" value="" property="srTicketPojo.qrcSubSubCategory"></html:text>
  <html:hidden name="qrcForm" property="srTicketPojo.qrcSubSubCategoryId" styleId="subSubCategoryId" />
   <%--
   <span id="showSubSubCategoryList" class="showList showlist_ticket"> 
   <html:select name="qrcForm" property="srTicketPojo.qrcSubSubCategoryId" styleId="subSubCategoryId" multiple="true" onclick="fillTextList('subSubCategoryId','qrcSubSubCategoryTextId','showSubSubCategoryList')" onchange="populateActions('resolutionType',this.value);">
   </html:select>
   </span>
    --%>
   <font id="subSubCategoryError"></font>
</p>
<p class="floatl clear"></p>
<p class="floatl clear"><strong> Remarks <span class="error_message verticalalignTop">*</span></strong>
	<html:textarea name="qrcForm" cols="27" rows="38" styleClass="LmsRemarkstextarea" property="ticketHistory.remarks" value="" styleId="remarks"></html:textarea>
	<font></font>
</p>

<p class="floatl clear"><strong> Calling Number</strong>
<html:text styleClass="Lmstextbox" name="qrcForm" maxlength="10" property="srTicketPojo.callingNo" styleId="callingNo" value="" onkeyup="javascript:ValidatenReplaceAlphanumeric(this);"/>
<font></font>
</p>
<!-- 
<p class="floatl marginleft30"><strong> Field Visit Required</strong>
<label class="label_radio" for="visitRequiredYes">
<html:radio name="qrcForm" property ="srTicketPojo.visitRequired" styleId="visitRequiredYes" value="Y" >Yes</html:radio>
</label>
<label  class="label_radio" for="visitRequiredNo">
<html:radio name="qrcForm"  property="srTicketPojo.visitRequired" styleId="visitRequiredNo" value="N">No</html:radio>
</label>
</p> -->

 <p class="floatl clear"><strong> Status <span class="error_message verticalalignTop">*</span> </strong> 
<span class="dropdownWithoutJs">
<html:select name="qrcForm" styleId="resolutionType" styleClass="" property="srTicketPojo.resolutionType" onchange="fillFunctionBinIdForForward('functionalbinId',this.value);">
 <html:optionsCollection name="qrcForm" property="ticketActions" label="contentName" value="contentValue"/>
</html:select>
 <font></font>
</span> 
</p>

 <div class="floatl clear rcaResolutionCodeDropdowns hidden">
 <p class="floatl clear"><strong> Action Taken <span class="error_message verticalalignTop">*</span> </strong> 
	 <span class="dropdownWithoutJs">
		<html:select name="qrcForm" styleId="actionTakenId" styleClass="" property="srTicketPojo.actionTakenId" onchange="fillResolutionListResCode(this.value,'rootCauseId');">
		<html:optionsCollection name="qrcForm" property="rcaList" label="contentName" value="contentValue"/>
		</html:select>
		<font></font>
	</span>
</p>
<p class="floatl clear"><strong> Root Cause <span class="error_message verticalalignTop">*</span> </strong>
	<span class="dropdownWithoutJs">
	<html:select name="qrcForm" styleId="rootCauseId" styleClass="" property="srTicketPojo.rootCauseId">
	<html:optionsCollection name="qrcForm" property="resoulationCodelist" label="resolutionCode" value="rootCauseId"/>
	</html:select>  
	</span> 
	<font></font>
</p>
</div>
<br class="clear" />
</div>
<div class="floatr inner_right">
	<p class="buttonPlacement"> <a href="javascript:saveTicket();" id="saveTicket" class="main_button  "><span>Generate</span></a> </p>
</div>
<br class="clear" />
</div>
</div>
</html:form>
</div>
<p class="clear"></p>
