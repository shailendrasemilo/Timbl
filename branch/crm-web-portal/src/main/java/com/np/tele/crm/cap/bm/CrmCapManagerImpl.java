package com.np.tele.crm.cap.bm;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.log4j.Logger;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.draw.LineSeparator;
import com.np.tele.crm.cap.form.CrmCapForm;
import com.np.tele.crm.client.MasterDataClient;
import com.np.tele.crm.common.utils.CRMCacheManager;
import com.np.tele.crm.common.utils.CommonUtils;
import com.np.tele.crm.constants.CRMDisplayListConstants;
import com.np.tele.crm.constants.CRMOperationStages;
import com.np.tele.crm.constants.CRMParamDataEnum;
import com.np.tele.crm.constants.CRMParameter;
import com.np.tele.crm.constants.CRMRequestType;
import com.np.tele.crm.constants.CRMServiceCode;
import com.np.tele.crm.constants.CRMStatusCode;
import com.np.tele.crm.constants.CrmActionEnum;
import com.np.tele.crm.constants.IAppConstants;
import com.np.tele.crm.constants.IDateConstants;
import com.np.tele.crm.constants.ServiceParameter;
import com.np.tele.crm.exceptions.NextraCrmException;
import com.np.tele.crm.gis.bm.GISUtils;
import com.np.tele.crm.masterdata.bm.IMasterBMngr;
import com.np.tele.crm.pojos.ContentPojo;
import com.np.tele.crm.pojos.GISMasterPojo;
import com.np.tele.crm.pojos.RolesPojo;
import com.np.tele.crm.service.client.AppointmentPojo;
import com.np.tele.crm.service.client.AreaPojo;
import com.np.tele.crm.service.client.CRMConfigService;
import com.np.tele.crm.service.client.CRMUserManagement;
import com.np.tele.crm.service.client.CityPojo;
import com.np.tele.crm.service.client.ConfigDto;
import com.np.tele.crm.service.client.CrmAdditionalDetailsPojo;
import com.np.tele.crm.service.client.CrmAddressDetailsPojo;
import com.np.tele.crm.service.client.CrmAuditLogPojo;
import com.np.tele.crm.service.client.CrmCapDto;
import com.np.tele.crm.service.client.CrmCapService;
import com.np.tele.crm.service.client.CrmCustAadharNumberPojo;
import com.np.tele.crm.service.client.CrmCustomerDetailsPojo;
import com.np.tele.crm.service.client.CrmDocumentDetailsPojo;
import com.np.tele.crm.service.client.CrmNationalityDetailsPojo;
import com.np.tele.crm.service.client.CrmNetworkConfigurationsPojo;
import com.np.tele.crm.service.client.CrmOrderDetailsPojo;
import com.np.tele.crm.service.client.CrmParamDataPojo;
import com.np.tele.crm.service.client.CrmPartnerMappingPojo;
import com.np.tele.crm.service.client.CrmPartnerNetworkConfigPojo;
import com.np.tele.crm.service.client.CrmPaymentDetailsPojo;
import com.np.tele.crm.service.client.CrmPlanDetailsPojo;
import com.np.tele.crm.service.client.CrmPlanMasterPojo;
import com.np.tele.crm.service.client.CrmUserPojo;
import com.np.tele.crm.service.client.MasterDataDto;
import com.np.tele.crm.service.client.PartnerPojo;
import com.np.tele.crm.service.client.SOAPException_Exception;
import com.np.tele.crm.service.client.SocietyNetworkPartnerPojo;
import com.np.tele.crm.service.client.SocietyPojo;
import com.np.tele.crm.service.client.StatePojo;
import com.np.tele.crm.service.comparators.PojoComparator;
import com.np.tele.crm.utils.BeanUtils;
import com.np.tele.crm.utils.CRMUtils;
import com.np.tele.crm.utils.DateUtils;
import com.np.tele.crm.utils.GlobalUtils;
import com.np.tele.crm.utils.SortingComparator;
import com.np.tele.crm.utils.StringUtils;
import com.np.validator.util.CommonValidator;

public class CrmCapManagerImpl
    implements ICrmCapManager
{
    private static final Logger LOGGER           = Logger.getLogger( CrmCapManagerImpl.class );
    private CrmCapService       capServiceClient = null;
    private MasterDataClient    masterDataClient = null;
    private IMasterBMngr        masterBMngr      = null;
    private CRMUserManagement   crmUserClient;
    private CRMConfigService    crmConfigClient  = null;

    public MasterDataClient getMasterDataClient()
    {
        return masterDataClient;
    }

    public void setMasterDataClient( MasterDataClient masterDataClient )
    {
        this.masterDataClient = masterDataClient;
    }

    public CrmCapService getCapServiceClient()
    {
        return capServiceClient;
    }

    public void setCapServiceClient( CrmCapService inCapServiceClient )
    {
        this.capServiceClient = inCapServiceClient;
    }

    public IMasterBMngr getMasterBMngr()
    {
        return masterBMngr;
    }

    public void setMasterBMngr( IMasterBMngr inMasterBMngr )
    {
        masterBMngr = inMasterBMngr;
    }

    public CRMUserManagement getCrmUserClient()
    {
        return crmUserClient;
    }

    public void setCrmUserClient( CRMUserManagement inCrmUserClient )
    {
        crmUserClient = inCrmUserClient;
    }

    public CRMConfigService getCrmConfigClient()
    {
        return crmConfigClient;
    }

    public void setCrmConfigClient( CRMConfigService inCrmConfigClient )
    {
        crmConfigClient = inCrmConfigClient;
    }

    @Override
    public CrmCapDto searchCrfDetails( CrmCapForm inCapForm, String inUserId )
    {
        CrmCapDto crmCapDto = null;
        try
        {
            crmCapDto = new CrmCapDto();
            inCapForm.getCustomerDetailsPojo().setCrfId( inCapForm.getCustomerDetailsPojo().getCrfId() );
            crmCapDto.setCustomerDetailsPojo( inCapForm.getCustomerDetailsPojo() );
            crmCapDto.setUserId( inUserId );
            crmCapDto = getCapServiceClient().crmCapOperation( ServiceParameter.SEARCH.getParameter(),
                                                               CRMRequestType.CAF.getRequestCode(), crmCapDto );
        }
        catch ( Exception ex )
        {
            LOGGER.info( "error occured calling client to service for search caf", ex );
        }
        return crmCapDto;
    }

    @Override
    public CrmCapDto viewCrfDetails( CrmCapForm inCapForm, String inUserId )
    {
        CrmCapDto crmCapDto = null;
        try
        {
            crmCapDto = new CrmCapDto();
            inCapForm.getCustomerDetailsPojo().setCrfId( StringUtils.upperCase( inCapForm.getCustomerDetailsPojo()
                                                                 .getCrfId() ) );
            crmCapDto.setCustomerDetailsPojo( inCapForm.getCustomerDetailsPojo() );
            crmCapDto.setUserId( inUserId );
            crmCapDto = getCapServiceClient().crmCapOperation( ServiceParameter.VIEW.getParameter(),
                                                               CRMRequestType.CAF.getRequestCode(), crmCapDto );
        }
        catch ( Exception ex )
        {
            LOGGER.info( "error occured calling client to service for search caf", ex );
        }
        return crmCapDto;
    }

    @Override
    public CrmCapDto trackCustomerProfileDetails( long customerRecordId, String inCRFId, String inCustomerId )
    {
        CrmCapDto crmCapDto = null;
        try
        {
            crmCapDto = new CrmCapDto();
            CrmCustomerDetailsPojo customerDetailsPojo = new CrmCustomerDetailsPojo();
            customerDetailsPojo.setRecordId( customerRecordId );
            customerDetailsPojo.setCrfId( StringUtils.upperCase( inCRFId ) );
            customerDetailsPojo.setCustomerId( inCustomerId );
            crmCapDto.setCustomerDetailsPojo( customerDetailsPojo );
            crmCapDto = getCapServiceClient().crmCapOperation( ServiceParameter.TRACK.getParameter(),
                                                               CrmCustomerDetailsPojo.class.getSimpleName(), crmCapDto );
        }
        catch ( SOAPException_Exception ex )
        {
            LOGGER.info( "error occured calling client to service for search customer profile", ex );
        }
        return crmCapDto;
    }

    @Override
    public CrmCapDto saveCustomerBasicInfo( CrmCapForm inCrmCapForm, String inUserId )
    {
        CrmCapDto crmCapDto = new CrmCapDto();
        int counter = 0;
        int equalCounter = 13;
        counter = extractedPojo( inCrmCapForm, inUserId, crmCapDto, counter );
        try
        {
            LOGGER.info( "Counter value:: " + counter );
            if ( counter != equalCounter )
            {
                inCrmCapForm.getCustomerDetailsPojo().setStatus( CRMStatusCode.ACTIVATION_PENDING.getStatusCode() );
                if ( StringUtils.isNotBlank( inCrmCapForm.getRemarksPojo().getRemarks() )
                        && StringUtils.isNotBlank( inCrmCapForm.getRemarksPojo().getMappingId() ) )
                {
                    crmCapDto.setRemarksPojo( inCrmCapForm.getRemarksPojo() );
                }
                crmCapDto.setAadharNumberPojo( inCrmCapForm.getAadharNumberPojo() );
                crmCapDto.setCustomerDetailsPojo( inCrmCapForm.getCustomerDetailsPojo() );
                crmCapDto = getCapServiceClient().crmCapOperation( ServiceParameter.SAVE.getParameter(),
                                                                   CRMRequestType.CAF.getRequestCode(), crmCapDto );
            }
            else
            {
                LOGGER.info( "Please modify the values before submitting." );
                crmCapDto.setStatusCode( CRMServiceCode.CRM995.getStatusCode() );
                crmCapDto.setStatusDesc( CRMServiceCode.CRM995.getStatusDesc() );
            }
        }
        catch ( SOAPException_Exception ex )
        {
            LOGGER.info( "error occured calling client to service for save caf", ex );
        }
        return crmCapDto;
    }

    @Override
    public void setInstallationGISDetails( CrmCapForm inCrmCapForm, boolean toDisplay )
    {
        inCrmCapForm.getInstallationAddressPojo().setAddLine1( StringUtils
                                                                       .removeStart( inCrmCapForm
                                                                               .getInstallationAddressPojo()
                                                                               .getAddLine1(), "," ) );
        if ( toDisplay )
        {
            StatePojo statePojo = GISUtils.getState( inCrmCapForm.getInstallationAddressPojo().getStateId() );
            if ( StringUtils.isValidObj( statePojo ) )
            {
                inCrmCapForm.setState( statePojo.getStateName() );
                CityPojo cityPojo = GISUtils.getCity( inCrmCapForm.getInstallationAddressPojo().getCityId(),
                                                      statePojo.getCities() );
                if ( StringUtils.isValidObj( cityPojo ) )
                {
                    inCrmCapForm.setCity( cityPojo.getCityName() );
                    AreaPojo areaPojo = GISUtils.getArea( inCrmCapForm.getInstallationAddressPojo().getInstAreaId(),
                                                          cityPojo.getAreas() );
                    if ( StringUtils.isValidObj( areaPojo ) )
                    {
                        inCrmCapForm.setArea( areaPojo.getArea() );
                        SocietyPojo societyPojo = GISUtils.getSociety( inCrmCapForm.getInstallationAddressPojo()
                                .getInstSocietyId(), GISUtils.getSocietybyArea( areaPojo.getAreaId() ) );
                        if ( StringUtils.isValidObj( societyPojo ) )
                        {
                            inCrmCapForm.setSociety( societyPojo.getSocietyName() );
                            inCrmCapForm.setLocality( societyPojo.getLocalityName() );
                            inCrmCapForm.setConnectableHomes( societyPojo.getConnectableHomes() );
                        }
                    }
                }
            }
        }
        else
        {
            getGisDetailsByMaster( inCrmCapForm );
            //            if ( StringUtils.equals( CRMDisplayListConstants.ETHERNET_ON_CABLE.getCode(), inCrmCapForm
            //                    .getCustomerDetailsPojo().getProduct() ) )
            //            {
            //                if ( StringUtils.isNotBlank( inCrmCapForm.getLocality() ) )
            //                {
            //                    getGisDetailsByMaster( inCrmCapForm );
            //                }
            //                else
            //                {
            //                    StatePojo statePojo = GISUtils.getState( inCrmCapForm.getState() );
            //                    if ( StringUtils.isValidObj( statePojo ) )
            //                    {
            //                        inCrmCapForm.getInstallationAddressPojo().setStateId( statePojo.getStateId() );
            //                        CityPojo cityPojo = GISUtils.getCity( inCrmCapForm.getCity(), statePojo.getCities() );
            //                        if ( StringUtils.isValidObj( cityPojo ) )
            //                        {
            //                            inCrmCapForm.getInstallationAddressPojo().setCityId( cityPojo.getCityId() );
            //                            AreaPojo areaPojo = GISUtils.getArea( inCrmCapForm.getArea(), cityPojo.getAreas() );
            //                            if ( StringUtils.isValidObj( areaPojo ) )
            //                            {
            //                                inCrmCapForm.getInstallationAddressPojo().setInstAreaId( areaPojo.getAreaId() );
            //                                inCrmCapForm.getInstallationAddressPojo().setInstLocalityId( 0 );
            //                                inCrmCapForm.getInstallationAddressPojo().setInstSocietyId( 0 );
            //                            }
            //                        }
            //                    }
            //                }
            //            }
            //            else
            //            {
            //            getGisDetailsByMaster( inCrmCapForm );
            //            }
        }
    }

    private void getGisDetailsByMaster( CrmCapForm inCrmCapForm )
    {
        GISMasterPojo gisMaster = new GISMasterPojo();
        gisMaster.setCountry( IAppConstants.COUNTRY_INDIA );
        gisMaster.setState( inCrmCapForm.getState() );
        gisMaster.setCity( inCrmCapForm.getCity() );
        gisMaster.setArea( inCrmCapForm.getArea() );
        List<GISMasterPojo> gisMasterPojos = GISUtils.getActiveGisMasterPojos();
        int index = gisMasterPojos.indexOf( gisMaster );
        if ( index >= 0 )
        {
            gisMaster = gisMasterPojos.get( index );
            inCrmCapForm.getInstallationAddressPojo().setStateId( gisMaster.getStateId() );
            inCrmCapForm.getInstallationAddressPojo().setCityId( gisMaster.getCityId() );
            inCrmCapForm.getInstallationAddressPojo().setInstAreaId( gisMaster.getAreaId() );
            List<SocietyPojo> societyPojos = GISUtils.getActiveSocieties( gisMaster.getCountryId(),
                                                                          gisMaster.getStateId(),
                                                                          gisMaster.getCityId(), gisMaster.getAreaId() );
            SocietyPojo societyPojo = GISUtils.getSociety( inCrmCapForm.getLocality(), inCrmCapForm.getSociety(),
                                                           societyPojos );
            if ( StringUtils.isValidObj( societyPojo ) )
            {
                inCrmCapForm.getInstallationAddressPojo().setInstSocietyId( societyPojo.getSocietyId() );
            }
        }
    }

    @Override
    public CrmCapDto submitCRFDetails( CrmCapForm inCrmCapForm, String inUserId )
    {
        CrmCapDto crmCapDto = new CrmCapDto();
        int counter = 0;
        LOGGER.info( "CAF ID: " + inCrmCapForm.getCustomerDetailsPojo().getCrfId() );
        LOGGER.info( "Recoed ID OF Billing POJO: " + inCrmCapForm.getBillingAddressPojo().getRecordId() );
        LOGGER.info( "Recoed ID OF Installation POJO: " + inCrmCapForm.getInstallationAddressPojo().getRecordId() );
        if ( !StringUtils.isValidObj( inCrmCapForm.getCustomerDetailsPojo().getFtSubmittionDate() ) )
        {
            inCrmCapForm.getCustomerDetailsPojo().setFtSubmittionDate( DateUtils.toXMLGregorianCalendar( Calendar
                                                                               .getInstance().getTime() ) );
        }
        inCrmCapForm.getCustomerDetailsPojo().setEmailValidationFlag( "N" );
        counter = extractedPojo( inCrmCapForm, inUserId, crmCapDto, counter );
        try
        {
            if ( StringUtils.equals( crmCapDto.getCustomerDetailsPojo().getCrfStage(),
                                     CRMOperationStages.FT_REJECT.getCode() )
                    || StringUtils.equals( crmCapDto.getCustomerDetailsPojo().getCrfStage(),
                                           CRMOperationStages.INITIATE.getCode() ) )
            {
                LOGGER.info( "Previous Stage of CAF::" + crmCapDto.getCustomerDetailsPojo().getCrfStage() );
                if ( StringUtils.equals( crmCapDto.getCustomerDetailsPojo().getCrfStage(),
                                         CRMOperationStages.FT_REJECT.getCode() ) )
                {
                    crmCapDto.setRemarksPojo( inCrmCapForm.getRemarksPojo() );
                }
                inCrmCapForm.getCustomerDetailsPojo().setStatus( CRMStatusCode.ACTIVATION_PENDING.getStatusCode() );
                crmCapDto.setToStage( CRMOperationStages.FULFILLMENT_TEAM.getCode() );
            }
            else
            {
                crmCapDto.getCustomerDetailsPojo().setCrfStage( CRMOperationStages.INITIATE.getCode() );
                crmCapDto.setToStage( CRMOperationStages.FULFILLMENT_TEAM.getCode() );
                inCrmCapForm.getCustomerDetailsPojo().setStatus( CRMStatusCode.ACTIVATION_PENDING.getStatusCode() );
            }
            LOGGER.info( "Going to CAF Forwarded to Stage:: " + crmCapDto.getToStage() );
            CommonUtils.setConnectionTypeDetails( crmCapDto.getCustomerDetailsPojo() );
            crmCapDto.setAadharNumberPojo( inCrmCapForm.getAadharNumberPojo() );
            crmCapDto = getCapServiceClient().crmCapOperation( ServiceParameter.FORWARD.getParameter(),
                                                               CRMRequestType.CAF.getRequestCode(), crmCapDto );
        }
        catch ( SOAPException_Exception ex )
        {
            LOGGER.info( "error occured calling client to service for submit CAF", ex );
        }
        return crmCapDto;
    }

    /*   public void setConnectionTypeDetails( CrmCustomerDetailsPojo inCustomerDetailsPojo )
       {
           if ( StringUtils.isValidObj( inCustomerDetailsPojo )
                   && StringUtils.isNotBlank( inCustomerDetailsPojo.getConnectionType() ) )
           {
               if ( StringUtils.equals( CRMDisplayListConstants.INDIVIDUAL.getCode(),
                                        inCustomerDetailsPojo.getConnectionType() )
                       || StringUtils.equals( CRMDisplayListConstants.PROPRIETORSHIP.getCode(),
                                              inCustomerDetailsPojo.getConnectionType() ) )
               {
                   inCustomerDetailsPojo.setAuthSignDesg( null );
                   inCustomerDetailsPojo.setAuthSignFname( null );
                   inCustomerDetailsPojo.setAuthSignLname( null );
                   inCustomerDetailsPojo.setOrgCordFname( null );
                   inCustomerDetailsPojo.setOrgCordLname( null );
               }
               else
               {
                   inCustomerDetailsPojo.setCustLname( null );
                   inCustomerDetailsPojo.setCustGender( null );
                   inCustomerDetailsPojo.setDisplayCustDob( null );
               }
           }
       }*/
    private int extractedPojo( CrmCapForm inCrmCapForm, String inUserId, CrmCapDto crmCapDto, int counter )
    {
        inCrmCapForm.getCustomerDetailsPojo().setCrfDate( DateUtils.changeDateFormat( inCrmCapForm
                                                                  .getCustomerDetailsPojo().getDisplayCrfDate() ) );
        inCrmCapForm.getCustomerDetailsPojo().setCustDob( DateUtils.changeDateFormat( inCrmCapForm
                                                                  .getCustomerDetailsPojo().getDisplayCustDob() ) );
        inCrmCapForm.getTelecommunicationPayment().setChequeDate( DateUtils.changeDateFormat( inCrmCapForm
                                                                          .getTelecommunicationPayment()
                                                                          .getDisplayChequeDate() ) );
        inCrmCapForm.getTeleservicesPayment()
                .setChequeDate( DateUtils.changeDateFormat( inCrmCapForm.getTeleservicesPayment()
                                        .getDisplayChequeDate() ) );
        inCrmCapForm.getSecurityDepositPayment().setChequeDate( DateUtils.changeDateFormat( inCrmCapForm
                                                                        .getSecurityDepositPayment()
                                                                        .getDisplayChequeDate() ) );
        inCrmCapForm.getNationalityDetailsPojo().setPassportValidity( DateUtils.changeDateFormat( inCrmCapForm
                                                                              .getNationalityDetailsPojo()
                                                                              .getDisplayPassportValidity() ) );
        inCrmCapForm.getNationalityDetailsPojo().setVisaValidity( DateUtils.changeDateFormat( inCrmCapForm
                                                                          .getNationalityDetailsPojo()
                                                                          .getDisplayVisaValidity() ) );
        if ( StringUtils.equals( inCrmCapForm.getCustomerDetailsPojo().getNationality(), IAppConstants.INDIAN ) )
        {
            inCrmCapForm.getNationalityDetailsPojo().setNationality( IAppConstants.INDIAN );
        }
        if ( StringUtils.isValidObj( inCrmCapForm.getTempCustomerDetailsPojo() ) )
        {
            PojoComparator<CrmCustomerDetailsPojo> pojoComparator = new PojoComparator<CrmCustomerDetailsPojo>();
            if ( pojoComparator.compare( inCrmCapForm.getTempCustomerDetailsPojo(),
                                         inCrmCapForm.getCustomerDetailsPojo() ) == 0 )
            {
                counter++;
                LOGGER.info( "CrmCustomerDetailsPojo will not be Save, because no change has been Made." );
            }
            crmCapDto.setCustomerDetailsPojo( inCrmCapForm.getCustomerDetailsPojo() );
        }
        else
        {
            crmCapDto.setCustomerDetailsPojo( inCrmCapForm.getCustomerDetailsPojo() );
        }
        if ( StringUtils.isValidObj( inCrmCapForm.getTempAdditionalDetailsPojo() ) )
        {
            PojoComparator<CrmAdditionalDetailsPojo> pojoComparator = new PojoComparator<CrmAdditionalDetailsPojo>();
            if ( pojoComparator.compare( inCrmCapForm.getTempAdditionalDetailsPojo(),
                                         inCrmCapForm.getAdditionalDetailsPojo() ) == 0 )
            {
                counter++;
                LOGGER.info( "CrmAdditionalDetailsPojo will not be Save, because no change has been Made." );
            }
            else
            {
                crmCapDto.setAdditionalDetailsPojo( inCrmCapForm.getAdditionalDetailsPojo() );
            }
        }
        else
        {
            crmCapDto.setAdditionalDetailsPojo( inCrmCapForm.getAdditionalDetailsPojo() );
        }
        if ( StringUtils.isValidObj( inCrmCapForm.getTempNationalityDetailsPojo() ) )
        {
            PojoComparator<CrmNationalityDetailsPojo> pojoComparator = new PojoComparator<CrmNationalityDetailsPojo>();
            if ( pojoComparator.compare( inCrmCapForm.getTempNationalityDetailsPojo(),
                                         inCrmCapForm.getNationalityDetailsPojo() ) == 0 )
            {
                counter++;
                LOGGER.info( "CrmNationalityDetailsPojo will not be Save, because no change has been Made." );
            }
            else
            {
                crmCapDto.setNationalityDetailsPojo( inCrmCapForm.getNationalityDetailsPojo() );
            }
        }
        else
        {
            crmCapDto.setNationalityDetailsPojo( inCrmCapForm.getNationalityDetailsPojo() );
        }
        if ( StringUtils.isValidObj( inCrmCapForm.getTempDocumentDetailsPojo() ) )
        {
            PojoComparator<CrmDocumentDetailsPojo> pojoComparator = new PojoComparator<CrmDocumentDetailsPojo>();
            if ( pojoComparator.compare( inCrmCapForm.getTempDocumentDetailsPojo(),
                                         inCrmCapForm.getDocumentDetailsPojo() ) == 0 )
            {
                counter++;
                LOGGER.info( "CrmDocumentDetailsPojo will not be Save, because no change has been Made." );
            }
            else
            {
                setDocumentProof( inCrmCapForm );
                crmCapDto.setDocumentDetailsPojo( inCrmCapForm.getDocumentDetailsPojo() );
            }
        }
        else
        {
            setDocumentProof( inCrmCapForm );
            crmCapDto.setDocumentDetailsPojo( inCrmCapForm.getDocumentDetailsPojo() );
        }
        if ( StringUtils.isValidObj( inCrmCapForm.getTempOrderDetailsPojo() ) )
        {
            PojoComparator<CrmOrderDetailsPojo> pojoComparator = new PojoComparator<CrmOrderDetailsPojo>();
            if ( pojoComparator.compare( inCrmCapForm.getTempOrderDetailsPojo(), inCrmCapForm.getOrderDetailsPojo() ) == 0 )
            {
                counter++;
                LOGGER.info( "CrmOrderDetailsPojo will not be Save, because no change has been Made." );
            }
            crmCapDto.setOrderDetailsPojo( inCrmCapForm.getOrderDetailsPojo() );
        }
        else
        {
            crmCapDto.setOrderDetailsPojo( inCrmCapForm.getOrderDetailsPojo() );
        }
        if ( StringUtils.isValidObj( inCrmCapForm.getTempPlanDetailsPojo() ) )
        {
            PojoComparator<CrmPlanDetailsPojo> pojoComparator = new PojoComparator<CrmPlanDetailsPojo>();
            if ( pojoComparator.compare( inCrmCapForm.getTempPlanDetailsPojo(), inCrmCapForm.getPlanDetailsPojo() ) == 0 )
            {
                counter++;
                LOGGER.info( "CrmPlanDetailsPojo will not be Save, because no change has been Made." );
            }
            else
            {
                crmCapDto.setPlanDetailsPojo( inCrmCapForm.getPlanDetailsPojo() );
            }
        }
        else
        {
            crmCapDto.setPlanDetailsPojo( inCrmCapForm.getPlanDetailsPojo() );
        }
        List<CrmAddressDetailsPojo> addressDetailsPojos = new ArrayList<CrmAddressDetailsPojo>();
        if ( StringUtils.isValidObj( inCrmCapForm.getTempBillingAddressPojo() ) )
        {
            setBillingGISDetails( inCrmCapForm, false );
            PojoComparator<CrmAddressDetailsPojo> pojoComparator = new PojoComparator<CrmAddressDetailsPojo>();
            if ( pojoComparator
                    .compare( inCrmCapForm.getTempBillingAddressPojo(), inCrmCapForm.getBillingAddressPojo() ) == 0 )
            {
                counter++;
                LOGGER.info( "BillingAddressPojo will not be Save, because no change has been Made." );
            }
            addressDetailsPojos.add( inCrmCapForm.getBillingAddressPojo() );
        }
        else
        {
            setBillingGISDetails( inCrmCapForm, false );
            addressDetailsPojos.add( inCrmCapForm.getBillingAddressPojo() );
        }
        if ( StringUtils.isValidObj( inCrmCapForm.getTempInstallationAddressPojo() ) )
        {
            setInstallationGISDetails( inCrmCapForm, false );
            PojoComparator<CrmAddressDetailsPojo> pojoComparator = new PojoComparator<CrmAddressDetailsPojo>();
            if ( pojoComparator.compare( inCrmCapForm.getTempInstallationAddressPojo(),
                                         inCrmCapForm.getInstallationAddressPojo() ) == 0 )
            {
                counter++;
                LOGGER.info( "InstallationAddressPojo will not be Save, because no change has been Made." );
            }
            addressDetailsPojos.add( inCrmCapForm.getInstallationAddressPojo() );
        }
        else
        {
            setInstallationGISDetails( inCrmCapForm, false );
            addressDetailsPojos.add( inCrmCapForm.getInstallationAddressPojo() );
        }
        if ( !addressDetailsPojos.isEmpty() )
        {
            crmCapDto.getAddressDetailsPojosList().addAll( addressDetailsPojos );
        }
        if ( StringUtils.isValidObj( inCrmCapForm.getTempTelecommunicationPayment() ) )
        {
            PojoComparator<CrmPaymentDetailsPojo> pojoComparator = new PojoComparator<CrmPaymentDetailsPojo>();
            if ( pojoComparator.compare( inCrmCapForm.getTempTelecommunicationPayment(),
                                         inCrmCapForm.getTelecommunicationPayment() ) == 0 )
            {
                counter++;
                LOGGER.info( "TelecommunicationPayment will not be Save, because no change has been Made." );
            }
            else
            {
                crmCapDto.getPaymentDetailsPojosList().add( inCrmCapForm.getTelecommunicationPayment() );
            }
        }
        else
        {
            crmCapDto.getPaymentDetailsPojosList().add( inCrmCapForm.getTelecommunicationPayment() );
        }
        if ( StringUtils.isValidObj( inCrmCapForm.getTempTeleservicesPayment() ) )
        {
            PojoComparator<CrmPaymentDetailsPojo> pojoComparator = new PojoComparator<CrmPaymentDetailsPojo>();
            if ( pojoComparator.compare( inCrmCapForm.getTempTeleservicesPayment(),
                                         inCrmCapForm.getTeleservicesPayment() ) == 0 )
            {
                counter++;
                LOGGER.info( "TeleservicesPayment will not be Save, because no change has been Made." );
            }
            else
            {
                crmCapDto.getPaymentDetailsPojosList().add( inCrmCapForm.getTeleservicesPayment() );
            }
        }
        else
        {
            crmCapDto.getPaymentDetailsPojosList().add( inCrmCapForm.getTeleservicesPayment() );
        }
        if ( StringUtils.isValidObj( inCrmCapForm.getTempSecurityDepositPayment() ) )
        {
            PojoComparator<CrmPaymentDetailsPojo> pojoComparator = new PojoComparator<CrmPaymentDetailsPojo>();
            if ( pojoComparator.compare( inCrmCapForm.getTempSecurityDepositPayment(),
                                         inCrmCapForm.getSecurityDepositPayment() ) == 0 )
            {
                counter++;
                LOGGER.info( "SecurityDepositPayment will not be Save, because no change has been Made." );
            }
            else
            {
                crmCapDto.getPaymentDetailsPojosList().add( inCrmCapForm.getSecurityDepositPayment() );
            }
        }
        else
        {
            crmCapDto.getPaymentDetailsPojosList().add( inCrmCapForm.getSecurityDepositPayment() );
        }
        if ( StringUtils.isValidObj( inCrmCapForm.getTempAppointmentPojo() ) )
        {
            LOGGER.info( "Appointment ID:: " + inCrmCapForm.getAppointmentPojo().getAppointmentId() );
            PojoComparator<AppointmentPojo> pojoComparator = new PojoComparator<AppointmentPojo>();
            if ( pojoComparator.compare( inCrmCapForm.getTempAppointmentPojo(), inCrmCapForm.getAppointmentPojo() ) == 0 )
            {
                counter++;
                LOGGER.info( "AppointmentPojo will not be Save, because no change has been Made." );
            }
            else if ( StringUtils.isNotBlank( inCrmCapForm.getAppointmentPojo().getAppointmentTime() ) )
            {
                crmCapDto.setAppointmentPojo( inCrmCapForm.getAppointmentPojo() );
            }
        }
        else if ( StringUtils.isNotBlank( inCrmCapForm.getAppointmentPojo().getAppointmentTime() ) )
        {
            crmCapDto.setAppointmentPojo( inCrmCapForm.getAppointmentPojo() );
        }
        crmCapDto.setUserId( inUserId );
        return counter;
    }

    @Override
    public void setBillingGISDetails( CrmCapForm inCrmCapForm, boolean toDisplay )
    {
        CrmAddressDetailsPojo inBillingAddress = inCrmCapForm.getBillingAddressPojo();
        inBillingAddress.setAddLine1( StringUtils.removeStart( inBillingAddress.getAddLine1(), "," ) );
        if ( toDisplay )
        {
            StatePojo statePojo = GISUtils.getState( inBillingAddress.getStateId() );
            if ( StringUtils.isValidObj( statePojo ) )
            {
                inBillingAddress.setStateName( statePojo.getStateName() );
                CityPojo cityPojo = GISUtils.getCity( inBillingAddress.getCityId(), statePojo.getCities() );
                if ( StringUtils.isValidObj( cityPojo ) )
                {
                    inCrmCapForm.setCityList( statePojo.getCities() );
                    inBillingAddress.setCityName( cityPojo.getCityName() );
                }
            }
        }
        else
        {
            StatePojo statePojo = GISUtils.getState( inBillingAddress.getStateName() );
            if ( StringUtils.isValidObj( statePojo ) )
            {
                inBillingAddress.setStateId( statePojo.getStateId() );
                CityPojo cityPojo = GISUtils.getCity( inBillingAddress.getCityName(), statePojo.getCities() );
                if ( StringUtils.isValidObj( cityPojo ) )
                {
                    inBillingAddress.setCityId( cityPojo.getCityId() );
                }
            }
        }
    }

    private void setDocumentProof( CrmCapForm inCrmCapForm )
    {
        if ( !inCrmCapForm.getDocuments().isEmpty() )
        {
            StringBuilder stringBuilder = new StringBuilder();
            for ( ContentPojo contentPojo : inCrmCapForm.getDocuments() )
            {
                if ( contentPojo.isSelected() )
                {
                    LOGGER.info( "Selected Document Proof: " + contentPojo.getContentName() );
                    stringBuilder.append( contentPojo.getContentValue() );
                    stringBuilder.append( IAppConstants.COMMA );
                }
            }
            inCrmCapForm.getDocumentDetailsPojo().setIdProof( stringBuilder.toString() );
        }
        if ( !inCrmCapForm.getAddressProofs().isEmpty() )
        {
            StringBuilder stringBuilder = new StringBuilder();
            for ( ContentPojo contentPojo : inCrmCapForm.getAddressProofs() )
            {
                if ( contentPojo.isSelected() )
                {
                    LOGGER.info( "Selected Address Proof: " + contentPojo.getContentName() );
                    stringBuilder.append( contentPojo.getContentValue() );
                    stringBuilder.append( IAppConstants.COMMA );
                }
            }
            inCrmCapForm.getDocumentDetailsPojo().setAddressProof( stringBuilder.toString() );
        }
    }

    @Override
    public void setCRFDetails( CrmCapDto inCrmCapDto, CrmCapForm inCrmCapForm )
    {
        LOGGER.info( "Fill all the crmCapDto pojo into crmCapForm" );
        if ( StringUtils.isValidObj( inCrmCapDto.getAdditionalDetailsPojo() ) )
        {
            LOGGER.info( "AdditionalDetailsPojo is Valid Object::" );
            inCrmCapForm.setAdditionalDetailsPojo( inCrmCapDto.getAdditionalDetailsPojo() );
            createTempAdditionalPojo( inCrmCapDto, inCrmCapForm );
        }
        else
        {
            inCrmCapForm.setBankList( CRMCacheManager.getBankListByStatus( CRMStatusCode.ACTIVE ) );
            inCrmCapForm.setAdditionalDetailsPojo( new CrmAdditionalDetailsPojo() );
        }
        if ( StringUtils.isValidObj( inCrmCapDto.getNationalityDetailsPojo() ) )
        {
            LOGGER.info( "NationalityDetailsPojo is Valid Object::" );
            inCrmCapForm.setNationalityDetailsPojo( inCrmCapDto.getNationalityDetailsPojo() );
            createTempNationalityPojo( inCrmCapDto, inCrmCapForm );
        }
        else
        {
            inCrmCapForm.setNationalityDetailsPojo( new CrmNationalityDetailsPojo() );
        }
        if ( StringUtils.isValidObj( inCrmCapDto.getNetworkConfigurationsPojo() ) )
        {
            LOGGER.info( "NetworkConfigurationsPojo is Valid Object::" );
            inCrmCapForm.setCrmNetworkConfigurations( inCrmCapDto.getNetworkConfigurationsPojo() );
            createTempNetworkConfigurationsPojo( inCrmCapDto, inCrmCapForm );
            if ( StringUtils.isValidObj( inCrmCapDto.getCrmPartnerNetworkConfig() ) )
            {
                inCrmCapForm.setCrmPartnerNetworkConfig( inCrmCapDto.getCrmPartnerNetworkConfig() );
                inCrmCapForm.setNasportID( inCrmCapDto.getCrmPartnerNetworkConfig().getNasPortId() );
            }
            else
            {
                inCrmCapForm.setCrmPartnerNetworkConfig( new CrmPartnerNetworkConfigPojo() );
            }
        }
        else
        {
            inCrmCapForm.setCrmNetworkConfigurations( new CrmNetworkConfigurationsPojo() );
        }
        if ( StringUtils.isValidObj( inCrmCapDto.getOrderDetailsPojo() ) )
        {
            LOGGER.info( "OrderDetailsPojo is Valid Object::" );
            inCrmCapForm.setOrderDetailsPojo( inCrmCapDto.getOrderDetailsPojo() );
            createTempOrderDetailPojo( inCrmCapDto, inCrmCapForm );
            if ( StringUtils.isEmpty( inCrmCapDto.getOrderDetailsPojo().getCpeStatus() ) )
            {
                if ( StringUtils.equals( inCrmCapDto.getCustomerDetailsPojo().getProduct(),
                                         CRMDisplayListConstants.ETHERNET_ON_CABLE.getCode() ) )
                {
                    inCrmCapDto.getOrderDetailsPojo().setCpeStatus( CRMDisplayListConstants.NEXTRA_RECOMMENDED
                                                                            .getCode() );
                }
                else
                {
                    inCrmCapDto.getOrderDetailsPojo().setCpeStatus( CRMDisplayListConstants.OWNED.getCode() );
                }
            }
        }
        else
        {
            inCrmCapForm.setOrderDetailsPojo( new CrmOrderDetailsPojo() );
        }
        if ( StringUtils.isValidObj( inCrmCapDto.getAppointmentPojo() ) )
        {
            LOGGER.info( "AppointmentPojo is Valid Object::" );
            inCrmCapForm.setAppointmentPojo( inCrmCapDto.getAppointmentPojo() );
            createTempAppointmentPojo( inCrmCapDto, inCrmCapForm );
        }
        else
        {
            inCrmCapForm.setAppointmentPojo( new AppointmentPojo() );
        }
        if ( !inCrmCapDto.getRemarksPojos().isEmpty() )
        {
            LOGGER.info( "Remarks pojo list size::" + inCrmCapDto.getRemarksPojos().size() );
            inCrmCapForm.setRemarksPojoList( inCrmCapDto.getRemarksPojos() );
        }
        if ( StringUtils.isValidObj( inCrmCapDto.getAadharNumberPojo() ) )
        {
            inCrmCapForm.setAadharNumberPojo( inCrmCapDto.getAadharNumberPojo() );
        }
        else
        {
            inCrmCapForm.setAadharNumberPojo( new CrmCustAadharNumberPojo() );
        }
        setCustomerDetials( inCrmCapDto, inCrmCapForm );
        List<ContentPojo> allAddProofList = CRMUtils.getAddressProofList();
        List<ContentPojo> allIdProofList = CRMUtils.getDocuments();
        setDocumentsDetails( inCrmCapDto, inCrmCapForm, allAddProofList, allIdProofList );
        setPaymentDetails( inCrmCapDto, inCrmCapForm );
        setActivationPlans( inCrmCapDto, inCrmCapForm );
        setPredefineList( inCrmCapDto, inCrmCapForm, allAddProofList, allIdProofList );
        inCrmCapForm.setCrmParamDataPojos( inCrmCapDto.getCrmParamDataPojos() );
        /* try
         {
             CrmuserDetailsDto crmUserDetailsDto = new CrmuserDetailsDto();
             CrmUserPojo crmUserPojo = new CrmUserPojo();
             crmUserPojo.setStatus( CRMStatusCode.ACTIVE.getStatusCode() );
             crmUserDetailsDto.setCrmUserDetailsPojo( crmUserPojo );
             crmUserDetailsDto = getCrmUserClient().userOperations( crmUserDetailsDto,
                                                                    ServiceParameter.LIST.getParameter() );
             if ( StringUtils.isValidObj( crmUserDetailsDto ) )
             {
                 inCrmCapForm.setCrmUserPojos( crmUserDetailsDto.getCrmUserDetailsPojoList() );
             }
         }
         catch ( SOAPException_Exception ex )
         {
             LOGGER.error( "exception occure while getting UserList", ex );
         }*/
        try
        {
            MasterDataDto masterDataDto = new MasterDataDto();
            masterDataDto.setPartnerId( inCrmCapDto.getCustomerDetailsPojo().getBusinessPartner() );
            masterDataDto.setProduct( inCrmCapDto.getCustomerDetailsPojo().getProduct() );
            masterDataDto = getMasterDataClient().masterOperations( ServiceParameter.LIST.getParameter(),
                                                                    CrmUserPojo.class.getSimpleName(), masterDataDto );
            if ( CommonValidator.isValidCollection( masterDataDto.getCrmUserPojos() ) )
            {
                LOGGER.info( "CrmUserPojos Size:" + masterDataDto.getCrmUserPojos().size() );
                inCrmCapForm.setCrmUserPojos( masterDataDto.getCrmUserPojos() );
            }
        }
        catch ( SOAPException_Exception ex )
        {
            LOGGER.error( "exception occure while getting UserList", ex );
        }
        if ( !StringUtils.isValidObj( inCrmCapForm.getCustomerDetailsPojo().getSalesUser() ) )
        {
            inCrmCapForm.getCustomerDetailsPojo().setSalesUser( new CrmUserPojo() );
        }
        List<PartnerPojo> data = null;
        if ( StringUtils.isNumeric( inCrmCapForm.getCustomerDetailsPojo().getBusinessPartner() + "" ) )
        {
            data = getMasterBMngr().getPartnersById( inCrmCapForm.getCustomerDetailsPojo().getBusinessPartner() + "" );
        }
        if ( CommonValidator.isValidCollection( data ) )
        {
            inCrmCapForm.setPartner( data.get( 0 ) );
        }
        else
        {
            inCrmCapForm.setPartner( new PartnerPojo() );
        }
    }

    @Override
    public void setPredefineList( CrmCapDto inCrmCapDto,
                                  CrmCapForm inCrmCapForm,
                                  List<ContentPojo> allAddProofList,
                                  List<ContentPojo> allIdProofList )
    {
        LOGGER.info( "Inside CrmCapManagerImpl, setPredefineList" );
        inCrmCapForm.setDocuments( allIdProofList );
        inCrmCapForm.setAddressProofs( allAddProofList );
        inCrmCapForm.setPaymentModes( CRMUtils.getPaymentMode( CRMParameter.INA.getParameter() ) ); // To enable Online mode remove parameter.
        inCrmCapForm.setBankList( CRMCacheManager.getBankListByStatus( CRMStatusCode.ACTIVE ) );
        inCrmCapForm.setConnectionTypeList( CRMUtils.getConnectionTypes() );
        inCrmCapForm.setVisaTypeList( CRMUtils.getVisaTypes() );
        inCrmCapForm.setServiceTypes( CRMUtils.getServiceTypesList() );
        inCrmCapForm.setBillingPreferences( CRMUtils.getBillingPreferences() );
        if ( CommonValidator.isValidCollection( inCrmCapForm.getUserAssociatedServices() ) )
        {
            List<ContentPojo> userServices = new ArrayList<ContentPojo>();
            ContentPojo contentPojo = null;
            for ( CRMDisplayListConstants crmDisplayConst : CRMDisplayListConstants.values() )
            {
                if ( StringUtils.equals( crmDisplayConst.getListType(), CRMParameter.PRODUCT.getParameter() )
                        && inCrmCapForm.getUserAssociatedServices().contains( crmDisplayConst.getCode() ) )
                {
                    contentPojo = new ContentPojo( crmDisplayConst.getValue(), crmDisplayConst.getCode() );
                    userServices.add( contentPojo );
                }
            }
            inCrmCapForm.setProductTypeList( userServices );
        }
        else
        {
            inCrmCapForm.setProductTypeList( CRMUtils.getProducts() );
        }
        List<PartnerPojo> allPartnerPojos = CRMCacheManager.getPartnerbyType( CRMDisplayListConstants.BUSINESS_PARTNER
                .getCode(), inCrmCapDto.getCustomerDetailsPojo().getProduct(), CRMStatusCode.ACTIVE.getStatusCode() );
        if ( CommonValidator.isValidCollection( inCrmCapForm.getUserAssociatedPartners() ) )
        {
            List<PartnerPojo> partnerPojos = new ArrayList<PartnerPojo>();
            for ( PartnerPojo partnerPojo : allPartnerPojos )
            {
                if ( inCrmCapForm.getUserAssociatedPartners().contains( partnerPojo.getPartnerId() + "" ) )
                {
                    partnerPojos.add( partnerPojo );
                }
            }
            inCrmCapForm.setBussinessPartners( partnerPojos );
        }
        else
        {
            inCrmCapForm.setBussinessPartners( allPartnerPojos );
        }
        inCrmCapForm.setStatePojoList( GISUtils.getActiveStates( IAppConstants.COUNTRY_INDIA ) );
    }

    private void setPaymentDetails( CrmCapDto inCrmCapDto, CrmCapForm inCrmCapForm )
    {
        if ( StringUtils.isValidObj( inCrmCapDto.getPaymentDetailsPojosList() )
                && !inCrmCapDto.getPaymentDetailsPojosList().isEmpty() )
        {
            LOGGER.info( "PaymentDetailsPojosList is Not Empty::" );
            for ( CrmPaymentDetailsPojo crmPaymentDetailsPojo : inCrmCapDto.getPaymentDetailsPojosList() )
            {
                if ( StringUtils.equals( crmPaymentDetailsPojo.getEntityType(),
                                         CRMDisplayListConstants.TELESERVICES.getCode() ) )
                {
                    if ( StringUtils.equals( CRMDisplayListConstants.SECURITY_DEPOSIT.getCode(),
                                             crmPaymentDetailsPojo.getPaymentChannel() ) )
                    {
                        inCrmCapForm.setSecurityDepositPayment( crmPaymentDetailsPojo );
                        inCrmCapForm
                                .setTempSecurityDepositPayment( BeanUtils.getTemporaryPojo( crmPaymentDetailsPojo ) );
                    }
                    else
                    {
                        inCrmCapForm.setTeleservicesPayment( crmPaymentDetailsPojo );
                        if ( !StringUtils.isValidObj( crmPaymentDetailsPojo.getActivationCharges() ) )
                            crmPaymentDetailsPojo.setActivationCharges( 0.0 );
                        if ( !StringUtils.isValidObj( crmPaymentDetailsPojo.getSecurityCharges() ) )
                            crmPaymentDetailsPojo.setSecurityCharges( 0.0 );
                        inCrmCapForm.setTempTeleservicesPayment( BeanUtils.getTemporaryPojo( crmPaymentDetailsPojo ) );
                    }
                }
                else
                {
                    inCrmCapForm.setTelecommunicationPayment( crmPaymentDetailsPojo );
                    inCrmCapForm.setTempTelecommunicationPayment( BeanUtils.getTemporaryPojo( crmPaymentDetailsPojo ) );
                }
            }
            if ( !StringUtils.isValidObj( inCrmCapForm.getSecurityDepositPayment() ) )
            {
                inCrmCapForm.setSecurityDepositPayment( (CrmPaymentDetailsPojo) GlobalUtils
                        .fillDefaultValues( CrmPaymentDetailsPojo.class, java.math.BigDecimal.class,
                                            new BigDecimal( 0.0 ) ) );
            }
            if ( !StringUtils.isValidObj( inCrmCapForm.getTeleservicesPayment() ) )
            {
                inCrmCapForm.setTeleservicesPayment( (CrmPaymentDetailsPojo) GlobalUtils
                        .fillDefaultValues( CrmPaymentDetailsPojo.class, java.math.BigDecimal.class,
                                            new BigDecimal( 0.0 ) ) );
            }
            if ( !StringUtils.isValidObj( inCrmCapForm.getTelecommunicationPayment() ) )
            {
                inCrmCapForm.setTelecommunicationPayment( (CrmPaymentDetailsPojo) GlobalUtils
                        .fillDefaultValues( CrmPaymentDetailsPojo.class, java.math.BigDecimal.class,
                                            new BigDecimal( 0.0 ) ) );
            }
        }
        else
        {
            LOGGER.info( "filling to big decimal value in pojo:::" );
            inCrmCapForm
                    .setTeleservicesPayment( (CrmPaymentDetailsPojo) GlobalUtils
                            .fillDefaultValues( CrmPaymentDetailsPojo.class, java.math.BigDecimal.class,
                                                new BigDecimal( 0.0 ) ) );
            inCrmCapForm
                    .setTelecommunicationPayment( (CrmPaymentDetailsPojo) GlobalUtils
                            .fillDefaultValues( CrmPaymentDetailsPojo.class, java.math.BigDecimal.class,
                                                new BigDecimal( 0.0 ) ) );
            inCrmCapForm
                    .setSecurityDepositPayment( (CrmPaymentDetailsPojo) GlobalUtils
                            .fillDefaultValues( CrmPaymentDetailsPojo.class, java.math.BigDecimal.class,
                                                new BigDecimal( 0.0 ) ) );
        }
    }

    private void setDocumentsDetails( CrmCapDto inCrmCapDto,
                                      CrmCapForm inCrmCapForm,
                                      List<ContentPojo> allAddProofList,
                                      List<ContentPojo> allIdProofList )
    {
        if ( StringUtils.isValidObj( inCrmCapDto.getDocumentDetailsPojo() ) )
        {
            LOGGER.info( "DocumentDetailsPojo is Valid Object::" );
            if ( StringUtils.isNotBlank( inCrmCapDto.getDocumentDetailsPojo().getAddressProof() ) )
            {
                LOGGER.info( "AddressProof is Not Empty::" + inCrmCapDto.getDocumentDetailsPojo().getAddressProof() );
                if ( StringUtils.contains( inCrmCapDto.getDocumentDetailsPojo().getAddressProof(), IAppConstants.COMMA ) )
                {
                    String addressProff[] = inCrmCapDto.getDocumentDetailsPojo().getAddressProof()
                            .split( IAppConstants.COMMA );
                    splitProof( allAddProofList, addressProff );
                }
            }
            if ( StringUtils.isNotBlank( inCrmCapDto.getDocumentDetailsPojo().getIdProof() ) )
            {
                LOGGER.info( "IdProof is Not Empty::" + inCrmCapDto.getDocumentDetailsPojo().getIdProof() );
                if ( StringUtils.contains( inCrmCapDto.getDocumentDetailsPojo().getIdProof(), IAppConstants.COMMA ) )
                {
                    String idProff[] = inCrmCapDto.getDocumentDetailsPojo().getIdProof().split( IAppConstants.COMMA );
                    splitProof( allIdProofList, idProff );
                }
            }
            inCrmCapForm.setDocumentDetailsPojo( inCrmCapDto.getDocumentDetailsPojo() );
            createTempDocumentPojo( inCrmCapDto, inCrmCapForm );
        }
        else
            inCrmCapForm.setDocumentDetailsPojo( new CrmDocumentDetailsPojo() );
    }

    private void setCustomerDetials( CrmCapDto inCrmCapDto, CrmCapForm inCrmCapForm )
    {
        if ( StringUtils.isValidObj( inCrmCapDto.getCustomerDetailsPojo() ) )
        {
            LOGGER.info( "CustomerDetailsPojo is Valid Object::" );
            if ( CommonValidator.isValidCollection( inCrmCapDto.getCustomerDetailsPojo().getCrmPlanDetailses() ) )
            {
                LOGGER.info( "PlanDetailses is Not Empty::" );
                inCrmCapForm.setPlanDetailsPojo( inCrmCapDto.getCustomerDetailsPojo().getCrmPlanDetailses().get( 0 ) );
                CRMDisplayListConstants constant = CRMDisplayListConstants
                        .getConstantByFilter( "PlanService", inCrmCapDto.getCustomerDetailsPojo().getProduct(),
                                              inCrmCapDto.getCustomerDetailsPojo().getServiceType() );
                CrmPlanMasterPojo basePlanDetails = CommonUtils.getPlanDetails( constant.getCode(), inCrmCapForm
                        .getPlanDetailsPojo().getBasePlanCode(), CRMDisplayListConstants.BASE_PLAN.getCode() );
                CrmPlanMasterPojo addOnPlanDetails = CommonUtils.getPlanDetails( constant.getCode(), inCrmCapForm
                        .getPlanDetailsPojo().getAddOnPlanCode(), CRMDisplayListConstants.ADDON_PLAN.getCode() );
                if ( StringUtils.isValidObj( basePlanDetails ) )
                {
                    inCrmCapForm.setPlanType( basePlanDetails.getPlanType() );
                    if ( !StringUtils.equals( IAppConstants.Y, basePlanDetails.getAddonAllowedYn() ) )
                    {
                        inCrmCapForm.getPlanDetailsPojo().setAddOnPlanCode( null );
                        inCrmCapForm.setAddOnNotAllowed( true );
                    }
                    else
                    {
                        inCrmCapForm.setAddOnNotAllowed( false );
                    }
                }
                createTempPlanDetailPojo( inCrmCapDto, inCrmCapForm );
                setMinReqAmounts( inCrmCapForm, basePlanDetails, addOnPlanDetails );
            }
            else
            {
                inCrmCapForm.setPlanDetailsPojo( new CrmPlanDetailsPojo() );
            }
            if ( CommonValidator.isValidCollection( inCrmCapDto.getCustomerDetailsPojo().getCrmAddressDetailses() ) )
            {
                LOGGER.info( "CrmAddressDetailses is Not Empty::" );
                for ( CrmAddressDetailsPojo addressDetailsPojo : inCrmCapDto.getCustomerDetailsPojo()
                        .getCrmAddressDetailses() )
                {
                    if ( StringUtils.equals( addressDetailsPojo.getAddressType(),
                                             IAppConstants.ADDRESS_TYPE_INSTALLATION ) )
                    {
                        inCrmCapForm.setInstallationAddressPojo( addressDetailsPojo );
                        createTempInstallationPojo( addressDetailsPojo, inCrmCapForm );
                    }
                    else
                    {
                        inCrmCapForm.setBillingAddressPojo( addressDetailsPojo );
                        createTempBillingPojo( addressDetailsPojo, inCrmCapForm );
                    }
                }
            }
            else if ( StringUtils.isValidObj( inCrmCapDto.getLmsPojo() ) )
            {
                LOGGER.info( "LMS POJO Found:" );
                CrmAddressDetailsPojo addressDetailsPojo = new CrmAddressDetailsPojo();
                inCrmCapDto.getCustomerDetailsPojo().setCustFname( inCrmCapDto.getLmsPojo().getCustomerName() );
                inCrmCapDto.getCustomerDetailsPojo().setRmn( inCrmCapDto.getLmsPojo().getContactNumber() );
                inCrmCapDto.getCustomerDetailsPojo().setCustEmailId( inCrmCapDto.getLmsPojo().getEmailId() );
                addressDetailsPojo.setAddLine3( inCrmCapDto.getLmsPojo().getCity() + IAppConstants.COMMA
                        + IAppConstants.WHITE_SPACE + inCrmCapDto.getLmsPojo().getState() );
                addressDetailsPojo.setLandmark( inCrmCapDto.getLmsPojo().getLandmark() );
                if ( inCrmCapDto.getLmsPojo().getPincode() > 0 )
                {
                    addressDetailsPojo.setPincode( inCrmCapDto.getLmsPojo().getPincode() );
                }
                // For fesible Address
                String locality = inCrmCapDto.getLmsPojo().getLocality();
                String society = null;
                String[] arr = StringUtils.split( locality, IAppConstants.DASH );
                if ( arr.length > 1 )
                {
                    locality = arr[0];
                    society = arr[1];
                }
                inCrmCapForm.setSociety( StringUtils.trimToEmpty( society ) );
                inCrmCapForm.setLocality( StringUtils.trimToEmpty( locality ) );
                addressDetailsPojo.setAddLine1( inCrmCapDto.getLmsPojo().getHouseNumber() + IAppConstants.COMMA
                        + IAppConstants.WHITE_SPACE + inCrmCapForm.getSociety() );
                addressDetailsPojo.setAddLine2( inCrmCapForm.getLocality() + IAppConstants.COMMA
                        + IAppConstants.WHITE_SPACE + inCrmCapDto.getLmsPojo().getArea() );
                inCrmCapForm.setArea( inCrmCapDto.getLmsPojo().getArea() );
                inCrmCapForm.setCity( inCrmCapDto.getLmsPojo().getCity() );
                inCrmCapForm.setState( inCrmCapDto.getLmsPojo().getState() );
                inCrmCapForm.setInstallationAddressPojo( addressDetailsPojo );
                createTempInstallationPojo( addressDetailsPojo, inCrmCapForm );
                inCrmCapForm.setBillingAddressPojo( new CrmAddressDetailsPojo() );
            }
            else
            {
                inCrmCapForm.setInstallationAddressPojo( new CrmAddressDetailsPojo() );
                inCrmCapForm.setBillingAddressPojo( new CrmAddressDetailsPojo() );
                if ( StringUtils.isValidObj( inCrmCapForm.getAadharNumberPojo() ) )
                {
                    inCrmCapForm.getInstallationAddressPojo().setPropertyDetails( inCrmCapForm.getAadharNumberPojo()
                                                                                          .getPropertyDetails() );
                }
            }
            inCrmCapForm.setCustomerDetailsPojo( inCrmCapDto.getCustomerDetailsPojo() );
            createTempCustomerPojo( inCrmCapDto, inCrmCapForm );
        }
    }

    private void setMinReqAmounts( CrmCapForm inCrmCapForm,
                                   CrmPlanMasterPojo basePlanDetails,
                                   CrmPlanMasterPojo addOnPlanDetails )
    {
        double miniSecurity = 0;
        double miniRental = 0;
        double miniTelSolnAmount = 0;
        if ( StringUtils.isValidObj( basePlanDetails ) )
        {
            miniSecurity = basePlanDetails.getSecurityDeposit();
            miniTelSolnAmount = basePlanDetails.getAdvTelsolnPayment();
            if ( StringUtils.equals( CRMDisplayListConstants.PRE_PAID.getCode(), inCrmCapForm.getCustomerDetailsPojo()
                    .getServiceType() ) )
            {
                miniRental = basePlanDetails.getRentInclTax();
                if ( StringUtils.isValidObj( addOnPlanDetails ) )
                {
                    miniRental = addOnPlanDetails.getRentInclTax();
                }
            }
            else if ( StringUtils.equals( CRMDisplayListConstants.POST_PAID.getCode(), inCrmCapForm
                    .getCustomerDetailsPojo().getServiceType() ) )
            {
                miniRental = basePlanDetails.getAdvTelservPayment();
            }
        }
        inCrmCapForm.setMinimumSecurityDeposit( miniSecurity );
        inCrmCapForm.setMinimumRentalCharge( miniRental );
        inCrmCapForm.setMiniTotalRental( miniRental );
        inCrmCapForm.setMiniTelesolutionAmount( miniTelSolnAmount );
    }

    @Override
    public void setActivationPlans( CrmCapDto inCrmCapDto, CrmCapForm inCrmCapForm )
    {
        CRMDisplayListConstants constant = CRMDisplayListConstants.getConstantByFilter( "PlanService", inCrmCapDto
                .getCustomerDetailsPojo().getProduct(), inCrmCapDto.getCustomerDetailsPojo().getServiceType() );
        String prepaid = ( inCrmCapDto.getCustomerDetailsPojo().getServiceType().equals( "PR" ) ? "Y" : "N" );
        if ( StringUtils.isNotBlank( inCrmCapForm.getPlanType() ) )
        {
            inCrmCapForm.setBasePlanNames( CRMCacheManager.getActivationPlansList( constant.getCode(), prepaid,
                                                                                   inCrmCapForm.getPlanType() ) );
        }
        else
        {
            inCrmCapForm.setBasePlanNames( CRMCacheManager.getActivationPlans( constant.getCode(), prepaid ) );
        }
        inCrmCapForm.setAddonPlanNames( CRMCacheManager.getAddonPlanDetails( constant.getCode() ) );
        SortingComparator<CrmPlanMasterPojo> sorter = new SortingComparator<CrmPlanMasterPojo>( "planName" );
        Collections.sort( inCrmCapForm.getBasePlanNames(), sorter );
        Collections.sort( inCrmCapForm.getAddonPlanNames(), sorter );
        sorter = null;
        inCrmCapForm.setPlanService( constant.getCode() );
    }

    @Override
    public void setUserPojos( CrmCapForm inCrmCapForm )
    {
        MasterDataDto masterDataDto = new MasterDataDto();
        try
        {
            masterDataDto.setPartnerId( inCrmCapForm.getCustomerDetailsPojo().getBusinessPartner() );
            masterDataDto.setProduct( inCrmCapForm.getCustomerDetailsPojo().getProduct() );
            masterDataDto = getMasterDataClient().masterOperations( ServiceParameter.LIST.getParameter(),
                                                                    CrmUserPojo.class.getSimpleName(), masterDataDto );
            if ( CommonValidator.isValidCollection( masterDataDto.getCrmUserPojos() ) )
            {
                LOGGER.info( "CrmUserPojos Size:" + masterDataDto.getCrmUserPojos().size() );
                inCrmCapForm.setCrmUserPojos( masterDataDto.getCrmUserPojos() );
            }
        }
        catch ( SOAPException_Exception ex )
        {
            LOGGER.error( "exception occure while getting UserList", ex );
        }
    }

    private void splitProof( List<ContentPojo> inAllAddProofList, String[] inAddressProff )
    {
        LOGGER.info( "Spliting Address Proof" );
        for ( int i = 0; i < inAddressProff.length; i++ )
        {
            for ( ContentPojo contentPojo2 : inAllAddProofList )
            {
                if ( contentPojo2.getContentValue().equals( inAddressProff[i] ) )
                {
                    LOGGER.info( "Selected value ----------- " + inAddressProff[i] );
                    contentPojo2.setSelected( true );
                }
            }
        }
    }

    public static void main( String[] args )
    {
        /* Date today = new Date();
         //Converting date to XMLGregorianCalendar in Java
         XMLGregorianCalendar xml = toXMLGregorianCalendar( today );
         System.out.println( "XMLGregorianCalendar from Date in Java      : " + xml );
         //Converting XMLGregorianCalendar to java.util.Date in Java
         Date date = toDate( xml );
         System.out.println( "java.util.Date from XMLGregorianCalendar in Java : " + date );
         String crfId = "RA0001";
         if ( StringUtils.isNotBlank( crfId ) )
             ;
         {
             String productName = crfId.substring( 0, 2 );
             System.out.println( productName );
         }*/
    }

    private void createTempBillingPojo( CrmAddressDetailsPojo addressDetailsPojo, CrmCapForm inCrmCapForm )
    {
        CrmAddressDetailsPojo addTempAddressPojo = new CrmAddressDetailsPojo();
        try
        {
            PropertyUtils.copyProperties( addTempAddressPojo, addressDetailsPojo );
            inCrmCapForm.setTempBillingAddressPojo( addTempAddressPojo );
        }
        catch ( Exception ex )
        {
            LOGGER.error( "Getting Error while copy properties in method createTempBillingPojo:: ", ex );
        }
    }

    private void createTempInstallationPojo( CrmAddressDetailsPojo addressDetailsPojo, CrmCapForm inCrmCapForm )
    {
        CrmAddressDetailsPojo addTempInstallationPojo = new CrmAddressDetailsPojo();
        try
        {
            PropertyUtils.copyProperties( addTempInstallationPojo, addressDetailsPojo );
            inCrmCapForm.setTempInstallationAddressPojo( addTempInstallationPojo );
        }
        catch ( Exception ex )
        {
            LOGGER.error( "Getting Error while copy properties in method createTempInstallationPojo:: ", ex );
        }
    }

    private void createTempPlanDetailPojo( CrmCapDto crmCapDto, CrmCapForm inCrmCapForm )
    {
        CrmPlanDetailsPojo tempPlanDetailsPojo = new CrmPlanDetailsPojo();
        try
        {
            PropertyUtils.copyProperties( tempPlanDetailsPojo, crmCapDto.getCustomerDetailsPojo().getCrmPlanDetailses()
                    .get( 0 ) );
            inCrmCapForm.setTempPlanDetailsPojo( tempPlanDetailsPojo );
        }
        catch ( Exception ex )
        {
            LOGGER.error( "Getting Error while copy properties in method createTempPlanDetailPojo:: ", ex );
        }
    }

    private void createTempAppointmentPojo( CrmCapDto crmCapDto, CrmCapForm inCrmCapForm )
    {
        AppointmentPojo tempAppointmentPojo = new AppointmentPojo();
        try
        {
            PropertyUtils.copyProperties( tempAppointmentPojo, crmCapDto.getAppointmentPojo() );
            inCrmCapForm.setTempAppointmentPojo( tempAppointmentPojo );
        }
        catch ( Exception ex )
        {
            LOGGER.error( "Getting Error while copy properties in method createTempAppointmentPojo:: ", ex );
        }
    }

    private void createTempOrderDetailPojo( CrmCapDto crmCapDto, CrmCapForm inCrmCapForm )
    {
        CrmOrderDetailsPojo tempOrderDetailsPojo = new CrmOrderDetailsPojo();
        try
        {
            PropertyUtils.copyProperties( tempOrderDetailsPojo, crmCapDto.getOrderDetailsPojo() );
            inCrmCapForm.setTempOrderDetailsPojo( tempOrderDetailsPojo );
        }
        catch ( Exception ex )
        {
            LOGGER.error( "Getting Error while copy properties in method createTempOrderDetailPojo:: ", ex );
        }
    }

    //    private void createTempNetworkPojo( CrmCapDto crmCapDto, CrmCapForm inCrmCapForm )
    //    {
    //        CrmNetworkDetailsPojo tempNetworkDetailsPojo = new CrmNetworkDetailsPojo();
    //        try
    //        {
    //            PropertyUtils.copyProperties( tempNetworkDetailsPojo, crmCapDto.getNetworkDetailsPojo() );
    //            inCrmCapForm.setTempNetworkDetailsPojo( tempNetworkDetailsPojo );
    //        }
    //        catch ( Exception ex )
    //        {
    //            ex.printStackTrace();
    //        }
    //    }
    private void createTempNationalityPojo( CrmCapDto crmCapDto, CrmCapForm inCrmCapForm )
    {
        CrmNationalityDetailsPojo tempNationalityDetailsPojo = new CrmNationalityDetailsPojo();
        try
        {
            PropertyUtils.copyProperties( tempNationalityDetailsPojo, crmCapDto.getNationalityDetailsPojo() );
            inCrmCapForm.setTempNationalityDetailsPojo( tempNationalityDetailsPojo );
        }
        catch ( Exception ex )
        {
            LOGGER.error( "Getting Error while copy properties in method createTempNationalityPojo:: ", ex );
        }
    }

    private void createTempDocumentPojo( CrmCapDto crmCapDto, CrmCapForm inCrmCapForm )
    {
        CrmDocumentDetailsPojo tempCrmDocumentDetailsPojo = new CrmDocumentDetailsPojo();
        try
        {
            PropertyUtils.copyProperties( tempCrmDocumentDetailsPojo, crmCapDto.getDocumentDetailsPojo() );
            inCrmCapForm.setTempDocumentDetailsPojo( tempCrmDocumentDetailsPojo );
        }
        catch ( Exception ex )
        {
            LOGGER.error( "Getting Error while copy properties in method createTempDocumentPojo:: ", ex );
        }
    }

    private void createTempCustomerPojo( CrmCapDto crmCapDto, CrmCapForm inCrmCapForm )
    {
        CrmCustomerDetailsPojo tempCrmCustomerDetailsPojo = new CrmCustomerDetailsPojo();
        try
        {
            PropertyUtils.copyProperties( tempCrmCustomerDetailsPojo, crmCapDto.getCustomerDetailsPojo() );
            inCrmCapForm.setTempCustomerDetailsPojo( tempCrmCustomerDetailsPojo );
        }
        catch ( Exception ex )
        {
            LOGGER.error( "Getting Error while copy properties in method createTempCustomerPojo:: ", ex );
        }
    }

    private void createTempAdditionalPojo( CrmCapDto crmCapDto, CrmCapForm inCrmCapForm )
    {
        CrmAdditionalDetailsPojo tempAdditionalDetailsPojo = new CrmAdditionalDetailsPojo();
        try
        {
            PropertyUtils.copyProperties( tempAdditionalDetailsPojo, crmCapDto.getAdditionalDetailsPojo() );
            inCrmCapForm.setTempAdditionalDetailsPojo( tempAdditionalDetailsPojo );
            //CrmCapTempPojoUtility.setTempAdditionalDetailsPojo( tempAdditionalDetailsPojo );
        }
        catch ( Exception ex )
        {
            LOGGER.error( "Getting Error while copy properties in method createTempAdditionalPojo:: ", ex );
        }
    }

    private void createTempNetworkConfigurationsPojo( CrmCapDto inCrmCapDto, CrmCapForm inCrmCapForm )
    {
        CrmNetworkConfigurationsPojo tempNetworkConfigurationsPojo = new CrmNetworkConfigurationsPojo();
        try
        {
            PropertyUtils.copyProperties( tempNetworkConfigurationsPojo, inCrmCapDto.getNetworkConfigurationsPojo() );
            inCrmCapForm.setTempNetworkConfigurations( tempNetworkConfigurationsPojo );
        }
        catch ( Exception ex )
        {
            LOGGER.error( "Getting Error while copy properties in method createTempNetworkConfigurationsPojo:: ", ex );
        }
    }

    @Override
    public CrmCapDto saveValidatedCRFEntry( CrmCapForm inCrmCapForm, String inUserId )
    {
        CrmCapDto crmCapDto = new CrmCapDto();
        try
        {
            if ( StringUtils.isValidObj( inCrmCapForm.getCustomerDetailsPojo() ) )
            {
                crmCapDto.setCustomerDetailsPojo( inCrmCapForm.getCustomerDetailsPojo() );
                LOGGER.info( "CAF Id ::" + inCrmCapForm.getCustomerDetailsPojo().getCrfId() + "Product :"
                        + inCrmCapForm.getCustomerDetailsPojo().getProduct() );
            }
            if ( StringUtils.isValidObj( inCrmCapForm.getRemarksPojo() ) )
            {
                inCrmCapForm.getRemarksPojo().setMappingId( String.valueOf( inCrmCapForm.getCustomerDetailsPojo()
                                                                    .getRecordId() ) );
                inCrmCapForm.getRemarksPojo().setCreatedBy( inUserId );
                crmCapDto.setRemarksPojo( inCrmCapForm.getRemarksPojo() );
            }
            if ( StringUtils.isNotBlank( inCrmCapForm.getDisplayISRDate() ) )
            {
                LOGGER.info( "CAF ISR Date:: " + inCrmCapForm.getDisplayISRDate() );
                crmCapDto.getCustomerDetailsPojo().setDateOnIsr( DateUtils.changeDateFormat( inCrmCapForm
                                                                         .getDisplayISRDate() ) );
                crmCapDto.setToStage( CRMOperationStages.ON_BOARD.getCode() );
                crmCapDto.getRemarksPojo().setActions( CrmActionEnum.FORWARDED.getActionCode() );
            }
            else
            {
                if ( StringUtils.equals( inCrmCapForm.getRemarksPojo().getActions(), IAppConstants.APPROVE ) )
                {
                    if ( StringUtils.equals( CRMOperationStages.FULFILLMENT_TEAM.getCode(), inCrmCapForm
                            .getCustomerDetailsPojo().getCrfStage() )
                            || StringUtils.equals( CRMOperationStages.NP_REJECT.getCode(), inCrmCapForm
                                    .getCustomerDetailsPojo().getCrfStage() ) )
                    {
                        crmCapDto.setToStage( CRMOperationStages.NETWORK_PARTNER.getCode() );
                    }
                    else
                    {
                        crmCapDto.setToStage( inCrmCapForm.getCustomerDetailsPojo().getCrfPreviousStage() );
                    }
                    crmCapDto.getRemarksPojo().setActions( CrmActionEnum.FORWARDED.getActionCode() );
                }
                else if ( StringUtils.equals( inCrmCapForm.getRemarksPojo().getActions(),
                                              IAppConstants.CHANGE_SELES_ADD ) )
                {
                    LOGGER.info( "CHANGE_SELES_ADD " );
                    getGisDetailsByMaster( inCrmCapForm );
                    crmCapDto.setAddressDetailsPojo( inCrmCapForm.getInstallationAddressPojo() );
                    crmCapDto.setToStage( CRMOperationStages.FULFILLMENT_TEAM.getCode() );
                    crmCapDto.getRemarksPojo().setActions( CrmActionEnum.FORWARDED.getActionCode() );
                }
                else
                {
                    if ( StringUtils.equals( CRMOperationStages.FULFILLMENT_TEAM.getCode(), inCrmCapForm
                            .getCustomerDetailsPojo().getCrfStage() ) )
                    {
                        crmCapDto.setToStage( CRMOperationStages.FT_REJECT.getCode() );
                        crmCapDto.getRemarksPojo().setActions( CrmActionEnum.REJECTED.getActionCode() );
                    }
                    else
                    {
                        crmCapDto.getRemarksPojo().setActions( CrmActionEnum.CANCEL.getActionCode() );
                        crmCapDto.setToStage( CRMOperationStages.CANCEL.getCode() );
                        crmCapDto.getCustomerDetailsPojo().setStatus( CRMStatusCode.CANCELED.getStatusCode() );
                    }
                }
            }
            crmCapDto.setUserId( inUserId );
            //crmCapDto.setCustomerDetailsPojo( inCrmCapForm.getCustomerDetailsPojo() );
            //client calling
            crmCapDto = getCapServiceClient().crmCapOperation( ServiceParameter.FORWARD.getParameter(),
                                                               CRMRequestType.CAF.getRequestCode(), crmCapDto );
        }
        catch ( SOAPException_Exception ex )
        {
            LOGGER.info( "error occured calling client to service for validate CAF entry", ex );
        }
        return crmCapDto;
    }

    @Override
    public CrmCapDto saveNetworkDetails( CrmCapForm inCrmCapForm, String inUserId )
    {
        CrmCapDto crmCapDto = null;
        try
        {
            crmCapDto = new CrmCapDto();
            LOGGER.info( "RemarksPojo Action ::" + inCrmCapForm.getRemarksPojo().getActions() + ",ReasonId ::"
                    + inCrmCapForm.getRemarksPojo().getReasonId() );
            crmCapDto.setCustomerDetailsPojo( inCrmCapForm.getCustomerDetailsPojo() );
            crmCapDto.setUserId( inUserId );
            if ( StringUtils.isNotBlank( inCrmCapForm.getRemarksPojo().getActions() ) )
            {
                LOGGER.info( "Forwad message:::: " + inCrmCapForm.getRemarksPojo().getActions() );
                if ( StringUtils.equals( inCrmCapForm.getRemarksPojo().getActions(), IAppConstants.REJECT_BY_NP ) )
                {
                    inCrmCapForm.getRemarksPojo().setActions( CrmActionEnum.REJECTED.getActionCode() );
                    fillRemarksPojo( inCrmCapForm, inUserId, crmCapDto );
                    crmCapDto.setToStage( CRMOperationStages.NP_REJECT.getCode() );
                }
                else if ( StringUtils.equals( inCrmCapForm.getRemarksPojo().getActions(), IAppConstants.SUBMIT_REFUSAL ) )
                {
                    inCrmCapForm.getRemarksPojo().setActions( CrmActionEnum.REFUSAL.getActionCode() );
                    fillRemarksPojo( inCrmCapForm, inUserId, crmCapDto );
                    crmCapDto.setToStage( CRMOperationStages.NP_REJECT_SALES.getCode() );
                }
                else if ( StringUtils.equals( inCrmCapForm.getRemarksPojo().getActions(),
                                              IAppConstants.CHANGE_FEASIBLE_ADD ) )
                {
                    inCrmCapForm.getRemarksPojo().setActions( CrmActionEnum.REFUSAL.getActionCode() );
                    fillRemarksPojo( inCrmCapForm, inUserId, crmCapDto );
                    crmCapDto.setToStage( CRMOperationStages.NP_REJECT_SALES.getCode() );
                }
                else if ( StringUtils.equals( inCrmCapForm.getRemarksPojo().getActions(),
                                              IAppConstants.CRF_CANCELLATION ) )
                {
                    inCrmCapForm.getRemarksPojo().setActions( CrmActionEnum.CANCEL.getActionCode() );
                    fillRemarksPojo( inCrmCapForm, inUserId, crmCapDto );
                    crmCapDto.setToStage( CRMOperationStages.CANCEL.getCode() );
                    crmCapDto.getCustomerDetailsPojo().setStatus( CRMStatusCode.CANCELED.getStatusCode() );
                }
                else if ( StringUtils.equals( inCrmCapForm.getRemarksPojo().getActions(), IAppConstants.CRF_ERP ) )
                {
                    inCrmCapForm.getRemarksPojo().setActions( CrmActionEnum.ERP.getActionCode() );
                    fillRemarksPojo( inCrmCapForm, inUserId, crmCapDto );
                }
                else
                {
                    // CRF forward to SP state
                    LOGGER.info( "Network Details save for customer id:: "
                            + inCrmCapForm.getCustomerDetailsPojo().getRecordId() );
                    inCrmCapForm.getCrmNetworkConfigurations().setCreatedBy( inUserId );
                    inCrmCapForm.getCrmNetworkConfigurations().setCustomerRecordId( inCrmCapForm
                                                                                            .getCustomerDetailsPojo()
                                                                                            .getRecordId() );
                    crmCapDto.setNetworkConfigurationsPojo( inCrmCapForm.getCrmNetworkConfigurations() );
                    inCrmCapForm.getRemarksPojo().setActions( CrmActionEnum.FORWARDED.getActionCode() );
                    inCrmCapForm.getRemarksPojo().setMappingId( String.valueOf( inCrmCapForm.getCustomerDetailsPojo()
                                                                        .getRecordId() ) );
                    inCrmCapForm.getRemarksPojo().setCreatedBy( inUserId );
                    crmCapDto.setRemarksPojo( inCrmCapForm.getRemarksPojo() );
                    LOGGER.info( "CAF_Id ::" + inCrmCapForm.getCustomerDetailsPojo().getCrfId() );
                    crmCapDto.setToStage( CRMOperationStages.SERVICE_PARTNER.getCode() );
                }
            }
            //client calling
            crmCapDto = getCapServiceClient().crmCapOperation( ServiceParameter.FORWARD.getParameter(),
                                                               CRMRequestType.CAF.getRequestCode(), crmCapDto );
        }
        catch ( Exception ex )
        {
            LOGGER.info( "error occured calling client to service for save network details", ex );
        }
        return crmCapDto;
    }

    @Override
    public List<CrmPartnerMappingPojo> getServicePartnerList( CrmCapForm incrfCapForm )
    {
        MasterDataDto masterDataDto = null;
        try
        {
            PartnerPojo pojo = new PartnerPojo();
            masterDataDto = new MasterDataDto();
            pojo.setPartnerId( incrfCapForm.getCustomerDetailsPojo().getNpId() );
            CrmPartnerMappingPojo mappingpojo = new CrmPartnerMappingPojo();
            mappingpojo.setPartnerByNpId( pojo );
            mappingpojo.setProduct( incrfCapForm.getCustomerDetailsPojo().getProduct() );
            mappingpojo.setStatus( CRMStatusCode.ACTIVE.getStatusCode() );
            masterDataDto.setCrmPartnerMappingPojo( mappingpojo );
            masterDataDto = getMasterDataClient().partnerOperations( ServiceParameter.VIEW.getParameter(),
                                                                     masterDataDto );
            LOGGER.info( "Result Mapping List Size........" + masterDataDto.getCrmPartnerMappingList().size() );
        }
        catch ( SOAPException_Exception ex )
        {
            LOGGER.error( "Getting Error While, Fetching Category Value: " + ex );
        }
        return masterDataDto.getCrmPartnerMappingList();
    }

    @Override
    public void setNetworkPartnersByProduct( CrmCapForm inCrmCapForm, String inProduct )
    {
        LOGGER.info( "Society data::" + inCrmCapForm.getState() + inCrmCapForm.getCity() + inCrmCapForm.getArea()
                + inCrmCapForm.getLocality() + inCrmCapForm.getSociety() + inProduct );
        List<SocietyPojo> societyPojos = GISUtils.getFeasibleSocieties( IAppConstants.COUNTRY_INDIA, inCrmCapForm
                                                                                .getInstallationAddressPojo()
                                                                                .getStateId(), inCrmCapForm
                                                                                .getInstallationAddressPojo()
                                                                                .getCityId(),
                                                                        inCrmCapForm.getInstallationAddressPojo()
                                                                                .getInstAreaId(), inProduct );
        Set<PartnerPojo> partnerPojos = new HashSet<PartnerPojo>();
        if ( CommonValidator.isValidCollection( societyPojos ) )
        {
            List<SocietyNetworkPartnerPojo> societyNetworkPartnerPojos = null;
            if ( inCrmCapForm.getInstallationAddressPojo().getInstSocietyId() > 0 )
            {
                SocietyPojo societyPojo = GISUtils.getSociety( inCrmCapForm.getInstallationAddressPojo()
                        .getInstSocietyId(), societyPojos );
                if ( StringUtils.isValidObj( societyPojo ) )
                {
                    societyNetworkPartnerPojos = societyPojo.getSocietyNetworkPartners();
                    if ( societyNetworkPartnerPojos.size() > 0 )
                    {
                        for ( SocietyNetworkPartnerPojo spojo : societyNetworkPartnerPojos )
                        {
                            if ( StringUtils.equals( spojo.getProductName(), inProduct )
                                    && StringUtils.equals( spojo.getStatus(), CRMStatusCode.ACTIVE.getStatusCode() ) )
                            {
                                partnerPojos.add( CRMCacheManager.getPartnerById( spojo.getPartnerId() + "" ) );
                            }
                        }
                    }
                }
            }
            else
            {
                SocietyPojo societyPojo = GISUtils.getSociety( inCrmCapForm.getSociety(), societyPojos );
                if ( StringUtils.isValidObj( societyPojo ) )
                {
                    societyNetworkPartnerPojos = societyPojo.getSocietyNetworkPartners();
                    if ( CommonValidator.isValidCollection( societyNetworkPartnerPojos ) )
                    {
                        for ( SocietyNetworkPartnerPojo spojo : societyNetworkPartnerPojos )
                        {
                            if ( StringUtils.equals( spojo.getProductName(), inProduct )
                                    && StringUtils.equals( spojo.getStatus(), CRMStatusCode.ACTIVE.getStatusCode() ) )
                            {
                                partnerPojos.add( CRMCacheManager.getPartnerById( spojo.getPartnerId() + "" ) );
                            }
                        }
                    }
                }
                else
                {
                    for ( SocietyPojo tmpSociety : societyPojos )
                    {
                        societyNetworkPartnerPojos = tmpSociety.getSocietyNetworkPartners();
                        if ( CommonValidator.isValidCollection( societyNetworkPartnerPojos ) )
                        {
                            for ( SocietyNetworkPartnerPojo spojo : societyNetworkPartnerPojos )
                            {
                                if ( StringUtils.equals( spojo.getProductName(), inProduct )
                                        && StringUtils.equals( spojo.getStatus(), CRMStatusCode.ACTIVE.getStatusCode() ) )
                                {
                                    partnerPojos.add( CRMCacheManager.getPartnerById( spojo.getPartnerId() + "" ) );
                                }
                            }
                        }
                    }
                }
            }
        }
        List<PartnerPojo> activePartners = CRMCacheManager.getPartnerbyType( CRMDisplayListConstants.NETWORK_PARTNER
                .getCode(), inProduct, CRMStatusCode.ACTIVE.getStatusCode() );
        if ( CommonValidator.isValidCollection( activePartners ) && CommonValidator.isValidCollection( partnerPojos ) )
        {
            // Retain only Active Network Partners
            partnerPojos.retainAll( activePartners );
        }
        if ( CommonValidator.isValidCollection( partnerPojos ) )
        {
            inCrmCapForm.setNetworkPartnerList( new ArrayList<PartnerPojo>( partnerPojos ) );
        }
    }

    @Override
    public CrmCapDto saveMapMacIdDetails( CrmCapForm inCrmCapForm, String inUserId )
        throws Exception
    {
        CrmCapDto crmCapDto = null;
        try
        {
            crmCapDto = new CrmCapDto();
            LOGGER.info( "CPE Mac Id ::" + inCrmCapForm.getCrmNetworkConfigurations().getCurrentCpeMacId() );
            crmCapDto.setCustomerDetailsPojo( inCrmCapForm.getCustomerDetailsPojo() );
            crmCapDto.setUserId( inUserId );
            if ( StringUtils.isNotBlank( inCrmCapForm.getRemarksPojo().getActions() ) )
            {
                crmCapDto.setNetworkConfigurationsPojo( inCrmCapForm.getCrmNetworkConfigurations() );
                LOGGER.info( "Forwad message:::: " + inCrmCapForm.getRemarksPojo().getActions() );
                if ( inCrmCapForm.getRemarksPojo().getActions().equals( IAppConstants.REJECT_BY_SP ) )
                {
                    // CAF Forward to network team
                    inCrmCapForm.getRemarksPojo().setActions( CrmActionEnum.REJECTED.getActionCode() );
                    fillRemarksPojo( inCrmCapForm, inUserId, crmCapDto );
                    crmCapDto.setToStage( CRMOperationStages.SP_REJECT_NP.getCode() );
                    getInboxData( inCrmCapForm, String.valueOf( inCrmCapForm.getCustomerDetailsPojo().getRecordId() ),
                                  inCrmCapForm.getCustomerDetailsPojo().getCrfPreviousStage(),
                                  CRMDisplayListConstants.CAF.getCode() );
                }
                else if ( inCrmCapForm.getRemarksPojo().getActions().equals( IAppConstants.SUBMIT_REFUSAL_BY_SP ) )
                {
                    inCrmCapForm.getRemarksPojo().setActions( CrmActionEnum.REFUSAL.getActionCode() );
                    fillRemarksPojo( inCrmCapForm, inUserId, crmCapDto );
                    crmCapDto.setToStage( CRMOperationStages.SP_REJECT_BP.getCode() );
                }
                else if ( inCrmCapForm.getRemarksPojo().getActions().equals( IAppConstants.CHANGE_FEASIBLE_ADD ) )
                {
                    inCrmCapForm.getRemarksPojo().setActions( CrmActionEnum.REFUSAL.getActionCode() );
                    fillRemarksPojo( inCrmCapForm, inUserId, crmCapDto );
                    crmCapDto.setToStage( CRMOperationStages.SP_REJECT_BP.getCode() );
                }
                else if ( inCrmCapForm.getRemarksPojo().getActions().equals( IAppConstants.CRF_CANCELLATION )
                        || inCrmCapForm.getRemarksPojo().getActions().equals( CrmActionEnum.CANCEL.getActionCode() ) )
                {
                    inCrmCapForm.getRemarksPojo().setActions( CrmActionEnum.CANCEL.getActionCode() );
                    fillRemarksPojo( inCrmCapForm, inUserId, crmCapDto );
                    crmCapDto.setToStage( CRMOperationStages.CANCEL.getCode() );
                    crmCapDto.getCustomerDetailsPojo().setStatus( CRMStatusCode.CANCELED.getStatusCode() );
                }
                else if ( StringUtils.equals( inCrmCapForm.getRemarksPojo().getActions(), IAppConstants.CRF_ERP ) )
                {
                    inCrmCapForm.getRemarksPojo().setActions( CrmActionEnum.ERP.getActionCode() );
                    fillRemarksPojo( inCrmCapForm, inUserId, crmCapDto );
                }
                else
                {
                    // CAF forward to Close state
                    LOGGER.info( "Service Model ::" + inCrmCapForm.getCrmNetworkConfigurations().getServiceModel() );
                    inCrmCapForm.getCrmNetworkConfigurations().setCreatedBy( inUserId );
                    crmCapDto.setNetworkConfigurationsPojo( inCrmCapForm.getCrmNetworkConfigurations() );
                    inCrmCapForm.getOrderDetailsPojo().setCreatedBy( inUserId );
                    crmCapDto.setOrderDetailsPojo( inCrmCapForm.getOrderDetailsPojo() );
                    inCrmCapForm.getTelecommunicationPayment().setCreatedBy( inUserId );
                    crmCapDto.setPaymentDetailsPojo( inCrmCapForm.getTelecommunicationPayment() );
                    inCrmCapForm.getRemarksPojo().setActions( CrmActionEnum.FORWARDED.getActionCode() );
                    fillRemarksPojo( inCrmCapForm, inUserId, crmCapDto );
                    LOGGER.info( "CAF_Id ::" + inCrmCapForm.getCustomerDetailsPojo().getCrfId() );
                    crmCapDto.getCustomerDetailsPojo().setActivationDate( DateUtils.changeDateFormat( ( ( inCrmCapForm
                                                                                  .getActivationDate() ) ) ) );
                    if ( ( StringUtils.equals( inCrmCapForm.getCustomerDetailsPojo().getProduct(),
                                               CRMDisplayListConstants.BROADBAND.getCode() ) || StringUtils
                            .equals( inCrmCapForm.getCustomerDetailsPojo().getProduct(),
                                     CRMDisplayListConstants.ETHERNET_ON_CABLE.getCode() ) )
                            && StringUtils.equals( inCrmCapForm.getCustomerDetailsPojo().getServiceType(),
                                                   CRMDisplayListConstants.POST_PAID.getCode() ) )
                    {
                        crmCapDto.setToStage( CRMOperationStages.ISR_PUNCH.getCode() );
                    }
                    else
                    {
                        crmCapDto.setToStage( CRMOperationStages.ON_BOARD.getCode() );
                    }
                }
            }
            //client calling
            /* if ( inCrmCapForm.getRemarksPojo().getActions().equals( IAppConstants.CRF_ERP ) )
             {
                 crmCapDto = getCapServiceClient().crmCapOperation( ServiceParameter.SAVE.getParameter(),
                                                                    CRMRequestType.CAF.getRequestCode(), crmCapDto );
             }
             else {*/
            crmCapDto = getCapServiceClient().crmCapOperation( ServiceParameter.FORWARD.getParameter(),
                                                               CRMRequestType.CAF.getRequestCode(), crmCapDto );
            // }
        }
        catch ( Exception ex )
        {
            LOGGER.info( "error occured calling client to service for save map mac id details", ex );
            if ( ex instanceof NextraCrmException )
            {
                throw ex;
            }
        }
        return crmCapDto;
    }

    private void fillRemarksPojo( CrmCapForm inCrmCapForm, String inUserId, CrmCapDto crmCapDto )
    {
        inCrmCapForm.getRemarksPojo().setCreatedBy( inUserId );
        inCrmCapForm.getRemarksPojo()
                .setMappingId( String.valueOf( inCrmCapForm.getCustomerDetailsPojo().getRecordId() ) );
        // inCrmCapForm.getRemarksPojo().setActions( inCrmCapForm.getRemarksPojo().getActions() );
        crmCapDto.setRemarksPojo( inCrmCapForm.getRemarksPojo() );
    }

    @Override
    public List<CrmParamDataPojo> getCustomerFeedBackList( CrmCapForm inCrmCapForm )
    {
        List<CrmParamDataPojo> customerFeedBackList = null;
        try
        {
            customerFeedBackList = new ArrayList<CrmParamDataPojo>();
            CrmParamDataPojo crmParamDataPojo = null;
            for ( CRMParamDataEnum crmParamData : CRMParamDataEnum.values() )
            {
                if ( StringUtils.equals( crmParamData.getCategory(), CRMParameter.CUSTOMER_FEEDBACK.getParameter() ) )
                {
                    crmParamDataPojo = new CrmParamDataPojo();
                    LOGGER.info( "Field ID:: " + crmParamData.getFieldId() + " Field Value:: "
                            + crmParamData.getFieldValue() + "FieldOption ::" + crmParamData.getFieldOption() );
                    String splitArray[] = crmParamData.getFieldOption().split( "-" );
                    for ( String feedBack : splitArray )
                    {
                        crmParamDataPojo.getDisplayValues().add( feedBack );
                    }
                    crmParamDataPojo.setParamId( crmParamData.getFieldId() );
                    crmParamDataPojo.setParamValue( crmParamData.getFieldValue() );
                    crmParamDataPojo.setParamType( crmParamData.getFieldType() );
                    crmParamDataPojo
                            .setMappingId( String.valueOf( inCrmCapForm.getCustomerDetailsPojo().getRecordId() ) );
                    crmParamDataPojo.setRequestType( CRMParameter.INA.getParameter() );
                    customerFeedBackList.add( crmParamDataPojo );
                }
            }
            LOGGER.info( "Size OF customer feedback List:::" + customerFeedBackList.size() );
        }
        catch ( Exception ex )
        {
            LOGGER.error( "error occured while geeting customer feed back list" + ex );
        }
        return customerFeedBackList;
    }

    @Override
    public List<CrmParamDataPojo> getMaterialList( CrmCapForm inCrmCapForm )
    {
        List<CrmParamDataPojo> materialList = null;
        try
        {
            materialList = new ArrayList<CrmParamDataPojo>();
            CrmParamDataPojo crmParamDataPojo = null;
            for ( CRMParamDataEnum crmParamData : CRMParamDataEnum.values() )
            {
                if ( StringUtils.equals( crmParamData.getCategory(), CRMParameter.MATERIAL_DETAILS.getParameter() ) )
                {
                    crmParamDataPojo = new CrmParamDataPojo();
                    LOGGER.info( "Field ID:: " + crmParamData.getFieldId() + " Field Value:: "
                            + crmParamData.getFieldValue() );
                    crmParamDataPojo.setParamId( crmParamData.getFieldId() );
                    crmParamDataPojo.setParamValue( crmParamData.getFieldValue() );
                    crmParamDataPojo.setParamType( crmParamData.getFieldType() );
                    crmParamDataPojo
                            .setMappingId( String.valueOf( inCrmCapForm.getCustomerDetailsPojo().getRecordId() ) );
                    crmParamDataPojo.setRequestType( CRMParameter.INA.getParameter() );
                    materialList.add( crmParamDataPojo );
                }
            }
            LOGGER.info( "Size OF material List:::" + materialList.size() );
        }
        catch ( Exception ex )
        {
            LOGGER.error( "error occured while geeting material List" + ex );
        }
        return materialList;
    }

    /**********************pdf generation code*****************/
    @Override
    public void generateISRPdf( HttpServletResponse inResponse,
                                String iconPath,
                                String checkBoxPath,
                                String checkBoxYesPath,
                                CrmCapForm form )
        throws DocumentException, IOException
    {
        LOGGER.info( "Inside ISR Pdf generated." );
        inResponse.setContentType( IAppConstants.PDF_CONTENT_TYPE );
        String fileName = StringUtils.replace( IAppConstants.INTLINE_FILE_NAME, "test", form.getCustomerDetailsPojo()
                .getCustomerId() );
        LOGGER.info( "Inline File:" + fileName );
        inResponse.setHeader( IAppConstants.CONTENT_DISPOSITION, fileName );
        Document document = new Document( PageSize.A4 );
        PdfWriter writer = PdfWriter.getInstance( document, inResponse.getOutputStream() );
        document.open();
        Image image1 = Image.getInstance( iconPath );
        image1.setAbsolutePosition( 470, 767 );
        image1.scaleToFit( 90, 100 );
        document.add( image1 );
        Font fontBold = FontFactory.getFont( IAppConstants.PDF_FONT, 12, Font.BOLD );
        Font fontSimple = FontFactory.getFont( IAppConstants.PDF_FONT, 8, Font.BOLD );
        Font fontHeading = FontFactory.getFont( IAppConstants.PDF_FONT, 8, Font.NORMAL );
        Paragraph paragraph = new Paragraph( "RI Networks Pvt. Ltd.", fontBold );
        paragraph.setAlignment( Element.ALIGN_CENTER );
        document.add( new Phrase( IAppConstants.NEW_LINE ) );
        document.add( paragraph );
        Paragraph paragraph2 = new Paragraph( "Plot No. 758, Udyog Vihar Phase-5, Gurgaon-122018, India.\n\n",
                                              fontSimple );
        paragraph2.setAlignment( Element.ALIGN_CENTER );
        document.add( paragraph2 );
        //add Single row table
        PdfPTable table = new PdfPTable( 1 );
        table.setWidthPercentage( 100 );
        Phrase phrase = new Phrase( "Installation Satisfaction Report" );
        phrase.setFont( fontBold );
        PdfPCell c1 = new PdfPCell( phrase );
        c1.setFixedHeight( 20 );
        c1.setHorizontalAlignment( Element.ALIGN_CENTER );
        //c1.setBackgroundColor( BaseColor.GRAY );
        //c1.setBorderColor( BaseColor.GRAY );
        table.addCell( c1 );
        document.add( table );
        //add heading
        Paragraph paragraph3 = new Paragraph( "General Information", fontSimple );
        paragraph3.setAlignment( Element.ALIGN_LEFT );
        document.add( paragraph3 );
        //add line
        LineSeparator line = new LineSeparator( 1, 100, null, Element.ALIGN_CENTER, 10 );
        Paragraph lineParagraph = new Paragraph();
        lineParagraph.add( line );
        document.add( lineParagraph );
        document.add( new Paragraph( IAppConstants.NEW_LINE ) );
        //add first table for General Information with 4 columns
        String[] lableArray =
        { "CAF No.:", "Plan Name:", "CAF Entry Date:", "Add-on Plan Name:", "Work Order No.:", "RI Customer ID:",
                "Work Order Date: ", "Install. Partner:", "SR Name:", "ISR Reference No.:", "Sales BP Name:", };
        String[] valueArray = generalInformationArray( form );
        /*{ "RA000552", "FiberBolt 5", "25-Jan-203 18:09", " ", "0", "2000882", "25-Jan-2013 18:53", " ", "SANJEEV 01",
                " ", "Radius" };*/
        createTable( 4, fontHeading, document, lableArray, valueArray );
        //add heading
        Paragraph paragraph4 = new Paragraph( "Installation Site Details", fontSimple );
        paragraph4.setAlignment( Element.ALIGN_LEFT );
        document.add( paragraph4 );
        //add line
        LineSeparator line1 = new LineSeparator( 1, 100, null, Element.ALIGN_CENTER, 10 );
        Paragraph lineParagraph1 = new Paragraph();
        lineParagraph1.add( line1 );
        document.add( lineParagraph1 );
        document.add( new Paragraph( IAppConstants.NEW_LINE ) );
        //
        String[] lableArraySite =
        { "Customer Name :", "CPE Status:", "Address Line 1:", "CPE MAC ID:", "Address Line 2:",
                "Email ID Verification:", "Address Line 3:", "RMN:", "Contact No.:", "Email ID:" };
        String[] valueArraySite = installationSiteInformationArray( form );
        createTable( 4, fontHeading, document, lableArraySite, valueArraySite );
        //add heading
        Paragraph paragraph5 = new Paragraph( "Material Details (To be filled by Engineer)", fontSimple );
        paragraph5.setAlignment( Element.ALIGN_LEFT );
        document.add( paragraph5 );
        //add line
        LineSeparator line2 = new LineSeparator( 1, 100, null, Element.ALIGN_CENTER, 10 );
        Paragraph lineParagraph2 = new Paragraph();
        lineParagraph2.add( line2 );
        document.add( lineParagraph2 );
        document.add( new Paragraph( IAppConstants.NEW_LINE ) );
        String[] lableArrayMaterial =
        { "CAT5 Cable Used (meters):", "RJ45 Cable Used (meters):", "Nail Hooks (nos.):", "Patch Cord (nos.):" };
        String[] valueArrayMaterial = materialListInformation( form );
        createTable( 4, fontHeading, document, lableArrayMaterial, valueArrayMaterial );
        //add heading
        Paragraph paragraph6 = new Paragraph( "Engineer Declaration (To be filled by Engineer)", fontSimple );
        paragraph6.setAlignment( Element.ALIGN_LEFT );
        document.add( paragraph6 );
        //add line
        LineSeparator line3 = new LineSeparator( 1, 100, null, Element.ALIGN_CENTER, 10 );
        Paragraph lineParagraph3 = new Paragraph();
        lineParagraph3.add( line3 );
        document.add( lineParagraph2 );
        document.add( new Paragraph( IAppConstants.NEW_LINE ) );
        //add heading
        Paragraph paragraph7 = new Paragraph( "Installation has been done at the customer premises and all the details mentioned above are correct to the best of my knowledge",
                                              fontHeading );
        paragraph7.setAlignment( Element.ALIGN_LEFT );
        document.add( paragraph7 );
        document.add( new Paragraph( IAppConstants.NEW_LINE ) );
        //add heading
        Paragraph date = new Paragraph( "Date:"
                                                + "                                                                                                                   "
                                                + "Engineer Signature:",
                                        fontHeading );
        date.setAlignment( Element.ALIGN_LEFT );
        document.add( date );
        document.add( new Paragraph( IAppConstants.NEW_LINE ) );
        //add heading
        Paragraph paragraph8 = new Paragraph( "Customer Feedback (To be filled by customer)", fontSimple );
        paragraph8.setAlignment( Element.ALIGN_LEFT );
        document.add( paragraph8 );
        //add line
        LineSeparator line4 = new LineSeparator( 1, 100, null, Element.ALIGN_CENTER, 10 );
        Paragraph lineParagraph4 = new Paragraph();
        lineParagraph4.add( line4 );
        document.add( lineParagraph4 );
        document.add( new Paragraph( IAppConstants.NEW_LINE ) );
        createCheckBoxTable( 2, 5, fontHeading, document, writer, checkBoxPath, checkBoxYesPath, form );
        Paragraph paragraph9 = new Paragraph( "Any Other Customer Comment/Feedback (To be filled by customer))",
                                              fontSimple );
        paragraph9.setAlignment( Element.ALIGN_LEFT );
        document.add( paragraph9 );
        //add line
        LineSeparator line5 = new LineSeparator( 1, 100, null, Element.ALIGN_CENTER, 10 );
        Paragraph lineParagraph5 = new Paragraph();
        lineParagraph5.add( line5 );
        document.add( lineParagraph5 );
        document.add( new Paragraph( IAppConstants.NEW_LINE ) );
        PdfPTable blankTable = new PdfPTable( 1 );
        blankTable.setWidthPercentage( 100 );
        PdfPCell blankCell = new PdfPCell();
        blankCell.setFixedHeight( 25 );
        blankCell.setBorderWidth( 1 );
        blankCell.setHorizontalAlignment( Element.ALIGN_CENTER );
        blankCell.setBackgroundColor( BaseColor.WHITE );
        blankCell.setBorderColor( BaseColor.BLACK );
        blankTable.addCell( blankCell );
        document.add( blankTable );
        Paragraph paragraph10 = new Paragraph( "Customer Declaration (To be filled by Customer)", fontSimple );
        paragraph10.setAlignment( Element.ALIGN_LEFT );
        document.add( paragraph10 );
        //add line
        LineSeparator line6 = new LineSeparator( 1, 100, null, Element.ALIGN_CENTER, 10 );
        Paragraph lineParagraph6 = new Paragraph();
        lineParagraph6.add( line6 );
        document.add( lineParagraph6 );
        document.add( new Paragraph( IAppConstants.NEW_LINE ) );
        Paragraph paragraph11 = new Paragraph( "Satisfactory Installation has been done at my premises in my presence.",
                                               fontHeading );
        paragraph11.setAlignment( Element.ALIGN_LEFT );
        document.add( paragraph11 );
        document.add( new Phrase( IAppConstants.NEW_LINE ) );
        //add heading
        Paragraph dateSignature = new Paragraph( "Date:"
                                                         + "                                                                                                                   "
                                                         + "Customer Signature:",
                                                 fontHeading );
        dateSignature.setAlignment( Element.ALIGN_LEFT );
        document.add( dateSignature );
        document.close();
        LOGGER.info( "Pdf generated successfully." );
    }

    private String[] materialListInformation( CrmCapForm inForm )
    {
        String CAT5 = IAppConstants.UNDERSCORE, RJ45 = IAppConstants.UNDERSCORE, NH = IAppConstants.UNDERSCORE, PC = IAppConstants.UNDERSCORE;
        if ( CommonValidator.isValidCollection( inForm.getCrmParamDataPojos() ) )
        {
            for ( CrmParamDataPojo crmParamDataPojo : inForm.getCrmParamDataPojos() )
            {
                if ( StringUtils.equals( crmParamDataPojo.getParamId(), CRMParamDataEnum.CAT5.getFieldId() ) )
                {
                    CAT5 = crmParamDataPojo.getParamValue();
                }
                else if ( StringUtils.equals( crmParamDataPojo.getParamId(), CRMParamDataEnum.RJ45.getFieldId() ) )
                {
                    RJ45 = crmParamDataPojo.getParamValue();
                }
                else if ( StringUtils.equals( crmParamDataPojo.getParamId(), CRMParamDataEnum.NH.getFieldId() ) )
                {
                    NH = crmParamDataPojo.getParamValue();
                }
                else if ( StringUtils.equals( crmParamDataPojo.getParamId(), CRMParamDataEnum.PC.getFieldId() ) )
                {
                    PC = crmParamDataPojo.getParamValue();
                }
                //break;
            }
        }
        String[] valueArray =
        { CAT5, RJ45, NH, PC };
        return valueArray;
    }

    private static void createTable( int column,
                                     Font fontSimple,
                                     Document document,
                                     String[] lableArray,
                                     String[] valueArray )
        throws IOException, DocumentException
    {
        PdfPTable table1 = new PdfPTable( column );
        table1.setWidthPercentage( 100 );
        if ( lableArray.length % 2 != 0 )
        {
            lableArray = Arrays.copyOf( lableArray, lableArray.length + 1 );
            valueArray = Arrays.copyOf( valueArray, valueArray.length + 1 );
        }
        for ( int i = 0; i < lableArray.length; i++ )
        {
            table1.addCell( createLabelCell( lableArray[i], fontSimple ) );
            table1.addCell( createValueCell( valueArray[i], fontSimple ) );
        }
        document.add( table1 );
    }

    private static PdfPCell createLabelCell( String text, Font font )
    {
        PdfPCell cell = new PdfPCell( new Phrase( text, font ) );
        cell.setFixedHeight( 10 );
        cell.setBorderColor( BaseColor.WHITE );
        return cell;
    }

    // create cells
    private static PdfPCell createValueCell( String text, Font font )
    {
        PdfPCell cell = new PdfPCell( new Phrase( text, font ) );
        cell.setBorderColor( BaseColor.WHITE );
        return cell;
    }

    private void createCheckBoxTable( int column,
                                      int row,
                                      Font fontSimple,
                                      Document document,
                                      PdfWriter writer,
                                      String checkBoxPath,
                                      String checkBoxYesPath,
                                      CrmCapForm incapForm )
        throws DocumentException, IOException
    {
        String[] lable =
        { "Quality of Installation & External Wiring", "Service attitude of Installation crew",
                "Overall presentability of Installation crew",
                "Did the installation team meet you at a convenient time?",
                "Overall experience during Installation Process" };
        PdfPTable table1 = new PdfPTable( column );
        for ( int i = 0; i < row; i++ )
        {
            table1.setWidthPercentage( 100 );
            table1.addCell( createCheckBoxLabelCell( lable[i], fontSimple ) );
            table1.addCell( createCheckBoxValueCell( writer, table1, lable[i], checkBoxPath, checkBoxYesPath, incapForm ) );
        }
        document.add( table1 );
    }

    private static PdfPCell createCheckBoxLabelCell( String text, Font font )
    {
        PdfPCell cell = new PdfPCell( new Phrase( text, font ) );
        cell.setBorderColor( BaseColor.BLACK );
        return cell;
    }

    // create cells
    private PdfPCell createCheckBoxValueCell( PdfWriter writer,
                                              PdfPTable table,
                                              String lable,
                                              String checkBoxPath,
                                              String checkBoxYesPath,
                                              CrmCapForm inCapForm )
        throws DocumentException, IOException
    {
        String[] options1 =
        { "Poor", "Average", "Good", "Excellent" };
        String[] options2 =
        { "Yes", "       No", "", "" };
        PdfPCell defaultContentCell = table.getDefaultCell();
        PdfPTable nestedTable = new PdfPTable( 4 );
        nestedTable.setWidthPercentage( 100 );
        Font fontLable = FontFactory.getFont( IAppConstants.PDF_FONT, 8, Font.NORMAL );
        Image image = Image.getInstance( checkBoxPath );
        Image selectedImage = Image.getInstance( checkBoxYesPath );
        String selectValue = "";
        if ( CommonValidator.isValidCollection( inCapForm.getCrmParamDataPojos() ) )
        {
            for ( CrmParamDataPojo crmParamDataPojo : inCapForm.getCrmParamDataPojos() )
            {
                if ( StringUtils
                        .equals( crmParamDataPojo.getParamId(), CRMParamDataEnum.getFieldIdByFieldValue( lable ) ) )
                {
                    selectValue = crmParamDataPojo.getParamValue();
                    break;
                }
            }
        }
        for ( int i = 0; i < 4; i++ )
        {
            PdfPCell checkBoxCell = new PdfPCell( defaultContentCell );
            if ( lable.equals( "Did the installation team meet you at a convenient time?" )
                    || lable.equals( "Overall experience during Installation Process" ) )
            {
                Paragraph p = new Paragraph();
                if ( i < 2 )
                {
                    p.add( new Phrase( options2[i], fontLable ) );
                    p.add( new Phrase( "  " ) );
                    if ( StringUtils.equals( options2[i], selectValue ) )
                    {
                        p.add( new Chunk( selectedImage, 0, 0 ) );
                    }
                    else
                    {
                        p.add( new Chunk( image, 0, 0 ) );
                    }
                }
                checkBoxCell.addElement( p );
                checkBoxCell.setBorderWidth( 1 );
                checkBoxCell.setBorderColor( BaseColor.WHITE );
            }
            else
            {
                Paragraph p = new Paragraph();
                p.add( new Phrase( options1[i], fontLable ) );
                p.add( new Phrase( " " ) );
                if ( StringUtils.equals( options1[i], selectValue ) )
                {
                    p.add( new Chunk( selectedImage, 0, 0 ) );
                }
                else
                {
                    p.add( new Chunk( image, 0, 0 ) );
                }
                checkBoxCell.addElement( p );
                checkBoxCell.setBorderColor( BaseColor.WHITE );
            }
            nestedTable.addCell( checkBoxCell );
        }
        PdfPCell cell = new PdfPCell();
        cell.addElement( nestedTable );
        return cell;
    }

    @Override
    public String[] generalInformationArray( CrmCapForm form )
    {
        String busPartnerName = "";
        String serPartnerName = "";
        PartnerPojo busPartnerPojo = CommonUtils.getPartnerById( StringUtils.numericValue( form
                .getCustomerDetailsPojo().getBusinessPartner() + "" ) );
        PartnerPojo serPartnerPojo = CommonUtils.getPartnerById( StringUtils.numericValue( form
                .getCustomerDetailsPojo().getSpId() + "" ) );
        if ( StringUtils.isValidObj( busPartnerPojo ) )
        {
            busPartnerName = busPartnerPojo.getPartnerName();
        }
        if ( StringUtils.isValidObj( serPartnerPojo ) )
        {
            serPartnerName = serPartnerPojo.getPartnerName();
        }
        RolesPojo rolesPojo = new RolesPojo();
        String salerRepresentative = form.getCustomerDetailsPojo().getSalesRepresentative();
        if ( StringUtils.isValidObj( form.getCustomerDetailsPojo().getSalesUser() ) )
        {
            salerRepresentative = form.getCustomerDetailsPojo().getSalesUser().getFullName();
        }
        String[] valueArray =
        { form.getCustomerDetailsPojo().getCrfId(),
                CommonUtils.getPlanNameByPlanCode( form.getPlanDetailsPojo().getBasePlanCode() ),
                form.getCustomerDetailsPojo().getDisplayCrfDate(),
                CommonUtils.getPlanNameByPlanCode( form.getPlanDetailsPojo().getAddOnPlanCode() ), "-",
                form.getCustomerDetailsPojo().getCustomerId(),
                DateUtils.convertXmlCalToString( form.getCustomerDetailsPojo().getFtApprovalDate() ), serPartnerName,
                salerRepresentative, form.getCustomerDetailsPojo().getIsrReferenceNo(), busPartnerName };
        return valueArray;
    }

    @Override
    public String[] installationSiteInformationArray( CrmCapForm form )
    {
        RolesPojo rolesPojo = new RolesPojo();
        // String[] cityState = StringUtils.split( form.getInstallationAddressPojo().getAddLine3(), IAppConstants.COMMA );
        LOGGER.info( "mac id is : " + form.getCrmNetworkConfigurations().getCurrentCpeMacId() );
        String[] valueArray =
        {
                form.getCustomerDetailsPojo().getCustFname()
                        + " "
                        + ( StringUtils.isEmpty( form.getCustomerDetailsPojo().getCustLname() ) ? "" : form
                                .getCustomerDetailsPojo().getCustLname() ),
                rolesPojo.getDisplayEnum( "CPEStatus," + form.getOrderDetailsPojo().getCpeStatus() ),
                form.getInstallationAddressPojo().getAddLine1().trim(),
                form.getCrmNetworkConfigurations().getCurrentCpeMacId(),
                form.getInstallationAddressPojo().getAddLine2().trim(),
                form.getCustomerDetailsPojo().getEmailValidationFlag(),
                form.getInstallationAddressPojo().getAddLine3().trim(),
                String.valueOf( form.getCustomerDetailsPojo().getRmn() ),
                String.valueOf( form.getCustomerDetailsPojo().getCustMobileNo() ),
                form.getCustomerDetailsPojo().getCustEmailId() };
        return valueArray;
    }

    @Override
    public List<CrmPartnerNetworkConfigPojo> getMasterNameList( long inPartnerId, String inServiceName )
    {
        LOGGER.info( "Partner Id ::" + inPartnerId );
        MasterDataDto masterDataDto = new MasterDataDto();
        PartnerPojo partnerPojo = new PartnerPojo();
        List<CrmPartnerNetworkConfigPojo> crmPartnerNetworkConfigPojos = null;
        CrmPartnerNetworkConfigPojo crmPartnerNetworkConfigPojo = new CrmPartnerNetworkConfigPojo();
        try
        {
            partnerPojo.setPartnerId( inPartnerId );
            partnerPojo.setPartnerType( CRMDisplayListConstants.NETWORK_PARTNER.getCode() );
            partnerPojo.setBussinessType( inServiceName );
            masterDataDto.setPartnerPojo( partnerPojo );
            masterDataDto = getMasterDataClient().partnerOperations( ServiceParameter.MAPPING_LIST.getParameter(),
                                                                     masterDataDto );
            if ( CommonValidator.isValidCollection( masterDataDto.getCrmPartnerDetailsPojos() ) )
            {
                crmPartnerNetworkConfigPojo.setPartnerDetailsId( masterDataDto.getCrmPartnerDetailsPojos().get( 0 )
                        .getRecordId() );
                crmPartnerNetworkConfigPojo.setStatus( CRMStatusCode.ACTIVE.getStatusCode() );
                //masterDataDto.setProduct( inCrmCapForm.getCustomerDetailsPojo().getProduct() );
                masterDataDto.setCrmPartnerNetworkConfigPojo( crmPartnerNetworkConfigPojo );
                masterDataDto = getMasterDataClient().masterOperations( ServiceParameter.SEARCH.getParameter(),
                                                                        CRMParameter.OPTION82.getParameter(),
                                                                        masterDataDto );
                if ( CommonValidator.isValidCollection( masterDataDto.getCrmPartnerNetworkConfigPojos() ) )
                {
                    LOGGER.info( "Size of PartnerNetworkPojo::"
                            + masterDataDto.getCrmPartnerNetworkConfigPojos().size() );
                    crmPartnerNetworkConfigPojos = masterDataDto.getCrmPartnerNetworkConfigPojos();
                }
            }
        }
        catch ( SOAPException_Exception ex )
        {
            LOGGER.error( "error occured calling client to service getpartner Details & getmasterName:: ", ex );
        }
        return crmPartnerNetworkConfigPojos;
    }

    @Override
    public void getInboxData( CrmCapForm inCrmCapForm, String inRecordId, String inCrfStage, String inRequestType )
    {
        ConfigDto configDto = new ConfigDto();
        try
        {
            configDto.setInboxType( inRequestType );
            configDto.getStages().add( inCrfStage );
            configDto.setMappingId( inRecordId );
            configDto = getCrmConfigClient().getInbox( configDto );
        }
        catch ( SOAPException_Exception ex )
        {
            LOGGER.error( "SOAP Exception : ", ex );
        }
    }

    @Override
    public CrmCapDto saveCRFReference( CrmCustomerDetailsPojo inCrmCustomerDetailsPojo, String inUserId )
    {
        CrmCapDto crmCapDto = new CrmCapDto();
        try
        {
            if ( StringUtils.isValidObj( inCrmCustomerDetailsPojo ) && StringUtils.isNotBlank( inUserId ) )
            {
                LOGGER.info( "CAFReferenceNo ::" + inCrmCustomerDetailsPojo.getCrfReferenceNo() );
                crmCapDto.setUserId( inUserId );
                crmCapDto.setCustomerDetailsPojo( inCrmCustomerDetailsPojo );
                crmCapDto = getCapServiceClient().crmCapOperation( ServiceParameter.SAVE_CRF_REFERENCE.getParameter(),
                                                                   CRMRequestType.CAF.getRequestCode(), crmCapDto );
            }
        }
        catch ( SOAPException_Exception ex )
        {
            LOGGER.info( "error occured calling client to service for save CAF reference", ex );
        }
        return crmCapDto;
    }

    @Override
    public CrmCapDto saveISRReferenceNo( CrmCustomerDetailsPojo inCrmCustomerDetailsPojo, String inUserId )
    {
        CrmCapDto crmCapDto = new CrmCapDto();
        try
        {
            if ( StringUtils.isValidObj( inCrmCustomerDetailsPojo ) && StringUtils.isNotBlank( inUserId ) )
            {
                LOGGER.info( "ISR Reference No ::" + inCrmCustomerDetailsPojo.getIsrReferenceNo() );
                crmCapDto.setUserId( inUserId );
                crmCapDto.setCustomerDetailsPojo( inCrmCustomerDetailsPojo );
                //client calling
                crmCapDto = getCapServiceClient().crmCapOperation( ServiceParameter.SAVE_ISR_REFERENCE.getParameter(),
                                                                   CRMRequestType.CAF.getRequestCode(), crmCapDto );
            }
        }
        catch ( SOAPException_Exception ex )
        {
            LOGGER.info( "error occured calling client to service for save ISR reference No.", ex );
        }
        return crmCapDto;
    }

    @Override
    public CrmCapDto bindCPEMACId( CrmCapForm inCrmCapForm, String inUserId )
    {
        CrmCapDto crmCapDto = null;
        try
        {
            crmCapDto = new CrmCapDto();
            LOGGER.info( "CPE Mac Id ::" + inCrmCapForm.getCrmNetworkConfigurations().getCurrentCpeMacId() );
            crmCapDto.setCustomerDetailsPojo( inCrmCapForm.getCustomerDetailsPojo() );
            crmCapDto.setUserId( inUserId );
            LOGGER.info( "Forwad message:::: " + inCrmCapForm.getRemarksPojo().getActions() );
            if ( StringUtils.isEmpty( inCrmCapForm.getCrmNetworkConfigurations().getFirstCpeMacId() ) )
            {
                inCrmCapForm.getCrmNetworkConfigurations().setFirstCpeMacId( inCrmCapForm.getCrmNetworkConfigurations()
                                                                                     .getCurrentCpeMacId() );
            }
            if ( StringUtils.isEmpty( inCrmCapForm.getCrmNetworkConfigurations().getFirstSlaveMacId() ) )
            {
                inCrmCapForm.getCrmNetworkConfigurations().setFirstSlaveMacId( inCrmCapForm
                                                                                       .getCrmNetworkConfigurations()
                                                                                       .getCurrentSlaveMacId() );
            }
            inCrmCapForm.getCrmNetworkConfigurations().setCreatedBy( inUserId );
            inCrmCapForm.getCrmNetworkConfigurations().setMacBind( IAppConstants.Y );
            crmCapDto.setNetworkConfigurationsPojo( inCrmCapForm.getCrmNetworkConfigurations() );
            crmCapDto.setToStage( CRMOperationStages.SERVICE_PARTNER.getCode() );
            crmCapDto.setMacFaulty( inCrmCapForm.isMacFaulty() );
            LOGGER.info( "CAF_Id ::" + inCrmCapForm.getCustomerDetailsPojo().getCrfId() );
            crmCapDto = getCapServiceClient().crmCapOperation( ServiceParameter.FORWARD.getParameter(),
                                                               CRMRequestType.CAF.getRequestCode(), crmCapDto );
        }
        catch ( Exception ex )
        {
            LOGGER.info( "error occured calling client to service for save network details", ex );
        }
        return crmCapDto;
    }

    @Override
    public CrmCapDto saveDeviceStatus( CrmCapForm inCrmCapForm, String inUserId )
    {
        CrmCapDto crmCapDto = new CrmCapDto();
        try
        {
            LOGGER.info( "Device Status ::" + inCrmCapForm.getOrderDetailsPojo().getCpeStatus() );
            crmCapDto.setCustomerDetailsPojo( inCrmCapForm.getCustomerDetailsPojo() );
            crmCapDto.setOrderDetailsPojo( inCrmCapForm.getOrderDetailsPojo() );
            inCrmCapForm.getTelecommunicationPayment().setStatus( CRMStatusCode.ACTIVE.getStatusCode() );
            inCrmCapForm.getTelecommunicationPayment().setEntityType( CRMDisplayListConstants.TELESOLUTIONS.getCode() );
            inCrmCapForm.getTelecommunicationPayment().setPaymentId( 0 );
            crmCapDto.setPaymentDetailsPojo( inCrmCapForm.getTelecommunicationPayment() );
            crmCapDto.setNetworkConfigurationsPojo( inCrmCapForm.getCrmNetworkConfigurations() );
            crmCapDto.setUserId( inUserId );
            crmCapDto = getCapServiceClient().crmCapOperation( ServiceParameter.SAVE.getParameter(),
                                                               CRMRequestType.CAF.getRequestCode(), crmCapDto );
        }
        catch ( SOAPException_Exception ex )
        {
            LOGGER.error( "error occured calling client to service for save Device status", ex );
        }
        return crmCapDto;
    }

    @Override
    public ConfigDto getActivityLogs( String inMappingId )
    {
        ConfigDto configDto = new ConfigDto();
        CrmAuditLogPojo auditLogPojo = new CrmAuditLogPojo();
        try
        {
            LOGGER.info( "Customer Record Id ::" + inMappingId );
            auditLogPojo.setMappingId( inMappingId );
            auditLogPojo.setModule( CRMRequestType.CAF.getRequestCode() );
            configDto.setAuditLogPojo( auditLogPojo );
            configDto = getCrmConfigClient().auditLogOperation( ServiceParameter.VIEW.getParameter(), configDto );
        }
        catch ( Exception ex )
        {
            LOGGER.error( "Error occured while get activity log of CAF", ex );
        }
        return configDto;
    }

    @Override
    public CrmCapDto getCustomerBySociety( CrmCapForm inForm )
    {
        CrmCapDto crmCapDto = null;
        CrmAddressDetailsPojo addressDetailsPojo = null;
        try
        {
            crmCapDto = new CrmCapDto();
            addressDetailsPojo = new CrmAddressDetailsPojo();
            addressDetailsPojo.setInstSocietyId( inForm.getInstallationAddressPojo().getInstSocietyId() );
            crmCapDto.setAddressDetailsPojo( addressDetailsPojo );
            crmCapDto = getCapServiceClient().crmCapOperation( ServiceParameter.LIST.getParameter(),
                                                               CRMRequestType.CUSTOMER_ID.getRequestCode(), crmCapDto );
        }
        catch ( Exception ex )
        {
            LOGGER.error( "error occured calling client to service for getCustomerBySociety", ex );
        }
        return crmCapDto;
    }

    @Override
    public CrmCapDto validateCrfInCustomerDetails( String inCrfId )
    {
        CrmCapDto crmCapDto = null;
        try
        {
            crmCapDto = new CrmCapDto();
            crmCapDto.setCustomerDetailsPojo( new CrmCustomerDetailsPojo() );
            crmCapDto.getCustomerDetailsPojo().setCrfId( inCrfId );
            crmCapDto = getCapServiceClient().crmCapOperation( ServiceParameter.VIEW.getParameter(),
                                                               CrmCustomerDetailsPojo.class.getSimpleName(), crmCapDto );
        }
        catch ( Exception ex )
        {
            LOGGER.error( "error occured when validate CAF in customer details:" + ex );
        }
        return crmCapDto;
    }

    @Override
    public CrmCapDto checkCrfInLinkToCrf( String inCrfId )
    {
        CrmCapDto crmCapDto = null;
        try
        {
            crmCapDto = new CrmCapDto();
            crmCapDto.setCustomerDetailsPojo( new CrmCustomerDetailsPojo() );
            crmCapDto.getCustomerDetailsPojo().setCrfId( inCrfId );
            crmCapDto = getCapServiceClient().crmCapOperation( ServiceParameter.VIEW.getParameter(),
                                                               CRMRequestType.LINK_TO_CRF.getRequestCode(), crmCapDto );
        }
        catch ( Exception ex )
        {
            LOGGER.error( "error occured when checking caf if linkage customer details:" + ex );
        }
        return crmCapDto;
    }

    @Override
    public CrmCapDto punchISR( CrmCapForm inCrmCapForm, String inUserId )
        throws Exception
    {
        CrmCapDto crmCapDto = null;
        try
        {
            crmCapDto = new CrmCapDto();
            inCrmCapForm.getCustomerDetailsPojo().setDateOnIsr( DateUtils.changeDateFormat( inCrmCapForm
                                                                        .getDisplayISRDate() ) );
            crmCapDto.setCustomerDetailsPojo( inCrmCapForm.getCustomerDetailsPojo() );
            crmCapDto.setUserId( inUserId );
            LOGGER.info( "CAF_Id ::" + inCrmCapForm.getCustomerDetailsPojo().getCrfId() );
            if ( CommonValidator.isValidCollection( inCrmCapForm.getMaterialList() )
                    && CommonValidator.isValidCollection( inCrmCapForm.getCustomerFeedBackList() ) )
            {
                LOGGER.info( "Size of Material List ::" + inCrmCapForm.getMaterialList().size() );
                LOGGER.info( "Size of getcustomerFeedBack List ::" + inCrmCapForm.getCustomerFeedBackList().size() );
                crmCapDto.getCrmParamDataPojos().addAll( inCrmCapForm.getMaterialList() );
                crmCapDto.getCrmParamDataPojos().addAll( inCrmCapForm.getCustomerFeedBackList() );
                for ( CrmParamDataPojo crmParamData : inCrmCapForm.getCustomerFeedBackList() )
                {
                    if ( StringUtils.equals( crmParamData.getParamType(), "Textarea" ) )
                    {
                        inCrmCapForm.getRemarksPojo().setRemarks( crmParamData.getParamValue() );
                        inCrmCapForm.getRemarksPojo().setActions( CrmActionEnum.FORWARDED.getActionCode() );
                        crmCapDto.setRemarksPojo( inCrmCapForm.getRemarksPojo() );
                        break;
                    }
                }
            }
            else
            {
                throw new NextraCrmException( CRMServiceCode.CRM315.getStatusCode() );
            }
            if ( !StringUtils.isValidObj( inCrmCapForm.getCustomerDetailsPojo().getActivationDate() ) )
            {
                throw new NextraCrmException( CRMServiceCode.CRM316.getStatusCode() );
            }
            if ( StringUtils.isBlank( inCrmCapForm.getDisplayISRDate() ) )
            {
                throw new NextraCrmException( CRMServiceCode.CRM317.getStatusCode() );
            }
            Calendar ISRDate = Calendar.getInstance();
            Calendar macBindDate = Calendar.getInstance();
            macBindDate.setTime( inCrmCapForm.getCustomerDetailsPojo().getActivationDate().toGregorianCalendar()
                    .getTime() );
            DateUtils.setDayStartTime( macBindDate );
            ISRDate.setTime( IDateConstants.SDF_DD_MMM_YYYY.parse( inCrmCapForm.getDisplayISRDate() ) );
            if ( ISRDate.compareTo( macBindDate ) > 0 )
            {
                LOGGER.info( "ISR Date :: " + inCrmCapForm.getDisplayISRDate() + " is greater than MAC Bind date:: "
                        + macBindDate );
                crmCapDto.setToStage( CRMOperationStages.FT_ISR.getCode() );
            }
            else
            {
                crmCapDto.setToStage( CRMOperationStages.ON_BOARD.getCode() );
            }
            //client calling
            crmCapDto = getCapServiceClient().crmCapOperation( ServiceParameter.FORWARD.getParameter(),
                                                               CRMRequestType.CAF.getRequestCode(), crmCapDto );
        }
        catch ( Exception ex )
        {
            LOGGER.info( "error occured calling client to service for save ISR Punch", ex );
            if ( ex instanceof NextraCrmException )
            {
                throw ex;
            }
        }
        return crmCapDto;
    }

    @Override
    public ConfigDto listNPUploadedDoc( String inModule, String inMapping )
    {
        ConfigDto configDto = null;
        try
        {
            configDto = new ConfigDto();
            configDto.setRequestType( inModule );
            configDto.setMappingId( inMapping );
            configDto = getCrmConfigClient().configOperations( ServiceParameter.LIST.getParameter(),
                                                               CRMParameter.POD_FILE_UPLOAD.getParameter(), configDto );
        }
        catch ( Exception ex )
        {
            LOGGER.error( "Error occured while get listNPUploadedDoc", ex );
        }
        return configDto;
    }

    @Override
    public CrmCapDto getCAFReferenceNo( CrmCustomerDetailsPojo inCrmCustomerDetailsPojo )
    {
        CrmCapDto crmCapDto = null;
        try
        {
            crmCapDto = new CrmCapDto();
            crmCapDto.setCustomerDetailsPojo( inCrmCustomerDetailsPojo );
            crmCapDto = getCapServiceClient().crmCapOperation( ServiceParameter.LIST.getParameter(),
                                                               CrmCustomerDetailsPojo.class.getSimpleName(), crmCapDto );
        }
        catch ( SOAPException_Exception ex )
        {
            LOGGER.error( "exception occure while getting caf reference no" + ex );
        }
        return crmCapDto;
    }
}
