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
				<bean:define id="activeMenu" value="FTRejection"/>
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

				<h4> FT Rejection Report</h4>

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
							onclick="javascript:inaReportSubmit('ftRejectionReportPage');"
							class="main_button marginleft10"><span>Submit</span></a>
					</div>
					<p class="floatl clear">&nbsp;</p>
				</html:form>
		<c:choose>
              <c:when test="${not empty reportForm.inaReportPojos }">
            <table id="reportDataTable" style="width:100%">
          <thead>
            <tr>
              <td>CAF&nbsp;NO</td>
              <td>Category</td>
              <td>Sale&nbsp;Date</td>
              <td>CAF&nbsp;Entry&nbsp;Date&nbsp;and&nbsp;Time</td>
              <td>FT&nbsp;Rejection&nbsp;Date&nbsp;and&nbsp;time</td>
              <td>Customer&nbsp;First&nbsp;Name</td>
              <td>Customer&nbsp;Last&nbsp;Name</td>
              <td>BP&nbsp;Name</td>
              <td>BP&nbsp;Code</td>
              <td>SR&nbsp;Code</td>
              <td>Approve&nbsp;Status</td>
              <td>Case&nbsp;Submitted&nbsp;By</td>
              <td>Area&nbsp;Manager</td>
              <td>Comment</td>
              <td>LMO&nbsp;Name</td>
              <td>Service</td>
              <td>Service&nbsp;Type</td>
              <td>Reason</td>
            </tr>
          </thead>
          <tbody>
         <c:forEach items="${reportForm.inaReportPojos }" var="report">
            <tr>
              <td>${report.crfId}</td>
              <td><bean:write name="crmRoles" property="displayEnum(ConnectionType,${report.connectionType})"/></td>
              <td><logic:notEmpty name="report" property="crfDate"><bean:write name="crmRoles" property="toDate(${report.crfDate})" scope="session"/></logic:notEmpty></td>             
              <td><bean:write name="crmRoles" property="reportXmlDate(${report.createdTime})" scope="session"/></td>
              <td><bean:write name="crmRoles" property="reportXmlDate(${report.ftRejectionDate})" scope="session"/></td>
              <td>${report.custFname}</td>
              <td>${report.custLname}</td>
              <td>${report.BPName}</td>
              <td>${report.BPCode}</td>
              <td>${report.SRCode}</td>
              <td>REJECTED</td>
              <td>${report.crfActivatedBy}</td>
              <td>${report.areaManager}</td>
              <td>${report.remarks}</td>
              <td><c:if test="${report.LMOName ne '0'}"><bean:write name="crmRoles" property="displayEnum(PartnerPojo,${report.LMOName})" scope="session" /></c:if></td>
              <td><bean:write name="crmRoles"	property="displayEnum(Product,${report.product})" scope="session" /></td>
              <td><bean:write name="crmRoles" property="displayEnum(ServiceType,${report.serviceType})" scope="session" /></td>
              <td>${report.reason}</td>
            </tr>
           </c:forEach>
          </tbody>
        </table>
        </c:when>
<c:otherwise>
<br class="clear" />
<c:if test="${ reportForm.toSearch eq 'true'}">
Record Not Found
</c:if>
</c:otherwise>
</c:choose>
				<br class="clear" />
				<div class="relative inner_left_lead">
					<br class="clear" />
				</div>
			</div>
			<p class="clear" />