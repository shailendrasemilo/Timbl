<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib uri="/WEB-INF/tlds/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/tlds/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/tlds/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/tlds/displaytag.tld" prefix="display"%>
<html>
<body>
<div class="loadingPopup hidden"></div>
<div id="section" >
<html:form action="/partnerManagement" styleId="idSearchPartner" method="post">
<html:hidden name="partnerManagementForm" property="businessTypeSP" styleId="IDbusinessType"/>
 <div class="section">
   <h2>Search Partner</h2>
   <span class="error_message"><html:errors property="appError" /></span>
        <div class="success_message">
			<logic:messagesPresent message="true">
   				<html:messages id="message" message="true">
   					<bean:write name="message" /><br />
   				 </html:messages>
			</logic:messagesPresent>
		</div>
   <div class="inner_section">
   <div class="inner_left_lead floatl">
     <h4>Date on Boarded</h4>
     
      <p class="floatl clear">
     <b>From</b> <html:text property="boardedDateFrom" styleClass="tcal" readonly="true" styleId="IdFormDate"/></p>  
	  <p class="floatl marginleft131"><b>To:</b><html:text property="boardedDateTo" styleClass="tcal" readonly="true" styleId="IdToDate"/>
    <a href="#" class="go_button marginleft78" onclick="clearBoardedDate();">Reset Date</a>
   </p> 

    <h4 class="clear margintop20">Partner Details</h4>
     <p  class="floatl clear"><b>State</b>
    <span class="dropdownWithoutJs"> 
       <html:select property="partnerState" styleId="partnerStateId" value='${partnerManagementForm.partnerState}'>
		  <html:option value="">All States</html:option>
		  <logic:notEmpty name="partnerManagementForm" property="statePojoList">
			 <html:optionsCollection name="partnerManagementForm" property="statePojoList" label="stateName" value="stateName" />
			</logic:notEmpty>
	 </html:select>
	</span>
    </p>
    
    <p  class="floatl marginleft60"><b>City</b> 
        <span class="dropdownWithoutJs"> 
        	<logic:notEmpty name="partnerManagementForm" property="cityPojoList">
           		<html:select property="partnerCity" name="partnerManagementForm" styleId="partnerCityId" value='${partnerManagementForm.partnerCity }'>
               		<html:option value="">All Cities</html:option>
               		<html:optionsCollection name="partnerManagementForm" property="cityPojoList" label="cityName" value="cityName" />
           		</html:select>
           </logic:notEmpty>
           <logic:empty name="partnerManagementForm" property="cityPojoList">
           		<html:select property="partnerCity" name="partnerManagementForm" styleId="partnerCityId" disabled="true" value='${partnerManagementForm.partnerCity }'>
           		</html:select>
           </logic:empty>
		</span>
    </p>

   <p  class="floatl clear"><b>Name of Partner</b> <html:text property="partnerName" styleClass="textbox" maxlength="60"/></p>
  
   <p  class="floatl marginleft60"><b>Type of Partner</b> 
      <span class="dropdownWithoutJs">
			<html:select property="partnerType">
			  <html:option value="">All</html:option>
			    <html:optionsCollection name="partnerManagementForm" property="partnerTypeList" label="contentName" value="contentValue"/>		
			</html:select>  
     </span>
     </p>
    <%-- 
    <p  class="floatl clear"><b>Added in ERP</b>
    <span class="dropdownWithoutJs">
        <html:select property="addedInERP">
			<html:option value="">All</html:option>
			<html:option value="P">Pending</html:option>
			<html:option value="C">Complete</html:option>
		</html:select>
    </span>
     </p>
     --%>
       <p  class="floatl clear"><b>Type of Business</b>
        <span class="dropdownWithoutJs">
			<html:select name="partnerManagementForm" property="businessType">
			<html:option value="">All</html:option>
			    <html:optionsCollection name="partnerManagementForm" property="products" label="contentName" value="contentValue"/>		
			</html:select>  
        </span>
     </p>
     
  
     <p  class="floatl marginleft60"><b>Status</b> 
         <span class="dropdownWithoutJs">
			<html:select property="searchStatus">
			    <html:optionsCollection name="partnerManagementForm" property="statusList" label="contentName" value="contentValue"/>		
			</html:select>  
		 </span>
    </p>
    <p  class="floatl clear"><b>Partner Short Name</b> <html:text name="partnerManagementForm" property="partnerShortName" styleClass="textbox" maxlength="60"/>
     </p>
     
     <p  class="floatl marginleft60"><b>Partner Code</b>
      <html:text name="partnerManagementForm" property="partnerCode" styleClass="textbox" maxlength="60"/>
     </p>
      
   </div>
   <div class="floatr inner_right">
   <html:link href="#" styleClass="main_button" styleId="submit_searchPartner"><span>Search</span></html:link>
    </div>
    <p class="clear"></p>
     </div>
			<logic:notEmpty name="partnerManagementForm" property="partnerList">
					<div overflow="auto">
					<display:table id="tableList" name="${partnerManagementForm.partnerList}" class="dataTable" style="width:100%" pagesize="15" requestURI="" >
							<display:setProperty name="paging.banner.placement"	value="bottom" />
							<display:column title="Sr.No.">
								<bean:write name="tableList_rowNum" />
							</display:column>
							<display:column title="Name Of Partner"	property="partnerName"></display:column>
      						<display:column title="Partner Code" property="crmPartnerCode"></display:column>
							<display:column title="Partner's Contact Person" property="hoCpn"></display:column>
							<display:column title="Mobile No." property="mobileNo"></display:column>
							<display:column title="Email ID" property="emailId"></display:column>
							<display:column title="Related Dept." property="relatedDept"></display:column>
							<display:column title="Boarded Date" >
								<bean:write name="crmRoles" property="toDate(${tableList.boardedDate})" scope="session"/>
							</display:column>
							<display:column title="Status">
							<logic:notEmpty name="tableList" property="currentStatus">
								<bean:write name="crmRoles" property="displayEnum(PartialStatus,${tableList.currentStatus})" scope="session" />
							</logic:notEmpty>
							</display:column>
							<%-- <display:column title="Added In ERP">
								<logic:equal name="tableList" property="addedErp" value="C">Complete</logic:equal>
								<logic:equal name="tableList" property="addedErp" value="P">Pending</logic:equal>
							</display:column> --%>
							<logic:equal name="crmRoles" property="available(update_partner_management)" value="true" scope="session">								
							<display:column title="Edit Details">
								<logic:notEqual name="tableList" property="currentStatus" value="D">
									<a href="javascript:editPartnerManagement('<bean:write name="tableList" property="partnerId" />','editPartner')" style="cursor: pointer" title="Edit Details">Edit</a>
								</logic:notEqual>
								<logic:equal name="tableList" property="currentStatus" value="D">
									-
								</logic:equal>
							</display:column>
							</logic:equal>
							<logic:equal name="crmRoles" property="available(update_partner_management,delete_partner_management,recover_partner_management)" value="true" scope="session">
							<display:column title="Change Status">
								<logic:equal name="tableList" property="currentStatus" value="A" >
								<logic:equal name="crmRoles" property="available(update_partner_management)" value="true" scope="session">
									<a href="javascript:change_PartnerStatus('<bean:write name="tableList" property="partnerId" />','I')">Deactivate</a>&nbsp;|&nbsp;
								</logic:equal>
								<logic:equal name="crmRoles" property="available(delete_partner_management)" value="true" scope="session">
									<a href="javascript:change_PartnerStatus('<bean:write name="tableList" property="partnerId" />','D')">Delete</a>
								</logic:equal>
								</logic:equal>
								<logic:equal name="tableList" property="currentStatus" value="I">
								<logic:equal name="crmRoles" property="available(update_partner_management)" value="true" scope="session">
									<a href="javascript:change_PartnerStatus('<bean:write name="tableList" property="partnerId" />','A')">Activate</a>&nbsp;|&nbsp;
								</logic:equal>
								<logic:equal name="crmRoles" property="available(delete_partner_management)" value="true" scope="session">
									<a href="javascript:change_PartnerStatus('<bean:write name="tableList" property="partnerId" />','D')">Delete</a>
								</logic:equal>
								</logic:equal>
								<logic:equal name="tableList" property="currentStatus" value="D">
								<logic:equal name="crmRoles" property="available(recover_partner_management)" value="true" scope="session">
									Deleted&nbsp;(<a href="javascript:change_PartnerStatus('<bean:write name="tableList" property="partnerId" />','A')">Recover</a>)
								</logic:equal>
								</logic:equal>
						  </display:column>
						  </logic:equal>
							
							
							
						<logic:equal name="crmRoles" property="available(view_partner_management)" value="true" scope="session">
							<display:column title="View">	
								<a href="javascript:viewPartnerManagement('<bean:write name="tableList" property="partnerId" />')">View</a>
							</display:column>
						</logic:equal>
						
						<logic:equal name="crmRoles" property="available(update_partner_management)" value="true" scope="session">
						  <display:column title="Assign SP">
							<logic:equal name="tableList" property="partnerType" value="NP">
							    <logic:equal name="tableList" property="currentStatus" value="A">
									<a href="javascript:assignServicePartner('<bean:write name="tableList" property="partnerId" />','<bean:write name="tableList" property="currentStatus" />','<bean:write name="tableList" property="bussinessType" />')" >Assign SP</a>
							    </logic:equal>
								<logic:notEqual name="tableList" property="currentStatus" value="A">
								-
								</logic:notEqual>
							 </logic:equal>
								<logic:notEqual name="tableList" property="partnerType" value="NP">
								-
								</logic:notEqual>
							</display:column>
							<display:column title="Add NP Mobile">
								<logic:equal name="tableList" property="partnerType" value="NP">
									<logic:equal name="tableList" property="currentStatus" value="A">
										<a href="javascript:addNPMobile('<bean:write name="tableList" property="partnerId" />','<bean:write name="tableList" property="currentStatus" />')" >Add NP Mobile</a>
								    </logic:equal>
									<logic:notEqual name="tableList" property="currentStatus" value="A"> - </logic:notEqual>
								</logic:equal>
								<logic:notEqual name="tableList" property="partnerType" value="NP"> -  </logic:notEqual>
							</display:column>
						</logic:equal>
						
				</display:table>
				</div>
			</logic:notEmpty>
			<logic:empty name="partnerManagementForm" property="partnerList">
					<b> <html:errors property="noRecordFound"/> </b>
			</logic:empty>
			<html:hidden name="partnerManagementForm" property="partnerId" styleId="idPartner"/>
			<html:hidden name="partnerManagementForm" property="currentStatus" styleId="newStatus" />
 </div>
 </html:form>
</div>
</body>
</html>
