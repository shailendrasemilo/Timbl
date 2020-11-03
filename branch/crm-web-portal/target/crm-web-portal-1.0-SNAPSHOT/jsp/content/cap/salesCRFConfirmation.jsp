<%@page import="java.util.Date"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.text.DateFormat"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<%@taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@taglib uri="http://struts.apache.org/tags-nested" prefix="nested"%>
<%@taglib uri="/WEB-INF/tlds/displaytag.tld" prefix="display"%>
<head>
<link href="css/colorbox.css" rel="stylesheet" />
<script src="javascript/jquery.colorbox.js"></script>
<link rel="stylesheet" href="css/jquery-ui.css">
<script src="javascript/jquery-ui.js"></script>
<script>
	$( document ).ready(
			function(){
				$( ".iframe1" ).colorbox( {
					iframe : true, width : "658px", height : "299px"
				} );
				$( ".calender" ).datepicker(
						{
							maxDate : today, changeMonth : true, changeYear : true, dateFormat : "dd-M-yy", prevText : "Earlier", showOn : "both",
							yearRange : "c-100:c+100"
						} );
				$( ".calenderDateOnCAF" ).datepicker(
						{
							maxDate : $( '#hiddenCreatedTime' ).val() != "" ? new Date( $( '#hiddenCreatedTime' ).val() ) : today,
							changeMonth : true, changeYear : true, dateFormat : "dd-M-yy", prevText : "Earlier", yearRange : "c-100:c+100"
						} );
			} );
	var today = new Date();
</script>

</head>
<body>
	<div id="section" align="center">
		<form action="/crmCap" method="post" Id="validationCRFEntry" name="validationCRFEntry">
			<html:hidden property="crfTabId" name="crmCapForm" value="${crmCapForm.crfTabId}" styleId="tabHidden" />
			<html:hidden name="crmCapForm" property="crmUserId" styleId="hiddenCRMUserId" value="${ crmCapForm.crmUserId }" />
			<%-- <html:hidden property="partnerId" name="crmCapForm" value="${crmCapForm.partnerId}" styleId="partnerIdHidden"/>--%>
			<html:hidden property="customerDetailsPojo.crfStage" name="crmCapForm" value="${crmCapForm.customerDetailsPojo.crfStage}" styleId="stageId" />
			<html:hidden property="customerDetailsPojo.recordId" name="crmCapForm" value="${crmCapForm.customerDetailsPojo.recordId}" styleId="hiddenRecordId" />
			<html:hidden name="crmCapForm" property="customerDetailsPojo.customerId" value="${crmCapForm.customerDetailsPojo.customerId}" />
			<html:hidden property="society" name="crmCapForm" value="${crmCapForm.society}" styleId="hiddenSociety" />
			<html:hidden property="state" name="crmCapForm" value="${crmCapForm.state}" styleId="hiddenState" />
			<html:hidden property="city" name="crmCapForm" value="${crmCapForm.city}" styleId="hiddenCity" />
			<html:hidden property="area" name="crmCapForm" value="${crmCapForm.area}" styleId="hiddenArea" />
			<html:hidden property="locality" name="crmCapForm" value="${crmCapForm.locality}" styleId="hiddenLocality" />
			<html:hidden property="installationAddressPojo.addressType" name="crmCapForm" value="IN"/>
			<html:hidden property="installationAddressPojo.recordId" name="crmCapForm" value="${crmCapForm.installationAddressPojo.recordId}"/>
			<input type="hidden" id="instAddLine1"  /><input type="hidden" id="instAddLine2"  /><input type="hidden" id="instAddLine3"  />
			<div class="section">
				<h2>CAF Confirmation</h2>
				<div class="inner_section marginBottom30">
					<!-- Success Messages Starts -->
					<div class="success_message">
						<logic:messagesPresent message="true">
							<html:messages id="message" message="true">
								<bean:write name="message" />
							</html:messages>
						</logic:messagesPresent>
					</div>
					<!-- Success Messages Ends -->
					<div class="error_message">
						<html:errors property="appError" />
					</div>
					<!-- for error messages -->
					<!-- validation form enteries -->
					<div class="relative">
						<div class="clear marginleft10 inner_left_lead floatl">

							<p class="floatl">
								<strong>Action </strong>
								<logic:empty name="crmCapForm" property="displayISRDate">
									<LABEL class="label_radio" for="approveValidationCRF"> <html:radio name="crmCapForm" property="remarksPojo.actions" value="Approve"
											styleId="approveValidationCRF">Process Further</html:radio>
									</LABEL>
									<LABEL class="label_radio" for="rejectValidationCRF"> <html:radio name="crmCapForm" property="remarksPojo.actions" value="Reject"
											styleId="rejectValidationCRF">Cancel</html:radio>
									</LABEL>
									<LABEL class="label_radio" for="changeFeasibleAddId"> <html:radio name="crmCapForm" property="remarksPojo.actions" value="FeasibleAddress"
											styleId="changeFeasibleAddId">Change Feasible Address</html:radio>
									</LABEL>
								</logic:empty>
								<logic:notEmpty name="crmCapForm" property="displayISRDate">
									<LABEL class="label_radio"> <html:radio name="crmCapForm" property="remarksPojo.actions" value="Approve" styleId="approveId">Process Further</html:radio>
									</LABEL>
								</logic:notEmpty>
							</p>

							<p class=" hide1 floatl marginleft30" id="rejectValidationCRFShow">
								<strong>Reason for Cancellation</strong> <span class="dropdownWithoutJs"> <logic:notEmpty name="crmCapForm" property="refuselReasonList">
										<html:select name="crmCapForm" property="remarksPojo.reasonId" styleId="reasonForRejection">
											<html:option value="0">Please Select</html:option>
											<html:optionsCollection name="crmCapForm" property="refuselReasonList" label="categoryValue" value="categoryId" />
										</html:select>
									</logic:notEmpty>
								</span> <font></font>
							</p>


							<div class="hide1 paddingBottom30 clear" id="instAddressShow">
								
						    	<h4>Feasible Address <a href="crmCap.do?method=checkFeasibility&Product=${crmCapForm.customerDetailsPojo.product}"
										class="floatr marginright5 yellow_button margintop7 iframe1">Check Feasibility</a></h4>
						     	<ul class="tabcontentUL">
							      	<li><strong>State</strong> 
							  	  		<label><bean:write name="crmCapForm" property="state" /></label> 
							  		</li>
							  		<li><strong>City</strong>   
							  	  		<label><bean:write name="crmCapForm" property="city" /></label>
							 		</li>
							  		<li><strong>Area</strong> 
							  	  		<label><bean:write name="crmCapForm" property="area" /></label>
									</li>
							  		<li><strong>Locality</strong> 
							  	  		<label>
							  	  			<logic:notEmpty name="crmCapForm" property="locality">
							  	  				<bean:write name="crmCapForm" property="locality" />	
							  	  			</logic:notEmpty>
							  	  			<logic:empty name="crmCapForm" property="locality">-</logic:empty>
							  	  		</label>
							 		</li>
							  		<li><strong>Society</strong> 
							  	  		<label>
							  	  			<logic:notEmpty name="crmCapForm" property="society">
							  	  				<bean:write name="crmCapForm" property="society" />
							  	  			</logic:notEmpty>
							  	  			<logic:empty name="crmCapForm" property="society">-</logic:empty>
							  	  		</label>
							 		</li>
							 		<li><strong>Connectable Homes</strong> 
							  	  		<label>
							  	  			<logic:notEmpty name="crmCapForm" property="connectableHomes">
							  	  				<bean:write name="crmCapForm" property="connectableHomes" />
							  	  			</logic:notEmpty>
							  	  			<logic:empty name="crmCapForm" property="connectableHomes">-</logic:empty>
							  	  		</label>
							 		</li>
								</ul>
							
								<br class="clear" />
							</div>
							
							<p class="floatl clear">
								<strong> Remarks <span class="error_message">*</span></strong>
								<html:textarea styleClass="LmsRemarkstextarea" name="crmCapForm" property="remarksPojo.remarks" styleId="commentsValidationCRF"></html:textarea>
								<font id="errorCommentsValidationCRF"></font>
							</p>

							<logic:equal name="crmCapForm" property="customerDetailsPojo.crfPreviousStage" value="SP">
								<logic:notEmpty name="crmCapForm" property="displayISRDate">
									<p class="floatl clear">
										<strong>Date on ISR</strong> <span class="relative"> <html:text styleClass="tcal tcalInput" readonly="true" styleId="ISRDate" name="crmCapForm"
												property="displayISRDate"></html:text><font></font>
										</span>
									</p>
								</logic:notEmpty>
							</logic:equal>
						</div>
						<div class="floatr inner_right">

							<p class="buttonPlacement">
								<logic:equal name="crmRoles" property="available(create_ina,update_ina)" value="true" scope="session">
									<a href="#" id="submit_validationCRFEntryFT" class="main_button marginleft10"><span>Submit</span></a>
								</logic:equal>
							</p>
						</div>
						<br class="clear" />
					</div>
					<!-- validation form enteries -->

					<!-- include table -->
					<div id="contentDiv"></div>
					<!-- include table -->

				</div>
				<div class="LmsTable">
					<h4>
						Customer Details: <span class="lmsMinusImage floatr"></span>
					</h4>
					<div class="viewLmsTable margintop10 viewLmsLeadTable">
						<jsp:include page="/jsp/content/cap/view-InA.jsp"></jsp:include>
					</div>
				</div>
				<div class="LmsTable">
					<h4>
						Remarks Details <span class="lmsMinusImage floatr"></span>
					</h4>
					<div class="viewLmsTable margintop10 viewLmsLeadTable">
						<iframe src="logAction.do?method=getRemarks&mappingId=${crmCapForm.customerDetailsPojo.recordId}" scrolling="no" frameborder="0" id="frame"
							style="border: none; overflow: hidden; width: 100%; height: 500px;" allowTransparency="true"> </iframe>
					</div>
				</div>
			</div>
		</form>
	</div>
</body>
</html>
