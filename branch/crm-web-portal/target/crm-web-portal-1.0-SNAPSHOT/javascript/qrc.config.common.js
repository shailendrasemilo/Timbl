$( document ).ready( function(){
	$( '.checkGreyText' ).each( function( index, obj ){
		// console.log(index+" "+obj.disabled);
		if ( obj.disabled ) {
			$( obj ).addClass( 'gray_text' );
		}
	} );
	// $.validator.addMethod( 'qrcCategoryId', function( value )
	// {
	// return ( value != '0' );
	// } );
	// $.validator.addMethod( 'serviceName', function( value )
	// {
	// return ( value != '' );
	// } );
	// $.validator.addMethod( 'qrcRcaId', function( value )
	// {
	// return ( value != '0' );
	// } );
	//
	// $( '#formResolutionCodeConfig' ).validate( {
	// rules : {
	// 'qrcCategoryId' : {
	// qrcCategoryId : true
	// },
	// 'serviceName' : {
	// serviceName : true
	// },
	// 'qrcRcaId' : {
	// qrcRcaId : true
	// }
	// },
	// messages : {
	// 'qrcCategoryId' : {
	// qrcCategoryId : '<font class="errorTextbox">Please select
	// Category</font>'
	// },
	// 'serviceName' : {
	// serviceName : '<font class="errorTextbox">Please select Service
	// Name</font>'
	// },
	// 'qrcRcaId' : {
	// qrcRcaId : '<font class="errorTextbox">Please select RCA</font>'
	// }
	// }
	// } );
	/*
	 * $("#formAttributedToConfig").validate({ rules : { 'qrcCategoryId' : { qrcCategoryId : true }, 'serviceName' : { serviceName : true }, 'qrcRcaId' : { qrcRcaId : true }, 'qrcResolutionCodeId' : { qrcResolutionCodeId : true } }, messages : { 'qrcCategoryId' : { qrcCategoryId : '<font
	 * class="errorTextbox">Please select Category</font>' }, 'serviceName' : { serviceName : '<font class="errorTextbox">Please select Service Name</font>' }, 'qrcRcaId' : { qrcRcaId : '<font class="errorTextbox">Please select RCA</font>' }, 'qrcResolutionCodeId' : { qrcResolutionCodeId : '<font
	 * class="errorTextbox">Please select Resolution Code</font>' } } });
	 */
	$( '#addRowResolutionCode' ).click( function(){

		// $( '#formResolutionCodeConfig' ).valid()
		if ( validateConfigResCodeForm() ) {
			$( '#formResolutionCodeConfig' ).attr( 'action', 'qrcConfig.do?method=addRowResolutionCode' );
			$( '#formResolutionCodeConfig' ).submit();
		}
	} );

	$( '#IDaddRowSUBSUBCAT' ).click( function(){
		/*
		 * if ( $( '#formResolutionCodeConfig' ).valid() ) {
		 */
		$( '#formConfigureSubSubCode' ).attr( 'action', 'qrcConfig.do?method=addRowSubSubCat' );
		$( '#formConfigureSubSubCode' ).submit();
		/* } */
	} );
	$( '#addRowAttributedTo' ).click( function(){
		if ( validateAttributedToForm() ) {
			$( '#formAttributedToConfig' ).attr( 'action', 'qrcConfig.do?method=addRowAttributedTo' );
			$( '#formAttributedToConfig' ).submit();
		}
	} );
	// $( '#submit_configResCode' ).click( function(){
	// // $( '#formResolutionCodeConfig' ).valid()
	// if ( validateConfigResCodeForm() && confirm( 'Are you sure you want to configure/update Resolution Code?' ) ) {
	// $( '#formResolutionCodeConfig' ).attr( 'action', 'qrcConfig.do?method=configResolutionCode' );
	// $( '#formResolutionCodeConfig' ).submit();
	// }
	// } );
	// $( '#submit_configAttributedTo' ).click( function(){
	// if ( validateAttributedToForm() && confirm( 'Are you sure you want to configure/update AttributedToPage?' ) ) {
	// $( '#formAttributedToConfig' ).attr( 'action', 'qrcConfig.do?method=attributedToPage' );
	// $( '#formAttributedToConfig' ).submit();
	// }
	// } );
	// $( '#submit_qrcRcaConfig' ).click( function(){
	// if ( qrcRcaConfigurationsCheck() && confirm( 'Are you sure you want to configure/update RCA ?' ) ) {
	// $( '#qrcRcaConfiguration' ).attr( 'action', 'qrcConfig.do?method=configRCA' );
	// $( '#qrcRcaConfiguration' ).submit();
	// }
	// } );

	// $( '#submit_qrcBinMapping' ).click( function(){
	// if ( qrcBinConfigurationsCheck() && confirm( 'Are you sure you want to configure/update QRC Functional Bin Mapping?' ) ) {
	// $( '#qrcBinMapping' ).attr( 'action', 'qrcConfig.do?method=configureQrcFB' );
	// $( '#qrcBinMapping' ).submit();
	// }
	// } );

	$( '#addRowRCAConfiguration' ).click( function(){
		if ( qrcRcaConfigurationsCheck() ) {
			$( '#qrcRcaConfiguration' ).attr( 'action', 'qrcConfig.do?method=addRCAConfiguration' );
			$( '#qrcRcaConfiguration' ).submit();
		}
	} );

	$( '#addRowBinConfiguration' ).click( function(){
		if ( $( '[name="resolutionStr"]' ).val() == 'ROL' ) {
			$( '#binCategoryID' ).next( 'font' ).show().html( "Mapping can't be create for ROL." ).addClass( 'errorTextbox' );
		}
		else if ( qrcBinConfigurationsCheck() ) {
			$( '#qrcBinMapping' ).attr( 'action', 'qrcConfig.do?method=addBinConfiguration' );
			$( '#qrcBinMapping' ).submit();
		}
	} );
} );
function qrcRcaConfig( size ){
	flag = true;
	flag1 = false;
	for ( var i = 0; i < size; i++ ) {
		$( "[name='qrcActionTakenPojos[" + i + "].actionTaken']" ).next( 'font' ).hide();
		if ( $( "[name='qrcActionTakenPojos[" + i + "].editable']" ).attr( 'checked' ) == true ) {
			flag1 = true;
		}
		if ( $( "[name='qrcActionTakenPojos[" + i + "].rca']" ).val() == '' ) {
			flag = false;
		}
		if ( $( "[name='qrcActionTakenPojos[" + i + "].actionTaken']" ).val() == '' ) {
			alert( "'Action taken' cant't be blank !" );
			flag = false;
			break;
		}
		else if ( !validateNotAllowSpecialCharacter( $( "[name='qrcActionTakenPojos[" + i + "].actionTaken']" ).val() ) ) {
			alert( "Please enter valid 'Action taken'" );
			flag = false;
			break;
		}

	}
	if ( !flag1 ) {
		alert( "Select at least one Action taken to configure/update !" );
		$( "#configMsg" ).hide();
	}
	else if ( !flag ) {
		// alert( "Action taken cant't be blank !" );
	}
	else {
		if ( qrcRcaConfigurationsCheck() && confirm( 'Are you sure you want to configure/update Action ?' ) ) {
			$( '#qrcRcaConfiguration' ).attr( 'action', 'qrcConfig.do?method=configRCA' );
			$( '#qrcRcaConfiguration' ).submit();
		}
	}
}
function qrcConfigResCode( size ){

	flag = true;
	flag1 = false;
	for ( var i = 0; i < size; i++ ) {
		if ( $( "[name='qrcRootCausePojos[" + i + "].editable']" ).attr( 'checked' ) == true ) {
			flag1 = true;
		}
		if ( $( "[name='qrcRootCausePojos[" + i + "].resolutionCode']" ).val() == '' ) {
			flag = false;
		}
	}
	if ( !flag1 ) {
		alert( "Select at least one Root Cause to configure/update !" );
	}
	else if ( !flag ) {
		alert( "Root Cause cant't be blank !" );
	}
	else {
		if ( validateConfigResCodeForm() && confirm( 'Are you sure you want to configure/update Root Cause?' ) ) {
			$( '#formResolutionCodeConfig' ).attr( 'action', 'qrcConfig.do?method=configResolutionCode' );
			$( '#formResolutionCodeConfig' ).submit();
		}
	}

}
function qrcAttributedToConfig( size ){
	flag = true;
	flag1 = false;
	for ( var i = 0; i < size; i++ ) {
		if ( $( "[name='attributedToPojos[" + i + "].editable']" ).attr( 'checked' ) == true ) {
			flag1 = true;
		}
		if ( $( "[name='attributedToPojos[" + i + "].attributedTo']" ).val() == '' ) {
			flag = false;
		}
	}
	if ( !flag1 ) {
		alert( "Select at least one Attributed To to configure/update !" );
	}
	else if ( !flag ) {
		alert( "Attributed To cant't be blank !" );
	}
	else {
		if ( validateAttributedToForm() && confirm( 'Are you sure you want to configure/update Attributed To?' ) ) {
			$( '#formAttributedToConfig' ).attr( 'action', 'qrcConfig.do?method=attributedToPage' );
			$( '#formAttributedToConfig' ).submit();
		}
	}

}
function qrcBinMapping( size ){
	flag1 = false;
	for ( var i = 0; i < size; i++ ) {
		if ( $( "[name='categoryBinMappingPojos[" + i + "].editable']" ).attr( 'checked' ) == true ) {
			flag1 = true;
		}
	}
	if ( !flag1 ) {
		alert( "Select at least one Bin Mapping to configure/update !" );
	}
	else {
		if ( qrcBinConfigurationsCheck() && confirm( 'Are you sure you want to configure/update QRC Functional Bin Mapping?' ) ) {
			$( '#qrcBinMapping' ).attr( 'action', 'qrcConfig.do?method=configureQrcFB' );
			$( '#qrcBinMapping' ).submit();
		}
	}
}

function validateConfigResCodeForm(){
	var valid = true;
	var categoryId = $( '#resCodeCatId' ).val();
	var serviceName = $( '#resCodeService' ).val();
	var rcaId = $( '#resCodeRcaId' ).val();

	$( '#resCodeRcaId ~ font' ).addClass( 'hidden' );
	$( '#resCodeService ~ font' ).addClass( 'hidden' );
	$( '#resCodeCatId ~ font' ).addClass( 'hidden' );

	if ( categoryId == '' || categoryId == '0' || categoryId == null || categoryId == undefined ) {
		$( '#resCodeCatId ~ font' ).removeClass( 'hidden' );
		valid = false;
	}
	if ( serviceName == '' || serviceName == '0' || serviceName == null || serviceName == undefined ) {
		$( '#resCodeService ~ font' ).removeClass( 'hidden' );
		valid = false;
	}
	if ( rcaId == '' || rcaId == '0' || rcaId == null || rcaId == undefined ) {
		$( '#resCodeRcaId ~ font' ).removeClass( 'hidden' );
		valid = false;
	}

	return valid;
}

function fillRCAListResCode( category, service, target ){
	var valid = true;

	$( '#resCodeCatId ~ font' ).addClass( 'hidden' );
	$( '#resCodeService ~ font' ).addClass( 'hidden' );

	if ( category == '' || category == '0' || category == null || category == undefined ) {
		$( '#resCodeCatId ~ font' ).removeClass( 'hidden' );
		valid = false;
	}
	if ( service == '' || service == '0' || service == null || service == undefined ) {
		$( '#resCodeService ~ font' ).removeClass( 'hidden' );
		valid = false;
	}
	if ( valid ) {
		$( '.loadingPopup' ).removeClass( 'hidden' );
		$( '#resCodeCatId ~ font' ).addClass( 'hidden' );
		$( '#resCodeService ~ font' ).addClass( 'hidden' );

		crmDwr.getQrcRcaPojos( category, service, function( list ){
			console.log( list );
			dwr.util.removeAllOptions( target );
			dwr.util.addOptions( target, [
				{
					"value" : "0", "label" : "Please Select"
				}
			], "value", "label" );
			if ( list != null && service != "" ) {
				dwr.util.addOptions( target, list, "actionId", "actionTaken" );
			}
			else {
				alert( "No RCA List register for given combination " );
			}
		} );
	}
	else {
		dwr.util.removeAllOptions( target );
		dwr.util.addOptions( target, [
			{
				"value" : "0", "label" : "Please Select"
			}
		], "value", "label" );
	}

	$( '#resolutionCodeList' ).hide();
	$( '.loadingPopup' ).addClass( 'hidden' );
}
function fillResolutionListResCode( rca, target ){
	var valid = true;
	$( '.loadingPopup' ).removeClass( 'hidden' );

	if ( rca == '' || rca == '0' || rca == null || rca == undefined ) {
		$( '#' + target + ' ~ font' ).removeClass( 'hidden' );
		valid = false;
	}
	if ( valid ) {
		$( '#' + target + ' ~ font' ).addClass( 'hidden' );
		crmDwr.getResolutionCodes( rca, function( list ){
			console.log( list );
			dwr.util.removeAllOptions( target );
			dwr.util.addOptions( target, [
				{
					"value" : "0", "label" : "Please Select"
				}
			], "value", "label" );
			if ( list != null ) {
				dwr.util.addOptions( target, list, "rootCauseId", "rootCause" );
			}
			else {
				alert( "No recolution code register for given combination" );
			}
		} );
	}
	else {
		dwr.util.removeAllOptions( target );
		dwr.util.addOptions( target, [
			{
				"value" : "0", "label" : "Please Select"
			}
		], "value", "label" );
	}
	$( '.loadingPopup' ).addClass( 'hidden' );
}

function getResolutionCodes( rcaId ){
	$( '#formResolutionCodeConfig' ).attr( 'action', 'qrcConfig.do?method=getResolutionCodes' );
	$( '#formResolutionCodeConfig' ).submit();
}

function removeResolutionCodeRow( index ){
	$( '#formResolutionCodeConfig' ).attr( 'action', 'qrcConfig.do?method=removeRowResolutionCode&index=' + index );
	$( '#formResolutionCodeConfig' ).submit();
}

function toggleEditableResCode( index ){
	var editChkBox = document.getElementsByName( "qrcRootCausePojos[" + index + "].editable" );
	var resCode = document.getElementsByName( "qrcRootCausePojos[" + index + "].rootCause" );
	var status = document.getElementsByName( "qrcRootCausePojos[" + index + "].status" );

	var attributedTo = document.getElementsByName( "qrcRootCausePojos[" + index + "].attributedTo" );
	if ( editChkBox[ 0 ].checked ) {
		resCode[ 0 ].readOnly = false;
		$( resCode ).removeClass( 'gray_text' );
		attributedTo[ 0 ].readOnly = false;
		$( attributedTo ).removeClass( 'gray_text' );
		status[ 0 ].onclick = "";
		status[ 1 ].onclick = "";
	}
	else {
		resCode[ 0 ].readOnly = true;
		$( resCode ).addClass( 'gray_text' );
		attributedTo[ 0 ].readOnly = true;
		$( attributedTo ).addClass( 'gray_text' );
		status[ 0 ].onclick = function(){
			return false;
		};
		status[ 1 ].onclick = function(){
			return false;
		};
	}
}

function validateAttributedToForm(){
	var valid = true;

	$( '#attrCodeCatId ~ font' ).addClass( 'hidden' );
	$( '#attrCodeService ~ font' ).addClass( 'hidden' );
	$( '#attrCodeRcaId ~ font' ).addClass( 'hidden' );
	$( '#attrCodeResolutionId ~ font' ).addClass( 'hidden' );

	var catId = $( '#attrCodeCatId' ).val();
	var service = $( '#attrCodeService' ).val();
	var rca = $( '#attrCodeRcaId' ).val();
	var resCode = $( '#attrCodeResolutionId' ).val();

	if ( catId == "" || catId == '0' || catId == null || catId == undefined ) {
		$( '#attrCodeCatId ~ font' ).removeClass( 'hidden' );
		valid = false;
	}
	if ( service == "" || service == '0' || service == null || service == undefined ) {
		$( '#attrCodeService ~ font' ).removeClass( 'hidden' );
		valid = false;
	}
	if ( rca == "" || rca == '0' || rca == null || rca == undefined ) {
		$( '#attrCodeRcaId ~ font' ).removeClass( 'hidden' );
		valid = false;
	}
	if ( resCode == "" || resCode == '0' || resCode == null || resCode == undefined ) {
		$( '#attrCodeResolutionId ~ font' ).removeClass( 'hidden' );
		valid = false;
	}

	return valid;
}

function toggleEditableAttributedCode( index ){
	var editChkBox = document.getElementsByName( "attributedToPojos[" + index + "].editable" );
	var resCode = document.getElementsByName( "attributedToPojos[" + index + "].attributedTo" );
	var status = document.getElementsByName( "attributedToPojos[" + index + "].status" );
	if ( editChkBox[ 0 ].checked ) {
		resCode[ 0 ].readOnly = false;
		$( resCode ).removeClass( 'gray_text' );
		status[ 0 ].onclick = "";
		status[ 1 ].onclick = "";
	}
	else {
		resCode[ 0 ].readOnly = true;
		$( resCode ).addClass( 'gray_text' );
		status[ 0 ].onclick = function(){
			return false;
		};
		status[ 1 ].onclick = function(){
			return false;
		};
	}
}
function getAttributedTo( resolutionCodeId ){
	$( '#formAttributedToConfig' ).attr( 'action', 'qrcConfig.do?method=getAttributedTo' );
	$( '#formAttributedToConfig' ).submit();
}

function removeAttributedToRow( index ){
	$( '#formAttributedToConfig' ).attr( 'action', 'qrcConfig.do?method=removeRowAttributedTo&index=' + index );
	$( '#formAttributedToConfig' ).submit();
}
function fillSubCatQrcConfig( categoryId, subCatId ){
	var moduleType = $( '#qrcConfigModuleType' ).val();
	crmDwr.getActiveSubCategoriesByModuleType( categoryId, moduleType, function( list ){
		addQrcSubCat( subCatId, list );
	} );
	function addQrcSubCat( id, list ){
		var select = document.getElementById( id );
		if ( select != null ) {
			if ( list != null ) {
				dwr.util.removeAllOptions( id );
				dwr.util.addOptions( id, [
					"Please Select"
				] );
				dwr.util.addOptions( id, list, "qrcSubCategoryId", "qrcSubCategory" );

			}
		}
		$( '.loadingPopup' ).addClass( 'hidden' );
	}
}

function getSubSubCat(){

	if ( $( '#IDsubCatQRCSUBSUBCAT' ).val() != 0 ) {

		// alert( 'subsubCatId:' + $( '#IDsubCatQRCSUBSUBCAT' ).val() );
		$( '#formConfigureSubSubCat' ).attr( 'action', 'qrcConfig.do?method=getSubSubCat' );
		$( '#formConfigureSubSubCat' ).submit();
	}
	else {
		$( '#subCatListArea' ).hide();
	}

}

function getQrcRcaConfigurations( inCategoryID, inServiceName ){
	$( '#rcaCategoryID' ).next( 'font' ).hide();
	$( '#rcaServiceName' ).next( 'font' ).hide();
	if ( qrcRcaConfigurationsCheck() ) {
		$( '#qrcRcaConfiguration' ).attr( 'action', 'qrcConfig.do?method=getQrcRcaConfigurations' );
		$( '#qrcRcaConfiguration' ).submit();
	}
}

function qrcRcaConfigurationsCheck(){
	var valid = true;
	var inCategoryID = document.getElementById( "rcaCategoryID" ).value;
	var inServiceName = document.getElementById( "rcaServiceName" ).value;
	if ( inCategoryID == '' || inCategoryID == '0' || inCategoryID == null || inCategoryID == undefined ) {
		$( '#rcaCategoryID' ).next( 'font' ).show().html( "Please select Category" ).addClass( 'errorTextbox' );
		valid = false;
	}
	if ( inServiceName == '' || inServiceName == '0' || inServiceName == null || inServiceName == undefined ) {
		$( '#rcaServiceName' ).next( 'font' ).show().html( "Please select Service Name" ).addClass( 'errorTextbox' );
		valid = false;
	}
	if ( valid ) {
		return true;
	}
	else {
		return false;
	}
}

function getQrcBinConfigurations(){
	if ( qrcBinConfigurationsCheck() ) {
		$( '#qrcBinMapping' ).attr( 'action', 'qrcConfig.do?method=getQrcBinConfigurations' );
		$( '#qrcBinMapping' ).submit();
	}
}

function removeRCAConfiguration( index ){
	$( '#qrcRcaConfiguration' ).attr( 'action', 'qrcConfig.do?method=removeRCAConfiguration&index=' + index );
	$( '#qrcRcaConfiguration' ).submit();
}

function removeBinConfiguration( index ){
	$( '#qrcBinMapping' ).attr( 'action', 'qrcConfig.do?method=removeBinConfiguration&index=' + index );
	$( '#qrcBinMapping' ).submit();
}

function toggleEditableRCA( index ){
	var editChkBox = document.getElementsByName( "qrcActionTakenPojos[" + index + "].editable" );
	var resCode = document.getElementsByName( "qrcActionTakenPojos[" + index + "].actionTaken" );
	var status = document.getElementsByName( "qrcActionTakenPojos[" + index + "].status" );
	if ( editChkBox[ 0 ].checked ) {
		resCode[ 0 ].readOnly = false;
		$( resCode ).removeClass( 'gray_text' );
		status[ 0 ].onclick = "";
		status[ 1 ].onclick = "";
	}
	else {
		resCode[ 0 ].readOnly = true;
		$( resCode ).addClass( 'gray_text' );
		status[ 0 ].onclick = function(){
			return false;
		};
		status[ 1 ].onclick = function(){
			return false;
		};
	}
}

function listActions( inDestinationID, inSubSubCategoryId ){
	$( '.loadingPopup' ).removeClass( 'hidden' );
	if ( qrcBinConfigurationsCheck() ) {
		var categoryID = document.getElementById( "binCategoryID" ).value;
		var subCategoryID = document.getElementById( "binSubCategoryId" ).value;
		crmDwr.getQrcActionsList( categoryID, subCategoryID, inSubSubCategoryId, function( list ){
			addActions( inDestinationID, list );
			getQrcBinConfigurations();
		} );

		function addActions( id, list ){
			var select = document.getElementById( id );
			if ( select != null ) {
				if ( list != null ) {
					var actions = [];
					for ( var i = 0; i < list.length; i++ ) {
						actions.push( list[ i ].contentName );
					}
					select.value = actions;
				}
				else {
					alert( "Actions List is empty for current selected sub-sub-category." );
					removeList( id );
				}
			}
		}

	}
}

function toggleEditableBinMapping( index ){
	var editChkBox = document.getElementsByName( "categoryBinMappingPojos[" + index + "].editable" );
	var fromBinId = document.getElementsByName( "categoryBinMappingPojos[" + index + "].fromBinId" );
	var toBinId = document.getElementsByName( "categoryBinMappingPojos[" + index + "].toBinId" );
	var status = document.getElementsByName( "categoryBinMappingPojos[" + index + "].status" );
	if ( editChkBox[ 0 ].checked ) {
		fromBinId[ 0 ].disabled = false;
		toBinId[ 0 ].disabled = false;
		status[ 0 ].onclick = "";
		status[ 1 ].onclick = "";
	}
	else {
		fromBinId[ 0 ].disabled = true;
		toBinId[ 0 ].disabled = true;
		status[ 0 ].onclick = function(){
			return false;
		};
		status[ 1 ].onclick = function(){
			return false;
		};
	}
}

function qrcBinConfigurationsCheck(){
	var valid = true;
	var inCategoryID = document.getElementById( "binCategoryID" ).value;
	var inSubCategoryID = document.getElementById( "binSubCategoryId" ).value;
	var inSubSubCategoryID = document.getElementById( "binSubSubCategoryId" ).value;
	var inQrcType = $( '[name="qrcType"]:checked' ).val();

	if ( inCategoryID == '' || inCategoryID == '0' || inCategoryID == null || inCategoryID == undefined ) {
		$( '#binCategoryID' ).next( 'font' ).show().html( "Please select Category" ).addClass( 'errorTextbox' );
		valid = false;
	}
	if ( inSubCategoryID == '' || inSubCategoryID == '0' || inSubCategoryID == null || inSubCategoryID == undefined
			|| inSubCategoryID == 'Please Select' ) {
		$( '#binSubCategoryId' ).next( 'font' ).show().html( "Please select Sub Category" ).addClass( 'errorTextbox' );
		valid = false;
	}
	if ( inSubSubCategoryID == '' || inSubSubCategoryID == '0' || inSubSubCategoryID == null || inSubSubCategoryID == undefined
			|| inSubSubCategoryID == 'Please Select' ) {
		$( '#binSubSubCategoryId' ).next( 'font' ).show().html( "Please select Sub Sub Category" ).addClass( 'errorTextbox' );
		valid = false;
	}
	if ( inQrcType == '' || inQrcType == '0' || inQrcType == null || inQrcType == undefined ) {
		$( '#qrcTypeComplaint' ).parent().next( 'font' ).show().html( "Please select QRC Type" ).addClass( 'errorTextboxTop42' );
		valid = false;
	}
	if ( valid ) {
		return true;
	}
	else {
		return false;
	}
}

function addRowSubSubCatQRCConfig(){

	if ( subsubCat_commonEmptyCheck() ) {
		$( '#formConfigureSubSubCat' ).attr( 'action', 'qrcConfig.do?method=addRowSubSubCat' );
		$( '#formConfigureSubSubCat' ).submit();
	}
}
function removeRowSubSubCatQRCConfig( index ){
	$( '#formConfigureSubSubCat' ).attr( 'action', 'qrcConfig.do?method=removeRowSubSubCat&index=' + index );
	$( '#formConfigureSubSubCat' ).submit();
}
function submitSubSubCatQRC( size ){

	// alert('size:'+size);
	var checkValidation = subsubCat_commonEmptyCheck();
	if ( checkValidation && size == 0 ) {
		$( '#role_group_view' ).prev( 'font' ).show().html( "please add 'Sub Sub Category'" ).addClass( 'errorTextbox' ).css( 'top', 45 ).css(
				'width', 220 ).css( 'left', 42 );
		// alert( "please add 'Sub Sub Category'" );
		return false;

	}
	else if ( checkValidation && checkPojos( size ) ) {
		var reply = confirm( "Are you sure you want to configure Sub Sub Category?" );
		if ( reply ) {
			$( '#formConfigureSubSubCat' ).attr( 'action', 'qrcConfig.do?method=configureSubSubCat' );
			$( '#formConfigureSubSubCat' ).submit();
		}
	}
}

function subsubCat_commonEmptyCheck(){
	$( '#qrcConfigModuleType' ).parent().next( 'font' ).hide();
	$( '#IDcatQRCSUBSUBCAT' ).parent().next( 'font' ).hide();
	$( '#IDsubCatQRCSUBSUBCAT' ).parent().next( 'font' ).hide();

	if ( $( '#qrcConfigModuleType' ).val() == '0' ) {
		$( '#qrcConfigModuleType' ).parent().next( 'font' ).show().html( "Please select Module Type" ).addClass( 'errorTextbox' );
		return false;
	}
	else if ( $( '#IDcatQRCSUBSUBCAT' ).val() == '' || $( '#IDcatQRCSUBSUBCAT' ).val() == '0' ) {
		$( '#IDcatQRCSUBSUBCAT' ).parent().next( 'font' ).show().html( "Please select Category ID" ).addClass( 'errorTextbox' );
		return false;
	}
	else if ( $( '#IDsubCatQRCSUBSUBCAT' ).val() == '' || $( '#IDsubCatQRCSUBSUBCAT' ).val() == '0'
			|| $( '#IDsubCatQRCSUBSUBCAT' ).val() == 'Please Select' ) {
		$( '#IDsubCatQRCSUBSUBCAT' ).parent().next( 'font' ).show().html( "Please select Sub Category ID" ).addClass( 'errorTextbox' );
		return false;
	}
	else {
		return true;
	}
}
function checkPojos( size ){
	flag = true;
	id = 'qrcSubSubCategoriesPojos[';
	cat = '].qrcSubSubCategory';
	qrcType = '].qrcType';
	resType = '].resolutionType';
	fb = '].functionalBinId';

	// console.log("[name='"+id+1+cat+"']");
	for ( var i = 0; i < size; i++ ) {
		// alert(id+i+cat);
		// alert($("[name='"+id+i+cat+"']").val());
		$( "[name='" + id + i + cat + "']" ).next( 'font' ).hide();
		$( "[name='" + id + i + qrcType + "']" ).parent().next( 'font' ).hide();
		$( "[name='" + id + i + resType + "']" ).parent().next( 'font' ).hide();
		$( "[name='" + id + i + fb + "']" ).parent().next( 'font' ).hide();
		if ( !validateNotAllowSpecialCharacter( $( "[name='" + id + i + cat + "']" ).val() ) ) {
			$( "[name='" + id + i + cat + "']" ).next( 'font' ).show().html( "Please enter valid 'Sub Sub Category'" ).addClass( 'errorTextbox' )
					.css( 'top', 45 ).css( 'width', 220 ).css( 'left', 42 );
			flag = false;
		}
		if ( $( "[name='" + id + i + cat + "']" ).val() == '' ) {
			// alert(i);
			$( "[name='" + id + i + cat + "']" ).next( 'font' ).show().html( "Please enter 'Sub Sub Category'" ).addClass( 'errorTextbox' ).css(
					'top', 45 ).css( 'width', 220 ).css( 'left', 42 );
			flag = false;
		}
		if ( $( "[name='" + id + i + qrcType + "']" ).val() == '' || $( "[name='" + id + i + qrcType + "']" ).val() == '0' ) {
			$( "[name='" + id + i + qrcType + "']" ).parent().next( 'font' ).show().html( "Please select" ).addClass( 'errorTextbox' )
					.css( 'top', 45 ).css( 'width', 220 ).css( 'left', 340 );
			flag = false;
		}
		if ( $( "[name='" + id + i + resType + "']" ).val() == '' || $( "[name='" + id + i + resType + "']" ).val() == '-1' ) {
			$( "[name='" + id + i + resType + "']" ).parent().next( 'font' ).show().html( "Please select" ).addClass( 'errorTextbox' )
					.css( 'top', 45 ).css( 'width', 220 ).css( 'left', 490 );
			flag = false;
		}
		if ( $( "[name='" + id + i + fb + "']" ).val() == '' || $( "[name='" + id + i + fb + "']" ).val() == '0' ) {
			$( "[name='" + id + i + fb + "']" ).parent().next( 'font' ).show().html( "Please select" ).addClass( 'errorTextbox' ).css( 'top', 45 )
					.css( 'width', 220 ).css( 'left', 645 );
			flag = false;
		}
	}
	return flag;
}

function qrcCategoriesByModyleType( inModuleType, inDestinationID ){
	var subCategoryID = document.getElementById( "IDsubCatQRCSUBSUBCAT" );
	crmDwr.getCategoryiesByModuleType( inModuleType, function( list ){
		addQrcCat( inDestinationID, list );
	} );
	function addQrcCat( id, list ){
		var select = document.getElementById( id );
		if ( select != null ) {
			if ( list != null ) {
				dwr.util.removeAllOptions( id );
				dwr.util.removeAllOptions( subCategoryID );
				dwr.util.addOptions( id, [
					{
						"value" : "0", "label" : "Please Select"
					}
				], "value", "label" );
				dwr.util.addOptions( subCategoryID, [
					{
						"value" : "0", "label" : "Please Select"
					}
				], "value", "label" );
				$( '#subCatListArea' ).hide();
				dwr.util.addOptions( id, list, "qrcCategoryId", "qrcCategory" );

			}
			else {
				dwr.util.removeAllOptions( id );
				dwr.util.removeAllOptions( subCategoryID );
				dwr.util.addOptions( id, [
					{
						"value" : "0", "label" : "Please Select"
					}
				], "value", "label" );
				dwr.util.addOptions( subCategoryID, [
					{
						"value" : "0", "label" : "Please Select"
					}
				], "value", "label" );
				$( '#subCatListArea' ).hide();
			}
		}
		$( '.loadingPopup' ).addClass( 'hidden' );
	}
}

function populateSubCategoriesforSubSubCat( inAccessID, inDestinationID ){
	var valid = true;
	$( '#qrcConfigModuleType ~ font' ).addClass( 'hidden' );
	$( '#IDcatQRCSUBSUBCAT ~ font' ).addClass( 'hidden' );
	$( '#IDsubCatQRCSUBSUBCAT ~ font' ).addClass( 'hidden' );
	if ( inAccessID == '' || inAccessID == '0' || inAccessID == null || inAccessID == undefined || inAccessID == 'Please Select' ) {
		$( '#binCategoryID ~ font' ).removeClass( 'hidden' );
		valid = false;
	}
	if ( valid ) {
		$( '.loadingPopup' ).removeClass( 'hidden' );
		$( '#IDcatQRCSUBSUBCAT ~ font' ).addClass( 'hidden' );
		fillSubCatQrcConfig( inAccessID, inDestinationID );
	}
	else {
		dwr.util.removeAllOptions( inDestinationID );
		dwr.util.addOptions( inDestinationID, [
			{
				"value" : "0", "label" : "Please Select"
			}
		], "value", "label" );
	}

	$( '#subCatListArea' ).hide();
}
function populateRCAforAttributed( inAccessID, inServiceValue, inDestinationID, ResolutionCodeID ){
	var valid = true;
	$( '#attrCodeCatId ~ font' ).addClass( 'hidden' );

	$( '#inDestinationID ~ font' ).addClass( 'hidden' );
	$( '#ResolutionCodeID ~ font' ).addClass( 'hidden' );
	if ( inAccessID == '' || inAccessID == '0' || inAccessID == null || inAccessID == undefined || inAccessID == 'Please Select' ) {
		$( '#attrCodeCatId ~ font' ).removeClass( 'hidden' );
		valid = false;
	}
	if ( valid ) {
		$( '#inDestinationID ~ font' ).addClass( 'hidden' );
		$( '#ResolutionCodeID ~ font' ).addClass( 'hidden' );

		fillRCAListResCode( inAccessID, inServiceValue, inDestinationID );
		dwr.util.removeAllOptions( ResolutionCodeID );

		dwr.util.addOptions( ResolutionCodeID, [
			{
				"value" : "0", "label" : "Please Select"
			}
		], "value", "label" );

	}

	$( '#displayAreaID' ).hide();
}
