<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib uri="/WEB-INF/tlds/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/tlds/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/tlds/displaytag.tld" prefix="display"%>
<%@taglib uri="/WEB-INF/tlds/struts-html.tld" prefix="html"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%--    code from qrcMenu.jsp    --%>
<link href="css/colorbox.css" rel="stylesheet" />
<script src="javascript/jquery.colorbox.js"></script>
<script>
	$( document ).ready(function(){
		$(".iframe2").colorbox({iframe:true, width:"750px", height:"320px"});
	});
</script>
<div class="loadingPopup hidden"></div>
<div id="section">
    <div class="section">
<logic:notEmpty name="qrcForm" property="custDetailsPojo.customerId">
  <jsp:include page="crfCustomerDescription.jsp"></jsp:include>

</logic:notEmpty>
      <div class="inner_section ">
        <div class="inner_left_lead floatl  qrcLeft">
        <bean:define id="activeMenu" value="device"></bean:define>
         <%@include file="qrcMenu.jsp"%>
        </div>
        <div class=" floatl manageGISRight  qrcRight">
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
       <div class="relative inner_left_lead">
       <html:form action="/manageQrc">
        <logic:notEmpty name="qrcForm" property="networkConfigurationsPojo">
	        <html:hidden name="qrcForm"property="networkConfigurationsPojo.serviceModel" value="${qrcForm.networkConfigurationsPojo.serviceModel}" styleId="IDQRChiddenOntRgMduDone"/>
	        <html:hidden name="qrcForm"property="networkConfigurationsPojo.recordId" value="${qrcForm.networkConfigurationsPojo.recordId}" styleId="IDQRChiddenNetworkRecordId"/>
	       <div class="floatl">
	         <h4>Change CPE Ownership / MAC Address</h4>
	         <logic:notEmpty name="qrcForm" property="orderDetailsPojo">
	         <c:choose>
	         <c:when test="${(qrcForm.custDetailsPojo.product eq 'BB') or (qrcForm.custDetailsPojo.product eq 'RF')}">
	    			 <p class=" floatl">
	     			<strong>CPE Ownership</strong><br/>
	     			<html:radio property="orderDetailsPojo.cpeStatus"  name="qrcForm" styleId="IDDeviceChangeCPENR" value="NR" > <bean:message bundle="userProp" key="brand.name"/>&nbsp;Recommended</html:radio>
	     			<html:radio property="orderDetailsPojo.cpeStatus"  name="qrcForm" styleId="IDDeviceChangeCPECO" value="CO" > Customer Owned</html:radio>	     
	     			<%--	<div class="floatr inner_right">
							<p class="buttonPlacement">
							<logic:equal name="crmRoles" property="available(update_qrc_dm)" value="true" scope="session">
	     					<a href="javascript:saveCPEWithMAC('${ qrcForm.orderDetailsPojo.cpeStatus}');"  class="main_button_multiple">Save</a>
	     					</logic:equal>
							</p>
						</div> --%>
     			</c:when>
     			<c:otherwise>
     				<p class="floatl clear"><strong>CPE Ownership</strong><br/>
	     			<LABEL class="label_radio" for="WiFiDeviceStatusNextraProvidedEdit">
	     			<html:radio property="orderDetailsPojo.cpeStatus"  name="qrcForm" styleId="IDDeviceChangeCPENR" value="NR" > <bean:message bundle="userProp" key="brand.name"/>&nbsp;Recommended</html:radio>
	     			</LABEL>
     				</p>
     				<p class="floatl marginleft30"><strong>MAC Faulty </strong><br/>
					     <LABEL class="label_radio" for="macFaultyYes">
					     	<html:radio name="qrcForm" property="macFaulty" styleId="macFaultyYes" value="true"> Yes</html:radio>
					     </LABEL> 
					     <LABEL class="label_radio" for="macFaultyNo">
					     	<html:radio name="qrcForm" property="macFaulty" styleId="macFaultyNo" value="false"> No</html:radio>
					     </LABEL>
					     <font></font>
	  				 </p>
     			</c:otherwise>
     			</c:choose>
     		</logic:notEmpty>
	         <p class=" floatl clear" ><strong>Old <logic:equal name="qrcForm" property="custDetailsPojo.product" value="EOC">Primary</logic:equal> MAC Address</strong>
	         	<html:text property="networkConfigurationsPojo.currentCpeMacId" name="qrcForm" styleClass="textbox gray_text"  readonly="true" styleId="IDqrcOldPriMacId" />
	         </p>
	         <p class=" floatl marginleft30" ><strong>New <logic:equal name="qrcForm" property="custDetailsPojo.product" value="EOC">Primary&nbsp;</logic:equal>MAC Address</strong>
	         	<html:text property="newPrimaryMacId" name="qrcForm" styleClass="textbox gray_text"  styleId="IDqrcNewPriMacId" maxlength="14" onkeypress='javascript:autoFormatMacID(event,this);' onkeyup="$( '#IDqrcNewPriMacId' ).next( 'font' ).hide();"/><font></font>
	         </p>
	         <logic:equal name="qrcForm" property="custDetailsPojo.product" value="EOC">
	         <br/>
	         <p class=" floatl clear" ><strong>Old Secondary MAC Address</strong>
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
             	<a href="javascript:saveMacQRC('${qrcForm.custDetailsPojo.product}');" class="yellow_button" id='IDsaveMacQrcDeviceChange'>Save MAC</a><font></font>
             </logic:equal>
             </p>
             </logic:equal>
             <logic:notEmpty name="qrcForm" property="orderDetailsPojo">
              <c:if test="${(qrcForm.custDetailsPojo.product eq 'BB') or (qrcForm.custDetailsPojo.product eq 'RF')}">
             <p class="floatl marginleft30" style="margin-top: 36px;">	
             <logic:equal name="crmRoles" property="available(update_qrc_dm)" value="true" scope="session">				
	     	  <a href="javascript:saveCPEWithMAC('${qrcForm.orderDetailsPojo.cpeStatus}');" class="yellow_button">Save CPE / MAC</a>     	
	     	  </logic:equal>				
			 </p>
             </c:if>
             </logic:notEmpty>
             </div>
	         <div class="floatl">
        	 <br/><br/>
           	 <h4>
              <c:if test="${(qrcForm.custDetailsPojo.product eq 'BB') }"> Change Option 82</c:if>
              <c:if test="${(qrcForm.custDetailsPojo.product ne 'BB')}">Change NAS Port ID</c:if>
              </h4>  
              <c:if test="${(qrcForm.custDetailsPojo.product ne 'BB')}">   
                <p class="floatl clear">
                  <strong>Master Name</strong>
                  <html:text name="qrcForm" property="partnerNetworkConfigPojo.masterName" styleClass="textbox gray_text" disabled="true"></html:text>
                </p>
                <p class="floatl marginleft30">
                  <strong>Pool Name</strong>
                  <html:text name="qrcForm" property="partnerNetworkConfigPojo.poolName" styleClass="textbox gray_text" disabled="true"></html:text>
                </p>
              </c:if>
             <p class=" floatl clear">
             <c:choose>
                <c:when test="${(qrcForm.custDetailsPojo.product eq 'BB')}">
	             <strong>Old Option 82</strong>
	        	 	<html:text property="networkConfigurationsPojo.option82" name="qrcForm" styleClass="textbox gray_text"  styleId="IDqrcOldOption82" readonly="true"/>
	      		</c:when>
	      		<c:otherwise>
	           		<strong>Old NAS Port ID</strong>
	           		<html:text property="partnerNetworkConfigPojo.nasPortId" name="qrcForm" styleClass="textbox gray_text"  styleId="IDqrcOldOption82" readonly="true"/>
	      	    </c:otherwise>
	      	    </c:choose>
      	   </p>
      	   <p class="floatl marginleft30">
      	   <c:if test="${(qrcForm.custDetailsPojo.product eq 'BB')}">
	           <strong>New Option 82</strong>
	           	<html:text name="qrcForm" property="newOption82" styleId="IDqrcNewOption82" styleClass="textbox gray_text" maxlength="50" ></html:text><font></font>
	          </c:if>
	      <logic:equal name="crmRoles" property="available(update_qrc_dm)" value="true" scope="session">
	      <c:if test="${(qrcForm.custDetailsPojo.product ne 'BB')}">
		          <strong>New NAS Port ID</strong>
			      <html:hidden name="qrcForm" property="newOption82" styleId="IDqrcNewOption82" styleClass="textbox gray_text "  ></html:hidden>
		          <a href= 'manageQrc.do?method=changeDevicePopupPage&npId=<bean:write name='qrcForm' property='custDetailsPojo.npId' /> 'class="iframe2"> 
		          <html:text name="qrcForm" property="showDivValue" styleId="IDqrcNewOption82show" styleClass="textbox gray_text " readonly="true"  ></html:text><font></font></a>
	          </c:if>
          </logic:equal>
        </p>
       <p class=" floatl marginleft30"><strong>&nbsp;</strong>
	       <logic:equal name="crmRoles" property="available(update_qrc_dm)" value="true" scope="session">
	       <a href="javascript:saveOption82QRC('${qrcForm.custDetailsPojo.product}','Q')"  class="yellow_button" id='IDsaveOption82QrcDeviceChange'>
	     <c:choose>
          <c:when test="${(qrcForm.custDetailsPojo.product eq 'BB')}">
	       Save Option82
	       </c:when>
	       <c:otherwise>
	         Save NAS Port ID
	       </c:otherwise>
	     </c:choose>
	        </a>
	       </logic:equal> 
	       <font></font>
       </p>
       </div>
      </logic:notEmpty>
      
      <logic:empty name="qrcForm" property="networkConfigurationsPojo">
      No network configurations done.
     </logic:empty>
     <br/>
     
     
     <p class="floatl clear">
     	<strong>Ticket ID</strong><html:text property="srTicketNo" name="qrcForm" maxlength="20" styleId="deviceChangeSRT" styleClass="textbox"></html:text>
     </p>
     <p class="floatl clear" id="deviceRemarks">
	     <strong> Remarks<span class="error_message verticalalignTop">*</span></strong>
	     <html:textarea property="remarksPojo.remarks" name="qrcForm" styleId="deviceChangeRemarks" styleClass="LmsRemarkstextarea"></html:textarea><font></font>
      </p>
      <logic:empty name="qrcForm" property="orderDetailsPojo">
     	 No CPE Ownership configurations.
      </logic:empty>
      <br class="clear" />
      <br class="clear" />
     </html:form>
     </div>
     <br class="clear" />
    </div>
    </div>
   <p class="clear"></p>
       