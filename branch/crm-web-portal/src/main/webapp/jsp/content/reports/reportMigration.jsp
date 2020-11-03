<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib uri="/WEB-INF/tlds/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/tlds/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/tlds/displaytag.tld" prefix="display"%>
<%@taglib uri="/WEB-INF/tlds/struts-html.tld" prefix="html"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<head>
<script src="javascript/tcal.js" type="text/javascript"></script> 
</head>
<div class="loadingPopup hidden"></div>
<div id="section">
<div class="section">

<div class="inner_section ">
	<div class="inner_left_lead floatl  qrcLeft">
	<bean:define id="activeMenu" value="migration"/>
		</div>
<div class=" floatl manageGISRight  qrcRight qrcReports">
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
	<div class="relative">
<h2> Migration Report</h2>
<form action="/qrcReportAction" id="IDMigrationReport" method="post">
	<div class="clear  inner_left_lead floatl">
			<html:hidden name="reportForm" property="toSearch" value="true"/>
   <p class="floatl clear">
  <strong> From Date<span class="error_message verticalalignTop">*</span></strong>
   <html:text property="fromDate" styleId="fromDate" name="reportForm" styleClass="tcal tcalInput" readonly="readonly"></html:text>
  </p>
  <p class="floatl marginleft30">
   <strong> To Date<span class="error_message verticalalignTop">*</span></strong>
              <html:text property="toDate" styleId="toDate" name="reportForm" styleClass="tcal tcalInput" readonly="readonly" />
  </p>
   <p class="floatl marginleft30">
  <strong> Service Name</strong>
  <span class="LmsdropdownWithoutJs">
  <html:select property="productType" name="reportForm">
  <html:option value="">All</html:option>
  <logic:notEmpty name="reportForm" property="productTypeList">
  <html:optionsCollection name="reportForm" property="productTypeList" label="contentName" value="contentValue"/>
  </logic:notEmpty>
  </html:select>
  </span>
  </p>
  <p class="floatl marginleft30">
  <strong> Service Type</strong>
  
  <span class="LmsdropdownWithoutJs">
  <html:select property="serviceType" name="reportForm">
  <html:option value="">All</html:option>
  <logic:notEmpty name="reportForm" property="serviceTypeList">
  <html:optionsCollection name="reportForm" property="serviceTypeList" label="contentName" value="contentValue"/>
  </logic:notEmpty>
  </html:select>
  </span>
  
  </p>
			</div>
			
	</div>
	<div class="floatr inner_right">
		<p class="buttonPlacement">
			<a href="#" id="IDMigrationSubmit" class="main_button" style="margin-right:4px;"><span>Search</span></a> 
		</p>
		<br class="clear"/>
</form>
	</div>
	<logic:notEmpty name="reportForm" property="crmCustomerActivityPojos">
					
	<div class="dTInteractionRpt">
	  <table id="reportDataTable" >
          <thead>
            <tr>
              <td>Customer&nbsp;ID</td>
              <td>Old&nbsp;Value</td>
			  <td>Action&nbsp;Date</td>
              <td>New&nbsp;Value</td>
			  <td>Action</td>
                        
			</tr>
          </thead>
		   <tbody>
		   	<logic:iterate id="migrationList" name="reportForm" property="crmCustomerActivityPojos">
		   <tr>
              <td>${migrationList.customerId}</td>
              <td>${migrationList.oldValue}</td>
              <td> <bean:write name="crmRoles" property="reportXmlDate(${migrationList.createdTime})" scope="session"/></td>
			  <td>${migrationList.newValue}</td>
              <td>${migrationList.action}</td> 
            </tr>
            </logic:iterate>
		   </tbody>
		  </table>
	</div>
	</logic:notEmpty>
	<br class="clear" />
	<logic:empty name="reportForm" property="crmCustomerActivityPojos">
	<logic:equal name="reportForm" property="toSearch" value="true">
	No Record Found
	</logic:equal>
	</logic:empty>
	
</div>
<p class="clear"/>
</div>
</div>
</div>