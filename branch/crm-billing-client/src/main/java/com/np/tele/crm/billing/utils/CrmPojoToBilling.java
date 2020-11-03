package com.np.tele.crm.billing.utils;

import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.xml.soap.SOAPException;

import org.apache.log4j.Logger;

import com.microsoft.schemas._2003._10.serialization.arrays.ArrayOfstring;
import com.np.tele.crm.billing.constants.CrmBillingDataTypes;
import com.np.tele.crm.constants.CRMDisplayListConstants;
import com.np.tele.crm.constants.IAppConstants;
import com.np.tele.crm.constants.IGlobalConstants;
import com.np.tele.crm.constants.ServiceParameter;
import com.np.tele.crm.dto.MasterDataDto;
import com.np.tele.crm.locator.CRMServicesProxy;
import com.np.tele.crm.pojos.CrmRcaReasonPojo;
import com.np.tele.crm.utils.CRMUtils;
import com.np.tele.crm.utils.DateUtils;
import com.np.tele.crm.utils.PropertyUtils;
import com.np.tele.crm.utils.StringUtils;

public class CrmPojoToBilling
{
    private static final Logger                                                             LOGGER                    = Logger.getLogger( CrmPojoToBilling.class );
    private static final String                                                             ORG_DATACONTRACT          = "org.datacontract";
    private static final com.microsoft.schemas._2003._10.serialization.arrays.ObjectFactory objectFactoryStringsArray = new com.microsoft.schemas._2003._10.serialization.arrays.ObjectFactory();
    private static final org.datacontract.schemas._2004._07.ObjectFactory                   dataContractor            = new org.datacontract.schemas._2004._07.ObjectFactory();

    public static <D, S> boolean covertToBilling( D dest, S source )
    {
        return covertToBilling( dest, source, null, false );
    }

    public static <D, S> boolean covertToBilling( D dest, S source, boolean removeZeros )
    {
        return covertToBilling( dest, source, null, removeZeros );
    }

    public static <D, S> boolean covertToBilling( D dest, S source, String inExtraKey, boolean inRemoveZeros )
    {
        LOGGER.info( "Inside CrmPojoToBilling : covertToBilling( D dest, S source )" );
        boolean copyFlag = true;
        if ( StringUtils.isValidObj( source ) && StringUtils.isValidObj( dest ) )
        {
            String destClassName = dest.getClass().getSimpleName();
            String sourceClassName = source.getClass().getSimpleName();
            String sourcePropertyName = "";
            String key = "";
            String concatenatingProperty = null;
            String sameBillingProperty = null;
            String sameBillingProperty2 = null;
            String billingPojoProperty = null;
            PropertyDescriptor[] prDesc = org.apache.commons.beanutils.PropertyUtils.getPropertyDescriptors( source );
            for ( PropertyDescriptor descriptor : prDesc )
            {
                try
                {
                    concatenatingProperty = null;
                    sameBillingProperty = null;
                    sourcePropertyName = descriptor.getName();
                    key = destClassName + IAppConstants.DOT + sourceClassName + IAppConstants.DOT + sourcePropertyName;
                    if ( StringUtils.isNotBlank( inExtraKey ) )
                    {
                        key += IAppConstants.DOT + String.valueOf( inExtraKey );
                    }
                    billingPojoProperty = PropertyUtils.getBillingMappingKeyValue( key );
                    if ( StringUtils.isNotBlank( billingPojoProperty ) )
                    {
                        if ( StringUtils.contains( billingPojoProperty, IAppConstants.COMMA ) )
                        {
                            String[] strArray = StringUtils.split( billingPojoProperty, IAppConstants.COMMA );
                            billingPojoProperty = strArray[0];
                            concatenatingProperty = strArray[1];
                            if ( concatenatingProperty.contains( IAppConstants.HASH ) )
                            {
                                strArray = StringUtils.split( concatenatingProperty, IAppConstants.HASH );
                                concatenatingProperty = strArray[0];
                                sameBillingProperty = strArray[1];
                                if ( strArray.length == 3 )
                                {
                                    sameBillingProperty2 = strArray[2];
                                }
                            }
                        }
                        else if ( StringUtils.contains( billingPojoProperty, IAppConstants.HASH ) )
                        {
                            String[] strArray = StringUtils.split( billingPojoProperty, IAppConstants.HASH );
                            billingPojoProperty = strArray[0];
                            sameBillingProperty = strArray[1];
                            if ( strArray.length == 3 )
                            {
                                sameBillingProperty2 = strArray[2];
                            }
                            LOGGER.info( "Same Billing property: " + sameBillingProperty );
                        }
                        setDestinationPropertyValue( dest, source, destClassName, sourcePropertyName, key,
                                                     concatenatingProperty, billingPojoProperty, inRemoveZeros );
                        if ( StringUtils.isNotBlank( sameBillingProperty ) )
                        {
                            key += IAppConstants.DOT + sameBillingProperty;
                            LOGGER.info( "Key for sameBillingProperty: " + key );
                            setDestinationPropertyValue( dest, source, destClassName, sourcePropertyName, key,
                                                         concatenatingProperty, sameBillingProperty, inRemoveZeros );
                        }
                        if ( StringUtils.isNotBlank( sameBillingProperty2 ) )
                        {
                            key += IAppConstants.DOT + sameBillingProperty2;
                            LOGGER.info( "Key for sameBillingProperty: " + key );
                            setDestinationPropertyValue( dest, source, destClassName, sourcePropertyName, key,
                                                         concatenatingProperty, sameBillingProperty2, inRemoveZeros );
                        }
                    }
                }
                catch ( SecurityException ex )
                {
                    copyFlag = false;
                    LOGGER.error( "Security Exception while converstion to Billing POJO ", ex );
                }
                catch ( NoSuchFieldException ex )
                {
                    copyFlag = false;
                    LOGGER.error( "No Such Field (" + billingPojoProperty
                            + ") Exception while converstion to Billing POJO ", ex );
                }
                catch ( IllegalAccessException ex )
                {
                    copyFlag = false;
                    LOGGER.error( "Illegal Access Exception while converstion to Billing POJO ", ex );
                }
                catch ( InvocationTargetException ex )
                {
                    copyFlag = false;
                    LOGGER.error( "Invocation Target Exception while converstion to Billing POJO ", ex );
                }
                catch ( NoSuchMethodException ex )
                {
                    copyFlag = false;
                    LOGGER.error( "No Such Method Exception while converstion to Billing POJO ", ex );
                }
            }
        }
        return copyFlag;
    }

    private static <D, S> void setDestinationPropertyValue( D dest,
                                                            S source,
                                                            String destClassName,
                                                            String sourcePropertyName,
                                                            String key,
                                                            String concatenatingProperty,
                                                            String billingPojoProperty,
                                                            boolean inRemoveZeros )
        throws IllegalAccessException, InvocationTargetException, NoSuchMethodException, NoSuchFieldException
    {
        Object value = org.apache.commons.beanutils.PropertyUtils.getProperty( source, sourcePropertyName );
        if ( StringUtils.isValidObj( value ) )
        {
            if ( inRemoveZeros && StringUtils.isNumeric( value + "" ) && StringUtils.numericValue( value + "" ) == 0 )
            {
                return;
            }
            String destinationPropertyType = dest.getClass()
                    .getDeclaredField( StringUtils.trimToEmpty( billingPojoProperty ) ).getGenericType().toString();
            CrmBillingDataTypes dataType = CrmBillingDataTypes.getBillingDataType( destinationPropertyType );
            String possibleValues = PropertyUtils.getBillingMappingKeyValue( key + ".values" );
            if ( StringUtils.contains( possibleValues, "?" ) )
            {
                String[] values = StringUtils.split( possibleValues, "?" );
                possibleValues = values[1];
                if ( StringUtils.contains( values[0] + "", source.getClass().getSimpleName() + IAppConstants.DOT ) )
                {
                    String property = StringUtils.substring( values[0] + "",
                                                             source.getClass().getSimpleName().length() + 1 );
                    value = org.apache.commons.beanutils.PropertyUtils.getProperty( source, property );
                }
            }
            Map<String, String> possibleValueMap = getPossibleValueMap( possibleValues );
            if ( StringUtils.isValidObj( possibleValueMap ) && !possibleValueMap.isEmpty() )
            {
                value = possibleValueMap.get( String.valueOf( value ) );
                if ( StringUtils.contains( value + "", source.getClass().getSimpleName() + IAppConstants.DOT ) )
                {
                    String property = StringUtils
                            .substring( value + "", source.getClass().getSimpleName().length() + 1 );
                    value = org.apache.commons.beanutils.PropertyUtils.getProperty( source, property );
                }
            }
            else if ( StringUtils.isNotBlank( possibleValues ) )
            {
                if ( StringUtils.contains( possibleValues, CRMDisplayListConstants.class.getSimpleName() ) )
                {
                    String type = StringUtils.substring( possibleValues, CRMDisplayListConstants.class.getSimpleName()
                            .length() + 1 );
                    value = CRMDisplayListConstants.getValueByCode( type, String.valueOf( value ) );
                }
                else if ( StringUtils.contains( possibleValues, CRMUtils.class.getSimpleName() ) )
                {
                    String type = StringUtils.substring( possibleValues, CRMUtils.class.getSimpleName().length() + 1 );
                    value = CRMUtils.getEnumValue( type, String.valueOf( value ) );
                }
                else if ( StringUtils.contains( possibleValues, CrmRcaReasonPojo.class.getSimpleName() )
                        && StringUtils.isNumeric( value + "" ) )
                {
                    CrmRcaReasonPojo rcaReason = new CrmRcaReasonPojo();
                    rcaReason.setCategoryId( StringUtils.numericValue( value + "" ) );
                    MasterDataDto dataDto = new MasterDataDto();
                    dataDto.setCrmRcaReason( rcaReason );
                    try
                    {
                        dataDto = CRMServicesProxy
                                .getInstance()
                                .getCRMMasterDataService( IGlobalConstants.REMOTE, IGlobalConstants.APP )
                                .masterOperations( ServiceParameter.VIEW.getParameter(),
                                                   CrmRcaReasonPojo.class.getSimpleName(), dataDto );
                    }
                    catch ( SOAPException ex )
                    {
                        LOGGER.error( "Unable to retrieve Master Data for Category ID:" + value );
                    }
                    if ( StringUtils.isValidObj( dataDto.getCrmRcaReason() )
                            && StringUtils.isNotBlank( dataDto.getCrmRcaReason().getCategoryValue() ) )
                    {
                        value = dataDto.getCrmRcaReason().getCategoryValue();
                    }
                }
                else if ( StringUtils.contains( possibleValues, "Append" ) )
                {
                    String type = StringUtils.substring( possibleValues, "Append".length() + 1 );
                    value = value + type;
                }
            }
            LOGGER.info( "Property: " + billingPojoProperty + " Value:" + value );
            if ( StringUtils.isValidObj( value ) )
            {
                Object valueToSet = null;
                Method meth = null;
                switch ( dataType )
                {
                    case JAXB_STRING:
                        meth = getBillingDestinationMethod( source, destClassName, concatenatingProperty,
                                                            billingPojoProperty, String.class );
                        if ( StringUtils.isNotBlank( concatenatingProperty ) )
                        {
                            Object concateValue = org.apache.commons.beanutils.PropertyUtils
                                    .getProperty( source, concatenatingProperty );
                            if ( StringUtils.isValidObj( concateValue ) )
                            {
                                value = value + " " + concateValue;
                            }
                        }
                        valueToSet = meth.invoke( dataContractor, String.valueOf( value ) );
                        break;
                    case JAXB_BIGDECIMAL:
                        meth = getBillingDestinationMethod( source, destClassName, concatenatingProperty,
                                                            billingPojoProperty, BigDecimal.class );
                        valueToSet = meth.invoke( dataContractor, (BigDecimal) value );
                        break;
                    case XMLGREGORIANCALENDAR:
                        valueToSet = DateUtils.toXMLGregorianCalendar( (Date) value );
                        break;
                    case JAXB_STRING_ARRAY:
                        if ( StringUtils.equalsIgnoreCase( billingPojoProperty, "addonPlanCode" )
                                && StringUtils.isBlank( String.valueOf( value ) ) )
                        {
                            break;
                        }
                        ArrayOfstring arrayStr = new ArrayOfstring();
                        arrayStr.getString().add( String.valueOf( value ) );
                        valueToSet = objectFactoryStringsArray.createArrayOfstring( arrayStr );
                        meth = getBillingDestinationMethod( source, destClassName, concatenatingProperty,
                                                            billingPojoProperty, ArrayOfstring.class );
                        valueToSet = meth.invoke( dataContractor, arrayStr );
                        break;
                    case JAXB_BILLING_ADDRESS:
                    case JAXB_INSTALLATION_ADDRESS:
                    case JAXB_CONTACT_ADDRESS:
                        break;
                    case INTEGER:
                        valueToSet = new Integer( String.valueOf( value ) );
                        break;
                    case DOUBLE:
                        valueToSet = new Double( String.valueOf( value ) );
                        break;
                    case LONG:
                        valueToSet = new Long( String.valueOf( value ) );
                        break;
                    case BIGDECIMAL:
                        valueToSet = new BigDecimal( String.valueOf( value ) );
                        break;
                    default:
                        valueToSet = value;
                        break;
                }
                if ( StringUtils.isValidObj( valueToSet ) )
                {
                    org.apache.commons.beanutils.PropertyUtils
                            .setSimpleProperty( dest, billingPojoProperty, valueToSet );
                }
            }
        }
    }

    private static <S> Method getBillingDestinationMethod( S source,
                                                           String destClassName,
                                                           String concatenatingProperty,
                                                           String billingPojoProperty,
                                                           Class inClass )
        throws NoSuchMethodException, IllegalAccessException, InvocationTargetException
    {
        LOGGER.info( "Inside CrmPojoToBilling, getBillingDestinationMethod" );
        LOGGER.info( "Billing Property: " + billingPojoProperty );
        Method meth = null;
        try
        {
            meth = dataContractor.getClass()
                    .getDeclaredMethod( "create" + destClassName + StringUtils.capitalize( billingPojoProperty ),
                                        inClass );
        }
        catch ( NoSuchMethodException ex )
        {
            try
            {
                meth = dataContractor
                        .getClass()
                        .getDeclaredMethod( "create"
                                                    + destClassName
                                                    + StringUtils.capitalize( StringUtils.replace( billingPojoProperty,
                                                                                                   StringUtils
                                                                                                           .safeSubString( billingPojoProperty,
                                                                                                                           1,
                                                                                                                           2 ),
                                                                                                   StringUtils
                                                                                                           .upperCase( StringUtils
                                                                                                                   .safeSubString( billingPojoProperty,
                                                                                                                                   1,
                                                                                                                                   2 ) ),
                                                                                                   1 ) ), inClass );
            }
            catch ( NoSuchMethodException ex2 )
            {
                String str = billingPojoProperty.substring( 0, 1 );
                meth = dataContractor.getClass()
                        .getDeclaredMethod( "create" + destClassName + StringUtils.capitalize( str )
                                                    + billingPojoProperty.substring( 1, billingPojoProperty.length() ),
                                            inClass );
            }
        }
        LOGGER.info( "Billing Method: " + meth.toString() );
        return meth;
    }

    public static Map<String, String> getPossibleValueMap( String inPossibleValues )
    {
        Map<String, String> possibleValueMap = null;
        if ( StringUtils.isNotBlank( inPossibleValues ) )
        {
            if ( StringUtils.contains( inPossibleValues, IAppConstants.COMMA ) )
            {
                possibleValueMap = new HashMap<String, String>();
                String[] values = StringUtils.split( inPossibleValues, IAppConstants.COMMA );
                for ( int i = 0; i < values.length; i++ )
                {
                    if ( StringUtils.contains( values[i], IAppConstants.COLON ) )
                    {
                        String[] finalValues = StringUtils.split( values[i], IAppConstants.COLON );
                        if ( StringUtils.contains( finalValues[0], IAppConstants.HASH ) )
                        {
                            String[] commonKeys = StringUtils.split( values[i], IAppConstants.HASH );
                            for ( String key : commonKeys )
                            {
                                if ( !StringUtils.equals( finalValues[1], "null" ) )
                                {
                                    possibleValueMap.put( key, finalValues[1] );
                                }
                            }
                        }
                        else
                        {
                            possibleValueMap.put( finalValues[0], finalValues[1] );
                        }
                    }
                }
            }
        }
        return possibleValueMap;
    }
    //    private static void getFields( Set<Field> inFieldsSet, Class<?> inClass )
    //    {
    //        Field[] fields = null;
    //        if ( StringUtils.startsWith( inClass.getName(), ORG_DATACONTRACT ) && StringUtils.isValidObj( inFieldsSet ) )
    //        {
    //            fields = inClass.getDeclaredFields();
    //            inFieldsSet.addAll( Arrays.asList( fields ) );
    //            Class<?> superClass = inClass.getSuperclass();
    //            getFields( inFieldsSet, superClass );
    //        }
    //    }
    //    @SuppressWarnings("unchecked")
    //    public static <E> String convertToString( E e )
    //    {
    //        LOGGER.info( "Inside CrmPojoToBilling : convertToString( E e )" );
    //        String str = null;
    //        StringBuilder builder = null;
    //        String propertyName = null;
    //        String propertyType = null;
    //        CrmBillingDataTypes dataType = null;
    //        try
    //        {
    //            if ( StringUtils.isValidObj( e ) )
    //            {
    //                Class<?> srcClass = e.getClass();
    //                Set<Field> srcFieldsSet = new HashSet<Field>();
    //                getFields( srcFieldsSet, srcClass );
    //                builder = new StringBuilder();
    //                builder.append( e.getClass().getSimpleName() ).append( " [" );
    //                for ( Field field : srcFieldsSet )
    //                {
    //                    propertyName = field.getName();
    //                    Object value = null;
    //                    if ( !StringUtils.equals( "automaticnotification", propertyName ) )
    //                    {
    //                        value = org.apache.commons.beanutils.PropertyUtils.getProperty( e, propertyName );
    //                    }
    //                    if ( StringUtils.isValidObj( value ) )
    //                    {
    //                        propertyType = field.getGenericType().toString();
    //                        dataType = CrmBillingDataTypes.getBillingDataType( propertyType );
    //                        if ( !StringUtils.isValidObj( dataType ) )
    //                        {
    //                            LOGGER.info( "Datatype not available for " + propertyName );
    //                        }
    //                        builder.append( propertyName ).append( IAppConstants.EQUAL );
    //                        switch ( dataType )
    //                        {
    //                            case JAXB_STRING:
    //                                JAXBElement<String> element = (JAXBElement<String>) value;
    //                                builder.append( element.getValue() );
    //                                break;
    //                            case JAXB_STRING_ARRAY:
    //                                JAXBElement<ArrayOfstring> arrayElement = (JAXBElement<ArrayOfstring>) value;
    //                                builder.append( arrayElement.getValue() );
    //                                break;
    //                            case JAXB_BILLING_ADDRESS:
    //                                JAXBElement<BillingAddress> billingAddress = (JAXBElement<BillingAddress>) value;
    //                                builder.append( convertToString( billingAddress.getValue() ) );
    //                                break;
    //                            case JAXB_INSTALLATION_ADDRESS:
    //                                JAXBElement<InstallationAddress> installationAddress = (JAXBElement<InstallationAddress>) value;
    //                                builder.append( convertToString( installationAddress.getValue() ) );
    //                                break;
    //                            case JAXB_CONTACT_ADDRESS:
    //                                JAXBElement<LocalContactAddress> localContactAddress = (JAXBElement<LocalContactAddress>) value;
    //                                builder.append( convertToString( localContactAddress.getValue() ) );
    //                                break;
    //                            case BOOLEAN:
    //                                break;
    //                            default:
    //                                builder.append( value );
    //                                break;
    //                        }
    //                        builder.append( IAppConstants.COMMA ).append( IAppConstants.SPACE );
    //                    }
    //                }
    //                str = builder.substring( 0, builder.length() - 2 ) + " ]";
    //            }
    //        }
    //        catch ( SecurityException ex )
    //        {
    //            LOGGER.error( "Security Exception while creating POJO string ", ex );
    //        }
    //        catch ( IllegalAccessException ex )
    //        {
    //            LOGGER.error( "Illegal Access Exception while creating POJO string ", ex );
    //        }
    //        catch ( InvocationTargetException ex )
    //        {
    //            LOGGER.error( "Invocation Target Exception while creating POJO string ", ex );
    //        }
    //        catch ( NoSuchMethodException ex )
    //        {
    //            LOGGER.error( "No Such Method Exception while creating POJO string ", ex );
    //        }
    //        return str;
    //    }
    //    public static void main( String[] args )
    //    {
    //        String possibleValues = "CRMDisplayListConstants.ConnectionType";
    //        String billingPojoProperty = "pgTransId";
    //        String type = StringUtils.capitalize( StringUtils.replace( billingPojoProperty, StringUtils
    //                .safeSubString( billingPojoProperty, 1, 2 ), StringUtils.upperCase( StringUtils
    //                .safeSubString( billingPojoProperty, 1, 2 ) ), 1 ) );
    //        System.out.println( type );
    //    }
}
