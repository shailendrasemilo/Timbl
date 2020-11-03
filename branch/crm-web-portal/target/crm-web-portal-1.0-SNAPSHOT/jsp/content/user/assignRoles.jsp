<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<%@taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@taglib uri="http://struts.apache.org/tags-nested" prefix="nested"%>
<div id="section">
  <html:form action="/roleManagement">
    <div class="section">
      <h2>Assign Roles</h2>
      <div class="inner_section">
        <logic:equal name="crmRoles" property="available(create_assign_params,update_assign_params,delete_assign_params,recover_assign_params)"
          value="true" scope="session">
          <a href="javascript:assignParameter()" class="link_button">Assign Parameters</a>
        </logic:equal>
        <p>
          <span class="error_message"> <html:errors property="userRolesPojos" /> <html:errors property="appError" />
          </span> <span class="success_message"> <logic:messagesPresent message="true" property="appMessage">
              <html:messages id="msg" message="true" property="appMessage">
                <bean:write name="msg" />
              </html:messages>
            </logic:messagesPresent>
          </span>
        </p>
        <div class="inner_left_lead floatl  marginleft10">
          <p>
            <strong>User ID</strong> <span class="showTextbox"> <bean:write name="searchUserPojo" property="userId" scope="session" />
            </span>
          </p>
          <div id="role_group_view">
            <span class="error_message"> </span> <span class="addbtn_role_group"><a href="javascript:void(0)" id="addRole"
              class="grey_button_add"><span>Add</span></a></span>
            <div class="display_group">
              <div class="stays-at-top">
                <span class="heading_text"> Role's Name</span> <span class="heading_text marginleft485"><a
                  href="javaScript:showRolesDetails();">Show Role Details</a></span>
              </div>
              <ul class="add_role_group">
                <logic:notEmpty name="loginForm" property="userRolesPojos">
                  <logic:iterate id="userRolesPojos" name="loginForm" property="userRolesPojos" indexId="ctr">
                    <logic:notEmpty name="userRolesPojos" property="crmRoles">

                      <li class="${ctr % 2 eq 0 ? 'first' : 'second' }"><logic:notEqual name="userRolesPojos" property="recordId" value="0">
                          <html:hidden name="userRolesPojos" property="recordId" value="${userRolesPojos.recordId}" indexed="true" />
                          <html:hidden name="userRolesPojos" property="createdBy" value="${userRolesPojos.createdBy}" indexed="true" />
                          <html:hidden name="userRolesPojos" property="lastModifiedBy" value="${userPojo.userId}" indexed="true" />
                          <html:hidden name="userRolesPojos" property="crmRoles.roleName" value="${userRolesPojos.crmRoles.roleName}" indexed="true" />
                          <html:hidden name="userRolesPojos" property="crmRoles.roleId" value="${userRolesPojos.crmRoles.roleId}" indexed="true" />
                          <html:hidden name="userRolesPojos" property="crmRoles.status" value="${userRolesPojos.crmRoles.status}" indexed="true" />
                          <html:hidden name="userRolesPojos" property="crmRoles.roleDescription" value="${userRolesPojos.crmRoles.roleDescription}"
                            indexed="true" />
                          <span class="showDropbox200"> <bean:write name="userRolesPojos" property="crmRoles.roleDescription" />
                          </span>
                        </logic:notEqual> <logic:equal name="userRolesPojos" property="recordId" value="0">
                          <html:hidden name="userRolesPojos" property="createdBy" value="${userPojo.userId}" indexed="true" />
                          <span class="dropdownWithoutJs"> <html:select name="userRolesPojos" property="crmRoles.roleId" indexed="true">
                              <html:optionsCollection name="loginForm" property="crmRoles" label="roleDescription" value="roleId" />
                            </html:select>
                          </span>
                        </logic:equal> <span class="paddingtopbottom5"><html:checkbox name="userRolesPojos" property="readAccess" value="true" indexed="true"
                            styleClass="marginright9">View</html:checkbox></span> <span class="paddingtopbottom5"><html:checkbox name="userRolesPojos"
                            property="createAccess" value="true" indexed="true" styleClass="marginright9">Create</html:checkbox></span> <span
                        class="paddingtopbottom5"><html:checkbox name="userRolesPojos" property="updateAccess" value="true" indexed="true"
                            styleClass="marginright9">Update</html:checkbox></span> <span class="paddingtopbottom5"><html:checkbox
                            name="userRolesPojos" property="deleteAccess" value="true" indexed="true" styleClass="marginright9">Delete</html:checkbox></span>
                        <span class="paddingtopbottom5"><html:checkbox name="userRolesPojos" property="recoverAccess" value="true"
                            indexed="true" styleClass="marginright9">Recover</html:checkbox></span> <html:hidden name="userRolesPojos" property="editable"
                          value="true" indexed="true" /> <html:hidden name="userRolesPojos" property="userId" value="${searchUserPojo.userId}"
                          indexed="true" /> <logic:notEqual name="userRolesPojos" property="recordId" value="0">
                          <span class="paddingtopbottom5"> <html:radio name="userRolesPojos" property="status" value="A" indexed="true">Active</html:radio>
                            <html:radio name="userRolesPojos" property="status" value="I" indexed="true">Inactive</html:radio>
                          </span>
                        </logic:notEqual> <logic:equal name="userRolesPojos" property="recordId" value="0">
                          <span> <html:hidden name="userRolesPojos" property="status" value="A" indexed="true" /> <html:link
                              href="javascript:deleteRole(${ctr})" styleClass="close">
                              <img src="images/bg/delete.png" align="absmiddle" title="delete" />
                            </html:link></span>
                        </logic:equal></li>
                    </logic:notEmpty>
                  </logic:iterate>
                </logic:notEmpty>
                <logic:empty name="loginForm" property="userRolesPojos">
                  <span class="error_message"><br /> &nbsp;Please click on 'Add' button to Assign Roles. </span>
                </logic:empty>
              </ul>
            </div>
          </div>
        </div>
        <div class=" floatl groupSelection">
          <div class="stays-at-top">
            <span>Role Group's Name</span>
          </div>
          <div class="searchRoles">
            <input type="text" class="searchAssignRoles search_textbox" />
          </div>
          <ul class="searchList" id="searchAssignRolesList">
            <logic:notEmpty name="loginForm" property="userRolesPojosForGroup">
              <logic:iterate id="userRolesPojosForGroup" name="loginForm" property="userRolesPojosForGroup" indexId="ctr">
                <li class="${ctr % 2 eq 0 ? 'first' : 'second' }"><logic:equal name="userRolesPojosForGroup" property="recordId" value="0">
                    <span> <html:hidden name="userRolesPojosForGroup" property="userId" value="${searchUserPojo.userId}" indexed="true" /> <html:hidden
                        name="userRolesPojosForGroup" property="status" value="A" indexed="true" /> <html:hidden name="userRolesPojosForGroup"
                        property="createdBy" value="${userPojo.userId}" indexed="true" /> <html:checkbox name="userRolesPojosForGroup"
                        property="editable" indexed="true" value="true" styleClass="marginright9">
                        <a><bean:write name="userRolesPojosForGroup" property="groups.groupName" /> </a>
                      </html:checkbox>
                    </span>
                    <html:hidden name="userRolesPojosForGroup" property="groups.groupName" value="${userRolesPojosForGroup.groups.groupName}"
                      indexed="true" />
                    <html:hidden name="userRolesPojosForGroup" property="groups.groupId" value="${userRolesPojosForGroup.groups.groupId}"
                      indexed="true" />
                    <html:hidden name="userRolesPojosForGroup" property="groups.groupType" value="${userRolesPojosForGroup.groups.groupType}"
                      indexed="true" />
                    <html:hidden name="userRolesPojosForGroup" property="groups.groupDescription"
                      value="${userRolesPojosForGroup.groups.groupDescription}" indexed="true" />
                    <html:hidden name="userRolesPojosForGroup" property="groups.status" value="${userRolesPojosForGroup.groups.status}" indexed="true" />
                  </logic:equal> <logic:notEqual name="userRolesPojosForGroup" property="recordId" value="0">
                    <span> <html:hidden name="userRolesPojosForGroup" property="userId" value="${searchUserPojo.userId}" indexed="true" /> <html:hidden
                        name="userRolesPojosForGroup" property="status" value="${userRolesPojosForGroup.status}" indexed="true" /> <html:hidden
                        name="userRolesPojosForGroup" property="lastModifiedBy" value="${userPojo.userId}" indexed="true" /> <html:checkbox
                        name="userRolesPojosForGroup" property="editable" indexed="true" value="true" styleClass="marginright9">
                        <a><bean:write name="userRolesPojosForGroup" property="groups.groupName" /> </a>
                      </html:checkbox>
                    </span>
                    <html:hidden name="userRolesPojosForGroup" property="groups.groupName" value="${userRolesPojosForGroup.groups.groupName}"
                      indexed="true" />
                    <html:hidden name="userRolesPojosForGroup" property="groups.groupId" value="${userRolesPojosForGroup.groups.groupId}"
                      indexed="true" />
                    <html:hidden name="userRolesPojosForGroup" property="groups.groupType" value="${userRolesPojosForGroup.groups.groupType}"
                      indexed="true" />
                    <html:hidden name="userRolesPojosForGroup" property="groups.groupDescription"
                      value="${userRolesPojosForGroup.groups.groupDescription}" indexed="true" />
                    <html:hidden name="userRolesPojosForGroup" property="groups.status" value="${userRolesPojosForGroup.groups.status}" indexed="true" />
                    <html:hidden name="userRolesPojosForGroup" property="recordId" value="${userRolesPojosForGroup.recordId}" indexed="true" />
                  </logic:notEqual></li>

              </logic:iterate>
            </logic:notEmpty>
          </ul>
        </div>
        <div class="floatr inner_right">
          <html:link href="#" styleId="submitAssignRole_assignrole" styleClass="main_button_multiple">Assign Roles</html:link>
        </div>
        <p class="clear"></p>
      </div>
      <div></div>
    </div>
    <html:hidden name="loginForm" property="rowCounter" styleId="rowIndex_Role" />
  </html:form>
</div>
