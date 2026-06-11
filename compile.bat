@echo off
REM Compile the Calculator Application
REM Make sure mysql-connector-java-8.0.33.jar is in the same directory

echo Compiling Java Calculator Application...
echo.

REM Check if JAR file exists
if not exist "mysql-connector-java-8.0.33.jar" (
    echo ERROR: mysql-connector-java-8.0.33.jar not found!
    echo Please download MySQL Connector/J and place it in this directory.
    echo Download from: https://dev.mysql.com/downloads/connector/j/
    pause
    exit /b 1
)

REM Compile all Java files
javac -cp "mysql-connector-java-8.0.33.jar" *.java

if %errorlevel% equ 0 (
    echo.
    echo Compilation successful!
    echo.
    echo To run the application, use:
    echo java -cp "mysql-connector-java-8.0.33.jar;." CalculatorApp
    echo.
    pause
) else (
    echo.
    echo Compilation failed! Check the errors above.
    pause
    exit /b 1
)
