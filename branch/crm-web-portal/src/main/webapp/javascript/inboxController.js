function editLead( inLmsId, inLeadId, inboxId ){
	var reply = confirm( "Do you want to work on Lead ID : " + inLeadId + " ?" );
	if ( reply ) {
		var url = "leadGeneration.do?method=modifyLeadPage&lmsId=" + inLmsId + "&InboxId=" + inboxId;
		var win = window.open( url, "_blank" );
		win.focus();
	}
}

function editCRF( crfRecordId, inboxId, inCRFId ){
	var reply = confirm( "Do you want to work on CAF : " + inCRFId + " ?" );
	if ( reply ) {
		var url = "crmCap.do?method=editCRFEntryForAnyStage&RecordId=" + crfRecordId + "&InboxId=" + inboxId;
		var win = window.open( url, "_blank" );
		win.focus();
	}
}
function viewAll( inLmsIdlmsIdCrfRecordId, inLeadIdCrfIdTicketId, inProduct, inParameter, inType, inboxId ){
	var boolValue = false;
	if ( inParameter == "lead" ) {
		viewLead( inLmsIdlmsIdCrfRecordId, inType, inboxId );
	}
	else if ( inParameter == "CAF" ) {
		viewCRF( inLmsIdlmsIdCrfRecordId, inboxId, inType );
	}
	else if ( inParameter == "QRC" ) {
		viewQRC( inLmsIdlmsIdCrfRecordId, boolValue, inType, inboxId );
	}
}
function editAll( inLmsIdlmsIdCrfRecordId, inLeadIdCrfIdTicketId, inProduct, inStage, inType, inboxId ){
	var boolValue = true;
	if ( inType == "lead" ) {
		editLead( inLmsIdlmsIdCrfRecordId, inLeadIdCrfIdTicketId, inboxId );
	}
	else if ( inType == "CAF" ) {
		editCRF( inLmsIdlmsIdCrfRecordId, inboxId, inLeadIdCrfIdTicketId );
	}
	else if ( inType == "QRC" ) {
		var inParam = 'inbox';
		editQRC( inLmsIdlmsIdCrfRecordId, boolValue, inParam, inboxId );
	}
}

function crfDetails( inRecordId, inCRFId ){
	var reply = confirm( "Do you want to work CAF : " + inCRFId + " ?" );
	if ( reply ) {
		document.forms[ 1 ].action = "crmCap.do?method=crfDetails";
		document.forms[ 1 ].submit();
	}
}
// time Duration AutoRefresh for Inbox
function AutoRefresh( timeDuration ){
	window.location.replace( 'goToInbox.do?method=allInbox' );
	//setTimeout( "location.reload(true);", timeDuration );
}

function changeBinOwner( inRowID, inElementID, inOwner, inStage, inElementType, inInboxType ){
	// inType -- lms - LMS, crf - CRF, qrc - QRC
	var reply = false;
	if ( inElementType === 'lead' ) {
		reply = confirm( "Do you want to change ownership for Lead ID : " + inElementID + " ?" );
	}
	else if ( inElementType === 'CAF' ) {
		reply = confirm( "Do you want to change ownership for CAF : " + inElementID + " ?" );
	}
	else if ( inElementType === 'QRC' ) {
		reply = confirm( "Do you want to change ownership for Ticket ID : " + inElementID + " ?" );
	}
	else if ( inElementType === 'WORKFLOW' ) {
		reply = confirm( "Do you want to change ownership for Workflow ID : " + inElementID + " ?" );
	}
	if ( reply ) {
		// document.getElementById( "selectedRowID" ).value = inRowID;
		// document.getElementById( "selectedElementID" ).value = inElementID;
		// document.getElementById( "inboxOwnerValue" ).value = inOwner;
		// document.getElementById( "selectedElementStage" ).value = inStage;
		// document.getElementById( "selectedElementInbox" ).value =
		// inElementType;
		// document.getElementById( "selectedInboxType" ).value = inInboxType;
		// document.forms[ 1 ].action = "goToInbox.do?method=changeBinOwner";
		// document.forms[ 1 ].submit();
		$( '.loadingPopup' ).removeClass( 'hidden' );
		crmDwr.changeBinOwner( inRowID, inElementID, inOwner, inStage, inElementType, inInboxType, {
			callback : function( result ){
				if ( result == true ) {
					// alert( 'Your request processed successfully.' );
					window.location.replace( 'goToInbox.do?method=allInbox' );
					// location.reload( true );
				}
				else {
					$( '.loadingPopup' ).addClass( 'hidden' );
					alert( 'Unable to process your request.' );
				}
			}, errorHandler : function( errorString, exception ){
				$( '.loadingPopup' ).addClass( 'hidden' );
				alert( errorString );
			}
		} );
	}
}

function editQRC( inTicketId, inBoolValue, inType, inboxId ){
	var url = "manageQrc.do?method=viewTicketPage&ticketId=" + inTicketId + "&boolValue=" + inBoolValue + "&inType=" + inType + "&inboxId=" + inboxId;
	window.open( url, "_blank" );
	win.focus();
};

function viewWorkflow( inWorkflowId, inRequestType, inInboxId ){
	if ( inRequestType == 'Shifting' ) {
		var url = "shiftingWorkflow.do?method=viewShiftingPage&inWorkflowId=" + inWorkflowId + "&inRequestType=" + inRequestType + "&inInboxId="
				+ inInboxId;
		window.open( url, "_blank" );
		win.focus();
	}
	else {
		var url = "waiver.do?method=viewWaiver&workflowId=" + inWorkflowId + "&inRequestType=" + inRequestType + "&inInboxId=" + inInboxId;
		window.open( url, "_blank" );
		win.focus();
	}
}
function editWorkflow( inWorkflowId, inRequestType, inStage, inWorkflowType, inCustomerId, inboxId ){
	var reply = confirm( "Do you want to work on workflow : " + inWorkflowId + " ?" );
	if ( reply ) {
		if ( inRequestType == 'Shifting' ) {
			var url = "shiftingWorkflow.do?method=editStage&workflowId=" + inWorkflowId + "&inStage=" + inStage + "&inWorkflowType=" + inWorkflowType
					+ "&inboxId=" + inboxId + "&inCustomerId=" + inCustomerId + "&paramValue="
			window.open( url, "_blank" );
			win.focus();
		}

		else {
			var url = "waiver.do?method=modifiyWaiverPage&workflowId=" + inWorkflowId + "&inRequestType=" + inRequestType + "&inboxId=" + inboxId;
			window.open( url, "_blank" );
			win.focus();
		}
	}
}
function inboxSearch( param ){
	var criteria = document.getElementById( "inboxSearchCriteria" );
	var criteriaValue = document.getElementById( "inboxSearchCriteriaValue" );
	if ( criteria.value == "" || criteriaValue.value == "" ) {
		alert( "Please select search criteria and provide search criteria value" );
		return false;
	}
	else if ( criteria.value == "customerId" && isNaN( criteriaValue.value ) ) {
		alert( "Please provide numeric value." );
		return false;
	}
	else if ( criteria.value == "ticketId" && criteriaValue.value.length < 11 && !criteriaValue.value.startsWith( "L" )) {
		alert( "Ticket ID should at least eleven digits long." );
		return false;
	}
	else if ( criteria.value == "customerId" && criteriaValue.value.length < 7 ) {
		alert( "Customer ID should at least seven digits long." );
		return false;
	}
	else if ( criteria.value == "leadid" && !criteriaValue.value.startsWith( "L" ) ) {
		alert( "Lead ID should start with 'L'." );
		return false;
	}
	else if ( criteria.value == "crfid" && !validateCRF( criteriaValue.value ) ) {
		alert( "Please provide CAF number in valid format." );
		return false;
	}
	else {
		$( '.loadingPopup' ).removeClass( 'hidden' );
		document.forms[ 1 ].action = "goToInbox.do?method=" + param;
		document.forms[ 1 ].submit();
	}

}
function resetInboxCriteria( param ){
	/*
	 * crmDwr.resetInboxSearch( { callback : function( result ){ if ( result == true ) { $( '#inboxSearchCriteriaValue' ).val( '' ); } else { alert( 'Unable to process your request.' ); } }, errorHandler : function( errorString, exception ){ alert( errorString ); } } );
	 */
	$( '#inboxSearchCriteriaValue' ).val( '' );
	document.forms[ 1 ].action = "goToInbox.do?method=" + param;
	document.forms[ 1 ].submit();
}
// Multiple checksBox for All Inbox calls
function InboxcheckData( elm, owner ){
	var selfLength = $( ".self" ).length;
	var selfCkdLength = $( ".self:checked" ).length;
	var groupLength = $( ".group" ).length;
	var groupCkdLength = $( ".group:checked" ).length;
	if ( owner == 'self' ) {
		if ( elm.checked ) {
			if ( selfLength == selfCkdLength ) {
				$( "#selectIndAll" ).attr( "checked", elm.checked );
			}
			else {
				$( "#selectIndAll" ).removeAttr( "checked" );
			}
			$( '#assignSelfButton' ).show();
		}
		else {
			$( "#selectIndAll" ).removeAttr( "checked" );
			if ( selfCkdLength == 0 ) {
				$( '#assignSelfButton' ).hide();
			}
		}
	}
	else {
		if ( elm.checked ) {
			if ( groupLength == groupCkdLength ) {
				$( "#selectgrpAll" ).attr( "checked", elm.checked );
			}
			else {
				$( "#selectgrpAll" ).removeAttr( "checked" );
			}
			$( '#assignGroupButton' ).show();
		}
		else {
			$( "#selectgrpAll" ).removeAttr( "checked" );
			if ( groupCkdLength == 0 ) {
				$( '#assignGroupButton' ).hide();
			}
		}

	}
}
function selectSelfAll( elm ){
	if ( elm.checked ) {
		$( '.self' ).attr( 'checked', elm.checked );
		$( '#assignSelfButton' ).show();
	}
	else {
		$( '.self' ).removeAttr( "checked" );
		$( '#assignSelfButton' ).hide();
	}
}

function selectGroupAll( elm ){
	if ( elm.checked ) {
		$( '.group' ).attr( 'checked', elm.checked );
		$( '#assignGroupButton' ).show();
	}
	else {
		$( '.group' ).removeAttr( "checked" );
		$( '#assignGroupButton' ).hide();
	}
}
function assignMultipleBin( inboxType, inboxOwner ){
	document.getElementById( "selectedInboxType" ).value = inboxType;
	document.getElementById( "inboxOwnerValue" ).value = inboxOwner;
	var answer = confirm( "Do you want to change ownership of the docket(s) ?" );
	if ( answer ) {
		$( '.loadingPopup' ).removeClass( 'hidden' );
		document.forms[ 1 ].action = "goToInbox.do?method=multipleChangeBinOwner";
		document.forms[ 1 ].submit();
	}
}
function changeToUpperCaseInbox( obj ){
	var val = $( '#inboxSearchCriteria' ).val();
	if ( val != 'subSubCategory' ) {
	var start = obj.selectionStart;
	obj.value = obj.value.toUpperCase();
	obj.selectionStart = start;
	obj.selectionEnd = start;
	}
}
function fetchSubSubCategories(){
	var val = $( '#inboxSearchCriteria' ).val();
	if ( val != 'subSubCategory' ) {
		$( '#inboxSearchCriteriaValue' ).autocomplete( {
			source : []
		} );
	}
	else {
		crmDwr.getQrcRcaSubSubCategoryies( function( subSubCategoryList ){
			subCats = [];
			var cat;
			if ( subSubCategoryList != null ) {
				subSubCategoryList.forEach( function( e ){
					cat = {}
					cat[ 'label' ] = e.qrcSubSubCategory;
					cat[ 'value' ] = e.qrcSubSubCategory;
					subCats.push( cat );
				} );
			}
			$( '#inboxSearchCriteriaValue' ).autocomplete( {
				source : subCats, minLength : 0, change : function( e, ui ){
					if ( !ui.item ) {
						$( e.target ).val( '' );
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
}