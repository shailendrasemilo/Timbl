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
	<bean:define id="activeMenu" value="disconnection"/>
	<%@include file="qrcMenu.jsp"%>
</div>
<div class="floatl manageGISRight  qrcRight">

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
	 <c:if test="${ qrcForm.custDetailsPojo.status eq 'A' or qrcForm.custDetailsPojo.status eq 'B' }">
	 <logic:equal name="crmRoles" property="available(update_qrc_cs)" value="true" scope="session">
		<h4>Temporary Disconnection (TDC)</h4>
	</logic:equal>
	</c:if>
	 <c:if test="${ qrcForm.custDetailsPojo.status eq 'T' or qrcForm.custDetailsPojo.status eq 'SC' }">
	 <logic:equal name="crmRoles" property="available(delete_qrc_cs)" value="true" scope="session">
		<h4>Permanent Disconnection (PDC)</h4>
	</logic:equal>
	</c:if>
<div class="relative inner_left_lead">
<html:form action="/manageQrc">
	<div class="disconnection">
	<p class="floatl">
		<strong>Customer ID</strong> <input type="text" class="textbox" readonly="readonly" value="${ qrcForm.customerId }">
	</p>
	
	<p class="floatl marginleft30">
	<strong>Churn Type <sup class="req">*</sup></strong> <span class="dropdownWithoutJs"> <html:select property="churnType" name="qrcForm" styleId="discReason">
		<html:option value="">Please select</html:option>
		<html:option value="Voluntary">Voluntary</html:option>
		<html:option value="In-Voluntary">In-Voluntary</html:option>
		</html:select> <font class="errorTextbox hidden">Please select churn type.</font>
		</span>
	</p>
	
	<p class="floatl clear">
		<strong>Reason <sup class="req">*</sup></strong> <span class="dropdownWithoutJs"> <html:select property="remarksPojo.reasonId" name="qrcForm" styleId="discReason">
		<html:option value="0">Please select</html:option>
		<html:optionsCollection property="disconnectionReasons" name="qrcForm" label="categoryValue" value="categoryId" />
		</html:select> <font class="errorTextbox hidden">Please select reason.</font>
		</span>
	</p>
	
	<p class="floatl marginleft30">
		<strong>Ticket ID</strong>
		<html:text property="srTicketNo" name="qrcForm" styleClass="textbox" maxlength="50"></html:text>
	</p>
	
	<p class="clear floatl">
		<strong>Remarks <sup class="req">*</sup></strong>
		<html:textarea property="remarksPojo.remarks" name="qrcForm" styleClass="LmsRemarkstextarea" styleId="discRemarks"></html:textarea>
		<font class="errorRemarks hidden">Please enter remarks between [2-4000]</font>
	</p>
	</div>
	<div class="floatr inner_right">
	<p class="buttonPlacement">
		<c:if test="${ qrcForm.custDetailsPojo.status eq 'A' or qrcForm.custDetailsPojo.status eq 'B' }">
			<logic:equal name="crmRoles" property="available(update_qrc_cs)" value="true" scope="session">
			<a href="#" id="submit_disconnection" class="main_button"> <span>TDC</span></a>
			</logic:equal>
		</c:if>
		<c:if test="${ qrcForm.custDetailsPojo.status eq 'T' or qrcForm.custDetailsPojo.status eq 'SC' }">
			<logic:equal name="crmRoles" property="available(delete_qrc_cs)" value="true" scope="session">
			<a href="#" id="submit_disconnection" class="main_button"> <span>PDC</span></a>
			</logic:equal>
		</c:if>
	</p>
	</div>
</html:form>
<br class="clear">
</div>
</div>
<p class="clear"></p>