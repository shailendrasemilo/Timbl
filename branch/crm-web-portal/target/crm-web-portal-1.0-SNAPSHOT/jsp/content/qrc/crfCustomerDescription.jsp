<%@taglib uri="/WEB-INF/tlds/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/tlds/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/tlds/displaytag.tld" prefix="display"%>
<%@taglib uri="/WEB-INF/tlds/struts-html.tld" prefix="html"%>
<h4>
  CAF ID:&nbsp;<a href="javascript:viewCRF('<bean:write name="qrcForm" property="custDetailsPojo.recordId"/>',0,'customerProfile')"
    title="View CAF Details" style="text-decoration: underline; color: #173676;"><bean:write name="qrcForm" property="custDetailsPojo.crfId" /></a> |&nbsp;Customer
  ID:&nbsp; <a href="manageQrc.do?method=searchCustomer&workingId=<bean:write name='qrcForm' property='custDetailsPojo.customerId'/>"
    title="View Customer Details" style="text-decoration: underline; color: #173676;"><bean:write name="qrcForm"
      property="custDetailsPojo.customerId" /></a>
</h4>