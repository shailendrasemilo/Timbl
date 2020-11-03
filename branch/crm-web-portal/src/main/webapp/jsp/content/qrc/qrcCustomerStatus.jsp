<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib uri="/WEB-INF/tlds/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/tlds/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/tlds/struts-logic.tld" prefix="logic"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<div class="loadingPopup hidden"></div>
<div id="section">
  <div class="section">
    <h2>Manage Status</h2>
    <div class="inner_section ">
      <div class="error_message" id="error">
        <html:errors />
      </div>
      <html:form action="/customerStatus" method="post" styleId="IDManageStatus" enctype="multipart/form-data">
        <div class="QRC marginleft10">
          	<p class="floatl clear">
	            <strong>Select Status</strong>
	             <c:forEach var="status" items="${qrcForm.customerStatusList}">
					<c:if test="${status.contentValue ne 'A'}">
		              <html:radio property="custDetailsPojo.status" value="${status.contentValue}" name="qrcForm">${status.contentName}</html:radio>
					</c:if>
  				 </c:forEach>
	              <font class="errorTextbox hidden whitelistType">Please select</font>
          	</p>
          	
          	<p class="floatl clear">
	            <strong>Selected File</strong>
	            <span id="inputFileName" class="selected_File"><bean:message key="error.gis.formFile" /></span> <br>
	            <br>
	            <span class="import inlineBlock"> <html:file property="whitelistExcelFile" name="qrcForm" styleId="fileUpload" styleClass="file_Upload" onchange="javascript:displayFileName();"></html:file>
	            	<a href="#" id="import_fileUpload" class="import_file_Upload"><span><bean:message key="file.upload.select" /></span></a>
	            </span>
				<span class="import inlineBlock marginleft10">
	        		<a href="CRMExcelFormats/QRC_Customers_Status.xlsx" class="download_Format"><bean:message key="file.download.format" /></a>
	        	</span>
          	</p>
          	<p class="serverSideMessage_upload_gis floatl marginleft30" style="z-index: 10;">
	          <span class="success_message_upload_gis"> 
			      <logic:messagesPresent message="true" property="uploadStatus">
				          <bean:message key="file.uploaded.successfully" />
				          <br>
				          <bean:message key="file.upload.status1" />
				          <br>
				          <html:messages id="uploadStatusMsg" property="uploadStatus" message="true">
				          <bean:write name="uploadStatusMsg" />
				          <br>
				          </html:messages>
				          <logic:messagesPresent message="true" property="displayErrorFile">
					           <bean:message key="file.upload.status5" />
					           <% String fileName = (String) request.getAttribute( "fileName" );%>
					           <a href="CustomerStatusUpload/<%=fileName%>"><bean:message key="file.download.error" /></a>
				           </logic:messagesPresent>
			         </logic:messagesPresent>
		         </span>
			     <span class="error_message_upload_gis">
					 <logic:messagesPresent message="true" property="invalidHeader">
			            <bean:message key="file.uploaded.successfully" />
			            <br>
			            <bean:message key="file.upload.status1" />
			            <br>
			            <html:messages id="invalidHeaderMsg" property="invalidHeader" message="true">
			            <bean:write name="invalidHeaderMsg" />
			            <br>
			           </html:messages>
			           </logic:messagesPresent>
		         </span>
          </p>
           <p class="floatl marginleft60">
          </p>
          <p class="clear floatl">
	          <strong> Reason </strong>
	          <span class="dropdownWithoutJs"> 
		          <html:select property="exceptionReason" name="qrcForm" styleId="IDManageStatusReasons">
			          <html:option value="0">Please Select</html:option>
			          <logic:notEmpty name="qrcForm" property="crmRcaReasonPojos">
					    <html:optionsCollection name="qrcForm" property="crmRcaReasonPojos" label="categoryValue" value="categoryId"/>
			          </logic:notEmpty>
		          </html:select> <font class="errorTextbox hidden ">Please select reason.</font>
	          </span>
          </p>
          <p class="floatl clear">
	          <strong> Remarks</strong>
	          <html:textarea property="remarksPojo.remarks" name="qrcForm" styleClass="LmsRemarkstextarea" styleId="IDManageStatusRemarks"/>
	          <font class="errorRemarks hidden">Please enter Remarks between [2-4000].</font>
          </p>
          <div class="floatr">
	          <p class="buttonPlacement">
	          <logic:equal name="crmRoles" property="available(create_qrc_cs,update_qrc_cs)" value="true" scope="session">
	          <a href="#" id="submit_bulk_status" class="main_button"><span>Upload</span></a>
	          </logic:equal>
	          </p>
         </div>
         </div>
       </html:form>
      <p class="clear" style="height: 250px;"></p>
    </div>
  </div>
</div>
<p class="clear"/>