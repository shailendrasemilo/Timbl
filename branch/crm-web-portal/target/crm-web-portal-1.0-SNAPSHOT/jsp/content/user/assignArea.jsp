<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<%@taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@taglib uri="http://struts.apache.org/tags-nested" prefix="nested"%>


<style>
	#role_group_view {width:845px}
</style>
<script language="javascript" type="text/javascript">
    //this code handles the F5/Ctrl+F5/Ctrl+R
   $(function () {  
        $(document).keydown(function (e) {  
		if((e.which || e.keyCode) == 116 || (e.which || e.keyCode) == 82)
            return false;
        });  
    });  
</script>




<body>
<div id="section" >
<html:form action="/roleManagement" >
 <div class="section">
   <h2>Assign Area</h2>
   <div class="inner_section">
   
    
	
	
	 <span class="error_message"><html:errors property="appError" /></span>
   <span class="success_message">
					<logic:messagesPresent message="true" >
								<html:messages id="message" message="true"  >
									<bean:write name="message" />
								</html:messages>
					</logic:messagesPresent>
					</span>
		 </p>
    <div class="inner_left_lead floatl marginleft10">
   <p><strong>User ID</strong>
   <span class="showTextbox">
   <logic:notEmpty name="searchUserPojo" scope="session">
   <bean:write name="searchUserPojo" property="userId"/>
   
   </logic:notEmpty>
   </span>
   </p>
     
   <div id="role_group_view">
   
   <span class="addbtn_role_group"><a href="javascript:void(0)" id="idAssignArea" class="grey_button_add"><span>Add</span></a></span>
    <!-- <span class="addbtn_role_group">
      <input name="" type="button" value="Add" class="grey_button_add"" onclick="addAreaMapping();"  style="margin: -14px 10px 56px 0;"> 
      </span>-->
    <div class="display_group">
      <div class="stays-at-top">
       <span class="heading_text" style="width:200px; padding:5px; margin-left:24px;">   State  </span> 
		 <span class="heading_text" style="width:205px; padding:5px; ">   City  </span> 
       
        <span class="heading_text " style="width:210px; padding:5px;"> Area  </span>   
      
     </div>
     <ul class="add_parameter_group">
      <%int j=0; %>
     		<logic:notEmpty name="loginForm" property="areaMappingPojos">
     
      
							<logic:iterate id="areaMappingPojos" name="loginForm" property="areaMappingPojos" indexId="ctr">
					 		<li class="${ctr % 2 eq 0 ? 'first' : 'second' }">
       							<logic:equal name="areaMappingPojos" property="mappingId" value="0">
	       							<html:hidden name="areaMappingPojos" property="userId" value="${searchUserPojo.userId}" indexed="true"/>
	       							<html:hidden name="areaMappingPojos" property="lastModifiedBy" value="${userPojo.userId}" indexed="true"/>
	    							<html:hidden name="areaMappingPojos" property="createdBy" value="${userPojo.userId}" indexed="true"/>
									<html:hidden name="areaMappingPojos" property="editable" value="true" indexed="true" />
									<html:hidden name="areaMappingPojos" property="status" value="A" indexed="true"/>
									<span class="dropdownWithoutJs">
									<html:select name="areaMappingPojos" property="stateId" indexed="true" styleId="stateId${ctr}" onchange="populateCityAssignArea( 'city${ctr}','area${ctr}', this.value, 'stateId${ctr}' )" indexed="true">
									 <html:option value="0">Please Select</html:option>
										<logic:notEmpty name="loginForm" property="stateDataList" >
												<html:optionsCollection name="loginForm" property="stateDataList" label="stateName" value="stateId"  />
												</logic:notEmpty >
										</html:select>
									</span>
									<span class="dropdownWithoutJs">
									<html:select name="areaMappingPojos" property="cityId" indexed="true" styleId="city${ctr}" onchange="populateAreaAssignArea('area${ctr}',this.value ,'city${ctr}');" >
									 <html:option value="0">Please Select</html:option>
										<logic:notEmpty name="areaMappingPojos" property="cityList" >
												<html:optionsCollection name="areaMappingPojos" property="cityList" label="cityName" value="cityId" />
												</logic:notEmpty >
												
										</html:select>
									</span>
									<span class="dropdownWithoutJs">
	       					 			<html:select name="areaMappingPojos" property="areaId" indexed="true"  styleId="area${ctr}" >
										<html:option value="0">Please Select</html:option>
										<logic:notEmpty name="areaMappingPojos" property="areaList" >
												<html:optionsCollection name="areaMappingPojos" property="areaList" label="area" value="areaId" />
												</logic:notEmpty >
										</html:select>
									</span>									
									<span class="marginleft13 inline">
	        							<html:link href="javascript:deleteMapping(${ctr})"  styleClass="close" >
	        								<img src="images/bg/delete.png" align="absmiddle" title="delete"/>
	        							</html:link>
	        						</span>
								</logic:equal>		
		<logic:notEqual name="areaMappingPojos" property="mappingId" value="0">	
		<span class="showTextbox" style="width:184px">
	    		    <bean:write name="areaMappingPojos" property="stateName" />
	    		       <html:hidden name="areaMappingPojos" property="stateId" styleId="state${ctr}" />
		</span>
		<span class="showTextbox" style="width:184px">
	   		   <bean:write name="areaMappingPojos" property="cityName" />
	   		     <html:hidden name="areaMappingPojos" property="cityId" styleId="city${ctr}" />
		</span>
		<span class="showTextbox" style="width:184px"> 
			    <bean:write name="areaMappingPojos" property="areaName" />
			     <html:hidden name="areaMappingPojos" property="areaId" styleId="area${ctr}" />
		</span>
		<logic:notEqual name="areaMappingPojos" property="mappingId" value="0">
                          <span class="paddingtopbottom5"> <html:radio name="areaMappingPojos" property="status" value="A" indexed="true">Active</html:radio>
                            <html:radio name="areaMappingPojos" property="status" value="I" indexed="true">Inactive</html:radio>
                          </span>
                        </logic:notEqual>
	    <html:hidden name="areaMappingPojos" property="createdBy" indexed="true"/>
		<html:hidden name="areaMappingPojos" property="lastModifiedBy" value="${userPojo.userId}" indexed="true"/>
        <html:hidden name="areaMappingPojos" property="stateId" indexed="true" styleId='stateId${ctr}'/>
	    <html:hidden name="areaMappingPojos" property="cityId"  indexed="true" styleId='city${ctr}'/>
	    <html:hidden name="areaMappingPojos" property="areaId"  indexed="true" styleId='area${ctr}'/>
	    <html:hidden name="areaMappingPojos" property="mappingId" value="${areaMappingPojos.mappingId}" indexed="true" />
		</logic:notEqual>					
								
     				 			</li>
     				 		<%j=j+1; %>
     				 				 		
							</logic:iterate>
							</logic:notEmpty>
							  <logic:empty name="loginForm" property="areaMappingPojos">
							  <span class="error_message"><br/>
							    &nbsp;Please click on 'Add' button to Assign Area.
							  </span>
							  </logic:empty>			  
     </ul>
      </div>
     </div> 
    </div>
   <div class=" floatl ">
    <div class=""></div>
  <div class=""></div>
   
   </div>    
    <div class="floatr inner_right">
     <a href="#" id="" onclick="submitAssignArea(<%=j %>);" class="main_button_multiple">Assign Area</a>
    </div>
    <p class="clear"></p>
     </div>
   <div>
  
   </div>
   
   
  
 </div>
 <html:hidden name="loginForm" property="rowCounter" styleId="rowIndex" />
 </html:form>
</div>
</body>


