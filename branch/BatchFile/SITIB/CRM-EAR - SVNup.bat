@echo off
@set OLDDIR=%CD%
@echo CurrentDir=%OLDDIR%
@SET DEPLOY_DIR=%JBOSS_HOME%\standalone\deployments\
@echo DEPLOY_DIR=%DEPLOY_DIR% 
@set SVNDIR=%RI_TRUNK_HOME%\CRM\
@set PRJDIR=%RI_TRUNK_HOME%\CRM\crm-parent-pom\
@echo PRJDIR=%PRJDIR% 
@set CLIENT_EARDIR=%RI_TRUNK_HOME%\CRM\crm-client-ear\target\
@echo CLIENT_EARDIR=%CLIENT_EARDIR% 
@set SERVICE_EARDIR=%RI_TRUNK_HOME%\CRM\crm-service-ear\target\
@echo SERVICE_EARDIR=%SERVICE_EARDIR% 
@set EARDIR=%RI_TRUNK_HOME%
@ %PROJECT_BASE_DIR%
@cd %SVNDIR%
@svn up
@echo Entered into Main-drive=%PROJECT_BASE_DIR%
@cd %PRJDIR%
@echo PRJDIR=%CD%
@Call mvn clean install -Denv=%BUILD_ENV%
@cd %CLIENT_EARDIR%
@copy *.ear %EARDIR%
@cd %SERVICE_EARDIR%
@copy *.ear %EARDIR%
@cd %EARDIR%
@copy *.ear %DEPLOY_DIR%
@cd %OLDDIR%
@echo CurrentDir=%CD%
pause