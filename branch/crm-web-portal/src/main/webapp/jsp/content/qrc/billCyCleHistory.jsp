<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib uri="/WEB-INF/tlds/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/tlds/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/tlds/displaytag.tld" prefix="display"%>
<%@taglib uri="/WEB-INF/tlds/struts-html.tld" prefix="html"%>
<html>
    <head>
    	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="css/common.css" rel="stylesheet" media="screen" />	
        <link href="css/color-${initParam.client }.css" rel="stylesheet" media="screen" />	
		<link href="css/userManagement.css" rel="stylesheet" media="screen" />
        <link href="css/emailManagement.css" rel="stylesheet" media="screen" />
        <link href="css/masterdata.css" rel="stylesheet" media="screen" />
        <link href="css/lms.css" rel="stylesheet" media="screen" />
        <link href="css/tcal.css"  rel="stylesheet" type="text/css" />
        <link href="css/gis.css" rel="stylesheet" media="screen" />
        <link href="css/displaytag.css" rel="stylesheet" type="text/css" />
        <link href="css/ina.css" rel="stylesheet" media="screen" />
        <link href="css/qrc.css" rel="stylesheet" media="screen" />
		
		 <script src="javascript/jquery.min.js" type="text/javascript"></script>
		 <script src="javascript/qrc.js" type="text/javascript"></script>
		</head>
		<body style="background: white;">  
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
		
			<logic:notEmpty name="logForm" property="crmBillCyclePojosList" scope="session">
			<html:hidden name="logForm" property="customerRecordID"/>
		      <display:table id="billHistory" name="sessionScope.logForm.crmBillCyclePojosList" class="dataTable" style="width:100%" pagesize="10" requestURI="logAction.do">
				  	<display:setProperty name="paging.banner.placement"	value="bottom" />  
					<display:column title="Sr.No"  maxLength="5"><bean:write name="billHistory_rowNum" /></display:column>		
					<display:column title="Customer ID" property="customerId"></display:column>	
					<display:column title="Bill Date" style="width:8%;" property="billDate"></display:column>		
					<display:column title="Workflow ID" style="width:8%" property="workflowId"></display:column>	
					<display:column title="Ticket ID"  style="width:20%;" >
					${ empty billHistory.ticketId? '-' : billHistory.ticketId }
					</display:column>						
					<display:column title="Status">
					  <bean:write name="crmRoles" property="displayEnum(AllStatus,${billHistory.status})" />
					</display:column>	
					<display:column title="Created By" property="createdBy"></display:column>		
					<display:column title="Created Time" >					
					<bean:write name="crmRoles" property="toDate(${billHistory.createdTime})" scope="session"/>
					</display:column>	
				   <display:column title="Action">					
					<logic:equal name="billHistory" property="status" value="IP">
					<a onclick='if(confirm("Are you sure you want to cancel bill cycle request ?")){this.href="customerProfile.do?method=cancelBillCycle&customerID=${billHistory.customerId}&recordId=${billHistory.billCycleId}"}' href="#" >Canceled</a>					
					</logic:equal>
					</display:column >
			</display:table>
		</logic:notEmpty>
		
</body>
</html>
