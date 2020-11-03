<%@taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>

<link href="css/userManagement.css" rel="stylesheet" media="screen" />
<link href="css/common.css" rel="stylesheet" media="screen" />
<link href="css/color-${initParam.client }.css" rel="stylesheet" media="screen" />
<SCRIPT type="text/javascript">
			window.history.forward();
			function noBack() { window.history.forward(); }
</SCRIPT>
<script src="javascript/jquery.min.js" type="text/javascript"></script>
<script src="javascript/jquery.validate.js" type="text/javascript"></script>
<script src="javascript/common.js" type="text/javascript"></script>
<script src="javascript/userManagement.js" type="text/javascript"></script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title><tiles:getAsString name="title" ignore="true" /></title>
</head>
<body >
	<tiles:insert attribute="header" ignore="true" />
	<tiles:insert attribute="body" />
	<tiles:insert attribute="footer" />
</body>
</html>