<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="/WEB-INF/tlds/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/tlds/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/tlds/displaytag.tld" prefix="display"%>
<%@taglib uri="/WEB-INF/tlds/struts-html.tld" prefix="html"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<div class="loadingPopup hidden"></div>
<div id="section">
	<div class="section">
		<div class="inner_section ">
			<div class="inner_left_lead floatl  qrcLeft">
				<bean:define id="activeMenu" value="activation" />				
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
				<h2>Activation Report</h2>
				<html:form action="/capReportAction" method="post"
					styleId="inaReport">
					<html:hidden name="reportForm" property="toSearch" value="true" />
					<div class=" inner_left_lead floatl">
						<p class="floatl clear  margintop20">
							<strong>From Date<span
								class="error_message verticalalignTop">*</span></strong>
							<html:text name="reportForm" styleClass="tcal" readonly="true"
								property="fromDate" styleId="fromDate"
								style="color:black !important;"></html:text>
							<font style="margin: 2px"></font>
						</p>
						<p class="floatl marginleft30 margintop20">
							<strong>To Date<span
								class="error_message verticalalignTop">*</span></strong>
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
							onclick="javascript:inaReportSubmit('activationReportPage');"
							class="main_button marginleft10"><span>Submit</span></a>
					</div>
					<p class="floatl clear">&nbsp;</p>
				</html:form>
				<logic:notEmpty name="reportForm" property="inaReportPojos">
					<div class="data-table">
						<table id="reportDataTable" style="width: 100%">
							<thead>
								<tr>
									<td>CUSTOMER&nbsp;ID</td>
									<td>CAF&nbsp;NO</td>
									<%---<td>COMPANY&nbsp;NAME</td> --%>
									<%--<td>CUSTOMER&nbsp;NAME</td> --%>
									<td>LMO&nbsp;NAME</td>
									<td>DEVICE&nbsp;MAC&nbsp;ID</td>
									<td>DATE&nbsp;ON&nbsp;CAF</td>
									<td>CAF&nbsp;ENTRY&nbsp;DATE</td>
									<td>MAC&nbsp;BIND&nbsp;DATE</td>
									<td>CUST&nbsp;ACTIVATION&nbsp;DATE</td>
									<td>CUST&nbsp;STATUS</td>
									<td>STATUS&nbsp;DATE</td>
									<td>BILLING&nbsp;CYCLE</td>
									<td>CONNECTION&nbsp;TYPE</td>
									<td>CUST&nbsp;CATEGORY</td>
									<td>RMN</td>
									<%--<td>RTN</td> --%>
									<td>SERVICE</td>
									<td>SERVICE&nbsp;TYPE</td>
									<td>BILL&nbsp;PREFERENCE</td>
									<td>BILL&nbsp;DETAIL&nbsp;PREFERENCE</td>
									<td>BILL&nbsp;DATE</td>
									<%--<td>BILLING&nbsp;ADDRESS</td> --%>
									<%--<td>INSTALLATION&nbsp;ADDRESS</td> --%>
									<td>AREA</td>
									<td>BILLING&nbsp;CITY</td>
									<td>BASE&nbsp;PLAN&nbsp;NAME</td>
									<td>BASE&nbsp;PLAN&nbsp;ACT&nbsp;DATE</td>
									<td>BASE&nbsp;PLAN&nbsp;CODE</td>
									<td>BASE&nbsp;PLAN&nbsp;RENT</td>
									<td>PRIMARY&nbsp;SPEED</td>
									<td>BASE&nbsp;PLAN&nbsp;DUL</td>
									<td>SECONDARY&nbsp;SPEED</td>
									<td>ADD&nbsp;ON&nbsp;PLAN&nbsp;NAME</td>
									<td>ADD&nbsp;ON&nbsp;PLAN&nbsp;ACT&nbsp;DATE</td>
									<td>ADD&nbsp;ON&nbsp;PLAN&nbsp;CODE</td>
									<td>ADD&nbsp;ON&nbsp;PLAN&nbsp;RENT</td>
									<td>ADD&nbsp;ON&nbsp;PLAN&nbsp;DUL</td>
									<td>BP&nbsp;Name</td>
									<td>SR&nbsp;Name</td>
									<td>NAS&nbsp;port&nbsp;ID</td> 
								</tr>
							</thead>
							<tbody>
								<c:forEach items="${reportForm.inaReportPojos }" var="report">
									<tr>
										<td>${report.customerId}</td>
										<td>${report.crfId}</td>
										<%--<td>${report.companyName}</td> --%>
										<%--<td>${report.custFname}&nbsp;${report.custLname}</td> --%>
										<td><c:if test="${report.LMOName ne '0'}"><bean:write name="crmRoles" property="displayEnum(PartnerPojo,${report.LMOName})" scope="session" /></c:if></td>
										<td>${report.cpeMacId}</td>
										<td><bean:write name="crmRoles" property="reportXmlDate(${report.crfDate})" scope="session" /></td>
										<td><bean:write name="crmRoles"
												property="reportXmlDate(${report.createdTime})" scope="session" /></td>
										<td><bean:write name="crmRoles"
												property="reportXmlDate(${report.macBindDate})" scope="session" /></td>
										<td><bean:write name="crmRoles"
												property="reportXmlDate(${report.activationDate})" scope="session" /></td>
										<td><bean:write name="crmRoles"
												property="displayEnum(AllStatus,${report.status})"
												scope="session" /></td>
										<td><bean:write name="crmRoles"
												property="reportXmlDate(${report.statusDate})"
												scope="session" /></td>
                                         <td><logic:notEmpty name="report" property="billCycle">BILLING GROUP&nbsp;${report.billCycle}</logic:notEmpty></td>
                                        <td>${report.category}</td>
										<td><bean:write name="crmRoles"
												property="displayEnum(ConnectionType,${report.connectionType})"
												scope="session" /></td>
										<td>${report.rmn}</td> 
										<%--<td>${report.rtn}</td> --%>
										<td><bean:write name="crmRoles"	property="displayEnum(Product,${report.product})" scope="session" /></td>
										<td><bean:write name="crmRoles"
												property="displayEnum(ServiceType,${report.serviceType})"
												scope="session" /></td>
										<td><bean:write name="crmRoles"
												property="displayEnum(BillingPreferences,${report.billMode})"
												scope="session" /></td>
										<td>${report.billPref}</td>
										<td>${report.billCycle}</td>
										<%--<td>${report.billingAddress}</td> --%>
										<%--<td>${report.installationAddress}</td> --%>
										<td>${report.areaName}</td>
										<td>${report.cityName}</td>
										<td>${report.basePlanName}</td>
										<td><bean:write name="crmRoles" property="reportXmlDate(${report.activationDate})" scope="session" /></td>
										<td>${report.planCode}</td>
										<td>${report.baseRent}</td>
										<td>${report.primarySpeed}</td>
										<td><fmt:formatNumber value="${ report.primaryQuota div 1024 div 1024 div 1024 }" minFractionDigits="0"
                              				maxFractionDigits="2" />&nbsp;GB</td>
										<td>${report.secondarySpeed}</td>
										<td>${report.addonPlanName}</td>
										<td><bean:write name="crmRoles" property="reportXmlDate(${report.addOnActivationDate})" scope="session" /></td>
										<td>${report.addOnPlanCode}</td>
										<td>${report.addOnRent}</td>
										<td><fmt:formatNumber value="${ report.addOnQuota div 1024 div 1024 div 1024 }" minFractionDigits="0"
                              				maxFractionDigits="2" />&nbsp;GB</td>
										<td><bean:write name="crmRoles"
												property="displayEnum(PartnerPojo,${report.BPName})"
												scope="session" /></td>
										<td>${report.SRName}</td>
										<td>${report.nasportId}</td>
									
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