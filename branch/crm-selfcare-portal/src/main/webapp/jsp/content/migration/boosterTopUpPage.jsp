<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<%@taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Untitled Document</title>
<link href="css/myaccount-common.css" type="text/css" rel="stylesheet" />
<link href="css/popup-common.css" type="text/css" rel="stylesheet" />
<link href="css/style.css" type="text/css" rel="stylesheet" />
<link href="css/main.css" type="text/css" rel="stylesheet" />
<link href="css/color-${initParam.client}.css" type="text/css" rel="stylesheet" />
<!-- =============== fancy box =====================-->
<link href="css/jquery.fancybox.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="javascript/jquery-1.10.2.min.js"></script>
<script type="text/javascript" src="javascript/jquery.fancybox.pack.js"></script>
<script type="text/javascript" src="javascript/fancybox-custom.js"></script>
<script type="text/javascript" src="javascript/selfcare.js"></script>
</head>

<body>
<html:form action="/migration" styleId="boosterTopUpForm">
<div class="mainWrapper loginWrap">
	<div class="titleBar">
    	<span class="pageTitle">Top Up</span>
        <html:link href='#' onclick="parent.jQuery.fancybox.close();" styleId="closeBtn" />
    </div>
    <div class="loginArea">
        <div style="margin-bottom: 25px; min-height: 400px; overflow: hidden;">
                <div style="height: 0;"> </div>
                <div class="SubHeading typeface-js colorBlue" style="margin-top: 5px;"> Booster Usage Top-Up </div>
                
                <logic:empty name="migrationForm" property="crmPlanMasterPojo.rentExclTax">
                	<div class="bodyText" style="margin: auto;">
                		Oops!!! You are not authorized to purchase booster plan.
                	</div>
                </logic:empty>
                <logic:notEmpty name="migrationForm" property="crmPlanMasterPojo.rentExclTax">
                <div style="margin: auto;">
                  <div class="clearBoth BorderTop">
                    <div class="DivMarginTop">
                      
                      <div class="bodyText"> Running out of Data Usage Limit (DUL) ? <br />
                        And not sure of Upgrading the Plan by adding <a target="_blank" class="linkChangePlan" style="text-decoration: underline;">Add-On DUL</a><br />
                        Now you can do a quick Usage Top-Up and grab those additional GBs to enjoy seamless
                        internet experience. </div>
                    </div>
                    <div class="my-helper-full-width DivMarginTop">
                      <div style="width: 600px;">
                        <table width="100%" cellpadding="0" cellspacing="0" border="0">
                          <tr>
                            <td class="NextraBlue basePlanRow"><span class="colorWhite">Data Usage Limit</span></td>
                            <td class="NextraBlue basePlanRow"><span class="colorWhite">Price</span></td>
                          </tr>
                          <tr>
                            <td class="NextraGrey bodyText typeface-js"><span class="colorBlue">2 GB and multiples of 2 GB thereafter </span></td>
                            <td class="NextraGrey bodyText typeface-js"><span class="colorBlue"> <img alt="" src="images/icons-${initParam.client }/indian-rupee-sign.png" style="width: 10px; margin-right: 5px;" /> ${migrationForm.crmPlanMasterPojo.rentExclTax} for every 2 GB </span></td>
                          </tr>
                        </table>
                      </div>
                    </div>
                    <div>
                      <div class="bodyText"> Note: Booster Usage Top-Up is non-refundable and does not auto renew. Unutilized
                        portion of Booster Usage Top-Up would not be carried forward to the next billing
                        cycle. <br/>
                        <br />
                      </div>
                    </div>
                    <div class="row-info1 gap-top-10 bodyText" style="margin-left: 0px !important; width: 100%;margin-top: -5px;">
                    	<logic:messagesPresent property="appError">
        <bean:define id="hasErrors" value="true"/>
        <div class="msgerror ${ hasErrors ? '' : 'hide' }">
        <html:errors />
      </div>
      </logic:messagesPresent>
      
      <logic:messagesPresent message="true">
        <html:messages id="message" message="true">
          <div class="msgsuccess">
            <bean:write name="message" />
          </div>
        </html:messages>
      </logic:messagesPresent>

                      <div style="float:left;width:60%;font-size:12px;margin-top:5px">
                        <div class="${ migrationForm.crmPlanMasterPojo.rentExclTax gt '0' ? '' : 'hidden' }">
                        <label> Select Booster Usage TopUp </label>
                        <div class="user-detail">
                        	<html:select name="migrationForm" property="topUpUsage" style="width: 120px" styleId="drpselectdul" onchange="fun_onselectchangetext(this.id,'${migrationForm.crmPlanMasterPojo.rentExclTax}');">
                        		<html:option value="">Please Select</html:option>
                        		<html:option value="2">2&nbsp;GB</html:option>
                            	<html:option value="4">4&nbsp;GB</html:option>
                            	<html:option value="6">6&nbsp;GB</html:option>
                            	<html:option value="8">8&nbsp;GB</html:option>
                            	<html:option value="10">10&nbsp;GB</html:option>
                            	<html:option value="12">12&nbsp;GB</html:option>
                        	</html:select>
                        	<font></font>
                        </div>
                        <div id="onselectshowprice_div" class="row-info1 gap-top-10" style="margin-left: 0px !important;display:none;">
                          <label> You will be charged : </label>
                          <div class="user-detail" ><img alt="" src="images/icons-${initParam.client }/indian-rupee-sign.png" style="width: 7px;" /> <span id="onselectshowprice"></span></div>
                        </div>
                        <div class="row-info1 gap-top-10" style="margin-left: 0px !important;">
                          <label> </label>
                          <div class="user-detail" >
                            <input type="button" value="Submit" class="submit-btn  gap-right-30" id="btnsubmittopupo" onclick='javascript:topUpUsageBooster();'/>
                          </div>
                        </div>
                        </div>
                        <div class="${ migrationForm.crmPlanMasterPojo.rentExclTax eq '0' ? '' : 'hidden' }">
                           No Booster Usage Top-Up plans available, currently.
                        </div>
                      </div>
                      <div style="float:right;width:40%;font-size:12px;" class="bodyText" >
                        <label style="color:red;display:none;width: 100%;" id="onselectchangetext" > <strong>Do you require more than 6 GB?</strong><br/>
                          It is advised to upgrade the plan by adding Add-On DUL rather than subscribing to Booster Usage Top Up.<br/> To upgarde<strong> <a class="linkChangePlan" target="_blank" style="text-decoration:underline;">CLICK HERE</a></strong> or call our Call Center for a customised advice. </label>
                        <div class="row-info1 gap-top-10" style="margin-left: 0px !important;">
                          <label> </label>
                          <div class="user-detail" > </div>
                        </div>
                      </div>
                    </div>
                  </div>
                </div></logic:notEmpty>
              </div>
    </div>
</div>
 </html:form>
</body>
</html>
