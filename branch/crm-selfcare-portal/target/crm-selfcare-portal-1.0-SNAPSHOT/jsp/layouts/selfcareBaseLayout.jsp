<%@taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<link href="css/myaccount-common.css" type="text/css" rel="stylesheet" />
<link href="css/main.css" type="text/css" rel="stylesheet" />
<link href="css/color-${initParam.client}.css" type="text/css" rel="stylesheet" />
<link href="css/style.css" type="text/css" rel="stylesheet" />


<!-- <script type="text/javascript" src="javascript/jquery-1.10.2.min.js"></script>-->
<script type="text/javascript" src="javascript/jquery-1.8.1.js"></script>
<script type="text/javascript" src="javascript/selfcare.js"></script>
<!-- for quick pay -->
<link rel="stylesheet" type="text/css" href="css/billing.css"/>
<link href="css/StyleSheet.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="javascript/typeface.js"></script>
<script type="text/javascript" src="javascript/helvetica_neue_lt_std_45_light.typeface.js"></script>
<script type="text/javascript" src="javascript/helvetica_neue_lt_std_56_italic.typeface.js"></script>
<script type="text/javascript" src="javascript/helvetica_neue_lt_std_75_bold.typeface.js"></script>

<!-- =============== fancy box =====================-->
<link rel="stylesheet" type="text/css" href="css/jquery.fancybox.css" />
<script type="text/javascript" src="javascript/jquery.fancybox.pack.js"></script>
<script type="text/javascript" src="javascript/fancybox-custom.js"></script>
<!-- =============== speedometer box =====================-->
<script type="text/javascript" src="javascript/jquery.speedometer.js"></script>
<script type="text/javascript" src="javascript/jquery.jqcanvas-modified.js"></script>
<!-- =============== data tables =====================-->
<link rel="stylesheet" type="text/css" href="css/jquery.dataTables.css" />
<script type="text/javascript" src="javascript/jquery.dataTables.min.js"></script>
<script type="text/javascript" src="javascript/jquery.dataTables.custom.js"></script>
<!-- =============== dwr =============== -->
<script src="dwr/engine.js" type="text/javascript"></script>
<script src="dwr/util.js" type="text/javascript"></script>
<script type='text/javascript' src='dwr/interface/selfcareForm.js'></script>
<script src="dwr/interface/crmDwr.js" type="text/javascript"></script>
<script type="text/javascript">
	window.history.forward();
	function noBack(){
		window.history.forward();
	}
</script>
<title><tiles:getAsString name="title" ignore="true" /></title>
</head>
<body>
  <div id="wrapper">
    <tiles:insert attribute="header" ignore="true" />
    <tiles:insert attribute="menu"></tiles:insert>
    <tiles:insert attribute="body" />
    <tiles:insert attribute="footer" />
  </div>
</body>
</html>