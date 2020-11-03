<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<%@taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<%@taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<html>
<head>
<link href="css/common.css" rel="stylesheet" media="screen" />
<link href="css/color-${initParam.client }.css" rel="stylesheet" media="screen" />
<link href="css/userManagement.css" rel="stylesheet" media="screen" />
<link href="css/displaytag.css" rel="stylesheet" type="text/css" />
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
</head>
<body>
	<div class="popUp1">
		<div class="popUpContainer">
			<div class="contentContainer">
				<%
				    int i = 0;
				%>
				<p class="popUpRow<%=++i % 2%>">
					<font class="popUpHead">Locality / Sector - Society:</font> <font class="popUpText"> <logic:notEmpty name="gisForm"
							property="societyPojo.searchName">
							<bean:write name="gisForm" property="societyPojo.searchName" />
						</logic:notEmpty>
					</font>
				</p>
				<p class="popUpRow<%=++i % 2%>">
					<font class="popUpHead">State:</font> <font class="popUpText"><bean:write name="gisForm" property="state" /></font>
				</p>
				<p class="popUpRow<%=++i % 2%>">
					<font class="popUpHead">City:</font> <font class="popUpText"> <bean:write name="gisForm" property="city" />
					</font>
				</p>
				<p class="popUpRow<%=++i % 2%>">
					<font class="popUpHead">Area:</font> <font class="popUpText"><bean:write name="gisForm" property="area" /></font>
				</p>
				<p class="popUpRow<%=++i % 2%>">
					<font class="popUpHead">Customer Category:</font> <font class="popUpText"><bean:write name="gisForm" property="societyPojo.customerCategory" /></font>
				</p>
				<p class="popUpRow<%=++i % 2%>">
					<font class="popUpHead">Type of Field:</font> <font class="popUpText"> <logic:equal name="gisForm" property="societyPojo.networkAvailability"
							value="G">Green</logic:equal> <logic:equal name="gisForm" property="societyPojo.networkAvailability" value="B">Brown</logic:equal>
					</font>
				</p>
				<p class="popUpRow<%=++i % 2%>">
					<font class="popUpHead">Connectable Homes:</font> <font class="popUpText"><bean:write name="gisForm" property="societyPojo.connectableHomes" /></font>
				</p>
				<p class="popUpRow<%=++i % 2%>">
					<font class="popUpHead">Latitude:</font> <font class="popUpText"><bean:write name="gisForm" property="societyPojo.latitude" /></font>
				</p>
				<p class="popUpRow<%=++i % 2%>">
					<font class="popUpHead">Longitude:</font> <font class="popUpText"><bean:write name="gisForm" property="societyPojo.longitude" /></font>
				</p>
				<p class="popUpRow<%=++i % 2%>">
					<font class="popUpHead">Primary PIN Code:</font> <font class="popUpText"><bean:write name="gisForm" property="societyPojo.primaryPincode" /></font>
				</p>
				<p class="popUpRow<%=++i % 2%>">
					<font class="popUpHead">Secondary PIN Code:</font> <font class="popUpText"><bean:write name="gisForm" property="societyPojo.secPincode" /></font>
				</p>
				<p class="popUpRow<%=++i % 2%>">
					<font class="popUpHead">Created By:</font> <font class="popUpText"><bean:write name="gisForm" property="societyPojo.createdBy" /></font>
				</p>
				<p class="popUpRow<%=++i % 2%>">
					<font class="popUpHead">Created DateTime:</font> <font class="popUpText">
					<bean:write name="crmRoles" property="xmlDate(${ gisForm.societyPojo.createdTime})" scope="session"/>
					</font>
				</p>
				<p class="popUpRow<%=++i % 2%>">
					<font class="popUpHead">Last Modified By:</font> <font class="popUpText"> <logic:notEmpty name="gisForm" property="societyPojo.lastModifiedBy">
							<bean:write name="gisForm" property="societyPojo.lastModifiedBy" />
						</logic:notEmpty> <logic:empty name="gisForm" property="societyPojo.lastModifiedBy">-</logic:empty></font>
				</p>
				<p class="popUpRow<%=++i % 2%>">
					<font class="popUpHead">Last Modified DateTime:</font> <font class="popUpText"><logic:notEmpty name="gisForm"
							property="societyPojo.lastModifiedTime">
							<bean:write name="crmRoles" property="xmlDate(${ gisForm.societyPojo.lastModifiedTime})" scope="session"/>
						</logic:notEmpty> <logic:empty name="gisForm" property="societyPojo.lastModifiedTime">-</logic:empty></font>
				</p>
				<p class="popUpRow<%=++i % 2%>">
					<font class="popUpHead">Current Society Status:</font> <font class="popUpText"> <logic:notEmpty name="gisForm" property="societyPojo.status">
							<bean:write name="crmRoles" property="displayEnum(PartialStatus,${gisForm.societyPojo.status})" scope="session" />
						</logic:notEmpty>
					</font>
				</p>
				<div class="roleDisplayTable widthAuto">
					<p class="headerDisplayTable">
						<span style="width: auto">Partner and Product</span>
					</p>
					<div class="contentDisplayTable" style="margin-top: -25px;">
						<display:table id="data" name="sessionScope.gisForm.societyPojo.societyNetworkPartners" requestURI="" class="dataTable" style="width:100%">
							<display:column title="Partner Name">
								<bean:write name="crmRoles" property="displayEnum(NP,${data.partnerId})" scope="session" />
							</display:column>
							<display:column title="Service Name">
								<logic:notEmpty name="data" property="productName">
									<bean:write name="crmRoles" property="displayEnum(Product,${data.productName})" scope="session" />
								</logic:notEmpty>
							</display:column>
							<display:column title="Status">
								<logic:equal name="data" property="status" value="A">Active</logic:equal>
								<logic:equal name="data" property="status" value="I">Inactive</logic:equal>
							</display:column>
						</display:table>
					</div>
				</div>

				<div class="roleDisplayTable widthAuto">
					<p class="headerDisplayTable">
						<span style="width: auto">Partner and RFS DUs</span>
					</p>
					<div style="margin-top: -25px;">
						<logic:notEmpty name="gisForm" property="societyNetworkPartners">
							<display:table id="data" name="sessionScope.gisForm.societyNetworkPartners" requestURI="" class="dataTable" style="width:100%">
								<display:column title="Partner Name">
									<bean:write name="crmRoles" property="displayEnum(NP,${data.partnerId})" scope="session" />
								</display:column>
								<display:column title="RFS DUs" property="rfsDus" />
							</display:table>
						</logic:notEmpty>
					</div>
				</div>

			</div>
		</div>
	</div>
</body>
</html>