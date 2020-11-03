<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%@taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<%@taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@taglib uri="http://struts.apache.org/tags-nested" prefix="nested"%>

<html>
<head>
<script src="javascript/jquery.min.js" type="text/javascript"></script>
<script src="javascript/jquery.validate.js" type="text/javascript"></script>
<link href="css/common.css" rel="stylesheet" media="screen" />
<link href="css/color-${initParam.client }.css" rel="stylesheet" media="screen" />
<link href="css/ina.css" rel="stylesheet" media="screen" />
<link href="css/masterdata.css" rel="stylesheet" media="screen" />
<!-- calender -->
<link rel="stylesheet" type="text/css" href="css/tcal.css" />
<script type="text/javascript" src="javascript/tcal.js"></script>
<link href="css/ina.css" rel="stylesheet" media="screen" />
<link href="css/lms.css" rel="stylesheet" media="screen" />
<link href="css/masterdata.css" rel="stylesheet" media="screen" />
<script src="javascript/common.js" type="text/javascript"></script>
<script src="javascript/npCrf.js" type="text/javascript"></script>
</head>

<body>

<div id="popupSection" >
<html:form action="/crmCap" styleId="editCpeStatus" method="post">
<html:hidden property="orderDetailsPojo.recordId" name="crmCapForm" value="${crmCapForm.orderDetailsPojo.recordId}"/>
 <div class="popupSection">
   <h2>Edit CPE Status</h2>
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
   <div class="inner_left_lead floatl">
   
   <p class="floatl"><strong>Wi-Fi Device Status </strong>
   		<label class="label_radio" for="WiFiDeviceStatusOwnedEdit">
   			<html:radio property="orderDetailsPojo.cpeStatus" name="crmCapForm" styleId="WiFiDeviceStatusOwnedEdit" value="CO"> Customer Owned/ Procured</html:radio>
   		</label>
   		<label class="label_radio" for="WiFiDeviceStatusNextraProvidedEdit">
   			<html:radio property="orderDetailsPojo.cpeStatus"  name="crmCapForm" styleId="WiFiDeviceStatusNextraProvidedEdit" value="NR" > <bean:message bundle="userProp" key="brand.name"/>&nbsp;Recommended</html:radio>
   		</label>
   </p> 
    <p style="height:100px" id="height" class="clear"></p>
   <div id="WiFiDeviceStatusNPEditShow" class="hide1">
    <%-- <p class=" floatl clear">
	    <strong>Device to be installed</strong>  
	       <span class="LmsdropdownWithoutJs">
	            <select  class="select" name="DeviceToBeInstalled">
	                <option value="0">Select Device to be installed</option>
					<option value="1">Nextra</option>
					<option value="2">Radius</option>
	            </select>
	        </span> 
     </p> 
          
     <h4 class="paddingTop10 clear">Wi-Fi Payment Details</h4>
       
   <p class="floatl   "><strong>Wi-Fi receiver required for computer(s)?</strong>
     <LABEL class="label_radio" for="WifiReceiverRequiredYes">
     	<html:radio property="orderDetailsPojo.receiverRequired" name="crmCapForm" styleId="WifiReceiverRequiredYes"  value="true"> Yes</html:radio>
     </LABEL> 
     <LABEL class="label_radio" for="WifiReceiverRequiredNo">
     	<html:radio property="orderDetailsPojo.receiverRequired" name="crmCapForm" styleId="WifiReceiverRequiredNo"  value="false"> No</html:radio>
     </LABEL>
   </p> 
   
   <div class="hide1 floatl marginleft30"  id="WifiReceiverRequiredYesShow">
    <p class=" floatl "  ><strong>No. of Wi-Fi receivers required</strong>
        <html:text styleClass="textbox" name="crmCapForm" property="orderDetailsPojo.receiverNo" maxlength="2" styleId="NoOfWifiReceiverRequired"></html:text>
   </p>
    
   
   <p  class=" floatl marginleft30">
     <strong>Wi-Fi receiver to be installed</strong>  
    <span class="LmsdropdownWithoutJs">
           <select class="select" name="wiFiReceiverInstalledEdit">
                <option value="0">Select Wi-Fi receiver to be installed</option>
				<option value="1">Nextra</option>
				<option value="2">Radius</option>
            </select>
        </span> 
     </p>
    </div>
    
    
     <p class="clear floatl "  ><strong>Upfront Payment Required</strong>
        <label>1800/-</label>
   </p>
   
   <p class="marginleft30 floatl "  ><strong>Total</strong>
   		<html:text styleClass="textbox" name="crmCapForm" property="telecommunicationPayment.amount" maxlength="10" styleId="total"></html:text>
   </p>
   --%>
   <p class="floatl  clear "><strong>Mode of Payment (CPE/Wi-Fi receiver)</strong>
   	 <label class="label_radio" for="ModePaymentReceiverCash">
   	 	<html:radio name="crmCapForm" property="telecommunicationPayment.paymentMode" styleId="ModePaymentReceiverCash"  value="C"> Cash</html:radio>
   	 </label>
     <label class="label_radio" for="ModePaymentReceiverDD">
     	<html:radio name="crmCapForm" property="telecommunicationPayment.paymentMode" styleId="ModePaymentReceiverDD" value="Q" > Cheque/DD</html:radio>
     </label>
     <label class="label_radio" for="ModePaymentReceiverOnline">
     	<html:radio name="crmCapForm" property="telecommunicationPayment.paymentMode" styleId="ModePaymentReceiverOnline" value="O" > Online Transaction</html:radio>
     </label>
   </p> 
   <div id="ModePaymentReceiverCashShow" class="hide1">
	     <h4 class="paddingTop10 clear">Cash Payment</h4>
	     <p class="floatl"><strong>Cash Receipt No.</strong>
	        <html:text styleClass="textbox" name="crmCapForm" property="telecommunicationPayment.receiptNo" styleId="cashReciptNo"></html:text>
	     </p>
	    <%-- <p  class="floatl marginleft30"><strong>Cash Receipt Date</strong>
	        <html:text styleClass="tcal tcalInput" name="crmCapForm"  property="telecommunicationPayment.displayChequeDate" readonly="true" styleId="disPlayDate"></html:text>
	   </p>--%>
	   <div class="clear"></div>
   </div>
   
   <div id="ModePaymentReceiverDDShow"  class="hide1">
	   <h4 class="paddingTop10 clear">Cheque/DD Payment</h4>
	    <p class=" floatl "  ><strong>Cheque/DD No.</strong>
	        <html:text styleClass="textbox" name="crmCapForm" property="telecommunicationPayment.chequeDdNo" maxlength="10" styleId="chequeDDNo" onkeyup="javascript:ValidatenReplaceAlphanumeric(this);"></html:text>
	   </p>
	   <p class="floatl marginleft30"><strong>Bank Name</strong>
				<span class="LmsdropdownWithoutJs">
	   				<html:select property="telecommunicationPayment.bankName" name="crmCapForm" styleId="bankNameId">
	             		<html:option value="0">Select Bank</html:option>
	             		<html:optionsCollection name="crmCapForm" property="bankList"  label="categoryValue" value="categoryValue" />
	      			</html:select>
				</span>
	   </p>
	   <p class="floatl marginleft30">
		 	<strong> Bank Branch</strong>
	 		<html:text styleClass="Lmstextbox"  property="telecommunicationPayment.bankBranch" name="crmCapForm" maxlength="45" styleId="bankBranchNameId"></html:text>
		</p>
	    <p  class=" floatl clear"><strong>City</strong>
	        <html:text styleClass="textbox" name="crmCapForm" property="telecommunicationPayment.bankCity" maxlength="10" styleId="bankCityid" onkeyup="replaceOtherThanAlphaSpace(this.id);" onblur="replaceOtherThanAlphaSpace(this.id);"></html:text>
	   </p>
	   <p  class=" floatl marginleft30"><strong>Cheque/DD Date</strong>
	        <html:text styleClass="tcal tcalInput" name="crmCapForm" property="telecommunicationPayment.displayChequeDate"  readonly="true" styleId="disPlayChkDate"></html:text>
	   </p>
	   <div class="clear"></div>
  </div>
  
   <div id="ModePaymentReceiverOnlineShow"  class="hide1">
	    <h4 class="paddingTop10 clear">Online Payment</h4>
	     <p class=" floatl "><strong>Online Transaction ID</strong>
	        <html:text styleClass="textbox" name="crmCapForm" property="telecommunicationPayment.transactionId" maxlength="10" styleId="transationId"></html:text>
	    </p>
	    <%-- <p  class=" floatl marginleft30"><strong>Online Transaction Date</strong>
	        <html:text styleClass="tcal tcalInput" name="crmCapForm" property="telecommunicationPayment.displayChequeDate" readonly="true" styleId="disPlayOnlineDate"></html:text>
	   </p>--%>
	   <div class="clear"></div>
   </div>
   <p class="floatl "  ><strong>Amount</strong>
   		<html:text styleClass="textbox" name="crmCapForm" property="telecommunicationPayment.amount" maxlength="10" styleId="amountId" onkeyup="javascript:ValidatenReplaceAlphanumericnDot(this);" onclick="removeZeroFromNumber(this);" onfocus="removeZeroFromNumber(this);"></html:text>
   </p>
   </div>
  </div>
   <div class="floatr inner_right">
   <logic:equal name="crmRoles" property="available(update_ina)" value="true" scope="session">
     <a href="#" id="update_CPEStatus" class="main_button"><span>Update</span></a>
    </logic:equal>
   </div>
   <br class="clear"/>
</div>
 </div>
 </html:form>
</div>
</body>
</html>
