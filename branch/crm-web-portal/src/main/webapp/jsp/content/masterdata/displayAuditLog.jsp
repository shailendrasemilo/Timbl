<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="/WEB-INF/tlds/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/tlds/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/tlds/displaytag.tld" prefix="display"%>
<link href="../../../css/disaplyTableHelper.css" rel="stylesheet" media="screen" />
	<link href="../../../css/color-${initParam.client }.css" rel="stylesheet" media="screen">
	
<logic:present name="CrmAuditLogPojo" >	
       <display:table id="activiyLogs" name="${sessionScope.CrmAuditLogPojo}" class="dataTable" style="width:100%" requestURI="" pagesize="10" >
	    <display:setProperty name="paging.banner.placement" value="bottom" />
			<display:column title="Sr.No" maxLength="5"><bean:write name="activiyLogs_rowNum" /></display:column>
			<display:column title="Event">
      		<bean:write name="crmRoles" property="displayEnum(CrmActionEnum,${activiyLogs.events})" scope="session" />
			</display:column>
			<display:column title="Old Values">
				<bean:write name="crmRoles" property="displayValues(${activiyLogs.oldValues})" scope="session" />
			</display:column>
			<display:column title="New Values">
				<bean:write name="crmRoles" property="displayValues(${activiyLogs.newValues})" scope="session" />
			</display:column>	
			<display:column title="Remarks" property="remarks"></display:column>
			<display:column title="Created Time">
				<bean:write name="crmRoles" property="xmlDate(${activiyLogs.createdTime})" scope="session"/>
			</display:column>
			<display:column title="Created By" property="createdBy"></display:column>
			<%--
			<display:column title="Client IP" property="clientIp" maxLength="15"></display:column>
			<display:column title="Server IP" property="serverIp" maxLength="15"></display:column>
			<display:column title="Service IP" property="serviceIp" maxLength="15"></display:column>
			--%>
	</display:table>
</logic:present>
<logic:empty name="CrmAuditLogPojo" ><p style="font:12px arial;">Nothing found to display.</p></logic:empty>
