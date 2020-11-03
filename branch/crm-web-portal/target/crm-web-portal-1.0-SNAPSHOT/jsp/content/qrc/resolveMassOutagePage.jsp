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
      Resolve Mass Outage <a href="massOutage.do?method=searchMassOutagePage" class="yellow_button floatr margintop7 ">Search Mass Outage</a>
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
      <html:form action="/massOutage.do?method=resolveMassOutage" styleId="resolveMassOutageForm">
        <div class="QRC marginleft10 floatl marginright40">
          
          <p class="floatl clear">
            <strong>Reason <sup class="req">*</sup></strong> <span class="qrcConfigDropdownWithoutJs"> <html:select
                property="massOutagePojo.reason">
                <html:option value="">Please Select</html:option>
                <html:optionsCollection name="massOutageForm" property="reasons" label="categoryValue" value="categoryValue" />
              </html:select>
            </span>
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
          <a href="#" id="submitResolveMassOutage" class="main_button"><span>Resolve</span></a>
        </div>
        <p class="clear"></p>
      </html:form>
      <!-- <p class="clear" style="height: 250px;"></p> -->
    </div>
  </div>
</div>
<p class="clear"></p>