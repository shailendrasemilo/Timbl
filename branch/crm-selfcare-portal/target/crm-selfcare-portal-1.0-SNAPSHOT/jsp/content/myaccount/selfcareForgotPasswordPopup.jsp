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
<link href="css/jquery.fancybox.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="javascript/jquery-1.10.2.min.js"></script>
<script type="text/javascript" src="javascript/jquery.fancybox.pack.js"></script>
<script type="text/javascript" src="javascript/fancybox-custom.js"></script>
<script type="text/javascript" src="javascript/selfcare.js"></script>
<script>
	$( document ).ready( function(){
		if ( $( '#chpswrdCustRMN' ).val() == 0 ) {
			$( '#chpswrdCustRMN' ).val( '' );
		}

	} );
</script>
</head>
<body>
  <div class="popup-loading hide">
    <img alt="Please wait" src="images/icons-${initParam.client }/ajaxloading.gif" />
  </div>
  <div class="mainWrapper forgotPwd">
    <div class="titleBar">
      <span class="pageTitle">Forgot Password</span>
      <html:link href="login.do?method=loginPage" styleId="closeBtn" />
    </div>
    <div class="forgotPasswordArea">
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
        <html:form action="/login" styleId="resetPswrdForm">
          <div class="userDetailArea marginT20">
            <label>Customer ID:</label>
            <div class="userDetail">
              <html:text property="customerDetailsPojo.customerId" name="loginForm" styleClass="inputField" styleId="chpswrdCustId" maxlength="8" onkeyup="removeChars(this);"></html:text>
            </div>
            <div class="clr"></div>
          </div>
          <div class="userDetailArea marginT10">
            <label></label>
            <div class="userDetail textAlignC">AND</div>
            <div class="clr"></div>
          </div>
          <div class="userDetailArea marginT10">
            <label>Registered Mobile No. (RMN): <span style="float: right">+91</span></label>
            <div class="userDetail">
              <html:text property="customerDetailsPojo.rmn" name="loginForm" styleClass="inputField" styleId="chpswrdCustRMN" maxlength="10" onkeyup="removeChars(this);"></html:text>
            </div>
            <div class="clr"></div>
          </div>
          <div class="userDetailArea marginT20">
            <label></label>
            <div class="userDetail">
              <html:link href="#" styleId="resetPswrdBt" styleClass="signinBtn floatR btn">Submit</html:link>
              <html:link href="login.do?method=loginPage" styleClass="signinBtn floatL btn" styleId="fancybox-signIn">Back to Sign In</html:link>
            </div>
            <div class="clr"></div>
          </div>
        </html:form>
      </div>
    </div>
  </div>
</body>
</html>
