<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="/WEB-INF/tlds/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/tlds/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/tlds/displaytag.tld" prefix="display"%>
<%@taglib uri="/WEB-INF/tlds/struts-html.tld" prefix="html"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<body>
<div id="section"  align="center">
<html:form  action="/crmCap"  method="post" styleId="crfDetails">

<html:hidden property="crmNetworkConfigurations.serviceType" name="crmCapForm" value="${crmCapForm.crmNetworkConfigurations.serviceType}" styleId="hiddenServiceType"/>
<html:hidden property="crmNetworkConfigurations.vlanId" name="crmCapForm" value="${crmCapForm.crmNetworkConfigurations.vlanId}" styleId="hiddenVlanId"/>
<html:hidden property="crmNetworkConfigurations.oltNoteId" name="crmCapForm" value="${crmCapForm.crmNetworkConfigurations.oltNoteId}" styleId="hiddenOltNoteId"/>
<html:hidden property="crmNetworkConfigurations.oltSlot" name="crmCapForm" value="${crmCapForm.crmNetworkConfigurations.oltSlot}" styleId="hiddenOltSlot"/>
<html:hidden property="crmNetworkConfigurations.oltPort" name="crmCapForm" value="${crmCapForm.crmNetworkConfigurations.oltPort}" styleId="hiddenOltPort"/>
<html:hidden property="crmNetworkConfigurations.oltSubPort" name="crmCapForm" value="${crmCapForm.crmNetworkConfigurations.oltSubPort}" styleId="hiddenOltSubPort"/>
<html:hidden property="crmNetworkConfigurations.currentCpeMacId" name="crmCapForm" value="${crmCapForm.crmNetworkConfigurations.currentCpeMacId}"/>
<html:hidden property="crmNetworkConfigurations.currentSlaveMacId" name="crmCapForm" value="${crmCapForm.crmNetworkConfigurations.currentSlaveMacId}"/>
<html:hidden property="crmNetworkConfigurations.firstCpeMacId" name="crmCapForm" value="${crmCapForm.crmNetworkConfigurations.firstCpeMacId}"/>
<html:hidden property="crmNetworkConfigurations.firstSlaveMacId" name="crmCapForm" value="${crmCapForm.crmNetworkConfigurations.firstSlaveMacId}"/>
<html:hidden property="crmNetworkConfigurations.macBind" name="crmCapForm" value="${crmCapForm.crmNetworkConfigurations.macBind}"/>
<html:hidden property="crmNetworkConfigurations.recordId" name="crmCapForm" value="${crmCapForm.crmNetworkConfigurations.recordId}"/>
<html:hidden property="forward" name="crmCapForm" value="${crmCapForm.forward}" styleId="forwardId"/>
<html:hidden property="customerDetailsPojo.serviceType" name="crmCapForm" value="${crmCapForm.customerDetailsPojo.serviceType}" styleId="hiddenCustServiceType"/>
<html:hidden property="customerDetailsPojo.product" name="crmCapForm" value="${crmCapForm.customerDetailsPojo.product}" styleId="hiddenProduct"/>
<html:hidden property="oltType" name="crmCapForm" value="${crmCapForm.oltType}" styleId="hiddenOltType"/>
<html:hidden property="customerDetailsPojo.npId" name="crmCapForm" value="${crmCapForm.customerDetailsPojo.npId}" styleId="hiddenPartnerId"/>
<html:hidden name="crmCapForm"  property="crmNetworkConfigurations.option82" value="${crmCapForm.crmNetworkConfigurations.option82}" styleId="option82Id"/>
<html:hidden name="crmCapForm" property="customerDetailsPojo.customerId" value="${crmCapForm.customerDetailsPojo.customerId}" />
<html:hidden name="crmCapForm" property="customerDetailsPojo.crfId" value="${crmCapForm.customerDetailsPojo.crfId}" styleId="hiddenCAFID" />
 <div class="section">
   <h2>Network Details </h2>
   <div class="inner_section">
	<div class="error_message" id="error"><html:errors /></div> <!-- for error messages -->
	
<div class="clear relative">
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
    	<logic:equal property="customerDetailsPojo.product" name="crmCapForm" value="BB">
    		<h4>Network Details</h4>    			
    		<p class="floatl" ><strong>Option82</strong>
     			<html:text  name="crmCapForm" styleClass="Lmstextbox" property="crmNetworkConfigurations.option82" styleId="IDoption82" maxlength="100" /><font></font>
                <html:hidden property="crmNetworkConfigurations.serviceModel" name="crmCapForm" value="ftth" styleId="hiddenOntRgMduDone"/>
   		 	</p>
    	</logic:equal>
   		<logic:notEqual property="customerDetailsPojo.product" name="crmCapForm" value="BB">
    		<h4 class="clear">Master Details</h4>
    		<p class="floatl" ><strong>NAS Port ID</strong>
     			<html:text  name="crmCapForm" styleClass="Lmstextbox" property="nasportID" styleId="IDnasportID" readonly="true"/><font></font>
                <html:hidden property="crmNetworkConfigurations.serviceModel" name="crmCapForm" value="fttn" styleId="hiddenOntRgMduDone"/>
   		 	</p>
   		 <p class="marginleft30 floatl paddingTop10">
			<br/>
	    	<a href="javascript:checkNetworkConfigDetails();" class="link">Click here for Network Inventory Detail form</a><font id="errorNetworkConfig"></font>
   		</p>
    	</logic:notEqual>
	</div>
</div>
	
	
    <!-- validation form enteries -->
    <div class="clear relative">
    <div class="clear marginleft10 inner_left_lead floatl">
    <h4>Actions</h4>
    <% boolean showValue = false; %>
   <logic:equal value="EOC" name="crmCapForm" property="customerDetailsPojo.product">
		<% showValue = true; %>
	</logic:equal>
	<logic:equal value="RF" name="crmCapForm" property="customerDetailsPojo.product">
		<% showValue = true; %>
	</logic:equal>
    <logic:notEqual name="crmCapForm" property="customerDetailsPojo.serviceType" value="PR">
       <p class="clear  floatl"><strong>ONT / RG / MDU Port configuration done ?</strong>
     <LABEL class="label_radio" for="portConfigurationYes">
     	<html:radio name="crmCapForm" property="crmNetworkConfigurations.ontRgMduDone" styleId="portConfigurationYes"  value="true"> Yes</html:radio>
     </LABEL> 
     <LABEL class="label_radio" for="portConfigurationNo">
     	<html:radio name="crmCapForm" property="crmNetworkConfigurations.ontRgMduDone" styleId="portConfigurationNo"  value="false"> No</html:radio>
     </LABEL>
     <font></font>
   </p>
   </logic:notEqual>
   <%-- <p class="<%= showValue ? "hide1" : "" %> floatl " ><strong>Radius Customer ID</strong>
        <html:text  name="crmCapForm" styleClass="Lmstextbox" property="crmNetworkConfigurations.radiusCustomerId" maxlength="45"/><font></font>
   </p> --%>
   
	<p class="clear floatl ">
  		 <strong>Assign To Service Partner</strong>  
   		 	<span class="LmsdropdownWithoutJs">
    	   	   <html:select property="customerDetailsPojo.spId" name="crmCapForm" styleId="spMappingId">
              	  <html:option value="">Please Select</html:option>
              	  <html:optionsCollection name="crmCapForm" property="crmMappingList"  label="partnerBySpId.partnerName" value="partnerBySpId.partnerId" />
          	  </html:select>
            </span> <font></font>
   	</p>
    <logic:notEqual name="crmCapForm" property="customerDetailsPojo.serviceType" value="PR">
      <p class="floatl marginleft30"><strong>Generate Installation Satisfaction Report (ISR)</strong>
     			<a href="#" id="idGenerateISRPdf" class="yellow_button">Generate & Print ISR</a>
      </p>
    </logic:notEqual>
 <span class="clear paddingTop10 floatl relative"><strong>Action Type</strong>
 	<span class="dropdownWithoutJs">
     <html:select property="remarksPojo.actions" name="crmCapForm" styleId="NPActionTypeId">
             <html:option value="">Select Action</html:option>
             <html:option value="Reject By NP">Reject</html:option>
             <html:option value="Approve">Approve</html:option>
             <html:option value="submitRefusal">SRP</html:option>
             <html:option value="Change Feasible Address">Change Feasible Address</html:option>
              <html:option value="ERP">ERP</html:option>
             <html:option value="Cancellation">Cancel CAF</html:option>
         </html:select>   
    </span>
	 <font></font>
 </span>
 
 <div id="npCancellationDivIdComment" class="hide1">
   <p class="floatl marginleft30 relative"><strong>Reason for Cancellation</strong>
 	<span class="LmsdropdownWithoutJs">
      <html:select property="remarksPojo.reasonId" name="crmCapForm" styleId="cancelForReasonId"  onchange="return widthChangeBR(this.value);">
             <html:option value="">Select Reason</html:option>
             <logic:notEmpty  name="crmCapForm" property="cancellationReasonList">
		          <html:optionsCollection name="crmCapForm" property="cancellationReasonList" label="categoryValue" value="categoryId" />
		    </logic:notEmpty>
         </html:select>   
    </span>
	 <font></font>
   </p>
</div> 
 
 <div id="DIVrejectionReason" class="hide1">
 	<p class=" floatl marginleft30">
 		<strong>Reason for Rejection</strong>
 	 <span class="dropdownWithoutJs">    	 
            <html:select name="crmCapForm" property="remarksPojo.reasonId" styleId="reasonForRejection">
				<html:option value="0">Please Select</html:option>
				<logic:notEmpty name="crmCapForm" property="rejectionReasonList" >
					<html:optionsCollection name="crmCapForm" property="rejectionReasonList" label="categoryValue" value="categoryId"/>
				</logic:notEmpty>
			</html:select>		
     	</span>
     	<font></font>
     	</p>
 </div>
 <div id="DiverpComment" class="hide1">
   <p class="floatl marginleft30 relative"><strong>Reason for ERP</strong>
 	<span class="LmsdropdownWithoutJs">
      <html:select property="remarksPojo.reasonId" name="crmCapForm" styleId="erpForReason"  onchange="return widthChangeBR(this.value);">
             <html:option value="">Select Reason</html:option>
             <logic:notEmpty  name="crmCapForm" property="erpReasonList">
		          <html:optionsCollection name="crmCapForm" property="erpReasonList" label="categoryValue" value="categoryId" />
		    </logic:notEmpty>
         </html:select>   
    </span>
	 <font></font>
   </p>
</div> 
 
  
  <div id="yesCustomerRefuseReason" class="hide1"> 
   <p class="clear floatl">
   <strong>Reason for Refusal</strong>  
    <span class="LmsdropdownWithoutJs">
    	  <html:select property="remarksPojo.reasonId" name="crmCapForm" styleId="reasonForRefusalId">
               <html:option value="">Select Reason </html:option>
                <logic:notEmpty  name="crmCapForm" property="refuselReasonList">
               		<html:optionsCollection name="crmCapForm" property="refuselReasonList" label="categoryValue" value="categoryId" />
               </logic:notEmpty>
           </html:select>
        </span> 
        <font></font>
   </p>   
    
   <%-- <div class="clear createBorder" id="cdcfFormId">
    	<h4>Upload CDCF Form</h4>
        <c:url value="${initParam.dmshost }/np-document-upload/files/upload/INA/${ crmCapForm.customerDetailsPojo.crfId }" var="dmsUrl"></c:url>
        http://10.20.0.12:8080/suncity/JSPs/AddDoc_order.jsp?CAFNO=${ crmCapForm.customerDetailsPojo.crfId }&CustomerID=${ crmCapForm.customerDetailsPojo.customerId }&CustomerName=${ crmCapForm.customerDetailsPojo.custFname }+${ crmCapForm.customerDetailsPojo.custLname }&RMN=${ crmCapForm.customerDetailsPojo.rmn }&RTN=${ crmCapForm.customerDetailsPojo.rtn }    	
    	<iframe src="${dmsUrl }"
    	scrolling="yes" frameborder="0"
    	style="border: none; overflow: hidden; width: 755px; height: 400px;" allowTransparency="true"></iframe>
    </div> --%>
  </div>
  <br class="clear"/>
   <div id="NPRefusalDivIdComment" class="hide1">
	   <!-- <p class="clear floatl"><strong> Comments</strong>
	      <html:textarea styleClass="LmsRemarkstextarea" name="crmCapForm" property="remarksPojo.remarks" styleId="commentId"></html:textarea>
	      <font></font>
	   </p> --> 
  </div>
  	<p class="clear paddingTop10"> <strong> Remarks<span class="error_message"></span></strong>
      <html:textarea  styleClass="LmsRemarkstextarea" name="crmCapForm" property="remarksPojo.remarks" styleId="aproveNetworkRemarks"></html:textarea><font></font>
    </p> 
      </div>
      <div class="floatr inner_right">
    
    <p class="buttonPlacement">
  <%-- <logic:equal name="crmRoles" property="available(create_ina,update_ina)" value="true" scope="session">
       <a href="javascript:;" id="backroute_crfDetails" class="main_button_multiple "><span>Back Route</span></a>
    </logic:equal> 
 --%>
    <logic:equal name="crmRoles" property="available(create_ina,update_ina)" value="true" scope="session">
       <a href="javascript:;" id="submit_crfDetails" class="main_button marginleft10"><span>Submit</span></a>
     </logic:equal>
     
     </p>
    </div>
       <br class="clear" />
    </div>
       <!-- validation form enteries -->
    <!-- include table -->
    <div id="contentDiv"></div>
<!-- include table -->
      </div>
	<div class="LmsTable">
	 	<h4>Customer Details: <span class="lmsMinusImage floatr"></span></h4>
	     <div class="viewLmsTable margintop10 viewLmsLeadTable">
	   	 	<jsp:include page="/jsp/content/cap/view-InA.jsp"></jsp:include>
		</div>
	</div>
		<div class="LmsTable">
	 			 <h4>Remarks Details <span class="lmsMinusImage floatr"></span></h4>
    			 <div class="viewLmsTable margintop10 viewLmsLeadTable">
					<iframe src="logAction.do?method=getRemarks&mappingId=${crmCapForm.customerDetailsPojo.recordId}" scrolling="no" frameborder="0" id="frame"
    				style="border: none; overflow: hidden;width:100%; height: 500px;" allowTransparency="true">
    			</iframe>
				</div>
				<h4>
			   Activity Log Details <span class="lmsMinusImage floatr"></span>
			  </h4>
				 <div class="viewLmsTable margintop10 viewLmsLeadTable">
		 <iframe src="jsp/content/masterdata/displayAuditLog.jsp" frameborder="0" scrolling="yes"
              style="border: none; overflow: hidden; width: 100%; height:250px;" allowTransparency="true" onload="iframeLoaded(this)"></iframe>
		 </div>
				
   			 </div>
    </div>
 </html:form>
</div>
</body>
</html>


