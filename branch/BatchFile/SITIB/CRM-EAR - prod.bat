@echo off
@set OLDDIR=%CD%
@echo CurrentDir=%OLDDIR%
@set _date=%DATE:/=-%
@ECHO %_date%
@SET SERVICE_EAR_DIR=E:\Official\Anurag\Delivery\Nextra\UAT\crmsvr\%_date%
@SET CLIENT_EAR_DIR=E:\Official\Anurag\Delivery\Nextra\UAT\crmclnt\%_date%
@SET STANDALONE_EAR_DIR=E:\Official\Anurag\Delivery\Nextra\UAT\standalone\%_date%
@echo SERVICE_EAR_DIR=%SERVICE_EAR_DIR% 
@mkdir %SERVICE_EAR_DIR%
@echo CLIENT_EAR_DIR=%CLIENT_EAR_DIR% 
@mkdir %CLIENT_EAR_DIR%
@echo STANDALONE_EAR_DIR=%STANDALONE_EAR_DIR% 
@mkdir %STANDALONE_EAR_DIR%
pause
@set PRJDIR=%RI_TRUNK_HOME%\CRM\crm-parent-pom\
@echo PRJDIR=%PRJDIR% 
@set CLIENT_EARDIR=%RI_TRUNK_HOME%\CRM\crm-client-ear\target\
@echo CLIENT_EARDIR=%CLIENT_EARDIR% 
@set SERVICE_EARDIR=%RI_TRUNK_HOME%\CRM\crm-service-ear\target\
@echo SERVICE_EARDIR=%SERVICE_EARDIR% 
@set MYACCOUNT_EARDIR=%RI_TRUNK_HOME%\CRM\crm-selfcare-ear\target\
@echo MYACCOUNT_EARDIR=%MYACCOUNT_EARDIR% 
@set STANDALONE_EARDIR=%RI_TRUNK_HOME%\CRM\crm-standalone-engines\target\
@echo STANDALONE_EARDIR=%STANDALONE_EARDIR% 
@set CMS_UPLOAD_EARDIR=%RI_TRUNK_HOME%\CRM\crm-cms-upload\target\
@echo CMS_UPLOAD_EARDIR=%CMS_UPLOAD_EARDIR% 
@set EARDIR=%RI_TRUNK_HOME%
@ %PROJECT_BASE_DIR%
@echo Entered into Main-drive=%PROJECT_BASE_DIR%
@cd %PRJDIR%
@echo PRJDIR=%CD%
@Call mvn clean install -Denv=prod -Dclient=siti -Dservicehost=%SERVICE_HOST%
@cd %CLIENT_EARDIR%
@copy *.ear %CLIENT_EAR_DIR%
@cd %SERVICE_EARDIR%
@copy *.ear %SERVICE_EAR_DIR%
@cd %MYACCOUNT_EARDIR%
@copy *.ear %CLIENT_EAR_DIR%
@cd %STANDALONE_EARDIR%
@copy *.tar.gz %STANDALONE_EAR_DIR%
@cd %CMS_UPLOAD_EARDIR%
@copy *.tar.gz %STANDALONE_EAR_DIR%
@cd %OLDDIR%
@echo CurrentDir=%CD%
pause