package com.np.tele.crm.gis.bm;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.struts.action.ActionForm;

import com.np.tele.crm.common.utils.CRMCacheManager;
import com.np.tele.crm.constants.CRMDisplayListConstants;
import com.np.tele.crm.constants.CRMParameter;
import com.np.tele.crm.constants.CRMServiceCode;
import com.np.tele.crm.constants.CRMStatusCode;
import com.np.tele.crm.constants.IAppConstants;
import com.np.tele.crm.constants.ServiceParameter;
import com.np.tele.crm.gis.forms.GISForm;
import com.np.tele.crm.gis.pojo.GISUploadPojo;
import com.np.tele.crm.lms.bm.ILmsManager;
import com.np.tele.crm.pojos.GISMasterPojo;
import com.np.tele.crm.service.client.AreaPojo;
import com.np.tele.crm.service.client.CRMGISService;
import com.np.tele.crm.service.client.CityPojo;
import com.np.tele.crm.service.client.CrmuserDetailsDto;
import com.np.tele.crm.service.client.GisDto;
import com.np.tele.crm.service.client.LmsPojo;
import com.np.tele.crm.service.client.SOAPException_Exception;
import com.np.tele.crm.service.client.SocietyNetworkPartnerPojo;
import com.np.tele.crm.service.client.SocietyPojo;
import com.np.tele.crm.service.client.StatePojo;
import com.np.tele.crm.service.comparators.PojoComparator;
import com.np.tele.crm.utils.StringUtils;
import com.np.validator.util.CommonValidator;

public class GISManagerImpl
    implements IGISManager
{
    private static final Logger LOGGER           = Logger.getLogger( GISManagerImpl.class );
    private CRMGISService       gisServiceClient = null;
    private ILmsManager         lmsManagerImpl   = null;

    public ILmsManager getLmsManagerImpl()
    {
        return lmsManagerImpl;
    }

    public void setLmsManagerImpl( ILmsManager lmsManagerImpl )
    {
        this.lmsManagerImpl = lmsManagerImpl;
    }

    public CRMGISService getGisServiceClient()
    {
        return gisServiceClient;
    }

    public void setGisServiceClient( CRMGISService inGisServiceClient )
    {
        gisServiceClient = inGisServiceClient;
    }

    @Override
    public GisDto createGisSocietyData( ActionForm inForm, CrmuserDetailsDto inUserDto )
    {
        return null;
    }

    @Override
    public GisDto createGisSocietyData( GISForm inForm )
    {
        GisDto gisDto = null;
        try
        {
            LOGGER.info( "entered Create Society Data" );
            gisDto = new GisDto();
            String[] products = inForm.getProductArray();
            if ( StringUtils.isValidObj( inForm.getSocietyPojo() )
                    && StringUtils.isValidObj( inForm.getOldSocietyPojo() ) )
            {
                if ( StringUtils.isValidObj( products ) && inForm.getPartnerId() > 0 )
                {
                    List<String> productList = Arrays.asList( products );
                    if ( CommonValidator.isValidCollection( inForm.getSocietyPojo().getSocietyNetworkPartners() )
                            && CommonValidator.isValidCollection( productList ) )
                    {
                        int count = 0;
                        for ( SocietyNetworkPartnerPojo societyNetworkPartnerPojo : inForm.getSocietyPojo()
                                .getSocietyNetworkPartners() )
                        {
                            if ( inForm.getPartnerId() == societyNetworkPartnerPojo.getPartnerId() )
                            {
                                if ( productList.contains( societyNetworkPartnerPojo.getProductName() ) )
                                {
                                    count++;
                                }
                            }
                        }
                        if ( productList.size() <= count )
                        {
                            PojoComparator<SocietyPojo> comparator = new PojoComparator<SocietyPojo>();
                            PojoComparator<SocietyNetworkPartnerPojo> societyNetworkPartnercomparator = new PojoComparator<SocietyNetworkPartnerPojo>();
                            if ( ( comparator.compare( inForm.getSocietyPojo(), inForm.getOldSocietyPojo() ) == 0 )
                                    && ( societyNetworkPartnercomparator.compare( inForm.getNetworkPartnerPojo(),
                                                                                  inForm.getOldNetworkPartnerPojo() ) == 0 ) )
                            {
                                gisDto.setStatusCode( CRMServiceCode.CRM995.getStatusCode() );
                                return gisDto;
                            }
                        }
                    }
                }
                else
                {
                    PojoComparator<SocietyPojo> comparator = new PojoComparator<SocietyPojo>();
                    if ( comparator.compare( inForm.getSocietyPojo(), inForm.getOldSocietyPojo() ) == 0 )
                    {
                        gisDto.setStatusCode( CRMServiceCode.CRM995.getStatusCode() );
                        return gisDto;
                    }
                }
            }
            if ( StringUtils.isValidObj( products ) && inForm.getPartnerId() > 0 )
            {
                for ( String product : products )
                {
                    gisDto = new GisDto();
                    gisDto.getSocietyPojosList().add( inForm.getSocietyPojo() );
                    gisDto.setPartnerId( inForm.getPartnerId() );
                    gisDto.setProductName( product );
                    gisDto.setSoNetworkPartnerPojo( inForm.getNetworkPartnerPojo() );
                    LOGGER.info( "GisServiceClient() ,service client called" );
                    gisDto = getGisServiceClient().gisOperation( ServiceParameter.CREATE.getParameter(),
                                                                 CRMParameter.SOCIETY.getParameter(), gisDto );
                    LOGGER.info( "GisServiceClient() ,calling done" );
                }
                //gisDto.getSocietyPojosList().add( inForm.getSocietyPojo() );
                //gisDto.setPartnerId( inForm.getPartnerId() );
                //gisDto.setProductName( inForm.getProduct() );
            }
            else
            {
                gisDto = new GisDto();
                gisDto.getSocietyPojosList().add( inForm.getSocietyPojo() );
                gisDto.setSoNetworkPartnerPojo( inForm.getNetworkPartnerPojo() );
                gisDto = getGisServiceClient().gisOperation( ServiceParameter.CREATE.getParameter(),
                                                             CRMParameter.SOCIETY.getParameter(), gisDto );
            }
        }
        catch ( Exception ex )
        {
            LOGGER.error( "Exception :", ex );
        }
        LOGGER.info( "returning value" );
        return gisDto;
    }

    @Override
    public GisDto getCities( GisDto inGisDto )
    {
        try
        {
            inGisDto = gisServiceClient.gisOperation( ServiceParameter.SEARCH.getParameter(),
                                                      CRMParameter.CITY.getParameter(), inGisDto );
        }
        catch ( SOAPException_Exception ex )
        {
            LOGGER.error( "Exception while retriving cit", ex );
        }
        return inGisDto;
    }

    @Override
    public GisDto getStates( GisDto inGisDto )
    {
        try
        {
            inGisDto = gisServiceClient.gisOperation( ServiceParameter.SEARCH.getParameter(),
                                                      CRMParameter.STATE.getParameter(), inGisDto );
        }
        catch ( SOAPException_Exception ex )
        {
            LOGGER.error( "Exception while retriving States", ex );
        }
        return inGisDto;
    }

    @Override
    public GisDto getCountries( GisDto inGisDto )
    {
        try
        {
            inGisDto = gisServiceClient.gisOperation( ServiceParameter.SEARCH.getParameter(),
                                                      CRMParameter.COUNTRY.getParameter(), inGisDto );
        }
        catch ( SOAPException_Exception ex )
        {
            LOGGER.error( "Exception while retriving States", ex );
        }
        return inGisDto;
    }

    @Override
    public GisDto searchSociety( GISForm inGisForm )
    {
        GisDto gisDto = null;
        SocietyPojo societyPojo = inGisForm.getSocietyPojo();
        societyPojo.setAreaId( inGisForm.getAreaId() );
        societyPojo.getSocietyNetworkPartners().clear();
        try
        {
            LOGGER.info( "entered search society impl" );
            gisDto = new GisDto();
            SocietyNetworkPartnerPojo societyNetworkPartnerPojo = null;
            if ( StringUtils.isValidObj( inGisForm.getSocietyPojo() ) )
            {
                if ( inGisForm.getSocietyPojo().getAreaId() == 0 )
                {
                    long city = inGisForm.getCityId();
                    long stateId = inGisForm.getStateId();
                    if ( city > 0 || stateId > 0 )
                    {
                        List<AreaPojo> areaPojos = GISUtils.getAllActiveAreas( city, stateId, false );
                        if ( areaPojos.isEmpty() )
                        {
                            gisDto.setStatusCode( CRMServiceCode.CRM001.getStatusCode() );
                            return gisDto;
                        }
                        gisDto.getAreaPojosList().addAll( areaPojos );
                    }
                }
            }
            if ( StringUtils.isNotBlank( inGisForm.getProduct() ) )
            {
                if ( !StringUtils.isValidObj( societyNetworkPartnerPojo ) )
                {
                    societyNetworkPartnerPojo = new SocietyNetworkPartnerPojo();
                }
                societyNetworkPartnerPojo.setProductName( inGisForm.getProduct() );
                LOGGER.info( "Product Name " + inGisForm.getProduct() );
            }
            if ( inGisForm.getPartnerId() > 0 )
            {
                if ( !StringUtils.isValidObj( societyNetworkPartnerPojo ) )
                {
                    societyNetworkPartnerPojo = new SocietyNetworkPartnerPojo();
                }
                /* PartnerPojo partnerPojo = new PartnerPojo();
                 partnerPojo.setPartnerId( inGisForm.getPartnerId() );*/
                LOGGER.info( "Partner Id " + inGisForm.getPartnerId() );
                societyNetworkPartnerPojo.setPartnerId( inGisForm.getPartnerId() );
            }
            if ( StringUtils.isValidObj( societyPojo ) )
            {
                LOGGER.info( "Society Name ::::::" + inGisForm.getSocietyPojo().getSocietyName() );
                gisDto.setSocietyPojo( societyPojo );
                if ( StringUtils.isValidObj( societyNetworkPartnerPojo ) )
                {
                    societyPojo.getSocietyNetworkPartners().add( societyNetworkPartnerPojo );
                }
                gisDto = gisServiceClient.gisOperation( ServiceParameter.SEARCH.getParameter(),
                                                        CRMParameter.SOCIETY.getParameter(), gisDto );
            }
            else
            {
                gisDto.setStatusCode( CRMServiceCode.CRM997.getStatusCode() );
            }
        }
        catch ( Exception ex )
        {
            LOGGER.info( "Error in Search Society Mehod ", ex );
        }
        return gisDto;
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<String> processGISExcel( GISForm inGisForm, String inUser, String inFilePath )
    {
        ExcelReadUtils<?, ?, ?> excelReadUtils = null;
        Workbook workbook = null;
        FileInputStream fileInputStream = null;
        ExcelWriteUtils excelWriteUtils = null;
        List<String> statusList = new ArrayList<String>();
        try
        {
            //            GisDto gisDto = new GisDto();
            //            gisDto = getGisServiceClient().gisOperation( ServiceParameter.LIST.getParameter(),
            //                                                         CRMParameter.GISMASTER.getParameter(), gisDto );
            List<GISMasterPojo> gisMastersPojos = GISUtils.getActiveGisMasterPojos();
            String inPath = ExcelWriteUtils.downloadUploadedFile( inFilePath, inUser, inGisForm.getFormFile(),
                                                                  CRMParameter.GIS.getParameter() );
            fileInputStream = new FileInputStream( inPath );
            if ( inPath.endsWith( ".xls" ) )
            {
                excelReadUtils = new ExcelReadUtils<HSSFSheet, HSSFRow, HSSFCell>();
                workbook = new HSSFWorkbook( fileInputStream );
            }
            else if ( inPath.endsWith( ".xlsx" ) )
            {
                excelReadUtils = new ExcelReadUtils<XSSFSheet, XSSFRow, XSSFCell>();
                workbook = new XSSFWorkbook( fileInputStream );
            }
            excelWriteUtils = new ExcelWriteUtils( CRMParameter.GIS.getParameter() );
            inGisForm.setConfigureGisUpload( null );
            Map<Long, Map<String, List<SocietyPojo>>> dataToProcess = excelReadUtils
                    .readGisFileData( inUser, workbook, inPath, excelWriteUtils, gisMastersPojos,
                                      CRMCacheManager.getNetworkPartners(), inGisForm );
            if ( !StringUtils.isValidObj( dataToProcess ) )
            {
                statusList.add( "Invalid File Header." );
            }
            else
            {
                String outPath = excelWriteUtils.createExcel( inUser, inFilePath, CRMParameter.GIS.getParameter() );
                String status = storeValidData( dataToProcess, inGisForm );
                if ( CommonValidator.isValidCollection( inGisForm.getConfigureGisUpload() ) )
                {
                    StatePojo statePojo = null;
                    CityPojo cityPojo = null;
                    AreaPojo areaPojo = null;
                    Map<String, Long> states = new HashMap<String, Long>();
                    Map<String, Long> cities = new HashMap<String, Long>();
                    Map<String, Long> areas = new HashMap<String, Long>();
                    Map<Long, Map<String, List<SocietyPojo>>> validRecords = new HashMap<Long, Map<String, List<SocietyPojo>>>();
                    for ( GISUploadPojo uploadPojo : inGisForm.getConfigureGisUpload() )
                    {
                        try
                        {
                            Long stateId = 0l;
                            Long cityId = 0l;
                            Long areaId = 0l;
                            statePojo = GISUtils.getState( uploadPojo.getState() );
                            if ( StringUtils.isValidObj( statePojo ) )
                            {
                                cityPojo = GISUtils.getCity( uploadPojo.getCity(), statePojo.getCities() );
                                if ( StringUtils.isValidObj( cityPojo ) )
                                {
                                    areaPojo = GISUtils.getArea( uploadPojo.getArea(), cityPojo.getAreas() );
                                    if ( !StringUtils.isValidObj( areaPojo ) )
                                    {
                                        //create area
                                        areaId = saveArea( inUser, uploadPojo, cityPojo.getCityId() );
                                        //                                        GISUtils.toRefresh( null );
                                    }
                                    else
                                    {
                                        areaId = areaPojo.getAreaId();
                                    }
                                }
                                else
                                {
                                    // create city - area
                                    cityId = saveCity( inUser, uploadPojo, statePojo.getStateId() );
                                    if ( cityId > 0 )
                                    {
                                        areaId = saveArea( inUser, uploadPojo, cityId );
                                        //                                        GISUtils.toRefresh( null );
                                    }
                                }
                            }
                            else
                            {
                                // create state - city - area
                                stateId = states.get( uploadPojo.getState() );
                                if ( ! ( StringUtils.isValidObj( stateId ) && stateId > 0 ) )
                                {
                                    stateId = saveState( inUser, uploadPojo );
                                    states.put( uploadPojo.getState(), stateId );
                                }
                                //                                if ( stateId > 0 )
                                //                                {
                                cityId = cities.get( uploadPojo.getState() + "_" + uploadPojo.getCity() );
                                if ( ! ( StringUtils.isValidObj( cityId ) && cityId > 0 ) )
                                {
                                    cityId = saveCity( inUser, uploadPojo, stateId );
                                    cities.put( uploadPojo.getState() + "_" + uploadPojo.getCity(), cityId );
                                }
                                //                                if ( cityId > 0 )
                                //                                {
                                areaId = areas.get( uploadPojo.getState() + "_" + uploadPojo.getCity() + "_"
                                        + uploadPojo.getArea() );
                                if ( ! ( StringUtils.isValidObj( areaId ) && areaId > 0 ) )
                                {
                                    areaId = saveArea( inUser, uploadPojo, cityId );
                                    areas.put( uploadPojo.getState() + "_" + uploadPojo.getCity() + "_"
                                                       + uploadPojo.getArea(), areaId );
                                }
                                //                                }
                                //                                    GISUtils.toRefresh( null );
                                //                                }
                            }
                            if ( StringUtils.isValidObj( areaId ) && areaId > 0 )
                            {
                                uploadPojo.getSocietyPojo().setAreaId( areaId );
                                excelReadUtils.prepareValidGisPojos( inUser, validRecords, uploadPojo,
                                                                     uploadPojo.getSocietyPojo() );
                            }
                        }
                        catch ( Exception ex )
                        {
                            LOGGER.error( "unable to configure uploads", ex );
                        }
                    }
                    if ( !validRecords.isEmpty() )
                    {
                        status = storeValidData( validRecords, inGisForm );
                        GISUtils.toRefresh( null );
                    }
                }
                statusList.add( status );
                statusList.add( excelReadUtils.getnRows() + "" );
                statusList.add( excelReadUtils.getProcessRows() + "" );
                statusList.add( outPath );
            }
        }
        catch ( FileNotFoundException ex )
        {
            LOGGER.error( "File not found Exception", ex );
        }
        catch ( IOException ioex )
        {
            LOGGER.error( "Input output Exception", ioex );
        }
        //        catch ( SOAPException_Exception soex )
        //        {
        //            LOGGER.error( "Soap client Exception", soex );
        //        }
        return statusList;
    }

    private long saveState( final String inUser, final GISUploadPojo uploadPojo )
        throws SOAPException_Exception
    {
        StatePojo statePojo = new StatePojo();
        statePojo.setCountryId( GISUtils.getCountryId( IAppConstants.COUNTRY_INDIA ) );
        statePojo.setStateName( uploadPojo.getState() );
        statePojo.setStatus( CRMStatusCode.ACTIVE.getStatusCode() );
        statePojo.setCreatedBy( inUser );
        GisDto gisDto = new GisDto();
        gisDto.getStatePojosList().add( statePojo );
        gisDto = getGisServiceClient().gisOperation( ServiceParameter.CREATE.getParameter(),
                                                     CRMParameter.STATE.getParameter(), gisDto );
        if ( StringUtils.equals( CRMServiceCode.CRM001.getStatusCode(), gisDto.getStatusCode() ) )
        {
            return gisDto.getStatePojosList().get( 0 ).getStateId();
        }
        return 0;
    }

    private long saveCity( final String inUser, final GISUploadPojo uploadPojo, final long stateId )
        throws SOAPException_Exception
    {
        CityPojo cityPojo;
        GisDto gisDto;
        cityPojo = new CityPojo();
        cityPojo.setStateId( stateId );
        cityPojo.setCityName( uploadPojo.getCity() );
        cityPojo.setStatus( CRMStatusCode.ACTIVE.getStatusCode() );
        cityPojo.setCreatedBy( inUser );
        gisDto = new GisDto();
        gisDto.getCityPojosList().add( cityPojo );
        gisDto = getGisServiceClient().gisOperation( ServiceParameter.CREATE.getParameter(),
                                                     CRMParameter.CITY.getParameter(), gisDto );
        if ( StringUtils.equals( CRMServiceCode.CRM001.getStatusCode(), gisDto.getStatusCode() ) )
        {
            return gisDto.getCityPojosList().get( 0 ).getCityId();
        }
        return 0;
    }

    private long saveArea( final String inUser, final GISUploadPojo uploadPojo, final long cityId )
        throws SOAPException_Exception
    {
        AreaPojo areaPojo;
        GisDto gisDto;
        areaPojo = new AreaPojo();
        areaPojo.setCityId( cityId );
        areaPojo.setArea( uploadPojo.getArea() );
        areaPojo.setStatus( CRMStatusCode.ACTIVE.getStatusCode() );
        areaPojo.setCreatedBy( inUser );
        gisDto = new GisDto();
        gisDto.getAreaPojosList().add( areaPojo );
        gisDto = getGisServiceClient().gisOperation( ServiceParameter.CREATE.getParameter(),
                                                     CRMParameter.AREA.getParameter(), gisDto );
        if ( StringUtils.equals( CRMServiceCode.CRM001.getStatusCode(), gisDto.getStatusCode() ) )
        {
            return gisDto.getAreaPojosList().get( 0 ).getAreaId();
        }
        return 0;
    }

    private String storeValidData( Map<Long, Map<String, List<SocietyPojo>>> dataToProcess, GISForm inGisForm )
    {
        String status = "failed";
        GisDto gisDto = null;
        //        String[] products = inGisForm.getProductArray();
        for ( Entry<Long, Map<String, List<SocietyPojo>>> partnerEntry : dataToProcess.entrySet() )
        {
            Map<String, List<SocietyPojo>> netTypeSociety = partnerEntry.getValue();
            for ( Entry<String, List<SocietyPojo>> societyEntry : netTypeSociety.entrySet() )
            {
                gisDto = new GisDto();
                gisDto.getSocietyPojosList().addAll( societyEntry.getValue() );
                gisDto.setOperationType( CRMParameter.GISFILE_UPLOAD.getParameter() );
                gisDto.setPartnerId( partnerEntry.getKey() );
                gisDto.setProductName( CRMDisplayListConstants.getCodeByValue( "Product", societyEntry.getKey() ) );
                LOGGER.info( "going to save society for partner: " + partnerEntry.getKey() + " >> product: "
                        + societyEntry.getKey() + " >> societies.size: " + societyEntry.getValue().size() );
                try
                {
                    if ( !dataToProcess.isEmpty() )
                    {
                        gisDto = getGisServiceClient().gisOperation( ServiceParameter.CREATE.getParameter(),
                                                                     CRMParameter.SOCIETY.getParameter(), gisDto );
                        if ( StringUtils.equals( CRMServiceCode.CRM001.getStatusCode(), gisDto.getStatusCode() ) )
                            status = "success";
                    }
                }
                catch ( SOAPException_Exception ex )
                {
                    LOGGER.error( "Soap client Exception", ex );
                }
                catch ( Exception exe )
                {
                    LOGGER.error( "Exception while storing valid societies data : ", exe );
                }
            }
        }
        //        for ( String product : products )
        //        {
        //            gisDto = new GisDto();
        //            //            gisDto.getSocietyPojosList().addAll( dataToProcess );
        //            gisDto.setOperationType( CRMParameter.GISFILE_UPLOAD.getParameter() );
        //            gisDto.setPartnerId( inGisForm.getPartnerId() );
        //            gisDto.setProductName( product );
        //            try
        //            {
        //                if ( !dataToProcess.isEmpty() )
        //                {
        //                    gisDto = getGisServiceClient().gisOperation( ServiceParameter.CREATE.getParameter(),
        //                                                                 CRMParameter.SOCIETY.getParameter(), gisDto );
        //                    if ( StringUtils.equals( CRMServiceCode.CRM001.getStatusCode(), gisDto.getStatusCode() ) )
        //                        status = "success";
        //                }
        //            }
        //            catch ( SOAPException_Exception ex )
        //            {
        //                LOGGER.error( "Soap client Exception", ex );
        //            }
        //            catch ( Exception exe )
        //            {
        //                LOGGER.error( "Exception while storing valid societies data : ", exe );
        //            }
        //        }
        return status;
    }

    @Override
    public GisDto changeSocietyStatus( GISForm inGISForm, String modifiedBy )
    {
        GisDto gisDto = null;
        try
        {
            gisDto = new GisDto();
            SocietyPojo societyPojo = new SocietyPojo();
            societyPojo.setStatus( inGISForm.getStatus() );
            societyPojo.setSocietyId( inGISForm.getSocietyId() );
            societyPojo.setLastModifiedBy( modifiedBy );
            gisDto.setSocietyPojo( societyPojo );
            gisDto = getGisServiceClient().gisOperation( ServiceParameter.MODIFY_STATUS.getParameter(),
                                                         CRMParameter.SOCIETY.getParameter(), gisDto );
        }
        catch ( SOAPException_Exception ex )
        {
            LOGGER.error( "Exception :", ex );
        }
        return gisDto;
    }

    @Override
    public GisDto getSocietiesByArea( GisDto inGisDto )
    {
        try
        {
            inGisDto = gisServiceClient.gisOperation( ServiceParameter.SEARCH.getParameter(),
                                                      CRMParameter.SOCIETY.getParameter(), inGisDto );
        }
        catch ( SOAPException_Exception ex )
        {
            LOGGER.error( "Exception :", ex );
        }
        return inGisDto;
    }

    @Override
    public GisDto changeSocietyNPStatus( GISForm inGISForm, String modifiedBy )
    {
        GisDto gisDto = null;
        try
        {
            gisDto = new GisDto();
            SocietyNetworkPartnerPojo societyNPPojo = new SocietyNetworkPartnerPojo();
            societyNPPojo.setId( inGISForm.getNetworkPartnerPojo().getId() );
            societyNPPojo.setStatus( inGISForm.getNetworkPartnerPojo().getStatus() );
            societyNPPojo.setLastModifiedBy( modifiedBy );
            gisDto.setSoNetworkPartnerPojo( societyNPPojo );
            gisDto = getGisServiceClient().gisOperation( SocietyNetworkPartnerPojo.class.getSimpleName(),
                                                         CRMParameter.SOCIETY.getParameter(), gisDto );
        }
        catch ( SOAPException_Exception ex )
        {
            LOGGER.error( "Exception :", ex );
        }
        return gisDto;
    }

    @Override
    public LmsPojo getSocietyByPinCode( Integer pincode )
    {
        return getLmsManagerImpl().getSocietyByPinCode( pincode );
    }
}
