@echo off
@set OLDDIR=%CD%
@echo CurrentDir=%OLDDIR%
@SET DEPLOY_DIR=%JBOSS_HOME%\standalone\deployments\
@echo DEPLOY_DIR=%DEPLOY_DIR% 
@set PRJDIR=%RI_BRANCH_HOME%\crm-parent-client\
@echo PRJDIR=%PRJDIR% 
@set CLIENT_EARDIR=%RI_BRANCH_HOME%\crm-selfcare-ear\target\
@echo CLIENT_EARDIR=%CLIENT_EARDIR% 
@set EARDIR=%RI_BRANCH_HOME%
@ %PROJECT_BASE_DIR%
@cd %PRJDIR%
@echo PRJDIR=%CD%
@Call mvn clean install -Denv=%BUILD_ENV% -Dclient=ri -Dservicehost=%SERVICE_HOST%
@cd %CLIENT_EARDIR%
@copy *.ear %EARDIR%
@cd %EARDIR%
@copy nextra-selfcare-ear.ear %DEPLOY_DIR%
@cd %OLDDIR%
@echo CurrentDir=%CD%
pause