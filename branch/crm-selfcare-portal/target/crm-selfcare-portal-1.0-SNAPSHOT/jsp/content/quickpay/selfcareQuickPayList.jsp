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
		<div class="payment-detail-table">
			<span class="payment-option-hint">You have multiple accounts. Please select one. </span>
			<div class="bodyText" style="margin-top: 20px;">
				<table id="RegistrationBlock" border="0" cellpadding="3" cellspacing="0" width="99%" class="payment-detail-table-txt">
					<html:form action="/quickPay" styleId="scQuickPayListForm">
						<div class="floatl marginleft15">
							<span class="dropdownforQcp"> <html:select property="customerDetailsPojo.customerId" name="quickPayForm" styleId="qcpCustId">
									<html:option value="">Select Customer ID</html:option>
									<logic:notEmpty name="quickPayForm" property="customerDetailsPojos">
										<html:optionsCollection name="quickPayForm" property="customerDetailsPojos" label="customerId" value="customerId" />
									</logic:notEmpty>
								</html:select>
							</span>
						</div>
						<div class="pInfo hide"></div>
					</html:form>
				</table>
				<div class="row">
					<div class="col">
						<input type="button" value="&nbsp; Back &nbsp;" id="scQuickPayTransactionBack" class="btn btn-broows submit_contact" style="margin-top: 15px" /> <input
							type="button" value="&nbsp; Proceed &nbsp;" class="btn btn-broows submit_contact" style="margin-top: 15px" onclick="addCustomerId();" />
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
