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
        <bean:define id="activeMenu" value="catWiseOTR" />
        <%@include file="qrcReportMenu.jsp"%>
      </div>
      <div class=" floatl manageGISRight  qrcRight qrcReports">
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
		<form action="/qrcReportAction" id="IDcatWiseORT" method="post">
		<div class="relative">
        <h4>Category Wise Open Ticket Report</h4>
			<div class="clear marginleft10 inner_left_lead floatl">
              <html:hidden name="reportForm" property="toSearch" value="true" />
              <p class="floatl clear margintop20">
              <strong>Category</strong> <span class="LmsdropdownWithoutJs">
              <html:select property="qrcCategoryId" name="reportForm"
                  styleId="qrcCatId" onchange="populateSubCategoriesforReport(this.value,'qrcSubCatId');">
                  <html:option value="0">All</html:option>
                  <html:optionsCollection property="qrcCategoryList" name="reportForm" label="qrcCategory" value="qrcCategoryId" />
                </html:select> </span>
              </p>
              <p class="floatl marginleft30 margintop20">
              <strong>Sub Category</strong> <span class="LmsdropdownWithoutJs">
              <html:select property="qrcSubCategoryId" name="reportForm"
                  styleId="qrcSubCatId" onchange="populateSubSubCategoriesReport('qrcCatId','qrcSubSubcatId',this.value);">
                  <html:option value="">All</html:option>
                  <logic:notEmpty property="qrcSubCategoryList" name="reportForm">
                  <html:optionsCollection property="qrcSubCategoryList" name="reportForm" label="qrcSubCategory" value="qrcSubCategoryId" />
                  </logic:notEmpty>
                  
                </html:select> </span>
            </p>
              <p class="floatl marginleft30 margintop20">
              <strong>Sub Sub Category</strong> <span class="LmsdropdownWithoutJs"><html:select property="qrcSubSubCategoryId" name="reportForm"
                  styleId="qrcSubSubcatId">
                  <html:option value="">All</html:option>
                  <logic:notEmpty property="qrcSubSubCategoryList" name="reportForm">
                  <html:optionsCollection property="qrcSubSubCategoryList" name="reportForm" label="qrcSubSubCategory" value="qrcSubSubCategoryId" />
                  </logic:notEmpty>
                </html:select> </span>
            </p>
              <p class="floatl marginleft30 margintop20">
               <strong>QRC Type</strong> <span class="LmsdropdownWithoutJs">
			  <html:select property="qrcType" name="reportForm" styleId="qrcTypeId">
                 <html:option value="0">All</html:option>
                  <html:option value="Q">Query</html:option>
                  <html:option value="R">Request</html:option>
                  <html:option value="C">Complaint</html:option>
                 
                </html:select></span>
			</span>
            </p>
          </div>
          </div>
          <div class="floatr inner_right">
            <p class="buttonPlacement">
              <a href="#" class="main_button" id="IDCWOpenSubmit">Search</a>
           </p>
            <br class="clear" />
          </div>
          <div style="width: 100%;">
            <p class="floatl clear">
              <logic:notEmpty name="reportForm" property="slaReportPojos">
			  
                <table id="reportDataTable" style="width: 100%">
          <thead>
             <tr>
              <td>Category&nbsp;Name</td>
			  <td>Sub&nbsp;Category&nbsp;Name</td>
			  <td>Sub&nbsp;Sub&nbsp;Category&nbsp;Name</td>
			  <td>QRC&nbsp;Type</td>
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
			  <td>Total</td>
			 </tr>
          </thead>
          <tbody>
          <c:set var="HRS_0_4" value="0" /><c:set var="HRS_4_24" value="0" /><c:set var="DAYS_1_2" value="0" /><c:set var="DAYS_2_3" value="0" />
          <c:set var="DAYS_3_4" value="0" /><c:set var="DAYS_4_5" value="0" /><c:set var="DAYS_5_6" value="0" /><c:set var="DAYS_6_7" value="0" />
          <c:set var="DAYS_7_10" value="0" /><c:set var="DAYS_10_15" value="0" /><c:set var="DAYS_ABOVE_15" value="0" /><c:set var="Total" value="0" />
           <logic:iterate id="ticketList" name="reportForm" property="slaReportPojos">
           
			
            <tr>
               <td>${ticketList.category}</td>
              <td>${ticketList.subCategory}</td>
			  <td>${ticketList.subSubCategory}</td>
			  <td>
			 <c:if test="${not empty ticketList.qrcType }"><bean:write name="crmRoles" property="displayEnum(qrcType,${ticketList.qrcType })" scope="session" /></c:if>
			  
			  </td>
              <td>
			  ${ticketList.slaTime}
			  </td>
			  <td> ${ticketList.hr04Count}<c:set var="HRS_0_4" value="${(HRS_0_4+ticketList.hr04Count)}" /></td>
			  <td> ${ticketList.hr424Count}<c:set var="HRS_4_24" value="${(HRS_4_24+ticketList.hr424Count)}" /></td>
			  <td> ${ticketList.day12Count}<c:set var="DAYS_1_2" value="${(DAYS_1_2+ticketList.day12Count)}" /></td>
			  <td> ${ticketList.day23Count}<c:set var="DAYS_2_3" value="${(DAYS_2_3+ticketList.day23Count)}" /></td>
			  <td> ${ticketList.day34Count}<c:set var="DAYS_3_4" value="${(DAYS_3_4+ticketList.day34Count)}" /></td>
			  <td> ${ticketList.day45Count}<c:set var="DAYS_4_5" value="${(DAYS_4_5+ticketList.day45Count)}" /></td>
			  <td> ${ticketList.day56Count}<c:set var="DAYS_5_6" value="${(DAYS_5_6+ticketList.day56Count)}" /></td>
			  <td> ${ticketList.day67Count}<c:set var="DAYS_6_7" value="${(DAYS_6_7+ticketList.day67Count)}" /></td>
			  <td> ${ticketList.day710Count}<c:set var="DAYS_7_10" value="${(DAYS_7_10+ticketList.day710Count)}" /></td>
			  <td> ${ticketList.day1015Count}<c:set var="DAYS_10_15" value="${(DAYS_10_15+ticketList.day1015Count)}" /></td>
			  <td> ${ticketList.dayAbove15Count}<c:set var="DAYS_ABOVE_15" value="${(DAYS_ABOVE_15+ticketList.dayAbove15Count)}" /></td>
			  <td> ${ticketList.totalCount}<c:set var="Total" value="${(Total+ticketList.totalCount)}" /></td>    
            </tr>
           </logic:iterate>
           <tr>
              <td>Total</td>
			  <td>&nbsp;&nbsp;</td>
			  <td>&nbsp;</td>
			  <td>&nbsp;</td>
			  <td>&nbsp;</td>
			  <td>${HRS_0_4}</td>
			  <td>${HRS_4_24}</td>
			  <td>${DAYS_1_2}</td>
			  <td>${DAYS_2_3}</td>
			  <td>${DAYS_3_4}</td>
			  <td>${DAYS_4_5}</td>
			  <td>${DAYS_5_6}</td>
			  <td>${DAYS_6_7}</td>
			  <td>${DAYS_7_10}</td>
			  <td>${DAYS_10_15}</td>
			  <td>${DAYS_ABOVE_15}</td>
			  <td>${Total}</td>
           
           </tr>
          </tbody>
        </table>
      </logic:notEmpty>
      <logic:empty name="reportForm" property="slaReportPojos">
        <logic:equal value="true" name="reportForm" property="toSearch">
        	No Record Found.
      	 </logic:equal>
     </logic:empty>
    </p>
          </div>
          <br class="clear" />
		  </form>
      </div>
      <p class="clear" />
    </div>
  </div>
</div>