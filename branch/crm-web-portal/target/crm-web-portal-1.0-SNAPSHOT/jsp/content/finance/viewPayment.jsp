<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@page import="com.np.tele.crm.service.client.CrmAuditLogPojo"%>
<%@page import="com.np.tele.crm.utils.StringUtils"%>
<%@taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<%@taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@taglib uri="http://struts.apache.org/tags-nested" prefix="nested"%>
<%@taglib uri="/WEB-INF/tlds/displaytag.tld" prefix="display"%>
<head>
<link href="css/common.css" rel="stylesheet" media="screen" />
<link href="css/color-${initParam.client }.css" rel="stylesheet" media="screen" />
<link href="css/displaytag.css" rel="stylesheet"
	type="text/css" />
<script src="javascript/jquery.min.js" type="text/javascript"></script>
<script src="javascript/common.js" type="text/javascript"></script>
<script>
	$(window).load(function(){
		$('.viewLmsTable').show();
		$('.lmsMinusImage').hide();
	});
</script>
</head>
<body>
	<html:form action="/paymentTracking" styleId="IDviewPaymentForm"
		method="post">
		<div id="lightboxPanel">
			<div class="popUp1">
				<div class="popUpContainer"></div>
				 <%
					Object auditLogDetails = session.getAttribute(CrmAuditLogPojo.class.getSimpleName());
					if(StringUtils.isValidObj(auditLogDetails)){
				 %>
				 <div class="LmsTable">
				 <h4>
			   			Activity Log Details <span class="lmsMinusImage floatr"></span>
			  		</h4>
				     <div class="viewLmsTable margintop10">
									 <iframe src="jsp/content/masterdata/displayAuditLog.jsp" frameborder="0" scrolling="yes"
			              style="border: none; overflow: hidden; width: 100%; height:250px;" allowTransparency="true" onload="iframeLoaded(this)"></iframe>
					 </div>
				</div>
				 <% }else{
				     out.println( "<p class='marginleft30'><b> No Record Found </b></p>" );
				    }
				 %>
				<br class="clear" />
			</div>
		</div>
	</html:form>
</body>