package com.np.tele.crm.masterdata.businessmgr;

import javax.xml.soap.SOAPException;

import com.np.tele.crm.dto.MasterDataDto;

public interface IMasterDataBusiness
{
    public MasterDataDto getMasterData( MasterDataDto inMasterDataDto, String inPojoName );

    public MasterDataDto rolesGroupOperations( MasterDataDto inMasterDataDto, String inOperation );

    public MasterDataDto parameterGroupOperations( MasterDataDto inMasterDataDto, String inOperation )
        throws SOAPException;

    public MasterDataDto masterOperation( String inServiceParam, String inMasterType, MasterDataDto inMasterDataDto )
        throws SOAPException;
}
