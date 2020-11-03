<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles"%>
<%@taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<%@taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link href="css/myaccount-common.css" type="text/css" rel="stylesheet" />
<link href="css/popup-common.css" type="text/css" rel="stylesheet" />
<!-- =============== fancy box =====================-->
<link rel="stylesheet" type="text/css" href="css/jquery.fancybox.css" />
<script type="text/javascript" src="javascript/jquery-1.10.2.min.js"></script>
<script type="text/javascript" src="javascript/jquery.fancybox.pack.js"></script>
<script type="text/javascript" src="javascript/fancybox-custom.js"></script>
<script type="text/javascript" src="javascript/selfcare.js"></script>
<!-- ========================= from CRM ================= -->
<link rel="stylesheet" type="text/css" href="css/faq.css" />
<link rel="stylesheet" type="text/css" href="css/customStyle.css" />
<link rel="stylesheet" type="text/css" href="css/color-${initParam.client}.css" />
<link rel="stylesheet" type="text/css" href="css/jquery-ui-1.7.3.custom.css"  />
<link rel="stylesheet" type="text/css" href="css/jquery-ui.css"/>
<link rel="stylesheet" type="text/css" href="css/fonts.css"/>
<script src="javascript/Jquery_Latest.js" type="text/javascript"></script>
<script src="javascript/jquery-ui.js" type="text/javascript"></script>
<script src="javascript/jquery.blockUI.js" type="text/javascript"></script>
    



<script type="text/javascript">
        $(document).ready(function () {
                $("#prepaidaccordion").accordion({
                    heightStyle: "content"
                });
            });
    </script>
</head>
<body>
  <div style="background-color: #fff; height:550px; width:538px; heightStyle: 'content';  draggable: 'true';">
    <div class="titleBar">
      <span class="pageTitle"  style="margin-left:8px !important;">Frequently Asked Questions</span>
       <html:link href='#' onclick="parent.jQuery.fancybox.close();" styleId="closeBtn" />
    </div>
        <div id="prepaidaccordion" style="margin-top:10px !important;">
            <h3 class="main-head" style="margin-left:18px !important;">
                What is the "Validity Period" for my connection?</h3>
            <div class="accordintext"  style="width:434px !important; font-size:12px !important;">
                Since this is a prepaid plan, you connection is valid for one calendar month, from the Activation Date of your plan. For Limited plans, validity will expire once the allocated data is consumed, even if the validity period is not over.</div>
            <h3 class="main-head" style="margin-left:18px !important;">
               What is the meaning of "Expiry Date"?</h3>
            <div class="accordintext"  style="width:434px !important; font-size:12px !important;">
                "Expiry Date" means the date when validity of your plan will expire.
                You need to recharge your account on or before the "Expiry Date" to continue your services.</div>
            <h3 class="main-head" style="margin-left:18px !important;">
               How can I 'Recharge' my account?</h3>
            <div class="accordintext"  style="width:434px !important; font-size:12px !important;">
                <p>
                    You can 'Recharge' your account through:</p>
                <ul>
                    <li>Online - Using Credit Card/ Debit Card/ Net Banking by logging into "My Account" or "Quick Pay" section on <a href="#"
                        target="_blank">${initParam.client }</a>. </li>
                    <li>Cash - You can make cash payment to your Local Cable Operator to recharge ypour account. 
                               Alternatively, you can also make cash payment at all "Easy Bill" outlets.
                               If paid at "Easy Bill" outlet, your account will be recharged on next working day. Please <a href="#"
                        target="_blank">click here</a> for authorized outlets.</li>
                   
                </ul>
                <div style="clear: both;">
                </div>
            </div>
            <h3 class="main-head" style="margin-left:18px !important;">
                How will I get Invoice/ Statement for my account?</h3>
            <div class="accordintext"  style="width:434px !important; font-size:12px !important;">
               
                <ul>
                     <li>
                   	You can log on to "My Account" section on <a href="#" target="_blank">${initParam.client }</a> & download your Invoice/ Statement.</li>
                    <li>By calling our Customer Support at 92125 99911</li>
                    <li>By sending an email request at <a href="#">support@${initParam.client }.com</a> from your registered email id</li>
                </ul>
                <div style="clear: both;">
                </div>
            </div>
            <h3 class="main-head" style="margin-left:18px !important;">
                How can I get Itemized Bill for my usage?</h3>
            <div class="accordintext"  style="width:434px !important; font-size:12px !important;">
                <ul>
                     <li>
                  	You can download Itemized Usage by logging on to "My Account" section on the <a href="#" target="_blank">${initParam.client }</a>.</li>
                    <li>By calling our Customer Support at 92125 99911</li>
                    <li>By sending an email request at <a href="#">support@${initParam.client }.com</a> from your registered email id</li>
                </ul>
            </div>
           
            <h3 class="main-head" style="margin-left:18px !important;">
                How can I change my Billing Address?</h3>
            <div class="accordintext"  style="width:434px !important; font-size:12px !important;">
                <p>
                    You can change your Billing Address by:</p>
                <ul>
                    <li>Logging into "My Account" or</li>
                    <li>By calling our Customer Support at 92125 99911</li>
                    <li>By sending an email request at <a href="#">support@${initParam.client }.com</a> from your registered email id</li>
                </ul>
                <div style="clear: both;">
                </div>
            </div>
            <h3 class="main-head" style="margin-left:18px !important;">
                Will I receive notification on exceeding the Data Usage Limit (DUL)?</h3>
            <div class="accordintext"  style="width:434px !important; font-size:12px !important;">
                <p>
                    You will receive alerts after consuming 80% and 100% of DUL. Alerts will be sent
                    through:</p>
                <ul>
                   <li>SMS on your Registered Mobile Number, and You can also check the Data Usage in the "My Account" section on the website.</li>
                </ul>
                <p>
                    You can also check the Data Usage in the "My Account" section on the website.</p>
                <div style="clear: both;">
                </div>
            </div>
            <h3 class="main-head" style="margin-left:18px !important;">
                What is the description of the various charges charged in the Bill?</h3>
            <div class="accordintext"  style="width:434px !important; font-size:12px !important;">
                <p>
                    The description is:</p>
                <table width="100%" border="0" cellspacing="1" cellpadding="5" class="tbl-info">
                    <tr>
                        <td width="40%" align="left" valign="top">
                            Base Plan charges
                        </td>
                        <td width="60%" align="left" valign="middle">
                            Monthly Base Plan charges as per the tariff plan opted by you.
                        </td>
                    </tr>
                    <tr class="alt">
                        <td align="left" valign="top">
                            Add-on Plan charges
                        </td>
                        <td align="left" valign="middle">
                            Charges/ Rental for any Add on Plan opted by you.
                        </td>
                    </tr>
                    <tr>
                        <td align="left" valign="top">
                            Top-Up &amp; Scheme charges
                        </td>
                        <td align="left" valign="middle">
                            Charges/ Rental for any Top Up/ Scheme activated on your account.
                        </td>
                    </tr>
                    <tr class="alt">
                        <td align="left" valign="top">
                            Value Added Service
                        </td>
                        <td align="left" valign="middle">
                            Charges/ Rental for any Value Added Service activated on your account.
                        </td>
                    </tr>
                  
                    <tr class="alt">
                        <td align="left" valign="top">
                            Other Charges
                        </td>
                        <td align="left" valign="middle">
                            These include Installation Charge/ One time charge/ Rewiring Charge/ Shifting Charge/
                            Itemized Paper Bill charge, etc. as applicable.
                        </td>
                    </tr>
                    <tr>
                        <td align="left" valign="top">
                            Discount
                        </td>
                        <td align="left" valign="middle">
                            Discounts as may be applicable under the opted scheme.
                        </td>
                    </tr>
                    <tr class="alt">
                        <td align="left" valign="top">
                            Tax
                        </td>
                        <td align="left" valign="middle">
                            Amount as per government regulations.
                        </td>
                    </tr>
                </table>
                <div style="clear: both;">
                </div>
            </div>
            
            <h3 class="main-head" style="margin-left:18px !important;">
                What is unbilled usage?</h3>
            <div class="accordintext"  style="width:434px !important; font-size:12px !important;">
               Unbilled usage is the Data Usage consumed on your account till date, within the current Validity Period.</div>
           <h3 class="main-head" style="margin-left:18px !important;">
                What are the different available Plans?</h3>
            <div class="accordintext"  style="width:434px !important; font-size:12px !important;">
                Please <a href="#" target="_blank">
                    click here</a> to view various Tariff Plans currently available.</div>
            <h3 class="main-head" style="margin-left:18px !important;">
                How do I change my Tariff Plan?</h3>
            <div class="accordintext"  style="width:434px !important; font-size:12px !important;">
                <p>
                    You can change your plan:</p>
                <ul>
                    <li>In the "My Account" section on the website</li>
                    <li>By sending an email request at <a href="#">support@${initParam.client }.com</a> from your registered email id</li>
                    <li>By calling our Customer Support at 92125 99911</li>
                </ul>
                <div style="clear: both;">
                </div>
            </div>
        </div>
</div>

</body>
</html>