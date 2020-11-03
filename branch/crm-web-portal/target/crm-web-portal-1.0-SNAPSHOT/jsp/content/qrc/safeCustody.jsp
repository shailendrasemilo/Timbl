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
		<bean:write name="message"/>
		</html:messages>
	</logic:messagesPresent>
	</div>
	<div class="error_message" id="error">
		<html:errors />
	</div>
	
		<h4>Customer Safe Custody </h4>
	
	
  <div class="relative inner_left_lead">
		<html:form action="/manageQrc">
		<div class="floatl">
		<p class="floatl ">
			<strong>Customer ID</strong>
			<html:text property="customerId" name="qrcForm" styleClass="textbox gray_text" readonly="true" styleId="sfCustomerId"></html:text>
		</p>
		
		<p class="floatl clear">
			<strong> Reason </strong> 
			<span class="dropdownWithoutJs"> 
			<html:select property="exceptionReason" name="qrcForm" styleId="SC_ReasonId">
			<html:option value="0">Please select</html:option>
			<logic:notEmpty name="qrcForm"  property="crmRcaReasonPojos">
			<html:optionsCollection name="qrcForm" property="crmRcaReasonPojos" label="categoryValue" value="categoryId" />
			</logic:notEmpty>
			</html:select>
			<font class="errorTextbox hidden">Please select reason.</font>
			</span>
		</p>
		
		<p class="floatl marginleft30">
			<strong>Ticket ID</strong>
			<html:text property="srTicketNo" name="qrcForm" maxlength="20" styleClass="textbox" styleId="SC_TicketId"></html:text>
		</p>
		<p class="floatl clear">
			<strong> Remarks</strong>
			<html:textarea property="remarksPojo.remarks" name="qrcForm" styleClass="LmsRemarkstextarea" styleId="SC_RemarksId"></html:textarea>
			<font class="errorRemarks hidden">Please enter remarks between [2-4000].</font>
		</p>
		
		 
		<br class="clear" />
		</div>
		<div class="floatr inner_right">
		<p class="buttonPlacement">
		
			<logic:equal name="crmRoles" property="available(update_qrc_cs)" value="true" scope="session">
			<c:if test="${ qrcForm.visibileButton eq true}">
			<a href="#" id="submit_sfCustody" class="main_button_multiple  ">
			<span>Safe Custody
			</span>			
			</a>
			</c:if>
			</logic:equal>
		</p>
		</div>
	</html:form>
	<br class="clear" />
   </div>
</div>
<p class="clear"/>