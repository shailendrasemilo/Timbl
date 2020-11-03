<%@taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
    	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="css/common.css" rel="stylesheet" media="screen" />
        <link href="css/color-${initParam.client }.css" rel="stylesheet" media="screen" />
		<link href="css/userManagement.css" rel="stylesheet" media="screen" />
        <link href="css/emailManagement.css" rel="stylesheet" media="screen" />
        <link href="css/masterdata.css" rel="stylesheet" media="screen" />
        <link href="css/lms.css" rel="stylesheet" media="screen" />
        <link href="css/tcal.css"  rel="stylesheet" type="text/css" />
        <link href="css/gis.css" rel="stylesheet" media="screen" />
        <link href="css/displaytag.css" rel="stylesheet" type="text/css" />
        <link href="css/ina.css" rel="stylesheet" media="screen" />
        <link href="css/qrc.css" rel="stylesheet" media="screen" />
        <SCRIPT type="text/javascript">
			window.history.forward();
			function noBack() { window.history.forward(); }
		</SCRIPT>   
        <script src="javascript/jquery.min.js" type="text/javascript"></script>
		<script src="javascript/jquery.validate.js" type="text/javascript"></script>
		<script src="dwr/engine.js" type="text/javascript"></script>
		<script src="dwr/util.js" type="text/javascript"></script>
		<script src="dwr/interface/crmDwr.js" type="text/javascript"></script> 
		<script src="javascript/common.js" type="text/javascript"></script>
		<script src="javascript/userManagement.js" type="text/javascript"></script>
		<script src="javascript/gis.js" type="text/javascript"></script>
  		<script src="javascript/alert.js" type="text/javascript"></script>
  		<script src="javascript/masterdata.js" type="text/javascript"></script>
  		<script src="javascript/lms.js" type="text/javascript"></script>
		<script src="javascript/tcal.js" type="text/javascript"></script> 
		<script src="javascript/rcaReason.js" type="text/javascript"></script>
		<script src="javascript/addReceipt.js" type="text/javascript"></script>
		<script src="javascript/ina.js" type="text/javascript"></script>
		<script src="javascript/npCrf.js" type="text/javascript"></script>
		<script src="javascript/lmsClientSideValidation.js" type="text/javascript"></script>
		<script src="javascript/paymentTracking.js" type="text/javascript"></script>
		<script src="javascript/paymentPosting.js" type="text/javascript"></script>
		<script src="javascript/upFrontPayment.js" type="text/javascript"></script>
		<script src="javascript/qrc.js" type="text/javascript"></script>
		 <script src="javascript/qrc.config.common.js" type="text/javascript"></script>
        <%-- Scripts for particular pages
        <script src="javascript/paymentReversal.js" type="text/javascript"></script> 
        --%>
	    <title><tiles:getAsString name="title" ignore="true" /></title>
	    
    </head>
    <body >
                      <tiles:insert attribute="header" ignore="true" />
                      <%-- 
                      <tiles:insert attribute="qrcMenu"/>
                      --%>
    	              <tiles:insert attribute="body" />
    	              <tiles:insert attribute="activity" />
    	              <tiles:insert attribute="remarksLog" />
                      <tiles:insert attribute="footer" />
    </body>
</html>