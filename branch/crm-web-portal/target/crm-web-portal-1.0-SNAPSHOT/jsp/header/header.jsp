<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<%@taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<script>
	$( document ).ready( function(){
		$('#idSearchCriteriaValue').bind('keypress', function(evt){
			var keyCode = evt.which || evt.keyCode;
			if ( keyCode == 13 ) {
				customerProfileSearch();
				return false;
			}

		} );
	} );
</script>
<div class="loadingPopup hidden"></div>
<div id="header">
  <div class="header">
    <div class="floatl">
      <ul class="top_menu">
        <%--<li><html:link href="login.do?method=redirectHome"><html:img src="images/bg/home.png" /></html:link></li>--%>
        <html:hidden styleId="mobileNumberRegex" property="mobileNumberRegex" value="${sessionScope.mobileNumberRegex}" />
        <html:hidden styleId="mobileNumberMesg" property="mobileNumberMesg" value="${sessionScope.mobileNumberMesg}" />
        <logic:notEmpty name="modules" scope="session">
          <logic:iterate id="modules" name="modules" scope="session">
            <logic:equal name="modules" property="moduleDisplay" value="Home">
              <li class="${modules.styleClass}"><html:link href="${modules.moduleUrl}">
                  <html:img src="images/bg/home.png" />
                </html:link></li>
            </logic:equal>
            <logic:notEqual name="modules" property="moduleDisplay" value="Home">
              <li class="${modules.styleClass}"><html:link href="${modules.moduleUrl}">
                  <bean:write name="modules" property="moduleDisplay" />
                </html:link></li>
            </logic:notEqual>
          </logic:iterate>
        </logic:notEmpty>
      </ul>
    </div>
    <div class="floatr">
      <ul class="login_menu">
        <li>Hi <html:link href="userManagement.do?method=myProfile">
            <logic:present name="userPojo" property="userId">
              <bean:write name="userPojo" property="firstName" />
            </logic:present>
          </html:link>
        </li>
        <li><html:link href="login.do?method=logout">logout</html:link></li>
      </ul>
    </div>
    <p class="clear"></p>
  </div>
</div>
<!----------------- Navigation ------------------------------->
<div id="navigation">
  <div class="navigation">
    <ul class="navigation_menu">
      <logic:notEmpty name="subModules" scope="session">
        <logic:iterate id="subModules" name="subModules" scope="session">
          <li class="${subModules.styleClass}"><html:link href="${subModules.moduleUrl}">
              <bean:write name="subModules" property="moduleDisplay" />
            </html:link></li>
        </logic:iterate>
      </logic:notEmpty>
    </ul>
    <p class="clear"></p>
  </div>
</div>
<!----------------- Menu ------------------------------->
<div id="menu">
  <div class="menu">
    <span class="floatl"><html:link href="userManagement.do?method=myProfile" styleId="profileSearch">
        <img src="<bean:message bundle="userProp" key="brand.logo"/>" />
      </html:link></span>
    <div class="floatr">
      <html:form action="/profileSearch.do?method=customerProfileSearch" method="post">
        <div class="profile_search">
          <logic:equal name="crmRoles" property="available(view_lead,view_ina,view_qrc)" value="true" scope="session">
            <span class="text">Customer Profile Search: </span>
            <span class="searchDropdown"> <html:select property="profileSearchName" name="customerProfileForm" styleId="idSearchCriteria">
                <html:options collection="customerProfileList" property="contentValue" labelProperty="contentName" />
              </html:select>
            </span>
            <html:text property="searchValue" name="customerProfileForm" styleClass="textbox marginleft6" styleId="idSearchCriteriaValue"
              onkeyup="javascript:changeToUpperCase(this)"></html:text>
            <button name="" class="go_button" type="button" value="Go" onclick="javascript:customerProfileSearch();">Go</button>
          </logic:equal>
          <logic:equal name="crmRoles" property="available(view_lead,view_ina,view_qrc)" value="false" scope="session">
            <p style="height: px; padding: 5px;"></p>
          </logic:equal>
        </div>
      </html:form>
      <ul class="tabs_menu ">
        <logic:notEmpty name="subSubModules" scope="session">
          <logic:iterate id="subSubModules" name="subSubModules" scope="session">
            <li class="${subSubModules.styleClass}"><html:link href="${subSubModules.moduleUrl}">
                <bean:write name="subSubModules" property="moduleDisplay" />
              </html:link></li>
          </logic:iterate>
        </logic:notEmpty>
      </ul>
    </div>
    <p class="clear"></p>
  </div>
</div>
