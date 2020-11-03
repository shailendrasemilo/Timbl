package com.np.tele.crm.masterdata.dao;

import java.util.List;

import com.np.tele.crm.dto.MasterDataDto;
import com.np.tele.crm.pojos.ProjectsPojo;

public interface IExternalApplicationDao
{

    MasterDataDto registerExternalApp( MasterDataDto inMasterDataDto );

    List<ProjectsPojo> listRegisteredExternalApp( ProjectsPojo inPojo );

    boolean modifyExternalAppStatus( ProjectsPojo inPojo );

    MasterDataDto updateExternalProjectParam( MasterDataDto inMasterDataDto );

    ProjectsPojo getProject( ProjectsPojo inPojo );
}
