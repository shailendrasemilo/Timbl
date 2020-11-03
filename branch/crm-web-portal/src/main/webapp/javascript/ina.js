var inaValidator;
var count = 0;
$( document )
		.ready(
				function(){
					$( '#Form60 font' ).hide();
					$( '#dateOnCRF' ).removeClass( 'gray_text' );

					if ( $( '[name="planType"]:checked' ).val() == undefined ) {
						$( '#addOnDulValueCRF' ).addClass( 'gray_text' );
						$( '#addOnDulValueCRF' ).attr( 'disabled', true );
						$( '#basePlanNameCRF' ).addClass( 'gray_text' );
						$( '#basePlanNameCRF' ).attr( 'disabled', true );
					}
					$( ".tab_content" ).hide();
					if ( $( '#rentalChargeId' ).val() != null ) {
						sum();
					}
					var k = document.getElementById( 'tabHidden' );
					if ( k != null ) {
						$( k.value ).show();
						$( '.selectedTab' ).removeClass( 'selectedTab' );
						$( k.value + 'tab' ).addClass( 'selectedTab' );
					}
					else
						$( ".tab_content" ).first().show();
					$( 'ul.i_aTabs li a' ).click( function(){
						$( ".tab_content" ).hide();
						$( $( this ).attr( "href" ) ).show();
						document.getElementById( 'tabHidden' ).value = $( this ).attr( "href" );
						/*
						 * $( '.selectedTab' ).removeClass( 'selectedTab' ); $( this ).addClass( 'selectedTab' );
						 */
						$( this ).addClass( 'active' );
						$( this ).parent().siblings().children().removeClass( 'active' );
					} );
					$( '#connectionTypeCRF' ).bind( 'change', function(){
						var connTypeVal = $( this ).val();
						viewConnectionType( connTypeVal );
					} );
					$( '#nationalityTypeId' ).bind( 'change', function(){
						var nationTypeVal = $( this ).val();
						viewNationalityDetails( nationTypeVal );
					} );
					$( '#cpeStatusId' ).bind( 'change', function(){
						var cpeStatusVal = $( this ).val();
						viewTelesolutionPayment( cpeStatusVal );
					} );
					// $('input[name="customerDetailsPojo.nationality"]')
					// .bind(
					// 'change',
					// function() {
					// viewNationalityDetails();
					// });
					// sameAsInstallation

					$( '#sameAsInstallation' ).bind( 'change', function(){
						if ( $( '#sameAsInstallation' ).is( ':checked' ) ) {
							$( '#billAddLine1' ).attr( 'value', $( 'input[name="installationAddressPojo.addLine1"]' ).val() );
							$( '#billAddLine2' ).attr( 'value', $( 'input[name="installationAddressPojo.addLine2"]' ).val() );
							$( '#billAddLine3' ).attr( 'value', $( 'input[name="installationAddressPojo.addLine3"]' ).val() );
							$( '#billingState' ).val( $( '#hiddenState' ).val() );
							if ( null != $( '#hiddenState' ).val() && $( '#hiddenState' ).val() != '' ) {
								populateCityByState( 'billingCity', $( '#hiddenState' ).val(), $( '#hiddenCity' ).val() );
							}
							$( '#billAddLandMark' ).attr( 'value', $( 'input[name="installationAddressPojo.landmark"]' ).val() );
							$( '#pinCodeBillingCRF' ).attr( 'value', $( 'input[name="installationAddressPojo.pincode"]' ).val() );
							// $('input[name="billingAddressPojo.contactNo"]').attr('value', $('input[name="installationAddressPojo.contactNo"]').val());
						}
					} );

					$( 'input[name="installationAddressPojo.addLine1"]' ).keyup( function(){
						if ( $( '#sameAsInstallation' ).is( ':checked' ) ) {
							$( '#billAddLine1' ).attr( 'value', $( 'input[name="installationAddressPojo.addLine1"]' ).val() );
						}
					} );
					$( 'input[name="installationAddressPojo.addLine2"]' ).keyup( function(){
						if ( $( '#sameAsInstallation' ).is( ':checked' ) ) {
							$( '#billAddLine2' ).attr( 'value', $( 'input[name="installationAddressPojo.addLine2"]' ).val() );
						}
					} );
					$( 'input[name="installationAddressPojo.addLine3"]' ).keyup( function(){
						if ( $( '#sameAsInstallation' ).is( ':checked' ) ) {
							$( '#billAddLine3' ).attr( 'value', $( 'input[name="installationAddressPojo.addLine3"]' ).val() );
						}
					} );
					$( 'input[name="installationAddressPojo.landmark"]' ).keyup( function(){
						if ( $( '#sameAsInstallation' ).is( ':checked' ) ) {
							$( '#billAddLandMark' ).attr( 'value', $( 'input[name="installationAddressPojo.landmark"]' ).val() );
						}
					} );
					$( 'input[name="installationAddressPojo.pincode"]' ).keyup( function(){
						if ( $( '#sameAsInstallation' ).is( ':checked' ) ) {
							$( '#pinCodeBillingCRF' ).attr( 'value', $( 'input[name="installationAddressPojo.pincode"]' ).val() );
						}
					} );

					$( '#paymentMode' ).bind( 'change', function(){
						var paymentModeVal = $( this ).val();
						viewPaymentMode( paymentModeVal );
					} );

					$( '#paymentModeCPE' ).bind( 'change', function(){
						var paymentModeCPEVal = $( this ).val();
						viewPaymentmodeCPE( paymentModeCPEVal );
					} );

					$( '#paymentModeSecurity' ).bind( 'change', function(){
						var paymentModeSecurity = $( this ).val();
						viewPaymentmodeSecurity( paymentModeSecurity );
					} );

					// / wifi receivers

					$( 'input[name="orderDetailsPojo.receiverRequired"]' ).bind( 'change', function(){
						viewReceiverRequired();
					} );

					// page wise All Reset
					$( '#customerBasicInfo_Reset' ).click( function(){
						$( '#resetCustomerDetails' ).click();
						$( '#resetCustomerPersonalDetails' ).click();
						$( '#resetPassportDetails' ).click();
						$( '#resetInstallationAddress' ).click();
						$( '#resetBillingAddress' ).click();
					} );
					$( '#orderPaymentDetails_Reset' ).click( function(){
						$( '#resetPlanDetails' ).click();
						$( '#resetBillingPreferences' ).click();
						$( '#resetPaymentInformation' ).click();
						$( '#resetCPECharges' ).click();
						$( '#resetSecurityDeposit' ).click();
					} );
					$( '#documentDetails_Reset' ).click( function(){
						$( '#resetDocumentsDetails' ).click();
						$( '#resetForm60' ).click();
					} );
					$( '#additionalInformation_Reset' ).click( function(){
						$( '#resetVehicleDetails' ).click();
						$( '#resetMobileDetails' ).click();
						$( '#resetCreditCardDetails' ).click();
						$( '#resetBankDetails' ).click();
						$( '#resetNetworkDetails' ).click();
						$( '#resetOthersDetails' ).click();
					} );
					// $("input[type=text], textarea")
					$( '#resetCustomerDetails' ).click( function(){
						$( '#customerDetails input[type=text],#customerDetails  textarea' ).val( "" );
						$( '#customerDetails select' ).attr( 'value', '0' ).attr( 'selected', true );
						$( '#NAforIndividualProprietorship' ).addClass( 'hide1' );
						$( '#AforIndividual' ).addClass( 'hide1' );
					} );

					$( '#resetCustomerPersonalDetails' ).click( function(){
						$( '#customerPersonalDetails input[type=text],#customerPersonalDetails  textarea' ).val( "" );
						$( '#customerPersonalDetails input[type=radio]' ).parent().removeClass( "radio_on" );
						$( '#customerPersonalDetails input[type=radio], #customerPersonalDetails input[type=checkbox]' ).attr( 'checked', false );
						$( '#customerPersonalDetails input[type=checkbox]' ).parent().removeClass( "check_on" );
						$( '#customerPersonalDetails select' ).attr( 'value', '0' ).attr( 'selected', true );
						$( '#customerPersonalDetails input[type=text],textarea' ).next( 'font' ).hide();
						$( '#OtherNationalityDetails' ).addClass( 'hide1' );
						$( '#OtherNationalityDetails input[type=text],#OtherNationalityDetails textarea' ).val( "" );
						$( '#OtherNationalityDetails input[type=text],#OtherNationalityDetails textarea' ).next( 'font' ).hide();
						$( '#nationalityTypeId' ).attr( 'value', 'Indian' ).attr( 'selected', true );
					} );

					$( '#resetPassportDetails' ).click( function(){
						$( '#OtherNationalityDetails input[type=text],#OtherNationalityDetails textarea' ).val( "" );
						$( '#OtherNationalityDetails input[type=radio]' ).parent().removeClass( "radio_on" );
						$( '#OtherNationalityDetails input[type=radio], #OtherNationalityDetails  input[type=checkbox]' ).attr( 'checked', false );
						$( '#OtherNationalityDetails input[type=checkbox]' ).parent().removeClass( "check_on" );
						$( '#OtherNationalityDetails select' ).attr( 'value', '0' ).attr( 'selected', true );
						$( '#OtherNationalityDetails input[type=text],#OtherNationalityDetails textarea' ).next( 'font' ).hide();
					} );

					$( '#resetInstallationAddress' ).click( function(){
						$( '#installationAddress input[type=text],#installationAddress textarea' ).val( "" );
						$( 'input[name="state"]' ).val( "" );
						$( 'input[name="city"]' ).val( "" );
						$( '#installationAddress input[type=radio]' ).parent().removeClass( "radio_on" );
						$( '#installationAddress input[type=radio], #installationAddress input[type=checkbox]' ).attr( 'checked', false );
						$( '#installationAddress input[type=checkbox]' ).parent().removeClass( "check_on" );
						$( '#installationAddress select' ).attr( 'value', '0' ).attr( 'selected', true );
						$( '#installationAddress input[type=text],#installationAddress  textarea' ).next( 'font' ).hide();
						$( '#installationAddress input[type=text],#installationAddress  textarea' ).next( 'label' ).next( 'font' ).hide();
					} );

					$( '#resetBillingAddress' ).click( function(){
						$( '#billingAddress input[type=text],#billingAddress textarea' ).val( "" );
						$( '#billingAddress input[type=radio]' ).parent().removeClass( "radio_on" );
						$( '#billingAddress input[type=radio],#billingAddress input[type=checkbox]' ).attr( 'checked', false );
						$( '#billingAddress input[type=checkbox]' ).parent().removeClass( "check_on" );
						$( '#billingAddress select' ).attr( 'value', '0' ).attr( 'selected', true );
						$( '#billingAddress input[type=text], #billingAddress textarea' ).next( 'font' ).hide();
					} );

					$( '#resetPlanDetails' ).click( function(){
						$( '#planDetails input[type=radio]' ).parent().removeClass( "radio_on" );
						$( '#planDetails input[type=radio],#planDetails input[type=checkbox]' ).attr( 'checked', false );
						$( '#planDetails input[type=checkbox]' ).parent().removeClass( "check_on" );
						$( '#planDetails input[type=text],#planDetails textarea' ).val( "" );
						$( '#planDetails select' ).attr( 'value', '0' ).attr( 'selected', true );
						$( '#resetPaymentInformation' ).click();
						$( '#resetCPECharges' ).click();
					} );

					$( '#resetBillingPreferences' ).click( function(){
						$( '#billingPreferences input[type=radio]' ).parent().removeClass( "radio_on" );
						$( '#billingPreferences input[type=radio], #billingPreferences input[type=checkbox]' ).attr( 'checked', false );
						$( '#billingPreferences input[type=checkbox]' ).parent().removeClass( "check_on" );
						$( '#billingPreferences input[type=text],#billingPreferences textarea' ).val( "" );
						$( '#billingPreferences select' ).attr( 'value', '0' ).attr( 'selected', true );
					} );

					$( '#resetPaymentInformation' ).click( function(){
						$( '#paymentInformation input[type=radio]' ).parent().removeClass( "radio_on" );
						$( '#paymentInformation input[type=radio], #paymentInformation input[type=checkbox]' ).attr( 'checked', false );
						$( '#paymentInformation input[type=checkbox]' ).parent().removeClass( "check_on" );
						$( '#paymentInformation input[type=text],#paymentInformation textarea' ).val( "" );
						$( '#paymentInformation select' ).attr( 'value', '' ).attr( 'selected', true );
						$( '#cash' ).addClass( 'hide1' );
						$( '#onlinePayment' ).addClass( 'hide1' );
						$( '#cheque' ).addClass( 'hide1' );
					} );

					$( '#resetCPECharges' ).click( function(){
						$( '#CPECharges input[type=radio]' ).parent().removeClass( "radio_on" );
						$( '#CPECharges input[type=radio], #CPECharges input[type=checkbox]' ).attr( 'checked', false );
						$( '#CPECharges input[type=checkbox]' ).parent().removeClass( "check_on" );
						$( '#CPECharges input[type=text],#CPECharges textarea' ).val( "" );
						$( '#CPECharges select' ).attr( 'value', '' ).attr( 'selected', true );
						$( '#cashCPE' ).addClass( 'hide1' );
						$( '#onlinePaymentCPE' ).addClass( 'hide1' );
						$( '#chequeCPE' ).addClass( 'hide1' );
					} );

					$( '#resetSecurityDeposit' ).click( function(){
						$( '#securityDeposit input[type=radio]' ).parent().removeClass( "radio_on" );
						$( '#securityDeposit input[type=radio], #securityDeposit input[type=checkbox]' ).attr( 'checked', false );
						$( '#securityDeposit input[type=checkbox]' ).parent().removeClass( "check_on" );
						$( '#securityDeposit input[type=text],#securityDeposit textarea' ).val( "" );
						$( '#securityDeposit select' ).attr( 'value', '' ).attr( 'selected', true );
						$( '#cashSecurity' ).addClass( 'hide1' );
						$( '#onlinePaymentSecurity' ).addClass( 'hide1' );
						$( '#chequeSecurity' ).addClass( 'hide1' );
					} );

					$( '#resetDocumentsDetails' ).click( function(){
						$( '#documentsDetails input[type=radio]' ).parent().removeClass( "radio_on" );
						$( '#documentsDetails input[type=radio],#documentsDetails input[type=checkbox]' ).attr( 'checked', false );
						$( '#documentsDetails input[type=checkbox]' ).parent().removeClass( "check_on" );
						$( '#documentsDetails input[type=text],#documentsDetails textarea' ).val( "" );
						$( '#documentsDetails select' ).attr( 'value', '' ).attr( 'selected', true );
					} );

					$( '#resetForm60' ).click( function(){
						$( '#Form60 input[type=radio]' ).parent().removeClass( "radio_on" );
						$( '#Form60 input[type=radio],#Form60 input[type=checkbox]' ).attr( 'checked', false );
						$( '#Form60 input[type=checkbox]' ).parent().removeClass( "check_on" );
						$( '#Form60 input[type=text],#Form60  textarea' ).val( "" );
						$( '#Form60 select' ).attr( 'value', '0' ).attr( 'selected', true );
						$( '#assessedToTaxShow' ).hide();
					} );

					$( '#resetVehicleDetails' ).click( function(){
						$( '#vehicleDetails input[type=radio]' ).parent().removeClass( "radio_on" );
						$( '#vehicleDetails input[type=radio],#vehicleDetails input[type=checkbox]' ).attr( 'checked', false );
						$( '#vehicleDetails input[type=checkbox]' ).parent().removeClass( "check_on" );
						$( '#vehicleDetails input[type=text],#vehicleDetails textarea' ).val( "" );
						$( '#vehicleDetails select' ).attr( 'value', '' ).attr( 'selected', true );
					} );

					$( '#resetMobileDetails' ).click( function(){
						$( '#mobileDetails input[type=radio]' ).parent().removeClass( "radio_on" );
						$( '#mobileDetails input[type=radio], #mobileDetails input[type=checkbox]' ).attr( 'checked', false );
						$( '#mobileDetails input[type=checkbox]' ).parent().removeClass( "check_on" );
						$( '#mobileDetails input[type=text],#mobileDetails textarea' ).val( "" );
						$( '#mobileDetails select' ).attr( 'value', '' ).attr( 'selected', true );
					} );

					$( '#resetCreditCardDetails' ).click( function(){
						$( '#creditCardDetails input[type=radio]' ).parent().removeClass( "radio_on" );
						$( '#creditCardDetails input[type=radio],#creditCardDetails input[type=checkbox]' ).attr( 'checked', false );
						$( '#creditCardDetails input[type=checkbox]' ).parent().removeClass( "check_on" );
						$( '#creditCardDetails input[type=text]' ).val( "" );
						$( '#creditCardDetails select' ).attr( 'value', '' ).attr( 'selected', true );
						$( '#creditCardDetails input[type=text]' ).next( 'font' ).hide();
						$( '#creditCardDetails select' ).parent().next( 'font' ).hide();
					} );

					$( '#resetBankDetails' ).click( function(){
						$( '#bankDetails input[type=radio]' ).parent().removeClass( "radio_on" );
						$( '#bankDetails input[type=radio], #bankDetails input[type=checkbox]' ).attr( 'checked', false );
						$( '#bankDetails input[type=checkbox]' ).parent().removeClass( "check_on" );
						$( '#bankDetails input[type=text],#bankDetails textarea' ).val( "" );
						$( '#bankDetails select' ).attr( 'value', '' ).attr( 'selected', true );
					} );

					$( '#resetNetworkDetails' ).click( function(){
						$( '#networkDetails input[type=radio]' ).parent().removeClass( "radio_on" );
						$( '#networkDetails input[type=radio], #networkDetails input[type=checkbox]' ).attr( 'checked', false );
						$( '#networkDetails input[type=checkbox]' ).parent().removeClass( "check_on" );
						$( '#networkDetails input[type=text],#networkDetails textarea' ).val( "" );
						$( '#networkDetails select' ).attr( 'value', '0' ).attr( 'selected', true );
					} );

					$( '#resetOthersDetails' ).click( function(){
						$( '#othersDetails select' ).attr( 'value', '0' );
					} );

					$( "#emailIdCRF" ).validate(
							{
								rules : {
									'customerDetailsPojo.custEmailId' : {
										required : true, email : true
									}
								},
								messages : {

									'customerDetailsPojo.custEmailId' : {
										required : "<font class='errorCreateUser'> Please provide 'Email ID'</font>",
										email : "<font class='errorTextbox'> Please provide valid 'Email ID'</font>"
									}
								}
							} );

					/** --- email allowed only--- * */
					$( '#emailIdCRF' ).keyup( function(){
						$( '#emailIdCRF' ).valid()
						/*
						 * email_address = $( this ); if ( email_address.val().length > 0 ) { if ( !validateEmail( email_address.val() ) ) { $( this ).next( 'font' ).show().html( "Please provide valid 'Email ID'" ).addClass( 'errorTextbox' ); } else $( this ).next( 'font' ).hide(); } else { $( this
						 * ).next( 'font' ).show().html( "Please provide 'Email ID'" ).addClass( 'errorTextbox' ); }
						 */

					} );
					$( '#altEmailIdCRF' ).keyup( function(){
						email_address = $( this );
						if ( email_address.val() != "" && !validateEmail( email_address.val() ) ) {
							$( this ).next( 'font' ).show().html( "Please provide valid 'Email ID'" ).addClass( 'errorTextbox' );
						}
						else
							$( this ).next( 'font' ).hide();
					} );

					/** ****** empty ***************** */
					$( '#passportValidUpToCRF, #visaValidUpToCRF, #dateOnCRF' ).keyup( function(){
						empty = $( this );
						if ( !empty.val() ) {
							$( this ).next( 'font' ).show().html( ' Please provide valid date' ).addClass( 'errorTextbox' );
						}
						else
							$( this ).next( 'font' ).hide();
					} );

					/** --- Number, digit allowed --- * */
					$( '#passportNoCRF' ).keyup( function(){
						value = $( this );
						if ( !validatePassport( value.val() ) ) {
							$( this ).next( 'font' ).show().html( 'Please provide passport No' ).addClass( 'errorTextbox' );
						}
						else
							$( this ).next( 'font' ).hide();
					} );

					/**
					 * ********** Save customer Basic Information ****************
					 */
					/** **** validation************ */

					$.validator.addMethod( 'customerDetailsPojo.connectionType', function( value ){
						return ( value != '0' );
					} );

					/*
					 * $.validator.addMethod( 'installationAddressPojo.landmark', function( value, element ){ return this.optional( element ) || validateAlphanumericSpace( value ); } ); $.validator.addMethod( 'billingAddressPojo.landmark', function( value, element ){ return this.optional( element ) ||
					 * validateAlphanumericSpace( value ); } );
					 */
					$.validator.addMethod( 'customerDetailsPojo.custPanGirNo', function( value, element ){
						return this.optional( element ) || validatePanGirNo( value );
					} );
					$.validator.addMethod( 'teleservicesPayment.bankBranch', function( value, element ){
						return this.optional( element ) || validateTextRegex_2_45( value );
					} );
					$.validator.addMethod( 'telecommunicationPayment.bankBranch', function( value, element ){
						return this.optional( element ) || validateTextRegex_2_45( value );
					} );
					$.validator.addMethod( 'securityDepositPayment.bankBranch', function( value, element ){
						return this.optional( element ) || validateTextRegex_2_45( value );
					} );
					$.validator.addMethod( 'additionalDetailsPojo.mobileMake', function( value, element ){
						return this.optional( element ) || validateTextRegex_2_45( value );
					} );
					$.validator.addMethod( 'additionalDetailsPojo.mobileNo', function( value, element ){
						return this.optional( element ) || validateAlphanumericSpace( value );
					} );
					$.validator.addMethod( 'additionalDetailsPojo.bankBranch', function( value, element ){
						return this.optional( element ) || validateTextRegex_2_45( value );
					} );

					$.validator.addMethod( 'alphaWithSpace', function( value, element ){
						return this.optional( element ) || validateAlphanumericNameCRF( value );
					}, function( params, element ){
						var connType = $( '#connectionTypeCRF' ).val();
						var min, max;
						if ( connType != 'Ind' && connType != 'Prop' ) {
							min = 3;
							max = 200;
							$( '#companyNameCRF' ).next( 'font' ).hide();
							return "<font class='errorCreateUser'> Please provide valid name [" + min + "-" + max + "]</font>";
						}
						else {
							min = 1;
							max = 50;
							$( '#firstNameCRF' ).next( 'font' ).hide();
							return "<font class='errorCreateUser'> Please provide valid name [" + min + "-" + max + "]</font>";
						}
						// return "<font class='errorCreateUser'> Please provide valid name [" + min + "-" + max + "]</font>";
					} );
					$.validator.addMethod( 'telesolutionPaymentMode', function( value, element ){
						return validateTelesolutionPaymentMode( value );
					} );
					$.validator.addMethod( 'customerDetailsPojo.aadharNo', function( value, element ){
						return this.optional( element ) || validateAadharNo( value );
					} );

					inaValidator = $( "#createInA" )
							.validate(
									{
										// debug:true,
										rules : {
											// Customer Details
											'customerDetailsPojo.displayCrfDate' : {
												required : true
											}, 'customerDetailsPojo.connectionType' : {
												dropDown : true
											},
											// Customer Personal Details
											'customerDetailsPojo.custFname' : {
												required : true, alphaWithSpace : true, maxlength : 200, // minlength : 3
											},
											/*
											 * 'customerDetailsPojo.custMname' : { required : true, alphaName : true, maxlength : 50, minlength:3 },
											 */
											'customerDetailsPojo.custLname' : {
												alphaWithSpace : true, maxlength : 50, // minlength : 3
											}, 'customerDetailsPojo.custEmailId' : {
												required : true, email : true
											}, 'customerDetailsPojo.custMobileNo' : {
												// required : true,
												'customerDetailsPojo.custMobileNo' : true
											}, 'customerDetailsPojo.rmn' : {
												required : true, 'customerDetailsPojo.rmn' : true
											},/*
												 * 'customerDetailsPojo.rtn' : { required : true, digits : true, minlength : 10 },
												 */'customerDetailsPojo.custPanGirNo' : {
												// required : true,
												'customerDetailsPojo.custPanGirNo' : true
											},
											// Applicable for all except
											// Individual and Proprietorship
											'customerDetailsPojo.orgCordFname' : {
												required : true, customerFirstName : true, maxlength : 45, minlength : 1
											}, 'customerDetailsPojo.orgCordLname' : {
												alphaNameSpaceDot : true, maxlength : 45, // minlength : 3
											}, 'customerDetailsPojo.authSignFname' : {
												required : true, customerFirstName : true, maxlength : 45, minlength : 1
											}, 'customerDetailsPojo.authSignLname' : {
												alphaNameSpaceDot : true, maxlength : 45,// minlength : 3
											}, 'customerDetailsPojo.authSignDesg' : {
												required : true, alphaNameSpaceDot : true, maxlength : 45
											},
											// Applicable for Individual
											'customerDetailsPojo.fhFname' : {
												required : true, customerFirstName : true, maxlength : 45, minlength : 1,
											}, 'customerDetailsPojo.fhLname' : {
												alphaNameSpaceDot : true, maxlength : 45,// minlength : 3
											}, 'customerDetailsPojo.custGender' : {
												required : true
											}, 'customerDetailsPojo.displayCustDob' : {
												required : true
											}, 'customerDetailsPojo.nationality' : {
												dropDown : true
											}, 'customerDetailsPojo.aadharNo' : {
												'customerDetailsPojo.aadharNo' : true
											},
											// Passport Details
											'nationalityDetailsPojo.passportNo' : {
												required : true
											}, 'nationalityDetailsPojo.displayPassportValidity' : {
												required : true
											}, 'nationalityDetailsPojo.visaType' : {
												dropDown : true
											}, 'nationalityDetailsPojo.displayVisaValidity' : {
												required : true
											}, 'nationalityDetailsPojo.localCpFname' : {
												required : true, customerFirstName : true, maxlength : 45, minlength : 1
											}, 'nationalityDetailsPojo.localCpLname' : {
												alphaNameSpaceDot : true, maxlength : 45,// minlength : 3
											}, 'nationalityDetailsPojo.localCpMobileNo' : {
												'nationalityDetailsPojo.localCpMobileNo' : true, required : true
											}, 'nationalityDetailsPojo.addLine1' : {
												required : true, maxlength : 60
											}, 'nationalityDetailsPojo.addLine2' : {
												required : true, maxlength : 60
											}, 'nationalityDetailsPojo.addLine3' : {
												required : true, maxlength : 60
											}, 'nationalityDetailsPojo.state' : {
												dropDown : true
											}, 'nationalityDetailsPojo.city' : {
												dropDown : true
											}, 'nationalityDetailsPojo.pincode' : {
												required : true, digits : true, minlength : 6
											}, 'nationalityDetailsPojo.nationality' : {
												required : true, maxlength : 50
											},
											// Installation Address
											'installationAddressPojo.pincode' : {
												required : true, digits : true, minlength : 6
											}, /*
												 * 'installationAddressPojo.landmark' : { 'installationAddressPojo.landmark' : true, required : true, maxlength : 45 },
												 */'installationAddressPojo.addLine1' : {
												required : true, maxlength : 60
											}, 'installationAddressPojo.addLine2' : {
												required : true, maxlength : 60
											}, 'installationAddressPojo.addLine3' : {
												required : true, maxlength : 60
											}, 'installationAddressPojo.propertyDetails' : {
												required : true
											}, 'installationAddressPojo.contactNo' : {
												required : true, digits : true, minlength : 10
											},
											// Billing Address
											'billingAddressPojo.pincode' : {
												required : true, digits : true, minlength : 6
											}, /*
												 * 'billingAddressPojo.landmark' : { 'billingAddressPojo.landmark' : true, required : true, maxlength : 45 },
												 */'billingAddressPojo.addLine1' : {
												required : true, maxlength : 60
											}, 'billingAddressPojo.addLine2' : {
												required : true, maxlength : 60
											}, 'billingAddressPojo.addLine3' : {
												required : true, maxlength : 60
											}, 'billingAddressPojo.contactNo' : {
												required : true, digits : true, minlength : 10
											}, 'billingAddressPojo.stateName' : {
												dropDown : true
											}, 'billingAddressPojo.cityName' : {
												dropDown : true
											},
											// Plan Details
											'planDetailsPojo.basePlanName' : {
												dropDown : true
											}, 'planDetailsPojo.addOnPlanName' : {
												dropDown : true
											},
											// Billing Preferences
											'planDetailsPojo.billMode' : {
												dropDown : true
											},
											// Type of Application
											'planDetailsPojo.usageType' : {
												dropDown : true
											},

											/** ******payment module********** */
											'teleservicesPayment.registrationCharges' : {
												required : false, paidAmount : true
											}, 'teleservicesPayment.activationCharges' : {
												required : true, paidAmount : true
											}, 'teleservicesPayment.securityCharges' : {
												// required : true,
												number : true
											}, 'teleservicesPayment.otherCharges' : {
												required : true, digits : true
											},
											// 'teleservicesPayment.paymentMode' : {
											// dropDown : true
											// },
											// cash
											/*'teleservicesPayment.receiptNo' : {
												required : true, alphabetOrNumber : true
											},*/ 'teleservicesPayment.transactionId' : {
												required : true, alphabetOrNumber : true, maxlength : 45
											},
											// cheque

											'teleservicesPayment.bankName' : {
												dropDown : true
											}, 'teleservicesPayment.chequeDdNo' : {
												required : true, maxlength : 6, digits : true
											}, 'teleservicesPayment.bankBranch' : {
												// required : true,
												'teleservicesPayment.bankBranch' : true, maxlength : 45
											}, 'teleservicesPayment.displayChequeDate' : {
												required : true
											}, 'telecommunicationPayment.paymentMode' : {
												telesolutionPaymentMode : true
											},
											// cash
											/*'telecommunicationPayment.receiptNo' : {
												required : true, alphabetOrNumber : true
											},*/
											// onlinePayment
											'telecommunicationPayment.transactionId' : {
												required : true, alphabetOrNumber : true, maxlength : 45
											}, 'telecommunicationPayment.chequeDdNo' : {
												required : true, maxlength : 6, digits : true
											}, 'telecommunicationPayment.bankName' : {
												dropDown : true
											}, 'telecommunicationPayment.bankBranch' : {
												// required : true,
												'telecommunicationPayment.bankBranch' : true, maxlength : 45
											}, 'telecommunicationPayment.displayChequeDate' : {
												required : true
											}, 'securityDepositPayment.bankBranch' : {
												'securityDepositPayment.bankBranch' : true, maxlength : 45
											},
											/** ******payment module********** */
											// Documents Details
											documents : {
												required : true
											}, addressProofs : {
												required : true
											}, 'additionalDetailsPojo.mobileImeiNo' : {
												digits : true, maxlength : 15
											}, 'additionalDetailsPojo.mobileMake' : {
												'additionalDetailsPojo.mobileMake' : true,
											}, 'additionalDetailsPojo.mobileNo' : {
												'additionalDetailsPojo.mobileNo' : true, maxlength : 45
											}, 'additionalDetailsPojo.bankBranch' : {
												'additionalDetailsPojo.bankBranch' : true, maxlength : 45
											}, 'additionalDetailsPojo.bankAccountNo' : {
												digits : true
											}, 'customerDetailsPojo.businessPartner' : {
												dropDown : true
											}, 'customerDetailsPojo.salesRepresentative' : {
												dropDown : true
											}, 'planDetailsPojo.basePlanCode' : {
												dropDown : true
											}, 'planType' : {
												required : true
											}
										},
										messages : {
											// Customer Details
											'customerDetailsPojo.displayCrfDate' : {
												required : "<font class='errorCreateUser'> Please select 'CAF Date' </font>"
											},
											'customerDetailsPojo.connectionType' : {
												dropDown : "<font class='errorTextbox'>Please select 'Connection Type'</font>"
											},
											// Customer Personal Details
											'customerDetailsPojo.custFname' : {
												required : "<font class='errorCreateUser'> Please provide 'First Name'</font>",
												// alphaWithSpace : "<font class='errorCreateUser'> Please provide alphabets without<br /> special characters</font>",
												maxlength : "<font class='errorTextbox'> Maximum 200 characters allowed.</font>",
											// minlength : "<font class='errorTextbox'> Please provide more than 3 characters.</font>"
											},
											/*
											 * 'customerDetailsPojo.custMname' : { required : "<font class='errorCreateUser'> Please enter 'Middle Name'</font>", alphaName : "<font class='errorCreateUser'> Please enter alphabets without space</font>", maxlength : "<font class='errorTextbox'>
											 * Please enter no more than 50 characters</font>", minlength:"<font class='errorTextbox'> Please enter more than 3 characters</font>" },
											 */
											'customerDetailsPojo.custLname' : {
												// required : "<font class='errorCreateUser'> Please provide 'Last Name'</font>",
												// alphaNameSpaceDot : "<font class='errorCreateUser'> Please provide alphabets</font>",
												// minlength : "<font class='errorTextbox'> Please provide more than 3 characters</font>",
												maxlength : "<font class='errorTextbox'> Maximum 50 characters allowed.</font>"

											},
											'customerDetailsPojo.custEmailId' : {
												required : "<font class='errorCreateUser'> Please provide 'Email ID'</font>",
												email : "<font class='errorTextbox'> Please provide valid 'Email ID'</font>"
											},
											'customerDetailsPojo.custMobileNo' : {
												// required : "<font class='errorCreateUser'> Please enter 'Mobile No'</font>",
												'customerDetailsPojo.custMobileNo' : "<font class='errorTextbox'>'Mobile Number' " + getMobileMesg()
														+ "</font>"
											},
											'customerDetailsPojo.rmn' : {
												required : "<font class='errorCreateUser'> Please provide 'Registered Mobile No.'</font>",
												'customerDetailsPojo.rmn' : "<font class='errorTextbox'>'Mobile Number' " + getMobileMesg()
														+ "</font>"
											},
											/*
											 * 'customerDetailsPojo.rtn' : { required : "<font class='errorCreateUser'> Please provide 'Registered Telephone No.'</font>", digits : "<font class='errorCreateUser'> Please provide only digit</font>", minlength : "<font class='errorTextbox'> Please
											 * provide at least 10 character allowed.</font>" },
											 */
											'customerDetailsPojo.custPanGirNo' : {
												// required : "<font class='errorCreateUser'> Please enter 'Pan/Gir No'</font>",
												'customerDetailsPojo.custPanGirNo' : "<font class='errorTextbox'> Please provide correct 10 characters 'Pan/Gir No'</font>"
											},
											// Applicable for all except
											// Individual and Proprietorship
											'customerDetailsPojo.orgCordFname' : {
												required : "<font class='errorCreateUser'> Please provide 'Org Cord FName' </font>",
												customerFirstName : "<font class='errorCreateUser'> Please provide alphabets</font>",
												minlength : "<font class='errorTextbox'> Please provide more than 1 characters</font>",
												maxlength : "<font class='errorTextbox'> Maximum 45 characters allowed.</font>"
											},
											'customerDetailsPojo.orgCordLname' : {
												alphaNameSpaceDot : "<font class='errorCreateUser'> Please provide alphabets </font>",
												// minlength : "<font class='errorTextbox'> Please provide more than 3 characters</font>",
												maxlength : "<font class='errorTextbox'> Maximum 45 characters allowed.</font>"
											},
											'customerDetailsPojo.authSignFname' : {
												required : "<font class='errorCreateUser'> Please provide 'Auth Sign FName'</font>",
												customerFirstName : "<font class='errorCreateUser'> Please provide alphabets </font>",
												minlength : "<font class='errorTextbox'> Please provide more than 1 characters</font>",
												maxlength : "<font class='errorTextbox'> Maximum 45 characters allowed.</font>"
											},
											'customerDetailsPojo.authSignLname' : {
												alphaNameSpaceDot : "<font class='errorCreateUser'> Please provide alphabets </font>",
												// minlength : "<font class='errorTextbox'> Please provide more than 3 characters</font>",
												maxlength : "<font class='errorTextbox'> Maximum 45 characters allowed.</font>"
											},
											'customerDetailsPojo.authSignDesg' : {
												required : "<font class='errorCreateUser'> Please provide 'Auth Sign Designation'</font>",
												alphaNameSpaceDot : "<font class='errorCreateUser'> Please provide alphabets </font>",
												maxlength : "<font class='errorTextbox'> Maximum 45 characters allowed.</font>"
											},
											// Applicable for Individual
											'customerDetailsPojo.fhFname' : {
												required : "<font class='errorCreateUser'> Please provide 'Father First Name'</font>",
												customerFirstName : "<font class='errorCreateUser'> Please provide only alphabets</font>",
												minlength : "<font class='errorTextbox'> Please provide more than 1 characters</font>",
												maxlength : "<font class='errorTextbox'> Maximum 45 characters allowed.</font>"
											},
											'customerDetailsPojo.fhLname' : {
												alphaNameSpaceDot : "<font class='errorCreateUser'> Please provide alphabets without space</font>",
												// minlength : "<font class='errorTextbox'> Please provide more than 3 characters</font>",
												maxlength : "<font class='errorTextbox'> Maximum 45 characters allowed.</font>"
											},
											'customerDetailsPojo.custGender' : {
												required : "<font class='errorCreateUser'> Please select 'Gender'</font>"
											},
											'customerDetailsPojo.displayCustDob' : {
												required : "<font class='errorCreateUser'> Please select 'Date Of Birth'</font>"
											},
											'customerDetailsPojo.nationality' : {
												dropDown : "<font class='errorTextbox'> Please provide 'Nationality'</font>"
											},
											'customerDetailsPojo.aadharNo' : {
												'customerDetailsPojo.aadharNo' : "<font class='errorTextbox'> 'Aadhar No.' should be 12 digit long.</font>"
											},
											// Passport Details
											'nationalityDetailsPojo.passportNo' : {
												required : "<font class='errorCreateUser'> Please provide 'Passport No'</font>"
											},
											'nationalityDetailsPojo.displayPassportValidity' : {
												required : "<font class='errorTextbox'> Please provide 'Passport Validity'</font>"
											},
											'nationalityDetailsPojo.visaType' : {
												dropDown : "<font class='errorCreateUser'> Please select 'Visa Type'</font>"
											},
											'nationalityDetailsPojo.displayVisaValidity' : {
												required : "<font class='errorTextbox'> Please select 'Visa Validity'</font>"
											},
											'nationalityDetailsPojo.localCpFname' : {
												required : "<font class='errorCreateUser'> Please provide 'CP First Name'</font>",
												customerFirstName : "<font class='errorCreateUser'> Please provide alphabets without space</font>",
												minlength : "<font class='errorTextbox'> Please provide more than 1 characters</font>",
												maxlength : "<font class='errorTextbox'> Maximum 45 characters allowed.</font>"
											},
											'nationalityDetailsPojo.localCpLname' : {
												alphaNameSpaceDot : "<font class='errorTextbox'> Please provide alphabets without space</font>",
												// minlength : "<font class='errorTextbox'> Please provide more than 3 characters</font>",
												maxlength : "<font class='errorTextbox'> Maximum 45 characters allowed.</font>"
											},
											'nationalityDetailsPojo.localCpMobileNo' : {
												required : "<font class='errorCreateUser'> Please provide 'CP Mobile No'</font>",
												'nationalityDetailsPojo.localCpMobileNo' : "<font class='errorTextbox'>'Mobile Number' "
														+ getMobileMesg() + "</font>"
											},
											'nationalityDetailsPojo.addLine1' : {
												required : "<font class='errorCreateUser'> Please provide 'Address1'</font>",
												maxlength : "<font class='errorTextbox'> Maximum 60 characters allowed.</font>"
											},
											'nationalityDetailsPojo.addLine2' : {
												required : "<font class='errorCreateUser'> Please provide 'Address2'</font>",
												maxlength : "<font class='errorTextbox'> Maximum 60 characters allowed.</font>"
											},
											'nationalityDetailsPojo.addLine3' : {
												required : "<font class='errorCreateUser'> Please provide 'Address3'</font>",
												maxlength : "<font class='errorTextbox'> Maximum 60 characters allowed.</font>"
											},
											'nationalityDetailsPojo.state' : {
												dropDown : "<font class='errorTextbox'>Please select 'state'</font>"
											},
											'nationalityDetailsPojo.city' : {
												dropDown : "<font class='errorTextbox'>Please select 'city'</font>"
											},
											'nationalityDetailsPojo.pincode' : {
												required : "<font class='errorCreateUser'> Please provide 'local CP Pin Code'</font>",
												digits : "<font class='errorCreateUser'> Please provide only digit</font>",
												minlength : "<font class='errorCreateUser'> Please provide 6 digit 'Pin Code'</font>"
											},
											'nationalityDetailsPojo.nationality' : {
												required : "<font class='errorCreateUser'> Please provide 'Other Nationality'</font>",
												maxlength : "<font class='errorTextbox'> Maximum 64 characters allowed.</font>"
											},
											// Installation Address
											'installationAddressPojo.pincode' : {
												required : "<font class='errorCreateUser'> Please provide 'Pin Code'</font>",
												digits : "<font class='errorCreateUser'> Please provide only digit</font>",
												minlength : "<font class='errorCreateUser'> Please provide 6 digit 'Pin Code'</font>"
											},
											// 'installationAddressPojo.landmark' : {
											// 'installationAddressPojo.landmark' : "<font class='errorTextbox'> Please provide alpha-numeric value.</font>",
											// required : "<font class='errorCreateUser'> Please provide 'Landmark'</font>",
											// maxlength : "<font class='errorTextbox'> Please provide no more than 45 characters</font>"
											// },
											'installationAddressPojo.addLine1' : {
												required : "<font class='errorCreateUser'> Please provide 'Address1'</font>",
												maxlength : "<font class='errorTextbox'> Maximum 60 characters allowed.</font>"
											},
											'installationAddressPojo.addLine2' : {
												required : "<font class='errorCreateUser'> Please provide 'Address2'</font>",
												maxlength : "<font class='errorTextbox'> Maximum 60 characters allowed.</font>"
											},
											'installationAddressPojo.addLine3' : {
												required : "<font class='errorCreateUser'> Please provide 'Address3'</font>",
												maxlength : "<font class='errorTextbox'> Maximum 60 characters allowed.</font>"
											},
											'installationAddressPojo.propertyDetails' : {
												required : "<font class='errorTextbox'> Please select 'Property Details'</font>"
											},
											'installationAddressPojo.contactNo' : {
												required : "<font class='errorCreateUser'> Please provide 'Contact Number'.</font>",
												digits : "<font class='errorCreateUser'> Please provide only digit</font>",
												minlength : "<font class='errorTextbox'> Please provide at least 10 character</font>"
											},
											// billing Address

											'billingAddressPojo.pincode' : {
												required : "<font class='errorCreateUser'> Please provide 'Pin Code'</font>",
												digits : "<font class='errorCreateUser'> Please provide only digit</font>",
												minlength : "<font class='errorCreateUser'> Please provide 6 digit 'Pin Code'</font>"
											},
											// 'billingAddressPojo.landmark' : {
											// 'billingAddressPojo.landmark' : "<font class='errorTextbox'> Please provide alpha-numeric value.</font>",
											// required : "<font class='errorCreateUser'> Please provide 'Landmark'</font>",
											// maxlength : "<font class='errorTextbox'> Please provide no more than 45 characters</font>"
											// },
											'billingAddressPojo.contactNo' : {
												required : "<font class='errorCreateUser'> Please provide 'Contact Number'</font>",
												digits : "<font class='errorCreateUser'> Please provide only digit</font>",
												minlength : "<font class='errorTextbox'> Please provide at least 10 character</font>"
											},
											'billingAddressPojo.addLine1' : {
												required : "<font class='errorCreateUser'> Please provide 'Address1'</font>",
												maxlength : "<font class='errorTextbox'> Maximum 60 characters allowed.</font>"
											},
											'billingAddressPojo.addLine2' : {
												required : "<font class='errorCreateUser'> Please provide 'Address2'</font>",
												maxlength : "<font class='errorTextbox'> Maximum 60 characters allowed.</font>"
											},
											'billingAddressPojo.addLine3' : {
												required : "<font class='errorCreateUser'> Please provide 'Address3'</font>",
												maxlength : "<font class='errorTextbox'> Maximum 60 characters allowed.</font>"
											},
											'billingAddressPojo.stateName' : {
												dropDown : "<font class='errorTextbox'>Please select 'state'</font>"
											},
											'billingAddressPojo.cityName' : {
												dropDown : "<font class='errorTextbox'>Please select 'city'</font>"
											},
											// Plan Details
											'planDetailsPojo.basePlanName' : {
												dropDown : "<font class='errorCreateUserSelect'> Please select 'Base Plan Name'</font>"
											},
											'planDetailsPojo.addOnPlanName' : {
												dropDown : "<font class='errorCreateUserSelect'> Please select 'Add On Plan Name'</font>"
											},

											// Billing Preferences
											'planDetailsPojo.billMode' : {
												dropDown : "<font class='errorCreateUserSelect'> Please select 'Bill Mode'</font>"
											},

											// Type of Application
											'planDetailsPojo.usageType' : {
												dropDown : "<font class='errorCreateUserSelect'> Please select 'Usage Type'</font>"
											},

											// Payment Information
											 'teleservicesPayment.registrationCharges' : {
											// required : "<font class='errorCreateUser'> Please provide 'Registration Charges'</font>",
											// digits : "<font class='errorCreateUser'> Please provide digits only</font>"
												paidAmount : "<font class='errorCreateUser'>Please provide amount not more than 8 digits(and 1-2 digits after decimal)</font>"
											 },
											'teleservicesPayment.activationCharges' : {
												required : "<font class='errorCreateUser'> Please provide amount</font>",
												paidAmount : "<font class='errorCreateUser'>Please provide amount not more than 8 digits(and 1-2 digits after decimal)</font>"
											},
											'teleservicesPayment.securityCharges' : {
												// required : "<font class='errorCreateUser'> Please provide 'Security Charges'</font>",
												number : "<font class='errorCreateUser'> Please provide digits only</font>"
											},
											// 'teleservicesPayment.otherCharges' : {
											// required : "<font class='errorCreateUser'> Please provide 'Other Charges'</font>",
											// digits : "<font class='errorCreateUser'>Please provide digits only</font>"
											// },
											// 'teleservicesPayment.paymentMode' : {
											// dropDown : "<font class='errorCreateUserSelect'> Please select 'Payment Mode'</font>"
											// },
											// cash
											/*'teleservicesPayment.receiptNo' : {
												required : "<font class='errorCreateUser'> Please provide 'Receipt No'</font>",
												alphabetOrNumber : "<font class='errorCreateUser'>Please provide number or alphabets only.</font>"
											},*/
											// onlinePayment
											'teleservicesPayment.transactionId' : {
												required : "<font class='errorCreateUser'> Please provide 'Transaction Id'</font>",
												maxlength : "<font class='errorTextbox'> Maximum 45 character allowed.</font>",
												alphabetOrNumber : "<font class='errorCreateUser'>Please provide number or alphabets only.</font>"
											},
											// dd
											'teleservicesPayment.chequeDdNo' : {
												required : "<font class='errorCreateUser'> Please provide 'Cheque DD No'</font>",
												maxlength : "<font class='errorTextbox'> Maximum 6 character allowed.</font>",
												digits : "<font class='errorCreateUser'> Please provide digits only.</font>"
											},
											'teleservicesPayment.bankName' : {
												dropDown : "<font class='errorCreateUser'> Please select 'Bank Name'</font>"
											},
											// cheque
											'teleservicesPayment.chequeDdNo' : {
												required : "<font class='errorCreateUser'> Please provide 'Cheque DD No'</font>",
												maxlength : "<font class='errorTextbox'> Maximum 6 character allowed.</font>",
												digits : "<font class='errorCreateUser'> Please provide digits only.</font>"
											},
											'teleservicesPayment.bankBranch' : {
												// required : "<font class='errorCreateUser'> Please enter 'Branch Name'</font>",
												'teleservicesPayment.bankBranch' : "<font class='errorTextbox'> 'Branch Name' should be starts with alphabets.</font>",
												maxlength : "<font class='errorTextbox'> Maximum 45 character allowed.</font>"
											},
											'teleservicesPayment.displayChequeDate' : {
												required : "<font class='errorCreateUser'> Please select 'Cheque Date'</font>"
											},

											// CPE Charges
											// 'telecommunicationPayment.cpeCharges' : {
											// required : "<font class='errorCreateUser'> Please enter 'CPE Charges'</font>",
											// digits : "<font class='errorCreateUser'> Please enter valid number</font>"
											// },
											// 'telecommunicationPayment.wiFiCharges' : {
											// required : "<font class='errorCreateUser'> Please enter 'wiFi Charges'</font>",
											// digits : "<font class='errorCreateUser'> Please enter valid number</font>"
											// },
											// 'telecommunicationPayment.wiringCharges' : {
											// required : "<font class='errorCreateUser'> Please enter 'Wiring Charges'</font>",
											// digits : "<font class='errorCreateUser'> Please enter valid number</font>"
											// },
											'telecommunicationPayment.paymentMode' : {
												// dropDown : "<font class='errorCreateUserSelect'> Please select 'Payment Mode'</font>"
												telesolutionPaymentMode : "<font class='errorCreateUserSelect'> Please select 'Payment Mode'</font>"
											},
											// cash
											'telecommunicationPayment.receiptNo' : {
												required : "<font class='errorCreateUser'> Please provide 'Receipt No'</font>",
												alphabetOrNumber : "<font class='errorCreateUser'>Please provide number or alphabets only.</font>"
											},
											// onlinePayment
											'telecommunicationPayment.transactionId' : {
												required : "<font class='errorCreateUser'> Please provide 'Transaction ID'</font>",
												maxlength : "<font class='errorTextbox'> Maximum 45 character allowed.</font>",
												alphabetOrNumber : "<font class='errorCreateUser'>Please provide number or alphabets only.</font>"
											},
											'telecommunicationPayment.bankName' : {
												dropDown : "<font class='errorCreateUser'> Please provide 'Bank Name'</font>"
											},
											// cheque
											'telecommunicationPayment.chequeDdNo' : {
												required : "<font class='errorCreateUser'> Please provide 'Cheque DD No'</font>",
												maxlength : "<font class='errorTextbox'> Maximum 15 character allowed.</font>",
												digits : "<font class='errorCreateUser'> Please provide digits only.</font>"
											},
											'telecommunicationPayment.bankBranch' : {
												// required : "<font class='errorCreateUser'> Please provide 'Branch Name'</font>",
												'telecommunicationPayment.bankBranch' : "<font class='errorTextbox'> 'Branch Name' should be starts with alphabets. </font>",
												maxlength : "<font class='errorTextbox'> Maximum 45 character allowed.</font>"
											},
											'telecommunicationPayment.displayChequeDate' : {
												required : "<font class='errorCreateUser'> Please select 'Cheque Date'</font>"
											},
											'securityDepositPayment.bankBranch' : {
												'securityDepositPayment.bankBranch' : "<font class='errorTextbox'> 'Branch Name' should be starts with alphabets. </font>",
												maxlength : "<font class='errorTextbox'> Maximum 45 character allowed.</font>"
											},

											// Documents Details
											documents : {
												required : "<font class='errorCreateUser'> Please select 'Document'</font>"
											},
											addressProofs : {
												required : "<font class='errorCreateUser'> Please select 'Address Proofs'</font>"
											},
											'additionalDetailsPojo.mobileImeiNo' : {
												digits : "<font class='errorCreateUser'> Please provide only digits</font>",
												maxlength : "<font class='errorTextbox'> Maximum 15 digits allowed.</font>"
											},
											'additionalDetailsPojo.mobileMake' : {
												'additionalDetailsPojo.mobileMake' : "<font class='errorTextbox'> Please provide only alphabets between [3-45] characters</font>"
											},
											'additionalDetailsPojo.mobileNo' : {
												'additionalDetailsPojo.mobileNo' : "<font class='errorTextbox'> Please provide alpha-numeric value.</font>",
												maxlength : "<font class='errorTextbox'> Maximum 45 characters allowed.</font>"
											},
											'additionalDetailsPojo.bankBranch' : {
												'additionalDetailsPojo.bankBranch' : "<font class='errorTextbox'> 'Branch Name' should be starts with alphabets. </font>",
												maxlength : "<font class='errorTextbox'> Maximum 45 characters allowed.</font>"
											}, 'additionalDetailsPojo.bankAccountNo' : {
												digits : "<font class='errorCreateUser'> Please provide only digits</font>",
											}, 'customerDetailsPojo.businessPartner' : {
												dropDown : "<font class='errorCreateUser'> Please select 'Business Partner'</font>"
											}, 'customerDetailsPojo.salesRepresentative' : {
												dropDown : "<font class='errorCreateUser'> Please select 'Sales Representative Code'</font>"
											}, 'planDetailsPojo.basePlanCode' : {
												dropDown : "<font class='errorCreateUser'> Please select 'Base Plan Name'</font>"
											}, 'planType' : {
												required : "<font class='errorCreateUser'> Please select 'Plan Type'</font>"
											},
										}
									// invalidHandler: function(e,validator) {
									// //validator.errorList contains an array of objects, where each object has properties "element" and "message". element is the actual HTML Input.
									// for (var i=0;i<validator.errorList.length;i++){
									// console.log(validator.errorList[i]);
									// }
									//
									// //validator.errorMap is an object mapping input names -> error messages
									// for (var i in validator.errorMap) {
									// console.log(i, ":", validator.errorMap[i]);
									// }
									// }
									} );

					$( '#submitInA' )
							.click(
									function(){
										document.getElementById( 'SAVE_SUBMIT_ERROR_SPAN' ).innerHTML = '';
										if ( $( '#securityChrgeId' ).val() == '' ) {
											$( '#securityChrgeId' ).val( 0 );
										}
										if ( $( '#rentalChargeId' ).val() == '' ) {
											$( '#rentalChargeId' ).val( 0 );
										}
										if ( $( '#amountId' ).val() == "" ) {
											$( '#amountId' ).val( 0 );
										}
										if ( $( '#mobileCRF' ).val() == '0' ) {
											$( '#mobileCRF' ).val( '' );
										}
										if ( $( '#connectionTypeCRF' ).val() != 'Ind' && $( '#connectionTypeCRF' ).val() != 'Prop' ) {
											$( '#nationalityTypeId' ).val( 'Indian' );
										}
										if ( validateCRFPayments() & $( '#createInA' ).valid() & validateAddressProof() & validateDocument()
												& commonError() & validateFTRRemarks() ) {
											var answer = confirm( "Please confirm if you want to submit CAF details!" );
											if ( answer ) {
												document.forms[ 1 ].action = "crmCap.do?method=submitCRFDetails";
												document.forms[ 1 ].submit();
											}
										}
										else {
											document.getElementById( 'SAVE_SUBMIT_ERROR_SPAN' ).innerHTML = 'Please provide mandatory fields. Kindly check all tabs.<br>';
										}
									} );

					$.validator.addMethod( 'customerDetailsPojo.custMobileNo', function( value, element ){

						return this.optional( element ) || validateMobile( value );
					} );
					$.validator.addMethod( 'nationalityDetailsPojo.localCpMobileNo', function( value, element ){
						return this.optional( element ) || validateMobile( value );
					} );
					$.validator.addMethod( 'customerDetailsPojo.rmn', function( value, element ){
						validateMobile( value );
						return this.optional( element ) || validateMobile( value );
					} );

					$.validator.addMethod( 'customerDetailsPojo.crfId', function( value, element ){
						return this.optional( element ) || validateCRF( value );
					} );

					$.validator.addMethod( 'customerFirstName', function( value, element ){
						return this.optional( element ) || validateCustomerFirstName( value );
					} );

					$( "#searchCAF" ).validate(
							{
								rules : {
									'customerDetailsPojo.crfId' : {
										required : true, maxlength : 8, minlength : 8, 'customerDetailsPojo.crfId' : true
									}, 'customerDetailsPojo.product' : {
										required : true
									}
								},
								messages : {
									'customerDetailsPojo.crfId' : {
										required : "<font  class='errorCreateUser'> Please enter 'CAF Number'</font>",
										maxlength : "<font class='errorTextbox'> Maximum 8 characters allowed.</font>",
										minlength : "<font class='errorTextbox'> Please provide minimum 8 characters.</font>",
										// upperCase : "<font class='errorTextbox'>'CAF Number' should be in valid format(RAXXXXXX or EAXXXXXX).</font>",
										'customerDetailsPojo.crfId' : "<font class='errorTextbox'>'CAF Number' should be numeric.</font>"
									}, 'customerDetailsPojo.product' : {
										required : "<font  class='errorCreateUser'> Please select 'Service Name'</font>"
									}
								}
							} );
					$( '#submitSearchCRF' ).click( function(){
						if ( $( "#searchCAF" ).valid() ) {
							$( '.loadingPopup' ).removeClass( 'hidden' );
							document.forms[ 1 ].action = "crmCap.do?method=searchCrf";
							document.forms[ 1 ].submit();
						}
					} );

					$( '#vehicleMake, #vehicleRegistrationNo' )
							.keyup(
									function(){
										value = $( this );
										if ( value.val() != "" ) {
											if ( !validateTextRegex( value.val() ) ) {
												$( this ).next( 'font' ).show().html( 'Please enter alpha-numeric value starts with alphabets.' )
														.addClass( 'errorTextbox' );
											}
											else
												$( this ).next( 'font' ).hide();
										}
										else {
											if ( value.val().length > 45 ) {
												$( this ).next( 'font' ).show().html( 'Please enter no more than 45 characters.' ).addClass(
														'errorTextbox' );
											}
											else
												$( this ).next( 'font' ).hide();
										}
										$( '#idVehicleDetail' ).val( $( '#vehicleMake' ).val() + $( '#vehicleRegistrationNo' ).val() );
									} );

					// / for label: connection type
					if ( $( '#connectionType' ).text() == 'Proprietorship' ) {
						$( '#NAforIndividualProprietorship' ).addClass( 'hide1' );
						$( '#AforIndividual' ).addClass( 'hide1' );
					}
					else if ( $( '#connectionType' ).text() == 'Individual' ) {
						$( '#NAforIndividualProprietorship' ).addClass( 'hide1' );
						$( '#AforIndividual' ).removeClass( 'hide1' );
					}
					else {
						$( '#NAforIndividualProprietorship' ).removeClass( 'hide1' );
						$( '#AforIndividual' ).addClass( 'hide1' );
					}
					// / passport details
					if ( $( '#nationalityView' ).text() == 'Indian' ) {
						$( '#OtherNationalityDetailsView' ).addClass( 'hide1' );
					}
					else if ( $( '#nationalityView' ).text() == 'other' ) {
						$( '#OtherNationalityDetailsView' ).removeClass( 'hide1' );
					}
					// Service Details
					if ( $( '#viewReciverRequired' ).text() == 'Yes' ) {
						$( '#idReciverRequired' ).removeClass( 'hide1' );
					}
					else {
						$( '#idReciverRequired' ).addClass( 'hide1' );
					}

					// Display Default after saving
					var connTypeCrf = document.getElementById( "connectionTypeCRF" );
					if ( connTypeCrf != null ) {
						viewConnectionType( connTypeCrf.value );
					}
					viewNationalityDetails();
					var payMode = document.getElementById( "paymentMode" );
					var payModeCPE = document.getElementById( "paymentModeCPE" );
					var paymentModeSD = document.getElementById( "paymentModeSecurity" );
					if ( payMode != null ) {
						viewPaymentMode( payMode.value );
					}
					if ( payModeCPE != null ) {
						viewPaymentmodeCPE( payModeCPE.value );
					}
					if ( paymentModeSD != null ) {
						viewPaymentmodeSecurity( paymentModeSD.value );
					}
					$( 'select[name="customerDetailsPojo.connectionType"]' ).bind( 'change', function(){
						if ( $( this ).val() == "0" ) {
							$( this ).parent().next( 'font' ).show();
						}
						else {
							$( this ).parent().next( 'font' ).hide();
						}
					} );

					$( '#idGenerateISRPdf' ).click( function(){
						var cafId = $( '#hiddenCAFID' ).val();
						var product = $( '#hiddenProduct' ).val();
						var url = "crmCap.do?method=generateAndPrintISRReport&CAFID=" + cafId + "&Product=" + product;
						window.open( url, '', 'width=1360,height=680,scrollbars=yes,titlebar=no,fullscreen=no,resizeable=no' );
					} );
					$( '#saveCRFReferenceId' ).click(
							function(){
								if ( $( '#crfReferenceId' ).val() == "" ) {
									$( '#errorcrfReferenceNo' ).show().html( "<br/>Please provide 'CAF reference number'" ).addClass(
											'serverSideMessage error_message' ).css( 'width', 'auto' ).css( 'top', 15 );
									return false;
								}
								else {
									$( '#errorcrfReferenceNo' ).hide();
									var answer = confirm( "Please confirm, that you want to save CAF Reference Number." );
									if ( answer ) {
										var crfReferenceNo = $( '#crfReferenceId' ).val();
										var userId = $( '#hiddenCRMUserId' ).val();
										var custRecordId = $( '#hiddenRecordId' ).val();
										crmDwr.saveCRFReference( crfReferenceNo, custRecordId, userId, function( result ){
											alert( result );
											return;
										} );
									}
								}
							} );
					$( 'input[name="remarksPojo.actions"]' ).bind( 'change', function(){
						if ( $( '#approveValidationCRF' ).is( ':checked' ) ) {

							$( '#approveValidationCRFShow' ).removeClass( 'hide1' );
							$( '#approveValidationCRFShow select' ).removeAttr( 'disabled' );
							$( '#instAddressShow' ).addClass( 'hide1' );
							$( '#rejectValidationCRFShow' ).addClass( 'hide1' );
							$( '#rejectValidationCRFShow select' ).attr( 'disabled', 'disabled' );
							if ( count > 0 ) {
								$( '#submit_validationCRFEntryFT' ).removeClass( 'hide1' );
							}
							else {
								if ( $( '#serviceTypeID' ).val() == 'PO' && $( '#productID' ).val() == 'BB' ) {
									$( '#validatePOPUpForCust' ).removeClass( 'hide1' );
									$( '#submit_validationCRFEntryFT' ).addClass( 'hide1' );
								}
							}
						}
						else if ( $( '#rejectValidationCRF' ).is( ':checked' ) ) {
							$( '#rejectValidationCRFShow' ).removeClass( 'hide1' );
							$( '#rejectValidationCRFShow select' ).removeAttr( 'disabled' );

							$( '#approveValidationCRFShow' ).addClass( 'hide1' );
							$( '#approveValidationCRFShow select' ).attr( 'disabled', 'disabled' );
							$( '#submit_validationCRFEntryFT' ).removeClass( 'hide1' );
							$( '#validatePOPUpForCust' ).addClass( 'hide1' );
							$( '#instAddressShow' ).addClass( 'hide1' );
						}
						else if ( $( '#changeFeasibleAddId' ).is( ':checked' ) ) {
							$( '#rejectValidationCRFShow' ).addClass( 'hide1' );
							$( '#rejectValidationCRFShow select' ).attr( 'disabled', 'disabled' );

							$( '#approveValidationCRFShow' ).addClass( 'hide1' );
							$( '#approveValidationCRFShow select' ).attr( 'disabled', 'disabled' );
							$( '#submit_validationCRFEntryFT' ).removeClass( 'hide1' );
							$( '#validatePOPUpForCust' ).addClass( 'hide1' );
							$( '#instAddressShow' ).removeClass( 'hide1' );

						}
					} );

					$( "#validationCRFEntry" ).validate(
							{
								rules : {
									'remarksPojo.actions' : {
										required : true
									}, 'remarksPojo.remarks' : {
										required : true, maxlength : 4000, minlength : 2
									},
								},
								messages : {
									'remarksPojo.actions' : {
										required : "<font class='errorRadio'> Please select at least one 'Action'</font>"
									},
									'remarksPojo.remarks' : {
										required : "<font class='errorAddress'> Please provide your 'Remarks'</font>",
										minlength : "<font class='errorAddress'> Please enter Remarks between [2-4000].</font>",
										maxlength : "<font class='errorAddress'> Please enter Remarks between [2-4000].</font>"
									},
								}
							} );

					$( '#submit_validationCRFEntryFT' )
							.click(
									function(){
										if ( $( "#validationCRFEntry" ).valid() ) {
											var check = true;
											var stage = document.getElementById( "stageId" ).value;
											$( '#partnerNameId' ).parent().next( 'font' ).hide();
											if ( stage == 'FT' ) {
												if ( $( 'input:radio[name="remarksPojo.actions"]:checked' ).val() == "Approve" ) {
													// var isrDate = document.getElementById( "ISRDate" );
													// if ( null != isrDate ) {
													// isrDate = isrDate.value;
													// }
													// if ( null != isrDate ) {
													// check = true;
													// }
													if ( $( '#partnerNameId' ).val() == 0 || $( '#partnerNameId' ).val() == '' ) {
														$( '#partnerNameId' ).parent().next( 'font' ).show().html( "Please select 'Network Partner'" )
																.addClass( 'errorTextbox' ).css( 'width', 'auto' );
														check = false;
													}
													else if ( document.getElementById( "partnerNameId" ) == null ) {
														alert( 'No Network Partner Available. Please add Partner in Society.' );
														check = false;
													}
												}
												else {
													if ( $( 'select[name="remarksPojo.reasonId"]' ).val() == 0 ) {
														$( 'select[name="remarksPojo.reasonId"]' ).parent().next( 'font' ).show().html(
																"Please select 'Reason for Rejection'" ).addClass( 'errorTextbox' ).css( 'width',
																'auto' );
														check = false;
													}
													else {
														$( 'select[name="remarksPojo.reasonId"]' ).parent().next( 'font' ).hide();
													}
												}
											}
											if ( stage == 'NPR' ) {
												if ( $( 'input:radio[name="remarksPojo.actions"]:checked' ).val() == "Reject" ) {
													if ( $( 'select[name="remarksPojo.reasonId"]' ).val() == 0 ) {
														$( 'select[name="remarksPojo.reasonId"]' ).parent().next( 'font' ).show().html(
																"Please select 'Reason for Close'" ).addClass( 'errorTextbox' ).css( 'width', 'auto' );
														check = false;
													}
													else {
														$( 'select[name="remarksPojo.reasonId"]' ).parent().next( 'font' ).hide();
													}
												}
												else { // Approve
													if ( $( '#partnerNameId' ).val() == 0 || $( '#partnerNameId' ).val() == '' ) {
														$( '#partnerNameId' ).parent().next( 'font' ).show().html( "Please select 'Network Partner'" )
																.addClass( 'errorTextbox' ).css( 'width', 'auto' );
														check = false;
													}
													else if ( document.getElementById( "partnerNameId" ) == null ) {
														alert( 'No Network Partner Available. Please add Partner in Society.' );
														check = false;
													}
													else {
														$( '#partnerNameId' ).parent().next( 'font' ).hide();
													}
												}

											}
											if ( stage == 'FTSR' ) {
												if ( $( 'input[name="displayISRDate"]' ).val() == "" ) {
													$( 'input[name="displayISRDate"]' ).next( 'font' ).show().html( "Please select 'ISR Date'" )
															.addClass( 'errorTextbox' );
													check = false;
												}
												else {
													var days = dateDiff( getISODate( $( '#activationDateID' ).val() ), getISODate( $(
															'input[name="displayISRDate"]' ).val() ) );
													if ( days < 0 ) {
														$( 'input[name="displayISRDate"]' ).next( 'font' ).show().html(
																"ISR date should be same or greater than as MAC Address bind date" ).addClass(
																'errorTextbox' ).css( 'top', '20px' );
														check = false;
													}
													else {
														$( 'input[name="displayISRDate"]' ).next( 'font' ).hide();
													}
												}
											}
											if ( stage == 'NPBP' ) {
												if ( $( 'input:radio[name="remarksPojo.actions"]:checked' ).val() == "Reject" ) {
													if ( $( 'select[name="remarksPojo.reasonId"]' ).val() == 0 ) {
														$( 'select[name="remarksPojo.reasonId"]' ).parent().next( 'font' ).show().html(
																"Please select 'Reason for Cancellation'" ).addClass( 'errorTextbox' ).css( 'width',
																'auto' );
														check = false;
													}
													else {
														$( 'select[name="remarksPojo.reasonId"]' ).parent().next( 'font' ).hide();
													}
												}
											}
											if ( check ) {
												var ans = confirm( "Please confirm if you want to Forward CAF!" );
												if ( ans ) {
													document.forms[ 1 ].action = "crmCap.do?method=saveValidateCRFEntry";
													document.forms[ 1 ].submit();
												}
											}
										}
									} );
					$( '#validatePOPUpForCust' ).click( function(){
						$( '#submit_validationCRFEntryFT' ).removeClass( 'hide1' );
						count++;
						$( '#validatePOPUpForCust' ).addClass( 'hide1' );

					} );

					$( '.next' ).click( function(){
						$( ".tab_content" ).hide();
						$( $( this ).attr( "href" ) ).show();
					} );
					$( '.prev' ).click( function(){
						$( ".tab_content" ).hide();
						$( $( this ).attr( "href" ) ).show();
					} );
					// hide client side error when js error generated
					$( '#customerBasicInfo_Save,#orderPaymentDetails_Save,#documentDetailsPojo_Save,#additionalInformation_Save,#submitInA' ).click(
							function(){
								if ( $( '#SAVE_SUBMIT_ERROR_SPAN' ).text() != null && $( '#SAVE_SUBMIT_ERROR_SPAN' ).text() != '' ) {
									$( '#error' ).css( 'display', 'none' );
								}
							} );
					if ( $( '#bpCodeId' ).val() == "0" ) {
						$( "input#bpCode" ).val( "" );
					}
				} );

function checkFeasibility(){
	window.open( 'crmCap.do?method=checkFeasibility', 'newWindow', 'width=620,height=350,scrollbars=yes,resizable=no,toolbar=no' );
}

/** *****------- nationality --------*********** */

function sum(){
	var result = 0.0;
	if ( $( '#securityChrgeId' ).val() != 0 ) {
		var securityChrge = $( '#securityChrgeId' ).val();
		result += parseFloat( securityChrge );
	}
	if ( $( '#rentalChargeId' ).val() != 0 ) {
		result += parseFloat( $( '#rentalChargeId' ).val() );
	}
	if ( $( '#installChargeId' ).val() != 0 ) {
		result += parseFloat( $( '#installChargeId' ).val() );
	}
	if ( !isNaN( result ) ) {
		document.getElementById( 'total' ).value = parseFloat( result ).toFixed( 2 );
	}
}

function openTab( selectedTab ){
	document.getElementById( 'tabHidden' ).value = selectedTab;
	$( "#error" ).hide();
}
function commonError(){
	var flag = true;
	$( '#firstNameCRF' ).next( 'font' ).hide();
	$( '#lastNameCRF' ).next( 'font' ).hide();
	$( '#dateOnCRF' ).next( 'font' ).hide();
	$( '#companyNameCRF' ).next( 'font' ).hide();
	$( ' #connectionTypeCRF' ).parent().next( 'font' ).hide();
	if ( $( '#dateOnCRF' ).val() == "" || $( '#connectionTypeCRF' ).val() == "0" ) {
		if ( $( '#dateOnCRF' ).val() == "" ) {
			$( '#dateOnCRF' ).next( 'font' ).show().html( "Please select 'CAF Date'" ).addClass( 'errorTextbox' );
		}
		if ( $( '#connectionTypeCRF' ).val() == "0" ) {
			$( ' #connectionTypeCRF' ).parent().next( 'font' ).show().html( "Please select 'Connection Type'" ).addClass( 'errorTextbox' );
		}
		flag = false;
	}
	else if ( $( '#connectionTypeCRF' ).val() == "Ind" ) {
		if ( $( '#firstNameCRF' ).val() == "" ) {
			$( '#firstNameCRF' ).next( 'font' ).show().html( "Please provide 'First Name'" ).addClass( 'errorTextbox' );
			flag = false;
		}
		else if ( $( '#firstNameCRF' ).val().length < 1 || $( '#firstNameCRF' ).val().length > 50
				|| !validateCustomerFirstName( $( '#firstNameCRF' ).val() ) ) {
			flag = false;
		}
	}
	else if ( $( '#connectionTypeCRF' ).val() == "Prop" ) {
		if ( $( '#firstNameCRF' ).val() == "" ) {
			$( '#firstNameCRF' ).next( 'font' ).show().html( "Please provide 'First Name'" ).addClass( 'errorTextbox' );
			flag = false;
		}
		else if ( $( '#firstNameCRF' ).val().length < 1 || $( '#firstNameCRF' ).val().length > 50
				|| !validateCustomerFirstNameForAlphaNumeric( $( '#firstNameCRF' ).val() ) ) {
			flag = false;
		}
		else if ( $( '#lastNameCRF' ).val().length < 1 || $( '#lastNameCRF' ).val().length > 50
				|| !validateCustomerFirstNameForAlphaNumeric( $( '#lastNameCRF' ).val() ) ) {
			flag = false;
		}
	}
	else {
		if ( $( '#companyNameCRF' ).val() == "" ) {
			$( '#companyNameCRF' ).next( 'font' ).show().html( "Please provide 'Company Name'" ).addClass( 'errorTextbox' );
			flag = false;
		}
		else {
			var ccrf = $( '#companyNameCRF' ).val();
			if ( ccrf.length < 3 || ccrf.length > 200 ) {
				flag = false;
			}
		}

	}
	if ( flag ) {
		$( '#firstNameCRF, #lastNameCRF, #dateOnCRF,#companyNameCRF' ).next( 'font' ).hide();
		$( ' #connectionTypeCRF' ).parent().next( 'font' ).hide();
		flag = true;
	}
	if ( flag && $( '#dateOnCRF' ).val() != "" ) {
		if ( dateDiff( new Date(), getISODate( $( '#dateOnCRF' ).val() ) ) > 0 ) {
			alert( "CAF Date cannot be a future date" );
			flag = false;
		}
		else if ( dateDiff( getISODate( $( '#dateOnCRF' ).val() ), new Date() ) > 90 ) {
			alert( "CAF Date must be from '" + ( new Date( new Date().getTime() - ( 90 * 24 * 60 * 60 * 1000 ) ).toDateString() ) + " to "
					+ ( new Date().toDateString() ) + "'" );
			flag = false;
		}
	}
	if ( flag && $( '#birthdate' ).val() != "" ) {
		var dateStr = $( '#birthdate' ).val();
		flag = pastDateValidityChecker( dateStr, 0 );
		if ( !flag ) {
			$( '#birthdate ~ font' ).text( 'Please Provide correct Birth Date' ).show();
		}
		else {
			$( '#birthdate ~ font' ).text( 'Please Provide correct Birth Date' ).hide();
		}
	}
	/*
	 * if ( flag && $( '#passportValidUpToCRF' ).val() != "" ) { var dateStr = $( '#passportValidUpToCRF' ).val(); flag = dateValidityChecker( dateStr, 90 ); if ( !flag ) { $( '#passportValidUpToCRF ~ font' ).text( 'Passport Validity should be more than 90 days' ).show(); } else { $(
	 * '#passportValidUpToCRF ~ font' ).text( 'Passport Validity should be more than 90 days' ).hide(); } }
	 */

	if ( flag && $( '#visaValidUpToCRF' ).val() != "" ) {
		var dateStr = $( '#visaValidUpToCRF' ).val();
		flag = dateValidityChecker( dateStr, 30 );
		if ( !flag ) {
			$( '#visaValidUpToCRF ~ font' ).text( 'Visa Validity should be more than 30 days' ).show();
		}
		else {
			$( '#visaValidUpToCRF ~ font' ).text( 'Visa Validity should be more than 30 days' ).hide();
		}
	}
	if ( flag && $( '#teleservicesChequeDate' ).val() != "" ) {
		var chequeDate = $( '#teleservicesChequeDate' ).val();
		flag = validityChequeDate( chequeDate, 90 );
		if ( !flag ) {
			$( '#teleservicesChequeDate ~ font' ).text( "Please provide 'Cheque/DD Date' within last 90 days only" ).show();
		}
		else {
			$( '#teleservicesChequeDate ~ font' ).text( "Please provide 'Cheque/DD Date' within last 90 days only" ).hide();
		}
	}
	if ( flag && $( '#teleCommChequeDate' ).val() != "" ) {
		var chequeDate = $( '#teleCommChequeDate' ).val();
		flag = validityChequeDate( chequeDate, 90 );
		if ( !flag ) {
			$( '#teleCommChequeDate ~ font' ).text( "Please provide 'Cheque/DD Date' within last 90 days only" ).show();
		}
		else {
			$( '#teleCommChequeDate ~ font' ).text( "Please provide 'Cheque/DD Date' within last 90 days only" ).hide();
		}
	}
	if ( flag && $( '#chequeDateSecurity' ).val() != "" && $( '#chequeDateSecurity' ).val() != undefined ) {
		var chequeDate = $( '#chequeDateSecurity' ).val();
		flag = validityChequeDate( chequeDate, 90 );
		if ( !flag ) {
			$( '#chequeDateSecurity ~ font' ).text( "Please provide 'Cheque/DD Date' within last 90 days only" ).show();
		}
		else {
			$( '#chequeDateSecurity ~ font' ).text( "Please provide 'Cheque/DD Date' within last 90 days only" ).hide();
		}
	}
	if ( flag && document.getElementById( 'ftr_remarks' ) !== null ) {
		var remarks = $( '#ftr_remarks' ).val();
		if ( $.trim( remarks ) != '' && ( remarks.trim().length < 2 || remarks.trim().length > 4000 ) ) {
			$( '#ftr_remarks' ).next( 'font' ).show().text( 'Please enter Remarks between [2-4000].' );
			flag = false;
		}
	}
	if ( flag && !inaValidator.element( '#bpCodeId' ) ) {
		flag = false;
	}
	if ( flag && !inaValidator.element( '#srCodeId' ) ) {
		flag = false;
	}
	// console.log( "Common Error:" + flag );
	return flag;
}
function saveForm(){
	document.getElementById( 'SAVE_SUBMIT_ERROR_SPAN' ).innerHTML = '';
	if ( $( '#securityChrgeId' ).val() == '' ) {
		$( '#securityChrgeId' ).val( 0 );
	}
	if ( $( '#rentalChargeId' ).val() == '' ) {
		$( '#rentalChargeId' ).val( 0 );
	}
	if ( $( '#amountId' ).val() == "" ) {
		$( '#amountId' ).val( 0 );
	}
	if ( commonError() && validateCRFPayments() ) {
		var answer = confirm( "Please confirm if you want to save CAF details!" );
		if ( answer ) {
			document.forms[ 1 ].action = "crmCap.do?method=saveCustomerBasicInfo";
			document.forms[ 1 ].submit();
		}
	}
	else {
		document.getElementById( 'SAVE_SUBMIT_ERROR_SPAN' ).innerHTML = 'Please provide mandatory fields. Kindly check all tabs.<br>';
	}
}
function viewConnectionType( connTypeVal ){
	if ( connTypeVal == "Ind" || connTypeVal == "Prop" ) {
		$( '#AforIndividual' ).removeClass( 'hide1' );
		$( '#NAforIndividualProprietorship' ).addClass( 'hide1' );
		$( '#AforIndividual input' ).attr( 'disabled', false );
		$( '#NAforIndividualProprietorship input' ).attr( 'disabled', true );
		$( '#OtherNationalityDetails' ).removeClass( 'hide1' );
		$( '#NAforOnlyIndividual' ).removeClass( 'hide1' );
		$( '#NAforOnlyCompany' ).addClass( 'hide1' );
		$( '#NAforOnlyCompany input' ).attr( 'disabled', true );
		$( '#NAforOnlyIndividual input' ).removeAttr( 'disabled' );
		$( '#OtherNationalityDetails input, #OtherNationalityDetails select, #OtherNationalityDetails textarea' ).attr( 'disabled', false );
	}
	else {
		$( '#NAforIndividualProprietorship' ).removeClass( 'hide1' );
		$( '#AforIndividual' ).addClass( 'hide1' );
		$( '#AforIndividual input' ).attr( 'disabled', true );
		$( '#NAforIndividualProprietorship input' ).attr( 'disabled', false );
		$( '#OtherNationalityDetails' ).addClass( 'hide1' );
		$( '#NAforOnlyIndividual' ).addClass( 'hide1' );
		$( '#NAforOnlyCompany' ).removeClass( 'hide1' );
		$( '#NAforOnlyCompany input' ).removeAttr( 'disabled' );
		$( '#NAforOnlyIndividual input' ).attr( 'disabled', true );
		$( '#OtherNationalityDetails input, #OtherNationalityDetails select, #OtherNationalityDetails textarea' ).attr( 'disabled', true );
	}
	if ( connTypeVal == "0" ) {
		$( '#NAforIndividualProprietorship' ).addClass( 'hide1' );
		$( '#AforIndividual' ).addClass( 'hide1' );
		$( '#AforIndividual input' ).attr( 'disabled', true );
		$( '#NAforIndividualProprietorship input' ).attr( 'disabled', true );
		$( '#OtherNationalityDetails' ).addClass( 'hide1' );
		$( '#NAforOnlyIndividual' ).removeClass( 'hide1' );
		$( '#NAforOnlyCompany' ).addClass( 'hide1' );
		$( '#OtherNationalityDetails input, #OtherNationalityDetails select, #OtherNationalityDetails textarea' ).attr( 'disabled', true );
	}
	if ( $( '#nationalityTypeId' ).val() == "Indian" ) {
		$( '#OtherNationalityDetails' ).addClass( 'hide1' );
		$( '#otherNationality' ).addClass( 'hide1' );
		$( '#otherNationality :input' ).attr( 'disabled', true );
		$( '#OtherNationalityDetails input, #OtherNationalityDetails select, #OtherNationalityDetails textarea' ).attr( 'disabled', true );
	}
	else if ( $( '#nationalityTypeId' ).val() == "0" ) {
		$( '#OtherNationalityDetails' ).addClass( 'hide1' );
		$( '#otherNationality' ).addClass( 'hide1' );
		$( '#otherNationality :input' ).attr( 'disabled', true );
		$( '#OtherNationalityDetails input, #OtherNationalityDetails select, #OtherNationalityDetails textarea' ).attr( 'disabled', true );
	}
	else {
		$( '#OtherNationalityDetails input, #OtherNationalityDetails select, #OtherNationalityDetails textarea' ).attr( 'disabled', false );
		$( '#otherNationality' ).removeClass( 'hide1' );
		$( '#otherNationality :input' ).attr( 'disabled', false );
	}
}
function viewNationalityDetails( nationTypeVal ){
	if ( nationTypeVal == "Indian" ) {
		$( '#OtherNationalityDetails' ).addClass( 'hide1' );
		$( '#otherNationality' ).addClass( 'hide1' );
		$( '#otherNationality :input' ).attr( 'disabled', true );
		$( '#OtherNationalityDetails input, #OtherNationalityDetails select, #OtherNationalityDetails textarea' ).attr( 'disabled', true );
	}
	else if ( nationTypeVal == "0" ) {
		$( '#OtherNationalityDetails' ).addClass( 'hide1' );
		$( '#otherNationality' ).addClass( 'hide1' );
		$( '#otherNationality :input' ).attr( 'disabled', true );
		$( '#OtherNationalityDetails input, #OtherNationalityDetails select, #OtherNationalityDetails textarea' ).attr( 'disabled', true );
	}
	else {
		$( '#OtherNationalityDetails' ).removeClass( 'hide1' );
		$( '#otherNationality' ).removeClass( 'hide1' );
		$( '#otherNationality :input' ).attr( 'disabled', false );
		$( '#OtherNationalityDetails input, #OtherNationalityDetails select, #OtherNationalityDetails textarea' ).attr( 'disabled', false );
	}
}
function viewPaymentMode( paymentModeVal ){
	if ( paymentModeVal == 'Q' ) {
		$( '#cheque' ).removeClass( 'hide1' );
		$( '#bankName' ).removeClass( 'hide1' );
		$( '#cheque input, #bankName select' ).attr( 'disabled', false );
		// $('#bankName select').attr('value', '0');
		$( '#cash, #dd, #onlinePayment' ).addClass( 'hide1' );
		$( '#cash input, #dd input, #onlinePayment input' ).attr( 'disabled', true );
	}
	else if ( paymentModeVal == 'C' ) {
		$( '#cash' ).removeClass( 'hide1' );
		$( '#cash input' ).attr( 'disabled', false );
		$( '#cheque, #dd, #onlinePayment, #bankName' ).addClass( 'hide1' );
		$( '#cheque input, #dd input, #onlinePayment input, #bankName select' ).attr( 'disabled', true );
	}
	// else if (paymentModeVal == 'dd') {
	// $('#dd').removeClass('hide1');
	// $('#bankName').removeClass('hide1');
	// //$('#bankName select').attr('value', '0');
	// $('#dd input, #bankName select').attr('disabled', false);
	// $('#cheque, #cash, #onlinePayment').addClass('hide1');
	// $('#cheque input, #cash input, #onlinePayment input').attr('disabled', true);
	// }
	else if ( paymentModeVal == 'O' || paymentModeVal == "NEFT" ) {
		$( '#onlinePayment' ).removeClass( 'hide1' );
		$( '#onlinePayment input' ).attr( 'disabled', false );
		$( '#cheque, #cash, #dd, #bankName' ).addClass( 'hide1' );
		$( '#cheque input, #cash input, #dd input, #bankName select' ).attr( 'disabled', true );
	}
	else {
		$( '#cheque, #cash, #dd, #onlinePayment,  #bankName ' ).addClass( 'hide1' );
		$( '#cheque input, #cash input, #dd input, #onlinePayment input, #bankName select' ).attr( 'disabled', true );
	}
}
function viewPaymentmodeCPE( paymentModeCPEVal ){
	if ( paymentModeCPEVal == 'Q' ) {
		$( '#chequeCPE' ).removeClass( 'hide1' );
		$( '#bankNameCPE' ).removeClass( 'hide1' );
		$( '#chequeCPE input, #bankNameCPE' ).attr( 'disabled', false );
		$( '#cashCPE, #ddCPE, #onlinePaymentCPE' ).addClass( 'hide1' );
		$( '#cashCPE input, #ddCPE input, #onlinePaymentCPE input' ).attr( 'disabled', true );
	}
	else if ( paymentModeCPEVal == 'C' ) {
		$( '#cashCPE' ).removeClass( 'hide1' );
		$( '#cashCPE input' ).attr( 'disabled', false );
		$( '#chequeCPE, #ddCPE, #onlinePaymentCPE,#bankNameCPE' ).addClass( 'hide1' );
		$( '#chequeCPE input, #ddCPE input, #onlinePaymentCPE input,#bankNameCPE' ).attr( 'disabled', true );
	}
	else if ( paymentModeCPEVal == 'O' || paymentModeCPEVal == "NEFT" ) {
		$( '#onlinePaymentCPE' ).removeClass( 'hide1' );
		$( '#onlinePaymentCPE input' ).attr( 'disabled', false );
		$( '#chequeCPE, #cashCPE, #ddCPE,#bankNameCPE' ).addClass( 'hide1' );
		$( '#chequeCPE input, #cashCPE input, #ddCPE input,#bankNameCPE' ).attr( 'disabled', true );
	}
	else {
		$( '#chequeCPE, #cashCPE, #ddCPE, #onlinePaymentCPE,#bankNameCPE' ).addClass( 'hide1' );
		$( '#chequeCPE input, #cashCPE input, #ddCPE input, #onlinePaymentCPE input,#bankNameCPE' ).attr( 'disabled', true );
	}
}

function viewTelesolutionPayment( cpeStatusVal ){
	if ( $( 'input[name="customerDetailsPojo.product"]' ).val() == "BB" || $( 'input[name="customerDetailsPojo.product"]' ).val() == "RF"
			&& $( 'input[name="customerDetailsPojo.serviceType"]' ).val() == "PO" ) {
		if ( cpeStatusVal == "NR" ) {
			$( '#CPECharges' ).removeClass( 'hide1' );
			$( '#CPECharges :input' ).removeAttr( 'disabled' );
			$( '#paymentModeCPE' ).triggerHandler( 'change' );
		}
		else {
			$( '#CPECharges' ).addClass( 'hide1' );
			$( '#CPECharges :input' ).attr( 'disabled', true );
		}
	}
}

function validateCrfIdWithService(){
	var flag = true;
	var crfId = $( 'input[name="customerDetailsPojo.crfId"]' ).val();
	var crfStart = crfId.substring( 0, 2 );
	var serviceName = document.getElementById( "productType" ).value;
	if ( serviceName == "EOC" && crfStart == "RA" ) {
		flag = false;
		alert( "Please enter correct CAF Number starts with EA." );
	}
	else if ( ( serviceName == "BB" || serviceName == "RF" ) && crfStart == "EA" ) {
		flag = false;
		alert( "Please enter correct CAF Number starts with RA." );
	}
	return flag;
}
function validateDocument(){
	$( 'font#dynamicI' ).hide();
	var sizeId = document.getElementById( "idDocument" );
	var listSize = 0;
	var proceed = false;
	if ( null != sizeId ) {
		listSize = sizeId.value;
	}
	if ( listSize > 0 ) {
		for ( var rCount = 0; rCount < listSize; rCount++ ) {
			var editable = document.getElementsByName( "documents[" + rCount + "].selected" );
			if ( editable[ 0 ].checked ) {
				proceed = true;
				break;
			}
		}
	}
	if ( !proceed ) {
		$( '#dynamicI' ).show().html( 'Please select at least one document for ID Proof.' ).addClass( 'errorTextbox' ).css( 'width', 'auto' ).css(
				'top', 60 );
	}
	else {
		$( '#dynamicA' ).next( 'font' ).hide();
		proceed = true;
	}
	return proceed;
}

function validateCRFPayments(){
	var valid = true;
	if ( $( '#securityChrgeId' ).val() == '' ) {
		$( '#securityChrgeId' ).val( 0 );
	}
	if ( $( '#rentalChargeId' ).val() == '' ) {
		$( '#rentalChargeId' ).val( 0 );
	}
	if ( $( '#amountId' ).val() == '' ) {
		$( '#amountId' ).val( 0 );
	}
	if ( $( '#total' ).val() == '' ) {
		$( '#total' ).val( 0 );
	}
	var serviceType = document.getElementById( "serviceType" );
	if ( null != serviceType ) {
		serviceType = serviceType.value;
	}
	var toValidate = true;
	var service = $( 'input[name="customerDetailsPojo.product"]' ).val();
	if ( $( '#cpeStatusId' ).val() == "NR" ) {
		if ( $( '#miniTelesolutionAmount' ).val() != "" && parseFloat( $( '#miniTelesolutionAmount' ).val() ) > 0 ) {
			if ( service == "BB" && serviceType == "PO" ) {
				toValidate = false;
			}
			if ( parseFloat( $( '#amountId' ).val() ) < parseFloat( $( '#miniTelesolutionAmount' ).val() ) ) {
				$( '#amountId ~ font' ).text( "Telesolution Payment cannot be less than " + $( '#miniTelesolutionAmount' ).val() ).show();
				valid = false;
			}
		}

	}
	if ( toValidate && valid ) {
		$( '#paymentInformation font' ).hide();
		if ( serviceType == "PO" ) {
			if ( $( '#miniTotalRental' ).val() != "" && parseFloat( $( '#miniTotalRental' ).val() ) > 0 ) {
				if ( parseFloat( $( '#rentalChargeId' ).val() ) < parseFloat( $( '#miniTotalRental' ).val() ) ) {
					$( '#rentalChargeId ~ font' ).text( 'Advance Payment should not be less than ' + $( '#miniTotalRental' ).val() ).show();
					valid = false;
				}
			}
		}
		else {
			if ( $( '#miniTotalRental' ).val() != "" && parseFloat( $( '#miniTotalRental' ).val() ) > 0 ) {
				if ( parseFloat( $( '#rentalChargeId' ).val() ) < ( parseFloat( $( '#miniTotalRental' ).val() ) - 1 ) ) {
					$( '#rentalChargeId ~ font' )
							.text( 'Rental Charge should not be less than ' + ( parseFloat( $( '#miniTotalRental' ).val() ) - 1 ) ).show();
					valid = false;
				}
			}
		}
		if ( valid ) {
			if ( $( '#miniSecurity' ).val() != "" && parseFloat( $( '#miniSecurity' ).val() ) > 0 ) {
				if ( parseFloat( $( '#securityChrgeId' ).val() ) < ( parseFloat( $( '#miniSecurity' ).val() ) - 1 ) ) {
					$( '#securityChrgeId ~ font' ).text( 'Security Deposit should not be less than ' + $( '#miniSecurity' ).val() ).show();
					valid = false;
				}
			}
		}

	}
	if(valid){
		var payMode = document.getElementById( "paymentMode" );
		if(($('#ecafAadhar').val()=="" || $('#ecafAadhar').val()==null) && payMode.value == "C"){
			if($('#receiptNoId').val() =="" || $('#receiptNoId').val() == null){
				$( '#receiptNoId ~ font' ).text( 'Please provide Receipt No').show();
				valid =false;
				}		
		}
	}
	// console.log( "validateCRFPayments:" + valid );
	return valid;
}
function validateAddressProof(){
	$( 'font#dynamicA' ).hide();
	var sizeId = document.getElementById( "idAddressProof" );
	var listSize = 0;
	var proceed = false;
	if ( null != sizeId ) {
		listSize = sizeId.value;
	}
	if ( listSize > 0 ) {
		for ( var rCount = 0; rCount < listSize; rCount++ ) {
			var editable = document.getElementsByName( "addressProofs[" + rCount + "].selected" );
			if ( editable[ 0 ].checked ) {
				proceed = true;
				break;
			}
		}
	}
	if ( !proceed ) {
		$( '#dynamicA' ).show().html( 'Please select at least one document for Address Proof.' ).addClass( 'errorTextbox' ).css( 'width', 'auto' )
				.css( 'top', 105 );
	}
	else {
		$( '#dynamicA' ).next( 'font' ).hide();
		proceed = true;
	}
	return proceed;
}

function validateFTRRemarks(){
	if ( document.getElementById( 'ftr_remarks' ) !== null ) {
		var remarks = $( '#ftr_remarks' ).val().trim();
		if ( remarks.trim().length < 2 || remarks.trim().length > 4000 ) {
			$( '#ftr_remarks' ).next( 'font' ).show().text( 'Please enter Remarks between [2-4000].' );
			return false;
		}
	}
	return true;
}

function validateTelesolutionPaymentMode( value ){
	var amount = $( '#amountId' ).val();
	if ( parseFloat( amount ) == 0 || amount == '' ) {
		return true;
	}
	else if ( value == '' || value == 0 ) {
		return false;
	}
	return true;
}

function setPlanAmounts( securityId, rentalId, telesolutionAmountId, totalId, planCode ){
	var planService = document.getElementById( "hiddenPlanServiceId" ).value;
	if ( planCode == 0 || planCode == "" || planCode == "Select Base Plan Name" ) {
		dwr.util.setValue( rentalId, 0 );
		dwr.util.setValue( securityId, 0 );
		dwr.util.setValue( "miniRental", 0 );
		dwr.util.setValue( "miniTotalRental", 0 );
		dwr.util.setValue( "miniSecurity", 0 );
		dwr.util.setValue( telesolutionAmountId, 0 );
		dwr.util.setValue( "miniTelesolutionAmount", 0 );
		dwr.util.setValue( totalId, 0 );
		dwr.util.setValue( "addOnDulValueCRF", "" );
		dwr.util.setValue( "addOnrental", 0 );
		$( '#addOnDulValueCRF' ).attr( 'disabled', true );
		$( '#addOnDulValueCRF' ).addClass( 'gray_text' );
	}
	else {
		crmDwr.getPlanAmounts( planService, planCode, "BASE", function( list ){
			if ( list != null ) {
				var serviceType = document.getElementById( "serviceType" );
				if ( null != serviceType ) {
					serviceType = serviceType.value;
				}
				var rental = list.advTelservPayment;
				var total = 0;
				dwr.util.setValue( securityId, list.securityDeposit );
				dwr.util.setValue( telesolutionAmountId, list.advTelsolnPayment );
				dwr.util.setValue( "miniTelesolutionAmount", list.advTelsolnPayment );
				var miniTotalRental = parseFloat( rental );
				rental = rental.toFixed( 2 );
				miniTotalRental = miniTotalRental.toFixed( 2 );
				dwr.util.setValue( rentalId, rental );
				dwr.util.setValue( "miniRental", rental );
				dwr.util.setValue( "miniTotalRental", miniTotalRental );
				dwr.util.setValue( "miniSecurity", list.securityDeposit );
				if ( !isNaN( list.securityDeposit ) ) {
					total += parseFloat( list.securityDeposit );
				}
				if ( !isNaN( rental ) ) {
					total += parseFloat( rental );
				}
				total = total.toFixed( 2 );
				dwr.util.setValue( totalId, total );
				dwr.util.setValue( "addOnDulValueCRF", "" );
				dwr.util.setValue( "addOnrental", 0 );
				dwr.util.setValue( "brandNameID", list.brand );
				if ( list.addonAllowedYn == 'N' ) {
					$( '#addOnDulValueCRF' ).attr( 'disabled', true );
					$( '#addOnDulValueCRF' ).addClass( 'gray_text' );
				}
				else {
					$( '#addOnDulValueCRF' ).removeAttr( 'disabled' );
					$( '#addOnDulValueCRF' ).removeClass( 'gray_text' );
				}
			}
		} );
	}
}
function addToPlanAmounts( securityId, rentalId, totalId, planCode ){
	var planService = document.getElementById( "hiddenPlanServiceId" ).value;
	var serviceType = document.getElementById( "serviceType" );
	if ( null != serviceType ) {
		serviceType = serviceType.value;
	}
	if ( serviceType == 'PO' ) {
		return false;
	}
	if ( planCode == 0 || planCode == "" ) {
		var basePlanCode = document.getElementById( "basePlanNameCRF" );
		if ( null != basePlanCode ) {
			setPlanAmounts( 'securityChrgeId', 'rentalChargeId', 'amountId', 'total', basePlanCode.value );
		}
	}
	else {
		crmDwr.getPlanAmounts( planService, planCode, "ADDON", function( list ){
			if ( list != null ) {
				var rental = parseFloat( $( '#' + rentalId ).val() );
				if ( $( '#addOnrental' ).val() != '' && !isNaN( $( '#addOnrental' ).val() ) ) {
					if ( !isNaN( $( '#' + rentalId ).val() ) ) {
						rental = parseFloat( $( '#' + rentalId ).val() ) - parseFloat( $( '#addOnrental' ).val() );
					}
				}

				if ( !isNaN( list.rentInclTax ) ) {
					rental = parseFloat( rental ) + parseFloat( list.rentInclTax );
				}
				dwr.util.setValue( "addOnrental", list.rentInclTax );
				var total = parseFloat( rental );
				if ( !isNaN( $( '#' + securityId ).val() ) ) {
					total = parseFloat( total ) + parseFloat( $( '#' + securityId ).val() );
				}
				var miniTotalRental = 0;
				if ( !isNaN( list.rentInclTax ) ) {
					miniTotalRental = parseFloat( miniTotalRental ) + parseFloat( list.rentInclTax );
				}
				if ( !isNaN( $( '#miniRental' ).val() ) ) {
					miniTotalRental = parseFloat( miniTotalRental ) + parseFloat( $( '#miniRental' ).val() );
				}
				rental = rental.toFixed( 2 );
				miniTotalRental = miniTotalRental.toFixed( 2 );
				total = total.toFixed( 2 );
				dwr.util.setValue( rentalId, rental );
				dwr.util.setValue( "miniTotalRental", miniTotalRental );
				dwr.util.setValue( totalId, total );
			}
		} );
	}
}
function getAllCities( cityId, paymentMode ){
	if ( paymentMode == 'cheque' ) {
		crmDwr.getAllCity( function( list ){
			addCity( cityId, list );
		} );
		function addCity( cityId, list ){
			if ( list != null ) {
				dwr.util.removeAllOptions( cityId );
				dwr.util.addOptions( cityId, list, "cityName", "cityName" );
			}
		}
	}
	else {
		return false;
	}
}

function disableBillingForm( checkbox ){
	if ( $( checkbox ).is( ":checked" ) ) {
		$( "#DIVStateCityBilling" ).addClass( 'hide1' );
		$( '#billingAddress :input' ).attr( 'readonly', 'readonly' );
		$( "#sameAsInstallation" ).removeAttr( 'readonly' );
		$( 'input[name="billingAddressPojo.addressType"]' ).removeAttr( 'readonly' );
	}
	else {
		$( "#DIVStateCityBilling" ).removeClass( 'hide1' );
		$( '#billingAddress :input' ).removeAttr( 'readonly' );
	}
}

function updateBillAddL3(){
	var city = $( '#billingCity' ).val();
	var state = $( '#billingState' ).val();
	if ( city != null && city != '0' ) {
		$( '#billAddLine3' ).val( city + ', ' + state );
		return;
	}
	alert( 'Please chose a city' );
}
$( document ).ready( function(){
	if ( $( '#connectionTypeCRF' ).val() == "Ind" || $( '#connectionTypeCRF' ).val() == "Prop" ) {
		$( '#AforIndividual' ).removeClass( 'hide1' );
		$( '#NAforIndividualProprietorship' ).addClass( 'hide1' );
		$( '#AforIndividual input' ).attr( 'disabled', false );
		$( '#NAforIndividualProprietorship input' ).attr( 'disabled', true );
		$( '#OtherNationalityDetails input, #OtherNationalityDetails select, #OtherNationalityDetails textarea' ).attr( 'disabled', false );
	}
	else {
		$( '#NAforIndividualProprietorship' ).removeClass( 'hide1' );
		$( '#AforIndividual' ).addClass( 'hide1' );
		$( '#AforIndividual input' ).attr( 'disabled', true );
		$( '#NAforIndividualProprietorship input' ).attr( 'disabled', false );
		$( '#OtherNationalityDetails' ).addClass( 'hide1' );
		$( '#OtherNationalityDetails input, #OtherNationalityDetails select, #OtherNationalityDetails textarea' ).attr( 'disabled', true );
	}
	if ( $( '#connectionTypeCRF' ).val() == "0" ) {
		$( '#NAforIndividualProprietorship' ).addClass( 'hide1' );
		$( '#AforIndividual' ).addClass( 'hide1' );
		$( '#AforIndividual input' ).attr( 'disabled', true );
		$( '#NAforIndividualProprietorship input' ).attr( 'disabled', true );
		$( '#OtherNationalityDetails' ).addClass( 'hide1' );
		$( '#OtherNationalityDetails input, #OtherNationalityDetails select, #OtherNationalityDetails textarea' ).attr( 'disabled', true );
	}
	if ( $( '#nationalityTypeId' ).val() == "Indian" ) {
		$( '#OtherNationalityDetails' ).addClass( 'hide1' );
		$( '#otherNationality' ).addClass( 'hide1' );
		$( '#otherNationality :input' ).attr( 'disabled', true );
		$( '#OtherNationalityDetails input, #OtherNationalityDetails select, #OtherNationalityDetails textarea' ).attr( 'disabled', true );
	}
	else if ( $( '#nationalityTypeId' ).val() == "0" ) {
		$( '#OtherNationalityDetails' ).addClass( 'hide1' );
		$( '#otherNationality' ).addClass( 'hide1' );
		$( '#otherNationality :input' ).attr( 'disabled', true );
		$( '#OtherNationalityDetails input, #OtherNationalityDetails select, #OtherNationalityDetails textarea' ).attr( 'disabled', true );
	}
	else {
		$( '#OtherNationalityDetails input, #OtherNationalityDetails select, #OtherNationalityDetails textarea' ).attr( 'disabled', false );
		$( '#otherNationality' ).removeClass( 'hide1' );
		$( '#otherNationality :input' ).attr( 'disabled', false );
	}
} );

$( document ).ready( function(){
	if ( $( '#approveValidationCRF' ).is( ':checked' ) ) {
		$( '#approveValidationCRFShow' ).removeClass( 'hide1' );
		$( '#approveValidationCRFShow select' ).removeAttr( 'disabled' );
		$( '#rejectValidationCRFShow' ).addClass( 'hide1' );
		$( '#rejectValidationCRFShow select' ).attr( 'disabled', 'disabled' );
		$( '#instAddressShow' ).addClass( 'hide1' );
	}
	else if ( $( '#rejectValidationCRF' ).is( ':checked' ) ) {
		$( '#rejectValidationCRFShow' ).removeClass( 'hide1' );
		$( '#rejectValidationCRFShow select' ).removeAttr( 'disabled' );
		$( '#approveValidationCRFShow' ).addClass( 'hide1' );
		$( '#approveValidationCRFShow select' ).attr( 'disabled', 'disabled' );
		$( '#instAddressShow' ).addClass( 'hide1' );
	}
	else if ( $( '#changeFeasibleAddId' ).is( ':checked' ) ) {
		$( '#instAddressShow' ).removeClass( 'hide1' );
		$( '#rejectValidationCRFShow' ).addClass( 'hide1' );
		$( '#rejectValidationCRFShow select' ).attr( 'disabled', 'disabled' );
		$( '#approveValidationCRFShow' ).addClass( 'hide1' );
		$( '#approveValidationCRFShow select' ).attr( 'disabled', 'disabled' );
	}
	$( '[name="planType"]' ).click( function(){
		$( '#addOnDulValueCRF' ).attr( 'disabled', true );
		$( '#addOnDulValueCRF' ).addClass( 'gray_text' );
		$( '#basePlanNameCRF' ).removeClass( 'gray_text' );
		$( '#basePlanNameCRF' ).removeAttr( 'disabled', false );
		planType( $( this ).val() );
		setPlanAmounts( 'securityChrgeId', 'rentalChargeId', 'amountId', 'total', 0 );
	} );
} );

function planType( planTypeValue ){
	var product = $( 'input[name="customerDetailsPojo.product"]' ).val();
	var serviceType = $( 'input[name="customerDetailsPojo.serviceType"]' ).val();
	var id = document.getElementById( "basePlanNameCRF" );
	// $( '.loadingPopup' ).removeClass( 'hidden' );
	crmDwr.getActivationPlan( product, serviceType, planTypeValue, function( list ){
		if ( list.length > 0 ) {
			dwr.util.removeAllOptions( id );
			dwr.util.addOptions( id, [
				"Select Base Plan Name"
			] );
			dwr.util.addOptions( id, list, "planCode", "planName" );
		}
		else {
			dwr.util.removeAllOptions( id );
			dwr.util.addOptions( id, [
				"Select Base Plan Name"
			] );
			alert( "PlanType " + planTypeValue + " is not available in master plan" );
		}
		// $( '.loadingPopup' ).addClass( 'hidden' );
	} );
}

function changeTab( val ){
	if ( val == 'ci' ) {
		$( 'a.tabOne' ).addClass( "active" );
		$( 'a.tabOne' ).parent().siblings().children().removeClass( "active" );
	}
	else if ( val == 'op' ) {
		$( 'a.tabTwo' ).addClass( "active" );
		$( 'a.tabTwo' ).parent().siblings().children().removeClass( "active" );
	}
	else if ( val == 'dod' ) {
		$( 'a.tabThree' ).addClass( "active" );
		$( 'a.tabThree' ).parent().siblings().children().removeClass( "active" );
	}
	else if ( val == 'ai' ) {
		$( 'a.tabFour' ).addClass( "active" );
		$( 'a.tabFour' ).parent().siblings().children().removeClass( "active" );
	}
}

function fillBpCode( inDestinationID, inPartnerId ){
	crmDwr.getPartnerById( inPartnerId, function( partner ){
		addBpCode( inDestinationID, partner );
	} );
	function addBpCode( id, partner ){
		if ( partner != null ) {
			dwr.util.setValue( id, partner.crmPartnerCode );
			fillAssociateSR( inPartnerId );
		}
		else {
			dwr.util.setValue( id, "" );
			fillAssociateSR( inPartnerId );
		}
	}
}

function fillSrCode( inDestinationID, userId ){
	crmDwr.getUserById( userId, function( user ){
		addSrCode( inDestinationID, user );
	} );
	function addSrCode( id, user ){
		if ( user != null ) {
			dwr.util.setValue( id, user.srCode );
		}
		else {
			dwr.util.setValue( id, "" );
		}
	}
}

function saveCrfRemarks(){
	var remarks = document.getElementById( "remarksInaSave" ).value;
	if ( remarks.trim() == '' ) {
		$( '#errorRemarksSave' ).show().html( "Please provide Remarks" ).addClass( 'errorTextbox' );
	}
	else if ( remarks.trim().length < 2 || remarks.trim().length > 4000 ) {
		$( '#errorRemarksSave' ).show().html( "Please provide Remarks between [2-4000]." ).addClass( 'errorTextbox' ).css( 'width', 210 );
	}
	else if ( confirm( "Would you like to save the remarks ?" ) ) {
		document.crmCapForm.action = "crmCap.do?method=saveRemarks";
		document.crmCapForm.submit();
	}
}
function downloadDocuments( url, module, crfId, sysTime ){
	var popupString = "<a href='#' id='closePopup'>X</a>" + "<h4>Download Documents</h4>" + "<iframe src='" + url + encodeURIComponent( module )
			+ "/" + crfId
			// + "&DFlag=100000&SystemTime=" + sysTime + "&DocType="
			+ "'" + " scrolling='yes'	style='border: none; overflow: hidden; width: 100%; height: 600px;'></iframe>";
	document.getElementById( "downloadDocId" ).innerHTML = popupString;
	$( '.modelPopupDiv, .overlayDiv' ).fadeIn();
	$( '#closePopup' ).click( function( e ){
		$( '.modelPopupDiv, .overlayDiv' ).fadeOut();
	} );
}
function validateCompany( value ){
	var flag = true;
	$( '#companyNameCRF' ).next( 'font' ).hide();
	if ( ( $( value ).val() == "" ) || !isNaN( value.value ) ) {
		$( '#companyNameCRF' ).next( 'font' ).show().html( "Company name must have atleast 1 alphabet" ).addClass( 'errorTextboxSpl' );
		flag = false;
	}
	return flag;
}

// //////////////cancel CRF
function checkValidCRF( crfNo ){
	$( '#submitCancelCRFDiv' ).css( "display", "none" );
	var flag = true;
	$( '#linkageCRF' ).next( 'font' ).addClass( 'hide1' );
	if ( crfValidation() ) {
		crfId_Text = document.getElementById( "hiddenCrfId" ).value;
		if ( crfId_Text == crfNo ) {
			$( '#linkageCRF' ).next( 'font' ).show().html( 'CAF Number and Link to CAF Number should not be same !' ).removeClass( 'hide1' )
					.addClass( 'errorTextboxInDiv' ).css( 'width', 320 );
			// $( '#linkageCRF' ).focus();
			return false;
		}
		else {
			crmDwr.checkValidCRFNo( crfNo, function( result ){
				if ( result == "0" ) {
					$( '#linkageCRF' ).next( 'font' ).show().html( 'Please provide a valid CAF ID !' ).removeClass( 'hide1' ).addClass(
							'errorTextbox' );
					// $( '#linkageCRF' ).val( '' );
					// $( '#linkageCRF' ).focus();
					flag = false;
				}
				else if ( result == "2" ) {
					$( '#linkageCRF' ).next( 'font' ).show().html( 'CAF ID already Linked with other CAF !' ).removeClass( 'hide1' ).addClass(
							'errorTextbox' );
					// $( '#linkageCRF' ).val( '' );
					// $( '#linkageCRF' ).focus();
					flag = false;
				}
				else if ( result == "3" ) {
					$( '#linkageCRF' ).next( 'font' ).show().html( 'Provided CAF ID already Punched !' ).removeClass( 'hide1' ).addClass(
							'errorTextbox' );
					// $( '#linkageCRF' ).val( '' );
					// $( '#linkageCRF' ).focus();
					flag = false;
				}
				else {
					$( '#submitCancelCRFDiv' ).css( "display", "block" );
					flag = true;
				}

			} );
		}
	}
	else {
		flag = false;
	}
	return flag;
}
function showHideLinkToCRF( linkToOtherCRF ){
	if ( $( "#" + linkToOtherCRF ).attr( "checked" ) ) {
		$( "#linkToOtherCRFText" ).css( "display", "block" );
		$( "#submitCancelCRFDiv" ).css( "display", "none" );
		$( '#linkageCRF' ).focus();
	}
	else {
		$( "#linkToOtherCRFText" ).css( "display", "none" );
		$( "#submitCancelCRFDiv" ).css( "display", "block" );
	}
}
function submitCancelCRF(){

	if ( checkCancelCRFError() && confirm( "Please confirm if you want to cancel CAF !" ) ) {
		// fillINAHiddenFields();
		document.forms[ 1 ].action = "crmCap.do?method=cancelCRFAction";
		document.forms[ 1 ].submit();
	}
}
function fillINAHiddenFields(){
	parent.document.getElementById( "crfCancelReasonHidden" ).value = $( "#crfCancelReason" ).val();
	parent.document.getElementById( "linkageCRFHidden" ).value = $( "#linkageCRF" ).val();
	parent.document.getElementById( "ftr_remarks" ).value = $( "#remarks" ).val();

}
function checkCancelCRFError(){
	if ( $( "#linkageCRF" ).val() != null && $( "#linkageCRF" ).val() != undefined ) {
		$( '#linkageCRF' ).next( 'font' ).addClass( 'hide1' );
	}
	$( '#crfCancelReason' ).next( 'font' ).addClass( 'hide1' );
	$( '#remarks' ).next( 'font' ).addClass( 'hide1' );
	var crfId_Text = document.getElementById( "hiddenCrfId" ).value;
	var flag = true;
	if ( $( "#crfCancelReason" ).val() == null || $( "#crfCancelReason" ).val() == "" ) {
		$( '#crfCancelReason' ).next( 'font' ).show().html( 'Please Select CAF Cancellation Reason.' ).removeClass( 'hide1' ).addClass(
				'errorTextboxInDiv' ).css( 'top', 52 );
		flag = false;
	}
	if ( $( "#linkToOtherCRF" ).val() != null && $( "#linkToOtherCRF" ).val() != undefined ) {
		if ( flag ) {
			if ( $( "#linkToOtherCRF" ).attr( "checked" ) ) {
				if ( $( "#linkageCRF" ).val() == null || $( "#linkageCRF" ).val() == "" ) {
					$( '#linkageCRF' ).next( 'font' ).show().html( 'Please enter CAF Number.' ).removeClass( 'hide1' ).addClass( 'errorTextbox' );
					flag = false;
				}
				if ( flag && !crfValidation() ) {
					flag = false;
				}

				if ( crfId_Text == $( "#linkageCRF" ).val() ) {
					$( '#linkageCRF' ).next( 'font' ).show().html( 'CAF Number and Link To CAF Number should not be same !' ).removeClass( 'hide1' )
							.addClass( 'errorTextboxInDiv' ).css( 'width', 320 );
					// $( '#linkageCRF' ).focus();
					return false;
				}

				// if ( flag && !checkValidCRF( $( "#linkageCRF" ).val() ) ) {
				// flag = false;
				// }

			}
			else {
				$( "#linkageCRF" ).val( '' );
			}
		}
	}
	if ( flag && $( '#remarks' ) !== null ) {
		var remarks = $( '#remarks' ).val();
		if ( remarks.trim().length < 2 || remarks.trim().length > 4000 ) {
			$( '#remarks' ).next( 'font' ).show().html( 'Please enter Remarks between [2-4000].' ).removeClass( 'hide1' ).addClass( 'errorTextbox' );
			flag = false;
		}
	}
	return flag;
}
function crfValidation(){
	$( '#linkageCRF' ).next( 'font' ).removeClass( 'errorTextbox' );
	$( '#linkageCRF' ).next( 'font' ).addClass( 'hide1' );
	if ( $( "#linkageCRF" ).val().trim() != null && $( "#linkageCRF" ).val().trim() == "" ) {
		$( '#linkageCRF' ).next( 'font' ).show().html( 'Please enter CAF Number.' ).removeClass( 'hide1' ).addClass( 'errorTextbox' );
		return false;
	}
	else if ( $( "#linkageCRF" ).val().trim() != "" && !validateCRF( $( "#linkageCRF" ).val() ) ) {
		$( '#linkageCRF' ).next( 'font' ).show().html( "CAF number maximum 8 characters allowed." ).removeClass( 'hide1' ).addClass( 'errorTextbox' )
				.css( 'width', 320 );
		return false;
	}
	else {
		return true;
	}
}

function viewPaymentmodeSecurity( paymentModeSecurity ){
	if ( paymentModeSecurity == 'Q' ) {
		$( '#chequeSecurity' ).removeClass( 'hide1' );
		$( '#bankIdSecurity' ).removeClass( 'hide1' );
		$( '#chequeSecurity input, #bankIdSecurity' ).attr( 'disabled', false );
		$( '#cashSecurity, #onlinePaymentSecurity' ).addClass( 'hide1' );
		$( '#cashSecurity input, #onlinePaymentSecurity input' ).attr( 'disabled', true );
	}
	else if ( paymentModeSecurity == 'C' ) {
		$( '#cashSecurity' ).removeClass( 'hide1' );
		$( '#cashSecurity input' ).attr( 'disabled', false );
		$( '#chequeSecurity, #onlinePaymentSecurity,#bankIdSecurity' ).addClass( 'hide1' );
		$( '#chequeSecurity input, #onlinePaymentSecurity input,#bankIdSecurity' ).attr( 'disabled', true );
	}
	else if ( paymentModeSecurity == 'O' || paymentModeSecurity == "NEFT" ) {
		$( '#onlinePaymentSecurity' ).removeClass( 'hide1' );
		$( '#onlinePaymentSecurity input' ).attr( 'disabled', false );
		$( '#chequeSecurity, #cashSecurity, #bankIdSecurity' ).addClass( 'hide1' );
		$( '#chequeSecurity input, #cashSecurity input, #bankIdSecurity' ).attr( 'disabled', true );
	}
	else {
		$( '#chequeSecurity, #cashSecurity, #onlinePaymentSecurity,#bankIdSecurity' ).addClass( 'hide1' );
		$( '#chequeSecurity input, #cashSecurity input, #onlinePaymentSecurity input,#bankIdSecurity' ).attr( 'disabled', true );
	}
}
function fillAssociateSR( partnerId ){
	var product = $( '#hiddProduct' ).val();
	var id = document.getElementById( "srCodeId" );
	var srCode = document.getElementById( "srCode" );
	crmDwr.getAssociatedSRWithBP( product, partnerId, function( userList ){
		addAssociateSRWithBP( id, userList );
	} );
	function addAssociateSRWithBP( id, userList ){
		if ( userList != null ) {
			dwr.util.removeAllOptions( id );
			dwr.util.setValue( srCode, "" );
			dwr.util.addOptions( id, [
				{
					value : '0', name : 'Please Select'
				}
			], 'value', 'name' );
			dwr.util.addOptions( id, userList, "userId", "fullName" );
			// fillSrCode(srCode,id.value);
		}
		else {
			dwr.util.removeAllOptions( id );
			dwr.util.addOptions( id, [
				{
					value : '0', name : 'Please Select'
				}
			], 'value', 'name' );
			dwr.util.setValue( srCode, "" );
		}
	}

}
