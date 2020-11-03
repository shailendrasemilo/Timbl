<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib uri="/WEB-INF/tlds/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/tlds/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/tlds/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/tlds/displaytag.tld" prefix="display"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
</head>
<body>
  <div id="section">
    <html:form action="/profileSearch" method="post">
      <div class="section">
        <h2>Customer Profile Search Results</h2>
        <span class="error_message"><html:errors property="appError" /></span>
        <p class="success_message">
          <logic:messagesPresent message="true">
            <html:messages id="message" message="true">
              <bean:write name="message" />
              <br />
            </html:messages>
          </logic:messagesPresent>
        </p>
        <logic:notEmpty name="customerProfileForm" property="crmCustomerDetailsPojos">
          <display:table id="tableList" name="${customerProfileForm.crmCustomerDetailsPojos}" class="dataTable" style="width:100%" pagesize="15" requestURI="">
            <display:setProperty name="paging.banner.placement" value="bottom" />
            <display:column title="Sr.No">
              <bean:write name="tableList_rowNum" />
            </display:column>
            <display:column title="CAF ID">
              <logic:notEmpty name='tableList' property='crfId'>
                <a href="javascript:viewCRF('<bean:write name="tableList" property="recordId"/>',0,'customerProfile')" title="View CAF Details"><bean:write
                    name="tableList" property="crfId" /></a>
              </logic:notEmpty>
              <logic:empty name='tableList' property='crfId'>
					-
					</logic:empty>
            </display:column>
            <display:column title="Customer ID">
              <logic:notEmpty name='tableList' property='customerId'>
                <c:if test="${(tableList.status ne 'I' and tableList.crfStage ne 'CL')}">
                  <a href="manageQrc.do?method=searchCustomer&workingId=<bean:write name='tableList' property='customerId'/>" title="View Customer Details"><bean:write
                      name="tableList" property="customerId" /></a>
                </c:if>
                <c:if test="${(tableList.status eq 'I' or tableList.crfStage eq 'CL')}">
                  <bean:write name="tableList" property="customerId" />
                </c:if>
              </logic:notEmpty>
              <logic:empty name='tableList' property='customerId'>
					-
			   </logic:empty>
            </display:column>
            <display:column title="Customer Name" property="custName" />
            <display:column title="Service Name">
              <bean:write name="crmRoles" property="displayEnum(Product,${tableList.product})" scope="session" />
            </display:column>
            <display:column title="Service Type">
              <bean:write name="crmRoles" property="displayEnum(ServiceType,${tableList.serviceType})" scope="session" />
            </display:column>
             <display:column title="Brand">
             <bean:write name="tableList" property="brand" />
            </display:column>
            <display:column title="Connection Type">
              <bean:write name="crmRoles" property="displayEnum(ConnectionType,${ tableList.connectionType})" scope="session" />
            </display:column>
            <display:column title="RMN" property="rmn" />
            <display:column title="RTN" property="rtn" />
            <display:column title="Email ID" property="custEmailId" />
            <display:column title="Stage">
              <logic:notEmpty name="tableList" property="crfStage">
                <bean:write name="crmRoles" property="displayEnum(CRMOperationStages,${tableList.crfStage})" scope="session" />
              </logic:notEmpty>
            </display:column>
            <display:column title="Current User" property="currentUser"></display:column>
            <display:column title="Status">
              <span title="${empty tableList.linkedCRF ? '':'Linked to:-'.concat(tableList.linkedCRF) }"><bean:write name="crmRoles" property="displayEnum(AllStatus,${tableList.status})" scope="session" /></span>
            </display:column>
          </display:table>
        </logic:notEmpty>
        <logic:notEmpty name="customerProfileForm" property="lmsPojoList">
          <display:table id="tableList" name="${customerProfileForm.lmsPojoList}" class="dataTable" style="width:100%" pagesize="15" requestURI="">
            <display:setProperty name="paging.banner.placement" value="bottom" />
            <display:column title="Sr.No" class="${tableList.lmsColor}">
              <bean:write name="tableList_rowNum" />
            </display:column>
            <display:column title="Lead ID" class="${tableList.lmsColor}">
              <a href="javascript:viewLead('<bean:write name="tableList" property="lmsId" />','customerProfile')"><bean:write name="tableList"
                  property="leadId" /></a>
            </display:column>
            <display:column title="Customer Name" class="${tableList.lmsColor}">
              <logic:notEmpty name="tableList" property="customerName">
                <bean:write name="tableList" property="customerName" />
              </logic:notEmpty>
              <logic:empty name="tableList" property="customerName"> - </logic:empty>
            </display:column>
            <display:column title="Contact No" property="contactNumber" class="${tableList.lmsColor}"/>
            <display:column title="Service" class="${tableList.lmsColor}">
              <bean:write name="crmRoles" property="displayEnum(Product,${tableList.product})" scope="session" />
            </display:column>
            <display:column title="Stage" class="${tableList.lmsColor}">
              <logic:notEmpty name="tableList" property="lmsStage">
                <bean:write name="crmRoles" property="displayEnum(CRMOperationStages,${tableList.lmsStage})" scope="session" />
              </logic:notEmpty>
            </display:column>
            <display:column title="Email ID" class="${tableList.lmsColor}">
              <logic:notEmpty name="tableList" property="emailId">
                <bean:write name="tableList" property="emailId" />
              </logic:notEmpty>
              <logic:empty name="tableList" property="emailId"> - </logic:empty>
            </display:column>
            <display:column title="Created Date" class="${tableList.lmsColor}">
              <bean:write name="crmRoles" property="toDate(${tableList.createdTime})" scope="session" />
            </display:column>
            <display:column title="Current User" property="currentUser" class="${tableList.lmsColor}"></display:column>
            <display:column title="Status" class="${tableList.lmsColor}">
              <bean:write name="crmRoles" property="displayEnum(LeadStatus,${tableList.status})" scope="session" />
            </display:column>
          </display:table>
        </logic:notEmpty>
        <logic:notEmpty name="customerProfileForm" property="srTicketsPojos">
          <display:table id="qrcTicketTable" name="${customerProfileForm.srTicketsPojos}" class="dataTable" style="width:100%" pagesize="15" requestURI="">
            <display:setProperty name="paging.banner.placement" value="bottom" />
            <display:column title="Sr.No" class="${qrcTicketTable.color }">
              <bean:write name="qrcTicketTable_rowNum" />
            </display:column>
            <display:column title="Ticket ID" class="${qrcTicketTable.color }">
              <a href="javascript:viewQRC('<bean:write name="qrcTicketTable" property="ticketId"/>','false','customerProfile','0')" title="View Ticket Details"><bean:write
                  name="qrcTicketTable" property="srId" /></a>
            </display:column>
            <display:column title="Stage" class="${qrcTicketTable.color }">
              <logic:notEmpty name="qrcTicketTable" property="functionalbinId">
              	<logic:equal name="qrcTicketTable" property="functionalbinId" value="0">-</logic:equal>
              	<logic:notEqual name="qrcTicketTable" property="functionalbinId" value="0">
					<bean:write name="crmRoles" property="displayEnum(functionalBins,${qrcTicketTable.functionalbinId})" scope="session" />
				</logic:notEqual>
              </logic:notEmpty>
              <logic:empty name="qrcTicketTable" property="functionalbinId"> - </logic:empty>
            </display:column>
            <display:column title="QRC Type" class="${qrcTicketTable.color }">
              <logic:notEmpty name="qrcTicketTable" property="qrcType">
                <bean:write name="crmRoles" property="displayEnum(qrcType,${qrcTicketTable.qrcType})" scope="session" />
              </logic:notEmpty>
            </display:column>
            <display:column title="Resolution Type" class="${qrcTicketTable.color }">
              <bean:write name="crmRoles" property="displayEnum(ticketAction,${ qrcTicketTable.resolutionType })" />
            </display:column>
            <display:column title="Category" property="qrcCategory" class="${qrcTicketTable.color }"></display:column>
            <display:column title="Sub Category" property="qrcSubCategory" class="${qrcTicketTable.color }"></display:column>
            <display:column title="Sub Sub Category" property="qrcSubSubCategory" class="${qrcTicketTable.color }"></display:column>
            <display:column title="Current User" property="currentUser" class="${qrcTicketTable.color }"></display:column>
            <display:column title="Status" class="${qrcTicketTable.color }">
              <logic:notEmpty name="qrcTicketTable" property="status">
                <bean:write name="crmRoles" property="displayEnum(qrcTicketStatus,${qrcTicketTable.status})" />
              </logic:notEmpty>
            </display:column>
            <display:column title="Created By" property="createdBy" class="${qrcTicketTable.color }"></display:column>
            <display:column title="Created Date" class="${qrcTicketTable.color }">
              <bean:write name="crmRoles" property="xmlDate(${qrcTicketTable.createdTime})" scope="session" />
            </display:column>
          </display:table>
        </logic:notEmpty>
        <logic:empty name="customerProfileForm" property="crmCustomerDetailsPojos">
          <logic:empty name="customerProfileForm" property="lmsPojoList">
            <logic:empty name="customerProfileForm" property="srTicketsPojos">
              <b class="margintop20 smsShow marginleft10"> <html:errors property="noRecordFound" /><br /> <br /> <br /> <br />
              </b>
            </logic:empty>
          </logic:empty>
        </logic:empty>
      </div>
    </html:form>
  </div>
</body>
</html>