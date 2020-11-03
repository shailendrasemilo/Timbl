<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib uri="/WEB-INF/tlds/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/tlds/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/tlds/displaytag.tld" prefix="display"%>
<%@taglib uri="/WEB-INF/tlds/struts-html.tld" prefix="html"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div class="loadingPopup hidden"></div>
<div id="section">
  <div class="section">
    <div class="inner_section ">
      <div class="inner_left_lead floatl  qrcLeft">
        <bean:define id="activeMenu" value="categoryWiseReopen" />
       <%@include file="qrcReportMenu.jsp"%>
      </div>
      <div class=" floatl manageGISRight  qrcRight" style="width: 1000px;">
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
        <h4>Category wise Reopen Ticket Report</h4>

        <div class="relative inner_left_lead">
       <form action="/qrcReportAction" id="IDCatwiseReopen" method="post">
       	<html:hidden name="reportForm" property="toSearch" value="true"/>
          <div class="floatl">
          <p class="floatl clear">
  			<strong> From Date<span class="error_message verticalalignTop">*</span></strong>
   			<html:text property="fromDate" styleId="fromDate" name="reportForm" styleClass="tcal tcalInput" readonly="readonly"></html:text>
  			 <font></font>
 			 </p>
  				<p class="floatl marginleft30">
   			  <strong> To Date<span class="error_message verticalalignTop">*</span></strong>
              <html:text property="toDate" styleId="toDate" name="reportForm" styleClass="tcal tcalInput" readonly="readonly" />
              <font ></font>
              <label id="ToDateError class="errorTextbox hidden">
              </label>
  			</p>
   
            <p class="marginleft30 floatl">
              <strong>QRC Type</strong> <span class="LmsdropdownWithoutJs"><select>
                  <option value="">All</option>
                 <option value="Q">Query</option>
                  <option value="R">Request</option>
                  <option value="C">Complaint</option>
              </select> </span>
            </p>
           
            <p class="floatl clear">
              <strong>Category</strong> <span class="LmsdropdownWithoutJs">
              <html:select property="qrcCategoryId" name="reportForm"
                  styleId="qrcCatId" onchange="populateSubCategoriesforReport(this.value,'qrcSubCatId');">
                  <html:option value="0">All</html:option>
                  <html:optionsCollection property="qrcCategoryList" name="reportForm" label="qrcCategory" value="qrcCategoryId" />
                </html:select> </span>
            </p>
            <p class="floatl marginleft30">
              <strong>Sub Category</strong> <span class="LmsdropdownWithoutJs">
              <html:select property="qrcSubCategoryId" name="reportForm"
                  styleId="qrcSubCatId" onchange="populateSubSubCategoriesReport('qrcCatId','qrcSubSubcatId',this.value);">
                  <html:option value="0">All</html:option>
                </html:select> </span>
            </p>
            <p class="floatl marginleft30">
              <strong>Sub Sub Category </strong> <span class="LmsdropdownWithoutJs"><html:select property="qrcSubSubCategoryId" name="reportForm"
                  styleId="qrcSubSubcatId">
                  <html:option value="">All</html:option>
                </html:select> </span>
            </p>
          </div>
          <div class="floatr inner_right">
            <p class="buttonPlacement">
              <a href="#" class="main_button" id="IDCatwiseReopenSubmit">Search</a>
            </p>
          </div>
          <br class="clear" />
          </form>
        </div>
      
		 
		<div class="dTInteractionRpt" >
	<p class="floatl clear" >
		 <logic:notEmpty  name="reportForm" property="qrcReportPojos">
        <table id="reportDataTable" >
          <thead>
            <tr>
			<td>
			Category
			</td>
			<td>
			Sub&nbsp;Category
			</td>
			<td>
			Sub&nbsp;SubCategory
			</td>
			<td>
			QRC&nbsp;Type
			</td>
			<td>
			Total&nbsp;Reopen&nbsp;Count
			</td>
			</tr>
          </thead>
          <tbody>
         
		<logic:iterate id="ticketList" name="reportForm" property="qrcReportPojos">
		 <tr>
			<td>${ticketList.category}</td>
             <td>${ticketList.subCategory}</td>	
            <td>${ticketList.subSubCategory}</td>
			 <td>  <bean:write name="crmRoles" property="displayEnum(qrcType,${ticketList.qrcType })" scope="session" /></td>	
			<td>
			<td>${ticketList.reopenCount}</td>
		</tr>
		</logic:iterate>
	
          </tbody>
        </table>
		</logic:notEmpty>
		   <logic:empty name="reportForm" property="qrcReportPojos">
        <logic:equal value="true" name="reportForm" property="toSearch">
        	No Record Found.
      	 </logic:equal>
     </logic:empty></p>
      </div>
       </div>
     
      <p class="clear" />