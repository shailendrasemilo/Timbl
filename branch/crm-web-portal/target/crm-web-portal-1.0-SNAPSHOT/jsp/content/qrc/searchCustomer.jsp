<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib uri="/WEB-INF/tlds/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/tlds/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/tlds/displaytag.tld" prefix="display"%>
<%@taglib uri="/WEB-INF/tlds/struts-html.tld" prefix="html"%>
<script>
$(document).ready(function(){
	$("#search_by_Cust :input").bind("keypress",function(evt) {
		var keyCode = evt.which || evt.keyCode;
		if (keyCode == 13) {
			$('#qrcSubmitSearchCustomer').triggerHandler('click');
	    }
	});
});
</script>
<div class="loadingPopup hidden"></div>
<div id="section">
	<html:form action="/manageQrc" styleId="search_by_Cust" method="post">
		<div class="section">
			<h2>Search Customer</h2>
			<div class="inner_section">
				<span class="error_message"><html:errors property="appError" /></span>
				<div class="QRC marginleft10">
			    <p class="floatl relative">
				<strong>CAF Number / Customer ID</strong>
				 <html:text styleClass="textbox" name="qrcForm" property="searchString" maxlength="15" styleId="searchStringId"></html:text>
				 </p>
				 <p class="floatl marginleft30 margintop15">
				 <strong>&nbsp;</strong>
				 <html:link href="#" styleId="qrcSubmitSearchCustomer" styleClass="yellow_button">Search</html:link>
				 </p>
				</div>
				<p class="clear" style=" height:250px;"></p>
				</div>
			</div>
	  </html:form>
</div>