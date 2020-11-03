<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles"%>
<%@taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<%@taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
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
<!-- =============== dwr =============== -->
<script src="dwr/engine.js" type="text/javascript"></script>
<script src="dwr/util.js" type="text/javascript"></script>
<script src="dwr/interface/crmDwr.js" type="text/javascript"></script>
</head>
<body>
  <div class="mainWrapper loginWrap">
    <logic:messagesPresent message="true">
      <bean:define id="hasMessages" value="true" />
    </logic:messagesPresent>
    <div class="titleBar">
      <span class="pageTitle">Billing Address</span> <a id="closeBtn"
        onclick="${hasMessages ? 'parent.location.reload();parent.jQuery.fancybox.close();' : 'parent.jQuery.fancybox.close();'}"></a>
    </div>
    <div class="loginArea">
      <div class="pInfo hide"></div>
      <logic:messagesPresent property="appError">
        <bean:define id="hasErrors" value="true"></bean:define>
      </logic:messagesPresent>
      <div class="msgerror ${ hasErrors ? '' : 'hide' }">
        <html:errors />
      </div>
      <logic:messagesPresent message="true">
        <html:messages id="message" message="true">
          <div class="msgsuccess">
            <bean:write name="message" />
          </div>
        </html:messages>
      </logic:messagesPresent>
      <div>
        <html:form action="/user" styleId="updateAddressForm">
          <div class="userDetailArea marginT10">
            <label>Address Line 1:</label>
            <div class="userDetail">
              <html:text name="selfcareForm" property="billingAddressPojo.addLine1" styleClass="inputField" styleId="addLine1" maxlength="35"
                onkeyup="this.value = this.value.toUpperCase()" />
            </div>
            <div class="clr"></div>
          </div>
          <div class="userDetailArea marginT10">
            <label>Address Line 2:</label>
            <div class="userDetail">
              <html:text name="selfcareForm" property="billingAddressPojo.addLine2" styleClass="inputField" styleId="addLine2" maxlength="35"
                onkeyup="this.value = this.value.toUpperCase()" />
            </div>
            <div class="clr"></div>
          </div>
          <div class="userDetailArea marginT10">
            <label>Address Line 3:</label>
            <div class="userDetail">
              <html:text name="selfcareForm" property="billingAddressPojo.addLine3" styleClass="inputField" styleId="addLine3" readonly="true" />
            </div>
            <div class="clr"></div>
          </div>
          <div class="userDetailArea marginT10">
            <label>State:</label>
            <div class="userDetail">
              <html:select name="selfcareForm" styleClass="inputField" property="billingAddressPojo.stateId" styleId="stateId"
                onchange="populateCitybyState(this.value,'cityId');">
                <html:option value="0">Please Select</html:option>
                <html:optionsCollection name="selfcareForm" property="stateList" label="stateName" value="stateId" />
              </html:select>
            </div>
            <div class="clr"></div>
          </div>
          <div class="userDetailArea marginT10">
            <label>City:</label>
            <div class="userDetail">
              <html:select property="billingAddressPojo.cityId" name="selfcareForm" styleClass="inputField" styleId="cityId"
                onchange="updateAddressLine3();">
                <html:option value="0">Please Select</html:option>
                <logic:notEmpty name="selfcareForm" property="cityList">
                  <html:optionsCollection name="selfcareForm" property="cityList" label="cityName" value="cityId" />
                </logic:notEmpty>
              </html:select>
            </div>
            <div class="clr"></div>
          </div>
          <div class="userDetailArea marginT10">
            <label></label>
            <div class="userDetail">
              <input class="submitBtn btn floatL" type="button" value="Save" id="updateBillAddr" /> <a class="submitBtn btn floatL hideMe"
                href="javascript:void(0);" style="margin-left: 10px; line-height: 27px; font-weight: bold; text-decoration: none;"
                onclick="parent.jQuery.fancybox.close();">Cancel</a>
            </div>
            <div class="clr"></div>
          </div>
        </html:form>
      </div>
    </div>
  </div>
</body>
</html>
