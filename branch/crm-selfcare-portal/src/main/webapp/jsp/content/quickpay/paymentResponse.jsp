<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@page import="com.np.tele.crm.utils.StringUtils"%>
<%@page import="com.np.tele.crm.service.client.CrmCustMyAccountPojo"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<%@taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<link href="css/color-${initParam.client}.css" type="text/css" rel="stylesheet" />
<div style="margin-top: 0px; border-radius: 0px;">
	<div class="col-lg-12 col-sm-12 col-md-12" style="padding: 0;">
		<div class="payable">
			<h1 class="payment-heading">Make Payment</h1>
			<h2 class="payment-option">Transaction Status</h2>
			<p class="payment-option-hint">
				<c:choose>
					<c:when test="${(quickPayForm.paymentGatewayPojo.status) eq 'S' }">
						Your transaction was successful! An electronic receipt has also been sent to your Timbl account email id
					</c:when>
					<c:otherwise>
						Your transaction was failed. Due to <span style="color: red;">${empty quickPayForm.paymentGatewayPojo.pgwErrorMsg ? '' :
							quickPayForm.paymentGatewayPojo.pgwErrorMsg}</span>
					</c:otherwise>
				</c:choose>
			</p>
		</div>
		<div class="payment-box">
			<html:form action="/quickPay" styleId="scQuickPayResponseForm">
				<div class="detail-table">
					<div class="row">
						<div class="col">Receipt No.</div>
						<div class="col">
							<c:if test="${(quickPayForm.paymentGatewayPojo.status) eq 'S' }">
                               ${quickPayForm.paymentGatewayPojo.pgwTrackId}
                                </c:if>
						</div>
					</div>
					<div class="row">
						<div class="col">Customer Name</div>
						<div class="col">${quickPayForm.customerDetailsPojo.custFname}&nbsp;${quickPayForm.customerDetailsPojo.custLname}</div>
					</div>
					<div class="row">
						<div class="col">Customer ID / CAF No.</div>
						<div class="col">${quickPayForm.customerDetailsPojo.customerId}</div>
					</div>
					<div class="row">
						<div class="col">Transaction Reference Number</div>
						<div class="col">${quickPayForm.paymentGatewayPojo.pgwTransactionId}</div>
					</div>
					<div class="row">
						<div class="col">Payment Date and Time</div>
						<div class="col">
							<bean:write name="selfcareUtils" property="xmlDate(${quickPayForm.paymentGatewayPojo.pgwResponseDatetime})" />
						</div>
					</div>
					<div class="row">
						<div class="col">Amount Paid (Rs.)</div>
						<div class="col">${quickPayForm.paymentGatewayPojo.amount}</div>
					</div>
				</div>
			</html:form>
		</div>
	</div>
</div>