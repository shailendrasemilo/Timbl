<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page import="org.apache.struts.Globals"%> 
<%@ page import="org.apache.struts.taglib.html.Constants"%>
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
<bean:define id="activeMenu" value="waiver"/>
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
<h4>Adjustment / Waiver</h4>
	<div class="relative inner_left_lead">
		<html:form action="/manageQrc" method="post">
		<html:hidden property="custWaiverPojo.customerId" name="qrcForm" value="${ qrcForm.customerId }"/>
		<%-- <input type="hidden" name="<%= Constants.TOKEN_KEY %>" value="<%= session.getAttribute(Globals.TRANSACTION_TOKEN_KEY) %>" > --%>
		<div class="floatl">
		<p class="floatl">
		<label class="label_radio"> 
		<html:radio property="custWaiverPojo.waiverType" value="Goodwill Waiver" name="qrcForm" styleClass="waiverType">Goodwill</html:radio>
		 </label>
		<label class="label_radio"> <html:radio property="custWaiverPojo.waiverType" value="Process Waiver" name="qrcForm" styleClass="waiverType">Process</html:radio>
		</label> <font class="errorTextbox hidden">Please select.</font>
		</p>
		 <p class="floatl clear">
		<strong> Select Category<sup class="req">*</sup></strong>
		<span class="LmsdropdownWithoutJs"> <html:select property="custWaiverPojo.waiverHead" name="qrcForm" styleId="waiverHead">
		<html:option value="">Please Select</html:option>
		</html:select>
		<font class="errorTextbox hidden">Please select category</font>
		</span>
		</p>
		 <p class="floatl marginleft30 ">
		<strong>Amount<sup class="req">*</sup></strong>
		<html:text property="custWaiverPojo.waiverAmount" name="qrcForm" styleClass="textbox" styleId="waiverAmount" maxlength="10" onclick="removeZeroFromNumber(this);" onfocus="removeZeroFromNumber(this);" onkeyup="javascript:ValidatenReplaceAlphanumericnDot(this);" ></html:text>
		<font class="errorTextbox hidden">Please enter valid amount.</font>
		</p>
		<p class="floatl marginleft30">
		<strong>Bill No.<sup class="req">*</sup></strong> 
		<span class="LmsdropdownWithoutJs"> 
		<html:select property="custWaiverPojo.billNo" name="qrcForm" styleId="waiverBill">
		<logic:notEmpty name="qrcForm" property="invoiceList">
		<html:option value="">Please Select</html:option>
		<html:optionsCollection name="qrcForm" property="invoiceList" label="billNumber" value="billNumber" />
		</logic:notEmpty>
		</html:select> 
		<font class="errorTextbox hidden">Please select Bill No.</font>
		</span>
		</p>
		<p class="floatl clear">
		<strong>Ticket ID</strong>
		<html:text property="srTicketNo" name="qrcForm" maxlength="20" styleClass="textbox" styleId="waiverSRT"/>
		</p>
		<p class="floatl clear">
		<strong> Remarks<sup class="req">*</sup></strong>
		<html:textarea property="remarksPojo.remarks" name="qrcForm" styleClass="LmsRemarkstextarea" styleId="waiverRemarks"></html:textarea>
		<font class="errorRemarks hidden">Please enter remarks.</font>
		</p>
		<br class="clear" />
		</div>
		<div class="floatr inner_right">
		<p class="buttonPlacement">
		<logic:equal name="crmRoles" property="available(create_qrc_adj)" value="true" scope="session">
		<a href="#" id="submit_waiver" class="main_button  "><span>Submit</span></a>
		</logic:equal>
		</p>
		</div>
		</html:form>
		<br class="clear" />
	</div>
	<div class="margintop30">
	<iframe src="manageQrc.do?method=getWaiverHistory"scrolling="no" frameborder="0"
    	style="border: none; overflow: hidden;width:100%; height: 500px;" allowTransparency="true">
    </iframe>
	</div>
</div>
<p class="clear"/>
