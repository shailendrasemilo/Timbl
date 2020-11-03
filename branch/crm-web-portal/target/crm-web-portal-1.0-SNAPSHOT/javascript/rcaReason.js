$( document ).ready( function(){
	$.validator.addMethod( 'rcaReason1', function( value ){
		return ( value != '0' );
	} );
	$.validator.addMethod( 'rcaReason2', function( value ){
		return ( value != '0' );
	} );
	$.validator.addMethod( 'rcaReason3', function( value ){
		return ( value != '0' );
	} );
	$.validator.addMethod( 'crmRcaReason.category', function( value ){
		return ( value != '0' );
	} );
	$.validator.addMethod( 'crmRcaReason.subCategory', function( value ){
		return ( value != '0' );
	} );
	$.validator.addMethod( 'crmRcaReason.subSubCategory', function( value ){
		return ( value != '0' );
	} );
	$( "#rcaReasonMaster" ).validate( {
		rules : {
			'crmRcaReason.category' : {
				'crmRcaReason.category' : true
			}, 'crmRcaReason.subCategory' : {
				'crmRcaReason.subCategory' : true
			}, 'crmRcaReason.subSubCategory' : {
				'crmRcaReason.subSubCategory' : true
			}
		}, messages : {
			'crmRcaReason.category' : {
				'crmRcaReason.category' : "<div class='category'>Please select 'Category'</div>"
			}, 'crmRcaReason.subCategory' : {
				'crmRcaReason.subCategory' : "<div  class='category'>Please select 'Sub Category'</div>"
			}, 'crmRcaReason.subSubCategory' : {
				'crmRcaReason.subSubCategory' : "<div  class='category'>Please select 'Sub Sub Category'</div>"
			}
		}
	} );
	$( '#submit_rcaReasonMaster' ).click( function(){
		if ( $( "#rcaReasonMaster" ).valid() ) {
			document.forms[ 1 ].action = "crmRcaReason.do?method=createCategoryValues";
			document.forms[ 1 ].submit();
		}
	} );
	$( '#idAddRca' ).click( function(){
		if ( $( "#rcaReasonMaster" ).valid() ) {
			document.forms[ 1 ].action = "crmRcaReason.do?method=addRcaReasonRow";
			document.forms[ 1 ].submit();
		}
	} );
} );
function deleteCategoryValueRow( inIndex ){
	document.getElementById( "rowIndex_addRow" ).value = inIndex;
	document.forms[ 1 ].action = "crmRcaReason.do?method=deleteCategoryValueRow";
	document.forms[ 1 ].submit();
}
function searchCategoryValue(){
	if ( $( "#rcaReasonMaster" ).valid() ) {
		document.forms[ 1 ].action = "crmRcaReason.do?method=searchCategoryValues";
		document.forms[ 1 ].submit();
	}
}
function enableRcaReason( rowNo ){
	var editChkBox = document.getElementsByName( "crmRcaReasonsList[" + rowNo + "].editable" );
	var categoryValue = document.getElementsByName( "crmRcaReasonsList[" + rowNo + "].categoryValue" );
	var status = document.getElementsByName( "crmRcaReasonsList[" + rowNo + "].status" );
	if ( editChkBox[ 0 ].checked ) {
		categoryValue[ 0 ].readOnly = false;
		$( categoryValue ).removeClass( 'gray_text' );
		status[ 0 ].onclick = "";
		status[ 1 ].onclick = "";
	}
	else {
		categoryValue[ 0 ].readOnly = true;
		$( categoryValue ).addClass( 'gray_text' );
		status[ 0 ].onclick = returnFalse;
		status[ 1 ].onclick = returnFalse;
	}
}
function returnFalse(){
	return false;
}
function getSubCategoryValue( category ){
	if ( category != 0 ) {
		$('.loadingPopup').removeClass('hidden');
		crmDwr.getCRFMasterCategories( category, null, function( list ){
			addSubCategory( "subcategory", list );
		} );
		function addSubCategory( id, list ){
			var select = document.getElementById( id );
			if ( select != null ) {
				if ( list != null ) {
					dwr.util.removeAllOptions( id );
					dwr.util.addOptions( id, list, "contentName", "contentValue" );
					document.getElementById( "subcategory" ).value = list[ 0 ].contentName;
					getSubSubCategoryValue( list[ 0 ].contentName );
				}
			}
		}
	}
	else {
		removeOptions( "subcategory" );
		removeOptions( "subsubcategory" );
	}
	$('.loadingPopup').addClass('hidden');
}
function removeOptions( subId ){
	dwr.util.removeAllOptions( subId );
	dwr.util.addOptions( subId, [
		"Please select"
	] );
}
function getSubSubCategoryValue( subCategory ){
	var category = document.getElementById( "category" ).value;
	crmDwr.getCRFMasterCategories( category, subCategory, function( list ){
		$('.loadingPopup').removeClass('hidden');
		addSubSubCategory( "subsubcategory", list );
	} );
	function addSubSubCategory( id, list ){
		var select = document.getElementById( id );
		if ( select != null ) {
			if ( list != null ) {
				dwr.util.removeAllOptions( id );
				dwr.util.addOptions( id, list, "contentName", "contentValue" );
				document.getElementById( "subsubcategory" ).value = list[ 0 ].contentName;
				searchCategoryValue();
			}
		}
	}
	$('.loadingPopup').addClass('hidden');
}
