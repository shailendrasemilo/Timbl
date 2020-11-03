$(document).ready(function() {
	//all validate methods added	
	$.validator.addMethod('greaterThan', function(value, element, param) {
		return this.optional(element) || value >= parseInt($(param).val(), 10);
	}, 'User expiry value shuold be greater than password expiry');

	$.validator.addMethod('lessThan', function(value, element, param) {
		return this.optional(element) || value <= parseInt($(param).val(), 10);
	}, 'Password expiry should be less than user expiry');

	$.validator.addMethod("firstName", function(value, element) {
		return this.optional(element) || validateUserFirstName(value.trim());
	}, "Alphabets(1-30), no special character/numbers");

	$.validator.addMethod("lastName", function(value, element) {
		return this.optional(element) || validateUserLastName(value.trim());
	}, "Alphabets(3-30), No special character or Numbers");

	$.validator.addMethod("emailId", function(value, element) {
		return this.optional(element) || validateEmail(value);
	});

					$.validator.addMethod( "mobileNo", function( value, element ){
						return this.optional( element ) || validateMobile( value );
					} );

					$.validator.addMethod( "oldPassword", function( value, element ){
						return this.optional( element ) || validatePassword( value );
					} );

					$.validator.addMethod( "newPassword", function( value, element ){
						return this.optional( element ) || validatePassword( value );
					} );

					$.validator.addMethod( "confirmNewPassword", function( value, element ){
						return this.optional( element ) || validatePassword( value );
					} );

					$.validator.addMethod( "notEqualTo", function( value, element, param ){
						return this.optional( element ) || value != $( param ).val();
					} );

					jQuery.validator.addMethod( "equalTo", function( value, element, param ){
						return this.optional( element ) || value == $( param ).val();
					}, "Password and New Password should be same" );

					// ALL VALIDATIONS for Forms
					// form #login validate rules
					$( "#login" ).validate( {
						rules : {
							userId : {
								required : true
							}, password : {
								required : true
							}
						}, messages : {
							userId : {
								required : "<div class='userId_error'> Please provide 'User ID'</div>"
							}, password : {
								required : "<div class='userId_error'>Please provide 'Password'</div>"
							}
						}
					} );
					// form #forgetPassword validate rules
					$( "#forgetPassword" ).validate(
							{
								rules : {
									userId : {
										required : true
									}, emailId : {
										required : true, emailId : true
									}, answer : {
										required : true
									}
								},
								messages : {
									userId : {
										required : "<div class='forgotPassword_error'> Please provide 'User ID'</div>"
									},
									emailId : {
										required : "<div class='forgotPassword_error'> Please provide 'Email ID'</div>",
										emailId : "<div class='forgotPassword_error'> Please provide valid 'Email ID'</div>"
									}, answer : {
										required : "<div class='forgotPasswordCaptcha_error'> Please provide 'Verification Text'</div>"
									}
								}
							} );

					// form #createUser validate rules
					$( "#createUser" ).validate(
							{
								rules : {
									emailId : {
										required : true, emailId : true
									}, firstName : {
										required : true, firstName : true
									}, lastName : {
										required : true, lastName : true, minlength : 3
									}, mobileNo : {
										required : true, mobileNo : true
									},
									// empType : {
									// dropDown:true
									// },
									// empCode :{ required : true},
									passwordExpiry : {
										required : true, digits : true, lessThan : "#userAccountExpiry"
									}, userAccountExpiry : {
										required : true, digits : true, greaterThan : "#passwordExpiry"
									}, functionalBinArray : {
										dropDown : true
									}
								},
								messages : {
									emailId : {
										required : "<font class='errorCreateUser'>Please provide 'Email ID'</font>",
										emailId : "<font class='errorCreateUser'>Please provide valid 'Email ID'</font>"
									},
									firstName : {
										required : "<font class='errorCreateUser'>Please provide 'First Name'</font>",
										firstName : "<font class='errorCreateUser'>Numbers & special characters are not allowed</font>"
									},
									lastName : {
										required : "<font class='errorCreateUser'>Please provide 'Last Name'</font>",
										lastName : "<font class='errorCreateUser'>Numbers & special characters are not allowed</font>",
										minlength : "<font class='errorCreateUser'>Please provide more than 3 characters</font>"
									},
									mobileNo : {
										required : "<font class='errorCreateUser'>Please provide 'Mobile Number'</font>",
										mobileNo : "<font class='errorCreateUser'>'Mobile Number' " + getMobileMesg() + "</font>"
									},
									// empType : {
									// dropDown : "<font class='errorCreateUserSelect'>Please select 'Employee Type'</font>"
									// },
									// empCode : {
									// required : "<font class='errorCreateUser'>Please provide 'Employee Code'</font>"
									// },
									passwordExpiry : {
										required : "<font class='errorCreateUser'>Please provide 'Password Expiry'</font>",
										digits : "<font class='errorCreateUser'>'Password Expiry' can only have digits</font>",
										lessThan : "<font class='errorCreateUser'>This must be less than 'Account Expiry'</font>"
									},
									userAccountExpiry : {
										required : "<font class='errorCreateUser'>Please provide 'User Account Expiry'</font>",
										digits : "<font class='errorCreateUser'>'User Account Expiry' can only have digits</font>",
										greaterThan : "<font class='errorCreateUser'>This must be greater than 'Password Expiry'</font>"
									}, functionalBinArray : {
										dropDown : "<font  class='Lms_Reference'>Please select 'Functional Bin'</font>"
									}
								}
							} );
					// form #changePassword validate rules
					$( "#changePassword" )
							.validate(
									{
										rules : {
											oldPassword : {
												required : true
											},
											// newPassword:"required newPassword",

											newPassword : {
												required : true, newPassword : true, notEqualTo : "#oldPassword"
											}, confirmPassword : {
												required : true, equalTo : "#newPassword"
											}, answer : {
												required : true
											}
										},
										messages : {
											oldPassword : {
												required : "<div class='forgotPassword_error'>Please provide 'Current Password'</div>"
											},
											// newPassword:"required newPassword",
											newPassword : {
												required : "<div class='forgotPassword_error'>Please provide 'New Password'</div>",
												newPassword : "<div class='forgotPassword_error'>Please provide a valid 'New Password'</div>",
												notEqualTo : "<div class='forgotPassword_error'>New Password can not be equal to 'Current Password'</div></div>"
											},
											confirmPassword : {
												required : "<div class='forgotPassword_error'>Please confirm 'New Password'</div>",
												equalTo : "<div class='forgotPassword_error'>'New Password' and 'Confirm Password' do not match</div>"
											}, answer : {
												required : "<div class='forgotPasswordCaptcha_error'> Please provide 'Verification Text'</div>"
											}
										}
									} );

					// For my profile change password:

					$( "#changePasswordMyAccount" )
							.validate(
									{
										rules : {
											oldPassword : {
												required : true
											},
											// newPassword:"required newPassword",

											newPassword : {
												required : true, newPassword : true, notEqualTo : "#oldPassword"
											}, confirmPassword : {
												required : true, equalTo : "#newPassword"
											}, answer : {
												required : true
											}
										},
										messages : {
											oldPassword : {
												required : "<div class='forgotPassword_error'>Please provide 'Current Password'</div>"
											},
											// newPassword:"required newPassword",
											newPassword : {
												required : "<div class='forgotPassword_error'>Please provide 'New Password'</div>",
												newPassword : "<div class='forgotPassword_error'>Please provide a valid 'New Password'</div>",
												notEqualTo : "<div class='forgotPassword_error'>'New Password' can not be equal to 'Current Password'</div></div>"
											},
											confirmPassword : {
												required : "<div class='forgotPassword_error'>Please confirm 'New Password'</div>",
												equalTo : "<div class='forgotPassword_error'>'New Password' and 'Confirm Password' do not match</div>"
											}, answer : {
												required : "<div class='forgotPasswordCaptcha_error'> Please provide 'Verification Text'</div>"
											}
										}
									} );

					// all button clicks
					// submit_login click for login.jsp
					$( '#submit_login' ).click( function(){
						if ( $( "#login" ).valid() ) {
							document.forms[ 0 ].action = "login.do?method=loginAuthentication";
							document.forms[ 0 ].submit();
						}
					} );

					// submit_forgetPassword click for forgetPassword.jsp
					$( '#submit_forgetPassword' ).click( function(){
						if ( $( "#forgetPassword" ).valid() ) {
							document.forms[ 0 ].action = "forgetPassword.do?method=forgetPassword";
							document.forms[ 0 ].submit();
						}
					} );

					$( "#submit_createUser" ).click( function(){
						if ( $( "#createUser" ).valid() ) {
							var answer = confirm( "Please confirm if you want to register user!" );
							if ( answer ) {
								document.forms[ 1 ].action = "register.do?method=registerNewUser";
								document.forms[ 1 ].submit();
							}
						}
					} );

					// #submit_updateUser for createUser.jsp
					$( "#submit_updateUser" ).click( function(){
						if ( $( "#createUser" ).valid() ) {
							var answer = confirm( "Please confirm if you want to update user!" );
							if ( answer ) {
								document.forms[ 1 ].action = "userManagement.do?method=modifyUser";
								document.forms[ 1 ].submit();
							}

						}
						else {
							$( '.success_message' ).addClass( 'hide1' );
						}
					} );

					$( '#addRole' ).click( function(){
						addRole();
					} );

					$( '#idAddParameter' ).click( function(){
						addParameters();
					} );

					$( '#idAssignArea' ).click( function(){
						addAreaMapping();
					} );

					$( '#submitAssignRole_assignrole' ).click( function(){
						var answer = confirm( "Are you sure to assign Role/Role group to user?" );
						if ( answer ) {
							document.forms[ 1 ].action = "roleManagement.do?method=assingUserRole";
							document.forms[ 1 ].submit();
						}
						else {
							return false;
						}

					} );

					$( '#submitAssignParameter_assignparameter' ).click( function(){
						var answer = confirm( "Are you sure to assign Parameter/Parameter group to user?" );
						// alert(answer);
						if ( answer ) {
							document.forms[ 1 ].action = "parameterManagement.do?method=assingUserParameter";
							document.forms[ 1 ].submit();
						}
						else {
							return false;
						}
					} );

					$( '#assignParameter_searchUser' ).click( function(){
						document.forms[ 1 ].action = "parameterManagement.do?method=assingUserParameter";
						document.forms[ 1 ].submit();
					} );

					// activateUser_createUser click for createUser.jsp
					$( "#activateUser_createUser" ).click( function(){
						var answer = confirm( "Please confirm if you want to activate the user!" );
						if ( answer ) {
							document.forms[ 1 ].action = "userManagement.do?method=activateUser";
							document.forms[ 1 ].submit();
						}
						else {
							return false;
						}
					} );
					$fill = false;
					// search_userSearch click for userSearch.jsp

					$( '#search_userSearch' )
							.click(
									function(){

										if ( jQuery.trim( $( '#userId' ).val() ) != ""
												|| jQuery.trim( $( '#firstName' ).val() ) != ""
												|| jQuery.trim( $( '#lastName' ).val() ) != ""
												|| jQuery.trim( $( '#emailId_searchUser' ).val() ) != ""
												/* ||$('#empType_searchUser').val()!=""||jQuery.trim($('#empCode').val())!="" */|| $(
														'#mobileNo_searchUser' ).val() != "" || $( '#functionalBinID' ).val() != ""
												|| $( '#idsearchUserStatus' ).val() != "" ) {
											$fill = true;
											if ( $( '#mobileNo_searchUser' ).val() != "" ) {
												mobileNoValidation();
											}
											else {
												$( '#mobileNo_searchUser' ).next( 'span' ).hide();
											}
										}
										else {
											$( '#unfill_searchUser' ).removeClass( 'hide1' );
											$( '#idDisplayTable' ).addClass( 'hide1' );
											$( '#mobileNo_searchUser' ).next( 'span' ).hide();
										}
										if ( $fill == true ) {
											$( '.loadingPopup' ).removeClass( 'hidden' );
											document.forms[ 1 ].action = "userManagement.do?method=searchUser";
											document.forms[ 1 ].submit();
										}

									} );

					// for search escalation work flow user
					$( "#search_by_user" ).validate( {
						rules : {
							userId : {
								required : true
							},
						}, messages : {
							userId : {
								required : "<div class='errorCreateUser'> Please provide 'User ID'</div>"
							}
						}
					} );
					$( "#searchUserByIdMainButton" ).click( function(){
						if ( $( "#search_by_user" ).valid() ) {
							$( '.loadingPopup' ).removeClass( 'hidden' );
							document.forms[ 1 ].action = "userManagement.do?method=searchUserById";
							document.forms[ 1 ].submit();
						}

					} );
					// popup change for change Password
					// This page is without Customer Search
					// submit_changePassword click for changePassword.jsp
					$( '#submit_changePassword' ).click( function(){
						if ( $( "#changePassword" ).valid() ) {
							document.forms[ 0 ].action = "changePassword.do?method=changePassword";
							document.forms[ 0 ].submit();
						}
					} );

					// submit_changePasswordMyAccount click for myAccount.jsp
					$( '#submit_changePasswordMyAccount' ).click( function(){
						if ( $( "#changePasswordMyAccount" ).valid() ) {
							var answer = confirm( "Please confirm if you want to change password!" );
							if ( answer ) {
								document.forms[ 1 ].action = "changePassword.do?method=changePassword";
								document.forms[ 1 ].submit();
							}
						}
					} );
					// button changes
					// empType_createUser for createUser.jsp
					// $('#empType_createUser').change(
					// function() {
					// if ($(this).val() == "N") {
					// $('#empCode_createUser').removeAttr('disabled');
					// $('#partnerName_createUser').attr('disabled', 'disabled');
					// $('#emp_code').removeClass('hide1');
					// $('#part_name').addClass('hide1');
					// } else if ($(this).val() == "P") {
					//			    
					// $('#partnerName_createUser').removeAttr('disabled');
					// $('#empCode_createUser').attr('disabled','disabled');
					// $('#emp_code').addClass('hide1');
					// $('#part_name').removeClass('hide1');
					// populatePartners(false);
					//				
					// }
					// else{
					// $('#emp_code').addClass('hide1');
					// $('#part_name').addClass('hide1');
					// }
					// });

					// for searchUser.jsp independent code
					// TOBECHANGED
					$( '.searchView' ).hide();
					// $('#ecode_searchUser, #pcode_searchUser ').hide();
					// $('#empType_searchUser').change(function() {
					// if ($(this).val() == "N") {
					// $('#pcode_searchUser').hide();
					// $('#ecode_searchUser').show();
					// } else if ($(this).val() == "P") {
					// $('#ecode_searchUser').hide();
					// $('#pcode_searchUser').show();
					// populatePartners(true);
					// }
					// });
					// for search escalation work flow user
					$( "#idSearchEWUser" ).validate( {
						rules : {
							userId : {
								required : true
							},
						}, messages : {
							userId : {
								required : "<div class='errorCreateUser'> Please provide 'User ID'</div>"
							}
						}
					} );
					$( "#submit_searchTemplate" ).click( function(){
						if ( $( "#idSearchEWUser" ).valid() ) {
							document.forms[ 1 ].action = "searchEWUser.do?method=searchUser";
							document.forms[ 1 ].submit();
						}
					} );
					$( "#submit_searchUserPopUp" ).click( function(){
						document.forms[ 0 ].action = "searchEWUser.do?method=searchUserPopUp";
						document.forms[ 0 ].submit();
					} );
					$( "#save_ewUserMapping" ).click( function(){
						document.forms[ 1 ].action = "searchEWUser.do?method=createMappedUser";
						document.forms[ 1 ].submit();
					} );

					/* for dropdown css and title */
					$( '#partnerName_createUser' ).hover( function(){
						$( "#partnerName_createUser" ).attr( 'title', $( '#partnerName_createUser option:selected' ).text() );
						console.log();
						$( this ).css( 'width', 205 );
						if ( jQuery.browser.msie && jQuery.browser.version.substring( 0, 1 ) >= 7 ) {
							$( this ).css( 'width', 213 );
							$( "#partnerName_createUser" ).attr( 'title', $( '#partnerName_createUser option:selected' ).text() );
						}
					} );
				} );
// javascript functions
// captcha check for changePassword.jsp and forgetPassword.jsp
function reloadCaptcha( id ){
	var obj = document.getElementById( id );
	var src = obj.src;
	var pos = src.indexOf( '?' );
	if ( pos >= 0 ) {
		src = src.substr( 0, pos );
	}
	var date = new Date();
	obj.src = src + '?v=' + date.getTime();
	return false;
}
// editUser_searchUser function for searchUser.jsp
function editUser_searchUser( inRecordID ){
	var answer = confirm( "Please confirm if you want to edit user!" );
	if ( answer ) {
		$( '.loadingPopup' ).removeClass( 'hidden' );
		document.getElementById( "recordID_searchUser" ).value = inRecordID;
		document.forms[ 1 ].action = "userManagement.do?method=modifyUserPage";
		document.forms[ 1 ].submit();
	}
}
// viewUser_searchUser function for searchUser.jsp
function viewUser_searchUser( inRecordID ){
	document.getElementById( "recordID_searchUser" ).value = inRecordID;
	var url = "userManagement.do?method=viewUser&userId=" + inRecordID;
	window.open( url, '', 'width=800,height=600,scrollbars=yes,titlebar=no,fullscreen=no,resizeable=no' );
}
function assignRoles_searchUser( inRecordID ){
	var answer = confirm( "Please confirm if you want to assign roles to the user!" );
	if ( answer ) {
		$( '.loadingPopup' ).removeClass( 'hidden' );
		document.getElementById( "recordID_searchUser" ).value = inRecordID;
		document.forms[ 1 ].action = "userManagement.do?method=assignRolesPage";
		document.forms[ 1 ].submit();
	}
}
function assignParameter_searchUser( inRecordID ){
	var answer = confirm( "Please confirm if you want to assign parameter to the user!" );
	if ( answer ) {
		$( '.loadingPopup' ).removeClass( 'hidden' );
		document.getElementById( "recordID_searchUser" ).value = inRecordID;
		document.forms[ 1 ].action = "userManagement.do?method=assignParameterPage";
		document.forms[ 1 ].submit();
	}
}
function assignParameter(){
	$( '.loadingPopup' ).removeClass( 'hidden' );
	document.forms[ 1 ].action = "parameterManagement.do?method=updateParameterAssignPage";
	document.forms[ 1 ].submit();
}
function assignRoles(){
	$( '.loadingPopup' ).removeClass( 'hidden' );
	document.forms[ 1 ].action = "roleManagement.do?method=updateRoleAssignPage";
	document.forms[ 1 ].submit();
}
// changeStatus function for createUser.jsp
// TOBECHANGED changeStatus_createUser
function changeStatusUser( inRecordID, inStatus ){
	var answer = confirm( "Please confirm if you want to change user status!" );
	if ( answer ) {
		document.getElementById( "record_status" ).value = inRecordID;
		document.getElementById( "userStatus" ).value = inStatus;
		document.forms[ 1 ].action = "userManagement.do?method=changeUserStatus";
		document.forms[ 1 ].submit();
	}
}

function resetPassword( inRecordID ){
	var answer = confirm( "Please confirm if you want to reset the password!" );
	if ( answer ) {
		document.getElementById( "record_status" ).value = inRecordID;
		document.forms[ 1 ].action = "userManagement.do?method=resetPassword";
		document.forms[ 1 ].submit();
	}
}

function updateRoleAssignPage( inRecordID ){
	document.getElementById( "record_status" ).value = inRecordID;
	document.forms[ 1 ].action = "userManagement.do?method=updateRoleAssignPage";
	document.forms[ 1 ].submit();
}
function addRole(){
	document.forms[ 1 ].action = "roleManagement.do?method=addRolesRow";
	document.forms[ 1 ].submit();
}
function addParameters(){
	document.forms[ 1 ].action = "parameterManagement.do?method=addParameterRow";
	document.forms[ 1 ].submit();
}
function deleteRole( inIndex ){
	document.getElementById( "rowIndex_Role" ).value = inIndex;
	document.forms[ 1 ].action = "roleManagement.do?method=deleteRolesRow";
	document.forms[ 1 ].submit();
}
function deleteParameter( inIndex ){
	document.getElementById( "rowIndex_Role" ).value = inIndex;
	document.forms[ 1 ].action = "parameterManagement.do?method=deleteParameterRow";
	document.forms[ 1 ].submit();
}
function searchUserById(){
	document.forms[ 1 ].action = "userManagement.do?method=searchUserById";
	document.forms[ 1 ].submit();
}
function populatePartners( toSelect ){
	crmDwr.getPartners( function( list ){
		addPartners( "partnerName_createUser", list );
	} );
	function addPartners( id, list ){
		var select = document.getElementById( id );
		if ( select != null ) {
			if ( list != null ) {
				dwr.util.removeAllOptions( id );
				if ( toSelect ) {
					dwr.util.addOptions( id, [
						"Please select"
					] );
				}
				dwr.util.addOptions( id, list, "partnerId", "partnerName" );
			}
			else {
				alert( "No partner registered in system." );
			}
		}
	}
}
function addAssignRoles(){
	document.forms[ 1 ].action = "roleManagement.do?method=addRolesRow";
	document.forms[ 1 ].submit();
}
// This page is without Customer Search
function activateAccount(){
	document.forms[ 0 ].action = "accountVerification.do?method=activateUser";
	document.forms[ 0 ].submit();
}
function resendActivationLink( userId ){
	var answer = confirm( "Please confirm if you want to resend activation link for:" + userId );
	if ( answer ) {
		crmDwr.resendActivationLink( userId, function( responseStr ){
			if ( responseStr ) {
				alert( "Activation link has been sent successfully for:" + userId );
			}
			else {
				alert( "Unable to send activation link, Please contact admin." );
			}
		} );
	}
	else {
		return false;
	}
}
function userMapping( inUserID, mappingType ){
	document.getElementById( "userID_searchUser" ).value = inUserID;
	document.getElementById( "mappingType_ID" ).value = mappingType;
	document.forms[ 1 ].action = "searchEWUser.do?method=searchEWUserMapping";
	document.forms[ 1 ].submit();
}
function enableUserMapping( rowNo ){
	var editChkBox = document.getElementsByName( "crmUserMappingPojosList[" + rowNo + "].editable" );
	var status = document.getElementsByName( "crmUserMappingPojosList[" + rowNo + "].status" );
	if ( editChkBox[ 0 ].checked ) {
		status[ 0 ].onclick = "";
		status[ 1 ].onclick = "";
	}
	else {
		status[ 0 ].onclick = returnFalse;
		status[ 1 ].onclick = returnFalse;
	}
}
function returnFalse(){
	return false;
}
function selectUser(){
	var url = "searchEWUser.do?method=selectMappedUser";
	window.open( url, '', 'width=800,height=500,scrollbars=yes' );
}
function addSelectedUser(){
	var listSize = document.getElementById( "userPojoSize" ).value;
	if ( listSize > 0 ) {
		var listOfPojo = new Array();
		var i = 0;
		for ( var rCount = 1; rCount <= listSize; rCount++ ) {
			var editable = document.getElementById( "edit_" + rCount );
			var userId = document.getElementById( "user_" + rCount );
			if ( null != editable && editable.checked ) {
				listOfPojo[ i ] = userId.value;
				i++;
			}
		}
		if ( null != listOfPojo && listOfPojo.length > 0 ) {
			var answer = confirm( "Please confirm if you want to add following user(s)?" + listOfPojo );
			if ( answer ) {
				window.opener.document.getElementById( "hiddenUserList" ).value = listOfPojo;
				window.opener.document.forms[ 1 ].action = "searchEWUser.do?method=searchEWUserMapping";
				window.opener.document.forms[ 1 ].submit();
				window.close();
			}

		}
		else {
			alert( "Please select at least one User" );
		}
	}
}

function deleteUserMappingRow( inIndex ){
	document.getElementById( "rowIndex_addRow" ).value = inIndex;
	document.forms[ 1 ].action = "searchEWUser.do?method=deleteUserMappingRow";
	document.forms[ 1 ].submit();
}

function mobileNoValidation(){
	mobile = $( '#mobileNo_searchUser' );
	if ( !validateMobile( mobile.val() ) ) {
		$( '#mobileNo_searchUser' ).next( 'span' ).show().html( " 'Mobile Number' " + getMobileMesg() );
		$fill = false;
		$( '#unfill_searchUser' ).addClass( 'hide1' );

	}
	else {
		$( '#mobileNo_searchUser' ).next( 'span' ).hide();

	}
}
function assignArea_searchUser( inRecordID ){
	var answer = confirm( "Please confirm if you want to assign area to the user!" );
	if ( answer ) {
		$( '.loadingPopup' ).removeClass( 'hidden' );
		document.getElementById( "recordID_searchUser" ).value = inRecordID;
		document.forms[ 1 ].action = "roleManagement.do?method=assignAreaPage";
		document.forms[ 1 ].submit();
	}
}

function addAreaMappingPopUP(){
	// alert("1");

	var url = "roleManagement.do?method=addAreaPopUp";
	window.open( url, '', 'width=800,height=600,scrollbars=yes,titlebar=no,fullscreen=no,resizeable=no' );

	// document.forms[1 ].action = "roleManagement.do?method=addAreaPopUp";
	// document.forms[ 1].submit();
}

function addAreaMapping(){
	// alert("1");
	document.forms[ 1 ].action = "roleManagement.do?method=addAssignAreaRow";
	document.forms[ 1 ].submit();
}

function deleteMapping( inIndex ){
	document.getElementById( "rowIndex" ).value = inIndex;
	document.forms[ 1 ].action = "roleManagement.do?method=deleteAreaRow";
	document.forms[ 1 ].submit();
}

function submitAssignArea( size ){
	var flag = true;
	var msg;
	for ( var i = 0; i < size; i++ ) {
		var stateId = 'stateId' + i;
		var cityId = 'city' + i;
		var areaId = 'area' + i;
		if ( document.getElementById( stateId ).value == "" || document.getElementById( stateId ).value == "0"
				|| document.getElementById( stateId ).value == null ) {
			msg = "Please Select state ";
			flag = false;
			break;
		}
		if ( flag && document.getElementById( cityId ).value == "" || document.getElementById( cityId ).value == "0"
				|| document.getElementById( cityId ).value == null || document.getElementById( cityId ).value == "Please Select" ) {

			msg = "Please Select city";
			flag = false;
			break;
		}
		if ( flag && document.getElementById( areaId ).value == "" || document.getElementById( areaId ).value == "0"
				|| document.getElementById( areaId ).value == null || document.getElementById( areaId ).value == "Please Select" ) {
			msg = "Please Select area";
			flag = false;
			break;
		}
	}
	if ( !flag ) {
		alert( msg );
		return false;
	}
	if ( flag ) {

		var answer = confirm( "Please confirm you want to assign area!" );
		if ( answer ) {
			document.forms[ 1 ].action = "roleManagement.do?method=submitAssignArea";
			document.forms[ 1 ].submit();
		}
	}
}