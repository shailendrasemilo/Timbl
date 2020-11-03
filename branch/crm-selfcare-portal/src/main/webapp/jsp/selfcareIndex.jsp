<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@page import="com.np.tele.crm.service.client.CrmCustMyAccountPojo"%>
<%@page import="com.np.tele.crm.utils.StringUtils"%>
<%--
	request.getRequestDispatcher("/login.do?method=loginPage").forward(request,response);
--%>
<%
    Object userDetails = session.getAttribute( CrmCustMyAccountPojo.class.getSimpleName() );
    if ( StringUtils.isValidObj( userDetails ) )
    {
        request.getRequestDispatcher( "/user.do?method=myAccountPage" ).forward( request, response );
    }
    else
    {
        request.getRequestDispatcher( "/quickPay.do?method=quickPayPage" ).forward( request, response );
    }
%>
<%@taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles"%>
<tiles:insert page="/jsp/layouts/selfcareBaseLayout.jsp" flush="true">
	<tiles:put name="title" value="My Account" />
	<tiles:put name="header" value="/jsp/header/selfcareHeader.jsp" />
	<tiles:put name="menu" value="/jsp/content/selfcareMenu.jsp"></tiles:put>
	<tiles:put name="body" value="/jsp/content/myaccount/selfcare.jsp" />
	<tiles:put name="footer" value="/jsp/footer/selfcareFooter.jsp" />
</tiles:insert>
