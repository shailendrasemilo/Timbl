package com.np.tele.crm.service.comparators;

import java.util.Comparator;

import org.apache.log4j.Logger;

import com.np.tele.crm.service.client.SmsTemplatePojo;
import com.np.tele.crm.utils.StringUtils;

public class SMSPojoComparator
    implements Comparator<SmsTemplatePojo>
{
    private static final Logger LOGGER = Logger.getLogger( SMSPojoComparator.class );

    @Override
    public int compare( SmsTemplatePojo inExist, SmsTemplatePojo inNew )
    {
        LOGGER.info( "Inside : SMSPojoComparator.compare" );
        if ( !StringUtils.equals( inExist.getSmsTemplateName(), inNew.getSmsTemplateName() ) )
        {
            return -1;
        }
        if ( !StringUtils.equals( inExist.getSmsGateway(), inNew.getSmsGateway() ) )
        {
            return -1;
        }
        if ( !StringUtils.equals( inExist.getSmsType(), inNew.getSmsType() ) )
        {
            return -1;
        }
        String existBody = new String( inExist.getSmsTemplate() );
        String newBody = new String( inNew.getSmsTemplate() );
        if ( !StringUtils.equals( existBody, newBody ) )
        {
            return -1;
        }
        if ( !StringUtils.equals( inExist.getStatus(), inNew.getStatus() ) )
        {
            return -1;
        }
        if ( inExist.getProjects().getProjectId() != inNew.getProjects().getProjectId() )
        {
            return -1;
        }
        return 0;
    }
}
