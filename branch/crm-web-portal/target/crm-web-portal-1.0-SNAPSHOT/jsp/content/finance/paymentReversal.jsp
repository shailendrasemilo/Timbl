<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib uri="/WEB-INF/tlds/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/tlds/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/tlds/displaytag.tld" prefix="display"%>
<%@taglib uri="/WEB-INF/tlds/struts-html.tld" prefix="html"%>
<html>
<head>
<link href="css/colorbox.css" rel="stylesheet" />
<script src="javascript/jquery.colorbox.js"></script>
<script>
	$( document ).ready( function(){
		$( ".iframe5" ).colorbox( {
			iframe : true, width : "950px", height : "500px"
		} );
	} );
</script>
<script src="javascript/paymentReversal.js" type="text/javascript"></script>
</head>
<body>
  <div id="section" align="center">
    <html:form action="/paymentPosting" method="post" styleId="idSearchPaymentReversal">
      <html:hidden name="financeForm" property="reversalWOCrf" styleId="idFinReversalWOCrf" value="${financeForm.reversalWOCrf}" />
      <html:hidden name="financeForm" property="crmCustomerDetailsPojo.customerId" styleId="idFinCustomerId"
        value="${financeForm.crmCustomerDetailsPojo.customerId}" />
      <html:hidden name="financeForm" property="crmCustomerDetailsPojo.crfId" styleId="idFinCrfId" value="${financeForm.crmCustomerDetailsPojo.crfId}" />
      <html:hidden name="financeForm" property="remarksPojo.reasonId" styleId="idFinReversalReason" value="${financeForm.remarksPojo.reasonId}" />
      <html:hidden name="financeForm" property="remarksPojo.remarks" styleId="idFinRemarks" value="${financeForm.remarksPojo.remarks}" />
      <html:hidden name="financeForm" property="docSrPaymentReversal" styleId="idFindocSrPaymentReversal" value="${financeForm.docSrPaymentReversal}" />
      <html:hidden name="financeForm" property="crmPaymentDetailsPojo.srId" styleId="idFinSrNoPrReason" value="${financeForm.crmPaymentDetailsPojo.srId}" />
      <html:hidden name="financeForm" property="crmPaymentDetailsPojo.paymentId" styleId="idPaymentId" />
      <html:hidden name="financeForm" property="remarksPojo.mappingId" styleId="idMappingId" />
      <html:hidden property="remarksPojo.createdBy" value="${sessionScope.userPojo.userId}" />
      <div class="section">
        <h2>Payment Reversal</h2>
        <div class="success_message">
          <logic:messagesPresent message="true">
            <html:messages id="message" message="true">
              <bean:write name="message" />
            </html:messages>
          </logic:messagesPresent>
        </div>
        <div class="inner_section">
          <span class="error_message"><html:errors property="appError" /></span>
          <!-- validation form enteries -->
          <div class="relative">
            <div class="clear marginleft10 inner_left_lead floatl">
              <p class="floatl ">
                <strong> Customer ID<span class="error_message verticalalignTop">*</span></strong>
                <html:text styleClass="Lmstextbox" styleId="pr_custId" name="financeForm" property="customerId" maxlength="15"
                  onblur="getCustomerDetails(this.value,'CUST_ID',true);"></html:text>
                <font id="eitherError"></font>
              </p>
              <span class="floatl paddingTop10 marginleft15"><strong>&nbsp;</strong> OR</span>
              <p class="floatl marginleft15">
                <strong>CAF Number<span class="error_message verticalalignTop">*</span></strong>
                <html:text styleClass="Lmstextbox" name="financeForm" property="crfId" maxlength="8"
                  styleId="uploadDocCRF" onblur="getCustomerDetails(this.value,'CRF_ID',true);"></html:text>
              </p>
              <p class="floatl marginleft30">
                <strong>Last Payment Date<span class="error_message verticalalignTop">*</span></strong>
                <html:text styleClass="tcal tcalInput" name="financeForm" property="toDate" readonly="readonly" />
              </p>
              <b class="clear margintop40 inlineBlock">&nbsp;</b> <b class="clear margintop40 inlineBlock">&nbsp;</b> <b class="clear margintop40 inlineBlock">&nbsp;</b>
            </div>
            <div class="floatr inner_right">
              <p class="buttonPlacement">
                <a href="#" id="search_PR" class="main_button marginleft10"><span>Search</span></a>
              </p>
            </div>
            <br class="clear" />
          </div>
          <!-- validation form enteries -->
        </div>
        <!-- display table-->
        <div class="marginleft10">
          <logic:notEmpty name="financeForm" property="searchPaymentList">
            <display:table export="false" id="data" name="${financeForm.searchPaymentList}" requestURI="paymentPosting.do?method=searchPaymentDetails"
              class="dataTable" style="width:100%" pagesize="15">
              <display:setProperty name="paging.banner.placement" value="bottom" />
              <display:column title="Customer ID / CAF No." sortable="false">
                <logic:notEmpty name="data" property="customerId">
                  <a href="paymentTracking.do?method=viewPayment&paymentId=${data.paymentId}" class="viewMorePayment_InvDetails iframe5"><bean:write
                      name="data" property="customerId" /></a>
                </logic:notEmpty>
                <logic:empty name="data" property="customerId">
                  <logic:notEmpty name="data" property="crfId">
                    <a href="paymentTracking.do?method=viewPayment&paymentId=${data.paymentId}" class="viewMorePayment_InvDetails iframe5"><bean:write
                        name="data" property="crfId" /></a>
                  </logic:notEmpty>
                </logic:empty>
              </display:column>
              <display:column title="Installation Status">
                <bean:write name="crmRoles" property="displayEnum(InstallationStatus,${ data.installationStatus})" scope="session" />
              </display:column>
              <display:column title="Entity Name">
                <bean:write name="crmRoles" property="displayEnum(EntityType,${ data.entityType})" scope="session" />
              </display:column>
              <display:column property="amount" title="Paid Amount" />
              <display:column title="Payment Date">
                <logic:empty name="data" property="paymentDate">
						-
					</logic:empty>
                <logic:notEmpty name="data" property="paymentDate">
                  <bean:write name="crmRoles" property="toDate(${ data.paymentDate})" scope="session" />
                </logic:notEmpty>
              </display:column>
             <display:column title="Posting Date">
              <bean:write name="crmRoles" property="toDate(${data.postingDate})" />
            </display:column>
              <display:column title="Payment Status">
                <bean:write name="crmRoles" property="displayEnum(PaymentStatus,${ data.paymentStatus})" scope="session" />
              </display:column>
              <display:column title="Payment Channel">
                <bean:write name="crmRoles" property="displayEnum(PaymentChannel,${ data.paymentChannel})" scope="session" />
              </display:column>
              <display:column title="Payment Mode">
                <bean:write name="crmRoles" property="displayEnum(PaymentMode,${ data.paymentMode})" scope="session" />
              </display:column>
              <display:column title="Cheque No./ Receipt No./ Transaction ID">
                <logic:notEmpty name="data" property="receiptNo">
                  <bean:write name="data" property="receiptNo" />
                </logic:notEmpty>
                <logic:notEmpty name="data" property="chequeDdNo">
                  <bean:write name="data" property="chequeDdNo" />
                </logic:notEmpty>
                <logic:notEmpty name="data" property="transactionId">
                  <bean:write name="data" property="transactionId" />
                </logic:notEmpty>
              </display:column>
              <display:column title="Cheque Date">
                <logic:empty name="data" property="chequeDate">
						-
					</logic:empty>
                <logic:notEmpty name="data" property="chequeDate">
                  <bean:write name="crmRoles" property="toDate(${ data.chequeDate})" scope="session" />
                </logic:notEmpty>
              </display:column>
              <display:column title="Bank Name">
                <logic:empty name="data" property="bankName">
						-
					</logic:empty>
                <logic:notEmpty name="data" property="bankName">
                  <bean:write name="crmRoles" property="displayEnum(BANK,${ data.bankName})" scope="session" />
                </logic:notEmpty>
              </display:column>
              <display:column title="Realization Status">
                <logic:empty name="data" property="realzationStatus">
						-
					</logic:empty>
                <logic:notEmpty name="data" property="realzationStatus">
                  <bean:write name="crmRoles" property="displayEnum(RealizationStatus,${ data.realzationStatus})" scope="session" />
                </logic:notEmpty>
              </display:column>
              <display:column title="Reversal">
                <logic:notEmpty name="data" property="customerId">
                  <logic:equal name="data" property="status" value="PR">
						Already Reversed
					</logic:equal>
                  <logic:notEqual name="data" property="status" value="PR">
                    <a href="paymentPosting.do?method=paymentReversalPopUp&paymentId=${data.paymentId}" style="cursor: pointer"
                      class="viewMorePayment_InvDetails iframe5">Reverse</a>
                  </logic:notEqual>
                </logic:notEmpty>
                <logic:empty name="data" property="customerId">
                  Not Applicable - CAF Not Submitted
                </logic:empty>
              </display:column>
            </display:table>
          </logic:notEmpty>
          <logic:empty name="financeForm" property="searchPaymentList">
            <b> <html:errors property="noRecordFound" />
            </b>
          </logic:empty>
        </div>
        <!-- display table-->
      </div>
    </html:form>
  </div>
</body>
</html>
