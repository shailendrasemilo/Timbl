<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page import="org.apache.struts.Globals"%>
<%@ page import="org.apache.struts.taglib.html.Constants"%>
<%@taglib uri="/WEB-INF/tlds/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/tlds/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/tlds/displaytag.tld" prefix="display"%>
<%@taglib uri="/WEB-INF/tlds/struts-html.tld" prefix="html"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div class="loadingPopup hidden"></div>
<div id="section">
	<div class="modelPopupDiv" id="uploadDocPPId"></div>
	<div class="section">
		<h2>
			Shifting Workflow&nbsp;-&nbsp;&nbsp;<a style="color: #DEAE00;"><bean:write name='qrcForm' property='customerId' /> </a>
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
				<h4 style="width: 615px">FT Level 1</h4>
				<div class="relative inner_left_lead">
					<html:form action="/shiftingWorkflow" method="post" styleId="ifrStage">
						<html:hidden name="qrcForm" property="shiftingWorkflowPojo.shiftingType" styleId="shiftingType" value="${qrcForm.shiftingWorkflowPojo.shiftingType}" />
						<html:hidden name='qrcForm' property='customerId' value="${qrcForm.customerId}" />
						<html:hidden name="qrcForm" property="shiftingWorkflowPojo.workflowId" />
						<html:hidden name="qrcForm" property="shiftingWorkflowPojo.areaId" />
						<html:hidden name="qrcForm" property="shiftingWorkflowPojo.societyId" />
						<html:hidden name="qrcForm" property="shiftingWorkflowPojo.previousNetwork" value="${qrcForm.custDetailsPojo.product}" />
						<html:hidden name="qrcForm" property="shiftingWorkflowPojo.previousNpId" value="${qrcForm.custDetailsPojo.npId}" />
						<div class="floatl">
							<p class="floatl">
								<span class=""><b> Existing Network Partner</b> <label class="showTextbox" style="width: 140px"> <bean:write name="crmRoles"
											property="displayEnum(NP,${qrcForm.custDetailsPojo.npId})" scope="session" />
								</label> </span>
							</p>
							<p class="floatl marginleft30">
								<b>Existing Network Type</b>
								<logic:equal name="qrcForm" property="custDetailsPojo.product" value="EOC">
									<input type="radio" checked="true" />FTTN
                    			</logic:equal>
								<logic:equal name="qrcForm" property="custDetailsPojo.product" value="BB">
									<input type="radio" checked="true" />FTTX
                    			</logic:equal>
							</p>
							<p class="floatl clear">

								<b>Network Partner<span class="error_message verticalalignTop">*</span></b> <span class="LmsdropdownWithoutJs"> <html:select
										property="shiftingWorkflowPojo.npId" styleId="op_npName"
										onchange="getProductsforSocietyNP(this.value,'${qrcForm.shiftingWorkflowPojo.societyId}',${qrcForm.shiftingWorkflowPojo.areaId},'productName');">
										<html:option value="">Please Select</html:option>
										<logic:notEmpty name="qrcForm" property="partnerPojoList">
											<html:optionsCollection name="qrcForm" property="partnerPojoList" label="partnerName" value="partnerId" />
										</logic:notEmpty>
									</html:select> <font></font>
								</span>
							</p>

							<p class="floatl marginleft30">
								<span class="" id="productName"> </span>
							</p>
							<p class="floatl clear">
								<strong> Remarks <span class="error_message verticalalignTop">*</span></strong>
								<html:textarea property="remarksPojo.remarks" value="" name="qrcForm" styleClass="LmsRemarkstextarea" styleId="ifrRemarks">
								</html:textarea>
								<font></font>
							</p>
							<br class="clear" />
						</div>
						<div class="floatr inner_right">
							<p class="buttonPlacement">
								<a href="#" id="submit_ifrStage" class="main_button "><span>Submit</span></a>
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