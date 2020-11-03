<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page import="org.apache.struts.Globals"%>
<%@ page import="org.apache.struts.taglib.html.Constants"%>
<%@taglib uri="/WEB-INF/tlds/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/tlds/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/tlds/displaytag.tld" prefix="display"%>
<%@taglib uri="/WEB-INF/tlds/struts-html.tld" prefix="html"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<script>
	function showdiv( str, property ){

		if ( property == "Installation" ) {

			var cpe = $( 'input[name="shiftingWorkflowPojo.cpeAvailable"]:checked' ).val();

			var refusal = $( 'input[name="shiftingWorkflowPojo.customerRefusal"]:checked' ).val();

			if ( str == 'Y' && refusal == "N" && ( cpe == 'Y' || cpe == undefined ) ) {
				document.getElementById( "npDiv" ).style.display = "block";
			}
			else {
				document.getElementById( 'npDiv' ).style.display = "none";

			}
		}
		if ( property == "cpe" ) {
			var installation = $( 'input[name="shiftingWorkflowPojo.physicalInstallation"]:checked' ).val();
			var refusal = $( 'input[name="shiftingWorkflowPojo.customerRefusal"]:checked' ).val();
			if ( (str == 'Y' || str == undefined) && refusal == "N" && installation == "Y" ) {
				document.getElementById( 'npDiv' ).style.display = "block";
			}
			else {
				document.getElementById( "npDiv" ).style.display = "none";
			}
		}
		if ( property == "refusal" ) {
			var installation = $( 'input[name="shiftingWorkflowPojo.physicalInstallation"]:checked' ).val();

			var cpe = $( 'input[name="shiftingWorkflowPojo.cpeAvailable"]:checked' ).val();

			if (  str == 'N'  &&  installation == 'Y'  && ( cpe == 'Y' || cpe == undefined ) ) {
				document.getElementById( 'npDiv' ).style.display = "block";
			}
			else {
				document.getElementById( "npDiv" ).style.display = "none";
			}
		}
	}
</script>
<div class="loadingPopup hidden"></div>
<div id="section">
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
				<h4 style="width: 615px">IFR EOC LEVEL1</h4>
				<div class="relative inner_left_lead">
					<html:form action="/shiftingWorkflow" method="post" styleId="ifrStage">
						<html:hidden name="qrcForm" property="shiftingWorkflowPojo.shiftingType" styleId="shiftingType" value="${qrcForm.shiftingWorkflowPojo.shiftingType}" />
						<html:hidden name="qrcForm" property="shiftingWorkflowPojo.product" styleId="product" value="${qrcForm.shiftingWorkflowPojo.product}" />
						<html:hidden name='qrcForm' property='customerId' value="${qrcForm.customerId}" />
						<html:hidden name="qrcForm" property="shiftingWorkflowPojo.workflowId" />
						<html:hidden name="qrcForm" property="shiftingWorkflowPojo.customerId" />
						<html:hidden name="qrcForm" property="shiftingWorkflowPojo.workflowStage" />
						<html:hidden name="qrcForm" property="shiftingWorkflowPojo.currentBin" />
						<html:hidden name="qrcForm" property="shiftingWorkflowPojo.previousBin" />
						<html:hidden name="qrcForm" property="shiftingWorkflowPojo.npId" />
						<html:hidden name="qrcForm" property="shiftingWorkflowPojo.previousNpId" />
						<html:hidden name="qrcForm" property="shiftingWorkflowPojo.previousNetwork" />
						<div class="floatl">
							<p class=" floatl clear">
								<b>Physical Installation</b> <span class=""> <html:radio property="shiftingWorkflowPojo.physicalInstallation" value="Y"
										onclick="showdiv(this.value,'Installation');" /> Yes <html:radio property="shiftingWorkflowPojo.physicalInstallation" value="N"
										onclick="showdiv(this.value,'Installation');" />No
								</span>
							</p>
							<p class=" floatl marginleft30">
								<b>CPE Available </b> <span class=""> <html:radio property="shiftingWorkflowPojo.cpeAvailable" value="Y" onclick="showdiv(this.value,'cpe');" />
									Yes <html:radio property="shiftingWorkflowPojo.cpeAvailable" value="N" onclick="showdiv(this.value,'cpe');" />No
								</span>

							</p>
							<p class=" floatl marginleft30">
								<b>Customer Refusal </b> <span class=""> <html:radio property="shiftingWorkflowPojo.customerRefusal" value="Y"
										onclick="showdiv(this.value,'refusal');" /> Yes <html:radio property="shiftingWorkflowPojo.customerRefusal" value="N"
										onclick="showdiv(this.value,'refusal');" />No
								</span>
							</p>
							<p>
								<c:if
									test="${qrcForm.shiftingWorkflowPojo.customerRefusal eq 'N' and qrcForm.shiftingWorkflowPojo.physicalInstallation eq 'Y' or qrcForm.shiftingWorkflowPojo.cpeAvailable ne 'Y'}">
									<jsp:include page="NPLevel1.jsp"></jsp:include>
								</c:if>
							</p>

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
								<a href="#" id="submit_csdStage" class="main_button " onclick="submitIfrEOCL1();"><span>Submit</span></a>
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