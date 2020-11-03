<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
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
				<bean:define id="activeMenu" value="firstAssignedBin" />
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
				<h4>First Assigned Bin Report</h4>

				<div class="relative inner_left_lead">
					<form action="/qrcReportAction" id="IDFABTicket" method="post">
						<html:hidden name="reportForm" property="toSearch" value="true" />
						<div class="floatl">
							<p class="floatl clear">
								<strong> From Date<span
									class="error_message verticalalignTop">*</span></strong>
								<html:text property="fromDate" styleId="fromDate"
									name="reportForm" styleClass="tcal tcalInput"
									readonly="readonly"></html:text>
								<font></font>
							</p>
							<p class="floatl marginleft30">
								<strong> To Date<span
									class="error_message verticalalignTop">*</span></strong>
								<html:text property="toDate" styleId="toDate" name="reportForm"
									styleClass="tcal tcalInput" readonly="readonly" />
								<font></font>
							</p>
							<p class="floatl marginleft30">
								<strong>Current Bin<span
									class="error_message verticalalignTop">*</span></strong> <span
									class="LmsdropdownWithoutJs"><html:select
										property="functionalBinId" name="reportForm"
										styleId="qrcFuncBinId">
										<html:option value="">All</html:option>
										<html:optionsCollection property="functionalBins"
											name="reportForm" label="categoryValue" value="categoryId" />
									</html:select> <font></font> </span>
							</p>
							<p class="marginleft30 floatl">
								<strong>QRC Type</strong> <span class="LmsdropdownWithoutJs">
									 <html:select name="reportForm" property="qrcType">
                  				 <html:option value="">All</html:option>
				   				<html:option value="R">Request</html:option>
				   				<html:option value="C">Complaint</html:option>
              </html:select>
								</span>
							</p>
							<p class="floatl clear">
								<strong>Category</strong> <span class="LmsdropdownWithoutJs">
									<html:select property="qrcCategoryId" name="reportForm"
										styleId="qrcCatId"
										onchange="populateSubCategoriesforReport(this.value,'qrcSubCatId');">
										<html:option value="0">All</html:option>
										<logic:notEmpty name="reportForm" property="qrcCategoryList">
											<html:optionsCollection property="qrcCategoryList"
												name="reportForm" label="qrcCategory" value="qrcCategoryId" />
										</logic:notEmpty>
									</html:select>
								</span>
							</p>
							<p class="floatl marginleft30">
								<strong>Sub Category</strong> <span class="LmsdropdownWithoutJs">
									<html:select property="qrcSubCategoryId" name="reportForm"
										styleId="qrcSubCatId"
										onchange="populateSubSubCategoriesReport('qrcCatId','qrcSubSubcatId',this.value);">
										<html:option value="">All</html:option>
										  <logic:notEmpty property="qrcSubCategoryList" name="reportForm">
                 						 <html:optionsCollection property="qrcSubCategoryList" name="reportForm" label="qrcSubCategory" value="qrcSubCategoryId" />
                 						 </logic:notEmpty>
									</html:select>
								</span>
							</p>
							<p class="floatl marginleft30">
								<strong>Sub Sub Category</strong> <span
									class="LmsdropdownWithoutJs"><html:select
										property="qrcSubCategoryId" name="reportForm"
										styleId="qrcSubSubcatId">
										<html:option value="">All</html:option>
										<logic:notEmpty property="qrcSubSubCategoryList" name="reportForm">
                 						 <html:optionsCollection property="qrcSubSubCategoryList" name="reportForm" label="qrcSubSubCategory" value="qrcSubSubCategoryId" />
                  						</logic:notEmpty>
									</html:select> </span>
							</p>
						</div>
						<br class="clear" />
						<div class="floatr buttonPlacement">
							
								<a href="#" class="main_button" id="IDFABSubmit">Search</a>
							
						</div>
						<p class="floatl clear">&nbsp;</p>
						<br class="clear" />
						
					</form>
				</div>
				<logic:notEmpty name="reportForm" property="qrcReportPojos">
					<div class="data-table">
						<table id="reportDataTable">
							<thead>
								<tr>
									<td>Customer&nbsp;ID</td>
									<%-- <td>Customer&nbsp;Name</td>--%>
									<td>Customer&nbsp;Status</td>
									<td>Service</td>
									<td>Ticket&nbsp;ID</td>
									<td>Ticket&nbsp;Type</td>
									<td>Category&nbsp;Name</td>
									<td>SubCategory&nbsp;Name</td>
									<td>Sub&nbsp;SubCategory&nbsp;Name</td>
									<td>Raised&nbsp;Date</td>
									<td>Raised&nbsp;By</td>
									<td>Ticket&nbsp;Status</td>
									<td>Resolved&nbsp;By</td>
									<td>BIN&nbsp;Name</td>
									<td>Resolved&nbsp;Date</td>
									<td>Resolution</td>
									<td>RCA&nbsp;Name</td>
									<td>Attribute&nbsp;Name</td>
									<td>Resolution&nbsp;Time</td>
									<td>Field&nbsp;Visit</td>
									<td>Slab</td>
									<td>First&nbsp;Assign&nbsp;User</td>
									<td>Total&nbsp;Duration&nbsp;Assigned&nbsp;Time</td>
									<td>Total&nbsp;Duration&nbsp;Remarks&nbsp;Time</td>
									<td>Ticket&nbsp;Remarks</td>
								</tr>
							</thead>
							<tbody>
								<logic:iterate id="ticketList" name="reportForm"
									property="qrcReportPojos">
									<tr>
										<td>${ticketList.customerId}</td>
										<%--<td>${ticketList.custFname}&nbsp;${ticketList.custLname}</td>--%>
										<td><bean:write name="crmRoles" property="displayEnum(AllStatus,${ticketList.status})" scope="session" /></td>
										<td><bean:write name="crmRoles" property="displayEnum(Product,${ticketList.product})" scope="session" /></td>
										<td>${ticketList.ticketId}</td>
										<td><bean:write name="crmRoles" property="displayEnum(qrcType,${ ticketList.qrcType })"/></td>
										<td>${ticketList.category}</td>
										<td>${ticketList.subCategory}</td>
										<td>${ticketList.subSubCategory}</td>
										<td><bean:write name="crmRoles" property="reportXmlDate(${ticketList.raisedDate})" scope="session" /></td>
										<td>${ticketList.raiseUser}</td>
										<td><bean:write name="crmRoles" property="displayEnum(qrcTicketStatus,${ticketList.ticketStatus})"/></td>
										<td>${ticketList.resolvedBy}</td>
										<td>${ticketList.binName}</td>
										<td><bean:write name="crmRoles" property="reportXmlDate(${ticketList.srResolvedOn})" scope="session" /></td>
										<td>${ticketList.resolutionCode}</td>
										<td>${ticketList.rca}</td>
										<td>${ticketList.attributedTo}</td>
										<td>${ticketList.resolutionTime}</td>
										<td><c:choose><c:when test="${ticketList.fieldVisit eq 'H'}">Home Visit</c:when><c:when test="${ticketList.fieldVisit eq 'F'}">Field Visit</c:when><c:otherwise>No</c:otherwise></c:choose></td>
										<td>${ticketList.slab}</td>
										<td>${ticketList.firstAssignUser}</td>
										<td>${ticketList.totDurAssignTime}</td>
										<td>${ticketList.totDurRemarkTime}</td>
										<td>${ticketList.remarks}</td>
									</tr>
								</logic:iterate>
							</tbody>
						</table>
					</div>
				</logic:notEmpty>
				<br class="clear" />
				<logic:empty name="reportForm" property="qrcReportPojos">
					<logic:equal value="true" name="reportForm" property="toSearch">
       					 No Record Found.
        			</logic:equal>
				</logic:empty>
			</div>

			<p class="clear" />
			
  <div class="relative inner_left_lead">
		
	<br class="clear" />
   </div>
</div>
<p class="clear"/>