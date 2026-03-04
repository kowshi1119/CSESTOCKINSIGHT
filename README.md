# CSE Stock Insight

A comprehensive Android application for analyzing stocks listed on the Colombo Stock Exchange (CSE) with both fundamental and technical analysis features.

## Features

### Fundamental Analysis
- **Company Search**: Browse and search through a database of CSE companies
- **Detailed Metrics**: View 5-year historical data for:
  - Income statements
  - Debt-to-equity ratios
  - Dividend history
  - P/E ratios
  - Promoter holdings
  - Earnings per share (EPS)
- **Interactive Charts**: Bar charts and pie charts for visualizing financial data
- **Watchlist**: Save companies to a personal watchlist

### Technical Analysis
- **Price History**: 12-month price charts using MPAndroidChart
- **Moving Averages**: Calculate and display moving averages
- **Trading Signals**: Bullish/Bearish/Neutral signals based on price movements
- **Price Forecasting**: Simple forecasting based on historical trends
- **Currency Conversion**: LKR to USD conversion for international perspective

## Technology Stack

- **Language**: Java
- **UI Framework**: Android SDK with Material Design Components
- **Database**: Room (SQLite)
- **Networking**: Retrofit2 with Gson converter
- **Charts**: MPAndroidChart library
- **Architecture**: Repository pattern with ExecutorService for background operations

## Setup Instructions

### Prerequisites
- Android Studio Arctic Fox or later
- Android SDK API level 21 or higher
- Java 8+

### Installation

#### Option 1: Using the Build Script (Recommended)
1. Run the automated build script:
   ```batch
   build.bat
   ```
   This script will automatically detect and use the correct Java version.

#### Option 2: Manual Setup
1. **Install Java 11 (Required for compatibility)**:
   - Download OpenJDK 11 from [Adoptium](https://adoptium.net/temurin/releases/?version=11)
   - Set JAVA_HOME environment variable to Java 11 installation path
   - Add Java 11 bin directory to your PATH

2. **Clone and build the project**:
   ```bash
   git clone <repository-url>
   cd CSEStockInsight
   ./gradlew clean assembleDebug
   ```

3. **Open in Android Studio** (Optional):
   - Open the project in Android Studio
   - Let it sync the Gradle files
   - Build and run on emulator or device

#### Troubleshooting Java Version Issues

If you encounter "Unsupported class file major version" errors:

1. **Check your Java version**:
   ```bash
   java -version
   javac -version
   ```

2. **Java Version Compatibility**:
   - Java 8: Works with Gradle 6.9 and AGP 4.2.2 ✅
   - Java 11: Works with Gradle 6.9 and AGP 4.2.2 ✅  
   - Java 17+: Not compatible with this Gradle/AGP version ❌

3. **Set correct JAVA_HOME**:
   ```batch
   set JAVA_HOME=C:\Program Files\Eclipse Adoptium\jdk-11.x.x
   set PATH=%JAVA_HOME%\bin;%PATH%
   ```

4. **Clear Gradle cache**:
   ```bash
   rm -rf ~/.gradle/caches  # On Unix
   rmdir /s %USERPROFILE%\.gradle\caches  # On Windows
   ```

### Configuration
- The app comes pre-loaded with sample data for 5 CSE companies
- API endpoints are currently placeholder URLs (`https://api.example.com`)
- To use real data, replace the API endpoints in the network service classes

## Project Structure

```
app/src/main/java/com/example/csestockinsight/
├── data/                           # Data layer
│   ├── AppDatabase.java           # Room database configuration
│   ├── CompanyFundamentals.java   # Entity class
│   ├── CompanyFundamentalsDao.java # Data access object
│   └── CompanyRepository.java     # Repository pattern implementation
├── network/                       # Network layer
│   ├── StockPriceApiService.java  # Stock price API interface
│   ├── StockPriceResponse.java    # Response model
│   ├── CurrencyApiService.java    # Currency conversion API
│   └── CurrencyResponse.java      # Currency response model
├── adapter/                       # RecyclerView adapters
│   └── CompanyAdapter.java        # Company list adapter
├── MainActivity.java              # Main entry point
├── FundamentalAnalyzerActivity.java # Company search and list
├── CompanyDetailActivity.java     # Detailed company information
├── FundamentalChartsActivity.java # Charts visualization
└── TechnicalForecastActivity.java # Technical analysis
```

## Key Improvements Made

1. **Modernized Architecture**:
   - Replaced deprecated AsyncTask with ExecutorService
   - Added proper lifecycle management to prevent memory leaks
   - Updated to latest Gradle and dependency versions

2. **Enhanced Security**:
   - Added internet permission
   - Configured network security for API calls
   - Added ProGuard rules for release builds

3. **Better Performance**:
   - Implemented proper threading for database operations
   - Added MultiDex support for large dependency count
   - Optimized Room database configuration

4. **Code Quality**:
   - Comprehensive error handling
   - Proper resource cleanup
   - Following Android best practices

## Sample Data

The app includes pre-loaded data for these companies:
- John Keells Holdings (JKH.N000)
- Lanka Milk Foods (LMF.N000)
- People's Leasing (PKME.N000)
- ABC Textiles (ABCT.N000)
- Delta Pharmaceuticals (DLPH.N000)

## API Integration

The app demonstrates API integration with placeholder endpoints:
- Stock price fetching
- Currency conversion (LKR to USD)

To integrate with real APIs, update the base URLs in:
- `CompanyDetailActivity.java`
- `TechnicalForecastActivity.java`

## License

This project is developed as an educational application for demonstrating Android development best practices and financial analysis concepts.

## Contributing

1. Fork the repository
2. Create a feature branch
3. Make your changes
4. Test thoroughly
5. Submit a pull request

## Support

For questions or issues, please create an issue in the repository or contact the development team.

## Common Build Issues and Solutions

### Issue 1: "Unsupported class file major version XX"
**Cause**: Using incompatible Java version
**Solution**: 
1. Install Java 11: `https://adoptium.net/temurin/releases/?version=11`
2. Set JAVA_HOME to Java 11 installation
3. Clear Gradle cache: `rm -rf ~/.gradle/caches`
4. Retry build

### Issue 2: "Could not resolve dependencies"
**Cause**: Network issues or repository problems
**Solution**:
1. Check internet connection
2. Add Google and JitPack repositories in build.gradle
3. Sync project with Gradle files

### Issue 3: "Multidex issues"
**Cause**: Method count exceeds 65K limit
**Solution**: Already configured in the project with:
- `multiDexEnabled true`
- `implementation 'androidx.multidex:multidex:2.0.1'`
- Custom Application class

### Issue 4: "Room database errors"
**Cause**: Database schema issues
**Solution**: 
1. Clean and rebuild project
2. Uninstall app from device/emulator
3. Reinstall fresh

## Build Status
- ✅ Gradle 6.9 with Android Gradle Plugin 4.2.2
- ✅ Java 8/11 compatibility
- ✅ All dependencies updated and compatible
- ✅ AsyncTask deprecated code replaced with ExecutorService
- ✅ Memory leak fixes implemented
- ✅ Network security configuration added
- ✅ ProGuard rules configured

## Next Steps for Development

1. **API Integration**: Replace placeholder URLs with real CSE stock APIs
2. **Authentication**: Add user accounts and authentication
3. **Real-time Data**: Implement WebSocket connections for live prices
4. **Notifications**: Add price alerts and notifications
5. **Advanced Analytics**: Implement more sophisticated technical indicators
6. **Data Persistence**: Add offline caching strategies
7. **Testing**: Add unit tests and integration tests

---
**Last Updated**: November 2025
**Status**: Ready for development and deployment
