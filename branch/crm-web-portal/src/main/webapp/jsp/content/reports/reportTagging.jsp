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
        <bean:define id="activeMenu" value="tagging" />
      <%@include file="csdMenu.jsp"%>
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
        <h4>Tagging Report</h4>

        <div class="relative inner_left_lead">
       <form action="/qrcReportAction" id="IDTagging" method="post">
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
              </html:select>
              </span>
            </p>
            <p class="floatl marginleft30">
              <strong>Current Bin</strong> <span class="LmsdropdownWithoutJs"><html:select property="functionalBinId" name="reportForm"
                  styleId="qrcFuncBinId">
                  <html:option value="">All</html:option>
                  <html:optionsCollection property="functionalBins" name="reportForm" label="categoryValue" value="categoryId" />
                </html:select></span>
            </p>
            <p class="floatl clear">
              <strong>Category</strong> <span class="LmsdropdownWithoutJs">
              <html:select property="qrcCategoryId" name="reportForm"
                  styleId="qrcCatId" onchange="populateSubCategoriesforReport(this.value,'qrcSubCatId');">
                  <html:option value="">All</html:option>
                  <html:optionsCollection property="qrcCategoryList" name="reportForm" label="qrcCategory" value="qrcCategoryId" />
                </html:select> </span>
            </p>
            <p class="floatl marginleft30">
              <strong>Sub Category</strong> <span class="LmsdropdownWithoutJs">
              <html:select property="qrcSubCategoryId" name="reportForm"
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
          </div>
          <div class="floatr inner_right">
            <p class="buttonPlacement" style="margin-right:-25px;">
              <a href="#" class="main_button" id="IDTaggingSubmit">Search</a>
            </p>
          </div>
          <br class="clear" />
          </form>
        </div>
        <div style="width: 100%;"><br class="clear" />
         <p class="floatl clear">
		 <logic:notEmpty  name="reportForm" property="qrcReportPojos">
        <table id="reportDataTable" >
          <thead>
            <tr>
              <td>Customer&nbsp;ID</td>
              <td>Customer&nbsp;Name</td>
              <td>Ticket&nbsp;ID</td>
			   <td>Qrc&nbsp;Type</td>
              <td>Category&nbsp;Name</td>
			  <td>Sub&nbsp;Category&nbsp;Name</td>
			   <td>Sub&nbsp;Sub&nbsp;Category&nbsp;Name</td>
              <td>QRC&nbsp;Action</td>
			  <td>Remarks&nbsp;Date</td>
              <td>Remarks&nbsp;By</td>
              <td>Service&nbsp;Name</td>
			  <td>Raised&nbsp;Date</td>
			  <td>Raised&nbsp;By</td>
			  <td>Status</td>
			  <td>Calling&nbsp;Number</td>
			  <td>Remarks</td>
			    </tr>
          </thead>
          <tbody>
            <logic:iterate id="ticketList" name="reportForm" property="qrcReportPojos">
            <tr>
            <td>${ticketList.customerId}</td>
              <td>${ticketList.custFname}&nbsp;${ticketList.custLname}</td>
              <td>${ticketList.ticketId}</td>
			   <td> <bean:write name="crmRoles" property="displayEnum(qrcType,${ticketList.qrcType })" scope="session" /></td>
			 
              <td>${ticketList.category}</td>
              <td>${ticketList.subCategory}</td>
			   <td>${ticketList.subSubCategory}</td>
              <td>${ticketList.action}</td>
			  <td><bean:write name="crmRoles" property="reportXmlDate(${ticketList.createdTime})" scope="session"/></td>
			   <td>${ticketList.resolvedBy}</td>
              <td><bean:write name="crmRoles" property="displayEnum(Product,${ticketList.product })" scope="session" />
               <bean:write name="crmRoles" property="displayEnum(ServiceType,${ticketList.serviceType})"  scope="session" />
               </td>
			   <td><bean:write name="crmRoles" property="reportXmlDate(${ticketList.raisedDate})" scope="session"/></td>
			   <td>${ticketList.raiseUser}</td>
			    <td><bean:write name="crmRoles" property="displayEnum(AllStatus,${ticketList.ticketStatus })" scope="session" /></td>
            
              <td>
              <logic:notEqual value="0" name="ticketList" property="callingNo">
              ${ticketList.callingNo}
              </logic:notEqual>
               
              </td>
              <td>${ticketList.remarks}</td>
            </tr>
            </logic:iterate>
          </tbody>
        </table>
		</logic:notEmpty> 
		<logic:empty name="reportForm" property="qrcReportPojos">
        <logic:equal value="true" name="reportForm" property="toSearch">
        	No Record Found.
      	 </logic:equal>
     </logic:empty>
	 </p>
          </div>
          <br class="clear" />
      </div>
      <p class="clear" />
    </div>
  </div>
</div>