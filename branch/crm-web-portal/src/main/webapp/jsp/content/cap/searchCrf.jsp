<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<%@taglib uri="/WEB-INF/tlds/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/tlds/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/tlds/displaytag.tld" prefix="display"%>
<%@taglib uri="/WEB-INF/tlds/struts-html.tld" prefix="html"%>
<html>
<head>
<script>
 $(document).ready(function(e) {
    $('form#searchCRF').keypress(function(e) {
     // return e.which !== 13;
     return true;
   });
});
</script>
</head>
<body>
<div class="loadingPopup hidden"></div>
<div id="section" >
<html:form action="/crmCap" styleId="searchCAF" method="post">
 <div class="section">
   <h2>CAF Entry</h2>
   <div class="success_message" >
		<logic:messagesPresent message="true">
   		<html:messages id="message" message="true">
		<bean:write name="message" />
		</html:messages>
		</logic:messagesPresent>
	</div>
   	<div class="inner_section">
   	<span class="error_message"><html:errors property="appError" /></span>
    	<div class="INA marginleft10">
    			<p class="floatl relative"><strong>CAF Number<span class="error_message verticalalignTop">*</span></strong> 
    				<html:text styleClass="textbox" name="crmCapForm" property="customerDetailsPojo.crfId" maxlength="8"></html:text>
    			</p>
     			<p class="floatl marginleft15">
				    <strong>Service Name<span class="error_message verticalalignTop">*</span></strong> 
     				<span class="dropdownWithoutJs">
     					<html:select property="customerDetailsPojo.product" name="crmCapForm" styleId="productType">
             				<html:option value="">Select Service Name</html:option>
             				 <logic:notEmpty  name="crmCapForm" property="productTypeList">
             				<html:optionsCollection name="crmCapForm" property="productTypeList" label="contentName" value="contentValue" />
             				</logic:notEmpty>
         				</html:select>
     				</span>
     		   </p>
     		   <p class="floatl marginleft15 ">
     		   		<strong>Service Type<span class="error_message verticalalignTop">*</span></strong> 
     		   		<span class="dropdownWithoutJs">
     		   			<html:select property="customerDetailsPojo.serviceType" name="crmCapForm">
     		   				<html:optionsCollection name="crmCapForm" property="serviceTypes" label="contentName" value="contentValue"/>
     		   			</html:select>
     		   		</span>
     		   </p>
    			<p class="floatl marginleft30 margintop15">
     				<strong>&nbsp</strong>
     				<html:link href="#" styleId="submitSearchCRF" styleClass="yellow_button">Proceed</html:link>
   				</p>
    	</div>
    	<p class="clear" style=" height:250px;"></p>
     </div>
 </div>
</html:form>
</div>
</body>
</html>
