$( document ).ready( function(){
	if ( $( '#mobile' ).val() == "0" ) {
		$( '#mobile' ).val( '' );
	}
	if ( $( '#passportMobileNo' ).val() == "0" ) {
		$( '#passportMobileNo' ).val( '' );
	}
	if ( $( '#connectionTypeCRF' ).val() != 'Ind' && $( '#connectionTypeCRF' ).val() != 'Prop' ) {
		$( '#nationalityTypeId' ).val( 'Indian' );
	}
	$( '#submitCustprof' ).click( function(){

		if ( validateCustomerConnectionType() && commonError() ) {
			var answer = confirm( "Please confirm if you want to modify customer category details!" );
			if ( answer ) {
				$( '.loadingPopup' ).removeClass( 'hidden' );
				$( '#customerProfileForm' ).attr( 'action', 'customerProfile.do?method=updateCustomerCategory' );
				$( '#customerProfileForm' ).submit();
			}
		}
	} );

	$( '#submitCustOwner' ).click( function(){

		if ( validateCustomerOwnership() && commonError() ) {
			var answer = confirm( "Please confirm if you want to modify customer ownership details!" );
			if ( answer ) {
				$( '.loadingPopup' ).removeClass( 'hidden' );
				$( '#customerProfileForm' ).attr( 'action', 'customerProfile.do?method=updateCustomerOwnerShip' );
				$( '#customerProfileForm' ).submit();
			}
		}
	} );

} );

function commonError(){
	var valid = true;
	$( '#connectionTypeCRF ~ font' ).addClass( 'hidden' );
	$( '#firstName ~ font' ).addClass( 'hidden' );
	$( '#firstName ~ span' ).addClass( 'hidden' );
	$( '#firstName ~ label' ).addClass( 'hidden' );
	$( '#lastName ~ font' ).addClass( 'hidden' );
	$( '#lastName ~ span' ).addClass( 'hidden' );
	$( '#lastName ~ label' ).addClass( 'hidden' );
	$( '#companyName ~ font' ).addClass( 'hidden' );
	$( '#companyName ~ span' ).addClass( 'hidden' );
	$( '#companyName ~ label' ).addClass( 'hidden' );
	$( '#registeredMobile ~ font' ).addClass( 'hidden' );
	$( '#registeredMobile ~ span' ).addClass( 'hidden' );
	//$( '#registeredTelephone ~ font' ).addClass( 'hidden' );
	//$( '#registeredTelephone ~ span' ).addClass( 'hidden' );
	$( '#emailId ~ font' ).addClass( 'hidden' );
	$( '#emailId ~ span' ).addClass( 'hidden' );
	//$( '#altEmailId ~ font' ).addClass( 'hidden' );
	$( '#mobile ~ font' ).addClass( 'hidden' );
	$( '#pan ~ font' ).addClass( 'hidden' );
	$( '#pan ~ span' ).addClass( 'hidden' );
	$( '#fatherFirstName ~ font' ).addClass( 'hidden' );
	$( '#fatherFirstName ~ label' ).addClass( 'hidden' );
	$( '#fatherLastName ~ font' ).addClass( 'hidden' );
	$( '#fatherLastName ~ label' ).addClass( 'hidden' );
	$( '#motherFirstName ~ font' ).addClass( 'hidden' );
	$( '#motherFirstName ~ label' ).addClass( 'hidden' );
	$( '#motherLastName ~ font' ).addClass( 'hidden' );
	$( '#motherLastName ~ label' ).addClass( 'hidden' );
	$( '#fatherFirstName ~ span' ).addClass( 'hidden' );
	$( '#fatherLastName ~ span' ).addClass( 'hidden' );
	$( '#motherFirstName ~ span' ).addClass( 'hidden' );
	$( '#motherLastName ~ span' ).addClass( 'hidden' );
	$( '#nationalityValueId ~ font' ).addClass( 'hidden' );
	$( '#passportNo ~ font' ).addClass( 'hidden' );
	$( '#passportNo ~ span' ).addClass( 'hidden' );
	$( '#passportValidUpTo ~ font' ).addClass( 'hidden' );
	$( '#passportValidUpTo ~ span' ).addClass( 'hidden' );
	$( '#visaType ~ font' ).addClass( 'hidden' );
	$( '#visaValidUpTo ~ font' ).addClass( 'hidden' );
	$( '#visaValidUpTo ~ span' ).addClass( 'hidden' );
	$( '#passportFirstName ~ font' ).addClass( 'hidden' );
	$( '#passportFirstName ~ label' ).addClass( 'hidden' );
	$( '#passportLastName ~ font' ).addClass( 'hidden' );
	$( '#passportLastName ~ label' ).addClass( 'hidden' );
	$( '#passportFirstName ~ span' ).addClass( 'hidden' );
	$( '#passportFirstName ~ label' ).addClass( 'hidden' );
	$( '#passportLastName ~ span' ).addClass( 'hidden' );
	$( '#passportLastName ~ label' ).addClass( 'hidden' );
	$( '#passportMobileNo ~ font' ).addClass( 'hidden' );
	$( '#passportMobileNo ~ span' ).addClass( 'hidden' );
	$( '#passportAddress ~ font' ).addClass( 'hidden' );
	$( '#passportAddress ~ span' ).addClass( 'hidden' );
	$( '#orgCoordFirstName ~ font' ).addClass( 'hidden' );
	$( '#orgCoordFirstName ~ label' ).addClass( 'hidden' );
	$( '#orgCoordLastName ~ font' ).addClass( 'hidden' );
	$( '#orgCoordLastName ~ label' ).addClass( 'hidden' );
	$( '#authorizedSignatoryFirstName ~ font' ).addClass( 'hidden' );
	$( '#authorizedSignatoryFirstName ~ label' ).addClass( 'hidden' );
	$( '#authorizedSignatoryLastName ~ font' ).addClass( 'hidden' );
	$( '#authorizedSignatoryLastName ~ label' ).addClass( 'hidden' );
	$( '#authorizedSignatoryDesignation ~ font' ).addClass( 'hidden' );
	$( '#orgCoordFirstName ~ span' ).addClass( 'hidden' );
	$( '#orgCoordLastName ~ span' ).addClass( 'hidden' );
	$( '#authorizedSignatoryFirstName ~ span' ).addClass( 'hidden' );
	$( '#authorizedSignatoryLastName ~ span' ).addClass( 'hidden' );
	$( '#authorizedSignatoryDesignation ~ span' ).addClass( 'hidden' );
	$( '#ticketId ~ font' ).addClass( 'hidden' );
	$( '#remarks ~ font' ).addClass( 'hidden' );
	$( '#remarks ~ span' ).addClass( 'hidden' );
	$( '#birthdate ~ font' ).addClass( 'hidden' );
	$( '[name="custDetailsPojo.custGender"]' ).parent().next( 'font' ).addClass( 'hidden' );

	if ( $( '#connectionTypeCRF' ).val() == "0" ) {
		$( '#connectionTypeCRF ~ font' ).removeClass( 'hidden' );
		return false;
	}

	if ( $( '#connectionTypeCRF' ).val() == "Ind" || $( '#connectionTypeCRF' ).val() == "Prop" ) {
		if ( $( '#firstName' ).val().trim() == "" ) {
			$( '#firstName ~ font' ).removeClass( 'hidden' );
			valid = false;

		}
		else if ( $( '#firstName' ).val().trim() != "" && $( '#firstName' ).val().length < 1 ) {
			$( '#firstName ~ span' ).removeClass( 'hidden' );
			valid = false;
		}
		/*else if ( !isNaN( $( '#firstName' ).val() ) ) {
			$( '#firstName ~ label' ).removeClass( 'hidden' );
			valid = false;
		}*/
		else if ( !validateCustomerFirstNameForAlphaNumeric($( '#firstName' ).val()))
		{
			$( '#firstName ~ label' ).removeClass( 'hidden' );
			valid = false;
		}

		if ( $( '#birthdate' ).val() == "" ) {

			$( '#birthdate' ).nextAll( 'font' ).removeClass( 'hidden' );
			valid = false;
		}
		if ( $( '[name="custDetailsPojo.custGender"]:checked' ).val() == undefined ) {
			$( '[name="custDetailsPojo.custGender"]' ).parent().next( 'font' ).removeClass( 'hidden' );
			valid = false;
		}

		// if ( $( '#lastName' ).val().trim() == "" ) {
		// $( '#lastName ~ font' ).removeClass( 'hidden' );
		// valid = false;
		// }
		// if ( $( '#lastName' ).val().trim() != "" && $( '#lastName' ).val().length < 3 ) {
		// $( '#lastName ~ span' ).removeClass( 'hidden' );
		// valid = false;
		// }
		if ( $( '#lastName' ).val().trim() != "" && !validAlphaNameSpaceDot( $( '#lastName' ).val() ) ) {
			$( '#lastName ~ label' ).removeClass( 'hidden' );
			valid = false;
		}
		if ( $( '#registeredMobile' ).val().trim() == "" ) {
			$( '#registeredMobile ~ font' ).removeClass( 'hidden' );
			valid = false;
		}
		if ( $( '#registeredMobile' ).val().trim() != "" && !validateMobile( $( '#registeredMobile' ).val() ) ) {
			$( '#registeredMobile ~ span' ).removeClass( 'hidden' );
			valid = false;
		}
		/*if ( $( '#registeredTelephone' ).val().trim() == "" ) {
			$( '#registeredTelephone ~ font' ).removeClass( 'hidden' );
			valid = false;
		}
		if ( $( '#registeredTelephone' ).val().trim() != "" && !validateNumericMobileSearch( $( '#registeredTelephone' ).val() ) ) {
			$( '#registeredTelephone ~ span' ).removeClass( 'hidden' );
			valid = false;
		}
		if ( $( '#altEmailId' ).val() != "" && !validateEmail( $( '#altEmailId' ).val() ) ) {
			$( '#altEmailId ~ font' ).removeClass( 'hidden' );
			valid = false;
		}*/
		if ( $( '#emailId' ).val().trim() == "" ) {
			$( '#emailId ~ font' ).removeClass( 'hidden' );
			valid = false;
		}
		if ( $( '#emailId' ).val().trim() != "" && !validateEmail( $( '#emailId' ).val() ) ) {
			$( '#emailId ~ span' ).removeClass( 'hidden' );
			valid = false;
		}
		if ( $( '#mobile' ).val() != "" && !validateMobile( $( '#mobile' ).val() ) ) {
			$( '#mobile ~ font' ).removeClass( 'hidden' );
			valid = false;
		}

		if ( $( '#pan' ).val() != "" && !validatePanGirNo( $( '#pan' ).val() ) ) {
			$( '#pan ~ span' ).removeClass( 'hidden' );
			valid = false;
		}
		// else {
		// if ( valid ) {
		// valid = validatePANDetails();
		// }
		// }
		if ( $( '#fatherFirstName' ).val().trim() == "" ) {
			$( '#fatherFirstName ~ font' ).removeClass( 'hidden' );
			valid = false;
		}
		else if ( $( '#fatherFirstName' ).val().trim() != "" && $( '#fatherFirstName' ).val().length < 1 ) {
			$( '#fatherFirstName ~ span' ).removeClass( 'hidden' );
			valid = false;
		}
		/*else if ( !validAlphaNameSpaceDot( $( '#fatherFirstName' ).val() ) ) {
			$( '#fatherFirstName ~ label' ).removeClass( 'hidden' );
			valid = false;
		}*/
		else if ( !validateCustomerFirstNameForAlphaNumeric($( '#fatherFirstName' ).val()))
		{
			$( '#fatherFirstName ~ label' ).removeClass( 'hidden' );
			valid = false;
		}

		
		// if ( $( '#fatherLastName' ).val().trim() == "" ) {
		// $( '#fatherLastName ~ font' ).removeClass( 'hidden' );
		// valid = false;
		// }
		// if ( $( '#fatherLastName' ).val().trim() != "" && $( '#fatherLastName' ).val().length < 3 ) {
		// $( '#fatherLastName ~ span' ).removeClass( 'hidden' );
		// valid = false;
		// }
		if ( $( '#fatherLastName' ).val().trim() != "" && !validAlphaNameSpaceDot( $( '#fatherLastName' ).val() ) ) {
			$( '#fatherLastName ~ label' ).removeClass( 'hidden' );
			valid = false;
		}
		// if ( $( '#motherFirstName' ).val().trim() == "" ) {
		// $( '#motherFirstName ~ font' ).removeClass( 'hidden' );
		// valid = false;
		// }
		// else if ( $( '#motherFirstName' ).val().trim() != "" && $( '#motherFirstName' ).val().length < 3 ) {
		// $( '#motherFirstName ~ span' ).removeClass( 'hidden' );
		// valid = false;
		// }
		// else if ( !validAlphaNameSpaceDot( $( '#motherFirstName' ).val() ) ) {
		// $( '#motherFirstName ~ label' ).removeClass( 'hidden' );
		// valid = false;
		// }
		//
		// if ( $( '#motherLastName' ).val().trim() == "" ) {
		// $( '#motherLastName ~ font' ).removeClass( 'hidden' );
		// valid = false;
		// }
		// if ( $( '#motherLastName' ).val().trim() != "" && $( '#motherLastName' ).val().length < 3 ) {
		// $( '#motherLastName ~ span' ).removeClass( 'hidden' );
		// valid = false;
		// }
		// else if ( $( '#motherLastName' ).val().trim() != "" && !validAlphaNameSpaceDot( $( '#motherLastName' ).val() ) ) {
		// $( '#motherLastName ~ label' ).removeClass( 'hidden' );
		// valid = false;
		// }
		if ( $( '#nationalityTypeId' ).val() != "Indian" ) {
			if ( $( '#nationalityValueId' ).val().trim() == "" || $( '#nationalityValueId' ).val().length > 50 ) {
				$( '#nationalityValueId ~ font' ).removeClass( 'hidden' );
				valid = false;
			}
			if ( $( '#passportNo' ).val().trim() == "" ) {
				$( '#passportNo ~ font' ).removeClass( 'hidden' );
				valid = false;
			}
			if ( $( '#passportNo' ).val().trim() != "" && !validatePassport( $( '#passportNo' ).val() ) ) {
				$( '#passportNo ~ span' ).removeClass( 'hidden' );
				valid = false;
			}
			if ( $( '#passportValidUpTo' ).val().trim() == "" ) {
				$( '#passportValidUpTo ~ font' ).removeClass( 'hidden' );
				valid = false;
			}
			/*if ( $( '#passportValidUpTo' ).val() != "" && !dateValidityChecker( $( '#passportValidUpTo' ).val(), 30 ) ) {
				$( '#passportValidUpTo ~ span' ).removeClass( 'hidden' );
				valid = false;
			}*/
			if ( $( '#visaType' ).val() == "0" ) {
				$( '#visaType ~ font' ).removeClass( 'hidden' );
				valid = false;
			}
			if ( $( '#visaValidUpTo' ).val().trim() == "" ) {
				$( '#visaValidUpTo ~ font' ).removeClass( 'hidden' );
				valid = false;
			}
			if ( $( '#visaValidUpTo' ).val() != "" && !dateValidityChecker( $( '#visaValidUpTo' ).val(), 30 ) ) {
				$( '#visaValidUpTo ~ span' ).removeClass( 'hidden' );
				valid = false;
			}
			if ( $( '#passportFirstName' ).val().trim() == "" ) {
				$( '#passportFirstName ~ font' ).removeClass( 'hidden' );
				valid = false;
			}
			else if ( $( '#passportFirstName' ).val().trim() != "" && $( '#passportFirstName' ).val().length < 1 ) {
				$( '#passportFirstName ~ span' ).removeClass( 'hidden' );
				valid = false;
			}
			else if ( !validateCustomerFirstNameForAlphaNumeric( $( '#passportFirstName' ).val() ) ) {
				$( '#passportFirstName ~ label' ).removeClass( 'hidden' );
				valid = false;
			}
			// if ( $( '#passportLastName' ).val().trim() == "" ) {
			// $( '#passportLastName ~ font' ).removeClass( 'hidden' );
			// valid = false;
			// }
			if ( $( '#passportLastName' ).val().trim() != "" && $( '#passportLastName' ).val().length < 3 ) {
				$( '#passportLastName ~ span' ).removeClass( 'hidden' );
				valid = false;
			}
			else if ( $( '#passportLastName' ).val().trim() != "" && !validAlphaNameSpaceDot( $( '#passportLastName' ).val() ) ) {
				$( '#passportLastName ~ label' ).removeClass( 'hidden' );
				valid = false;
			}

			if ( $( '#passportMobileNo' ).val().trim() == "" ) {
				$( '#passportMobileNo ~ font' ).removeClass( 'hidden' );
				valid = false;
			}
			if ( $( '#passportMobileNo' ).val().trim() != "" && !validateMobile( $( '#passportMobileNo' ).val() ) ) {
				$( '#passportMobileNo ~ span' ).removeClass( 'hidden' );
				valid = false;
			}
			if ( $( '#passportAddress' ).val().trim() == "" ) {
				$( '#passportAddress ~ font' ).removeClass( 'hidden' );
				valid = false;
			}
			if ( $( '#passportAddress' ).val().trim() != "" && $( '#passportAddress' ).val().length > 255 ) {
				$( '#passportAddress ~ span' ).removeClass( 'hidden' );
				valid = false;
			}

		}
	}
	else {
		if ( $( '#companyName' ).val().trim() == "" ) {
			$( '#companyName ~ font' ).removeClass( 'hidden' );
			valid = false;
		}
		else if ( $( '#companyName' ).val().trim() != "" && $( '#companyName' ).val().length < 3 ) {
			$( '#companyName ~ span' ).removeClass( 'hidden' );
			valid = false;
		}
		else if ( !isNaN( $( '#companyName' ).val() ) ) {
			$( '#companyName ~ label' ).removeClass( 'hidden' );
			valid = false;
		}
		if ( $( '#registeredMobile' ).val().trim() == "" ) {
			$( '#registeredMobile ~ font' ).removeClass( 'hidden' );
			valid = false;
		}
		if ( $( '#registeredMobile' ).val().trim() != "" && !validateMobile( $( '#registeredMobile' ).val() ) ) {
			$( '#registeredMobile ~ span' ).removeClass( 'hidden' );
			valid = false;
		}
		/*if ( $( '#registeredTelephone' ).val().trim() == "" ) {
			$( '#registeredTelephone ~ font' ).removeClass( 'hidden' );
			valid = false;
		}
		if ( $( '#registeredTelephone' ).val().trim() != "" && !validateNumericMobileSearch( $( '#registeredTelephone' ).val() ) ) {
			$( '#registeredTelephone ~ span' ).removeClass( 'hidden' );
			valid = false;
		}*/
		if ( $( '#emailId' ).val().trim() == "" ) {
			$( '#emailId ~ font' ).removeClass( 'hidden' );
			valid = false;
		}
		if ( $( '#emailId' ).val().trim() != "" && !validateEmail( $( '#emailId' ).val() ) ) {
			$( '#emailId ~ span' ).removeClass( 'hidden' );
			valid = false;
		}
		/*if ( $( '#altEmailId' ).val() != "" && !validateEmail( $( '#altEmailId' ).val() ) ) {
			$( '#altEmailId ~ font' ).removeClass( 'hidden' );
			valid = false;
		}*/
		if ( $( '#mobile' ).val() != "" && !validateMobile( $( '#mobile' ).val() ) ) {
			$( '#mobile ~ font' ).removeClass( 'hidden' );
			valid = false;
		}

		if ( $( '#pan' ).val() != "" && !validatePanGirNo( $( '#pan' ).val() ) ) {
			$( '#pan ~ font' ).removeClass( 'hidden' );
			valid = false;
		}
		// else {
		// if ( valid ) {
		// valid = validatePANDetails();
		// }
		// }
		if ( $( '#fatherFirstName' ).val().trim() == "" ) {
			$( '#fatherFirstName ~ font' ).removeClass( 'hidden' );
			valid = false;
		}
		else if ( $( '#fatherFirstName' ).val().trim() != "" && $( '#fatherFirstName' ).val().length < 1 ) {
			$( '#fatherFirstName ~ span' ).removeClass( 'hidden' );
			valid = false;
		}
		/*else if ( !validAlphaNameSpaceDot( $( '#fatherFirstName' ).val() ) ) {
			$( '#fatherFirstName ~ label' ).removeClass( 'hidden' );
			valid = false;
		}*/
		else if ( !validateCustomerFirstNameForAlphaNumeric($( '#fatherFirstName' ).val()))
		{
			$( '#fatherFirstName ~ label' ).removeClass( 'hidden' );
			valid = false;
		}
		// if ( $( '#fatherLastName' ).val().trim() == "" ) {
		// $( '#fatherLastName ~ font' ).removeClass( 'hidden' );
		// valid = false;
		// }
		// if ( $( '#fatherLastName' ).val().trim() != "" && $( '#fatherLastName' ).val().length < 3 ) {
		// $( '#fatherLastName ~ span' ).removeClass( 'hidden' );
		// valid = false;
		// }
		if ( $( '#fatherLastName' ).val().trim() != "" && !validAlphaNameSpaceDot( $( '#fatherLastName' ).val() ) ) {
			$( '#fatherLastName ~ label' ).removeClass( 'hidden' );
			valid = false;
		}
		// if ( $( '#motherFirstName' ).val().trim() == "" ) {
		// $( '#motherFirstName ~ font' ).removeClass( 'hidden' );
		// valid = false;
		// }
		// else if ( $( '#motherFirstName' ).val().trim() != "" && $( '#motherFirstName' ).val().length < 3 ) {
		// $( '#motherFirstName ~ span' ).removeClass( 'hidden' );
		// valid = false;
		// }
		// else if ( !validAlphaNameSpaceDot( $( '#motherFirstName' ).val() ) ) {
		// $( '#motherFirstName ~ label' ).removeClass( 'hidden' );
		// valid = false;
		// }

		// if ( $( '#motherLastName' ).val().trim() == "" ) {
		// $( '#motherLastName ~ font' ).removeClass( 'hidden' );
		// valid = false;
		// }
		// if ( $( '#motherLastName' ).val().trim() != "" && $( '#motherLastName' ).val().length < 3 ) {
		// $( '#motherLastName ~ span' ).removeClass( 'hidden' );
		// valid = false;
		// }
		// else if ( $( '#motherLastName' ).val().trim() != "" && !validAlphaNameSpaceDot( $( '#motherLastName' ).val() ) ) {
		// $( '#motherLastName ~ label' ).removeClass( 'hidden' );
		// valid = false;
		// }

		if ( $( '#orgCoordFirstName' ).val().trim() == "" ) {
			$( '#orgCoordFirstName ~ font' ).removeClass( 'hidden' );
			valid = false;
		}
		else if ( $( '#orgCoordFirstName' ).val().trim() != "" && $( '#orgCoordFirstName' ).val().length < 1 ) {
			$( '#orgCoordFirstName ~ span' ).removeClass( 'hidden' );
			valid = false;
		}
		/*else if ( !validAlphaNameSpaceDot( $( '#orgCoordFirstName' ).val() ) ) {
			$( '#orgCoordFirstName ~ label' ).removeClass( 'hidden' );
			valid = false;
		}*/
		else if ( !validateCustomerFirstNameForAlphaNumeric($( '#orgCoordFirstName' ).val()))
		{
			$( '#orgCoordFirstName ~ label' ).removeClass( 'hidden' );
			valid = false;
		}

		// if ( $( '#orgCoordLastName' ).val().trim() == "" ) {
		// $( '#orgCoordLastName ~ font' ).removeClass( 'hidden' );
		// valid = false;
		// }
		// if ( $( '#orgCoordLastName' ).val().trim() != "" && $( '#orgCoordLastName' ).val().length < 3 ) {
		// $( '#orgCoordLastName ~ span' ).removeClass( 'hidden' );
		// valid = false;
		// }
		if ( $( '#orgCoordLastName' ).val().trim() != "" && !validAlphaNameSpaceDot( $( '#orgCoordLastName' ).val() ) ) {
			$( '#orgCoordLastName ~ label' ).removeClass( 'hidden' );
			valid = false;
		}

		if ( $( '#authorizedSignatoryFirstName' ).val().trim() == "" ) {
			$( '#authorizedSignatoryFirstName ~ font' ).removeClass( 'hidden' );
			valid = false;
		}
		else if ( $( '#authorizedSignatoryFirstName' ).val().trim() != "" && $( '#authorizedSignatoryFirstName' ).val().length < 1 ) {
			$( '#authorizedSignatoryFirstName ~ span' ).removeClass( 'hidden' );
			valid = false;
		}
		/*else if ( !validAlphaNameSpaceDot( $( '#authorizedSignatoryFirstName' ).val() ) ) {
			$( '#authorizedSignatoryFirstName ~ label' ).removeClass( 'hidden' );
			valid = false;
		}*/
		else if ( !validateCustomerFirstNameForAlphaNumeric($( '#authorizedSignatoryFirstName' ).val()))
		{
			$( '#authorizedSignatoryFirstName ~ label' ).removeClass( 'hidden' );
			valid = false;
		}
		// if ( $( '#authorizedSignatoryLastName' ).val().trim() == "" ) {
		// $( '#authorizedSignatoryLastName ~ font' ).removeClass( 'hidden' );
		// valid = false;
		// }
		// if ( $( '#authorizedSignatoryLastName' ).val().trim() != "" && $( '#authorizedSignatoryLastName' ).val().length < 3 ) {
		// $( '#authorizedSignatoryLastName ~ span' ).removeClass( 'hidden' );
		// valid = false;
		// }
		if ( $( '#authorizedSignatoryLastName' ).val().trim() != "" && !validAlphaNameSpaceDot( $( '#authorizedSignatoryLastName' ).val() ) ) {
			$( '#authorizedSignatoryLastName ~ label' ).removeClass( 'hidden' );
			valid = false;
		}
		if ( $( '#authorizedSignatoryDesignation' ).val().trim() == "" ) {
			$( '#authorizedSignatoryDesignation ~ font' ).removeClass( 'hidden' );
			valid = false;
		}
		if ( $( '#authorizedSignatoryDesignation' ).val().trim() != "" && $( '#authorizedSignatoryDesignation' ).val().length < 3 ) {
			$( '#authorizedSignatoryDesignation ~ span' ).removeClass( 'hidden' );
			valid = false;
		}

	}

	if ( $( '#remarks' ).val().trim() == "" ) {
		$( '#remarks ~ font' ).removeClass( 'hidden' ).css('display','block');
		valid = false;
	}
	if ( $( '#remarks' ).val().trim() != "" && ( $( '#remarks' ).val().trim().length < 2 || $( '#remarks' ).val().trim().length > 4000 ) ) {
		$( '#remarks ~ span' ).removeClass( 'hidden' ).css('display','block');
		valid = false;
	}

	return valid;
}

function validatePANDetails(){
	$( '#form60fullName ~ font' ).hide();
	$( '#form60transactionAmount ~ font' ).hide();
	$( '#form60transactionParticulars ~ font' ).hide();
	$( '#form60address ~ font' ).hide();
	$( '#form60taxWard ~ font' ).hide();
	$( '#form60reasonNoPnr ~ font' ).hide();
	$( '#form60supportDocument ~ font' ).hide();
	if ( $( '#pan' ).val() != '' && $( '#pan' ).val().length == 10 ) {
		$( '#Form60 font' ).hide();
		return true;
	}
	else {
		var valid = true;
		// $('#Form60 font').hide();
		if ( $( '#form60fullName' ).val() == '' ) {
			$( '#form60fullName ~ font' ).show();
			valid = false;
		}
		if ( $( '#form60transactionAmount' ).val() == "0" ) {
			$( '#form60transactionAmount' ).val( '' );
		}
		if ( $( '#form60transactionAmount' ).val() == '' ) {
			$( '#form60transactionAmount ~ font' ).show();
			valid = false;
		}
		else if ( $( '#form60transactionAmount' ).val() != '' && isNaN( $( '#form60transactionAmount' ).val() ) ) {
			$( '#form60transactionAmount ~ font' ).show();
			valid = false;
		}
		if ( $( '#form60transactionParticulars' ).val() == '' ) {
			$( '#form60transactionParticulars ~ font' ).show();
			valid = false;
		}
		if ( $( '#form60address' ).val() == '' || $( '#form60address' ).val().length > 255 ) {
			$( '#form60address ~ font' ).show();
			valid = false;
		}
		if ( $( 'input[name=documentDetailsPojo.form60TaxApplicable]:checked' ).val() == "true" ) {
			if ( $( '#form60taxWard' ).val() == '' ) {
				$( '#form60taxWard ~ font' ).show();
				valid = false;
			}
			if ( $( '#form60reasonNoPnr' ).val() == '' ) {
				$( '#form60reasonNoPnr ~ font' ).show();
				valid = false;
			}
			if ( $( '#form60supportDocument' ).val() == '' ) {
				$( '#form60supportDocument ~ font' ).show();
				valid = false;
			}
		}
		return valid;
	}
}

function validateCustomerOwnership(){
	var valid = true;
	$( '#ownership ' ).addClass( 'hidden' );
	if ( $( '#connectionTypeCRF' ).val() == "Ind" || $( '#connectionTypeCRF' ).val() == "Prop" ) {
		if ( $( '#firstName' ).val() == $( '#oldfirstName' ).val() ) {
			$( '#ownership ' ).removeClass( 'hidden' );
			valid = false;
		}
	}
	else {
		if ( $( '#companyName' ).val() == $( '#oldfirstName' ).val() ) {
			$( '#ownership ' ).removeClass( 'hidden' );
			valid = false;
		}
	}
	return valid;
}

function validateCustomerConnectionType(){
	$( '#connectionTypeCRF ~ font' ).addClass( 'hidden' );
	if ( $( '#connectionTypeCRF' ).val() == $( '#oldconnectionType' ).val() ) {

		$( '#category ' ).removeClass( 'hidden' );
		return false;
	}
	else {
		return true;
	}
}
function hideErrormsg(){
	$( '#connectionTypeCRF ~ font' ).addClass( 'hidden' );
	$( '#category ' ).addClass( 'hidden' );
}
function hideErrorforOwnership(){

	$( '#ownership ' ).addClass( 'hidden' );
}