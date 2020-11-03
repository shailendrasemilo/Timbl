<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	if (request.getSession() != null && request.getSession().getAttribute("crmUserDetailsDto") != null) {
%>
		<c:redirect url="/login.do?method=loginAuthentication" />
<%
	}
%>
<tiles:insert page="/jsp/layouts/loginLayout.jsp" flush="true">
	<tiles:put name="title" value="Login" />
	<tiles:put name="header" value="/jsp/header/loginHeader.jsp" />
	<tiles:put name="body" value="/jsp/content/user/login.jsp" />
	<tiles:put name="footer" value="/jsp/footer/loginFooter.jsp" />
</tiles:insert>
