<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="/WEB-INF/tlds/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/tlds/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/tlds/displaytag.tld" prefix="display"%>
<%@taglib uri="/WEB-INF/tlds/struts-html.tld" prefix="html"%> 
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%--    code from qrcMenu.jsp    --%>
<div id="section" >
<form action=""  method="post" id="logQRCBarring">
 <div class="section">
 <h2>Log QRC</h2>
   <div class="inner_section ">
 	<div class="inner_left_lead floatl  qrcLeft">
     <bean:define id="activeMenu" value="collectedPaymentPost"></bean:define>
        <%@include file="qrcMenu.jsp"%>
    </div>
<%--    end code from qrcMenu.jsp    --%>

   <div class=" floatl manageGISRight qrcRight">
   <h4> Collected Payment Post</h4>
   <a href="collected_payment_data.html" class="floatr blue_button">Click to know Collected Payment Data</a>
   
    <div class=" relative inner_left_lead">
    <div class="">
     
       <p class="floatl">
      <strong>Payment Type</strong>
      <LABEL class="label_radio" for="portConfigurationYes"><INPUT name="portConfiguration" id="portConfigurationYes" type="radio" value="display_masterData"> Upload File  </LABEL> 
     <LABEL class="label_radio" for="portConfigurationNo"><INPUT name="portConfiguration" id="portConfigurationNo" type="radio" value="display_societyData" > Post Payment  </LABEL>
     </p>
     
     
      <p class=" floatl clear" >
       <span class="importISR">
              <input class="importInputFile " name="fileUpload" type="file" onchange="javascript:importTemplate(event,'emailTemplateBody','E')">
              <a href="#" id="" class=" yellow_button marginleft6 importLink">Browse</a> </span> 
             </p>
       
      <br class="clear" />
     </div>

    </div>
    
    
    <h4 class="">Payment Details</h4>
     <div class=" inner_left_lead relative">
    <div class="floatl">
     
       
  <p class="floatl clear"><strong> Select Vendor </strong>
           <span class="LmsdropdownWithoutJs">
     <select  class="" name="creditCardType">
      <option value="0">Select Vendor </option>
      <option >Gold</option>
      <option >Platinum</option>
     </select>
     </span>
     </p>
     <p class="floatl marginleft30"><strong> Select Inst Type </strong>
           <span class="LmsdropdownWithoutJs">
     <select  class="" name="creditCardType">
      <option value="0">Select Inst Type </option>
      <option >Gold</option>
      <option >Platinum</option>
     </select>
     </span>
     </p>
     <p class="floatl clear"><strong> Select Company Name </strong>
           <span class="LmsdropdownWithoutJs">
     <select  class="" name="creditCardType">
      <option value="0">Select Company Name </option>
      <option >Gold</option>
      <option >Platinum</option>
     </select>
     </span>
     </p>
     <p class="floatl marginleft30"><strong> Select  Payment Head </strong>
           <span class="LmsdropdownWithoutJs">
     <select  class="" name="creditCardType">
      <option value="0">Select Category </option>
      <option >Gold</option>
      <option >Platinum</option>
     </select>
     </span>
     </p>
       <p class="floatl clear ">
            <strong>Amount</strong>
           <input type="text"  class="Lmstextbox" readonly="readonly"/>
       </p>
       <p class="floatl marginleft30 ">
            <strong>Receipt No.</strong>
           <input type="text"  class="Lmstextbox" readonly="readonly"/>
       </p>
       
        <p class="floatl clear "><strong> Inst Date</strong>
                <input type="text"  class="tcal tcalInput" name="" id=""  readonly="readonly" />
              </p>
              <p class="floatl marginleft30"><strong> Post Date</strong>
                <input type="text"  class="tcal tcalInput" name="" id=""  readonly="readonly" />
              </p>
      
 
    
      <br class="clear" />
     </div>
     
     <div class="floatr inner_right">
    <p class="buttonPlacement">
     <a href="#" id="customerBasicInfo_Save" class="main_button  "><span>Add Plan</span></a>
     
     </p>
   </div>
   <br class="clear" />
    </div>
    
    </div>
    <!-- end -->
    <p class="clear"></p>
