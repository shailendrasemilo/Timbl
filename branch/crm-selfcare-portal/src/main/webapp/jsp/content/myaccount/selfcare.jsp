<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@page import="com.np.tele.crm.utils.StringUtils"%>
<%@page import="com.np.tele.crm.service.client.CrmCustMyAccountPojo"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<%@taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%
    Object userDetails = session
					.getAttribute(CrmCustMyAccountPojo.class.getSimpleName());
			if (!StringUtils.isValidObj(userDetails)) {
				out.write("<script type='text/javascript'>"
						+ "$(document).ready(function(){"
						+ "  if(window.top != window.self)"
						+ "  { parent.$('.popup-loading').removeClass('hide'); window.top.location.href = window.self.location.href; parent.jQuery.fancybox.close(); }"
						+ "  else $('#fancybox-signIn').trigger('click');"
						+ "});</script>");
			} else {
				out.write("<script type='text/javascript'>"
						+ "$(document).ready(function(){"
						+ "  if(window.top != window.self)"
						+ "  { parent.$('.popup-loading').removeClass('hide'); window.top.location.href = window.self.location.href; parent.jQuery.fancybox.close(); }"
						+ "});</script>");
			}
%>
<div class="popup-loading hide">
  <img alt="Please wait" src="images/icons-${initParam.client }/ajaxloading.gif" />
</div>
<html:form action="/quickPay" styleId="selfcareFormId">
  <div class="clearfix ui-corner-all" id="middle-container">
    <div class="round-wrap" style="height: 0px !important">
      <div class="round-img-left"></div>
      <div class="round-bg"></div>
      <div class="round-img-right"></div>
    </div>
    <div id="ctl00_ContentPlaceHolder1_MainContainer" class="cust-info-wrap" style="height: 350px;">
      <div class="cust-col-left">
        <%
            int i = 0;
        %>
        <div class="col-wrap-first col-bg-<%=++i % 2%>">
          <div class="left-col">
            <label>${ (selfcareForm.customerDetailsPojo.connectionType eq 'Ind' || selfcareForm.customerDetailsPojo.connectionType eq 'Prop')
              ? 'Customer Name:' : 'Company Name:' }</label>
          </div>
          <div class="col-info">
            <font id="customerName">${ selfcareForm.customerDetailsPojo.custFname } <c:if
                test="${ (selfcareForm.customerDetailsPojo.connectionType eq 'Ind' || selfcareForm.customerDetailsPojo.connectionType eq 'Prop') }">${ selfcareForm.customerDetailsPojo.custLname }</c:if></font>
          </div>
        </div>
        <div class="col-wrap col-bg-<%=++i % 2%>">
          <div class="left-col">
            <label> Customer ID:</label>
          </div>
          <div class="col-info">
            <span>${ empty selfcareForm.custMyAccountPojo.customerId ? '-' : selfcareForm.custMyAccountPojo.customerId }</span>
          </div>
        </div>
        <div class="col-wrap col-bg-<%=++i % 2%>">
          <div class="left-col">
            <label> Installation Address:</label>
          </div>
          <div class="col-info">
            <span class="prev-info"> <span>${ selfcareForm.installationAddressPojo.addLine1 } ${
                selfcareForm.installationAddressPojo.addLine2 } ${ selfcareForm.installationAddressPojo.addLine3 } ${
                selfcareForm.installationAddressPojo.cityName } ${ selfcareForm.installationAddressPojo.stateName } </span>
            </span>
          </div>
        </div>
        <c:if test="${selfcareForm.customerDetailsPojo.serviceType eq 'PO'}">
          <div id="ctl00_ContentPlaceHolder1_isPostBillingAddress" class="col-wrap  col-bg-<%=++i % 2%>">
            <div class="left-col">
              <label> Billing Address:</label>
            </div>
            <div class="col-info-pop" id="BillAddress">
              <span class="prev-info" style="padding-right: 4px;" id="updateAddress">${selfcareForm.billingAddressPojo.addLine1}
                ${selfcareForm.billingAddressPojo.addLine2} ${selfcareForm.billingAddressPojo.addLine3}</span> <span class="icon-edit"></span>
            </div>
          </div>
        </c:if>
        <div id="scRMN" class="col-wrap-edit-fix col-bg-<%=++i % 2%>">
          <div class="left-col">
            <label> Registered Mobile No. (RMN):</label>
          </div>
          <span class="col-fix-info" style='display: block;'>+91</span>
          <div class="col-info-edit">
            <span class="prev-info">${empty selfcareForm.customerDetailsPojo.rmn ? '-' : selfcareForm.customerDetailsPojo.rmn}</span> <span
              class="icon-edit"></span>
          </div>
          <div class="col-info-editor" style="display: none;">
            <ul class="col-info-inner">
              <li><input name="scRMN" type="text" value="${selfcareForm.customerDetailsPojo.rmn}" maxlength="10" onkeyup="removeChars(this);"
                id="sc_rmn" class="text-container numbersOnly" /></li>
              <li class="gap-top-5"><img src="images/Save.png" id="updateRMN" alt="update" class="update" /></li>
              <li><img src="images/icon-close.png" class="cancel" alt="cancel" /></li>
            </ul>
          </div>
        </div>
        <%-- <div id="scRTN" class="col-wrap-edit-fix col-bg-<%=++i % 2%>">
          <div class="left-col">
            <label> Registered Telephone No. (RTN):</label>
          </div>
          <span class="col-fix-info" style='display: block;'>+91</span>
          <div class="col-info-edit">
            <span class="prev-info">${empty selfcareForm.customerDetailsPojo.rtn ? '-' : selfcareForm.customerDetailsPojo.rtn}</span> <span
              class="icon-edit"></span>
          </div>
          <div class="col-info-editor" style="display: none;">
            <ul class="col-info-inner w221">
              <li><input name="scRTN" type="text" value="${selfcareForm.customerDetailsPojo.rtn}" maxlength="10" onkeyup="removeChars(this);"
                id="sc_rtn" class="text-container numbersOnly" /></li>
              <li class="gap-top-5"><img src="images/Save.png" id="RTN" alt="update" class="update" /></li>
              <li><img src="images/icon-close.png" class="cancel" alt="cancel" /></li>
            </ul>
          </div>
        </div>--%>
        <div id="scAMN" class="col-wrap-edit-fix col-bg-<%=++i % 2%>">
          <div class="left-col">
            <label> Alternate Mobile No.:</label>
          </div>
          <span class="col-fix-info" style='display: block;'>+91</span>
          <div class="col-info-edit">
            <span class="prev-info">${selfcareForm.customerDetailsPojo.custMobileNo}</span> <span class="icon-edit"></span>
          </div>
          <div class="col-info-editor" style="display: none;">
            <ul class="col-info-inner w221">
              <li><input name="scAMN" type="text" value="${selfcareForm.customerDetailsPojo.custMobileNo}" onkeyup="removeChars(this);"
                maxlength="10" id="sc_amn" class="text-container numbersOnly" /></li>
              <li class="gap-top-5"><img src="images/Save.png" id="ANO" alt="update" class="update" /></li>
              <li><img src="images/icon-close.png" class="cancel" alt="cancel" /></li>
            </ul>
          </div>
        </div>
        <div id="scRegEmail" class="col-wrap-edit col-bg-<%=++i % 2%>">
          <div class="left-col gap-top-5">
            <label> Registered Email ID:</label>
          </div>
          <div class="col-info-edit">
            <span class="${ selfcareForm.customerDetailsPojo.emailValidationFlag eq 'Y' ? 'valid' : 'error' } gap-top-5 prev-info emaillabel"
              title="${selfcareForm.customerDetailsPojo.custEmailId}">${selfcareForm.customerDetailsPojo.custEmailId}</span> <span class="icon-edit"></span>
            <div class="btn1 ${selfcareForm.customerDetailsPojo.emailValidationFlag eq 'Y' ? 'hide' : ''}">
              <img src="images/icons-${initParam.client }/verify-btn.png" alt="verify" id="scVerifyEmail" />
            </div>
          </div>
          <div class="col-info-editor" style="display: none;">
            <ul class="col-info-inner">
              <li><input name="scRegEmail" type="text" value="${selfcareForm.customerDetailsPojo.custEmailId}" id="sc_reg_email" maxlength="255"
                class="text-container" /></li>
              <li class="gap-top-5"><img src="images/Save.png" id="REI" alt="update" class="update" /></li>
              <li><img src="images/icon-close.png" class="cancel" alt="cancel" /></li>
            </ul>
          </div>
        </div>
        <div id="scAltEmail" class="col-wrap-edit col-bg-<%=++i % 2%>" style="width: 100% !important;">
          <div class="left-col">
            <label> Alternate Email ID:</label>
          </div>
          <div class="col-info-edit">
            <span class="prev-info emaillabel" title="${selfcareForm.customerDetailsPojo.altEmailId}">${empty
              selfcareForm.customerDetailsPojo.altEmailId ? '-' : selfcareForm.customerDetailsPojo.altEmailId}</span> <span class="icon-edit"></span>
          </div>
          <div class="col-info-editor" style="display: none;">
            <ul class="col-info-inner">
              <li><input name="scAltEmail" type="text" value="${ selfcareForm.customerDetailsPojo.altEmailId }" id="sc_alt_email" maxlength="255"
                class="text-container" /></li>
              <li class="gap-top-5"><img src="images/Save.png" id="AEI" alt="update" class="update" /></li>
              <li><img src="images/icon-close.png" class="cancel" alt="cancel" /></li>
            </ul>
          </div>
        </div>
      </div>
      <div class="cust-col-right">
        <div id="ctl00_ContentPlaceHolder1_PostPlanName" class="col-wrap-first" style="background: none repeat scroll 0% 0% #EEE;">
          <div class="left-col" style="width: 90px !important">
            <label> Plan Info:</label>
          </div>
          <div class="col-info" style="width: 350px !important; height: 42px;">
            <span style="float: left; width: 140px; margin-left: 100px">
				<c:choose>
					<c:when test="${selfcareForm.basePlanMasterPojo.planUsageType eq 'L'}">
						<span>${selfcareForm.basePlanMasterPojo.planName};</span> <br/>
						<span id="ctl00_ContentPlaceHolder1_Label3">
							<fmt:formatNumber value="${selfcareForm.basePlanMasterPojo.primarySpeed div 1024}" minFractionDigits="0" maxFractionDigits="2" /> Mbps; 
							${empty selfcareForm.custAdditionalDetails.primaryQuotaInGB ? selfcareForm.basePlanMasterPojo.totalDULInGB : selfcareForm.custAdditionalDetails.primaryQuotaInGB};<br>
							<bean:message key="limited.plan.info.postusage"/>
						</span>
					</c:when>
					<c:otherwise>
						<span>${selfcareForm.basePlanMasterPojo.planName};</span> <br/>
						<span id="ctl00_ContentPlaceHolder1_Label3">
							<fmt:formatNumber value="${selfcareForm.basePlanMasterPojo.primarySpeed div 1024}" minFractionDigits="0" maxFractionDigits="2" /> Mbps 
                            <c:if test="${selfcareForm.basePlanMasterPojo.primaryQuota lt 0 }">Unlimited</c:if>
                            <c:if test="${selfcareForm.basePlanMasterPojo.primaryQuota gt -1 }">
                              upto 
     							${empty selfcareForm.custAdditionalDetails.primaryQuotaInGB ? selfcareForm.basePlanMasterPojo.totalDULInGB : selfcareForm.custAdditionalDetails.primaryQuotaInGB};<br>
  				    			${ selfcareForm.basePlanMasterPojo.secondarySpeed } Kbps thereafter
                            </c:if>
						</span>
					</c:otherwise>
				</c:choose>
            </span> <span style="float: right; width: 210px"> 
            <%--
              <a id="btnchangeplan" target="_blank" style="float: left; height: 30px">
                  <div id="ctl00_ContentPlaceHolder1_Divbtncngeplan" class="submit-btn"
                    style="padding: 5px 20px 12px 7px !important; width: 75px !important; background-size: 85px 35px;">Change Plan/ Data Limit</div>
              </a>
              <c:if test="${selfcareForm.basePlanMasterPojo.planUsageType eq 'U'}">
                <a id="btntopup" target="_blank" style="float: right">
                    <div id="ctl00_ContentPlaceHolder1_Divbtntopup_plan" class="submit-btn"
                      style="margin-left: 0px !important; padding: 5px 5px 9px 10px !important; background-size: 85px 35px;">Add Booster Top Up</div>
                </a>
              </c:if> 
             --%>
            </span>
          </div>
        </div>
        <%-- ${selfcareForm.customerDetailsPojo.serviceType eq 'PO'} --%>
        <div id="ctl00_ContentPlaceHolder1_isPrePaidOutStanding" class="col-wrap">
          <div class="left-col gap-top-5">
            <label><span id="ctl00_ContentPlaceHolder1_lblOutstandingAmount">${selfcareForm.customerDetailsPojo.serviceType eq 'PO' ?
                'Outstanding Amount' : 'Wallet Balance'}:</span></label>
          </div>
          <div class="col-info">
            <div class="FloatLeft gap-top-5">
              <span class="WebRupee" style="font-size: 15px !important;"><img alt="" src="images/icons-${initParam.client }/indian-rupee-sign.png"
                style="width: 9px;" />&nbsp;&nbsp;</span> <span id="ctl00_ContentPlaceHolder1_lblOutStandingAmt"><fmt:formatNumber
                  value="${selfcareForm.custAdditionalDetails.balance}" minFractionDigits="2" maxFractionDigits="2" /> </span>
              <c:if test="${selfcareForm.customerDetailsPojo.serviceType eq 'PO'}">
                <a href="javascript:void(0)" id="scPayNow"> <img src="images/icons-${initParam.client }/pay-btn.png" alt="Pay Now"
                  style="margin-top: -5px; margin-left: 15px;" /></a>
              </c:if>
              <c:if test="${selfcareForm.customerDetailsPojo.serviceType eq 'PR'}">
                <a href="javascript:void(0)" id="scPayNow"> <img src="images/icons-${initParam.client }/pay-btn.png" alt="Pay Now"
                  style="margin-top: -5px; margin-left: 15px;" /></a>
              </c:if>
            </div>
          </div>
        </div>
        <div id="ctl00_ContentPlaceHolder1_IsPrePaidNextChargingDate" class="col-wrap col-bg">
          <div class="left-col">
            <span id="ctl00_ContentPlaceHolder1_lblDueDate" style="font-weight: bold;">${selfcareForm.customerDetailsPojo.serviceType eq 'PR' ?
              'Next Charging Date' : 'Due Date'}:</span>
          </div>
          <div class="col-info">
            <span id="ctl00_ContentPlaceHolder1_DueDate"> <c:choose>
                <c:when test="${(selfcareForm.customerDetailsPojo.serviceType eq 'PR')}">
                  <%--
                  <bean:write name="selfcareUtils" property="toDate(${selfcareForm.custAdditionalDetails.expiryDate})" />
                 --%>
                 ${selfcareForm.custAdditionalDetails.nextChargeDate}
                </c:when>
                <c:when test="${(selfcareForm.customerDetailsPojo.serviceType eq 'PO')}">
                  <bean:write name="selfcareUtils" property="toDate(${selfcareForm.invoiceDetailsPojo.dueDate})" />
                </c:when>
                <c:otherwise>-</c:otherwise>
              </c:choose>
            </span>
          </div>
        </div>
        <div class="col-wrap">
          <div class="left-col">
            <span style="font-weight: bold;">Total Usage:</span>
          </div>
          <div class="col-info">
            <span>${selfcareForm.custAdditionalDetails.usedVolumeQuotaInGB}</span>
          </div>
        </div>
        <div class="col-wrap col-bg">
          <div class="left-col">
            <span style="font-weight: bold;">Additional DUL available @ Primary Speed:</span>
          </div>
          <div class="col-info">
            <span>${selfcareForm.custAdditionalDetails.additionalAvailableDULAtPrimarySpeed}</span>
          </div>
        </div>
        <div class="col-wrap">
          <%--
          <div id="divCurrentSpeed" style="margin-top: 20px; margin-left: 10px; position: relative;">
            <div style="position: absolute; top: 16px; left: 0px; font-weight: bold; visibility: visible;">Current Usage:</div>
          </div>
           --%>
          <div align="center" class="speed-mtr-wrap">
            <%--
            <div style="margin-left: 25px; width: 315px; height: 64px; margin-top: -15px; margin-bottom: 5px; visibility: visible;">
              <div style="text-align: center; width: 315px; font-weight: bold; margin-bottom: 5px;">
                <span> ${selfcareForm.currentUsage} GB</span> of <span> ${selfcareForm.totalQuota} GB</span> (Usage Till Last Router Restart)
              </div>
              <div style="width: 100%; height: 30px; border: solid 1px #000;">
                <div id="usageBar" style="float: left; background-color: #007F0E; height: 100%; width: 0%;">&nbsp;</div>
                <div style="text-align: center; position: absolute; width: 315px; margin-top: 10px; color: #000;">
                  <span id="ctl00_ContentPlaceHolder1_PercentageUsageConsumed" class="bold"> <fmt:formatNumber
                      value="${selfcareForm.usedPercentage}" pattern="#" maxFractionDigits="2" /> %
                  </span>
                </div>
                <script>
																	$( function(){
																		$( '#usageBar' ).css( 'width',
																				'${selfcareForm.usedPercentage div selfcareForm.percentage}%' );
																	} );
																</script>
              </div>
              <div style="clear: left;"></div>
              <div style="width: 100%; position: relative; clear: both;">
                <div id="ctl00_ContentPlaceHolder1_Percentage100Tick"
                  style="position: absolute; text-align: right; margin-left: 316px; height: 5px; width: 1px; border-left: 1px solid black; text-align: right;">
                </div>
                <div style="float: left; text-align: left; padding-top: 4px;"></div>
                <div id="ctl00_ContentPlaceHolder1_Percentage100"
                  style="position: absolute; width: 55px; margin-left: 275px; text-align: right; padding-top: 4px;">
                  <fmt:formatNumber value="${selfcareForm.percentage * 100}" pattern="#" maxFractionDigits="0" />
                  %
                </div>
              </div>
            </div>
               --%>
            <div style="clear: left;"></div>
            <div class="speed-meter">
              <div id="test"
                style="background-image: url(images/speed-meter4.png); margin-top: -6px; width: 214px !important; height: 110px !important; position: relative; padding: 0px; border: 0px; font-family: Arial; font-weight: 900;"
                linecolor="rgb(255,0,0)" class="tooltip">
                <div id="speedometerHolder"></div>
                <script type="text/javascript">
																	$( function(){
																		$( '#speedometerHolder' )
																				.speedometer(
																						{
																							percentage : '<fmt:formatNumber value="${selfcareForm.custAdditionalDetails.currentBandwidth div 1024}" minFractionDigits="0" maxFractionDigits="2" />',
																							animateTime : 50, suffix : ' Mbps'

																						} );
																	} );
																</script>
                <div style="width: 100px; height: 30px; position: absolute; left: -115px; top: 15px;">Current Speed:</div>
              </div>
            </div>
            <div class="mtr-btn" style="margin-left: 0px !important; width: 100px !important; margin-top: 84px;">
              <%-- <a href="http://www.speedtest.net/" target="_blank"> <img alt="" src="images/icons-${initParam.client }/speed-btn.png"></a> --%>
            </div>
          </div>
          <div id="ctl00_ContentPlaceHolder1_divusagedetails" class="col-wrap col-bg">
            <div class="left-col" style="width: 449px; font-family: Arial, Helvetica, sans-serif; font-size: 12px;">
              <html:link page="/user.do?method=downloadCustomerUsage" target="_blank"
                style="text-decoration: underline; float: none !important; padding-left: 0; text-align: center; font-family: Arial, Helvetica, sans-serif; font-size: 12px;">Click here to download UnBilled Usage,</html:link>
              <span>It may take approx 1 minute to download.</span>
            </div>
          </div>
        </div>
      </div>
    </div>
    <div class="round-wrap">
      <div class="round-btm-left"></div>
      <div class="round-bg-btm"></div>
      <div class="round-btm-right"></div>
    </div>
    <c:if test="${selfcareForm.custPlanDetailsPojo.billMode eq 'PB'}">
      <div class="ebill gap-top-10" id="SwitchToEBill">
        <div class="ebill-title">Switch to E-Bill</div>
        <div class="ebill-info">
          <div class="chkbox">
            <input type="checkbox" id="BillChk">
          </div>
          <div class="ebill-text">Yes I want to contribute to the environment by switching to E-bill mode</div>
          <div class="submit">
            <div class="submit-btn" id="EBILL">Submit</div>
          </div>
        </div>
      </div>
    </c:if>
    <div class="account-details gap-top-8">
      <div class="acc-info-1 gap-right-12" style="width: 30%; margin: 0px !important;">
        <div id="Div1" class="clearfix ui-corner-top submenu-wrap"
          style="margin: 0px; text-align: left !important; border-color: #414042; border-width: 0 1px; border-style: solid;">
          <div class="gap-right-10" style="margin-right: 10px; text-align: left;">
            <span class="subheading-pos" style="float: left; margin-left: 10px; margin-top: 8px;"> <span
              id="ctl00_ContentPlaceHolder1_BillingHeading">Bill Details</span>
            </span>
          </div>
        </div>
        <div style="clear: both;"></div>
        <div class="ui-corner-bottom paymentBill-table"
          style="width: 100%; border: solid 1px #414042; border-top: none; min-height: 165px; position: relative; padding-bottom: 5px;">
          <%-- 
        <div class="EvenBG" style="font-weight: bold;">
          <div style="float: left; width: 45%;">Date</div>
          <div style="width: 45%; float: right; text-align: right;">Amount</div>
          <div style="clear: both;"></div>
        </div>
        --%>
          <table id="billDetails">
            <thead>
              <tr>
                <th>Date</th>
                <th class="textRight">Amount</th>
              </tr>
            </thead>
            <tbody>
              <c:forEach items="${ selfcareForm.invoiceDetailsPojos }" var="invoiceDetailsPojos" varStatus="current">
                <tr>
                  <td><bean:write name="selfcareUtils" property="toDate(${invoiceDetailsPojos.billDate})" /></td>
                  <td class="textRight"><fmt:formatNumber value="${invoiceDetailsPojos.billAmount}" minFractionDigits="2" maxFractionDigits="2" /></td>
                </tr>
              </c:forEach>
            </tbody>
          </table>
          <%--
        <div class="EvenBG2" style="text-align: center;">
          <h3>No data found</h3>
        </div>
         --%>
        </div>
      </div>
      <div style="padding-left: 10px; float: left; width: 32%;">
        <div id="SubMenu" class="clearfix ui-corner-top submenu-wrap"
          style="margin: 0px; text-align: left !important; border-color: #414042; border-width: 0 1px; border-style: solid;">
          <div class="gap-right-10" style="margin-right: 10px; text-align: left;">
            <span class="subheading-pos" style="float: left; margin-left: 10px; margin-top: 8px;">Payment History</span>
          </div>
        </div>
        <div style="clear: both;"></div>
        <div class="ui-corner-bottom paymentBill-table"
          style="width: 100%; border: solid 1px #414042; border-top: none; min-height: 165px; position: relative; padding-bottom: 5px;">
          <%--
        <div class="EvenBG" style="font-weight: bold;">
          <div style="float: left; width: 40%;">Date</div>
          <div style="width: 30%; float: left;">Amount</div>
          <div style="width: 30%; float: right; text-align: right;">Mode</div>
          <div style="clear: both;"></div>
        </div>
         --%>
          <table id="paymentHistory">
            <thead>
              <tr>
                <th>Date</th>
                <th class="">Amount</th>
                <th>Mode</th>
              </tr>
            </thead>
            <tbody>
              <c:forEach items="${ selfcareForm.paymentDetailsPojos }" var="paymentDetailsPojos">
                <tr>
                  <td><bean:write name="selfcareUtils" property="toDate(${paymentDetailsPojos.paymentDate})" /></td>
                  <td class=""><fmt:formatNumber value="${paymentDetailsPojos.amount}" minFractionDigits="2" maxFractionDigits="2" /></td>
                  <td><bean:write name="selfcareUtils" property="displayEnum(PaymentMode,${paymentDetailsPojos.paymentMode})" /></td>
                </tr>
              </c:forEach>
            </tbody>
          </table>
          <%--
        <div id="ctl00_ContentPlaceHolder1_listPaymentDetails_ctrl0_divContainer" class="EvenBG2">
          <div style="float: left; width: 40%;">
            <span id="ctl00_ContentPlaceHolder1_listPaymentDetails_ctrl0_PaymentDate">14-Oct-2014</span>
          </div>
          <div style="width: 30%; float: left;">
            <span id="ctl00_ContentPlaceHolder1_listPaymentDetails_ctrl0_PaymentAmount"><span class='WebRupee'
              style='font-size: 15px !important;'> Rs.&nbsp;&nbsp;</span>7.00</span>
          </div>
          <div style="width: 30%; float: right; text-align: right;">Cash</div>
          <div style="clear: both;"></div>
        </div>
        <div id="ctl00_ContentPlaceHolder1_listPaymentDetails_ctrl1_divContainer" class="EvenBG">&nbsp;</div>
        <div id="ctl00_ContentPlaceHolder1_listPaymentDetails_ctrl2_divContainer" class="EvenBG2">&nbsp;</div>
        <div id="ctl00_ContentPlaceHolder1_listPaymentDetails_ctrl3_divContainer" class="EvenBG ui-corner-bottom">&nbsp;</div>
         --%>
        </div>
      </div>
      <div style="float: right; width: 36%;">
        <div id="Div2" class="clearfix ui-corner-top submenu-wrap"
          style="margin: 0px; text-align: left !important; border-color: #414042; border-width: 0 1px; border-style: solid;">
          <div class="gap-right-10" style="margin-right: 10px; text-align: left;">
            <span class="subheading-pos" style="float: left; margin-left: 10px; margin-top: 8px;">Open&nbsp;Tickets</span>
          </div>
        </div>
        <div style="clear: both;"></div>
        <div class="ui-corner-bottom paymentBill-table"
          style="width: 100%; border: solid 1px #414042; border-top: none; min-height: 165px; position: relative; padding-bottom: 5px;">
          <!--   <div class="EvenBG" style="font-weight: bold;">
          <div style="float: left; width: 25%;">Date</div>
          <div style="width: 25%; float: left;">Nature</div>
          <div style="width: 25%; float: left;">Category</div>
          <div style="width: 25%; float: right; text-align: right;">Ticket&nbsp;No.</div>
          <div style="clear: both;"></div>
        </div>-->
          <table id="openTicketDetails">
            <thead>
              <tr>
                <th class="padding2lr">Date</th>
                <th class="padding2lr">Nature</th>
                <th class="padding2lr">Category</th>
                <th class="padding2lr">Ticket No.</th>
              </tr>
            </thead>
            <tbody>
              <c:forEach items="${ selfcareForm.openTicketsPojos }" var="openTicketsPojos">
                <tr>
                  <td class="padding2lr"><span><bean:write name="selfcareUtils" property="toDate(${openTicketsPojos.createdTime})" /></span></td>
                  <td class="padding2lr"><span>${openTicketsPojos.qrcType eq 'R' ? 'Request' : (openTicketsPojos.qrcType eq 'Q' ? 'Query'
                      : 'Complaint')}</span></td>
                  <td class="padding2lr"><span class="ticketCategory" title="${openTicketsPojos.qrcCategory}">${openTicketsPojos.qrcCategory}</span></td>
                  <td class="padding2lr"><span>${ openTicketsPojos.srId }</span></td>
                </tr>
              </c:forEach>
            </tbody>
          </table>
        </div>
      </div>
      <div style="clear: both; height: 10px;"></div>
    </div>
    <div style="width: 23%; float: right;">
      <div style="float: right">
        <a class="btnlogticket" target="_blank" style="text-align: center">
          <div id="ctl00_ContentPlaceHolder1_div_divinnerlogticket" class="submit-btn"
            style="padding: 8px !important; margin-top: -5px; width: 67px; margin-right: 20px;">Log Ticket</div>
        </a> <a id="A2" target="_blank" style="float: right">
          <div id="nonOpenTickets" class="submit-btn openTicketHistory"
            style="padding: 8px 0px 8px 0px !important; float: right; padding-left: 0; padding-right: 0px; margin-top: -5px; text-align: center;">
            Ticket History</div>
        </a>
      </div>
    </div>
    <div style="clear: both; height: 0px;"></div>
  </div>
  <div class="popupDiv hide">
    <div class="titleBar">
      <span class="pageTitle">Change Password <a id="closeBtn" class="closeTktHistory" onclick=""></a></span>
    </div>
  </div>
  <div class="overlayDiv hide"></div>
  <div style="clear: both; height: 0px;"></div>
</html:form>
<html:form action="/quickPay" styleId="quickPayFormId">
</html:form>
