<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%@taglib uri="/WEB-INF/tlds/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/tlds/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/tlds/displaytag.tld" prefix="display"%>
<%@taglib uri="/WEB-INF/tlds/struts-html.tld" prefix="html"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<style>
.ui-autocomplete {
  max-height: 150px;
  overflow-y: auto;
}
</style>
<script type="text/javascript" src="javascript/jquery-ui.js"></script>
<link rel="stylesheet" href="css/jquery-ui.css" />
<html>
<body>
<div class="loadingPopup hidden"></div>
<div id="section"  align="center">
<html:form action="/crmCap"  method="post" styleId="mapMacId">
<logic:equal property="orderDetailsPojo.cpeStatus"  name="crmCapForm" value="CO">
  <html:hidden property="orderDetailsPojo.cpeStatus" name="crmCapForm" value="${crmCapForm.orderDetailsPojo.cpeStatus}" styleId="hiddenWiFiDeviceStatusOwnedEdit"/>
  <html:hidden property="telecommunicationPayment.paymentMode" name="crmCapForm" value="${crmCapForm.telecommunicationPayment.paymentMode}" styleId="hiddenPaymentMode"/>
  <html:hidden property="telecommunicationPayment.receiptNo" name="crmCapForm" value="${crmCapForm.telecommunicationPayment.receiptNo}" styleId="hiddenReceiptNo"/>
  <html:hidden property="telecommunicationPayment.displayChequeDate" name="crmCapForm" value="${crmCapForm.telecommunicationPayment.displayChequeDate}" styleId="hiddenDisplayChequeDate"/>
  <html:hidden property="telecommunicationPayment.chequeDdNo" name="crmCapForm" value="${crmCapForm.telecommunicationPayment.chequeDdNo}" styleId="hiddenChequeDdNo"/>
  <html:hidden property="telecommunicationPayment.bankName" name="crmCapForm" value="${crmCapForm.telecommunicationPayment.bankName}" styleId="hiddenBankName"/>
  <html:hidden property="telecommunicationPayment.bankBranch" name="crmCapForm" value="${crmCapForm.telecommunicationPayment.bankBranch}" styleId="hiddenBankBranchName"/>
  <html:hidden property="telecommunicationPayment.bankCity" name="crmCapForm" value="${crmCapForm.telecommunicationPayment.bankCity}" styleId="hiddenBankCity"/>
  <html:hidden property="telecommunicationPayment.transactionId" name="crmCapForm" value="${crmCapForm.telecommunicationPayment.transactionId}" styleId="hiddenTransactionId"/>
  <logic:empty name="crmCapForm" property="telecommunicationPayment.amount" >
    <html:hidden property="telecommunicationPayment.amount" name="crmCapForm" value="0" styleId="hiddenAmount"/>
  </logic:empty>
  <logic:notEmpty name="crmCapForm" property="telecommunicationPayment.amount">
    <html:hidden property="telecommunicationPayment.amount" name="crmCapForm" value="${crmCapForm.telecommunicationPayment.amount}" styleId="hiddenAmount"/>
  </logic:notEmpty>
</logic:equal>
<html:hidden property="telecommunicationPayment.customerId" name="crmCapForm" value="${crmCapForm.customerDetailsPojo.customerId}" styleId="telcomCustomer"/>
<html:hidden property="orderDetailsPojo.recordId" name="crmCapForm" value="${crmCapForm.orderDetailsPojo.recordId}" styleId="hiddOrderRecordID"/>
<html:hidden property="customerDetailsPojo.crfId" name="crmCapForm" value="${crmCapForm.customerDetailsPojo.crfId}" styleId="hiddenCRFId"/>
<logic:notEmpty property="customerDetailsPojo.activationDate" name="crmCapForm">
	<input type="hidden" name="hiddenActivationDate" id="hiddenActivationDate" value="${crmCapForm.customerDetailsPojo.activationDate}" />
</logic:notEmpty>
<html:hidden property="forward" name="crmCapForm" value="${crmCapForm.forward}" styleId="forwardId"/>
<html:hidden property="product" name="crmCapForm" value="${crmCapForm.customerDetailsPojo.product}" styleId="productId"/>
<html:hidden property="activationDate" name="crmCapForm" value="${crmCapForm.activationDate}" styleId="ActivationDate"/>
<html:hidden property="customerDetailsPojo.product" name="crmCapForm" value="${crmCapForm.customerDetailsPojo.product}" styleId="ProductId"/>
<html:hidden property="customerDetailsPojo.serviceType" name="crmCapForm" value="${crmCapForm.customerDetailsPojo.serviceType}" styleId="ServiceTypeId"/>
<html:hidden property="crmNetworkConfigurations.serviceModel" name="crmCapForm" value="${crmCapForm.crmNetworkConfigurations.serviceModel}" styleId="hiddenOntRgMduDone"/>
<html:hidden property="crmNetworkConfigurations.serviceType" name="crmCapForm" value="${crmCapForm.crmNetworkConfigurations.serviceType}" styleId="hiddenServiceType"/>
<html:hidden property="crmNetworkConfigurations.vlanId" name="crmCapForm" value="${crmCapForm.crmNetworkConfigurations.vlanId}" styleId="hiddenVlanId"/>
<html:hidden property="crmNetworkConfigurations.oltNoteId" name="crmCapForm" value="${crmCapForm.crmNetworkConfigurations.oltNoteId}" styleId="hiddenOltNoteId"/>
<html:hidden property="crmNetworkConfigurations.oltSlot" name="crmCapForm" value="${crmCapForm.crmNetworkConfigurations.oltSlot}" styleId="hiddenOltSlot"/>
<html:hidden property="crmNetworkConfigurations.oltPort" name="crmCapForm" value="${crmCapForm.crmNetworkConfigurations.oltPort}" styleId="hiddenOltPort"/>
<html:hidden property="crmNetworkConfigurations.oltSubPort" name="crmCapForm" value="${crmCapForm.crmNetworkConfigurations.oltSubPort}" styleId="hiddenOltSubPort"/>
<html:hidden property="crmNetworkConfigurations.option82" name="crmCapForm" styleId="option82Id" value="${crmCapForm.crmNetworkConfigurations.option82}" />
<html:hidden property="crmNetworkConfigurations.macBind" name="crmCapForm" value="${crmCapForm.crmNetworkConfigurations.macBind}" styleId="macBindId"/>
<html:hidden property="crmNetworkConfigurations.recordId" name="crmCapForm" value="${crmCapForm.crmNetworkConfigurations.recordId}"/>
<html:hidden name="crmCapForm" property="customerDetailsPojo.customerId" value="${crmCapForm.customerDetailsPojo.customerId}" />
<input type="hidden" name="crmCapForm" value="${crmCapForm.customerDetailsPojo.npId}" id="partnerId"/>

 <div class="section">
   <h2>Map MAC ID</h2>
   <div class="inner_section">
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
	<div class="error_message"><logic:notEqual name="crmCapForm" property="crmNetworkConfigurations.macBind" value="Y">MAC address is yet to bind.</logic:notEqual></div>
	<!-- validation form enteries -->
    <div class="relative">
	    <div class="clear marginleft10 inner_left_lead floatl">
	    	<h4>Network Partner</h4> 
    		<p class="floatl" ><strong>
           		<logic:notEqual name="crmCapForm" property="customerDetailsPojo.npId" value="0">
      		 		<label>
      		 			<bean:write name="crmRoles" property="displayEnum(PartnerPojo,${crmCapForm.customerDetailsPojo.npId})" scope="session" />
      		 		</label>
       	 		</logic:notEqual>
       	 		<logic:equal name="crmCapForm" property="customerDetailsPojo.npId" value="0">-</logic:equal>
       		</strong></p>
       	</div>
       	<div class="marginleft10 inner_left_lead floatl">
	    	<h4 class="floatl">Service Partner</h4> 
	    		<p class="clear floatl" ><strong>
	           		<logic:notEqual name="crmCapForm" property="customerDetailsPojo.spId" value="0">
	      		 		<label>
	      		 			<bean:write name="crmRoles" property="displayEnum(PartnerPojo,${crmCapForm.customerDetailsPojo.spId})" scope="session" />
	      		 		</label>
	       	 		</logic:notEqual>
	       	 		<logic:equal name="crmCapForm" property="customerDetailsPojo.spId" value="0">-</logic:equal>
	       		</strong></p>
	    </div>
	</div>
 <div class="relative">
    <div class="clear marginleft10 inner_left_lead floatl">
    <% boolean showValue = false; %>
    <h4>Device Details</h4>
      <p class="floatl">
      <strong>Wi-Fi Device Status </strong>
       <logic:equal name="crmCapForm" property="customerDetailsPojo.product" value="EOC">
       <% showValue = true; %>
     	    <LABEL class="label_radio" for="WiFiDeviceStatusNextraProvidedEdit">
     		   <html:radio property="orderDetailsPojo.cpeStatus"  name="crmCapForm" styleId="WiFiDeviceStatusNextraProvidedEdit" value="NR" > <bean:message bundle="userProp" key="brand.name"/>&nbsp;Recommended</html:radio>
     	    </LABEL>
	  </logic:equal>
	 <logic:notEqual name="crmCapForm" property="customerDetailsPojo.product" value="EOC">
	 	<LABEL class="label_radio" for="WiFiDeviceStatusOwnedEdit">
			 <html:radio property="orderDetailsPojo.cpeStatus" name="crmCapForm" styleId="WiFiDeviceStatusOwnedEdit" value="CO" disabled="${ crmCapForm.orderDetailsPojo.cpeStatus eq 'NR' ? 'true' : '' }">  Customer Owned / Procured</html:radio>
    	</LABEL> 
        <LABEL class="label_radio" for="WiFiDeviceStatusNextraProvidedEdit">
     	     <html:radio property="orderDetailsPojo.cpeStatus"  name="crmCapForm" styleId="WiFiDeviceStatusNextraProvidedEdit" value="NR" disabled="${ crmCapForm.orderDetailsPojo.cpeStatus eq 'CO' ? 'true' : '' }"> <bean:message bundle="userProp" key="brand.name"/>&nbsp;Recommended</html:radio>
     	</LABEL>
     <c:if test="${ (crmCapForm.orderDetailsPojo.cpeStatus eq 'CO')  }">  
     	 <logic:equal name="crmRoles" property="available(update_ina)" value="true" scope="session">
      		 <LABEL>
     	  		<a href="javascript:editCPEStatus();" class="yellow_button">Edit</a>
       		</LABEL>
    	</logic:equal>
	</c:if>
	 </logic:notEqual>
   </p>
   <p class="<%= showValue ? "" : "clear" %> floatl"><strong>ONT / RG / MDU Port configuration done ? </strong>
     <LABEL class="label_radio" for="portConfigurationYes">
     	<html:radio name="crmCapForm" property="crmNetworkConfigurations.spOntRgMduDone" styleId="portConfigurationYes" value="true"> Yes</html:radio>
     </LABEL> 
     <LABEL class="label_radio" for="portConfigurationNo">
     	<html:radio name="crmCapForm" property="crmNetworkConfigurations.spOntRgMduDone" styleId="portConfigurationNo" value="false" > No</html:radio>
     </LABEL>
     <font></font>
   </p>
   <c:if test="${ (crmCapForm.crmNetworkConfigurations.macBind eq 'Y') and (crmCapForm.customerDetailsPojo.product eq 'EOC') }">  
	   <p class="<%= showValue ? "" : "clear" %> floatl marginleft30"><strong>MAC Faulty </strong>
	     <LABEL class="label_radio" for="macFaultyYes">
	     	<html:radio name="crmCapForm" property="macFaulty" styleId="macFaultyYes" value="true"> Yes</html:radio>
	     </LABEL> 
	     <LABEL class="label_radio" for="macFaultyNo">
	     	<html:radio name="crmCapForm" property="macFaulty" styleId="macFaultyNo" value="false" > No</html:radio>
	     </LABEL>
	     <font></font>
	   </p>
   </c:if>
   <logic:notEqual name="crmCapForm" property="customerDetailsPojo.product" value="EOC">
   		<p class="floatl clear " ><strong>CPE MAC ID</strong>
        <span class="relative inlineBlock">
        	<html:text styleClass="Lmstextbox" name="crmCapForm" property="crmNetworkConfigurations.currentCpeMacId" maxlength="14" styleId="cpemacid" onkeypress='javascript:autoFormatMacID(event,this);'></html:text><font></font>
       	</span>
        <a href="#" id="bind_CPEMacId" class="marginleft15 yellow_button">Bind CPE MAC ID</a>
	   </p>
   </logic:notEqual>
   <logic:equal name="crmCapForm" property="customerDetailsPojo.product" value="EOC">
   	<p class="clear floatl" ><strong>Primary MAC Address</strong>
        <span class="relative inlineBlock">
        	<html:text styleId="primaryMACAddrId" styleClass="Lmstextbox" name="crmCapForm" property="crmNetworkConfigurations.currentCpeMacId" maxlength="14" onkeypress='javascript:autoFormatMacID(event,this);'></html:text>	<font></font>
       	</span>
   </p>
    <p class="floatl marginleft30" >
       	<strong>Secondary MAC Address <logic:equal name="crmCapForm" property="crmNetworkConfigurations.macBind" value="Y"><span style="font-weight: normal;">(<bean:write property="crmNetworkConfigurations.currentSlaveMacId" name="crmCapForm"/>)</span></logic:equal></strong> 
       	 <%--<span class="LmsdropdownWithoutJs">
       	<html:select property="crmNetworkConfigurations.currentSlaveMacId" name="crmCapForm" styleId="secondaryMACAddrId" onchange="fillDataPrimaryMacAddr(this.value,'primaryMACAddrId')">
               <html:option value="">Select MAC Address</html:option>
                <logic:notEmpty  name="crmCapForm" property="secondaryMACAddrList">
               		<html:options name="crmCapForm" property="secondaryMACAddrList" />
               </logic:notEmpty>
           </html:select> 
         </span>  --%>
		<html:text styleId="secondaryMacTextId" styleClass="Lmstextbox" name="crmCapForm" property="crmNetworkConfigurations.currentSlaveMacId" onkeyup="getSecondaryMACAddr('primaryMACAddrId');"></html:text> 
		  <font></font>
		 <html:hidden name="crmCapForm" property="crmNetworkConfigurations.currentSlaveMacId" styleId="currentSlaveMacId"/>
        <font></font>
       
        <a href="#" id="bind_CPESlaveMacId" class="marginleft15 yellow_button">Bind CPE Slave MAC ID</a>
   	</p>
   </logic:equal>
    <br class="paddingTop10"/>
   
  <span class="clear floatl relative paddingTop10"><strong> Action Type</strong>
 	<span class="LmsdropdownWithoutJs">
      <html:select property="remarksPojo.actions" name="crmCapForm" styleId="actionTypeId" >
             <html:option value="">Select Action</html:option>
             <html:option value="Reject By SP">Reject</html:option>
             <html:option value="Approve By SP">Approve</html:option>
             <html:option value="Customer Refusal By SP">SRP</html:option>
             <html:option value="Change Feasible Address">Change Feasible Address</html:option>
              <html:option value="ERP">ERP</html:option>
             <html:option value="Cancellation">Cancel CAF</html:option>
            
         </html:select>   
    </span>
	 <font></font>
 </span>

<div id="macRefusalDivIdComment" class="hide1">
   <p class="floatl relative marginleft30"><strong>Reason for Rejection</strong>
 	<span class="LmsdropdownWithoutJs">
      <html:select property="remarksPojo.reasonId" name="crmCapForm" styleId="rejectForReasonId"  onchange="return widthChangeBR(this.value);">
             <html:option value="">Select Reason</html:option>
             <logic:notEmpty  name="crmCapForm" property="rejectionReasonList">
		          <html:optionsCollection name="crmCapForm" property="rejectionReasonList" label="categoryValue" value="categoryId" />
		    </logic:notEmpty>
         </html:select>   
    </span>
	 <font></font>
   </p>
</div> 

<div id="CustomerRefuseReasonYesShow" class="hide1"> 
   <p class="floatl marginleft30 relative"><strong>Reason for Refusal</strong>  
    <span class="LmsdropdownWithoutJs">
            <html:select property="remarksPojo.reasonId" name="crmCapForm" styleId="reasonForRefusalId"  onchange="return widthChangeBR(this.value);">
               <html:option value="">Select Reason </html:option>
                <logic:notEmpty  name="crmCapForm" property="refuselReasonList">
               		<html:optionsCollection name="crmCapForm" property="refuselReasonList" label="categoryValue" value="categoryId" />
               </logic:notEmpty>
           </html:select>
        </span> 
		<font></font>
   </p>
   </div>
  <%--  <div class="clear createBorder" id="cdcfFormId">
    	<h4>Upload CDCF Form</h4>
        <c:url value="${initParam.dmshost }/np-document-upload/files/upload/INA/${ crmCapForm.customerDetailsPojo.crfId }" var="dmsUrl"></c:url>
        http://10.20.0.12:8080/suncity/JSPs/AddDoc_order.jsp?CAFNO=${ crmCapForm.customerDetailsPojo.crfId }&CustomerID=${ crmCapForm.customerDetailsPojo.customerId }&CustomerName=${ crmCapForm.customerDetailsPojo.custFname }+${ crmCapForm.customerDetailsPojo.custLname }&RMN=${ crmCapForm.customerDetailsPojo.rmn }&RTN=${ crmCapForm.customerDetailsPojo.rtn }    	
    	<iframe src="${dmsUrl }"
    	scrolling="yes" frameborder="0"
    	style="border: none; overflow: hidden; width: 755px; height: 400px;" allowTransparency="true"></iframe>
    </div>
     --%>
    <%-- <p class=" floatl marginleft30" ><strong>Upload CDCF Form</strong>
        <span class="importISR">
          <input class="importInputFile " name="fileUpload" type="file" onchange="javascript:importTemplate(event,'emailTemplateBody','E')">
            <a href="#" id="" class=" yellow_button marginleft6 importLink">Browse</a>
        </span>
    </p> --%>

<div id="cancellationDivIdComment" class="hide1">
   <p class="floatl marginleft30 relative"><strong>Reason for Cancellation</strong>
 	<span class="LmsdropdownWithoutJs">
      <html:select property="remarksPojo.reasonId" name="crmCapForm" styleId="cancelForReasonId"  onchange="return widthChangeBR(this.value);">
             <html:option value="">Select Reason</html:option>
             <logic:notEmpty  name="crmCapForm" property="cancellationReasonList">
		          <html:optionsCollection name="crmCapForm" property="cancellationReasonList" label="categoryValue" value="categoryId" />
		    </logic:notEmpty>
         </html:select>   
    </span>
	 <font></font>
   </p>
</div> 
<div id="erpDivIdComment" class="hide1">
   <p class="floatl marginleft30 relative"><strong>Reason for ERP</strong>
 	<span class="LmsdropdownWithoutJs">
      <html:select property="remarksPojo.reasonId" name="crmCapForm" styleId="erpForReasonId"  onchange="return widthChangeBR(this.value);">
             <html:option value="">Select Reason</html:option>
             <logic:notEmpty  name="crmCapForm" property="erpReasonList">
		          <html:optionsCollection name="crmCapForm" property="erpReasonList" label="categoryValue" value="categoryId" />
		    </logic:notEmpty>
         </html:select>   
    </span>
	 <font></font>
   </p>
</div> 

   <br class="paddingTop10"/>
   <p class="floatl paddingTop10 clear">
   	<strong>Remarks <span class="error_message"></span> </strong>
      	<html:textarea styleClass="LmsRemarkstextarea" name="crmCapForm" property="remarksPojo.remarks" styleId="aproveComment"></html:textarea><font style='width:258px !important'></font>
   </p> 
  <br class="clear" />
  </div>
      <div class="floatr inner_right hide1" id="macSubmitButton" >	    
	    <p class="buttonPlacement">	   
	    	<logic:equal name="crmRoles" property="available(create_ina,update_ina)" value="true" scope="session">
	       		<a href="javascript:;" id="submit_mapMacId" class="main_button marginleft10"><span>Submit</span></a>
	     	</logic:equal>
	     </p>
    </div>
       <br class="clear" />
    </div>
       <!-- validation form enteries -->
       
    <!-- include table -->
    <div id="contentDiv"></div>
	<!-- include table -->
		<div class="LmsTable">
		 	<h4>Customer Details <span class="lmsMinusImage floatr"></span></h4>
		     <div class="viewLmsTable margintop10 viewLmsLeadTable">
		   	 	<jsp:include page="/jsp/content/cap/view-InA.jsp"></jsp:include>
			</div>
		</div>
	<div class="LmsTable">
	 			 <h4>Remarks Details <span class="lmsMinusImage floatr"></span></h4>
    			 <div class="viewLmsTable margintop10 viewLmsLeadTable">
				<iframe src="logAction.do?method=getRemarks&mappingId=${crmCapForm.customerDetailsPojo.recordId}" scrolling="no" frameborder="0" id="frame"
    	style="border: none; overflow: hidden;width:100%; height: 500px;" allowTransparency="true">
    </iframe>
				</div>
				<h4>
			   Activity Log Details <span class="lmsMinusImage floatr"></span>
			  </h4>
				
				 <div class="viewLmsTable margintop10 viewLmsLeadTable">
	 			<iframe src="jsp/content/masterdata/displayAuditLog.jsp" frameborder="0" scrolling="yes"
             		 style="border: none; overflow: hidden; width: 100%; height:250px;" allowTransparency="true" onload="iframeLoaded(this)"></iframe>
					 </div>
   			 </div>
      </div>
    </div>
 </html:form>
</div>
</body>
</html>
