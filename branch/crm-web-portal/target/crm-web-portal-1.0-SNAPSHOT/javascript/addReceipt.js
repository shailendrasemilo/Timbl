$( document ).ready( function(){
	$.validator.addMethod( 'crmRcaReason.category', function( value ){
		return ( value != '0' );
	} );
	$.validator.addMethod( 'crmRcaReason.subCategory', function( value ){
		return ( value != '' );
	} );
	$.validator.addMethod( 'crmRcaReason.subSubCategory', function( value ){
		return ( value != 'Please select' );
	} );
	$.validator.addMethod( 'prefix', function( value ){
		return ( value != '' );
	} );
	$.validator.addMethod( 'startRangeString', function( value ){
		return ( value != '' || value != '0' );
	} );
	$.validator.addMethod( 'endRangeString', function( value ){
		return ( value != '' || value != '0' );
	} );

	$( "#viewReceiptCRF" ).validate( {
		rules : {
			'crmRcaReason.subCategory' : {
				'crmRcaReason.subCategory' : true
			}, 'crmRcaReason.subSubCategory' : {
				'crmRcaReason.subSubCategory' : true
			}, 

		}, messages : {
			'crmRcaReason.subSubCategory' : {
				'crmRcaReason.subSubCategory' : "<div  class='category'>Please select 'Sub Sub Category'</div>"
			}, 'crmRcaReason.subCategory' : {
				'crmRcaReason.subCategory' : "<div  class='category'>Please select 'Sub Category'</div>"
			}, 
		}
	} );

	/*
	 * $("#addReceiptCRF").validate({ rules: { 'prefix':{ required:true, alphaName:true }, 'startRangeString':{ required:true, digits:true, maxlength:6 }, 'endRangeString':{ required:true, digits:true, maxlength:6 } }, messages: { 'prefix':{ required:"<font class='errorCreateUser'> Please provide
	 * prefix of receipt</font>", alphaName: "<font class='errorCreateUser'> Please provide only alphabets</font>" }, 'startRangeString':{ required:"<font class='errorCreateUser'> Please provide start range of receipt</font>", digits: "<font class='errorCreateUser'> Please provide only digit</font>",
	 * maxlength: "<font class='errorCreateUser'> Please provide only 6 digit long</font>" }, 'endRangeString':{ required:"<font class='errorCreateUser'> Please provide end range of receipt</font>", digits: "<font class='errorCreateUser'> Please provide only digit</font>", maxlength: "<font
	 * class='errorCreateUser'> Please provide only 6 digit long</font>" } } });
	 */

	$( 'input[name="startRangeString"], input[name="endRangeString"]' ).keyup( function(){
		value = $( this );
		value_regex = /^[0-9]{1,}$/i;
		if ( !value_regex.test( value.val() ) ) {
			$( this ).next( 'font' ).show().html( ' Please enter only digit' ).addClass( 'errorTextbox' );
		}
		else {
			$( this ).next( 'font' ).hide();
		}
	} );

	$( '#submit_viewReceipt' ).click( function(){
		if ( $( "#viewReceiptCRF" ).valid() ) {
			var valid = true;

			// if ( !$( 'input[name="startRangeString"]' ).val() || !$( 'input[name="endRangeString"]' ).val() ) {
			if ( !$( 'input[name="startRangeString"]' ).val() ) {
				$( 'input[name="startRangeString"]' ).next( 'font' ).show().html( "Please enter 'Start Range'" ).addClass( 'errorTextbox' );
				valid = false;
			}
			else if ( $( 'input[name="startRangeString"]' ).val() != "" && parseInt( $( 'input[name="startRangeString"]' ).val() ) < 1 ) {
				$( 'input[name="startRangeString"]' ).next( 'font' ).show().html( "Please enter valid 'Start Range'" ).addClass( 'errorTextbox' );
				valid = false;
			}
			else {
				$( 'input[name="startRangeString"]' ).keyup();
			}
			if ( !$( 'input[name="endRangeString"]' ).val() ) {
				$( 'input[name="endRangeString"]' ).next( 'font' ).show().html( "Please enter 'End Range'" ).addClass( 'errorTextbox' );
				valid = false;
			}
			else if ( $( 'input[name="endRangeString"]' ).val() != "" && parseInt( $( 'input[name="endRangeString"]' ).val() ) < 1 ) {
				$( 'input[name="endRangeString"]' ).next( 'font' ).show().html( "Please enter valid 'End Range'" ).addClass( 'errorTextbox' );
				valid = false;
			}
			else {
				$( 'input[name="endRangeString"]' ).keyup();
			}
			// }
			// else {
			if ( valid ) {
				var reply = confirm( "Do you want to save the data ?" );
				if ( reply ) {
					document.forms[ 1 ].action = "crmRcaReason.do?method=addReceipt";
					document.forms[ 1 ].submit();
				}
			}
			// }
		}
	} );

	$( '#submit_search' ).click( function(){
		if ( $( "#viewReceiptCRF" ).valid() ) {
			$( '.loadingPopup' ).removeClass( 'hidden' );
			document.forms[ 1 ].action = "crmRcaReason.do?method=searchCategoryValues&requestPage1=recipetPage";
			document.forms[ 1 ].submit();
		}
	} );
} );
function addReceiptPage(){
	document.getElementById( "requestPageId" ).value = "addReciept";
	document.forms[ 1 ].action = "crmRcaReason.do?method=viewAddReceiptPage";
	document.forms[ 1 ].submit();
	// window.open('crmRcaReason.do?method=viewAddReceiptPage&value=addReciept', 'newWindow','width=1100,height=280,scrollbars=yes,resizable=no,toolbar=no');
}
function addCRFPage(){
	document.getElementById( "requestPageId" ).value = "addCRF";
	document.forms[ 1 ].action = "crmRcaReason.do?method=viewAddReceiptPage";
	document.forms[ 1 ].submit();
	// window.open('crmRcaReason.do?method=addCRFPage&value=addCRF', 'newWindow','width=1100,height=280,scrollbars=yes,resizable=no,toolbar=no');
}
function showDetails(){
	window.open( 'crmRcaReason.do?method=showDetails', 'newWindow', 'width=1000,height=450,scrollbars=yes,resizable=no,toolbar=no' );
}
function changePrefix( receiptSubSubCategory ){
	// $( '#prefixId_select select' ).attr( 'disabled', true );
	// $( '#prefixId_select' ).addClass( 'hide1' );

	// $( '#prefixId_text' ).attr( 'disabled', false );
	// $( '#prefixId_text' ).removeClass( 'hide1' );

	/*	if ( receiptSubSubCategory == "EOC" ) {
		document.getElementById( "prefixId_text" ).value = "EA";
	}
	else if ( receiptSubSubCategory == "BB" ) {
		document.getElementById( "prefixId_text" ).value = "RA";
	}
	else if ( receiptSubSubCategory == "RF" ) {
		document.getElementById( "prefixId_text" ).value = "RA";
	}*/
	if ( receiptSubSubCategory == "T" ) {
		document.getElementById( "prefixId_text" ).value = "T";
	}
	else if ( receiptSubSubCategory == "E" ) {
		document.getElementById( "prefixId_text" ).value = "E";
	}
	else {
		document.getElementById( "prefixId_text" ).value = "";
	}
	/*
	 * else { $( '#prefixId_text' ).attr( 'disabled', true ); $( '#prefixId_text' ).addClass( 'hide1' ); $( '#prefixId_select select' ).attr( 'disabled', false ); $( '#prefixId_select' ).removeClass( 'hide1' ); }
	 */
}
function getSubSubCategory( cat ){
	if ( cat == "CashReceipt" ) {
		// populateBussinessPartners( true );
		$( '#prefixIdPage' ).removeClass( 'hide1' );
		var map = {
			"T" : "Services"
		};
		addProduct( "receiptSubSubCategory", map );
	}
	if ( cat == "CAF" ) {
		// populateProduct();
		$( '#prefixIdPage' ).addClass( 'hide1' );
		
		var map = {
			"EOC" : "FTTX / FTTN"
		};
		addProduct( "receiptSubSubCategory", map );
	}
	if ( cat == "" ) {
		$( '#prefixIdPage' ).addClass( 'hide1' );
		
		var map = {
			"" : "Please select"
		};
		addProduct( "receiptSubSubCategory", map );
	}
}
function addProduct( id, list ){
	if ( list != null ) {
		dwr.util.removeAllOptions( id );
		dwr.util.addOptions( id, list );
		}
	else {
		alert( "No product registered in system." );
	}
}

function populateBussinessPartners( toSelect ){
	$( '.loadingPopup' ).removeClass( 'hidden' );
	crmDwr.getBussinessPartners( function( list ){
		addBussinessPartners( "receiptSubSubCategory", list );
	} );
	function addBussinessPartners( id, list ){
		var select = document.getElementById( id );
		if ( select != null ) {
			if ( list != null ) {
				dwr.util.removeAllOptions( id );
				if ( toSelect ) {
					dwr.util.addOptions( id, [
						"Please select"
					] );
				}
				dwr.util.addOptions( id, list, "partnerName", "partnerName" );
			}
			else {
				dwr.util.removeAllOptions( "receiptSubSubCategory" );
				dwr.util.addOptions( "receiptSubSubCategory", [
					"Please select"
				] );
				document.getElementById( "prefixId_text" ).value = "";
				alert( "No partner registered in system." );
			}
		}
	}
	$( '.loadingPopup' ).addClass( 'hidden' );
}

function populateProduct(){
	crmDwr.getProduct( function( list ){
		$( '.loadingPopup' ).removeClass( 'hidden' );
		addProduct( "receiptSubSubCategory", list );
	} );
	function addProduct( id, list ){
		if ( list != null ) {
			dwr.util.removeAllOptions( id );
			dwr.util.addOptions( id, [
				"Please select"
			] );
			dwr.util.addOptions( id, list, "contentValue", "contentName" );
		}
		else {
			alert( "No product registered in system." );
		}
	}
	$( '.loadingPopup' ).addClass( 'hidden' );
}
function changeReciptStatus( inRecordID, inStatus, catValue, catName, subcatName, subsubcatName ){
	document.getElementById( "categoryId" ).value = inRecordID;
	document.getElementById( "crfReceiptStatus" ).value = inStatus;
	document.getElementById( "catValue" ).value = catValue;
	document.getElementById( "catName" ).value = catName;
	var answer = confirm( "Please confirm if you want to change status?" );
	if ( answer ) {
		document.forms[ 1 ].action = "crmRcaReason.do?method=changeStatus";
		document.forms[ 1 ].submit();
	}
}