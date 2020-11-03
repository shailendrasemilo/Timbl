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
	<bean:define id="activeMenu" value="interaction"/>
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
<h4> Interaction Report</h4>
<form action="/qrcReportAction" id="IDInteractionReport" method="post">
	<div class="clear marginleft10 inner_left_lead floatl">
			<html:hidden name="reportForm" property="toSearch" value="true"/>
   <p class="floatl clear">
  <strong> From Date<span class="error_message verticalalignTop">*</span></strong>
   <html:text property="fromDate" styleId="fromDate" name="reportForm" styleClass="tcal tcalInput" readonly="readonly"></html:text>
  </p>
  <p class="floatl marginleft30">
   <strong> To Date<span class="error_message verticalalignTop">*</span></strong>
              <html:text property="toDate" styleId="toDate" name="reportForm" styleClass="tcal tcalInput" readonly="readonly" />
              </p></div>	
	</div>
	<div class="floatr inner_right">
		<p class="buttonPlacement">
			<a href="#" id="IDInteractionSubmit" class="main_button" ><span>Search</span></a> 
		</p>
		<br class="clear"/>

	</div>
	</form>
	
	<div class="dTInteractionRpt" >
	<p class="floatl clear" >
	<logic:notEmpty name="reportForm" property="interactionPojos">
	   <table id="reportDataTable" style="width:100%"class="reportDataTable_wrapper">
          <thead>
            <tr>
              <td>Customer&nbsp;ID</td>
              <td>Date</td>             
			  <td>Category</td>
              <td>Sub&nbsp;Category</td>
              <td>User&nbsp;Name</td>
              <td>Remarks</td>               
			</tr>
          </thead>
		   <tbody>
			<logic:iterate id="interactionList" name="reportForm" property="interactionPojos">
		   <tr>
             <td><span style="display: inline-block;width: 100px;">${interactionList.customerId}</span></td>
              <td><span style="display: inline-block;width: 150px;"><bean:write name="crmRoles" property="reportXmlDate(${interactionList.createdTime})"/></span></td>      
			  <td><span style="display: inline-block;width: 100px;">${interactionList.interactionCategory}</span></td>
              <td><span style="display: inline-block;width: 100px;">${interactionList.interactionSubCategory}</span></td>
              <td><span style="display: inline-block;width: 100px;">${interactionList.createdBy}</span></td>
              <td><span style="display: inline-block;width: 100px;">${interactionList.remarks}</span></td>
            </tr>
            </logic:iterate>
		   </tbody>
		  </table>
		 </logic:notEmpty>
		<logic:empty name="reportForm" property="interactionPojos">
        <logic:equal value="true" name="reportForm" property="toSearch">
        	No Record Found.
      	 </logic:equal>
     </logic:empty></p>
	</div>
	
	
	<br class="clear" />
</div>
<p class="clear"/>
</div>
</div>
</div>