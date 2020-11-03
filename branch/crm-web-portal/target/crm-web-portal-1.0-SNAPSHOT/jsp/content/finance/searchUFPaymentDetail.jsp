<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<%@taglib uri="/WEB-INF/tlds/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/tlds/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/tlds/displaytag.tld" prefix="display"%>
<%@taglib uri="/WEB-INF/tlds/struts-html.tld" prefix="html"%>
<html>
<body>
<!----------------- Section ------------------------------->

<div id="section"  align="center">
  <html:form action="/upFrontPaymentRecovery"  method="post" styleId="idSearchUpfrontPayment">
    <div class="section">
      <h2>Search Upfront Payment Details</h2>
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
              <p class="floatl "><strong>From Date<span class="error_message verticalalignTop">*</span></strong>
                <html:text styleClass="tcal tcalInput" name="financeForm" property="fromDate" styleId="idUpfrontFromDate"  readonly="true" />
              </p>
              <p class="floatl marginleft30"><strong>To Date<span class="error_message verticalalignTop">*</span></strong>
                <html:text  styleClass="tcal tcalInput" name="financeForm" property="toDate" styleId="idUpfrontToDate"  readonly="true" />
              </p>
              <p class="floatl clear">
               <strong>Business Partner<span class="error_message verticalalignTop">*</span></strong>
               <span class="LmsdropdownWithoutJs">
               <logic:notEmpty name="financeForm" property="partnerList" >
                	<html:select property="crmUpfrontPaymentPojo.partnerId" name="financeForm" styleId="searchPartnerIdCRF">
             			<html:option value="0">Please Select</html:option>
             			<html:optionsCollection name="financeForm" property="partnerList" label="partnerName" value="partnerId" />
         			</html:select>
         			</logic:notEmpty>
                </span>
              </p>
              
          </div>
          <!-- right customerBasicInformation-->
          <div class="floatr inner_right">
            <p class="buttonPlacement"> <a href="#" id="search_UP" class="main_button marginleft10"><span>Search</span></a> </p>
          </div>
          <br class="clear" />
      
        </div>
        <!-- validation form enteries --> 
        
        <div class="marginleft10">
        <logic:notEmpty name="financeForm" property="crmUpfrontPaymentPojoList">
    	<display:table export="false" id="data"	name="${financeForm.crmUpfrontPaymentPojoList}" requestURI="upFrontPaymentRecovery.do?method=searchUpfrontPayment" class="dataTable" 
    			style="width:100%" pagesize="15">
			<display:setProperty name="paging.banner.placement" value="bottom" />
				<display:column  title="Sr.No.">
					<bean:write name="data_rowNum" />
				</display:column>
				<%-- <display:column property="upfrontId" title="System Reference-ID"/> --%>
				<display:column title="Cheque No.">
				<logic:empty name="data" property="chequeNo">
					-
				</logic:empty>
				<logic:notEmpty name="data" property="chequeNo">
					<a href="javascript:viewUPFrontDetailsPopUP('<bean:write name="data" property="upfrontId" />')" ><bean:write name="data" property="chequeNo"/></a>
				</logic:notEmpty>					
				</display:column>
				<display:column title="Cheque Date">
					<logic:empty name="data" property="chequeDate">
						-
					</logic:empty>
					<logic:notEmpty name="data" property="chequeDate">
						<bean:write name="crmRoles" property="toDate(${ data.chequeDate})" scope="session"/>
					</logic:notEmpty>
				</display:column>
				<display:column  title="Bank Name">
					<logic:equal name="data" property="bankId" value="0">
						-
					</logic:equal>
					<logic:notEqual name="data" property="bankId" value="0">
						<bean:write name="crmRoles" property="displayEnum(BANK,${ data.bankId})" scope="session" />
					</logic:notEqual>
				</display:column>
				
				<display:column property="amount" title="Paid Amount"/>
				<display:column title="Payment Date">
					<logic:empty name="data" property="createdTime">
						-
					</logic:empty>
					<logic:notEmpty name="data" property="createdTime">
						<bean:write name="crmRoles" property="toDate(${ data.createdTime})" scope="session"/>					
					</logic:notEmpty>
					
				</display:column>
				<%-- <display:column title="BP Name" >
					<logic:equal  name="data" property="partnerId" value="0">
						NA
					</logic:equal>
					<logic:notEqual  name="data" property="partnerId" value="0">
						<bean:write name="crmRoles" property="displayEnum(PartnerPojo,${ data.partnerId})" scope="session" />
					</logic:notEqual>
				</display:column>				
				<display:column title="BP Code">
				<a href="javascript:viewUPFrontDetailsPopUP('<bean:write name="data" property="upfrontId" />')" ><bean:write name="data" property="partnerId"/></a>
				</display:column> --%>
				<display:column  title="Created By" property="createdBy"/>
				<display:column  title="Created Time">
					<logic:empty name="data" property="createdTime">
						-
					</logic:empty>
					<logic:notEmpty name="data" property="createdTime">
						<bean:write name="crmRoles" property="xmlDate(${ data.createdTime})" scope="session"/>					
					</logic:notEmpty>
				</display:column>
			</display:table>
			</logic:notEmpty>
			<logic:empty name="financeForm" property="crmUpfrontPaymentPojoList">
					<b> <html:errors property="noRecordFound"/> </b>
			</logic:empty>
			</div>
      </div>
   <html:hidden name="financeForm" property="upFrontId" styleId="upFrontId"/>
  </html:form>
</div>
</body>
</html>
