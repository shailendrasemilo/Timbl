package com.np.tele.crm.utils;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.log4j.Logger;

public class BeanUtils
{
    private static final Logger LOGGER             = Logger.getLogger( BeanUtils.class );
    public static <E> E getTemporaryPojo( E inPojo )
    {
        E tempPojo = null;
        try
        {
            tempPojo = (E) inPojo.getClass().newInstance();
            PropertyUtils.copyProperties( tempPojo, inPojo );
        }
        catch ( InstantiationException ex )
        {
            LOGGER.error( "Unable to create Temp object", ex );
        }
        catch ( IllegalAccessException ex )
        {
            LOGGER.error( "Unable to create Temp object", ex );
        }
        catch ( Exception ex )
        {
            LOGGER.error( "Unable to create Temp object", ex );
        }
        return tempPojo;
    }
}
