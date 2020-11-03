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
				<bean:define id="activeMenu" value="KPI" />				
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


				<h2>KPI Report</h2>
				<html:form action="/capReportAction" method="post"
					styleId="inaReport">
					<html:hidden name="reportForm" property="toSearch" value="true" />
					<div class=" inner_left_lead floatl">
						<p class="floatl clear  margintop20">
							<strong>From Date</strong>
							<html:text name="reportForm" styleClass="tcal" readonly="true"
								property="fromDate" styleId="fromDate"
								style="color:black !important;"></html:text>
							<font style="margin: 2px"></font>
						</p>
						<p class="floatl marginleft30 margintop20">
							<strong>To Date</strong>
							<html:text name="reportForm" styleClass="tcal" property="toDate"
								readonly="true" styleId="toDate" style="color:black !important;"></html:text>
							<font style="margin: 2px"></font>
						</p>
						<p class="floatl marginleft30 margintop20">
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
							onclick="javascript:inaReportSubmit('kpiReportPage');"
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
									<td>Service&nbsp;Name</td>
									<td>Service&nbsp;Type</td>
									<td>Sale&nbsp;Date</td>
									<td>CAF&nbsp;Entry&nbsp;Date</td>
									<td>Rejected&nbsp;By&nbsp;Ft Yes/No</td>
									<td>FT&nbsp;IN&nbsp;DATE</td>
									<td>FT&nbsp;OUT&nbsp;DATE</td>
									<td>ISR&nbsp;DATE</td>
									<td>REGULAR/CANCELLED</td>
									<td>CAF&nbsp;ENTRY&nbsp;SLA (In Days)</td>
									<td>CAF&nbsp;TO&nbsp;ISR&nbsp;SLA (In Days)</td>
									<td>WO&nbsp;TO&nbsp;ISR&nbsp;SLA (In Days)</td>
									<td>FT&nbsp;IN&nbsp;OUT&nbsp;SLA (In Days)</td>
								</tr>
							</thead>
							<tbody>
								<c:forEach items="${reportForm.inaReportPojos }" var="report">
									<tr>
										<td>${report.crfId}</td>
										<td><bean:write name="crmRoles"
												property="displayEnum(Product,${report.product})"
												scope="session" /></td>
										<td><bean:write name="crmRoles"
												property="displayEnum(ServiceType,${report.serviceType})"
												scope="session" /></td>
										<td><logic:notEmpty name="report" property="crfDate"><bean:write name="crmRoles"
												property="toDate(${report.crfDate})" scope="session" /></logic:notEmpty></td>
										<td><bean:write name="crmRoles"
												property="reportXmlDate(${report.createdTime})" scope="session" /></td>
										<td>${report.status}</td>
										<td><bean:write name="crmRoles"
												property="reportXmlDate(${report.ftSubmittionDate})"
												scope="session" /></td>
										<td><bean:write name="crmRoles"
												property="reportXmlDate(${report.ftApprovalDate})" scope="session" /></td>
										<td><logic:notEmpty name="report" property="isrDate"><bean:write name="crmRoles"
												property="toDate(${report.isrDate})" scope="session" /></logic:notEmpty></td>
										<td>${report.cancelled}</td>
										<td>${report.CRFEntrySLADay}</td>
										<td>${report.CRFToISRSLA}</td>
										<td>${report.WOToISRSLA}</td>
										<td>${report.ftinoutsla}</td>
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
				<div class="relative inner_left_lead">
					<br class="clear" />
				</div>
			</div>
			<p class="clear" />