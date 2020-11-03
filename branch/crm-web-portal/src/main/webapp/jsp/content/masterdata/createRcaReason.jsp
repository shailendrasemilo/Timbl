<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib uri="/WEB-INF/tlds/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/tlds/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/tlds/displaytag.tld" prefix="display"%>
<%@taglib uri="/WEB-INF/tlds/struts-html.tld" prefix="html"%>
<div class="loadingPopup hidden"></div>
<div id="section" align="center">
  <html:form action="/crmRcaReason" method="post" styleId="rcaReasonMaster">
    <html:hidden name="crmRcaReasonForm" property="rowCounter" styleId="rowIndex_addRow" />
    <div class="section">
      <h2>CRM Master</h2>
      <div class="success_message">
        <logic:messagesPresent message="true">
          <html:messages id="message" message="true">
            <bean:write name="message" />
          </html:messages>
        </logic:messagesPresent>
      </div>
      <div class="error_message">
        <html:errors property="appError" />
      </div>
      <div class="inner_section">
        <div class="inner_left_lead floatl">

          <p class="floatl">
            <strong>Category</strong> <span class="dropdownWithoutJs"> <html:select styleId="category" name="crmRcaReasonForm"
                property="crmRcaReason.category" onchange="getSubCategoryValue(this.value);">
                <html:option value="0">Please Select</html:option>
                <logic:notEmpty name="crmRcaReasonForm" property="categoryList">
                  <html:optionsCollection name="crmRcaReasonForm" property="categoryList" label="contentName" value="contentValue" />
                </logic:notEmpty>
              </html:select>
            </span>
          </p>

          <p class="floatl marginleft30">
            <strong>Sub Category</strong> <span class="dropdownWithoutJs"> <html:select styleId="subcategory" name="crmRcaReasonForm"
                property="crmRcaReason.subCategory" onchange="getSubSubCategoryValue(this.value);">
                <logic:notEmpty name="crmRcaReasonForm" property="subCategoryList">
                  <html:optionsCollection name="crmRcaReasonForm" property="subCategoryList" label="contentValue" value="contentName" />
                </logic:notEmpty>
                <logic:empty name="crmRcaReasonForm" property="subCategoryList">
                  <html:option value="0">Please Select</html:option>
                </logic:empty>
              </html:select>
            </span>
          </p>

          <p class="floatl marginleft30">
            <strong>Sub Sub Category</strong> <span class="dropdownWithoutJs"> <html:select styleId="subsubcategory" name="crmRcaReasonForm"
                property="crmRcaReason.subSubCategory" onchange="searchCategoryValue();">
                <logic:notEmpty name="crmRcaReasonForm" property="subSubCategoryList">
                  <html:optionsCollection name="crmRcaReasonForm" property="subSubCategoryList" label="contentValue" value="contentName" />
                </logic:notEmpty>
                <logic:empty name="crmRcaReasonForm" property="subSubCategoryList">
                  <html:option value="0">Please Select</html:option>
                </logic:empty>
              </html:select>
            </span>
          </p>
          <%--
          <p class="floatl clear">
            <a href="javaScript:showDetails();" class="link">Show details</a>
          </p>
           --%>
          <br class="clear" />

          <div id="role_group_view1" class="relative margintop20">
            <span class="addbtn_role_group"> <html:link href="javascript:void(0)" styleId="idAddRca" styleClass="grey_button_add">
                <span>Add</span>
              </html:link>
            </span>
            <div class="display_catagoryValue">
              <div class="stays-at-top">
                <span class="heading_text"> CRM Master</span> <span class="heading_text marginleft485"><a href="javaScript:showDetails();">Show
                    CRM Master Details</a></span>
              </div>
              <logic:empty name="crmRcaReasonForm" property="crmRcaReasonsList">
                <span class="error_message"> &nbsp;Please click on 'Add' button to add Master data details. </span>
              </logic:empty>
              <logic:notEmpty name="crmRcaReasonForm" property="crmRcaReasonsList">
                <ul class="add_rcaValue">
                  <%!int k = 0;%>
                  <logic:iterate id="crmRcaReasonsList" name="crmRcaReasonForm" property="crmRcaReasonsList" indexId="ctr">
                    <%
                        k = 0;
                    %>
                    <%
                        if ( k % 2 == 0 )
                                    {
                    %>
                    <li class="first">
                      <%
                          }
                                      else
                                      {
                      %>
                    
                    <li class="second">
                      <%
                          }
                      %> <logic:equal name="crmRcaReasonsList" property="editable" value="true">
                        <logic:equal name="crmRcaReasonsList" property="categoryId" value="0">
                          <span class="manageRcaReason floatl"> <html:checkbox name="crmRcaReasonsList" property="editable" value="true"
                              indexed="true" onclick="return false"></html:checkbox>
                          </span>
                        </logic:equal>
                        <logic:notEqual name="crmRcaReasonsList" property="categoryId" value="0">
                          <span class="manageRcaReason floatl"> <html:checkbox name="crmRcaReasonsList" property="editable" value="true"
                              indexed="true" onclick="enableRcaReason(${ctr });"></html:checkbox>
                          </span>
                        </logic:notEqual>
                        <span class=" "> <html:text styleClass="reasonTextbox" name="crmRcaReasonsList" property="categoryValue" indexed="true"></html:text>
                        </span>
                        <span class="marginleft13"> <html:radio name="crmRcaReasonsList" property="status" value="A" indexed="true">Active</html:radio>
                          <html:radio name="crmRcaReasonsList" property="status" value="I" indexed="true">Inactive</html:radio>
                        </span>
                      </logic:equal> <logic:notEqual name="crmRcaReasonsList" property="editable" value="true">
                        <span class="manageRcaReason floatl"> <html:checkbox name="crmRcaReasonsList" property="editable" value="true"
                            indexed="true" onclick="enableRcaReason(${ctr });"
                            disabled="${crmRcaReasonsList.modificationAllowed eq 'Y' ? 'false' : 'true'}"></html:checkbox>
                        </span>
                        <span class=" "> <html:text styleClass="reasonTextbox" name="crmRcaReasonsList" property="categoryValue" indexed="true"
                            readonly="true" disabled="${crmRcaReasonsList.modificationAllowed eq 'Y' ? 'false' : 'true'}"></html:text>
                        </span>
                        <span class="marginleft13"> <html:radio name="crmRcaReasonsList" property="status" value="A" indexed="true"
                            onclick="return false" disabled="${crmRcaReasonsList.modificationAllowed eq 'Y' ? 'false' : 'true'}">Active</html:radio> <html:radio
                            name="crmRcaReasonsList" property="status" value="I" indexed="true" onclick="return false"
                            disabled="${crmRcaReasonsList.modificationAllowed eq 'Y' ? 'false' : 'true'}">Inactive</html:radio>
                        </span>
                      </logic:notEqual> <logic:equal name="crmRcaReasonsList" property="categoryId" value="0">
                        <span class="marginleft13"> <html:link href="javascript:deleteCategoryValueRow(${ctr})" styleClass="close">
                            <img src="images/bg/delete.png" align="absmiddle" title="delete" />
                          </html:link>
                        </span>
                      </logic:equal>
                    </li>
                    <%
                        k++;
                    %>
                  </logic:iterate>
                </ul>

              </logic:notEmpty>
            </div>
          </div>
        </div>
        <logic:equal name="crmRoles" property="available(create_crm_master,update_crm_master)" value="true" scope="session">
          <div class="floatr inner_right">
            <html:link href="#" styleClass="main_button" styleId="submit_rcaReasonMaster">Save</html:link>
          </div>
        </logic:equal>
        <p class="clear"></p>
      </div>
    </div>
    <p class="clear"></p>
  </html:form>
</div>
