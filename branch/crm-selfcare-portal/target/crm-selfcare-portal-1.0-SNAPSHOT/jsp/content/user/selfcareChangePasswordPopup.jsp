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
<script type="text/javascript">
	$( document ).ready( function(){
		if ( window.top == window.self ) {
			window.location.href = 'user.do?method=logout';
			$( '.popup-loading' ).removeClass( 'hide' );
		}
	} );
</script>
</head>
<body>
  <div class="popup-loading hide">
    <img alt="Please wait" src="images/icons-${initParam.client }/ajaxloading.gif" />
  </div>
  <div class="mainWrapper" id="changePwd">
    <div class="titleBar">
      <span class="pageTitle">Change Password</span>
      <logic:equal name="selfcareForm" property="custMyAccountPojo.status" value="N">
        <html:link href="user.do?method=changePasswordPage" styleId="closeBtn" />
      </logic:equal>
      <logic:notEqual name="selfcareForm" property="custMyAccountPojo.status" value="N">
        <a id="closeBtn" class="closeCPwdPopup" href="#"></a>
      </logic:notEqual>
    </div>
    <div class="changePasswordArea">
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
        <html:form action="/user" styleId="changePasswordForm">
          <div class="changePassDetail marginT20">
            <div class="userDetail">
              <html:hidden property="custMyAccountPojo.customerId" name="selfcareForm" styleClass="inputField" styleId="chngePswrdCustId" />
            </div>
            <div class="clr"></div>
          </div>
          <div class="changePassDetail marginT20">
            <label>Old Password:</label>
            <div class="userDetail">
              <html:password property="custMyAccountPojo.password1" value="" name="selfcareForm" maxlength="15" styleClass="inputField"
                styleId="chngePswrdCustOldPw" />
            </div>
            <div class="clr"></div>
          </div>
          <div class="changePassDetail marginT10">
            <label>New Password:</label>
            <div class="userDetail">
              <html:password property="custMyAccountPojo.password" value="" name="selfcareForm" maxlength="15" styleClass="inputField"
                styleId="chngePswrdCustNewPw" />
            </div>
            <div class="clr"></div>
          </div>
          <div class="changePassDetail marginT10">
            <label>Retype New Password:</label>
            <div class="userDetail">
              <html:password property="retypePassword" name="selfcareForm" value="" maxlength="15" styleClass="inputField"
                styleId="chngePswrdCustRNewPw" />
            </div>
            <div class="clr"></div>
          </div>
          <div class="changePassDetail marginT10">
            <label>Verification Text:</label><img src="Captcha.jpg" border="0" Id="captcha" alt="captcha" align="absmiddle" class="captcha" /></b>
            &nbsp;&nbsp;&nbsp;&nbsp;
            <html:link href="#" styleClass="grey_button" onclick="return reloadCaptcha('captcha');">Reload</html:link>
          </div>
          <div class="changePassDetail marginT10">
            <label>Enter the verification text:</label>
            <div class="userDetail">
              <input class="inputField" name="answer" type="text" id="answer1" maxlength="10" />
              </p>
            </div>
            <div class="clr"></div>
          </div>
          <div class="changePassDetail marginT10">
            <label></label>
            <div class="userDetail">
              <html:link href="#" styleClass="signinBtn floatR btn" styleId="chngePswrdBtn">Submit</html:link>
            </div>
            <div class="clr"></div>
          </div>
        </html:form>
      </div>
    </div>
  </div>
</body>
</html>