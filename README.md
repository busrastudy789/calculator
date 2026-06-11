# Java Swing Calculator - Setup Guide

## Features
✅ User Login & Registration with MySQL Database
✅ General Calculator Mode (Basic operations)
✅ Scientific Calculator Mode (Advanced functions)
✅ Memory Operations (M+, M-, MR)
✅ Inverse Functions (Sin⁻¹, Cos⁻¹, Tan⁻¹, etc.)
✅ Degree/Radian Toggle
✅ Answer History (Ans button)

## Prerequisites
- Java Development Kit (JDK 8 or higher)
- XAMPP (for MySQL Database)
- MySQL JDBC Driver

## Step 1: Set up XAMPP and Database

### 1.1 Install XAMPP
- Download XAMPP from: https://www.apachefriends.org/
- Install and run XAMPP Control Panel
- Start Apache and MySQL services

### 1.2 Create Database
- Open phpMyAdmin: http://localhost/phpmyadmin
- Create new database named: `calculator_db`
- The users table will be created automatically on first run

## Step 2: Download MySQL JDBC Driver

1. Download MySQL Connector/J from:
   https://dev.mysql.com/downloads/connector/j/

2. Extract the downloaded file and locate `mysql-connector-java-X.X.X.jar`

3. Copy it to your project directory or add to Java classpath

## Step 3: Compile the Application

### Option 1: Without JDBC in classpath
```bash
# Download the driver first
javac -cp "mysql-connector-java-8.0.33.jar" *.java
```

### Option 2: Add driver to classpath permanently
- Extract mysql-connector-java-X.X.X.jar
- Copy the JAR file to: `C:\Program Files\Java\jdk1.X.X_XXX\lib\ext` (Windows)
- Or: `/Library/Java/JavaVirtualMachines/jdkX.X.X_XXX/Contents/Home/lib` (Mac)

Then compile with:
```bash
javac *.java
```

## Step 4: Run the Application

```bash
java -cp "mysql-connector-java-8.0.33.jar:." CalculatorApp
```

Or if JDBC driver is in Java lib/ext folder:
```bash
java CalculatorApp
```

## Database Connection Details

If you need to change database settings, edit `DatabaseHelper.java`:

```java
private static final String URL = "jdbc:mysql://localhost:3306/calculator_db";
private static final String USER = "root";      // XAMPP default
private static final String PASSWORD = "";      // XAMPP default is empty
```

## Default Credentials (Create your own on first run)
- Use the "Register" button to create a new account
- Minimum username: 3 characters
- Minimum password: 4 characters

## File Structure

```
CalculatorApp.java           - Main application entry point
LoginFrame.java              - Login & Registration GUI
CalculatorFrame.java         - Main calculator interface
CalculatorLogic.java         - Calculation engine
DatabaseHelper.java          - MySQL connection & authentication
```

## Calculator Modes

### General Mode Buttons
```
AC    CE    Ans   ÷     
7     8     9     ×     M+
4     5     6     -     M-
1     2     3     +     RND
0     .     =     
```

### Scientific Mode Buttons
```
Deg   Rad   Inv   x!    sin   cos
tan   ln    log   √     x^y   EXP
π     Ans   e^x   10^x  x²    ⁿ√x
Rnd   (     )     %     CE    AC
÷     ×     -     +     
7     8     9
4     5     6
1     2     3     0     .     =
```

## Features Explanation

### General Mode
- Basic arithmetic operations (+, -, ×, ÷)
- Memory functions (M+, M-, stores values)
- Ans button (recalls last answer)
- Random number generator
- Clear Entry (CE) and All Clear (AC)

### Scientific Mode
- Trigonometric: sin, cos, tan
- Logarithmic: log (base 10), ln (natural log)
- Powers: x^y, x², √x, ⁿ√x
- Factorial (x!)
- Inverse functions (press Inv button)
- Degree/Radian toggle
- Constants: π (pi), e (Euler's number)
- Parentheses for complex calculations

### Inverse Functions (when Inv is active)
- sin → sin⁻¹ (arcsine)
- cos → cos⁻¹ (arccosine)
- tan → tan⁻¹ (arctangent)
- ln → e^x (exponential)
- log → 10^x (power of 10)

## Troubleshooting

### "Connection refused" error
- Make sure XAMPP MySQL is running
- Check that calculator_db exists in phpMyAdmin

### "ClassNotFoundException: com.mysql.cj.jdbc.Driver"
- Ensure mysql-connector-java JAR is in classpath
- Check JDBC driver version compatibility

### "Access denied for user 'root'"
- Verify your XAMPP MySQL password (usually empty)
- Check DatabaseHelper.java credentials match your setup

### Calculator shows "0" on startup
- This is normal, just start entering numbers

## Tips & Tricks
- Press Enter key while typing username/password to login
- Use Inv button before trig functions to get inverse results
- Toggle between Deg/Rad for different angle calculations
- Memory functions accumulate (M+ adds, M- subtracts)
- Power calculations: Enter base, press x^y, enter exponent, press =

## Security Note
⚠️ This is a learning project. For production use:
- Use password hashing (bcrypt, SHA-256)
- Implement SQL injection prevention
- Add HTTPS for network transmission
- Use prepared statements (already implemented)

## Known Limitations
- Calculator doesn't support parentheses nesting in general mode
- Some edge cases with very large numbers
- No graphing capabilities

Enjoy your calculator! 🧮
