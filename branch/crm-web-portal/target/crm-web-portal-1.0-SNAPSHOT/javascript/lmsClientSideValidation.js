var selectErrorPrefix = "Please select";
var provideErrorPrefix = "Please provide";
var invalidErrorPrefix = provideErrorPrefix + " valid";
var incorrectLengthPrefix = "Incorrect length for";
var email_regex = /^[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,4}$/i;
var pincode_regex = /^[0-9]{6}$/i;
var crfId_regex = /^RA[0-9]{6}$|^EA[0-9]{4}$/i;
var alphaNumeric_regex = /^([0-9\s]+[a-zA-Z]|[a-zA-Z])[0-9a-zA-Z\s]*$/i;

var appointmentTimings = new Array( "Morning (9am-12pm)", "Afternoon (12pm-4pm)", "Evening (4pm-8pm)", "Any Time" );
var appointmentModes = new Array( "Face to Face", "Telephonic" );
var nextraProducts = new Array( "FTTX","FTTN" );
var lmsReferralSources = new Array( "ReferaFriendCampaign", "Hoardings", "Others", "Website" );

var lmsPageErrorArray = new Array();

function $input( inVariable ){
	if ( null != document.getElementById( inVariable ) ) {
		return document.getElementById( inVariable ).value;
	}
	else {
		return null;
	}
}

function $id( inVariable ){
	if ( null != document.getElementById( inVariable ) ) {
		return document.getElementById( inVariable );
	}
	else {
		return null;
	}
}

function $isSelected( inTagId ){
	if ( null != document.getElementById( inTagId ) ) {
		return document.getElementById( inTagId ).checked;
	}
	else {
		return false;
	}
}

function $empty( inValue ){
	if ( inValue == "" || inValue.length == 0 || inValue == 0 )
		return true;
	else
		return false;
}

function $isValidWithRegex( inComparingRegex, inValueToCompare ){
	var isValid = false;
	if ( inComparingRegex.test( inValueToCompare ) ) {
		isValid = true;
	}
	return isValid;
}

function $isValidLength( inValueToCheck, inMinLength, inMaxLength ){
	var isValidByLength = false;
	if ( $empty( inMinLength ) ) {
		inMinLength = 0;
	}
	if ( $empty( inMaxLength ) ) {
		inMinLength = 100;
	}
	if ( inValueToCheck.length >= inMinLength && inValueToCheck.length <= inMaxLength ) {
		isValidByLength = true;
	}
	return isValidByLength;
}

function $showErrorMsg( inErrorMsgId, inErrorMsg ){
	var errorID = inErrorMsgId + 'ErrorId';
	if ( !$getDuplicateErrorId( errorID ) ) {
		var errorMsg = '<font id="' + errorID + '" class="Lms_Reference">' + inErrorMsg + '</font>';
		$( "#" + inErrorMsgId ).after( errorMsg );
		lmsPageErrorArray.push( errorID );
	}
}

function $getDuplicateErrorId( inErrorId ){
	var isDuplicate = false;
	if ( lmsPageErrorArray.length > 0 ) {
		for ( var id = 0; id < lmsPageErrorArray.length; id++ ) {
			if ( inErrorId == lmsPageErrorArray[ id ] ) {
				isDuplicate = true;
				break;
			}
		}
	}
	return isDuplicate;
}

function $hideErrorMsg(){
	if ( lmsPageErrorArray.length > 0 ) {
		for ( var id = 0; id < lmsPageErrorArray.length; id++ ) {
			$( "#" + lmsPageErrorArray[ id ] ).remove();
		}
		lmsPageErrorArray.length = 0;
	}
}
function $hideSingleErrorMsg( inErrorMsgId ){
	var errorID = inErrorMsgId + 'ErrorId';
	if ( lmsPageErrorArray.length > 0 ) {
		for ( var id = 0; id < lmsPageErrorArray.length; id++ ) {
			if ( errorID == lmsPageErrorArray[ id ] ) {
				document.getElementById( lmsPageErrorArray[ id ] ).innerHTML = "";
				break;
			}
		}
		lmsPageErrorArray.length = lmsPageErrorArray.length - 1;
	}
}

function mobileN_CTIChecks(){
	if ( document.getElementById( "generateLmsCTI" ).value != "" && ( document.getElementById( "generateLmsCTI" ).value != "0" )
			&& ( document.getElementById( "generateLmsCTI" ).value.length < 10 ) ) {
		var errorMsg = "'Please enter 10 digit valid calling number' ";
		$showErrorMsg( "generateLmsCTI", errorMsg );
	}

	mobileValidation( 'generateLmsMobile' );
}

function mobileValidation( elementId ){
	if ( $empty( $input( elementId ) ) ) {
		var errorMsg = "'Mobile Number' " + getMobileMesg();
		$showErrorMsg( elementId, errorMsg );
	}
	else if ( !validateMobile( $input( elementId ) ) ) {
		var errorMsg = "'Mobile Number' " + getMobileMesg();
		$showErrorMsg( elementId, errorMsg );
	}
}
function remarksEmptyCheck(){
	if ( $empty( $input( 'remarks' ).trim() ) ) {
		var errorMsg = provideErrorPrefix + " 'Remarks'.";
		$showErrorMsg( 'remarks', errorMsg );
	}
	else if ( !$isValidLength( $input( 'remarks' ).trim(), 2, 4000 ) ) {
		var errorMsg = "Please enter Remarks between [2-4000]";
		$showErrorMsg( 'remarks', errorMsg );
	}
}

function appointmentChecks(){
	if ( null != $input( 'appointmentDate' ) ) {
		if ( !$empty( $input( 'appointmentDate' ) ) ) {
			//var appointmentDateDiff = dateDiff( new Date(), new Date( $input( 'appointmentDate' ) ) );
			var appointmentDateDiff =  dateDiff( getISODate( $input( 'appointmentDate' )  ), new Date() );
			var isTimingSelected = false;
			for ( var id = 0; id < appointmentTimings.length; id++ ) {
				isTimingSelected = $isSelected( appointmentTimings[ id ] );
				if ( isTimingSelected ) {
					break;
				}
			}
			var isModeSelected = false;
			var selectedValue = "";
			for ( var id = 0; id < appointmentModes.length; id++ ) {
				isModeSelected = $isSelected( appointmentModes[ id ] );
				if ( isModeSelected ) {
					selectedValue = appointmentModes[ id ];
					break;
				}
			}
			if ( appointmentDateDiff > 0 ) {
				var errorMsg = "'Appointment Date' must not be out dated.";
				$showErrorMsg( 'appointmentDate', errorMsg );
			}
			else if ( appointmentDateDiff == 0 && isTimingSelected ) {
				validateAppointmentTiming();
			}
			if ( !isTimingSelected ) {
				var errorMsg = selectErrorPrefix + " 'Appointment Timing'.";
				$showErrorMsg( 'appointmentTiming', errorMsg );
			}

			if ( !isModeSelected ) {
				var errorMsg = selectErrorPrefix + " 'Appointment Mode'.";
				$showErrorMsg( 'appointmentMode', errorMsg );
			}
			// if ( $empty( $input( 'idApRemark' ) ) ) {
			// var errorMsg = provideErrorPrefix + " 'Appointment Remarks'.";
			// $showErrorMsg( 'idApRemark', errorMsg );
			// }
			// else if ( !$isValidLength( $input( 'idApRemark' ), 0, 1024 ) ) {
			// var errorMsg = "Suggested 'Appointment Remarks' length 1024.";
			// $showErrorMsg( 'idApRemark', errorMsg );
			// }
			// if ( selectedValue == appointmentModes[ 0 ] ) {
			// if ( $empty( $input( 'idAddress' ) ) ) {
			// var errorMsg = provideErrorPrefix + " 'Appointment Address'.";
			// $showErrorMsg( 'idAddress', errorMsg );
			// }
			// else if ( !$isValidLength( $input( 'idAddress' ), 0, 256 ) ) {
			// var errorMsg = "Suggested 'Appointment Address' length 256.";
			// $showErrorMsg( 'idAddress', errorMsg );
			// }
			// }
			// else if ( selectedValue == appointmentModes[ 1 ] ) {
			// if ( $empty( $input( 'idContactNo' ) ) ) {
			// var errorMsg = "Appointment 'Mobile Number' " + getMobileMesg();
			// $showErrorMsg( 'idContactNo', errorMsg );
			// }
			// else if ( !validateMobile( $input( 'idContactNo' ) ) ) {
			// var errorMsg = "Appointment 'Mobile Number' " + getMobileMesg();
			// $showErrorMsg( 'idContactNo', errorMsg );
			// }
			//
			// }
		}
	}
}

function emptyChecks(){
	if ( $empty( $input( 'generateLmsC_Name' ) ) ) {
		var errorMsg = provideErrorPrefix + " 'Customer Name'.";
		$showErrorMsg( 'generateLmsC_Name', errorMsg );
	}
	/*
	 * if ( $empty( $input( 'generateLmsEmail' ) ) ) { var errorMsg = provideErrorPrefix + " 'Customer Email'."; $showErrorMsg( 'generateLmsEmail', errorMsg ); }
	 */
	if ( $empty( $input( 'statelms' ) ) ) {
		var errorMsg = selectErrorPrefix + " 'State'.";
		$showErrorMsg( 'statelms', errorMsg );
	}
	if ( $empty( $input( 'citylms' ) ) || $input( 'citylms' ) == 'All Cities' ) {
		var errorMsg = selectErrorPrefix + " 'City'.";
		$showErrorMsg( 'citylms', errorMsg );
	}
	if ( $empty( $input( 'arealms' ) ) ) {
		var errorMsg = provideErrorPrefix + " 'Area'.";
		$showErrorMsg( 'arealms', errorMsg );
	}
	if ( $empty( $input( 'localitylms' ) ) ) {
		var errorMsg = provideErrorPrefix + " 'Locality/ Sector - Society'.";
		$showErrorMsg( 'localitylms', errorMsg );
	}
	if ( $empty( $input( 'houseNoLms' ) ) ) {
		var errorMsg = provideErrorPrefix + " 'House Number'.";
		$showErrorMsg( 'houseNoLms', errorMsg );
	}
	/*
	 * if ( $empty( $input( 'landmarkLms' ) ) ) { var errorMsg = provideErrorPrefix + " 'Landmark'."; $showErrorMsg( 'landmarkLms', errorMsg ); }
	 */
	if ( $empty( $input( 'pincodeLms' ) ) ) {
		var errorMsg = provideErrorPrefix + " 'Pincode'.";
		$showErrorMsg( 'pincodeLms', errorMsg );
	}
}

function feasibilityChecks(){
	if ( ( !$empty( $input( 'citylms' ) ) || !$input( 'citylms' ) == 'All Cities' ) && $empty( $input( 'statelms' ) ) ) {
		var errorMsg = selectErrorPrefix + " 'State'.";
		$showErrorMsg( 'statelms', errorMsg );
	}
	if ( !$empty( $input( 'arealms' ) ) ) {
		if ( $empty( $input( 'statelms' ) ) ) {
			var errorMsg = selectErrorPrefix + " 'State'.";
			$showErrorMsg( 'statelms', errorMsg );
		}
		if ( $empty( $input( 'citylms' ) ) || $input( 'citylms' ) == 'All Cities' ) {
			var errorMsg = selectErrorPrefix + " 'City'.";
			$showErrorMsg( 'citylms', errorMsg );
		}
	}
	if ( !$empty( $input( 'localitylms' ) ) ) {
		if ( $empty( $input( 'statelms' ) ) ) {
			var errorMsg = selectErrorPrefix + " 'State'.";
			$showErrorMsg( 'statelms', errorMsg );
		}
		if ( $empty( $input( 'citylms' ) ) || $input( 'citylms' ) == 'All Cities' ) {
			var errorMsg = selectErrorPrefix + " 'City'.";
			$showErrorMsg( 'citylms', errorMsg );
		}
		if ( $empty( $input( 'arealms' ) ) ) {
			var errorMsg = provideErrorPrefix + " 'Area'.";
			$showErrorMsg( 'arealms', errorMsg );
		}
	}
}

function nonEmptyChecks(){
	// if ( !$isValidWithRegex( email_regex ,$input( 'generateLmsEmail' ) ) && !$empty( $input( 'generateLmsEmail' ) ) )
	if ( !validateEmail( $input( 'generateLmsEmail' ) ) && !$empty( $input( 'generateLmsEmail' ) ) ) {
		var errorMsg = invalidErrorPrefix + " 'Customer Email'.";
		$showErrorMsg( 'generateLmsEmail', errorMsg );
	}
	else if ( !validatePin( $input( 'pincodeLms' ) ) && !$empty( $input( 'pincodeLms' ) ) ) {
		var errorMsg = invalidErrorPrefix + " 'Pincode'.";
		$showErrorMsg( 'pincodeLms', errorMsg );
	}
	if ( !$isValidLength( $input( 'generateLmsC_Name' ), 1, 50 ) && !$empty( $input( 'generateLmsC_Name' ) ) ) {
		var errorMsg = "Suggested length 1-50 characters.";
		$showErrorMsg( 'generateLmsC_Name', errorMsg );
	}
	else if ( !validateAlphanumericSpace( $input( 'generateLmsC_Name' ) ) && !$empty( $input( 'generateLmsC_Name' ) ) ) {
		var errorMsg = "Name must be alphabets.";
		$showErrorMsg( 'generateLmsC_Name', errorMsg );
	}
	if ( !validateNotAllowSpecialCharacter( $input( 'houseNoLms' ) ) && !$empty( $input( 'houseNoLms' ) ) ) {
		var errorMsg = "Please provide valid house number.";
		$showErrorMsg( 'houseNoLms', errorMsg );
	}
	if ( !validateNotAllowSpecialCharacter( $input( 'landmarkLms' ) ) && !$empty( $input( 'landmarkLms' ) ) ) {
		var errorMsg = "Please provide valid landmark";
		$showErrorMsg( 'landmarkLms', errorMsg );
	}
}

function checkProductInterested(){
	var isProductSelected = false;
	var selectedValue = "";
	for ( var id = 0; id < nextraProducts.length; id++ ) {
		isProductSelected = $isSelected( nextraProducts[ id ] );
		if ( isProductSelected ) {
			selectedValue = nextraProducts[ id ];
			break;
		}
	}
	if ( !isProductSelected ) {
		var errorMsg = selectErrorPrefix + " 'Product Interested'.";
		$showErrorMsg( 'lmsProducts', errorMsg );
	}
	return selectedValue;
}

function checkReferralSource(){
	var isReferralSelected = false;
	var selectedValue = "";
	for ( var id = 0; id < lmsReferralSources.length; id++ ) {
		isReferralSelected = $isSelected( lmsReferralSources[ id ] );
		if ( isReferralSelected ) {
			selectedValue = lmsReferralSources[ id ];
			break;
		}
	}
	if ( selectedValue == "Others" && $empty( $input( 'otherSource' ) ) ) {
		var errorMsg = provideErrorPrefix + " other 'Referral Source'.";
		$showErrorMsg( 'otherSource', errorMsg );
	}
	return selectedValue;
}

function submitFormQRC( inParameter ){
	$( 'span.closeReason' ).hide();
	if ( inParameter == 'create' ) {
		remarksEmptyCheck();
		mobileN_CTIChecks();
		appointmentChecks();
		nonEmptyChecks();
		// checkProductInterested();
		checkReferralSource();
		feasibilityChecks();
		var isRemarksSelected = prepareReferralSourceStr();
		if ( lmsPageErrorArray.length < 1 && isRemarksSelected ) {
			var answer = confirm( "Please confirm you are going to create the Lead" );
			if ( answer ) {
				document.forms[ 1 ].action = "leadGeneration.do?method=leadGenereation";
				document.forms[ 1 ].submit();
			}
		}
	}
	else if ( inParameter == 'update' ) {
		$( 'span.actionPerform' ).hide();
		if ( $input( 'actionToBePerformed' ) == '' ) {
			var errorMsg = selectErrorPrefix + " action to perform.";
			$showErrorMsg( 'actionToBePerformed', errorMsg );
		}
		else {
			if ( $input( 'actionToBePerformed' ) == 'CL' ) {
				action_CL();
				if ( lmsPageErrorArray.length < 1 ) {
					var answer = confirm( "Please confirm you would like to close the Lead" );
					if ( answer ) {
						document.forms[ 1 ].action = "leadGeneration.do?method=performAction";
						document.forms[ 1 ].submit();
					}
				}
			}
			else if ( $input( 'actionToBePerformed' ) == 'SC' ) {
				remarksEmptyCheck();
				noAppointment();
				if ( lmsPageErrorArray.length < 1 ) {
					var answer = confirm( "Please confirm you are going to forward the Lead to SC" );
					if ( answer ) {
						$( "#idCloseReason" ).remove();
						document.forms[ 1 ].action = "leadGeneration.do?method=performAction";
						document.forms[ 1 ].submit();
					}
				}
			}
			else if ( $input( 'actionToBePerformed' ) == 'AM' ) {
				if ( $( "input[name='lmsPojo.lmsStage']" ).val() == 'SC' ) {
					/*if ( $( "input[name='lmsPojo.product']:checked" ).val() == 'RF' ) {
						var feasibility = document.getElementById( "hiddenFeasibility" );
						if ( !( null != feasibility && feasibility.value == "Y" ) ) {
							var errorMsg = "[Radio Frequency] case can't be forward to Area Manager";
							$showErrorMsg( 'lmsProducts', errorMsg );
						}
					}*/
					if ( $empty( $input( 'idAMs' ) ) ) {
						var errorMsg = "Area-Manager missing, can't be forward to Area Manager";
						$showErrorMsg( 'idAMs', errorMsg );
					}
					emptyChecks();
					//appointmentChecks();
					remarksEmptyCheck();
					nonEmptyChecks();
					checkProductInterested();
				}
				if ( $( "input[name='lmsPojo.lmsStage']" ).val() == 'CLHD' ) {
					if ( $empty( $input( 'idAMs' ) ) ) {
						var errorMsg = "Area-Manager missing, can't be forward to Area Manager";
						$showErrorMsg( 'idAMs', errorMsg );
					}
					/*appointMentDateCheck();
					appointmentChecks();*/
					remarksEmptyCheck();
				}
				if ( $( "input[name='lmsPojo.lmsStage']" ).val() == 'FT' ) {
					if ( $empty( $input( 'feasibilityLms' ) ) ) {
						var errorMsg = selectErrorPrefix + " Area Feasibility";
						$showErrorMsg( 'feasibilityLms', errorMsg );
					}
					if ( $input( 'feasibilityLms' ) == 'N' && $input( 'actionToBePerformed' ) == 'AM' ) {
						var errorMsg = "Non Feasibile Area Lead can't be forward to Area Manager.";
						$showErrorMsg( 'feasibilityLms', errorMsg );
					}
					remarksEmptyCheck();
					nonEmptyChecks();
				}
				if ( lmsPageErrorArray.length < 1 ) {
					if ( null != document.getElementById( "feasibilityLms" ) ) {
						document.getElementById( "hiddenFeasibility" ).value = document.getElementById( "feasibilityLms" ).value;
					}
					var answer = confirm( "Please confirm you are going to forward the Lead to Area Manager" );
					if ( answer ) {
						$( "#idCloseReason" ).remove();
						document.forms[ 1 ].action = "leadGeneration.do?method=performAction";
						document.forms[ 1 ].submit();
					}
				}
			}
			else if ( $input( 'actionToBePerformed' ) == 'FT' ) {
				if ( $( "input[name='lmsPojo.lmsStage']" ).val() == 'SC'
						&& ( $( "input[name='lmsPojo.product']:checked" ).val() == 'BB' || $( "input[name='lmsPojo.product']:checked" ).val() == 'EOC' ) ) {
					var errorMsg = "[Broad Band]/[Ethernet on Cable] cases can't be forward to Fullfillment Team";
					$showErrorMsg( 'lmsProducts', errorMsg );
				}
				emptyChecks();
				//appointmentChecks();
				remarksEmptyCheck();
				nonEmptyChecks();
				checkProductInterested();
				if ( lmsPageErrorArray.length < 1 ) {
					var answer = confirm( "Please confirm you are going to forward the Lead to Fulfillment Team" );
					if ( answer ) {
						$( "#idCloseReason" ).remove();
						document.forms[ 1 ].action = "leadGeneration.do?method=performAction";
						document.forms[ 1 ].submit();
					}
				}
			}
			else if ( $input( 'actionToBePerformed' ) == 'ST' ) {
				if ( $input( 'idBP' ) == '' ) {
					var errorMsg = selectErrorPrefix + " 'Business Partner'.";
					$showErrorMsg( 'idBP', errorMsg );
				}
				//appointmentChecks();
				remarksEmptyCheck();
				if ( lmsPageErrorArray.length < 1 ) {
					var answer = confirm( "Please confirm you are going to forward the Lead to Sales Team" );
					if ( answer ) {
						$( "#idCloseReason" ).remove();
						document.forms[ 1 ].action = "leadGeneration.do?method=performAction";
						document.forms[ 1 ].submit();
					}
				}
			}
			else if ( $input( 'actionToBePerformed' ) == 'CLHD' ) {
				remarksEmptyCheck();
				//appointmentChecks();
				if ( lmsPageErrorArray.length < 1 ) {
					var answer = confirm( "Please confirm you would like to forward the Lead to Cluster Head" );
					if ( answer ) {
						$( "#idCloseReason" ).remove();
						document.forms[ 1 ].action = "leadGeneration.do?method=performAction";
						document.forms[ 1 ].submit();
					}
				}
			}
			else if ( $input( 'actionToBePerformed' ) == 'ML' ) {
				var url = "leadGeneration.do?method=performAction";
				var allowedModule = document.getElementById( "allowedModule" ).value;
				if ( crfIDsArray.length < 1 && allowedModule == 'Y' ) {
					var errorMsg = provideErrorPrefix + " CAF Id.";
					$showErrorMsg( 'crfIdBlock', errorMsg );

				}
				noAppointment();
				remarksEmptyCheck();
				if ( lmsPageErrorArray.length < 1 ) {
					var answer = confirm( "Please confirm you are going to mature the Lead" );
					if ( answer ) {

						if ( allowedModule == 'Y' ) {
							url = url + "&crfIds=" + crfIDsArray.toString();
						}
						$( "#idCloseReason" ).remove();
						document.forms[ 1 ].action = url;
						document.forms[ 1 ].submit();
					}
				}
			}
		}
	}
}

function action_CL(){
	remarksEmptyCheck();
	noAppointment();
	if ( $input( 'idCloseReason' ) == '' ) {
		var errorMsg = selectErrorPrefix + " closing reason.";
		$showErrorMsg( 'idCloseReason', errorMsg );
	}
}

function noAppointment(){
	if ( null != $input( 'appointmentDate' ) ) {
		if ( '' != $input( 'appointmentDate' ) && $input( 'appointmentId' ) == '0' ) {
			var errorMsg = "Appointment can't be saved for selected action. Please reset appointment to proceed.";
			$showErrorMsg( 'idApRemark', errorMsg );
		}
	}
}

function onOffAppointment(){
	if ( $empty( $input( 'appointmentDate' ) ) ) {
		$( '#customerAppointmentDetails input' ).attr( 'disabled', true );
		$( '#customerAppointmentDetails textarea' ).attr( 'readonly', true );
		$( '#appointmentDate' ).attr( 'disabled', false );
	}
	else {
		$( '#customerAppointmentDetails input' ).attr( 'disabled', false );
		$( '#customerAppointmentDetails textarea' ).attr( 'readonly', false );
	}
}

function appointMentDateCheck(){

	if ( $empty( $input( 'appointmentDate' ) ) ) {
		var errorMsg = "'Appointment Date' must be selected.";
		$showErrorMsg( 'appointmentDate', errorMsg );
	}
}

function isSocietyFesible(){
	if ( null != $id( 'SOCIETY_FEASIBLITY_ALERT' ) ) {
		if ( ( !$empty( $input( 'citylms' ) ) || !$input( 'citylms' ) == 'All Cities' ) && !$empty( $input( 'statelms' ) )
				&& !$empty( $input( 'arealms' ) ) && !$empty( $input( 'localitylms' ) ) ) {
			// var locSoc = $input( 'localitylms' ).split( ' - ' );
			crmDwr
					.isLmsDataFeasible(
							$input( 'statelms' ),
							$input( 'citylms' ),
							$input( 'arealms' ),
							$input( 'localitylms' ),
							'',
							{
								callback : function( isFesible ){
									if ( isFesible ) {
										document.getElementById( 'SOCIETY_FEASIBLITY_ALERT' ).innerHTML = "Please advise to customer that broadband service is available at this location.";
										$( "#SOCIETY_FEASIBLITY_ALERT" ).removeClass( "error_message" );
										$( "#SOCIETY_FEASIBLITY_ALERT" ).addClass( "success_message" );
										$( "#shiftingInitiation" ).removeClass( "hidden" );
									}
									else {
										document.getElementById( 'SOCIETY_FEASIBLITY_ALERT' ).innerHTML = "Broadband service is not available at this location.";
										$( "#SOCIETY_FEASIBLITY_ALERT" ).removeClass( "success_message" );
										$( "#SOCIETY_FEASIBLITY_ALERT" ).addClass( "error_message" );
										$( "#shiftingInitiation" ).addClass( "hidden" );
									}
								}
							} );
		}
		else {
			document.getElementById( 'SOCIETY_FEASIBLITY_ALERT' ).innerHTML = "To check feasibility, provide data till Locality / Sector - Society.";
		}
	}
}