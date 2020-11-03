<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="/WEB-INF/tlds/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/tlds/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/tlds/struts-html.tld" prefix="html"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
</head>
<body>
	<div id="section" align="center">
		<html:form action="/paymentTracking" method="post"
			styleId="paymentGatewaysConfigId">
			<div class="section">
				<h2>Configure Payment Gateways</h2>
				<div class="success_message">
					<logic:messagesPresent message="true">
						<html:messages id="message" message="true">
							<bean:write name="message" />
						</html:messages>
					</logic:messagesPresent>
				</div>
				<div class="error_message">
					<html:errors property="appError" />
				</div>
				<div class="inner_section">
					<div class="inner_left_lead floatl">
												
						<logic:notEmpty name="financeForm" property="paymentGateways">
							<logic:iterate id="paymentGateways" name="financeForm" property="paymentGateways" indexId="ctr">								
								<html:hidden name="paymentGateways" property="categoryId" value="${paymentGateways.categoryId}" indexed="true"/>
									<logic:equal name="paymentGateways" property="subSubCategory" value="DC">
										<p class="clear floatl marginleft15">
											<strong>Debit Card </strong>
										</p>								
									</logic:equal>
									<logic:equal name="paymentGateways" property="subSubCategory" value="CC">
										<p class="clear floatl marginleft15">
											<strong>Credit Card </strong>
										</p>										
									</logic:equal>
									<logic:equal name="paymentGateways" property="subSubCategory" value="IB">
										<p class="clear floatl marginleft15">
											<strong>Internet Banking </strong>
										</p>										
									</logic:equal>
									<p class="floatl marginleft15">
										<span class="LmsdropdownWithoutJs">
										<html:select name="paymentGateways" property="categoryValue" indexed="true">
											<html:option value="-1">Inactive</html:option>
											<html:option value="HDFC">HDFC</html:option>
											<html:option value="ATOM">ATOM</html:option>
											<html:option value="TP">TechProcess</html:option>
										</html:select>
										</span>
									</p>
									<logic:empty name="paymentGateways" property="createdBy">
										<html:hidden name="paymentGateways" property="createdBy" value="${sessionScope.userPojo.userId}" indexed="true"/>
									</logic:empty>
									<logic:notEmpty name="paymentGateways" property="createdBy">
										<html:hidden name="paymentGateways" property="lastModifiedBy" value="${sessionScope.userPojo.userId}" indexed="true"/>
									</logic:notEmpty>
							</logic:iterate>
						</logic:notEmpty>
						
						<b class="space" style="height: 120px">&nbsp;</b>
					</div>
					<br class="clear" /> <br class="clear" />
					<logic:equal name="crmRoles" property="available(update_finance,delete_finance,recover_finance)" value="true" scope="session">
						<div class="floatr inner_right">
							<html:link href="#" styleClass="main_button"
								styleId="submit_paymentGatewayConfig">Submit</html:link>
						</div>
					</logic:equal>
				</div>
				<br class="clear" />
			</div>
		</html:form>
	</div>
</body>
</html>