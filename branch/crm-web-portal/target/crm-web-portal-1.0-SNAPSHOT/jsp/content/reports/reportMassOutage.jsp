<%@page import="com.np.tele.crm.utils.DateUtils"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@page import="java.util.Date;"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
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
			<div class="inner_left_lead floatl  qrcLeft">
				<bean:define id="activeMenu" value="massOutage" />
			</div>
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
				<html:form action="/qrcReportAction" styleId="IDMassOutageReport"
					method="post">
					<div class="relative">
						<h2>Mass Outage Report</h2>
						<div class="clear  inner_left_lead floatl">
							<html:hidden name="reportForm" property="toSearch" value="true" />
							<p class="floatl clear  ">
								<strong>From Date<span
									class="error_message verticalalignTop">*</span></strong>
								<html:text name="reportForm" styleClass="tcal" readonly="true"
									style="color:black !important;" property="fromDate"
									styleId="fromDate"></html:text>
								<font style="margin: 2px"></font>
							</p>
							<p class="floatl marginleft30 ">
								<strong>To Date<span
									class="error_message verticalalignTop">*</span></strong>
								<html:text name="reportForm" styleClass="tcal" property="toDate"
									style="color:black !important;" readonly="true"
									styleId="toDate"></html:text>
								<font style="margin: 2px"></font>
							</p>
							<p class="floatl margintop20 clear ">
								<strong>Select Outage Type</strong> <span
									class="LmsdropdownWithoutJs"> <html:select
										property="outageType" name="reportForm" styleId="outageTypeId">
										<html:option value="">Please Select</html:option>
										<html:option value="Planned">Planned</html:option>
										<html:option value="Unplanned">Unplanned</html:option>
									</html:select>
								</span>
							</p>
							<p class="floatl margintop20 marginleft30">
								<strong>Mass Outage Status</strong> <span
									class="LmsdropdownWithoutJs"> <html:select
										property="outageStatus" name="reportForm"
										styleId="massStatusId">
										<html:option value="">Please Select</html:option>
										<html:option value="A">Active</html:option>
										<html:option value="R">Resolved</html:option>
									</html:select>
								</span>
							</p>
							<p class="floatl marginleft30 margintop20">
								<strong> Service Name</strong> <span
									class="LmsdropdownWithoutJs"> <html:select
										property="productType" name="reportForm">
										<html:option value="">Please Select</html:option>
										<logic:notEmpty name="reportForm" property="productTypeList">
											<html:optionsCollection name="reportForm"
												property="productTypeList" label="contentName"
												value="contentValue" />
										</logic:notEmpty>
									</html:select>
								</span>
							</p>
						</div>
					</div>
					<div class="floatr inner_right">
						<p class="buttonPlacement">
							<a href="#" id="IDMassOutageSubmit" class="main_button"><span>Search</span></a>
						</p>
						<br class="clear" />
					</div>
				</html:form>
				<logic:notEmpty name="reportForm" property="crmMassOutagePojos">
						<table id="reportDataTable" style="width: 90%">
							<thead>
								<tr>
									<td>Outage&nbsp;Start&nbsp;Time&nbsp;with&nbsp;date</td>
									<td>Outage&nbsp;end&nbsp;time&nbsp;with&nbsp;date</td>
									<td>Outage&nbsp;ETR</td>
									<td>Outage&nbsp;Initiated&nbsp;time&nbsp;with&nbsp;date</td>
									<td>Outage&nbsp;Initiated&nbsp;User</td>
									<td>Total&nbsp;Downtime</td>
									<td>Outage&nbsp;Type</td>
									<td>Outage&nbsp;Level</td>
								    <td>Partner&nbsp;Name</td>
								    <td>Master&nbsp;Name</td>
								    <td>Effected&nbsp;Customers</td>
									<td>Reason</td>
									<td>Remarks</td>
									<td>Closed&nbsp;By</td>
								</tr>
							</thead>
							<tbody>
								<logic:iterate id="report" name="reportForm"
									property="crmMassOutagePojos">
									<tr>
										<td><bean:write name="crmRoles"
												property="reportXmlDate(${report.outageStartTime})" scope="session" /></td>
										<td><bean:write name="crmRoles"
												property="reportXmlDate(${report.resolutionTime})" scope="session" /></td>
										<td><logic:notEmpty name="report" property="outageEtrTime"><bean:write name="crmRoles"
												property="toDate(${report.outageEtrTime})" scope="session" /></logic:notEmpty></td>
										<td><bean:write name="crmRoles"
												property="reportXmlDate(${report.createdTime})" scope="session" /></td>
										<td>${report.createdBy}</td>
										<td>${report.downTime}</td>
										<td>${report.outageType}</td>
										<td>${report.outageLevel}</td>
										<td>
											${report.partnerNames}
										</td>
										<td>
										${report.masterNames}
										</td>
										<td>
										${report.customerCount}
										</td>
										<td>${report.reason}</td>
										<td>${report.remarks}</td>
										<td>
										<c:if test="${report.status ne 'R'}">-</c:if>
				                        <c:if test="${report.status eq 'R'}">
				                        ${report.lastModifiedBy}
				                        </c:if>
										</td>
									</tr>
								</logic:iterate>
							</tbody>
						</table>
				</logic:notEmpty>
				<br class="clear" />
				<logic:empty name="reportForm" property="crmMassOutagePojos">
					<logic:equal value="true" name="reportForm" property="toSearch">
        				No Record Found.
       			 	</logic:equal>
				</logic:empty>
				<br class="clear" />
			</div>
			<p class="clear" />
		</div>
	</div>
</div>