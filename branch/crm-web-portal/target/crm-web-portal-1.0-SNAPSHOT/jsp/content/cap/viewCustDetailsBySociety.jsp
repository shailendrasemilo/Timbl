<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib uri="/WEB-INF/tlds/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/tlds/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/tlds/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/tlds/displaytag.tld" prefix="display"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link href="css/displaytag.css" rel="stylesheet" type="text/css" />
<link href="css/common.css" rel="stylesheet" media="screen" />
<link href="css/color-${initParam.client }.css" rel="stylesheet" media="screen" />
<link href="css/lms.css" rel="stylesheet" media="screen" />
<link href="css/userManagement.css" rel="stylesheet" media="screen" />
<link href="css/ina.css" rel="stylesheet" media="screen" />
<link href="css/masterdata.css" rel="stylesheet" media="screen" />
<script src="javascript/jquery.min.js" type="text/javascript"></script>
<script src="javascript/jquery.validate.js" type="text/javascript"></script>
<script src="javascript/ina.js" type="text/javascript"></script>
</head>
<body>
	<div id="popupSection">
		<form action="/crmCap" id="custDetailsBySocietyID" method="post">
			<div class="popupSection">
				<h2>Customer Details</h2>
				<div class="inner_section">
					<!-- Success Messages Starts -->
					<div class="success_message">
						<logic:messagesPresent message="true">
							<html:messages id="message" message="true">
								<bean:write name="message" />
							</html:messages>
						</logic:messagesPresent>
					</div>
					<!-- Success Messages Ends -->
					<div class="error_message" id="error">
						<html:errors />
					</div>
					<!-- for error messages -->
					<logic:notEmpty name="crmCapForm" property="commonCustDetailsPojos">
						<div class=" paddingTop10  floatl clear" id="">
							<ul class="tabcontentUL">
								<li><strong>State:</strong> <label><bean:write name="crmCapForm" property="state" /></label></li>
								<li><strong>City:</strong> <label><bean:write name="crmCapForm" property="city" /></label></li>
								<br class="clear" />
								<li><strong>Area:</strong> <label><bean:write name="crmCapForm" property="area" /></label></li>
								<li><strong>Locality:</strong> <label> <bean:write name="crmCapForm" property="locality" /></label></li>
							</ul>
						</div>

						<br class="clear" />

						<display:table id="customerDetails" name="${crmCapForm.commonCustDetailsPojos}" class="dataTable" style="width:100%" pagesize="10" requestURI="">
							<display:setProperty name="paging.banner.placement" value="bottom" />
							<display:column title="Sr.No.">
								<bean:write name="customerDetails_rowNum" />
							</display:column>
							<display:column property="crfId" title="CAF No."></display:column>
							<display:column property="customerId" title="Customer ID"></display:column>
							<display:column property="custName" title="Customer Name"></display:column>
							<display:column title="Status">
								<logic:notEmpty name="customerDetails" property="status">
									<bean:write name="crmRoles" property="displayEnum(AllStatus,${customerDetails.status})" scope="session" />
								</logic:notEmpty>
							</display:column>
							<display:column property="instAddress" title="House No."></display:column>
							<display:column title="MAC ID">
								<logic:empty name="customerDetails" property="firstCpeMacId"> - </logic:empty>
								<logic:notEmpty name="customerDetails" property="firstCpeMacId">
									<bean:write name="customerDetails" property="firstCpeMacId" />
								</logic:notEmpty>
							</display:column>
							<display:column title="Society">
								<logic:notEmpty name="customerDetails" property="societyName">
									<bean:write name="customerDetails" property="societyName" />
								</logic:notEmpty>
								<logic:empty name="customerDetails" property="societyName">
									-
								</logic:empty>
							</display:column>
						</display:table>
					</logic:notEmpty>
					<logic:empty name="crmCapForm" property="commonCustDetailsPojos">
						<span style="font-weight: bold;">No customer found under status Barred, Temporary Disconnected, Safe Custody for this Society.</span>
					</logic:empty>
					<br class="clear" />
				</div>

			</div>

		</form>
	</div>
</body>
</html>