<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<%@taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@taglib uri="http://struts.apache.org/tags-nested" prefix="nested"%>
<div id="section">
	<html:form action="/partnerManagement" styleId="addNpMobileForm">
		<div class="section">
			<h2>Add NP Mobile No.</h2>
			<div class="inner_section">
				<div class="error_message">
					<span class="error_message"><html:errors property="appError" /></span> <span class="success_message"> <logic:messagesPresent message="true"
							property="appMessage">
							<html:messages id="msg" message="true" property="appMessage">
								<bean:write name="msg" />
							</html:messages>
						</logic:messagesPresent>
					</span>
				</div>
				<div class="inner_left floatl createUserLeft">
					<p class="floatl">
			   			<b>Network Partner</b>
			    		<span class="showTextbox">
			    			<bean:write name="crmRoles" property="displayEnum(PartnerPojo,${partnerManagementForm.partnerId})" scope="session" />
						</span>
					</p>
					<br class="clear"/>
					<div id="role_group_view">
						<span class="addbtn_role_group"><a href="javascript:addNPMobileNewRow();" id="addNPMobileNewRow" class="grey_button_add"><span>Add</span></a></span>
						<div class="display_group">
							<div class="stays-at-top">
								<span class="heading_text">Mobile No.</span>
								<span class="heading_text" style="margin-left:175px !important"> Status </span>  
							</div>
							<ul class="add_option82" id="parameterGroupList">
								<logic:notEmpty name="partnerManagementForm" property="crmNpMobileList">
									<logic:iterate id="crmNpMobileList" name="partnerManagementForm" property="crmNpMobileList" indexId="ctr">
										<%!int i = 1;%>
										<html:hidden name="crmNpMobileList" property="editable" value="true" indexed="true" />

										<logic:equal name="crmNpMobileList" property="recordId" value="0">
											<html:hidden name="crmNpMobileList" property="npId" value="${partnerManagementForm.partnerId}" indexed="true" />
										</logic:equal>
										<logic:equal name="crmNpMobileList" property="recordId" value="0">
											<html:hidden name="crmNpMobileList" property="createdBy" value="${userPojo.userId}" indexed="true" />
										</logic:equal>
										<logic:equal name="crmNpMobileList" property="recordId" value="0">
											<html:hidden name="crmNpMobileList" property="genericType" value="partner" indexed="true" />
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
											<span class="inlineBlock ">
       					 						<html:text name="crmNpMobileList" property="mobileNo" styleClass="textbox" indexed="true" maxlength="10" onkeyup="javascript:ValidatenReplaceAlphanumeric(this);" onclick="removeZeroFromNumber(this);"></html:text> 
											</span>
											</logic:equal>
											<logic:notEqual name="crmNpMobileList" property="recordId" value="0">
											<span class="showTextbox">	
												<bean:write name="crmNpMobileList" property="mobileNo" />
											</span>
											</logic:notEqual>
											<span class="marginleft13"> 
											  <html:radio name="crmNpMobileList" property="status" value="A" indexed="true">Active</html:radio> 
											  <html:radio name="crmNpMobileList" property="status" value="I" indexed="true">Inactive</html:radio>
										   </span>
										   <logic:equal name="crmNpMobileList" property="recordId" value="0"> 
										   <span class="marginleft13"> 
											 <html:link href="javascript:deleteAddNPMobileRow(${ctr})" styleClass="close">
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
								<logic:empty name="partnerManagementForm" property="crmNpMobileList">
									<span class="error_message"> Please click on 'Add' button to Add NP Mobile. </span>
								</logic:empty>
							</ul>
						</div>
					</div>
				</div>
				<div class="floatr inner_right">
					<a href="#" id="submit_AddNPMobile" class="main_button">Submit</a>
				</div>
				<br class="clear" />
			</div>
		</div>
		<html:hidden name="partnerManagementForm" property="rowCounter" styleId="rowIndex_addNPMobile" />
	</html:form>
	<p class="clear"></p>
</div>
<div></div>
