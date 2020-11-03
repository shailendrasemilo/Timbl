<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib uri="/WEB-INF/tlds/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/tlds/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/tlds/displaytag.tld" prefix="display"%>
<%@taglib uri="/WEB-INF/tlds/struts-html.tld" prefix="html"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<div id="section" >
<form action=""  method="post" id="logQRCBarring">
	<div class="section">
	<h2>Log QRC</h2>
	<div class="inner_section ">
		<div class="inner_left_lead floatl  qrcLeft">
		<bean:define id="activeMenu" value="planMigration"></bean:define>
		<%@include file="qrcMenu.jsp"%>
	</div>
<div class=" floatl manageGISRight qrcRight">
	<h4>Add On Plans</h4>
<form action=""  method="post" id="logQRCAddOnPlan">
	<div class=" relative inner_left_lead">
	<div class="floatl">
	<p class="floatl "><strong>Customer ID</strong>
		<input type="text"  class="textbox" readonly="readonly"/>
	</p>
	
	<p class="floatl marginleft30"><strong> Reason </strong>
		<span class="dropdownWithoutJs">
			<select  class="" name="reasonLogQRCAddOnPlan">
			<option value="0">Select Reason </option>
			<option >Gold</option>
			<option >Platinum</option>
			</select>
		</span>
	</p>
	
	<p class="floatl clear"><strong> Remarks</strong>
		<input type="text"  class="LmsRemarkstextarea" name="remarksLogQRCAddOnPlan"/>
	</p>  
		<br class="clear" />
		</div>
		<div class="floatr inner_right">
	 <p class="buttonPlacement">
		<a href="#" id="submit_logQRCAddOnPlan" class="main_button  "><span>Add Plan</span></a>
	</p>
	</div>
	<br class="clear" />
	</div>
</form> 

<h4 class="paddingTop20">Booster Plans</h4>
 <form action=""  method="post" id="logQRCBoosterPlan">
 <div class=" inner_left_lead relative">
	 <div class="floatl">
	<p class=" "><strong> Booster Plan </strong>
		<span class="dropdownWithoutJs">
			 <select  class="" name="boosterLogQRCBoosterPlan">
			 <option value="0">Select Reason </option>
			 <option >Gold</option>
			 <option >Platinum</option>
			 </select>
		</span>
	  </p>
	  <b class="space"> </b>
	  <br class="clear" />
	  </div>
	  
	  <div class="floatr inner_right">
	  <p class="buttonPlacement">
	  <a href="#" id="submit_logQRCBoosterPlan" class="main_button  "><span>Add Plan</span></a>
	  </p>
	 </div>
	 <br class="clear" />
   </div>
  </form>
 <h4>Base Plans</h4>
 <form action=""  method="post" id="logQRCBasePlan">
 <div class="relative inner_left_lead">
  <div class="floatl">
	<p class=" ">
	   <strong> Base Plan </strong>
	    <span class="dropdownWithoutJs">
	    <select  class="" name="baseLogQRCBasePlan">
	    <option value="0">Select Base Plan </option>
	    <option >Gold</option>
	    <option >Platinum</option>
	    </select>
	    </span>
	 </p>
	  <b class="space"> </b>
	  <br class="clear" />
     </div>
    <div class="floatr inner_right">
    <p class="buttonPlacement">
     <a href="#" id="submit_logQRCBasePlan" class="main_button_multiple  "><span>Check Feasibility</span></a>
    </p>
    </div>
   <br class="clear" />
    </div>
   </form> 
    </div>
    <!-- end -->
    <p class="clear"/>
