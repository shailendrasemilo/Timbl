$( document ).ready( function(){

	/* == Adjustment Report Starts == */

	$( '#IDSubmitadjustment' ).click( function(){
		if ( $( '#IDAdjustmentReport' ) ) {

			var flag = DateValidityChecker( $( '#fromDate' ).val(), $( '#toDate' ).val(), 90 );
			if ( flag ) {
				$( '.loadingPopup' ).removeClass( 'hidden' );
				document.forms[ 1 ].action = "qrcReportAction.do?method=adjustmentReport";
				document.forms[ 1 ].submit();
			}
		}
	} );

	/* == Adjustment Report Ends == */

	/* == Churn Report Starts == */
	$( '#IDchurnSubmit' ).click( function(){
		if ( $( '#IDChurnReport' ) ) {

			var flag = DateValidityChecker( $( '#fromDate' ).val(), $( '#toDate' ).val(), 90 );
			if ( flag ) {
				$( '.loadingPopup' ).removeClass( 'hidden' );
				document.forms[ 1 ].action = "qrcReportAction.do?method=churnReport";
				document.forms[ 1 ].submit();
			}
		}
	} );

	/* == Churn Report Ends == */

	/* == Interaction Report Starts == */
	$( '#IDInteractionSubmit' ).click( function(){
		if ( $( '#IDInteractionReport' ) ) {

			var flag = DateValidityChecker( $( '#fromDate' ).val(), $( '#toDate' ).val(), 90 );
			if ( flag ) {
				$( '.loadingPopup' ).removeClass( 'hidden' );
				document.forms[ 1 ].action = "qrcReportAction.do?method=interactionReport";
				document.forms[ 1 ].submit();
			}
		}
	} );

	/* == Interaction Report Ends == */

	/* == Migration Report Starts == */

	$( '#IDMigrationSubmit' ).click( function(){
		if ( $( '#IDMigrationReport' ) ) {

			var flag = DateValidityChecker( $( '#fromDate' ).val(), $( '#toDate' ).val(), 90 );
			if ( flag ) {
				$( '.loadingPopup' ).removeClass( 'hidden' );
				document.forms[ 1 ].action = "qrcReportAction.do?method=migrationReport";
				document.forms[ 1 ].submit();
			}
		}
	} );

	/* == Migration Report Ends == */

	/* == Reactivation Report Starts == */

	$( '#IDReactivationSubmit' ).click( function(){
		if ( $( '#IDReactivationReport' ) ) {

			var flag = DateValidityChecker( $( '#fromDate' ).val(), $( '#toDate' ).val(), 90 );
			if ( flag ) {
				$( '.loadingPopup' ).removeClass( 'hidden' );
				document.forms[ 1 ].action = "qrcReportAction.do?method=reactivationReport";
				document.forms[ 1 ].submit();
			}
		}
	} );

	/* == Reactivation Report Ends == */

	/* == Validity Extension Report Starts == */
	$( '#IDValidityExtensionSubmit' ).click( function(){
		if ( $( '#IDValidityExtensionReport' ) ) {

			var flag = DateValidityChecker( $( '#fromDate' ).val(), $( '#toDate' ).val(), 90 );
			if ( flag ) {
				$( '.loadingPopup' ).removeClass( 'hidden' );
				document.forms[ 1 ].action = "qrcReportAction.do?method=validityExtensionReport";
				document.forms[ 1 ].submit();
			}
		}
	} );

	/* == Validity Extension Report Ends == */

	/* == Open Ticket Report Starts == */

	$( '#IDTicketOpenSubmit' ).click( function(){
		if ( $( '#IDOpenTicket' ) ) {
			$( '.loadingPopup' ).removeClass( 'hidden' );
			document.forms[ 1 ].action = "qrcReportAction.do?method=openTicketReport";
			document.forms[ 1 ].submit();
		}
	} );

	/* == Open Ticket Report Ends == */

	/* == Resolved Report Starts == */

	$( '#IDTicketResolvedSubmit' ).click( function(){
		if ( $( '#IDTicketResolved' ) ) {

			var flag = DateValidityChecker( $( '#fromDate' ).val(), $( '#toDate' ).val(), 90 );
			if ( flag ) {
				$( '.loadingPopup' ).removeClass( 'hidden' );
				document.forms[ 1 ].action = "qrcReportAction.do?method=resolvedTickets";
				document.forms[ 1 ].submit();
			}
		}
	} );

	/* == Resolved Report Ends == */

	/* == Tagging Report Starts == */

	$( '#IDTaggingSubmit' ).click( function(){
		if ( $( '#IDTagging' ) ) {

			var flag = DateValidityChecker( $( '#fromDate' ).val(), $( '#toDate' ).val(), 90 );
			if ( flag ) {
				$( '.loadingPopup' ).removeClass( 'hidden' );
				document.forms[ 1 ].action = "qrcReportAction.do?method=taggingReport";
				document.forms[ 1 ].submit();
			}
		}
	} );

	/* == Tagging Report Ends == */

	/* == Ticket Report Starts == */

	$( '#IDTicketSubmit' ).click( function(){
		if ( $( '#IDTicket' ) ) {

			var flag = DateValidityChecker( $( '#fromDate' ).val(), $( '#toDate' ).val(), 90 );
			if ( flag ) {
				$( '.loadingPopup' ).removeClass( 'hidden' );
				document.forms[ 1 ].action = "qrcReportAction.do?method=ticketReport";
				document.forms[ 1 ].submit();
			}
		}
	} );

	/* == Ticket Report Ends == */

	/* == First Assigned Bin Ticket Report Starts == */

	$( '#IDFABSubmit' ).click( function(){
		if ( $( '#IDFABTicket' ) ) {

			var flag = DateValidityChecker( $( '#fromDate' ).val(), $( '#toDate' ).val(), 90 );

			if ( flag && validateBin( $( '#qrcFuncBinId' ).val() ) ) {
				$( '.loadingPopup' ).removeClass( 'hidden' );
				document.forms[ 1 ].action = "qrcReportAction.do?method=firstAssignedBinTicketReport";
				document.forms[ 1 ].submit();
			}
		}
	} );

	/* == First Assigned Bin Ticket Report Ends == */
	/** Reopenticket* */
	$( '#IDTicketReopenSubmit' ).click( function(){
		if ( $( '#IDTicketReopen' ) ) {

			var flag = DateValidityChecker( $( '#fromDate' ).val(), $( '#toDate' ).val(), 90 );

			if ( flag ) {
				$( '.loadingPopup' ).removeClass( 'hidden' );
				document.forms[ 1 ].action = "qrcReportAction.do?method=reopenTicketReport";
				document.forms[ 1 ].submit();
			}
		}
	} );

	/** *Repeat Ticket */

	$( '#IDTicketRepeatSubmit' ).click( function(){
		if ( $( '#IDTicketRepeat' ) ) {
			var flag = DateValidityChecker( $( '#fromDate' ).val(), $( '#toDate' ).val(), 90 );
			if ( flag ) {
				$( '.loadingPopup' ).removeClass( 'hidden' );
				document.forms[ 1 ].action = "qrcReportAction.do?method=repeatTicketReport";
				document.forms[ 1 ].submit();
			}
		}
	} );
	/** ********** */
	/* User wise Reopen Resolved Ticket Report Starts */
	$( '#IDUsrWiseRRTktSubmit' ).click( function(){
		if ( $( '#IDUsrWiseRRTkt' ) ) {
			$( '.loadingPopup' ).removeClass( 'hidden' );
			document.forms[ 1 ].action = "qrcReportAction.do?method=userWiseReOpenResolvedTktReport";
			document.forms[ 1 ].submit();
		}
	} );
	/* User wise Reopen Resolved Ticket Report End */

	/* == Mass Outage Report Starts == */
	$( '#IDMassOutageSubmit' ).click( function(){
		if ( $( '#IDMassOutageReport' ) ) {

			var flag = DateValidityChecker( $( '#fromDate' ).val(), $( '#toDate' ).val(), 90 );
			if ( flag ) {
				$( '.loadingPopup' ).removeClass( 'hidden' );
				document.forms[ 1 ].action = "qrcReportAction.do?method=massOutageReport";
				document.forms[ 1 ].submit();
			}
		}
	} );
	/* == Mass Outage Report End == */

	$( '#IDSafeCustodySubmit' ).click( function(){
		if ( $( '#IDSafeCustodyReport' ) ) {
			var flag = DateValidityChecker( $( '#fromDate' ).val(), $( '#toDate' ).val(), 90 );
			if ( flag ) {
				$( '.loadingPopup' ).removeClass( 'hidden' );
				document.forms[ 1 ].action = "qrcReportAction.do?method=safeCustodyReport";
				document.forms[ 1 ].submit();
			}
		}
	} );

	$( '#IDCWOpenSubmit' ).click( function(){
		if ( $( '#IDcatWiseORT' ) ) {
			$( '.loadingPopup' ).removeClass( 'hidden' );
			document.forms[ 1 ].action = "qrcReportAction.do?method=catWiseOpenTicketReport";
			document.forms[ 1 ].submit();
		}
	} );

	$( '#IDUWOpenSubmit' ).click( function(){
		if ( $( '#IDuserWiseORT' ) ) {
			$( '.loadingPopup' ).removeClass( 'hidden' );
			document.forms[ 1 ].action = "qrcReportAction.do?method=userWiseOpenTicketReport";
			document.forms[ 1 ].submit();
		}
	} );

	/* Barring Report */
	$( '#IDBarringSubmit' ).click( function(){
		if ( $( '#IDBarringReport' ) ) {
			var flag = DateValidityChecker( $( '#frmDateBarring' ).val(), $( '#todateBaaring' ).val(), 90 );
			if ( flag ) {
				$( '.loadingPopup' ).removeClass( 'hidden' );
				document.forms[ 1 ].action = "qrcReportAction.do?method=barringReport";
				document.forms[ 1 ].submit();
			}
		}
	} );

	/* user wise open out of sla ticket report */
	$( '#userWiseOTRSUBMIT' ).click( function(){
		if ( $( '#userWiseOTR' ) ) {
			$( '.loadingPopup' ).removeClass( 'hidden' );
			document.forms[ 1 ].action = "qrcReportAction.do?method=outOfSLATicketReport";
			document.forms[ 1 ].submit();

		}
	} );
	/* user wise open out of sla ticket report end */

	/* category wise resolve ticket report */
	$( '#categoryWiseResolveTcktSUBMIT' ).click( function(){
		if ( $( '#categoryWiseResolveTckt' ) ) {
			var flag = DateValidityChecker( $( '#fromDate' ).val(), $( '#toDate' ).val(), 90 );
			if ( flag ) {
				$( '.loadingPopup' ).removeClass( 'hidden' );
				document.forms[ 1 ].action = "qrcReportAction.do?method=ctgryWiseResolveTckt";
				document.forms[ 1 ].submit();
			}
		}
	} );
	/* category wise resolve ticket report end */

} );

function DateValidityChecker( fromDate, toDate, noOfDays ){
	if ( fromDate == null || toDate == null ) {
		$( '#ToDateError ~ font' ).addClass( 'hidden' );
		return false;
	}
	/*var d1 = new Date( fromDate );
	var d2 = new Date( toDate );
	if ( d1 > d2 ) {
		alert( "'To Date' should be greater than 'From Date'" );
		return false;
	}*/
	var days = dateDiff( getISODate( fromDate ), getISODate( toDate ) );

	if ( days < 0 ) {
		alert( "'To Date' should be greater than 'From Date'" );
		return false;
	}

	if ( days <= noOfDays ) {
		return true;
	}
	else {
		alert( "Days difference should be less than or equal to " + noOfDays + " days." );
		$( '#ToDateError ~ font' ).removeClass( 'hidden' );
		return false;
	}
}
function FutureDateValidityChecker(fromDate, toDate, noOfDays){

	var today= new Date();
	var d1 = new Date( fromDate );
	var d2 = new Date( toDate );
	if(d2 > today)
	{
	alert( "'To Date' should be less than or equal to 'Current Date'" );
		return false;	
	}
	
	if(d1 > today)
	{
	alert( "'From Date' should be less than or equal to 'Current Date'" );
		return false;	
	}
	
	var days = dateDiff( getISODate( fromDate ), getISODate( toDate ) );

	if ( days < 0 ) {
		alert( "'To Date' should be greater than 'From Date'" );
		return false;
	}

	if ( days <= noOfDays ) {
		return true;
	}
	else {
		alert( "Days difference should be less than or equal to " + noOfDays + " days." );
		$( '#ToDateError ~ font' ).removeClass( 'hidden' );
		return false;
	}
	
}
function populateSubCategoriesforReport( inAccessID, inDestinationID ){
	var valid = true;
	if ( inAccessID == '' || inAccessID == '0' || inAccessID == null || inAccessID == undefined || inAccessID == 'All' ) {
		valid = false;
	}
	if ( valid ) {
		fillSubCatReport( inAccessID, inDestinationID );
	}
	else {
		// set sub category list empty
		dwr.util.removeAllOptions( inDestinationID );
		dwr.util.addOptions( inDestinationID, [
			{
				"value" : "0", "label" : "All"
			}
		], "value", "label" );

		// set sub sub category list empty
		dwr.util.removeAllOptions( "qrcSubSubcatId" );
		dwr.util.addOptions( "qrcSubSubcatId", [
			{
				"value" : "0", "label" : "All"
			}
		], "value", "label" );

	}
}
function fillSubCatReport( categoryId, subCatId ){
	crmDwr.getActiveQrcSubCategories( categoryId, function( list ){
		addSubCat( subCatId, list );
	} );
	function addSubCat( id, list ){
		var select = document.getElementById( id );
		if ( select != null ) {
			if ( list != null ) {
				dwr.util.removeAllOptions( id );
				dwr.util.addOptions( id, [
					"All"
				] );
				dwr.util.addOptions( id, list, "qrcSubCategoryId", "qrcSubCategory" );

			}
		}

	}
}
function populateSubSubCategoriesReport( inCategoryId, inDestinationID, inSubCategoryId ){
	var valid = true;
	if ( inSubCategoryId == '' || inSubCategoryId == '0' || inSubCategoryId == null || inSubCategoryId == undefined || inSubCategoryId == 'All' ) {
		valid = false;
	}
	if ( valid ) {
		var categoryID = document.getElementById( inCategoryId ).value;
		crmDwr.getActiveQrcSubSubCategories( categoryID, inSubCategoryId, function( list ){
			addSubSubCategories( inDestinationID, list );
		} );
		function addSubSubCategories( id, list ){
			var select = document.getElementById( id );
			if ( select != null ) {
				if ( list != null ) {
					dwr.util.removeAllOptions( id );
					dwr.util.addOptions( id, [
						"All"
					] );
					if ( list != null ) {
						dwr.util.addOptions( id, list, "qrcSubSubCategoryId", "qrcSubSubCategory" );
					}
				}
				else {
					alert( "Sub Sub Catogory List is empty for current selected Sub Category." );

					removeList( id );
				}
			}
		}
	}
	else {
		// set sub sub category list empty
		dwr.util.removeAllOptions( inDestinationID );
		dwr.util.addOptions( inDestinationID, [
			{
				"value" : "0", "label" : "All"
			}
		], "value", "label" );
	}
}
/* Ina Report Submit Common */
function inaReportSubmit( inParam ){
	var flag = true;
	if ( inParam != "isrPendancyReportPage" && inParam != "srPReportPage" && inParam != "workorderPendancyReportPage"
			&& inParam != 'ftRejectionReportPage' && inParam != 'workorderGenerationReportPage' &&  inParam != "erpReportPage") {
		flag = DateValidityChecker( $( '#fromDate' ).val(), $( '#toDate' ).val(), 90 );
	}
	if( inParam == "workorderGenerationReportPage")
	{
		flag = FutureDateValidityChecker($( '#fromDate' ).val(), $( '#toDate' ).val(), 90);
	}
	if ( flag ) {
		$( '.loadingPopup' ).removeClass( 'hidden' );
		document.forms[ 1 ].action = "capReportAction.do?method=" + inParam;
		document.forms[ 1 ].submit();
	}

}
/* Ina Report Submit Common End */

function validateBin( value ){
	var status = true;
	if ( value.trim() == '' || value == '0' || value == null || value == undefined || value == 'All' ) {
		alert( "Please select funtional bin" );
		status = false;
	}
	return status;
}

function paymentReport( inParam ){
	var flag = true;

	if ( $( "#crfId" ).val().trim() != "" && !validateCRF( $( "#crfId" ).val() ) ) {
		alert( "CAF number should be numeric and 8 character long." );
		flag = false;

	}
	var flag1 = DateValidityChecker( $( '#fromDate' ).val(), $( '#toDate' ).val(), 90 );
	if ( flag && flag1 ) {
		$( '.loadingPopup' ).removeClass( 'hidden' );
		document.forms[ 1 ].action = "qrcReportAction.do?method=" + inParam;
		document.forms[ 1 ].submit();
	}
}
function lmsReportSubmit( inParam ){
	var flag = DateValidityChecker( $( '#fromDate' ).val(), $( '#toDate' ).val(), 90 );
	if ( flag ) {
		$( '.loadingPopup' ).removeClass( 'hidden' );
		document.forms[ 1 ].action = "qrcReportAction.do?method=" + inParam;
		document.forms[ 1 ].submit();
	}
}

/*function rfsReportSubmit( inParam ){
		$( '.loadingPopup' ).removeClass( 'hidden' );
		document.forms[ 1 ].action = "rfsReportAction.do?method=rfsReport" + inParam;
		document.forms[ 1 ].submit();
}*/
/* Safe Custody Report */

/* Safe Custody Report */

$( '#IDSafeCustodySubmit' ).click( function(){
	if ( $( '#IDSafeCustodyReport' ) ) {
		var flag = DateValidityChecker( $( '#fromDate' ).val(), $( '#toDate' ).val(), 90 );
		if ( flag ) {
			$( '.loadingPopup' ).removeClass( 'hidden' );
			document.forms[ 1 ].action = "qrcReportAction.do?method=safeCustodyReport";
			document.forms[ 1 ].submit();
		}
	}
} );

function validateCategories(){
	var status = true;
	var cat = document.getElementById( "qrcCatId" ).value;
	var subCat = document.getElementById( "qrcSubCatId" ).value;
	var subsubCat = document.getElementById( "qrcSubSubcatId" ).value;
	// alert("cat:"+cat+":subCat:"+subCat+":subsubCat:"+subsubCat);
	if ( cat == '' || cat == '0' || cat == null || cat == undefined || cat == 'All' ) {
		alert( "Please select category." );
		status = false;
	}
	else if ( subCat == '' || subCat == '0' || subCat == null || subCat == undefined || subCat == 'All' ) {
		alert( "Please select sub category." );
		status = false;
	}
	else if ( subsubCat == '' || subsubCat == '0' || subsubCat == null || subsubCat == undefined || subsubCat == 'All' ) {
		alert( "Please select sub subcategory." );
		status = false;
	}
	return status;
}
function submitRfsReport(){
	$( '.loadingPopup' ).removeClass( 'hidden' );
	document.forms[ 1 ].action = "rfsReportAction.do?method=rfsReport";
	document.forms[ 1 ].submit();
}