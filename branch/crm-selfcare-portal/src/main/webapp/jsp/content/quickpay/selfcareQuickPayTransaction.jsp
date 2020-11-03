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
			<h2 class="payment-option">Account Details</h2>
			<p class="payment-option-hint">Please fill in the amount you wish to pay</p>
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
			<html:form action="/quickPay" styleId="scQuickPayTransactionForm">
			 <html:hidden property="customerDetailsPojo.customerId" name="quickPayForm" value="${quickPayForm.customerDetailsPojo.customerId}"/>
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
						<div class="col">Account Balance</div>
						<div class="col">Rs. <fmt:formatNumber value="${quickPayForm.accountBalance}" minFractionDigits="0" maxFractionDigits="2"/></div>
					</div>
					<div class="row">
						<div class="col">Plan/ Bill Amount</div>
						<div class="col">Rs. <fmt:formatNumber value="${quickPayForm.planBillAmount}" minFractionDigits="0" maxFractionDigits="2"/></div>
					</div>
					<div class="row">
						<div class="col">Amount Due</div>
						<div class="col">Rs. <fmt:formatNumber value="${quickPayForm.amountDue}" minFractionDigits="0" maxFractionDigits="2"/></div>
					</div>
					<div class="row">
						<div class="col">Due by</div>
						<div class="col">
							<c:if test="${not(empty quickPayForm) and not(empty quickPayForm.invoicePojo.dueDate)}">
								<bean:write name="selfcareUtils" property="xmlDate(${quickPayForm.invoicePojo.dueDate})" />
							</c:if>
							<c:if test="${(empty quickPayForm.invoicePojo.dueDate)}">NA</c:if>
						</div>
					</div>
					<div class="row">
						<div class="col">
							<span class="input-lable">Pay Amount</span>
						</div>
						<div class="col">
							<input class="inp-inp" name="paymentGatewayPojo.amount" id="scAmount" maxlength="9" type="text"
								value="<fmt:formatNumber value="${quickPayForm.amountDue + 0.49}" minFractionDigits="0" maxFractionDigits="0" groupingUsed="false"/>">
						</div>
					</div>
					<div class="row">
						<div class="col">&nbsp;</div>
						<div class="col">
							<c:if test="${sessionScope.CrmCustMyAccountPojo == null }">
								<input type="button" value="&nbsp; Back &nbsp;" id="scQuickPayTransactionBack" class="btn btn-broows submit_contact" />
							</c:if>
							<input type="button" value="&nbsp; Pay Now &nbsp;" id="scQuickPayTransaction" class="btn btn-broows submit_contact" />
						</div>
					</div>
				</div>
			</html:form>
		</div>
	</div>
</div>
