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
            <html:hidden name="financeForm" property="crmPodDetailsPojo.podId" styleId="er_cmsId" />
            
            <p class="floatl clear">
              <strong>Customer ID</strong>
              <html:text name="financeForm" property="crmPodDetailsPojo.customerId" styleClass="Lmstextbox" styleId="er_customerId" maxlength="15" onkeyup="javascript:ValidatenReplaceAlphanumeric(this)"></html:text>
            </p>
            <p class="floatl marginleft30">
              <strong>Reciver Name </strong>
              <html:text property="crmPodDetailsPojo.receiverName"  maxlength="11" styleClass="Lmstextbox" styleId="er_instrumentAmount"></html:text>
            </p>
           
           
            <p class=" floatl clear">
              <strong>Customer Relation </strong>
              <html:text property="crmPodDetailsPojo.customerRelation" styleClass="Lmstextbox" styleId="er_instrumentNo" maxlength="6" onkeyup="javascript:ValidatenReplaceAlphanumeric(this)"></html:text>
            </p>
            <p class=" floatl marginleft30">
              <strong>Deliver Date</strong>
              <html:text property="deliverDate" name="financeForm" styleClass="tcal tcalInput" readonly="true" styleId="er_chequeDate" />
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
