<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<%@taglib uri="/WEB-INF/tlds/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/tlds/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/tlds/displaytag.tld" prefix="display"%>
<%@taglib uri="/WEB-INF/tlds/struts-html.tld" prefix="html"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<link href="css/common.css" rel="stylesheet" media="screen" />
<link href="css/color-${initParam.client }.css" rel="stylesheet" media="screen" />
<link href="css/userManagement.css" rel="stylesheet" media="screen" />
<link href="css/ina.css" rel="stylesheet" media="screen" />
<link href="css/lms.css" rel="stylesheet" media="screen" /> 
<link href="css/masterdata.css" rel="stylesheet" media="screen" />
<script src="javascript/jquery.min.js" type="text/javascript"></script>
<script src="javascript/jquery.validate.js" type="text/javascript"></script>
<script src="javascript/npCrf.js" type="text/javascript"></script>
<script src="javascript/common.js" type="text/javascript"></script>
<script src="javascript/paymentReversal.js" type="text/javascript"></script>
<script src="javascript/paymentPosting.js" type="text/javascript"></script>
<script src="dwr/engine.js" type="text/javascript"></script>
<script src="dwr/util.js" type="text/javascript"></script>
<script src="dwr/interface/crmDwr.js" type="text/javascript"></script>
</head>
<body>
<jsp:include page="/jsp/content/finance/uploadReferenceDoc.jsp"></jsp:include>
<div id="popupSection" >
<html:form action="/paymentPosting" styleId="idPaymentReversal"  method="post">
<html:hidden name="financeForm" property="crmPaymentDetailsPojo.paymentId" styleId="revPaymentId"/>
 <div class="popupSection">
   <h2>Payment Reversal </h2>
   <div class="inner_section">
   <div class="inner_left_lead floatl">
   <p class="floatl ">
   
       <LABEL class="label_check margintop5" for="widthoutIds" style="width:300px !important;">
       	<html:checkbox name="financeForm" property="reversalWOCrf" styleId="widthoutIds" value="true" onchange="paymentRevrsalWidthoutIds(this);">Without Customer ID/CAF Number</html:checkbox>
      </LABEL> 
  </p>
    <div id ="showRevCustomerId">
		 <p class="floatl clear"><strong> Customer ID</strong>
	    	<html:text styleClass="Lmstextbox" name="financeForm" property="crmCustomerDetailsPojo.customerId" styleId="idRevCustomerId" maxlength="15" onblur="getCustomerDetails(this.value,'CUST_ID',true);"></html:text>
	    	<font id="eitherErrorPopup"></font>
	    </p>
	    
	    <span class="floatl paddingTop10 marginleft15"><strong>&nbsp;</strong> OR</span>
	    
	    <p class="floatl marginleft15"><strong>CAF Number</strong>
	   		 <html:text styleClass="Lmstextbox" name="financeForm" property="crmCustomerDetailsPojo.crfId" maxlength="8" styleId="idRevCrfId" onblur="getCustomerDetails(this.value,'CRF_ID',true);"></html:text>
	    </p>
    </div> 
     <p  class="clear floatl">
    <strong>Reversal Reason</strong>
     <span class="LmsdropdownWithoutJs">
     	<html:select property="remarksPojo.reasonId" name="financeForm" styleId="idReversal">
             <html:option value="0">Please Select</html:option>
             <html:optionsCollection name="financeForm" property="paymentReversalReason" label="categoryValue" value="categoryId" />
         </html:select>
     </span>
     </p>
	 
	<p class="floatl marginleft30">
		<strong>Upload Document/ Punch Ticket ID</strong>
		 <LABEL class="label_radio" for="uploadDocument">
		 	<html:radio name="financeForm" property="docSrPaymentReversal" styleId="uploadDocument" value="DOC">Upload Document</html:radio>
		</LABEL> 
		<LABEL class="label_radio" for="punchSRNo">
			<html:radio name="financeForm" property="docSrPaymentReversal" styleId="punchSRNo"  value="SR" >Punch Ticket Number</html:radio>
		</LABEL>
	</p>
	<p class="hide1 floatl  marginleft30" id="punchSRNoShow">
		<strong>Ticket ID</strong>
		 <html:text styleClass="Lmstextbox" name="financeForm" property="crmPaymentDetailsPojo.srId" styleId="idRevSrNoPrReason" maxlength="11" />
	</p>
     <p class="floatl clear paddingTop10 marginBottom30"><strong> Comments</strong>
      <html:textarea  styleClass="LmsRemarkstextarea" property="remarksPojo.remarks" name="financeForm" styleId="idRevRemarks"></html:textarea>
      </p>  
     <br class="clear" />	    
    </div>
     
   <br class="clear" /> 
   <div class="floatr inner_right">
     <a href="#" id="submit_PaymentReverse" class="main_button_multiple"><span>Reverse Payment</span></a>
   </div>
    <br class="clear"/>
</div>
  
 </div>
 
 </html:form>
</div>

</body>
</html>
