<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page import="org.apache.struts.Globals"%>
<%@ page import="org.apache.struts.taglib.html.Constants"%>
<%@taglib uri="/WEB-INF/tlds/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/tlds/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/tlds/displaytag.tld" prefix="display"%>
<%@taglib uri="/WEB-INF/tlds/struts-html.tld" prefix="html"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<style>
.ui-autocomplete {
	max-height: 150px;
	overflow-y: auto;
}
</style>
<script>
	function showReason( str ){
		if ( str == 'N' ) {
			document.getElementById( 'csdCloserReason' ).style.display = "block";
		}
		else {
			document.getElementById( "csdCloserReason" ).style.display = "none";
		}
	}
</script>
<script type="text/javascript" src="javascript/jquery-ui.js"></script>
<link rel="stylesheet" href="css/jquery-ui.css" />
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
				<h4 style="width: 620px">
					NOC LEVEL1 &nbsp;-&nbsp;&nbsp;<a style="color: #DEAE00;"> <logic:equal value="EOC" name="qrcForm" property="shiftingWorkflowPojo.product"> FTTN </logic:equal>
						<logic:equal value="BB" name="qrcForm" property="shiftingWorkflowPojo.product"> FTTX </logic:equal></a>
				</h4>
				<div class="relative inner_left_lead">
					<html:form action="/shiftingWorkflow" method="post" styleId="ifrStage">
						<div class="floatl">
							<html:hidden name="qrcForm" property="shiftingWorkflowPojo.workflowId" value="${qrcForm.shiftingWorkflowPojo.workflowId}" styleId="workflowId" />
							<html:hidden name="qrcForm" property="shiftingWorkflowPojo.workflowStage" value="${qrcForm.shiftingWorkflowPojo.workflowStage}" styleId="workflowStage" />
							<html:hidden name="qrcForm" property="shiftingWorkflowPojo.customerId" value="${qrcForm.shiftingWorkflowPojo.customerId}" styleId="customerIds" />
							<html:hidden name="qrcForm" property="customerId" value="${qrcForm.shiftingWorkflowPojo.customerId}" styleId="customerId" />
							<html:hidden name="qrcForm" property="shiftingWorkflowPojo.shiftingType" value="${qrcForm.shiftingWorkflowPojo.shiftingType}" styleId="shiftingType" />
							<html:hidden name="qrcForm" property="networkConfigurationsPojo.serviceModel" value="${qrcForm.networkConfigurationsPojo.serviceModel}"
								styleId="IDQRChiddenOntRgMduDone" />
							<html:hidden name="qrcForm" property="networkConfigurationsPojo.recordId" value="${qrcForm.networkConfigurationsPojo.recordId}"
								styleId="IDQRChiddenNetworkRecordId" />
							<html:hidden name="qrcForm" property="newOption82" styleId="newOption82EOC" value="${qrcForm.shiftingWorkflowPojo.option82}" />
							<html:hidden name="qrcForm" property="networkConfigurationsPojo.option82" styleId="oldOption82EOC" />
							<html:hidden name='qrcForm' property='customerId' value="${qrcForm.customerId}" />
							<html:hidden name="qrcForm" property="shiftingWorkflowPojo.workflowId" />
							<html:hidden name="qrcForm" property="shiftingWorkflowPojo.shiftingId" />
							<html:hidden name="qrcForm" property="shiftingWorkflowPojo.currentBin" />
							<html:hidden name="qrcForm" property="shiftingWorkflowPojo.product" styleId="product"/>
							<html:hidden name="qrcForm" property="shiftingWorkflowPojo.previousNetwork" />

							<logic:equal value="BB" name="qrcForm" property="shiftingWorkflowPojo.product">
								<p class="floatl clear">
									<span> <strong> Old Option82 </strong> <html:text name="qrcForm" property="networkConfigurationsPojo.option82" styleClass="textbox gray_text"
											readonly="true" styleId="IDqrcNewOption82OId" />
									</span>
								</p>
								<p class="floatl marginleft30">
									<span> <strong> Option82 </strong> <html:text name="qrcForm" property="shiftingWorkflowPojo.option82" styleClass="textbox gray_text"
											readonly="true" styleId="IDqrcNewOption82" /> <font></font> <html:hidden name="qrcForm" property="custDetailsPojo.product" styleId=""
											value="${qrcForm.shiftingWorkflowPojo.product}"></html:hidden><font></font>
									</span>
								</p>

								<p class="floatl clear ">
									<span> <strong>Old MAC Address </strong> <html:text name="qrcForm" property="networkConfigurationsPojo.currentCpeMacId"
											styleClass="textbox gray_text" readonly="true" styleId="IDqrcOldPriMacIdBB" />
									</span>
								</p>
								<%-- <p class="floatl marginleft30 ">
									<span> <strong> MAC Address </strong> <html:text name="qrcForm" property="shiftingWorkflowPojo.currentCPeMacId" styleClass="textbox gray_text"
											readonly="true" styleId="IDqrcNewPriMacIdBB" />
									</span>
								</p>--%>
								<p class="floatl marginleft30 ">
									<span class=""><strong>Mac Address</strong></span>

									<html:text styleClass="Lmstextbox" name="qrcForm" property="shiftingWorkflowPojo.currentCPeMacId" maxlength="14" styleId="IDqrcNewPriMacIdBB"
										onkeypress='javascript:autoFormatMacID(event,this);'></html:text>
									<font style="top: 53px;"></font>
								</p>
								<c:if
									test="${qrcForm.shiftingWorkflowPojo.npId ne qrcForm.shiftingWorkflowPojo.previousNpId && qrcForm.shiftingWorkflowPojo.product eq 'BB' &&  qrcForm.shiftingWorkflowPojo.product eq qrcForm.shiftingWorkflowPojo.previousNetwork} ">
									<span class=""><strong>CPE Available</strong></span>
									<span class=""> <html:radio property="shiftingWorkflowPojo.cpeAvailable" value="Y" /> Yes <html:radio
											property="shiftingWorkflowPojo.cpeAvailable" value="N" onclick="" />No
									</span>
								</c:if>
							</logic:equal>
							<logic:equal value="EOC" name="qrcForm" property="shiftingWorkflowPojo.product">
								<span id="errorNasport" class="errorTextbox" style="position: static; width: 217px"> </span>
								<font></font>
								<span>
									<p class="floatl clear">
										<strong> Old MASTER Name </strong>
										<html:text name="qrcForm" property="crmPartnerNetworkConfig.masterName" readonly="true" styleClass="textbox gray_text" />
								</span>
								</p>
								<p class="floatl marginleft30 ">
									<span> <strong> Old NAS-Port ID </strong> <html:text name="qrcForm" property="crmPartnerNetworkConfig.nasPortId" readonly="true"
											styleClass="textbox gray_text" />
									</span>
								</p>
								<p class="floatl marginleft30 ">
									<span> <strong> Old PoolName </strong> <html:text name="qrcForm" property="crmPartnerNetworkConfig.poolName" readonly="true"
											styleClass="textbox gray_text" />
									</span>
								</p>
								<p class="floatl clear">
									<strong> MASTER Name </strong>
									<html:text name="qrcForm" property="newcrmPartnerNetworkConfig.masterName" readonly="true" styleClass="textbox gray_text" />
									</span>
								</p>
								<p class="floatl marginleft30 ">
									<span> <strong> NAS-Port ID</strong> <html:text name="qrcForm" property="newcrmPartnerNetworkConfig.nasPortId" readonly="true"
											styleClass="textbox gray_text" />
									</span>
								</p>
								<p class="floatl marginleft30 ">
									<span> <strong> Pool Name </strong> <html:text name="qrcForm" property="newcrmPartnerNetworkConfig.poolName" readonly="true"
											styleClass="textbox gray_text" />
									</span>
								</p>

								<p class="floatl clear">
									<span> <strong> Old Primary MAC Address </strong> <html:text name="qrcForm" property="networkConfigurationsPojo.currentCpeMacId" readonly="true"
											styleClass="textbox gray_text" styleId="oldprimaryMac" />
									</span>
								</p>
								<p class="floatl marginleft30 ">
									<span> <strong> Old Secondary MAC Address </strong> <html:text name="qrcForm" property="networkConfigurationsPojo.currentSlaveMacId"
											readonly="true" styleClass="textbox gray_text" styleId="oldsecondaryMac" />
									</span>
								</p>

								<p class="floatl clear">
									<span class=""><strong>Primary MAC Address</strong></span>

									<html:hidden property="shiftingWorkflowPojo.npId" name="qrcForm" styleId="partnerId" />
									<html:hidden property="shiftingWorkflowPojo.customerId" name="qrcForm" styleId="telcomCustomer" />
									<html:text styleId="primaryMACAddrId" styleClass="textbox" name="qrcForm" property="shiftingWorkflowPojo.currentCPeMacId" maxlength="14"
										 onkeypress='javascript:autoFormatMacID(event,this);'></html:text>
									<font style="top: 53px;"></font>
								</p>
								<p class="floatl marginleft30">
									<span><strong>Secondary MAC Address</strong></span>
									<html:text styleId="secondaryMacTextId" styleClass="textbox" name="qrcForm" property="shiftingWorkflowPojo.currentSlaveMacId"
										onkeyup="getSecondaryMACAddrForNOCLevel1('primaryMACAddrId');" onkeypress='javascript:autoFormatMacID(event,this);'></html:text>
									<font style="top: 53px;"></font>

								</p>

							</logic:equal>
							<p class="floatl clear">
								<strong> Remarks <span class="error_message verticalalignTop">*</span></strong>
								<html:textarea property="remarksPojo.remarks" value="" name="qrcForm" styleClass="LmsRemarkstextarea" styleId="ifrRemarks">
								</html:textarea>
								<font></font> <span class="hidden"><font class="errorRemarks" style="top: 98px;">Please enter Remarks between [3-4000].</font></span> <font
									class="errorRemarks hidden" style="top: 98px;">Please enter Remarks.</font>
							</p>
							<br class="clear" />
						</div>
						<div class="floatr inner_right">
							<p class="buttonPlacement">

								<a href="#" id="submit_csdStage" class="main_button " onclick="submitNOCL1();"><span>Submit</span></a>
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