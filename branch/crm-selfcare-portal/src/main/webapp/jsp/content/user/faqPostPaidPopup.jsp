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
<link href="css/color-${initParam.client}.css" type="text/css" rel="stylesheet" />
<!-- =============== fancy box =====================-->
<link rel="stylesheet" type="text/css" href="css/jquery.fancybox.css" />
<script type="text/javascript" src="javascript/jquery-1.10.2.min.js"></script>
<script type="text/javascript" src="javascript/jquery.fancybox.pack.js"></script>
<script type="text/javascript" src="javascript/fancybox-custom.js"></script>
<script type="text/javascript" src="javascript/selfcare.js"></script>
<!-- ========================= from CRM ================= -->
<link rel="stylesheet" type="text/css" href="css/faq.css" />
<link rel="stylesheet" type="text/css" href="css/customStyle.css" />
<link rel="stylesheet" type="text/css" href="css/jquery-ui-1.7.3.custom.css"  />
<link rel="stylesheet" type="text/css" href="css/jquery-ui.css"/>
<link rel="stylesheet" type="text/css" href="css/fonts.css"/>
<script src="javascript/Jquery_Latest.js" type="text/javascript"></script>
<script src="javascript/jquery-ui.js" type="text/javascript"></script>
<script src="javascript/jquery.blockUI.js" type="text/javascript"></script>
<script type="text/javascript">
        $(document).ready(function () {
           
                $("#accordion").accordion({
                    heightStyle: "content"
                });
               
            });
           
    </script>
</head>
<body>
  <div style="background-color: #fff; height:550px; width:538px; heightStyle: 'content';  draggable: 'true';">
    <div class="titleBar">
      <span class="pageTitle" style="margin-left:8px !important;">Frequently Asked Questions</span>
              <html:link href='#' onclick="parent.jQuery.fancybox.close();" styleId="closeBtn" />
    </div>
 
        <div id="accordion" style="margin-top:10px !important;">
            <h3 class="main-head" style="margin-left:18px !important;">
                What is the meaning of "Billing period"?</h3>
            <div class="accordintext" style="width:434px !important; font-size:12px !important;">
                'Billing Period' is normally a month. The first billing period can be less than a month. Depending on the activation date, your "Period" will be either 5th of every month to 4th of next month or 20th of every month to 19th of next month.</div>
            <h3 class="main-head" style="margin-left:18px !important;">
                What is the meaning of "Due Date"?</h3>
            <div class="accordintext" style="width:434px !important; font-size:12px !important;">
                'Due Date' is the date mentioned in your bill by which you should pay your due to
                avoid any penalty or disconnection.            </div>
            <h3 class="main-head" style="margin-left:18px !important;">
                How can I pay my bills?</h3>
            <div class="accordintext" style="width:434px !important; font-size:12px !important;">
                <p>
                    You can pay your bill through:</p>
                <ul>
                    <li>Online - By Credit Card/ Debit Card/ Net Banking. Log onto "My Account" section on <a href="#" target="_blank">${initParam.client}</a> or through "Quick Pay" option on our Website</li>
                    <li>Cash - can be paid at all "Easy Bill" outlets. Please <a href="#"
                        target="_blank">click here</a> for authorized outlets.</li>
                    <li>Cheque - Drop cheque at any HDFC Branch drop box.<strong>(While dropping cheques at HDFC drop boxes, it is mandatory to attach 'Payment
                            Slip' to the cheque.)</strong></li>
                </ul>
                <div style="clear: both;">
                </div>
            </div>
             <h3 class="main-head" style="margin-left:18px !important;">
                How can I receive my bills via email?</h3>
                       <div class="accordintext" style="width:434px !important; font-size:12px !important;">
                <p>
                    You can subscribe to E-bill service free of cost.You can also get E-bill by:</p>
                <ul>
                    <li>Logging onto "My Account" section on <a href="#" target="_blank">${initParam.client }</a></li>
                    <li>Calling Customer Support at 92125 99911.</li>
                    <li>Sending an email request at <a href="#">support@${initParam.client }.com</a>
                        from your registered email id.</li>
                </ul>
                <div style="clear: both;">
                </div>
            </div>
             <h3 class="main-head" style="margin-left:18px !important;">
                How can I get Itemized Bill for my usage?</h3>
            <div class="accordintext" style="width:434px !important; font-size:12px !important;">
                <p>
                    You can download itemized usage from "My Account" or by:</p>
                 <ul>
                    <li>Calling Customer Support at 92125 99911.</li>
                     <li>Sending an email request at <a href="#">support@${initParam.client }.com</a>
                        from your registered email id.</li>
                   
                </ul>
            </div>
        
             <h3 class="main-head" style="margin-left:18px !important;">
                How can I change my Billing Address?</h3>
            <div class="accordintext" style="width:434px !important; font-size:12px !important;">
                <p>
                    You can change your Billing Address by:</p>
                <ul>
                    <li>Logging into "My Account" or</li>
                    <li>Calling Customer Support at 92125 99911.</li>
                     <li>Sending an email request at <a href="#">support@${initParam.client }.com</a>
                        from your registered email id.</li>
                </ul>
                <div style="clear: both;">
                </div>
            </div>
            <h3 class="main-head" style="margin-left:18px !important;">
                Will I receive notification on exceeding the Data Usage Limit (DUL)?</h3>
            <div class="accordintext" style="width:434px !important; font-size:12px !important;">
                <p>
                    You will receive alerts after consuming 80% and 100% of DUL. Alerts will be sent
                    through:</p>
                <ul>
                    <li>SMS on your Registered Mobile Number</li>
		            <li>On Screen Message</li>
                </ul>
                <p>
                    You can also check the Data Usage in the "My Account" section on  <a href="#" target="_blank">${initParam.client }</a>.</p>
                <div style="clear: both;">
                </div>
            </div>
            <h3 class="main-head" style="margin-left:18px !important;">
                What is the description of the various charges charged in the Bill?</h3>
            <div class="accordintext" style="width:434px !important; font-size:12px !important;">
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
                    <tr>
                        <td align="left" valign="top">
                            Chargeable Usage
                        </td>
                        <td align="left" valign="middle">
                            Incase of "Usage Based" Plans, Chargeable Usage is the usage charged post consumption
                            of Data Usage Limit (DUL) as per the tariff plan.
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
                What are Cheque Bounce charges?</h3>
            <div class="accordintext" style="width:434px !important; font-size:12px !important;">
                Cheque Bounce charges are levied in case cheque payment is returned unpaid, for
                any reason, by your bank.</div>
             <h3 class="main-head" style="margin-left:18px !important;">
                What is unbilled usage?</h3>
            <div class="accordintext" style="width:434px !important; font-size:12px !important;">
                Unbilled usage is the Data Usage consumed on your account till date, within the current Validity Period.</div>
             <h3 class="main-head" style="margin-left:18px !important;">
                What are the different available Plans?</h3>
            <div class="accordintext" style="width:434px !important; font-size:12px !important;">
                Please <a href="#" target="_blank">
                    click here</a> to view various Tariff Plans currently available.</div>
             <h3 class="main-head" style="margin-left:18px !important;">
                How do I change my Tariff Plan?</h3>
            <div class="accordintext" style="width:434px !important; font-size:12px !important;">
                <p>
                    You can change your plan:</p>
                <ul>
                    <li>In the "My Account" section on the <a href="#" target="_blank">${initParam.client }</a>.</li>
                    <li>Calling Customer Support at 92125 99911.</li>
                     <li>Sending an email request at <a href="#">support@${initParam.client }.com</a> from your registered email id.</li>
                </ul>
                <div style="clear: both;">
                </div>
            </div>
        </div>
</div>

</body>
</html>