<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<%@taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@taglib uri="http://struts.apache.org/tags-nested" prefix="nested"%>

<style>
<%--.slct span{position: relative;
    top: -15px;
    left: 10px;
}--%>

#role_group_view {width:750px; }
.showTextbox {vertical-align: middle}
.rdio{    vertical-align: middle; }
.rdio input {position:relative; top:2px}

</style>

<div id="section">
	<html:form action="/smsAlert" styleId="addUserAlertForm">
		<div class="section">
			<h2>Map User Event</h2>
			<div class="inner_section">
				<div class="error_message">
					<span id="val_error" class="error_message"><html:errors property="appError" /></span> <span class="success_message"> <logic:messagesPresent message="true"
							property="appMessage">
							<html:messages id="msg" message="true" property="appMessage">
								<bean:write name="msg" />
							</html:messages>
						</logic:messagesPresent>
					</span>
				</div>
				<div class="inner_left floatl createUserLeft">
					<p class="floatl">
			   			<b>Events</b>
					<%--	<span class="dropdownWithoutJs slct"> 
						  <html:select styleClass="select"  name="alertForm" property="eventId" styleId="selctEventId">
                            <logic:notEmpty name="alertForm" property="eventList">
                              <html:optionsCollection name="alertForm" property="eventList" label="eventDescription" value="eventId"></html:optionsCollection>
                            </logic:notEmpty>
                          </html:select>
                      </span> --%>
					  <span class="showTextbox">
			    		Add Mass Outage
						</span>
			    		
					</p>
					<br class="clear"/>
					<div id="role_group_view">
						<logic:equal name="crmRoles" property="available(create_template_mapping)" value="true" scope="session">
						<span class="addbtn_role_group"><a href="javascript:addUserAlertMobileEmailNewRow();" id="addNPMobileNewRow" class="grey_button_add"><span>Add</span></a></span>
						</logic:equal>
						<div class="display_group">
							<div class="stays-at-top">
								<span class="heading_text">Mobile No.</span>
								<span class="heading_text" style="margin-left:175px !important">Email-ID</span>
								<span class="heading_text" style="margin-left:200px !important"> Status </span>  
							</div>
							<ul class="add_option82" id="parameterGroupList">
								<logic:notEmpty name="alertForm" property="crmNpMobileList">
									<logic:iterate id="crmNpMobileList" name="alertForm" property="crmNpMobileList" indexId="ctr">
										<%!int i = 1;%>
									<%--	<html:hidden name="crmNpMobileList" property="editable" value="true" indexed="true" />  --%>
										<logic:equal name="crmNpMobileList" property="recordId" value="0">
											<html:hidden name="crmNpMobileList" property="eventId" value="${alertForm.eventId}" indexed="true" />
										</logic:equal>
										<logic:equal name="crmNpMobileList" property="recordId" value="0">
											<html:hidden name="crmNpMobileList" property="createdBy" value="${userPojo.userId}" indexed="true" />
										</logic:equal>
										<logic:equal name="crmNpMobileList" property="recordId" value="0">
											<html:hidden name="crmNpMobileList" property="genericType" value="event" indexed="true" />
										</logic:equal>
										<logic:notEqual name="crmNpMobileList" property="recordId" value="0">
											<html:hidden name="crmNpMobileList" property="lastModifiedBy" value="${userPojo.userId}" indexed="true" />
										</logic:notEqual>
										    <%
											    if ( i % 2 == 0 )
											     {
											        %> <li class="first"> <%
												 }
								                else
								                 { 
								                	%> <li class="second"> <%
												 }
											%> 
											<logic:equal name="crmNpMobileList" property="recordId" value="0">
											 <span class="inlineBlock"> <html:checkbox name="crmNpMobileList" property="editable" value="true"
                                               indexed="true" onclick="return false"></html:checkbox>
                                             </span>
										    </logic:equal>
											<logic:notEqual name="crmNpMobileList" property="recordId" value="0">
										     <span class="inlineBlock"> <html:checkbox name="crmNpMobileList" property="editable" value="true"
                                               indexed="true" onclick="enableUserAlert(${ctr });"></html:checkbox>
                                             </span>
											</logic:notEqual>
											<logic:equal name="crmNpMobileList" property="recordId" value="0">
											<span class="inlineBlock ">
       					 						<html:text name="crmNpMobileList" property="mobileNo" styleClass="textbox" indexed="true" maxlength="10" onkeyup="javascript:ValidatenReplaceAlphanumeric(this);" onclick="removeZeroFromNumber(this);"></html:text> 
											</span>
											<span class="inlineBlock " style="margin-left:20px">
       					 						<html:text  name="crmNpMobileList" property="emailId" styleClass="textbox" indexed="true" maxlength="256"></html:text> 
											</span>
											</logic:equal>
										<%--	<logic:notEqual name="crmNpMobileList" property="recordId" value="0">
											<span class="showTextbox">	
												<bean:write name="crmNpMobileList" property="mobileNo" />
											</span>
											<span class="showTextbox" style="margin-left:20px">	
												<bean:write name="crmNpMobileList" property="emailId" />
											</span>
											</logic:notEqual> --%>
											<logic:notEqual name="crmNpMobileList" property="recordId" value="0">
											<span class="inlineBlock ">
       					 						<html:text readonly="true" name="crmNpMobileList" property="mobileNo" styleClass="textbox" indexed="true" maxlength="10" onkeyup="javascript:ValidatenReplaceAlphanumeric(this);" onclick="removeZeroFromNumber(this);"></html:text> 
											</span>
											<span class="inlineBlock " style="margin-left:20px">
       					 						<html:text  readonly="true" name="crmNpMobileList" property="emailId" styleClass="textbox" indexed="true" maxlength="256"></html:text> 
											</span>
											</logic:notEqual> 
									       	<logic:equal name="crmNpMobileList" property="recordId" value="0">
											<span class="marginleft13 rdio"> 
											  <html:radio name="crmNpMobileList" property="status" value="A" indexed="true">Active</html:radio> 
											  <html:radio name="crmNpMobileList" property="status" value="I" indexed="true">Inactive</html:radio>
										   </span>
											</logic:equal>
											<logic:notEqual name="crmNpMobileList" property="recordId" value="0">
											<span class="marginleft13 rdio"> 
											  <html:radio name="crmNpMobileList" property="status" value="A" indexed="true" onclick="return false">Active</html:radio> 
											  <html:radio name="crmNpMobileList" property="status" value="I" indexed="true" onclick="return false">Inactive</html:radio>
										   </span>
											</logic:notEqual> 
										   <logic:equal name="crmNpMobileList" property="recordId" value="0"> 
										   <span class="marginleft13"> 
											 <html:link href="javascript:deleteUserAlertMobileEmailRow(${ctr})" styleClass="close">
											    <img src="images/bg/delete.png" align="absmiddle" title="delete" />
											  </html:link>
											</span>
											</logic:equal>
										</li>
										<%
										    i++;
										%>
									</logic:iterate>
								</logic:notEmpty>
								<logic:empty name="alertForm" property="crmNpMobileList">
									<span class="error_message"> Please click on 'Add' button to Add User Alert mobile and Email </span>
								</logic:empty>
							</ul>
						</div>
					</div>
				</div>
				<div class="floatr inner_right">
					<logic:equal name="crmRoles" property="available(create_template_mapping,update_template_mapping)" value="true" scope="session">
					<a href="#" id="submit_AddUserAlertMobileEmail" class="main_button">Submit</a>
					</logic:equal>
				</div>
				<br class="clear" />
			</div>
		</div>
		<html:hidden name="alertForm" property="rowCounter" styleId="rowIndex_addNPMobile" />
	</html:form>
	<p class="clear"></p>
</div>
<div></div>
