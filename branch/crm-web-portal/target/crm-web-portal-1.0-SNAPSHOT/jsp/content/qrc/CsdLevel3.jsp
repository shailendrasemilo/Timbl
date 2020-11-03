<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page import="org.apache.struts.Globals"%>
<%@ page import="org.apache.struts.taglib.html.Constants"%>
<%@taglib uri="/WEB-INF/tlds/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/tlds/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/tlds/displaytag.tld" prefix="display"%>
<%@taglib uri="/WEB-INF/tlds/struts-html.tld" prefix="html"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<script>
	function showReason( str ){
		if ( str == 'N' ) {
			document.getElementById( 'csdCloserReason' ).style.display = "block";
		}
		else {
			document.getElementById( "csdCloserReason" ).style.display = "none";
		}
	}
</script>
<div class="loadingPopup hidden"></div>
<div id="section">
  <div class="modelPopupDiv" id="uploadDocPPId"></div>
  <div class="section">
    <h2>
      Shifting Workflow&nbsp;-&nbsp;&nbsp;<a style="color: #DEAE00;"><bean:write name='qrcForm' property='customerId' /></a>
    </h2>
    <div class="inner_section ">
      <div class="inner_left_lead floatl  qrcLeft"></div>
      <div class=" floatl manageGISRight  qrcRight">
        <div class="success_message">
          <logic:messagesPresent message="true">
            <html:messages id="message" message="true">
              <bean:write name="message" />
            </html:messages>
          </logic:messagesPresent>
        </div>
        <div class="error_message" id="errors">
          <html:errors />
        </div>
        <h4>CSD Level 3</h4>
        <div class="relative inner_left_lead">
          <html:form action="/shiftingWorkflow" method="post" styleId="ifrStage">   
             <html:hidden name='qrcForm' property='customerId' value="${qrcForm.customerId}" />
            <html:hidden name="qrcForm" property="shiftingWorkflowPojo.workflowId" />
            <html:hidden name="qrcForm" property="shiftingWorkflowPojo.shiftingId" />
            <html:hidden name="qrcForm" property="shiftingWorkflowPojo.workflowStage" />
            <html:hidden name="qrcForm" property="shiftingWorkflowPojo.currentBin" />
             <html:hidden name="qrcForm" property="shiftingWorkflowPojo.previousBin" />
              <html:hidden name="qrcForm" property="shiftingWorkflowPojo.product" />
                <html:hidden name="qrcForm" property="shiftingWorkflowPojo.previousNetwork" />
            
            <html:hidden name="qrcForm" property="shiftingWorkflowPojo.shiftingType" styleId="shiftingType" value="${qrcForm.shiftingWorkflowPojo.shiftingType}" />
           
            <div class="floatl">
            
              <p class=" floatl clear">
                    <b>Issue Resolve</b> <span class=""> 
                    <html:radio property="customerResponse" value="Y" onclick="showReason(this.value);" /> Yes <html:radio
                        property="customerResponse" value="N" onclick="showReason(this.value);" />No
                    </span>
                  </p>
                  <p class=" floatl clear" style='display: none' id="csdCloserReason">
                    <b>Closer Reason</b> <span class="LmsdropdownWithoutJs"> <html:select property="shiftingWorkflowPojo.closerReason"
                        styleId="closer_reason_Id">
                        <html:option value="">Please Select</html:option>
                        <logic:notEmpty name="qrcForm" property="closerReasonList">
                          <html:optionsCollection name="qrcForm" property="closerReasonList" label="categoryValue" value="categoryId" />
                        </logic:notEmpty>
                      </html:select><font></font>
                      <%--  <font class="errorTextbox hidden">Please select Closer Reason.</font>--%>
                    </span>
                  </p>
                  <p class="floatl clear">
                    <strong> Remarks <span class="error_message verticalalignTop">*</span></strong>
                    <html:textarea property="remarksPojo.remarks" value="" name="qrcForm" styleClass="LmsRemarkstextarea" styleId="csdl3Remarks">
                    </html:textarea><font></font>
                    <span class="hidden"><font class="errorRemarks" style="top: 98px;">Please enter Remarks between [3-4000].</font></span> <font
                      class="errorRemarks hidden" style="top: 98px;">Please enter Remarks.</font>

                  </p> <br class="clear" />
            </div>
            <div class="floatr inner_right">
              <p class="buttonPlacement">

                <a href="#" id="submit_csdStage" class="main_button " onclick="submitCSDLevel3();"><span>Submit</span></a>
              </p>
              <br class="clear" /> <br class="clear" />
            </div>
            <br class="clear" />
            <br class="clear" />
          </html:form>
          <br class="clear" />
        </div>
      </div>
      <p class="clear" />

      <div class="LmsTable marginRight10">
        <h4>
          Remarks Details <span class="lmsMinusImage floatr"></span>
        </h4>
        <div class="viewLmsTable margintop10 viewLmsLeadTable">
          <iframe src="logAction.do?method=getRemarks&mappingId=${qrcForm.shiftingWorkflowPojo.workflowId}" scrolling="no" frameborder="0" id="frame"
            style="border: none; overflow: hidden; width: 100%; height: 500px;" allowTransparency="true"> </iframe>
        </div>
      </div>

      <br class="clear" />
    </div>
  </div>
</div>