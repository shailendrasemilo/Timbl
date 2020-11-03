<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<%@taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<link href="css/displaytag.css" rel="stylesheet" type="text/css" />
<div class="loadingPopup hidden"></div>
<div id="section">
	<html:form action="/gis" method="post" styleId="searchSociety">
		<html:hidden property="gisOptions" styleId="gisHidden" value="search" />
		<div class="section">
			<h2>Search Locality / Sector - Society</h2>
			<span class="success_message"> <logic:messagesPresent message="true" property="appMessage">
					<html:messages id="message" message="true" property="appMessage">
						<bean:write name="message" />
					</html:messages>
				</logic:messagesPresent>
			</span>
			<div class="inner_section ">
				<div class="inner_left_lead marginleft10 floatl ">
					<div id="display_societyData">
						<div class="floatl">
							<p class="floatl clear ">
								<strong>State</strong> <span class="dropdownWithoutJs"> <logic:notEmpty name="gisForm" property="statePojoList">
										<html:select name="gisForm" property="stateId" styleId="state">
											<html:option value="">All States</html:option>
											<html:optionsCollection property="statePojoList" label="stateName" value="stateId" />
										</html:select>
									</logic:notEmpty> <logic:empty name="gisForm" property="statePojoList">
										<html:select name="gisForm" property="stateId" styleId="state">
										</html:select>
									</logic:empty>
								</span>
							</p>
							<p class="floatl marginleft60">
								<strong>City</strong> <span class="dropdownWithoutJs"> <logic:notEmpty name="gisForm" property="cityList">
										<html:select name="gisForm" property="cityId" styleId="city">
											<html:option value="">All Cities</html:option>
											<html:optionsCollection property="cityList" label="cityName" value="cityId" />
										</html:select>
									</logic:notEmpty> <logic:empty name="gisForm" property="cityList">

										<html:select name="gisForm" property="cityId" styleId="city">
											<html:option value="">All Cities</html:option>
										</html:select>
									</logic:empty>
								</span>
							</p>

							<p class="floatl clear">
								<strong>Area</strong> <span class="dropdownWithoutJs"> <logic:notEmpty name="gisForm" property="areaList">
										<html:select name="gisForm" property="areaId" styleId="area">
											<html:option value="">All Areas</html:option>
											<html:optionsCollection property="areaList" label="area" value="areaId" />
										</html:select>
									</logic:notEmpty> <logic:empty name="gisForm" property="areaList">

										<html:select name="gisForm" property="areaId" styleId="area">
											<html:option value="">All Areas</html:option>
										</html:select>
									</logic:empty>
								</span>
							</p>
							<p class="floatl marginleft60">
								<strong>Partner</strong> <span class="dropdownWithoutJs"> <html:select name="gisForm" property="partnerId" styleId="partnerId">
										<html:option value="0">All Partners</html:option>
										<logic:notEmpty name="gisForm" property="partnerPojoList">
											<html:optionsCollection name="gisForm" property="partnerPojoList" label="partnerName" value="partnerId" />
										</logic:notEmpty>
									</html:select>
								</span>
							</p>

							<p class="floatl clear">
								<strong>Status</strong> <span class="dropdownWithoutJs" style="width: 220px;"> <html:select name="gisForm" property="societyPojo.status"
										styleId="status">
										<html:optionsCollection name="gisForm" property="statusList" label="contentName" value="contentValue" />
									</html:select>
								</span>
							</p>
							<p class="floatl marginleft60">
								<strong>Service</strong> <span class="dropdownWithoutJs"> <html:select property="product" styleId="productName">
										<html:option value="">Please Select</html:option>
										<logic:notEmpty name="gisForm" property="productList">
											<html:optionsCollection name="gisForm" property="productList" label="contentName" value="contentValue" />
										</logic:notEmpty>
									</html:select>
								</span>
							</p>
							<p class="floatl clear">
								<strong>Locality / Sector - Society</strong>
								<html:text styleClass="textbox" name="gisForm" property="societyPojo.societyName" styleId="nameOfSociety" value="${gisForm.societyPojo.societyName}"
									maxlength="28" />
							<p>
						</div>
						<p class="clear"></p>
					</div>
				</div>
				<div class="floatr inner_right">
					<a href="#" id="submit_searchGIS" class="main_button"><span>Search</span></a>
				</div>
				<p class="clear"></p>
			</div>
			<html:hidden name="gisForm" property="societyId" styleId="societyId_searchSociety" />
			<html:hidden name="gisForm" property="status" styleId="newStatus" />
			<logic:notEmpty name="gisForm" property="searchSocietyList">
				<display:table id="data" name="sessionScope.gisForm.searchSocietyList" requestURI="gis.do?method=searchSociety" class="dataTable" style="width:100%"
					pagesize="15">
					<display:setProperty name="paging.banner.placement" value="bottom" />
					<display:setProperty name="export.excel.filename" value="Societies-Details.xls" />
					<display:setProperty name="export.csv.filename" value="Societies-Details.csv" />
					<display:column title="S.No.">
						<bean:write name="data_rowNum" />
					</display:column>
					<display:column title="Locality / Sector - Society" property="searchName"/>
					<display:column title="Status">
						<logic:notEmpty name="data" property="status">
							<bean:write name="crmRoles" property="displayEnum(PartialStatus,${data.status})" scope="session" />
						</logic:notEmpty>
					</display:column>
					<logic:equal name="crmRoles" property="available(view_gis_data)" value="true" scope="session">
						<display:column title="View" media="html">
							<a href="javascript:viewSociety_searchSociety('<bean:write name="data" property="societyId" />')">View</a>
						</display:column>
					</logic:equal>
					<logic:equal name="crmRoles" property="available(update_gis_data)" value="true" scope="session">
						<display:column title="Edit" media="html">
							<logic:equal name="data" property="editable" value="true">
								<logic:equal name="data" property="status" value="A">
									<a href="javascript:editSociety_searchSociety('<bean:write name="data" property="societyId" />')">Edit</a>
								</logic:equal>
							</logic:equal>
							<logic:equal name="data" property="editable" value="false">
								<logic:equal name="data" property="status" value="A">	
							  -
							</logic:equal>
							</logic:equal>
							<logic:notEqual name="data" property="status" value="A">-
							</logic:notEqual>
						</display:column>
					</logic:equal>
					<logic:equal name="crmRoles" property="available(create_gis_data)" value="true" scope="session">
						<display:column title="Copy" media="html">
							<logic:equal name="data" property="status" value="A">
								<a href="javascript:copySociety_searchSociety('<bean:write name="data" property="societyId" />')">Copy</a>
							</logic:equal>
							<logic:notEqual name="data" property="status" value="A">-
						</logic:notEqual>
						</display:column>
					</logic:equal>
					<logic:equal name="crmRoles" property="available(update_gis_data,delete_gis_data,recover_gis_data)" value="true" scope="session">
						<display:column title="Change Status" media="html">
							<logic:equal name="data" property="status" value="A">
								<logic:equal name="crmRoles" property="available(update_gis_data)" value="true" scope="session">
			 					[<a href="javascript:changeSocietyStatus('<bean:write name="data" property="societyId" />','I')">Deactivate</a>]
			 	 			</logic:equal>
								<logic:equal name="crmRoles" property="available(delete_gis_data)" value="true" scope="session">
			 	  				[<a href="javascript:changeSocietyStatus('<bean:write name="data" property="societyId" />','D')">Delete</a>]
			 				</logic:equal>
							</logic:equal>
							<logic:equal name="data" property="status" value="I">
								<logic:equal name="crmRoles" property="available(update_gis_data)" value="true" scope="session">
						 	 	[<a href="javascript:changeSocietyStatus('<bean:write name="data" property="societyId" />','A')">Activate</a>]
						 	 </logic:equal>
								<logic:equal name="crmRoles" property="available(delete_gis_data)" value="true" scope="session">
						 	  	[<a href="javascript:changeSocietyStatus('<bean:write name="data" property="societyId" />','D')">Delete</a>]
						 	 </logic:equal>
							</logic:equal>
							<logic:equal name="data" property="status" value="D">
						 		Deleted
						 	<logic:equal name="crmRoles" property="available(recover_gis_data)" value="true" scope="session">
						 	[ <a href="javascript:changeSocietyStatus('<bean:write name="data" property="societyId" />','A')">Recover</a>]
						 	</logic:equal>
							</logic:equal>
						</display:column>
					</logic:equal>
				</display:table>
			</logic:notEmpty>
			<logic:empty name="gisForm" property="searchSocietyList">
				<b> <html:errors property="noRecordFound" />
				</b>
			</logic:empty>
		</div>
	</html:form>
</div>


