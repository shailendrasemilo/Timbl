<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib uri="/WEB-INF/tlds/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/tlds/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/tlds/displaytag.tld" prefix="display"%>
<%@taglib uri="/WEB-INF/tlds/struts-html.tld" prefix="html"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!--================ plan migration ===============-->
<link href="css/jquery.tooltip.css" rel="stylesheet" type="text/css" />
<script src="javascript/Jquery_Latest.js" type="text/javascript"></script>
<script src="javascript/jquery.tooltip.min.js" type="text/javascript"></script>
<script src="javascript/MakeYourPlan2.js" type="text/javascript"></script>
<script>
window.onload = function() {
  disableAddonPlan();
};
</script>
<!--================ plan migration ===============-->
<div class="loadingPopup hidden"></div>
<div id="section">
  <div class="section">
    <logic:notEmpty name="qrcForm" property="custDetailsPojo.customerId">
      <jsp:include page="crfCustomerDescription.jsp"></jsp:include>
      </h4>
    </logic:notEmpty>
    <div class="inner_section ">
      <div class="inner_left_lead floatl  qrcLeft">
        <bean:define id="activeMenu" value="tariffMigration"></bean:define>
        <%@include file="qrcMenu.jsp"%>
      </div>
      <div class=" floatl manageGISRight qrcRight">
        <div class="success_message">
          <logic:messagesPresent message="true">
            <html:messages id="message" message="true">
              <bean:write name="message" />
              <br>
            </html:messages>
          </logic:messagesPresent>
        </div>
        <div class="error_message" id="error">
          <html:errors />
        </div>
        <html:form action="/manageQrc" method="post" styleId="tariffMigration">
          <html:hidden property="addonAllowed" styleId="addonAllowedId" />
          <html:hidden name="qrcForm" property="planCategory" styleId="planCategoryId" />
          <html:hidden property="addonPlanCode" name="qrcForm" styleId="addonPlanId" />
          <html:hidden property="oldBasePlanCode" name="qrcForm" styleId="oldBasePlanCodeId"
            value="${ qrcForm.planDetailsPojo.basePlanCode eq '0' ? '' : qrcForm.planDetailsPojo.basePlanCode }" />
          <html:hidden property="oldAddonPlanCode" name="qrcForm" styleId="oldAddonPlanCodeId"
            value="${ qrcForm.planDetailsPojo.addOnPlanCode eq '0' ? '' : qrcForm.planDetailsPojo.addOnPlanCode }" />
          <input type="hidden" id="serviceTypeId" value="${ qrcForm.custDetailsPojo.serviceType }" />
          <input type="hidden" id="postusage" value="<bean:message key="limited.plan.info.postusage"/>" />
          <input type="hidden" id="customerId" value="${ qrcForm.custDetailsPojo.customerId }" />
          <html:hidden property="activationType" name="qrcForm" styleId="activationTypeId" />
          <div class="floatl">
            <!-- plan info -->
            <div class="floatl planMigrationHead">
              <ul class="planInfo">
                <li>
                  <ul class="pageHeads">
				  <logic:equal name="crmRoles"
                      property="available(create_plan_migration_paid,update_plan_migration_paid,create_plan_migration_foc,update_plan_migration_foc,create_plan_migration_app,
                                update_plan_migration_app,create_plan_migration_mbo,update_plan_migration_mbo,create_plan_migration_retention,update_plan_migration_retention)"
                      value="true" scope="session">
                      <c:if
                        test="${ ( qrcForm.custDetailsPojo.serviceType eq 'PR' && qrcForm.custDetailsPojo.status eq 'A' && !qrcForm.custAdditionalDetails.expired ) || (qrcForm.custDetailsPojo.serviceType eq 'PO' && qrcForm.custDetailsPojo.status eq 'A')}">
                        <li class="">
                          <%-- ${ ( qrcForm.custDetailsPojo.serviceType eq 'PR' && qrcForm.custDetailsPojo.status eq 'A' && !qrcForm.custAdditionalDetails.expired ) || (qrcForm.custDetailsPojo.serviceType eq 'PO' && qrcForm.custDetailsPojo.status eq 'A') ? '' : 'hidden' } --%>
                          <a href="javascript:void(0);"
                          class="yellow_button ${ qrcForm.planCategory eq 'Addon Plans' ? 'active' : '' } ${qrcForm.addonAllowed or qrcForm.crmPlanMasterPojo.addonAllowedYn eq 'Y' ? '' : 'disabled'}"
                          onclick="${qrcForm.addonAllowed or qrcForm.crmPlanMasterPojo.addonAllowedYn eq 'Y' ? 'addonPlans(this);' : 'return false;'}">Add-on
                            Plans</a>
                           <input type="hidden" value="${ qrcForm.planDetailsPojo.addOnPlanCode}" id="addonPlanHiddenID"/> 
                        </li>
                      </c:if>
                    </logic:equal>
                    <logic:equal name="crmRoles"
                      property="available(create_plan_migration_paid,update_plan_migration_paid,create_plan_migration_foc,update_plan_migration_foc,create_plan_migration_app,
                                update_plan_migration_app,create_plan_migration_mbo,update_plan_migration_mbo,create_plan_migration_retention,update_plan_migration_retention)"
                      value="true" scope="session">
                      <c:if
                        test="${ ( qrcForm.custDetailsPojo.serviceType eq 'PR' && qrcForm.custDetailsPojo.status eq 'A' && !qrcForm.custAdditionalDetails.expired ) || (qrcForm.custDetailsPojo.serviceType eq 'PO' && qrcForm.custDetailsPojo.status eq 'A') }">
                        <li class="">
                          <%-- ${ ( qrcForm.custDetailsPojo.serviceType eq 'PR' && qrcForm.custDetailsPojo.status eq 'A' && !qrcForm.custAdditionalDetails.expired ) || (qrcForm.custDetailsPojo.serviceType eq 'PO' && qrcForm.custDetailsPojo.status eq 'A') ? '' : 'hidden' } --%>
                          <a href="javascript:void(0);" class="yellow_button ${ qrcForm.planCategory eq 'Base plan migration' ? 'active' : '' }"
                          onclick="planMigration(this);"> Plan Migration</a>
                        </li>
                      </c:if>
                    </logic:equal>
					<logic:equal name="crmRoles"
                      property="available(create_plan_renew_paid,update_plan_renew_paid,create_plan_renew_foc,update_plan_renew_foc,create_plan_renew_app,
                                  update_plan_renew_app,create_plan_renew_mbo,update_plan_renew_mbo,create_plan_renew_retention,update_plan_renew_retention)"
                      value="true" scope="session">
                      <c:if
                        test="${ qrcForm.custDetailsPojo.serviceType eq 'PR' && (qrcForm.custDetailsPojo.status eq 'B' || qrcForm.custDetailsPojo.status eq 'A')}">
                        <li class="">
                          <%-- ${ qrcForm.custDetailsPojo.serviceType eq 'PR' && (qrcForm.custDetailsPojo.status eq 'B' || qrcForm.custDetailsPojo.status eq 'A') ? '' : 'hidden' } --%>
                          <a href="javascript:void(0);" class="yellow_button ${ qrcForm.planCategory eq 'Plan Renewal' ? 'active' : '' }"
                          onclick="planRenew(this);"> Plan Renew</a>
                        </li>
                      </c:if>
                    </logic:equal>
                    <logic:equal name="crmRoles"
                      property="available(create_plan_reactivation_paid,update_plan_reactivation_paid,create_plan_reactivation_foc,update_plan_reactivation_foc,create_plan_reactivation_app,
                              update_plan_reactivation_app,create_plan_reactivation_mbo,update_plan_reactivation_mbo,create_plan_reactivation_retention,update_plan_reactivation_retention)"
                      value="true" scope="session">
                      <c:if test="${ (qrcForm.custDetailsPojo.status eq 'T' || qrcForm.custDetailsPojo.status eq 'SC')}">
                        <li class="">
                          <%-- ${ (qrcForm.custDetailsPojo.status eq 'T' || qrcForm.custDetailsPojo.status eq 'SC') ? '' : 'hidden' } --%> <a
                          href="javascript:void(0);" class="yellow_button ${ qrcForm.planCategory eq 'Plan Reactivation' ? 'active' : '' }"
                          onclick="planReactivation(this);"> Plan Reactivation</a>
                        </li>
                      </c:if>
                    </logic:equal>
                    <logic:equal name="crmRoles"
                      property="available(create_plan_booster_usage,update_plan_booster_usage,create_plan_booster_speed,update_plan_booster_speed)"
                      value="true" scope="session">
                      <c:if
                        test="${ ( qrcForm.custDetailsPojo.serviceType eq 'PR' && qrcForm.custDetailsPojo.status eq 'A' && !qrcForm.custAdditionalDetails.expired ) || (qrcForm.custDetailsPojo.serviceType eq 'PO' && qrcForm.custDetailsPojo.status eq 'A') }">
                        <li class="">
                          <%-- ${ ( qrcForm.custDetailsPojo.serviceType eq 'PR' && qrcForm.crmPlanMasterPojo.planUsageType eq 'U' && qrcForm.custDetailsPojo.status eq 'A' && !qrcForm.custAdditionalDetails.expired ) || (qrcForm.custDetailsPojo.serviceType eq 'PO' && qrcForm.crmPlanMasterPojo.planUsageType eq 'U'  && qrcForm.custDetailsPojo.status eq 'A') ? '' : 'hidden' } --%>
                          <a href="javascript:void(0);"
                          class="yellow_button ${ qrcForm.planCategory eq 'Booster plan' ? 'active' : '' } ${qrcForm.crmPlanMasterPojo.boosterAllowedYn eq 'Y' ? '' : 'disabled'}"
                          onclick="${qrcForm.crmPlanMasterPojo.boosterAllowedYn eq 'Y' ? 'planBooster(this)' : 'return false'};"> Plan Booster</a>
                        </li>
                      </c:if>
                    </logic:equal>
                    
                    <logic:equal name="crmRoles" property="available(create_vas_subscription,update_vas_subscription,delete_vas_subscription)"
                      value="true" scope="session">
                      <c:if
                        test="${ ( qrcForm.custDetailsPojo.serviceType eq 'PR' && qrcForm.crmPlanMasterPojo.planUsageType eq 'U' && qrcForm.custDetailsPojo.status eq 'A' && !qrcForm.custAdditionalDetails.expired ) || (qrcForm.custDetailsPojo.serviceType eq 'PO' && qrcForm.crmPlanMasterPojo.planUsageType eq 'U'  && qrcForm.custDetailsPojo.status eq 'A')}">
                        <li class="">
                          <%-- ${ ( qrcForm.custDetailsPojo.serviceType eq 'PR' && qrcForm.crmPlanMasterPojo.planUsageType eq 'U' && qrcForm.custDetailsPojo.status eq 'A' && !qrcForm.custAdditionalDetails.expired ) || (qrcForm.custDetailsPojo.serviceType eq 'PO' && qrcForm.crmPlanMasterPojo.planUsageType eq 'U'  && qrcForm.custDetailsPojo.status eq 'A') ? '' : 'hidden' } --%>
                          <a href="javascript:void(0);" class="yellow_button ${ qrcForm.planCategory eq 'VAS_MANAGEMENT' ? 'active' : '' }"
                          onclick="vasManagement(this);"> VAS</a>
                        </li>
                      </c:if>
                    </logic:equal>
                  </ul>
                </li>
                <li>
                  <ul class="speedTable">
                    <li class="colHead">
                      <div class="col1">&nbsp;</div>
                      <div class="col2">Primary</div>
                      <div class="col3">Secondary</div>
                    </li>
                    <li>
                      <div class="col1">Speed</div>
                      <div class="col2">
                        <fmt:formatNumber type="number" maxFractionDigits="0" value="${ qrcForm.crmPlanMasterPojo.primarySpeed div 1024 }" />
                        &nbsp;Mbps
                      </div>
                      <div class="col3">
                      <c:if test="${ qrcForm.crmPlanMasterPojo.secondarySpeed lt 1024 }">
			  				  ${qrcForm.crmPlanMasterPojo.secondarySpeed}&nbsp;Kbps
			  		  </c:if>
					  <c:if test="${ qrcForm.crmPlanMasterPojo.secondarySpeed div 1024 ge 1 }">
				          <fmt:formatNumber type="number" minFractionDigits="0" maxFractionDigits="2" value="${ qrcForm.crmPlanMasterPojo.secondarySpeed div 1024 }" />&nbsp;Mbps
				      </c:if>
				     </div>
                    </li>
                    <li>
                      <div class="col1">DUL</div>
                      <div class="col2"><c:if test="${ qrcForm.crmPlanMasterPojo.quotaType eq 'TUL' }">Unlimited</c:if>
                      		<c:if test="${ qrcForm.crmPlanMasterPojo.quotaType ne 'TUL' }">
                      			${empty qrcForm.custAdditionalDetails.primaryQuotaInGB ? qrcForm.crmPlanMasterPojo.primaryQuotaInGB :qrcForm.custAdditionalDetails.primaryQuotaInGB}
                      		</c:if>
                        </div>
                      <div class="col3">${ empty qrcForm.custAdditionalDetails.secondaryQuotaInGB ? qrcForm.crmPlanMasterPojo.secondaryQuotaInGB :
                        qrcForm.custAdditionalDetails.secondaryQuotaInGB }</div>
                    </li>
                    <li>
                      <div class="col1">Usage</div>
                      <div class="col2">
                        ${qrcForm.custAdditionalDetails.primaryUsedQuotaInGB} ${empty qrcForm.custAdditionalDetails.primaryUsagePercentage ? '-' :
                        "(".concat(qrcForm.custAdditionalDetails.primaryUsagePercentage.concat(")"))}
                        <a href="javascript:downloadCustomerUsage('Unbilled');" style="color: #092BFC; margin-left: 15px;">Usage Details</a>
                        <%--
                        <div class="smallUsageBar">
                          <div class="smallBar"></div>
                          <div class="smallUsageBar_50">
                            <span>50%</span>
                          </div>
                          <div class="smallUsageBar_80">
                            <span>80%</span>
                          </div>
                          <span>100%</span>
                        </div>
                         --%>
                      </div>
                      <div class="col3">${qrcForm.custAdditionalDetails.secondaryUsedQuotaInGB}</div>
                    </li>
                  </ul>
                </li>
                <li>
                  <div class="floatl margintop10" style="width: 300px">
                    <label><strong>Current Plan Info:</strong></label>
                    <div class="floatr">
                      <c:choose>
                        <c:when test="${qrcForm.crmPlanMasterPojo.planUsageType eq 'L'}">
                          <span><span style='margin-top: initial'> <bean:write name="crmRoles"
                                property="displayEnum(BASE,${ qrcForm.planDetailsPojo.basePlanCode })" scope="session" />;
                                Rs <fmt:formatNumber type="number" minFractionDigits="0" maxFractionDigits="2" value="${ qrcForm.crmPlanMasterPojo.rentExclTax + (not empty qrcForm.crmAddonPlanMasterPojo ? qrcForm.crmAddonPlanMasterPojo.rentExclTax : 0)}" /> plus tax for<br> 
                              <c:if test="${qrcForm.custDetailsPojo.serviceType eq 'PO'}">
                                ${empty qrcForm.custAdditionalDetails.primaryQuotaInGB ? qrcForm.crmPlanMasterPojo.totalDULInGB : qrcForm.custAdditionalDetails.primaryQuotaInGB} at
                                <fmt:formatNumber type="number" minFractionDigits="0" maxFractionDigits="2" value="${ qrcForm.crmPlanMasterPojo.primarySpeed div 1024 }" />&nbsp;Mbps
                              </c:if>
                              <c:if test="${qrcForm.custDetailsPojo.serviceType eq 'PR'}">
                                ${empty qrcForm.custAdditionalDetails.primaryQuotaInGB ? qrcForm.crmPlanMasterPojo.totalDULInGB : qrcForm.custAdditionalDetails.primaryQuotaInGB} at
                                <fmt:formatNumber type="number" minFractionDigits="0" maxFractionDigits="2" value="${ qrcForm.crmPlanMasterPojo.primarySpeed div 1024 }" />&nbsp;Mbps
                              </c:if>
                              <%-- <fmt:formatNumber type="number" minFractionDigits="0" maxFractionDigits="2" value="${ qrcForm.crmPlanMasterPojo.primarySpeed div 1024 }" />&nbsp;Mbps;
                              <span class="bold">${empty qrcForm.custAdditionalDetails.primaryQuotaInGB ? qrcForm.crmPlanMasterPojo.totalDULInGB :
                       qrcForm.custAdditionalDetails.primaryQuotaInGB};</span><br>
                              <bean:message key="limited.plan.info.postusage" /> --%>
                          </span></span>
                        </c:when>
                        <c:otherwise>
                          <span><span style='margin-top: initial'> <bean:write name="crmRoles" property="displayEnum(BASE,${ qrcForm.planDetailsPojo.basePlanCode })" scope="session" />;
                                Rs <fmt:formatNumber type="number" minFractionDigits="0" maxFractionDigits="2" value="${ qrcForm.crmPlanMasterPojo.rentExclTax + (not empty qrcForm.crmAddonPlanMasterPojo ? qrcForm.crmAddonPlanMasterPojo.rentExclTax : 0)}" /> plus tax for<br>
                                <c:if test="${ qrcForm.crmPlanMasterPojo.quotaType eq 'TUL' }">Unlimited</c:if>
                                <c:if test="${ qrcForm.crmPlanMasterPojo.quotaType ne 'TUL' }">
                                	<span class="bold">${empty qrcForm.custAdditionalDetails.primaryQuotaInGB ? qrcForm.crmPlanMasterPojo.totalDULInGB : qrcForm.custAdditionalDetails.primaryQuotaInGB}</span>
                                </c:if>  at
                                <fmt:formatNumber type="number" maxFractionDigits="0" value="${ qrcForm.crmPlanMasterPojo.primarySpeed div 1024 }" />&nbsp;Mbps
                                <c:if test="${ qrcForm.crmPlanMasterPojo.quotaType ne 'TUL' }">
	                                , and <br>Unlimited at
	                               <%--  ${ qrcForm.crmPlanMasterPojo.secondarySpeed } Kbps--%>
	                                 <c:if test="${ qrcForm.crmPlanMasterPojo.secondarySpeed lt 1024 }">
				 				   		${qrcForm.crmPlanMasterPojo.secondarySpeed}&nbsp;Kbps
									</c:if>
									 <c:if test="${ qrcForm.crmPlanMasterPojo.secondarySpeed div 1024 ge 1 }">
										<fmt:formatNumber type="number" minFractionDigits="0" value="${ qrcForm.crmPlanMasterPojo.secondarySpeed div 1024 }" />&nbsp;Mbps
									</c:if>
								</c:if>
                                <%-- <fmt:formatNumber type="number" maxFractionDigits="0" value="${ qrcForm.crmPlanMasterPojo.primarySpeed div 1024 }" />&nbsp;Mbps <c:if
                                test="${qrcForm.crmPlanMasterPojo.primaryQuota lt 0 }">Unlimited</c:if> <c:if
                                test="${qrcForm.crmPlanMasterPojo.primaryQuota gt -1 }">
                                upto <span class="bold">${empty qrcForm.custAdditionalDetails.primaryQuotaInGB ? qrcForm.crmPlanMasterPojo.totalDULInGB : qrcForm.custAdditionalDetails.primaryQuotaInGB};</span>
                                <br> ${ qrcForm.crmPlanMasterPojo.secondarySpeed } Kbps thereafter
                                </c:if> --%>
                          </span></span>
                        </c:otherwise>
                      </c:choose>
                    </div>
                  </div>
                  <div id="planInfoText" style="width: 300px; margin-left: 50px;"
                    class="floatl margintop10 ${ ((qrcForm.planCategory eq 'Base plan migration') or (qrcForm.planCategory eq 'Booster plan') or (qrcForm.planCategory eq 'Addon Plans') or (qrcForm.planCategory eq 'VAS_MANAGEMENT') or (empty qrcForm.planCategory)) ? 'hidden' : '' }">
                    <label><strong>New Plan Info:</strong></label>
                    <div id="planInfoID" class="floatr"></div>
                  </div>
                </li>
              </ul>
            </div>
            <!-- plan limited or unlimited-->
            <div
              class="floatl clear ${ qrcForm.planCategory eq 'Base plan migration' or qrcForm.planCategory eq 'Plan Renewal' or qrcForm.planCategory eq 'Plan Reactivation' ? '' : 'hidden' } margintop10"
              id="planLimitType">
              <LABEL class="label_radio" for="unlimitedPlanType"> <html:radio styleId="unlimitedPlanType" name="qrcForm"
                  property="planUsageType" value="U" onclick="showPlanUnlimited();">Unlimited</html:radio>
              </LABEL> <LABEL class="label_radio" for="limitedPlanType"> <html:radio styleId="limitedPlanType" name="qrcForm" property="planUsageType"
                  value="L" onclick="showPlanLimited();">Limited</html:radio>
              </LABEL>
            </div>
            <!-- plan migration/plan booster/plan renew/vas -->
            <div id="newPlanType"
              class="floatl clear margintop10 ${ empty qrcForm.planUsageType ? 'hidden' : ( qrcForm.planUsageType eq 'U' or qrcForm.planUsageType eq 'L' ? '' : 'hidden' ) }">
              <logic:equal name="crmRoles" property="available(create_plan_migration_paid,update_plan_migration_paid,create_plan_renew_paid,update_plan_renew_paid,create_plan_reactivation_paid,update_plan_reactivation_paid)" value="true" scope="session">
                <LABEL class="label_radio" for="unlimitedPaid"> <html:radio styleId="unlimitedPaid" name="qrcForm" property="planType"
                    value="PAID" onclick="getTeriffMigrationPlan();">Paid</html:radio>
                </LABEL>
              </logic:equal>
              <logic:equal name="crmRoles" property="available(create_plan_migration_foc,update_plan_migration_foc,create_plan_renew_foc,update_plan_renew_foc,create_plan_reactivation_foc,update_plan_reactivation_foc)" value="true" scope="session">
                <LABEL class="label_radio" for="unlimitedgFoc"> <html:radio styleId="unlimitedgFoc" name="qrcForm" property="planType"
                    value="FOC" onclick="getTeriffMigrationPlan();">FOC</html:radio>
                </LABEL>
              </logic:equal>
              <logic:equal name="crmRoles" property="available(create_plan_migration_app,update_plan_migration_app,create_plan_renew_app,update_plan_renew_app,create_plan_reactivation_app,update_plan_reactivation_app)" value="true" scope="session">
                <LABEL class="label_radio" for="unlimitedgApp"> <html:radio styleId="unlimitedgApp" name="qrcForm" property="planType"
                    value="APP" onclick="getTeriffMigrationPlan();">APP</html:radio>
                </LABEL>
              </logic:equal>
              <logic:equal name="crmRoles" property="available(create_plan_migration_mbo,update_plan_migration_mbo,create_plan_renew_mbo,update_plan_renew_mbo,create_plan_reactivation_mbo,update_plan_reactivation_mbo)" value="true" scope="session">
                <LABEL class="label_radio" for="unlimitedgMbo"> <html:radio styleId="unlimitedgMbo" name="qrcForm" property="planType"
                    value="MBO" onclick="getTeriffMigrationPlan();">MBO</html:radio>
                </LABEL>
              </logic:equal>
              <logic:equal name="crmRoles" property="available(create_plan_migration_retention,update_plan_migration_retention,create_plan_renew_retention,update_plan_renew_retention,create_plan_reactivation_retention,update_plan_reactivation_retention)" value="true"
                scope="session">
                <LABEL class="label_radio" for="unlimitedgRet"> <html:radio styleId="unlimitedgRet" name="qrcForm" property="planType"
                    value="RETENTION" onclick="getTeriffMigrationPlan();">Retention</html:radio>
                </LABEL>
              </logic:equal>
            </div>
            <!-- make new plan table for unlimited -->
            <c:if test="${ empty qrcForm.planType ? false : (qrcForm.planUsageType eq 'U') }">
              <div id="newPlanTable">
                <logic:notEmpty name="qrcForm" property="planMasterList">
                  <p class="floatl clear margintop20">
                    <strong> Base Plan Name<sup class="req">*</sup></strong> <span class="qrcticketDropdownWithoutJs width320Bg width320"
                      style="margin-left: 50px;"> <select name="selectedPlanCode" id="newBasePlanCodeID" onchange="setSelectedPlanInfo(this);"
                      data-quota="${qrcForm.crmPlanMasterPojo.primaryQuota }" style="width: 320px;">
                        <option value="">Please Select</option>
                        <c:forEach items="${ qrcForm.planMasterList }" var="planMasterList">
                          <option value="${planMasterList.planCode }" data-name="${planMasterList.planName }"
                            data-primary="<fmt:formatNumber value="${ planMasterList.primarySpeed div 1024 }" minFractionDigits="0" maxFractionDigits="2" />"
                            data-secondary="${ planMasterList.secondarySpeed }"
                            data-quota="<fmt:formatNumber value="${ planMasterList.primaryQuota lt 0 ? planMasterList.primaryQuota : planMasterList.primaryQuota div 1024 div 1024 div 1024 }" minFractionDigits="0"
                              maxFractionDigits="2" />"
                            data-addonyn="${ planMasterList.addonAllowedYn }" data-usagetype="${planMasterList.planUsageType }"
                            ${qrcForm.selectedPlanCode eq planMasterList.planCode ? 'selected="selected"' : '' }>${planMasterList.planName };
                            <fmt:formatNumber value="${ planMasterList.primarySpeed div 1024 }" minFractionDigits="0" maxFractionDigits="2" /> Mbps
                            <c:if test="${planMasterList.primaryQuota lt 0  or planMasterList.quotaType eq 'TUL' }">Unlimited</c:if>
                            <c:if test="${planMasterList.quotaType ne 'TUL' }">
                            upto
                            <fmt:formatNumber value="${ planMasterList.primaryQuota div 1024 div 1024 div 1024 }" minFractionDigits="0"
                                maxFractionDigits="2" /> GB
                            </c:if> &#64; &#8377;
                            <fmt:formatNumber
                              value="${qrcForm.custDetailsPojo.serviceType eq 'PO' ? planMasterList.rentExclTax : planMasterList.rentInclTax}"
                              minFractionDigits="0" maxFractionDigits="2" /></option>
                          <%-- ${ planMasterList.addonAllowedYn eq 'Y' ? '&radic;' : '&times;' } --%>
                        </c:forEach>
                    </select>
                    </span> <font class="errorTextbox hidden" style="position: static; padding-left: 155px; width: 385px;">Addon Plan not allowed for
                      selected plan. Existing addon will be removed if any.</font>
                  </p>
                  <logic:greaterThan name="qrcForm" property="addonPlanQuota" value="0">
                  <div class="floatl clear margintop20" id="addonTypeText">
				  <label><strong>ADDON Action</strong></label> <span class="basePlanQuota"></span></label>
				  <span class="LmsdropdownWithoutJs" style="margin-left: 59px;">
				     <html:select styleId="addonAction" styleClass="AddonActionCSS" name="qrcForm" property="addonAction">
                      <html:option value="0">Please select</html:option>
                      <html:option value="WA">With ADDON</html:option>
                      <html:option value="WOA">Without ADDON</html:option>
                    </html:select>
                    <font class="errorTextArea" style="left: 410px;"></font>	
					</span>
				  </div>
				  </logic:greaterThan>
                  <div class="floatl clear margintop20">
                    <html:hidden property="addonPlanQuota" styleId="addonPlanQuota" />
                    <ul class="planInfo crm floatl">
                      <li><label><strong>Base Plan DUL</strong></label> <span class="basePlanQuota"></span> <%-- <fmt:formatNumber value="${qrcForm.basePlanQuota }" minFractionDigits="0" maxFractionDigits="2" pattern="#.## GB"/> --%></li>
                      <li><label><strong>Current Addon DUL</strong></label> <span><fmt:formatNumber value="${qrcForm.addonPlanQuota }"
                            minFractionDigits="0" maxFractionDigits="2" pattern="#.## GB" /> (To Change Addon Plan, Please Click On <span
                          class="bold">Add-on Plans Button</span>)</span></li>
                      <li><label><strong>Total DUL</strong></label> <span class="totalQuota"></span> <%-- <fmt:formatNumber value="${qrcForm.totalQuota }" minFractionDigits="0" maxFractionDigits="2" pattern="#.## GB" /> --%></li>
                    </ul>
                  </div>
                </logic:notEmpty>
                <logic:empty name="qrcForm" property="planMasterList">
                  <font color="red" style="position: static; float: left; clear: both;">There is no plan available for selected plan type.</font>
                </logic:empty>
              </div>
            </c:if>
            <%-- addon change/removal --%>
            <div id="addonPlanOptions" class="floatl clear ${ qrcForm.planCategory eq 'Addon Plans' ? '' : 'hidden' } margintop10">
              <label class="label_radio" for="addonPlanChange"> <html:radio styleId="addonPlanChange" name="qrcForm" property="planUsageType"
                  value="CHANGE_ADDON" onclick="showAddonChange();">Inc/Dec DUL</html:radio>
              </label> <label class="label_radio" for="addonPlanRemove" id="add_opacity"> <html:radio styleId="addonPlanRemove" name="qrcForm" property="planUsageType"
                  value="REMOVE_ADDON" onclick="showAddonRemove();">Remove Addon</html:radio>
              </label>
            </div>
            <div id="ADDON_PAID_FOC_DIV"
              class="floatl clear margintop10 ${ empty qrcForm.planUsageType ? 'hidden' : ( qrcForm.planUsageType eq 'CHANGE_ADDON' ? '' : 'hidden' ) }">
              <label class="label_radio" for="addonPlanPaid"> <html:radio styleId="addonPlanPaid" name="qrcForm" property="planType"
                  value="ADDON_PAID" onclick="getTeriffMigrationPlan();">Paid</html:radio>
              </label>
              <logic:equal name="crmRoles" property="available(view_addon_foc,create_addon_foc,update_addon_foc)" value="true" scope="session">
               <label class="label_radio" for="addonPlanFoc"> 
              <html:radio styleId="addonPlanFoc" name="qrcForm" property="planType"
                  value="ADDON_FOC" onclick="getTeriffMigrationPlan();">FOC</html:radio>
              </label>
              </logic:equal>
            </div>
            <!-- plan usage/ plan speed -->
            <div id="PLAN_BOOSTER_USAGE_SPEED_DIV" class="floatl clear margintop10 ${ qrcForm.planCategory eq 'Booster plan' ? '' : 'hidden' }">
              <LABEL class="label_radio" for="planUsage"> <html:radio styleId="planUsage" name="qrcForm" property="planUsageType"
                  value="BOOSTER_USAGE" onclick="showPaidFocDiv(this.value);">Usage</html:radio>
              </LABEL> <LABEL class="label_radio" for="planSpeed"> <html:radio styleId="planSpeed" name="qrcForm" property="planUsageType"
                  value="BOOSTER_SPEED" onclick="showPaidFocDiv(this.value);">Speed</html:radio>
              </LABEL>
            </div>
            <!-- plan paid/plan foc -->
            <div id="PLAN_BOOSTER_PAID_FOC_DIV"
              class="floatl clear margintop10 ${ empty qrcForm.planUsageType ? 'hidden' : ( qrcForm.planUsageType eq 'BOOSTER_USAGE' or qrcForm.planUsageType eq 'BOOSTER_SPEED' ? '' : 'hidden' ) }">
              <LABEL class="label_radio" for="planBoosterPaid"> <html:radio styleId="planBoosterPaid" name="qrcForm" property="planType"
                  value="BOOSTER_PAID" onclick="showPlanBoosterData();">Paid</html:radio>
              </LABEL> 
               <logic:equal name="crmRoles" property="available(view_booster_foc,create_booster_foc,update_booster_foc)" value="true" scope="session">
              <LABEL class="label_radio" for="planBoosterFoc"> <html:radio styleId="planBoosterFoc" name="qrcForm" property="planType"
                  value="BOOSTER_FOC" onclick="showPlanBoosterData();">FOC</html:radio>
              </LABEL>
              </logic:equal>
            </div>
            <div id="PLAN_BOOSTER_SPEED_DATA" class="floatl clear margintop10 ${qrcForm.planUsageType eq 'BOOSTER_SPEED' ? '' : 'hidden'  }">
              <logic:notEmpty name="qrcForm" property="planMasterList">
                <label class="inlineBlock"><strong>Speed</strong></label>
                <span class="qrcticketDropdownWithoutJs inlineBlock marginleft10" style='margin-left: 59px !important;'
                  data-plan="<bean:write name="crmRoles" property="displayEnum(BASE,${ qrcForm.planDetailsPojo.basePlanCode })" scope="session" />"
                  data-primary="<fmt:formatNumber type="number" maxFractionDigits="0" value="${ qrcForm.crmPlanMasterPojo.primarySpeed div 1024 }" />"
                  data-secondary="${qrcForm.crmPlanMasterPojo.planUsageType eq 'L' ? '' : qrcForm.crmPlanMasterPojo.secondarySpeed }"
                  data-quota='<fmt:formatNumber maxFractionDigits="2" minFractionDigits="0" value="${qrcForm.crmPlanMasterPojo.primaryQuota lt 0 ? qrcForm.crmPlanMasterPojo.primaryQuota : qrcForm.crmPlanMasterPojo.primaryQuota div 1024 div 1024 div 1024}" />'
                  data-usagetype="${qrcForm.crmPlanMasterPojo.planUsageType }"> <html:select name="qrcForm" property="planCode"
                    onchange="newPlanInfoBoosterSpeed(this);">
                    <option value="">Please Select</option>
                    <logic:iterate id="planMasterList" name="qrcForm" property="planMasterList">
                      <option value="${planMasterList.planCode }" data-speed="${planMasterList.primarySpeed }">${planMasterList.planName }</option>
                    </logic:iterate>
                    <%-- <html:optionsCollection name="qrcForm" property="planMasterList" label="planName" value="planCode" /> --%>
                  </html:select>
                </span>
              </logic:notEmpty>
              <logic:empty name="qrcForm" property="planMasterList">
                <font color="red">No Speed Booster found for current selection</font>
              </logic:empty>
            </div>
            <!-- plan table dul/unit price/quantity/total price/ total dul -->
            <div id="PLAN_BOOSTER_USAGE_DATA" class="floatl clear margintop10 ${qrcForm.planUsageType eq 'BOOSTER_USAGE' ? '' : 'hidden'  }">
              <logic:notEmpty name="qrcForm" property="planMasterList">
                <ul class="planListTable">
                  <li class="colHead">
                    <div class="col_4 noborder"></div>
                    <div class="col_8">Name</div>
                    <div class="col_8">DUL</div>
                    <div class="col_8">Unit Price (&#8377;)</div>
                    <div class="col_8">Quantity</div>
                    <div class="col_8">Total Price (&#8377;)</div>
                    <div class="col_8">Total Dul</div>
                  </li>
                  <c:forEach items="${ qrcForm.planMasterList }" var="planMasterList" varStatus="current">
                    <li>
                      <div class="col_4 noborder" data-index="${current.index }"
                        data-plan="<bean:write name="crmRoles" property="displayEnum(BASE,${ qrcForm.planDetailsPojo.basePlanCode })" scope="session" />"
                        data-primary="<fmt:formatNumber type="number" maxFractionDigits="0" value="${ qrcForm.crmPlanMasterPojo.primarySpeed div 1024 }" />"
                        data-secondary="${qrcForm.crmPlanMasterPojo.planUsageType eq 'L' ? '' : qrcForm.crmPlanMasterPojo.secondarySpeed }"
                        data-quota='<fmt:formatNumber maxFractionDigits="2" minFractionDigits="0" value="${qrcForm.crmPlanMasterPojo.primaryQuota lt 0 ? qrcForm.crmPlanMasterPojo.primaryQuota : qrcForm.crmPlanMasterPojo.primaryQuota div 1024 div 1024 div 1024}" />'
                        data-usagetype="${qrcForm.crmPlanMasterPojo.planUsageType }">
                        <html:checkbox name="planMasterList" property="editable" styleId="rowID${current.index}" styleClass="chkBooster" value="true"
                          indexed="true" onchange="javascript:activeDeactivateRow(${current.index});"></html:checkbox>
                      </div> <html:hidden property="primaryQuota" name="planMasterList" styleId="boost${current.index}" />
                       <div class="col_8">${ planMasterList.planName}</div>
                      <div class="col_8"><fmt:formatNumber value="${ planMasterList.primaryQuota div 1024 div 1024 div 1024 div 1024}" minFractionDigits="0"
                                maxFractionDigits="2" />&nbsp;GB</div> <html:hidden property="rentInclTax" name="planMasterList"
                        styleId="unitPrice${current.index}" />
                      <div class="col_8">${ planMasterList.rentInclTax}</div>
                      <div class="col_8">
                        <html:text styleClass="reasonTextbox ${planMasterList.editable ? '' : 'gray_text'}" styleId="enterQuantity${current.index}"
                          readonly="${planMasterList.editable ? 'false' : 'true'}" onblur="calculateBooster(this.value,${current.index})"
                          indexed="true" name="planMasterList" property="quantity" style="width: 92px;" />
                        <!--<input type="text" value="Enter Quantity" onblur="if(this.value == '') { this.value='Enter Quantity'}" onfocus="if (this.value == 'Enter Quantity') {this.value=''}" />-->
                      </div>
                      <div class="col_8" id="totalPrice${current.index}">
                        <fmt:formatNumber value="${planMasterList.editable ? (planMasterList.quantity * planMasterList.rentInclTax) : 0 }"
                          minFractionDigits="0" maxFractionDigits="2" />
                      </div>
                      <div class="col_8" id="totalDul${current.index}">
                        <fmt:formatNumber value="${planMasterList.editable ? ( planMasterList.quantity * planMasterList.primaryQuota div 1024 div 1024 div 1024 div 1024) : 0}" minFractionDigits="0"
                                maxFractionDigits="2" />&nbsp;GB
                      </div>
                    </li>
                  </c:forEach>
                </ul>
              </logic:notEmpty>
              <logic:empty name="qrcForm" property="planMasterList">
                <font color="red">No Usage Booster found for current selection</font>
              </logic:empty>
            </div>
            <!-- VAS Activation Deactivation DIVs Start -->
            <!-- VAS Activation/Deactivation Div -->
            <div id="VAS_ACTIVATION_DEACTIVATION_DIV" class="floatl clear margintop10 ${ qrcForm.planCategory eq 'VAS_MANAGEMENT' ? '' : 'hidden' }">
              <logic:equal name="crmRoles" property="available(create_vas_subscription,update_vas_subscription)" value="true" scope="session">
                <LABEL class="label_radio" for="vasActionationID"> <html:radio styleId="vasActionationID" name="qrcForm"
                    property="planUsageType" value="Smart broadband activation" onclick="showPaidFocDivVAS(this.value);">Activation</html:radio>
                </LABEL>
              </logic:equal>
              <logic:equal name="crmRoles" property="available(delete_vas_subscription)" value="true" scope="session">
                <LABEL class="label_radio" for="vasDeactivationID"> <html:radio styleId="vasDeactivationID" name="qrcForm"
                    property="planUsageType" value="Smart broadband deactivation" onclick="showPaidFocDivVAS(this.value);">Deactivation</html:radio>
                </LABEL>
              </logic:equal>
            </div>
            <!-- VAS Paid/FOC Div -->
            <div id="VAS_PAID_FOC_DIV"
              class="floatl clear margintop10 ${ empty qrcForm.planUsageType ? 'hidden' : ( qrcForm.planUsageType eq 'Smart broadband activation' or qrcForm.planUsageType eq 'Smart broadband deactivation' ? '' : 'hidden' ) }">
              <LABEL class="label_radio" for="vasPaidID"> <html:radio styleId="vasPaidID" name="qrcForm" property="planType" value="VAS_PAID"
                  onclick="showPlanBoosterData();">Paid</html:radio>
              </LABEL> <LABEL class="label_radio" for="vasFOCID"> <html:radio styleId="vasFOCID" name="qrcForm" property="planType" value="VAS_FOC"
                  onclick="showPlanBoosterData();">FOC</html:radio>
              </LABEL>
            </div>
            <!-- To Activate VAS Div -->
            <div id="VAS_ACITIVATION_DATA_DIV"
              class="floatl clear margintop10 ${qrcForm.planUsageType eq 'Smart broadband activation' and (qrcForm.planType eq 'VAS_PAID' or qrcForm.planType eq 'VAS_FOC' ) ? '' : 'hidden'  }">
              <logic:notEmpty name="qrcForm" property="vasToActivatePojos">
                <ul class="planListTable">
                  <li class="colHead">
                    <div class="col_4 noborder"></div>
                    <div class="col_8">VAS Name</div>
                    <div class="col_8">VAS Price (Taxes Excluded)</div>
                    <div class="col_8">VAS Price (Taxes Included)</div>
                  </li>
                  <c:forEach items="${ qrcForm.vasToActivatePojos }" var="vasToActivatePojos" varStatus="current">
                    <li>
                      <div class="col_4 noborder">
                        <html:checkbox name="vasToActivatePojos" property="editable" styleId="rowID${current.index}" value="true" indexed="true"
                          onchange="javascript:activeDeactivateRow(${current.index});"></html:checkbox>
                      </div>
                      <div class="col_8">${ vasToActivatePojos.planName}</div>
                      <div class="col_8">${ vasToActivatePojos.rentExclTax}</div>
                      <div class="col_8">${ vasToActivatePojos.rentInclTax}</div>
                    </li>
                  </c:forEach>
                </ul>
              </logic:notEmpty>
              <logic:empty name="qrcForm" property="vasToActivatePojos">
                <font color="red">No VAS available for this customer</font>
              </logic:empty>
            </div>
            <!-- To Deactivate VAS Div -->
            <div id="VAS_DEACITIVATION_DATA_DIV"
              class="floatl clear margintop10 ${qrcForm.planUsageType eq 'Smart broadband deactivation' and (qrcForm.planType eq 'VAS_PAID' or qrcForm.planType eq 'VAS_FOC' ) ? '' : 'hidden'  }">
              <logic:notEmpty name="qrcForm" property="vasToDeactivatePojos">
                <ul class="planListTable">
                  <li class="colHead">
                    <div class="col_4 noborder"></div>
                    <div class="col_8">VAS Name</div>
                    <div class="col_8">VAS Price (Taxes Excluded)</div>
                    <div class="col_8">VAS Price (Taxes Included)</div>
                  </li>
                  <c:forEach items="${ qrcForm.vasToDeactivatePojos }" var="vasToDeactivatePojos" varStatus="current">
                    <li>
                      <div class="col_4 noborder">
                        <html:checkbox name="vasToDeactivatePojos" property="editable" styleId="rowID${current.index}" value="true" indexed="true"
                          onchange="javascript:activeDeactivateRow(${current.index});"></html:checkbox>
                      </div>
                      <div class="col_8">${ vasToDeactivatePojos.planName}</div>
                      <div class="col_8">${ vasToDeactivatePojos.rentExclTax}</div>
                      <div class="col_8">${ vasToDeactivatePojos.rentInclTax}</div>
                    </li>
                  </c:forEach>
                </ul>
              </logic:notEmpty>
              <logic:empty name="qrcForm" property="vasToDeactivatePojos">
                <font color="red">Currently no VAS activated on customer</font>
              </logic:empty>
            </div>
            <!-- VAS Activation Deactivation DIVs End -->
            <!-- limited plan list -->
            <c:if test="${ empty qrcForm.planType ? false : (qrcForm.planUsageType eq 'L') }">
              <div id="planChargesTable" class="floatl clear ${ empty qrcForm.planType ? 'hidden' : (qrcForm.planUsageType eq 'L' ? '' : 'hidden' ) }">
                <logic:notEmpty name="qrcForm" property="planMasterList">
                  <p class="floatl clear margintop20">
                    <strong>Base Plan Name</strong> <span class="qrcticketDropdownWithoutJs width320Bg width320" style="margin-left: 50px;"> <select
                      name="selectedPlanCode" id="newBasePlanCodeID" onchange="setSelectedPlanInfo(this);"
                      data-quota="${qrcForm.crmPlanMasterPojo.primaryQuota }" style="width: 320px;">
                        <option value="">Please Select</option>
                        <c:forEach items="${ qrcForm.planMasterList }" var="planMasterList">
                          <option value="${planMasterList.planCode }" data-name="${planMasterList.planName }"
                            data-primary="${ planMasterList.primarySpeed div 1024 }" data-secondary="${ planMasterList.secondarySpeed }"
                            data-quota="${ planMasterList.primaryQuota div 1024 div 1024 div 1024 }" data-addonyn="${ planMasterList.addonAllowedYn }"
                            data-usagetype="${planMasterList.planUsageType }"
                            ${qrcForm.selectedPlanCode eq planMasterList.planCode ? 'selected="selected"' : '' }>${planMasterList.planName };
                            <fmt:formatNumber value="${ planMasterList.primarySpeed div 1024 }" minFractionDigits="0" maxFractionDigits="2" /> Mbps;
                            <fmt:formatNumber value="${ planMasterList.primaryQuota div 1024 div 1024 div 1024 }" minFractionDigits="0"
                              maxFractionDigits="2" /> GB; &#64; &#8377;
                            <fmt:formatNumber
                              value="${qrcForm.custDetailsPojo.serviceType eq 'PO' ? planMasterList.rentExclTax : planMasterList.rentInclTax}"
                              minFractionDigits="0" maxFractionDigits="2" /></option>
                        </c:forEach>
                    </select>
                    </span> <font class="errorTextbox hidden" style="position: static; padding-left: 155px; width: 385px;">Addon Plan not allowed for
                      selected plan. Existing addon will be removed if any.</font>
                  </p>
                  <div class="floatl clear margintop20">
                    <html:hidden property="addonPlanQuota" styleId="addonPlanQuota" />
                    <ul class="planInfo crm floatl">
                      <li><label><strong>Base Plan DUL</strong></label> <span class="basePlanQuota"></span> <%-- <fmt:formatNumber value="${qrcForm.basePlanQuota }" minFractionDigits="0" maxFractionDigits="2" pattern="#.## GB" /> --%></li>
                      <li><label><strong>Current Addon DUL</strong></label> <span><fmt:formatNumber value="${qrcForm.addonPlanQuota }"
                            minFractionDigits="0" maxFractionDigits="2" pattern="#.## GB" /> (To Change Addon Plan, Please Click On <span
                          class="bold">Add-on Plans Button</span>)</span></li>
                      <li><label><strong>Total DUL</strong></label> <span class="totalQuota"></span> <%-- <fmt:formatNumber value="${qrcForm.totalQuota }" minFractionDigits="0" maxFractionDigits="2" pattern="#.## GB" /> --%></li>
                    </ul>
                  </div>
                </logic:notEmpty>
                <logic:empty name="qrcForm" property="planMasterList">
                  <font color="red">There is no plan available for selected plan type.</font>
                </logic:empty>
              </div>
            </c:if>
            <logic:notEmpty name="qrcForm" property="planMasterList">
              <div id="otherDetailsId" class="floatl clear ${ empty qrcForm.planType ? 'hidden' : '' }">
                <!-- plan table(date/amount/mode/status) -->
                <div id="planPaymentTable" class="floatl clear ${ qrcForm.planType eq 'APP' or qrcForm.planType eq 'MBO' ? '' : 'hidden' }">
                  <logic:notEmpty name="qrcForm" property="custPaymentDetailsPojos">
                    <ul class="planListTable">
                      <li class="colHead">
                        <div class="col5">Date</div>
                        <div class="col5">Amount (&#8377;)</div>
                        <div class="col6">Mode</div>
                        <div class="col5">Status</div>
                        <div class="col5">Tx Id</div>
                      </li>
                      <c:forEach items="${ qrcForm.custPaymentDetailsPojos }" var="custPaymentDetailsPojos" varStatus="current">
                        <li>
                          <div class="col5">
                            <bean:write name="crmRoles" property="xmlDate(${custPaymentDetailsPojos.paymentDate})" scope="session" />
                          </div>
                          <div class="col5">
                            <fmt:formatNumber value="${custPaymentDetailsPojos.amount}" minFractionDigits="2" maxFractionDigits="2" />
                          </div>
                          <div class="col6">
                            <bean:write name="crmRoles" property="displayEnum(PaymentMode,${custPaymentDetailsPojos.paymentMode})" scope="session" />
                          </div>
                          <div class="col5">
                            <bean:write name="crmRoles" property="displayEnum(PaymentStatus,${custPaymentDetailsPojos.paymentStatus})" scope="session" />
                          </div>
                          <div class="col5">
                            <logic:notEmpty name="custPaymentDetailsPojos" property="receiptNo">
                              <bean:write name="custPaymentDetailsPojos" property="receiptNo" />
                            </logic:notEmpty>
                            <logic:notEmpty name="custPaymentDetailsPojos" property="chequeDdNo">
                              <bean:write name="custPaymentDetailsPojos" property="chequeDdNo" />
                            </logic:notEmpty>
                            <logic:notEmpty name="custPaymentDetailsPojos" property="transactionId">
                              <bean:write name="custPaymentDetailsPojos" property="transactionId" />
                            </logic:notEmpty>
                          </div>
                        </li>
                      </c:forEach>
                    </ul>
                  </logic:notEmpty>
                </div>
                <!-- plan info and ticket table -->
                <div id="planTicketTable" class="floatl clear ${ empty qrcForm.planType ? 'hidden' : '' }">
                  <div class="clear floatl" id="ticketDivId" style="position: relative">
                    <logic:notEmpty property="srTicketsPojos" name="qrcForm">
                      <input type="hidden" id="ticketListAvailID" value="Y" />
                      <ul class="planListTable floatl nMarginB nMarginT ${qrcForm.planCategory eq 'Base plan migration' or qrcForm.planCategory eq 'Plan Renewal' ? '' : 'hidden'}">
                        <li class="colHead">
                          <div class="noborder">Open/Reopen ticket in the same sub-sub category</div>
                        </li>
                        <li>
                          <div class="col4 noborder"></div>
                          <div class="col5">
                            <strong>S.R. No.</strong>
                          </div>
                          <div class="col14">
                            <strong>Category</strong>
                          </div>
                          <div class="col14">
                            <strong>Sub Category</strong>
                          </div>
                          <div class="col15">
                            <strong>Sub Sub Category </strong>
                          </div>
                          <div class="col14">
                            <strong>Action</strong>
                          </div>
                        </li>
                        <c:forEach items="${qrcForm.srTicketsPojos}" var="srTicketsPojos" varStatus="current">
                          <li>
                            <div class="col4 noborder">
                              <html:checkbox property="editable" name="srTicketsPojos" value="true" indexed="true"></html:checkbox>
                            </div>
                            <div class="col5">
                              <a href="javascript:viewQRC('${srTicketsPojos.ticketId}','false','qrcTicketView',0)"
                                style="color: blue; text-decoration: underline;">${srTicketsPojos.srId}</a>
                            </div>
                            <div class="col14">${srTicketsPojos.qrcCategory}</div>
                            <div class="col14">${srTicketsPojos.qrcSubCategory}</div>
                            <div class="col15">${srTicketsPojos.qrcSubSubCategory}</div>
                            <div class="col14">
                              <a onclick="return cancelPlanMigration();" href="#" style="color: blue; text-decoration: underline;">Cancel</a>
                            </div> <%-- manageQrc.do?method=cancelPlanMigration --%>
                          </li>
                          <%-- <li>
                <div class="col4 noborder">
                <html:checkbox property="editable" name="srTicketsPojos" value="true" indexed="true"></html:checkbox>
                </div>
                <div class="col5">${srTicketsPojos.srId}</div>
                <div class="col7">${srTicketsPojos.qrcCategory}</div>
                <div class="col7">${srTicketsPojos.qrcSubCategory} </div>
                <div class="col13">${srTicketsPojos.qrcSubSubCategory}</div>
              </li> --%>
                        </c:forEach>
                      </ul>
                    </logic:notEmpty>
                    <font style="position: absolute; top: 100%;"></font>
                  </div>
                  <ul class="planInfo floatl clear">
                    <li>
                      <div id="adviceDivId" class="floatl clear hidden adviceDiv margintop20">
                        <strong>Advice</strong> <span id="adviceId"></span>
                      </div>
                    </li>
                    <li>
                      <p class="floatl clear margintop20" style="margin-bottom: 20px;">
                        <strong>Activation time <span class="error_message verticalalignTop">*</span></strong> <span
                          class="marginleft10 ${ qrcForm.planCategory eq 'Base plan migration' ? '' : 'hidden' }"> <span
                          class="LmsdropdownWithoutJs"> <html:select property="activationTime" name="qrcForm" styleId="activationPopUpId"
                              onchange="setPlanAdvice(this.value);" disabled="${ qrcForm.planCategory eq 'Base plan migration' ? 'false' : 'true' }">
                            </html:select>
                        </span>
                        </span> <span class="marginleft10 ${ qrcForm.planCategory eq 'Base plan migration' ? 'hidden' : '' }"
                          style="display: inline-block; margin-top: 5px;"> Immediate <html:hidden property="activationTime" name="qrcForm"
                            value="Immediate"
                            disabled="${ qrcForm.planCategory eq 'Plan Renewal' or qrcForm.planCategory eq 'Plan Reactivation' ? 'true' : 'false' }" />
                        </span> <font class="errorTextbox" style="top: 30px; left: 110px;"></font>
                      </p>
                    </li>
                    <c:if test="${ qrcForm.planCategory eq 'Plan Reactivation' }">
                      <li>
                        <p class="floatl clear " style="margin-bottom: 0px;">
                          <strong>Ticket ID</strong>
                          <html:text property="srTicketNo" name="qrcForm" maxlength="20" style="margin-left: 50px;" styleClass="textbox"
                            styleId="ticketId"></html:text>
                        </p>
                      </li>
                    </c:if>
                    <li>
                      <p class="floatl clear">
                        <strong> Remarks <span class="error_message verticalalignTop">*</span></strong>
                        <html:textarea property="remarksPojo.remarks" name="qrcForm" styleClass="LmsRemarkstextarea" styleId="bubRemarksId"
                          style='margin-left: 40px;'></html:textarea>
                        <font class="errorTextArea" style="left: 110px; position: relative;"></font>
                      </p>
                    </li>
                  </ul>
                  <p class="clear"></p>
                </div>
              </div>
              <div class="floatr inner_right">
                <a href="#" id="submit_parametergroup" class="main_button" onclick="return saveCustomerPlanMigration();">Submit</a>
              </div>
            </logic:notEmpty>
            <%-- addon list not empty and plan category addon --%>
            <c:if test="${qrcForm.planCategory eq 'Addon Plans'}">
              <%-- and not(empty qrcForm.addonPlanMasterList) --%>
              <div id="newPlanTable">
                <c:if test="${empty qrcForm.planType ? false : (qrcForm.planUsageType eq 'CHANGE_ADDON' and not(empty qrcForm.addonPlanMasterList)) }">
                  <p class="floatl clear margintop20">
                    <strong> Add-on Plan Name<sup class="req">*</sup></strong> <span class="qrcticketDropdownWithoutJs" style="margin-left: 40px;">
                      <c:set var="k" value="0"></c:set> <select name="tariffAddonPlan" id="newAddonPlanCodeID"
                      onchange="setPlanCode(this,this.value,'${qrcForm.crmPlanMasterPojo.primaryQuota }');"
                      data-plan="<bean:write name="crmRoles" property="displayEnum(BASE,${ qrcForm.planDetailsPojo.basePlanCode })" scope="session" />"
                      data-primary="<fmt:formatNumber type="number" maxFractionDigits="0" value="${ qrcForm.crmPlanMasterPojo.primarySpeed div 1024 }" />"
                      data-secondary="${qrcForm.crmPlanMasterPojo.planUsageType eq 'L' ? '' : qrcForm.crmPlanMasterPojo.secondarySpeed }"
                      data-quota='<fmt:formatNumber maxFractionDigits="2" minFractionDigits="0" value="${qrcForm.crmPlanMasterPojo.primaryQuota lt 0 ? qrcForm.crmPlanMasterPojo.primaryQuota : qrcForm.crmPlanMasterPojo.primaryQuota div 1024 div 1024 div 1024}" />'
                      data-usagetype="${qrcForm.crmPlanMasterPojo.planUsageType }">
                        <option value="">Please Select</option>
                        <c:forEach items="${ qrcForm.addonPlanMasterList }" var="addonPlanMasterList">
                          <c:set var="k" value="${ addonPlanMasterList.primaryQuota div 1024 div 1024 div 1024 }"></c:set>
                          <option value="${addonPlanMasterList.planCode}" data-quota="${k}"
                            ${qrcForm.addonPlanCode eq addonPlanMasterList.planCode ? 'selected="selected"' : '' }>${addonPlanMasterList.planName }</option>
                        </c:forEach>
                    </select>
                    </span> <font class="errorTextbox hidden" style="position: static; padding-left: 155px; width: 385px;"></font>
                  </p>
                  <div class="floatl clear margintop20">
                    <ul class="planInfo crm floatl">
                      <li><label><strong>Current Base Plan DUL</strong></label> <span><fmt:formatNumber value="${qrcForm.basePlanQuota }"
                            minFractionDigits="0" maxFractionDigits="2" pattern="#.## GB" /> (To Change Base Plan, Please Click On <span class="bold">Plan
                            Migration Button</span>)</span></li>
                      <li><label><strong>Addon DUL</strong></label> <span class="addonPlanQuota"></span> <%-- <fmt:formatNumber value="${qrcForm.addonPlanQuota }" minFractionDigits="0" maxFractionDigits="2" pattern="#.## GB" /> --%></li>
                      <li><label><strong>Total DUL</strong></label> <span class="totalQuota"></span> <%-- <fmt:formatNumber value="${qrcForm.totalQuota }" minFractionDigits="0" maxFractionDigits="2" pattern="#.## GB" /> --%></li>
                    </ul>
                  </div>
                </c:if>
                <c:if test="${empty qrcForm.planType ? false : (qrcForm.planUsageType eq 'CHANGE_ADDON' and (empty qrcForm.addonPlanMasterList)) }">
                  <font color="red" style="position: static; float: left; clear: both;">There is no addon plan available for selected plan
                    type.</font>
                </c:if>
              </div>
              <c:if test="${qrcForm.planUsageType eq 'CHANGE_ADDON' ? not empty qrcForm.planType : qrcForm.planUsageType eq 'REMOVE_ADDON' }">
                <div id="otherDetailsId" class="floatl clear ">
                  <div id="planTicketTable" class="floatl clear ">
                    <!-- addon plan ticket table -->
                    <div class="clear floatl" id="ticketDivId" style="position: relative">
                      <logic:notEmpty property="srTicketsPojos" name="qrcForm">
                        <input type="hidden" id="ticketListAvailID" value="Y" />
                        <ul class="planListTable floatl ${qrcForm.planUsageType eq 'REMOVE_ADDON' ? 'margintop20' : 'nMarginB nMarginT'}">
                          <li class="colHead">
                            <div class="noborder">Open/Reopen ticket in the same sub-sub category</div>
                          </li>
                          <li>
                            <div class="col4 noborder"></div>
                            <div class="col5">
                              <strong>S.R. No.</strong>
                            </div>
                            <div class="col14">
                              <strong>Category</strong>
                            </div>
                            <div class="col14">
                              <strong>Sub Category</strong>
                            </div>
                            <div class="col15">
                              <strong>Sub Sub Category </strong>
                            </div>
                            <div class="col14">
                              <strong>Action</strong>
                            </div>
                          </li>
                          <c:forEach items="${qrcForm.srTicketsPojos}" var="srTicketsPojos" varStatus="current">
                            <li>
                              <div class="col4 noborder">
                                <html:checkbox property="editable" name="srTicketsPojos" value="true" indexed="true"></html:checkbox>
                              </div>
                              <div class="col5">
                                <a href="javascript:viewQRC('${srTicketsPojos.ticketId}','false','qrcTicketView','0')"
                                  style="color: blue; text-decoration: underline;">${srTicketsPojos.srId}</a>
                              </div>
                              <div class="col14">${srTicketsPojos.qrcCategory}</div>
                              <div class="col14">${srTicketsPojos.qrcSubCategory}</div>
                              <div class="col15">${srTicketsPojos.qrcSubSubCategory}</div>
                              <div class="col14">
                                <a onclick="return cancelPlanMigration();" href="#" style="color: blue; text-decoration: underline;">Cancel</a>
                              </div> <%-- manageQrc.do?method=cancelPlanMigration --%>
                            </li>
                          </c:forEach>
                        </ul>
                      </logic:notEmpty>
                      <font style="position: absolute; top: 100%;"></font>
                    </div>
                    <c:if test="${not empty qrcForm.addonPlanMasterList or qrcForm.planUsageType eq 'REMOVE_ADDON'}">
                      <ul class="planInfo floatl clear ${qrcForm.planUsageType eq 'REMOVE_ADDON' ? 'margintop20' : ''}">
                        <%-- <c:if test="${empty qrcForm.planType ? false : qrcForm.planUsageType eq 'CHANGE_ADDON'  }"> --%>
                        <li>
                          <div id="adviceDivId" class="floatl clear hidden adviceDiv margintop20">
                            <strong>Advice</strong> <span id="adviceId"></span>
                          </div>
                        </li>
                        <li>
                          <p class="floatl clear margintop20" style="margin-bottom: 20px;">
                            <strong>Activation time <span class="error_message verticalalignTop">*</span></strong> <span
                              class="marginleft10 ${ not qrcForm.addonAllowed ? '' : 'hidden' }"> <span class="LmsdropdownWithoutJs"> <html:select
                                  property="activationTime" name="qrcForm" styleId="activationPopUpId" onchange="setPlanAdvice(this.value);"
                                  disabled="${qrcForm.addonAllowed }">
                                </html:select>
                            </span>
                            </span> <span class="marginleft10 ${ qrcForm.addonAllowed ? '' : 'hidden' }">Next Billing Cycle<html:hidden
                                property="activationTime" name="qrcForm" value="Next Billing Cycle" disabled="${not qrcForm.addonAllowed}" />
                            </span> <font class="errorTextbox" style="top: 30px; left: 110px;"></font>
                          </p>
                        </li>
                        <%-- </c:if> --%>
                        <li>
                          <p class="floatl clear">
                            <strong> Remarks <span class="error_message verticalalignTop">*</span></strong>
                            <html:textarea property="remarksPojo.remarks" name="qrcForm" styleClass="LmsRemarkstextarea" styleId="bubRemarksId"
                              style='margin-left: 40px;'></html:textarea>
                            <font class="errorTextArea" style="left: 110px; position: relative;"></font>
                          </p>
                        </li>
                        <li>
                          <div class="floatr inner_right">
                            <a href="#" id="submit_parametergroup" class="main_button" onclick="return saveCustomerPlanMigration();">Submit</a>
                          </div>
                        </li>
                      </ul>
                    </c:if>
                  </div>
                </div>
              </c:if>
            </c:if>
            <%--
            <div id="addonRemoveRemarks" class="hidden">
              <p class="floatl clear margintop20">
                <strong> Remarks <span class="error_message verticalalignTop">*</span></strong>
                <html:textarea property="remarksPojo.remarks" name="qrcForm" styleClass="LmsRemarkstextarea" styleId="bubRemarksId" disabled="true"
                  style='margin-left: 40px;'></html:textarea>
                <font class="errorTextArea" style="left: 110px; position: relative;"></font>
              </p>
              <div class="floatr inner_right">
                <a href="#" id="submit_parametergroup" class="main_button" onclick="saveCustomerPlanMigration();">Submit</a>
              </div>
            </div>
             --%>
            <br class="clear" />
          </div>
          <p class="clear"></p>
        </html:form>
        <p class="clear"></p>
      </div>
      <p class="clear"></p>
    </div>