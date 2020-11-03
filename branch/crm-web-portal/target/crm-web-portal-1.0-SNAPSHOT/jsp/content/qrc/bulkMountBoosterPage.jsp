<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib uri="/WEB-INF/tlds/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/tlds/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/tlds/struts-logic.tld" prefix="logic"%>
<div class="loadingPopup hidden"></div>
<div id="section">
	<div class="section">
		<h2>Bulk Mount Booster</h2>
		<div class="inner_section ">
			<div class="error_message" id="error">
				<html:errors />
			</div>
			<html:form action="/bulkUploadAction" method="post" styleId="IDuploadMountBooster" enctype="multipart/form-data">
				<div class="QRC marginleft10">

					<p class="floatl">
						<strong>Selected File</strong> <span id="inputFileName" class="selected_File"><bean:message key="error.gis.formFile" /></span> <br> <br> <span
							class="import inlineBlock"> <html:file property="mountBoosterExcelFile" name="bulkUploadForm" styleId="fileUpload" styleClass="file_Upload"
								onchange="javascript:displayFileName();"></html:file> <a href="#" id="import_fileUpload" class="import_file_Upload"><span><bean:message
										key="file.upload.select" /></span></a>
						</span> <span class="import inlineBlock marginleft10"> <a href="CRMExcelFormats/Mount_Booster.xlsx" class="download_Format"><bean:message
									key="file.download.format" /></a>
						</span>
					</p>

					<p class="floatl clear">
						<strong> Remarks<sup class="req">*</sup></strong>
						<html:textarea property="remarksPojo.remarks" name="bulkUploadForm" styleClass="LmsRemarkstextarea" styleId="mountBoosterRemarks" />
						<font class="errorRemarks hidden">Please enter remarks between[2-4000]</font>
					</p>
					<p class="serverSideMessage_upload_gis floatl marginleft30" style="z-index: 10; top:21px;">
						<span class="success_message_upload_gis"> <logic:messagesPresent message="true" property="uploadStatus">
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
									<%
									    String fileName = (String) request.getAttribute( "fileName" );
									%>
									<a href="MountBoosterExcelUpload/<%=fileName%>"><bean:message key="file.download.error" /></a>
								</logic:messagesPresent>
							</logic:messagesPresent>
						</span> <span class="error_message_upload_gis"> <logic:messagesPresent message="true" property="invalidHeader">
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
					<div class="floatr">
						<p class="buttonPlacement">
							<logic:equal name="crmRoles" property="available(create_bulk_mount_booster,update_bulk_mount_booster)" value="true" scope="session">
								<a href="#" id="submit_bulk_MountBooster" class="main_button"><span>Upload</span></a>
							</logic:equal>
						</p>
					</div>
				</div>
			</html:form>
			<p class="clear" style="height: 250px;"></p>
		</div>
	</div>
</div>
<p class="clear" />