<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="/WEB-INF/tlds/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/tlds/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/tlds/displaytag.tld" prefix="display"%>
<%@taglib uri="/WEB-INF/tlds/struts-html.tld" prefix="html"%> 
<%@taglib uri="/WEB-INF/tlds/struts-logic.tld" prefix="logic"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<div id="section" >
  <form action=""  method="post">
   
  	<div class="section">
   
    <h2>Waiver&nbsp;-&nbsp;&nbsp;<a style="color: #DEAE00;"><bean:write name='qrcForm' property='custWaiverPojo.customerId'/></a> </h2>
      <div class="inner_section ">
        <div class=" floatl manageGISRight qrcRight">
			<div class="success_message">
				 <logic:messagesPresent message="true">
				 <html:messages id="message" message="true">
				  <bean:write name="message" />
				  </html:messages>
				  </logic:messagesPresent>
			  </div>
				  <span class="error_message"> 
				  <html:errors property="appError" />
				  </span>
         <br />
          <ul class="table display mWidth900 ticketView">
          <logic:notEmpty name="qrcForm" property="custWaiverPojo" >
            <li class="table_header"> <span>View Waiver</span> </li>
            <li class="table_container">
              <label>WorkFlow ID:</label>
              <span>${ qrcForm.custWaiverPojo.workflowId }</span>
              <label>Customer ID:</label>
              <span>${ empty qrcForm.custWaiverPojo.customerId ? '-' : qrcForm.custWaiverPojo.customerId }</span>
            </li>
            <li class="table_container">
               <label>Waiver Head:</label>
              <span>${ qrcForm.custWaiverPojo.waiverHead }</span>
              <label>Waiver Type:</label>
              <span>${ qrcForm.custWaiverPojo.waiverType }</span> 
            </li>
            <li class="table_container">
              <label>Bill Number:</label>
              <span>${ qrcForm.custWaiverPojo.billNo }</span>
              <label>Waiver Amount:</label>
              <span>${ qrcForm.custWaiverPojo.waiverAmount }</span> 
            </li>
            <li class="table_container">
              <label>Initiated By:</label>
              <span> ${ qrcForm.custWaiverPojo.createdBy } </span>
              <label>Initiataion Date:</label>
              <span><bean:write name="crmRoles" property="toDate(${qrcForm.custWaiverPojo.createdTime })"/></span> 
            </li>
            <li class="table_container">
             <label>WorkFlow Stage:</label>
              <span><bean:write name="crmRoles" property="displayEnum(functionalBins,${qrcForm.custWaiverPojo.workflowStage})" scope="session"/></span>
              <label>Status:</label>
              <span><bean:write name="crmRoles" property="displayEnum(WaiverStatus,${qrcForm.custWaiverPojo.status})" scope="session"/></span>
            </li>
            <c:if test="${ not empty qrcForm.custWaiverPojo.approvedBy }">
            <li class="table_container">
              <label>Approved By:</label>
              <span>${ qrcForm.custWaiverPojo.approvedBy }</span> 
              <label>Final Approved By:</label>
              <span>${ qrcForm.custWaiverPojo.finalApprovedBy }</span>
            </li>
            </c:if>
            <c:if test="${ not empty qrcForm.custWaiverPojo.rejectedBy }">
            <li class="table_container">
              <label>Rejected By :</label>
              <span>${ qrcForm.custWaiverPojo.rejectedBy }</span> 
                <label>Rejected Reason:</label>
              <span>${ qrcForm.custWaiverPojo.rejectionReason }</span>
            </li>
           </c:if>
         </logic:notEmpty>
          </ul>
    	<p class="floatl clear"></p>
    	 
          </div>
       <p class="clear"></p>
        </div>
        <!-- end -->
        <p class="clear"></p>
    <div class="LmsTable marginRight10">
	  <h4>Remarks Details <span class="lmsMinusImage floatr"></span></h4>
     <div class="viewLmsTable margintop10 viewLmsLeadTable">
	<iframe src="logAction.do?method=getRemarks&mappingId=${ qrcForm.custWaiverPojo.workflowId }" scrolling="no" frameborder="0" id="frame"
    	style="border: none; overflow: hidden;width:100%; height: auto;" allowTransparency="true">
    </iframe>
	</div>
    </div>
    </div>
  </form>
</div>
