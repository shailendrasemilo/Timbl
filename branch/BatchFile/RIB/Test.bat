@ECHO OFF
ECHO.


FOR /F "TOKENS=2* DELIMS=:" %%A IN ('IPCONFIG ^| FIND "IPv4"') DO FOR %%B IN (%%A) DO SET IPADDR=%%B
ECHO %IPADDR%

@set _date=%DATE:/=-%
@ECHO %_date%
pause
:End