<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<%@taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<%@taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<head>
<script src="dwr/engine.js" type="text/javascript"></script>
<script src="dwr/util.js" type="text/javascript"></script>
<script src="dwr/interface/crmDwr.js" type="text/javascript"></script>
	<link href="css/colorbox.css" rel="stylesheet" />
<script src="javascript/jquery.colorbox.js"></script>
<script>
	$( document ).ready(function(){
		$(".rectifyRecords").colorbox({iframe:true, width:"750px", height:"320px"});
	});
</script>
</head>
<div id="section">
  <html:form action="/refund" styleId="searchPOD">
   
    <div class="section">
      <h2>Suspense/Rejected Records Details</h2>
      <span class="error_message"> <html:errors property="appError" />
      </span> <span class="success_message"> <logic:messagesPresent message="true" property="appMessage">
          <html:messages id="msg" message="true" property="appMessage">
            <bean:write name="msg" />
          </html:messages>
        </logic:messagesPresent>
      </span>
      <div class="inner_section">
        <div class="inner_left_lead floatl">
          <p class="floatl clear">
            <strong> From Date<span class="error_message verticalalignTop">*</span></strong>
            <html:text property="fromDate" name="financeForm" styleClass="tcal tcalInput" readonly="true" styleId="fromDate_s" onchange="getFileDetails();"></html:text>
          </p>
          <p class="floatl marginleft30">
            <strong> To Date<span class="error_message verticalalignTop">*</span></strong>
            <html:text property="toDate" name="financeForm" styleClass="tcal tcalInput" readonly="true" styleId="toDate_s" onchange="getFileDetails();"></html:text>
          </p>
          
          <p class="floatl  clear">
            <strong>Record Type</strong> <span class="LmsdropdownWithoutJs"> <html:select property="podStatus">
                <html:option value="">All</html:option>
                <logic:notEmpty name="financeForm" property="fileStatusList">
                  <html:optionsCollection name="financeForm" property="fileStatusList" label="contentName" value="contentValue" />
                </logic:notEmpty>
              </html:select>
            </span>
          </p>
        </div>
        <div class="floatr inner_right">
          <a href="#" id="submit_searchPOD" class="main_button"><span>Search</span></a>
        </div>
        <p class="clear"></p>
      </div>
      <div>
        
      </div>
      <logic:notEmpty name="financeForm" property="crmPodDetailsPojos">
          <display:table id="data" name="sessionScope.financeForm.crmPodDetailsPojos" requestURI="" class="dataTable" style="width:100%" pagesize="15">
            <display:setProperty name="paging.banner.placement" value="bottom" />
            <display:setProperty name="mymedia" value="true"></display:setProperty>
            <display:setProperty name="export.excel.filename" value="SuspenseRejected-Detail.xls" />
            <display:setProperty name="export.csv.filename" value="SuspenseRejected-Detail.csv" />
            <display:column title="Sr.No.">
              <bean:write name="data_rowNum" />
            </display:column>
			<display:column title="Customer ID">
             <logic:equal name="data" property="status" value="SP">
                <span ondblclick="customeridShow('${data.podId}');" class="linkCustomerId" id="${data.podId}"> <bean:write name="data" property="customerId" />
                  <span class="toolTip">Double click to rectify</span>
                </span>
				 <p class="hide1" id="customeridShow${data.podId}">
                  <html:text styleClass="Cmstextbox" name="financeForm" property="newCustomerId" value="${data.customerId}"
                    onblur="javascript:suspenseRejectedPODRecords(this.value,'${data.podId}','${data.customerId}');" styleId="editedCustId" />
                  <a href="javascript:customerIdHide('${data.podId}');" class="closeBtn">x</a>
                </p>
                </logic:equal>
              <logic:notEqual name="data" property="status" value="SP">
                <bean:write name="data" property="customerId" />
              </logic:notEqual>
			  </display:column>
             <display:column title="Reciever Name" property="receiverName"/>
             <display:column title="Customer Relation" property="customerRelation"/>
           
			  
			  <display:column title="Deliver Date  " sortable="false">
							<bean:write name="crmRoles" property="toDate(${data.deliveredDate})"
								scope="session" />
						</display:column>
               <display:column title="Status  " sortable="false">
							<bean:write name="crmRoles"
								property="displayEnum(AllStatus,${ data.status})"
								scope="session" />
						</display:column>
			    <display:column title="Rectify">
                <logic:equal name="data" property="status" value="RJ">
                <!--  <a href="javascript:editPodRejectedRecords('<bean:write name="data" property="customerId" />','<bean:write name="data" property="podId" />');">
                    Rectify&nbsp;Record </a>  -->
                    
                     <a href= 'refund.do?method=rectifyRecordPage&custId=${data.customerId}&Id=${data.podId}'class="rectifyRecords"> Rectify&nbsp;Record	</a>
                    
                </logic:equal>
                <logic:notEqual name="data" property="status" value="RJ">
						-
					</logic:notEqual>
              </display:column>
           
            </display:table>
            </logic:notEmpty>
    </div>
  </html:form>
</div>