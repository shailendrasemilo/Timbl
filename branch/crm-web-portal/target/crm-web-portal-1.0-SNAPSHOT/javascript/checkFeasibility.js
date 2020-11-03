$( document ).ready( function(){

	/** ****** check feasibility ************** */
	$( "#checkFeasibility" ).validate( {
		rules : {
			state : {
				dropDown : true
			}, city : {
				dropDown : true
			}, area : {
				dropDown : true
			}, 'installationAddressPojo.stateName' : {
				dropDown : true
			}, 'installationAddressPojo.cityName' : {
				dropDown : true
			}
		}, messages : {
			state : {
				dropDown : "<font class='errorCreateUserSelect'> Please select state </font>"
			}, city : {
				dropDown : "<font class='errorCreateUserSelect'> Please select city</font>"
			}, area : {
				dropDown : "<font class='errorCreateUserSelect'> Please select area</font>"
			}, 'installationAddressPojo.stateName' : {
				dropDown : "<font class='errorCreateUserSelect'> Please select state</font>"
			}, 'installationAddressPojo.cityName' : {
				dropDown : "<font class='errorCreateUserSelect'> Please select city</font>"
			}
		}
	} );

	$( '#submitCheckFeasibility' ).click( function(){
		if ( $( "#checkFeasibility" ).valid() ) {

			if ( $( '#hiddenProduct' ).val() != "" ) {
				if ( $( '#feasiblArea' ).val() == "0" || $( '#feasiblArea' ).val() == "All Area" ) {
					$( '#errorArea' ).html( 'Please select Area' ).addClass( 'errorCreateUserSelect error_message' );
					return false;
				}
				else
					$( '#errorArea' ).hide();

				if ( $( '#feasibleSociety' ).val() == "0" || $( '#feasibleSociety' ).val() == "All Society" ) {
					$( '#errorSociety' ).html( 'Please select locality / sector – society' ).addClass( 'errorCreateUserSelect error_message' );
					return false;
				}
				else
					$( '#errorSociety' ).hide();
				showup();
			}
		}
	} );
	/** ****** check feasibility ************** */
} );
function showup(){
	var stateName = document.getElementById( "feasibleState" ).value;
	var cityName = document.getElementById( "feasibleCity" ).value;
	var areaName = document.getElementById( "feasiblArea" ).value;
	// var localityName = document.getElementById( "feasibleLocality" ).value;
	var societyName = document.getElementById( "feasibleSociety" ).value;
	var values = societyName.split("-");
	var localityName = values[0];
	var societyName = values[1];
	if ( societyName != '0' && societyName != "All Society" && societyName != undefined ) {
		parent.document.getElementById( "instAddLine1" ).value = "," + societyName;
		parent.document.getElementById( "hiddenSociety" ).value = societyName;
	}
	else {
		parent.document.getElementById( "instAddLine1" ).value = "";
		parent.document.getElementById( "hiddenSociety" ).value = "";
	}
	parent.document.getElementById( "instAddLine2" ).value = localityName + ", " + areaName;
	parent.document.getElementById( "instAddLine3" ).value = cityName + ", " + stateName;
	parent.document.getElementById( "hiddenState" ).value = stateName;
	parent.document.getElementById( "hiddenCity" ).value = cityName;
	parent.document.getElementById( "hiddenArea" ).value = areaName;
	parent.document.getElementById( "hiddenLocality" ).value = localityName;
	var sameAsInstalltion = parent.document.getElementById( "sameAsInstallation" );
	if ( null != sameAsInstalltion && sameAsInstalltion.checked ) {
		if ( societyName != '0' && societyName != "All Society" && societyName != undefined ) {
			parent.document.getElementById( "billAddLine1" ).value = "," + societyName;
		}
		else {
			parent.document.getElementById( "billAddLine1" ).value = "";
		}
		parent.document.getElementById( "billAddLine2" ).value = localityName + ", " + areaName;
		parent.document.getElementById( "billAddLine3" ).value = cityName + ", " + stateName;
		parent.document.getElementById( "billingState" ).value = stateName;
		if ( null != stateName && stateName != '' ) {
			populateCityByState( "billingCity", stateName, cityName );
		}
	}
	parent.$.colorbox.close();
}

// version2
function checkLocalityHasFeasibleSociety( stateName, cityName, areaName, productName ){
	crmDwr.getFeasibleSocieties( stateName, cityName, areaName, productName, function( list ){
		if ( list == null ) {
			$( '#errorSociety' ).html( 'No Feasible societies found for selection.' ).addClass( 'errorCreateUserSelect error_message' );
		}
		else {
			showup();
		}
	} );
}
