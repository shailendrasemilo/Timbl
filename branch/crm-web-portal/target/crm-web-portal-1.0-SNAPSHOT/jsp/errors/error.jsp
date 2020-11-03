<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>Error Page</title>
<link href="css/common.css" rel="stylesheet" media="screen" />
<link href="css/userManagement.css" rel="stylesheet" media="screen" />
<%@taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<script src="javascript/jquery.min.js" type="text/javascript"></script>
<script src="javascript/jquery.validate.js" type="text/javascript"></script>
<script src="javascript/userManagement.js" type="text/javascript"></script>
<script src="javascript/common.js" type="text/javascript"></script>
</head>
<body style="background: #ffffff;">
  <div id="login_header">
    <div class="login_header">
      <img src="<bean:message bundle="userProp" key="brand.logo"/>" alt="" />
    </div>
  </div>
  <!----------------- Section ------------------------------->
  <div id="login_section_top">
    <div class="login_section_top">
      <p class="error_message">
        <h2>
          <font color="red"> <bean:message key="error.global.message" /></font>
        </h2>
        <br /> Please click <a href="./userManagement.do?method=myProfile">here</a> to go back.
      </p>
    </div>
  </div>
  </div>
  <div id="login_section_bottom" style="background:#fff;"><p class="login_section_bottom"></p></div>
  <div id="login_footer">
    <p class="login_footer">
      Copyright &copy; 2015 <bean:message bundle="userProp" key="companyName"/>&nbsp;Pvt. Ltd.
      <%--<a href="#">Helpdesk</a> <a href="#">Privacy Policy</a> <a href="#">Terms &amp; conditions</a> --%>
    </p>
  </div>
</body>
</html>
