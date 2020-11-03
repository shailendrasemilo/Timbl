$(document).ready(function() {

/**** ----  Payment reversal ----  *******/ 
$.validator.addMethod('crfId',function(value, element) {
				return this.optional(element)|| validateCRF(value);
});
	
  $("#idSearchPaymentReversal").validate({
                rules: {
						//User Details
                	customerId:{
                        	number:true,
                        	maxlength:15,
                        },
                        crfId: {
							crfId:true
                        },
                        toDate:{
                        	required:true
                        },
						
                },
				messages: {
					customerId:{
						number:"<font class='errorCreateUser'>Please provide numeric value.</font>", 
						maxlength:"<font class='errorCreateUser'>Maximum length should be 15.</font>"
					},
					crfId: {
						crfId:"<font class='errorCreateUser'>CAF Number should be numeric and 8 characters long.</font>"							
				   }, 
				   toDate:{
			    		required:"<font class='errorCreateUser'>Please provide payment date</font>"},
						
				   }
		});

    $('#search_PR').click(function() {
    if($("#idSearchPaymentReversal").valid()&& eitherOne())
	{ 
      document.forms[1].action = "paymentPosting.do?method=searchReversalPaymentDetails";
      document.forms[1].submit();
	}
    });
	
	/**** ----  Payment reversal reason----  *******/
	
	// On checkbox checked, inputs get disabled
	$('input:checkbox[name="reversalWOCrf"]').bind('change', function(){
	  if($(this).is(':checked')){
		  $('input:checkbox[name="reversalWOCrf"]').val();
	      $('input:text[name="crmCustomerDetailsPojo.customerId"], input:text[name="crmCustomerDetailsPojo.crfId"]').attr('disabled', true);
		   $('input:text[name="crmCustomerDetailsPojo.customerId"], input:text[name="crmCustomerDetailsPojo.crfId"]').val('');
		   $('#eitherErrorPopup').addClass('hide1');
		    
	  }
	  else
	  {
		  
		  $('input:text[name="crmCustomerDetailsPojo.customerId"], input:text[name="crmCustomerDetailsPojo.crfId"]').removeAttr('disabled');
		  $('#eitherErrorPopup').removeClass('hide1');
		  $('input[name="crmCustomerDetailsPojo.customerId"],input[name="crmCustomerDetailsPojo.crfId"]').attr('readonly', false);
		  
	  }
	});


	$('input:radio[name="docSrPaymentReversal"]').bind('change', function(){
		if($('#punchSRNo').is(':checked')){
			$('#punchSRNoShow').removeClass('hide1');
			$('#idRevSrNoPrReason').attr('disabled', false);
		}
		else
		{
			$('#idRevSrNoPrReason').attr('disabled', true);
			$('#punchSRNoShow').addClass('hide1');
		}
		if($('#uploadDocument').is(':checked')){
			$('#punchSRNoShow').addClass('hide1');
			$('#idRevSrNoPrReason').attr('disabled', true);
			$('.modelPopupDiv, .overlayDiv').fadeIn();
		}
		else
		{
			$('#idRevSrNoPrReason').attr('disabled', false);
			$('#punchSRNoShow').removeClass('hide1');
		}
		
		
    }); 
	
$.validator.addMethod("alphaNumeric", function(value,element) {
		return this.optional(element)|| validateAlphanumericSimple(value);

});
$.validator.addMethod('crmCustomerDetailsPojo.crfId',function(value, element) {
		return this.optional(element)|| validateCRF(value);
});
  $("#idPaymentReversal").validate({
	  		   	
                rules: {
						//User Details
                		'crmCustomerDetailsPojo.customerId':{
                			number:true,
                        	maxlength:15
                        },
                        'crmCustomerDetailsPojo.crfId': {
                        	'crmCustomerDetailsPojo.crfId':true
                        },
						'remarksPojo.reasonId':{
							dropDown:true
						},
						'remarksPojo.remarks':{
							required:true,
							maxlength:4000
						},
						docSrPaymentReversal:{
							required:true
						},
						'crmPaymentDetailsPojo.srId':{
							required:true,							
						}						
                },				
				messages: {
					'crmCustomerDetailsPojo.customerId':{
							number:"<font class='errorCreateUser'> Please provide numeric value.</font>", 
							maxlength:"<font class='errorCreateUser'>Maximum length should be 15.</font>"
						},
						'crmCustomerDetailsPojo.crfId': {
							'crmCustomerDetailsPojo.crfId':"CAF Number should be numeric and 8 characters long."
					   },
						'remarksPojo.reasonId':{
							dropDown:"<font class='errorCreateUser'> Please select payment revarsal reason.</font>"
						},
						'remarksPojo.remarks':{
							required:"<font class='errorAddress'> Please provide comments.</font>",
							maxlength:"<font class='errorAddress'> Please no insert more than 128 character.</font>"
						},
						docSrPaymentReversal:{
							required:"<font class='errorRadio'>Please select Upload Document or SR Number </font>"
						},
						'crmPaymentDetailsPojo.srId':{
							required:"<font class='errorTextbox'>Please provide SR Number</font>",
						}						
				}
	});

    $('#submit_PaymentReverse').click(function() {
    if($("#idPaymentReversal").valid() && eitherOnePopup())
	{  
    	
      var ans = confirm("Please confirm that you are going to reverse the Payment.");
      if(ans){
    	  	var reversalWOCrf=document.getElementById("widthoutIds").checked;
      		var customerId=document.getElementById("idRevCustomerId").value;
      		var crfId= document.getElementById("idRevCrfId").value;
      		var reversalReason=document.getElementById("idReversal").value;
      		var remarks=document.getElementById("idRevRemarks").value;
      		var radios = document.getElementsByName("docSrPaymentReversal");
      		var paymentId = document.getElementById("revPaymentId").value;
      		var radioOption="";
			for(var i = 0; i < radios.length; i++) {
			    if(radios[i].checked) 
			    {
			    radioOption = radios[i].value;
			    break;
			    }
			 }	
      		var srNoPrReason=document.getElementById("idRevSrNoPrReason").value;
      		
      		
      		parent.document.getElementById("idFinReversalWOCrf").value = reversalWOCrf;
      		parent.document.getElementById("idFinCustomerId").value = customerId;
      		parent.document.getElementById("idFinCrfId").value = crfId;
      		parent.document.getElementById("idFinReversalReason").value = reversalReason;
      		parent.document.getElementById("idFinRemarks").value = remarks;
      		parent.document.getElementById("idFindocSrPaymentReversal").value = radioOption;
      		parent.document.getElementById("idFinSrNoPrReason").value = srNoPrReason;
      		parent.document.getElementById("idPaymentId").value = paymentId;
      		parent.document.getElementById("idMappingId").value = paymentId;      		
      		parent.document.forms[1].action = "paymentPosting.do?method=paymentReversal";
      		parent.document.forms[1].submit();
      		//window.close();
      }
	}
    });
//    $('#paymentReversal').click(function(){
//        window.open('paymentPosting.do?method=paymentReversalPopUp', 'newWindow','width=800,height=430,scrollbars=yes,resizable=no,toolbar=no');
//    });  

$('input[name="crfId"], input[name="customerId"]').keyup(function(){
	
	eitherOne(); 
	/*if($('input[name="crfId"]').val()){
	$('input[name="crfId"]').attr('readonly', false);
	$('input[name="customerId"]').attr('readonly', true);
	
}
if($('input[name="customerId"]').val()){
	$('input[name="customerId"]').attr('readonly', false);
	$('input[name="crfId"]').attr('readonly', true);
}*/

});

$('input[name="crmCustomerDetailsPojo.crfId"], input[name="crmCustomerDetailsPojo.customerId"]').keyup(function(){
	eitherOnePopup();
	/*if($('input[name="crmCustomerDetailsPojo.crfId"]').val()){
	$('input[name="crmCustomerDetailsPojo.crfId"]').attr('readonly', false);
	$('input[name="crmCustomerDetailsPojo.customerId"]').attr('readonly', true);
	
}*/
/*if($('input[name="crmCustomerDetailsPojo.customerId"]').val()){
	$('input[name="crmCustomerDetailsPojo.customerId"]').attr('readonly', false);
	$('input[name="crmCustomerDetailsPojo.crfId"]').attr('readonly', true);
}*/

});
	
});
function paymentRevrsalWidthoutIds(checkbox){
	
	if($(checkbox).is(':checked')){
		$('#showRevCustomerId').addClass('hide1');
	}else{
		$('#showRevCustomerId').removeClass('hide1');
	}
}

function eitherOne(){
	var flag=true;
	if(!$('input[name="crfId"]').val()&&!$('input[name="customerId"]').val()){
		flag=false;
		$('#eitherError').show().html("Please provide at least 'Customer ID' or 'CAF Number'").addClass('errorTextbox').css({'width':'260px', 'top':'54px'});
		//$('input[name="crfId"],input[name="customerId"]').attr('readonly', false);
	}
	else{				
		$('#eitherError').hide();
	}	
	return flag;
}

function eitherOnePopup(){
	var flag=true;
	if(!$('input[name="crmCustomerDetailsPojo.crfId"]').val() && !$('input[name="crmCustomerDetailsPojo.customerId"]').val() && !$('input[name="crmCustomerDetailsPojo.customerId"]').attr('disabled') && !$('input[name="crmCustomerDetailsPojo.crfId"]').attr('disabled')){
		flag=false;
		$('#eitherErrorPopup').show().html('Please provide at least Customer ID or CAF No.').addClass('errorTextbox').css( 'width', 230 );
		//$('input[name="crmCustomerDetailsPojo.crfId"],input[name="crmCustomerDetailsPojo.customerId"]').attr('readonly', false);
	}
	else{				
		$('#eitherErrorPopup').hide();
	}
	
	return flag;
}
/*function paymentReversalPopUp(paymentId){
	var ans = confirm("Please be ensure before reversing the payment.");
	if(ans){
		document.getElementById("idPaymentId").value = paymentId;
		document.getElementById("idMappingId").value = paymentId;
	    window.open("paymentPosting.do?method=paymentReversalPopUp&paymentId="+ paymentId, 'newWindow','width=913,height=430,scrollbars=yes,resizable=no,toolbar=no, modal=yes');
	}
}*/

