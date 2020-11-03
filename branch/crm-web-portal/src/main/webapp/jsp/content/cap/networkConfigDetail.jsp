<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib uri="/WEB-INF/tlds/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/tlds/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/tlds/displaytag.tld" prefix="display"%>
<%@taglib uri="/WEB-INF/tlds/struts-html.tld" prefix="html"%>
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
</head>
<body>
  <div id="popupSection">
    <html:form action="/crmCap" styleId="networkInventoryDetail" method="post">
      <html:hidden property="crmPartnerNetworkConfig.partnerDetailsId" name="crmCapForm" value="${crmCapForm.crmPartnerNetworkConfig.partnerDetailsId}"
        styleId="ID_partnerDetailsId" />
      <div id=''>
        <span id='DIVoption82Note'></span>
      </div>
      <div class="popupSection">
        <h2>Network Inventory Detail</h2>
        <div class="inner_section">
          <div class="inner_left_lead floatl">
            <div class="floatl">
              <h4>Actions</h4>
              <p class="serviceModel">
                <strong>Service Model</strong> 
                <label class="label_radio" for="actionFtthModel"> <html:radio name="crmCapForm"
                    property="crmNetworkConfigurations.serviceModel" styleId="actionFtthModel" value="fttn" >FTTN Model</html:radio>
                </label> 
                <font class="hide1">Please select Service Model</font>
              </p>
            </div>
            <logic:notEqual value="BB" name="crmCapForm" property="customerDetailsPojo.product">
              <logic:notEmpty name="crmCapForm" property="masterNameList">
                <p class="floatl clear">
                  <strong>Master Name</strong> <span class="LmsdropdownWithoutJs"> <html:select property="crmPartnerNetworkConfig.masterName"
                      name="crmCapForm" styleId="masterNameId" onchange="javascript:populateNasportIdByMasterName('nasportId',this.value);">
                      <html:option value="">Please Select</html:option>
                      <logic:notEmpty name="crmCapForm" property="masterNames">
                        <html:options name="crmCapForm" property="masterNames" />
                      </logic:notEmpty>
                    </html:select>
                  </span> <font class='hide1'>Please Select Master Name</font>
                </p>
                <p class="floatl marginleft15">
                  <strong>NAS Port ID</strong> <span class="LmsdropdownWithoutJs"> <html:select name="crmCapForm"
                      property="crmPartnerNetworkConfig.nasPortId" styleId="nasportId"
                      onchange="javascript:populatePoolNameByNasportId('poolNameId','masterNameId',this.value);">
                      <html:option value="">Please Select</html:option>
                    </html:select>
                  </span><font class='hide1'>Please Select NAS Port ID</font>
                </p>
                <p class="floatl marginleft15">
                  <strong>Pool Name</strong> <span class="LmsdropdownWithoutJs"> <html:select name="crmCapForm"
                      property="crmNetworkConfigurations.option82" styleId="poolNameId">
                      <html:option value="0">Please Select</html:option>
                    </html:select>
                  </span>
                </p>
              </logic:notEmpty>
			  <p class="floatl clear ">
              <logic:empty name="crmCapForm" property="masterNameList">
			  
                <span class="error_message clear">Network configurations are not mapped with Partner ID. Please configure.</span>
				
              </logic:empty>
			  </p>
            </logic:notEqual>
           <%-- <logic:equal value="BB" name="crmCapForm" property="customerDetailsPojo.product">
              <div class="floatl marginleft30">
                <h4>Service Related Information</h4>
                <p>
                  <strong>Service Type</strong> <span class="LmsdropdownWithoutJs"> <html:select name="crmCapForm"
                      property="crmNetworkConfigurations.serviceType" styleId="serviceTypeId">
                      <option value="0">Select Service Type</option>
                      <option value="ServiceType1">ServiceType1</option>
                      <option value="ServiceType2">ServiceType2</option>
                    </html:select>
                  </span>
                </p>
              </div>
              <div id="onFtth" class="hide1 floatl marginleft30">
                <h4>ONT Details</h4>
                <p>
                  <strong>ONT Port</strong> <span class="LmsdropdownWithoutJs"> <html:select name="crmCapForm"
                      property="crmNetworkConfigurations.ontOnuPort" styleId="ontType">
                      <option value="0">Select ONT Port</option>
                      <option value="ONT Port1">ONT Port1</option>
                      <option value="ONT Port2">ONT Port2</option>
                    </html:select>
                  </span><font></font>
                </p>
              </div>
              <div id="onFttb" class="hide1 floatl marginleft30">
                <h4>ONU Details</h4>
                <p>
                  <strong> ONU Port</strong> <span class="LmsdropdownWithoutJs"> <html:select name="crmCapForm"
                      property="crmNetworkConfigurations.ontOnuPort" styleId="ontType">
                      <option value="0">Select ONU Port</option>
                      <option value="ONU Port 1">ONU Port 1</option>
                      <option value="ONU Port 2">ONU Port 2</option>
                    </html:select>
                  </span><font></font>
                </p>
              </div>
              <div class="floatl marginleft30">
                <h4>VLAN Details</h4>
                <p class="">
                  <strong>VLAN ID</strong>
                  <html:text styleClass="Lmstextbox" property="crmNetworkConfigurations.vlanId" name="crmCapForm" maxlength="10" styleId="vlanId"></html:text>
                  <font></font>
                </p>
              </div>
              <h4 class="paddingTop10 clear">OLT Details</h4>
              <p class="floatl">
                <strong>OLT Type</strong> <span class="LmsdropdownWithoutJs"> <html:select name="crmCapForm" property="oltType" styleId="oltTypeList"
                    onchange="javascript:populateNetworkDetailsByPartner(this.value);">
                    <html:option value="0">Please Select</html:option>
                    <logic:notEmpty name="crmCapForm" property="oltTypeList">
                      <html:optionsCollection name="crmCapForm" property="oltTypeList" label="contentName" value="contentValue" />
                    </logic:notEmpty>
                  </html:select>
                </span>
              </p>
              <p class="floatl marginleft30">
                <strong>OLT Node ID</strong>
                <html:text name="crmCapForm" styleClass="Lmstextbox" property="crmNetworkConfigurations.oltNoteId" maxlength="10" styleId="oltNodeId"></html:text>
                <font></font>
              </p>
              <p class=" floatl marginleft30">
                <strong>OLT Slot</strong>
                <html:text name="crmCapForm" styleClass="Lmstextbox" property="crmNetworkConfigurations.oltSlot" maxlength="2" styleId="oltSlotId"></html:text>
                <font></font>
              </p>
              <p class="floatl marginleft30">
                <strong>OLT Port</strong>
                <html:text name="crmCapForm" styleClass="Lmstextbox" property="crmNetworkConfigurations.oltPort" maxlength="2" styleId="oltPortId"></html:text>
                <font></font>
              </p>
              <p class=" floatl marginleft30">
                <strong>OLT Sub Port</strong>
                <html:text name="crmCapForm" styleClass="Lmstextbox" property="crmNetworkConfigurations.oltSubPort" maxlength="2" styleId="oltSubPortId"></html:text>
              </p>
              <div id='testDiv'>
                <input type='text' id='testField' />
              </div>
            </logic:equal>
            --%> 
          </div>
          <div class="floatr inner_right">
            <logic:equal name="crmRoles" property="available(create_ina,update_ina,update_qrc_shifting)" value="true" scope="session">
              <a href="#" id="submit_networkInventoryDetail" class="main_button"><span>Submit</span></a>
            </logic:equal>
          </div>
          <br class="clear" />
        </div>
      </div>
    </html:form>
  </div>
</body>
</html>
