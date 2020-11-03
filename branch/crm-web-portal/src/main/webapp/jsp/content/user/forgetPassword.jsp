<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@page import="com.np.tele.crm.utils.Captcha"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<%@taglib uri="http://struts.apache.org/tags-html" prefix="html"%>

<body style=" background:#ffffff;">

<div id="login_section_top">
<html:form action="/forgetPassword" styleId="forgetPassword" >
 <div class="login_section_top">
   <h1><strong>Welcome</strong> to <bean:message bundle="userProp" key="companyName"/>&nbsp;CRM</h1>
   
      
        <p class="h5_bg"> <span>Forgot Password</span></p>
        <p class="goback"><html:link href="login.do">Go Back To Login Page</html:link></p>
	<p class="serverSideMessage">
	
	
	<span class="success_message">
   			<logic:messagesPresent message="true" property="appMessage">
			<html:messages id="msg" message="true" property="appMessage">
			<bean:write name="msg" />
			</html:messages>
			</logic:messagesPresent>
			</span>
		 <span class="error_message">
		 	
		 	<html:errors property="appError"/>
		 </span>
	</p>
		
		<div class="login_alt">
          <p><b>User ID</b>
        <html:text  styleClass="login_textbox" styleId="userId" property="userId"/>
        <html:errors property="userId" />
        </p>
        
        <p><b>Email ID</b>
        <html:text   styleClass="login_textbox"  property="emailId" styleId="emailId"/>
        <html:errors property="emailId" />
        </p>
        
        <p><b>Verification Text</b><img src="Captcha.jpg" border="0" Id="captcha" alt="captcha" align="absmiddle" class="captcha"/>
		&nbsp;&nbsp;&nbsp;&nbsp;<html:link href="#" onclick="return reloadCaptcha('captcha');" styleClass="grey_button">Reload</html:link>
		</p>
          <p class="serverSideMessage">
		 <span class="error_message">
        <logic:messagesPresent message="true" property="captchaReenter" >		 	
		 <html:messages id="msg" property="captchaReenter" message="true">
              <bean:write name="msg" />
        </html:messages>
        </logic:messagesPresent>
		</span>
		
        </p>
        
        <p>
       	<b> Enter the verification text</b>
        <input class="login_textbox" name="answer" type="text" id="answer" />
        
        </p>
        
  		<p class="clear margintop10"><b></b><html:link href="#" styleClass="login_button" styleId="submit_forgetPassword" >Submit</html:link></p>      
       </div>
        
 </div> 
</html:form>
</div>
<div id="login_section_bottom" style="background:#fff;"><p class="login_section_bottom"></p></div>
</body>



