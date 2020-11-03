var oldVal = {
	rmn : "", rtn : "", altMob : "", email : "", altEmail : "", billMode : "", billAddr1 : "", billAddr2 : "", billAddr3 : "", billAddrC : "",
	billAddrS : "",
};

var qrcROL = {
	name : "updateCustName", contactPerson : "updateContactPerson", authorizedPerson : "updateAuthorizedPerson", rmn : "updateRMN",
	rtn : "updateRTN", altMob : "updateAltMob", email : "updateEmail", altEmail : "updateAltEmail", billMode : "updateBillMode",
	connectionType : "updateConnectionType", billAddr : "updateBillingAddress", instAddr : "updateInstallationAddress",
};

$( document ).ready( function(){

	$( '#resetPswrdBt' ).click( function(){
		if ( validateForgotPaswrd() && confirm( "Do you want to reset the Password" ) ) {
			$( '.popup-loading' ).removeClass( 'hide' );
			$( '#resetPswrdForm' ).attr( 'action', 'login.do?method=forgotPassword' );
			$( '#resetPswrdForm' ).submit();
		}
	} );

	$( '#chngePswrdBtn' ).click( function(){
		if ( validateChangePassword() && confirm( "Do you want to change the Password" ) ) {
			$( '.popup-loading' ).removeClass( 'hide' );
			$( '#changePasswordForm' ).attr( 'action', 'user.do?method=changePassword' );
			$( '#changePasswordForm' ).submit();
		}
	} );

	$( '#submitLogTicket' ).click( function(){
		if ( validateLogTicket() && confirm( 'Are you sure?' ) ) {
			$( '.popup-loading' ).removeClass( 'hide' );
			$( '#logTicketForm' ).attr( 'action', 'user.do?method=logTicket' );
			$( '#logTicketForm' ).submit();
		}
	} );

	// =============== Update RMN ===============
	$( '#scRMN .icon-edit' ).click( function(){
		$( this ).parent().hide();
		$( this ).parent().next( '.col-info-editor' ).show();

		oldVal.rmn = $( '#sc_rmn' ).val();
	} );

	$( '#scRMN .cancel' ).click( function(){
		HideUpdate( this );
		$( '#sc_rmn' ).val( oldVal.rmn );
	} );

	$( '#scRMN .update' ).click( function(){
		var field = $( '#sc_rmn' );
		updateRMN( this, field );
	} );

	// =============== Update RTN ===============
	$( '#scRTN .icon-edit' ).click( function(){
		$( this ).parent().hide();
		$( this ).parent().next( '.col-info-editor' ).show();

		oldVal.rtn = $( '#sc_rtn' ).val();
	} );

	$( '#scRTN .cancel' ).click( function(){
		HideUpdate( this );
		$( '#sc_rtn' ).val( oldVal.rtn );
	} );

	$( '#scRTN .update' ).click( function(){
		var field = $( '#sc_rtn' );
		updateRTN( this, field );
	} );

	// =============== Update AMN ===============
	$( '#scAMN .icon-edit' ).click( function(){
		$( this ).parent().hide();
		$( this ).parent().next( '.col-info-editor' ).show();

		oldVal.altMob = $( '#sc_amn' ).val();
	} );

	$( '#scAMN .cancel' ).click( function(){
		HideUpdate( this );
		$( '#sc_amn' ).val( oldVal.altMob );
	} );

	$( '#scAMN .update' ).click( function(){
		var field = $( '#sc_amn' );
		updateAMN( this, field );
	} );

	// =============== Update RegEmail ===============
	$( '#scRegEmail .icon-edit' ).click( function(){
		$( this ).parent().hide();
		$( this ).parent().next( '.col-info-editor' ).show();

		oldVal.email = $( '#sc_reg_email' ).val();
	} );

	$( '#scRegEmail .cancel' ).click( function(){
		HideUpdate( this );
		$( '#sc_reg_email' ).val( oldVal.email );
	} );

	$( '#scRegEmail .update' ).click( function(){
		var field = $( '#sc_reg_email' );
		updateRegEmail( this, field );
	} );

	// =============== Update AltEmail ===============
	$( '#scAltEmail .icon-edit' ).click( function(){
		$( this ).parent().hide();
		$( this ).parent().next( '.col-info-editor' ).show();

		oldVal.altEmail = $( '#sc_alt_email' ).val();
	} );

	$( '#scAltEmail .cancel' ).click( function(){
		HideUpdate( this );
		$( '#sc_alt_email' ).val( oldVal.altEmail );
	} );

	$( '#scAltEmail .update' ).click( function(){
		var field = $( '#sc_alt_email' );
		updateAltEmail( this, field );
	} );

	// =============== Update BillAddr ===============
	$( '#updateBillAddr' ).click( function(){
		if ( validateBillAddr() && confirm( 'Are you sure?' ) ) {
			$( '#updateAddressForm' ).attr( 'action', 'user.do?method=updateBillAddr' );
			$( '#updateAddressForm' ).submit();
		}
	} );

	// =============== Update Bill Mode ===============
	$( '#EBILL' ).click( function(){
		if ( $( '#BillChk' ).is( ':checked' ) ) {
			$( '.popup-loading' ).removeClass( 'hide' );
			selfcareForm.getCustomerDetailsPojo( changeBillMode );
		}
	} );

	// =============== Send Verification Link ===============
	$( '#scVerifyEmail' ).click( function(){
		$( '.popup-loading' ).removeClass( 'hide' );
		crmDwr.sendEmailVerificationLink( function( result ){
			if ( isBlank( result ) ) {
				$( '.popup-loading' ).addClass( 'hide' );
				alert( "Unable to process your request." );
			}
			else {
				$( '.popup-loading' ).addClass( 'hide' );
				alert( result[ 1 ] );
			}
		} );
	} );

	$( '#scQuickPayBtn' ).click( function(){
		if ( validateSelfcareQuickPay() ) {
			$( '#scQuickPayForm' ).attr( 'action', 'quickPay.do?method=quickPayAction' );
			$( '#scQuickPayForm' ).submit();
		}
	} );

	$( '#scQuickPayTransaction' ).click( function(){
		if ( validateSelfcareQuickPayTransaction() ) {
			$( '#scQuickPayTransactionForm' ).attr( 'action', 'quickPay.do?method=quickPayTransaction' );
			$( '#scQuickPayTransactionForm' ).submit();
		}
	} );

	$( '#scQuickPayConfirm' ).click( function(){
		$( '#scQuickPayConfirmForm' ).attr( 'action', 'quickPay.do?method=quickPayRedirect' );
		$( '#scQuickPayConfirmForm' ).submit();
	} );

	$( '#scQuickPayTransactionBack' ).click( function(){

		location.href = 'quickPay.do?method=quickPayPage';
		return false;
	} );

	$( '#scQuickPayConfirmBack' ).click( function(){
		location.href = 'quickPay.do?method=quickPayAction';
		return false;

	} );

	$( '.linkChangePlan' ).click( function(){
		parent.jQuery.fancybox.close();
		parent.$( '#btnchangeplan' ).trigger( 'click' );
	} );

	$( '#makenewpayment' ).click( function(){

		$( '#scQuickPayResponseForm' ).attr( 'action', 'quickPay.do?method=quickPayPage' );
		$( '#scQuickPayResponseForm' ).submit();

	} );

	$( '#scPayNow' ).click( function(){
		$( '#quickPayFormId' ).attr( 'action', 'quickPay.do?method=quickPayAction' );
		$( '#quickPayFormId' ).submit();
	} );

	$( '#scPaymentPdf' ).click( function(){
		$( '#scQuickPayResponseForm' ).attr( 'target', 'newStuff' );
		$( '#scQuickPayResponseForm' ).attr( 'action', 'generatepdf.do' );
		$( '#scQuickPayResponseForm' ).submit();
	} );

	$( '#printBtn' ).click( function(){
		var divElements = document.getElementById( 'printTable' ).innerHTML;
		var oldPage = document.body.innerHTML;
		document.body.innerHTML = '<html><head><title></title></head><body>' + divElements + '</body>';
		window.print();
		document.body.innerHTML = oldPage;
		window.location.reload();
	} );

	$( '#pymntCntrSearch' ).click( function(){
		var pin = $( '#selectPC' ).val();
		if ( validatePin( pin ) ) {
			$( '.popup-loading' ).removeClass( 'hide' );
			$( '#pymntCntrsForm' ).attr( 'action', 'quickPay.do?method=getPaymentCenterDetails' );
			$( '#pymntCntrsForm' ).submit();
		}
		else {
			alert( "Please Select 'Pincode'" );
		}
	} );
	$( '#scAmount' ).on( 'keyup keypress', function( e ){
		var keyCode = e.keyCode || e.which;
		if ( keyCode === 13 ) {
			if ( validateSelfcareQuickPayTransaction() ) {
				$( '#scQuickPayTransactionForm' ).attr( 'action', 'quickPay.do?method=quickPayTransaction' );
				$( '#scQuickPayTransactionForm' ).submit();
			}
			else {
				e.preventDefault();
				return false;
			}
		}
	} );
	$( "#scCustRMN,#scCustId" ).keypress( function( e ){
		if ( ( e.which && e.which == 13 ) || ( e.keyCode && e.keyCode == 13 ) ) {
			if ( validateSelfcareQuickPay() ) {
				$( '#scQuickPayForm' ).attr( 'action', 'quickPay.do?method=quickPayAction' );
				$( '#scQuickPayForm' ).submit();
			}
			else {
				e.preventDefault();
				return false;
			}
		}
	} );
} );

function selfcareLogin(){
	if ( validateSelfcareLogin() ) {
		$( '.popup-loading' ).removeClass( 'hide' );
		$( '.loginOption' ).each( function( index, element ){
			if ( $( element ).attr( 'disabled' ) == 'disabled' || $( element ).attr( 'disabled' ) == true ) {
				$( element ).removeAttr( 'disabled' );
				$( element ).val( '' );
			}
		} );
		return true;
	}
	return false;
}

function changeBillMode( cust ){
	crmDwr.saveCustomerProfileDetails( cust.crmPlanDetailses[ 0 ].recordId, cust.customerId, qrcROL.billMode + "EB", "EB", function( result ){
		if ( result[ 0 ] == "success" ) {
			$( '#SwitchToEBill' ).addClass( 'hide' );
		}
		$( '.popup-loading' ).addClass( 'hide' );
		alert( result[ 1 ] );
	} );

}

function updateRMN( obj, field ){
	if ( oldVal.rmn != field.val() ) {
		if ( !isBlank( field.val() )
				&& ( isNaN( field.val() ) || field.val().length != 10 || ( field.val().charAt( 0 ) != 7 && field.val().charAt( 0 ) != 8 && field
						.val().charAt( 0 ) != 9 ) ) ) {
			alert( "Please enter valid 'RMN'." );
			field.focus();
		}
		else {
			// alert( 'call to dwr rmn' );
			$( '.popup-loading' ).removeClass( 'hide' );
			selfcareForm.getCustomerDetailsPojo( function( cust ){
				crmDwr.saveCustomerProfileDetails( cust.recordId, cust.customerId, qrcROL.rmn, field.val(), function( result ){
					if ( result[ 0 ] == "success" ) {
						oldVal.rmn = field.val();
						$( '#scRMN .prev-info' ).text( field.val() );
						HideUpdate( obj );
					}
					$( '.popup-loading' ).addClass( 'hide' );
					alert( result[ 1 ] );
				} );
			} );
		}
	}
	else {
		HideUpdate( obj );
	}
}

function updateRTN( obj, field ){
	if ( oldVal.rtn != field.val() ) {
		if ( !isBlank( field.val() ) && isNaN( field.val() ) ) {
			alert( "Please enter valid 'RTN'." );
			field.focus();
		}
		else {
			// alert( 'call to dwr rtn' );
			$( '.popup-loading' ).removeClass( 'hide' );
			selfcareForm.getCustomerDetailsPojo( function( cust ){
				crmDwr.saveCustomerProfileDetails( cust.recordId, cust.customerId, qrcROL.rtn, field.val(), function( result ){
					if ( result[ 0 ] == "success" ) {
						oldVal.rtn = field.val();
						$( '#scRTN .prev-info' ).text( field.val() );
						HideUpdate( obj );
					}
					$( '.popup-loading' ).addClass( 'hide' );
					alert( result[ 1 ] );
				} );
			} );
		}
	}
	else {
		HideUpdate( obj );
	}
}

function updateAMN( obj, field ){
	if ( oldVal.altMob != field.val() ) {
		if ( !isBlank( field.val() )
				&& ( isNaN( field.val() ) || field.val().length != 10 || ( field.val().charAt( 0 ) != 7 && field.val().charAt( 0 ) != 8 && field
						.val().charAt( 0 ) != 9 ) ) ) {
			alert( "Please enter valid 'Alternate Mobile No.'." );
			field.focus();
		}
		else {
			// alert( 'call to dwr amn' );
			$( '.popup-loading' ).removeClass( 'hide' );
			selfcareForm.getCustomerDetailsPojo( function( cust ){
				crmDwr.saveCustomerProfileDetails( cust.recordId, cust.customerId, qrcROL.altMob, field.val(), function( result ){
					if ( result[ 0 ] == "success" ) {
						oldVal.altMob = field.val();
						$( '#scAMN .prev-info' ).text( field.val() );
						HideUpdate( obj );
					}
					$( '.popup-loading' ).addClass( 'hide' );
					alert( result[ 1 ] );
				} );
			} );
		}
	}
	else {
		HideUpdate( obj );
	}
}

function updateRegEmail( obj, field ){
	if ( oldVal.email != field.val() ) {
		if ( !isBlank( field.val() ) && !validateEmail( field.val() ) ) {
			alert( "Please enter valid 'Email ID'." );
			field.focus();
		}
		else {
			// alert( 'call to dwr reg email' );
			$( '.popup-loading' ).removeClass( 'hide' );
			selfcareForm.getCustomerDetailsPojo( function( cust ){
				crmDwr.saveCustomerProfileDetails( cust.recordId, cust.customerId, qrcROL.email, field.val(), function( result ){
					if ( result[ 0 ] == "success" ) {
						oldVal.email = field.val();
						$( '#scRegEmail .prev-info' ).text( field.val() ).addClass( 'error' ).removeClass( 'valid' );
						$( '#scRegEmail .prev-info' ).attr( 'title', field.val() );
						$( '#scVerifyEmail' ).parent().removeClass( 'hide' );
						HideUpdate( obj );
					}
					$( '.popup-loading' ).addClass( 'hide' );
					alert( result[ 1 ] );
				} );
			} );
		}
	}
	else {
		HideUpdate( obj );
	}
}

function updateAltEmail( obj, field ){
	if ( oldVal.altEmail != field.val() ) {
		if ( !isBlank( field.val() ) && !validateEmail( field.val() ) ) {
			alert( "Please enter valid 'Alternate Email ID'." );
			field.focus();
		}
		else {
			// alert( 'call to dwr alt. email' );
			$( '.popup-loading' ).removeClass( 'hide' );
			selfcareForm.getCustomerDetailsPojo( function( cust ){
				crmDwr.saveCustomerProfileDetails( cust.recordId, cust.customerId, qrcROL.altEmail, field.val(), function( result ){
					if ( result[ 0 ] == "success" ) {
						oldVal.altEmail = field.val();
						$( '#scAltEmail .prev-info' ).text( field.val() );
						$( '#scAltEmail .prev-info' ).attr( 'title', field.val() );
						HideUpdate( obj );
					}
					$( '.popup-loading' ).addClass( 'hide' );
					alert( result[ 1 ] );
				} );
			} );
		}
	}
	else {
		HideUpdate( obj );
	}
}

function HideUpdate( obj ){
	$( obj ).parent().parent().parent().parent().find( '.col-info-editor' ).hide();
	var editor = $( obj ).parent().parent().parent().parent().find( '.col-info-edit' );
	editor.show();
}

function validateBillAddr(){
	var valid = true;
	var line1 = $( '#addLine1' ).val();
	var line2 = $( '#addLine2' ).val();
	var line3 = $( '#addLine3' ).val();
	var state = $( '#stateId' ).val();
	var city = $( '#cityId' ).val();

	$( '.msgsuccess' ).addClass( 'hide' );
	$( '.msgerror' ).addClass( 'hide' );

	if ( isBlank( line1 ) ) {
		$( '.pInfo' ).removeClass( 'hide' ).text( "Please enter 'Address Line 1'." );
		valid = false;
	}
	else if ( isBlank( line2 ) ) {
		$( '.pInfo' ).removeClass( 'hide' ).text( "Please enter 'Address Line 2'." );
		valid = false;
	}
	else if ( isBlank( line3 ) ) {
		$( '.pInfo' ).removeClass( 'hide' ).text( "Please select 'State' / 'City'." );
		valid = false;
	}
	else if ( isBlank( state ) || state == 0 ) {
		$( '.pInfo' ).removeClass( 'hide' ).text( "Please select 'State'." );
		valid = false;
	}
	else if ( isBlank( city ) || city == 0 ) {
		$( '.pInfo' ).removeClass( 'hide' ).text( "Please select 'City'." );
		valid = false;
	}

	if ( !valid ) {
		parent.jQuery.fancybox.update();
	}
	return valid;
}

function validateLogTicket(){
	var valid = true;
	var subject = $( '#scSubject' ).val();
	var category = $( '#scCategory' ).val();
	var remarks = $( '#scRemarks' ).val();

	$( '.msgsuccess' ).addClass( 'hide' );
	$( '.msgerror' ).addClass( 'hide' );

	if ( isBlank( subject ) ) {
		$( '.pInfo' ).removeClass( 'hide' ).text( "Please select 'Subject'." );
		valid = false;
	}
	else if ( isBlank( category ) || category == '0' ) {
		$( '.pInfo' ).removeClass( 'hide' ).text( "Please select 'Category'." );
		valid = false;
	}
	else if ( isBlank( remarks ) ) {
		$( '.pInfo' ).removeClass( 'hide' ).text( "Please enter 'Remarks'." );
		valid = false;
	}

	if ( !valid ) {
		parent.jQuery.fancybox.update();
	}
	return valid;
}

function validateSelfcareLogin(){
	var valid = true;
	var customerId = $( '#scCustId' ).val();
	var email = $( '#scCustEmail' ).val();
	var rmn = $( '#scCustRMN' ).val();
	var pw = $( '#scCustPw' ).val();

	$( '.msgsuccess' ).addClass( 'hide' );
	$( '.msgerror' ).addClass( 'hide' );
	$( '.pInfo' ).addClass( 'hide' );

	if ( isBlank( customerId ) && isBlank( email ) && isBlank( rmn ) ) {
		$( '.pInfo' ).removeClass( 'hide' ).text( "Please provide 'Customer ID' or 'Email ID' or 'RMN' to login." );
		valid = false;
		parent.jQuery.fancybox.update();
	}
	else if ( isBlank( pw ) ) {
		$( '.pInfo' ).removeClass( 'hide' ).text( "Please provide 'Password'." );
		valid = false;
		parent.jQuery.fancybox.update();
	}
	else if ( !isBlank( customerId ) && isNaN( customerId ) ) {
		$( '.pInfo' ).removeClass( 'hide' ).text( "Please provide valid 'Customer ID'." );
		valid = false;
		parent.jQuery.fancybox.update();
	}
	else if ( !isBlank( email ) && !validateEmail( email ) ) {
		$( '.pInfo' ).removeClass( 'hide' ).text( "Please provide valid 'Email ID'." );
		valid = false;
		parent.jQuery.fancybox.update();
	}
	else if ( !isBlank( rmn ) && ( isNaN( rmn ) || rmn.length != 10 || ( rmn.charAt( 0 ) != 7 && rmn.charAt( 0 ) != 8 && rmn.charAt( 0 ) != 9 ) ) ) {
		$( '.pInfo' ).removeClass( 'hide' ).text( "Please provide valid 'RMN'." );
		valid = false;
		parent.jQuery.fancybox.update();
	}

	return valid;
}

function proceedLoginWith( custId ){
	if ( !isBlank( custId ) ) {
		$( '#hdnCustId' ).val( custId );
		$( '#proceedLoginForm' ).attr( 'action', 'login.do?method=authenticate' );
		$( '#proceedLoginForm' ).submit();
	}
	else {
		console.log( "unable to process custId:" + custId );
	}
}

function disableLoginFields( ignoreElement ){
	if ( isBlank( $( ignoreElement ).val() ) ) {
		$( '.loginOption' ).attr( 'disabled', false );
		$( '.loginOption' ).val( '' );
		return;
	}
	var value = $( ignoreElement ).val();
	$( '.loginOption' ).attr( 'disabled', true );
	$( '.loginOption' ).val( '' );
	$( ignoreElement ).attr( 'disabled', false );
	$( ignoreElement ).val( value );

	$( '#scCustPw' ).focus();
}

function isBlank( value ){
	if ( $.trim( value ) == '' || value == null || value == undefined ) {
		return true;
	}
	return false;
}

function validateEmail( emailID ){
	var re = /[a-zA-Z0-9._-]+@[a-zA-Z0-9-]+\.[a-zA-Z.]{2,5}/;
	return re.test( emailID );
}

function removeChars( txt ){
	txt.value = txt.value.replace( /[^0-9\n\r]+/g, '' );
}

function validateForgotPaswrd(){
	var valid = true;
	var custId = $( '#chpswrdCustId' ).val();
	var rmn = $( '#chpswrdCustRMN' ).val();

	if ( isBlank( custId ) && isBlank( rmn ) ) {
		$( '.pInfo' ).removeClass( 'hide' ).text( "Please provide 'Customer ID' and 'RMN'." );
		valid = false;
	}
	else if ( isBlank( custId ) ) {
		$( '.pInfo' ).removeClass( 'hide' ).text( "Please provide 'Customer ID'." );
		valid = false;
	}
	else if ( !isBlank( custId ) && isNaN( custId ) ) {
		$( '.pInfo' ).removeClass( 'hide' ).text( "Please provide a valid 'Customer ID'." );
		valid = false;
	}
	else if ( isBlank( rmn ) ) {
		$( '.pInfo' ).removeClass( 'hide' ).text( "Please provide 'RMN'." );
		valid = false;
	}
	else if ( !isBlank( rmn ) && ( isNaN( rmn ) || rmn.length != 10 || ( rmn.charAt( 0 ) != 7 && rmn.charAt( 0 ) != 8 && rmn.charAt( 0 ) != 9 ) ) ) {
		$( '.pInfo' ).removeClass( 'hide' ).text( "Please provide valid 'RMN'." );
		valid = false;
	}

	if ( !valid ) {
		parent.jQuery.fancybox.update();
	}

	return valid;
}

function validateChangePassword(){
	var valid = true;
	var oldPswrd = $( '#chngePswrdCustOldPw' ).val();
	var newPswrd = $( '#chngePswrdCustNewPw' ).val();
	var newRPswrd = $( '#chngePswrdCustRNewPw' ).val();
	var captcha = $( '#answer1' ).val();
	if ( isBlank( oldPswrd ) && isBlank( newPswrd ) && isBlank( newRPswrd ) && isBlank( captcha ) ) {
		$( '.pInfo' ).removeClass( 'hide' ).text( "Please provide 'Old Password','New Password','Retype Password' and 'Captcha'." );
		valid = false;
	}
	else if ( isBlank( oldPswrd ) ) {
		$( '.pInfo' ).removeClass( 'hide' ).text( "Please provide 'Old Password'." );
		valid = false;
	}
	else if ( isBlank( newPswrd ) ) {
		$( '.pInfo' ).removeClass( 'hide' ).text( "Please provide 'New Password'." );
		valid = false;
	}
	else if ( !isBlank( newPswrd ) && !isValidPswrd( newPswrd ) ) {
		$( '.pInfo' ).removeClass( 'hide' ).text( "Please provide a valid 'New Password'." );
		valid = false;
	}
	else if ( isBlank( newRPswrd ) ) {
		$( '.pInfo' ).removeClass( 'hide' ).text( "Please provide 'Retype Password'." );
		valid = false;
	}
	else if ( !isBlank( newRPswrd ) && !isValidPswrd( newRPswrd ) ) {
		$( '.pInfo' ).removeClass( 'hide' ).text( "Please provide a valid 'Retype Password'." );
		valid = false;
	}
	else if ( newPswrd != newRPswrd ) {
		$( '.pInfo' ).removeClass( 'hide' ).text( "'New Password' and 'Retype Password' are not same." );
		valid = false;
	}
	else if ( isBlank( captcha ) ) {
		$( '.pInfo' ).removeClass( 'hide' ).text( "Please enter 'Captcha'." );
		valid = false;
	}

	if ( !valid ) {
		parent.jQuery.fancybox.update();
	}

	return valid;
}

function isValidPswrd( password ){
	var passRegex = /^.*(?=.{8,15})(?=.*\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%&]).*$/;
	return passRegex.test( password );
}

function reloadCaptcha( id ){
	var obj = document.getElementById( id );
	var src = obj.src;
	var pos = src.indexOf( '?' );
	if ( pos >= 0 ) {
		src = src.substr( 0, pos );
	}
	var date = new Date();
	obj.src = src + '?v=' + date.getTime();
	return false;
}

function getSelfcareCategories( subject, target ){
	if ( !isBlank( subject ) ) {
		crmDwr.getSelfcareCategories( subject, function( list ){
			dwr.util.removeAllOptions( target );
			dwr.util.addOptions( target, [
				{
					value : '0', text : 'Please Select'
				}
			], 'value', 'text' );
			dwr.util.addOptions( target, list, "qrcSubSubCategoryId", "selfcareCategory" );
		} );
	}
	else {
		dwr.util.removeAllOptions( target );
		dwr.util.addOptions( target, [
			{
				value : '0', text : 'Please Select'
			}
		], 'value', 'text' );
	}
}

function populateCitybyState( stateId, target ){
	if ( !isBlank( stateId ) && stateId != 0 ) {
		dwr.util.removeAllOptions( target );
		dwr.util.addOptions( target, [
			{
				value : '0', text : 'Please Select'
			}
		], 'value', 'text' );
		crmDwr.getCityList( stateId, function( list ){
			if ( list != null ) {
				dwr.util.addOptions( target, list, "cityId", "cityName" );
			}
		} );
	}
	else {
		dwr.util.removeAllOptions( target );
		dwr.util.addOptions( target, [
			{
				value : '0', text : 'Please Select'
			}
		], 'value', 'text' );
	}
	updateAddressLine3();
}

function updateAddressLine3(){
	var city = $( '#cityId' );
	var state = $( '#stateId' );
	var target = $( '#addLine3' );

	if ( city.val() == 0 || state.val() == 0 ) {
		target.val( '' );
	}
	else {
		target.val( $( '#cityId option:selected' ).text() + ", " + $( '#stateId option:selected' ).text() );
	}
}

function validateSelfcareQuickPay(){
	var valid = true;
	var customerId = $( '#scCustId' ).val();

	var rmn = $( '#scCustRMN' ).val();

	$( '.msgsuccess' ).addClass( 'hide' );
	$( '.msgerror' ).addClass( 'hide' );

	if ( isBlank( customerId ) && isBlank( rmn ) ) {
		$( '.pInfo' ).removeClass( 'hide' ).text( "Please provide 'Customer ID'  or 'RMN'." );
		valid = false;
	}

	else if ( !isBlank( customerId ) && isNaN( customerId ) ) {
		$( '.pInfo' ).removeClass( 'hide' ).text( "Please provide valid 'Customer ID'." );
		valid = false;
	}

	else if ( !isBlank( rmn ) && ( isNaN( rmn ) || rmn.length != 10 || ( rmn.charAt( 0 ) != 7 && rmn.charAt( 0 ) != 8 && rmn.charAt( 0 ) != 9 ) ) ) {
		$( '.pInfo' ).removeClass( 'hide' ).text( "Please provide valid 'RMN'." );
		valid = false;
	}

	return valid;
}

function addCustomerId( scCustId ){
	if ( isBlank( $( '#qcpCustId' ).val() ) ) {
		$( '.pInfo' ).removeClass( 'hide' ).text( "Please select Customer ID." );
	}
	else {
		$( '.pInfo' ).addClass( 'hide' );
		$( '#scQuickPayListForm' ).attr( 'action', 'quickPay.do?method=quickPayAction' );
		$( '#scQuickPayListForm' ).submit();
	}
}
function validateSelfcareQuickPayTransaction(){
	var valid = true;
	var amount = $( '#scAmount' ).val();
	if ( isBlank( amount ) ) {
		$( '.pInfo' ).removeClass( 'hide' ).text( "Please provide amount to be paid." );
		valid = false;
	}

	else if ( !isBlank( amount ) && !checkPaidAmount( amount ) ) {
		$( '.pInfo' ).removeClass( 'hide' ).text( "Please provide valid amount." );
		valid = false;
	}

	else if ( parseInt( amount ) <= 0 ) {
		$( '.pInfo' ).removeClass( 'hide' ).text( "Please provide valid amount." );
		valid = false;
	}
	else
		valid = true;

	return valid;
}

function validateSelfcareQuickPayConfirm(){
	var valid = true;
	if ( $( '#paymentModeCC' ).is( ':checked' ) || $( '#paymentModeDC' ).is( ':checked' ) || $( '#paymentModeIB' ).is( ':checked' ) ) {
		valid = true;
	}
	else {
		$( '.pInfo' ).removeClass( 'hide' ).text( "Please select the payment option." );
		valid = false;
	}

	return valid;
}

function disableQuickPayFields( ignoreElement ){
	if ( isBlank( $( ignoreElement ).val() ) ) {
		$( '.inputText' ).attr( 'disabled', false );
		$( '.inputText:disabled' ).val( '' );
		return;
	}
	$( '.inputText' ).attr( 'disabled', true );
	$( ignoreElement ).attr( 'disabled', false );
}

function topUpUsageBooster(){
	var topUpUsage = document.getElementById( 'drpselectdul' ).value;
	if ( topUpUsage != '' ) {
		var answer = confirm( "Please confirm you are going to purchase usage top-up?" );
		if ( answer ) {
			$( '#boosterTopUpForm' ).attr( 'action', 'migration.do?method=topUpUsageBooster' );
			$( '#boosterTopUpForm' ).submit();
		}
	}
	else {
		$( '#drpselectdul' ).next( 'font' ).show().html( "Please select 'Top-Up Usage'" ).addClass( 'errorTextbox' );
	}
}

function validatePin( value ){
	value = Number( value );
	var regex = /^[0-9]{6}$/g;
	var res = regex.test( value );
	return res;
}

function checkPaidAmount( value ){
	var value_regex = /^(([0-9]{1,6}))((\.[0-9]{1,2})?)$/i;
	var res = value_regex.test( value );
	return res;
}
