<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<%@taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@taglib uri="http://struts.apache.org/tags-nested" prefix="nested"%>
<link href="css/colorbox.css" rel="stylesheet" />
<script src="javascript/jquery.colorbox.js"></script>
<script>
	$( document ).ready(function(){
		$(".iframeParam").colorbox({iframe:true, width:"750px", height:"450px"});
	});
</script>

	<div id="section" >
  <html:form action="/parameterGroup" styleId="parameterGroup" >
		
		<logic:empty name="parameterGroupForm" property="groupPojo">
 
 <div class="section">
   <h2>Parameter group</h2>
   <div class="inner_section">
  
  		 <span class="error_message" style="margin-right: 40cm; ">
  		 		<html:errors property="appError"/>
  		 		<html:errors property="ParameterGroup"/>
		</span>
				
			<span class="success_message" style="margin-right: 40cm; ">
   				<logic:messagesPresent message="true" property="appMessage">
				<html:messages id="msg" message="true" property="appMessage">
				<bean:write name="msg" />
				</html:messages>
				</logic:messagesPresent>
			</span>
   <div class="inner_left floatl createUserLeft">
   <p class=""><b>Parameter Group Name</b> 
				<html:text styleClass="textbox" property="parameterGroupName" styleId="parameter_name"></html:text>
				
	</p>
     <p class=""><b>Parameter Group Description</b> 
			<html:textarea styleClass="textarea" property="parameterGroupDescription" styleId="parameter_description"></html:textarea>
	 </p>
     
   <div id="role_group_view">
      <span class="addbtn_role_group"><a href="javascript:void(0)" id="addParameterRow" class="grey_button_add" ><span>Add</span></a></span>
    <div class="display_group">
      <div class="stays-at-top">
       
        <span class="heading_text">   Parameter Name</span> 
       
        <span class="heading_text marginleft131"> Parameter Value </span>  
          <span class="heading_text marginleft100"><a href= 'parameterGroup.do?method=showDetails'class="iframeParam">Show Parameter Details</a></span> 
     </div>
    <ul class="add_parameter_group" id="parameterGroupList">
					<logic:notEmpty name="parameterGroupForm" property="accessGroups">
							<logic:iterate id="accessGroups" name="parameterGroupForm" property="accessGroups" indexId="ctr">
							
							<logic:equal name="accessGroups" property="editable" value="true">
							<li class="${ctr % 2 eq 0 ? 'first' : 'second' }">
       
								<html:hidden name="accessGroups" property="editable" value="true" indexed="true"/>
								<html:hidden name="accessGroups" property="status" value="${accessGroups.status}" indexed="true"/>
        						<span class="dropdownWithoutJs">
            						<html:select name="accessGroups" property="crmParameter.parameterId" indexed="true">
											<html:optionsCollection name="parameterGroupForm" property="parameterPojos" label="parameterName" value="parameterId" />
									</html:select>
        						</span>
        
       					 		<span class="marginleft13 inlineBlock verticalAlignTop">
       					 			<html:text name="accessGroups" property="parameterValue" styleClass="textbox"  indexed="true">
									</html:text> 
								</span>
        						<span class="marginleft13 inlineBlock">
        							<html:link href="javascript:deleteAccessGroupFromParameter(${ctr})"  styleClass="close" >
        								<img src="images/bg/delete.png" align="absmiddle" title="delete"/>
        							</html:link>
        						</span>
     				 			</li>
     				 		</logic:equal>
     				 		<logic:equal name="accessGroups" property="editable" value="false">
									<li>
										<html:checkbox name="accessGroups" property="editable" value="true" indexed="true" onclick="return false"></html:checkbox>
										<bean:write name="accessGroups" property="crmParameter.parameterName" /> 
										<bean:write name="accessGroups" property="parameterValue" />
									</li>
							</logic:equal>
							
							</logic:iterate>
							</logic:notEmpty>
					</ul>
      </div>
     </div> 
    </div>
   
    <div class="floatr inner_right">
     <a href="#" id="submit_CreateParameterGroup" class="main_button">Save</a>
    </div>
    <p class="clear"></p>
     </div>
   <div>
  
   </div>
   
   
  
 </div>
 
 </logic:empty>
 
<!-- ------------------------------------------------------- -->


	<logic:notEmpty name="parameterGroupForm" property="groupPojo">
 
		 <div class="section">
		  <h2>Update Parameter Group</h2>
		 	<span class="error_message">
				<html:errors property="appError"/>
				<html:errors property="ParameterGroup"/>
				
			</span>		
			<span class="success_message">
				  <html:messages id="msg" message="true" property="appMessage">
					<bean:write name="msg" />
				 </html:messages>
			</span>			
		   <div class="inner_section">		   
		   <div class="inner_left floatl createUserLeft">
		   <p><b>Parameter Group Name:</b> 
		   				<span class="showTextbox">
						<bean:write name="parameterGroupForm" property="parameterGroupName" />
						</span>
			</p>
		     <p><b>Parameter Group Description:</b> 
					<html:textarea styleClass="textarea" property="parameterGroupDescription" styleId="parameter_description"></html:textarea>
			</p>
		     
		   <div id="role_group_view">
		      <span class="addbtn_role_group"><a href="javascript:void(0)" id="addParameterRow" class="grey_button_add" ><span>Add</span></a></span>
		    <div class="display_group">
		      <div class="stays-at-top">
		       
		        <span class="heading_text">   Parameter Name</span> 
		       
		        <span class="heading_text marginleft131"> Parameter Value </span>   
		     </div>
		    <ul class="add_parameter_group" id="parameterGroupList">
					<logic:notEmpty name="parameterGroupForm" property="accessGroups">
							<logic:iterate id="accessGroups" name="parameterGroupForm" property="accessGroups" indexId="ctr">
							<logic:equal name="accessGroups" property="editable" value="true">
					 			<li class="first">
								<html:hidden name="accessGroups" property="editable" value="true" indexed="true"/>										
								<logic:notEqual name="accessGroups" property="recordId" value="0">
										<span class="showDropbox">
										
										<bean:write name="accessGroups" property="crmParameter.parameterName" />
										</span>
										
										<html:hidden name="accessGroups" property="recordId" indexed="true" value="${accessGroups.recordId}"/>
										<html:hidden name="accessGroups" property="crmParameter.parameterId" indexed="true" value="${accessGroups.crmParameter.parameterId}"/>
										<html:hidden name="accessGroups" property="crmParameter.parameterName" indexed="true" value="${accessGroups.crmParameter.parameterName}"/>
										<html:hidden name="accessGroups" property="crmParameter.status" indexed="true" value="${accessGroups.crmParameter.status}"/>
								</logic:notEqual> 
								
								<logic:equal name="accessGroups" property="recordId" value="0">
     										<span class="dropdownWithoutJs">
         										<html:select name="accessGroups" property="crmParameter.parameterId" indexed="true">
											<html:optionsCollection name="parameterGroupForm" property="parameterPojos" label="parameterName" value="parameterId" />
										</html:select>
     										</span>
     									</logic:equal>
        
       					 		<span class="marginleft13 inlineBlock verticalAlignTop">
       					 			<html:text name="accessGroups" property="parameterValue" styleClass="textbox" value="${accessGroups.parameterValue}" indexed="true">
									</html:text> 
								</span>
								<span class="marginleft13 inlineBlock"> 
									<html:radio name="accessGroups" property="status" value="A" indexed="true">Active</html:radio>
									<html:radio name="accessGroups" property="status" value="I" indexed="true">Inactive</html:radio> 
								</span>
								
								<logic:equal name="accessGroups" property="recordId" value="0">
        						<span class="marginleft13 inlineBlock ">
        							<html:link href="javascript:deleteAccessGroupFromParameter(${ctr})"  styleClass="close" >
        								<img src="images/bg/delete.png" align="absmiddle" title="delete"/>
        							</html:link>
        						</span>
        						</logic:equal>
     				 			</li>
     				 		</logic:equal>
     				 		
							</logic:iterate>
							</logic:notEmpty>
					</ul>
		      </div>
		     </div> 
		    </div>
		   
		    <div class="floatr inner_right">
		   <a href="#" id="submit_UpdateParameterGroup" class="main_button">Update</a>
		    </div>
		    <p class="clear"></p>
		     </div>
		   <div>
		  
		   </div>
		   
		   
		  
		 </div>
		 
 </logic:notEmpty>
 <html:hidden name="parameterGroupForm" property="rowCounter" styleId="rowIndex_createParameterGroup" />
</html:form>
</div>

