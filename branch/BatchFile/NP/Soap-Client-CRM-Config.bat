@echo off
@set OLDDIR=%CD%
@echo CurrentDir=%OLDDIR%
@FOR /F "TOKENS=2* DELIMS=:" %%A IN ('IPCONFIG ^| FINDSTR /C:"IP Address" /C:"IPv4"') DO FOR %%B IN (%%A) DO SET IPADDR=%%B
@set IPADDR=%IPADDR%:8080
@echo %IPADDR%
pause
@set MainDrive=%PROJECT_BASE_DIR%
@set PRJDIR=%TELECRM_HOME%\CRM\crm-soa-client\src\main\java\
@set PACKAGE_DIR=com\np\tele\crm\service\client\
@set PACKAGE=com.np.tele.crm.service.client
@set WSDL=http://%IPADDR%/crm-config-service/CRMConfigService?wsdl
@echo Entered into Main-drive=%MainDrive%
@%MainDrive%
@cd %PRJDIR%
@echo CurrentDir=%CD%
@Call wsimport -extension -keep -p %PACKAGE% -XadditionalHeaders %WSDL%
@cd %PACKAGE_DIR%
@del *.class
@cd %OLDDIR%
@echo CurrentDir=%CD%
@echo URL=" + PropertyUtils.getServiceDetails( IAppConstants.CRM_CONFIG_CLIENT ) + "
pause

