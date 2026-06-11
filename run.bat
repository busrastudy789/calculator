@echo off
REM Run the compiled Calculator Application
REM Make sure you've compiled it first with compile.bat

echo Starting Calculator Application...
echo.

REM Check if main class is compiled
if not exist "CalculatorApp.class" (
    echo ERROR: CalculatorApp.class not found!
    echo Please compile the application first using compile.bat
    pause
    exit /b 1
)

REM Check if JAR file exists
if not exist "mysql-connector-java-8.0.33.jar" (
    echo ERROR: mysql-connector-java-8.0.33.jar not found!
    pause
    exit /b 1
)

REM Run the application
java -cp "mysql-connector-java-8.0.33.jar;." CalculatorApp

if %errorlevel% neq 0 (
    echo.
    echo Application encountered an error!
    pause
    exit /b 1
)
