<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib uri="/WEB-INF/tlds/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/tlds/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/tlds/displaytag.tld" prefix="display"%>
<%@taglib uri="/WEB-INF/tlds/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/tlds/struts-logic.tld" prefix="logic"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<style>
.ui-autocomplete {
	max-height: 150px;
	overflow-y: auto;
}
</style>
<script type="text/javascript" src="javascript/jquery-ui.js"></script>
<link rel="stylesheet" href="css/jquery-ui.css" />
<script>
	$( document ).ready( function(){

		var slection = document.getElementById( "selection" ).value;
		if ( slection == "RV" ) {
			$( '#resolveAction' ).removeClass( 'hide1' );

		}
		if ( slection == "FW" ) {
			$( '#forwardAction' ).removeClass( 'hide1' );

		}
		if ( slection == "FU" ) {
			$( '#followUpAction' ).removeClass( 'hide1' );

		}

	} );
</script>

<div id="section">
	<form action="" method="post" id="searchGIS">
	
	<html:hidden name="qrcForm" property="srTicketPojo.srId" value="${qrcForm.srTicketPojo.srId}" styleId="uploadDocCRF" />
<div class="overlayDiv"></div>
<div class="modelPopupDiv" id="uploadDocPPId"></div>
		<html:hidden name="qrcForm" property="srTicketPojo.qrcSubSubCategoryId" styleId="qrcSubSubCategoryId" />
		<html:hidden name="qrcForm" property="srTicketPojo.srId" styleId="srId" />
		<logic:notEmpty name="qrcForm" property="srTicketPojo.customerDetailsPojo">
			<html:hidden name="qrcForm" property="srTicketPojo.customerDetailsPojo.product" styleId="product" />
			<html:hidden name="qrcForm" property="srTicketPojo.customerDetailsPojo.customerId" styleId="customerId" />
		</logic:notEmpty>
		<logic:notEmpty name="qrcForm" property="lmsPojo">
			<html:hidden name="qrcForm" property="lmsPojo.product" styleId="product" />
		</logic:notEmpty>
		<html:hidden name="qrcForm" property="srTicketPojo.qrcCategoryId" styleId="qrcCategoryId" />
		<html:hidden name="qrcForm" property="presentStage" value="${qrcForm.srTicketPojo.functionalbinId}" styleId="functionalBinId" />
		<html:hidden name="qrcForm" property="presentOwner" value="${sessionScope.userPojo.userId}" />
		<div class="section">
			<logic:equal name="qrcForm" property="srTicketPojo.moduleType" value="QRC"><h2>Edit Ticket</h2></logic:equal>
			<logic:equal name="qrcForm" property="srTicketPojo.moduleType" value="LMS"><h2>Edit LMS Ticket</h2></logic:equal>
			<div class="marginRight10" style="margin-left: -1px;">
				<h4>
					Ticket History Details <span class="lmsMinusImageRemarks floatr"></span>
				</h4>
				<div class="viewticketRemarks margintop10 viewLmsLeadTable">
					<iframe src="logAction.do?method=getTicketHistory&mappingId=${ qrcForm.srTicketPojo.srId }" scrolling="yes" frameborder="0" id="frame"
						style="border: none; overflow: hidden; width: 100%; height: 265px;" allowTransparency="true"> </iframe>
				</div>
			</div>
			<div class="inner_section ">
				<div class=" floatl manageGISRight qrcRight">
					<div class="success_message">
						<logic:messagesPresent message="true">
							<html:messages id="message" message="true">
								<bean:write name="message" />
							</html:messages>
						</logic:messagesPresent>
					</div>
					<span class="error_message"> <html:errors property="appError" />
					</span> <br />
					<ul class="table display mWidth900 ticketView">
						<logic:notEmpty name="qrcForm" property="srTicketPojo">
							<logic:equal name="qrcForm" property="srTicketPojo.moduleType" value="QRC">
								<jsp:include page="ticketDiv.jsp"></jsp:include>
							</logic:equal>
							<logic:equal name="qrcForm" property="srTicketPojo.moduleType" value="LMS">
								<jsp:include page="lmsTicketDiv.jsp"></jsp:include>
							</logic:equal>
						</logic:notEmpty>
					</ul>
					<p class="floatl clear"></p>
					<div id="actionDiv" class="floatl">
						<div id="actionSection" class="floatl">
							<p class="floatl clear">
								<strong>Status<span class="error_message">*</span></strong>
							</p>
							<p style="margin-top: 0px;">
								<span class="dropdownWithoutJs"> <html:select styleId="selection" name="qrcForm" property="qrcActions"
										onchange="javascript:qrcActionSelection('functionBinList','rcaLsit',this.value)">
										<html:option value="0">Please Select</html:option>
										<html:optionsCollection name="qrcForm" property="ticketActions" label="contentName" value="contentValue" />
									</html:select><font></font>
								</span>
							</p>
						</div>
						<div id="followUpAction" class="hide1 floatl marginleft30">
							<div class="floatl">
								<p class="floatl clear">
									<strong>Follow Up Date<span class="error_message">*</span></strong>
								</p>
								<p style="margin-top: 0px;">
									<html:text styleClass="tcal" name="qrcForm" styleId="followUpDate" property="srTicketPojo.displayFollowupOn" readonly="readonly" />
									<font></font>
								</p>
								<html:hidden name="qrcForm" property="followupOn" styleId="hiddenfollowUpDate" />
							</div>
							<div class="floatl marginleft30">
								<p class="floatl">
									<strong>(HH:MM:SS 24 hour format)</strong>
								</p>
								<p style="margin-top: 0px;">
									<font style="border: 1px solid #AFAFAF; padding: 5px 5px 5px 5px;"> <select id="strHourID">
											<%
											    for ( int i = 0; i < 24; i++ )
											    {
											%>
											<option value="<%=i%>"><%=String.format( "%02d", i )%></option>
											<%
											    }
											%>
									</select> <select id="strMinuteID">
											<%
											    for ( int i = 0; i < 60; i++ )
											    {
											%>
											<option value="<%=i%>"><%=String.format( "%02d", i )%></option>
											<%
											    }
											%>
									</select> <select id="strSecondID">
											<%
											    for ( int i = 0; i < 60; i++ )
											    {
											%>
											<option value="<%=i%>"><%=String.format( "%02d", i )%></option>
											<%
											    }
											%>
									</select>
									</font> <font></font>
								</p>
							</div>
							<div class="floatl">
								<p class="floatl marginleft30">
									<strong>Inbox</strong>	
								</p>
								<p class="marginleft30" style="margin-top: 0px;">
									<span class="LmsdropdownWithoutJs">
            						    <html:select name="qrcForm" property="inboxSelected" styleId="inboxSelectionID">
											   <html:option value="GI">Group Inbox</html:option>
											   <html:option value="I">My Inbox</html:option>
									
										</html:select> 
									</span>
								</p>
							</div>

						</div>
						<div id="forwardAction" class="hide1 floatl marginleft30">
							<div class="floatl">
								<p class="floatl clear">
									<strong>Functional Bin<span class="error_message">*</span></strong>
								</p>
								<p style="margin-top: 0px;">
									<span class="dropdownWithoutJs"> <html:select name="qrcForm" property="futureStage" styleId="functionBinList"
											onchange="javascript:fillUsersList('usersList',this.value)">
											<logic:notEmpty property="actionTakens" name="qrcForm">
												<html:optionsCollection property="actionTakens" name="qrcForm" label="contentName" value="contentValue" />
											</logic:notEmpty>

										</html:select> <font></font>
									</span>
								</p>
							</div>
							<div class="floatl marginleft30">
								<p class="floatl clear">
									<strong>User List</strong>
								</p>
								<p style="margin-top: 0px;">
									<span class="dropdownWithoutJs"> <html:select name="qrcForm" property="futureOwner" styleId="usersList">
											<logic:notEmpty property="crmUsers" name="qrcForm">
												<html:options property="crmUsers" name="qrcForm" />
											</logic:notEmpty>
										</html:select>
									</span>
								</p>
							</div>
							<!-- <div class="floatl marginleft30">
								<p class="floatl clear">
									<strong>Sub Sub Category</strong>
								</p>
								<p style="margin-top: 0px;">
									<span class="dropdownWithoutJs"> <html:select name="qrcForm" property="subSubCategoryId" styleId="subSubCategory">
											<logic:notEmpty property="qrcSubSubCategoriesPojos" name="qrcForm">
												<html:optionsCollection property="qrcSubSubCategoriesPojos" name="qrcForm" label="qrcSubSubCategory" value="qrcSubSubCategoryId" />
											</logic:notEmpty>
										</html:select>
									</span>
								</p>
							</div> -->
						<div class="floatl marginleft30" >
						<p class="floatl clear">
								<strong>&nbsp;</strong>
								</p>
							<p style="margin-top: 0px;">
								<label class="label_check margintop5 width200" for="flagSelected">
									<html:checkbox property="srTicketPojo.flagSelected" name="qrcForm" value="true" styleId="flagSelected"></html:checkbox><strong style="margin-top: 0">Flag Remark</strong>
																	</label>
							</p>
						</div>
						</div>
					</div>
					<div id="resolveAction" class="hide1 floatl marginleft30">
						<div class="floatl">
							<p class="floatl clear">
								<strong>Action Taken<span class="error_message">*</span></strong>
							</p>
							<p style="margin-top: 0px;">
								<span class="dropdownWithoutJs"> <html:text styleId="rcaLsit" styleClass="textbox" name="qrcForm" value="" property="srTicketPojo.actionTaken"></html:text>
									<font></font> <html:hidden name="qrcForm" property="srTicketPojo.actionTakenId" styleId="actionTakenId" />

								</span>
							</p>
						</div>
						<div class="floatl marginleft30">
							<p class="floatl clear">
								<strong>Root Cause<span class="error_message">*</span></strong>
							</p>

							<p style="margin-top: 0px;">
								<span class="dropdownWithoutJs"> <html:text styleId="rcaReasonList" styleClass="textbox" name="qrcForm" value=""
										property="srTicketPojo.rootCause"></html:text> <font></font> <html:hidden name="qrcForm" property="srTicketPojo.rootCauseId" styleId="rootCauseId" />
								</span>
							</p>
						</div>
						<p class="floatl clear">
							<br /> <strong>Wrong Tagging</strong>
							<html:radio styleId="trueValueWT" name="qrcForm" property="srTicketPojo.wrongTagging" value="Y">
								<bean:message key="message.select.trueValue" />
							</html:radio>
							<html:radio styleId="falseValueWT" name="qrcForm" property="srTicketPojo.wrongTagging" value="N">
								<bean:message key="message.select.falseValue" />
							</html:radio>
						</p>
						<p class="floatl" style='margin-left: 80px !important;'>
							<br /> <strong>Field Visit Required</strong>
							<html:radio styleId="homeVisit" name="qrcForm" property="srTicketPojo.visitRequired" value="H">
								<bean:message key="message.select.homeVisit" />
							</html:radio>
							<html:radio styleId="fieldVisit" name="qrcForm" property="srTicketPojo.visitRequired" value="F">
								<bean:message key="message.select.fieldVisit" />
							</html:radio>
							<html:radio styleId="falseValueFVR" name="qrcForm" property="srTicketPojo.visitRequired" value="N">
								<bean:message key="message.select.falseValue" />
							</html:radio>
						</p>
           				<p class="floatl marginleft30"><strong> </strong><strong> </strong>
   							<a href="javascript:openUploadPopUp('${initParam.dmshost }/np-document-upload','QRC');" class="yellow_button">Upload Document</a>
   						</p>
					</div>
					<div class="floatl clear">
						<br />
						<html:hidden name="qrcForm" property="ticketHistory.action" value="SR" />
						<html:hidden name="qrcForm" property="ticketHistory.createdBy" value="${sessionScope.userPojo.userId}" />
						<html:hidden name="qrcForm" property="ticketHistory.ticketId" value="${qrcForm.srTicketPojo.srId}" />
						<p class="floatl clear">
							<strong> Remarks <span class="error_message">*</span></strong>
						</p>
						<p style="margin-top: 0px;">
							<html:textarea name="qrcForm" cols="27" rows="38" styleClass="textareaheight80" property="ticketHistory.remarks" styleId="remarks"></html:textarea>
							<font style="margin-top: 12px;"></font>
						</p>
					</div>
					<div class="floatr inner_right">
						<p class="buttonPlacement">
							<logic:equal name="crmRoles" property="available(update_qrc_tkt)" value="true" scope="session">
								<a href="javascript:editServiceRequest();" id="submit_ticket" class="main_button"><span>Submit</span></a>
							</logic:equal>
						</p>
					</div>
				</div>
				<p class="clear"></p>
			</div>
		</div>
		<!-- end -->
		<p class="clear"></p>
	</form>
</div>
