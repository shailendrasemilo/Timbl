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
	<bean:define id="activeMenu" value="reopenTicket"/>
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
	<div class="relative">
<h4> Reopen Ticket Report</h4>
<form action="/qrcReportAction" id="IDTicketReopen" method="post">
	<div class="clear marginleft10 inner_left_lead floatl">
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
			<a href="#" id="IDTicketReopenSubmit" class="main_button" style="margin-right:40px;"><span>Search</span></a> 
		</p>
		<br class="clear"/>

	</div>
	</form>
	

	<p class="floatl clear" >
	<logic:notEmpty  name="reportForm" property="qrcReportPojos">
	  <table id="reportDataTable">
          <thead>
           <tr>
              <td>Customer&nbsp;ID</td>
              <td>Customer&nbsp;Name</td>
              
               <td>Ticket&nbsp;ID</td>
			   <td>Qrc&nbsp;Type</td>
              <td>Category&nbsp;Name</td>
			  <td>SubCategory&nbsp;Name</td>
			   <td>Sub&nbsp;SubCategory&nbsp;Name</td>
			   <td>Ticket&nbsp;Status</td>
			    <td>Reopen&nbsp;Count</td>
			   <td>Raised&nbsp;Date</td>
			  <td>Raised&nbsp;User</td>
			   <td>Resolved&nbsp;Date</td>
			   <td>Resolved&nbsp;User</td>
			    <td>Resolution&nbsp;Name</td>
			  <td>RCA&nbsp;Name</td>
              <td>Attribute&nbsp;Name</td>
              <td>BIN&nbsp;Name</td>
			   <td>Service&nbsp;Name</td>
			   <td>Total&nbsp;Resolution&nbsp;Time</td>
			  <td>Slab</td>
			  <td>Remarks</td>
			    </tr>
          </thead>
		   <tbody>
		    
		   <logic:iterate id="ticketList" name="reportForm" property="qrcReportPojos">
		   <tr>
		   
		   <td>${ticketList.customerId}</td>
           <td>${ticketList.custFname}&nbsp;${ticketList.custLname}</td>    
               <td>${ticketList.ticketId}</td>
			   <td><bean:write name="crmRoles" property="displayEnum(qrcType,${ticketList.qrcType })" scope="session" /></td>
              <td>${ticketList.category}</td>
			  <td>${ticketList.subCategory}</td>
			   <td>${ticketList.subSubCategory}</td>
			   <td><bean:write name="crmRoles" property="displayEnum(AllStatus,${ticketList.ticketStatus })" scope="session" /></td>
			    <td>${ticketList.reopenCount}</td>
			   <td> <bean:write name="crmRoles" property="reportXmlDate(${ticketList.raisedDate})" scope="session"/></td>
			  <td>${ticketList.raiseUser}</td>
			   <td> <bean:write name="crmRoles" property="reportXmlDate(${ticketList.srResolvedOn})" scope="session"/></td>
			   <td>${ticketList.resolvedBy}</td>
			   <td>${ticketList.resolutionCode}</td>
			  <td>${ticketList.rca}</td>
              <td>${ticketList.attributedTo}</td>
              <td><bean:write name="crmRoles" property="displayEnum(functionalBins,${ticketList.binName})" scope="session" /></td>
			   <td><bean:write name="crmRoles" property="displayEnum(Product,${ticketList.product })" scope="session" />
			   </td>
			   <td>${ ticketList.resolutionTime}</td>
			  <td>${ticketList.slab}</td>
			  <td>${ticketList.remarks}</td>
			  </tr>
           </logic:iterate>
          
		   </tbody>
		  </table>
		   </logic:notEmpty>
		   <logic:empty name="reportForm" property="qrcReportPojos">
        <logic:equal value="true" name="reportForm" property="toSearch">
        	No Record Found.
      	 </logic:equal>
     </logic:empty></p>

	
	<br class="clear" />
	
	
</div>
<p class="clear"/>
</div>
</div>
</div>