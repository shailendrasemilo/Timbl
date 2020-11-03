<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
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
				<bean:define id="activeMenu" value="ERP" />
				<%@include file="inaReportMenu.jsp"%>
			</div>
			<div class=" floatl manageGISRight  qrcRight">
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

				<h4>ERP Report</h4>

				<html:form action="/capReportAction" method="post"
					styleId="srpReport">
					<html:hidden name="reportForm" property="toSearch" value="true" />
					<div class="marginleft10 inner_left_lead floatl">
						<p class="floatl margintop20">
							<strong>Service Name</strong> <span class="LmsdropdownWithoutJs">
								<html:select property="productType" name="reportForm"
									styleId="rcs_ServiceNameId">
									<html:option value="">Please Select</html:option>
									<logic:notEmpty name="reportForm" property="productTypeList">
										<html:optionsCollection name="reportForm"
											property="productTypeList" label="contentName"
											value="contentValue" />
									</logic:notEmpty>
								</html:select> <font></font>
							</span>
						</p>
						<p class="floatl marginleft30 margintop20 ">
							<strong>Service Type</strong> <span class="LmsdropdownWithoutJs">
								<html:select property="serviceType" name="reportForm"
									styleId="rcs_ServiceTypeId">
									<html:option value="">Please Select</html:option>
									<logic:notEmpty name="reportForm" property="serviceTypeList">
										<html:optionsCollection name="reportForm"
											property="serviceTypeList" label="contentName"
											value="contentValue" />
									</logic:notEmpty>
								</html:select> <font></font>
							</span>
						</p>

					</div>
					<br />
					<div class="floatr buttonPlacement">
						<a href="javascript:void(0);"
							onclick="javascript:inaReportSubmit('erpReportPage');"
							class="main_button marginleft10"><span>Submit</span></a>
					</div>
					<p class="floatl clear">&nbsp;</p>
				</html:form>
				<logic:notEmpty name="reportForm" property="inaReportPojos">
					<div class="data-table">
						<table id="reportDataTable" style="width: 100%">
							<thead>
								<tr>
									<td>CAF&nbsp;No</td>
									<td>Date&nbsp;Of&nbsp;CAF</td>
									<td>CAF&nbsp;Entry&nbsp;Date</td>
									<td>CAF&nbsp;Approval&nbsp;Date</td>
									<td>ERP&nbsp;Date&nbsp;Time</td>
									<td>Category</td>
									<td>Customer&nbsp;Name</td>
									<td>Contact&nbsp;No</td>
									<td>Registered&nbsp;Mobile&nbsp;No</td>
									<%--<td>Registered&nbsp;Telephone&nbsp;No</td> --%>
									<td>Email&nbsp;ID</td>
									<td>Address</td>
									<td>City&nbsp;Name</td>
									<td>Base&nbsp;Plan&nbsp;Name</td>
									<td>Add&nbsp;On&nbsp;DUL&nbsp;Name</td>
									<td>CPE&nbsp;Status</td>
									<td>BP&nbsp;Name</td>
									<td>LMO</td>
									<td>Customer&nbsp;ID</td>
									<td>Status</td>
									<td>Activated</td>
									<td>Cancelled</td>
									<td>Current&nbsp;Stage</td>
									<td>Current&nbsp;Owner</td>
									<td>Previous&nbsp;Stage</td>
									<td>Previous&nbsp;Owner</td>
									<td>Reason</td>
									<td>CAF&nbsp;Approved&nbsp;By</td>
									<td>Service&nbsp;Name</td>
									<td>Service&nbsp;Type</td>
									<td>Remarks</td>
								</tr>
							</thead>
							<tbody>
								<c:forEach items="${reportForm.inaReportPojos }" var="report">
									<tr>
										<td>${report.crfId}</td>
										<td><logic:notEmpty name="report" property="crfDate"><bean:write name="crmRoles"
												property="toDate(${report.crfDate})" scope="session" /></logic:notEmpty></td>
										<td><bean:write name="crmRoles"
												property="reportXmlDate(${report.createdTime})" scope="session" /></td>
										<td><bean:write name="crmRoles"
												property="reportXmlDate(${report.ftApprovalDate})" scope="session" /></td>
										<td><bean:write name="crmRoles"
												property="reportXmlDate(${report.ftRejectionDate})" scope="session" /></td>
										<td><bean:write name="crmRoles"
												property="displayEnum(ConnectionType,${report.connectionType})"
												scope="session" /></td>
										<td>${report.custFname}&nbsp;${report.custLname}</td>
										<td>${report.custMobileNo}</td>
										<td>${report.rmn}</td>
										<%--<td>${report.rtn}</td> --%>
										<td>${report.custEmailId}</td>
										<td>${report.installationAddress}</td>
										<td>${report.cityName}</td>
										<td>${report.basePlanName}</td>
										<td>${report.addonPlanName}</td>
										<td><bean:write name="crmRoles"
												property="displayEnum(CPEStatus,${report.cpeStatus})"
												scope="session" /></td>
										<td><bean:write name="crmRoles"
												property="displayEnum(PartnerPojo,${report.BPName})"
												scope="session" /></td>
										<td><bean:write name="crmRoles"
												property="displayEnum(PartnerPojo,${report.LMOName})"
												scope="session" /></td>
										<td>${report.customerId}</td>
										<td>APPROVED</td>
										<td>${report.activated}</td>
										<td>${report.cancelled}</td>
										<td><bean:write name="crmRoles"
												property="displayEnum(CRMOperationStages,${report.crfStage})"
												scope="session" /></td>
										<td>${report.currentOwner}</td>
										<td><bean:write name="crmRoles"
												property="displayEnum(CRMOperationStages,${report.crfPreviousStage})"
												scope="session" /></td>
										<td>${report.previousOwner}</td>
										<td>${report.reason}</td>
										<td>${report.crfApprovedBy}</td>
										<td><bean:write name="crmRoles"
												property="displayEnum(Product,${report.product})"
												scope="session" /></td>
										<td><bean:write name="crmRoles"
												property="displayEnum(ServiceType,${report.serviceType})"
												scope="session" /></td>
										<td>${report.remarks}</td>
									</tr>
								</c:forEach>

							</tbody>
						</table>
						<br class="clear" />
					</div>
				</logic:notEmpty>
				<br class="clear" />
				<logic:empty name="reportForm" property="inaReportPojos">
					<logic:equal value="true" name="reportForm" property="toSearch">
       					 No Record Found.
        			</logic:equal>
				</logic:empty>
				<br class="clear" />
				<div class="relative inner_left_lead">
					<br class="clear" />
				</div>
			</div>
			<p class="clear" />