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
	<bean:define id="activeMenu" value="cancellation"/>
		<%@include file="crfReportMenu.jsp"%>
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

		<h4> Cancellation Report</h4>
	<html:form action="/capReportAction" method="post" styleId="inaReport">
	<html:hidden name="reportForm" property="toSearch" value="true"/>
					<div class="marginleft10 inner_left_lead floatl">
					<p class="floatl clear marginleft15 margintop20">
							<strong>From Date</strong>
							<html:text property="fromDate" styleId="fromDate" name="reportForm" styleClass="tcal tcalInput" readonly="readonly"></html:text>
							<font style="margin: 2px"></font>
						</p>
						<p class="floatl marginleft30 margintop20">
							<strong>To Date</strong>
							<html:text property="toDate" styleId="toDate" name="reportForm" styleClass="tcal tcalInput" readonly="readonly" />
							<font style="margin: 2px"></font>
						</p>
						<p class="floatl marginleft30 margintop20">
							<strong>Service Name</strong> <span class="LmsdropdownWithoutJs">
								<html:select property="productType" name="reportForm"
									styleId="rcs_ServiceNameId">
									<html:option value="">Please Select</html:option>
									<logic:notEmpty name="reportForm" property="productTypeList">
										<html:optionsCollection name="reportForm" property="productTypeList" label="contentName" value="contentValue" />
									</logic:notEmpty>
								</html:select>
								<font></font>
							</span>
						</p>
						<p class="floatl marginleft30 margintop20 ">
							<strong>Service Type</strong> <span class="LmsdropdownWithoutJs">
								<html:select property="serviceType" name="reportForm"
									styleId="rcs_ServiceTypeId">
									<html:option value="">Please Select</html:option>
									<logic:notEmpty name="reportForm" property="serviceTypeList">
										<html:optionsCollection name="reportForm" property="serviceTypeList" label="contentName" value="contentValue" />
									</logic:notEmpty>
								</html:select>
								<font></font>
							</span>
						</p>
						
					</div>
					<br />
					<div class="floatr buttonPlacement">
						<a href="javascript:void(0);" onclick="javascript:inaReportSubmit('cancellationReportPage');" id="submit_ReportScanningID"
							class="main_button marginleft10"><span>Submit</span></a>
					</div>
					<p class="floatl clear">&nbsp;</p>
					
<div class="dTInteractionRpt">	
<p class="floatl clear">
<c:choose>
<c:when test="${not empty reportForm.inaReportPojos }">
 <table id="reportDataTable" style="width:100%">
          <thead>
            <tr>
              <td>CAF&nbsp;NO</td>
              <td>CANCELLATION&nbsp;DATE</td>
              <td>CANCELLATION&nbsp;REASON</td>
              <td>LMO&nbsp;NAME</td>
              <td>BP&nbsp;NAME</td>          
              <td>CITY</td> 
              <td>CAF&nbsp;Entry&nbsp;Date</td>
              <td>FT&nbsp;Approval&nbsp;Date</td> 
			  <td>Customer&nbsp;ID</td> 
			  <td>Service</td>
			  <td>Service&nbsp;Type</td>
			  <td>Connection&nbsp;Type</td>
			  <td>Plan&nbsp;Name</td>
			  <td>Cancelled&nbsp;By</td>      
			 </tr>
          </thead>
          <tbody>
          <c:forEach items="${reportForm.inaReportPojos }" var="report">
            <tr>
            <td><span style="display: inline-block;">${report.crfId}</span></td>
              <td><span style="display: inline-block;"><bean:write name="crmRoles" property="reportXmlDate(${report.lastModifiedTime})" scope="session"/></span></td>
              <td><span style="display: inline-block;">${report.reason}</span></td>
              <td><span style="display: inline-block;"><c:if test="${report.LMOName ne '0'}"><bean:write name="crmRoles" property="displayEnum(PartnerPojo,${report.LMOName})" scope="session" /></c:if></span></td>
              <td><span style="display: inline-block;"><bean:write name="crmRoles" property="displayEnum(PartnerPojo,${report.BPName})" scope="session" /></span></td>          
              <td><span style="display: inline-block;">${report.cityName}</span></td>
              <td><span style="display: inline-block;"><bean:write name="crmRoles" property="reportXmlDate(${report.crfDate})" scope="session"/></span></td>
              <td><span style="display: inline-block;"><bean:write name="crmRoles" property="reportXmlDate(${report.ftApprovalDate})" scope="session"/></span></td>
              <td><span style="display: inline-block;">${report.customerId}</span></td>
              <td><span style="display: inline-block;"><bean:write name="crmRoles"	property="displayEnum(Product,${report.product})" scope="session" /></span></td>
              <td><span style="display: inline-block;"><bean:write name="crmRoles" property="displayEnum(ServiceType,${report.serviceType})" scope="session" /></span></td>
              <td><span style="display: inline-block;"><bean:write name="crmRoles"	property="displayEnum(ConnectionType,${report.connectionType})"	scope="session" /></span></td>
              <td><span style="display: inline-block;">${report.basePlanName}</span></td>
              <td><span style="display: inline-block;">${report.rejectedBy}</span></td>
            </tr>
           </c:forEach>
          </tbody>
        </table>
        <br class="clear" />
</c:when>
<c:otherwise>
<c:if test="${ reportForm.toSearch eq 'true'}">
Record Not Found
</c:if>
</c:otherwise>
</c:choose>
</p>
</div>				
	<br class="clear" />
	</html:form>
		
<p class="clear"/>
		

  <div class="relative inner_left_lead">
		
	<br class="clear" />
   </div>
</div>
<p class="clear"/>