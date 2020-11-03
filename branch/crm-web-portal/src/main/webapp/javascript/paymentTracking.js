var ddmmmyyyy = 'ddmmmyyyy';
var ddmmyyyy = 'ddmmyyyy';
$(document)
		.ready(
				function() {
					$('#fromDate_s').removeClass('gray_text');
					$('#toDate_s').removeClass('gray_text');
					$('#er_paymentDate').removeClass('gray_text');
					$('#er_chequeDate').removeClass('gray_text');
					$.validator.addMethod("DaysCheck", function(value, element,
							param) {
						return this.optional(element)
								|| validityChequeDate(value, parseInt(param));
					});
					$.validator.addMethod("CheckFuturePaymentDate", function(
							value, element, param) {
						return this.optional(element)
								|| pastDateValidityChecker(value,
										parseInt(param));
					});
					$('#IDnotRealizedPopUpForm')
							.validate(
									{
										rules : {
											bouncingDate : {
												required : true,
											},
											bouncingReason : {
												dropDown : true
											}
										},
										messages : {
											bouncingDate : {
												required : " <font class='errorTextbox'>Please provide 'Bouncing Date'</font> "
											},
											bouncingReason : {
												dropDown : "<font class='errorTextbox'>Please provide 'Bouncing Reason'</font>"
											}
										}
									});

					$('#IDpaymentTracking')
							.validate(
									{
										rules : {
											fromDate : {
												required : true,
											},
											toDate : {
												required : true
											}
										},
										messages : {
											fromDate : {
												required : " <font class='errorTextbox'>Please provide 'From Date'</font> "
											},
											toDate : {
												required : "<font class='errorTextbox'>Please provide 'To Date'</font>"
											}
										}

									});

					$("#IDRefundtTracking")
							.validate(
									{
										rules : {
											// User Details
											customerId : {
												number : true,
												minlength : 7,
												maxlength : 15,
											},
											crfId : {
												crfId : true
											},

										},
										messages : {
											customerId : {
												number : "<font class='errorCreateUser'>Please provide numeric value.</font>",
												minlength : "<font class='errorCreateUser'>Please provide valid customer Id between [7-15].</font>",
												maxlength : "<font class='errorCreateUser'>Please provide valid customer Id between [7-15].</font>"
											},
											crfId : {
												crfId : "<font class='errorCreateUser'>CAF number should be numeric and 8 character long.</font>"
											}
										}

									});

					$('#PRsubmit_PaymentTracking')
							.click(
									function() {
										if ($("#IDpaymentTracking").valid()) {

											// alert('yoyo');
											if (checkDate(
													$(
															'input:text[name="fromDate"]')
															.val(),
													$(
															'input:text[name="toDate"]')
															.val(), '-',
													'ddmmmyyyy')) {
												$( '.loadingPopup' ).removeClass( 'hidden' );
												document.forms[1].action = "paymentTracking.do?method=paymentTracking";
												document.forms[1].submit();
											} else {
												alert("'To Date' should be greater than 'From Date'");
											}
										}
									});

					$('#PRsubmitNotRealized')
							.click(
									function() {
										if ($("#IDnotRealizedPopUpForm")
												.valid()) {

											if (checkDate(
													$(
															'input:text[name="bouncingDate"]')
															.val(),
													getTodayDate('ddmmmyyyy'),
													'-', 'ddmmmyyyy')) {
												var answer2 = confirm("Are you sure you want to change cheque status to Not-Realized?");
												if (answer2) {
													parent.document
															.getElementById("PTbouncingReason").value = document
															.getElementById("bouncingReason").value;
													parent.document
															.getElementById("PTbouncingDate").value = document
															.getElementById("IDBouncingDate").value;
													parent.document
															.getElementById('PTreliazationVariable').value = false;
													parent.document
															.getElementById('PTpaymentId').value = document
															.getElementById("PTpaymentId").value;
													parent.document.forms[1].action = "paymentPosting.do?method=changeRealizationStatus";
													parent.document.forms[1]
															.submit();
													// window.close();
												}
											} else {
												alert("Bouncing date cannot be greater than today's date");
											}
										}
									});
					$('#submit_searchSuspense')
							.click(
									function() {
										if ($("#searchSuspense")) {
											$( '.loadingPopup' ).removeClass( 'hidden' );
											document.forms[1].action = "paymentTracking.do?method=viewSuspenseRejectedRecord";
											document.forms[1].submit();
										}
									});

					$('#submit_paymentGatewayConfig')
							.click(
									function() {
										if ($('#paymentGatewaysConfigId')) {
											document.forms[1].action = "paymentTracking.do?method=paymentGatewaysConfigure";
											document.forms[1].submit();
										}
									});

					$("#editRejected")
							.validate(
									{
										rules : {
											'crmCmsPaymentPojo.ie2' : {
												required : true
											},
											'paymentDate' : {
												required : true,
												DaysCheck : '90',
												CheckFuturePaymentDate : '0'
											},
											'crmCmsPaymentPojo.instrumentAmount' : {
												required : true,
												number : true,
												paidAmount : true,
												notZero : true
											},
											'crmCmsPaymentPojo.instrumentNo' : {
												required : true,
												digits : true,
												minlength : 6,
												maxlength : 6
											},
											'chequeDate' : {
												required : true,
												DaysCheck : '90'
											},
											'crmCmsPaymentPojo.draweeBank' : {
												dropDown : true
											}
										},
										messages : {
											'crmCmsPaymentPojo.ie2' : {
												required : "<font class='errorTextbox'> Please provide 'Customer ID'</font>"
											},
											'paymentDate' : {
												required : "<font class='errorTextbox'> Please provide'Payment Date'</font>",
												DaysCheck : "<font class='errorTextbox'>Please provide 'Payment Date'<br /> from last 90 days only</font>",
												CheckFuturePaymentDate : "<font class='errorTextbox'>'Payment Date' can't be future date.</font>"
											},
											'crmCmsPaymentPojo.instrumentAmount' : {
												required : "<font class='errorTextbox'> Please provide 'Paid Amount'</font>",
												number : "<font class='errorTextbox'> Please provide only number, decimal allowed</font>",
												paidAmount : "<font class='errorTextbox'> Please provide amount not more than 8 digits(and 1-2 digits after decimal)</font>",
												notZero : "<font class='errorTextbox'> Please provide amount more than 0.</font>"
											},
											'crmCmsPaymentPojo.instrumentNo' : {
												required : "<font class='errorTextbox'> Please provide valid 'Cheque Number.'</font>",
												digits : "<font class='errorTextbox'> Please provide only number</font>",
												minlength : "<font class='errorTextbox'>Please provide minimum 6 digits.</font>",
												maxlength : "<font class='errorTextbox'>Please provide maximum 6 digits.</font>"
											},
											'chequeDate' : {
												required : "<font class='errorTextbox'>Please provide 'Cheque Date'</font>",
												DaysCheck : "<font class='errorTextbox'>Please provide 'Cheque Date'<br /> from last 90 days only</font>"
											},
											'crmCmsPaymentPojo.draweeBank' : {
												dropDown : "<font class='errorTextbox'> Please select 'Bank Name'</font>"
											}
										}

									});
					$('#submit_editRejectedRecord')
							.click(
									function() {
										if ($("#editRejected").valid()) {
											window.opener.$( '.loadingPopup' ).removeClass( 'hidden' );
											window.opener.document
													.getElementById("hidden_cmsId").value = document
													.getElementById("er_cmsId").value;
											window.opener.document
													.getElementById("hidden_ie2").value = document
													.getElementById("er_customerId").value;
											window.opener.document
													.getElementById("hidden_instrumentAmount").value = document
													.getElementById("er_instrumentAmount").value;
											window.opener.document
													.getElementById("hidden_paymentDate").value = document
													.getElementById("er_paymentDate").value;
											window.opener.document
													.getElementById("hidden_instrumentNo").value = document
													.getElementById("er_instrumentNo").value;
											window.opener.document
													.getElementById("hidden_chequeDate").value = document
													.getElementById("er_chequeDate").value;
											window.opener.document
													.getElementById("hidden_draweeBank").value = document
													.getElementById("er_draweeBank").value;
											window.opener.document.forms[1].action = "paymentPosting.do?method=rectifyRejectedRecord";
											window.opener.document.forms[1]
													.submit();
											self.close();
										}
									});

					$('#submit_Refund')
							.click(
									function() {

										if ($("#IDRefundtTracking").valid()
												) {
											$( '.loadingPopup' ).removeClass( 'hidden' );
											document.forms[1].action = "refund.do?method=refundTracking";
											document.forms[1].submit();

										}
									});

					$('#RsubmitNotRealized')
							.click(
									function() {
										if ($("#IDRnotRealizedPopUpForm")) {

											var answer2 = confirm("Are you sure you want to change cheque status to Not-Realized?");
											if (answer2) {
												parent.document
														.getElementById("RbouncingReason").value = document
														.getElementById("bouncingReason").value;
												parent.document
														.getElementById('newStatus').value = document
														.getElementById("IDnewStatus").value;
												parent.document
														.getElementById('refundId').value = document
														.getElementById("refundId").value;
												parent.document.forms[1].action = "refund.do?method=changeRefundStatus";
												parent.document.forms[1]
														.submit();
												// window.close();
											}

										}
									});

					$('#submit_searchPOD')
							.click(
									function() {
										if ($("#searchPOD")) {
											document.forms[1].action = "refund.do?method=trackSuspenseRejectedRecord";
											document.forms[1].submit();
										}
									});

				});

function changePaymentStatus(changeToStatus, elementToChange, paymentId,
		installationStatus) {
	document.getElementById("PTpaymentId").value = paymentId;
	if (elementToChange == 'realization') {
		if (changeToStatus == 'RL') {
			var answer = confirm("Are you sure you want to change cheque status to Realized?");
			if (answer) {
				$( '.loadingPopup' ).removeClass( 'hidden' );
				document.getElementById('PTreliazationVariable').value = true;
				document.forms[1].action = "paymentPosting.do?method=changeRealizationStatus";
				document.forms[1].submit();
			}
		}
		/*
		 * else if ( changeToStatus == 'NRL' ) { var url =
		 * "paymentPosting.do?method=changeRealizationStatusPage&paymentId=" +
		 * paymentId+"&installationStatus="+installationStatus; window.open(
		 * url, 'newWindow',
		 * 'width=800,height=352,scrollbars=yes,titlebar=no,fullscreen=no,resizeable=no' ); }
		 */
	} else if (elementToChange == 'paymentStatus') {

		var answer = confirm("Are you sure you want to change 'Payment Status'?");
		if (answer) {
			$( '.loadingPopup' ).removeClass( 'hidden' );
			document.getElementById('PTchangeDynamicVariable').value = changeToStatus;
			document.forms[1].action = "paymentPosting.do?method=changePaymentStatus";
			document.forms[1].submit();
		}
	} else if (elementToChange == 'caseStatus') {
		if (changeToStatus == "") {
			alert("Please select valid case status.");
			document.getElementById('caseStatusID_' + paymentId).value = document
					.getElementById('hdnCS_' + paymentId).value;
			return;
		}
		var answer = confirm("Are you sure you want to change 'Case Status'?");
		if (answer) {
			$( '.loadingPopup' ).removeClass( 'hidden' );
			document.getElementById('PTchangeDynamicVariable').value = changeToStatus;
			document.forms[1].action = "paymentPosting.do?method=changeCaseStatus";
			document.forms[1].submit();
		} else {
			document.getElementById('caseStatusID_' + paymentId).value = document
					.getElementById('hdnCS_' + paymentId).value;
		}
	} else if (changeToStatus == 'AC') {
		var answer = confirm("Do you want to change status to Alteration/Correction?");
		if (answer) {
			$( '.loadingPopup' ).removeClass( 'hidden' );
			document.getElementById('PTchangeDynamicVariable').value = changeToStatus;
			document.forms[1].action = "paymentPosting.do?method=changeCaseStatus";
			document.forms[1].submit();
		}
	} else if (changeToStatus == 'CID') {
		var answer = confirm("Do you want to change status to Cheque Irregularly Drawn?");
		if (answer) {
			$( '.loadingPopup' ).removeClass( 'hidden' );
			document.getElementById('PTchangeDynamicVariable').value = changeToStatus;
			document.forms[1].action = "paymentPosting.do?method=changeCaseStatus";
			document.forms[1].submit();
		}
	} else if (changeToStatus == 'DSR') {
		var answer = confirm("Do you want to change status to Drawer sign Required?");
		if (answer) {
			$( '.loadingPopup' ).removeClass( 'hidden' );
			document.getElementById('PTchangeDynamicVariable').value = changeToStatus;
			document.forms[1].action = "paymentPosting.do?method=changeCaseStatus";
			document.forms[1].submit();
		}
	} else if (changeToStatus == 'DSD') {
		var answer = confirm("Do you want to change status to Drawer Signature Differ?");
		if (answer) {
			$( '.loadingPopup' ).removeClass( 'hidden' );
			document.getElementById('PTchangeDynamicVariable').value = changeToStatus;
			document.forms[1].action = "paymentPosting.do?method=changeCaseStatus";
			document.forms[1].submit();
		}
	} else if (changeToStatus == 'INC') {
		var answer = confirm("Do you want to change status to Image not Clear?");
		if (answer) {
			$( '.loadingPopup' ).removeClass( 'hidden' );
			document.getElementById('PTchangeDynamicVariable').value = changeToStatus;
			document.forms[1].action = "paymentPosting.do?method=changeCaseStatus";
			document.forms[1].submit();
		}
	} else if (changeToStatus == 'IF') {
		var answer = confirm("Do you want to change status to Insufficient Fund?");
		if (answer) {
			$( '.loadingPopup' ).removeClass( 'hidden' );
			document.getElementById('PTchangeDynamicVariable').value = changeToStatus;
			document.forms[1].action = "paymentPosting.do?method=changeCaseStatus";
			document.forms[1].submit();
		}
	} else if (changeToStatus == 'PDDF') {
		var answer = confirm("Do you want to change status to Post Dated - Due to date format?");
		if (answer) {
			$( '.loadingPopup' ).removeClass( 'hidden' );
			document.getElementById('PTchangeDynamicVariable').value = changeToStatus;
			document.forms[1].action = "paymentPosting.do?method=changeCaseStatus";
			document.forms[1].submit();
		}
	} else if (changeToStatus == 'SC') {
		var answer = confirm("Do you want to change status to Smudge Cheque?");
		if (answer) {
			$( '.loadingPopup' ).removeClass( 'hidden' );
			document.getElementById('PTchangeDynamicVariable').value = changeToStatus;
			document.forms[1].action = "paymentPosting.do?method=changeCaseStatus";
			document.forms[1].submit();
		}
	} else if (changeToStatus == 'STC') {
		var answer = confirm("Do you want to change status to Stale Cheque?");
		if (answer) {
			$( '.loadingPopup' ).removeClass( 'hidden' );
			document.getElementById('PTchangeDynamicVariable').value = changeToStatus;
			document.forms[1].action = "paymentPosting.do?method=changeCaseStatus";
			document.forms[1].submit();
		}
	} else if (changeToStatus == 'SP') {
		var answer = confirm("Do you want to change status to Stop Payment?");
		if (answer) {
			$( '.loadingPopup' ).removeClass( 'hidden' );
			document.getElementById('PTchangeDynamicVariable').value = changeToStatus;
			document.forms[1].action = "paymentPosting.do?method=changeCaseStatus";
			document.forms[1].submit();
		}
	} else if (changeToStatus == 'DA') {
		var answer = confirm("Do you want to change status to Dormant Account?");
		if (answer) {
			$( '.loadingPopup' ).removeClass( 'hidden' );
			document.getElementById('PTchangeDynamicVariable').value = changeToStatus;
			document.forms[1].action = "paymentPosting.do?method=changeCaseStatus";
			document.forms[1].submit();
		}
	} else if (changeToStatus == 'OTRS') {
		var answer = confirm("Do you want to change status to Others?");
		if (answer) {
			$( '.loadingPopup' ).removeClass( 'hidden' );
			document.getElementById('PTchangeDynamicVariable').value = changeToStatus;
			document.forms[1].action = "paymentPosting.do?method=changeCaseStatus";
			document.forms[1].submit();
		}
	}

	document.getElementById('PTchangeDynamicVariable').value = '';
}

function getTodayDate(format) {
	var today = new Date();
	var date = today.getDate();
	var month = today.getMonth() + 1;
	var monthChar = convertMonthToChar(month);
	var year = today.getFullYear();

	if (format == 'ddmmmyyyy') {

		return date + '-' + monthChar + '-' + year;
	} else if (format == 'ddmmyyyy') {

		return '' + date + '-' + month + '-' + year;
	}
}
function checkDate(olddate1, olddate2, delimeter, format) {
	// date in format dd-mmm-yyyy, olddate1 should be less than olddate2
	date1 = parseDate(olddate1, delimeter, format);
	date2 = parseDate(olddate2, delimeter, format);
	if (date2 >= date1) {
		return true;
	} else
		return false;
}

function parseDate(date, delimeter, format) {
	// returns date in numbers ready to be matched
	if (delimeter === '-') {
		d = date.split("-");
	}
	if (format === 'ddmmmyyyy') {
		var c = convertMonthToNumber(d[1]);

		return new Date(d[2], c - 1, d[0]);// returns the reverse date
	} else if (format === 'ddmmyyyy') {
		return new Date(d[2], d[1], d[0]);
	}
}

function convertMonthToNumber(month) {
	switch (month) {// changes Jan to 01,Dec to 12 String to Numbers(for months)
	case 'Jan':
		c = 01;
		break;
	case 'Feb':
		c = 02;
		break;
	case 'Mar':
		c = 03;
		break;
	case 'Apr':
		c = 04;
		break;
	case 'May':
		var c = 05;
		break;
	case 'Jun':
		c = 06;
		break;
	case 'Jul':
		c = 07;
		break;
	case 'Aug':
		c = 08;
		break;
	case 'Sep':
		c = 09;
		break;
	case 'Oct':
		c = 10;
		break;
	case 'Nov':
		c = 11;
		break;
	case 'Dec':
		c = 12;
		break;
	default:
		alert('not supported date');
	}
	return c;
}
function convertMonthToChar(monthDigit) {
	switch (monthDigit) {// changes Jan to 01,Dec to 12 String to Numbers(for
	// months)
	case 1:
		c = 'Jan';
		break;
	case 2:
		c = 'Feb';
		break;
	case 3:
		c = 'Mar';
		break;
	case 4:
		c = 'Apr';
		break;
	case 5:
		c = 'May';
		break;
	case 6:
		c = 'Jun';
		break;
	case 7:
		c = 'Jul';
		break;
	case 8:
		c = 'Aug';
		break;
	case 9:
		c = 'Sep';
		break;
	case 10:
		c = 'Oct';
		break;
	case 11:
		c = 'Nov';
		break;
	case 12:
		c = 'Dec';
		break;
	default:
		alert('not supported date');
	}
	return c;

}
function realizeRecieveCloseAll(variableToChange) {

	document.getElementById("PTchangeDynamicVariable").value = variableToChange;
	var listSize = document.getElementById("PTsearchPaymentListSize").value;
	if (listSize > 0) {
		var listOfPojo = new Array();
		var i = 0;
		for ( var rCount = 1; rCount <= listSize; rCount++) {
			var editable = document.getElementById("PTeditable_" + rCount);
			var paymentId = document.getElementById("PTpaymentId_" + rCount);
			if (null != editable && editable.checked) {
				listOfPojo[i] = paymentId.value;
				i++;
			}
		}
		if (null != listOfPojo && listOfPojo.length > 0) {
			var msg = "Please confirm if you want to realize selected Payment(s)?";
			if (variableToChange == 'R') {
				msg = "Please confirm if you want to modify the payment status to 'Received' for selected Payment(s)?";
			}
			var answer = confirm(msg);
			if (answer) {
				document.getElementById("PThiddenPaymentIdList").value = listOfPojo;
				document.forms[1].action = "paymentPosting.do?method=realizeRecieveAll";
				document.forms[1].submit();
			}
		} else {
			alert("Please select atleast one payment.");
		}
	}
	document.getElementById("PTchangeDynamicVariable").value = '';
}

var recievedSelectedList = new Array();
var notRecievedSelectedList = new Array();
var i = 0;
var j = 0;

// whether to realize or recieve all,checks and calls
function PTcheckData(row_number) {
	var listSize = document.getElementById('PTsearchPaymentListSize');
	if (document.getElementById("PTeditable_" + row_number).checked == true) {

		if (document.getElementById("PTpaymentStatus_" + row_number).value === 'R') {
			recievedSelectedList[i] = row_number;
			i++;
			recievedSelectedList = arrayRemoveBlankElements(recievedSelectedList);
		} else if (document.getElementById("PTpaymentStatus_" + row_number).value === 'NR') {
			notRecievedSelectedList[j] = row_number;
			j++;
			notRecievedSelectedList = arrayRemoveBlankElements(notRecievedSelectedList);
		}
	} else if (document.getElementById("PTeditable_" + row_number).checked == false) {
		if (document.getElementById("PTpaymentStatus_" + row_number).value === 'R') {
			for ( var y = 0; y < recievedSelectedList.length; y++) {

				if (recievedSelectedList[y] === row_number) {
					recievedSelectedList.splice(y, 1);

				}
			}
			recievedSelectedList = arrayRemoveBlankElements(recievedSelectedList);
		} else if (document.getElementById("PTpaymentStatus_" + row_number).value === 'NR') {
			for ( var z = 0; z < notRecievedSelectedList.length; z++) {

				if (notRecievedSelectedList[z] === row_number) {
					notRecievedSelectedList.splice(z, 1);

				}
			}
			notRecievedSelectedList = arrayRemoveBlankElements(notRecievedSelectedList);
		}
	}
	if ((notRecievedSelectedList.length > 0)
			&& (recievedSelectedList.length > 0)) {
		$('#PTrealizeButton').hide();
		$('#PTrecieveAllButton').hide();
		var msg = "You have selected the records, with payment status as 'Recieved' and need to modify realization status only,'\n'"
				+ "this is leading to ambiguity in records and no action can be prformed on these records,'\n' "
				+ "please select consistent records either to modify payment status or to modify realization status.";
		alert(msg);
	} else if ((notRecievedSelectedList.length === 0)
			&& (recievedSelectedList.length > 0)) {
		$('#PTrealizeButton').show();
		$('#PTrecieveAllButton').hide();
	} else if ((recievedSelectedList.length === 0)
			&& (notRecievedSelectedList.length > 0)) {
		$('#PTrecieveAllButton').show();
		$('#PTrealizeButton').hide();
	} else if ((notRecievedSelectedList.length === 0)
			&& (recievedSelectedList.length === 0)) {
		$('#PTrealizeButton').hide();
		$('#PTrecieveAllButton').hide();
	}
}

function arrayRemoveBlankElements(inArray) {
	var newArray = new Array();
	if (inArray.length > 0) {
		for ( var e = 0; e < inArray.length; e++) {
			if (inArray[e]) {
				// alert('empty at:'+e);
				newArray.push(inArray[e]);
			}

		}
		// alert(newArray);
	}
	return newArray;
}

function getFileDetails() {
	var toDate = document.getElementById("toDate_s").value;
	var fromDate = document.getElementById("fromDate_s").value;
	crmDwr.getFileDetails(toDate, fromDate, "DP", function(list) {
		addFileDetails("cmsDepositFileId", list);
	});
	crmDwr.getFileDetails(toDate, fromDate, "CL", function(list) {
		addFileDetails("cmsClearanceFileId", list);
	});
	function addFileDetails(id, list) {
		if (list != null) {
			dwr.util.removeAllOptions(id);
			dwr.util.addOptions(id, [ "Please select" ]);
			dwr.util.addOptions(id, list, "cmsFileId", "cmsFileName");
		}
	}
}

function editRejectedRecords(custId, cmsId) {
	// alert("hello");
	document.getElementById("customerId").value = custId;
	document.getElementById("cmsId").value = cmsId;
	var toDate = document.getElementById("toDate_s").value;
	var fromDate = document.getElementById("fromDate_s").value;
	var url = "paymentTracking.do?method=editRejectedRecordPage&ie2=" + custId
			+ "&cmsId=" + cmsId + "&toDate=" + toDate + "&fromDate" + fromDate;
	window.open(url, 'newWindow',
			'width=680,height=480,scrollbars=yes,resizable=no,toolbar=no');
}
function suspenseRejectedRecords(custId, cmsId, oldCustomerId) {
	if (custId == oldCustomerId) {
		return false;
	}
	var answer = confirm("Are you sure you want to change customerId:"
			+ oldCustomerId + " to " + custId);
	if (answer) {
		$( '.loadingPopup' ).removeClass( 'hidden' );
		document.getElementById("hidden_ie2").value = custId;
		document.getElementById("hidden_cmsId").value = cmsId;
		document.forms[1].action = "paymentPosting.do?method=rectifySuspenseRecord";
		document.forms[1].submit();
	}
}
var alreadyEdited = false;
function customeridShow(id) {
	if (alreadyEdited) {
		alert("Another record is already in edited state, Please close the same to edit this.");
		return false;
	}
	$('#customeridShow' + id).removeClass('hide1');
	$('#' + id).addClass('hide1');
	alreadyEdited = true;
}
function customerIdHide(id) {
	$('#customeridShow' + id).addClass('hide1');
	$('#' + id).removeClass('hide1');
	alreadyEdited = false;
}

function viewPayment(paymentId) {
	document.getElementById("PTpaymentId").value = paymentId;
	//alert("hi payment id" + paymentId);
	url = "paymentTracking.do?method=viewPayment&paymentId=" + paymentId;
	window.open(url,null,"height=600,width=700,scrollbars=yes,toolbar=no,status=yes,menubar=no,location=no");
	document.forms[1].submit();
}

function changeRefundStatus(inStatus, inRecordID, inCustomerID) {

	document.getElementById("refundId").value = inRecordID;
	document.getElementById("newStatus").value = inStatus;
	document.getElementById("customerId").value = inCustomerID;
	var answer = confirm("Please confirm if you want to change cheque status?");
	if (answer) {
		document.forms[1].action = "refund.do?method=changeRefundStatus";
		document.forms[1].submit();
	}

	function suspenseRejectedRefundRecords(custId, cmsId, oldCustomerId) {
		if (custId == oldCustomerId) {
			return false;
		}
		var answer = confirm("Are you sure you want to change customerId:"+ oldCustomerId + " to " + custId);
		if (answer) {
			document.getElementById("hidden_ie2").value = custId;
			document.getElementById("hidden_cmsId").value = cmsId;
			document.forms[1].action = "refund.do?method=rectifySuspenseRecord";
			document.forms[1].submit();
		}
	}
}

function getRefundDetail(refundID, customerID) {

	$("#refundId").val(refundID);
	$("#customerId").val(refundID);
	var answer = confirm("Please confirm if you want to modify refund details ?");
	if (answer) {
		document.forms[1].action = "refund.do?method=modifyRefundPage";
		document.forms[1].submit();
	}
}
function suspenseRejectedPODRecords(newCustomerId, podId, oldCustomerId) {
	alert("hello");
}
