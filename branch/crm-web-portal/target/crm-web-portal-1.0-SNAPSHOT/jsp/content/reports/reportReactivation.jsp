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
				<bean:define id="activeMenu" value="reactivation" />
					<%@include file="customerStatusMenu.jsp"%>
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
				<h4> Reactivation Report</h4>
				<form action="/qrcReportAction" id="IDReactivationReport" method="post">
				<html:hidden name="reportForm" property="toSearch" value="true"/>
					<div class="marginleft10 inner_left_lead floatl">
						<p class="floatl clear marginleft15 margintop20">
  <strong> From Date<span class="error_message verticalalignTop">*</span></strong>
   <html:text property="fromDate" styleId="fromDate" name="reportForm" styleClass="tcal tcalInput" style="color:black !important;" readonly="readonly"></html:text>
  </p>
  <p class="floatl marginleft30 margintop20">
   <strong> To Date<span class="error_message verticalalignTop">*</span></strong>
              <html:text property="toDate" styleId="toDate" name="reportForm" styleClass="tcal tcalInput" style="color:black !important;" readonly="readonly" />
  </p>
   <p class="floatl marginleft30 margintop20">
  <strong> Service Name</strong>
  <span class="LmsdropdownWithoutJs">
  <html:select property="productType" name="reportForm">
  <html:option value="">Please Select</html:option>
  <logic:notEmpty name="reportForm" property="productTypeList">
  <html:optionsCollection name="reportForm" property="productTypeList" label="contentName" value="contentValue"/>
  </logic:notEmpty>
  </html:select>
  </span>
  </p>
  <p class="floatl marginleft30 margintop20">
  <strong> Service Type</strong>
  
  <span class="LmsdropdownWithoutJs">
  <html:select property="serviceType" name="reportForm">
  <html:option value="">Please Select</html:option>
  <logic:notEmpty name="reportForm" property="serviceTypeList">
  <html:optionsCollection name="reportForm" property="serviceTypeList" label="contentName" value="contentValue"/>
  </logic:notEmpty>
  </html:select>
  </span>
  
  </p>
					</div>
					<br />
					<div class="floatr buttonPlacement">
						<a href="#" id="IDReactivationSubmit" class="main_button"><span>Search</span></a> 
					</div>
					
					<p class="floatl clear">&nbsp;</p>
					
<div style="width:100%;">	
<p class="floatl clear">
<logic:notEmpty name="reportForm" property="inaReportPojos">
        <table id="reportDataTable" style="width:100%">
          <thead>
            <tr>
             <td>Customer&nbsp;ID</td>
              <td>Customer&nbsp;Name</td>
			  <td>Society</td>
              <td>Area</td>
			  <td>City</td>
			   <td>Activation&nbsp;Date</td>
			   <td>Deactivation&nbsp;Date</td>
				<td>Churn&nbsp;Reason</td>
			   <td>Reactivation&nbsp;Date</td>
			    <td>Segment</td> 
            </tr>
          </thead>
          <tbody>
       <logic:iterate id="reactivationList" name="reportForm" property="inaReportPojos">
           <tr>
               <td><span style="display: inline-block;width: 40px;">${reactivationList.customerId}</span></td>
              <td><span style="display: inline-block;width: 70px;">${reactivationList.custFname}&nbsp;${reactivationList.custLname}</span></td>
              <td><span style="display: inline-block;width: 80px;">${reactivationList.societyName}</span></td>
			  <td><span style="display: inline-block;width: 60px;">${reactivationList.areaName}</span></td>
			   <td><span style="display: inline-block;width: 80px;">${reactivationList.cityName}</span></td>
              <td><span style="display: inline-block;width: 100px;"><bean:write name="crmRoles" property="reportXmlDate(${reactivationList.activationDate})" scope="session"/></span></td>
			  <td><span style="display: inline-block;width: 100px;"> <bean:write name="crmRoles" property="reportXmlDate(${reactivationList.lastModifiedTime})" scope="session"/></span>
			  </td>
			  <td><span style="display: inline-block;width: 100px;">${reactivationList.reason}</span></td>
              <td><span style="display: inline-block;width: 100px;"><bean:write name="crmRoles" property="reportXmlDate(${reactivationList.createdTime})" scope="session"/></span></td>
			 <td><span style="display: inline-block;width: 100px;"><bean:write name="crmRoles" property="displayEnum(Product,${reactivationList.product})" scope="session"/>&nbsp;<bean:write name="crmRoles" property="displayEnum(ServiceType,${reactivationList.serviceType})" scope="session"/></span> </td>
            </tr>
          </logic:iterate>
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
				</form>
			</div>
			<p class="clear" />