<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@page import="com.np.tele.crm.utils.StringUtils"%>
<%@page import="com.np.tele.crm.service.client.CrmCustMyAccountPojo"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<%@taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<div style="margin-top: 0px; border-radius: 0px;">
	<div class="col-lg-12 col-sm-12 col-md-12" style="padding: 0;">
		<div class="payable">
			<h1 class="payment-heading">Make Payment</h1>
			<h2 class="payment-option">Confirmation</h2>
			<p class="payment-option-hint">Please confirm your details</p>
		</div>
		<div class="pInfo hide"></div>
		<logic:messagesPresent property="appError">
			<bean:define id="hasErrors" value="true"></bean:define>
		</logic:messagesPresent>
		<div class="msgerror ${ hasErrors ? '' : 'hide' }">
			<html:errors />
		</div>
		<logic:messagesPresent message="true">
			<html:messages id="message" message="true">
				<div class="msgsuccess">
					<bean:write name="message" />
				</div>
			</html:messages>
		</logic:messagesPresent>
		<div class="payment-box">
			<html:form action="/quickPay" styleId="scQuickPayConfirmForm" target="_parent">
				<div class="detail-table">
					<div class="row">
						<div class="col">Customer ID</div>
						<div class="col">${quickPayForm.customerDetailsPojo.customerId}</div>
					</div>
					<div class="row">
						<div class="col">Customer Name</div>
						<div class="col">${quickPayForm.customerDetailsPojo.custFname}&nbsp;${quickPayForm.customerDetailsPojo.custLname}</div>
					</div>
					<div class="row">
						<div class="col">RMN</div>
						<div class="col">${quickPayForm.maskedRmn}</div>
					</div>
					<div class="row">
						<div class="col">Email ID</div>
						<div class="col">${quickPayForm.maskedEmailId}</div>
					</div>
					<div class="row">
						<div class="col">Pay Amount (Rs.)</div>
						<div class="col">${quickPayForm.paymentGatewayPojo.amount}</div>
					</div>
				</div>
				<div class="form-registration" style="margin-top: 0px;">
					<input type="button" value="&nbsp; Back &nbsp;" id="scQuickPayConfirmBack" class="btn submit_contact" /> <input type="button"
						value="&nbsp; Proceed &nbsp;" id="scQuickPayConfirm" class="btn submit_contact" />
				</div>
				<html:hidden name="quickPayForm" property="paymentGatewayPojo.paymentOption" value="CC" />
				<html:hidden name="quickPayForm" property="customerDetailsPojo.customerId" value="${quickPayForm.customerDetailsPojo.customerId}"/>
				<html:hidden name="quickPayForm" property="customerDetailsPojo.custFname"  value="${quickPayForm.customerDetailsPojo.custFname}"/>
				<html:hidden name="quickPayForm" property="customerDetailsPojo.custLname" value="${quickPayForm.customerDetailsPojo.custLname}"/>
				<html:hidden name="quickPayForm" property="maskedRmn" value="${quickPayForm.customerDetailsPojo.rmn}"/>
				<html:hidden name="quickPayForm" property="maskedEmailId" value="${quickPayForm.customerDetailsPojo.custEmailId}"/>
				<html:hidden name="quickPayForm" property="paymentGatewayPojo.amount" value="${quickPayForm.paymentGatewayPojo.amount}"/>
				<html:hidden name="quickPayForm" property="paymentGatewayPojo.pgwTrackId" value="${quickPayForm.paymentGatewayPojo.pgwTrackId}"/>
				<html:hidden name="quickPayForm" property="paymentGatewayPojo.status" value="${quickPayForm.paymentGatewayPojo.status}"/>
				<html:hidden name="quickPayForm" property="appReturnUrl" value="${quickPayForm.paymentGatewayPojo.appReturnUrl}"/>
				
			</html:form>
		</div>
	</div>
</div>