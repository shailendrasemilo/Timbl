<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@taglib uri="/WEB-INF/tlds/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/tlds/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/tlds/displaytag.tld" prefix="display"%>
<%@taglib uri="/WEB-INF/tlds/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/tlds/c-rt.tld" prefix="c"%>
<html xmlns="http://www.w3.org/1999/xhtml">

<body>
<div class="overlayDiv"></div>
<div class="modelPopupDiv" id="isrUploadDocID"></div>
<html:hidden property="customerDetailsPojo.recordId" name="crmCapForm" value="${crmCapForm.customerDetailsPojo.recordId}" styleId="hiddenRecordId"/>
<html:hidden name="crmCapForm" property="crmUserId" styleId="hiddenCRMUserId" value="${ crmCapForm.crmUserId }"/>
<input type="hidden" name="activationDate" value="<bean:write name="crmRoles" property="toDate(${crmCapForm.customerDetailsPojo.activationDate})"/>" id="activationDateID"/>

<div id="section"  align="center">
<form action="/crmCap" id="isrReportsID" name="networkInventoryDetail" method="post">
<div class="section">
   <h2>Installation Satisfaction Report</h2>
   <div class="inner_section">
    <!-- Success Messages Starts -->
	<div class="success_message" >
		<logic:messagesPresent message="true">
   		<html:messages id="message" message="true">
		<bean:write name="message" />
		</html:messages>
		</logic:messagesPresent>
	</div>
<!-- Success Messages Ends -->
	<div class="error_message" id="error"><html:errors /></div> <!-- for error messages -->
	
   <div class="inner_left_lead floatl">
   
   <div class="relative">
	    <div class="clear marginleft10 inner_left_lead floatl">
	    	<h4>Network Partner</h4> 
    		<p class="floatl" ><strong>
           		<logic:notEqual name="crmCapForm" property="customerDetailsPojo.npId" value="0">
      		 		<label>
      		 			<bean:write name="crmRoles" property="displayEnum(PartnerPojo,${crmCapForm.customerDetailsPojo.npId})" scope="session" />
      		 		</label>
       	 		</logic:notEqual>
       	 		<logic:equal name="crmCapForm" property="customerDetailsPojo.npId" value="0">-</logic:equal>
       		</strong></p>
       	</div>
       	<div class="marginleft10 inner_left_lead floatl">
	    	<h4 class="floatl">Service Partner</h4> 
	    		<p class="clear floatl" ><strong>
	           		<logic:notEqual name="crmCapForm" property="customerDetailsPojo.spId" value="0">
	      		 		<label>
	      		 			<bean:write name="crmRoles" property="displayEnum(PartnerPojo,${crmCapForm.customerDetailsPojo.spId})" scope="session" />
	      		 		</label>
	       	 		</logic:notEqual>
	       	 		<logic:equal name="crmCapForm" property="customerDetailsPojo.spId" value="0">-</logic:equal>
	       		</strong></p>
	    </div>
		
		<div class="marginleft10 inner_left_lead floatl">
	    	<h4>Activation Date</h4> 
    		<p class="floatl" ><strong>
           		<logic:notEmpty  name="crmCapForm" property="customerDetailsPojo.activationDate">
				   <label><bean:write name="crmRoles" property="toDate(${crmCapForm.customerDetailsPojo.activationDate})"/></label>
			   </logic:notEmpty>
	           <logic:empty name="crmCapForm" property="customerDetailsPojo.activationDate">-</logic:empty>
       	 	</strong></p>
       	</div>
		 <div class="clear marginleft10 inner_left_lead floatl">
	    	<h4>Primary MAC Address</h4> 
    		<p class="floatl" ><strong>
           		<label>${ empty crmCapForm.crmNetworkConfigurations.currentCpeMacId ? '-' : crmCapForm.crmNetworkConfigurations.currentCpeMacId }</label>
       		</strong></p>
       	</div>
		<div class="marginleft10 inner_left_lead floatl">
	    	<h4 class="floatl">Secondary MAC Address</h4> 
	    		<p class="clear floatl" ><strong>
	           	     <label>${ empty crmCapForm.crmNetworkConfigurations.currentSlaveMacId ? '-' : crmCapForm.crmNetworkConfigurations.currentSlaveMacId }</label>  	 		       	 		
	       		</strong></p>
	    </div>
	
	</div>   
     <p class="floatl clear"><strong>Date on ISR <label>(should not be greater than current date)</label></strong>
     	<html:text styleClass="tcal tcalInput" readonly="true" styleId="ISRDate" name="crmCapForm" property="displayISRDate"></html:text><font></font>
     	<a href="javascript:void(0);" onclick="ISRUploadDocument('${initParam.dmshost }/np-document-upload','INA','${crmCapForm.customerDetailsPojo.crfId}','${crmCapForm.customerDetailsPojo.customerId}');" class="marginleft30 yellow_button">Upload&nbsp;Document</a>
     </p>
   
     <h4 class="paddingTop10 clear">Material Details</h4>
 <div id="materialId">
  <logic:iterate id="materialList" name="crmCapForm" property="materialList"  indexId="ctr" >
       <p class=" floatl ${ctr eq 0 ? '':'marginleft20'}"><strong>${materialList.paramValue}</strong>
			<input type="text" class="Lmstextbox" name="materialList[${ctr}].paramValue" onkeyup="javascript:ValidatenReplaceAlphanumeric(this)" id="outer_${ctr}" maxlength="15">${displayValues}</input>
         	<!--<html:text styleClass="Lmstextbox" name="crmCapForm" property="materialList.paramValue" value="" maxlength="10" styleId="${materialList.paramValue}_${ctr}" indexed = "true"></html:text>-->
         	<font class="materialDetails_msg errorTextbox"></font>
	  </p>   
   </logic:iterate>
   </div>
    
     <h4 class="clear paddingTop10">Customer Feedback</h4>
  <div id="customerFeedBackId">
    <logic:iterate id="customerFeedBackList" name="crmCapForm" property="customerFeedBackList"  indexId="ctr" >
      
     <p class=" floatl clear"><strong>${customerFeedBackList.paramValue}</strong>
     
     	<logic:iterate id="displayValues" name="customerFeedBackList" property="displayValues">
			<logic:equal name="customerFeedBackList" property="paramType" value="Radio">
				<input type="radio" name="customerFeedBackList[${ctr}].paramValue" value="${displayValues}" id="customerFeedBack_${ctr}">${displayValues}</input>
			</logic:equal>
			<logic:equal name="customerFeedBackList" property="paramType" value="Textarea">
			  <br/>
				<textarea class="LmsRemarkstextarea" name="customerFeedBackList[${ctr}].paramValue" id="remarks_${ctr}">${displayValues}</textarea>
			</logic:equal>
					
		 </logic:iterate>
		 <font class="feedback_msg errorText" style="color:red"></font>
   	</p>   
   </logic:iterate>
 </div>
   <br class="clear" />
   <p class="floatl clear paddingTop10"><strong>ISR Reference No.</strong>
        <span class="relative">
        	<html:text styleClass="textbox" name="crmCapForm" property="customerDetailsPojo.isrReferenceNo" maxlength="45" styleId="isrReferenceId"></html:text><font id="errorISRReferenceNo" class='errorRadio'></font>
        </span>
      <a href="#" id="saveISRReferenceId" class="yellow_button marginleft15">Save</a>
      
  </p>
  
       </div>
   <div class="floatr inner_right">
     <a href="#" id="submit_ISRReports" class="main_button"><span>Submit</span></a>
   </div>
    
    <br class="clear"/>
 </div>
	<!-- include table -->
	<div id="contentDiv"></div>
	<!-- include table -->
	<div class="LmsTable">
		<h4>
			Customer Details <span class="lmsMinusImage floatr"></span>
		</h4>
		<div class="viewLmsTable margintop10 viewLmsLeadTable">
			<jsp:include page="/jsp/content/cap/view-InA.jsp"></jsp:include>
		</div>
	</div>
	<div class="LmsTable">
		<h4>
			Remarks Details <span class="lmsMinusImage floatr"></span>
		</h4>
		<div class="viewLmsTable margintop10 viewLmsLeadTable">
			<iframe
				src="logAction.do?method=getRemarks&mappingId=${crmCapForm.customerDetailsPojo.recordId}"
				scrolling="no" frameborder="0" id="frame"
				style="border: none; overflow: hidden; width: 100%; height: 500px;"
				allowTransparency="true"> </iframe>
		</div>
		<h4>Activity Log Details <span class="lmsMinusImage floatr"></span></h4>
		 <div class="viewLmsTable margintop10">
		 <iframe src="jsp/content/masterdata/displayAuditLog.jsp" frameborder="0" scrolling="yes"
              style="border: none; overflow: hidden; width: 100%; height:250px;" allowTransparency="true" onload="iframeLoaded(this)"></iframe>
		 </div>
	</div>
</div>
 </form>
</div>
</body>
</html>
