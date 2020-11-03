$( document ).ready( function(){

	if ( $( "#chequeId:checked" ).length != 0 ) {
		$( "#refundByCheque" ).css( {
			display : "block"
		} );
		$( "#refProcessDateBlock" ).css( {
			display : "none"
		} );
		$( '#refProcess option[value="RP"]' ).remove();
	}
	if ( $( "#neftId:checked" ).length != 0 ) {
		$( "#refundByNEFT" ).css( {
			display : "block"
		} );
		$( "#refProcessDateBlock" ).css( {
			display : "block"
		} );

	}
	$( '#chequeId' ).click( function(){
		$( "#refundByNEFT" ).css( {
			display : "none"
		} );
		$( "#refundByCheque" ).css( {
			display : "block"
		} );
		$( "#refProcessDateBlock" ).css( {
			display : "none"
		} );
		$( '#refProcess option[value="IP"]' ).remove();
		$( '#refProcess option[value="PS"]' ).remove();
		$( "#refProcess" ).append( new Option( "In Process", "IP" ) );

	} );
	$( '#neftId' ).click( function(){
		$( "#refundByNEFT" ).css( {
			display : "block"
		} );
		$( "#refundByCheque" ).css( {
			display : "none"
		} );
		$( "#refProcessDateBlock" ).css( {
			display : "block"
		} );
		$( '#refProcess option[value="IP"]' ).remove();
		$( '#refProcess option[value="PS"]' ).remove();
		$( "#refProcess" ).append( new Option( "Processed", "PS" ) );
		$( "#refProcess" ).append( new Option( "In Process", "IP" ) );

	} );
	$( '#customerId' ).keyup( function(){
		$( '#customerIdError' ).addClass( 'hidden' );
		if ( $( "#customerId" ).val().trim() != "" && isNaN( $( "#customerId" ).val() ) ) {
			$( '#customerIdError' ).removeClass( 'hidden' );
			$( '#customerIdError' ).show().html( "Please Provide only numeric 'Customer ID'." ).addClass( 'errorTextboxInDiv' );
			valid = false;
		}
	} );
	$( '#crfId' ).keyup( function(){
		$( '#crfIdError' ).addClass( 'hidden' );
		if ( $( "#crfId" ).val().trim() != "" && !validateCRF( $( "#crfId" ).val() ) ) {
			$( '#crfIdError' ).removeClass( 'hidden' );
			$( '#crfIdError' ).show().html( "CAF number should be <br> numeric and 8 character long." ).addClass( 'errorTextboxInDiv' );
			valid = false;
		}
	} );

	$( '#REFUND_SUBMIT' ).click( function(){
		if ( commonRefundError() ) {
			if ( confirm( "Are you sure you want to process the refund ?" ) ) {
				$( '#refundPaymentForm' ).attr( 'action', 'refund.do?method=updateRefund' );
				$( '#refundPaymentForm' ).submit();
			}
		}
	} );
} );

function commonRefundError(){
	var msg = "Please Provide ";
	var valid = true;
	$( '#customerIdError' ).addClass( 'hidden' );
	$( '#crfIdError' ).addClass( 'hidden' );
	$( '#paymentModeError' ).addClass( 'hidden' );
	$( '#refundAmountError' ).addClass( 'hidden' );
	$( '#refundProcessError' ).addClass( 'hidden' );
	$( '#refundProcessDateError' ).addClass( 'hidden' );
	$( '#entityTypeError' ).addClass( 'hidden' );
	$( '#chequeNoError' ).addClass( 'hidden' );
	$( '#chequeDateError' ).addClass( 'hidden' );
	$( '#chequeStatusError' ).addClass( 'hidden' );
	$( '#payerBankNameError' ).addClass( 'hidden' );
	$( '#neftTransactionIdError' ).addClass( 'hidden' );
	$( '#beneficiaryAccNoError' ).addClass( 'hidden' );
	$( '#beneficiaryBankNameError' ).addClass( 'hidden' );
	$( '#ifscCodeError' ).addClass( 'hidden' );
	$( '#ticketIdError' ).addClass( 'hidden' );
	
	if ( $( "#chequeId:checked" ).length == 0 && $( "#neftId:checked" ).length == 0 ) {
		$( '#paymentModeError' ).removeClass( 'hidden' );
		$( '#paymentModeError' ).show().html( msg + " payment mode ." ).addClass( 'errorTextboxInDiv' );
		valid = false;
	}
	if ( $( "#customerId" ).val().trim() == "" && $( "#crfId" ).val().trim() == "" ) {
		$( '#customerIdError' ).removeClass( 'hidden' );
		$( '#customerIdError' ).show().html( msg + " 'Customer ID' Or 'CRF Number'." ).addClass( 'errorTextboxInDiv' );
		valid = false;
	}

	if ( $( "#crfId" ).val().trim() != "" && !validateCRF( $( "#crfId" ).val() ) ) {
		$( '#crfIdError' ).removeClass( 'hidden' );
		$( '#crfIdError' ).show().html( "CAF number should be in<br> numeric and 8 character long." ).addClass( 'errorTextboxInDiv' );
		valid = false;
	}
	if ( ( $( "#customerId" ).val().trim() != "" || $( "#crfId" ).val().trim() != "" )
			&& ( $( "#customerStatus" ).val().trim() == "" || $( "#serviceType" ).val().trim() == "" ) ) {
		$( '#customerIdError' ).removeClass( 'hidden' );
		$( '#customerIdError' ).show().html( "Either 'Customer ID' Or 'CAF Number' is invalid." ).addClass( 'errorTextboxInDiv' );
		valid = false;
	}
	if ( $( "#customerId" ).val().trim() != "" && isNaN( $( "#customerId" ).val() ) ) {
		$( '#customerIdError' ).removeClass( 'hidden' );
		$( '#customerIdError' ).show().html( msg + " only numeric 'Customer ID'." ).addClass( 'errorTextboxInDiv' );
		valid = false;
	}
	if ( $( "#entityType" ).val() == "" || $( "#entityType" ).val() == null || $( "#entityType" ).val() == undefined ) {
		$( '#entityTypeError' ).removeClass( 'hidden' );
		$( '#entityTypeError' ).show().html( msg + " 'Entity Type'." ).addClass( 'errorTextboxInDiv' );
		valid = false;
	}

	if ( $( "#chequeId:checked" ).length != 0 ) {
		if ( $( "#chequeNo" ).val().trim() == "" || $( "#chequeNo" ).val() == null || $( "#chequeNo" ).val() == undefined ) {
			$( '#chequeNoError' ).removeClass( 'hidden' );
			$( '#chequeNoError' ).show().html( msg + " 'Cheque Number'." ).addClass( 'errorTextboxInDiv' );
			valid = false;
		}
		if ( $( "#chequeNo" ).val() != "" && ( isNaN( $( "#chequeNo" ).val() ) || ( $( "#chequeNo" ).val().length != 6 ) ) ) {

			$( '#chequeNoError' ).removeClass( 'hidden' );
			$( '#chequeNoError' ).show().html( "'Cheque No.' should be  'digits ' and 6 character long ." ).addClass( 'errorTextboxInDiv' );
			valid = false;
		}
		if ( $( "#chequeDate" ).val().trim() == "" || $( "#chequeDate" ).val() == null || $( "#chequeDate" ).val() == undefined ) {
			$( '#chequeDateError' ).removeClass( 'hidden' );
			$( '#chequeDateError' ).show().html( msg + " 'Cheque Date'." ).addClass( 'errorTextboxInDiv' );
			valid = false;
		}
		if ( $( "#chequeDate" ).val() != "" && !validityChequeDate( $( "#chequeDate" ).val(), 90 ) ) {
			$( '#chequeDateError' ).removeClass( 'hidden' );
			$( '#chequeDateError' ).show().html( "Please provide 'Cheque Date'<br> within last 90 days only." ).addClass( 'errorTextbox' );
			valid = false;
		}
		if ( $( "#payerBankName" ).val().trim() == "" || $( "#payerBankName" ).val() == null || $( "#payerBankName" ).val() == undefined ) {
			$( '#payerBankNameError' ).removeClass( 'hidden' );
			$( '#payerBankNameError' ).show().html( msg + " 'Payer Bank Name'." ).addClass( 'errorTextboxInDiv' );
			valid = false;
		}

	}
	if ( $( "#neftId:checked" ).length != 0 ) {
		if ( $( "#neftTransactionId" ).val().trim() == "" || $( "#neftTransactionId" ).val() == null || $( "#neftTransactionId" ).val() == undefined ) {
			$( '#neftTransactionIdError' ).removeClass( 'hidden' );
			$( '#neftTransactionIdError' ).show().html( msg + "'Transaction ID'." ).addClass( 'errorTextboxInDiv' );
			valid = false;
		}
		if ( $( "#beneficiaryAccNo" ).val().length < 6 ) {
			$( '#beneficiaryAccNoError' ).removeClass( 'hidden' );
			$( '#beneficiaryAccNoError' ).show().html( msg+" valid 'Benef. A/C No.'" ).addClass( 'errorTextboxInDiv' );
			valid = false;
		}
		if ( $( "#beneficiaryAccNo" ).val().trim() == "" || $( "#beneficiaryAccNo" ).val() == null || $( "#beneficiaryAccNo" ).val() == undefined ) {
			$( '#beneficiaryAccNoError' ).removeClass( 'hidden' );
			$( '#beneficiaryAccNoError' ).show().html( msg + " 'Benef. A/C No.'" ).addClass( 'errorTextboxInDiv' );
			valid = false;
		}
		if ( $( "#beneficiaryAccNo" ).val() != "" && isNaN( $( "#beneficiaryAccNo" ).val() ) ) {
			$( '#beneficiaryAccNoError' ).removeClass( 'hidden' );
			$( '#beneficiaryAccNoError' ).show().html( " 'Benef. A/C No.' should be digits." ).addClass( 'errorTextboxInDiv' );
			valid = false;
		}
		if ( $( "#beneficiaryBankName" ).val().trim() == "" || $( "#beneficiaryBankName" ).val() == null
				|| $( "#beneficiaryBankName" ).val() == undefined ) {
			$( '#beneficiaryBankNameError' ).removeClass( 'hidden' );
			$( '#beneficiaryBankNameError' ).show().html( msg + "'Benef. Bank Name'." ).addClass( 'errorTextboxInDiv' );
			valid = false;
		}
		if ( !validityIfscCode( $( "#ifscCode" ).val() ) ) {
			$( '#ifscCodeError' ).removeClass( 'hidden' );
			$( '#ifscCodeError' ).show().html( msg+"valid 'IFSC Code'" ).addClass( 'errorTextboxInDiv' );
			valid = false;
		}
		if ( $( "#ifscCode" ).val().trim() == "" || $( "#ifscCode" ).val() == null || $( "#ifscCode" ).val() == undefined ) {
			$( '#ifscCodeError' ).removeClass( 'hidden' );
			$( '#ifscCodeError' ).show().html( msg + "'IFSC Code'." ).addClass( 'errorTextboxInDiv' );
			valid = false;
		}		
		if ( $( "#refProcessDate" ).val().trim() == "" || $( "#refProcessDate" ).val() == null || $( "#refProcessDate" ).val() == undefined ) {
			$( '#refundProcessDateError' ).removeClass( 'hidden' );
			$( '#refundProcessDateError' ).show().html( msg + " 'Process Date'." ).addClass( 'errorTextboxInDiv' );
			valid = false;
		}

	}
	if ( $( "#refAmount" ).val().trim() == "" || $( "#refAmount" ).val().trim() == null || $( "#refAmount" ).val().trim() == undefined
			|| $( "#refAmount" ).val().trim() <= 0 ) {
		$( '#refundAmountError' ).removeClass( 'hidden' );
		$( '#refundAmountError' ).show().html( msg + " 'Amount' ." ).addClass( 'errorTextboxInDiv' );
		valid = false;
	}
	if ( $( "#refAmount" ).val().trim() != "" && !checkPaidAmount( $( "#refAmount" ).val() ) ) {
		$( '#refundAmountError' ).removeClass( 'hidden' );
		$( '#refundAmountError' ).show().html( msg + " valid 'Amount' ." ).addClass( 'errorTextboxInDiv' );
		valid = false;
	}
	if ( $( "#refProcess" ).val().trim() == "" || $( "#refProcess" ).val() == null || $( "#refProcess" ).val() == undefined ) {
		$( '#refundProcessError' ).removeClass( 'hidden' );
		$( '#refundProcessError' ).show().html( msg + " 'Process Status'." ).addClass( 'errorTextboxInDiv' );
		valid = false;
	}
	if ( $( "#ticketId" ).val().trim() == "" || $( "#ticketId" ).val() == null || $( "#ticketId" ).val() == undefined ) {
		$( '#ticketIdError' ).show().html( msg + " 'Ticket ID'." ).removeClass( 'hidden' ).addClass( 'errorTextboxInDiv' );
		valid = false;
	}
	/*if ( $( "#ticketId" ).val().trim() != "" ) {
		$( '#ticketIdError' ).show().html( msg + " Valid 'Ticket ID'." ).removeClass( 'hidden' ).addClass( 'errorTextboxInDiv' );
		valid = false;
	}*/
	if ( $( "#remarks" ).val().trim() == "" || $( "#remarks" ).val() == null || $( "#remarks" ).val() == undefined
			|| $( "#remarks" ).val().trim().length < 2 || $( "#remarks" ).val().trim().length > 4000 ) {
		$( '#remarksError' ).removeClass( 'hidden' );
		$( '#remarksError' ).show().html( msg + "'remarks' between [2-4000]." ).addClass( 'errorTextboxInDiv' );
		valid = false;
	}

	return valid;
}

function getCustomerDetails( customerId, idType, addToList ){
	if ( customerId != "" ) {
		if ( idType == 'CUST_ID' && isNaN( customerId ) ) {
			return false;

		}
		if ( idType == 'CRF_ID' && !validateCRF( customerId ) ) {
			return false;

		}

		crmDwr.getCustomerDetails( customerId, idType, function( list ){
			if ( addToList ) {
				addCustomerDetails( list );
			}
			else {
				if ( list == null ) {
					document.getElementById( 'crfID' ).value = '';
					alert( "Please provide valid CAF Number." );
				}
			}
		} );
		function addCustomerDetails( list ){
			if ( list != null ) {
				dwr.util.setValue( "crfId", list.crfId );
				dwr.util.setValue( "customerId", list.customerId );
				dwr.util.setValue( "serviceType", list.serviceType );
				dwr.util.setValue( "customerStatus", list.status );
				// $( '#crfId' ).attr( 'readonly', true );
				// $( '#customerId' ).attr( 'readonly', true );
			}
			else {
				alert( "No Customer details in the system for this Customer ID, Please provide correct Customer ID or CAF Number." );
				dwr.util.setValue( "serviceType", "" );
				dwr.util.setValue( "customerStatus", "" );
			}
		}

	}
}