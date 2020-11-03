<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<%@taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@taglib uri="/WEB-INF/tlds/displaytag.tld" prefix="display"%>
<script type="text/javascript">
	$( document ).ready( function(){
		var last_valid_selection = new Array();
		var functionalBinArr = document.getElementById( "functionalBinID" );
		if ( null != functionalBinArr ) {
			for ( var i = 0; i < functionalBinArr.options.length; i++ ) {

				if ( functionalBinArr.options[ i ].selected ) {
					last_valid_selection[ i - 1 ] = functionalBinArr.options[ i ].value;
				}
			}
		}
		/* $('select[name="functionalBinArray"]').change(function(event) {
		if ($(this).val().length > 4) {
		 alert('You can choose at most 4 functional bins.');
		$(this).val(last_valid_selection);
		} else {
		 last_valid_selection = $(this).val();
		}
		}); */
	} );
</script>
<!----------------- Section ------------------------------->
<div id="section" align="center">
  <html:form action="/register" styleId="createUser" styleClass="createUser">
    <logic:empty name="loginForm" property="crmUserPojo">
      <div class="section">
        <h2>Create User - Registration</h2>
        <span class="error_message"><html:errors property="appError" /></span> <span class="success_message"> <logic:messagesPresent message="true"
            property="appMessage">
            <html:messages id="msg" message="true" property="appMessage">
              <bean:write name="msg" />
            </html:messages>
          </logic:messagesPresent>
        </span>
        <div class="inner_section">
          <div class="inner_left floatl createUserLeft">
            <div class="floatl clear">
              <p class="floatl">
                <b>First Name <sup class="req">*</sup></b>
                <html:text property="firstName" styleId="firstName" maxlength="30" styleClass="showTextbox" />
                <html:errors property="firstName" />
              </p>
              <p class="floatl clear">
                <b>Last Name <sup class="req">*</sup></b>
                <html:text property="lastName" styleId="lastName" maxlength="30" styleClass="showTextbox" />
                <html:errors property="lastName" />
              </p>
              <p class="clear floatl">
                <b>Email ID <sup class="req">*</sup></b>
                <html:text property="emailId" styleId="emailId" styleClass="textbox" />
                <html:errors property="emailId" />
              </p>
              <p class="floatl clear">
                <b>Password Expiry (In days) <sup class="req">*</sup></b>
                <html:text property="passwordExpiry" styleId="passwordExpiry" value="3" maxlength="3" styleClass="textbox"  onkeyup="javascript:ValidatenReplaceAlphanumeric(this);"/>
              </p>
              <p class="floatl clear">
                <b style="FONT-FAMILY: 'Arial';">User Account Expiry (In days) <sup class="req">*</sup></b>
                <html:text property="userAccountExpiry" styleId="userAccountExpiry" maxlength="3" value="10" styleClass="textbox"  onkeyup="javascript:ValidatenReplaceAlphanumeric(this);"/>
                <html:errors property="userAccountExpiry" />
              </p>
              <%-- <p class="clear floatl">
								<b>Employee Type</b> 
								<span class="dropdownWithoutJs"> 
									<html:select property="empType" styleId="empType_createUser">
										<html:option value="0">Please Select</html:option>
										<html:option value="N">Nextra</html:option>
										<html:option value="P">Partner</html:option>
									</html:select>
								</span>
								<html:errors property="empType_createUser" />
							</p> 
							<p id="emp_code" class="clear floatl hide1  ">
								<b>Employee Code</b>
								<html:text property="empCode" styleId="empCode_createUser" disabled="true" maxlength="10" styleClass="textbox" />
							</p>
							<p id="part_name" class="clear floatl hide1 ">
								<b>Partner Name</b> 
								<span class="dropdownWithoutJs"> 
									<html:select property="partnerName" styleId="partnerName_createUser" disabled="true" title=""></html:select>
								</span>
							</p> --%>
            </div>
            <div class="floatl marginleft60">
              <p class=" floatl">
                <b>Functional Bin <sup class="req">*</sup></b> <span class="showListFunctionalBin"> <html:select multiple="true"
                    property="functionalBinArray">
                    <html:optionsCollection name="loginForm" property="leadStagesList" label="categoryValue" value="categoryId" />
                  </html:select>
                </span>
              </p>
              <p class=" floatl marginleft30">
                <b>Service Name</b> <span class="showListFunctionalBin"> <html:select multiple="true" property="serviceNameArray">
                    <logic:notEmpty name="loginForm" property="productList">
                      <html:optionsCollection name="loginForm" property="productList" label="contentName" value="contentValue" />
                    </logic:notEmpty>
                  </html:select>
                </span>
              </p>
              <p class=" floatl marginleft30">
                <b>Partner Name</b> <span class="showListFunctionalBin"> <html:select multiple="true" property="partnerNameArray">
                    <logic:notEmpty name="loginForm" property="partnerList">
                      <html:optionsCollection name="loginForm" property="partnerList" label="partnerName" value="partnerId" />
                    </logic:notEmpty>
                  </html:select>
                </span>
              </p>
              <p id="sr_code" class="clear floatl ">
                <b>SR Code</b>
                <html:text property="srCode" styleId="srCode_createUser" maxlength="10" styleClass="textbox" />
              </p>
              <p class="floatl clear">
                <b>Mobile Number <sup class="req">*</sup></b> <span class="mobilePrefix">[+91]</span>
                <html:text property="mobileNo" styleId="mobileNo" maxlength="10" styleClass="textbox" onclick="removeZeroFromNumber(this);" onfocus="removeZeroFromNumber(this);" onkeyup="javascript:ValidatenReplaceAlphanumeric(this);"/>
                <html:errors property="mobileNo" />
              </p>
            </div>
          </div>
          <logic:equal name="crmRoles" property="available(create_user_profile)" value="true" scope="session">
            <div class="floatr inner_right">
              <html:link href="#" styleId="submit_createUser" styleClass="main_button">Create</html:link>
            </div>
          </logic:equal>
          <p class="clear"></p>
        </div>
        <p class="clear"></p>
      </div>
    </logic:empty>
    <logic:notEmpty name="loginForm" property="crmUserPojo">
      <div class="section">
        <h2>Update User</h2>
        <span class="error_message"> <html:errors property="appError" />
        </span> <span class="success_message"> <logic:messagesPresent message="true" property="appMessage">
            <html:messages id="msg" message="true" property="appMessage">
              <bean:write name="msg" />
            </html:messages>
          </logic:messagesPresent>
        </span>
        <div class="inner_section">
          <div class="inner_left_lead floatl marginleft10">
            <div class="floatl ">
              <p class="floatl">
                <b>User ID</b>
                <html:hidden property="userId" value="${loginForm.crmUserPojo.userId}" />
                <span class="showTextbox"> <bean:write name="loginForm" property="crmUserPojo.userId" />
                </span>
              </p>
              <p class="floatl clear">
                <b>First Name</b>
                <html:hidden property="firstName" styleId="firstName" value='${loginForm.crmUserPojo.firstName}' />
                <span class="showTextbox"> <bean:write name="loginForm" property="crmUserPojo.firstName" />
                </span>
                <html:errors property="firstName" />
              </p>
              <p class="floatl clear">
                <b>Last Name</b>
                <html:hidden property="lastName" styleId="lastName" styleClass="textbox" value='${loginForm.crmUserPojo.lastName}' />
                <span class="showTextbox"> <bean:write name="loginForm" property="crmUserPojo.lastName" />
                </span>
                <html:errors property="lastName" />
              </p>
              <p class="floatl clear">
                <b>Email ID <sup class="req">*</sup></b>
                <html:text property="emailId" styleId="emailId" styleClass="textbox" value='${loginForm.crmUserPojo.emailId}' />
                <html:errors property="emailId" />
              </p>
              <%-- <p class="floatl clear">
							<b>Employee Type</b>
							
							<span class="showTextbox">	
							<html:hidden property="empType" value="${loginForm.crmUserPojo.empType}" />
							<logic:equal name="loginForm" property="crmUserPojo.empType" value="N">
								Nextra
							</logic:equal>
							<logic:notEqual name="loginForm" property="crmUserPojo.empType" value="N">
								Partner
							</logic:notEqual>
							</span>
											

							<html:errors property="empType_createUser" />
						</p> --%>
              <p class="floatl clear">
                <b>Password Expiry (In days) <sup class="req">*</sup></b>
                <html:text property="passwordExpiry" styleId="passwordExpiry" value="${loginForm.crmUserPojo.passwordExpiry}" maxlength="3" styleClass="textbox"  onkeyup="javascript:ValidatenReplaceAlphanumeric(this);"/>
                <html:errors property="passwordExpiry" />
              </p>
              <p class="floatl clear">
                <b>User Account Expiry (In days) <sup class="req">*</sup></b>
                <html:text property="userAccountExpiry" styleId="userAccountExpiry" maxlength="3" styleClass="textbox"
                  value="${loginForm.crmUserPojo.userAccountExpiry}"  onkeyup="javascript:ValidatenReplaceAlphanumeric(this);"/>
                <html:errors property="userAccountExpiry" />
              </p>
            </div>
            <div class="floatl marginleft60">
              <p class="floatl ">
                <b>Functional Bin <sup class="req">*</sup></b> <span class="showListFunctionalBin"> <html:select name="loginForm"
                    property="functionalBinArray" styleId="functionalBinID" multiple="true">
                    <html:optionsCollection name="loginForm" property="leadStagesList" label="categoryValue" value="categoryId" />
                  </html:select>
                </span>
                <html:errors property="funtionalBin_createUser" />
              </p>
              <p class=" floatl marginleft30">
                <b>Service Name</b> <span class="showListFunctionalBin"> <html:select multiple="true" property="serviceNameArray" name="loginForm"
                    styleId="serviceName">
                    <logic:notEmpty name="loginForm" property="productList">
                      <html:optionsCollection name="loginForm" property="productList" label="contentName" value="contentValue" />
                    </logic:notEmpty>
                  </html:select>
                </span>
              </p>
              <p class=" floatl marginleft30">
                <b>Partner Name</b> <span class="showListFunctionalBin"> <html:select multiple="true" property="partnerNameArray" name="loginForm"
                    styleId="partnerId">
                    <logic:notEmpty name="loginForm" property="partnerList">
                      <html:optionsCollection name="loginForm" property="partnerList" label="partnerName" value="partnerId" />
                    </logic:notEmpty>
                  </html:select>
                </span>
              </p>
              <p id="sr_code" class="clear floatl ">
                <b>SR Code</b>
                <html:text property="srCode" styleId="srCode_createUser" maxlength="10" styleClass="textbox" value='${loginForm.crmUserPojo.srCode}' />
              </p>
              <%-- <logic:equal name="loginForm" property="crmUserPojo.empType" value="N">
						<p id="emp_code" class="floatl clear">
							<b>Employee Code</b>
							<html:hidden property="empCode" styleId="empCode_createUser"  value='${loginForm.crmUserPojo.empCode}'
								 styleClass="textbox" />
								
							<span class="showTextbox">	
							<bean:write name="loginForm" property="crmUserPojo.empCode" />
							</span>
					
							<html:hidden property="empCode" value="${loginForm.crmUserPojo.empCode}" />
						</p>
						</logic:equal>
						<logic:notEqual name="loginForm" property="crmUserPojo.empType" value="N">
						<p id="part_name" class="floatl clear">
							<b>Partner Name</b> 
							<span class="showTextbox">	
							<logic:notEmpty  name="loginForm" property="crmUserPojo.partner">
							<bean:write name="loginForm" property="crmUserPojo.partner.partnerName" />
							</logic:notEmpty>
							
							</span>

							<html:hidden property="partnerName" value="${loginForm.crmUserPojo.partner.partnerId}" />
						</p>
						</logic:notEqual> --%>
              <p class="clear floatl ">
                <b>Mobile Number <sup class="req">*</sup></b> <span class="mobilePrefix">[+91]</span>
                <html:text property="mobileNo" styleId="mobileNo" maxlength="10" styleClass="textbox" value='${loginForm.crmUserPojo.mobileNo}'   onclick="removeZeroFromNumber(this);" onfocus="removeZeroFromNumber(this);" onkeyup="javascript:ValidatenReplaceAlphanumeric(this);"/>
                <html:errors property="mobileNo" />
              </p>
            </div>
            <p class="floatl clear margintop20">
              <b>Status </b> <span> <html:hidden property="recordID" value='${loginForm.crmUserPojo.recordId}' styleId="recordId" /> <html:hidden
                  property="userStatus" value='${loginForm.crmUserPojo.status}' styleId="userStatus" /> <logic:equal name="loginForm"
                  property="crmUserPojo.status" value="P">
                  <font class="statusTextbox">Pending</font>
                  <logic:equal name="crmRoles" property="available(update_user_profile)" value="true" scope="session">
                    <html:link href="javascript:changeStatusUser('${loginForm.crmUserPojo.recordId}','A')" styleClass="grey_button">ACTIVE</html:link>
                    <html:link href="javascript:changeStatusUser('${loginForm.crmUserPojo.recordId}','L')" styleClass="grey_button">LOCK</html:link>
                  </logic:equal>
                </logic:equal> <logic:equal name="loginForm" property="crmUserPojo.status" value="A">
                  <font class="statusTextbox">Active</font>
                  <logic:equal name="crmRoles" property="available(update_user_profile)" value="true" scope="session">
                    <html:link href="javascript:changeStatusUser('${loginForm.crmUserPojo.recordId}','L')" styleClass="grey_button">LOCK</html:link>
                  </logic:equal>
                </logic:equal> <logic:equal name="loginForm" property="crmUserPojo.status" value="N">
                  <font class="statusTextbox"> New</font>
                  <logic:equal name="crmRoles" property="available(update_user_profile,delete_user_profile)" value="true" scope="session">
                    <html:link href="javascript:changeStatusUser('${loginForm.crmUserPojo.recordId} }','A')" styleClass="grey_button">ACTIVE</html:link>
                    <html:link href="javascript:changeStatusUser('${loginForm.crmUserPojo.recordId} }','L')" styleClass="grey_button">LOCK</html:link>
                  </logic:equal>
                </logic:equal> <logic:equal name="loginForm" property="crmUserPojo.status" value="I">
                  <font class="statusTextbox">Inactive</font>
                  <logic:equal name="crmRoles" property="available(update_user_profile,delete_user_profile)" value="true" scope="session">
                    <html:link href="javascript:changeStatusUser('${loginForm.crmUserPojo.recordId} }','L')" styleClass="grey_button">LOCK</html:link>
                  </logic:equal>
                </logic:equal> <logic:equal name="loginForm" property="crmUserPojo.status" value="L">
                  <font class="statusTextbox">Lock</font>
                  <logic:equal name="crmRoles" property="available(update_user_profile,delete_user_profile)" value="true" scope="session">
                    <html:link href="javascript:changeStatusUser('${loginForm.crmUserPojo.recordId} }','E')" styleClass="grey_button">EXPIRE</html:link>
                    <html:link href="javascript:changeStatusUser('${loginForm.crmUserPojo.recordId} }','A')" styleClass="grey_button">ACTIVE</html:link>
                  </logic:equal>
                </logic:equal> <logic:equal name="loginForm" property="crmUserPojo.status" value="E">
                  <font class="statusTextbox">Expire </font>
                  <logic:equal name="crmRoles" property="available(update_user_profile,delete_user_profile)" value="true" scope="session">
                    <html:link href="javascript:changeStatusUser('${loginForm.crmUserPojo.recordId} }','A')" styleClass="grey_button">ACTIVE</html:link>
                  </logic:equal>
                  <logic:equal name="crmRoles" property="available(delete_user_profile)" value="true" scope="session">
                    <html:link href="javascript:changeStatusUser('${loginForm.crmUserPojo.recordId} }','D')" styleClass="grey_button">DELETE</html:link>
                  </logic:equal>
                </logic:equal> <logic:equal name="loginForm" property="crmUserPojo.status" value="D">
                  <font class="statusTextbox">Deleted </font>
                </logic:equal> <logic:notEqual name="loginForm" property="crmUserPojo.status" value="D">
                  <logic:equal name="crmRoles" property="available(update_user_profile)" value="true" scope="session">
                    <html:link href="javascript:resetPassword('${loginForm.crmUserPojo.recordId} }')" styleClass="grey_button">Reset Password</html:link>
                  </logic:equal>
                </logic:notEqual> <logic:equal name="loginForm" property="crmUserPojo.status" value="P">
                  <logic:equal name="crmRoles" property="available(update_user_profile)" value="true" scope="session">
                    <html:link href="javascript:resendActivationLink('${loginForm.crmUserPojo.userId}')" styleClass="grey_button">Re-Send Activation Link</html:link>
                  </logic:equal>
                </logic:equal>
              </span>
            </p>
            <p>
              <!-- 
						<html:link href="javascript:updateRoleAssignPage('${loginForm.crmUserPojo.recordId} }')">Update Role Group</html:link>
						 -->
            </p>
          </div>
          <html:hidden name="loginForm" property="recordID" styleId="record_status" />
          <html:hidden name="loginForm" property="userId" styleId="record_userId" />
          <logic:equal name="crmRoles" property="available(update_user_profile)" value="true" scope="session">
            <div class="floatr inner_right">
              <logic:notEqual name="loginForm" property="crmUserPojo.status" value="D">
                <p>
                  <html:hidden name="loginForm" property="serviceNames" value="${loginForm.serviceNames}" />
                  <html:hidden name="loginForm" property="partnerNames" value="${loginForm.partnerNames}" />
                  <html:link href="#" styleId="submit_updateUser" styleClass="main_button">Update</html:link>
                </p>
              </logic:notEqual>
            </div>
          </logic:equal>
          <p class="clear"></p>
        </div>
        <p class="clear"></p>
           <div class="LmsTable">
				<h4>Activity Log Details <span class="lmsMinusImage floatr"></span></h4>
			  <div class="viewLmsTable margintop10">
			 <iframe src="jsp/content/masterdata/displayAuditLog.jsp" frameborder="0" scrolling="yes"
                  style="border: none; overflow: hidden; width: 100%; height:250px;" allowTransparency="true" onload="iframeLoaded(this)"></iframe>
			 </div>
   	 </div>
      </div>
    </logic:notEmpty>
  </html:form>
</div>
