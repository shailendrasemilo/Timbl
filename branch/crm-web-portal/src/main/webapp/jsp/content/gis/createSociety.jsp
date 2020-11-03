<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<%@taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<head>
<link href="css/displaytag.css" rel="stylesheet" type="text/css" />
</head>
<div class="loadingPopup hidden"></div>
<div id="section" align="center">
	<html:form action="/gis" method="post" styleId="FORMgisAddExcel">
		<div class="section">
			<logic:equal name="gisForm" property="societyPojo.societyId" value="0">
				<h2>Add Locality / Sector - Society</h2>
			</logic:equal>
			<logic:notEqual name="gisForm" property="societyPojo.societyId" value="0">
				<h2>Edit Locality / Sector - Society</h2>
			</logic:notEqual>
			<div class="inner_section">
				<div class="inner_left_lead floatl marginleft10">
					<span class="error_message" id="error"><html:errors property="appError" /></span> <span class="success_message"> <logic:messagesPresent
							message="true" property="appMessage">
							<html:messages id="msg" message="true" property="appMessage">
								<bean:write name="msg" />
							</html:messages>
						</logic:messagesPresent>
					</span>
					<div class="paddingBottom30">

						<p class="floatl ">
							<strong>Partner<sup class="req">*</sup></strong> <span class="LmsdropdownWithoutJs"> <html:select property="partnerId" styleId="partnerId"
									onchange="getProductById(this.value,'productName', false);getRfsDus(this.value,'rfsDus');">
									<html:option value="0">Please Select</html:option>
									<logic:notEmpty name="gisForm" property="partnerList">
										<html:optionsCollection name="gisForm" property="partnerList" label="partnerName" value="partnerId" />
									</logic:notEmpty>
								</html:select>
							</span>
						</p>
						<p class="floatl marginleft30">
							<strong>Registered Service<sup class="req">*</sup></strong> <span class="showListFunctionalBin showListFunctionalBinHeight60"> <html:select
									property="productArray" styleId="productName" multiple='true'>
									<logic:notEmpty name="gisForm" property="productList">
										<html:optionsCollection name="gisForm" property="productList" label="contentName" value="contentValue" />
									</logic:notEmpty>
								</html:select>
							</span>
						</p>
						<p class="floatl marginleft30">
							<strong>RFS DUs<sup class="req">*</sup></strong>
							<html:text name="gisForm" styleClass="Lmstextbox" property="networkPartnerPojo.rfsDus" styleId="rfsDus" maxlength="10"
								onclick="removeZeroFromNumber(this);" onkeyup="javascript:ValidatenReplaceAlphanumeric(this);" />
						</p>
						<br class="clear" />
					</div>

					<%-- </logic:equal>  --%>
					<div class=" paddingBottom30">
						<h4>Locality / Sector - Society</h4>
						<p class="floatl">
							<strong>Locality / Sector<sup class="req">*</sup></strong>
							<html:text name="gisForm" styleClass="Lmstextbox" property="societyPojo.localityName" styleId="locality" maxlength="50"
								onkeyup="javascript:changeToUpperCase(this)" />
						</p>
						<p class="floatl marginleft30">
							<strong>Name of Society</strong>
							<html:text name="gisForm" styleClass="Lmstextboxuppercase" property="societyPojo.societyName" styleId="nameOfSociety" maxlength="50"
								onkeyup="javascript:changeToUpperCase(this)" />
						</p>
						<p class="floatl marginleft30">
							<strong>Type of Field<sup class="req">*</sup></strong> <span class="LmsdropdownWithoutJs"> <logic:notEmpty name="gisForm" property="fieldTypeList">
									<html:select name="gisForm" property="societyPojo.networkAvailability" styleId="typeOfField">
										<html:optionsCollection name="gisForm" property="fieldTypeList" label="contentName" value="contentValue" />
									</html:select>
								</logic:notEmpty>
							</span>
						</p>
						<p class="clear floatl margintop20">
							<strong>State<sup class="req">*</sup></strong>
							<logic:equal name="gisForm" property="societyPojo.societyId" value="0">
								<span class="LmsdropdownWithoutJs"> <html:select name="gisForm" property="stateId" styleId="state">
										<option value="">Please Select</option>
										<logic:notEmpty name="gisForm" property="statePojoList">
											<html:optionsCollection property="statePojoList" label="stateName" value="stateId" />
										</logic:notEmpty>
									</html:select>
								</span>
							</logic:equal>
							<logic:notEqual name="gisForm" property="societyPojo.societyId" value="0">
								<html:hidden property="state" value="${gisForm.state}" />
								<span class="lmsshowTextbox"> <bean:write name="gisForm" property="state" />
								</span>
							</logic:notEqual>
						</p>
						<p class="floatl marginleft30 margintop20">
							<strong>City<sup class="req">*</sup></strong>
							<logic:equal name="gisForm" property="societyPojo.societyId" value="0">
								<span class="LmsdropdownWithoutJs"> <logic:empty name="gisForm" property="cityList">
										<html:select name="gisForm" property="city" styleId="city">
											<option value="">Please Select</option>
										</html:select>
									</logic:empty> <logic:notEmpty name="gisForm" property="cityList">
										<html:select name="gisForm" property="city" styleId="city">
											<html:option value="">Please Select</html:option>
											<html:optionsCollection property="cityList" label="cityName" value="cityId" />
										</html:select>
									</logic:notEmpty>
								</span>
							</logic:equal>
							<logic:notEqual name="gisForm" property="societyPojo.societyId" value="0">
								<html:hidden property="city" value="${gisForm.city}" />
								<span class="lmsshowTextbox"> <bean:write name="gisForm" property="city" />
								</span>
							</logic:notEqual>
						</p>
						<p class="floatl marginleft30 margintop20">
							<strong>Area<sup class="req">*</sup></strong>
							<logic:equal name="gisForm" property="societyPojo.societyId" value="0">
								<span class="LmsdropdownWithoutJs"> <logic:empty name="gisForm" property="areaList">
										<html:select name="gisForm" property="societyPojo.areaId" styleId="area">
											<option value="">Please Select</option>
										</html:select>
									</logic:empty> <logic:notEmpty name="gisForm" property="areaList">
										<html:select name="gisForm" property="societyPojo.areaId" styleId="area">
											<html:option value="">Please Select</html:option>
											<html:optionsCollection property="areaList" label="area" value="areaId" />
										</html:select>
									</logic:notEmpty>
								</span>
							</logic:equal>
							<logic:notEqual name="gisForm" property="societyPojo.societyId" value="0">
								<html:hidden name="gisForm" property="area" value="${gisForm.area}" />
								<span class="lmsshowTextbox"> <bean:write name="gisForm" property="area" />
								</span>
								<br />
							</logic:notEqual>
						</p>

						<p class="floatl margintop20 clear ">
							<strong>Customer Category<sup class="req">*</sup></strong> <span class="LmsdropdownWithoutJs"> <html:select name="gisForm"
									property="societyPojo.customerCategory" styleId="customerCategory">
									<html:option value="">Please Select</html:option>
									<html:option value="Residential">Residential</html:option>
									<html:option value="Commercial">Commercial</html:option>
									<html:option value="Mixed">Mixed</html:option>
								</html:select>
							</span>
						</p>
						<p class="floatl margintop20 marginleft30">
							<strong>Primary PIN Code<sup class="req">*</sup></strong>
							<html:text styleClass="Lmstextbox" property="societyPojo.primaryPincode" name="gisForm" styleId="primaryPinCode" maxlength="6"
								onclick="removeZeroFromNumber(this);" onfocus="removeZeroFromNumber(this);" onkeyup="javascript:ValidatenReplaceAlphanumeric(this);"></html:text>
							<font></font>
						</p>
						<p class="floatl margintop20 marginleft30">
							<strong>Secondary PIN Code</strong>
							<html:text styleClass="Lmstextbox" property="societyPojo.secPincode" name="gisForm" styleId="secPincode" maxlength="6"
								onclick="removeZeroFromNumber(this);" onfocus="removeZeroFromNumber(this);" onkeyup="javascript:ValidatenReplaceAlphanumeric(this);"></html:text>
							<font></font>
						</p>
						<p class="floatl margintop20 clear">
							<strong>Latitude</strong>
							<html:text name="gisForm" styleClass="Lmstextbox" property="societyPojo.latitude" styleId="latitude" maxlength="10" />
						</p>
						<p class="floatl margintop20 marginleft30">
							<strong>Longitude</strong>
							<html:text name="gisForm" styleClass="Lmstextbox" property="societyPojo.longitude" styleId="longitude" maxlength="10" />
						</p>

						<p class="floatl margintop20 marginleft30">
							<strong>Connectable Homes</strong>
							<html:text name="gisForm" styleClass="Lmstextboxuppercase" property="societyPojo.connectableHomes" styleId="connectableHomes" maxlength="35" />
							<br />
						</p>
						<br class="clear" />
					</div>

					<logic:equal name="gisForm" property="societyPojo.societyId" value="0">
						<html:hidden property="societyPojo.createdBy" value="${userPojo.userId}" />
					</logic:equal>
					<logic:notEqual name="gisForm" property="societyPojo.societyId" value="0">
						<html:hidden name="gisForm" property="societyPojo.lastModifiedBy" value="${userPojo.userId}" />
					</logic:notEqual>
					<html:hidden name="gisForm" property="societyPojo.status" value="${gisForm.societyPojo.status}" />
					<br class="clear" />
					<logic:notEqual name="gisForm" property="societyPojo.societyId" value="0">
						<div style="width: 100%;">
							<div class="roleDisplayTable clear margintop20" style="float: left; margin-right: 20px; width: 430px;">
								<p class="headerDisplayTable" style="margin-top: 0px;">
									<span style="width: auto;">Partner and Product</span>
								</p>
								<html:hidden name="gisForm" property="networkPartnerPojo.id" value="${gisForm.networkPartnerPojo.id}" styleId="societyNPid" />
								<html:hidden name="gisForm" property="networkPartnerPojo.status" value="${gisForm.networkPartnerPojo.status}" styleId="societyNPStatus"/>
								<div style="margin-top: -25px;">
									<display:table id="data" name="sessionScope.gisForm.societyPojo.societyNetworkPartners" requestURI="" class="dataTable" style="width:100%">
										<display:column title="Partner Name" style="width:30%;">
											<logic:notEmpty name="data" property="partnerId">
												<bean:write name="crmRoles" property="displayEnum(NP,${data.partnerId})" scope="session" />
											</logic:notEmpty>
										</display:column>
										<display:column title="Service Name" style="width:20%;">
											<logic:notEmpty name="data" property="productName">
												<bean:write name="crmRoles" property="displayEnum(Product,${data.productName})" scope="session" />
											</logic:notEmpty>
										</display:column>
										<display:column title="Change Status" style="width:20%;">
											<logic:equal name="data" property="status" value="A">
													<a href="javascript:change_SocietyNPStatus(${data.id},'I')">Deactivate</a>
											</logic:equal>
											<logic:equal name="data" property="status" value="I">
													<a href="javascript:change_SocietyNPStatus(${data.id},'A')">Activate</a>
											</logic:equal>
									  </display:column>
									</display:table>
								</div>
							</div>
							<div class="roleDisplayTable margintop20" style="float: right; width: 430px;">
								<p class="headerDisplayTable" style="margin-top: 0px;">
									<span style="width: auto">Partner and RFS DUs</span>
								</p>
								<div style="margin-top: -25px;">
									<logic:notEmpty name="gisForm" property="societyNetworkPartners">
										<display:table id="data" name="sessionScope.gisForm.societyNetworkPartners" requestURI="" class="dataTable" style="width:100%">
											<display:column title="Partner Name" style="width:30%;">
												<bean:write name="crmRoles" property="displayEnum(NP,${data.partnerId})" scope="session" />
											</display:column>
											<display:column title="RFS DUs" property="rfsDus" style="width:20%;" />
										</display:table>
									</logic:notEmpty>
								</div>
							</div>
						</div>
					</logic:notEqual>

				</div>
				<div class="floatr inner_right">
					<logic:equal name="crmRoles" property="available(create_gis_data,update_gis_data)" value="true" scope="session">
						<html:link href="#" styleId="submitGisAddExcel" styleClass="main_button">Submit</html:link>
					</logic:equal>
				</div>
				<br class="clear">
			</div>
		</div>
	</html:form>
</div>
