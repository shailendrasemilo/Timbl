$( document )
		.ready(
				function(){

					$.validator.addMethod( "roleGroupName", function( value, element ){
						return this.optional( element ) || validateNames( value );
					} );
					$.validator.addMethod( "maxlength6", function( value, element ){
						return this.optional( element ) || validatePin( value );
					} );
					$.validator.addMethod( "parameterGroupName", function( value, element ){
						return this.optional( element ) || validateNames( value );
					} );
					$.validator.addMethod( "host", function( value, element ){
						return this.optional( element ) || validateNotAllowSpecialCharacter( value );
					} );
					$.validator.addMethod( "emailId", function( value, element ){
						return this.optional( element ) || validateEmail( value );
					}, "Enter a valid Email ID" );
					$( "#ESform" ).validate(
							{
								rules : {
									'emailServerPojo.display' : {
										required : true
									}, 'emailServerPojo.subCategory' : {
										required : true
									}, 'emailServerPojo.host' : {
										required : true, host : true
									}, 'emailServerPojo.port' : {
										required : true, number : true
									}, 'emailServerPojo.userid' : {
										required : true
									}, 'emailServerPojo.password' : {
										required : true
									}, 'emailServerPojo.from' : {
										required : true, emailId : true
									}, 'emailServerPojo.replyTo' : {
										/* required : true, */
										emailId : true
									}, 'emailServerPojo.connectionTimeOut' : {
										required : true, number : true
									}, 'emailServerPojo.socketTimeOut' : {
										required : true, number : true
									}
								/*
								 * , 'emailServerPojo.retryValue' : { required : true, number : true }
								 */
								},
								messages : {
									'emailServerPojo.display' : {
										required : "<font class='errorCreateUser'>Please Provide Display</font>"
									},
									'emailServerPojo.subCategory' : {
										required : "<font class='errorCreateUser'>Please Provide Email Server Name</font>"
									},
									'emailServerPojo.host' : {
										required : "<font class='errorCreateUser'>Please Provide SMTP Host</font>",
										host : "<font class='errorCreateUser'>Please Provide valid SMTP Host</font>"
									},
									'emailServerPojo.port' : {
										required : "<font class='errorCreateUser'>Please Provide SMTP Port</font>",
										number : "<font class='errorCreateUser'>Please Provide Only Numbers</font>"
									},
									'emailServerPojo.userid' : {
										required : "<font class='errorCreateUser'>Please Provide SMTP User</font>"
									},
									'emailServerPojo.password' : {
										required : "<font class='errorCreateUser'>Please Provide SMTP Password</font>"
									},
									'emailServerPojo.from' : {
										required : "<font class='errorCreateUser'>Please Provide From Email ID</font>",
										emailId : "<font class='errorCreateUser'>Please Provide valid Email ID</font>"
									},
									'emailServerPojo.replyTo' : {
										// required : "<font class='errorCreateUser'>Please Provide Reply To Email ID</font>",
										emailId : "<font class='errorCreateUser'>Please Provide valid Email ID</font>"
									},
									'emailServerPojo.connectionTimeOut' : {
										required : "<font class='errorCreateUser'>Please Provide Connection TimeOut</font>",
										number : "<font class='errorCreateUser'>Please Provide Only Numbers</font>"
									},
									'emailServerPojo.socketTimeOut' : {
										required : "<font class='errorCreateUser'>Please Provide Socket TimeOut</font>",
										number : "<font class='errorCreateUser'>Please Provide Only Numbers</font>"
									}
								/*
								 * , 'emailServerPojo.retryValue' : { required : "<font class='errorCreateUser'>Please Provide Retry Value</font>", number : "<font class='errorCreateUser'>Please Provide Only Numbers</font>" }
								 */
								}
							} );
					$( "#roleGroup" )
							.validate(
									{
										rules : {
											roleGroupName : "required roleGroupName", roleGroupDescription : {
												maxlength : 128
											}
										},
										messages : {
											roleGroupName : {
												required : "<font class='errorCreateUser'>Please provide 'Role Group Name'</font>",
												roleGroupName : "<font class='errorCreateUser'>Alpha-Numeric(3-50) and (_) underscore allowed, No other special character allowed.</font>"
											}, roleGroupDescription : {
												maxlength : "<font class='errortextArea'>'Description' can only be 128 characters long </font>"
											}
										}
									} );

					$( "#parameterGroup" )
							.validate(
									{
										rules : {
											parameterGroupName : "required parameterGroupName", parameterGroupDescription : {
												maxlength : 128
											}
										},
										messages : {
											parameterGroupName : {
												required : "<font  class='errorCreateUser'>Please provide 'Parameter Group Name'</font>",
												parameterGroupName : "<font  class='errorCreateUser'>Alpha-Numeric(3-50) and (_) underscore allowed, No other special character allowed. </font>"
											}, parameterGroupDescription : {
												maxlength : "<font  class='errortextArea'>'Description' can only be 128 characters long </font>"
											}
										}
									} );
					// search Email Server
					$( '#ESsearchEmailServerButton' ).click( function(){
						if ( $( '#ESsearchForm' ).valid() ) {
							document.forms[ 1 ].action = "emailServer.do?method=searchEmailServer";
							document.forms[ 1 ].submit();
						}
					} );
					// create Email Server
					$( '#EScreateEmailServerButton' ).click( function(){
						if ( $( '#ESform' ).valid() ) {
							var flag = document.getElementById( "createFlag" ).value;
							var answer = false;
							if ( flag == "create" ) {
								answer = confirm( "Are you sure you want to create Email Server?" );
							}
							else if ( flag == "update" ) {
								answer = confirm( "Are you sure you want to update Email Server?" );
							}
							if ( answer ) {
								document.forms[ 1 ].action = "emailServer.do?method=createEmailServer";
								document.forms[ 1 ].submit();
							}
						}
					} );
					// create role group
					$( '#submit_createRoleGroup' ).click( function(){
						if ( $( "#roleGroup" ).valid() ) {
							var answer = confirm( "Please confirm if you want to create role group?" );
							if ( answer ) {
								document.forms[ 1 ].action = "roleGroup.do?method=createRoleGroup";
								document.forms[ 1 ].submit();
							}
						}
					} );
					// update role group
					$( '#submit_modifyRoleGroup' ).click( function(){
						if ( $( "#roleGroup" ).valid() ) {
							var answer = confirm( "Please confirm if you want to modify role group?" );
							if ( answer ) {
								document.forms[ 1 ].action = "roleGroup.do?method=modifyRoleGroup";
								document.forms[ 1 ].submit();
							}
						}
					} );

					$( '#submit_UpdateParameterGroup' ).click( function(){
						var answer = confirm( "Please confirm if you want to modify parameter group?" );
						if ( answer ) {
							document.forms[ 1 ].action = "parameterGroup.do?method=modifyParameterGroup";
							document.forms[ 1 ].submit();
						}
					} );

					$( "#CACform" ).validate(
							{
								rules : {
									'alertMasterPojo.maxRetry' : {
										required : true, number : true
									}, 'alertMasterPojo.retryTimeInterval' : {
										required : true, number : true
									}, 'alertMasterPojo.traiStart' : {
										required : true, number : true, min : 0, max : 23
									}, 'alertMasterPojo.traiEnd' : {
										required : true, number : true, min : 0, max : 23
									}, 'alertMasterPojo.inventoryEmail' : {
										required : true, email : true, emailId : true
									}, 'alertMasterPojo.cmsStatusEmail' : {
										required : true, email : true, emailId : true
									},
								},
								messages : {
									'alertMasterPojo.maxRetry' : {
										required : "<font class='errorCreateUser'>Please Provide Maximum Retry</font>",
										number : "<font class='errorCreateUser'>Please Provide Number</font>"
									},
									'alertMasterPojo.retryTimeInterval' : {
										required : "<font class='errorCreateUser'>Please Provide </font>",
										number : "<font class='errorCreateUser'>Please Provide Number</font>"
									},
									'alertMasterPojo.traiStart' : {
										required : "<font class='errorCreateUser'>Please Provide TRAI Start</font>",
										number : "<font class='errorCreateUser'>Please Provide Number</font>",
										min : jQuery.format( "Please enter number between 0 and 23 (included)" ),
										max : jQuery.format( "Please enter number between 0 and 23 (included)" ),
									},
									'alertMasterPojo.traiEnd' : {
										required : "<font class='errorCreateUser'>Please Provide TRAI End</font>",
										number : "<font class='errorCreateUser'>Please Provide  Number</font>",
										min : jQuery.format( "Please enter number between 0 and 23 (included)" ),
										max : jQuery.format( "Please enter number between 0 and 23 (included)" ),
									},
									'alertMasterPojo.inventoryEmail' : {
										required : "<font class='errorCreateUser'>Please Provide Inventory Email</font>",
										email : "<font class='errorCreateUser'>Please Provide valid Inventory Email</font>",
										emailId : "<font class='errorCreateUser'>Please Provide valid Inventory Email</font>",
									},
									'alertMasterPojo.cmsStatusEmail' : {
										required : "<font class='errorCreateUser'>Please Provide CMS Status Email</font>",
										email : "<font class='errorCreateUser'>Please Provide valid CMS Status Email</font>",
										emailId : "<font class='errorCreateUser'>Please Provide valid CMS Status Email</font>",
									}
								}
							} );

					$( '#CACsubmit_button' ).click( function(){
						if ( $( "#CACform" ).valid() ) {
							var answer = confirm( "Are you sure you want to update Alert Configurations?" );
							if ( answer == true ) {
								document.forms[ 1 ].action = "alertsConfiguration.do?method=createAlertsConfig";
								document.forms[ 1 ].submit();
							}
						}
					} );

					$( '#submit_CreateParameterGroup' ).click( function(){
						if ( $( "#parameterGroup" ).valid() ) {
							var answer = confirm( "Please confirm if you want to create parameter group?" );
							if ( answer ) {
								document.forms[ 1 ].action = "parameterGroup.do?method=createParameterGroup";
								document.forms[ 1 ].submit();
							}
						}
					} );
					// // ADD and DElete
					$( '.close' ).click( function(){
						$( this ).parent().parent().fadeOut();
					} );
					$( '#addParameter span' ).click( function(){
						// $('.add_role_group').clone().appendTo('#role_group_view');
						addParameter();

					} );
					$( '#add' ).click( function(){
						addAccessGroup();
					} );
					$( '#deleteAccessRowFrom_RoleGroupPage' ).click( function(){
						deleteAccessGroup();
					} );
					$( '#addParameterRow' ).click( function(){
						addAccessGroupParameter();
					} );
					// //register external project
					$.validator.addMethod( "extProjectName", function( value, element ){
						return this.optional( element ) || validateNames( value );
					} );
					$( "#registerProject" )
							.validate(
									{
										rules : {
											extProjectName : "required extProjectName", projectDesc : {
												maxlength : 128
											}
										},
										messages : {
											extProjectName : {
												required : "<font class='errorCreateUser'>Please provide 'Project Name'</font>",
												extProjectName : "<font class='errorCreateUser' style='width: 423px !important;'>Alpha-Numeric(3-50) and (_) underscore allowed, No other special character allowed. </font>"
											}, projectDesc : {
												maxlength : "<font class='errortextArea'>'Description' can only be 128 characters long </font>"
											}
										}
									} );
					$( '#regProject' ).click( function(){
						if ( $( "#registerProject" ).valid() ) {
							var answer = confirm( "Please confirm if you want to register project?" );
							if ( answer ) {
								document.forms[ 1 ].action = "crmProject.do?method=rgstrExtrnlProject";
								document.forms[ 1 ].submit();
							}
						}
					} );
					$( '#IDsearchRoleGroup' ).click( function(){
						$( '.loadingPopup' ).removeClass( 'hidden' );
						document.forms[ 1 ].action = "roleGroup.do?method=searchRoleGroup";
						document.forms[ 1 ].submit();
					} );
					$( '#IDsearchParameterGroup' ).click( function(){
						$( '.loadingPopup' ).removeClass( 'hidden' );
						document.forms[ 1 ].action = "parameterGroup.do?method=searchParameterGroup";
						document.forms[ 1 ].submit();
					} );
					$( "a#userprofile" ).click( function(){
						$( "#lightbox, #lightbox-panel" ).fadeIn( 300 );
					} );
					$( "a#close-panel" ).click( function(){
						$( "#lightbox, #lightbox-panel" ).fadeOut( 300 );
					} );
					$( '.search' ).hide();
					//					
					$( '#searchProject' ).click( function(){
						$( '.loadingPopup' ).removeClass( 'hidden' );
						document.forms[ 1 ].action = "crmProject.do?method=searchExtrnlProject";
						document.forms[ 1 ].submit();
					} );
					// Server side error remove at textbox empty
					if ( $( '#extProjectName' ).val() == "" ) {
						$( '.addServerSide-error' ).hide();
					}
					// partner registration page js
					// $('.partnerERPCode').hide();

					/*
					 * $('#addedInERP').bind('change', function() { if($('#addedInERP').val()=='C') { $('.partnerERPCode').fadeIn(); $('.partnerERPCode input').removeAttr('disabled'); } else { $('.partnerERPCode').hide(); $('.partnerERPCode input').attr('disabled', 'disabled'); }
					 * 
					 * });
					 */
					$.validator.addMethod( "partnerName", function( value, element ){
						return this.optional( element ) || validateUserPartnerName( value.trim() );
					}, "" );

					$.validator.addMethod( "chiefExFirstName", function( value, element ){
						return this.optional( element ) || validateUserPartnerName( value.trim() );
					}, "" );

					$.validator.addMethod( "chiefExLastName", function( value, element ){
						return this.optional( element ) || validateUserPartnerName( value.trim() );
					}, "" );

					$.validator.addMethod( "contactFirstName", function( value, element ){
						return this.optional( element ) || validateUserPartnerName( value.trim() );
					}, "" );

					$.validator.addMethod( "contactLastName", function( value, element ){
						return this.optional( element ) || validateUserPartnerName( value.trim() );
					}, "" );

					$.validator.addMethod( "partnerMobile", function( value, element ){
						return this.optional( element ) || validateMobile( value );
					}, "" );
					$.validator.addMethod( "chiefMobile", function( value, element ){
						return this.optional( element ) || validateMobile( value );
					}, "" );
					$.validator.addMethod( 'relatedNextraDept', function( value ){
						return ( value != '0' );
					} );
					$.validator.addMethod( 'partnerState', function( value ){
						return ( value != '0' );
					} );
					$.validator.addMethod( 'partnerCity', function( value ){
						return ( value != '0' );
					} );
					/*
					 * $.validator.addMethod('addedInERP', function (value) { return (value != '0'); });
					 */

					$( "#partnerRegistration" ).validate(
							{
								rules : {
									businessType : {
										required : true
									}, partnerType : {
										required : true
									}, boardedDate : {
										required : true
									}, partnerName : {
										required : true
									}, chiefExFirstName : {
										required : true, chiefExFirstName : true
									}, chiefExLastName : {
										required : true, chiefExLastName : true
									}, chiefDesignation : {
										required : true, minlength : 3
									}, chiefMobile : {
										required : true, digits : true, chiefMobile : true
									},  relatedNextraDept : {
										relatedNextraDept : true
									}, contactFirstName : {
										required : true, contactFirstName : true
									}, contactLastName : {
										required : true, contactLastName : true
									}, partnerPhone : {
										required : true, digits : true
									}, stdCode : {
										required : true, digits : true
									}, partnerExFirstName : {
										required : true
									}, partnerExLastName : {
										required : true
									}, partnerEmail : {
										required : true, email : true
									}, partnerMobile : {
										required : true, digits : true, partnerMobile : true
									}, partnerFax : {
										required : true
									}, partnerAddress1 : {
										required : true
									}, partnerCity : {
										partnerCity : true
									}, partnerState : {
										partnerState : true
									}, partnerPincode : {
										required : true, maxlength6 : true
									}, currentStatus : {
										required : true
									}, addedERP : {
										required : true
									},
								/*
								 * partnerERPCode:{ required:true },
								 */
								/*
								 * addedInERP:{ addedInERP:true },
								 */
								// partnerCode : {
								// required : true
								// }
								},
								messages : {
									businessType : {
										required : "<div class='businesstype_error'>Please select 'Business Type'</div>"
									},
									partnerType : {
										required : "<div class='partner_error'>Please select 'Partner Type'</div>"
									},
									boardedDate : {
										required : "<font class='errorCreateUser'>Please select 'Date'</font>"
									},
									partnerName : {
										required : "<font class='errorCreateUser'>Please provide 'Name of Partner'</font>",
									},
									chiefExFirstName : {
										required : "<div class='nameError'>Please provide 'First Name'</div>",
										chiefExFirstName : "<div class='nameError'>Please provide only alphabets</div>"
									},
									chiefExLastName : {
										required : "<div class='nameError'>Please provide 'Last Name'</div>",
										chiefExLastName : "<div class='nameError'>Please provide only alphabets</div>"
									},
									chiefDesignation : {
										required : "<font class='errorCreateUser'>Please provide 'Chief's Designation'</font>",
										minlength : "<font class='errorCreateUser'>Please provide more than 3 characters'</font>"
									},
									chiefMobile : {
										required : "<font class='errorCreateUser'>Please provide 'Mobile Number'</font>",
										digits : "<font class='errorCreateUser'>Please provide only digits</font>",
										chiefMobile : "<font class='errorCreateUser'>'Mobile Number' " + getMobileMesg() + "</font>"
									},
									relatedNextraDept : {
										relatedNextraDept : "<div class='relatedDept_error'>Please select 'Related Department'</div>"
									},
									partnerExFirstName : {
										required : "<div class='nameError'>Please provide 'Partner Executive First Name'</div>"
									},
									partnerExLastName : {
										required : "<div class='nameError'>Please provide 'Partner Executive Last Name</div>"
									},
									partnerEmail : {
										required : "<font class='errorCreateUser'>Please provide 'Email ID'</font>",
										email : "<font class='errorCreateUser'>Please provide valid 'Email ID'</font>"
									},
									partnerMobile : {
										required : "<font class='errorCreateUser'>Please provide 'Mobile Number'</font>",
										digits : "<font class='errorCreateUser'>Please provide only digits</font>",
										partnerMobile : "<font class='errorCreateUser'>'Mobile Number' " + getMobileMesg() + "</font>"
									},
									partnerFax : {
										required : "<font class='errorCreateUser'>Please provide 'Fax Number'</font>"
									},
									partnerAddress1 : {
										required : "<font class='addressError'>Please provide 'Partner Address' details</font>"
									},
									partnerCity : {
										partnerCity : "<font class='errorCreateUser'>Please select 'Partner City'</font>"
									},
									partnerState : {
										partnerState : "<div class='errorCreateUserSelect'>Please select 'Partner State'</div>"
									},
									partnerPincode : {
										required : "<font class='errorCreateUser'>Please provide 'Pin Code'</font>",
										maxlength6 : "<font class='errorCreateUser'>Pincode must be of 6 digits only.</font>"
									},
									currentStatus : {
										required : "<div class='errorCreateUserSelect'>Please provide 'Current Status'</div>"
									},
									addedERP : {
										required : "<div class='errorCreateUserSelect'>Please select 'Added In Erp'</div>"
									},
									/*
									 * partnerERPCode:{ required: "<font class='errorCreateUser'>Please provide 'Partner ERP Code'</font>" },
									 */
									contactFirstName : {
										required : "<div class='nameError'>Please provide 'First Name'</div>",
										contactFirstName : "<div class='nameError'>Please provide only alphabets</div>"
									},
									contactLastName : {
										required : "<div class='nameError'>Please provide 'Last Name'</div>",
										contactLastName : "<div class='nameError'>Please provide only alphabets</div>"
									},
									stdCode : {
										required : "<div class='std_error'>Please provide 'Std Code'</div>",
										digits : "<div class='std_error'>Please provide only digits</div>"
									},
									partnerPhone : {
										required : "<div class='errorCreateUser'>Please provide 'Phone Number'</div>",
										digits : "<div class='errorCreateUser'>Please provide only digits</div>"
									},
								/*
								 * addedInERP:{ addedInERP: "<div class='errorCreateUserSelect'>Please select 'Added In ERP'</div>" },
								 */
								// partnerCode : {
								// required : "<font class='errorCreateUser'>Please provide 'Partner Code'</font>"
								// }
								}
							} );
					$( '#managePartnerAdd' ).click( function(){
						if ( $( "#partnerRegistration" ).valid() ) {
							var answer = confirm( "Please confirm if you want to register partner?" );
							if ( answer ) {
								document.forms[ 1 ].action = "partnerManagement.do?method=addPartner";
								document.forms[ 1 ].submit();
							}
						}
					} );
					$( '#managePartnerUpdate' ).click( function(){
						if ( $( "#partnerRegistration" ).valid() ) {
							var answer = confirm( "Please confirm if you want to update partner?" );
							if ( answer ) {
								document.forms[ 1 ].action = "partnerManagement.do?method=modifyPartner";
								document.forms[ 1 ].submit();
							}
						}
					} );
					$( '#submit_searchPartner' ).click( function(){
						$( '.loadingPopup' ).removeClass( 'hidden' );
						document.forms[ 1 ].action = "partnerManagement.do?method=searchPartner";
						document.forms[ 1 ].submit();
					} );
					$( '#partnerStateId' ).change( function(){
						if ( $( this ).val() != "" ) {
							$( '#partnerCityId' ).removeAttr( 'disabled' );
							populateCityByState( 'partnerCityId', $( this ).val() );
						}
						else {
							$( '#partnerCityId' ).attr( 'disabled', 'disabled' );
							removeList( 'partnerCityId' );
						}
					} );
					// for sms config
					$.validator.addMethod( "smsGatewayPojo.subCategory", function( value, element ){
						return this.optional( element ) || /^[a-zA-Z0-9_]{3,50}$/i.test( value );
					}, "Alphabets, no special character/numbers" );

					$.validator.addMethod( "smsGatewayPojo.userid", function( value, element ){
						return this.optional( element ) || /^[a-zA-Z_]{3,128}$/i.test( value );
					}, "Alphabets, no special character/numbers" );
					$( "#smsform" ).validate(
							{
								rules : {
									'smsGatewayPojo.subCategory' : {
										required : true, 'smsGatewayPojo.subCategory' : true
									}, 'smsGatewayPojo.url' : {
										required : true, url : true
									}, 'smsGatewayPojo.userid' : {
										required : true, 'smsGatewayPojo.userid' : true
									}, 'smsGatewayPojo.password' : {
										required : true
									}, 'smsGatewayPojo.connectionTimeOut' : {
										required : true, digits : true
									}, 'smsGatewayPojo.socketTimeOut' : {
										required : true, digits : true
									}/*
										 * , 'smsGatewayPojo.retryValue' : { required : true, digits : true }
										 */, 'smsGatewayPojo.response' : {
										required : true
									}
								},
								messages : {
									'smsGatewayPojo.subCategory' : {
										required : "<font class='errorCreateUser'>Please Provide Sms category</font>",
										'smsGatewayPojo.subCategory' : "<font class='errorCreateUser'>Please Provide Sms category</font>"
									},

									'smsGatewayPojo.userid' : {
										required : "<font class='errorCreateUser'>Please provide User ID</font>",
										'smsGatewayPojo.userid' : "<font class='errorCreateUser'>Please provide a valid UserID</font>"
									},
									'smsGatewayPojo.url' : {
										required : "<font class='errorCreateUser'>Please provide URL</font>",
										url : "<font class='errorCreateUser'>Please provide a valid URL</font>"
									},
									'smsGatewayPojo.password' : {
										required : "<font class='errorCreateUser'>Please provide Password.</font>"
									},
									'smsGatewayPojo.connectionTimeOut' : {
										required : "<font class='errorCreateUser'>Please provide connection time out.</font>",
										digits : "<font class='errorCreateUser'>Please provide a valid connection time out.</font>"
									},
									'smsGatewayPojo.socketTimeOut' : {
										required : "<font class='errorCreateUser'>Please provide socket time out</font>",
										digits : "<font class='errorCreateUser'>Please provide a valid socket time out</font>"
									}/*
										 * ,
										 * 
										 * 'smsGatewayPojo.retryValue' : { required : "<font class='errorCreateUser'>Please provide retry value.</font>", digits : "<font class='errorCreateUser'>Please provide a valid retry value.</font>" }
										 */,

									'smsGatewayPojo.response' : {
										required : "<font class='errorCreateUser'>Please provide response status</font>"
									}
								}
							} );

					$.validator.addMethod( '', function( value ){
						return ( value != '0' );
					} );
					$.validator.addMethod( '', function( value ){
						return ( value != '0' );
					} );
					$.validator.addMethod( '', function( value ){
						return ( value != '0' );
					} );

					$( '#SMScreateButton' ).click( function(){
						if ( $( "#smsform" ).valid() ) {
							var answer = confirm( "Are you sure you want to create/update sms gateway?" );
							if ( answer ) {
								document.forms[ 1 ].action = "smsManagement.do?method=smsConfigurationOperation";
								document.forms[ 1 ].submit();
							}
						}
					} );
					//
					$( '#addServicePartner' ).click(
							function(){
								if ( $( '#businessTypeSPID' ).val() == "0" ) {
									$( 'select#businessTypeSPID' ).next( 'font' ).show().html( 'Please Select Product Type.' ).addClass(
											'errorCreateUserSelect' );
								}
								else {
									$( '#businessTypeSPID' ).next( 'font' ).hide();
									addServicePartnerRow();
								}
							} );

					$( '#submit_AssignPartner' ).click( function(){
						var answer = confirm( "Are you sure you want to create/update partner mapping ?" );
						if ( answer ) {
							document.forms[ 1 ].action = "partnerManagement.do?method=assignServicePartner";
							document.forms[ 1 ].submit();
						}
					} );
					$( '#productDetailsList' ).bind( 'change', function(){
						var productTypeVal = $( this ).val();
						viewOltTypeDetails( productTypeVal );
					} );
					$( '#oltTypeList' ).bind( 'change', function(){
						var oltType = $( this ).val();
						viewOption82Details( oltType );
					} );
					$( '#addParameterOption82' ).click( function(){
						addNassportRow();
					} );
					$( '#oltTypeSubmitButton' ).click( function(){
						option82Operation();
					} );
					$( '#nasportIdSubmitButton' ).click( function(){
						nasportIdOperation();
					} );
					$( '#submit_AddNPMobile' ).click( function(){
						var answer = confirm( "Are you sure you want to add multiple mobile No with network partner ?" );
						if ( answer ) {
							document.forms[ 1 ].action = "partnerManagement.do?method=addNPMobile";
							document.forms[ 1 ].submit();
						}
					} );
					$( '#submit_AddUserAlertMobileEmail' ).click( function(){
						var answer = confirm( "Are you sure you want to add mobile No and Email ?" );
						if ( answer ) {
							document.forms[ 1 ].action = "smsAlert.do?method=addUserAlertDetails";
							document.forms[ 1 ].submit();
						}
					} );

					if ( $( "#val_error" ).text() != '' && $( "#val_error" ).text() != undefined && $( "#val_error" ).text() != null ) {
						var i = 0;
						while ( document.getElementsByName( "crmNpMobileList[" + i + "].editable" ) != 'undefined'
								&& document.getElementsByName( "crmNpMobileList[" + i + "].editable" ) != null
								&& document.getElementsByName( "crmNpMobileList[" + i + "].editable" ).length != 0 ) {
							var editChkBox = document.getElementsByName( "crmNpMobileList[" + i + "].editable" );
							var mobileNo = document.getElementsByName( "crmNpMobileList[" + i + "].mobileNo" );
							var emailId = document.getElementsByName( "crmNpMobileList[" + i + "].emailId" );
							var status = document.getElementsByName( "crmNpMobileList[" + i + "].status" );
							if ( editChkBox[ 0 ].checked ) {
								mobileNo[ 0 ].readOnly = false;
								$( mobileNo ).removeClass( 'gray_text' );
								emailId[ 0 ].readOnly = false;
								$( emailId ).removeClass( 'gray_text' );
								status[ 0 ].onclick = "";
								status[ 1 ].onclick = "";
							}

							i++;
						}

					}

				} );

function populateRole(){
	crmDwr.getRoles( function( list ){
		addRoles( "roleName", list );
	} );
	function addRoles( id, list ){
		var select = document.getElementById( id );
		if ( select != null ) {
			dwr.util.removeAllOptions( id );
			dwr.util.addOptions( id, list, "roleId", "roleName" );
		}
	}
}
function addAccessGroup(){
	document.forms[ 1 ].action = "roleGroup.do?method=addRoleGroupRow";
	document.forms[ 1 ].submit();
}

// here we are deleteing row on click of cross button
function deleteAccessGroupFromRole( inIndex ){
	document.getElementById( "rowIndex_createRoleGroup" ).value = inIndex;
	document.forms[ 1 ].action = "roleGroup.do?method=deleteRoleGroupRow";
	document.forms[ 1 ].submit();
}

function deleteAccessGroupFromParameter( inIndex ){
	document.getElementById( "rowIndex_createParameterGroup" ).value = inIndex;
	document.forms[ 1 ].action = "parameterGroup.do?method=deleteParameterGroupRow";
	document.forms[ 1 ].submit();
}

function addAccessGroupParameter(){
	document.forms[ 1 ].action = "parameterGroup.do?method=addParameterGroupRow";
	document.forms[ 1 ].submit();
}

function addAccessGroupUpdate(){
	document.forms[ 1 ].action = "roleGroup.do?method=addRoleGroupRowUpdate";
	document.forms[ 1 ].submit();
}

function displayAlert( chkbox ){
	alert( "Value of CheckBox:" + chkbox + ":" );
}

function viewRole_RoleGroup( inRoleGroupId ){
	document.getElementById( "inRoleGroupId_searchUser" ).value = inRoleGroupId;
	window.open( "roleGroup.do?method=viewRoleGroup&inRoleGroupId=" + inRoleGroupId, null,
			"height=400,width=900,status=yes,toolbar=no,menubar=no,location=no,scrollbars=yes" );
}

function modify_RoleGroupPage( inRoleGroupId ){
	document.getElementById( "inRoleGroupId_searchUser" ).value = inRoleGroupId;
	document.forms[ 1 ].action = "roleGroup.do?method=modifyRoleGroupPage";
	document.forms[ 1 ].submit();
}

function modify_ParameterGroupPage( inRoleGroupId ){
	document.getElementById( "inParameterGroupId_searchParameter" ).value = inRoleGroupId;
	document.forms[ 1 ].action = "parameterGroup.do?method=modifyParameterGroupPage";
	document.forms[ 1 ].submit();
}

function change_RoleGroup( inRecordID, inStatus ){
	document.getElementById( "inRoleGroupId_searchUser" ).value = inRecordID;
	document.getElementById( "newStatus" ).value = inStatus;
	var answer = confirm( "Please confirm if you want to change role group status?" );
	if ( answer ) {
		document.forms[ 1 ].action = "roleGroup.do?method=changeRoleGroupStatus";
		document.forms[ 1 ].submit();
	}
}

function change_ParameterGroup( inRecordID, inStatus ){
	document.getElementById( "inParameterGroupId_searchParameter" ).value = inRecordID;
	document.getElementById( "status" ).value = inStatus;
	var answer = confirm( "Please confirm if you want to change parameter group status?" );
	if ( answer ) {
		document.forms[ 1 ].action = "parameterGroup.do?method=changeParameterGroupStatus";
		document.forms[ 1 ].submit();
	}
}

function viewParameter_ParameterGroup( inGroupId ){
	window.open( "parameterGroup.do?method=viewParameterGroup&inGroupId=" + inGroupId, null,
			"height=600,width=600,status=yes,toolbar=no,menubar=no,location=no" );
}
function addParameter(){
	document.forms[ 1 ].action = "crmProject.do?method=addExtProjectParameterRow";
	document.forms[ 1 ].submit();
}
function changeExtrProjectStatus( projectId, inStatus ){
	document.getElementById( "idProject" ).value = projectId;
	document.getElementById( "idStatus" ).value = inStatus;
	var answer = confirm( "Please confirm if you want to change project status!" );
	if ( answer ) {
		document.forms[ 1 ].action = "crmProject.do?method=changeExtrnalProjectStatus";
		document.forms[ 1 ].submit();
	}
}
function extrProjectOperation( projectId, method ){
	document.getElementById( "idProject" ).value = projectId;
	document.forms[ 1 ].action = "crmProject.do?method=" + method;
	document.forms[ 1 ].submit();
}
// function updateGroup() {
// document.forms[1].action = "roleGroup.do?method=modifyRoleGroup";
// document.forms[1].submit();
// }
function viewExtrProject( values, projectName, projectDesc, status ){
	var dataDisplay = document.getElementById( "rowDisplayData" );
	dataDisplay.innerHTML = generateTableForParameter( values, projectName, projectDesc, status );
	$( "#lightbox, #lightbox-panel" ).fadeIn( 300 );
}

function generateTableForParameter( dataRow, projectName, projectDesc, status ){
	var statusPrint;
	if ( status == 'A' ) {
		statusPrint = "Active";
	}
	else if ( status == 'I' ) {
		statusPrint = "Inactive";
	}
	else {
		statusPrint = "Deleted";
	}
	var tableData = "<p class='popUpRow1'><font class='popUpHead'>Project Name:</font> <font class='popUpText'>" + projectName + "</font></p> "
			+ "<p class='popUpRow2'><font class='popUpHead'>Project Description:</font> <font class='popUpText'>" + projectDesc + "</font></p>"
			+ "<p class='popUpRow1'><font class='popUpHead'>Status:</font> <font class='popUpText'>" + statusPrint + "</font></p>";
	var param = "<div class='displayTable'><p class='headerDisplayTable'> " + "<span>Parameter Name</span> " + "<span>Parameter Type</span>"
			+ "<span>Parameter Length</span>" + "<span>Parameter Status</span>" + "</p><div class='contentDisplayTable'>";

	var li = '';

	var rowData = SplitTheString( dataRow, ";" );
	if ( rowData != null ) {
		for ( var i = 0; i < rowData.length; i++ ) {

			var columnData = SplitTheString( rowData[ i ], "," );
			if ( columnData != null ) {
				if ( i % 2 == 0 ) {
					li += "<p class='rowFirst'>";
				}
				else {
					li += "<p class='rowSecond'>";
				}
				var q = 4;
				for ( var j = 0; j < columnData.length; j++ ) {
					if ( q - j == 1 ) {
						if ( columnData[ j ].trim() == 'A' ) {
							li += "<span>" + 'Active' + "</span>";
						}
						else if ( columnData[ j ].trim() == 'I' ) {
							li += "<span>" + 'Inactive' + "</span>";
						}
						q += 4;
					}
					else {
						li += "<span>" + columnData[ j ] + "</span>";
					}
				}
			}
			li += "</p>";
		}
	}
	else {
		li = "<p class='rowSecond'><span >Data not available</span></p>";
	}
	tableData += param + li + "</div></div>";
	return tableData;
}
function SplitTheString( CommaSepStr, splitString ){
	var ResultArray = null;
	if ( CommaSepStr != null ) {
		if ( CommaSepStr.indexOf( splitString ) >= 0 ) {
			ResultArray = CommaSepStr.split( splitString );
		}
	}
	return ResultArray;
}
function isIdAvailableAjax( extProjectName, exPrjAvail ){
	var projectName = document.getElementById( extProjectName ).value;
	if ( projectName != null ) {
		crwDwr.checkProjectNameAvailability( projectName, function( result ){
			isProjectNameAvailable( exPrjAvail, result );
		} );
		function isProjectNameAvailable( id, result ){
			var select = document.getElementById( id );
			if ( select != null ) {
				select.innerHtml = result;
			}
		}
	}
}
function updateExtrnalProject(){
	var answer = confirm( "Please confirm if you want to update project!" );
	if ( answer ) {
		document.forms[ 1 ].action = "crmProject.do?method=modifyCRMProject";
		document.forms[ 1 ].submit();
	}
	else {
		return false;
	}
}
// delete parameter when click on cross button
function deleteParameterFromProject( inIndex ){
	document.getElementById( "rowIndex_createparameter" ).value = inIndex;
	document.forms[ 1 ].action = "crmProject.do?method=deleteExtProjectParameterRow";
	document.forms[ 1 ].submit();
}
function editPartnerManagement( partnerId, method ){
	document.getElementById( "idPartner" ).value = partnerId;
	document.forms[ 1 ].action = "partnerManagement.do?method=" + method;
	document.forms[ 1 ].submit();
}
function viewPartnerManagement( inPartnerId ){
	document.getElementById( "idPartner" ).value = inPartnerId;
	window.open( "partnerManagement.do?method=viewPartner&inPartnerId=" + inPartnerId, null,
			"height=600,width=700,scrollbars=yes,toolbar=no,status=yes,menubar=no,location=no" );
}
function checkNumbers( rCount ){
	var paramLength = document.getElementsByName( "crmParameters[" + rCount + "].parameterLength" );
	if ( isNaN( paramLength[ 0 ].value ) ) {
		paramLength[ 0 ].value = "0";
	}
}
function option82Operation( type ){
	var answer;
	if ( type == 'olt' ) {
		answer = confirm( "Are you sure you want to create/update Option 82 string?" );
	}
	else if ( type == 'nasport' ) {
		answer = confirm( "Are you sure you want to create/update NAS-Port ID?" );
	}
	if ( answer ) {
		document.forms[ 1 ].action = "option82Management.do?method=option82Operation";
		document.forms[ 1 ].submit();
	}
	else {
		return false;
	}
}
function editEmailServer_ESsearchForm( alias ){
	document.getElementById( "alias_ESsearchForm" ).value = alias;
	document.forms[ 1 ].action = "emailServer.do?method=editEmailServerPage";
	document.forms[ 1 ].submit();
}
function populateCityByState( id, stateName ){
	$( '.loadingPopup' ).removeClass( 'hidden' );
	crmDwr.getCityByStateName( stateName, function( list ){
		addCity( id, list );
	} );
	function addCity( id, list ){
		var select = document.getElementById( id );
		if ( select != null ) {
			if ( list != null ) {
				dwr.util.removeAllOptions( id );
				dwr.util.addOptions( id, [
					"All Cities"
				] );
				dwr.util.addOptions( id, list, "cityName", "cityName" );
			}
			else {
				alert( "No city registered in system for selected state." );
				removeList( id );
			}
		}
		$( '.loadingPopup' ).addClass( 'hidden' );
	}
}

// feasibility on INA

var options = [
	{
		key : 0, value : 'Please Select'
	}
];

function populateCityByState( id, stateName, selectedCity ){
	if ( stateName == 0 || stateName == "" ) {
		dwr.util.removeAllOptions( 'feasibleCity' );
		dwr.util.removeAllOptions( 'feasiblArea' );
		dwr.util.removeAllOptions( 'feasibleLocality' );
		dwr.util.removeAllOptions( 'feasibleSociety' );
		dwr.util.removeAllOptions( id );
		dwr.util.addOptions( id, options, 'key', 'value' );
		// dwr.util.addOptions( 'feasibleCity', options, 'key', 'value' );
		dwr.util.addOptions( 'feasiblArea', options, 'key', 'value' );
		dwr.util.addOptions( 'feasibleLocality', options, 'key', 'value' );
		dwr.util.addOptions( 'feasibleSociety', options, 'key', 'value' );
	}
	else {
		$( '.loadingPopup' ).removeClass( 'hidden' );
		crmDwr.getCityByStateName( stateName, function( list ){
			addCity( id, list );
		} );
	}
	function addCity( id, list ){
		var current = document.getElementById( id ) != null;
		var select = current ? document.getElementById( id ) : parent.document.getElementById( id );
		if ( select != null ) {
			if ( list != null && current ) {
				dwr.util.removeAllOptions( id );
				dwr.util.addOptions( id, [
					"All Cities"
				] );
				dwr.util.addOptions( id, list, "cityName", "cityName" );
			}
			else if ( list != null && !current ) {
				parent.dwr.util.removeAllOptions( id );
				parent.dwr.util.addOptions( id, [
					"All Cities"
				] );
				parent.dwr.util.addOptions( id, list, "cityName", "cityName" );
			}
			else {
				alert( "No city registered in system for selected state." );
				current ? dwr.util.removeAllOptions( id ) : parent.dwr.util.removeAllOptions( id );
				current ? dwr.util.addOptions( id, options, 'key', 'value' ) : parent.dwr.util.addOptions( id, options, 'key', 'value' );
				// dwr.util.addOptions( 'feasibleCity', options, 'key', 'value' );
				if ( null != document.getElementById( "feasibleCity" ) && document.getElementById( "feasibleCity" ).length == 0 ) {
					dwr.util.addOptions( 'feasibleCity', options, 'key', 'value' );
				}
			}
			dwr.util.removeAllOptions( 'feasiblArea' );
			dwr.util.removeAllOptions( 'feasibleLocality' );
			dwr.util.removeAllOptions( 'feasibleSociety' );
			dwr.util.addOptions( 'feasiblArea', options, 'key', 'value' );
			dwr.util.addOptions( 'feasibleLocality', options, 'key', 'value' );
			dwr.util.addOptions( 'feasibleSociety', options, 'key', 'value' );
		}
		if ( null != selectedCity ) {
			current ? dwr.util.setValue( id, selectedCity ) : parent.dwr.util.setValue( id, selectedCity );
		}
		$( '.loadingPopup' ).addClass( 'hidden' );
	}
}
function populateAreaByCity( id, stateID, cityName ){
	$( '.loadingPopup' ).removeClass( 'hidden' );
	if ( cityName == 0 || cityName == 'All Cities' ) {
		dwr.util.removeAllOptions( 'feasiblArea' );
		dwr.util.removeAllOptions( 'feasibleLocality' );
		dwr.util.removeAllOptions( 'feasibleSociety' );
		dwr.util.addOptions( 'feasiblArea', options, 'key', 'value' );
		dwr.util.addOptions( 'feasibleLocality', options, 'key', 'value' );
		dwr.util.addOptions( 'feasibleSociety', options, 'key', 'value' );
	}
	else {
		var stateName = document.getElementById( stateID ).value;
		crmDwr.getAreaByCity( stateName, cityName, function( list ){
			addArea( id, list );
		} );
	}
	function addArea( id, list ){
		var select = document.getElementById( id );
		if ( select != null ) {
			if ( list != null ) {
				dwr.util.removeAllOptions( id );
				dwr.util.addOptions( id, [
					"All Area"
				] );
				dwr.util.addOptions( id, list, "area", "area" );
			}
			else {
				alert( "No area registered in system for selected city." );
				dwr.util.removeAllOptions( id );
				dwr.util.addOptions( 'feasiblArea', options, 'key', 'value' );
			}
			dwr.util.removeAllOptions( 'feasibleLocality' );
			dwr.util.removeAllOptions( 'feasibleSociety' );
			dwr.util.addOptions( 'feasibleLocality', options, 'key', 'value' );
			dwr.util.addOptions( 'feasibleSociety', options, 'key', 'value' );
		}
		$( '.loadingPopup' ).addClass( 'hidden' );
	}
}
function populateLocalityByArea( id, stateID, cityID, areaName ){
	$( '.loadingPopup' ).removeClass( 'hidden' );
	if ( areaName == 0 || areaName == 'All Area' ) {
		dwr.util.removeAllOptions( 'feasibleLocality' );
		dwr.util.removeAllOptions( 'feasibleSociety' );
		dwr.util.addOptions( 'feasibleLocality', options, 'key', 'value' );
		dwr.util.addOptions( 'feasibleSociety', options, 'key', 'value' );
	}
	else {
		var stateName = document.getElementById( stateID ).value;
		var cityName = document.getElementById( cityID ).value;
		crmDwr.getLocalityByArea( stateName, cityName, areaName, function( list ){
			addLocality( id, list );
		} );
	}
	function addLocality( id, list ){
		var select = document.getElementById( id );
		if ( select != null ) {
			if ( list != null ) {
				dwr.util.removeAllOptions( id );
				dwr.util.addOptions( id, [
					"All Locality"
				] );
				dwr.util.addOptions( id, list, "locality", "locality" );
			}
			else {
				alert( "No locality registered in system for selected area." );
				dwr.util.removeAllOptions( id );
				dwr.util.addOptions( 'feasibleLocality', options, 'key', 'value' );
			}
			dwr.util.removeAllOptions( 'feasibleSociety' );
			dwr.util.addOptions( 'feasibleSociety', options, 'key', 'value' );
		}
		$( '.loadingPopup' ).addClass( 'hidden' );
	}
}

function populateFeasibleSocieties( id, stateID, cityID, areaID, hiddenProduct ){
	$( '.loadingPopup' ).removeClass( 'hidden' );
	if ( areaID == 0 || areaID == 'All Area' ) {
		dwr.util.removeAllOptions( 'feasibleSociety' );
		dwr.util.addOptions( 'feasibleSociety', options, 'key', 'value' );
	}
	else {
		var stateName = document.getElementById( stateID ).value;
		var cityName = document.getElementById( cityID ).value;
		var areaName = document.getElementById( areaID ).value;
		var productName = document.getElementById( hiddenProduct ).value;
		crmDwr.getFeasibleSocieties( stateName, cityName, areaName, productName, function( list ){
			addSociety( id, list );
		} );
	}
	function addSociety( id, list ){
		var select = document.getElementById( id );
		if ( select != null ) {
			if ( list != null ) {
				dwr.util.removeAllOptions( id );
				dwr.util.addOptions( id, [
					"All Society"
				] );
				dwr.util.addOptions( id, list, "searchName", "searchName" );
			}
			else {
				alert( "No society registered in system for selected locality." );
				dwr.util.removeAllOptions( id );
				dwr.util.addOptions( 'feasibleSociety', options, 'key', 'value' );
				// removeList(id);
			}
		}
		$( '.loadingPopup' ).addClass( 'hidden' );
	}
}

function updateSMSGateway( smsAlias ){
	document.getElementById( "smsAlias" ).value = smsAlias;
	document.forms[ 1 ].action = "smsManagement.do?method=updateSMSGatewayPage";
	document.forms[ 1 ].submit();
}
function getOption82Value( showValue ){
	document.getElementById( "IDshowDivValue" ).value = showValue;
	document.forms[ 1 ].action = "option82Management.do?method=searchOption82Page";
	document.forms[ 1 ].submit();
}
function isNumeric( id ){
	var element = document.getElementById( id );
	if ( isNaN( element.value ) ) {
		element.value = "0";
	}
}
function clearBoardedDate(){
	document.getElementById( "IdToDate" ).value = "";
	document.getElementById( "IdFormDate" ).value = "";
}
function change_PartnerStatus( inRecordID, inStatus ){
	document.getElementById( "idPartner" ).value = inRecordID;
	document.getElementById( "newStatus" ).value = inStatus;
	var answer = confirm( "Please confirm if you want to change partner status?" );
	if ( answer ) {
		document.forms[ 1 ].action = "partnerManagement.do?method=modifyPartner";
		document.forms[ 1 ].submit();
	}
}
function assignServicePartner( partnerId, status, businessType ){
	document.getElementById( "idPartner" ).value = partnerId;
	document.getElementById( "newStatus" ).value = status;
	document.getElementById( "IDbusinessType" ).value = businessType;
	document.forms[ 1 ].action = "partnerManagement.do?method=assignServicePartnerPage";
	document.forms[ 1 ].submit();
}
function addServicePartnerRow(){
	document.forms[ 1 ].action = "partnerManagement.do?method=assignServicePartnerRow";
	document.forms[ 1 ].submit();
}
function deletePartnerRow( inIndex ){
	document.getElementById( "rowIndex_partner" ).value = inIndex;
	document.forms[ 1 ].action = "partnerManagement.do?method=deletePartnerRow";
	document.forms[ 1 ].submit();

}
function getServicePartnerList(){
	document.forms[ 1 ].action = "partnerManagement.do?method=assignServicePartnerPage";
	document.forms[ 1 ].submit();
}
function showRolesDetails(){
	window.open( 'roleGroup.do?method=showRolesDetails', 'newWindow', 'width=1000,height=450,scrollbars=yes,resizable=no,toolbar=no' );
}

function showParameterDetails(){
	window.open( 'parameterGroup.do?method=showDetails', 'newWindow', 'width=1000,height=450,scrollbars=yes,resizable=no,toolbar=no' );
}

function viewOltTypeDetails( productTypeVal ){
	document.getElementById( "oltTypeList" ).value = 0;
	if ( productTypeVal == "EOC" || productTypeVal == "RF" ) {
		getOption82Value( "n" );
	}
	else if ( productTypeVal == "BB" ) {
		$( '#oltTypeDiv' ).removeClass( 'hide1' );
		$( '#oltTypeDiv input, #oltTypeDiv select, #oltTypeDiv textarea' ).attr( 'disabled', false );
	}
}
function viewOption82Details( oltTypeValue ){
	if ( oltTypeValue == "E" ) {
		getOption82Value( "o" );
	}
	if ( oltTypeValue == "H" ) {
		getOption82Value( "o" );
	}
}
function addNassportRow(){
	document.forms[ 1 ].action = "option82Management.do?method=addNassportRow";
	document.forms[ 1 ].submit();
}

function deleteNasportRow( inIndex ){
	document.getElementById( "rowIndex_Nasport" ).value = inIndex;
	document.forms[ 1 ].action = "option82Management.do?method=deleteNassportRow";
	document.forms[ 1 ].submit();
}
function divHider( divs ){
	if ( divs.indexOf( ',' ) > -1 ) {
		divsArray = divs.split( ',' );
		for ( var i = 0; i < divsArray.length; i++ ) {
			document.getElementById( divsArray[ i ] ).className = 'hide1';
		}
	}
	else {
		document.getElementById( divs ).className = 'hide1';
	}
}

function uploadDocument( /* bpCode, */url, module, partnerName ){

	// if ( bpCode == "" || bpCode == null ) {
	// alert( "Please provide partnerCode" );
	// }
	// else
	if ( partnerName == "" || partnerName == null ) {
		alert( "Please provide Partner Name" );
	}
	else {
		var popupString = "<a href='#' id='closePopup'>X</a>"
				+ "<h4>Upload Reference Document</h4>"
				+ "<iframe src='"
				+ url
				+ "/files/upload/"
				+ encodeURIComponent( module )
				+ "/"
				+ encodeURIComponent( partnerName )
				// + "&CustomerName="
				// + encodeURI( partnerName )
				+ "' scrolling='yes' frameborder='0' style='border: none; overflow: hidden; width: 755px; height: 400px;' allowTransparency='true' ></iframe>";
		document.getElementById( "PartnerDocId" ).innerHTML = popupString;
		$( '.modelPopupDiv, .overlayDiv' ).fadeIn();
		$( '#closePopup' ).click( function( e ){
			$( '.modelPopupDiv, .overlayDiv' ).fadeOut();
		} );
	}

}
function holidaySubmit(){
	if ( $( '#holidayDate' ).val().trim() == '' ) {
		alert( "Please Provide Date." );
	}
	else if ( $( '#holidayDesc' ).val().trim() == '' ) {
		alert( "Please Provide Description." );
	}
	else {
		if ( confirm( "Please confirm if you want to submit ?" ) ) {
			document.forms[ 1 ].action = "crmRcaReason.do?method=addHoliday";
			document.forms[ 1 ].submit();
		}
	}
}
function changeHolidayStatus( id, recordId, status, date ){

	var holidayDate = new Date( date );
	var currDate = new Date();
	if ( currDate >= holidayDate ) {
		alert( "Only Future Date Allowed to Update Status ! " );
		$( '#' + id ).attr( 'checked', true );
	}
	else {
		if ( confirm( "Please confirm if you want to change the status ?" ) ) {
			$( '#recordId' ).val( recordId );
			$( '#status' ).val( status );
			$( '#holidayDate' ).val( date );
			document.forms[ 1 ].action = "crmRcaReason.do?method=addHoliday";
			document.forms[ 1 ].submit();
		}
		else {
			$( '#' + id ).attr( 'checked', true );
		}
	}
}
function addNPMobile( partnerId, status ){
	document.getElementById( "idPartner" ).value = partnerId;
	document.getElementById( "newStatus" ).value = status;
	document.forms[ 1 ].action = "partnerManagement.do?method=addNPMobilePage";
	document.forms[ 1 ].submit();
}
function deleteAddNPMobileRow( inIndex ){
	document.getElementById( "rowIndex_addNPMobile" ).value = inIndex;
	document.forms[ 1 ].action = "partnerManagement.do?method=deleteAddNPMobileRow";
	document.forms[ 1 ].submit();
}
function addNPMobileNewRow(){
	document.forms[ 1 ].action = "partnerManagement.do?method=addNPMobileNewRow";
	document.forms[ 1 ].submit();
}
/*
 * function addUserAlertPage( partnerId, status ){ document.getElementById( "idPartner" ).value = partnerId; document.getElementById( "newStatus" ).value = status; document.forms[ 1 ].action = "smsAlert.do?method=userAlertTemplatePage"; document.forms[ 1 ].submit(); }
 */
// #start For user alert page
function deleteUserAlertMobileEmailRow( inIndex ){
	document.getElementById( "rowIndex_addNPMobile" ).value = inIndex;
	document.forms[ 1 ].action = "smsAlert.do?method=deleteUserAlertMobileEmailRow";
	document.forms[ 1 ].submit();
}
function addUserAlertMobileEmailNewRow(){
	document.forms[ 1 ].action = "smsAlert.do?method=addUserAlertMobileEmailNewRow";
	document.forms[ 1 ].submit();
}

function enableUserAlert( rowNo ){
	var editChkBox = document.getElementsByName( "crmNpMobileList[" + rowNo + "].editable" );
	var mobileNo = document.getElementsByName( "crmNpMobileList[" + rowNo + "].mobileNo" );
	var emailId = document.getElementsByName( "crmNpMobileList[" + rowNo + "].emailId" );
	var status = document.getElementsByName( "crmNpMobileList[" + rowNo + "].status" );
	if ( editChkBox[ 0 ].checked ) {
		mobileNo[ 0 ].readOnly = false;
		$( mobileNo ).removeClass( 'gray_text' );
		emailId[ 0 ].readOnly = false;
		$( emailId ).removeClass( 'gray_text' );
		status[ 0 ].onclick = "";
		status[ 1 ].onclick = "";
	}
	else {
		mobileNo[ 0 ].readOnly = true;
		$( mobileNo ).addClass( 'gray_text' );
		emailId[ 0 ].readOnly = true;
		$( emailId ).addClass( 'gray_text' );
		status[ 0 ].onclick = returnFalse;
		status[ 1 ].onclick = returnFalse;
	}
}
// #end For user alert page
// function displayOLTType(oltTypeVariable) {
// if (oltTypeVariable.value == 'BB') {
// $('#oltTypeDiv').removeClass('hide1');
// }else{
// alert(oltTypeVariable.value);
// $('#oltTypeDiv').addClass('hide1');
// }
// }

function populateSocietyByArea( id, stateID, cityID, areaName ){
	// $( '.loadingPopup' ).removeClass( 'hidden' );

	if ( areaName == 0 || areaName == 'All Area' ) {
		dwr.util.removeAllOptions( 'feasibleLocality' );
		dwr.util.removeAllOptions( 'feasibleSociety' );
		// dwr.util.addOptions( 'feasibleLocality', options, 'key', 'value' );
		// dwr.util.addOptions( 'feasibleSociety', options, 'key', 'value' );
	}
	else {
		var stateName = document.getElementById( stateID ).value;
		var cityName = document.getElementById( cityID ).value;
		crmDwr.getSocietyByLocality( stateName, cityName, areaName, function( list ){
			addSociety( id, list );
		} );
	}
	function addSociety( id, list ){
		var select = document.getElementById( id );
		if ( select != null ) {
			if ( list != null ) {
				dwr.util.removeAllOptions( id );
				dwr.util.addOptions( id, [
					"All Society"
				] );
				dwr.util.addOptions( id, list, "localityName", "localityName" );
			}
			else {
				alert( "No locality registered in system for selected area." );
				dwr.util.removeAllOptions( id );
				dwr.util.addOptions( 'feasibleSociety', options, 'key', 'value' );
			}
			dwr.util.removeAllOptions( 'feasibleSociety' );
			dwr.util.addOptions( 'feasibleSociety', options, 'key', 'value' );
		}
		// $( '.loadingPopup' ).addClass( 'hidden' );
	}
}

function populateAllCitiesForRFS( id, stateId ){
	if ( stateId == null || stateId == '' || stateId == 'Please Select' || stateId == 0 ) {

		dwr.util.removeAllOptions( id );
		var areaId = document.getElementById( "feasiblArea" );
		var societyId = document.getElementById( "societyId" );
		dwr.util.removeAllOptions( areaId );
		dwr.util.removeAllOptions( societyId );

		dwr.util.addOptions( id, [
			{
				key : "0", value : "All Cities"
			}
		], 'key', 'value' );
		dwr.util.addOptions( areaId, [
			{
				key : "0", value : "All Area"
			}
		], 'key', 'value' );
		dwr.util.addOptions( societyId, [
			{
				key : "0", value : "All Localities"
			}
		], 'key', 'value' );

		return false;
	}
	$( '.loadingPopup' ).removeClass( 'hidden' );
	crmDwr.getAllCitiesForRFS( stateId, function( list ){
		addCityForRFS( id, list );
	} );
	function addCityForRFS( id, list ){
		var select = document.getElementById( id );
		if ( select != null ) {
			if ( list != null ) {
				dwr.util.removeAllOptions( id );

				/*
				 * dwr.util.addOptions( id, [ "All Cities" ] );
				 */
				dwr.util.addOptions( id, [
					{
						key : "0", value : "All Cities"
					}
				], 'key', 'value' );

				dwr.util.addOptions( id, list, "cityId", "cityName" );
			}
			else {
				alert( "No city registered in system for selected state." );
				removeList( id );
				dwr.util.addOptions( id, [
					{
						key : "0", value : "All Cities"
					}
				], 'key', 'value' );
			}
		}
		$( '.loadingPopup' ).addClass( 'hidden' );
	}
}

function populateAllAreaForRFS( id, areaId ){
	$( '.loadingPopup' ).removeClass( 'hidden' );
	// alert(areaId);
	if ( areaId == null || areaId == '' || areaId == 'Please Select' || areaId == 0 ) {
		dwr.util.removeAllOptions( id );

		var societyId = document.getElementById( "societyId" );
		// dwr.util.removeAllOptions(areaId);
		dwr.util.removeAllOptions( societyId );

		dwr.util.addOptions( id, [
			{
				key : "0", value : "All Area"
			}
		], 'key', 'value' );

		dwr.util.addOptions( societyId, [
			{
				key : "0", value : "All Localities"
			}
		], 'key', 'value' );

		return false;

	}
	crmDwr.getAllAreaForRFS( areaId, function( list ){
		addAreaForRFS( id, list );
	} );
	function addAreaForRFS( id, list ){
		var select = document.getElementById( id );
		if ( select != null ) {
			if ( list != null ) {
				dwr.util.removeAllOptions( id );
				// dwr.util.addOptions(id, [ "All Area" ]);
				dwr.util.addOptions( id, [
					{
						key : "0", value : "All Area"
					}
				], 'key', 'value' );

				dwr.util.addOptions( id, list, "areaId", "area" );
			}
			else {
				alert( "No Area registered in system for selected city." );
				removeList( id );
				dwr.util.addOptions( id, [
					{
						key : "0", value : "All Area"
					}
				], 'key', 'value' );
			}
		}
		$( '.loadingPopup' ).addClass( 'hidden' );
	}
}

function populateAlllocalityForRFS( id, localityId ){
	$( '.loadingPopup' ).removeClass( 'hidden' );
	// alert(localityId);
	if ( localityId == null || localityId == '' || localityId == 'Please Select' || localityId == 0 ) {
		dwr.util.removeAllOptions( id );

		dwr.util.addOptions( id, [
			{
				key : "0", value : "All Localities"
			}
		], 'key', 'value' );
		return false;

	}
	crmDwr.populateAlllocalityForRFS( localityId, function( list ){
		addlocalityForRFS( id, list );
	} );
	function addlocalityForRFS( id, list ){
		var select = document.getElementById( id );
		if ( select != null ) {
			if ( list != null ) {
				dwr.util.removeAllOptions( id );

				dwr.util.addOptions( id, [
					{
						key : "0", value : "All Localities"
					}
				], 'key', 'value' );
				dwr.util.addOptions( id, list, "societyId", "localityName" );
			}
			else {
				alert( "No Locality/Sector registered in system for selected area." );
				removeList( id );
				dwr.util.addOptions( id, [
					{
						key : "0", value : "All Localities"
					}
				], 'key', 'value' );
			}
		}
		$( '.loadingPopup' ).addClass( 'hidden' );
	}
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

function resetRFSForm(){
	var stateId = document.getElementById( "feasibleState" );
	var id = document.getElementById( "feasibleCity" );
	var areaId = document.getElementById( "feasiblArea" );
	var societyId = document.getElementById( "societyId" );
	dwr.util.removeAllOptions( id );
	dwr.util.removeAllOptions( areaId );
	dwr.util.removeAllOptions( societyId );

	document.getElementById( "feasibleState" ).value = "0";
	dwr.util.addOptions( id, [
		{
			key : "0", value : "All Cities"
		}
	], 'key', 'value' );

	dwr.util.addOptions( areaId, [
		{
			key : "0", value : "All Area"
		}
	], 'key', 'value' );
	dwr.util.addOptions( societyId, [
		{
			key : "0", value : "All Localities"
		}
	], 'key', 'value' );

}