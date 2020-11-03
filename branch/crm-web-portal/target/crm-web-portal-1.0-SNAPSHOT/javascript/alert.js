$( document ).ready(
		function(){
			$.validator.addMethod( "templateName", function( value, element ){
				return this.optional( element ) || validateAlphanumericUnderScore( value );
			}, "no special character allowed" );
			$.validator.addMethod( 'emailServer', function( value ){
				return ( value != '0' );
			}, "please select email server" );
			$.validator.addMethod( 'eventName', function( value ){
				return ( value != '0' );
			}, "please select event server" );
			$.validator.addMethod( 'smsGateway', function( value ){
				return ( value != '0' );
			}, "please select sms gateway" );
			// Multiple email id Validation for From, CC and BCC
			$.validator.addMethod( "emailFrom", function( value, element ){
				return this.optional( element ) || validateEmail( value );
			} );
			$.validator.addMethod( "emailCc", function( value, element ){
				if ( this.optional( element ) ) {
					return true;
				}
				var emails = value.split( ',' ), valid = true;
				for ( var i = 0, limit = emails.length; i < limit; i++ ) {
					value = emails[ i ];
					valid = valid && jQuery.validator.methods.email.call( this, value, element );
				}
				return valid;
			} );
			$.validator.addMethod( "emailBcc", function( value, element ){
				if ( this.optional( element ) ) {
					return true;
				}
				var emails = value.split( ',' ), valid = true;
				for ( var i = 0, limit = emails.length; i < limit; i++ ) {
					value = emails[ i ];
					valid = valid && jQuery.validator.methods.email.call( this, value, element );
				}
				return valid;
			} );
			// Validate signup form
			$( "#createEmail" ).validate(
					{
						rules : {
							projectType : {
								required : true
							}, templateName : {
								required : true, templateName : true
							}, emailFrom : {
								required : true, emailFrom : true
							}, emailSubject : {
								required : true
							}, emailCc : {
								emailCc : true
							}, emailBcc : {
								emailBcc : true
							}, emailServer : {
								emailServer : true
							}
						},
						messages : {

							projectType : {
								required : "<font class='errorRadio' style='top:40px;'>Please select 'Project Type'</font>"
							},
							templateName : {
								required : "<font class='errorTextbox'>Please provide 'Template Name'</font>",
								templateName : "<font class='errorTextbox'>Please provide valid 'Template Name'</font>"
							},
							emailFrom : {
								required : "<font class='email'>Please provide recipient's 'Email Address'</font>",
								emailFrom : "<font class='email' style='top:-13px;'>Please provide valid & single 'Email Address'</font>"
							}, emailCc : {
								emailCc : "<font class='email' style='top:0px;'>Please provide valid 'Email Address'</font>"
							}, emailBcc : {
								emailBcc : "<font class='email' style='top:0px;'>Please provide valid 'Email Address'</font>"
							}, emailSubject : {
								required : "<font class='email' style='top:0px;'>Please provide email 'Subject' for mail</font>"
							}, emailServer : {
								emailServer : "<font class='errorTextbox'>Please select 'Email Server'</font>"
							}
						}
					} );

			$( '#createEmailTemplate' ).click( function(){
				if ( $( "#createEmail" ).valid() ) {
					var answer = confirm( "Please confirm if you want to create email template!" );
					if ( answer ) {
						document.forms[ 1 ].action = "emailAlert.do?method=createEmailTemplate";
						document.forms[ 1 ].submit();
					}
				}
			} );
			$( '#updateEmailTemplate' ).click( function(){
				if ( $( "#createEmail" ).valid() ) {
					var answer = confirm( "Please confirm if you want to update email template!" );
					if ( answer ) {
						document.forms[ 1 ].action = "emailAlert.do?method=createEmailTemplate";
						document.forms[ 1 ].submit();
					}
				}
			} );
			$( '#importEmail' ).click( function(){
				document.forms[ 1 ].action = "emailAlert.do?method=importEmailTemplate";
				document.forms[ 1 ].submit();

			} );
			$( '#cc,#bcc' ).hide();
			$( '#add_cc' ).click( function(){
				$( '#cc' ).toggle();
				$( '#display_cc' ).toggle();
			} );
			$( '#add_bcc' ).click( function(){
				$( '#bcc' ).toggle();
				$( '#display_bcc' ).toggle();
			} );

			$( "#createEmail :input" ).bind( "keypress", function( evt ){
				var keyCode = evt.which || evt.keyCode;
				if ( keyCode == 13 ) {
					if ( $( "#createEmail" ).valid() ) {
						document.forms[ 1 ].action = "emailAlert.do?method=createEmailTemplate";
						document.forms[ 1 ].submit();
					}
				}
				return true;
			} );

			// Validate signup form
			$( "#sms" ).validate(
					{
						rules : {
							smsType : {
								required : true
							}, projectType : {
								required : true
							}, templateName : {
								required : true, templateName : true
							}, smsTemplateBody : {
								required : true
							}, smsGateway : {
								smsGateway : true
							}
						},
						messages : {
							smsType : {
								required : "<div class='projectType_error'>Please select 'SMS Type'</div>"
							},
							projectType : {
								required : "<div class='projectType_error'>Please select 'Project Type'</div>"
							},
							templateName : {
								required : "<div class='sms_error'>Please provide 'Template Name'</div>",
								templateName : "<div class='sms_error'>Please do not insert special character</div>"
							}, smsTemplateBody : {
								required : "<div class='smsTemplateBody_error'>Please type your 'Message'</div>"
							}, smsGateway : {
								smsGateway : "<div class='sms_error'>Please select 'SMS Gateway'</div>"
							}
						}
					} );

			$( '#createSMSTemplate' ).click( function(){
				if ( $( "#sms" ).valid() ) {
					var answer = confirm( "Please confirm if you want to create sms template!" );
					if ( answer ) {
						document.forms[ 1 ].action = "smsAlert.do?method=createSMSTemplate";
						document.forms[ 1 ].submit();
					}
				}
			} );
			$( '#updateTemplate' ).click( function(){
				if ( $( "#sms" ).valid() ) {
					var answer = confirm( "Please confirm if you want to update sms template!" );
					if ( answer ) {
						document.forms[ 1 ].action = "smsAlert.do?method=modifySMSTemplate";
						document.forms[ 1 ].submit();
					}
				}
			} );
			$( "#sms :input" ).bind( "keypress", function( evt ){
				var keyCode = evt.which || evt.keyCode;
				if ( keyCode == 13 ) {
					if ( $( "#sms" ).valid() ) {
						var answer = confirm( "Please confirm if you want to update sms template!" );
						if ( answer ) {
							document.forms[ 1 ].action = "smsAlert.do?method=modifySMSTemplate";
							document.forms[ 1 ].submit();
						}
					}
				}
				return true;
			} );

			$( '.search' ).hide();
			$( "#search_template" ).validate( {
				rules : {
					projectType : "required", templateType : "required"
				}, messages : {
					projectType : {
						required : "<font class='errorRadio' style='top:40px;'>Please select 'Project Type'</font>"
					}, templateType : {
						required : "<font class='errorRadio' style='top:40px;'>Please select 'Template Type'</font>"
					}
				}
			} );

			$( ".hide" ).hide();
			$( 'input:radio[name=templateType]' ).change( function(){
				var test = $( this ).val();
				$( ".hide" ).hide();
				$( ".smsShow" ).hide();
				$( "#display_" + test ).show();
			} );

			$( '#idSmsCheck' ).click( function(){
				var isChecked = $( '#idSmsCheck' ).attr( 'checked' );
				if ( isChecked ) {
					$( '#idSms' ).attr( 'disabled', false );
				}
				else {
					$( '#idSms' ).attr( 'disabled', true );
				}
			} );
			$( '#idEmailCheck' ).click( function(){
				var isChecked = $( '#idEmailCheck' ).attr( 'checked' );
				if ( isChecked ) {
					$( '#idEmail' ).attr( 'disabled', false );
				}
				else {
					$( '#idEmail' ).attr( 'disabled', true );
				}
			} );
			$( '#addMapping' ).click( function(){
				addMapping();
			} );
			$( '#submit_EventMapping' ).click( function(){
				createEventTemplateMapping();
			} );
			$( "a#close-panel" ).click( function(){
				$( "#lightbox, #lightbox-panel" ).fadeOut( 300 );
			} );
			$( '#template_name' ).bind( 'keypress', function( evt ){
				var keyCode = evt.which || evt.keyCode;
				if ( keyCode == 13 ) {
					searchTemplate();
					return false;
				}
			} );
		} );

$( window ).load( function(){
	$( '.smsEnabled' ).each( function( index, element ){
		if ( $( element ).is( ":checked" ) ) {
			$( element ).parent().next().css( 'color', '#000' );
		}
		else {
			$( element ).parent().next().css( 'color', '#a4a3a3' );
		}
	} );
	$( '.emailEnabled' ).each( function( index, element ){
		if ( $( element ).is( ":checked" ) ) {
			$( element ).parent().next().css( 'color', '#000' );
		}
		else {
			$( element ).parent().next().css( 'color', '#a4a3a3' );
		}
	} );
} );

function viewTemplate( templateId, templateType ){
	crmDwr.getTemplate( templateId, templateType, function( map ){
		var templateName = map[ "templateName" ];
		var body = map[ "body" ];
		var status = map[ "status" ];
		var from = map[ "from" ];
		var subject = map[ "subject" ];
		var cc = map[ "cc" ];
		var bcc = map[ "bcc" ];
		var smsType = map[ "smsType" ];
		var dataDisplay = document.getElementById( "rowDisplayData" );
		if ( templateType == "sms" ) {
			dataDisplay.innerHTML = generateTableSmsTemplate( templateName, status, body, smsType );
		}
		else {
			dataDisplay.innerHTML = generateTableEmailTemplate( templateName, status, body, from, subject, cc, bcc );
		}
		$( "#lightbox, #lightbox-panel" ).fadeIn( 300 );
	} );
}
function searchTemplate(){
	if ( $( "#search_template" ).valid() ) {
		var tType = "email";
		var radios = document.getElementsByName( "templateType" );
		for ( var i = 0; i < radios.length; i++ ) {
			if ( radios[ i ].checked )
				tType = radios[ i ].value;
		}
		if ( tType == "email" ) {
			$( '.loadingPopup' ).removeClass( 'hidden' );
			document.forms[ 1 ].action = "emailAlert.do?method=searchAndViewEmailTemplate";
			document.forms[ 1 ].submit();
		}
		else if ( tType == "sms" ) {
			$( '.loadingPopup' ).removeClass( 'hidden' );
			document.forms[ 1 ].action = "smsAlert.do?method=viewSMSTemplate";
			document.forms[ 1 ].submit();
		}
	}

}

function generateTableSmsTemplate( templateName, templateStatus, templateBody, smsType ){
	if ( smsType == "P" ) {
		smsType = "Promotional";
	}
	else {
		smsType = "Transactional";
	}
	var tableData = "<p class='popUpRow1'><font class='popUpHead'>Template Name:</font> <font class='popUpText'>" + templateName + "</font></p> "
			+ "<p class='popUpRow2'><font class='popUpHead'>Template Body:</font> <font class='popUpText'>" + templateBody + "</font></p> "
			+ "<p class='popUpRow1'><font class='popUpHead'>Template Status:</font> <font class='popUpText'>" + templateStatus + "</font></p>"
			+ "<p class='popUpRow2'><font class='popUpHead'>Sms Type:</font> <font class='popUpText'>" + smsType + "</font></p>";
	return tableData;
}
function generateTableEmailTemplate( templateName, templateStatus, templateBody, from, subject, cc, bcc ){
	var tableData = "<p class='popUpRow1'><font class='popUpHead'>Template Name:</font> <font class='popUpText'>" + templateName + "</font></p> "
			+ "<p class='popUpRow2'><font class='popUpHead'>Template Subject:</font> <font class='popUpText'>" + subject + "</font></p> "
			+ "<p class='popUpRow1'><font class='popUpHead'>From:</font> <font class='popUpText'>" + from + "</font></p> "
			+ "<p class='popUpRow2'><font class='popUpHead'>Cc:</font> <font class='popUpText'>" + cc + "</font></p> "
			+ "<p class='popUpRow1'><font class='popUpHead'>Bcc:</font> <font class='popUpText'>" + bcc + "</font></p> "
			+ "<p class='popUpRow2'><font class='popUpHead'>Template Status:</font> <font class='popUpText'>" + templateStatus + "</font></p>"
			+ "<p class='popUpRow1'><font class='popUpHead'>Template Body:</font> <div style='padding:10px;'>" + templateBody + "</div></p> ";
	return tableData;
}

// message and character counter for sms template creation
function textCounter( field, charCounter, messageCounter, maxlimit ){
	var countfield = document.getElementById( charCounter );
	var messageCountf = document.getElementById( messageCounter );
	countfield.value = field.value.length;
	messageCountf.value = Math.ceil( countfield.value / maxlimit );
	return true;
}
// Dwr implementation
function populateList( value ){
	crmDwr.getProject( value, function( list ){
		addlist( "sel", list );
	} );
	function addlist( id, list ){
		var select = document.getElementById( id );
		if ( select != null && null != list ) {
			dwr.util.removeAllOptions( id );
			dwr.util.addOptions( id, list, "projectId", "projectName" );
			document.getElementById( "pName" ).style.display = "block";
			getParameter( list[ 0 ].projectId );
		}
		else {
			alert( "No projects registered in CRM." );
		}
	}
}

function changeStatus( templateId, type, method, status ){
	var answer = confirm( "Please confirm if you want to change status of template!" );
	if ( answer ) {
		document.getElementById( "idStatus" ).value = status;
		templateOperation( templateId, type, method );
	}
}
function templateOperation( templateId, type, method ){
	var vAction = "emailAlert.do";
	if ( type == "S" ) {
		vAction = "smsAlert.do";
	}
	document.getElementById( "idTemplateId" ).value = templateId;
	document.forms[ 1 ].action = vAction + "?method=" + method;
	document.forms[ 1 ].submit();
}
// create email template javascript

function importTemplate( evt, messageId, tType, fieldId ){
	// Retrieve the first (and only!) File from the FileList object
	var f = evt.target.files[ 0 ];
	if ( f ) {
		var tReader = new FileReader();
		if ( ( tType == "E" ) && !( f.type.match( 'html.*' ) || f.type.match( 'htm.*' ) ) ) {
			alert( f.name + " is not a valid html file." );
			f = null;
		}
		if ( ( tType == "S" ) && ( !( f.type.match( 'text.*' ) ) || ( f.type.match( 'html.*' ) || f.type.match( 'htm.*' ) ) ) ) {
			alert( f.name + " is not a valid text file." );
			f = null;
		}
		if ( f != null ) {
			var answer = confirm( "Do you want to overwrite the data by the file's data?" );
			if ( answer ) {
				tReader.readAsText( f );
				tReader.onload = function( e ){
					var contents = e.target.result;
					if ( tType == "E" ) {
						var ck = CKEDITOR.instances[ messageId ];
						ck.setData( contents );
						var fileName = f.name;
						fileName = fileName.substring( 0, fileName.lastIndexOf( "." ) );
						var templateName = document.getElementById( 'emailTemplateName' );
						if ( null != templateName ) {
							templateName = templateName.value;
							if ( null == templateName || "" == templateName ) {
								document.getElementById( 'emailTemplateName' ).value = fileName;
							}
						}
					}
					else {
						document.getElementById( messageId ).value = contents;
						document.getElementById( messageId ).innerHTML = contents;
						document.getElementById( messageId ).focus();
						var fileName = f.name;
						fileName = fileName.substring( 0, fileName.lastIndexOf( "." ) );
						var templateName = document.getElementById( 'template_name' );
						if ( null != templateName ) {
							templateName = templateName.value;
							if ( null == templateName || "" == templateName ) {
								document.getElementById( 'template_name' ).value = fileName;
							}
						}
					}
				};
			}
		}
	}
	else {
		alert( "Unable to upload file." );
	}
	document.getElementById( fieldId ).value = "";
}

// populate parameter list in dropdown
function getParameter( value ){
	crmDwr.getParameters( value, function( list ){
		addParameterList( "smsparameter", list );
		addParameterList( "emailParameter", list );
	} );
	function addParameterList( id, list ){
		var select = document.getElementById( id );
		if ( select != null ) {
			dwr.util.removeAllOptions( id );
			dwr.util.addOptions( id, list, "parameterName", "parameterName" );
		}
	}
}

function additem( id ){
	var listbox;
	var textarea;
	if ( id == "idSmsParam" ) {
		listbox = document.getElementById( 'smsparameter' );
		textarea = document.getElementById( 'message' );
		for ( var i = 0; i < listbox.options.length; i++ ) {
			if ( listbox.options[ i ].selected ) {
				var val = listbox.options[ i ].value;
				textarea.value += '$' + "{" + val + "}";
			}
		}
	}
	else if ( id == "idSubjectParam" ) {
		listbox = document.getElementById( 'emailParameter' );
		subject = document.getElementById( 'emailSubject' );
		for ( var i = 0; i < listbox.options.length; i++ ) {
			if ( listbox.options[ i ].selected ) {
				var val = listbox.options[ i ].value;
				subject.value += '$' + "{" + val + "}";
			}
		}
	}
	else {
		listbox = document.getElementById( 'emailParameter' );
		for ( var i = 0; i < listbox.options.length; i++ ) {
			if ( listbox.options[ i ].selected ) {
				var val = listbox.options[ i ].value;
				var ck = CKEDITOR.instances[ 'emailTemplateBody' ];
				var value = ck.getData();
				value += '$' + "{" + val + "}";
				ck.setData( value );
			}
		}
	}
}
function addMapping(){
	document.forms[ 1 ].action = "eventTemplateMapping.do?method=addTemplateMappingRow";
	document.forms[ 1 ].submit();
}
function deleteMappingRow( inIndex ){
	document.getElementById( "rowIndex_addRow" ).value = inIndex;
	document.forms[ 1 ].action = "eventTemplateMapping.do?method=deleteTemplateMappingRow";
	document.forms[ 1 ].submit();
}
function createEventTemplateMapping(){
	var answer = confirm( "Please be ensure that mappings are correct." );
	if ( answer ) {
		document.forms[ 1 ].action = "eventTemplateMapping.do?method=createEventTemplateMapping";
		document.forms[ 1 ].submit();
	}
	else {
		return false;
	}
}
function enableMapping( rCount ){
	var smsChkBox = document.getElementsByName( "evntTemplateList[" + rCount + "].smsEnabled" );
	var emailChkBox = document.getElementsByName( "evntTemplateList[" + rCount + "].emailEnabled" );
	// var editBox = document.getElementsByName("evntTemplateList[" + rCount
	// + "].editable");
	if ( smsChkBox[ 0 ].onclick == "" || smsChkBox[ 0 ].onclick == null ) {
		smsChkBox[ 0 ].onclick = returnFalse;
	}
	else {
		smsChkBox[ 0 ].onclick = "";
	}
	if ( emailChkBox[ 0 ].onclick == "" || emailChkBox[ 0 ].onclick == null ) {
		emailChkBox[ 0 ].onclick = returnFalse;
	}
	else {
		emailChkBox[ 0 ].onclick = "";
	}
}
function smsEmailToggle( checkbox, name ){
	setTimeout( function(){
		var select = document.getElementsByName( name )[ 0 ];
		if ( $( checkbox ).is( ":checked" ) ) {
			$( select ).parent().css( 'color', '#000' );
		}
		else {
			$( select ).parent().css( 'color', '#a4a3a3' );
		}
	}, 100 );
}
function returnFalse(){
	return false;
}
// .setAttribute('onclick','return false');

