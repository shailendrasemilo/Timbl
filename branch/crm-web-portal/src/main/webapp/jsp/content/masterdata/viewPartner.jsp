<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<%@taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@taglib uri="http://struts.apache.org/tags-nested" prefix="nested"%>
<%@taglib uri="/WEB-INF/tlds/displaytag.tld" prefix="display"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<link href="css/common.css" rel="stylesheet" media="screen" />
<link href="css/color-${initParam.client }.css" rel="stylesheet" media="screen" />
<link href="css/displaytag.css" rel="stylesheet" type="text/css" />
<script src="javascript/jquery.min.js" type="text/javascript"></script>
<script src="javascript/common.js" type="text/javascript"></script>

</head>
<body>
  <div id="lightboxPanel">

    <div class="popUp1">

      <div class="popUpContainer">
        <logic:notEmpty name="partnerManagementForm" property="partnerPojo">
          <h3 class="marginleft20 margintop10">Partner Details:</h3>
          <%
              int i = 0;
          %>
          <p class="popUpRow<%=++i % 2%>">
            <font class="popUpHead">Type of Business:</font> <font class="popUpText"> <logic:notEmpty name="partnerManagementForm"
                property="products">
                <logic:iterate id="products" name="partnerManagementForm" property="products" indexId="ctr">
                  <LABEL class="label_check margintop5" for="${products.contentName}">
                    <html:checkbox name="products" property="selected" styleId="${products.contentName}" value="true" indexed="true" disabled="true">${products.contentName}</html:checkbox>
                  </LABEL>
                </logic:iterate>
              </logic:notEmpty>
            </font>
          </p>
          <p class="popUpRow<%=++i % 2%>">
            <font class="popUpHead">Type of Partner:</font> <font class="popUpText"> <logic:notEmpty name="partnerManagementForm"
                property="partnerTypeList">
                <logic:iterate id="partnerTypeList" name="partnerManagementForm" property="partnerTypeList" indexId="ctr">
                  <LABEL class="label_check margintop5" for="${partnerTypeList.contentName}">
                    <html:checkbox name="partnerTypeList" property="selected" styleId="${partnerTypeList.contentName}" value="true" indexed="true"
                      disabled="true">${partnerTypeList.contentName}</html:checkbox>
                  </LABEL>
                </logic:iterate>
              </logic:notEmpty>
            </font>
          </p>
          <p class="popUpRow<%=++i % 2%>">
            <font class="popUpHead">Name of Partner:</font> <font class="popUpText"><bean:write name="partnerManagementForm"
                property="partnerPojo.partnerName" /></font>
          </p>
          <p class="popUpRow<%=++i % 2%>">
            <font class="popUpHead">Partner Short Name:</font> <font class="popUpText"><bean:write name="partnerManagementForm"
                property="partnerPojo.partnerShortName" /></font>
          </p>
          <p class="popUpRow<%=++i % 2%>">
            <font class="popUpHead">Partner Code:</font> <font class="popUpText"><bean:write name="partnerManagementForm"
                property="partnerPojo.crmPartnerCode" /></font>
          </p>
          <p class="popUpRow<%=++i % 2%>">
            <font class="popUpHead">Boarded Date:</font> <font class="popUpText"><bean:write name="partnerManagementForm"
                property="partnerPojo.strBoardedDate" /></font>
          </p>
          <p class="popUpRow<%=++i % 2%>">
            <font class="popUpHead">Created on:</font> <font class="popUpText">
            <bean:write name="crmRoles" property="xmlDate(${ partnerManagementForm.partnerPojo.createdTime})" scope="session"/>
            </font>
          </p>
          <p class="popUpRow<%=++i % 2%>">
            <font class="popUpHead">Partner's Executive Name:</font> <font class="popUpText"><bean:write name="partnerManagementForm"
                property="partnerPojo.partnerCen" /></font>
          </p>
          <p class="popUpRow<%=++i % 2%>">
            <font class="popUpHead">Designation:</font> <font class="popUpText"><bean:write name="partnerManagementForm"
                property="partnerPojo.designation" /></font>
          </p>

          <p class="popUpRow<%=++i % 2%>">
            <font class="popUpHead">Mobile Number:</font> <font class="popUpText"><bean:write name="partnerManagementForm"
                property="partnerPojo.mobileNo" /></font>
          </p>

          <p class="popUpRow<%=++i % 2%>">
            <font class="popUpHead">Email ID:</font> <font class="popUpText"><bean:write name="partnerManagementForm"
                property="partnerPojo.emailId" /></font>
          </p>
          <p class="popUpRow<%=++i % 2%>">
            <font class="popUpHead">Partner Related to Our Organization:</font> <font class="popUpText"><bean:write
                name="partnerManagementForm" property="partnerPojo.relatedDept" /></font>
          </p>

          <h3 class="marginleft20 margintop10">Head office</h3>

          <p class="popUpRow<%=++i % 2%>">
            <font class="popUpHead">Contact Person Name:</font> <font class="popUpText"><bean:write name="partnerManagementForm"
                property="partnerPojo.hoCpn" /></font>
          </p>

          <p class="popUpRow<%=++i % 2%>">
            <font class="popUpHead">Email ID:</font> <font class="popUpText"><bean:write name="partnerManagementForm"
                property="partnerPojo.hoEmailId" /></font>
          </p>

          <p class="popUpRow<%=++i % 2%>">
            <font class="popUpHead">Phone Number:</font> <font class="popUpText"><bean:write name="partnerManagementForm"
                property="partnerPojo.phoneNo" /></font>
          </p>

          <p class="popUpRow<%=++i % 2%>">
            <font class="popUpHead">Mobile Number:</font> <font class="popUpText"><bean:write name="partnerManagementForm"
                property="partnerPojo.hoMobileNo" /></font>
          </p>
          <p class="popUpRow<%=++i % 2%>">
            <font class="popUpHead">Fax Number:</font> <font class="popUpText"><bean:write name="partnerManagementForm"
                property="partnerPojo.fax" /></font>
          </p>
          <p class="popUpRow<%=++i % 2%>">
            <font class="popUpHead">Address:</font> <font class="popUpText"><bean:write name="partnerManagementForm"
                property="partnerPojo.address" /></font>
          </p>

          <%--  <p class="popUpRow<%=++i%2%>">
         	<font class="popUpHead">Partner ERP Code:</font>
         	<font class="popUpText"><bean:write name="partnerManagementForm"
								property="partnerPojo.partnerErpCode" /></font>
	     </p>--%>

          <p class="popUpRow<%=++i % 2%>">
            <font class="popUpHead">Current Status:</font>
            <logic:notEmpty name="partnerManagementForm" property="partnerPojo.currentStatus">
              <bean:write name="crmRoles" property="displayEnum(PartialStatus,${partnerManagementForm.partnerPojo.currentStatus})" scope="session" />
            </logic:notEmpty>

          </p>
          <%-- <p class="popUpRow<%=++i%2%>">
         	<font class="popUpHead">Added In ERP:</font>
         	<font class="popUpText">
         		<bean:define id="AddedInERP" name="partnerManagementForm" property="partnerPojo.addedErp"></bean:define>
         			<logic:equal name="AddedInERP" value="C">Complete</logic:equal>
					<logic:equal name="AddedInERP" value="P">Pending</logic:equal>
         	</font>
	   </p>--%>
          <p class="popUpRow<%=++i % 2%>">
            <font class="popUpHead">State:</font> <font class="popUpText"> <bean:write name="partnerManagementForm"
                property="partnerPojo.state" />
            </font>
          </p>
          <p class="popUpRow<%=++i % 2%>">
            <font class="popUpHead">City:</font> <font class="popUpText"> <bean:write name="partnerManagementForm" property="partnerPojo.city" />
            </font>
          </p>
          <p class="popUpRow<%=++i % 2%>">
            <font class="popUpHead">PIN code:</font> <font class="popUpText"> <bean:write name="partnerManagementForm"
                property="partnerPojo.pincode" />
            </font>
          </p>
          <h3 class="marginleft20 margintop10">View Documents</h3>         
          <c:url value="${initParam.dmshost }/np-document-upload/files/view/ADMIN/${partnerManagementForm.partnerPojo.partnerName }" var="dms_view"/>
          <iframe src='${dms_view}' scrolling='yes' style='border: 1px solid #ccc; overflow: hidden; width: 100%; height: 600px;'></iframe><%-- &SystemTime=${now}&DocType= --%>
        </logic:notEmpty>
      </div>
         <div class="LmsTable">
				<h4>Activity Log Details <span class="lmsMinusImage floatr"></span></h4>
			  <div class="viewLmsTable margintop10">
			 <iframe src="jsp/content/masterdata/displayAuditLog.jsp" frameborder="0" scrolling="yes"
                  style="border: none; overflow: hidden; width: 100%; height:250px;" allowTransparency="true" onload="iframeLoaded(this)"></iframe>
			 </div>
   	 </div>
      <br class="clear" />
    </div>
  </div>
</body>
</html>