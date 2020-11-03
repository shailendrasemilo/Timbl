<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib uri="/WEB-INF/tlds/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/tlds/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/tlds/displaytag.tld" prefix="display"%>
<%@taglib uri="/WEB-INF/tlds/struts-html.tld" prefix="html"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<head>
<style>
.ui-autocomplete {
	max-height: 150px;
	overflow-y: auto;
}
</style>
<script type="text/javascript" src="javascript/jquery-ui.js"></script>
<link rel="stylesheet" href="css/jquery-ui.css" />
</head>
<div id="section">
	<html:form action="/viewDetails" method="post">
		<html:hidden name="lmsForm" property="remarksPojo.createdBy" value="${sessionScope.userPojo.userId}" />
		<div id="popupSection" class="noPadding">
			<div class="popupSection" style="padding-top: 5px;">
				<div class="inner_section">
					<div class="inner_left_lead ">
						<div class="inner_view_lead">
							<h4>Lead Details</h4>
							<h4 style="border: none; float: left; width: auto; position: absolute; top: 0px; right: -2px;">
								<logic:equal name="crmRoles" property="available(create_qrc_tkt)" value="true" scope="session">
									<a href="javascript:void(0);" class="yellow_button floatr margintop7 generateLeadTicketBtn">Generate New Ticket</a>
								</logic:equal>
							</h4>
							<p class=" floatl ">
								<strong>Lead ID</strong>
								<logic:notEmpty name="lmsForm" property="lmsPojo.leadId">
									<font class=""><bean:write name="lmsForm" property="lmsPojo.leadId" /></font>
								</logic:notEmpty>
								<logic:empty name="lmsForm" property="lmsPojo.leadId">-</logic:empty>
							</p>
							<p class="floatl marginleft30">
								<strong>Service Name</strong>
								<logic:notEmpty name="lmsForm" property="lmsPojo.product">
									<bean:write name="crmRoles" property="displayEnum(Product,${lmsForm.lmsPojo.product})" scope="session" />
								</logic:notEmpty>
								<logic:empty name="lmsForm" property="lmsPojo.product">-</logic:empty>
							</p>
							<h4 class="paddingTop10 clear">Customer Details</h4>
							<p class="floatl">
								<strong>Customer Name</strong>
								<logic:notEmpty name="lmsForm" property="lmsPojo.customerName">
									<bean:write name="lmsForm" property="lmsPojo.customerName" />
								</logic:notEmpty>
								<logic:empty name="lmsForm" property="lmsPojo.customerName">-</logic:empty>
							</p>
							<p class="floatl marginleft30">
								<strong>Mobile Number</strong> ${ lmsForm.lmsPojo.contactNumber > 0 ? lmsForm.lmsPojo.contactNumber : '-' }
							</p>
							<p class="floatl marginleft30">
								<strong>Calling Number</strong> ${ lmsForm.lmsPojo.ctiNumber > 0 ? lmsForm.lmsPojo.ctiNumber : '-' }
							</p>
							<p class="floatl marginleft30">
								<strong>Email ID</strong>
								<logic:notEmpty name="lmsForm" property="lmsPojo.emailId">
									<bean:write name="lmsForm" property="lmsPojo.emailId" />
								</logic:notEmpty>
								<logic:empty name="lmsForm" property="lmsPojo.emailId">-</logic:empty>
							</p>
							<h4 class="clear paddingTop10">Customer Address Details</h4>
							<p class=" floatl ">
								<strong>State</strong>
								<logic:notEmpty name="lmsForm" property="lmsPojo.state">
									<bean:write name="lmsForm" property="lmsPojo.state" />
								</logic:notEmpty>
								<logic:empty name="lmsForm" property="lmsPojo.state">-</logic:empty>
							</p>
							<p class=" floatl marginleft30">
								<strong>City</strong>
								<logic:notEmpty name="lmsForm" property="lmsPojo.city">
									<bean:write name="lmsForm" property="lmsPojo.city" />
								</logic:notEmpty>
								<logic:empty name="lmsForm" property="lmsPojo.city">-</logic:empty>
							</p>
							<p class=" floatl marginleft30">
								<strong>Area</strong>
								<logic:notEmpty name="lmsForm" property="lmsPojo.area">
									<bean:write name="lmsForm" property="lmsPojo.area" />
								</logic:notEmpty>
								<logic:empty name="lmsForm" property="lmsPojo.area">-</logic:empty>
							</p>
							<p class=" floatl marginleft30">
								<strong>Locality / Sector - Society</strong> ${empty lmsForm.lmsPojo.locality ? '-' : lmsForm.lmsPojo.locality }
							<p class="floatl clear">
								<strong>House Number</strong>
								<logic:notEmpty name="lmsForm" property="lmsPojo.houseNumber">
									<bean:write name="lmsForm" property="lmsPojo.houseNumber" />
								</logic:notEmpty>
								<logic:empty name="lmsForm" property="lmsPojo.houseNumber">-</logic:empty>
							</p>
							<p class="floatl marginleft30">
								<strong>Landmark</strong>
								<logic:notEmpty name="lmsForm" property="lmsPojo.landmark">
									<bean:write name="lmsForm" property="lmsPojo.landmark" />
								</logic:notEmpty>
								<logic:empty name="lmsForm" property="lmsPojo.landmark">-</logic:empty>
							</p>
							<p class="floatl marginleft30">
								<strong>PIN Code</strong> ${ lmsForm.lmsPojo.pincode > 0 ? lmsForm.lmsPojo.pincode : '-' }
							</p>
							<br class="clear" />
							<div id="remarksSection" class="hideRemarks">
								<logic:equal name="lmsForm" property="parameter" value="customerProfile">
									<c:if test="${ lmsForm.lmsPojo.status ne 'C' and lmsForm.lmsPojo.status ne 'M'}">
										<p class="floatl clear">
											<strong> Remarks</strong>
											<html:hidden name="lmsForm" property="remarksPojo.createdBy" value="${sessionScope.userPojo.userId}" />
											<html:hidden name="lmsForm" property="remarksPojo.mappingId" value="${lmsForm.lmsPojo.leadId}" />
											<html:textarea cols="27" rows="38" styleClass="textareaheight80" name="lmsForm" styleId="remarksLms" property="remarksPojo.remarks" />
											<font style="margin-top: 0px;"></font>
										</p>
										<p class="buttonPlacement floatr">
											<html:link href="javascript:saveRemarks()" styleId="remarks_Save" styleClass="main_button">Save</html:link>
										</p>
									</c:if>
								</logic:equal>
								<br class="clear" />
								<div class="success_message">
									<logic:messagesPresent message="true">
										<html:messages id="message" message="true">
											<bean:write name="message" />
											<br />
										</html:messages>
									</logic:messagesPresent>
								</div>
							</div>
							<!-- start Lms table -->
							<br class="clear" />
							<div class="LmsTable marginRight10 " style="margin-top: 0px;">
								<%--<h4 style="margin: 0px;">
									Appointment Details <span class="lmsMinusImage floatr"></span>
								</h4>
								<div class="viewLmsTable margintop10">
									<iframe src="leadGeneration.do?method=getAppointment&mappingId=${lmsForm.lmsPojo.leadId}" scrolling="yes" frameborder="0"
										style="border: none; overflow: hidden; width: 100%;" allowTransparency="true" onload="iframeLoaded(this)"></iframe>
								</div>--%>
								<logic:equal name="crmRoles" property="available(create_qrc_tkt)" value="true" scope="session">
									<h4 style="margin: 0px;">
										LMS Ticket Details <span class="lmsMinusImage floatr"></span>
									</h4>
									<div class="viewLmsTable margintop10">
										<iframe src="leadGeneration.do?method=getLMSTktInfo&mappingId=${lmsForm.lmsPojo.leadId}" scrolling="yes" frameborder="0"
											style="border: none; overflow: hidden; width: 100%;" allowTransparency="true" onload="iframeLoaded(this)"></iframe>
									</div>
								</logic:equal>
								<h4>
									Remarks Details <span class="lmsMinusImage floatr"></span>
								</h4>
								<div class="viewLmsTable margintop10">
									<iframe src="logAction.do?method=getRemarks&mappingId=${lmsForm.lmsPojo.leadId}" scrolling="yes" frameborder="0"
										style="border: none; overflow: hidden; height: 250px; width: 100%;" allowTransparency="true" onload="iframeLoaded(this)"></iframe>
								</div>
								<h4>
									Activity Log Details <span class="lmsMinusImage floatr"></span>
								</h4>
								<div class="viewLmsTable margintop10">
									<iframe src="jsp/content/masterdata/displayAuditLog.jsp" scrolling="yes" frameborder="0"
										style="border: none; overflow: hidden; width: 100%; height: 250px;" allowTransparency="true" onload="iframeLoaded(this)"></iframe>
								</div>
							</div>
							<!-- end Lms table -->
						</div>
					</div>
					<!-- for Lead tickets creation -->
					<div class="leadTicketCreation hidden">
						<h4>
							Ticket Generation <a href="javascript:void(0);" class="yellow_button floatr margintop7 viewLeadBtn">View Lead Details</a>
						</h4>
						<div class="relative inner_left_lead">
							<div class="floatl">
								<html:hidden name="lmsForm" property="srTicketPojo.mappingId" value="${lmsForm.lmsPojo.leadId}" />
								<html:hidden name="lmsForm" property="srTicketPojo.createdBy" value="${sessionScope.userPojo.userId}" />
								<html:hidden name="lmsForm" property="srTicketPojo.qrcType" styleId="qrcType" />
								<html:hidden name="lmsForm" property="srTicketPojo.functionalbinId" styleId="functionalbinId" />
								<html:hidden name="lmsForm" property="ticketHistory.createdBy" value="${sessionScope.userPojo.userId}" />
								<html:hidden name="lmsForm" styleId="lmsCategoryId" property="srTicketPojo.qrcCategoryId" />
								<p class="floatl">
									<strong> Sub Category <span class="error_message verticalalignTop">*</span>
									</strong>
									<html:text styleId="lmsSubCategoryTextId" styleClass="textbox" name="lmsForm" maxlength="28" value="" property="srTicketPojo.qrcSubCategory"></html:text>
									<html:hidden name="lmsForm" property="srTicketPojo.qrcSubCategoryId" styleId="lmsSubCategoryId" />

									<font id="subCategoryError"></font>
								</p>

								<p class="floatl marginleft30">
									<strong> Sub Sub Category <span class="error_message verticalalignTop">*</span></strong>
									<html:text styleId="lmsSubSubCategoryTextId" styleClass="textbox" name="lmsForm" maxlength="28" value="" property="srTicketPojo.qrcSubSubCategory"></html:text>
									<html:hidden name="lmsForm" property="srTicketPojo.qrcSubSubCategoryId" styleId="lmsSubSubCategoryId" />

									<font id="subSubCategoryError"></font>
								</p>
								<p class="floatl clear"></p>
								<p class="floatl clear">
									<strong> Remarks <span class="error_message verticalalignTop">*</span></strong>
									<html:textarea name="lmsForm" cols="27" rows="38" styleClass="LmsRemarkstextarea" property="ticketHistory.remarks" value="" styleId="remarks"></html:textarea>
									<font></font>
								</p>

								<p class="floatl clear">
									<strong> Calling Number</strong>
									<html:text styleClass="Lmstextbox" name="lmsForm" maxlength="10" property="srTicketPojo.callingNo" styleId="callingNo" value=""
										onkeyup="javascript:ValidatenReplaceAlphanumeric(this);" />
									<font></font>
								</p>

								<p class="floatl clear">
									<strong> Status <span class="error_message verticalalignTop">*</span>
									</strong> <span class="dropdownWithoutJs"> <html:select name="lmsForm" styleId="resolutionType" styleClass="" property="srTicketPojo.resolutionType"
											onchange="fillFunctionBinIdForForward('functionalbinId',this.value);">
											<logic:notEmpty name="lmsForm" property="ticketActions">
												<html:optionsCollection name="lmsForm" property="ticketActions" label="contentName" value="contentValue" />
											</logic:notEmpty>
										</html:select> <font></font>
									</span>
								</p>
								<br class="clear" />
							</div>
							<div class="floatr inner_right">
								<p class="buttonPlacement">
									<a href="javascript:saveLMSTicket();" id="saveLMSTicket" class="main_button  "><span>Generate</span></a>
								</p>
							</div>
							<br class="clear" />
						</div>
					</div>
				</div>
			</div>
		</div>
	</html:form>
</div>
