@echo off
@set OLDDIR=%CD%
@echo CurrentDir=%OLDDIR%
@FOR /F "TOKENS=2* DELIMS=:" %%A IN ('IPCONFIG ^| FINDSTR /C:"IP Address" /C:"IPv4"') DO FOR %%B IN (%%A) DO SET IPADDR=%%B
@set IPADDR=%IPADDR%:8080
@echo %IPADDR%
pause
@echo CurrentDir=%OLDDIR%
@set MainDrive=%PROJECT_BASE_DIR%
@set PRJDIR=%RI_BRANCH_HOME%\crm-soa-client\src\main\java\
@set PACKAGE_DIR=com\np\tele\crm\service\client\
@set PACKAGE=com.np.tele.crm.service.client
@set MASTER_WSDL=http://%IPADDR%/crm-master-service/MasterDataService?wsdl
@set USER_WSDL=http://%IPADDR%/crm-user-mngmt-service/UserManagementService?wsdl
@set ALERTS_WSDL=http://%IPADDR%/crm-alert-service/AlertsService?wsdl
@set CONFIG_WSDL=http://%IPADDR%/crm-config-service/CRMConfigService?wsdl
@set GIS_WSDL=http://%IPADDR%/crm-gis-service/GISService?wsdl
@set LMS_WSDL=http://%IPADDR%/crm-lms-service/LMSService?wsdl
@set CAP_WSDL=http://%IPADDR%/crm-cap-service/CrmCapService?wsdl
@set FINANCE_WSDL=http://%IPADDR%/crm-finance-service/CrmFinanceService?wsdl
@set QRC_WSDL=http://%IPADDR%/crm-qrc-service/CrmQrcService?wsdl
@set REPORT_WSDL=http://%IPADDR%/crm-report-service/CRMReportService?wsdl
@echo Entered into Main-drive=%MainDrive%
@echo URL=%WSDL%
@%MainDrive%
@cd %PRJDIR%
@echo CurrentDir=%CD%
@echo Exceuting %MASTER_WSDL%
@Call wsimport -extension -keep -p %PACKAGE% -XadditionalHeaders %MASTER_WSDL%
@echo Exceuting %USER_WSDL%
@Call wsimport -extension -keep -p %PACKAGE% -XadditionalHeaders %USER_WSDL%
@echo Exceuting %ALERTS_WSDL%
@Call wsimport -extension -keep -p %PACKAGE% -XadditionalHeaders %ALERTS_WSDL%
@echo Exceuting %CONFIG_WSDL%
@Call wsimport -extension -keep -p %PACKAGE% -XadditionalHeaders %CONFIG_WSDL%
@echo Exceuting %GIS_WSDL%
@Call wsimport -extension -keep -p %PACKAGE% -XadditionalHeaders %GIS_WSDL%
@echo Exceuting %LMS_WSDL%
@Call wsimport -extension -keep -p %PACKAGE% -XadditionalHeaders %LMS_WSDL%
@echo Exceuting %CAP_WSDL%
@Call wsimport -extension -keep -p %PACKAGE% -XadditionalHeaders %CAP_WSDL%
@echo Exceuting %FINANCE_WSDL%
@Call wsimport -extension -keep -p %PACKAGE% -XadditionalHeaders %FINANCE_WSDL%
@echo Exceuting %QRC_WSDL%
@Call wsimport -extension -keep -p %PACKAGE% -XadditionalHeaders %QRC_WSDL%
@echo Exceuting %REPORT_WSDL%
@Call wsimport -extension -keep -p %PACKAGE% -XadditionalHeaders %REPORT_WSDL%
@cd %PACKAGE_DIR%
@del *.class
@cd %OLDDIR%
@echo CurrentDir=%CD%
pause