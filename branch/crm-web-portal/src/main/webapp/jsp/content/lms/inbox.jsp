<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="/WEB-INF/tlds/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/tlds/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/tlds/displaytag.tld" prefix="display"%>
<%@taglib uri="/WEB-INF/tlds/struts-html.tld" prefix="html"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<html>
	<head>
	<link href="css/displaytag.css" rel="stylesheet" type="text/css" />
	<link href="css/tabcontent.css" rel="stylesheet" type="text/css" />
	<script type="text/javascript" src="javascript/tabcontent.js"></script>
	<script type="text/javascript" src="javascript/inboxController.js"></script>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<style>
.ui-autocomplete {
  max-height: 150px;
  overflow-y: auto;
}
</style>
<script type="text/javascript" src="javascript/jquery-ui.js"></script>
<link rel="stylesheet" href="css/jquery-ui.css" />
<script>
$(document).ready(function() {
fetchSubSubCategories();
});
</script>
</head>
<body>
<div class="loadingPopup hidden"></div>
<div id="section" >
<html:form action="/goToInbox.do?method=allInbox" styleId="idLeadInbox"  method="post">
<c:set var="inboxType" value="ALL_INBOX"></c:set>
<div class="section">
<!-- Success Messages Starts -->
	<div class="success_message" >
		<logic:messagesPresent message="true">
   		<html:messages id="message" message="true">
		<bean:write name="message" />
		</html:messages>
		</logic:messagesPresent>
	</div>
<!-- Success Messages Ends -->
	<p style="margin: 5px 0 15px; color: #173676;">Note: Please click refresh button, before start work.</p>
	<ul class="tabs" persist="true">
		<li><a href="#" rel="Individual_Inbox">My Inbox</a></li>
		<li><a href="#" rel="Group_Inbox">Group Inbox</a></li>
		<li>&nbsp;</li>
	    <li style="padding: 7px 20px;">
          <logic:equal name="crmRoles" property="available(view_lead,view_ina,view_qrc,view_qrc_adj,view_qrc_shifting,view_freeze)" value="true" scope="session">
            <span class="LmsdropdownWithoutJs"> <html:select property="searchCriteria" name="inboxForm" styleId="inboxSearchCriteria" onchange="javascript:fetchSubSubCategories();">
            	<logic:notEmpty name="inboxForm" property="inboxSearchList">
                	<html:optionsCollection name="inboxForm" property="inboxSearchList" label="contentName" value="contentValue" />
                </logic:notEmpty>
              </html:select>
            </span>
            <html:text property="searchCriteriaValue" name="inboxForm" styleClass="textbox marginleft6" styleId="inboxSearchCriteriaValue"  maxlength="128" style="width:130px;" onkeyup="javascript:changeToUpperCaseInbox(this)"></html:text>
            <c:choose>
            <c:when test="${inboxForm.inboxType ne 'Waiver' and inboxForm.inboxType ne 'Shifting' }">
            <input class="go_button" type="button" value="Go" onclick="javascript:inboxSearch('allInbox');" />
            <input class="go_button" type="button" value="Reset" onclick="javascript:resetInboxCriteria('allInbox');" />
             <input class="go_button" type="button" value="Refresh" onclick="javascript:AutoRefresh(60000);" />
            </c:when>
            <c:otherwise>
            <input class="go_button" type="button" value="Go" onclick="javascript:inboxSearch('workflowInbox');" />
            <input class="go_button" type="button" value="Reset" onclick="javascript:resetInboxCriteria('workflowInbox');" />
            <input class="go_button" type="button" value="Refresh" onclick="javascript:AutoRefresh(60000);" />
            </c:otherwise>
            </c:choose>
            
          </logic:equal>  
	    </li>
	</ul>
	<div class="tabcontents">
		<div id="Individual_Inbox" class="tabcontent">
<!-- All inbox for individual starts -->		
		<logic:equal name="inboxForm" property="inboxType" value="ALL_INBOX">
		<display:table id="allDataIndividual" name="sessionScope.inboxForm.allInboxList" class="dataTable_Inbox" style="width:100%"  requestURI="" pagesize="10">
						<display:setProperty name="paging.banner.placement"	value="bottom" />
						<display:column style="width:5%;" title="<strong>Select All</strong><input type='checkbox' title='Select All' id='selectIndAll' onclick='selectSelfAll(this)'/>" class="${allDataIndividual.lmsColor} ${allDataIndividual.type eq 'QRC' ? allDataIndividual.color : '' }">
							<html:checkbox  name="inboxForm" property="selfIds" styleClass="self" value="${allDataIndividual_rowNum}" styleId="allInbox_${allDataIndividual_rowNum}" onchange="InboxcheckData(this,'self')"></html:checkbox>
						</display:column>
						<display:column title="Lead ID/CAF Number/Ticket ID" style="width:8%;" class="bold_${allDataIndividual.unRead} ${allDataIndividual.lmsColor} ${allDataIndividual.type eq 'QRC' ? allDataIndividual.color : '' }">
							<logic:equal name="crmRoles" property="available(view_lead,view_ina,view_qrc_tkt,view_freeze)" value="true" scope="session"> 
									<a href="javascript:viewAll('<bean:write name="allDataIndividual" property="lmsIdCrfRecordId"/>',
									'<bean:write name="allDataIndividual" property="leadIdCrfIdTicketId"/>',
									'<bean:write name="allDataIndividual" property="product"/>',
									'<bean:write name="allDataIndividual" property="type"/>','inbox',
									'<bean:write name="allDataIndividual" property="inboxId"/>')">
									<bean:write name="allDataIndividual" property="leadIdCrfIdTicketId"/></a>
							</logic:equal>
							<logic:notEqual name="crmRoles" property="available(view_lead,view_ina,view_lead,view_qrc_tkt,view_freeze)" value="true" scope="session">
									<bean:write name="allDataIndividual" property="leadIdCrfIdTicketId"/>
							</logic:notEqual>
						</display:column>
						<display:column title="Stage/Bin" style="width:10%;" class="bold_${allDataIndividual.unRead} ${allDataIndividual.lmsColor} ${allDataIndividual.type eq 'QRC' ? allDataIndividual.color : '' }">
						 <logic:notEmpty name="allDataIndividual" property="stage">
						 	<logic:equal name="allDataIndividual" property="type" value="QRC">
						 		<bean:write name="crmRoles" property="displayEnum(functionalBins,${allDataIndividual.stage})" scope="session" />
						 	</logic:equal>
						 	<logic:notEqual name="allDataIndividual" property="type" value="QRC">
								<bean:write name="crmRoles" property="displayEnum(CRMOperationStages,${allDataIndividual.stage})" scope="session" />
							</logic:notEqual>
						</logic:notEmpty>
						</display:column>
						<display:column title="Previous Stage/Bin" style="width:10%;" class="bold_${allDataIndividual.unRead} ${allDataIndividual.lmsColor}  ${allDataIndividual.type eq 'QRC' ? allDataIndividual.color : '' }">
						 <logic:notEmpty name="allDataIndividual" property="previousStage">
						 	<logic:equal name="allDataIndividual" property="type" value="QRC">
						 		<bean:write name="crmRoles" property="displayEnum(functionalBins,${allDataIndividual.previousStage})" scope="session" />
						 	</logic:equal>
						 	<logic:notEqual name="allDataIndividual" property="type" value="QRC">
								<bean:write name="crmRoles" property="displayEnum(CRMOperationStages,${allDataIndividual.previousStage})" scope="session" />
							</logic:notEqual>
						</logic:notEmpty>
						<logic:empty name="allDataIndividual" property="previousStage">
							-
						</logic:empty>
						</display:column>
						
						<display:column title="QRC Type/Docket Type" style="width:10%;"class="bold_${allDataIndividual.unRead} ${allDataIndividual.lmsColor} ${allDataIndividual.type eq 'QRC' ? allDataIndividual.color : '' }">
						<logic:equal  name="allDataIndividual" property="type"  value="QRC">
						
						  <bean:write name="crmRoles" property="displayEnum(qrcType,${ allDataIndividual.requestType })"/>
						
						</logic:equal>
						<logic:notEqual  name="allDataIndividual" property="type"  value="QRC">
						<bean:write name="allDataIndividual" property="requestType"/>
						</logic:notEqual>
						</display:column>
						<display:column title= "Sub Sub Category" style="width:10%;" class="bold_${allDataIndividual.unRead} ${allDataIndividual.lmsColor} ${allDataIndividual.type eq 'QRC' ? allDataIndividual.color : '' }" >
						<logic:notEmpty name="allDataIndividual" property="subSubCategory">
						<bean:write name="allDataIndividual" property="subSubCategory"/>
						</logic:notEmpty>
						
						<logic:empty name="allDataIndividual" property="subSubCategory">
						-
						</logic:empty>
						
						</display:column>
						<display:column title="Network Partner" style="width:10%;" class="bold_${allDataIndividual.unRead} ${allDataIndividual.lmsColor} ${allDataIndividual.type eq 'QRC' ? allDataIndividual.color : '' }">
						 <logic:notEqual name="allDataIndividual" property="networkPartner" value="0">
						 <bean:write name="crmRoles" property="displayEnum(NP,${ allDataIndividual.networkPartner})" scope="session" />
						 </logic:notEqual>
						  <logic:equal name="allDataIndividual" property="networkPartner" value="0">
						-
						 </logic:equal>
						</display:column>
						
						<display:column title="Service Name" style="width:10%;" class="bold_${allDataIndividual.unRead} ${allDataIndividual.lmsColor} ${allDataIndividual.type eq 'QRC' ? allDataIndividual.color : '' }">
						 <logic:notEmpty name="allDataIndividual" property="product">
								<bean:write name="crmRoles" property="displayEnum(Product,${allDataIndividual.product})" scope="session" />
						</logic:notEmpty>
							 <logic:empty name="allDataIndividual" property="product">
							 -
							 </logic:empty>
						</display:column>
						<%-- <display:column title="Previous Owner" style="width:10%;" property="previousStageOwner" class="bold_${allDataIndividual.unRead}"></display:column> --%>
						
						<display:column title="Created Date" style="width:10%;" property="displayCreatedTime" class="bold_${allDataIndividual.unRead} ${allDataIndividual.lmsColor} ${allDataIndividual.type eq 'QRC' ? allDataIndividual.color : '' }"></display:column>
						<logic:equal name="crmRoles" property="available(update_lead,delete_lead,update_ina,delete_ina,update_qrc_tkt,delete_qrc_tkt)" value="true" scope="session">
							<display:column title="Edit" style="width:8%;" class="bold_${allDataIndividual.unRead} ${allDataIndividual.lmsColor} ${allDataIndividual.type eq 'QRC' ? allDataIndividual.color : '' }">
									<a href="javascript:editAll('
									<bean:write name="allDataIndividual" property="lmsIdCrfRecordId"/>','
									<bean:write name="allDataIndividual" property="leadIdCrfIdTicketId"/>','
									<bean:write name="allDataIndividual" property="product"/>','
									<bean:write name="allDataIndividual" property="stage"/>','
									<bean:write name="allDataIndividual" property="type"/>','
									<bean:write name="allDataIndividual" property="inboxId"/>')">Edit</a>
							</display:column>
						</logic:equal>
						<logic:equal name="crmRoles" property="available(view_lead,view_ina,view_qrc_tkt)" value="true" scope="session">
							<display:column title="Assign to Group" style="width:15%;" class="bold_${allDataIndividual.unRead} ${allDataIndividual.lmsColor} ${allDataIndividual.type eq 'QRC' ? allDataIndividual.color : '' }">
								<logic:notMatch name="allDataIndividual" property="stage" value="AM">
									<logic:notMatch name="allDataIndividual" property="stage" value="Initiate">
									<a href="javascript:changeBinOwner('<bean:write name="allDataIndividual" property="lmsIdCrfRecordId"/>','<bean:write name="allDataIndividual" property="leadIdCrfIdTicketId"/>','Personal','<bean:write name="allDataIndividual" property="stage"/>','<bean:write name="allDataIndividual" property="type"/>','ALL_INBOX')">Assign to Group</a>
								</logic:notMatch>
								</logic:notMatch>
								<logic:match name="allDataIndividual" property="stage" value="AM">
									-
								</logic:match>
								<logic:match name="allDataIndividual" property="stage" value="Initiate">
									-
								</logic:match>
							</display:column>
						</logic:equal>
		</display:table>
		</logic:equal>
<!-- All inbox for individual ends -->		
		<logic:equal name="inboxForm" property="inboxType" value="lead">
		<c:set var="inboxType" value="lead"></c:set>
		<display:table id="leadDataIndividual" name="sessionScope.inboxForm.lmsInboxList" class="dataTable_Inbox" style="width:100%"  requestURI="" pagesize="10">
						<display:setProperty name="paging.banner.placement"	value="bottom" />
						<display:column style="width:5%;" title="<strong>Select All</strong><input type='checkbox' title='Select All' id='selectIndAll' onclick='selectSelfAll(this)'/>" class="${leadDataIndividual.lmsColor}">
							<html:checkbox  name="inboxForm" property="selfIds" styleClass="self" value="${leadDataIndividual_rowNum}" styleId="selfLead_${leadDataIndividual_rowNum}" onchange="InboxcheckData(this,'self')"></html:checkbox>
						</display:column>
						<display:column title="Lead ID" style="width:8%;" class="bold_${leadDataIndividual.unRead} ${leadDataIndividual.lmsColor}">
							<logic:equal name="crmRoles" property="available(view_lead)" value="true" scope="session">
								<a href="javascript:viewLead('<bean:write name="leadDataIndividual" property="lmsId"/>','inbox','<bean:write name="leadDataIndividual" property="inboxId"/>')"><bean:write name="leadDataIndividual" property="leadId"/></a>
							</logic:equal>
							<logic:notEqual name="crmRoles" property="available(view_lead)" value="true" scope="session">
								<bean:write name="leadDataIndividual" property="leadId"/>
							</logic:notEqual>
						</display:column>
						<display:column title="Stage/Bin" style="width:10%;" class="bold_${leadDataIndividual.unRead} ${leadDataIndividual.lmsColor}">
						 <logic:notEmpty name="leadDataIndividual" property="lmsStage">
								<bean:write name="crmRoles" property="displayEnum(CRMOperationStages,${leadDataIndividual.lmsStage})" scope="session" />
						</logic:notEmpty>
						</display:column>
						<display:column title="Previous Stage/Bin" style="width:10%;" class="bold_${leadDataIndividual.unRead} ${leadDataIndividual.lmsColor}">
							<logic:notEmpty name="leadDataIndividual" property="previousStage">
								<bean:write name="crmRoles" property="displayEnum(CRMOperationStages,${leadDataIndividual.previousStage})" scope="session" />
							</logic:notEmpty>
							<logic:empty name="leadDataIndividual" property="previousStage">
							-
							</logic:empty>
						</display:column>
						
						<display:column title=" QRC Type /Docket Type" style="width:10%;" class="bold_${leadDataIndividual.unRead} ${leadDataIndividual.lmsColor}">
						
						${fn:toUpperCase(fn:substring(inboxForm.inboxType, 0, 1))}${fn:toLowerCase(fn:substring(inboxForm.inboxType, 1,fn:length(inboxForm.inboxType)))}
						
						</display:column >
						<display:column title="Sub Sub Category" style="width:10%;" class="bold_${leadDataIndividual.unRead} ${leadDataIndividual.lmsColor}">
						-
						</display:column >
						<display:column title="Network Partner" style="width:10%;" class="bold_${leadDataIndividual.unRead} ${leadDataIndividual.lmsColor}">
						-
						</display:column >
						
						<display:column title="Service Name" style="width:10%;" class="bold_${leadDataIndividual.unRead} ${leadDataIndividual.lmsColor}">
						 <logic:notEmpty name="leadDataIndividual" property="product">
								<bean:write name="crmRoles" property="displayEnum(Product,${leadDataIndividual.product})" scope="session" />
						</logic:notEmpty>
						</display:column>
						
						<display:column title="Created Date" style="width:10%;" class="bold_${leadDataIndividual.unRead} ${leadDataIndividual.lmsColor}">
							<bean:write name="crmRoles" property="xmlDate(${leadDataIndividual.createdTime})" scope="session" />	
						</display:column>
						<logic:equal name="crmRoles" property="available(update_lead,delete_lead)" value="true" scope="session">
							<display:column title="Edit" style="width:8%;" class="bold_${leadDataIndividual.unRead} ${leadDataIndividual.lmsColor}">
								<a href="javascript:editLead('<bean:write name="leadDataIndividual" property="lmsId"/>','<bean:write name="leadDataIndividual" property="leadId"/>','<bean:write name="leadDataIndividual" property="inboxId"/>')">Edit</a>
							</display:column>
						</logic:equal>
						<logic:equal name="crmRoles" property="available(view_lead)" value="true" scope="session">
							<display:column title="Assign to Group" style="width:15%;" class="bold_${leadDataIndividual.unRead} ${leadDataIndividual.lmsColor}">
								<logic:notMatch name="leadDataIndividual" property="lmsStage" value="AM">
									<a href="javascript:changeBinOwner('<bean:write name="leadDataIndividual" property="lmsId"/>','<bean:write name="leadDataIndividual" property="leadId"/>','Personal','<bean:write name="leadDataIndividual" property="lmsStage"/>','lead','lead')">Assign to Group</a>
								</logic:notMatch>
								<logic:match name="leadDataIndividual" property="lmsStage" value="AM">
									-
								</logic:match>
							</display:column>
						</logic:equal>
		</display:table>
		</logic:equal>
		<logic:equal name="inboxForm" property="inboxType" value="CAF">
		<c:set var="inboxType" value="CAF"></c:set>
			<display:table id="crfDataIndividual" name="sessionScope.inboxForm.crfInboxList" class="dataTable_Inbox" style="width:100%"  requestURI="" pagesize="10">
						<display:setProperty name="paging.banner.placement"	value="bottom" />
						<display:column style="width:5%;" title="<strong>Select All</strong><input type='checkbox' title='Select All' id='selectIndAll' onclick='selectSelfAll(this)'/>">
							<html:checkbox name="inboxForm" property="selfIds" styleClass="self" value="${crfDataIndividual_rowNum}" styleId="selfCAF_${crfDataIndividual_rowNum}" onchange="InboxcheckData(this,'self')"></html:checkbox>
						</display:column>
						<display:column title="CAF Number" style="width:8%;" class="bold_${crfDataIndividual.unRead}" >
							<logic:equal name="crmRoles" property="available(view_ina)" value="true" scope="session">
								<a href="javascript:viewCRF('<bean:write name="crfDataIndividual" property="recordId"/>',
							     '<bean:write name="crfDataIndividual" property="inboxId"/>','inbox')"><bean:write name="crfDataIndividual" property="crfId"/>
								</a>
							</logic:equal>
							<logic:notEqual name="crmRoles" property="available(view_ina)" value="true" scope="session">
								<bean:write name="crfDataIndividual" property="crfId"/>
							</logic:notEqual>
						</display:column>
						<display:column title="Stage/Bin" style="width:10%;" class="bold_${crfDataIndividual.unRead}">
							 <logic:notEmpty name="crfDataIndividual" property="crfStage">
								<bean:write name="crmRoles" property="displayEnum(CRMOperationStages,${crfDataIndividual.crfStage})" scope="session" />
							</logic:notEmpty>
						</display:column>
						<display:column title="Previous Stage/Bin" style="width:10%;" class="bold_${crfDataIndividual.unRead}">
							<logic:notEmpty name="crfDataIndividual" property="crfPreviousStage">
								<bean:write name="crmRoles" property="displayEnum(CRMOperationStages,${crfDataIndividual.crfPreviousStage})" scope="session" />
							</logic:notEmpty>
							<logic:empty name="crfDataIndividual" property="crfPreviousStage">
							-
							</logic:empty>
						</display:column>
						
						<display:column title=" QRC Type /Docket Type" style="width:10%;" class="bold_${crfDataIndividual.unRead}">
						
						<bean:write name="inboxForm" property="inboxType" />
						</display:column >
						
						<display:column title= "Sub Sub Category" style="width:10%;" >
						
						-
						</display:column>
						<display:column title="Network Partner" style="width:10%;" class="bold_${crfDataIndividual.unRead}">
						 <logic:notEqual name="crfDataIndividual" property="npId" value="0">
						 <bean:write name="crmRoles" property="displayEnum(NP,${ crfDataIndividual.npId})" scope="session" />
						 </logic:notEqual>
						  <logic:equal name="crfDataIndividual" property="npId" value="0">
						-
						 </logic:equal>
						 
						</display:column>
						
						
						<display:column title="Service Name" style="width:10%;" class="bold_${crfDataIndividual.unRead}">
							 <logic:notEmpty name="crfDataIndividual" property="product">
								<bean:write name="crmRoles" property="displayEnum(Product,${crfDataIndividual.product})" scope="session" />
							</logic:notEmpty>
						</display:column>
						
						
						<display:column title="Created Date" style="width:10%;" class="bold_${crfDataIndividual.unRead}">
							<bean:write name="crmRoles" property="xmlDate(${crfDataIndividual.createdTime})" scope="session" />
						</display:column>
						<logic:equal name="crmRoles" property="available(create_ina,update_ina,delete_ina)" value="true" scope="session">
							<display:column title="Edit" style="width:8%;" class="bold_${crfDataIndividual.unRead}">
							     <a href="javascript:editCRF('<bean:write name="crfDataIndividual" property="recordId"/>',
							     '<bean:write name="crfDataIndividual" property="inboxId"/>',
							     '<bean:write name="crfDataIndividual" property="crfId"/>')">Edit</a>
						   </display:column>
						</logic:equal>						
						<logic:equal name="crmRoles" property="available(view_ina)" value="true" scope="session">							
							<display:column title="Assign to group" style="width:15%;" class="bold_${crfDataIndividual.unRead}">
							<logic:notMatch name="crfDataIndividual" property="crfStage" value="Initiate">
								<a href="javascript:changeBinOwner('<bean:write name="crfDataIndividual" property="recordId"/>','<bean:write name="crfDataIndividual" property="crfId"/>','Personal','<bean:write name="crfDataIndividual" property="crfStage"/>','CAF','CAF')">Assign to Group</a>
						      </logic:notMatch>						      
						    <logic:equal name="crfDataIndividual" property="crfStage" value="Initiate">
								-
							</logic:equal>
						    </display:column>						  
						</logic:equal>
						
		</display:table>
		</logic:equal>
		<!-- QRC Individual Inbox implementation Starts -->
		<logic:equal name="inboxForm" property="inboxType" value="QRC">
		<c:set var="inboxType" value="QRC"></c:set>
			<display:table id="qrcDataIndividual" name="sessionScope.inboxForm.qrcInboxList" class="dataTable_Inbox" style="width:100%"  requestURI="" pagesize="10">
						<display:setProperty name="paging.banner.placement"	value="bottom" />
						<display:column style="width:5%;" title="<strong>Select All</strong><input type='checkbox' title='Select All' id='selectIndAll' onclick='selectSelfAll(this)'/>" class="${qrcDataIndividual.color}">
							<html:checkbox name="inboxForm" property="selfIds" styleClass="self" value="${qrcDataIndividual_rowNum}" styleId="selfQRC_${qrcDataIndividual_rowNum}" onchange="InboxcheckData(this,'self')"></html:checkbox>
						</display:column>
						<display:column title="Ticket ID" style="width:8%;" class="${qrcDataIndividual.color}">
							<logic:equal name="crmRoles" property="available(view_qrc_tkt)" value="true" scope="session">
								<a href="javascript:viewQRC('<bean:write name="qrcDataIndividual" property="ticketId"/>',0,'inbox','<bean:write name="qrcDataIndividual" property="inboxId"/>')"><bean:write name="qrcDataIndividual" property="srId"/></a>
							</logic:equal>
							<logic:notEqual name="crmRoles" property="available(view_qrc_tkt)" value="true" scope="session">
								<bean:write name="qrcDataIndividual" property="srId"/>
							</logic:notEqual>
					 	</display:column>
						<display:column title="Stage/Bin" style="width:10%;" class="bold_${qrcDataIndividual.unRead} ${qrcDataIndividual.color}">
							<c:if test="${ (qrcDataIndividual.moduleType eq 'QRC' || qrcDataIndividual.moduleType  eq 'LMS') }">
								<logic:notEmpty name="qrcDataIndividual" property="functionalbinId">
									<bean:write name="crmRoles" property="displayEnum(functionalBins,${qrcDataIndividual.functionalbinId})" scope="session" />
								</logic:notEmpty>							
							</c:if>
						</display:column>
						<display:column title="Previous Stage/Bin" style="width:10%;"  class="bold_${qrcDataIndividual.unRead} ${qrcDataIndividual.color}">
						 	<logic:notEmpty name="qrcDataIndividual" property="previousStage">								
								<bean:write name="crmRoles" property="displayEnum(functionalBins,${qrcDataIndividual.previousStage})" />
							</logic:notEmpty>
							<logic:empty name="qrcDataIndividual" property="previousStage">
							-
							</logic:empty>
						</display:column>
						<display:column title=" QRC Type /Docket Type" style="width:10%;" class="bold_${qrcDataIndividual.unRead} ${qrcDataIndividual.color}">
						  <bean:write name="crmRoles" property="displayEnum(qrcType,${ qrcDataIndividual.qrcType })"/>
						
						</display:column >
						
						<display:column title= "Sub Sub Category" style="width:10%;" property="qrcSubSubCategory" class="bold_${qrcDataIndividual.unRead} ${qrcDataIndividual.color}" >
						
					
						</display:column>
						
						<display:column title="Network Partner" style="width:10%;" class="bold_${qrcDataIndividual.unRead} ${qrcDataIndividual.color}">
						 <bean:write name="crmRoles" property="displayEnum(NP,${ qrcDataIndividual.customerDetailsPojo.npId })"/>
						</display:column>
						
						<display:column title="Service Name" style="width:10%;" class="bold_${qrcDataIndividual.unRead} ${qrcDataIndividual.color}">
						   <logic:notEmpty name="qrcDataIndividual" property="customerDetailsPojo">
							  <logic:notEmpty name="qrcDataIndividual" property="customerDetailsPojo.product">
									<bean:write name="crmRoles" property="displayEnum(Product,${qrcDataIndividual.customerDetailsPojo.product})" scope="session" />
							 </logic:notEmpty>
							 <logic:empty name="qrcDataIndividual" property="customerDetailsPojo.product">
								 -
							 </logic:empty>
						 </logic:notEmpty>
						 <logic:notEmpty name="qrcDataIndividual" property="lmsPojo">
							  <logic:notEmpty name="qrcDataIndividual" property="lmsPojo.product">
									<bean:write name="crmRoles" property="displayEnum(Product,${qrcDataIndividual.lmsPojo.product})" scope="session" />
							 </logic:notEmpty>
							 <logic:empty name="qrcDataIndividual" property="lmsPojo.product">
								 -
							 </logic:empty>
						 </logic:notEmpty>
						</display:column>
						
						<display:column title="Created Date" style="width:10%;" class="bold_${qrcDataIndividual.unRead} ${qrcDataIndividual.color}">
							<bean:write name="crmRoles" property="xmlDate(${qrcDataIndividual.createdTime})" scope="session" />
						</display:column>
						<logic:equal name="crmRoles" property="available(update_qrc_tkt,delete_qrc_tkt)" value="true" scope="session">
							<display:column title="Edit" style="width:8%;" class="bold_${crfDataIndividual.unRead} ${qrcDataIndividual.color}">
							     <a href="javascript:editQRC('<bean:write name="qrcDataIndividual" property="ticketId"/>','true','inbox','<bean:write name="qrcDataIndividual" property="inboxId"/>')">Edit</a>
						   </display:column>
						</logic:equal>						
						<logic:equal name="crmRoles" property="available(view_qrc_tkt)" value="true" scope="session">
							<display:column title="Assign to group" style="width:15%;" class="bold_${qrcDataIndividual.unRead} ${qrcDataIndividual.color}">
								<a href="javascript:changeBinOwner('<bean:write name="qrcDataIndividual" property="ticketId"/>','<bean:write name="qrcDataIndividual" property="srId"/>','Personal','<bean:write name="qrcDataIndividual" property="functionalbinId"/>','QRC','QRC')">Assign to Group</a>
						    </display:column>
						</logic:equal>
			</display:table>
		</logic:equal>
		<!-- QRC Individual Inbox implementation Ends -->
		<!-- Workflow(Shifting/Waiver) Inbox implementation Starts -->
		<c:if test="${ inboxForm.inboxType eq 'Waiver' or inboxForm.inboxType eq 'Shifting'}" >
		<c:set var="inboxType" value="WORKFLOW"></c:set>
			<display:table id="workFlowDataId" name="sessionScope.inboxForm.workflowInboxList" class="dataTable_Inbox" style="width:100%"  requestURI="" pagesize="10">
						<display:setProperty name="paging.banner.placement"	value="bottom" />
						<display:column style="width:5%;" title="<strong>Select All</strong><input type='checkbox' title='Select All' id='selectIndAll' onclick='selectSelfAll(this)'/>">
							<html:checkbox name="inboxForm" property="selfIds" styleClass="self" value="${workFlowDataId_rowNum}" styleId="selfWF_${workFlowDataId_rowNum}" onchange="InboxcheckData(this,'self')"></html:checkbox>
						</display:column>
						<display:column title="Workflow ID" style="width:8%;">
						<logic:equal name="crmRoles" property="available(view_qrc_adj,view_qrc_shifting)" value="true" scope="session">
								<a href="javascript:viewWorkflow('<bean:write name="workFlowDataId" property="workflowId"/>',
							     '<bean:write name="workFlowDataId" property="requestType"/>','<bean:write name="workFlowDataId" property="inboxId"/>')"><bean:write name="workFlowDataId" property="workflowId"/></a>
							</logic:equal>
							<logic:notEqual name="crmRoles" property="available(view_qrc_adj,view_qrc_shifting)" value="true" scope="session">
								<bean:write name="workFlowDataId" property="workflowId"/>
							</logic:notEqual>
					 	</display:column>
						<display:column title="Stage/Bin" style="width:10%;" class="bold_${workFlowDataId.unRead}">
						 <logic:notEmpty name="workFlowDataId" property="workflowStage">
						 <logic:equal  name="workFlowDataId" property="requestType" value="Waiver">
						 <bean:write name="crmRoles" property="displayEnum(functionalBins,${workFlowDataId.workflowStage})" scope="session" />
						 </logic:equal>
						  <logic:notEqual  name="workFlowDataId" property="requestType" value="Waiver">
								<bean:write name="crmRoles" property="displayEnum(CRMOperationStages,${workFlowDataId.workflowStage})" scope="session" />
						</logic:notEqual>
						</logic:notEmpty>
						</display:column>
						<display:column title="Previous Stage/Bin" style="width:10%;" class="bold_${workFlowDataId.unRead}">
							<logic:notEmpty name="workFlowDataId" property="previousStage">
							 <logic:equal  name="workFlowDataId" property="requestType" value="Waiver">
							 <bean:write name="crmRoles" property="displayEnum(functionalBins,${workFlowDataId.previousStage})" scope="session" />
							 </logic:equal>
							<logic:notEqual  name="workFlowDataId" property="requestType" value="Waiver">
								<bean:write name="crmRoles" property="displayEnum(CRMOperationStages,${workFlowDataId.previousStage})" scope="session" />
								</logic:notEqual>
							</logic:notEmpty>
							<logic:empty name="workFlowDataId" property="previousStage">
							-
							</logic:empty>
						</display:column>
						<display:column title="Customer ID" style="width:10%;" property="customerId" class="bold_${workFlowDataId.unRead}">
						</display:column>
						<display:column title="Request Type" style="width:10%;" property="requestType" class="bold_${workFlowDataId.unRead}">
						</display:column>
						<display:column title="Workflow Type" style="width:10%;" property="workflowType" class="bold_${workFlowDataId.unRead}">
						</display:column>
						
						<display:column title="Created Date" style="width:10%;">
							<bean:write name="crmRoles" property="xmlDate(${workFlowDataId.createdTime})" scope="session"/>
						</display:column>
						<logic:equal name="crmRoles" property="available(update_qrc_adj,delete_qrc_adj,update_qrc_shifting,delete_qrc_shifting)" value="true" scope="session">
							<display:column title="Edit" style="width:8%;" class="bold_${workFlowDataId.unRead}">
							     <a href="javascript:editWorkflow('<bean:write name="workFlowDataId" property="workflowId"/>','<bean:write name="workFlowDataId" property="requestType"/>','<bean:write  name="workFlowDataId" property="workflowStage"/>','<bean:write  name="workFlowDataId" property="workflowType"/>','<bean:write  name="workFlowDataId" property="customerId"/>','<bean:write name="workFlowDataId" property="inboxId"/>')">Edit</a>
						   </display:column>
						</logic:equal>						
						<logic:equal name="crmRoles" property="available(view_qrc_adj,view_qrc_shifting)" value="true" scope="session">
							<display:column title="Assign to group" style="width:15%;" class="bold_${workFlowDataId.unRead}">
								<a href="javascript:changeBinOwner('0','<bean:write name="workFlowDataId" property="workflowId"/>','Personal','<bean:write name="workFlowDataId" property="workflowStage"/>','WORKFLOW','<bean:write name="workFlowDataId" property="requestType"/>')">Assign to Group</a>
						    </display:column>
						</logic:equal>
			</display:table>
			</c:if>
		<!-- Workflow(Shifting/Waiver) Inbox implementation Ends -->
		<logic:equal name="inboxForm" property="inboxType" value="Freeze">
			<c:set var="inboxType" value="CAF"></c:set>
				<display:table id="freezeDataIndividual" name="sessionScope.inboxForm.freezeInboxList" class="dataTable_Inbox" style="width:100%"  requestURI="" pagesize="10">
						<display:setProperty name="paging.banner.placement"	value="bottom" />
						<%--<display:column style="width:5%;" title="<strong>Select All</strong><input type='checkbox' title='Select All' id='selectIndAll' onclick='selectSelfAll(this)'/>">
							<html:checkbox name="inboxForm" property="selfIds" styleClass="self" value="${crfDataIndividual_rowNum}" styleId="selfCAF_${crfDataIndividual_rowNum}" onchange="InboxcheckData(this,'self')"></html:checkbox>
						</display:column>--%>
						<display:column title="CAF Number" style="width:8%;" class="bold_${freezeDataIndividual.unRead}" >
							<logic:equal name="crmRoles" property="available(view_freeze)" value="true" scope="session">
								<a href="javascript:viewCRF('<bean:write name="freezeDataIndividual" property="recordId"/>',
							     '<bean:write name="freezeDataIndividual" property="inboxId"/>','inbox')"><bean:write name="freezeDataIndividual" property="crfId"/>
								</a>
							</logic:equal>
						</display:column>
						<display:column title="Stage/Bin" style="width:10%;" class="bold_${freezeDataIndividual.unRead}">
							 <logic:notEmpty name="freezeDataIndividual" property="crfStage">
								<bean:write name="crmRoles" property="displayEnum(CRMOperationStages,${freezeDataIndividual.crfStage})" scope="session" />
							</logic:notEmpty>
						</display:column>
						<display:column title="Previous Stage/Bin" style="width:10%;" class="bold_${crfDataIndividual.unRead}">
							<logic:notEmpty name="freezeDataIndividual" property="crfPreviousStage">
								<bean:write name="crmRoles" property="displayEnum(CRMOperationStages,${freezeDataIndividual.crfPreviousStage})" scope="session" />
							</logic:notEmpty>
							<logic:empty name="freezeDataIndividual" property="crfPreviousStage">
							-
							</logic:empty>
						</display:column>
						<display:column title=" QRC Type /Docket Type" style="width:10%;" class="bold_${freezeDataIndividual.unRead}">
						   <c:out value="${inboxType}"/>
						</display:column >
						<display:column title= "Sub Sub Category" style="width:10%;" >
						-
						</display:column>
						<display:column title="Network Partner" style="width:10%;" class="bold_${freezeDataIndividual.unRead}">
						 <logic:notEqual name="freezeDataIndividual" property="npId" value="0">
						 <bean:write name="crmRoles" property="displayEnum(NP,${ freezeDataIndividual.npId})" scope="session" />
						 </logic:notEqual>
						  <logic:equal name="freezeDataIndividual" property="npId" value="0">
						-
						 </logic:equal>
						</display:column>
						<display:column title="Service Name" style="width:10%;" class="bold_${freezeDataIndividual.unRead}">
							 <logic:notEmpty name="freezeDataIndividual" property="product">
								<bean:write name="crmRoles" property="displayEnum(Product,${freezeDataIndividual.product})" scope="session" />
							</logic:notEmpty>
						</display:column>
						<display:column title="Created Date" style="width:10%;" class="bold_${freezeDataIndividual.unRead}">
							<bean:write name="crmRoles" property="xmlDate(${freezeDataIndividual.createdTime})" scope="session"/>
						</display:column>
				</display:table>	
		</logic:equal>
		</div>
		<div id="Group_Inbox" class="tabcontent">
	<!-- All inbox for group starts -->	
		<logic:equal name="inboxForm" property="inboxType" value="ALL_INBOX">
			<display:table id="allDataGroup" name="sessionScope.inboxForm.allGroupInboxList" class="dataTable_Inbox" style="width:100%"  requestURI="" pagesize="10">
						<display:setProperty name="paging.banner.placement"	value="bottom" />
						<display:column style="width:5%;" title="<strong>Select All</strong><input type='checkbox' title='Select All' id='selectgrpAll' onclick='selectGroupAll(this)'/>" class="${allDataGroup.lmsColor} ${allDataGroup.type eq 'QRC' ? allDataGroup.color : '' }">
							<html:checkbox  name="inboxForm" property="groupIds" styleClass="group" value="${allDataGroup_rowNum}" styleId="allInbox_${allDataGroup_rowNum}" onchange="InboxcheckData(this,'group')"></html:checkbox>
						</display:column>
						<display:column title="Lead ID/CAF Number/Ticket ID" style="width:8%;" class="${allDataGroup.lmsColor} ${allDataGroup.type eq 'QRC' ? allDataGroup.color : '' }">
							<logic:equal name="crmRoles" property="available(view_lead,view_ina,view_qrc_tkt,view_freeze)" value="true" scope="session">
								<a href="javascript:viewAll('<bean:write name="allDataGroup" property="lmsIdCrfRecordId"/>',
								'<bean:write name="allDataGroup" property="leadIdCrfIdTicketId"/>',
								'<bean:write name="allDataGroup" property="product"/>',
								'<bean:write name="allDataGroup" property="type"/>','inbox',0)"><bean:write name="allDataGroup" property="leadIdCrfIdTicketId"/></a>
							</logic:equal>
							<logic:notEqual name="crmRoles" property="available(view_lead,view_ina,view_qrc_tkt,view_freeze)" value="true" scope="session">
								<bean:write name="allDataGroup" property="leadIdCrfIdTicketId"/>
							</logic:notEqual>
						</display:column>
						
						<display:column title="Stage/Bin" style="width:8%;" class="${allDataGroup.lmsColor} ${allDataGroup.type eq 'QRC' ? allDataGroup.color : '' }">
						<logic:notEmpty name="allDataGroup" property="stage">
							<logic:equal name="allDataGroup" property="type" value="QRC">
						 		<bean:write name="crmRoles" property="displayEnum(functionalBins,${allDataGroup.stage})" scope="session" />
						 	</logic:equal>
						 	<logic:notEqual name="allDataGroup" property="type" value="QRC">
								<bean:write name="crmRoles" property="displayEnum(CRMOperationStages,${allDataGroup.stage})" scope="session" />
							</logic:notEqual>
						</logic:notEmpty>
						</display:column>
						<display:column title="Previous Stage/Bin" style="width:8%;" class="${allDataGroup.lmsColor} ${allDataGroup.type eq 'QRC' ? allDataGroup.color : '' }">
						<logic:notEmpty name="allDataGroup" property="previousStage">
							<logic:equal name="allDataGroup" property="type" value="QRC">
						 		<bean:write name="crmRoles" property="displayEnum(functionalBins,${allDataGroup.previousStage})" scope="session" />
						 	</logic:equal>
						 	<logic:notEqual name="allDataGroup" property="type" value="QRC">
								<bean:write name="crmRoles" property="displayEnum(CRMOperationStages,${allDataGroup.previousStage})" scope="session" />
							</logic:notEqual>
						</logic:notEmpty>
						<logic:empty name="allDataGroup" property="previousStage">
							-
						</logic:empty>
						</display:column>
						
						<display:column title="QRC Type /Docket Type" style="width:10%;" class="${allDataGroup.lmsColor} ${allDataGroup.type eq 'QRC' ? allDataGroup.color : '' }">
						<logic:equal  name="allDataGroup" property="type"  value="QRC">						
						  <bean:write name="crmRoles" property="displayEnum(qrcType,${ allDataGroup.requestType })"/>						
						</logic:equal>
						<logic:notEqual  name="allDataGroup" property="type"  value="QRC">
						<bean:write name="allDataGroup" property="requestType"/>
						</logic:notEqual>
						</display:column>
						
							<display:column title="Sub Sub Category" style="width:10%;" class="${allDataGroup.lmsColor} ${allDataGroup.type eq 'QRC' ? allDataGroup.color : '' }">
							
						<logic:notEmpty name="allDataGroup" property="subSubCategory"  >
						<bean:write name="allDataGroup" property="subSubCategory"/>
						</logic:notEmpty>
						<logic:empty  name="allDataGroup" property="subSubCategory">
						-
						</logic:empty >
						</display:column>
						
						<display:column title="Network Partner" style="width:10%;" class="bold_${allDataGroup.unRead} ${allDataGroup.lmsColor} ${allDataGroup.type eq 'QRC' ? allDataGroup.color : '' }">
						 <logic:notEqual name="allDataGroup" property="networkPartner" value="0">
						 <bean:write name="crmRoles" property="displayEnum(NP,${ allDataGroup.networkPartner})" scope="session" />
						 </logic:notEqual>
						  <logic:equal name="allDataGroup" property="networkPartner" value="0">-</logic:equal>
						</display:column>
						
						<display:column title="Service Name" style="width:10%;" class="${allDataGroup.lmsColor} ${allDataGroup.type eq 'QRC' ? allDataGroup.color : '' }">
						 <logic:notEmpty name="allDataGroup" property="product">
						 
								<bean:write name="crmRoles" property="displayEnum(Product,${allDataGroup.product})" scope="session" />
						</logic:notEmpty>
						<logic:empty name="allDataGroup" property="product">-</logic:empty >
						</display:column>
						
						<display:column title="Created Date" style="width:15%;" property="displayCreatedTime" class="${allDataGroup.lmsColor} ${allDataGroup.type eq 'QRC' ? allDataGroup.color : '' }"></display:column>
						<logic:equal name="crmRoles" property="available(update_lead,delete_lead,update_ina,delete_ina,update_qrc_tkt,delete_qrc_tkt)" value="true" scope="session">
							<display:column title="Assign to self" style="width:10%;" class="${allDataGroup.lmsColor} ${allDataGroup.type eq 'QRC' ? allDataGroup.color : '' }">
								<logic:empty name="inboxForm" property="userFunctionBin">
									<a href="javascript:changeBinOwner('<bean:write name="allDataGroup" property="lmsIdCrfRecordId"/>','<bean:write name="allDataGroup" property="leadIdCrfIdTicketId"/>','Group','<bean:write name="allDataGroup" property="stage"/>','<bean:write name="allDataGroup" property="type"/>','ALL_INBOX')">Assign to self</a>
								</logic:empty>
							</display:column>
						</logic:equal>
			</display:table>
		</logic:equal>
		<!-- All inbox for group ends -->
		<logic:equal name="inboxForm" property="inboxType" value="lead">
			<display:table id="leadDataGroup" name="sessionScope.inboxForm.lmsGroupInboxList" class="dataTable_Inbox" style="width:100%"  requestURI="" pagesize="10">
						<display:setProperty name="paging.banner.placement"	value="bottom" />
						<display:column style="width:5%;" title="<strong>Select All</strong><input type='checkbox' title='Select All' id='selectgrpAll' onclick='selectGroupAll(this)'/>" class="${leadDataGroup.lmsColor}">
							<html:checkbox name="inboxForm" property="groupIds" styleClass="group" value="${leadDataGroup_rowNum}" styleId="groupLead_${leadDataGroup_rowNum}" onchange="InboxcheckData(this,'Group')"></html:checkbox>
						</display:column>
						<display:column title="Lead ID" style="width:8%;" class="${leadDataGroup.lmsColor}">
							<logic:equal name="crmRoles" property="available(view_lead)" value="true" scope="session">
								<a href="javascript:viewLead('<bean:write name="leadDataGroup" property="lmsId"/>','inbox',0)"><bean:write name="leadDataGroup" property="leadId"/></a>
							</logic:equal>
							<logic:notEqual name="crmRoles" property="available(view_lead)" value="true" scope="session">
								<bean:write name="leadDataGroup" property="leadId"/>
							</logic:notEqual>
						</display:column>
						<display:column title="Stage/Bin" style="width:8%;" class="${leadDataGroup.lmsColor}">
						<logic:notEmpty name="leadDataGroup" property="lmsStage">
								<bean:write name="crmRoles" property="displayEnum(CRMOperationStages,${leadDataGroup.lmsStage})" scope="session" />
						</logic:notEmpty>
						</display:column>
						<display:column title="Previous Stage/Bin" style="width:10%;" class="${leadDataGroup.lmsColor}">
							<logic:notEmpty name="leadDataGroup" property="previousStage">
								<bean:write name="crmRoles" property="displayEnum(CRMOperationStages,${leadDataGroup.previousStage})" scope="session" />
							</logic:notEmpty>
							<logic:empty name="leadDataGroup" property="previousStage">
							-
							</logic:empty>
							</display:column>
						<display:column title=" QRC Type /Docket Type" style="width:10%;" class="bold_${leadDataGroup.unRead} ${leadDataGroup.lmsColor}">
						${fn:toUpperCase(fn:substring(inboxForm.inboxType, 0, 1))}${fn:toLowerCase(fn:substring(inboxForm.inboxType, 1,fn:length(inboxForm.inboxType)))}						
						</display:column >
						<display:column title="Sub Sub Category" style="width:10%;" class="bold_${leadDataGroup.unRead} ${leadDataGroup.lmsColor}">
						-
						</display:column >
						<display:column title="Network Partner" style="width:10%;" class="bold_${leadDataGroup.unRead} ${leadDataGroup.lmsColor}">
						-
						</display:column >
						
						<display:column title="Service Name" style="width:10%;" class="${leadDataGroup.lmsColor}">
						 <logic:notEmpty name="leadDataGroup" property="product">
								<bean:write name="crmRoles" property="displayEnum(Product,${leadDataGroup.product})" scope="session" />
						</logic:notEmpty>
						</display:column>
						
						<display:column title="Created Date" style="width:10%;" class="${leadDataGroup.lmsColor}">
							<bean:write name="crmRoles" property="xmlDate(${leadDataGroup.createdTime})" scope="session" />
						</display:column>
						<logic:equal name="crmRoles" property="available(update_lead,delete_lead)" value="true" scope="session">
							<display:column title="Assign to self" style="width:15%;" class="${leadDataGroup.lmsColor}">
								<logic:empty name="inboxForm" property="userFunctionBin">
									<a href="javascript:changeBinOwner('<bean:write name="leadDataGroup" property="lmsId"/>','<bean:write name="leadDataGroup" property="leadId"/>','Group','<bean:write name="leadDataGroup" property="lmsStage"/>','lead','lead')">Assign to self</a>
								</logic:empty>
							</display:column>
						</logic:equal>
			</display:table>
		</logic:equal>
		<logic:equal name="inboxForm" property="inboxType" value="CAF">
			<display:table id="crfDataGroup" name="sessionScope.inboxForm.crfGroupInboxList" class="dataTable_Inbox" style="width:100%"  requestURI="" pagesize="10">
						<display:setProperty name="paging.banner.placement"	value="bottom" />
						<display:column style="width:5%;" title="<strong>Select All</strong><input type='checkbox' title='Select All' id='selectgrpAll' onclick='selectGroupAll(this)'/>">
							<html:checkbox name="inboxForm" property="groupIds" styleClass="group" value="${crfDataGroup_rowNum}" styleId="groupCAF_${crfDataGroup_rowNum}" onchange="InboxcheckData(this,'Group')"></html:checkbox>
						</display:column>
						<display:column title="CAF Number" style="width:8%;">
							<logic:equal name="crmRoles" property="available(view_ina)" value="true" scope="session">
								<a href="javascript:viewCRF('<bean:write name="crfDataGroup" property="recordId"/>',
							     0,'inbox')"><bean:write name="crfDataGroup" property="crfId"/></a>
							</logic:equal>
							<logic:notEqual name="crmRoles" property="available(view_ina)" value="true" scope="session">
								<bean:write name="crfDataGroup" property="crfId"/>
							</logic:notEqual>
						</display:column>
						<display:column title="Stage/Bin" style="width:8%;">
						<logic:notEmpty name="crfDataGroup" property="crfStage">
								<bean:write name="crmRoles" property="displayEnum(CRMOperationStages,${crfDataGroup.crfStage})" scope="session" />
						</logic:notEmpty>
						</display:column>
						<display:column title="Previous Stage/Bin" style="width:10%;">
						<logic:notEmpty name="crfDataGroup" property="crfPreviousStage">
								<bean:write name="crmRoles" property="displayEnum(CRMOperationStages,${crfDataGroup.crfPreviousStage})" scope="session" />
						</logic:notEmpty>
						<logic:empty name="crfDataGroup" property="crfPreviousStage">
							-
						</logic:empty>
						</display:column>
						<display:column title=" QRC Type /Docket Type" style="width:10%;" class="bold_${crfDataGroup.unRead}">
						
						<bean:write name="inboxForm" property="inboxType" />
						</display:column >
						
						<display:column title= "Sub Sub Category" style="width:10%;" >
						
						-
						</display:column>
						<display:column title="Network Partner" style="width:10%;" class="bold_${crfDataGroup.unRead}">
						 <logic:notEqual name="crfDataGroup" property="npId" value="0">
						 <bean:write name="crmRoles" property="displayEnum(NP,${ crfDataGroup.npId})" scope="session" />
						 </logic:notEqual>
						  <logic:equal name="crfDataGroup" property="npId" value="0">
						-
						 </logic:equal>
						 
						</display:column>
						
						<display:column title="Service Name" style="width:10%;">
							<logic:notEmpty name="crfDataGroup" property="product">
								<bean:write name="crmRoles" property="displayEnum(Product,${crfDataGroup.product})" scope="session" />
						</logic:notEmpty> 
						</display:column>
						
						<display:column title="Created Date" style="width:15%;">
							<bean:write name="crmRoles" property="xmlDate(${crfDataGroup.createdTime})" scope="session" />
						</display:column>
						<logic:equal name="crmRoles" property="available(create_ina,update_ina,delete_ina)" value="true" scope="session">
							<display:column title="Assign to self" style="width:15%;">
								<logic:empty name="inboxForm" property="userFunctionBin">
									<a href="javascript:changeBinOwner('<bean:write name="crfDataGroup" property="recordId"/>','<bean:write name="crfDataGroup" property="crfId"/>','Group','<bean:write name="crfDataGroup" property="crfStage"/>','CAF','CAF')">Assign to self</a>
								</logic:empty>
							</display:column>
						</logic:equal>
			</display:table>
		</logic:equal>
		<!-- QRC Group Inbox Implementation Starts -->
		<logic:equal name="inboxForm" property="inboxType" value="QRC">
			<display:table id="qrcDataGroup" name="sessionScope.inboxForm.qrcGroupInboxList" class="dataTable_Inbox" style="width:100%"  requestURI="" pagesize="10">
						<display:setProperty name="paging.banner.placement"	value="bottom" />
						<display:column style="width:5%;" title="<strong>Select All</strong><input type='checkbox' title='Select All' id='selectgrpAll' onclick='selectGroupAll(this)'/>" class="${ qrcDataGroup.color}">
							<html:checkbox name="inboxForm" property="groupIds" styleClass="group" value="${qrcDataGroup_rowNum}" styleId="groupQRC_${qrcDataGroup_rowNum}" onchange="InboxcheckData(this,'Group')"></html:checkbox>
						</display:column>
						<display:column title="Ticket ID" style="width:8%;" class="${ qrcDataGroup.color}">
						<logic:equal name="crmRoles" property="available(view_qrc_tkt)" value="true" scope="session">
								<a href="javascript:viewQRC('<bean:write name="qrcDataGroup" property="ticketId"/>',0,'inbox','<bean:write name="qrcDataGroup" property="inboxId"/>')"><bean:write name="qrcDataGroup" property="srId"/></a>
							</logic:equal>
							<logic:notEqual name="crmRoles" property="available(view_qrc_tkt)" value="true" scope="session">
								<bean:write name="qrcDataGroup" property="srId"/>
							</logic:notEqual>
					 	</display:column>
						<display:column title="Stage/Bin" style="width:10%;" class="${ qrcDataGroup.color}">
							<c:if test="${ (qrcDataGroup.moduleType eq 'QRC' || qrcDataGroup.moduleType  eq 'LMS') }">
								<logic:notEmpty name="qrcDataGroup" property="functionalbinId">
									<bean:write name="crmRoles" property="displayEnum(functionalBins,${qrcDataGroup.functionalbinId})" scope="session" />
								</logic:notEmpty>							
							</c:if>							 
						</display:column>
						<display:column title="Previous Stage/Bin" style="width:10%;" class="${ qrcDataGroup.color}">
							<logic:notEmpty name="qrcDataGroup" property="previousStage">
								<bean:write name="crmRoles" property="displayEnum(functionalBins,${qrcDataGroup.previousStage})" scope="session" />
							</logic:notEmpty>
							<logic:empty name="qrcDataGroup" property="previousStage">
							-
							</logic:empty>
						</display:column>
						<display:column title=" QRC Type /Docket Type" style="width:10%;" class="bold_${qrcDataGroup.unRead} ${ qrcDataGroup.color}">
						  <bean:write name="crmRoles" property="displayEnum(qrcType,${ qrcDataGroup.qrcType })"/>
						
						</display:column >
						
						<display:column title= "Sub Sub Category" style="width:10%;"  property="qrcSubSubCategory" class="${ qrcDataGroup.color}" >
						
					
						</display:column>
						
						<display:column title="Network Partner" style="width:10%;" class="bold_${qrcDataGroup.unRead} ${ qrcDataGroup.color}">
						 <bean:write name="crmRoles" property="displayEnum(NP,${ qrcDataGroup.customerDetailsPojo.npId })"/>
						</display:column>
						
						
						<display:column title="Service Name" style="width:10%;" class="${ qrcDataGroup.color}">
						 <logic:notEmpty name="qrcDataGroup" property="customerDetailsPojo">
							 <logic:notEmpty name="qrcDataGroup" property="customerDetailsPojo.product">
									<bean:write name="crmRoles" property="displayEnum(Product,${qrcDataGroup.customerDetailsPojo.product})" scope="session" />
							</logic:notEmpty>
							 <logic:empty name="qrcDataGroup" property="customerDetailsPojo.product">
								 -
							 </logic:empty>
						  </logic:notEmpty>
						  <logic:notEmpty name="qrcDataGroup" property="lmsPojo">
							  <logic:notEmpty name="qrcDataGroup" property="lmsPojo.product">
									<bean:write name="crmRoles" property="displayEnum(Product,${qrcDataGroup.lmsPojo.product})" scope="session" />
							 </logic:notEmpty>
							 <logic:empty name="qrcDataGroup" property="lmsPojo.product">
								 -
							 </logic:empty>
						 </logic:notEmpty>
						</display:column>
						
						<display:column title="Created Date" style="width:15%;" class="${ qrcDataGroup.color}" >
							<bean:write name="crmRoles" property="xmlDate(${qrcDataGroup.createdTime})" scope="session" />
						</display:column>
						<logic:equal name="crmRoles" property="available(update_qrc_tkt,delete_qrc_tkt)" value="true" scope="session">
						<display:column title="Assign to Self" style="width:15%;" class="${ qrcDataGroup.color}">
						<logic:empty name="inboxForm" property="userFunctionBin">
						<a href="javascript:changeBinOwner('<bean:write name="qrcDataGroup" property="ticketId"/>','<bean:write name="qrcDataGroup" property="srId"/>','Group','<bean:write name="qrcDataGroup" property="functionalbinId"/>','QRC','QRC')">Assign to self</a>
						</logic:empty>
						</display:column>
						</logic:equal>
			</display:table>
		</logic:equal>
		<!-- QRC Group Inbox Implementation Ends -->
		<!-- Workflow(Shifting/Waiver) Group Inbox implementation Starts -->
		<c:if test="${ inboxForm.inboxType eq 'Waiver' or inboxForm.inboxType eq 'Shifting'}" >
			<display:table id="workFlowGroupDataId" name="sessionScope.inboxForm.workflowGroupInboxList" class="dataTable_Inbox" style="width:100%"  requestURI="" pagesize="10">
						<display:setProperty name="paging.banner.placement"	value="bottom" />
						<display:column style="width:5%;" title="<strong>Select All</strong><input type='checkbox' title='Select All' id='selectgrpAll' onclick='selectGroupAll(this)'/>">
							<html:checkbox  name="inboxForm" property="groupIds" styleClass="group" value="${workFlowGroupDataId_rowNum}" styleId="groupWF_${workFlowGroupDataId_rowNum}" onchange="InboxcheckData(this,'Group')"></html:checkbox>
						</display:column>
						<display:column title="Workflow ID" style="width:8%;">
						<logic:equal name="crmRoles" property="available(view_qrc_adj,view_qrc_shifting)" value="true" scope="session">
								<a href="javascript:viewWorkflow('<bean:write name="workFlowGroupDataId" property="workflowId"/>',
							     '<bean:write name="workFlowGroupDataId" property="requestType"/>','0')"><bean:write name="workFlowGroupDataId" property="workflowId"/></a>
							</logic:equal>
							<logic:notEqual name="crmRoles" property="available(view_qrc_adj,view_qrc_shifting)" value="true" scope="session">
								<bean:write name="workFlowGroupDataId" property="workflowId"/>
							</logic:notEqual>
					 	</display:column>
						<display:column title="Stage/Bin" style="width:10%;">
						 <logic:notEmpty name="workFlowGroupDataId" property="workflowStage">
						 <logic:equal name="workFlowGroupDataId" property="requestType"  value="Waiver">
								<bean:write name="crmRoles" property="displayEnum(functionalBins,${workFlowGroupDataId.workflowStage})" scope="session" />
						</logic:equal>
						<logic:notEqual name="workFlowGroupDataId" property="requestType"  value="Waiver">
								<bean:write name="crmRoles" property="displayEnum(CRMOperationStages,${workFlowGroupDataId.workflowStage})" scope="session" />
						</logic:notEqual>
						</logic:notEmpty>
						</display:column>
						<display:column title="Previous Stage/Bin" style="width:10%;" >
						<logic:notEmpty name="workFlowGroupDataId" property="previousStage">
						 <logic:equal name="workFlowGroupDataId" property="requestType"  value="Waiver">
								<bean:write name="crmRoles" property="displayEnum(functionalBins,${workFlowGroupDataId.previousStage})" scope="session" />
						</logic:equal>
						 <logic:notEqual name="workFlowGroupDataId" property="requestType"  value="Waiver">
								<bean:write name="crmRoles" property="displayEnum(CRMOperationStages,${workFlowGroupDataId.previousStage})" scope="session" />
						</logic:notEqual>
						</logic:notEmpty>
						<logic:empty name="workFlowGroupDataId" property="previousStage">
							-
						</logic:empty>
						</display:column>
						<display:column title="Customer ID" style="width:10%;" property="customerId" ></display:column>
						<display:column title="Request Type" style="width:10%;" property="requestType"></display:column>
						<display:column title="Workflow Type" style="width:10%;" property="workflowType"></display:column>
						
						
						<display:column title="Created Date" style="width:10%;">
							<bean:write name="crmRoles" property="xmlDate(${workFlowGroupDataId.createdTime})" scope="session"/>
						</display:column>
						
						<logic:equal name="crmRoles" property="available(view_qrc_adj,view_qrc_shifting)" value="true" scope="session">
							<display:column title="Assign to group" style="width:15%;" class="bold_${workFlowDataId.unRead}">
								<a href="javascript:changeBinOwner('0','<bean:write name="workFlowGroupDataId" property="workflowId"/>','Group','<bean:write name="workFlowGroupDataId" property="workflowStage"/>','WORKFLOW','<bean:write name="workFlowGroupDataId" property="requestType"/>')">Assign to Self</a>
						    </display:column>
						</logic:equal>
			</display:table>
		</c:if>
		<!-- Workflow(Shifting/Waiver) Group Inbox implementation Ends -->
			<logic:equal name="inboxForm" property="inboxType" value="Freeze">
			<c:set var="inboxType" value="CAF"></c:set>
				<display:table id="freezeGroupDataId" name="sessionScope.inboxForm.freezeGroupInboxList" class="dataTable_Inbox" style="width:100%"  requestURI="" pagesize="10">
						<display:setProperty name="paging.banner.placement"	value="bottom" />
						<%--<display:column style="width:5%;" title="<strong>Select All</strong><input type='checkbox' title='Select All' id='selectIndAll' onclick='selectSelfAll(this)'/>">
							<html:checkbox name="inboxForm" property="selfIds" styleClass="self" value="${crfDataIndividual_rowNum}" styleId="selfCAF_${crfDataIndividual_rowNum}" onchange="InboxcheckData(this,'self')"></html:checkbox>
						</display:column>--%>
						<display:column title="CAF Number" style="width:8%;" class="bold_${freezeGroupDataId.unRead}" >
							<logic:equal name="crmRoles" property="available(view_freeze)" value="true" scope="session">
								<a href="javascript:viewCRF('<bean:write name="freezeGroupDataId" property="recordId"/>',
							     '<bean:write name="freezeGroupDataId" property="inboxId"/>','inbox')"><bean:write name="freezeGroupDataId" property="crfId"/>
								</a>
							</logic:equal>
						</display:column>
						<display:column title="Stage/Bin" style="width:10%;" class="bold_${freezeGroupDataId.unRead}">
							 <logic:notEmpty name="freezeGroupDataId" property="crfStage">
								<bean:write name="crmRoles" property="displayEnum(CRMOperationStages,${freezeGroupDataId.crfStage})" scope="session" />
							</logic:notEmpty>
						</display:column>
						<display:column title="Previous Stage/Bin" style="width:10%;" class="bold_${freezeGroupDataId.unRead}">
							<logic:notEmpty name="freezeGroupDataId" property="crfPreviousStage">
								<bean:write name="crmRoles" property="displayEnum(CRMOperationStages,${freezeGroupDataId.crfPreviousStage})" scope="session" />
							</logic:notEmpty>
							<logic:empty name="freezeGroupDataId" property="crfPreviousStage">
							-
							</logic:empty>
						</display:column>
						<display:column title=" QRC Type /Docket Type" style="width:10%;" class="bold_${freezeGroupDataId.unRead}">
						  <c:out value="${inboxType}"/>
						</display:column >
						<display:column title= "Sub Sub Category" style="width:10%;" >
						-
						</display:column>
						<display:column title="Network Partner" style="width:10%;" class="bold_${freezeGroupDataId.unRead}">
						 <logic:notEqual name="freezeGroupDataId" property="npId" value="0">
						 <bean:write name="crmRoles" property="displayEnum(NP,${ freezeGroupDataId.npId})" scope="session" />
						 </logic:notEqual>
						  <logic:equal name="freezeGroupDataId" property="npId" value="0">
						-
						 </logic:equal>
						</display:column>
						<display:column title="Service Name" style="width:10%;" class="bold_${freezeGroupDataId.unRead}">
							 <logic:notEmpty name="freezeGroupDataId" property="product">
								<bean:write name="crmRoles" property="displayEnum(Product,${freezeGroupDataId.product})" scope="session" />
							</logic:notEmpty>
						</display:column>
						<display:column title="Created Date" style="width:10%;" property="createdTime" class="bold_${freezeGroupDataId.unRead}">
							<bean:write name="crmRoles" property="xmlDate(${freezeGroupDataId.createdTime})" scope="session" />
						</display:column>
				</display:table>	
		</logic:equal>
		</div>
	</div>
	<p class="clear margintop10" align="center">
      <span hidden="true" id="assignSelfButton"> <html:link href="#" styleClass="yellow_button" onclick="javascript:assignMultipleBin('${inboxType }','Personal');">Assign to Group </html:link></span>
      <span hidden="true" id="assignGroupButton"> <html:link href="#" styleClass="yellow_button" onclick="javascript:assignMultipleBin('${inboxType }','Group');">Assign to Self</html:link></span>
	</p>
  </div>
  	<html:hidden name="inboxForm" property="rowID" styleId="selectedRowID" />
  	<html:hidden name="inboxForm" property="elementID" styleId="selectedElementID" />
  	<html:hidden name="inboxForm" property="inboxOwner" styleId="inboxOwnerValue" />
  	<html:hidden name="inboxForm" property="currentStage" styleId="selectedElementStage" />
  	<html:hidden name="inboxForm" property="binChangeInbox" styleId="selectedElementInbox" />
  	<html:hidden name="inboxForm" property="inboxType" styleId="selectedInboxType" />
 </html:form>
</div>

<!-------------------  Light box  -------------------------------->
			<div id="lightbox-panel">
      			 <div id="pop_up" class="popUp"> 
     			<div class="closePopup"><a id="close-panel" href="#"></a></div>
      						<div id="rowDisplayData" class="popUpContainer innerPopupContainer"></div>
      			</div>			
   			</div>
	<div id="lightbox"> </div>
</body>
</html>
