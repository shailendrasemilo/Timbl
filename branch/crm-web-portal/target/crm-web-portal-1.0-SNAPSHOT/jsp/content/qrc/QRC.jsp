<%@page import="com.np.tele.crm.utils.DateUtils"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="/WEB-INF/tlds/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/tlds/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/tlds/displaytag.tld" prefix="display"%>
<%@taglib uri="/WEB-INF/tlds/struts-html.tld" prefix="html"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<link href="css/colorbox.css" rel="stylesheet" />
<style type="text/css">
#cboxTitle{
position: absolute; bottom: 479px; left: 10px; text-align: left; width: 100%; color: white; font: bold 14px arial;}
</style>
<script src="javascript/jquery.colorbox.js"></script>
<script>
	$( document ).ready(function(){
		$(".iframe3").colorbox({iframe:true, width:"1250px", height:"500px",titleShow:true});
		$( '.rmnArea #rmnid' ).addClass('simple');
	});
</script>
<%--    code from qrcMenu.jsp    --%>
<div class="loadingPopup hidden"></div>
<div id="section" >
 <div class="section">
      <logic:notEmpty name="qrcForm" property="custDetailsPojo.customerId">
     
      <jsp:include page="crfCustomerDescription.jsp"></jsp:include>
      
	     <%--  <h4>CAF ID:&nbsp;<a href="javascript:viewCRF('<bean:write name="qrcForm" property="custDetailsPojo.recordId"/>',0,'customerProfile')" title="View CAF Details"><bean:write name="qrcForm" property="custDetailsPojo.crfId"/></a>,&nbsp;Customer ID:&nbsp; <a href="manageQrc.do?method=searchCustomer&workingId=<bean:write name='qrcForm' property='custDetailsPojo.customerId'/>" title="View Customer Details"><bean:write name="qrcForm" property="custDetailsPojo.customerId"/></a></h4>--%>
	  </logic:notEmpty>
   <div class="inner_section" style="margin-top: -25px">
 	<div class="inner_left_lead floatl  qrcLeft">
        <bean:define id="activeMenu" value="customerEnquiry"></bean:define>
        <%@include file="qrcMenu.jsp"%>
           </div>
<%--    end code from qrcMenu.jsp    --%>

<div class="inner_left_lead floatl manageGISRight qrcRight" >
   <html:form action="/manageQrc" styleId="qrcCustomerProfileForm">
   <input type="hidden" id="hCustRecId" value="${ qrcForm.custDetailsPojo.recordId }">
   <input type="hidden" id="hCustId" value="${ qrcForm.custDetailsPojo.customerId }">
   <input type="hidden" id="hInstAddId" value="${ qrcForm.installationAddressPojo.recordId }">
   <input type="hidden" id="hBillAddId" value="${ qrcForm.billingAddressPojo.recordId }">
   <input type="hidden" id="hOrderId" value="${ qrcForm.orderDetailsPojo.recordId }">
   <input type="hidden" id="hPlanId" value="${ qrcForm.planDetailsPojo.recordId }">
   <input type="hidden" id="hNetCfgId" value="${ qrcForm.networkConfigurationsPojo.recordId }">
   <input type="hidden" id="hNationalityId" value="${ qrcForm.nationalityDetailsPojo.recordId }">
   <input type="hidden" id="hCRMUserId" value="${ qrcForm.crmUserId }">
   <input type="hidden" id="hConnType" value="${ qrcForm.custDetailsPojo.connectionType }">
   <input type="hidden" id="hBrandName" value="${ qrcForm.custDetailsPojo.brand }">
    <html:hidden  name="qrcForm" property="customerId" value="${qrcForm.custDetailsPojo.customerId}"/>
    <input type="hidden" id="hPaymentId" value="${ qrcForm.crmPaymentDetailsPojo.paymentId }">

  <div class="mWidth900">
    <div class="success_message">
      <logic:messagesPresent message="true">
        <html:messages id="message" message="true">
          <bean:write name="message" />
        </html:messages>
      </logic:messagesPresent>
    </div>
    <div class="error_message" id="error"><html:errors /></div>
   <logic:equal name="crmRoles" property="available(update_qrc_cp)" value="true" scope="session">
      <c:if test="${ not(empty qrcForm.custDetailsPojo.activationDate) and (qrcForm.custDetailsPojo.status ne 'PD') and (qrcForm.custDetailsPojo.brand ne 'INITIA') }">
        <a href="manageQrc.do?method=resetMyAccountPassword" class="yellow_button floatr">Reset My Account Password</a>
      </c:if>
    </logic:equal>
    <c:if test="${ not(empty qrcForm.custDetailsPojo.activationDate) and (qrcForm.custDetailsPojo.status eq 'T') and (qrcForm.custAdditionalDetails.balance ge '100' ) and (qrcForm.custDetailsPojo.serviceType eq 'PO' ) }"> <%-- //TODO add check for outstanding amount 100 --%>
      <logic:equal name="crmRoles" property="available(update_qrc_slm)" value="true" scope="session">
      <a href="manageQrc.do?method=sendLegalMail" class="yellow_button floatr">Send Legal Mail</a>
      </logic:equal>
    </c:if>
    <p class="clear"></p>
  </div>
  
  <div class="customerInfo">
    <ul>
      <li class="customerHead">Customer Details</li>
      <li>  
       
        <label>${ (qrcForm.custDetailsPojo.connectionType eq 'Ind' || qrcForm.custDetailsPojo.connectionType eq 'Prop') ? 'Customer Name:' : 'Company Name:' }</label>
        <span class="width450 customerNameArea"> <font  id="customerName">${ qrcForm.custDetailsPojo.custFname } 
        <c:if test="${ (qrcForm.custDetailsPojo.connectionType eq 'Ind' || qrcForm.custDetailsPojo.connectionType eq 'Prop') }">${ qrcForm.custDetailsPojo.custLname }</c:if></font>
          <div class="namePopup hidden">
            <div>
              <label>${ (qrcForm.custDetailsPojo.connectionType eq 'Ind' || qrcForm.custDetailsPojo.connectionType eq 'Prop') ? 'First Name:' : 'Company Name:' }</label>
              <input name="custFname" class="Lmstextbox firstName" maxlength="${ (qrcForm.custDetailsPojo.connectionType eq 'Ind' || qrcForm.custDetailsPojo.connectionType eq 'Prop') ? '50' : '200' }"
                value="${ qrcForm.custDetailsPojo.custFname }" type="text" id="lmstextbox" onkeyup="javascript:changeToUpperCase(this)" />
            </div>
            <c:if test="${ (qrcForm.custDetailsPojo.connectionType eq 'Ind' || qrcForm.custDetailsPojo.connectionType eq 'Prop') }">
              <div>
                <label>Last Name</label><input name="custLname" class="Lmstextbox lastName" maxlength="50" onkeyup="javascript:changeToUpperCase(this)"
                  value="${ qrcForm.custDetailsPojo.custLname }" type="text" id="lmstextbox" />
              </div>
            </c:if>
            <div class="saveCanelButtons">
              <a href="javascript:void(0);" id="cancelButton"></a>
           <c:if test="${crmRoles.getAvailable('update_qrc_nmchng') eq 'true' and (not empty qrcForm.custDetailsPojo.activationDate) and (qrcForm.custDetailsPojo.status ne 'PD')}">
              <a href="javascript:void(0);" id="saveButton"></a>
    	   </c:if>
            </div>
          </div>
         <c:if test="${crmRoles.getAvailable('update_qrc_nmchng') eq 'true' and (not empty qrcForm.custDetailsPojo.activationDate) and (qrcForm.custDetailsPojo.status ne 'PD')}">
          <a href="javascript:void(0);" id="updateCustomerName">edit</a>
          </c:if>
        </span>  
            <c:if test="${ qrcForm.custDetailsPojo.connectionType ne 'Ind' and qrcForm.custDetailsPojo.connectionType ne 'Prop' }">

          <label class="qrcHlbl">Authorised Person:</label>
           <span class="width172 contactPersonArea qrcHlbl" style="margin-left:-3px; height: 14px; padding:5px 5px;"><font id="contactPerson" >${ empty qrcForm.custDetailsPojo.authSignFname ? '-' : qrcForm.custDetailsPojo.authSignFname } ${ qrcForm.custDetailsPojo.authSignLname }</font>
            <div class="contactPopup hidden">
              <div><label>First Name</label> 
              <input name="contFname" type="text" maxlength="45" class="Lmstextbox firstName" value="${ qrcForm.custDetailsPojo.authSignFname }" id="lmstextbox" onkeyup="javascript:changeToUpperCase(this)"> </div>
              <div><label>Last Name </label> 
              <input name="contLname" type="text" maxlength="45" class="Lmstextbox lastName" value="${ qrcForm.custDetailsPojo.authSignLname }" id="lmstextbox" onkeyup="javascript:changeToUpperCase(this)"> </div>
              <div class="saveCanelButtons"> <a href="javascript:void(0);" id="cancelButton"></a> 
            <c:if test="${crmRoles.getAvailable('update_qrc_nmchng') eq 'true' and (not empty qrcForm.custDetailsPojo.activationDate) and (qrcForm.custDetailsPojo.status ne 'PD')}">
              <a href="javascript:void(0);" id="saveButton"></a> 
            </c:if>
              </div>
            </div>
            <c:if test="${crmRoles.getAvailable('update_qrc_nmchng') eq 'true' and (not empty qrcForm.custDetailsPojo.activationDate) and (qrcForm.custDetailsPojo.status ne 'PD')}">
              <a href="javascript:void(0);" id="updateContactPerson">edit</a>
            </c:if>
          </span>

      </c:if>
	  <c:if test="${ qrcForm.custDetailsPojo.nationality eq 'Other'}">

        <%--   <label style="background: #B9E6F5;">Authorised Person:</label>
            <span class="width172 contactPersonArea" style="background: #FFD07A; margin-left:-3px; height: 14px; padding:5px 5px;"><font id="localContactPerson">${ empty qrcForm.nationalityDetailsPojo.localCpFname ? '-' : qrcForm.nationalityDetailsPojo.localCpFname } ${ qrcForm.nationalityDetailsPojo.localCpLname }</font>
            <div class="contactPopup hidden">
              <div><label>First Name</label> 
              <input name="contFname" type="text" maxlength="45" class="Lmstextbox firstName" value="${ qrcForm.nationalityDetailsPojo.localCpFname }" id="lmstextbox" onkeyup="javascript:changeToUpperCase(this)"> </div>
              <div><label>Last Name </label> 
              <input name="contLname" type="text" maxlength="45" class="Lmstextbox lastName" value="${ qrcForm.nationalityDetailsPojo.localCpLname }" id="lmstextbox" onkeyup="javascript:changeToUpperCase(this)"> </div>
              <div class="saveCanelButtons"> <a href="javascript:void(0);" id="cancelButton"></a> 
            <c:if test="${crmRoles.getAvailable('update_qrc_nmchng') eq 'true' and (not empty qrcForm.custDetailsPojo.activationDate) and (qrcForm.custDetailsPojo.status ne 'PD')}">
              <a href="javascript:void(0);" id="saveButton"></a> 
            </c:if>
              </div>
            </div>
            <c:if test="${crmRoles.getAvailable('update_qrc_nmchng') eq 'true' and (not empty qrcForm.custDetailsPojo.activationDate) and (qrcForm.custDetailsPojo.status ne 'PD')}">
              <a href="javascript:void(0);" id="localUpdateContactPerson">edit</a>
            </c:if>
          </span>--%>

      </c:if>
	  
	  </li>
	  <li>
		<label>Plan Details:</label>
		<span class="width450"> <bean:write name="crmRoles" property="displayEnum(BASE,${ qrcForm.planDetailsPojo.basePlanCode })" scope="session" />
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
                <%--${ qrcForm.crmPlanMasterPojo.secondarySpeed } Kbps--%>
                <%-- <fmt:formatNumber type="number" maxFractionDigits="0" value="${ qrcForm.crmPlanMasterPojo.primarySpeed div 1024 }" />&nbsp;Mbps
                <c:if test="${qrcForm.crmPlanMasterPojo.primaryQuota lt 0 }">Unlimited</c:if>
                <c:if test="${qrcForm.crmPlanMasterPojo.primaryQuota gt -1 }">
                  upto 
                  ${empty qrcForm.custAdditionalDetails.primaryQuotaInGB ? qrcForm.crmPlanMasterPojo.totalDULInGB : qrcForm.custAdditionalDetails.primaryQuotaInGB};
                  ${ qrcForm.crmPlanMasterPojo.secondarySpeed } Kbps thereafter
                </c:if> --%>
            </c:otherwise>
          </c:choose>
		</span>
		<label class="qrcHlbl">Service:</label>
         <span class="width172 qrcHlbl" style="margin-left:-3px; height: 14px; padding:5px 5px;letter-spacing: -1px;">
		<bean:write name="crmRoles" property="displayEnum(Product,${ qrcForm.custDetailsPojo.product })" scope="session"/> &nbsp;<bean:write name="crmRoles" property="displayEnum(ServiceType,${ qrcForm.custDetailsPojo.serviceType })" scope="session" />&nbsp;&nbsp;${qrcForm.custDetailsPojo.brand}
		</span>
	  </li>
	  <li>
        <label>Email ID:</label>
        <span class="emailArea" style="width:445px;"> <font  id="email" class="${ qrcForm.custDetailsPojo.emailValidationFlag eq 'Y' ? 'green' : 'red' }">${ empty qrcForm.custDetailsPojo.custEmailId ? '-' : qrcForm.custDetailsPojo.custEmailId }</font>
            <input class="Lmstextbox hidden width220" name="custEmail" type="text" id="lmstextbox" value="${ qrcForm.custDetailsPojo.custEmailId }" maxlength="256"/>
            <c:if test="${crmRoles.getAvailable('update_qrc_cp') eq 'true' and (not empty qrcForm.custDetailsPojo.activationDate) and (qrcForm.custDetailsPojo.status ne 'PD')}">
       		 <a id="updateEmail" href="javascript:void(0);">edit</a>
             <c:if test="${(qrcForm.custDetailsPojo.emailValidationFlag ne 'Y') and (qrcForm.custDetailsPojo.brand ne 'INITIA') }">
             	 <a href="#" id="sendVerificationEmail">Send Verification Link</a>
             </c:if>
            </c:if>
            <div class="saveCanelButtons hidden"> <a href="javascript:void(0);" id="cancelButton"></a>
          <c:if test="${crmRoles.getAvailable('update_qrc_cp') eq 'true' and (not empty qrcForm.custDetailsPojo.activationDate) and (qrcForm.custDetailsPojo.status ne 'PD')}">
  		    <a href="javascript:void(0);" id="saveButton"></a>
          </c:if>
  		  </div>
        </span>
		<span class="width165"> 
		   <span class="qrcHlbl" style="height: 14px; padding:5px 5px; width:298px;"> <bean:write name="crmRoles" property="displayEnum(AllStatus,${ qrcForm.custDetailsPojo.status })" scope="session"/>/&nbsp;
		  <c:if test="${ qrcForm.custDetailsPojo.status ne 'B'}">
		  <bean:write name="crmRoles" property="displayEnum(CRMOperationStages,${ qrcForm.custDetailsPojo.crfStage })"/></c:if>/&nbsp;${ empty qrcForm.custDetailsPojo.currentUser ? '-' : qrcForm.custDetailsPojo.currentUser }</span>
		</span>
      </li>
	  <%--<li>
         <label>Alt. Email ID:</label>
        <span class="width450 altEmailArea "> <font  id="altEmail">${ empty qrcForm.custDetailsPojo.altEmailId ? '-' : qrcForm.custDetailsPojo.altEmailId }</font>
          <input class="Lmstextbox hidden width220" name="custAltEmail" type="text" id="lmstextbox" value="${ qrcForm.custDetailsPojo.altEmailId }" maxlength="256"/>
        <c:if test="${crmRoles.getAvailable('update_qrc_cp') eq 'true' and (not empty qrcForm.custDetailsPojo.activationDate) and (qrcForm.custDetailsPojo.status ne 'PD')}">
          <a id="updateAltEmail" href="javascript:void(0);">edit</a>
        </c:if>
          <div class="saveCanelButtons hidden"> <a href="javascript:void(0);" id="cancelButton"></a> 
        <c:if test="${crmRoles.getAvailable('update_qrc_cp') eq 'true' and (not empty qrcForm.custDetailsPojo.activationDate) and (qrcForm.custDetailsPojo.status ne 'PD')}">
		  <a href="javascript:void(0);" id="saveButton"></a>
        </c:if>
		  </div>
        </span>
		 
      </li>--%>
	  <li>
        <label>${qrcForm.custDetailsPojo.product eq 'BB' ? 'Option 82':'NAS-Port ID'}:</label>
        <span class="width450">
			${ qrcForm.custDetailsPojo.product eq 'BB' ? qrcForm.networkConfigurationsPojo.option82:qrcForm.partnerNetworkConfigPojo.nasPortId }
		</span>
		
		<label class="qrcHlbl">Mass Outage:</label>
       
        <c:if test="${empty qrcForm.massOutagePojo or qrcForm.massOutagePojo.status eq 'R' }"><span class="width172 qrcHlbl" style="margin-left:-3px; height: 24px; padding:0px 5px;"> 
           <strong><font color="green">NO</font></strong>
       </span> </c:if>
		 
        <c:if test="${not empty qrcForm.massOutagePojo and qrcForm.massOutagePojo.status ne 'R' }"> <span class="width172 simple qrcHlbl" style="margin-left:-3px; height: 14px; padding:0px 5px;"> 
          <a href="#" style="text-decoration: none; margin-left: 0; font-weight: bold;  }" 
            title="ETR: <bean:write name="crmRoles" property="xmlDate(${qrcForm.massOutagePojo.outageEtrTime})"/> Remarks: ${ qrcForm.massOutagePojo.reason }">
			<font color="red">Yes</font></label></a>
      </span>  </c:if>
		
		
      </li>
	  <li>
        <label>Primary MAC ID:</label>
        <span class="width150 customerMacIdArea">${ empty qrcForm.networkConfigurationsPojo.currentCpeMacId ? '-' : qrcForm.networkConfigurationsPojo.currentCpeMacId }</span>
		<label>Sec. MAC ID:</label>
        <span class="width158">${ empty qrcForm.networkConfigurationsPojo.currentSlaveMacId ? '-' : qrcForm.networkConfigurationsPojo.currentSlaveMacId }</span>
		<label class="qrcHlbl">Device Status:</label>
        <span class="width172 qrcHlbl" style="margin-left:-3px; height: 14px; padding:5px 5px;"> <bean:write name="crmRoles" property="displayEnum(CPEStatus,${ qrcForm.orderDetailsPojo.cpeStatus })" scope="session" /></span>
	</li>
	<li>
		 <label>RMN:</label>
			  <span class="rmnArea width150"><font id="rmn">${ qrcForm.custDetailsPojo.rmn }</font> 
           <input class="Lmstextbox hidden width75" name="custRMN" type="text" id="lmstextbox"  maxlength="10" value="${ qrcForm.custDetailsPojo.rmn }" onclick="removeZeroFromNumber(this);" onfocus="removeZeroFromNumber(this);" onkeyup="javascript:ValidatenReplaceAlphanumeric(this);"/>
        <c:if test="${crmRoles.getAvailable('update_qrc_cp') eq 'true' and (not empty qrcForm.custDetailsPojo.activationDate) and (qrcForm.custDetailsPojo.status ne 'PD')}">
           <a id="updateRmn" href="javascript:void(0);">edit</a>
        </c:if>
           <div class="saveCanelButtons hidden"> <a href="javascript:void(0);" id="cancelButton">
  		 </a>
        <c:if test="${crmRoles.getAvailable('update_qrc_cp') eq 'true' and (not empty qrcForm.custDetailsPojo.activationDate) and (qrcForm.custDetailsPojo.status ne 'PD')}">
  		 <a href="javascript:void(0);" id="saveButton"></a>
        </c:if>
  		 </div>
        </span>
		<label>Alt. Mobile No.:</label>
        <span class="width158 altMobileArea"> <font  id="altMobile">${ ( empty qrcForm.custDetailsPojo.custMobileNo || qrcForm.custDetailsPojo.custMobileNo eq 0 ) ? '-' : qrcForm.custDetailsPojo.custMobileNo }</font>
            <input class="Lmstextbox hidden width75" name="custAltMob" type="text" id="lmstextbox" value="${ qrcForm.custDetailsPojo.custMobileNo }" maxlength="10" onclick="removeZeroFromNumber(this);" onfocus="removeZeroFromNumber(this);" onkeyup="javascript:ValidatenReplaceAlphanumeric(this);"/>
            <c:if test="${crmRoles.getAvailable('update_qrc_cp') eq 'true' and (not empty qrcForm.custDetailsPojo.activationDate) and (qrcForm.custDetailsPojo.status ne 'PD')}">
              <a id="updateAltMobile" href="javascript:void(0);">edit</a>
            </c:if>
            <div class="saveCanelButtons hidden"> <a href="javascript:void(0);" id="cancelButton"></a>
            <c:if test="${crmRoles.getAvailable('update_qrc_cp') eq 'true' and (not empty qrcForm.custDetailsPojo.activationDate) and (qrcForm.custDetailsPojo.status ne 'PD')}">
    		  <a href="javascript:void(0);" id="saveButton"></a> 
			</c:if>
			
			</div>
        </span>
		<label class="qrcHlbl">Network Partner:</label>
        <span class="width172 qrcHlbl" style="margin-left:-3px; height: 14px; padding:5px 5px;"> 	<bean:write name="crmRoles" property="displayEnum(PartnerPojo,${ qrcForm.custDetailsPojo.npId })" scope="session" /></span>        
      </li>
	  <li>
        <label>CAF Date:</label>
        <span class="width150">
        <bean:write name="crmRoles" property="toDate(${qrcForm.custDetailsPojo.crfDate})"/>
		</span>
        <label>FT Approval Date:</label>
        <span class="width158"><bean:write name="crmRoles" property="toDate(${qrcForm.custDetailsPojo.ftApprovalDate})"/></span>
        <label class="qrcHlbl">Activation Date:</label>
        <span class="width172 qrcHlbl" style="margin-left:-3px; height: 14px; padding:5px 5px;"> <bean:write name="crmRoles" property="toDate(${qrcForm.custDetailsPojo.activationDate})"/></span>
      </li>
	  <li>
        <label>Close Date:</label>
        <span class="width150">
		<logic:notEmpty name="qrcForm" property="custDetailsPojo.permanentDisconnectDate">
		<bean:write name="crmRoles" property="toDate(${qrcForm.custDetailsPojo.permanentDisconnectDate})"/>
		</logic:notEmpty>
		<logic:empty name="qrcForm" property="custDetailsPojo.permanentDisconnectDate">   	-	</logic:empty>	
        </span>
		<label>DOB:</label>
        <span class="width158">
        <bean:write name="crmRoles" property="toDate(${qrcForm.custDetailsPojo.custDob})"/>
        </span>
		<label class="qrcHlbl">Plan Activation Date</label>
		<span class="width172 qrcHlbl" style="margin-left:-3px; height: 14px; padding:5px 5px;"> <bean:write name="crmRoles" property="toDate(${qrcForm.custAdditionalDetails.activationDate})"/></span>
	  </li>
	  <li>
	  <label>Connection Type:</label>
        <span class="width150 connectionTypeArea">
			<bean:write name="crmRoles" property="displayEnum(ConnectionType,${ qrcForm.custDetailsPojo.connectionType })"/>
        </span>
		<label>Bill Mode: </label>
        <span class="width158 billMode"> <font id="billType"><bean:write name="crmRoles" property="displayEnum(BillingPreferences,${ qrcForm.planDetailsPojo.billMode })" scope="session"/></font>
            <div class="BillModedropdown hidden" style="width:120px;">
              <html:select property="planDetailsPojo.billMode" name="qrcForm" styleId="billMode" styleClass="select valid" style="z-index: 10; opacity: 0;">
                <html:optionsCollection name="qrcForm" property="billModes" label="contentName" value="contentValue" />
              </html:select>
            </div>
            <c:if test="${crmRoles.getAvailable('update_qrc_cp') eq 'true' and (not empty qrcForm.custDetailsPojo.activationDate) and (qrcForm.custDetailsPojo.status ne 'PD')}">
              <a id="updateBillMode" href="javascript:void(0);">edit</a>
            </c:if>
            <div class="saveCanelButtons hidden"> 
             <a href="javascript:void(0);" id="cancelButton"></a> 
             <c:if test="${crmRoles.getAvailable('update_qrc_cp') eq 'true' and (not empty qrcForm.custDetailsPojo.activationDate) and (qrcForm.custDetailsPojo.status ne 'PD')}">
               <a href="javascript:void(0);" id="saveButton"></a> 
             </c:if>
			   
            </div>
        </span>
		<label class="qrcHlbl">Plan Expiry Date:</label>
		<logic:equal name="qrcForm" property="custDetailsPojo.serviceType" value="PR">
		<span class="width172 qrcHlbl" style="margin-left:-3px; height: 14px; padding:5px 5px;">
		<bean:write name="crmRoles" property="toDate(${qrcForm.custAdditionalDetails.expiryDate})"/></span>
		</logic:equal>
		<logic:equal name="qrcForm" property="custDetailsPojo.serviceType" value="PO">
		<span class="width172 qrcHlbl" style="margin-left:-3px; height: 14px; padding:5px 5px;">
		-</span>
		</logic:equal>
	  </li>
	  <li><label>Feedback:</label>
	  <c:if test="${not empty qrcForm.crmCustAssetDetailsPojo and qrcForm.crmCustAssetDetailsPojo.categoryValue eq 'Y' }"><span class="width150 "> 
           <strong><a href="javascript:customerFeedbackPopUp();">Yes</a></strong>
       </span> </c:if>
		 <c:if test="${empty qrcForm.crmCustAssetDetailsPojo or qrcForm.crmCustAssetDetailsPojo.categoryValue ne 'Y' }"><span class="width150 "> 
           <strong><font color="green"><a href="javascript:customerFeedbackPopUp();">NO</a></font></strong>
       </span> </c:if>	
       <label>Inventory Type:</label>
      	<span class="width158"><bean:write name="qrcForm" property="inventoryName"/></span>
      	 <label class="qrcHlbl">Running Session:</label>
      	<span class="width172 qrcHlbl" style="margin-left:-3px; height: 14px; padding:5px 5px;">
      		${ qrcForm.custAdditionalDetails.status } | <bean:write name="crmRoles" property="reportXmlDate(${ qrcForm.custAdditionalDetails.currentSession })"/> 
      	</span>  
         </li>
        
         <li>
          <label>OLT/Master:</label>
          <c:if test="${not empty qrcForm.device1Object }">
         <span class="width150 customerMacIdArea">
	  	<a href="javascript:void(0);" onclick="window.open( 'http://${qrcForm.device1Object.host}');">${qrcForm.partnerNetworkConfigPojo.masterName}</a>
		</span>
		</c:if>
		<c:if test="${empty qrcForm.device1Object and not empty qrcForm.partnerNetworkConfigPojo}">
		 <span class="width150 customerMacIdArea">
	  	<a href="javascript:void(0);">${qrcForm.partnerNetworkConfigPojo.masterName}</a>
		</span>		
		</c:if>
        <c:if test="${not empty qrcForm.device1Object and qrcForm.device1Object.status eq 'Up' }">
        <label>Up Time: </label>
        <span class="width158">
       ${qrcForm.device1Object.uptimesince}
       </span>
       <label  class="qrcHlbl">OLT/Master Status:</label>
       <span  class="width169 qrcHlbl">
         <strong><font color="green" style="margin-left:-3px; height: 14px; padding:5px 5px;">Up</font></strong>
       </span>
       
       </c:if>
		 
        <c:if test="${not empty qrcForm.device1Object and qrcForm.device1Object.status ne 'Up'  }"> 
        
        <label>Down Time: </label>
        <span class="width158">
       ${qrcForm.device1Object.downtimesince}
       </span>
       <label  class="qrcHlbl">OLT/Master Status:</label>
       <span  class="width169 qrcHlbl">
         <strong><font color="red" style="margin-left:-3px; height: 14px; padding:5px 5px;">Down</font></strong>
       </span>
        </c:if>
         </li>
	  </ul>
	  </div>  
	  
	<ul class="table clear customerAddress mWidth900" >
     <li class="table_header"> 
		<span class="width367">Usage Details</span>
		<span class="width430">Invoice Details &nbsp;&nbsp;&nbsp;<a href="manageQrc.do?method=viewPaymentAndInvoiceDetails" class="iframe3" title="Payment & Invoice History" style="font-color:#FFD07A;">View More Payment & Invoice Details</a></span>	
	 </li>
     <li class="table_container">
     <span class="width367">
        <ul class="usageDetails">
          <li>
            <span class="width120"><strong></strong></span>
            <span class="width120"><strong>Primary</strong></span>
            <span class="width120"><strong>Secondary</strong></span>
          </li>
          <li class="margintop10">
            <span class="width120"><strong>DUL</strong></span>
            <span class="width120"><c:if test="${ qrcForm.crmPlanMasterPojo.quotaType eq 'TUL' }">Unlimited</c:if>
            <c:if test="${ qrcForm.crmPlanMasterPojo.quotaType ne 'TUL' }">${empty qrcForm.custAdditionalDetails.primaryQuotaInGB ? qrcForm.crmPlanMasterPojo.primaryQuotaInGB : qrcForm.custAdditionalDetails.primaryQuotaInGB} </c:if>
            </span>
            <span class="width120">
			 ${ empty qrcForm.custAdditionalDetails.secondaryQuotaInGB ? qrcForm.crmPlanMasterPojo.secondaryQuotaInGB : qrcForm.custAdditionalDetails.secondaryQuotaInGB  }
			</span>
          </li>
          <li class="margintop5">
            <span class="width120"><strong>Usage</strong></span>
            <span class="width120">${qrcForm.custAdditionalDetails.primaryUsedQuotaInGB}
            <c:if test="${ qrcForm.crmPlanMasterPojo.primaryQuotaInGB ne '0 GB' }">
             ${empty qrcForm.custAdditionalDetails.primaryUsagePercentage ? '-' : "(".concat(qrcForm.custAdditionalDetails.primaryUsagePercentage.concat(")"))}</c:if></span>
            <span class="width120">${qrcForm.custAdditionalDetails.secondaryUsedQuotaInGB}</span>
          </li>
           <li class="margintop10 borderBottom">
		  </li>
          <li class="margintop5 ">
            <span class="width120"><strong>Total Usage</strong></span>
            <span>${empty qrcForm.custAdditionalDetails.usedVolumeQuotaInGB ? '-' : qrcForm.custAdditionalDetails.usedVolumeQuotaInGB}&nbsp;(Usage Till Last Router Restart)</span>
           
          </li>
		 
          <li class="margintop5">
            <span class="width120"><strong>Unbilled Usage</strong></span>
            <span class="class="width120"">
            <c:if test="${(not empty qrcForm.custDetailsPojo.activationDate)}">
              <a href="javascript:downloadCustomerUsage('Unbilled');" title="Click to download Unbuild usage details" class="excel-download"></a>
            </c:if>
			<c:if test="${crmRoles.getAvailable('update_qrc_cp') eq 'true' and (not empty qrcForm.custDetailsPojo.activationDate) and (qrcForm.custDetailsPojo.status ne 'PD')}">
          		<a href="javascript:sendCustomerUsage('Unbilled','');"  title="Click to Email Unbuild usage details." class="send-mail"></a>
            </c:if>
			</span>
           
          </li>
          <li class="margintop5">
            <span class="width120"><strong>Billed Usage</strong></span>
            
         <span class="width120 billedUsageArea"><font></font>
          <div class="billedUsagePopup hidden">
          	<div id='billedUsageErrorArea' class='errorBilledUsage'></div>
            <div>
            	<label> From Date : </label>
            	<html:text styleClass="tcal tcalInput" name="qrcForm" styleId="usageFormDateID" property="usageFormDate" readonly="true"></html:text>  
            </div>
            <div>
            	<label> To Date : </label>
            	<html:text styleClass="tcal tcalInput" name="qrcForm" styleId="usageToDateID" property="usageToDate" readonly="true"></html:text>  
            </div>
            <div class="saveCanelBilledUsageButtons"> <a href="javascript:void(0);" id="cancelButton"></a> 
			<c:if test="${crmRoles.getAvailable('update_qrc_cp') eq 'true' and (not empty qrcForm.custDetailsPojo.activationDate) and (qrcForm.custDetailsPojo.status ne 'PD')}">
			 <a href="javascript:void(0);" id="saveButton"></a>
			</c:if>
			</div>
          </div>
          <c:if test="${(not empty qrcForm.custDetailsPojo.activationDate)}">
            <a href="javascript:void(0);" id="downloadBilledUsage"  title="Click to download Build usage details" class="excel-download"></a>
          </c:if>
        </span>
          </li>
        </ul> 
       </span> 
	   <span class="width300">
          <ul class="invoiceDetails">
            <li>
              <span class="width140"><strong class="noTopMargin">Invoice Date</strong></span>
              <span class="width120">
              <bean:write name="crmRoles" property="toDate(${qrcForm.invoicePojo.billDate})"/></span>
            </li>
            <li>
              <span class="width140"><strong>Invoice No.</strong></span> 
			  <span>
              <logic:empty name="qrcForm" property="invoicePojo.billNumber">
                -
              </logic:empty>
              <logic:notEmpty name="qrcForm" property="invoicePojo.billNumber">                
                  <a href="#" onclick="window.open( '${qrcForm.invoicePojo.nopasswordPdfFile}', 'newWindow', 'width='+parent.innerWidth+',height='+parent.innerHeight+',scrollbars=yes,resizable=no,toolbar=no');" class="viewMorePayment_InvDetails">${qrcForm.invoicePojo.billNumber}</a>               
              </logic:notEmpty> 
			</span>              
            </li>
            <li>
              <span class="width140"><strong>Invoice Amount</strong></span>
              <span>
			  	${ empty qrcForm.invoicePojo.billAmount ? '-' : qrcForm.invoicePojo.billAmount  }
			  </span>
            </li>
            <li>
              <span class="width140"><strong>Due Date</strong></span>
              <span><bean:write name="crmRoles" property="toDate(${qrcForm.invoicePojo.dueDate})"/></span>
            </li>
            <li>
            <span class="width140"><strong>Security Deposit</strong></span>
            <span class="altSecurityDeposit"> <font  id="altSecurityDepo">${ ( empty qrcForm.crmPaymentDetailsPojo.securityCharges ) ? '-' : qrcForm.crmPaymentDetailsPojo.securityCharges }</font>
            <c:if test="${ not empty qrcForm.crmPaymentDetailsPojo.paymentId }">
            <input class="Lmstextbox hidden width75" name="securityDepo" type="text" id="lmstextbox" value="${ qrcForm.crmPaymentDetailsPojo.securityCharges }" maxlength="10" onclick="removeZeroFromNumber(this);" onfocus="removeZeroFromNumber(this);" onkeyup="javascript:ValidatenReplaceAlphanumeric(this);"/>
            <c:if test="${crmRoles.getAvailable('update_security_deposit') eq 'true' and (not empty qrcForm.custDetailsPojo.activationDate) and (qrcForm.custDetailsPojo.status ne 'PD')}">
              <a id="updateSecDepo" class="edit-btn" href="javascript:void(0);">edit</a>
            </c:if>
            <div class="saveCanelButtons hidden"> <a href="javascript:void(0);" id="cancelButton"></a>
            <c:if test="${crmRoles.getAvailable('update_security_deposit') eq 'true' and (not empty qrcForm.custDetailsPojo.activationDate) and (qrcForm.custDetailsPojo.status ne 'PD')}">
    		  <a href="javascript:void(0);" id="saveButton"></a> 
			</c:if>
			</div>
			</c:if>
        	</span>
             <!--  <span class="width140"><strong>Security Deposit</strong></span>
              <span>${ empty qrcForm.crmPaymentDetailsPojo.securityCharges ? '-' :qrcForm.crmPaymentDetailsPojo.securityCharges }</span> -->
            </li>
            <li>
              <span class="width140"><strong>${qrcForm.custDetailsPojo.serviceType eq 'PR' ? 'Wallet Balance' : 'Outstanding'}</strong></span>
              <span>${ empty qrcForm.custAdditionalDetails.balance ? '-' : qrcForm.custAdditionalDetails.balance  }</span>
            </li>
          </ul>
        </span>
	</li>
   </ul>
   
   <ul class="table clear customerAddress mWidth900" >
     <li class="table_header"> 
		<span class="width367">Installation Address</span>
		<span class="width430">Billing Address</span>
			
	 </li>
	 <li class="table_container">
     	<span class="installAddressArea width367">
       <div>
         <div class="addressLine01"> <font id="installAddLine1">${ qrcForm.installationAddressPojo.addLine1 }</font>
           <input class="Lmstextbox hidden" name="installAddL1" type="text" id="lmstextbox" value="${ qrcForm.installationAddressPojo.addLine1 }" 
           onkeyup="javascript:changeToUpperCase(this)" maxlength="60" />
         </div>
       </div>
       <div>
		<div class="addressLine02"> <font>${ qrcForm.installationAddressPojo.addLine2 }</font> </div>
       </div>
       <div>
         <div class="addressLine03"> <font>${ qrcForm.installationAddressPojo.addLine3 }</font> </div>
       </div>
	   <div>
	    <div><font id="insAddPincode">${ qrcForm.installationAddressPojo.pincode}</font></div>
	   </div>
	   <c:if test="${crmRoles.getAvailable('update_qrc_insadd') eq 'true' and (not empty qrcForm.custDetailsPojo.activationDate) and (qrcForm.custDetailsPojo.status ne 'PD')}">
       <a id="changeinstallAddress" href="javascript:void(0);">edit</a>
       </c:if>
       <div class="saveCanelButtons hidden"> 
       	<a href="javascript:void(0);" id="cancelButton"></a> 
       	<c:if test="${crmRoles.getAvailable('update_qrc_insadd') eq 'true' and (not empty qrcForm.custDetailsPojo.activationDate) and (qrcForm.custDetailsPojo.status ne 'PD')}">
       	<a href="javascript:void(0);" id="saveButton"></a> 
       	</c:if>
       </div>
       </span> 
	  <span class="billAddArea width165">
	  <ul class="customerAdd" style="margin-left:-37px !important;">
	  <li class="table_contain" style="border-top: 0px !important; border-bottom: 0px !important;	border-left: 0px !important;	border-right: 0px !important; width:450px !important;">
	    <span class="billAddressArea width230">
       <div>
         <div class="addressLine1"> <font id="billAddLine1">${ qrcForm.billingAddressPojo.addLine1 }</font>
           <input class="Lmstextbox hidden" name="billAddL1" type="text" id="lmstextbox" value="${ qrcForm.billingAddressPojo.addLine1 }" 
           onkeyup="javascript:changeToUpperCase(this)" maxlength="60"/>
         </div>
       </div>
       <div>
         <div class="addressLine2"> <font id="billAddLine2">${ qrcForm.billingAddressPojo.addLine2 }</font>
           <input class="Lmstextbox hidden" name="billAddL2" type="text" id="lmstextbox" value="${ qrcForm.billingAddressPojo.addLine2 }" 
           onkeyup="javascript:changeToUpperCase(this)" maxlength="60"/>
         </div>
       </div>
       <div>
         <div class="addressLine3"> <font id="billAddLine3">${ qrcForm.billingAddressPojo.addLine3 }</font>
           <input class="Lmstextbox hidden" readonly="readonly" type="text" id="lmstextbox" value="${ qrcForm.billingAddressPojo.addLine3 }" />
         </div>
       </div>
      <div class="addressPincode"><font id="billAddPincode">${ qrcForm.billingAddressPojo.pincode}</font>
           <input class="Lmstextbox hidden" maxlength="6" type="text" id="lmstextbox" value="${ qrcForm.billingAddressPojo.pincode}" onclick="removeZeroFromNumber(this);" onfocus="removeZeroFromNumber(this);" onkeyup="javascript:ValidatenReplaceAlphanumeric(this);"/>
         </div><div>
      <c:if test="${crmRoles.getAvailable('update_qrc_cp') eq 'true' and (not empty qrcForm.custDetailsPojo.activationDate) and (qrcForm.custDetailsPojo.status ne 'PD')}">
       <a id="changeBillAddress" href="javascript:void(0);">edit</a>
       </c:if>
       <div class="saveCanelButtons hidden"> <a href="javascript:void(0);" id="cancelButton"></a> 
       <c:if test="${crmRoles.getAvailable('update_qrc_cp') eq 'true' and (not empty qrcForm.custDetailsPojo.activationDate) and (qrcForm.custDetailsPojo.status ne 'PD')}">
        <a href="javascript:void(0);" id="saveButton"></a> 
       </c:if>
       </div></div>
       </span> 
	   <span class="billAddArea width230">
	    <div class="stateDropdown hidden">
            <label>State</label>
            <div class="Lmsdropdown height33i">
              <html:select name="qrcForm" property="billingAddressPojo.stateName" value="${ qrcForm.billingAddressPojo.stateName }" 
              styleClass="select valid qrcBillState" style="z-index: 10; opacity: 0;" styleId="qrcBillState" 
              onchange="javascript:populateCityByState('qrcBillCity',this.value);">
            	<html:optionsCollection name="qrcForm" property="stateList" label="stateName" value="stateName" />
            </html:select>
              </div>
         </div>
		 <div class="cityDropdown hidden">
            <label>City</label>
            <div class="Lmsdropdown height33i">
              <html:select name="qrcForm" property="billingAddressPojo.cityName" value="${ qrcForm.billingAddressPojo.cityName }" 
              styleClass="select valid qrcBillCity" style="z-index: 10; opacity: 0;" styleId="qrcBillCity">
                <html:option value="0">Please select</html:option>
              	<logic:notEmpty name="qrcForm" property="billingAddressPojo.cityId">
                  <html:optionsCollection name="qrcForm" property="cityList" label="cityName" value="cityName" />
	     		</logic:notEmpty>
              </html:select>
			  </div>
              </div>
	  
	   </span>
	   </ul></li>
	   </span>
	   </li>
	</ul>
   </html:form>
</div>
