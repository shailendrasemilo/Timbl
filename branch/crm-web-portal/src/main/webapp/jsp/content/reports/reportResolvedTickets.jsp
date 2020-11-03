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
	<bean:define id="activeMenu" value="resolvedTicket"/>
	 <%@include file="qrcReportMenu.jsp"%>
	</div>
<div class=" floatl manageGISRight  qrcRight">
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
		<h4> Resolved Ticket Report</h4>
	
		<html:form action="/qrcReportAction" method="post" styleId="IDTicketResolved">
		<html:hidden name="reportForm" property="toSearch" value="true"/>
					<div class="marginleft10 inner_left_lead floatl">
					<p class="floatl clear  ">
							<strong>From Date<span class="error_message verticalalignTop">*</span></strong>
							
								 <html:text property="fromDate" styleId="fromDate" name="reportForm" styleClass="tcal tcalInput" readonly="readonly"></html:text>
							<font style="margin: 2px"></font>
						</p>
						<p class="floatl marginleft30 ">
							<strong>To Date<span class="error_message verticalalignTop">*</span></strong>
							 <html:text property="toDate" styleId="toDate" name="reportForm" styleClass="tcal tcalInput" readonly="readonly" />
							<font style="margin: 2px"></font>
						</p>
						<p class="marginleft30 floatl">
              <strong>QRC Type</strong> <span class="LmsdropdownWithoutJs">
              <html:select name="reportForm" property="qrcType">
                   <html:option value="">All</html:option>
				   <html:option value="R">Request</html:option>
				   <html:option value="C">Complaint</html:option>
              </html:select> </span>
            </p>
            <p class="floatl marginleft30">
              <strong>Current Bin</strong> <span class="LmsdropdownWithoutJs">
              <html:select property="functionalBinId" name="reportForm"
                  styleId="qrcFuncBinId">
                  <html:option value="0">All</html:option>
                  <html:optionsCollection property="functionalBins" name="reportForm" label="categoryValue" value="categoryId" />
                </html:select></span>
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
					<br />
					<div class="floatr buttonPlacement">
						 <a href="#" class="main_button" id="IDTicketResolvedSubmit">Search</a>
					</div>
					<p class="floatl clear">&nbsp;</p>
					
					
<div style="width:100%;">	
<p class="floatl clear" >
 <logic:notEmpty  name="reportForm" property="qrcReportPojos">
        <table id="reportDataTable" style="width:100%"class="reportDataTable_wrapper">
          <thead>
            <tr>
             <td>Customer&nbsp;ID</td>
            <%--   <td>Customer&nbsp;Name</td>--%>
               <td>CAF&nbsp;No</td>
               <td>Customer&nbsp;Status</td>
                  <%-- <td>RMN</td>--%>
                 <td>Installation&nbsp;Adderess</td>
                 <td>Ticket&nbsp;ID</td>
			   <td>Qrc&nbsp;Type</td>
              <td>Category&nbsp;Name</td>
			  <td>SubCategory&nbsp;Name</td>
			   <td>Sub&nbsp;SubCategory&nbsp;Name</td>
			   <td>Ticket&nbsp;Status</td>
			   <td>Resolution&nbsp;Type</td>
			   <td>Raised&nbsp;Date</td>
			  <td>Raised&nbsp;User</td>
			   <td>ReOpen&nbsp;Date</td>
			   <td>Resolved&nbsp;Date</td>
			   <td>Resolved&nbsp;User</td>
              <td>Resolution&nbsp;Name</td>
			  <td>RCA&nbsp;Name</td>
              <td>Attribute&nbsp;Name</td>
              <td>Field&nbsp;Visit</td>
               <td>BIN&nbsp;Name</td>
			   <td>LMO</td>
			   <td>NAS&nbsp;Port/&nbsp;Option&nbsp;82</td>
			   <td>Service&nbsp;Name</td>
			   <td>Resolution&nbsp;Time&nbsp;(d:h:m:s)</td>
			  <td>Slab</td>
			  <td>SLA&nbsp;Status </td>	
			  <td>Configured&nbsp;SLA</td>
			  <td>EOC Resolved Bin Time </td>
			   <td>Remarks</td>
            </tr>
          </thead>
          <tbody>
          
        <logic:iterate id="ticketList" name="reportForm" property="qrcReportPojos">
            <tr>
              <td>${ticketList.customerId}</td>
             <%--    <td>${ticketList.custFname}&nbsp;${ticketList.custLname}</td>--%>
              <td>${ticketList.crfId}</td>
			   <td>
			    <bean:write name="crmRoles" property="displayEnum(AllStatus,${ticketList.status })" scope="session" />
			   </td>
			    <%--  <td>${ticketList.rmn}</td>--%>
              <td>${ticketList.installationAddress}</td>
              <td>${ticketList.ticketId}</td>
              <td>
               <bean:write name="crmRoles" property="displayEnum(qrcType,${ticketList.qrcType })" scope="session" />
              </td>
              <td>${ticketList.category}</td>
               <td>${ticketList.subCategory}</td>
			   <td>${ticketList.subSubCategory}</td>
              <td>
               <bean:write name="crmRoles" property="displayEnum(AllStatus,${ticketList.ticketStatus })" scope="session" />
              </td>
			   <td>
              <bean:write name="crmRoles" property="displayEnum(ticketAction,${ ticketList.resolutionType })" /> 
              </td>
              <td>
               <bean:write name="crmRoles" property="reportXmlDate(${ticketList.raisedDate})" scope="session"/>
              </td>
              <td>${ticketList.raiseUser}</td>
              <td>
              <bean:write name="crmRoles" property="reportXmlDate(${ticketList.srReopenedOn})" scope="session"/>
              </td>
			   <td>
			    <bean:write name="crmRoles" property="reportXmlDate(${ticketList.srResolvedOn})" scope="session"/>
			   </td>
              <td>${ticketList.resolvedBy}</td>
              <td>${ticketList.resolutionCode}</td>
               <td>${ticketList.rca}</td>
                <td>${ticketList.attributedTo}</td>
                 <td><c:choose><c:when test="${ticketList.fieldVisit eq 'H'}">Home Visit</c:when><c:when test="${ticketList.fieldVisit eq 'F'}">Field Visit</c:when><c:otherwise>No</c:otherwise></c:choose></td>
                  <td>
				  	<logic:notEqual value="0" name="ticketList" property="binName">
				  <bean:write name="crmRoles" property="displayEnum(functionalBins,${ticketList.binName})" scope="session" />
				  </logic:notEqual>
				  </td>
                 <td><bean:write name="crmRoles" property="displayEnum(PartnerPojo,${ticketList.LMOName})" scope="session" /></td>
                 <td> ${ticketList.option82}</td>
                 <td>
				  <bean:write name="crmRoles" property="displayEnum(Product,${ticketList.product})" scope="session" />&nbsp;<bean:write name="crmRoles" property="displayEnum(ServiceType,${ticketList.serviceType})" scope="session" />
				 </td>
                 <td>${ticketList.resolutionDayTime}</td>
                 <td>${ticketList.slab}</td>
                 <td>${ticketList.slaStatus}</td>
                 <td>${ticketList.configuredSLA}</td>
                 <td><bean:write name="crmRoles" property="reportXmlDate(${ticketList.createdTime})" scope="session" /></td>
                 <td>${ticketList.remarks}</td>
            </tr>
          </logic:iterate>
           
          </tbody>
        </table>
        <br class="clear" />
         </logic:notEmpty>
             <logic:empty name="reportForm" property="qrcReportPojos">
        		<logic:equal value="true" name="reportForm" property="toSearch">
        	No Record Found.
      	 </logic:equal>
     </logic:empty>
		 </p>
      </div>
	
					
					<br class="clear" />
				</html:form>
		
<p class="clear"/>

  <div class="relative inner_left_lead">
		
	<br class="clear" />
   </div>
</div>
<p class="clear"/>