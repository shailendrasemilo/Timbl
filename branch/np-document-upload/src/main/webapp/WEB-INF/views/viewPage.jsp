<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="sf"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link href="${pageContext.request.contextPath}/resources/css/style.css" rel="stylesheet" type="text/css">
<title>View Document</title>
</head>
<body>
  <div id="main-wrapper">
    <c:if test="${not empty uploaded }">
      <table>
        <caption>Uploaded Document for <b>${mappingId }</b></caption>
        <thead>
          <tr>
            <td>Version</td>
            <td>Document Type</td>
            <td>File</td>
          </tr>
        </thead>
        <tbody>
          <c:forEach items="${uploaded }" var="upload">
            <tr>
              <td style="width: 10%;">${upload.version }</td>
              <td style="width: 30%;">${upload.docType }</td>
              <td style="width: 60%;"><sf:form servletRelativeAction="/files/download" method="post" enctype="multipart/form-data">
                  <input type="hidden" name="module" value="${upload.moduleName }">
                  <input type="hidden" name="mapping" value="${upload.mappingId }">
                  <input type="hidden" name="doc" value="${upload.docType }">
                  <input type="hidden" name="version" value="${upload.version }">
                  <input type="submit" class="btn" value="${upload.docName}" title="Download">
                </sf:form></td>
            </tr>
          </c:forEach>
        </tbody>
      </table>
    </c:if>
    <c:if test="${empty uploaded }">
      <p>No document found</p>
    </c:if>
  </div>
</body>
</html>