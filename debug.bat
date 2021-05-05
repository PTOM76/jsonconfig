@echo off

rem ----------------
set name="jsonconfig"
set version="1.0.0"
rem ----------------

call build.bat
copy "target\%name%-%version%.jar" "run\plugins"