$( document ).ready(
		function(){
			$( '#viewPaymentHistory, #invoiceDetailsId' ).DataTable(
					{
						"ordering" : false, "searching" : false, "iDisplayLength" : 7, "bLengthChange" : false, "bScrollCollapse" : false,
						"bInfo" : true, "pagingType" : "simple", "language" : {
							"emptyTable" : "<b>No record found</b>"
						},
					} );
			$( '#reportDataTable').dataTable( {
				"ordering" : false, "searching" : false, "iDisplayLength" : 20, "bLengthChange" : false, "language" : {
					"emptyTable" : "No record found"
				},
				// for horizontal scroll
				"sScrollX" : "200px",
				// export config
				dom : 'T<"clear">lfrtip', tableTools : {
					"sSwfPath" : "swf/copy_csv_xls_pdf.swf", "aButtons" : [
							/* "csv", "pdf", "copy", */
							{
								sExtends : "xls", sFileName : 'report.xls'
							},
					]
				},
			} );
		} );