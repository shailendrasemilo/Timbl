@echo off
@set OLDDIR=%CD%
@echo CurrentDir=%OLDDIR%
@SET DEPLOY_DIR=%JBOSS_HOME%\standalone\deployments\
@echo DEPLOY_DIR=%DEPLOY_DIR% 
@set PRJDIR=%TELECRM_HOME%\CRM\crm-parent-client\
@echo PRJDIR=%PRJDIR% 
@set CLIENT_EARDIR=%TELECRM_HOME%\CRM\crm-client-ear\target\
@echo CLIENT_EARDIR=%CLIENT_EARDIR% 
@set MYACCOUNT_EARDIR=%TELECRM_HOME%\CRM\crm-selfcare-ear\target\
@echo MYACCOUNT_EARDIR=%MYACCOUNT_EARDIR%
@set EARDIR=%TELECRM_HOME%
@ %PROJECT_BASE_DIR%
@cd %PRJDIR%
@echo PRJDIR=%CD%
@Call mvn clean install -Denv=%BUILD_ENV% -Dclient=np -Dservicehost=%SERVICE_HOST%
@cd %CLIENT_EARDIR%
@copy *.ear %EARDIR%
@cd %MYACCOUNT_EARDIR%
@copy *.ear %EARDIR%
@cd %EARDIR%
@copy crm-client-ear.ear %DEPLOY_DIR%
@copy crm-selfcare-ear.ear %DEPLOY_DIR%
@cd %OLDDIR%
@echo CurrentDir=%CD%
pause