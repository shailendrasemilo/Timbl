@echo off
@set OLDDIR=%CD%
@FOR /F "TOKENS=2* DELIMS=:" %%A IN ('IPCONFIG ^| FINDSTR /C:"IP Address" /C:"IPv4"') DO FOR %%B IN (%%A) DO SET IPADDR=%%B
@set IPADDR=%IPADDR%:8080
@echo %IPADDR%
pause
@echo CurrentDir=%OLDDIR%
@set MainDrive=%PROJECT_BASE_DIR%
@set PRJDIR=%TELECRM_HOME%\CRM\crm-soa-client\src\main\java\
@set PACKAGE_DIR=com\np\tele\crm\service\client\
@set PACKAGE=com.np.tele.crm.service.client
@set WSDL=http://%IPADDR%/crm-lms-service/LMSService?wsdl
@echo Entered into Main-drive=%MainDrive%
@echo URL=%WSDL%
@%MainDrive%
@cd %PRJDIR%
@echo CurrentDir=%CD%
@Call wsimport -extension -keep -p %PACKAGE% -XadditionalHeaders %WSDL%
@cd %PACKAGE_DIR%
@del *.class
@cd %OLDDIR%
@echo CurrentDir=%CD%
@echo URL=" + PropertyUtils.getServiceDetails( IAppConstants.CRM_LMS_CLIENT )+ "
pause

