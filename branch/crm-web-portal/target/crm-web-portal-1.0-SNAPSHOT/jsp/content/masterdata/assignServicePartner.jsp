<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<%@taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@taglib uri="http://struts.apache.org/tags-nested" prefix="nested"%>
<div id="section" >
  <html:form action="/partnerManagement" styleId="assignPartner" >	
 <div class="section">
   <h2>Network Partner Mapping</h2>
   <div class="inner_section">
   <div class="error_message">
	<span class="error_message"><html:errors property="appError" /></span>
				<span class="success_message">
				<logic:messagesPresent message="true" property="appMessage">
				<html:messages id="msg" message="true" property="appMessage">
					<bean:write name="msg" />
				</html:messages>
				</logic:messagesPresent>
				</span>
	</div>	 
   <div class="inner_left floatl createUserLeft">
   			<p class="floatl">
   			<b>Network Partner</b> 
    		<span class="showTextbox">
    			<bean:write name="partnerManagementForm" property="partnerName"/>
			</span>
			</p>
			
			<p class="floatl marginleft30">
   			<b>Service Name</b> 
    		
      		<logic:notEmpty name="partnerManagementForm" property="products">
      		<span class="dropdownWithoutJs">
      			<html:select name="partnerManagementForm" property="businessTypeSP" onchange="getServicePartnerList();" styleId="businessTypeSPID">
					<html:optionsCollection name="partnerManagementForm" property="products" label="contentName" value="contentValue"/>
				</html:select>
			</span>
      		</logic:notEmpty>
      		<logic:empty name="partnerManagementForm" property="products">
   				<span class="showTextbox">
   					No Service Available
   				</span>
      		</logic:empty>			
			</p>
			<br class="clear"/>
	   <div id="role_group_view">
	    <span class="addbtn_role_group"><a href="javascript:void(0)" id="addServicePartner" class="grey_button_add" ><span>Add</span></a></span>
	    <div class="display_group">
	     <div class="stays-at-top">
	        <span class="heading_text">  Service Partner</span> 
	     </div>
		    <ul class="add_option82" id="parameterGroupList">
		    	<logic:notEmpty name="partnerManagementForm" property="crmMappingList">
				<logic:iterate id="crmMappingList" name="partnerManagementForm" property="crmMappingList" indexId="ctr">
					<%! int i=1; %>
						<html:hidden name="crmMappingList" property="editable" value="true" indexed="true"/>
				
				
				<logic:equal name="crmMappingList" property="recordId" value="0">
					<html:hidden name="crmMappingList" property="partnerByNpId.partnerId" value="${partnerManagementForm.partnerId}" indexed="true"/>
				</logic:equal>
				<logic:notEqual name="crmMappingList" property="recordId" value="0">
					<html:hidden name="crmMappingList" property="partnerByNpId.partnerId" value="${partnerByNpId.partnerId}" indexed="true"/>
				</logic:notEqual>
				<logic:equal name="crmMappingList" property="recordId" value="0">
					<html:hidden name="crmMappingList" property="product" value="${partnerManagementForm.businessTypeSP}" indexed="true"/>
				</logic:equal>
				<logic:notEqual name="crmMappingList" property="recordId" value="0">
					<html:hidden name="crmMappingList" property="product" value="${crmMappingList.product}" indexed="true"/>
				</logic:notEqual>
				
				<logic:equal name="crmMappingList" property="recordId" value="0">
					<html:hidden name="crmMappingList" property="createdBy" value="${userPojo.userId}" indexed="true" />
				</logic:equal>
				<logic:notEqual name="crmMappingList" property="recordId" value="0">
					<html:hidden name="crmMappingList" property="lastModifiedBy" value="${userPojo.userId}" indexed="true" />
				</logic:notEqual>
				
				
					<% if(i%2 == 0){ %>							
		      		<li class="first">
		      		<% } else { %>
		       		<li class="second">
		       		<% } %>
				<logic:equal name="crmMappingList" property="recordId" value="0">
				 <span class="dropdownWithoutJs">
			       	<html:select name="crmMappingList" property="partnerBySpId.partnerId" indexed="true">
			  		<logic:notEmpty name="partnerManagementForm" property="servicePartnerList">
					<html:optionsCollection name="partnerManagementForm" property="servicePartnerList" label="partnerName" value="partnerId"/>		
					</logic:notEmpty>
					</html:select>
				</span>
				</logic:equal>
				<logic:notEqual name="crmMappingList" property="recordId" value="0">
				 <span class="showTextbox">	
					<bean:write name="crmMappingList" property="partnerBySpId.partnerName" />
				</span>
					<html:hidden name="crmMappingList" property="partnerBySpId.partnerId"  indexed="true"/>
					<html:hidden name="crmMappingList" property="partnerByNpId.partnerId" value="${partnerManagementForm.partnerId}" indexed="true"/>
				</logic:notEqual>
				
				
				<span class="marginleft13">
					<html:radio name="crmMappingList" property="status" value="A" indexed="true">Active</html:radio>
					<html:radio name="crmMappingList" property="status" value="I" indexed="true">Inactive</html:radio> 
				</span>
				<logic:equal name="crmMappingList" property="recordId" value="0">
				<span class="marginleft13">
				 <html:link href="javascript:deletePartnerRow(${ctr})"  styleClass="close" >
			        <img src="images/bg/delete.png" align="absmiddle" title="delete" />
			     </html:link>
				 </span>
			    </logic:equal>
					</li>
					 <% i++; %>	
			</logic:iterate>
			</logic:notEmpty>	
			<logic:empty name="partnerManagementForm" property="crmMappingList">
			 <span class="error_message">
			  Please click on 'Add' button to Assign Service Partner.
			  </span>
			</logic:empty>
		</ul>
      </div>
     </div> 
    </div>
   <logic:equal name="crmRoles" property="available(update_npsp_mapping,create_npsp_mapping)" value="true" scope="session">	 
    <div class="floatr inner_right">
     	<a href="#" id="submit_AssignPartner" class="main_button">Submit</a>
    </div>
  </logic:equal>
    	<br class="clear"/>
    </div>
    </div>
    	<html:hidden name="partnerManagementForm" property="rowCounter" styleId="rowIndex_partner" />
    </html:form>
    <p class="clear"></p>
     </div>
 <div>
</div>
		   
		   
		  
		 
		 

 



