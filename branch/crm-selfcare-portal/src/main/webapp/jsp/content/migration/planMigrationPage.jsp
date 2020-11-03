<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="/WEB-INF/tlds/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/tlds/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/tlds/displaytag.tld" prefix="display"%>
<%@taglib uri="/WEB-INF/tlds/struts-html.tld" prefix="html"%> 
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link href="css/main.css" type="text/css" rel="stylesheet" />
<link href="css/color-${initParam.client}.css" type="text/css" rel="stylesheet" />
<link href="css/style.css" type="text/css" rel="stylesheet" />
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<script src="javascript/typeface.js" type="text/javascript"></script>
<script src="javascript/jquery-1.8.1.js" type="text/javascript"></script>
<script src="javascript/helvetica_neue_lt_std_45_light.typeface.js" type="text/javascript"></script>
<script src="javascript/helvetica_neue_lt_std_56_italic.typeface.js" type="text/javascript"></script>
<script src="javascript/helvetica_neue_lt_std_75_bold.typeface.js" type="text/javascript"></script>


<script src="javascript/jquery.min.js" type="text/javascript"></script>
<script src="javascript/jquery.speedometer.js" type="text/javascript"></script>
<script src="javascript/jquery.jqcanvas-modified.js" type="text/javascript"></script>
<script type="text/javascript" src="javascript/jquery.tooltip.min.js"></script>
<script src="javascript/MakeYourPlan2.js" type="text/javascript"></script>
<link rel="stylesheet" type="text/css" href="css/TariffsStylesheet.css"/>

<link rel="stylesheet" type="text/css" href="css/popup-common.css" />
<link rel="stylesheet" type="text/css" href="css/myaccount-common.css" />
<!-- =============== fancy box =====================-->
<script type="text/javascript" src="javascript/jquery-1.10.2.min.js"></script>
<script type="text/javascript" src="javascript/jquery.fancybox.pack.js"></script>
<link rel="stylesheet" type="text/css" href="css/jquery.fancybox.css" />
<script type="text/javascript" src="javascript/fancybox-custom.js"></script>
<link href="css/jquery.tooltip.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="javascript/make-my-plan.js"></script>

<!-- =============== dwr =============== -->
<script src="dwr/engine.js" type="text/javascript"></script>
<script src="dwr/util.js" type="text/javascript"></script>
<script src="dwr/interface/crmDwr.js" type="text/javascript"></script>

<title>Insert title here</title>
</head>
<body>
 <html:form action="/migration"  method="post" styleId="tariffMigration">
    <html:hidden property="addonPlanCode" name="migrationForm" styleId="addonPlanId"/>
    <html:hidden property="oldBasePlanCode" name="migrationForm" styleId="oldBasePlanCodeId" value="${ migrationForm.crmPlanDetailsPojo.basePlanCode }"/>
    <html:hidden property="oldAddonPlanCode" name="migrationForm" styleId="oldAddonPlanCodeId" value="${ migrationForm.crmPlanDetailsPojo.addOnPlanCode }"/>
    <html:hidden property="activationType" name="migrationForm" styleId="activationTypeId"/>
    <html:hidden property="serviceType" name="migrationForm" styleId="serviceTypeId" value="${ migrationForm.serviceType }"/>
    <html:hidden property="customerId" name="migrationForm" styleId="customerId" value="${ migrationForm.customerId }"/>
     <logic:notEmpty property="srTicketsPojos" name="migrationForm">
     	<<input type="hidden" id="ticketListAvailID" value="Y" />
     </logic:notEmpty>
<div class="mainWrapper changePlan">
	<logic:messagesPresent message="true">
      <bean:define id="hasMessages" value="true" />
    </logic:messagesPresent>
	<div class="titleBar">
    	<span class="pageTitle">Make Your Own Plan</span>
       <a id="closeBtn"
        onclick="${hasMessages ? 'parent.location.reload();parent.jQuery.fancybox.close();' : 'parent.jQuery.fancybox.close();'}"></a>
    </div>
     <div class="pInfo hide"></div>
      <logic:messagesPresent property="appError">
        <bean:define id="hasErrors" value="true"></bean:define>
      </logic:messagesPresent>
      <div class="msgerror ${ hasErrors ? '' : 'hide' }">
        <html:errors />
      </div>
     
	<div id="Divchangeplanpopup" title="Make Your Own Plan" style="margin-bottom: 0px;">

        <div style="width: 960px; margin: 10px auto; overflow: hidden;">
          <logic:messagesPresent message="true" property="appMessage">
        <html:messages id="message" message="true" property="appMessage" >
          <div class="msgsuccess">
            <bean:write name="message"  />
          </div>
        </html:messages>
      </logic:messagesPresent>
        <div>
          <table style="width: 100%;">
            <tr>
              <td colspan="4" id="showonplanmigration" class="clearfix ui-corner-all" style="width: 99.8%;margin-top: 5px; min-height: 500px; border: solid 1px #E5E5E5; background-color: #fff; overflow: auto;"><div id="BusinessInnerSubMenu" class="TextAlignCenter UpperCase" style="width: 100%; top: -4.5px; position: relative; min-height: 30px; overflow: hidden; font-size: 16px;">
                  <div class="SubHeading">
                  	 <div id="Internet" class="FloatRight" style="width: 148px; background: url(images/top-tab-selected.png) no-repeat; position: absolute; z-index: 0; right: 112px;"> <a onclick="showhidedivsontabclick(this.id);" id="tabunlimited_popup">
                      <div> Unlimited </div>
                      </a> </div>
                    <div id="iLL" class="FloatRight" style="width: 132px; background: url(images/top-tab-deselected.png) no-repeat; position: absolute; z-index: 1; right: 0px;"> <a onclick="showhidedivsontabclick(this.id);" id="tablimited_popup">
                      <div> Limited </div>
                      </a> </div>
                    	
                  </div>
                </div>
                <div style="margin-bottom: 25px; overflow: hidden;">
                  <div style="height: 0;"> </div>
                  <div id="showorhide" style="display: none">
                    <div style="width: 900px; margin: auto;">
                      <div id="tbl_Planmigration" style="display: none;"><%-- ${ migrationForm.crmPlanMasterPojo.planUsageType eq 'L' ? 'none' : 'block'} --%>
                        <div class="PageTitle typeface-js"> Unlimited > <span class="colorBlue">Make Your Own Plan</span> </div>
                        <div class="clearBoth BorderTop">
                          <div class="DivMarginTop">
                            <div class="bodyText"> Your current Tariff Plan is <span id="ctl00_ContentPlaceHolder1_Label4">${ migrationForm.crmPlanMasterPojo.planName }</span>. <br />
                              To migrate on another plan, please make your selection from table below: </div>
                          </div>
                          <div class="DivMarginTop"> <a href="#">
                            <div class="myopSteps " data-step="0">
                              <div class="stepNumberContainer  activeStepNumber" id="step_1_unlimited">
                                <div class="stepNumber"> 1 </div>
                              </div>
                              <span class="SubHeading typeface-js">Select Your Speed</span> </div>
                            </a>
                            <div id="showsecondstep_unlimited" style="display:none">
                              <div class="myopStepSeperatorContainer"> <img class="myopStepSeperator" src="images`/SepArrow.jpg" alt="" /> </div>
                              <div class="myopSteps " data-step="1">
                                <div class="stepNumberContainer  inActiveStepNumber" id="step_2_unlimited">
                                  <div class="stepNumber"> 2 </div>
                                </div>
                                <span class="SubHeading typeface-js">Choose Your Data Usage Limit (DUL)</span> </div>
                            </div>
                            <div class="clearBoth"> </div>
                          </div>
                          <div>
                            <input type="hidden" name="__EVENTTARGET" id="__EVENTTARGET" value="" />
                            <input type="hidden" name="__EVENTARGUMENT" id="__EVENTARGUMENT" value="" />
                          </div>
                          
                          <div>
                            <input type="hidden" name="__EVENTVALIDATION" id="__EVENTVALIDATION" value="/wEWBgKe0erjBQLAtc6WCQLv+fTWCgKF+pDnBwKElOH+BwK7pNqTCOGzThCSfpnH8t6k1AglZZSBabt0" />
                          </div>
                          <div class="my-helper-full-width">
                            <div class="myopContainer my-helper-full-width">
                              <div class="my-helper-full-width" style="height: 30px;"> <img src="images/AdonBaseCombined.jpg" alt="" /> </div>
                              <div class="myopBaseplan">
                                <table>
                                  <tbody>
                                    <tr class="basePlanRow">
                                      <td class="NextraWhite"><span class="">&nbsp;</span></td>
                                      <td class="NextraWhite"><span class="">&nbsp;</span></td>
                                      <td class="NextraWhite" style="text-align: left"><span class="">Total DUL<img alt="" src="images/arrow_2.png" /></span></td>
                                    </tr>
                                    
                                    <tr class="basePlanRow">
                                      <td class="NextraWhite"><span class="">Plan Name</span></td>
                                      <td class="NextraWhite"><span class="">Speed<img alt="" src="images/arrow_1.png" /></span></td>
                                      <td class="NextraBlue animateBaseTd"><span class="">20 GB</span></td>
                                    </tr>
                                    <c:forEach items="${ migrationForm.unlimitedBasePlanMasterPojos }" var="unlimitedBasePlanMasterPojos" varStatus="current">
       									<tr class="basePlanRow animateBaseBodyRow">
       										<td class="NextraGrey"><span class="">${ unlimitedBasePlanMasterPojos.planName }</span></td>
                                 			<td class="NextraGrey"><span class="tarrifPlanBaseSpeed " data-speed="10">
                                 			<fmt:formatNumber type="number" maxFractionDigits="0" value="${unlimitedBasePlanMasterPojos.primarySpeed div 1024}" />&nbsp;Mbps</span></td>
                                 			<td class="NextraGrey animateBasePriceTd">
                                 				<span class="tarrifPlanBasePrice" data-price="${ unlimitedBasePlanMasterPojos.rentExclTax}">
                                 	 				<img alt="" src="images/icons-${initParam.client }/indian-rupee-sign.png" style="width: 10px; margin-right: 5px;" />
                                 	 				${ unlimitedBasePlanMasterPojos.rentExclTax}
                                 	 				<html:radio name="migrationForm" property="selectedPlanCode" value="${ unlimitedBasePlanMasterPojos.planCode }" styleClass="addon_${unlimitedBasePlanMasterPojos.addonAllowedYn}"
                                 	 						onclick="setPlanInfo('${ unlimitedBasePlanMasterPojos.planName }','${ unlimitedBasePlanMasterPojos.primarySpeed div 1024}','${ unlimitedBasePlanMasterPojos.primaryQuota div 1024 div 1024 div 1024}','${ unlimitedBasePlanMasterPojos.secondarySpeed div 1024}');" ></html:radio>
                                   				</span>
                                 			</td>
                               			</tr>
                  					</c:forEach>
                                  </tbody>
                                </table>
                              </div>
                              <div class="myopAddonplan" style="vertical-align: top;"> 
                              <table class="my-helper-full-width">
                                  <tbody>
                                    <tr>
                                    <logic:notEmpty name="migrationForm" property="crmAddonPLMasterPojos" >
                                   	 <c:set var="k" value="0"></c:set>
                                     		<c:forEach items="${ migrationForm.crmAddonPLMasterPojos }" var="crmAddonPLMasterPojos" varStatus="current">
                                       		 	<td width="94">
                                       		 		<div class="addOnPlanRow noMarginT">
		                                            	<div class="NextraWhite animateTopArrowTd">
		                                                	<div class="topArrow topArrowLight"><div class="planLimitTag">${ crmAddonPLMasterPojos.primaryQuota div 1024 div 1024 div 1024 -k}&nbsp;GB</div></div>
		                                                	<c:set var="k" value="${crmAddonPLMasterPojos.primaryQuota div 1024 div 1024 div 1024 }"></c:set>
		                                                </div>
		                                            </div>
		                                            <div class="addOnPlanRow addOnPlanHeaderRow noMarginT">
		                                            	<div class="NextraBlue animateHeadTd" style="position: relative; width: 80px; line-height: 40px;">
		                                            	<span class="tarrifPlanAddonSpeed" data-speed="30">${ crmAddonPLMasterPojos.primaryQuota div 1024 div 1024 div 1024 + 20}&nbsp;GB</span>
		                                            	</div>
		                                            </div>
		                                            <div class="">
		                                            	<div class="NextraGrey animateBodyTd" style="height: 223px"><div style="height: 92px"></div>
		                                            	<span class=" tarrifPlanAddonPrice" data-price="300">+&nbsp;
															<input type="checkbox" name="tariffAddonPlan" id="newAddonPlanCodeID" onchange="setPlanCode(this,'${crmAddonPLMasterPojos.planCode}');" data-code="${crmAddonPLMasterPojos.planCode}" value="300" data-quota="${k}"/>
															<img alt="" src="images/icons-${initParam.client }/indian-rupee-sign.png" style="width: 10px; margin-right: 5px;" />300 </span></div>
		                                            </div>
                                           </td>
                                       	</c:forEach>
                                    </logic:notEmpty>
                                    </tr>
                                  </tbody>
                               </table>
                               <div class="clr"></div>
                                <div class="addOnOverlayDiv"> </div>
                              </div>
                              <p style="color: #818181; font-size: 11px; line-height: 11px; width: 100%; margin: auto; margin-top: 4px; font-family: Verdana; height: 12px; text-align: left"> * Prices shown above are exclusive of Service Tax. </p>
                              <div class="myopNavigator my-helper-full-width clearfix" style="margin-top: -4px;">
                                <div class="noAddonPlanDivContainer">
                                  <div class="noAddonPlanDiv" style="display: none;"> <span>
                                  <html:checkbox property="tariffNoAddOn" name="migrationForm" onclick="checkboxclick_table(this.name);" value="true"></html:checkbox>
                                   <!--  <input type="checkbox" onclick="checkboxclick_table(this.name);" name="tariffNoAddOn" /> -->
                                    Don't require Add on usage right now </span> </div>
                                </div>
                              </div>
                            </div>
                             <font class="errorTextbox" id="planSelectId" ></font>
                          </div>
                          <div class="DivMarginTop bodyText" style="margin-top: 4px"> {For Example, if "FiberBolt 10" Plan is selected with a total DUL of 80 GB (20 GB
                            Base DUL plus 60 GB Add-on DUL), your speed would remain 10/10 Mbps until the Total
                            DUL of 80 GB, and your fixed monthly Plan Charges would be <img alt="" src="images/icons-${initParam.client }/indian-rupee-sign.png" style="width: 7px; margin-right: 5px;" />1899 per month ( <img alt="" src="images/icons-${initParam.client }/indian-rupee-sign.png" style="width: 7px; margin-right: 5px;" />999 for Base Plan, and <img alt="" src="images/icons-${initParam.client }/indian-rupee-sign.png" style="width: 7px; margin-right: 5px;" />900 for Add-on Plan Charges)} </div>
                           
                        </div>
                      </div>
                      <div id="showhideonlimited" style="display: none; width: 100%; font-size: 12px;"><%-- ${migrationForm.crmPlanMasterPojo.planUsageType eq 'L' ? 'block' : 'none'} --%>
                        <div class="clearfix">
                          <div style="width: 900px; margin: auto;">
                            <div class="PageTitle typeface-js"> Limited Plan </div>
                            <div class="clearBoth BorderTop">
                              <div class="DivMarginTop">
                                <div class="bodyText"> Your current Tariff Plan is <span id="ctl00_ContentPlaceHolder1_Label5">${ migrationForm.crmPlanMasterPojo.planName };</span> <br />
                                  To migrate on another plan, please make your selection from table below: </div>
                              </div>
                              <div class="DivMarginTop"> <a>
                                <div class="myopSteps " data-step="0" id="step_1_limited">
                                  <div class="stepNumberContainer  activeStepNumber">
                                    <div class="stepNumber"> 1 </div>
                                  </div>
                                  <span class="SubHeading typeface-js">Select The Plan</span> </div>
                                </a>
                                <div class="clearBoth"> </div>
                              </div>
                              <div class="DivMarginTop">
                                <div class="bodyText"> You can also enjoy speeds higher than 10 Mbps and No Usage Limit with our Unlimited with our Unlimited Tariff plans.To migrate,<a onclick="showhidedivsontabclick('tabunlimited_popup');" style="text-decoration:underline;">CLICK HERE</a> </div>
                              </div>
                            </div>
                          </div>
                          <div class="my-helper-full-width DivMarginTop">
                            <div class="myopLimContainer my-helper-full-width" style="text-align: inherit!important;">
                              <div class="myopBaseplan" style="text-align: center!important; width: 100%!important">
                                <table width="100%">
                                  <tbody>
                                    <tr class="basePlanRow" style="text-align: center!important">
                                      <td class="NextraDarkGrey" style="width: 120px;">Plan&nbsp;Name </td>
                                      <td class="NextraDarkGrey" style="width: 75px;"><span class="">Speed</span></td>
                                      <td class="NextraDarkGrey" style="width: 75px;"><span class="">Total DUL</span></td>
                                      <td class="NextraDarkGrey" style="width: 110px;"><span class="">Monthly Charges</span></td>
                                      <td class="NextraDarkGrey" style="width: 180px;"><span class="">Post DUL Usage Charges</span></td>
                                    </tr>
                                    
                                    <c:forEach items="${ migrationForm.limitedBasePlanMasterPojos }" var="limitedBasePlanMasterPojos" varStatus="current">
                                    <tr class="basePlanRow animateBaseBodyRow">
                                      <td class="NextraLightGrey"><span class="">
                                       <html:radio name="migrationForm" property="selectedPlanCode" value="${limitedBasePlanMasterPojos.planCode}" 
                                       	onclick="showradiobuttons11('${limitedBasePlanMasterPojos.planName}','${limitedBasePlanMasterPojos.primarySpeed div 1024}','${limitedBasePlanMasterPojos.primaryQuota div 1024 div 1024 div 1024}','${limitedBasePlanMasterPojos.secondarySpeed}');"></html:radio>
                                          ${limitedBasePlanMasterPojos.planName}</span>
                                       </td>
                                      <td class="NextraLightGrey"><span class="tarrifPlanBaseSpeed " data-speed="10">
                                      <fmt:formatNumber type="number" maxFractionDigits="0" value="${limitedBasePlanMasterPojos.primarySpeed div 1024}" />&nbsp;Mbps</span></td>
                                      <td class="NextraLightGrey"><span class="">${limitedBasePlanMasterPojos.primaryQuota div 1024 div 1024 div 1024}&nbsp;GB</span></td>
                                      <td class="NextraLightGrey"><span class="tarrifPlanBasePrice" data-price="699">
                                       <img alt="" src="images/icons-${initParam.client }/indian-rupee-sign.png" style="width: 10px; margin-right: 5px;" />${limitedBasePlanMasterPojos.rentExclTax} </span></td>
                                      <td class="NextraLightGrey"><span class="">10p&nbsp;/&nbsp;MB</span></td>
                                    </tr>
                                	</c:forEach>
                                  </tbody>
                                </table>
                              </div>
                            </div>
                          </div>
                          <p style="color: #818181; font-size: 11px; line-height: 11px; width: 817px;font-family: Verdana; height: 12px; text-align: left"> * Prices shown above are exclusive of Service Tax. </p>
                        </div>
                      </div>
                      <div id="showradiooption" style="display: none;">
                        <div class="row-info1 gap-top-10" style="margin-left: 0px !important; width: 100%;">
                          <label> </label>
                          <div class="user-detail"></div>
                        </div>
                        <div class="row-info1 gap-top-10 bodyText" style="margin-left: 0px !important; width: 100%;">
                          <div style="float:left">
                            <label>Current Plan Info</label>
                            <div class="user-detail"> <span>${ migrationForm.crmPlanMasterPojo.planName }<br />
                              ${ migrationForm.crmPlanMasterPojo.primarySpeed div 1024 }&nbsp;Mbps&nbsp;upto&nbsp;${ migrationForm.crmPlanMasterPojo.primaryQuota div 1024 div 1024 div 1024 }&nbsp;GB<br />
                              512&nbsp;thereafter </span> </div>
                          </div>
                          <div style="float:right;width:50%">
                            <label>New&nbsp;Plan&nbsp;Info</label>
                            <div class="user-detail" id="planInfoID"></div>
                          </div>
                        </div>
                        <div class="row-info1 gap-top-10 bodyText hidden" style="margin-left: 0px !important; width: 100%;" id="adviceDivId" >
                          <label>Advice</label>
                          <div class="user-detail">
                            <span  id="adviceId"></span> 
                          </div>
                        </div>
                        <div class="row-info1 gap-top-10 bodyText" style="margin-left: 0px !important; width: 100%;">
                          <label>Activation&nbsp;time</label>
                          <div class="user-detail">
                             <html:select property="activationTime" name="migrationForm" styleId="activationPopUpId" onchange="setPlanAdvice(this.value);">
                              </html:select>
                          </div>
                        </div>
                        <div class="row-info1 gap-top-10" style="margin-left: 0px !important; width: 100%;">
                          <label> </label>
                          <div class="user-detail"></div>
                        </div>
                        <div class="row-info1 gap-top-10" style="margin-left: 0px !important; width: 100%;">
                          <label> </label>
                          <div class="user-detail"></div>
                        </div>
                        <div class="row-info1 gap-top-10" style="margin-left: 0px !important; width: 100%;">
                          <label> </label>
                          <div class="user-detail">
                            <input type="button" value="Submit" class="submit-btn  gap-right-30" id="btnpasswordReset" onclick="submitPlanMigrationDetail();"/>
                          </div>
                        </div>
                      </div>
                    </div>
                  </div>
                </div></td>
            </tr>
          </table>
        </div>
        </div>
        </div>  
</div>  
</html:form>
</body>
</html>