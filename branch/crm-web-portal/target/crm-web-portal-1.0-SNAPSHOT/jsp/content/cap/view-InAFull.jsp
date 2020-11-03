<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="/WEB-INF/tlds/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/tlds/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/tlds/displaytag.tld" prefix="display"%>
<%@taglib uri="/WEB-INF/tlds/struts-html.tld" prefix="html"%>
<head>
	<link href="css/common.css" rel="stylesheet" media="screen" />
	<script src="javascript/jquery.min.js" type="text/javascript"></script>
</head>
<%
Object objFbFTID = session.getAttribute( "FB_FT_ID" );
String fbFtId = null;
if(null != objFbFTID){
    fbFtId  = String.valueOf( objFbFTID);
}
%>
<jsp:include page="/jsp/content/cap/view-InA.jsp"></jsp:include>
<div id="section" class="noPaddingLR">
<div class="section">
 <form action="/crmCap"  method="post" name="crmCapForm">
 <html:hidden property="customerDetailsPojo.recordId" name="crmCapForm" value="${crmCapForm.customerDetailsPojo.recordId}"/>
<logic:equal name="crmCapForm" property="parameter" value="customerProfile">
 <div class="inner_section">
   <div class="inner_left_lead ">
   <logic:notEmpty name="userPojo" property="functionalBin">
	 <logic:match name="userPojo" property="functionalBin" value="<%=fbFtId%>" scope="session">
	 	<html:hidden name="crmCapForm" property="crmUserId" styleId="hiddenCRMUserId" value="${sessionScope.userPojo.userId}"/>
		<html:hidden name="crmCapForm" property="customerDetailsPojo.recordId" value="${crmCapForm.customerDetailsPojo.recordId}" styleId="hiddenRecordId"/>		
			<logic:empty name="crmCapForm" property="customerDetailsPojo.crfReferenceNo"> 
			<logic:notEqual name="crmCapForm" property="customerDetailsPojo.crfStage" value="CN">
		  		<logic:notEqual name="crmCapForm" property="customerDetailsPojo.crfStage" value="OB">
		  			<logic:equal name="crmRoles" property="available(update_ina)" value="true" scope="session">
		    		 <p class="floatl clear"><strong>CAF Reference Number</strong>
		        		 <span class="relative">
		  					<html:text styleClass="textbox" name="crmCapForm" property="customerDetailsPojo.crfReferenceNo" maxlength="45" styleId="crfReferenceId"></html:text>
		  					<font id="errorcrfReferenceNo" class='errorRadio'></font>
		   				</span>		   				 
		  		 		<a href="#" id="saveCRFReferenceId" class="marginleft15 yellow_button">Save</a>						
		  			</p>
		  			</logic:equal>
		   		</logic:notEqual>
			</logic:notEqual>
			</logic:empty>
		
		<%--<logic:equal name="crmRoles" property="available(recover_ina)" value="true" scope="session">
			<logic:notEqual name="crmCapForm" property="customerDetailsPojo.crfStage" value="CN">
		  		<logic:notEqual name="crmCapForm" property="customerDetailsPojo.crfStage" value="OB">
		    		 <p class="floatl clear"><strong>CAF Reference Number</strong>
		        		 <span class="relative">
		  					<html:text styleClass="textbox" name="crmCapForm" property="customerDetailsPojo.crfReferenceNo" maxlength="45" styleId="crfReferenceId"></html:text>
		  					<font id="errorcrfReferenceNo" class='errorRadio'></font>
		   				</span>		   				 
		  		 		<a href="#" id="saveCRFReferenceId" class="marginleft15 yellow_button">Save</a>						
		  			</p>
		   		</logic:notEqual>
			</logic:notEqual>
		</logic:equal> 
		 --%>
	<logic:notEqual name="crmCapForm" property="customerDetailsPojo.product" value="EOC">
	 <logic:empty  name="crmCapForm" property="customerDetailsPojo.isrReferenceNo">
		<logic:notEqual name="crmCapForm" property="customerDetailsPojo.crfStage" value="CN">
			<logic:notEqual name="crmCapForm" property="customerDetailsPojo.crfStage" value="OB">
	  			<logic:notEmpty  name="crmCapForm" property="customerDetailsPojo.activationDate">
	  				<logic:equal name="crmRoles" property="available(update_ina)" value="true" scope="session">
	  				<p class="floatl clear"><strong>ISR Reference No.</strong>
	        			<span class="relative">
	        				<html:text styleClass="textbox" name="crmCapForm" property="customerDetailsPojo.isrReferenceNo" maxlength="45" styleId="isrReferenceId"></html:text>
	        				<font id="errorISRReferenceNo" class='errorRadio'></font>
	        			</span>
	      				<a href="#" id="saveISRReferenceId" class="yellow_button marginleft15">Save</a>
	  				</p>
	  				</logic:equal>
	  			</logic:notEmpty>
			</logic:notEqual>
		</logic:notEqual>
	 </logic:empty>
	 
	<%--<logic:equal name="crmRoles" property="available(recover_ina)" value="true" scope="session">
		<logic:notEqual name="crmCapForm" property="customerDetailsPojo.crfStage" value="CN">
			<logic:notEqual name="crmCapForm" property="customerDetailsPojo.crfStage" value="OB">
	  			<logic:notEmpty  name="crmCapForm" property="customerDetailsPojo.activationDate">
	  				<p class="floatl clear"><strong>ISR Reference No.</strong>
	        			<span class="relative">
	        				<html:text styleClass="textbox" name="crmCapForm" property="customerDetailsPojo.isrReferenceNo" maxlength="45" styleId="isrReferenceId"></html:text>
	        				<font id="errorISRReferenceNo" class='errorRadio'></font>
	        			</span>
	      				<a href="#" id="saveISRReferenceId" class="yellow_button marginleft15">Save</a>
	  				</p>
	  			</logic:notEmpty>
			</logic:notEqual>
		</logic:notEqual>
	 </logic:equal>
	  --%>
	 </logic:notEqual>
	 </logic:match>
   </logic:notEmpty>
	 </div>
	 <logic:notEqual name="crmCapForm" property="customerDetailsPojo.crfStage" value="CN">
		 <logic:notEqual name="crmCapForm" property="customerDetailsPojo.crfStage" value="OB">
			 <div class="inner_left_lead ">
			 <div id="remarksSection" class="hideRemarks"> 	
			   	<p class="floatl clear">
			     <strong> Remarks</strong><span class="relative">
			      <html:hidden name="crmCapForm" property="remarksPojo.createdBy" value="${sessionScope.userPojo.userId}"/>
			       <html:hidden name="crmCapForm" property="remarksPojo.mappingId" value="${crmCapForm.customerDetailsPojo.recordId}"/>       
					 <html:textarea name="crmCapForm" property="remarksPojo.remarks" styleId="remarksInaSave" styleClass="LmsRemarkstextarea" ></html:textarea>
				   <font id="errorRemarksSave" style="top:12px;"></font></span>
				  <html:link href="javascript:saveCrfRemarks()" styleId="remarksInASave" styleClass="yellow_button marginleft15 verticleAlignB">Save</html:link>
			    </p>
		     </div>
		    <br class="clear" />
		  </div>
	  </logic:notEqual>
  </logic:notEqual>
 </div>
 </logic:equal>
</form>
 	<div class="LmsTable">
	 			 <h4>Remarks Details <span class="lmsMinusImage floatr"></span></h4>
    			 <div class="viewLmsTable margintop10 viewLmsLeadTable">
					<iframe src="logAction.do?method=getRemarks&mappingId=${crmCapForm.customerDetailsPojo.recordId}" scrolling="yes" frameborder="0" id="frame"
    				style="border: none; overflow: hidden;width:100%; height: 250px;" allowTransparency="true">
    			</iframe>
				</div>
				<h4>Activity Log Details <span class="lmsMinusImage floatr"></span></h4>
			  <div class="viewLmsTable margintop10">
			 <iframe src="jsp/content/masterdata/displayAuditLog.jsp" frameborder="0" scrolling="yes"
                  style="border: none; overflow: hidden; width: 100%; height:250px;" allowTransparency="true" onload="iframeLoaded(this)"></iframe>
			 </div>
   			 </div>
 	 </div>
 </div>
</body>
</html>