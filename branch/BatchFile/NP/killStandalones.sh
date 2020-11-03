#!/bin/sh

#--------------------------- Date and time section ------------------------------
CURRENT_DATE_TIME=`date '+%d-%b-%Y %H:%m:%S'`

#--------------------------------------------------------------------------------
echo "-----------------Going to Stop Engines on ${CURRENT_DATE_TIME}------------------";

jps | grep 'BillingPlanRequestEngine' | awk '{print $1}' | xargs kill -9;
jps | grep 'CloseTicketEngine' |  awk '{print $1}' | xargs kill -9;
jps | grep 'ResolveTicketEngine' |  awk '{print $1}' | xargs kill -9;
jps | grep 'SlaQrcEngine' |  awk '{print $1}' | xargs kill -9;
jps | grep 'SlaLmsEngine' |  awk '{print $1}' | xargs kill -9;
jps | grep 'UploadEngine' | awk '{print $1}' | xargs kill -9;
jps | grep 'AlertsEngine' |  awk '{print $1}' | xargs kill -9;
jps | grep 'SlaInaEngine' |  awk '{print $1}' | xargs kill -9;

echo "----------------All Engines are stopped on ${CURRENT_DATE_TIME}-------------";




