@echo off
setlocal

if "%KETTLE_DIR%" == "" call "%~dp0config.bat"
call "%~dp0init.bat"

call %KETTLE_DIR%\spoonconsole.bat %*