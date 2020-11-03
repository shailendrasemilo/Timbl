<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib uri="/WEB-INF/tlds/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/tlds/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/tlds/displaytag.tld" prefix="display"%>
<%@taglib uri="/WEB-INF/tlds/struts-html.tld" prefix="html"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<script type="text/javascript">
	//show n hide content on radio GIS master
	$(document).ready(function() {
		var selectedDiv = document.getElementsByName("showDivValue")[0].value;
		console.log("divselectvalue:"+selectedDiv);
		$('#DIVstaticIP_ADDREMOVEACC').addClass('hide1');
		$('#DIVwiring_ADDREMOVEACC').addClass('hide1');
		$('#DIVstaticIPCharges_ADDREMOVEACC').addClass('hide1');
		$('#DIVsaveStaticIP').addClass('hide1');
		$('#DIVsaveWiring').addClass('hide1');
		$('#DIVsaveStaticIPCharges').addClass('hide1');
		$('#DIVticketIDRemarksACC').addClass('hidden');
		if (null != selectedDiv||selectedDiv!="") {
			if (selectedDiv == "staticIP") {
				$('#DIVstaticIP_ADDREMOVEACC').removeClass('hide1');
				$('#DIVsaveStaticIP').removeClass('hide1');
				$('.addStaticIp').addClass('active');
				$('.addWiringIp').removeClass('active');
				$('.addStaticIPCharges').removeClass('active');
				$('#DIVticketIDRemarksACC').removeClass('hidden');
				
			}
			if (selectedDiv == "wiring") {
				$('#DIVwiring_ADDREMOVEACC').removeClass('hide1');
				$('#DIVsaveWiring').removeClass('hide1');
				$('.addStaticIp').removeClass('active');
				$('.addWiringIp').addClass('active');
				$('.addStaticIPCharges').removeClass('active');
				$('#DIVticketIDRemarksACC').removeClass('hidden');
			}
			if (selectedDiv == "staticIPCharges") {
				$('#DIVstaticIPCharges_ADDREMOVEACC').removeClass('hide1');
				$('#DIVsaveStaticIPCharges').removeClass('hide1');
				$('.addStaticIp').removeClass('active');
				$('.addWiringIp').removeClass('active');
				$('.addStaticIPCharges').addClass('active');
				$('#DIVticketIDRemarksACC').removeClass('hidden');
			}
		}
    });
</script>
</head>
<div class="loadingPopup hidden"></div>
<div id="section">
    <div class="section">
<logic:notEmpty name="qrcForm" property="custDetailsPojo.customerId">
  <jsp:include page="crfCustomerDescription.jsp"></jsp:include>
	
</logic:notEmpty>
	     <div class="inner_section ">
	        <div class="inner_left_lead floatl  qrcLeft">
	          <bean:define id="activeMenu" value="addRemoveAccessories"></bean:define>
	          <%@include file="qrcMenu.jsp"%>
	        </div>
        <%--    end code from qrcMenu.jsp    --%>
        <div class=" floatl manageGISRight  qrcRight">
	          <div class="success_message" style="top:15px;">
		          <logic:messagesPresent message="true" property="appMessage">
		          <html:messages id="message" message="true">
		          <bean:write name="message" />
		          </html:messages>
	          </logic:messagesPresent>
	          </div>
	          <div class="error_message" id="error" style="top:15px;">
	            <html:errors />
	          </div>
          <div class="relative inner_left_lead">
          	<html:form action="/manageQrc" styleId="formADDACC">
          <%
              int j = 0;
          %>
       <html:hidden name="qrcForm" property="showDivValue" styleId="IDshowDivValueADDACC" value="${qrcForm.showDivValue}" />       
<c:if test="${qrcForm.showDivValue eq 'staticIP'}">
	<div id="DIVstaticIP_ADDREMOVEACC" class="hide1 ">
		<div class="topHeadingLinks">
		</div>
		<h4>Add Static IP
          <logic:equal name="crmRoles" property="available(create_qrc_stipchg,update_qrc_stipchg)" value="true" scope="session">

          <a href="javascript:divSelectAddAccessories('staticIPCharges');" class="yellow_button floatr margintop7 addStaticIPCharges" style="margin-left: 15px;">Static IP Charges</a>
                      <logic:equal name="crmRoles" property="available(create_qrc_wrchg,update_qrcwrchg)" value="true" scope="session">
            <a href="javascript:divSelectAddAccessories('wiring');" class="yellow_button floatr margintop7 addWiringIp" style="margin-left: 15px;">Add Wiring Charges</a>
            </logic:equal>
          </logic:equal>
        </h4>
		<div class="floatl">
			<div id="role_group_view1" class="relative margintop30"	style="width: 745px;">
				<span class="addbtn_role_group">
					<a href="javascript:addRowStaticIPADDACC();" id="addRowRCAConfiguration" class="grey_button_add"><span>Add</span></a>
				</span>
				<div class="display_group">
					<div class="stays-at-top">
						<span class="heading_text marginleft30">Static IP</span> 
						<%-- <span class="heading_text marginleft230">Amount</span>--%>
					</div>
					<logic:empty name="qrcForm"	property="crmCustAssetDetailsPojos">
						<span class="error_message"> &nbsp;Please click on 'Add' button to add Static IP. </span>
					</logic:empty>
					<ul class="add_parameter_group">
						<logic:notEmpty name="qrcForm" property="crmCustAssetDetailsPojos">
							<logic:iterate id="crmCustAssetDetailsPojos" name="qrcForm"	property="crmCustAssetDetailsPojos" indexId="ctr">
								<li class="${ctr%2 eq '1'?'first':'second'}" style="position: relative">
									<!-- modification allowed --> 
									<logic:notEqual	name="crmCustAssetDetailsPojos"	property="assetDetailsId" value="0">
										<html:hidden name="crmCustAssetDetailsPojos" property="assetDetailsId" indexed="true" />
									</logic:notEqual> <logic:equal name="crmCustAssetDetailsPojos" property="assetDetailsId" value="0">
										<html:hidden name="crmCustAssetDetailsPojos" property="customerRecordId" indexed="true"	value="${qrcForm.custDetailsPojo.recordId}" />
									</logic:equal> <pre style="display: inline;">${ ctr+1}.</pre> 
										<html:text name="crmCustAssetDetailsPojos" property="categoryValue"	styleClass="QRCtextbox checkGreyText" indexed="true" onkeyup="javascript:validateIPAddrs(this)"></html:text><font></font>
										<%-- <html:text name="crmCustAssetDetailsPojos" property="categoryAmount" onkeyup="javascript:ValidatenReplaceAlphanumericnDot(this);" onclick="removeZeroFromNumber(this);" onfocus="removeZeroFromNumber(this);" styleClass="Lmstextbox checkGreyText" indexed="true"></html:text>
										<font></font> --%>
										<span class="padding"> 
											<html:radio	name="crmCustAssetDetailsPojos"	styleClass="checkGreyText" property="status" value="A" indexed="true">Active</html:radio> 
											<html:radio	name="crmCustAssetDetailsPojos"	styleClass="checkGreyText" property="status" value="I" indexed="true">Inactive</html:radio>
										</span> 
									<logic:equal name="crmCustAssetDetailsPojos" property="assetDetailsId" value="0">
										<span class="marginleft13"> 
											<html:link href="javascript:removeRowStaticIPADDACC(${ ctr})" styleClass="close">
													<img src="images/bg/delete.png" align="absmiddle" title="delete" />
											</html:link>
										</span>
									</logic:equal> <% j = ctr + 1; %>
							</logic:iterate>
						</logic:notEmpty>
					</ul>
				</div>
			</div>
			<br class="clear" />
		</div>
	</div>
</c:if>
		<logic:equal name="qrcForm" property="showDivValue" value="wiring">
			<logic:notEmpty name="qrcForm" property="crmCustAssetDetailsPojo">
				<div id="DIVwiring_ADDREMOVEACC" class="hide1 ">
					<div class="topHeadingLinks">
					</div>
					<h4>Wiring Charges 
                      <logic:equal name="crmRoles" property="available(create_qrc_stipchg,update_qrc_stipchg)" value="true" scope="session">
                      <a href="javascript:divSelectAddAccessories('staticIPCharges');" class="yellow_button floatr margintop7 addStaticIPCharges" style="margin-left: 15px;">Static IP Charges</a>&nbsp;&nbsp;
                         </logic:equal>
                         <logic:equal name="crmRoles" property="available(create_qrc_stip,update_qrc_stip)" value="true" scope="session">
                        <a href="javascript:divSelectAddAccessories('staticIP');" class="yellow_button floatr margintop7 addStaticIp" style="margin-left: 15px;">Add Static IP</a>
                     </logic:equal>
                    </h4>
					<p class="floatl margintop20">
						<html:hidden name="qrcForm" property="crmCustAssetDetailsPojo.customerRecordId"	value="${qrcForm.custDetailsPojo.recordId}" />
						<strong>Wiring Charges:<sup class="req">*</sup></strong>
						<html:text name="qrcForm" property="crmCustAssetDetailsPojo.categoryAmount" styleClass="textbox" styleId="IDWiringPointsADDACC" onkeyup="javascript:ValidatenReplaceAlphanumericnDot(this);" onclick="removeZeroFromNumber(this);" onfocus="removeZeroFromNumber(this);"></html:text>
						<font></font>
					</p>
					<br class="clear" />
				</div>
			</logic:notEmpty>
		</logic:equal>
		<logic:equal name="qrcForm" property="showDivValue" value="staticIPCharges">
			<logic:notEmpty name="qrcForm" property="crmCustAssetDetailsPojo">
				<div id="DIVstaticIPCharges_ADDREMOVEACC" class="hide1 ">
					<div class="topHeadingLinks">
					</div>
					<h4>Static IP Charges
                      <logic:equal name="crmRoles" property="available(create_qrc_stip,update_qrc_stip)" value="true" scope="session">
                        <a href="javascript:divSelectAddAccessories('staticIP');" class="yellow_button floatr margintop7 addStaticIp" style="margin-left: 15px;">Add Static IP</a>&nbsp;&nbsp;
                        </logic:equal>
                        <logic:equal name="crmRoles" property="available(create_qrc_wrchg,update_qrcwrchg)" value="true" scope="session">
                        <a href="javascript:divSelectAddAccessories('wiring');" class="yellow_button floatr margintop7 addWiringIp" style="margin-left: 15px;">Add Wiring Charges</a>
                        
                      </logic:equal>
                    </h4>
					<p class="floatl clear">
				<strong>Static IP Charges<sup class="req">*</sup></strong>
				<html:text name="qrcForm" property="crmCustAssetDetailsPojo.categoryAmount" onkeyup="javascript:ValidatenReplaceAlphanumericnDot(this);" onclick="removeZeroFromNumber(this);" onfocus="removeZeroFromNumber(this);" styleClass="Lmstextbox checkGreyText" styleId="staticIPChargesAmount"></html:text>
				<font></font>
				<html:hidden name="qrcForm" property="crmCustAssetDetailsPojo.customerRecordId"	value="${qrcForm.custDetailsPojo.recordId}" />
			   </p>
					<br class="clear" />
				</div>
			</logic:notEmpty>
		</logic:equal>
		
		<br class="clear" />
		<div id="DIVticketIDRemarksACC">
			<p class="floatl clear">
				<strong>Ticket ID</strong>
				<html:text name="qrcForm" property="srTicketNo" maxlength="20" value="" styleId="IDTicketAccesoriesPage" styleClass="textbox"/>
				<font></font>
			</p>
			<p class="floatl clear">
				<strong> Remarks<sup class="req">*</sup></strong>
				<html:textarea name="qrcForm" styleClass="LmsRemarkstextarea" property="remarksPojo.remarks" styleId="IDRemarksAccesoriesPage"></html:textarea>
				<font></font>
			</p>
			<br class="clear" />
		</div>
		<logic:equal name="crmRoles" property="available(create_qrc_stip,update_qrc_stip)" value="true" scope="session">
		    <div class="floatr inner_right" id="DIVsaveStaticIP">
		    	<a href="javascript:saveAccessories('staticIP',<%=j%>);" class="main_button_multiple" id="IDsaveStaticADDACC">Submit</a><font></font>
	        </div>
	     </logic:equal>
	     <logic:equal name="crmRoles" property="available(create_qrc_wrchg,update_qrc_wrchg)" value="true" scope="session">
	        <div class="floatr inner_right" id="DIVsaveWiring">
	        	<a href="javascript:saveAccessories('wiring',<%=j%>);" class="main_button_multiple" id="IDsaveStaticADDACC">Submit</a><font></font>
	        </div>
	     </logic:equal>
	     <logic:equal name="crmRoles" property="available(create_qrc_stipchg,update_qrc_stipchg)" value="true" scope="session">
	        <div class="floatr inner_right" id="DIVsaveStaticIPCharges">
	        	<a href="javascript:saveAccessories('staticIPCharges',<%=j%>);" class="main_button_multiple" id="IDsaveStaticIPChargesADDACC">Submit</a><font></font>
	        </div>
        </logic:equal>
        <p class="clear"></p>

        </html:form>
        </div>
        <br class="clear" />
        </div>
      </div>
        <p class="clear"></p>