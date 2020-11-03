package com.np.tele.crm.constants;

public enum FileHeaderConstants {
    GIS_SHEET("GIS_Sheet","GIS File Upload",0),
    GIS0("GIS","PRIMARY PIN CODES",0),
    GIS1("GIS","SECONDARY PIN CODES",0),
    GIS2("GIS","STATE",0),
    GIS3("GIS","CITY",0),
    GIS4("GIS","AREA",0),
    GIS5("GIS","LOCALITY/SECTOR",0),
    GIS6("GIS","SOCIETY",0),
    GIS7("GIS","NETWORK PARTNER",0),
    GIS8("GIS","NETWORK TYPE",0),
    GIS9("GIS","CONNECTABLE HOMES",0),
    GIS10("GIS","RFS DU's",0),
    GIS11("GIS","LONGITUDE",0),
    GIS12("GIS","LATITUDE",0),
    GIS13("GIS","CUSTOMER CATEGORY",0),
    GIS14("GIS","Type Of Field",0),
//    GIS15("GIS","Landmark",0),
//    GIS16("GIS","City",0),
//    GIS17("GIS","Pincode",0),
//    GIS18("GIS","Latitude",0),
//    GIS19("GIS","Longitude",0),
//    GIS20("GIS","FTTH / FTTB",0),
//    GIS21("GIS","Customer Category",0),
    LMS_SHEET("LMS_Sheet","LMS_Fields_Leads",0),
    LMS0("LMS","Sr. Number",0),
    LMS1("LMS","Customer Name",50),
    LMS2("LMS","Contact Number",10),
    LMS3("LMS","State",30),
    LMS4("LMS","City",30),
    LMS5("LMS","Area",30),
    LMS6("LMS","Locality / Sector - Society",60),
    //LMS7("LMS","Society",35),
    LMS8("LMS","House No",10),
    LMS9("LMS","Land Mark",25),
    LMS10("LMS","Service/Product",0),
    LMS11("LMS","Remarks",4000),
    LMS13("LMS","Pincode",6),
    LMS14("LMS","Referral Source",127),
    LMS12("_LMS","Customer Email",256),
    UPFRONT_SHEET("Upfront_Sheet","Upfront_Payments",0),
    UPFRONT0(CRMParameter.UPFRONTFILE.getParameter(),"Sr. No.",0),
    UPFRONT1(CRMParameter.UPFRONTFILE.getParameter(),"CAF Number",0),
    UPFRONT2(CRMParameter.UPFRONTFILE.getParameter(),"Cheque Number",6),
    UPFRONT3(CRMParameter.UPFRONTFILE.getParameter(),"Cheque Date",0),
    UPFRONT4(CRMParameter.UPFRONTFILE.getParameter(),"Bank Name",0),
    UPFRONT5(CRMParameter.UPFRONTFILE.getParameter(),"Amount",0),
    UPFRONT6(CRMParameter.UPFRONTFILE.getParameter(),"Entity Name",0),
    WHITELIST_SHEET("Whitelist_Sheet","Whitelist",0),
    WHITELIST0(CRMParameter.WHITELIST.getParameter(),"Sr. No.",0),
    WHITELIST1(CRMParameter.WHITELIST.getParameter(),"Customer ID",0),
    CUSTOMER_STATUS_SHEET("Customer_Status_Sheet","CustomerStatus",0),
    CUSTOMER_STATUS0(CRMParameter.CHANGE_STATUS.getParameter(),"Sr. No.",0),
    CUSTOMER_STATUS1(CRMParameter.CHANGE_STATUS.getParameter(),"Customer ID",0),
    POD_SHEET("POD_Sheet","POD_Data",0),
    POD0(CRMParameter.POD_FILE_UPLOAD.getParameter(),"Sr. No.",0),
    POD1(CRMParameter.POD_FILE_UPLOAD.getParameter(),"Customer ID",0),
    POD2(CRMParameter.POD_FILE_UPLOAD.getParameter(),"Receiver Name",0),
    POD3(CRMParameter.POD_FILE_UPLOAD.getParameter(),"Delivered Date",0),
    POD4(CRMParameter.POD_FILE_UPLOAD.getParameter(),"Receiver Relation",0),
    POD5(CRMParameter.POD_FILE_UPLOAD.getParameter(),"Contact No",10),
    POD6(CRMParameter.POD_FILE_UPLOAD.getParameter(),"Delivery Status",0),
    POD7(CRMParameter.POD_FILE_UPLOAD.getParameter(),"Non Delivery Reason",0),
    POD8(CRMParameter.POD_FILE_UPLOAD.getParameter(),"Instrument Number",0),
    MOUNT_BOOSTER_SHEET("MountBooster_Sheet","Mount_Booster",0),
    MOUNTBOOSTER0(CRMParameter.MOUNT_BOOSTER.getParameter(),"Customer ID",0),
    MOUNTBOOSTER1(CRMParameter.MOUNT_BOOSTER.getParameter(),"Plan Code",0),
    MOUNTBOOSTER2(CRMParameter.MOUNT_BOOSTER.getParameter(),"Quantity",0),
    VALIDITY_EXTENSION_SHEET("ValidityExtension_Sheet","Validity_Extension",0),
    VALIDITYEXTENSION0(CRMParameter.VALIDITY_EXTENSION.getParameter(),"Customer Id",0),
    VALIDITYEXTENSION1(CRMParameter.VALIDITY_EXTENSION.getParameter(),"Extension Days",0),
    VALIDITYEXTENSION2(CRMParameter.VALIDITY_EXTENSION.getParameter(),"Ticket Id",0)
    

    ;

    private String upLoadType;
    private String value;
    private int    size;

    private FileHeaderConstants( String inUploadType, String inValue, int inSize )
    {
        upLoadType = inUploadType;
        value = inValue;
        size = inSize;
    }
   
    public String getUpLoadType()
    {
        return upLoadType;
    }

    public String getValue()
    {
        return value;
    }

    public int getSize()
    {
        return size;
    }
}