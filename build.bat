@echo off
echo =====================================
echo CSE Stock Insight - Build Setup
echo =====================================
echo.

REM Check if Java is available
java -version >nul 2>&1
if %errorlevel% neq 0 (
    echo ERROR: Java is not installed or not in PATH
    echo Please install Java 11 or Java 8 and add it to your PATH
    pause
    exit /b 1
)

echo Checking Java version...
for /f "tokens=3" %%g in ('java -version 2^>^&1 ^| findstr /i "version"') do (
    set JAVA_VERSION=%%g
)
echo Java version: %JAVA_VERSION%

REM Try to find Java 11 installation
set JAVA_11_PATH=
if exist "C:\Program Files\Eclipse Adoptium\jdk-11.*\bin\java.exe" (
    for /f %%i in ('dir "C:\Program Files\Eclipse Adoptium\jdk-11.*" /b /ad 2^>nul') do (
        set JAVA_11_PATH=C:\Program Files\Eclipse Adoptium\%%i
    )
)
if exist "C:\Program Files\Java\jdk-11.*\bin\java.exe" (
    for /f %%i in ('dir "C:\Program Files\Java\jdk-11.*" /b /ad 2^>nul') do (
        set JAVA_11_PATH=C:\Program Files\Java\%%i
    )
)

if defined JAVA_11_PATH (
    echo Found Java 11 at: %JAVA_11_PATH%
    set JAVA_HOME=%JAVA_11_PATH%
    set PATH=%JAVA_11_PATH%\bin;%PATH%
    echo Using Java 11 for build
) else (
    echo WARNING: Java 11 not found. Using system Java.
    echo For best compatibility, please install OpenJDK 11.
    echo Download from: https://adoptium.net/temurin/releases/?version=11
)

echo.
echo Clearing Gradle cache...
if exist "%USERPROFILE%\.gradle\caches" (
    rmdir /s /q "%USERPROFILE%\.gradle\caches"
)

echo.
echo Building project...
call gradlew.bat clean assembleDebug --no-daemon --stacktrace

if %errorlevel% equ 0 (
    echo.
    echo =====================================
    echo BUILD SUCCESSFUL!
    echo =====================================
    echo.
    echo APK Location: app\build\outputs\apk\debug\app-debug.apk
    echo.
) else (
    echo.
    echo =====================================
    echo BUILD FAILED!
    echo =====================================
    echo.
    echo Please check the error messages above.
    echo For help, see the README.md file.
)

pause
