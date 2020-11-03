package com.np.tele.crm.constants;

import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.np.tele.crm.utils.PropertyUtils;

public interface IAppConstants
{
    String              STATUS_ACTIVE                          = "Active";
    String              COUNTRY_INDIA                          = "India";
    String              STATUS_INACTIVE                        = "Inactive";
    String              ACCOUNT_LOCK_TIME                      = "24 hr.";
    String              CRM_USER_OBJECT                        = "crmUserDetailsDto";
    String              OLD_USER_OBJECT                        = "oldUserPojo";
    String              CRM_USER_POJO                          = "userPojo";
    String              Y                                      = "Y";
    String              AD_STATUS_INACTIVE                     = "N";
    String              EMP_TYPE_NEXTRA                        = "N";
    String              EMP_TYPE_PARTNER                       = "P";
    String              GREEN_NETWORK_AVAILABILIY              = "G";
    String              BROWN_NETWORK_AVAILABILIY              = "B";
    String              CRM_USER_SEARCH_LIST                   = "crmUserSearchList";
    String              CRM_PAYMENT_SEARCH_LIST                = "paymentSearchList";
    String              CRM_SUCCESS_STATUS_CODE                = "successStatusCode";
    String              CRM_MASTER_ROLE_GROUP_LIST             = "groupsPojos";
    String              MODULES                                = "modules";
    String              SUB_MODULES                            = "subModules";
    String              SUB_SUB_MODULES                        = "subSubModules";
    String              STYLE_SELECTED                         = "selected";
    String              SEARCH_USER_POJO                       = "searchUserPojo";
    String              USERID                                 = "userId";
    String              BLANK_OPTION_82_STRING                 = "0-0-0-0-0-0-0";
    String              SEARCH_SMS_SERVER_LIST                 = "smsGateWayList";
    int                 LOGIN_MAX_TIME                         = 23;
    int                 LOGIN_MIN_TIME                         = 0;
    int                 ZERO                                   = 0;
    int                 MAX_MOBLIE_LENGTH                      = 10;
    int                 MIN_PIN_VALUE                          = 100000;
    int                 MAX_PIN_VALUE                          = 999999;
    int                 MAX_STD_LENGTH                         = 4;
    String              ALIAS                                  = "alias";
    String              SUB_CATEGORY                           = "subCategory";
    String              CATEGORY                               = "category";
    String              ALL                                    = "All";
    String              STR_LOCALITY                           = "_locality_";
    String              STR_AREA                               = "_area_";
    String              STR_CITY                               = "_city_";
    String              STR_STATE                              = "_state_";
    String              STR_COUNTRY                            = "country_";
    String              EMPTY_STRING                           = "";
    String              SINGLE_PARAM                           = "S";
    String              MULTIPLE_PARAM                         = "M";
    String              SERVER_IP_ADDRESS                      = "serverIPAddress";
    String              CLIENT_IP_ADDRESS                      = "clientIPAddress";
    String              BILLED                                 = "Billed";
    String              UNBILLED                               = "Unbilled";
    String              INBOUND                                = "IB";
    String              GROUP_INBOX                            = "GI";
    String              EMAIL_FLAG_VALIDATED                   = "Validated";
    /* query parameters */
    String              PARAM_INDEX                            = "index";
    // Resource Bundle for CRM Clients
    String              COMMON_UTIL_FILE_NAME                  = "crm-common-utils.properties";
    String              CRM_MODULES_FILE_NAME                  = "crm-module-utils.properties";
    String              CRM_CMS_PAYMENT_FILE_NAME              = "crm-cms-payment.properties";
    String              CRM_BILLING_MAPPING_FILE_NAME          = "crm-billing-mapping.properties";
    String              CRM_BILLING_CLIENT                     = "crm.biling.client";
    String              FILE_PATH                              = "upload.dir";
    String              CRM_BILLING_WSDL                       = "http://"
                                                                       + PropertyUtils
                                                                               .getServiceDetails( IAppConstants.CRM_BILLING_CLIENT )
                                                                       + "/RINetwork/Api/Customer.svc?wsdl";
    String              CRM_BILLING_END_POINT                  = "http://"
                                                                       + PropertyUtils
                                                                               .getServiceDetails( IAppConstants.CRM_BILLING_CLIENT )
                                                                       + "/RINetwork/Api/Customer.svc";
    String              CRM_ALERTS_CLIENT                      = "crm.alerts.client";
    String              CRM_ALERTS_WSDL                        = "http://"
                                                                       + PropertyUtils
                                                                               .getServiceDetails( IAppConstants.CRM_ALERTS_CLIENT )
                                                                       + "/crm-alert-service/AlertsService?wsdl";
    String              CRM_ALERTS_END_POINT                   = "http://"
                                                                       + PropertyUtils
                                                                               .getServiceDetails( IAppConstants.CRM_ALERTS_CLIENT )
                                                                       + "/crm-alert-service/AlertsService";
    String              CRM_USR_MNGMNT_CLIENT                  = "crm.usermngmt.client";
    String              CRM_USR_MNGMNT_WSDL                    = "http://"
                                                                       + PropertyUtils
                                                                               .getServiceDetails( IAppConstants.CRM_USR_MNGMNT_CLIENT )
                                                                       + "/crm-user-mngmt-service/UserManagementService?wsdl";
    String              CRM_USR_MNGMNT_END_POINT               = "http://"
                                                                       + PropertyUtils
                                                                               .getServiceDetails( IAppConstants.CRM_USR_MNGMNT_CLIENT )
                                                                       + "/crm-user-mngmt-service/UserManagementService";
    String              CRM_MASTER_DATA_CLIENT                 = "crm.masterdata.client";
    String              CRM_MASTER_DATA_WSDL                   = "http://"
                                                                       + PropertyUtils
                                                                               .getServiceDetails( IAppConstants.CRM_MASTER_DATA_CLIENT )
                                                                       + "/crm-master-service/MasterDataService?wsdl";
    String              CRM_MASTER_DATA_END_POINT              = "http://"
                                                                       + PropertyUtils
                                                                               .getServiceDetails( IAppConstants.CRM_MASTER_DATA_CLIENT )
                                                                       + "/crm-master-service/MasterDataService";
    String              CRM_CONFIG_CLIENT                      = "crm.config.client";
    String              CRM_CONFIG_WSDL                        = "http://"
                                                                       + PropertyUtils
                                                                               .getServiceDetails( IAppConstants.CRM_CONFIG_CLIENT )
                                                                       + "/crm-config-service/CRMConfigService?wsdl";
    String              CRM_CONFIG_END_POINT                   = "http://"
                                                                       + PropertyUtils
                                                                               .getServiceDetails( IAppConstants.CRM_CONFIG_CLIENT )
                                                                       + "/crm-config-service/CRMConfigService";
    String              CRM_GIS_CLIENT                         = "crm.gis.client";
    String              CRM_GIS_WSDL                           = "http://"
                                                                       + PropertyUtils
                                                                               .getServiceDetails( IAppConstants.CRM_GIS_CLIENT )
                                                                       + "/crm-gis-service/GISService?wsdl";
    String              CRM_GIS_END_POINT                      = "http://"
                                                                       + PropertyUtils
                                                                               .getServiceDetails( IAppConstants.CRM_GIS_CLIENT )
                                                                       + "/crm-gis-service/GISService";
    String              CRM_LMS_CLIENT                         = "crm.lms.client";
    String              CRM_LMS_WSDL                           = "http://"
                                                                       + PropertyUtils
                                                                               .getServiceDetails( IAppConstants.CRM_LMS_CLIENT )
                                                                       + "/crm-lms-service/LMSService?wsdl";
    String              CRM_LMS_END_POINT                      = "http://"
                                                                       + PropertyUtils
                                                                               .getServiceDetails( IAppConstants.CRM_LMS_CLIENT )
                                                                       + "/crm-lms-service/LMSService";
    String              CRM_CAP_CLIENT                         = "crm.cap.client";
    String              CRM_CAP_WSDL                           = "http://"
                                                                       + PropertyUtils
                                                                               .getServiceDetails( IAppConstants.CRM_CAP_CLIENT )
                                                                       + "/crm-cap-service/CrmCapService?wsdl";
    String              CRM_CAP_END_POINT                      = "http://"
                                                                       + PropertyUtils
                                                                               .getServiceDetails( IAppConstants.CRM_CAP_CLIENT )
                                                                       + "/crm-cap-service/CrmCapService";
    String              CRM_REPORT_CLIENT                      = "crm.report.client";
    String              CRM_REPORT_WSDL                        = "http://"
                                                                       + PropertyUtils
                                                                               .getServiceDetails( IAppConstants.CRM_REPORT_CLIENT )
                                                                       + "/crm-report-service/CRMReportService?wsdl";
    String              CRM_REPORT_END_POINT                   = "http://"
                                                                       + PropertyUtils
                                                                               .getServiceDetails( IAppConstants.CRM_REPORT_CLIENT )
                                                                       + "/crm-report-service/CRMReportService";
    String              CRM_FINANCE_CLIENT                     = "crm.finance.client";
    String              CRM_FINANCE_WSDL                       = "http://"
                                                                       + PropertyUtils
                                                                               .getServiceDetails( IAppConstants.CRM_FINANCE_CLIENT )
                                                                       + "/crm-finance-service/CrmFinanceService?wsdl";
    String              CRM_FINANCE_END_POINT                  = "http://"
                                                                       + PropertyUtils
                                                                               .getServiceDetails( IAppConstants.CRM_FINANCE_CLIENT )
                                                                       + "/crm-finance-service/CrmFinanceService";
    String              CRM_QRC_CLIENT                         = "crm.qrc.client";
    String              CRM_QRC_WSDL                           = "http://"
                                                                       + PropertyUtils
                                                                               .getServiceDetails( IAppConstants.CRM_QRC_CLIENT )
                                                                       + "/crm-qrc-service/CrmQrcService?wsdl";
    String              CRM_QRC_END_POINT                      = "http://"
                                                                       + PropertyUtils
                                                                               .getServiceDetails( IAppConstants.CRM_QRC_CLIENT )
                                                                       + "/crm-qrc-service/CrmQrcService";
    String              CRM_QRC_FAULT_REPAIR_CLIENT            = "crm.faultRepair.client";
    String              CRM_QRC_FAULT_REPAIR_WSDL              = "http://"
                                                                       + PropertyUtils
                                                                               .getServiceDetails( IAppConstants.CRM_QRC_FAULT_REPAIR_CLIENT )
                                                                       + "/crm-external-trigger/QRCFaultRepairService?wsdl";
    String              CRM_QRC_FAULT_REPAIR_END_POINT         = "http://"
                                                                       + PropertyUtils
                                                                               .getServiceDetails( IAppConstants.CRM_QRC_FAULT_REPAIR_CLIENT )
                                                                       + "/crm-external-trigger/QRCFaultRepairService";
    String              PARAMETER_NAME                         = "method";
    int                 EMAIL_ID_MAX_LENGTH                    = 256;
    //Method Names
    String              METHOD_VIEW_PARTNER                    = "viewPartner";
    String              METHOD_ADD_PARTNER                     = "addPartner";
    String              METHOD_ADD_NP_MOBILE                   = "addNPMobile";
    String              METHOD_ADD_NP_MOBILE_PAGE              = "addNPMobilePage";
    String              METHOD_MODIFY_PARTNER                  = "modifyPartner";
    String              METHOD_SEARCH_PARTNER                  = "searchPartner";
    String              METHOD_EDIT_PARTNER                    = "editPartner";
    String              METHOD_ADD_PARTNER_PAGE                = "addPartnerPage";
    String              METHOD_SEARCH_PARTNER_PAGE             = "searchPartnerPage";
    String              METHOD_ADD_ROLE_GROUP_ROW              = "addRoleGroupRow";
    String              METHOD_CREATE_ROLE_GROUP_PAGE          = "createRoleGroupPage";
    String              METHOD_CREATE_ROLE_GROUP               = "createRoleGroup";
    String              METHOD_MODIFY_ROLE_GROUP_PAGE          = "modifyRoleGroupPage";
    String              METHOD_MODIFY_ROLE_GROUP               = "modifyRoleGroup";
    String              METHOD_CREATE_PARAMETER_GROUP          = "createParameterGroup";
    String              METHOD_MODIFY_PARAMETER_GROUP          = "modifyParameterGroup";
    String              METHOD_MODIFY_PARAMETER_GROUP_PAGE     = "modifyParameterGroupPage";
    String              METHOD_ADD_USER_ROLE_ROW               = "addRolesRow";
    String              METHOD_ASSIGN_AREA                     = "assignAreaPage";
    String              METHOD_ASSIGN_USER_ROLE                = "assingUserRole";
    String              METHOD_ADD_USER_PARAMETER_ROW          = "addParameterRow";
    String              METHOD_ASSIGN_USER_PARAMETER_PAGE      = "updateParameterAssignPage";
    String              METHOD_ASSIGN_USER_PARAMETER           = "assingUserParameter";
    String              CHANGE_PASSWORD_PAGE                   = "changePasswordPage";
    String              METHOD_NEW_USER_FORWARD                = "newUserForward";
    String              METHOD_SEARCH_USER_BY_ID               = "searchUserById";
    String              METHOD_RGST_EXT_PROJECT                = "rgstrExtrnlProject";
    String              METHOD_MODIFY_RGST_EXT_PROJECT         = "modifyCRMProject";
    String              METHOD_RGST_EXT_PROJECT_PAGE           = "rgstrExtrnlPrjctPg";
    String              METHOD_SEARCH_EXT_PROJECT_PAGE         = "searchExtrnlPrjctPg";
    String              METHOD_SEARCH_EXT_PROJECT              = "searchExtrnlProject";
    String              METHOD_CHANGE_EXT_PROJECT_STATUS       = "changeExtrnalProjectStatus";
    String              METHOD_SEARCH_SMS_GATEWAY              = "searchSMSGateway";
    String              METHOD_OPERATION_82                    = "option82Operation";
    String              METHOD_SMS_CONFIGURATION               = "smsConfigurationOperation";
    String              METHOD_OPTION82_PAGE                   = "option82Page";
    String              METHOD_SEARCH_OPTION82_PAGE            = "searchOption82Page";
    String              METHOD_MODIFY_SOCIETY_PAGE             = "modifySocietyPage";
    String              METHOD_VIEW_SOCIETY_PAGE               = "viewSociety";
    String              METHOD_PAYMENT_TRACKING_PAGE           = "paymentTrackingPage";
    String              METHOD_PAYMENT_TRACKING                = "paymentTracking";
    String              METHOD_CHANGE_PAYMENT_STATUS           = "changePaymentStatus";
    String              METHOD_CHANGE_REALIZATION_STATUS_PAGE  = "changeRealizationStatusPage";
    String              METHOD_CMS_TRACKING_PAGE               = "changeRealizationStatusPage";
    String              METHOD_SEARCH_USER                     = "searchUser";
    String              METHOD_CHANGE_PASSWORD                 = "changePassWord";
    String              METHOD_ASSING_USER_ROLE                = "assingUserRole";
    String              METHOD_LOGIN_AUTHENTICATION            = "loginAuthentication";
    String              METHOD_REGISTER_NEW_USER               = "registerNewUser";
    String              METHOD_FORGET_PASSWORD                 = "forgetPassword";
    String              METHOD_MODIFY_USER_PAGE                = "modifyUser";
    String              METHOD_QRC_SEARCH_CUSTOMER_PAGE        = "searchCustomerPage";
    String              METHOD_QRC_SEARCH_CUSTOMER             = "searchCustomer";
    String              METHOD_QRC_ADD_SINGLE_WHITELIST        = "addWhiteList";
    String              METHOD_QRC_BARRING_UNBARRING_PAGE      = "barringUnbarringPage";
    String              METHOD_QRC_DISCONNECTION_PAGE          = "disconnectionPage";
    String              METHOD_QRC_ADDRESS_CHANGE_PAGE         = "addressChangePage";
    String              METHOD_QRC_DEVICE_CHANGE_PAGE          = "deviceChangePage";
    String              METHOD_QRC_EDIT_MAC                    = "editMac";
    String              METHOD_QRC_EDIT_OPTION82               = "editOption82";
    String              METHOD_QRC_EDIT_CPE                    = "editCPE";
    String              METHOD_ADD_PLAN                        = "addPlan";
    String              METHOD_MODIFY_PLAN                     = "modifyPlan";
    String              METHOD_UPDATE_MASS_OUTAGE              = "updateMassOutage";
    // Generic Constants
    String              DOT                                    = ".";
    String              COMMA                                  = ",";
    String              DASH                                   = "-";
    String              SLASH                                  = "/";
    String              EQUAL                                  = "=";
    String              NOTAPPLICABLE                          = "NA";
    String              UNDERSCORE                             = "_";
    String              FILE_SEPERATOR                         = System.getProperty( "file.separator" );
    String              SPACE                                  = " ";
    String              COLON                                  = ":";
    String              HASH                                   = "#";
    // Bean Keys
    String              SMS_ALERT                              = "smsAlert";
    String              EMAIL_ALERT                            = "emailAlert";
    String              MASTER_DATA                            = "masterDataBm";
    String              USER_MANAGEMENT                        = "userMngmntBm";
    String              GIS_MANAGER                            = "gisManagerBm";
    String              APP_DATA_MANAGER                       = "appDataManager";
    String              CRM_CONFIG_MANAGER                     = "configManager";
    String              FINANCE_MANAGER                        = "financeManagerBm";
    String              QRC_MANAGER                            = "qrcManagerBm";
    String              LMS_MANAGER                            = "lmsManagerBm";
    String              CRM_CAP_MANAGER                        = "crmCapManagerBm";
    String              QRC_CONFIG_MANAGER                     = "qrcConfigManagerBm";
    String              INBOX_MANAGER                          = "inboxManagerBm";
    String              SELFCARE_MANAGER                       = "selfcareManager";
    String              SELFCARE_PLAN_MIGRATION_MANAGER        = "selfcarePlanMigrationMgr";
    String              ROLE_GROUP_MAX_LENGTH                  = "128";
    String              ROLE_GROUP_MIN_LENGTH                  = "3";
    String              EMAIL                                  = "email";
    String              SMS                                    = "sms";
    String              START_TIME                             = " 00:00:00";
    String              END_TIME                               = " 23:59:59";
    String              APP_MESSAGE                            = "appMessage";
    String              APP_ERROR                              = "appError";
    String              GIS_ERROR                              = "gisError";
    String              CKEDITOR_BLANK_BODY                    = "<br />\n<br />";
    int                 CKEDITOR_BLANK_BODY_LENGTH             = 15;
    String              NO_RECORD_FOUND                        = "noRecordFound";
    Map<String, Object> flyWeightBeanMap                       = new HashMap<String, Object>();
    String              GIS_MASTER                             = "GisMaster";
    Map<String, Date>   GIS_TIME_MAP                           = new LinkedHashMap<String, Date>();
    String              COUNTRY_LIST                           = "countryList";
    String              USER_MASTER                            = "userMaster";
    String              CRM_ROLES                              = "crmRoles";
    String              CRM_ROLES_LIST                         = "crmRolesList";
    String              SOCIETY_POJO_LIST_SESS                 = "societyPojoListSess";
    //----- Configuration Data --
    //---GIS Method Constant
    String              METHOD_MASTER_GIS_PAGE                 = "masterGisPage";
    String              METHOD_ADD_GIS_STATE_ROW               = "addStateRow";
    String              METHOD_ADD_GIS_CITY_ROW                = "addCityRow";
    String              METHOD_ADD_GIS_AREA_ROW                = "addAreaRow";
    String              METHOD_ADD_GIS_STATE                   = "addNewState";
    String              METHOD_ADD_GIS_CITY                    = "addNewCity";
    String              METHOD_ADD_GIS_AREA                    = "addNewArea";
    String              NO_STATE_RECORD_FOUND                  = "noStateRecordFound";
    String              NO_CITY_RECORD_FOUND                   = "noCityRecordFound";
    String              NO_AREA_RECORD_FOUND                   = "noAreaRecordFound";
    String              SOCIETY_POJO                           = "societyPojo";
    String              METHOD_CREATE_EMAIL_SERVER             = "createEmailServer";
    String              INVALID_ROLE                           = "invalidRole";
    String              METHOD_SMS_UPDATE_OPERATION            = "updateSMSGatewayPage";
    String              METHOD_SMS_CONFIG_PAGE                 = "smsConfigurationPage";
    String              METHOD_CREATE_ALERT_CONFIG             = "createAlertsConfig";
    String              METHOD_CREATE_SOCIETY_PAGE             = "createSocietyPage";
    String              METHOD_SEARCH_SOCIETY_PAGE             = "searchSocietyPage";
    String              METHOD_SEARCH_SOCIETY                  = "searchSociety";
    String              METHOD_GIS_SEARCH_STATE                = "searchState";
    String              METHOD_GIS_SEARCH_CITY                 = "searchCity";
    String              METHOD_GIS_SEARCH_AREA                 = "searchArea";
    String              STATE_GIS                              = "stateGIS";
    String              CITY_GIS                               = "cityGIS";
    String              AREA_GIS                               = "areaGIS";
    String              LMS_POLO_LIST                          = "personalInboxList";
    String              NON_USER_FOUND                         = "noUserFound";
    String              UPLOAD_GIS_PAGE                        = "uploadGisPage";
    String              METHOD_COPY_SOCIETY_PAGE               = "copySocietyPage";
    /*Lms Lead related methods Starts*/
    String              METHOD_LEAD_GENEREATION                = "leadGenereation";
    String              METHOD_PERFORM_ACTION                  = "performAction";
    String              METHOD_MODIFY_DETAILS                  = "modifyLeadDetails";
    String              METHOD_LMS_FILE_UPLOAD                 = "lmsFileUpload";
    String              METHOD_LOG_LMS_TICKET                  = "logLMSTicket";
    /*Lms Lead related methods Ends*/
    String              METHOD_ASSIGN_SERVICE_PARTNER          = "assignServicePartner";
    String              METHOD_ASSIGN_SERVICE_PARTNER_PAGE     = "assignServicePartnerPage";
    //RCA/Reason action methods
    String              METHOD_CREATE_CATEGORY_VALUE           = "createCategoryValues";
    String              METHOD_ADD_RCA_REASON_ROW              = "addRcaReasonRow";
    String              METHOD_CREATE_RCA_REASON_PAGE          = "createRcaReasonPage";
    /* String              GIS_TEXT_PATTERN                   = "^[a-zA-Z\\s]*$";
     String              GIS_ALPHA_NUMERIC_PATTERN          = "^[a-zA-Z0-9-\\s]*$";*/
    //REGEX
    String              METHOD_SELECT_USER_POP_UP              = "selectMappedUser";
    String              METHOD_SELECT_USER_SEARCH              = "searchEWUserPage";
    String              METHOD_SELECT_USER_MAPPING             = "searchEWUserMapping";
    String              METHOD_SEARCH_USER_MAPPING             = "searchUser";
    String              METHOD_SEARCH_USER_MAPPING_POP_UP      = "searchUserPopUp";
    /*I&A related methods Starts*/
    String              I_AND_A_PAGE                           = "installationAndActivationPage";
    String              SEARCH_CRF                             = "searchCrf";
    //String              METHOD_VIEW_CRF_DETAILS                = "viewCRFDetails";
    String              CHEQUE                                 = "Cheque";
    String              DD                                     = "DD";
    String              CASH                                   = "Cash";
    String              ONLINE_PAYMENT                         = "Online Payment";
    String              WHITE_SPACE                            = " ";
    String              ADDRESS_TYPE_INSTALLATION              = "IN";
    String              ADDRESS_TYPE_BILLING                   = "BL";
    String              METHOD_SAVE_CUSTOMER_BASIC_INFO        = "saveCustomerBasicInfo";
    String              SUBMIT_CRF_DETAILS                     = "submitCRFDetails";
    String              SUBMIT_CANCEL_CRF                      = "cancelCRFAction";
    String              OTHER                                  = "Other";
    String              INDIAN                                 = "Indian";
    String              METHOD_VIEW_ADD_RECIEPT_PAGE           = "viewAddReceiptPage";
    String              METHOD_ADD_RECIEPT                     = "addReceipt";
    String              METHOD_ADD_RECIEPT_PAGE                = "addCRFPage";
    String              CLOSE_WINDOW                           = "closeWindow";
    int                 MIN_CRF_LENGTH                         = 4;
    int                 MAX_CRF_LENGTH                         = 8;
    int                 MIN_RECIPT_LENGTH                      = 6;
    int                 MAX_RECIPT_LENGTH                      = 6;
    String              RA                                     = "RA";
    //    String              CA                                     = "CA";
    String[]            RESTRICTED_PROPERTIES_ARRAY            =
                                                               { "class", "createdBy", "createdTime", "lastModifiedBy",
            "lastModifiedTime", "displayCreatedTime", "displayLastModifiedTime", "referralSource", "editable",
            "currentUser", "feasibility", "inboxId", "previousStage", "remarks", "unRead" };
    List<String>        RESTRICTED_PROPERTIES                  = Arrays.asList( RESTRICTED_PROPERTIES_ARRAY );
    String[]            DOCUMENT_EXTENSIONS                    = new String[]
                                                               { "pdf", "doc", "docx", "png", "jpg", "jpeg", "txt",
            "PDF", "DOC", "DOCX", "PNG", "JPG", "JPEG", "TXT" };
    String              EA                                     = "EA";
    String              ADDCRF                                 = "addCRF";
    String              ADDRECIEPT                             = "addReciept";
    String              METHOD_SEARCH_ADDRECIEPT_CRF_PAGE      = "addReceiptCRFPage";
    String              CHANGE_STATUS                          = "changeStatus";
    String              MOBILE_NUMBER_REGEX                    = "mobileNumberRegex";
    String              MOBILE_NUMBER_MESSAGE                  = "mobileNumberMesg";
    /*************InA constant****************/
    String              CUSTOMER                               = "Customer's";
    String              COMPANY                                = "Company";
    String              FATHER_HUSBAND                         = "Father's/Husband's";
    String              MOTHER_MAIDEN                          = "Mother's/Maiden";
    String              COORDINATOR                            = "Coordinator's";
    String              AUTHORIZED_SIGNATORY                   = "Authorized signatory's";
    String              LOCAL_REFERENCE                        = "Local reference's";
    String              FIRST                                  = "first";
    String              LAST                                   = "last";
    String              CUSTOMER_PROFILE_LIST                  = "customerProfileList";
    String              PAN_GIR                                = "not having PAN/GIR Number";
    String              DOCUMENT_DETAIL                        = "documents being produced in support of Address";
    String              MOBILE_MAKE                            = "mobile make";
    String              VEHICLE                                = "vehicle";
    String              APPROVE                                = "Approve";
    String              CHANGE_SELES_ADD                       = "FeasibleAddress";
    String              APPROVE_BY_SP                          = "Approve By SP";
    String              YES                                    = "Yes";
    String              NO                                     = "No";
    String              ALL_LOCALITY                           = "0|All Locality";
    String              ALL_AREA                               = "0|All Area";
    String              ALL_SOCIETY                            = "0|All Society";
    String              METHOD_PAYMENT_POSTING                 = "paymentPosting";
    String              METHOD_PAYMENT_POSTING_PAGE            = "paymentPostingPage";
    String              EDIT_CRF_AT_ANY_STAGE                  = "editCRFEntryForAnyStage";
    String              SUBMIT_CRF_PAGE                        = "submitCRFDetails";
    String              METHOD_SAVE_VALIDATE_CRF               = "saveValidateCRFEntry";
    String              METHOD_SAVE_NETWORK_DETAILS            = "saveNetworkDetails";
    String              METHOD_SAVE_MAPMACID_DETAILS           = "saveMapMacIdDetails";
    String              METHOD_BINDCPE_MACID                   = "bindCPEMACId";
    String              METHOD_SAVE_DEVICE_STATUS              = "saveDeviceStatus";
    String              METHOD_EDIT_CPE_STATUS_PAGE            = "editCPEStatusPage";
    String              METHOD_SAVE_REMARKS                    = "saveRemarks";
    String              METHOD_PUNCH_ISR                       = "punchISR";
    String              METHOD_CANCEL_CRF_ACTION               = "cancelCRFAction";
    String              METHOD_NETWORK_INVENTORY_DETAILS_PAGE  = "networkInventoryDetailsPage";
    String              MATERIAL_LIST                          = "MaterialList";
    String              CUSTOMER_FEEDBACK_LIST                 = "CustomerFeedBackList";
    String              REJECT_BY_NP                           = "Reject By NP";
    String              REJECT_BY_SP                           = "Reject By SP";
    String              SUBMIT_REFUSAL                         = "submitRefusal";
    String              SUBMIT_REFUSAL_BY_SP                   = "Customer Refusal By SP";
    String              CHANGE_FEASIBLE_ADD                    = "Change Feasible Address";
    String              CRF_ERP                                = "ERP";
    String              CRF_CANCELLATION                       = "Cancellation";
    String              SECURITY_DEPOSIT                       = "Security deposit";
    String              RENTAL_CHARGE                          = "Rental charge";
    String              PARAM_CUSTOMER_RECORD_ID               = "customerRecordId";
    String              PARAM_MAPPING_ID                       = "mappingId";
    String              FUNCTIONAL_BIN                         = "Functional Bin=";
    String              BRAND_INITIA                           = "INITIA";
    /*********************Finance module constant**************************/
    String              METHOD_PAYMENT_REVERSAL_PAGE           = "paymentReversalPage";
    String              METHOD_PAYMENT_REVERSAL_POP_UP         = "paymentReversalPopUp";
    String              METHOD_SEARCH_PAYMENT_REVERSAL         = "searchReversalPaymentDetails";
    String              SR_NUMBER                              = "SR";
    String              DOCUMENT                               = "DOC";
    String              REJECT                                 = "Reject";
    String              METHOD_SUBMIT_UP_FRONT_PAYMENT         = "submitUpfrontPaymentManualy";
    String              MANUALY_UPFRONT_PAYMENT_PAGE           = "manualyUpfrontPaymentPage";
    String              METHOD_SUSPENSE_REJECTED_PAYMENET_PAGE = "suspenseRejectedPaymentPage";
    String              METHOD_VIEW_SUSPENSE_REJECTED_PAYMENET = "viewSuspenseRejectedRecord";
    String              CRM_UPFRONT_PAYMENT_LIST               = "crmUpfrontPaymentList";
    String              SEARCH_UPFRONT_PAYMENT_PAGE            = "searchUpfrontPaymentPage";
    String              METHOD_SEARCH_UPFRONT_PAYMENT          = "searchUpfrontPayment";
    String              UPLOAD_POD_FILE                        = "uploadPODFile";
    /**************************************/
    String              QRC_CATEGORY_BILLING                   = "Billing";
    String              VERIFY_USER                            = "verifyUser";
    String              CUSTOMER_PROFILE_SEARCH                = "customerProfileSearch";
    String              NEW_LINE                               = "\n";
    String              PDF_CONTENT_TYPE                       = "application/pdf";
    String              PDF_FONT                               = "TIMES_ROMAN";
    String              CONTENT_DISPOSITION                    = "Content-Disposition";
    String              INTLINE_FILE_NAME                      = "inline; filename=\"test.pdf\"";
    String              PDF_ICON_PATH                          = "images/%s-pdf.png";
    String              PDF_CHECK_BOX_PATH                     = "images/pdfCheckbox.png";
    String              PDF_CHECK_BOX_YES_PATH                 = "images/pdfCheckboxYes.png";
    String              REGISTERED_MOBILE_NUMBER               = "rmn";
    //String              REGISTERED_TELEPHONE_NUMBER            = "rtn";
    String              CONTACT_NUMBER                         = "contactno";
    String              SUB_UPFRONT_POPUP_SEARCH               = "subUpfrontPopupSearch";
    String              FREEZE                                 = "F";
    CharSequence        METHOD_MODIFY_USER                     = "modifyUser";
    String              CUST_DETAILS_LIST                      = "customerDetailsPojos";
    String              CATEGORY_VALUE                         = "SALES";
    String              BULK_WHITELIST                         = "bulkWhitelist";
    String              CREATE_CUSTOMER_INTERACTION            = "createCustomerInteraction";
    CharSequence        METHOD_QRC_WAIVER_PAGE                 = "waiverPage";
    String              QRC_PRIMARY_MAC                        = "Primary";
    String              QRC_SECONDARY_MAC                      = "Secondary";
    String              OPTION82                               = "Option 82";
    String              NASPORTID                              = "NASport ID";
    String              METHOD_SEARCH_CUSTOMER_INTERACTION     = "searchCustomerInteraction";
    String              NBC                                    = "Next Billing Cycle";
    /** QRC Config */
    String              METHOD_ADD_ROW_RESOLUTION_CODE         = "addRowResolutionCode";
    String              METHOD_REMOVE_ROW_RESOLUTION_CODE      = "removeRowResolutionCode";
    String              METHOD_CONFIG_RESOLUTION_CODE          = "configResolutionCode";
    String              METHOD_CREATE_ATTRIBUTED_TO            = "attributedToPage";
    String              METHOD_ADD_ROW_ATTRIBUTED_TO           = "addRowAttributedTo";
    String              METHOD_REMOVE_ROW__ATTRIBUTED_TO       = "removeRowAttributedTo";
    String              METHOD_CONFIGURE_SUB_SUB_CAT_PAGE      = "configureSubSubCatPage";
    String              METHOD_CONFIGURE_SUB_SUB_CAT           = "configureSubSubCat";
    String              YES_CHAR                               = "Y";
    String              NO_CHAR                                = "N";
    String              METHOD_ADD_ACCESSORIES_PAGE            = "addRemoveAccessoriesPage";
    String              METHOD_QRC_GET_ACCESSORIES_ADDACC      = "getAccessories";
    String              METHOD_ADD_ACCESSORIES                 = "addRemoveAccessories";
    String              METHOD_CREATE_ATTRIBUTED_TO_PAGE       = "configAttributedToPage";
    /** ADD ACCESSORIES **/
    String              QRC_REQUEST_PARAM                      = "requestType";
    String              METHOD_CUSTOMER_ACTIVATION_PLAN        = "getCustomerActivationPlan";
    String              METHOD_TARIFF_MIGRATION_PAGE           = "tariffMigrationPage";
    /**Methods for Selfcare **/
    String              METHOD_CHANGE_PASSWORD_PAGE            = "changePasswordPage";
    String              METHOD_MYACCOUNTPAGE                   = "myAccountPage";
    String              METHOD_SAVE_CUSTOMER_PLAN              = "saveCustomerActivationPlan";
    /** Selfcare Constants **/
    String              SELFCARE_UTILS                         = "selfcareUtils";
    String              METHOD_QUICKPAY_PAGE                   = "quickPayPage";
    /**QRC METOD NAME**/
    String              METHOD_VIEW_TICKET_PAGE                = "viewTicketPage";
    String              METHOD_TICKET_PAGE                     = "ticketPage";
    String              INBOX                                  = "inbox";
    String              CUSTOMER_PROFILE                       = "customerProfile";
    String              METHOD_VIEW_SAFE_CUSTODY               = "viewSafeCustody";
    String              METHOD_UPDATE_SAFE_CUSTODY             = "updateSafeCustody";
    String              METHOD_UPDATE_CUSTOMERS_STATUS         = "updateCustomersStatus";
    String              METHOD_UPDATE_CUSTOMER_BILLCYCLE       = "updateCustomerBillCycle";
    String              METHOD_UPDATE_CUSTOMER_OWNERSHIP       = "updateCustomerOwnerShip";
    String              METHOD_UPDATE_CUSTOMER_CATEGORY        = "updateCustomerCategory";
    String              METHOD_VIEW_CUSTOMER_BILLCYCLE         = "viewCustomerBillCycle";
    String              METHOD_VIEW_CUSTOMER_CATEGORY          = "viewCustomerCategory";
    String              METHOD_VIEW_CUSTOMER_OWNERSHIP         = "viewCustomerOwnerShip";
    String              METHOD_DOCUMENT_DETAILS                = "documentDetails";
    String              SYNC_BILL_CYCLE_WITH_BILLING           = "syncBillCycleWithBillilng";
    // Methods for workflows
    String              METHOD_SHIFTING_INITIATION_PAGE        = "shiftingInitiationPage";
    String              METHOD_SHIFTING_INITIATION             = "shiftingInitiation";
    String              METHOD_WAIVER_APPROVED_REJECT          = "approveRejectWaiver";
    String              METHOD_INBOX_WORKFLOW                  = "workflowInbox";
    String              METHOD_CHANGE_INBOX_BIN                = "changeBinOwner";
    String              METHOD_IFR_STAGE                       = "ifrStage";
    String              WORKFLOW_PREFIX                        = "W";
    String              METHOD_SAVE_SP_DETAILS                 = "saveSpDetails";
    String              METHOD_SAVE_NP_DETAILS                 = "saveNpDetails";
    String              METHOD_WAIVER_PAGE                     = "modifiyWaiverPage";
    String              METHOD_REFUND_TRACKING_PAGE            = "refundTrackingPage";
    String              METHOD_EDIT_SP                         = "editSpdetails";
    String              METHOD_EDIT_NP                         = "editNpdetails";
    String              METHOD_EDIT_IFRAD                      = "editIFRADdetails";
    String              METHOD_GET_SHIFTING_HISTORY            = "getShiftingHistory";
    String              METHOD_CSD_LEVEL3                      = "submitCSDLevel3";
    String              METHOD_CSD_LEVEL2                      = "submitcsdStage";
    String              METHOD_EOC_LEVEL_ONE                   = "submitIfrEOCL1";
    String              METHOD_NOC_LEVEL1                      = "submitNOCLevel1";
    String              METHOD_IFR_EOC_LEVEL2                  = "submitftLevel2";
    /** Workflow Constants **/
    String              WORKFLOW                               = "workflow";
    /**MassOutage Constants*/
    String              METHOD_SEARCH_MASS_OUTAGE_PAGE         = "searchMassOutagePage";
    String              METHOD_SEARCH_MASS_OUTAGE              = "searchMassOutage";
    String              METHOD_UPDATE_MASSOUTAGE               = "updateMassOutage";
    String              METHOD_ADD_MASSOUTAGE_PAGE             = "addMassOutagePage";
    String              METHOD_ADD_MASSOUTAGE                  = "addMassOutage";
    String              METHOD_GRACE_PERIOD                    = "saveGracePeriod";
    String              MULTIPLE_GRACE_PERIOD                  = "multipleGracePeriod";
    String              METHOD_CANCEL_CUSTOMER_BILLCYCLE       = "cancelCustomerBillCycle";
    String              METHOD_VIEW_VALIDITY_EXTENSION_PAGE    = "viewGracePeriod";
    String              REMOVE_ADDON                           = "REMOVE_ADDON";
    String              CHANGE_ADDON                           = "CHANGE_ADDON";
    String              IMMEDIATE_ACTIVATION                   = "Immediate";
    String              AUTO_REMARKS                           = "Lead Generated By Customer";
    String              CHANGE_PASSWORD                        = "ChangePassword";
    String              FORGOT_PASSWORD                        = "forgotPassword";
    String              ASSIGN                                 = "Assign";
    String              QUERY_STATE                            = "stateQuery";
    String              QUERY_CITY                             = "cityQuery";
    String              QUERY_AREA                             = "areaQuery";
    String              QUERY_LOCALITIES                       = "locatiliesQuery";
    String              LEAD_ACTION                            = "leadAction";
    String              PARTNER                                = "partner";
    String              TIMBL                                  = "Timbl";
    String              METHOD_CSD_STAGE                       = "submitcsdStage";
    String              AREA                                   = "Area";
    String              EVENT                                  = "event";
    String              ADD_MASS_OUTAGE                        = "ADD_MASS_OUTAGE";
    String              INSTALLATION_ADDRESS                   = "IN";
    String              WITHOUT_ADDON                          = "WOA";
}
