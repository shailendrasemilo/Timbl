<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<%@taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link href="css/myaccount-common.css" type="text/css" rel="stylesheet" />
<link href="css/popup-common.css" type="text/css" rel="stylesheet" />
<link href="css/color-${initParam.client}.css" type="text/css" rel="stylesheet" />
<!-- =============== fancy box =====================-->
<link rel="stylesheet" type="text/css" href="css/jquery.fancybox.css" />
<script type="text/javascript" src="javascript/jquery-1.10.2.min.js"></script>
<script type="text/javascript" src="javascript/jquery.fancybox.pack.js"></script>
<script type="text/javascript" src="javascript/fancybox-custom.js"></script>
<script type="text/javascript" src="javascript/selfcare.js"></script>
<script type="text/javascript">
  $(document).ready(function(){
    parent.jQuery.fancybox.update();
  });
</script>
</head>
<body>
  <div class="popup-loading hide">
    <img alt="Please wait" src="images/icons-${initParam.client }/ajaxloading.gif" />
  </div>
  <div class="mainWrapper" id="changePwd" style="width: 775px;">
    <div class="titleBar" style="width: 637px;">
      <span class="pageTitle">Log In</span> <a id="closeBtn" class="closeChooseAccount"></a>
    </div>
    <div class="loginArea" style="width: 646px;">
      <div class="pInfo hide">
        <logic:messagesPresent message="true">
          <div class="msgsuccess">
            <html:messages id="message" message="true">
              <bean:write name="message" />
            </html:messages>
          </div>
        </logic:messagesPresent>
      </div>
      <logic:messagesPresent property="appError">
        <bean:define id="hasErrors" value="true"></bean:define>
      </logic:messagesPresent>
      <div class="msgerror ${ hasErrors ? '' : 'hide' }">
        <html:errors />
      </div>
      <div>
        <html:form action="/login" styleId="proceedLoginForm">
          <html:hidden property="custMyAccountPojo.customerId" name="loginForm" styleId="hdnCustId" />
        </html:form>
        <ul class="loginDetail">
          <li class="tableHeading">
            <div class="colOne">Customer ID</div>
            <div class="colOne">CRF ID</div>

            <div class="colFour">Service Name</div>
            <div class="colOne">Service Type</div>

            <div class="colTwo">Reg. Mobile No.</div>
            <div class="colThree">Email ID</div>
          </li>
          <c:forEach items="${ loginForm.customerDetailsPojos }" var="customerDetailsPojos" varStatus="current">
            <li>
              <div class="colOne">
                <a href="javascript:void(0);" class="iul" onclick="proceedLoginWith(${ customerDetailsPojos.customerId });">${customerDetailsPojos.customerId}</a>
              </div>
              <div class="colOne">${ customerDetailsPojos.crfId }</div>

              <div class="colFour">
                <bean:write name="selfcareUtils" property="displayEnum(Product,${customerDetailsPojos.product})" />
              </div>
              <div class="colOne">
                <bean:write name="selfcareUtils" property="displayEnum(ServiceType,${customerDetailsPojos.serviceType})" />
              </div>

              <div class="colTwo">${ customerDetailsPojos.rmn }</div>
              <div class="colThree" title="${ customerDetailsPojos.custEmailId }">${ customerDetailsPojos.custEmailId }</div>
            </li>
          </c:forEach>
        </ul>
      </div>
      <div class="clr"></div>
    </div>
  </div>
</body>
</html>
