@echo off
@set OLDDIR=%CD%
@echo CurrentDir=%OLDDIR%
@set MainDrive=E:
@set DEPLOY_DIR=E:\Official\Anurag\Tools\Servers\JBoss7.1.1.Final\standalone\deployments\
@set PRJDIR=E:\Official\Anurag\CodeBase\Nextra\Nextra-Trunk\CRM\crm-parent-pom\
@set CLIENT_EARDIR=E:\Official\Anurag\CodeBase\Nextra\Nextra-Trunk\CRM\crm-client-ear\target\
@set SERVICE_EARDIR=E:\Official\Anurag\CodeBase\Nextra\Nextra-Trunk\CRM\crm-service-ear\target\
@set EARDIR=E:\Official\Anurag\CodeBase\Nextra\Nextra-Trunk\
@echo Entered into Main-drive=%MainDrive%
@%MainDrive%
@cd %PRJDIR%
@echo CurrentDir=%CD%
@Call mvn clean install -Denv=qa -Dclient=np -Dservicehost=%SERVICE_HOST%
@cd %CLIENT_EARDIR%
@copy *.ear %EARDIR%
@cd %SERVICE_EARDIR%
@copy *.ear %EARDIR%
@cd %EARDIR%
@copy *.ear %DEPLOY_DIR%
@cd %OLDDIR%
@echo CurrentDir=%CD%