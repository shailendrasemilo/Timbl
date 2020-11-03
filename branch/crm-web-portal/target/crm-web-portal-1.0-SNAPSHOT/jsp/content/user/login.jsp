<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<%@taglib uri="http://struts.apache.org/tags-html" prefix="html"%>

<script>
$(document).ready(function(){
$(":input").bind("click keypress",function(evt) {
	var keyCode = evt.which || evt.keyCode;
	if (keyCode == 13) { 
	if ($('#login').valid()) { 
		document.forms[0].action = "login.do?method=loginAuthentication";
		document.forms[0].submit();
		}
	  }
	});
});
</script>
<body style="background:#D8D8D8 !important">
<div id="login_section_top">
	<html:form action="/login" styleId="login" method="post">
		<div class="login_section_top">
		<h1><strong>Welcome</strong> to <bean:message bundle="userProp" key="companyName"/> CRM</h1>
			<div class="login">
			<h5><span>Login</span></h5>
				<div class="userId_error"></div>
				<div>
				<span class="error_message">
				<html:errors property="errors"/>
				</span>
				
			<span class="success_message">
   			<logic:messagesPresent message="true" property="appMessage">
			<html:messages id="msg" message="true" property="appMessage">
			<bean:write name="msg" />
			</html:messages>
			</logic:messagesPresent>
			</span>
					<span class="error_message">
						<html:errors property="appError"/>
				<html:errors property="message"/>
				</span>
				</div>
				
				<p class="margintop20">
				    <span class="error_message smsShow"><html:errors  property="userId"/></span>
					<b><img src="./images/userid-text.png" align="absmiddle"alt="User ID"  /></b>
					<html:text property="userId" styleId="userId" styleClass="login_textbox" maxlength="128" />
					
				</p>
				<p>
				    <span class="error_message smsShow"><html:errors  property="password"/></span>
					<b><img src="images/password-text.png" alt="Password" align="absmiddle" /></b>
					<html:password styleClass="login_textbox" property="password" styleId="password" maxlength="15" />
					
				</p>
				<p>
					
					<html:link href="forgetPassword.do?method=forgetPasswordPage" styleClass="forgotPassword">Forgot Password</html:link>
					<br>
				</p>
				<p class="clear">
				<b></b>
				<html:link href="#" styleId="submit_login" styleClass="login_button">Login</html:link>
				</p>
			</div>
		</div>
	</html:form>	
</div>
<div id="login_section_bottom"><p class="login_section_bottom"></p></div>

</body>

