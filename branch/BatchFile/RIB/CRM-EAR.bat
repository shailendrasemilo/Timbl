@echo off
@set OLDDIR=%CD%
@echo CurrentDir=%OLDDIR%
@set PRJDIR=%RI_BRANCH_HOME%\crm-parent-pom\
@echo PRJDIR=%PRJDIR% 

@set CLIENT_EARDIR=%RI_BRANCH_HOME%\crm-client-ear\target\
@echo CLIENT_EARDIR=%CLIENT_EARDIR% 
@set SERVICE_EARDIR=%RI_BRANCH_HOME%\crm-service-ear\target\
@echo SERVICE_EARDIR=%SERVICE_EARDIR% 
@set EARDIR=%RI_BRANCH_HOME%
@ %PROJECT_BASE_DIR%
@cd %PRJDIR%
@echo PRJDIR=%CD%
@Call mvn clean install -Denv=staging -Dclient=ri -Dservicehost=192.168.2.31:8080 -Ddmshost=http://192.168.2.31:8080 -Dcrm.myaccount.url=timbl.co.in
@cd %CLIENT_EARDIR%
@copy *.ear %EARDIR%
@cd %SERVICE_EARDIR%
@copy *.ear %EARDIR%
@cd %EARDIR%
@cd %OLDDIR%
@echo CurrentDir=%CD%
pause