package com.np.tele.crm.qrc.action;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.log4j.Logger;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.type.CollectionType;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class TestAPI
{
    private static final Logger LOGGER = Logger.getLogger( TestAPI.class );

    public static void main( String[] args )
    {
        getRIDetails( "AGR-AGP-NCN-MST08" );
    }

    public static RiMasterData getRIDetails( String masterName )
    {
        RiMasterData response = new RiMasterData();
        try
        {
            String url = "http://192.168.1.104:8080/timbl/api/v1/device?device=" + masterName;
            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.setContentType( MediaType.APPLICATION_JSON );
            httpHeaders.setAccept( Arrays.asList( MediaType.APPLICATION_JSON ) );
            HttpEntity entity = new HttpEntity( httpHeaders );
            RestTemplate restTemplate = new RestTemplate();
            //  restTemplate.getMessageConverters().add( new MappingJacksonHttpMessageConverter() );
            ResponseEntity<String> response1 = restTemplate.getForEntity( url, String.class );
            LOGGER.info( "Before convert Response:: " + response1.getBody() );
            ObjectMapper mapper = new ObjectMapper();
           /* Map<String, Object> object = mapper.readValue( response1.getBody(), new TypeReference<Map<String, Object>>()
            {} );
            for ( Map.Entry<String, Object> entry : object.entrySet() )
            {
                System.out.println( "Key : " + entry.getKey() + " Value is:" + entry.getValue() );
                if ( entry.getValue() instanceof LinkedHashMap )
                {
                    
                    System.out.println( "True" );
                }
            }*/
            response = mapper.readValue( response1.getBody(), RiMasterData.class );
           LOGGER.info( "After Response covert:: " + response.toString() );
            //response.setDevice( device );
           // response.setStatusCode( CRMServiceCode.CRM001.getStatusCode() );
            //response.setStatusDesc( CRMServiceCode.CRM001.getStatusDesc() );
        }
        catch ( Exception e )
        {
            //response.setStatusCode( CRMServiceCode.CRM300.getStatusCode() );
            //response.setStatusDesc( CRMServiceCode.CRM300.getStatusDesc() );
            //response.setApiErrorCode( e.getMessage() );
           // return response;
            // TODO Auto-generated catch block
        }
        return response;
    }

    public static <T> List<T> jsonArrayToObjectList( String json, Class<T> tClass )
        throws IOException
    {
        ObjectMapper mapper = new ObjectMapper();
        CollectionType listType = mapper.getTypeFactory().constructCollectionType( ArrayList.class, tClass );
        List<T> ts = mapper.readValue( json, listType );
       // LOGGER.info( "class name: {}" + ts.get( 0 ).getClass().getName() );
        return ts;
    }
}
