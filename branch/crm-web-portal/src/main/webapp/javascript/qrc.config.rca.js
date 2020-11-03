/*This JS is dedicatedly works QRC RCA configurations:
1. Function CrmQrcRcaJsPojo is exact replica of CrmQrcRcaPojo, any changes in this on server side, will require to change here too.
2. index, toDisplay, and changed properties are introduced here to facilitate UI rendering and these does not affect server programming.
3. Final list has been submitted to server through DWR, if changes require.
*/

var qrcCommonPojoList = [];
var propArray = [];
//var qrcRcaPojoList = [];

window.onload = function() {
	qrcCommonPojoList = [];
	fillQrcRcaPageCategoryies();
	fillQrcRcaPageServiceNames();
};

function QrcConfigCommonPojo(index, changed) {
	this.index = index;
	this.toDisplay = "";//preparePojoRow(index, recordID, rca, status);
	this.changed = changed;
}

//function CrmQrcRcaJsPojo(index, rcaId, qrcCategoryId, serviceName, rca,
//		createdBy, lastModifiedBy, status, changed) {
//	this.index = index;
//	this.rcaId = rcaId;
//	this.qrcCategoryId = qrcCategoryId;
//	this.serviceName = serviceName;
//	this.rca = rca;
//	this.createdBy = createdBy;
//	this.lastModifiedBy = lastModifiedBy;
//	this.status = status;
//	this.toDisplay = preparePojoRow(index, rcaId, rca, status);
//	this.changed = changed;
//}

function preparePojoRow(index,inObj,pageParam){
	if( pageParam == "qrcRca" ){
		return preparePojoRowRCA(index, inObj["rcaId"], inObj["rca"], inObj["status"]);
	}
}

function preparePojoRowRCA(index, rcaId, rca, status) {
	var rcaStr = "rcaStr";
	var rcaStatusActive = "rcaStatusActive";
	var rcaStatusInactive = "rcaStatusInactive";
	var rowStr = "<input type='hidden' id='oldRca_" + index	+ "' value='" + rca + "'/><input type='hidden' id='oldStatus_" + index	+ "' value='" + status + "'/>"
			+ "<span><input id='enableRca_" + index	+ "' type='checkbox' value='true' onchange='javascript:enableCreateQrcRca( " + index + " )'></span>" 
			+ "<span class='marginleft13' id='row_"	+ index	+ "'>" 
			+ "<input readOnly='true' type='text' id='rcaStr_"	+ index	+ "' class='reasonTextbox gray_text' value='"	+ rca + "' onblur='javascript:setPropertyToPojo(\""	+ rcaStr + "\"," + index + ")'/>"
			+ "</span><span class='marginleft13'><input disabled='true' type='radio' id='rcaStatusActive_"	+ index	+ "' class='marginright5 gray_text' value='A' " + (status == 'A' ? 'checked="checked"' : '')	+ " onclick='javascript:setPropertyToPojo(\"" + rcaStatusActive	+ "\","	+ index	+ ")' />Active</span>"
			+ "<span class='marginleft13'><input disabled='true' type='radio' id='rcaStatusInactive_" + index + "' class='marginright5 gray_text' value='I' "	+ (status == 'A' ? '' : 'checked="checked"') + "  onclick='javascript:setPropertyToPojo(\""	+ rcaStatusInactive	+ "\","	+ index	+ ")'/>Inactive</span> ";
	if( rcaId == 0 ){
		rowStr = rowStr +"<span class='marginleft13'><a href='javascript:removeRcaElement("
						+ index
						+ ")' class='close'><img src='images/bg/delete.png' align='absmiddle' /></a></span>";
	}
	//console.log(rowStr);		
	return rowStr;
}

function fillQrcRcaPageCategoryies() {
	if (null != document.getElementById("categories")) {
		var categories = document.getElementById("categories");

		crmDwr.getQrcRcaCategoryies(function(list) {
			fillCategories(categories, list);
		});

		function fillCategories(categories, qrcCategoriesPojos) {
			if (categories != null) {
				if (qrcCategoriesPojos != null) {
					dwr.util.removeAllOptions(categories);
					dwr.util.addOptions(categories, [ {
						"value" : "0",
						"label" : "Please Select"
					} ], "value", "label");
					dwr.util.addOptions(categories, qrcCategoriesPojos,
							"qrcCategoryId", "qrcCategory");
				} else {
					removeList(categories);
				}
			}
		}
	}
}

function fillQrcRcaPageServiceNames() {
	if (null != document.getElementById("serviceName")) {
		var serviceName = document.getElementById("serviceName");

		crmDwr.getServiceNames(function(list) {
			fillServiceNames(serviceName, list);
		});

		function fillServiceNames(serviceName, serviceNames) {
			if (serviceName != null) {
				if (serviceNames != null) {
					dwr.util.removeAllOptions(serviceName);
					dwr.util.addOptions(serviceName, [ {
						"value" : "0",
						"label" : "Please Select"
					} ], "value", "label");
					dwr.util.addOptions(serviceName, serviceNames,
							"contentValue", "contentName");
				} else {
					removeList(serviceName);
				}
			}
		}
	}
}

function displayQrcRcaConfigurations(displayID, checkID, selectedValue) {
	$('#serviceName').next('font').hide();
	$('#categories').next('font').hide();
	if ('0' != selectedValue) {
		var displayElement = document.getElementById(displayID);
		var checkValue = document.getElementById(checkID).value;

		if ('0' != checkValue) {
			if (null != displayElement) {
				if (checkID.trim() == 'categories') {
					crmDwr.getQrcRcaPojos(checkValue, selectedValue, function(
							list) {
						fillQrcCommonPojo(displayElement, list, "getList","qrcRca");
					});
				} else {
					crmDwr.getQrcRcaPojos(selectedValue, checkValue, function(
							list) {
						fillQrcCommonPojo(displayElement, list, "getList","qrcRca");
					});
				}

			}
		} else {
			if (checkID.trim() == 'categories') {
				$('#categories').next('font').show().html(
						"Please select Category").addClass('errorTextbox');
			} else {
				$('#serviceName').next('font').show().html(
						"Please select Service Name").addClass('errorTextbox');
			}
		}
	} else {
		if (checkID.trim() == 'categories') {
			$('#serviceName').next('font').show().html(
					"Please select Service Name").addClass('errorTextbox');

		} else {
			$('#categories').next('font').show().html("Please select Category")
					.addClass('errorTextbox');
		}
	}
}

function fillQrcCommonPojo(displayElement, list, param, pageParam) {
	if (displayElement != null) {
		if (param == "getList") {
			qrcCommonPojoList = [];
			dwr.util.removeAllOptions(displayElement);
		}
		if (list != null) {
			dwr.util.removeAllOptions(displayElement);

			for ( var i = 0; i < list.length; i++) {
				var serviceSidePojo = list[i];
				propArray = Object.getOwnPropertyNames(serviceSidePojo);
				var qrcConfigCommonPojo = new QrcConfigCommonPojo( i , "N");
				for( var j = 0 ; j < propArray.length ; j++ ){
					var property = propArray[j];
					qrcConfigCommonPojo[property] = serviceSidePojo[property];
				}
				qrcConfigCommonPojo["toDisplay"] = preparePojoRow(i,qrcConfigCommonPojo,pageParam);
				if (param != "addNewElement") {
					qrcCommonPojoList.push(qrcConfigCommonPojo);
				}
				var rowClass = i % 2 == 0 ? 'first' : 'second';
				dwr.util.addOptions(displayElement, [ qrcConfigCommonPojo ],
						"toDisplay", {
							escapeHtml : false
						});
				$("#row_" + i).parent('li').addClass(rowClass);
				
			}

		} else {
			removeList(displayElement);
		}
	}
}

//function fillQrcRca(displayElement, qrcRcaPojos, param) {
//	if (displayElement != null) {
//		if (param == "getList") {
//			qrcRcaPojoList = [];
//			dwr.util.removeAllOptions(displayElement);
//		}
//		if (qrcRcaPojos != null) {
//			dwr.util.removeAllOptions(displayElement);
//
//			for ( var i = 0; i < qrcRcaPojos.length; i++) {
//				var qrcRcaPojo = qrcRcaPojos[i];
//				var qrcRcaJsPojo = new CrmQrcRcaJsPojo(i, qrcRcaPojo.rcaId,
//						qrcRcaPojo.qrcCategoryId, qrcRcaPojo.serviceName,
//						qrcRcaPojo.rca, qrcRcaPojo.createdBy,
//						qrcRcaPojo.lastModifiedBy, qrcRcaPojo.status, "N");
//				if (param != "addNewElement") {
//					qrcRcaPojoList.push(qrcRcaJsPojo);
//				}
//				var rowClass = i % 2 == 0 ? 'first' : 'second';
//				dwr.util.addOptions(displayElement, [ qrcRcaJsPojo ],
//						"toDisplay", {
//							escapeHtml : false
//						});
//				$("#rca_" + i).parent('li').addClass(rowClass);
//				
//			}
//
//		} else {
//			removeList(displayElement);
//		}
//	}
//}

function addNewRcaPojo() {
	$('#serviceName').next('font').hide();
	$('#categories').next('font').hide();
	var categorieID = document.getElementById("categories").value;
	var serviceName = document.getElementById("serviceName").value;
	
	if( "0" == categorieID){
		$('#categories').next('font').show().html("Please select Category").addClass('errorTextbox');
	}
	if("0" == serviceName){
		$('#serviceName').next('font').show().html("Please select Service Name").addClass('errorTextbox');
	}
	else
	{
		var displayElement = document.getElementById('qrcRcaConfigurations');
		var qrcConfigCommonPojo = new QrcConfigCommonPojo(qrcCommonPojoList.length,"N");//0,categorieID, serviceName, "", "", "", "A",
		for( var j = 0 ; j < propArray.length ; j++ ){
			qrcConfigCommonPojo[propArray[j]] = "";
		}
		//qrcCategoryId, serviceName, rca, createdBy, lastModifiedBy, status"
		qrcConfigCommonPojo["qrcCategoryId"] = categorieID;
		qrcConfigCommonPojo["serviceName"] = serviceName;
		qrcConfigCommonPojo["rca"] = "";
		qrcConfigCommonPojo["createdBy"] = "";
		qrcConfigCommonPojo["lastModifiedBy"] = "";
		qrcConfigCommonPojo["status"] = "A";
		
		//qrcRcaPojoList[qrcRcaPojoList.length] = qrcRcaJsPojo;
		qrcCommonPojoList.splice(0,0,qrcConfigCommonPojo);
		fillQrcCommonPojo(displayElement, qrcCommonPojoList, "addNewElement","qrcRca");
	}
}

function removeRcaElement(inIndex) {
	var displayElement = document.getElementById('qrcRcaConfigurations');
	qrcCommonPojoList.splice(inIndex, 1);
	fillQrcCommonPojo(displayElement, qrcCommonPojoList, "addNewElement","qrcRca");
}

function setPropertyToPojo(inProperty, inIndex) {
	var propValue = document.getElementById(inProperty + "_" + inIndex).value;
	var oldRca = document.getElementById( "oldRca_" + inIndex).value;
	var oldStatus = document.getElementById("oldStatus_" + inIndex).value;
	if (inProperty == "rcaStr" && "" != propValue) {
		if (qrcCommonPojoList[inIndex].rca != propValue.trim()) {
			qrcCommonPojoList[inIndex].rca = propValue;
			oldRca != propValue ? qrcCommonPojoList[inIndex].changed = "Y" : qrcCommonPojoList[inIndex].changed = "N";
		}
	} else if (inProperty == "rcaStatusActive" && "" != propValue) {
		if (qrcCommonPojoList[inIndex].status != propValue.trim()) {
			qrcCommonPojoList[inIndex].status = propValue;
			oldStatus != propValue ? qrcCommonPojoList[inIndex].changed = "Y" : qrcCommonPojoList[inIndex].changed = "N";
			document.getElementById("rcaStatusInactive_" + inIndex).checked = false;
		}
	} else if (inProperty == "rcaStatusInactive" && "" != propValue) {
		if (qrcCommonPojoList[inIndex].status != propValue.trim()) {
			qrcCommonPojoList[inIndex].status = propValue;
			oldStatus != propValue ? qrcCommonPojoList[inIndex].changed = "Y" : qrcCommonPojoList[inIndex].changed = "N";
			document.getElementById("rcaStatusActive_" + inIndex).checked = false;
		}
	}
}

function configureQrcRca() {
	var changeArray = getChangedConfiguations();
	if (changeArray.length > 0) {
		var reply = confirm("Do you want to change RCA configurations ?");
		if (reply) {
			crmDwr.setChangedConfigurations(changeArray, function(success) {
				if (success) {
					document.forms[1].action = "qrcConfig.do?method=configRCA";
					document.forms[1].submit();
				}
			});
		}
	}
	else
	{
		alert("Kindly, check\n1. None of the row selected.\n2. Selected row RCA left as an empty string.\n3. Selected row have no change to previous.");
	}
}

function getChangedConfiguations() {
	var changeArray = [];
	if(qrcCommonPojoList.length > 0){
		for ( var i = 0; i < qrcCommonPojoList.length; i++) {
			if (qrcCommonPojoList[i].changed == 'Y' && document.getElementById("enableRca_" + i).checked && qrcCommonPojoList[i].rca != "") {
				changeArray.push(qrcCommonPojoList[i]);
			}
		}
	}
	else{
		alert("Kindly provide data");
	}
	return changeArray;
}

function enableCreateQrcRca( inIndex ){
	var enableFields = document.getElementById("enableRca_" + inIndex).checked ? false : true ;
	document.getElementById("rcaStr_" + inIndex ).readOnly = enableFields;
	enableFields ? $('#rcaStr_' + inIndex).addClass('gray_text') : $('#rcaStr_' + inIndex).removeClass('gray_text')  ;
	document.getElementById("rcaStatusActive_" + inIndex ).disabled = enableFields;
	document.getElementById("rcaStatusInactive_" + inIndex ).disabled = enableFields;
}