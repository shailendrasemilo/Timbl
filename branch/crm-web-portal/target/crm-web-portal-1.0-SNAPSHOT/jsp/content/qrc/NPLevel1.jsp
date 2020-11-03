<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page import="org.apache.struts.Globals"%>
<%@ page import="org.apache.struts.taglib.html.Constants"%>
<%@taglib uri="/WEB-INF/tlds/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/tlds/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/tlds/displaytag.tld" prefix="display"%>
<%@taglib uri="/WEB-INF/tlds/struts-html.tld" prefix="html"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div class="floatl" id="npDiv">
	<p class=" floatl clear">

		<logic:equal name="qrcForm" property="shiftingWorkflowPojo.product" value="BB"> Change Option 82</logic:equal>
		<logic:notEqual name="qrcForm" property="shiftingWorkflowPojo.product" value="BB">Change NAS Port ID</logic:notEqual>
		<logic:equal value="BB" name="qrcForm" property="shiftingWorkflowPojo.product">
			<c:if
				test="${qrcForm.shiftingWorkflowPojo.npId eq qrcForm.shiftingWorkflowPojo.previousNpId && qrcForm.shiftingWorkflowPojo.product eq qrcForm.shiftingWorkflowPojo.previousNetwork} ">
				<span class=""><strong>CPE Available</strong></span>
				<span class=""> <html:radio property="shiftingWorkflowPojo.cpeAvailable" value="Y" /> Yes <html:radio property="shiftingWorkflowPojo.cpeAvailable"
						value="N" onclick="" />No
				</span>
			</c:if>
	</p>

	<p class=" floatl clear" id="option82ID">
		<span class=""><strong>Option 82</strong></span>
		<html:text name="qrcForm" property="shiftingWorkflowPojo.option82" styleId="option82Ids" styleClass="Lmstextbox" />
	</p>
	<font style="top: 149px;"></font>
	</logic:equal>
	<font style="top: 211px;"></font>
	<logic:equal property="shiftingWorkflowPojo.product" name="qrcForm" value="EOC">
		<p class="floatl clear">
			<span class="" title="Click on textbox for Nassport"> <strong>Nas Port-ID</strong> <html:text name="qrcForm" property="nassPortId"
					styleId="IDnasportID" readonly="true" styleClass="Lmstextbox" onclick="checkNetworkConfigDetails();" /><font class="errorRemarks"
				style="top: 46px; left: 181px;"></font> <html:hidden name="qrcForm" property="shiftingWorkflowPojo.npId" styleId="hiddenPartnerId" /> <html:hidden
					name="qrcForm" property="shiftingWorkflowPojo.product" styleId="hiddenProduct" value="EOC" /> <html:hidden name="qrcForm"
					property="shiftingWorkflowPojo.option82" value="${qrcForm.networkConfigurationsPojo.option82}" styleId="option82Id" styleClass="Lmstextbox" /> <html:hidden
					property="networkConfigurationsPojo.serviceModel" name="qrcForm" value="ftth" styleId="hiddenOntRgMduDone" />
				<p class="marginleft30 floatl paddingTop10">
					<br /> <a href="javascript:checkNetworkConfigDetails();" class="link">Click here for Network Inventory details</a><font id="errorNetworkConfig"></font>
				</p>
			</span>
	</logic:equal>
	<br class="clear" />
</div>
