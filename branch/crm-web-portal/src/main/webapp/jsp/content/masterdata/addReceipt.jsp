<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib uri="/WEB-INF/tlds/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/tlds/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/tlds/displaytag.tld" prefix="display"%>
<%@taglib uri="/WEB-INF/tlds/struts-html.tld" prefix="html"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<script src="dwr/engine.js" type="text/javascript"></script>
<script src="dwr/util.js" type="text/javascript"></script>
<script src="dwr/interface/crmDwr.js" type="text/javascript"></script>
<html>
<body>
  <div id="section" align="center">
    <html:form action="/crmRcaReason" method="post" styleId="viewReceiptCRF">
      <div class="loadingPopup hidden"></div>
      <div class="section">
        <logic:equal name="crmRcaReasonForm" property="requestPage" value="">
          <h2>Search Receipt/ CAF</h2>
        </logic:equal>
        <logic:notEqual name="crmRcaReasonForm" property="requestPage" value="">
          <logic:equal name="crmRcaReasonForm" property="returnButton" value="search">
            <h2>Search Receipt/ CAF</h2>
          </logic:equal>
        </logic:notEqual>
        <logic:equal name="crmRcaReasonForm" property="requestPage" value="addReciept">
          <logic:equal name="crmRcaReasonForm" property="returnButton" value="">
            <h2>Add Receipt</h2>
          </logic:equal>
        </logic:equal>
        <logic:equal name="crmRcaReasonForm" property="requestPage" value="addCRF">
          <logic:equal name="crmRcaReasonForm" property="returnButton" value="">
            <h2>Add CAF</h2>
          </logic:equal>
        </logic:equal>
        <div class="inner_section">
          <logic:equal name="crmRoles" property="available(create_crf_master)" value="true" scope="session">
            <p>
              <c:choose>
                <c:when test="${ not empty crmRcaReasonForm.requestPage and crmRcaReasonForm.returnButton eq 'search'}">
                  <html:link href="javascript:addReceiptPage();" styleClass="link_button2">Add Receipt</html:link>
                </c:when>
                <c:when test="${ crmRcaReasonForm.requestPage eq 'addReciept' }">
                  <html:link href="crmRcaReason.do?method=addReceiptCRFPage" styleClass="link_button2">Search Receipt</html:link>
                </c:when>
                <c:otherwise>
                  <html:link href="javascript:addReceiptPage();" styleClass="link_button2">Add Receipt</html:link>
                </c:otherwise>
              </c:choose>
              <c:choose>
                <c:when test="${ not empty crmRcaReasonForm.requestPage and crmRcaReasonForm.returnButton eq 'search'}">
                  <html:link href="javascript:addCRFPage();" styleClass="link_button">Add CAF</html:link>
                </c:when>
                <c:when test="${ crmRcaReasonForm.requestPage eq 'addCRF'}">
                  <html:link href="crmRcaReason.do?method=addReceiptCRFPage" styleClass="link_button">Search CAF</html:link>
                </c:when>
                <c:otherwise>
                  <html:link href="javascript:addCRFPage();" styleClass="link_button">Add CAF</html:link>
                </c:otherwise>
              </c:choose>
            </p>
          </logic:equal>
          <p>
            <span class="error_message"> <html:errors property="appError" />
            </span> <span class="success_message"> <logic:messagesPresent message="true" property="appMessage">
                <html:messages id="msg" message="true" property="appMessage">
                  <bean:write name="msg" />
                </html:messages>
              </logic:messagesPresent>
            </span>
          </p>
          <div class="inner_left_lead floatl ">
            <p class="floatl">
              <strong>Category</strong> <span class="dropdown"> <logic:equal name="crmRcaReasonForm" property="requestPage" value="">
                  <logic:iterate id="categoryList" name="crmRcaReasonForm" property="categoryList" indexId="ctr">
                    <logic:equal name="categoryList" property="contentName" value="Installation & Activation">
                      <span class="showTextbox"> <bean:write name="categoryList" property="contentName" />
                      </span>
                      <html:hidden styleClass="hidden" property="crmRcaReason.category" value="${categoryList.contentValue}" />
                    </logic:equal>
                  </logic:iterate>
                </logic:equal> <logic:notEqual name="crmRcaReasonForm" property="requestPage" value="">
                  <logic:iterate id="categoryList" name="crmRcaReasonForm" property="categoryList" indexId="ctr">
                    <logic:equal name="categoryList" property="contentName" value="Installation & Activation">
                      <span class="showTextbox"> <bean:write name="categoryList" property="contentName" />
                      </span>
                      <html:hidden styleClass="hidden" property="crmRcaReason.category" value="${categoryList.contentValue}" />
                    </logic:equal>
                  </logic:iterate>
                </logic:notEqual>
              </span>
            </p>
            <p class="floatl marginleft30">
              <strong>Sub Category</strong> <span class="dropdownWithoutJs"> <logic:equal name="crmRcaReasonForm" property="requestPage" value="">
                  <html:select styleId="crmRcaReasonSubCategory" name="crmRcaReasonForm" property="crmRcaReason.subCategory"
                    onchange="getSubSubCategory(this.value);">
                    <%-- <html:option value="0">Please select</html:option>
					 		<html:optionsCollection name="crmRcaReasonForm" property="subCategoryList" label="contentName" value="contentValue"/> --%>
                    <html:option value="">Please select</html:option>
                    <html:option value="CashReceipt">Cash Receipt</html:option>
                    <html:option value="CAF">CAF</html:option>
                  </html:select>
                </logic:equal> <logic:notEqual name="crmRcaReasonForm" property="requestPage" value="">
                  <logic:equal name="crmRcaReasonForm" property="returnButton" value="">
                    <html:select styleId="crmRcaReasonSubCategory" styleClass="" name="crmRcaReasonForm" property="crmRcaReason.subCategory"
                      onchange="getSubSubCategory(this.value);" disabled="true">
                      <html:option value="">Please select</html:option>
                      <html:option value="CashReceipt">Cash Receipt</html:option>
                      <html:option value="CAF">CAF</html:option>
                    </html:select>
                  </logic:equal>
                  <logic:notEqual name="crmRcaReasonForm" property="returnButton" value="">
                    <html:select styleId="crmRcaReasonSubCategory" styleClass="" name="crmRcaReasonForm" property="crmRcaReason.subCategory"
                      onchange="getSubSubCategory(this.value);">
                      <html:option value="">Please select</html:option>
                      <html:option value="CashReceipt">Cash Receipt</html:option>
                      <html:option value="CAF">CAF</html:option>
                    </html:select>
                  </logic:notEqual>
                </logic:notEqual>
              </span>
            </p>
            <p class="floatl marginleft30">
              <strong>Sub Sub Category</strong> <span class="dropdownWithoutJs"> <html:select styleId="receiptSubSubCategory" styleClass=""
                  name="crmRcaReasonForm" property="crmRcaReason.subSubCategory" onchange="changePrefix(this.value);">
				    <html:option value="Please select">Please select</html:option>
                  <%-- <logic:equal name="crmRcaReasonForm" property="requestPage" value="addReciept">
                    <logic:notEmpty name="crmRcaReasonForm" property="partnerList">
                      <html:option value="Please select">Please select</html:option>
                      <html:optionsCollection name="crmRcaReasonForm" property="partnerList" label="partnerName" value="partnerName" />
                    </logic:notEmpty>
                  </logic:equal>--%>
                  <logic:equal name="crmRcaReasonForm" property="requestPage" value="">
                   
                    <logic:equal name="crmRcaReasonForm" property="crmRcaReason.subSubCategory" value="EOC">
                    <html:option value="EOC">FTTX / FTTN</html:option>
                    </logic:equal>
                    <logic:equal name="crmRcaReasonForm" property="crmRcaReason.subSubCategory" value="T">
                    <html:option value="T">Services</html:option>
                    </logic:equal>
                  </logic:equal>
                  <logic:equal name="crmRcaReasonForm" property="requestPage" value="addCRF">
                  <%--  <html:option value="Please select">Please select</html:option>
                    <html:option value="BB">FTTX</html:option> --%>
                    <html:option value="EOC">FTTX / FTTN</html:option>
                  </logic:equal>
                  <logic:equal name="crmRcaReasonForm" property="requestPage" value="addReciept">
                    <html:option value="T">Services</html:option>
                 <%--    <html:option value="E">Telesolutions</html:option>--%>
                  </logic:equal>
                  <%-- <logic:equal name="crmRcaReasonForm" property="requestPage" value="">
                    <html:option value="Please select">Please select</html:option>
                    <html:option value="BB">Fiber Broadband/RF</html:option>
                    <html:option value="EOC">EoC/STT</html:option>
                    <html:option value="T">Teleservices</html:option>
                    <html:option value="E">Telesolutions</html:option>
                    <logic:notEmpty name="crmRcaReasonForm" property="partnerList">
                      <html:option value="Please select">Please select</html:option>
                      <html:optionsCollection name="crmRcaReasonForm" property="partnerList" label="partnerName" value="partnerName" />
                    </logic:notEmpty>
                  </logic:equal>--%>
                </html:select>
              </span>
            </p>
             <p class="floatl clear ${crmRcaReasonForm.crmRcaReason.subCategory ne 'CashReceipt' ? 'hide1' : ''}" id="prefixIdPage">
              <strong>Prefix</strong>
              <html:text styleClass="textbox" name="crmRcaReasonForm" property="prefix" styleId="prefixId_text" readonly="true"></html:text>
              <html:hidden name="crmRcaReasonForm" property="requestPage" styleId="requestPageId" />
            </p>
			<p class="floatl clear">
              <strong>Start Range</strong>
              <html:text styleClass="textbox" name="crmRcaReasonForm" property="startRangeString" maxlength="8"
                onkeyup="javascript:ValidatenReplaceAlphanumeric(this);"></html:text>
              <font></font>
            </p>
            <p class="floatl marginleft30">
              <strong>End Range</strong>
              <html:text styleClass="textbox" name="crmRcaReasonForm" property="endRangeString" maxlength="8"
                onkeyup="javascript:ValidatenReplaceAlphanumeric(this);"></html:text>
              <font></font>
            </p>
            <c:if test="${crmRcaReasonForm.requestPage eq '' or crmRcaReasonForm.returnButton eq 'search'}">
              <p class="floatl clear">
                <logic:notEmpty name="crmRcaReasonForm" property="aciveInactiveStatusList">
                  <strong>Status</strong>
                  <span class="dropdownWithoutJs"> <html:select property="status" name="crmRcaReasonForm">
                      <html:optionsCollection name="crmRcaReasonForm" property="aciveInactiveStatusList" label="contentName" value="contentValue" />
                    </html:select>
                  </span>
                </logic:notEmpty>
              </p>
              <p class="floatl marginleft30">
                <strong>Used/Unused</strong> <span class="dropdownWithoutJs"> <html:select property="usedUnusedStatus" name="crmRcaReasonForm"
                    styleId="usedUnusedStatusID">
                    <html:option value="">All</html:option>
                    <html:option value="US">Used</html:option>
                    <html:option value="UU">Unused</html:option>
                  </html:select>
                </span>
              </p>
            </c:if>
          </div>
          <div class="floatr inner_right">
            <logic:equal name="crmRoles" property="available(view_crf_master)" value="true" scope="session">
              <logic:equal name="crmRcaReasonForm" property="requestPage" value="">
                <html:link href="#" styleClass="main_button" styleId="submit_search">Search</html:link>
              </logic:equal>
            </logic:equal>
            <logic:notEqual name="crmRcaReasonForm" property="requestPage" value="">
              <logic:equal name="crmRoles" property="available(create_crf_master)" value="true" scope="session">
                <logic:equal name="crmRcaReasonForm" property="returnButton" value="">
                  <html:link href="#" styleClass="main_button" styleId="submit_viewReceipt">Submit</html:link>
                </logic:equal>
              </logic:equal>
              <logic:notEqual name="crmRcaReasonForm" property="returnButton" value="">
                <html:link href="#" styleClass="main_button" styleId="submit_search">Search</html:link>
              </logic:notEqual>
            </logic:notEqual>
          </div>
          <p class="clear"></p>
        </div>
        <!-------- display table ------------>
        <div>
          <html:hidden name="crmRcaReasonForm" property="crmRcaReason.categoryId" styleId="categoryId" />
          <html:hidden name="crmRcaReasonForm" property="crmRcaReason.status" styleId="crfReceiptStatus" />
          <html:hidden name="crmRcaReasonForm" property="crmRcaReason.categoryValue" styleId="catValue" />
          <html:hidden name="crmRcaReasonForm" property="crmRcaReason.category" styleId="catName" />
          <html:errors property="noRecordFound" />
          <logic:notEmpty name="crmRcaReasonForm" property="crmRcaReasonsList">
            <display:table id="data" name="sessionScope.crmRcaReasonForm.crmRcaReasonsList" requestURI="" class="dataTable" style="width:100%" pagesize="15">
              <display:setProperty name="paging.banner.placement" value="bottom" />
              <display:setProperty name="export.excel.filename" value="${data.category}-${data.subCategory}-${data.subSubCategory}-Report-.xls" />
              <display:setProperty name="export.csv.filename" value="${data.category}-${data.subCategory}-${data.subSubCategory}-Report.csv" />
              <display:column title="Sr.No.">
                <bean:write name="data_rowNum" />
              </display:column>
              <%-- 
				<display:column  title="Category" >
				<logic:notEmpty name="data" property="category">
					<bean:write name="crmRoles" property="displayEnum(category,${data.category})" scope="session" />
				</logic:notEmpty>
				</display:column>
				<display:column  title="Sub Category" property="subCategory"/>
				<display:column  title="Sub Sub Category" >
				<logic:notEmpty name="data" property="subSubCategory">
					<logic:equal name="data" property="subCategory" value="CRF">
						<bean:write name="crmRoles" property="displayEnum(Product,${data.subSubCategory})" scope="session" />
					 </logic:equal>
					<logic:equal name="data" property="subCategory" value="CashReceipt">
						<bean:write name="crmRoles" property="displayEnum(Partner,${data.subSubCategory})" scope="session" />
					</logic:equal>
				</logic:notEmpty>				
				</display:column>
				--%>
              <logic:equal name="data" property="subCategory" value="CashReceipt">
                <display:column title="Cash Receipt" property="categoryValue" />
              </logic:equal>
              <logic:equal name="data" property="subCategory" value="CAF">
                <display:column title="CAF Number" property="categoryValue" />
              </logic:equal>
              <display:column title="Status">
                <logic:notEmpty name="data" property="status">
                  <bean:write name="crmRoles" property="displayEnum(AciveInactiveStatus,${data.status})" scope="session" />/ 
					<bean:write name="crmRoles" property="displayEnum(AllStatus,${data.valueAlias})" scope="session" />
                </logic:notEmpty>
              </display:column>
              <logic:equal name="crmRoles" property="available(update_crf_master)" value="true" scope="session">
                <display:column title="Edit" media="html">
                  <logic:equal name="data" property="status" value="A">
                    <logic:equal name="data" property="valueAlias" value="UU">
                      <a
                        href="javascript:changeReciptStatus('<bean:write name="data" property="categoryId" />','I','<bean:write name="data" property="categoryValue" />','<bean:write name="data" property="category" />','<bean:write name="data" property="subCategory" />','<bean:write name="data" property="subSubCategory" />')">Deactivate</a>
                    </logic:equal>
                    <logic:notEqual name="data" property="valueAlias" value="UU">
                    -
                    </logic:notEqual>
                  </logic:equal>
                  <logic:equal name="data" property="status" value="I">
                    <a
                      href="javascript:changeReciptStatus('<bean:write name="data" property="categoryId" />','A','<bean:write name="data" property="categoryValue" />','<bean:write name="data" property="category" />','<bean:write name="data" property="subCategory" />','<bean:write name="data" property="subSubCategory" />')">Activate</a>
                  </logic:equal>
                </display:column>
              </logic:equal>
              <display:column title="Created By" property="createdBy" />
              <display:column title="Created Time" property="displayCreatedTime" />
            </display:table>
          </logic:notEmpty>
        </div>
      </div>
      <p class="clear"></p>
    </html:form>
  </div>
</body>
</html>
