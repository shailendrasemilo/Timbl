<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<%@ page import="com.np.tele.crm.service.client.CrmUserPojo"%>
<%@taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<%@taglib uri="http://struts.apache.org/tags-html" prefix="html"%>

<head>
<style type="text/css">
.home_section{  padding:40px 0 15px 0;  position:relative;  height:305px; background:#fff; }
.home_section h1{ font:36px RockwellRegular; color:#050505; border-bottom:1px solid #e6e6e6;}
.home_section h1 strong{ font:58px RockwellRegular; color:#173676;}
.home_section .inner_home_section{width:800px; margin:0 auto;}
.home_section .inner_home_section p{color:#898989; font:14px/20px arial; padding-top:30px;}
</style>
</head>
<body>

<div id="section" >
 <div class="home_section" style="height:400px">

<div class="inner_home_section">
<h1><strong>Welcome</strong> to <bean:message bundle="userProp" key="companyName"/> CRM</h1>
  <%--  <p >
   Nextra Broadband is an Ultra-High Speed broadband service, with the fiber reaching your neighbourhood.
</p>
<p>
So, while your conventional broadband provider struggles to offer speeds beyond 4 Mbps, Nextra offers super lightning speed plans starting from 5 Mbps and soaring all the way to 100 Mbps. Choices are available between 10 Mbps, 15 Mbps, 20 Mbps and 50 Mbps also. With super high speeds, Nextra gives you the "Bandwidth for More". Faster Speeds mean More Entertainment, More Work, More Productivity. More Time. More Fun.
   </p> --%>
   <p> Hi <bean:write name="userPojo"  property="firstName" scope="session"/>, You are not authorize to access the module. Please contact admin for the same.</p>
   </div>



 </div>
</div>
</body>
