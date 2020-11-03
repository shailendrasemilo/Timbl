package com.np.upload.constants;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

public interface IDateConstantsDms
{
    DateFormat SDF_YYYYMMDD        = new SimpleDateFormat( "yyyyMMdd" );
    DateFormat SDF_HHMMSS          = new SimpleDateFormat( "HHmmss" );
    DateFormat SDF_YYYYMMDD_HHMMSS = new SimpleDateFormat( "yyyyMMdd_HHmmss" );
}
