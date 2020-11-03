package com.np.tele.crm.constants;

public enum ExcelFailCode {
    STATENULL("State missing/invalid (max 30 characters). "),
    SOCIETYNULL("Society Name invalid (max 50 characters). "),
    FIELDINVALID("Type of Field must be Brown or Green. "), 
    FIELDNULL("Type of Field Missing?"), 
    AREANULL("Area missing/invalid (max 30 characters). "),
    LOCALITYNULL("Locality/Sector missing or invalid (max 50 characters). "),
    CITYNULL("City missing/invalid (max 30 characters). "),
    PINCOEDNULL("Pin Code missing/invalid. "),
    FIBERCONNECTIVITYNULL("FTTN/FTTX missing. "),
    MASTERVALIDATIONFAIL("Master Data Validation Fail"), 
    MOBILEINVALID("Invalid Mobile Number?"), 
    MOBILEINVALIDSTART("Mobile must start with "), 
    MOBILENONNUMERIC("Non numeric mobile value"), 
    MOBILELENGTHPROB("Mobile length must 10 numbers"), 
    ENTITYMISSING("Entity Name Missing?"),
    ENTITYINVALID("Entity Name Invalid?"),
    AMOUNTMISSING("Paid Amount Missing?"), 
    BANKNAMEMISSING("Bank Name Missing?"), 
    CHQDATEMISSING("Cheque Date Missing?"), 
    CHQNOMISSING("Cheque Number Missing?"), 
    CRFIDMISSING("CAF Id Missing?"), 
    UNREGISTEREDBANK(" not exist in our list?"), 
    INVALIDCRFID("value is invalid for CAF Id?"),
    CRFIDNOTPUNCHED("CAF Id not punched yet?"),
    INVALIDCHQNO("value is invalid for Cheque No?"), 
    INVALIDCHQDATE("value is invalid/wrong format for Cheque date?"), 
    INVALIDAMOUNT("value is invalid for Amount?"),
    CUSTOMERIDMISSING("Customer Id Missing?"),
    INVALIDCID("value is invalid for Cusotmer Id?"),
    RECEIVERNAMEMISSING("Receiver Name Missing"),
    DELIVERDATEMISSING("Delivered Date Missing?"),
    INVALIDDELIVERDATE("value is invalid/wrong format for Deliver date?"), 
    CUSTOMERRELATION("Customer Relation Missing"),
    STATUSMISSING("Status Missing"),
    CHEQUENUMBERMISSING("Cheque Number Missing"),
    NETWORK_PARTNER_INVALID("Network Partner does not exist. "),
    CONNECTABLE_HOMES_INVALID("Connectable Homes invalid (max 128 characters). "),
    RFS_DUS_INVALID("RFS DUs missing/invalid (max 20 digits). "),
    LATITUDE_INVALID("Latitude invalid (max 50 digits). "),
    LONGITUDE_INVALID("Longitude invalid (max 50 digits). "),
    CUSTOMER_CATEGORY_INVALID("Customer Category missing/invalid (max 35 digits). "),
    LATITUDE_REQUIRED("Please provide Latitude. "),
    LONGITUDE_REQUIRED("Please provide Longitude. "),
    PLANCODEMISSING("Plan Code Missing?"),
    QUANTITYMISSING("Quantity missing/invalid ?"),
    EXTENSIONDAYSMISSING("Extension days missing/invalid ?"),
    TICKETIDMISSING("Ticket Id missing/invalid ?"),
    TICKETID_INVALID("Ticket Id should at least eleven digits long. ?"),
    QUANTITYNUMERIC("Quantity should be numeric value")
    ;
    
    private String value;

    private ExcelFailCode( String inValue )
    {
        value = inValue;
    }

    public String getValue()
    {
        return value;
    }
}
