$( document ).ready(
		function(){
			$.validator.addMethod( 'partnerName', function( value ){
				return ( value != '0' );
			} );
			$.validator.addMethod( 'product', function( value ){
				return ( value != '' );
			} );
			// FormgisAddExcel Fields
			$.validator.addMethod( "stateId", function( value ){
				return ( value != "" );
			} );
			$.validator.addMethod( "area1", function( value ){
				return ( value != "Please Select" );
			} );
			$.validator.addMethod( "city", function( value ){
				return ( value != "Please Select" );
			} );
			$.validator.addMethod( "typeOfField", function( value ){
				return ( value != "0" );
			} );
			$.validator.addMethod( "maxlength6", function( value, element ){
				return this.optional( element ) || validatePin( value );
			} );
			$.validator.addMethod( "societyPojo.localityName", function( value, element ){
				return ( value != "0" );
			} );
			$.validator.addMethod( "societyPojo.latitude", function( value, element ){
				return this.optional( element ) || validateLatitude( value );
			} );
			$.validator.addMethod( "societyPojo.longitude", function( value, element ){
				return this.optional( element ) || validateLatitude( value );
			} );
			$.validator.addMethod( "societyPojo.societyName", function( value, element ){
				return ( value != "0" );
			} );
			$.validator.addMethod( 'networkPartnerPojo.rfsDus', function( value ){
				return ( value != '0' );
			} );
			jQuery.validator.addMethod( "alpha", function( value, element ){
				return this.optional( element ) || validateCustomerCategory( value );
			}, "Only Characters Allowed." );

			$( "#FORMgisAddExcel" ).validate(
					{
						rules : {
							stateId : {
								stateId : true
							}, 'societyPojo.areaId' : {
								area1 : true
							}, city : {
								city : true
							}, productArray : {
								required : true
							}, partnerName : {
								partnerName : true
							}, 'societyPojo.networkAvailability' : {
								typeOfField : true
							}, 'societyPojo.localityName' : {
								required : true, minlength : 3
							}, 'societyPojo.societyName' : {
								'societyPojo.societyName' : true
							}, 'networkPartnerPojo.rfsDus' : {
								required : true, digits : true,
								'networkPartnerPojo.rfsDus':true
							}, 'societyPojo.customerCategory' : {
								dropDown : true
							}, 'societyPojo.primaryPincode' : {
								required : true, maxlength6 : true
							}, 'societyPojo.latitude' : {
								required : function(element) {
									if ( $('#productName').val()=='EOC') {
										   return false;
										}
										else {
										return true;
										}
									  }, 
								'societyPojo.latitude' : true
							}, 'societyPojo.longitude' : {
								required : function(element) {
									if ( $('#productName').val()=='EOC') {
										   return false;
										}
										else {
										return true;
										}
									  },  
								'societyPojo.longitude' : true
							}

						},
						messages : {
							stateId : {
								stateId : "<font class='errorSocietyText'>Please select 'State'</font>"
							},
							'societyPojo.areaId' : {
								area1 : "<font class='errorSocietyText'>Please select 'Area'</font>"
							},
							city : {
								city : "<font class='errorSocietyText'>Please select 'City'</font>"
							},
							productArray : {
								required : "<font class='errorSocietyText' style='top:95px'> Please select 'Product'</font>"
							},
							partnerName : {
								partnerName : "<font class='errorSocietyText'> Please select 'Network Partner'</font>"
							},
							'societyPojo.networkAvailability' : {
								typeOfField : "<font class='errorSocietyText'>Please select 'Type of Field'</font>"
							},
							'societyPojo.localityName' : {
								required : "<font class='errorSocietyText'>Please provide 'Locality'</font>",
								minlength : "<font class='errorSocietyText'>Should be alpha numeric with length(3-50)</font>"
							},
							'societyPojo.societyName' : {
								'societyPojo.societyName' : "<font class='errorSocietyText'>Please provide valid 'Society Name'</font>"
							},
							'networkPartnerPojo.rfsDus' : {
								required : "<font class='errorSocietyText'>Please provide 'RFS DUs'</font>",
								digits : "<font class='errorSocietyText'>Only digits allowed</font>",
								'networkPartnerPojo.rfsDus' : "<font class='errorSocietyText'> Please provide 'RFS DUs'</font>"
							},
							'societyPojo.customerCategory' : {
								dropDown : "<font class='errorSocietyText'>Please select 'Customer Category'</font>",
							},
							'societyPojo.primaryPincode' : {
								required : "<font class='errorCreateUser'> Please provide 'Primary Pin Code'</font>",
								maxlength6 : "<font class='errorCreateUser'>Pincode must be of 6 digits only.</font>"
							}, 'societyPojo.latitude' : {
								required : "<font class='errorCreateUser'> Please provide 'Latitude'</font>",
								'societyPojo.latitude' : "<font class='errorCreateUser'>Only number,decimals allowed</font>",
							}, 'societyPojo.longitude' : {
								required : "<font class='errorCreateUser'> Please provide 'Longitude'</font>",
								'societyPojo.longitude' : "<font class='errorCreateUser'>Only number,decimals allowed</font>",
							}
						}
					} );

			$( "#IDuploadGis" ).validate( {
				rules : {
					productArray : {
						required : true
					}, partnerName : {
						partnerName : true
					},
				}, messages : {
					partnerName : {
						partnerName : "<font class='errorCreateUserSelect'>Please select 'Network Partner'</font>"
					}, productArray : {
						required : "<font class='errorSocietyText' style='top:95px'>Please select  'Product'</font>"
					}
				}
			} );

			$( "#idUploadLmsFile" ).validate(
					{
						rules : {
							'remarksPojo.remarks' : {
								maxlength : 4000, required : true
							}

						},
						messages : {
							'remarksPojo.remarks' : {
								required : "<font class='Lms_Reference'>Please provide 'Remarks'</font>",
								maxlength : "<font class='Lms_Reference'>Suggested 'Remarks' length 4000.</font>"
							}
						}
					} );

			$( '#upload_uploadFile' ).click( function(){

				var uploadType = document.getElementById( "fileType" ).value;

				if ( uploadType == "gis" ) {
					if ( ( $( "#IDuploadGis" ).valid() ) ) {
						if ( checkFileExtension() == 1 ) {
							var answer = confirm( "Are you sure you want to upload file?" );
							if ( answer ) {
								document.forms[ 1 ].action = "gis.do?method=uploadGis";
								document.forms[ 1 ].submit();
							}
						}
						else {
							alert( "Please select valid excel file." );
						}
					}
				}
				else if ( uploadType == "lms" ) {
					if ( checkFileExtension() == 1 && $( "#idUploadLmsFile" ).valid() ) {
						var answer = confirm( "Are you sure you want to upload file?" );
						if ( answer ) {
							document.forms[ 1 ].action = "leadGeneration.do?method=lmsFileUpload";
							document.forms[ 1 ].submit();
						}
					}
				}
				else if ( uploadType == "upFront" ) {
					if ( $( "#partnerIdCRF" ).val() === '0' ) {
						$( "#partnerIdCRF" ).next( 'font' ).show().html( "Please select 'Business Partner'" ).addClass( 'errorTextbox' );
					}
					else if ( $( "#remarks" ).val().trim() === '' ||  $( "#remarks" ).val().trim().length < 2 || $( "#remarks" ).val().trim().length > 4000) {
						$( "#partnerIdCRF" ).next( 'font' ).hide();
						$( "#remarks" ).next( 'font' ).show().html( "Please enter 'Remarks' between [2-4000]" ).addClass( 'errorAddress' );
					}
					else if ( checkFileExtension() == 1 ) {
						$( "#partnerIdCRF" ).next( 'font' ).hide();
						$( "#remarks" ).next( 'font' ).hide();
						document.forms[ 1 ].action = "upFrontPaymentRecovery.do?method=fileUploadUFPayment";
						document.forms[ 1 ].submit();
					}
				}
			} );
			// ************GIS Master Data***************//
			$( '#searchState' ).click( function(){
				var stateSizeId = document.getElementById( "stateSize" );
				var stateSize = 0;
				var proceed = true;
				if ( null != stateSizeId ) {
					stateSize = stateSizeId.value;
				}
				if ( stateSize > 0 ) {
					for ( var rCount = 0; rCount < stateSize; rCount++ ) {
						var editable = document.getElementsByName( "stateDataList[" + rCount + "].editable" );
						if ( editable[ 0 ].checked ) {
							proceed = false;
							break;
						}
					}
				}
				var answer = true;
				if ( !proceed ) {
					answer = confirm( "Are you sure you want to search? All your data (selected/modified) will be lost." );
				}
				if ( answer ) {
					document.forms[ 1 ].action = "gisMaster.do?method=searchState";
					document.forms[ 1 ].submit();
				}
			} );
			$( '#searchCity' ).click( function(){
				var sizeId = document.getElementById( "citySize" );
				var listSize = 0;
				var proceed = true;
				if ( null != sizeId ) {
					listSize = sizeId.value;
				}
				if ( listSize > 0 ) {
					for ( var rCount = 0; rCount < listSize; rCount++ ) {
						var editable = document.getElementsByName( "cityDataList[" + rCount + "].editable" );
						if ( editable[ 0 ].checked ) {
							proceed = false;
							break;
						}
					}
				}
				var answer = true;
				if ( !proceed ) {
					answer = confirm( "Are you sure you want to search? All your data (selected/modified) will be lost." );
				}
				if ( answer ) {
					document.forms[ 1 ].action = "gisMaster.do?method=searchCity";
					document.forms[ 1 ].submit();
				}
			} );
			$( '#searchArea' ).click( function(){
				var sizeId = document.getElementById( "areaSize" );
				var listSize = 0;
				var proceed = true;
				if ( null != sizeId ) {
					listSize = sizeId.value;
				}
				if ( listSize > 0 ) {
					for ( var rCount = 0; rCount < listSize; rCount++ ) {
						var editable = document.getElementsByName( "areaDataList[" + rCount + "].editable" );
						if ( editable[ 0 ].checked ) {
							proceed = false;
							break;
						}
					}
				}
				var answer = true;
				if ( !proceed ) {
					answer = confirm( "Are you sure you want to search? All your data (selected/modified) will be lost." );
				}
				if ( answer ) {
					document.forms[ 1 ].action = "gisMaster.do?method=searchArea";
					document.forms[ 1 ].submit();
				}
			} );

			$( '#addStateRow' ).click( function(){
				document.forms[ 1 ].action = "gisMaster.do?method=addStateRow";
				document.forms[ 1 ].submit();
			} );
			$( '#addState' ).click( function(){
				var stateSizeId = document.getElementById( "stateSize" );
				var stateSize = 0;
				var proceed = false;
				if ( null != stateSizeId ) {
					stateSize = stateSizeId.value;
				}
				if ( stateSize > 0 ) {
					for ( var rCount = 0; rCount < stateSize; rCount++ ) {
						var editable = document.getElementsByName( "stateDataList[" + rCount + "].editable" );
						if ( editable[ 0 ].checked ) {
							proceed = true;
							break;
						}
					}
				}
				if ( proceed ) {
					document.forms[ 1 ].action = "gisMaster.do?method=addNewState";
					document.forms[ 1 ].submit();
				}
				else {
					alert( "Please select at least one State." );
				}
			} );
			$( '#addCityRow' ).click( function(){
				document.forms[ 1 ].action = "gisMaster.do?method=addCityRow";
				document.forms[ 1 ].submit();

			} );
			$( '#addCity' ).click( function(){
				var sizeId = document.getElementById( "citySize" );
				var listSize = 0;
				var proceed = false;
				if ( null != sizeId ) {
					listSize = sizeId.value;
				}
				if ( listSize > 0 ) {
					for ( var rCount = 0; rCount < listSize; rCount++ ) {
						var editable = document.getElementsByName( "cityDataList[" + rCount + "].editable" );
						if ( editable[ 0 ].checked ) {
							proceed = true;
							break;
						}
					}
				}
				if ( proceed ) {
					document.forms[ 1 ].action = "gisMaster.do?method=addNewCity";
					document.forms[ 1 ].submit();
				}
				else {
					alert( "Please select at least one City." );
				}
			} );
			$( '#addAreaRow' ).click( function(){
				document.forms[ 1 ].action = "gisMaster.do?method=addAreaRow";
				document.forms[ 1 ].submit();
			} );
			$( '#addArea' ).click( function(){
				var sizeId = document.getElementById( "areaSize" );
				var listSize = 0;
				var proceed = false;
				if ( null != sizeId ) {
					listSize = sizeId.value;
				}
				if ( listSize > 0 ) {
					for ( var rCount = 0; rCount < listSize; rCount++ ) {
						var editable = document.getElementsByName( "areaDataList[" + rCount + "].editable" );
						if ( editable[ 0 ].checked ) {
							proceed = true;
							break;
						}
					}
				}
				if ( proceed ) {
					document.forms[ 1 ].action = "gisMaster.do?method=addNewArea";
					document.forms[ 1 ].submit();
				}
				else {
					alert( "Please select at least one Area." );
				}
			} );

			$( '#addLocalityRow' ).click( function(){
				document.forms[ 1 ].action = "gisMaster.do?method=addLocalityRow";
				document.forms[ 1 ].submit();

			} );
			$( '#addLocality' ).click( function(){
				var sizeId = document.getElementById( "localitySize" );
				var listSize = 0;
				var proceed = false;
				if ( null != sizeId ) {
					listSize = sizeId.value;
				}
				if ( listSize > 0 ) {
					for ( var rCount = 0; rCount < listSize; rCount++ ) {
						var editable = document.getElementsByName( "localityList[" + rCount + "].editable" );
						if ( editable[ 0 ].checked ) {
							proceed = true;
							break;
						}
					}
				}
				if ( proceed ) {
					document.forms[ 1 ].action = "gisMaster.do?method=addNewLocality";
					document.forms[ 1 ].submit();
				}
				else {
					alert( "Please select at least one Locality." );
				}
			} );
			// ************GIS Master Data End***************//
			// ///////////////////// GIS
			// //////////////////////////////////
			$.validator.addMethod( 'gisState', function( value ){
				return ( value != '0' );
			} );
			$.validator.addMethod( 'gisCity', function( value ){
				return ( value != '0' );
			} );
			$.validator.addMethod( 'gisArea', function( value ){
				return ( value != '0' );
			} );
			$.validator.addMethod( 'gisLocality', function( value ){
				return ( value != '0' );
			} );
			$.validator.addMethod( 'gisTypeField', function( value ){
				return ( value != '0' );
			} );
			$.validator.addMethod( 'gisFtth', function( value ){
				return ( value != '0' );
			} );
			$( "#gisAddExcel" ).validate(
					{
						rules : {
							gisSociety : {
								required : true
							}, gisPincode : {
								required : true, digits : true
							}, gisState : {
								gisState : true
							}, gisCity : {
								gisCity : true
							}, gisArea : {
								gisArea : true
							}, gisLocality : {
								gisLocality : true
							}, gisTypeField : {
								gisTypeField : true
							}, gisFtth : {
								gisFtth : true
							}

						},
						messages : {
							gisSociety : {
								required : "<font class='gisText'>Please insert Society Name</font>"
							},
							gisPincode : {
								required : "<font class='gisText'>Please insert Pin code</font>",
								digits : "<font class='gisText'>Please insert only Digits</font>"
							}, gisState : {
								gisState : "<font class='gis'>Please select State</font>"
							}, gisCity : {
								gisCity : "<font class='gis'>Please select City</font>"
							}, gisArea : {
								gisArea : "<font class='gis'>Please select Area</font>"
							}, gisLocality : {
								gisLocality : "<font class='gis'>Please select Locality</font>"
							}, gisTypeField : {
								gisTypeField : "<font class='gis'>Please select Type of Field</font>"
							}, gisFtth : {
								gisFtth : "<font class='gis'>Please select FTTH/FTTX</font>"
							}
						}
					} );
			$( '.manageGIS li' ).click( function(){
				$( '.manageGIS li' ).removeClass( 'active' );
				$( this ).addClass( 'active' );
				return true;
			} );
			// add Society data by Excel || gisExcel
			$( '#submitGisAddExcel' ).click( function(){
				if ( $( "#FORMgisAddExcel" ).valid() ) {
					document.forms[ 1 ].action = "gis.do?method=createSociety";
					document.forms[ 1 ].submit();
				}

			} );
			$( "#searchSociety" ).validate( {
				rules : {}, messages : {
					'societyPojo.societyName' : {
						required : "<font class='gisText'>Please insert Society Name</font>"
					}
				}
			} );
			$( "#addSocietyData" ).validate( {
				rules : {
					gisSociety : {
						required : true
					}
				}, messages : {
					gisSociety : {
						required : "<font class='gisText'>Please insert Society Name</font>"
					}
				}
			} );

			// / show n hide content on radio GIS
			// $(".hide").hide();
			// $('input:radio[name=data_type]').change(function(){
			// var test = $(this).val();
			// $(".hide").hide();
			// $('#height200').hide();
			// });

			// ////////////// GIS
			$( '.searchView' ).hide();
			$( 'input:radio[name=manageGis]' ).change( function(){
				var test = $( this ).val();
				$( ".stateHide" ).hide();
				$( ".cityHide" ).hide();
				$( ".areaHide" ).hide();
				$( ".localityHide" ).hide();
				$( "#gisError" ).hide();
				$( "#gisMessage" ).hide();
				$( "#" + test ).show();
				$( '#height200' ).hide();
			} );

			// state change
			$( '#state' ).change( function(){
				if ( $( this ).val() != "" ) {
					// $('#city').removeAttr('disabled');
					if ( $( this ).val() != "Please Select" ) {
						populateCity( 'city', $( this ).val() );
					}
				}
				else {
					// $('#city').attr('disabled', 'disabled');
					removeList( 'city' );
					removeList( 'area' );
					removeList( 'locality' );
					var hiddenGis = document.getElementById( "gisHidden" );
					if ( ( hiddenGis != null ) && ( hiddenGis.value == "search" ) ) {
						dwr.util.addOptions( 'city', [
							"All Cities"
						] );
						dwr.util.addOptions( 'area', [
							"All Area"
						] );
						dwr.util.addOptions( 'locality', [
							"All Localites"
						] );
					}
					else {
						dwr.util.addOptions( 'city', [
							"Please Select"
						] );
						dwr.util.addOptions( 'area', [
							"Please Select"
						] );
						dwr.util.addOptions( 'locality', [
							"Please Select"
						] );
					}
				}
			} );
			$( '#city' ).change( function(){
				if ( $( this ).val() != "" ) {
					if ( ( $( this ).val() != "Please Select" ) && ( $( this ).val() != "All Cities" ) ) {
						populateArea( 'area', $( this ).val() );
					}
					else {
						removeList( 'area' );
						removeList( 'locality' );
						var hiddenGis = document.getElementById( "gisHidden" );
						if ( ( hiddenGis != null ) && ( hiddenGis.value == "search" ) ) {
							dwr.util.addOptions( 'area', [
								"All Area"
							] );
							dwr.util.addOptions( 'locality', [
								"All Localites"
							] );
						}
						else {
							dwr.util.addOptions( 'area', [
								"Please Select"
							] );
							dwr.util.addOptions( 'locality', [
								"Please Select"
							] );
						}
					}
				}
			} );

			$( '#area' ).change( function(){
				if ( $( this ).val() != "" ) {
					if ( ( $( this ).val() != "Please Select" ) && ( $( this ).val() != "All Area" ) ) {
						// $('#locality').removeAttr('disabled');
						// populateLocality( 'locality', $( this ).val() );
					}
					else {
						removeList( 'locality' );
						var hiddenGis = document.getElementById( "gisHidden" );
						if ( ( hiddenGis != null ) && ( hiddenGis.value == "search" ) ) {
							dwr.util.addOptions( 'locality', [
								"All Localites"
							] );
						}
						else {
							dwr.util.addOptions( 'locality', [
								"Please Select"
							] );
						}
					}
				}
			} );
			// search society
			$( '#submit_searchGIS' ).click( function(){
				if ( $( "#searchSociety" ).valid() ) {
					$( '.loadingPopup' ).removeClass( 'hidden' );
					document.forms[ 1 ].action = "gis.do?method=searchSociety";
					document.forms[ 1 ].submit();
				}
			} );
			$( '#secPincode' ).keyup( function(){
				value = $( this );
				if ( !validatePin( value.val() ) ) {
					$( this ).next( 'font' ).show().html( "Pincode must be of 6 digits only." ).addClass( 'errorTextbox' );
				}
				else
					$( this ).next( 'font' ).hide();
			} );

		} );
function displayFileName(){
	var result = checkFileExtension();
	if ( result == 0 ) {
		document.getElementById( "fileUpload" ).value = "";
	}
	else {
		var y = document.getElementById( "fileUpload" ).value.replace( /C:\\fakepath\\/i, '' );
		document.getElementById( "inputFileName" ).innerHTML = y;
	}
}

function checkFileExtension(){
	var x = document.getElementById( "fileUpload" ).value;
	file_extension = x.substr( x.lastIndexOf( '.' ) + 1 ).toLowerCase();
	var allowedExtensions = [
			'xls', 'xlsx'
	];
	if ( file_extension.length > 0 ) {
		if ( allowedExtensions.indexOf( file_extension ) == -1 ) {
			alert( 'Only xls or xlsx [Microsoft Excel Files] files are supported.' );
			return 0;// file extension not allowed
		}
		else {
			return 1;// file extension allowed
		}
	}
	else {
		alert( "File not selected, Please select file." );
		return -1;// no selection of file
	}
}
function populateState( idPop ){
	crmDwr.getState( function( list ){
		addState( idPop, list );
	} );
	function addState( id, list ){
		var select = document.getElementById( id );
		if ( select != null ) {
			if ( list != null ) {
				dwr.util.removeAllOptions( id );
				if ( id ) {
					dwr.util.addOptions( id, [
						"Please Select"
					] );
				}
				dwr.util.addOptions( id, list, "stateId", "stateName" );
			}
			else {
				alert( "No State registered in system." );
				document.getElementById( id ).disabled = true;
			}
		}
	}
}
function populateCity( idPop, stateId ){
	if ( stateId == null || stateId == '' || stateId == 'Please Select' ) {
		$( '.loadingPopup' ).addClass( 'hidden' );
		return false;
	}
	$( '.loadingPopup' ).removeClass( 'hidden' );
	crmDwr.getCity( stateId, function( list ){
		addCity( idPop, list );
	} );
	function addCity( id, list ){
		var select = document.getElementById( id );
		var hiddenGis = document.getElementById( "gisHidden" );
		if ( null != hiddenGis ) {
			hiddenGis = hiddenGis.value;
		}
		if ( select != null ) {
			if ( list != null ) {
				dwr.util.removeAllOptions( id );
				if ( null != hiddenGis && "search" == hiddenGis ) {
					dwr.util.addOptions( id, [
						"All Cities"
					] );
				}
				else {
					dwr.util.addOptions( id, [
						"Please Select"
					] );
				}
				dwr.util.addOptions( id, list, "cityId", "cityName" );
			}
			else {
				alert( "No City registered in system for Selected state." );
				$( '.loadingPopup' ).addClass( 'hidden' );
				if ( id == 'cityarea' ) {
					removeList( id );
				}
				else if ( id == 'cityforl' ) {
					removeList( id );
					removeList( 'areaL' );
				}
				else {
					removeList( 'city' );
					removeList( 'area' );
					removeList( 'locality' );
				}

			}
		}
		$( '.loadingPopup' ).addClass( 'hidden' );
	}
}
function populateArea( idPop, cityId ){
	if ( cityId == null || cityId == '' || cityId == 'Please Select' ) {
		$( '.loadingPopup' ).addClass( 'hidden' );
		return false;
	}
	$( '.loadingPopup' ).removeClass( 'hidden' );
	crmDwr.getArea( cityId, function( list ){
		addArea( idPop, list );
	} );
	function addArea( id, list ){
		var select = document.getElementById( id );
		var hiddenGis = document.getElementById( "gisHidden" );
		if ( null != hiddenGis ) {
			hiddenGis = hiddenGis.value;
		}
		if ( select != null ) {
			if ( list != null ) {
				dwr.util.removeAllOptions( id );
				if ( null != hiddenGis && "search" == hiddenGis ) {
					dwr.util.addOptions( id, [
						"All Area"
					] );
				}
				else {
					dwr.util.addOptions( id, [
						"Please Select"
					] );
				}
				dwr.util.addOptions( id, list, "areaId", "area" );
			}
			else {
				alert( "No Area registered in system for Selected City." );
				$( '.loadingPopup' ).addClass( 'hidden' );
				if ( id == 'areaL' ) {
					removeList( id );
				}
				else {
					removeList( 'area' );
					removeList( 'locality' );
				}
			}
			$( '.loadingPopup' ).addClass( 'hidden' );
		}
	}
}
// function populateLocality( idPop, areaId ){
// crmDwr.getLocality( areaId, function( list ){
// addLocality( idPop, list );
// } );
// function addLocality( id, list ){
// var select = document.getElementById( id );
// var hiddenGis = document.getElementById( "gisHidden" );
// if ( null != hiddenGis ) {
// hiddenGis = hiddenGis.value;
// }
// if ( select != null ) {
// if ( list != null ) {
// dwr.util.removeAllOptions( id );
// if ( null != hiddenGis && "search" == hiddenGis ) {
// dwr.util.addOptions( id, [
// "All Localities"
// ] );
// }
// else {
// dwr.util.addOptions( id, [
// "Please Select"
// ] );
// }
// dwr.util.addOptions( id, list, "localityId", "locality" );
// }
// else {
// alert( "No Locality registered in system for Selected Area." );
// removeList( 'locality' );
// }
// }
// }
// }

function gisOperation( gisType, hiddenGISOperation ){
	var methodName = "";
	var proceed = true;
	var msg = "";
	if ( gisType == "State" ) {
		methodName = "addStateRow";
		if ( hiddenGISOperation == "C" || hiddenGISOperation == "E" ) {
			var sizeId = document.getElementById( "stateSize" );
			var listSize = 0;
			proceed = false;
			if ( null != sizeId ) {
				listSize = sizeId.value;
			}
			if ( listSize > 0 ) {
				for ( var rCount = 0; rCount < listSize; rCount++ ) {
					var editable = document.getElementsByName( "stateDataList[" + rCount + "].editable" );
					if ( editable[ 0 ].checked ) {
						proceed = true;
						break;
					}
				}
			}
			if ( !proceed ) {
				if ( hiddenGISOperation == "C" ) {
					msg = "Please select at least one State to copy.";
				}
				else if ( hiddenGISOperation == "E" ) {
					msg = "Please select at least one State to edit.";
				}
			}
		}
	}
	if ( gisType == "City" ) {
		methodName = "addCityRow";
		if ( hiddenGISOperation == "C" || hiddenGISOperation == "E" ) {
			var sizeId = document.getElementById( "citySize" );
			var listSize = 0;
			proceed = false;
			if ( null != sizeId ) {
				listSize = sizeId.value;
			}
			if ( listSize > 0 ) {
				for ( var rCount = 0; rCount < listSize; rCount++ ) {
					var editable = document.getElementsByName( "cityDataList[" + rCount + "].editable" );
					if ( editable[ 0 ].checked ) {
						proceed = true;
						break;
					}
				}
			}
			if ( !proceed ) {
				if ( hiddenGISOperation == "C" ) {
					msg = "Please select at least one City to copy.";
				}
				else if ( hiddenGISOperation == "E" ) {
					msg = "Please select at least one City to edit.";
				}
			}
		}
	}
	if ( gisType == "Area" ) {
		methodName = "addAreaRow";
		if ( hiddenGISOperation == "C" || hiddenGISOperation == "E" ) {
			var sizeId = document.getElementById( "areaSize" );
			var listSize = 0;
			proceed = false;
			if ( null != sizeId ) {
				listSize = sizeId.value;
			}
			if ( listSize > 0 ) {
				for ( var rCount = 0; rCount < listSize; rCount++ ) {
					var editable = document.getElementsByName( "areaDataList[" + rCount + "].editable" );
					if ( editable[ 0 ].checked ) {
						proceed = true;
						break;
					}
				}
			}
			if ( !proceed ) {
				if ( hiddenGISOperation == "C" ) {
					msg = "Please select at least one Area to copy.";
				}
				else if ( hiddenGISOperation == "E" ) {
					msg = "Please select at least one Area to edit.";
				}
			}
		}
	}
	if ( gisType == "Locality" ) {
		methodName = "addLocalityRow";
		if ( hiddenGISOperation == "C" || hiddenGISOperation == "E" ) {
			var sizeId = document.getElementById( "localitySize" );
			var listSize = 0;
			proceed = false;
			if ( null != sizeId ) {
				listSize = sizeId.value;
			}
			if ( listSize > 0 ) {
				for ( var rCount = 0; rCount < listSize; rCount++ ) {
					var editable = document.getElementsByName( "localityList[" + rCount + "].editable" );
					if ( editable[ 0 ].checked ) {
						proceed = true;
						break;
					}
				}
			}
			if ( !proceed ) {
				if ( hiddenGISOperation == "C" ) {
					msg = "Please select at least one Locality to copy.";
				}
				else if ( hiddenGISOperation == "E" ) {
					msg = "Please select at least one Locality to edit.";
				}
			}
		}
	}
	if ( proceed ) {
		document.getElementById( "hiddenGisOperation" ).value = hiddenGISOperation;
		document.forms[ 1 ].action = "gisMaster.do?method=" + methodName;
		document.forms[ 1 ].submit();
	}
	else {
		alert( msg );
	}
}

function viewSociety_searchSociety( inRecordID ){
	document.getElementById( "societyId_searchSociety" ).value = inRecordID;
	var url = "gis.do?method=viewSociety&societyId=" + inRecordID;
	window.open( url, '', 'width=800,height=600,scrollbars=yes,titlebar=no,fullscreen=no,resizeable=no' );
}

function copySociety_searchSociety( inRecordID ){
	document.getElementById( "societyId_searchSociety" ).value = inRecordID;
	document.forms[ 1 ].action = "gis.do?method=copySocietyPage";
	document.forms[ 1 ].submit();
}

function editSociety_searchSociety( inRecordID ){
	document.getElementById( "societyId_searchSociety" ).value = inRecordID;
	document.forms[ 1 ].action = "gis.do?method=modifySocietyPage";
	document.forms[ 1 ].submit();
}
function removeList( id ){
	var element = document.getElementById( id );
	if ( null == element ) {
		return;
	}
	length = element.length;
	if ( length > 0 ) {
		dwr.util.removeAllOptions( id );
	}
}
function changeSocietyStatus( inRecordID, inStatus ){
	document.getElementById( "societyId_searchSociety" ).value = inRecordID;
	document.getElementById( "newStatus" ).value = inStatus;
	var answer = confirm( "Please confirm if you want to change society status?" );
	if ( answer ) {
		document.forms[ 1 ].action = "gis.do?method=changeSocietyStatus";
		document.forms[ 1 ].submit();
	}
}
function deleteGISRow( inIndex ){
	document.getElementById( "rowIndex_create" ).value = inIndex;
	document.forms[ 1 ].action = "gisMaster.do?method=deleteGISRow";
	document.forms[ 1 ].submit();
}

function menuChange( menuId ){
	document.getElementById( menuId ).click();
}
function getProduct( patnerName ){
	crmDwr.getProductByNPartner( patnerName, function( list ){
		addProductByType( "productName", list );
	} );
	function addProductByType( id, list ){
		if ( list != null ) {
			// alert("hello");
			dwr.util.removeAllOptions( id );
			dwr.util.addOptions( id, list, "partnerId", "bussinessType" );
		}
		else {
			alert( "No Product registered in system." );
		}
	}
}
function getProductById( patnerId, styleId, withSelect ){
	crmDwr.getProductByNPartnerId( patnerId, function( list ){
		$( '.loadingPopup' ).removeClass( 'hidden' );
		addProductByType( styleId, list );
	} );
	function addProductByType( id, list ){
		if ( list != null ) {
			dwr.util.removeAllOptions( id );
			if ( withSelect ) {
				dwr.util.addOptions( id, [
					"Please Select"
				] );
			}
			dwr.util.addOptions( id, list, "contentValue", "contentName" );
		}
		else {
			alert( "No product registered in system." );
		}
		$( '.loadingPopup' ).addClass( 'hidden' );
	}
}

function getRfsDus( partnerId, target ){
	if ( partnerId > 0 ) {
		crmDwr.getRfsDus( partnerId, function( result ){
			document.getElementById( target ).value = result;
		} );
	}
	else {
		document.getElementById( target ).value = 0;
	}
}

function change_SocietyNPStatus( inRecordID, inStatus ){
	$( '#societyNPid' ).val( inRecordID );
	$( '#societyNPStatus' ).val( inStatus );
	var answer = confirm( "Please confirm if you want to change Society Network Partner status?" );
	if ( answer ) {
		document.forms[ 1 ].action = "gis.do?method=modifySocietyNPStatus";
		document.forms[ 1 ].submit();
	}
}
function populateCityAssignArea( idPop, idArea, stateId, stateStyleId ){
	if ( stateId == null || stateId == '' || stateId == 'Please Select' || stateId == '0' ) {
		removeList( idPop );
		removeList( idArea );
		dwr.util.addOptions( idPop, [
			"Please Select"
		] );
		dwr.util.addOptions( idArea, [
			"Please Select"
		] );
		//document.getElementById(stateStyleId).value=0;
		$( '.loadingPopup' ).addClass( 'hidden' );
		return false;
	}
	$( '.loadingPopup' ).removeClass( 'hidden' );
	crmDwr.getCity( stateId, function( list ){
		addCity( idPop, list );
	} );
	function addCity( id, list ){
		var select = document.getElementById( id );
		var hiddenGis = document.getElementById( "gisHidden" );
		if ( null != hiddenGis ) {
			hiddenGis = hiddenGis.value;
		}
		if ( select != null ) {
			if ( list != null ) {
				dwr.util.removeAllOptions( id );
				if ( null != hiddenGis && "search" == hiddenGis ) {
					dwr.util.addOptions( id, [
						"All Cities"
					] );
				}
				else {
					dwr.util.addOptions( id, [
						"Please Select"
					] );
				}
				dwr.util.addOptions( id, list, "cityId", "cityName" );
			}
			else {
				alert( "No City registered in system for Selected state." );
				removeList( idPop );
				removeList( idArea );
				dwr.util.addOptions( idPop, [
					"Please Select"
				] );
				dwr.util.addOptions( idArea, [
					"Please Select"
				] );
				$( '.loadingPopup' ).addClass( 'hidden' );
				if ( id == 'cityarea' ) {
					removeList( id );
				}
				else if ( id == 'cityforl' ) {
					removeList( id );
					removeList( 'areaL' );
				}
				else {
					removeList( 'city' );
					removeList( 'area' );
					removeList( 'locality' );
				}

			}
		}
		$( '.loadingPopup' ).addClass( 'hidden' );
	}
}
function populateAreaAssignArea( idPop, cityId ,cityStyleId){
	if ( cityId == null || cityId == '' || cityId == 'Please Select' || cityId == '0' ) {
		removeList( idPop );
		dwr.util.addOptions( idPop, [
			"Please Select"
		] );		
		$( '.loadingPopup' ).addClass( 'hidden' );
		//document.getElementById(cityStyleId).value=0;
		return false;
	}
	$( '.loadingPopup' ).removeClass( 'hidden' );
	crmDwr.getArea( cityId, function( list ){
		addArea( idPop, list );
	} );
	function addArea( id, list ){
		var select = document.getElementById( id );
		var hiddenGis = document.getElementById( "gisHidden" );
		if ( null != hiddenGis ) {
			hiddenGis = hiddenGis.value;
		}
		if ( select != null ) {
			if ( list != null ) {
				dwr.util.removeAllOptions( id );
				if ( null != hiddenGis && "search" == hiddenGis ) {
					dwr.util.addOptions( id, [
						"All Area"
					] );
				}
				else {
					dwr.util.addOptions( id, [
						"Please Select"
					] );
				}
				dwr.util.addOptions( id, list, "areaId", "area" );
			}
			else {
				alert( "No Area registered in system for Selected City." );
				removeList( idPop );
				dwr.util.addOptions( idPop, [
					"Please Select"
				] );
				$( '.loadingPopup' ).addClass( 'hidden' );
				if ( id == 'areaL' ) {
					removeList( id );
				}
				else {
					removeList( 'area' );
					removeList( 'locality' );
				}
			}
			$( '.loadingPopup' ).addClass( 'hidden' );
		}
	}
}