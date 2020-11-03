package com.np.tele.crm.constants;

public enum  CRMOperationStages {
    INITIATE(0,"Initiate", "Initiate"),
    SALES_COORDINATOR(1,"SC", "Sales Coordinator"),
    FULFILLMENT_TEAM(2,"FT", "Fulfilment Team"),
    AREA_MANAGER(3,"AM", "Area Manager"), 
    SALES(4,"ST","Sales"), 
    CLUSTER_HEAD(5,"CLHD","Cluster Head"),
    CLOSE(6,"CL", "Closed"), 
    MATURE(7,"ML","Matured"),
    NETWORK_PARTNER(8,"NP","Network Partner"),
    FT_REJECT(9,"FTR","FT Rejected"),
    CUSTOMER_CARE(10,"CC","CUSTOMER CARE"),
    SERVICE_PARTNER(11,"SP","Service Partner"),
    NP_REJECT(12,"NPR","NP Rejected"),
    SP_REJECT_NP(13,"SPNP","SP Rejected To NP"),
    SP_REJECT_BP(14,"SPBP","SP Rejected To Sales"),
    INITIATE_QRC(15,"InitQRC","INIT_QRC"),
    FREEZE(16,"FZ","Freeze"),
    CSD_BILLING(17,"CSDB","CSD Billing"),
    CHURN(18,"CH","Churn"),
    CUSTOMER_SERVICE(19,"CS","Customer Service"),
    FINANCE(20,"FI","Finance"),
    I_AND_FR(21,"IFR","I&FR"),
    IT_BILLING(22,"ITB","IT Billing"),
    MARKETING(23,"MT","Marketing"),
    NETWORK_TEAM(24,"NET","Network Team"),
    COLLECTION(26,"COLL","Collection"),
    CSD_CHURN(27,"CSDC","CSD Churn"),
    CSD_OUTCALL(28,"CSDO","CSD Outcall"),
    EOC_FIRST_RECHARGE(29,"EOCF","EOC First Recharge"),
    EOC_NETWORK(30,"EOCN","EOC Network"),
    EOC_RETENTION(31,"EOCR","EOC Retention"),
    EOC_SERVICE(32,"EOCS","EOC Service"),
    NEXTRA_NOC(33,"NNOC","NOC"),
    NP_REJECT_SALES(34,"NPBP","NP Rejected To Sales"),
    ON_BOARD(35,"OB","Customer Onboard"),
    FT_ISR(9,"FTSR","ISR Date Approval"),
    IFR_AD(36,"IFRAD"," IFR Address Confirmation"),
    CANCEL(37,"CN","CAF Canceled"),
    ISR_PUNCH(38,"ISR","ISR PUNCH"),
    EOC_RESOLVED_BIN(39,"EOCRB","EOC RESOLVED BIN"),
    FT_LEVEL1(40,"FTL1", "Fulfilment Team LEVEL1"),
    CSD_LEVEL2(42,"CSDL2","CSD LEVEL2"),
    IFR_EOC_LEVEL1(43,"IFREOCL1","IFR EOC Team LEVEL1"),
    CSD_LEVEL3(44,"CSDL3","CSD LEVEL3"),
    NOC_LEVEL1(46,"NOCL1","NOC LEVEL1 1"),
    TIMBL_NOC(47,"TNOC","TIMBL NOC LEVEL1 1"),
    IFR_EOC_LEVEL2(48,"IFREOCL2","IFR EOC Team LEVEL2")
    ;
    
    int    index = 0;
    String code  = null;
    String desc  = null;

    private CRMOperationStages( int inIndex, String code, String desc )
    {
        this.index = inIndex;
        this.code = code;
        this.desc = desc;
    }

    public int getIndex()
    {
        return index;
    }

    public String getCode()
    {
        return code;
    }

    public String getDesc()
    {
        return desc;
    }
}
