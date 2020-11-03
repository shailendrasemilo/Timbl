<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<%@taglib uri="http://struts.apache.org/tags-html" prefix="html"%>

<!--page inherits from gis.css(the error messages 'font=gis') -->
<div id="section" align="center">
  <html:form action="/emailServer" styleId="ESform">
    <html:hidden name="masterForm" property="emailServerPojo.category" value="${masterForm.emailServerPojo.category}" />
    <div class="section">
      <logic:empty name="masterForm" property="emailServerPojo.alias">
        <h2>Create Email Server</h2>
      </logic:empty>
      <logic:notEmpty name="masterForm" property="emailServerPojo.alias">
        <h2>Edit Email Server</h2>
      </logic:notEmpty>
      <span class="error_message"><html:errors property="appError" /></span> <span class="success_message"> <logic:messagesPresent
          message="true" property="appMessage">
          <html:messages id="msg" message="true" property="appMessage">
            <bean:write name="msg" />
          </html:messages>
        </logic:messagesPresent>
      </span>

      <div class="inner_section">
        <div class="inner_left floatl createUserLeft">
          <p class="floatl clear ">
            <strong> Email Server Name <sup class="req">*</sup></strong>
            <logic:empty name="masterForm" property="emailServerPojo.alias">
              <html:text property="emailServerPojo.subCategory" styleId="ESsubCategory" maxlength="50" styleClass="textbox" />
              <html:errors property="emailServerPojo.subCategory" />
            </logic:empty>
            <logic:notEmpty name="masterForm" property="emailServerPojo.alias">
              <span class="showTextbox"> <bean:write name="masterForm" property="emailServerPojo.subCategory" />
              </span>
            </logic:notEmpty>
          </p>
          <p class="floatl clear ">
            <strong> SMTP Host <sup class="req">*</sup></strong>
            <html:text name="masterForm" property="emailServerPojo.host" styleId="EShost" maxlength="1024" styleClass="textbox" />
            <html:errors property="emailServerPojo.host" />
          </p>
          <p class="floatl marginleft60 ">
            <strong> SMTP Port <sup class="req">*</sup></strong>
            <html:text name="masterForm" property="emailServerPojo.port" styleId="ESport" maxlength="30" styleClass="textbox"  onkeyup="javascript:ValidatenReplaceAlphanumeric(this);" onclick="removeZeroFromNumber(this)" onfocus="removeZeroFromNumber(this);"/>
            <html:errors property="emailServerPojo.port" />
          </p>
          <p class="floatl clear ">
            <strong> SMTP User <sup class="req">*</sup></strong>
            <html:text name="masterForm" property="emailServerPojo.userid" styleId="ESuserid" maxlength="128" styleClass="textbox" />
            <html:errors property="emailServerPojo.userid" />
          </p>
          <p class="floatl marginleft60 ">
            <strong> SMTP Password <sup class="req">*</sup></strong>
            <html:text name="masterForm" property="emailServerPojo.password" styleId="ESpassword" maxlength="30" styleClass="textbox" />
            <html:errors property="emailServerPojo.password" />
          </p>
          <p class="floatl clear ">
            <strong> From Email ID <sup class="req">*</sup></strong>
            <html:text name="masterForm" property="emailServerPojo.from" styleId="ESfrom" maxlength="128" styleClass="textbox" />
            <html:errors property="emailServerPojo.from" />
          </p>
          <p class="floatl marginleft60 ">
            <strong> Reply To Email ID</strong>
            <html:text name="masterForm" property="emailServerPojo.replyTo" styleId="ESreplyTo" maxlength="128" styleClass="textbox" />
            <html:errors property="emailServerPojo.replyTo" />
          </p>
          <p class="floatl clear">
            <strong>From Display Name <sup class="req">*</sup></strong>
            <html:text name="masterForm" property="emailServerPojo.display" styleId="ESdisplay" maxlength="128" styleClass="textbox" />
            <html:errors property="emailServerPojo.socketTimeOut" />
          </p>
		  <p class="floatl marginleft60 ">
            <strong style="width: 100%;"> Socket Time Out (In milliseconds) <sup class="req">*</sup></strong>
            <html:text name="masterForm" property="emailServerPojo.socketTimeOut" styleId="ESsocketTimeOut" maxlength="5" styleClass="textbox" onclick="removeZeroFromNumber(this);" onfocus="removeZeroFromNumber(this);" onkeyup="javascript:ValidatenReplaceAlphanumeric(this);" />
            <html:errors property="emailServerPojo.socketTimeOut" />
          </p>
          <!-- <p class="floatl marginleft60 ">
            <strong> Retry Count <sup class="req">*</sup></strong>
            <html:text name="masterForm" property="emailServerPojo.retryValue" styleId="ESretryValue" maxlength="3" styleClass="textbox" />
            <html:errors property="emailServerPojo.retryValue" />
          </p> -->

          <p class="floatl clear ">
            <strong style="width: 100%;"> Connection Time Out (In milliseconds) <sup class="req">*</sup></strong>
            <html:text name="masterForm" property="emailServerPojo.connectionTimeOut" styleId="ESconnectionTimeOut" maxlength="5" styleClass="textbox"  onkeyup="javascript:ValidatenReplaceAlphanumeric(this);" onclick="removeZeroFromNumber(this);" onfocus="removeZeroFromNumber(this);"/>
            <html:errors property="emailServerPojo.connectionTimeOut" />
          </p>
          
          <p class="floatl marginleft60 ">
            <b><br /></b>
            <html:checkbox name="masterForm" property="emailServerPojo.enable" value="true">SMTP Enabled</html:checkbox>
            <html:errors property="enable" />
            <html:checkbox name="masterForm" property="emailServerPojo.auth" value="true">Authorization</html:checkbox>
            <html:errors property="auth" />
          </p>
        </div>
        <div class="floatr inner_right">
          <html:link href="#" styleId="EScreateEmailServerButton" styleClass="main_button">
            <logic:empty name="masterForm" property="emailServerPojo.alias">
              <input type="hidden" value="create" id="createFlag">Create</logic:empty>
            <logic:notEmpty name="masterForm" property="emailServerPojo.alias">
              <input type="hidden" value="update" id="createFlag">Update</logic:notEmpty>
          </html:link>
        </div>
        <p class="clear"></p>
      </div>
      <p class="clear"></p>
    </div>
  </html:form>
</div>
