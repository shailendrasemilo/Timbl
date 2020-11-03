<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<%--
<link href="css/main.css" type="text/css" rel="stylesheet" />
<link href="css/jquery.tooltip.css" rel="stylesheet" />
<link href="css/style.css" type="text/css" rel="stylesheet" />
<link href="css/TariffsStylesheet.css" rel="stylesheet" type="text/css" />
 --%>
<link href="css/myaccount-common.css" type="text/css" rel="stylesheet" />
<link href="css/popup-common.css" type="text/css" rel="stylesheet" />
<link href="css/color-${initParam.client}.css" type="text/css" rel="stylesheet" />
<!-- =============== fancy box =====================-->
<link rel="stylesheet" type="text/css" href="css/jquery.fancybox.css" />
<script type="text/javascript" src="javascript/jquery-1.10.2.min.js"></script>
<script type="text/javascript" src="javascript/jquery.fancybox.pack.js"></script>
<script type="text/javascript" src="javascript/fancybox-custom.js"></script>
<script type="text/javascript" src="javascript/selfcare.js"></script>
<!-- =============== data tables =====================-->
<link rel="stylesheet" type="text/css" href="css/jquery.dataTables.css" />
<script type="text/javascript" src="javascript/jquery.dataTables.min.js"></script>
<script type="text/javascript">
	$( document ).ready(
			function(){
				$( '#ticketHistory' ).DataTable(
						{
							"ordering" : false, "searching" : false, "iDisplayLength" : 15, "bLengthChange" : false, "bScrollCollapse" : false,
							"bInfo" : true, "pagingType" : "simple", retrieve : true, "language" : {
								"emptyTable" : "No record found"
							},
						} );

			} );
</script>
</head>
<body>
  <div class="mainWrapper loginWrap">
    <div class="titleBar">
      <span class="pageTitle">Ticket History</span> <a id="closeBtn" onclick="parent.jQuery.fancybox.close();"></a>
    </div>
    <div class="ui-corner-bottom paymentBill-table" style="width: 100%; border: none; position: relative; padding-bottom: 5px;">
      <table cellpadding="0" cellspacing="0" width="100%" border="0" style="text-align: left;" id="ticketHistory">
        <thead>
          <tr>
            <th>Date</th>
            <th>Nature</th>
            <th>Category</th>
            <th>Ticket No.</th>
          </tr>
        </thead>
        <tbody>
          <c:forEach items="${ selfcareForm.nonOpenTicketsPojos }" var="nonOpenTicketsPojos">
            <tr>
              <td><bean:write name="selfcareUtils" property="toDate(${nonOpenTicketsPojos.createdTime})" /></td>
              <td>${ nonOpenTicketsPojos.qrcType eq 'R' ? 'Request' : (openTicketsPojos.qrcType eq 'Q' ? 'Query' : 'Complaint') }</td>
              <td>${ nonOpenTicketsPojos.qrcCategory }</td>
              <td>${ nonOpenTicketsPojos.srId }</td>
            </tr>
          </c:forEach>
        </tbody>
      </table>
    </div>
  </div>
</body>
</html>
