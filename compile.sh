#!/bin/bash

# Compile the Calculator Application
# Make sure mysql-connector-java-8.0.33.jar is in the same directory

echo "Compiling Java Calculator Application..."
echo ""

# Check if JAR file exists
if [ ! -f "mysql-connector-java-8.0.33.jar" ]; then
    echo "ERROR: mysql-connector-java-8.0.33.jar not found!"
    echo "Please download MySQL Connector/J and place it in this directory."
    echo "Download from: https://dev.mysql.com/downloads/connector/j/"
    exit 1
fi

# Compile all Java files
javac -cp "mysql-connector-java-8.0.33.jar" *.java

if [ $? -eq 0 ]; then
    echo ""
    echo "Compilation successful!"
    echo ""
    echo "To run the application, use:"
    echo "java -cp \"mysql-connector-java-8.0.33.jar:.\" CalculatorApp"
    echo ""
else
    echo ""
    echo "Compilation failed! Check the errors above."
    exit 1
fi
