$( document ).ready(
		function(){
			$.validator.addMethod( 'ostDate', function( value, element ){
				return validateOutageStartDate( value );
			} );
			$.validator.addMethod( 'oetDate', function( value, element ){
				return this.optional( element ) || validateOutageETRDate( value );
			} );
			$.validator.addMethod( 'oetDateUpdate', function( value, element ){
				return validateOutageETRDateUpdate( value );
			} );
			$( '#addMassOutageForm' ).validate(
					{
						rules : {
							'massOutagePojo.outageType' : {
								required : true
							}, 'massOutagePojo.serviceName' : {
								required : true
							}, 'ostDate' : {
								'ostDate' : true
							}, 'oetDate' : {
								required : true, 'oetDate' : true
							}, 'massOutagePojo.reason' : {
								required : true
							}, 'massOutagePojo.remarks' : {
								required : true, minlength : 2, maxlength : 4000
							}
						},
						messages : {
							'massOutagePojo.outageType' : {
								required : '<font class="errorCheckbox">Please select Outage Type</font>'
							},
							'massOutagePojo.serviceName' : {
								required : '<font class="errorCheckbox">Please select Service Name</font>'
							},
							'ostDate' : {
								'ostDate' : '<font class="errorTextbox">Please select valid Start Date / Time [future Date / Time]</font>'
							},
							'oetDate' : {
								required : '<font class="errorTextbox">Please select ETR Date / Time</font>',
								'oetDate' : '<font class="errorTextbox">Please select valid ETR Date / Time</font>'
							},
							'massOutagePojo.reason' : {
								required : '<font class="errorTextbox">Please select Reason</font>',
							},
							'massOutagePojo.remarks' : {
								required : '<font class="errorAddress">Please enter Remarks between [2-4000]</font>',
								minlength : '<font class="errorAddress">Please enter Remarks between [2-4000]</font>',
								maxlength : '<font class="errorAddress">Please enter Remarks between [2-4000]</font>',
							}
						}
					} );

			$( '#updateMassOutageForm' ).validate( {
				rules : {
					'oetDate' : {
						'oetDateUpdate' : true
					},'massOutagePojo.reason' : {
						required : true
					}, 'massOutagePojo.remarks' : {
						required : true, minlength : 2, maxlength : 4000
					}
				}, messages : {
					'oetDate' : {
						'oetDateUpdate' : '<font class="errorTextbox">Please select valid ETR Date / Time</font>'
					},
					'massOutagePojo.reason' : {
						required : '<font class="errorTextbox">Please select Reason</font>',
					},
					'massOutagePojo.remarks' : {
						required : '<font class="errorAddress">Please enter Remarks between [2-4000]</font>',
						minlength : '<font class="errorAddress">Please enter Remarks between [2-4000]</font>',
						maxlength : '<font class="errorAddress">Please enter Remarks between [2-4000]</font>',
					}
				}
			} );
			
			$( '#resolveMassOutageForm' ).validate( {
				rules : {
					'massOutagePojo.reason' : {
						required : true
					}
				}, messages : {					
					'massOutagePojo.reason' : {
						required : '<font class="errorTextbox">Please select Reason</font>',
					}
				}
			} );
			
			$( "#searchMassOutage" ).click( function(){
				var date, hh, mm, ss;
				date = $( '#strDateID' ).val();
				hh = $( '#strHourID' ).val();
				mm = $( '#strMinuteID' ).val();
				ss = $( '#strSecondID' ).val();
				var strDateTime = date + " ";
				if ( hh != 0 && mm != 0 && ss != 0 ) {
					strDateTime = strDateTime + hh + ":" + mm + ":" + ss;
				}
				$( '#strDateTimeID' ).val( strDateTime );

				date = $( '#etrDateID' ).val();
				hh = $( '#etrHourID' ).val();
				mm = $( '#etrMinuteID' ).val();
				ss = $( '#etrSecondID' ).val();
				var etrDateTime = date + " ";
				if ( hh != 0 && mm != 0 && ss != 0 ) {
					etrDateTime = etrDateTime + hh + ":" + mm + ":" + ss;
				}
				$( '#etrDateTimeID' ).val( etrDateTime );
				$( '.loadingPopup' ).removeClass( 'hidden' );
				document.getElementById( "IDsearchMassOutage" ).action = "massOutage.do?method=searchMassOutage";
				$( "#IDsearchMassOutage" ).submit();

			} );

			/* ========== Submit Add Mass Outage ========== */
			$( '#submitAddMassOutage' ).click( function(){
				// set outage start time
				var date, hh, mm, ss;
				date = document.getElementById( 'ostDate' ).value;
				hh = document.getElementById( 'ostHH' ).value;
				mm = document.getElementById( 'ostMM' ).value;
				ss = document.getElementById( 'ostSS' ).value;
				var ostDateTime = date + " " + hh + ":" + mm + ":" + ss;
				document.getElementById( 'displayOstId' ).value = ostDateTime;
				// set outage etr time
				date = document.getElementById( 'oetDate' ).value;
				hh = document.getElementById( 'oetHH' ).value;
				mm = document.getElementById( 'oetMM' ).value;
				ss = document.getElementById( 'oetSS' ).value;
				var oetDateTime = date + " " + hh + ":" + mm + ":" + ss;
				document.getElementById( 'displayEtrId' ).value = oetDateTime;
				if ( $( '#addMassOutageForm' ).valid() && confirm( 'Are you sure to add Mass Outage?' ) ) {
					$( '#addMassOutageForm' ).submit();
				}
			} );

			/* ========== Submit Edit Mass Outage ========== */
			$( '#submitUpdateMassOutage' ).click( function(){
				var date, hh, mm, ss
				date = document.getElementById( 'oetDate' ).value;
				hh = document.getElementById( 'oetHH' ).value;
				mm = document.getElementById( 'oetMM' ).value;
				ss = document.getElementById( 'oetSS' ).value;
				var oetDateTime = date + " " + hh + ":" + mm + ":" + ss;
				document.getElementById( 'displayEtrId' ).value = oetDateTime;
				if ( $( '#updateMassOutageForm' ).valid() && confirm( 'Are you sure to update Mass Outage?' ) ) {
					$( '#updateMassOutageForm' ).submit();
				}
			} );
			
			$( '#submitResolveMassOutage' ).click( function(){
				if ($( '#resolveMassOutageForm' ).valid() && confirm( 'Are you sure to Resolve Mass Outage?' ) ) {
					$( '#resolveMassOutageForm' ).submit();
				}
			} );
		} );

function displayOutageTree( radio ){
	$( '.fbrOutage' ).addClass( 'hidden' );
	$( '.eocOutage' ).addClass( 'hidden' );
	$( '.rfOutage' ).addClass( 'hidden' );
	$( '.fbrOutage :input' ).attr( 'disabled', true );
	$( '.eocOutage :input' ).attr( 'disabled', true );
	$( '.rfOutage :input' ).attr( 'disabled', true );
	var $radio = $( radio );
	if ( $radio.val() == 'EOC' ) {
		$( '.eocOutage' ).removeClass( 'hidden' );
		$( '.eocOutage :input' ).removeAttr( 'disabled' );
	}
	else if ( $radio.val() == 'RF' ) {
		$( '.rfOutage' ).removeClass( 'hidden' );
		$( '.rfOutage :input' ).removeAttr( 'disabled' );
	}
	else if ( $radio.val() == 'BB' ) {
		$( '.fbrOutage' ).removeClass( 'hidden' );
		$( '.fbrOutage :input' ).removeAttr( 'disabled' );
	}
}

function toggleOutageStartTime( type ){
	if ( type == 'Planned' ) {
		$( '.outageStartTime' ).removeClass( 'hidden' );
		$( '.outageStartTime :input' ).removeAttr( 'disabled' );
	}
	else {
		$( '.outageStartTime' ).addClass( 'hidden' );
		$( '.outageStartTime :input' ).attr( 'disabled', true );
	}
}

function validateOutageStartDate( value ){
	console.log( "validateOutageStartDate :: " + value );
	var outageType = $( '[name="massOutagePojo.outageType"]:checked' ).val();
	if ( outageType != 'Planned' )
		return true;
	if ( value == '' )
		return false;

	var hh = $( '#ostHH' ).val();
	var mm = $( '#ostMM' ).val();
	var ss = $( '#ostSS' ).val();
	var isoDate = getISODate( value );
	isoDate.setHours( hh, mm, ss, 0 );
	var dayDiff = new Date() > isoDate;
	if ( dayDiff )
		return false;
	return true;
}

function validateOutageETRDate( value ){
	var serviceName = $( '[name="massOutagePojo.serviceName"]:checked' ).val();
	var date, hh, mm, ss;
	var ostDate = null;
	var oetDate = null;
	date = $( '#oetDate' ).val();
	hh = $( '#oetHH' ).val();
	mm = $( '#oetMM' ).val();
	ss = $( '#oetSS' ).val();
	oetDate = getISODate( date );
	oetDate.setHours( hh, mm, ss, 0 );
	if ( serviceName == 'Planned' ) {
		date = $( '#ostDate' ).val();
		hh = $( '#ostHH' ).val();
		mm = $( '#ostMM' ).val();
		ss = $( '#ostSS' ).val();
		ostDate = getISODate( date );
		ostDate.setHours( hh, mm, ss, 0 );
		if ( ostDate > oetDate )
			return false;
	}
	var dayDiff = new Date() > oetDate;
	if ( dayDiff )
		return false;
	return true;
}

function validateOutageETRDateUpdate( value ){
	if ( value == '' )
		return true;
	var hh, mm, ss;
	hh = $( '#oetHH' ).val();
	mm = $( '#oetMM' ).val();
	ss = $( '#oetSS' ).val();
	var oetDate = getISODate( value );
	oetDate.setHours( hh, mm, ss, 0 );
	if ( new Date() > oetDate )
		return false;

	var startDate = $( '#displayOstId' ).val();
	console.log( startDate + " :: " + value );
	var isoDate = getISODate( startDate.split( ' ' )[ 0 ] );
	var time = startDate.split( ' ' )[ 1 ].split( ':' );
	isoDate.setHours( time[ 0 ], time[ 1 ], time[ 2 ], 0 );
	console.log( 'outage start date :: ' + isoDate );
	if ( isoDate > oetDate )
		return false;

	return true;
}

function getMasterList( npId ){

	var product = $( "input[type='radio'][name='massOutagePojo.serviceName']:checked" ).val();

	if ( product == "" || product == undefined ) {
		alert( "Please select the service Type" );
		return false;
	}
	if ( npId != "" && npId != 0 && product != 'BB' ) {

		crmDwr.getNassPortList( npId, product, function( list ){
			addMasterList( "nassPortId", list );
		} );
	}

	function addMasterList( id, list ){

		if ( list != null ) {
			dwr.util.removeAllOptions( id );

			dwr.util.addOptions( id, [
				"Please select"
			] );

			dwr.util.addOptions( id, list, "contentValue", "contentName" );
		}
		else {
			alert( "No  list available" );
		}

	}

}

function getPartnerByType(){
	var product = $( "input[type='radio'][name='massOutagePojo.serviceName']:checked" ).val();
	// alert("product ***"+product);
	crmDwr.getPartnerByType( product, function( list ){
		addNetworkPartner( "networkPartner", list );
	} );
	function addNetworkPartner( id, list ){

		if ( list != null ) {
			dwr.util.removeAllOptions( id );

			dwr.util.addOptions( id, [
				"Please select"
			] );

			dwr.util.addOptions( id, list, "partnerId", "partnerName" );
		}
		else {
			alert( "No  list available" );
		}

	}

}
function showHideDivOutage(){

	var product = $( "input[type='radio'][name='massOutagePojo.serviceName']:checked" ).val();
	$( '#nasportDiv ' ).addClass( 'hidden' );
	$( '#gisDiv ' ).addClass( 'hidden' );

	if ( product == 'BB' ) {
		$( '#gisDiv ' ).removeClass( 'hidden' );
	}
	else {
		$( '#nasportDiv ' ).removeClass( 'hidden' );

	}
}
