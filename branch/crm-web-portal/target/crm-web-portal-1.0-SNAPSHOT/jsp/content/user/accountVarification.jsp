<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<%@taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@taglib uri="http://struts.apache.org/tags-nested" prefix="nested"%>
<html>
<head>
<script type="text/javascript">
	
</script>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
</head>
<body>
	<div id="section">
		<html:form action="/accountVerification" method="post">
			<html:hidden name="loginForm" property="recordID"
				value="${loginForm.recordID}" />
			<div class="section">
				<logic:notEmpty name="loginForm" property="recordID">
					<h2>
						Welcome&nbsp;<bean:write name="loginForm" property="firstName" />&nbsp;<bean:write name="loginForm" property="lastName" />
					</h2>
					<div class="inner_section">

						<div class=" inner_left margintop20">
						<logic:equal name="loginForm" property="crmUserPojo.status" value="P">
						
							<p>						
								<span class="bold font14 marginright9 ">Please click on
									link to activate your account</span> <a
									href="javascript:activateAccount();" class="yellow_button">Activate</a>
							</p>
							<br>
							<html:hidden name="loginForm" property="firstName"
								value="${loginForm.firstName}" />
							<html:hidden name="loginForm" property="lastName"
								value="${loginForm.lastName}" />
						</logic:equal>
						<logic:notEqual name="loginForm" property="crmUserPojo.status" value="P">
						<p>
								<span class="bold font14 marginright9 ">
									Your account is already 
									<logic:equal name="loginForm" property="crmUserPojo.status" value="N">New User</logic:equal>									
									<logic:equal name="loginForm" property="crmUserPojo.status" value="A">Active</logic:equal>
									<logic:equal name="loginForm" property="crmUserPojo.status" value="L">Lock</logic:equal>
									<logic:equal name="loginForm" property="crmUserPojo.status" value="E">Expire</logic:equal>
									<logic:equal name="loginForm" property="crmUserPojo.status" value="I">Inactive</logic:equal>
									<logic:equal name="loginForm" property="crmUserPojo.status" value="D">Deleted</logic:equal>
									.<br/><br/>Please click <a href="./" style="text-decoration:underline;">here</a> to login.
								</span>
								
								
							</p>
						</logic:notEqual>
						</div>
					</div>
				</logic:notEmpty>
				<p class="clear" style="height: 250px;"></p>
				<div id="login_section_bottom" style="background:#fff;"><p class="login_section_bottom"></p></div>
			</div>
		</html:form>
	</div>
	
</body>
</html>