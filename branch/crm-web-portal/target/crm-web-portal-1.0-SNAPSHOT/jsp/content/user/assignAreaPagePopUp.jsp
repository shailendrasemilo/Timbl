<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<%@taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@taglib uri="http://struts.apache.org/tags-nested" prefix="nested"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
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
        <link href="css/jquery.bonsai.css" rel="stylesheet" media="screen" />
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
		<script src="javascript/inboxController.js" type="text/javascript"></script>
		<script src="javascript/qrc.js" type="text/javascript"></script>
        <script src="javascript/qrc.config.common.js" type="text/javascript"></script>
        <script src="javascript/massOutage.js" type="text/javascript"></script>
        
        
</head>
<body>
<div>
<html:form action="/parameterManagement" >
<span class="dropdownWithoutJs">

<html:select name="loginForm" property="areaMappingPojo.stateId" styleId="stateId" onchange="populateCity( 'cityId', this.value )" >
									 <html:option value="0">Please Select</html:option>
										<logic:notEmpty name="loginForm" property="stateDataList" >
												<html:optionsCollection name="loginForm" property="stateDataList" label="stateName" value="stateId" />
												</logic:notEmpty >
</html:select>
</span>
<span class="dropdownWithoutJs">

						<html:select name="loginForm" property="areaMappingPojo.cityId"  styleId="cityId" onchange="populateArea('areaId',this.value );" >
									 <html:option value="0">Please Select</html:option>
										<logic:notEmpty name="loginForm" property="cityList" >
												<html:optionsCollection name="loginForm" property="cityList" label="cityName" value="cityId" />
												</logic:notEmpty >
										</html:select>
										</span>
										<span class="dropdownWithoutJs">
										<html:select name="loginForm" property="areaMappingPojo.areaId"  styleId="areaId" >
										<html:option value="0">Please Select</html:option>
										<logic:notEmpty name="loginForm" property="areaList" >
												<html:optionsCollection name="loginForm" property="areaList" label="area" value="areaId" />
												</logic:notEmpty >
										</html:select>
										</span>
										 <div class="floatr inner_right">
     <a href="#" id="submitAssignParameter_assignparameter" onclick="" class="main_button_multiple">AddRow</a>
    </div>
    <p class="clear"></p>
     </div>
   <div>
										</html:form>

</div>

</body>
</html>