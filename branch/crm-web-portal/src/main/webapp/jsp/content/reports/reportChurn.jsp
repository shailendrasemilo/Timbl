<%@page import="com.np.tele.crm.utils.DateUtils"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@page import="java.util.Date;"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib uri="/WEB-INF/tlds/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/tlds/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/tlds/displaytag.tld" prefix="display"%>
<%@taglib uri="/WEB-INF/tlds/struts-html.tld" prefix="html"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<head>
<script src="javascript/tcal.js" type="text/javascript"></script>
</head>
<div class="loadingPopup hidden"></div>
<div id="section">
  <div class="section">
    <div class="inner_section ">
      <div class="inner_left_lead floatl  qrcLeft">
        <bean:define id="activeMenu" value="churn" />
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
        <form action="/qrcReportAction" id="IDChurnReport" method="post">
          <div class="relative">
            <h4>Churn Report</h4>
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
              <a href="#" id="IDchurnSubmit" class="main_button"><span>Search</span></a>
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
                      <td>Society</td>
                      <td>Area</td>
                      <td>City</td>
                      <td>Activation&nbsp;Date</td>
                      <td>Deactivation&nbsp;Date</td>
                      <td>Month</td>
                      <td>Reason</td>
                       <td>Churn&nbsp;Type</td>
                      <td>Reason Description</td>
                      <td>LMO</td>
                      <td>Segment</td>
     <%--             <td>Outstanding/&nbsp;Balance</td>   --%>
                      <td>Requested&nbsp;Date</td>
                      <td>Ticket&nbsp;ID</td>
                    </tr>
                  </thead>
                  <tbody>
                    <logic:iterate id="churnList" name="reportForm" property="inaReportPojos">
                      <bean:define id="xmlDate" name="churnList" property="tempDisconnectDate" type="javax.xml.datatype.XMLGregorianCalendar" />
                      <c:set var="arrayMsg" value="${fn:split(churnList.reason,'~')}"/>
                      <%
                          Date date = DateUtils.convertXmlCalToDate( xmlDate );
                      %>
                      <tr>
                        <td>${churnList.customerId}</td>
                        <td>${churnList.custFname}&nbsp;${churnList.custLname}</td>
                        <td>${churnList.societyName}</td>
                        <td>${churnList.areaName}</td>
                        <td>${churnList.cityName}</td>
                        <td><bean:write name="crmRoles" property="reportXmlDate(${churnList.activationDate})" /></td>
                        <td><bean:write name="crmRoles" property="reportXmlDate(${churnList.tempDisconnectDate})" /></td>
                        <td><fmt:formatDate pattern="MMMM" value="<%=date%>" /></td>
                        <td>${arrayMsg[0]}</td>
                        <td>${arrayMsg[1]}</td>
                        <td>${churnList.remarks}</td>
                        <td>${churnList.LMOName}</td>
                        <td><bean:write name="crmRoles" property="displayEnum(Product,${churnList.product})" scope="session" />&nbsp;<bean:write
                            name="crmRoles" property="displayEnum(ServiceType,${churnList.serviceType})" scope="session" /></td>
                    <%--      <td>${churnList.outStandingBalance}</td>  --%>
                        <td><bean:write name="crmRoles" property="reportXmlDate(${churnList.createdTime})" /></td>
                        <td>${churnList.ticketId}</td>
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