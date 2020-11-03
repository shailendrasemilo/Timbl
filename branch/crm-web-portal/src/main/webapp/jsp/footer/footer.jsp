<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>

<div id="footer" class="clear">
 <p class="footer">
  <bean:message bundle="userProp" key="copyRightName"/><%--<a href="#">Helpdesk</a> <a href="#">Privacy Policy</a> <a href="#">Terms &amp; conditions</a> --%>
 </p>
</div>
<script>
$('.pagelinks').find('a').click(function(){
	$( '.loadingPopup' ).removeClass( 'hidden' );
});
</script>