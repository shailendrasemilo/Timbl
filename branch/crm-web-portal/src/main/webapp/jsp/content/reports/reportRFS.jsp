<%@ page language="java" contentType="text/html; charset=ISO-8859-1"  pageEncoding="ISO-8859-1"%>
<%@taglib uri="/WEB-INF/tlds/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/tlds/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/tlds/displaytag.tld" prefix="display"%>
<%@taglib uri="/WEB-INF/tlds/struts-html.tld" prefix="html"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<head>
<script src="javascript/tcal.js" type="text/javascript"></script> 
</head>
<!--  <div class="loadingPopup hidden"></div> -->
<div id="section">
<div class="section">

<div class="inner_section ">
	
<div class="   manageGISRight  qrcRight qrcReports">
	<div class="success_message">
		<logic:messagesPresent message="true">
		<html:messages id="message" message="true">
		<bean:write name="message" />
		</html:messages>
	</logic:messagesPresent>
	</div>
	<div class="error_message" id="error">
		<html:errors />
	</div>
<div class="relative">
<h2>Ready for Sale Report</h2>
<form action="/rfsReportAction" id="IDAdjustmentReport" method="post">
	 <div class="popupSection">
  <!--   <div class="inner_section"> -->
   <div class=" marginleft10  inner_left_lead">
     
   <p  class="floatl clear marginleft15 margintop20"><strong>State</strong>
     <span class="LmsdropdownWithoutJs">
       <html:select name="reportForm" property="state" styleId="feasibleState" onchange="javascript:populateAllCitiesForRFS('feasibleCity',this.value);">
		 <option value="0">All States</option>
		 <logic:notEmpty  name="reportForm" property="statePojoList">
		  <html:optionsCollection name="reportForm" property="statePojoList" label="stateName" value="stateId" />
	  	</logic:notEmpty>
	  </html:select>
     </span>
   </p>
    
  <p  class="floatl marginleft15 margintop20 "><strong>City</strong>
    <span class="LmsdropdownWithoutJs">    
      <html:select name="reportForm" property="city" styleId="feasibleCity" onchange="javascript:populateAllAreaForRFS('feasiblArea',this.value);">
	     <html:option value="0">All Cities</html:option>
	     <logic:notEmpty  name="reportForm" property="cityPojoList">
		  <html:optionsCollection name="reportForm" property="cityPojoList" label="cityName" value="cityId" />
	  	</logic:notEmpty>
	  </html:select>
   </span>
 </p>
  

  <p  class="floatl  clear marginleft15 margintop20"><strong>Area</strong>  
   <span class="LmsdropdownWithoutJs">
        <html:select name="reportForm" property="area" styleId="feasiblArea" onchange="javascript:populateAlllocalityForRFS('societyId', this.value );">
		   <html:option value="0">All Areas</html:option>
		   <logic:notEmpty  name="reportForm" property="areaPojoList">
		  <html:optionsCollection name="reportForm" property="areaPojoList" label="area" value="areaId" />
	  	</logic:notEmpty>
	 </html:select><font id="errorArea"></font>
	
   </span>
   </p>
  
  <p  class="floatl marginleft15 margintop20 "><strong>Locality / Sector - Society</strong>  
   <span class="LmsdropdownWithoutJs">
    	<html:select name="reportForm" property="society" styleId="societyId">
	      <html:option value="0">All Localities</html:option>
	      <logic:notEmpty  name="reportForm" property="societyPojoList">
		  <html:optionsCollection name="reportForm" property="societyPojoList" label="localityName" value="societyId" />
	  	</logic:notEmpty>
	   </html:select><font id="errorSociety"></font>
	   
    </span>
	<span class=" marginleft15"><input type="button" class="go_button" name="reset" value ="Reset" onclick="resetRFSForm();"></span>
  </p>
  
       </div>
  
   <div class="floatr inner_right">
<!--   self.close();-->

     <a href="#" id="submitCheckFeasibility" class="main_button" onclick="submitRfsReport();"><span>Submit</span></a>
   </div>
    
    <br class="clear"/>
</div>

	<div class="floatr inner_right">
		<br class="clear"/>

	</div>
	</form>
	<c:choose>
				<c:when test="${not empty reportForm.rfsPojoList }">
				            <table id="reportDataTable" style="width:100%">
				          <thead>
				            <tr>
				                <td>Primary&nbsp;Pin&nbsp;Codes</td>
							    <td>Secondary&nbsp;Pin&nbsp;Codes</td>
							    <td>State</td>
								<td>City</td>
								<td>Area</td>	
								<td>Locality&nbsp;/&nbsp;Sector</td>
								<td>Society</td>
								<td>Network&nbsp;Partner</td>
								<td>Network&nbsp;Type</td>
								<td>Connectable&nbsp;Homes</td>
								<td>RFS&nbsp;DU's</td>
								<td>Longitude</td>
								<td>Latitude</td>
								<td>Customer&nbsp;Category</td>
								<td>Type&nbsp;Of&nbsp;Field</td>
								<td>Status</td>
				            </tr>
				          </thead>
				          <tbody>
				         <c:forEach items="${reportForm.rfsPojoList }" var="report">
				            <tr>
							    <td>${report.primaryPinCodes}</td>
							    <td>${report.secondaryPinCodes}</td>
								<td>${report.state}</td>
								<td>${report.city}</td>	
								<td>${report.area}</td>
								<td>${report.localitySector}</td>
								<td>${report.society}</td>
								<td>${report.networkPartner}</td>
								<td>${report.networkType}</td>
								<td>${report.connectableHomes}</td>
								<td>${report.rfsDus}</td>
								<td>${report.latitude}</td>
								<td>${report.longitude}</td>
								<td>${report.customerCategory}</td>
								<td>
									<c:if test="${report.typeOfField eq 'B'}">
									Brown
									</c:if>
									<c:if test="${report.typeOfField eq 'G'}">
									Green
									</c:if>
								</td>
								<td>
									<bean:write name="crmRoles" property="displayEnum(PartialStatus,${report.societyStatus})" scope="session" />
								</td>
				            </tr>
				           </c:forEach>
				          </tbody>
				        </table>
				        </c:when>
				<c:otherwise>
				<c:if test="${ empty reportForm.rfsPojoList and param.method ne 'rfsReportPage'}">
				<br class="clear" />
			    No Data
				</c:if>
			   </c:otherwise>
		</c:choose>
	<br class="clear" />
</div>
<p class="clear"/>
</div>
</div>
</div>

