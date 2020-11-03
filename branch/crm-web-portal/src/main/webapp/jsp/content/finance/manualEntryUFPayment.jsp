<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<%@taglib uri="/WEB-INF/tlds/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/tlds/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/tlds/displaytag.tld" prefix="display"%>
<%@taglib uri="/WEB-INF/tlds/struts-html.tld" prefix="html"%>
<html>
<head>
</head>

<body>
<!----------------- Section ------------------------------->
<div id="section"  align="center">
  <html:form action="/upFrontPaymentRecovery"  method="post" styleId="idManualPaymentEntry">
  <html:hidden property="crmUpfrontPaymentPojo.createdBy" value="${sessionScope.userPojo.userId}"/>
  
    <div class="section">
      <h2>Upfront Payment Post Entry</h2>
      <div class="success_message" >
		<logic:messagesPresent message="true">
   		<html:messages id="message" message="true">
		<bean:write name="message" />
		</html:messages>
		</logic:messagesPresent>
	</div>     
   <div class="inner_section">
   <p><span class="error_message"><html:errors property="appError" /></span></p>
          <!-- left customerBasicInformation-->
          <div class="inner_left_lead  marginleft10 floatl"> 
              <p class="floatl " ><strong>Cheque Number<span class="error_message verticalalignTop">*</span></strong>
                <html:text styleClass="Lmstextbox" name="financeForm" property="crmUpfrontPaymentPojo.chequeNo" maxlength="6" onkeyup="javascript:ValidatenReplaceAlphanumeric(this)"/>
              </p>
              <p class="floatl marginleft30"><strong> Cheque Date<span class="error_message verticalalignTop">*</span></strong>
                <html:text styleClass="tcal tcalInput" name="financeForm" styleId="idChequeDate"  property="crmUpfrontPaymentPojo.displayChequeDate" readonly="true" />
              </p>
               
                
     <p class=" floatl marginleft30">
		<strong>Bank Name<span class="error_message verticalalignTop">*</span></strong>
		 <span class="LmsdropdownWithoutJs">
      			<html:select property="crmUpfrontPaymentPojo.bankId" name="financeForm" styleId="" indexed="">
             		<html:option value="0">Please Select </html:option>
             		<logic:notEmpty name="financeForm" property="bankList">
             		<html:optionsCollection name="financeForm" property="bankList"  label="categoryValue" value="categoryId" />
      				</logic:notEmpty>
      			</html:select>
     		</span>
		</p>
                <br class="clear" />
                  <br class="clear" />
              <p class="floatl clear" ><strong>Amount<span class="error_message verticalalignTop">*</span></strong>
                <html:text  styleClass="Lmstextbox" name="financeForm" property="crmUpfrontPaymentPojo.amount" maxlength="11" onclick="removeZeroFromNumber(this);" onfocus="removeZeroFromNumber(this);" onkeyup="javascript:ValidatenReplaceAlphanumericnDot(this)"/>
              </p>
              
              <p  class="floatl marginleft30">
               <strong>Entity Type<span class="error_message verticalalignTop">*</span></strong>
               <span class="LmsdropdownWithoutJs">
                	<html:select property="crmUpfrontPaymentPojo.entityName" name="financeForm" styleId="entityTypeCRF">
             			<html:option value="0">Please Select</html:option>
						<logic:notEmpty name="financeForm" property="entityTypeList">
             			<html:optionsCollection name="financeForm" property="entityTypeList" label="contentName" value="contentValue" />
						</logic:notEmpty>
         			</html:select>
                </span>
                 </p>
                 <p  class="floatl marginleft30">
               <strong>Business Partner<span class="error_message verticalalignTop">*</span></strong>
               <span class="LmsdropdownWithoutJs">
               <logic:notEmpty name="financeForm" property="partnerList" >
                	<html:select property="crmUpfrontPaymentPojo.partnerId" name="financeForm" styleId="partnerIdCRF">
             			<html:option value="0">Please Select</html:option>
						<logic:notEmpty name="financeForm" property="partnerList">
             			<html:optionsCollection name="financeForm" property="partnerList" label="partnerName" value="partnerId" />
						</logic:notEmpty>
         			</html:select>
         			</logic:notEmpty>
                </span>
                 </p>
                 <br class="clear" />
                  <br class="clear" />
                <div class="leadCrfView clear  " >
    			<span class="addbtn_role_group"><a href="javascript:addMoreCRFId()" class="grey_button_add" ><span>Add</span></a></span>
				 <div class="display_group" id="crfIdBlock">
      				<div class="stays-at-top">
					<span class="heading_text"> CAF Number<span class="error_message verticalalignTop">*</span>&nbsp;<input type="text" id="crfID"  class="Lmstextbox" maxlength="8" onblur="getCustomerDetails(this.value,'CRF_ID',false);"> </span>
      				</div>
     				<ul class="lead_group" >
						<div id="showID"></div>	
					</ul>
      				</div>
      				<font></font>
     	</div>
                
          </div>
          <!-- right customerBasicInformation-->
          <div class="floatr inner_right">
            <p class="buttonPlacement"> <a href="#" id="submit_UP" class="main_button marginleft10"><span>Submit</span></a> </p>
          </div>
          <br class="clear" />
        
        </div>
        <!-- validation form enteries --> 
        
        <!-- display table-->
        
      </div>
   
  </html:form>
</div>
</body>
</html>
