<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<%@taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@taglib uri="http://struts.apache.org/tags-nested" prefix="nested"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<head>
<link rel="stylesheet" href="css/jquery-ui.css">
<script src="javascript/jquery-ui.js"></script>
<style type="text/css">
.ui-datepicker-trigger {
position: absolute;
}
</style>
</head>
<div id="section">
  <html:form action="/crmRcaReason" styleId="holidayId" method="post">
  <html:hidden property="crmHolidayPojo.recordId" name="crmRcaReasonForm" styleId="recordId"/>
    <div class="section">
      <h2>Manage Holidays</h2>
      <div class="inner_section"> 
        <div class="inner_left_lead floatl  marginleft10">
		<div class="success_message">
          <logic:messagesPresent message="true">
            <html:messages id="message" message="true">
              <bean:write name="message" />
            </html:messages>
          </logic:messagesPresent>
        </div>
		 <div class="error_message" id="error">
          <html:errors />
		  </div>
          <p style="width:150px;">
            <strong>Date<span class="error_message verticalalignTop">*</span></strong> 
			 <html:text name="crmRcaReasonForm" property="date" styleId="holidayDate" styleClass="Lmstextbox calender" readonly="true"/>
          </p>
		   <p  style="margin-left: 198px;margin-top: -55px;">
		    <strong>Description<span class="error_message verticalalignTop">*</span></strong>
	        <html:text name="crmRcaReasonForm" property="crmHolidayPojo.description" styleId="holidayDesc" styleClass="Lmstextbox" maxlength="40"/>
	         </p >
			  <p style="margin-left: 375px;margin-top: -28px;">
              <html:hidden name="crmRcaReasonForm" property="crmHolidayPojo.status" styleId="status"/>
               <logic:equal name="crmRoles" property="available(create_hldy_cnf)" value="true" scope="session">
              <a href="javascript:void(0);" onclick="holidaySubmit();" id="holidayIdSubmit" class="yellow_button">Submit</a>
             </logic:equal>
              </p> 
          <div id="role_group_view">
            <span class="error_message"> </span> 
            <div class="display_group">
              <div class="stays-at-top">
                <span class="heading_text"> </span> <span class="heading_text marginleft600">Upcoming Holidays</span> <span class="heading_text marginleft485"><a
                  href="javaScript:showRolesDetails();"></a></span>
              </div>
              <ul class="add_role_group">               
            <%--  <%int i=0;%>--%>
               <c:forEach items="${crmRcaReasonForm.crmFutureHolidayPojos }" var="data">
              <%--  <%++i; %>--%>
                      <li class="first">
                          <span >						   
                          <c:set var="holidayDate"><bean:write name="crmRoles" property="toDate(${data.holidayDate})" scope="session"/></c:set>
						  <input type="text" value="${ holidayDate}" class="textbox" readonly="readonly">
                          </span>
                           &nbsp;&nbsp;
                          <span > 
						  <html:text name="data" property="description" styleClass="textbox" readonly="true"/>						   
                          </span>
                          <%-- 
                           <logic:equal name="crmRoles" property="available(update_hldy_cnf)" value="true" scope="session">
                        <span class="paddingtopbottom8">
						 <c:choose>
			                <c:when test="${data.status eq 'A'}">
			                 <input type="radio"  name="status<%=i %>" id="statusA<%=i %>" value="A" checked="checked">Active&nbsp;&nbsp;
			                </c:when>
			                <c:otherwise>
			                <input type="radio"  name="status<%=i %>" id="statusA<%=i %>" value="A" onchange="changeHolidayStatus('statusI<%=i %>','${data.recordId }','A','${holidayDate}')">Active&nbsp;&nbsp;
			                </c:otherwise>
		                </c:choose>
		                 <c:choose>
			                <c:when test="${data.status eq 'I'}">
			                 <input type="radio"  name="status<%=i %>" id="statusI<%=i %>" value="I" checked="checked" >In Active
			                </c:when>
			                <c:otherwise>
			                <input type="radio"  name="status<%=i %>" id="statusI<%=i %>" value="I" onchange="changeHolidayStatus('statusA<%=i %>','${data.recordId }','I','${holidayDate}')">In Active
			                </c:otherwise>
		                </c:choose>
						</span> 
						</logic:equal>
						--%>
                        </li>
                   </c:forEach>
              </ul>
            </div>
          </div>
        </div>
        <div class=" floatl groupSelection">
          <div class="stays-at-top">
            <span>Previous Holidays</span>
          </div> 
          <ul class="searchList" id="searchAssignRolesList">
           <c:forEach items="${crmRcaReasonForm.crmPastHolidayPojos }" var="data">
                <li class="first">
                    <span style="display: inline-block;width: 100px;">
                       <bean:write name="crmRoles" property="toDate(${data.holidayDate})" scope="session"/>
                    </span>
                    <span style="display: inline-block;width: 100px;"> 
                     <bean:write name="data" property="description" />
                    </span>
                   </li>
              </c:forEach>
          </ul>
        </div>
        <div class="floatr inner_right"> 
        </div>
        <p class="clear"></p>
      </div>
      <div></div>
    </div>
  </html:form>
</div>
<script>
var today = new Date();
    var tomorrow = new Date();
    tomorrow.setDate(today.getDate() + 1);
$( ".calender" ).datepicker({
  //maxDate: "+0y +0m +0w +0d",
  //minDate: "+0y +0m +0w +0d",
  minDate: tomorrow,
  changeMonth: true,
  changeYear: true,
  dateFormat: "dd-M-yy",
  prevText: "Earlier",
  showOn: "both",
  yearRange: "c-100:c+100"
});
</script>
