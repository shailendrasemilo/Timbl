package com.np.tele.crm.service.comparators;

import java.util.Comparator;

import com.np.tele.crm.service.client.EmailTemplatePojo;
import com.np.tele.crm.utils.StringUtils;

public class EmailPojoComparator
    implements Comparator<EmailTemplatePojo>
{
    @Override
    public int compare( EmailTemplatePojo inExist, EmailTemplatePojo inNew )
    {
        if ( !StringUtils.equals( inExist.getEmailTemplateName(), inNew.getEmailTemplateName() ) )
        {
            return -1;
        }
        if ( !StringUtils.equals( inExist.getEmailSubject(), inNew.getEmailSubject() ) )
        {
            return -1;
        }
        String existBody = new String( inExist.getEmailTemplate() );
        String newBody = new String( inNew.getEmailTemplate() );
        if ( !StringUtils.equals( existBody, newBody ) )
        {
            return -1;
        }
        if ( !StringUtils.equals( inExist.getEmailServer(), inNew.getEmailServer() ) )
        {
            return -1;
        }
        if ( !StringUtils.equals( inExist.getEmailFrom(), inNew.getEmailFrom() ) )
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
        if ( !inExist.getEmailCcBccs().containsAll( inNew.getEmailCcBccs() ) )
        {
            return -1;
        }
        if ( ! ( inExist.getEmailCcBccs().size() == inNew.getEmailCcBccs().size() && inExist.getEmailCcBccs()
                .containsAll( inNew.getEmailCcBccs() ) ) )
        {
            return -1;
        }
        return 0;
    }
}
