<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib uri="/WEB-INF/tlds/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/tlds/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/tlds/displaytag.tld" prefix="display"%>
<%@taglib uri="/WEB-INF/tlds/struts-html.tld" prefix="html"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
<script src="javascript/refundPayment.js"></script>
<script src="dwr/engine.js" type="text/javascript"></script>
<script src="dwr/util.js" type="text/javascript"></script>
<script src="dwr/interface/crmDwr.js" type="text/javascript"></script>
</head>
<body>
  <div id="section">
    <form action="/refund" id="refundPaymentForm" method="post">
      <div class="section">
        <h2>Payment Refund Process</h2>
        <span class="success_message"> <logic:messagesPresent message="true" property="appMessage">
            <html:messages id="message" message="true" property="appMessage">
              <bean:write name="message" />
            </html:messages>
          </logic:messagesPresent>
        </span>
        <p class="error_message">
          <html:errors property="appError" />
          <html:errors property="message" />
        </p>
        <div class="inner_section">
          <div class="inner_left_lead  floatl marginleft10">
		  <h4 class=" clear">Customer Details</h4>
            <p class="floatl clear">
              <strong> Customer ID<sup class="req">*</sup>&nbsp;&nbsp;&nbsp; </strong>
            <html:text property="crmCustomerDetailsPojo.customerId" name="financeForm" styleClass="Lmstextbox" styleId="customerId" onblur="getCustomerDetails(this.value,'CUST_ID',true);" maxlength="15" ></html:text>
           <span> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;OR</span>
            <font id="customerIdError"></font>
            </p>
           
            <p class="floatl marginleft30">
             <strong>CAF Number<sup class="req">*</sup>&nbsp;&nbsp;&nbsp; </strong> 
              <html:text property="crmCustomerDetailsPojo.crfId" name="financeForm" styleClass="Lmstextbox" onblur="getCustomerDetails(this.value,'CRF_ID',true);" onkeyup="javascript:changeToUpperCase(this)" styleId="crfId" maxlength="8"></html:text>
              <font id="crfIdError"></font>
            </p>
            <p class="floatl marginleft30">
             <strong>Customer Status </strong>
             <html:text name="financeForm" property="crmCustomerDetailsPojo.status" styleClass="Lmstextbox"  styleId="customerStatus" readonly="true"/>
            </p>
           <p class="floatl marginleft30">
			<strong>Entity Type<sup class="req">*</sup>&nbsp;&nbsp;&nbsp;</strong> <span class="LmsdropdownWithoutJs">
					<html:select name="financeForm"
						property="crmCustomerRefundsPojo.entityType"
						styleId="entityType">
						<html:option value="">Please Select</html:option>
						<logic:notEmpty name="financeForm" property="entityTypeList">
						<html:optionsCollection name="financeForm"
							property="entityTypeList" label="contentName"
							value="contentValue" />
							</logic:notEmpty>
					</html:select>
					
				 
			</span><font id="entityTypeError"></font>
			</p>
            <p class="floatl marginleft30">
             <strong>Service Type </strong>
             <html:text name="financeForm" property="crmCustomerDetailsPojo.serviceType" styleClass="Lmstextbox"  styleId="serviceType" readonly="true" />
            </p>
            <h4 class=" clear paddingTop10">Payment Details</h4>
			 <p class="floatl  clear">
              <strong>Refund Payment Mode<sup class="req">*</sup>&nbsp;&nbsp;&nbsp; </strong>
              <html:radio styleId="chequeId" name="financeForm" property="crmCustomerRefundsPojo.refundMode" value="Q"> 
		      </html:radio>	 Cheque &nbsp;
		       <html:radio styleId="neftId" name="financeForm" property="crmCustomerRefundsPojo.refundMode" value="O"> 
		      </html:radio>	 Online
		      <font id="paymentModeError"></font>
            </p>
			
			<span id="refundByCheque" style="display:none;">
			<p class="floatl marginleft30">
              <strong>Cheque No<sup class="req">*</sup>&nbsp;&nbsp;&nbsp; </strong> 
              <html:text property="crmCustomerRefundsPojo.chequeNumber" name="financeForm" styleClass="Lmstextbox" styleId="chequeNo" maxlength="6" onkeyup="javascript:ValidatenReplaceAlphanumericnDot(this);"></html:text>
              <font id="chequeNoError"></font>
            </p>
            <p class="floatl marginleft30">
              <strong>Cheque Date<sup class="req">*</sup>&nbsp;&nbsp;&nbsp; </strong>
             <html:text property="chequeDate" name="financeForm" styleClass="tcal tcalInput" styleId="chequeDate" maxlength="7"></html:text>
             <font id="chequeDateError"></font>
            </p>
            <p class="floatl marginleft30">
              <strong>Payer Bank Name<sup class="req">*</sup>&nbsp;&nbsp;&nbsp; </strong>
              <span class="LmsdropdownWithoutJs">
			<html:select name="financeForm" styleClass="" styleId="payerBankName" property="crmCustomerRefundsPojo.payerBankName">
			<html:option value="">Please Select</html:option>
			<html:optionsCollection name="financeForm" property="bankList" label="categoryValue" value="categoryId" />
		   </html:select>
			</span>
              <font id="payerBankNameError"></font>
            </p>
			</span>
			
			 <span id="refundByNEFT" style="display:none;">
            <p class="floatl marginleft30">
              <strong>NEFT Transaction ID<sup class="req">*</sup>&nbsp;&nbsp;&nbsp; </strong> 
              <html:text property="crmCustomerRefundsPojo.transactionId" name="financeForm" styleClass="Lmstextbox" styleId="neftTransactionId" maxlength="15"></html:text>
              <font id="neftTransactionIdError"></font>
            </p>
            <p class="floatl marginleft30">
              <strong>Beneficiary A/c Number<sup class="req">*</sup>&nbsp;&nbsp;&nbsp; </strong>
             <html:text property="crmCustomerRefundsPojo.custAccountNumber" name="financeForm" styleClass="Lmstextbox" styleId="beneficiaryAccNo" maxlength="16" onkeyup="javascript:ValidatenReplaceAlphanumericnDot(this);"></html:text>
             <font id="beneficiaryAccNoError"></font>
            </p>
            <p class="floatl marginleft30">
              <strong>Beneficiary Bank Name<sup class="req">*</sup>&nbsp;&nbsp;&nbsp; </strong>
              <span class="LmsdropdownWithoutJs">
			<html:select name="financeForm" styleClass="" styleId="beneficiaryBankName" property="crmCustomerRefundsPojo.custBankName">
			<html:option value="">Please Select</html:option>
			<html:optionsCollection name="financeForm" property="bankList" label="categoryValue" value="categoryId" />
		   </html:select>
			</span>
             <font id="beneficiaryBankNameError"></font>
            </p>
             <p class="floatl marginleft30">
              <strong>IFSC Code<sup class="req">*</sup>&nbsp;&nbsp;&nbsp; </strong>
             <html:text property="crmCustomerRefundsPojo.ifscCode" name="financeForm" styleClass="Lmstextbox" styleId="ifscCode" maxlength="11"></html:text>
             <font id="ifscCodeError"></font>
            </p>
			</span>
			
			 <p class="floatl  clear">
              <strong>Refund Amount<sup class="req">*</sup>&nbsp;&nbsp;&nbsp; </strong> 
              <html:text property="crmCustomerRefundsPojo.refundAmount" name="financeForm" styleClass="Lmstextbox" styleId="refAmount" maxlength="8" onclick="removeZeroFromNumber(this);" onfocus="removeZeroFromNumber(this);" onkeyup="javascript:ValidatenReplaceAlphanumericnDot(this);"></html:text>
               <font id="refundAmountError"></font>
            </p>
            <p class="floatl marginleft30">
              <strong>Refund Process Status<sup class="req">*</sup>&nbsp;&nbsp;&nbsp; </strong>
			  <span class="LmsdropdownWithoutJs">
              <html:select property="crmCustomerRefundsPojo.status" name="financeForm" styleId="refProcess">
              <html:option value="">Please select</html:option>
              <logic:notEmpty name="financeForm" property="paymentStatusList">
			  <html:optionsCollection name="financeForm" property="paymentStatusList" label="contentName" value="contentValue"/>   
			  </logic:notEmpty>
              </html:select>
             </span><font id="refundProcessError"></font>
            </p>
            <p class="floatl marginleft30" style="display: none;" id="refProcessDateBlock">
              <strong>Refund Process Date<sup class="req">*</sup>&nbsp;&nbsp;&nbsp; </strong>
             <html:text property="paymentDate" name="financeForm" styleClass="tcal tcalInput" readonly="true" styleId="refProcessDate" maxlength="7"></html:text>
             <font id="refundProcessDateError"></font>
            </p>
            <p class="floatl clear">
		    <strong>Ticket ID<sup class="req">*</sup>&nbsp;&nbsp;&nbsp;</strong>
		     <html:text property="crmCustomerRefundsPojo.ticketID" name="financeForm" maxlength="20" styleClass="Lmstextbox" styleId="ticketId" />
		     <font id="ticketIdError"></font>
	        </p>
            <p class="floatl clear">
		    <strong> Remarks<sup class="req">*</sup>&nbsp;&nbsp;&nbsp;</strong>
		    <html:textarea property="remarksPojo.remarks" name="financeForm" styleClass="LmsRemarkstextarea" styleId="remarks"></html:textarea>
		    <font id="remarksError"></font>
	       </p> 
          </div>
          <div class="floatr inner_right">
          <%--<c:if test="${financeForm.displayButton}"> --%>
            <a href="#" id="REFUND_SUBMIT" class="main_button"><span>Submit</span></a>
          <%--</c:if>--%>
          </div>
          <p class="clear"></p>
        </div>
       
      </div>
    </form>
  </div>
</body>
</html>