<%@taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="css/common.css" rel="stylesheet" media="screen" />
<link href="css/color-${initParam.client }.css" rel="stylesheet" media="screen" />
<link href="css/emailManagement.css" rel="stylesheet" media="screen" />
<link href="css/lms.css" rel="stylesheet" media="screen" />
<link href="css/tcal.css" rel="stylesheet" type="text/css" />
<link href="css/gis.css" rel="stylesheet" media="screen" />
<link href="css/displaytag.css" rel="stylesheet" type="text/css" />
<link href="css/qrc.css" rel="stylesheet" media="screen" />
<link href="css/report.css" rel="stylesheet" media="screen" />
<link href="css/jquery.dataTables.min.css" rel="stylesheet" media="screen" />
<link href="css/jquery.dataTableTools.css" rel="stylesheet" media="screen" />
<script type="text/javascript" src="javascript/jquery-1.8.1.js"></script>
<script type="text/javascript" src="javascript/jquery.dataTables.min.js"></script>
<script type="text/javascript" src="javascript/jquery.dataTables.tableTools.js"></script>
<script type="text/javascript" src="javascript/jquery.dataTables.custom.js"></script>
<script src="javascript/tcal.js" type="text/javascript"></script>
<script src="javascript/common.js" type="text/javascript"></script>
<script src="javascript/jquery.validate.js" type="text/javascript"></script>
<script src="javascript/tcal.js" type="text/javascript"></script> 
<script src="dwr/engine.js" type="text/javascript"></script>
<script src="dwr/util.js" type="text/javascript"></script>
<script src="dwr/interface/crmDwr.js" type="text/javascript"></script> 
<script type="text/javascript" src="javascript/masterdata.js"></script>
<SCRIPT type="text/javascript">
	window.history.forward();
	function noBack(){
		window.history.forward();
	}
</SCRIPT>

<script src="javascript/report.js" type="text/javascript"></script>

<title><tiles:getAsString name="title" ignore="true" /></title>

</head>
<body>
  <tiles:insert attribute="header" ignore="true" />
  <tiles:insert attribute="body" />
  <tiles:insert attribute="footer" />
</body>
</html>