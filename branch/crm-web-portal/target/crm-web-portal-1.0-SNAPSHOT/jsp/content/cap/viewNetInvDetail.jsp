<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<%@taglib uri="/WEB-INF/tlds/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/tlds/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/tlds/displaytag.tld" prefix="display"%>
<%@taglib uri="/WEB-INF/tlds/struts-html.tld" prefix="html"%>
<html>
<head>
<link href="css/common.css" rel="stylesheet" media="screen" />
<link href="css/ina.css" rel="stylesheet" media="screen" />
<link href="css/masterdata.css" rel="stylesheet" media="screen" />
</head>
<body>
<div id="popupSection" >
<html:form action="" styleId="networkInventoryDetail"  method="post">
 <div class="popupSection">
   <h2>Network Inventory Detail</h2>
   <div class="inner_section">
   <div class="inner_left_lead ">
   
   <div class="floatl">
   <h4>Actions</h4>
       <p><strong>Service Model</strong>
     <LABEL>FTTH Model</LABEL> 
   </p>
   </div>
   
    <div class=" floatl marginleft30">
     <h4 class="">ONT Details</h4>
     <p  ><strong>ONT Port</strong>  
   <LABEL>FTTH Model</LABEL> 
     </p>
     </div>
     
     <h4 class="paddingTop10 clear">VLAN Details</h4>
     <p class="" ><strong>VLAN ID</strong>
       <label>abcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrs</label>
   </p>
     <h4 class="paddingTop10">OLT Details</h4>
     
     <p class=" floatl "  ><strong>OLT Node ID</strong>
        <label>abcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrs</label>
   </p>
    <p  class=" floatl marginleft30"><strong>OLT Slot</strong>
       <label>abcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrs</label>
   </p>
   
   <p class=" floatl clear"><strong>OLT Port</strong>
       <label>abcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrs</label>
   </p>
   <p  class=" floatl marginleft30"><strong>OLT Sub Port</strong>
       <label>abcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrs</label>
   </p>
  
   
     <h4 class="paddingTop10 clear">Service Related Information</h4>
     <p>
     <strong>Service Type</strong>  
   <label>abcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrs</label>
     </p>
       </div>
</div>
 </div>
 </html:form>
</div>
</body>
</html>
