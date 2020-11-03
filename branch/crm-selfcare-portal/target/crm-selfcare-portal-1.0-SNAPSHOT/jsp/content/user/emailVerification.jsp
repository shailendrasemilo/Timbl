<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
 <%@taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<%@taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<html>
<body>
<div class="clearfix ui-corner-all centerWrapper">
  <div style="margin-bottom: 5px; min-height: 400px; overflow: hidden;">
    <div style="height: 20px;"></div>
    <div style="width: 900px; margin: auto; overflow: hidden;">

<p style="margin-left: auto;  margin-right: auto; margin-top:149px; width: 15em;font-weight: bold; font-size: 15px;font-family: Verdana;">
 <logic:messagesPresent property="appError">
        <bean:define id="hasErrors" value="true"></bean:define>
      </logic:messagesPresent>
      <div class="msgerror ${ hasErrors ? '' : 'hide' }">
        <html:errors />
      </div>
      <logic:messagesPresent message="true">
        <html:messages id="message" message="true">
          <div class="msgsuccess">
            <bean:write name="message" />
          </div>
        </html:messages>
      </logic:messagesPresent>
</p>

</div>
</div>
</div>
</body>
</html>