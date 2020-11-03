<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib uri="/WEB-INF/tlds/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/tlds/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/tlds/struts-html.tld" prefix="html"%>
<div id="section">
  <html:form action="/eventTemplateMapping" styleId="rolegroup" method="post" enctype="multipart/form-data">
    <%
        int count = 0;
    %>
    <div class="section">
      <h2>Event Template Mapping</h2>
      <div class="success_message">
        <logic:messagesPresent message="true">
          <html:messages id="message" message="true">
            <bean:write name="message" />
          </html:messages>
        </logic:messagesPresent>
      </div>
      <div class="error_message">
        <html:errors />
      </div>
      <div class="inner_section">
        <div class="inner_left floatl">
          <div id="mapping_view">
            <logic:equal name="crmRoles" property="available(create_template_mapping)" value="true" scope="session">
              <span class="addbtn_mapping"><a href="javascript:void(0)" id="addMapping" class="grey_button_add"><span>Add Mapping</span></a></span>
            </logic:equal>
            <div class="display_mapping_email">
              <div class="stays-at-top">
                <span class="heading_text marginleft12"> Edit</span> <span class="heading_text marginleft6"> Event Name</span> <span
                  class="heading_text" style="margin-left: 153px;"> Enabled</span> <span class="heading_text" style="margin-left: 6px;"> SMS
                  Template</span> <span class="heading_text" style="margin-left: 145px;"> Enabled</span> <span class="heading_text"
                  style="margin-left: 12px;"> Email Template</span>
              </div>
              <ul class="add_mapping_alert">
                <logic:notEmpty name="alertForm" property="evntTemplateList">
                  <logic:iterate id="evntTemplateList" name="alertForm" property="evntTemplateList" indexId="ctr">

                    <logic:equal name="evntTemplateList" property="editable" value="true">
                      <li class="first"><span class="padding"> <logic:equal name="evntTemplateList" property="eventTemplateId" value="0">
                            <html:checkbox name="evntTemplateList" property="editable" value="true" indexed="true" onclick="return false"></html:checkbox>
                          </logic:equal> <logic:notEqual name="evntTemplateList" property="eventTemplateId" value="0">
                            <html:checkbox name="evntTemplateList" property="editable" value="true" indexed="true"></html:checkbox>
                          </logic:notEqual>
                      </span> <logic:equal name="evntTemplateList" property="eventTemplateId" value="0">
                          <span class="dropdownWithoutJs "> <html:select name="evntTemplateList" property="events.eventId" indexed="true">
                              <html:optionsCollection name="alertForm" property="eventList" label="eventDescription" value="eventId"></html:optionsCollection>
                            </html:select>
                          </span>
                        </logic:equal> <logic:notEqual name="evntTemplateList" property="eventTemplateId" value="0">
                          <span> <html:hidden name="evntTemplateList" property="events.eventId" indexed="true" /> <html:text
                              name="evntTemplateList" property="events.eventDescription" styleClass="textbox" readonly="true" indexed="true" />
                          </span>
                        </logic:notEqual> <span class="padding"> <html:checkbox name="evntTemplateList" property="smsEnabled" styleClass="smsEnabled"
                            onchange="smsEmailToggle(this, 'evntTemplateList[${ctr}].smsTemplate.smsTemplateId')" indexed="true" value="true"></html:checkbox>
                      </span> <span class="mappingDropdown marginleft35 "> <html:select styleClass="select" name="evntTemplateList"
                            property="smsTemplate.smsTemplateId" indexed="true">
                            <logic:notEmpty name="alertForm" property="smsTemplateList">
                              <html:optionsCollection name="alertForm" property="smsTemplateList" label="smsTemplateName" value="smsTemplateId"></html:optionsCollection>
                            </logic:notEmpty>
                          </html:select>
                      </span> <span class="padding"> <html:checkbox name="evntTemplateList" property="emailEnabled" styleClass="emailEnabled"
                            onchange="smsEmailToggle(this, 'evntTemplateList[${ctr}].emailTemplate.emailTemplateId')" indexed="true" value="true"></html:checkbox>
                      </span> <span class="mappingDropdown marginleft40"> <html:select styleClass="select" name="evntTemplateList"
                            property="emailTemplate.emailTemplateId" indexed="true">
                            <logic:notEmpty name="alertForm" property="emailTemplateList">
                              <html:optionsCollection name="alertForm" property="emailTemplateList" label="emailTemplateName" value="emailTemplateId"></html:optionsCollection>
                            </logic:notEmpty>
                          </html:select>
                      </span> <logic:equal name="evntTemplateList" property="eventTemplateId" value="0">
                          <span class=" marginleft4"><a href="javascript:deleteMappingRow(${ctr})" class="close"><img
                              src="images/bg/delete.png" align="absmiddle" /></a></span>
                        </logic:equal></li>
                    </logic:equal>
                    <logic:equal name="evntTemplateList" property="editable" value="false">
                      <li class="first"><span class="padding"> <html:checkbox name="evntTemplateList" property="editable" value="true"
                            indexed="true" onchange="javascript:enableMapping(${ctr })"></html:checkbox></span> <span> <html:hidden
                            name="evntTemplateList" property="events.eventId" indexed="true" /> <html:text name="evntTemplateList"
                            property="events.eventDescription" styleClass="textbox" readonly="true" indexed="true" />
                      </span> <span class="padding"> <html:checkbox name="evntTemplateList" property="smsEnabled" styleClass="smsEnabled"
                            onchange="smsEmailToggle(this, 'evntTemplateList[${ctr}].smsTemplate.smsTemplateId')" onclick="return false"
                            indexed="true" value="true"></html:checkbox></span> <span class="mappingDropdown marginleft35"> <html:select
                            styleClass="select" name="evntTemplateList" property="smsTemplate.smsTemplateId" indexed="true" onclick="return false">
                            <logic:notEmpty name="alertForm" property="smsTemplateList">
                              <html:optionsCollection name="alertForm" property="smsTemplateList" label="smsTemplateName" value="smsTemplateId"></html:optionsCollection>
                            </logic:notEmpty>
                          </html:select>
                      </span> <span class="padding"> <html:checkbox name="evntTemplateList" property="emailEnabled" styleClass="emailEnabled"
                            onchange="smsEmailToggle(this, 'evntTemplateList[${ctr}].emailTemplate.emailTemplateId')" onclick="return false"
                            indexed="true" value="true"></html:checkbox></span> <span class="mappingDropdown marginleft40"> <html:select
                            styleClass="select" name="evntTemplateList" property="emailTemplate.emailTemplateId" indexed="true"
                            onchange="return false" onclick="return false">
                            <logic:notEmpty name="alertForm" property="emailTemplateList">
                              <html:optionsCollection name="alertForm" property="emailTemplateList" label="emailTemplateName" value="emailTemplateId"></html:optionsCollection>
                            </logic:notEmpty>
                          </html:select>
                      </span></li>
                    </logic:equal>
                    <%
                        count++;
                    %>
                  </logic:iterate>
                  <input type="hidden" id="dataCount" value="<%=count%>" />
                </logic:notEmpty>


              </ul>
            </div>
          </div>
        </div>
        <div class="floatr inner_right">
          <logic:equal name="crmRoles" property="available(create_template_mapping,update_template_mapping)" value="true" scope="session">
            <html:link href="#" styleId="submit_EventMapping" styleClass="main_button_multiple">Create Mapping</html:link>
          </logic:equal>
        </div>
        <p class="clear"></p>
      </div>
    </div>
    <html:hidden name="alertForm" property="rowCounter" styleId="rowIndex_addRow" />
  </html:form>
</div>