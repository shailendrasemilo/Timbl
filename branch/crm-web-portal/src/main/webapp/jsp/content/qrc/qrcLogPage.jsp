<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<%@taglib uri="/WEB-INF/tlds/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/tlds/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/tlds/displaytag.tld" prefix="display"%>
<%@taglib uri="/WEB-INF/tlds/struts-html.tld" prefix="html"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<script src="javascript/qrc.js" type="text/javascript"></script>
<title>QRC Log Page</title>
</head>
<body>
<div id="section"  align="center">
<form action="/manageQrc" method="post" id="QRC" >
<html:hidden property="qrcCategoriesPojo.createdBy" value="${sessionScope.userPojo.userId}"/>
<html:hidden property="remarksPojo.createdBy" value="${sessionScope.userPojo.userId}"/>
 <div class="section">
   <h2>Log QRC</h2>
	
   <div class="inner_section">
   <div class="inner_left_lead floatl marginleft10">
	<p>
     <strong>Log Type</strong>
     <LABEL class="label_radio" for="Query">
     	<html:radio name="qrcForm" styleId="Query" property="qrcCategoriesPojo.qrcType" value="Q" onchange="populateConditionalList('category',1,this.value);"> Query</html:radio>
     </LABEL> 
     <LABEL class="label_radio" for="Request">
     	<html:radio name="qrcForm" styleId="Request" property="qrcCategoriesPojo.qrcType" value="R" onchange="populateConditionalList('category',1,this.value);"> Request</html:radio>
     </LABEL> 
     <LABEL class="label_radio" for="Complaint">
     	<html:radio name="qrcForm" styleId="Complaint" property="qrcCategoriesPojo.qrcType" value="C" onchange="populateConditionalList('category',1,this.value);"> Complaint</html:radio>
     </LABEL> 
   </p>
  
    <p id="typePara" class="floatl clear"><strong>Type</strong>
     <span class="dropdownWithoutJs">
     	<html:select name="qrcForm" styleId="category" property="qrcCategoriesPojo.category" onchange="populateConditionalList('subCategory',2,this.value);">
     	</html:select>
   	 </span>
    </p>
     <p id="subTypePara" class="floatl clear"><strong>Sub-Type</strong>
    <span class="dropdownWithoutJs">
     	<html:select name="qrcForm" styleId="subCategory" property="qrcCategoriesPojo.subCategory" onchange="populateConditionalList('subSubCategory',3,this.value);">
     	</html:select>
     </span>
    </p>
    
    <p id="subSubTypePara" class="floatl clear"><strong>Sub-Sub-Type</strong>
    <span class="dropdownWithoutJs">
     	<html:select name="qrcForm" styleId="subSubCategory" property="qrcCategoriesPojo.subSubCategory" onchange="populateResolutionBlock('resolutionBlock',this.value);">
     	</html:select>
     </span>
    </p>
    <p class="floatl clear hide1" id="resolutionType">
	<strong>Resolution Type</strong>
     	<span id="resolutionQuery" class="floatl hide1">
     		<LABEL class="label_radio" for="rQuery">
				<html:radio name="qrcForm" styleId="rQuery"	property="qrcCategoriesPojo.resolutionType"	value="0"> Query</html:radio>
			</LABEL>
		</span>
     	<span id="resolutionForward"  class="floatl hide1">
     		<LABEL class="label_radio" for="forward">
				<html:radio name="qrcForm" styleId="forward" property="qrcCategoriesPojo.resolutionType" value="1"> Forward</html:radio>
			</LABEL>
     	</span>
     	<span id="resolutionRol"  class="floatl hide1">
     		<LABEL class="label_radio" for="rol">
				<html:radio name="qrcForm" styleId="rol" property="qrcCategoriesPojo.resolutionType" value="2"> ROL</html:radio>
			</LABEL>
     	</span>
		
   	</p>
	<p id="showID" class="floatl clear"></p>
   	<p class="clear floatl">
      <strong>Remarks:</strong>
      	<html:textarea cols="27" rows="38" styleClass="LmsRemarkstextarea" property="remarksPojo.remarks" name="qrcForm" styleId="remarks"/>
   </p>
   
    <br class="clear" />
   </div>
   
    <div class="floatr inner_right">
     <a href="javascript:submit();" id="submitQRC" class="main_button">Submit</a>
    </div>
    <p class="clear"></p>
    <!-- Customer Activity Log section starts -->
     <div id="customerActivitylogs">
     <logic:notEmpty name="qrcForm" property="crmCustomerActivityList" >
     <jsp:include page="/jsp/content/masterdata/customerActivityLog.jsp"></jsp:include>
     </logic:notEmpty>
     </div>
      <!-- Customer Activity Log section ends -->
     </div>
   </div>
  <p class="clear"></p>
 </form>
</div>
</body>
</html>