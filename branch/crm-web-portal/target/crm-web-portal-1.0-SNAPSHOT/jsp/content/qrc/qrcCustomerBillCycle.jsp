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
<bean:define id="activeMenu" value="billCycleChange"/>
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
	<div class="error_message" id="errors">
		<html:errors />
	</div>
	<h4>Change Bill Cycle</h4>
	<div class="relative inner_left_lead">
		<html:form action="/manageQrc" method="post">
			<html:hidden name="oldBillDate" property="custDetailsPojo.billDate" styleId="oldBillDate" value="${qrcForm.custDetailsPojo.billDate}" />
			<div class="floatl"><p><span>
				<label style="font-weight:bold;"> Current Bill Cycle : </label><bean:write name="qrcForm" property="custDetailsPojo.billDate" /></span></p>
				<p class="floatl"><strong> New Bill Cycle<sup class="req">*</sup></strong> 
					<span class="qrcticketDropdownWithoutJs">
						<html:select name="qrcForm" property="paramValue" styleId="newBillDateId">
							<html:option value="0">Please select</html:option>
							<html:option value="5">5</html:option>
							<html:option value="20">20</html:option>
						</html:select>						
						<font class="errorTextbox hidden">Please select New Bill Cycle.</font>
						<span class="hidden"><font class="errorTextbox" style='width:170px;'>Please select any other Bill Cycle.</font></span>
					</span> 
				</p>
				<p class="floatl clear">
					<strong>Ticket ID<sup class="req">*</sup></strong>
					<html:text property="srTicketNo" name="qrcForm" value="" maxlength="20" styleClass="textbox" styleId="changeBillDateSRT"/>
				<font class="errorTextbox hidden">Please provide Ticket ID.</font>
				</p>
				<p class="floatl clear">
					<strong> Remarks <span class="error_message verticalalignTop">*</span></strong>
					<html:textarea property="remarksPojo.remarks" value="" name="qrcForm" styleClass="LmsRemarkstextarea" styleId="changeBillDateRemarks"></html:textarea>
					<span class="hidden"><font class="errorRemarks">Please enter remarks.</font></span>
					<font class="errorRemarks hidden">Please enter Remarks between [2-4000].</font>
				</p>
				<br class="clear" />
			</div>
			<div class="floatr inner_right">
				<p class="buttonPlacement">
					<a href="#" id="updateBillDate" class="main_button "><span>Submit</span></a> 
				</p>
			</div>
		</html:form>
		<br class="clear" />
	</div>
	<div class="margintop30">
	<iframe src='logAction.do?method=getBillCycleHistory&customerId=${ qrcForm.custDetailsPojo.customerId }&recordId=${qrcForm.custDetailsPojo.recordId}'scrolling="yes" frameborder="0"
    	style="border: none; overflow: hidden;width:100%; height: 200px;" allowTransparency="true">
    </iframe>
	</div>
</div>
<p class="clear"/>

