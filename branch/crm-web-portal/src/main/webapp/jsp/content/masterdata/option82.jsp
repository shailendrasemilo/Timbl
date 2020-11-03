<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="/WEB-INF/tlds/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/tlds/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/tlds/struts-html.tld" prefix="html"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<script type="text/javascript">
	//show n hide content on radio GIS master
	$(document).ready(function() {
		var selectedDiv = document.getElementsByName("showDivValue")[0].value;
		$('#option82div').addClass('hide1');
		$('#nasportIdDiv').addClass('hide1');
		if (null != selectedDiv) {
			if (selectedDiv == "o") {
				$('#oltTypeDiv').removeClass('hide1');
				$('#option82div').removeClass('hide1');
			}
			if (selectedDiv == "n") {
				$('#nasportIdDiv').removeClass('hide1');
			}
		}
    });
</script>
</head>
<body>
	<!----------------- Section ------------------------------->

<div id="section" align="center">
	<html:form action="/option82Management" styleId="option82Management">
	<html:hidden name="masterForm" property="showDivValue" styleId="IDshowDivValue" value="${masterForm.showDivValue}" />
	<html:hidden name="masterForm" property="rowCounter" styleId="rowIndex_Nasport" />
<div class="loadingPopup hidden"></div>
	<div class="section">
			<%String showValue = (String) request.getAttribute( "showDivvValue" );%>
				<h2>Configure NAS-Port ID</h2>
				<span class="success_message"> 
					<logic:messagesPresent message="true" property="appMessage">
						<html:messages id="msg" message="true" property="appMessage">
							<bean:write name="msg" />
						</html:messages>
					</logic:messagesPresent>
				</span> 
				<span class="error_message"> 
					<html:errors property="appError" />
				</span>
				
		<div class="inner_section">
			<div class="inner_left floatl createUserLeft">
						<p>
							<b>Network Partner:</b> 
							<span class="dropdownWithoutJs"> 
								<html:select name="masterForm" property="partnerId" onchange="getProductById(this.value,'productDetailsList',true);divHider('oltTypeDiv,option82div,nasportIdDiv');" styleId="networkPartnerSelectID" >
									<html:option value="0">Please Select</html:option>
									
									<logic:notEmpty name="masterForm" property="partnerList">
										<html:optionsCollection name="masterForm" property="partnerList" label="partnerName" value="partnerId" />
									</logic:notEmpty>
								</html:select>
							</span>
							</p>
						<p>
							<b>Service Name:</b> 
							<span class="dropdownWithoutJs"> 
								<html:select	name="masterForm" property="businessType" styleId="productDetailsList" onchange="divHider('oltTypeDiv,option82div,nasportIdDiv');">
									<html:option value="0">Please Select</html:option>
										<logic:notEmpty name="masterForm" property="productTypeList">
											<html:optionsCollection name="masterForm" property="productTypeList" label="contentName" value="contentValue" />
										</logic:notEmpty>
								</html:select>
							</span>
						</p>


						<!-- Section of OLT type starts -->
						<div id="oltTypeDiv" class="hide1 marginBottom30">
						<p>
							<b>OLT Type:</b> 
							<span class="dropdownWithoutJs"> 
							<html:select name="masterForm" property="oltType" styleId="oltTypeList">
								<html:option value="0">Please Select</html:option>
									<logic:notEmpty name="masterForm" property="oltTypeList">
										<html:optionsCollection name="masterForm" property="oltTypeList" label="contentName" value="contentValue" />
									</logic:notEmpty>
							</html:select>
							</span>
						</p>
						</div>

	<div id="option82div" class="hide1 marginBottom30">
		<logic:equal name="masterForm" property="businessType" value="BB">
		<div id="role_group_view1" class="relative margintop20 floatl">
			<div class="display_group">
			<div class="stays-at-top">
				<span class="heading_text"> Parameter List</span>
			</div>
			<ul class="add_option82">
	<logic:notEmpty name="masterForm" property="crmPartnerNetworkConfigPojos">
		<%int i = 0;%>
		<logic:iterate id="crmPartnerNetworkConfigPojos" name="masterForm" property="crmPartnerNetworkConfigPojos" indexId="ctr">
		<logic:empty name="crmPartnerNetworkConfigPojos" property="status">
		<% i++;%>
		<% if ( i % 2 == 1 )
		 {%>
			<li class="first">
		<%}
		 else
		{%>
			<li class="second">
		<% }%> 
		<span class="floatl margintop10">Parameter<%=i%></span>
			<html:hidden name="crmPartnerNetworkConfigPojos" property="recordId" indexed="true"/> 
			<html:hidden name="crmPartnerNetworkConfigPojos" property="createdBy" indexed="true"/>			
			<html:hidden name="crmPartnerNetworkConfigPojos" property="parameterSequenceNo" indexed="true" />
			<html:hidden name="crmPartnerNetworkConfigPojos" property="oltType" indexed="true" />
			<span class="dropdownWithoutJs marginleft30"> 
				<html:select name="crmPartnerNetworkConfigPojos" property="parameterId" indexed="true">
					<html:option value="0">Please Select</html:option>
					<html:optionsCollection name="masterForm" property="crmParameterList" label="parameterName" value="parameterId" />
				</html:select>
				
			</span>
			</li>
			</logic:empty>
		</logic:iterate>
	</logic:notEmpty>
	
		</ul>
		</div>
	</div>
	</logic:equal>
	<logic:equal name="crmRoles" property="available(create_option82,update_option82)" value="true" scope="session">
		<div class="floatr inner_right">
			<a href="#" onclick="option82Operation('olt');" class="main_button">Submit</a>
		</div>
	</logic:equal>
	</div>
	<!--  Section of OLT type ends -->
	
	
	<!--  NassPort Id starts -->
	<p>
	<div id="nasportIdDiv" class="hide1 marginright70 floatl">
	<logic:notEqual name="masterForm" property="businessType" value="BB">
	<div id="role_group_view">
		<span class="addbtn_role_group">
			<a href="javascript:void(0)" id="addParameterOption82" class="grey_button_add">
				<span>Add</span>
			</a>
		</span>
		<div class="display_group width950">
			<div class="stays-at-top">
				<span class="heading_text">NAS-Port ID</span> 
				<span class="heading_text marginleft161">Master</span> 
				<span class="heading_text marginleft194">Pool Name</span>
			</div>
					
<ul class="add_parameter_group">
<logic:notEmpty name="masterForm" property="crmPartnerNetworkConfigPojos">
	<logic:iterate id="crmPartnerNetworkConfigPojos" name="masterForm" property="crmPartnerNetworkConfigPojos" indexId="ctr">
		<logic:notEmpty name="crmPartnerNetworkConfigPojos" property="status">
		<%!int i = 1;%>
		<%if ( i % 2 == 0 )
			{%>
			<li class="first">
			<%}
			else
			{%>
			<li class="second">
		<%}%> 
		<logic:equal name="crmPartnerNetworkConfigPojos" property="recordId" value="0">
			<html:text name="crmPartnerNetworkConfigPojos" styleId="nasPortId" property="nasPortId"  maxlength="30" styleClass="textbox" indexed="true"></html:text>
			<html:errors property="nasPortId" />
			<span class="marginleft13 inline"> 
				<html:text name="crmPartnerNetworkConfigPojos" styleId="masterName" property="masterName"  maxlength="50" styleClass="textbox" indexed="true"></html:text>
				<html:errors property="masterName" />
			</span>
			<span class="marginleft13 inline"> 
				<html:text name="crmPartnerNetworkConfigPojos" styleId="poolName" property="poolName"   maxlength="50" styleClass="textbox" indexed="true"> </html:text>
				<html:errors property="poolName" />
			</span>
			<span class="marginleft13 inline"> 
				<html:link href="javascript:deleteNasportRow(${ctr})" styleClass="close">
					<img src="images/bg/delete.png" align="absmiddle"title="delete" />
				</html:link>
			</span>
		</logic:equal> 
		<logic:notEqual name="crmPartnerNetworkConfigPojos"	property="recordId" value="0">
			<html:hidden name="crmPartnerNetworkConfigPojos" property="recordId" indexed="true" />
			<html:hidden name="crmPartnerNetworkConfigPojos" property="partnerDetailsId" indexed="true" />
			<html:hidden name="crmPartnerNetworkConfigPojos" property="createdBy" indexed="true" />
			<html:hidden name="crmPartnerNetworkConfigPojos" property="lastModifiedBy" indexed="true" />

			<html:text name="crmPartnerNetworkConfigPojos" property="nasPortId" styleClass="textbox" indexed="true"></html:text>
			<span class="marginleft13 inline"> 
				<html:text name="crmPartnerNetworkConfigPojos" property="masterName" styleClass="textbox" indexed="true"></html:text>
			</span>
			<span class="marginleft13 inline"> 
				<html:text name="crmPartnerNetworkConfigPojos" property="poolName"styleClass="textbox" indexed="true"></html:text>
			</span>
			<span class="padding"> 
				<html:radio	name="crmPartnerNetworkConfigPojos" property="status" value="A" indexed="true">Active</html:radio> 
				<html:radio name="crmPartnerNetworkConfigPojos" property="status" value="I" indexed="true">Inactive</html:radio>
			</span>
		</logic:notEqual>
		
		</li>
		<%i++;%>
		</logic:notEmpty>
	</logic:iterate>
	</logic:notEmpty>
	<logic:empty name="masterForm" property="crmPartnerNetworkConfigPojos">
		<span class="error_message"><br /> &nbsp;Please click on 'Add' button to Add NAS-Port ID. </span>
	</logic:empty>
</ul>

</div>

</div>
</logic:notEqual>
<logic:equal name="crmRoles" property="available(create_option82,update_option82)" value="true" scope="session">
		<div class="floatr inner_right">
			<a href="#" onclick="option82Operation('nasport');" class="main_button">Submit</a>
		</div>
</logic:equal>
</div>
	
</p>

	<!-- Nassport Id ends -->
		</div>
				<p class="clear"></p>
		</div>
		<p class="clear"></p>
	</div>
	</html:form>
</div>