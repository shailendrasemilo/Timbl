<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib uri="/WEB-INF/tlds/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/tlds/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/tlds/displaytag.tld" prefix="display"%>
<%@taglib uri="/WEB-INF/tlds/struts-html.tld" prefix="html"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div id="section" align="center">
  <html:form action="/qrcConfig" styleId="formResolutionCodeConfig">
  <div class="loadingPopup hidden"></div>
    <div class="section">
      <h2>Root Cause</h2>
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
            <strong>Category<span class="error_message verticalalignTop">*</span></strong> <span class="qrcConfigDropdownWithoutJs"> <html:select property="qrcCategoryId" name="qrcConfigForm"
                styleId="resCodeCatId" onchange="fillRCAListResCode(this.value, $('#resCodeService').val(), 'resCodeRcaId');">
                <html:option value="0">Please Select</html:option>
                <html:optionsCollection name="qrcConfigForm" property="qrcCategories" value="qrcCategoryId" label="qrcCategory" />
              </html:select> <font class="errorTextbox hidden">Please select Category.</font>
            </span>
          </p>
          <p class="floatl marginleft30">
            <strong>Service Name<span class="error_message verticalalignTop">*</span></strong> <span class="qrcConfigDropdownWithoutJs"> <html:select property="serviceName" name="qrcConfigForm"
                styleId="resCodeService" onchange="fillRCAListResCode($('#resCodeCatId').val(), this.value, 'resCodeRcaId');">
                <html:option value="">Please Select</html:option>
                <html:optionsCollection property="productTypes" name="qrcConfigForm" value="contentValue" label="contentName" />
              </html:select> <font class="errorTextbox hidden">Please select Service Name.</font>
            </span>
          </p>
          <p class="floatl marginleft30">
            <strong>Action Taken<span class="error_message verticalalignTop">*</span></strong><span class="qrcConfigDropdownWithoutJs"> 
            <html:select property="qrcRcaId" name="qrcConfigForm"
                styleId="resCodeRcaId" onchange="getResolutionCodes(this.value);">
                <html:option value="0">Please Select</html:option>
                <logic:notEmpty name="qrcConfigForm" property="qrcActionTakenPojos">
                  <html:optionsCollection name="qrcConfigForm" property="qrcActionTakenPojos" label="actionTaken" value="actionId" />
                </logic:notEmpty>
              </html:select> <font class="errorTextbox hidden">Please select Action Taken.</font>
            </span>
          </p>
          <br class="clear" />
          <div id="role_group_view1" class="relative margintop20" style="width: 745px;">
            <span class="addbtn_role_group"><a href="javascript:void(0)" id="addRowResolutionCode" class="grey_button_add"><span>Add</span></a></span>
            <div class="display_group" style="height: 250px;">
              <div class="stays-at-top">
                <span class="heading_text"> Root Cause</span>
				 <span  class="heading_text" style="margin-left:190px;"> Attributed To</span>
              </div>
              <logic:empty name="qrcConfigForm" property="qrcRootCausePojos">
                <span class="error_message"> &nbsp;Please click on 'Add' button to add Root Cause. </span>
              </logic:empty>
              <logic:notEmpty name="qrcConfigForm" property="qrcRootCausePojos">
                <div id="resolutionCodeList">
                  <ul class="add_rcaValue">
                    <c:forEach items="${ qrcConfigForm.qrcRootCausePojos }" var="qrcRootCausePojos" varStatus="current">
                      <li class="${ current.index mod 2 eq 0 ? 'first' : 'second' }"><span class="manageRcaReason floatl"> <html:checkbox
                            name="qrcRootCausePojos" property="editable" value="true" indexed="true"
                            onclick="${ qrcRootCausePojos.editable ? ( qrcRootCausePojos.rootCauseId gt 0 ? 'toggleEditableResCode('.concat(current.index).concat(');') : 'return false;' ) : 'toggleEditableResCode('.concat(current.index).concat(');') }"></html:checkbox>
                      </span> <span class=""> <html:text styleClass="textbox" name="qrcRootCausePojos" property="rootCause"
                            indexed="true" readonly="${ qrcRootCausePojos.editable ? 'false' : 'true' }" maxlength="128"></html:text>
							
							
							
                      </span>
<span>
<html:text styleClass="textbox" name="qrcRootCausePojos" property="attributedTo"
                            indexed="true" readonly="${ qrcRootCausePojos.editable ? 'false' : 'true' }" maxlength="128"></html:text>
</span>
					  <span class="marginleft13"> <label><html:radio name="qrcRootCausePojos" property="status" value="A"
                              indexed="true" onclick="${ qrcRootCausePojos.editable ? '' : 'return false;' }">Active</html:radio></label> <label><html:radio
                              name="qrcRootCausePojos" property="status" value="I" indexed="true"
                              onclick="${ qrcRootCausePojos.editable ? '' : 'return false;' }">Inactive</html:radio></label>
                      </span> <c:if test="${ qrcRootCausePojos.editable and (qrcRootCausePojos.rootCauseId eq 0) }">
                          <span class="marginleft13"> <html:link href="javascript:removeResolutionCodeRow(${ current.index })"
                              styleClass="close">
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
        
          <a href="javascript:qrcConfigResCode(<%=j %>)" class="main_button" >Configure</a>
          </logic:equal>
        </div>
        <p class="clear"></p>
      </div>
    </div>
    <p class="clear"></p>
  </html:form>
</div>

