$( document )
		.ready(
				function(){
					var qrcROL = {
						name : "updateCustName", contactPerson : "updateContactPerson", authorizedPerson : "updateAuthorizedPerson",
						rmn : "updateRMN", altMob : "updateAltMob", email : "updateEmail",
						// rmn : "updateRMN", rtn : "updateRTN", altMob : "updateAltMob", email : "updateEmail", altEmail : "updateAltEmail",
						billMode : "updateBillMode", connectionType : "updateConnectionType", billAddr : "updateBillingAddress",
						instAddr : "updateInstallationAddress", securityDeposit : "updateSecurityDeposit",
					};
					$( 'input[name="houseNumber"]' ).keyup( function(){
						var str = new String( "," + $( 'input[name="changeInstallationAddressPojo.addLine1"]' ).val().split( /,(.+)/ )[ 1 ] );
						$( 'input[name="changeInstallationAddressPojo.addLine1"]' ).attr( 'value', $( 'input[name="houseNumber"]' ).val() + str );
					} );
					var oldVal = {
						nameF : "", nameL : "", authPersonF : "", authPersonL : "", rmn : "", altMob : "", email : "",
						// nameF : "", nameL : "", authPersonF : "", authPersonL : "", rmn : "", rtn : "", altMob : "", email : "",
						billMode : "", connectionType : "", billAddr1 : "", billAddr2 : "", billAddr3 : "", billAddrC : "", billAddrS : "",
						billPin : "", instAddr : "", securityDeposit : "",
					};
					// CSD Level 2 hide show div
					var res = $( 'input[name=customerResponse]:checked' ).val();
					if ( res != 'undefined' && res == "N" ) {
						document.getElementById( "csdCloserReason" ).style.display = "block";
					}
					else if ( res != 'undefined' && res == "Y" ) {
						document.getElementById( 'planTypeShiftingID' ).style.display = "block";
						document.getElementById( 'basePlanCodeID' ).style.display = "block";
						document.getElementById( 'AddonID' ).style.display = "block";
						document.getElementById( 'Other_ID' ).style.display = "block";
					}
					var hCustRecId = $( '#hCustRecId' ).val();
					var hCustId = $( '#hCustId' ).val();
					var hInstAddId = $( '#hInstAddId' ).val();
					var hBillAddId = $( '#hBillAddId' ).val();
					var hOrderId = $( '#hOrderId' ).val();
					var hPlanId = $( '#hPlanId' ).val();
					var hNetCfgId = $( '#hNetCfgId' ).val();
					var hNationalityId = $( '#hNationalityId' ).val();
					var hCRMUserId = $( '#hCRMUserId' ).val();
					var hBrandName = $( '#hBrandName' ).val();
					var hPaymentId = $( '#hPaymentId' ).val();

					/* /////////////////////// customer name edit ////////////////////////// */

					/* == update customer name == */
					$( '.customerNameArea #updateCustomerName, #customerName' ).click( function(){
						var str = $( ".customerNameArea #customerName" ).text();
						// var name = str.split(" ");
						// $(".customerNameArea .firstName").val(name[0]);
						// $(".customerNameArea .lastName").val(name[1]);

						oldVal.nameF = $( ".customerNameArea .firstName" ).val();
						oldVal.nameL = $( ".customerNameArea .lastName" ).val();

						$( ".customerNameArea #lmstextbox" ).removeClass( 'hidden' ).focus();
						$( '#updateCustomerName' ).addClass( 'hidden' );
						$( '.customerNameArea .saveCanelButtons' ).removeClass( 'hidden' );
						$( '.namePopup' ).removeClass( 'hidden' );
					} );

					/* == save updatation in customer details == */
					$( '.customerNameArea #saveButton' )
							.click(
									function(){
										$( '.loadingPopup' ).removeClass( 'hidden' );
										var updateCustomerFirstName = $( ".customerNameArea .firstName" ).val();
										var updateCustomerLastName = $( ".customerNameArea .lastName" ).val();
										var connType = $( '#hConnType' ).val();
										if ( updateCustomerFirstName == '' ) {
											$( '.loadingPopup' ).addClass( 'hidden' );
											alert( 'Please enter Customer First Name' );
											$( ".customerNameArea .firstName" ).focus();
											return;
										}
										// else if ( updateCustomerFirstName.length < 3 ) {
										// $( '.loadingPopup' ).addClass( 'hidden' );
										// alert( 'Please provide more than 3 characters' );
										// $( ".customerNameArea .firstName" ).focus();
										// return;
										// }
										else if ( ( connType == 'Ind' || connType == 'Prop' )
												&& ( updateCustomerFirstName.length < 1 || !validateCustomerFirstName( updateCustomerFirstName ) ) ) {
											$( '.loadingPopup' ).addClass( 'hidden' );
											alert( 'Please enter valid First Name (minimum 1 characters).' );
											$( ".customerNameArea .firstName" ).focus();
											return;
										}
										// else if ( ( connType != 'Ind' && connType != 'Prop' ) && !validateAlphanumericNameCRF( updateCustomerFirstName ) ) {
										// $( '.loadingPopup' ).addClass( 'hidden' );
										// alert( 'Please enter alphabets without special characters' );
										// $( ".customerNameArea .firstName" ).focus();
										// return;
										// }
										else if ( ( connType != 'Ind' && connType != 'Prop' )
												&& ( updateCustomerFirstName.length < 3 ) ) {
											$( '.loadingPopup' ).addClass( 'hidden' );
											alert( 'Please enter atleast one alphabet (minimum 3 characters).' );
											$( ".customerNameArea .firstName" ).focus();
											return;
										}
										/*
										 * else if ( ( connType == 'Ind' || connType == 'Prop' ) && updateCustomerLastName == '' ) { $( '.loadingPopup' ).addClass( 'hidden' ); alert( 'Please enter Customer Last Name' ); $( ".customerNameArea .lastName" ).focus(); return; }
										 */
										// else if ( ( connType == 'Ind' || connType == 'Prop' ) && updateCustomerLastName != ''
										// && updateCustomerLastName.length < 3 ) {
										// $( '.loadingPopup' ).addClass( 'hidden' );
										// alert( 'Please provide more than 3 characters' );
										// $( ".customerNameArea .lastName" ).focus();
										// return;
										// }
										else if ( ( connType == 'Ind' || connType == 'Prop' ) && updateCustomerLastName != ''
												&& !isNaN( updateCustomerLastName ) ) {
											$( '.loadingPopup' ).addClass( 'hidden' );
											alert( 'Please enter valid Last Name.' );
											$( ".customerNameArea .lastName" ).focus();
											return;
										}
										else {
											if ( oldVal.nameF == updateCustomerFirstName && oldVal.nameL == updateCustomerLastName ) {
												$( '.loadingPopup' ).addClass( 'hidden' );
												var updateFullname = updateCustomerFirstName + " "
														+ ( updateCustomerLastName != undefined ? updateCustomerLastName : '' );
												$( ".customerNameArea #customerName" ).html( updateFullname.toUpperCase() );
												$( '.customerNameArea .saveCanelButtons, .customerNameArea #lmstextbox' ).addClass( 'hidden' );
												$( '.customerNameArea #customerName' ).removeClass( 'hidden' );
												$( '#updateCustomerName' ).removeClass( 'hidden' );
												$( '.namePopup' ).addClass( 'hidden' );
											}
											else {
												crmDwr.saveCustomerProfileDetails( hCustRecId, connType + "||" + updateCustomerFirstName
														+ ( updateCustomerLastName != undefined ? "::" + updateCustomerLastName : '' ),
														qrcROL[ 'name' ], hCustId, hCRMUserId, hBrandName, {
															callback : function( result ){
																if ( result[ 0 ] == "success" ) {
																	$( '.loadingPopup' ).addClass( 'hidden' );
																	var updateFullname = updateCustomerFirstName + " "
																			+ ( updateCustomerLastName != undefined ? updateCustomerLastName : '' );
																	$( ".customerNameArea #customerName" ).html( updateFullname.toUpperCase() );
																	$( '.customerNameArea .saveCanelButtons, .customerNameArea #lmstextbox' )
																			.addClass( 'hidden' );
																	$( '.customerNameArea #customerName' ).removeClass( 'hidden' );
																	$( '#updateCustomerName' ).removeClass( 'hidden' );
																	$( '.namePopup' ).addClass( 'hidden' );
																	alert( result[ 1 ] );
																}
																else {
																	alert( result[ 1 ] );
																	$( '.loadingPopup' ).addClass( 'hidden' );
																}
															}, errorHandler : function( errorString, exception ){
																$( '.loadingPopup' ).addClass( 'hidden' );
																alert( errorString );
															}
														} );
											}
										}
									} );

					/* == cancel updation in customer detials == */
					$( '.customerNameArea #cancelButton' ).click( function(){
						$( '.customerNameArea .saveCanelButtons' ).addClass( 'hidden' );
						$( '.customerNameArea #customerName' ).removeClass( 'hidden' );
						$( ".customerNameArea #lmstextbox" ).addClass( 'hidden' );
						$( '.customerNameArea #updateCustomerName' ).removeClass( 'hidden' );
						$( '.namePopup' ).addClass( 'hidden' );
						$( ".customerNameArea .firstName" ).val( oldVal.nameF );
						$( ".customerNameArea .lastName" ).val( oldVal.nameL );
					} );

					/* ============== contact person update ============== */

					$( '.contactPersonArea #updateContactPerson, #contactPerson' ).click( function(){
						var str = $( ".customerNameArea #contactPerson" ).text();
						// var name = str.split(" ");
						// $(".customerNameArea .firstName").val(name[0]);
						// $(".customerNameArea .lastName").val(name[1]);

						oldVal.authPersonF = $( ".contactPersonArea .firstName" ).val();
						oldVal.authPersonL = $( ".contactPersonArea .lastName" ).val();

						$( ".contactPersonArea #lmstextbox" ).removeClass( 'hidden' ).focus();
						$( '#updateContactPerson' ).addClass( 'hidden' );
						$( '.contactPersonArea .saveCanelButtons' ).removeClass( 'hidden' );
						$( '.contactPopup' ).removeClass( 'hidden' );
					} );

					$( '.contactPersonArea #saveButton' ).click(
							function(){
								$( '.loadingPopup' ).removeClass( 'hidden' );
								var updateCustomerFirstName = $( ".contactPersonArea .firstName" ).val();
								var updateCustomerLastName = $( ".contactPersonArea .lastName" ).val();
								console.log( updateCustomerFirstName + " " + updateCustomerLastName );
								if ( updateCustomerFirstName == '' ) {
									$( '.loadingPopup' ).addClass( 'hidden' );
									alert( 'Please enter Contact Person\'s First Name' );
									$( ".contactPersonArea .firstName" ).focus();
									return;
								}
								else if ( updateCustomerFirstName.length < 3 ) {
									$( '.loadingPopup' ).addClass( 'hidden' );
									alert( 'Please provide more than 3 characters' );
									$( ".contactPersonArea .firstName" ).focus();
									return;
								}
								else if ( !validAlphaNameSpaceDot( updateCustomerFirstName ) ) {
									$( '.loadingPopup' ).addClass( 'hidden' );
									alert( 'Please enter alphabets without special characters' );
									$( ".contactPersonArea .firstName" ).focus();
									return;
								}
								else if ( updateCustomerLastName == '' ) {
									$( '.loadingPopup' ).addClass( 'hidden' );
									alert( 'Please enter Contact Person\'s Last Name' );
									$( ".contactPersonArea .lastName" ).focus();
									return;
								}
								else if ( updateCustomerLastName.length < 3 ) {
									$( '.loadingPopup' ).addClass( 'hidden' );
									alert( 'Please provide more than 3 characters' );
									$( ".contactPersonArea .lastName" ).focus();
									return;
								}
								else if ( !validAlphaNameSpaceDot( updateCustomerLastName ) ) {
									$( '.loadingPopup' ).addClass( 'hidden' );
									alert( 'Space and numbers are not allowed' );
									$( ".contactPersonArea .lastName" ).focus();
									return;
								}
								else {
									if ( oldVal.authPersonF == updateCustomerFirstName && oldVal.authPersonL == updateCustomerLastName ) {
										$( '.loadingPopup' ).addClass( 'hidden' );
										var updateFullname = updateCustomerFirstName + " " + updateCustomerLastName;
										$( ".contactPersonArea #contactPerson" ).html( updateFullname.toUpperCase() );
										$( '.contactPersonArea .saveCanelButtons, .contactPersonArea #lmstextbox' ).addClass( 'hidden' );
										$( '.contactPersonArea #contactPerson' ).removeClass( 'hidden' );
										$( '#updateContactPerson' ).removeClass( 'hidden' );
										$( '.contactPopup' ).addClass( 'hidden' );
									}
									else {
										crmDwr.saveCustomerProfileDetails( hCustRecId, updateCustomerFirstName + "::" + updateCustomerLastName,
												qrcROL[ 'authorizedPerson' ], hCustId, hCRMUserId, hBrandName, {
													callback : function( result ){
														if ( result[ 0 ] == "success" ) {
															$( '.loadingPopup' ).addClass( 'hidden' );
															var updateFullname = updateCustomerFirstName + " " + updateCustomerLastName;
															$( ".contactPersonArea #contactPerson" ).html( updateFullname.toUpperCase() );
															$( '.contactPersonArea .saveCanelButtons, .contactPersonArea #lmstextbox' ).addClass(
																	'hidden' );
															$( '.contactPersonArea #contactPerson' ).removeClass( 'hidden' );
															$( '#updateContactPerson' ).removeClass( 'hidden' );
															$( '.contactPopup' ).addClass( 'hidden' );
															alert( result[ 1 ] );
														}
														else {
															alert( result[ 1 ] );
															$( '.loadingPopup' ).addClass( 'hidden' );
														}
													}, errorHandler : function( errorString, exception ){
														$( '.loadingPopup' ).addClass( 'hidden' );
														alert( errorString );
													}
												} );
									}
								}
							} );

					/* == cancel updation in contact detials == */
					$( '.contactPersonArea #cancelButton' ).click( function(){
						$( '.contactPersonArea .saveCanelButtons' ).addClass( 'hidden' );
						$( '.contactPersonArea #contactPerson' ).removeClass( 'hidden' );
						$( ".contactPersonArea #lmstextbox" ).addClass( 'hidden' );
						$( '.contactPersonArea #updateContactPerson' ).removeClass( 'hidden' );
						$( '.contactPopup' ).addClass( 'hidden' );
						$( ".contactPersonArea .firstName" ).val( oldVal.authPersonF );
						$( ".contactPersonArea .lastName" ).val( oldVal.authPersonL );
					} );

					/* ==============Local contact person update ============== */

					$( '.localContactPersonArea #localUpdateContactPerson, #localContactPerson' ).click( function(){
						var str = $( ".customerNameArea #localContactPerson" ).text();
						// var name = str.split(" ");
						// $(".customerNameArea .firstName").val(name[0]);
						// $(".customerNameArea .lastName").val(name[1]);

						oldVal.authPersonF = $( ".localContactPersonArea .firstName" ).val();
						oldVal.authPersonL = $( ".localContactPersonArea .lastName" ).val();

						$( ".localContactPersonArea #lmstextbox" ).removeClass( 'hidden' ).focus();
						$( '#localUpdateContactPerson' ).addClass( 'hidden' );
						$( '.localContactPersonArea .saveCanelButtons' ).removeClass( 'hidden' );
						$( '.contactPopup' ).removeClass( 'hidden' );
					} );

					$( '.localContactPersonArea #saveButton' ).click(
							function(){
								$( '.loadingPopup' ).removeClass( 'hidden' );
								var updateCustomerFirstName = $( ".localContactPersonArea .firstName" ).val();
								var updateCustomerLastName = $( ".localContactPersonArea .lastName" ).val();
								console.log( updateCustomerFirstName + " " + updateCustomerLastName );
								if ( updateCustomerFirstName == '' ) {
									$( '.loadingPopup' ).addClass( 'hidden' );
									alert( 'Please enter Contact Person\'s First Name' );
									$( ".localContactPersonArea .firstName" ).focus();
									return;
								}
								else if ( updateCustomerFirstName.length < 3 ) {
									$( '.loadingPopup' ).addClass( 'hidden' );
									alert( 'Please provide more than 3 characters' );
									$( ".localContactPersonArea .firstName" ).focus();
									return;
								}
								else if ( !validAlphaNameSpaceDot( updateCustomerFirstName ) ) {
									$( '.loadingPopup' ).addClass( 'hidden' );
									alert( 'Please enter alphabets without special characters' );
									$( ".localContactPersonArea .firstName" ).focus();
									return;
								}
								else if ( updateCustomerLastName == '' ) {
									$( '.loadingPopup' ).addClass( 'hidden' );
									alert( 'Please enter Contact Person\'s Last Name' );
									$( ".localContactPersonArea .lastName" ).focus();
									return;
								}
								else if ( updateCustomerLastName.length < 3 ) {
									$( '.loadingPopup' ).addClass( 'hidden' );
									alert( 'Please provide more than 3 characters' );
									$( ".localContactPersonArea .lastName" ).focus();
									return;
								}
								else if ( !validAlphaNameSpaceDot( updateCustomerLastName ) ) {
									$( '.loadingPopup' ).addClass( 'hidden' );
									alert( 'Space and numbers are not allowed' );
									$( ".localContactPersonArea .lastName" ).focus();
									return;
								}
								else {
									if ( oldVal.authPersonF == updateCustomerFirstName && oldVal.authPersonL == updateCustomerLastName ) {
										$( '.loadingPopup' ).addClass( 'hidden' );
										var updateFullname = updateCustomerFirstName + " " + updateCustomerLastName;
										$( ".localContactPersonArea #localContactPerson" ).html( updateFullname.toUpperCase() );
										$( '.localContactPersonArea .saveCanelButtons, .localContactPersonArea #lmstextbox' ).addClass( 'hidden' );
										$( '.localContactPersonArea #localContactPerson' ).removeClass( 'hidden' );
										$( '#localUpdateContactPerson' ).removeClass( 'hidden' );
										$( '.contactPopup' ).addClass( 'hidden' );
									}
									else {
										crmDwr.saveCustomerProfileDetails( hCustRecId, updateCustomerFirstName + "::" + updateCustomerLastName,
												qrcROL[ 'contactPerson' ], hCustId, hCRMUserId, hBrandName, {
													callback : function( result ){
														if ( result[ 0 ] == "success" ) {
															$( '.loadingPopup' ).addClass( 'hidden' );
															var updateFullname = updateCustomerFirstName + " " + updateCustomerLastName;
															$( ".localContactPersonArea #localContactPerson" ).html( updateFullname.toUpperCase() );
															$( '.localContactPersonArea .saveCanelButtons, .localContactPersonArea #lmstextbox' )
																	.addClass( 'hidden' );
															$( '.localContactPersonArea #localContactPerson' ).removeClass( 'hidden' );
															$( '#localUpdateContactPerson' ).removeClass( 'hidden' );
															$( '.contactPopup' ).addClass( 'hidden' );
															alert( result[ 1 ] );
														}
														else {
															alert( result[ 1 ] );
															$( '.loadingPopup' ).addClass( 'hidden' );
														}
													}, errorHandler : function( errorString, exception ){
														$( '.loadingPopup' ).addClass( 'hidden' );
														alert( errorString );
													}
												} );
									}
								}
							} );

					/* == cancel updation in local person contact detials == */
					$( '.localContactPersonArea #cancelButton' ).click( function(){
						$( '.localContactPersonArea .saveCanelButtons' ).addClass( 'hidden' );
						$( '.localContactPersonArea #contactPerson' ).removeClass( 'hidden' );
						$( ".localContactPersonArea #lmstextbox" ).addClass( 'hidden' );
						$( '.localContactPersonArea #localUpdateContactPerson' ).removeClass( 'hidden' );
						$( '.contactPopup' ).addClass( 'hidden' );
						$( ".localContactPersonArea .firstName" ).val( oldVal.authPersonF );
						$( ".localContactPersonArea .lastName" ).val( oldVal.authPersonL );
					} );

					/* /////////////////////// customer RMN edit ////////////////////////// */

					/* == update customer RMN == */
					$( '.rmnArea #rmn, #updateRmn' ).click( function(){
						$( '.rmnArea#rmnid' ).addClass( 'styleForUpdate' );
						var rmn = $( ".rmnArea #rmn" ).text();

						oldVal.rmn = $( ".rmnArea #lmstextbox" ).val();

						$( ".rmnArea #lmstextbox" ).removeClass( 'hidden' ).focus();
						$( '.rmnArea #updateRmn' ).addClass( 'hidden' );
						$( '.rmnArea #rmn' ).addClass( 'hidden' );
						$( '.rmnArea .saveCanelButtons' ).removeClass( 'hidden' );
					} );

					/* == save customer RMN == */
					$( '.rmnArea #saveButton' ).click( function(){
						$( '.loadingPopup' ).removeClass( 'hidden' );
						var updateRNM = $( ".rmnArea #lmstextbox" ).val();
						if ( updateRNM == '' ) {
							$( '.loadingPopup' ).addClass( 'hidden' );
							alert( 'Please enter Registered Mobile Number' );
							$( ".rmnArea #lmstextbox" ).focus();
							return;
						}
						else if ( updateRNM.length != 10 ) {
							$( '.loadingPopup' ).addClass( 'hidden' );
							alert( 'Please enter valid 10 digit Mobile Number' );
							$( ".rmnArea #lmstextbox" ).focus();
							return;
						}
						else if ( !validateMobile( updateRNM ) ) {
							$( '.loadingPopup' ).addClass( 'hidden' );
							alert( 'Mobile Number ' + getMobileMesg() );
							$( ".rmnArea #lmstextbox" ).focus();
							return;
						}
						else if ( isNaN( updateRNM ) ) {
							$( '.loadingPopup' ).addClass( 'hidden' );
							alert( 'Please enter valid Mobile Number' );
							$( ".rmnArea #lmstextbox" ).focus();
							return;
						}
						else {
							if ( oldVal.rmn == updateRNM ) {
								$( '.loadingPopup' ).addClass( 'hidden' );
								$( ".rmnArea #rmn" ).html( updateRNM );
								$( '.rmnArea .saveCanelButtons, .rmnArea #lmstextbox' ).addClass( 'hidden' );
								$( '.rmnArea #rmn' ).removeClass( 'hidden' );
								$( '#updateRmn' ).removeClass( 'hidden' );
								$( '.rmnArea#rmnid' ).removeClass( 'styleForUpdate' );
							}
							else {
								crmDwr.saveCustomerProfileDetails( hCustRecId, updateRNM, qrcROL[ 'rmn' ], hCustId, hCRMUserId, hBrandName, {
									callback : function( result ){
										if ( result[ 0 ] == "success" ) {
											$( '.loadingPopup' ).addClass( 'hidden' );
											$( ".rmnArea #rmn" ).html( updateRNM );
											$( '.rmnArea .saveCanelButtons, .rmnArea #lmstextbox' ).addClass( 'hidden' );
											$( '.rmnArea #rmn' ).removeClass( 'hidden' );
											$( '#updateRmn' ).removeClass( 'hidden' );
											$( '.rmnArea#rmnid' ).removeClass( 'styleForUpdate' );
											alert( result[ 1 ] );
										}
										else {
											alert( result[ 1 ] );
											$( '.loadingPopup' ).addClass( 'hidden' );
										}
									}, errorHandler : function( errorString, exception ){
										$( '.loadingPopup' ).addClass( 'hidden' );
										alert( errorString );
									}
								} );
							}
						}
					} );

					/* == cancel customer RMN == */
					$( '.rmnArea #cancelButton' ).click( function(){
						$( '.rmnArea .saveCanelButtons' ).addClass( 'hidden' );
						$( '.rmnArea #rmn' ).removeClass( 'hidden' );
						$( ".rmnArea #lmstextbox" ).addClass( 'hidden' );
						$( '.rmnArea #updateRmn' ).removeClass( 'hidden' );
						$( ".rmnArea #lmstextbox" ).val( oldVal.rmn );
						$( '.rmnArea#rmnid' ).removeClass( 'styleForUpdate' );
					} );

					/* /////////////////////// customer RTN edit ////////////////////////// */

					/* == update customer RTN == */
					/*
					 * $( '.rtnArea #rtn, #updateRtn' ).click( function(){ var rtn = $( ".rtnArea #rtn" ).text();
					 * 
					 * oldVal.rtn = $( ".rtnArea #lmstextbox" ).val(); $( ".rtnArea #lmstextbox" ).removeClass( 'hidden' ).focus(); $( '.rtnArea #updateRtn' ).addClass( 'hidden' ); $( '.rtnArea #rtn' ).addClass( 'hidden' ); $( '.rtnArea .saveCanelButtons' ).removeClass( 'hidden' ); } ); /* == save
					 * customer RTN ==
					 */
					/*
					 * $( '.rtnArea #saveButton' ).click( function(){ $( '.loadingPopup' ).removeClass( 'hidden' ); var updateRTN = $( ".rtnArea #lmstextbox" ).val(); if ( updateRTN == '' ) { $( '.loadingPopup' ).addClass( 'hidden' ); alert( 'Please enter Telephone Number' ); $( ".rtnArea
					 * #lmstextbox" ).focus(); return; } else if ( isNaN( updateRTN ) ) { $( '.loadingPopup' ).addClass( 'hidden' ); alert( 'Please enter valid telephone number' ); $( ".rtnArea #lmstextbox" ).focus(); return; } else if ( updateRTN.length != 10 ) { $( '.loadingPopup' ).addClass(
					 * 'hidden' ); alert( 'Please enter valid 10 digit telephone number' ); $( ".rtnArea #lmstextbox" ).focus(); return; } else { if ( oldVal.rtn == updateRTN ) { $( '.loadingPopup' ).addClass( 'hidden' ); $( ".rtnArea #rtn" ).html( updateRTN ); $( '.rtnArea .saveCanelButtons,
					 * .rtnArea #lmstextbox' ).addClass( 'hidden' ); $( '.rtnArea #rtn' ).removeClass( 'hidden' ); $( '#updateRtn' ).removeClass( 'hidden' ); } else { crmDwr.saveCustomerProfileDetails( hCustRecId, updateRTN, qrcROL[ 'rtn' ], hCustId, hCRMUserId, hBrandName, { callback : function(
					 * result ){ if ( result[ 0 ] == "success" ) { $( '.loadingPopup' ).addClass( 'hidden' ); $( ".rtnArea #rtn" ).html( updateRTN ); $( '.rtnArea .saveCanelButtons, .rtnArea #lmstextbox' ).addClass( 'hidden' ); $( '.rtnArea #rtn' ).removeClass( 'hidden' ); $( '#updateRtn'
					 * ).removeClass( 'hidden' ); alert( result[ 1 ] ); } else { alert( result[ 1 ] ); $( '.loadingPopup' ).addClass( 'hidden' ); } }, errorHandler : function( errorString, exception ){ $( '.loadingPopup' ).addClass( 'hidden' ); alert( errorString ); } } ); } } } ); /* == cancel
					 * customer RTN ==
					 */
					/*
					 * $( '.rtnArea #cancelButton' ).click( function(){ $( '.rtnArea .saveCanelButtons' ).addClass( 'hidden' ); $( '.rtnArea #rtn' ).removeClass( 'hidden' ); $( ".rtnArea #lmstextbox" ).addClass( 'hidden' ); $( '.rtnArea #updateRtn' ).removeClass( 'hidden' ); $( ".rtnArea
					 * #lmstextbox" ).val( oldVal.rtn ); } );/* /* /////////////////////// customer ALT MOBILE NO. edit //////////////////////////
					 */

					/* == update customer ALT MOBILE NO. == */
					$( '.altMobileArea #altMobile, #updateAltMobile' ).click( function(){
						var altMobile = $( ".altMobileArea #altMobile" ).text();

						oldVal.altMob = $( ".altMobileArea #lmstextbox" ).val();

						$( ".altMobileArea #lmstextbox" ).removeClass( 'hidden' ).focus();
						$( '.altMobileArea #updateAltMobile' ).addClass( 'hidden' );
						$( '.altMobileArea #altMobile' ).addClass( 'hidden' );
						$( '.altMobileArea .saveCanelButtons' ).removeClass( 'hidden' );
					} );

					/* == save customer ALT MOBILE NO. == */
					$( '.altMobileArea #saveButton' ).click(
							function(){
								$( '.loadingPopup' ).removeClass( 'hidden' );
								var updateAltMobile = $( ".altMobileArea #lmstextbox" ).val();
								if ( updateAltMobile == '' ) {
									$( '.loadingPopup' ).addClass( 'hidden' );
									alert( 'Please enter Alternate Mobile Number' );
									$( ".altMobileArea #lmstextbox" ).focus();
									return;
								}
								else if ( updateAltMobile.length != 10 ) {
									$( '.loadingPopup' ).addClass( 'hidden' );
									alert( 'Please enter valid 10 digit Mobile Number' );
									$( ".altMobileArea #lmstextbox" ).focus();
									return;
								}
								else if ( !validateMobile( updateAltMobile ) ) {
									$( '.loadingPopup' ).addClass( 'hidden' );
									alert( 'Mobile Number ' + getMobileMesg() );
									$( ".altMobileArea #lmstextbox" ).focus();
									return;
								}
								else if ( isNaN( updateAltMobile ) ) {
									$( '.loadingPopup' ).addClass( 'hidden' );
									alert( 'Please enter valid Mobile Number' );
									$( ".altMobileArea #lmstextbox" ).focus();
									return;
								}
								else {
									if ( oldVal.altMob == updateAltMobile ) {
										$( '.loadingPopup' ).addClass( 'hidden' );
										$( ".altMobileArea #altMobile" ).html( updateAltMobile );
										$( '.altMobileArea .saveCanelButtons, .altMobileArea #lmstextbox' ).addClass( 'hidden' );
										$( '.altMobileArea #altMobile' ).removeClass( 'hidden' );
										$( '#updateAltMobile' ).removeClass( 'hidden' );
									}
									else {
										crmDwr.saveCustomerProfileDetails( hCustRecId, updateAltMobile, qrcROL[ 'altMob' ], hCustId, hCRMUserId,
												hBrandName, {
													callback : function( result ){
														if ( result[ 0 ] == "success" ) {
															$( '.loadingPopup' ).addClass( 'hidden' );
															$( ".altMobileArea #altMobile" ).html( updateAltMobile );
															$( '.altMobileArea .saveCanelButtons, .altMobileArea #lmstextbox' ).addClass( 'hidden' );
															$( '.altMobileArea #altMobile' ).removeClass( 'hidden' );
															$( '#updateAltMobile' ).removeClass( 'hidden' );
															alert( result[ 1 ] );
														}
														else {
															alert( result[ 1 ] );
															$( '.loadingPopup' ).addClass( 'hidden' );
														}
													}, errorHandler : function( errorString, exception ){
														$( '.loadingPopup' ).addClass( 'hidden' );
														alert( errorString );
													}
												} );
									}
								}
							} );

					/* == cancel customer ALT MOBILE NO. == */
					$( '.altMobileArea #cancelButton' ).click( function(){
						$( '.altMobileArea .saveCanelButtons' ).addClass( 'hidden' );
						$( '.altMobileArea #altMobile' ).removeClass( 'hidden' );
						$( ".altMobileArea #lmstextbox" ).addClass( 'hidden' );
						$( '.altMobileArea #updateAltMobile' ).removeClass( 'hidden' );
						$( ".altMobileArea #lmstextbox" ).val( oldVal.altMob );
					} );
					
					/*Customer security deposit*/
					$( '.altSecurityDeposit #altSecurityDepo, #updateSecDepo' ).click( function(){
						var altSecurityDepo = $( ".altSecurityDeposit #altSecurityDepo" ).text();

						oldVal.securityDeposit = $( ".altSecurityDeposit #lmstextbox" ).val();

						$( ".altSecurityDeposit #lmstextbox" ).removeClass( 'hidden' ).focus();
						$( '.altSecurityDeposit #updateSecDepo' ).addClass( 'hidden' );
						$( '.altSecurityDeposit #altSecurityDepo' ).addClass( 'hidden' );
						$( '.altSecurityDeposit .saveCanelButtons' ).removeClass( 'hidden' );
					} );

					/* == save customer Security Deposit == */
					$( '.altSecurityDeposit #saveButton' ).click(
							function(){
								$( '.loadingPopup' ).removeClass( 'hidden' );
								var updateSecurityDepo = $( ".altSecurityDeposit #lmstextbox" ).val();
								if ( updateSecurityDepo == '' ) {
									$( '.loadingPopup' ).addClass( 'hidden' );
									alert( 'Please enter Security Deposit Ammount' );
									$( ".altSecurityDeposit #lmstextbox" ).focus();
									return;
								}
								else if ( updateSecurityDepo.length > 10 ) {
									$( '.loadingPopup' ).addClass( 'hidden' );
									alert( 'Please enter valid Security Deposit' );
									$( ".altSecurityDeposit #lmstextbox" ).focus();
									return;
								}								
								else if ( isNaN( updateSecurityDepo ) ) {
									$( '.loadingPopup' ).addClass( 'hidden' );
									alert( 'Please enter valid Security Deposit' );
									$( ".altSecurityDeposit #lmstextbox" ).focus();
									return;
								}
								else {
									if ( oldVal.securityDeposit == updateSecurityDepo ) {
										$( '.loadingPopup' ).addClass( 'hidden' );
										$( ".altSecurityDeposit #altSecurityDepo" ).html( updateSecurityDepo );
										$( '.altSecurityDeposit .saveCanelButtons, .altSecurityDeposit #lmstextbox' ).addClass( 'hidden' );
										$( '.altSecurityDeposit #altSecurityDepo' ).removeClass( 'hidden' );
										$( '#updateSecDepo' ).removeClass( 'hidden' );
									}
									else {
										var id = document.getElementById( "hPaymentId" );										
										crmDwr.saveCustomerProfileDetails( hPaymentId, updateSecurityDepo, qrcROL[ 'securityDeposit' ], hCustId, hCRMUserId,
												hBrandName, {
													callback : function( result ){
														if ( result[ 0 ] == "success" ) {
															$( '.loadingPopup' ).addClass( 'hidden' );
															$( ".altSecurityDeposit #altSecurityDepo" ).html( updateSecurityDepo );
															$( '.altSecurityDeposit .saveCanelButtons, .altSecurityDeposit #lmstextbox' ).addClass( 'hidden' );
															$( '.altSecurityDeposit #altSecurityDepo' ).removeClass( 'hidden' );
															$( '#updateSecDepo' ).removeClass( 'hidden' );
															alert( result[ 1 ] );
														}
														else {
															alert( result[ 1 ] );
															$( '.loadingPopup' ).addClass( 'hidden' );
														}
													}, errorHandler : function( errorString, exception ){
														$( '.loadingPopup' ).addClass( 'hidden' );
														alert( errorString );
													}
												} );
									}
								}
							} );

					/* == cancel customer security deposit. == */
					$( '.altSecurityDeposit #cancelButton' ).click( function(){
						$( '.altSecurityDeposit .saveCanelButtons' ).addClass( 'hidden' );
						$( '.altSecurityDeposit #altSecurityDepo' ).removeClass( 'hidden' );
						$( ".altSecurityDeposit #lmstextbox" ).addClass( 'hidden' );
						$( '.altSecurityDeposit #updateSecDepo' ).removeClass( 'hidden' );
						$( ".altSecurityDeposit #lmstextbox" ).val( oldVal.securityDeposit );
					} );
					
					/*End security deposit*/

					/* /////////////////////// customer Email edit ////////////////////////// */

					/* == update customer Email == */
					$( '.emailArea #email, #updateEmail' ).click( function(){
						var email = $( ".emailArea #email" ).text();

						oldVal.email = $( ".emailArea #lmstextbox" ).val();

						$( ".emailArea #lmstextbox" ).removeClass( 'hidden' ).focus();
						$( '.emailArea #updateEmail' ).addClass( 'hidden' );
						$( '.emailArea #email' ).addClass( 'hidden' );
						$( '.emailArea .saveCanelButtons' ).removeClass( 'hidden' );
					} );

					/* == save customer Email == */
					$( '.emailArea #saveButton' ).click( function(){
						$( '.loadingPopup' ).removeClass( 'hidden' );
						var updateEmail = $( ".emailArea #lmstextbox" ).val();
						if ( updateEmail == '' ) {
							$( '.loadingPopup' ).addClass( 'hidden' );
							alert( 'Please enter Customer Email' );
							$( ".emailArea #email" ).focus();
							return;
						}
						else if ( !validateEmail( updateEmail ) ) {
							$( '.loadingPopup' ).addClass( 'hidden' );
							alert( 'Please enter valid email' );
							$( ".emailArea #email" ).focus();
							return;
						}
						else {
							if ( oldVal.email == updateEmail ) {
								$( '.loadingPopup' ).addClass( 'hidden' );
								$( ".emailArea #email" ).html( updateEmail );
								$( '.emailArea .saveCanelButtons, .emailArea #lmstextbox' ).addClass( 'hidden' );
								$( '.emailArea #email' ).removeClass( 'hidden' );
								$( '#updateEmail' ).removeClass( 'hidden' );
							}
							else {
								crmDwr.saveCustomerProfileDetails( hCustRecId, updateEmail, qrcROL[ 'email' ], hCustId, hCRMUserId, hBrandName, {
									callback : function( result ){
										if ( result[ 0 ] == "success" ) {
											$( '#email' ).removeClass( 'green' ).addClass( 'red' );
											$( '#sendVerificationEmail' ).removeClass( 'hidden' );
											$( '.loadingPopup' ).addClass( 'hidden' );
											$( ".emailArea #email" ).html( updateEmail );
											$( '.emailArea .saveCanelButtons, .emailArea #lmstextbox' ).addClass( 'hidden' );
											$( '.emailArea #email' ).removeClass( 'hidden' );
											$( '#updateEmail' ).removeClass( 'hidden' );
											alert( result[ 1 ] );
										}
										else {
											alert( result[ 1 ] );
											$( '.loadingPopup' ).addClass( 'hidden' );
										}
									}, errorHandler : function( errorString, exception ){
										$( '.loadingPopup' ).addClass( 'hidden' );
										alert( errorString );
									}
								} );
							}
						}
					} );

					/* == cancel customer Email == */
					$( '.emailArea #cancelButton' ).click( function(){
						$( '.emailArea .saveCanelButtons' ).addClass( 'hidden' );
						$( '.emailArea #email' ).removeClass( 'hidden' );
						$( ".emailArea #lmstextbox" ).addClass( 'hidden' );
						$( '.emailArea #updateEmail' ).removeClass( 'hidden' );
						$( ".emailArea #lmstextbox" ).val( oldVal.email );
					} );

					/* /////////////////////// customer Alt Email edit ////////////////////////// */

					/* == update customer Alt Email == */
					$( '.altEmailArea #altEmail, #updateAltEmail' ).click( function(){
						var altEmail = $( ".altEmailArea #altEmail" ).text();

						oldVal.altEmail = $( ".altEmailArea #lmstextbox" ).val();

						$( ".altEmailArea #lmstextbox" ).removeClass( 'hidden' ).focus();
						$( '.altEmailArea #updateAltEmail' ).addClass( 'hidden' );
						$( '.altEmailArea #altEmail' ).addClass( 'hidden' );
						$( '.altEmailArea .saveCanelButtons' ).removeClass( 'hidden' );
					} );

					/* == save customer Alt Email == */
					$( '.altEmailArea #saveButton' ).click(
							function(){
								$( '.loadingPopup' ).removeClass( 'hidden' );
								var updateAltEmail = $( ".altEmailArea #lmstextbox" ).val();
								if ( updateAltEmail == '' ) {
									$( '.loadingPopup' ).addClass( 'hidden' );
									alert( 'Please enter Customer Email' );
									$( ".altEmailArea #email" ).focus();
									return;
								}
								else if ( !validateEmail( updateAltEmail ) ) {
									$( '.loadingPopup' ).addClass( 'hidden' );
									alert( 'Please enter valid email' );
									$( ".altEmailArea #email" ).focus();
									return;
								}
								else {
									if ( oldVal.altEmail == updateAltEmail ) {
										$( '.loadingPopup' ).addClass( 'hidden' );
										$( ".altEmailArea #altEmail" ).html( updateAltEmail );
										$( '.altEmailArea .saveCanelButtons, .altEmailArea #lmstextbox' ).addClass( 'hidden' );
										$( '.altEmailArea #altEmail' ).removeClass( 'hidden' );
										$( '#updateAltEmail' ).removeClass( 'hidden' );
									}
									else {
										crmDwr.saveCustomerProfileDetails( hCustRecId, updateAltEmail, qrcROL[ 'altEmail' ], hCustId, hCRMUserId,
												hBrandName, {
													callback : function( result ){
														if ( result[ 0 ] == "success" ) {
															$( '.loadingPopup' ).addClass( 'hidden' );
															$( ".altEmailArea #altEmail" ).html( updateAltEmail );
															$( '.altEmailArea .saveCanelButtons, .altEmailArea #lmstextbox' ).addClass( 'hidden' );
															$( '.altEmailArea #altEmail' ).removeClass( 'hidden' );
															$( '#updateAltEmail' ).removeClass( 'hidden' );
															alert( result[ 1 ] );
														}
														else {
															alert( result[ 1 ] );
															$( '.loadingPopup' ).addClass( 'hidden' );
														}
													}, errorHandler : function( errorString, exception ){
														$( '.loadingPopup' ).addClass( 'hidden' );
														alert( errorString );
													}
												} );
									}
								}
							} );

					/* == cancel customer Alt Email == */
					$( '.altEmailArea #cancelButton' ).click( function(){
						$( '.altEmailArea .saveCanelButtons' ).addClass( 'hidden' );
						$( '.altEmailArea #altEmail' ).removeClass( 'hidden' );
						$( ".altEmailArea #lmstextbox" ).addClass( 'hidden' );
						$( '.altEmailArea #updateAltEmail' ).removeClass( 'hidden' );
						$( ".altEmailArea #lmstextbox" ).val( oldVal.altEmail );
					} );

					/* /////////////////////// Bill mode change ////////////////////////// */

					/* == change bill mode == */
					$( '.billMode #billType, #updateBillMode' ).click( function(){

						oldVal.billMode = $( '#billMode :selected' ).val();
						$( '#updateBillMode' ).addClass( 'hidden' );
						$( '.billMode .BillModedropdown' ).removeClass( 'hidden' );
						$( '.billMode #billType' ).addClass( 'hidden' );
						$( '.billMode .saveCanelButtons' ).removeClass( 'hidden' );
					} );
					/* == save changes bill mode == */
					$( '.billMode #saveButton' ).click(
							function(){
								$( '.loadingPopup' ).removeClass( 'hidden' );
								var updateBillMode = $( "#billMode :selected" ).text();
								var updateBillModeVal = $( '#billMode :selected' ).val();
								if ( oldVal.billMode == updateBillModeVal ) {
									$( '.loadingPopup' ).addClass( 'hidden' );
									$( ".billMode #billType" ).html( updateBillMode );
									$( '.billMode .saveCanelButtons, .billMode .BillModedropdown' ).addClass( 'hidden' );
									$( '.billMode #billType' ).removeClass( 'hidden' );
									$( '#updateBillMode' ).removeClass( 'hidden' );
								}
								else {
									crmDwr.saveCustomerProfileDetails( hPlanId, updateBillModeVal, qrcROL[ 'billMode' ] + updateBillModeVal, hCustId,
											hCRMUserId, hBrandName, {
												callback : function( result ){
													if ( result[ 0 ] == "success" ) {
														$( '.loadingPopup' ).addClass( 'hidden' );
														$( ".billMode #billType" ).html( updateBillMode );
														$( '.billMode .saveCanelButtons, .billMode .BillModedropdown' ).addClass( 'hidden' );
														$( '.billMode #billType' ).removeClass( 'hidden' );
														$( '#updateBillMode' ).removeClass( 'hidden' );
														alert( result[ 1 ] );
													}
													else {
														alert( result[ 1 ] );
														$( '.loadingPopup' ).addClass( 'hidden' );
													}
												}, errorHandler : function( errorString, exception ){
													$( '.loadingPopup' ).addClass( 'hidden' );
													alert( errorString );
												}
											} );
								}

							} );
					/* == cancel changes bill mode == */
					$( '.billMode #cancelButton' ).click( function(){
						$( '.billMode .saveCanelButtons' ).addClass( 'hidden' );
						$( '.billMode #billType' ).removeClass( 'hidden' );
						$( '.billMode .BillModedropdown' ).addClass( 'hidden' );
						$( '#updateBillMode' ).removeClass( 'hidden' );
						$( '#billMode' ).val( oldVal.billMode ).change();
					} );

					/* /////////////////////// connectin type change ////////////////////////// */

					// /* == change connection type == */
					// $( '.connectionTypeArea #connectionType, #updateConnectionType' ).click( function(){
					//
					// oldVal.connectionType = $( '#connectionDropdown :selected' ).val();
					// $( '.connectionTypeArea .Lmsdropdown' ).removeClass( 'hidden' );
					// $( '.connectionTypeArea #connectionType' ).addClass( 'hidden' );
					// $( '.connectionTypeArea .saveCanelButtons' ).removeClass( 'hidden' );
					// $( '#updateConnectionType' ).addClass( 'hidden' );
					// } );
					// /* == save changes connection type == */
					// $( '.connectionTypeArea #saveButton' ).click( function(){
					// $( '.loadingPopup' ).removeClass( 'hidden' );
					// var updateConnectionType = $( "#connectionDropdown :selected" ).text();
					// var updateConnectionVal = $( '#connectionDropdown :selected' ).val();
					// if ( oldVal.connectionType == updateConnectionVal ) {
					// $( '.loadingPopup' ).addClass( 'hidden' );
					// $( ".connectionTypeArea #connectionType" ).html( updateConnectionType );
					// $( '.connectionTypeArea #connectionType' ).removeClass( 'hidden' );
					// $( '.connectionTypeArea .saveCanelButtons, .connectionTypeArea .Lmsdropdown' ).addClass( 'hidden' );
					// $( '#updateConnectionType' ).removeClass( 'hidden' );
					// }
					// else {
					// crmDwr.saveCustomerProfileDetails( hCustRecId, updateConnectionVal, qrcROL[ 'connectionType' ], hCustId, hCRMUserId, {
					// callback : function( result ){
					// if ( result[ 0 ] == "success" ) {
					// $( '.loadingPopup' ).addClass( 'hidden' );
					// $( ".connectionTypeArea #connectionType" ).html( updateConnectionType );
					// $( '.connectionTypeArea #connectionType' ).removeClass( 'hidden' );
					// $( '.connectionTypeArea .saveCanelButtons, .connectionTypeArea .Lmsdropdown' ).addClass( 'hidden' );
					// alert( result[ 1 ] );
					// $( '#updateConnectionType' ).removeClass( 'hidden' );
					// }
					// else {
					// alert( result[ 1 ] );
					// $( '.loadingPopup' ).addClass( 'hidden' );
					// }
					// }, errorHandler : function( errorString, exception ){
					// $( '.loadingPopup' ).addClass( 'hidden' );
					// alert( errorString );
					// }
					// } );
					// }
					//
					// } );
					//
					// /* == cancel changes connectioni Type == */
					// $( '.connectionTypeArea #cancelButton' ).click( function(){
					// $( '.connectionTypeArea .saveCanelButtons' ).addClass( 'hidden' );
					// $( '.connectionTypeArea #connectionType' ).removeClass( 'hidden' );
					// $( '.connectionTypeArea .Lmsdropdown' ).addClass( 'hidden' );
					// $( '#updateConnectionType' ).removeClass( 'hidden' );
					// } );
					/* /////////////////////// customer intallation address edit ////////////////////////// */
					/* == update customer installing address line 1 == */
					$( '#changeinstallAddress' ).click( function(){
						var installAdd = $( "#installAddLine1" ).text();

						oldVal.instAddr = $( ".addressLine01 #lmstextbox" ).val();

						$( ".addressLine01 #lmstextbox" ).removeClass( 'hidden' ).focus();
						$( '#changeinstallAddress' ).addClass( 'hidden' );
						$( '#installAddLine1' ).addClass( 'hidden' );
						$( '.installAddressArea .saveCanelButtons' ).removeClass( 'hidden' );
						$( '.installAddressArea div:first-child label' ).addClass( 'verticleAlignT8' );
					} );
					/* == save updatation in customer installation address line 1 == */
					$( '.installAddressArea #saveButton' ).click(
							function(){
								$( '.loadingPopup' ).removeClass( 'hidden' );
								var updateInstallLIne1 = $( ".addressLine01 #lmstextbox" ).val();
								if ( updateInstallLIne1 == '' ) {
									$( '.loadingPopup' ).addClass( 'hidden' );
									alert( 'Please enter installation address' );
									$( ".addressLine01 #lmstextbox" ).focus();
									return;
								}
								else if ( !isNaN( updateInstallLIne1 ) ) {
									$( '.loadingPopup' ).addClass( 'hidden' );
									alert( 'Please enter atleast one alphabet.' );
									$( ".addressLine01 #lmstextbox" ).focus();
									return;
								}
								else {
									if ( updateInstallLIne1.charAt( 0 ) == ',' ) {
										updateInstallLIne1 = updateInstallLIne1.slice( 1 );
									}
									if ( oldVal.instAddr == updateInstallLIne1 ) {
										$( '.loadingPopup' ).addClass( 'hidden' );
										$( "#installAddLine1" ).html( updateInstallLIne1.toUpperCase() );
										$( '.installAddressArea .saveCanelButtons, .addressLine01 #lmstextbox' ).addClass( 'hidden' );
										$( '#installAddLine1' ).removeClass( 'hidden' );
										$( '#changeinstallAddress' ).removeClass( 'hidden' );
										$( '.installAddressArea div:first-child label' ).removeClass( 'verticleAlignT8' );
									}
									else {
										crmDwr.saveCustomerProfileDetails( hInstAddId, updateInstallLIne1, qrcROL[ 'instAddr' ], hCustId, hCRMUserId,
												hBrandName, {
													callback : function( result ){
														if ( result[ 0 ] == "success" ) {
															$( '.loadingPopup' ).addClass( 'hidden' );
															$( "#installAddLine1" ).html( updateInstallLIne1.toUpperCase() );
															$( '.installAddressArea .saveCanelButtons, .addressLine01 #lmstextbox' ).addClass(
																	'hidden' );
															$( '#installAddLine1' ).removeClass( 'hidden' );
															$( '#changeinstallAddress' ).removeClass( 'hidden' );
															$( '.installAddressArea div:first-child label' ).removeClass( 'verticleAlignT8' );
															alert( result[ 1 ] );
														}
														else {
															alert( result[ 1 ] );
															$( '.loadingPopup' ).addClass( 'hidden' );
														}
													}, errorHandler : function( errorString, exception ){
														$( '.loadingPopup' ).addClass( 'hidden' );
														alert( errorString );
													}
												} );
									}
								}
							} );

					/* == cancel installation address updation == */
					$( '.installAddressArea #cancelButton' ).click( function(){
						$( '.installAddressArea .saveCanelButtons' ).addClass( 'hidden' );
						$( '#installAddLine1' ).removeClass( 'hidden' );
						$( ".installAddressArea #lmstextbox" ).addClass( 'hidden' );
						$( '#changeinstallAddress' ).removeClass( 'hidden' );
						$( '.installAddressArea div:first-child label' ).removeClass( 'verticleAlignT8' );
						$( ".addressLine01 #lmstextbox" ).val( oldVal.instAddr );
					} );

					/* /////////////////////// customer billing address edit ////////////////////////// */

					/* == update customer billing address line 1 == */
					$( '#changeBillAddress' ).click( function(){
						var billAddLIne1 = $( "#billAddLine1" ).text();

						oldVal.billAddr1 = $( ".addressLine1 #lmstextbox" ).val();

						$( ".addressLine1 #lmstextbox" ).removeClass( 'hidden' ).focus();
						$( '#changeBillAddress' ).addClass( 'hidden' );
						$( '#billAddLine1' ).addClass( 'hidden' );
						$( '.billAddressArea .saveCanelButtons' ).removeClass( 'hidden' );
						$( '.billAddressArea label' ).addClass( 'verticleAlignT8' );
						$( '.stateDropdown, .cityDropdown' ).removeClass( 'hidden' );
						$( '.stateDropdown, .cityDropdown' ).removeClass( 'verticleAlignT8' );
					} );
					/* == update customer billing address line 2 == */
					$( '#changeBillAddress' ).click( function(){
						var billAddLIne2 = $( "#billAddLine2" ).text();

						oldVal.billAddr2 = $( ".addressLine2 #lmstextbox" ).val();

						$( ".addressLine2 #lmstextbox" ).removeClass( 'hidden' );
						$( '#changeBillAddress' ).addClass( 'hidden' );
						$( '#billAddLine2' ).addClass( 'hidden' );
						$( '.billAddressArea .saveCanelButtons' ).removeClass( 'hidden' );
					} );
					/* == update customer billing address line 3 == */
					$( '#changeBillAddress' ).click( function(){
						var billAddLIne3 = $( "#billAddLine3" ).text();

						oldVal.billAddr3 = $( ".addressLine3 #lmstextbox" ).val();
						oldVal.billAddrC = $( '.qrcBillCity :selected' ).val();
						oldVal.billAddrS = $( '.qrcBillState :selected' ).val();
						oldVal.billPin = $( '.addressPincode #lmstextbox' ).val();

						$( ".addressLine3 #lmstextbox" ).removeClass( 'hidden' );
						$( '.addressPincode #lmstextbox' ).removeClass( 'hidden' );
						$( '#changeBillAddress' ).addClass( 'hidden' );
						$( '#billAddLine3' ).addClass( 'hidden' );
						$( '#billAddPincode' ).addClass( 'hidden' );
						$( '.billAddressArea .saveCanelButtons' ).removeClass( 'hidden' );
					} );

					/* == save updatation in customer billing address line 1 == */
					$( '.billAddressArea #saveButton' ).click(
							function(){
								$( '.loadingPopup' ).removeClass( 'hidden' );
								var updatebillLine1 = $( ".addressLine1 #lmstextbox" ).val();
								var updatebillLine2 = $( ".addressLine2 #lmstextbox" ).val();
								var updatebillLine3 = $( ".addressLine3 #lmstextbox" ).val();
								var updatebillcity = $( '.qrcBillCity :selected' ).val();
								var updatebillstate = $( '.qrcBillState :selected' ).val();
								var updateBillPincode = $( '.addressPincode #lmstextbox' ).val();

								if ( updatebillLine1 == '' ) {
									$( '.loadingPopup' ).addClass( 'hidden' );
									alert( 'Please enter billing address line 1' );
									$( ".addressLine1 #lmstextbox" ).focus();
									return;
								}
								else if ( !isNaN( updatebillLine1 ) ) {
									$( '.loadingPopup' ).addClass( 'hidden' );
									alert( 'Please enter atleast one alphabet.' );
									$( ".addressLine1 #lmstextbox" ).focus();
									return;
								}
								else if ( updatebillLine2 == '' ) {
									$( '.loadingPopup' ).addClass( 'hidden' );
									alert( 'Please enter billing address line 2' );
									$( ".addressLine2 #lmstextbox" ).focus();
									return;
								}
								else if ( !isNaN( updatebillLine2 ) ) {
									$( '.loadingPopup' ).addClass( 'hidden' );
									alert( 'Please enter atleast one alphabet.' );
									$( ".addressLine1 #lmstextbox" ).focus();
									return;
								}
								else if ( !validatePin( updateBillPincode ) ) {
									$( '.loadingPopup' ).addClass( 'hidden' );
									alert( 'Please enter valid PIN Code.' );
									$( ".addressPincode #lmstextbox" ).focus();
									return;
								}
								else if ( updatebillcity == "" || updatebillcity == "All Cities" || updatebillcity == null
										|| updatebillcity == undefined ) {
									$( '.loadingPopup' ).addClass( 'hidden' );
									alert( 'Please select billing city' );
									return;
								}
								if ( updatebillLine1.charAt( 0 ) == ',' ) {
									updatebillLine1 = updatebillLine1.slice( 1 );
								}
								var billAdd = updatebillLine1 + "::" + updatebillLine2 + "::" + updatebillLine3 + "::" + updatebillstate + "::"
										+ updatebillcity + "::" + updateBillPincode;
								if ( oldVal.billAddr1 == updatebillLine1 && oldVal.billAddr2 == updatebillLine2
										&& oldVal.billAddr3 == updatebillLine3 && oldVal.billAddrC == updatebillcity
										&& oldVal.billAddrS == updatebillstate && oldVal.billPin == updateBillPincode ) {
									$( '.loadingPopup' ).addClass( 'hidden' );
									$( "#billAddLine1" ).html( updatebillLine1.toUpperCase() );
									$( '.billAddressArea .saveCanelButtons, .addressLine1 #lmstextbox' ).addClass( 'hidden' );
									$( '#billAddLine1' ).removeClass( 'hidden' );
									$( '#changeBillAddress' ).removeClass( 'hidden' );
									$( '.billAddressArea label' ).removeClass( 'verticleAlignT8' );
									$( '.stateDropdown, .cityDropdown' ).addClass( 'hidden' );

									$( "#billAddLine2" ).html( updatebillLine2.toUpperCase() );
									$( '.billAddressArea .saveCanelButtons, .addressLine2 #lmstextbox' ).addClass( 'hidden' );
									$( '#billAddLine2' ).removeClass( 'hidden' );
									$( '#changeBillAddress' ).removeClass( 'hidden' );

									$( "#billAddLine3" ).html( updatebillLine3.toUpperCase() );
									$( '.billAddressArea .saveCanelButtons, .addressLine3 #lmstextbox' ).addClass( 'hidden' );
									$( '#billAddLine3' ).removeClass( 'hidden' );
									$( '#changeBillAddress' ).removeClass( 'hidden' );

									$( '#billAddPincode' ).removeClass( 'hidden' );
									$( '.billAddressArea .addressPincode #lmstextbox' ).addClass( 'hidden' );

								}
								else {
									crmDwr.saveCustomerProfileDetails( hBillAddId, billAdd, qrcROL[ 'billAddr' ], hCustId, hCRMUserId, hBrandName, {
										callback : function( result ){

											if ( result[ 0 ] == "success" ) {
												$( '.loadingPopup' ).addClass( 'hidden' );
												$( "#billAddLine1" ).html( updatebillLine1.toUpperCase() );
												$( '.billAddressArea .saveCanelButtons, .addressLine1 #lmstextbox' ).addClass( 'hidden' );
												$( '#billAddLine1' ).removeClass( 'hidden' );
												$( '#changeBillAddress' ).removeClass( 'hidden' );
												$( '.billAddressArea label' ).removeClass( 'verticleAlignT8' );
												$( '.stateDropdown, .cityDropdown' ).addClass( 'hidden' );

												$( "#billAddLine2" ).html( updatebillLine2.toUpperCase() );
												$( '.billAddressArea .saveCanelButtons, .addressLine2 #lmstextbox' ).addClass( 'hidden' );
												$( '#billAddLine2' ).removeClass( 'hidden' );
												$( '#changeBillAddress' ).removeClass( 'hidden' );

												$( "#billAddLine3" ).html( updatebillLine3.toUpperCase() );
												$( '.billAddressArea .saveCanelButtons, .addressLine3 #lmstextbox' ).addClass( 'hidden' );
												$( '#billAddLine3' ).removeClass( 'hidden' );
												$( '#changeBillAddress' ).removeClass( 'hidden' );

												$( '#billAddPincode' ).html( updateBillPincode ).removeClass( 'hidden' );
												$( '.billAddressArea .addressPincode #lmstextbox' ).addClass( 'hidden' );

												alert( result[ 1 ] );
											}
											else {
												$( '.loadingPopup' ).addClass( 'hidden' );
												alert( result[ 1 ] );
											}
										}, errorHandler : function( errorString, exception ){
											$( '.loadingPopup' ).addClass( 'hidden' );
											alert( errorString );
										}
									} );
								}

							} );

					/* == cancel billing address updation == */
					$( '.billAddressArea #cancelButton' ).click( function(){
						$( '.billAddressArea .saveCanelButtons' ).addClass( 'hidden' );
						$( '#billAddLine1, #billAddLine2, #billAddLine3, #billAddPincode' ).removeClass( 'hidden' );
						$( ".billAddressArea #lmstextbox" ).addClass( 'hidden' );
						$( '#changeBillAddress' ).removeClass( 'hidden' );
						$( '.billAddressArea label' ).removeClass( 'verticleAlignT8' );
						$( '.stateDropdown, .cityDropdown' ).addClass( 'hidden' );
						$( ".addressLine1 #lmstextbox" ).val( oldVal.billAddr1 );
						$( ".addressLine2 #lmstextbox" ).val( oldVal.billAddr2 );
						$( ".addressLine3 #lmstextbox" ).val( oldVal.billAddr3 );
						$( ".addressPincode #lmstextbox" ).val( oldVal.billPin );
					} );

					$( '#qrcBillState' ).change( function(){
						$( '.addressLine3 #lmstextbox' ).val( $( '#qrcBillState' ).val() );
						setTimeout( function(){
							$( '#qrcBillCity' ).trigger( 'change' );
						}, 500 );
					} );

					$( '#qrcBillCity' ).change( function(){
						var billCity = $( '#qrcBillCity' ).val();
						if ( !( billCity == "" || billCity == "null" || billCity == null || billCity == undefined ) ) {
							$( '.addressLine3 #lmstextbox' ).val( $( '#qrcBillCity' ).val() + ", " + $( '#qrcBillState' ).val() );
						}
					} );

					/* ====================== Send Email Verification Link ====================== */
					$( '#sendVerificationEmail' ).click( function(){
						$( '.loadingPopup' ).removeClass( 'hidden' );
						crmDwr.sendEmailVerificationLink( {
							callback : function( result ){
								if ( result != '' && result != null && result != undefined ) {
									$( '.loadingPopup' ).addClass( 'hidden' );
									alert( result[ 1 ] );
								}
								else {
									$( '.loadingPopup' ).addClass( 'hidden' );
									alert( "Session Expired. Please login again to continue." );
									location.href = "./";
								}
							}, errorHandler : function( errorString, exception ){
								$( '.loadingPopup' ).addClass( 'hidden' );
								alert( errorString );
							}
						} );
					} );

					/* == cancel download billed Usage in customer detials == */
					$( '.billedUsageArea #cancelButton' ).click( function(){
						$( '.billedUsageArea .saveCanelButtons' ).addClass( 'hidden' );
						$( '.billedUsageArea #customerName' ).removeClass( 'hidden' );
						$( ".billedUsageArea #lmstextbox" ).addClass( 'hidden' );
						$( '.billedUsageArea #updateCustomerName' ).removeClass( 'hidden' );
						$( '.billedUsagePopup' ).addClass( 'hidden' );
					} );

					/* == save download billed Usage in customer detials == */
					$( '.billedUsageArea #saveButton' ).click( function(){
						if ( validateUsagePeriod() ) {
							downloadCustomerUsage( 'Billed' );
						}

					} );

					/* == Download Billed Usage == */
					$( '.billedUsageArea #downloadBilledUsage' ).click( function(){

						$( ".billedUsageArea #lmstextbox" ).removeClass( 'hidden' ).focus();
						// $( '#downloadBilledUsage' ).addClass( 'hidden' );
						$( '.billedUsageArea .saveCanelButtons' ).removeClass( 'hidden' );
						$( '.billedUsagePopup' ).removeClass( 'hidden' );
					} );

					/* ====================== Ticket List Hide/Show ====================== */
					$( '.ticketSearch a.generateTicketBtn' ).click( function(){
						$( '.ticketSearch' ).addClass( 'hidden' );
						$( '.ticketSearch :input' ).attr( 'disabled', true );
						$( '.ticketCreation' ).removeClass( 'hidden' );
						$( '.ticketCreation :input' ).removeAttr( 'disabled' );
						refreshValue( 'generate', 'ticket' );
					} );

					$( '.ticketCreation a.ticketSearchBtn' ).click( function(){
						$( '.ticketCreation' ).addClass( 'hidden' );
						$( '.ticketCreation :input' ).attr( 'disabled', true );
						$( '.ticketSearch' ).removeClass( 'hidden' );
						$( '.ticketSearch :input' ).removeAttr( 'disabled' );
						refreshValue( 'search', 'ticket' );

					} );

					$( '.section a.section' ).click( function(){
						$( '.ticketSearch' ).addClass( 'hidden' );
						$( '.ticketSearch :input' ).attr( 'disabled', true );
						$( '.ticketCreation' ).removeClass( 'hidden' );
						$( '.ticketCreation :input' ).removeAttr( 'disabled' );
						refreshValue( 'editTicket', 'ticket' );
					} );

					$( 'a#saveTicket' ).keydown( function( evt ){
						var keyCode = evt.which || evt.keyCode;
						if ( keyCode == 13 ) {
							saveTicket();
						}
					} );

					$( 'a#saveInteraction' ).keydown( function( evt ){
						var keyCode = evt.which || evt.keyCode;
						if ( keyCode == 13 ) {
							postCustomerInteraction();
						}
					} );

					$( '.searchPage a.createPageBtn' ).click( function(){
						$( '.searchPage' ).addClass( 'hidden' );
						$( '.searchPage :input' ).attr( 'disabled', true );
						$( '.createPage' ).removeClass( 'hidden' );
						$( '.createPage :input' ).removeAttr( 'disabled' );
						refreshValue( 'generate', 'interaction' );
					} );

					$( '.createPage a.searchPageBtn' ).click( function(){
						$( '.createPage' ).addClass( 'hidden' );
						$( '.createPage :input' ).attr( 'disabled', true );
						$( '.searchPage' ).removeClass( 'hidden' );
						$( '.searchPage :input' ).removeAttr( 'disabled' );
						refreshValue( 'search', 'interaction' );
					} );

					$( '#remarks,#callingNo' ).keyup( function(){
						$( this ).next( 'font' ).hide();
					} );

					$( '#categoryId,#subCategoryId,#subSubCategoryId,#resolutionType' ).click( function(){
						$( this ).next( 'font' ).hide();
					} );

					$( '#submit_logQRCBarring' ).click( function(){
						var reason = $( '[name="crmQrcWhitelistPojo.reason"] :selected' ).val();
						var remarks = $( '#bubRemarks' ).val();
						var endDate = $( '#displayEndDate' ).val();
						var diffDate = dateDiff( new Date(), getISODate( $( '#displayEndDate' ).val() ) );
						var valid = true;

						if ( reason == '' || reason == '0' || reason == undefined || reason == null ) {
							$( '[name="crmQrcWhitelistPojo.reason"] ~ font' ).removeClass( 'hidden' );
							valid = false;
						}
						else {
							$( '[name="crmQrcWhitelistPojo.reason"] ~ font' ).addClass( 'hidden' );
						}

						if ( endDate == '' || endDate == undefined || endDate == null ) {
							$( '#displayEndDate ~ font' ).removeClass( 'hidden' );
							valid = false;
						}
						else if ( diffDate < 1 ) {
							$( '#displayEndDate ~ font' ).text( 'End date must be greater than current date' ).removeClass( 'hidden' );
							valid = false;
						}
						else {
							$( '#displayEndDate ~ font' ).text( 'Please select end date.' ).addClass( 'hidden' );
						}

						if ( remarks.trim() == '' || remarks == undefined || remarks == null ) {
							$( '.errorRemarks' ).removeClass( 'hidden' ).addClass( 'displayB' );
							valid = false;
						}
						else {
							$( '.errorRemarks' ).addClass( 'hidden' ).removeClass( 'displayB' );
						}

						if ( !valid ) {
							return;
						}

						if ( confirm( "Are you sure to add customer in exception list?" ) ) {
							$( '.loadingPopup' ).removeClass( 'hidden' );
							document.forms[ 1 ].action = "manageQrc.do?method=addUpdateWhiteList";
							document.forms[ 1 ].submit();
						}
					} );

					$( '#remove_whitelist' ).click( function(){
						var valid = true;
						var remarks = $( '#bubRemarks' ).val();
						if ( remarks.trim() == '' || remarks == undefined || remarks == null ) {
							$( '.errorRemarks' ).removeClass( 'hidden' ).addClass( 'displayB' );
							valid = false;
						}
						else {
							$( '.errorRemarks' ).addClass( 'hidden' ).removeClass( 'displayB' );
						}

						if ( !valid ) {
							return;
						}
						if ( confirm( "Are you sure you want to remove customer  Exception List?" ) ) {
							$( '.loadingPopup' ).removeClass( 'hidden' );
							document.forms[ 1 ].action = "manageQrc.do?method=removeFromWhiteList";
							document.forms[ 1 ].submit();
						}
					} );

					$( '[name="crmQrcWhitelistPojo.whitelistType"]' ).click( function(){
						if ( $( this ).is( ':checked' ) ) {
							var type = $( this ).val();
							crmDwr.getWhitelistReasons( type, function( list ){
								dwr.util.removeAllOptions( 'whitelistReasons' );
								dwr.util.addOptions( 'whitelistReasons', [
									{
										key : 0, value : "Please select"
									}
								], 'key', 'value' );
								if ( list != null ) {
									dwr.util.addOptions( 'whitelistReasons', list, 'categoryId', 'categoryValue' );
								}
							} );
						}
					} );

					$( '#submit_bulk_whitelist' ).click(
							function(){
								var whitelistType = $( 'input[name="crmQrcWhitelistPojo.whitelistType"]:checked' ).val();
								var whitelistExcelFile = $( '#fileUpload' ).val();
								var whitelistReason = $( '#whitelistReasons :selected' ).val();
								var whitelistEndDate = $( '#whitelistEndDate' ).val();
								var whitelistRemarks = $( '#whitelistRemarks' ).val();
								var diffDate = dateDiff( new Date(), getISODate( whitelistEndDate ) );
								var valid = true;

								if ( whitelistType == '' || whitelistType == null || whitelistType == undefined ) {
									$( '.whitelistType' ).removeClass( 'hidden' );
									valid = false;
								}
								else {
									$( '.whitelistType' ).addClass( 'hidden' );
								}

								if ( whitelistExcelFile == '' || whitelistExcelFile == null || whitelistExcelFile == undefined ) {
									$( '#fileUpload ~ font' ).removeClass( 'hidden' );
									$( '#inputFileName' ).addClass( 'red' );
									$( '#inputFileName' ).removeClass( 'green' );
									valid = false;
								}
								else {
									$( '#inputFileName' ).removeClass( 'red' );
									// $( '#inputFileName' ).addClass( 'green' );
								}

								if ( whitelistReason == '' || whitelistReason == '0' || whitelistReason == null || whitelistReason == undefined ) {
									$( '#whitelistReasons ~ font' ).removeClass( 'hidden' );
									valid = false;
								}
								else {
									$( '#whitelistReasons ~ font' ).addClass( 'hidden' );
								}

								if ( whitelistEndDate == '' || whitelistEndDate == undefined || whitelistEndDate == null ) {
									$( '#whitelistEndDate ~ font' ).removeClass( 'hidden' );
									valid = false;
								}
								else if ( diffDate < 1 ) {
									$( '#whitelistEndDate ~ font' ).text( 'End date must be greater than current date' ).removeClass( 'hidden' );
									valid = false;
								}
								else {
									$( '#whitelistEndDate ~ font' ).text( 'Please select end date.' ).addClass( 'hidden' );
								}

								if ( whitelistRemarks.trim() == '' || whitelistRemarks == null || whitelistRemarks == undefined
										|| whitelistRemarks.trim().length < 2 || whitelistRemarks.trim().length > 4000 ) {
									$( '.errorRemarks' ).removeClass( 'hidden' ).addClass( 'displayB' );
									valid = false;
								}
								else {
									$( '.errorRemarks' ).addClass( 'hidden' ).removeClass( 'displayB' );
								}

								if ( valid ) {
									if ( confirm( "Are you sure you want to upload customer Barring/Unbarring whitelist file?" ) ) {
										$( '.loadingPopup' ).removeClass( 'hidden' );
										document.forms[ 1 ].action = 'manageQrc.do?method=bulkWhitelist';
										document.forms[ 1 ].submit();
									}
								}
							} );
					// Mount Booster Call..
					$( '#submit_bulk_MountBooster' ).click(
							function(){
								var mountBoosterExcelFile = $( '#fileUpload' ).val();
								var remarks = $( '#mountBoosterRemarks' ).val();
								var valid = true;
								if ( mountBoosterExcelFile == '' || mountBoosterExcelFile == null || mountBoosterExcelFile == undefined ) {
									$( '#fileUpload ~ font' ).removeClass( 'hidden' );
									$( '#inputFileName' ).addClass( 'red' );
									$( '#inputFileName' ).removeClass( 'green' );
									valid = false;
								}
								else {
									$( '#inputFileName' ).removeClass( 'red' );
								}

								if ( remarks.trim() == '' || remarks == null || remarks == undefined || remarks.trim().length < 2
										|| remarks.trim().length > 4000 ) {
									$( '.errorRemarks' ).removeClass( 'hidden' ).addClass( 'displayB' );
									valid = false;
								}
								else {
									$( '.errorRemarks' ).addClass( 'hidden' ).removeClass( 'displayB' );
								}

								if ( valid ) {
									if ( confirm( "Are you sure you want to upload Mount Booster file?" ) ) {
										$( '.loadingPopup' ).removeClass( 'hidden' );
										document.forms[ 1 ].action = 'bulkUploadAction.do?method=uploadMountBooster';
										document.forms[ 1 ].submit();
									}
								}
							} );
					// Validity Extension Call..
					$( '#submit_bulk_ValidityExtension' ).click(
							function(){
								var mountBoosterExcelFile = $( '#fileUpload' ).val();
								var validityExtensionReason = $( '#GP_ReasonId :selected' ).val();
								var remarks = $( '#validityExtensionRemarks' ).val();
								var valid = true;
								if ( mountBoosterExcelFile == '' || mountBoosterExcelFile == null || mountBoosterExcelFile == undefined ) {
									$( '#fileUpload ~ font' ).removeClass( 'hidden' );
									$( '#inputFileName' ).addClass( 'red' );
									$( '#inputFileName' ).removeClass( 'green' );
									valid = false;
								}
								else {
									$( '#inputFileName' ).removeClass( 'red' );
								}
								if ( validityExtensionReason == '' || validityExtensionReason == '0' || validityExtensionReason == null
										|| validityExtensionReason == undefined ) {
									$( '#GP_ReasonId ~ font' ).removeClass( 'hidden' );
									valid = false;
								}
								else {
									$( '#GP_ReasonId ~ font' ).addClass( 'hidden' );
								}
								if ( remarks.trim() == '' || remarks == null || remarks == undefined || remarks.trim().length < 2
										|| remarks.trim().length > 4000 ) {
									$( '.errorRemarks' ).removeClass( 'hidden' ).addClass( 'displayB' );
									valid = false;
								}
								else {
									$( '.errorRemarks' ).addClass( 'hidden' ).removeClass( 'displayB' );
								}

								if ( valid ) {
									if ( confirm( "Are you sure you want to upload Validity Extension file?" ) ) {
										$( '.loadingPopup' ).removeClass( 'hidden' );
										document.forms[ 1 ].action = 'bulkUploadAction.do?method=uploadValidityExtension';
										document.forms[ 1 ].submit();
									}
								}
							} );

					$( '#submit_bub' ).click(
							function(){
								var reason = $( '#bubReasonId' ).val();
								// var ticketId = $('#bubTicketId').val();
								var remarks = $( '#bubRemarksId' ).val();
								var valid = true;

								if ( reason == "" || reason == 0 || reason == null || reason == undefined ) {
									$( '#bubReasonId ~ font' ).removeClass( 'hidden' );
									valid = false;
								}
								else {
									$( '#bubReasonId ~ font' ).addClass( 'hidden' );
								}

								/*
								 * if(ticketId == '' || ticketId == null || ticketId == undefined){ $('#bubTicketId ~ font').removeClass('hidden'); valid = false; }else{ $('#bubTicketId ~ font').addClass('hidden'); }
								 */

								if ( remarks.trim() == '' || remarks == null || remarks == undefined || remarks.trim().length < 2
										|| remarks.trim().length > 4000 ) {
									$( '#bubRemarksId ~ font' ).removeClass( 'hidden' ).addClass( 'displayB' );
									valid = false;
								}
								else {
									$( '#bubRemarksId ~ font' ).addClass( 'hidden' ).removeClass( 'displayB' );
								}

								if ( !valid ) {
									return;
								}

								if ( confirm( "Are you sure you want to Bar/UnBar Customer Service?" ) ) {
									$( '.loadingPopup' ).removeClass( 'hidden' );
									document.forms[ 1 ].action = "manageQrc.do?method=barringUnbarringService";
									document.forms[ 1 ].submit();
								}
							} );

					$( '#remove_bub' ).click(
							function(){
								var remarks = $( '#bubRemarksId' ).val();
								var valid = true;

								if ( remarks.trim() == '' || remarks == null || remarks == undefined || remarks.trim().length < 2
										|| remarks.trim().length > 4000 ) {
									$( '#bubRemarksId ~ font' ).removeClass( 'hidden' ).addClass( 'displayB' );
									valid = false;
								}
								else {
									$( '#bubRemarksId ~ font' ).addClass( 'hidden' ).removeClass( 'displayB' );
								}

								if ( !valid ) {
									return;
								}
								if ( confirm( "Are you sure you want to remove Customer from Exception List?" ) ) {
									$( '.loadingPopup' ).removeClass( 'hidden' );
									document.forms[ 1 ].action = "manageQrc.do?method=removeFromWhiteListBeforeBUB";
									document.forms[ 1 ].submit();
								}
							} );
					$( '#submit_instAddrChange' ).click(
							function(){
								var valid = true;
								var addrL1 = $( '#instAddLine1' ).val().trim();
								var addrL2 = $( '#instAddLine2' ).val();
								/*
								 * var addrL3 = $( '#instAddLine3' ).val(); var state = $( '#feasibleState' ).val();
								 * 
								 * var city = $( '#feasibleCity' ).val(); var area = $( '#feasiblArea' ).val(); var locality = $( '#feasibleLocality' ).val(); var society = $( '#feasibleSociety' ).val(); var record = $( '#instAddrRecordId' ).val(); var ldmark = $( '#instAddrLandmark' ).val(); var
								 * pincode = $( '#instAddrPincode' ).val(); var propdt = $( 'input[name="installationAddressPojo.propertyDetails"]:checked' ).val();
								 */
								var remarks = $( '#instAddrRemarks' ).val();

								var customerId = $( '#customerId' ).val();
								var workflowId = $( '#workflowId' ).val();
								var shiftingType = $( '#shiftingType' ).val();
								var product = $( '#hiddenProduct' ).val();
								var houseID = $( '#houseId' ).val().trim();

								$( '#instAddLine1 ~ font' ).addClass( 'hidden' );
								$( '#instAddLine2 ~ font' ).addClass( 'hidden' );
								$( '#instAddLine3 ~ font' ).addClass( 'hidden' );
								$( '#houseId ~ font' ).addClass( 'hidden' );
								/*
								 * $( '#feasibleState ~ font' ).addClass( 'hidden' ); $( '#feasibleCity ~ font' ).addClass( 'hidden' ); $( '#feasiblArea ~ font' ).addClass( 'hidden' ); $( '#feasibleLocality ~ font' ).addClass( 'hidden' ); $( '#feasibleSociety ~ font' ).addClass( 'hidden' ); $(
								 * '#instAddrLandmark ~ font' ).addClass( 'hidden' ); $( '#instAddrPincode ~ font' ).addClass( 'hidden' ); $( '.propdt > font' ).addClass( 'hidden' ); $( '#instAddrRemarks ~ font' ).addClass( 'hidden' ); $( '#instAddrRemarks ~ span' ).addClass( 'hidden' );
								 */

								// console.log(addrL1 + " | " + addrL2 + " | " + addrL3 + " | " + ldmark + " | " + record + " | " + city + " | " + state + " | " + pincode + " | " + propdt);
								if ( addrL1 == '' || addrL1 == null || addrL1 == undefined ) {
									$( '#instAddLine1 ~ font' ).removeClass( 'hidden' );
									valid = false;
								}

								if ( addrL2 == '' || addrL2 == null || addrL2 == undefined ) {
									$( '#instAddLine2 ~ font' ).removeClass( 'hidden' );
									valid = false;
								}
								if ( houseID == '' || houseID == null || houseID == undefined ) {
									$( '#houseId ~ font' ).removeClass( 'hidden' );
									valid = false;
								}

								/*
								 * if ( addrL3 == '' || addrL3 == null || addrL3 == undefined ) { $( '#instAddLine3 ~ font' ).removeClass( 'hidden' ); valid = false; }
								 * 
								 * if ( state == "0" && city == "0" && area == "0" && locality == "0" && society == "0" ) { $( '#feasibleState ~ font' ).removeClass( 'hidden' ); valid = false; } else if ( ( city == "0" || city == "All Cities" ) && area == "0" && locality == "0" && society == "0" ) { $(
								 * '#feasibleCity ~ font' ).removeClass( 'hidden' ); valid = false; } else if ( ( area == "0" || area == "All Area" ) && locality == "0" && society == "0" ) { $( '#feasiblArea ~ font' ).removeClass( 'hidden' ); valid = false; } else if ( ( locality == "0" || locality ==
								 * "All Locality" ) && society == "0" && product != "EOC" ) { $( '#feasibleLocality ~ font' ).removeClass( 'hidden' ); valid = false; } else if ( ( society == "0" || society == "All Society" ) && product != "EOC" ) { $( '#feasibleSociety ~ font' ).removeClass(
								 * 'hidden' ); valid = false; }
								 * 
								 * if ( ldmark == '' || ldmark == null || ldmark == undefined ) { $( '#instAddrLandmark ~ font' ).removeClass( 'hidden' ); valid = false; }
								 * 
								 * if ( pincode == '' || pincode == null || pincode == undefined ) { $( '#instAddrPincode ~ font' ).removeClass( 'hidden' ); valid = false; } else if ( isNaN( pincode ) || pincode.length != 6 ) { $( '#instAddrPincode ~ font' ).text( 'Please enter valid 6 digit PIN
								 * Code' ).removeClass( 'hidden' ); valid = false; }
								 * 
								 * if ( propdt == '' || propdt == null || propdt == undefined ) { $( '.propdt > font' ).removeClass( 'hidden' ); valid = false; }
								 */
								if ( remarks.trim() == '' || remarks == null || remarks == undefined ) {
									$( '#instAddrRemarks ~ font' ).removeClass( 'hidden' );
									valid = false;
								}
								else if ( remarks.trim().length < 2 || remarks.trim().length > 4000 ) {
									$( '#instAddrRemarks ~ span' ).removeClass( 'hidden' );
									valid = false;
								}

								if ( !valid ) {
									return;
								}

								if ( confirm( "Are you sure you want to change address?" ) ) {
									$( '.loadingPopup' ).removeClass( 'hidden' );
									var urlString = "shiftingWorkflow.do?method=changeInstallationAddress&inCustomerId=" + customerId
											+ "&inWorkflowId=" + workflowId + "&inShiftingType=" + shiftingType;
									document.forms[ 1 ].action = urlString;
									document.forms[ 1 ].submit();
								}
							} );

					$( '#submit_disconnection' ).click(
							function(){
								var valid = true;
								var reason = $( '#discReason' ).val();
								var remarks = $( '#discRemarks' ).val();

								$( '#discReason ~ font' ).addClass( 'hidden' );
								$( '#discRemarks ~ font' ).addClass( 'hidden' );

								if ( reason == '' || reason == 0 || reason == null || reason == undefined ) {
									$( '#discReason ~ font' ).removeClass( 'hidden' );
									valid = false;
								}
								if ( remarks.trim() == '' || remarks == null || remarks == undefined || remarks.trim().length < 2
										|| remarks.trim().length > 4000 ) {
									$( '#discRemarks ~ font' ).removeClass( 'hidden' );
									valid = false;
								}

								if ( !valid ) {
									return;
								}

								if ( confirm( "Are you sure to " + $( '#submit_disconnection' ).text() + " customer?" ) ) {
									$( '.loadingPopup' ).removeClass( 'hidden' );
									document.forms[ 1 ].action = "manageQrc.do?method=disconnection";
									document.forms[ 1 ].submit();
								}
							} );

					if ( $( '.waiverType:checked' ).val() != '' && $( '.waiverType:checked' ).val() != null
							&& $( '.waiverType:checked' ).val() != undefined ) {
						$( '.loadingPopup' ).removeClass( 'hidden' );
						var waiver = $( '.waiverType:checked' ).val();
						crmDwr.getWaiverCategories( waiver, function( list ){
							fillWaiverCategories( list );
						} );
					}

					$( '.waiverType' ).click( function(){
						if ( $( this ).is( ':checked' ) ) {
							var waiver = $( this ).val();
							crmDwr.getWaiverCategories( waiver, function( list ){
								fillWaiverCategories( list );
							} );
						}
					} );

					$( '#submit_waiver' ).click( function(){
						var valid = true;
						var type = $( '.waiverType:checked' ).val();
						var waiverHead = $( '#waiverHead' ).val();
						var waiverAmount = $( '#waiverAmount' ).val();
						var waiverBill = $( '#waiverBill' ).val();
						var waiverRemarks = $( '#waiverRemarks' ).val();

						$( '.waiverType' ).parent().next( 'font' ).addClass( 'hidden' );
						$( '#waiverHead ~ font' ).addClass( 'hidden' );
						$( '#waiverAmount ~ font' ).addClass( 'hidden' );
						$( '#waiverBill ~ font' ).addClass( 'hidden' );
						$( '#waiverRemarks ~ font' ).addClass( 'hidden' );

						if ( type == '' || type == null || type == undefined ) {
							$( '.waiverType' ).parent().next( 'font' ).removeClass( 'hidden' );
							valid = false;
						}

						if ( waiverHead == '' || waiverHead == 0 || waiverHead == null || waiverHead == undefined ) {
							$( '#waiverHead ~ font' ).removeClass( 'hidden' );
							valid = false;
						}

						if ( waiverAmount == '' || waiverAmount == null || waiverAmount == undefined || waiverAmount == '0' ) {
							$( '#waiverAmount ~ font' ).removeClass( 'hidden' );
							valid = false;
						}
						else if ( isNaN( waiverAmount ) ) {
							$( '#waiverAmount ~ font' ).removeClass( 'hidden' );
							valid = false;
						}

						if ( waiverBill == '' || waiverBill == 0 || waiverBill == null || waiverBill == undefined ) {
							$( '#waiverBill ~ font' ).removeClass( 'hidden' );
							valid = false;
						}

						if ( waiverRemarks.trim() == '' || waiverRemarks == null || waiverRemarks == undefined ) {
							$( '#waiverRemarks ~ font' ).removeClass( 'hidden' );
							valid = false;
						}

						if ( !valid ) {
							return;
						}

						if ( confirm( "Are you sure you want to apply Waiver?" ) ) {
							$( '.loadingPopup' ).removeClass( 'hidden' );
							document.forms[ 1 ].action = "manageQrc.do?method=applyWaiver";
							document.forms[ 1 ].submit();
						}
					} );

					$.validator.addMethod( "searchString", function( value, element ){
						return /^RA[0-9]{6}|EA[0-9]{6}|([0-9]{7,15})/i.test( value );
					} );
					// for search Customer
					$( "#search_by_Cust" ).validate(
							{
								rules : {
									searchString : {
										required : true, searchString : true
									},
								},
								messages : {
									searchString : {
										required : "<div class='errorCreateUser'> Please provide 'CAF Number / Customer ID'</div>",
										searchString : "<div class='errorCreateUser'> Please provide  CAF Number / Customer ID'</div>"
									}
								}
							} );

					$( "#qrcSubmitSearchCustomer" ).click( function(){
						if ( $( "#search_by_Cust" ).valid() ) {
							$( '.loadingPopup' ).removeClass( 'hidden' );
							document.forms[ 1 ].action = "manageQrc.do?method=searchCustomer";
							document.forms[ 1 ].submit();
						}
					} );

					$( "#qrcViewPayment_InvoiceDetails" ).click( function(){
						document.forms[ 1 ].action = "manageQrc.do?method=viewPaymentAndInvoiceDetails";
						document.forms[ 1 ].submit();
					} );

					$( '#actionTakenId' ).bind( "change", function(){
						if ( $( this ).val() == 0 ) {
							$( this ).next( 'font' ).show().html( "Please select 'Action Taken'" ).addClass( 'errorTextbox' );
						}
						else {
							$( this ).next( 'font' ).hide();
						}
					} );
					$( '#rootCauseId' ).bind( "change", function(){
						if ( $( this ).val() == 0 ) {
							$( this ).parent().next( 'font' ).show().html( "Please select 'Root Cause'" ).addClass( 'errorTextbox' );
						}
						else {
							$( this ).parent().next( 'font' ).hide();
						}
					} );
					$( '#submit_sfCustody' ).click(
							function(){

								var reason = $( '#SC_ReasonId' ).val();
								// var ticketId = $('#bubTicketId').val();
								var remarks = $( '#SC_RemarksId' ).val();
								var valid = true;

								if ( reason == "" || reason == 0 || reason == null || reason == undefined ) {
									$( '#SC_ReasonId ~ font' ).removeClass( 'hidden' );
									valid = false;
								}
								else {
									$( '#SC_ReasonId ~ font' ).addClass( 'hidden' );
								}

								/*
								 * if(ticketId == '' || ticketId == null || ticketId == undefined){ $('#bubTicketId ~ font').removeClass('hidden'); valid = false; }else{ $('#bubTicketId ~ font').addClass('hidden'); }
								 */

								if ( remarks.trim() == '' || remarks == null || remarks == undefined || remarks.trim().length < 2
										|| remarks.trim().length > 4000 ) {
									$( '#SC_RemarksId ~ font' ).removeClass( 'hidden' ).addClass( 'displayB' );
									valid = false;
								}
								else {
									$( '#SC_RemarksId ~ font' ).addClass( 'hidden' ).removeClass( 'displayB' );
								}

								if ( !valid ) {
									return;
								}

								if ( confirm( "Are you sure you want to apply Safe Custody?" ) ) {
									$( '.loadingPopup' ).removeClass( 'hidden' );
									document.forms[ 1 ].action = "customerStatus.do?method=updateSafeCustody";
									document.forms[ 1 ].submit();
								}

							} );

					$( '[name="custDetailsPojo.status"]' ).click( function(){
						if ( $( this ).is( ':checked' ) ) {
							var type = $( this ).val();
							crmDwr.getReasonsList( type, function( list ){
								dwr.util.removeAllOptions( 'IDManageStatusReasons' );
								dwr.util.addOptions( 'IDManageStatusReasons', [
									{
										key : 0, value : "Please Select"
									}
								], 'key', 'value' );
								if ( list != null ) {
									dwr.util.addOptions( 'IDManageStatusReasons', list, 'categoryId', 'categoryValue' );
								}
							} );
						}
					} );

					$( '#submit_bulk_status' ).click( function(){
						var reasonListType = $( 'input[name="custDetailsPojo.status"]:checked' ).val();
						var statusExcelFile = $( '#fileUpload' ).val();
						var reasonList = $( '#IDManageStatusReasons :selected' ).val();
						var statusRemarks = $( '#IDManageStatusRemarks' ).val();
						var valid = true;

						if ( reasonListType == '' || reasonListType == null || reasonListType == undefined ) {
							$( '.reasonListType' ).removeClass( 'hidden' );
							valid = false;
						}
						else {
							$( '.reasonListType' ).addClass( 'hidden' );
						}

						if ( statusExcelFile == '' || statusExcelFile == null || statusExcelFile == undefined ) {

							$( '#error_message_upload_gis ~ font' ).removeClass( 'hidden' );
							$( '#inputFileName' ).addClass( 'red' );
							$( '#inputFileName' ).removeClass( 'green' );

							valid = false;
						}
						else {
							$( '#inputFileName' ).removeClass( 'red' );
							// $( '#inputFileName' ).addClass( 'green' );
						}

						if ( reasonList == '' || reasonList == '0' || reasonList == null || reasonList == undefined ) {
							$( '#IDManageStatusReasons ~ font' ).removeClass( 'hidden' );
							valid = false;
						}
						else {
							$( '#IDManageStatusReasons ~ font' ).addClass( 'hidden' );
						}
						if ( statusRemarks.trim() == '' || statusRemarks == null || statusRemarks == undefined ) {
							$( '.errorRemarks' ).removeClass( 'hidden' ).addClass( 'displayB' );
							valid = false;
						}
						else {
							if ( $.trim( statusRemarks ) != '' && ( statusRemarks.trim().length < 2 || statusRemarks.trim().length > 4000 ) ) {
								$( '.errorRemarks' ).removeClass( 'hidden' ).addClass( 'displayB' );
								valid = false;
							}
							else {
								$( '.errorRemarks' ).addClass( 'hidden' ).removeClass( 'displayB' );
							}
						}

						if ( valid ) {
							if ( confirm( "Are you sure you want to upload file for manage customers status?" ) ) {
								$( '.loadingPopup' ).removeClass( 'hidden' );
								document.forms[ 1 ].action = 'customerStatus.do?method=updateCustomersStatus';
								document.forms[ 1 ].submit();
							}
						}
					} );

					$( '#updateBillDate' ).click( function(){
						var valid = true;
						var oldValue = document.getElementById( 'oldBillDate' ).value;
						var newValue = document.getElementById( 'newBillDateId' ).value;
						var remarks = $( '#changeBillDateRemarks' ).val();
						var ticketId = $( '#changeBillDateSRT' ).val();

						$( '#changeBillDateRemarks ~ span' ).addClass( 'hidden' );
						$( '#changeBillDateRemarks ~ font' ).addClass( 'hidden' );
						$( '#newBillDateId ~ font' ).addClass( 'hidden' );
						$( '#newBillDateId ~ span' ).addClass( 'hidden' );
						$( '#changeBillDateSRT ~ font' ).addClass( 'hidden' );
						if ( newValue == '0' ) {
							$( '#newBillDateId ~ font' ).removeClass( 'hidden' );
							valid = false;
						}
						else if ( newValue == oldValue ) {
							$( '#newBillDateId ~ span' ).removeClass( 'hidden' );
							valid = false;
						}
						else if ( ticketId == '' || ticketId == null || ticketId == undefined ) {
							$( '#changeBillDateSRT ~ font' ).removeClass( 'hidden' );
							valid = false;
						}
						else if ( remarks.trim() == '' || remarks == null || remarks == undefined ) {
							$( '#changeBillDateRemarks ~ span' ).removeClass( 'hidden' );
							valid = false;
						}
						else if ( remarks.trim().length < 2 || remarks.trim().length > 4000 ) {
							$( '#changeBillDateRemarks ~ font' ).removeClass( 'hidden' );
							valid = false;
						}
						if ( !valid ) {
							return;
						}
						if ( confirm( 'Are you sure to change the billing date ?' ) ) {
							$( '.loadingPopup' ).removeClass( 'hidden' );
							document.forms[ 1 ].action = "customerProfile.do?method=updateCustomerBillCycle";
							document.forms[ 1 ].submit();
						}
					} );

					$( '#shiftingInitiation' ).click( function(){
						if ( $( "#shifting_initiation" ).valid() ) {
							if ( confirm( 'Are you sure to initiate shifting ?' ) ) {
								$( '.loadingPopup' ).removeClass( 'hidden' );
								document.forms[ 1 ].action = "shiftingWorkflow.do?method=shiftingInitiation";
								document.forms[ 1 ].submit();
							}
						}
					} );

					$( "#ifrStage" ).validate(
							{

								rules : {
									'shiftingWorkflowPojo.npId' : {
										dropDown : true
									}, 'remarksPojo.remarks' : {
										required : true, maxlength : 4000, minlength : 2
									},
								},
								messages : {
									'shiftingWorkflowPojo.npId' : {
										dropDown : "<font class='errorTextbox' style='width: 168px;'> Please select 'Network Partner'.</font>"
									},
									'remarksPojo.remarks' : {
										required : "<font class='errorAddress'> Please provide your 'Remarks'</font>",
										minlength : "<font class='errorAddress'> Please enter Remarks between [2-4000].</font>",
										maxlength : "<font class='errorAddress'> Please enter Remarks between [2-4000].</font>"
									},
								}
							} );

					$( "#csdOutcallStage" ).validate(
							{

								rules : {
									'customerResponse' : {
										required : true
									}, 'remarksPojo.remarks' : {
										required : true, maxlength : 4000, minlength : 2
									},
								},
								messages : {
									'remarksPojo.remarks' : {
										required : "<font class='errorAddress'> Please provide your 'Remarks'</font>",
										minlength : "<font class='errorAddress'> Please enter Remarks between [2-4000].</font>",
										maxlength : "<font class='errorAddress'> Please enter Remarks between [2-4000].</font>"
									},
								}
							} );

					$( "#shifting_initiation" )
							.validate(
									{

										rules : {
											'shiftingWorkflowPojo.pincode' : {
												required : true
											}, 'stateName' : {
												dropDown : true
											}, 'cityName' : {
												dropDown : true
											}, 'areaName' : {
												required : true
											}, 'shiftingWorkflowPojo.society' : {
												required : true
											}, 'remarksPojo.remarks' : {
												required : true, maxlength : 4000, minlength : 2
											}
										},
										messages : {
											'shiftingWorkflowPojo.pincode' : {
												required : "<font class='errorTextbox'>Please provide 'Pincode.'</font>"
											},
											'stateName' : {
												dropDown : "<font class='errorTextbox'>Please select 'State.'</font>"
											},
											'cityName' : {
												dropDown : "<font class='errorTextbox'>Please select 'City.'</font>"
											},
											'areaName' : {
												required : "<font class='errorTextbox'>Please select 'Area.'</font>"
											},
											'shiftingWorkflowPojo.society' : {
												required : "<font style='width: 200px;' class='errorTextbox'>Please select 'Locality/ Sector - Society'.' </font>"
											},
											'remarksPojo.remarks' : {
												required : "<font class='errorAddress'> Please provide your 'Remarks'</font>",
												minlength : "<font class='errorAddress'> Please enter Remarks between [2-4000].</font>",
												maxlength : "<font class='errorAddress'> Please enter Remarks between [2-4000].</font>"
											},

										}
									} );

					$( "#submit_ifrStage" ).click( function(){
						if ( $( "#ifrStage" ).valid() ) {
							var answer = confirm( "Please confirm if you want to forward the docket" );
							if ( answer ) {
								$( '.loadingPopup' ).removeClass( 'hidden' );
								document.forms[ 1 ].action = "shiftingWorkflow.do?method=submitIfrStage";
								document.forms[ 1 ].submit();
							}
						}
					} );

					$( "#submit_csdStage" ).click( function(){
						if ( $( "#csdOutcallStage" ).valid() ) {
							submitCSDAddressShifting();
						}
					} );

					$( '#paymentModeQRC' ).bind( 'change', function(){
						var paymentModeVal = $( this ).val();
						viewPaymentmodeqrc( paymentModeVal );
					} );

					$( '#submit_gracePeriod' ).click(
							function(){

								var reason = $( '#GP_ReasonId' ).val();
								// var ticketId = $('#bubTicketId').val();
								var remarks = $( '#GP_RemarksId' ).val();
								var days = $( '#GP_days' ).val();
								var valid = true;

								if ( reason == "" || reason == 0 || reason == null || reason == undefined ) {
									$( '#GP_ReasonId ~ font' ).removeClass( 'hidden' );
									valid = false;
								}
								else {
									$( '#GP_ReasonId ~ font' ).addClass( 'hidden' );
								}

								/*
								 * if(ticketId == '' || ticketId == null || ticketId == undefined){ $('#bubTicketId ~ font').removeClass('hidden'); valid = false; }else{ $('#bubTicketId ~ font').addClass('hidden'); }
								 */

								if ( remarks.trim() == '' || remarks == null || remarks == undefined || remarks.trim().length < 2
										|| remarks.trim().length > 4000 ) {
									$( '#GP_RemarksId ~ font' ).removeClass( 'hidden' ).addClass( 'displayB' );
									valid = false;
								}
								else {
									$( '#GP_RemarksId ~ font' ).addClass( 'hidden' ).removeClass( 'displayB' );
								}
								if ( days == "" || days == 0 || days == null || days == undefined ) {
									$( '#GP_days ~ font' ).removeClass( 'hidden' );
									valid = false;
								}
								else {
									$( '#GP_days ~ font' ).addClass( 'hidden' );
								}

								if ( !valid ) {
									return;
								}
								// rubina
								if ( confirm( "Are you sure you want to apply validity extension. " ) ) {
									$( '.loadingPopup' ).removeClass( 'hidden' );
									document.forms[ 1 ].action = "customerProfile.do?method=saveGracePeriod";
									document.forms[ 1 ].submit();
								}

							} );

					$( '[name="planTypeShifting"]' ).click( function(){
						$( '#addOnDulValueCRF' ).attr( 'disabled', true );
						$( '#addOnDulValueCRF' ).addClass( 'gray_text' );
						$( '#basePlanNameCRF' ).removeClass( 'gray_text' );
						$( '#basePlanNameCRF' ).removeAttr( 'disabled', false );
						planTypeShifting( $( this ).val() );
						setPlanAmounts( 'securityChrgeId', 'rentalChargeId', 'amountId', 'total', 0 );
					} );

				} );

function planTypeShifting( planTypeValue ){
	var product = $( '#product_CSDL2' ).val();
	var serviceType = $( '#serviceType_CSDL2' ).val();
	var id = document.getElementById( "basePlanNameCRF" );
	crmDwr.getActivationPlan( product, serviceType, planTypeValue, function( list ){
		if ( list.length > 0 ) {
			dwr.util.removeAllOptions( id );
			dwr.util.addOptions( id, [
				"Select Base Plan Name"
			] );
			dwr.util.addOptions( id, list, "planCode", "planName" );
		}
		else {
			dwr.util.removeAllOptions( id );
			dwr.util.addOptions( id, [
				"Select Base Plan Name"
			] );
			alert( "PlanType " + planTypeValue + " is not available in master plan" );
		}
		// $( '.loadingPopup' ).addClass( 'hidden' );
	} );
}

$( window ).load(
		function(){
			var planCategory = $( '#planCategoryId' ).val();
			var $planUsageType = $( '[name="planUsageType"]' );
			if ( planCategory == "Base plan migration" || planCategory == "Plan Renewal" || planCategory == "Plan Reactivation" ) {

				$( '#newBasePlanCodeID' ).triggerHandler( 'change' );
			}
			else if ( planCategory == 'Addon Plans' ) {
				if ( $planUsageType.is( ':checked' ) && $planUsageType.filter( ':checked' ).val() == "CHANGE_ADDON" ) {
					$( '#newAddonPlanCodeID' ).triggerHandler( 'change' );
				}
				else if ( $planUsageType.is( ':checked' ) && $planUsageType.filter( ':checked' ).val() == "REMOVE_ADDON" ) {
					planMigrationPolicy( planCategory, $( '#oldBasePlanCodeId' ).val(), null, $( '#oldAddonPlanCodeId' ).val(), null, $(
							'#serviceTypeId' ).val(), $( '#customerId' ).val() );
				}
			}
			// $( 'input[name=selectedPlanCode]:checked' ).trigger( 'click' );
			// if ( $( '#addonPlanId' ).val() != '' && $( '#addonPlanId' ).val() != null && $( '#addonPlanId' ).val() != undefined ) {
			// $( '#newAddonPlanCodeID[data-code="' + $( '#addonPlanId' ).val() + '"]' ).attr( 'checked', true ).triggerHandler( 'click' );
			// $( '#newAddonPlanCodeID[data-code="' + $( '#addonPlanId' ).val() + '"]' ).attr( 'checked', true ).triggerHandler( 'change' )
			// addonQuota = $( '#newAddonPlanCodeID[data-code="' + $( '#addonPlanId' ).val() + '"]' ).attr( 'data-quota' );
			// if ( $( '#newBasePlanCodeID:checked' ).length ) {
			// setPlanInfo( null, null, addonQuota, null );
			// }
			// }
		} );

function fillWaiverCategories( list ){
	dwr.util.removeAllOptions( 'waiverHead' );
	dwr.util.addOptions( 'waiverHead', [
		{
			key : "", value : "Please select"
		}
	], 'key', 'value' );
	if ( list != null ) {
		dwr.util.addOptions( 'waiverHead', list, 'qrcSubSubCategory', 'qrcSubSubCategory' );
	}
	$( '.loadingPopup' ).addClass( 'hidden' );
}

function addressChangeFeasibility(){
	window.open( 'manageQrc.do?method=addressChangeFeasibility', 'newWindow', 'width=620,height=350,scrollbars=yes,resizable=no,toolbar=no' );
}

function populateSubCategoriesnew( inDestinationID, inAccessID, inDestSubSubCatID ){
	crmDwr.getActiveQrcSubCategories( inAccessID, function( list ){
		addSubCategories( inDestinationID, list );
	} );

	function addSubCategories( id, list ){
		subCats = [];
		var cat;
		var subCatId = document.getElementById( id );
		if ( subCatId != null ) {
			if ( list != null ) {

				list.forEach( function( e ){
					cat = {}
					cat[ 'label' ] = e.qrcSubCategory;
					cat[ 'value' ] = e.qrcSubCategoryId;
					// console.log(e.qrcSubCategory);
					subCats.push( cat );
				} );
				/*
				 * dwr.util.removeAllOptions( id ); dwr.util.addOptions( id, [ "Please Select" ] ); if ( list != null ) { dwr.util.addOptions( id, list, "qrcSubCategoryId", "qrcSubCategory" ); // $('#'+id).removeAttr('disabled'); } else { alert( "No SubCategory register for given category." ); }
				 */
			}

		}

		$( '#subCategoryIdSearch' ).autocomplete( {
			source : subCats, minLength : 0, change : function( e, ui ){
				// resetTicketSearchAutocomplete( false ,true );
				// $( '#categoryIdSearch' ).val( '' );
				// $( '#subCategoryIdSearch' ).val( '' );
				// $( '#subSubCategoryIdSearch' ).val( '' );
				if ( !ui.item ) {
					$( e.target ).val( '' );
				}
				else {
					$( '#qrcSubCategoryId' ).val( ui.item.value );
					// alert(ui.item.value+"catId"+inAccessID);
					populateSubSubCategoriesnew( inAccessID, ui.item.value, inDestSubSubCatID );

				}
			}, select : function( e, ui ){
				e.preventDefault();
				$( e.target ).val( ui.item.label );
			}, focus : function( e, ui ){
				return false;
			}
		} );
	}

}

function populateSubSubCategoriesnew( inCategoryId, inSubCategoryId, inDestSubSubCatID ){
	console.log( "CategoryID  " + inCategoryId + "   SubcatID  " + inSubCategoryId );
	var process = true;
	if ( inSubCategoryId != '0' ) {
		// var categoryID = document.getElementById( inCategoryId ).value;
		// var subsubcategory=document.getElementById( qrcSubCategoryId ).value;
		var qrcType = "";
		if ( $( '[name="qrcType"]:checked' ).val() != undefined ) {
			qrcType = $( '[name="qrcType"]:checked' ).val();
			if ( qrcType == undefined ) {
				$( '#qrcTypeComplaint' ).parent().next( 'font' ).show().html( "Please select QRC Type" ).addClass( 'errorTextbox' );
				process = false;
			}
			else {
				$( '#qrcTypeComplaint' ).parent().next( 'font' ).hide();
			}
		}
		crmDwr.getActiveQrcSubSubCategoriesByType( qrcType, inCategoryId, inSubCategoryId, function( list ){
			addSubSubCategories( inDestSubSubCatID, list );
		} );

		function addSubSubCategories( id, list ){
			subCatCatsnew = [];
			var subCatCatnew;
			var subsubcategory = document.getElementById( id );
			if ( subsubcategory != null ) {
				if ( list != null ) {

					list.forEach( function( e ){
						subCatCatnew = {};
						subCatCatnew[ 'label' ] = e.qrcSubSubCategory;
						subCatCatnew[ 'value' ] = e.qrcSubSubCategoryId;
						console.log( e.qrcSubSubCategory );
						subCatCatsnew.push( subCatCatnew );
					} );
					$( '#subSubCategoryIdSearch' ).autocomplete( {
						source : subCatCatsnew, minLength : 0, change : function( e, ui ){
							// $( '#qrcSubSubCategoryId' ).val( '' );
							if ( !ui.item ) {
								$( e.target ).val( '' );
							}
							else {
								$( '#qrcSubSubCategoryId' ).val( ui.item.value );
							}
						}, select : function( e, ui ){
							e.preventDefault();
							$( e.target ).val( ui.item.label );
						}, focus : function( e, ui ){
							return false;
						}
					} );
				}
			}

		}
	}
}

function populateActions( inDestinationID, inSubSubCategoryId ){
	$( '.rcaResolutionCodeDropdowns' ).addClass( 'hidden' );
	if ( inSubSubCategoryId != 'Select Sub SubCategory' ) {
		var categoryID = document.getElementById( "categoryId" ).value;
		var subCategoryID = document.getElementById( "subCategoryId" ).value;
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
					// $('#'+id).removeAttr('disabled');
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

function fillFunctionBinIdForForward( mappingID, action ){
	if ( action === '1' ) {
		$( '.rcaResolutionCodeDropdowns' ).addClass( 'hidden' );
		var categoryID = document.getElementById( "categoryId" ).value;
		var subCategoryID = document.getElementById( "subCategoryId" ).value;
		var subSubCategoryID = document.getElementById( "subSubCategoryId" ).value;
		crmDwr.getFunctionalBinID( categoryID, subCategoryID, subSubCategoryID, {
			callback : function( functionBinId ){
				document.getElementById( mappingID ).value = functionBinId;
			}
		} );
	}
	else {
		var qrcType = document.getElementById( 'qrcType' ).value;
		if ( qrcType == 'C' ) {
			$( '.rcaResolutionCodeDropdowns' ).removeClass( 'hidden' );
			var qrcCategoryId = document.getElementById( "categoryId" ).value;
			var product = document.getElementById( "productId" ).value;
			crmDwr.getRca( product, qrcCategoryId, function( list ){
				addRca( list );
			} );

			function addRca( list ){
				var select = document.getElementById( "actionTakenId" );
				var id = "actionTakenId";
				if ( select != null ) {
					if ( list != null ) {
						dwr.util.removeAllOptions( id );
						dwr.util.addOptions( id, [
							{
								value : '0', name : 'Select Action Taken'
							}
						], 'value', 'name' );
						dwr.util.addOptions( id, list, "contentValue", "contentName" );
						// $('#'+id).removeAttr('disabled');
					}
					else {
						alert( "Action Taken list is not available." );
						removeList( id );
					}
				}
			}
		}
	}

}

$( '#actionTakenId' ).bind( "change", function(){

	if ( $( this ).val() == 0 ) {
		$( this ).next( 'font' ).show().html( "Please select RCA" ).addClass( 'errorTextbox' );
	}
	else {
		$( this ).next( 'font' ).hide();
	}
} );

function emptyChecksQRC(){
	$( '#subCategoryError' ).addClass( 'hidden' );
	$( '#subSubCategoryError' ).addClass( 'hidden' );
	var isReceivedValidData = true;
	// var categoryId = document.getElementById( 'categoryId' ).value;
	var subCategory = document.getElementById( 'qrcSubCategoryTextId' ).value;
	var subSubCategory = document.getElementById( 'qrcSubSubCategoryTextId' ).value;
	// var subCategoryListValue = $( '#subCategoryId' ).find( 'option:selected' ).text();
	// var subSubCategoryListValue = $( '#subSubCategoryId' ).find( 'option:selected' ).text();
	var remarks = document.getElementById( 'remarks' ).value;
	var resolutionType = document.getElementById( 'resolutionType' ).value;
	var callingNo = document.getElementById( 'callingNo' ).value;
	var qrcType = document.getElementById( 'qrcType' ).value;

	if ( ( qrcType == 'C' ) && ( resolutionType == 0 ) ) {
		// var rca = document.getElementById( 'actionTakenId' ).value;
		//
		// var rcode = document.getElementById( 'rootCauseId' ).value;

		if ( document.getElementById( 'actionTakenId' ).value == 0 ) {
			isReceivedValidData = false;
			$( '#actionTakenId' ).next( 'font' ).show().html( "Please select 'Action Taken'" ).addClass( 'errorTextbox' );
			document.getElementById( 'actionTakenId' ).value = '';
		}

		if ( document.getElementById( 'rootCauseId' ).value == 0 ) {
			isReceivedValidData = false;

			$( '#rootCauseId' ).parent( 'span' ).next( 'font' ).show().html( "Please select 'Root Cause'" ).addClass( 'errorTextbox' );
			document.getElementById( 'rootCauseId' ).value = '';
		}

	}

	if ( callingNo === 0 ) {
		callingNo = '';
	}

	/*
	 * if ( categoryId === '0' ) { isReceivedValidData = false; $( '#categoryId' ).next( 'font' ).show().html( "Please select Category" ).addClass( 'errorTextbox' ); // document.getElementById( 'categoryId' ).value = ''; }
	 */
	if ( subCategory == null || subCategory == '' ) {
		isReceivedValidData = false;
		$( '#subCategoryError' ).show().html( "Please enter sub Category" ).removeClass( 'hidden' ).addClass( 'errorTextbox' );
	}
	// else if ( subCategory != subCategoryListValue ) {
	// isReceivedValidData = false;
	// $( '#subCategoryError' ).show().html( "Please Enter Sub Category From Auto Generated List." ).removeClass( 'hidden' ).addClass(
	// 'errorTextbox' );
	// }
	if ( subSubCategory == null || subSubCategory == '' ) {
		isReceivedValidData = false;
		$( '#subSubCategoryError' ).show().html( "Please enter sub Sub Category" ).removeClass( 'hidden' ).addClass( 'errorTextbox' );
	}
	// else if ( subSubCategory != subSubCategoryListValue ) {
	// isReceivedValidData = false;
	// $( '#subSubCategoryError' ).show().html( "Please Enter Sub Sub Category From Auto Generated List" ).removeClass( 'hidden' ).addClass(
	// 'errorTextbox' );
	// }
	if ( callingNo != "" && callingNo.length < 10 ) {
		isReceivedValidData = false;
		$( '#callingNo' ).next( 'font' ).show().html( "Please enter ten digits calling number." ).addClass( 'errorTextbox' ).css( 'width', 200 );
	}

	if ( remarks.trim() === '' || remarks.trim().length < 2 || remarks.trim().length > 4000 ) {
		isReceivedValidData = false;
		$( '#remarks' ).next( 'font' ).show().html( "Please enter Remarks between [2-4000]." ).addClass( 'errorTextArea' ).css( 'width', 200 );
	}
	if ( resolutionType === 'Please Select' ) {
		isReceivedValidData = false;
		$( '#resolutionType' ).next( 'font' ).show().html( "Please select Action" ).addClass( 'errorTextbox' );
		// document.getElementById( 'resolutionType' ).value = '';
	}
	/*
	 * if (callingNo !== /^[0-9]{10}$/g) { isReceivedValidData = false; $('#callingNo').next('font').show().html("Please enter valid no").addClass('errorTextbox'); }
	 */
	return isReceivedValidData;
}

function populateInteractionSubCategory( inDestinationID, inInteractionCategory ){
	if ( inInteractionCategory != '' ) {
		crmDwr.getInteractionSubCategories( inInteractionCategory, function( list ){
			addInteractionSubCategories( inDestinationID, list );
		} );

		function addInteractionSubCategories( id, list ){
			var select = document.getElementById( id );
			if ( select != null ) {
				if ( list != null ) {
					dwr.util.removeAllOptions( id );
					dwr.util.addOptions( id, [
						{
							"value" : "", "label" : "Please Select"
						}
					], "value", "label" );
					dwr.util.addOptions( id, list, "categoryValue", "categoryValue" );
					// $('#'+id).removeAttr('disabled');
				}
				else {
					alert( "Actions List is empty for current selected category." );
					removeList( id );
				}
			}
		}

	}
	else {

		dwr.util.removeAllOptions( inDestinationID );
		dwr.util.addOptions( inDestinationID, [
			{
				"value" : "", "label" : "Please Select"
			}
		], "value", "label" );

	}

}

function postCustomerInteraction(){
	var isReceivedValidData = true;
	var interactionCategory = document.getElementById( 'interactionCategory' ).value;
	var interactionSubCategory = document.getElementById( 'interactionSubCategory' ).value;
	var remarks = document.getElementById( 'remarks' ).value;
	$( '#interactionCategory' ).next( 'font' ).hide();
	$( '#interactionSubCategory' ).next( 'font' ).hide();
	$( '#remarks' ).next( 'font' ).hide();
	if ( interactionCategory === '0' ) {
		isReceivedValidData = false;
		$( '#interactionCategory' ).next( 'font' ).show().html( "Please select Category" ).addClass( 'errorTextbox' );
	}
	if ( interactionSubCategory === '' ) {
		isReceivedValidData = false;
		$( '#interactionSubCategory' ).next( 'font' ).show().html( "Please select Sub-Category" ).addClass( 'errorTextbox' );
	}
	if ( remarks.trim() === '' || remarks.trim().length < 2 || remarks.trim().length > 4000 ) {
		isReceivedValidData = false;
		$( '#remarks' ).next( 'font' ).show().html( "Please enter Remarks between [2-4000]." ).addClass( 'errorRemarks' );
	}
	if ( isReceivedValidData ) {
		if ( confirm( 'Are you sure you want to add Customer Interaction?' ) ) {
			$( '.loadingPopup' ).removeClass( 'hidden' );
			document.forms[ 1 ].action = "manageQrc.do?method=createCustomerInteraction";
			document.forms[ 1 ].submit();
		}
	}
}

function searchCustomerInteraction(){
	var category = document.getElementById( 'interactionCategorySearch' ).value;
	if ( category === 'Select Category' ) {
		document.getElementById( 'interactionCategorySearch' ).value = "";
	}
	var subCategory = document.getElementById( 'interactionSubCategorySearch' ).value;
	if ( subCategory === 'Select SubCategory' ) {
		document.getElementById( 'interactionSubCategorySearch' ).value = "";
	}
	if ( true ) {
		$( '.loadingPopup' ).removeClass( 'hidden' );
		document.forms[ 1 ].action = "manageQrc.do?method=searchCustomerInteraction";
		document.forms[ 1 ].submit();
	}

}

function saveTicketRemarks( inType ){
	var isReceivedValidData = true;
	$( '#remarks ~ font' ).addClass( 'hidden' );
	$( '#remarks ~ span' ).addClass( 'hidden' );
	var remarks = document.getElementById( 'remarks' ).value;
	if ( remarks.trim() == '' || remarks == null || remarks == undefined ) {
		$( '#remarks ~ font' ).removeClass( 'hidden' );
		isReceivedValidData = false;
		return;
	}
	else if ( remarks.trim().length < 2 || remarks.trim().length > 4000 ) {
		$( '#remarks ~ span' ).removeClass( 'hidden' );
		isReceivedValidData = false;
		return;
	}
	if ( isReceivedValidData == true ) {
		if ( confirm( "Do you want to save remarks ?" ) ) {
			if ( inType == 'view' ) {
				document.forms[ 1 ].action = "manageQrc.do?method=saveTicketRemarks";
				document.forms[ 1 ].submit();
			}
		}
	}
}
function saveTicketFlagRemarks( inType ){
	var isReceivedValidData = true;
	$( '#remarks ~ font' ).addClass( 'hidden' );
	$( '#remarks ~ span' ).addClass( 'hidden' );
	var remarks = document.getElementById( 'remarks' ).value;
	if ( remarks.trim() == '' || remarks == null || remarks == undefined ) {
		$( '#remarks ~ font' ).removeClass( 'hidden' );
		isReceivedValidData = false;
		return;
	}
	else if ( remarks.trim().length < 2 || remarks.trim().length > 4000 ) {
		$( '#remarks ~ span' ).removeClass( 'hidden' );
		isReceivedValidData = false;
		return;
	}
	if ( isReceivedValidData == true ) {
		if ( confirm( "Do you want to save flag remarks ?" ) ) {
			if ( inType == 'view' ) {
				document.getElementById( 'actioId' ).value = 'FR';
				document.forms[ 1 ].action = "manageQrc.do?method=saveTicketRemarks";
				document.forms[ 1 ].submit();
			}
		}
	}
}

function saveTicket(){
	if ( emptyChecksQRC() ) {
		if ( confirm( 'Are you sure to Generate Ticket?' ) ) {
			$( '.loadingPopup' ).removeClass( 'hidden' );
			document.forms[ 1 ].action = "manageQrc.do?method=logTicket";
			document.forms[ 1 ].submit();
		}
	}
}

function saveQrcAction( inAction ){

	if ( inAction == 'forward' ) {
		var reply = confirm( "Do you want to forward this ticket ?" );
		if ( reply ) {
			document.forms[ 1 ].action = "manageQrc.do?method=saveTicketAction&inAction=" + inAction;
			document.forms[ 1 ].submit();
		}
	}
	else if ( inAction == 'followup' ) {
		var reply = confirm( "Do you want to save the followUp date for this ticket ?" );
		if ( reply ) {
			document.forms[ 1 ].action = "manageQrc.do?method=saveTicketAction&inAction=" + inAction;
			document.forms[ 1 ].submit();
		}
	}
	else if ( inAction == 'resolve' ) {
		var reply = confirm( "Do you want to resolve this ticket ?" );
		if ( reply ) {
			document.forms[ 1 ].action = "manageQrc.do?method=saveTicketAction&inAction=" + inAction;
			document.forms[ 1 ].submit();
		}
	}
}

function fillUsersList( inDestinationID, inFucntionBinID ){
	crmDwr.getUsersByBinID( inFucntionBinID, function( list ){
		addUsers( inDestinationID, list );
	} );

	function addUsers( id, list ){
		var select = document.getElementById( id );
		if ( select != null ) {
			if ( list != null ) {
				dwr.util.removeAllOptions( id );
				dwr.util.addOptions( id, [
					{
						value : '', name : 'Please Select'
					}
				], 'value', 'name' );
				dwr.util.addOptions( id, list );
				// $('#'+id).removeAttr('disabled');
			}
			else {
				alert( "User List is empty." );
				removeList( id );
			}
		}
	}
}

function bulkMassOutageOperation(){
	$( '#etrDateID' ).next( 'font' ).hide();
	$( '#fileUpload' ).next( 'font' ).hide();
	var isReceivedValidData = true;
	var date = document.getElementById( 'etrDateID' ).value;
	var hh = document.getElementById( 'etrHourID' ).value;
	var mm = document.getElementById( 'etrMinuteID' ).value;
	var ss = document.getElementById( 'etrSecondID' ).value;
	var etrDateTime = date + " " + hh + ":" + mm + ":" + ss;
	document.getElementById( 'etrDateTimeID' ).value = etrDateTime;
	var massOutageExcelFile = document.getElementById( 'fileUpload' ).value;
	var addOutageType = document.getElementById( 'addOutageType' ).checked;
	var removeOutageType = document.getElementById( 'removeOutageType' ).checked;
	var remarks = document.getElementById( 'remarks' ).value;
	if ( remarks.trim() === '' || remarks.trim().length < 2 || remarks.trim().length > 4000 ) {
		isReceivedValidData = false;
		$( '#remarks' ).next( 'font' ).show().html( "Please enter Remarks between [2-4000]." ).addClass( 'errorTextbox' ).css( 'top', 95 );
	}
	if ( massOutageExcelFile == '' ) {
		isReceivedValidData = false;
		$( '#fileUpload' ).next( 'font' ).show().html( "File not selected." ).addClass( 'errorTextArea' ).css( {
			'top' : '35px'
		} );

	}

	if ( addOutageType ) {
		if ( date == '' ) {
			isReceivedValidData = false;
			$( '#etrDateID' ).next( 'font' ).show().html( "Please select valid ETR Date Time." ).addClass( 'errorTextbox' ).css( 'top', 50 );
		}
		else {
			var isoDate = getISODate( date );
			isoDate.setHours( hh, mm, ss, 0 );
			var daysdiffMassOutage = new Date() > isoDate;
			if ( daysdiffMassOutage ) {
				isReceivedValidData = false;
				$( '#etrDateID' ).next( 'font' ).show().html( "Please select future ETR Date / Time." ).addClass( 'errorTextbox' ).css( {
					'top' : '50px', 'width' : '190px'
				} );
			}
		}
	}
	if ( isReceivedValidData == true ) {
		if ( confirm( "Are you sure you want to upload mass outage operation file?" ) ) {
			$( '.loadingPopup' ).removeClass( 'hidden' );
			document.forms[ 1 ].action = "manageQrc.do?method=massOutageOperations";
			document.forms[ 1 ].submit();
		}
	}
}
function hideUnhideEtrdate( inParam ){
	if ( inParam == 'hide' ) {
		$( '#etrDatediv' ).addClass( 'hide1' );

	}
	if ( inParam == 'unhide' ) {
		$( '#etrDatediv' ).removeClass( 'hide1' );
	}
}

function openChangeDevicePopup( npId ){
	// alert('npId'+npId);
	window.open( 'manageQrc.do?method=changeDevicePopupPage&npId=' + npId, 'newWindow',
			'width=1100,height=380,scrollbars=yes,resizable=no,toolbar=no' );
}
function saveMacQRC( product, requestType ){
	flag_deviceChangeMac = false;
	$( '#IDqrcNewPriMacId' ).next( 'font' ).hide();
	$( '#IDsaveMacQrcDeviceChange' ).next( 'font' ).hide();
	if ( product != 'EOC' ) {
		// BB & RF
		// primary mandatory for BB
		if ( $( '#IDqrcNewPriMacId' ).val() == '' ) {
			$( '#IDqrcNewPriMacId' ).next( 'font' ).show().html( "Please enter 'New MAC Address'" ).addClass( 'errorTextbox' ).css( 'width', 215 );
		}
		else if ( $( '#IDqrcNewPriMacId' ).val() == $( '#IDqrcOldPriMacId' ).val() ) {
			$( '#IDqrcNewPriMacId' ).next( 'font' ).show().html( "'New MAC Address' should not be equal to 'Old MAC Address'" ).addClass(
					'errorTextbox' ).css( 'width', 380 );
		}
		else {
			if ( checkHexadecimal( $( '#IDqrcNewPriMacId' ).val() ) ) {
				flag_deviceChangeMac = true;
			}
			else {
				$( '#IDqrcNewPriMacId' ).next( 'font' ).show().html(
						"'New MAC Address' should be hexadecimal value and address should  be in MAC format [23aF.D3de.A12f]" ).addClass(
						'errorTextbox' ).css( 'width', 380 );
			}
		}
	}
	else {
		// EOC
		// primary and secondary for EOC
		$( '#IDqrcNewSecMacId' ).next( 'font' ).hide();
		if ( $( '#IDqrcNewSecMacId' ).val() == "" ) {
			$( '#IDqrcNewSecMacId' ).next( 'font' ).show().html( "Please select 'Secondary MAC Address.'" ).addClass( 'errorTextbox' ).css( 'width',
					'auto' );

		}
		else if ( $( '#IDqrcNewPriMacId' ).val() == '' ) {
			$( '#IDqrcNewPriMacId' ).next( 'font' ).show().html( "Please give 'Primary MAC Address'" ).addClass( 'errorTextbox' ).css( 'width', 380 );
		}
		else if ( $( '#IDqrcNewPriMacId' ).val() == '' && $( '#IDqrcNewSecMacId' ).val() == '' ) {
			$( '#IDqrcNewPriMacId' ).next( 'font' ).show().html( "Please give one of 'Primary MAC Address' or 'Secondary MAC Address'" ).addClass(
					'errorTextbox' ).css( 'width', 380 );
		}
		else if ( $( '#IDqrcNewPriMacId' ).val() == $( '#IDqrcOldPriMacId' ).val() ) {
			// new primary and secondary should not be equal to old values
			$( '#IDqrcNewPriMacId' ).next( 'font' ).show().html( "Please change one of 'Primary MAC Address' or 'Secondary MAC Address'" ).addClass(
					'errorTextbox' ).css( 'width', 380 );
		}
		else {
			if ( checkHexadecimal( $( '#IDqrcNewPriMacId' ).val() ) ) {
				flag_deviceChangeMac = true;
			}
			else {
				$( '#IDqrcNewPriMacId' ).next( 'font' ).show().html(
						"'New Primary MAC Address' should be hexadecimal value and address should  be in MAC format [23aF.D3de.A12f]" ).addClass(
						'errorTextbox' ).css( 'width', 600 );
			}
		}
	}
	if ( flag_deviceChangeMac && emptyCheckSRTandRemarks() ) {
		var reply = confirm( "Do you want to save new MAC ?" );
		if ( reply ) {
			$( '.loadingPopup' ).removeClass( 'hidden' );
			document.forms[ 1 ].action = "manageQrc.do?method=editMac&inRequestType=" + requestType;
			document.forms[ 1 ].submit();
		}
	}
}
function saveOption82QRC( product, requestType ){

	var flag_deviceChangeOption82 = false;

	$( '#IDqrcNewOption82' ).next( 'font' ).hide();
	$( '#IDqrcNewOption82show' ).next( 'font' ).hide();
	if ( $( '#IDqrcNewOption82' ).val() == '' ) {
		if ( product != 'BB' ) {
			$( '#IDqrcNewOption82show' ).next( 'font' ).show().html( "Please click on the text box and generate Nasport ID" ).addClass(
					'errorTextbox' ).css( 'width', 300 );
		}
		else {
			$( '#IDqrcNewOption82' ).next( 'font' ).show().html( "Please provide Option 82" ).addClass( 'errorTextbox' ).css( 'width', 300 );
		}
	}

	else if ( $( '#IDqrcNewOption82' ).val() == $( '#IDqrcOldOption82' ).val()
			|| $( '#IDqrcNewOption82show' ).val() == $( '#IDqrcOldOption82' ).val() ) {
		if ( product != 'BB' ) {
			$( '#IDqrcNewOption82show' ).next( 'font' ).show().html( "Old and New Nas Port ID should be different" ).addClass( 'errorTextbox' ).css(
					'width', 250 );
		}
		else {
			$( '#IDqrcNewOption82' ).next( 'font' ).show().html( "Old and New Option82 strings must be different" ).addClass( 'errorTextbox' ).css(
					'width', 250 );
		}
	}
	else {
		flag_deviceChangeOption82 = true;
		/*
		 * if ( !isNaN( $( '#IDqrcNewOption82' ).val() ) ) { flag_deviceChangeOption82 = true; } else { $( '#IDqrcNewOption82' ).next( 'font' ).show().html( "Please provide only number" ).addClass( 'errorTextbox' ).css( 'width', 300 ); }
		 */
	}

	// new option82
	if ( flag_deviceChangeOption82 && emptyCheckSRTandRemarks() ) {
		if ( product == 'BB' ) {
			reply = confirm( "Do you want to save new Option82 ?" );
		}
		if ( product != 'BB' ) {
			reply = confirm( "Do you want to save new Nas Port ID ?" );
		}
		if ( reply && requestType == 'Q' ) {
			$( '.loadingPopup' ).removeClass( 'hidden' );
			document.forms[ 1 ].action = "manageQrc.do?method=editOption82&inRequestType=" + requestType;
			document.forms[ 1 ].submit();
		}
	}
}
function saveCPEWithMAC( oldOwenerShip ){
	var flag_deviceChangeMac = false;
	var newMacId = $( '#IDqrcNewPriMacId' ).val();
	var newOwnerShip = $( '[name="orderDetailsPojo.cpeStatus"]:checked' ).val();
	if ( oldOwenerShip != newOwnerShip ) {
		if ( newMacId == '' ) {
			$( '#IDqrcNewPriMacId' ).next( 'font' ).show().html( "Please enter 'New MAC Address'" ).addClass( 'errorTextbox' ).css( 'width', 215 );
		}
		else if ( $( '#IDqrcNewPriMacId' ).val() == $( '#IDqrcOldPriMacId' ).val() ) {
			$( '#IDqrcNewPriMacId' ).next( 'font' ).show().html( "'New MAC Address' should not be equal to 'Old MAC Address'" ).addClass(
					'errorTextbox' ).css( 'width', 380 );
		}
		else {
			if ( checkHexadecimal( $( '#IDqrcNewPriMacId' ).val() ) ) {
				flag_deviceChangeMac = true;
			}
			else {
				$( '#IDqrcNewPriMacId' ).next( 'font' ).show().html(
						"'New MAC Address' should be hexadecimal value and address should  be in MAC format [23aF.D3de.A12f]" ).addClass(
						'errorTextbox' ).css( 'width', 380 );
			}
		}
		if ( flag_deviceChangeMac && emptyCheckSRTandRemarks() ) {
			var reply = confirm( "Do you want to save CPE with MAC?" );
			if ( reply ) {
				$( '.loadingPopup' ).removeClass( 'hidden' );
				document.forms[ 1 ].action = "manageQrc.do?method=editCPE";
				document.forms[ 1 ].submit();
			}
		}
	}
	else {

		if ( newMacId == '' ) {
			$( '#IDqrcNewPriMacId' ).next( 'font' ).show().html( "Please enter 'New MAC Address'" ).addClass( 'errorTextbox' ).css( 'width', 215 );
		}
		else if ( $( '#IDqrcNewPriMacId' ).val() == $( '#IDqrcOldPriMacId' ).val() ) {
			$( '#IDqrcNewPriMacId' ).next( 'font' ).show().html( "'New MAC Address' should not be equal to 'Old MAC Address'" ).addClass(
					'errorTextbox' ).css( 'width', 380 );
		}
		else {
			if ( checkHexadecimal( $( '#IDqrcNewPriMacId' ).val() ) ) {
				flag_deviceChangeMac = true;
			}
			else {
				$( '#IDqrcNewPriMacId' ).next( 'font' ).show().html(
						"'New MAC Address' should be hexadecimal value and address should  be in MAC format [23aF.D3de.A12f]" ).addClass(
						'errorTextbox' ).css( 'width', 380 );
			}
		}
		if ( flag_deviceChangeMac && emptyCheckSRTandRemarks() ) {
			var reply = confirm( "Do you want to save MAC Address?" );
			if ( reply ) {
				$( '.loadingPopup' ).removeClass( 'hidden' );
				document.forms[ 1 ].action = "manageQrc.do?method=editMac";
				document.forms[ 1 ].submit();
			}
		}
	}
}
function saveMacWithCPEWORKFLOW( product, requestType, workflowId, inStage, inCustomerId, inWorkflowType, oldOwenerShip ){
	var flag_deviceChangeMac = false;
	$( '#IDqrcNewPriMacId' ).next( 'font' ).hide();
	var newMacId = $( '#IDqrcNewPriMacId' ).val();
	var newOwnerShip = $( '[name="orderDetailsPojo.cpeStatus"]:checked' ).val();
	if ( oldOwenerShip != newOwnerShip ) {
		if ( newMacId == '' ) {
			$( '#IDqrcNewPriMacId' ).next( 'font' ).show().html( "Please enter 'New MAC Address'" ).addClass( 'errorTextbox' ).css( 'width', 215 );
		}
		else if ( $( '#IDqrcNewPriMacId' ).val() == $( '#IDqrcOldPriMacId' ).val() ) {
			$( '#IDqrcNewPriMacId' ).next( 'font' ).show().html( "'New MAC Address' should not be equal to 'Old MAC Address'" ).addClass(
					'errorTextbox' ).css( 'width', 380 );
		}
		else {
			if ( checkHexadecimal( $( '#IDqrcNewPriMacId' ).val() ) ) {
				flag_deviceChangeMac = true;
			}
			else {
				$( '#IDqrcNewPriMacId' ).next( 'font' ).show().html(
						"'New MAC Address' should be hexadecimal value and address should  be in MAC format [23aF.D3de.A12f]" ).addClass(
						'errorTextbox' ).css( 'width', 380 );
			}
		}
		if ( flag_deviceChangeMac && commonShiftingError() && emptyCheckRemarks() ) {
			var reply = confirm( "Do you want to save CPE with MAC?" );
			if ( reply ) {
				$( '.loadingPopup' ).removeClass( 'hidden' );
				document.forms[ 1 ].action = "shiftingWorkflow.do?method=saveSpDetails";
				document.forms[ 1 ].submit();
			}
		}
	}
	else {

		if ( newMacId == '' ) {
			$( '#IDqrcNewPriMacId' ).next( 'font' ).show().html( "Please enter 'New MAC Address'" ).addClass( 'errorTextbox' ).css( 'width', 215 );
		}
		else if ( $( '#IDqrcNewPriMacId' ).val() == $( '#IDqrcOldPriMacId' ).val() ) {
			$( '#IDqrcNewPriMacId' ).next( 'font' ).show().html( "'New MAC Address' should not be equal to 'Old MAC Address'" ).addClass(
					'errorTextbox' ).css( 'width', 380 );
		}
		else {
			if ( checkHexadecimal( $( '#IDqrcNewPriMacId' ).val() ) ) {
				flag_deviceChangeMac = true;
			}
			else {
				$( '#IDqrcNewPriMacId' ).next( 'font' ).show().html(
						"'New MAC Address' should be hexadecimal value and address should  be in MAC format [23aF.D3de.A12f]" ).addClass(
						'errorTextbox' ).css( 'width', 380 );
			}
		}
		if ( flag_deviceChangeMac && emptyCheckRemarks() ) {
			var reply = confirm( "Do you want to save MAC Address?" );
			if ( reply ) {
				document.getElementById( "customerId" ).value = inCustomerId;
				document.getElementById( "workflowId" ).value = workflowId;
				document.getElementById( "workflowStage" ).value = inStage;
				document.getElementById( "shiftingType" ).value = inWorkflowType;
				$( '.loadingPopup' ).removeClass( 'hidden' );
				document.forms[ 1 ].action = "manageQrc.do?method=editMac&inRequestType=" + requestType;
				document.forms[ 1 ].submit();
			}
		}
	}
}
function emptyCheckSRTandRemarks(){
	// alert('inEmptycheck');
	// $('#deviceChangeSRT').next('font').hide();
	$( '#deviceChangeRemarks' ).next( 'font' ).hide();
	srtRemarksCheck_flag = true;
	/*
	 * if($('#deviceChangeSRT').val()==''){ $('#deviceChangeSRT').next('font').show().html("Please Provide SR Ticket").addClass('errorTextbox').css('width',300); srtRemarksCheck_flag=false; }
	 */
	if ( $( '#deviceChangeRemarks' ).val().trim() == '' || $( '#deviceChangeRemarks' ).val().trim().length < 2
			|| $( '#deviceChangeRemarks' ).val().trim().length > 4000 ) {
		$( '#deviceChangeRemarks' ).next( 'font' ).show().html( "Please enter 'Remarks' between [2-4000]." ).addClass( 'errorTextbox' ).css( 'width',
				300 ).css( 'top', 100 );
		$( '#deviceChangeRemarks' ).focus();
		location.href = location.href + "#deviceRemarks";
		srtRemarksCheck_flag = false;
	}

	return srtRemarksCheck_flag;
}
function submitQRCNetworkInventory(){
	if ( validValueForMasterQRC() ) {
		setValueForMasterQRC();
	}
}

function setValueForMasterQRC(){

	// var masterName = document.getElementById( "IDQRCmasterNameId" ).value;

	var actionFtthModel = document.getElementById( "actionFttnModel" ).value;
	var masterName = document.getElementById( "IDQRCnasportId" ).value;
	var networkRecordId = $( '#IDQRCpoolNameId' ).val();
	parent.document.getElementById( "IDQRChiddenOntRgMduDone" ).value = actionFtthModel;
	// var nasportId=document.getElementById("IDQRCnasportId").value;
	// window.opener.document.getElementById("IDqrcNewOption82").value = nasportId;
	// window.opener.document.getElementById("IDQRCoption82Id").value = masterName;

	// window.opener.document.getElementById( "IDqrcNewOption82" ).value = masterName;
	parent.document.getElementById( "IDqrcNewOption82show" ).value = masterName;
	parent.document.getElementById( "IDqrcNewOption82" ).value = networkRecordId;

	// alert($('#IDQRCpoolNameId').val());
	parent.$( "#IDqrcNewOption82" ).next( 'font' ).hide();
	// $('#IDqrcNewOption82').next('font').hide()
	parent.$.colorbox.close();
}

function validValueForMasterQRC(){
	$( '#IDQRCmasterNameId' ).parent().next( 'font' ).hide();
	$( '#IDQRCnasportId' ).parent().next( 'font' ).hide();
	if ( $( "input[name='networkConfigurationsPojo.serviceModel']:checked" ).length == 0 ) {
		$( '#actionFttnModel' ).parent().show().next( 'font' ).html( "Please provide service model." ).addClass( 'errorTextbox' ).css( 'width', 383 )
				.css( 'top', 30 );

	}
	else if ( $( '#IDQRCmasterNameId' ).val() == '0' || $( '#IDQRCmasterNameId' ).val() == undefined ) {
		$( '#IDQRCmasterNameId' ).parent().show().next( 'font' ).html( "Please provide 'NASPORT ID' and 'Poolname' by changing master name" )
				.addClass( 'errorTextbox' ).css( 'width', 383 ).css( 'top', 56 );

	}
	else if ( $( '#IDQRCnasportId' ).val() == '0' || $( '#IDQRCpoolNameId' ).val() == '0' || $( '#IDQRCnasportId' ).val() == "Please Select" ) {
		$( '#IDQRCnasportId' ).parent().next( 'font' ).show().html( "Please provide 'NASPORT ID' and 'Poolname' by changing master name" ).addClass(
				'errorTextbox' ).css( 'width', 383 ).css( 'top', 56 );

	}
	else {
		return true;
	}
	return false;
}

/* ================== 19/11/2014 plan migration start ======================= */

function showPlanUnlimited(){
	uncheckPlanType( "U" );
	$( '#newPlanType' ).removeClass( 'hidden' );
	$( '#newPlanTable' ).addClass( 'hidden' );
	$( '#planTicketTable' ).addClass( 'hidden' );
	$( '#planPaymentTable' ).addClass( 'hidden' );
	$( '#planBoosters' ).addClass( 'hidden' );
	$( '#planBoosterType' ).addClass( 'hidden' );
	$( '#PLAN_BOOSTER_TABLE' ).addClass( 'hidden' );
	$( '#planChargesTable' ).addClass( 'hidden' );
	$( '#speedDropdown' ).addClass( 'hidden' );
	$( '#ulHidden' ).addClass( 'hidden' );
	$( '#otherDetailsId' ).addClass( 'hidden' );
	$( '#planInfoText' ).addClass( 'hidden' );
	// $('#unlimitedPaid').parent().removeClass('radio_on');
	// if ( $( '#unlimitedgApp, #unlimitedgMbo' ).parent().hasClass( 'radio_on' ) ) {
	// showNewPlanUnlimitedPayment();
	// }
	// if ( $( '#unlimitedPaid, #unlimitedgFoc, #unlimitedgRet' ).parent().hasClass( 'radio_on' ) ) {
	// showNewUnlimitedPlan();
	// }

}
function showPlanLimited(){
	uncheckPlanType( "L" );
	$( '#newPlanType' ).removeClass( 'hidden' );
	$( '#newPlanTable' ).addClass( 'hidden' );
	$( '#planTicketTable' ).addClass( 'hidden' );
	$( '#planPaymentTable' ).addClass( 'hidden' );
	$( '#planBoosters' ).addClass( 'hidden' );
	$( '#planBoosterType' ).addClass( 'hidden' );
	$( '#PLAN_BOOSTER_TABLE' ).addClass( 'hidden' );
	$( '#planChargesTable' ).addClass( 'hidden' );
	$( '#speedDropdown' ).addClass( 'hidden' );
	$( '#otherDetailsId' ).addClass( 'hidden' );
	$( '#planInfoText' ).addClass( 'hidden' );
	// $('#unlimitedPaid').parent().removeClass('radio_on');
	if ( $( '#unlimitedgApp, #unlimitedgMbo' ).parent().hasClass( 'radio_on' ) ) {
		showNewPlanLimitedPayment();
	}
	if ( $( '#unlimitedPaid, #unlimitedgFoc, #unlimitedgRet' ).parent().hasClass( 'radio_on' ) ) {
		showNewLimitedPlan();
	}

}

function newPlanShow(){
	if ( $( '#unlimitedPlanType' ).parent().hasClass( 'radio_on' ) ) {
		showNewUnlimitedPlan();
	}
	else if ( $( '#limitedPlanType' ).parent().hasClass( 'radio_on' ) ) {
		showNewLimitedPlan();
	}
}

function newPlanPayment(){

	if ( $( '#unlimitedPlanType' ).parent().hasClass( 'radio_on' ) ) {
		showNewPlanUnlimitedPayment();
	}
	else if ( $( '#limitedPlanType' ).parent().hasClass( 'radio_on' ) ) {
		showNewPlanLimitedPayment();
	}
}

function showAddonChange(){
	$( '#ADDON_PAID_FOC_DIV' ).removeClass( 'hidden' );
	$( '#ADDON_PAID_FOC_DIV [type="radio"]' ).removeAttr( 'checked' );
	$( '#ADDON_PAID_FOC_DIV [type="radio"]' ).parent().removeClass( 'radio_on' );
	$( '#otherDetailsId' ).addClass( 'hidden' );
	$( '#addonRemoveRemarks' ).addClass( 'hidden' );
	$( '#addonRemoveRemarks textarea' ).attr( 'disabled', true );
}

function showAddonRemove(){
	// $( '#ADDON_PAID_FOC_DIV' ).addClass( 'hidden' );
	// $( '#newPlanTable' ).addClass( 'hidden' );
	// $( '#otherDetailsId' ).addClass( 'hidden' );
	// $( '#newPlanTable :input' ).attr( 'disabled', true );
	// $( '#otherDetailsId :input' ).attr( 'disabled', true );
	// $( '#addonRemoveRemarks' ).removeClass( 'hidden' );
	// $( '#addonRemoveRemarks textarea' ).removeAttr( 'disabled' );
	$( '.loadingPopup' ).removeClass( 'hidden' );
	document.forms[ 1 ].action = "manageQrc.do?method=getCustomerActivationPlan";
	document.forms[ 1 ].submit();
}

function showNewUnlimitedPlan(){
	$( '#newPlanTable' ).removeClass( 'hidden' );
	$( '#newPlanType' ).removeClass( 'hidden' );
	$( '#planTicketTable' ).removeClass( 'hidden' );
	$( '#planPaymentTable' ).addClass( 'hidden' );
	$( '#planBoosters' ).addClass( 'hidden' );
	$( '#planBoosterType' ).addClass( 'hidden' );
	$( '#PLAN_BOOSTER_TABLE' ).addClass( 'hidden' );
	$( '#planChargesTable' ).addClass( 'hidden' );
	$( '#speedDropdown' ).addClass( 'hidden' );
}

function showNewLimitedPlan(){
	$( '#newPlanTable' ).addClass( 'hidden' );
	$( '#newPlanType' ).removeClass( 'hidden' );
	$( '#planTicketTable' ).removeClass( 'hidden' );
	$( '#planPaymentTable' ).addClass( 'hidden' );
	$( '#planBoosters' ).addClass( 'hidden' );
	$( '#planBoosterType' ).addClass( 'hidden' );
	$( '#PLAN_BOOSTER_TABLE' ).addClass( 'hidden' );
	$( '#planChargesTable' ).addClass( 'hidden' );
	$( '#speedDropdown' ).addClass( 'hidden' );
}

function showNewPlanUnlimitedPayment(){
	showNewUnlimitedPlan();
	$( '#planPaymentTable' ).removeClass( 'hidden' );
}

function showNewPlanLimitedPayment(){
	showNewLimitedPlan();
	$( '#planChargesTable' ).removeClass( 'hidden' );
}

function planBoosterUsage(){
	uncheckPlanType( "T" );
	$( '#newPlanType' ).addClass( 'hidden' );
	$( '#newPlanTable' ).addClass( 'hidden' );
	$( '#planTicketTable' ).addClass( 'hidden' );
	$( '#planPaymentTable' ).addClass( 'hidden' );
	$( '#planBoosterType' ).removeClass( 'hidden' );
	$( '#PLAN_BOOSTER_TABLE' ).addClass( 'hidden' );
	$( '#planChargesTable' ).addClass( 'hidden' );
	$( '#speedDropdown' ).addClass( 'hidden' );
	$( '#planInfoText' ).addClass( 'hidden' );
	if ( $( '#planBoosterPaid, #planBoosterFoc' ).parent().hasClass( 'radio_on' ) ) {
		showPlanBoosterUsage();
	}
}
function planBoosterSpeed(){
	uncheckPlanType( "S" );
	$( '#newPlanType' ).addClass( 'hidden' );
	$( '#newPlanTable' ).addClass( 'hidden' );
	$( '#planTicketTable' ).addClass( 'hidden' );
	$( '#planPaymentTable' ).addClass( 'hidden' );
	$( '#planBoosterType' ).removeClass( 'hidden' );
	$( '#PLAN_BOOSTER_TABLE' ).addClass( 'hidden' );
	$( '#planChargesTable' ).addClass( 'hidden' );
	$( '#speedDropdown' ).addClass( 'hidden' );
	$( '#planInfoText' ).addClass( 'hidden' );
	if ( $( '#planBoosterPaid, #planBoosterFoc' ).parent().hasClass( 'radio_on' ) ) {
		showPlanBoosterSpeed();
	}

}

function planBoosterUsagePaid(){

	if ( $( '#planUsage' ).parent().hasClass( 'radio_on' ) ) {
		showPlanBoosterUsage();
	}
	else if ( $( '#planSpeed' ).parent().hasClass( 'radio_on' ) ) {
		showPlanBoosterSpeed();
	}
	getTeriffMigrationPlan();
}

function showPlanBoosterUsage(){

	$( '#PLAN_BOOSTER_TABLE' ).removeClass( 'hidden' );
	$( '#planTicketTable' ).removeClass( 'hidden' );
	$( '#planInfoText' ).addClass( 'hidden' );
}

function showPlanBoosterSpeed(){
	$( '#speedDropdown' ).removeClass( 'hidden' );
	$( '#planTicketTable' ).removeClass( 'hidden' );
	$( '#planInfoText' ).addClass( 'hidden' );
}

/* ================== 19/11/2014 plan migration end ======================= */

function divSelect( type ){
	document.forms[ 1 ].action = "manageQrc.do?method=getAccessories&requestType=" + type;
	document.forms[ 1 ].submit();

	/*
	 * if(type=='staticIP'){ $('#DIVstaticIP_ADDREMOVEACC').removeClass('hide1'); $('#DIVwiring_ADDREMOVEACC').addClass('hide1'); $('#DIVticketIDRemarksACC').removeClass('hide1'); $('#DIVsaveStaticIP').removeClass('hide1'); $('#DIVsaveWiring').addClass('hide1');
	 * 
	 * }else if(type=='wiring'){ $('#DIVwiring_ADDREMOVEACC').removeClass('hide1'); $('#DIVstaticIP_ADDREMOVEACC').addClass('hide1'); $('#DIVticketIDRemarksACC').removeClass('hide1'); $('#DIVsaveStaticIP').addClass('hide1'); $('#DIVsaveWiring').removeClass('hide1'); }
	 */

}
function saveAccessories( requestType, size ){
	flag = false;
	if ( $( '#IDTicketAccesoriesPage' ).val() == undefined || $( '#IDRemarksAccesoriesPage' ).val().trim() == ''
			|| $( '#IDRemarksAccesoriesPage' ).val().trim().length < 2 || $( '#IDRemarksAccesoriesPage' ).val().trim().length > 4000 ) {
		$( '#IDRemarksAccesoriesPage' ).next( 'font' ).show().html( "Please Provide Remarks between[2-4000]" ).addClass( 'errorTextbox' ).css(
				'width', 300 ).css( 'top', 100 );
		flag = false;
	}
	else if ( requestType == 'staticIP' ) {
		// alert( 'counts' + size );
		if ( !checkStaticPojosDynamic( size ) ) {
			flag = false;
		}
		else {
			flag = true;
		}

	}
	else if ( requestType == 'wiring' ) {
		// condition true wiring point flag true
		if ( $( '#IDWiringPointsADDACC' ).val() == undefined || $( '#IDWiringPointsADDACC' ).val() == '' || $( '#IDWiringPointsADDACC' ).val() == '0' ) {
			flag = false;
			$( '#IDWiringPointsADDACC' ).next( 'font' ).show().html( "Please provide 'wiring charge'" ).addClass( 'errorTextbox' );
		}
		else if ( !checkPaidAmount( $( '#IDWiringPointsADDACC' ).val() ) ) {
			$( '#IDWiringPointsADDACC' ).next( 'font' ).show().html( "Only two digits allowed after decimal point." ).removeClass( 'hidden' )
					.addClass( 'errorTextboxInDiv' );
			flag = false;
		}
		else {
			flag = true;
			$( '#IDWiringPointsADDACC' ).next( 'font' ).hide();
		}
	}
	else if ( requestType == 'staticIPCharges' ) {
		$( '#staticIPChargesRemarks' ).next( 'font' ).addClass( 'hidden' );
		$( '#staticIPChargesAmount' ).next( 'font' ).addClass( 'hidden' );

		if ( $( '#staticIPChargesAmount' ).val() == undefined || $( '#staticIPChargesAmount' ).val() == ''
				|| parseInt( $( '#staticIPChargesAmount' ).val() ) <= 0 ) {
			$( '#staticIPChargesAmount' ).next( 'font' ).show().html( "Please enter a valid 'Amount'" ).removeClass( 'hidden' ).addClass(
					'errorTextbox' );
			flag = false;
		}
		else if ( !checkPaidAmount( $( '#staticIPChargesAmount' ).val() ) ) {
			$( '#staticIPChargesAmount' ).next( 'font' ).show().html( "Only two digits allowed after decimal point." ).removeClass( 'hidden' )
					.addClass( 'errorTextboxInDiv' );
			flag = false;
		}
		else {
			flag = true;
			$( '#staticIPChargesAmount' ).next( 'font' ).hide();
		}
	}
	if ( flag ) {

		var reply = false;
		if ( requestType == 'staticIP' ) {
			reply = confirm( "Do you want to add/edit 'Static IP'?" );
		}
		else if ( requestType == 'staticIPCharges' ) {
			reply = confirm( "Please confirm, If you want to submit Static IP Charges ?" );
		}
		else {

			reply = confirm( "Please confirm, If you want to submit add wiring charge ?'" );
		}
		if ( reply ) {
			$( '.loadingPopup' ).removeClass( 'hidden' );
			document.forms[ 1 ].action = "manageQrc.do?method=addRemoveAccessories&requestType=" + requestType;
			document.forms[ 1 ].submit();
		}
	}
}
function addRowStaticIPADDACC(){
	$( '#formADDACC' ).attr( 'action', 'manageQrc.do?method=addRowStaticIP' );
	$( '#formADDACC' ).submit();
}
function getTeriffMigrationPlan(){
	$( '.loadingPopup' ).removeClass( 'hidden' );
	document.forms[ 1 ].action = "manageQrc.do?method=getCustomerActivationPlan";
	document.forms[ 1 ].submit();
}

function uncheckPlanType( planUsageType ){
	var flag = false;
	if ( planUsageType == 'U' || planUsageType == 'L' ) {
		if ( $( '#unlimitedPaid' ).is( ':checked' ) ) {
			document.getElementById( "unlimitedPaid" ).checked = false;
			flag = true;
		}
		else if ( $( '#unlimitedgFoc' ).is( ':checked' ) ) {
			document.getElementById( "unlimitedgFoc" ).checked = false;
			flag = true;
		}
		else if ( $( '#unlimitedgApp' ).is( ':checked' ) ) {
			document.getElementById( "unlimitedgApp" ).checked = false;
			flag = true;
		}
		else if ( $( '#unlimitedgMbo' ).is( ':checked' ) ) {
			document.getElementById( "unlimitedgMbo" ).checked = false;
			flag = true;
		}
		else if ( $( '#unlimitedgRet' ).is( ':checked' ) ) {
			document.getElementById( "unlimitedgRet" ).checked = false;
			flag = true;
		}
	}
	else if ( planUsageType == 'T' || planUsageType == 'S' ) {

		if ( $( '#planBoosterPaid' ).is( ':checked' ) ) {
			document.getElementById( "planBoosterPaid" ).checked = false;
			flag = true;
		}
		else if ( $( '#planBoosterFoc' ).is( ':checked' ) ) {
			document.getElementById( "planBoosterFoc" ).checked = false;
			flag = true;
		}
	}

	if ( flag ) {
	}
}
function removeRowStaticIPADDACC( index ){

	$( '#formADDACC' ).attr( 'action', 'manageQrc.do?method=removeRowStaticIP&index=' + index );
	$( '#formADDACC' ).submit();
}
function divSelectAddAccessories( type ){
	document.forms[ 1 ].action = "manageQrc.do?method=getAccessories&requestType=" + type;
	document.forms[ 1 ].submit();
}

function calculateBooster( selectedQuotaCount, index ){
	if ( !isNaN( selectedQuotaCount ) ) {
		var result = 0.0;
		var selectedPlanQuota = document.getElementById( 'boost' + index ).value;
		result = selectedPlanQuota * selectedQuotaCount;
		if ( !isNaN( result ) ) {
			document.getElementById( 'totalDul' + index ).innerHTML = parseFloat( result / 1024 / 1024 / 1024 / 1024 ).toFixed( 2 ) + " GB";
		}
		var unitPrice = document.getElementById( 'unitPrice' + index ).value;
		result = selectedQuotaCount * unitPrice;
		if ( !isNaN( result ) ) {
			document.getElementById( 'totalPrice' + index ).innerHTML = parseFloat( result ).toFixed( 2 );
		}
		newPlanInfoBoosterUsage();
	}
}
function checkStaticPojosDynamic( size ){
	flag = true;
	id = 'crmCustAssetDetailsPojos[';
	staticIP = '].categoryValue';
	// amount = '].categoryAmount';

	// console.log("[name='"+id+1+staticIP+"']");
	for ( var i = 0; i < size; i++ ) {
		// alert(id+i+staticIP);
		// alert($("[name='"+id+i+staticIP+"']").val());
		// var amt = $( "[name='" + id + i + amount + "']" ).val();
		$( "[name='" + id + i + staticIP + "']" ).next( 'font' ).hide();
		// $( "[name='" + id + i + amount + "']" ).next( 'font' ).hide();

		if ( $( "[name='" + id + i + staticIP + "']" ).val() == '' ) {
			// alert(i);
			$( "[name='" + id + i + staticIP + "']" ).next( 'font' ).show().html( "Please enter 'Static IP'" ).addClass( 'errorTextbox' ).css( 'top',
					45 ).css( 'width', 220 ).css( 'left', 42 );
			flag = false;
		}
		if ( !validateIP( $( "[name='" + id + i + staticIP + "']" ).val() ) ) {
			$( "[name='" + id + i + staticIP + "']" ).next( 'font' ).show().html( "Please enter  valid 'Static IP'" ).addClass( 'errorTextbox' ).css(
					'top', 45 ).css( 'width', 220 ).css( 'left', 42 );
			flag = false;
		}

		/*
		 * if ( $( "[name='" + id + i + amount + "']" ).val() == '' || $( "[name='" + id + i + amount + "']" ).val() == '0' ) { // alert(i); $( "[name='" + id + i + amount + "']" ).next( 'font' ).show().html( "Please enter 'Amount'" ).addClass( 'errorTextbox' ).css( 'top', 45 ) .css( 'width', 220
		 * ).css( 'left', 341 ); flag = false; } if ( !checkPaidAmount( amt ) ) { $( "[name='" + id + i + amount + "']" ).next( 'font' ).show().html( "Please enter valid 'Amount'" ).addClass( 'errorTextbox' ).css( 'top', 45 ).css( 'width', 220 ).css( 'left', 341 ); flag = false; }
		 */
	}
	return flag;
}

function qrcActionSelection( functionBinList, rcaLsit, inParam ){

	$( '#selection' ).next( 'font' ).hide();
	$( '#remarks' ).next( 'font' ).hide();
	$( '#followUpDate' ).next( 'font' ).hide();
	$( '#functionBinList' ).next( 'font' );
	$( '#rcaLsit' ).next( 'font' ).hide();
	$( '#rcaReasonList' ).next( 'font' ).hide();

	if ( inParam == 'FU' ) {
		$( '#followUpAction' ).removeClass( 'hide1' );
		$( '#forwardAction' ).addClass( 'hide1' );
		$( '#resolveAction' ).addClass( 'hide1' );
		$( '#remarksSection' ).addClass( 'hide1' );

	}
	else if ( inParam == 'FW' ) {
		$( '#forwardAction' ).removeClass( 'hide1' );
		$( '#resolveAction' ).addClass( 'hide1' );
		$( '#followUpAction' ).addClass( 'hide1' );
		$( '#remarksSection' ).addClass( 'hide1' );
		populateFunctionalBin( functionBinList );

	}
	else if ( inParam == 'RV' ) {
		$( '#resolveAction' ).removeClass( 'hide1' );
		$( '#forwardAction' ).addClass( 'hide1' );
		$( '#followUpAction' ).addClass( 'hide1' );
		$( '#remarksSection' ).addClass( 'hide1' );
		populateRca( rcaLsit );
		// refreshValue( 'editTicket', 'ticket' );
		// alert("working");
		// refreshValueEditTickets(rcaLsit);

	}
	else if(inParam == 'FR' ){
		$( '#resolveAction' ).addClass( 'hide1' );
		$( '#forwardAction' ).addClass( 'hide1' );
		$( '#followUpAction' ).addClass( 'hide1' );
		$( '#remarksSection' ).removeClass( 'hide1' );
	}
	else {
		$( '#resolveAction' ).addClass( 'hide1' );
		$( '#forwardAction' ).addClass( 'hide1' );
		$( '#followUpAction' ).addClass( 'hide1' );
		$( '#remarksSection' ).removeClass( 'hide1' );
	}
	if ( inParam == 'RV' ) {
		$( '#falseValueWT' ).attr( 'checked', true );
	}
	else {
		$( '#falseValueWT' ).attr( 'checked', false );
	}
}

function editServiceRequest(){
	if ( checkRequiredConditions() ) {
		var reply = confirm( "Do you want to modify this ticket ?" );
		if ( reply ) {
			$( '.loadingPopup' ).removeClass( 'hidden' );
			document.forms[ 1 ].action = "manageQrc.do?method=editServiceRequest";
			document.forms[ 1 ].submit();
		}
	}
}

function checkRequiredConditions(){
	var response = true;
	var action = document.getElementById( 'selection' ).value;
	var remarks = document.getElementById( 'remarks' ).value;
	switch ( action ) {
		case '0':
			$( '#selection' ).next( 'font' ).show().html( "Please select Action" ).addClass( 'errorTextbox' );
			response = false;
			break;
		case 'SR':
			if ( remarks.trim() == '' || remarks == '0' || remarks == null || remarks == undefined ) {
				$( '#remarks' ).next( 'font' ).show().html( "Please enter Remarks" ).addClass( 'errorRemarks' );
				response = false;
			}
			else if ( remarks.trim().length < 2 || remarks.trim().length > 4000 ) {
				$( '#remarks' ).next( 'font' ).show().html( "Please enter Remarks between [2-4000]." ).addClass( 'errorRemarks' );
				response = false;
			}
			break;
		case 'FR':
			if ( remarks.trim() == '' || remarks == '0' || remarks == null || remarks == undefined ) {
				$( '#remarks' ).next( 'font' ).show().html( "Please enter Remarks" ).addClass( 'errorRemarks' );
				response = false;
			}
			else if ( remarks.trim().length < 2 || remarks.trim().length > 4000 ) {
				$( '#remarks' ).next( 'font' ).show().html( "Please enter Remarks between [2-4000]." ).addClass( 'errorRemarks' );
				response = false;
			}
			break;
		case 'FU':
			var followUpDate = document.getElementById( 'followUpDate' ).value;
			if ( remarks.trim() == '' || remarks == '0' || remarks == null || remarks == undefined ) {
				$( '#remarks' ).next( 'font' ).show().html( "Please enter Remarks" ).addClass( 'errorRemarks' );
				response = false;
			}
			else if ( remarks.trim().length < 2 || remarks.trim().length > 4000 ) {
				$( '#remarks' ).next( 'font' ).show().html( "Please enter Remarks between [2-4000]." ).addClass( 'errorRemarks' );
				response = false;
			}
			if ( followUpDate == '' || followUpDate == '0' || followUpDate == null || followUpDate == undefined ) {
				$( '#followUpDate' ).next( 'font' ).show().html( "Please provide Follow Up Date" ).addClass( 'errorTextbox' );
				response = false;
			}
			else if ( !validateFollowUpDate( followUpDate ) ) {

				$( '#followUpDate' ).next( 'font' ).show().html( "Please provide current/future Follow Up Date" ).addClass( 'errorTextbox' );
				response = false;
			}
			else {
				$( '#followUpDate' ).next( 'font' ).hide();
			}
			var date, hh, mm, ss;
			date = $( '#followUpDate' ).val();
			hh = $( '#strHourID' ).val();
			mm = $( '#strMinuteID' ).val();
			ss = $( '#strSecondID' ).val();
			var strDateTime = date + " " + hh + ":" + mm + ":" + ss;
			$( '#hiddenfollowUpDate' ).val( strDateTime );
			break;
		case 'FW':
			var functionBin = document.getElementById( 'functionBinList' ).value;
			if ( remarks.trim() == '' || remarks == '0' || remarks == null || remarks == undefined ) {
				$( '#remarks' ).next( 'font' ).show().html( "Please enter Remarks" ).addClass( 'errorRemarks' );
				response = false;
			}
			else if ( remarks.trim().length < 2 || remarks.trim().length > 4000 ) {
				$( '#remarks' ).next( 'font' ).show().html( "Please enter Remarks between [2-4000]." ).addClass( 'errorRemarks' );
				response = false;
			}
			if ( functionBin == '' || functionBin == '0' || functionBin == null || functionBin == undefined ) {
				$( '#functionBinList' ).next( 'font' ).show().html( "Please select Functional Bin" ).addClass( 'errorTextbox' );
				response = false;
			}
			break;
		case 'RV':
			var rca = document.getElementById( 'rcaLsit' ).value;
			var reason = document.getElementById( 'rcaReasonList' ).value;
			if ( remarks.trim() == '' || remarks == '0' || remarks == null || remarks == undefined ) {
				$( '#remarks' ).next( 'font' ).show().html( "Please enter Remarks" ).addClass( 'errorRemarks' );
				response = false;
			}
			else if ( remarks.trim().length < 2 || remarks.trim().length > 4000 ) {
				$( '#remarks' ).next( 'font' ).show().html( "Please enter Remarks between [2-4000]." ).addClass( 'errorRemarks' );
				response = false;
			}
			if ( rca == '' || rca == '0' || rca == null || rca == undefined ) {
				$( '#rcaLsit' ).next( 'font' ).show().html( "Please enter RCA" ).addClass( 'errorTextbox' );
				response = false;
			}
			else {
				$( '#rcaLsit' ).next( 'font' ).hide().html( "Please enter RCA" ).removeClass( 'errorTextbox' );
			}

			if ( reason == '' || reason == '0' || reason == null || reason == undefined ) {
				$( '#rcaReasonList' ).next( 'font' ).show().html( "Please enter Reason" ).addClass( 'errorTextbox' );
				response = false;
			}
			else {
				$( '#rcaReasonList' ).next( 'font' ).hide().html( "Please enter Reason" ).removeClass( 'errorTextbox' );
			}
			break;
	}
	return response;
}

function populateRca( rcaLsit ){
	var product = document.getElementById( 'product' ).value;
	var qrcCategoryId = document.getElementById( 'qrcCategoryId' ).value;
	crmDwr.getRca( product, qrcCategoryId, function( list ){
		subCats = [];
		var cat;
		if ( list != null ) {
			list.forEach( function( e ){
				cat = {}
				cat[ 'label' ] = e.contentName;
				cat[ 'value' ] = e.contentValue;

				subCats.push( cat );

			} );

		}
		$( '#rcaLsit' ).autocomplete( {
			source : subCats, minLength : 0, change : function( e, ui ){
				resetEditTicketAutocomplete( false, true );
				$( '#qrcCategoryId' ).val( '' );
				$( '#actionTakenId' ).val( '' );
				$( '#rootCauseId' ).val( '' );
				if ( !ui.item ) {
					$( e.target ).val( '' );
				}
				else {
					$( '#actionTakenId' ).val( ui.item.value );
					fillRcaReason( "rcaReasonList", ui.item.value );

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

function fillRcaReason( inDestinationID, inRcaId ){
	if ( inRcaId != 0 ) {
		/*
		 * crmDwr.getRcaReason( inRcaId, function( list ){ addRcaReason( inDestinationID, list ); } );
		 */

		crmDwr.getRcaReason( inRcaId, function( rootCauseList ){
			rootCauses = [];
			if ( rootCauseList != null ) {
				var rootCause;
				rootCauseList.forEach( function( e ){
					// alert(e.contentName);
					rootCause = {};
					rootCause[ 'label' ] = e.contentName;
					rootCause[ 'value' ] = e.contentValue;
					rootCauses.push( rootCause );
				} );

			}
			$( '#rcaReasonList' ).autocomplete( {
				source : rootCauses, minLength : 0, change : function( e, ui ){
					$( '#rootCauseId' ).val( '' );
					if ( !ui.item ) {
						$( e.target ).val( '' );
					}
					else {
						$( '#rootCauseId' ).val( ui.item.value );
					}
				}, select : function( e, ui ){
					e.preventDefault();
					$( e.target ).val( ui.item.label );
				}, focus : function( e, ui ){
					return false;
				}
			} );
		} );

		/*
		 * function addRcaReason( id, list ){ var select = document.getElementById( id ); if ( select != null ) { if ( list != null ) { dwr.util.removeAllOptions( id ); dwr.util.addOptions( id, [ { value : '0', name : 'Please Select' } ], 'value', 'name' ); dwr.util.addOptions( id, list,
		 * "contentValue", "contentName" ); // $('#'+id).removeAttr('disabled'); } else { alert( "Resolution Code List is empty." ); removeList( id ); } } }
		 */
	}
	else {
		dwr.util.removeAllOptions( inDestinationID );
		dwr.util.addOptions( inDestinationID, [
			{
				value : '0', name : 'Please Select'
			}
		], 'value', 'name' );
		return false;
	}
}
function validateDate( date, ids ){
	var isReceivedValidData = true;
	if ( ids != "" ) {
		var daysdiff = dateDiff( new Date(), getISODate( date ) );
		$( '#' + ids.id ).next( 'font' ).hide();
		if ( daysdiff > 0 ) {
			isReceivedValidData = false;
			$( '#' + ids.id ).next( 'font' ).show().html( "Future date not allowed." ).addClass( 'errorTextbox' ).css( 'top', 55 );
		}
	}
	else {
		var daysdiff = dateDiff( new Date(), getISODate( date ) );
		if ( daysdiff < 0 ) {
			isReceivedValidData = false;
		}
	}
	return isReceivedValidData;
}

function validateFollowUpDate( value ){
	var date, hh, mm, ss;
	var followUpDate = null;
	date = $( '#followUpDate' ).val();
	hh = $( '#strHourID' ).val();
	mm = $( '#strMinuteID' ).val();
	ss = $( '#strSecondID' ).val();
	followUpDate = getISODate( date );
	followUpDate.setHours( hh, mm, ss, 0 );
	var dayDiff = new Date() > followUpDate;
	if ( dayDiff )
		return false;
	return true;
}
function populateFunctionalBin( functionBinList ){
	var subSubCategoryId = document.getElementById( 'qrcSubSubCategoryId' ).value;
	var functionalBinId = document.getElementById( 'functionalBinId' ).value;
	crmDwr.getFunctionalBinbyId( subSubCategoryId, functionalBinId, function( list ){
		addFunctionalBin( functionBinList, list );
	} );

	function addFunctionalBin( id, list ){
		var select = document.getElementById( id );
		if ( select != null ) {
			if ( list != null ) {
				dwr.util.removeAllOptions( id );
				dwr.util.addOptions( id, [
					{
						value : '0', name : 'Please Select '
					}
				], 'value', 'name' );
				dwr.util.addOptions( id, list, "contentValue", "contentName" );
				// $('#'+id).removeAttr('disabled');
			}
			else {
				alert( "Functional Bin List is empty." );
				removeList( id );
			}
		}
	}
}

function showInstallationPartner( serviceList, inPartnerId, inProduct ){
	if ( inPartnerId != "" && inProduct != "" ) {
		$( '.loadingPopup' ).removeClass( 'hidden' );
		crmDwr.getServicePartner( inPartnerId, inProduct, function( list ){
			addInstallationPartner( serviceList, list );
		} );

		function addInstallationPartner( id, list ){
			var select = document.getElementById( id );
			if ( select != null ) {
				if ( list != null ) {
					dwr.util.removeAllOptions( id );
					dwr.util.addOptions( id, [
						{
							value : '0', name : 'Please Select '
						}
					], 'value', 'name' );
					dwr.util.addOptions( id, list, "contentValue", "contentName" );
				}
				else {

					alert( "Installation Partner list is empty." );
					removeList( id );
					dwr.util.addOptions( id, [
						{
							value : '0', name : 'Please Select '
						}
					], 'value', 'name' );
				}
			}
			$( '.loadingPopup' ).addClass( 'hidden' );
		}
	}
}
function searchCustomerTicket(){
	if ( true ) {
		var isReceivedValidData = true;
		var startDateValue = document.getElementById( 'startDate' ).value;

		var endDateValue = document.getElementById( "endDate" ).value;
		if ( ( startDateValue != "" ) ) {
			isReceivedValidData = validateDate( startDateValue, startDate );
		}
		if ( endDateValue != "" ) {
			isReceivedValidData = validateDate( endDateValue, endDate );
		}
		if ( dateDiff( getISODate( startDateValue ), getISODate( endDateValue ) ) == 0 ) {
			isReceivedValidData = validateDate( startDateValue, startDate );
			isReceivedValidData = validateDate( endDateValue, endDate );
		}
		else if ( startDateValue != "" && endDateValue != "" && dateDiff( getISODate( startDateValue ), getISODate( endDateValue ) ) < 1 ) {
			$( '#' + startDate.id ).next( 'font' ).show().html( "Start Date must be less than End Date." ).addClass( 'errorTextbox' ).css( 'top', 55 );
			isReceivedValidData = false;
		}
		if ( isReceivedValidData == true ) {
			$( '.loadingPopup' ).removeClass( 'hidden' );
			document.forms[ 1 ].action = "manageQrc.do?method=searchTicket";
			document.forms[ 1 ].submit();
		}
	}
}
function viewServiceRequestPage( inSR_No, inCustomerID ){
	if ( inSR_No != "" && inCustomerID != "" ) {
		document.forms[ 1 ].action = "manageQrc.do?method=viewServiceRequestPage&qrcSR_No=" + inSR_No + "&customerID=" + inCustomerID;
		document.forms[ 1 ].submit();
	}
}
var addonPlanCodeGlobal = null;
function setPlanCode( e, planCode, quota ){
	$( '#adviceDivId' ).addClass( 'hidden' );
	document.getElementById( 'addonPlanId' ).value = planCode;
	var planCategory = document.getElementById( 'planCategoryId' ).value;
	var $option = $( e ).find( ':selected' );
	var quota = 0;
	if ( $option.val() != '' ) {
		$( '#planInfoText' ).removeClass( 'hidden' );
		quota = parseFloat( $( e ).data( 'quota' ) ) + parseFloat( $option.data( 'quota' ) );

		setPlanInfo( $( e ).data( 'plan' ), $( e ).data( 'primary' ), quota, $( e ).data( 'secondary' ), $( e ).data( 'usagetype' ), quota );

		$( 'ul.planInfo span.addonPlanQuota' ).text( parseFloat( $option.data( 'quota' ) ).toString() + " GB" );
		$( 'ul.planInfo span.totalQuota' ).text( quota.toString() + " GB" );
	}
	else {
		$( 'ul.planInfo span.addonPlanQuota' ).text( '' );
		$( 'ul.planInfo span.totalQuota' ).text( '' );
		$( '#planInfoText' ).addClass( 'hidden' );

		dwr.util.removeAllOptions( "activationPopUpId" );
		dwr.util.addOptions( "activationPopUpId", [
				{
					key : '', value : "Please select"
				},
		], 'key', 'value' );
	}
}

var pName, pSpeed, pQuota, pSecSpeed;
function setPlanInfo( planName, primarySpeed, quota, secondarySpeed, usageType, oldQuota ){
	var serviceType = $( '#serviceTypeId' ).val();
	var finalName;
	if ( planName != null ) {
		pName = planName;
		pSpeed = primarySpeed;
		pQuota = quota;
		if ( secondarySpeed < 1024 ) {
			pSecSpeed = secondarySpeed + " Kbps";
		}
		else {
			pSecSpeed = secondarySpeed / 1024 + " Mbps";
		}
		$( '#adviceDivId' ).addClass( 'hidden' );
		if ( usageType == 'U' ) {
			// if ( quota < 0 ) {
			// finalName = planName + ";<br>" + parseFloat( primarySpeed ).toString() + " Mbps Unlimited";
			// }
			// else {
			// finalName = planName + ";<br>" + parseFloat( primarySpeed ).toString() + " Mbps upto <font class='bold'>"
			// + parseFloat( parseFloat( quota ).toFixed( 2 ) ).toString() + " GB;</font> <br>" + secondarySpeed + " Kbps thereafter";
			// }
			finalName = planName + ";<br>"
			if ( quota == 0 || quota == 9999) {
				finalName += "Unlimited at " + parseFloat( primarySpeed ).toString() + " Mbps";
			}
			else {
				finalName += parseFloat( parseFloat( quota ).toFixed( 2 ) ).toString() + " GB at " + parseFloat( primarySpeed ).toString()
						+ " Mbps, and<br>Unlimited at " + pSecSpeed;
			}
		}
		else {
			finalName = planName + ";<br>" + parseFloat( parseFloat( quota ).toFixed( 2 ) ).toString() + " GB at "
					+ parseFloat( primarySpeed ).toString() + " Mbps";
			if ( serviceType == "PR" ) {
				// finalName += ", and<br>"+$( '#postusage' ).val();
			}
		}
		$( '#planInfoID' ).html( finalName );
		var planCategory = document.getElementById( 'planCategoryId' ).value;
		if ( planCategory == "Base plan migration" || planCategory == "Plan Renewal" || planCategory == "Plan Reactivation" ) {
			var oldBasePlanCode = document.getElementById( 'oldBasePlanCodeId' ).value;
			var newBasePlanCode = $( '[name=selectedPlanCode] option:selected' ).val();
			if ( planCategory != "Base plan migration" || oldBasePlanCode != newBasePlanCode ) {
				var oldAddonPlanCode = document.getElementById( 'oldAddonPlanCodeId' ).value;

				var newAddonPlanCode = addonPlanCodeGlobal;
				var serviceType = document.getElementById( 'serviceTypeId' ).value;
				var customerId = document.getElementById( 'customerId' ).value;
				if ( planCategory == "Base plan migration" ) {
					if ( quota < 0 ) {
						dwr.util.removeAllOptions( "activationPopUpId" );
						dwr.util.addOptions( "activationPopUpId", [
								{
									key : '', value : "Please select"
								}, {
									key : 'null', value : "Next Billing Cycle"
								}
						], 'key', 'value' );
					}
					else {
						if ( oldQuota < 0 ) {
							dwr.util.removeAllOptions( "activationPopUpId" );
							dwr.util.addOptions( "activationPopUpId", [
									{
										key : '', value : "Please select"
									}, {
										key : 'null', value : "Next Billing Cycle"
									}
							], 'key', 'value' );
						}
						else {
							planMigrationPolicy( planCategory, oldBasePlanCode, newBasePlanCode, oldAddonPlanCode, newAddonPlanCode, serviceType,
									customerId );
						}
					}
				}
			}
			// else {
			// dwr.util.removeAllOptions( "activationPopUpId" );
			// dwr.util.addOptions( "activationPopUpId", [
			// {
			// key : '', value : "Please select"
			// }
			// ], 'key', 'value' );
			// }
		}
		else if ( planCategory == 'Addon Plans' ) {
			var planCode = $( '#newAddonPlanCodeID' ).val();
			var oldAddonPlanCode = document.getElementById( 'oldAddonPlanCodeId' ).value;
			if ( oldAddonPlanCode != planCode ) {
				var oldBasePlanCode = document.getElementById( 'oldBasePlanCodeId' ).value;
				var newBasePlanCode = $( 'input[name=selectedPlanCode]:checked' ).val();
				var serviceType = document.getElementById( 'serviceTypeId' ).value;
				var customerId = document.getElementById( 'customerId' ).value;
				if ( $( '#addonAllowedId' ).val() == 'false' ) {
					planMigrationPolicy( planCategory, oldBasePlanCode, newBasePlanCode, oldAddonPlanCode, planCode, serviceType, customerId );
				}
			}
			else {
				dwr.util.removeAllOptions( "activationPopUpId" );
				dwr.util.addOptions( "activationPopUpId", [
					{
						key : '', value : "Please select"
					}
				], 'key', 'value' );
			}
		}
		else {
			dwr.util.removeAllOptions( "activationPopUpId" );
			dwr.util.addOptions( "activationPopUpId", [
					{
						key : '', value : "Please select"
					}, {
						key : 'Immediate', value : "Immediate"
					}
			], 'key', 'value' );
		}
	}
	// else if ( planName == null && primarySpeed == null && secondarySpeed == null ) {
	// finalName = pName + ";<br>" + parseFloat( pSpeed ).toString() + " Mbps upto " + ( parseInt( quota ) + parseInt( pQuota ) ) + " GB; <br>"
	// + pSecSpeed + " Kbps thereafter";
	// $( '#planInfoID' ).html( finalName );
	// }
}

function setSelectedPlanInfo( select ){
	var $option = $( select ).find( ':selected' );
	var quota = 0;
	var baseQuota = 0;
	if ( $option.val() != '' ) {
		baseQuota = $option.data( 'quota' );
		var baseQuotaStr = baseQuota.toString();
		if ( ~baseQuotaStr.search( ',' ) ) {
			baseQuota = parseFloat( baseQuota.replace( ",", "" ) );
		}
		else {
			baseQuota = parseFloat( baseQuota );
		}
		if ( $option.data( 'addonyn' ) == 'Y' ) {
			$( '#newBasePlanCodeID' ).parent().next( 'font' ).addClass( 'hidden' );
			quota = baseQuota + parseFloat( $( '#addonPlanQuota' ).val() );
			$( '#addonTypeText' ).removeClass( 'hidden' );
		}
		else {
			$( '#addonTypeText' ).addClass( 'hidden' );
			$( '#addonAction' ).val( 'WOA' );
			$( '#newBasePlanCodeID' ).parent().next( 'font' ).removeClass( 'hidden' ).text(
					'Addon Plan not allowed for selected plan. Existing addon will be removed if any.' );
			quota = baseQuota;
		}

		setPlanInfo( $option.data( 'name' ), $option.data( 'primary' ), baseQuota < 0 ? baseQuota : quota, $option.data( 'secondary' ), $option
				.data( 'usagetype' ), $( select ).data( 'quota' ) );

		if ( baseQuota < 0 || baseQuota == 0 || baseQuota == 9999) {
			$( 'ul.planInfo span.basePlanQuota' ).text( "Unlimited" );
			$( 'ul.planInfo span.totalQuota' ).text( "Unlimited" );
		}
		else {
			$( 'ul.planInfo span.basePlanQuota' ).text( baseQuota.toString() + " GB" );
			$( 'ul.planInfo span.totalQuota' ).text( quota.toString() + " GB" );
		}
		$( '#planInfoText' ).removeClass( 'hidden' );
	}
	else {
		$( '#newBasePlanCodeID' ).parent().next( 'font' ).addClass( 'hidden' );
		$( 'ul.planInfo span.basePlanQuota' ).text( '' );
		$( 'ul.planInfo span.totalQuota' ).text( '' );
		$( '#planInfoText' ).addClass( 'hidden' );
	}
}

function newPlanInfoBoosterUsage(){
	var quota = 0;
	if ( $( '.chkBooster:checked' ).length ) {
		$( '#planInfoText' ).removeClass( 'hidden' );
		$( '.chkBooster:checked' ).each( function( i, e ){
			var row = $( e ).parent( 'div' );
			var index = row.data( 'index' );
			var plan = row.data( 'plan' );
			var primary = row.data( 'primary' );
			var secondary = row.data( 'secondary' );
			var base = parseFloat( row.data( 'quota' ) );
			var usagetype = row.data( 'usagetype' );
			quota += parseInt( $( '#boost' + index ).val() ) * parseInt( $( '#enterQuantity' + index ).val() );
			quota = quota / 1024 / 1024 / 1024 / 1024;
			setPlanInfo( plan, primary, base < 0 ? base : quota + base, secondary, usagetype, null );
		} );
	}
	else {
		$( '#planInfoText' ).addClass( 'hidden' );
	}
}

function newPlanInfoBoosterSpeed( e ){
	$option = $( e ).find( ':selected' );
	if ( $option.val() ) {
		$( '#planInfoText' ).removeClass( 'hidden' );
		var planName = $option.parents( 'span' ).data( 'plan' );
		var primarySpeed = parseFloat( $option.data( 'speed' ) ) / 1024;
		var quota = $option.parents( 'span' ).data( 'quota' );
		var secondarySpeed = $option.parents( 'span' ).data( 'secondary' );
		var usageType = $option.parents( 'span' ).data( 'usagetype' );
		setPlanInfo( planName, primarySpeed, quota, secondarySpeed, usageType, null );
		// console.log( $option.parents( 'span' ).data( 'plan' ) );
	}
	else {
		$( '#planInfoText' ).addClass( 'hidden' );
	}
}

function activeDeactivateRow( inIndex ){
	var textBoxID = "enterQuantity" + inIndex;
	var rowID = "rowID" + inIndex;
	var textBoxIDElement = document.getElementById( textBoxID );
	var rowIDElement = document.getElementById( rowID );
	if ( rowIDElement.checked ) {
		textBoxIDElement.readOnly = false;
		$( textBoxIDElement ).removeClass( 'gray_text' );
		$( textBoxID ).triggerHandler( 'blur' );
	}
	else {
		textBoxIDElement.readOnly = true;
		$( textBoxIDElement ).addClass( 'gray_text' );
	}

}
function planMigrationPolicy( planCategory, oldBasePlanCode, newBasePlanCode, oldAddonPlanCode, newAddonPlanCode, serviceType, customerId ){
	if ( planCategory == "Base plan migration" || planCategory == "Plan Renewal" || planCategory == "Plan Reactivation" ) {
		crmDwr.getBasePlanMigrationPolicy( oldBasePlanCode, newBasePlanCode, oldAddonPlanCode, serviceType, customerId, function( list ){
			addPolicyResult( "activationPopUpId", list );
		} );
	}
	else if ( planCategory == 'Addon Plans' ) {
		crmDwr.getAddonPlanMigrationPolicy( oldBasePlanCode, oldAddonPlanCode, newAddonPlanCode, serviceType, customerId, function( list ){
			addPolicyResult( "activationPopUpId", list );
		} );
	}
	function addPolicyResult( id, list ){
		var select = document.getElementById( id );
		if ( select != null ) {
			if ( list != null ) {
				dwr.util.removeAllOptions( id );
				dwr.util.addOptions( id, [
					{
						key : '', value : "Please select"
					}
				], 'key', 'value' );
				dwr.util.addOptions( id, list, "contentValue", "contentName" );
			}
			else {
				dwr.util.removeAllOptions( id );
				dwr.util.addOptions( id, [
					{
						key : '', value : "Please select"
					}
				], 'key', 'value' );
			}
		}
	}
}
function setPlanAdvice( advice ){
	if ( advice != 'null' && advice != '' && advice != 'Immediate' ) {
		$( '#adviceDivId' ).removeClass( 'hidden' );
		$( '#adviceId' ).html( advice );
	}
	else {
		$( '#adviceDivId' ).addClass( 'hidden' );
	}

}
// --------------------------- Plan Migration Page ------------------------------------

function planMigration( element ){
	$( '[name="planUsageType"]' ).parent().removeClass( 'radio_on' );
	$( '#planCategoryId' ).val( "Base plan migration" );
	$( '#VAS_ACTIVATION_DEACTIVATION_DIV' ).addClass( 'hidden' );
	$( '#VAS_PAID_FOC_DIV' ).addClass( 'hidden' );
	$( '#VAS_ACITIVATION_DATA_DIV' ).addClass( 'hidden' );
	$( '#VAS_DEACITIVATION_DATA_DIV' ).addClass( 'hidden' );
	$( '#planLimitType' ).removeClass( 'hidden' );
	$( '#newPlanTable' ).addClass( 'hidden' );
	$( '#newPlanType' ).addClass( 'hidden' );
	$( '#planTicketTable' ).addClass( 'hidden' );
	$( '#planPaymentTable' ).addClass( 'hidden' );
	$( '#planBoosters' ).addClass( 'hidden' );
	$( '#planBoosterType' ).addClass( 'hidden' );
	$( '#PLAN_BOOSTER_TABLE' ).addClass( 'hidden' );
	$( '#planChargesTable' ).addClass( 'hidden' );
	$( '#speedDropdown' ).addClass( 'hidden' );
	$( '#planInfoText' ).addClass( 'hidden' );
	$( element ).addClass( 'active' );
	$( element ).parent().siblings().children().removeClass( 'active' );
	$( '#PLAN_BOOSTER_USAGE_SPEED_DIV' ).addClass( 'hidden' );
	$( '#PLAN_BOOSTER_PAID_FOC_DIV' ).addClass( 'hidden' );
	$( '#PLAN_BOOSTER_TABLE' ).addClass( 'hidden' );
	$( '#PLAN_BOOSTER_USAGE_DATA' ).addClass( 'hidden' );
	$( '#PLAN_BOOSTER_SPEED_DATA' ).addClass( 'hidden' );
	$( '#PLAN_BOOSTER_PAID_FOC_DIV :input' ).attr( 'disabled', true );
	$( '#PLAN_BOOSTER_USAGE_SPEED_DIV :input' ).attr( 'disabled', true );
	$( '#planLimitType :input' ).removeAttr( 'disabled' );
	$( '#newPlanType :input' ).removeAttr( 'disabled' );
	$( '#VAS_ACTIVATION_DEACTIVATION_DIV :input' ).attr( 'disabled', true );
	$( '#VAS_PAID_FOC_DIV :input' ).attr( 'disabled', true );
	$( '#addonPlanOptions' ).addClass( 'hidden' );
	$( '#addonPlanOptions :input' ).attr( 'disabled', true );
	$( '#ADDON_PAID_FOC_DIV' ).addClass( 'hidden' );
	$( '#ADDON_PAID_FOC_DIV :input' ).attr( 'disabled', true );
	$( '#addonRemoveRemarks' ).addClass( 'hidden' );
	$( '#addonRemoveRemarks textarea' ).attr( 'disabled', true );
}

function addonPlans( element ){
	$( '[name="planUsageType"]' ).parent().removeClass( 'radio_on' );
	$( '#planCategoryId' ).val( 'Addon Plans' );
	$( '#PLAN_BOOSTER_PAID_FOC_DIV' ).addClass( 'hidden' );
	$( '#planLimitType' ).addClass( 'hidden' );
	$( '#newPlanTable' ).addClass( 'hidden' );
	$( '#newPlanType' ).addClass( 'hidden' );
	$( '#planTicketTable' ).addClass( 'hidden' );
	$( '#planPaymentTable' ).addClass( 'hidden' );
	$( '#planBoosterType' ).addClass( 'hidden' );
	$( '#PLAN_BOOSTER_TABLE' ).addClass( 'hidden' );
	$( '#planChargesTable' ).addClass( 'hidden' );
	$( '#speedDropdown' ).addClass( 'hidden' );
	$( element ).addClass( 'active' );
	$( element ).parent().siblings().children().removeClass( 'active' );
	$( '#PLAN_BOOSTER_USAGE_DATA' ).addClass( 'hidden' );
	$( '#PLAN_BOOSTER_SPEED_DATA' ).addClass( 'hidden' );
	$( '#planInfoText' ).addClass( 'hidden' );
	$( '#VAS_ACTIVATION_DEACTIVATION_DIV' ).addClass( 'hidden' );
	$( '#VAS_PAID_FOC_DIV' ).addClass( 'hidden' );
	$( '#VAS_ACITIVATION_DATA_DIV' ).addClass( 'hidden' );
	$( '#VAS_DEACITIVATION_DATA_DIV' ).addClass( 'hidden' );
	$( '#newPlanType :input' ).attr( 'disabled', true );
	$( '#planLimitType :input' ).attr( 'disabled', true );
	$( '#VAS_ACTIVATION_DEACTIVATION_DIV :input' ).attr( 'disabled', true );
	$( '#VAS_PAID_FOC_DIV :input' ).attr( 'disabled', true );
	$( '#PLAN_BOOSTER_USAGE_SPEED_DIV' ).addClass( 'hidden' );
	$( '#PLAN_BOOSTER_USAGE_SPEED_DIV :input' ).attr( 'disabled', true );
	$( '#addonPlanOptions' ).removeClass( 'hidden' );
	$( '#addonPlanOptions :input' ).removeAttr( 'disabled' );
	$( '#ADDON_PAID_FOC_DIV' ).addClass( 'hidden' );
	$( '#ADDON_PAID_FOC_DIV :input' ).removeAttr( 'disabled' );
	disableAddonPlan();
}
function disableAddonPlan(){
	if ( $( '#addonPlanHiddenID' ).val() == "" ) {
		$( "#addonPlanRemove" ).attr( 'disabled', true );
		$( "#add_opacity" ).css( 'opacity', '0.2' );
	}
	else {
		$( "#add_opacity" ).css( 'opacity', '1' );
	}
}
function planBooster( element ){
	$( '[name="planUsageType"]' ).parent().removeClass( 'radio_on' );
	$( '[name="planUsageType"]' ).prop( 'checked', false );
	$( '#planCategoryId' ).val( "Booster plan" );
	$( '#PLAN_BOOSTER_USAGE_SPEED_DIV' ).removeClass( 'hidden' );
	$( '#PLAN_BOOSTER_PAID_FOC_DIV' ).addClass( 'hidden' );
	$( '#planLimitType' ).addClass( 'hidden' );
	$( '#newPlanTable' ).addClass( 'hidden' );
	$( '#newPlanType' ).addClass( 'hidden' );
	$( '#planTicketTable' ).addClass( 'hidden' );
	$( '#planPaymentTable' ).addClass( 'hidden' );
	$( '#planBoosterType' ).addClass( 'hidden' );
	$( '#PLAN_BOOSTER_TABLE' ).addClass( 'hidden' );
	$( '#planChargesTable' ).addClass( 'hidden' );
	$( '#speedDropdown' ).addClass( 'hidden' );
	$( element ).addClass( 'active' );
	$( element ).parent().siblings().children().removeClass( 'active' );
	$( '#PLAN_BOOSTER_USAGE_DATA' ).addClass( 'hidden' );
	$( '#PLAN_BOOSTER_SPEED_DATA' ).addClass( 'hidden' );
	$( '#planInfoText' ).addClass( 'hidden' );
	$( '#VAS_ACTIVATION_DEACTIVATION_DIV' ).addClass( 'hidden' );
	$( '#VAS_PAID_FOC_DIV' ).addClass( 'hidden' );
	$( '#VAS_ACITIVATION_DATA_DIV' ).addClass( 'hidden' );
	$( '#VAS_DEACITIVATION_DATA_DIV' ).addClass( 'hidden' );
	$( '#newPlanType :input' ).attr( 'disabled', true );
	$( '#planLimitType :input' ).attr( 'disabled', true );
	$( '#VAS_ACTIVATION_DEACTIVATION_DIV :input' ).attr( 'disabled', true );
	$( '#VAS_PAID_FOC_DIV :input' ).attr( 'disabled', true );
	$( '#PLAN_BOOSTER_USAGE_SPEED_DIV :input' ).removeAttr( 'disabled' );
	$( '#PLAN_BOOSTER_PAID_FOC_DIV :input' ).removeAttr( 'disabled' );
	$( '#addonPlanOptions' ).addClass( 'hidden' );
	$( '#addonPlanOptions :input' ).attr( 'disabled', true );
	$( '#ADDON_PAID_FOC_DIV' ).addClass( 'hidden' );
	$( '#ADDON_PAID_FOC_DIV :input' ).attr( 'disabled', true );
	$( '#addonRemoveRemarks' ).addClass( 'hidden' );
	$( '#addonRemoveRemarks textarea' ).attr( 'disabled', true );
}

function vasManagement( element ){
	$( element ).addClass( 'active' );
	$( element ).parent().siblings().children().removeClass( 'active' );
	$( '#planCategoryId' ).val( "VAS_MANAGEMENT" );
	$( '#VAS_ACTIVATION_DEACTIVATION_DIV' ).removeClass( 'hidden' );
	$( '#VAS_PAID_FOC_DIV' ).addClass( 'hidden' );
	$( '#PLAN_BOOSTER_USAGE_DATA' ).addClass( 'hidden' );
	$( '#PLAN_BOOSTER_SPEED_DATA' ).addClass( 'hidden' );
	$( '#planInfoText' ).addClass( 'hidden' );
	$( '#planLimitType' ).addClass( 'hidden' );
	$( '#newPlanType' ).addClass( 'hidden' );
	$( '#newPlanTable' ).addClass( 'hidden' );
	$( '#planTicketTable' ).addClass( 'hidden' );
	$( '#PLAN_BOOSTER_USAGE_SPEED_DIV' ).addClass( 'hidden' );
	$( '#PLAN_BOOSTER_PAID_FOC_DIV' ).addClass( 'hidden' );
	$( '#VAS_ACITIVATION_DATA_DIV' ).addClass( 'hidden' );
	$( '#VAS_DEACITIVATION_DATA_DIV' ).addClass( 'hidden' );
	$( '#newPlanType :input' ).attr( 'disabled', true );
	$( '#planLimitType :input' ).attr( 'disabled', true );
	$( '#PLAN_BOOSTER_USAGE_SPEED_DIV :input' ).attr( 'disabled', true );
	$( '#PLAN_BOOSTER_PAID_FOC_DIV :input' ).attr( 'disabled', true );
	$( '#VAS_ACTIVATION_DEACTIVATION_DIV :input' ).removeAttr( 'disabled' );
	$( '#VAS_PAID_FOC_DIV :input' ).removeAttr( 'disabled' );
	$( '#addonPlanOptions' ).addClass( 'hidden' );
	$( '#addonPlanOptions :input' ).attr( 'disabled', true );
	$( '#ADDON_PAID_FOC_DIV' ).addClass( 'hidden' );
	$( '#ADDON_PAID_FOC_DIV :input' ).attr( 'disabled', true );
	$( '#addonRemoveRemarks' ).addClass( 'hidden' );
	$( '#addonRemoveRemarks textarea' ).attr( 'disabled', true );
}
function planRenew( element ){
	$( '[name="planUsageType"]' ).parent().removeClass( 'radio_on' );
	$( '#planCategoryId' ).val( "Plan Renewal" );
	$( '#VAS_ACTIVATION_DEACTIVATION_DIV' ).addClass( 'hidden' );
	$( '#VAS_PAID_FOC_DIV' ).addClass( 'hidden' );
	$( '#VAS_ACITIVATION_DATA_DIV' ).addClass( 'hidden' );
	$( '#VAS_DEACITIVATION_DATA_DIV' ).addClass( 'hidden' );
	$( '#planLimitType' ).removeClass( 'hidden' );
	$( '#newPlanTable' ).addClass( 'hidden' );
	$( '#newPlanType' ).addClass( 'hidden' );
	$( '#planTicketTable' ).addClass( 'hidden' );
	$( '#planPaymentTable' ).addClass( 'hidden' );
	$( '#planBoosters' ).addClass( 'hidden' );
	$( '#planBoosterType' ).addClass( 'hidden' );
	$( '#PLAN_BOOSTER_TABLE' ).addClass( 'hidden' );
	$( '#planChargesTable' ).addClass( 'hidden' );
	$( '#speedDropdown' ).addClass( 'hidden' );
	$( '#planInfoText' ).addClass( 'hidden' );
	$( element ).addClass( 'active' );
	$( element ).parent().siblings().children().removeClass( 'active' );
	$( '#PLAN_BOOSTER_USAGE_SPEED_DIV' ).addClass( 'hidden' );
	$( '#PLAN_BOOSTER_PAID_FOC_DIV' ).addClass( 'hidden' );
	$( '#PLAN_BOOSTER_TABLE' ).addClass( 'hidden' );
	$( '#PLAN_BOOSTER_USAGE_DATA' ).addClass( 'hidden' );
	$( '#PLAN_BOOSTER_SPEED_DATA' ).addClass( 'hidden' );
	$( '#PLAN_BOOSTER_PAID_FOC_DIV :input' ).attr( 'disabled', true );
	$( '#PLAN_BOOSTER_USAGE_SPEED_DIV :input' ).attr( 'disabled', true );
	$( '#planLimitType :input' ).removeAttr( 'disabled' );
	$( '#newPlanType :input' ).removeAttr( 'disabled' );
	$( '#VAS_ACTIVATION_DEACTIVATION_DIV :input' ).attr( 'disabled', true );
	$( '#VAS_PAID_FOC_DIV :input' ).attr( 'disabled', true );
	$( '#addonPlanOptions' ).addClass( 'hidden' );
	$( '#addonPlanOptions :input' ).attr( 'disabled', true );
	$( '#ADDON_PAID_FOC_DIV' ).addClass( 'hidden' );
	$( '#ADDON_PAID_FOC_DIV :input' ).attr( 'disabled', true );
	$( '#addonRemoveRemarks' ).addClass( 'hidden' );
	$( '#addonRemoveRemarks textarea' ).attr( 'disabled', true );
}
function planReactivation( element ){
	$( '#planCategoryId' ).val( "Plan Reactivation" );
	$( '#VAS_ACTIVATION_DEACTIVATION_DIV' ).addClass( 'hidden' );
	$( '#VAS_PAID_FOC_DIV' ).addClass( 'hidden' );
	$( '#VAS_ACITIVATION_DATA_DIV' ).addClass( 'hidden' );
	$( '#VAS_DEACITIVATION_DATA_DIV' ).addClass( 'hidden' );
	$( '#planLimitType' ).removeClass( 'hidden' );
	$( '#newPlanTable' ).addClass( 'hidden' );
	$( '#newPlanType' ).addClass( 'hidden' );
	$( '#planTicketTable' ).addClass( 'hidden' );
	$( '#planPaymentTable' ).addClass( 'hidden' );
	$( '#planBoosters' ).addClass( 'hidden' );
	$( '#planBoosterType' ).addClass( 'hidden' );
	$( '#PLAN_BOOSTER_TABLE' ).addClass( 'hidden' );
	$( '#planChargesTable' ).addClass( 'hidden' );
	$( '#speedDropdown' ).addClass( 'hidden' );
	$( '#planInfoText' ).addClass( 'hidden' );
	$( element ).addClass( 'active' );
	$( element ).parent().siblings().children().removeClass( 'active' );
	$( '#PLAN_BOOSTER_USAGE_SPEED_DIV' ).addClass( 'hidden' );
	$( '#PLAN_BOOSTER_PAID_FOC_DIV' ).addClass( 'hidden' );
	$( '#PLAN_BOOSTER_TABLE' ).addClass( 'hidden' );
	$( '#PLAN_BOOSTER_USAGE_DATA' ).addClass( 'hidden' );
	$( '#PLAN_BOOSTER_SPEED_DATA' ).addClass( 'hidden' );
	$( '#PLAN_BOOSTER_PAID_FOC_DIV :input' ).attr( 'disabled', true );
	$( '#PLAN_BOOSTER_USAGE_SPEED_DIV :input' ).attr( 'disabled', true );
	$( '#planLimitType :input' ).removeAttr( 'disabled' );
	$( '#newPlanType :input' ).removeAttr( 'disabled' );
	$( '#VAS_ACTIVATION_DEACTIVATION_DIV :input' ).attr( 'disabled', true );
	$( '#VAS_PAID_FOC_DIV :input' ).attr( 'disabled', true );
	$( '#addonPlanOptions' ).addClass( 'hidden' );
	$( '#addonPlanOptions :input' ).attr( 'disabled', true );
	$( '#ADDON_PAID_FOC_DIV' ).addClass( 'hidden' );
	$( '#ADDON_PAID_FOC_DIV :input' ).attr( 'disabled', true );
	$( '#addonRemoveRemarks' ).addClass( 'hidden' );
	$( '#addonRemoveRemarks textarea' ).attr( 'disabled', true );
}

function showPaidFocDiv(){
	$( '[name="planType"]' ).parent().removeClass( 'radio_on' );
	$( '[name="planType"]' ).prop( 'checked', false );
	$( '#PLAN_BOOSTER_PAID_FOC_DIV' ).removeClass( 'hidden' );
	$( '#PLAN_BOOSTER_USAGE_DATA' ).addClass( 'hidden' );
	$( '#PLAN_BOOSTER_SPEED_DATA' ).addClass( 'hidden' );
	$( '#newPlanType' ).addClass( 'hidden' );
	$( '#newPlanTable' ).addClass( 'hidden' );
	$( '#planTicketTable' ).addClass( 'hidden' );
	$( '#planPaymentTable' ).addClass( 'hidden' );
	$( '#PLAN_BOOSTER_TABLE' ).addClass( 'hidden' );
	$( '#planChargesTable' ).addClass( 'hidden' );
	$( '#speedDropdown' ).addClass( 'hidden' );
	$( '#planInfoText' ).addClass( 'hidden' );
}

function showPaidFocDivVAS(){
	$( '#VAS_ACITIVATION_DATA_DIV' ).addClass( 'hidden' );
	$( '#VAS_DEACITIVATION_DATA_DIV' ).addClass( 'hidden' );
	$( '#VAS_PAID_FOC_DIV' ).removeClass( 'hidden' );
}

function showPlanBoosterData(){
	$( '.loadingPopup' ).removeClass( 'hidden' );
	document.forms[ 1 ].action = "manageQrc.do?method=getCustomerActivationPlan";
	document.forms[ 1 ].submit();
}

function saveCustomerPlanMigration(){
	var isReceivedValidData = true;
	var remarks = $( '#bubRemarksId:enabled' ).val();
	var oldBasePlanCode = $( '#oldBasePlanCodeId' ).val();
	var newBasePlanCode = $( '[name="selectedPlanCode"] option:selected' ).val();
	var oldAddonPlanCode = $( '#oldAddonPlanCodeId' ).val();
	var newAddonPlanCode = $( '#addonPlanId' ).val();
	var planCategory = $( '#planCategoryId' ).val();
	var planUsageType = '';
	var activationTime = $( '#activationPopUpId option:selected' ).text();
	$( '#bubRemarksId' ).next( 'font' ).hide();
	$( '#planSelectId' ).hide();
	$( '#activationPopUpId' ).parent().parent().siblings( 'font' ).hide();
	if ( planCategory == "Base plan migration" || planCategory == "Plan Renewal" || planCategory == "Plan Reactivation" ) {

		if ( planCategory == "Base plan migration" ) {
			document.getElementById( 'activationTypeId' ).value = activationTime;
			if ( activationTime == "Next Billing Cycle" && $( '[name="selectedPlanCode"] option:selected' ).data( 'addonyn' ) == 'Y' ) {
				$( '#addonAllowedId' ).val( 'true' );
			}
			else {
				$( '#addonAllowedId' ).val( 'false' );
			}
		}
		else {
			document.getElementById( 'activationTypeId' ).value = 'Immediate';
		}

		if ( $( '[name="selectedPlanCode"] option:selected' ).val() == '' ) {
			// if ( $( '[name="tariffAddonPlan"]:checked' ).length == 0 ) {
			isReceivedValidData = false;
			$( '#newBasePlanCodeID' ).parent().next( 'font' ).removeClass( 'hidden' ).text( 'Please select Base Plan.' );
			// }
		}
		else if ( document.getElementById( 'ticketListAvailID' ) != null ) {
			if ( document.getElementById( 'ticketListAvailID' ).value == 'Y' && $( '[name="srTicketsPojos[0].editable"]:checked' ).length == 0 ) {
				isReceivedValidData = false;
				$( '#ticketDivId' ).children( 'font' ).show().html( "Ticket selection is mandatory." ).addClass( 'errorTextbox' );
			}
			else {
				$( '#ticketDivId' ).children( 'font' ).hide();
			}
		}
		if ( planCategory == "Base plan migration"
				&& ( $( '#activationPopUpId option:selected' ).val() == '' || $( '#activationPopUpId option:selected' ).val() == null || $(
						'#activationPopUpId option:selected' ).val() == undefined ) ) {
			isReceivedValidData = false;
			$( '#activationPopUpId' ).parent().parent().siblings( 'font' ).show().text( "Please select Activation Time" );
		}
		if ( planCategory == "Base plan migration" && oldBasePlanCode == newBasePlanCode && oldAddonPlanCode == newAddonPlanCode ) {
			isReceivedValidData = false;
			$( '#planSelectId' ).show().text( "Selected Plan is same as the current Plan" );
		}

	}
	else if ( planCategory == 'Addon Plans' ) {
		document.getElementById( 'activationTypeId' ).value = $( '#activationPopUpId option:selected' ).text();
		$( '#newAddonPlanCodeID' ).parent().next( 'font' ).addClass( 'hidden' );
		$( '#activationPopUpId' ).parent().parent().siblings( 'font' ).hide();
		planUsageType = $( '[name="planUsageType"]:checked' ).val();
		if ( document.getElementById( 'ticketListAvailID' ) != null ) {
			if ( document.getElementById( 'ticketListAvailID' ).value == 'Y' && $( '[name="srTicketsPojos[0].editable"]:checked' ).length == 0 ) {
				isReceivedValidData = false;
				$( '#ticketDivId' ).children( 'font' ).show().html( "Ticket selection is mandatory." ).addClass( 'errorTextbox' );
			}
			else {
				$( '#ticketDivId' ).children( 'font' ).hide();
			}
		}
		if ( planUsageType == 'CHANGE_ADDON' ) {
			if ( $( '#newAddonPlanCodeID' ).val() == '' ) {
				isReceivedValidData = false;
				$( '#newAddonPlanCodeID' ).parent().next( 'font' ).removeClass( 'hidden' ).text( 'Please selecte Addon Plan' );
			}
			if ( $( '#addonAllowedId' ).val() == "false" ) {
				if ( $( '#activationPopUpId' ).val() == '' ) {
					isReceivedValidData = false;
					$( '#activationPopUpId' ).parent().parent().siblings( 'font' ).show().text( 'Please select Activation Time' );
				}
			}
			else {
				document.getElementById( 'activationTypeId' ).value = "Next Billing Cycle";
			}
		}
		else {
			if ( $( '#activationPopUpId' ).val() == '' ) {
				isReceivedValidData = false;
				$( '#activationPopUpId' ).parent().parent().siblings( 'font' ).show().text( 'Please select Activation Time' );
			}
		}
	}

	var confirmationMsg = '';

	if ( planCategory == "Base plan migration" ) {
		confirmationMsg = "Are you sure? You want to migrate customer's plan.";
	}
	else if ( planCategory == "Plan Renewal" ) {
		confirmationMsg = "Are you sure? You want to renew customer's plan.";
	}
	else if ( planCategory == "Plan Reactivation" ) {
		confirmationMsg = "Are you sure? You want to reactivate customer's plan.";
	}
	else if ( planCategory == "Booster plan" ) {
		confirmationMsg = "Are you sure? You want to activate booster for customer.";
	}
	else if ( planCategory == "VAS_MANAGEMENT" ) {
		confirmationMsg = "Are you sure? You want to activate value added service for customer.";
	}
	else if ( planCategory == 'Addon Plans' ) {
		if ( planUsageType == 'CHANGE_ADDON' )
			confirmationMsg = "Are you sure to change addon?";
		else if ( planUsageType == 'REMOVE_ADDON' )
			confirmationMsg = "Are you sure to remove addon?";
	}
	if ( $( '#addonAction' ).val() == '0' ) {
		isReceivedValidData = false;
		$( '#addonAction' ).next( 'font' ).show().html( 'Please select ADDON Action' );
	}
	else if ( $( '#addonAction' ).val() != '0' || $( '#addonAction' ).val() != null || $( '#addonAction' ).val() != undefined ) {
		$( '#addonAction' ).next( 'font' ).hide();
	}
	if ( remarks.trim() === '' || remarks == undefined || remarks == null || remarks.trim().length < 2 || remarks.trim().length > 4000 ) {
		isReceivedValidData = false;
		$( '#bubRemarksId' ).next( 'font' ).show().html( 'Please enter Remarks [2-4000]' );
	}
	if ( '' != confirmationMsg && isReceivedValidData == true ) {
		if ( confirm( confirmationMsg ) ) {
			$( '.loadingPopup' ).removeClass( 'hidden' );
			document.forms[ 1 ].action = "manageQrc.do?method=saveCustomerActivationPlan";
			document.forms[ 1 ].submit();
		}
	}
	return false;
}

function cancelPlanMigration(){
	var process = true;
	var remarks = $( '#bubRemarksId' ).val();
	$( '#bubRemarksId:enabled' ).next( 'font' ).hide();
	if ( remarks.trim() == '' || remarks == undefined || remarks == null || remarks.trim().length < 2 || remarks.trim().length > 4000 ) {
		$( '#bubRemarksId' ).next( 'font' ).show().text( 'Please enter Remarks [2-4000]' );
		$( '#bubRemarksId' ).focus();
		process = false;
	}
	if ( process && confirm( 'Are you sure to cancel?' ) ) {
		$( '#tariffMigration' ).attr( 'action', 'manageQrc.do?method=cancelPlanMigration' );
		$( '#tariffMigration' ).submit();
	}
	return false;
}

function downloadCustomerUsage( inUsageType ){
	if ( confirm( "Are you sure you want to download Customer Usage?" ) ) {
		document.forms[ 1 ].action = "manageQrc.do?method=downloadCustomerUsage&usageType=" + inUsageType;
		document.forms[ 1 ].target = '_blank';
		document.forms[ 1 ].submit();
	}
}

function populateSubSubCategoriesforRadio( inCategoryId, inDestinationID, inSubCategoryId ){

	var subCategoryValue = inSubCategoryId.value;
	if ( subCategoryValue == "Please Select" || subCategoryValue == ' ' || subCategoryValue == '0' || subCategoryValue == null ) {
		// alert("Please select category and sub Category");
		return false;
	}
	else {
		populateSubSubCategories( inCategoryId, inDestinationID, subCategoryValue );
	}
}

function populateSubCategoriesforFunctionBin( inDestinationID, inAccessID, inDestSubSubCatID ){

	var valid = true;

	// $( '.loadingPopup' ).removeClass( 'hidden' );
	$( '#binCategoryID ~ font' ).addClass( 'hidden' );
	$( '#binSubCategoryId ~ font' ).addClass( 'hidden' );

	if ( inAccessID == '' || inAccessID == '0' || inAccessID == null || inAccessID == undefined || inAccessID == 'Please Select' ) {
		$( '#binCategoryID ~ font' ).removeClass( 'hidden' );
		valid = false;
	}

	if ( valid ) {
		$( '#binCategoryID ~ font' ).addClass( 'hidden' );

		populateSubCategories( inDestinationID, inAccessID );
		dwr.util.removeAllOptions( inDestSubSubCatID );
		dwr.util.addOptions( inDestSubSubCatID, [
			{
				"value" : "0", "label" : "Please Select"
			}
		], "value", "label" );
	}
	else {
		dwr.util.removeAllOptions( inDestSubSubCatID );
		dwr.util.addOptions( inDestSubSubCatID, [
			{
				"value" : "0", "label" : "Please Select"
			}
		], "value", "label" );
		dwr.util.removeAllOptions( inDestinationID );
		dwr.util.addOptions( inDestinationID, [
			{
				"value" : "0", "label" : "Please Select"
			}
		], "value", "label" );
		$( '.loadingPopup' ).addClass( 'hidden' );
	}

	$( '#functionalBinList' ).hide();
}
function refreshValue( divId, methodName ){
	if ( methodName == "ticket" ) {
		if ( divId == 'search' ) {
			// alert("ticket"+methodName+divId);
			// document.getElementById( 'categoryIdSearch' ).value = '0';
			// dwr.util.removeAllOptions( subCategoryIdSearch );
			// dwr.util.removeAllOptions( subSubCategoryIdSearch );
			// dwr.util.addOptions( subCategoryIdSearch, [
			// {
			// "value" : "0", "label" : "Please Select"
			// }
			// ], "value", "label" );
			// dwr.util.addOptions( subSubCategoryIdSearch, [
			// {
			// "value" : "0", "label" : "Please Select"
			// }
			// ], "value", "label" );
			$( '#ticketListDiv' ).show();

			$( '#msgDiv' ).hide();

		}
		else if ( divId == 'generate' ) {
			// document.getElementById( 'categoryId' ).value = '';
			// document.getElementById( 'remarks' ).value = '';
			/*
			 * dwr.util.removeAllOptions( subCategoryId ); dwr.util.removeAllOptions( subSubCategoryId ); dwr.util.addOptions( subCategoryId, [ { "value" : "0", "label" : "Please Select" } ], "value", "label" ); dwr.util.addOptions( subSubCategoryId, [ { "value" : "0", "label" : "Please Select" } ],
			 * "value", "label" );
			 * 
			 * dwr.util.removeAllOptions( resolutionType ); dwr.util.addOptions( resolutionType, [ { "value" : "0", "label" : "Please Select" } ], "value", "label" );
			 */
			crmDwr.filterSubCategories( "", function( subCategoryList ){
				subCats = [];
				var cat;
				if ( subCategoryList != null ) {
					subCategoryList.forEach( function( e ){
						cat = {}
						cat[ 'label' ] = e.qrcSubCategory;
						cat[ 'value' ] = e.qrcSubCategoryId;
						subCats.push( cat );
					} );
				}
				$( '#qrcSubCategoryTextId' ).autocomplete( {
					source : subCats, minLength : 0, change : function( e, ui ){
						resetTicketAutocomplete( false, true );
						$( '#categoryId' ).val( '' );
						$( '#subCategoryId' ).val( '' );
						$( '#subSubCategoryId' ).val( '' );
						if ( !ui.item ) {
							$( e.target ).val( '' );
						}
						else {
							$( '#subCategoryId' ).val( ui.item.value );
							// get sub sub categories
							crmDwr.getCategoryIdBySubcategory( ui.item.value, function( categoryId ){
								if ( categoryId != null && categoryId != "0" ) {
									$( '#categoryId' ).val( categoryId );
									fillSubSubCategories( categoryId, ui.item.value );
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
	}
	else {

		if ( divId == 'search' ) {
			// alert("if"+methodName+divId);

			document.getElementById( 'interactionCategorySearch' ).value = '';
			dwr.util.removeAllOptions( interactionSubCategorySearch );
			dwr.util.addOptions( interactionSubCategorySearch, [
				{
					"value" : "0", "label" : "Please Select"
				}
			], "value", "label" );
			$( '#interactionMsg' ).hide();

			$( '#interactionResult' ).hide();

		}
		else if ( divId == 'generate' ) {
			// alert("else if"+methodName+divId);
			document.getElementById( 'interactionCategory' ).value = '0';
			dwr.util.removeAllOptions( interactionSubCategory );
			dwr.util.addOptions( interactionSubCategory, [
				{
					"value" : "0", "label" : "Please Select"
				}
			], "value", "label" );
		}

	}
}

function sendEbill( billNumber ){
	$( '.loadingPopup' ).removeClass( 'hidden' );
	document.forms[ 0 ].action = "manageQrc.do?method=sendEBill&billNumber=" + billNumber;
	document.forms[ 0 ].submit();
}

function sendCustomerUsage( inUsageType, inBillPeriod ){
	// var action = "manageQrc.do?method=sendCustomerUsage&usageType=" + inUsageType + "&billPeriod=" + inBillPeriod;
	// if ( inUsageType == 'Billed' ) {
	// document.forms[ 0 ].action = action;
	// document.forms[ 0 ].submit();
	// }
	// else {
	// document.forms[ 1 ].action = action;
	// document.forms[ 1 ].submit();
	// }
	$( '.loadingPopup' ).removeClass( 'hidden' );
	crmDwr.sendCustomerUsage( inUsageType, inBillPeriod, {
		callback : function( result ){
			if ( result == null || result.length != 2 ) {
				$( '.loadingPopup' ).addClass( 'hidden' );
				alert( 'Unable to process your request.' );
			}
			else {
				$( '.loadingPopup' ).addClass( 'hidden' );
				alert( result[ 1 ] );
			}
		}, errorHandler : function( errorString, exception ){
			$( '.loadingPopup' ).addClass( 'hidden' );
			alert( errorString );
		}
	} );
}

function validateUsagePeriod(){
	var fromDate = document.getElementById( 'usageFormDateID' ).value;
	var toDate = document.getElementById( 'usageToDateID' ).value;
	var currDate = new Date();
	var errorElement = document.getElementById( 'billedUsageErrorArea' );
	errorElement.innerHTML = "";
	if ( fromDate == "" ) {
		errorElement.innerHTML = "'From Date' can't be left blank.";
		return false;
	}
	else if ( toDate == "" ) {
		errorElement.innerHTML = "'To Date' can't be left blank.";
		return false;
	}
	else {
		var startDate = getISODate( fromDate );
		var endDate = getISODate( toDate );
		// alert(dateDiff( endDate, currDate ));
		if ( dateDiff( startDate, currDate ) < 0 ) {
			errorElement.innerHTML = "Future date selected for 'From Date'.";
			return false;
		}
		else if ( dateDiff( endDate, currDate ) < 0 ) {
			errorElement.innerHTML = "Future date selected for 'To Date'.";
			return false;
		}
		else if ( dateDiff( startDate, endDate ) < 0 ) {
			errorElement.innerHTML = "'From Date' is future date in respect to 'To Date'.";
			return false;
		}
		else if ( dateDiff( startDate, endDate ) > 30 ) {
			errorElement.innerHTML = "Only, 30 days period is allowed.";
			return false;
		}
	}
	return true;
}
function populateTicket( inDestinationID, inAccessID, inDestSubSubCatID ){

	// alert(inAccessID);
	if ( inAccessID == 0 ) {
		$( '#subCategoryIdSearch' ).val( '' );
		$( '#subSubCategoryIdSearch' ).val( '' );
		subCats = [];
		$( '#subCategoryIdSearch' ).autocomplete( 'option', {
			source : subCats
		} );
		document.getElementById( 'subCategoryIdSearch' ).value = '';
		subSubCats = [];
		$( '#subSubCategoryIdSearch' ).autocomplete( 'option', {
			source : subSubCats
		} );
		document.getElementById( 'subSubCategoryIdSearch' ).value = '';

	}
	else {
		var valid = true;

		$( '#categoryIdSearch ~ font' ).addClass( 'hidden' );
		$( '#binSubCategoryId ~ font' ).addClass( 'hidden' );

		if ( inAccessID == '' || inAccessID == '0' || inAccessID == null || inAccessID == undefined || inAccessID == 'Please Select' ) {
			$( '#categoryIdSearch ~ font' ).removeClass( 'hidden' );
			valid = false;
		}

		if ( valid ) {
			$( '#categoryIdSearch ~ font' ).addClass( 'hidden' );

			// populateSubCategories( inDestinationID, inAccessID, inDestSubSubCatID );
			populateSubCategoriesnew( inDestinationID, inAccessID, inDestSubSubCatID );
			dwr.util.removeAllOptions( inDestSubSubCatID );
			dwr.util.addOptions( inDestSubSubCatID, [
				{
					"value" : "0", "label" : "Please Select"
				}
			], "value", "label" );
			dwr.util.removeAllOptions( inDestSubSubCatID );
			dwr.util.addOptions( inDestSubSubCatID, [
				{
					"value" : "0", "label" : "Please Select"
				}
			], "value", "label" );
		}
		else {
			dwr.util.removeAllOptions( inDestSubSubCatID );
			dwr.util.addOptions( inDestSubSubCatID, [
				{
					"value" : "0", "label" : "Please Select"
				}
			], "value", "label" );
			dwr.util.removeAllOptions( inDestinationID );
			dwr.util.addOptions( inDestinationID, [
				{
					"value" : "0", "label" : "Please Select"
				}
			], "value", "label" );

		}

		// $( '#ticketListDiv' ).hide();
	}
}
/* waiver */
$( document ).ready( function(){
	if ( $( "#reject:checked" ).length != 0 ) {
		document.getElementById( 'rejectReason' ).style.display = 'block';
	}
	$( '#submitmodifywaiver' ).click( function(){

		var valid = true;
		if ( $( "#approved:checked" ).length == 0 && $( "#reject:checked" ).length == 0 ) {
			alert( "Please choose  an action to submit." );
			valid = false;
		}
		if ( valid && commonWaiverError() ) {
			if ( confirm( "Are you sure you want to Approve/Reject Waiver?" ) ) {
				$( '.loadingPopup' ).removeClass( 'hidden' );
				$( '#modifyWaiverForm' ).attr( 'action', 'waiver.do?method=approveRejectWaiver' );
				$( '#modifyWaiverForm' ).submit();
			}
		}
	} );
	$( '.loadingPopup' ).addClass( 'hidden' );
	// By default 'group inbox' selection in follow up of tickets
	$( '#inboxSelectionID option:selected' ).removeAttr( 'selected' );
} );

function commonWaiverError(){
	var valid = true;
	$( '#rejectionReason ~ font' ).addClass( 'hidden' );
	$( '#remarks ~ font' ).addClass( 'hidden' );
	if ( $( "#reject:checked" ).length != 0 ) {
		if ( $( "#rejectionReason" ).val() == '' ) {
			$( '#rejectionReason ~ font' ).removeClass( 'hidden' );
			$( '#rejectionReason' ).next( 'font' ).show().html( "Please select Rejection Reason." ).addClass( 'errorTextboxInDiv' );
			valid = false;
		}
	}
	if ( $( "#remarks" ).val().trim() == "" ) {
		$( '#remarks ~ font' ).removeClass( 'hidden' );
		$( '#remarks' ).next( 'font' ).show().html( "Please enter remarks" ).addClass( 'errorTextboxInDiv' );
		valid = false;
	}
	else if ( ( $( "#remarks" ).val().trim().length < 2 ) || ( ( $( "#remarks" ).val().trim().length > 4000 ) ) ) {
		$( '#remarks ~ font' ).removeClass( 'hidden' );
		$( '#remarks' ).next( 'font' ).show().html( "Please enter Remarks between [2-4000]." ).addClass( 'errorTextboxInDiv' );
		valid = false;
	}
	return valid;
}

function showPayment( newCpe ){

	var oldCpe = $( "#oldCpeStatus" ).val();

	if ( newCpe == oldCpe ) {
		$( '#CPEChargesnSave' ).addClass( 'hide1' );
		$( '#CPEChargesQRC' ).addClass( 'hide1' );
	}
	else if ( newCpe == "CO" ) {
		$( '#CPEChargesnSave' ).removeClass( 'hide1' );
		$( '#CPEChargesQRC' ).addClass( 'hide1' );
	}
	else if ( newCpe == "NR" ) {
		$( '#CPEChargesnSave' ).removeClass( 'hide1' );
		$( '#CPEChargesQRC' ).removeClass( 'hide1' );
	}
}

function commonShiftingError(){
	var flag = true;
	var oldCpe = $( "#oldCpeStatus" ).val();
	if ( oldCpe == 'CO' ) {
		$( '#paymentModeQRC ~ font' ).addClass( 'hidden' );
		$( '#amountId ~ font' ).addClass( 'hidden' );

		var value = $( '#paymentModeQRC' ).val();
		var amount = $( '#amountId' ).val();

		if ( value == "" || value == null || value == undefined ) {
			flag = false;
			$( '#paymentModeQRC ~ font' ).removeClass( 'hidden' );
		}
		if ( amount == '0' || amount == null || amount == "" ) {
			flag = false;
			$( '#amountId ~ font' ).removeClass( 'hidden' );
		}
		if ( flag && $( '#teleCommChequeDate' ).val() != "" ) {
			var chequeDate = $( '#teleCommChequeDate' ).val();
			flag = validityChequeDate( chequeDate, 90 );
			if ( !flag ) {
				$( '#teleCommChequeDate ~ font' ).text( 'Cheque/DD Date should not be earlier/later than 90 days' ).show();
			}
			else {
				$( '#teleCommChequeDate ~ font' ).text( 'Cheque/DD Date should not be earlier/later than 90 days' ).hide();
			}
		}
	}
	console.log( "Common Error:" + flag );
	return flag;
}
function emptyCheckRemarks(){
	var valid = true;
	var remarks = $( '#ifrRemarks' ).val();

	$( '#ifrRemarks ~ font' ).addClass( 'hidden' );
	$( '#ifrRemarks ~ span' ).addClass( 'hidden' );

	if ( remarks.trim() == '' || remarks == null || remarks == undefined ) {
		$( '#ifrRemarks ~ font' ).removeClass( 'hidden' );
		valid = false;
	}
	else if ( remarks.trim().length < 2 || remarks.trim().length > 4000 ) {
		$( '#ifrRemarks ~ span' ).removeClass( 'hidden' );
		valid = false;
	}
	return valid;
}

function viewPaymentmodeqrc( paymentModeVal ){
	if ( paymentModeVal == 'Q' ) {
		$( '#chequeQRC' ).removeClass( 'hide1' );
		$( '#bankNameQRC' ).removeClass( 'hide1' );
		$( '#chequeQRC input, #bankNameQRC' ).attr( 'disabled', false );
		$( '#cashQRC, #ddQRC, #onlinePaymentQRC' ).addClass( 'hide1' );
		$( '#cashQRC input, #ddQRC input, #onlinePaymentQRC input' ).attr( 'disabled', true );
	}
	else if ( paymentModeVal == 'C' ) {
		$( '#cashQRC' ).removeClass( 'hide1' );
		$( '#cashQRC input' ).attr( 'disabled', false );
		$( '#chequeQRC, #ddCPE, #onlinePaymentQRC,#bankNameQRC' ).addClass( 'hide1' );
		$( '#chequeQRC input, #ddQRC input, #onlinePaymentQRC input,#bankNameQRC' ).attr( 'disabled', true );
	}
	else if ( paymentModeVal == 'O' || paymentModeVal == "NEFT" ) {
		$( '#onlinePaymentQRC' ).removeClass( 'hide1' );
		$( '#onlinePaymentQRC input' ).attr( 'disabled', false );
		$( '#chequeQRC, #cashQRC, #cashQRC,#bankNameQRC' ).addClass( 'hide1' );
		$( '#chequeQRC input, #cashQRC input, #ddQRC input,#bankNameQRC' ).attr( 'disabled', true );
	}
	else {
		$( '#chequeQRC, #cashQRC, #cashQRC, #onlinePaymentQRC,#bankNameQRC' ).addClass( 'hide1' );
		$( '#chequeQRC input, #cashQRC input, #ddQRC input, #onlinePaymentQRC input,#bankNameQRC' ).attr( 'disabled', true );
	}
}

function forwardToNextBin( inWorkflowId, shiftingType, customerId ){
	var reply = null;
	if ( emptyCheckRemarks() && shiftingType == "Outside Premises" ) {
		reply = confirm( "Do you want to forward the docket to the next bin ?" );
	}
	else if ( emptyCheckRemarks() && shiftingType == "Within Premises" ) {
		reply = confirm( "Do you want to close the docket ?" );
	}
	if ( reply ) {
		$( '.loadingPopup' ).removeClass( 'hidden' );
		document.forms[ 1 ].action = "shiftingWorkflow.do?method=forwardToNextBin&inWorkflowId=" + inWorkflowId + "&inCustomerId=" + customerId
				+ "&inShiftingType=" + shiftingType;
		document.forms[ 1 ].submit();
	}
}

function saveMacWORKFLOW( product, requestType, workflowId, inStage, inCustomerId, inWorkflowType ){

	document.getElementById( "customerId" ).value = inCustomerId;
	document.getElementById( "workflowId" ).value = workflowId;
	document.getElementById( "workflowStage" ).value = inStage;
	document.getElementById( "shiftingType" ).value = inWorkflowType;

	flag_deviceChangeMac = false;
	// $( '#IDqrcNewPriMacId' ).next( 'font' ).hide();
	// $( '#IDsaveMacQrcDeviceChange' ).next( 'font' ).hide();
	if ( product != 'EOC' ) {

		// BB & RF
		// primary mandatory for BB
		if ( $( '#IDqrcNewPriMacId' ).val() == '' ) {

			$( '#IDqrcNewPriMacId' ).next( 'font' ).show().html( "Please enter 'New MAC Address'" ).addClass( 'errorTextbox' ).css( 'width', 215 );
		}
		else if ( $( '#IDqrcNewPriMacId' ).val() == $( '#IDqrcOldPriMacId' ).val() ) {
			alert( product + "3" );
			$( '#IDqrcNewPriMacId' ).next( 'font' ).show().html( "'New MAC Address' should not be equal to 'Old MAC Address'" ).addClass(
					'errorTextbox' ).css( 'width', 380 );
		}
		else {
			if ( checkHexadecimal( $( '#IDqrcNewPriMacId' ).val() ) ) {
				flag_deviceChangeMac = true;
			}
			else {
				$( '#IDqrcNewPriMacId' ).next( 'font' ).show().html(
						"'New MAC Address' should be hexadecimal value and address should  be in MAC format [23aF.D3de.A12f]" ).addClass(
						'errorTextbox' ).css( 'width', 380 );
			}
		}
	}
	else {
		// EOC
		// primary and secondary for EOC
		$( '#IDqrcNewSecMacId' ).next( 'font' ).hide();
		if ( $( '#secondaryMac' ).val() == "" ) {

			$( '#secondaryMac' ).next( 'font' ).show().html( "Please select 'Secondary MAC Address.'" ).addClass( 'errorTextbox' ).css( 'width',
					'auto' );

		}
		else if ( $( '#primaryMac' ).val() == '' ) {

			$( '#primaryMac' ).next( 'font' ).show().html( "Please give 'Primary MAC Address'" ).addClass( 'errorTextbox' ).css( 'width', 380 );
		}
		else if ( $( '#primaryMac' ).val() == '' && $( '#primaryMac' ).val() == '' ) {

			$( '#primaryMac' ).next( 'font' ).show().html( "Please give one of 'Primary MAC Address' or 'Secondary MAC Address'" ).addClass(
					'errorTextbox' ).css( 'width', 380 );
		}
		else if ( $( '#primaryMac' ).val() == $( '#oldprimaryMac' ).val() ) {

			// new primary and secondary should not be equal to old values
			$( '#IDqrcNewPriMacId' ).next( 'font' ).show().html( "Please change one of 'Primary MAC Address' or 'Secondary MAC Address'" ).addClass(
					'errorTextbox' ).css( 'width', 380 );
		}
		else {

			if ( checkHexadecimal( $( '#primaryMac' ).val() ) ) {

				flag_deviceChangeMac = true;
			}
			else {

				$( '#primaryMac' ).next( 'font' ).show().html(
						"'New Primary MAC Address' should be hexadecimal value and address should  be in MAC format [23aF.D3de.A12f]" ).addClass(
						'errorTextbox' ).css( 'width', 600 );
			}
		}
	}

	if ( flag_deviceChangeMac && emptyCheckRemarks() ) {

		var reply = confirm( "Do you want to save new MAC ?" );
		if ( reply ) {
			$( '.loadingPopup' ).removeClass( 'hidden' );
			document.forms[ 1 ].action = "shiftingWorkflow.do?method=editMacForShifting&inRequestType=workflow";
			document.forms[ 1 ].submit();
		}

	}
}
function saveAccessoriesWorkflow( requestType, size, paramValue, workflowId, inStage, inCustomerId, inWorkflowType ){

	document.getElementById( "customerId" ).value = inCustomerId;
	document.getElementById( "workflowId" ).value = workflowId;
	document.getElementById( "workflowStage" ).value = inStage;
	document.getElementById( "shiftingType" ).value = inWorkflowType;

	flag = false;

	if ( requestType == 'wiring' && emptyCheckRemarks() ) {
		// condition true wiring point flag true
		if ( $( '#IDWiringChargesADD' ).val() == undefined || $( '#IDWiringChargesADD' ).val() == '' || $( '#IDWiringChargesADD' ).val() == '0' ) {
			flag = false;
			$( '#IDWiringChargesADD' ).next( 'font' ).show().html( "Please Provide 'Wiring Charges'" ).addClass( 'errorTextbox' );
		}
		else if ( !checkPaidAmount( $( '#IDWiringChargesADD' ).val() ) ) {
			$( '#IDWiringChargesADD' ).next( 'font' ).show().html( "Only two digits allowed after decimal point." ).addClass( 'errorTextbox' ).css(
					'width', 350 );
			flag = false;
		}
		else {
			flag = true;
			$( '#IDWiringChargesADD' ).next( 'font' ).hide();
		}
	}

	if ( flag ) {
		var reply = false;

		reply = confirm( "Are you sure to save 'Wiring Charges?'" );

		if ( reply ) {
			$( '.loadingPopup' ).removeClass( 'hidden' );
			document.forms[ 1 ].action = "manageQrc.do?method=addRemoveAccessories&requestType=" + requestType + "&inParamValue=" + paramValue;
			document.forms[ 1 ].submit();
		}
	}
}

function saveOption82WORKFLOW( product, requestType, workflowId, inStage, inCustomerId, inWorkflowType ){
	document.getElementById( "customerId" ).value = inCustomerId;
	document.getElementById( "workflowId" ).value = workflowId;
	document.getElementById( "workflowStage" ).value = inStage;
	document.getElementById( "shiftingType" ).value = inWorkflowType;
	var flag_deviceChangeOption82 = true;
	var custId = $( '#customerId' ).val();
	var workflowId = $( '#workflowId' ).val();
	if ( requestType == 'workflow' ) {
		var remarks = $( '#ifrRemarks' ).val();
		$( '#ifrRemarks ~ font' ).addClass( 'hidden' );
		$( '#ifrRemarks ~ span' ).addClass( 'hidden' );
		if ( remarks.trim() == '' || remarks == null || remarks == undefined ) {
			$( '#ifrRemarks ~ font' ).removeClass( 'hidden' );
			valid = false;
			return;
		}
		else if ( remarks.trim().length < 2 || remarks.trim().length > 4000 ) {
			$( '#ifrRemarks ~ span' ).removeClass( 'hidden' );
			valid = false;
			return;
		}
	}
	$( '#IDqrcNewOption82' ).next( 'font' ).hide();
	$( '#IDqrcNewOption82show' ).next( 'font' ).hide();
	if ( product != 'BB' ) {
		if ( $( '#newOption82EOC' ).val() == '' ) {
			flag_deviceChangeOption82 = false;
			document.getElementById( "errorNasport" ).innerHTML = "Please click on the text box and generate NAS Port ID";
		}
		else if ( $( '#newOption82EOC' ).val() == $( '#oldOption82EOC' ).val() ) {
			flag_deviceChangeOption82 = false;
			document.getElementById( "errorNasport" ).innerHTML = "Old and New NAS Port ID should be different";
		}
		else {
			if ( $( '#IDqrcNewOption82' ).val() == '' ) {
				flag_deviceChangeOption82 = false;
				document.getElementById( "errorNasport" ).innerHTML = "Please provide Option 82";
			}
			else if ( $( '#IDqrcNewOption82' ).val() == $( '#IDqrcNewOption82OId' ).val() ) {
				document.getElementById( "errorNasport" ).innerHTML = "Old and New Option82 strings must be different";
			}
		}
	}
	if ( flag_deviceChangeOption82 ) {
		if ( product == 'BB' ) {
			reply = confirm( "Do you want to save new Option82 ?" );
		}
		if ( product != 'BB' ) {
			reply = confirm( "Do you want to save new NAS Port ID ?" );
		}
		if ( reply && requestType == 'workflow' ) {
			$( '.loadingPopup' ).removeClass( 'hidden' );
			document.forms[ 1 ].action = "shiftingWorkflow.do?method=bindOption82&inRequestType=" + requestType + "&customerId=" + custId;
			document.forms[ 1 ].submit();
		}
	}
}

function fillAddressLines( param ){
	var stateName = document.getElementById( "feasibleState" ).value;
	var cityName = document.getElementById( "feasibleCity" ).value;
	var areaName = document.getElementById( "feasiblArea" ).value;
	var societyName = document.getElementById( "feasibleSociety" ).value;
	if ( param == 'state' ) {
		parent.document.getElementById( "instAddLine1" ).value = "";
		parent.document.getElementById( "instAddLine2" ).value = "";
		parent.document.getElementById( "instAddLine3" ).value = ", " + stateName;
	}
	else if ( param == 'city' ) {
		parent.document.getElementById( "instAddLine1" ).value = "";
		parent.document.getElementById( "instAddLine2" ).value = "";
		parent.document.getElementById( "instAddLine3" ).value = cityName + ", " + stateName;
	}
	else if ( param == 'area' ) {
		parent.document.getElementById( "instAddLine1" ).value = "";
		parent.document.getElementById( "instAddLine2" ).value = ", " + areaName;
		// parent.document.getElementById( "instAddLine3" ).value = cityName + ", " + stateName;
	}
	else if ( param == 'society' ) {
		var values = societyName.split( "-" );
		if ( values != 0 ) {
			var localityName = values[ 0 ];
			var societyName = values[ 1 ];
			parent.document.getElementById( "instAddLine2" ).value = localityName + ", " + areaName;
			if ( societyName != undefined ) {
				parent.document.getElementById( "instAddLine1" ).value = "," + societyName;
			}
			else {
				parent.document.getElementById( "instAddLine1" ).value = "";
			}
		}
		else {
			parent.document.getElementById( "instAddLine2" ).value = "" + ", " + areaName;
			parent.document.getElementById( "instAddLine1" ).value = "";
		}
	}
}
function validateIPAddrs( ipObject ){
	$( "[name='" + ipObject.name + "']" ).next( 'font' ).addClass( 'hidden' );
	if ( !validateIP( ipObject.value ) ) {
		$( "[name='" + ipObject.name + "']" ).next( 'font' ).show().html( "Please enter  valid 'Static IP'" ).removeClass( 'hidden' ).addClass(
				'errorTextbox' ).css( 'top', 45 ).css( 'width', 220 ).css( 'left', 42 );
		return false;
	}
	return true;

}
function modifyFollowUpDate(){
	$( '#remarks ~ font' ).addClass( 'hidden' );
	$( '#remarks ~ span' ).addClass( 'hidden' );
	var remarks = document.getElementById( 'remarks' ).value.trim();
	var response = true;

	if ( remarks.trim() == '' || remarks == '0' || remarks == null || remarks == undefined ) {
		$( '#remarks ~ font' ).removeClass( 'hidden' );

		response = false;
	}
	else if ( remarks.trim().length < 2 || remarks.trim().length > 4000 ) {
		$( '#remarks ~ span' ).removeClass( 'hidden' );
		response = false;
		return;
	}
	if ( response ) {
		var reply = confirm( "Do you want to modify this ticket ?" );
		if ( reply ) {
			document.forms[ 1 ].action = "manageQrc.do?method=editServiceRequest&param=true";
			document.forms[ 1 ].submit();
		}
	}
}
var subCats = [];
var subSubCats = [];
function resetTicketAutocomplete( subCat, subSubCat ){
	if ( subCat ) {
		subCats = [];
		$( '#qrcSubCategoryTextId' ).autocomplete( 'option', {
			source : subCats
		} );
		document.getElementById( 'qrcSubCategoryTextId' ).value = '';
	}
	if ( subSubCat ) {
		subSubCats = [];
		$( '#qrcSubSubCategoryTextId' ).autocomplete( 'option', {
			source : subSubCats
		} );
		document.getElementById( 'qrcSubSubCategoryTextId' ).value = '';
	}
}
function resetEditTicketAutocomplete( subCat, subSubCat ){
	if ( subCat ) {
		subCats = [];
		$( '#rcaLsit' ).autocomplete( 'option', {
			source : subCats
		} );
		document.getElementById( 'rcaLsit' ).value = '';
	}
	if ( subSubCat ) {
		subSubCats = [];
		$( '#rcaReasonList' ).autocomplete( 'option', {
			source : subSubCats
		} );
		document.getElementById( 'rcaReasonList' ).value = '';
	}
}
function fillSubSubCategories( catId, subCatId ){
	crmDwr.filterSubSubCategories( catId, subCatId, "", function( subSubCategoryList ){
		subSubCats = [];
		if ( subSubCategoryList != null ) {
			var subSubCat;
			subSubCategoryList.forEach( function( e ){
				subSubCat = {};
				subSubCat[ 'label' ] = e.qrcSubSubCategory;
				subSubCat[ 'value' ] = e.qrcSubSubCategoryId;
				subSubCats.push( subSubCat );
			} );
		}
		$( '#qrcSubSubCategoryTextId' ).autocomplete( {
			source : subSubCats, minLength : 0, change : function( e, ui ){
				$( '#subSubCategoryId' ).val( '' );
				if ( !ui.item ) {
					$( e.target ).val( '' );
				}
				else {
					$( '#subSubCategoryId' ).val( ui.item.value );
					populateActions( 'resolutionType', ui.item.value );
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
function change_TicketStatus( inRecordID ){
	var answer = confirm( "Please confirm if you want to change ticket status?" );
	if ( answer ) {
		document.forms[ 1 ].action = "manageQrc.do?method=reopenTicket&ticketID=" + inRecordID;
		document.forms[ 1 ].submit();
	}
}
// function filterSubCategory( subCategory ){
// $( '#qrcSubSubCategoryTextId' ).val( '' );
// crmDwr.filterSubCategories( subCategory, function( subCategoryList ){
// var id = document.getElementById( "subCategoryId" );
// if ( subCategoryList != null ) {
// dwr.util.removeAllOptions( id );
// document.getElementById( 'showSubCategoryList' ).style.display = "block";
// dwr.util.addOptions( id, subCategoryList, "qrcSubCategoryId", "qrcSubCategory" );
// }
// } );
//
// $( '#subCategoryError' ).addClass( 'hidden' );
// }
// function filterSubSubCategory( subSubCategoryValue ){
// var subCategoryId = $( '#subCategoryId :selected' ).val();
// var categoryId = document.getElementById( "categoryId" ).value;
// if ( categoryId != null && categoryId != "" && categoryId != "0" ) {
// if ( subCategoryId != null && subCategoryId != "" && subCategoryId != "0" ) {
// crmDwr.filterSubSubCategories( categoryId, subCategoryId, subSubCategoryValue, function( subSubCategoryList ){
// var id = document.getElementById( "subSubCategoryId" );
// if ( subSubCategoryList != null ) {
// dwr.util.removeAllOptions( id );
// document.getElementById( 'showSubSubCategoryList' ).style.display = "block";
// dwr.util.addOptions( id, subSubCategoryList, "qrcSubSubCategoryId", "qrcSubSubCategory" );
// }
// } );
// }
// }
// else {
// alert( "Please Enter Sub Category" );
// }
// $( '#subSubCategoryError' ).addClass( 'hidden' );
// }
// function fillCategoryHiddenField(){
// $( '#categoryId' ).val( '' );
// var subCategoryListId = $( '#subCategoryId :selected' ).val();
// if ( subCategoryListId != null && subCategoryListId != '0' && subCategoryListId != "" ) {
// crmDwr.getCategoryIdBySubcategory( subCategoryListId, function( categoryId ){
// if ( categoryId != null && categoryId != "0" ) {
// $( '#categoryId' ).val( categoryId );
// }
// } );
// }
//
// }
function fillTextList( filterId, textId, inListType ){
	var selectedValue = $( '#' + filterId ).find( 'option:selected' ).text();
	var textElement = document.getElementById( textId );
	textElement.value = selectedValue;
	document.getElementById( inListType ).style.display = "none";
	textElement.focus();
}

function resetTicketSearchAutocomplete( subCat, subSubCat ){
	if ( subCat ) {
		subCats = [];
		$( '#subCategoryIdSearch' ).autocomplete( 'option', {
			source : subCats
		} );
		document.getElementById( 'subCategoryIdSearch' ).value = '';
	}
	if ( subSubCat ) {
		subSubCats = [];
		$( '#subSubCategoryIdSearch' ).autocomplete( 'option', {
			source : subSubCats
		} );
		document.getElementById( 'subSubCategoryIdSearch' ).value = '';
	}
}

function populateSubSubCategories( inCategoryId, inDestinationID, inSubCategoryId ){
	$( '.rcaResolutionCodeDropdowns' ).addClass( 'hidden' );
	var process = true;
	$( '.loadingPopup' ).removeClass( 'hidden' );
	if ( inSubCategoryId != 'Please Select' ) {
		var categoryID = document.getElementById( inCategoryId ).value;
		var qrcType = "";
		if ( $( '[name="qrcType"]:checked' ).val() != undefined ) {
			qrcType = $( '[name="qrcType"]:checked' ).val();
			if ( qrcType == undefined ) {
				$( '#qrcTypeComplaint' ).parent().next( 'font' ).show().html( "Please select QRC Type" ).addClass( 'errorTextbox' );
				process = false;
			}
			else {
				$( '#qrcTypeComplaint' ).parent().next( 'font' ).hide();
			}
		}
		if ( process ) {
			crmDwr.getActiveQrcSubSubCategoriesByType( qrcType, categoryID, inSubCategoryId, function( list ){
				addSubSubCategories( inDestinationID, list );
			} );
		}
		function addSubSubCategories( id, list ){
			var select = document.getElementById( id );
			if ( select != null ) {
				if ( list != null ) {
					dwr.util.removeAllOptions( id );
					dwr.util.addOptions( id, [
						"Please Select"
					] );
					if ( list != null ) {
						dwr.util.addOptions( id, list, "qrcSubSubCategoryId", "qrcSubSubCategory" );
						// $('#'+id).removeAttr('disabled');
					}
				}
				else {
					alert( "Sub Sub Catogory List is empty for current selected Sub Category." );

					removeList( id );
				}
			}
		}

		$( '.loadingPopup' ).addClass( 'hidden' );
	}
	else {
		$( '.loadingPopup' ).addClass( 'hidden' );
		dwr.util.removeAllOptions( inDestinationID );
		dwr.util.addOptions( inDestinationID, [
			"Please Select"
		] );
	}
}

function populateSubCategories( inDestinationID, inAccessID ){

	$( '.rcaResolutionCodeDropdowns' ).addClass( 'hidden' );
	if ( inAccessID != '0' ) {
		crmDwr.getActiveQrcSubCategories( inAccessID, function( list ){
			addSubCategories( inDestinationID, list );
		} );

		function addSubCategories( id, list ){
			var select = document.getElementById( id );
			if ( select != null ) {
				if ( list != null ) {
					dwr.util.removeAllOptions( id );
					dwr.util.addOptions( id, [
						"Please Select"
					] );
					if ( list != null ) {
						dwr.util.addOptions( id, list, "qrcSubCategoryId", "qrcSubCategory" );
						// $('#'+id).removeAttr('disabled');
					}
					else {
						alert( "No SubCategory register for given category." );
					}
				}
				else {
					alert( "Sub Catogory List is empty for current selected Category." );

					removeList( id );
				}
			}
		}

	}
	$( '.loadingPopup' ).addClass( 'hidden' );

}

function submitCSDAddressShifting(){
	var valid = true;
	var requestType = "forward";
	var res = $( 'input[name=customerResponse]:checked' ).val();

	if ( res == "N" ) {
		requestType = "close";
		var closer_reason_Id = document.getElementById( "closer_reason_Id" ).value;
		if ( closer_reason_Id == '' || closer_reason_Id == '0' || closer_reason_Id == null || closer_reason_Id == undefined ) {
			$( '#closer_reason_Id' ).next( 'font' ).show().html( "Please select 'Closer Reason'" ).addClass( 'errorTextbox' );
			valid = false;
		}
		else {
			$( '#closer_reason_Id' ).next( 'font' ).hide().html( "Please select 'Closer Reason'" ).removeClass( 'errorTextbox' );
			valid = true;
		}
	}
	else {
		var planType = $( 'input[name=planTypeShifting]:checked' ).val();
		if ( planType == '' || planType == null || planType == undefined ) {
			$( '#planTypeShiftingID' ).next( 'font' ).show().html( "Please select 'Plan Type'" ).addClass( 'errorTextbox' );
			valid = false;
		}
		else {
			$( '#planTypeShiftingID' ).next( 'font' ).hide().html( "Please select 'Plan Type'" ).removeClass( 'errorTextbox' );
			var basePlan = $( '#basePlanNameCRF' ).val();
			if ( basePlan == '' || basePlan == null || basePlan == 'Select Base Plan Name' ) {
				$( '#basePlanCodeID' ).next( 'font' ).show().html( "Please select 'Base Plan'" ).addClass( 'errorTextbox' );
				valid = false;
			}
			else {
				$( '#basePlanCodeID' ).next( 'font' ).hide().html( "Please select 'Base Plan'" ).removeClass( 'errorTextbox' );
				valid = true;
			}
		}

	}
	if ( valid ) {
		var answer = confirm( "Please confirm if you want to " + requestType + " the docket" );
		if ( answer ) {
			$( '.loadingPopup' ).removeClass( 'hidden' );
			document.forms[ 1 ].action = "shiftingWorkflow.do?method=submitcsdStage";
			document.forms[ 1 ].submit();
		}
	}
}

function submitftLevel2(){
	var valid = true;
	var requestType = "forward";
	document.getElementById( "errorID" ).innerHTML = "";
	var remarks = document.getElementById( "csdRemarks" ).value;
	var netPartner = $( '#op_npName' ).val();
	var netType = $( 'input[name=shiftingWorkflowPojo.product]:checked' ).val();
	// var netTypes = document.getElementById( "netsype" ).value;
	if ( netPartner == "" || netPartner == null || netPartner == undefined ) {
		$( '#op_npName' ).next( 'font' ).show().html( "Please select Network Partner" ).addClass( 'errorTextbox' );
		valid = false;
	}
	if ( netType == "" || netType == null || netType == undefined ) {
		document.getElementById( "errorID" ).innerHTML = "<span style='color:#e70000;'>" + "<br/>Please select Network Type." + "</span>";
		valid = false;
	}

	if ( remarks == "" || remarks == null || remarks == undefined ) {
		$( '#csdRemarks' ).next( 'font' ).show().html( "Please enter Remarks" ).addClass( 'errorRemarks' );
		valid = false;
	}
	else if ( remarks.trim().length < 2 || remarks.trim().length > 4000 ) {
		$( '#csdRemarks' ).next( 'font' ).show().html( "Please enter Remarks between [2-4000]." ).addClass( 'errorRemarks' );
		valid = false;
	}
	if ( valid ) {
		var answer = confirm( "Please confirm if you want to " + requestType + " the docket" );
		if ( answer ) {
			$( '.loadingPopup' ).removeClass( 'hidden' );
			document.forms[ 1 ].action = "shiftingWorkflow.do?method=submitftLevel2";
			document.forms[ 1 ].submit();
		}
	}
}

function submitIfrEOCL1(){
	var valid = true;
	var requestType = "forward";
	var remarks = document.getElementById( "csdRemarks" ).value;
	/*
	 * var product = $( '#product' ).val(); var cpe = $( 'input[name="shiftingWorkflowPojo.cpeAvailable"]:checked' ).val(); var installation = $( 'input[name="shiftingWorkflowPojo.physicalInstallation"]:checked' ).val(); var refusal = $( 'input[name="shiftingWorkflowPojo.customerRefusal"]:checked'
	 * ).val(); if ( ( cpe == 'Y' || cpe == undefined ) && installation == 'Y' && refusal == 'N' ) { if ( product == 'EOC' ) { var nassPortId = $( '#IDnasportID' ).val(); if ( nassPortId == "" || nassPortId == null || nassPortId == undefined ) { $( '#IDnasportID' ).next( 'font' ).show().html(
	 * "Please click on link and select Master Name" ).addClass( 'errorRemarks' ); valid = false; } else { $( '#IDnasportID' ).next( 'font' ).addClass( 'hidden' ); valid = true; }
	 *  } else { var option82 = $( '#option82Ids' ).val(); if ( option82 == "" || option82 == null || option82 == undefined ) { $( '#option82ID' ).next( 'font' ).show().html( "Please enter Option82" ).addClass( 'errorRemarks' ); valid = false; } else { $( '#option82ID' ).next( 'font' ).addClass(
	 * 'hidden' ); valid = true; } } }
	 */
	if ( remarks == "" || remarks == null || remarks == undefined ) {
		$( '#csdRemarks' ).next( 'font' ).show().html( "Please enter Remarks" ).addClass( 'errorRemarks' );
		valid = false;

	}
	else if ( remarks.trim().length < 2 || remarks.trim().length > 4000 ) {
		$( '#csdRemarks' ).next( 'font' ).show().html( "Please enter Remarks between [2-4000]." ).addClass( 'errorRemarks' );
		valid = false;
	}

	if ( valid ) {
		var answer = confirm( "Please confirm if you want to " + requestType + " the docket" );
		if ( answer ) {
			$( '.loadingPopup' ).removeClass( 'hidden' );
			document.forms[ 1 ].action = "shiftingWorkflow.do?method=submitIfrEOCL1";
			document.forms[ 1 ].submit();
		}
	}
}
function submitCSDLevel3(){
	var valid = true;
	var requestType = "forward";
	var res = $( 'input[name=customerResponse]:checked' ).val();
	var remarks = document.getElementById( "csdl3Remarks" ).value;
	if ( res == "N" ) {
		requestType = "close";
		var closer_reason_Id = document.getElementById( "closer_reason_Id" ).value;
		if ( closer_reason_Id == '' || closer_reason_Id == '0' || closer_reason_Id == null || closer_reason_Id == undefined ) {
			$( '#closer_reason_Id' ).next( 'font' ).show().html( "Please enter Reason" ).addClass( 'errorTextbox' );
			valid = false;
		}
		else {
			$( '#closer_reason_Id' ).next( 'font' ).hide().html( "Please enter Reason" ).removeClass( 'errorTextbox' );
			valid = true;
		}

	}
	if ( remarks == "" || remarks == null || remarks == undefined ) {
		$( '#csdl3Remarks' ).next( 'font' ).show().html( "Please enter Remarks" ).addClass( 'errorRemarks' );
		valid = false;

	}
	else if ( remarks.trim().length < 2 || remarks.trim().length > 4000 ) {
		$( '#csdl3Remarks' ).next( 'font' ).show().html( "Please enter Remarks between [2-4000]." ).addClass( 'errorRemarks' );
		valid = false;
	}
	if ( valid ) {
		var answer = confirm( "Please confirm if you want to " + requestType + " the docket" );
		if ( answer ) {
			$( '.loadingPopup' ).removeClass( 'hidden' );
			document.forms[ 1 ].action = "shiftingWorkflow.do?method=submitCSDLevel3";
			document.forms[ 1 ].submit();
		}
	}
}

function submitNpLevel1(){
	var valid = true;
	var requestType = "forward";
	var product = $( '#product' ).val();
	var remarks = document.getElementById( "csdl3Remarks" ).value;
	if ( product == 'BB' ) {
		var opt82 = $( '#option82Ids' ).val();
		var macAdd = $( '#cpemacid' ).val();
	}
	if ( product == 'EOC' ) {
		var primaryMacId = $( '#primaryMACAddrId' ).val();
		var secMacId = $( '#secondaryMacTextId' ).val();
		var naspId = document.getElementById( "IDnasportID" ).value;
	}
	if ( product == 'EOC' ) {
		if ( secMacId == "" || secMacId == null || secMacId == undefined ) {
			$( '#secondaryMacTextId' ).next( 'font' ).show().html( "Please enter Secondary MAC-ID " ).addClass( 'errorTextbox' );
			valid = false;
		}
		else if ( naspId == "" || naspId == null || naspId == undefined ) {
			$( '#IDnasportID' ).next( 'font' ).show().html( "Please enter Nas Port-ID " ).addClass( 'errorTextbox' );
			valid = false;
		}
	}
	if ( product == 'BB' ) {
		if ( macAdd == "" || macAdd == null || macAdd == undefined ) {
			$( '#cpemacid' ).next( 'font' ).show().html( "Please enter MAC Address " ).addClass( 'errorTextbox' );
			valid = false;
		}
		else if ( opt82 == "" || opt82 == null || opt82 == undefined ) {
			$( '#option82Ids' ).next( 'font' ).show().html( "Please enter Option82 " ).addClass( 'errorTextbox' );
			valid = false;
		}
	}
	if ( remarks == "" || remarks == null || remarks == undefined ) {
		$( '#csdl3Remarks' ).next( 'font' ).show().html( "Please enter Remarks" ).addClass( 'errorRemarks' );
		valid = false;
	}
	else if ( remarks.trim().length < 2 || remarks.trim().length > 4000 ) {
		$( '#csdl3Remarks' ).next( 'font' ).show().html( "Please enter Remarks between [2-4000]." ).addClass( 'errorRemarks' );
		valid = false;
	}
	if ( valid ) {
		var answer = confirm( "Please confirm if you want to " + requestType + " the docket" );
		if ( answer ) {
			$( '.loadingPopup' ).removeClass( 'hidden' );
			document.forms[ 1 ].action = "shiftingWorkflow.do?method=submitNpLevel1";
			document.forms[ 1 ].submit();
		}
	}
}

function submitNOCL1(){
	var valid = true;
	var requestType = "forward";
	var remarks = document.getElementById( "ifrRemarks" ).value;
	/*
	 * var product = $( '#product' ).val(); if ( product == 'EOC' ) { var primaryMac = $( '#primaryMACAddrId' ).val(); var secondaryMac = $( '#secondaryMacTextId' ).val(); if ( primaryMac == "" || primaryMac == null || primaryMac == undefined ) { $( '#primaryMACAddrId' ).next( 'font' ).show().html(
	 * "Please enter Primary Mac Address" ).addClass( 'errorRemarks' ); valid = false; } else { $( '#primaryMACAddrId' ).next( 'font' ).addClass( 'hidden' ); valid = true; } if ( secondaryMac == "" || secondaryMac == null || secondaryMac == undefined ) { $( '#secondaryMacTextId' ).next( 'font'
	 * ).show().html( "Please enter Secondary Mac Address" ).addClass( 'errorRemarks' ); valid = false; } else { $( '#secondaryMacTextId' ).next( 'font' ).addClass( 'hidden' ); valid = true; } } else { var cpeMacId = $( '#IDqrcNewPriMacIdBB' ).val(); if ( cpeMacId == "" || cpeMacId == null ||
	 * cpeMacId == undefined ) { $( '#IDqrcNewPriMacIdBB' ).next( 'font' ).show().html( "Please enter Mac Address" ).addClass( 'errorRemarks' ); valid = false; } else { $( '#IDqrcNewPriMacIdBB' ).next( 'font' ).addClass( 'hidden' ); valid = true; } }
	 */
	if ( remarks == "" || remarks == null || remarks == undefined ) {
		$( '#ifrRemarks' ).next( 'font' ).show().html( "Please enter Remarks" ).addClass( 'errorRemarks' );
		valid = false;

	}
	else if ( remarks.trim().length < 2 || remarks.trim().length > 4000 ) {
		$( '#ifrRemarks' ).next( 'font' ).show().html( "Please enter Remarks between [2-4000]." ).addClass( 'errorRemarks' );
		valid = false;
	}
	if ( valid ) {
		var answer = confirm( "Please confirm if you want to " + requestType + " the docket" );
		if ( answer ) {
			$( '.loadingPopup' ).removeClass( 'hidden' );
			document.forms[ 1 ].action = "shiftingWorkflow.do?method=submitNOCLevel1";
			document.forms[ 1 ].submit();
		}
	}

}
function getProductsforSocietyNP( npId, societyId, areaId, styleId ){
	if ( npId != '' && npId != null && npId != undefined ) {
		crmDwr.getProductByNPNSociety( npId, societyId, areaId, function( list ){
			addProductsforSocietyNP( styleId, list );
		} );
		function addProductsforSocietyNP( styleId, list ){

			if ( list != null ) {

				var txt = "<label><strong>Available Network Type:</strong></label>";

				list.forEach( function( e ){
					txt = txt + "<input type='radio' name='shiftingWorkflowPojo.product' id='nettype' value='" + e.contentValue
							+ "' checked='checked'>" + e.contentName + "  <font></font>";
				} );
				document.getElementById( styleId ).innerHTML = txt;
			}
			else {
				alert( "No Product registered in system." );
			}
		}
	}
	else {
		document.getElementById( styleId ).innerHTML = "";
	}
}

function getTeriffMigrationPlanForCSD(){
	$( '.loadingPopup' ).removeClass( 'hidden' );
	document.forms[ 1 ].action = "shiftingWorkflow.do?method=getCustomerPlan";
	document.forms[ 1 ].submit();
}

function customerFeedbackPopUp(){
	var custId = $( '#hCustId' ).val();
	var submittedBy = $( '#hCRMUserId' ).val();
	window.open( 'https://timbl.co.in/customer-survey-feedback?cid=' + custId + '&sub=' + submittedBy, '_blank' );
}