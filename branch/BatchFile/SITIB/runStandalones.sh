#!/bin/sh

#--------------------------- Date and time section ------------------------------
CURRENT_DATE_TIME=`date '+%d-%b-%Y %H:%m:%S'`
CURRENT_DATE=`date '+%d-%b-%Y'`

#---------------------------- Local paths for copy----------------------------------------
SOURCE_PATH="/home/crmclnt/nextra-standalone-apps/builds/${CURRENT_DATE}/target/jars/";
DEST_CRM_ALERT_ENGINE="crm-alerts-engine/target/jars/";
DEST_CRM_LMS_SLA="crm-lms-sla/target/jars/";
DEST_CRM_QRC_SLA="crm-qrc-sla/target/jars/";
DEST_CRM_TICKET_RESOLVE_ENGINE="crm-ticket-resolve-engine/target/jars/";
DEST_CRM_BILL_CYCLE="crm-bill-cycle/target/jars/";
DEST_CRM_INA_SLA="crm-ina-sla/target/jars/";
DEST_PLAN_UPDATE="crm-plan-update/target/jars/";
DEST_CRM_TICKET_CLOSE_ENGINE="crm-ticket-close-engine/target/jars/";

#---------------------------- Local paths for startup----------------------------------------
START_CRM_ALERT_ENGINE="crm-alerts-engine/target/bin/runAlertEngine.sh";
START_CRM_LMS_SLA="crm-lms-sla/target/bin/runLmsSla.sh";
START_CRM_QRC_SLA="crm-qrc-sla/target/bin/runQrcSla.sh";
START_CRM_TICKET_RESOLVE_ENGINE="crm-ticket-resolve-engine/target/bin/runResolveTicket.sh";
START_CRM_BILL_CYCLE="crm-bill-cycle/target/bin/runChangeBiilCycle.sh";
START_CRM_INA_SLA="crm-ina-sla/target/bin/runInaSla.sh";
START_PLAN_UPDATE="crm-plan-update/target/bin/runBillingPlanRequest.sh";
START_CRM_TICKET_CLOSE_ENGINE="crm-ticket-close-engine/target/bin/runCloseTicket.sh";

echo "--------------------------- Going to copy and start on ${CURRENT_DATE_TIME} ------------------------------";
cp ${SOURCE_PATH}* ${DEST_CRM_ALERT_ENGINE};
./${START_CRM_ALERT_ENGINE};

cp ${SOURCE_PATH}* ${DEST_CRM_LMS_SLA};
./${START_CRM_LMS_SLA};

cp ${SOURCE_PATH}* ${DEST_CRM_QRC_SLA};
./${START_CRM_QRC_SLA};

cp ${SOURCE_PATH}* ${DEST_CRM_TICKET_RESOLVE_ENGINE};
./${START_CRM_TICKET_RESOLVE_ENGINE};

cp ${SOURCE_PATH}* ${DEST_CRM_BILL_CYCLE};
./${START_CRM_BILL_CYCLE};

cp ${SOURCE_PATH}* ${DEST_CRM_INA_SLA};
./${START_CRM_INA_SLA};

cp ${SOURCE_PATH}* ${DEST_PLAN_UPDATE};
./${START_PLAN_UPDATE};

cp ${SOURCE_PATH}* ${DEST_CRM_TICKET_CLOSE_ENGINE};
./${START_CRM_TICKET_CLOSE_ENGINE};

echo "--------------------------- All Engines are started on ${CURRENT_DATE_TIME} ------------------------------";
 