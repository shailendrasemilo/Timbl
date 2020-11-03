<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"  pageEncoding="ISO-8859-1"%>
<%@taglib uri="/WEB-INF/tlds/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/tlds/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/tlds/displaytag.tld" prefix="display"%>
<%@taglib uri="/WEB-INF/tlds/struts-html.tld" prefix="html"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
	<script src="dwr/engine.js" type="text/javascript"></script>
	<script src="dwr/util.js" type="text/javascript"></script>
	<script src="dwr/interface/crmDwr.js" type="text/javascript"></script> 
	<link href="css/common.css" rel="stylesheet" media="screen" />
	<link href="css/color-${initParam.client }.css" rel="stylesheet" media="screen" />
	<link href="css/userManagement.css" rel="stylesheet" media="screen" />
	<link href="css/ina.css" rel="stylesheet" media="screen" />
	<link href="css/lms.css" rel="stylesheet" media="screen" />
	<link href="css/masterdata.css" rel="stylesheet" media="screen" />
	<script src="javascript/jquery.min.js" type="text/javascript"></script>
	<script src="javascript/jquery.validate.js" type="text/javascript"></script>
	<script src="javascript/npCrf.js" type="text/javascript"></script>
	<script src="javascript/common.js" type="text/javascript"></script>
	<script src="javascript/qrc.js" type="text/javascript"></script>
</head>
<body>
<div id="popupSection" >
		<html:form action="/crmCap" styleId="IDQRCnetworkInventoryDetail" method="post">
		<html:hidden property="partnerId" name="qrcForm" value="${qrcForm.partnerId}" styleId="IDQRChiddenPartnerId"/>
		<html:hidden property="crmPartnerNetworkConfig.partnerDetailsId" name="qrcForm" value="${qrcForm.crmPartnerNetworkConfig.partnerDetailsId}" styleId="ID_partnerDetailsId"/>
		<div id=''><span id='DIVQRCoption82Note'></span></div>
		<div class="popupSection">
		<h2>Network Inventory Detail</h2>
		<div class="inner_section">
		<div class="inner_left_lead floatl">
		<div class="floatl">
		<h4>Actions</h4>
		
		<p class="serviceModel"><strong>Service Model</strong>
		<label class="label_radio" for="actionFttnModel">
			<html:radio name="qrcForm" property ="networkConfigurationsPojo.serviceModel" styleId="actionFttnModel" value="fttn" >FTTN Model</html:radio>
		</label>
		<font></font>
		</p>
		</div>
		<c:if test="${(qrcForm.custDetailsPojo.product eq 'EOC') or (qrcForm.custDetailsPojo.product eq 'RF') }">
		<logic:notEmpty name="qrcForm" property="masterNameList">
		<p class="floatl clear">
		<strong>Master Name</strong>  
			<span class="LmsdropdownWithoutJs">
			<html:select property="crmPartnerNetworkConfig.masterName" name="qrcForm" styleId="IDQRCmasterNameId" onchange="javascript:populateNasportIdByMasterName('IDQRCnasportId',this.value);">
			<html:option value="">Please Select</html:option>
			<logic:notEmpty name="qrcForm" property="masterNames">
			<html:options name="qrcForm" property="masterNames" />
			</logic:notEmpty>
			</html:select>
			</span> <font></font>
		</p>
		
		 <p class="floatl marginleft15"><strong> NAS Port ID</strong>
			<span class="LmsdropdownWithoutJs">
			<html:select name="qrcForm" property="crmPartnerNetworkConfig.nasPortId" styleId="IDQRCnasportId" onchange="javascript:populatePoolNameByNasportId('IDQRCpoolNameId','IDQRCmasterNameId',this.value);">
			<html:option value="0">Please Select</html:option>
			</html:select>
			</span><font></font>
		</p>
		
		<p class="floatl marginleft15"><strong>Pool Name</strong>
			<span class="LmsdropdownWithoutJs">
			<html:select name="qrcForm" property="networkConfigurationsPojo.option82" styleId="IDQRCpoolNameId">
			<html:option value="0">Please Select</html:option>
			</html:select>
			</span><font></font>
		</p>
		</logic:notEmpty>
		
		<logic:empty name="qrcForm" property="masterNameList">
			<strong class="headStrong">Network Configurations not mapped with partner ID. </strong>
		</logic:empty>
		</c:if>
		</div>
		<div class="floatr inner_right">
			<logic:equal name="crmRoles" property="available(create_ina,update_ina,create_qrc_dm,update_qrc_dm)" value="true" scope="session">
			<a href="javascript:submitQRCNetworkInventory();" id="IDQRCsubmit_networkInventoryDetail" class="main_button"><span>Submit</span></a>
		</logic:equal>
		</div>
		<br class="clear"/>
		</div>
	 </div>
	</html:form>
</div>
</body>
</html>