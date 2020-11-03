<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib uri="/WEB-INF/tlds/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/tlds/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/tlds/displaytag.tld" prefix="display"%>
<%@taglib uri="/WEB-INF/tlds/struts-html.tld" prefix="html"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<style>
.ui-autocomplete {
	max-height: 150px;
	overflow-y: auto;
}
</style>
<script type="text/javascript" src="javascript/jquery-ui.js"></script>
<link rel="stylesheet" href="css/jquery-ui.css" />
<div class="loadingPopup hidden"></div>
<div id="section" align="center">
		<html:form action="/leadGeneration" method="post" styleId="generateLms" styleClass="updateLeadGeneration">
				<html:hidden property="remarksPojo.createdBy" value="${sessionScope.userPojo.userId}" />
				<html:hidden property="appointmentPojo.createdBy" value="${sessionScope.userPojo.userId}" />
				<html:hidden styleId="commonErrors" property="commonErrors" value="true" />
				<logic:notEqual name="lmsForm" property="lmsPojo.lmsStage" value="SC">
					<html:hidden name="lmsForm" property="lmsPojo.product" />
				</logic:notEqual>
				<div class="section">
						<%
						    boolean tagValue = false;
						        boolean tagValueMoblie = false;
						%>
						<!-- Page Heading and hidden values for CC case Starts -->
						<logic:equal name="lmsForm" property="stageIndex" value="0">
								<html:hidden property="lmsPojo.createdBy" value="${sessionScope.userPojo.userId}" />
								<html:hidden property="lmsPojo.leadSource" styleId="leadSource" value="CC" />
								<!-- value="${sessionScope.userPojo.functionalBin}"/>  -->
								<html:hidden name="lmsForm" property="appointmentPojo.stage" value="CC" />
								<!-- value="${sessionScope.userPojo.functionalBin}"/> -->
								<h2>Generate Lead</h2>
								<p class="absoluteRight">
									<a href="javascript:;" class="yellow_button" id="generateIILLead">Generate IIL Lead</a>
								<p>
						</logic:equal>
						<!-- Page Heading and hidden values for CC case Ends -->
						<!-- Page Heading and hidden values for lead manage case Starts -->
						<logic:greaterThan name="lmsForm" property="stageIndex" value="0">
								<html:hidden name="lmsForm" property="lmsPojo.createdBy" />
								<html:hidden name="lmsForm" property="lmsPojo.leadSource" />
								<html:hidden name="lmsForm" property="lmsPojo.leadId" />
								<html:hidden name="lmsForm" property="appointmentPojo.appointmentId" />
								<html:hidden name="lmsForm" property="lmsPojo.lastModifiedBy" value="${sessionScope.userPojo.userId}" />
								<html:hidden name="lmsForm" property="lmsPojo.lmsStage" />
								<html:hidden name="lmsForm" property="lmsPojo.lmsId" />
								<html:hidden name="lmsForm" property="lmsPojo.feasibility" value="${lmsForm.lmsPojo.feasibility}" styleId="hiddenFeasibility" />
								<html:hidden name="lmsForm" styleId="leadSource" property="lmsPojo.leadSource" />
								<html:hidden name="lmsForm" property="lmsPojo.status" />
								<html:hidden name="lmsForm" property="appointmentPojo.stage" value="${lmsForm.lmsPojo.lmsStage}" />
								<html:hidden name="lmsForm" property="appointmentPojo.mappingId" value="${lmsForm.lmsPojo.leadId}" />
								<h2>Update Lead</h2>
						</logic:greaterThan>
						<logic:greaterThan name="lmsForm" property="stageIndex" value="1">
								<%
								    tagValue = true;
								            tagValueMoblie = true;
								%>
						</logic:greaterThan>
						<logic:equal name="lmsForm" property="nonEditableAtSC" value="true">
								<%
								    tagValue = true;
								%>
						</logic:equal>
						<logic:equal name="lmsForm" property="stageIndex" value="1">
								<%
								    tagValueMoblie = true;
								%>
						</logic:equal>
						<!-- Page Heading and hidden values for lead manage case Ends -->
						<!-- Success Messages Starts -->
						<div class="success_message">
								<logic:messagesPresent message="true">
										<html:messages id="message" message="true">
												<bean:write name="message" />
										</html:messages>
								</logic:messagesPresent>
						</div>
						<!-- Success Messages Ends -->
						<div class="error_message" id="error">
								<html:errors />
						</div>
						<!-- for error messages -->
						<div class="inner_section">
								<div class="inner_left_lead floatl marginleft10">
										<div class="Address clear paddingBottom30">
												<h4 style='font: 18px/25px Times !important;'>Customer Address Details</h4>
												<logic:lessThan name="lmsForm" property="stageIndex" value="4">
														<p id='SOCIETY_FEASIBLITY_ALERT' class="floatl " style='color: #022864; font: 14px arial bolder;'>${lmsForm.stageIndex < 2 ? 'To check
																feasibility, provide data till Locality / Sector - Society.' : ''}</p>
												</logic:lessThan>
												<p class="floatl clear">
														<strong>PIN Code</strong>
														<html:text styleClass="Lmstextbox" property="lmsPojo.pincode" maxlength="6" styleId="pincodeLms" readonly="<%=tagValue%>"
																onkeyup="javascript:ValidatenReplaceAlphanumeric(this);" onclick="removeZeroFromNumber(this);" onfocus="removeZeroFromNumber(this);" onblur="getSocietyByPinCode(this.value);"></html:text>
												</p>
												<p class="floatl marginleft30">
														<strong>State</strong>
														<logic:notEqual name="lmsForm" property="nonEditableAtSC" value="true">
																<logic:lessThan name="lmsForm" property="stageIndex" value="2">
																		<span class="LmsdropdownWithoutJs"> <html:select name="lmsForm" styleId="statelms" styleClass="" property="lmsPojo.state"
																						onchange="populateCityByState('citylms',this.value);resetAutocomplete(true,true,true);">
																						<html:option value="">Please Select</html:option>
																						<logic:notEmpty name="lmsForm" property="statePojoList">
																								<html:optionsCollection name="lmsForm" property="statePojoList" label="stateName" value="stateName" />
																						</logic:notEmpty>
																				</html:select>
																		</span>
																</logic:lessThan>
														</logic:notEqual>
														<logic:greaterThan name="lmsForm" property="stageIndex" value="1">
																<html:text styleClass="Lmstextbox" styleId="statelms" property="lmsPojo.state" readonly="<%=tagValue%>" />
														</logic:greaterThan>
														<logic:equal name="lmsForm" property="nonEditableAtSC" value="true">
																<html:text styleClass="Lmstextbox" styleId="statelms" property="lmsPojo.state" readonly="<%=tagValue%>" />
														</logic:equal>
												</p>
												<p class="floatl marginleft30">
														<strong>City</strong>
														<logic:notEqual name="lmsForm" property="nonEditableAtSC" value="true">
																<logic:lessThan name="lmsForm" property="stageIndex" value="2">
																		<span class="LmsdropdownWithoutJs"> <html:select name="lmsForm" styleId="citylms" property="lmsPojo.city"
																						onchange="areasByCity(this.value);">
																						<html:option value="">Please Select</html:option>
																						<logic:notEmpty name="lmsForm" property="cityPojoList">
																								<html:optionsCollection name="lmsForm" property="cityPojoList" label="cityName" value="cityName" />
																						</logic:notEmpty>
																				</html:select>
																		</span>
																</logic:lessThan>
														</logic:notEqual>
														<logic:greaterThan name="lmsForm" property="stageIndex" value="1">
																<html:text styleClass="Lmstextbox" styleId="citylms" property="lmsPojo.city" readonly="<%=tagValue%>" />
														</logic:greaterThan>
														<logic:equal name="lmsForm" property="nonEditableAtSC" value="true">
																<html:text styleClass="Lmstextbox" styleId="citylms" property="lmsPojo.city" readonly="<%=tagValue%>" />
														</logic:equal>
												</p>
												<p class="floatl marginleft30">
														<strong>Area</strong>
														<html:text styleId="arealms" styleClass="Lmstextboxuppercase" property="lmsPojo.area" maxlength="30" readonly="<%=tagValue%>"
																onkeyup="javascript:changeToUpperCase(this);//filterArea(this.value);" onblur="getAreaManagersByArea(this.value);"></html:text>
														<span id="showAreaList" class="showList"> <%-- <html:select name="lmsForm" property="lmsPojo.area" styleId="lmsArea" multiple="true"
                  onclick="fillGisList('lmsArea','arealms',this.value,'showAreaList')">
                </html:select> --%>
														</span>
												</p>
												<p class="floatl clear">
														<strong>Locality / Sector - Society</strong>
														<%-- value="${lmsForm.lmsPojo.lmsId gt 0 ? lmsForm.lmsPojo.locality.concat((empty lmsForm.lmsPojo.society ? '' : ' - '.concat(lmsForm.lmsPojo.society))) : '' }" --%>
														<html:text styleId="localitylms" styleClass="Lmstextboxuppercase" maxlength="60" property="lmsPojo.locality" readonly="<%=tagValue%>"
																onkeyup="javascript:changeToUpperCase(this);//filterLocality(this.value);"></html:text>
														<span id="showLocalityList" class="showList"> </span>
												</p>

												<p class="floatl marginleft30">
														<strong>House Number</strong>
														<html:text styleClass="Lmstextboxuppercase" maxlength="10" property="lmsPojo.houseNumber" styleId="houseNoLms" readonly="<%=tagValue%>"
																onkeyup="javascript:changeToUpperCase(this)" />
												</p>
												<p class="floatl marginleft30">
														<strong>Landmark</strong>
														<html:text styleClass="Lmstextboxuppercase" maxlength="25" property="lmsPojo.landmark" styleId="landmarkLms" readonly="<%=tagValue%>"
																onkeyup="javascript:changeToUpperCase(this)" />
												</p>
												<logic:equal name="lmsForm" property="stageIndex" value="2">
														<p class="floatl clear">
																<b>Is selected area feasible?</b> <span class="LmsdropdownWithoutJs"> <html:select name="lmsForm" styleId="feasibilityLms"
																				property="lmsPojo.feasibility">
																				<html:option value="">Please Select</html:option>
																				<html:option value="Y">Yes</html:option>
																				<html:option value="N">No</html:option>
																		</html:select>
																</span>
														</p>
												</logic:equal>
												<p class="clear"></p>
										</div>
										<div class=" paddingBottom30">
												<h4 style='font: 18px/25px Times !important; margin-top: -16px;'>Customer Details</h4>
												<p class="floatl">
														<strong>Customer Name</strong>
														<html:text styleClass="Lmstextboxuppercase" name="lmsForm" maxlength="50" property="lmsPojo.customerName" styleId="generateLmsC_Name"
																readonly="<%=tagValue%>" onkeyup="changeToUpperCase(this);"></html:text>
												</p>
												<p class="floatl marginleft30">
														<strong>Mobile Number<span class="error_message">*</span></strong> <span class="mobilePrefix">[+91]</span>
														<html:text styleClass="Lmstextbox" property="lmsPojo.contactNumber" name="lmsForm" styleId="generateLmsMobile" maxlength="10"
																readonly="<%=tagValueMoblie%>" onkeyup="javascript:ValidatenReplaceAlphanumeric(this);javascript:mobileValidation('generateLmsMobile');"
																onclick="removeZeroFromNumber(this);" onfocus="removeZeroFromNumber(this);"></html:text>
												</p>
												<p class="floatl marginleft30">
														<strong>Calling Number</strong><span class="mobilePrefix">[+91]</span>
														<html:text styleClass="Lmstextbox" maxlength="10" property="lmsPojo.ctiNumber" name="lmsForm" styleId="generateLmsCTI"
																onkeyup="javascript:ValidatenReplaceAlphanumeric(this);" onclick="removeZeroFromNumber(this);" onfocus="removeZeroFromNumber(this);"
																readonly="<%=tagValue%>"></html:text>
												</p>
												<p class="floatl marginleft30">
														<strong>Email ID</strong>
														<html:text styleClass="Lmstextbox" maxlength="256" property="lmsPojo.emailId" name="lmsForm" styleId="generateLmsEmail" readonly="<%=tagValue%>"></html:text>
												</p>
												<p class="clear"></p>
										</div>
										<logic:notEqual name="lmsForm" property="stageIndex" value="0">
												<div class="paddingBottom20">
														<h4 style='font: 18px/25px Times !important; margin-top: -16px;'>
																Service Name<span class="error_message verticalalignTop"> *</span>
														</h4>
														<p>
																<span class=" inlineBlock"> <span id="lmsProducts"> <logic:iterate id="products" name="lmsForm" property="products" indexId="ctr">
																						<LABEL class="label_radio margintop5" for="${products.contentName}"> <html:radio name="lmsForm" styleId="${products.contentName}"
																										property="lmsPojo.product" value="${products.contentValue}" disabled="<%=tagValue%>">${products.contentName}</html:radio>
																						</LABEL>
																				</logic:iterate>
																</span>
																</span>
														</p>
												</div>
										</logic:notEqual>
									   <%--	<logic:equal name="lmsForm" property="showAppointmentDiv" value="false">
												<p class="floatl clear">
														Appointments Block have been disabled. Either you are not eligble for it or max chances to save appointments has been completed.<br /> In case,
														You're a Area Manager or Business Partner, This lead should be forwarded to Cluster Head for further processing.
												</p>
										</logic:equal>
										<logic:notEqual name="lmsForm" property="showAppointmentDiv" value="false">
												<html:hidden name="lmsForm" property="showAppointmentDiv" />
												<html:hidden name="lmsForm" property="appointmentPojo.appointmentId" styleId="appointmentId" />
												<div class=" clear paddingBottom30" id="customerAppointmentDetails">
														<h4 style='font: 18px/25px Times !important; margin-top: -16px;'>
																Customer Appointment Details <a href="javascript:;" class="yellow_button floatr margintop7 reset" id="resetLmsAppointmentDetails"
																		style='margin-top: -5px;'>Reset</a>
														</h4>
														<p class="floatl ">
																<b>Preferred Date of Appointment</b>
																<html:text styleClass="tcal tcalInput" name="lmsForm" styleId="appointmentDate" property="appointmentPojo.displayDate" readonly="true"></html:text>
														</p>
														<p class="floatl marginleft30">
																<b>Preferred Time of Appointment</b> <span class=" inlineBlock" id="appointmentTiming"> <logic:iterate id="meetingTime" name="lmsForm"
																				property="appointmentTimings" indexId="ctr">
																				<LABEL class="label_radio margintop5" for="${meetingTime.contentName}"> <html:radio name="lmsForm"
																								styleId="${meetingTime.contentName}" property="appointmentPojo.appointmentTime" value="${meetingTime.contentValue}">${meetingTime.contentName}</html:radio>
																				</LABEL>
																		</logic:iterate>
																</span>
														</p>
														<p class="floatl clear">
																<strong>Appointment Remarks</strong>
																<html:textarea cols="27" rows="38" styleClass="Lmstextarea" name="lmsForm" styleId="idApRemark" property="appointmentPojo.remarks"></html:textarea>
														</p>
														<p class="floatl marginleft30">
																<b>Preferred Mode of Appointment</b> <span class=" inlineBlock" id="appointmentMode"> <logic:iterate id="appointmentModes" name="lmsForm"
																				property="appointmentModes" indexId="ctr">
																				<LABEL class="label_radio margintop5" for="${appointmentModes.contentName}"> <html:radio name="lmsForm"
																								styleId="${appointmentModes.contentName}" property="appointmentPojo.modeOfContact" value="${appointmentModes.contentValue}">${appointmentModes.contentName}</html:radio>
																				</LABEL>
																		</logic:iterate>
																</span>
														</p>
														<p class="floatl marginleft30 modeofcontact" id="T">
																<strong>Contact Number</strong> <span class="mobilePrefix">[+91]</span>
																<html:text styleClass="Lmstextbox" name="lmsForm" styleId="idContactNo" property="appointmentPojo.contactNumber" maxlength="10"
																		onkeyup="javascript:mobileValidation('idContactNo')"></html:text>
														</p>
														<p class="floatl marginleft30 modeofcontact" id="F">
																<strong>Appointment Address</strong>
																<html:textarea cols="0" rows="10" styleClass="Lmstextarea" name="lmsForm" styleId="idAddress" property="appointmentPojo.appointmentAddress"
																		onkeyup="javascript:changeToUpperCase(this)"></html:textarea>
														</p>
														<br class="clear" />
												</div>
										</logic:notEqual>--%>
										<p class="clear"></p>
										<div class="paddingBottom30 clear">
												<!-- Logical Display for CC users for lead creation Starts -->
												<logic:equal name="lmsForm" property="stageIndex" value="0">
														<h4 style='font: 18px/25px Times !important; margin-top: -31px;'>Remarks</h4>
														<p id="referralSourcesPara" class=" ">
																<b>Where did you hear about us?</b> <span class="inlineBlock" id="lmsReference"> <%
     int referSourcIndex = 1;
 %> <logic:iterate id="referralSources" name="lmsForm" property="crmReferralSources">
																				<LABEL class="label_check margintop5" for="${fn:replace(referralSources.categoryValue,' ','')}" style="width: 150px;"> <input
																						type="checkbox" id="${fn:replace(referralSources.categoryValue,' ','')}" onclick="javascript:showOthersReferralSource(this.value);"
																						value="${referralSources.categoryValue}" name="referralResources" maxlength="128">${referralSources.categoryValue}
																				</LABEL>
																				<%
																				    if ( referSourcIndex % 2 == 0 )
																				                {
																				%>
																				<br>
																				<%
																				    }
																				                referSourcIndex++;
																				%>
																		</logic:iterate>
																</span> <span id="otherReferralSource"></span>
																<html:hidden name="lmsForm" styleId="otherRS" property="lmsPojo.referralSource" />
														</p>
												</logic:equal>
												<!-- Logical Display for CC users for lead creation Ends -->
												<!-- Logical Display for lead manage Stages Starts -->
												<logic:greaterThan name="lmsForm" property="stageIndex" value="0">
														<h4>Actions</h4>
														<p class="floatl ">
																<strong>Action to be performed</strong> <span class="dropdownWithoutJs"> <html:select name="lmsForm" styleId="actionToBePerformed"
																				property="remarksPojo.actions">
																				<html:option value="">Please Select</html:option>
																				<logic:notEmpty name="lmsForm" property="performingActions">
																						<html:optionsCollection name="lmsForm" property="performingActions" label="contentName" value="contentValue" />
																				</logic:notEmpty>
																		</html:select> <html:hidden name="lmsForm" styleId="crfIds" property="crfIds" />
																</span>
														</p>
														<p id="actionToBePerformedCloseLead" class="hide1 marginleft30 floatl ">
																<strong>Close Reason</strong> <span class="LmsdropdownWithoutJs"> <html:select name="lmsForm" property="remarksPojo.reasonId"
																				styleId="idCloseReason">
																				<html:option value="">Please Select</html:option>
																				<logic:notEmpty name="lmsForm" property="closeReasons">
																						<html:optionsCollection name="lmsForm" property="closeReasons" label="categoryValue" value="categoryId" />
																				</logic:notEmpty>
																		</html:select>
																</span>
														</p>
														<p id="forwardToAM" class="hide1  marginleft30 floatl ">
																<strong>Forward to Area Manager</strong> <span class="LmsdropdownWithoutJs"> <html:select name="lmsForm" property="toUser"
																				styleId="idAMs" />
																</span> <span id="amNotFound" class="marginleft30 error_message"></span>
														</p>
														<p id="forwardToBP" class="hide1  marginleft30 floatl ">
																<strong>Forward to Business Partner</strong> <span class="LmsdropdownWithoutJs"> <html:select name="lmsForm" property="lmsPojo.partnerId"
																				styleId="idBP">
																				<html:option value="">Please Select</html:option>
																				<logic:notEmpty name="lmsForm" property="partnerList">
																						<html:optionsCollection name="lmsForm" property="partnerList" label="partnerName" value="partnerId" />
																				</logic:notEmpty>
																		</html:select>
																</span>
														</p>
														<html:hidden name="lmsForm" property="inaModule" styleId="allowedModule" />
														<logic:equal name="lmsForm" property="inaModule" value="Y">

																<div class="hide1 clear floatl leadCrfView" id="idCloseReason">
																		<span class="addbtn_role_group"><a href="javascript:addLmsCRFId()" class="grey_button_add"><span>Add</span></a></span>
																		<div class="display_group" id="crfIdBlock">
																				<div class="stays-at-top">
																						<span class="heading_text"> CAF Number <input type="text" id="crfID" class="Lmstextbox" maxlength="8">
																						</span>
																				</div>
																				<ul class="lead_group">
																						<div id="showID"></div>
																				</ul>
																		</div>
																</div>
														</logic:equal>

														<h4 class="clear" style="padding-top: 30px;">
																Remarks<span class="error_message">*</span>
														</h4>
														<br class="clear" />
												</logic:greaterThan>
												<logic:lessEqual name="lmsForm" property="stageIndex" value="0">
														<p class="clear">
																<strong>Remarks<span class="error_message">*</span></strong>
														</p>
												</logic:lessEqual>
												<p style="margin-top: 0px;">
														<html:textarea cols="27" rows="38" styleClass="LmsRemarkstextarea" property="remarksPojo.remarks" name="lmsForm" styleId="remarks" />
												</p>
										</div>
								</div>
								<!-- Logical Display for lead manage Stages Ends -->
								<!-- Page buttons Starts-->
								<div class="floatr inner_right">
										<logic:greaterThan name="lmsForm" property="stageIndex" value="0">
												<logic:lessThan name="lmsForm" property="stageIndex" value="6">
														<html:link href="javascript:saveLead();" styleId="save_generateLms" styleClass="main_button">Save</html:link>
												</logic:lessThan>
										</logic:greaterThan>
										<logic:empty property="lmsPojo.leadId" name="lmsForm">
												<html:link href="javascript:submitFormQRC('create')" styleClass="main_button marginleft10">Submit</html:link>
										</logic:empty>
										<logic:notEmpty property="lmsPojo.leadId" name="lmsForm">
												<html:link href="javascript:submitFormQRC('update')" styleClass="main_button marginleft10">Submit</html:link>
										</logic:notEmpty>
										<logic:equal name="lmsForm" property="stageIndex" value="0">
												<html:link href="#" styleId="reset_generateLms" styleClass="main_button marginleft10">Reset</html:link>
										</logic:equal>
								</div>
								<!-- Page buttons Ends-->
								<br class="clear" />
						</div>
						<!-- Lead related additional data Starts -->
						<logic:greaterThan name="lmsForm" property="stageIndex" value="0">
								<div class="LmsTable marginRight10">
										<%--<h4>
												Appointment Details <span class="lmsMinusImage floatr"></span>
										</h4>
										<div class="viewLmsTable margintop10">
												<iframe src="leadGeneration.do?method=getAppointment&mappingId=${lmsForm.lmsPojo.leadId}" scrolling="yes" frameborder="0" style="border: none; overflow: hidden; width: 100%;"
														allowTransparency="true" onload="iframeLoaded(this)"></iframe>
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
				</div>
				</logic:greaterThan>
				<!-- Lead related additional data Ends -->
</div>
<p class="clear"></p>
</html:form>
</div>
