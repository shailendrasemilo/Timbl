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
	<bean:define id="activeMenu" value="ISRPendancy"/>
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

		<h4> ISR Pendency Report</h4>
	
		<html:form action="/capReportAction" method="post" styleId="isrPendencyReport">
		<html:hidden name="reportForm" property="toSearch" value="true"/>
					<div class="marginleft10 inner_left_lead floatl">
						<p class="floatl margintop20">
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
						
					</div>
					 <br />
					<div class="floatr buttonPlacement">
						<a href="javascript:void(0);" onclick="javascript:inaReportSubmit('isrPendancyReportPage');" 
							class="main_button marginleft10"><span>Submit</span></a>
					</div>
					<p class="floatl clear">&nbsp;</p>
					
<div style="width:100%;">	
<p class="floatl clear">
<c:choose>
<c:when test="${not empty reportForm.inaReportPojos }">
        <table id="reportDataTable" style="width:100%">
          <thead>
            <tr>
              <td>CAF&nbsp;No</td>
              <td>Customer&nbsp;ID</td>
              <td>CAF&nbsp;Entry&nbsp;Date</td>
              <td>FT&nbsp;Approval&nbsp;Date</td>
              <td>Mac&nbsp;Bind&nbsp;Date</td>
              <td>Date&nbsp;On&nbsp;ISR</td>
              <td>ISR&nbsp;Entry&nbsp;Date</td>
              <td>CPE&nbsp;Mac&nbsp;ID</td>
              <td>Area&nbsp;Name</td>
              <td>LMO</td>
              <td>Service</td>
              <td>CPE&nbsp;Status</td>
              <td>CAF&nbsp;Activated&nbsp;By</td>
              <td>ISR&nbsp;Punched&nbsp;By</td>
            </tr>
          </thead>
          <tbody>
          <c:forEach items="${reportForm.inaReportPojos }" var="report">
            <tr>
              <td>${report.crfId}</td>
              <td>${report.customerId}</td>
              <td><bean:write name="crmRoles" property="reportXmlDate(${report.createdTime})" scope="session"/></td>
              <td><bean:write name="crmRoles" property="reportXmlDate(${report.ftApprovalDate})" scope="session"/></td>
              <td><bean:write name="crmRoles" property="reportXmlDate(${report.macBindDate})" scope="session"/></td>
              <td><logic:notEmpty name="report" property="isrDate"><bean:write name="crmRoles" property="toDate(${report.isrDate})" scope="session"/></logic:notEmpty></td>
              <td><bean:write name="crmRoles" property="reportXmlDate(${report.isrEntryDate})" scope="session"/></td>
              <td>${report.cpeMacId}</td>
              <td>${report.areaName}</td>
              <td><bean:write name="crmRoles" property="displayEnum(PartnerPojo,${report.LMOName})" scope="session" /></td>
              <td><bean:write name="crmRoles"	property="displayEnum(Product,${report.product})" scope="session" /></td>
              <td><bean:write name="crmRoles" property="displayEnum(CPEStatus,${report.cpeStatus })" scope="session" /></td>
              <td>${report.crfActivatedBy}</td>
              <td>${report.isrPunchedBy}</td>
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
				<p class="clear" />
  <div class="relative inner_left_lead">
		
	<br class="clear" />
   </div>
</div>

 
      <p class="clear"/>