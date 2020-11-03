<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<%@taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link href="css/myaccount-common.css" type="text/css" rel="stylesheet" />
<link href="css/popup-common.css" type="text/css" rel="stylesheet" />
<link href="css/color-${initParam.client}.css" type="text/css" rel="stylesheet" />
<!-- =============== fancy box =====================-->
<link rel="stylesheet" type="text/css" href="css/jquery.fancybox.css" />
<script type="text/javascript" src="javascript/jquery-1.10.2.min.js"></script>
<script type="text/javascript" src="javascript/jquery.fancybox.pack.js"></script>
<script type="text/javascript" src="javascript/fancybox-custom.js"></script>
<script type="text/javascript" src="javascript/selfcare.js"></script>
<!-- =============== dwr =============== -->
<script src="dwr/engine.js" type="text/javascript"></script>
<script src="dwr/util.js" type="text/javascript"></script>
<script src="dwr/interface/crmDwr.js" type="text/javascript"></script>
<title>Log Ticket</title>
</head>
<body>
  <div class="popup-loading hide">
    <img alt="Please wait" src="images/icons-${initParam.client }/ajaxloading.gif" />
  </div>
  <div class="mainWrapper loginWrap">
    <div class="titleBar">
      <span class="pageTitle">Log Ticket</span> <a id="closeBtn" href="javascript:void(0);" onclick="parent.jQuery.fancybox.close();"></a>
    </div>
    <div class="loginArea">
      <div class="pInfo hide"></div>
      <logic:messagesPresent property="appError">
        <bean:define id="hasErrors" value="true" />
      </logic:messagesPresent>
      <div class="msgerror ${ hasErrors ? '' : 'hide' }">
        <html:errors />
      </div>
      <logic:messagesPresent message="true">
        <html:messages id="message" message="true">
          <div class="msgsuccess">
            <bean:write name="message" />
          </div>
        </html:messages>
      </logic:messagesPresent>
      <div>
        <html:form action="/user" styleId="logTicketForm">
          <div class="logTicketArea marginT10">
            <label>Subject:</label>
            <div class="userDetail">
              <html:select property="crmSelfcareCategoriesPojo.subject" name="selfcareForm" styleClass="inputDropdown" styleId="scSubject"
                onchange="getSelfcareCategories(this.value,'scCategory');">
                <html:option value="">Please Select</html:option>
                <logic:notEmpty name="selfcareForm" property="selfcareSubjects">
                  <html:options name="selfcareForm" property="selfcareSubjects" />
                </logic:notEmpty>
              </html:select>
            </div>
            <div class="clr"></div>
          </div>
          <div class="logTicketArea marginT10">
            <label>Category:</label>
            <div class="userDetail">
              <html:select property="crmSelfcareCategoriesPojo.qrcSubSubCategoryId" name="selfcareForm" styleClass="inputDropdown"
                styleId="scCategory">
                <html:option value="0">Please Select</html:option>
                <logic:notEmpty name="selfcareForm" property="crmSelfcareCategoriesPojos">
                  <html:optionsCollection name="selfcareForm" property="crmSelfcareCategoriesPojos" value="qrcSubSubCategoryId"
                    label="selfcareCategory" />
                </logic:notEmpty>
              </html:select>
            </div>
            <div class="clr"></div>
          </div>
          <div class="logTicketArea marginT10">
            <label>Remarks:</label>
            <div class="userDetail">
              <html:textarea property="ticketHistoryPojo.remarks" name="selfcareForm" styleClass="inputTextArea" styleId="scRemarks" rows="4"></html:textarea>
            </div>
            <div class="clr"></div>
          </div>
          <div class="logTicketArea marginT10">
            <label></label>
            <div class="userDetail">
              <input class="submitBtn btn floatL" type="button" id="submitLogTicket" value="Submit" />
            </div>
            <div class="clr"></div>
          </div>
        </html:form>
      </div>
    </div>
  </div>
</body>
</html>