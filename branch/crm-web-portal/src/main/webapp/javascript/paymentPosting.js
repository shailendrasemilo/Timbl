$( document )
		.ready(
				function( e ){
					$( '#paymentDate' ).removeClass( 'gray_text' );
					$( '#IDBouncingDate' ).removeClass( 'gray_text' );
					$( '#pp_chequeDateId' ).removeClass( 'gray_text' );
					var currentDate = new Date(), day = currentDate.getDate(), month = currentDate.getMonth() + 1, year = currentDate.getFullYear(), day1 = day
							+ "/" + month + "/" + year;

					$( '[name="crmPaymentDetailsPojo.chequeDate"]' ).val( day1 );
					$( '[name="crmPaymentDetailsPojo.paymentDate"]' ).val( day1 );

					$.validator.addMethod( "DaysCheck", function( value, element, param ){
						return this.optional( element ) || validityChequeDate( value, parseInt( param ) );

					} );
					$.validator.addMethod( "alphaNumeric", function( value, element ){
						return this.optional( element ) || validateAlphanumericSpace( value );

					} );
					$.validator.addMethod( "alphabetOrNumber", function( value, element ){
						return this.optional( element ) || validateAlphabetOrNumber( value );

					} );

					$.validator.addMethod( 'crfId', function( value, element ){
						return this.optional( element ) || validateCRF( value );
					} );

					$.validator.addMethod( 'paidAmount', function( value, element ){
						return this.optional( element ) || checkPaidAmount( value );
					} );

					$.validator.addMethod( 'notZero', function( value, element ){
						return this.optional( element ) || checkForZero( value );
					} );

					$( 'input[name="crmPaymentDetailsPojo.paymentMode"]' ).bind(
							'change',
							function(){
								if ( $( '#ModePaymentPostC' ).is( ':checked' ) ) {
									   getPaymentChannel( 'C', null );
									   //getPaymentCaseStatus('C', null);
									$( '#ModePaymentPostCashShow' ).removeClass( 'hide1' );
									$( '#ModePaymentPostCashShow select, #ModePaymentPostCashShow input' ).removeAttr( 'disabled', 'disabled' );
									$( '#ModePaymentPostDDShow, #ModePaymentPostOnlineShow' ).addClass( 'hide1' );
									$( '#ModePaymentPostOnlineShow input, #ModePaymentPostDDShow input, #ModePaymentPostDDShow select' ).attr(
											'disabled', 'disabled' );
								}
								else if ( $( '#ModePaymentPostQ' ).is( ':checked' ) ) {
									 getPaymentChannel( 'Q', null );
									 //getPaymentCaseStatus('Q', null);
									$( '#ModePaymentPostDDShow' ).removeClass( 'hide1' );
									$( '#headingCheque' ).html( 'Cheque/DD Payment' );
									$( '#numberCheque' ).html( 'Cheque/DD Number<span class="error_message verticalalignTop">*</span>' );
									$( '#dateCheque' ).html( 'Cheque/DD Date<span class="error_message verticalalignTop">*</span>' );
									$( '#ModePaymentPostDDShow select, #ModePaymentPostDDShow input' ).removeAttr( 'disabled', 'disabled' );
									$( '#ModePaymentPostCashShow, #ModePaymentPostOnlineShow' ).addClass( 'hide1' );
									$( '#ModePaymentPostOnlineShow input, #ModePaymentPostCashShow input' ).attr( 'disabled', 'disabled' );
								}
								else if ( $( '#ModePaymentPostO' ).is( ':checked' ) ) {
									getPaymentChannel( 'O', null );
									 //getPaymentCaseStatus('O', null);
									$( '#ModePaymentPostOnlineShow' ).removeClass( 'hide1' );
									$( '#ModePaymentPostOnlineShow select, #ModePaymentPostOnlineShow input' ).removeAttr( 'disabled', 'disabled' );
									$( '#ModePaymentPostDDShow, #ModePaymentPostCashShow' ).addClass( 'hide1' );
									$( '#ModePaymentPostCashShow input, #ModePaymentPostDDShow input, #ModePaymentPostDDShow select' ).attr(
											'disabled', 'disabled' );
								}
							} );

					if ( $( '#ModePaymentPostC' ).is( ':checked' ) ) {
						//getPaymentChannel( 'C', null );
						$( '#ModePaymentPostCashShow' ).removeClass( 'hide1' );
						$( '#ModePaymentPostCashShow input' ).removeAttr( 'disabled', 'disabled' );
						$( '#ModePaymentPostDDShow select, #ModePaymentPostDDShow input, #ModePaymentPostOnlineShow input' ).attr( 'disabled',
								'disabled' );
					}

					if ( $( '#ModePaymentPostQ' ).is( ':checked' ) ) {
						//getPaymentChannel( 'Q', null );
						$( '#ModePaymentPostDDShow' ).removeClass( 'hide1' );
						$( '#headingCheque' ).html( 'Cheque/DD Payment' );
						$( '#numberCheque' ).html( 'Cheque/DD Number' );
						$( '#dateCheque' ).html( 'Cheque/DD Date' );
						$( '#ModePaymentPostDDShow select, #ModePaymentPostDDShow input' ).removeAttr( 'disabled', 'disabled' );
						$( '#ModePaymentPostCashShow input, #ModePaymentPostOnlineShow input' ).attr( 'disabled', 'disabled' );
					}
					/*
					 * if($('#ModePaymentPostdd').is(':checked')){ $('#ModePaymentPostDDShow').removeClass('hide1'); $('#headingCheque').html('DD Payment'); $('#numberCheque').html('DD Number'); $('#dateCheque').html('DD Date'); $('#ModePaymentPostDDShow select, #ModePaymentPostDDShow
					 * input').removeAttr('disabled', 'disabled'); $('#ModePaymentPostCashShow input, #ModePaymentPostOnlineShow input').attr('disabled', 'disabled'); }
					 */

					if ( $( '#ModePaymentPostO' ).is( ':checked' ) ) {
						//getPaymentChannel( 'O', null );
						$( '#ModePaymentPostOnlineShow' ).removeClass( 'hide1' );
						$( '#ModePaymentPostOnlineShow input' ).removeAttr( 'disabled', 'disabled' );
						$( '#ModePaymentPostCashShow input, #ModePaymentPostDDShow select, #ModePaymentPostDDShow input' ).attr( 'disabled',
								'disabled' );
					}

					/* -------- Payment Posting ----------- */
					$( "#paymentPosting" )
							.validate(
									{
										rules : {
											// User Details
											customerId : {
												digits : true
											}, crfId : {
												crfId : true
											}, 'crmPaymentDetailsPojo.entityType' : {
												dropDown : true
											},
											// Payment Details
											'crmPaymentDetailsPojo.paymentMode' : {
												required : true
											}, 'crmPaymentDetailsPojo.paymentChannel' : {
												dropDown : true
											}, 'crmPaymentDetailsPojo.paymentDate' : {
												required : true
											}, 'crmPaymentDetailsPojo.amount' : {
												required : true, number : true, paidAmount : true, notZero : true
											},
											// Mode type details
											'crmPaymentDetailsPojo.chequeDdNo' : {
												required : true, digits : true, minlength : 6, maxlength : 6
											}, 'chequeDate' : {
												required : true, DaysCheck : '90'
											}, 'crmPaymentDetailsPojo.bankName' : {
												dropDown : true
											}, 'crmPaymentDetailsPojo.realzationStatus' : {
												dropDown : true
											}, 'crmPaymentDetailsPojo.transactionId' : {
												required : true, alphabetOrNumber : true, maxlength : 15
											}, 'crmPaymentDetailsPojo.receiptNo' : {
												required : true, alphabetOrNumber : true//, minlength : 8, maxlength : 8
											}
										},

										messages : {
											customerId : {
												digits : "<font class='errorCreateUser'> Please provide only number</font>"
											},
											crfId : {
												crfId : "<font class='errorCreateUser'>  Please provide in valid format </font>"
											},
											'crmPaymentDetailsPojo.entityType' : {
												dropDown : "<font class='errorCreateUser'> Please select 'Entity Type'</font>"
											},
											// Payment Details
											'crmPaymentDetailsPojo.paymentMode' : {
												required : "<font class='errorCreateUser'> Please provide 'Payment Mode'</font>"
											},
											'crmPaymentDetailsPojo.paymentChannel' : {
												dropDown : "<font class='errorCreateUser'> Please select 'Payment Channel'</font>",

												
											},
											// cash details
											'crmPaymentDetailsPojo.paymentDate' : {
												required : "<font class='errorCreateUser'> Please provide 'Payment Date'</font>"
											},
											'crmPaymentDetailsPojo.amount' : {
												required : "<font class='errorCreateUser'> Please provide 'Amount'</font>",
												number : "<font class='errorCreateUser'> Please provide only number, decimal allowed</font>",
												paidAmount : "<font class='errorCreateUser'> Please provide amount not more than 8 digits(and 1-2 digits after decimal)</font>",
												notZero : "<font class='errorCreateUser'> Please provide amount more than zero.</font>"
											},
											'crmPaymentDetailsPojo.chequeDdNo' : {
												required : "<font class='errorCreateUser'> Please provide valid 'Cheque Number.'</font>",
												digits : "<font class='errorCreateUser'> Please provide only number</font>",
												minlength : "<font class='errorCreateUser'>Please provide minimum 6 digits.</font>",
												maxlength : "<font class='errorCreateUser'>Please provide maximum 6 digits.</font>"
											},
											'chequeDate' : {
												required : "<font class='errorCreateUser'>Please provide 'Cheque Date'</font>",
												DaysCheck : "<font class='errorCreateUser'>Please provide 'Cheque/DD Date'<br /> within last 90 days only</font>"
											},
											'crmPaymentDetailsPojo.bankName' : {
												dropDown : "<font class='errorCreateUser'> Please select 'Bank Name'</font>"
											},
											'crmPaymentDetailsPojo.realzationStatus' : {
												dropDown : "<font class='errorCreateUser'> Please select 'Realization Status'</font>"
											},
											// online details
											'crmPaymentDetailsPojo.receiptNo' : {
												required : "<font class='errorCreateUser'> Please provide 'Receipt Number.'</font>",
												alphabetOrNumber : "<font class='errorCreateUser'>Please provide number or alphabets only.</font>",
												//minlength : "<font class='errorCreateUser'>Receipt number should be 8 characters long</font>",
												//maxlength : "<font class='errorCreateUser'>Receipt number should be 8 characters long</font>"
											},
											'crmPaymentDetailsPojo.transactionId' : {
												required : "<font class='errorCreateUser'> Please provide 'Transaction ID'</font>",
												alphabetOrNumber : "<font class='errorCreateUser'>Please provide number or alphabets only.</font>",
												maxlength : "<font class='errorCreateUser'> Please provide not more than 15 character</font>"
											}
										}
									} );

					$( '#submit_paymentPosting' ).click( function(){
						if ( anyOne() && $( "#paymentPosting" ).valid() && paymentError() ) {
							var answer = confirm( "Are you sure you want to post payment?" );
							if ( answer ) {
								$( '.loadingPopup' ).removeClass( 'hidden' );
								document.forms[ 1 ].action = "paymentPosting.do?method=paymentPosting";
								document.forms[ 1 ].submit();
							}
						}
					} );
					/* --------END Payment Posting ----------- */

					/* -------- Excel Upload for POD ----------- */
					$( '#submit_POD' ).click( function(){
						var statusExcelFile = $( '#fileUpload' ).val();
						var valid = true;

						if ( statusExcelFile == '' || statusExcelFile == null || statusExcelFile == undefined ) {

							$( '#error_message_upload_gis ~ font' ).removeClass( 'hidden' );
							$( '#inputFileName' ).addClass( 'red' );
							$( '#inputFileName' ).removeClass( 'green' );

							valid = false;
						}
						else {
							$( '#inputFileName' ).removeClass( 'red' );
							// $( '#inputFileName' ).addClass( 'green' );
						}
						if ( valid ) {
							if ( confirm( "Are you sure you want to upload POD file for cheque?" ) ) {
								document.forms[ 1 ].action = 'refund.do?method=uploadPODFile';
								document.forms[ 1 ].submit();
							}
						}
					} );
				} );

function anyOne(){
	var flag = true;
	if ( !$( '#pp_customerId' ).val() && !$( '#pp_crfId' ).val() ) {
		flag = false;
		$( '#eitherError' ).show().html( "Please provide at least 'Customer ID' or 'CAF Number'" ).addClass( 'errorTextbox' ).css( {
			'width' : '260px', 'top' : '54px'
		} );
		$( '#pp_crfId,#pp_customerId' ).attr( 'readonly', false );
	}
	else {
		$( '#eitherError' ).hide();
	}
	return flag;
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
				dwr.util.setValue( "recordId", list.recordId );
				dwr.util.setValue( "pp_customerId_hdn", list.customerId );
				dwr.util.setValue( "pp_customerId", list.customerId );
				dwr.util.setValue( "pr_custId", list.customerId );
				dwr.util.setValue( "installationStatus", list.installationStatus );
				// dwr.util.setValue( "pp_serviceType", list.serviceType );
				dwr.util.setValue( "uploadDocCRF", list.crfId );
				dwr.util.setValue( "pp_crfId", list.crfId );
				dwr.util.setValue( "idRevCrfId", list.crfId );
				dwr.util.setValue( "idRevCustomerId", list.customerId );
				crmDwr.getBouncingReason( list.installationStatus, function( list ){
					if ( list != null ) {
						dwr.util.removeAllOptions( 'bouncingReason' );
						dwr.util.addOptions( 'bouncingReason', [
							"Please Select"
						] );
						dwr.util.addOptions( 'bouncingReason', list, "qrcSubSubCategory", "qrcSubSubCategory" );
					}

				} );
			}
			else {
				dwr.util.setValue( "installationStatus", "" );
				alert( "No Customer details in the system for this Customer ID, Please provide correct Customer ID or CRF Number." );
			}
		}
	}
}

function widthChange( val ){
	if ( val == 'E' ) {
		$( '.dropdownWithoutJs, .dropdownWithoutJs select' ).addClass( 'width320' );
		$( '.dropdownWithoutJs' ).addClass( 'width320Bg' );
	}
	else {
		$( '.dropdownWithoutJs, .dropdownWithoutJs select' ).removeClass( 'width320' );
		$( '.dropdownWithoutJs' ).removeClass( 'width320Bg' );
	}
}

/*
 * function ValidateLength(txt) { var index = txt.toString().indexOf('.'); if (index >= 0){ var substr = txt.toString().split('.'); if(substr[1].length > 2){ document.getElementById("pp_crmPaymentDetailsPojo.paidAmount").value = substr[0]+"."+parseInt(substr[1]/10); alert("Please provide vlaues not
 * more than 2 decimal places"); } } else { if(txt.toString().length>6) { document.getElementById("pp_crmPaymentDetailsPojo.paidAmount").value = parseInt(txt/10); alert("Please provide vlaues not more than 6 digits"); } } }
 */

function paymentError(){
	var flag = true;
	if ( $( '#paymentDate' ).val() != "" ) {
		var dateStr = $( '#paymentDate' ).val();
		flag = pastDateValidityChecker( dateStr, 0 );
		if ( !flag ) {
			$( '#paymentDate ~ font' ).text( 'Please Provide correct Payment Date' ).show();
		}
		else {
			$( '#paymentDate ~ font' ).text( 'Please Provide correct payment Date' ).hide();
		}
	}
	if ( $( '#realizationStatus' ).val() == "NRL" ) {
		if ( $( '#IDBouncingDate' ).val() != "" ) {
			var dateStr = $( '#IDBouncingDate' ).val();
			flag = pastDateValidityChecker( dateStr, 0 );
			if ( !flag ) {
				$( '#IDBouncingDate ~ font' ).text( 'Please Provide correct Bouncing Date' ).show();
			}
			else {
				$( '#IDBouncingDate ~ font' ).text( 'Please Provide correct Bouncing Date' ).hide();
			}
		}
		if ( $( '#bouncingReason' ).val() == "" ) {
			flag = false;
			$( '#bouncingReason ~ font' ).text( 'Please Select Bouncing Reason' ).show();
		}

		if ( $( '#bouncingReason' ).val() != "" ) {
			$( '#bouncingReason ~ font' ).text( 'Please Select Bouncing Reason' ).hide();
		}
	}
	return flag;
}

function hideBouncingReason( inParam ){
	if ( inParam == 'NRL' ) {
		$( '#hideUnhideBouncingReason' ).removeClass( 'hide1' );
	}
	else {
		$( '#hideUnhideBouncingReason' ).addClass( 'hide1' );
	}
}

function getPaymentChannel( inPaymentMode, method ){
	
	crmDwr.getPaymentChannelByPaymentMode( inPaymentMode, function( list ){
		addPayemntChannel( "paymentChannel", method, list );
	} );
	function addPayemntChannel( id, method, list ){

		if ( list != null ) {
			dwr.util.removeAllOptions( id );
			if ( ( method != null ) && ( method != "" ) ) {
				dwr.util.addOptions( id, [
				{
					"value" : "", "label" : "All"
				}
			], "value", "label" );
			}
			else {
				dwr.util.addOptions( id, [
				{
					"value" : "0", "label" : "Please Select"
				}
			], "value", "label" );
			}

			dwr.util.addOptions( id, list, "contentValue", "contentName" );
		}

	}
}
/*function getPaymentCaseStatus( inPaymentMode, method ){
	
	crmDwr.getCaseStatusByPaymentMode( inPaymentMode, function( list ){
		addCaseStatus( "caseStatus", method, list );
	} );
	function addCaseStatus( id, method, list ){

		if ( list != null ) {
			dwr.util.removeAllOptions( id );
			if ( ( method != null ) && ( method != "" ) ) {
				dwr.util.addOptions( id, [
				{
					"value" : "", "label" : "All"
				}
			], "value", "label" );
			}
			else {
				dwr.util.addOptions( id, [
				{
					"value" : "0", "label" : "Please Select"
				}
			], "value", "label" );
			}

			dwr.util.addOptions( id, list, "contentValue", "contentName" );
		}

	}
}*/