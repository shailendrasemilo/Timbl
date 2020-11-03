<%@taglib uri="/WEB-INF/tlds/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/tlds/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/tlds/displaytag.tld" prefix="display"%>
<%@taglib uri="/WEB-INF/tlds/struts-html.tld" prefix="html"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<head>
<link href="css/colorbox.css" rel="stylesheet" />
<script src="javascript/jquery.colorbox.js"></script>
<script>
	$( document ).ready( function(){
		$( ".iframe" ).colorbox( {
			iframe : true, width : "658px", height : "345px"
		} );
	} );
</script>
<style>
.input-edit {
	position: relative;
	z-index: 9999;
}
</style>
</head>
<div class="loadingPopup hidden"></div>
<div id="section">
	<div class="modelPopupDiv" id="uploadDocPPId"></div>
	<div class="section">
		<h2>
			Shifting Workflow&nbsp;-&nbsp;&nbsp;<a style="color: #DEAE00;"><bean:write name='qrcForm' property='customerId' /></a>
		</h2>
		<div class="inner_section ">
			<div class="inner_left_lead floatl  qrcLeft"></div>
			<div class="floatl manageGISRight  qrcRight">
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
				<h4 style="width: 750px">Current Installation Address</h4>
				<div class="relative inner_left_lead">
					<html:form action="/shiftingWorkflow">
						<html:hidden name="qrcForm" property="custDetailsPojo.crfId" value="${qrcForm.custDetailsPojo.crfId}" styleId="uploadDocCRF" />
						<html:hidden property="installationAddressPojo.recordId" name="qrcForm" styleId="instAddrRecordId" value="${ qrcForm.installationAddressPojo.recordId }" />
						<html:hidden property="installationAddressPojo.addressType" name="qrcForm" styleId="instAddrType" value="${ qrcForm.installationAddressPojo.addressType }" />
						<html:hidden property="shiftingWorkflowPojo.customerId" name="qrcForm" value="${ qrcForm.shiftingWorkflowPojo.customerId}" styleId="customerId" />
						<html:hidden property="shiftingWorkflowPojo.workflowId" name="qrcForm" value="${ qrcForm.shiftingWorkflowPojo.workflowId}" styleId="workflowId" />
						<html:hidden property="shiftingWorkflowPojo.shiftingType" name="qrcForm" value="${ qrcForm.shiftingWorkflowPojo.shiftingType}" styleId="shiftingType" />
						<html:hidden property="custDetailsPojo.product" name="qrcForm" value="${ qrcForm.custDetailsPojo.product}" styleId="hiddenProduct" />
						<html:hidden name='qrcForm' property='customerId' value="${qrcForm.customerId}" />
						<html:hidden name="qrcForm" property="shiftingWorkflowPojo.workflowId" />
						<html:hidden name="qrcForm" property="shiftingWorkflowPojo.shiftingId" />
						<html:hidden name="qrcForm" property="shiftingWorkflowPojo.workflowStage" />
						<html:hidden name="qrcForm" property="shiftingWorkflowPojo.currentBin" />
						<html:hidden name="qrcForm" property="shiftingWorkflowPojo.product" />
						<html:hidden name="qrcForm" property="shiftingWorkflowPojo.previousNetwork" />
						<html:hidden name='qrcForm' property='changeInstallationAddressPojo.recordId' value="${ qrcForm.installationAddressPojo.recordId}" />
						<div class="instAddrChange">
							<div class="INA floatl">
								<p class="floatl clear">
									<strong>Address Line 1</strong>
									<html:text property="installationAddressPojo.addLine1" name="qrcForm" styleClass="textbox" styleId="changinstAddLine1" maxlength="35"
										value="${qrcForm.installationAddressPojo.addLine1 }" onblur="this.value = this.value.toUpperCase();" readonly="true"></html:text>
									<font class="errorTextbox hidden">Please enter Address Line 1</font>
								</p>
								<p class="floatl marginleft30">
									<strong>Address Line 2</strong>
									<html:text property="installationAddressPojo.addLine2" name="qrcForm" styleClass="textbox" styleId="changinstAddLine2" maxlength="35"
										value="${ qrcForm.installationAddressPojo.addLine2 }" onblur="this.value = this.value.toUpperCase();" readonly="true"></html:text>
									<font class="errorTextbox hidden">Please enter Address Line 2 </font>
								</p>
								<p class="floatl marginleft30">
									<strong>Address Line 3</strong>
									<html:text property="installationAddressPojo.addLine3" name="qrcForm" styleClass="textbox" styleId="changinstAddLine3" readonly="true"
										value="${qrcForm.installationAddressPojo.addLine3}"></html:text>
									<font class="errorTextbox hidden">Please check Address Line 3.</font>
								</p>
								<p class="floatl clear ">
									<strong>PIN Code</strong> <span class="showTextbox floatl" style="width: 160px;"><bean:write name="qrcForm"
											property="installationAddressPojo.pincode" /></span>
								</p>
								<p class="floatl marginleft10">
									<strong>State</strong> <span class="showTextbox floatl" style="width: 160px;"><bean:write name="qrcForm"
											property="installationAddressPojo.stateName" /></span>
								</p>
								<p class="floatl marginleft10">
									<strong>City</strong> <span class="showTextbox floatl" style="width: 160px;"><bean:write name="qrcForm"
											property="installationAddressPojo.cityName" /></span>
								</p>
								<p class="floatl marginleft10">
									<strong>Area</strong> <span class="showTextbox floatl" style="width: 160px;"><bean:write name="qrcForm" property="instAddrArea" /></span>
								</p>
								<p class="floatl clear">
									<strong>Locality / Sector - Society</strong> <span class="showTextbox floatl"> <bean:write name="qrcForm" property="instAddrSociety" />
									</span>
								</p>
								<p class="floatl marginleft30">
									<strong>House Number</strong>
									<html:text styleClass="Lmstextboxuppercase" maxlength="10" readonly="true" property="installationAddressPojo.addLine1" styleId="houseNoLms" />
								</p>
								<p class="floatl marginleft30">
									<strong>Landmark</strong>
									<html:text styleClass="Lmstextboxuppercase" maxlength="25" readonly="true" property="installationAddressPojo.landmark" styleId="landmarkLms" />
								</p>
								<div class="clear"></div>
								<h4 style="padding-top: 20px">New Installation Address</h4>
								<p class="floatl clear">
									<strong>Address Line 1</strong>
									<html:text property="changeInstallationAddressPojo.addLine1" name="qrcForm" styleClass="textbox input-edit" styleId="instAddLine1" maxlength="35"
										onblur="this.value = this.value.toUpperCase();"></html:text>
									<font class="errorTextbox hidden">Please enter Address Line 1</font>
								</p>
								<p class="floatl marginleft30">
									<strong>Address Line 2</strong>
									<html:text property="changeInstallationAddressPojo.addLine2" name="qrcForm" styleClass="textbox input-edit" styleId="instAddLine2" maxlength="35"
										onblur="this.value = this.value.toUpperCase();"></html:text>
									<font class="errorTextbox hidden">Please enter Address Line 2 </font>
								</p>
								<p class="floatl marginleft30">
									<strong>Address Line 3</strong>
									<html:text property="changeInstallationAddressPojo.addLine3" name="qrcForm" styleClass="textbox" styleId="instAddLine3" readonly="true"></html:text>
									<font class="errorTextbox hidden">Please check Address Line 3.</font>
								</p>
								<p class="floatl clear ">
									<strong>PIN Code</strong>
									<html:text property="changeInstallationAddressPojo.pincode" name="qrcForm" styleClass="Lmstextbox" styleId="" maxlength="6" readonly="true"
										onkeyup="javascript:ValidatenReplaceAlphanumeric(this);" onclick="removeZeroFromNumber(this);" onfocus="removeZeroFromNumber(this);"></html:text>
									<font class="errorTextbox hidden">Please enter PIN Code</font>
								</p>
								<p class="floatl marginleft10">
									<strong>State</strong> <span class="showTextbox floatl" style="width: 160px;"> <bean:write name="qrcForm"
											property="changeInstallationAddressPojo.stateName" /></span> </span>
								</p>
								<p class="floatl marginleft10">
									<strong>City</strong> <span class=""> <span class="showTextbox floatl" style="width: 160px;"> <bean:write name="qrcForm"
												property="changeInstallationAddressPojo.cityName" /></span>
									</span>
								</p>
								<p class="floatl marginleft10">
									<strong>Area</strong> <span class=""> <span class="showTextbox floatl" style="width: 160px;"> <bean:write name="qrcForm"
												property="changeInstArea" /></span>
									</span>
								</p>
								<p class="floatl clear">
									<strong>Locality / Sector - Society</strong> <span class="showTextbox floatl" style="width: 160px;"> <bean:write name="qrcForm"
											property="changeInnstSociety" /></span> </span>
								</p>
								<p class="floatl marginleft30">
									<strong>House Number</strong>
									<html:text property="houseNumber" name="qrcForm" styleClass="textbox"  maxlength="10" 
										onblur="this.value = this.value.toUpperCase();" styleId="houseId"></html:text>
										<font class="errorTextbox hidden">Please enter house number.</font>
								</p>
								<p class="floatl marginleft30">
									<strong>Landmark</strong>

									<html:text styleClass="Lmstextboxuppercase" maxlength="25" property="changeInstallationAddressPojo.landmark" styleId="landmarkLms"
										onkeyup="javascript:changeToUpperCase(this)"  />

								</p>
								<div class="relative inner_left_lead clear">

									<br class="clear" />
								</div>



								<p class="floatl clear">
									<strong> Remarks<sup class="req">*</sup></strong>
									<html:textarea property="remarksPojo.remarks" name="qrcForm" styleClass="LmsRemarkstextarea" styleId="instAddrRemarks"></html:textarea>
									<span class="hidden"><font class="errorRemarks" style="top: 98px;">Please enter Remarks between [2-4000].</font></span> <font
										class="errorRemarks hidden" style="top: 98px;">Please enter Remarks.</font>

								</p>


							</div>
							<div class="floatr inner_right">
								<p class="clear"></p>
								<p class="buttonPlacement">
									<logic:equal name="crmRoles" property="available(create_qrc_shifting,update_qrc_shifting)" value="true" scope="session">
										<a href="#" id="submit_instAddrChange" class="main_button"> <span>Confirm</span></a>
									</logic:equal>
								</p>

							</div>
					</html:form>
					<br class="clear"> <br class="clear">
				</div>
			</div>
			<p class="clear"></p>
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