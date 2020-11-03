<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<%@taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<%@taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div class="loadingPopup hidden"></div>
<div id="section">
  <html:form action="/manageQrc" method="post" styleId="IDsearchMassOutage">
    <div class="section">
      <h2>
        Search Mass Outage
        <c:if test="${crmRoles.getAvailable('create_qrc_mo')}">
          <a href="massOutage.do?method=addMassOutagePage" class="yellow_button floatr margintop7 ">Add Mass Outage</a>
        </c:if>
      </h2>
      <div class="success_message" id="msgDiv">
        <logic:messagesPresent message="true">
          <html:messages id="message" message="true">
            <bean:write name="message" />
          </html:messages>
        </logic:messagesPresent>
      </div>
      <div class="error_message" id="error">
        <html:errors property="appError" />
      </div>
      <div class="inner_section">
        <div class="inner_left floatl INA">
          <p class="floatl clear">
            <strong>Select Outage Type</strong> <label class=" marginleft10"> <html:radio property="massOutagePojo.outageType" value="Planned"
                name="massOutageForm" styleId="">Planned</html:radio>
            </label> <label class=" marginleft10 " style="margin-top: 8px;"> <html:radio property="massOutagePojo.outageType" value="Unplanned"
                name="massOutageForm" styleId="">Unplanned</html:radio>
            </label> <font></font>
          </p>
          <p class="floatl clear">
            <strong>Select Service Name</strong>
            <logic:iterate id="productTypeList" name="massOutageForm" property="productTypeList" indexId="ctr">
              <label class=" marginleft10"><html:radio name="massOutageForm" styleId="product${productTypeList.contentValue}"
                  property="massOutagePojo.serviceName" value="${productTypeList.contentValue}" onclick="getPartnerByType(); showHideDivOutage();">
                  <bean:write name="crmRoles" property="displayEnum(Product,${productTypeList.contentValue})" scope="session" />
                </html:radio></label>
            </logic:iterate>
          </p>
          <div id="strDatediv">
            <p class="floatl clear">
              <strong>Start Date</strong>
              <html:hidden name="massOutageForm" property="massOutagePojo.displayOutageStartTime" styleId="strDateTimeID" />
              <input type="text" class="tcal" id="strDateID" /> <font></font>
            </p>
            <p class="floatl marginleft30 ">
              <strong>Start Time (HH:MM:SS 24 hour format)</strong> <font style="border: 1px solid #AFAFAF; padding: 5px 5px 5px 5px;"> <select
                id="strHourID">
                  <%
                      for ( int i = 0; i < 24; i++ )
                          {
                  %>
                  <option value="<%=i%>"><%=String.format( "%02d", i )%></option>
                  <%
                      }
                  %>
              </select> <select id="strMinuteID">
                  <%
                      for ( int i = 0; i < 60; i++ )
                          {
                  %>
                  <option value="<%=i%>"><%=String.format( "%02d", i )%></option>
                  <%
                      }
                  %>
              </select> <select id="strSecondID">
                  <%
                      for ( int i = 0; i < 60; i++ )
                          {
                  %>
                  <option value="<%=i%>"><%=String.format( "%02d", i )%></option>
                  <%
                      }
                  %>
              </select>
              </font> <font></font>
            </p>
          </div>
          <div id="etrDatediv">
            <p class="floatl clear">
              <strong>ETR Date</strong>
              <html:hidden name="massOutageForm" property="massOutagePojo.displayOutageEtrTime" styleId="etrDateTimeID" />
              <input type="text" class="tcal" id="etrDateID" /> <font></font>
            </p>
            <p class="floatl marginleft30">
              <strong>ETR Time (HH:MM:SS 24 hour format)</strong> <font style="border: 1px solid #AFAFAF; padding: 5px 5px 5px 5px;"> <select
                id="etrHourID">
                  <%
                      for ( int i = 0; i < 24; i++ )
                          {
                  %>
                  <option value="<%=i%>"><%=String.format( "%02d", i )%></option>
                  <%
                      }
                  %>
              </select> <select id="etrMinuteID">
                  <%
                      for ( int i = 0; i < 60; i++ )
                          {
                  %>
                  <option value="<%=i%>"><%=String.format( "%02d", i )%></option>
                  <%
                      }
                  %>
              </select> <select id="etrSecondID">
                  <%
                      for ( int i = 0; i < 60; i++ )
                          {
                  %>
                  <option value="<%=i%>"><%=String.format( "%02d", i )%></option>
                  <%
                      }
                  %>
              </select>
              </font> <font></font>
            </p>

          </div>
          <%--	
			 <p  class="floatl clear"><strong>Network Partner</strong>
    <span class="LmsdropdownWithoutJs"> 
       <html:select  name="massOutageForm" property="networkPartner" styleId="networkPartner" onchange="getMasterList(this.value)">
		 
		 
	 </html:select>
	</span>
    </p>
	<p  class="floatl marginleft30 hidden" id="nasportDiv"><strong>Master-NasPortId-Pool Name</strong>
    <span class="dropdownWithoutJs" style="width:600px"> 
       <html:select  name="massOutageForm" property="nassPortId" styleId="nassPortId" >
		  
		  
	 </html:select>
	</span>
    </p>
 
 

     

    <div id="gisDiv" class="hidden">
    <p  class="floatl clear"><strong>State</strong>
    <span class="LmsdropdownWithoutJs"> 
       <html:select property="state" styleId="partnerStateId" value='${massOutageForm.statePojos}' onchange="javascript:populateCityByState('partnerCityId',this.value);">
		  <html:option value="">All States</html:option>
		  <logic:notEmpty name="massOutageForm" property="statePojos">
			 <html:optionsCollection name="massOutageForm" property="statePojos" label="stateName" value="stateName" />
			</logic:notEmpty>
	 </html:select>
	</span>
    </p>
     <p  class="floatl marginleft30"><strong>City</strong>
    <span class="LmsdropdownWithoutJs"> 
       <html:select property="city" styleId="partnerCityId" value='${massOutageForm.cityPojoList}'  onchange="javascript:populateAreaByCity('partnerAreaId','partnerStateId',this.value);" >
		  <html:option value="">All City</html:option>
		 
	 </html:select>
	</span>
    </p>
	
	 <p  class="floatl marginleft30"><strong>Area</strong>
    <span class="LmsdropdownWithoutJs"> 
       <html:select property="area" styleId="partnerAreaId" value='${massOutageForm.cityPojoList}'
	   onchange="javascript:populateLocalityByArea('partnerLocalityId','partnerStateId','partnerCityId',this.value)"
	   >
		  <html:option value="">All Area</html:option>
		  
	 </html:select>
	</span>
    </p>
	 <p  class="floatl clear"><strong>Locality</strong>
    <span class="LmsdropdownWithoutJs"> 
       <html:select property="locality" styleId="partnerLocalityId" value='${massOutageForm.localityList}' onchange="javascript:populateFeasibleSocieties('partnerSocietyId','partnerStateId','partnerCityId','partnerAreaId',this.value,'product');">
		  <html:option value="">All Locality</html:option>
		  
	 </html:select>
	</span>
    </p>
	<p  class="floatl marginleft30"><strong>Society</strong>
    <span class="LmsdropdownWithoutJs"> 
       <html:select property="locality" styleId="partnerSocietyId" value='${massOutageForm.societyList}'>
		  <html:option value="">All Society</html:option>
		  
	 </html:select>
	</span>
    </p>
		</div>	--%>

        </div>
        <div class="floatr inner_right">
          <logic:equal name="crmRoles" property="available(view_qrc_mo)" value="true" scope="session">
            <a href="#" id="searchMassOutage" class="main_button"><span>Search</span></a>
          </logic:equal>
        </div>
        <p class="clear"></p>
      </div>
      <div id="idDisplayTable">
        <logic:notEmpty name="massOutageForm" property="massOutagePojos">
          <h4>Mass Outage List</h4>
          <display:table id="data" name="sessionScope.massOutageForm.massOutagePojos" requestURI="" class="dataTable" style="width:100%" pagesize="15">
            <display:setProperty name="paging.banner.placement" value="bottom" />
            <display:setProperty name="mymedia" value="true"></display:setProperty>
            <display:setProperty name="export.excel.filename" value="massOutage.xls" />
            <display:setProperty name="export.csv.filename" value="massOutage.csv" />
            <display:column title="Sr.No.">
              <bean:write name="data_rowNum" />
            </display:column>
            <display:column title="Outage Type" property="outageType">
            </display:column>
            <display:column title="Service Name">
              <bean:write name="crmRoles" property="displayEnum(Product,${data.serviceName})" scope="session" />
            </display:column>
            <display:column title="Outage Level" property="outageLevel" />
            <display:column title="Start Time">
              <bean:write name="crmRoles" property="xmlDate(${data.outageStartTime})" />
            </display:column>
            <display:column title="ETR Time">
              <bean:write name="crmRoles" property="xmlDate(${data.outageEtrTime})" />
            </display:column>
            <display:column title="Reason" property="reason" />
            <display:column title="Remarks" property="remarks" style="width:200px ;word-wrap: break-word; word-break: break-all;"/>
            <display:column title="Resolution Time">
              <bean:write name="crmRoles" property="xmlDate(${data.resolutionTime})" />
            </display:column>
            <display:column title="Status">
              <bean:write name="crmRoles" property="displayEnum(AllStatus,${data.status})" scope="session" />
            </display:column>

            <display:column title="Action" media="html">
              <c:if test="${not crmRoles.getAvailable('update_qrc_mo') or data.status eq 'R'}">-</c:if>
              <c:if test="${crmRoles.getAvailable('update_qrc_mo') and data.status eq 'A'}">
                <a
                  onclick="if(confirm('Do you want to edit this Mass Outage?'))this.href='massOutage.do?method=editMassOutagePage&oid=${data.outageId }'"
                  href="#" class="action">Edit</a>
                <br />
                <a
                  onclick="if(confirm('Are you sure to Resolve this Mass Outage?'))this.href='massOutage.do?method=resolveMassOutagePage&oid=${data.outageId }';"
                  href="#" class="action">Resolve</a>
              </c:if>
            </display:column>

            <display:column title="Created By" property="createdBy" style="width:100px; word-wrap: break-word; word-break: break-all;" />
            <display:column title="Created Date" style="width:100px; word-wrap: break-word; word-break: break-all;">
              <bean:write name="crmRoles" property="toDate(${data.createdTime})" scope="session" />
            </display:column>
            <display:column title="Resolved By" style="width:80px">
			<span style="word-wrap: break-word; word-break: break-all; ">
				<c:if test="${data.status ne 'R'}">-</c:if>
				<c:if test="${data.status eq 'R'}"><bean:write name="data" property="lastModifiedBy"/></c:if>
				</span>
			</display:column>
          </display:table>
        </logic:notEmpty>
        <logic:empty name="massOutageForm" property="massOutagePojos">
          <b><html:errors property="noRecordFound" /></b>
        </logic:empty>
      </div>
    </div>
  </html:form>
</div>
