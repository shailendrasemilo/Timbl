<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="/WEB-INF/tlds/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/tlds/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/tlds/displaytag.tld" prefix="display"%>
<%@taglib uri="/WEB-INF/tlds/struts-html.tld" prefix="html"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<link href="css/colorbox.css" rel="stylesheet" />
<script src="javascript/jquery.colorbox.js"></script>
<script src="javascript/paymentReversal.js"
	type="text/javascript"></script>
	
	<link href="css/colorbox.css" rel="stylesheet" />
<script src="javascript/jquery.colorbox.js"></script>
<script>
	$( document ).ready(function(){
		$(".notRealized").colorbox({iframe:true, width:"750px", height:"350px"});
	});
</script>
	
</head>
<body>
<div class="loadingPopup hidden"></div>
	<div id="section">
		<form action="/paymentrefunded" id="IDRefundtTracking" method="post">
			<!--    <html:hidden name="financeForm" property="paymentId" styleId="PTpaymentId" />
      <html:hidden name="financeForm" property="searchPaymentListSize" styleId="PTsearchPaymentListSize" />
      <html:hidden name="financeForm" property="hiddenPaymentIdList" styleId="PThiddenPaymentIdList" />
      <html:hidden name="financeForm" property="changeDynamicVariable" styleId="PTchangeDynamicVariable" />
      <html:hidden name="financeForm" property="realizationVariable" styleId="PTreliazationVariable" />
      <html:hidden name="financeForm" property="bouncingDate" styleId="PTbouncingDate" />
      <html:hidden name="financeForm" property="bouncingReason" styleId="PTbouncingReason" />-->
			<html:hidden name="financeForm" property="modifiedBy"
				value="${sessionScope.userPojo.userId}" />
			<div class="section">
				<h2>Search Refund Payment Details</h2>
				<span class="success_message"> <logic:messagesPresent
						message="true" property="appMessage">
						<html:messages id="message" message="true" property="appMessage">
							<bean:write name="message" />
						</html:messages>
					</logic:messagesPresent>
				</span>
				<p class="error_message">
					<html:errors property="appError" />
					<html:errors property="message" />
				</p>
				<div class="inner_section">
					<div class="inner_left_lead  floatl marginleft10">

						<p class="floatl clear">

							<strong>Customer ID</strong>
							<html:text styleClass="Lmstextbox" name="financeForm"
								property="customerId" maxlength="15"></html:text>
								
							
						</p>
						

						<p class="floatl marginleft30">
							<strong>CAF Number</strong>
							<html:text styleClass="Lmstextbox" name="financeForm"
								property="crfId" maxlength="8"
								onkeyup="javascript:changeToUpperCase(this)"
								styleId="uploadDocCRF"></html:text>
						</p>
						<p class="floatl clear"></p>


						<p class="floatl clear">
							<strong>Payment Status</strong> <span
								class="LmsdropdownWithoutJs"> <html:select
									property="paymentStatus" name="financeForm">
									<html:option value="">All</html:option>
									<logic:notEmpty name="financeForm" property="paymentStatusList">
										<html:optionsCollection name="financeForm"
											property="paymentStatusList" label="contentName"
											value="contentValue" />
									</logic:notEmpty>
								</html:select>
							</span>
						</p>
						<p class="floatl marginleft30">
							<strong>Payment Mode</strong> <span class="LmsdropdownWithoutJs">
								<html:select property="payment_mode" name="financeForm">
									<html:option value="">All</html:option>
									<logic:notEmpty name="financeForm" property="paymentModeList">
										<html:optionsCollection name="financeForm"
											property="paymentModeList" label="contentName"
											value="contentValue" />
									</logic:notEmpty>
								</html:select>
							</span>
						</p>

						<p class="floatl clear">
							<strong>Entity Type</strong> <span class="LmsdropdownWithoutJs">
								<html:select property="entity_type" name="financeForm">
									<html:option value="">All</html:option>
									<logic:notEmpty name="financeForm" property="entityTypeList">
										<html:optionsCollection name="financeForm"
											property="entityTypeList" label="contentName"
											value="contentValue" />
									</logic:notEmpty>
								</html:select>
							</span>
						</p>

					</div>
					<div class="floatr inner_right">
						<a href="#" id="submit_Refund" class="main_button"><span>Search</span></a>
						<input type="hidden" id="refundId"
							value="${ financeForm.crmCustomerRefundsPojo.refundId }"
							name="refundId"> <input type="hidden" id="newStatus"
							value="${ financeForm.crmCustomerRefundsPojo.chequeStatus }"
							name="chequeStatus">
							<input type="hidden" id="customerId"
							value="${ financeForm.crmCustomerRefundsPojo.customerId }"
							name="customerID">
							 <html:hidden name="financeForm" property="bouncingReason" styleId="RbouncingReason" />
							

					</div>
					<p class="clear"></p>

				</div>
				<logic:notEmpty name="financeForm"
					property="crmCustomerRefundsPojos">


					<display:table id="data"
						name="sessionScope.financeForm.crmCustomerRefundsPojos"
						requestURI="" class="dataTable" style="width:100%" pagesize="15">
						<display:setProperty name="paging.banner.placement" value="bottom" />
						<display:setProperty name="mymedia" value="true"></display:setProperty>
						<display:setProperty name="export.excel.filename"
							value="Payment-Detail.xls" />
						<display:setProperty name="export.csv.filename"
							value="Payment-Detail.csv" />
						<display:column title="Sr.No.">
							<bean:write name="data_rowNum" />
						</display:column>
						<display:column title="Customer ID " sortable="false">
                        <html:link title="Ticket ID : ${data.ticketID}" href="javascript:void(0);">${data.customerId}</html:link>
						</display:column>
						<display:column title="Refund Mode " sortable="false">
							<bean:write name="crmRoles"
								property="displayEnum(PaymentMode,${ data.refundMode})"
								scope="session" />
						</display:column>
						<display:column title="Entity Type " sortable="false">
							<bean:write name="crmRoles"
								property="displayEnum(EntityType,${ data.entityType})"
								scope="session" />
						</display:column>
						
							<display:column title="Cheque Number/ Transaction ID " sortable="false">
								${data.chequeNumber}${not empty data.chequeNumber?not empty data.transactionId ?'/':'':''} ${data.transactionId}
								</display:column>
							<display:column title="Cheque Date " sortable="false">

								<bean:write name="crmRoles"
									property="toDate(${data.chequeDate})" scope="session" />
							</display:column>
							<display:column title="Cheque Status" style="width:10%;"
								sortable="false">
								<bean:write name="crmRoles"
									property="displayEnum(RealizationStatus,${data.chequeStatus})"
									scope="session" />
								<logic:equal name="data" property="chequeStatus" value="CLRAWT">
								<logic:equal name="crmRoles" property="available(update_finance)" value="true" scope="session">
									<br />
									<a
										href="javascript:changeRefundStatus('RL','<bean:write name="data" property="refundId"/>','<bean:write name="data" property="customerId"/>')">Realized</a>
									<br />
									
										
									  <a href= 'refund.do?method=changeNonRealizationStatusPage&cheStatus=NRL&custId=${data.customerId}&Id=${data.refundId}'class="notRealized">Not Realized	</a>
										
										</logic:equal>

								</logic:equal>
							</display:column>
						
						<display:column title="Payer Bank Name " sortable="false"
							>
							<logic:empty name="data" property="payerBankName">-
							</logic:empty>
							<logic:notEmpty name="data" property="payerBankName">
							 <bean:write name="crmRoles" property="displayEnum(BANK,${ data.payerBankName})" scope="session" />
							 </logic:notEmpty>
							</display:column>
						<display:column title="Account Number " sortable="false"
							>
								${ empty data.custAccountNumber? '-' : data.custAccountNumber}
								</display:column>
						<display:column title="Customer Bank Name " sortable="false">
						 <logic:empty name="data" property="custBankName">-
						 </logic:empty>
						     <logic:notEmpty name="data" property="custBankName">
							 <bean:write name="crmRoles" property="displayEnum(BANK,${ data.custBankName})" scope="session" />
							 </logic:notEmpty>
								</display:column>
							
							<display:column title="IFSC Code" sortable="false">
							
							${ empty data.ifscCode? '-' : data.ifscCode}
								</display:column>
						<display:column title="Refund Amount " sortable="false"
							property="refundAmount" />
						<display:column title="Refund Date  " sortable="false">
							<bean:write name="crmRoles" property="toDate(${data.refundDate})"
								scope="session" />
						</display:column>
						<display:column title="Status  " sortable="false">
							<bean:write name="crmRoles"
								property="displayEnum(AllStatus,${ data.status})"
								scope="session" />
						</display:column>
						<%--<display:column title="Edit  " sortable="false">
							<c:if test="${ data.refundMode ne'O' and data.status eq 'RI'}">
							<a href="javascript:getRefundDetail('<bean:write name="data" property="refundId"/>','<bean:write name="data" property="customerId"/>')" class="">
										Edit</a>
							</c:if>
							</display:column>--%>

					</display:table>

				</logic:notEmpty>


				<logic:empty name="financeForm" property="crmCustomerRefundsPojos">
					<b> <html:errors property="noRecordFound" />
					</b>
				</logic:empty>
			</div>




		</form>


	</div>
</body>
</html>
