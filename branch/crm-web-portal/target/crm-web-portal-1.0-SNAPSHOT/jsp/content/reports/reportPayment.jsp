<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib uri="/WEB-INF/tlds/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/tlds/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/tlds/displaytag.tld" prefix="display"%>
<%@taglib uri="/WEB-INF/tlds/struts-html.tld" prefix="html"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<head>
<script src="javascript/tcal.js" type="text/javascript"></script> 
</head>
<div class="loadingPopup hidden"></div>
<div id="section">
<div class="section">

<div class="inner_section ">
	
<div class=" floatl manageGISRight  qrcRight qrcReports">
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
	<div class="relative">
<h2>Payment Report</h2>
<form action="/paymentTracking" id="IDAdjustmentReport" method="post">
	<div class="clear marginleft10 inner_left_lead floatl">
  <p class="floatl clear">
              <strong> From Date<span class="error_message verticalalignTop">*</span></strong>
              <html:text property="fromDate" styleId="fromDate" name="reportForm" styleClass="tcal tcalInput" readonly="readonly"></html:text>
            </p>
            <p class="floatl marginleft30">
              <strong> To Date<span class="error_message verticalalignTop">*</span></strong>
              <html:text property="toDate" styleId="toDate" name="reportForm" styleClass="tcal tcalInput" readonly="readonly" />
            </p>
            <p class="floatl clear">
              <strong>Customer ID</strong>
              <html:text property="customerId" name="reportForm" styleClass="Lmstextbox" styleId="customerId" maxlength="15" onkeyup="javascript:ValidatenReplaceAlphanumeric(this);" ></html:text>
            </p>
            <p class="floatl marginleft30">
              <strong>CAF Number</strong>
              <html:text property="crfId" name="reportForm" styleClass="Lmstextbox" styleId="crfId" maxlength="8"></html:text>
            </p>
            <p class="floatl clear">
              <strong>Service Type</strong> <span class="LmsdropdownWithoutJs"> <html:select name="reportForm" property="customerServiceType">
                  <html:option value="">All</html:option>
                  <logic:notEmpty name="reportForm" property="customerServiceTypeList">
                    <html:optionsCollection name="reportForm" property="customerServiceTypeList" label="contentName" value="contentValue" />
                  </logic:notEmpty>
                </html:select>
              </span>
            </p>
            <p class="floatl marginleft30">
              <strong>Installation Status</strong> <span class="LmsdropdownWithoutJs"> <html:select property="installationStatus" name="reportForm">
                  <html:option value="">All</html:option>
                  <logic:notEmpty name="reportForm" property="installationStatusList">
                    <html:optionsCollection name="reportForm" property="installationStatusList" label="contentName" value="contentValue" />
                  </logic:notEmpty>
                </html:select>
              </span>
            </p>
            <p class="floatl marginleft30">
              <strong>Payment Status</strong> <span class="LmsdropdownWithoutJs"> <html:select property="paymentStatus" name="reportForm">
                  <html:option value="">All</html:option>
                  <logic:notEmpty name="reportForm" property="paymentStatusList">
                    <html:optionsCollection name="reportForm" property="paymentStatusList" label="contentName" value="contentValue" />
                  </logic:notEmpty>
                </html:select>
              </span>
            </p>
            <p class="floatl marginleft30">
              <strong>Case Status</strong> <span class="LmsdropdownWithoutJs"> <html:select property="caseStatus" name="reportForm">
                  <html:option value="">All</html:option>
                  <logic:notEmpty name="reportForm" property="caseStatusList">
                    <html:optionsCollection name="reportForm" property="caseStatusList" label="contentName" value="contentValue" />
                  </logic:notEmpty>
                </html:select>
              </span>
            </p>
            <p class="floatl  clear">
              <strong>Cheque Status</strong> <span class="LmsdropdownWithoutJs"> <html:select property="chequeStatus" name="reportForm">
                  <html:option value="">All</html:option>
                  <logic:notEmpty name="reportForm" property="chequeStatusList">
                    <html:optionsCollection name="reportForm" property="chequeStatusList" label="contentName" value="contentValue" />
                  </logic:notEmpty>
                </html:select>
              </span>
            </p>
            <p class="floatl marginleft30">
              <strong>Entity Type</strong> <span class="LmsdropdownWithoutJs"> <html:select property="entityType" name="reportForm">
                  <html:option value="">All</html:option>
                  <logic:notEmpty name="reportForm" property="entityTypeList">
                    <html:optionsCollection name="reportForm" property="entityTypeList" label="contentName" value="contentValue" />
                  </logic:notEmpty>
                </html:select>
              </span>
            </p>
            <p class="floatl marginleft30">
              <strong>Payment Mode</strong> <span class="LmsdropdownWithoutJs"> <html:select property="paymentMode" name="reportForm" onchange="getPaymentChannel(this.value,'select');" styleId="">
                  <html:option value="">All</html:option>
                  <logic:notEmpty name="reportForm" property="paymentModeList">
                    <html:optionsCollection name="reportForm" property="paymentModeList" label="contentName" value="contentValue" />
                  </logic:notEmpty>
                </html:select>
              </span>
            </p>
            <p class="floatl marginleft30">
              <strong>Channels</strong> <span class="LmsdropdownWithoutJs"> <html:select property="channelType" name="reportForm" styleId="paymentChannel">
                  <html:option value="">All</html:option>
                  <logic:notEmpty name="reportForm" property="paymentChannelList">
                    <html:optionsCollection name="reportForm" property="paymentChannelList" label="contentName" value="contentValue" />
                  </logic:notEmpty>
                </html:select>
              </span>
            </p>
            <p class="floatl  clear">
              <strong>Posting Status</strong> <span class="LmsdropdownWithoutJs"> <html:select property="postingStatus" name="reportForm">
                  <html:option value="">All</html:option>
                   <html:option value="A">Pending</html:option>
                    <html:option value="PP">Posted</html:option>
                     <html:option value="PR">Reversed</html:option>
                </html:select>
              </span>
            </p>
</div>	
	</div>
	<div class="floatr inner_right">
		<p class="buttonPlacement">
			 <a href="javascript:void(0);" onclick="javascript:paymentReport('paymentReport');"  class="main_button"><span>Submit</span></a> 
		</p>
		<br class="clear"/>

	</div>
	</form>
	<p class="floatl clear">
	<c:choose>
<c:when test="${not empty reportForm.paymentReportList }">
            <table id="reportDataTable" style="width:100%">
          <thead>
            <tr>
            <td>CUSTOMER&nbsp;ID</td>
			<td>CUSTOMER&nbsp;NAME</td>
			<td>CAF&nbsp;NO</td>
			<td>BILLING&nbsp;CYCLE</td>
			<td>SERVICE&nbsp;NAME</td>
			<td>PAYMENT&nbsp;AMOUNT</td>
			<td>PAYMENT&nbsp;DATE</td>
			<td>PAYMENT&nbsp;MODE</td>
			<td>PAYMENT&nbsp;TRACK</td>
			<td>CHANNEL</td>
			<td>CHEQUE&nbsp;NO</td>
			<td>CHEQUE&nbsp;DATE</td>
			<td>BANK</td>
			<td>BRANCH</td>
			<td>CITY</td>
			<td>REF&nbsp;TRANS&nbsp;ID</td>
			<td>PG&nbsp;TRAN&nbsp;ID</td>
			<td>BANK&nbsp;TRANS&nbsp;ID</td>
			<td>PAYMENT&nbsp;HEAD</td>
			<td>POSTING&nbsp;STATUS</td>
			<td>REVERSAL&nbsp;DATE</td>
			<td>CHEQUE&nbsp;BOUNCING&nbsp;DATE</td>
            </tr>
          </thead>
          <tbody>
         <c:forEach items="${reportForm.paymentReportList }" var="report">
            <tr>
            <td>${report.customerId}</td>
			<td>${report.customerName}</td>
			<td>${report.crfId}</td>
			<td>${report.billingCycle}</td>
			<td><bean:write name="crmRoles" property="displayEnum(Product,${report.productName})" scope="session" /></td>
			<td>${report.amount}</td>
			<td><logic:notEmpty name="report" property="paymentDate"><bean:write name="crmRoles" property="toDate(${report.paymentDate})" scope="session"/></logic:notEmpty></td>
			<td><bean:write name="crmRoles" property="displayEnum(PaymentMode,${report.paymentMode})" scope="session" /></td>
			<td><bean:write name="crmRoles" property="displayEnum(InstallationStatus,${report.installationStatus})" scope="session" /></td>
			<td><bean:write name="crmRoles" property="displayEnum(PaymentChannel,${report.paymentChannel})" scope="session" /></td>
			<td>${report.chequeDdNo}</td>
			<td><logic:notEmpty name="report" property="chequeDate"><bean:write name="crmRoles" property="toDate(${report.chequeDate})" scope="session"/></logic:notEmpty></td>
			<td><bean:write name="crmRoles" property="displayEnum(BANK,${report.bankName})" scope="session" /></td>
			<td>${report.bankBranch}</td>
			<td>${report.bankCity}</td>
			<td>${report.refTransId}</td>
			<td>${report.pgwTransId}</td>
			<td>${report.bankTransId}</td>
			<td><bean:write name="crmRoles" property="displayEnum(EntityType,${report.entityType})" scope="session" /></td> 
			<td><c:choose><c:when test="${report.postingStatus eq 'A'}">Pending</c:when><c:when test="${report.postingStatus eq 'PP'}">Posted</c:when><c:when test="${report.postingStatus eq 'PR'}">Reversed</c:when><c:otherwise>${report.postingStatus}</c:otherwise></c:choose></td>
			<td><bean:write name="crmRoles" property="reportXmlDate(${report.reversalDate})" scope="session"/></td>
			<td><logic:notEmpty name="report" property="chequeBouncingDate"><bean:write name="crmRoles" property="toDate(${report.chequeBouncingDate})" scope="session"/></logic:notEmpty></td>          
            </tr>
           </c:forEach>
          </tbody>
        </table>
        </c:when>
<c:otherwise>
<c:if test="${ empty reportForm.paymentReportList and param.method ne 'paymentReportPage'}">
<br class="clear" />
&nbsp;&nbsp;&nbsp;&nbsp;No Record Found
</c:if>
</c:otherwise>
</c:choose>
</p>
	<br class="clear" />
	
	
</div>
<p class="clear"/>
</div>
</div>
</div>