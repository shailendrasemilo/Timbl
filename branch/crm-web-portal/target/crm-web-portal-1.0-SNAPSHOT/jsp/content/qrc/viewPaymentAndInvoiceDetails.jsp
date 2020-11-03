<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib uri="/WEB-INF/tlds/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/tlds/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/tlds/displaytag.tld" prefix="display"%>
<%@taglib uri="/WEB-INF/tlds/struts-html.tld" prefix="html"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<script src="dwr/engine.js" type="text/javascript"></script>
<script src="dwr/util.js" type="text/javascript"></script>
<script src="dwr/interface/crmDwr.js" type="text/javascript"></script>
<script type="text/javascript" src="javascript/jquery-1.8.1.js"></script>
<script type="text/javascript" src="javascript/jquery.dataTables.min.js"></script>
<script type="text/javascript" src="javascript/jquery.dataTables.custom.js"></script>
<script type="text/javascript" src="javascript/qrc.js"></script>
<link href="css/jquery.dataTables.css" rel="stylesheet" media="screen" />
<link href="css/color-${initParam.client }.css" rel="stylesheet" />
<link href="css/qrc.css" rel="stylesheet" />
<link href="css/gis.css" rel="stylesheet" />
<div class="loadingPopup hidden"></div>
<div class="section">
  <div class="inner_section ">
    <div class=" floatl qrcLeft">
      <html:form action="/manageQrc" styleId="qrcCustomerProfileForm">
        <div class="success_message">
          <logic:messagesPresent message="true">
            <html:messages id="message" message="true">
              <bean:write name="message" />
            </html:messages>
          </logic:messagesPresent>
        </div>
        <div style="color: #E70000 !important; font: 11px arial; font-weight: bold;" id="error">
          <html:errors />
        </div>
        <div class="relative inner_left_lead">
          <ul class="clear customerAddress noMargin noPadding">
            <li class="table_container bgWhite"><span style="padding: 0px; width: 40% !important; height: 321px;"> <font class="table_header"
                style="display: block;"> <span>Payment History</span>
              </font> <logic:notEmpty name="qrcForm" property="custPaymentDetailsPojos">
                  <table id="viewPaymentHistory">
                    <thead>
                      <tr>
                        <th class="col-date">DATE</th>
                        <th class="col-amount">AMOUNT</th>
                        <th class="col-mode">MODE</th>
                        <th class="col-trans">TRANS&nbsp;ID</th>
                        <th class="col-mode">STATUS</th>
                        <th class="col-date">BOUNCING&nbsp;DATE</th>
                        <th class="col-date">POSTING&nbsp;DATE</th>
                        <th class="col-date">REVERSAL&nbsp;DATE</th>
                      </tr>
                    </thead>
                    <tbody>
                      <c:forEach items="${qrcForm.custPaymentDetailsPojos}" var="viewPaymentHistory">
                        <tr>
                          <td class="col-date"><span><bean:write name="crmRoles" property="toDate(${viewPaymentHistory.paymentDate})" /></span></td>
                          <td class="padding2lr"><span>${viewPaymentHistory.amount }</span></td>
                          <td class="padding2lr"><span><bean:write name="crmRoles"
                                property="displayEnum(PaymentMode,${viewPaymentHistory.paymentMode})" /></span></td>
                          <logic:notEmpty name="viewPaymentHistory" property="receiptNo">
                            <c:set var="transId" value="${viewPaymentHistory.receiptNo}"></c:set>
                          </logic:notEmpty>
                          <logic:notEmpty name="viewPaymentHistory" property="chequeDdNo">
                            <c:set var="transId" value="${viewPaymentHistory.chequeDdNo}"></c:set>
                          </logic:notEmpty>
                          <logic:notEmpty name="viewPaymentHistory" property="transactionId">
                            <c:set var="transId" value="${viewPaymentHistory.transactionId}"></c:set>
                          </logic:notEmpty>
                          <td class="col-trans" style="max-width: 100px; overflow: hidden; text-overflow: ellipsis;" title="${transId}">${transId}</td>
                          <td class="col-mode"><logic:notEmpty name="viewPaymentHistory" property="status">
                              <logic:equal name="viewPaymentHistory" property="status" value="PP">Posted</logic:equal>
                              <logic:equal name="viewPaymentHistory" property="status" value="PR">Reversed</logic:equal>
                              <logic:equal name="viewPaymentHistory" property="status" value="A">Pending</logic:equal>
                            </logic:notEmpty></td>
                          <td class="col-date"><span><bean:write name="crmRoles" property="toDate(${viewPaymentHistory.bouncingDate})" /></span></td>
                          <td class="col-date"><span><bean:write name="crmRoles" property="toDate(${viewPaymentHistory.postingDate})" /></span></td>
                          <td class="col-date"><span><bean:write name="crmRoles" property="xmlDate(${viewPaymentHistory.reversalDate})" /></span></td>
                        </tr>
                      </c:forEach>
                    </tbody>
                  </table>
                </logic:notEmpty>
            </span> <span style="padding: 0px; width: 450px; border-right: none;"> <font class="table_header" style="display: block;"> <span>Invoice
                    History</span>
              </font>
                <table id="invoiceDetailsId">
                  <thead>
                    <tr>
                      <th class="col-inv">INV NO</th>
                      <th class="col-common">BILL&nbsp;DATE</th>
                      <th class="col-common">PERIOD</th>
                      <th class="col-due">DUE&nbsp;AMT</th>
                      <c:if test="${ qrcForm.custDetailsPojo.brand  ne 'INITIA'}">
                        <th class="col-common">SEND&nbsp;E-Bill</th>
                      </c:if>
                      <th class="col-common">USAGE</th>
                    </tr>
                  </thead>
                  <tbody>
                    <logic:notEmpty name="qrcForm" property="invoiceList">
                      <c:forEach items="${qrcForm.invoiceList}" var="invoiceDetails">
                        <tr>
                          <td class="col-inv"><span> <a href="#"
                              onclick="window.open( '${invoiceDetails.nopasswordPdfFile}', 'newWindow', 'width='+parent.innerWidth+',height='+parent.innerHeight+',scrollbars=yes,resizable=no,toolbar=no');">${invoiceDetails.billNumber}</a>
                          </span></td>
                          <td class="col-common"><span><bean:write name="crmRoles" property="toDate(${invoiceDetails.billDate})" /></span></td>
                          <td class="col-common"><span class="period_span" title="${invoiceDetails.billPeriod}">${invoiceDetails.billPeriod}</span></td>
                          <td class="col-common"><span>${invoiceDetails.billAmount}</span></td>
                          <c:if test="${ qrcForm.custDetailsPojo.brand  ne 'INITIA'}">
                            <td class="col-common"><span title="Send E-Bill Details"> <a
                                href="javascript:sendEbill('${invoiceDetails.billNumber}');" class="send-mail small-bg"></a>
                            </span></td>
                          </c:if>
                          <td class="col-common"><span title="Send Usages Details"><a
                              href="javascript:sendCustomerUsage('Billed','${invoiceDetails.billPeriod}');" class="send-mail small-bg"></a></span></td>
                        </tr>
                      </c:forEach>
                    </logic:notEmpty>
                  </tbody>
                </table>
            </span></li>
          </ul>
        </div>
      </html:form>
    </div>
  </div>
</div>
