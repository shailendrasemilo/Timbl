<%@page import="com.np.tele.crm.constants.CRMDisplayListConstants"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<%@taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@taglib uri="http://struts.apache.org/tags-nested" prefix="nested"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="/WEB-INF/tlds/displaytag.tld" prefix="display"%>
<%--<%@taglib uri="/WEB-INF/tlds/c.tld" prefix="c" %>--%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<link href="css/common.css" rel="stylesheet" media="screen" />
	<link href="css/lms.css" rel="stylesheet" media="screen" />
	<link href="css/ina.css" rel="stylesheet" media="screen" />
	<link href="css/masterdata.css" rel="stylesheet" media="screen" />
	<script src="javascript/jquery.validate.js" type="text/javascript"></script>
	<link href="css/displaytag.css" rel="stylesheet" type="text/css" />
	<link href="css/color-${initParam.client }.css" rel="stylesheet" media="screen" />
</head>

<body>
	<html:hidden property="customerDetailsPojo.crfStage" name="crmCapForm" value="${crmCapForm.customerDetailsPojo.crfStage}"/>
	<html:hidden property="customerDetailsPojo.crfPreviousStage" name="crmCapForm" value="${crmCapForm.customerDetailsPojo.crfPreviousStage}"/>
	<html:hidden property="customerDetailsPojo.recordId" name="crmCapForm" value="${crmCapForm.customerDetailsPojo.recordId}"/>
	<html:hidden property="customerDetailsPojo.status" name="crmCapForm" value="${crmCapForm.customerDetailsPojo.status}"/>
	<div id="section" align="center" class="noPaddingLR">
		<form action=""  method="post" id="createInA" name="createINA">
			<html:hidden property="crfTabId" name="crmCapForm" value="${crmCapForm.crfTabId}" styleId="tabHidden"/>
			<div class="section noPaddingLR">
				<logic:equal name="crmCapForm" property="heading" value="true">
					<h2>Installation and Activation</h2>
				</logic:equal>

				<div class="inner_section validationCRFEntry blueborder1" style="padding: 10px; margin-top: 30px;">
					<!-- <ul class="i_aTabs marginleft10 floatl">
				    	<li><a class="tabFirst">&nbsp;</a></li>
				    	<li><a href="#customerBasicInformation" class="selectedTab" > <i>1</i><span>CUSTOMER <br />Basic Information </span></a></li>
				    	<li><a href="#orderPaymentDetails"><i>2</i><span> ORDER <br />&amp; Payment Details </span></a></li>
				    	<li><a href="#documentDetails"> <i>3</i><span>DOCUMENTS <br />Details </span></a></li>
				    	<li><a href="#additionalInformation"><i>4</i><span> ADDITIONAL <br />Information </span></a></li>
				    	<li><a href="#networkConfiguration"> <i>5</i><span>Network <br />Configuration </span></a></li>
				    	<li><a href="#deviceDetails"> <i>6</i><span class="tabLast">Device <br />Details </span></a></li>
					</ul> -->
					<ul class="i_aTabs">
				   		<li><a href="#customerBasicInformation" class="active tabOne">Customer Basic Information</a></li>
				        <li><a href="#orderPaymentDetails" class="tabTwo">Order & Payment Details</a></li>
				        <li><a href="#documentDetails"class="tabThree">Document Details</a></li>
				        <li><a href="#additionalInformation" class="tabFour">Additional Information</a></li>
				        <li><a href="#networkConfiguration">Network Configuration</a></li>
				    	<li><a href="#deviceDetails">Device Details</a></li>
				   	</ul>
					<!--  customerBasicInformation tab-->
					 
					<div id="customerBasicInformation" class="clear tab_content">					    
					   <!-- left customerBasicInformation-->
						<div class="inner_left_lead  marginleft10">
						<!-- Success Messages Starts -->
						<div class="success_message">
							<logic:messagesPresent message="true">
								<html:messages id="message" message="true">
									<bean:write name="message" />
								</html:messages>
							</logic:messagesPresent>
						</div>
						<div class="error_message" id="error"><html:errors /></div>
					<!-- Success Messages Ends -->
							<!-- CRF Details -->
							<div class="floatl" id="customerDetails">
								<h4>CAF Details</h4>
								<ul class="tabcontentUL">
									<li><strong>CAF Number</strong>
										<label><bean:write name="crmCapForm" property="customerDetailsPojo.crfId" /></label>
									</li>
									<li><strong>Service Name</strong> 
										<label>
											<logic:notEmpty name="crmCapForm" property="customerDetailsPojo.product">
												<bean:write name="crmRoles" property="displayEnum(Product,${ crmCapForm.customerDetailsPojo.product})" scope="session" />
											</logic:notEmpty>
										</label>
									</li>
									<li><strong>Service Type</strong>
										<label>
											<logic:notEmpty name="crmCapForm" property="customerDetailsPojo.serviceType">
												<bean:write name="crmRoles" property="displayEnum(ServiceType,${ crmCapForm.customerDetailsPojo.serviceType})" scope="session" />
											</logic:notEmpty>
										</label>
									</li>
									<li><strong>Brand</strong>
										<label>
											<bean:write  name="crmCapForm" property="customerDetailsPojo.brand" />
										</label>
									</li>
									<li><strong> Date on CAF</strong> 
									    <label>
									     <bean:write name="crmRoles" property="toDate(${ crmCapForm.customerDetailsPojo.crfDate})" scope="session"/>
									    </label>
									</li>
									<li><strong> CAF Entry Date</strong> 
									    <bean:write name="crmRoles" property="toDate(${crmCapForm.customerDetailsPojo.createdTime})" scope="session"/>
									</li>
									<li><strong> FT Submission Date</strong> 
									    <logic:empty name="crmCapForm" property="customerDetailsPojo.ftSubmittionDate">-</logic:empty>
										<logic:notEmpty name="crmCapForm" property="customerDetailsPojo.ftSubmittionDate">
											<bean:write name="crmRoles" property="toDate(${crmCapForm.customerDetailsPojo.ftSubmittionDate})" scope="session"/>		
										</logic:notEmpty>
									</li>
									<li><strong> FT Approval Date</strong> 
									    <logic:empty name="crmCapForm" property="customerDetailsPojo.ftApprovalDate">-</logic:empty>
										<logic:notEmpty name="crmCapForm" property="customerDetailsPojo.ftApprovalDate">
											<bean:write name="crmRoles" property="toDate(${crmCapForm.customerDetailsPojo.ftApprovalDate})" scope="session"/>		
										</logic:notEmpty>
									</li>
								</ul>
								<ul class="tabcontentUL">
									<li><strong>CAF Reference Number</strong>
										<logic:empty name="crmCapForm" property="customerDetailsPojo.crfReferenceNo">
									 		-
									 	</logic:empty>
										<logic:notEmpty name="crmCapForm" property="customerDetailsPojo.crfReferenceNo">
											<label><bean:write name="crmCapForm" property="customerDetailsPojo.crfReferenceNo" /></label>
										</logic:notEmpty>
									</li>
									<li>
									<logic:equal name="crmCapForm" property="customerDetailsPojo.serviceType" value="PO">
									<strong>Bill Cycle</strong>
									${ empty crmCapForm.customerDetailsPojo.billDate ? '-' :crmCapForm.customerDetailsPojo.billDate  }
									</logic:equal>
									</li>
								</ul>
							</div>
							<!-- End CRF Details -->
						     
						    <!-------- Customer Personal Details------->
							<div class=" paddingTop10 floatl clear" id="customerPersonalDetails">
							<h4>Customer  Details</h4>
								<ul class="tabcontentUL">
									<li><strong>Customer ID</strong>
									<logic:empty name="crmCapForm" property="customerDetailsPojo.customerId">-</logic:empty>
									<logic:notEmpty name="crmCapForm" property="customerDetailsPojo.customerId">
									<label><bean:write name="crmCapForm" property="customerDetailsPojo.customerId" /></label>
									</logic:notEmpty>
									</li>
									<li><strong>Connection Type</strong>
										<logic:notEmpty name="crmCapForm" property="customerDetailsPojo.connectionType">
											<bean:write name="crmRoles" property="displayEnum(ConnectionType,${ crmCapForm.customerDetailsPojo.connectionType})" scope="session" />
										</logic:notEmpty>
									</li>
								</ul>
								<%! boolean showCompanyDetails = true; %>
									<c:if test="${ (crmCapForm.customerDetailsPojo.connectionType eq 'Ind') || (crmCapForm.customerDetailsPojo.connectionType eq 'Prop')  }">
										<% showCompanyDetails = false; %>
									</c:if>
								<ul class="tabcontentUL">
									<li class="clear <%= showCompanyDetails ? "" : "hide1" %>"><strong class="headStrong">Company/Organization/Society</strong></li>
									<li class="clear <%= showCompanyDetails ? "" : "hide1" %>"><strong>Company Name</strong>
										<label><bean:write name="crmCapForm" property="customerDetailsPojo.custFname" /></label>
									</li>
									
									<li class="clear <%= showCompanyDetails ? "hide1" : "" %>"><strong class="headStrong">Individual/Proprietorship</strong></li>
									<li class="clear <%= showCompanyDetails ? "hide1" : "" %>"><strong>First Name</strong>
										<label><bean:write name="crmCapForm" property="customerDetailsPojo.custFname" /></label>
									</li>
									<li class="<%= showCompanyDetails ? "hide1" : "" %>"><strong>Last Name</strong>
										<label><bean:write name="crmCapForm" property="customerDetailsPojo.custLname" /></label>
									</li>
									
									<li><strong>Registered Mobile No.</strong>
										<label><bean:write name="crmCapForm" property="customerDetailsPojo.rmn" /></label>
									</li>
									<%--<li><strong>Registered Telephone No.</strong>
										<label><bean:write name="crmCapForm" property="customerDetailsPojo.rtn" /></label>
									</li>--%>
									<li class="clear"><strong>Registered Email ID</strong>
										<label><bean:write name="crmCapForm" property="customerDetailsPojo.custEmailId" /></label>
									</li>
									<%--<li><strong>Alt. Email ID</strong>
										<label><bean:write name="crmCapForm" property="customerDetailsPojo.altEmailId" /></label> 
										<label>${ empty crmCapForm.customerDetailsPojo.altEmailId ? '-' : crmCapForm.customerDetailsPojo.altEmailId }</label>
									</li> --%>
									<li><strong>Alt. Mobile No.</strong>
										<%-- <label><bean:write name="crmCapForm" property="customerDetailsPojo.custMobileNo" /></label> --%>
										<label>${ crmCapForm.customerDetailsPojo.custMobileNo eq 0 ? '-' : crmCapForm.customerDetailsPojo.custMobileNo }</label>
									</li>
									<li><strong>PAN / GIR No.</strong>
										<%-- <label><bean:write name="crmCapForm" property="customerDetailsPojo.custPanGirNo" /></label> --%>
										<label>${ empty crmCapForm.customerDetailsPojo.custPanGirNo ? '-' : crmCapForm.customerDetailsPojo.custPanGirNo }</label>
									</li>
									<li class="clear"> 
										<strong class="headStrong">Father's / Husband's Name </strong>
										<span class="inlineblock">
											<strong>First Name</strong>
									 		<label><bean:write name="crmCapForm" property="customerDetailsPojo.fhFname" /></label>
									 	</span>
									 	<span class="inlineblock marginleft30">
									 		<strong>Last Name</strong>
											<label>${ empty crmCapForm.customerDetailsPojo.fhLname ? '-' : crmCapForm.customerDetailsPojo.fhLname }</label>
										</span>
										<span class="inlineblock marginleft30">
									 		<strong>Aadhar No.</strong>
											<label>${ crmCapForm.customerDetailsPojo.aadharNo eq 0 ? '-' : crmCapForm.customerDetailsPojo.aadharNo }</label>
										</span>
									</li>
									<li class="clear <%= showCompanyDetails ? "hide1" : "" %>"><strong class="headStrong">&nbsp;</strong>
										<strong>Gender</strong> 
										<label>
											<logic:notEmpty name="crmCapForm" property="customerDetailsPojo.custGender">
												<bean:define id="custGender" name="crmCapForm" property="customerDetailsPojo.custGender"></bean:define>
												<logic:equal name="custGender" value="M">Male</logic:equal>
												<logic:equal name="custGender" value="F">Female</logic:equal>
											</logic:notEmpty> 
											<logic:empty name="crmCapForm" property="customerDetailsPojo.custGender">
									 		-
									 	</logic:empty>
									 	</label>
									</li>
									<li class="<%= showCompanyDetails ? "hide1" : "" %>"><strong class="headStrong">&nbsp; </strong>
										<strong>Birth Date</strong> 
									 	<label>
									 	<bean:write name="crmRoles" property="toDate(${crmCapForm.customerDetailsPojo.custDob})" scope="session"/>
									 	</label>
									</li>
									<li class="<%= showCompanyDetails ? "hide1" : "" %>"><strong class="headStrong">&nbsp; </strong> 
										<strong>Nationality</strong>
										<c:choose>
										   <c:when test="${not empty crmCapForm.nationalityDetailsPojo.nationality}">
										  	 <label id="nationalityView"><bean:write name="crmCapForm" property="nationalityDetailsPojo.nationality" /></label>
										   </c:when> 
										   <c:otherwise>
										   	 <label id="nationalityView"><bean:write name="crmCapForm" property="customerDetailsPojo.nationality" /></label>
										    </c:otherwise>
										</c:choose>
									</li>
									<li class="clear <%= showCompanyDetails ? "" : "hide1" %>"><strong class="headStrong">Organization Coordinator Name </strong>
									<span class="inlineblock"><strong> First Name</strong>
										<label><bean:write name="crmCapForm" property="customerDetailsPojo.orgCordFname" /></label></span>
										<span class="inlineblock marginleft30">
										<strong> Last Name</strong>
										<label><bean:write name="crmCapForm" property="customerDetailsPojo.orgCordLname" /></label>
										</span>
									</li>
									<li class="marginleft30 <%= showCompanyDetails ? "" : "hide1" %>"><strong class="headStrong">Authorized Signatory Name &amp; Designation </strong>
									 	<span class="inlineblock"><strong> First Name</strong>
									 	<label><bean:write name="crmCapForm" property="customerDetailsPojo.authSignFname" /></label></span>
									 	<span class="inlineblock marginleft30">
									 	<strong> Last Name</strong>
									 	<label><bean:write name="crmCapForm" property="customerDetailsPojo.authSignLname" /></label></span>
									 	<span class="inlineblock marginleft30">
									 	<strong> Designation</strong>
									 	<label><bean:write name="crmCapForm" property="customerDetailsPojo.authSignDesg" /></label>
									 	
									 	</span>
									</li>
								</ul>
							</div>
						    <!-------- End Customer Personal Details ------->
						     
							<!----------   Other Nationality Details -------------------->
							<c:if test="${ crmCapForm.customerDetailsPojo.nationality ne 'Indian' && crmCapForm.customerDetailsPojo.nationality ne '0' }">
								<div class=" clear floatl paddingTop10 " id="OtherNationalityDetailsView">
									<h4>Passport Details</h4>
									<ul class="tabcontentUL">
										<li class="clear"><strong class="headStrong">Passport Information </strong></li>
										<li class="clear"><strong>Passport No.</strong> 
											  <logic:notEmpty name="crmCapForm" property="nationalityDetailsPojo.passportNo" >   
												<label><bean:write name="crmCapForm" property="nationalityDetailsPojo.passportNo" /></label>
											  </logic:notEmpty>
											  <logic:empty name="crmCapForm" property="nationalityDetailsPojo.passportNo" > -
											  </logic:empty>
										</li>
										<li><strong>Passport Valid Up to</strong>
											  <logic:notEmpty name="crmCapForm" property="nationalityDetailsPojo.passportValidity" >
												<label>
												 <bean:write name="crmRoles" property="toDate(${crmCapForm.nationalityDetailsPojo.passportValidity})" scope="session"/>
												</label>
											 </logic:notEmpty>
										 <logic:empty name="crmCapForm" property="nationalityDetailsPojo.passportValidity" >-</logic:empty>
										 
										</li>
										<li><strong> Visa Type</strong> 
											  <logic:notEqual  name="crmCapForm" property="nationalityDetailsPojo.visaType" value="0">
													<label><bean:write name="crmCapForm" property="nationalityDetailsPojo.visaType" /></label> 
											   </logic:notEqual>
											   <logic:equal  name="crmCapForm" property="nationalityDetailsPojo.visaType" value="0">-</logic:equal>
										</li>
										<li><strong>Visa Valid Up to</strong> 
											 <logic:notEmpty name="crmCapForm" property="nationalityDetailsPojo.visaValidity" >
												<label><bean:write name="crmRoles" property="toDate(${crmCapForm.nationalityDetailsPojo.visaValidity})" scope="session"/></label>
											 </logic:notEmpty>
											 <logic:empty name="crmCapForm" property="nationalityDetailsPojo.visaValidity" >-</logic:empty>
										</li>
									 
									 	<li class="clear"><strong class="headStrong">Details of Local Reference </strong></li>
									  
										<li class="clear"><strong> First Name</strong>
											 <logic:notEmpty name="crmCapForm" property="nationalityDetailsPojo.localCpFname" >
												<label><bean:write name="crmCapForm" property="nationalityDetailsPojo.localCpFname" /></label>
											</logic:notEmpty>
											<logic:empty name="crmCapForm" property="nationalityDetailsPojo.localCpFname" >-</logic:empty>
										</li>
										<li><strong> Last Name</strong>
											 <logic:notEmpty name="crmCapForm" property="nationalityDetailsPojo.localCpLname" >
												<label><bean:write name="crmCapForm" property="nationalityDetailsPojo.localCpLname" /></label>
											 </logic:notEmpty>
											 <logic:empty name="crmCapForm" property="nationalityDetailsPojo.localCpLname" >-</logic:empty>
										</li>
										<li><strong> Mobile No.</strong>
											   <logic:notEqual  name="crmCapForm" property="nationalityDetailsPojo.localCpMobileNo" value="0">
												  <label><bean:write name="crmCapForm" property="nationalityDetailsPojo.localCpMobileNo" /></label>
												</logic:notEqual>
												<logic:equal name="crmCapForm" property="nationalityDetailsPojo.localCpMobileNo" value="0">-</logic:equal>
										</li>
										<li><strong>Address</strong>
											<logic:notEmpty name="crmCapForm" property="nationalityDetailsPojo.localCpAdd">
												<label><bean:write name="crmCapForm" property="nationalityDetailsPojo.localCpAdd" /></label>
											</logic:notEmpty>
											<logic:empty name="crmCapForm" property="nationalityDetailsPojo.localCpAdd">-</logic:empty>
										</li>
									</ul>
								</div>
							</c:if>
							<logic:notEqual value="Indian" name="crmCapForm" property="customerDetailsPojo.nationality">
								<%-- 
								<div class=" clear floatl paddingTop10 " id="OtherNationalityDetailsView">
									<h4>Passport Details</h4>
									<ul class="tabcontentUL">
										<li class="clear"><strong class="headStrong">Passport Information </strong></li>
										<li class="clear"><strong>Passport No.</strong>    
											<label><bean:write name="crmCapForm" property="nationalityDetailsPojo.passportNo" /></label>
										</li>
										<li><strong>Passport Valid Up to</strong>
											<label><bean:write name="crmCapForm" property="nationalityDetailsPojo.displayPassportValidity" /></label>
										</li>
										<li><strong> Visa Type</strong> 
											<label><bean:write name="crmCapForm" property="nationalityDetailsPojo.visaType" /></label> 
										</li>
										<li><strong>Visa Valid Up to</strong> 
											<label><bean:write name="crmCapForm" property="nationalityDetailsPojo.displayVisaValidity" /></label>
										</li>
									 
									 	<li class="clear"><strong class="headStrong">Details of Local Reference </strong></li>
									  
										<li class="clear"><strong> First Name</strong>
											<label><bean:write name="crmCapForm" property="nationalityDetailsPojo.localCpFname" /></label>
										</li>
										<li><strong> Last Name</strong>
											<label><bean:write name="crmCapForm" property="nationalityDetailsPojo.localCpLname" /></label>
										</li>
										<li><strong> Mobile No.</strong>
											<label><bean:write name="crmCapForm" property="nationalityDetailsPojo.localCpMobileNo" /></label>
										</li>
										<li><strong>Address</strong>
											<label><bean:write name="crmCapForm" property="nationalityDetailsPojo.localCpAdd" /></label>
										</li>
									</ul>
								</div>
								--%>
							</logic:notEqual>
							<!-- End Other Nationality Details -->
								
						    <!-------- Feasible address------->
							<div class=" paddingTop10  floatl clear" id="">
						    	<h4>Feasible Address</h4>
						     	<ul class="tabcontentUL">
							      	<li><strong>State</strong> 
							  	  		<label><bean:write name="crmCapForm" property="state" /></label> 
							  		</li>
							  		<li><strong>City</strong>   
							  	  		<label><bean:write name="crmCapForm" property="city" /></label>
							 		</li>
							  		<li><strong>Area</strong> 
							  	  		<label><bean:write name="crmCapForm" property="area" /></label>
									</li>
							  		<li><strong>Locality</strong> 
							  	  		<label>
							  	  			<logic:notEmpty name="crmCapForm" property="locality">
							  	  				<bean:write name="crmCapForm" property="locality" />	
							  	  			</logic:notEmpty>
							  	  			<logic:empty name="crmCapForm" property="locality">-</logic:empty>
							  	  		</label>
							 		</li>
							  		<li><strong>Society</strong> 
							  	  		<label>
							  	  			<logic:notEmpty name="crmCapForm" property="society">
							  	  				<bean:write name="crmCapForm" property="society" />
							  	  			</logic:notEmpty>
							  	  			<logic:empty name="crmCapForm" property="society">-</logic:empty>
							  	  		</label>
							 		</li>
							 		<li><strong>Connectable Homes</strong> 
							  	  		<label>
							  	  			<logic:notEmpty name="crmCapForm" property="connectableHomes">
							  	  				<bean:write name="crmCapForm" property="connectableHomes" />
							  	  			</logic:notEmpty>
							  	  			<logic:empty name="crmCapForm" property="connectableHomes">-</logic:empty>
							  	  		</label>
							 		</li>
								</ul>
							</div>
							<!-- End Feasible address -->
							
							
							<!-------- Installation address------->
							<div class=" paddingTop10  floatl clear" id="">  
						    	<h4>Installation Address</h4>
								<ul class="tabcontentUL">
									<li><strong>Address Line 1</strong> 
										<label><bean:write name="crmCapForm" property="installationAddressPojo.addLine1" /></label> 
									</li>
									<li><strong>Address Line 2</strong>   
										<label><bean:write name="crmCapForm" property="installationAddressPojo.addLine2" /></label>
									</li>
									<li><strong>Address Line 3</strong> 
										<label><bean:write name="crmCapForm" property="installationAddressPojo.addLine3" /></label>
									</li>
									<li><strong>Landmark</strong> 
										<label><bean:write name="crmCapForm" property="installationAddressPojo.landmark" /></label>
									</li>
									<li><strong>PIN Code</strong> 
										<label><bean:write name="crmCapForm" property="installationAddressPojo.pincode" /></label>
									</li>
									<%-- <li><strong>Contact No.</strong>  
										<label><bean:write name="crmCapForm" property="installationAddressPojo.contactNo" /></label>
									</li>--%>
									<li><strong>Property Details</strong>  
									    <label>
										    <logic:notEmpty name="crmCapForm" property="installationAddressPojo.propertyDetails">
									   			<bean:define id="propertyDetails" name="crmCapForm" property="installationAddressPojo.propertyDetails" ></bean:define>
									     		<logic:equal name="propertyDetails" value="Rented">Rented</logic:equal>
												<logic:equal name="propertyDetails" value="Owned">Owned</logic:equal> 
											</logic:notEmpty>
										</label>
									</li>
								</ul>
							</div>
							<!-- End Installation address -->
						     
						     
						      <!-------- billing address------->
							<div class=" paddingTop10  floatl clear" id="">
								<h4>Billing Address</h4>
								<ul class="tabcontentUL">
									<li><strong>Address Line 1</strong>
										<label><bean:write name="crmCapForm" property="billingAddressPojo.addLine1" /></label>
									</li>
									<li><strong>Address Line 2</strong>
										<label><bean:write name="crmCapForm" property="billingAddressPojo.addLine2" /></label>
									</li>
									<li><strong>Address Line 3</strong> 
										<label><bean:write name="crmCapForm" property="billingAddressPojo.addLine3" /></label>
									</li>
									<li><strong>Landmark</strong>
										<label><bean:write name="crmCapForm" property="billingAddressPojo.landmark" /></label>
									</li>
									<li><strong>PIN Code</strong> 
										<label><bean:write name="crmCapForm" property="billingAddressPojo.pincode" /></label>
									</li>
									<%-- <li><strong>Contact No.</strong> 
										<label><bean:write name="crmCapForm" property="billingAddressPojo.contactNo" /></label>
									</li>--%>
						 		</ul>
							</div>
							<logic:notEmpty name="crmCapForm" property="aadharNumberPojo.aadharAdd">
								<div class=" paddingTop10  floatl clear" id="">
									<h4>Aadhar Address</h4>
									<ul class="tabcontentUL">
										<li><label>AD: <bean:write name="crmCapForm" property="aadharNumberPojo.aadharAdd" /></label></li>
									</ul>
									<ul class="tabcontentUL">
										<li><label>IN: <bean:write name="crmCapForm" property="aadharNumberPojo.instAddress" /></label></li>
									</ul>
								</div>
							</logic:notEmpty>
						    <!-------- end billing address------->
						   <br class="clear" /> 
							
						</div>
					     <!-- end customerBasicInformation-->
					</div>
					<!-- end customerBasicInformation tab-->
					 
					 
					 <!--  orderPaymentDetails tab-->  
					<div id="orderPaymentDetails"  class="clear  tab_content">
					     
					 <!-- orderPaymentDetails-->  
						<div class="inner_left_lead  marginleft10" id="serviceDetails">
							<!-- service details -->
							<div class="floatl  ">
					    		<h4>Service Details</h4>
								<ul class="tabcontentUL">
									<li><strong>CPE Status</strong>
										<label>
									  		<logic:notEmpty  name="crmCapForm" property="orderDetailsPojo.cpeStatus">
									  			<bean:write name="crmRoles" property="displayEnum(CPEStatus,${ crmCapForm.orderDetailsPojo.cpeStatus})" scope="session" />
									        </logic:notEmpty>
									  	</label>
									</li>
															
					     		</ul>
					    	</div>
							<!-- end service details -->
					
							<!-- Plan details -->
							<div class="floatl clear paddingTop10" id="planDetails">
								<h4>Plan Details</h4>
								<ul class="tabcontentUL">
									<li><strong>Base Plan Name</strong>    
										<label>
											<logic:notEmpty  name="crmCapForm" property="planDetailsPojo.basePlanCode">
												<bean:write name="crmRoles" property="displayEnum(BASE,${crmCapForm.planDetailsPojo.basePlanCode})" scope="session" />
											</logic:notEmpty>
										</label>
									</li>
									<li><strong>Add-on DUL Value</strong>    
										<label>
											<logic:notEmpty name="crmCapForm" property="planDetailsPojo.addOnPlanCode">
												<bean:write name="crmRoles" property="displayEnum(ADDON,${crmCapForm.planDetailsPojo.addOnPlanCode})" scope="session" />
									    	</logic:notEmpty>
									    	<logic:empty name="crmCapForm" property="planDetailsPojo.addOnPlanCode"> - </logic:empty>
									    	
										</label>
									</li>
									<li><strong>Billing Preferences</strong>    
									 	<label>
											<logic:notEmpty name="crmCapForm" property="planDetailsPojo.billMode" >
												<bean:write name="crmRoles" property="displayEnum(BillingPreferences,${crmCapForm.planDetailsPojo.billMode})" scope="session" />
									    	</logic:notEmpty>
										</label>
									</li>
									<li><strong>Type of Application/Usage of Service</strong> 
										<label>
											<logic:notEmpty name="crmCapForm" property="planDetailsPojo.usageType">
												<bean:define id="usageType" name="crmCapForm" property="planDetailsPojo.usageType"></bean:define>
									    		<logic:equal name="usageType" value="O">Own use</logic:equal>
												<logic:equal name="usageType" value="R">Resale</logic:equal>
												<logic:equal name="usageType" value="C">Cyber Cafe</logic:equal>
											</logic:notEmpty>
										</label>
									</li>
								</ul>
							</div>
							<!-- End Plan details -->
							
							<c:set var="codeChequeDD" value="<%= CRMDisplayListConstants.CHEQUE.getCode() %>"></c:set>
							<c:set var="codeOnline" value="<%= CRMDisplayListConstants.ONLINE_PAYMENT.getCode() %>"></c:set>
							<c:set var="codeCash" value="<%= CRMDisplayListConstants.CASH.getCode() %>" ></c:set>
							
							<!-------- Payment Information ------->
							<logic:notEmpty name="crmCapForm" property="teleservicesPayment">
							<div class=" floatl clear paddingTop10" id="paymentInformation">
								<h4>Teleservices Payment</h4>
								<ul class="tabcontentUL">
									<li><strong> Security Deposit</strong>  
										<label><bean:write name="crmCapForm" property="teleservicesPayment.securityCharges" /></label>
									</li>
									<li><strong>Rental Charges</strong>   
										<label><bean:write name="crmCapForm" property="teleservicesPayment.activationCharges" /></label>
									</li>
										<li><strong>Installation Charges</strong>   
										<label><bean:write name="crmCapForm" property="teleservicesPayment.registrationCharges" /></label>
									</li>
									<li class="clear"><strong>Payment Mode</strong>
										<logic:notEmpty name="crmCapForm" property="teleservicesPayment.paymentMode" >
											<bean:write name="crmRoles" property="displayEnum(PaymentMode,${crmCapForm.teleservicesPayment.paymentMode})" scope="session" />
					         			</logic:notEmpty>
					         			<logic:empty name="crmCapForm" property="teleservicesPayment.paymentMode" >-</logic:empty>
									</li>
									
									<c:choose>
										<c:when test="${ crmCapForm.teleservicesPayment.paymentMode eq codeChequeDD }">
											<li class="floatl marginleft15"><strong>Cheque/DD No.</strong>
											   <label><bean:write name="crmCapForm" property="teleservicesPayment.chequeDdNo" /></label>
											</li>
											
											<li class="floatl marginleft15"><strong>Bank Name</strong>
											    <label>
											  <logic:notEmpty name="crmCapForm" property="teleservicesPayment.bankName" >
											    	<bean:write name="crmRoles" property="displayEnum(BANK,${crmCapForm.teleservicesPayment.bankName})" scope="session" />
											    </logic:notEmpty>
											    <logic:empty name="crmCapForm" property="teleservicesPayment.bankName" >
											    -
											    </logic:empty>
											    </label>
											</li>
											
											<li class="clear "><strong>Bank Branch</strong>
											    <label><bean:write name="crmCapForm" property="teleservicesPayment.bankBranch" /></label>
											</li>
											
											<li class="floatl marginleft15 "><strong>Cheque Date</strong>
											    <label>
											     <bean:write name="crmRoles" property="toDate(${ crmCapForm.teleservicesPayment.chequeDate})" scope="session"/>
											    </label>
											</li>
											
											<li class="floatl marginleft15 "><strong>Bank City</strong>
											    <label><bean:write name="crmCapForm" property="teleservicesPayment.bankCity" /></label>
											</li>
										</c:when>
										<c:when test="${ crmCapForm.teleservicesPayment.paymentMode eq codeCash }">
											<li class="floatl marginleft15 "><strong>Receipt No.</strong>    
												<label><bean:write name="crmCapForm" property="teleservicesPayment.receiptNo" /></label>
											</li>
										</c:when>
										<c:when test="${ crmCapForm.teleservicesPayment.paymentMode eq codeOnline }">
											<li class="floatl marginleft15 "><strong>Transaction ID</strong>
											    <label><bean:write name="crmCapForm" property="teleservicesPayment.transactionId" /></label>
											</li>
										</c:when>
										<c:otherwise>
										</c:otherwise>
									</c:choose>
								</ul>
							</div>
							</logic:notEmpty>
							<!-- End Payment Information -->
					    
							<!-------- Telesolution Payment ------->
							<logic:equal name="crmCapForm" property="orderDetailsPojo.cpeStatus" value="NR">
							<logic:notEmpty name="crmCapForm" property="telecommunicationPayment">
								<div class="floatl paddingTop10 clear">
								<h4>Associate Co.</h4>
					      			<ul class="tabcontentUL">
										<li><strong>Amount</strong>    
											<label><bean:write name="crmCapForm" property="telecommunicationPayment.amount" /></label>
										</li>
										<li class="clear"><strong>Payment Mode</strong>
											<logic:notEmpty name="crmCapForm" property="telecommunicationPayment.paymentMode" >
												<bean:write name="crmRoles" property="displayEnum(PaymentMode,${crmCapForm.telecommunicationPayment.paymentMode})" scope="session" />
											</logic:notEmpty>
										</li>
										<c:choose>
											<c:when test="${ crmCapForm.telecommunicationPayment.paymentMode eq codeChequeDD }">
												<li class="floatl marginleft15 "><strong>Cheque/DD No.</strong>
												   <label><bean:write name="crmCapForm" property="telecommunicationPayment.chequeDdNo" /></label>
												</li>
												
												<li class="floatl marginleft15 "><strong>Bank Name</strong>
												    <label>
												    <logic:notEmpty name="crmCapForm" property="teleservicesPayment.bankName" >
												    <bean:write name="crmRoles" property="displayEnum(BANK,${crmCapForm.telecommunicationPayment.bankName})" scope="session" />
												    </logic:notEmpty>
												    <logic:empty name="crmCapForm" property="teleservicesPayment.bankName" >
												    -
												    </logic:empty>
												    </label>
												</li>
												
												<li class="clear "><strong>Bank Branch</strong>
												    <label><bean:write name="crmCapForm" property="telecommunicationPayment.bankBranch" /></label>
												</li>
												
												<li class="floatl marginleft15 "><strong>Cheque Date</strong>
												    <label>
												      <bean:write name="crmRoles" property="toDate(${ crmCapForm.telecommunicationPayment.chequeDate})" scope="session"/>
												    </label>
												</li>
												
												<li class="floatl marginleft15 "><strong>Bank City</strong>
												    <label><bean:write name="crmCapForm" property="telecommunicationPayment.bankCity" /></label>
												</li>
											</c:when>
											<c:when test="${ crmCapForm.telecommunicationPayment.paymentMode eq codeCash }">
												<li class="floatl marginleft15 "><strong>Receipt No.</strong>    
													<label><bean:write name="crmCapForm" property="telecommunicationPayment.receiptNo" /></label>
												</li>
											</c:when>
											<c:when test="${ crmCapForm.telecommunicationPayment.paymentMode eq codeOnline }">
												<li class="floatl marginleft15 "><strong>Transaction ID</strong>
												    <label><bean:write name="crmCapForm" property="telecommunicationPayment.transactionId" /></label>
												</li>
											</c:when>
											<c:otherwise>
											</c:otherwise>
										</c:choose>
					    			</ul>
								</div>
								</logic:notEmpty>
								</logic:equal>
								
								<!-- Secuirty Deposit payment starts-->
								
								
							<logic:notEmpty name="crmCapForm" property="securityDepositPayment">
							<div class=" floatl clear paddingTop10" id="securityDepositInformation">
								<h4>Security Deposit Payment</h4>
								<ul class="tabcontentUL">
									<li><strong>Amount</strong>  
										<label><bean:write name="crmCapForm" property="securityDepositPayment.amount" /></label>
									</li>
									<li class="clear"><strong>Payment Mode</strong>
										<logic:notEmpty name="crmCapForm" property="securityDepositPayment.paymentMode" >
											<bean:write name="crmRoles" property="displayEnum(PaymentMode,${crmCapForm.securityDepositPayment.paymentMode})" scope="session" />
					         			</logic:notEmpty>
					         			<logic:empty name="crmCapForm" property="securityDepositPayment.paymentMode" >-</logic:empty>
									</li>
									
									<c:choose>
										<c:when test="${ crmCapForm.securityDepositPayment.paymentMode eq codeChequeDD }">
											<li class="floatl marginleft15"><strong>Cheque/DD No.</strong>
											   <label><bean:write name="crmCapForm" property="securityDepositPayment.chequeDdNo" /></label>
											</li>
											
											<li class="floatl marginleft15"><strong>Bank Name</strong>
											    <label>
											  <logic:notEmpty name="crmCapForm" property="securityDepositPayment.bankName" >
											    	<bean:write name="crmRoles" property="displayEnum(BANK,${crmCapForm.securityDepositPayment.bankName})" scope="session" />
											    </logic:notEmpty>
											    <logic:empty name="crmCapForm" property="securityDepositPayment.bankName" >
											    -
											    </logic:empty>
											    </label>
											</li>
											
											<li class="clear "><strong>Bank Branch</strong>
											    <label><bean:write name="crmCapForm" property="securityDepositPayment.bankBranch" /></label>
											</li>
											
											<li class="floatl marginleft15 "><strong>Cheque Date</strong>
											    <label>
											    <bean:write name="crmRoles" property="toDate(${ crmCapForm.securityDepositPayment.chequeDate})" scope="session"/>
											   </label>
											</li>
											
											<li class="floatl marginleft15 "><strong>Bank City</strong>
											    <label><bean:write name="crmCapForm" property="securityDepositPayment.bankCity" /></label>
											</li>
										</c:when>
										<c:when test="${ crmCapForm.securityDepositPayment.paymentMode eq codeCash }">
											<li class="floatl marginleft15 "><strong>Receipt No.</strong>    
												<label><bean:write name="crmCapForm" property="securityDepositPayment.receiptNo" /></label>
											</li>
										</c:when>
										<c:when test="${ crmCapForm.securityDepositPayment.paymentMode eq codeOnline }">
											<li class="floatl marginleft15 "><strong>Transaction ID</strong>
											    <label><bean:write name="crmCapForm" property="securityDepositPayment.transactionId" /></label>
											</li>
										</c:when>
										<c:otherwise>
										</c:otherwise>
									</c:choose>
								</ul>
							</div>
							</logic:notEmpty>
							
								
								<!-- Secuirty Deposit payment ends -->
					   		<br class="clear" /> 
						</div>
					     <!-- end orderPaymentDetails-->
					</div>
					<!-- end orderPaymentDetails tab-->  
					 
					<!--  documentDetails tab-->  
					<div id="documentDetails"  class="clear tab_content">
						<!-- Document Details -->
						<div class="inner_left_lead  marginleft10">
					    	<!-- Documents -->
							<div class="floatl">
					        	<h4>Documents Details</h4>
								<ul class="tabcontentUL">
									<li><strong>Documents</strong> 
										<label>
											<logic:notEmpty name="crmCapForm" property="documentDetailsPojo.idProof">
												<logic:iterate id="idProof" name="crmCapForm" property="documents" indexId="ctr">
													<logic:equal name="idProof" property="selected" value="true">${idProof.contentName},</logic:equal>
												</logic:iterate>
											</logic:notEmpty>
										</label>
					        		</li>
							        <li><strong>Address Proof</strong>
							        	<label>
										    <logic:notEmpty name="crmCapForm" property="documentDetailsPojo.addressProof">
					      						<logic:iterate id="addProof" name="crmCapForm" property="addressProofs" indexId="ctr">
													<logic:equal name="addProof" property="selected" value="true">${addProof.contentName},</logic:equal>
						    					</logic:iterate>
							      		    </logic:notEmpty>
										</label>
							        </li>
					    		</ul>
					    	</div>
					    	<!-- End Documents -->
					        <!-- uploaded documents -->
                            <c:url value="${initParam.dmshost }/np-document-upload/files/latest/INA/${ crmCapForm.customerDetailsPojo.crfId }" var="dmsUrl"></c:url>
                            <div class=" floatl clear paddingTop10">
                              <h4>Uploaded Document(s)</h4>                              
                              <iframe src="${dmsUrl }" scrolling="yes" frameborder="0" style="border: 1px solid #eff2f8; overflow: hidden; width: 755px; height: 400px;" allowTransparency="true"></iframe>
                            </div>
                            <!-- end uploaded documents -->
							<br class="clear" />
						</div>
						<!-- End Document Details-->
					</div>
					<!-- end documentDetails tab -->  
					   
					<!-- additionalInformation tab -->  
					<div id="additionalInformation"  class="clear  tab_content">
						<!-- additional information -->
						<div class="inner_left_lead  marginleft10">
					    	<!-- Vehicle Details  -->
							<div class="floatl" id="vehicleDetails">
					        	<h4>Vehicle/Mobile Handset Details</h4>
					        	<ul class="tabcontentUL">
					         		<li><strong>Vehicle Make</strong>
					            		<logic:notEmpty name="crmCapForm" property="additionalDetailsPojo.vehicleDetail" >
					         	  			<label><bean:write name="crmCapForm" property="additionalDetailsPojo.vehicleMake" /></label>
					         			</logic:notEmpty>
					         			<logic:empty name="crmCapForm" property="additionalDetailsPojo.vehicleDetail">-</logic:empty>
					         		</li>
					        		<li><strong>Vehicle Registration No.</strong>
					                	<logic:notEmpty name="crmCapForm" property="additionalDetailsPojo.vehicleDetail" >
					           	   			<label><bean:write name="crmCapForm" property="additionalDetailsPojo.vehicleDetail" /></label>
					           			</logic:notEmpty>
					           			<logic:empty name="crmCapForm" property="additionalDetailsPojo.vehicleDetail">-</logic:empty>
					        		</li>
					         		<li class="clear"><strong>Mobile Make</strong>
					             		<logic:notEmpty name="crmCapForm" property="additionalDetailsPojo.mobileMake"  >
					        				<label><bean:write name="crmCapForm" property="additionalDetailsPojo.mobileMake" /></label>
					            		</logic:notEmpty>
					            		<logic:empty name="crmCapForm" property="additionalDetailsPojo.mobileMake" >-</logic:empty>
					        		</li>
					         		<li><strong>Mobile Model No.</strong>
					              		<logic:notEmpty name="crmCapForm" property="additionalDetailsPojo.mobileNo">
					            			<label><bean:write name="crmCapForm" property="additionalDetailsPojo.mobileNo" /></label>
					            		</logic:notEmpty>
					            		<logic:empty name="crmCapForm" property="additionalDetailsPojo.mobileNo">-</logic:empty>
					        		</li>
					         		<li><strong>Mobile IMEI No.</strong>
					            		<logic:notEmpty name="crmCapForm" property="additionalDetailsPojo.mobileImeiNo">
					          				<label><bean:write name="crmCapForm" property="additionalDetailsPojo.mobileImeiNo" /></label>
					          			</logic:notEmpty>
					          			<logic:empty name="crmCapForm" property="additionalDetailsPojo.mobileImeiNo">-</logic:empty>
					         		</li>
					       		</ul>   
							</div>
							<!-- Credit Card  Details  -->
							<div class="floatl paddingTop10 clear" id="creditCardDetails">
					        	<h4>Credit Card/Bank Details</h4>
					        	<ul class="tabcontentUL">
					         		<li><strong>Credit Card Type</strong>
					              		<label>
					              			<logic:notEmpty name="crmCapForm" property="additionalDetailsPojo.crcdType">
												<bean:define id="crcdType" name="crmCapForm" property="additionalDetailsPojo.crcdType"></bean:define>
					         					<logic:equal name="crcdType" value="G">Gold</logic:equal>
												<logic:equal name="crcdType" value="P">Platinum</logic:equal>
								 			</logic:notEmpty>
								 			<logic:empty name="crmCapForm" property="additionalDetailsPojo.crcdType">-</logic:empty>
							   			</label>
					        		</li>
					         		<li><strong>Credit Card No.</strong>
					               		<logic:notEqual name="crmCapForm" property="additionalDetailsPojo.crcdCardNo" value="0">
					             			<label><bean:write name="crmCapForm" property="additionalDetailsPojo.crcdCardNo" /></label>
					            		</logic:notEqual>
					            		<logic:equal name="crmCapForm" property="additionalDetailsPojo.crcdCardNo" value="0">-</logic:equal>
					         		</li>
					         		<li><strong>Credit Card Expiry Date</strong>
					               		<logic:notEmpty name="crmCapForm" property="additionalDetailsPojo.displayCrcdExpiryDate" >
					           				<label><bean:write name="crmCapForm" property="additionalDetailsPojo.displayCrcdExpiryDate" /></label>
					      				</logic:notEmpty>
					           			<logic:empty name="crmCapForm" property="additionalDetailsPojo.displayCrcdExpiryDate">-</logic:empty>
					         		</li>
					         		<li class="clear"><strong>Bank Name</strong>
					               		<logic:notEmpty name="crmCapForm" property="additionalDetailsPojo.bankName" >
						           			<label><bean:write name="crmRoles" property="displayEnum(BANK,${crmCapForm.additionalDetailsPojo.bankName})" scope="session" /></label>
					    	       		</logic:notEmpty>
					        	   		<logic:empty name="crmCapForm" property="additionalDetailsPojo.bankName">-</logic:empty>
					         		</li>
					         		<li><strong>Bank Branch</strong>
					              		<logic:notEmpty name="crmCapForm" property="additionalDetailsPojo.bankBranch" >
					           				<label><bean:write name="crmCapForm" property="additionalDetailsPojo.bankBranch" /></label>
					           			</logic:notEmpty>
					           			<logic:empty name="crmCapForm" property="additionalDetailsPojo.bankBranch">-</logic:empty>
					         		</li>
					         		<li><strong>Bank Account No.</strong>
					                	<logic:notEqual name="crmCapForm" property="additionalDetailsPojo.bankAccountNo" value="0">
					           				<label><bean:write name="crmCapForm" property="additionalDetailsPojo.bankAccountNo" /></label>
										</logic:notEqual>
										<logic:equal name="crmCapForm" property="additionalDetailsPojo.bankAccountNo" value="0">-</logic:equal>
					         		</li>
					         		<li><strong>Nature of Bank Account</strong>
					              		<label>
					              			<logic:notEmpty name="crmCapForm" property="additionalDetailsPojo.bankAccountType">
												<bean:define id="bankAccountType" name="crmCapForm" property="additionalDetailsPojo.bankAccountType"></bean:define>
					         					<logic:equal name="bankAccountType" value="S">Savings</logic:equal>
												<logic:equal name="bankAccountType" value="C">Current</logic:equal>
											</logic:notEmpty>
											<logic:empty name="crmCapForm" property="additionalDetailsPojo.bankAccountType">-</logic:empty>
								 		</label>
					         		</li>
					        	</ul>  
							</div>
					      	<!-- Network Details  -->
					      	<div class="floatl paddingTop10  clear" id="networkDetails">
					        	<h4>Partner Details</h4>
					        	<ul class="tabcontentUL">
					        <%-- <li><strong>Network & Connectivity Information </strong>
					              <label>	
										 <logic:notEmpty name="crmCapForm" property="networkDetailsPojo.nwDetails" >
										 	<bean:write name="crmRoles" property="displayEnum(networkConnectivityInfo,${ crmCapForm.networkDetailsPojo.nwDetails})" scope="session" />
					                    </logic:notEmpty>
							    </label>
					        </li> --%>
					        		<li><strong>Business Partner</strong> 
					               		<logic:notEmpty name="crmCapForm" property="customerDetailsPojo.businessPartner" >
					          		 		<label>
					          		 			<bean:write name="crmRoles" property="displayEnum(PartnerPojo,${ crmCapForm.customerDetailsPojo.businessPartner})" scope="session" />
					          		 		</label>
					          	 		</logic:notEmpty>
					          	 		<logic:empty name="crmCapForm" property="customerDetailsPojo.businessPartner">-</logic:empty>
					        		</li>
					        		<li><strong>Business Partner Code</strong> 
					               		<logic:notEmpty name="crmCapForm" property="partner.crmPartnerCode" >
					          		 		<label>
					          		 			<bean:write name="crmCapForm" property="partner.crmPartnerCode" />
					          		 		</label>
					          	 		</logic:notEmpty>
					          	 		<logic:empty name="crmCapForm" property="partner.crmPartnerCode">
					          	 		-
					          	 		</logic:empty>
					        		</li>
					        		</ul>
					        		<ul class="tabcontentUL">
					         		<li><strong>Sales Representative Name</strong>
					                	<logic:notEmpty name="crmCapForm" property="customerDetailsPojo.salesRepresentative">
					          				<label><bean:write name="crmCapForm" property="customerDetailsPojo.salesRepresentative"/></label>
					          			</logic:notEmpty>
					          			<logic:empty name="crmCapForm" property="customerDetailsPojo.salesRepresentative">
					          			-</logic:empty>
					         		</li>
					         		<li><strong>Sales Representative Code</strong>
                                      <logic:notEmpty name="crmCapForm" property="customerDetailsPojo.salesUser">
					                	<logic:notEmpty name="crmCapForm" property="customerDetailsPojo.salesUser.srCode">
					          				<label><bean:write name="crmCapForm" property="customerDetailsPojo.salesUser.srCode"/></label>
					          			</logic:notEmpty>
					          			<logic:empty name="crmCapForm" property="customerDetailsPojo.salesUser.srCode">-</logic:empty>
                                       </logic:notEmpty>
                                       <logic:empty name="crmCapForm" property="customerDetailsPojo.salesUser">-</logic:empty>
					         		</li>
					         <%--<li><strong>CRF Sign Date</strong>
					              <logic:notEmpty name="crmCapForm" property="networkDetailsPojo.displayCrfSignDate">
					           		  <label><bean:write name="crmCapForm" property="networkDetailsPojo.displayCrfSignDate" /></label>
					           	  </logic:notEmpty>
					           	 <logic:empty name="crmCapForm" property="networkDetailsPojo.displayCrfSignDate">NA</logic:empty>
					         </li>--%>
					         	</ul> 
							</div>
					     	<br class="clear" />
						</div>
					    <!-- end additional information-->
					</div>
					<!-- end additional information tab -->
					
				 <div id="networkConfiguration"  class="clear tab_content">
						<!-- Network Configuration -->
						<div class="inner_left_lead  marginleft10">
							<div class="floatl">
							<%! boolean showProduct = true; %>
							<logic:empty name="crmCapForm" property="crmNetworkConfigurations.serviceType">
								<% showProduct = false; %>
							 </logic:empty>
							<logic:notEmpty name="crmCapForm" property="crmNetworkConfigurations.serviceType">
							   <% showProduct = true; %>
							</logic:notEmpty>
					        	<h4>Network Partner</h4>
								<ul class="tabcontentUL">
									<li><strong> 
						               		<logic:notEmpty name="crmCapForm" property="customerDetailsPojo.npId">
							               		<logic:notEqual name="crmCapForm" property="customerDetailsPojo.npId" value="0">
							               			<label>
						          		 			<bean:write name="crmRoles" property="displayEnum(PartnerPojo,${crmCapForm.customerDetailsPojo.npId})" scope="session" />
						          		 		</label>
							               		</logic:notEqual>
							               		<logic:equal name="crmCapForm" property="customerDetailsPojo.npId" value="0"> - </logic:equal>
						          	 		</logic:notEmpty>
						          	 		<logic:empty name="crmCapForm" property="customerDetailsPojo.npId"> - </logic:empty>
					          	 		</strong>
					        		</li>
					        		<h4 class="clear <%= showProduct ? "hide1" : "" %>">Master Details</h4>
					        		<logic:notEmpty name="crmCapForm" property="crmPartnerNetworkConfig">
					        			<li class="<%= showProduct ? "hide1" : "" %>"><strong>Master Name</strong>
											<label>${ empty crmCapForm.crmPartnerNetworkConfig.masterName ? '-' : crmCapForm.crmPartnerNetworkConfig.masterName }</label>
										</li>
										<li class="<%= showProduct ? "hide1" : "" %>"><strong>NAS Port ID</strong>
											<label>${ empty crmCapForm.crmPartnerNetworkConfig.nasPortId ? '-' : crmCapForm.crmPartnerNetworkConfig.nasPortId }</label>
										</li>
										<li class="<%= showProduct ? "hide1" : "" %>"><strong>Pool Name</strong>
											<label>${ empty crmCapForm.crmPartnerNetworkConfig.poolName ? '-' : crmCapForm.crmPartnerNetworkConfig.poolName }</label>
										</li>
					        		</logic:notEmpty>
					        		<h4 class="clear">Network Configuration</h4>
					        		<logic:equal name="crmCapForm" property="customerDetailsPojo.product" value="BB">
					        			<li><strong>Option82</strong>
											<label>${ empty crmCapForm.crmNetworkConfigurations.option82 ? '-' : crmCapForm.crmNetworkConfigurations.option82 }</label>
										</li>
									 </logic:equal>                   
									<li><strong>ONT / RG / MDU Port configuration done</strong>
										<label>${ empty crmCapForm.crmNetworkConfigurations.ontRgMduDone ? '-' : crmCapForm.crmNetworkConfigurations.ontRgMduDone }</label>
									</li>
									<li><strong>Radius Customer ID</strong>
										<label>${ empty crmCapForm.crmNetworkConfigurations.radiusCustomerId ? '-' : crmCapForm.crmNetworkConfigurations.radiusCustomerId }</label>
									</li>
								 <%-- <logic:equal name="crmCapForm" property="crmNetworkConfigurations.serviceType" value="BB">
										<h4 class="clear">Network Inventory Detail</h4>
										<li><strong>Service Model</strong>
											<label>${ empty crmCapForm.crmNetworkConfigurations.serviceModel ? '-' : crmCapForm.crmNetworkConfigurations.serviceModel }</label>
										</li>
										<li><strong>Service Type</strong>
											<label>${ empty crmCapForm.crmNetworkConfigurations.serviceType ? 'NA' : crmCapForm.crmNetworkConfigurations.serviceType }</label>
										</li>
										<li><strong>ONT Details</strong>
											<label>${ empty crmCapForm.crmNetworkConfigurations.ontOnuPort ? 'NA' : crmCapForm.crmNetworkConfigurations.ontOnuPort }</label>
										</li>
										<li><strong>ONU Details</strong>
											<label>${ empty crmCapForm.crmNetworkConfigurations.ontOnuPort ? 'NA' : crmCapForm.crmNetworkConfigurations.ontOnuPort }</label>
										</li>
										<li><strong>VLAN Details</strong>
											<label>${ empty crmCapForm.crmNetworkConfigurations.vlanId ? 'NA' : crmCapForm.crmNetworkConfigurations.vlanId }</label>
										</li>
										<h4 class="clear">OLT Details</h4>
										<li><strong>OLT Node ID</strong>
											<label>${ empty crmCapForm.crmNetworkConfigurations.oltNoteId ? 'NA' : crmCapForm.crmNetworkConfigurations.oltNoteId }</label>
										</li>
										<li><strong>OLT Slot</strong>
											<label>${ empty crmCapForm.crmNetworkConfigurations.oltSlot ? 'NA' : crmCapForm.crmNetworkConfigurations.oltSlot }</label>
										</li>
										<li><strong>OLT Port</strong>
											<label>${ empty crmCapForm.crmNetworkConfigurations.oltPort ? 'NA' : crmCapForm.crmNetworkConfigurations.oltPort }</label>
										</li>
										<li><strong>OLT Sub Port</strong>
											<label>${ empty crmCapForm.crmNetworkConfigurations.oltSubPort ? 'NA' : crmCapForm.crmNetworkConfigurations.oltSubPort }</label>
										</li>
									</logic:equal> --%>
					    		</ul>
					    	</div>
							<br class="clear" />
						</div>
						<!-- End Network Configuration -->
			     </div>
			     <div id="deviceDetails"  class="clear tab_content">
						<!-- Device Details -->
						<div class="inner_left_lead  marginleft10">
							<div class="floatl">
					        	<h4>Service Partner</h4>
								<ul class="tabcontentUL">
									<li><strong>
						               		<logic:notEmpty name="crmCapForm" property="customerDetailsPojo.spId">
							               		<logic:notEqual name="crmCapForm" property="customerDetailsPojo.spId" value="0">
							               			<label>
						          		 			<bean:write name="crmRoles" property="displayEnum(PartnerPojo,${crmCapForm.customerDetailsPojo.spId})" scope="session" />
						          		 		</label>
							               		</logic:notEqual>
						          		 		<logic:equal name="crmCapForm" property="customerDetailsPojo.spId" value="0"> - </logic:equal>
						          	 		</logic:notEmpty>
						          	 		<logic:empty name="crmCapForm" property="customerDetailsPojo.spId"> - </logic:empty>
					          	 		</strong> 
					        		</li>
					        		<h4 class="clear">Device Details</h4>
				        			<li><strong>Wi-Fi Device Status</strong>
									  		<logic:notEmpty  name="crmCapForm" property="orderDetailsPojo.cpeStatus">
									  			<label><bean:write name="crmRoles" property="displayEnum(CPEStatus,${crmCapForm.orderDetailsPojo.cpeStatus})" scope="session" /></label>
									        </logic:notEmpty>
									</li>
									<li><strong>ONT / RG / MDU Port configuration done</strong>
										<label>${ empty crmCapForm.crmNetworkConfigurations.spOntRgMduDone ? '-' : crmCapForm.crmNetworkConfigurations.spOntRgMduDone }</label>
									</li>
									<li class="clear"><strong>Primary MAC Address</strong>
										<label>${ empty crmCapForm.crmNetworkConfigurations.currentCpeMacId ? '-' : crmCapForm.crmNetworkConfigurations.currentCpeMacId }</label>
									</li>
									<li><strong>Secondary MAC Address</strong>
										<label>${ empty crmCapForm.crmNetworkConfigurations.currentSlaveMacId ? '-' : crmCapForm.crmNetworkConfigurations.currentSlaveMacId }</label>
									</li>
									<li class="clear"><strong>CPE MAC ID</strong>
										<label>${ empty crmCapForm.crmNetworkConfigurations.currentCpeMacId ? '-' : crmCapForm.crmNetworkConfigurations.currentCpeMacId }</label>
									</li>
									<li><strong>Date on ISR</strong>
									  		<logic:notEmpty  name="crmCapForm" property="customerDetailsPojo.dateOnIsr">
									  			<label><bean:write name="crmRoles" property="toDate(${crmCapForm.customerDetailsPojo.dateOnIsr})"/></label>
									        </logic:notEmpty>
									        <logic:empty name="crmCapForm" property="customerDetailsPojo.dateOnIsr">-</logic:empty>
									</li>
									<li><strong>Activation Date</strong>
									  		<logic:notEmpty  name="crmCapForm" property="customerDetailsPojo.activationDate">
									  			<label><bean:write name="crmRoles" property="toDate(${crmCapForm.customerDetailsPojo.activationDate})"/></label>
									        </logic:notEmpty>
									        <logic:empty name="crmCapForm" property="customerDetailsPojo.activationDate">-</logic:empty>
									</li>
									<li class="clear"><strong>ISR Reference Number</strong>
										<logic:empty name="crmCapForm" property="customerDetailsPojo.isrReferenceNo">
									 		-
									 	</logic:empty>
										<logic:notEmpty name="crmCapForm" property="customerDetailsPojo.isrReferenceNo">
											<label><bean:write name="crmCapForm" property="customerDetailsPojo.isrReferenceNo" /></label>
										</logic:notEmpty>
									</li>
					    		</ul>
					    	</div>
							<br class="clear" />
						</div>
						<!-- End Device Details -->
			     </div>
				</div>
			</div>
		</form>
	</div>

