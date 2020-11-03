<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<%@taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<script src="dwr/engine.js" type="text/javascript"></script>
<script src="dwr/util.js" type="text/javascript"></script>
<script src="dwr/interface/crmDwr.js" type="text/javascript"></script>



<div id="section" >
  <html:form action="/gis" method="post" styleId="IDuploadGis" enctype="multipart/form-data">
    <div class="section">
      <h2>GIS File Upload</h2><P class="error_message"><html:errors property="gisError"/></P>
      
      <div class="inner_section">
        <div class="inner_left_lead floatl  marginleft10">
        <html:hidden property="fileTypeId" styleId="fileType" value="gis"/>
          <%-- <p class="floatl clear"><strong>Network Partner</strong>  
    <span class="dropdownWithoutJs">
    		<html:select property="partnerId" styleId="partnerId" onchange="getProductById(this.value,'productName',false)"> 
    			<html:option value="0">Please Select</html:option>
    			<logic:notEmpty name="gisForm" property="partnerList">
					<html:optionsCollection name="gisForm"  property="partnerList" label="partnerName" value="partnerId"/>
				</logic:notEmpty> 		
			</html:select>  
        </span> </p> --%>
		<%-- <p class="floatl marginleft60"><strong>Registered Services</strong>  
    <span class="showListFunctionalBin showListFunctionalBinHeight60">
    	<html:select property="productArray" styleId="productName" multiple='true'>
    		<logic:notEmpty name="gisForm" property="productList">
 				<html:optionsCollection name="gisForm" property="productList" label="contentName" value="contentValue"/>
 			</logic:notEmpty>
 		</html:select>
		</span></p> --%>
		
        <p  class="floatl clear"><strong>Selected File</strong>
        <span id="inputFileName" class="selected_File"><bean:message key="error.gis.formFile" /></span>
		<br> <br>
		 <span class="import">
            	<input type="file" name="formFile" id="fileUpload" class="file_Upload" onchange="javascript:displayFileName();"/>
            	<a href="#" id="import_fileUpload" class="import_file_Upload"><span><bean:message key="file.upload.select" /></span></a>
          </span>
            <br> <br>
		 <span class="import">
        	<a href="CRMExcelFormats/GIS_Data_Feed.xlsx" class="download_Format"><bean:message key="file.download.format" /></a>
        	</span>
		</p>
        </div>
        	<div class="floatm middle_middle ">
        		<p class="serverSideMessage_upload_gis">
					<span class="success_message_upload_gis"> 
						<logic:messagesPresent message="true" property="uploadStatus">
							<bean:message key="file.uploaded.successfully" />
							<br>
							<bean:message key="file.upload.status1" />
							<br>
							<html:messages id="uploadStatusMsg" property="uploadStatus"
								message="true">
								<bean:write name="uploadStatusMsg" />
								<br>
							</html:messages>
							<logic:messagesPresent message="true" property="displayErrorFile">
								<bean:message key="file.upload.status5" />
								<%String fileName=(String)request.getAttribute("fileName");%>
								<a href="GisExcelUpload/<%=fileName%>"><bean:message key="file.download.error" /></a>
							</logic:messagesPresent>
						</logic:messagesPresent>
					</span>
					<span class="error_message_upload_gis"> 
						<logic:messagesPresent message="true" property="invalidHeader">
							<bean:message key="file.uploaded.successfully" />
							<br>
							<bean:message key="file.upload.status1" />
							<br>
							<html:messages id="invalidHeaderMsg" property="invalidHeader"
								message="true">
								<bean:write name="invalidHeaderMsg" />
								<br>
							</html:messages>
						</logic:messagesPresent>
					</span>
				</p>
				
        	
        	</div>
        
        
        
        <div class="floatr inner_right">
<logic:equal name="crmRoles" property="available(create_gis_data)" value="true" scope="session">
	<html:link href="#" styleId="upload_uploadFile" styleClass="main_button">Upload</html:link> 
</logic:equal>
</div>

        
        
        <p class="clear"></p>
        
      </div>
     
    </div>
 </html:form>
</div>
