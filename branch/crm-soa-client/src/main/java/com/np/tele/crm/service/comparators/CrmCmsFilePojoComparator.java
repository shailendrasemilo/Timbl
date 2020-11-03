package com.np.tele.crm.service.comparators;

import java.util.Comparator;

import com.np.tele.crm.service.client.CrmCmsFilePojo;

public class CrmCmsFilePojoComparator
    implements Comparator<CrmCmsFilePojo>
{
    @Override
    public int compare( CrmCmsFilePojo inO1, CrmCmsFilePojo inO2 )
    {
        return -1 * ( inO1.getCmsFileType().compareTo( inO2.getCmsFileType() ) );
    }
}
