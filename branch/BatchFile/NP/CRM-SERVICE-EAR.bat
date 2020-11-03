@echo off
@set OLDDIR=%CD%
@echo CurrentDir=%OLDDIR%
@SET DEPLOY_DIR=%JBOSS_HOME%\standalone\deployments\
@echo DEPLOY_DIR=%DEPLOY_DIR% 
@set PRJDIR=%TELECRM_HOME%\CRM\crm-parent-service\
@echo PRJDIR=%PRJDIR% 
@set SERVICE_EARDIR=%TELECRM_HOME%\CRM\crm-service-ear\target\
@echo SERVICE_EARDIR=%SERVICE_EARDIR% 
@set EARDIR=%TELECRM_HOME%
@ %PROJECT_BASE_DIR%
@cd %PRJDIR%
@echo PRJDIR=%CD%
@Call mvn clean install -Denv=%BUILD_ENV% -Dclient=np -Dservicehost=%SERVICE_HOST%  -Dcrm.portal.url=%NP_PORTAL_URL% -Dcrm.myaccount.url=%NP_MYACCOUNT_URL%
@cd %SERVICE_EARDIR%
@copy *.ear %EARDIR%
@cd %EARDIR%
@copy crm-service-ear.ear %DEPLOY_DIR%
@cd %OLDDIR%
@echo CurrentDir=%CD%
pause