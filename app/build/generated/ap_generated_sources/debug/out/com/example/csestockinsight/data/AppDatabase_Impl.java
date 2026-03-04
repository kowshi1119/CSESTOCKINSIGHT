package com.example.csestockinsight.data;

import androidx.annotation.NonNull;
import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.room.RoomOpenHelper;
import androidx.room.RoomOpenHelper.Delegate;
import androidx.room.RoomOpenHelper.ValidationResult;
import androidx.room.migration.AutoMigrationSpec;
import androidx.room.migration.Migration;
import androidx.room.util.DBUtil;
import androidx.room.util.TableInfo;
import androidx.room.util.TableInfo.Column;
import androidx.room.util.TableInfo.ForeignKey;
import androidx.room.util.TableInfo.Index;
import androidx.sqlite.db.SupportSQLiteDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper;
import androidx.sqlite.db.SupportSQLiteOpenHelper.Callback;
import androidx.sqlite.db.SupportSQLiteOpenHelper.Configuration;
import java.lang.Class;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.annotation.processing.Generated;

@Generated("androidx.room.RoomProcessor")
@SuppressWarnings({"unchecked", "deprecation"})
public final class AppDatabase_Impl extends AppDatabase {
  private volatile CompanyFundamentalsDao _companyFundamentalsDao;

  @Override
  protected SupportSQLiteOpenHelper createOpenHelper(DatabaseConfiguration configuration) {
    final SupportSQLiteOpenHelper.Callback _openCallback = new RoomOpenHelper(configuration, new RoomOpenHelper.Delegate(1) {
      @Override
      public void createAllTables(SupportSQLiteDatabase _db) {
        _db.execSQL("CREATE TABLE IF NOT EXISTS `company_fundamentals` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `name` TEXT, `ticker` TEXT, `sector` TEXT, `income_year1` REAL NOT NULL, `income_year2` REAL NOT NULL, `income_year3` REAL NOT NULL, `income_year4` REAL NOT NULL, `income_year5` REAL NOT NULL, `de_ratio_year1` REAL NOT NULL, `de_ratio_year2` REAL NOT NULL, `de_ratio_year3` REAL NOT NULL, `de_ratio_year4` REAL NOT NULL, `de_ratio_year5` REAL NOT NULL, `dividend_year1` REAL NOT NULL, `dividend_year2` REAL NOT NULL, `dividend_year3` REAL NOT NULL, `dividend_year4` REAL NOT NULL, `dividend_year5` REAL NOT NULL, `pe_ratio` REAL NOT NULL, `promoter_holding` REAL NOT NULL, `eps_year1` REAL NOT NULL, `eps_year2` REAL NOT NULL, `eps_year3` REAL NOT NULL, `eps_year4` REAL NOT NULL, `eps_year5` REAL NOT NULL, `price_month1` REAL NOT NULL, `price_month2` REAL NOT NULL, `price_month3` REAL NOT NULL, `price_month4` REAL NOT NULL, `price_month5` REAL NOT NULL, `price_month6` REAL NOT NULL, `price_month7` REAL NOT NULL, `price_month8` REAL NOT NULL, `price_month9` REAL NOT NULL, `price_month10` REAL NOT NULL, `price_month11` REAL NOT NULL, `price_month12` REAL NOT NULL, `is_favourite` INTEGER NOT NULL)");
        _db.execSQL("CREATE TABLE IF NOT EXISTS room_master_table (id INTEGER PRIMARY KEY,identity_hash TEXT)");
        _db.execSQL("INSERT OR REPLACE INTO room_master_table (id,identity_hash) VALUES(42, '0311426743377df38a87b20afe8fa3c1')");
      }

      @Override
      public void dropAllTables(SupportSQLiteDatabase _db) {
        _db.execSQL("DROP TABLE IF EXISTS `company_fundamentals`");
        if (mCallbacks != null) {
          for (int _i = 0, _size = mCallbacks.size(); _i < _size; _i++) {
            mCallbacks.get(_i).onDestructiveMigration(_db);
          }
        }
      }

      @Override
      protected void onCreate(SupportSQLiteDatabase _db) {
        if (mCallbacks != null) {
          for (int _i = 0, _size = mCallbacks.size(); _i < _size; _i++) {
            mCallbacks.get(_i).onCreate(_db);
          }
        }
      }

      @Override
      public void onOpen(SupportSQLiteDatabase _db) {
        mDatabase = _db;
        internalInitInvalidationTracker(_db);
        if (mCallbacks != null) {
          for (int _i = 0, _size = mCallbacks.size(); _i < _size; _i++) {
            mCallbacks.get(_i).onOpen(_db);
          }
        }
      }

      @Override
      public void onPreMigrate(SupportSQLiteDatabase _db) {
        DBUtil.dropFtsSyncTriggers(_db);
      }

      @Override
      public void onPostMigrate(SupportSQLiteDatabase _db) {
      }

      @Override
      protected RoomOpenHelper.ValidationResult onValidateSchema(SupportSQLiteDatabase _db) {
        final HashMap<String, TableInfo.Column> _columnsCompanyFundamentals = new HashMap<String, TableInfo.Column>(39);
        _columnsCompanyFundamentals.put("id", new TableInfo.Column("id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsCompanyFundamentals.put("name", new TableInfo.Column("name", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsCompanyFundamentals.put("ticker", new TableInfo.Column("ticker", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsCompanyFundamentals.put("sector", new TableInfo.Column("sector", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsCompanyFundamentals.put("income_year1", new TableInfo.Column("income_year1", "REAL", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsCompanyFundamentals.put("income_year2", new TableInfo.Column("income_year2", "REAL", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsCompanyFundamentals.put("income_year3", new TableInfo.Column("income_year3", "REAL", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsCompanyFundamentals.put("income_year4", new TableInfo.Column("income_year4", "REAL", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsCompanyFundamentals.put("income_year5", new TableInfo.Column("income_year5", "REAL", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsCompanyFundamentals.put("de_ratio_year1", new TableInfo.Column("de_ratio_year1", "REAL", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsCompanyFundamentals.put("de_ratio_year2", new TableInfo.Column("de_ratio_year2", "REAL", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsCompanyFundamentals.put("de_ratio_year3", new TableInfo.Column("de_ratio_year3", "REAL", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsCompanyFundamentals.put("de_ratio_year4", new TableInfo.Column("de_ratio_year4", "REAL", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsCompanyFundamentals.put("de_ratio_year5", new TableInfo.Column("de_ratio_year5", "REAL", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsCompanyFundamentals.put("dividend_year1", new TableInfo.Column("dividend_year1", "REAL", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsCompanyFundamentals.put("dividend_year2", new TableInfo.Column("dividend_year2", "REAL", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsCompanyFundamentals.put("dividend_year3", new TableInfo.Column("dividend_year3", "REAL", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsCompanyFundamentals.put("dividend_year4", new TableInfo.Column("dividend_year4", "REAL", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsCompanyFundamentals.put("dividend_year5", new TableInfo.Column("dividend_year5", "REAL", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsCompanyFundamentals.put("pe_ratio", new TableInfo.Column("pe_ratio", "REAL", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsCompanyFundamentals.put("promoter_holding", new TableInfo.Column("promoter_holding", "REAL", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsCompanyFundamentals.put("eps_year1", new TableInfo.Column("eps_year1", "REAL", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsCompanyFundamentals.put("eps_year2", new TableInfo.Column("eps_year2", "REAL", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsCompanyFundamentals.put("eps_year3", new TableInfo.Column("eps_year3", "REAL", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsCompanyFundamentals.put("eps_year4", new TableInfo.Column("eps_year4", "REAL", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsCompanyFundamentals.put("eps_year5", new TableInfo.Column("eps_year5", "REAL", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsCompanyFundamentals.put("price_month1", new TableInfo.Column("price_month1", "REAL", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsCompanyFundamentals.put("price_month2", new TableInfo.Column("price_month2", "REAL", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsCompanyFundamentals.put("price_month3", new TableInfo.Column("price_month3", "REAL", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsCompanyFundamentals.put("price_month4", new TableInfo.Column("price_month4", "REAL", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsCompanyFundamentals.put("price_month5", new TableInfo.Column("price_month5", "REAL", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsCompanyFundamentals.put("price_month6", new TableInfo.Column("price_month6", "REAL", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsCompanyFundamentals.put("price_month7", new TableInfo.Column("price_month7", "REAL", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsCompanyFundamentals.put("price_month8", new TableInfo.Column("price_month8", "REAL", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsCompanyFundamentals.put("price_month9", new TableInfo.Column("price_month9", "REAL", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsCompanyFundamentals.put("price_month10", new TableInfo.Column("price_month10", "REAL", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsCompanyFundamentals.put("price_month11", new TableInfo.Column("price_month11", "REAL", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsCompanyFundamentals.put("price_month12", new TableInfo.Column("price_month12", "REAL", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsCompanyFundamentals.put("is_favourite", new TableInfo.Column("is_favourite", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysCompanyFundamentals = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesCompanyFundamentals = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoCompanyFundamentals = new TableInfo("company_fundamentals", _columnsCompanyFundamentals, _foreignKeysCompanyFundamentals, _indicesCompanyFundamentals);
        final TableInfo _existingCompanyFundamentals = TableInfo.read(_db, "company_fundamentals");
        if (! _infoCompanyFundamentals.equals(_existingCompanyFundamentals)) {
          return new RoomOpenHelper.ValidationResult(false, "company_fundamentals(com.example.csestockinsight.data.CompanyFundamentals).\n"
                  + " Expected:\n" + _infoCompanyFundamentals + "\n"
                  + " Found:\n" + _existingCompanyFundamentals);
        }
        return new RoomOpenHelper.ValidationResult(true, null);
      }
    }, "0311426743377df38a87b20afe8fa3c1", "d6e7a9e009664495398dca85f80e6066");
    final SupportSQLiteOpenHelper.Configuration _sqliteConfig = SupportSQLiteOpenHelper.Configuration.builder(configuration.context)
        .name(configuration.name)
        .callback(_openCallback)
        .build();
    final SupportSQLiteOpenHelper _helper = configuration.sqliteOpenHelperFactory.create(_sqliteConfig);
    return _helper;
  }

  @Override
  protected InvalidationTracker createInvalidationTracker() {
    final HashMap<String, String> _shadowTablesMap = new HashMap<String, String>(0);
    HashMap<String, Set<String>> _viewTables = new HashMap<String, Set<String>>(0);
    return new InvalidationTracker(this, _shadowTablesMap, _viewTables, "company_fundamentals");
  }

  @Override
  public void clearAllTables() {
    super.assertNotMainThread();
    final SupportSQLiteDatabase _db = super.getOpenHelper().getWritableDatabase();
    try {
      super.beginTransaction();
      _db.execSQL("DELETE FROM `company_fundamentals`");
      super.setTransactionSuccessful();
    } finally {
      super.endTransaction();
      _db.query("PRAGMA wal_checkpoint(FULL)").close();
      if (!_db.inTransaction()) {
        _db.execSQL("VACUUM");
      }
    }
  }

  @Override
  protected Map<Class<?>, List<Class<?>>> getRequiredTypeConverters() {
    final HashMap<Class<?>, List<Class<?>>> _typeConvertersMap = new HashMap<Class<?>, List<Class<?>>>();
    _typeConvertersMap.put(CompanyFundamentalsDao.class, CompanyFundamentalsDao_Impl.getRequiredConverters());
    return _typeConvertersMap;
  }

  @Override
  public Set<Class<? extends AutoMigrationSpec>> getRequiredAutoMigrationSpecs() {
    final HashSet<Class<? extends AutoMigrationSpec>> _autoMigrationSpecsSet = new HashSet<Class<? extends AutoMigrationSpec>>();
    return _autoMigrationSpecsSet;
  }

  @Override
  public List<Migration> getAutoMigrations(
      @NonNull Map<Class<? extends AutoMigrationSpec>, AutoMigrationSpec> autoMigrationSpecsMap) {
    return Arrays.asList();
  }

  @Override
  public CompanyFundamentalsDao companyDao() {
    if (_companyFundamentalsDao != null) {
      return _companyFundamentalsDao;
    } else {
      synchronized(this) {
        if(_companyFundamentalsDao == null) {
          _companyFundamentalsDao = new CompanyFundamentalsDao_Impl(this);
        }
        return _companyFundamentalsDao;
      }
    }
  }
}
