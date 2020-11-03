<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<%@taglib uri="/WEB-INF/tlds/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/tlds/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/tlds/displaytag.tld" prefix="display"%>
<%@taglib uri="/WEB-INF/tlds/struts-html.tld" prefix="html"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<link href="css/common.css" rel="stylesheet" media="screen" />
<link href="css/color-${initParam.client }.css" rel="stylesheet" media="screen" />
<link href="css/userManagement.css" rel="stylesheet" media="screen" />
<link href="css/ina.css" rel="stylesheet" media="screen" />
<link href="css/lms.css" rel="stylesheet" media="screen" /> 
<link href="css/masterdata.css" rel="stylesheet" media="screen" />
<link href="css/tcal.css"  rel="stylesheet" type="text/css" />

<script src="javascript/jquery.min.js" type="text/javascript"></script>
<script src="javascript/jquery.validate.js" type="text/javascript"></script>
<script src="javascript/npCrf.js" type="text/javascript"></script>
<script src="javascript/common.js" type="text/javascript"></script>
<script src="javascript/tcal.js" type="text/javascript"></script> 
<script src="javascript/paymentTracking.js" type="text/javascript"></script> 
</head>
<body>
<div id="popupSection" >
<html:form action="/paymentTracking" styleId="IDnotRealizedPopUpForm"  method="post">
<html:hidden name="financeForm" property="realizationVariable" styleId="PTreliazationVariable"/>
<html:hidden name="financeForm" property="paymentId" styleId="PTpaymentId"/>
 <div class="popupSection">
	   <h2>Check Bouncing Details </h2>
	<div class="inner_section">
	   <div class="inner_left_lead floatl">
			 <p class="floatl "><strong> Bouncing Date</strong>
		    	<html:text styleClass="tcal tcalInput" name="financeForm" property="bouncingDate" styleId="IDBouncingDate"></html:text>
		    </p>
		  
		     <p  class="floatl marginleft30">
			    <strong>Bouncing Reason</strong>
			     <span class="LmsdropdownWithoutJs">
			     	<html:select name="financeForm" property="bouncingReason"  styleId="bouncingReason">
			             <html:option value="">Please Select</html:option>
						 <logic:notEmpty name="financeForm" property="chequeRejectionReasonList">
			            	 <html:optionsCollection name="financeForm" property="chequeRejectionReasonList" label="qrcSubSubCategory" value="qrcSubSubCategory"/>
						 </logic:notEmpty>
			         </html:select>
			     </span>
		     </p>
	    </div>
	    <br class="clear" /> 
	   <div class="floatr inner_right" style="z-index: 2;">
	     <a href="#" id="PRsubmitNotRealized" class="main_button"><span>Submit</span></a>
	   </div>
	   <p style="height: 100px" ></p>
	    <br class="clear"/>
	</div>
	
 </div>
 </html:form>
</div>
</body>
</html>
