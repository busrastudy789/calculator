# Quick Start Guide - Java Calculator

## Prerequisites Check
Before starting, ensure you have:
- ✅ Java JDK 8 or higher installed
- ✅ XAMPP installed
- ✅ All 5 Java files (.java files)
- ✅ MySQL Connector/J JAR file

## Step-by-Step Setup

### STEP 1: Verify Java Installation
Open Command Prompt/Terminal and type:
```
java -version
```
You should see Java version output. If not, install JDK from oracle.com

### STEP 2: Start XAMPP Services
1. Open XAMPP Control Panel
2. Click "Start" next to Apache
3. Click "Start" next to MySQL
4. Wait for both to show "Running"

### STEP 3: Verify MySQL Connection
Open browser and go to:
```
http://localhost/phpmyadmin
```
You should see phpMyAdmin interface (green checkmark = MySQL is running)

### STEP 4: Create Database
In phpMyAdmin:
1. Click "New" or "Create database"
2. Database name: `calculator_db`
3. Click "Create"
4. The users table will auto-create on first app run

### STEP 5: Download MySQL JDBC Driver
1. Visit: https://dev.mysql.com/downloads/connector/j/
2. Download "mysql-connector-java-8.0.33.jar" (Platform Independent)
3. Extract the ZIP file
4. Copy `mysql-connector-java-8.0.33.jar` to your project folder

### STEP 6: Compile the Application

**Windows Users:**
```
compile.bat
```

**Linux/Mac Users:**
```
chmod +x compile.sh
./compile.sh
```

**Manual Compilation:**
```
javac -cp "mysql-connector-java-8.0.33.jar" *.java
```

### STEP 7: Run the Application

**Windows:**
```
java -cp "mysql-connector-java-8.0.33.jar;." CalculatorApp
```

**Linux/Mac:**
```
java -cp "mysql-connector-java-8.0.33.jar:." CalculatorApp
```

Or use the run scripts if compiled successfully.

## First Time Using the App

1. **Login window appears** ✅
2. **Register tab:** Create a new account
   - Username: At least 3 characters
   - Password: At least 4 characters
3. **Login:** Use your new credentials
4. **Calculator appears** ✅
5. Choose between "General" or "Scientific" mode

## Troubleshooting

### "Database Error" / Connection Refused
**Problem:** XAMPP MySQL not running
**Solution:** 
- Open XAMPP Control Panel
- Make sure MySQL shows "Running"
- Restart MySQL if needed

### "ClassNotFoundException: com.mysql.cj.jdbc.Driver"
**Problem:** JAR file not found
**Solution:**
- Check mysql-connector-java-8.0.33.jar is in project folder
- Verify filename exactly matches command

### "Invalid Credentials" but just registered
**Problem:** Database not created yet
**Solution:**
- Create database manually in phpMyAdmin
- Database name must be: `calculator_db`

### Port 3306 already in use
**Problem:** Another MySQL instance running
**Solution:**
- Stop other MySQL services
- Or change port in DatabaseHelper.java

## Project Files Overview

| File | Purpose |
|------|---------|
| CalculatorApp.java | Starts the application |
| LoginFrame.java | Login/Register window |
| CalculatorFrame.java | Main calculator GUI |
| CalculatorLogic.java | All calculations |
| DatabaseHelper.java | Database connection |
| README.md | Full documentation |
| setup.sql | Database initialization |
| compile.bat/sh | Compilation script |
| run.bat | Run script |

## Calculator Usage

### General Mode
- Basic math: +, -, ×, ÷
- Memory: M+ (add), M- (subtract)
- Functions: √, Ans (last answer), RND (random)

### Scientific Mode
- Trig: sin, cos, tan (toggle Deg/Rad)
- Log: ln (natural), log (base 10)
- Power: x^y, x², √, ⁿ√x
- Special: x!, π, e, %
- Inverse: Click "Inv" to toggle sin⁻¹, cos⁻¹, etc.

## Common Issues & Solutions

| Issue | Solution |
|-------|----------|
| "Error: port 3306" | MySQL not running, start XAMPP |
| Blank calculator window | Wait a moment, app is loading |
| Numbers not appearing | Click on calculator window first |
| "Authentication failed" | Wrong username/password, try again |
| App crashes on launch | Ensure all 5 .java files are present |

## Next Steps

Once working, you can:
- Add password encryption (bcrypt)
- Add calculation history
- Create user profiles
- Add result sharing
- Export calculations to file
- Add keyboard support
- Customize colors/themes

## Need Help?

1. **Check README.md** for detailed docs
2. **Verify all .java files** are in same folder
3. **Restart XAMPP** and try again
4. **Check Java version** (need JDK 8+)
5. **Ensure MySQL driver JAR** is present

Good luck! 🚀
