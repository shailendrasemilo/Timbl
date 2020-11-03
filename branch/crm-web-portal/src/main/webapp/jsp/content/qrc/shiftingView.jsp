<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="/WEB-INF/tlds/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/tlds/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/tlds/displaytag.tld" prefix="display"%>
<%@taglib uri="/WEB-INF/tlds/struts-html.tld" prefix="html"%> 
<%@taglib uri="/WEB-INF/tlds/struts-logic.tld" prefix="logic"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>QRC Ticket</title>
</head>
<body>
<!----------------- Header -------------------------------> 
<script>header();</script> 

<!----------------- Navigation -------------------------------> 
<script>navigation(2);</script> 

<!----------------- Menu -------------------------------> 
<script>tabmenu(4);</script> 

<!----------------- Section ------------------------------->

<div id="section" >
  <form action=""  method="post" id="searchGIS">
    <div class="section">
        <h2>Shifting Workflow&nbsp;-&nbsp;&nbsp;<a style="color: #DEAE00;"><bean:write name='qrcForm' property='shiftingWorkflowPojo.customerId'/></a></h2>
      <div class="inner_section ">
					<div class="success_message">
						<logic:messagesPresent message="true">
						<html:messages id="message" message="true">
						<bean:write name="message" />
							</html:messages>
						</logic:messagesPresent>
					</div>
					<span class="error_message"> 
					<html:errors property="appError" />
				</span>
          <br />
          <ul class="table display mWidth900 ticketView">
          <logic:notEmpty name="qrcForm" property="shiftingWorkflowPojo" >
            <li class="table_header"> <span>Shifting Workflow View</span> </li>
            
            <li class="table_container">
              
              <label>Customer ID:</label>
              <span>${ empty qrcForm.shiftingWorkflowPojo.customerId ? '-' : qrcForm.shiftingWorkflowPojo.customerId }</span>
				<label>Workflow ID:</label>
              <span >${ qrcForm.shiftingWorkflowPojo.workflowId }</span>			  </li>
              
              
			    <li class="table_container">
              <label>State:</label>
              <span><bean:write name="qrcForm" property="stateName" /></span> 
              <label>City :</label>
              <span><bean:write name="qrcForm" property="cityName" /></span> </li>
              
              <li class="table_container">
              <label>Area:</label>
              <span ><bean:write name="qrcForm" property="areaName" /></span>
              <label>Locality/Society:</label>
              <span><bean:write name="qrcForm" property="societyName" /></span> </li>
			  
			  <li class="table_container">
			<label>House No:</label>
              <span>${empty qrcForm.shiftingWorkflowPojo.houseNumber  ? '-' : qrcForm.shiftingWorkflowPojo.houseNumber}</span>
              <label>Property Details:</label>
              <span>${empty qrcForm.shiftingWorkflowPojo.proprtyType  ? '-' : qrcForm.shiftingWorkflowPojo.proprtyType}</span>
              </li>
              
              
              
            <li class="table_container">
              
			  <label>Current Stage:</label>
              <span>
			  <bean:write name="crmRoles" property="displayEnum(CRMOperationStages,${qrcForm.shiftingWorkflowPojo.workflowStage})" scope="session" />
			  </span>
			  <label>Previous Stage:</label>
              <span><bean:write name="crmRoles" property="displayEnum(CRMOperationStages,${qrcForm.shiftingWorkflowPojo.previousStage})" scope="session" />
			  </span>
              </li>
	<li class="table_container">		   
	<label>Current Bin:</label>
              <span>
        <bean:write name="crmRoles" property="displayEnum(functionalBins,${qrcForm.shiftingWorkflowPojo.currentBin})" scope="session" />
        </span>
		<label>Previous Bin:</label>
              <span><bean:write name="crmRoles" property="displayEnum(functionalBins,${qrcForm.shiftingWorkflowPojo.previousBin})" scope="session" />
			  </span>
      </li>
              <li class="table_container">		   
	<label>Current NetworkType:</label>
              <span>
        <bean:write name="crmRoles" property="displayEnum(Product,${qrcForm.shiftingWorkflowPojo.product})" scope="session" />
        </span>
		<label>Previous NetworkType:</label>
              <span><bean:write name="crmRoles" property="displayEnum(Product,${qrcForm.shiftingWorkflowPojo.previousNetwork})" scope="session" />
			  </span>
      </li>
            <li class="table_container">
              <label>Network Partner:</label>
              <span><bean:write name="crmRoles" property="displayEnum(PartnerPojo,${ qrcForm.shiftingWorkflowPojo.npId})" scope="session" />
			  </span>
              <label>Current Status:</label>
              <span><bean:write name="crmRoles" property="displayEnum(WorkflowStatus,${qrcForm.shiftingWorkflowPojo.status})" scope="session" /></span> </li>
              
             <li class="table_container">
              <label>Created By:</label>
              <span>${ qrcForm.shiftingWorkflowPojo.createdBy }</span>
              <label>Created Date:</label>
              <span><bean:write name="crmRoles" property="toDate(${qrcForm.shiftingWorkflowPojo.createdTime})" scope="session"/></span> </li>
              
            <li class="table_container">
              <label>Last Modified By:</label>
              <span>${empty qrcForm.shiftingWorkflowPojo.lastModifiedBy ? '-' :  qrcForm.shiftingWorkflowPojo.lastModifiedBy }</span>
              <label>Last Modified Date:</label>
              <span><bean:write name="crmRoles" property="toDate(${qrcForm.shiftingWorkflowPojo.lastModifiedTime})" scope="session"/></span> </li>
              
         </logic:notEmpty>
          </ul>
    	 <p class="clear"></p>
        <!-- end -->
        <p class="clear floatl"></p>
      </div>

	<div class="LmsTable marginRight10">
	  <h4>Remarks Details <span class="lmsMinusImage floatr"></span></h4>
     <div class="viewLmsTable margintop10 viewLmsLeadTable">
	<iframe src="logAction.do?method=getRemarks&mappingId=${qrcForm.shiftingWorkflowPojo.workflowId}" scrolling="no" frameborder="0" id="frame"
    	style="border: none; overflow: hidden;width:100%; height: 500px;" allowTransparency="true">
    </iframe>
	</div>
    </div>
    </div>
   
  </form>
</div>
<!----------------- footer -------------------------------> 
<script>footer();</script>
</body>
</html>
