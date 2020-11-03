<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="/WEB-INF/tlds/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/tlds/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/tlds/displaytag.tld" prefix="display"%>
<%@taglib uri="/WEB-INF/tlds/struts-html.tld" prefix="html"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<head>
<link href="css/colorbox.css" rel="stylesheet" />
<script src="javascript/jquery.colorbox.js"></script>
<link rel="stylesheet" href="css/jquery-ui.css">
<script src="javascript/jquery-ui.js"></script>
<script>
	$( document ).ready(function(){
		$(".iframe1").colorbox({iframe:true, width:"658px", height:"299px"});
		$(".calender").datepicker({
		  maxDate: today,
		  changeMonth: true,
		  changeYear: true,
		  dateFormat: "dd-M-yy",
		  prevText: "Earlier",
		  showOn: "both",
		  yearRange: "c-100:c+100"
		});
		$(".calenderDateOnCAF").datepicker({
		      maxDate: $( '#hiddenCreatedTime' ).val() != ""  ? new Date($('#hiddenCreatedTime').val()) : today,
		      changeMonth: true,
		      changeYear: true,
		      dateFormat: "dd-M-yy",
		      prevText: "Earlier",
		      yearRange: "c-100:c+100"
		});
	});
var today = new Date();
</script>
</head>
<body>
<div id="section"  align="center">
<html:form action="/crmCap"  method="post" styleId="createInA">
<input type="hidden" value="${crmCapForm.customerDetailsPojo.createdTime}" id="hiddenCreatedTime" />
<html:hidden property="additionalDetailsPojo.createdBy" value="${crmCapForm.additionalDetailsPojo.createdBy}"/>
<html:hidden property="installationAddressPojo.createdBy" value="${crmCapForm.installationAddressPojo.createdBy}"/>
<html:hidden property="billingAddressPojo.createdBy" value="${crmCapForm.billingAddressPojo.createdBy}"/>
<html:hidden property="customerDetailsPojo.createdBy" value="${crmCapForm.customerDetailsPojo.createdBy}"/>
<html:hidden property="documentDetailsPojo.createdBy" value="${crmCapForm.documentDetailsPojo.createdBy}"/>
<html:hidden property="nationalityDetailsPojo.createdBy" value="${crmCapForm.nationalityDetailsPojo.createdBy}"/>
<html:hidden property="orderDetailsPojo.createdBy" value="${crmCapForm.orderDetailsPojo.createdBy}"/>
<html:hidden property="telecommunicationPayment.createdBy" value="${crmCapForm.telecommunicationPayment.createdBy}"/>
<html:hidden property="teleservicesPayment.createdBy" value="${crmCapForm.teleservicesPayment.createdBy}"/>
<html:hidden property="planDetailsPojo.createdBy" value="${crmCapForm.planDetailsPojo.createdBy}"/>
<html:hidden property="appointmentPojo.createdBy" value="${crmCapForm.appointmentPojo.createdBy}"/>
<html:hidden property="crfTabId" name="crmCapForm" value="${crmCapForm.crfTabId}" styleId="tabHidden"/>
<html:hidden property="state" name="crmCapForm" value="${crmCapForm.state}" styleId="hiddenState"/>
<html:hidden property="city" name="crmCapForm" value="${crmCapForm.city}" styleId="hiddenCity"/>
<html:hidden property="area" name="crmCapForm" value="${crmCapForm.area}" styleId="hiddenArea"/>
<html:hidden property="locality" name="crmCapForm" value="${crmCapForm.locality}" styleId="hiddenLocality"/>
<html:hidden property="society" name="crmCapForm" value="${crmCapForm.society}" styleId="hiddenSociety"/>
<html:hidden property="installationAddressPojo.instAreaId" name="crmCapForm" value="${crmCapForm.installationAddressPojo.instAreaId}"/>
<html:hidden property="installationAddressPojo.instLocalityId" name="crmCapForm" value="${crmCapForm.installationAddressPojo.instLocalityId}"/>
<html:hidden property="installationAddressPojo.instSocietyId" name="crmCapForm" value="${crmCapForm.installationAddressPojo.instSocietyId}"/>
<html:hidden property="additionalDetailsPojo.recordId" name="crmCapForm" value="${crmCapForm.additionalDetailsPojo.recordId}"/>
<html:hidden property="installationAddressPojo.recordId" name="crmCapForm" value="${crmCapForm.installationAddressPojo.recordId}"/>
<html:hidden property="billingAddressPojo.recordId" name="crmCapForm" value="${crmCapForm.billingAddressPojo.recordId}"/>
<html:hidden property="customerDetailsPojo.recordId" name="crmCapForm" value="${crmCapForm.customerDetailsPojo.recordId}"/>
<html:hidden property="documentDetailsPojo.recordId" name="crmCapForm" value="${crmCapForm.documentDetailsPojo.recordId}"/>
<html:hidden property="telecommunicationPayment.paymentId" name="crmCapForm" value="${crmCapForm.telecommunicationPayment.paymentId}"/>
<html:hidden property="nationalityDetailsPojo.recordId" name="crmCapForm" value="${crmCapForm.nationalityDetailsPojo.recordId}"/>
<html:hidden property="teleservicesPayment.paymentId" name="crmCapForm" value="${crmCapForm.teleservicesPayment.paymentId}"/>
<html:hidden property="planDetailsPojo.recordId" name="crmCapForm" value="${crmCapForm.planDetailsPojo.recordId}"/>
<html:hidden property="orderDetailsPojo.recordId" name="crmCapForm" value="${crmCapForm.orderDetailsPojo.recordId}"/>
<html:hidden property="appointmentPojo.appointmentId" name="crmCapForm" value="${crmCapForm.appointmentPojo.appointmentId}"/>
<html:hidden property="customerDetailsPojo.crfStage" name="crmCapForm" value="${crmCapForm.customerDetailsPojo.crfStage}"/>
<html:hidden property="customerDetailsPojo.crfPreviousStage" name="crmCapForm" value="${crmCapForm.customerDetailsPojo.crfPreviousStage}"/>
<html:hidden property="customerDetailsPojo.status" name="crmCapForm" value="${crmCapForm.customerDetailsPojo.status}"/>
<html:hidden property="customerDetailsPojo.isrReferenceNo" name="crmCapForm" value="${crmCapForm.customerDetailsPojo.isrReferenceNo}"/>
<html:hidden property="customerDetailsPojo.crfReferenceNo" name="crmCapForm" value="${crmCapForm.customerDetailsPojo.crfReferenceNo}"/>
<html:hidden property="customerDetailsPojo.npId" name="crmCapForm" value="${crmCapForm.customerDetailsPojo.npId}"/>
<html:hidden property="customerDetailsPojo.spId" name="crmCapForm" value="${crmCapForm.customerDetailsPojo.spId}"/>
<html:hidden property="customerDetailsPojo.customerId" name="crmCapForm" value="${crmCapForm.customerDetailsPojo.customerId}"/>
<html:hidden property="appointmentPojo.appointmentType" name="crmCapForm" value="I&A"/>
<html:hidden property="planService" name="crmCapForm" value="${crmCapForm.planService}" styleId="hiddenPlanServiceId"/>
<html:hidden property="minimumSecurityDeposit" name="crmCapForm" value="${crmCapForm.minimumSecurityDeposit}" styleId="miniSecurity"/>
<html:hidden property="minimumRentalCharge" name="crmCapForm" value="${crmCapForm.minimumRentalCharge}" styleId="miniRental"/>
<html:hidden property="miniTelesolutionAmount" name="crmCapForm" value="${crmCapForm.miniTelesolutionAmount}" styleId="miniTelesolutionAmount"/>
<html:hidden property="miniTotalRental" name="crmCapForm" value="${crmCapForm.miniTotalRental}" styleId="miniTotalRental"/>
<html:hidden property="aadharNumberPojo.aadharAdd" name="crmCapForm" value="${crmCapForm.aadharNumberPojo.aadharAdd}"/>
<html:hidden property="aadharNumberPojo.aadharNumber" name="crmCapForm" value="${crmCapForm.aadharNumberPojo.aadharNumber}"/>
<html:hidden property="aadharNumberPojo.instAddress" name="crmCapForm" value="${crmCapForm.aadharNumberPojo.instAddress}"/>
<html:hidden property="aadharNumberPojo.propertyDetails" name="crmCapForm" value="${crmCapForm.aadharNumberPojo.propertyDetails}"/>
<logic:equal name="crmCapForm" property="customerDetailsPojo.product" value="EOC">
	<html:hidden name="crmCapForm" property="orderDetailsPojo.cpeStatus" value="NR" />
</logic:equal>
<logic:equal name="crmCapForm" property="customerDetailsPojo.product" value="BB">
	<html:hidden name="crmCapForm" property="orderDetailsPojo.cpeStatus" value="CO" />
</logic:equal>
<input type="hidden" id="addOnrental" name="addOnrental" />
<html:hidden property="aadharNumberPojo.aadharNumber" name="crmCapForm" value="${crmCapForm.aadharNumberPojo.aadharNumber}" styleId="ecafAadhar"/>
 <div class="section">
   <h2>Installation and Activation</h2>
   <span id='SAVE_SUBMIT_ERROR_SPAN' style='font: 11px arial; color: #e70000;'></span>
   <logic:empty name="crmCapForm" property="bussinessPartners">
   <span style='font: 11px arial; color: #e70000;'>Warning! you are not associated with any Business Partner.</span>
   </logic:empty>
   <div class="inner_section blueborder1" style="padding: 10px; margin-top: 40px;">
    	<ul class="i_aTabs">
	   		<li><a href="#customerBasicInformation" class="active tabOne">Customer Basic Information</a></li>
	        <li><a href="#orderPaymentDetails" class="tabTwo">Order & Payment Details</a></li>
	        <li><a href="#documentDetails"class="tabThree">Document Details</a></li>
	        <li><a href="#additionalInformation" class="tabFour">Additional Information</a></li>
	   	</ul>
   <!-- Success Messages Starts -->
	<div class="success_message" >
		<logic:messagesPresent message="true">
   		<html:messages id="message" message="true">
		<bean:write name="message" />
		</html:messages>
		</logic:messagesPresent>
	</div>
<!-- Success Messages Ends -->
	<div class="error_message" id="error"><html:errors /></div> <!-- for error messages -->
<!--  customerBasicInformation-->
   <div id="customerBasicInformation" class="clear tab_content">
   <!-- left customerBasicInformation-->
    <div class="INA floatl marginleft10">
      <div class=" paddingBottom30" id="customerDetails">
    <h4>CAF Details</h4>
    
     <p class="floatl "><strong>CAF Number</strong>
     	 <html:text styleClass="Lmstextbox" property="customerDetailsPojo.crfId" name="crmCapForm" maxlength="8" readonly="true" styleId="crfId_Text"></html:text>
    </p>
    
    <p class="floatl marginleft30"><strong>Service Name</strong>
				<span class="lmsshowTextbox">
			<logic:notEmpty name="crmCapForm" property="customerDetailsPojo.product">
						<bean:write name="crmRoles" property="displayEnum(Product,${crmCapForm.customerDetailsPojo.product})" scope="session" />
						<html:hidden name="crmCapForm" property="customerDetailsPojo.product" styleId="hiddProduct"/>
			</logic:notEmpty>  
			</span>
   </p>
   <p class="floatl marginleft30"><strong>Service Type</strong>
				<span class="lmsshowTextbox">
			<logic:notEmpty name="crmCapForm" property="customerDetailsPojo.serviceType">
						<bean:write name="crmRoles" property="displayEnum(ServiceType,${crmCapForm.customerDetailsPojo.serviceType})" scope="session" />						
			</logic:notEmpty>  
			<html:hidden name="crmCapForm" property="customerDetailsPojo.serviceType" styleId="serviceType"  />
			</span>
   </p>
    <p class="floatl marginleft30"><strong> Date on CAF</strong>
    	<html:text property="customerDetailsPojo.displayCrfDate" styleClass="calenderDateOnCAF tcalInput" styleId="dateOnCRF"  readonly="true" name="crmCapForm"></html:text><font></font>		
    </p>
    <br class="clear"/>
    </div>
     <!-------- Customer Personal Details------->
    <div class=" paddingBottom30 clear" id="customerPersonalDetails">
    <h4>Customer Details
    <c:if test="${crmCapForm.customerDetailsPojo.crfStage ne 'FTR' }">
      <a href="javascript:;" class="yellow_button floatr margintop7 reset" id="resetCustomerPersonalDetails">Reset</a>
    </c:if> 
    <a href="javascript:;" class="yellow_button floatr margintop7 marginright5 reset" onclick="saveForm();">Save</a></h4>
 <p class="floatl clear">    
    <strong>Connection Type</strong>
	    <span class="dropdownWithoutJs">
	     	<html:select property="customerDetailsPojo.connectionType" name="crmCapForm" styleId="connectionTypeCRF">
	             <html:option value="0">Select Connection Type</html:option>
	             <html:optionsCollection name="crmCapForm" property="connectionTypeList" label="contentName" value="contentValue" />
	         </html:select>
	     </span>
     <font></font>
 </p> 
 <div id="NAforOnlyIndividual" class="hide1">
    <p class="floatl clear">
    <strong class="headStrong"> Individual/Proprietorship</strong>
    <logic:notEmpty name="crmCapForm" property="aadharNumberPojo.aadharAdd">
    <span class="floatl "><strong> First Name</strong>
     	<html:text styleClass="Lmstextbox" property="customerDetailsPojo.custFname" name="crmCapForm" styleId="firstNameCRF" readonly="true"></html:text><font></font>
    </span>
      <span class="floatl marginleft15 "><strong> Last Name</strong>
      	 <html:text styleClass="Lmstextbox"  property="customerDetailsPojo.custLname" name="crmCapForm" styleId="lastNameCRF" readonly="true"></html:text><font></font>
      </span>
    </logic:notEmpty>
    <logic:empty  name="crmCapForm" property="aadharNumberPojo.aadharAdd">
     <span class="floatl "><strong> First Name</strong>
     	<html:text styleClass="Lmstextbox" property="customerDetailsPojo.custFname" name="crmCapForm" styleId="firstNameCRF" maxlength="50" onkeyup="javascript:changeToUpperCase(this)"></html:text><font></font>
    </span>
      <span class="floatl marginleft15 "><strong> Last Name</strong>
      	 <html:text styleClass="Lmstextbox"  property="customerDetailsPojo.custLname" name="crmCapForm" styleId="lastNameCRF" maxlength="50" onkeyup="javascript:changeToUpperCase(this)"></html:text><font></font>
      </span>
      </logic:empty>
    </p>
  </div>  
  <div id="NAforOnlyCompany" class="hide1">
    <p class="floatl clear">
	    <strong class="headStrong">Company/Organization/Society</strong>
	    <logic:notEmpty name="crmCapForm" property="aadharNumberPojo.aadharAdd">
	     <span class="floatl "><strong> Company Name</strong>
	     	<html:text styleClass="textbox" property="customerDetailsPojo.custFname" name="crmCapForm" styleId="companyNameCRF" readonly="true"></html:text><font></font><%-- onblur="javascript:validateCompany(this);" --%>
	    </span>
	    </logic:notEmpty>
	    <logic:empty  name="crmCapForm" property="aadharNumberPojo.aadharAdd">
	    <span class="floatl "><strong> Company Name</strong>
	     	<html:text styleClass="textbox" property="customerDetailsPojo.custFname" name="crmCapForm" styleId="companyNameCRF" maxlength="200" onkeyup="javascript:changeToUpperCase(this);"></html:text><font></font><%-- onblur="javascript:validateCompany(this);" --%>
	    </span>
	    </logic:empty>
    </p>
  </div>  
  <p class="floatl ">
	<strong class="headStrong">&nbsp;</strong>
	    <span class="floatl marginleft10"><strong class="marginleft30"> Registered Mobile No.</strong>[+91]
	    	<html:text styleClass="Lmstextbox" property="customerDetailsPojo.rmn" name="crmCapForm" styleId="registeredMobileCRF" maxlength="10" onclick="removeZeroFromNumber(this);" onfocus="removeZeroFromNumber(this);" onkeyup="javascript:ValidatenReplaceAlphanumeric(this);"></html:text><font></font>
	    </span>
	     <%--  <span class="floatl marginleft10 "><strong class="marginleft30">Registered Telephone No.</strong>[+91]
	     	<html:text styleClass="Lmstextbox" property="customerDetailsPojo.rtn" name="crmCapForm" styleId="registeredTelephoneCRF" maxlength="10" onclick="removeZeroFromNumber(this);" onfocus="removeZeroFromNumber(this);" onkeyup="javascript:ValidatenReplaceAlphanumeric(this);"></html:text><font></font>
	    </span>--%>
  </p>
   
 <p class="floatl clear">
     <span class="floatl"><strong>Registered Email ID</strong>
     	<html:text styleClass="Lmstextbox" property="customerDetailsPojo.custEmailId" name="crmCapForm" styleId="emailIdCRF" maxlength="256"></html:text><font></font>
    </span>
    <%--<span class="floatl marginleft15 "><strong>Alt. Email ID</strong>
    	<html:text styleClass="Lmstextbox" property="customerDetailsPojo.altEmailId" name="crmCapForm" styleId="altEmailIdCRF" maxlength="256"></html:text><font></font>
    </span>--%>
    <span class="floatl marginleft10 "><strong class="marginleft30">Alt. Mobile No.</strong>[+91]
    	<html:text styleClass="Lmstextbox" property="customerDetailsPojo.custMobileNo" name="crmCapForm" styleId="mobileCRF" maxlength="10" onclick="removeZeroFromNumber(this);" onfocus="removeZeroFromNumber(this);" onkeyup="javascript:ValidatenReplaceAlphanumeric(this);"></html:text><font></font>
   </span>
   <span class="floatl marginleft15"><strong> PAN / GIR No.</strong>
     	<html:text styleClass="Lmstextbox" property="customerDetailsPojo.custPanGirNo" name="crmCapForm" styleId="panCRF" maxlength="10" onkeyup="javascript:changeToUpperCase(this)"></html:text><font></font>
    </span>
 </p>
 <p class="floatl clear">
    <strong class="headStrong"> Father's/Husband's Name </strong>
    <logic:notEmpty name="crmCapForm" property="aadharNumberPojo.aadharAdd">
     <span class="floatl"><strong> First Name</strong>
     	<html:text styleClass="Lmstextbox"  property="customerDetailsPojo.fhFname" name="crmCapForm" styleId="fatherFirstNameCRF" readonly="true"></html:text><font></font>
    </span>
    <span class="floatl marginleft15"><strong> Last Name</strong>
    	<html:text styleClass="Lmstextbox" property="customerDetailsPojo.fhLname" name="crmCapForm" styleId="fatherLastNameCRF" readonly="true"></html:text><font></font>
    </span>
    </logic:notEmpty>
    <logic:empty name="crmCapForm" property="aadharNumberPojo.aadharAdd">
    <span class="floatl"><strong> First Name</strong>
     	<html:text styleClass="Lmstextbox"  property="customerDetailsPojo.fhFname" name="crmCapForm" styleId="fatherFirstNameCRF" maxlength="45" onkeyup="javascript:changeToUpperCase(this)"></html:text><font></font>
    </span>
    <span class="floatl marginleft15"><strong> Last Name</strong>
    	<html:text styleClass="Lmstextbox" property="customerDetailsPojo.fhLname" name="crmCapForm" styleId="fatherLastNameCRF" maxlength="45" onkeyup="javascript:changeToUpperCase(this)"></html:text><font></font>
    </span>
    </logic:empty>
 </p>
 
 <p class="floatl marginleft15">
    <strong class="headStrong">Aadhar Card</strong>
     <span class="floatl"><strong> Aadhar No.</strong>
     	<html:text styleClass="Lmstextbox"  property="customerDetailsPojo.aadharNo" name="crmCapForm" styleId="aadharNo" maxlength="12" onkeyup="javascript:ValidatenReplaceAlphanumeric(this);"></html:text><font></font>
    </span>
   
 </p>
 
  <!-- NA for Individual & Proprietorship.  -->  
 <div id="NAforIndividualProprietorship" class="hide1">
   <p class="floatl clear">
     <strong class="headStrong"> Organization Coordinator Name </strong>
     <logic:notEmpty name="crmCapForm" property="aadharNumberPojo.aadharAdd">
     <span class="floatl"><strong> First Name</strong>
     	<html:text styleClass="Lmstextbox" property="customerDetailsPojo.orgCordFname" name="crmCapForm" styleId="orgCoordFirstNameCRF" readonly="true"></html:text><font></font>
    </span>
    <span class="floatl marginleft15"><strong> Last Name</strong>
    	<html:text styleClass="Lmstextbox" property="customerDetailsPojo.orgCordLname" name="crmCapForm" styleId="orgCoordLastNameCRF" readonly="true"></html:text><font></font>
    </span>
     </logic:notEmpty>
     <logic:empty name="crmCapForm" property="aadharNumberPojo.aadharAdd">
     <span class="floatl"><strong> First Name</strong>
     	<html:text styleClass="Lmstextbox" property="customerDetailsPojo.orgCordFname" name="crmCapForm" styleId="orgCoordFirstNameCRF" maxlength="45" onkeyup="javascript:changeToUpperCase(this)"></html:text><font></font>
    </span>
    <span class="floatl marginleft15"><strong> Last Name</strong>
    	<html:text styleClass="Lmstextbox" property="customerDetailsPojo.orgCordLname" name="crmCapForm" styleId="orgCoordLastNameCRF" maxlength="45" onkeyup="javascript:changeToUpperCase(this)"></html:text><font></font>
    </span>
     </logic:empty>
   </p>
   <p class="floatl marginleft15">
     <strong class="headStrong"> Authorized Signatory Name &amp; Designation </strong>
     <span class="floatl"><strong> First Name</strong>
     	<html:text styleClass="Lmstextbox" property="customerDetailsPojo.authSignFname" name="crmCapForm" styleId="authorizedSignatoryFirstNameCRF" maxlength="45" onkeyup="javascript:changeToUpperCase(this)"></html:text><font></font>
    </span>
    <span class="floatl marginleft15"><strong> Last Name</strong>
    	<html:text styleClass="Lmstextbox" property="customerDetailsPojo.authSignLname" name="crmCapForm" styleId="authorizedSignatoryLastNameCRF" maxlength="45" onkeyup="javascript:changeToUpperCase(this)"></html:text><font></font>
    </span>
    <span class="floatl marginleft15"><strong> Designation</strong>
    	<html:text styleClass="Lmstextbox" property="customerDetailsPojo.authSignDesg" name="crmCapForm" styleId="authorizedSignatoryDesignationCRF" maxlength="45" onkeyup="javascript:changeToUpperCase(this)"></html:text><font></font>
    </span>
  </p>
 </div>
     <!-- NA for Individual & Proprietorship.  -->
    <!-- A for Individual  -->
   <div id="AforIndividual" class="hide1">
 <p class="floatl clear">
   <span class="floatl"><strong> Gender</strong>
     <LABEL class="label_radio" for="male">
     	<html:radio styleId="male"  property="customerDetailsPojo.custGender" value="M" name="crmCapForm"></html:radio>Male
     </LABEL> 
     <LABEL class="label_radio" for="female">
     	<html:radio styleId="female"  property="customerDetailsPojo.custGender" value="F" name="crmCapForm"></html:radio>Female
     </LABEL>
  </span>
  <span class="floatl marginleft15"><strong> Birth Date</strong>
  <logic:notEmpty name="crmCapForm" property="aadharNumberPojo.aadharAdd">
  	<html:text styleClass="Lmstextbox" readonly="true" property="customerDetailsPojo.displayCustDob" name="crmCapForm" styleId="birthdate"></html:text>
  </logic:notEmpty>
  <logic:empty name="crmCapForm" property="aadharNumberPojo.aadharAdd">
  	<html:text styleClass="Lmstextbox calender" readonly="true" property="customerDetailsPojo.displayCustDob" name="crmCapForm" styleId="birthdate"></html:text>
  	<font class="errorTextbox"></font>
  </logic:empty>
  </span>
  <span class="floatl marginleft30"><strong> Nationality</strong>
   <logic:notEmpty name="crmCapForm" property="aadharNumberPojo.aadharAdd">
 	<html:text styleClass="Lmstextbox" property="customerDetailsPojo.nationality" name="crmCapForm" styleId="nationalityTypeId" readonly="true"></html:text>
   </logic:notEmpty>
   <logic:empty name="crmCapForm" property="aadharNumberPojo.aadharAdd">
   <span class="LmsdropdownWithoutJs">
     <html:select property="customerDetailsPojo.nationality" name="crmCapForm" styleId="nationalityTypeId">
            <%-- <html:option value="0">Select Nationality Type</html:option>
              <html:optionsCollection name="crmCapForm" property="nationalityType" label="categoryValue" value="categoryValue" /> --%>
             <html:option value="Indian">Indian</html:option>
             <html:option value="Other">Other</html:option>
         </html:select>   
    </span>
   </logic:empty>
	</span>
	<span class="floatl marginleft15" id="otherNationality"><strong> Please Specify</strong>
    	<html:text styleClass="Lmstextbox" property="nationalityDetailsPojo.nationality" name="crmCapForm" styleId="nationalityValueId" maxlength="50"></html:text><font></font>
    </span>
 </p>
 </div>
    <!-- A for Individual  -->
    <br class="clear" />
</div>
     <!-------- Customer Personal Details------->
     <!----------   Other Nationality Details -------------------->
 <div id="OtherNationalityDetails" class="clear createBorder hide1 marginBottom30">
  <h4>Passport Details <a href="javascript:;" class="yellow_button floatr margintop7" id="resetPassportDetails">Reset</a><a href="javascript:;" class="yellow_button floatr margintop7 marginright5 reset" onclick="saveForm();">Save</a></h4>
     
    <p class="floatl "><strong> Passport No.</strong>
      	<html:text property="nationalityDetailsPojo.passportNo" styleClass="Lmstextbox" styleId="passportNoCRF" name="crmCapForm" maxlength="15"></html:text><font></font>
    </p>
    <p class="floatl marginleft15"><strong>Passport Valid Up to</strong>
    	<html:text property="nationalityDetailsPojo.displayPassportValidity" styleClass="tcal tcalInput" styleId="passportValidUpToCRF" name="crmCapForm" readonly="true"></html:text>
    	<font class="errorTextbox"></font>
   </p>

  <p class="floatl marginleft15"><strong> Visa Type</strong>
   <span class="LmsdropdownWithoutJs">
   		<html:select property="nationalityDetailsPojo.visaType" name="crmCapForm" styleId="visaTypeCRF">
             <html:option value="0">Select Visa Type</html:option>
             <html:optionsCollection name="crmCapForm" property="visaTypeList" label="contentName" value="contentValue" />
         </html:select>
   </span>
   <font></font>
  </p>
    
     <p class="floatl marginleft15"><strong>Visa Valid Up to</strong>
     	<html:text property="nationalityDetailsPojo.displayVisaValidity" styleClass="tcal tcalInput" styleId="visaValidUpToCRF" name="crmCapForm" readonly="true"></html:text>
     	<font class="errorTextbox"></font>
    </p>
      <strong class="headStrong paddingTop10 clear">Details of Local Reference </strong>
      
     <p class="floatl"><strong> First Name</strong>
     	<html:text property="nationalityDetailsPojo.localCpFname" styleClass="Lmstextbox" styleId="passportFirstNameCRF" name="crmCapForm" maxlength="45" onkeyup="javascript:changeToUpperCase(this)"></html:text><font></font>
    </p>
    
      <p class="floatl marginleft15"><strong> Last Name</strong>
      	<html:text property="nationalityDetailsPojo.localCpLname" styleClass="Lmstextbox" styleId="passportLastNameCRF" name="crmCapForm" maxlength="45" onkeyup="javascript:changeToUpperCase(this)"></html:text><font></font>
     </p>
    
    <p class="floatl marginleft15"><strong class="marginleft30"> Mobile No.</strong>[+91]
    	<html:text property="nationalityDetailsPojo.localCpMobileNo" styleClass="Lmstextbox" styleId="passportMobileNoCRF" name="crmCapForm" maxlength="10" onclick="removeZeroFromNumber(this);" onfocus="removeZeroFromNumber(this);" onkeyup="javascript:ValidatenReplaceAlphanumeric(this);"></html:text><font></font>
   </p>
     <!-- Paspport local reference: address -->
   
   <p class="floatl clear "><strong>Address</strong>
     	<html:textarea property="nationalityDetailsPojo.localCpAdd" styleClass="LmsRemarkstextarea" styleId="passportAddressCRF" name="crmCapForm" cols="80" rows="3" onkeyup="javascript:changeToUpperCase(this)"></html:textarea><font></font>
   </p>
   <br class="clear" />
 </div>
  <!----------   Other Nationality Details -------------------->

    <!-------- Installation address------->
 <div class=" paddingBottom30 clear" id="installationAddress">
 	<html:hidden property="installationAddressPojo.addressType" name="crmCapForm" value="IN"/>
    <h4>Installation Address
    <c:if test="${crmCapForm.customerDetailsPojo.crfStage ne 'FTR' }">
      <a href="javascript:;" class="yellow_button floatr margintop7" id="resetInstallationAddress">Reset</a>
    </c:if> 
    <a href="javascript:;" class="yellow_button floatr margintop7 marginright5 reset" onclick="saveForm();">Save</a>
    <a href="crmCap.do?method=checkFeasibility&Product=${crmCapForm.customerDetailsPojo.product}" class="floatr marginright5 yellow_button margintop7 iframe1">Check Feasibility</a>
    </h4>
    
  <p class="floatl"><strong>Address Line 1</strong>
  	  <html:text styleClass="textbox " property="installationAddressPojo.addLine1" name="crmCapForm" styleId="instAddLine1" maxlength="60" onblur="javascript:changeToUpperCase(this)"></html:text>
  </p>
  <p class="floatl marginleft15"><strong>Address Line 2</strong>
  	  <html:text styleClass="textbox " property="installationAddressPojo.addLine2" name="crmCapForm" styleId="instAddLine2" maxlength="60" onkeyup="javascript:changeToUpperCase(this)"></html:text>
 </p>
  <p class="floatl marginleft15"><strong>Address Line 3</strong>
  	<html:text styleClass="textbox " property="installationAddressPojo.addLine3" name="crmCapForm" styleId="instAddLine3" maxlength="60" readonly="true" onkeyup="javascript:changeToUpperCase(this)"></html:text>
  </p>
  <p  class="floatl clear "><strong>Landmark</strong>
  	<html:text styleClass="textbox" property="installationAddressPojo.landmark" name="crmCapForm" maxlength="45" onkeyup="javascript:changeToUpperCase(this)"></html:text>
  </p>
   <p  class="floatl marginleft15"><strong>PIN Code</strong>
   	<html:text styleClass="Lmstextbox" property="installationAddressPojo.pincode" name="crmCapForm" styleId="pinCodeInstalltionCRF" maxlength="6" onclick="removeZeroFromNumber(this);" onfocus="removeZeroFromNumber(this);" onkeyup="javascript:ValidatenReplaceAlphanumeric(this);"></html:text><font></font>
  </p>   
  <p  class="floatl marginleft15"><strong>Property Details</strong>   
     <LABEL class="label_radio" for="rentedPropertyInstalltionCRF">
     	<html:radio property="installationAddressPojo.propertyDetails" name="crmCapForm" value="Rented" styleId="rentedPropertyInstalltionCRF"></html:radio>Rented
     </LABEL> 
     <LABEL class="label_radio" for="ownedPropertyInstalltionCRF">
     	<html:radio property="installationAddressPojo.propertyDetails" name="crmCapForm" value="Owned" styleId="ownedPropertyInstalltionCRF"></html:radio>Owned
     </LABEL>
 </p>
     <br class="clear" />
    </div>
     <!-------- Installation address------->
      <!-------- billing address------->
    <div class=" paddingBottom30 clear" id="billingAddress">
    	<html:hidden property="billingAddressPojo.addressType" name="crmCapForm" value="BL"/>
    <h4>Billing Address
    <c:if test="${crmCapForm.customerDetailsPojo.crfStage ne 'FTR' }">
      <a href="javascript:;" class="yellow_button floatr margintop7" id="resetBillingAddress">Reset</a>
    </c:if> 
    <a href="javascript:;" class="yellow_button floatr margintop7 marginright5 reset" onclick="saveForm();">Save</a></h4>
    
   <p class="floatl ">
       <label class="label_check margintop5 width200" for="sameAsInstallation">
       	<html:checkbox property="billingAddressPojo.sameAsInstAddr" name="crmCapForm" value="true" styleId="sameAsInstallation" onchange="disableBillingForm(this);">Same as installation Address</html:checkbox>
      </label> 
  </p>
  <p  class="floatl clear"><strong>Address Line 1</strong>
  	<html:text styleClass="textbox " property="billingAddressPojo.addLine1" name="crmCapForm" maxlength="60" styleId="billAddLine1" onkeyup="javascript:changeToUpperCase(this)"></html:text>
  </p>
  <p class="floatl marginleft15"><strong>Address Line 2</strong>
  	<html:text styleClass="textbox " property="billingAddressPojo.addLine2" name="crmCapForm" maxlength="60" styleId="billAddLine2" onkeyup="javascript:changeToUpperCase(this)"></html:text>
 </p>
  <p class="floatl marginleft15"><strong>Address Line 3</strong> 
  	<html:text styleClass="textbox " property="billingAddressPojo.addLine3" name="crmCapForm" maxlength="60" styleId="billAddLine3" readonly="true" onkeyup="javascript:changeToUpperCase(this)"></html:text>
 </p>
 <div id="DIVStateCityBilling" class="${crmCapForm.billingAddressPojo.sameAsInstAddr ? 'hide1' : ''}">
 <p class="floatl clear"><strong> State</strong>
	 <span class="LmsdropdownWithoutJs">
   	   <html:select name="crmCapForm" property="billingAddressPojo.stateName" styleId="billingState" onchange="javascript:populateCityByState('billingCity',this.value);">
		 	<option value="0">Please Select</option>
		 	<logic:notEmpty  name="crmCapForm" property="statePojoList">
		  		<html:optionsCollection name="crmCapForm" property="statePojoList" label="stateName" value="stateName" />
	  		</logic:notEmpty>
	  	</html:select>
     </span>
 </p>
 <p class="floatl marginleft15"><strong> City</strong>
    <span class="LmsdropdownWithoutJs">    
      <html:select name="crmCapForm" property="billingAddressPojo.cityName" styleId="billingCity" onchange="updateBillAddL3();" >
	     <html:option value="0">Please Select</html:option>
	     <logic:notEmpty name="crmCapForm" property="cityList">
	     	<html:optionsCollection name="crmCapForm" property="cityList" label="cityName" value="cityName" />
	     </logic:notEmpty>
	  </html:select>
   	</span> 
 </p>
 </div>
  <p  class="floatl clear "><strong>Landmark</strong>
  	<html:text styleClass="textbox" property="billingAddressPojo.landmark" name="crmCapForm" styleId="billAddLandMark" maxlength="45" onkeyup="javascript:changeToUpperCase(this)"></html:text>
 </p>
 <p  class="floatl marginleft15"><strong>PIN Code</strong>
   	<html:text styleClass="Lmstextbox" property="billingAddressPojo.pincode" name="crmCapForm" styleId="pinCodeBillingCRF" maxlength="6" onclick="removeZeroFromNumber(this);" onfocus="removeZeroFromNumber(this);" onkeyup="javascript:ValidatenReplaceAlphanumeric(this);"></html:text><font></font>
 </p>   
     <br class="clear" />
    </div>
 <logic:notEmpty name="crmCapForm" property="aadharNumberPojo.aadharAdd">
	<div class=" paddingBottom30 clear">
	  <h4>Aadhar Address</h4>
		<ul class="tabcontentUL">
		   <li><label>AD: <bean:write name="crmCapForm" property="aadharNumberPojo.aadharAdd" /></label></li>
		</ul>
		<ul class="tabcontentUL clear">
		   <li><label>IN: <bean:write name="crmCapForm" property="aadharNumberPojo.instAddress" /></label></li>
		</ul>								
	</div>							
 </logic:notEmpty>
     <!-------- billing address------->
    
</div>
      <div class="clear textAC margintop20">
      	<a href="#orderPaymentDetails" class="pageNav next" onclick="changeTab('op')">Next</a>
      </div>
      
     <!-- right customerBasicInformation-->
  <div class="floatr inner_right positionR10">
    <p class="buttonPlacement">
    <logic:equal name="crmRoles" property="available(create_ina)" value="true" scope="session">
        <a href="#" id="customerBasicInfo_Save" class="main_button" onclick="saveForm();"><span>Save</span></a>
    </logic:equal>
    <c:if test="${crmCapForm.customerDetailsPojo.crfStage ne 'FTR'}">
     <a href="javascript:;" id="customerBasicInfo_Reset" class="main_button_multiple  marginleft15"><span>All <br />Reset</span></a>
     </c:if>
     </p>
  </div>
   <br class="clear" />
     
   </div>
<!--  customerBasicInformation-->
 <!--  orderPaymentDetails-->  
   <div id="orderPaymentDetails"  class="clear tab_content">
 <!-- left orderPaymentDetails-->  
    <div class="INA floatl marginleft10" id="serviceDetails">
     
     
     <!-- Plan details -->
    <div class=" paddingBottom30" id="planDetails">
    <h4>Plan Details
    <c:if test="${crmCapForm.customerDetailsPojo.crfStage ne 'FTR' }">
      <a href="javascript:;" class="yellow_button floatr margintop7" id="resetPlanDetails">Reset</a>
    </c:if> 
    <a href="javascript:;" class="yellow_button floatr margintop7 marginright5 reset" onclick="saveForm();">Save</a></h4>
 
  <p class="floatl clear">
     <strong> Plan Type </strong>
     <LABEL class="label_radio">
     	<html:radio property="planType" value="DEMO" name="crmCapForm"></html:radio>DEMO
     </LABEL> 
     <LABEL class="label_radio" >
     	<html:radio  property="planType" value="TnB" name="crmCapForm"></html:radio>TnB
     </LABEL>
     <LABEL class="label_radio">
     	<html:radio property="planType" value="APP" name="crmCapForm"></html:radio>APP
     </LABEL> 
     <LABEL class="label_radio" >
     	<html:radio  property="planType" value="PAID" name="crmCapForm"></html:radio>PAID
     </LABEL>
     <LABEL class="label_radio" >
     	<html:radio  property="planType" value="MBO" name="crmCapForm"></html:radio>MBO
     </LABEL>
  <logic:equal name="crmRoles" property="available(view_foc_plan)" value="true" scope="session">
	  <LABEL class="label_radio" >
     	<html:radio  property="planType" value="FOC" name="crmCapForm"></html:radio>FOC
     </LABEL>
  </logic:equal>
 </p> 
    
  <p class="floatl clear"><strong>Base Plan Name</strong>
     <span class="dropdownWithoutJs">
     <logic:notEmpty property="planType" name="crmCapForm">
     	<html:select property="planDetailsPojo.basePlanCode" name="crmCapForm" styleId="basePlanNameCRF" onchange="setPlanAmounts('securityChrgeId', 'rentalChargeId','amountId', 'total', this.value);">
             <html:option value="0">Select Base Plan Name</html:option>
             <html:optionsCollection name="crmCapForm" property="basePlanNames" label="planName" value="planCode" />
          </html:select>
     </logic:notEmpty>
     <logic:empty property="planType" name="crmCapForm">
    	 <html:select property="planDetailsPojo.basePlanCode" name="crmCapForm" styleId="basePlanNameCRF" onchange="setPlanAmounts('securityChrgeId', 'rentalChargeId','amountId', 'total', this.value);">
             <html:option value="0">Select Base Plan Name</html:option>
          </html:select>
     </logic:empty>
   </span>
 </p>
  <p  class="floatl marginleft30"><strong>Add-on DUL Value</strong>
    <span class="dropdownWithoutJs">
    	<html:select property="planDetailsPojo.addOnPlanCode" name="crmCapForm" styleId="addOnDulValueCRF" onchange="addToPlanAmounts('securityChrgeId', 'rentalChargeId','total', this.value);" disabled="${crmCapForm.addOnNotAllowed}" styleClass="${crmCapForm.addOnNotAllowed eq true? 'gray_text':''}">
            <html:option value="">Select Add-on DUL Value</html:option>
            <html:optionsCollection name="crmCapForm" property="addonPlanNames" label="planName" value="planCode" />
       </html:select>
   </span>
  </p>
  <p class="floatl marginleft30">
    <strong>Billing Preferences</strong>
    <span class="dropdownWithoutJs">
    	<html:select property="planDetailsPojo.billMode" name="crmCapForm" styleId="billingPreferencesCRF">
             <%--<html:option value="0">Select Billing Preferences</html:option> --%>
             <html:optionsCollection name="crmCapForm" property="billingPreferences" label="contentName" value="contentValue" />
          </html:select>
    </span>
  </p>
  <p class="floatl clear"><strong>Type of Application / Usage of Service</strong>
    <span class="dropdownWithoutJs">
    	<html:select property="planDetailsPojo.usageType" name="crmCapForm" styleId="usageType">
    	   <html:option value="0">Select Usage of Service</html:option>
    	   <html:option value="O">Own use</html:option>
    	   <html:option value="R">Resale</html:option>
    	   <html:option value="C">Cyber Cafe</html:option>
    	</html:select>
    </span>
 </p>
   <p class="floatl marginleft30">
    <strong>Brand Name</strong>
      <html:text property="customerDetailsPojo.brand" name="crmCapForm" styleClass="Lmstextbox" styleId="brandNameID" maxlength="20" readonly="true"></html:text>      
  </p>
    <br class="clear"/>
 </div>
      <!-------- Payment Information ------->
 <div class=" paddingBottom30 clear" id="paymentInformation">
    <h4>Teleservices Payment
    <c:if test="${crmCapForm.customerDetailsPojo.crfStage ne 'FTR' }">
      <a href="javascript:;" class="yellow_button floatr margintop7" id="resetPaymentInformation">Reset</a>
    </c:if> 
    <a href="javascript:;" class="yellow_button floatr margintop7 marginright5 reset" onclick="saveForm();">Save</a></h4>
    <html:hidden property="teleservicesPayment.entityType" name="crmCapForm" value="T"/>
    <html:hidden property="teleservicesPayment.serviceType" name="crmCapForm" value="${crmCapForm.customerDetailsPojo.serviceType}"/>
    <html:hidden property="teleservicesPayment.status" name="crmCapForm" value="A"/>
    <html:hidden property="teleservicesPayment.installationStatus" name="crmCapForm" value="PreIns"/>
    <c:if test="${crmCapForm.customerDetailsPojo.product eq 'EOC' and crmCapForm.customerDetailsPojo.serviceType eq 'PR' }">
	<p class="floatl marginright15"><strong> Security Deposit</strong>
			<html:text styleClass="Lmstextbox"  property="teleservicesPayment.securityCharges" name="crmCapForm" maxlength="11" styleId="securityChrgeId" onkeyup="javascript:ValidatenReplaceAlphanumericnDot(this);sum();" onclick="removeZeroFromNumber(this);" onfocus="removeZeroFromNumber(this);"></html:text>
			<font class="errorTextbox"></font>
	</p>
    </c:if>
	<p class="floatl"><strong>${crmCapForm.customerDetailsPojo.serviceType eq 'PR' ? 'Rental Charge' : 'Advance Payment' } </strong>        
		<html:text property="teleservicesPayment.activationCharges" name="crmCapForm" styleClass="Lmstextbox" maxlength="11" styleId="rentalChargeId" onkeyup="javascript:ValidatenReplaceAlphanumericnDot(this);sum();" onclick="removeZeroFromNumber(this);" onfocus="removeZeroFromNumber(this);"></html:text>
		<font class="errorTextbox"></font>
	</p>
		<p class="floatl"><strong>Installation Charge </strong>        
		<html:text property="teleservicesPayment.registrationCharges" name="crmCapForm" styleClass="Lmstextbox" maxlength="11" styleId="installChargeId" onkeyup="sum();"></html:text>
		<font class="errorTextbox"></font>
		</p>
  <c:if test="${crmCapForm.customerDetailsPojo.product eq 'EOC' and crmCapForm.customerDetailsPojo.serviceType eq 'PR' }">
    <p class="floatl marginleft15"><strong>Total</strong>
    	 <%--<html:text name="crmCapForm" property="totalAmount" styleClass="Lmstextbox" maxlength="11" styleId="total" readonly="true"></html:text> --%>
    	 <input type="text" class="Lmstextbox" id="total" name="total" readonly="readonly" />
    	 <script type="text/javascript">sum();</script>
   </p> 
   </c:if>
	<p class="floatl clear"><strong>Payment Mode</strong>
		<span class="LmsdropdownWithoutJs">
			<html:select property="teleservicesPayment.paymentMode" name="crmCapForm" styleId="paymentMode">
				<html:option value="">Payment Mode</html:option>
				<html:optionsCollection name="crmCapForm" property="paymentModes" label="contentName" value="contentValue" />
			</html:select>
		</span>
	</p>
	<p class="floatl marginleft15 hide1" id="cash"><strong>Receipt No.</strong>	 
		<html:text styleClass="Lmstextbox" property="teleservicesPayment.receiptNo" name="crmCapForm" styleId="receiptNoId"></html:text>
		<font class="errorTextbox"></font>
	</p>
	<p class="floatl marginleft15 hide1" id="onlinePayment"><strong>Transaction ID</strong>
		<html:text styleClass="Lmstextbox"  property="teleservicesPayment.transactionId" name="crmCapForm" maxlength="45"></html:text>
	</p>
	<div class="hide1" id="cheque">
		<p class="floatl marginleft15 ">
		    <strong> Cheque/DD No.</strong>
	    	<html:text styleClass="Lmstextbox"  property="teleservicesPayment.chequeDdNo" name="crmCapForm" maxlength="6" onkeyup="javascript:ValidatenReplaceAlphanumeric(this);"></html:text>
		</p>
		<p class="floatl marginleft15 " id="bankName">
			<strong> Bank Name</strong>
			<span class="LmsdropdownWithoutJs">
				<html:select property="teleservicesPayment.bankName" name="crmCapForm" styleId="bankId">
					<html:option value="">Select Bank </html:option>
					<html:optionsCollection name="crmCapForm" property="bankList"  label="categoryValue" value="categoryId" />
				</html:select>
			</span>
		</p>
		<p class="clear floatl">
			<strong> Bank Branch</strong>
	 		<html:text styleClass="Lmstextbox"  property="teleservicesPayment.bankBranch" name="crmCapForm" maxlength="45"></html:text>
		</p>
		<p class="marginleft15 floatl">
			<strong> Cheque/DD Date</strong>
			<html:text styleClass="tcal tcalInput"  property="teleservicesPayment.displayChequeDate" name="crmCapForm" styleId="teleservicesChequeDate"></html:text>
			<font class="errorTextbox"></font>
		</p>
		<p class="marginleft15 floatl">
			<strong> City</strong>
			<html:text property="teleservicesPayment.bankCity" name="crmCapForm" styleId="teleserviceCityId" styleClass="Lmstextbox" maxlength="35" onkeyup="replaceOtherThanAlphaSpace(this.id);" onblur="replaceOtherThanAlphaSpace(this.id);"></html:text>
		</p>
	</div>
      <br class="clear" /> 
</div>
    
      <!-------- CPE Charges ------->
<div class="hide1" id="CPECharges">
	<h4>Associate Co.
    <c:if test="${crmCapForm.customerDetailsPojo.crfStage ne 'FTR' }">
      <a href="javascript:;" class="yellow_button floatr margintop7 " id="resetCPECharges">Reset</a>
    </c:if> 
    <a href="javascript:;" class="yellow_button floatr margintop7 marginright5 reset" onclick="saveForm();">Save</a>
    </h4>
	<html:hidden property="telecommunicationPayment.entityType" name="crmCapForm" value="E"/>
	<html:hidden property="telecommunicationPayment.serviceType" name="crmCapForm" value="${crmCapForm.customerDetailsPojo.serviceType}"/>
	<html:hidden property="telecommunicationPayment.status" name="crmCapForm" value="A"/>
	<html:hidden property="telecommunicationPayment.installationStatus" name="crmCapForm" value="PreIns"/>
	<p class="floatl"><strong>Amount</strong>
		<html:text property="telecommunicationPayment.amount" name="crmCapForm" styleClass="Lmstextbox" maxlength="10" styleId="amountId" onkeyup="javascript:ValidatenReplaceAlphanumericnDot(this)" onclick="javascript:removeZeroFromNumber(this);" onfocus="removeZeroFromNumber(this);"></html:text>
		<font class="errorTextbox"></font>
	</p>
	<p class="floatl clear"><strong>Payment Mode</strong>
	  <span class="LmsdropdownWithoutJs">
	  	<html:select property="telecommunicationPayment.paymentMode" name="crmCapForm" styleId="paymentModeCPE">
	         <html:option value="">Payment Mode</html:option>
	         <html:optionsCollection name="crmCapForm" property="paymentModes" label="contentName" value="contentValue" />
	     </html:select>
	  </span>
	</p>
	<p class="floatl marginleft15 hide1" id="cashCPE"><strong>Receipt No.</strong>
		<html:text styleClass="Lmstextbox"  property="telecommunicationPayment.receiptNo" name="crmCapForm" styleId="telesolutionReceiptNoId"></html:text>
	</p>
	<p class="floatl marginleft15 hide1" id="onlinePaymentCPE"><strong>Transaction ID</strong>
		<html:text styleClass="Lmstextbox"  property="telecommunicationPayment.transactionId" name="crmCapForm" maxlength="45"></html:text>
	</p>
	<div class="hide1" id="chequeCPE">
		<p class="floatl marginleft15 ">
			<strong> Cheque/DD No.</strong>
	  		<html:text styleClass="Lmstextbox"  property="telecommunicationPayment.chequeDdNo" name="crmCapForm" maxlength="6" onkeyup="javascript:ValidatenReplaceAlphanumeric(this);"></html:text>	
		</p>
		 <p class="floatl marginleft15 ">
		<strong> Bank Name</strong>
		<span class="LmsdropdownWithoutJs">
			<html:select property="telecommunicationPayment.bankName" name="crmCapForm" styleId="bankNameCPE">
				<html:option value="0">Select Bank</html:option>
				<html:optionsCollection name="crmCapForm" property="bankList" label="categoryValue" value="categoryId" />
			</html:select>
		</span>
		</p>
		<p class="floatl clear">
		 	<strong> Bank Branch</strong>
	 		<html:text styleClass="Lmstextbox"  property="telecommunicationPayment.bankBranch" name="crmCapForm" maxlength="45"></html:text>
		</p>
		<p class="floatl marginleft15">
			<span class="floatl "><strong> Cheque/DD Date</strong>
		  		<html:text styleClass="tcal tcalInput"  property="telecommunicationPayment.displayChequeDate" name="crmCapForm" styleId="teleCommChequeDate"></html:text>
		  		<font class="errorTextbox"></font>
			</span>
		</p>
		<p class="marginleft15 floatl">
			<strong> City</strong>
			<html:text styleId="telecommCityId" styleClass="Lmstextbox" name="crmCapForm" property="telecommunicationPayment.bankCity" onkeyup="replaceOtherThanAlphaSpace(this.id);" onblur="replaceOtherThanAlphaSpace(this.id);"></html:text>
		</p>
	   
	</div>
	<br class="clear" />
</div>
<!-- End CPE Charges -->

<c:if test="${not((crmCapForm.customerDetailsPojo.product eq 'EOC') and (crmCapForm.customerDetailsPojo.serviceType eq 'PR'))}" >
<div class=" paddingBottom30 clear" id="securityDeposit">
    <h4>Security Deposit Payment
    <c:if test="${crmCapForm.customerDetailsPojo.crfStage ne 'FTR' }">
      <a href="javascript:;" class="yellow_button floatr margintop7 " id="resetSecurityDeposit">Reset</a>
    </c:if> 
    <a href="javascript:;" class="yellow_button floatr margintop7 marginright5 reset" onclick="saveForm();">Save</a></h4>
    <html:hidden property="securityDepositPayment.entityType" name="crmCapForm" value="T"/>
    <html:hidden property="securityDepositPayment.serviceType" name="crmCapForm" value="${crmCapForm.customerDetailsPojo.serviceType}"/>
    <html:hidden property="securityDepositPayment.status" name="crmCapForm" value="A"/>
    <html:hidden property="securityDepositPayment.paymentChannel" name="crmCapForm" value="SD"/>
    <html:hidden property="securityDepositPayment.installationStatus" name="crmCapForm" value="PreIns"/>
    <input type="hidden" class="Lmstextbox" id="total" name="total" />
	<%-- <html:hidden name="crmCapForm" property="totalAmount" styleClass="Lmstextbox" styleId="total" value="0.0"></html:hidden>--%>
	<p class="floatl"><strong>Amount</strong>
	<c:if test="${empty crmCapForm.securityDepositPayment.amount}">
	<html:text property="securityDepositPayment.amount" name="crmCapForm" value="0" styleClass="Lmstextbox" maxlength="11" styleId="securityChrgeId" onkeyup="javascript:ValidatenReplaceAlphanumericnDot(this)" onclick="removeZeroFromNumber(this);" onfocus="removeZeroFromNumber(this);"></html:text>
	</c:if>
	<c:if test="${not empty crmCapForm.securityDepositPayment.amount}">
		<html:text property="securityDepositPayment.amount" name="crmCapForm" styleClass="Lmstextbox" maxlength="11" styleId="securityChrgeId" onkeyup="javascript:ValidatenReplaceAlphanumericnDot(this)" onclick="removeZeroFromNumber(this);" onfocus="removeZeroFromNumber(this);"></html:text>
	</c:if>
		<font class="errorTextbox"></font>
	</p>
	<p class="floatl clear"><strong>Payment Mode</strong>
		<span class="LmsdropdownWithoutJs">
			<html:select property="securityDepositPayment.paymentMode" name="crmCapForm" styleId="paymentModeSecurity">
				<html:option value="">Payment Mode</html:option>
				<html:optionsCollection name="crmCapForm" property="paymentModes" label="contentName" value="contentValue" />
			</html:select>
		</span>
	</p>
	<p class="floatl marginleft15 hide1" id="cashSecurity"><strong>Receipt No.</strong>	 
		<html:text styleClass="Lmstextbox" property="securityDepositPayment.receiptNo" name="crmCapForm" styleId="receiptNoIdSecurity"></html:text>
	</p>
	<p class="floatl marginleft15 hide1" id="onlinePaymentSecurity"><strong>Transaction ID</strong>
		<html:text styleClass="Lmstextbox"  property="securityDepositPayment.transactionId" name="crmCapForm" maxlength="45"></html:text>
	</p>
	<div class="hide1" id="chequeSecurity">
		<p class="floatl marginleft15 ">
		    <strong> Cheque/DD No.</strong>
	    	<html:text styleClass="Lmstextbox"  property="securityDepositPayment.chequeDdNo" name="crmCapForm" maxlength="6" onkeyup="javascript:ValidatenReplaceAlphanumeric(this);"></html:text>
		</p>
		<p class="floatl marginleft15 " id="bankNameSecurity">
			<strong> Bank Name</strong>
			<span class="LmsdropdownWithoutJs">
				<html:select property="securityDepositPayment.bankName" name="crmCapForm" styleId="bankIdSecurity">
					<html:option value="">Select Bank </html:option>
					<html:optionsCollection name="crmCapForm" property="bankList"  label="categoryValue" value="categoryId" />
				</html:select>
			</span>
		</p>
		<p class="clear floatl">
			<strong> Bank Branch</strong>
	 		<html:text styleClass="Lmstextbox"  property="securityDepositPayment.bankBranch" name="crmCapForm" maxlength="45" styleId="bankBranchSecurity"></html:text>
		</p>
		<p class="marginleft15 floatl">
			<strong> Cheque/DD Date</strong>
			<html:text styleClass="tcal tcalInput"  property="securityDepositPayment.displayChequeDate" name="crmCapForm" styleId="chequeDateSecurity"></html:text>
			<font class="errorTextbox"></font>
		</p>
		<p class="marginleft15 floatl">
			<strong> City</strong>
			<html:text property="securityDepositPayment.bankCity" name="crmCapForm" styleId="cityIdSecurity" styleClass="Lmstextbox" maxlength="35" onkeyup="replaceOtherThanAlphaSpace(this.id);" onblur="replaceOtherThanAlphaSpace(this.id);"></html:text>
		</p>
	</div>
      <br class="clear" /> 
</div>
</c:if>

</div>

 <div class="clear textAC margintop20">
      	<a href="#customerBasicInformation " class="pageNav prev" onclick="changeTab('ci')">Prev</a><a href="#documentDetails" class="pageNav next" onclick="changeTab('dod')">Next</a>
      </div>
      
     <!-- right   orderPaymentDetails--> 
 <div class="floatr inner_right positionR10">
    <p class="buttonPlacement">
    <logic:equal name="crmRoles" property="available(create_ina)" value="true" scope="session">
      <a href="#" id="orderPaymentDetails_Save" class="main_button" onclick="saveForm();"><span>Save</span></a>
    </logic:equal>
    <c:if test="${crmCapForm.customerDetailsPojo.crfStage ne 'FTR'}">
     <a href="javascript:;" id="orderPaymentDetails_Reset" class="main_button_multiple  marginleft15"><span>All <br />Reset</span></a>
     </c:if>
     </p>
 </div>
   
  <br class="clear" />
   </div>
 <!--  orderPaymentDetails-->  
 
  <!--  documentDetails-->  
   <div id="documentDetails"  class="clear tab_content">
    <div class="INA floatl marginleft10">
    <!-- Documents -->
      <div class=" paddingBottom30" id="documentsDetails">
        <h4>Documents Details
        <c:if test="${crmCapForm.customerDetailsPojo.crfStage ne 'FTR' }">
          <a href="javascript:;" class="yellow_button floatr margintop7" id="resetDocumentsDetails">Reset</a>
        </c:if> 
        <a href="javascript:;" class="yellow_button floatr margintop7 marginright5 reset" onclick="saveForm();">Save</a></h4>
 <p> <strong>Documents</strong>
  <span class=" inlineBlock">
	<% int index = 1 ; %>
	<% int i=0; %>
  		<logic:iterate id="documents" name="crmCapForm" property="documents" indexId="ctr">
      	   <LABEL class="label_check margintop5" for="documents_${documents.contentName}" >
    		 <html:checkbox name="documents" property="selected" styleId="documents_${documents.contentName}" value="true" indexed="true" >${documents.contentName}</html:checkbox>
    	 </LABEL>
		 <% if( index % 4 == 0 ){ %> <br/>  <% } index++; %>
		 <%i++; %>
     </logic:iterate>
     <html:hidden property="document" styleId="idDocument" value='<%=i+""%>'/> 
  </span>
  <font id="dynamicI"></font>
</p>
        <br class="clear" />
        <p>
           <strong>Address Proof</strong>
   <span class="inlineBlock">
		<% int address = 1 ; %>
		<%int j=0; %>
   		<logic:iterate id="addressProofs" name="crmCapForm" property="addressProofs" indexId="ctr">
      	   <LABEL class="label_check margintop5" for="${addressProofs.contentName}" >
    		 <html:checkbox name="addressProofs" property="selected" styleId="${addressProofs.contentName}" value="true" indexed="true" >${addressProofs.contentName}</html:checkbox>
    	 </LABEL>
	   <% if( address % 4 == 0 ){ %> <br/>  <% } address++; %>
	   <%j++; %>
     </logic:iterate>
     <html:hidden property="addressProof" styleId="idAddressProof" value='<%=j+"" %>'/> 
  </span>
  <font id="dynamicA"></font>
 </p>
    <p><sup>#</sup><i class="marginleft10">Document should not be older than 3 months.</i></p>
    </div>
    
    <div class="clear createBorder marginBottom30" id="documentUpload">
    	<h4>Upload Documents</h4>
      <c:url value="${initParam.dmshost }/np-document-upload/files/upload/INA/${ crmCapForm.customerDetailsPojo.crfId }" var="dmsUrl"></c:url>
      <%-- http://10.20.0.12:8080/suncity/JSPs/AddDoc_order.jsp?CAFNO=${ crmCapForm.customerDetailsPojo.crfId }&CustomerID=${ crmCapForm.customerDetailsPojo.customerId }&CustomerName=${ crmCapForm.customerDetailsPojo.custFname }+${ crmCapForm.customerDetailsPojo.custLname }&RMN=${ crmCapForm.customerDetailsPojo.rmn }&RTN=${ crmCapForm.customerDetailsPojo.rtn } --%>    	
    	<iframe src="${dmsUrl }"
    	scrolling="yes" frameborder="0"
    	style="border: none; overflow: hidden; width: 755px; height: 400px;" allowTransparency="true"></iframe>
    </div>
  
    </div>
    
          <div class="clear textAC margintop20">
      	<a href="#orderPaymentDetails" class="pageNav prev" onclick="changeTab('op')">Prev</a><a href="#additionalInformation" class="pageNav next" onclick="changeTab('ai')">Next</a>
      </div>
     <!-- right customerBasicInformation-->
   <div class="floatr inner_right positionR10">
    <p class="buttonPlacement">
    <logic:equal name="crmRoles" property="available(create_ina)" value="true" scope="session">
       <a href="javascript:;" id="documentDetailsPojo_Save" class="main_button" onclick="saveForm();"><span>Save</span></a>
     </logic:equal>
     <c:if test="${crmCapForm.customerDetailsPojo.crfStage ne 'FTR'}">
    <a href="javascript:;" id="documentDetails_Reset" class="main_button_multiple  marginleft15"><span>All <br />Reset</span></a>
    </c:if>
     </p>
   </div>
   
  <br class="clear" />
    
   </div>
   <!--  documentDetails-->  
   
   <!--  additionalInformation-->  
   <div id="additionalInformation"  class="clear tab_content">
     <div class="INA floatl marginleft10">
    
    <!-- Vehicle Details  -->
   <div class="floatl paddingBottom30" id="vehicleDetails">
        <h4>Vehicle/Mobile Handset Details
        <c:if test="${crmCapForm.customerDetailsPojo.crfStage ne 'FTR' }">
          <a href="javascript:;" class="yellow_button floatr margintop7" id="resetVehicleDetails">Reset</a>
        </c:if> 
        <a href="javascript:;" class="yellow_button floatr margintop7 marginright5 reset" onclick="saveForm();">Save</a></h4>
        
         <p class="floatl"><strong>Vehicle Make</strong>
         	<html:text name="crmCapForm" property="additionalDetailsPojo.vehicleMake" styleClass="Lmstextbox"  maxlength="45" styleId="vehicleMake"></html:text><font></font>
         </p>
         <p class="floatl marginleft15"><strong>Vehicle Registration No.</strong>
         	<html:text name="crmCapForm" property="additionalDetailsPojo.vehicleDetail" styleClass="Lmstextbox"  maxlength="45" styleId="vehicleRegistrationNo"></html:text><font></font>
          </p>
          <p class="floatl marginleft15"><strong>Mobile Make</strong>
          	<html:text property="additionalDetailsPojo.mobileMake" styleClass="Lmstextbox" name="crmCapForm" maxlength="45"></html:text><font></font>
          </p>
         <p class="floatl marginleft15"><strong>Mobile Model No.</strong>
         	<html:text property="additionalDetailsPojo.mobileNo" styleClass="Lmstextbox" name="crmCapForm" maxlength="45"></html:text><font></font>
         </p>
           
         <p class="floatl clear"><strong>Mobile IMEI No.</strong>
         	<html:text property="additionalDetailsPojo.mobileImeiNo" styleClass="Lmstextbox" name="crmCapForm" maxlength="15"></html:text><font></font>
         </p>
            <br class="clear" />
			<html:hidden name="crmCapForm" property="additionalDetailsPojo.vehicleDetail" styleId="idVehicleDetail"/>
   </div>
      <!-- Credit Card  Details  -->
      <div class="floatl paddingBottom30 clear" id="creditCardDetails">
       <!-- <h4>Bank Details <a href="javascript:;" class="yellow_button floatr margintop7" id="resetCreditCardDetails">Reset</a></h4> -->
        
         <h4>Credit Card/Bank Details
         <c:if test="${crmCapForm.customerDetailsPojo.crfStage ne 'FTR' }">
           <a href="javascript:;" class="yellow_button floatr margintop7" id="resetCreditCardDetails">Reset</a>
         </c:if> 
         <a href="javascript:;" class="yellow_button floatr margintop7 marginright5 reset" onclick="saveForm();">Save</a></h4>
        
  <p class="floatl"><strong> Select Credit Card</strong>
    <span class="LmsdropdownWithoutJs">
    	<html:select property="additionalDetailsPojo.crcdType"name="crmCapForm" styleId="creditCardTypeId">
    	   <html:option value="">Select</html:option>
    	   <html:option value="G">Gold</html:option>
    	   <html:option value="P">Platinum</html:option>
    	</html:select>
     </span><font></font>
  </p>
         <p class="floatl marginleft15"><strong>Credit Card No.</strong>
         	<html:text property="additionalDetailsPojo.crcdCardNo" styleClass="Lmstextbox" name="crmCapForm" maxlength="16" onclick="removeZeroFromNumber(this);" onfocus="removeZeroFromNumber(this);" onkeyup="javascript:ValidatenReplaceAlphanumeric(this);"></html:text><font></font>
         </p>
         <p class="floatl marginleft15"><strong>Credit Card Expiry Date</strong>
         	<html:text property="additionalDetailsPojo.displayCrcdExpiryDate" styleClass="tcal tcalInput" name="crmCapForm" readonly="true" onclick="crCardExpDate()"></html:text><font></font>
        </p> 
        
       <p class="clear floatl ">
  		<strong> Bank Name</strong>
   			<span class="LmsdropdownWithoutJs">
      			<html:select property="additionalDetailsPojo.bankName" name="crmCapForm" styleId="">
             		<html:option value="">Select Bank</html:option>
             		<html:optionsCollection name="crmCapForm" property="bankList"  label="categoryValue" value="categoryValue" />
      			</html:select>
     		</span>
 		</p>
         <p class="marginleft15 floatl"><strong>Bank Branch</strong>
         	<html:text property="additionalDetailsPojo.bankBranch" styleClass="Lmstextbox" name="crmCapForm" maxlength="45"></html:text><font></font>
        </p>
         <p class="floatl marginleft15"><strong>Bank Account No.</strong>
         	<html:text property="additionalDetailsPojo.bankAccountNo" styleClass="Lmstextbox" name="crmCapForm" maxlength="16" onclick="removeZeroFromNumber(this);" onfocus="removeZeroFromNumber(this);" onkeyup="javascript:ValidatenReplaceAlphanumeric(this);"></html:text><font></font>
        </p>
        <p class="floatl marginleft15"><strong>Nature of Bank Account</strong>
           <span class="LmsdropdownWithoutJs">
           		<html:select property="additionalDetailsPojo.bankAccountType"name="crmCapForm" styleId="bankAccountNatureId">
    	   			<html:option value="">Nature of Account</html:option>
    	   			<html:option value="Savings">Savings</html:option>
    	  	        <html:option value="Current">Current</html:option>
    			</html:select>
     	 </span>
      </p>
            <br class="clear" />
     </div>
       
     <!-- Network Details  -->
      <div class=" paddingBottom30 clear" id="networkDetails">
        <h4>Partner Details
        <c:if test="${crmCapForm.customerDetailsPojo.crfStage ne 'FTR' }">
          <a href="javascript:;" class="yellow_button floatr margintop7" id="resetNetworkDetails">Reset</a>
        </c:if>
        <a href="javascript:;" class="yellow_button floatr margintop7 marginright5 reset" onclick="saveForm();">Save</a></h4>
 		<p class="floatl clear"><strong>Business Partner</strong>
    		 <span class="LmsdropdownWithoutJs">
     			<html:select property="customerDetailsPojo.businessPartner" name="crmCapForm" styleId="bpCodeId" onchange="javascript:fillBpCode('bpCode',this.value)">
             		<html:option value="0">Please Select</html:option>
             		<html:optionsCollection name="crmCapForm" property="bussinessPartners" label="partnerName" value="partnerId" />
          		</html:select>
  		 	</span>
 		</p>
 		<p class="floatl marginleft15"><strong>Business Partner Code</strong>
 		<html:text styleClass="Lmstextbox"  property="partner.crmPartnerCode" name="crmCapForm" maxlength="10" styleId="bpCode" readonly="true" />
 		</p>
 	 	    <p class="floatl clear"><strong>Sales Representative</strong>
    		 <span class="LmsdropdownWithoutJs">
     			<html:select property="customerDetailsPojo.salesRepresentative" name="crmCapForm" styleId="srCodeId" onchange="javascript:fillSrCode('srCode',this.value)">
             		<html:option value="0">Please Select</html:option>
             		<logic:notEmpty name="crmCapForm" property="crmUserPojos">
             			<html:optionsCollection name="crmCapForm" property="crmUserPojos" label="fullName" value="userId"/>
             		</logic:notEmpty>
          		</html:select>
  		 	</span>
 		</p>
 		<p class="floatl marginleft15"><strong>Sales Representative Code</strong>
 		<html:text styleClass="Lmstextbox"  property="customerDetailsPojo.salesUser.srCode" name="crmCapForm" maxlength="10" styleId="srCode" readonly="true" />
 		</p>
            <br class="clear" />
     </div>
      <c:if test="${crmCapForm.customerDetailsPojo.crfStage eq 'FTR'}">
	      <div class=" paddingBottom30 clear">
	        <h4>Remarks</h4>
	        <p class="floatl clear">
	          <strong>Remarks </strong>
	          <html:textarea property="remarksPojo.remarks" name="crmCapForm" styleClass="LmsRemarkstextarea" styleId="ftr_remarks"></html:textarea>
	          <font class="errorTextbox" style="top: 100px; width: 200px;"></font>
	          <html:hidden property="remarksPojo.mappingId" name="crmCapForm" value="${crmCapForm.customerDetailsPojo.recordId }"/>
	          <html:hidden property="remarksPojo.actions" name="crmCapForm" value="FW"/>
	        </p>
	      </div>
      </c:if>
      <logic:notEqual name="crmCapForm" property="customerDetailsPojo.recordId" value="0">
	      <p class="floatl clear">
	        <a href="crmCap.do?method=cancelCRFPage&CrfID=${crmCapForm.customerDetailsPojo.crfId}&RecordId=${crmCapForm.customerDetailsPojo.recordId}" class="floatr marginright5 yellow_button margintop7 ">Cancel CAF</a>
	      <!-- hidden field for cancel crf -->
	    	<html:hidden property="remarksPojo.reasonId" name="crmCapForm" styleId="crfCancelReasonHidden"/>
	    	<html:hidden property="customerDetailsPojo.linkedCRF" name="crmCapForm" styleId="linkageCRFHidden"/>   
	     </p>
      </logic:notEqual>
  </div>
  <div class="clear textAC margintop20">
      	<a href="#documentDetails" class="pageNav prev" onclick="changeTab('dod')">Prev</a>
  </div>
     <!-- right additionalInformation-->
   <div class="floatr inner_right positionR10">
    <p class="buttonPlacement">
    <logic:equal name="crmRoles" property="available(create_ina)" value="true" scope="session">
       <a href="javascript:;" id="additionalInformation_Save" class="main_button" onclick="saveForm();"><span>Save</span></a>
    </logic:equal>
    <logic:equal name="crmRoles" property="available(create_ina)" value="true" scope="session">
      <a href="javascript:;" id="submitInA" class="main_button  marginleft15"><span>Submit</span></a>
    </logic:equal>
    <c:if test="${crmCapForm.customerDetailsPojo.crfStage ne 'FTR'}">
     <a href="javascript:;" id="additionalInformation_Reset" class="main_button_multiple  marginleft15"><span>All<br/>Reset</span></a>
     </c:if>
     </p>
   </div>
  <br class="clear" />
   </div>
    <!--  additionalInformation-->  
      </div>
<logic:notEqual name="crmCapForm" property="customerDetailsPojo.crfStage" value="Initiate">
<logic:notEmpty name="crmCapForm" property="remarksPojoList">
<div class="LmsTable">
 <h4>Remarks Details: <span class="lmsMinusImage floatr"></span></h4>
     <div class="viewLmsTable margintop10 viewLmsLeadTable">
   	 	<display:table id="remarkData" name="crmCapForm.remarksPojoList" class="dataTable" style="width:100% height:250px" requestURI="">
			<display:column title="Sr.No." style="width:3%;" maxLength="5"><bean:write name="remarkData_rowNum" /></display:column>
			<display:column title="Remarks" property="remarks" style="width:25%;" maxLength="80"></display:column>
			<display:column title="Created By" property="createdBy" maxLength="10"></display:column>
			<display:column title="Created Time" property="displayCreatedTime"></display:column>
			<display:column title="Action" property="actions"></display:column>
			<display:column title="Reason">
			<logic:notEqual name="remarkData" property="reasonId" value="0">
				<logic:notEmpty name="remarkData" property="reasonId">
							<bean:write name="crmRoles" property="displayEnum(AllCRFReasons,${remarkData.reasonId})" scope="session" />
				</logic:notEmpty>
			</logic:notEqual>			
			<logic:equal name="remarkData" property="reasonId" value="0">-</logic:equal>
			</display:column>
		</display:table>
	</div>
	<h4>Activity Log Details <span class="lmsMinusImage floatr"></span></h4>
	<div class="viewLmsTable margintop10">
	 <iframe src="jsp/content/masterdata/displayAuditLog.jsp" frameborder="0" scrolling="yes"
     style="border: none; overflow: hidden; width: 100%; height:250px;" allowTransparency="true" onload="iframeLoaded(this)"></iframe>
	 </div>
</div>
</logic:notEmpty>
</logic:notEqual>
   </div>
 </html:form> 
</div>
</body>