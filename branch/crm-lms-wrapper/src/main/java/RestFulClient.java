import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.http.HttpEntity;
import org.springframework.web.client.RestTemplate;

import com.np.lms.rest.dto.LeadRequestDto;
import com.np.lms.rest.dto.LeadResponsedto;
import com.np.lms.rest.dto.MasterRequestDto;
import com.np.lms.rest.dto.MasterResponsedto;
import com.np.lms.rest.dto.UserRequestDto;
import com.np.lms.rest.dto.UserResponseDto;
import com.np.tele.crm.constants.CRMDisplayListConstants;
import com.np.tele.crm.constants.CRMParameter;
import com.np.tele.crm.constants.CRMRequestType;
import com.np.tele.crm.constants.CrmActionEnum;
import com.np.tele.crm.constants.IAppConstants;
import com.np.tele.crm.constants.ServiceParameter;
import com.np.tele.crm.pojos.AreaPojo;
import com.np.tele.crm.pojos.CityPojo;
import com.np.tele.crm.pojos.CrmUserPojo;
import com.np.tele.crm.pojos.LMSPojo;
import com.np.tele.crm.pojos.LocalityPojo;
import com.np.tele.crm.pojos.PartnerPojo;
import com.np.tele.crm.pojos.RemarksPojo;
import com.np.tele.crm.pojos.RolesPojo;
import com.np.tele.crm.pojos.SocietyPojo;
import com.np.tele.crm.pojos.StatePojo;

public class RestFulClient
{
    private static void getState()
    {
        /*  
          SocietyRequestPojo locality = new SocietyRequestPojo();
          locality.setStateId( 1 );
          locality.setCityId( 1 );
          locality.setAreaId( 1 );
          locality.setLocalityId( 2 );
           }
         MasterRequestDto dto = new MasterRequestDto();
         dto.setOpretaion( ServiceParameter.SEARCH.getParameter() );
         dto.setServiceParam( SocietyPojo.class.getSimpleName() );
         dto.setStateId((short) 1 );
         dto.setCityId((short) 1 );
         dto.setAreaId((short) 1 );
         dto.setLocalityId((short)  2 );*/
        /*LeadRequestDto dto = new LeadRequestDto();
              dto.setOpretaion( ServiceParameter.LIST.getParameter() );
              LMSPojo pojo = new LMSPojo();
              pojo.setLeadId( "" );
              dto.setLeadPojo( pojo );*/
        /*  LeadRequestDto dto = new LeadRequestDto();
          dto.setOpretaion( ServiceParameter.CREATE.getParameter() );
          LMSPojo pojo = new LMSPojo();
          pojo.setContactNumber( 9873314540 );
          pojo.setLeadSource( CRMDisplayListConstants.APP.getCode() );
          pojo.setCreatedBy( "9873314540" );
          dto.setLeadPojo( pojo );
          dto.setClientIPAddress( "192.1168.1.164" );*/
        /*UserRequestDto dto = new UserRequestDto();
       // dto.setOpretaion( IAppConstants.CHANGE_PASSWORD );
         CrmUserPojo pojo=new CrmUserPojo();
         pojo.setEmailId( "rubina.hasrat@gmail.com" );
         pojo.setUserId( "Rubina.Hasrat" );
         dto.setCrmUserDetailsPojo( pojo );
        dto.setUserID( "Rubina.Hasrat" );
       dto.setPassword( "Rubina@122" );//Rubina@12
*/      //  dto.setNewPassword("Rubina@122" );
       /* List<String>list=new ArrayList<String>();
        list.add("SALES COORDINATOR"  );*/
       // list.add( "AREA MANAGER" );
        //list.add( "SALES" );
       // System.out.println(list.size());
        /*MasterRequestDto dto = new MasterRequestDto();
        dto.setOperation( CRMParameter.PERSONAL.getParameter());
        dto.setUserId( "nawab");
        dto.setRequestType( CRMRequestType.LEAD.getRequestCode()  );
        dto.setServiceParam( IAppConstants.ASSIGN );
        dto.setCurrentStage( "SC" );
        dto.setLeadId( "L35");
        */
        
        
        /*RestTemplate restTemplate = new RestTemplate();
        HttpEntity<MasterRequestDto> request = new HttpEntity<MasterRequestDto>( dto );
        MasterResponsedto response = restTemplate.postForObject( "http://192.168.1.164:8080/crm-lms-wrapper/inboxAPI",
                                                                 request, MasterResponsedto.class );
        System.out.println(response.getStatusCode());*/
      //  dto.setFunctionalBin( list);
       
        MasterRequestDto dto = new MasterRequestDto();
        dto.setOperation(ServiceParameter.CLOSE.getParameter());
        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<MasterRequestDto> request = new HttpEntity<MasterRequestDto>( dto );
        MasterResponsedto response = restTemplate.postForObject( "http://192.168.1.164:8080/crm-lms-wrapper/actionListAPI",
                                                               request, MasterResponsedto.class );
        System.out.println(response.getCrmRcaReasonsList().size());
      
        /*for(StatePojo pojo:response.getStatePojosList()){
            System.out.println("State Name"+pojo.getStateName());
            System.out.println("City List"+pojo.getCities());
        }*/
       // System.out.println( response );
        /*  for(LMSPojo lmspojo:response.getSuccessInsertPojos()){
              System.out.println(lmspojo);
          }*/
        /*String binArray[]=new RolesPojo().getFunctionalBin( ",216,217,219,220,221,222,223,225,233,234," ).split( "," );
        System.out.println(( Arrays.asList(binArray ).contains( "216" )));*/
    }

    public static void main( String[] args )
    {
        try
        {
            getState();
        }
        catch ( Exception ex )
        {
            ex.printStackTrace();
        }
        //System.out.println(CrmActionEnum.GENERATED.getActionName());
    }
}
