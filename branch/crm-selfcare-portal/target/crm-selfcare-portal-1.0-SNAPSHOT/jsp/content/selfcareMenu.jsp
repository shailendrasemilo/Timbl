<%@page import="com.np.tele.crm.service.client.CrmCustomerDetailsPojo"%>
<%@page import="com.np.tele.crm.utils.StringUtils"%>
<%@page import="com.np.tele.crm.service.client.CrmCustMyAccountPojo"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div id="Menu" class="top-nav-wrap">
  <div class="top-nav-cont">
    <div class="UpperCase top-nav-home">
      <div class="FloatLeft top-nav-link">
        <a href="./">
          <div class="SubHeading typeface-js" style="visibility: visible;">Home</div>
        </a>
      </div>
    </div>
    <div class="UpperCase top-nav-buss">
      <div class="FloatLeft top-nav-link">
        <a href="#">
          <div class="SubHeading typeface-js" style="visibility: visible;">BUSINESS</div>
        </a>
      </div>
    </div>
    <div class="UpperCase top-nav-acc">
      <div class="FloatLeft top-nav-link-sel">
        <a href="user.do?method=myAccountPage">
          <div class="SubHeading-sel typeface-js" style="visibility: visible;" id="fancybox-manual-e">MY ACCOUNT</div>
        </a>
      </div>
      <div class="user-info" style="margin-left: 0px; width: 660px;">
        <%
            CrmCustMyAccountPojo userDetails = (CrmCustMyAccountPojo) session.getAttribute( CrmCustMyAccountPojo.class
                    .getSimpleName() );
            CrmCustomerDetailsPojo customerDetails = (CrmCustomerDetailsPojo) session
                    .getAttribute( CrmCustomerDetailsPojo.class.getSimpleName() );
        %>
        Welcome <span id="ctl00_UserName"><%=StringUtils.isValidObj( customerDetails ) ? customerDetails.getCustFname()
                                                                : "Teleservices Pvt Ltd"%></span>&nbsp; | &nbsp;
        <%
            if ( !StringUtils.isValidObj( userDetails ) )
            {
                out.write( "<a class='btnLogOut' href='#' id='fancybox-signIn'>SIGN IN</a>" );
            }
            else
            {
                out.write( "<a id='fancybox-changePassword' href='#'>CHANGE PASSWORD</a> &nbsp; | &nbsp;" );
                out.write( "<a class='btnLogOut' href='user.do?method=logout'>LOG OUT</a>" );
            }
        %>
        <%--<a class="btnLogOut" href="#" id="fancybox-signIn">SIGN IN</a> --%>
      </div>
    </div>
  </div>
  <div id="ctl00_SubMenu" class="clearfix ui-corner-all UpperCase submenu-wrap">
    <div class="gap-right-10" style="margin-right: 10px;">
    <c:if test="${ (selfcareForm.customerDetailsPojo.serviceType eq 'PO') }">
      <a class="SubHeading-sel" href="#"><span class="subheading-pos">FAQ</span></a><%-- id="postPaidFAQ" --%>
    </c:if>
    <c:if test="${ (selfcareForm.customerDetailsPojo.serviceType eq 'PR') }">
      <a class="SubHeading-sel" href="#"><span class="subheading-pos">FAQ</span></a><%-- id="prePaidFAQ" --%>
    </c:if>
      <a class="SubHeading-sel"><span class="Seprator2">|</span><span class="subheading-pos">Payment Centres</span></a><%-- id="dialog_PaymentCenter" --%>
      <a id="dialog_logticketCenter_mp" class="SubHeading-sel"><span class="Seprator2">|</span><span class="subheading-pos btnlogticket">Log Ticket</span></a> 
      <span class="Seprator2" style="display: none;">|</span> <a id="aTrusted" class="SubHeading-sel" href="#"><span class="subheading-pos" style="display: none;">My Devices</span></a>
    </div>
  </div>
</div>