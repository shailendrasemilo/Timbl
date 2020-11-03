@echo off
@set OLDDIR=%CD%
@echo CurrentDir=%OLDDIR%
@set IPADDR=103.14.124.21:8082
@set IPADDR=10.10.0.21:8082
@set MainDrive=%PROJECT_BASE_DIR%
@set PRJDIR=%RI_BRANCH_HOME%\crm-billing-client\src\main\java\
@set PACKAGE_DIR=com\np\tele\crm\billing\client\
@set PACKAGE=com.np.tele.crm.billing.client
@set WSDL=http://%IPADDR%/Api/Customer.svc?wsdl
@echo Entered into Main-drive=%MainDrive%
@%MainDrive%
@cd %PRJDIR%
@echo CurrentDir=%CD%
@Call wsimport -extension -keep -XadditionalHeaders %WSDL%
@del *.class /S /Q
@cd %OLDDIR%
@echo CurrentDir=%CD%
@echo URL=" + PropertyUtils.getServiceDetails( IAppConstants.CRM_BILING_CLIENT ) + "
pause

