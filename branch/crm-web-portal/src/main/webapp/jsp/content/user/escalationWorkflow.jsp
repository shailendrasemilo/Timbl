<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="/WEB-INF/tlds/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/tlds/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/tlds/displaytag.tld" prefix="display"%>
<%@taglib uri="/WEB-INF/tlds/struts-html.tld" prefix="html"%>
<html>
<div id="section"  align="center">
<html:form action="/searchEWUser"  method="post" styleId="">
<html:hidden  name="eWUserMappingForm" property="hiddenUserList" styleId="hiddenUserList" value=""/>
<html:hidden name="eWUserMappingForm" property="rowCounter" styleId="rowIndex_addRow" />
 <div class="section">
   <h2>User Mapping
   	<logic:equal name="eWUserMappingForm" property="mappingType" value="E">Escalation</logic:equal>
   	<logic:equal name="eWUserMappingForm" property="mappingType" value="W">Work Flow</logic:equal>
   </h2>
    <div class="success_message" >
					<logic:messagesPresent message="true">
   					<html:messages id="message" message="true">
					<bean:write name="message" />
					</html:messages>
					</logic:messagesPresent>
	</div>
   <span class="error_message"><html:errors property="appError" /></span>
   <div class="inner_section">
   <div class="inner_left_lead floatl">
    <p class="floatl ">
    	<strong>User ID</strong>
    		<html:text  styleClass="textbox" name="eWUserMappingForm" property="hiddenUserId" readonly="true"></html:text>
   	</p>
    <p class="floatl marginleft60">
    	<strong class="inlineBlock">&nbsp;</strong>
    		<a href="#" onclick="javascript:selectUser();" class="yellow_button">Select User</a>
    </p>
   <br class="clear"/>
   <div id="role_group_view1" class="relative margintop20">
    <div class="display_group">
      <div class="stays-at-top">
        <span class="heading_text marginleft40">  Add User</span> 
     </div>
    		<logic:empty name="eWUserMappingForm" property="crmUserMappingPojosList">
     			<span class="error_message">
       				Please click on select user to add user mapping.
       			</span>
     		</logic:empty>
     <logic:notEmpty name="eWUserMappingForm" property="crmUserMappingPojosList">
     <ul class="add_role_group">
     <%! int k=0; %>
     <%k=0; %>
     	<logic:iterate id="crmUserMappingPojosList" name="eWUserMappingForm" property="crmUserMappingPojosList" indexId="ctr">      		
			<% if(k%2 == 0){ %>							
				<li class="first">
			<% } else { %>
				<li class="second">
			<% } %>
			<logic:equal name="crmUserMappingPojosList" property="editable" value="true">
					<logic:equal name="crmUserMappingPojosList" property="userMappingId" value="0">
					<span class="marginright5">
       					<html:checkbox name="crmUserMappingPojosList" property="editable" value="true" indexed="true" onclick="return false"></html:checkbox>
       				</span>
       				</logic:equal>
       				<logic:notEqual name="crmUserMappingPojosList" property="userMappingId" value="0">
       					<html:hidden name="crmUserMappingPojosList" property="userId" value="${crmUserMappingPojosList.userId}" indexed="true"/>
       					<html:hidden name="crmUserMappingPojosList" property="mappingType" value="${crmUserMappingPojosList.mappingType}" indexed="true"/>
       					<span class="marginright5">
       						<html:checkbox name="crmUserMappingPojosList" property="editable" value="true" indexed="true" onclick="enableUserMapping(${ctr });"></html:checkbox>
       					</span>
       				</logic:notEqual>
       				<span class="marginleft13">	

        				<html:text styleClass="marginright5" name="crmUserMappingPojosList" property="mappedUserId" indexed="true" readonly="true" styleClass="textbox"></html:text>
        			</span>
        			<span class="marginleft13">
        					<html:radio name="crmUserMappingPojosList" property="status" value="A" indexed="true">Active</html:radio>
							<html:radio name="crmUserMappingPojosList" property="status" value="I" indexed="true">Inactive</html:radio>
        			</span>
			</logic:equal>
			<logic:notEqual name="crmUserMappingPojosList" property="editable" value="true">
					<span class="marginright5">
       					<html:checkbox name="crmUserMappingPojosList" property="editable" value="true" indexed="true" onclick="enableUserMapping(${ctr });"></html:checkbox>
       				</span>
       				<span class="marginleft13">
        				<html:text styleClass="marginleft13" name="crmUserMappingPojosList" property="mappedUserId" indexed="true" readonly="true" styleClass="textbox"></html:text>
        			</span>
        			<span class="marginleft13">
        					<html:radio name="crmUserMappingPojosList" property="status" value="A" indexed="true" onclick="return false">Active</html:radio>
							<html:radio name="crmUserMappingPojosList" property="status" value="I" indexed="true" onclick="return false">Inactive</html:radio>
        			</span>
			</logic:notEqual>
        	<logic:equal name="crmUserMappingPojosList" property="userMappingId" value="0">
        				<span class="marginleft13">
         					<html:link href="javascript:deleteUserMappingRow(${ctr})" styleClass="close"><img src="images/bg/delete.png" align="absmiddle" title="delete"/></html:link>
         				</span>
         	</logic:equal>
      		</li>
      		<% k++; %>
    	 </logic:iterate>
     </ul>
     </logic:notEmpty>
    </div>
   </div>
  </div>
   <logic:equal name="crmRoles" property="available(create_esc_wflow,update_esc_wflow)" value="true" scope="session">
	    <div class="floatr inner_right">
	     <html:link href="#"  styleClass="main_button" styleId="save_ewUserMapping">Save</html:link>
	    </div>
    </logic:equal>
    <p class="clear"></p>
     </div>
   </div>
  <p class="clear"></p>
 </html:form>
</div>
</body>
</html>
