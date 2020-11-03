<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="sf"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link href="${pageContext.request.contextPath}/resources/css/style.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery-1.12.0.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/common.js"></script>
<title>Uploaded Document(s)</title>
</head>
<body>
  <div id="main-wrapper">
    <c:if test="${not empty uploaded }">
      <table style="width: 100%;">
        <caption>List of Uploaded Files</caption>
        <tbody>
          <c:forEach items="${uploaded }" var="upload">
            <tr>
              <td style="width: 30%;">${upload.docType }</td>
              <td style="width: 70%;"><sf:form servletRelativeAction="/files/download" method="post">
                  <input type="hidden" name="module" value="${upload.moduleName }">
                  <input type="hidden" name="mapping" value="${upload.mappingId }">
                  <input type="hidden" name="doc" value="${upload.docType }">
                  <input type="hidden" name="version" value="${upload.version }">
                  <input type="submit" class="btn" value="${upload.docName}" title="Download">
                </sf:form></td>
            </tr>
          </c:forEach>
        </tbody>
        <tfoot>
          <tr>
            <c:url var="allDocUrl" value="/files/view/${UploadForm.module}/${UploadForm.mapping }"></c:url>
            <td colspan="2" style="text-align: right;"><input type="button" onclick="window.open('${allDocUrl}');" class="viewAll" title="View All"
              value="View All"></td>
          </tr>
        </tfoot>
      </table>
    </c:if>
    <c:if test="${empty uploaded }">No document found</c:if>
  </div>
</body>
</html>