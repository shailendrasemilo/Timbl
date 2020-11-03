$( document ).ready(
		function(){
			isSocietyFesible();
			try {
				$( "input[type='text']" ).each( function(){
					$( this ).attr( "autocomplete", "off" );
				} );
			} catch ( e ) {
				console.log( e.getMessage() );
			}
			$( '.modeofcontact' ).hide();
			$( 'input:radio[name="appointmentPojo.modeOfContact"]' ).change( function(){
				var test = $( this ).val();
				$( ".modeofcontact" ).hide();
				$( "#" + test ).show();
			} );
			if ( $( 'input:radio[name="appointmentPojo.modeOfContact"]' ).is( ':checked' ) ) {
				var test = $( 'input[name="appointmentPojo.modeOfContact"]:checked' ).val();
				$( ".modeofcontact" ).hide();
				$( "#" + test ).show();
			}
			/*
			 * $('.tcal').click(function(e){
			 * 
			 * e.preventDefault(); e.stopPropagation(); $('#tcal').show();
			 * 
			 * }); $(document).click(function() { $('#tcal').hide(); });
			 */
			$( 'span.Lmserror_message' ).hide();
			$( 'span.errorAppointmentDate' ).hide();
			$( 'span.Lmserror_optionmessage' ).hide();
			$( 'span.errorProductInterested' ).hide();
			$( 'span.errorRemarks' ).hide();
			$( 'span.errorDiffAppointmentDate' ).hide();
			$( 'span.actionPerform' ).hide();
			$( 'span.errorCorrectTime' ).hide();
			if ( $( '#actionToBePerformed' ).val() != "" && $( '#actionToBePerformed' ).val() == 'ML' ) {
				$( '.leadCrfView' ).removeClass( 'hide1' );
				crfIDsArray.length = 0;
				crfIDsArray = $( '#crfIds' ).val().split( "," );
				crfIdDisplayArray = arrayRecreation();
				var y = crfIdDisplayArray.join( "" );
				document.getElementById( "showID" ).innerHTML = y;
			}
			/***********************************************************************************************************************************************************************************************************************************************************************************************
			 * $.validator.addMethod('appointmentPojo.contactNumber', function(value, element) { validateMobile(value); return this.optional(element)|| mobileValidated ; }); $.validator.addMethod('lmsPojo.contactNumber', function(value, element) { validateMobile(value); return
			 * this.optional(element)|| mobileValidated; }); $("#generateLms") .validate( { rules : { 'lmsPojo.product' : { required : true }, 'remarksPojo.remarks' : { required : true }, 'lmsPojo.emailId' : { email: true }, referralResources : { required : true }, 'lmsPojo.customerName':{
			 * minlength:3 }, 'lmsPojo.contactNumber':{ 'lmsPojo.contactNumber':true }, }, messages : { 'lmsPojo.product' : { required : "<div class='lmsProductInterested'> Please select product interested</div>" }, 'remarksPojo.remarks' : { required : "<font class='addressError'>Please enter
			 * remarks</font>" }, referralResources : { required : "<font class='Lms_Reference'>Please select where did you hear about us?</font>" }, 'lmsPojo.emailId': { email : "<font class='Lms_Reference'>Please enter correct email id</font>" }, 'appointmentPojo.contactNumber': {
			 * 'appointmentPojo.contactNumber' : "<font class='errorSocietyText'>Please provide correct mobile number</font>" }, 'lmsPojo.contactNumber': { 'lmsPojo.contactNumber' : "<font class='errorSocietyText'>Please provide correct mobile number</font>" }, 'lmsPojo.customerName': {
			 * minlength : "<font class='errorSocietyText'>Name should be greater than 3 characters.</font>" } } });
			 * 
			 * 
			 * 
			 * $('#generateLmsMobile') .bind('onblur', function(){ mobile = $(this); validateMobile(mobile); });
			 **********************************************************************************************************************************************************************************************************************************************************************************************/

			$( '#generateLmsEmail' ).keyup( function(){
				email_address = $( this );
				email_regex = /^[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,4}$/i;
				// if
				// (!email_regex.test(email_address.val()))
				if ( !validateEmail( email_address.val() ) ) {
					var errorMsg = invalidErrorPrefix + " 'Email Id'";
					$showErrorMsg( 'generateLmsEmail', errorMsg );
				}
			} );

			$( 'ul.manageGIS li' ).click( function( e ){
				$( ".active" ).removeClass( "active" );
				$( this ).addClass( "active" );
			} );

			$( '#acknowledgeOthers' ).bind( 'change', function(){
				if ( $( '#acknowledgeOthers' ).is( ':checked' ) ) {
					$( '#othersModeofAck' ).removeClass( 'hide1' );
				}
				else {
					$( '#othersModeofAck' ).addClass( 'hide1' );
				}
			} );

			/*
			 * $('#appointmentDate').bind('change',function() { if ($('#appointmentDate').val() != "") { if (!$("input[name='appointmentPojo.modeOfContact']:checked").val()&& !$("input[name='appointmentPojo.appointmentTime']:checked").val()) { $('span.Lmserror_optionmessage').show(); return false; }
			 * else { $('span.Lmserror_optionmessage').hide(); }
			 * 
			 * if (!$("input[name='appointmentPojo.modeOfContact']:checked").val()) { $('span.errorAppointmentMode').show(); return false; } else { $('span.errorAppointmentMode').hide(); }
			 * 
			 * if (!$( "input[name='appointmentPojo.appointmentTime']:checked").val()) { $('span.errorAppointmentTime').show(); return false; } else { $('span.errorAppointmentTime').hide(); } }
			 * 
			 * });
			 */

			$( '#resetLmsAppointmentDetails' ).click( function(){
				$( '#appointmentDate' ).val( '' );
				$( '#idContactNo' ).val( '' );
				$( '#idAddress' ).val( '' );
				$( '#idApRemark' ).val( '' );
				$( '#appointmentId' ).val( '0' );
				$( "input[name='appointmentPojo.modeOfContact']" ).attr( 'checked', false );
				$( "input[name='appointmentPojo.appointmentTime']" ).attr( 'checked', false );
				$( "input[name='appointmentPojo.modeOfContact']" ).parent().removeClass( 'radio_on' );
				$( "input[name='appointmentPojo.appointmentTime']" ).parent().removeClass( 'radio_on' );
				$( 'span.errorAppointmentMode' ).hide();
				$( 'span.errorAppointmentTime' ).hide();
				$( 'span.errorAppointmentDate' ).hide();
				$( '.modeofcontact' ).hide();
			} );
			/*----------Reset all the fields of lead generation page--------------*/
			$( '#reset_generateLms' ).click( function(){
				$( "#generateLms" ).validate().resetForm();
				$( '.Lmserror_message' ).hide();
				$( "input[type=text], textarea" ).val( "" );
				$( "input[type=radio], input[type=checkbox]" ).attr( 'checked', false );
				$( "input[type=radio]" ).parent().removeClass( 'radio_on' );
				$( "input[type=checkbox]" ).parent().removeClass( 'check_on' );
				$( '#othersModeofAck' ).addClass( 'hide1' );
				$( 'span.errorAppointmentMode' ).hide();
				$( 'span.errorAppointmentTime' ).hide();
				$( 'span.errorAppointmentDate' ).hide();
				$( "select" ).attr( 'checked', false );
				resetSelect();
				// $("#citylms").empty();
				// $('#citylms').attr('disabled','disabled');
				$( '#citylms' ).attr( 'value', 'selected' );
				document.getElementById( 'error' ).innerHTML = "";
				$( '.modeofcontact' ).hide();
				document.getElementById( "otherReferralSource" ).innerHTML = "";
				// $('#otherReferralSource').hide();
			} );

			$( "select[id='actionToBePerformed']" ).bind( 'change', function(){
				$( 'span.closeReason' ).hide();
				if ( $( this ).val() == "CL" ) {
					$( '#forwardToBP' ).addClass( 'hide1' );
					$( '.leadCrfView' ).addClass( 'hide1' );
					$( '#actionToBePerformedCloseLead' ).removeClass( 'hide1' );
					$( '#forwardToAM' ).addClass( 'hide1' );
				}
				else if ( $( this ).val() == "ST" ) {
					$( '#actionToBePerformedCloseLead' ).addClass( 'hide1' );
					$( '.leadCrfView' ).addClass( 'hide1' );
					$( '#forwardToBP' ).removeClass( 'hide1' );
					$( '#forwardToAM' ).addClass( 'hide1' );
				}
				else if ( $( this ).val() == "AM" ) {
					if ( !$empty( $input( 'arealms' ) ) ) {
						getAreaManagersByArea( $input( 'arealms' ) );
					}
					$( '#actionToBePerformedCloseLead' ).addClass( 'hide1' );
					$( '.leadCrfView' ).addClass( 'hide1' );
					$( '#forwardToAM' ).removeClass( 'hide1' );
					$( '#forwardToBP' ).addClass( 'hide1' );
				}
				else if ( $( this ).val() == "ML" ) {
					$( '#forwardToBP' ).addClass( 'hide1' );
					$( '#actionToBePerformedCloseLead' ).addClass( 'hide1' );
					$( '.leadCrfView' ).removeClass( 'hide1' );
					$( '#forwardToAM' ).addClass( 'hide1' );
				}
				else {
					$( '#actionToBePerformedCloseLead' ).addClass( 'hide1' );
					$( '#forwardToBP' ).addClass( 'hide1' );
					$( '#forwardToAM' ).addClass( 'hide1' );
					$( '.leadCrfView' ).addClass( 'hide1' );
				}
			} );

			if ( $( "select[id='actionToBePerformed']" ).val() == "CL" ) {
				$( '#forwardToBP' ).addClass( 'hide1' );
				$( '.leadCrfView' ).addClass( 'hide1' );
				$( '#actionToBePerformedCloseLead' ).removeClass( 'hide1' );
			}
			else if ( $( "select[id='actionToBePerformed']" ).val() == "ST" ) {
				$( '#actionToBePerformedCloseLead' ).addClass( 'hide1' );
				$( '.leadCrfView' ).addClass( 'hide1' );
				$( '#forwardToBP' ).removeClass( 'hide1' );
			}
			else if ( $( "select[id='actionToBePerformed']" ).val() == "ML" ) {
				$( '#forwardToBP' ).addClass( 'hide1' );
				$( '#actionToBePerformedCloseLead' ).addClass( 'hide1' );
				$( '.leadCrfView' ).removeClass( 'hide1' );
			}

			/*
			 * $.validator.addMethod('customerDetailsPojo.crfId', function(value, element) { return this.optional(element) || /^[ra]{2}[0-9]{6}|[ea]{2}[0-9]{4}$/i .test(value); });
			 * 
			 * $("#searchCRF").validate({ rules: { 'customerDetailsPojo.crfId':{ required:true, 'customerDetailsPojo.crfId':true } }, messages: { 'customerDetailsPojo.crfId':{ required: "<font class='errorCreateUser'> Please insert CRF Number</font>", 'customerDetailsPojo.crfId': "<font
			 * class='errorCreateUser'>CRF number is not valid, Please validate again at your end</font>" } } });
			 */

			$( '#crfID' ).keyup(
					function(){
						value = $( this );
						if ( value.val() != "" ) {
							if ( !validateCRF( value.val() ) ) {
								$( 'font#crfIdError' ).show().html( ' CAF number is not valid, Please validate again at your end' ).addClass(
										'addServerSide-error' );
							}
							else {
								$( '#crfIdError' ).hide();
							}
						}
						else {
							$( '#crfIdError' ).hide();
						}

					} );

			$( '#generateIILLead' ).click( function(){
				var url = "http://entcrm.timbl.co.in/smart_oms/oms/New_Order/add_new_call.php";
				window.open( url, "_blank" );
				win.focus();
			} );

			/* ====================== Lead Ticket Hide/Show ====================== */
			$( '.inner_view_lead a.generateLeadTicketBtn' ).click( function(){
				$( '.inner_view_lead' ).addClass( 'hidden' );
				$( '.inner_view_lead :input' ).attr( 'disabled', true );
				$( '.leadTicketCreation' ).removeClass( 'hidden' );
				$( '.leadTicketCreation :input' ).removeAttr( 'disabled' );
				refreshLMSTicketValue();

			} );
			$( '.leadTicketCreation a.viewLeadBtn' ).click( function(){
				$( '.leadTicketCreation' ).addClass( 'hidden' );
				$( '.leadTicketCreation :input' ).attr( 'disabled', true );
				$( '.inner_view_lead' ).removeClass( 'hidden' );
				$( '.inner_view_lead :input' ).removeAttr( 'disabled' );

			} );

		} );

function resetLMSTktAutocomplete( subCat, subSubCat ){
	if ( subCat ) {
		subCats = [];
		$( '#lmsSubCategoryTextId' ).autocomplete( 'option', {
			source : subCats
		} );
		document.getElementById( 'lmsSubCategoryTextId' ).value = '';
	}
	if ( subSubCat ) {
		subSubCats = [];
		$( '#lmsSubSubCategoryTextId' ).autocomplete( 'option', {
			source : subSubCats
		} );
		document.getElementById( 'lmsSubSubCategoryTextId' ).value = '';
	}
}
function refreshLMSTicketValue(){
	crmDwr.filterSubCategories( "", function( subCategoryList ){
		subCats = [];
		var cat;
		if ( subCategoryList != null ) {
			subCategoryList.forEach( function( e ){
				if ( 'LMS' == e.moduleType ) {
					cat = {};
					cat[ 'label' ] = e.qrcSubCategory;
					cat[ 'value' ] = e.qrcSubCategoryId;
					subCats.push( cat );
				}

			} );
		}
		$( '#lmsSubCategoryTextId' ).autocomplete( {
			source : subCats, minLength : 0, change : function( e, ui ){
				resetLMSTktAutocomplete( false, true );
				$( '#lmsCategoryId' ).val( '' );
				$( '#lmsSubCategoryId' ).val( '' );
				$( '#lmsSubSubCategoryId' ).val( '' );
				if ( !ui.item ) {
					$( e.target ).val( '' );
				}
				else {
					$( '#lmsSubCategoryId' ).val( ui.item.value );
					crmDwr.getCategoryIdBySubcategory( ui.item.value, function( categoryId ){
						if ( categoryId != null && categoryId != "0" ) {
							$( '#lmsCategoryId' ).val( categoryId );
							fillLMSSubSubCategories( categoryId, ui.item.value );
						}
					} );
				}
			}, select : function( e, ui ){
				e.preventDefault();
				$( e.target ).val( ui.item.label );
			}, focus : function( e, ui ){
				return false;
			}
		} );
	} );
}

function fillLMSSubSubCategories( catId, subCatId ){
	crmDwr.filterSubSubCategories( catId, subCatId, "", function( subSubCategoryList ){
		subSubCats = [];
		if ( subSubCategoryList != null ) {
			var subSubCat;
			subSubCategoryList.forEach( function( e ){
				if ( 'LMS' == e.moduleType ) {
					subSubCat = {};
					subSubCat[ 'label' ] = e.qrcSubSubCategory;
					subSubCat[ 'value' ] = e.qrcSubSubCategoryId;
					subSubCats.push( subSubCat );
				}
			} );
		}
		$( '#lmsSubSubCategoryTextId' ).autocomplete( {
			source : subSubCats, minLength : 0, change : function( e, ui ){
				$( '#lmsSubSubCategoryId' ).val( '' );
				if ( !ui.item ) {
					$( e.target ).val( '' );
				}
				else {
					$( '#lmsSubSubCategoryId' ).val( ui.item.value );
					populateLMSActions( 'resolutionType', ui.item.value );
				}
			}, select : function( e, ui ){
				e.preventDefault();
				$( e.target ).val( ui.item.label );
			}, focus : function( e, ui ){
				return false;
			}
		} );
	} );
}

function populateLMSActions( inDestinationID, inSubSubCategoryId ){
	$( '.rcaResolutionCodeDropdowns' ).addClass( 'hidden' );
	if ( inSubSubCategoryId != 'Select Sub SubCategory' ) {
		var categoryID = document.getElementById( "lmsCategoryId" ).value;
		var subCategoryID = document.getElementById( "lmsSubCategoryId" ).value;
		crmDwr.getQrcActionsList( categoryID, subCategoryID, inSubSubCategoryId, function( list ){
			addActions( inDestinationID, list );
		} );

		function addActions( id, list ){
			var select = document.getElementById( id );
			if ( select != null ) {
				if ( list != null ) {
					dwr.util.removeAllOptions( id );
					dwr.util.addOptions( id, [
						"Please Select"
					] );
					dwr.util.addOptions( id, list, "contentValue", "contentName" );

				}
				else {
					alert( "Actions List is empty for current selected sub-sub-category." );
					removeList( id );
				}
			}
		}

		crmDwr.getQRCType( categoryID, subCategoryID, inSubSubCategoryId, {
			callback : function( qrcType ){
				document.getElementById( 'qrcType' ).value = qrcType;
			}
		} );

	}
}
function saveLMSTicket(){
	if ( emptyChecksLMSTkt() ) {
		if ( confirm( 'Are you sure to Generate LMS Ticket ?' ) ) {
			$( '.loadingPopup' ).removeClass( 'hidden' );
			document.forms[ 1 ].action = "leadGeneration.do?method=logLMSTicket";
			document.forms[ 1 ].submit();
		}
	}
}
function emptyChecksLMSTkt(){
	$( '#subCategoryError' ).addClass( 'hidden' );
	$( '#subSubCategoryError' ).addClass( 'hidden' );
	var isReceivedValidData = true;
	var callingNo = document.getElementById( 'callingNo' ).value;

	if ( callingNo === 0 ) {
		callingNo = '';
	}
	if ( $( '#lmsSubCategoryTextId' ).val() == null || $( '#lmsSubCategoryTextId' ).val() == '' ) {
		isReceivedValidData = false;
		$( '#subCategoryError' ).show().html( "Please enter sub Category" ).removeClass( 'hidden' ).addClass( 'errorTextbox' );
	}

	if ( $( '#lmsSubSubCategoryTextId' ).val() == null || $( '#lmsSubSubCategoryTextId' ).val() == '' ) {
		isReceivedValidData = false;
		$( '#subSubCategoryError' ).show().html( "Please enter sub Sub Category" ).removeClass( 'hidden' ).addClass( 'errorTextbox' );
	}

	if ( callingNo != "" && callingNo.length < 10 ) {
		isReceivedValidData = false;
		$( '#callingNo' ).next( 'font' ).show().html( "Please enter ten digits calling number." ).addClass( 'errorTextbox' ).css( 'width', 200 );
	}

	if ( $( '#remarks' ).val().trim() === '' || $( '#remarks' ).val().trim().length < 2 || $( '#remarks' ).val().trim().length > 4000 ) {
		isReceivedValidData = false;
		$( '#remarks' ).next( 'font' ).show().html( "Please enter Remarks between [2-4000]." ).addClass( 'errorTextArea' ).css( 'width', 200 );
	}
	if ( $( '#resolutionType' ).val() === 'Please Select' ) {
		isReceivedValidData = false;
		$( '#resolutionType' ).next( 'font' ).show().html( "Please select Action" ).addClass( 'errorTextbox' );
	}
	return isReceivedValidData;
}

function showCustomerDetails(){
	document.getElementById( 'CustomerDetails' ).style.display = 'block';
	document.getElementById( 'ActivityLog' ).style.display = 'none';
}

function showActivityLog(){
	document.getElementById( 'ActivityLog' ).style.display = 'block';
	document.getElementById( 'CustomerDetails' ).style.display = 'none';
}

function resetSelect(){
	var selects = document.getElementsByTagName( 'select' );
	var len = selects.length;
	for ( var i = 0; i < len; i++ ) {
		selects[ i ].selectedIndex = 0;
	}
}

function saveLead(){
	remarksEmptyCheck();
	appointmentChecks();
	nonEmptyChecks();
	if ( $input( 'actionToBePerformed' ) == '' && lmsPageErrorArray.length < 1 ) {
		if ( null != document.getElementById( "feasibilityLms" ) ) {
			document.getElementById( "hiddenFeasibility" ).value = document.getElementById( "feasibilityLms" ).value;
		}
		var answer = confirm( "Would you like to save the Lead?" );
		if ( answer ) {
			document.forms[ 1 ].action = "leadGeneration.do?method=modifyLeadDetails";
			document.forms[ 1 ].submit();
		}
	}
	else if ( $input( 'actionToBePerformed' ) != '' ) {
		var errorMsg = "You can not perform any action while saving Lead";
		$showErrorMsg( 'actionToBePerformed', errorMsg );
	}
}
function getAreaManagersByArea( areaName ){
	var stateName = document.getElementById( "statelms" ).value;
	var cityName = document.getElementById( "citylms" ).value;
	if ( document.getElementById( "amNotFound" ) != null ) {
		crmDwr.getUsersByParameter( "Area", stateName, cityName, areaName, "AM", function( userList ){
			var id = document.getElementById( "idAMs" );
			if ( userList != null ) {
				document.getElementById( "amNotFound" ).innerHTML = "";
				dwr.util.removeAllOptions( id );
				dwr.util.addOptions( id, userList );
			}
			else {

				dwr.util.removeAllOptions( id );
				document.getElementById( "amNotFound" ).innerHTML = "No Area-Manager present for provided Area.";
			}
		} );
	}
}
function getSocietyByPinCode( pinCode ){
	if ( !isNaN( pinCode ) && pinCode != null && pinCode != undefined && pinCode != '' && pinCode.length == 6 ) {
		$( '.loadingPopup' ).removeClass( 'hidden' );
		crmDwr.getSocietyByPinCode( pinCode, function( lmsSocieties ){
			var msg = null;
			if ( lmsSocieties != null ) {
				if ( lmsSocieties.state != null ) {
					document.getElementById( "statelms" ).value = lmsSocieties.state;
					populateCityByPinCode( 'citylms', lmsSocieties );
				}
				else {
					document.getElementById( "statelms" ).value = "";
					document.getElementById( "citylms" ).value = "";
					document.getElementById( "arealms" ).value = "";
					document.getElementById( "localitylms" ).value = "";
					isSocietyFesible();
					// alert( "No Area Mapped to the PIN Code - " + pinCode );
				}
			}
			else {
				document.getElementById( "statelms" ).value = "";
				document.getElementById( "citylms" ).value = "";
				document.getElementById( "arealms" ).value = "";
				document.getElementById( "localitylms" ).value = "";
				isSocietyFesible();
				msg = "Multiple states found for the provided pincode. Please ask admin to correct the data."
			}
			$( '.loadingPopup' ).addClass( 'hidden' );
			if ( msg != null ) {
				alert( msg );
			}
		} );
	}
	else if ( pinCode != '' && pinCode.length < 6 ) {
		alert( "Please provide valid PIN Code." );
	}
}

function populateCityByPinCode( id, lms ){
	crmDwr.getCityByStateName( lms.state, function( list ){
		addCity( id, list );
	} );
	function addCity( id, list ){
		if ( list != null ) {
			dwr.util.removeAllOptions( id );
			dwr.util.addOptions( id, [
				"All Cities"
			] );
			dwr.util.addOptions( id, list, "cityName", "cityName" );
			if ( lms.city != null ) {
				document.getElementById( "citylms" ).value = lms.city;
				$( '#citylms' ).triggerHandler( 'change' );
			}
			else {
				document.getElementById( "citylms" ).value = "All Cities";
			}
			if ( lms.area != null ) {
				document.getElementById( "arealms" ).value = lms.area;
				societiesByLocality( lms.area );
			}
			else {
				document.getElementById( "arealms" ).value = "";
			}
			document.getElementById( "localitylms" ).value = '';
			if ( lms.locality != null ) {
				if ( lms.society != null && lms.society != '' ) {
					document.getElementById( "localitylms" ).value = lms.locality + " - " + lms.society;
				}
				else {
					document.getElementById( "localitylms" ).value = lms.locality;
				}
			}
		}
		else {
			alert( "No city registered in system for selected state." );
			removeList( id );
		}
		isSocietyFesible();
	}
}
var lmsAreas = [];
var lmsLocalities = [];
var lmsSocieties = [];

function resetAutocomplete( area, locality, society ){
	if ( area ) {
		lmsAreas = [];
		$( '#arealms' ).autocomplete( 'option', {
			source : lmsAreas
		} );
		document.getElementById( 'arealms' ).value = '';
	}
	if ( locality ) {
		lmsLocalities = [];
		$( '#localitylms' ).autocomplete( 'option', {
			source : lmsLocalities
		} );
		document.getElementById( 'localitylms' ).value = '';
	}

}

function areasByCity( city ){
	resetAutocomplete( true, true, true );
	var state = document.getElementById( "statelms" ).value;
	crmDwr.getFilterArea( state, city, null, function( areaList ){
		if ( areaList != null ) {
			areaList.forEach( function( a ){
				lmsAreas.push( a.area );
			} );
		}
		$( '#arealms' ).autocomplete( {
			source : lmsAreas, minLength : 0, change : function( e, ui ){
				console.log( ui );
				if ( !ui.item ) {
					// $( e.target ).val( '' );
					resetAutocomplete( false, true, true );
				}
				else {
					// get localities of area
					$( '#localitylms' ).val( '' );
					societiesByLocality( ui.item.value );
				}
			}, focus : function( e, ui ){
				return false;
			}
		} );
	} );
}
// function localitiesByArea( area ){
// resetAutocomplete( false, true, true );
// var state = document.getElementById( "statelms" ).value;
// var city = document.getElementById( "citylms" ).value;
// crmDwr.getFilterLocality( state, city, area, null, function( localityList ){
// var id = document.getElementById( "lmsLocality" );
// if ( localityList != null ) {
// localityList.forEach( function( l ){
// lmsLocalities.push( l.locality );
// } );
// }
// $( '#localitylms' ).autocomplete( {
// source : lmsLocalities, minLength : 0, change : function( e, ui ){
// console.log( ui );
// if ( !ui.item ) {
// // $( e.target ).val( '' );
// // resetAutocomplete( false, false, true );
// }
// else {
// // get societies of locality
// societiesByLocality( ui.item.value );
// }
// }, focus : function( e, ui ){
// return false;
// }
// } );
// } );
// }
function societiesByLocality( locality ){
	resetAutocomplete( false, false, true );
	var state = document.getElementById( "statelms" ).value;
	var city = document.getElementById( "citylms" ).value;
	var area = document.getElementById( "arealms" ).value;
	crmDwr.getFilterSociety( state, city, area, null, function( societyList ){
		if ( societyList != null ) {
			lmsSocieties = [];
			societyList.forEach( function( s ){
				lmsSocieties.push( s.searchName );
			} );
		}
		else {
			lmsSocieties = [];
			isSocietyFesible();
		}
		$( '#localitylms' ).autocomplete( {
			source : lmsSocieties, minLength : 0, change : function( e, ui ){
				console.log( ui );
				if ( !ui.item ) {
					// $( e.target ).val( '' );
				}
				isSocietyFesible();
			}, focus : function( e, ui ){
				return false;
			}
		} );
	} );
}
function fillGisList( filterId, textId, selectedValue, inListType ){
	var textElement = document.getElementById( textId );
	textElement.value = selectedValue;
	dwr.util.removeAllOptions( filterId );
	document.getElementById( inListType ).style.display = "none";
	textElement.focus();
}

function addLmsCRFId(){
	var productType = "";
	var radios = document.getElementsByName( "lmsPojo.product" );
	for ( var i = 0; i < radios.length; i++ ) {
		if ( radios[ i ].checked )
			productType = radios[ i ].value;
	}
	var lmsCrfId = document.getElementById( 'crfID' ).value;
	if ( lmsCrfId.trim() != "" ) {
		if ( validateCRF( lmsCrfId ) ) {
			crmDwr.crfIdMasterValidation( lmsCrfId, productType, function( found ){
				if ( found == "NotInMaster" ) {
					var errorMsg = "'CAF Number' " + lmsCrfId + " not found in CAF Master.";
					$showErrorMsg( 'crfIdBlock', errorMsg );
				}
				else if ( found == "InLms" ) {
					var errorMsg = "'CAF Number' " + lmsCrfId + " is already mapped with another Lead.";
					$showErrorMsg( 'crfIdBlock', errorMsg );
				}
				// else if ( found == "InINA" ) {
				// var errorMsg = "'CRF Number' " + lmsCrfId + " is already punched.";
				// $showErrorMsg( 'crfIdBlock', errorMsg );
				// }
				else {
					addMoreCRFId();
				}
			} );
		}
		else {
			var errorMsg = "'CAF Number' should be numeric 8 characters long.";
			$showErrorMsg( 'crfIdBlock', errorMsg );
		}

	}
	else {
		var errorMsg = "Please provide 'CAF Number'";
		$showErrorMsg( 'crfIdBlock', errorMsg );
	}
}
function crfIdAsPerProduct( lmsCrfId, productType ){
	var flag = false;
	// var startsWith = lmsCrfId.substr( 0, 2 );
	if ( !validateCRF( lmsCrfId ) /* || !isUpperCase( lmsCrfId ) */) {
		var errorMsg = "'CAF Number' should be numeric 8 characters long.";
		$showErrorMsg( 'crfIdBlock', errorMsg );
	}
	/*
	 * else if ( startsWith == "RA" && ( productType == "RF" || productType == "BB" ) ) { flag = true; } else if ( startsWith == "EA" && ( productType == "EOC" ) ) { flag = true; }
	 */
	else {
		flag = false;
		var errorMsg = "Please provide 'CAF Number' according to selected product";
		$showErrorMsg( 'crfIdBlock', errorMsg );
	}
	return flag;
}
var crfIdDisplayArray = new Array();
var crfIDsArray = new Array();
var index = 1, outerIndex = 1;

function addMoreCRFId(){
	var isBoolean = true;
	var crfID = document.getElementById( 'crfID' ).value;
	if ( crfID == "" ) {
		var errorMsg = provideErrorPrefix + " 'CAF Number'.";
		$showErrorMsg( 'crfIdBlock', errorMsg );
		isBoolean = false;
	}
	else if ( !validateCRF( crfID ) /* || !isUpperCase( crfID ) */) {
		var errorMsg = "'CAF Number' should be numeric and 8 characters long.";
		$showErrorMsg( 'crfIdBlock', errorMsg );
		isBoolean = false;
	}
	else if ( crfIDsArray.length > 0 ) {
		if ( checkDuplicateCRFId( crfID ) ) {
			isBoolean = false;
		}
	}
	if ( isBoolean ) {
		crfIDsArray.push( crfID );
		var fieldRest = '<span class="borderLeft"><input id="' + index + '" type="text" class="Lmstextbox" value="' + crfID
				+ '" readonly="true"/><a href="javascript:removeLmsCRFId(' + index
				+ ');" class="marginleft6"><img src="images/bg/delete.png" align="absmiddle" title="delete"></a></span>';
		var listFirst = '<li class="first">';
		var listSecond = '<li class="second">';
		var listEnd = '</li>';
		if ( index % 3 === 1 || index == 1 ) {
			if ( outerIndex % 2 === 1 || outerIndex == 1 ) {
				crfIdDisplayArray.push( listFirst + fieldRest );
			}
			else if ( outerIndex % 2 === 0 ) {
				crfIdDisplayArray.push( listSecond + fieldRest );
			}
			index++;
		}
		else if ( index % 3 == 0 ) {
			crfIdDisplayArray.push( fieldRest + listEnd );
			index++;
			outerIndex++;
		}
		else {
			crfIdDisplayArray.push( fieldRest );
			index++;
		}
		var y = crfIdDisplayArray.join( "" );
		document.getElementById( "showID" ).innerHTML = y;
		document.getElementById( 'crfID' ).value = "";
	}
}
function removeLmsCRFId( inCrfIdIndex ){
	var crfValue = document.getElementById( inCrfIdIndex ).value;
	var valueIndex = returnIndex( crfIDsArray, crfValue );
	crfIDsArray.splice( valueIndex, 1 );
	crfIdDisplayArray.splice( valueIndex, 1 );
	if ( index === 2 ) {
		index = 1;
		outerIndex = 1;
	}
	else if ( index % 3 == 2 || index % 3 == 0 ) {
		index--;
	}
	else if ( index % 3 == 1 ) {
		index--;
		outerIndex--;
	}
	crfIdDisplayArray = arrayRecreation();
	var y = crfIdDisplayArray.join( "" );
	document.getElementById( "showID" ).innerHTML = y;
}
function returnIndex( inArray, inValue ){
	for ( var i = 0; i < inArray.length; i++ ) {
		if ( inArray[ i ].trim() === inValue.trim() ) {
			return i;
		}
	}
}
function arrayRecreation(){
	var newArray = new Array();
	var listFirst = '<li class="first">';
	var listSecond = '<li class="second">';
	var listEnd = '</li>';
	var newIndex = 1, newOuterIndex = 1;
	for ( var i = 0; i < crfIDsArray.length; i++ ) {
		var str = crfIDsArray[ i ];
		var fieldRest = '<span class="borderLeft"><input id="' + newIndex + '" type="text" class="Lmstextbox" value="' + str
				+ '" readonly="true"/><a class="marginleft6" href="javascript:removeLmsCRFId(' + newIndex
				+ ');"><img src="images/bg/delete.png" align="absmiddle" title="delete"></a></span>';
		if ( newIndex % 3 === 1 || newIndex == 1 ) {
			if ( newOuterIndex % 2 === 1 || newOuterIndex == 1 ) {
				newArray.push( listFirst + fieldRest );
			}
			else if ( newOuterIndex % 2 === 0 ) {
				newArray.push( listSecond + fieldRest );
			}
			newIndex++;
		}
		else if ( newIndex % 3 == 0 ) {
			newArray.push( fieldRest + listEnd );
			newIndex++;
			newOuterIndex++;
		}
		else {
			newArray.push( fieldRest );
			newIndex++;
		}
	}
	return newArray;
}
function showOthersReferralSource( value ){
	var inputToShow = '<input type="text" class="Lmstextbox" id="otherSource" size="20" maxlength="128">';
	if ( value == 'Others' ) {
		if ( document.getElementById( "Others" ).checked ) {
			$( '#ReferaFriendCampaign, #Hoardings, #Website' ).parent().removeClass( "check_on" );
			$( '#ReferaFriendCampaign, #Hoardings, #Website' ).attr( 'checked', false );
			document.getElementById( "otherReferralSource" ).innerHTML = inputToShow;
		}
		else {
			document.getElementById( "otherReferralSource" ).innerHTML = "";
		}
	}
	else {
		$( '#Others' ).parent().removeClass( "check_on" );
		$( '#Others' ).attr( 'checked', false );
		document.getElementById( "otherReferralSource" ).innerHTML = "";
	}

}
function prepareReferralSourceStr(){
	$( 'span.Lms_Reference' ).hide();
	var x = document.getElementById( "referralSourcesPara" ).getElementsByTagName( 'INPUT' );

	var y = new Array();
	if ( document.getElementById( "Others" ).checked == true ) {
		if ( !$empty( $input( 'otherSource' ) ) ) {
			y.push( $input( 'otherSource' ) );
		}
		for ( var i = 0; i < x.length - 1; i++ ) {
			if ( x[ i ].value.trim() != "Others" ) {
				var z = x[ i ].checked;
				if ( z == true ) {
					var errorMsg = "You can't check options with Others.";
					$showErrorMsg( 'lmsReference', errorMsg );
					break;
				}
			}
		}
	}
	else {
		for ( var i = 0; i < x.length - 1; i++ ) {
			var z = document.getElementById( x[ i ].id ).checked;
			if ( z == true ) {
				if ( x[ i ].value != "Others" ) {
					y.push( x[ i ].value );
				}
			}
		}
	}
	document.getElementById( "otherRS" ).value = y.toString();
	return true;
}

function hideError(){
	$( 'span.selectState' ).hide();
	$( 'span.enterArea' ).hide();
	$( 'span.enterLocality' ).hide();
	$( 'span.enterSocity' ).hide();
	$( 'span.enterHouseNumber' ).hide();
	$( 'span.enterLandMark' ).hide();
	$( 'span.enterPincode' ).hide();
	$( 'span.enterCPincode' ).hide();
	$( 'span.customerName' ).hide();
}

function checkDuplicateCRFId( inCRFId ){
	var isDuplicate = false;
	for ( var i = 0; i < crfIDsArray.length; i++ ) {
		if ( crfIDsArray[ i ].trim() === inCRFId.trim() ) {
			var errorMsg = "Duplicate CAF Number, Please provide non-duplicate CAF Number.";
			$showErrorMsg( 'crfIdBlock', errorMsg );
			isDuplicate = true;
			break;
		}
	}
	return isDuplicate;
}
function validateAppointmentTiming(){
	$( 'span.errorCorrectTime' ).hide();
	var time = $( "input[name='appointmentPojo.appointmentTime']:checked" ).val();
	var Hours = new Date().getHours();
	var Minute = new Date().getMinutes();

	var hr = 0;

	if ( Minute > 0 ) {
		hr = Hours + 1;
	}
	else {
		hr = Hours;
	}
	var date = dateDiff( new Date(), new Date( $( '#appointmentDate' ).val() ) );
	if ( date == 0 ) {
		var errorMsg = selectErrorPrefix + " current or future Appointment Timing slot.";
		if ( time == 'M' && hr > 12 ) {
			$showErrorMsg( 'appointmentTiming', errorMsg );
		}
		else if ( time == 'A' && hr > 16 ) {
			$showErrorMsg( 'appointmentTiming', errorMsg );
		}
		else if ( time == 'E' && hr > 20 ) {
			$showErrorMsg( 'appointmentTiming', errorMsg );
		}
	}
}

window.onload = function(){
	document.onclick = function(){
		isSocietyFesible();
		var commonErrors = document.getElementById( 'commonErrors' );
		if ( null != commonErrors && commonErrors.value ) {
			document.getElementById( 'showAreaList' ).style.display = 'none';
			document.getElementById( 'showLocalityList' ).style.display = 'none';
			// document.getElementById( 'showSocietyList' ).style.display = 'none';
			$hideErrorMsg();
			onOffAppointment();
		}
		$hideErrorMsg();
	};
};
function isUpperCase( str ){
	return str == str.toUpperCase();
}