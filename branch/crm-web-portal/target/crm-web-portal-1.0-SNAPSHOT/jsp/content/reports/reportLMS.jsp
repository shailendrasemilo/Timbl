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
<h2>Lead Management System Report</h2>
<form action="/qrcReportAction" id="IDAdjustmentReport" method="post">
	<div class="clear marginleft10 inner_left_lead floatl">
		<html:hidden name="reportForm" property="toSearch" value="true"/>
   <p class="floatl clear">
  <strong> From Date<span class="error_message verticalalignTop">*</span></strong>
   <html:text property="fromDate" styleId="fromDate" name="reportForm" styleClass="tcal tcalInput" readonly="readonly"></html:text>
   <font></font>
  </p>
  <p class="floatl marginleft30">
   <strong> To Date<span class="error_message verticalalignTop">*</span></strong>
              <html:text property="toDate" styleId="toDate" name="reportForm" styleClass="tcal tcalInput" readonly="readonly" />
              <font ></font>
              <label id="ToDateError class="errorTextbox hidden">
              </label></p></div>	
	</div>
	<div class="floatr inner_right">
		<p class="buttonPlacement">
			<a href="javascript:void(0);" onclick="javascript:lmsReportSubmit('lmsReport');"  class="main_button marginleft10"><span>Search</span></a> 
		</p>
		<br class="clear"/>

	</div>
	</form>
	<c:choose>
				<c:when test="${not empty reportForm.lmsPojoList }">
				            <table id="reportDataTable" style="width:100%">
				          <thead>
				            <tr>
				            	<td>Lead&nbsp;ID</td>
				                <td>Date</td>
							    <td>Lead&nbsp;Entered&nbsp;By</td>
							    <td>Lead&nbsp;Source</td>
								<td>Reference</td>
								<td>Wired/&nbsp;Not&nbsp;Wired</td>	
								<td>Business</td>
								<td>Customer&nbsp;Name</td>
								<td>Mobile&nbsp;No.</td>
								<td>Calling&nbsp;No.</td>
								<td>Email&nbsp;ID</td>
								<td>State</td>
								<td>City</td>
								<td>Area</td>
								<td>Society</td>
								<td>House&nbsp;No.</td>
								<td>Landmark</td>
								<td>Pin&nbsp;Code</td>
								<td>Current&nbsp;Owner</td>
								<td>Lead&nbsp;Forwarded&nbsp;On&nbsp;(Date)</td>
								<td>Lead&nbsp;Status</td>
								<td>Reason</td>
								<td>CAF&nbsp;No.</td>
								<td>Remarks&nbsp;at&nbsp;the&nbsp;time&nbsp;of&nbsp;generation</td>
								<td>Latest&nbsp;Remarks</td>
								<td>Latest&nbsp;Remarks&nbsp;Time</td>
								<td>Closed&nbsp;By</td>
								<td>Closed&nbsp;Time</td>
								<td>Second&nbsp;Last&nbsp;Remarks</td>	
								<td>Third&nbsp;Last&nbsp;Remarks</td>	
								<td>Second&nbsp;Last&nbsp;Remarks&nbsp;Time</td>	
								<td>Third&nbsp;Last&nbsp;Remarks&nbsp;Time</td>					              
				            </tr>
				          </thead>
				          <tbody>
				         <c:forEach items="${reportForm.lmsPojoList }" var="report">
				            <tr>
				            	<td>L${report.lmsId}</td>
				                <td><bean:write name="crmRoles" property="reportXmlDate(${report.createdTime})" scope="session"/></td>
							    <td>${report.createdBy}</td>
							    <td>${report.leadSource}</td>
								<td>${report.referralSource}</td>
								<td>${report.feasibility}</td>	
								<td><bean:write name="crmRoles" property="displayEnum(Product,${report.business})" scope="session" /></td>
								<td>${report.customerName}</td>
								<td>${report.mobileNumber}</td>
								<td><c:if test="${report.callingNumber ne '0'}">${report.callingNumber}</c:if></td>
								<td>${report.emailId}</td>
								<td>${report.state}</td>
								<td>${report.city}</td>
								<td>${report.area}</td>
								<td>${report.locality}</td>
								<td>${report.houseNumber}</td>
								<td>${report.landmark}</td>
								<td><c:if test="${report.pincode ne '0'}">${report.pincode}</c:if></td>
								<td>${report.currentOwner}</td>
								<td><bean:write name="crmRoles" property="reportXmlDate(${report.leadForwardedOn})" scope="session"/></td>
								<td><bean:write name="crmRoles" property="displayEnum(LeadStatus,${report.status})" scope="session" /></td>
								<td>${report.reason}</td>
								<td>${report.crfNo}</td>
								<td>${report.remarks}</td>
								<td>${report.latestRemarks}</td>
								<td><bean:write name="crmRoles" property="reportXmlDate(${report.latestRemarksTime})" scope="session"/></td>
								<td>${report.lastModifiedBy}</td>
								<td><bean:write name="crmRoles" property="reportXmlDate(${report.lastModifiedTime})" scope="session"/></td>
								<td>${report.secondLastRemarks}</td>
								<td>${report.thirdOneRemarks}</td>
								<td><bean:write name="crmRoles" property="reportXmlDate(${report.secondLastRemarksTime})" scope="session"/></td>
								<td><bean:write name="crmRoles" property="reportXmlDate(${report.thirdOneRemarksTime})" scope="session"/></td>
								
				            </tr>
				           </c:forEach>
				          </tbody>
				        </table>
				        </c:when>
				<c:otherwise>
				<c:if test="${ empty reportForm.lmsPojoList and param.method ne 'lmsReportPage'}">
				<br class="clear" />
				No Record Found
				</c:if>
			   </c:otherwise>
		</c:choose>
	<br class="clear" />
	
	
</div>
<p class="clear"/>
</div>
</div>
</div>