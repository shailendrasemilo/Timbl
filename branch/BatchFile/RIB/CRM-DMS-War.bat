@echo off
@set OLDDIR=%CD%
@echo CurrentDir=%OLDDIR%
@echo upload.dir=%UPLOAD_DIR%
pause


@set PRJDIR=%RI_BRANCH_HOME%\np-document-upload\
@echo PRJDIR=%PRJDIR% 
@set EARDIR=%RI_BRANCH_HOME%
@ %PROJECT_BASE_DIR%
@cd %PRJDIR%
@echo PRJDIR=%CD%
@Call mvn clean install -Dupload.dir=/home/document/
@cd %EARDIR%
@copy np-document-upload.war %EARDIR%
@cd %OLDDIR%
@echo CurrentDir=%CD%
pause