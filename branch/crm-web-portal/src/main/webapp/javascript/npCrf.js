var validateArrayForOption82 = [];
$( document )
		.ready(
				function(){
					// / validation crf entry
					$( '#ISRDate' ).removeClass( 'gray_text' );
					// $( '#disPlayDate' ).removeClass( 'gray_text' );
					$( '#disPlayChkDate' ).removeClass( 'gray_text' );
					// $( '#disPlayOnlineDate' ).removeClass( 'gray_text' );
					var payMode = $( '[name="telecommunicationPayment.paymentMode"]:checked' ).val();
					if ( payMode != null ) {
						viewPaymentMode();
					}
					$( '[name="remarksPojo.actions"]' ).bind(
							'change',
							function(){
								if ( $( '#NPActionTypeId' ).val() == "" ) {
									$( '#NPRefusalDivIdComment' ).addClass( 'hide1' );
									$( '#DIVrejectionReason' ).addClass( 'hide1' );
									$( '#yesCustomerRefuseReason' ).addClass( 'hide1' );
									$( '#npCancellationDivIdComment' ).addClass( 'hide1' );
									$( '#DiverpComment' ).addClass( 'hide1' );

									$( '#NPActionTypeId' ).parent().next( 'font' ).show().html( "Please Select 'Action Type'" ).addClass(
											'errorTextbox' ).css( 'top', 60 );
								}
								else if ( $( '#NPActionTypeId' ).val() == "submitRefusal" ) {
									// whatk
									$( '#npCancellationDivIdComment' ).addClass( 'hide1' );
									$( '#NPActionTypeId' ).parent().next( 'font' ).hide();
									$( '#DIVrejectionReason' ).addClass( 'hide1' );
									$( '#yesCustomerRefuseReason' ).removeClass( 'hide1' );
									$( '#DiverpComment' ).addClass( 'hide1' );
								}
								else if ( $( '#NPActionTypeId' ).val() == "Approve" ) {
									$( '#npCancellationDivIdComment' ).addClass( 'hide1' );
									$( '#DIVrejectionReason' ).addClass( 'hide1' );
									$( '#yesCustomerRefuseReason' ).addClass( 'hide1' );
									$( '#NPRefusalDivIdComment' ).addClass( 'hide1' );
									$( '#DiverpComment' ).addClass( 'hide1' );
								}
								// backRoute==Reject
								else if ( $( '#NPActionTypeId' ).val() == "Reject By NP" ) {
									$( '#npCancellationDivIdComment' ).addClass( 'hide1' );
									$( '#yesCustomerRefuseReason' ).addClass( 'hide1' );
									$( '#DIVrejectionReason' ).removeClass( 'hide1' );
									$( '#NPRefusalDivIdComment' ).removeClass( 'hide1' );
									$( '#DiverpComment' ).addClass( 'hide1' );
								}
								else if ( $( '#NPActionTypeId' ).val() == "Cancellation" ) {
									$( '#npCancellationDivIdComment' ).removeClass( 'hide1' );
									$( '#yesCustomerRefuseReason' ).addClass( 'hide1' );
									$( '#DIVrejectionReason' ).addClass( 'hide1' );
									$( '#NPRefusalDivIdComment' ).removeClass( 'hide1' );
									$( '#DiverpComment' ).addClass( 'hide1' );
								}
								else if ( $( '#NPActionTypeId' ).val() == "ERP" ) {
									$( '#DiverpComment' ).removeClass( 'hide1' );
									$( '#npCancellationDivIdComment' ).addClass( 'hide1' );
									$( '#yesCustomerRefuseReason' ).addClass( 'hide1' );
									$( '#DIVrejectionReason' ).addClass( 'hide1' );
									$( '#NPRefusalDivIdComment' ).add( 'hide1' );

								}
								else if ( $( '#NPActionTypeId' ).val() == "Change Feasible Address" ) {
									$( '#npCancellationDivIdComment' ).addClass( 'hide1' );
									$( '#NPActionTypeId' ).parent().next( 'font' ).hide();
									$( '#DIVrejectionReason' ).addClass( 'hide1' );
									$( '#yesCustomerRefuseReason' ).addClass( 'hide1' );
									$( '#DiverpComment' ).addClass( 'hide1' );

								}
							} );

					$( 'input[name="actionValidationCRF"]' ).bind( 'change', function(){
						if ( $( '#approveValidationCRF' ).is( ':checked' ) ) {

							$( '#approveValidationCRFShow' ).removeClass( 'hide1' );
							$( '#approveValidationCRFShow select' ).removeAttr( 'disabled' );
							$( '#instAddressShow' ).addClass( 'hide1' );
							$( '#rejectValidationCRFShow' ).addClass( 'hide1' );
							$( '#rejectValidationCRFShow select' ).attr( 'disabled', 'disabled' );
						}
						else if ( $( '#rejectValidationCRF' ).is( ':checked' ) ) {
							$( '#rejectValidationCRFShow' ).removeClass( 'hide1' );
							$( '#rejectValidationCRFShow select' ).removeAttr( 'disabled' );

							$( '#approveValidationCRFShow' ).addClass( 'hide1' );
							$( '#approveValidationCRFShow select' ).attr( 'disabled', 'disabled' );
							$( '#instAddressShow' ).addClass( 'hide1' );
						}
						else if ( $( '#changeFeasibleAddId' ).is( ':checked' ) ) {
							$( '#instAddressShow' ).removeClass( 'hide1' );
							$( '#rejectValidationCRFShow' ).addClass( 'hide1' );
							$( '#rejectValidationCRFShow select' ).attr( 'disabled', 'disabled' );

							$( '#approveValidationCRFShow' ).addClass( 'hide1' );
							$( '#approveValidationCRFShow select' ).attr( 'disabled', 'disabled' );
						}
					} );

					$( "#validationCRFEntry" ).validate( {
						rules : {
							actionValidationCRF : {
								required : true
							}, commentsValidationCRF : {
								maxlength : 128
							}, crfReferenceNoValidation : {
								required : true
							}, npValidationCRF : {
								dropDown : true
							}, rejectionReasonValidationCRF : {
								dropDown : true
							}
						}, messages : {
							actionValidationCRF : {
								required : "<font class='errorTextbox'> Please insert</font>"
							}, commentsValidationCRF : {
								maxlength : "<font class='errorTextbox'> Please insert no more than 128 characters</font>"
							}, crfReferenceNoValidation : {
								required : "<font class='errorTextbox'> Please insert</font>"
							}, npValidationCRF : {
								dropDown : "<font class='errorTextbox'>Please select</font>"
							}, rejectionReasonValidationCRF : {
								dropDown : "<font class='errorTextbox'>Please select</font>"
							}
						}
					} );

					$( '#submit_validationCRFEntry' ).click(
							function(){
								if ( $( '#rejectValidationCRF' ).is( ':checked' ) ) {
									if ( $( 'textarea[name="commentsValidationCRF"]' ).val() == "" ) {
										$( '#errorCommentsValidationCRF' ).html( '<br/>Please insert your reason for rejection' ).addClass(
												'serverSideMessage error_message' );
										return false;
									}
									else {
										$( '#errorCommentsValidationCRF' ).hide();
									}
								}
								if ( $( "#validationCRFEntry" ).valid() ) {
									document.forms[ 1 ].action = "user-management-create-user.html";
									document.forms[ 1 ].submit();
								}
							} );

					// Network Inventory Detail
					$( 'input[name="crmNetworkConfigurations.serviceModel"]' ).bind( 'change', function(){
						if ( $( '#actionFtthModel' ).is( ':checked' ) ) {
							$( '#onFtth' ).removeClass( 'hide1' );
							$( '#onFttb' ).addClass( 'hide1' );
						}
						else if ( $( '#actionFttbModel' ).is( ':checked' ) ) {
							$( '#onFttb' ).removeClass( 'hide1' );
							$( '#onFtth' ).addClass( 'hide1' );
						}
					} );

					$.validator.addMethod( 'crmNetworkConfigurations.oltNoteId', function( value, element ){
						return this.optional( element ) || validateOltNodeId( value );
					} );

					$.validator.addMethod( 'telecommunicationPayment.bankBranch', function( value, element ){
						return this.optional( element ) || validateTextRegex_2_45( value );
					} );
					$.validator.addMethod( "alphabetOrNumber", function( value, element ){
						return this.optional( element ) || validateAlphabetOrNumber( value );

					} );
					$.validator.addMethod( 'paidAmount', function( value, element ){
						return this.optional( element ) || checkPaidAmount( value );
					} );
					$.validator.addMethod( 'notZero', function( value, element ){
						return this.optional( element ) || checkForZero( value );
					} );

					$( "#networkInventoryDetail" ).validate( {
						rules : {
							'crmNetworkConfigurations.serviceModel' : {
								required : false
							},

							'oltType' : {
								dropDown : true
							}
						}, messages : {
							'crmNetworkConfigurations.serviceModel' : {
								required : "<font class='errorRadio'> Please select 'Service Model'</font>"
							}, 'oltType' : {
								dropDown : "<font class='errorTextbox'> Please select 'Olt Type'</font>"
							}
						}
					} );
					$( '#submit_networkInventoryDetail' ).click( function(){
						var product = window.opener.document.getElementById( "hiddenProduct" ).value;
						if ( product != "BB" ) {
							if ( validValueForMaster() ) {
								setValueForMaster();
							}
						} /*
							 * else if ($("#networkInventoryDetail").valid() && validateOption82Configuration()) { setValueForNetworkDetails(); }
							 */
					} );

					// Edit CPE Status
					$( 'input[name="telecommunicationPayment.paymentMode"]' )
							.bind(
									'change',
									function(){
										if ( $( '#ModePaymentReceiverCash' ).is( ':checked' ) ) {
											$( '#ModePaymentReceiverCashShow' ).removeClass( 'hide1' );
											$( '#ModePaymentReceiverDDShow, #ModePaymentReceiverOnlineShow' ).addClass( 'hide1' );
											$( '#ModePaymentReceiverCashShow select, #ModePaymentReceiverCashShow input' ).removeAttr( 'disabled' );
											$(
													'#ModePaymentReceiverDDShow select, #ModePaymentReceiverDDShow input, #ModePaymentReceiverOnlineShow select, #ModePaymentReceiverOnlineShow input' )
													.attr( 'disabled', 'disabled' );
										}
										else if ( $( '#ModePaymentReceiverDD' ).is( ':checked' ) ) {
											$( '#ModePaymentReceiverDDShow' ).removeClass( 'hide1' );
											$( '#ModePaymentReceiverCashShow, #ModePaymentReceiverOnlineShow' ).addClass( 'hide1' );
											$( '#ModePaymentReceiverDDShow select, #ModePaymentReceiverDDShow input' ).removeAttr( 'disabled' );
											$(
													'#ModePaymentReceiverCashShow select, #ModePaymentReceiverCashShow input, #ModePaymentReceiverOnlineShow select, #ModePaymentReceiverOnlineShow input' )
													.attr( 'disabled', 'disabled' );

										}
										else if ( $( '#ModePaymentReceiverOnline' ).is( ':checked' ) ) {
											$( '#ModePaymentReceiverOnlineShow' ).removeClass( 'hide1' );
											$( '#ModePaymentReceiverCashShow, #ModePaymentReceiverDDShow' ).addClass( 'hide1' );
											$( '#ModePaymentReceiverOnlineShow select, #ModePaymentReceiverOnlineShow input' )
													.removeAttr( 'disabled' );
											$(
													'#ModePaymentReceiverCashShow select, #ModePaymentReceiverCashShow input, #ModePaymentReceiverDDShow select, #ModePaymentReceiverDDShow input' )
													.attr( 'disabled', 'disabled' );
										}
									} );

					$( 'input[name="orderDetailsPojo.receiverRequired"]' ).bind( 'change', function(){
						if ( $( '#WifiReceiverRequiredYes' ).is( ':checked' ) ) {
							$( '#WifiReceiverRequiredYesShow' ).removeClass( 'hide1' );
							$( '#WifiReceiverRequiredYesShow select, #WifiReceiverRequiredYesShow input' ).removeAttr( 'disabled' );
						}
						else if ( $( '#WifiReceiverRequiredNo' ).is( ':checked' ) ) {
							$( '#WifiReceiverRequiredYesShow' ).addClass( 'hide1' );
							$( '#WifiReceiverRequiredYesShow select, #WifiReceiverRequiredYesShow input' ).attr( 'disabled', 'disabled' );
						}
					} );

					$( 'input[name="orderDetailsPojo.cpeStatus"]' ).bind( 'change', function(){
						if ( $( '#WiFiDeviceStatusNextraProvidedEdit' ).is( ':checked' ) ) {
							$( '#WiFiDeviceStatusNPEditShow' ).removeClass( 'hide1' );
							$( '#height' ).hide();
							$( '#WiFiDeviceStatusNPEditShow select, #WiFiDeviceStatusNPEditShow input' ).removeAttr( 'disabled' );
							var payMode = $( '[name="telecommunicationPayment.paymentMode"]:checked' ).val();
							if ( payMode != null ) {
								viewPaymentMode();
							}
						}
						else {
							$( '#WiFiDeviceStatusNPEditShow' ).addClass( 'hide1' );
							$( '#height' ).show();
							$( '#WiFiDeviceStatusNPEditShow select, #WiFiDeviceStatusNPEditShow input' ).attr( 'disabled', 'disabled' );
						}
					} );

					$( "#editCpeStatus" )
							.validate(
									{
										rules : {
											'telecommunicationPayment.paymentMode' : {
												required : true
											},
											// cash payment
											'telecommunicationPayment.receiptNo' : {
												required : true, alphabetOrNumber : true
											},
											// cheque payment
											'telecommunicationPayment.chequeDdNo' : {
												required : true, maxlength : 15, digits : true
											}, 'telecommunicationPayment.displayChequeDate' : {
												required : true
											}, 'telecommunicationPayment.bankName' : {
												dropDown : true
											}, 'telecommunicationPayment.bankCity' : {
												required : true
											}, 'telecommunicationPayment.bankBranch' : {
												required : true, 'telecommunicationPayment.bankBranch' : true, maxlength : 45
											},
											// online payment
											'telecommunicationPayment.transactionId' : {
												required : true, alphabetOrNumber : true, maxlength : 45
											}, 'telecommunicationPayment.amount' : {
												required : true, number : true, paidAmount : true, notZero : true
											}
										},
										messages : {
											'telecommunicationPayment.paymentMode' : {
												required : "<font class='errorTextbox'> Please provide 'Payment Mode'</font>"
											},
											// cash payment
											'telecommunicationPayment.receiptNo' : {
												required : "<font class='errorTextbox'> Please provide 'Receipt no.'</font>",
												alphabetOrNumber : "<font class='errorTextbox'>Please provide number or alphabets only.</font>"
											},
											// cheque payment
											'telecommunicationPayment.chequeDdNo' : {
												required : "<font class='errorTextbox'style='width: 156px;'> Please provide 'Cheque/DD No.'</font>",
												maxlength : "<font class='errorTextbox'> Please provide no more than 15 character</font>",
												digits : "<font class='errorTextbox'> Please provide digits only.</font>"
											},
											'telecommunicationPayment.displayChequeDate' : {
												required : "<font class='errorTextbox'> Please select 'Cheque Date'</font>"
											},
											'telecommunicationPayment.bankName' : {
												dropDown : "<font class='errorTextbox'> Please provide 'Bank Name'</font>"
											},
											'telecommunicationPayment.bankCity' : {
												required : "<font class='errorTextbox'> Please provide 'City'</font>"
											},
											'telecommunicationPayment.bankBranch' : {
												required : "<font class='errorTextbox'> Please provide 'Bank Branch'</font>",
												'telecommunicationPayment.bankBranch' : "<font class='errorTextbox'> 'Branch Name' should be starts with alphabets. </font>",
												maxlength : "<font class='errorTextbox'> Please provide no more than 45 character</font>"
											},
											// online payment
											'telecommunicationPayment.transactionId' : {
												required : "<font class='errorTextbox'> Please provide 'Transaction ID'</font>",
												maxlength : "<font class='errorTextbox'> Please provide no more than 45 character</font>",
												alphabetOrNumber : "<font class='errorTextbox'>Please provide number or alphabets only.</font>"
											},
											'telecommunicationPayment.amount' : {
												required : "<font class='errorTextbox'> Please provide 'Amount'</font>",
												number : "<font class='errorTextbox'style='width: 370px;'> Please provide only number, decimal allowed</font>",
												paidAmount : "<font class='errorTextbox' style='width: 370px;'> Please provide amount not more than 8 digits(and 1-2 digits after decimal)</font>",
												notZero : "<font class='errorTextbox'style='width: 370px;'> Please provide amount more than zero.</font>"
											}
										}
									} );

					// show
					if ( $( '#WiFiDeviceStatusNextraProvidedEdit' ).is( ':checked' ) ) {
						$( '#WiFiDeviceStatusNPEditShow' ).removeClass( 'hide1' );
						$( '#height' ).hide();
					}
					/*
					 * // show yes no if($('#WifiReceiverRequiredYes').is(':checked')){ $('#WifiReceiverRequiredYesShow').removeClass('hide1'); } else if($('#WifiReceiverRequiredNo').is(':checked')){ $('#WifiReceiverRequiredYesShow').addClass('hide1'); $('#WifiReceiverRequiredYesShow select,
					 * #WifiReceiverRequiredYesShow input').attr('disabled','disabled'); }
					 */

					$( '#update_CPEStatus' ).click( function(){
						if ( $( "#editCpeStatus" ).valid() ) {
							setValueForMapMacId();
						}
					} );

					function viewPaymentMode(){
						if ( $( '#ModePaymentReceiverCash' ).is( ':checked' ) ) {
							$( '#ModePaymentReceiverCashShow' ).removeClass( 'hide1' );
							$( '#ModePaymentReceiverDDShow, #ModePaymentReceiverOnlineShow' ).addClass( 'hide1' );
							$( '#ModePaymentReceiverCashShow select, #ModePaymentReceiverCashShow input' ).removeAttr( 'disabled' );
							$(
									'#ModePaymentReceiverDDShow select, #ModePaymentReceiverDDShow input, #ModePaymentReceiverOnlineShow select, #ModePaymentReceiverOnlineShow input' )
									.attr( 'disabled', true );
						}
						else if ( $( '#ModePaymentReceiverDD' ).is( ':checked' ) ) {
							$( '#ModePaymentReceiverDDShow' ).removeClass( 'hide1' );
							$( '#ModePaymentReceiverCashShow, #ModePaymentReceiverOnlineShow' ).addClass( 'hide1' );
							$( '#ModePaymentReceiverDDShow select, #ModePaymentReceiverDDShow input' ).removeAttr( 'disabled' );
							$(
									'#ModePaymentReceiverCashShow select, #ModePaymentReceiverCashShow input, #ModePaymentReceiverOnlineShow select, #ModePaymentReceiverOnlineShow input' )
									.attr( 'disabled', true );
						}
						else if ( $( '#ModePaymentReceiverOnline' ).is( ':checked' ) ) {
							$( '#ModePaymentReceiverOnlineShow' ).removeClass( 'hide1' );
							$( '#ModePaymentReceiverCashShow, #ModePaymentReceiverDDShow' ).addClass( 'hide1' );
							$( '#ModePaymentReceiverOnlineShow select, #ModePaymentReceiverOnlineShow input' ).removeAttr( 'disabled' );
							$(
									'#ModePaymentReceiverCashShow select, #ModePaymentReceiverCashShow input, #ModePaymentReceiverDDShow select, #ModePaymentReceiverDDShow input' )
									.attr( 'disabled', true );
						}
					}

					function setValueForMapMacId(){
						var deviceStatus = $( '[name="orderDetailsPojo.cpeStatus"]:checked' ).val();
						var paymentMode = $( '[name="telecommunicationPayment.paymentMode"]:checked' ).val();
						var cashReciptNo = document.getElementById( "cashReciptNo" ).value;
						// var disPlayDate = document.getElementById( "disPlayDate" ).value;
						var chequeddNo = document.getElementById( "chequeDDNo" ).value;
						var bankName = document.getElementById( "bankNameId" ).value;
						var bankCity = document.getElementById( "bankCityid" ).value;
						var transationId = document.getElementById( "transationId" ).value;
						var branchName = document.getElementById( "bankBranchNameId" ).value;
						var disPlayChkDate = document.getElementById( "disPlayChkDate" ).value;
						var amount = document.getElementById( "amountId" ).value;
						if ( amount == "" ) {
							amount = 0;
						}

						window.opener.document.getElementById( "hiddenWiFiDeviceStatusOwnedEdit" ).value = deviceStatus;
						window.opener.$( '[name="orderDetailsPojo.cpeStatus"].[value="' + deviceStatus + '"]' ).attr( 'checked', true ).trigger(
								'click' );
						window.opener.document.getElementById( "hiddenPaymentMode" ).value = paymentMode;
						window.opener.document.getElementById( "hiddenReceiptNo" ).value = cashReciptNo;
						window.opener.document.getElementById( "hiddenDisplayChequeDate" ).value = disPlayChkDate;
						window.opener.document.getElementById( "hiddenChequeDdNo" ).value = chequeddNo;
						window.opener.document.getElementById( "hiddenBankName" ).value = bankName;
						window.opener.document.getElementById( "hiddenBankCity" ).value = bankCity;
						window.opener.document.getElementById( "hiddenTransactionId" ).value = transationId;
						window.opener.document.getElementById( "hiddenBankBranchName" ).value = branchName;
						window.opener.document.getElementById( "hiddenAmount" ).value = amount;
						var answer = confirm( "are you sure you want to modify devices status ? Please note after modification it can't be change further" );
						if ( answer ) {
							window.opener.document.forms[ 1 ].action = "crmCap.do?method=saveDeviceStatus";
							window.opener.document.forms[ 1 ].submit();
							window.close();
						}
					}
					// map MAC ID

					$( '#actionTypeId' ).bind(
							'change',
							function(){
								if ( $( '#actionTypeId' ).val() == "" ) {
									$( '#macRefusalDivIdComment' ).addClass( 'hide1' );
									$( '#CustomerRefuseReasonYesShow' ).addClass( 'hide1' );
									$( '#erpDivIdComment' ).addClass( 'hide1' );
									$( '#macSubmitButton' ).addClass( 'hide1' );
									$( '#cancellationDivIdComment' ).addClass( 'hide1' );
									$( '#actionTypeId' ).parent().next( 'font' ).show().html( "Please Select 'Action Type'" ).addClass(
											'errorTextbox' ).css( 'top', 60 );
								}
								else {
									$( '#actionTypeId' ).parent().next( 'font' ).hide();
									if ( $( '#actionTypeId' ).val() == "Reject By SP" ) {
										$( '#cancellationDivIdComment' ).addClass( 'hide1' );
										$( '#macRefusalDivIdComment' ).removeClass( 'hide1' );
										$( '#erpDivIdComment' ).addClass( 'hide1' );
										$( '#CustomerRefuseReasonYesShow' ).addClass( 'hide1' );
										$( '#macSubmitButton' ).removeClass( 'hide1' );
									}
									else if ( $( '#actionTypeId' ).val() == "Customer Refusal By SP" ) {
										$( '#cancellationDivIdComment' ).addClass( 'hide1' );
										$( '#macRefusalDivIdComment' ).addClass( 'hide1' );
										$( '#CustomerRefuseReasonYesShow' ).removeClass( 'hide1' );
										$( '#erpDivIdComment' ).addClass( 'hide1' );
										$( '#macSubmitButton' ).removeClass( 'hide1' );
									}
									else if ( $( '#actionTypeId' ).val() == "Cancellation" ) {
										$( '#cancellationDivIdComment' ).removeClass( 'hide1' );
										$( '#macRefusalDivIdComment' ).addClass( 'hide1' );
										$( '#erpDivIdComment' ).addClass( 'hide1' );
										$( '#CustomerRefuseReasonYesShow' ).addClass( 'hide1' );
										$( '#macSubmitButton' ).removeClass( 'hide1' );
									}
									else if ( $( '#actionTypeId' ).val() == "ERP" ) {
										$( '#erpDivIdComment' ).removeClass( 'hide1' );
										$( '#macRefusalDivIdComment' ).addClass( 'hide1' );
										$( '#cancellationDivIdComment' ).addClass( 'hide1' );
										$( '#macRefusalDivIdComment' ).addClass( 'hide1' );
										$( '#CustomerRefuseReasonYesShow' ).addClass( 'hide1' );
										$( '#macSubmitButton' ).removeClass( 'hide1' );
									}
									else if ( $( '#actionTypeId' ).val() == "Change Feasible Address" ) {
										$( '#erpDivIdComment' ).addClass( 'hide1' );
										$( '#macRefusalDivIdComment' ).addClass( 'hide1' );
										$( '#cancellationDivIdComment' ).addClass( 'hide1' );
										$( '#macRefusalDivIdComment' ).addClass( 'hide1' );
										$( '#CustomerRefuseReasonYesShow' ).addClass( 'hide1' );
										$( '#macSubmitButton' ).removeClass( 'hide1' );
									}
									else {
										$( '#macRefusalDivIdComment' ).addClass( 'hide1' );
										$( '#erpDivIdComment' ).addClass( 'hide1' );
										$( '#cancellationDivIdComment' ).addClass( 'hide1' );
										$( '#CustomerRefuseReasonYesShow' ).addClass( 'hide1' );
										if ( $( '#macBindId' ).val() == "Y" ) {
											$( '#macSubmitButton' ).removeClass( 'hide1' );
										}
										else {
											$( '#macSubmitButton' ).addClass( 'hide1' );
										}
									}
								}
							} );

					$( '#reasonForRejection' ).bind(
							'change',
							function(){
								if ( ( $( '#reasonForRejection' ).val() == "" ) || $( '#reasonForRejection' ).val() == "0" ) {
									$( '#reasonForRejection' ).parent().next( 'font' ).show().html( "Please Select 'Rejection Reason'" ).addClass(
											'errorTextbox' );
								}
							} );

					/* Network details */

					$( 'input:radio[name="crmNetworkConfigurations.ontRgMduDone"]' ).bind( 'change', function(){
						$( this ).parent().next( 'font' ).hide();
					} );
					$( 'select[name="customerDetailsPojo.spId"]' ).bind( 'change', function(){
						if ( $( this ).val() == 0 )
							$( this ).parent().next( 'font' ).show();
						else
							$( this ).parent().next( 'font' ).hide();
					} );

					$( 'input:text[name="crmNetworkConfigurations.radiusCustomerId"]' ).bind( 'change', function(){
						if ( !$( this ).val() )
							$( this ).next( 'font' ).show();
						else
							$( this ).next( 'font' ).hide();
					} );

					$( 'textarea[name="remarksPojo.remarks"]' ).bind( 'change', function(){
						$( this ).next( 'font' ).hide();
					} );

					$( 'select[name="remarksPojo.reasonId"]' ).bind( 'change', function(){
						if ( $( this ).val() == 0 )
							$( this ).parent().next( 'font' ).show();
						else
							$( this ).parent().next( 'font' ).hide();
					} );
					$( 'textarea[name="remarksPojo.remarks"]' ).bind( 'change', function(){
						$( this ).next( 'font' ).hide();
					} );

					// ON CHANGE RADIO BUTTONS, RESPECTIVE ERROR GET HIDE
					$( 'input:radio[name="remarksPojo.actions"]' ).bind( 'change', function(){
						$( this ).parent().parent().find( 'font' ).hide();
					} );
					$( 'input:radio[name="crmNetworkConfigurations.ontRgMduDone"]' ).bind( 'change', function(){
						$( this ).parent().parent().find( 'font' ).hide();
					} );

					// ON CHANGE RADIO BUTTONS, RESPECTIVE ERROR GET HIDE

					$( '#submit_crfDetails' )
							.click(
									function(){
										if ( $( 'textarea[name="remarksPojo.remarks"]' ).val().length > 4000 ) {

											$( 'textarea[name="remarksPojo.remarks"]' ).next( 'font' ).show().html(
													"'Remarks' length should be less than [4000] characters" ).addClass( 'errorTextbox' ).css( 'top',
													106 );
										}
										else {
											$( 'textarea[name="remarksPojo.remarks"]' ).next( 'font' ).hide();
										}
										// alert($( 'textarea[name="remarksPojo.remarks"]' ).val().length>4000);
										if ( $( 'textarea[name="remarksPojo.remarks"]' ).val().length > 4000 ) {

											// alert("'Remarks' length should be [3-4000] characters");
											$( 'textarea[name="remarksPojo.remarks"]' ).next( 'font' ).show().html(
													"'Remarks' length should be [3-4000] characters" ).addClass( 'errorTextbox' ).css( 'top', 106 );
										}
										else {
											$( 'textarea[name="remarksPojo.remarks"]' ).next( 'font' ).hide();
										}
										if ( ( $( '#NPActionTypeId' ).val() == "" )
												/* || ( $( 'textarea[name="remarksPojo.remarks"]' ).val() == '' ) */
												|| ( ( $( '#NPActionTypeId' ).val() == "Reject By NP" ) && ( ( $( '#reasonForRejection' ).val() == "" ) || $(
														'#reasonForRejection' ).val() == "0" ) )
												|| ( ( $( '#NPActionTypeId' ).val() == "submitRefusal" ) && ( ( $( '#reasonForRefusalId' ).val() == "" ) || $(
														'#reasonForRefusalId' ).val() == "0" ) )
												|| ( ( $( '#NPActionTypeId' ).val() == "ERP" ) && ( ( $( '#erpForReason' ).val() == "" ) || $(
														'#erpForReason' ).val() == "0" ) )
												|| ( ( $( '#NPActionTypeId' ).val() == "Cancellation" ) && ( ( $( '#cancelForReasonId' ).val() == "" )

												|| $( '#cancelForReasonId' ).val() == "0" ) ) ) {
											if ( $( '#NPActionTypeId' ).val() == "" ) {
												$( '#NPActionTypeId' ).parent().next( 'font' ).show().html( "Please Select 'Action Type'" ).addClass(
														'errorTextbox' ).css( 'top', 60 );
											}
											else {
												$( '#NPActionTypeId' ).parent().next( 'font' ).hide();
											}

											if ( ( ( $( '#NPActionTypeId' ).val() == "Reject By NP" ) && ( ( $( '#reasonForRejection' ).val() == "" ) || $(
													'#reasonForRejection' ).val() == "0" ) ) ) {
												$( '#errorNetworkConfig' ).hide();
												$( 'textarea[id="aproveNetworkRemarks"]' ).next( 'font' ).hide();
												$( 'input:radio[name="crmNetworkConfigurations.ontRgMduDone"]' ).parent().next( 'font' ).hide();
												$( 'input:text[name="crmNetworkConfigurations.radiusCustomerId"]' ).next( 'font' ).hide();
												$( 'select[name="customerDetailsPojo.spId"]' ).parent().next( 'font' ).hide();
												$( '#reasonForCustomerRejection' ).parent().next( 'font' ).hide();
												$( '#reasonForRejection' ).parent().next( 'font' ).show().html( "Please Select 'Rejection Reason'" )
														.addClass( 'errorTextbox' );
											}
											else {
												$( '#reasonForRejection' ).parent().next( 'font' ).hide();
											}
											if ( ( $( '#NPActionTypeId' ).val() == "submitRefusal" )
													&& ( ( $( '#reasonForRefusalId' ).val() == "" ) || $( '#reasonForRefusalId' ).val() == "0" ) ) {
												$( '#errorNetworkConfig' ).hide();
												$( 'textarea[id="aproveNetworkRemarks"]' ).next( 'font' ).hide();
												$( 'input:radio[name="crmNetworkConfigurations.ontRgMduDone"]' ).parent().next( 'font' ).hide();
												$( 'input:text[name="crmNetworkConfigurations.radiusCustomerId"]' ).next( 'font' ).hide();
												$( 'select[name="customerDetailsPojo.spId"]' ).parent().next( 'font' ).hide();
												$( '#reasonForRejection' ).parent().next( 'font' ).hide();
												$( '#reasonForRefusalId' ).parent().next( 'font' ).show().html( "Please Select 'Refusal Reason'" )
														.addClass( 'errorTextbox' );

											}
											else {
												$( '#reasonForRefusalId' ).parent().next( 'font' ).hide();
											}
											if ( ( $( '#NPActionTypeId' ).val() == "Change Feasible Address" )) {
												$( '#errorNetworkConfig' ).hide();
												$( 'textarea[id="aproveNetworkRemarks"]' ).next( 'font' ).hide();
												$( 'input:radio[name="crmNetworkConfigurations.ontRgMduDone"]' ).parent().next( 'font' ).hide();
												$( 'input:text[name="crmNetworkConfigurations.radiusCustomerId"]' ).next( 'font' ).hide();
												$( 'select[name="customerDetailsPojo.spId"]' ).parent().next( 'font' ).hide();
												$( '#reasonForRejection' ).parent().next( 'font' ).hide();
											}
											else {
												$( '#reasonForRefusalId' ).parent().next( 'font' ).hide();
											}
											if ( ( $( '#NPActionTypeId' ).val() == "ERP" )
													&& ( ( $( '#erpForReason' ).val() == "" ) || $( '#erpForReason' ).val() == "0" ) ) {
												$( '#errorNetworkConfig' ).hide();
												$( 'textarea[id="aproveNetworkRemarks"]' ).next( 'font' ).hide();
												$( 'input:radio[name="crmNetworkConfigurations.ontRgMduDone"]' ).parent().next( 'font' ).hide();
												$( 'input:text[name="crmNetworkConfigurations.radiusCustomerId"]' ).next( 'font' ).hide();
												$( 'select[name="customerDetailsPojo.spId"]' ).parent().next( 'font' ).hide();
												$( '#reasonForRejection' ).parent().next( 'font' ).hide();
												$( '#reasonForRefusalId' ).parent().next( 'font' ).hide();
												$( '#erpForReason' ).parent().next( 'font' ).show().html( "Please Select 'ERP Reason'" ).addClass(
														'errorTextbox' );

											}
											else {
												$( '#erpForReason' ).parent().next( 'font' ).hide();
											}

											if ( ( $( '#NPActionTypeId' ).val() == "Cancellation" )
													&& ( ( $( '#cancelForReasonId' ).val() == "" ) || $( '#cancelForReasonId' ).val() == "0" ) ) {
												$( '#errorNetworkConfig' ).hide();
												$( 'textarea[id="aproveNetworkRemarks"]' ).next( 'font' ).hide();
												$( 'input:radio[name="crmNetworkConfigurations.ontRgMduDone"]' ).parent().next( 'font' ).hide();
												$( 'input:text[name="crmNetworkConfigurations.radiusCustomerId"]' ).next( 'font' ).hide();
												$( 'select[name="customerDetailsPojo.spId"]' ).parent().next( 'font' ).hide();
												$( '#reasonForRejection' ).parent().next( 'font' ).hide();
												$( '#cancelForReasonId' ).parent().next( 'font' ).show().html( "Please Select 'Cancellation Reason'" )
														.addClass( 'errorTextbox' );
											}
											else {
												$( '#cancelForReasonId' ).parent().next( 'font' ).hide();
											}

										}
										else {
											$( '#NPActionTypeId' ).parent().next( 'font' ).hide();
											submitNetworkDetails();
										}
									} );

					// MAP MAC ID

					// radio selection error hide

					$( 'input:radio[name="remarksPojo.actions"]' ).bind( 'change', function(){
						$( this ).parent().next( 'font' ).hide();
					} );

					$( '#submit_mapMacId' ).click(
							function(){
								if ( $( '#hiddenAmount' ).val() == "" ) {
									document.getElementById( 'hiddenAmount' ).value = 0;
								}

								if ( $( '#actionTypeId' ).val() == "" ) {
									$( '#actionTypeId' ).parent().next( 'font' ).show().html( "Please Select 'Action Type'" ).addClass(
											'errorTextbox' ).css( 'top', 65 );
								}
								else {
									$( '#actionTypeId' ).parent().next( 'font' ).hide();
									submitCRFMapMac();
								}
							} );
					$( '#bind_CPEMacId' ).click(
							function(){
								if ( !checkHexadecimal( $( 'input:text[id="cpemacid"]' ).val() ) ) {
									$( 'input:text[id="cpemacid"]' ).next( 'font' ).show().html( 'Please insert only hexadecimal 23aF.D3de.A12f' )
											.addClass( 'errorRadio' ).css( 'width', '250px' );
									check = false;
								}
								else {
									$( 'input:text[id="cpemacid"]' ).next( 'font' ).hide();
									var answer = confirm( "Please confirm bind CPE MAC ID!" );
									if ( answer ) {
										document.forms[ 1 ].action = "crmCap.do?method=bindCPEMACId";
										document.forms[ 1 ].submit();
									}
								}
							} );
					// save ISR Reference
					$( '#saveISRReferenceId' ).click(
							function(){
								if ( $( '#isrReferenceId' ).val() == "" ) {
									$( '#errorISRReferenceNo' ).html( "<br/>Please provide 'ISR Reference No'" ).addClass(
											'serverSideMessage error_message' ).css( 'width', 'auto' ).css( 'top', 15 );
									return false;
								}
								else {
									$( '#errorISRReferenceNo' ).hide();
									var ans = confirm( "Please confirm, if you want to save ISR Reference Number." );
									if ( ans ) {
										var isrReferenceNo = $( '#isrReferenceId' ).val();
										var userId = $( '#hiddenCRMUserId' ).val();
										var custRecordId = $( '#hiddenRecordId' ).val();
										crmDwr.saveISRReferenceNo( isrReferenceNo, custRecordId, userId, function( result ){
											alert( result );
										} );
									}
								}
							} );
					// for istallation satisfaction report
					$( '#submit_ISRReports' ).click(
							function(){
								var flag = true;
								$( '.materialDetails_msg' ).hide();
								$( '.feedback_msg' ).hide();

								var remarks = document.getElementById( "remarks_5" );
								if ( remarks.value.trim() == "" ) {
									$( remarks ).next().show().html( '<br>Please provide Remarks.' );
									remarks.focus();
									flag = false;
								}
								else if ( remarks.value.trim().length < 2 || remarks.value.trim().length > 4000 ) {
									$( remarks ).next().show().html( "'<br>Please enter Remarks between [2-4000]." );
									remarks.focus();
									flag = false;
								}
								if ( $( 'input[name="displayISRDate"]' ).val() == "" ) {
									$( 'input[name="displayISRDate"]' ).next( 'font' ).show().html( "Please select 'ISR Date'" ).addClass(
											'errorTextbox' );
									flag = false;
								}
								else if ( dateDiff( new Date(), getISODate( $( 'input[name="displayISRDate"]' ).val() ) ) > 0 ) {
									$( 'input[name="displayISRDate"]' ).next( 'font' ).show().html( "ISR Date cannot be a future date." ).addClass(
											'errorTextbox' );
									flag = false;
								}
								else {
									var days = dateDiff( getISODate( $( '#activationDateID' ).val() ), getISODate( $( 'input[name="displayISRDate"]' )
											.val() ) );
									if ( days < 0 ) {
										$( 'input[name="displayISRDate"]' ).next( 'font' ).show().html(
												"ISR date should be same or greater than as MAC Address bind date" ).addClass( 'errorTextbox' ).css(
												'width', '329px' );
										flag = false;
									}
									else {
										$( 'input[name="displayISRDate"]' ).next( 'font' ).hide();
									}
								}
								// materialList
								var CAT5 = document.getElementById( "outer_0" );
								var RH45 = document.getElementById( "outer_1" );
								var Nail = document.getElementById( "outer_2" );
								var Patch = document.getElementById( "outer_3" );
								if ( CAT5.value == "" ) {
									$( CAT5 ).next().show().html( 'Please provide CAT5 Cable.' );
									CAT5.focus();
									flag = false;
								}
								if ( RH45.value == "" ) {
									$( RH45 ).next().show().html( 'Please provide RJ45 Cable.' );
									RH45.focus();
									flag = false;
								}
								if ( Nail.value == "" ) {
									$( Nail ).next().show().html( 'Please provide Nail Hooks (nos.).' );
									Nail.focus();
									flag = false;
								}
								if ( Patch.value == "" ) {
									$( Patch ).next().show().html( 'Please provide Patch Cord (nos.).' );
									Patch.focus();
									flag = false;
								}

								// end of materialList
								var QIEW = $( '[id="customerFeedBack_0"]' );
								var SAIC = $( '[id="customerFeedBack_1"]' );
								var OPIC = $( '[id="customerFeedBack_2"]' );
								var DITMYCT = $( '[id="customerFeedBack_3"]' );
								var OEDIP = $( '[id="customerFeedBack_4"]' );
								if ( !QIEW.is( ':checked' ) ) {
									QIEW.next().show().html( '<br>Please provide Quality of Installation & External Wiring.' );
									flag = false;
								}
								if ( !SAIC.is( ':checked' ) ) {
									SAIC.next().show().html( '<br>Please provide Service attitude of Installation crew.' );
									flag = false;
								}
								if ( !OPIC.is( ':checked' ) ) {
									OPIC.next().show().html( '<br>Please provide Overall presentability of Installation crew.' );
									flag = false;
								}
								if ( !DITMYCT.is( ':checked' ) ) {
									DITMYCT.next().show().html( '<br>Please provide installation team meet you at a convenient time.' );
									flag = false;
								}
								if ( !OEDIP.is( ':checked' ) ) {
									OEDIP.next().show().html( '<br>Please provide Overall experience during Installation Process.' );
									flag = false;
								}
								if ( flag ) {
									var answer = confirm( "Please confirm Punch ISR!" );
									if ( answer ) {
										document.forms[ 1 ].action = "crmCap.do?method=punchISR";
										document.forms[ 1 ].submit();
									}
								}

							} );
					$( '#bind_CPESlaveMacId' ).click(
							function(){
								if ( $( '#hiddenAmount' ).val() == "" ) {
									document.getElementById( 'hiddenAmount' ).value = 0;
								}
								var check = true;
								if ( !$( 'input:text[id="primaryMACAddrId"]' ).val() ) {
									$( 'input:text[id="primaryMACAddrId"]' ).next( 'font' ).show().html( "Please provide 'MAC Address.'" ).addClass(
											'errorRadio' ).css( 'width', 'auto' );
									check = false;
								}
								else if ( !checkHexadecimal( $( 'input:text[id="primaryMACAddrId"]' ).val() ) ) {
									$( 'input:text[id="primaryMACAddrId"]' ).next( 'font' ).show().html( 'Insert valid format 23aF.D3de.A12f' )
											.addClass( 'errorRadio' ).css( 'width', '215px' );
									check = false;
								}
								else {
									$( 'input:text[id="primaryMACAddrId"]' ).next( 'font' ).hide();
								}

								if ( $( 'input[id="secondaryMacTextId"]' ).val() == "" ) {
									$( '#currentSlaveMacId' ).next( 'font' ).hide();
									$( 'input[id="secondaryMacTextId"]' ).next( 'font' ).show().html( "Please provide Secondary 'MAC Address.'" )
											.addClass( 'errorTextbox' ).css( 'width', 'auto' );
									check = false;
								}
								else {
									$( 'input[id="secondaryMacTextId"]' ).next( 'font' ).hide();
								}

								if ( check ) {
									var answer = confirm( "Please confirm bind CPE MAC ID!" );
									if ( answer ) {
										$( '.loadingPopup' ).removeClass( 'hidden' );
										document.forms[ 1 ].action = "crmCap.do?method=bindCPEMACId";
										document.forms[ 1 ].submit();
									}
								}
							} );
					populatePoolNameByMasterName( 'nasportId', 'poolNameId', $( '#masterNameId' ).val() );
				} );

function populatePoolNameByMasterName( nasPortId, poolNameId, masterName ){
	if ( null == masterName || "" == masterName ) {
		return;
	}
	populateNasportIdByMasterName( nasPortId, masterName );
	var nasportId = $( '#nasportId' ).val();
	if ( null == nasportId || "" == nasportId ) {
		return;
	}
	populatePoolNameByNasportId( poolNameId, "masterNameId", nasportId );
}
function checkNetworkConfigDetails(){
	var npID = $( '#hiddenPartnerId' ).val();
	var product = $( '#hiddenProduct' ).val();
	window.open( 'crmCap.do?method=networkInventoryDetailsPage&NPID=' + npID + '&Product=' + product, 'newWindow',
			'width=1100,height=380,scrollbars=yes,resizable=no,toolbar=no' );
}
// function fillISRReport(){
// window.open( 'crmCap.do?method=fillISRReportPage', 'newWindow', 'width=1200,height=670,scrollbars=yes,resizable=no,toolbar=no' );
//
// }
function editCPEStatus(){
	var orderId = $( '#hiddOrderRecordID' ).val();
	var cpeStatus = $( '#hiddenWiFiDeviceStatusOwnedEdit' ).val();
	window.open( 'crmCap.do?method=editCPEStatusPage&OrderId=' + orderId + '&CPEStatus=' + cpeStatus, 'newWindow',
			'width=950,height=470,scrollbars=yes,resizable=no,toolbar=no' );
}

function submitNetworkDetails(){
	var product = document.getElementById( "hiddenProduct" ).value;
	var check = true;
	if ( $( '#NPActionTypeId' ).val() == "Approve" ) {
		if ( product != "BB" && !$( '#IDnasportID' ).val() ) {
			$( '#errorNetworkConfig' ).html( 'Please click on link and select Master Name' ).addClass( 'errorCreateUserSelect error_message' );
			return false;
		}
		else {
			$( '#errorNetworkConfig' ).hide();
		}
		if ( $( 'input[name="customerDetailsPojo.serviceType"]' ).val() == "PO" ) {
			if ( !$( 'input:radio[name="crmNetworkConfigurations.ontRgMduDone"]' ).is( ':checked' ) ) {
				$( 'input:radio[name="crmNetworkConfigurations.ontRgMduDone"]' ).parent().next( 'font' ).show().html(
						"Please select 'ONT / RG / MDU Port configuration'" ).addClass( 'errorRadio' ).css( 'width', 'auto' );
				check = false;
			}
			else {
				$( 'input:radio[name="crmNetworkConfigurations.ontRgMduDone"]' ).parent().next( 'font' ).hide();
			}
		}
		if ( product == "BB" ) {
			if ( !$( 'input:text[name="crmNetworkConfigurations.option82"]' ).val() ) {
				$( 'input:text[name="crmNetworkConfigurations.option82"]' ).next( 'font' ).show().html( "Please provide 'Option82'" ).addClass(
						'errorTextbox' );
				check = false;
			}
			else {
				$( 'input:text[name="crmNetworkConfigurations.option82"]' ).next( 'font' ).hide();
				$( '#option82Id' ).val( $( '#IDoption82' ).val() );
			}
		}
		if ( $( 'select[name="customerDetailsPojo.spId"]' ).val() == '' ) {
			$( 'select[name="customerDetailsPojo.spId"]' ).parent().next( 'font' ).show().html( "Please select 'Service Partner'" ).addClass(
					'errorTextbox' );
			check = false;
		}
		else {
			$( 'select[name="customerDetailsPojo.spId"]' ).parent().next( 'font' ).hide();
		}
	}
	if ( check ) {
		var answer = confirm( "Please confirm if you want to submit CAF!" );
		if ( answer ) {
			if ( $( '#NPActionTypeId' ).val() == "Reject By NP" ) {
				$( "#reasonForRefusalId" ).remove();
				$( "#cancelForReasonId" ).remove();
				$( "#erpForReason" ).remove();

			}
			else if ( $( '#NPActionTypeId' ).val() == "submitRefusal" ) {
				$( "#cancelForReasonId" ).remove();
				$( "#reasonForRejection" ).remove();
				$( "#erpForReason" ).remove();

			}
			else if ( $( '#NPActionTypeId' ).val() == "Cancellation" ) {
				$( "#reasonForRejection" ).remove();
				$( "#reasonForRefusalId" ).remove();
				$( "#erpForReason" ).remove();
			}
			if ( $( '#NPActionTypeId' ).val() == "ERP" ) {
				$( "#reasonForRejection" ).remove();
				$( "#reasonForRefusalId" ).remove();
				$( "#cancelForReasonId" ).remove();
			}
			document.getElementById( "forwardId" ).value = "submitRefusal";
			document.forms[ 1 ].action = "crmCap.do?method=saveNetworkDetails";
			document.forms[ 1 ].submit();
		}
	}
}

function submitCRFMapMac(){
	// alert('submitCRFMapMac');
	var check = true;
	if ( $( 'textarea[id="aproveComment"]' ).val().length > 4000 ) {
		$( 'textarea[id="aproveComment"]' ).next( 'font' ).show().html( "'Remarks' length should be less than [4000] characters." ).addClass(
				'errorAddress ' ).css( {
			'top' : 106
		} );
		check = false;
	}
	else {
		$( 'textarea[id="aproveComment"]' ).next( 'font' ).hide();
	}

	/*
	 * if ( !$( 'textarea[id="aproveComment"]' ).val() ) { $( 'textarea[id="aproveComment"]' ).next( 'font' ).show().html( "Please enter 'Remarks'" ).addClass( 'errorAddress ' ).css( 'top', 106 ); check = false; }
	 */
	if ( $( 'textarea[id="aproveComment"]' ).val().length > 4000 ) {
		$( 'textarea[id="aproveComment"]' ).next( 'font' ).show().html( "'Remarks' length should be [3-4000] characters." )
				.addClass( 'errorAddress ' ).css( {
					'top' : 106
				} );
		check = false;
	}
	else {
		$( 'textarea[id="aproveComment"]' ).next( 'font' ).hide();
	}

	if ( $( '#actionTypeId' ).val() == "Reject By SP" ) {
		$( 'input:radio[name="crmNetworkConfigurations.spOntRgMduDone"]' ).parent().next( 'font' ).hide();
		$( 'input:text[id="cpemacid"]' ).next( 'font' ).hide();
		$( 'input[name="displayISRDate"]' ).next( 'font' ).hide();
		$( 'input:text[id="primaryMACAddrId"]' ).next( 'font' ).hide();
		$( 'select[id="secondaryMACAddrId"]' ).parent().next( 'font' ).hide();

		if ( $( 'select[id="rejectForReasonId"]' ).val() == "" ) {
			$( 'select[id="rejectForReasonId"]' ).parent().next( 'font' ).show().html( "Please select 'Reject Reason'." ).addClass( 'errorTextbox' );
			check = false;
		}
		else {
			$( 'select[id="rejectForReasonId"]' ).parent().next( 'font' ).hide();
		}

	}
	else if ( $( '#actionTypeId' ).val() == "ERP" ) {
		$( 'input:radio[name="crmNetworkConfigurations.spOntRgMduDone"]' ).parent().next( 'font' ).hide();
		$( 'input:text[id="cpemacid"]' ).next( 'font' ).hide();
		$( 'input[name="displayISRDate"]' ).next( 'font' ).hide();
		$( 'input:text[id="primaryMACAddrId"]' ).next( 'font' ).hide();
		$( 'select[id="secondaryMACAddrId"]' ).parent().next( 'font' ).hide();

		if ( $( 'select[id="erpForReasonId"]' ).val() == "" ) {
			$( 'select[id="erpForReasonId"]' ).parent().next( 'font' ).show().html( "Please select 'ERP Reason'." ).addClass( 'errorTextbox' );
			check = false;
		}
		else {
			$( 'select[id="erpForReasonId"]' ).parent().next( 'font' ).hide();
		}

	}
	else if ( $( '#actionTypeId' ).val() == "Approve By SP" ) {

		if ( !$( 'input:radio[name="crmNetworkConfigurations.spOntRgMduDone"]' ).is( ':checked' ) ) {
			$( 'input:radio[name="crmNetworkConfigurations.spOntRgMduDone"]' ).parent().next( 'font' ).show().html(
					"Please select 'ONT / RG / MDU Port configuration'" ).addClass( 'errorRadio' ).css( 'width', 'auto' );
			check = false;
		}
		else {
			$( 'input:radio[name="crmNetworkConfigurations.spOntRgMduDone"]' ).parent().next( 'font' ).hide();
		}
		if ( $( 'input:hidden[id="macBindId"]' ).val() == 'N' || $( 'input[name="hiddenActivationDate"]' ).val() == "" ) {
			$( 'div[id="error"]' ).show().html( "MAC is yet to bind, Please bind the MAC first." ).css( 'width', 'auto' );
			check = false;
		}
		else {
			$( 'div[id="error"]' ).hide();
		}
	}
	else if ( $( '#actionTypeId' ).val() == "Customer Refusal By SP" ) {
		$( 'input:radio[name="crmNetworkConfigurations.spOntRgMduDone"]' ).parent().next( 'font' ).hide();
		$( 'input:text[id="cpemacid"]' ).next( 'font' ).hide();
		$( 'input[name="displayISRDate"]' ).next( 'font' ).hide();
		$( 'input:text[id="primaryMACAddrId"]' ).next( 'font' ).hide();
		$( 'select[id="secondaryMACAddrId"]' ).parent().next( 'font' ).hide();

		if ( $( 'select[id="reasonForRefusalId"]' ).val() == "" ) {
			$( 'select[id="reasonForRefusalId"]' ).parent().next( 'font' ).show().html( "Please select 'Reason for Refusal.'" ).addClass(
					'errorTextbox' ).css( 'width', 200 );
			check = false;
		}
		else {
			$( 'select[id="reasonForRefusalId"]' ).parent().next( 'font' ).hide();
		}
	}
	else if ( $( '#actionTypeId' ).val() == "Cancellation" ) {
		$( 'input:radio[name="crmNetworkConfigurations.spOntRgMduDone"]' ).parent().next( 'font' ).hide();
		$( 'input:text[id="cpemacid"]' ).next( 'font' ).hide();
		$( 'input[name="displayISRDate"]' ).next( 'font' ).hide();
		$( 'input:text[id="primaryMACAddrId"]' ).next( 'font' ).hide();
		$( 'select[id="secondaryMACAddrId"]' ).parent().next( 'font' ).hide();

		if ( $( 'select[id="cancelForReasonId"]' ).val() == "" ) {
			$( 'select[id="cancelForReasonId"]' ).parent().next( 'font' ).show().html( "Please select 'Reason for Cancellation.'" ).addClass(
					'errorTextbox' ).css( 'width', 200 );
			check = false;
		}
		else {
			$( 'select[id="cancelForReasonId"]' ).parent().next( 'font' ).hide();
		}
	}
	if ( check ) {
		var answer = confirm( "Please confirm if you want to submit CAF!" );
		if ( answer ) {
			if ( $( '#actionTypeId' ).val() == "Reject By SP" ) {
				$( "#reasonForRefusalId" ).remove();
				$( "#cancelForReasonId" ).remove();
				$( "#erpForReasonId" ).remove();

			}
			else if ( $( '#actionTypeId' ).val() == "Customer Refusal By SP" ) {
				$( "#cancelForReasonId" ).remove();
				$( "#rejectForReasonId" ).remove();
				$( "#erpForReasonId" ).remove();
			}
			else if ( $( '#actionTypeId' ).val() == "Cancellation" ) {
				$( "#rejectForReasonId" ).remove();
				$( "#reasonForRefusalId" ).remove();
				$( "#erpForReasonId" ).remove();
			}
			else if ( $( '#actionTypeId' ).val() == "ERP" ) {
				$( "#cancelForReasonId" ).remove();
				$( "#rejectForReasonId" ).remove();
				$( "#reasonForRefusalId" ).remove();
			}
			document.getElementById( "forwardId" ).value = "submitRefusal";
			document.forms[ 1 ].action = "crmCap.do?method=saveMapMacIdDetails";
			document.forms[ 1 ].submit();
		}

	}

}

function receiptNo(){
	var partnerId = document.getElementById( "hiddenPartnerId" ).value;
	crmDwr.getReceiptNo( partnerId, function( list ){
		var id = document.getElementById( "receipt" );
		if ( list != null ) {
			dwr.util.removeAllOptions( id );
			document.getElementById( 'showReceiptList' ).style.display = "block";
			dwr.util.addOptions( id, list );

		}
	} );
}
function receiptNoforTelesolution(){
	var partnerId = document.getElementById( "hiddenPartnerId" ).value;
	crmDwr.getReceiptNo( partnerId, function( list ){
		var id = document.getElementById( "telesolutionReceipt" );
		if ( list != null ) {
			dwr.util.removeAllOptions( id );
			document.getElementById( 'telesolutionShowReceiptList' ).style.display = "block";
			dwr.util.addOptions( id, list );

		}
	} );
}

function fillReceiptList( filterId, textId, selectedValue, inListType ){
	var textElement = document.getElementById( textId );
	textElement.value = selectedValue;
	dwr.util.removeAllOptions( filterId );
	document.getElementById( inListType ).style.display = "none";
	textElement.focus();
}
function populateNasportIdByMasterName( nasPortId, masterName ){
	var poolId = "";
	if ( $( '#poolNameId' ).val() != "" && $( '#poolNameId' ).val() != undefined ) {
		poolId = document.getElementById( "poolNameId" );
	}
	else if ( $( '#IDQRCpoolNameId' ).val() != "" ) {
		poolId = document.getElementById( "IDQRCpoolNameId" );
	}
	if ( null == masterName || "" == masterName ) {
		dwr.util.removeAllOptions( nasPortId );
		dwr.util.addOptions( nasPortId, [
			"Please Select"
		] );
		dwr.util.removeAllOptions( poolId );
		dwr.util.addOptions( poolId, [
			"Please Select"
		] );
		return;
	}
	var partnerDetailsId = $( '#ID_partnerDetailsId' ).val();
	if ( null == partnerDetailsId || partnerDetailsId <= 0 ) {
		return;
	}
	crmDwr.getNasportId( partnerDetailsId, masterName, function( list ){
		addNASPortId( nasPortId, poolId, masterName, list );
	} );
	function addNASPortId( id, poolId, masterName, list ){
		var select = document.getElementById( id );
		dwr.util.removeAllOptions( poolId );
		dwr.util.addOptions( poolId, [
			"Please Select"
		] );
		if ( select != null ) {
			if ( list != null ) {
				dwr.util.removeAllOptions( id );
				dwr.util.addOptions( id, [
					"Please Select"
				] );
				dwr.util.addOptions( id, list );
			}
			else {
				alert( "No NasPort ID registered in system for " + masterName );
				removeList( id );
			}
		}
	}

}

function populatePoolNameByNasportId( poolNameId, masterNameId, nasportId ){
	var masterName = $( '#' + masterNameId ).val();
	if ( null == nasportId || nasportId <= 0 || 'Please Select' == nasportId ) {
		dwr.util.removeAllOptions( poolNameId );
		dwr.util.addOptions( poolNameId, [
			"Please Select"
		] );
		return;
	}
	var partnerDetailsId = $( '#ID_partnerDetailsId' ).val();
	if ( null == partnerDetailsId || partnerDetailsId <= 0 ) {
		return;
	}
	crmDwr.getPoolName( partnerDetailsId, masterName, nasportId, function( list ){
		addPoolName( poolNameId, masterName, nasportId, list );
	} );

	function addPoolName( id, masterName, nasportId, list ){
		var select = document.getElementById( id );
		if ( select != null ) {
			if ( list != null ) {
				dwr.util.removeAllOptions( id );
				dwr.util.addOptions( id, list, "recordId", "poolName" );
				dwr.util.addOptions();
			}
			else {
				alert( "No PoolName registered in system for Master:" + masterName + " and NAS Port ID:" + nasportId );
				removeList( id );
			}
		}
	}
}

function populateNetworkDetailsByPartner( oltType ){
	validateArrayForOption82 = [];
	var partnerId = $( '#hiddenPartnerId' ).val();
	var product = window.opener.document.getElementById( "hiddenProduct" ).value;
	// oltType
	crmDwr.getCrmParameterListOption82( partnerId, product, oltType, function( list ){
		addParameterName( list );

	} );

	function addParameterName( list ){
		if ( list != null ) {
			// var validateArrayForOption82=[];
			// alert('hello');
			for ( var i = 0; i < list.length; i++ ) {
				// alert(list[i].parameterName);
				switch ( list[ i ].parameterName ) {
					case 'OLTNODE ID':
						// alert('OLTNODE ID');
						validateArrayForOption82.push( 'oltNodeId' );
						break;
					case 'VLAN ID':
						// alert('yuhoo VLAN ID');
						validateArrayForOption82.push( 'vlanId' );
						break;
					case 'OLT SLOT':
						// alert('OLTNODE ID');
						validateArrayForOption82.push( 'oltSlotId' );
						break;
					case 'OLT PORT':
						// alert('OLTNODE ID');
						validateArrayForOption82.push( 'oltPortId' );
						break;
					case 'OLT SUB PORT':
						// alert('OLTNODE ID');
						validateArrayForOption82.push( 'oltSubPortId' );
						break;
					case 'SERVICE TYPE':
						// alert('OLTNODE ID');
						validateArrayForOption82.push( 'serviceTypeId' );
						break;
					case 'ONT/ONU PORT':
						// alert('OLTNODE ID');
						validateArrayForOption82.push( 'ontType' );
						break;
					/*
					 * default: validateArrayForOption82.push('oltNodeId'); break;
					 */
				}
				// dwr.util.setValue('testField',list[i].parameterName);
			}

			alert( 'global array value:' + validateArrayForOption82 );
			/*
			 * dwr.util.setValue('testField',list[0].parameterName); alert($('#testField').val());
			 */
			// $('#masterId').trigger("change");
		}
		else {
			alert( "No nasPortId registered in system for selected Master Name." );
			removeList( id );
		}
	}
}

// INA for NP stage
$( document ).ready( function(){
	if ( $( '#NPActionTypeId' ).val() == "" ) {
		$( '#NPRefusalDivIdComment' ).addClass( 'hide1' );
		$( '#yesCustomerRefuseReason' ).addClass( 'hide1' );
	}
	else {
		$( '#NPActionTypeId' ).parent().next( 'font' ).hide();
		if ( $( '#NPActionTypeId' ).val() == "submitRefusal" ) {
			$( '#NPActionTypeId' ).parent().next( 'font' ).hide();
			$( '#DIVrejectionReason' ).addClass( 'hide1' );
			$( '#yesCustomerRefuseReason' ).removeClass( 'hide1' );
		}
		else {
			$( '#yesCustomerRefuseReason' ).addClass( 'hide1' );
		}

		if ( $( '#NPActionTypeId' ).val() == "Approve" ) {
			$( '#NPRefusalDivIdComment' ).addClass( 'hide1' );
		}
		else {
			$( '#NPRefusalDivIdComment' ).removeClass( 'hide1' );
		}

		if ( $( '#NPActionTypeId' ).val() == "Reject By NP" ) {
			$( '#yesCustomerRefuseReason' ).addClass( 'hide1' );
			$( '#DIVrejectionReason' ).removeClass( 'hide1' );
			$( '#NPRefusalDivIdComment' ).removeClass( 'hide1' );
			$( '#NPRefusalDivIdComment' ).removeClass( 'hide1' );
		}
		if ( $( '#NPActionTypeId' ).val() == "Cancellation" ) {
			$( '#npCancellationDivIdComment' ).removeClass( 'hide1' );
			$( '#yesCustomerRefuseReason' ).addClass( 'hide1' );
			$( '#DIVrejectionReason' ).addClass( 'hide1' );
			$( '#NPRefusalDivIdComment' ).addClass( 'hide1' );
		}
		if ( $( '#NPActionTypeId' ).val() == "Change Feasible Address" ) {
			$( '#npCancellationDivIdComment' ).addClass( 'hide1' );
			$( '#yesCustomerRefuseReason' ).addClass( 'hide1' );
			$( '#DIVrejectionReason' ).addClass( 'hide1' );
			$( '#NPRefusalDivIdComment' ).addClass( 'hide1' );
		}
	}
} );

// INA for SP stage
$( document ).ready( function(){
	if ( $( '#actionTypeId' ).val() == "" ) {
		$( '#NPRefusalDivIdComment' ).addClass( 'hide1' );
		$( '#CustomerRefuseReasonYesShow' ).addClass( 'hide1' );
	}
	else {
		$( '#actionTypeId' ).parent().next( 'font' ).hide();
		if ( $( '#actionTypeId' ).val() == "Reject By SP" ) {
			$( '#macRefusalDivIdComment' ).removeClass( 'hide1' );
			$( '#CustomerRefuseReasonYesShow' ).addClass( 'hide1' );
			$( '#macSubmitButton' ).removeClass( 'hide1' );
		}
		else if ( $( '#actionTypeId' ).val() == "Customer Refusal By SP" ) {
			$( '#macRefusalDivIdComment' ).addClass( 'hide1' );
			$( '#CustomerRefuseReasonYesShow' ).removeClass( 'hide1' );
			$( '#macSubmitButton' ).removeClass( 'hide1' );
		}
		else if ( $( '#actionTypeId' ).val() == "Cancellation" ) {
			$( '#cancellationDivIdComment' ).removeClass( 'hide1' );
			$( '#CustomerRefuseReasonYesShow' ).addClass( 'hide1' );
			$( '#macSubmitButton' ).removeClass( 'hide1' );
		}
		else {
			$( '#macRefusalDivIdComment' ).addClass( 'hide1' );
			$( '#CustomerRefuseReasonYesShow' ).addClass( 'hide1' );
			$( '#cancellationDivIdComment' ).addClass( 'hide1' );
			if ( $( '#macBindId' ).val() == "Y" ) {
				$( '#macSubmitButton' ).removeClass( 'hide1' );
			}
			else {
				$( '#macSubmitButton' ).addClass( 'hide1' );
			}
		}
	}
} );

function validValueForMaster(){
	var masterFlag = true;
	// alert('helloworld');
	var serviceModel = $( 'input[name="crmNetworkConfigurations.serviceModel"]:checked' ).val();
	if ( serviceModel == undefined || ( serviceModel == '' ) ) {
		masterFlag = false;
		$( '.serviceModel > font' ).removeClass( 'hide1' );
		$( '.serviceModel > font' ).show().addClass( 'errorTextbox' ).css( 'top', 32 );
	}
	else {
		$( '.serviceModel > font' ).addClass( 'hide1' );
	}
	if ( $( '#masterNameId' ).val() == '0' || $( '#masterNameId' ).val() == '' ) {
		$( '#masterNameId' ).parent().next( 'font' ).removeClass( 'hide1' );
		$( '#masterNameId' ).parent().next( 'font' ).show().addClass( 'errorTextbox' ).css( 'top', 51 );

		masterFlag = false;
	}
	else {
		$( '#masterNameId' ).parent().next( 'font' ).addClass( 'hide1' );
		if ( $( '#nasportId' ).val() == '0' || $( '#nasportId' ).val() == 'Please Select' ) {
			$( '#nasportId' ).parent().next( 'font' ).removeClass( 'hide1' );
			$( '#nasportId' ).parent().next( 'font' ).show().addClass( 'errorTextbox' ).css( 'top', 51 );

			masterFlag = false;
		}
		else {
			$( '#nasportId' ).parent().next( 'font' ).addClass( 'hide1' );
		}
	}
	if ( masterFlag ) {
		return true;
	}
	else {
		return false;
	}
}

function setValueForMaster(){
	var recordId = document.getElementById( "poolNameId" ).value;
	var actionFtthModel = document.getElementById( "actionFtthModel" ).value;
	var nasportId = document.getElementById( "nasportId" ).value;
	window.opener.document.getElementById( "option82Id" ).value = recordId;
	window.opener.document.getElementById( "hiddenOntRgMduDone" ).value = actionFtthModel;
	window.opener.document.getElementById( "IDnasportID" ).value = nasportId;
	self.close();
}
function setValueForNetworkDetails(){

	var actionFtthModel = document.getElementById( "actionFtthModel" ).value;
	var serviceType = document.getElementById( "serviceTypeId" ).value;
	var vlan = document.getElementById( "vlanId" ).value;
	var oltNode = document.getElementById( "oltNodeId" ).value;
	var oltSlot = document.getElementById( "oltSlotId" ).value;
	var oltPort = document.getElementById( "oltPortId" ).value;
	var oltSubPort = document.getElementById( "oltSubPortId" ).value;
	var partnerId = document.getElementById( "hiddenPartnerId" ).value;

	// alert("partnerId ??"+partnerId);
	window.opener.document.getElementById( "hiddenOntRgMduDone" ).value = actionFtthModel;
	window.opener.document.getElementById( "hiddenServiceType" ).value = serviceType;
	window.opener.document.getElementById( "hiddenVlanId" ).value = vlan;
	window.opener.document.getElementById( "hiddenOltNoteId" ).value = oltNode;
	window.opener.document.getElementById( "hiddenOltSlot" ).value = oltSlot;
	window.opener.document.getElementById( "hiddenOltPort" ).value = oltPort;
	window.opener.document.getElementById( "hiddenOltSubPort" ).value = oltSubPort;
	var oltType = document.getElementById( "oltTypeList" ).value;
	window.opener.document.getElementById( "hiddenOltType" ).value = oltType;
	var nameof82 = new Array();
	var valueof82 = new Array();
	nameof82[ 0 ] = "OLTNODE ID";
	nameof82[ 1 ] = "VLAN ID";
	nameof82[ 2 ] = "OLT SLOT";
	nameof82[ 3 ] = "OLT PORT";
	nameof82[ 4 ] = "OLT SUB PORT";
	nameof82[ 5 ] = "Service Type";
	nameof82[ 6 ] = "ONT PORT";
	valueof82[ 0 ] = oltNode;
	valueof82[ 1 ] = vlan;
	valueof82[ 2 ] = oltSlot;
	valueof82[ 3 ] = oltPort;
	valueof82[ 4 ] = oltSubPort;
	valueof82[ 5 ] = serviceType;
	valueof82[ 6 ] = oltPort;
	// Dwr implementation
	crmDwr.getOption82String( partnerId, nameof82, valueof82, oltType, {
		callback : function( str ){
			alert( 'option82 is' + str );
			window.opener.document.getElementById( "option82Id" ).value = str;
			window.opener.document.getElementById( "IDoption82" ).value = str;

			self.close();
		}
	} );
}
function validateOption82Configuration(){
	var flag = false;
	var forloopCounter = 0;
	$( '#DIVoption82Note' ).html( "Please give any one of the mandatory values:" + validateArrayForOption82 ).addClass( 'errorTextbox' ).css( 'top',
			26 ).css( 'width', 295 ).css( 'left', 30 );
	if ( validateArrayForOption82.length > 0 ) {
		for ( var j = 0; j < validateArrayForOption82.length; j++ ) {
			if ( document.getElementById( validateArrayForOption82[ j ] ).value !== '' ) {
				// alert('came to check'+validateArrayForOption82[j]);
				forloopCounter++;
				switch ( validateArrayForOption82[ j ] ) {
					case 'oltNodeId':
						if ( ( !validateOltNodeId( $( '#oltNodeId' ).val() ) ) || $( '#oltNodeId' ).val() ) {
							flag = false;
							$( '#oltNodeId' ).next( 'font' ).show().html( "Please Provide correct olt Node Value" ).addClass( 'errorTextbox' );
						}
						else {
							flag = true;
						}
						break;
					case 'vlanId':

						if ( ( !digits( $( '#vlanId' ).val() ) ) || $( '#vlanId' ).val() == 0 ) {
							alert( 'vlan validation failed' );
							flag = false;
							$( '#vlanId' ).next( 'font' ).show().html( "Please Provide only digits" ).addClass( 'errorTextbox' );
						}
						else {
							flag = true;
						}
						break;
					case 'oltSlotId':
						if ( ( !digits( $( '#oltSlotId' ).val() ) ) || ( $( '#oltSlotId' ).val() < 1 || $( '#oltSlotId' ).val() > 24 ) ) {
							if ( !digits( $( '#oltSlotId' ).val() ) ) {
								flag = false;
								$( '#oltSlotId' ).next( 'font' ).show().html( "Please Provide only digits" ).addClass( 'errorTextbox' );

							}
							if ( $( '#oltSlotId' ).val() < 1 || $( '#oltSlotId' ).val() > 24 ) {
								flag = false;
								$( '#oltSlotId' ).next( 'font' ).show().html( "Please Provide digits between 1-24" ).addClass( 'errorTextbox' );

							}
						}
						else {
							flag = true;
						}
						break;
					case 'oltPortId':
						if ( ( !digits( $( '#oltPortId' ).val() ) ) || ( $( '#oltPortId' ).val() < 1 || $( '#oltSlotId' ).val() > 16 ) ) {
							if ( !digits( $( '#oltPortId' ).val() ) ) {
								flag = false;
								$( '#oltPortId' ).next( 'font' ).show().html( "Please Provide only digits" ).addClass( 'errorTextbox' );
							}
							if ( $( '#oltPortId' ).val() < 1 || $( '#oltSlotId' ).val() > 16 ) {
								flag = false;
								$( '#oltPortId' ).next( 'font' ).show().html( "Please Provide digits between 1-16" ).addClass( 'errorTextbox' );

							}
						}
						else {
							flag = true;
						}
						break;
					case 'oltSubPortId':
						if ( ( !digits( $( '#oltSubPortId' ).val() ) ) || ( $( '#oltSubPortId' ).val() < 1 || $( '#oltSlotId' ).val() > 64 ) ) {
							if ( !digits( $( '#oltSubPortId' ).val() ) ) {
								flag = false;
								$( '#oltSubPortId' ).next( 'font' ).show().html( "Please Provide only digits" ).addClass( 'errorTextbox' );
							}
							if ( $( '#oltSubPortId' ).val() < 1 || $( '#oltSlotId' ).val() > 64 ) {
								flag = false;
								$( '#oltSubPortId' ).next( 'font' ).show().html( "Please Provide digits between 1-64" ).addClass( 'errorTextbox' );
							}
						}
						else {
							flag = true;
						}
						break;
					case 'serviceTypeId':
						/*
						 * if(!oltNodeIdValidator){ $('#serviceTypeId').parent().next('font').show().html("Please Provide correct Olt Node Value").addClass('errorTextbox'); }
						 */
						break;
					case 'ontType':
						/*
						 * if(!oltNodeIdValidator){ $('#ontType').parent().next('font').show().html("Please Provide correct Olt Node Value").addClass('errorTextbox'); }
						 */
						break;
				}
				if ( flag ) {
					break;
				}
			}
			else {

			}

		}
		if ( ( !flag ) && ( forloopCounter == 0 ) ) {
			$( '#oltNodeId' ).parent().next( 'font' ).show().html( "Please Select at least one value from" + validateArrayForOption82 ).addClass(
					'errorTextbox' );

		}
	}
	else {

	}
	// flag=false;

	if ( flag )
		return true;
	else
		return false;
}

function ISRUploadDocument( url, module, crfId, customerId ){
	var popupString = "<a href='#' id='closePopup'>X</a>"
			+ "<h4>Upload Reference Document</h4>"
			+ "<iframe src='"
			+ url
			+ "/files/upload/"
			+ encodeURIComponent( module )
			+ "/"
			+ crfId
			// + "&CustomerID="
			// + customerId
			+ "' scrolling='yes' frameborder='0' style='border: none; overflow: hidden; width: 755px; height: 400px;' allowTransparency='true' ></iframe>";
	document.getElementById( "isrUploadDocID" ).innerHTML = popupString;
	$( '.modelPopupDiv, .overlayDiv' ).fadeIn();
	$( '#closePopup' ).click( function( e ){
		$( '.modelPopupDiv, .overlayDiv' ).fadeOut();
	} );

}
