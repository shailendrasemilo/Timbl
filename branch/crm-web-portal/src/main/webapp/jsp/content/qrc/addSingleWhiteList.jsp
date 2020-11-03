<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib uri="/WEB-INF/tlds/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/tlds/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/tlds/displaytag.tld" prefix="display"%>
<%@taglib uri="/WEB-INF/tlds/struts-html.tld" prefix="html"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%--    code from qrcMenu.jsp    --%>
<div class="loadingPopup hidden"></div>
<div id="section">
<div class="section">
<logic:notEmpty name="qrcForm" property="custDetailsPojo.customerId">
  <jsp:include page="crfCustomerDescription.jsp"></jsp:include>

</logic:notEmpty>
<div class="inner_section ">
<div class="inner_left_lead floatl  qrcLeft">
<bean:define id="activeMenu" value="exceptionList"></bean:define>
 <%@include file="qrcMenu.jsp"%>
</div>
      <%--    end code from qrcMenu.jsp    --%>
<div class=" floatl manageGISRight  qrcRight">
        <html:form action="/manageQrc" styleId="singleWhitelistForm">
          <div class="success_message">
           
          <logic:messagesPresent message="true">
	          <html:messages id="message" message="true">
	           <bean:write name="message" />
	          </html:messages>
          </logic:messagesPresent>
         </div>
         <div class="error_message" id="error">
           <html:errors />
         </div>
           <logic:equal value="A" property="custDetailsPojo.status" name="qrcForm">
           	<h4>Add to Barring Exception List</h4>
           </logic:equal>
           <logic:equal value="B" property="custDetailsPojo.status" name="qrcForm">
          	 <h4>Add to UnBarred Exception List</h4>
           </logic:equal>
           <c:if test="${ (qrcForm.custDetailsPojo.status ne 'A') and (qrcForm.custDetailsPojo.status ne 'B') }">
          	 <h4>Add to Exception List</h4>
           </c:if>
          <div class="relative inner_left_lead">
          <div class="floatl">
          <p class="floatl">
	          <strong>Customer ID</strong>
	          <html:text property="custDetailsPojo.customerId" name="qrcForm" readonly="true" styleClass="textbox"></html:text>
          </p>
          <c:if test="${ qrcForm.custDetailsPojo.status ne 'A' and qrcForm.custDetailsPojo.status ne 'B' }">
	          <p class="floatl marginleft30">
		          <strong>Exception Type</strong> 
		          <label class="label_radio" for="customerBarring"> 
		          <html:radio property="crmQrcWhitelistPojo.whitelistType" name="qrcForm" value="BARRED" styleId="customerBarring"></html:radio> Barring
		          </label> 
		          <label class="label_radio" for="customerUnbarring">
		           <html:radio property="crmQrcWhitelistPojo.whitelistType" name="qrcForm" value="UNBARRED" styleId="customerUnbarring"></html:radio> Unbarring
		         </label>
	           </p>
           </c:if>
           
           <p class="floatl clear">
	           <strong> Reason<sup class="req">*</sup></strong> 
	           <span class="dropdownWithoutJs"> 
	           <html:select property="crmQrcWhitelistPojo.reason" name="qrcForm">
	           <html:option value="0">Please Select</html:option>
	           <html:optionsCollection name="qrcForm" property="crmRcaReasonPojos" value="categoryId" label="categoryValue" />
	           </html:select> <font class="errorTextbox hidden">Please select reason.</font>
	           </span>
           </p>
           
           <p class="floatl marginleft30">
	           <strong> End Date<sup class="req">*</sup></strong>
	          <html:text property="endDate" name="qrcForm" styleClass="tcal" styleId="displayEndDate"></html:text>
	          <%-- value="${ empty qrcForm.crmQrcWhitelistPojo.endDate ? '' : displayEndDate }" --%>
	          <font class="errorTextbox hidden">Please select end date.</font>
          </p>
          
          <p class="floatl clear">
	          <strong> Remarks<sup class="req">*</sup></strong>
	          <html:textarea property="remarksPojo.remarks" name="qrcForm" styleClass="LmsRemarkstextarea" styleId="bubRemarks"></html:textarea>
	          <font class="errorRemarks hidden">Please enter remarks.</font>
          </p>
          
         <logic:equal value="Remove" name="qrcForm" property="removeOption">                
			 <p style="" class="floatl clear">                	
	        	 <strong style="color: red;">Customer already in ${ qrcForm.custDetailsPojo.status eq 'A' ? 'Barring' : (qrcForm.custDetailsPojo.status eq 'B' ? 'Unbarring' : '') } Exception List</strong>
	         </p>
			 <p style="" class="floatl marginleft30">
	        	 <a href="#" class="yellow_button" id="remove_whitelist">Remove</a>
	         </p>
        </logic:equal>
        <br class="clear" />
        </div>
        <br class="clear" />
        <div class="floatr inner_right">
        <p class="buttonPlacement">
	        <logic:equal name="crmRoles" property="available(create_qrc_el,update_qrc_el)" value="true" scope="session">
	        <a href="#" id="submit_logQRCBarring" class="main_button"><span>Submit</span></a>
	        </logic:equal>
	        </p>
	       </div>
	       </div>
	      </html:form>
	      </div>
     <p class="clear"></p>
      <!-- end -->
     <p class="clear"></p>