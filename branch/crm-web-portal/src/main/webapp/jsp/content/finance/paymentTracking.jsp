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
  $( document ).ready(function(){
    $(".iframe4").colorbox({iframe:true, width:"950px", height:"500px"});
  });
  $( document ).ready(function(){
    $(".iframe6").colorbox({iframe:true, width:"650px", height:"350px"});
  });
</script>
</head>
<body>
<div class="loadingPopup hidden"></div>
  <div id="section">
    <form action="/paymentTracking" id="IDpaymentTracking" method="post">
      <html:hidden name="financeForm" property="paymentId" styleId="PTpaymentId" />
      <html:hidden name="financeForm" property="searchPaymentListSize" styleId="PTsearchPaymentListSize" />
      <html:hidden name="financeForm" property="hiddenPaymentIdList" styleId="PThiddenPaymentIdList" />
      <html:hidden name="financeForm" property="changeDynamicVariable" styleId="PTchangeDynamicVariable" />
      <html:hidden name="financeForm" property="realizationVariable" styleId="PTreliazationVariable" />
      <html:hidden name="financeForm" property="bouncingDate" styleId="PTbouncingDate" />
      <html:hidden name="financeForm" property="bouncingReason" styleId="PTbouncingReason" />
      <html:hidden name="financeForm" property="modifiedBy" value="${sessionScope.userPojo.userId}" />
      <div class="section">
        <h2>Search Payments Details</h2>
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
            <p class="floatl clear">
              <strong> From Date<span class="error_message verticalalignTop">*</span></strong>
              <html:text property="fromDate" styleId="fromDateTracking" name="financeForm" styleClass="tcal tcalInput" readonly="readonly"></html:text>
            </p>
            <p class="floatl marginleft30">
              <strong> To Date<span class="error_message verticalalignTop">*</span></strong>
              <html:text property="toDate" styleId="toDateTracking" name="financeForm" styleClass="tcal tcalInput" readonly="readonly" />
            </p>
            <p class="floatl clear">
              <strong>Customer ID</strong>
              <html:text property="customerId" name="financeForm" styleClass="Lmstextbox"></html:text>
            </p>
            <p class="floatl marginleft30">
              <strong>CAF Number</strong>
              <html:text property="crfId" name="financeForm" styleClass="Lmstextbox"></html:text>
            </p>
            <p class="floatl clear">
              <strong>Service Type</strong> <span class="LmsdropdownWithoutJs"> <html:select name="financeForm" property="customerServiceType">
                  <html:option value="">All</html:option>
                  <logic:notEmpty name="financeForm" property="customerServiceTypeList">
                    <html:optionsCollection name="financeForm" property="customerServiceTypeList" label="contentName" value="contentValue" />
                  </logic:notEmpty>
                </html:select>
              </span>
            </p>
            <p class="floatl marginleft30">
              <strong>Installation Status</strong> <span class="LmsdropdownWithoutJs"> <html:select property="installationStatus" name="financeForm">
                  <html:option value="">All</html:option>
                  <logic:notEmpty name="financeForm" property="installationStatusList">
                    <html:optionsCollection name="financeForm" property="installationStatusList" label="contentName" value="contentValue" />
                  </logic:notEmpty>
                </html:select>
              </span>
            </p>
            <p class="floatl marginleft30">
              <strong>Payment Status</strong> <span class="LmsdropdownWithoutJs"> <html:select property="paymentStatus" name="financeForm">
                  <html:option value="">All</html:option>
                  <logic:notEmpty name="financeForm" property="paymentStatusList">
                    <html:optionsCollection name="financeForm" property="paymentStatusList" label="contentName" value="contentValue" />
                  </logic:notEmpty>
                </html:select>
              </span>
            </p>
            <p class="floatl marginleft30">
              <strong>Case Status</strong> <span class="LmsdropdownWithoutJs"> <html:select property="caseStatus" name="financeForm">
                  <html:option value="">All</html:option>
                  <logic:notEmpty name="financeForm" property="caseStatusList">
                    <html:optionsCollection name="financeForm" property="caseStatusList" label="contentName" value="contentValue" />
                  </logic:notEmpty>
                </html:select>
              </span>
            </p>
            <p class="floatl  clear">
              <strong>Cheque Status</strong> <span class="LmsdropdownWithoutJs"> <html:select property="cheque_status" name="financeForm">
                  <html:option value="">All</html:option>
                  <logic:notEmpty name="financeForm" property="chequeStatusList">
                    <html:optionsCollection name="financeForm" property="chequeStatusList" label="contentName" value="contentValue" />
                  </logic:notEmpty>
                </html:select>
              </span>
            </p>
            <p class="floatl marginleft30">
              <strong>Entity Type</strong> <span class="LmsdropdownWithoutJs"> <html:select property="entity_type" name="financeForm">
                  <html:option value="">All</html:option>
                  <logic:notEmpty name="financeForm" property="entityTypeList">
                    <html:optionsCollection name="financeForm" property="entityTypeList" label="contentName" value="contentValue" />
                  </logic:notEmpty>
                </html:select>
              </span>
            </p>
            <p class="floatl marginleft30">
              <strong>Payment Mode</strong> <span class="LmsdropdownWithoutJs"> <html:select property="payment_mode" name="financeForm" onchange="getPaymentChannel(this.value,'select');" styleId="">
                  <html:option value="">All</html:option>
                  <logic:notEmpty name="financeForm" property="paymentModeList">
                    <html:optionsCollection name="financeForm" property="paymentModeList" label="contentName" value="contentValue" />
                  </logic:notEmpty>
                </html:select>
              </span>
            </p>
            <p class="floatl marginleft30">
              <strong>Channels</strong> <span class="LmsdropdownWithoutJs"> <html:select property="channel_type" name="financeForm" styleId="paymentChannel">
                  <html:option value="">All</html:option>
                  <logic:notEmpty name="financeForm" property="paymentChannelList">
                    <html:optionsCollection name="financeForm" property="paymentChannelList" label="contentName" value="contentValue" />
                  </logic:notEmpty>
                </html:select>
              </span>
            </p>
          </div>
          <div class="floatr inner_right">
            <a href="#" id="PRsubmit_PaymentTracking" class="main_button"><span>Search</span></a>
          </div>
          <p class="clear"></p>
        </div>
        <logic:notEmpty name="financeForm" property="searchPaymentList">
          <display:table id="data" name="sessionScope.financeForm.searchPaymentList" requestURI="" class="dataTable" style="width:100%" pagesize="15">
            <display:setProperty name="paging.banner.placement" value="bottom" />
            <display:setProperty name="mymedia" value="true"></display:setProperty>
            <display:setProperty name="export.excel.filename" value="Payment-Detail.xls" />
            <display:setProperty name="export.csv.filename" value="Payment-Detail.csv" />
            <display:column title="Customer ID / CAF No." sortable="false">
              <logic:notEmpty name="data" property="customerId">
                <a href="paymentTracking.do?method=viewPayment&paymentId=${data.paymentId}" class="viewMorePayment_InvDetails iframe4"><bean:write
                    name="data" property="customerId" /></a>
              </logic:notEmpty>
              <logic:empty name="data" property="customerId">
                <logic:notEmpty name="data" property="crfId">
                  <a href="paymentTracking.do?method=viewPayment&paymentId=${data.paymentId}" class="viewMorePayment_InvDetails iframe4"> <bean:write
                      name="data" property="crfId" /></a>
                </logic:notEmpty>
              </logic:empty>
            </display:column>
            <display:column title="Payment Channel">
              <logic:empty name="data" property="paymentChannel">-</logic:empty>
              <logic:notEmpty name="data" property="paymentChannel">
                <bean:write name="crmRoles" property="displayEnum(PaymentChannel,${ data.paymentChannel})" scope="session" />
              </logic:notEmpty>
            </display:column>
            <display:column title="Payment Mode" sortable="false">
              <logic:empty name="data" property="paymentMode">-</logic:empty>
              <logic:notEmpty name="data" property="paymentMode">
                <bean:write name="crmRoles" property="displayEnum(PaymentMode,${ data.paymentMode})" scope="session" />
              </logic:notEmpty>
            </display:column>
            <display:column title="Posting Date">
              <bean:write name="crmRoles" property="toDate(${data.postingDate})" />
            </display:column>
            <display:column title="Cheque No. /<br> Receipt No. / Transaction ID">
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
            <display:column title="Cheque Date" sortable="false">
              <logic:equal name="data" property="paymentMode" value="Q">
                <bean:write name="crmRoles" property="toDate(${data.chequeDate})" />
              </logic:equal>
              <logic:notEqual name="data" property="paymentMode" value="Q">-</logic:notEqual>
            </display:column>
            <display:column title="Bank Name" style="min-width: 80px;">
              <logic:empty name="data" property="bankName">-</logic:empty>
              <logic:notEmpty name="data" property="bankName">
                <bean:write name="crmRoles" property="displayEnum(BANK,${ data.bankName})" scope="session" />
              </logic:notEmpty>
            </display:column>
            <display:column property="amount" title="Amount" sortable="false"></display:column>
            <display:column title="Payment Status" sortable="false">
              <bean:write name="crmRoles" property="displayEnum(PaymentStatus,${ data.paymentStatus})" scope="session" />
              <logic:notEmpty name="data" property="customerId">
                <logic:equal name="crmRoles" property="available(update_fpp,recover_fpp)" value="true" scope="session">
                  <logic:equal name="data" property="paymentStatus" value="NR">
                    <br />
                    <a href="javascript:changePaymentStatus('R','paymentStatus','<bean:write name="data" property="paymentId"/>','')">Received</a>
                  </logic:equal>
                  <logic:equal name="crmRoles" property="available(recover_fpp)" value="true" scope="session">
                    <logic:equal name="data" property="paymentStatus" value="R">
                      <br />
                      <a href="javascript:changePaymentStatus('NR','paymentStatus','<bean:write name="data" property="paymentId"/>','')">Not Received</a>
                    </logic:equal>
                  </logic:equal>
                </logic:equal>
              </logic:notEmpty>
            </display:column>
            <display:column title="Realization Status" style="width:10%;" sortable="false">
            <logic:equal name="data"  property="realzationStatus" value="NRL">
            <a href="#" title="Bouncing Date : <bean:write name="crmRoles" property="toDate(${data.bouncingDate})"/> , Bouncing Reason : <bean:write name="data" property="bouncingReason" />" style="text-decoration:none"> 
              <bean:write name="crmRoles" property="displayEnum(RealizationStatus,${data.realzationStatus})" scope="session" />
            </a>
            </logic:equal>
            <logic:notEqual  name="data"  property="realzationStatus" value="NRL">
             <bean:write name="crmRoles" property="displayEnum(RealizationStatus,${data.realzationStatus})" scope="session" />
              <logic:notEmpty name="data" property="customerId">
                <logic:equal name="data" property="realzationStatus" value="CLRAWT">
                  <br />
                  <logic:equal name="crmRoles" property="available(update_fpp)" value="true" scope="session">
                    <logic:equal name="data" property="paymentStatus" value="R">
                      <a href="javascript:changePaymentStatus('RL','realization','<bean:write name="data" property="paymentId"/>','')">Realized</a>
                      <br />
                    </logic:equal>
                    <a href="paymentPosting.do?method=changeRealizationStatusPage&paymentId=${data.paymentId}&installationStatus=${data.installationStatus}"
                      class="viewMorePayment_InvDetails iframe6">Not Realized</a>
                  </logic:equal>
                </logic:equal>
              </logic:notEmpty>
			</logic:notEqual>              
            </display:column>
            <display:column title="Posting Status" style="width:10%;" sortable="false" >
            <logic:notEmpty name="data" property="status">
              <logic:equal name="data" property="status" value="PP">Posted</logic:equal>
              <logic:equal name="data" property="status" value="PR">
                <a href="#" title="Reversal Date : <bean:write name="crmRoles" property="toDate(${data.reversalDate})"/> , Reversal Reason : <bean:write name="data" property="reversalReason" />" style="text-decoration:none"> 
                     Reversed
                </a>
              </logic:equal>
              <logic:equal name="data" property="status" value="A">Pending</logic:equal>
           </logic:notEmpty>
           </display:column>
            <display:column title="User Name" property="createdBy" />
            <display:column title="Case Status" sortable="false" style="width:1px;">
              <logic:equal name="crmRoles" property="available(update_fpp,recover_fpp)" value="true" scope="session">
                <logic:notEqual name="crmRoles" property="available(recover_fpp)" value="true" scope="session">
                  <logic:equal property="caseStatus" name="data" value="CLS">
                    <bean:write name="crmRoles" property="displayEnum(CaseStatus,${data.caseStatus})" scope="session" />
                  </logic:equal>
                  <logic:notEqual property="caseStatus" name="data" value="CLS">
                    <span class="LmsdropdownWithoutJs"> <html:select property="caseStatus" name="data"
                        onchange="changePaymentStatus(this.value,'caseStatus',${data.paymentId},'')" styleId="caseStatusID_${data.paymentId}">
                        <html:option value="">Please Select</html:option>
                        <html:optionsCollection name="financeForm" property="caseStatusList" label="contentName" value="contentValue" />
                      </html:select> <input type="hidden" id="hdnCS_${data.paymentId}" value="${data.caseStatus}">
                    </span>
                  </logic:notEqual>
                </logic:notEqual>
                <logic:equal name="crmRoles" property="available(recover_fpp)" value="true" scope="session">
                  <span class="LmsdropdownWithoutJs"> <html:select property="caseStatus" name="data"
                      onchange="changePaymentStatus(this.value,'caseStatus',${data.paymentId},'')" styleId="caseStatusID_${data.paymentId}">
                      <html:option value="">Please Select</html:option>
                      <html:optionsCollection name="financeForm" property="caseStatusList" label="contentName" value="contentValue" />
                    </html:select> <input type="hidden" id="hdnCS_${data.paymentId}" value="${data.caseStatus}">
                  </span>
                </logic:equal>
              </logic:equal>
              <logic:notEqual name="crmRoles" property="available(update_fpp,recover_fpp)" value="true" scope="session">
                <bean:write name="crmRoles" property="displayEnum(CaseStatus,${data.caseStatus})" scope="session" />
              </logic:notEqual>
            </display:column>
            <logic:equal name="crmRoles" property="available(update_fpp,delete_fpp,recover_fpp)" value="true" scope="session">
              <display:column title="Select">
                <logic:notEmpty name="data" property="customerId">
                  <logic:equal name="data" property="paymentStatus" value="NR">
                    <logic:equal name="data" property="realzationStatus" value="CLRAWT">
                      <html:checkbox name="data" property="editable" value="true" styleId="PTeditable_${data_rowNum}" onchange="PTcheckData(${data_rowNum})"></html:checkbox>
                    </logic:equal>
                  </logic:equal>
                  <logic:equal name="data" property="paymentStatus" value="NR">
                    <logic:notEqual name="data" property="realzationStatus" value="CLRAWT">
                      <html:checkbox name="data" property="editable" value="true" styleId="PTeditable_${data_rowNum}" onchange="PTcheckData(${data_rowNum})"></html:checkbox>
                    </logic:notEqual>
                  </logic:equal>
                  <logic:notEqual name="data" property="paymentStatus" value="NR">
                    <logic:equal name="data" property="realzationStatus" value="CLRAWT">
                      <html:checkbox name="data" property="editable" value="true" styleId="PTeditable_${data_rowNum}" onchange="PTcheckData(${data_rowNum})"></html:checkbox>
                    </logic:equal>
                  </logic:notEqual>
                  <html:hidden name="data" property="paymentId" styleId="PTpaymentId_${data_rowNum}" value="${data.paymentId}" />
                  <html:hidden name="data" property="paymentStatus" styleId="PTpaymentStatus_${data_rowNum}" value="${data.paymentStatus}" />
                </logic:notEmpty>
                <logic:empty name="data" property="customerId">
                  CAF Not Submitted 
                </logic:empty>
              </display:column>
            </logic:equal>
          </display:table>
          <p class="clear margintop10" align="center">
            <logic:equal name="crmRoles" property="available(update_fpp)" value="true" scope="session">
              <span hidden="true" id="PTrecieveAllButton"> <html:link href="#" styleClass="yellow_button"
                  onclick="javascript:realizeRecieveCloseAll('R');">Received All</html:link></span>
              <span hidden="true" id="PTrealizeButton"> <html:link href="#" styleClass="yellow_button" onclick="javascript:realizeRecieveCloseAll('RL');">Realize All</html:link></span>
            </logic:equal>
          </p>
        </logic:notEmpty>
        <logic:empty name="financeForm" property="searchPaymentList">
          <b><html:errors property="noRecordFound" /></b>
        </logic:empty>
      </div>
    </form>
  </div>
</body>
</html>
