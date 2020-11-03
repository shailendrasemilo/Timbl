<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="/WEB-INF/tlds/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/tlds/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/tlds/struts-html.tld" prefix="html"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<logic:notEmpty name="financeForm" property="crfId">  
	<bean:define id="uploadDocProperty" name="financeForm" property="crfId"></bean:define>
</logic:notEmpty>
<div class="overlayDiv"></div>
<div class="modelPopupDiv" id="uploadDocReversalId" >
     <a href="#" id="closePopup">X</a>
     <h4>Upload Reference Document</h4>
     <logic:notEmpty name="uploadDocProperty">
            <c:url value="${initParam.dmshost }/np-document-upload/files/upload/FINANCE/${uploadDocProperty}" var="dmsUrl"></c:url>
            <%-- http://10.20.0.12:8080/suncity/JSPs/AddDoc_order.jsp?CAFNO=${uploadDocProperty} --%> 
	     	<iframe src="${dmsUrl }" scrolling="yes" frameborder="0" style="border: none; overflow: hidden; width: 755px; height: 400px;" allowTransparency="true"></iframe>
     </logic:notEmpty >    
</div>