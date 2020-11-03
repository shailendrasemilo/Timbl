<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<%@taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<link href="css/displaytag.css" rel="stylesheet" type="text/css" />
<title>Insert title here</title>
<script type="text/javascript">
//show n hide content on radio GIS master
$(document).ready(function() {
var selectedGis = document.getElementsByName("manageGis")[0].value;
$(".stateHide").hide();
$(".cityHide").hide();
$(".areaHide").hide();
//$(".localityHide").hide();
if(null != selectedGis){
	selectedGis = selectedGis.replace("GIS","");
}
$("."+selectedGis+"Hide").show();
$('#height200').hide();
});
</script>
</head>
<body onload="populateState(this.value);">
<div class="loadingPopup hidden"></div>
<div id="section" >
<html:form action="/gisMaster"  method="post" styleId="searchGIS">
<html:hidden name="gisForm" property="hiddenGisOperation" styleId="hiddenGisOperation" value=""/>
<html:hidden property ="gisOptions" styleId="gisHidden" value="search"  />
<html:hidden name="gisForm" property="manageGis" value="${gisForm.manageGis}"/>
 <div class="section">
   <h2>Manage GIS Master</h2>   
   	<span class="success_message manageGisServerError" id="gisMessage">
		<logic:messagesPresent message="true" property="appMessage">
   			<html:messages id="message" message="true" property="appMessage">
			<bean:write name="message" />
			<br />
			</html:messages>
		</logic:messagesPresent>
		</span>	
    <div class="error_message" id="gisError">
		<html:errors property="appError"/>
	</div>
   <div class="inner_section ">   
   <div class="inner_left_gis floatl">
     <ul class="manageGIS">
     <!-- <li><span><input type="radio" name="manage" value="area"/> Manage Country</span></li> -->
     <logic:equal value="stateGIS" name="gisForm" property="manageGis">
     	<li class="active" >
     	<span>
      		<html:radio property="manageGis" value="stateGIS" name="gisForm" styleClass="manageRadio" styleId="idStateMenu"></html:radio>
      			 <label onclick="javascript:menuChange('idStateMenu')">Manage State</label>
      	</span>
      </li>
     </logic:equal>
     <logic:notEqual value="stateGIS" name="gisForm" property="manageGis">
     	<li>
      	<span>
      		<html:radio property="manageGis" value="stateGIS" name="gisForm" styleClass="manageRadio" styleId="idStateMenu"></html:radio>
      			 <label onclick="javascript:menuChange('idStateMenu')">Manage State</label>
      	</span>
      </li>
     </logic:notEqual>
      <logic:equal value="cityGIS" name="gisForm" property="manageGis">
      <li class="active">
      	<span>
      		<html:radio property="manageGis" value="cityGIS" name="gisForm" styleClass="manageRadio" styleId="idCityMenu"></html:radio>
      			 <label onclick="javascript:menuChange('idCityMenu')">Manage City</label>
      	</span>
      </li>
      </logic:equal>
      <logic:notEqual value="cityGIS" name="gisForm" property="manageGis">
      <li>
      	<span>
      		<html:radio property="manageGis" value="cityGIS" name="gisForm" styleClass="manageRadio" styleId="idCityMenu"></html:radio>
      			 <label  onclick="javascript:menuChange('idCityMenu')">Manage City</label>
      	</span>
      </li>
      </logic:notEqual>
      <logic:equal value="areaGIS" name="gisForm" property="manageGis">
      <li class="active">
      	<span>
      		<html:radio property="manageGis" value="areaGIS" name="gisForm"  styleClass="manageRadio" styleId="idAreaMenu"></html:radio>
      			 <label onclick="javascript:menuChange('idAreaMenu')">Manage Area</label>
      	</span>
      </li>
      </logic:equal>
      <logic:notEqual value="areaGIS" name="gisForm" property="manageGis">
      <li>
      	<span>
      		<html:radio property="manageGis" value="areaGIS" name="gisForm"  styleClass="manageRadio" styleId="idAreaMenu"></html:radio>
      			 <label onclick="javascript:menuChange('idAreaMenu')">Manage Area</label>
      	</span>
      </li>
      </logic:notEqual>
          
     </ul>
    </div>
   <div class="inner_left_gis floatl manageGISRight widthAuto" >
   <div id="stateGIS" class="stateHide">
   <logic:notEmpty name="gisForm" property="statePojoList">
   </logic:notEmpty>
   	   	<p class="floatl">
   	   		<strong>State</strong><br/>
   	   		<html:text name="gisForm"  property="state" styleClass="textbox"></html:text>
   	   	</p>
		<p class="floatl marginleft30">	<strong>Status</strong><br/>
   	   		<span class="dropdownWithoutJs"> 
  			<html:select name="gisForm"  property="stateStatus">
  			    <logic:notEmpty name="gisForm" property="statusList">
				<html:optionsCollection name="gisForm" property="statusList" label="contentName" value="contentValue"/>
				</logic:notEmpty>
				</html:select>	
			</span>
		</p>
		<p class="floatl marginleft30 margintop47">
			<logic:equal name="crmRoles" property="available(view_gis_master)" value="true" scope="session">
   	 			<html:link href="#" styleClass="gis_button" styleId="searchState">Go</html:link> 
   	 		</logic:equal>
   	 		<logic:equal name="crmRoles" property="available(create_gis_master)" value="true" scope="session">
   	 			<html:link href="#"  styleClass="gis_button marginleft6" styleId="addStateRow">Add New State</html:link>
   	 		</logic:equal>   	 		
   	   	</p>
   	   	<br class="clear"/>
     	<logic:notEmpty name="gisForm" property="stateDataList">
     	<p class="textright margintop20 ">   
     	<logic:equal name="crmRoles" property="available(update_gis_master,delete_gis_master)" value="true" scope="session">	   		
			<html:link href="javascript:gisOperation('State' ,'E')" styleClass="gis_button" styleId="addStateRow">Edit State</html:link>
		</logic:equal>
		<logic:equal name="crmRoles" property="available(create_gis_master)" value="true" scope="session">
			<html:link href="javascript:gisOperation('State','C')" styleClass="gis_button marginleft6" styleId="addStateRow">Copy State</html:link>
		</logic:equal>
     	</p>
		<div class="display_group">
    		<div class="stays-at-top inlineBlock">
    		 	
      			 <span class="manageText floatl marginleft30">State Name</span>
       			 <span class="manageText floatl paddingleft5">State Alias</span>
       			 <span class="manageText floatl paddingleft5">Created By</span>
       			 <span class="manageText floatl paddingleft10">Status</span>
       			<p class="clear"></p>
     		</div>
     		
     			<ul class="add_gis_group">
     				<%! int k=0; %>   
     				<% k=0; %>    		
      				<logic:iterate id="stateDataList" name="gisForm" property="stateDataList" indexId="ctr">
   						<% if(k%2 == 0){ %>							
   							<li class="first">
   								<% } else { %>
    						<li class="second">
    					<% } %>
       					<span class="marginright9 floatl"> 
       					<logic:equal name="crmRoles" property="available(create_gis_master,update_gis_master,delete_gis_master)" value="true" scope="session">      						
       						<logic:equal name="stateDataList" property="stateId" value="0">
       							<html:checkbox name="stateDataList" property="editable" value="true" indexed="true" onclick="return false" ></html:checkbox>
       						</logic:equal>
       						<logic:notEqual name="stateDataList" property="stateId" value="0">
       							<html:checkbox name="stateDataList" property="editable" value="true" indexed="true"></html:checkbox>
       						</logic:notEqual>
       					</logic:equal>
       					</span>
      					<logic:equal name="stateDataList" property="editable" value="false" >
       						<span class="manageText floatl paddingleft5"><bean:write name="stateDataList" property="stateName" /></span>
       					</logic:equal>
       					<logic:equal name="stateDataList" property="editable" value="true" >
       						<logic:equal name="stateDataList" property="stateId" value="0">
       							<span class="manageText floatl">
								<html:text property="stateName" name="stateDataList" styleClass="gisTextboxuppercase" indexed="true" maxlength="30" onkeyup="javascript:changeToUpperCase(this)"></html:text>
								</span>
       						</logic:equal>
       						<logic:notEqual name="stateDataList" property="stateId" value="0">
       							<span class="manageText floatl"><bean:write name="stateDataList" property="stateName"/></span>
       							<html:hidden name="stateDataList" property="lastModifiedBy" value="${sessionScope.userPojo.userId}" indexed="true"/>
       						</logic:notEqual>       						
       					</logic:equal>
       					<logic:equal name="stateDataList" property="editable" value="false" >
       						
       						<logic:notEmpty name="stateDataList" property="stateAlias">
       								<span class="manageText floatl paddingleft5">
       									<bean:write name="stateDataList" property="stateAlias"/>
       								</span>
							</logic:notEmpty>
       						<logic:empty name="stateDataList" property="stateAlias">
       							<span class="manageText floatl paddingleft5">
       								-
       							</span>
       						</logic:empty>
       					</logic:equal>
       					<logic:equal name="stateDataList" property="editable" value="true" >
       						<span class="manageText floatl marginleft6">
								<html:text property="stateAlias" name="stateDataList" styleClass="gisTextbox" indexed="true" maxlength="10"></html:text>
							</span>
       					</logic:equal>
       					
       					<logic:equal name="stateDataList" property="editable" value="false" >
       						<span class="manageText floatl paddingleft5"><bean:write name="stateDataList" property="createdBy"/></span>
       					</logic:equal>
       					
       					
       					<logic:equal name="stateDataList" property="editable" value="true" >
       						<span class="manageText floatl marginleft6">
       							<logic:equal name="stateDataList" property="stateId" value="0">
       							<html:text name="stateDataList" property="createdBy" value="${sessionScope.userPojo.userId}" readonly="true" styleClass="gisTextbox" indexed="true"></html:text>
       							</logic:equal>
       							<logic:notEqual name="stateDataList" property="stateId" value="0">
       							<html:text name="stateDataList" property="createdBy"  readonly="true" styleClass="gisTextbox" indexed="true"></html:text>
       							</logic:notEqual>
       						</span>       						 						
       					</logic:equal>
       					
       					<logic:equal name="stateDataList" property="editable" value="false" >
       					<span class="paddingleft10 floatl">
							<logic:notEmpty name="stateDataList" property="status">
								<bean:write name="crmRoles" property="displayEnum(AciveInactiveStatus,${stateDataList.status})" scope="session" />
							</logic:notEmpty>   
								
      						
      					</span>
      					</logic:equal>  
      					<logic:equal name="stateDataList" property="editable" value="true" >
      						<logic:equal name="crmRoles" property="available(delete_gis_master)" value="true" scope="session">
      						<span class=" floatl">
								<html:radio name="stateDataList" property="status" value="A" indexed="true">Active</html:radio>
								<html:radio name="stateDataList" property="status" value="I" indexed="true">Inactive</html:radio>
							</span>
							</logic:equal>
							<logic:equal name="crmRoles" property="available(delete_gis_master)" value="false" scope="session">
								<span class="paddingleft10 floatl">
								<logic:notEmpty name="stateDataList" property="status">
								<bean:write name="crmRoles" property="displayEnum(AciveInactiveStatus,${stateDataList.status})" scope="session" />
							</logic:notEmpty> 
      							</span>
							</logic:equal>
      					</logic:equal>
      					<logic:equal name="stateDataList" property="stateId" value="0">
        					<span>
        						<html:link href="javascript:deleteGISRow(${ctr})" styleClass="close" >
        						&nbsp;
       		 					<img src="images/bg/delete.png" align="absmiddle" title="delete"/></html:link>
       					 	</span>
       					</logic:equal>     					 
       					   					
						<p class="clear"></p>
						</li>
						<% k++; %>	 
      				</logic:iterate>
      				<html:hidden property="stateSize" styleId="stateSize" value='<%=k+""%>'/>    				
    			</ul>
      </div>
      <p class="textright margintop20"> 
      	<logic:equal name="crmRoles" property="available(create_gis_master,update_gis_master,delete_gis_master)" value="true" scope="session">
				<html:link href="#" styleClass="gis_button" styleId="addState">Save</html:link>
		</logic:equal>
	  </p>
      </logic:notEmpty>
      <logic:empty name="gisForm" property="stateDataList">
   
		    <p><strong> <html:errors property="noStateRecordFound"/> </strong></p>
      </logic:empty>
     
    </div>
   <div id="cityGIS" class="cityHide">
		<p>
			<span class="dropdown">
   				<strong>State</strong><br/>
				<span class="dropdownWithoutJs">    				
   				<logic:notEmpty name="gisForm" property="statePojoList">
   					<html:select styleId="statefor" property="stateId" >
  						<html:option value="0">All States</html:option>
				  		<html:optionsCollection name="gisForm" property="statePojoList" label="stateName" value="stateId"/>
					 </html:select>
   				</logic:notEmpty>
				</span>
     		</span>
    	</p>
     	<p class="clear floatl ">	
     		<strong>City </strong><br/>
   	   		<html:text property="city" styleClass="textbox"></html:text>
		</p>
		<p class=" floatl marginleft30">
   	   		<strong>Status</strong><br>
   	   		<span class="dropdownWithoutJs"> 
  			<html:select name="gisForm"  property="cityStatus">
				<html:optionsCollection name="gisForm" property="statusList" label="contentName" value="contentValue"/>
				</html:select>	
			</span>
		</p>
		<p class="floatl marginleft30 margintop47">	
		<logic:equal name="crmRoles" property="available(view_gis_master)" value="true" scope="session">
   	 		<html:link href="#" styleClass="gis_button" styleId="searchCity">Go</html:link>
   	 	</logic:equal>
   	 	<logic:equal name="crmRoles" property="available(create_gis_master)" value="true" scope="session">
   	 		<html:link href="#" styleClass="gis_button marginleft6" styleId="addCityRow">Add New City</html:link>
   	 	</logic:equal>
    	
     	</p> 
<br class="clear"/>    	
     <logic:notEmpty name="gisForm" property="cityDataList">
     	<p class="textright margintop20 ">
     	<logic:equal name="crmRoles" property="available(update_gis_master,delete_gis_master)" value="true" scope="session">
     		<html:link href="javascript:gisOperation('City' ,'E')" styleClass="gis_button">Edit City</html:link>&nbsp;&nbsp;
     	</logic:equal>
     		<logic:equal name="crmRoles" property="available(create_gis_master)" value="true" scope="session">
			<html:link href="javascript:gisOperation('City','C')" styleClass="gis_button" >Copy City</html:link>
			</logic:equal>
     	</p>
		<div class="display_group">
    		<div class="stays-at-top inlineBlock">
			
      			 <span class="manageText floatl marginleft30">City Name</span>
       			 <span class="manageText floatl paddingleft5">City Alias</span>
       			 <span class="manageText floatl paddingleft5">STD Code</span>
       			 <span class="manageText floatl paddingleft5">Created By</span>
       			 <span class="manageText floatl paddingleft10">Status</span>
       			<p class="clear"></p>
     		</div>
     		
     			<ul class="add_gis_group">
     				<%! int i=0; %>
     				<% i = 0;%>
      				<logic:iterate id="cityDataList" name="gisForm" property="cityDataList" indexId="ctr">
					<% if(i%2 == 0){ %>							
					<li class="first">
						<% } else { %>
						<li class="second">
					<% } %>
      					<span class="marginright9 floatl">
      					<logic:equal name="crmRoles" property="available(create_gis_master,update_gis_master,delete_gis_master)" value="true" scope="session">
      						<logic:equal name="cityDataList" property="cityId" value="0">
       							<html:checkbox name="cityDataList" property="editable" value="true" indexed="true" onclick="return false"></html:checkbox>
       						</logic:equal>
       						<logic:notEqual name="cityDataList" property="cityId" value="0">
       							<html:checkbox name="cityDataList" property="editable" value="true" indexed="true"></html:checkbox>
       						</logic:notEqual>
       					</logic:equal>
       					</span>
      					<logic:equal name="cityDataList" property="editable" value="false" >
       						<span class="manageText floatl paddingleft5"><bean:write name="cityDataList" property="cityName"/></span>
       					</logic:equal>
       					<logic:equal name="cityDataList" property="editable" value="true" >
       						<logic:equal name="cityDataList" property="cityId" value="0">
       							<span class="manageText floatl">
								<html:text property="cityName" name="cityDataList" styleClass="gisTextboxuppercase" indexed="true" maxlength="30" onkeyup="javascript:changeToUpperCase(this)"></html:text>
								</span>
       						</logic:equal>
       						<logic:notEqual name="cityDataList" property="cityId" value="0">
       							<span class="manageText floatl"><bean:write name="cityDataList" property="cityName"/></span>
       							<html:hidden name="cityDataList" property="lastModifiedBy" value="${sessionScope.userPojo.userId}" indexed="true"/>
       						</logic:notEqual>       						
       					</logic:equal>
       					<logic:equal name="cityDataList" property="editable" value="false" >
       					<logic:notEmpty name="cityDataList" property="cityAlias">
       								<span class="manageText floatl paddingleft5">
       									<bean:write name="cityDataList" property="cityAlias"/>
       								</span>
							</logic:notEmpty>
       						<logic:empty name="cityDataList" property="cityAlias" >
       							<span class="manageText floatl paddingleft5">
       								-
       							</span>
       						</logic:empty>
       					</logic:equal>
       					<logic:equal name="cityDataList" property="editable" value="true" >
       						<span class="manageText floatl">
								<html:text property="cityAlias" name="cityDataList" styleClass="gisTextbox" indexed="true" maxlength="10"></html:text>
							</span>
       					</logic:equal>
       					<logic:equal name="cityDataList" property="editable" value="false" >
       						<span class="manageText floatl paddingleft5"><bean:write name="cityDataList" property="stdCode"/></span>
       					</logic:equal>
       					<logic:equal name="cityDataList" property="editable" value="true" >
       						<span class="manageText floatl">
								<html:text name="cityDataList" property="stdCode"  styleClass="gisTextbox" indexed="true" maxlength="5" onclick="removeZeroFromNumber(this);" onfocus="removeZeroFromNumber(this);"></html:text>
							</span>
       					</logic:equal>
       					<logic:equal name="cityDataList" property="editable" value="false" >
       						<span class="manageText floatl paddingleft5"><bean:write name="cityDataList" property="createdBy"/></span>
       					</logic:equal>
       					<logic:equal name="cityDataList" property="editable" value="true" >
       						<span class="manageText floatl">
							<logic:equal name="cityDataList" property="cityId" value="0">
							<html:text name="cityDataList" property="createdBy" value="${sessionScope.userPojo.userId}" readonly="true" styleClass="gisTextbox" indexed="true"></html:text>
						</logic:equal>
							<logic:notEqual name="cityDataList" property="cityId" value="0">
       							<html:text name="cityDataList" property="createdBy"  readonly="true" styleClass="gisTextbox" indexed="true"></html:text>
								</logic:notEqual>
       						</span>       						 						
       					</logic:equal>
       					<logic:equal name="cityDataList" property="editable" value="false" >
       					<span class="paddingleft10 floatl">
							<logic:notEmpty name="cityDataList" property="status">
								<bean:write name="crmRoles" property="displayEnum(AciveInactiveStatus,${cityDataList.status})" scope="session" />
							</logic:notEmpty> 
      					</span>
      					</logic:equal>  
      					<logic:equal name="cityDataList" property="editable" value="true" >
      					<logic:equal name="crmRoles" property="available(delete_gis_master)" value="true" scope="session">
      						<span class=" floatl">
								<html:radio name="cityDataList" property="status" value="A" indexed="true">Active</html:radio>
								<html:radio name="cityDataList" property="status" value="I" indexed="true">Inactive</html:radio>
							</span>
							</logic:equal>
							<logic:equal name="crmRoles" property="available(delete_gis_master)" value="false" scope="session">
							<span class=" floatl">
							<logic:notEmpty name="cityDataList" property="status">
								<bean:write name="crmRoles" property="displayEnum(AciveInactiveStatus,${cityDataList.status})" scope="session" />
							</logic:notEmpty> 
      						</span>
							</logic:equal>
      					</logic:equal>  
      					
      					<logic:equal name="cityDataList" property="cityId" value="0">
        				<span>
        				<html:link href="javascript:deleteGISRow(${ctr})" styleClass="close" >
        				&nbsp;
       		 			<img src="images/bg/delete.png" align="absmiddle" title="delete" /></html:link>
       					 </span>
       					 </logic:equal>   
      					
      					
      					
      					   					     					
						<p class="clear"></p>
						</li>
						<% i++; %>	 
      				</logic:iterate>
      				<html:hidden property="citySize" styleId="citySize" value='<%=i+""%>'/>  
    			</ul>
      </div>
      <p class="textright margintop20"> 
      	<logic:equal name="crmRoles" property="available(update_gis_master,create_gis_master,delete_gis_master)" value="true" scope="session">
			<html:link href="#" styleClass="gis_button" styleId="addCity">Save</html:link>
			</logic:equal>
	 </p>
    </logic:notEmpty>
     <logic:empty name="gisForm" property="cityDataList">      
		  <p><strong> <html:errors property="noCityRecordFound"/> </strong></p>
      </logic:empty>
   </div>
   <div id="areaGIS" class="areaHide">
   		<p class="floatl">	
   			<span class="dropdown">
   				<strong> State </strong><br/>
				<span class="dropdownWithoutJs">
   				<logic:notEmpty name="gisForm" property="statePojoList">
   					<html:select styleId="stateforarea" property="stateIdForArea" onchange="populateCity('cityarea',this.value);">
   							<html:option value="0">All States</html:option>
					  		<html:optionsCollection name="gisForm" property="statePojoList" label="stateName" value="stateId"/>
					  	</html:select>
   				</logic:notEmpty>
				</span>
     		</span>
		</p>
		<p class="floatl marginleft30">	
     		<span class="dropdown">
			
     			<strong> City</strong><br/>
				<span class="dropdownWithoutJs">
				<logic:notEmpty name="gisForm" property="cityList">
   					<html:select styleId="cityarea" name="gisForm" property="cityId" >
  						<html:option value="0">All Cities</html:option>
				  		<html:optionsCollection name="gisForm" property="cityList" label="cityName" value="cityId"/>
					 </html:select>
   				</logic:notEmpty>
   				<logic:empty name="gisForm" property="cityList">
     				<html:select styleId="cityarea" name="gisForm" property="cityId">
   					</html:select>
   				</logic:empty>
				</span>
     		</span>
     	</p>
     	<p class="floatl clear">	
     		<strong>Area </strong><br/>
     		<html:text property="area" styleClass="textbox"></html:text>
		</p>
		<p class="floatl marginleft30">	
     		<strong>Status</strong>  	<br>	
	  		<span class="dropdownWithoutJs"> 
	  			<html:select name="gisForm"  property="areaStatus">
					<html:optionsCollection name="gisForm" property="statusList" label="contentName" value="contentValue"/>
					</html:select>	
			</span>
		</p>
		<p class="floatl marginleft30 margintop47">	
		<logic:equal name="crmRoles" property="available(view_gis_master)" value="true" scope="session">
   	 		<html:link href="#" styleClass="gis_button" styleId="searchArea">Go</html:link>
   	 	</logic:equal>
   	 	<logic:equal name="crmRoles" property="available(create_gis_master)" value="true" scope="session">
   	 		<html:link href="#" styleClass="gis_button marginleft6" styleId="addAreaRow">Add New Area</html:link>
   	 	</logic:equal>
   	
     	</p>    
<br class="clear"/>		
    <logic:notEmpty name="gisForm" property="areaDataList">
    	<p class="textright margintop20 "> 
    	<logic:equal name="crmRoles" property="available(update_gis_master,delete_gis_master)" value="true" scope="session">    		
     		<html:link href="javascript:gisOperation('Area' ,'E')" styleClass="gis_button">Edit Area</html:link>
     		</logic:equal>
     		<logic:equal name="crmRoles" property="available(create_gis_master)" value="true" scope="session">
			<html:link href="javascript:gisOperation('Area','C')" styleClass="gis_button marginleft6" >Copy Area</html:link>
			</logic:equal>
     	</p>
		<div class="display_group">
    		<div class="stays-at-top inlineBlock">
			
      			 <span class="manageText floatl marginleft30">Area Name</span>
       			 <span class="manageText floatl paddingleft5">Area Alias</span>
       			 <span class="manageText floatl paddingleft5">Created By</span>
       			 <span class="manageText floatl paddingleft10">Status</span>
       			<p class="clear"></p>
     		</div>
     			<ul class="add_gis_group">
     				<%! int j=0; %>
     				<%j = 0; %>
      				<logic:iterate id="areaDataList" name="gisForm" property="areaDataList" indexId="ctr">
  					<% if(j%2 == 0){ %>							
						<li class="first">
						<% } else { %>
						<li class="second">
       				<% } %>
       						<span class="marginright9 floatl">
       						<logic:equal name="crmRoles" property="available(create_gis_master,update_gis_master,delete_gis_master)" value="true" scope="session">
       							<logic:equal name="areaDataList" property="areaId" value="0">
       								<html:checkbox name="areaDataList" property="editable" value="true" indexed="true" onclick="return false"></html:checkbox>
       							</logic:equal>
       							
       							<logic:notEqual name="areaDataList" property="areaId" value="0">
       							
       								<html:checkbox name="areaDataList" property="editable" value="true" indexed="true"></html:checkbox>
       							</logic:notEqual>
       							</logic:equal>
       						</span>
      					<logic:equal name="areaDataList" property="editable" value="false" >
       						<span class="manageText floatl paddingleft5"><bean:write name="areaDataList" property="area"/></span>
       					</logic:equal>
       					<logic:equal name="areaDataList" property="editable" value="true" >
       						<logic:equal name="areaDataList" property="areaId" value="0">
       							<span class="manageText floatl">
								<html:text property="area" name="areaDataList" styleClass="gisTextboxuppercase" indexed="true" maxlength="30" onkeyup="javascript:changeToUpperCase(this)"></html:text>
								</span>
       						</logic:equal>
       						<logic:notEqual name="areaDataList" property="areaId" value="0">
       							<span class="manageText floatl paddingleft5"><bean:write name="areaDataList" property="area"/></span>
       							<html:hidden name="areaDataList" property="lastModifiedBy" value="${sessionScope.userPojo.userId}" indexed="true"/>
       						</logic:notEqual>       						
       					</logic:equal>
       					<logic:equal name="areaDataList" property="editable" value="false" >
       							<logic:notEmpty name="areaDataList" property="areaAlias">
       								<span class="manageText floatl paddingleft5">
       									<bean:write name="areaDataList" property="areaAlias"/>
									</span>
							</logic:notEmpty>
       						<logic:empty name="areaDataList" property="areaAlias">
       							<span class="manageText floatl paddingleft5">
       								-
       							</span>
       						</logic:empty>
       					</logic:equal>
       					<logic:equal name="areaDataList" property="editable" value="true" >
       						<span class="manageText floatl">
								<html:text property="areaAlias" name="areaDataList" styleClass="gisTextbox" indexed="true" maxlength="10"></html:text>
							</span>
       					</logic:equal>
       					<logic:equal name="areaDataList" property="editable" value="false" >
       						<span class="manageText floatl paddingleft5"><bean:write name="areaDataList" property="createdBy"/></span>
       					</logic:equal>
       					<logic:equal name="areaDataList" property="editable" value="true" >
       						<span class="manageText floatl">
							<logic:equal name="areaDataList" property="areaId" value="0">
       							<html:text name="areaDataList" property="createdBy" value="${sessionScope.userPojo.userId}" readonly="true" styleClass="gisTextbox" indexed="true"></html:text>
							</logic:equal>
							<logic:notEqual name="areaDataList" property="areaId" value="0">
       							<html:text name="areaDataList" property="createdBy"  readonly="true" styleClass="gisTextbox" indexed="true"></html:text>
							</logic:notEqual>
       						</span>       						 						
       					</logic:equal>
       					<logic:equal name="areaDataList" property="editable" value="false" >
       					<span class="paddingleft5 floatl">
							<logic:notEmpty name="areaDataList" property="status">
								<bean:write name="crmRoles" property="displayEnum(AciveInactiveStatus,${areaDataList.status})" scope="session" />
							</logic:notEmpty> 
      					</span>
      					</logic:equal>  
      					<logic:equal name="areaDataList" property="editable" value="true" >
      					<logic:equal name="crmRoles" property="available(delete_gis_master)" value="true" scope="session">
      						<span class=" floatl">
								<html:radio name="areaDataList" property="status" value="A" indexed="true">Active</html:radio>
								<html:radio name="areaDataList" property="status" value="I" indexed="true">Inactive</html:radio>
							</span>
						 </logic:equal>
						 <logic:equal name="crmRoles" property="available(delete_gis_master)" value="false" scope="session">
						 <span class=" floatl">
							<logic:notEmpty name="areaDataList" property="status">
								<bean:write name="crmRoles" property="displayEnum(AciveInactiveStatus,${areaDataList.status})" scope="session" />
							</logic:notEmpty> 
      					</span>
						 </logic:equal>
      					</logic:equal>   
      					
      					<logic:equal name="areaDataList" property="areaId" value="0">
        				<span>
        				<html:link href="javascript:deleteGISRow(${ctr})" styleClass="close" >
        				&nbsp;
       		 			<img src="images/bg/delete.png" align="absmiddle" title="delete" /></html:link>
       					 </span>
       					 </logic:equal>   
      					
      					  					     					
						<p class="clear"></p>
						</li>
						<% j++; %>	 
      				</logic:iterate>
      				<html:hidden property="areaSize" styleId="areaSize" value='<%=j+""%>'/> 
    			</ul>
      </div>
       <p class="textright margintop20"> 
       	<logic:equal name="crmRoles" property="available(update_gis_master,create_gis_master,delete_gis_master)" value="true" scope="session">
			<html:link href="#" styleClass="gis_button" styleId="addArea">Save</html:link>
			</logic:equal>
	 	</p>
      </logic:notEmpty> 
       <logic:empty name="gisForm" property="areaDataList">
      	
		  <p><strong> <html:errors property="noAreaRecordFound"/> </strong></p>
      </logic:empty>    
   </div>
   
 </div>
 <p class="clear"></p>
 </div>
 </div>
 <html:hidden name="gisForm" property="rowCounter" styleId="rowIndex_create" /> 
 </html:form>
</div>

</body>
</body>
