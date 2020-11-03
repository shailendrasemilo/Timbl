<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib uri="/WEB-INF/tlds/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/tlds/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/tlds/displaytag.tld" prefix="display"%>
<%@taglib uri="/WEB-INF/tlds/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/tlds/struts-logic.tld" prefix="logic"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div id="section">
  <form action="" method="post" id="searchGIS">
    <div class="section">
      <h2>Ticket View</h2>
      <div class="marginRight10" style="margin-left: -1px;">
        <h4>
          Ticket History Details <span class="lmsMinusImageRemarks floatr"></span>
        </h4>
        <div class="viewticketRemarks viewLmsLeadTable">
          <iframe src="logAction.do?method=getTicketHistory&mappingId=${ qrcForm.srTicketPojo.srId }" scrolling="yes" frameborder="0" id="frame"
            style="border: none; overflow: hidden; width: 100%; height: 265px;" allowTransparency="true"> </iframe>
        </div>
      </div>
      <div class="inner_section ">
        <div class="success_message">
          <logic:messagesPresent message="true">
            <html:messages id="message" message="true">
              <bean:write name="message" />
            </html:messages>
          </logic:messagesPresent>
        </div>
        <span class="error_message"> <html:errors property="appError" />
        </span> <br />
        <ul class="table display mWidth900 ticketView">
          <logic:notEmpty name="qrcForm" property="srTicketPojo">
          	<logic:equal name="qrcForm" property="srTicketPojo.moduleType" value="QRC">
            	<jsp:include page="ticketDiv.jsp"></jsp:include>
            </logic:equal>
            <logic:equal name="qrcForm" property="srTicketPojo.moduleType" value="LMS">
           		 <jsp:include page="lmsTicketDiv.jsp"></jsp:include>
            </logic:equal>
          </logic:notEmpty>
         
        </ul>
         <c:if test="${qrcForm.srTicketPojo.status eq 'R' or qrcForm.srTicketPojo.status eq 'C'}">
          <h3 class="marginleft20 margintop10">View Documents</h3>         
          <c:url value="${initParam.dmshost }/np-document-upload/files/view/QRC/${qrcForm.srTicketPojo.srId }" var="dms_view"/>
          <iframe src='${dms_view}' scrolling='yes' style='border: 1px solid #ccc; overflow: hidden; width: 100%; height: 600px;'></iframe><%-- &SystemTime=${now}&DocType= --%>
          </c:if>
        <p class="clear"></p>
        <logic:equal name="crmRoles" property="available(update_qrc_tkt)" value="true" scope="session">
          <logic:equal name="qrcForm" property="inType" value="customerProfile">
            <logic:notEqual name="qrcForm" property="srTicketPojo.status" value="C">
              <div class="floatl clear">
                <br />
                <html:hidden name="qrcForm" property="ticketHistory.action" value="SR" styleId="actioId"/>
                <html:hidden name="qrcForm" property="ticketHistory.createdBy" value="${sessionScope.userPojo.userId}" />
                <html:hidden name="qrcForm" property="ticketHistory.ticketId" value="${qrcForm.srTicketPojo.srId}" />
				 <c:if test="${qrcForm.srTicketPojo.status ne 'R'}">
				
                <p class="floatl clear">
                  <strong> Remarks <span class="error_message">*</span></strong>
                </p>
                <p style="margin-top: 0px;">
                  <html:textarea name="qrcForm" cols="27" rows="38" styleClass="textareaheight80" property="ticketHistory.remarks" value="" styleId="remarks"></html:textarea>
                  <span class="hidden"><font class="errorRemarks" style="top:120px;">Please enter Remarks between [2-4000].</font></span>
					<font class="errorRemarks hidden" style="top:120px;">Please enter Remarks.</font>
                </p> 
				</c:if>
				
              </div>
              <p class="clear"></p>
              <div id="remarksSection" class="floatr inner_right">
                <p class="buttonPlacement">
                  <c:if test="${qrcForm.visibileButton and qrcForm.srTicketPojo.status ne 'R'}">
                    <a href="javascript:modifyFollowUpDate();" id="modify_ticket" class="main_button_multiple"> <span>Modify Ticket</span>
                    </a>
                  </c:if>
				  <c:if test="${qrcForm.srTicketPojo.status ne 'R'}">
                  <a href="javascript:saveTicketRemarks('view')" id="submit_QRC" class="main_button_n"><span>Save Remarks</span></a>
                  <a href="javascript:saveTicketFlagRemarks('view')" id="submit_QRC" class="main_button_n"><span>Save Flag</span></a>
				  </c:if>
                </p>
              </div>
            </logic:notEqual>
          </logic:equal>
        </logic:equal>
        <!-- end -->
        <p class="clear floatl"></p>
      </div>
    </div>
    <logic:notEmpty name="qrcForm" property="srTicketPojo.customerDetailsPojo">
			<html:hidden name="qrcForm" property="srTicketPojo.customerDetailsPojo.customerId" styleId="customerId" />
	</logic:notEmpty>
    <html:hidden name="qrcForm" property="srTicketPojo.functionalbinId" styleId="functionalBinId" />
    <html:hidden name="qrcForm" property="srTicketPojo.qrcSubSubCategoryId" styleId="qrcSubSubCategoryId" />
    <html:hidden name="qrcForm" property="srTicketPojo.qrcCategoryId" styleId="qrcCategoryId" />
    <html:hidden name="qrcForm" property="presentStage" styleId="srTicketPojo.functionalbinId" />
    <html:hidden name="qrcForm" property="presentOwner" styleId="${sessionScope.userPojo.userId}" />
    <html:hidden name="qrcForm" property="srTicketPojo.displayFollowupOn" styleId="followUpOn" />
    <html:hidden name="qrcForm" property="srTicketPojo.actionTakenId" styleId="rca" />
    <html:hidden name="qrcForm" property="srTicketPojo.rootCauseId" styleId="rcaReason" />
    <html:hidden name="qrcForm" property="futureStage" styleId="futureStage" />
    <html:hidden name="qrcForm" property="qrcActions" value="1" />
  </form>
</div>
