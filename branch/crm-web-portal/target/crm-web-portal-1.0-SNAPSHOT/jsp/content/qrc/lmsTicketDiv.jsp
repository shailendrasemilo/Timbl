<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib uri="/WEB-INF/tlds/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/tlds/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/tlds/displaytag.tld" prefix="display"%>
<%@taglib uri="/WEB-INF/tlds/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/tlds/struts-logic.tld" prefix="logic"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<logic:notEmpty name="qrcForm" property="srTicketPojo">
	<li class="table_header">
		<span> Ticket No.:&nbsp; ${ qrcForm.srTicketPojo.srId }</span> 
		<span style="padding-left: 100px;">Lead ID:&nbsp;${ empty
			qrcForm.srTicketPojo.mappingId ? '-' : qrcForm.srTicketPojo.mappingId }
		</span> 
		<span style="padding-left: 130px;">Ticket Status: &nbsp; <bean:write
				name="crmRoles" property="displayEnum(qrcTicketStatus,${qrcForm.srTicketPojo.status})" />
		</span> 
		<c:if
			test="${ qrcForm.srTicketPojo.status eq 'R' and  qrcForm.srTicketPojo.resolutionType == 1}">
			<span class="addbtn_role_group" style="right: -89px; top: -3px; border: none"> <a
				href="javascript:change_TicketStatus('<bean:write name="qrcForm" property="srTicketPojo.ticketId" />');" id="idAddRca" class="grey_button_add"><span>Reopen</span></a></span>
		</c:if>
	</li>
	
<logic:notEmpty name="qrcForm" property="lmsPojo">
	<li class="table_container"><label>Name:</label> 
		<span>${qrcForm.lmsPojo.customerName }</span> 
		<label>RMN:</label>
		<span> ${qrcForm.lmsPojo.contactNumber }</span> 
		<label>Status:</label> 
		<span> <bean:write name="crmRoles" property="displayEnum(AllStatus,${ qrcForm.lmsPojo.status })" scope="session" />	</span>
	</li>

	<li class="table_container"><label>LMS Stage:</label> 
		<span> 
			<logic:notEmpty name="qrcForm" property="lmsPojo.lmsStage" >
					<bean:write name="crmRoles" property="displayEnum(CRMOperationStages,${qrcForm.lmsPojo.lmsStage})" scope="session" />
			</logic:notEmpty>
			<logic:empty name="qrcForm" property="lmsPojo.lmsStage" > - </logic:empty>
		</span> 
		<label>Lead Source:</label> 
		<span> 
			<logic:notEmpty name="qrcForm" property="lmsPojo.leadSource" >
					<bean:write name="crmRoles" property="displayEnum(CRMOperationStages,${qrcForm.lmsPojo.leadSource})" scope="session" />
			</logic:notEmpty>
			<logic:empty name="qrcForm" property="lmsPojo.leadSource" > - </logic:empty>
		</span> 
		<label>Service:</label> 
		<span><bean:write name="crmRoles" property="displayEnum(Product,${ qrcForm.lmsPojo.product })"scope="session" /></span>
	</li>
</logic:notEmpty>

	<li class="table_container">
		<label>Category:</label> 
		<span>${ qrcForm.srTicketPojo.qrcCategory }</span> 
		<label>Sub Category:</label> 
		<span>${qrcForm.srTicketPojo.qrcSubCategory }</span>
		<label>Sub Sub Category:</label> 
		<span>${ qrcForm.srTicketPojo.qrcSubSubCategory }</span>
	</li>

	<li class="table_container">
		<label>Type:</label> 
		<span> 
			<bean:write name="crmRoles"property="displayEnum(qrcType,${ qrcForm.srTicketPojo.qrcType })" />
		</span> 
		<label>Bin/ Owner:</label> 
		<span> 
			<logic:equal name="qrcForm" property="srTicketPojo.functionalbinId" value="0"> - </logic:equal> 
			<logic:notEqual	name="qrcForm" property="srTicketPojo.functionalbinId" value="0">
				<bean:write name="crmRoles" property="displayEnum(functionalBins,${ qrcForm.srTicketPojo.functionalbinId })" />
			</logic:notEqual> 
			<logic:notEmpty name="qrcForm" property="srTicketPojo.currentUser">${ qrcForm.srTicketPojo.currentUser }</logic:notEmpty>
			<logic:empty name="qrcForm" property="srTicketPojo.currentUser"> - </logic:empty>
		</span> 
		<label> Calling No.:</label> 
		<span>${ '0' == qrcForm.srTicketPojo.callingNo ? '-' : qrcForm.srTicketPojo.callingNo }</span>
	</li>

	<li class="table_container">
		<label>Raised By:</label> 
		<span>${ qrcForm.srTicketPojo.createdBy }</span> 
		<label>Raise Date:</label> <span>${qrcForm.srTicketPojo.displayCreatedTime }</span> 
		<label>Follow Up Date:</label> 
		<span> <bean:write name="crmRoles"property="xmlDate(${qrcForm.srTicketPojo.followupOn})" /></span>
	</li>

	<li class="table_container">
		<label>Resolved By:</label> 
		<span>${empty qrcForm.srTicketPojo.resolvedBy ? '-' : qrcForm.srTicketPojo.resolvedBy }</span> 
		<label>Resolved Date:</label> 
		<span><bean:write name="crmRoles" property="xmlDate(${qrcForm.srTicketPojo.srResolvedOn})" /></span> 
		<label> Reopen Date:</label> 
		<span><bean:write name="crmRoles" property="xmlDate(${qrcForm.srTicketPojo.srReopenedOn})" /></span>
	</li>

	<li class="table_container">
		<label>Action Taken:</label> 
		<span>${empty qrcForm.srTicketPojo.actionTaken ? '-' : qrcForm.srTicketPojo.actionTaken }</span>
		<label>Root Cause:</label> 
		<span>${empty qrcForm.srTicketPojo.rootCause ? '-' : qrcForm.srTicketPojo.rootCause}</span> 
		<label>Attribute To:</label> <span>${empty	qrcForm.srTicketPojo.attributedTo ? '-' : qrcForm.srTicketPojo.attributedTo}</span>
	</li>

	<li class="table_container">
		<label>SLA Time:</label> 
		<span>${ empty qrcForm.srTicketPojo.slaCalculateTime ? 'NA' : qrcForm.srTicketPojo.slaCalculateTime }</span> 
		<label>SLA Expiry :</label> 
		<span> 
			<logic:empty name="qrcForm" property="srTicketPojo.expectedSLATime">	NA </logic:empty>
			<logic:notEmpty name="qrcForm" property="srTicketPojo.expectedSLATime">
				<bean:write name="crmRoles" property="xmlDate(${qrcForm.srTicketPojo.expectedSLATime})" />
			</logic:notEmpty>
		</span> 
		<label></label><span></span>
	</li>

</logic:notEmpty>
