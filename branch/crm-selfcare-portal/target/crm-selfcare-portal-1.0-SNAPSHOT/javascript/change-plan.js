// JavaScript Document
$(document).ready(function () {
             $("#BusinessInnerSubMenu div").addClass("colorBlue");
             $("#Internet").css("background", "url('images/top-tab-selected.png') no-repeat");
             $("#Internet div").addClass("colorWhite");
             $("#MenuILL").addClass("colorOrange");
             $("#TariffMenu").addClass("colorOrange");
             $("a[rel='PopUp']").colorbox({ transition: "none", fastIframe: false, initialWidth: "990px", initialHeight: "800px" });
             

         });

window.CurrentUsagePage = 0;
        $(document).ready(function () {
            fillUsageDetails(CurrentUsagePage);
            $('.next_usage').click(function () {
                //fillUsageDetails(++CurrentUsagePage);
                $('#ctl00_ContentPlaceHolder1_ButtonDownloadFurtherUsage').click();
            });
            $('.prev_usage').click(function () {
                //if(CurrentUsagePage > 0)
                //fillUsageDetails(--CurrentUsagePage);
                $('#ctl00_ContentPlaceHolder1_ButtonDownloadUsage').click();
            });
        });

        function fillUsageDetails(pagenum) {
            GetUsage( 2000143 , pagenum, 500, function (data) {
                $('.usageDetails .bodyContainer').html('');
                for(var i= 0; i<data.d.length; i++) {
                    var item = '<div class="bodyItem ' + (i%2 == 0 ? 'EvenBG2' : 'EvenBG') + '" ><span class="START_DATE">'+ data.d[i].START_DATE +'</span><span class="DURATION">'+ data.d[i].DURATION +'</span><span class="USAGE_IN_KB">' + data.d[i].USAGE_IN_KB +'</span><span class="FRAMED_IP">' + data.d[i].FRAMED_IP +'</span></div>';
                    $('.usageDetails .bodyContainer').append($(item));
                }
            });
        };


        var animatedHeadLightShade = '#fcecac';
        var animatedBodyLightShade = '#fcecac';
        var animatedHeadDarkShade = '#f5c300';
        var animatedBodyDarkShade = '#f5c300';
        var animatedHeadForeColor = '#1f487c';

        //Cache Elements to save processing :)
        var animateHeadTds = $('.animateHeadTd');
        var animateBodyTds = $('.animateBodyTd');
        /*var headAbsDivs = $('.' + headAbsDivClass);
        var bodyAbsDivs = $('.' + bodyAbsDivClass);*/

        var activeHeadTdClass = 'activeHeadTd';
        var inActiveHeadTdClass = 'inActiveHeadTd';
        var activeBodyTdClass = 'activeBodyTd';
        var inActiveBodyTdClass = 'inActiveBodyTd';
        var activeBaseBodyTdClass = 'activeBaseBodyTd';
        var inActiveBaseBodyTdClass = 'inActiveBaseBodyTd';

        var baseAnimationBlocked = false;
        var addOnAnimationBlocked = false;

        window.__activeAnimElemIndex = -1;
        window.__inActiveAnimElemIndex = -1;
        window.__selectedAddonIndex = -1;

        var headShadeColors = getColorArray(animatedHeadLightShade, animatedHeadDarkShade, animateHeadTds.length);
        var bodyShadeColors = getColorArray(animatedBodyLightShade, animatedBodyDarkShade, animateBodyTds.length);

        function   fun_onselectchangetext(varid){
            var retval=$("#drpselectdul").val();
            if (retval==""||retval==null||retval==undefined||retval=="--Select--") {//alert("Please select DUL");
                $("#onselectshowprice_div").css("display", "none");
                $("#onselectchangetext").css("display", "none");
                $('#onselectshowprice').html('');
                //$('#onselectchangetext').html('');
                return false;
    
            }else if (parseInt(retval)>8) {
                $("#onselectchangetext").css("display", "block");
                //$('#onselectchangetext').html("<storng>Do you require more than 8GB Booster Usage Top-Up?</strong><br>please upgrade your plan by adding Add-On DUL by</strong> <a onclick='showhidedivsontabclick(&apos;tabunlimited_popup&apos;);'>CLICK HERE</a></strong> or call our Call Center for a customised advice.");
                $("#onselectshowprice_div").css("display", "block");
                var topupprice=(parseInt(retval)/2)*99;
                // alert(topupprice);
                $('#onselectshowprice').html(topupprice);
            }
            else {
               // $('#onselectchangetext').html('');
                $("#onselectshowprice_div").css("display", "block");
                var topupprice=(parseInt(retval)/2)*99;
               // alert(topupprice);
                $('#onselectshowprice').html(topupprice);
                
            }


        }


        function showhidedivsontabclick(varid) {
            $("#step_1_unlimited").removeClass("inActiveStepNumber");
            $("#step_1_unlimited").addClass("activeStepNumber");
            $("#step_2_unlimited").removeClass("activeStepNumber");
            $("#step_2_unlimited").addClass("inActiveStepNumber");
            $("#showsecondstep_unlimited").css("display", "none");
            $('input[name=tariffNoAddOn]').removeAttr('checked');
            $('#myopNavigateNext').attr('disabled', 'disabled');
            $('#myopValueplanNavigateNext').removeAttr('disabled');
            $('input[name=tariffNoAddOn]').removeAttr('checked');
            $('input[name=tariffAddonPlan]').removeAttr('checked');
            $('.addOnOverlayDiv').show();
            $('.topArrow').hide();
            $('#arrowNumberIMG').hide();
            $('.noAddonPlanDiv').hide();
            if (varid=="tabunlimited_popup") {
                $("#BusinessInnerSubMenu div").removeClass("colorBlue");
                $("#BusinessInnerSubMenu div").addClass("colorBlue");
                $("#Internet").css("background", "url('images/top-tab-selected.png') no-repeat");
                $("#Internet div").addClass("colorWhite");
                //$("#BusinessInnerSubMenu div").removeClass("colorBlue");
                $("#iLL div").removeClass("colorWhite");
                $("#iLL").css("background", "url('images/top-tab-deselected.png') no-repeat");
                var contentofgrid = $("#Dummylayoutdiv");
                //  alert(contentofgrid.html());
                $('input[name=tariffAddonPlan]').removeAttr('checked');
                $("#tbl_Planmigration").css("display", "block");
                $("#showhideonlimited").css("display", "none");
                $("#showorhide").css("display", "block");

                $("#show_onclickofradiooption").css("display", "none");
                $("#showradiooption").css("display", "none");
                //            $('#tariffUsageBasedPlan').attr('checked', false);
                //            $('#tariffBasePlan').attr('checked', false);
                //            $('#plan_details').attr('checked', false);

                $('input:radio[name=tariffUsageBasedPlan]').each(function () {
                    $(this).prop('checked', false);
                });

                $('input:radio[name=tariffBasePlan]').each(function () {
                    $(this).prop('checked', false);
                });

                $('input:radio[name=plan_details]').each(function () {
                    $(this).prop('checked', false);
                });

                $('input:checkbox[name=ticketselect]').each(function () {
                    $(this).prop('checked', false);
                });
                $('input:checkbox[name=paymentselect]').each(function () {
                    $(this).prop('checked', false);
                });

                $('input:checkbox[name=paymentselectall]').prop('checked', false);
                $('input:checkbox[name=ticketselectall]').prop('checked', false);
                //            $('#tariffUsageBasedPlan').find("input:radio:checked").prop('checked', false);
                //            $('#tariffBasePlan').find("input:radio:checked").prop('checked', false);
                //            $('#plan_details').find("input:radio:checked").prop('checked', false);
                //
                //$("#showorhide").html('');
                // $("#showorhide").html(contentofgrid.html());
                $("#showorhide").load(contentofgrid.html());
                $("#showorhide").css("display", "block");
                $("#tbl_Planmigration").css("display", "block");
                $("#showhideonlimited").css("display", "none");
                $("#showradiooption").css("display", "none");

                
            }
            else if(varid=="tablimited_popup") {
                $("#BusinessInnerSubMenu div").removeClass("colorBlue");

                $("#BusinessInnerSubMenu div").addClass("colorBlue");
                $("#iLL").css("background", "url('images/top-tab-selected.png') no-repeat");
                $("#iLL div").addClass("colorWhite");
                $("#Internet").css("background", "url('images/top-tab-deselected.png') no-repeat");
                $("#Internet div").removeClass("colorWhite");
                //$("#Internet div").removeClass("colorBlue");

                $("#showhideonlimited").css("display", "block");
                $("#showorhide").css("display", "none");
                $("#tbl_Planmigration").css("display", "block");
                $("#show_onclickofradiooption").css("display", "none");
                $("#showradiooption").css("display", "none");
                //            $('#tariffUsageBasedPlan').find("input:radio:checked").prop('checked', false);
                //            $('#tariffBasePlan').find("input:radio:checked").prop('checked', false);
                //            $('#plan_details').find("input:radio:checked").prop('checked', false);
                $('input:radio[name=tariffUsageBasedPlan]').each(function () {
                    $(this).prop('checked', false);
                });

                $('input:radio[name=tariffBasePlan]').each(function () {
                    $(this).prop('checked', false);
                });

                $('input:radio[name=plan_details]').each(function () {
                    $(this).prop('checked', false);
                });

                $('input:checkbox[name=ticketselect]').each(function () {
                    $(this).prop('checked', false);
                });
                $('input:checkbox[name=paymentselect]').each(function () {
                    $(this).prop('checked', false);
                });

                $('input:checkbox[name=paymentselectall]').prop('checked', false);
                $('input:checkbox[name=ticketselectall]').prop('checked', false);
                $("#showhideonlimited").css("display", "block");
                $("#showradiooption").css("display", "none");
                $("#showorhide").css("display", "block");
                $("#tbl_Planmigration").css("display", "none");
                

            }
            else {
    
            }
    
        }




        function showhidedivsontabclickforA() {
            //alert("testindf");
            $( "#Divtopupplanpopup" ).dialog( "close" );
            $("#Divchangeplanpopup").dialog({
                modal: true,
                width: 1000,
                height: 570,
                draggable: true,
                resizeable: false
            });
            $('#myopNavigateNext').attr('disabled', 'disabled');
            $('#myopValueplanNavigateNext').removeAttr('disabled');
            $('input[name=tariffNoAddOn]').removeAttr('checked');
            $('input[name=tariffAddonPlan]').removeAttr('checked');
            $('.addOnOverlayDiv').show();
            $('.topArrow').hide();
            $('#arrowNumberIMG').hide();
            $('.noAddonPlanDiv').hide();
            $('input[name=tariffNoAddOn]').removeAttr('checked');
            $("#step_1_unlimited").removeClass("inActiveStepNumber");
            $("#step_1_unlimited").addClass("activeStepNumber");
            $("#step_2_unlimited").removeClass("activeStepNumber");
            $("#step_2_unlimited").addClass("inActiveStepNumber");
            $("#showsecondstep_unlimited").css("display", "none");
                $("#BusinessInnerSubMenu div").removeClass("colorBlue");
                $("#BusinessInnerSubMenu div").addClass("colorBlue");
                $("#Internet").css("background", "url('images/top-tab-selected.png') no-repeat");
                $("#Internet div").addClass("colorWhite");
                //$("#BusinessInnerSubMenu div").removeClass("colorBlue");
                $("#iLL div").removeClass("colorWhite");
                $("#iLL").css("background", "url('images/top-tab-deselected.png') no-repeat");
                var contentofgrid = $("#Dummylayoutdiv");
                //  alert(contentofgrid.html());
                $('input[name=tariffAddonPlan]').removeAttr('checked');
                $("#tbl_Planmigration").css("display", "block");
                $("#showhideonlimited").css("display", "none");
                $("#showorhide").css("display", "block");

                $("#show_onclickofradiooption").css("display", "none");
                $("#showradiooption").css("display", "none");
                //            $('#tariffUsageBasedPlan').attr('checked', false);
                //            $('#tariffBasePlan').attr('checked', false);
                //            $('#plan_details').attr('checked', false);

                $('input:radio[name=tariffUsageBasedPlan]').each(function () {
                    $(this).prop('checked', false);
                });

                $('input:radio[name=tariffBasePlan]').each(function () {
                    $(this).prop('checked', false);
                });

                $('input:radio[name=plan_details]').each(function () {
                    $(this).prop('checked', false);
                });

                $('input:checkbox[name=ticketselect]').each(function () {
                    $(this).prop('checked', false);
                });
                $('input:checkbox[name=paymentselect]').each(function () {
                    $(this).prop('checked', false);
                });

                $('input:checkbox[name=paymentselectall]').prop('checked', false);
                $('input:checkbox[name=ticketselectall]').prop('checked', false);
                //            $('#tariffUsageBasedPlan').find("input:radio:checked").prop('checked', false);
                //            $('#tariffBasePlan').find("input:radio:checked").prop('checked', false);
                //            $('#plan_details').find("input:radio:checked").prop('checked', false);
                //
                //$("#showorhide").html('');
                // $("#showorhide").html(contentofgrid.html());
                $("#showorhide").load(contentofgrid.html());
                $("#showorhide").css("display", "block");
                $("#tbl_Planmigration").css("display", "block");
                $("#showhideonlimited").css("display", "none");
                $("#showradiooption").css("display", "none");
        }



        $("#btnchangeplan").click(function (event) {
            event.preventDefault();
            $("#BusinessInnerSubMenu div").removeClass("colorBlue");
            $("#BusinessInnerSubMenu div").addClass("colorBlue");
            $("#Internet").css("background", "url('images/top-tab-selected.png') no-repeat");
            $("#Internet div").addClass("colorWhite");
            //$("#BusinessInnerSubMenu div").removeClass("colorBlue");
            $("#iLL div").removeClass("colorWhite");
            $("#iLL").css("background", "url('images/top-tab-deselected.png') no-repeat");
               
            var contentofgrid = $("#Dummylayoutdiv");
            //  alert(contentofgrid.html());
            $('input[name=tariffAddonPlan]').removeAttr('checked');
            $("#tbl_Planmigration").css("display", "block");
            $("#showhideonlimited").css("display", "none");
            $("#showorhide").css("display", "block");
            $("#step_1_unlimited").removeClass("inActiveStepNumber");
            $("#step_1_unlimited").addClass("activeStepNumber");
            $("#step_2_unlimited").removeClass("activeStepNumber");
            $("#step_2_unlimited").addClass("inActiveStepNumber");
            $("#showsecondstep_unlimited").css("display", "none");
            $('input[name=tariffNoAddOn]').removeAttr('checked');
           
            $("#show_onclickofradiooption").css("display", "none");
            $("#showradiooption").css("display", "none");
            //            $('#tariffUsageBasedPlan').attr('checked', false);
            //            $('#tariffBasePlan').attr('checked', false);
            //            $('#plan_details').attr('checked', false);

            $('input:radio[name=tariffUsageBasedPlan]').each(function () {
                $(this).prop('checked', false);
            });

            $('input:radio[name=tariffBasePlan]').each(function () {
                $(this).prop('checked', false);
            });

            $('input:radio[name=plan_details]').each(function () {
                $(this).prop('checked', false);
            });

            $('input:checkbox[name=ticketselect]').each(function () {
                $(this).prop('checked', false);
            });
            $('input:checkbox[name=paymentselect]').each(function () {
                $(this).prop('checked', false);
            });

            $('input:checkbox[name=paymentselectall]').prop('checked', false);
            $('input:checkbox[name=ticketselectall]').prop('checked', false);
            //            $('#tariffUsageBasedPlan').find("input:radio:checked").prop('checked', false);
            //            $('#tariffBasePlan').find("input:radio:checked").prop('checked', false);
            //            $('#plan_details').find("input:radio:checked").prop('checked', false);
            //
            //$("#showorhide").html('');
            // $("#showorhide").html(contentofgrid.html());
            $("#showorhide").load(contentofgrid.html());

            $('input:radio[name=plan]').each(function () {
                $(this).prop('checked', false);
            });
            $("#showorhide").css("display", "none");
            $("#showhideonlimited").css("display", "none");
            //$("#show_onclickofradiooption").css("display", "none");
            $("#showradiooption").css("display", "none");
            //                $("#PaymentCenter").load('BillPay.aspx', function() {
            $("#showorhide").css("display", "block");
            var baseAnimationBlocked = false;
            var addOnAnimationBlocked = false;

            $("#tbl_Planmigration").css("display", "block");
            $('#myopNavigateNext').attr('disabled', 'disabled');
            $('#myopValueplanNavigateNext').removeAttr('disabled');
            $('input[name=tariffNoAddOn]').removeAttr('checked');
            $('input[name=tariffAddonPlan]').removeAttr('checked');
            $('.addOnOverlayDiv').show();
            $('.topArrow').hide();
            $('#arrowNumberIMG').hide();
            $('.noAddonPlanDiv').hide();
            $("#Divchangeplanpopup").dialog({
                modal: true,
                width: 1000,
                height: 570,
                draggable: true,
                resizeable: false
            });
            //                });
        });


        function checkboxclick_table(idvar) {
           // alert(idvar);
            if ($("input[name="+idvar+"]").is(':checked')) {
                $("#showradiooption").css("display", "block");
                $('#drpfocus').focus();
            }
            else {
                $("#showradiooption").css("display", "none");
            }
           
        }

        //$("#tariffAddonPlan").click(function (event) {
        //    if (!$('input[name=tariffAddonPlan]').attr('checked')) {
        //        $("#showradiooption").css("display", "none");
        //    }
        //    else {
        //        $("#showradiooption").css("display", "block");
        //    }
            
        //});
        //$("#tariffNoAddOn").click(function (event) {
        //    if (!$('input[name=tariffNoAddOn]').attr('checked')) {
        //        $("#showradiooption").css("display", "none");
        //    }
        //    else {
        //        $("#showradiooption").css("display", "block");
        //    }

        //});



        $("#btntopup").click(function (event) {
            event.preventDefault();
            //                $("#PaymentCenter").load('BillPay.aspx', function() {
            $('input:radio[name=plan_ussage]').each(function () {
                $(this).prop('checked', false);
            });
            $("#step_1_unlimited").removeClass("inActiveStepNumber");
            $("#step_1_unlimited").addClass("activeStepNumber");
            $("#step_2_unlimited").removeClass("activeStepNumber");
            $("#step_2_unlimited").addClass("inActiveStepNumber");
            $("#showsecondstep_unlimited").css("display", "none");
            $('input[name=tariffNoAddOn]').removeAttr('checked');
            $("#onselectshowprice_div").css("display", "none");
            $("#onselectchangetext").css("display", "none");
            $("#div_Speed").css("display", "none");
            $("#show_onclickofradiooption").css("display", "none");
            $("#div_planbooster").css("display", "none"); 
            $("#drpselectdul").val('');
            // $("#div_Speed").css("display", "none");
            $("#Divtopupplanpopup").dialog({
                modal: true,
                width: 590,
                height: 570,
                draggable: true,
                resizeable: false
            });
            //                });
        });

        $("#btnlogticket").click(function (event) {
            event.preventDefault();
           

            $("#Divlogticketpopup").dialog({
                modal: true,
                width: 590,
                height: 'auto',
                draggable: true,
                resizeable: false
            });
            //                });
        });

        $("#btnsubmittopupo").click(function (event) {
            event.preventDefault();
            var retval=$("#drpselectdul option:selected").text();
            // alert(retval);
            if (retval==""||retval==null||retval==undefined||retval=="--Select--") {alert("Please select DUL");
                return false;
    
            }else {
                $("#drpselectedvalue").text($("#drpselectdul option:selected").text());
            }
           
            $("#Divtopupplanpopupconfirm").dialog({
                modal: true,
                width: 260,
                height: 'auto',
                draggable: true,
                resizeable: false
            });
            //                });
        });

        $("#btnokconfirm").click(function (event) {
            event.preventDefault();
            $( "#Divtopupplanpopupconfirm" ).dialog( "close" );
            $( "#Divtopupplanpopup" ).dialog( "close" );
            //                });
        });
        $("#btncancelconfirm").click(function (event) {
            event.preventDefault();
            $( "#Divtopupplanpopupconfirm" ).dialog( "close" );
            // $( "#Divtopupplanpopup" ).dialog( "close" );
            //                });
        });

        $("#openchangeplan").click(function (event) {
            event.preventDefault();

            $("#BusinessInnerSubMenu div").removeClass("colorBlue");
            $("#BusinessInnerSubMenu div").addClass("colorBlue");
            $("#Internet").css("background", "url('images/top-tab-selected.png') no-repeat");
            $("#Internet div").addClass("colorWhite");
            //$("#BusinessInnerSubMenu div").removeClass("colorBlue");
            $("#iLL div").removeClass("colorWhite");
            $("#iLL").css("background", "url('images/top-tab-deselected.png') no-repeat");
               
            var contentofgrid = $("#Dummylayoutdiv");
            //  alert(contentofgrid.html());
            $('input[name=tariffAddonPlan]').removeAttr('checked');
            $("#tbl_Planmigration").css("display", "block");
            $("#showhideonlimited").css("display", "none");
            $("#showorhide").css("display", "block");

            $("#show_onclickofradiooption").css("display", "none");
            $("#showradiooption").css("display", "none");
            //            $('#tariffUsageBasedPlan').attr('checked', false);
            //            $('#tariffBasePlan').attr('checked', false);
            //            $('#plan_details').attr('checked', false);

            $('input:radio[name=tariffUsageBasedPlan]').each(function () {
                $(this).prop('checked', false);
            });

            $('input:radio[name=tariffBasePlan]').each(function () {
                $(this).prop('checked', false);
            });

            $('input:radio[name=plan_details]').each(function () {
                $(this).prop('checked', false);
            });

            $('input:checkbox[name=ticketselect]').each(function () {
                $(this).prop('checked', false);
            });
            $('input:checkbox[name=paymentselect]').each(function () {
                $(this).prop('checked', false);
            });

            $('input:checkbox[name=paymentselectall]').prop('checked', false);
            $('input:checkbox[name=ticketselectall]').prop('checked', false);
            //            $('#tariffUsageBasedPlan').find("input:radio:checked").prop('checked', false);
            //            $('#tariffBasePlan').find("input:radio:checked").prop('checked', false);
            //            $('#plan_details').find("input:radio:checked").prop('checked', false);
            //
            //$("#showorhide").html('');
            // $("#showorhide").html(contentofgrid.html());
            $("#showorhide").load(contentofgrid.html());

            $('input:radio[name=plan]').each(function () {
                $(this).prop('checked', false);
            });
            $("#showorhide").css("display", "none");
            $("#showhideonlimited").css("display", "none");
            //$("#show_onclickofradiooption").css("display", "none");
            $("#showradiooption").css("display", "none");
            //                $("#PaymentCenter").load('BillPay.aspx', function() {
            $("#showorhide").css("display", "block");

            $("#tbl_Planmigration").css("display", "block");
            $( "#Divtopupplanpopup" ).dialog( "close" );
            $("#Divchangeplanpopup").dialog({
                modal: true,
                width: 1000,
                height: 570,
                draggable: true,
                resizeable: false
            });
            $('input:radio[name=plan]').each(function () {
                $(this).prop('checked', false);
            });
            //$("#showorhide").css("display", "none");
           // $("#showhideonlimited").css("display", "none");
            //$("#show_onclickofradiooption").css("display", "none");
            $("#showradiooption").css("display", "none");
            //                $("#PaymentCenter").load('BillPay.aspx', function() {
           
            
            //                });
        });
		
		$('input[name=tariffAddonPlan]').removeAttr('checked');
                                                        $('input[name=tariffNoAddOn]').removeAttr('checked');
                                                        $('input[name=tariffBasePlan]').removeAttr('checked');

                                                        $('#myopNavigateNext').click(function () {
                                                            var plandata = { BasePlanPrice: null, AddonPlanIndex: null };
                                                            plandata.BasePlanPrice = $('input[name=tariffBasePlan]:radio:checked').val();
                                                            if (!$('input[name=tariffNoAddOn]').attr('checked')) {
                                                                $('input[name=tariffAddonPlan]:checkbox:checked').each(function () {
                                                                    plandata.AddonPlanIndex = $(this).data('index');
                                                                });
                                                            }
                                                            //console.log(plandata);
                                                            postToSelf(plandata);
                                                        });
                                                        function postToSelf(submitdata) {
                                                            var form = $('<form action="TariffsUnlimitedPlan.aspx" method="post">' +
                                        '<input type="hidden" name="BasePlanPrice" value="' + submitdata.BasePlanPrice + '" />' +
                                        '<input type="hidden" name="AddonPlanIndex" value="' + submitdata.AddonPlanIndex + '" />' +
                                        '</form>');
                                                            $('body').append(form);
                                                            $(form).submit();
                                                        }

                                                        $(window).load(function () {

                                                        });
														
														
														function showunlimiteddiv() {
            var contentofgrid = $("#Dummylayoutdiv");
            //  alert(contentofgrid.html());
            $('input[name=tariffAddonPlan]').removeAttr('checked');
            $("#tbl_Planmigration").css("display", "block");
            $("#showhideonlimited").css("display", "none");
            $("#showorhide").css("display", "block");

            $("#show_onclickofradiooption").css("display", "none");
            $("#showradiooption").css("display", "none");
            //            $('#tariffUsageBasedPlan').attr('checked', false);
            //            $('#tariffBasePlan').attr('checked', false);
            //            $('#plan_details').attr('checked', false);

            $('input:radio[name=tariffUsageBasedPlan]').each(function () {
                $(this).prop('checked', false);
            });

            $('input:radio[name=tariffBasePlan]').each(function () {
                $(this).prop('checked', false);
            });

            $('input:radio[name=plan_details]').each(function () {
                $(this).prop('checked', false);
            });

            $('input:checkbox[name=ticketselect]').each(function () {
                $(this).prop('checked', false);
            });
            $('input:checkbox[name=paymentselect]').each(function () {
                $(this).prop('checked', false);
            });

            $('input:checkbox[name=paymentselectall]').prop('checked', false);
            $('input:checkbox[name=ticketselectall]').prop('checked', false);
            //            $('#tariffUsageBasedPlan').find("input:radio:checked").prop('checked', false);
            //            $('#tariffBasePlan').find("input:radio:checked").prop('checked', false);
            //            $('#plan_details').find("input:radio:checked").prop('checked', false);
            //
            //$("#showorhide").html('');
            // $("#showorhide").html(contentofgrid.html());
            $("#showorhide").load(contentofgrid.html());


        }
        function showlimited_plandiv() {
            $("#showhideonlimited").css("display", "block");
            $("#showorhide").css("display", "none");
            $("#tbl_Planmigration").css("display", "block");
            $("#show_onclickofradiooption").css("display", "none");
            $("#showradiooption").css("display", "none");
            //            $('#tariffUsageBasedPlan').find("input:radio:checked").prop('checked', false);
            //            $('#tariffBasePlan').find("input:radio:checked").prop('checked', false);
            //            $('#plan_details').find("input:radio:checked").prop('checked', false);
            $('input:radio[name=tariffUsageBasedPlan]').each(function () {
                $(this).prop('checked', false);
            });

            $('input:radio[name=tariffBasePlan]').each(function () {
                $(this).prop('checked', false);
            });

            $('input:radio[name=plan_details]').each(function () {
                $(this).prop('checked', false);
            });

            $('input:checkbox[name=ticketselect]').each(function () {
                $(this).prop('checked', false);
            });
            $('input:checkbox[name=paymentselect]').each(function () {
                $(this).prop('checked', false);
            });

            $('input:checkbox[name=paymentselectall]').prop('checked', false);
            $('input:checkbox[name=ticketselectall]').prop('checked', false);

        }
        function showradiobuttons() {
           //$("#showradiooption").css("display", "block");
            // $("#showhideonlimited").css("display", "block");
            //  $("#showhideonlimited").css("display", "block");
            $("#showsecondstep_unlimited").css("display", "block");
            $("#step_1_unlimited").removeClass("activeStepNumber");
            $("#step_1_unlimited").addClass("inActiveStepNumber");
            $("#step_2_unlimited").removeClass("inActiveStepNumber");
            $("#step_2_unlimited").addClass("activeStepNumber");

        }

        function showradiobuttons11() {
            $("#showradiooption").css("display", "block");
            $( "#drpfocus" ).focus();
            // $("#showhideonlimited").css("display", "block");
            //  $("#showhideonlimited").css("display", "block");

        }


        function show_divwith_table() {
            $("#tabledisplay").css("display", "block");
            $("#show_onclickofradiooption").css("display", "block");
            $('input:checkbox[name=ticketselect]').each(function () {
                $(this).prop('checked', false);
            });
            $('input:checkbox[name=paymentselect]').each(function () {
                $(this).prop('checked', false);
            });

            $('input:checkbox[name=paymentselectall]').prop('checked', false);
            $('input:checkbox[name=ticketselectall]').prop('checked', false);

            if ($('input:radio[name=planmigration]:checked').val() == "Plan Renew") {
                // alert($('input:radio[name=planmigration]:checked').val());
                $("#trshowhideonplanrenew").attr("display","none");
                $("#trshowhideonplanrenew").css("display", "none");
                // $("#trshowhideonplanrenew1").css("display", "none");
            }
            else {
                // alert($('input:radio[name=planmigration]:checked').val());
                $("#trshowhideonplanrenew").style.removeAttr("style");
                $("#trshowhideonplanrenew").css("display", "inline-flexbox");
                // $("#trshowhideonplanrenew1").css("display", "block");

            }

        }
        function show_divwithout_table() {
            $("#tabledisplay").css("display", "none");
            $("#show_onclickofradiooption").css("display", "block");
            $('input:checkbox[name=ticketselect]').each(function () {
                $(this).prop('checked', false);
            });
            $('input:checkbox[name=paymentselect]').each(function () {
                $(this).prop('checked', false);
            });

            $('input:checkbox[name=paymentselectall]').prop('checked', false);
            $('input:checkbox[name=ticketselectall]').prop('checked', false);
            if ($('input:radio[name=planmigration]:checked').val() == "Plan Renew") {
                $("#trshowhideonplanrenew").css("display", "none");
                // $("#trshowhideonplanrenew1").css("display", "none");
            }
            else {
                $("#trshowhideonplanrenew").removeAttr("style");
                $("#trshowhideonplanrenew").css("display", "inline-flexbox");
                // $("#trshowhideonplanrenew1").css("display", "block");

            }
        }




        function statusgridcheckboxselect() {
            //            $('input:radio[name=paymentselectall]').each(function () {
            //            if($(this).prop('checked', true)){
            //            $(this).prop('checked', false);}
            //            else{$(this).prop('checked', true);}
            //                
            //            });
            var isChecked = $('#paymentselectall').prop('checked');


            if (!isChecked) {
                $('input:checkbox[name=paymentselect]').each(function () {
                    $(this).prop('checked', false);
                });

            }
            else {
                $('input:checkbox[name=paymentselect]').each(function () {
                    $(this).prop('checked', true);
                });

            }

        }

        function ticketgridcheckboxselect() {
            var isChecked = $('#ticketselectall').prop('checked');


            if (!isChecked) {
                $('input:checkbox[name=ticketselect]').each(function () {
                    $(this).prop('checked', false);
                });

            }
            else {
                $('input:checkbox[name=ticketselect]').each(function () {
                    $(this).prop('checked', true);
                });

            }
        }

        function fun_showPlanRenew() {

            $("#tbl_Planmigration").css("display", "none");
            $("#tabledisplay").css("display", "none");
            $("#tr_PlanBooster").css("display", "none");
            // $("#showhideonlimited").css("display", "none");
            // $("#showhideonlimited").css("display", "none");
            //$("#showonplanmigration").css("display", "none");
            $("#showhideonlimited").css("display", "none");
            $("#tbl_Planmigration").css("display", "block");
            $("#showonplanmigration").css("display", "block");
            $("#tbl_PlanBooster").css("display", "none");
            $("#showorhide").css("display", "none");
            $("#show_onclickofradiooption").css("display", "none");
            $("#showradiooption").css("display", "none");
            $('input:radio[name=tariffUsageBasedPlan]').each(function () {
                $(this).prop('checked', false);
            });
            $('input:radio[name=tariffBasePlan]').each(function () {
                $(this).prop('checked', false);
            });

            $('input:radio[name=plan_details]').each(function () {
                $(this).prop('checked', false);
            });

            $('input:checkbox[name=ticketselect]').each(function () {
                $(this).prop('checked', false);
            });
            $('input:checkbox[name=paymentselect]').each(function () {
                $(this).prop('checked', false);
            });
            $('input:checkbox[name=paymentselectall]').prop('checked', false);
            $('input:checkbox[name=ticketselectall]').prop('checked', false);

            $('input:radio[name=tariffUsageBasedPlan]').each(function () {
                $(this).prop('checked', false);
            });

            $('input:radio[name=tariffBasePlan]').each(function () {
                $(this).prop('checked', false);
            });

            $('input:radio[name=plan_details]').each(function () {
                $(this).prop('checked', false);
            });

            $('input:checkbox[name=ticketselect]').each(function () {
                $(this).prop('checked', false);
            });
            $('input:checkbox[name=paymentselect]').each(function () {
                $(this).prop('checked', false);
            });
            $('input:radio[name=plan]').each(function () {
                $(this).prop('checked', false);
            });

            $("#tr_titleplanbooster").css("display", "none");
            $("#tr_titleplanmigration").css("display", "block");

            $('input:checkbox[name=paymentselectall]').prop('checked', false);
            $('input:checkbox[name=ticketselectall]').prop('checked', false);

            $("#tr_PlanBoosterpaidfoc").css("display", "block");
            $("#div_planbooster").css("display", "none");
            $("#div_Speed").css("display", "none");
            $('input:radio[name=plan_paid_booster]').each(function () {
                $(this).prop('checked', false);
            });
            $("#tr_PlanBoosterpaidfoc").css("display", "none");
            $('input:radio[name=plan_ussage]').each(function () {
                $(this).prop('checked', false);
            });

        }

        function fun_showPlanmigration() {

            $("#tbl_Planmigration").css("display", "none");
            $("#tabledisplay").css("display", "none");
            $("#tr_PlanBooster").css("display", "none");
            // $("#showhideonlimited").css("display", "none");
            // $("#showhideonlimited").css("display", "none");
            //$("#showonplanmigration").css("display", "none");
            $("#showhideonlimited").css("display", "none");
            $("#tbl_Planmigration").css("display", "block");
            $("#showonplanmigration").css("display", "block");
            $("#tbl_PlanBooster").css("display", "none");
            $("#showorhide").css("display", "none");
            $("#show_onclickofradiooption").css("display", "none");
            $("#showradiooption").css("display", "none");
            $('input:radio[name=tariffUsageBasedPlan]').each(function () {
                $(this).prop('checked', false);
            });
            $('input:radio[name=tariffBasePlan]').each(function () {
                $(this).prop('checked', false);
            });

            $('input:radio[name=plan_details]').each(function () {
                $(this).prop('checked', false);
            });

            $('input:checkbox[name=ticketselect]').each(function () {
                $(this).prop('checked', false);
            });
            $('input:checkbox[name=paymentselect]').each(function () {
                $(this).prop('checked', false);
            });
            $('input:checkbox[name=paymentselectall]').prop('checked', false);
            $('input:checkbox[name=ticketselectall]').prop('checked', false);

            $('input:radio[name=tariffUsageBasedPlan]').each(function () {
                $(this).prop('checked', false);
            });

            $('input:radio[name=tariffBasePlan]').each(function () {
                $(this).prop('checked', false);
            });

            $('input:radio[name=plan_details]').each(function () {
                $(this).prop('checked', false);
            });

            $('input:checkbox[name=ticketselect]').each(function () {
                $(this).prop('checked', false);
            });
            $('input:checkbox[name=paymentselect]').each(function () {
                $(this).prop('checked', false);
            });
            $('input:radio[name=plan]').each(function () {
                $(this).prop('checked', false);
            });

            $("#tr_titleplanbooster").css("display", "none");
            $("#tr_titleplanmigration").css("display", "block");

            $('input:checkbox[name=paymentselectall]').prop('checked', false);
            $('input:checkbox[name=ticketselectall]').prop('checked', false);

            $("#tr_PlanBoosterpaidfoc").css("display", "block");
            $("#div_planbooster").css("display", "none");
            $("#div_Speed").css("display", "none");
            $('input:radio[name=plan_paid_booster]').each(function () {
                $(this).prop('checked', false);
            });
            $("#tr_PlanBoosterpaidfoc").css("display", "none");
            $('input:radio[name=plan_ussage]').each(function () {
                $(this).prop('checked', false);
            });
            if ($('input:radio[name=planmigration]:checked').val() == "Plan Renew") {
                // alert($('input:radio[name=planmigration]:checked').val());
                $("#trshowhideonplanrenew").css("display", "none");
                // $("#trshowhideonplanrenew1").css("display", "none");
            }
            else {
                // alert($('input:radio[name=planmigration]:checked').val());
                $("#trshowhideonplanrenew").removeAttr("style");
                $("#trshowhideonplanrenew").css("display", "inline-flexbox");
                //  $("#trshowhideonplanrenew1").css("display", "block");

            }
        }
        function fun_showPlanBooster() {
            $("#tabledisplay").css("display", "none");
            $('input:radio[name=plan_ussage]').each(function () {
                $(this).prop('checked', false);
            });
            $("#tr_PlanBooster").css("display", "block");
            $("#tr_titleplanbooster").css("display", "block");
            $("#tr_titleplanmigration").css("display", "none");
            $("#showonplanmigration").css("display", "none");
            ///////////
            $("#tr_PlanBoosterpaidfoc").css("display", "block");
            $("#div_planbooster").css("display", "none");
            $("#div_Speed").css("display", "none");
            $('input:radio[name=plan_paid_booster]').each(function () {
                $(this).prop('checked', false);
            });
            $("#tr_PlanBoosterpaidfoc").css("display", "none");
            //////////////
            $("#tbl_Planmigration").css("display", "none");
            $("#showhideonlimited").css("display", "none");
            $("#tbl_Planmigration").css("display", "none");
            $("#tbl_PlanBooster").css("display", "block");
            $("#showonplanbooster").css("display", "block");
            $("#showorhide").css("display", "none");
            $("#show_onclickofradiooption").css("display", "none");
            $("#showradiooption").css("display", "none");

            $('input:radio[name=tariffUsageBasedPlan]').each(function () {
                $(this).prop('checked', false);
            });

            $('input:radio[name=tariffBasePlan]').each(function () {
                $(this).prop('checked', false);
            });

            $('input:radio[name=plan_details]').each(function () {
                $(this).prop('checked', false);
            });

            $('input:checkbox[name=ticketselect]').each(function () {
                $(this).prop('checked', false);
            });
            $('input:checkbox[name=paymentselect]').each(function () {
                $(this).prop('checked', false);
            });

            $('input:checkbox[name=paymentselectall]').prop('checked', false);
            $('input:checkbox[name=ticketselectall]').prop('checked', false);

            $("#showhideonlimited").css("display", "none");
            $("#showorhide").css("display", "none");
            $("#show_onclickofradiooption").css("display", "none");
            $("#showradiooption").css("display", "none");
            $('input:radio[name=tariffUsageBasedPlan]').each(function () {
                $(this).prop('checked', false);
            });

            $('input:radio[name=tariffBasePlan]').each(function () {
                $(this).prop('checked', false);
            });

            $('input:radio[name=plan_details]').each(function () {
                $(this).prop('checked', false);
            });

            $('input:checkbox[name=ticketselect]').each(function () {
                $(this).prop('checked', false);
            });
            $('input:checkbox[name=paymentselect]').each(function () {
                $(this).prop('checked', false);
            });
            $('input:radio[name=plan]').each(function () {
                $(this).prop('checked', false);
            });
            $("#show_onclickofradiooption").css("display", "none");

            $('input:checkbox[name=paymentselectall]').prop('checked', false);
            $('input:checkbox[name=ticketselectall]').prop('checked', false);
            if ($('input:radio[name=planmigration]:checked').val() == "Plan Renew") {
                // alert($('input:radio[name=planmigration]:checked').val());
                $("#trshowhideonplanrenew").css("display", "none");
                // $("#trshowhideonplanrenew1").css("display", "none");
            }
            else {
                // alert($('input:radio[name=planmigration]:checked').val());
                $("#trshowhideonplanrenew").removeAttr("style");
                $("#trshowhideonplanrenew").css("display", "inline-flexbox");
                //  $("#trshowhideonplanrenew1").css("display", "block");

            }
        }
        function showlimited_plandivbooster() {
            $("#tr_PlanBoosterpaidfoc").css("display", "block");
            $("#div_planbooster").css("display", "none");
            $("#div_Speed").css("display", "none");
            $("#tabledisplay").css("display", "none");

            $("#show_onclickofradiooption").css("display", "none");
            // show_onclickofradiooption
            //            $('input:radio[name=plan]').each(function () {
            //                $(this).prop('checked', false);
            //            });
            $('input:radio[name=plan_paid_booster]').each(function () {
                $(this).prop('checked', false);
            });
            // $("#tr_PlanBoosterpaidfoc").css("display", "none");
            var is_plan_ussage = $('input[name=plan_ussage]:checked').val()
            if (is_plan_ussage == "usage") {

                $("#div_planbooster").css("display", "block");
                $("#div_Speed").css("display", "none");

            } else {
                $("#div_planbooster").css("display", "none");
                $("#div_Speed").css("display", "block");
            }
            $("#show_onclickofradiooption").css("display", "block");

        }

        function showunlimiteddivbooster() {
            $("#tr_PlanBoosterpaidfoc").css("display", "block");
            $('input:radio[name=plan_paid_booster]').each(function () {
                $(this).prop('checked', false);
            });
           
            var is_plan_ussage = $('input[name=plan_ussage]:checked').val()
            if (is_plan_ussage == "usage") {
                $("#div_planbooster").css("display", "block");
                $("#div_Speed").css("display", "none");

            } else {
                $("#div_planbooster").css("display", "none");
                $("#div_Speed").css("display", "block");
            }
            $("#show_onclickofradiooption").css("display", "block");
            
            //$("#tr_PlanBoosterpaidfoc").css("display", "none");
        }

        function showusagediv() {
            $("#tr_PlanBoosterpaidfoc").css("display", "block");
            // alert("dsvdsv");
            $("#tbl_Planmigration").css("display", "none");
            $("#div_Speed").css("display", "none");
            $("#tabledisplay").css("display", "none");
            var is_plan_ussage = $('input[name=plan_ussage]:checked').val()
            if (is_plan_ussage == "usage") {

                $("#div_planbooster").css("display", "block");
                $("#div_Speed").css("display", "none");

            } else {
                $("#div_planbooster").css("display", "none");
                $("#div_Speed").css("display", "block");
            }
            $("#show_onclickofradiooption").css("display", "block");
            //  var is_speed = $('#ticketselectall').prop('checked').val();
            // alert(is_plan_ussage);
            // div_planbooster

        }
        function showboster_plandiv() {
            $("#tr_PlanBoosterpaidfoc").css("display", "block");
            $("#div_planbooster").css("display", "none");
            $("#tbl_Planmigration").css("display", "none");
            // alert("dsvdsv");
            $("#show_onclickofradiooption").css("display", "block");
            var is_plan_ussage = $('input[name=plan_ussage]:checked').val()
            if (is_plan_ussage == "speed") {
                $("#div_Speed").css("display", "block");
                $("#div_planbooster").css("display", "none");
            } else {
                $("#div_Speed").css("display", "none");
                $("#div_planbooster").css("display", "block");
            }
            $("#tabledisplay").css("display", "none");
        }