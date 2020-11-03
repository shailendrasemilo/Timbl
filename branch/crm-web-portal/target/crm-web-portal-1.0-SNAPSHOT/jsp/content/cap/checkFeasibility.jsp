<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<%@taglib uri="/WEB-INF/tlds/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/tlds/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/tlds/displaytag.tld" prefix="display"%>
<%@taglib uri="/WEB-INF/tlds/struts-html.tld" prefix="html"%>
<html>
<head>
<link href="css/common.css" rel="stylesheet" media="screen" />
<link href="css/color-${initParam.client }.css" rel="stylesheet" media="screen" />
<link href="css/lms.css" rel="stylesheet" media="screen" />
<link href="css/userManagement.css" rel="stylesheet" media="screen" />
<link href="css/ina.css" rel="stylesheet" media="screen" />
<link href="css/masterdata.css" rel="stylesheet" media="screen" />
<script src="javascript/jquery.min.js" type="text/javascript"></script>
<script src="javascript/jquery.validate.js" type="text/javascript"></script>
<script src="dwr/engine.js" type="text/javascript"></script>
<script src="dwr/util.js" type="text/javascript"></script>
<script src="dwr/interface/crmDwr.js" type="text/javascript"></script> 
<script src="javascript/common.js" type="text/javascript"></script>
<script src="javascript/masterdata.js" type="text/javascript"></script>
<script src="javascript/ina.js" type="text/javascript"></script>
<script src="javascript/checkFeasibility.js" type="text/javascript"></script>

</head>
<body>
<html:hidden property="hiddenProductType"  name="crmCapForm" value="${crmCapForm.hiddenProductType}" styleId="hiddenProduct"/>
<div id="popupSection" >
<form action="/crmCap" id="checkFeasibility" name="checkFeasibility" method="post">
 <div class="popupSection">
   <h2>Check Feasibility </h2>
   <div class="inner_section">
   <div class="INA floatl">
     
   <p  class="floatl "><strong>State</strong>
     <span class="LmsdropdownWithoutJs">
       <html:select name="crmCapForm" property="state" styleId="feasibleState" onchange="javascript:populateCityByState('feasibleCity',this.value);">
		 <option value="0">Please Select</option>
		 <logic:notEmpty  name="crmCapForm" property="statePojoList">
		  <html:optionsCollection name="crmCapForm" property="statePojoList" label="stateName" value="stateName" />
	  	</logic:notEmpty>
	  </html:select>
     </span>
   </p>
    
  <p  class="floatl marginleft30"><strong>City</strong>
    <span class="LmsdropdownWithoutJs">    
      <html:select name="crmCapForm" property="city" styleId="feasibleCity" onchange="javascript:populateAreaByCity('feasiblArea','feasibleState',this.value);">
	     <html:option value="0">Please Select</html:option>
	     <logic:notEmpty  name="crmCapForm" property="cityList">
		  <html:optionsCollection name="crmCapForm" property="cityList" label="cityName" value="cityName" />
	  	</logic:notEmpty>
	  </html:select>
   </span>
 </p>
   
  <p  class="floatl clear"><strong>Area</strong>  
   <span class="LmsdropdownWithoutJs">
        <html:select name="crmCapForm" property="area" styleId="feasiblArea" onchange="javascript:populateFeasibleSocieties('feasibleSociety','feasibleState','feasibleCity','feasiblArea','hiddenProduct');">
		   <html:option value="0">Please Select</html:option>
		   <logic:notEmpty  name="crmCapForm" property="areaList">
		  <html:optionsCollection name="crmCapForm" property="areaList" label="area" value="area" />
	  	</logic:notEmpty>
	 </html:select><font id="errorArea"></font>
	
   </span>
   </p>
  
  <p  class="floatl marginleft30"><strong>Locality / Sector - Society</strong>  
   <span class="LmsdropdownWithoutJs">
    	<html:select name="crmCapForm" property="feasiblelocSoc" styleId="feasibleSociety">
	      <html:option value="0">Please Select</html:option>
	      <logic:notEmpty  name="crmCapForm" property="societyList">
		  <html:optionsCollection name="crmCapForm" property="societyList" label="searchName" value="searchName" />
	  	</logic:notEmpty>
	   </html:select><font id="errorSociety"></font>
	   
    </span>
  </p>
       </div>
  
   <div class="floatr inner_right">
<!--   self.close();-->
     <a href="#" id="submitCheckFeasibility" class="main_button"><span>Submit</span></a>
   </div>
    
    <br class="clear"/>
</div>

 </div>
 
 </form>
</div>

</body>
</html>
