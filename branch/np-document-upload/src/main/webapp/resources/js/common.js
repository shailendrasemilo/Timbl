$( document ).ready( function(){
	$( '#addMore' ).click( function(){
		var options = document.getElementById( 'docTypes' ).innerHTML;
		var select = '<select name="docTypes">' + options + '</select>';
		var file = '<input type="file" name="document">';
		var row = '<tr><td>' + select + '</td><td>' + file + '</td></tr>';
		$( '#uploadTable tbody' ).append( row );
	} );
} );
