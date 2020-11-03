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
      Edit Mass Outage <a href="massOutage.do?method=searchMassOutagePage" class="yellow_button floatr margintop7 ">Search Mass Outage</a>
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
      <html:form action="/massOutage.do?method=updateMassOutage" styleId="updateMassOutageForm">
        <div class="QRC marginleft10 floatl marginright40">
          <p class="floatl clear">
            <strong>Outage Type</strong> <label class="marginRight10"> <html:radio property="massOutagePojo.outageType" value="Planned"
                name="massOutageForm" styleId="" disabled="true">Planned</html:radio>
            </label> <label class="marginRight10 " style="margin-top: 8px;"> <html:radio property="massOutagePojo.outageType" value="Unplanned"
                name="massOutageForm" styleId="" disabled="true">Unplanned</html:radio>
            </label>
          </p>
          <p class="floatl clear">
            <strong>Service Name</strong>
            <logic:iterate id="productTypeList" name="massOutageForm" property="productTypeList">
              <label class=" marginRight10"><html:radio name="massOutageForm" property="massOutagePojo.serviceName"
                  value="${productTypeList.contentValue}" onclick="displayOutageTree(this);" disabled="true">
                  <bean:write name="crmRoles" property="displayEnum(Product,${productTypeList.contentValue})" scope="session" />
                </html:radio></label>
            </logic:iterate>
          </p>
          <p class="floatl clear">
            <strong>Outage Start Time <!-- <span class="floatr">(HH:MM:SS format)</span> -->
            </strong>
            <html:hidden property="massOutagePojo.displayOutageStartTime" name="massOutageForm" styleId="displayOstId" />
            <label>
             <bean:write name="crmRoles" property="xmlDate(${massOutageForm.massOutagePojo.outageStartTime})" /></label>
            <%--
              <html:hidden property="massOutagePojo.displayOutageStartTime" name="massOutageForm" styleId="displayOstId" />
              <input type="text" class="tcal" id="ostDate" /> <font style="outline: 1px solid #AFAFAF; padding: 5px;" class="marginleft10"> <select
                id="ostHH">
                  <%
                      for ( int i = 0; i < 24; i++ )
                          {
                  %>
                  <option value="<%=String.format( "%02d", i )%>"><%=String.format( "%02d", i )%></option>
                  <%
                      }
                  %>
              </select> <select id="ostMM">
                  <%
                      for ( int i = 0; i < 60; i++ )
                          {
                  %>
                  <option value="<%=String.format( "%02d", i )%>"><%=String.format( "%02d", i )%></option>
                  <%
                      }
                  %>
              </select> <select id="ostSS">
                  <%
                      for ( int i = 0; i < 60; i++ )
                          {
                  %>
                  <option value="<%=String.format( "%02d", i )%>"><%=String.format( "%02d", i )%></option>
                  <%
                      }
                  %>
              </select>
              </font>
             --%>
          </p>
          <p class="floatl clear">
            <strong>Outage ETR Time <!-- <span class="floatr">(HH:MM:SS format)</span> --> <span class="floatr">(<bean:write name="crmRoles" property="xmlDate(${massOutageForm.massOutagePojo.outageEtrTime})" />)</span>
             
            </strong>
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
          <c:if test="${massOutageForm.massOutagePojo.serviceName eq 'EOC'}">
            <div class="eocOutage margintop20 clear floatl">
              <ul id="eocOutageTree" class="floatl clear">
                <c:forEach items="${massOutageForm.massOutagePojo.crmMassOutagePartners }" var="crmMassOutagePartners" varStatus="npEoc">
                  <li data-name="massOutagePojo.crmMassOutagePartners[${npEoc.index }].enabled" data-value="on"
                    data-checked="${crmMassOutagePartners.enabled ? 1 : 0 }"
                    <%-- data-onclick="${crmMassOutagePartners.enabled ? '' : 'return false;' }" --%>
                    data-disabled="${crmMassOutagePartners.enabled ? 0 : 1 }">${crmMassOutagePartners.partnerName }<logic:notEmpty
                      property="crmMassOutageMasterses" name="crmMassOutagePartners">
                      <ul>
                        <c:forEach items="${crmMassOutagePartners.crmMassOutageMasterses }" var="crmMassOutageMasterses" varStatus="npEocMaster">
                          <li data-name="massOutagePojo.crmMassOutagePartners[${npEoc.index }].crmMassOutageMasterses[${npEocMaster.index }].enabled"
                            data-value="on" data-checked="${crmMassOutageMasterses.enabled ? 1 : 0 }"
                            <%-- data-onclick="${crmMassOutageMasterses.enabled ? '' : 'return false;' }" --%>
                            data-disabled="${crmMassOutageMasterses.enabled ? 0 : 1 }">${crmMassOutageMasterses.masterName }
                            <%-- ${crmMassOutageMasterses.nasPortId } ${crmMassOutageMasterses.poolName } --%>
                          </li>
                        </c:forEach>
                      </ul>
                    </logic:notEmpty>
                  </li>
                </c:forEach>
              </ul>
            </div>
          </c:if>
          <%-- RF Mass Outage --%>
          <c:if test="${massOutageForm.massOutagePojo.serviceName eq 'RF'}">
            <div class="rfOutage margintop20 clear floatl">
              <ul id="rfOutageTree" class="floatl clear">
                <c:forEach items="${massOutageForm.massOutagePojo.crmMassOutagePartners }" var="crmMassOutagePartners" varStatus="npRf">
                  <li data-name="massOutagePojo.crmMassOutagePartners[${npRf.index }].enabled" data-value="on"
                    data-checked="${crmMassOutagePartners.enabled ? 1 : 0 }"
                    <%-- data-onclick="${crmMassOutagePartners.enabled ? '' : 'return false;' }" --%>
                    data-disabled="${crmMassOutagePartners.enabled ? 0 : 1 }">${crmMassOutagePartners.partnerName }<logic:notEmpty
                      property="crmMassOutageMasterses" name="crmMassOutagePartners">
                      <ul>
                        <c:forEach items="${crmMassOutagePartners.crmMassOutageMasterses }" var="crmMassOutageMasterses" varStatus="npRfMaster">
                          <li data-name="massOutagePojo.crmMassOutagePartners[${npRf.index }].crmMassOutageMasterses[${npRfMaster.index }].enabled"
                            data-value="on" data-checked="${crmMassOutageMasterses.enabled ? 1 : 0 }"
                            <%-- data-onclick="${crmMassOutageMasterses.enabled ? '' : 'return false;' }" --%>
                            data-disabled="${crmMassOutageMasterses.enabled ? 0 : 1 }">${crmMassOutageMasterses.masterName }
                            <%-- ${crmMassOutageMasterses.nasPortId } ${crmMassOutageMasterses.poolName } --%>
                          </li>
                        </c:forEach>
                      </ul>
                    </logic:notEmpty>
                  </li>
                </c:forEach>
              </ul>
            </div>
          </c:if>
          <%-- Fiber Broadband Mass Outage --%>
          <c:if test="${massOutageForm.massOutagePojo.serviceName eq 'BB'}">
            <div class="fbrOutage margintop20 clear floatl">
              <ul id="fbrOutageTree" class="floatl clear">
                <c:forEach items="${massOutageForm.massOutagePojo.crmMassOutagePartners }" var="crmMassOutagePartners" varStatus="npFbr">
                  <li data-name="massOutagePojo.crmMassOutagePartners[${npFbr.index }].enabled" data-value="on"
                    data-checked="${crmMassOutagePartners.enabled ? 1 : 0 }"
                    <%-- data-onclick="${crmMassOutagePartners.enabled ? '' : 'return false;' }" --%>
                    data-disabled="${crmMassOutagePartners.enabled ? 0 : 1 }">${crmMassOutagePartners.partnerName }
                    <ul>
                      <c:forEach items="${crmMassOutagePartners.crmMassOutageStates}" var="crmMassOutageStates" varStatus="state">
                        <li data-name="massOutagePojo.crmMassOutagePartners[${npFbr.index }].crmMassOutageStates[${state.index }].enabled"
                          data-value="on" data-checked="${crmMassOutageStates.enabled ? 1 : 0 }"
                          <%-- data-onclick="${crmMassOutageStates.enabled ? '' : 'return false;' }" --%>
                          data-disabled="${crmMassOutageStates.enabled ? 0 : 1 }">${state.index + 1 }:${crmMassOutageStates.stateName }
                          <ul>
                            <c:forEach items="${crmMassOutageStates.crmMassOutageCities }" var="crmMassOutageCities" varStatus="city">
                              <li
                                data-name="massOutagePojo.crmMassOutagePartners[${npFbr.index }].crmMassOutageStates[${state.index }].crmMassOutageCities[${city.index }].enabled"
                                data-value="on" data-checked="${crmMassOutageCities.enabled ? 1 : 0 }"
                                <%-- data-onclick="${crmMassOutageCities.enabled ? '' : 'return false;' }" --%>
                                data-disabled="${crmMassOutageCities.enabled ? 0 : 1 }">${state.index + 1 }.${city.index + 1}:${crmMassOutageCities.cityName }
                                <ul>
                                  <c:forEach items="${crmMassOutageCities.crmMassOutageAreas }" var="crmMassOutageAreas" varStatus="area">
                                    <li
                                      data-name="massOutagePojo.crmMassOutagePartners[${npFbr.index }].crmMassOutageStates[${state.index }].crmMassOutageCities[${city.index }].crmMassOutageAreas[${area.index }].enabled"
                                      data-value="on" data-checked="${crmMassOutageAreas.enabled ? 1 : 0 }"
                                      <%-- data-onclick="${crmMassOutageAreas.enabled ? '' : 'return false;' }" --%>
                                      data-disabled="${crmMassOutageAreas.enabled ? 0 : 1 }">${state.index + 1 }.${city.index + 1}.${area.index + 1}:${crmMassOutageAreas.area }
                                      <ul>
                                        <%-- <c:forEach items="${crmMassOutageAreas.crmMassOutageLocalities }" var="crmMassOutageLocalities"
                                          varStatus="locality">
                                          <li
                                            data-name="massOutagePojo.crmMassOutagePartners[${npFbr.index }].crmMassOutageStates[${state.index }].crmMassOutageCities[${city.index }].crmMassOutageAreas[${area.index }].crmMassOutageLocalities[${locality.index }].enabled"
                                            data-value="on" data-checked="${crmMassOutageLocalities.enabled ? 1 : 0 }"
                                            data-onclick="${crmMassOutageLocalities.enabled ? '' : 'return false;' }"
                                            data-disabled="${crmMassOutageLocalities.enabled ? 0 : 1 }">${state.index + 1 }.${city.index + 1}.${area.index + 1}.${locality.index + 1}:
                                            ${crmMassOutageLocalities.locality }
                                            <ul> --%>
                                              <c:forEach items="${crmMassOutageAreas.crmMassOutageSocietyPojos }" var="crmMassOutageSocietyPojos"
                                                varStatus="society">
                                                <li
                                                  data-name="massOutagePojo.crmMassOutagePartners[${npFbr.index }].crmMassOutageStates[${state.index }].crmMassOutageCities[${city.index }].crmMassOutageAreas[${area.index }].crmMassOutageSocietyPojos[${society.index }].enabled"
                                                  data-value="on" data-checked="${crmMassOutageSocietyPojos.enabled ? 1 : 0}"
                                                  <%-- data-onclick="${crmMassOutageSocietyPojos.enabled ? '' : 'return false;' }" --%>
                                                  data-disabled="${crmMassOutageSocietyPojos.enabled ? 0 : 1 }">${state.index + 1 }.${city.index + 1}.${area.index + 1}.${society.index + 1}:
                                                  ${crmMassOutageSocietyPojos.society }</li>
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
              </ul>
            </div>
          </c:if>
        </div>
        <div class="floatr inner_right" style="top: 20px;">
          <a href="#" id="submitUpdateMassOutage" class="main_button"><span>Update</span></a>
        </div>
        <p class="clear"></p>
      </html:form>
      <!-- <p class="clear" style="height: 250px;"></p> -->
    </div>
  </div>
</div>
<p class="clear"></p>