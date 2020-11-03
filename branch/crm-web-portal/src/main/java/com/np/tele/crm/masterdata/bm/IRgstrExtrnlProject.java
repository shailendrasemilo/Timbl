package com.np.tele.crm.masterdata.bm;

import java.util.List;

import com.np.tele.crm.masterdata.forms.CrmProjectForm;
import com.np.tele.crm.service.client.MasterDataDto;

public interface IRgstrExtrnlProject
{
    public MasterDataDto rgstrExtrnlProject( CrmProjectForm inForm, String inString );

    public MasterDataDto searchRgstrExtrnlProject( CrmProjectForm inProjectForm );

    public List<String> getParameterType();

    public MasterDataDto modifyExtrProject( CrmProjectForm inProjectForm, String inUserId );

    public MasterDataDto changeExtrnalProjectStatus( CrmProjectForm inProjectForm , String inUserId );
}
