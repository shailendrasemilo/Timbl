@echo off
@set OLDDIR=%CD%
@echo CurrentDir=%OLDDIR%
@SET DEPLOY_DIR=%JBOSS_HOME%\standalone\deployments\
@echo DEPLOY_DIR=%DEPLOY_DIR% 
@set PRJDIR=%RI_BRANCH_HOME%\crm-parent-service\
@echo PRJDIR=%PRJDIR% 
@set SERVICE_EARDIR=%RI_BRANCH_HOME%\crm-service-ear\target\
@echo SERVICE_EARDIR=%SERVICE_EARDIR% 
@set EARDIR=%RI_BRANCH_HOME%
@ %PROJECT_BASE_DIR%
@cd %PRJDIR%
@echo PRJDIR=%CD%
@Call mvn clean install -Denv=dev -Dclient=ri -Dservicehost=localhost:8080  -Dcrm.portal.url=localhost:8080/RICRM
@cd %SERVICE_EARDIR%
@copy *.ear %EARDIR%
@cd %EARDIR%
@copy crm-service-ear.ear %DEPLOY_DIR%
@cd %OLDDIR%
@echo CurrentDir=%CD%
pause