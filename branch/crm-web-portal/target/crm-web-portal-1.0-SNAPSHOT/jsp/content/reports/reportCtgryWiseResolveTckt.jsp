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
        <bean:define id="activeMenu" value="ctgryWiseRTR" />
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
        <h4>Category Wise Resolve Ticket Report</h4>
        <div class="relative inner_left_lead">
       <form action="/qrcReportAction" id="categoryWiseResolveTckt" method="post">
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
              <strong>QRC Type</strong> <span class="LmsdropdownWithoutJs">
             <html:select name="reportForm" property="qrcType">
                   <html:option value="">All</html:option>
				   <html:option value="Q">Query</html:option>
				   <html:option value="R">Request</html:option>
				   <html:option value="C">Complaint</html:option>
              </html:select> </span>
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
              <strong>Sub Category</strong> <span class="LmsdropdownWithoutJs"><html:select property="qrcSubCategoryId" name="reportForm"
                  styleId="qrcSubCatId" onchange="populateSubSubCategoriesReport('qrcCatId','qrcSubSubcatId',this.value);">
                  <html:option value="">All</html:option>
                  <logic:notEmpty property="qrcSubCategoryList" name="reportForm">
                  <html:optionsCollection property="qrcSubCategoryList" name="reportForm" label="qrcSubCategory" value="qrcSubCategoryId" />
                  </logic:notEmpty>
                </html:select> </span>
            </p>
            <p class="floatl marginleft30">
              <strong>Sub Sub Category</strong> <span class="LmsdropdownWithoutJs"><html:select property="qrcSubSubCategoryId" name="reportForm"
                  styleId="qrcSubSubcatId">
                  <html:option value="">All</html:option>
                   <logic:notEmpty property="qrcSubSubCategoryList" name="reportForm">
                  <html:optionsCollection property="qrcSubSubCategoryList" name="reportForm" label="qrcSubSubCategory" value="qrcSubSubCategoryId" />
                  </logic:notEmpty>
                </html:select> </span>
            </p>
			 <br class="clear"/>
			<br class="clear"/>
			<br class="clear"/>
          </div>
          <div class="floatr inner_right">		 
            <p class="buttonPlacement">		
              <a href="javascript:void(0);" class="main_button" id="categoryWiseResolveTcktSUBMIT">Search</a>
            </p>
          </div>
          <br class="clear" />
          </form>
        </div>
      <logic:notEmpty  name="reportForm" property="qrcReportPojos">
		 <div class="data-table">
        <table id="reportDataTable" >
          <thead>
            <tr>
			   <td>Category&nbsp;Name</td>
			   <td>Sub&nbsp;Category&nbsp;Name</td>
			   <td>Sub&nbsp;SubCategory&nbsp;Name</td>
			    <td>Qrc&nbsp;Type</td>
			   <td>SLA</td> 
			   <td>HRS_0_4</td>
			   <td>HRS_4_24</td>
			   <td>DAYS_1_2</td>
			   <td>DAYS_2_3</td>
			   <td>DAYS_3_4</td>
			   <td>DAYS_4_5</td>
			   <td>DAYS_5_6</td>	   
			   <td>DAYS_6_7</td>
			   <td>DAYS_7_10</td>
			   <td>DAYS_10_15</td>
			   <td>DAYS_ABOVE_15</td>
			   <td>TOTAL</td>			   
		   </tr>
          </thead>
          <tbody>
             <logic:iterate id="ticketList" name="reportForm" property="qrcReportPojos">
            <tr>
				<td>${ticketList.category}</td>
				<td>${ticketList.subCategory} </td>
				<td>${ticketList.subSubCategory}</td>
				<td><bean:write name="crmRoles" property="displayEnum(qrcType,${ticketList.qrcType})"/></td>
				<td>SLA</td> 
			   <td>HRS_0_4</td>
			   <td>HRS_4_24</td>
			   <td>DAYS_1_2</td>
			   <td>DAYS_2_3</td>
			   <td>DAYS_3_4</td>
			   <td>DAYS_4_5</td>
			   <td>DAYS_5_6</td>	   
			   <td>DAYS_6_7</td>
			   <td>DAYS_7_10</td>
			   <td>DAYS_10_15</td>
			   <td>DAYS_ABOVE_15</td>
			   <td>TOTAL</td>
            </tr>
            </logic:iterate>         
          </tbody>
        </table>
      </div>
      </logic:notEmpty>
      <logic:empty name="reportForm" property="qrcReportPojos">
	<logic:equal name="reportForm" property="toSearch" value="true">
	 No Record Found
	</logic:equal>
	</logic:empty>
      </div>
     
     <br class="clear" />
	