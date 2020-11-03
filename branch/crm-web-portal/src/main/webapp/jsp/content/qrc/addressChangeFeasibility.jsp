<%@taglib uri="/WEB-INF/tlds/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/tlds/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/tlds/displaytag.tld" prefix="display"%>
<%@taglib uri="/WEB-INF/tlds/struts-html.tld" prefix="html"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<head>
<link href="css/colorbox.css" rel="stylesheet" />
<script src="javascript/jquery.colorbox.js"></script>
<script>
	$( document ).ready(function(){
		$(".iframe").colorbox({iframe:true, width:"658px", height:"345px"});
	});
</script>
</head>
<div class="loadingPopup hidden"></div>
<div id="section">
<div class="modelPopupDiv" id="uploadDocPPId"></div>
  <div class="section">
     <h2>Shifting Workflow&nbsp;-&nbsp;&nbsp;<a style="color: #DEAE00;"><bean:write name='qrcForm' property='customerId'/></a></h2>
    <div class="inner_section ">
    <div class="inner_left_lead floatl  qrcLeft">
</div>
      <div class="floatl manageGISRight  qrcRight">
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
        <h4>
          Change Installation Address 
        </h4>
        <div class="relative inner_left_lead">
          <html:form action="/shiftingWorkflow">
          <html:hidden name="qrcForm" property="custDetailsPojo.crfId" value="${qrcForm.custDetailsPojo.crfId}" styleId="uploadDocCRF" />
            <html:hidden property="installationAddressPojo.recordId" name="qrcForm" styleId="instAddrRecordId"
              value="${ qrcForm.installationAddressPojo.recordId }" />
            <html:hidden property="installationAddressPojo.addressType" name="qrcForm" styleId="instAddrType"
              value="${ qrcForm.installationAddressPojo.addressType }" />
            <html:hidden property="shiftingWorkflowPojo.customerId" name="qrcForm" value="${ qrcForm.shiftingWorkflowPojo.customerId}" styleId="customerId"/>
             <html:hidden property="shiftingWorkflowPojo.workflowId" name="qrcForm" value="${ qrcForm.shiftingWorkflowPojo.workflowId}" styleId="workflowId"/>
             <html:hidden property="shiftingWorkflowPojo.shiftingType" name="qrcForm" value="${ qrcForm.shiftingWorkflowPojo.shiftingType}" styleId="shiftingType"/>
             <html:hidden property="custDetailsPojo.product" name="qrcForm" value="${ qrcForm.custDetailsPojo.product}" styleId="hiddenProduct"/>
            <div class="instAddrChange">
            <div class="INA floatl">
			 <p class="floatl clear">
                <strong>Address Line 1</strong>
                <html:text property="installationAddressPojo.addLine1" name="qrcForm" styleClass="textbox" styleId="instAddLine1" maxlength="35"
                  value="${qrcForm.installationAddressPojo.addLine1 }"
                  onblur="this.value = this.value.toUpperCase();"></html:text>
                <font class="errorTextbox hidden">Please enter Address Line 1</font>
              </p>
              <p class="floatl marginleft30">
                <strong>Address Line 2</strong>
                <html:text property="installationAddressPojo.addLine2" name="qrcForm" styleClass="textbox" styleId="instAddLine2" maxlength="35"
                  value="${ qrcForm.installationAddressPojo.addLine2 }"
                  onblur="this.value = this.value.toUpperCase();"></html:text>
                <font class="errorTextbox hidden">Please enter Address Line 2 </font>
              </p>
              <p class="floatl marginleft30">
                <strong>Address Line 3</strong>
                <html:text property="installationAddressPojo.addLine3" name="qrcForm" styleClass="textbox" styleId="instAddLine3" readonly="true"
                  value="${qrcForm.installationAddressPojo.addLine3}"></html:text>
                <font class="errorTextbox hidden">Please check Address Line 3.</font>
              </p>
            <p class="floatl clear ">
			
                <strong>PIN Code</strong>
                <html:text property="installationAddressPojo.pincode" name="qrcForm" styleClass="Lmstextbox" styleId="instAddrPincode" maxlength="6"
                  value="${qrcForm.installationAddressPojo.pincode}"  onkeyup="javascript:ValidatenReplaceAlphanumeric(this);" onclick="removeZeroFromNumber(this);" onfocus="removeZeroFromNumber(this);"></html:text>
                <font class="errorTextbox hidden">Please enter PIN Code</font>
				
              </p>
			  <p class="floatl marginleft30">
              <strong>State</strong> 
			  <span class="LmsdropdownWithoutJs">
			  <span class="showTextbox floatl" style="width:160px;"><bean:write name="qrcForm" property="installationAddressPojo.stateName" /></span>
               
              </span>
            </p>
            <p class="floatl marginleft30">
              <strong>City</strong> <span class="LmsdropdownWithoutJs"> <html:select name="qrcForm" property="installationAddressPojo.cityName"
                  styleId="feasibleCity" onchange="javascript:populateAreaByCity('feasiblArea','feasibleState',this.value), fillAddressLines('city');">
                  <html:option value="0">Please Select</html:option>
                  <logic:notEmpty  name="qrcForm" property="cityList">
                    <html:optionsCollection name="qrcForm" property="cityList" label="cityName" value="cityName" />
                  </logic:notEmpty>
                </html:select> <font class="errorTextbox hidden">Please select city</font>
              </span>
            </p>
            <p class="floatl marginleft30">
              <strong>Area</strong> <span class="LmsdropdownWithoutJs"> <html:select name="qrcForm" property="instAddrArea" styleId="feasiblArea"
                onchange="javascript:populateFeasibleSocieties('feasibleSociety','feasibleState','feasibleCity','feasiblArea','hiddenProduct'),fillAddressLines('area');">
                  <html:option value="0">Please Select</html:option>
                   <logic:notEmpty  name="qrcForm" property="areaList">
                    <html:optionsCollection name="qrcForm" property="areaList" label="area" value="area" />
                  </logic:notEmpty>
              </html:select><font class="errorTextbox hidden">Please select area</font>
              </span>
            </p>
            <p class="floatl clear">
              <strong>Locality / Sector - Society</strong> <span class="LmsdropdownWithoutJs"> <html:select name="qrcForm" property="instAddrSociety"  styleId="feasibleSociety" onchange="javascript:fillAddressLines('society')">
                  <html:option  value="0">Please Select</html:option >
                  <logic:notEmpty  name="qrcForm" property="societyList">
                    <html:optionsCollection name="qrcForm" property="societyList" label="searchName" value="searchName" />
                  </logic:notEmpty>
              </html:select> <font class="errorTextbox hidden">Please select Locality / Sector - Society</font></span>
            </p>
			<p class="floatl marginleft30">
						<strong>House Number</strong>
							<html:text styleClass="Lmstextboxuppercase" maxlength="10" property="installationAddressPojo.addLine1" styleId="houseNoLms" onkeyup="javascript:changeToUpperCase(this)" />
				</p>
				<p class="floatl marginleft30">
						<strong>Landmark</strong>
						  <html:text styleClass="Lmstextboxuppercase" maxlength="25" property="installationAddressPojo.landmark" styleId="landmarkLms" onkeyup="javascript:changeToUpperCase(this)" />
				</p>
			
			  <p class="floatl clear">
                <strong>Address Line 1</strong>
                <html:text property="shiftingWorkflowPojo.addressLine1" name="qrcForm" styleClass="textbox" styleId="instAddLine1" maxlength="35"
                 
                  onblur="this.value = this.value.toUpperCase();"></html:text>
                <font class="errorTextbox hidden">Please enter Address Line 1</font>
              </p>
              <p class="floatl marginleft30">
                <strong>Address Line 2</strong>
                <html:text property="shiftingWorkflowPojo.addressLine2" name="qrcForm" styleClass="textbox" styleId="instAddLine2" maxlength="35"
                 
                  onblur="this.value = this.value.toUpperCase();"></html:text>
                <font class="errorTextbox hidden">Please enter Address Line 2 </font>
              </p>
              <p class="floatl marginleft30">
                <strong>Address Line 3</strong>
                <html:text property="shiftingWorkflowPojo.addressLine3" name="qrcForm" styleClass="textbox" styleId="instAddLine3" readonly="true"
                 ></html:text>
                <font class="errorTextbox hidden">Please check Address Line 3.</font>
              </p>
			    <p class="floatl clear ">
			
                <strong>PIN Code</strong>
                <html:text property="shiftingWorkflowPojo.pincode" name="qrcForm" styleClass="Lmstextbox" styleId="" maxlength="6"
                  onkeyup="javascript:ValidatenReplaceAlphanumeric(this);" onclick="removeZeroFromNumber(this);" onfocus="removeZeroFromNumber(this);"></html:text>
                <font class="errorTextbox hidden">Please enter PIN Code</font>
				
              </p>
			    <p class="floatl marginleft30">
              <strong>State</strong> 
			  <span class="LmsdropdownWithoutJs">
			  <span class="showTextbox floatl" style="width:160px;">
			  <bean:write name="qrcForm" property="installationAddressPojo.stateName" /></span>
               
              </span>
            </p>
            <p class="floatl marginleft30">
              <strong>City</strong> <span class="LmsdropdownWithoutJs"> <html:select name="qrcForm" property="installationAddressPojo.cityName"
                  styleId="feasibleCity" onchange="javascript:populateAreaByCity('feasiblArea','feasibleState',this.value), fillAddressLines('city');">
                  <html:option value="0">Please Select</html:option>
                  <logic:notEmpty  name="qrcForm" property="cityList">
                    <html:optionsCollection name="qrcForm" property="cityList" label="cityName" value="cityName" />
                  </logic:notEmpty>
                </html:select> <font class="errorTextbox hidden">Please select city</font>
              </span>
            </p>
            <p class="floatl marginleft30">
              <strong>Area</strong> <span class="LmsdropdownWithoutJs"> <html:select name="qrcForm" property="instAddrArea" styleId="feasiblArea"
                onchange="javascript:populateFeasibleSocieties('feasibleSociety','feasibleState','feasibleCity','feasiblArea','hiddenProduct'),fillAddressLines('area');">
                  <html:option value="0">Please Select</html:option>
                   <logic:notEmpty  name="qrcForm" property="areaList">
                    <html:optionsCollection name="qrcForm" property="areaList" label="area" value="area" />
                  </logic:notEmpty>
              </html:select><font class="errorTextbox hidden">Please select area</font>
              </span>
            </p>
            <p class="floatl clear">
              <strong>Locality / Sector - Society</strong> <span class="LmsdropdownWithoutJs"> <html:select name="qrcForm" property="instAddrSociety"  styleId="feasibleSociety" onchange="javascript:fillAddressLines('society')">
                  <html:option  value="0">Please Select</html:option >
                  <logic:notEmpty  name="qrcForm" property="societyList">
                    <html:optionsCollection name="qrcForm" property="societyList" label="searchName" value="searchName" />
                  </logic:notEmpty>
              </html:select> <font class="errorTextbox hidden">Please select Locality / Sector - Society</font></span>
            </p>
			<p class="floatl marginleft30">
						<strong>House Number</strong>
							<html:text styleClass="Lmstextboxuppercase" maxlength="10" property="shiftingWorkflowPojo.houseNumber" styleId="" onkeyup="javascript:changeToUpperCase(this)" />
				</p>
				<p class="floatl marginleft30">
						<strong>Landmark</strong>
						  <html:text styleClass="Lmstextboxuppercase" maxlength="25" property="shiftingWorkflowPojo.landmark" styleId="landmarkLms" onkeyup="javascript:changeToUpperCase(this)" />
				</p>
		 <div class="relative inner_left_lead">
		 
			  <br class="clear" /><br class="clear" />
          </div>
          
        
           
              <p class="floatl clear">
                <strong> Remarks<sup class="req">*</sup></strong>
                <html:textarea property="remarksPojo.remarks" name="qrcForm" styleClass="LmsRemarkstextarea" styleId="instAddrRemarks"></html:textarea>
                <span class="hidden"><font class="errorRemarks" style="top:98px;">Please enter Remarks between [2-4000].</font></span>
               <font class="errorRemarks hidden" style="top:98px;">Please enter Remarks.</font>
				
              </p>
             
			  
            </div>
            <div class="floatr inner_right"><p class="clear"></p>	
            <p class="buttonPlacement">
              <logic:equal name="crmRoles" property="available(create_qrc_shifting,update_qrc_shifting)" value="true" scope="session">
             	<a href="#" id="submit_instAddrChange" class="main_button"> <span>Submit</span></a>
             </logic:equal>
            </p>
              <br class="clear">
                <br class="clear">
           </div>
          </html:form>
          <br class="clear">
            <br class="clear">
        </div>
      </div>
      <p class="clear"></p>
	  <div class="LmsTable marginRight10">
	 			 <h4>Remarks Details <span class="lmsMinusImage floatr"></span></h4>
    			 <div class="viewLmsTable margintop10 viewLmsLeadTable">
				<iframe src="logAction.do?method=getRemarks&mappingId=${qrcForm.shiftingWorkflowPojo.workflowId}" scrolling="no" frameborder="0" id="frame"
    	style="border: none; overflow: hidden;width:100%; height: 500px;" allowTransparency="true">
    </iframe>
				</div>
   			 </div>
			 
			 <br class="clear" />
			 </div>
   		</div>
   	</div>