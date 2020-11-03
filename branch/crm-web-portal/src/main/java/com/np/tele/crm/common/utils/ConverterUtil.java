package com.np.tele.crm.common.utils;

import java.math.BigDecimal;

import org.apache.log4j.Logger;

public class ConverterUtil
{
    private static Logger     logger        = Logger.getLogger( ConverterUtil.class );
    private static BigDecimal KBtoBytes     = new BigDecimal( 1024 );
    private static BigDecimal MBtoBytes     = new BigDecimal( 1024 * 1024 );
    private static BigDecimal GBtoBytes     = new BigDecimal( 1024 * 1024L * 1024 );
    private static BigDecimal BytestoKBytes = BigDecimal.ONE.divide( KBtoBytes );
    private static BigDecimal MBtoKBytes    = new BigDecimal( 1024 );
    private static BigDecimal GBtoKBytes    = new BigDecimal( 1024 * 1024 );
    private static BigDecimal BytestoMBytes = BigDecimal.ONE.divide( MBtoBytes );
    private static BigDecimal KBtoMBytes    = BigDecimal.ONE.divide( MBtoKBytes );
    private static BigDecimal GBtoMBytes    = new BigDecimal( 1024 );
    private static BigDecimal BytestoGBytes = BigDecimal.ONE.divide( GBtoBytes );
    private static BigDecimal KBtoGBytes    = BigDecimal.ONE.divide( GBtoKBytes );
    private static BigDecimal MBtoGBytes    = BigDecimal.ONE.divide( GBtoMBytes );

    public static long memoryUnitConvertor( BigDecimal value, MemorySizeUnit sourceUnit, MemorySizeUnit targetUnit )
    {
        return memoryUnitConvertorBD( value, sourceUnit, targetUnit ).longValue();
    }

    public static double memoryUnitConvertorDouble( BigDecimal value,
                                                    MemorySizeUnit sourceUnit,
                                                    MemorySizeUnit targetUnit )
    {
        return memoryUnitConvertorBD( value, sourceUnit, targetUnit ).doubleValue();
    }

    public static BigDecimal memoryUnitConvertorBD( BigDecimal value,
                                                    MemorySizeUnit sourceUnit,
                                                    MemorySizeUnit targetUnit )
    {
        BigDecimal returnValue = null;
        switch ( targetUnit )
        {
            case BYTE:
                if ( sourceUnit.equals( MemorySizeUnit.BYTE ) )
                {
                    returnValue = value.setScale( 2, BigDecimal.ROUND_HALF_UP );
                }
                else if ( sourceUnit.equals( MemorySizeUnit.KILOBYTE ) )
                {
                    returnValue = value.multiply( KBtoBytes ).setScale( 2, BigDecimal.ROUND_HALF_UP );
                }
                else if ( sourceUnit.equals( MemorySizeUnit.MEGABYTE ) )
                {
                    returnValue = value.multiply( MBtoBytes ).setScale( 2, BigDecimal.ROUND_HALF_UP );
                }
                else if ( sourceUnit.equals( MemorySizeUnit.GIGABYTE ) )
                {
                    returnValue = value.multiply( GBtoBytes ).setScale( 2, BigDecimal.ROUND_HALF_UP );
                }
                break;
            case KILOBYTE:
                if ( sourceUnit.equals( MemorySizeUnit.BYTE ) )
                {
                    returnValue = value.multiply( BytestoKBytes ).setScale( 2, BigDecimal.ROUND_HALF_UP );
                }
                else if ( sourceUnit.equals( MemorySizeUnit.KILOBYTE ) )
                {
                    returnValue = value.setScale( 2, BigDecimal.ROUND_HALF_UP );
                }
                else if ( sourceUnit.equals( MemorySizeUnit.MEGABYTE ) )
                {
                    returnValue = value.multiply( MBtoKBytes ).setScale( 2, BigDecimal.ROUND_HALF_UP );
                }
                else if ( sourceUnit.equals( MemorySizeUnit.GIGABYTE ) )
                {
                    returnValue = value.multiply( GBtoKBytes ).setScale( 2, BigDecimal.ROUND_HALF_UP );
                }
                break;
            case MEGABYTE:
                if ( sourceUnit.equals( MemorySizeUnit.BYTE ) )
                {
                    returnValue = value.multiply( BytestoMBytes ).setScale( 2, BigDecimal.ROUND_HALF_UP );
                }
                else if ( sourceUnit.equals( MemorySizeUnit.KILOBYTE ) )
                {
                    returnValue = value.multiply( KBtoMBytes ).setScale( 2, BigDecimal.ROUND_HALF_UP );
                }
                else if ( sourceUnit.equals( MemorySizeUnit.MEGABYTE ) )
                {
                    returnValue = value.setScale( 2, BigDecimal.ROUND_HALF_UP );
                }
                else if ( sourceUnit.equals( MemorySizeUnit.GIGABYTE ) )
                {
                    returnValue = value.multiply( GBtoMBytes ).setScale( 2, BigDecimal.ROUND_HALF_UP );
                }
                break;
            case GIGABYTE:
                if ( sourceUnit.equals( MemorySizeUnit.BYTE ) )
                {
                    returnValue = value.multiply( BytestoGBytes ).setScale( 2, BigDecimal.ROUND_HALF_UP );
                }
                else if ( sourceUnit.equals( MemorySizeUnit.KILOBYTE ) )
                {
                    returnValue = value.multiply( KBtoGBytes ).setScale( 2, BigDecimal.ROUND_HALF_UP );
                }
                else if ( sourceUnit.equals( MemorySizeUnit.MEGABYTE ) )
                {
                    returnValue = value.multiply( MBtoGBytes ).setScale( 2, BigDecimal.ROUND_HALF_UP );
                }
                else if ( sourceUnit.equals( MemorySizeUnit.GIGABYTE ) )
                {
                    returnValue = value.setScale( 2, BigDecimal.ROUND_HALF_UP );
                }
                break;
            default:
                logger.error( "Unsupported conversion from " + sourceUnit + " to " + targetUnit );
                returnValue = BigDecimal.ONE.negate();
        }
        if ( logger.isDebugEnabled() )
        {
            logger.debug( "Converted " + value + " from " + sourceUnit + " to " + targetUnit + " : "
                    + returnValue.longValue() );
        }
        return returnValue;
    }

    public static void main( String args[] )
    {
        System.out.println( "---"
                + memoryUnitConvertor( new BigDecimal( 500 ), MemorySizeUnit.MEGABYTE, MemorySizeUnit.BYTE ) );
        System.out.println( memoryUnitConvertorBD( new BigDecimal( 1073741 ), MemorySizeUnit.BYTE,
                                                   MemorySizeUnit.GIGABYTE ) );
        System.out.println( Long.MAX_VALUE );
    }
}
