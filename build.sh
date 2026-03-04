#!/bin/bash

echo "======================================"
echo "CSE Stock Insight - Build Setup (Unix)"
echo "======================================"
echo

# Check if Java is available
if ! command -v java &> /dev/null; then
    echo "ERROR: Java is not installed or not in PATH"
    echo "Please install OpenJDK 11 and add it to your PATH"
    echo "Download from: https://adoptium.net/temurin/releases/?version=11"
    exit 1
fi

echo "Checking Java version..."
JAVA_VERSION=$(java -version 2>&1 | head -n 1 | cut -d'"' -f2)
echo "Java version: $JAVA_VERSION"

# Check for Java 11
if command -v java -version 2>&1 | grep -q "11\."; then
    echo "✅ Using Java 11 - compatible with Gradle 6.9"
elif command -v java -version 2>&1 | grep -q "1\.8"; then
    echo "✅ Using Java 8 - compatible with Gradle 6.9"
else
    echo "⚠️  WARNING: You are using Java $JAVA_VERSION"
    echo "   This may cause compatibility issues with Gradle 6.9"
    echo "   Recommended: Install OpenJDK 11 for best compatibility"
    echo
    read -p "Continue anyway? (y/N): " -n 1 -r
    echo
    if [[ ! $REPLY =~ ^[Yy]$ ]]; then
        exit 1
    fi
fi

echo
echo "Clearing Gradle cache..."
rm -rf ~/.gradle/caches 2>/dev/null || true

echo
echo "Making gradlew executable..."
chmod +x ./gradlew

echo
echo "Building project..."
./gradlew clean assembleDebug --no-daemon --stacktrace

if [ $? -eq 0 ]; then
    echo
    echo "======================================"
    echo "BUILD SUCCESSFUL!"
    echo "======================================"
    echo
    echo "APK Location: app/build/outputs/apk/debug/app-debug.apk"
    echo
else
    echo
    echo "======================================"
    echo "BUILD FAILED!"
    echo "======================================"
    echo
    echo "Please check the error messages above."
    echo "For help, see the README.md file."
    exit 1
fi
