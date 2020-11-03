<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="/WEB-INF/tlds/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/tlds/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/tlds/displaytag.tld" prefix="display"%>
<%@taglib uri="/WEB-INF/tlds/struts-html.tld" prefix="html"%>

<div id="section"  align="center">

 <div class="section">


   		<h2>Created Lead Details</h2>
		
   		
   <div class="inner_section">
   <div class="createUserLeft  marginleft10 successLead">
   <div class="paddingBottom30">
   <p class="floatl"><strong>Generated Lead ID:</strong>
     		<logic:notEmpty name="lmsForm" property="lmsPojo.leadId">
         			<font class=""><bean:write name="lmsForm" property="lmsPojo.leadId" /></font>
         		</logic:notEmpty>
         		<logic:empty name="lmsForm" property="lmsPojo.leadId">-</logic:empty>
       		
    </p>
	<p  class="floatl marginleft60"><strong>Service Name:</strong>

			<logic:notEmpty name="lmsForm" property="lmsPojo.product">
					<bean:write name="crmRoles" property="displayEnum(Product,${lmsForm.lmsPojo.product})" scope="session" />
			</logic:notEmpty>
      	  <logic:empty name="lmsForm" property="lmsPojo.product">-</logic:empty>
    </p>
<br class="paddingTop10"/>
    </div>
    <div class=" paddingBottom30">
    <h4>Customer Details</h4>
    
     <p class="floatl marginleft10 pa" ><strong>Customer Name:</strong>
     	<logic:notEmpty name="lmsForm" property="lmsPojo.customerName">
         			<font class=""><bean:write name="lmsForm" property="lmsPojo.customerName" /></font>
         		</logic:notEmpty>
         		<logic:empty name="lmsForm" property="lmsPojo.customerName">-</logic:empty>
       		
    </p>
	
    <p  class="floatl marginleft60"><strong>Mobile Number:</strong>
    			${ lmsForm.lmsPojo.contactNumber > 0 ? lmsForm.lmsPojo.contactNumber : '-' }
    </p>
    
    <p class="floatl clear"><strong>Calling Number:</strong>
     			${ lmsForm.lmsPojo.ctiNumber > 0 ? lmsForm.lmsPojo.ctiNumber : '-' }
    </p>
   
    <p  class="floatl marginleft60"><strong>Email:</strong>
    	<logic:notEmpty name="lmsForm" property="lmsPojo.emailId">
         			<font class=""><bean:write name="lmsForm" property="lmsPojo.emailId" /></font>
         		</logic:notEmpty>
         		<logic:empty name="lmsForm" property="lmsPojo.emailId">-</logic:empty>
    </p>
  <br class="paddingTop20"/>
    </div>
    
     <div class="Address clear paddingBottom30 paddingTop10">
    	<h4>Customer Address Details</h4>
    	<p  class="floatl ">
    		<strong>State:</strong>
    		<logic:notEmpty name="lmsForm" property="lmsPojo.state">
         			<font class=""><bean:write name="lmsForm"property="lmsPojo.state" /></font>
         		</logic:notEmpty>
         		<logic:empty name="lmsForm" property="lmsPojo.state">-</logic:empty>
    	</p>
     	<p  class="floatl marginleft60"><strong>City:</strong>
     		<logic:notEmpty name="lmsForm" property="lmsPojo.city">
         			<font class=""><bean:write name="lmsForm"property="lmsPojo.city" /></font>
         		</logic:notEmpty>
         		<logic:empty name="lmsForm" property="lmsPojo.city">-</logic:empty>
    	</p>
   
     	<p  class="floatl clear">
     		<strong>Area:</strong>
     			<logic:notEmpty name="lmsForm" property="lmsPojo.area">
         			<font class=""><bean:write name="lmsForm"property="lmsPojo.area" /></font>
         		</logic:notEmpty>
         		<logic:empty name="lmsForm" property="lmsPojo.area">-</logic:empty>
     	</p>
    	<p  class="floatl marginleft60">
    		<strong>Locality / Sector - Society:</strong>
              ${empty lmsForm.lmsPojo.locality ? '-' : lmsForm.lmsPojo.locality }
    			<%-- <logic:notEmpty name="lmsForm" property="lmsPojo.locality">
         			<font class=""><bean:write name="lmsForm"property="lmsPojo.locality" /></font>
         		</logic:notEmpty>
         		<logic:empty name="lmsForm" property="lmsPojo.locality">-</logic:empty> --%>
    	</p>
    	<%-- <p  class="floatl clear">
    		<strong>Society:</strong>
    			<logic:notEmpty name="lmsForm" property="lmsPojo.society">
         			<font class=""><bean:write name="lmsForm"property="lmsPojo.society" /></font>
         		</logic:notEmpty>
         		<logic:empty name="lmsForm" property="lmsPojo.society">-</logic:empty>
    	</p> --%>
     	<p  class="floatl clear">
     		<strong>House Number:</strong>
     			<logic:notEmpty name="lmsForm" property="lmsPojo.houseNumber">
         			<font class=""><bean:write name="lmsForm"property="lmsPojo.houseNumber" /></font>
         		</logic:notEmpty>
         		<logic:empty name="lmsForm" property="lmsPojo.houseNumber">-</logic:empty>
     	</p>
    	<p  class="floatl marginleft60">
    		<strong>Landmark:</strong>
    			<logic:notEmpty name="lmsForm" property="lmsPojo.landmark">
         			<font class=""><bean:write name="lmsForm"property="lmsPojo.landmark" /></font>
         		</logic:notEmpty>
         		<logic:empty name="lmsForm" property="lmsPojo.landmark">-</logic:empty>
    	</p>
      	<p  class="floatl clear">
      		<strong>Pin Code:</strong>
      			${ lmsForm.lmsPojo.pincode > 0 ? lmsForm.lmsPojo.pincode : '-' }
      	</p>
      	
    
    </div>
	
   
  
<!-- Lead related additional data Starts
    <div class="LmsTable">
     <h4>Appointment Details: <span class="lmsMinusImage floatr"></span></h4>
     <div class="viewLmsTable margintop10">
      <display:table id="leadData" name="sessionScope.inboxForm.appointmentPojos" class="dataTable" style="width:100%"  requestURI="">
						<display:setProperty name="paging.banner.placement"	value="bottom" />
						<display:column title="Sr.No" style="width:3%;" maxLength="3"><bean:write name="leadData_rowNum" /></display:column>
						<display:column title="Appointment Date" property="displayDate" style="width:15%;"></display:column>
						<display:column title="Appointment Time" style="width:15%;">
						<logic:iterate id="meetingTime" name="lmsForm" property="appointmentTimings" indexId="ctr">
    							<logic:equal name="leadData" property="appointmentTime" value="${meetingTime.contentValue}">${meetingTime.contentName}</logic:equal>
      						</logic:iterate>
						</display:column>
						<display:column title="Mode Of Contact" style="width:10%;">
						<logic:iterate id="contactMode" name="lmsForm" property="appointmentModes" indexId="ctr">
    							<logic:equal name="leadData" property="modeOfContact" value="${contactMode.contentValue}">${contactMode.contentName}</logic:equal>
      						</logic:iterate>
						</display:column>
						<display:column title="Contact No." style="width:10%;" property="contactNumber"></display:column>
						<display:column title="Appointment Address" property="appointmentAddress" style="width:15%;"></display:column>
						<display:column title="Created By" style="width:10%;" property="createdBy"></display:column>
						<display:column title="Created Time" style="width:15%;" property="displayCreatedTime"></display:column>
						<display:column title="remarks" style="width:15%;" property="remarks" maxLength="20"></display:column>
	</display:table>
	</div>

 <h4>Remarks Details: <span class="lmsMinusImage floatr"></span></h4>
     <div class="viewLmsTable margintop10 viewLmsLeadTable">
    <display:table id="leadData" name="sessionScope.lmsForm.remarksPojoList" class="dataTable" style="width:100%" requestURI="">
			<display:column title="Sr.No" style="width:3%;" maxLength="5"><bean:write name="leadData_rowNum" /></display:column>
			<display:column title="Remarks" property="remarks" style="width:15%;" maxLength="20"></display:column>
			<display:column title="Created By" property="createdBy" style="width:15%;" maxLength="10"></display:column>
			<display:column title="Created Time" property="displayCreatedTime" style="width:15%;"></display:column>
			<display:column title="Action" style="width:15%;">
				<logic:iterate id="performingActions" name="lmsForm" property="performingActions" indexId="ctr">
					<logic:equal name="leadData" property="actions" value="${performingActions.contentValue}">${performingActions.contentName}</logic:equal>
				</logic:iterate>
				<logic:equal name="leadData" property="actions" value=""></logic:equal>
			</display:column>
			<display:column title="Reason" style="width:15%;">
			<logic:iterate id="closeReasons" name="lmsForm" property="closeReasons" indexId="ctr">
				<logic:equal name="leadData" property="reasonId" value="${closeReasons.categoryId}">${closeReasons.categoryValue}</logic:equal>
			</logic:iterate>
			<logic:equal name="leadData" property="reasonId" value="0"></logic:equal>
			</display:column>
	</display:table>
	</div>

 <h4>Activity Log Details: <span class="lmsMinusImage floatr"></span></h4>
      <div class="viewLmsTable margintop10">
         Coming Soon and stay with us
	</div>
</div>
 -->
<!-- Lead related additional data Ends -->

  <p class="clear"></p>

</div>
</div>
</div>
</div>
