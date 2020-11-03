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
        <bean:define id="activeMenu" value="openTicket" />
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
		<form action="/qrcReportAction" id="IDOpenTicket" method="post">
		<div class="relative">
        <h4>Open Ticket Report</h4>
			<div class="clear marginleft10 inner_left_lead floatl">
              <html:hidden name="reportForm" property="toSearch" value="true" />
              <p class="floatl clear margintop20">
              <strong>Category</strong> <span class="LmsdropdownWithoutJs">
              <html:select property="qrcCategoryId" name="reportForm"
                  styleId="qrcCatId" onchange="populateSubCategoriesforReport(this.value,'qrcSubCatId');">
                  <html:option value="">All</html:option>
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
                 <html:option value="">All</html:option>
                  <html:option value="R">Request</html:option>
                  <html:option value="C">Complaint</html:option>
                 
                </html:select></span>
			</span>
            </p>
            <p class="floatl clear">
              <strong>Current Bin</strong> <span class="LmsdropdownWithoutJs">
			  <html:select property="functionalBinId" name="reportForm" styleId="qrcFuncBinId">
			     <html:option value="">All</html:option>
                 <html:optionsCollection property="functionalBins" name="reportForm" label="categoryValue" value="categoryId" /> 
                </html:select></span>
            </p>
			<p class="floatl marginleft30">
              <strong>Status</strong> <span class="LmsdropdownWithoutJs">
			  <html:select property="statusOfTicket" name="reportForm" styleId="statusId">
			     <html:option value="">All</html:option>
                 <html:option value="O">Open</html:option>
                 <html:option value="RO">ReOpen</html:option> 
                </html:select></span>
            </p>
          </div>
          </div>
          <div class="floatr inner_right">
            <p class="buttonPlacement">
              <a href="#" class="main_button" id="IDTicketOpenSubmit">Search</a>
           </p>
            <br class="clear" />
          </div>
          <div style="width: 100%;">
            <p class="floatl clear">
              <logic:notEmpty name="reportForm" property="qrcReportPojos">
                <table id="reportDataTable" style="width: 100%">
          <thead>
             <tr>
              <td>Ticket&nbsp;ID</td>
              <td>CAF&nbsp;No</td>
              <td>Customer&nbsp;ID</td>
              <td>Customer&nbsp;Name</td>
			  <td>RMN</td>
              <td>Customer&nbsp;Status</td>
              <td>Segment</td>
			  <td>LMO</td>
              <td>NAS&nbsp;Port/&nbsp;Option&nbsp;82</td>
              <td>INST&nbsp;Address</td>
			  <td>QRC&nbsp;Type</td>
			  <td>Category&nbsp;Name</td>
			  <td>Sub&nbsp;Category&nbsp;Name</td>
			  <td>Sub&nbsp;Sub&nbsp;Category&nbsp;Name</td>
			  <td>Raised&nbsp;Date</td>
			  <td>Raised&nbsp;User</td>
			  <td>Current&nbsp;Bin</td>
			  <td>Current&nbsp;Bin&nbsp;Assign&nbsp;Date</td>
			  <td>Current&nbsp;User</td>
			  <td>Current&nbsp;User&nbsp;Assign&nbsp;Date</td>
			  <td>Last&nbsp;Bin&nbsp;User</td>
			  <td>Total&nbsp;Pending&nbsp;Hours</td>
			  <td>Pending&nbsp;Current&nbsp;Bin&nbsp;Hours</td>
			  <td>Pending&nbsp;Current&nbsp;User&nbsp;Hours</td>
			  <td>Slab</td>
			  <td>Last&nbsp;Remarks</td>
			  <td>Ticket&nbsp;Status</td>
			  <td>Security&nbsp;Deposit</td>
			  <td>SLA&nbsp;Status</td>
			  <td>Flag&nbsp;Remarks</td>
			 </tr>
          </thead>
          <tbody>
           <logic:iterate id="ticketList" name="reportForm" property="qrcReportPojos">
            <tr>
              <td>${ticketList.ticketId}</td>
              <td>${ticketList.crfId}</td>
              <td>${ticketList.customerId}</td>
              <td>${ticketList.custFname}&nbsp;${ticketList.custLname}</td>
			  <td>${ticketList.rmn}</td>
              <td><bean:write name="crmRoles" property="displayEnum(AllStatus,${ticketList.status })" scope="session" /></td>
              <td><bean:write name="crmRoles" property="displayEnum(Product,${ticketList.product})" scope="session" />&nbsp;<bean:write
                            name="crmRoles" property="displayEnum(ServiceType,${ticketList.serviceType})" scope="session" /></td>
			  <td><bean:write name="crmRoles" property="displayEnum(NP,${ticketList.LMOName})" scope="session" /></td>
              
			<td>
		
			  ${ticketList.option82}
			  
			  </td>
              <td>${ticketList.installationAddress}</td>
			  <td><bean:write name="crmRoles" property="displayEnum(qrcType,${ticketList.qrcType})" scope="session" /></td>
              <td>${ticketList.category}</td>
              <td>${ticketList.subCategory}</td>
			  <td>${ticketList.subSubCategory}</td>
              <td><bean:write name="crmRoles" property="reportXmlDate(${ticketList.raisedDate})" scope="session"/></td>
              <td>${ticketList.raiseUser}</td>
			  <td>
			  <bean:write name="crmRoles" property="displayEnum(functionalBins,${ticketList.currentBin})" scope="session" />
			  </td>
			  <td><bean:write name="crmRoles" property="reportXmlDate(${ticketList.inboxCreatedTime})" scope="session"/></td>
			  <td>${ticketList.inboxUserId}</td>
			   <td><bean:write name="crmRoles" property="reportXmlDate(${ticketList.inboxLastmodifiedTime})" scope="session"/></td>
			  <td>${ticketList.inboxCreatedBy}</td>
			  <td>${ticketList.totalPendingHours}</td>
              <td>${ticketList.pendingCurrentBinHours}</td>
			  <td>${ticketList.pendingCurrentUserHours}</td>
			   <td>${ticketList.slab}</td>
			   <td>${ticketList.remarks}</td>
			   <td><bean:write name="crmRoles" property="displayEnum(AllStatus,${ticketList.ticketStatus })" scope="session" /></td>
			   <td>${ticketList.securityDeposit}</td>
			   <td>${ticketList.slaStatus}</td>
			   <td>${ticketList.flagRemarks}</td>
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
		  </form>
      </div>
      <p class="clear" />
    </div>
  </div>
</div>