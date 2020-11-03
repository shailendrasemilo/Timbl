<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page import="org.apache.struts.Globals"%> 
<%@ page import="org.apache.struts.taglib.html.Constants"%>
<%@taglib uri="/WEB-INF/tlds/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/tlds/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/tlds/displaytag.tld" prefix="display"%>
<%@taglib uri="/WEB-INF/tlds/struts-html.tld" prefix="html"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<script type="text/javascript">
	$( document ).ready( function(){
		var param = $( 'input[name="paramValue"]' ).val();
		if(param != "Initiate"){
			var oldCpe = $( 'input[name="oldCpe"]' ).val();
			if ( oldCpe == "NR" ) {
				$( '#CPEChargesnSave' ).removeClass( 'hide1' );
				$( '#CPEChargesQRC' ).addClass( 'hide1' );
			}
			else if ( oldCpe == "CO" ) {
				$( '#CPEChargesnSave' ).removeClass( 'hide1' );
				$( '#CPEChargesQRC' ).removeClass( 'hide1' );
			}
			else {
				$( '#CPEChargesnSave' ).addClass( 'hide1' );
				$( '#CPEChargesQRC' ).addClass( 'hide1' );
			}
			var paymentModeVal = $( '#paymentModeQRC' ).val();
			if ( paymentModeVal == 'Q' ) {
				$( '#chequeQRC' ).removeClass( 'hide1' );
				$( '#bankNameQRC' ).removeClass( 'hide1' );
				$( '#chequeQRC input, #bankNameQRC' ).attr( 'disabled', false );
				$( '#"cashQRC", #ddQRC, #onlinePaymentQRC' ).addClass( 'hide1' );
				$( '#"cashQRC" input, #ddQRC input, #onlinePaymentQRC input' ).attr( 'disabled', true );
			}
			else if ( paymentModeVal == 'C' ) {
				$( '#cashQRC' ).removeClass( 'hide1' );
				$( '#cashQRC input' ).attr( 'disabled', false );
				$( '#chequeQRC, #ddCPE, #onlinePaymentQRC,#bankNameQRC' ).addClass( 'hide1' );
				$( '#chequeQRC input, #ddQRC input, #onlinePaymentQRC input,#bankNameQRC' ).attr( 'disabled', true );
			}
			else {
				$( '#chequeQRC, #cashQRC, #cashQRC, #onlinePaymentQRC,#bankNameQRC' ).addClass( 'hide1' );
				$( '#chequeQRC input, #cashQRC input, #ddQRC input, #onlinePaymentQRC input,#bankNameQRC' ).attr( 'disabled', true );
			}
		}
	} );
</script>
<div class="loadingPopup hidden"></div>
<div id="section">
<div class="modelPopupDiv" id="uploadDocPPId"></div>
<div class="section">
  <h2>Shifting Workflow&nbsp;-&nbsp;&nbsp;<a style="color: #DEAE00;"><bean:write name='qrcForm' property='customerId'/></a></h2>
<div class="inner_section ">
<div class="inner_left_lead floatl  qrcLeft">
</div>
<div class=" floatl manageGISRight  qrcRight">
	<div class="success_message">
		<logic:messagesPresent message="true">
			<html:messages id="message" message="true">
				<bean:write name="message" />
			</html:messages>
		</logic:messagesPresent>
	</div>
	<div class="error_message" id="errors">
		<html:errors />
	</div>
	<h4>Installation Partner Stage</h4>
	<div class="relative inner_left_lead">
		<html:form action="/shiftingWorkflow" method="post" styleId="spStage">
		<html:hidden name="qrcForm" property="shiftingWorkflowPojo.workflowId" value="${qrcForm.shiftingWorkflowPojo.workflowId}" styleId="workflowId"/>
		<html:hidden name="qrcForm" property="shiftingWorkflowPojo.workflowStage" value="${qrcForm.shiftingWorkflowPojo.workflowStage}" styleId="workflowStage"/>
		<html:hidden name="qrcForm" property="shiftingWorkflowPojo.customerId" value="${qrcForm.shiftingWorkflowPojo.customerId}" styleId="customerId"/>
		<html:hidden name="qrcForm" property="shiftingWorkflowPojo.shiftingType" value="${qrcForm.shiftingWorkflowPojo.shiftingType}" styleId="shiftingType"/>
		<!--  <input type = "text" name="param" value="workflow" styleId="paramValue"/>-->
		  <div class="floatl clear">
			<div class="floatl clear"><p>
				<logic:notEmpty name="qrcForm" property="orderDetailsPojo">
				<c:choose> 
				<c:when test="${(qrcForm.custDetailsPojo.product eq 'BB') or (qrcForm.custDetailsPojo.product eq 'RF') }">
				
						<div class="floatl clear"><br /> 
						<html:hidden name="qrcForm" property="oldCpe" styleId="oldCpeStatus" />
						<html:hidden name="qrcForm" property="paramValue" styleId="paramValue" />
	     					<h4>Change CPE Status</h4>
	     					<p class="floatl">
	     						<strong>CPE Ownership</strong><br/>
	     							<html:radio property="orderDetailsPojo.cpeStatus"  name="qrcForm" styleId="IDDeviceChangeCPENR" value="NR" onclick="javascript:showPayment(this.value);"> <bean:message bundle="userProp" key="brand.name"/>&nbsp;Recommended</html:radio>
	    							<html:radio property="orderDetailsPojo.cpeStatus"  name="qrcForm" styleId="IDDeviceChangeCPECO" value="CO" onclick="javascript:showPayment(this.value);"> Customer Owned</html:radio>
							</p>
						</div>
    		 	</c:when>
    		 	<c:otherwise>
     				<p class="floatl clear"><strong>CPE Ownership</strong><br/>
	     				<LABEL class="label_radio" for="WiFiDeviceStatusNextraProvidedEdit">
	     					<html:radio property="orderDetailsPojo.cpeStatus"  name="qrcForm" styleId="IDDeviceChangeCPENR" value="NR" > <bean:message bundle="userProp" key="brand.name"/>&nbsp;Recommended</html:radio>
	     				</LABEL>
    		 		</p>
     			</c:otherwise>
				</c:choose>
    		 </logic:notEmpty></p>
    	 </div>
		 <p class="clear" />
    	 <div class="hide1 floatl clear" id="CPEChargesnSave">
    	  <div class="hide1 floatl clear" id="CPEChargesQRC">
			<h4>Nextra Telesolutions Payment</h4>
			<p class="floatl">
			<html:hidden property="crmPaymentDetailsPojo.entityType" name="qrcForm" value="E"/>
			<html:hidden property="crmPaymentDetailsPojo.serviceType" name="qrcForm" value="${qrcForm.custDetailsPojo.serviceType}"/>
			<html:hidden property="crmPaymentDetailsPojo.status" name="qrcForm" value="A"/>
			<html:hidden property="crmPaymentDetailsPojo.installationStatus" name="qrcForm" value="PreIns"/>
			<html:hidden property="miniTelesolutionAmount" name="qrcForm" value="${qrcForm.miniTelesolutionAmount}" styleId="miniTelesolutionAmount"/>
			<p class="floatl clear"><strong>Amount</strong>
				<html:text property="crmPaymentDetailsPojo.amount" name="qrcForm" styleClass="Lmstextbox" maxlength="10" styleId="amountId" onclick="removeZeroFromNumber(this);" onfocus="removeZeroFromNumber(this);" onkeyup="javascript:ValidatenReplaceAlphanumericnDot(this);"></html:text>
				<font class="errorTextbox hidden">Please enter Amount</font>
			</p>
			<p class="floatl marginleft30">
				<strong>Payment Mode</strong>
	  			<span class="LmsdropdownWithoutJs">
	  				<html:select property="crmPaymentDetailsPojo.paymentMode" name="qrcForm" styleId="paymentModeQRC">
	        		 <html:option value="">Payment Mode</html:option>
	         		 <html:optionsCollection name="qrcForm" property="paymentModes" label="contentName" value="contentValue" />
	     			</html:select>
	     			<font class="errorTextbox hidden">Please select Payment Mode</font>
	  			</span>
			</p>
			<p class="floatl marginleft15 hide1" id="cashQRC"><strong>Receipt No.</strong>
				<html:text styleClass="Lmstextbox"  property="crmPaymentDetailsPojo.receiptNo" name="qrcForm" maxlength="8" styleId="telesolutionReceiptNoId"></html:text>
			</p>
			<p class="floatl marginleft15 hide1" id="onlinePaymentQRC"><strong>Transaction ID</strong>
				<html:text styleClass="Lmstextbox"  property="crmPaymentDetailsPojo.transactionId" name="qrcForm" maxlength="45"></html:text>
			</p>
			<div class="hide1" id="chequeQRC">
				<p class="floatl clear">
					<strong> Cheque/DD No.</strong>
	  				<html:text styleClass="Lmstextbox"  property="crmPaymentDetailsPojo.chequeDdNo" name="qrcForm" maxlength="6" onkeyup="javascript:ValidatenReplaceAlphanumeric(this);"></html:text>	
				</p>
		 		<p class="floatl marginleft15 ">
					<strong> Bank Name</strong>
					<span class="LmsdropdownWithoutJs">
						<html:select property="crmPaymentDetailsPojo.bankName" name="qrcForm" styleId="bankNameQRC">
							<html:option value="0">Select Bank</html:option>
							<html:optionsCollection name="qrcForm" property="bankList" label="categoryValue" value="categoryId" />
						</html:select>
					</span>
				</p>
				<p class="floatl clear">
		 			<strong> Bank Branch</strong>
	 					<html:text styleClass="Lmstextbox"  property="crmPaymentDetailsPojo.bankBranch" name="qrcForm" maxlength="45"></html:text>
				</p>
				<p class="floatl marginleft15">
					<span class="floatl "><strong> Cheque/DD Date</strong>
		  				<html:text styleClass="tcal tcalInput"  property="crmPaymentDetailsPojo.displayChequeDate" name="qrcForm" styleId="teleCommChequeDate"></html:text>
		  					<font class="errorTextbox"></font>
					</span>
				</p>
				<p class="marginleft15 floatl">
					<strong> City</strong>
						<html:text styleId="telecommCityId" styleClass="Lmstextbox" name="qrcForm" property="crmPaymentDetailsPojo.bankCity" onkeyup="replaceOtherThanAlphaSpace(this.id);" onblur="replaceOtherThanAlphaSpace(this.id);"></html:text>
				</p>
	   
			</div>
			</p>
		</div>
		
	 	</div>
	 </div>
	 <logic:notEmpty name="qrcForm" property="networkConfigurationsPojo">
    	<div class="floatl clear">
	       <br/><br/>
	         <h4>Change MAC Address</h4>
	         <p class=" floatl" ><strong>Old <logic:equal name="qrcForm" property="custDetailsPojo.product" value="EOC">Primary</logic:equal> MAC Address</strong>
	         	<html:text property="networkConfigurationsPojo.currentCpeMacId" name="qrcForm" styleClass="textbox gray_text"  readonly="true" styleId="IDqrcOldPriMacId" />
	         </p>
	         <p class=" floatl marginleft30" ><strong>New <logic:equal name="qrcForm" property="custDetailsPojo.product" value="EOC">Primary</logic:equal> MAC Address</strong>
	         	<html:text property="newPrimaryMacId" name="qrcForm" styleClass="textbox gray_text" maxlength="14"  styleId="IDqrcNewPriMacId" onkeypress='javascript:autoFormatMacID(event,this);'/><font></font>
	         </p>
	         <logic:equal name="qrcForm" property="custDetailsPojo.product" value="EOC">
	         	<br/>
	         	<p class=" floatl" ><strong>Old Secondary MAC Address</strong>
	         		<html:text property="networkConfigurationsPojo.currentSlaveMacId" name="qrcForm" styleClass="textbox gray_text"  styleId="IDqrcOldSecMacId" readonly="true"/>
	         	</p>
            	 <p class=" floatl marginleft30" ><strong>New Secondary MAC Address</strong>
	             	<span class="LmsdropdownWithoutJs LmsdropdownWithoutJsExt">
	             		<html:select property="newSecondaryMacId" name="qrcForm" styleId="IDqrcNewSecMacId" onchange="fillDataPrimaryMacAddr(this.value,'IDqrcNewPriMacId')">
	             			<html:option value="">Select MAC Address</html:option>
	             				<logic:notEmpty  name="qrcForm" property="secondaryMACAddrList">
	             					<html:options name="qrcForm" property="secondaryMACAddrList" />
	             				</logic:notEmpty>
	            			 </html:select>
	            			 <font></font>
             		</span>   
             	</p>
             	<p class= "floatl marginleft30"><strong>&nbsp;</strong>
             	<logic:equal name="crmRoles" property="available(update_qrc_dm)" value="true" scope="session">
             		<a href="javascript:saveMacWORKFLOW('${qrcForm.custDetailsPojo.product}','workflow','<bean:write name="qrcForm" property="shiftingWorkflowPojo.workflowId"/>','<bean:write name="qrcForm" property="shiftingWorkflowPojo.workflowStage"/>','<bean:write name="qrcForm" property="shiftingWorkflowPojo.customerId"/>','<bean:write name="qrcForm" property="shiftingWorkflowPojo.shiftingType"/>');" class="yellow_button" id='IDsaveMacQrcDeviceChange'>Save MAC</a><font></font>
             	</logic:equal>
             	</p>
             </logic:equal>
             <logic:notEqual name="qrcForm" property="custDetailsPojo.product" value="EOC">
             	<p class= "floatl marginleft30"><strong>&nbsp;</strong>
             	<logic:equal name="crmRoles" property="available(update_qrc_dm)" value="true" scope="session">
             		<a href="javascript:saveMacWithCPEWORKFLOW('${qrcForm.custDetailsPojo.product}','workflow','<bean:write name="qrcForm" property="shiftingWorkflowPojo.workflowId"/>','<bean:write name="qrcForm" property="shiftingWorkflowPojo.workflowStage"/>','<bean:write name="qrcForm" property="shiftingWorkflowPojo.customerId"/>','<bean:write name="qrcForm" property="shiftingWorkflowPojo.shiftingType"/>','${ qrcForm.orderDetailsPojo.cpeStatus}');" class="yellow_button" id='IDsaveMacQrcDeviceChange'>Save CPE/MAC</a><font></font>
             	</logic:equal>
             	</p>
             </logic:notEqual>
		</div>
		
		</logic:notEmpty>
		<logic:empty name="qrcForm" property="networkConfigurationsPojo">
      		No network configurations done.
     	</logic:empty>
		<div class="floatl clear">
       			
        			<h4>Wiring Charges</h4>
					<p class="floatl margintop20">
		 				<html:hidden name="qrcForm" property="crmCustAssetDetailsPojo.customerRecordId" value="${qrcForm.custDetailsPojo.recordId}"/>
		 				<strong>Wiring Charges</strong>
		 				<html:text name="qrcForm" property="crmCustAssetDetailsPojo.categoryAmount" styleClass="textbox" styleId="IDWiringChargesADD" onkeyup="javascript:ValidatenReplaceAlphanumericnDot(this);" onclick="removeZeroFromNumber(this);" onfocus="removeZeroFromNumber(this);"></html:text><font></font>
		 			</p>
					<p class= "floatl margintop20 marginleft30"><strong>&nbsp;</strong>
					<logic:equal name="crmRoles" property="available(update_qrc_wrchg)" value="true" scope="session">
	        		<a href="javascript:saveAccessoriesWorkflow('wiring','0','workflow','<bean:write name="qrcForm" property="shiftingWorkflowPojo.workflowId"/>','<bean:write name="qrcForm" property="shiftingWorkflowPojo.workflowStage"/>','<bean:write name="qrcForm" property="shiftingWorkflowPojo.customerId"/>','<bean:write name="qrcForm" property="shiftingWorkflowPojo.shiftingType"/>');" class="yellow_button" id="IDsaveStaticADD">Apply Charges</a><font></font>
	        		</logic:equal>
	        	</p>
		</div>
		<p class="floatl clear">
	  		<strong> Remarks <span class="error_message verticalalignTop">*</span></strong>
				<html:textarea property="remarksPojo.remarks" name="qrcForm" styleClass="LmsRemarkstextarea" styleId="ifrRemarks"></html:textarea>
				<span class="hidden"><font class="errorRemarks" style="top:98px;">Please enter Remarks between [2-4000].</font></span>
					<font class="errorRemarks hidden" style="top:98px;">Please enter Remarks.</font>
					
		</p>
		<br class="clear" />
			<div class="floatr inner_right">
				<p class="buttonPlacement">
				<logic:equal name="crmRoles" property="available(update_qrc_shifting)" value="true" scope="session">
					<a href="javascript:forwardToNextBin('${qrcForm.shiftingWorkflowPojo.workflowId}','${qrcForm.shiftingWorkflowPojo.shiftingType}','${qrcForm.shiftingWorkflowPojo.customerId}');" id="forward_bin" class="main_button ">
					<span>
					<logic:equal name="qrcForm" property="shiftingWorkflowPojo.shiftingType" value="Within Premises">
					Close
					</logic:equal>
					<logic:equal name="qrcForm" property="shiftingWorkflowPojo.shiftingType" value="Outside Premises">
					Forward
					</logic:equal>
					</span></a>
					</logic:equal> 
				</p>
				<br class="clear" />
				<br class="clear" />
			</div>
			<br class="clear" />
			<br class="clear" />
		</html:form>
	<br class="clear" />
	</div>
</div>
<p class="clear"/>
<div class="LmsTable marginRight10">
	 			 <h4>Remarks Details <span class="lmsMinusImage floatr"></span></h4>
    			 <div class="viewLmsTable margintop10 viewLmsLeadTable">
				<iframe src="logAction.do?method=getRemarks&mappingId=${qrcForm.shiftingWorkflowPojo.workflowId}" scrolling="no" frameborder="0" id="frame"
    	style="border: none; overflow: hidden;width:100%; height: 500px;" allowTransparency="true">
    </iframe>
				</div>
   			 </div>
			 
			 <br class="clear" />
			</div>
		</div>
	</div>
