<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="/WEB-INF/tlds/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/tlds/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/tlds/displaytag.tld" prefix="display"%>
<%@taglib uri="/WEB-INF/tlds/struts-html.tld" prefix="html"%> 
<%@taglib uri="/WEB-INF/tlds/struts-logic.tld" prefix="logic"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
          <logic:notEmpty name="qrcForm" property="srTicketPojo" >
            <li class="table_header"> <span>
			Ticket No.:&nbsp;
			${ qrcForm.srTicketPojo.srId }</span>
			<span style="padding-left:100px;">Cutomer ID:&nbsp;${ empty qrcForm.srTicketPojo.mappingId ? '-' : qrcForm.srTicketPojo.mappingId }</span>
			 <span  style="padding-left:130px;">Ticket Status: &nbsp;
             <bean:write name="crmRoles" property="displayEnum(qrcTicketStatus,${qrcForm.srTicketPojo.status})"/></span> 
			 <c:if test="${ qrcForm.srTicketPojo.status eq 'R' and  qrcForm.srTicketPojo.resolutionType == 1}">
				<span class="addbtn_role_group" style=" right: -89px; top: -3px; border:none"> <a href="javascript:change_TicketStatus('<bean:write name="qrcForm" property="srTicketPojo.ticketId" />');" id="idAddRca" class="grey_button_add"><span>Reopen</span></a></span>
			  </c:if>
			</li>
            
            <li class="table_container">
              <label>Name:</label>
              <span >${ qrcForm.custDetailsPojo.custFname }
			  ${ qrcForm.custDetailsPojo.custLname }
			  </span>
              <label>RMN:</label>
              <span> ${ qrcForm.custDetailsPojo.rmn }</span> 
			   <label>Status:</label>
			   <span>
			     <bean:write name="crmRoles" property="displayEnum(AllStatus,${ qrcForm.custDetailsPojo.status })" scope="session" />
              </span> 
			  
			  </li>
              
            <li class="table_container">
              <label>MAC ID:</label>
              <span>${ empty qrcForm.networkConfigurationsPojo.currentCpeMacId ? '-' : qrcForm.networkConfigurationsPojo.currentCpeMacId }</span>
              <label>NASPort ID:</label>
               
              <span>
              <c:if test="${ qrcForm.custDetailsPojo.product eq 'EOC'|| qrcForm.custDetailsPojo.product eq 'RF'}">
              ${ qrcForm.partnerNetworkConfigPojo.nasPortId  }
              </c:if>
              <c:if test="${ qrcForm.custDetailsPojo.product ne 'EOC'and qrcForm.custDetailsPojo.product ne 'RF'}">
              -
              </c:if>
              </span>
				<label>Option 82:</label>
				
				  
              <span>
              <c:if test="${ qrcForm.custDetailsPojo.product eq 'BB' }">
              ${ qrcForm.networkConfigurationsPojo.option82 }
              </c:if>
               <c:if test="${ qrcForm.custDetailsPojo.product ne 'BB' }">
              -
              </c:if>
              </span>
			  </li>
              
            <li class="table_container">
              <label>CAF No:</label>
              <span>${ qrcForm.custDetailsPojo.crfId }</span>
              <label>CAF Date:</label>
              <span>
              <bean:write name="crmRoles" property="xmlDate(${ qrcForm.custDetailsPojo.crfDate})" scope="session"/>
              </span> 
			   <label>FT Approval Date:</label>
              <span><bean:write name="crmRoles" property="xmlDate(${qrcForm.custDetailsPojo.ftApprovalDate})"/></span> 
			  </li>
              
            <li class="table_container" >
             <label style="width:198px; "
			 >Plan Info:</label>
              <span style="padding-left: 3px; max-width: 530px; width: 75.5%;">
                <bean:write name="crmRoles" property="displayEnum(BASE,${ qrcForm.planDetailsPojo.basePlanCode })" scope="session" />
                <logic:notEmpty name="qrcForm" property="planDetailsPojo.addOnPlanCode"> 
					+ <bean:write name="crmRoles" property="displayEnum(ADDON,${ qrcForm.planDetailsPojo.addOnPlanCode })" scope="session" />
				</logic:notEmpty>;
                Rs <fmt:formatNumber type="number" minFractionDigits="0" maxFractionDigits="2" value="${ qrcForm.crmPlanMasterPojo.rentExclTax + (not empty qrcForm.crmAddonPlanMasterPojo ? qrcForm.crmAddonPlanMasterPojo.rentExclTax : 0)}" /> plus tax for
			   <c:choose>    
                <c:when test="${qrcForm.crmPlanMasterPojo.planUsageType eq 'L'}">
                    <c:if test="${qrcForm.custDetailsPojo.serviceType eq 'PO'}">
                     <fmt:formatNumber type="number" minFractionDigits="0" maxFractionDigits="2" value="${ qrcForm.crmPlanMasterPojo.totalDUL + (not empty qrcForm.crmAddonPlanMasterPojo.totalDUL ? qrcForm.crmAddonPlanMasterPojo.totalDUL : 0)}" /> 
	            	GB at
                      <fmt:formatNumber type="number" minFractionDigits="0" maxFractionDigits="2" value="${ qrcForm.crmPlanMasterPojo.primarySpeed div 1024 }" />&nbsp;Mbps
                    </c:if>
                    <c:if test="${qrcForm.custDetailsPojo.serviceType eq 'PR'}">
                      <fmt:formatNumber type="number" minFractionDigits="0" maxFractionDigits="2" value="${ qrcForm.crmPlanMasterPojo.totalDUL + (not empty qrcForm.crmAddonPlanMasterPojo.totalDUL ? qrcForm.crmAddonPlanMasterPojo.totalDUL : 0)}" /> 
	            	GB at
                      <fmt:formatNumber type="number" minFractionDigits="0" maxFractionDigits="2" value="${ qrcForm.crmPlanMasterPojo.primarySpeed div 1024 }" />&nbsp;Mbps
                    </c:if>
                    <%-- <fmt:formatNumber type="number" minFractionDigits="0" maxFractionDigits="2" value="${ qrcForm.crmPlanMasterPojo.primarySpeed div 1024 }" />&nbsp;Mbps; 
                    ${empty qrcForm.custAdditionalDetails.primaryQuotaInGB ? qrcForm.crmPlanMasterPojo.totalDULInGB : qrcForm.custAdditionalDetails.primaryQuotaInGB};
                    <bean:message key="limited.plan.info.postusage"/> --%>
                </c:when>
                <c:otherwise>
                	<c:if test="${ qrcForm.crmPlanMasterPojo.quotaType eq 'TUL' }">Unlimited</c:if>
            	  	<c:if test="${ qrcForm.crmPlanMasterPojo.quotaType ne 'TUL' }">
            			<fmt:formatNumber type="number" minFractionDigits="0" maxFractionDigits="2" value="${ qrcForm.crmPlanMasterPojo.totalDUL + (not empty qrcForm.crmAddonPlanMasterPojo.totalDUL ? qrcForm.crmAddonPlanMasterPojo.totalDUL : 0)}" /> 
            	    GB</c:if><c:if test="${qrcForm.crmPlanMasterPojo.quotaType eq 'MON' }">
            			(<fmt:formatNumber type="number" maxFractionDigits="1" value="${ qrcForm.crmPlanMasterPojo.totalDUL div 30 }" />GB/day)
            			</c:if> at
                	<fmt:formatNumber type="number" maxFractionDigits="0" value="${ qrcForm.crmPlanMasterPojo.primarySpeed div 1024 }" />&nbsp;Mbps
	                <c:if test="${ qrcForm.crmPlanMasterPojo.quotaType ne 'TUL' }">
		                , and Unlimited at
		                 <c:if test="${ qrcForm.crmPlanMasterPojo.secondarySpeed lt 1024 }">
					    	${qrcForm.crmPlanMasterPojo.secondarySpeed}&nbsp;Kbps
						</c:if>
						 <c:if test="${ qrcForm.crmPlanMasterPojo.secondarySpeed div 1024 ge 1 }">
							<fmt:formatNumber type="number" minFractionDigits="0" maxFractionDigits="2" value="${ qrcForm.crmPlanMasterPojo.secondarySpeed div 1024 }" />&nbsp;Mbps
						</c:if>
					</c:if>
                    <%--${empty qrcForm.custAdditionalDetails.primaryQuotaInGB ? qrcForm.crmPlanMasterPojo.totalDULInGB : qrcForm.custAdditionalDetails.primaryQuotaInGB} at
                    <fmt:formatNumber type="number" maxFractionDigits="0" value="${ qrcForm.crmPlanMasterPojo.primarySpeed div 1024 }" />&nbsp;Mbps, and Unlimited at
                    ${ qrcForm.crmPlanMasterPojo.secondarySpeed } Kbps
                     <fmt:formatNumber type="number" maxFractionDigits="0" value="${ qrcForm.crmPlanMasterPojo.primarySpeed div 1024 }" />&nbsp;Mbps upto 
                    ${empty qrcForm.custAdditionalDetails.primaryQuotaInGB ? qrcForm.crmPlanMasterPojo.totalDULInGB : qrcForm.custAdditionalDetails.primaryQuotaInGB};${ qrcForm.crmPlanMasterPojo.secondarySpeed } Kbps thereafter --%>
                </c:otherwise>
          </c:choose>
         
              </span>
			  
			  <label>Service:</label>
              <span><bean:write name="crmRoles" property="displayEnum(Product,${ qrcForm.custDetailsPojo.product })" scope="session"/></span> 
			  </li>
              
            <li class="table_container">
              <label>Category:</label>
              <span>${ qrcForm.srTicketPojo.qrcCategory }</span>
              <label>Sub Category:</label>
              <span>${ qrcForm.srTicketPojo.qrcSubCategory }</span> 
			    <label>Sub Sub Category:</label>
              <span>${ qrcForm.srTicketPojo.qrcSubSubCategory }</span> 
			  </li>
              
            <li class="table_container">
              <label>Type:</label>
              <span>
			  <bean:write name="crmRoles" property="displayEnum(qrcType,${ qrcForm.srTicketPojo.qrcType })"/>
			  </span>
              <label>Bin/ Owner:</label>
              <span> <logic:equal name="qrcForm" property="srTicketPojo.functionalbinId" value="0" >-</logic:equal>
              <logic:notEqual name="qrcForm" property="srTicketPojo.functionalbinId" value="0" ><bean:write name="crmRoles" property="displayEnum(functionalBins,${ qrcForm.srTicketPojo.functionalbinId })"/></logic:notEqual>
               / <logic:notEmpty name="qrcForm" property="srTicketPojo.currentUser">${ qrcForm.srTicketPojo.currentUser }</logic:notEmpty>
                <logic:empty name="qrcForm" property="srTicketPojo.currentUser">-</logic:empty>
			  </span>			 
			  <label> Calling No.:</label>
              <span>${ '0' == qrcForm.srTicketPojo.callingNo ? '-' : qrcForm.srTicketPojo.callingNo }</span> 
			  </li>
              
            <li class="table_container">
              <label>Raised By:</label>
              <span>${ qrcForm.srTicketPojo.createdBy }</span>
              <label>Raise Date:</label>
              <span>
              <bean:write name="crmRoles" property="xmlDate(${qrcForm.srTicketPojo.createdTime})" scope="session"/>
             </span> 
			  <label>Follow Up Date:</label>
              <span>
			  <bean:write name="crmRoles" property="xmlDate(${qrcForm.srTicketPojo.followupOn})" />
			  </span> 

			  </li>
              
            <li class="table_container">
              <label>Resolved By:</label>
              <span>${empty qrcForm.srTicketPojo.resolvedBy ? '-' : qrcForm.srTicketPojo.resolvedBy }</span>
              <label> Resolved Date:</label>
              <span><bean:write name="crmRoles" property="xmlDate(${qrcForm.srTicketPojo.srResolvedOn})" /></span> 
			 <label> Reopen Date:</label>
              <span>
			  <bean:write name="crmRoles" property="xmlDate(${qrcForm.srTicketPojo.srReopenedOn})" />
			  </span> 
			  
			  </li>
              
            <li class="table_container">
              <label>Action Taken:</label>
               <span>${empty qrcForm.srTicketPojo.actionTaken ? '-' : qrcForm.srTicketPojo.actionTaken }</span> 
              <label>Root Cause:</label>
              <span>${empty qrcForm.srTicketPojo.rootCause ? '-' :  qrcForm.srTicketPojo.rootCause}</span> 
			  
			  <label>Attribute To:</label>
              <span>${empty qrcForm.srTicketPojo.attributedTo ? '-' :  qrcForm.srTicketPojo.attributedTo}</span> 
			  </li>
			  
			  <li class="table_container">
				  <label>SLA Time:</label>
				  <span>${ empty qrcForm.srTicketPojo.slaCalculateTime ? 'NA' : qrcForm.srTicketPojo.slaCalculateTime }</span>
				  <label>SLA Expiry :</label>
				  <span>
					  <logic:empty name="qrcForm" property="srTicketPojo.expectedSLATime" >	NA </logic:empty >
					  <logic:notEmpty name="qrcForm" property="srTicketPojo.expectedSLATime" >
						<bean:write name="crmRoles" property="xmlDate(${qrcForm.srTicketPojo.expectedSLATime})"/> 
					  </logic:notEmpty >
				 </span>
				  <label></label><span></span>
			 </li>
			  
           <li class="table_container">
             <label style="width:132px; 
              ">Installation Address:</label>
               <span style="padding-left: 3px; max-width: 530px; width: 75.5%">${ qrcForm.billingAddressPojo.addLine1 }
			   ${ qrcForm.billingAddressPojo.addLine2 }
			   ${ qrcForm.billingAddressPojo.addLine3 } - ${ qrcForm.billingAddressPojo.pincode }
			   </span>
          </li>
         </logic:notEmpty>
          