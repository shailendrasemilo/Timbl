<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib uri="/WEB-INF/tlds/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/tlds/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/tlds/displaytag.tld" prefix="display"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="css/common.css" rel="stylesheet" media="screen" />
<link href="css/color-${initParam.client }.css" rel="stylesheet" media="screen" />
<link href="css/userManagement.css" rel="stylesheet" media="screen" />
<link href="css/emailManagement.css" rel="stylesheet" media="screen" />
<link href="css/masterdata.css" rel="stylesheet" media="screen" />
<link href="css/lms.css" rel="stylesheet" media="screen" />
<link href="css/tcal.css" rel="stylesheet" type="text/css" />
<link href="css/gis.css" rel="stylesheet" media="screen" />
<link href="css/displaytag.css" rel="stylesheet" type="text/css" />
<link href="css/ina.css" rel="stylesheet" media="screen" />
<link href="css/qrc.css" rel="stylesheet" media="screen" />
</head>
<body style='background: white; margin-top: -20px;'>
  <logic:notEmpty name="logForm" property="ticketHistoryList" scope="session">
    <display:table id="ticketHistoryData" name="sessionScope.logForm.ticketHistoryList" class="dataTable" style="width:100%" pagesize="10"
      requestURI="logAction.do">
      <display:setProperty name="paging.banner.placement" value="bottom" />
      <display:column title="Sr.No" style="width:3%;" maxLength="5"><bean:write name="ticketHistoryData_rowNum" /></display:column>
      <display:column title="Action">
      	<bean:write name="crmRoles" property="displayEnum(CrmActionEnum,${ticketHistoryData.action})" scope="session" />
      </display:column>
      <display:column title="Old Values" style="width:20%;">
      <c:choose>
        <c:when test="${ticketHistoryData.action eq 'FU' }">
          <c:choose><c:when test="${not empty ticketHistoryData.oldFollowupOn}"><bean:write name="crmRoles" property="xmlDate(${ticketHistoryData.oldFollowupOn})" scope="session" /></c:when>
          <c:otherwise>-</c:otherwise></c:choose>
        </c:when>
        <c:when test="${ticketHistoryData.action eq 'RV' }">-</c:when>
        <c:when test="${ticketHistoryData.action eq 'AS' }">
          <c:choose><c:when test="${not empty ticketHistoryData.fromUser}"><bean:write name="ticketHistoryData" property="fromUser" /></c:when>
          <c:otherwise>-</c:otherwise></c:choose>
        </c:when>
        <c:when test="${ticketHistoryData.action eq 'FW' }">
          <c:choose><c:when test="${not empty ticketHistoryData.fromBin}"><bean:write name="crmRoles" property="displayEnum(functionalBins,${ticketHistoryData.fromBin})" scope="session" /></c:when>
          <c:otherwise>-</c:otherwise></c:choose>
        </c:when>
        <c:otherwise>-</c:otherwise>
        </c:choose>
      </display:column>
      <display:column title="New Values" style="width:20%;">
      <c:choose> 
	  	<c:when test="${ticketHistoryData.action eq 'OP' }">
			 <c:choose>
			 	<c:when test="${not empty ticketHistoryData.toBin}"><bean:write name="crmRoles" property="displayEnum(functionalBins,${ticketHistoryData.toBin})" scope="session" /></c:when>
			 <c:otherwise>-</c:otherwise></c:choose>
        </c:when>
      <c:when test="${ticketHistoryData.action eq 'FU' }">
          <c:choose>
          	<c:when test="${not empty ticketHistoryData.newFollowupOn}"><bean:write name="crmRoles" property="xmlDate(${ticketHistoryData.newFollowupOn})" scope="session" /></c:when>
          <c:otherwise>-</c:otherwise></c:choose>
        </c:when>
        <c:when test="${ticketHistoryData.action eq 'RV' }">
            <logic:notEmpty name="ticketHistoryData" property="actionTaken">
                      Action Taken: <bean:write name="ticketHistoryData" property="actionTaken" />, &nbsp;
                      Root Cause: <bean:write name="ticketHistoryData" property="rootCause" />, &nbsp;
                      Attributed To: <bean:write name="ticketHistoryData" property="attributedTo" />
            </logic:notEmpty>                      
        </c:when>
        <c:when test="${ticketHistoryData.action eq 'AS' }">
          <c:choose><c:when test="${not empty ticketHistoryData.toUser}"><bean:write name="ticketHistoryData" property="toUser" /></c:when><c:otherwise>-</c:otherwise></c:choose>
        </c:when>
        <c:when test="${ticketHistoryData.action eq 'FW' }">
         <c:choose><c:when test="${not empty ticketHistoryData.toBin}"><bean:write name="crmRoles" property="displayEnum(functionalBins,${ticketHistoryData.toBin})" scope="session" /></c:when>
         <c:otherwise>-</c:otherwise></c:choose>
        </c:when>
        <c:otherwise>-</c:otherwise>
        </c:choose>
      </display:column>
      <display:column title="Remarks" property="remarks" style="width:20%;"></display:column>
      <display:column title="Created By" property="createdBy"></display:column>
      <display:column title="Created Time">
        <bean:write name="crmRoles" property="xmlDate(${ticketHistoryData.createdTime})" scope="session" />
      </display:column>
    </display:table>
  </logic:notEmpty>
  <logic:empty name="logForm" property="ticketHistoryList">
    <span class="error_message"> No Record Found. </span>
  </logic:empty>
</body>
</html>
