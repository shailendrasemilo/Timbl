<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page import="org.apache.struts.Globals"%>
<%@ page import="org.apache.struts.taglib.html.Constants"%>
<%@taglib uri="/WEB-INF/tlds/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/tlds/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/tlds/displaytag.tld" prefix="display"%>
<%@taglib uri="/WEB-INF/tlds/struts-html.tld" prefix="html"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<script>
	function showReason( str ){
		if ( str == 'N' ) {
			document.getElementById( 'csdCloserReason' ).style.display = "block";
			document.getElementById( 'planTypeShiftingID' ).style.display = "none";
			document.getElementById( 'basePlanCodeID' ).style.display = "none";
			document.getElementById( 'AddonID' ).style.display = "none";
			document.getElementById( 'Other_ID' ).style.display = "none";

		}
		else {
			document.getElementById( "csdCloserReason" ).style.display = "none";
			document.getElementById( 'planTypeShiftingID' ).style.display = "block";
			document.getElementById( 'basePlanCodeID' ).style.display = "block";
			document.getElementById( 'AddonID' ).style.display = "block";
			document.getElementById( 'Other_ID' ).style.display = "block";

		}
	}
</script>
<div class="loadingPopup hidden"></div>
<div id="section">
	<div class="modelPopupDiv" id="uploadDocPPId"></div>
	<div class="section">
		<h2>
			Shifting Workflow&nbsp;-&nbsp;&nbsp;<a style="color: #DEAE00;"><bean:write name='qrcForm' property='customerId' /></a>
		</h2>
		<div class="inner_section ">
			<div class="inner_left_lead floatl  qrcLeft"></div>
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
				<h4 style="width: 615px">CSD Level 2</h4>
				<div class="relative inner_left_lead">
					<html:form action="/shiftingWorkflow" method="post" styleId="csdOutcallStage">
						<html:hidden name='qrcForm' property='customerId' value="${qrcForm.customerId}" />
						<html:hidden name="qrcForm" property="shiftingWorkflowPojo.workflowId" value="${qrcForm.shiftingWorkflowPojo.workflowId}"/>
						<html:hidden name="qrcForm" property="custDetailsPojo.serviceType" value="${qrcForm.custDetailsPojo.serviceType}" styleId="serviceType_CSDL2" />
						<html:hidden name="qrcForm" property="custDetailsPojo.product" value="${qrcForm.custDetailsPojo.product}"  />
						<html:hidden name="qrcForm" property="shiftingWorkflowPojo.product" value="${qrcForm.shiftingWorkflowPojo.product}" styleId="product_CSDL2" />
						<html:hidden property="planService" name="qrcForm" value="${qrcForm.planService}" styleId="hiddenPlanServiceId" />
						<div class="floatl">
							<p class=" floatl clear">
								<b>Customer Response</b>
								<html:radio property="customerResponse" value="Y" onclick="showReason(this.value);" />
								Yes
								<html:radio property="customerResponse" value="N" onclick="showReason(this.value);" />
								No
								<label for="customerResponse" class="error" style="display:none;">Please provide 'Customer Response'.</label>
							</p>
							<p class=" floatl clear" style='display: none' id="csdCloserReason">
								<b>Closer Reason</b> <span class="LmsdropdownWithoutJs"> <html:select property="shiftingWorkflowPojo.closerReason" styleId="closer_reason_Id">
										<html:option value="">Please Select</html:option>
										<logic:notEmpty name="qrcForm" property="closerReasonList">
											<html:optionsCollection name="qrcForm" property="closerReasonList" label="categoryValue" value="categoryId" />
										</logic:notEmpty>
									</html:select> <font></font>
								</span>
							</p>
							<p class="floatl clear" style='display: none' id="planTypeShiftingID">
								<strong> Plan Type </strong> <LABEL class="label_radio"> <html:radio property="planTypeShifting" value="DEMO" name="qrcForm"></html:radio>DEMO
								</LABEL> <LABEL class="label_radio"> <html:radio property="planTypeShifting" value="TnB" name="qrcForm"></html:radio>TnB
								</LABEL> <LABEL class="label_radio"> <html:radio property="planTypeShifting" value="APP" name="qrcForm"></html:radio>APP
								</LABEL> <LABEL class="label_radio"> <html:radio property="planTypeShifting" value="PAID" name="qrcForm"></html:radio>PAID
								</LABEL> <LABEL class="label_radio"> <html:radio property="planTypeShifting" value="MBO" name="qrcForm"></html:radio>MBO
								</LABEL> <LABEL class="label_radio"> <html:radio property="planTypeShifting" value="FOC" name="qrcForm"></html:radio>FOC
								</LABEL>

							</p><font style="top: 107px;"></font>
							<p class="floatl clear" style='display: none' id="basePlanCodeID">
								<strong>Base Plan Name</strong> <span class="dropdownWithoutJs"> <logic:notEmpty property="planTypeShifting" name="qrcForm">
										<html:select property="planDetailsPojo.basePlanCode" name="qrcForm" styleId="basePlanNameCRF"
											onchange="setPlanAmounts('securityChrgeId', 'rentalChargeId','amountId', 'total', this.value);">
											<html:option value="0">Select Base Plan Name</html:option>
											<html:optionsCollection name="qrcForm" property="basePlanNames" label="planName" value="planCode" />
										</html:select>
									</logic:notEmpty> <logic:empty property="planTypeShifting" name="qrcForm">
										<html:select property="planDetailsPojo.basePlanCode" name="qrcForm" styleId="basePlanNameCRF"
											onchange="setPlanAmounts('securityChrgeId', 'rentalChargeId','amountId', 'total', this.value);">
											<html:option value="0">Select Base Plan Name</html:option>
										</html:select>
									</logic:empty>
								</span>
							</p><font style="top: 171px;"></font>
							<p class="floatl marginleft30" style='display: none' id="AddonID">
								<strong>Add-on DUL Value</strong> <span class="dropdownWithoutJs"> <html:select property="planDetailsPojo.addOnPlanCode" name="qrcForm"
										styleId="addOnDulValueCRF" onchange="addToPlanAmounts('securityChrgeId', 'rentalChargeId','total', this.value);" disabled="${qrcForm.addOnNotAllowed}"
										styleClass="${qrcForm.addOnNotAllowed eq true? 'gray_text':''}">
										<html:option value="">Select Add-on DUL Value</html:option>
										<logic:notEmpty property="addonPlanNames" name="qrcForm">
											<html:optionsCollection name="qrcForm" property="addonPlanNames" label="planName" value="planCode" />
										</logic:notEmpty>
									</html:select>
								</span>
							</p>

							<div id="Other_ID" style='display: none'>
								<c:if test="${qrcForm.custDetailsPojo.product eq 'EOC' and qrcForm.custDetailsPojo.serviceType eq 'PR' }">
									<p class="floatl marginright15">
										<strong> Security Deposit</strong>
										<html:text styleClass="Lmstextbox" property="teleservicesPayment.securityCharges" name="qrcForm" maxlength="11" styleId="securityChrgeId"
											onkeyup="javascript:ValidatenReplaceAlphanumericnDot(this);sum();" onclick="removeZeroFromNumber(this);" onfocus="removeZeroFromNumber(this);"></html:text>
										<font class="errorTextbox"></font>
									</p>
								</c:if>
								<p class="floatl">
									<strong>${qrcForm.custDetailsPojo.serviceType eq 'PR' ? 'Rental Charge' : 'Advance Payment' } </strong>
									<html:text property="teleservicesPayment.activationCharges" name="qrcForm" styleClass="Lmstextbox" maxlength="11" styleId="rentalChargeId"
										onkeyup="javascript:ValidatenReplaceAlphanumericnDot(this);sum();" onclick="removeZeroFromNumber(this);" onfocus="removeZeroFromNumber(this);"></html:text>
									<font class="errorTextbox"></font>
								</p>
								<c:if test="${qrcForm.custDetailsPojo.product eq 'EOC' and qrcForm.custDetailsPojo.serviceType eq 'PR' }">
									<p class="floatl marginleft15">
										<strong>Total</strong> <input type="text" class="Lmstextbox" id="total" name="total" readonly="readonly" />
										<script type="text/javascript">
											sum();
										</script>
									</p>
								</c:if>
							</div>

							<p class="floatl clear">
								<strong> Remarks <span class="error_message verticalalignTop">*</span></strong>
								<html:textarea property="remarksPojo.remarks" value="" name="qrcForm" styleClass="LmsRemarkstextarea" styleId="csdRemarks">
								</html:textarea>
								<font></font> <span class="hidden"><font class="errorRemarks" style="top: 98px;">Please enter Remarks between [3-4000].</font></span> <font
									class="errorRemarks hidden" style="top: 98px;">Please enter Remarks.</font>
							</p>
							<br class="clear" />
						</div>
						<div class="floatr inner_right">
							<p class="buttonPlacement">
								<a href="#" id="submit_csdStage" class="main_button"><span>Submit</span></a>
							</p>
							<br class="clear" /> <br class="clear" />
						</div>
						<br class="clear" />
						<br class="clear" />
					</html:form>
					<br class="clear" />
				</div>
			</div>
			<p class="clear" />
			<div class="LmsTable marginRight10">
				<h4>
					Remarks Details <span class="lmsMinusImage floatr"></span>
				</h4>
				<div class="viewLmsTable margintop10 viewLmsLeadTable">
					<iframe src="logAction.do?method=getRemarks&mappingId=${qrcForm.shiftingWorkflowPojo.workflowId}" scrolling="no" frameborder="0" id="frame"
						style="border: none; overflow: hidden; width: 100%; height: 500px;" allowTransparency="true"> </iframe>
				</div>
			</div>
			<br class="clear" />
		</div>
	</div>
</div>