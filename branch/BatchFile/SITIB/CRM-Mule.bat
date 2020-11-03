@echo off
@set OLDDIR=%CD%
@echo CurrentDir=%OLDDIR%
@set CMS_EMAIL_TO_FTP_DIR=%TELECRM_HOME%\CRM\nextra-mule-projects\nextra-cms-email-to-ftp\
@echo CMS_EMAIL_TO_FTP_DIR=%CMS_EMAIL_TO_FTP_DIR% 
@set NEXTRA_PROXY_DIR=%TELECRM_HOME%\CRM\nextra-mule-projects\nextra-ws-proxy\
@echo NEXTRA_PROXY_DIR=%NEXTRA_PROXY_DIR%
@set _date=%DATE:/=-%
@ECHO %_date%
@SET EARDIR=E:\Official\Anurag\Delivery\Nextra\UAT\%_date%
@echo EARDIR=%EARDIR% 
@mkdir %EARDIR%
pause
@ %PROJECT_BASE_DIR%
@echo Entered into Main-drive=%PROJECT_BASE_DIR%

@cd %CMS_EMAIL_TO_FTP_DIR%
@echo CMS_EMAIL_TO_FTP_DIR=%CD%
@Call mvn clean install
@cd %CMS_EMAIL_TO_FTP_DIR%\target\
@copy *.zip %EARDIR%
pause
@cd %NEXTRA_PROXY_DIR%
@echo NEXTRA_PROXY_DIR=%CD%
@Call mvn clean install
@cd %NEXTRA_PROXY_DIR%\target\
@copy *.zip %EARDIR%
pause
@cd %OLDDIR%
@echo CurrentDir=%CD%
pause



