<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib uri="/WEB-INF/tlds/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/tlds/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/tlds/displaytag.tld" prefix="display"%>
<%@taglib uri="/WEB-INF/tlds/struts-html.tld" prefix="html"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div class="loadingPopup hidden"></div>
<div id="section" align="center">
  <html:form action="/qrcConfig" styleId="formConfigureSubSubCat">
    <%
        int j = 0;
    %>
    <div class="section">
      <h2>Sub Sub Category Configuration</h2>
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
        <div class="inner_left_lead floatl">
        <p class="floatl">
            <strong>Module Type<span class="error_message verticalalignTop">*</span></strong> 
            <span class="LmsdropdownWithoutJs">
	     	  <html:select property="moduleType" name="qrcConfigForm" styleId="qrcConfigModuleType" onchange="qrcCategoriesByModyleType(this.value,'IDcatQRCSUBSUBCAT');">
	     	  	<html:option value="0">Please Select</html:option>
	             <html:option value="">QRC</html:option>
	             <html:option value="LMS">LMS</html:option>
	          </html:select>   
    		</span>
           <font></font>
          </p>
          <p class="floatl marginleft30">
            <strong>Category<span class="error_message verticalalignTop">*</span></strong> <span class="qrcConfigDropdownWithoutJs"> <html:select property="qrcCategoryId" name="qrcConfigForm"
                styleId="IDcatQRCSUBSUBCAT" onchange="populateSubCategoriesforSubSubCat(this.value,'IDsubCatQRCSUBSUBCAT');">
                <html:option value="0">Please Select</html:option>
                <logic:notEmpty name="qrcConfigForm" property="qrcCategories">
                  <html:optionsCollection name="qrcConfigForm" property="qrcCategories" value="qrcCategoryId" label="qrcCategory" />
                </logic:notEmpty>
              </html:select> 
            </span><font></font>
          </p>
          <p class="floatl marginleft30">
            <strong>Sub Category<span class="error_message verticalalignTop">*</span></strong><span class="qrcConfigDropdownWithoutJs"> <html:select property="qrcSubCategoryId" name="qrcConfigForm"
                styleId="IDsubCatQRCSUBSUBCAT" onchange="getSubSubCat();">
                <html:option value="0">Please Select</html:option>
                <logic:notEmpty name="qrcConfigForm" property="qrcSubCategoriesPojos">
                  <html:optionsCollection name="qrcConfigForm" property="qrcSubCategoriesPojos" label="qrcSubCategory" value="qrcSubCategoryId" />
                </logic:notEmpty>
              </html:select> 
            </span><font></font>
          </p>
          <!-- div for Sub Sub Cat Panel STARTS -->
          <br class="clear" /> <font></font>
          <div id="role_group_view">
            <span class="addbtn_role_group"> <a href="javascript:addRowSubSubCatQRCConfig();" id="IDaddSubSubCatQRCCONFIG" class="grey_button_add"> <span>Add</span><font></font>
            </a>
            </span>
            <div class="display_group width1005">
              <div class="stays-at-top">
                <span class="heading_text" style="width: 295px;">Sub Sub Category</span> <span class="heading_text" style="width: 150px;">QRC Type</span> <span
                  class="heading_text" style="width: 150px;">Resolution Type</span> <span class="heading_text">Functional Bin</span>
              </div>              
                <ul class="add_parameter_group">
                  <div id="subCatListArea">
                  <logic:notEmpty name="qrcConfigForm" property="qrcSubSubCategoriesPojos">
                    <logic:iterate id="qrcSubSubCategoriesPojos" name="qrcConfigForm" property="qrcSubSubCategoriesPojos" indexId="ctr">
                      <li class="${ctr%2 eq '1'?'first':'second'}" style="position: relative"><logic:notEqual name="qrcSubSubCategoriesPojos"
                          property="qrcSubSubCategoryId" value="0">
                          <html:hidden name="qrcSubSubCategoriesPojos" property="qrcSubSubCategoryId" indexed="true" />
                        </logic:notEqual><%=ctr + 1%><%="."%><span> <html:text name="qrcSubSubCategoriesPojos" property="qrcSubSubCategory"
                            styleClass="QRCtextbox checkGreyText" indexed="true"
                            disabled="${qrcSubSubCategoriesPojos.modificationAllowed eq 'Y'?'false':'true'}"></html:text><font></font>
                      </span> <span class="LmsdropdownWithoutJs marginleft13"> <html:select name="qrcSubSubCategoriesPojos" styleClass="checkGreyText"
                            property="qrcType" indexed="true" disabled="${qrcSubSubCategoriesPojos.modificationAllowed eq 'Y'?'false':'true'}">
                            <html:option value="0">Please Select</html:option>
                            <logic:notEmpty name="qrcConfigForm" property="qrcTypeList">
                              <html:optionsCollection name="qrcConfigForm" property="qrcTypeList" label="contentName" value="contentValue" />
                            </logic:notEmpty>
                          </html:select>
                      </span><font></font> <span class="LmsdropdownWithoutJs marginleft13"> <html:select name="qrcSubSubCategoriesPojos"
                            styleClass="checkGreyText" property="resolutionType" indexed="true"
                            disabled="${qrcSubSubCategoriesPojos.modificationAllowed eq 'Y'?'false':'true'}">
                            <html:option value="-1">Please Select</html:option>
                            <logic:notEmpty name="qrcConfigForm" property="resolutionTypeList">
                              <html:optionsCollection name="qrcConfigForm" property="resolutionTypeList" label="contentName" value="contentValue" />
                            </logic:notEmpty>
                          </html:select>
                      </span><font></font> 
                      <span class="LmsdropdownWithoutJs marginleft13">
						<html:select name="qrcSubSubCategoriesPojos" styleClass="checkGreyText" property="functionalBinId" indexed="true" disabled="${qrcSubSubCategoriesPojos.modificationAllowed eq 'Y'?'false':'true'}">
                            <html:option value="0">Please Select</html:option>
                            <logic:notEmpty name="qrcConfigForm" property="functionalBinList">
                              <html:optionsCollection name="qrcConfigForm" property="functionalBinList" label="categoryValue" value="categoryId" />
                            </logic:notEmpty>
                          </html:select>
                      </span><font></font>
                       <span class="padding">
                        <html:radio name="qrcSubSubCategoriesPojos" styleClass="checkGreyText" property="status" value="A" indexed="true" disabled="${qrcSubSubCategoriesPojos.modificationAllowed eq 'Y'?'false':'true'}">Active</html:radio> 
                        <html:radio name="qrcSubSubCategoriesPojos" styleClass="checkGreyText" property="status" value="I" indexed="true"  disabled="${qrcSubSubCategoriesPojos.modificationAllowed eq 'Y'?'false':'true'}">Inactive</html:radio>
                      </span> 
                      <logic:equal name="qrcSubSubCategoriesPojos" property="qrcSubSubCategoryId" value="0">
                          <span class="marginleft13"> <html:link href="javascript:removeRowSubSubCatQRCConfig(${ctr})" styleClass="close">
                              <img src="images/bg/delete.png" align="absmiddle" title="delete" />
                            </html:link>
                          </span>
                        </logic:equal> 
                        <%
                          j = ctr + 1;
                        %>
                    </logic:iterate>
                  </logic:notEmpty>
                  </div>                            
              <logic:empty name="qrcConfigForm" property="qrcSubSubCategoriesPojos">
                <span class="error_message"> &nbsp;Please click on 'Add' button to add Sub Sub Categories. </span>
              </logic:empty>
              </ul>  
            </div>
          </div>
          <!-- div for Sub Sub Cat Panel ENDS -->
        </div>
        <div class="floatr inner_right">
          <logic:equal name="crmRoles" property="available(create_qrc_cnf,update_qrc_cnf)" value="true" scope="session">
            <a href="javascript:submitSubSubCatQRC(<%=j%>);" class="main_button" id="IDsubmit_QRCCONFIGSUBSUBCAT">Configure</a>
          </logic:equal>
        </div>
        <p class="clear"></p>
      </div>
    </div>
    <p class="clear"></p>
  </html:form>
</div>
