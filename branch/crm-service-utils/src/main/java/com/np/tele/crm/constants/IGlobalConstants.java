package com.np.tele.crm.constants;

import com.np.tele.crm.alerts.service.IAlertsServiceLocal;
import com.np.tele.crm.alerts.service.IAlertsServiceRemote;
import com.np.tele.crm.cap.service.ICrmCapServiceLocal;
import com.np.tele.crm.cap.service.ICrmCapServiceRemote;
import com.np.tele.crm.config.service.ICrmConfigServiceLocal;
import com.np.tele.crm.config.service.ICrmConfigServiceRemote;
import com.np.tele.crm.easyBill.ICrmEasyBillServiceLocal;
import com.np.tele.crm.easyBill.ICrmEasyBillServiceRemote;
import com.np.tele.crm.ecaf.fileupload.IECAFServiceLocal;
import com.np.tele.crm.ecaf.fileupload.IECAFServiceRemote;
import com.np.tele.crm.external.trigger.ICrmExternalTriggerServiceLocal;
import com.np.tele.crm.external.trigger.ICrmExternalTriggerServiceRemote;
import com.np.tele.crm.faultRepair.IFaultRepairServiceLocal;
import com.np.tele.crm.faultRepair.IFaultRepairServiceRemote;
import com.np.tele.crm.finance.service.ICrmFinanceServiceLocal;
import com.np.tele.crm.finance.service.ICrmFinanceServiceRemote;
import com.np.tele.crm.gis.service.IGISServiceLocal;
import com.np.tele.crm.gis.service.IGISServiceRemote;
import com.np.tele.crm.lms.service.ILMSServiceLocal;
import com.np.tele.crm.lms.service.ILMSServiceRemote;
import com.np.tele.crm.masterdata.service.IMasterDataServiceLocal;
import com.np.tele.crm.masterdata.service.IMasterDataServiceRemote;
import com.np.tele.crm.qrc.service.ICrmQrcServiceLocal;
import com.np.tele.crm.qrc.service.ICrmQrcServiceRemote;
import com.np.tele.crm.report.service.IReportServiceLocal;
import com.np.tele.crm.report.service.IReportServiceRemote;
import com.np.tele.crm.usrmngmnt.service.IUserManagementServiceLocal;
import com.np.tele.crm.usrmngmnt.service.IUserManagementServiceRemote;

public interface IGlobalConstants
{
    String APPLICATION_NAME                                            = "crm-service-ear";
    String LOCAL                                                       = "local";
    String REMOTE                                                      = "remote";
    String GLOBAL                                                      = "global";
    String APP                                                         = "app";
    String MODULE                                                      = "module";
    String JAVA_GLOBAL                                                 = "java:global";
    String JAVA_APP                                                    = "java:app";
    String JAVA_MODULE                                                 = "java:module";
    String JAVA_GLOBAL_APPLICATION_NAME                                = JAVA_GLOBAL + "/" + APPLICATION_NAME;
    //User Management Ejb
    String BEAN_NAME_USER_MANAGEMENT_EJB                               = "UserManagementEjbService";
    String MODULE_NAME_USER_MANAGEMENT_EJB                             = "crm-user-mngmt-service";
    String GLOBAL_PREFIX_USER_MANAGEMENT_EJB                           = JAVA_GLOBAL_APPLICATION_NAME + "/"
                                                                               + MODULE_NAME_USER_MANAGEMENT_EJB + "/"
                                                                               + BEAN_NAME_USER_MANAGEMENT_EJB;
    String GLOBAL_REMOTE_USER_MANAGEMENT_EJB_BINDING_NAME              = GLOBAL_PREFIX_USER_MANAGEMENT_EJB
                                                                               + "!"
                                                                               + IUserManagementServiceRemote.class
                                                                                       .getName();
    String GLOBAL_LOCAL_USER_MANAGEMENT_EJB_BINDING_NAME               = GLOBAL_PREFIX_USER_MANAGEMENT_EJB
                                                                               + "!"
                                                                               + IUserManagementServiceLocal.class
                                                                                       .getName();
    String APP_PREFIX_USER_MANAGEMENT_EJB                              = JAVA_APP + "/"
                                                                               + MODULE_NAME_USER_MANAGEMENT_EJB + "/"
                                                                               + BEAN_NAME_USER_MANAGEMENT_EJB;
    String APP_REMOTE_USER_MANAGEMENT_EJB_BINDING_NAME                 = APP_PREFIX_USER_MANAGEMENT_EJB
                                                                               + "!"
                                                                               + IUserManagementServiceRemote.class
                                                                                       .getName();
    String APP_LOCAL_USER_MANAGEMENT_EJB_BINDING_NAME                  = APP_PREFIX_USER_MANAGEMENT_EJB
                                                                               + "!"
                                                                               + IUserManagementServiceLocal.class
                                                                                       .getName();
    String MODULE_PREFIX_USER_MANAGEMENT_EJB                           = JAVA_MODULE + "/"
                                                                               + BEAN_NAME_USER_MANAGEMENT_EJB;
    String MODULE_REMOTE_USER_MANAGEMENT_EJB_BINDING_NAME              = MODULE_PREFIX_USER_MANAGEMENT_EJB
                                                                               + "!"
                                                                               + IUserManagementServiceRemote.class
                                                                                       .getName();
    String MODULE_LOCAL_USER_MANAGEMENT_EJB_BINDING_NAME               = MODULE_PREFIX_USER_MANAGEMENT_EJB
                                                                               + "!"
                                                                               + IUserManagementServiceLocal.class
                                                                                       .getName();
    //Alerts Service Ejb
    String BEAN_NAME_ALERTS_SERVICE_EJB                                = "AlertsEjbService";
    String MODULE_NAME_ALERTS_SERVICE_EJB                              = "crm-alert-service";
    String GLOBAL_PREFIX_ALERTS_SERVICE_EJB                            = JAVA_GLOBAL_APPLICATION_NAME + "/"
                                                                               + MODULE_NAME_ALERTS_SERVICE_EJB + "/"
                                                                               + BEAN_NAME_ALERTS_SERVICE_EJB;
    String GLOBAL_REMOTE_ALERTS_SERVICE_EJB_BINDING_NAME               = GLOBAL_PREFIX_ALERTS_SERVICE_EJB + "!"
                                                                               + IAlertsServiceRemote.class.getName();
    String GLOBAL_LOCAL_ALERTS_SERVICE_EJB_BINDING_NAME                = GLOBAL_PREFIX_ALERTS_SERVICE_EJB + "!"
                                                                               + IAlertsServiceLocal.class.getName();
    String APP_PREFIX_ALERTS_SERVICE_EJB                               = JAVA_APP + "/"
                                                                               + MODULE_NAME_ALERTS_SERVICE_EJB + "/"
                                                                               + BEAN_NAME_ALERTS_SERVICE_EJB;
    String APP_REMOTE_ALERTS_SERVICE_EJB_BINDING_NAME                  = APP_PREFIX_ALERTS_SERVICE_EJB + "!"
                                                                               + IAlertsServiceRemote.class.getName();
    String APP_LOCAL_ALERTS_SERVICE_EJB_BINDING_NAME                   = APP_PREFIX_ALERTS_SERVICE_EJB + "!"
                                                                               + IAlertsServiceLocal.class.getName();
    String MODULE_PREFIX_ALERTS_SERVICE_EJB                            = JAVA_MODULE + "/"
                                                                               + BEAN_NAME_ALERTS_SERVICE_EJB;
    String MODULE_REMOTE_ALERTS_SERVICE_EJB_BINDING_NAME               = MODULE_PREFIX_ALERTS_SERVICE_EJB + "!"
                                                                               + IAlertsServiceRemote.class.getName();
    String MODULE_LOCAL_ALERTS_SERVICE_EJB_BINDING_NAME                = MODULE_PREFIX_ALERTS_SERVICE_EJB + "!"
                                                                               + IAlertsServiceLocal.class.getName();
    //Master Service Ejb
    String BEAN_NAME_MASTER_SERVICE_EJB                                = "MasterEjbService";
    String MODULE_NAME_MASTER_SERVICE_EJB                              = "crm-master-service";
    String GLOBAL_PREFIX_MASTER_SERVICE_EJB                            = JAVA_GLOBAL_APPLICATION_NAME + "/"
                                                                               + MODULE_NAME_MASTER_SERVICE_EJB + "/"
                                                                               + BEAN_NAME_MASTER_SERVICE_EJB;
    String GLOBAL_REMOTE_MASTER_SERVICE_EJB_BINDING_NAME               = GLOBAL_PREFIX_MASTER_SERVICE_EJB
                                                                               + "!"
                                                                               + IMasterDataServiceRemote.class
                                                                                       .getName();
    String GLOBAL_LOCAL_MASTER_SERVICE_EJB_BINDING_NAME                = GLOBAL_PREFIX_MASTER_SERVICE_EJB
                                                                               + "!"
                                                                               + IMasterDataServiceLocal.class
                                                                                       .getName();
    String APP_PREFIX_MASTER_SERVICE_EJB                               = JAVA_APP + "/"
                                                                               + MODULE_NAME_MASTER_SERVICE_EJB + "/"
                                                                               + BEAN_NAME_MASTER_SERVICE_EJB;
    String APP_REMOTE_MASTER_SERVICE_EJB_BINDING_NAME                  = APP_PREFIX_MASTER_SERVICE_EJB
                                                                               + "!"
                                                                               + IMasterDataServiceRemote.class
                                                                                       .getName();
    String APP_LOCAL_MASTER_SERVICE_EJB_BINDING_NAME                   = APP_PREFIX_MASTER_SERVICE_EJB
                                                                               + "!"
                                                                               + IMasterDataServiceLocal.class
                                                                                       .getName();
    String MODULE_PREFIX_MASTER_SERVICE_EJB                            = JAVA_MODULE + "/"
                                                                               + BEAN_NAME_MASTER_SERVICE_EJB;
    String MODULE_REMOTE_MASTER_SERVICE_EJB_BINDING_NAME               = MODULE_PREFIX_MASTER_SERVICE_EJB
                                                                               + "!"
                                                                               + IMasterDataServiceRemote.class
                                                                                       .getName();
    String MODULE_LOCAL_MASTER_SERVICE_EJB_BINDING_NAME                = MODULE_PREFIX_MASTER_SERVICE_EJB
                                                                               + "!"
                                                                               + IMasterDataServiceLocal.class
                                                                                       .getName();
    //Config Service Ejb
    String BEAN_NAME_CONFIG_SERVICE_EJB                                = "ConfigEjbService";
    String MODULE_NAME_CONFIG_SERVICE_EJB                              = "crm-config-service";
    String GLOBAL_PREFIX_CONFIG_SERVICE_EJB                            = JAVA_GLOBAL_APPLICATION_NAME + "/"
                                                                               + MODULE_NAME_CONFIG_SERVICE_EJB + "/"
                                                                               + BEAN_NAME_CONFIG_SERVICE_EJB;
    String GLOBAL_REMOTE_CONFIG_SERVICE_EJB_BINDING_NAME               = GLOBAL_PREFIX_CONFIG_SERVICE_EJB
                                                                               + "!"
                                                                               + ICrmConfigServiceRemote.class
                                                                                       .getName();
    String GLOBAL_LOCAL_CONFIG_SERVICE_EJB_BINDING_NAME                = GLOBAL_PREFIX_CONFIG_SERVICE_EJB + "!"
                                                                               + ICrmConfigServiceLocal.class.getName();
    String APP_PREFIX_CONFIG_SERVICE_EJB                               = JAVA_APP + "/"
                                                                               + MODULE_NAME_CONFIG_SERVICE_EJB + "/"
                                                                               + BEAN_NAME_CONFIG_SERVICE_EJB;
    String APP_REMOTE_CONFIG_SERVICE_EJB_BINDING_NAME                  = APP_PREFIX_CONFIG_SERVICE_EJB
                                                                               + "!"
                                                                               + ICrmConfigServiceRemote.class
                                                                                       .getName();
    String APP_LOCAL_CONFIG_SERVICE_EJB_BINDING_NAME                   = APP_PREFIX_CONFIG_SERVICE_EJB + "!"
                                                                               + ICrmConfigServiceLocal.class.getName();
    String MODULE_PREFIX_CONFIG_SERVICE_EJB                            = JAVA_MODULE + "/"
                                                                               + BEAN_NAME_CONFIG_SERVICE_EJB;
    String MODULE_REMOTE_CONFIG_SERVICE_EJB_BINDING_NAME               = MODULE_PREFIX_CONFIG_SERVICE_EJB
                                                                               + "!"
                                                                               + ICrmConfigServiceRemote.class
                                                                                       .getName();
    String MODULE_LOCAL_CONFIG_SERVICE_EJB_BINDING_NAME                = MODULE_PREFIX_CONFIG_SERVICE_EJB + "!"
                                                                               + ICrmConfigServiceLocal.class.getName();
    //GIS Service Ejb
    String BEAN_NAME_GIS_SERVICE_EJB                                   = "GISEjbService";
    String MODULE_NAME_GIS_SERVICE_EJB                                 = "crm-gis-service";
    String GLOBAL_PREFIX_GIS_SERVICE_EJB                               = JAVA_GLOBAL_APPLICATION_NAME + "/"
                                                                               + MODULE_NAME_GIS_SERVICE_EJB + "/"
                                                                               + BEAN_NAME_GIS_SERVICE_EJB;
    String GLOBAL_REMOTE_GIS_SERVICE_EJB_BINDING_NAME                  = GLOBAL_PREFIX_GIS_SERVICE_EJB + "!"
                                                                               + IGISServiceRemote.class.getName();
    String GLOBAL_LOCAL_GIS_SERVICE_EJB_BINDING_NAME                   = GLOBAL_PREFIX_GIS_SERVICE_EJB + "!"
                                                                               + IGISServiceLocal.class.getName();
    String APP_PREFIX_GIS_SERVICE_EJB                                  = JAVA_APP + "/" + MODULE_NAME_GIS_SERVICE_EJB
                                                                               + "/" + BEAN_NAME_GIS_SERVICE_EJB;
    String APP_REMOTE_GIS_SERVICE_EJB_BINDING_NAME                     = APP_PREFIX_GIS_SERVICE_EJB + "!"
                                                                               + IGISServiceRemote.class.getName();
    String APP_LOCAL_GIS_SERVICE_EJB_BINDING_NAME                      = APP_PREFIX_GIS_SERVICE_EJB + "!"
                                                                               + IGISServiceLocal.class.getName();
    String MODULE_PREFIX_GIS_SERVICE_EJB                               = JAVA_MODULE + "/" + BEAN_NAME_GIS_SERVICE_EJB;
    String MODULE_REMOTE_GIS_SERVICE_EJB_BINDING_NAME                  = MODULE_PREFIX_GIS_SERVICE_EJB + "!"
                                                                               + IGISServiceRemote.class.getName();
    String MODULE_LOCAL_GIS_SERVICE_EJB_BINDING_NAME                   = MODULE_PREFIX_GIS_SERVICE_EJB + "!"
                                                                               + IGISServiceLocal.class.getName();
    //LMS Service EJB
    //GIS Service Ejb
    String BEAN_NAME_LMS_SERVICE_EJB                                   = "LMSEjbService";
    String MODULE_NAME_LMS_SERVICE_EJB                                 = "crm-lms-service";
    String GLOBAL_PREFIX_LMS_SERVICE_EJB                               = JAVA_GLOBAL_APPLICATION_NAME + "/"
                                                                               + MODULE_NAME_LMS_SERVICE_EJB + "/"
                                                                               + BEAN_NAME_LMS_SERVICE_EJB;
    String GLOBAL_REMOTE_LMS_SERVICE_EJB_BINDING_NAME                  = GLOBAL_PREFIX_LMS_SERVICE_EJB + "!"
                                                                               + ILMSServiceRemote.class.getName();
    String GLOBAL_LOCAL_LMS_SERVICE_EJB_BINDING_NAME                   = GLOBAL_PREFIX_LMS_SERVICE_EJB + "!"
                                                                               + ILMSServiceLocal.class.getName();
    String APP_PREFIX_LMS_SERVICE_EJB                                  = JAVA_APP + "/" + MODULE_NAME_LMS_SERVICE_EJB
                                                                               + "/" + BEAN_NAME_LMS_SERVICE_EJB;
    String APP_REMOTE_LMS_SERVICE_EJB_BINDING_NAME                     = APP_PREFIX_LMS_SERVICE_EJB + "!"
                                                                               + ILMSServiceRemote.class.getName();
    String APP_LOCAL_LMS_SERVICE_EJB_BINDING_NAME                      = APP_PREFIX_LMS_SERVICE_EJB + "!"
                                                                               + ILMSServiceLocal.class.getName();
    String MODULE_PREFIX_LMS_SERVICE_EJB                               = JAVA_MODULE + "/" + BEAN_NAME_LMS_SERVICE_EJB;
    String MODULE_REMOTE_LMS_SERVICE_EJB_BINDING_NAME                  = MODULE_PREFIX_LMS_SERVICE_EJB + "!"
                                                                               + ILMSServiceRemote.class.getName();
    String MODULE_LOCAL_LMS_SERVICE_EJB_BINDING_NAME                   = MODULE_PREFIX_LMS_SERVICE_EJB + "!"
                                                                               + ILMSServiceLocal.class.getName();
    //Report Service EJB
    String BEAN_NAME_REPORT_SERVICE_EJB                                = "ReportEjbService";
    String MODULE_NAME_REPORT_SERVICE_EJB                              = "crm-report-service";
    String GLOBAL_PREFIX_REPORT_SERVICE_EJB                            = JAVA_GLOBAL_APPLICATION_NAME + "/"
                                                                               + MODULE_NAME_REPORT_SERVICE_EJB + "/"
                                                                               + BEAN_NAME_REPORT_SERVICE_EJB;
    String GLOBAL_REMOTE_REPORT_SERVICE_EJB_BINDING_NAME               = GLOBAL_PREFIX_REPORT_SERVICE_EJB + "!"
                                                                               + IReportServiceRemote.class.getName();
    String GLOBAL_LOCAL_REPORT_SERVICE_EJB_BINDING_NAME                = GLOBAL_PREFIX_REPORT_SERVICE_EJB + "!"
                                                                               + IReportServiceLocal.class.getName();
    String APP_PREFIX_REPORT_SERVICE_EJB                               = JAVA_APP + "/"
                                                                               + MODULE_NAME_REPORT_SERVICE_EJB + "/"
                                                                               + BEAN_NAME_REPORT_SERVICE_EJB;
    String APP_REMOTE_REPORT_SERVICE_EJB_BINDING_NAME                  = APP_PREFIX_REPORT_SERVICE_EJB + "!"
                                                                               + IReportServiceRemote.class.getName();
    String APP_LOCAL_REPORT_SERVICE_EJB_BINDING_NAME                   = APP_PREFIX_REPORT_SERVICE_EJB + "!"
                                                                               + IReportServiceLocal.class.getName();
    String MODULE_PREFIX_REPORT_SERVICE_EJB                            = JAVA_MODULE + "/"
                                                                               + BEAN_NAME_REPORT_SERVICE_EJB;
    String MODULE_REMOTE_REPORT_SERVICE_EJB_BINDING_NAME               = MODULE_PREFIX_REPORT_SERVICE_EJB + "!"
                                                                               + IReportServiceRemote.class.getName();
    String MODULE_LOCAL_REPORT_SERVICE_EJB_BINDING_NAME                = MODULE_PREFIX_REPORT_SERVICE_EJB + "!"
                                                                               + IReportServiceLocal.class.getName();
    // Installation And Activation EJB Binding
    String BEAN_NAME_CRM_CAP_SERVICE_EJB                               = "CrmCapEjbService";
    String MODULE_NAME_CRM_CAP_SERVICE_EJB                             = "crm-cap-service";
    String GLOBAL_PREFIX_CRM_CAP_SERVICE_EJB                           = JAVA_GLOBAL_APPLICATION_NAME + "/"
                                                                               + MODULE_NAME_CRM_CAP_SERVICE_EJB + "/"
                                                                               + BEAN_NAME_CRM_CAP_SERVICE_EJB;
    String GLOBAL_REMOTE_CRM_CAP_SERVICE_EJB_BINDING_NAME              = GLOBAL_PREFIX_CRM_CAP_SERVICE_EJB + "!"
                                                                               + ICrmCapServiceRemote.class.getName();
    String GLOBAL_LOCAL_CRM_CAP_SERVICE_EJB_BINDING_NAME               = GLOBAL_PREFIX_CRM_CAP_SERVICE_EJB + "!"
                                                                               + ICrmCapServiceLocal.class.getName();
    String APP_PREFIX_CRM_CAP_SERVICE_EJB                              = JAVA_APP + "/"
                                                                               + MODULE_NAME_CRM_CAP_SERVICE_EJB + "/"
                                                                               + BEAN_NAME_CRM_CAP_SERVICE_EJB;
    String APP_REMOTE_CRM_CAP_SERVICE_EJB_BINDING_NAME                 = APP_PREFIX_CRM_CAP_SERVICE_EJB + "!"
                                                                               + ICrmCapServiceRemote.class.getName();
    String APP_LOCAL_CRM_CAP_SERVICE_EJB_BINDING_NAME                  = APP_PREFIX_CRM_CAP_SERVICE_EJB + "!"
                                                                               + ICrmCapServiceLocal.class.getName();
    String MODULE_PREFIX_CRM_CAP_SERVICE_EJB                           = JAVA_MODULE + "/"
                                                                               + BEAN_NAME_CRM_CAP_SERVICE_EJB;
    String MODULE_REMOTE_CRM_CAP_SERVICE_EJB_BINDING_NAME              = MODULE_PREFIX_CRM_CAP_SERVICE_EJB + "!"
                                                                               + ICrmCapServiceRemote.class.getName();
    String MODULE_LOCAL_CRM_CAP_SERVICE_EJB_BINDING_NAME               = MODULE_PREFIX_CRM_CAP_SERVICE_EJB + "!"
                                                                               + ICrmCapServiceLocal.class.getName();
    // Finance Module EJB Bindings
    String BEAN_NAME_CRM_FINANCE_SERVICE_EJB                           = "CrmFinanceEjbService";
    String MODULE_NAME_CRM_FINANCE_SERVICE_EJB                         = "crm-finance-service";
    String GLOBAL_PREFIX_CRM_FINANCE_SERVICE_EJB                       = JAVA_GLOBAL_APPLICATION_NAME + "/"
                                                                               + MODULE_NAME_CRM_FINANCE_SERVICE_EJB
                                                                               + "/"
                                                                               + BEAN_NAME_CRM_FINANCE_SERVICE_EJB;
    String GLOBAL_REMOTE_CRM_FINANCE_SERVICE_EJB_BINDING_NAME          = GLOBAL_PREFIX_CRM_FINANCE_SERVICE_EJB
                                                                               + "!"
                                                                               + ICrmFinanceServiceRemote.class
                                                                                       .getName();
    String GLOBAL_LOCAL_CRM_FINANCE_SERVICE_EJB_BINDING_NAME           = GLOBAL_PREFIX_CRM_FINANCE_SERVICE_EJB
                                                                               + "!"
                                                                               + ICrmFinanceServiceLocal.class
                                                                                       .getName();
    String APP_PREFIX_CRM_FINANCE_SERVICE_EJB                          = JAVA_APP + "/"
                                                                               + MODULE_NAME_CRM_FINANCE_SERVICE_EJB
                                                                               + "/"
                                                                               + BEAN_NAME_CRM_FINANCE_SERVICE_EJB;
    String APP_REMOTE_CRM_FINANCE_SERVICE_EJB_BINDING_NAME             = APP_PREFIX_CRM_FINANCE_SERVICE_EJB
                                                                               + "!"
                                                                               + ICrmFinanceServiceRemote.class
                                                                                       .getName();
    String APP_LOCAL_CRM_FINANCE_SERVICE_EJB_BINDING_NAME              = APP_PREFIX_CRM_FINANCE_SERVICE_EJB
                                                                               + "!"
                                                                               + ICrmFinanceServiceLocal.class
                                                                                       .getName();
    String MODULE_PREFIX_CRM_FINANCE_SERVICE_EJB                       = JAVA_MODULE + "/"
                                                                               + BEAN_NAME_CRM_FINANCE_SERVICE_EJB;
    String MODULE_REMOTE_CRM_FINANCE_SERVICE_EJB_BINDING_NAME          = MODULE_PREFIX_CRM_FINANCE_SERVICE_EJB
                                                                               + "!"
                                                                               + ICrmFinanceServiceRemote.class
                                                                                       .getName();
    String MODULE_LOCAL_CRM_FINANCE_SERVICE_EJB_BINDING_NAME           = MODULE_PREFIX_CRM_FINANCE_SERVICE_EJB
                                                                               + "!"
                                                                               + ICrmFinanceServiceLocal.class
                                                                                       .getName();
    // QRC Module EJB Bindings
    String BEAN_NAME_CRM_QRC_SERVICE_EJB                               = "CrmQrcEjbService";
    String MODULE_NAME_CRM_QRC_SERVICE_EJB                             = "crm-qrc-service";
    String GLOBAL_PREFIX_CRM_QRC_SERVICE_EJB                           = JAVA_GLOBAL_APPLICATION_NAME + "/"
                                                                               + MODULE_NAME_CRM_QRC_SERVICE_EJB + "/"
                                                                               + BEAN_NAME_CRM_QRC_SERVICE_EJB;
    String GLOBAL_REMOTE_CRM_QRC_SERVICE_EJB_BINDING_NAME              = GLOBAL_PREFIX_CRM_QRC_SERVICE_EJB + "!"
                                                                               + ICrmQrcServiceRemote.class.getName();
    String GLOBAL_LOCAL_CRM_QRC_SERVICE_EJB_BINDING_NAME               = GLOBAL_PREFIX_CRM_QRC_SERVICE_EJB + "!"
                                                                               + ICrmQrcServiceLocal.class.getName();
    String APP_PREFIX_CRM_QRC_SERVICE_EJB                              = JAVA_APP + "/"
                                                                               + MODULE_NAME_CRM_QRC_SERVICE_EJB + "/"
                                                                               + BEAN_NAME_CRM_QRC_SERVICE_EJB;
    String APP_REMOTE_CRM_QRC_SERVICE_EJB_BINDING_NAME                 = APP_PREFIX_CRM_QRC_SERVICE_EJB + "!"
                                                                               + ICrmQrcServiceRemote.class.getName();
    String APP_LOCAL_CRM_QRC_SERVICE_EJB_BINDING_NAME                  = APP_PREFIX_CRM_QRC_SERVICE_EJB + "!"
                                                                               + ICrmQrcServiceLocal.class.getName();
    String MODULE_PREFIX_CRM_QRC_SERVICE_EJB                           = JAVA_MODULE + "/"
                                                                               + BEAN_NAME_CRM_QRC_SERVICE_EJB;
    String MODULE_REMOTE_CRM_QRC_SERVICE_EJB_BINDING_NAME              = MODULE_PREFIX_CRM_QRC_SERVICE_EJB + "!"
                                                                               + ICrmQrcServiceRemote.class.getName();
    String MODULE_LOCAL_CRM_QRC_SERVICE_EJB_BINDING_NAME               = MODULE_PREFIX_CRM_QRC_SERVICE_EJB + "!"
                                                                               + ICrmQrcServiceLocal.class.getName();
    // CRM External Trigger Module EJB Bindings
    String BEAN_NAME_CRM_EXTERNAL_TRIGGER_SERVICE_EJB                  = "CrmExternalTriggerEJBService";
    String MODULE_NAME_CRM_EXTERNAL_TRIGGER_SERVICE_EJB                = "crm-external-trigger";
    String GLOBAL_PREFIX_CRM_EXTERNAL_TRIGGER_SERVICE_EJB              = JAVA_GLOBAL_APPLICATION_NAME
                                                                               + "/"
                                                                               + MODULE_NAME_CRM_EXTERNAL_TRIGGER_SERVICE_EJB
                                                                               + "/"
                                                                               + BEAN_NAME_CRM_EXTERNAL_TRIGGER_SERVICE_EJB;
    String GLOBAL_REMOTE_CRM_EXTERNAL_TRIGGER_SERVICE_EJB_BINDING_NAME = GLOBAL_PREFIX_CRM_EXTERNAL_TRIGGER_SERVICE_EJB
                                                                               + "!"
                                                                               + ICrmExternalTriggerServiceRemote.class
                                                                                       .getName();
    String GLOBAL_LOCAL_CRM_EXTERNAL_TRIGGER_SERVICE_EJB_BINDING_NAME  = GLOBAL_PREFIX_CRM_EXTERNAL_TRIGGER_SERVICE_EJB
                                                                               + "!"
                                                                               + ICrmExternalTriggerServiceLocal.class
                                                                                       .getName();
    String APP_PREFIX_CRM_EXTERNAL_TRIGGER_SERVICE_EJB                 = JAVA_APP
                                                                               + "/"
                                                                               + MODULE_NAME_CRM_EXTERNAL_TRIGGER_SERVICE_EJB
                                                                               + "/"
                                                                               + BEAN_NAME_CRM_EXTERNAL_TRIGGER_SERVICE_EJB;
    String APP_REMOTE_CRM_EXTERNAL_TRIGGER_SERVICE_EJB_BINDING_NAME    = APP_PREFIX_CRM_EXTERNAL_TRIGGER_SERVICE_EJB
                                                                               + "!"
                                                                               + ICrmExternalTriggerServiceRemote.class
                                                                                       .getName();
    String APP_LOCAL_CRM_EXTERNAL_TRIGGER_SERVICE_EJB_BINDING_NAME     = APP_PREFIX_CRM_EXTERNAL_TRIGGER_SERVICE_EJB
                                                                               + "!"
                                                                               + ICrmExternalTriggerServiceLocal.class
                                                                                       .getName();
    String MODULE_PREFIX_CRM_EXTERNAL_TRIGGER_SERVICE_EJB              = JAVA_MODULE
                                                                               + "/"
                                                                               + BEAN_NAME_CRM_EXTERNAL_TRIGGER_SERVICE_EJB;
    String MODULE_REMOTE_CRM_EXTERNAL_TRIGGER_SERVICE_EJB_BINDING_NAME = MODULE_PREFIX_CRM_EXTERNAL_TRIGGER_SERVICE_EJB
                                                                               + "!"
                                                                               + ICrmExternalTriggerServiceRemote.class
                                                                                       .getName();
    String MODULE_LOCAL_CRM_EXTERNAL_TRIGGER_SERVICE_EJB_BINDING_NAME  = MODULE_PREFIX_CRM_EXTERNAL_TRIGGER_SERVICE_EJB
                                                                               + "!"
                                                                               + ICrmExternalTriggerServiceLocal.class
                                                                                       .getName();
    // CRM Easy Bill EJB Bindings
    String BEAN_NAME_CRM_EASY_BILL_SERVICE_EJB                         = "CrmEasyBillEJBService";
    String MODULE_NAME_CRM_EASY_BILL_SERVICE_EJB                       = "crm-easy-bill";
    String GLOBAL_PREFIX_CRM_EASY_BILL_SERVICE_EJB                     = JAVA_GLOBAL_APPLICATION_NAME + "/"
                                                                               + MODULE_NAME_CRM_EASY_BILL_SERVICE_EJB
                                                                               + "/"
                                                                               + BEAN_NAME_CRM_EASY_BILL_SERVICE_EJB;
    String GLOBAL_REMOTE_CRM_EASY_BILL_SERVICE_EJB_BINDING_NAME        = GLOBAL_PREFIX_CRM_EASY_BILL_SERVICE_EJB
                                                                               + "!"
                                                                               + ICrmEasyBillServiceRemote.class
                                                                                       .getName();
    String GLOBAL_LOCAL_CRM_EASY_BILL_SERVICE_EJB_BINDING_NAME         = GLOBAL_PREFIX_CRM_EASY_BILL_SERVICE_EJB
                                                                               + "!"
                                                                               + ICrmEasyBillServiceLocal.class
                                                                                       .getName();
    String APP_PREFIX_CRM_EASY_BILL_SERVICE_EJB                        = JAVA_APP + "/"
                                                                               + MODULE_NAME_CRM_EASY_BILL_SERVICE_EJB
                                                                               + "/"
                                                                               + BEAN_NAME_CRM_EASY_BILL_SERVICE_EJB;
    String APP_REMOTE_CRM_EASY_BILL_SERVICE_EJB_BINDING_NAME           = APP_PREFIX_CRM_EASY_BILL_SERVICE_EJB
                                                                               + "!"
                                                                               + ICrmEasyBillServiceRemote.class
                                                                                       .getName();
    String APP_LOCAL_CRM_EASY_BILL_SERVICE_EJB_BINDING_NAME            = APP_PREFIX_CRM_EASY_BILL_SERVICE_EJB
                                                                               + "!"
                                                                               + ICrmEasyBillServiceLocal.class
                                                                                       .getName();
    String MODULE_PREFIX_CRM_EASY_BILL_SERVICE_EJB                     = JAVA_MODULE + "/"
                                                                               + BEAN_NAME_CRM_EASY_BILL_SERVICE_EJB;
    String MODULE_REMOTE_CRM_EASY_BILL_SERVICE_EJB_BINDING_NAME        = MODULE_PREFIX_CRM_EASY_BILL_SERVICE_EJB
                                                                               + "!"
                                                                               + ICrmEasyBillServiceRemote.class
                                                                                       .getName();
    String MODULE_LOCAL_CRM_EASY_BILL_SERVICE_EJB_BINDING_NAME         = MODULE_PREFIX_CRM_EASY_BILL_SERVICE_EJB
                                                                               + "!"
                                                                               + ICrmEasyBillServiceLocal.class
                                                                                       .getName();
    // QRC Fault Repair Module EJB Bindings
    String BEAN_NAME_QRC_FAULT_REPAIR_SERVICE_EJB                      = "QRCFaultRepairEJBService";
    String MODULE_NAME_QRC_FAULT_REPAIR_SERVICE_EJB                    = "crm-external-trigger";
    String GLOBAL_PREFIX_QRC_FAULT_REPAIR_SERVICE_EJB                  = JAVA_GLOBAL_APPLICATION_NAME
                                                                               + "/"
                                                                               + MODULE_NAME_QRC_FAULT_REPAIR_SERVICE_EJB
                                                                               + "/"
                                                                               + BEAN_NAME_QRC_FAULT_REPAIR_SERVICE_EJB;
    String GLOBAL_REMOTE_QRC_FAULT_REPAIR_SERVICE_EJB_BINDING_NAME     = GLOBAL_PREFIX_QRC_FAULT_REPAIR_SERVICE_EJB
                                                                               + "!"
                                                                               + IFaultRepairServiceRemote.class
                                                                                       .getName();
    String GLOBAL_LOCAL_QRC_FAULT_REPAIR_SERVICE_EJB_BINDING_NAME      = GLOBAL_PREFIX_QRC_FAULT_REPAIR_SERVICE_EJB
                                                                               + "!"
                                                                               + IFaultRepairServiceLocal.class
                                                                                       .getName();
    String APP_PREFIX_QRC_FAULT_REPAIR_SERVICE_EJB                     = JAVA_APP
                                                                               + "/"
                                                                               + MODULE_NAME_QRC_FAULT_REPAIR_SERVICE_EJB
                                                                               + "/"
                                                                               + BEAN_NAME_QRC_FAULT_REPAIR_SERVICE_EJB;
    String APP_REMOTE_QRC_FAULT_REPAIR_SERVICE_EJB_BINDING_NAME        = APP_PREFIX_QRC_FAULT_REPAIR_SERVICE_EJB
                                                                               + "!"
                                                                               + IFaultRepairServiceRemote.class
                                                                                       .getName();
    String APP_LOCAL_QRC_FAULT_REPAIR_SERVICE_EJB_BINDING_NAME         = APP_PREFIX_QRC_FAULT_REPAIR_SERVICE_EJB
                                                                               + "!"
                                                                               + IFaultRepairServiceLocal.class
                                                                                       .getName();
    String MODULE_PREFIX_QRC_FAULT_REPAIR_SERVICE_EJB                  = JAVA_MODULE + "/"
                                                                               + BEAN_NAME_QRC_FAULT_REPAIR_SERVICE_EJB;
    String MODULE_REMOTE_QRC_FAULT_REPAIR_SERVICE_EJB_BINDING_NAME     = MODULE_PREFIX_QRC_FAULT_REPAIR_SERVICE_EJB
                                                                               + "!"
                                                                               + IFaultRepairServiceRemote.class
                                                                                       .getName();
    String MODULE_LOCAL_QRC_FAULT_REPAIR_SERVICE_EJB_BINDING_NAME      = MODULE_PREFIX_QRC_FAULT_REPAIR_SERVICE_EJB
                                                                               + "!"
                                                                               + IFaultRepairServiceLocal.class
                                                                                       .getName();
    // E-CAF EJB Bindings
    String BEAN_NAME_ECAF_SERVICE_EJB                                  = "ECAFEJBService";
    String MODULE_NAME_ECAF_SERVICE_EJB                                = "crm-external-trigger";
    String GLOBAL_PREFIX_ECAF_SERVICE_EJB                              = JAVA_GLOBAL_APPLICATION_NAME + "/"
                                                                               + MODULE_NAME_ECAF_SERVICE_EJB + "/"
                                                                               + BEAN_NAME_ECAF_SERVICE_EJB;
    String GLOBAL_REMOTE_ECAF_SERVICE_EJB_BINDING_NAME                 = GLOBAL_PREFIX_ECAF_SERVICE_EJB + "!"
                                                                               + IECAFServiceRemote.class.getName();
    String GLOBAL_LOCAL_ECAF_SERVICE_EJB_BINDING_NAME                  = GLOBAL_PREFIX_ECAF_SERVICE_EJB + "!"
                                                                               + IECAFServiceLocal.class.getName();
    String APP_PREFIX_ECAF_SERVICE_EJB                                 = JAVA_APP + "/" + MODULE_NAME_ECAF_SERVICE_EJB
                                                                               + "/" + BEAN_NAME_ECAF_SERVICE_EJB;
    String APP_REMOTE_ECAF_SERVICE_EJB_BINDING_NAME                    = APP_PREFIX_ECAF_SERVICE_EJB + "!"
                                                                               + IECAFServiceRemote.class.getName();
    String APP_LOCAL_ECAF_SERVICE_EJB_BINDING_NAME                     = APP_PREFIX_ECAF_SERVICE_EJB + "!"
                                                                               + IECAFServiceLocal.class.getName();
    String MODULE_PREFIX_ECAF_SERVICE_EJB                              = JAVA_MODULE + "/" + BEAN_NAME_ECAF_SERVICE_EJB;
    String MODULE_REMOTE_ECAF_SERVICE_EJB_BINDING_NAME                 = MODULE_PREFIX_ECAF_SERVICE_EJB + "!"
                                                                               + IECAFServiceRemote.class.getName();
    String MODULE_LOCAL_ECAF_SERVICE_EJB_BINDING_NAME                  = MODULE_PREFIX_ECAF_SERVICE_EJB + "!"
                                                                               + IECAFServiceLocal.class.getName();
    // Constants for various CRM Ecosystem Related Datasources
    String CRM_SERVICES_PROXY_CONFIG                                   = "crmServicesProxy.properties";
    String CRM_USER_SPRING_BEAN_XML                                    = "crm-user-spring-bean.xml";
    String CRM_ALERT_SPRING_BEAN_XML                                   = "crm-alert-spring-bean.xml";
    String CRM_MASTER_SPRING_BEAN_XML                                  = "crm-master-spring-bean.xml";
    String CRM_CONFIG_SPRING_BEAN_XML                                  = "crm-config-spring-bean.xml";
    String CRM_GIS_SPRING_BEAN_XML                                     = "crm-gis-spring-bean.xml";
    String CRM_LMS_SPRING_BEAN_XML                                     = "crm-lms-spring-bean.xml";
    String CRM_CAP_SPRING_BEAN_XML                                     = "crm-cap-spring-bean.xml";
    String CRM_FINANCE_SPRING_BEAN_XML                                 = "crm-finance-spring-bean.xml";
    String CRM_QRC_SPRING_BEAN_XML                                     = "crm-qrc-spring-bean.xml";
    String CRM_EXTERNAL_SPRING_BEAN_XML                                = "crm-external-spring-bean.xml";
    String CRM_REPORT_SPRING_BEAN_XML                                  = "crm-report-spring-bean.xml";
}
