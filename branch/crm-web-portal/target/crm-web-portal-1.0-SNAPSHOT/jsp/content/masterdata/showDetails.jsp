<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib uri="/WEB-INF/tlds/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/tlds/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/tlds/displaytag.tld" prefix="display"%>
<%@taglib uri="/WEB-INF/tlds/struts-html.tld" prefix="html"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<link href="css/common.css" rel="stylesheet" media="screen" />
<link href="css/color-${initParam.client }.css" rel="stylesheet" media="screen" />
<link href="css/masterdata.css" rel="stylesheet" media="screen" />
<link href="css/userManagement.css" rel="stylesheet" media="screen">
<link href="css/emailManagement.css" rel="stylesheet" media="screen">
<link href="css/lms.css" rel="stylesheet" media="screen">
<script src="javascript/jquery.min.js" type="text/javascript"></script>
<script src="javascript/jquery.validate.js" type="text/javascript"></script>
<script src="javascript/addReceipt.js" type="text/javascript"></script>
<style>
.viewCrmMaster ul.add_rcaValue {
	height: auto !important;
}

.viewCrmMaster .display_catagoryValue {
	height: auto !important;
}
</style>
</head>
<body>
  <div id="popupSection">
    <div class="popupSection">
      <h2>CRM Master Details</h2>
      <div class="inner_section">
        <div class="inner_left_lead viewCrmMaster marginleft10 ">
          <div id="role_group_view1" class="relative margintop20 width870">
            <div class="display_catagoryValue">
              <div class="stays-at-top">
                <p class="heading_text ">Category</p>
                <p class="heading_text marginL20">Sub Category</p>
                <p class="heading_text marginL15">Sub Sub Category</p>
                <p class="heading_text">Description</p>
              </div>
              <ul class="add_rcaValue rcaCat">
                <c:forEach var="category" items="${crmRcaReasonForm.subSubCategoryDetails}" varStatus="rcaCat">
                  <li class="rcaCat ${rcaCat.index mod 2 eq 0 ? 'first' : 'second'}">
                    <p>${category.key}</p>
                    <ul class="rcaSubCat">
                      <c:forEach var="subCats" items="${category.value}" varStatus="rcaSubCat">
                        <c:forEach var="subCategory" items="${subCats}">
                          <li class="rcaSubCat">
                            <%-- ${(rcaCat.index mod 2 eq 0) and (rcaSubCat.index mod 2 eq 0) ? 'second' : (rcaSubCat.index mod 2 eq 0 ? 'first' : 'second')} --%>
                            <p>${subCategory.key}</p>
                            <ul class="rcaSubSubCat">
                              <c:forEach var="subSubCat" items="${subCategory.value}" varStatus="rcaSubSubCat">
                                <li class="rcaSubSubCat">
                                  <%-- ${(rcaCat.index mod 2 eq 0) and (rcaSubCat.index mod 2 eq 0) ? (rcaSubSubCat.index mod 2 eq 0 ? 'first' : 'second') : (rcaSubSubCat.index mod 2 eq 0 ? 'second' : 'first')} --%>
                                  <p>${subSubCat.contentName}</p>
                                  <p class="desc">${subSubCat.contentValue}</p>
                                </li>
                              </c:forEach>
                            </ul>
                          </li>
                        </c:forEach>
                      </c:forEach>
                    </ul>
                  </li>
                </c:forEach>
                <%--
                <li class="first">
                  <p class="floatl">Lead Management System</p>
                  <p class="floatl marginleft30">REASON</p>
                  <p class="floatl marginleft30">Close</p>
                  <p class="floatl marginleft30">Add close reasons for lead management system .</p> <br class="clear" />
                </li>
                <li class="second">
                  <p class="floatl ">Installation &amp; Activation</p>
                  <p class="floatl marginleft30">CRF/Root Call Analysis</p>
                  <p class="floatl marginleft30">Close</p>
                  <p class="floatl marginleft30">Add close CRF/Root Call Analysis for Installation &amp; Activation process .</p> <br class="clear" />
                </li>
                <li class="first">
                  <p class="floatl  ">CRM</p>
                  <p class="floatl marginleft30">Master</p>
                  <p class="floatl marginleft30">Banks</p>
                  <p class="floatl marginleft30">Add Banks details for CRM master.</p> <br class="clear" />
                </li>
                 --%>
              </ul>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</body>
</html>
