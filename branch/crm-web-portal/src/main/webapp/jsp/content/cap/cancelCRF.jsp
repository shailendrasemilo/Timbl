<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib uri="/WEB-INF/tlds/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/tlds/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/tlds/displaytag.tld" prefix="display"%>
<%@taglib uri="/WEB-INF/tlds/struts-html.tld" prefix="html"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<body>
	<div id="section">
		<html:form action="/crmCap" method="post" styleId="cancleCRF">
			<html:hidden property="crfTabId" name="crmCapForm" value="${crmCapForm.crfTabId}" styleId="hiddenCrfId" />
			<html:hidden property="customerDetailsPojo.recordId" name="crmCapForm" value="${crmCapForm.customerDetailsPojo.recordId}" />
			<html:hidden property="customerDetailsPojo.customerId" name="crmCapForm" value="${crmCapForm.customerDetailsPojo.customerId}" />
			<div class="section">
				<h2>Cancel CAF</h2>
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
				<div class="inner_section">
					<div class="INA floatl">

						<p class="floatl">
							<strong>Cancel Reason</strong> <span class="LmsdropdownWithoutJs"> <html:select name="crmCapForm" property="remarksPojo.reasonId"
									styleId="crfCancelReason">
									<option value="">Please Select</option>
									<logic:notEmpty name="crmCapForm" property="cancellationReasonList">
										<html:optionsCollection name="crmCapForm" property="cancellationReasonList" label="categoryValue" value="categoryId" />
									</logic:notEmpty>
								</html:select> <font></font>
							</span>
						</p>
						<c:if test="${requestScope.APPMSG ne 'CRM001' }">
							<p class="floatl clear">
								<strong> Link To CAF</strong><input type="checkbox" id="linkToOtherCRF" onchange="showHideLinkToCRF('linkToOtherCRF')" />

							</p>
							<p class="floatl">
								<strong>&nbsp;&nbsp;&nbsp;</strong>
							</p>
							<p class="floatl" id="linkToOtherCRFText" style="display: none;">
								<strong> CAF Number </strong>
								<html:text name="crmCapForm" property="customerDetailsPojo.linkedCRF" styleClass="Lmstextbox" styleId="linkageCRF" maxlength="8"
									onkeyup="javascript:crfValidation();" onblur="checkValidCRF(this.value)" />
								<font></font>
							</p>
						</c:if>
						<p class="floatl clear">
							<strong>Remarks</strong>
							<html:textarea property="remarksPojo.remarks" name="crmCapForm" styleClass="LmsRemarkstextarea" styleId="remarks"></html:textarea>
							<font class="errorTextbox" style="top: 100px; width: 200px;"></font>
						</p>

					</div>

					<div class="floatr inner_right" id="submitCancelCRFDiv">
						<a href="javascript:void(0)" id="submitCancelCRF" class="main_button" onclick="javascript:submitCancelCRF()"><span>Submit</span></a>
					</div>

					<br class="clear" />
				</div>
			</div>
		</html:form>
	</div>
</body>
</html>
