<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@page import="com.np.tele.crm.utils.StringUtils"%>
<%@page import="com.np.tele.crm.service.client.CrmCustMyAccountPojo"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<%@taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<script>
	$( document ).ready( function(){
		if ( $( '#scCustRMN' ).val() >= 0 ) {
			$( '#scCustRMN' ).val( '' );
		}
		if ( $( '#scCustId' ).val() >= 0 ) {
			$( '#scCustId' ).val( '' );
		}
	} );
</script>
<div style="margin-top: 0px; border-radius: 0px;">
	<div class="col-lg-12 col-sm-12 col-md-12" style="padding: 0;">
		<div class="payable">
			<h1 class="payment-heading">MAKE PAYMENT</h1>
			<h2 class="payment-option">Personal Details</h2>
			<p class="payment-option-hint typeface-js">Enter your Customer ID or Registered Mobile Number</p>
		</div>
		<div class="pInfo hide"></div>
		<logic:messagesPresent property="appError">
			<bean:define id="hasErrors" value="true"></bean:define>
		</logic:messagesPresent>
		<div class="msgerror ${ hasErrors ? '' : 'hide' }">
			<html:errors />
		</div>
		<logic:messagesPresent message="true">
			<html:messages id="message" message="true">
				<div class="msgsuccess">
					<bean:write name="message" />
				</div>
			</html:messages>
		</logic:messagesPresent>
		<html:form action="/quickPay" styleId="scQuickPayForm">
			<div class="payment-box">
				<div class="form-registration">
					<input class="inp-inp" id="scCustId" name="customerDetailsPojo.customerId" placeholder="Enter your Customer ID" type="text" maxlength="15"
						onkeyup="removeChars(this);" onblur="disableQuickPayFields(this);">
					<div class="clear"></div>
					<div class="saperate">OR</div>
					<div class="clear"></div>
					<input class="inp-inp" id="scCustRMN" name="customerDetailsPojo.rmn" placeholder="Enter your RMN" type="text" maxlength="10"
						onblur="disableQuickPayFields(this);" onkeyup="removeChars(this);">
					<div class="clear"></div>
					<input type="button" value="&nbsp; &nbsp; &nbsp; Go &nbsp; &nbsp; &nbsp" id="scQuickPayBtn" class="btn submit_contact" />
				</div>
			</div>
		</html:form>
	</div>
</div>
