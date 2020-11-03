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
	<bean:define id="activeMenu" value="adjustment"/>
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
<h2> Adjustment Report</h2>
<form action="/qrcReportAction" id="IDAdjustmentReport" method="post">
	<div class="clear  inner_left_lead floatl">
		<html:hidden name="reportForm" property="toSearch" value="true"/>
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
              </label></p></div>	
	</div>
	<div class="floatr inner_right">
		<p class="buttonPlacement">
			<a href="#" id="IDSubmitadjustment" class="main_button" style="margin-right:5px;"><span>Search</span></a> 
		</p>
		<br class="clear"/>

	</div>
	</form>
	 <logic:notEmpty  name="reportForm" property="custWaiverPojos">
	<div class="dTInteractionRpt ">	
	  <table id="reportDataTable" class="floatl" class="width950">
          <thead>
            <tr>
              <td >Customer&nbsp;ID</td>
              <td >CAF&nbsp;NO</td>
              <td >Bill&nbsp;Cycle</td>
			  <td >Waiver&nbsp;Date</td>
              <td class="width43">Waiver&nbsp;Amt</td>
              <td class="width153">Waiver&nbsp;Sub Remarks</td>
			  <td class="width153">Waiver&nbsp;Type</td>
              <td class="width153">Bill&nbsp;No</td>
			  <td class="width153">Ticket&nbsp;No</td>
              <td class="width153">User&nbsp;Name</td>
              <td class="width153">Remarks</td></tr>
          </thead>
		   <tbody>
		  <c:forEach items="${reportForm.custWaiverPojos}" var="waiverPojo">
		   <tr>
            
              <td>${waiverPojo.customerId }</td>
              <td>${waiverPojo.crfNo }</td>
              <td>${waiverPojo.billCycle}</td>
			  <td><bean:write name="crmRoles" property="reportXmlDate(${waiverPojo.waiverPostedDate})" scope="session"/></td>
              <td>${waiverPojo.waiverAmount }</td>
              <td>${waiverPojo.waiverHead }</td>
			  <td>${waiverPojo.waiverType }</td>
              <td>${waiverPojo.billNo }</td>
			  <td>${waiverPojo.srTicketId }</td>
              <td>${empty waiverPojo.finalApprovedBy ? waiverPojo.approvedBy :waiverPojo.finalApprovedBy}</td>
              <td>${waiverPojo.remarks }</td>
			   </tr>
			 </c:forEach>
           
		   </tbody>
		  </table>
	</div>
	</logic:notEmpty>
	<br class="clear" />
	<logic:empty name="reportForm" property="custWaiverPojos">
	<logic:equal name="reportForm" property="toSearch" value="true">
	No Record Found
	</logic:equal>
	</logic:empty>
	
</div>
<p class="clear"/>
</div>
</div>
</div>