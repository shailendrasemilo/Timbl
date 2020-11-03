<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<%@taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<!--page inherits from gis.css(the error messages 'font=gis') -->
<div id="section" align="center">
  <html:form action="/alertsConfiguration" styleId="CACform">
    <div class="section">
      <h2>Create Alerts Configuration</h2>
      <span class="error_message"><html:errors property="appError" /></span> <span class="success_message"> <logic:messagesPresent
          message="true" property="appMessage">
          <html:messages id="msg" message="true" property="appMessage">
            <bean:write name="msg" />
          </html:messages>
        </logic:messagesPresent>
      </span>
      <div class="inner_section">
        <div class="inner_left_lead floatl marginleft10">
          <p class="floatl clear">
            <strong> Maximum Retries Allowed<span class="error_message verticalalignTop">*</span></strong>
            <html:text name="masterForm" property="alertMasterPojo.maxRetry" value="0" styleId="CACmax_retry" maxlength="30" styleClass="textbox"
              onkeyup="javascript:isNumeric('CACmax_retry')" />
            <html:errors property="alertMasterPojo.maxRetry" />
          </p>
          <p class="floatl marginleft60 ">
            <strong> Retry Time Interval (In Seconds)<span class="error_message verticalalignTop">*</span></strong>
            <html:text name="masterForm" property="alertMasterPojo.retryTimeInterval" styleId="CACretry_timeInterval" maxlength="30"
              styleClass="textbox" onkeyup="javascript:isNumeric('CACretry_timeInterval')" />
            <html:errors property="alertMasterPojo.retryTimeInterval" />
          </p>
          <p class="floatl clear">
            <strong> TRAI Start (0-23 Hours)<span class="error_message verticalalignTop">*</span></strong>
            <html:text name="masterForm" property="alertMasterPojo.traiStart" styleId="CACtrai_Start" maxlength="2" styleClass="textbox"
              onkeyup="javascript:isNumeric('CACtrai_Start')" />
            <html:errors property="alertMasterPojo.traiStart" />
          </p>
          <p class="floatl marginleft60 ">
            <strong> TRAI End (0-23 Hours)<span class="error_message verticalalignTop">*</span></strong>
            <html:text name="masterForm" property="alertMasterPojo.traiEnd" styleId="CACtrai_End" maxlength="2" styleClass="textbox"
              onkeyup="javascript:isNumeric('CACtrai_End')" />
            <html:errors property="alertMasterPojo.traiEnd" />
          </p>
          <p class="floatl clear ">
            <strong>Inventory Email<span class="error_message verticalalignTop">*</span></strong>
            <html:text name="masterForm" property="alertMasterPojo.inventoryEmail" styleId="CACinventory_email" maxlength="75" styleClass="textbox" />
            <html:errors property="alertMasterPojo.inventoryEmail" />
          </p>
          <p class="floatl marginleft60 ">
            <strong>CMS Status Email<span class="error_message verticalalignTop">*</span></strong>
            <html:text name="masterForm" property="alertMasterPojo.cmsStatusEmail" styleId="CACcms_status_email" maxlength="75" styleClass="textbox" />
            <html:errors property="alertMasterPojo.cmsStatusEmail" />
          </p>
        </div>
        <p class="clear"></p>
        <logic:equal name="crmRoles" property="available(update_alerts_config,create_alerts_config)" value="true" scope="session">
          <div class="floatr inner_right">
            <html:link href="#" styleId="CACsubmit_button" styleClass="main_button">Update</html:link>
          </div>
        </logic:equal>
        <p class="clear"></p>
      </div>
      <p class="clear"></p>
    </div>
  </html:form>
</div>
