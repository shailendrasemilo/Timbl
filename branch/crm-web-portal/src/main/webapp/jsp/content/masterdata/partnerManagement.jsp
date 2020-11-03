<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib uri="/WEB-INF/tlds/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/tlds/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/tlds/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/tlds/displaytag.tld" prefix="display"%>
<div class="overlayDiv"></div>
<div class="modelPopupDiv" id="PartnerDocId"></div>
<div class="loadingPopup hidden"></div>
<div id="section" align="center">
  <html:form action="/partnerManagement" method="post" styleId="partnerRegistration">
    <div class="section">
      <logic:empty property="partnerList" name="partnerManagementForm">
        <h2>Partner Registration</h2>
      </logic:empty>
      <logic:notEmpty property="partnerList" name="partnerManagementForm">
        <h2>Edit Partner Details</h2>
      </logic:notEmpty>
      <span class="error_message"><html:errors property="appError" /></span>
      <div class="success_message">
        <logic:messagesPresent message="true">
          <html:messages id="message" message="true">
            <bean:write name="message" />
            <br />
          </html:messages>
        </logic:messagesPresent>
      </div>
      <div class="inner_section">
        <p class="absoluteRight">
          <span class="error_message">All Fields are mandatory </span>
        </p>
        <div class="createUserLeft  floatl marginleft10">
          <div class=" paddingBottom30">
            <h4>Partner Details</h4>
            <p class="floatl clear ">
              <strong>Name of Partner<sup class="req">*</sup></strong>
              <logic:equal name="partnerManagementForm" property="partnerId" value="0">
                <html:text property="partnerName" styleClass="Lmstextboxuppercase" maxlength="60" styleId="pm_partnerName" />
              </logic:equal>
              <logic:notEqual name="partnerManagementForm" property="partnerId" value="0">
                <span class="showTextbox"> <bean:write name="partnerManagementForm" property="partnerName" /> <html:hidden
                    name="partnerManagementForm" property="partnerName" styleId="pm_partnerName" />
                </span>
              </logic:notEqual>
            </p>
            <p class="floatl marginleft30">
              <strong>Date on Boarded<sup class="req">*</sup></strong>
               <logic:equal name="partnerManagementForm" property="partnerId" value="0">
                <html:text property="boardedDate" styleClass="tcal" />
              </logic:equal>
              <logic:notEqual name="partnerManagementForm" property="partnerId" value="0">
                <html:text property="boardedDate" readonly="true" styleClass="textbox" />
             </logic:notEqual>
            </p>
            <br class="clear" />
              <p class="floatl clear">
                <b>Type of Business<sup class="req">*</sup></b> <span class="inlineBlock"> <logic:notEmpty name="partnerManagementForm" property="products">
                    <logic:iterate id="products" name="partnerManagementForm" property="products" indexId="ctr">
                      <LABEL class="label_check margintop5" for="${products.contentName}">
                        <html:checkbox name="products" property="selected" styleId="${products.contentName}" value="true" indexed="true">${products.contentName}</html:checkbox>
                      </LABEL>
                    </logic:iterate>
                  </logic:notEmpty>
                </span>
              </p>
              <p class="floatl clear">
                <strong>Type of Partner<sup class="req">*</sup></strong> <span class="inlineBlock"> <logic:notEmpty name="partnerManagementForm"
                    property="partnerTypeList">
                    <logic:iterate id="partnerTypeList" name="partnerManagementForm" property="partnerTypeList" indexId="ctr">
                      <LABEL class="label_check margintop5" for="${partnerTypeList.contentName}">
                        <html:checkbox name="partnerTypeList" property="selected" styleId="${partnerTypeList.contentName}" value="true" indexed="true">${partnerTypeList.contentName}</html:checkbox>
                      </LABEL>
                    </logic:iterate>
                  </logic:notEmpty>
                </span>
              </p>
            <p class="floatl clear">
              <b>Partner's Chief Executive Name<sup class="req">*</sup></b> <span class="honorificsDropdown"> <html:select property="chiefInitial" styleClass="select">
                  <html:option value="MR.">MR.</html:option>
                  <html:option value="MRS.">MRS.</html:option>
                  <html:option value="MISS">MISS</html:option>
                </html:select>
              </span> <span class="relative inlineBlock"><html:text property="chiefExFirstName" styleClass="Lmstextboxuppercase marginleft6" /></span> <span
                class="relative inlineBlock"><html:text property="chiefExLastName" styleClass="Lmstextboxuppercase marginleft6" /></span>
            </p>
            <p class="floatl clear">
              <strong>Designation<sup class="req">*</sup></strong>
              <html:text property="chiefDesignation" styleClass="Lmstextboxuppercase" maxlength="50" />
            </p>
            <p class="floatl marginleft40">
              <strong>Mobile Number<sup class="req">*</sup></strong> <span class="mobilePrefix">[+91]</span>
              <html:text property="chiefMobile" size="10" styleClass="Lmstextbox" maxlength="10" onclick="removeZeroFromNumber(this);" onfocus="removeZeroFromNumber(this);" onkeyup="javascript:ValidatenReplaceAlphanumeric(this);"/>
            </p>
            <p class="floatl marginleft40">
              <strong>Email ID<sup class="req">*</sup></strong>
              <html:text property="chiefEmail" styleClass="Lmstextbox" />
            </p>
            <p class="floatl marginleft40">
              <b>Related Department<sup class="req">*</sup></b> <span class="LmsdropdownWithoutJs"> <html:select property="relatedDept">
                  <html:option value="Sales">Sales</html:option>
                  <html:option value="Marketing">Marketing</html:option>
                  <html:option value="Networking">Networking</html:option>
                </html:select>
              </span>
            </p>
            <br class="clear" />
          </div>
          <div class=" paddingBottom30">
            <h4>Head Office</h4>
            <p class="margintop20">
              <b>Contact Person Name<sup class="req">*</sup></b> <span class="honorificsDropdown"> <html:select property="contactChiefInitial" styleClass="select">
                  <html:option value="MR.">MR.</html:option>
                  <html:option value="MRS.">MRS.</html:option>
                  <html:option value="MISS">MISS</html:option>
                </html:select>
              </span> <span class="relative inlineBlock"><html:text property="contactFirstName" styleClass="Lmstextboxuppercase marginleft6" /></span> <span
                class="relative inlineBlock"><html:text property="contactLastName" styleClass="Lmstextboxuppercase marginleft6" /></span>
            </p>
            <p class="floatl clear">
              <strong>Email ID<sup class="req">*</sup></strong>
              <html:text property="partnerEmail" styleClass="Lmstextbox" />
            </p>
            <p class="floatl marginleft40">
              <strong>Phone Number<sup class="req">*</sup></strong> <span class="mobilePrefix">[+91]</span>
              <html:text property="partnerPhone" styleClass="Lmstextbox" maxlength="10" onclick="removeZeroFromNumber(this);" onfocus="removeZeroFromNumber(this);" onkeyup="javascript:ValidatenReplaceAlphanumeric(this);"/>
            </p>
            <p class="floatl marginleft40">
              <strong>Mobile Number<sup class="req">*</sup></strong> <span class="mobilePrefix">[+91]</span>
              <html:text property="partnerMobile" styleClass="Lmstextbox" maxlength="10" onclick="removeZeroFromNumber(this);" onfocus="removeZeroFromNumber(this);" onkeyup="javascript:ValidatenReplaceAlphanumeric(this);"/>
            </p>
            <p class="floatl marginleft40">
              <strong>Fax Number<sup class="req">*</sup></strong>
              <html:text property="partnerFax" styleClass="Lmstextbox" onclick="removeZeroFromNumber(this);" onfocus="removeZeroFromNumber(this);" onkeyup="javascript:ValidatenReplaceAlphanumeric(this);"/>
            </p>
            <p class="floatl clear">
              <b>Address<sup class="req">*</sup></b>
              <html:textarea property="partnerAddress1" styleClass="LmsRemarkstextarea" cols="80" rows="3"></html:textarea>
            </p>
            <p class="floatl clear">
              <strong>State<sup class="req">*</sup></strong> <span class="LmsdropdownWithoutJs"> <html:select property="partnerState" styleId="partnerStateId"
                  value='${partnerManagementForm.partnerState }'>
                  <option value="0">Please Select</option>
                  <logic:notEmpty name="partnerManagementForm" property="statePojoList">
                    <html:optionsCollection name="partnerManagementForm" property="statePojoList" label="stateName" value="stateName" />
                  </logic:notEmpty>
                </html:select>
              </span>
            </p>
            <p class="floatl marginleft30">
              <strong>City<sup class="req">*</sup></strong> <span class="LmsdropdownWithoutJs"> <logic:notEmpty name="partnerManagementForm" property="cityPojoList">
                  <html:select property="partnerCity" name="partnerManagementForm" styleId="partnerCityId"
                    value='${partnerManagementForm.partnerCity }'>
                    <html:option value="">Please Select</html:option>
                    <html:optionsCollection name="partnerManagementForm" property="cityPojoList" label="cityName" value="cityName" />
                  </html:select>
                </logic:notEmpty> <logic:empty name="partnerManagementForm" property="cityPojoList">
                  <html:select property="partnerCity" name="partnerManagementForm" styleId="partnerCityId"
                    value='${partnerManagementForm.partnerCity }'>
                  </html:select>
                </logic:empty>
              </span>
            </p>
            <p class="floatl marginleft30">
              <strong>PIN Code<sup class="req">*</sup></strong>
              <html:text property="partnerPincode" styleClass="Lmstextbox" maxlength="6" onclick="removeZeroFromNumber(this);" onfocus="removeZeroFromNumber(this);" onkeyup="javascript:ValidatenReplaceAlphanumeric(this);"/>
            </p>
            <p class="floatl marginleft30">
              <strong>Current Status<sup class="req">*</sup></strong>
              <LABEL class="label_radio margintop5" for="active">
                <html:radio property="currentStatus" value="A" styleId="active">Active</html:radio>
              </LABEL>
              <LABEL class="label_radio margintop5" for="inactive">
                <html:radio property="currentStatus" value="I" styleId="inactive">Inactive</html:radio>
              </LABEL>
              <logic:notEmpty property="partnerList" name="partnerManagementForm">
                <LABEL class="label_radio margintop5" for="delete">
                  <html:radio property="currentStatus" value="D" styleId="delete">Delete</html:radio>
                </LABEL>
              </logic:notEmpty>
            </p>
            <p class="floatl clear">
              <strong style="width: 100%;">Partner Code (optional)</strong>
              <html:text property="partnerCode" name="partnerManagementForm" styleClass="Lmstextbox" maxlength="30" styleId="partnerCodeId" />
            </p>
            <p class="floatl marginleft30">
              <strong>&nbsp;</strong> <a href="javascript:void(0);" onclick="uploadDocument('${initParam.dmshost }/np-document-upload','ADMIN',$('#pm_partnerName').val());"
                class="marginleft30 yellow_button">Upload&nbsp;Document</a>
              <%-- onclick="uploadDocument($('#partnerCodeId').val(),$('#pm_partnerName').val());" --%>
            </p>
            <%--<p class="floatl marginleft30"><strong>Added in ERP</strong>
        <logic:empty property="addedInERP" name="partnerManagementForm">
       		<span class="LmsdropdownWithoutJs">
       		 	<html:select property="addedInERP" styleId="addedInERP">
					<html:option value="0">Select</html:option>
					<html:option value="P">Pending</html:option>
					<html:option value="C">Complete</html:option>
				</html:select>
    		</span>
       	</logic:empty>
       
       	<logic:notEmpty property="addedInERP" name="partnerManagementForm">       		       			
       			<logic:equal name="partnerManagementForm" property="addedInERP" value="C">
       				<html:hidden  name="partnerManagementForm" property="addedInERP" value="C"/>
       				<span class=" showTextboxReadonly">Complete</span>
		   				<p class=" floatl marginleft30"><strong>Partner ERP Code</strong> 
		    				<html:hidden name="partnerManagementForm" property="partnerERPCode" />
		    				<span class="showTextboxReadonly">
		      					<bean:write name="partnerManagementForm"  property="partnerERPCode" />
		   				</span>
					 	</p>
       			</logic:equal>
       			<logic:equal name="partnerManagementForm" property="addedInERP" value="P">
       			<span class="LmsdropdownWithoutJs">
       		 	<html:select property="addedInERP" styleId="addedInERP">
					<html:option value="0">Select</html:option>
					<html:option value="P">Pending</html:option>
					<html:option value="C">Complete</html:option>
				</html:select>
    			</span>
    		</logic:equal>       		
       	</logic:notEmpty>     
       	
       	
     </p> --%>
            <%--<iframe src="http://10.20.0.12:8080/suncity/JSPs/AddDoc_order.jsp?CAFNO=Partner001&CustomerID=Partner001&CustomerName=Tata&RMN=9650145266&RTN=9650145266" ></iframe> --%>
            <%--<p class="partnerERPCode floatl marginleft30"><strong>Partner ERP Code </strong> 
		<html:text property="partnerERPCode" styleClass="Lmstextbox" maxlength="30"/>
		</p> --%>
          </div>
        </div>
        <div class="floatr inner_right">
          <br /> <br />
          <logic:empty property="partnerList" name="partnerManagementForm">
            <html:link href="#" styleId="managePartnerAdd" styleClass="main_button floatr">Register</html:link>
          </logic:empty>
          <logic:notEmpty property="partnerList" name="partnerManagementForm">
            <html:link href="#" styleId="managePartnerUpdate" styleClass="main_button floatr">Update</html:link>
          </logic:notEmpty>
        </div>
        <p class="clear"></p>
      </div>
     <div class="LmsTable">
				<h4>Activity Log Details <span class="lmsMinusImage floatr"></span></h4>
			  <div class="viewLmsTable margintop10">
			 <iframe src="jsp/content/masterdata/displayAuditLog.jsp" frameborder="0" scrolling="yes"
                  style="border: none; overflow: hidden; width: 100%; height:250px;" allowTransparency="true" onload="iframeLoaded(this)"></iframe>
			 </div>
   	 </div>
    </div>
    <p class="clear"></p>
  </html:form>
</div>
