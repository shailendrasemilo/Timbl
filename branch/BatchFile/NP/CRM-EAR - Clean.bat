@echo off
@set OLDDIR=%CD%
@echo CurrentDir=%OLDDIR%
@SET DEPLOY_DIR=%JBOSS_HOME%\standalone\deployments\
@echo DEPLOY_DIR=%DEPLOY_DIR% 
@set PRJDIR=%TELECRM_HOME%\CRM\crm-parent-pom\
@echo PRJDIR=%PRJDIR% 
@set EARDIR=%TELECRM_HOME%
@ %PROJECT_BASE_DIR%
@echo Entered into Main-drive=%PROJECT_BASE_DIR%
@cd %PRJDIR%
@echo PRJDIR=%CD%
@Call mvn clean
@cd %OLDDIR%
@echo CurrentDir=%CD%
pause