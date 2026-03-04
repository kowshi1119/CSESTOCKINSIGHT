# Build Troubleshooting Guide

## If You Encounter JDK/jlink Errors

If you see errors like:
```
Error while executing process C:\Program Files\Java\jdk-23\bin\jlink.exe
Error: unknown target platform android
```

This is caused by Java 23's jlink tool not being fully compatible with Android builds yet.

## Solutions (Choose One)

### Solution 1: Use Java 17 LTS (RECOMMENDED)

Java 17 is the recommended version for Android development as it's an LTS version.

1. **Download Java 17:**
   - Visit: https://adoptium.net/temurin/releases/?version=17
   - Download the Windows x64 MSI installer
   - Install to a location like `C:\Program Files\Eclipse Adoptium\jdk-17`

2. **Set JAVA_HOME:**
   ```powershell
   [System.Environment]::SetEnvironmentVariable('JAVA_HOME', 'C:\Program Files\Eclipse Adoptium\jdk-17', 'Machine')
   ```

3. **Update PATH:**
   - Open System Properties > Environment Variables
   - Edit PATH variable
   - Add: `%JAVA_HOME%\bin` at the beginning
   - Remove any other Java paths

4. **Restart your IDE** (IntelliJ IDEA, Android Studio, etc.)

5. **Verify:**
   ```powershell
   java -version
   # Should show: openjdk version "17.x.x"
   ```

6. **Clean and rebuild:**
   ```powershell
   .\gradlew clean build
   ```

### Solution 2: Upgrade AGP to Latest

If you must use Java 23, upgrade to the latest AGP:

1. **Edit `build.gradle` (root):**
   ```groovy
   classpath 'com.android.tools.build:gradle:8.5.2'
   ```

2. **May also need to upgrade Gradle:**
   ```properties
   # In gradle/wrapper/gradle-wrapper.properties
   distributionUrl=https\://services.gradle.org/distributions/gradle-8.7-bin.zip
   ```

3. **Clean and rebuild:**
   ```powershell
   .\gradlew --stop
   .\gradlew clean build
   ```

### Solution 3: Use IDE's Embedded JDK

Configure your IDE to use its embedded JDK for Gradle:

**In IntelliJ IDEA / Android Studio:**
1. File > Settings > Build, Execution, Deployment > Build Tools > Gradle
2. Set "Gradle JVM" to the IDE's bundled JDK (usually Java 17 or 21)
3. Click OK and sync project

## Verify Your Current Setup

```powershell
# Check Java version
java -version

# Check Gradle version
.\gradlew -version

# Check Gradle JVM
.\gradlew -version | Select-String "JVM"
```

## Quick Build Commands

```powershell
# Stop all Gradle daemons
.\gradlew --stop

# Clean build
.\gradlew clean

# Build debug APK
.\gradlew assembleDebug

# Build and install on device
.\gradlew installDebug

# Run tests
.\gradlew test
```

## Common Build Errors

### Error: "Namespace not specified"
**Solution:** Already fixed! The namespace is now in app/build.gradle

### Error: "android:exported needs to be explicitly specified"
**Solution:** Already fixed! All activities now have android:exported attribute

### Error: "Unsupported class file major version"
**Solution:** Java version too new for Gradle - use Java 17

### Error: "Could not find com.android.tools.build:gradle:X.X.X"
**Solution:** Check internet connection and ensure google() repository is in build.gradle

## Still Having Issues?

1. **Clear Gradle caches:**
   ```powershell
   Remove-Item -Recurse -Force $env:USERPROFILE\.gradle\caches
   ```

2. **Clear Android build caches:**
   ```powershell
   .\gradlew cleanBuildCache
   ```

3. **Invalidate IDE caches:**
   - In IntelliJ/Android Studio: File > Invalidate Caches / Restart

4. **Check the BUILD_FIX_SUMMARY.md** file for detailed information about what was changed

## Recommended Development Environment

- **OS:** Windows 10/11
- **IDE:** Android Studio Hedgehog (2023.1.1) or later
- **JDK:** Java 17 LTS
- **Gradle:** 8.10.2 (configured via wrapper)
- **Android SDK:** API 34 (Android 14)
- **Build Tools:** 33.0.1 or higher

## Need Help?

1. Check BUILD_FIX_SUMMARY.md for detailed fix information
2. Check PROJECT_STATUS.md for current project status
3. Review this troubleshooting guide
4. Check Android developer documentation: https://developer.android.com/build

