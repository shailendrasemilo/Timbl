<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<%@taglib uri="http://struts.apache.org/tags-html" prefix="html"%>

<div id="section" align="center">
  <html:form action="/smsManagement" styleId="smsform" styleClass="createUser">
    <div class="section">
      <logic:empty name="masterForm" property="smsGatewayPojo.alias">
        <h2>Create SMS Gateway</h2>
      </logic:empty>
      <logic:notEmpty name="masterForm" property="smsGatewayPojo.alias">
        <h2>Update SMS Gateway</h2>
      </logic:notEmpty>
      <span class="error_message"><html:errors property="appError" /></span> <span class="success_message"> <logic:messagesPresent
          message="true" property="appMessage">
          <html:messages id="msg" message="true" property="appMessage">
            <bean:write name="msg" />
          </html:messages>
        </logic:messagesPresent>
      </span>
      <div class="inner_section">
        <html:hidden property="smsGatewayPojo.category" value="${masterForm.smsGatewayPojo.category}" />
        <div class="inner_left_lead marginleft10 floatl">
          <p class="floatl clear ">
            <strong> Gateway Name <sup class="req">*</sup></strong>
            <logic:empty name="masterForm" property="smsGatewayPojo.alias">
              <html:text name="masterForm" property="smsGatewayPojo.subCategory" styleId="smsSubCategory" maxlength="50" styleClass="textbox" />
            </logic:empty>
            <logic:notEmpty name="masterForm" property="smsGatewayPojo.alias">
              <span class="showTextbox"> <bean:write name="masterForm" property="smsGatewayPojo.subCategory" />
              </span>
            </logic:notEmpty>
            <html:errors property="smsGatewayPojo.subCategory" />
          </p>
          <p class="floatl marginleft60 ">
            <strong> Gateway URL <sup class="req">*</sup></strong>
            <html:text name="masterForm" property="smsGatewayPojo.url" styleId="smsUrl" styleClass="textbox" maxlength="1024" />
          </p>
          <p class="floatl marginleft60 ">
            <strong> Gateway Provider<sup class="req">*</sup></strong>
            <span class="dropdownWithoutJs"> <html:select name="masterForm" property="smsGatewayPojo.gatewayProvider">
              <html:option value="vfirst">ValueFirst</html:option>
            </html:select>
            </span>
          </p>
          <p class="floatl clear ">
            <strong> Gateway User <sup class="req">*</sup></strong>
            <html:text name="masterForm" property="smsGatewayPojo.userid" styleId="smsUserId" maxlength="128" styleClass="textbox" />
            <html:errors property="smsGatewayPojo.userid" />
          </p>
          <p class="floatl marginleft60 ">
            <strong> Gateway Password <sup class="req">*</sup></strong>
            <html:text name="masterForm" property="smsGatewayPojo.password" styleId="smsPassword" maxlength="30" styleClass="textbox" />
            <html:errors property="smsGatewayPojo.password;" />
          </p>
          <p class="floatl clear ">
            <strong> Connection Time Out (In milliseconds) <sup class="req">*</sup></strong>
            <html:text name="masterForm" property="smsGatewayPojo.connectionTimeOut" styleId="smsConnectionTimeOut" maxlength="5" styleClass="textbox"  onclick="removeZeroFromNumber(this);" onfocus="removeZeroFromNumber(this);" onkeyup="javascript:ValidatenReplaceAlphanumeric(this);"/>
            <html:errors property="smsGatewayPojo.connectionTimeOut" />
          </p>
          <p class="floatl marginleft60 ">
            <strong> Socket Time Out (In milliseconds) <sup class="req">*</sup></strong>
            <html:text property="smsGatewayPojo.socketTimeOut" styleId="smsSocketTimeOut" maxlength="5" styleClass="textbox"  onclick="removeZeroFromNumber(this);" onfocus="removeZeroFromNumber(this);" onkeyup="javascript:ValidatenReplaceAlphanumeric(this);" />
            <html:errors property="smsGatewayPojo.socketTimeOut" />
          </p>
          <!-- <p class="floatl clear">
            <strong> Retry Count <sup class="req">*</sup></strong>
            <html:text name="masterForm" property="smsGatewayPojo.retryValue" styleId="smsRetryValue" maxlength="3" styleClass="textbox" />
            <html:errors property="smsGatewayPojo.retryValue" />
          </p> -->
          <p class="floatl clear ">
            <strong> Response Starts with <sup class="req">*</sup></strong>
            <html:text property="smsGatewayPojo.response" styleId="smsResponse" maxlength="50" styleClass="textbox" />
            <html:errors property="smsGatewayPojo.response" />
          </p>
          <p class="floatl marginleft60 ">
            <strong> From <!-- <sup class="req">*</sup> --></strong>
            <html:text property="smsGatewayPojo.from" styleId="smsFrom" maxlength="50" styleClass="textbox" />
          </p>
          <p class="floatl clear">
            <html:checkbox name="masterForm" property="smsGatewayPojo.enable" value='true'>Gateway Enabled</html:checkbox>
            <html:errors property="enable" />
          </p>
        </div>
        <div class="floatr inner_right ">
          <html:link href="#" styleId="SMScreateButton" styleClass="main_button">
            <logic:empty name="masterForm" property="smsGatewayPojo.alias">Create</logic:empty>
            <logic:notEmpty name="masterForm" property="smsGatewayPojo.alias">Update</logic:notEmpty>
          </html:link>
        </div>
        <p class="clear"></p>
      </div>
      <p class="clear"></p>
    </div>
  </html:form>
</div>
