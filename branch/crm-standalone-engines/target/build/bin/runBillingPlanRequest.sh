#!/bin/sh

BASE=`dirname $0`

CURR_DIR=`pwd`

export JAVA_HOME=/usr/java/jdk1.6.0_45	
JAVA=${JAVA_HOME}/bin/java

#change to application base directory. It should ideally be the bin folder
cd $BASE
echo "Current Directory is : ${CURR_DIR}"

DATETIME="`date '+%Y%m%d'`_`date '+%H%M%S'`"

LOG_FOLDER="../log/"${DATETIME}


#Max Heap Size in MB. The M denotes MB
MAX_MEMORY_LIMIT=1024M
echo "Setting max heap size to ${MAX_MEMORY_LIMIT} "

classpathStr=""
for file in `find ../jars | egrep '[*.jar]$|[*.zip]$'`; do
classpathStr=":$file$classpathStr";
done

#find if Billing Plan Request Engine is already running
app_pid=`cat .billingPlanRequest.app.pid`
app_run_status=`ps $app_pid | wc -l | tr -d " "`
if [ "$app_run_status" = "2" ]; then
    	echo " Billing Plan Request Engine already running!!! PID=" $app_pid
    	echo "Exiting startup!!!"
else 
	#make the log directory if it does not exist
	mkdir -p ${LOG_FOLDER}

	echo "Created Log Folder for Older Logs : ${LOG_FOLDER}"
	mv ../log/crm-billing-plan-request.log* ${LOG_FOLDER}	

	echo "Class Path is : $classpathStr "
	nohup ${JAVA} -mx${MAX_MEMORY_LIMIT}  -cp $classpathStr -Dlog4j.configuration=../config/log4j-billing-planRequest.xml com.np.tele.crm.billing.plan.request.engine.BillingPlanRequestEngine System 6 5 1> ../log/crm-billing-plan-request.log 2> ../log/stderr.log &
	BILLING_PLAN_REQ_PID=$!
	echo ${BILLING_PLAN_REQ_PID} > .billingPlanRequest.app.pid
	echo "Billing Plan Request Engine Process with PID : ${BILLING_PLAN_REQ_PID}"
	if [[ $? -ne 0 ]]; then
		echo "Error/Exception occurred. Please check the logs for details..."
	fi
fi;

#Restore the old directory
cd ${CURR_DIR}

echo "Process started..."

#
#The below statement can be used to run the program for testing on Windows
#java -mx512M  -cp ../jars/commons-dbcp-1.3.jar;../jars/commons-pool-1.5.4.jar;../jars/log4j-1.2.16.jar;../jars/mysql-connector-java-5.1.5.jar;../jars/ojdbc14-10.2.0.1.0.jar;../jars/crm-standalone-engines-1.0-SNAPSHOT -Dlog4j.configuration=../config/log4j-billing-planRequest.xml com.np.tele.crm.billing.plan.request.engine.BillingPlanRequestEngine
#

