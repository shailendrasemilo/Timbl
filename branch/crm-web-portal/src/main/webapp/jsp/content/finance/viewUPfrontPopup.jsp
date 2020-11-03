<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<%@taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<%@taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<html>
<head>
<link href="css/common.css" rel="stylesheet" media="screen" />
<link href="css/color-${initParam.client }.css" rel="stylesheet" media="screen" />
<link href="css/userManagement.css" rel="stylesheet" media="screen" />
<link href="css/displaytag.css" rel="stylesheet" type="text/css" />
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
</head>
<body >
<div class="popUp1">
<div class="popUpContainer">
     <div class="contentContainer">
       <div class="success_message" >
		<logic:messagesPresent message="true">
   		<html:messages id="message" message="true">
		<bean:write name="message" />
		</html:messages>
		</logic:messagesPresent>
	  </div> 
	  <p><span class="error_message"><html:errors property="appError" /></span><html:errors  property="message"/></p>
       
    <logic:notEmpty name="financeForm" property="crmCustomerDetailsPojos">
	    <div class="roleDisplayTable widthAuto">
		<p class="headerDisplayTable"  ><span style="width:auto">CAF Payment Details</span></p>
			<div class="contentDisplayTable" style="margin-top: -25px;">
				<display:table id="data"
						name="sessionScope.financeForm.crmCustomerDetailsPojos" requestURI="" class="dataTable" style="width:100%" >
						<display:column property="crfId" title="CAF No"/>
						<display:column  title="CAF Punched Date">
							<bean:write name="crmRoles" property="toDate(${data.crfDate})" scope="session"/>
						</display:column>
						<display:column title="Service Name">
							<bean:write name="crmRoles" property="displayEnum(Product,${ data.product})" scope="session" />
						</display:column>
						<display:column  title="Customer ID">
							<logic:notEmpty name="data" property="customerId"> 
								<bean:write name="data" property="customerId"/>
							</logic:notEmpty>
							<logic:empty name="data" property="customerId"> - </logic:empty>
						</display:column>				
						<display:column title="Installation Status">
							<bean:write name="crmRoles" property="displayEnum(InstallationStatus,${ data.crmPaymentDetailsPojo.installationStatus})" scope="session"/>  
						</display:column>
						<display:column  title="Entity Name">
							<bean:write name="crmRoles" property="displayEnum(EntityType,${data.crmPaymentDetailsPojo.entityType})" scope="session" />
						</display:column>
						<display:column  title="Mode">
							<bean:write name="crmRoles" property="displayEnum(PaymentMode,${data.crmPaymentDetailsPojo.paymentMode})" scope="session" />
						</display:column>  
						<display:column  title="Channel">
							<logic:notEmpty name="data" property="crmPaymentDetailsPojo.paymentChannel"> 
								<bean:write name="crmRoles" property="displayEnum(PaymentChannel,${data.crmPaymentDetailsPojo.paymentChannel})" scope="session" />
							</logic:notEmpty>
							<logic:empty name="data" property="crmPaymentDetailsPojo.paymentChannel"> - </logic:empty>
						</display:column>
						<display:column property="crmPaymentDetailsPojo.receiptNo" title="Cash Receipt No"/>				
				</display:table>
		    </div>
		</div>
	</logic:notEmpty>
       
      </div>
</div>
</div>
</body>
</html>