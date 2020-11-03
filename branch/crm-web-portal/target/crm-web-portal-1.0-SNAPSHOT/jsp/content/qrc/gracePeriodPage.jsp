<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib uri="/WEB-INF/tlds/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/tlds/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/tlds/displaytag.tld" prefix="display"%>
<%@taglib uri="/WEB-INF/tlds/struts-html.tld" prefix="html"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<div class="loadingPopup hidden"></div>
<div id="section">
  <div class="section">
    <logic:notEmpty name="qrcForm" property="custDetailsPojo.customerId">
      <jsp:include page="crfCustomerDescription.jsp"></jsp:include>
    </logic:notEmpty>
    <div class="inner_section ">
      <div class="inner_left_lead floatl  qrcLeft">
        <bean:define id="activeMenu" value="graceperiod" />
        <%@include file="qrcMenu.jsp"%>
      </div>
      <div class=" floatl manageGISRight  qrcRight">
        <div class="success_message">
          <logic:messagesPresent message="true">
            <html:messages id="message" message="true">
              <bean:write name="message" />
            </html:messages>
          </logic:messagesPresent>
        </div>
        <div class="error_message" id="error">
          <html:errors />
        </div>
        <h4>Validity Extension</h4>

        <div class="relative inner_left_lead">
          <html:form action="/manageQrc">
            <div class="floatl">
              <p class="floatl clear">
                <strong> Plan Name </strong>
                <bean:write name="crmRoles" property="displayEnum(BASE,${ qrcForm.planDetailsPojo.basePlanCode })" scope="session" />;
                Rs <fmt:formatNumber type="number" minFractionDigits="0" maxFractionDigits="2" value="${ qrcForm.crmPlanMasterPojo.rentExclTax + (not empty qrcForm.crmAddonPlanMasterPojo ? qrcForm.crmAddonPlanMasterPojo.rentExclTax : 0)}" /> plus tax for
                <c:choose>
                  <c:when test="${qrcForm.crmPlanMasterPojo.planUsageType eq 'L'}">
                    <c:if test="${qrcForm.custDetailsPojo.serviceType eq 'PO'}">
                      ${empty qrcForm.custAdditionalDetails.primaryQuotaInGB ? qrcForm.crmPlanMasterPojo.totalDULInGB : qrcForm.custAdditionalDetails.primaryQuotaInGB} at
                      <fmt:formatNumber type="number" minFractionDigits="0" maxFractionDigits="2" value="${ qrcForm.crmPlanMasterPojo.primarySpeed div 1024 }" />&nbsp;Mbps
                    </c:if>
                    <c:if test="${qrcForm.custDetailsPojo.serviceType eq 'PR'}">
                      ${empty qrcForm.custAdditionalDetails.primaryQuotaInGB ? qrcForm.crmPlanMasterPojo.totalDULInGB : qrcForm.custAdditionalDetails.primaryQuotaInGB} at
                      <fmt:formatNumber type="number" minFractionDigits="0" maxFractionDigits="2" value="${ qrcForm.crmPlanMasterPojo.primarySpeed div 1024 }" />&nbsp;Mbps
                    </c:if>
                    <%-- <fmt:formatNumber type="number" minFractionDigits="0" maxFractionDigits="2"
                      value="${ qrcForm.crmPlanMasterPojo.primarySpeed div 1024 }" />&nbsp;Mbps; 
                ${empty qrcForm.custAdditionalDetails.primaryQuotaInGB ? qrcForm.crmPlanMasterPojo.totalDULInGB : qrcForm.custAdditionalDetails.primaryQuotaInGB};
             <bean:message key="limited.plan.info.postusage" /> --%>
                  </c:when>
                  <c:otherwise>
                    ${empty qrcForm.custAdditionalDetails.primaryQuotaInGB ? qrcForm.crmPlanMasterPojo.totalDULInGB : qrcForm.custAdditionalDetails.primaryQuotaInGB} at
                    <fmt:formatNumber type="number" maxFractionDigits="0" value="${ qrcForm.crmPlanMasterPojo.primarySpeed div 1024 }" />&nbsp;Mbps, and Unlimited at
                    ${ qrcForm.crmPlanMasterPojo.secondarySpeed } Kbps
                    <%-- <fmt:formatNumber type="number" maxFractionDigits="0" value="${ qrcForm.crmPlanMasterPojo.primarySpeed div 1024 }" />&nbsp;Mbps 
                    <c:if test="${qrcForm.crmPlanMasterPojo.primaryQuota lt 0 }">Unlimited</c:if>
                    <c:if test="${qrcForm.crmPlanMasterPojo.primaryQuota gt -1 }">
                      upto 
                      ${empty qrcForm.custAdditionalDetails.primaryQuotaInGB ? qrcForm.crmPlanMasterPojo.totalDULInGB : qrcForm.custAdditionalDetails.primaryQuotaInGB};
                      ${ qrcForm.crmPlanMasterPojo.secondarySpeed } Kbps thereafter
                    </c:if> --%>
                  </c:otherwise>
                </c:choose>
              </p>
              <p class="floatl clear">
                <strong> Activation Date </strong> <span> <html:hidden name="qrcForm" property="qrcForm.custAdditionalDetails.activationDate"
                    value='${qrcForm.custAdditionalDetails.activationDate}' /> <bean:write name="crmRoles"
                    property="toDate(${qrcForm.custAdditionalDetails.activationDate})" />
                </span> </span>
              </p>
              <p class="floatl marginleft60">
                <strong> Expire Date </strong> <span> <html:hidden name="qrcForm" property="qrcForm.custAdditionalDetails.expiryDate"
                    value='${qrcForm.custAdditionalDetails.expiryDate}' /> <bean:write name="crmRoles"
                    property="toDate(${qrcForm.custAdditionalDetails.expiryDate})" />
                </span>
              </p>
              <p class="floatl clear">
                <strong> No of Extended days </strong> <span class="LmsdropdownWithoutJs"> <html:select name="qrcForm" property="days"
                    styleId="GP_days">
                    <html:option value="0">Please Select</html:option>
                    <c:forEach var="i" begin="1" end="31">
                      <html:option value='${i}'>${i}</html:option>
                    </c:forEach>

                  </html:select> <font class="errorTextbox hidden">Please select extended days.</font>
                </span>
              </p>
              <p class="floatl marginleft60">
                <strong>Ticket ID</strong>
                <html:text property="srTicketNo" name="qrcForm" maxlength="20" styleClass="Lmstextbox" styleId="GP_TicketId"></html:text>
              </p>
              <p class="floatl clear">
                <strong> Reason </strong> <span class="LmsdropdownWithoutJs"> <html:select property="gracePeriodReason" name="qrcForm"
                    styleId="GP_ReasonId">
                    <html:option value="0">Please select</html:option>
                    <logic:notEmpty name="qrcForm" property="crmRcaReasonPojos">
                      <html:optionsCollection name="qrcForm" property="crmRcaReasonPojos" label="categoryValue" value="categoryValue" />
                    </logic:notEmpty>
                  </html:select> <font class="errorTextbox hidden">Please select reason.</font>
                </span>
              </p>


              <p class="floatl clear">
                <strong> Remarks</strong>
                <html:textarea property="remarksPojo.remarks" name="qrcForm" styleClass="LmsRemarkstextarea" styleId="GP_RemarksId"></html:textarea>
                <font class="errorRemarks hidden">Please enter remarks between [2-4000].</font>
              </p>


              <br class="clear" />
            </div>
            <div class="floatr inner_right">
              <p class="buttonPlacement">



                <logic:equal name="crmRoles" property="available(create_qrc_valex)" value="true" scope="session">
                  <c:if test="${qrcForm.visibileButton}">
                    <a href="#" id="submit_gracePeriod" class="main_button_multiple  "> <span>Submit </span>
                    </a>
                  </c:if>


                </logic:equal>


              </p>
            </div>
          </html:form>
          <br class="clear" />
        </div>
      </div>
      <p class="clear" />