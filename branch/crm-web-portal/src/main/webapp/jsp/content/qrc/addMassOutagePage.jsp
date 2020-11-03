<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib uri="/WEB-INF/tlds/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/tlds/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/tlds/struts-logic.tld" prefix="logic"%>
<%@taglib uri="http://struts.apache.org/tags-nested" prefix="nested"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<script type="text/javascript">
	$( document ).ready( function(){
		$( '#fbrOutageTree, #eocOutageTree, #rfOutageTree' ).bonsai( {
			expandAll : false, checkboxes : true, createCheckboxes : true, handleDuplicateCheckboxes : false, addExpandAll : true
		} );
	} );
</script>
<div id="section">
  <div class="section">
    <h2>
      Add Mass Outage <a href="massOutage.do?method=searchMassOutagePage" class="yellow_button floatr margintop7 ">Search Mass Outage</a>
    </h2>
    <div class="inner_section ">
      <div class="error_message" id="error">
        <html:errors />
      </div>
      <div class="success_message">
        <logic:messagesPresent message="true">
          <html:messages id="message" message="true">
            <bean:write name="message" />
          </html:messages>
        </logic:messagesPresent>
      </div>
      <html:form action="/massOutage.do?method=addMassOutage" styleId="addMassOutageForm">
        <div class="QRC marginleft10 floatl marginright40">
          <p class="floatl clear">
            <strong>Outage Type <sup class="req">*</sup></strong> <span class="inlineBlock"> <label class="marginRight10 label_radio">
                <html:radio property="massOutagePojo.outageType" value="Planned" name="massOutageForm" onclick="toggleOutageStartTime(this.value);">Planned</html:radio>
            </label> <label class="marginRight10 label_radio"> <html:radio property="massOutagePojo.outageType" value="Unplanned"
                  name="massOutageForm" onclick="toggleOutageStartTime(this.value);">Unplanned</html:radio>
            </label>
            </span>
          </p>
          <p class="floatl clear">
            <strong>Service Name <sup class="req">*</sup></strong> <span class="inlineBlock"> <logic:iterate id="productTypeList"
                name="massOutageForm" property="productTypeList">
                <label class=" marginRight10 label_radio"><html:radio name="massOutageForm" property="massOutagePojo.serviceName"
                    value="${productTypeList.contentValue}" onclick="displayOutageTree(this);">
                    <bean:write name="crmRoles" property="displayEnum(Product,${productTypeList.contentValue})" scope="session" />
                  </html:radio></label>
              </logic:iterate>
            </span>
          </p>
          <p class="floatl clear outageStartTime ${massOutageForm.massOutagePojo.outageType eq 'Planned' ? '' : 'hidden' }">
            <strong>Outage Start Time <%-- <span class="floatr">(HH:MM:SS format)</span> --%> <sup class="req">*</sup></strong>
            <fmt:parseDate var="dosds" value="${massOutageForm.massOutagePojo.displayOutageStartTime}" pattern="dd-MMM-yyyy HH:mm:ss" />
            <fmt:formatDate var="dosd" value="${dosds }" pattern="dd-MMM-yyyy" />
            <fmt:formatDate var="dosth" value="${dosds }" pattern="HH" />
            <fmt:formatDate var="dostm" value="${dosds }" pattern="mm" />
            <fmt:formatDate var="dosts" value="${dosds }" pattern="ss" />
            <html:hidden property="massOutagePojo.displayOutageStartTime" name="massOutageForm" styleId="displayOstId"
              disabled="${massOutageForm.massOutagePojo.outageType eq 'Planned' ? 'false' : 'true' }" />
            <input type="text" class="tcal" name="ostDate" id="ostDate" value="${not empty dosd ? dosd : ''}"
              ${massOutageForm.massOutagePojo.outageType eq 'Planned' ? '' : 'disabled="disabled"' } /> <font
              style="outline: 1px solid #AFAFAF; padding: 5px;" class="marginleft10"> <select id="ostHH"
              ${massOutageForm.massOutagePojo.outageType eq 'Planned' ? '' : 'disabled="disabled"' }>
                <%
                    for ( int i = 0; i < 24; i++ )
                        {
                %>
                <option value="<%=String.format( "%02d", i )%>"
                  <c:if test="${not empty dosth }">
                <%=i == Integer.parseInt( (String) pageContext.getAttribute( "dosth" ) ) ? "selected='selected'" : ""%>
                </c:if>><%=String.format( "%02d", i )%></option>
                <%
                    }
                %>
            </select> <select id="ostMM" ${massOutageForm.massOutagePojo.outageType eq 'Planned' ? '' : 'disabled="disabled"' }>
                <%
                    for ( int i = 0; i < 60; i++ )
                        {
                %>
                <option value="<%=String.format( "%02d", i )%>"
                  <c:if test="${not empty dostm }">
                <%=i == Integer.parseInt( (String) pageContext.getAttribute( "dostm" ) ) ? "selected='selected'" : ""%>
                </c:if>><%=String.format( "%02d", i )%></option>
                <%
                    }
                %>
            </select> <select id="ostSS" ${massOutageForm.massOutagePojo.outageType eq 'Planned' ? '' : 'disabled="disabled"' }>
                <%
                    for ( int i = 0; i < 60; i++ )
                        {
                %>
                <option value="<%=String.format( "%02d", i )%>"
                  <c:if test="${not empty dosts }">
                <%=i == Integer.parseInt( (String) pageContext.getAttribute( "dosts" ) ) ? "selected='selected'" : ""%>
                </c:if>><%=String.format( "%02d", i )%></option>
                <%
                    }
                %>
            </select>
            </font>
          </p>
          <p class="floatl clear">
            <strong>Outage ETR Time <%-- <span class="floatr">(HH:MM:SS format)</span> --%> <sup class="req">*</sup></strong>
            <fmt:parseDate var="doeds" value="${massOutageForm.massOutagePojo.displayOutageEtrTime}" pattern="dd-MMM-yyyy HH:mm:ss" />
            <fmt:formatDate var="doed" value="${doeds }" pattern="dd-MMM-yyyy" />
            <fmt:formatDate var="doeth" value="${doeds }" pattern="HH" />
            <fmt:formatDate var="doetm" value="${doeds }" pattern="mm" />
            <fmt:formatDate var="doets" value="${doeds }" pattern="ss" />
            <html:hidden property="massOutagePojo.displayOutageEtrTime" name="massOutageForm" styleId="displayEtrId" />
            <input type="text" class="tcal" name="oetDate" id="oetDate" value="${not empty doed ? doed : '' }" /> <font
              style="outline: 1px solid #AFAFAF; padding: 5px;" class="marginleft10"> <select id="oetHH">
                <%
                    for ( int i = 0; i < 24; i++ )
                        {
                %>
                <option value="<%=String.format( "%02d", i )%>"
                  <c:if test="${not empty doeth }">
                <%=i == Integer.parseInt( (String) pageContext.getAttribute( "doeth" ) ) ? "selected='selected'" : ""%>
                </c:if>><%=String.format( "%02d", i )%></option>
                <%
                    }
                %>
            </select> <select id="oetMM">
                <%
                    for ( int i = 0; i < 60; i++ )
                        {
                %>
                <option value="<%=String.format( "%02d", i )%>"
                  <c:if test="${not empty doetm }">
                <%=i == Integer.parseInt( (String) pageContext.getAttribute( "doetm" ) ) ? "selected='selected'" : ""%>
                </c:if>><%=String.format( "%02d", i )%></option>
                <%
                    }
                %>
            </select> <select id="oetSS">
                <%
                    for ( int i = 0; i < 60; i++ )
                        {
                %>
                <option value="<%=String.format( "%02d", i )%>"
                  <c:if test="${not empty doets }">
                <%=i == Integer.parseInt( (String) pageContext.getAttribute( "doets" ) ) ? "selected='selected'" : ""%>
                </c:if>><%=String.format( "%02d", i )%></option>
                <%
                    }
                %>
            </select>
            </font>
          </p>
          <p class="floatl clear">
            <strong>Reason <sup class="req">*</sup></strong> <span class="qrcConfigDropdownWithoutJs"> <html:select
                property="massOutagePojo.reason">
                <html:option value="">Please Select</html:option>
                <html:optionsCollection name="massOutageForm" property="reasons" label="categoryValue" value="categoryValue" />
              </html:select>
            </span>
          </p>
          <p class="floatl clear">
            <strong>Event Communication</strong> 
            <html:checkbox name="massOutageForm" property="massOutagePojo.sendSms" style=" position: relative;
    top: 2px;">Send SMS</html:checkbox>
          </p>
          <p class="floatl clear">
            <strong>Remarks <sup class="req">*</sup></strong>
            <html:textarea property="massOutagePojo.remarks" name="massOutageForm" styleClass="textarea" style="height: 70px;"></html:textarea>
          </p>
        </div>
        <div class="floatl treeView">
          <%-- EOC Mass Outage --%>
          <div class="eocOutage margintop20 clear floatl ${massOutageForm.massOutagePojo.serviceName eq 'EOC' ? '' : 'hidden' }">
            <ul id="eocOutageTree" class="floatl clear">
              <c:forEach items="${massOutageForm.networkPartnersEoc }" var="networkPartnersEoc" varStatus="npEoc">
                <li data-name="networkPartnersEoc[${npEoc.index }].editable" data-value="on" data-checked="${networkPartnersEoc.editable ? 1 : 0 }">${networkPartnersEoc.partnerName }
                  <logic:notEmpty property="partnerNetworkConfigPojos" name="networkPartnersEoc">
                    <ul>
                      <c:forEach items="${networkPartnersEoc.partnerNetworkConfigPojos }" var="partnerNetworkConfigPojos" varStatus="npEocMaster">
                        <li data-name="networkPartnersEoc[${npEoc.index }].partnerNetworkConfigPojos[${npEocMaster.index }].editable" data-value="on"
                          data-checked="${partnerNetworkConfigPojos.editable ? 1 : 0 }">
                          <%-- ${partnerNetworkConfigPojos.recordId}: --%> ${partnerNetworkConfigPojos.masterName } <%-- ${partnerNetworkConfigPojos.nasPortId } ${partnerNetworkConfigPojos.poolName } --%>
                        </li>
                      </c:forEach>
                    </ul>
                  </logic:notEmpty>
                </li>
              </c:forEach>
            </ul>
          </div>
          <%-- RF Mass Outage --%>
          <div class="rfOutage margintop20 clear floatl ${massOutageForm.massOutagePojo.serviceName eq 'RF' ? '' : 'hidden' }">
            <ul id="rfOutageTree" class="floatl clear">
              <c:forEach items="${massOutageForm.networkPartnersRf }" var="networkPartnersRf" varStatus="npRf">
                <li data-name="networkPartnersRf[${npRf.index }].editable" data-value="on" data-checked="${networkPartnersRf.editable ? 1 : 0 }">${networkPartnersRf.partnerName }
                  <logic:notEmpty property="partnerNetworkConfigPojos" name="networkPartnersRf">
                    <ul>
                      <c:forEach items="${networkPartnersRf.partnerNetworkConfigPojos }" var="partnerNetworkConfigPojos" varStatus="npRfMaster">
                        <li data-name="networkPartnersRf[${npRf.index }].partnerNetworkConfigPojos[${npRfMaster.index }].editable" data-value="on"
                          data-checked="${partnerNetworkConfigPojos.editable ? 1 : 0 }">
                          <%-- ${partnerNetworkConfigPojos.recordId}: --%> ${partnerNetworkConfigPojos.masterName } <%-- ${partnerNetworkConfigPojos.nasPortId } ${partnerNetworkConfigPojos.poolName } --%>
                        </li>
                      </c:forEach>
                    </ul>
                  </logic:notEmpty>
                </li>
              </c:forEach>
            </ul>
          </div>
          <%-- Fiber Broadband Mass Outage --%>
          <div class="fbrOutage margintop20 clear floatl ${massOutageForm.massOutagePojo.serviceName eq 'BB' ? '' : 'hidden' }">
            <ul id="fbrOutageTree" class="floatl clear">
              <c:forEach items="${massOutageForm.networkPartnersFbr }" var="networkPartnersFbr" varStatus="npFbr">
                <li data-name="networkPartnersFbr[${npFbr.index }].editable" data-value="on" data-checked="${networkPartnersFbr.editable ? 1 : 0 }">${networkPartnersFbr.partnerName }
                  <ul>
                    <c:forEach items="${networkPartnersFbr.statePojos}" var="statePojos" varStatus="state">
                      <li data-name="networkPartnersFbr[${npFbr.index }].statePojos[${state.index }].editable" data-value="on"
                        data-checked="${statePojos.editable ? 1 : 0 }">${state.index + 1 }:${statePojos.stateName }
                        <ul>
                          <c:forEach items="${statePojos.cities }" var="cities" varStatus="city">
                            <li data-name="networkPartnersFbr[${npFbr.index }].statePojos[${state.index }].cities[${city.index }].editable"
                              data-value="on" data-checked="${cities.editable ? 1 : 0 }">${state.index + 1 }.${city.index + 1}:${cities.cityName }
                              <ul>
                                <c:forEach items="${cities.areas }" var="areas" varStatus="area">
                                  <li
                                    data-name="networkPartnersFbr[${npFbr.index }].statePojos[${state.index }].cities[${city.index }].areas[${area.index }].editable"
                                    data-value="on" data-checked="${areas.editable ? 1 : 0 }">${state.index + 1 }.${city.index + 1}.${area.index + 1}:${areas.area }
                                    <ul>
                                      <%-- <c:forEach items="${areas.localities }" var="localities" varStatus="locality">
                                        <li
                                          data-name="networkPartnersFbr[${npFbr.index }].statePojos[${state.index }].cities[${city.index }].areas[${area.index }].localities[${locality.index }].editable"
                                          data-value="on" data-checked="${localities.editable ? 1 : 0 }">${state.index + 1 }.${city.index + 1}.${area.index + 1}.${locality.index + 1}:
                                          ${localities.locality }
                                          <ul> --%>
                                            <c:forEach items="${areas.societies }" var="societies" varStatus="society">
                                              <li
                                                data-name="networkPartnersFbr[${npFbr.index }].statePojos[${state.index }].cities[${city.index }].areas[${area.index }].societies[${society.index }].editable"
                                                data-value="on" data-checked="${societies.editable ? 1 : 0}">${state.index + 1 }.${city.index + 1}.${area.index + 1}.${society.index + 1}:
                                                ${societies.searchName }</li>
                                            </c:forEach>
                                          <%-- </ul>
                                        </li>
                                      </c:forEach> --%>
                                    </ul>
                                  </li>
                                </c:forEach>
                              </ul>
                            </li>
                          </c:forEach>

                        </ul>
                      </li>
                    </c:forEach>
                  </ul>
                </li>
              </c:forEach>
              <%--
              <c:forEach items="${massOutageForm.statePojos}" var="statePojos" varStatus="state">
                <li data-name="statePojos[${state.index }].editable" data-value="on" data-checked="${statePojos.editable ? 1 : 0 }">${state.index + 1 }:
                  ${statePojos.stateName }
                  <ul>
                    <c:forEach items="${statePojos.cities }" var="cities" varStatus="city">
                      <li data-name="statePojos[${state.index }].cities[${city.index }].editable" data-value="on"
                        data-checked="${cities.editable ? 1 : 0 }">${state.index + 1 }.${city.index + 1}:${cities.cityName }
                        <ul>
                          <c:forEach items="${cities.areas }" var="areas" varStatus="area">
                            <li data-name="statePojos[${state.index }].cities[${city.index }].areas[${area.index }].editable" data-value="on"
                              data-checked="${areas.editable ? 1 : 0 }">${state.index + 1 }.${city.index + 1}.${area.index + 1}:${areas.area }
                              <ul>
                                <c:forEach items="${areas.localities }" var="localities" varStatus="locality">
                                  <li
                                    data-name="statePojos[${state.index }].cities[${city.index }].areas[${area.index }].localities[${locality.index }].editable"
                                    data-value="on" data-checked="${localities.editable ? 1 : 0 }">${state.index + 1 }.${city.index + 1}.${area.index + 1}.${locality.index + 1}:
                                    ${localities.locality }
                                    <ul>
                                      <c:forEach items="${localities.societies }" var="societies" varStatus="society">
                                        <li
                                          data-name="statePojos[${state.index }].cities[${city.index }].areas[${area.index }].localities[${locality.index }].societies[${society.index }].editable"
                                          data-value="on" data-checked="${societies.editable ? 1 : 0}">${state.index + 1 }.${city.index + 1}.${area.index + 1}.${locality.index + 1}.${society.index + 1}:
                                          ${societies.societyName }</li>
                                      </c:forEach>
                                    </ul>
                                  </li>
                                </c:forEach>
                              </ul>
                            </li>
                          </c:forEach>
                        </ul>
                      </li>
                    </c:forEach>

                  </ul>
                </li>
              </c:forEach>
               --%>
            </ul>
          </div>
        </div>
        <div class="floatr inner_right" style="top: 20px;">
          <a href="#" id="submitAddMassOutage" class="main_button"><span>Submit</span></a>
        </div>
        <p class="clear"></p>
      </html:form>
      <!-- <p class="clear" style="height: 250px;"></p> -->
    </div>
  </div>
</div>
<p class="clear"></p>