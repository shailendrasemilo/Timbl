package com.np.tele.crm.constants;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.np.tele.crm.utils.StringUtils;

public enum CRMFunctionalBinStages {
    SALES_COORDINATOR("SALES COORDINATOR", CRMOperationStages.SALES_COORDINATOR.getCode(), ",SC,"), 
    FULFILLMENT_TEAM("FULFILLMENT TEAM", CRMOperationStages.FULFILLMENT_TEAM.getCode(), ",FT,NPR,FTSR,FTL2,FTL1"), 
    AREA_MANAGER("AREA MANAGER",CRMOperationStages.AREA_MANAGER.getCode(), ",AM,AMEOCL1"),
    SALES("SALES", CRMOperationStages.SALES.getCode(), ",Initiate,ST,FTR,NPBP,SPBP,FZ,"), 
    CLUSTER_HEAD("CLUSTER HEAD", CRMOperationStages.CLUSTER_HEAD.getCode(), ",CLHD,"), 
    NETWORK_PARTNER("NETWORK PARTNER",CRMOperationStages.NETWORK_PARTNER.getCode(), ",NP,SPNP,NPL1,"), 
    SERVICE_PARTNER("SERVICE PARTNER",CRMOperationStages.SERVICE_PARTNER.getCode(), ",SP,"), 
    CUSTOMER_CARE("CUSTOMER CARE",CRMOperationStages.CUSTOMER_CARE.getCode(), ",CC,InitQRC,"), 
    CSD_BILLING("CSD BILLING",CRMOperationStages.CSD_BILLING.getCode(), ",CSDB,"), 
    CHURN("CHURN", CRMOperationStages.CHURN.getCode(),",CH,"), 
    CUSTOMER_SERVICE("CUSTOMER SERVICE", CRMOperationStages.CUSTOMER_SERVICE.getCode(), ",CS,"), 
    FINANCE("FINANCE", CRMOperationStages.FINANCE.getCode(), ",FI,"), 
    I_AND_FR("IFR", CRMOperationStages.I_AND_FR.getCode(), ",IFR,IFRAD,IFREOCL1,IFREOCL2"),
    IT_BILLING("IT BILLING", CRMOperationStages.IT_BILLING.getCode(), ",ITB,"), 
    MARKETING("MARKETING", CRMOperationStages.MARKETING.getCode(), ",MT,"), 
    NETWORK_TEAM("NETWORK TEAM",CRMOperationStages.NETWORK_TEAM.getCode(), ",NET,"), 
    COLLECTION("COLLECTION", CRMOperationStages.COLLECTION.getCode(), ",COLL,"), 
    CSD_CHURN("CSD CHURN", CRMOperationStages.CSD_CHURN.getCode(), ",CSDC,"), 
    CSD_OUTCALL("CSD OUTCALL", CRMOperationStages.CSD_OUTCALL.getCode(), ",CSDO,CSDL2,CSDL3"), 
    EOC_FIRST_RECHARGE("EOC FIRST RECHARGE", CRMOperationStages.EOC_FIRST_RECHARGE.getCode(), ",EOCF,"), 
    EOC_NETWORK("EOC NETWORK", CRMOperationStages.EOC_NETWORK.getCode(), ",EOCN,IFREOCL1,AMEOCL1,IFREOCL2"), 
    EOC_RETENTION("EOC RETENTION",CRMOperationStages.EOC_RETENTION.getCode(), ",EOCR,"), 
    EOC_SERVICE("EOC SERVICE",CRMOperationStages.EOC_SERVICE.getCode(), ",EOCS,"), 
    NEXTRA_NOC("NOC",CRMOperationStages.NEXTRA_NOC.getCode(), ",NNOC,"), 
    ISR_PUNCH("ISR PUNCH",CRMOperationStages.ISR_PUNCH.getCode(), ",ISR,"),
    EOC_RESOLVED_BIN("EOC RESOLVED BIN",CRMOperationStages.EOC_RESOLVED_BIN.getCode(), ",EOCRB,"),
    TIMBL_NOC("TIMBL NOC",CRMOperationStages.TIMBL_NOC.getCode(), ",TNOC,NOCL1,"),
    ;
    String functionalBin = null;
    String stage         = null;
    String subStages     = null;

    private CRMFunctionalBinStages( String functionalBin, String inStage, String inSubStages )
    {
        this.functionalBin = functionalBin;
        this.stage = inStage;
        this.subStages = inSubStages;
    }

    public String getStage()
    {
        return stage;
    }

    public String getSubStages()
    {
        return subStages;
    }

    public String getFunctionalBin()
    {
        return functionalBin;
    }

    public static List<String> getStagesBySubStage( String subStage )
    {
        List<String> stages = new ArrayList<String>();
        for ( CRMFunctionalBinStages binStages : CRMFunctionalBinStages.values() )
        {
            if ( StringUtils.contains( binStages.getSubStages(), "," + subStage + "," ) )
            {
                stages.addAll( Arrays.asList( StringUtils.split( binStages.getSubStages(), "," ) ) );
            }
        }
        return stages;
    }

    public static String getStageBySubStage( String subStage )
    {
        for ( CRMFunctionalBinStages binStages : CRMFunctionalBinStages.values() )
        {
            if ( StringUtils.contains( binStages.getSubStages(), "," + subStage + "," ) )
            {
                return binStages.getStage();
            }
        }
        return null;
    }
    public static CRMFunctionalBinStages getBinByStage( String stage )
    {
        for ( CRMFunctionalBinStages crmFunctionalBinStages : CRMFunctionalBinStages.values() )
        {
            if ( StringUtils.equals( crmFunctionalBinStages.getStage(), stage ) )
            {
                return crmFunctionalBinStages;
            }
        }
        return null;
    }
}
