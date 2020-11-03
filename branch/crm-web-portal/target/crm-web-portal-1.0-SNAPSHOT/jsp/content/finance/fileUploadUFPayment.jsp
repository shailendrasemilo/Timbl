<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<%@taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Upfront Payment File Upload</title>
</head>
<body>
  <div id="section">
    <html:form action="/upFrontPaymentRecovery" method="post" styleId="IDuploadLms" enctype="multipart/form-data">
      <html:hidden name="financeForm" property="remarksPojo.createdBy" value="${sessionScope.userPojo.userId}" />
      <div class="section">
        <h2>Upfront Payment File Upload</h2>
        <p class="error_message">
          <html:errors />
        </p>
        <div class="inner_section">
          <div class="createUserLeft floatl relative marginleft10 inner_left">
            <html:hidden property="fileTypeId" styleId="fileType" value="upFront" />
            <p class="floatl clear">
              <b>Business Partner<span class="error_message verticalalignTop">*</span></b> <span class="LmsdropdownWithoutJs"> <logic:notEmpty name="financeForm" property="partnerList">
                  <html:select property="crmUpfrontPaymentPojo.partnerId" name="financeForm" styleId="partnerIdCRF">
                    <html:option value="0">Please Select</html:option>
                    <html:optionsCollection name="financeForm" property="partnerList" label="partnerName" value="partnerId" />
                  </html:select>
                </logic:notEmpty> <font></font>
              </span>
            </p>
            <p class="floatl clear">
              <b>Selected File<span class="error_message verticalalignTop">*</span></b> <span id="inputFileName" class="selected_File"><bean:message key="error.gis.formFile" /></span><br /> <br> <span
                class="import"> <input type="file" name="formFile" id="fileUpload" class="file_Upload" onchange="javascript:displayFileName();" /> <a
                href="#" id="import_fileUpload" class="import_file_Upload"><span><bean:message key="file.upload.select" /></span></a>
              </span> <br />
              <br /> <span class="import"> <a href="CRMExcelFormats/UpFront_Payment_Feed.xlsx"><bean:message key="file.download.format" /></a>
              </span>
            </p>
            <p class="serverSideMessage_upload_gis floatl marginleft30">
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
                    <a href="UpFrontExcelUpload/<%=fileName%>"><bean:message key="file.download.error" /></a>
                    <br />
                    <br />
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
            <p class="floatl clear">
              <strong>Remarks<span class="error_message verticalalignTop">*</span></strong>
              <html:textarea cols="27" rows="38" styleClass="LmsRemarkstextarea" property="remarksPojo.remarks" name="financeForm" styleId="remarks"></html:textarea>
              <font></font>
            </p>
          </div>
          <div class="floatr inner_right">
            <html:link href="#" styleId="upload_uploadFile" styleClass="main_button">Upload</html:link>
          </div>
          <p class="clear"></p>
        </div>
      </div>
    </html:form>
  </div>