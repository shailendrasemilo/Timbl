<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib uri="/WEB-INF/tlds/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/tlds/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/tlds/displaytag.tld" prefix="display"%>
<div class="LmsTable marginRight10 paddingBottom30">
    <h4>
      Customer Activity Log Details <span class="lmsMinusImage floatr"></span>
    </h4>
    <div class="viewLmsTable margintop10">
      <iframe src="manageQrc.do?method=getActivityLog" scrolling="yes" frameborder="0"
    	style="border: none; overflow: hidden;width:100%;" allowTransparency="true" onload="iframeLoaded(this)"></iframe>
    </div>
</div>

