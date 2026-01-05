@echo off
set JAVA_HOME=C:\Program Files\Eclipse Adoptium\jdk-25.0.1.8-hotspot
set JAVAC="%JAVA_HOME%\bin\javac.exe"
set JAVA="%JAVA_HOME%\bin\java.exe"

echo Compiling Healthcare Management System...
if not exist bin mkdir bin
%JAVAC% -d bin -sourcepath src src/com/healthcare/view/MainFrame.java
if %errorlevel% neq 0 (
    echo Compilation failed.
    pause
    exit /b %errorlevel%
)
echo Running Healthcare Management System...
%JAVA% -cp bin com.healthcare.view.MainFrame
pause
