<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<%@taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<%@taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<script src="dwr/engine.js" type="text/javascript"></script>
<script src="dwr/util.js" type="text/javascript"></script>
<script src="dwr/interface/crmDwr.js" type="text/javascript"></script>
<script type="text/javascript">
	$(document).ready(function() {
		$('#hideUnhideBouncingReason').addClass('hide1');
    });
</script>

<html:hidden name="financeForm" property="crfId" value="${financeForm.crfId}" styleId="uploadDocCRF" />
<div class="overlayDiv"></div>
<div class="modelPopupDiv" id="uploadDocPPId"></div>
<div class="loadingPopup hidden"></div>
<div id="section" align="center">
	<html:form action="/paymentPosting" styleId="paymentPosting">
	<html:hidden name="financeForm" property="crmPaymentDetailsPojo.customerRecordId" styleId="recordId" />
	<html:hidden name="financeForm" property="crmPaymentDetailsPojo.customerId" styleId="pp_customerId_hdn" />	
		<div class="section">
			<h2>Payment Posting</h2>
			<div class="inner_section">
				<p>
					<span class="error_message"> <html:errors
							property="appError" />
					</span> <span class="success_message"> <logic:messagesPresent
							message="true" property="appMessage">
							<html:messages id="msg" message="true" property="appMessage">
								<bean:write name="msg" />
							</html:messages>
						</logic:messagesPresent>
					</span>
				</p>
				<!-- validation form enteries -->
				<div class="relative">
					<div class="clear marginleft10 inner_left_lead floatl">
						<h4 class=" clear">Customer Details</h4>
						<logic:equal name="financeForm"
							property="crmPaymentDetailsPojo.paymentId" value="0">
							<html:hidden name="financeForm"
								property="crmPaymentDetailsPojo.status" value="A"
								/>
								<html:hidden name="financeForm"
								property="crmPaymentDetailsPojo.paymentStatus" value="R"
								/>
								<html:hidden name="financeForm" property="crmPaymentDetailsPojo.createdBy" value="${sessionScope.userPojo.userId}" />
						</logic:equal>

						<p class="floatl ">
							<strong> Customer ID<span class="error_message verticalalignTop">*</span></strong>
							<logic:equal name="financeForm"
								property="crmPaymentDetailsPojo.paymentId" value="0">
								<html:text name="financeForm" property="customerId" maxlength="15"
									styleId="pp_customerId" styleClass="Lmstextbox " 
									onblur="getCustomerDetails(this.value,'CUST_ID',true);" />
							</logic:equal>
						<font id="eitherError"></font>
						</p>

						<span class="floatl paddingTop10 marginleft15">
							<strong>&nbsp;</strong> OR
						</span>
						<p class="floatl marginleft15">
							<strong>CAF Number<span class="error_message verticalalignTop">*</span></strong>
							<logic:equal name="financeForm"
								property="crmPaymentDetailsPojo.paymentId" value="0">
								<html:text name="financeForm" property="crfId" maxlength="8"
									styleId="pp_crfId" styleClass="Lmstextbox textupper "
									 onblur="getCustomerDetails(this.value,'CRF_ID',true);" />
							</logic:equal>
							
						</p>
						<p class="floatl marginleft30">
							<strong>Installation Status</strong>
							<logic:equal name="financeForm"
								property="crmPaymentDetailsPojo.paymentId" value="0">
								<html:text name="financeForm"
									property="crmPaymentDetailsPojo.installationStatus"
									styleId="installationStatus" styleClass="Lmstextbox" readonly="true" />
								
							</logic:equal>
							

						</p>
						<p class="floatl marginleft30">
							<strong>Entity Type<span class="error_message verticalalignTop">*</span></strong> <span class="LmsdropdownWithoutJs">
								<logic:equal name="financeForm"
									property="crmPaymentDetailsPojo.paymentId" value="0">
									<html:select name="financeForm"
										property="crmPaymentDetailsPojo.entityType"
										styleId="pp_EntityType">
										<html:option value="0">Please Select</html:option>
										<logic:notEmpty name="financeForm" property="entityTypeList">
										<html:optionsCollection name="financeForm"
											property="entityTypeList" label="contentName"
											value="contentValue" />
											</logic:notEmpty>
									</html:select>
								</logic:equal> 
							</span>
						</p>
						<h4 class=" clear paddingTop10">Payment Details</h4>
						<p class="floatl  clear ">
							<strong>Mode of Payment<span class="error_message verticalalignTop">*</span></strong>
							<logic:equal name="financeForm"
								property="crmPaymentDetailsPojo.paymentId" value="0">
								<logic:iterate id="paymentModeList" name="financeForm"
									property="paymentModeList" indexId="ctr">
									<LABEL class="label_radio"
										for="ModePaymentPost${paymentModeList.contentValue}">
										<html:radio property="crmPaymentDetailsPojo.paymentMode"
											value="${paymentModeList.contentValue}"
											styleId="ModePaymentPost${paymentModeList.contentValue}">
												${paymentModeList.contentName}</html:radio>
									</LABEL>
								</logic:iterate>
							</logic:equal>
							
						</p>
						<p class="floatl marginleft101">
							<strong>Channel<span class="error_message verticalalignTop">*</span></strong>
							<logic:equal name="financeForm"
								property="crmPaymentDetailsPojo.paymentId" value="0">
								<span class="LmsdropdownWithoutJs"> <html:select
										name="financeForm"
										property="crmPaymentDetailsPojo.paymentChannel" styleId="paymentChannel">
										<logic:notEmpty name="financeForm" property="paymentChannelList">
										<html:option value="0">Please Select</html:option>
										<html:optionsCollection name="financeForm"
											property="paymentChannelList" label="contentName"
											value="contentValue" />
											</logic:notEmpty>
									</html:select>
								</span>
							</logic:equal>
							
						</p>
			<p class="floatl marginleft30"><strong> </strong><strong> </strong>
   			<a href="javascript:openUploadPopUp('${initParam.dmshost }/np-document-upload','FINANCE');" class="yellow_button">Upload Document</a>
						
						<div>
							<p class=" floatl clear">
								<strong>Payment Date<span class="error_message verticalalignTop">*</span></strong>
								<html:text property="paymentDate" styleClass="tcal tcalInput" styleId="paymentDate" readonly="true"></html:text>
									<font class="errorTextbox"></font>
							</p>
							<p class=" floatl marginleft30">
								<strong>Paid Amount<span class="error_message verticalalignTop">*</span></strong>
								<logic:equal name="financeForm"
									property="crmPaymentDetailsPojo.paymentId" value="0">
									<html:text name="financeForm"
										property="crmPaymentDetailsPojo.amount"
										styleId="pp_crmPaymentDetailsPojo.paidAmount"
										styleClass="Lmstextbox either" maxlength="11" onclick="removeZeroFromNumber(this);" onfocus="removeZeroFromNumber(this);" onkeyup="javascript:ValidatenReplaceAlphanumericnDot(this)"/>
								</logic:equal>
								
							</p>
							<p class=" floatl marginleft30">
								<strong>Case Status</strong> <span class="dropdownWithoutJs" id="dropdownHolder">
									<html:select name="financeForm" 
										property="crmPaymentDetailsPojo.caseStatus" styleId="caseStatus" onchange="return widthChange(this.value);">
										<html:option value="">Please Select</html:option>
										<logic:notEmpty name="financeForm" property="caseStausList">
										<html:optionsCollection name="financeForm" property="caseStausList" label="contentName" value="contentValue" />
											</logic:notEmpty>
									</html:select>
								</span>
							</p>
						</div>
						<div id="ModePaymentPostCashShow" class="hide1">
							<h4 class="paddingTop10 clear">Cash Payment</h4>
							<p class=" floatl ">
								<strong>Cash Receipt Number<span class="error_message verticalalignTop">*</span></strong>
								<html:text name="financeForm"
									property="crmPaymentDetailsPojo.receiptNo"
									styleId="pp_crmPaymentDetailsPojo.receiptNo"
									styleClass="Lmstextbox either" />
							</p>
						</div>

						<div id="ModePaymentPostDDShow" class="hide1">
							<h4 class="paddingTop10 clear" id="headingCheque"></h4>

							<p class=" floatl ">
								<strong id="numberCheque">Cheque Number<span class="error_message verticalalignTop">*</span></strong>

								<html:text name="financeForm"
									property="crmPaymentDetailsPojo.chequeDdNo"
									styleId="pp_crmPaymentDetailsPojo.chequeDdNo"
									styleClass="Lmstextbox either" maxlength="6" onkeyup="javascript:ValidatenReplaceAlphanumeric(this)"/>

							</p>
							<p class=" floatl marginleft30">
								<strong id="dateCheque">Cheque Date<span class="error_message verticalalignTop">*</span></strong>
								<html:text property="chequeDate" styleClass="tcal tcalInput" readonly="true" styleId="pp_chequeDateId"></html:text>
							</p>
							<p class=" floatl marginleft30">
								<strong>Bank Name<span class="error_message verticalalignTop">*</span></strong> <span class="LmsdropdownWithoutJs">
									<html:select styleClass=""
										styleId="pp_crmPaymentDetailsPojo.bankName"
										property="crmPaymentDetailsPojo.bankName">
										<html:option value="">Please Select</html:option>
										<logic:notEmpty name="financeForm" property="bankList">
										<html:optionsCollection name="financeForm" property="bankList"
											label="categoryValue" value="categoryId" />
										</logic:notEmpty>
									</html:select>
								</span>
							</p>

							<p class="floatl marginleft30">
								<strong>Realization Status<span class="error_message verticalalignTop">*</span></strong> <span
									class="LmsdropdownWithoutJs">
									 <html:select name="financeForm" styleId="realizationStatus" property="crmPaymentDetailsPojo.realzationStatus"  onchange="javascript:hideBouncingReason(this.value)">
										<html:option value="">Please Select</html:option>
										<logic:notEmpty name="financeForm" property="chequeStatusList">
										 <html:optionsCollection name="financeForm" property="chequeStatusList" label="contentName" value="contentValue"/>   
										</logic:notEmpty>
									</html:select>
								</span>
							</p>
							<p  class="floatl clear" />
							<div id ="hideUnhideBouncingReason" class="floatl clear">
							  <p  class="floatl clear">
   								 <strong>Bouncing Reason</strong>
     							<span class="dropdownWidthchange">
     								<html:select name="financeForm" property="crmPaymentDetailsPojo.bouncingReason" styleId="bouncingReason" onchange="return widthChangeBR(this.value);">
             							<html:option value="">Please Select</html:option>
										 <logic:notEmpty name="financeForm" property="chequeRejectionReasonList">
             								<html:optionsCollection name="financeForm" property="chequeRejectionReasonList" label="qrcSubSubCategory" value="qrcSubSubCategory"/>
			 							</logic:notEmpty>
			 						</html:select>
			 						<font class="errorTextbox"></font>
     							</span>
     						</p>
     						
     					<p class="floatl marginleft30"><strong> Bouncing Date<span class="error_message verticalalignTop">*</span></strong>
    						<html:text styleClass="tcal tcalInput" name="financeForm" property="bouncingDate" styleId="IDBouncingDate" readonly="true"></html:text>
    						<font class="errorTextbox"></font>
    					</p>
     						
							</div>
						</div>

						<div id="ModePaymentPostOnlineShow" class="hide1">
							<h4 class="paddingTop10 clear">Online Payment</h4>

							<p class=" floatl ">
								<strong>Transaction ID<span class="error_message verticalalignTop">*</span></strong>
								<html:text name="financeForm"
									property="crmPaymentDetailsPojo.transactionId"
									styleId="pp_crmPaymentDetailsPojo.transactionId"
									styleClass="Lmstextbox either" maxlength="15"/>
							</p>
						</div>
					</div>
					<div class="floatr inner_right">

						<p class="buttonPlacement">
						 <logic:equal name="crmRoles" property="available(create_fpp)" value="true" scope="session">
							<a href="#" id="submit_paymentPosting" class="main_button marginleft10"><span>Submit</span></a>
							</logic:equal>
						</p>
					</div>
					<br class="clear" />
					<div>
<!-- 
<logic:notEmpty name="financeForm" property="crmPaymentDetailsPojos">
			<display:table export="true" id="data"
				name="sessionScope.financeForm.crmPaymentDetailsPojos" requestURI="" class="dataTable"
				style="width:100%" pagesize="15">
					<display:setProperty name="paging.banner.placement" value="bottom" />
					<display:setProperty name="mymedia" value="true"></display:setProperty>
				<display:setProperty name="export.excel.filename" value="Users-Detail.xls" />
				<display:setProperty name="export.csv.filename" value="Users-Detail.csv" />
					<display:column  title="Sr.No.">
						<bean:write name="data_rowNum" />
					</display:column>
					<display:column  title="CRFId/CustomerId " >
					<bean:write name="financeForm" property="crfId"/>
					</display:column>
					<display:column  title="CRFId/CustomerId " >
					<bean:write name="financeForm" property="paymentId"/>
					</display:column>
					<display:column  title="Installation Status" property="installationStatus"/>
					<display:column  title="EntityType" property="entityType"/>
					<display:column  title="paymentMode" property="paymentMode"/>
					<display:column  title="paymentChannel" property="paymentChannel"/>
					<display:column  title="Payment Date" property="paymentDate"/>
					<display:column  title="PaymentAmount" property="amount"/>
					<logic:notEmpty name="financeForm" property="receiptNo">
					<display:column  title="Reciept No" property="receiptNo"/>
					</logic:notEmpty>
					<logic:notEmpty name="financeForm" property="bankName">
					<display:column  title="BankName" property="bankName"/>
					</logic:notEmpty>
					<logic:notEmpty name="financeForm" property="chequeDdNo">
					<display:column  title="ChequeNumber" property="chequeDdNo"/>
					</logic:notEmpty>
					<logic:notEmpty name="financeForm" property="chequeDate">
					<display:column  title="ChequeDate" property="chequeDate"/>
					</logic:notEmpty>
					<logic:notEmpty name="financeForm" property="transactionId">
					<display:column  title="TransactionId" property="transactionId"/>
					</logic:notEmpty>
				
					<display:column  title="Edit" property="Edit">
					<a href="javascript:editPaymentPost'<bean:write name="data" property="paymentId" />')">Edit</a>
					</display:column>
			</display:table>
			</logic:notEmpty> -->
</div>
				</div>
				<!-- validation form enteries -->
			</div>
		</div>



	</html:form>

</div>
