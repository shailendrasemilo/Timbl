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
	<bean:define id="activeMenu" value="validityExtension"/>
		<%@include file="csdMenu.jsp"%>
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
	<h4> Validity Extension Report</h4>
	<form action="/qrcReportAction" id="IDValidityExtensionReport" method="post">
	<div class="relative">
	<html:hidden name="reportForm" property="toSearch" value="true"/>
	<div class="clear marginleft10 inner_left_lead floatl">
		
   <p class="floatl clear">
  <strong> From Date<span class="error_message verticalalignTop">*</span></strong>
   <html:text property="fromDate" styleId="fromDate" name="reportForm" styleClass="tcal tcalInput" readonly="readonly"></html:text>
   <font></font>
  </p>
  <p class="floatl marginleft30">
   <strong> To Date<span class="error_message verticalalignTop">*</span></strong>
              <html:text property="toDate" styleId="toDate" name="reportForm" styleClass="tcal tcalInput" readonly="readonly" />
              <font ></font>
              <label id="ToDateError class="errorTextbox hidden">
             
              </label>
  </p>
   
			</div>
			<div class="floatr inner_right">
		<p class="buttonPlacement" style="margin-right:-39px;">
			<a href="#" id="IDValidityExtensionSubmit" class="main_button" style="margin-right:40px;"><span>Search</span></a> 
		</p>
		<br class="clear"/>
		</div>
			
	</div>
	
</form>
	
	
	<p class="floatl clear" >
	 <logic:notEmpty  name="reportForm" property="qrcReportPojos">
	  <table id="reportDataTable" class="floatl" >
          <thead>
            <tr>
              <td>Customer&nbsp;ID</td>
              <td>Customer&nbsp;Name</td>
			  <td>Action&nbsp;Date</td>
              <td>Ticket&nbsp;No</td>
			   <td>Extension&nbsp;Days</td>
			   <td>Reason</td>
			   <td>User&nbsp;ID</td>
			    <td>Service&nbsp;Name</td> 
			    <td>Remarks</td>
			     <td>&nbsp;</td> 
			</tr>
          </thead>
		   <tbody>
		   <logic:iterate id="extensionList" name="reportForm" property="qrcReportPojos">
		   <tr>
              <td>${extensionList.customerId}</td>
              <td>${extensionList.custFname}&nbsp;${extensionList.custLname}</td>
			  <td><bean:write name="crmRoles" property="reportXmlDate(${extensionList.activationDate})" scope="session"/></td>
			  <td>${extensionList.ticketId}</td>
              <td>${extensionList.extendedDays}</td>
			  <td>${extensionList.reason}</td>
			  <td>${extensionList.raiseUser}</td>
			  <td><bean:write name="crmRoles" property="displayEnum(Product,${extensionList.product})" scope="session" />&nbsp;<bean:write name="crmRoles" property="displayEnum(ServiceType,${extensionList.serviceType})" scope="session" /> </td>
             <td>${extensionList.remarks}</td>
             <td>&nbsp;</td>
            </tr>
            </logic:iterate>
           
		   </tbody>
		  </table>
		   </logic:notEmpty>
		   <logic:empty name="reportForm" property="qrcReportPojos">
        <logic:equal value="true" name="reportForm" property="toSearch">
        	No Record Found.
      	 </logic:equal>
     </logic:empty>
		   </p>
	
	<br class="clear" />
</div>
<p class="clear"/>
</div>
</div>
</div>