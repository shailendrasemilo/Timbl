#!/bin/sh

BASE=`dirname $0`

CURR_DIR=`pwd`

export JAVA_HOME=/usr/java/jdk1.6.0_37	
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

#find if CMS Upload is already running
app_pid=`cat .cmsUpload.app.pid`
app_run_status=`ps $app_pid | wc -l | tr -d " "`
if [ "$app_run_status" = "2" ]; then
    	echo " CMS Upload already running!!! PID=" $app_pid
    	echo "Exiting startup!!!"
else 
	#make the log directory if it does not exist
	mkdir -p ${LOG_FOLDER}

	echo "Created Log Folder for Older Logs : ${LOG_FOLDER}"
	mv ../log/cmsUpload.log* ${LOG_FOLDER}	

	echo "Class Path is : $classpathStr "
	nohup ${JAVA} -mx${MAX_MEMORY_LIMIT}  -cp $classpathStr -Dlog4j.configuration=../config/log4j.xml -Dappconfig.properties=../config/AppProperties.properties com.np.tele.crm.read.engine.UploadEngine 1> ../log/stdout.log 2> ../log/stderr.log &
	CMS_UPLOAD_PID=$!
	echo ${CMS_UPLOAD_PID} > .cmsUpload.app.pid
	echo "CMS Upload Process with PID : ${CMS_UPLOAD_PID}"
	if [[ $? -ne 0 ]]; then
		echo "Error/Exception occurred. Please check the logs for details..."
	fi
fi;

#Restore the old directory
cd ${CURR_DIR}

echo "Process started..."

#
#The below statement can be used to run the program for testing on Windows
#java -mx512M  -cp ../jars/commons-dbcp-1.3.jar;../jars/commons-pool-1.5.4.jar;../jars/log4j-1.2.16.jar;../jars/mysql-connector-java-5.1.5.jar;../jars/ojdbc14-10.2.0.1.0.jar;../jars/crm-cms-upload-1.0-SNAPSHOT -Dlog4j.configuration=../config/log4j.xml -Dappconfig.properties=../config/AppProperties.properties com.np.tele.crm.read.engine.UploadEngine
#

