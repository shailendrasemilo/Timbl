<%@page import="com.np.tele.crm.utils.DateUtils"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@page import="java.util.Date;"%>
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
        <bean:define id="activeMenu" value="safeCustody" />
        <%@include file="customerStatusMenu.jsp"%>
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
        <form action="/qrcReportAction" id="IDSafeCustodyReport" method="post">
          <div class="relative">
            <h4>Safe Custody Report</h4>
            <div class="clear marginleft10 inner_left_lead floatl">
              <html:hidden name="reportForm" property="toSearch" value="true" />
              <p class="floatl clear marginleft15 margintop20">
                <strong>From Date<span class="error_message verticalalignTop">*</span></strong>
                <html:text name="reportForm" styleClass="tcal" readonly="true" style="color:black !important;" property="fromDate" styleId="fromDate"></html:text>
                <font style="margin: 2px"></font>
              </p>
              <p class="floatl marginleft30 margintop20">
                <strong>To Date<span class="error_message verticalalignTop">*</span></strong>
                <html:text name="reportForm" styleClass="tcal" property="toDate" style="color:black !important;" readonly="true" styleId="toDate"></html:text>
                <font style="margin: 2px"></font>
              </p>
              <p class="floatl marginleft30 margintop20">
                <strong> Service Name</strong> <span class="LmsdropdownWithoutJs"> <html:select property="productType" name="reportForm">
                    <html:option value="">Please Select</html:option>
                    <logic:notEmpty name="reportForm" property="productTypeList">
                      <html:optionsCollection name="reportForm" property="productTypeList" label="contentName" value="contentValue" />
                    </logic:notEmpty>
                  </html:select>
                </span>
              </p>
              <p class="floatl marginleft30 margintop20">
                <strong> Service Type</strong> <span class="LmsdropdownWithoutJs"> <html:select property="serviceType" name="reportForm">
                    <html:option value="">Please Select</html:option>
                    <logic:notEmpty name="reportForm" property="serviceTypeList">
                      <html:optionsCollection name="reportForm" property="serviceTypeList" label="contentName" value="contentValue" />
                    </logic:notEmpty>
                  </html:select>
                </span>
              </p>
            </div>
          </div>
          <div class="floatr inner_right">
            <p class="buttonPlacement">
              <a href="#" id="IDSafeCustodySubmit" class="main_button"><span>Search</span></a>
            </p>
            <br class="clear" />
          </div>
          <div style="width: 100%;">
            <p class="floatl clear">
              <logic:notEmpty name="reportForm" property="inaReportPojos">
                <table id="reportDataTable" style="width: 100%">
                  <thead>
                    <tr>
                      <td>Customer&nbsp;ID</td>
                      <td>Customer&nbsp;Name</td>
                      <td>CAF&nbsp;Number</td>
                      <td>Customer&nbsp;Status</td>
                      <td>Safe&nbsp;Custody&nbsp;Remarks</td>
                      <td>Safe&nbsp;Custody&nbsp;Initiated&nbsp;Date</td>
                      <td>Safe&nbsp;Custody&nbsp;Initiated&nbsp;User</td>
                      <td>Safe&nbsp;Custody&nbsp;Expiry&nbsp;Date</td>
                      <td>Number&nbsp;of&nbsp;Days</td>
                    </tr>
                  </thead>
                  <tbody>
                    <logic:iterate id="custodyList" name="reportForm" property="inaReportPojos">
                      <tr>
                        <td>${custodyList.customerId}</td>
                        <td>${custodyList.custFname}&nbsp;${churnList.custLname}</td>
                        <td>${custodyLiCAFcrfId}</td>
                        <td><bean:write name="crmRoles" property="displayEnum(AllStatus,${custodyList.status })" scope="session" /></td>
                        <td>${custodyList.remarks}</td><!--  safe custody remarks -->
                        <td><bean:write name="crmRoles" property="toDate(${custodyList.activationDate})" /></td> <!--  safe custody initiated date -->
                        <td><bean:write name="crmRoles" property="toDate(${custodyList.currentOwner})" /></td><!--  safe custody initiated user -->
                        <td><bean:write name="crmRoles" property="toDate(${custodyList.ftApprovalDate})" /></td><!--  safe custody expiry date -->
                        <td>${custodyList.extendedDays}</td><!--  it should be calculated as Current Date - Safe Custody Initiated Date -->
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
          <br class="clear" />
      </div>
      <p class="clear" />
    </div>
  </div>
</div>