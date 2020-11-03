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
				<bean:define id="activeMenu" value="crfScanning" />
				<%@include file="crfReportMenu.jsp"%>
			</div>
			<div class=" floatl manageGISRight qrcRight">
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
				<h4>CAF Scanning Report</h4>
				<html:form action="/capReportAction" method="post" styleId="inaReport">
				<html:hidden name="reportForm" property="toSearch" value="true"/>
					<div class="marginleft10 inner_left_lead floatl">
						<p class="floatl clear marginleft15 margintop20">
							<strong>From Date</strong>
							<html:text name="reportForm" styleClass="tcal" readonly="true"
								property="fromDate" styleId="fromDate" style="color:black !important;"></html:text>
							<font style="margin: 2px"></font>
						</p>
						<p class="floatl marginleft30 margintop20">
							<strong>To Date</strong>
							<html:text name="reportForm" styleClass="tcal" property="toDate"
								readonly="true" styleId="toDate"  style="color:black !important;"></html:text>
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
								</html:select>
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
								</html:select>
							</span>
						</p>

					</div>
					<br />
					<div class="floatr buttonPlacement">
						<a href="javascript:void(0);" onclick="javascript:inaReportSubmit('crfScanningReportPage');" id="submit_ReportScanningID"
							class="main_button marginleft10"><span>Submit</span></a>
					</div>
					
					<p class="floatl clear">&nbsp;</p>
					
<div style="width:100%;">	
<p class="floatl clear">
<logic:notEmpty property="inaReportPojos" name="reportForm">
        <table id="reportDataTable" style="width:100%">
          <thead>
            <tr>
             <td>CAF&nbsp;NO</td>
              <td>SALE&nbsp;DATE</td>
              <td>CAF&nbsp;ENTRY&nbsp;DATE</td>
              <td>CUSTOMER&nbsp;FIRST&nbsp;NAME</td>
              <td>CUSTOMER&nbsp;LAST&nbsp;NAME</td>
              <td>BP&nbsp;NAME</td>
              <td>SR&nbsp;NAME</td>
              <td>LMO&nbsp;NAME</td>
              <td>BP&nbsp;CODE</td>
              <td>SR&nbsp;CODE</td>
              <td>FT&nbsp;APPROVE&nbsp;STATUS</td>
            </tr>
          </thead>
          <tbody>
         <c:forEach items="${reportForm.inaReportPojos}" var="report">
           <tr>
              <td>${report.crfId}</td>
              <td><logic:notEmpty name="report" property="crfDate"><bean:write name="crmRoles" property="toDate(${report.crfDate})"/></logic:notEmpty></td>
              <td><bean:write name="crmRoles" property="reportXmlDate(${report.createdTime})" scope="session"/></td>
              <td>${report.custFname}</td>
              <td>${report.custLname}</td>
              <td>${report.BPName}</td>
              <td>${report.SRName}</td>
              <td>${report.LMOName}</td>
              <td>${report.BPCode}</td>
              <td>${report.SRCode}</td>
              <td>${report.status}</td>
            </tr>
           </c:forEach>
          </tbody>
        </table>
        </logic:notEmpty>
        <logic:empty name="reportForm" property="inaReportPojos">
        <logic:equal value="true" name="reportForm" property="toSearch">
        No Record Found.
        </logic:equal>
        </logic:empty>
		 </p>
      </div>	
			<br class="clear" /><br class="clear" /><br class="clear" />
				</html:form>
			</div>
			<p class="clear" />