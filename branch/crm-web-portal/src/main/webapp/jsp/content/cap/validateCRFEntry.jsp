<%@page import="java.util.Date"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.text.DateFormat"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<%@taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@taglib uri="http://struts.apache.org/tags-nested" prefix="nested"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="/WEB-INF/tlds/displaytag.tld" prefix="display"%>
<head>
<link href="css/colorbox.css" rel="stylesheet" />
<script src="javascript/jquery.colorbox.js"></script>
<script>
	$( document ).ready(function(){
		$(".iframe2").colorbox({iframe:true, width:"820px", height:"450px"});
	});
</script>
</head>
<body>
  <div class="overlayDiv"></div>
  <div class="modelPopupDiv" id="downloadDocId"></div>
  <div id="section" align="center">
    <form action="/crmCap" method="post" Id="validationCRFEntry" name="validationCRFEntry">
      <html:hidden property="crfTabId" name="crmCapForm" value="${crmCapForm.crfTabId}" styleId="tabHidden" />
      <html:hidden name="crmCapForm" property="crmUserId" styleId="hiddenCRMUserId" value="${ crmCapForm.crmUserId }" />
      <html:hidden property="customerDetailsPojo.crfStage" name="crmCapForm" value="${crmCapForm.customerDetailsPojo.crfStage}" styleId="stageId" />
      <html:hidden property="customerDetailsPojo.recordId" name="crmCapForm" value="${crmCapForm.customerDetailsPojo.recordId}" styleId="hiddenRecordId" />
      <input type="hidden" value="<bean:write name="crmRoles" property="toDate(${crmCapForm.customerDetailsPojo.activationDate})"/>" id="activationDateID" />
      <html:hidden name="crmCapForm" property="customerDetailsPojo.serviceType" value="${crmCapForm.customerDetailsPojo.serviceType}" styleId="serviceTypeID" />
      <html:hidden name="crmCapForm" property="customerDetailsPojo.product" value="${crmCapForm.customerDetailsPojo.product}" styleId="productID" />
      <html:hidden name="crmCapForm" property="customerDetailsPojo.customerId" value="${crmCapForm.customerDetailsPojo.customerId}" />
      <div class="section">
        <h2>Validation of CAF Entry</h2>
        <div class="inner_section marginBottom30">
          <!-- Success Messages Starts -->
          <div class="success_message">
            <logic:messagesPresent message="true">
              <html:messages id="message" message="true">
                <bean:write name="message" />
              </html:messages>
            </logic:messagesPresent>
          </div>
          <!-- Success Messages Ends -->
          <div class="error_message">
            <html:errors property="appError" />
          </div>
          <!-- for error messages -->
          <!-- validation form enteries -->
          <div class="relative">
            <div class="clear marginleft10 inner_left_lead floatl">
              <%
                  DateFormat dateFormat = new SimpleDateFormat( "HH:mm:ss" );
              %>
              <%
                  boolean showValue = false;
              %>
              <a href="#"
                onclick="downloadDocuments('${initParam.dmshost }/np-document-upload/files/latest/','INA','<bean:write name='crmCapForm' property='customerDetailsPojo.crfId'/>','<%=dateFormat.format( new Date() )%>');"
                class=" floatl yellow_button">View Documents</a>
              <logic:equal name="crmCapForm" property="customerDetailsPojo.crfStage" value="FTSR">
                <p class="floatl clear">
                  <strong>Action </strong> <LABEL class="label_radio" for="approveValidationCRF"> <html:radio name="crmCapForm"
                      property="remarksPojo.actions" value="Approve" styleId="approveValidationCRF">Approve</html:radio>
                  </LABEL>
              </logic:equal>
              <logic:equal name="crmCapForm" property="customerDetailsPojo.crfStage" value="FT">
                <p class="floatl clear">
                  <strong>Action </strong> <LABEL class="label_radio" for="approveValidationCRF"> <html:radio name="crmCapForm"
                      property="remarksPojo.actions" value="Approve" styleId="approveValidationCRF">Approve</html:radio>
                  </LABEL> <LABEL class="label_radio" for="rejectValidationCRF"> <html:radio name="crmCapForm" property="remarksPojo.actions" value="Reject"
                      styleId="rejectValidationCRF">Reject</html:radio>
                  </LABEL>
              </logic:equal>
              <logic:equal name="crmCapForm" property="customerDetailsPojo.crfStage" value="NPR">
                <p class="floatl clear">
                  <strong>Action </strong> <LABEL class="label_radio" for="approveValidationCRF"> <html:radio name="crmCapForm"
                      property="remarksPojo.actions" value="Approve" styleId="approveValidationCRF">Change Network Partner</html:radio>
                  </LABEL>
              </logic:equal>
              <%-- <logic:notEqual name="crmCapForm" property="customerDetailsPojo.crfStage" value="FT">
    	<LABEL class="label_radio" for="rejectValidationCRF">
     		<html:radio name="crmCapForm" property="remarksPojo.actions" value="Reject" styleId="rejectValidationCRF">Close</html:radio>
    	</LABEL>
    </logic:notEqual> --%>
              <%-- <logic:notEmpty name="crmCapForm" property="displayISRDate">
    	<LABEL class="label_radio">
     		<html:radio name="crmCapForm" property="remarksPojo.actions" value="Approve" styleId="approveId">Approve</html:radio>
     	</LABEL>
    </logic:notEmpty> --%>
              </p>
              <c:if test="${ (crmCapForm.customerDetailsPojo.crfStage eq 'FT') || (crmCapForm.customerDetailsPojo.crfStage eq 'NPR')  }">
                <p class=" hide1 floatl marginleft30" id="approveValidationCRFShow">
                  <strong>Network Partner</strong>
                  <logic:notEmpty name="crmCapForm" property="networkPartnerList">
                    <span class="dropdownWithoutJs"> <html:select name="crmCapForm" property="customerDetailsPojo.npId" styleId="partnerNameId">
                        <html:option value="">Please Select</html:option>
                        <html:optionsCollection name="crmCapForm" property="networkPartnerList" label="partnerName" value="partnerId" />
                      </html:select>
                    </span>
                    <font></font>
                  </logic:notEmpty>
                  <logic:empty name="crmCapForm" property="networkPartnerList">
   				No Network Partner Available.
   			</logic:empty>
                </p>
              </c:if>
              <p class=" hide1 floatl marginleft30" id="rejectValidationCRFShow">
                <logic:equal name="crmCapForm" property="customerDetailsPojo.crfStage" value="FT">
                  <strong>Reason for Rejection</strong>
                  <span class="dropdownWithoutJs"> <logic:notEmpty name="crmCapForm" property="rejectionReasonList">
                      <html:select name="crmCapForm" property="remarksPojo.reasonId" styleId="reasonForRejection">
                        <html:option value="0">Please Select</html:option>
                        <html:optionsCollection name="crmCapForm" property="rejectionReasonList" label="categoryValue" value="categoryId" />
                      </html:select>
                    </logic:notEmpty>
                  </span>
                  <font></font>
                </logic:equal>
              </p>
              <p class="floatl clear">
                <strong>Remarks</strong>
                <html:textarea styleClass="LmsRemarkstextarea" name="crmCapForm" property="remarksPojo.remarks" styleId="commentsValidationCRF"></html:textarea>
                <font id="errorCommentsValidationCRF"></font>
              </p>
              <logic:notEqual name="crmCapForm" property="customerDetailsPojo.crfStage" value="NPR">
                <logic:notEqual name="crmCapForm" property="customerDetailsPojo.crfStage" value="SPBP">
                  <p class="floatl clear paddingTop10">
                    <strong>CAF Reference Number</strong> <span class="relative"> <html:text styleClass="textbox" name="crmCapForm"
                        property="customerDetailsPojo.crfReferenceNo" maxlength="45" styleId="crfReferenceId"></html:text><font id="errorcrfReferenceNo"
                      class='errorRadio'></font>
                    </span>
                    <logic:equal name="crmRoles" property="available(update_ina)" value="true" scope="session">
                      <a href="#" id="saveCRFReferenceId" class="marginleft15 yellow_button">Save</a>
                    </logic:equal>
                  </p>
                </logic:notEqual>
              </logic:notEqual>
              <logic:equal name="crmCapForm" property="customerDetailsPojo.crfStage" value="FTSR">
                <%-- ISR Reference Number --%>
                <p class="floatl clear paddingTop10">
                  <strong>ISR Reference No.</strong> <span class="relative"> <html:text styleClass="textbox" name="crmCapForm"
                      property="customerDetailsPojo.isrReferenceNo" maxlength="45" styleId="isrReferenceId"></html:text> <font id="errorISRReferenceNo"
                    class='errorRadio'></font>
                  </span>
                  <logic:equal name="crmRoles" property="available(update_ina)" value="true" scope="session">
                    <a href="#" id="saveISRReferenceId" class="yellow_button marginleft15">Save</a>
                  </logic:equal>
                </p>
                <logic:notEmpty name="crmCapForm" property="displayISRDate">
                  <p class="floatl clear">
                    <strong>Date on ISR</strong> <span class="relative"> <html:text styleClass="tcal tcalInput" readonly="true" styleId="ISRDate"
                        name="crmCapForm" property="displayISRDate"></html:text><font></font>
                    </span>
                  </p>
                </logic:notEmpty>
              </logic:equal>
            </div>
            <div class="floatr inner_right"> 
              <c:if test="${ (crmCapForm.customerDetailsPojo.product eq 'BB' && crmCapForm.customerDetailsPojo.serviceType eq 'PO') }">
                <a href="crmCap.do?method=getCustomerBySociety&State=${crmCapForm.state}&City=${crmCapForm.city}&Area=${crmCapForm.area}&Locality=${crmCapForm.locality}" id="validatePOPUpForCust" class="marginright20 main_button iframe2"><span>Validate</span> </a>
                <%
                    showValue = true;
                %>
              </c:if>
              <logic:equal name="crmRoles" property="available(update_ina,delete_ina)" value="true" scope="session">
                <a href="#" id="submit_validationCRFEntryFT" class="<%=showValue ? "hide1" : " "%> main_button marginleft10"><span>Submit</span></a>
              </logic:equal>
            </div>
            <br class="clear" />
          </div>
          <!-- validation form enteries -->
          <!-- include table -->
          <div id="contentDiv"></div>
          <!-- include table -->
        </div>
        <div class="LmsTable">
          <h4>
            Customer Details: <span class="lmsMinusImage floatr"></span>
          </h4>
          <div class="viewLmsTable margintop10 viewLmsLeadTable">
            <jsp:include page="/jsp/content/cap/view-InA.jsp"></jsp:include>
          </div>
        </div>
        <div class="LmsTable">
          <h4>
            Remarks Details <span class="lmsMinusImage floatr"></span>
          </h4>
          <div class="viewLmsTable margintop10 viewLmsLeadTable">
            <iframe src="logAction.do?method=getRemarks&mappingId=${crmCapForm.customerDetailsPojo.recordId}" scrolling="no" frameborder="0" id="frame"
              style="border: none; overflow: hidden; width: 100%; height: 500px;" allowTransparency="true"> </iframe>
          </div>
          
          <h4>Activity Log Details <span class="lmsMinusImage floatr"></span></h4>
			  <div class="viewLmsTable margintop10">
			 <iframe src="jsp/content/masterdata/displayAuditLog.jsp" frameborder="0" scrolling="yes"
                  style="border: none; overflow: hidden; width: 100%; height:250px;" allowTransparency="true" onload="iframeLoaded(this)"></iframe>
			 </div>          
        </div>
      </div>
    </form>
  </div>
</body>
</html>
