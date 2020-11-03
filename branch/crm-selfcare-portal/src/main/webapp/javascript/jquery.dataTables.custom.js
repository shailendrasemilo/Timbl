$( document ).ready(
		function(){
			$( '#billDetails, #openTicketDetails, #paymentHistory, #ticketHistory' ).DataTable(
					{
						"ordering" : false, "searching" : false, "iDisplayLength" : 4, "bLengthChange" : false, "bScrollCollapse" : false,
						"bInfo" : false, "pagingType" : "simple", "language" : {
							"emptyTable" : "No record found"
						},
					} );
		} );