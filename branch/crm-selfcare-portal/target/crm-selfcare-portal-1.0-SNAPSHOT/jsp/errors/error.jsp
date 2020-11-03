<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>Error Page</title>
<link href="css/common.css" rel="stylesheet" media="screen" />
<link href="css/userManagement.css" rel="stylesheet" media="screen" />
<%@taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<style>
#login_section_top .login_section_top {
	width: 770px;
	margin: 0 auto;
	padding: 40px 0 15px 0;
	position: relative;
	height: 290px;
}
</style>
</head>
<body>
	<!----------------- Section ------------------------------->
	<div id="login_section_top">
		<div class="login_section_top">
			<p class="error_message">
				<h2>
					<font color="red"> <bean:message key="error.global.message" /></font>
				</h2>
				<br />
			</p>
		</div>
	</div>
	<div id="login_header"></div>
</body>
</html>
