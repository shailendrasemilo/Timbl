<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib uri="/WEB-INF/tlds/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/tlds/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/tlds/displaytag.tld" prefix="display"%>
<%@taglib uri="/WEB-INF/tlds/struts-html.tld" prefix="html"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div class="loadingPopup hidden"></div>
<div id="section">
	<div class="section">

		<div class="inner_section ">
			<div class="inner_left_lead floatl  qrcLeft">
				<bean:define id="activeMenu" value="WorkorderGeneration" />
				<%@include file="inaReportMenu.jsp"%>
			</div>
		</div>
		<div class="floatl manageGISRight  qrcRight qrcReports">

			<div class="success_message">
				<logic:messagesPresent message="true">
					<html:messages id="message" message="true">
						<bean:write name="message" />
					</html:messages>
				</logic:messagesPresent>
			</div>
			<div class="error_message" id="errors">
				<html:errors />
			</div>
			<h4>Workorder Generation Report</h4>
			<html:form action="/capReportAction" method="post" styleId="inaReport">
				<html:hidden name="reportForm" property="toSearch" value="true" />
				<div class="marginleft10 inner_left_lead floatl">
					<p class="floatl clear marginleft15 margintop20">
						<strong>From Date<span class="error_message verticalalignTop">*</span></strong>
						<html:text name="reportForm" styleClass="tcal" readonly="readonly" property="fromDate" styleId="fromDate"></html:text>
						<font style="margin: 2px"></font>
					</p>
					<p class="floatl marginleft30 margintop20">
						<strong>To Date<span class="error_message verticalalignTop">*</span></strong>
						<html:text name="reportForm" styleClass="tcal" property="toDate" readonly="readonly" styleId="toDate"></html:text>
						<font style="margin: 2px"></font>
					</p>										
				</div>
				<br />
				<div class="floatr buttonPlacement">
					<a href="javascript:void(0);" onclick="javascript:inaReportSubmit('workorderGenerationReportPage');" id="submit_ReportScanningID"
						class="main_button marginleft10"><span>Submit</span></a>
				</div>
				<p class="floatl clear">&nbsp;</p>
			</html:form>
			<logic:notEmpty name="reportForm" property="inaReportPojos">
				<div class="data-table">
					<table id="reportDataTable" style="width: 100%">
						<thead>
							<tr>
								<td>CAF&nbsp;NO</td>
								<td>Customer&nbsp;ID</td>
								<td>Date&nbsp;on&nbsp;CAF</td>
								<td>&nbsp;Entry&nbsp;Date</td>
								<td>FT&nbsp;Approval&nbsp;Date</td>
								<td>LMO</td>
								<td>Connection&nbsp;Type</td>
								<td>Customer&nbsp;Name</td> 
								<td>RMN</td>
								<td>Customer&nbsp;Email&nbsp;ID</td>
								<td>Installation&nbsp;Address</td>
								<td>Area</td>
								<td>Plan&nbsp;Name</td>
								<td>Add&nbsp;On&nbsp;DUL&nbsp;Name</td>
								<td>SR&nbsp;Name</td>
								<td>BP&nbsp;Name</td>
								<td>Service</td>
								<td>Service&nbsp;Type</td>
								<td>FT&nbsp;Approved&nbsp;By</td>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${reportForm.inaReportPojos }" var="report">
								<tr>
									<td>${report.crfId}</td>
									<td>${report.customerId}</td>
									<td><logic:notEmpty name="report" property="crfDate">
											<bean:write name="crmRoles" property="toDate(${report.crfDate})" />
										</logic:notEmpty></td>
									<td><bean:write name="crmRoles" property="reportXmlDate(${report.createdTime})" scope="session" /></td>
									<td><bean:write name="crmRoles" property="reportXmlDate(${report.ftApprovalDate})" scope="session" /></td>
									<td><bean:write name="crmRoles" property="displayEnum(PartnerPojo,${report.LMOName})" scope="session" /></td>
									<td><bean:write name="crmRoles" property="displayEnum(ConnectionType,${report.connectionType})" scope="session" /></td>
									<td>${report.custFname}&nbsp;${report.custLname}</td> 
									<td>${report.rmn}</td>
									<td>${report.custEmailId}</td>
									<td>${report.installationAddress}</td> 
									<td>${report.areaName}</td>
									<td>${report.basePlanName}</td>
									<td>${report.addonPlanName}</td>
									<td>${report.SRName}</td>
									<td><bean:write name="crmRoles" property="displayEnum(PartnerPojo,${report.BPName})" scope="session" /></td>
									<td><bean:write name="crmRoles" property="displayEnum(Product,${report.product})" scope="session" /></td>
									<td><bean:write name="crmRoles" property="displayEnum(ServiceType,${report.serviceType})" scope="session" /></td>
									<td>${report.crfApprovedBy}</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
			</logic:notEmpty>
			<br class="clear" />
			<logic:empty name="reportForm" property="inaReportPojos">
				<logic:equal value="true" name="reportForm" property="toSearch">
       					 No Record Found.
        			</logic:equal>
			</logic:empty>
			<br class="clear" />
		</div>
		<p class="clear" />
	</div>
</div>