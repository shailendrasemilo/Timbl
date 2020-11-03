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
	<bean:define id="activeMenu" value="crfDetail"/>
	<%@include file="crfReportMenu.jsp"%>
	</div>
<div class=" floatl manageGISRight  qrcRight">

		<h4> CAF Detail Report</h4>
	
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
						<a href="javascript:void(0);" onclick="javascript:inaReportSubmit('crfDetailReportPage');" id="submit_ReportScanningID"
							class="main_button marginleft10"><span>Submit</span></a>
					</div>
					<p class="floatl clear">&nbsp;</p>
					
					
<div style="width:100%;">	
<p class="floatl clear" >
<logic:notEmpty  name="reportForm" property="inaReportPojos">
        <table id="reportDataTable" style="width:100%"class="reportDataTable_wrapper">
          <thead>
            <tr>
              <td>CAF&nbsp;NO</td>
              <td>DATE&nbsp;OF&nbsp;CAF</td>
              <td>CAF&nbsp;APPROVAL&nbsp;DATE</td>
              <td>CATEGORY</td>
                 <%--<td>CUSTOMER&nbsp;NAME</td>--%>
              <td>CONTACT&nbsp;NO</td>
             <%--  <td>REGISTERED&nbsp;MOBILE&nbsp;NUMBER</td>
              <td>REGISTERED&nbsp;TELEPHONE&nbsp;NUMBER</td>--%>
              <td>EMAIL&nbsp;ID</td>
              <td>&nbsp;&nbsp;ADDRESS&nbsp;&nbsp;</td>
              <td>CITY&nbsp;NAME</td>
              <td>BASE&nbsp;PLAN&nbsp;NAME</td>
              <td>ADD&nbsp;ON&nbsp;PLAN&nbsp;NAME</td>
              <td>CPE&nbsp;STATUS</td>
              <td>SR&nbsp;NAME</td>
              <td>BP&nbsp;NAME</td>
              <td>BP&nbsp;CODE</td>
              <td>SR&nbsp;CODE</td>
              <td>NETWORK&nbsp;PARTNER</td>
              <td>CUSTOMER&nbsp;ID</td>
              <td>STATUS</td>
              <td>ACTIVATED</td>
              <td>CANCELLED</td>
              <td>Current&nbsp;Bin</td>
              <td>Mode&nbsp;of&nbsp;Payment</td>
            </tr>
          </thead>
          <tbody>
          
          <c:forEach items="${reportForm.inaReportPojos}" var="report">
            <tr>
              <td>${report.crfId}</td>
              <td><logic:notEmpty name="report" property="crfDate">
			  <bean:write name="crmRoles" property="toDate(${report.crfDate})"/>
			  </logic:notEmpty>
			  </td>
              <td>
			    <bean:write name="crmRoles" property="reportXmlDate(${report.ftApprovalDate})" scope="session"/>
			  </td>
              <td><bean:write name="crmRoles" property="displayEnum(ConnectionType,${ report.connectionType })"/>
              </td>
                <%-- <td>${report.custFname} &nbsp;${report.custLname}</td>--%>
              <td>${report.custMobileNo}</td>
              <%--   <td>${report.rmn}</td>
              <td>${report.rtn}</td>--%>
              <td>${report.custEmailId}</td>
              <td>${report.installationAddress}</td> 
              <td>${report.cityName}</td>
              <td>${report.basePlanName}</td>
              <td>${report.addonPlanName}</td>
              <td>
               <bean:write name="crmRoles" property="displayEnum(CPEStatus,${ report.cpeStatus })" scope="session" />
              </td>
              <td>${report.SRName}</td>
              <td>${report.BPName}</td>
              <td>${report.BPCode}</td>
              <td>${report.SRCode}</td>
              <td>${report.LMOName}</td>
              <td>${report.customerId}</td>
              <td> ${report.status} </td>
              <td> ${report.activated}</td>
              <td>
			  
			  ${report.cancelled}
			  
			  
			  </td>
              <td>
               
              <c:if test="${report.crfStage ne 'CN'and report.crfStage ne 'OB' }">
              <bean:write name="crmRoles" property="displayEnum(CRMFunctionalBinStagesCRMOperationStages,${report.crfStage})" scope="session" />
			 </c:if>
			  
			  </td>
              <td>
			     <bean:write name="crmRoles" property="displayEnum(PaymentMode,${ report.paymentMode })" scope="session" />
			  
			  </td>
            </tr>
           </c:forEach>
           
          </tbody>
        </table>
        <br class="clear" />
         </logic:notEmpty>
             <logic:empty name="reportForm" property="inaReportPojos">
        		<logic:equal value="true" name="reportForm" property="toSearch">
        	No Record Found.
      	 </logic:equal>
     </logic:empty>
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