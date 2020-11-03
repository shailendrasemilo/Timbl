<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page import="org.apache.struts.Globals"%> 
<%@ page import="org.apache.struts.taglib.html.Constants"%>
<%@taglib uri="/WEB-INF/tlds/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/tlds/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/tlds/displaytag.tld" prefix="display"%>
<%@taglib uri="/WEB-INF/tlds/struts-html.tld" prefix="html"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<link href="css/colorbox.css" rel="stylesheet" />
<script src="javascript/jquery.colorbox.js"></script>
<script>
	$( document ).ready(function(){
		$(".iframe2").colorbox({iframe:true, width:"750px", height:"320px"});
	});
</script>
<div class="loadingPopup hidden"></div>
<div id="section">
<div class="modelPopupDiv" id="uploadDocPPId"></div>
<div class="section">
  <h2>Shifting Workflow&nbsp;-&nbsp;&nbsp;<a style="color: #DEAE00;"><bean:write name='qrcForm' property='customerId'/></a></h2>
<div class="inner_section ">
<div class="inner_left_lead floatl  qrcLeft">
</div>
<div class=" floatl manageGISRight  qrcRight">
	<div class="success_message">
		<logic:messagesPresent message="true">
			<html:messages id="message" message="true">
				<bean:write name="message" />
			</html:messages>
		</logic:messagesPresent>
	</div>
	<div class="error_message" id="errors">
		<html:errors />
	</div>
	<h4>Network Partner Stage</h4>
	<div class="relative inner_left_lead">
	<html:form action="/shiftingWorkflow" method="post" styleId="npStage">

	<html:hidden name="qrcForm" property="shiftingWorkflowPojo.workflowId" value="${qrcForm.shiftingWorkflowPojo.workflowId}" styleId="workflowId"/>
	<html:hidden name="qrcForm" property="shiftingWorkflowPojo.workflowStage" value="${qrcForm.shiftingWorkflowPojo.workflowStage}" styleId="workflowStage"/>
	<html:hidden name="qrcForm" property="shiftingWorkflowPojo.customerId" value="${qrcForm.shiftingWorkflowPojo.customerId}" styleId="customerId"/>
	<html:hidden name="qrcForm" property="shiftingWorkflowPojo.shiftingType" value="${qrcForm.shiftingWorkflowPojo.shiftingType}" styleId="shiftingType"/>
	<html:hidden name="qrcForm"property="networkConfigurationsPojo.serviceModel" value="${qrcForm.networkConfigurationsPojo.serviceModel}" styleId="IDQRChiddenOntRgMduDone"/>
	<html:hidden name="qrcForm"property="networkConfigurationsPojo.recordId" value="${qrcForm.networkConfigurationsPojo.recordId}" styleId="IDQRChiddenNetworkRecordId"/>
		 <logic:notEmpty name="qrcForm" property="networkConfigurationsPojo">
			<div class="floatl">
        	 <br/><br/>
           	 <h4>
             <logic:equal name="qrcForm" property="custDetailsPojo.product" value="BB"> Change Option 82</logic:equal>
             <logic:notEqual name="qrcForm" property="custDetailsPojo.product" value="BB">Change NAS Port ID</logic:notEqual>
              </h4>     
              <logic:notEqual name="qrcForm" property="custDetailsPojo.product" value="BB">
                <p class="floatl clear">
                  <strong>Master Name</strong>
                  <html:text name="qrcForm" property="partnerNetworkConfigPojo.masterName" styleClass="textbox gray_text"></html:text>
                </p>
                <p class="floatl marginleft30">
                  <strong>Pool Name</strong>
                  <html:text name="qrcForm" property="partnerNetworkConfigPojo.poolName" styleClass="textbox gray_text"></html:text>
                </p>
              </logic:notEqual>
             <p class=" floatl clear">
	             <logic:equal name="qrcForm" property="custDetailsPojo.product" value="BB"><strong>Old Option 82</strong>
	        	 	<html:text property="networkConfigurationsPojo.option82" name="qrcForm" styleClass="textbox gray_text"  styleId="IDqrcOldOption82" readonly="true"/>
	      		</logic:equal>
	      	  	<logic:notEqual name="qrcForm" property="custDetailsPojo.product" value="BB">
	           		<strong>Old NAS Port ID</strong>
	           		<html:text property="partnerNetworkConfigPojo.nasPortId" name="qrcForm" styleClass="textbox gray_text"  styleId="IDqrcOldOption82" readonly="true"/>
	      	    </logic:notEqual>
      	   </p>
      	   <p class="floatl marginleft30">
	           <logic:equal name="qrcForm" property="custDetailsPojo.product" value="BB">
	           <strong>New Option 82</strong>
	           	<html:text name="qrcForm" property="newOption82" styleId="IDqrcNewOption82" value="" styleClass="textbox gray_text" maxlength="50" ></html:text><font></font>
	          </logic:equal>
	      <logic:equal name="crmRoles" property="available(update_qrc_dm)" value="true" scope="session">
	          <logic:notEqual name="qrcForm" property="custDetailsPojo.product" value="BB">       
		          <strong>New NAS Port ID</strong>
			      <html:hidden name="qrcForm" property="newOption82" styleId="IDqrcNewOption82" styleClass="textbox gray_text "  ></html:hidden>
		          <a href= 'manageQrc.do?method=changeDevicePopupPage&npId=<bean:write name='qrcForm' property='custDetailsPojo.npId' /> 'class="iframe2"> 
		          <html:text name="qrcForm" property="showDivValue" styleId="IDqrcNewOption82show" styleClass="textbox gray_text" value="" readonly="true"></html:text><font></font></a>
	          </logic:notEqual>
          </logic:equal>
        </p>
        <p class="floatl clear">
	  		<strong> Remarks <span class="error_message verticalalignTop">*</span></strong>
				<html:textarea property="remarksPojo.remarks" value="" name="qrcForm" styleClass="LmsRemarkstextarea" styleId="npRemarks"></html:textarea>
				<span class="hidden"><font class="errorRemarks" style="top:98px;">Please enter Remarks between [2-4000].</font></span>
					<font class="errorRemarks hidden" style="top:98px;">Please enter Remarks.</font>
		</p>
       </div>
       </logic:notEmpty>
       <logic:empty name="qrcForm" property="networkConfigurationsPojo">
      No network configurations done.
     </logic:empty>
			<div class="floatr inner_right">
			  <p class=" floatl marginleft30"><strong>&nbsp;</strong>
	       <logic:equal name="crmRoles" property="available(update_qrc_dm)" value="true" scope="session">
	       <a href="javascript:saveOption82WORKFLOW('${qrcForm.custDetailsPojo.product}','workflow', '<bean:write name="qrcForm" property="shiftingWorkflowPojo.workflowId"/>','<bean:write name="qrcForm" property="shiftingWorkflowPojo.workflowStage"/>','<bean:write name="qrcForm" property="shiftingWorkflowPojo.customerId"/>','<bean:write name="qrcForm" property="shiftingWorkflowPojo.shiftingType"/>')"  class="main_button" id='submit'>Submit</a>
	       </logic:equal> 
	       <font></font>
       </p>
       <br class="clear" />
       <br class="clear" />
			</div>
			<br class="clear" />
			<br class="clear" />
		</html:form>
		<br class="clear" />
	</div>
</div>
<p class="clear"/>

<div class="LmsTable marginRight10">
	 			 <h4>Remarks Details <span class="lmsMinusImage floatr"></span></h4>
    			 <div class="viewLmsTable margintop10 viewLmsLeadTable">
				<iframe src="logAction.do?method=getRemarks&mappingId=${qrcForm.shiftingWorkflowPojo.workflowId}" scrolling="no" frameborder="0" id="frame"
    	style="border: none; overflow: hidden;width:100%; height: 500px;" allowTransparency="true">
    </iframe>
				</div>
   			 </div>
			 <br class="clear" />
			</div>
   		</div>
   	</div>