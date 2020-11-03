<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib uri="/WEB-INF/tlds/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/tlds/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/tlds/struts-logic.tld" prefix="logic"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<div id="section">
  <div class="section">
    <h2>POD File Upload</h2>
    <div class="inner_section ">
      <div class="error_message" id="error">
        <html:errors />
      </div>
      <html:form action="/refund" method="post" styleId="ID_POD" enctype="multipart/form-data">
        <div class="QRC marginleft10">
          	<p class="floatl clear">
	            <strong>Selected File<span class="error_message verticalalignTop">*</span></strong>
	            <span id="inputFileName" class="selected_File"><bean:message key="error.gis.formFile" /></span> <br>
	            <br>
	            <span class="import inlineBlock"> <html:file property="formFile" name="financeForm" styleId="fileUpload" styleClass="file_Upload" onchange="javascript:displayFileName();"></html:file>
	            	<a href="#" id="import_fileUpload" class="import_file_Upload"><span><bean:message key="file.upload.select" /></span></a>
	            </span>
				<span class="import inlineBlock marginleft10">
	        		<a href="CRMExcelFormats/POD_feed.xlsx" class="download_Format"><bean:message key="file.download.format" /></a>
	        	</span>
          	</p>
          	<p class="serverSideMessage_upload_gis floatl marginleft30" style="z-index: 10;">
	          <span class="success_message_upload_gis"> 
			      <logic:messagesPresent message="true" property="PODStatus">
				          <bean:message key="file.uploaded.successfully" />
				          <br>
				          <bean:message key="file.upload.status1" />
				          <br>
				          <html:messages id="uploadPODMsg" property="PODStatus" message="true">
				          <bean:write name="uploadPODMsg" />
				          <br>
				          </html:messages>
				          <logic:messagesPresent message="true" property="displayErrorFile">
					           <bean:message key="file.upload.status5" />
					           <% String fileName = (String) request.getAttribute( "fileName" );%>
					           <a href="PODUpload/<%=fileName%>"><bean:message key="file.download.error" /></a>
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
          
          
          <div class="floatr">
	          <p class="buttonPlacement">
	          	<a href="#" id="submit_POD" class="main_button"><span>Upload </span></a>
	          </p>
         </div>
         </div>
       </html:form>
      <p class="clear" style="height: 250px;"></p>
    </div>
  </div>
</div>
<p class="clear"/>