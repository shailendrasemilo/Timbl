$( document ).ready(
		function(){

			$( '.fancybox' ).fancybox();

			$( "#fancybox-signIn" ).click(
					function(){
						$.fancybox.open( {
							href : 'login.do?method=loginPage', type : 'iframe', padding : 5, width : 500, height : 325, openEffect : "none",
							closeEffect : "none", scrolling : "auto", aspectRatio : true, fitToView : true, iframe : {
								scrolling : 'no', preload : true
							}
						} );
					} );

			$( '.closeSignIn' ).click( function( e ){
				e.preventDefault();
				//parent.location.href = "#";
			} );

			$( '.closeChooseAccount' ).click( function( e ){
				e.preventDefault();
				$( '.popup-loading' ).removeClass( 'hide' );
				location.href = "login.do?method=loginPage";
			} );

			$( '.closeCPwdPopup' ).click( function( e ){
				e.preventDefault();
				window.top.location.href = 'user.do?method=myAccountPage';
				parent.$( '.popup-loading' ).removeClass( 'hide' );
				parent.jQuery.fancybox.close();
			} );

			$( "#dialog_PaymentCenters" ).click( function(){
				$.fancybox.open( {
					'href' : 'quickPay.do?method=paymentCenterPage', 'type' : 'iframe', 'padding' : 5, 'width' : 600, 'height' : 200
				} );
			} );
			$( "#fancybox-changePassword" ).click(
					function(){
						$.fancybox.open( {
							href : 'user.do?method=changePasswordPage', type : 'iframe', 'padding' : 5, 'width' : 460, 'height' : 200,
							openEffect : "none", closeEffect : "none", scrolling : "none"
						} );
					} );

			$( "#postPaidFAQ" ).click( function(){
				$.fancybox.open( {
					'href' : 'user.do?method=displayFaqPage', 'type' : 'iframe', 'padding' : 5, 'width' : 515, 'height' : 200, 'top' : 58.5
				
				} );

			} );
			
			$( "#prePaidFAQ" ).click( function(){
				$.fancybox.open( {
					'href' : 'user.do?method=displayFaqPage', 'type' : 'iframe', 'padding' : 5, 'width' : 515, 'height' : 200, 'top' : 58.5
				
				} );prePaidFAQ

			} );

			$( "#dialog_PaymentCenter" ).click( function(){
				$.fancybox.open( {
					'href' : 'quickPay.do?method=paymentCenterPage', 'type' : 'iframe', 'padding' : 5, 'width' : 600, 'height' : 340

				} );
			} );

			$( "#fancybox-forgotPassword" ).click(
					function(){
						$.fancybox.open( {

							href : 'login.do?method=forgotPasswordPage', type : 'iframe', 'padding' : 5, 'width' : 460, 'height' : 200,
							openEffect : "none", closeEffect : "none", scrolling : "none"
						} );

					} );

			$( "#btnchangeplan" ).click( function(){
				$.fancybox.open( {
					'href' : 'migration.do?method=planMigrationPage', 'type' : 'iframe', 'padding' : 5, 'width' : 960, 'height' : 500,
				} );

			} );

			$( "#btntopup" ).click( function(){
				$.fancybox.open( {
					'href' : 'migration.do?method=boosterTopUpPage', 'type' : 'iframe', 'padding' : 5, 'width' : 600, 'height' : 700,
				} );

			} );

			$( "#fancybox-manual-f" ).click( function(){
				$.fancybox.open( {
					'href' : 'topup.html', 'type' : 'iframe', 'padding' : 5, 'width' : 560, 'height' : 700,
				} );

			} );

			$( "#BillAddress .icon-edit" ).click( function(){
				$.fancybox.open( {
					'href' : 'user.do?method=updateAddressPage', 'type' : 'iframe', 'padding' : 5, 'width' : 600, 'height' : 700,
				} );

			} );

			$( ".btnlogticket" ).click( function(){
				$.fancybox.open( {
					'href' : 'user.do?method=logTicketPage', 'type' : 'iframe', 'padding' : 5, 'width' : 525, 'height' : 550,
				} );

			} );

			$( "#nonOpenTickets" ).click( function(){
				$.fancybox.open( {
					'href' : 'user.do?method=nonOpenTickets', 'type' : 'iframe', 'padding' : 5, 'width' : 500, 'height' : 550,
				} );

			} );

		} );

function fun_onselectchangetext( varid, inBasePrice ){
	var retval = $( "#drpselectdul" ).val();
	if ( retval == "" || retval == null || retval == undefined || retval == "Please Select" ) {// alert("Please select DUL");
		$( "#onselectshowprice_div" ).css( "display", "none" );
		$( "#onselectchangetext" ).css( "display", "none" );
		$( '#onselectshowprice' ).html( '' );
		// $('#onselectchangetext').html('');
		return false;

	}
	else if ( parseInt( retval ) > 6 ) {
		$( "#onselectchangetext" ).css( "display", "block" );
		// $('#onselectchangetext').html("<storng>Do you require more than 8GB
		// Booster Usage Top-Up?</strong><br>please upgrade your plan by adding
		// Add-On DUL by</strong> <a
		// onclick='showhidedivsontabclick(&apos;tabunlimited_popup&apos;);'>CLICK
		// HERE</a></strong> or call our Call Center for a customised advice.");
		$( "#onselectshowprice_div" ).css( "display", "block" );
		var topupprice = ( parseInt( retval ) / 2 ) * inBasePrice;
		// alert(topupprice);
		$( '#onselectshowprice' ).html( topupprice );
	}
	else {
		// $('#onselectchangetext').html('');
		$( "#onselectshowprice_div" ).css( "display", "block" );
		var topupprice = ( parseInt( retval ) / 2 ) * inBasePrice;
		// alert(topupprice);
		$( '#onselectshowprice' ).html( topupprice );

	}

};

