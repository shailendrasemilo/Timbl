@echo off
@set OLDDIR=%CD%
@echo CurrentDir=%OLDDIR%
@SET DEPLOY_DIR=%JBOSS_HOME%\standalone\deployments\
@echo DEPLOY_DIR=%DEPLOY_DIR% 
@set PRJDIR=%RI_BRANCH_HOME%\crm-parent-client\
@echo PRJDIR=%PRJDIR% 
@set CLIENT_EARDIR=%RI_BRANCH_HOME%\crm-client-ear\target\
@echo CLIENT_EARDIR=%CLIENT_EARDIR% 
@set EARDIR=%RI_BRANCH_HOME%
@ %PROJECT_BASE_DIR%
@cd %PRJDIR%
@echo PRJDIR=%CD%
@Call mvn clean install -Denv=dev -Dclient=ri -Dservicehost=localhost:8080 -Ddmshost=http://192.168.1.106
@cd %CLIENT_EARDIR%
@copy *.ear %EARDIR%
@cd %EARDIR%
@copy crm-client-ear.ear %DEPLOY_DIR%
@cd %OLDDIR%
@echo CurrentDir=%CD%
pause