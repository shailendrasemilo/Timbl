package com.np.tele.crm.sla.engine.constants;

import com.np.tele.crm.constants.CRMDisplayListConstants;

public enum CRMSLAConstants {
    LMS_SC_AM_ALERT(CRMDisplayListConstants.WORKING_HOURS.getCode(),2),
    LMS_SC_CH_ALERT(CRMDisplayListConstants.WORKING_HOURS.getCode(),4),
    LMS_AM_CH_ALERT(CRMDisplayListConstants.WORKING_DAYS.getCode(),24),
    LMS_BP_AM_ALERT(CRMDisplayListConstants.WORKING_DAYS.getCode(),72),
    LMS_BP_CH_ALERT(CRMDisplayListConstants.WORKING_DAYS.getCode(),120),
    LMS_BP_AM_FORWARD(CRMDisplayListConstants.WORKING_DAYS.getCode(),168),
    INA_BP_AM_ALERT(CRMDisplayListConstants.WORKING_DAYS.getCode(),48),
    INA_BP_CH_ALERT(CRMDisplayListConstants.WORKING_DAYS.getCode(),72),
    ;
    
    private String slaUnit;
    private int    slaTimeInHours;

    private CRMSLAConstants( String inSlaUnit, int inSlaTimeInHours )
    {
        slaUnit = inSlaUnit;
        slaTimeInHours = inSlaTimeInHours;
    }

    public String getSlaUnit()
    {
        return slaUnit;
    }

    public void setSlaUnit( String inSlaUnit )
    {
        slaUnit = inSlaUnit;
    }

    public int getSlaTimeInHours()
    {
        return slaTimeInHours;
    }

    public void setSlaTimeInHours( int inSlaTimeInHours )
    {
        slaTimeInHours = inSlaTimeInHours;
    }
}
