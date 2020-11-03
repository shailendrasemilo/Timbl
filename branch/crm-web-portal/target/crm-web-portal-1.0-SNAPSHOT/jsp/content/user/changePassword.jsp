<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<%@taglib uri="http://struts.apache.org/tags-html" prefix="html"%>

<head>

<link href="css/common.css" rel="stylesheet" media="screen" />
<script src="javascript/jquery.min.js" type="text/javascript"></script>
<script src="javascript/jquery.validate.js" type="text/javascript"></script>
<script src="javascript/userManagement.js" type="text/javascript"></script>
<script src="javascript/common.js" type="text/javascript"></script>
<link href="css/userManagement.css" rel="stylesheet" media="screen" />
</head>
<body style=" background:#ffffff !important;">
<div id="login_section_top">
<html:form action="/login" styleId="changePassword">
 <div class="login_section_top" style="min-height: 515px !important">
   		<h1><strong>Welcome</strong> to <bean:message bundle="userProp" key="companyName"/>&nbsp;CRM</h1>
	   		  <p class="h5_bg"> <span>Change Password</span></p>
        		 <p class="goback"><html:link href="login.do">Go Back To Login Page</html:link></p>
        <p class="clear" align="center"><span class="error_message">	
        <html:errors property="appError"/>
		</span></p>
        <p class="serverSideMessage clear" align="center">
        	<span class="success_message">
        	 <logic:messagesPresent message="true" property="appMessage">
				<html:messages id="message" message="true" property="appMessage">
				<bean:write name="message" /></html:messages>
			</logic:messagesPresent>
		</span>
	
		
		</p>
        
        <div class="login_alt">
         	 <p><b>Current Password:</b>
        	<html:password  styleClass="login_textbox" styleId="oldPassword" property="oldPassword" maxlength="15" value=""/></p>
        
        	<p><b>New Password:</b>
      		 <html:password   styleClass="login_textbox"  property="newPassword" styleId="newPassword" maxlength="15" value=""/></p>
        
       		 <p><b>Confirm Password:</b>
       		 <html:password  styleClass="login_textbox"  property="confirmPassword" styleId="confirmNewPassword" maxlength="15" value=""/></p>
				<p class="clear" align="right"><span class="error_message"  style="font:9px arial">(Please use combination of numbers, uppercase, lowercase and special characters with length 8 - 15.)</span></p>
        	<p><b>Verification Text:</b><img src="Captcha.jpg" border="0" Id="captcha" alt="captcha" align="absmiddle" class="captcha"/></b>
        	&nbsp;&nbsp;&nbsp;&nbsp;<html:link href="#" styleClass="grey_button" onclick="return reloadCaptcha('captcha');">Reload</html:link>
        	</p>
			<html:hidden property="changePasswordFlag" value="0"/>
			<logic:messagesPresent message="true" property="captchaReenter" >
        	<p class="serverSideMessage">
		 	<span class="error_message">
		 		<html:messages id="msg" property="captchaReenter" message="true">
              		<bean:write name="msg" />
        		</html:messages>
        	</span>
        	</logic:messagesPresent>                
    
       		<p><b> Enter the verification text:</b><input class="login_textbox" name="answer" type="text" id="answer1" /></p>
        
       		<p class="clear"><b class="changeLogin_text"></b>
        	</p>
        	<p class="clear margintop10"><b></b> 
        	<html:link href="#" styleClass="login_button" styleId="submit_changePassword" >Change Password</html:link></p>
     	</div>
 	</div> 
</html:form> 
</div>
</body>