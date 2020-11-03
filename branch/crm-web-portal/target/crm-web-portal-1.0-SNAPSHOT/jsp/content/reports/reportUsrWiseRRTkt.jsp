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
				<bean:define id="activeMenu" value="userWiseReopenResolved" />
				<%@include file="qrcReportMenu.jsp"%>
			</div>
			<div class=" floatl manageGISRight  qrcRight" style="width: 1000px;">
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
				<h4>User Wise Reopen Resolved Ticket Report</h4>

				<div class="relative inner_left_lead">
					<form action="/qrcReportAction" id="IDUsrWiseRRTkt" method="post">
						<html:hidden name="reportForm" property="toSearch" value="true" />
						<div class="floatl">
							<p class="floatl clear">
								<strong> From Date<span
									class="error_message verticalalignTop">*</span></strong>
								<html:text property="fromDate" styleId="fromDate"
									name="reportForm" styleClass="tcal tcalInput"
									readonly="readonly"></html:text>
								<font></font>
							</p>
							<p class="floatl marginleft30">
								<strong> To Date<span
									class="error_message verticalalignTop">*</span></strong>
								<html:text property="toDate" styleId="toDate" name="reportForm"
									styleClass="tcal tcalInput" readonly="readonly" />
								<font></font> <label id="ToDateError class=" errorTextboxhidden">
								</label>
							</p>
						</div>
						<div class="floatr inner_right">
							<p class="buttonPlacement">
								<a href="#" class="main_button" id="IDUsrWiseRRTktSubmit"
									style="margin-bottom: -106px">Search</a>
							</p>
						</div>
						<br class="clear" />
					</form>
				</div>
				<logic:notEmpty name="reportForm" property="qrcReportPojos">
					<div class="dTInteractionRpt ">
						<table id="reportDataTable">
							<thead>
								<tr>
									<td>Last&nbsp;Resolved&nbsp;User&nbsp;Name</td>
									<td>Count</td>
								</tr>
							</thead>
							<tbody>
								<logic:iterate id="ticketList" name="reportForm"
									property="qrcReportPojos">
									<tr>
										<td>${ticketList.category}</td>
										<td>${ticketList.subCategory}</td>
										<td>${ticketList.subSubCategory}</td>
										<td><bean:write name="crmRoles"
												property="displayEnum(qrcType,${ticketList.qrcType })"
												scope="session" /></td>
										<td>
										<td>${ticketList.reopenCount}</td>
									</tr>
								</logic:iterate>
							</tbody>
						</table>
					</div>
				</logic:notEmpty>
				<br class="clear" />
				<logic:empty name="reportForm" property="qrcReportPojos">
					<logic:equal value="true" name="reportForm" property="toSearch">
       					 No Record Found.
        			</logic:equal>
				</logic:empty>
				<br class="clear" />
			</div>
			<p class="clear" />