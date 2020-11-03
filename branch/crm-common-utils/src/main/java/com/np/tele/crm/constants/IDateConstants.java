package com.np.tele.crm.constants;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

public interface IDateConstants
{
    SimpleDateFormat SDF_DD_MMM_YYYY             = new SimpleDateFormat( "dd-MMM-yyyy" );
    String           FORMAT_DD_MMM_YYYY_HH_MM_SS = "dd-MMM-yyyy HH:mm:ss";
    String           FORMAT_DD_MMM_YYYY          = "dd-MMM-yyyy";
    SimpleDateFormat SDF_DD_MMM_YYYY_HH_MM_SS    = new SimpleDateFormat( FORMAT_DD_MMM_YYYY_HH_MM_SS );
    SimpleDateFormat SDF_HH                      = new SimpleDateFormat( "HH" );
    SimpleDateFormat SDF_YYYYMMDD_HHMMSS         = new SimpleDateFormat( "yyyyMMdd_HHmmSS" );
    SimpleDateFormat SDF_GMT                     = new SimpleDateFormat( "EEE MMM dd HH:mm:ss zzz yyyy" );
    SimpleDateFormat SDF_DD_MM_YYYY              = new SimpleDateFormat( "dd/MM/yyyy" );
    DateFormat       yyMMddHHmmSS                = new SimpleDateFormat( "yyMMddHHmmss" );
    SimpleDateFormat SDF_DD_MM_YYYY_HH_MM_SS     = new SimpleDateFormat( "dd-MM-yyyy-HH-mm-ss" );
    SimpleDateFormat SDF_HH_MM_SS    = new SimpleDateFormat( "HH:mm:ss" );
    String           SDF_FORMAT_DD_MMM_YYYY          = "dd-MM-yyyy";
}
