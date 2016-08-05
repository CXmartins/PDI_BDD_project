@echo off
setlocal

:: configuration
set BASEDIR=%~dp0
call %BASEDIR%\config.bat

call %BASEDIR%\kitchen.bat /file:"%KETTLE_HOME%\CXA_init_setup.kjb"
pause
endlocal
