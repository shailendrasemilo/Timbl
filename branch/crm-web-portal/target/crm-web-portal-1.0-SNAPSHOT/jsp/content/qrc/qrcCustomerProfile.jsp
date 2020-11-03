<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib uri="/WEB-INF/tlds/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/tlds/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/tlds/displaytag.tld" prefix="display"%>
<%@taglib uri="/WEB-INF/tlds/struts-html.tld" prefix="html"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<script src="javascript/qrcCustomerProfile.js" type="text/javascript"></script>

<html:hidden name="qrcForm" property="custDetailsPojo.crfId" value="${qrcForm.custDetailsPojo.crfId}" styleId="uploadDocCRF" />
<div class="overlayDiv"></div>
<div class="modelPopupDiv" id="uploadDocPPId"></div>
<div class="loadingPopup hidden"></div>
<div id="section">
<div class="section">
<logic:notEmpty name="qrcForm" property="custDetailsPojo.customerId">
  <jsp:include page="crfCustomerDescription.jsp"></jsp:include>
	
</logic:notEmpty>
<div class="inner_section ">
	<div class="inner_left_lead floatl  qrcLeft">
	<c:set var="method">CustomerCategory</c:set>
	<c:if test="${(param.method eq 'viewCustomerOwnerShip') or (param.method eq 'updateCustomerOwnerShip') }">
	<c:set var="method">CustomerOwnerShip</c:set>
	</c:if>	
	<c:if test="${method eq 'CustomerCategory' }">
	<bean:define id="activeMenu" value="CustomerCategory"/>
	</c:if>
	<c:if test="${method eq 'CustomerOwnerShip' }">
	 <bean:define id="activeMenu" value="CustomerOwner"/>
	</c:if>
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
	<div class="error_message" id="error">
		<html:errors />
	</div>
	
  <div class="relative inner_left_lead">
  <html:form action="/manageQrc" styleId="customerProfileForm">
		
		<div class=" paddingBottom30 " id="customerPersonalDetails">
    <h4>Customer  Details </h4>
 <p class="floatl clear">    
    <strong>Connection Type</strong>
    
   <c:if test="${method eq 'CustomerCategory' }">
	    <span class="dropdownWithoutJs">
	     	<html:select property="custDetailsPojo.connectionType" name="qrcForm" styleId="connectionTypeCRF" onchange="hideErrormsg();">
	             <html:option value="0">Select Connection Type</html:option>
	             <html:optionsCollection name="qrcForm" property="connectionTypes" label="contentName" value="contentValue" />
	         </html:select>
	         <font class="errorTextbox hidden" style="width:250px;">Please select connection type .</font>
			 <label  id ="category"class="hidden"><font class="errorTextboxInDiv" style="top:52px">Please change connection type</font></label>
	     </span>
     <font></font>
	 <html:hidden name="qrcForm" property="presentStage" styleId="oldconnectionType" />
     </c:if>
      <c:if test="${method eq 'CustomerOwnerShip' }">
      <span class="lmsshowTextbox" style="width: 207px">
      <bean:write name="crmRoles" property="displayEnum(ConnectionType,${ qrcForm.custDetailsPojo.connectionType })"/>
      </span>
      <html:hidden name="qrcForm" property="custDetailsPojo.connectionType" styleId="connectionTypeCRF" />
     </c:if>
 </p> 
 <div id="NAforOnlyIndividual" class="hide1">
    <p class="floatl clear">
    <strong class="headStrong"> Individual/Proprietorship</strong>
     <span class="floatl "><strong> First Name</strong>
     	<html:text styleClass="Lmstextbox" property="custDetailsPojo.custFname" name="qrcForm" styleId="firstName" maxlength="50" onkeyup="javascript:changeToUpperCase(this);hideErrorforOwnership(); "></html:text>
		 <html:hidden name="qrcForm" property="presentName" styleId="oldfirstName" />
     	<font class="errorTextboxInDiv hidden">Please enter first name.</font>
     	<span class="hidden"><font class="errorTextboxInDiv">First name length should be[1-50]</font></span>
     	<label class="hidden"><font class="errorTextboxInDiv">Please enter valid first name</font></label>
			<span  id="ownership"class="hidden"><font class="errorTextboxInDiv">Please change first name .</font></span>
		 
    </span>
  
      <span class="floatl marginleft15 "><strong> Last Name</strong>
      	 <html:text styleClass="Lmstextbox"  property="custDetailsPojo.custLname" name="qrcForm" styleId="lastName" maxlength="50" onkeyup="javascript:changeToUpperCase(this)"></html:text>
      	 <font class="errorTextboxInDiv hidden">Please enter last name.</font>
      	 <span class="hidden"><font class="errorTextboxInDiv">Last name length should be[3-50]</font></span>
     	<label class="hidden"><font class="errorTextboxInDiv">Please enter valid last name</font></label>
      </span>
    </p>
  </div>  
  <div id="NAforOnlyCompany" class="hide1">
    <p class="floatl clear">
	    <strong class="headStrong">Company/Organization/Society</strong>
	     <span class="floatl "><strong> Company Name</strong>
	     	<html:text styleClass="textbox" property="custDetailsPojo.custFname" name="qrcForm" styleId="companyName" maxlength="200" onkeyup="javascript:changeToUpperCase(this);hideErrorforOwnership();" ></html:text>
	     	<font class="errorTextboxInDiv hidden">Please enter company name.</font>
			<span class="hidden"><font class="errorTextboxInDiv">Company name length should be[3-200]</font></span>
			<label id ="ownership"class="hidden"><font class="errorTextboxInDiv">Please change company name.</font></label>
	    </span>
		
		 <html:hidden name="qrcForm" property="presentName" styleId="oldfirstName" />
    </p>
  </div>  
  <p class="floatl ">
	<strong class="headStrong">&nbsp;</strong>
	    <span class="floatl marginleft10"><strong class="marginleft30"> Registered Mobile No.</strong>[+91]
	    	<html:text styleClass="Lmstextbox" property="custDetailsPojo.rmn" name="qrcForm" styleId="registeredMobile" maxlength="10" onkeyup="javascript:ValidatenReplaceAlphanumeric(this);" onclick="removeZeroFromNumber(this);" onfocus="removeZeroFromNumber(this);" ></html:text>
	    	<font class="errorTextboxInDiv hidden">Please enter mobile number.</font>
	    	<span class="hidden"><font class="errorTextboxInDiv">Please enter a valid mobile no.</font></span>
	    </span>
	     <%-- <span class="floatl marginleft10 "><strong class="marginleft30">Registered Telephone No.</strong>[+91]
	     	<html:text styleClass="Lmstextbox" property="custDetailsPojo.rtn" name="qrcForm" styleId="registeredTelephone" maxlength="10" onkeyup="javascript:ValidatenReplaceAlphanumeric(this);" onclick="removeZeroFromNumber(this);" onfocus="removeZeroFromNumber(this);"></html:text>
	     	<font class="errorTextboxInDiv hidden">Please enter telephone number.</font>
	     	<span class="hidden"><font class="errorTextboxInDiv">Please enter a valid telephone no.</font></span>
	    </span>--%>
  </p>
   
 <p class="floatl clear">
     <span class="floatl"><strong>Registered Email ID</strong>
     	<html:text styleClass="Lmstextbox" property="custDetailsPojo.custEmailId" name="qrcForm" styleId="emailId" maxlength="256"></html:text>
     	<font class="errorTextbox hidden">Please enter email id.</font>
     	<span class="hidden"><font class="errorTextbox">Please enter a valid email id.</font></span>
    </span>
    <%--<span class="floatl marginleft15 "><strong>Alt. Email ID</strong>
    	<html:text styleClass="Lmstextbox" property="custDetailsPojo.altEmailId" name="qrcForm" styleId="altEmailId" maxlength="256"></html:text>
    	<font class="errorTextboxInDiv hidden">Please enter a valid Alt. email id.</font>
    </span>--%>
    <span class="floatl marginleft10 "><strong class="marginleft30">Alt. Mobile No.</strong>[+91]
    	<html:text styleClass="Lmstextbox" property="custDetailsPojo.custMobileNo" name="qrcForm" styleId="mobile" maxlength="10" onkeyup="javascript:ValidatenReplaceAlphanumeric(this);" onclick="removeZeroFromNumber(this);" onfocus="removeZeroFromNumber(this);"></html:text>
    	<font class="errorTextboxInDiv hidden">Enter a valid mobile number.</font>
   </span>
   <span class="floatl marginleft15"><strong> PAN / GIR No.</strong>
     	<html:text styleClass="Lmstextbox" property="custDetailsPojo.custPanGirNo" name="qrcForm" styleId="pan" maxlength="10" onkeyup="javascript:changeToUpperCase(this)"></html:text>
     	<font class="errorTextboxInDiv hidden">Enter correct 10 characters Pan/Gir No.</font>
     	<span class="hidden"><font class="errorTextboxInDiv">Enter correct 10 characters Pan/Gir No.</font></span>
    </span>
 </p>
 <p class="floatl clear">
    <strong class="headStrong"> Father's/Husband's Name </strong>
     <span class="floatl"><strong> First Name</strong>
     	<html:text styleClass="Lmstextbox"  property="custDetailsPojo.fhFname" name="qrcForm" styleId="fatherFirstName" maxlength="45" onkeyup="javascript:changeToUpperCase(this)"></html:text>
     	<font class="errorTextboxInDiv hidden">Please enter first name.</font>
     	<span class="hidden"><font class="errorTextboxInDiv">First name length should be[1-50]</font></span>
     	<label class="hidden"><font class="errorTextboxInDiv">Please enter valid first name</font></label>
    </span>
    <span class="floatl marginleft15"><strong> Last Name</strong>
    	<html:text styleClass="Lmstextbox" property="custDetailsPojo.fhLname" name="qrcForm" styleId="fatherLastName" maxlength="45" onkeyup="javascript:changeToUpperCase(this)"></html:text>
      <font class="errorTextboxInDiv hidden">Please enter last name.</font>
      <span class="hidden"><font class="errorTextboxInDiv">Last name length should be[3-50]</font></span>
     	<label class="hidden"><font class="errorTextboxInDiv">Please enter valid last name</font></label>
    </span>
 </p>
 <%-- <p class="floatl marginleft15">
     <strong class="headStrong"> Mother's Maiden Name </strong>
     <span class="floatl"><strong> First Name</strong>
     	<html:text styleClass="Lmstextbox"  property="custDetailsPojo.motherFname" name="qrcForm" styleId="motherFirstName" maxlength="45" onkeyup="javascript:changeToUpperCase(this)"></html:text>
     	<font class="errorTextboxInDiv hidden">Please enter first name.</font>
     	<span class="hidden"><font class="errorTextboxInDiv">First name length should be[3-50]</font></span>
     	<label class="hidden"><font class="errorTextboxInDiv">Please enter valid first name</font></label>
    </span>
    <span class="floatl marginleft15"><strong> Last Name</strong>
    	<html:text styleClass="Lmstextbox"  property="custDetailsPojo.motherLname" name="qrcForm" styleId="motherLastName" maxlength="45" onkeyup="javascript:changeToUpperCase(this)"></html:text>
    	<font class="errorTextboxInDiv hidden">Please enter last name.</font>
    	<span class="hidden"><font class="errorTextboxInDiv">Last name length should be[3-50]</font></span>
     	<label class="hidden"><font class="errorTextboxInDiv">Please enter valid last name</font></label>
    </span>
     <br class="clear" />
 </p> --%>
  <!-- NA for Individual & Proprietorship.  -->  
 <div id="NAforIndividualProprietorship" class="hide1">
   <p class="floatl clear">
     <strong class="headStrong"> Organization Coordinator Name </strong>
     <span class="floatl"><strong> First Name</strong>
     	<html:text styleClass="Lmstextbox" property="custDetailsPojo.orgCordFname" name="qrcForm" styleId="orgCoordFirstName" maxlength="45" onkeyup="javascript:changeToUpperCase(this)"></html:text>
     	<font class="errorTextboxInDiv hidden">Please enter first name.</font>
     	<span class="hidden"><font class="errorTextboxInDiv">First name length should be[1-50]</font></span>
     	<label class="hidden"><font class="errorTextboxInDiv">Please enter valid first name</font></label>
    </span>
    <span class="floatl marginleft15"><strong> Last Name</strong>
    	<html:text styleClass="Lmstextbox" property="custDetailsPojo.orgCordLname" name="qrcForm" styleId="orgCoordLastName" maxlength="45" onkeyup="javascript:changeToUpperCase(this)"></html:text>
    	<font class="errorTextboxInDiv hidden">Please enter last name.</font>
    	<span class="hidden"><font class="errorTextboxInDiv">Last name length should be[3-50]</font></span>
     	<label class="hidden"><font class="errorTextboxInDiv">Please enter valid last name</font></label>
    </span>
   </p>
   <p class="floatl marginleft15">
     <strong class="headStrong"> Authorized Signatory Name &amp; Designation </strong>
     <span class="floatl"><strong> First Name</strong>
     	<html:text styleClass="Lmstextbox" property="custDetailsPojo.authSignFname" name="qrcForm" styleId="authorizedSignatoryFirstName" maxlength="45" onkeyup="javascript:changeToUpperCase(this)"></html:text>
     	<font class="errorTextboxInDiv hidden">Please enter first name.</font>
     	<span class="hidden"><font class="errorTextboxInDiv">First name length should be[1-50]</font></span>
     	<label class="hidden"><font class="errorTextboxInDiv">Please enter valid first name</font></label>
    </span>
    <span class="floatl marginleft15"><strong> Last Name</strong>
    	<html:text styleClass="Lmstextbox" property="custDetailsPojo.authSignLname" name="qrcForm" styleId="authorizedSignatoryLastName" maxlength="45" onkeyup="javascript:changeToUpperCase(this)"></html:text>
    	<font class="errorTextboxInDiv hidden">Please enter last name.</font>
    	<span class="hidden"><font class="errorTextboxInDiv">Last name length should be[3-50]</font></span>
     	<label class="hidden"><font class="errorTextboxInDiv">Please enter valid last name</font></label>    </span>
    <span class="floatl marginleft15"><strong> Designation</strong>
    	<html:text styleClass="Lmstextbox" property="custDetailsPojo.authSignDesg" name="qrcForm" styleId="authorizedSignatoryDesignation" maxlength="45" onkeyup="javascript:changeToUpperCase(this)"></html:text>
    	<font class="errorTextboxInDiv hidden">Please enter designation.</font>
    	<span class="hidden"><font class="errorTextboxInDiv">Designation length should be[3-50]</font></span>
    </span>
   
  </p>
 </div>
     <!-- NA for Individual & Proprietorship.  -->
    <!-- A for Individual  -->
   <div id="AforIndividual" class="hide1">
 <p class="floatl clear">
   <span class="floatl"><strong> Gender</strong>
     <LABEL class="label_radio" for="male">
     	<html:radio styleId="male"  property="custDetailsPojo.custGender" value="M" name="qrcForm"></html:radio>Male
     </LABEL> 
     <LABEL class="label_radio" for="female">
     	<html:radio styleId="female"  property="custDetailsPojo.custGender" value="F" name="qrcForm"></html:radio>Female
     </LABEL>
	<font class="errorTextboxInDiv hidden">Please select gender.</font>
	 
  </span>
  <span class="floatl marginleft15" style="position: relative;"><strong> Birth Date</strong>
  	
  	<html:text styleClass="Lmstextbox calender" readonly="true" property="custDetailsPojo.displayCustDob" name="qrcForm" styleId="birthdate" ></html:text>
  	<font class="errorTextboxInDiv hidden" >Please enter birth date.</font>
  </span>
  <span class="floatl marginleft30"><strong> Nationality</strong>
 	<span class="LmsdropdownWithoutJs">
     <html:select property="custDetailsPojo.nationality" name="qrcForm" styleId="nationalityTypeId">
             <%--<html:option value="0">Select Nationality Type</html:option>
              <html:optionsCollection name="qrcForm" property="nationalityType" label="categoryValue" value="categoryValue" /> --%>
             <html:option value="Indian">Indian</html:option>
             <html:option value="Other">Other</html:option>
         </html:select>   
    </span>
	</span>
	<span class="floatl marginleft15" id="otherNationality"><strong> Please Specify</strong>
    	<html:text styleClass="Lmstextbox" property="nationalityDetailsPojo.nationality" name="qrcForm" styleId="nationalityValueId" maxlength="50" onkeyup="javascript:changeToUpperCase(this)"></html:text>
    	<font class="errorTextboxInDiv hidden">Please enter other nationality.</font>
    </span>
 </p>
  
 </div>
 <div>
 <div id="OtherNationalityDetails" class="clear  hide1 marginBottom30">
  <h4>Passport Details</h4>
   <p class="floatl clear">
  <span class="floatl"><strong> Passport No.</strong> 
     <html:text styleClass="Lmstextbox" property="nationalityDetailsPojo.passportNo" name="qrcForm" styleId="passportNo" maxlength="45"></html:text>
     <font class="errorTextboxInDiv hidden">Please enter passport number.</font>
     <span class="hidden"><font class="errorTextboxInDiv">Please enter a valid passport number.</font></span>
     
  </span>
   <span class="floatl marginleft15"><strong> Passport Valid Up to.</strong>
   	<html:text styleClass="tcal tcalInput" readonly="true" property="nationalityDetailsPojo.displayPassportValidity" name="qrcForm" styleId="passportValidUpTo"></html:text>
   <font class="errorTextboxInDiv hidden">Please enter passport valid up to date.</font>
   <span class="hidden"><font class="errorTextboxInDiv">Passport Validity should be more than 30 days.</font></span>
  </span>

   <span class="floatl marginleft15""><strong>  Visa Type</strong>
   <span class="LmsdropdownWithoutJs">
   <html:select property="nationalityDetailsPojo.visaType" name="qrcForm" styleId="visaType">
	             <html:option value="0">Select Visa Type</html:option>
	             <html:optionsCollection name="qrcForm" property="visaList" label="contentName" value="contentValue" />
	         </html:select>
   <font class="errorTextboxInDiv hidden">Please select visa type.</font>
    </span>
	      
 </span>
      <span class="floatl marginleft15"><strong> Visa Up to.</strong>
   	<html:text styleClass="tcal tcalInput" readonly="true" property="nationalityDetailsPojo.displayVisaValidity" name="qrcForm" styleId="visaValidUpTo"></html:text>   
   <font class="errorTextboxInDiv hidden">Please enter Visa up to.</font>
   <span class="hidden"><font class="errorTextboxInDiv">Visa Validity should be more than 30 days.</font></span>
  </span>
  </p>
   <p class="floatl clear">&nbsp;
   </p>
   <p class="floatl clear">
      <strong class="headStrong">Details of Local Reference </strong>
	  <span class="floatl"><strong> First Name</strong>   
	  <span>
     <html:text styleClass="Lmstextbox" property="nationalityDetailsPojo.localCpFname" name="qrcForm" styleId="passportFirstName" maxlength="50" onkeyup="javascript:changeToUpperCase(this)"></html:text>
    <font class="errorTextboxInDiv hidden">Please enter first name.</font> 
   <span class="hidden"><font class="errorTextboxInDiv">First name length should be[1-50]</font></span>
     	<label class="hidden"><font class="errorTextboxInDiv">Please enter valid first name</font></label> 
     </span>
     </span>
     <span class="floatl marginleft15"><strong> Last Name</strong>
     <html:text styleClass="Lmstextbox" property="nationalityDetailsPojo.localCpLname" name="qrcForm" styleId="passportLastName" maxlength="50" onkeyup="javascript:changeToUpperCase(this)"></html:text>
     <font class="errorTextboxInDiv hidden">Please enter last name.</font>
     <span class="hidden"><font class="errorTextboxInDiv">Last name length should be[3-50]</font></span>
     	<label class="hidden"><font class="errorTextboxInDiv">Please enter valid last name</font></label>
     </span>
  <span class="floatl marginleft15"><strong> Mobile No.</strong>
     
     <html:text styleClass="Lmstextbox" property="nationalityDetailsPojo.localCpMobileNo" name="qrcForm" styleId="passportMobileNo" maxlength="10"  onkeyup="javascript:ValidatenReplaceAlphanumeric(this);" onclick="removeZeroFromNumber(this);" onfocus="removeZeroFromNumber(this);"></html:text>
     <font class="errorTextboxInDiv hidden">Please enter mobile number.</font>
      <span class="hidden"><font class="errorTextboxInDiv">Please enter valid mobile number.</font></span>
  </span>
  </p>
  <br />
   <p class="floatl clear">
   
    <span class="floatl"><strong> Address.</strong>
   <html:textarea property="nationalityDetailsPojo.localCpAdd" styleClass="LmsRemarkstextarea" styleId="passportAddress" name="qrcForm" cols="80" rows="3" onkeyup="javascript:changeToUpperCase(this)"></html:textarea>
   <font class="errorRemarks hidden">Please enter address.</font>
   <span class="hidden"><font class="errorTextboxInDiv">Address length should be[3-255]</font></span>
  </span>
 
  </p>
  <p>
  </p>
 </div>
	<p class="floatl clear">
		<strong>Ticket ID</strong>
		<html:text property="srTicketNo" name="qrcForm" maxlength="20" styleClass="textbox" styleId="ticketId" />
	</p>
	<p class="floatl marginleft30"><strong> </strong><strong> </strong>
   	<a href="javascript:openUploadPopUp('${initParam.dmshost }/np-document-upload','QRC');" class="yellow_button">Upload Document</a>
   	</p>
   	
   	
	<p class="floatl clear">
		<strong> Remarks<span class="error_message">*</span></strong>
		<html:textarea property="remarksPojo.remarks" name="qrcForm" styleClass="LmsRemarkstextarea" styleId="remarks"></html:textarea>
		<font class="errorRemarks hidden">Please enter remarks.</font>
		 <span class="hidden"><font class="errorTextboxInDiv">Remarks length should be[2-4000]</font></span>
	</p> 
 </div>	
 
     <!-- Form 60/61 -->
    <%-- <div class=" clear" id="Form60">
    <br class="clear" />
		<br class="clear" />
    <h4>Form 60/61  </h4>
    <p><strong> (Form of Declaration to be filled by a person who does not have either Permanent Account Number/ <br />General Index Register Number and who makes payment in cash in respect of transaction specified in clause (a) to (h) of rule 1148)</strong></p>
  <p class="clear floatl"><strong> Full Name</strong>
  	 <html:text property="documentDetailsPojo.form60FullName" styleId="form60fullName" styleClass="Lmstextbox" name="qrcForm" maxlength="200" onkeyup="javascript:changeToUpperCase(this)"></html:text>
  	 <font class='errorTextboxInDiv'>Please provide 'Full Name'</font>
  </p>
   <p class="floatl marginleft15"><strong> Amount of Transaction</strong>
   	 <html:text property="documentDetailsPojo.form60TransactionAmount" styleId="form60transactionAmount" styleClass="Lmstextbox" name="qrcForm" maxlength="10" onclick="removeZeroFromNumber(this);" onfocus="removeZeroFromNumber(this);" onkeyup="javascript:ValidatenReplaceAlphanumericnDot(this);"></html:text>
   	 <font class='errorTextbox'> Please provide valid 'Amount'(only digits allow).</font>
   </p>
   <p class="floatl marginleft15"><strong> Particulars of Transaction</strong>
   	 <html:text property="documentDetailsPojo.form60TransactionParticulars" styleId="form60transactionParticulars" styleClass="Lmstextbox" name="qrcForm" maxlength="45" onkeyup="javascript:changeToUpperCase(this)"></html:text>
   	 <font class='errorTextboxInDiv'>Please provide 'Transaction Particulars'</font>
   </p>
   <p class="floatl marginleft15"><strong> Are you assessed to Tax</strong>
      <LABEL class="label_radio" for="assessedToTaxYes">
        <html:radio property="documentDetailsPojo.form60TaxApplicable" styleId="assessedToTaxYes" name="qrcForm"  value="true"></html:radio>Yes
      </LABEL> 
     <LABEL class="label_radio" for="assessedToTaxNo">
     	<html:radio property="documentDetailsPojo.form60TaxApplicable" styleId="assessedToTaxNo" name="qrcForm" value="false"></html:radio>No
     </LABEL>
  </p> 
   <p class="floatl clear"><strong> Address</strong>
   	 <html:textarea property="documentDetailsPojo.form60Address" styleId="form60address" styleClass="LmsRemarkstextarea" name="qrcForm" cols="80" rows="3"></html:textarea>
   	 <font class='errorTextboxInDiv' style="top: 100px;">Please provide 'Address' and length should be[3-255]'</font>
   </p>  
      <br class="clear" />
 <div  class="clear hide1" id="assessedToTaxShow">
 <br /> <br />
       <h4>Yes, assessed to Tax</h4>
        <p class="floatl">
        	<strong> Details of Ward/Circle/Range where the last return of income was filed</strong>
        	<html:text property="documentDetailsPojo.form60TaxWard" styleId="form60taxWard" styleClass="textbox" name="qrcForm" maxlength="45" ></html:text><font class='errorTextboxInDiv'>Please provide 'Taxward Range'</font>
        </p>
    
    <p  class="floatl clear">
    	<strong> Reasons for not having Permanent Account Number/General Index Register Number</strong>
    	<html:text property="documentDetailsPojo.form60ReasonNoPnr" styleId="form60reasonNoPnr" styleClass="textbox" name="qrcForm" maxlength="45"></html:text><font class='errorTextboxInDiv'>Please provide 'Reason No Pnr'</font>
    </p>
    
     <p  class="floatl clear">
     	<strong> Details of documents being produced in support of Address</strong>
     	<html:text property="documentDetailsPojo.form60SupportDocument" styleId="form60supportDocument" styleClass="textbox" name="qrcForm" maxlength="45"></html:text>
     	<font class='errorTextboxInDiv'>Please provide 'Support Document'</font>
    </p>
	 <br class="clear" />
 </div> 
</div> --%>
 <br/>
 <br/>
  		<br class="clear" />
		<br class="clear" />
		
		</div>
		<div class="floatr inner_right">
		<p class="buttonPlacement">	
		<c:if test="${method eq 'CustomerCategory' }">     
			<a href="javascript:void(0)" id="submitCustprof" class="main_button_multiple  ">
			<span>Update Profile </span>			
			</a>
			
			</c:if>	
			<c:if test="${method eq 'CustomerOwnerShip' }">
           <a href="javascript:void(0)" id="submitCustOwner" class="main_button_multiple  ">
			<span>Update Profile </span>			
			</a>
           </c:if>	
		</p>
		</div>
	</html:form>
	<br class="clear" />
   </div>
</div>
<p class="clear"/>
<link rel="stylesheet" type="text/css" href="css/jquery-ui.css"/>
<script src="javascript/jquery-ui.js"></script>
<script>
  $( document ).ready(function(){
    $(".calender").datepicker({
      maxDate: today,
      changeMonth: true,
      changeYear: true,
      dateFormat: "dd-M-yy",
      prevText: "Earlier",
      showOn: "both",
      yearRange: "c-100:c+100"
    });
  });
  var today = new Date();
</script>
