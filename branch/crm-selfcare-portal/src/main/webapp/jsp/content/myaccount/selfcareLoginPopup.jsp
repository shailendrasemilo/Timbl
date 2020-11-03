<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
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
<script>
	$( document ).ready( function(){
		if ( $( '#scCustRMN' ).val() == 0 ) {
			$( '#scCustRMN' ).val( '' );
		}
	} );
</script>
</head>
<body>
  <div class="popup-loading hide">
    <img alt="Please wait" src="images/icons-${initParam.client }/ajaxloading.gif" />
  </div>
  <div class="mainWrapper loginWrap">
    <div class="titleBar">
      <span class="pageTitle">Sign In</span> <a id="closeBtn" class="closeSignIn"></a>
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
        <html:form action="/login.do?method=authenticate" styleId="selfcareLoginForm" onsubmit="return selfcareLogin();">
          <div class="userDetailArea marginT10">
            <label>Customer ID:</label>
            <div class="userDetail">
              <html:text property="custMyAccountPojo.customerId" name="loginForm" styleClass="inputField loginOption" onkeyup="removeChars(this);"
                disabled="${ hasErrors and (empty loginForm.custMyAccountPojo.customerId) ? true : false }" onblur="disableLoginFields(this);"
                styleId="scCustId" maxlength="15"></html:text>
            </div>
            <div class="clr"></div>
          </div>
          <div class="userDetailArea marginT10">
            <label></label>
            <div class="userDetail textAlignC">OR</div>
            <div class="clr"></div>
          </div>
          <div class="userDetailArea marginT10">
            <label>Email ID:</label>
            <div class="userDetail">
              <html:text property="custMyAccountPojo.custEmailId" name="loginForm" styleClass="inputField loginOption"
                disabled="${ hasErrors and (empty loginForm.custMyAccountPojo.custEmailId) ? true : false }" onblur="disableLoginFields(this);"
                styleId="scCustEmail" maxlength="255"></html:text>
            </div>
            <div class="clr"></div>
          </div>
          <div class="userDetailArea marginT10">
            <label></label>
            <div class="userDetail textAlignC">OR</div>
            <div class="clr"></div>
          </div>
          <div class="userDetailArea marginT10">
            <label>
              Registered Mobile No. (RMN): <span style="float: right">+91</span>
            </label>
            <div class="userDetail">
              <html:text property="custMyAccountPojo.rmn" name="loginForm" styleClass="inputField loginOption" onblur="disableLoginFields(this);"
                onkeyup="removeChars(this);" disabled="${ hasErrors and (loginForm.custMyAccountPojo.rmn eq 0) ? true : false }" styleId="scCustRMN"
                maxlength="10"></html:text>
            </div>
            <div class="clr"></div>
          </div>
          <div class="marginT10 divideRow"></div>
          <div class="userDetailArea marginT10">
            <label>Password:</label>
            <div class="userDetail">
              <html:password property="custMyAccountPojo.password" name="loginForm" styleClass="inputField" styleId="scCustPw" maxlength="15"></html:password>
            </div>
            <div class="clr"></div>
          </div>
          <div class="userDetailArea marginT10">
            <label></label>
            <div class="userDetail">
              <html:submit styleClass="submitBtn floatR btn" value="Submit"></html:submit>
              <%--
                <input type="button" id="scLoginBtn" value="Submit" class="submitBtn floatR btn" />
               --%>
            </div>
            <div class="clr"></div>
          </div>
          <div class="userDetailArea marginT20">
            <label></label>
            <div class="userDetail">
              <html:link href="login.do?method=forgotPasswordPage" styleId="fancybox-forgotPassword" styleClass="forgotPwd floatR">Forgot Password?</html:link>
            </div>
            <div class="clr"></div>
          </div>
        </html:form>
      </div>
    </div>
  </div>
</body>
</html>