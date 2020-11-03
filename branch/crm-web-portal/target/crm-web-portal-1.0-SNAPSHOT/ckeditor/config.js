/*
Copyright (c) 2003-2012, CKSource - Frederico Knabben. All rights reserved.
For licensing, see LICENSE.html or http://ckeditor.com/license
*/

/*CKEDITOR.editorConfig = function( config )
{
	config.enterMode = CKEDITOR.ENTER_BR;
	config.shiftEnterMode = CKEDITOR.ENTER_P;
	config.resize_enabled = false;
	// Define changes to default configuration here. For example:
	// config.language = 'fr';
	// config.uiColor = '#AADC6E';
	
};*/
CKEDITOR.on('dialogDefinition', function(ev) {
		// Take the dialog name and its definition from the event data
		var dialogName = ev.data.name;
		var dialogDefinition = ev.data.definition;
		if (dialogName == 'image') {
			// Remove upload tab
			dialogDefinition.removeContents('Upload');
			dialogDefinition.removeContents( 'Link' );
	        dialogDefinition.removeContents( 'advanced' );
	        	               
		}
	});


CKEDITOR.editorConfig = function( config )
{
	config.toolbar = 'Full';
 	config.enterMode = CKEDITOR.ENTER_BR;
	config.shiftEnterMode = CKEDITOR.ENTER_P;
	config.resize_enabled = false;
	config.toolbar_Full =
	[
		{ name: 'document',		items : [ 'Source','-','NewPage','DocProps','Preview','Print'] },
		{ name: 'editing',		items : [ 'Find','Replace','-','SelectAll','-','SpellChecker', 'Scayt' ] },
		{ name: 'links',		items : [ 'Link','Unlink','Anchor' ] },
		{ name: 'insert',		items : [ 'Image','Table','HorizontalRule','Smiley','SpecialChar','PageBreak' ] },
		'/',
		{ name: 'basicstyles',	items : [ 'Bold','Italic','Underline','Strike','Subscript','Superscript','-','RemoveFormat' ] },
		{ name: 'paragraph',	items : [ 'NumberedList','BulletedList','-','Outdent','Indent','-','Blockquote','CreateDiv','-','JustifyLeft','JustifyCenter','JustifyRight','JustifyBlock','-','BidiLtr','BidiRtl' ] },
		'/',
		{ name: 'styles',		items : [ 'Styles','Format','Font','FontSize' ] },
		{ name: 'colors',		items : [ 'TextColor','BGColor' ] }		
	];
};

CKEDITOR.replace( 'emailTemplateBody',
		{
			toolbar : 'Full'
		});
