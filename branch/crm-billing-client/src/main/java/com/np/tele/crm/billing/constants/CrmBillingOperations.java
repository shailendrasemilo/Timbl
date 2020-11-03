package com.np.tele.crm.billing.constants;

public enum CrmBillingOperations {
    CREATE_CUSTOMER("Create Customer", "CC", "createCustomer"),
    UPDATE_CUSTOMER("UpdateCustomer", "UC", "updateCustomer"),
    CUSTOMER_ACTIVATION("CustomerActivation", "CA", "customerActivation"),
    DISTRIBUTOR_INVENTORY_LIST("Distributor Inventory List", "DIL", "distributorInventoryList"),
    POST_PAYMENT("PostPayment", "PP", "postPayment"),
    CANCEL_PAYMENT("CancelPayment", "CP", "cancelPayment"),
    ADD_CHARGE("AddCharge", "AC", "addCharge"),
    CHANGE_STATUS("ChangeStatus", "CS", "changeStatus"),
    CHANGE_ADDRESS("ChangeAddress", "CAD", "changeAddress"),
    CHANGE_DEVICE_DETAILS("ChangeDeviceDetails","CDD","changeDeviceDetails"),
    POST_WAIVER("PostWaiver", "PW", "postWaiver"),
    GET_BILLING_DETAILS("GetBillingDetails","GBD","getBillingDetails"),
    ADD_EXCEPTION_LIST("AddExceptionList","AEL","addExceptionList"),
    REMOVE_EXCEPTION_LIST("RemoveExceptionList","REL","removeExceptionList"),
    ADD_PLAN("Add Plan","AP","addPlan"),
    CHANGE_OFFER("Plan Migration","CO","changeOffer"),
    CANCEL_PLAN_MIGRATION("Cancel Plan Migration","CPM","cancelPlanOfferMigration"),
    INSTANT_OFFER_RENEW("Instant Plan Renewal","IOR","instantOfferRenew"),
    CANCEL_ADDITIONAL_PLAN("Cancel ADDON/VAS Plans","CPL","cancelAdditionalPlan"),
    CUSTOMER_USAGE_DETAIL("Customer Usage Detail","CUD","getCustomerUsageDetail"),
    CHANGE_BILL_CYCLE("Change Bill Cycle","CBC","changeBillCycle"),
    ALLOW_GRACE_PERIOD("Allow Grace Period","AGP","allowGracePeriod"), 
    TERMINATE_REFUND_PROCESS("Terminate Refund Process","TRP","terminateRefund"),
    CHANGE_BUSINESS_PARTNER("Change Business Partner","CBP","changeBusinessPartner"),
    CHANGE_CUSTOMER_STATUS("Change Customer Status","CCS","changeCustomerStatus"),
    GENERATE_QRC_TICKET("Generate QRC Ticket","GQT","generateQrcTicket"),
    POST_PREPAID_PAYMENT("Post Prepaid Payment","PPP","postPrepaidPayment"),
    INSTANT_PREPAID_OFFER_RENEW("Instant Prepaid Plan Renewal","IPR","instantPrepaidOfferRenew"),
    ;
    private String displayValue;
    private String operationCode;
    private String operationName;

    private CrmBillingOperations( String inDisplayValue, String inOperationCode, String inOperationName )
    {
        displayValue = inDisplayValue;
        operationCode = inOperationCode;
        operationName = inOperationName;
    }

    public String getDisplayValue()
    {
        return displayValue;
    }

    public String getOperationCode()
    {
        return operationCode;
    }

    public String getOperationName()
    {
        return operationName;
    }
}
