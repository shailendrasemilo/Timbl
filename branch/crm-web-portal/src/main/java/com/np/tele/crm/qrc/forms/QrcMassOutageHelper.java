package com.np.tele.crm.qrc.forms;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;

import javax.xml.datatype.XMLGregorianCalendar;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;

import com.np.tele.crm.common.utils.ValidationUtil;
import com.np.tele.crm.constants.CRMDisplayListConstants;
import com.np.tele.crm.constants.IAppConstants;
import com.np.tele.crm.constants.IDateConstants;
import com.np.tele.crm.constants.IPropertiesConstant;
import com.np.tele.crm.service.client.AreaPojo;
import com.np.tele.crm.service.client.CityPojo;
import com.np.tele.crm.service.client.CrmMassOutageAreaPojo;
import com.np.tele.crm.service.client.CrmMassOutageCityPojo;
import com.np.tele.crm.service.client.CrmMassOutageLocalityPojo;
import com.np.tele.crm.service.client.CrmMassOutageMastersPojo;
import com.np.tele.crm.service.client.CrmMassOutagePartnerPojo;
import com.np.tele.crm.service.client.CrmMassOutagePojo;
import com.np.tele.crm.service.client.CrmMassOutageSocietyPojo;
import com.np.tele.crm.service.client.CrmMassOutageStatePojo;
import com.np.tele.crm.service.client.CrmPartnerNetworkConfigPojo;
import com.np.tele.crm.service.client.LocalityPojo;
import com.np.tele.crm.service.client.PartnerPojo;
import com.np.tele.crm.service.client.SocietyPojo;
import com.np.tele.crm.service.client.StatePojo;
import com.np.tele.crm.utils.DateUtils;
import com.np.tele.crm.utils.ICRMValidationCriteriaUtil;
import com.np.tele.crm.utils.StringUtils;
import com.np.validator.util.CommonValidator;
import com.np.validator.util.ValidationPojoUtil;

public class QrcMassOutageHelper
{
    private static final Logger LOGGER = Logger.getLogger( QrcMassOutageHelper.class );

    /*public static void validate( QrcMassOutageForm inMassOutageForm, String inMethod, ActionMessages inErrors )
    {
        LOGGER.info( "in validate :: " + inMethod );
    }*/

    public static void reset( QrcMassOutageForm inMassOutageForm, String method )
    {
        if ( StringUtils.equals( method, IAppConstants.METHOD_SEARCH_MASS_OUTAGE_PAGE ) )
        {
            inMassOutageForm.setMassOutagePojos( new ArrayList<CrmMassOutagePojo>() );
        }
        else if ( StringUtils.equals( method, IAppConstants.METHOD_SEARCH_MASS_OUTAGE ) )
        {
            inMassOutageForm.setMassOutagePojo( new CrmMassOutagePojo() );
            //            if ( StringUtils.isValidObj( inMassOutageForm.getMassOutagePojo() ) )
            //            {
            //                inMassOutageForm.getMassOutagePojo().setOutageId( 0 );
            //                inMassOutageForm.getMassOutagePojo().setServiceName( "" );
            //                inMassOutageForm.getMassOutagePojo().setOutageType( "" );
            //                inMassOutageForm.getMassOutagePojo().setOutageStartTime( null );
            //                inMassOutageForm.getMassOutagePojo().setOutageEtrTime( null );
            //                inMassOutageForm.getMassOutagePojo().setResolutionTime( null );
            //                inMassOutageForm.getMassOutagePojo().setRemarks( "" );
            //                inMassOutageForm.getMassOutagePojo().setCreatedBy( "" );
            //                inMassOutageForm.getMassOutagePojo().setCreatedTime( null );
            //                inMassOutageForm.getMassOutagePojo().setLastModifiedBy( "" );
            //                inMassOutageForm.getMassOutagePojo().setLastModifiedTime( null );
            //                inMassOutageForm.getMassOutagePojo().setStatus( "" );
            //                inMassOutageForm.getMassOutagePojo().setOutageLevel( "" );
            //            }
        }
        else if ( StringUtils.equals( method, IAppConstants.METHOD_ADD_MASSOUTAGE_PAGE )
                || StringUtils.equals( method, IAppConstants.METHOD_ADD_MASSOUTAGE ) )
        {
            LOGGER.info( "in method :::: " + method );
            if ( StringUtils.equals( method, IAppConstants.METHOD_ADD_MASSOUTAGE_PAGE ) )
                inMassOutageForm.setMassOutagePojo( null );
            if ( CommonValidator.isValidCollection( inMassOutageForm.getNetworkPartnersEoc() ) )
            {
                for ( PartnerPojo partnerPojo : inMassOutageForm.getNetworkPartnersEoc() )
                {
                    for ( CrmPartnerNetworkConfigPojo partnerNetworkConfigPojo : partnerPojo
                            .getPartnerNetworkConfigPojos() )
                    {
                        partnerNetworkConfigPojo.setEditable( false );
                    }
                    partnerPojo.setEditable( false );
                }
            }
            if ( CommonValidator.isValidCollection( inMassOutageForm.getNetworkPartnersRf() ) )
            {
                for ( PartnerPojo partnerPojo : inMassOutageForm.getNetworkPartnersRf() )
                {
                    for ( CrmPartnerNetworkConfigPojo partnerNetworkConfigPojo : partnerPojo
                            .getPartnerNetworkConfigPojos() )
                    {
                        partnerNetworkConfigPojo.setEditable( false );
                    }
                    partnerPojo.setEditable( false );
                }
            }
            if ( CommonValidator.isValidCollection( inMassOutageForm.getNetworkPartnersFbr() ) )
            {
                for ( PartnerPojo partnerPojo : inMassOutageForm.getNetworkPartnersFbr() )
                {
                    for ( StatePojo statePojo : partnerPojo.getStatePojos() )
                    {
                        for ( CityPojo cityPojo : statePojo.getCities() )
                        {
                            for ( AreaPojo areaPojo : cityPojo.getAreas() )
                            {
                                for ( SocietyPojo societyPojo : areaPojo.getSocieties() )
                                {
                                    societyPojo.setEditable( false );
                                }
                                areaPojo.setEditable( false );
                            }
                            cityPojo.setEditable( false );
                        }
                        statePojo.setEditable( false );
                    }
                    partnerPojo.setEditable( false );
                }
            }
        }
        else if ( StringUtils.equals( method, IAppConstants.METHOD_UPDATE_MASSOUTAGE ) )
        {
            CrmMassOutagePojo massOutagePojo = inMassOutageForm.getMassOutagePojo();
            if ( StringUtils.isValidObj( massOutagePojo ) )
            {
                for ( CrmMassOutagePartnerPojo outagePartnerPojo : massOutagePojo.getCrmMassOutagePartners() )
                {
                    if ( StringUtils.equals( massOutagePojo.getServiceName(),
                                             CRMDisplayListConstants.ETHERNET_ON_CABLE.getCode() )
                            || StringUtils.equals( massOutagePojo.getServiceName(),
                                                   CRMDisplayListConstants.RADIO_FREQUENCY.getCode() ) )
                    {
                        for ( CrmMassOutageMastersPojo outageMastersPojo : outagePartnerPojo
                                .getCrmMassOutageMasterses() )
                        {
                            outageMastersPojo.setEnabled( false );
                        }
                        outagePartnerPojo.setEnabled( false );
                    }
                    else
                    {
                        for ( CrmMassOutageStatePojo outageStatePojo : outagePartnerPojo.getCrmMassOutageStates() )
                        {
                            for ( CrmMassOutageCityPojo outageCityPojo : outageStatePojo.getCrmMassOutageCities() )
                            {
                                for ( CrmMassOutageAreaPojo outageAreaPojo : outageCityPojo.getCrmMassOutageAreas() )
                                {
                                    for ( CrmMassOutageSocietyPojo outageSocietyPojo : outageAreaPojo
                                            .getCrmMassOutageSocietyPojos() )
                                    {
                                        LOGGER.info( outageSocietyPojo.getSociety() + " >> "
                                                + outageSocietyPojo.isEnabled() );
                                        outageSocietyPojo.setEnabled( false );
                                    }
                                    outageAreaPojo.setEnabled( false );
                                }
                                outageCityPojo.setEnabled( false );
                            }
                            outageStatePojo.setEnabled( false );
                        }
                        outagePartnerPojo.setEnabled( false );
                    }
                }
            }
        }
    }

    public static void validateAddMassOutage( QrcMassOutageForm inMassOutageForm, ActionMessages inErrors )
    {
        LOGGER.info( "in validateAddMassOutage" );
        CrmMassOutagePojo massOutagePojo = inMassOutageForm.getMassOutagePojo();
        Map<String, Object[]> resultMap = ValidationPojoUtil
                .validateForm( massOutagePojo, ICRMValidationCriteriaUtil.FORM_QRC_ADD_MASSOUTAGE, false );
        if ( StringUtils.isValidObj( resultMap ) )
        {
            ValidationUtil.prepareErrorMessage( inErrors, resultMap );
        }
        XMLGregorianCalendar outageStartTime = DateUtils.changeDateFormatWithTime( massOutagePojo
                .getDisplayOutageStartTime() );
        XMLGregorianCalendar outageEtrTime = DateUtils.changeDateFormatWithTime( massOutagePojo
                .getDisplayOutageEtrTime() );
        if ( StringUtils.equals( massOutagePojo.getOutageType(), "Planned" )
                && !StringUtils.isValidObj( outageStartTime ) )
        {
            inErrors.add( IAppConstants.APP_ERROR,
                          new ActionMessage( IPropertiesConstant.ERROR_INVALID_OUTAGE_START_TIME ) );
        }
        else if ( !StringUtils.isValidObj( outageEtrTime ) )
        {
            inErrors.add( IAppConstants.APP_ERROR,
                          new ActionMessage( IPropertiesConstant.ERROR_INVALID_OUTAGE_ETR_TIME ) );
        }
        else if ( !validETRTime( massOutagePojo ) )
        {
            inErrors.add( IAppConstants.APP_ERROR,
                          new ActionMessage( IPropertiesConstant.ERROR_INVALID_OUTAGE_ETR_TIME ) );
        }
        else
        {
            massOutagePojo.setOutageStartTime( outageStartTime );
            massOutagePojo.setOutageEtrTime( outageEtrTime );
        }
    }

    public static boolean validETRTime( CrmMassOutagePojo inMassOutagePojo )
    {
        Date start;
        Date end;
        try
        {
            if ( StringUtils.equals( inMassOutagePojo.getOutageType(), "Planned" ) )
            {
                start = IDateConstants.SDF_DD_MMM_YYYY_HH_MM_SS.parse( inMassOutagePojo.getDisplayOutageStartTime() );
            }
            else
            {
                start = Calendar.getInstance().getTime();
            }
            end = IDateConstants.SDF_DD_MMM_YYYY_HH_MM_SS.parse( inMassOutagePojo.getDisplayOutageEtrTime() );
            return end.compareTo( start ) == 1;
        }
        catch ( ParseException ex )
        {
            LOGGER.info( "unable to validate etr time", ex );
        }
        return false;
    }
}
