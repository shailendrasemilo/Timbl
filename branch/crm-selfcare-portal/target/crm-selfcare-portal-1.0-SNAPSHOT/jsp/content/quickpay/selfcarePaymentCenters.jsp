<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles"%>
<%@taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<%@taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link href="css/myaccount-common.css" type="text/css" rel="stylesheet" />
<link href="css/popup-common.css" type="text/css" rel="stylesheet" />
<link href="css/color-${initParam.client}.css" type="text/css" rel="stylesheet" />
<!-- =============== fancy box =====================-->
<link rel="stylesheet" type="text/css" href="css/jquery.fancybox.css" />
<script type="text/javascript" src="javascript/jquery-1.10.2.min.js"></script>
<script type="text/javascript" src="javascript/jquery.fancybox.pack.js"></script>
<script type="text/javascript" src="javascript/fancybox-custom.js"></script>
<script type="text/javascript" src="javascript/selfcare.js"></script>
<script type="text/javascript">
	$( document ).ready( function(){
		if ( window.top == window.self ) {
			window.location.href = 'user.do?method=logout';
			$( '.popup-loading' ).removeClass( 'hide' );
		}
	} );
</script>
</head>
<body>
<html:form action="/quickPay" styleId="pymntCntrsForm">
  <div class="popup-loading hide">
    <img alt="Please wait" src="images/icons-${initParam.client }/ajaxloading.gif" />
  </div>
  <div class="mainWrapper" id="PCArea">
    <div class="titleBar">
      <span class="pageTitle">Payment Centers</span>
         <html:link href='#' onclick="parent.jQuery.fancybox.close();" styleId="closeBtn" />
    </div>
	<div class="changePasswordArea" style="margin-bottom: 20px; background-color: #F7F7F7;">
      <div class="pInfo hide"></div>
            <div id="MainDiv" class="divStyle">
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
                <div class="style1">
                    <div class="PageTitle typeface-js hdfcDropBox">
                      <br />  HDFC Bank Drop Boxes
						</div>
                    <div class="clearBoth borderTop">
                        <div class="DivMarginTop bodyText typeface-js" style="visibility: visible;">
                            <p class="bodytext">
                                You can drop a cheque for your Broadband Bill at HDFC Bank&apos;s Drop 
								Boxes located at their <a href="http://www.hdfcbank.com/personal/find-your-nearest/find-nearest-branch/all-facilities/gxissh3j"
                                    target="_blank" class="colorBlue" style="text-decoration: underline; display: inline-block;">
                                    Branches</a> or <a href="http://www.hdfcbank.com/personal/find-your-nearest/find-nearest-atm"
                                        target="_blank" class="colorBlue" style="text-decoration: underline; display: inline-block;">
                                        ATMs</a> in Delhi/ NCR.
                                <br />
                                Click <a href="http://www.hdfcbank.com/personal/find-your-nearest/find-nearest-branch/all-facilities/gxissh3j"
                                    target="_blank" class="colorBlue" style="text-decoration: underline; display: inline-block;">
                                    Branches</a> or <a href="http://www.hdfcbank.com/personal/find-your-nearest/find-nearest-atm"
                                        target="_blank" class="colorBlue" style="text-decoration: underline; display: inline-block;">
                                        ATMs</a> to locate the nearest Drop Box. Please note it is mandatory to
                                attach the Payment Slip (part of the bill) along with the Cheque, failing which
                                the cheque would not be accepted.
                            </p>
                        </div>
                    </div>
                    <div style="clear: both; height: 5px;"></div>
                    <div class="PageTitle typeface-js style2">
                        Easy Bill Outlets</div>
                    <div class="clearBoth"  style=" border-top: solid 1px #E5E5E5;">
                        <div class="DivMarginTop bodyText typeface-js" style="visibility: visible;">
                            <p class="bodytext">
                                You can pay your Broadband Bills by Cash at Easy Bill Outlets in Delhi NCR only.
                                 You can find the nearest Easy Bill Outlet through the search option below.
                                 (Kindly note that all these Easy Bill Outlets accept payment by Cash only)
                            </p>
                        </div>
                    </div>
                </div>
                <div style="width: 535px; margin: auto;">
                    <div class="style3">
                        <div class="cityClass">
						<br class="clear" /> 
						<br class="clear" /> 
                            <span class="style4"> Pincode:</span>
							<html:select styleId="selectPC" name="quickPayForm" property="paymentCentresPojo.pincode">
							<html:option value="000000">Please Choose</html:option>
							 <html:options property="listPincode" name="quickPayForm"/>
							</html:select>	
							<html:link href="#" styleClass="signinBtn btn marginleft30" styleId="pymntCntrSearch">Search</html:link>							
                        </div>
                        <div class="cityClass" style="width: 90px;">
                         
                        </div>
                    </div>
					<logic:notEmpty name="quickPayForm" property="paymentCentresPojos">
                    <div style="width: 535px; margin: 30px auto; margin-left:-9px;">
        				<table id="paymentCenters" class="tableStyle">
          					<tbody>
        						 <c:forEach items="${quickPayForm.paymentCentresPojos }" var="data">
            						<tr style="background-color:#FAFAFA;font-weight:bold;height:0px;">
										<th scope="col" style="background-color:White;">&nbsp;</th>
										<th scope="col" style="background-color:White;">&nbsp;</th>
									</tr>
									<tr style="color:#333333;background-color:#F2F2F2;">
									<td style="border-color:#333333;">
              							<div>
                 							<div class="VendorName vendor">
                     							${data.vendorName}
               								</div>
                							<div class="bodyText detailStyle">
                      							${data.outletAddress}
                							</div>
             							</div>
            						</td>
            						<td style=" border-color:#333333;">
             							<img src="images/EasyBill/Cash.png" id="ContentPlaceHolder1_footerText_gridSearchData_Image1_0" align="right" />
            						</td>
									</tr>
          						</c:forEach>
          				</tbody>
       				</table>
		 		</p>
            </div>
			</logic:notEmpty>
         </div>
       </div>
     </div>
   </div>
</html:form>
</body>
</html>