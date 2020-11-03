<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<%@taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<%@taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<script src="dwr/engine.js" type="text/javascript"></script>
<script src="dwr/util.js" type="text/javascript"></script>
<script src="dwr/interface/crmDwr.js" type="text/javascript"></script>
<div class="loadingPopup hidden"></div>
<div id="section">
  <html:form action="/paymentPosting" styleId="searchSuspense">
    <html:hidden name="financeForm" property="crmCmsPaymentPojo.lastModifiedBy" value="${sessionScope.userPojo.userId}" />
    <html:hidden name="financeForm" property="crmCmsPaymentPojo.cmsId" styleId="hidden_cmsId" value="0" />
    <html:hidden name="financeForm" property="crmCmsPaymentPojo.ie2" styleId="hidden_ie2" />
    <html:hidden name="financeForm" property="paymentDate" styleId="hidden_paymentDate" />
    <html:hidden name="financeForm" property="crmCmsPaymentPojo.instrumentAmount" styleId="hidden_instrumentAmount" value="0.0" />
    <html:hidden name="financeForm" property="crmCmsPaymentPojo.instrumentNo" styleId="hidden_instrumentNo" />
    <html:hidden name="financeForm" property="chequeDate" styleId="hidden_chequeDate" />
    <html:hidden name="financeForm" property="crmCmsPaymentPojo.draweeBank" styleId="hidden_draweeBank" />
    <div class="section">
      <h2>Suspense/Rejected Records Details</h2>
      <span class="error_message"> <html:errors property="appError" />
      </span> <span class="success_message"> <logic:messagesPresent message="true" property="appMessage">
          <html:messages id="msg" message="true" property="appMessage">
            <bean:write name="msg" />
          </html:messages>
        </logic:messagesPresent>
      </span>
      <div class="inner_section">
        <div class="inner_left_lead floatl">
          <p class="floatl clear">
            <strong> From Date<span class="error_message verticalalignTop">*</span></strong>
            <html:text property="fromDate" name="financeForm" styleClass="tcal tcalInput" readonly="true" styleId="fromDate_s" onchange="getFileDetails();"></html:text>
          </p>
          <p class="floatl marginleft30">
            <strong> To Date<span class="error_message verticalalignTop">*</span></strong>
            <html:text property="toDate" name="financeForm" styleClass="tcal tcalInput" readonly="true" styleId="toDate_s" onchange="getFileDetails();"></html:text>
          </p>
          <p class="floatl clear">
            <strong>Deposit File Name</strong> <span class="dropdownWithoutJs"> <html:select name="financeForm"
                property="crmCmsPaymentPojo.depositFileId" styleId="cmsDepositFileId">
                <html:option value="">Please select</html:option>
                <html:optionsCollection name="financeForm" property="crmCmsFilePojos" label="cmsFileName" value="cmsFileId" />
              </html:select>
            </span>
          </p>
          <p class="floatl marginleft30">
            <strong>Clearance File Name</strong> <span class="dropdownWithoutJs"> <html:select name="financeForm"
                property="crmCmsPaymentPojo.clearanceFileId" styleId="cmsClearanceFileId">
                <html:option value="">Please select</html:option>
                <logic:notEmpty name="financeForm" property="clearancefileList">
                  <html:optionsCollection name="financeForm" property="clearancefileList" label="cmsFileName" value="cmsFileId" />
                </logic:notEmpty>
              </html:select>
            </span>
          </p>
          <p class="floatl  clear">
            <strong>Record Type</strong> <span class="LmsdropdownWithoutJs"> <html:select property="crmCmsPaymentPojo.status">
                <html:option value="">All</html:option>
                <logic:notEmpty name="financeForm" property="fileStatusList">
                  <html:optionsCollection name="financeForm" property="fileStatusList" label="contentName" value="contentValue" />
                </logic:notEmpty>
              </html:select>
            </span>
          </p>
        </div>
        <div class="floatr inner_right">
          <a href="#" id="submit_searchSuspense" class="main_button"><span>Search</span></a>
        </div>
        <p class="clear"></p>
      </div>
      <div>
        <logic:notEmpty name="financeForm" property="crmCmsPaymentPojos">
          <display:table id="data" name="sessionScope.financeForm.crmCmsPaymentPojos" requestURI="" class="dataTable" style="width:100%" pagesize="15">
            <display:setProperty name="paging.banner.placement" value="bottom" />
            <display:setProperty name="mymedia" value="true"></display:setProperty>
            <display:setProperty name="export.excel.filename" value="SuspenseRejected-Detail.xls" />
            <display:setProperty name="export.csv.filename" value="SuspenseRejected-Detail.csv" />
            <display:column title="Sr.No.">
              <bean:write name="data_rowNum" />
            </display:column>
            <display:column title="Customer ID">
              <logic:equal name="data" property="status" value="SP">
                <span ondblclick="customeridShow('${data.cmsId}');" class="linkCustomerId" id="${data.cmsId}"> <bean:write name="data" property="ie2" />
                  <span class="toolTip">Double click to rectify</span>
                </span>
                <p class="hide1" id="customeridShow${data.cmsId}">
                  <html:text styleClass="Cmstextbox" name="financeForm" property="newCustomerId" value="${data.ie2}"
                    onblur="javascript:suspenseRejectedRecords(this.value,'${data.cmsId}','${data.ie2}');" styleId="editedCustId" />
                  <a href="javascript:customerIdHide('${data.cmsId}');" class="closeBtn">x</a>
                </p>
              </logic:equal>
              <logic:notEqual name="data" property="status" value="SP">
                <bean:write name="data" property="ie2" />
              </logic:notEqual>
            </display:column>
            <html:text styleClass="Lmstextbox" property="crmCmsPaymentPojo.ie2"
              onclick="javascript:suspenseRejectedRecords(this.value,'${sessionScope.userPojo.userId}');" />
            <display:column title="Internal Dept No.">${ empty data.internalDepNo ? '-' : data.internalDepNo}</display:column>
            <display:column title="Cheque Date">
              <bean:write name="crmRoles" property="toDate(${ data.instrumentDate})" scope="session" />
            </display:column>
            <display:column title="Cheque Number">${ empty data.instrumentNo ? '-' : data.instrumentNo}</display:column>
            <display:column title="Cheque Amount">${ empty data.instrumentAmount ? '-' : data.instrumentAmount}</display:column>
            <display:column title="Drawee Bank">
              <bean:write name="crmRoles" property="displayEnum(BANK,${data.draweeBank})" scope="session" />
            </display:column>
            <display:column title="Deposit Slip No.">${ empty data.depositSlipNo ? '-' : data.depositSlipNo}</display:column>
            <display:column title="Deposit Date">
              <bean:write name="crmRoles" property="toDate(${ data.depositDate})" scope="session" />
            </display:column>
            <display:column title="Deposit Remarks">${ empty data.depositRemarks ? '-' : data.depositRemarks}</display:column>
            <display:column title="Value Date">
              <bean:write name="crmRoles" property="toDate(${ data.valueDate})" scope="session" />
            </display:column>
            <display:column title="Posting Date">
              <bean:write name="crmRoles" property="toDate(${ data.postingDate})" scope="session" />
            </display:column>
            <display:column title="Clearance Status">
				${ empty data.debitCreditFlag ? '-' : (data.debitCreditFlag eq 'C' ? 'Credited' : 'Debited') }	
			</display:column>
            <display:column title="Status">
              <bean:write name="crmRoles" property="displayEnum(AllStatus,${data.status})" scope="session" />
            </display:column>
            <logic:equal name="crmRoles" property="available(update_finance)" value="true" scope="session">
              <display:column title="Rectify">
                <logic:equal name="data" property="status" value="RJ">
                  <a href="javascript:editRejectedRecords('<bean:write name="data" property="ie2" />','<bean:write name="data" property="cmsId" />');">
                    Rectify&nbsp;Record </a>
                </logic:equal>
                <logic:notEqual name="data" property="status" value="RJ">
						-
					</logic:notEqual>
              </display:column>
            </logic:equal>
          </display:table>
        </logic:notEmpty>
         <logic:empty name="financeForm" property="crmCmsPaymentPojos">
         	<b><html:errors property="noRecordFound" /></b>
         </logic:empty>
      </div>
      <html:hidden name="financeForm" property="crmCmsPaymentPojo.ie2" styleId="customerId" />
      <html:hidden name="financeForm" property="crmCmsPaymentPojo.cmsId" styleId="cmsId" />
    </div>
  </html:form>
</div>