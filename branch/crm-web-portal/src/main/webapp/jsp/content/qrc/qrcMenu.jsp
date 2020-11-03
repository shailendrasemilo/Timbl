<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="/WEB-INF/tlds/struts-logic.tld" prefix="logic"%>
<ul class="qrc">
  <logic:equal name="crmRoles" property="available(view_qrc)" value="true" scope="session">
    <li class="${ activeMenu eq 'customerEnquiry' ? 'active' : '' }"><a href="manageQrc.do?method=searchCustomer">Customer Enquiry</a></li>
  </logic:equal>
  <logic:equal name="crmRoles" property="available(view_qrc_tkt)" value="true" scope="session">
      <li class="${ activeMenu eq 'tickets' ? 'active' : '' }"><a href="manageQrc.do?method=ticketPage">Tickets</a></li>
  </logic:equal>
  <logic:equal name="crmRoles" property="available(view_qrc)" value="true" scope="session">
    <li class="${ activeMenu eq 'customerInteraction' ? 'active' : '' }"><a href="manageQrc.do?method=customerInteractionPage">Customer Interaction</a></li>
  </logic:equal>
  <c:if test="${ not(empty qrcForm.custDetailsPojo.activationDate) }">
    <c:if test="${ qrcForm.custDetailsPojo.status eq 'A' or qrcForm.custDetailsPojo.status eq 'B'}">
      <logic:equal name="crmRoles" property="available(view_qrc_shifting)" value="true" scope="session">
        <li class="${ activeMenu eq 'addressChange' ? 'active' : '' }"><a href="shiftingWorkflow.do?method=shiftingInitiationPage&customerId=${qrcForm.custDetailsPojo.customerId}">Address Shifting</a></li>
      </logic:equal>
    </c:if>
    <%-- customer barring link --%>
    <logic:equal name="crmRoles" property="available(update_qrc_cs)" value="true" scope="session">
      <c:if test="${ qrcForm.custDetailsPojo.status eq 'A' }">
        <li class="${ activeMenu eq 'barring' ? 'active' : '' }"><a href="manageQrc.do?method=barringUnbarringPage">Barring</a></li>
      </c:if>
      <%-- customer unbarring link --%>
      <c:if test="${ qrcForm.custDetailsPojo.status eq 'B' }">
        <li class="${ activeMenu eq 'barring' ? 'active' : '' }"><a href="manageQrc.do?method=barringUnbarringPage">Unbarring</a></li>
      </c:if>
      <c:if test="${ qrcForm.custDetailsPojo.status eq 'T'}">
        <li class="${ activeMenu eq 'safeCustody' ? 'active' : '' }"><a href="customerStatus.do?method=viewSafeCustody">Safe Custody</a></li>
      </c:if>
    </logic:equal>
    <%-- <li class="${ activeMenu eq 'planMigration' ? 'active' : '' }"><a href="manageQrc.do?method=planMigrationPage">Plan Migration</a></li> --%>
    <logic:equal name="crmRoles" property="available(view_qrc_adj)" value="true" scope="session">
      <c:if test="${ qrcForm.custDetailsPojo.status ne 'PD' and   qrcForm.custDetailsPojo.serviceType eq 'PO' }">
        <li class="${ activeMenu eq 'waiver' ? 'active' : '' }"><a href="manageQrc.do?method=waiverPage">Adjustment/ Waiver</a></li>
      </c:if>
    </logic:equal>
    <%-- <li class="${ activeMenu eq 'collectedPaymentPost' ? 'active' : '' }"><a href="manageQrc.do?method=collectedPaymentPage">Collected Payment Post</a></li> --%>
    <%-- customer barring/unbarring exception list link --%>
    <logic:equal name="crmRoles" property="available(view_qrc_el)" value="true" scope="session">
      <c:if test="${ (qrcForm.custDetailsPojo.status eq 'A' or qrcForm.custDetailsPojo.status eq 'B') and (qrcForm.custDetailsPojo.serviceType eq 'PO') }">
        <li class="${ activeMenu eq 'exceptionList' ? 'active' : '' }"><a href="manageQrc.do?method=addWhiteList">Add to Exception List</a></li>
      </c:if>
    </logic:equal>
    <%-- customer disconnection link --%>
    <c:if
      test="${ qrcForm.custDetailsPojo.status eq 'A' or qrcForm.custDetailsPojo.status eq 'B' }">
      <logic:equal name="crmRoles" property="available(update_qrc_cs)" value="true" scope="session">
        <li class="${ activeMenu eq 'disconnection' ? 'active' : '' }"><a href="manageQrc.do?method=disconnectionPage">Disconnection</a></li>
      </logic:equal>
    </c:if>
    <c:if
      test="${ qrcForm.custDetailsPojo.status eq 'T' or qrcForm.custDetailsPojo.status eq 'SC' }">
      <logic:equal name="crmRoles" property="available(delete_qrc_cs)" value="true" scope="session">
        <li class="${ activeMenu eq 'disconnection' ? 'active' : '' }"><a href="manageQrc.do?method=disconnectionPage">Disconnection</a></li>
      </logic:equal>
    </c:if>
    <%-- customer device change link --%>
    <c:if test="${ qrcForm.custDetailsPojo.status eq 'A' or qrcForm.custDetailsPojo.status eq 'B'  or qrcForm.custDetailsPojo.status eq 'SC' }">
      <logic:equal name="crmRoles" property="available(create_qrc_dm,update_qrc_dm)" value="true" scope="session">
        <li class="${ activeMenu eq 'device' ? 'active' : '' }"><a href="manageQrc.do?method=deviceChangePage">Device Change</a></li>
      </logic:equal>
    </c:if>
    <c:if test="${ qrcForm.custDetailsPojo.status eq 'A'}">
      <logic:equal name="crmRoles"
        property="available(view_plan_migration_paid,view_plan_migration_foc,view_plan_migration_app,view_plan_migration_mbo,view_plan_migration_retention,view_plan_booster_usage,view_plan_booster_speed,view_plan_renew_paid,view_plan_renew_foc,view_plan_renew_app,view_plan_renew_mbo,view_plan_renew_retention,view_plan_reactivation_paid,view_plan_reactivation_foc,view_plan_reactivation_app,view_plan_reactivation_mbo,view_plan_reactivation_retention,view_vas_subscription)"
        value="true" scope="session">
        <li class="${ activeMenu eq 'tariffMigration' ? 'active' : '' }"><a href="manageQrc.do?method=tariffMigrationPage">Tariff Migration </a></li>
      </logic:equal>
    </c:if>
    <c:if test="${ qrcForm.custDetailsPojo.status eq 'B' and qrcForm.custDetailsPojo.serviceType eq 'PR' }">
      <logic:equal name="crmRoles"
        property="available(view_plan_renew_paid,view_plan_renew_foc,view_plan_renew_app,view_plan_renew_mbo,view_plan_renew_retention)" value="true"
        scope="session">
        <li class="${ activeMenu eq 'tariffMigration' ? 'active' : '' }"><a href="manageQrc.do?method=tariffMigrationPage">Renewal </a></li>
      </logic:equal>
    </c:if>
    <c:if test="${ qrcForm.custDetailsPojo.status eq 'T' or qrcForm.custDetailsPojo.status eq 'SC' }">
      <logic:equal name="crmRoles"
        property="available(view_plan_reactivation_paid,view_plan_reactivation_foc,view_plan_reactivation_app,view_plan_reactivation_mbo,view_plan_reactivation_retention)"
        value="true" scope="session">
        <li class="${ activeMenu eq 'tariffMigration' ? 'active' : '' }"><a href="manageQrc.do?method=tariffMigrationPage">Reactivation </a></li>
      </logic:equal>
    </c:if>
    <c:if
      test="${ qrcForm.custDetailsPojo.status eq 'A' or qrcForm.custDetailsPojo.status eq 'B' or qrcForm.custDetailsPojo.status eq 'T' or qrcForm.custDetailsPojo.status eq 'SC' }">
      <logic:equal name="crmRoles" property="available(view_qrc_stip,view_qrc_stipchg,view_qrc_wrchg)" value="true" scope="session">
        <li class="${ activeMenu eq 'addRemoveAccessories' ? 'active' : '' }"><a href="javascript:divSelectAddAccessories('menuPage');">Add or Remove
            Accessories</a></li>
      </logic:equal>
    </c:if>
    <logic:equal name="crmRoles" property="available(view_qrc_cp,create_qrc_cp,update_qrc_cp)" value="true" scope="session">
      <c:if test="${ qrcForm.custDetailsPojo.status ne 'PD'}">
        <li class="${ activeMenu eq 'CustomerCategory' ? 'active' : '' }"><a href="customerProfile.do?method=viewCustomerCategory">Modify Customer
            Category</a></li>
      </c:if>
    </logic:equal>
    <logic:equal name="crmRoles" property="available(view_qrc_cp,create_qrc_cp,update_qrc_cp)" value="true" scope="session">
      <c:if test="${ qrcForm.custDetailsPojo.status eq 'A'}">
        <li class="${ activeMenu eq 'CustomerOwner' ? 'active' : '' }"><a href="customerProfile.do?method=viewCustomerOwnerShip">Modify Customer
            Ownership</a></li>
      </c:if>
    </logic:equal>
    <logic:equal name="crmRoles" property="available(view_qrc_bc,create_qrc_bc,update_qrc_bc)" value="true" scope="session">
      <c:if test="${ qrcForm.custDetailsPojo.status eq 'A' and qrcForm.custDetailsPojo.serviceType ne 'PR'}">
        <li class="${ activeMenu eq 'billCycleChange' ? 'active' : '' }"><a href="customerProfile.do?method=viewCustomerBillCycle">Modify Bill Cycle</a></li>
      </c:if>
        </logic:equal>
        <logic:equal value="PR"  property="custDetailsPojo.serviceType" name="qrcForm" >
          <logic:equal name="crmRoles" property="available(view_qrc_valex_,create_qrc_valex,update_qrc_valex)" value="true" scope="session">
       <c:if test="${ qrcForm.custDetailsPojo.status eq 'A' or qrcForm.custDetailsPojo.status eq 'B'}">
	    <li class="${ activeMenu eq 'graceperiod' ? 'active' : '' }"><a href="customerProfile.do?method=viewGracePeriod">Validity Extension</a></li>	    
      </c:if>
     
     </logic:equal>
     </logic:equal>
     
  
  </c:if>
</ul>
