<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib uri="/WEB-INF/tlds/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/tlds/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/tlds/displaytag.tld" prefix="display"%>
<%@taglib uri="/WEB-INF/tlds/struts-html.tld" prefix="html"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div class="loadingPopup hidden"></div>
<div id="section" align="center">
  <html:form action="/qrcConfig" styleId="formAttributedToConfig">
    <div class="section">
      <h2>Attributed To</h2>
      <div class="inner_section">
        <div class="success_message">
          <logic:messagesPresent message="true">
            <html:messages id="message" message="true">
              <bean:write name="message" />
            </html:messages>
          </logic:messagesPresent>
        </div>
        <div class="error_message" id="error">
          <html:errors />
        </div>
        <%int j=0; %>
        <div class="inner_left_lead floatl">
          <p class="floatl">
            <strong>Category<span class="error_message verticalalignTop">*</span></strong> <span class="LmsdropdownWithoutJs"> <html:select property="qrcCategoryId" name="qrcConfigForm"
                styleId="attrCodeCatId" onchange="populateRCAforAttributed(this.value, $('#attrCodeService').val(), 'attrCodeRcaId','attrCodeResolutionId');">
                <html:option value="0">Please Select</html:option>
                <html:optionsCollection name="qrcConfigForm" property="qrcCategories" value="qrcCategoryId" label="qrcCategory" />
              </html:select> <font class="errorTextbox hidden">Please select Category.</font>
            </span>
          </p>
          <p class="floatl marginleft30">
            <strong>Service Name<span class="error_message verticalalignTop">*</span></strong> <span class="LmsdropdownWithoutJs"> <html:select property="serviceName" name="qrcConfigForm"
                styleId="attrCodeService" onchange="populateRCAforAttributed($('#attrCodeCatId').val(), this.value, 'attrCodeRcaId','attrCodeResolutionId');">
                <html:option value="0">Please Select</html:option>
                <html:optionsCollection property="productTypes" name="qrcConfigForm" value="contentValue" label="contentName" />
              </html:select> <font class="errorTextbox hidden">Please select Service Name.</font>
            </span>
          </p>
          <p class="floatl marginleft30">
            <strong>Action Taken<span class="error_message verticalalignTop">*</span></strong><span class="LmsdropdownWithoutJs"> <html:select property="qrcRcaId" name="qrcConfigForm"
                styleId="attrCodeRcaId" onchange="fillResolutionListResCode( this.value, 'attrCodeResolutionId');">
                <html:option value="0">Please Select</html:option>
                <logic:notEmpty name="qrcConfigForm" property="qrcActionTakenPojos">
                  <html:optionsCollection name="qrcConfigForm" property="qrcActionTakenPojos" label="actionTaken" value="actionId" />
                </logic:notEmpty>
              </html:select> <font class="errorTextbox hidden">Please select Action Taken.</font>
            </span>
          </p>
          <p class="floatl marginleft30">
            <strong>Root Cause<span class="error_message verticalalignTop">*</span></strong><span class="LmsdropdownWithoutJs"> <html:select property="qrcResolutionCodeId"
                name="qrcConfigForm" styleId="attrCodeResolutionId" onchange="getAttributedTo(this.value);">
                <html:option value="0">Please Select</html:option>
                <logic:notEmpty name="qrcConfigForm" property="qrcRootCausePojos">
                  <html:optionsCollection name="qrcConfigForm" property="qrcRootCausePojos" label="rootCause" value="rootCauseId" />
                </logic:notEmpty>
              </html:select> <font class="errorTextbox hidden">Please select Root Cause.</font>
            </span>
          </p>
          <br class="clear" />
          <div id="role_group_view1" class="relative margintop20" style="width: 745px;">
            <span class="addbtn_role_group"><a href="javascript:void(0)" id="addRowAttributedTo" class="grey_button_add"><span>Add</span></a></span>
            <div class="display_group" style="height: 250px;">
              <div class="stays-at-top">
                <span class="heading_text"> Attributed To</span>
              </div>
              <logic:empty name="qrcConfigForm" property="attributedToPojos">
                <span class="error_message"> &nbsp;Please click on 'Add' button to add Attributed To. </span>
              </logic:empty>
              <logic:notEmpty name="qrcConfigForm" property="attributedToPojos">
			  <div id="displayAreaID">
                <ul class="add_rcaValue">
                  <c:forEach items="${ qrcConfigForm.attributedToPojos }" var="attributedToPojos" varStatus="current">
                    <li class="${ current.index mod 2 eq 0 ? 'first' : 'second' }"><span class="manageRcaReason floatl"> <html:checkbox
                          name="attributedToPojos" property="editable" value="true" indexed="true"
                          onclick="${ qrcResolutionCodePojos.editable ? ( attributedToPojos.attributedToId gt 0 ? 'toggleEditableAttributedCode('.concat(current.index).concat(');') : 'return false;' ) : 'toggleEditableAttributedCode('.concat(current.index).concat(');') }">
                        </html:checkbox>
                    </span> <span class=""> <html:text styleClass="reasonTextbox" name="attributedToPojos" property="attributedTo" indexed="true"
                          readonly="${ attributedToPojos.editable ? 'false' : 'true' }">
                        </html:text>
                    </span> <span class="marginleft13"> <html:radio name="attributedToPojos" property="status" value="A" indexed="true"
                          onclick="${ attributedToPojos.editable ? '' : 'return false;' }">
                        	  Active
                         </html:radio> <html:radio name="attributedToPojos" property="status" value="I" indexed="true"
                          onclick="${ attributedToPojos.editable ? '' : 'return false;' }">
                           	Inactive
                         </html:radio>
                    </span> <c:if test="${ attributedToPojos.editable and (attributedToPojos.attributedToId eq 0) }">
                        <span class="marginleft13"> <html:link href="javascript:removeAttributedToRow(${ current.index })" styleClass="close">
                            <img src="images/bg/delete.png" align="absmiddle" title="delete" />
                          </html:link>
                        </span>
                      </c:if></li>
                      <%j=j+1; %>
                  </c:forEach>
                </ul>
				</div>
              </logic:notEmpty>
              <ul id='qrcRcaConfigurations' class="add_resCode"></ul>
            </div>
          </div>
        </div>
        <div class="floatr inner_right">
        <logic:equal name="crmRoles" property="available(create_qrc_cnf,update_qrc_cnf)" value="true" scope="session">
          <a href="javascript:qrcAttributedToConfig(<%=j %>)" class="main_button">Configure</a>
          </logic:equal>
        </div>
        <p class="clear"></p>
      </div>
    </div>
    <p class="clear"></p>
  </html:form>
</div>
