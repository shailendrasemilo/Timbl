<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib uri="/WEB-INF/tlds/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/tlds/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/tlds/displaytag.tld" prefix="display"%>
<%@taglib uri="/WEB-INF/tlds/struts-html.tld" prefix="html"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div class="loadingPopup hidden"></div>
<div id="section">
<div class="section">
<logic:notEmpty name="qrcForm" property="custDetailsPojo.customerId">
  <jsp:include page="crfCustomerDescription.jsp"></jsp:include>

</logic:notEmpty>
<div class="inner_section ">
	<div class="inner_left_lead floatl  qrcLeft">
	<bean:define id="activeMenu" value="barring"/>
		<%@include file="qrcMenu.jsp"%>
	</div>
<div class=" floatl manageGISRight  qrcRight">
	<div class="success_message">
		<logic:messagesPresent message="true">
		<html:messages id="message" message="true">
		<bean:write name="message" />
		</html:messages>
	</logic:messagesPresent>
	</div>
	<div class="error_message" id="error">
		<html:errors />
	</div>
	<logic:equal value="A" property="custDetailsPojo.status" name="qrcForm">
		<h4>Bar Customer</h4>
	</logic:equal>
	<logic:equal value="B" property="custDetailsPojo.status" name="qrcForm">
		<h4>Unbar Customer</h4>
	</logic:equal>
  <div class="relative inner_left_lead">
		<html:form action="/manageQrc">
		<div class="floatl">
		<p class="floatl ">
			<strong>Customer ID</strong>
			<html:text property="customerId" name="qrcForm" styleClass="textbox gray_text" readonly="true" styleId="bubCustomerId"></html:text>
		</p>
		
		<p class="floatl clear">
			<strong> Reason<sup class="req">*</sup></strong> 
			<span class="dropdownWithoutJs"> 
			<html:select property="exceptionReason" name="qrcForm" styleId="bubReasonId">
			<html:option value="0">Please select</html:option>
			<html:optionsCollection name="qrcForm" property="crmRcaReasonPojos" label="categoryValue" value="categoryId" />
			</html:select>
			<font class="errorTextbox hidden">Please select reason.</font>
			</span>
		</p>
		
		<p class="floatl marginleft30">
			<strong>Ticket ID</strong>
			<html:text property="srTicketNo" name="qrcForm" maxlength="20" styleClass="textbox" styleId="bubTicketId"></html:text>
		</p>
		<p class="floatl clear">
			<strong> Remarks<sup class="req">*</sup></strong>
			<html:textarea property="remarksPojo.remarks" name="qrcForm" styleClass="LmsRemarkstextarea" styleId="bubRemarksId"></html:textarea>
			<font class="errorRemarks hidden">Please enter remarks between [2-4000].</font>
		</p>
		
		 <c:if test="${ qrcForm.inExceptionList }">
			<p class="floatl marginleft30" style="margin-top: 55px;">
				<strong>&nbsp;</strong> <a href="#" id="remove_bub" class="yellow_button"> <span>Remove from Exception List</span></a>
			</p>
		</c:if>
		<br class="clear" />
		</div>
		<div class="floatr inner_right">
		<p class="buttonPlacement">
			<logic:equal name="crmRoles" property="available(update_qrc_cs)" value="true" scope="session">
				<a href="#" id="submit_bub" class="main_button_multiple  "> <c:if test="${ qrcForm.custDetailsPojo.status eq 'A' }">
				<span>Bar Service</span>
				</c:if>
				<c:if test="${ qrcForm.custDetailsPojo.status eq 'B' }">
				<span>Unbar Service</span>
				</c:if>
				</a>
			</logic:equal>
		</p>
		</div>
	</html:form>
	<br class="clear" />
   </div>
</div>
<p class="clear"/>