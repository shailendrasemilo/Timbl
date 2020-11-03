<%@page import="org.apache.struts.taglib.html.HiddenTag"%>
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
	$( document ).ready( function(){
		document.getElementById( 'feasible' ).style.display = 'none';
	} );
	function show(){
		document.getElementById( 'feasible' ).style.display = 'inline';
		document.getElementById( 'shiftingInitiation' ).style.display = 'block';
	}
</script>
<script type="text/javascript" src="javascript/jquery-ui.js"></script>
<link rel="stylesheet" href="css/jquery-ui.css" />
<div class="loadingPopup hidden"></div>
<div id="section">
	<div class="modelPopupDiv" id="uploadDocPPId"></div>
	<div class="section">
		<logic:notEmpty name="qrcForm" property="custDetailsPojo.customerId">
			<jsp:include page="crfCustomerDescription.jsp"></jsp:include>
		</logic:notEmpty>
		<div class="inner_section ">
			<div class="inner_left_lead floatl  qrcLeft">
				<bean:define id="activeMenu" value="addressChange"></bean:define>
				<%@include file="qrcMenu.jsp"%>
			</div>
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
				<ul class="table clear customerAddress" style="max-width: 410px;">
					<h4>Current Address :</h4>

					<li class="table_container"><span class="installAddressArea width367">

							<div>
								<font>${qrcForm.oldshiftingWorkflowPojo.addressLine1}, </font>
							</div>
							<div>
								<font>${qrcForm.oldshiftingWorkflowPojo.addressLine2}</font>
							</div>
							<div>
								<font>${qrcForm.oldshiftingWorkflowPojo.addressLine3}</font>
							</div>
							<div>
								<div>
									<font>${qrcForm.oldshiftingWorkflowPojo.pincode}</font>
								</div>
					</span></li>
				</ul>
				<div class="clear"></div>
				<h4>Shifting Address Initiation</h4>
				<div style="position: absolute">
					<div id="feasible">
						<p id='SOCIETY_FEASIBLITY_ALERT' class="floatl " style='color: #022864; font: 14px arial bolder;'>${qrcForm.stageIndex < 2 ? 'To check feasibility,
							provide data till Locality / Sector - Society.' : ''}</p>
					</div>
				</div>
				<div class="relative inner_left_lead">
					<html:form action="/shiftingWorkflow" method="post" styleId="shifting_initiation">
						<div class="floatl">
							<p>
								<span> <html:hidden property="shiftingWorkflowPojo.shiftingType" value="OP" name="qrcForm"></html:hidden> <html:hidden property="customerId"
										value="${qrcForm.custDetailsPojo.customerId}" name="qrcForm"></html:hidden>


									<p class="floatl clear">
										<strong>PIN Code</strong>
										<html:text styleClass="Lmstextbox" property="shiftingWorkflowPojo.pincode" maxlength="6" styleId="pincodeLms" readonly="false"
											onkeyup="javascript:ValidatenReplaceAlphanumeric(this);" onclick="removeZeroFromNumber(this);" onfocus="removeZeroFromNumber(this);"
											onblur="getSocietyByPinCode(this.value);"></html:text>
										<font></font>
									</p>
									<p class="floatl marginleft30">
										<strong>State</strong> <span class="LmsdropdownWithoutJs"> <html:select name="qrcForm" styleId="statelms" styleClass="" property="stateName"
												onchange="populateCityByState('citylms',this.value);resetAutocomplete(true,true,true);">
												<html:option value="">Please Select</html:option>
												<logic:notEmpty name="qrcForm" property="stateList">
													<html:optionsCollection name="qrcForm" property="stateList" label="stateName" value="stateName" />
												</logic:notEmpty>
											</html:select><font></font>
										</span>
									</p>
									<p class="floatl marginleft30">
										<strong>City</strong> <span class="LmsdropdownWithoutJs"> <html:select name="qrcForm" styleId="citylms" property="cityName"
												onchange="areasByCity(this.value);">
												<html:option value="">Please Select</html:option>
												<logic:notEmpty name="qrcForm" property="cityList">
													<html:optionsCollection name="qrcForm" property="cityList" label="cityName" value="cityName" />
												</logic:notEmpty>
											</html:select><font></font>
										</span>
									</p>
									<p class="floatl marginleft30">
										<strong>Area</strong>
										<html:text styleId="arealms" styleClass="Lmstextboxuppercase" property="areaName" maxlength="30" onkeyup="javascript:changeToUpperCase(this);"
											onblur="getAreaManagersByArea(this.value);"></html:text>
										<font> </font> <span id="showAreaList" class="showList" />
									</p>
									<p class="floatl clear">
										<strong>Locality / Sector - Society</strong>
										<html:text styleId="localitylms" styleClass="Lmstextboxuppercase" maxlength="60" property="shiftingWorkflowPojo.locality"
											onkeyup="show();javascript:changeToUpperCase(this);//filterLocality(this.value);"></html:text>
										<font></font> <span id="showLocalityList" class="showList"> </span>
									</p>

									<p class="floatl marginleft30">
										<strong>House Number</strong>
										<html:text styleClass="Lmstextboxuppercase" maxlength="10" property="shiftingWorkflowPojo.houseNumber" styleId="houseNoLms"
											onkeyup="javascript:changeToUpperCase(this)" />
									</p>
									<p class="floatl marginleft30">
										<strong>Landmark</strong>
										<html:text styleClass="Lmstextboxuppercase" maxlength="25" property="shiftingWorkflowPojo.landmark" styleId="landmarkLms"
											onkeyup="javascript:changeToUpperCase(this)" />
									</p>
									<p class="floatl clear">
										<strong> Remarks <span class="error_message verticalalignTop">*</span></strong>
										<html:textarea property="remarksPojo.remarks" value="" name="qrcForm" styleClass="LmsRemarkstextarea" styleId="shiftingRemarks"></html:textarea>
										<span class="hidden"><font></font>
									</p> <br class="clear" />
						</div>
						<div class="floatr inner_right">
							<p class="buttonPlacement">
								<a href="#" id="shiftingInitiation" class="main_button "><span>Submit</span></a>
							</p>
							<br class="clear" /> <br class="clear" />
						</div>
						<br class="clear" />
						<br class="clear" />
					</html:form>
					<br class="clear" />
				</div>
				<div class="margintop30">
					<iframe src="shiftingWorkflow.do?method=getShiftingHistory&customerId=${qrcForm.custDetailsPojo.customerId}" scrolling="yes" frameborder="0"
						style="border: none; overflow: hidden; width: 100%; height: 250px;" allowTransparency="true"> </iframe>
				</div>

			</div>
			<p class="clear" />