$( document )
		.ready(
				function(){
					$( '#idUpfrontFromDate' ).removeClass( 'gray_text' );
					$( '#idUpfrontToDate' ).removeClass( 'gray_text' );
					$( '#idChequeDate' ).removeClass( 'gray_text' );

					$.validator.addMethod( "DaysCheck", function( value, element, param ){
						return this.optional( element ) || validityChequeDate( value, parseInt( param ) );
					} );

					$.validator.addMethod( 'paidAmount', function( value, element ){
						return this.optional( element ) || checkPaidAmount( value );
					} );

					$.validator.addMethod( 'notZero', function( value, element ){
						return this.optional( element ) || checkForZero( value );
					} );

					$( "#idManualPaymentEntry" )
							.validate(
									{
										rules : {
											'crmUpfrontPaymentPojo.chequeNo' : {
												required : true, digits : true, maxlength : 6, minlength : 6
											}, 'crmUpfrontPaymentPojo.displayChequeDate' : {
												required : true, DaysCheck : '90'
											}, 'crmUpfrontPaymentPojo.bankId' : {
												dropDown : true
											}, 'crmUpfrontPaymentPojo.amount' : {
												required : true, number : true, paidAmount : true, notZero : true

											}, 'crmUpfrontPaymentPojo.entityName' : {
												dropDown : true
											}, 'crmUpfrontPaymentPojo.partnerId' : {
												dropDown : true
											}

										},
										messages : {
											'crmUpfrontPaymentPojo.chequeNo' : {
												required : "<font class='errorCreateUser'>Please provide 'Cheque Number'.</font>",
												digits : "<font class='errorCreateUser'> Please provide only digits</font>",
												maxlength : "<font class='errorCreateUser'> Please no insert more than 6 character</font>",
												minlength : "<font class='errorCreateUser'> Please provide atleast 6 character</font>"
											},
											'crmUpfrontPaymentPojo.displayChequeDate' : {
												required : "<font class='errorCreateUser'>Please provide 'Cheque Date'.</font>",
												DaysCheck : "<font class='errorCreateUser'>Please provide 'Cheque Date' <br />from last ninety days only</font>"
											},
											'crmUpfrontPaymentPojo.bankId' : {
												dropDown : "<font class='errorCreateUser'> Please select 'Bank'</font>"
											},
											'crmUpfrontPaymentPojo.amount' : {
												required : "<font class='errorCreateUser'> Please provide'Amount'</font>",
												number : "<font class='errorCreateUser'> Please provide only number,<br /> decimal allowed</font>",
												paidAmount : "<font class='errorCreateUser'> Please provide amount not more than<br/> 8 digits(and 1-2 digits after decimal)</font>",
												notZero : "<font class='errorCreateUser'> Please provide amount more than 0.</font>"
											}, 'crmUpfrontPaymentPojo.entityName' : {
												dropDown : "<font class='errorCreateUser'> Please select 'Entity Type'</font>"
											}, 'crmUpfrontPaymentPojo.partnerId' : {
												dropDown : "<font class='errorCreateUser'> Please select 'Partner Name'</font>"
											}
										}
									} );

					$( '#submit_UP' ).click( function(){
						if ( $( "#idManualPaymentEntry" ).valid() ) {
							var ans = confirm( "Please confirm that you are going to submit payment." );
							if ( ans ) {
								document.forms[ 1 ].action = "upFrontPaymentRecovery.do?method=submitUpfrontPaymentManualy&CrfIds=" + crfIDsArray;
								document.forms[ 1 ].submit();
							}
						}

					} );

					/** ******Search upfront payment details******** */
					$( "#idSearchUpfrontPayment" ).validate( {
						rules : {
							toDate : {
								required : true
							}, fromDate : {
								required : true
							}, 'crmUpfrontPaymentPojo.partnerId' : {
								dropDown : true
							}

						}, messages : {
							toDate : {
								required : "<font class='errorCreateUser'>Please provide 'To Date'.</font>",
							}, fromDate : {
								required : "<font class='errorCreateUser'>Please provide 'From Date'.</font>",
							}, 'crmUpfrontPaymentPojo.partnerId' : {
								dropDown : "<font class='errorCreateUser'> Please select 'Partner Name'</font>"
							}
						}
					} );
					$( '#search_UP' ).click(
							function(){
								if ( $( "#idSearchUpfrontPayment" ).valid()
										& dateDifference( '#idUpfrontToDate', '#idUpfrontFromDate', 'From Date', 'To Date' ) ) {
									document.forms[ 1 ].action = "upFrontPaymentRecovery.do?method=searchUpfrontPayment";
									document.forms[ 1 ].submit();
								}
							} );
				} );

/*
 * var crfIdDisplayArray = new Array(); var crfIDsArray = new Array(); var index = 1,outerIndex = 1; function addCrfId(){ var flag = true; var crfID = document.getElementById('idManualCrfID').value; if( crfID == "") { alert("Please provide 'Crf ID'"); flag = false; } else if( !validateCRF(crfID) ) {
 * alert("Please provide correct 'Crf ID'"); flag = false; } else if(crfIDsArray.length > 1) { if (!checkDuplicateCrf(crfID)) { flag = false; } } if(flag){ crfIDsArray.push(crfID); var fieldRest = '<span class="borderLeft"><input id="'+ index +'" type="text" class="Lmstextbox" value="'+ crfID +'"
 * readonly="true"/><a href="javascript:removeCRFId('+ index +');" class="marginleft6"><img src="images/bg/delete.png" align="absmiddle" title="delete"></a></span>'; var listFirst = '<li class="first">'; var listSecond = '<li class="second">'; var listEnd = '</li>'; if( index % 3 === 1 ||
 * index == 1) { if ( outerIndex % 2 === 1 || outerIndex == 1) { crfIdDisplayArray.push(listFirst + fieldRest); } else if (outerIndex % 2 === 0) { crfIdDisplayArray.push(listSecond + fieldRest); } index++; } else if ( index % 3 == 0) { crfIdDisplayArray.push(fieldRest + listEnd); index++;
 * outerIndex++; } else { crfIdDisplayArray.push(fieldRest); index++; } var y = crfIdDisplayArray.join(""); document.getElementById("manualShowID").innerHTML = y; document.getElementById('idManualCrfID').value = "";
 *  }
 *  } function checkDuplicateCrf(inCRFId) { for (var i=0;i<crfIDsArray.length;i++) { if(crfIDsArray[i].trim() == inCRFId.trim()) { alert("in side duplicate crf id."); return false; } } } function removeCRFId(inCrfIdIndex) { var crfValue = document.getElementById(inCrfIdIndex).value; var valueIndex =
 * returnIndex(crfIDsArray,crfValue); crfIDsArray.splice(valueIndex,1); crfIdDisplayArray.splice(valueIndex,1); if ( index === 2) { index = 1; outerIndex = 1; } else if( index % 3 == 2 || index % 3 == 0) { index--; } else if (index % 3 == 1) { index--; outerIndex--; } crfIdDisplayArray =
 * arrayRecreation(); var y = crfIdDisplayArray.join(""); document.getElementById("manualShowID").innerHTML = y; } function returnIndex(inArray,inValue) { for(var i=0; i<inArray.length; i++) { if(inArray[i].trim() === inValue.trim()) { return i; } } } function arrayRecreation() { var newArray = new
 * Array(); var listFirst = '<li class="first">'; var listSecond = '<li class="second">'; var listEnd = '</li>'; var newIndex = 1,newOuterIndex = 1; for (var i=0;i<crfIDsArray.length;i++) { var str = crfIDsArray[i]; var fieldRest = '<span class="borderLeft"><input id="'+ newIndex +'"
 * type="text" class="Lmstextbox" value="'+ str +'" readonly="true"/><a class="marginleft6" href="javascript:removeCRFId('+ newIndex +');"><img src="images/bg/delete.png" align="absmiddle" title="delete"></a></span>'; if( newIndex % 3 === 1 || newIndex == 1) { if ( newOuterIndex % 2 === 1 ||
 * newOuterIndex == 1) { newArray.push(listFirst + fieldRest); } else if (newOuterIndex % 2 === 0) { newArray.push(listSecond + fieldRest); } newIndex++; } else if ( newIndex % 3 == 0) { newArray.push(fieldRest + listEnd); newIndex++; newOuterIndex++; } else { newArray.push(fieldRest); newIndex++; } }
 * return newArray; }
 */
function bpCodePopUp(){
	alert( "This is inside popup" );
}
function dateDiffr(){
	var flag = true;
	if ( dateDiff( new Date( $( '#idUpfrontToDate' ).val() ), new Date( $( '#idUpfrontFromDate' ).val() ) ) > 0 ) {
		alert( "'To Date' must be greater than from date" );
		flag = false;
	}

	return flag;
}
/*
 * function checkCrfID(){ if(crfIDsArray<1){ alert("Please provide 'Crf ID'"); return false; } else{ return true; } }
 */

function dateDifference( fromdate, todate, fromDateName, toDatename ){
	var flag = true;
	if ( dateDiff( fromdate != "" ? new Date( $( fromdate ).val() ) : new Date(), new Date( $( todate ).val() ) ) > 0 ) {
		alert( fromDateName + " must be less than " + toDatename );
		flag = false;
	}
	return flag;
}

function viewUPFrontDetailsPopUP( inRecordID ){
	// alert("id"+inRecordID);
	document.getElementById( "upFrontId" ).value = inRecordID;
	var url = "upFrontPaymentRecovery.do?method=subUpfrontPopupSearch&upFrontId=" + inRecordID;
	window.open( url, 'newWindow', 'width=800,height=600,scrollbars=yes,titlebar=no,fullscreen=no,resizeable=no' );

}
