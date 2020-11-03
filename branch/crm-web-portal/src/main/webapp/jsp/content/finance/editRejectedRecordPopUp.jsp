<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>Edit Rejected Records</title>
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
<script src="javascript/npCrf.js" type="text/javascript"></script>
<script src="javascript/common.js" type="text/javascript"></script>
<script src="javascript/paymentTracking.js" type="text/javascript"></script>
<script src="javascript/paymentPosting.js" type="text/javascript"></script>
<%@taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<%@taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<%@taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
</head>
<body>
  <div id="popupSection">
    <html:form action="/paymentTracking" styleId="editRejected">
      <div class="popupSection">
        <h2>Edit Rejected Records</h2>
        <div class="inner_section">
          <div class="inner_left_lead floatl">
            <html:hidden name="financeForm" property="toDate" />
            <html:hidden name="financeForm" property="fromDate" />
            <html:hidden name="financeForm" property="crmCmsPaymentPojo.cmsId" styleId="er_cmsId" />
            <p class="floatl clear">
              <strong>Entity Name</strong>
              <html:text property="entity_type" value="Teleservices" readonly="true" styleClass="Lmstextbox"></html:text>
            </p>
            <p class="floatl marginleft30">
              <strong>Customer ID</strong>
              <html:text name="financeForm" property="crmCmsPaymentPojo.ie2" styleClass="Lmstextbox" styleId="er_customerId" maxlength="15" onkeyup="javascript:ValidatenReplaceAlphanumeric(this)"></html:text>
            </p>
            <p class="floatl clear">
              <strong>Paid Amount</strong>
              <html:text property="crmCmsPaymentPojo.instrumentAmount" onclick="removeZeroFromNumber(this);" onfocus="removeZeroFromNumber(this);" onkeyup="javascript:ValidatenReplaceAlphanumericnDot(this)" maxlength="11" styleClass="Lmstextbox" styleId="er_instrumentAmount"></html:text>
            </p>
            <p class="floatl marginleft30">
              <strong> Payment Date</strong>
              <html:text property="paymentDate" name="financeForm" styleClass="tcal tcalInput" readonly="true" styleId="er_paymentDate" />
            </p>
            <h4 class="paddingTop10   clear ">
              <strong> Payment Mode Details 
            </h4>
            <p class=" floatl clear">
              <strong>Cheque Number</strong>
              <html:text property="crmCmsPaymentPojo.instrumentNo" styleClass="Lmstextbox" styleId="er_instrumentNo" maxlength="6" onkeyup="javascript:ValidatenReplaceAlphanumeric(this)"></html:text>
            </p>
            <p class=" floatl marginleft30">
              <strong>Cheque Date</strong>
              <html:text property="chequeDate" name="financeForm" styleClass="tcal tcalInput" readonly="true" styleId="er_chequeDate" />
            </p>
            <p class="paddingTop10 clear "></p>
            <p class=" floatl clear">
              <strong>Bank Name</strong> <span class="LmsdropdownWithoutJs"> <html:select property="crmCmsPaymentPojo.draweeBank" styleId="er_draweeBank">
                  <html:option value="0">Please Select</html:option>
                  <logic:notEmpty name="financeForm" property="bankList">
                    <html:optionsCollection name="financeForm" property="bankList" label="categoryValue" value="categoryId" />
                  </logic:notEmpty>
                </html:select>
              </span>
            </p>
            <p class="floatl marginleft30">
              <strong>Realization Status</strong>
	              <logic:notEmpty name="financeForm" property="crmCmsPaymentPojo.debitCreditFlag">
		                <logic:equal name="financeForm" property="crmCmsPaymentPojo.debitCreditFlag" value="D">
						 <input type="text" readonly="readonly" value="Not Realized" class="Lmstextbox"></input> 
		                </logic:equal>
		                <logic:equal name="financeForm" property="crmCmsPaymentPojo.debitCreditFlag" value="C">
						   <input type="text" readonly="readonly" value="Realized" class="Lmstextbox"></input> 	
		                </logic:equal>
	              </logic:notEmpty>
	              <logic:empty name="financeForm" property="crmCmsPaymentPojo.debitCreditFlag">
					<input type="text" readonly="readonly" value="Clearance Awaited" class="Lmstextbox"></input>               
	              </logic:empty>
            </p>
            <br class="clear" />
          </div>
          <div class="floatr inner_right">
            <a href="#" id="submit_editRejectedRecord" class="main_button"><span>Submit</span></a>
          </div>
          <br class="clear" />
        </div>
      </div>
    </html:form>
  </div>
</body>
</html>
