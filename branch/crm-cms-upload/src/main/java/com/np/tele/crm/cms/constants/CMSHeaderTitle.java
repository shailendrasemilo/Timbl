package com.np.tele.crm.cms.constants;

import com.np.tele.crm.constants.CRMStatusCode;
import com.np.tele.crm.utils.StringUtils;

public enum CMSHeaderTitle {
    // Deposit file header constants
    CMS_DEPOSIT_0(0,"CMS_" + CRMStatusCode.DEPOSIT.getStatusCode(),"Record Type",0,"","",false),
    CMS_DEPOSIT_1(1,"CMS_" + CRMStatusCode.DEPOSIT.getStatusCode(),"Internal Dep No.",20,"internalDepNo","String",false),
    CMS_DEPOSIT_2(2,"CMS_" + CRMStatusCode.DEPOSIT.getStatusCode(),"Deposit Slip No",45,"depositSlipNo","String",false),
    CMS_DEPOSIT_3(3,"CMS_" + CRMStatusCode.DEPOSIT.getStatusCode(),"Deposit Remarks",256,"depositRemarks","String",false),
    CMS_DEPOSIT_4(4,"CMS_" + CRMStatusCode.DEPOSIT.getStatusCode(),"Deposit Date",0,"depositDate","XMLGregorianCalendar",false),
    CMS_DEPOSIT_5(5,"CMS_" + CRMStatusCode.DEPOSIT.getStatusCode(),"Total Amount",0,"totalAmount","Double",false),
    CMS_DEPOSIT_6(6,"CMS_" + CRMStatusCode.DEPOSIT.getStatusCode(),"Total Instruments",4,"totalInstruments","Integer",false),
    CMS_DEPOSIT_7(7,"CMS_" + CRMStatusCode.DEPOSIT.getStatusCode(),"Pickup Date",0,"pickupDate","XMLGregorianCalendar",false),
    CMS_DEPOSIT_8(8,"CMS_" + CRMStatusCode.DEPOSIT.getStatusCode(),"Pickup Location",0,"","",false),
    CMS_DEPOSIT_9(9,"CMS_" + CRMStatusCode.DEPOSIT.getStatusCode(),"Pickup Point",0,"","",false),
    CMS_DEPOSIT_10(10,"CMS_" + CRMStatusCode.DEPOSIT.getStatusCode(),"Arrangement Code",0,"","",false),
    CMS_DEPOSIT_11(11,"CMS_" + CRMStatusCode.DEPOSIT.getStatusCode(),"Instrument No.",6,"instrumentNo","String",true),
    CMS_DEPOSIT_12(12,"CMS_" + CRMStatusCode.DEPOSIT.getStatusCode(),"Instrument Date",0,"instrumentDate","XMLGregorianCalendar",false),
    CMS_DEPOSIT_13(13,"CMS_" + CRMStatusCode.DEPOSIT.getStatusCode(),"Instrument Amount",0,"instrumentAmount","Double",false),
    CMS_DEPOSIT_14(14,"CMS_" + CRMStatusCode.DEPOSIT.getStatusCode(),"Clearing Location",0,"","",false),
    CMS_DEPOSIT_15(15,"CMS_" + CRMStatusCode.DEPOSIT.getStatusCode(),"Drawer Name",128,"drawerName","String",false),
    CMS_DEPOSIT_16(16,"CMS_" + CRMStatusCode.DEPOSIT.getStatusCode(),"Drawee Bank",0,"draweeBank","String",false),
    CMS_DEPOSIT_17(17,"CMS_" + CRMStatusCode.DEPOSIT.getStatusCode(),"Drawee Branch",128,"draweeBranch","String",false),
    CMS_DEPOSIT_18(18,"CMS_" + CRMStatusCode.DEPOSIT.getStatusCode(),"Product Code",0,"","",false),
    CMS_DEPOSIT_19(19,"CMS_" + CRMStatusCode.DEPOSIT.getStatusCode(),"IE1",128,"ie1","String",false),
    CMS_DEPOSIT_20(20,"CMS_" + CRMStatusCode.DEPOSIT.getStatusCode(),"IE2",0,"ie2","String",false),
    // Clearance file header constants
    CMS_CLEARANCE_0(0,"CMS_" + CRMStatusCode.CLEARANCE.getStatusCode(),"band_id",2,"bandId","String",false),
    CMS_CLEARANCE_1(1,"CMS_" + CRMStatusCode.CLEARANCE.getStatusCode(),"entry_id",20,"entryId","String",false),
    CMS_CLEARANCE_2(2,"CMS_" + CRMStatusCode.CLEARANCE.getStatusCode(),"type_of_enrty",2,"typeOfEntry","String",false),
    CMS_CLEARANCE_3(3,"CMS_" + CRMStatusCode.CLEARANCE.getStatusCode(),"debit_credit_flag",0,"debitCreditFlag","String",false),
    CMS_CLEARANCE_4(4,"CMS_" + CRMStatusCode.CLEARANCE.getStatusCode(),"entry_amount",0,"entryAmount","Double",false),
    CMS_CLEARANCE_5(5,"CMS_" + CRMStatusCode.CLEARANCE.getStatusCode(),"value_date",0,"valueDate","XMLGregorianCalendar",false),
    CMS_CLEARANCE_6(6,"CMS_" + CRMStatusCode.CLEARANCE.getStatusCode(),"posting_date",0,"postingDate","XMLGregorianCalendar",false),
    CMS_CLEARANCE_7(7,"CMS_" + CRMStatusCode.CLEARANCE.getStatusCode(),"product_code",0,"","",false),
    CMS_CLEARANCE_8(8,"CMS_" + CRMStatusCode.CLEARANCE.getStatusCode(),"pickup_location",0,"","",false),
    CMS_CLEARANCE_9(9,"CMS_" + CRMStatusCode.CLEARANCE.getStatusCode(),"pickup_point",0,"","",false),
    CMS_CLEARANCE_10(10,"CMS_" + CRMStatusCode.CLEARANCE.getStatusCode(),"pickup_date",0,"pickupDate","XMLGregorianCalendar",false),
    CMS_CLEARANCE_11(11,"CMS_" + CRMStatusCode.CLEARANCE.getStatusCode(),"deposit_slip_number",45,"depositSlipNo","String",false),
    CMS_CLEARANCE_12(12,"CMS_" + CRMStatusCode.CLEARANCE.getStatusCode(),"deposit_date",0,"depositDate","XMLGregorianCalendar",false),
    CMS_CLEARANCE_13(13,"CMS_" + CRMStatusCode.CLEARANCE.getStatusCode(),"deposit_amount",0,"depositAmount","Double",false),
    CMS_CLEARANCE_14(14,"CMS_" + CRMStatusCode.CLEARANCE.getStatusCode(),"number_of_instruments",4,"totalInstruments","Integer",false),
    CMS_CLEARANCE_15(15,"CMS_" + CRMStatusCode.CLEARANCE.getStatusCode(),"deposit_remarks",256,"depositRemarks","String",false),
    CMS_CLEARANCE_16(16,"CMS_" + CRMStatusCode.CLEARANCE.getStatusCode(),"d1",0,"","",false),
    CMS_CLEARANCE_17(17,"CMS_" + CRMStatusCode.CLEARANCE.getStatusCode(),"instrument_number",6,"instrumentNo","String",true),
    CMS_CLEARANCE_18(18,"CMS_" + CRMStatusCode.CLEARANCE.getStatusCode(),"drawee_bank",0,"draweeBank","String",false),
    CMS_CLEARANCE_19(19,"CMS_" + CRMStatusCode.CLEARANCE.getStatusCode(),"clearing_location",0,"","",false),
    CMS_CLEARANCE_20(20,"CMS_" + CRMStatusCode.CLEARANCE.getStatusCode(),"instrument_amount",0,"instrumentAmount","Double",false),
    CMS_CLEARANCE_21(21,"CMS_" + CRMStatusCode.CLEARANCE.getStatusCode(),"instrument_date",0,"instrumentDate","XMLGregorianCalendar",false),
    CMS_CLEARANCE_22(22,"CMS_" + CRMStatusCode.CLEARANCE.getStatusCode(),"drawer_name",128,"drawerName","String",false),
    CMS_CLEARANCE_23(23,"CMS_" + CRMStatusCode.CLEARANCE.getStatusCode(),"e1",128,"ie1","String",false),
    CMS_CLEARANCE_24(24,"CMS_" + CRMStatusCode.CLEARANCE.getStatusCode(),"e2",0,"ie2","String",false),
    CMS_CLEARANCE_25(25,"CMS_" + CRMStatusCode.CLEARANCE.getStatusCode(),"return_reason_remarks",256,"returnReason","String",false);

    private int     index;
    private String  upLoadType;
    private String  title;
    private int     size;
    private String  property;
    private String  propertyType;
    private boolean prefix;

    private CMSHeaderTitle( int inIndex,
                            String inUploadType,
                            String inTitle,
                            int inSize,
                            String inProperty,
                            String inPropertyType,
                            boolean inPrefix )
    {
        index = inIndex;
        upLoadType = inUploadType;
        title = inTitle;
        size = inSize;
        property = inProperty;
        propertyType = inPropertyType;
        prefix = inPrefix;
    }

    /**
     * @return the index
     */
    public int getIndex()
    {
        return index;
    }

    /**
     * @return the upLoadType
     */
    public String getUpLoadType()
    {
        return upLoadType;
    }

    /**
     * @return the title
     */
    public String getTitle()
    {
        return title;
    }

    /**
     * @return the size
     */
    public int getSize()
    {
        return size;
    }

    

    /**
     * @return the property
     */
    public String getProperty()
    {
        return property;
    }

    /**
     * @return the propertyType
     */
    public String getPropertyType()
    {
        return propertyType;
    }

    /**
     * @return the prefix
     */
    public boolean isPrefix()
    {
        return prefix;
    }
    public static CMSHeaderTitle getCMSHeaderByTypeValue( String inType, String inTitle )
    {
        CMSHeaderTitle cmsTitle = null;
        for ( CMSHeaderTitle cmsHeaderTitle : CMSHeaderTitle.values() )
        {
            if ( StringUtils.equals( cmsHeaderTitle.getUpLoadType(), "CMS_" + inType )
                    && StringUtils.equals( StringUtils.trimToEmpty( cmsHeaderTitle.getTitle() ),
                                           StringUtils.trimToEmpty( inTitle ) ) )
            {
                cmsTitle = cmsHeaderTitle;
                break;
            }
        }
        return cmsTitle;
    }
}
