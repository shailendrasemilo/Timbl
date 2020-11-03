package com.np.tele.crm.gis.businessmgr;

import com.np.tele.crm.dto.GISDto;

public interface IGISOperationMgr
{
    GISDto gisOperation( String inServiceParam, String inOperationParam, GISDto inGisDto );

    public GISDto countryOperation( GISDto inGisDto );

    public GISDto stateOperation( GISDto inGisDto );

    public GISDto cityOperation( GISDto inGisDto );

    public GISDto areaOperation( GISDto inGisDto );
}
