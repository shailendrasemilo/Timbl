<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="/WEB-INF/tlds/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/tlds/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/tlds/displaytag.tld" prefix="display"%>
<%@taglib uri="/WEB-INF/tlds/struts-html.tld" prefix="html"%> 
<%@taglib uri="/WEB-INF/tlds/struts-logic.tld" prefix="logic"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<div class="loadingPopup hidden"></div>
<div id="section" >
  <form action="/waiver"  method="post" id="modifyWaiverForm">
  	<div class="section">
   
    <h2>Waiver&nbsp;-&nbsp;&nbsp;<a style="color: #DEAE00;"><bean:write name='qrcForm' property='custWaiverPojo.customerId'/></a></h2>
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
            <li class="table_header"> <span>Modify Waiver</span> </li>
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
              <label>Initiated Time:</label>
              <span><bean:write name="crmRoles" property="xmlDate(${qrcForm.custWaiverPojo.createdTime })"/></span> 
            </li>
            <li class="table_container">
             <label>WorkFlow Stage:</label>
              <span><bean:write name="crmRoles" property="displayEnum(functionalBins,${qrcForm.custWaiverPojo.workflowStage})" scope="session"/></span>
              <label>Status:</label>
              <span><bean:write name="crmRoles" property="displayEnum(WaiverStatus,${qrcForm.presentStage})" scope="session"/></span>
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
    	 <div id="waiverAction">
		 	<div class="floatl">
	        <p style="margin-top:0px;">&nbsp;</p>  <p style="margin-top:0px;">&nbsp;</p>
	         <p style="margin-top:0px;" id="waiveraction">
			    <html:radio styleId="approved" name="qrcForm" property="custWaiverPojo.status" value="WA" onclick="javascript:document.getElementById('rejectReason').style.display='none';">   Approve 
		        </html:radio>
		         <html:radio styleId="reject" name="qrcForm"  property="custWaiverPojo.status" value="RJ" onclick="javascript:document.getElementById('rejectReason').style.display='block';">Reject
		         </html:radio> 
		         &nbsp; &nbsp;
                 <font></font>
	         </p>
	        </div>
	        <div class="floatl marginleft30" id="rejectReason" style="display:none;">
			 <p style="margin-top:0px;">&nbsp;</p>  <p style="margin-top:0px;">&nbsp;</p>
	         <p style="margin-top:0px;">  
			 <span class="dropdownWithoutJs">
			 <html:select name="qrcForm"  property="custWaiverPojo.rejectionReason" styleId="rejectionReason">
			 <html:option value="">Please Select Reason.</html:option>
			 <html:optionsCollection name="qrcForm" property="rejectionReasons" label="categoryValue" value="categoryValue" />
			</html:select>
			<font style="margin:6px !important";></font>
			  </span>
	          </p>
	          </div>
          </div>
       	
          <div class="floatl clear">
            <br />
             <p class="floatl clear"><strong> Remarks <span class="error_message">*</span></strong></p>
              <p style="margin-top:0px;">
              <html:textarea name="qrcForm" cols="27" rows="38" styleClass="textareaheight80" property="remarksPojo.remarks" styleId="remarks"></html:textarea>
              <font style="margin-top: 0px;"></font>
              </p>
          </div>
		  <div class="floatr inner_right">
            <p class="buttonPlacement"> 
            <logic:equal name="crmRoles" property="available(update_qrc_adj)" value="true" scope="session">
            <a href="javascript:void(0);" id="submitmodifywaiver" class="main_button"><span>Submit</span></a> 
            </logic:equal>
            </p>
         </div>
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
    </div>
  </form>
</div>
