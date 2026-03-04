package com.example.csestockinsight.data;

import android.database.Cursor;
import androidx.room.EntityDeletionOrUpdateAdapter;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import java.lang.Class;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.annotation.processing.Generated;

@Generated("androidx.room.RoomProcessor")
@SuppressWarnings({"unchecked", "deprecation"})
public final class CompanyFundamentalsDao_Impl implements CompanyFundamentalsDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<CompanyFundamentals> __insertionAdapterOfCompanyFundamentals;

  private final EntityDeletionOrUpdateAdapter<CompanyFundamentals> __updateAdapterOfCompanyFundamentals;

  public CompanyFundamentalsDao_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfCompanyFundamentals = new EntityInsertionAdapter<CompanyFundamentals>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR REPLACE INTO `company_fundamentals` (`id`,`name`,`ticker`,`sector`,`income_year1`,`income_year2`,`income_year3`,`income_year4`,`income_year5`,`de_ratio_year1`,`de_ratio_year2`,`de_ratio_year3`,`de_ratio_year4`,`de_ratio_year5`,`dividend_year1`,`dividend_year2`,`dividend_year3`,`dividend_year4`,`dividend_year5`,`pe_ratio`,`promoter_holding`,`eps_year1`,`eps_year2`,`eps_year3`,`eps_year4`,`eps_year5`,`price_month1`,`price_month2`,`price_month3`,`price_month4`,`price_month5`,`price_month6`,`price_month7`,`price_month8`,`price_month9`,`price_month10`,`price_month11`,`price_month12`,`is_favourite`) VALUES (nullif(?, 0),?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, CompanyFundamentals value) {
        stmt.bindLong(1, value.id);
        if (value.name == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.name);
        }
        if (value.ticker == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindString(3, value.ticker);
        }
        if (value.sector == null) {
          stmt.bindNull(4);
        } else {
          stmt.bindString(4, value.sector);
        }
        stmt.bindDouble(5, value.incomeYear1);
        stmt.bindDouble(6, value.incomeYear2);
        stmt.bindDouble(7, value.incomeYear3);
        stmt.bindDouble(8, value.incomeYear4);
        stmt.bindDouble(9, value.incomeYear5);
        stmt.bindDouble(10, value.deRatioYear1);
        stmt.bindDouble(11, value.deRatioYear2);
        stmt.bindDouble(12, value.deRatioYear3);
        stmt.bindDouble(13, value.deRatioYear4);
        stmt.bindDouble(14, value.deRatioYear5);
        stmt.bindDouble(15, value.dividendYear1);
        stmt.bindDouble(16, value.dividendYear2);
        stmt.bindDouble(17, value.dividendYear3);
        stmt.bindDouble(18, value.dividendYear4);
        stmt.bindDouble(19, value.dividendYear5);
        stmt.bindDouble(20, value.peRatio);
        stmt.bindDouble(21, value.promoterHolding);
        stmt.bindDouble(22, value.epsYear1);
        stmt.bindDouble(23, value.epsYear2);
        stmt.bindDouble(24, value.epsYear3);
        stmt.bindDouble(25, value.epsYear4);
        stmt.bindDouble(26, value.epsYear5);
        stmt.bindDouble(27, value.priceMonth1);
        stmt.bindDouble(28, value.priceMonth2);
        stmt.bindDouble(29, value.priceMonth3);
        stmt.bindDouble(30, value.priceMonth4);
        stmt.bindDouble(31, value.priceMonth5);
        stmt.bindDouble(32, value.priceMonth6);
        stmt.bindDouble(33, value.priceMonth7);
        stmt.bindDouble(34, value.priceMonth8);
        stmt.bindDouble(35, value.priceMonth9);
        stmt.bindDouble(36, value.priceMonth10);
        stmt.bindDouble(37, value.priceMonth11);
        stmt.bindDouble(38, value.priceMonth12);
        final int _tmp = value.isFavourite ? 1 : 0;
        stmt.bindLong(39, _tmp);
      }
    };
    this.__updateAdapterOfCompanyFundamentals = new EntityDeletionOrUpdateAdapter<CompanyFundamentals>(__db) {
      @Override
      public String createQuery() {
        return "UPDATE OR ABORT `company_fundamentals` SET `id` = ?,`name` = ?,`ticker` = ?,`sector` = ?,`income_year1` = ?,`income_year2` = ?,`income_year3` = ?,`income_year4` = ?,`income_year5` = ?,`de_ratio_year1` = ?,`de_ratio_year2` = ?,`de_ratio_year3` = ?,`de_ratio_year4` = ?,`de_ratio_year5` = ?,`dividend_year1` = ?,`dividend_year2` = ?,`dividend_year3` = ?,`dividend_year4` = ?,`dividend_year5` = ?,`pe_ratio` = ?,`promoter_holding` = ?,`eps_year1` = ?,`eps_year2` = ?,`eps_year3` = ?,`eps_year4` = ?,`eps_year5` = ?,`price_month1` = ?,`price_month2` = ?,`price_month3` = ?,`price_month4` = ?,`price_month5` = ?,`price_month6` = ?,`price_month7` = ?,`price_month8` = ?,`price_month9` = ?,`price_month10` = ?,`price_month11` = ?,`price_month12` = ?,`is_favourite` = ? WHERE `id` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, CompanyFundamentals value) {
        stmt.bindLong(1, value.id);
        if (value.name == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.name);
        }
        if (value.ticker == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindString(3, value.ticker);
        }
        if (value.sector == null) {
          stmt.bindNull(4);
        } else {
          stmt.bindString(4, value.sector);
        }
        stmt.bindDouble(5, value.incomeYear1);
        stmt.bindDouble(6, value.incomeYear2);
        stmt.bindDouble(7, value.incomeYear3);
        stmt.bindDouble(8, value.incomeYear4);
        stmt.bindDouble(9, value.incomeYear5);
        stmt.bindDouble(10, value.deRatioYear1);
        stmt.bindDouble(11, value.deRatioYear2);
        stmt.bindDouble(12, value.deRatioYear3);
        stmt.bindDouble(13, value.deRatioYear4);
        stmt.bindDouble(14, value.deRatioYear5);
        stmt.bindDouble(15, value.dividendYear1);
        stmt.bindDouble(16, value.dividendYear2);
        stmt.bindDouble(17, value.dividendYear3);
        stmt.bindDouble(18, value.dividendYear4);
        stmt.bindDouble(19, value.dividendYear5);
        stmt.bindDouble(20, value.peRatio);
        stmt.bindDouble(21, value.promoterHolding);
        stmt.bindDouble(22, value.epsYear1);
        stmt.bindDouble(23, value.epsYear2);
        stmt.bindDouble(24, value.epsYear3);
        stmt.bindDouble(25, value.epsYear4);
        stmt.bindDouble(26, value.epsYear5);
        stmt.bindDouble(27, value.priceMonth1);
        stmt.bindDouble(28, value.priceMonth2);
        stmt.bindDouble(29, value.priceMonth3);
        stmt.bindDouble(30, value.priceMonth4);
        stmt.bindDouble(31, value.priceMonth5);
        stmt.bindDouble(32, value.priceMonth6);
        stmt.bindDouble(33, value.priceMonth7);
        stmt.bindDouble(34, value.priceMonth8);
        stmt.bindDouble(35, value.priceMonth9);
        stmt.bindDouble(36, value.priceMonth10);
        stmt.bindDouble(37, value.priceMonth11);
        stmt.bindDouble(38, value.priceMonth12);
        final int _tmp = value.isFavourite ? 1 : 0;
        stmt.bindLong(39, _tmp);
        stmt.bindLong(40, value.id);
      }
    };
  }

  @Override
  public void insert(final CompanyFundamentals company) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __insertionAdapterOfCompanyFundamentals.insert(company);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void insertAll(final List<CompanyFundamentals> companies) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __insertionAdapterOfCompanyFundamentals.insert(companies);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void update(final CompanyFundamentals company) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __updateAdapterOfCompanyFundamentals.handle(company);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public List<CompanyFundamentals> getAllCompanies() {
    final String _sql = "SELECT * FROM company_fundamentals ORDER BY name ASC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
      final int _cursorIndexOfName = CursorUtil.getColumnIndexOrThrow(_cursor, "name");
      final int _cursorIndexOfTicker = CursorUtil.getColumnIndexOrThrow(_cursor, "ticker");
      final int _cursorIndexOfSector = CursorUtil.getColumnIndexOrThrow(_cursor, "sector");
      final int _cursorIndexOfIncomeYear1 = CursorUtil.getColumnIndexOrThrow(_cursor, "income_year1");
      final int _cursorIndexOfIncomeYear2 = CursorUtil.getColumnIndexOrThrow(_cursor, "income_year2");
      final int _cursorIndexOfIncomeYear3 = CursorUtil.getColumnIndexOrThrow(_cursor, "income_year3");
      final int _cursorIndexOfIncomeYear4 = CursorUtil.getColumnIndexOrThrow(_cursor, "income_year4");
      final int _cursorIndexOfIncomeYear5 = CursorUtil.getColumnIndexOrThrow(_cursor, "income_year5");
      final int _cursorIndexOfDeRatioYear1 = CursorUtil.getColumnIndexOrThrow(_cursor, "de_ratio_year1");
      final int _cursorIndexOfDeRatioYear2 = CursorUtil.getColumnIndexOrThrow(_cursor, "de_ratio_year2");
      final int _cursorIndexOfDeRatioYear3 = CursorUtil.getColumnIndexOrThrow(_cursor, "de_ratio_year3");
      final int _cursorIndexOfDeRatioYear4 = CursorUtil.getColumnIndexOrThrow(_cursor, "de_ratio_year4");
      final int _cursorIndexOfDeRatioYear5 = CursorUtil.getColumnIndexOrThrow(_cursor, "de_ratio_year5");
      final int _cursorIndexOfDividendYear1 = CursorUtil.getColumnIndexOrThrow(_cursor, "dividend_year1");
      final int _cursorIndexOfDividendYear2 = CursorUtil.getColumnIndexOrThrow(_cursor, "dividend_year2");
      final int _cursorIndexOfDividendYear3 = CursorUtil.getColumnIndexOrThrow(_cursor, "dividend_year3");
      final int _cursorIndexOfDividendYear4 = CursorUtil.getColumnIndexOrThrow(_cursor, "dividend_year4");
      final int _cursorIndexOfDividendYear5 = CursorUtil.getColumnIndexOrThrow(_cursor, "dividend_year5");
      final int _cursorIndexOfPeRatio = CursorUtil.getColumnIndexOrThrow(_cursor, "pe_ratio");
      final int _cursorIndexOfPromoterHolding = CursorUtil.getColumnIndexOrThrow(_cursor, "promoter_holding");
      final int _cursorIndexOfEpsYear1 = CursorUtil.getColumnIndexOrThrow(_cursor, "eps_year1");
      final int _cursorIndexOfEpsYear2 = CursorUtil.getColumnIndexOrThrow(_cursor, "eps_year2");
      final int _cursorIndexOfEpsYear3 = CursorUtil.getColumnIndexOrThrow(_cursor, "eps_year3");
      final int _cursorIndexOfEpsYear4 = CursorUtil.getColumnIndexOrThrow(_cursor, "eps_year4");
      final int _cursorIndexOfEpsYear5 = CursorUtil.getColumnIndexOrThrow(_cursor, "eps_year5");
      final int _cursorIndexOfPriceMonth1 = CursorUtil.getColumnIndexOrThrow(_cursor, "price_month1");
      final int _cursorIndexOfPriceMonth2 = CursorUtil.getColumnIndexOrThrow(_cursor, "price_month2");
      final int _cursorIndexOfPriceMonth3 = CursorUtil.getColumnIndexOrThrow(_cursor, "price_month3");
      final int _cursorIndexOfPriceMonth4 = CursorUtil.getColumnIndexOrThrow(_cursor, "price_month4");
      final int _cursorIndexOfPriceMonth5 = CursorUtil.getColumnIndexOrThrow(_cursor, "price_month5");
      final int _cursorIndexOfPriceMonth6 = CursorUtil.getColumnIndexOrThrow(_cursor, "price_month6");
      final int _cursorIndexOfPriceMonth7 = CursorUtil.getColumnIndexOrThrow(_cursor, "price_month7");
      final int _cursorIndexOfPriceMonth8 = CursorUtil.getColumnIndexOrThrow(_cursor, "price_month8");
      final int _cursorIndexOfPriceMonth9 = CursorUtil.getColumnIndexOrThrow(_cursor, "price_month9");
      final int _cursorIndexOfPriceMonth10 = CursorUtil.getColumnIndexOrThrow(_cursor, "price_month10");
      final int _cursorIndexOfPriceMonth11 = CursorUtil.getColumnIndexOrThrow(_cursor, "price_month11");
      final int _cursorIndexOfPriceMonth12 = CursorUtil.getColumnIndexOrThrow(_cursor, "price_month12");
      final int _cursorIndexOfIsFavourite = CursorUtil.getColumnIndexOrThrow(_cursor, "is_favourite");
      final List<CompanyFundamentals> _result = new ArrayList<CompanyFundamentals>(_cursor.getCount());
      while(_cursor.moveToNext()) {
        final CompanyFundamentals _item;
        _item = new CompanyFundamentals();
        _item.id = _cursor.getInt(_cursorIndexOfId);
        if (_cursor.isNull(_cursorIndexOfName)) {
          _item.name = null;
        } else {
          _item.name = _cursor.getString(_cursorIndexOfName);
        }
        if (_cursor.isNull(_cursorIndexOfTicker)) {
          _item.ticker = null;
        } else {
          _item.ticker = _cursor.getString(_cursorIndexOfTicker);
        }
        if (_cursor.isNull(_cursorIndexOfSector)) {
          _item.sector = null;
        } else {
          _item.sector = _cursor.getString(_cursorIndexOfSector);
        }
        _item.incomeYear1 = _cursor.getDouble(_cursorIndexOfIncomeYear1);
        _item.incomeYear2 = _cursor.getDouble(_cursorIndexOfIncomeYear2);
        _item.incomeYear3 = _cursor.getDouble(_cursorIndexOfIncomeYear3);
        _item.incomeYear4 = _cursor.getDouble(_cursorIndexOfIncomeYear4);
        _item.incomeYear5 = _cursor.getDouble(_cursorIndexOfIncomeYear5);
        _item.deRatioYear1 = _cursor.getDouble(_cursorIndexOfDeRatioYear1);
        _item.deRatioYear2 = _cursor.getDouble(_cursorIndexOfDeRatioYear2);
        _item.deRatioYear3 = _cursor.getDouble(_cursorIndexOfDeRatioYear3);
        _item.deRatioYear4 = _cursor.getDouble(_cursorIndexOfDeRatioYear4);
        _item.deRatioYear5 = _cursor.getDouble(_cursorIndexOfDeRatioYear5);
        _item.dividendYear1 = _cursor.getDouble(_cursorIndexOfDividendYear1);
        _item.dividendYear2 = _cursor.getDouble(_cursorIndexOfDividendYear2);
        _item.dividendYear3 = _cursor.getDouble(_cursorIndexOfDividendYear3);
        _item.dividendYear4 = _cursor.getDouble(_cursorIndexOfDividendYear4);
        _item.dividendYear5 = _cursor.getDouble(_cursorIndexOfDividendYear5);
        _item.peRatio = _cursor.getDouble(_cursorIndexOfPeRatio);
        _item.promoterHolding = _cursor.getDouble(_cursorIndexOfPromoterHolding);
        _item.epsYear1 = _cursor.getDouble(_cursorIndexOfEpsYear1);
        _item.epsYear2 = _cursor.getDouble(_cursorIndexOfEpsYear2);
        _item.epsYear3 = _cursor.getDouble(_cursorIndexOfEpsYear3);
        _item.epsYear4 = _cursor.getDouble(_cursorIndexOfEpsYear4);
        _item.epsYear5 = _cursor.getDouble(_cursorIndexOfEpsYear5);
        _item.priceMonth1 = _cursor.getDouble(_cursorIndexOfPriceMonth1);
        _item.priceMonth2 = _cursor.getDouble(_cursorIndexOfPriceMonth2);
        _item.priceMonth3 = _cursor.getDouble(_cursorIndexOfPriceMonth3);
        _item.priceMonth4 = _cursor.getDouble(_cursorIndexOfPriceMonth4);
        _item.priceMonth5 = _cursor.getDouble(_cursorIndexOfPriceMonth5);
        _item.priceMonth6 = _cursor.getDouble(_cursorIndexOfPriceMonth6);
        _item.priceMonth7 = _cursor.getDouble(_cursorIndexOfPriceMonth7);
        _item.priceMonth8 = _cursor.getDouble(_cursorIndexOfPriceMonth8);
        _item.priceMonth9 = _cursor.getDouble(_cursorIndexOfPriceMonth9);
        _item.priceMonth10 = _cursor.getDouble(_cursorIndexOfPriceMonth10);
        _item.priceMonth11 = _cursor.getDouble(_cursorIndexOfPriceMonth11);
        _item.priceMonth12 = _cursor.getDouble(_cursorIndexOfPriceMonth12);
        final int _tmp;
        _tmp = _cursor.getInt(_cursorIndexOfIsFavourite);
        _item.isFavourite = _tmp != 0;
        _result.add(_item);
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @Override
  public CompanyFundamentals getById(final int id) {
    final String _sql = "SELECT * FROM company_fundamentals WHERE id = ? LIMIT 1";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, id);
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
      final int _cursorIndexOfName = CursorUtil.getColumnIndexOrThrow(_cursor, "name");
      final int _cursorIndexOfTicker = CursorUtil.getColumnIndexOrThrow(_cursor, "ticker");
      final int _cursorIndexOfSector = CursorUtil.getColumnIndexOrThrow(_cursor, "sector");
      final int _cursorIndexOfIncomeYear1 = CursorUtil.getColumnIndexOrThrow(_cursor, "income_year1");
      final int _cursorIndexOfIncomeYear2 = CursorUtil.getColumnIndexOrThrow(_cursor, "income_year2");
      final int _cursorIndexOfIncomeYear3 = CursorUtil.getColumnIndexOrThrow(_cursor, "income_year3");
      final int _cursorIndexOfIncomeYear4 = CursorUtil.getColumnIndexOrThrow(_cursor, "income_year4");
      final int _cursorIndexOfIncomeYear5 = CursorUtil.getColumnIndexOrThrow(_cursor, "income_year5");
      final int _cursorIndexOfDeRatioYear1 = CursorUtil.getColumnIndexOrThrow(_cursor, "de_ratio_year1");
      final int _cursorIndexOfDeRatioYear2 = CursorUtil.getColumnIndexOrThrow(_cursor, "de_ratio_year2");
      final int _cursorIndexOfDeRatioYear3 = CursorUtil.getColumnIndexOrThrow(_cursor, "de_ratio_year3");
      final int _cursorIndexOfDeRatioYear4 = CursorUtil.getColumnIndexOrThrow(_cursor, "de_ratio_year4");
      final int _cursorIndexOfDeRatioYear5 = CursorUtil.getColumnIndexOrThrow(_cursor, "de_ratio_year5");
      final int _cursorIndexOfDividendYear1 = CursorUtil.getColumnIndexOrThrow(_cursor, "dividend_year1");
      final int _cursorIndexOfDividendYear2 = CursorUtil.getColumnIndexOrThrow(_cursor, "dividend_year2");
      final int _cursorIndexOfDividendYear3 = CursorUtil.getColumnIndexOrThrow(_cursor, "dividend_year3");
      final int _cursorIndexOfDividendYear4 = CursorUtil.getColumnIndexOrThrow(_cursor, "dividend_year4");
      final int _cursorIndexOfDividendYear5 = CursorUtil.getColumnIndexOrThrow(_cursor, "dividend_year5");
      final int _cursorIndexOfPeRatio = CursorUtil.getColumnIndexOrThrow(_cursor, "pe_ratio");
      final int _cursorIndexOfPromoterHolding = CursorUtil.getColumnIndexOrThrow(_cursor, "promoter_holding");
      final int _cursorIndexOfEpsYear1 = CursorUtil.getColumnIndexOrThrow(_cursor, "eps_year1");
      final int _cursorIndexOfEpsYear2 = CursorUtil.getColumnIndexOrThrow(_cursor, "eps_year2");
      final int _cursorIndexOfEpsYear3 = CursorUtil.getColumnIndexOrThrow(_cursor, "eps_year3");
      final int _cursorIndexOfEpsYear4 = CursorUtil.getColumnIndexOrThrow(_cursor, "eps_year4");
      final int _cursorIndexOfEpsYear5 = CursorUtil.getColumnIndexOrThrow(_cursor, "eps_year5");
      final int _cursorIndexOfPriceMonth1 = CursorUtil.getColumnIndexOrThrow(_cursor, "price_month1");
      final int _cursorIndexOfPriceMonth2 = CursorUtil.getColumnIndexOrThrow(_cursor, "price_month2");
      final int _cursorIndexOfPriceMonth3 = CursorUtil.getColumnIndexOrThrow(_cursor, "price_month3");
      final int _cursorIndexOfPriceMonth4 = CursorUtil.getColumnIndexOrThrow(_cursor, "price_month4");
      final int _cursorIndexOfPriceMonth5 = CursorUtil.getColumnIndexOrThrow(_cursor, "price_month5");
      final int _cursorIndexOfPriceMonth6 = CursorUtil.getColumnIndexOrThrow(_cursor, "price_month6");
      final int _cursorIndexOfPriceMonth7 = CursorUtil.getColumnIndexOrThrow(_cursor, "price_month7");
      final int _cursorIndexOfPriceMonth8 = CursorUtil.getColumnIndexOrThrow(_cursor, "price_month8");
      final int _cursorIndexOfPriceMonth9 = CursorUtil.getColumnIndexOrThrow(_cursor, "price_month9");
      final int _cursorIndexOfPriceMonth10 = CursorUtil.getColumnIndexOrThrow(_cursor, "price_month10");
      final int _cursorIndexOfPriceMonth11 = CursorUtil.getColumnIndexOrThrow(_cursor, "price_month11");
      final int _cursorIndexOfPriceMonth12 = CursorUtil.getColumnIndexOrThrow(_cursor, "price_month12");
      final int _cursorIndexOfIsFavourite = CursorUtil.getColumnIndexOrThrow(_cursor, "is_favourite");
      final CompanyFundamentals _result;
      if(_cursor.moveToFirst()) {
        _result = new CompanyFundamentals();
        _result.id = _cursor.getInt(_cursorIndexOfId);
        if (_cursor.isNull(_cursorIndexOfName)) {
          _result.name = null;
        } else {
          _result.name = _cursor.getString(_cursorIndexOfName);
        }
        if (_cursor.isNull(_cursorIndexOfTicker)) {
          _result.ticker = null;
        } else {
          _result.ticker = _cursor.getString(_cursorIndexOfTicker);
        }
        if (_cursor.isNull(_cursorIndexOfSector)) {
          _result.sector = null;
        } else {
          _result.sector = _cursor.getString(_cursorIndexOfSector);
        }
        _result.incomeYear1 = _cursor.getDouble(_cursorIndexOfIncomeYear1);
        _result.incomeYear2 = _cursor.getDouble(_cursorIndexOfIncomeYear2);
        _result.incomeYear3 = _cursor.getDouble(_cursorIndexOfIncomeYear3);
        _result.incomeYear4 = _cursor.getDouble(_cursorIndexOfIncomeYear4);
        _result.incomeYear5 = _cursor.getDouble(_cursorIndexOfIncomeYear5);
        _result.deRatioYear1 = _cursor.getDouble(_cursorIndexOfDeRatioYear1);
        _result.deRatioYear2 = _cursor.getDouble(_cursorIndexOfDeRatioYear2);
        _result.deRatioYear3 = _cursor.getDouble(_cursorIndexOfDeRatioYear3);
        _result.deRatioYear4 = _cursor.getDouble(_cursorIndexOfDeRatioYear4);
        _result.deRatioYear5 = _cursor.getDouble(_cursorIndexOfDeRatioYear5);
        _result.dividendYear1 = _cursor.getDouble(_cursorIndexOfDividendYear1);
        _result.dividendYear2 = _cursor.getDouble(_cursorIndexOfDividendYear2);
        _result.dividendYear3 = _cursor.getDouble(_cursorIndexOfDividendYear3);
        _result.dividendYear4 = _cursor.getDouble(_cursorIndexOfDividendYear4);
        _result.dividendYear5 = _cursor.getDouble(_cursorIndexOfDividendYear5);
        _result.peRatio = _cursor.getDouble(_cursorIndexOfPeRatio);
        _result.promoterHolding = _cursor.getDouble(_cursorIndexOfPromoterHolding);
        _result.epsYear1 = _cursor.getDouble(_cursorIndexOfEpsYear1);
        _result.epsYear2 = _cursor.getDouble(_cursorIndexOfEpsYear2);
        _result.epsYear3 = _cursor.getDouble(_cursorIndexOfEpsYear3);
        _result.epsYear4 = _cursor.getDouble(_cursorIndexOfEpsYear4);
        _result.epsYear5 = _cursor.getDouble(_cursorIndexOfEpsYear5);
        _result.priceMonth1 = _cursor.getDouble(_cursorIndexOfPriceMonth1);
        _result.priceMonth2 = _cursor.getDouble(_cursorIndexOfPriceMonth2);
        _result.priceMonth3 = _cursor.getDouble(_cursorIndexOfPriceMonth3);
        _result.priceMonth4 = _cursor.getDouble(_cursorIndexOfPriceMonth4);
        _result.priceMonth5 = _cursor.getDouble(_cursorIndexOfPriceMonth5);
        _result.priceMonth6 = _cursor.getDouble(_cursorIndexOfPriceMonth6);
        _result.priceMonth7 = _cursor.getDouble(_cursorIndexOfPriceMonth7);
        _result.priceMonth8 = _cursor.getDouble(_cursorIndexOfPriceMonth8);
        _result.priceMonth9 = _cursor.getDouble(_cursorIndexOfPriceMonth9);
        _result.priceMonth10 = _cursor.getDouble(_cursorIndexOfPriceMonth10);
        _result.priceMonth11 = _cursor.getDouble(_cursorIndexOfPriceMonth11);
        _result.priceMonth12 = _cursor.getDouble(_cursorIndexOfPriceMonth12);
        final int _tmp;
        _tmp = _cursor.getInt(_cursorIndexOfIsFavourite);
        _result.isFavourite = _tmp != 0;
      } else {
        _result = null;
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @Override
  public CompanyFundamentals getByTicker(final String ticker) {
    final String _sql = "SELECT * FROM company_fundamentals WHERE ticker = ? LIMIT 1";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    if (ticker == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, ticker);
    }
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
      final int _cursorIndexOfName = CursorUtil.getColumnIndexOrThrow(_cursor, "name");
      final int _cursorIndexOfTicker = CursorUtil.getColumnIndexOrThrow(_cursor, "ticker");
      final int _cursorIndexOfSector = CursorUtil.getColumnIndexOrThrow(_cursor, "sector");
      final int _cursorIndexOfIncomeYear1 = CursorUtil.getColumnIndexOrThrow(_cursor, "income_year1");
      final int _cursorIndexOfIncomeYear2 = CursorUtil.getColumnIndexOrThrow(_cursor, "income_year2");
      final int _cursorIndexOfIncomeYear3 = CursorUtil.getColumnIndexOrThrow(_cursor, "income_year3");
      final int _cursorIndexOfIncomeYear4 = CursorUtil.getColumnIndexOrThrow(_cursor, "income_year4");
      final int _cursorIndexOfIncomeYear5 = CursorUtil.getColumnIndexOrThrow(_cursor, "income_year5");
      final int _cursorIndexOfDeRatioYear1 = CursorUtil.getColumnIndexOrThrow(_cursor, "de_ratio_year1");
      final int _cursorIndexOfDeRatioYear2 = CursorUtil.getColumnIndexOrThrow(_cursor, "de_ratio_year2");
      final int _cursorIndexOfDeRatioYear3 = CursorUtil.getColumnIndexOrThrow(_cursor, "de_ratio_year3");
      final int _cursorIndexOfDeRatioYear4 = CursorUtil.getColumnIndexOrThrow(_cursor, "de_ratio_year4");
      final int _cursorIndexOfDeRatioYear5 = CursorUtil.getColumnIndexOrThrow(_cursor, "de_ratio_year5");
      final int _cursorIndexOfDividendYear1 = CursorUtil.getColumnIndexOrThrow(_cursor, "dividend_year1");
      final int _cursorIndexOfDividendYear2 = CursorUtil.getColumnIndexOrThrow(_cursor, "dividend_year2");
      final int _cursorIndexOfDividendYear3 = CursorUtil.getColumnIndexOrThrow(_cursor, "dividend_year3");
      final int _cursorIndexOfDividendYear4 = CursorUtil.getColumnIndexOrThrow(_cursor, "dividend_year4");
      final int _cursorIndexOfDividendYear5 = CursorUtil.getColumnIndexOrThrow(_cursor, "dividend_year5");
      final int _cursorIndexOfPeRatio = CursorUtil.getColumnIndexOrThrow(_cursor, "pe_ratio");
      final int _cursorIndexOfPromoterHolding = CursorUtil.getColumnIndexOrThrow(_cursor, "promoter_holding");
      final int _cursorIndexOfEpsYear1 = CursorUtil.getColumnIndexOrThrow(_cursor, "eps_year1");
      final int _cursorIndexOfEpsYear2 = CursorUtil.getColumnIndexOrThrow(_cursor, "eps_year2");
      final int _cursorIndexOfEpsYear3 = CursorUtil.getColumnIndexOrThrow(_cursor, "eps_year3");
      final int _cursorIndexOfEpsYear4 = CursorUtil.getColumnIndexOrThrow(_cursor, "eps_year4");
      final int _cursorIndexOfEpsYear5 = CursorUtil.getColumnIndexOrThrow(_cursor, "eps_year5");
      final int _cursorIndexOfPriceMonth1 = CursorUtil.getColumnIndexOrThrow(_cursor, "price_month1");
      final int _cursorIndexOfPriceMonth2 = CursorUtil.getColumnIndexOrThrow(_cursor, "price_month2");
      final int _cursorIndexOfPriceMonth3 = CursorUtil.getColumnIndexOrThrow(_cursor, "price_month3");
      final int _cursorIndexOfPriceMonth4 = CursorUtil.getColumnIndexOrThrow(_cursor, "price_month4");
      final int _cursorIndexOfPriceMonth5 = CursorUtil.getColumnIndexOrThrow(_cursor, "price_month5");
      final int _cursorIndexOfPriceMonth6 = CursorUtil.getColumnIndexOrThrow(_cursor, "price_month6");
      final int _cursorIndexOfPriceMonth7 = CursorUtil.getColumnIndexOrThrow(_cursor, "price_month7");
      final int _cursorIndexOfPriceMonth8 = CursorUtil.getColumnIndexOrThrow(_cursor, "price_month8");
      final int _cursorIndexOfPriceMonth9 = CursorUtil.getColumnIndexOrThrow(_cursor, "price_month9");
      final int _cursorIndexOfPriceMonth10 = CursorUtil.getColumnIndexOrThrow(_cursor, "price_month10");
      final int _cursorIndexOfPriceMonth11 = CursorUtil.getColumnIndexOrThrow(_cursor, "price_month11");
      final int _cursorIndexOfPriceMonth12 = CursorUtil.getColumnIndexOrThrow(_cursor, "price_month12");
      final int _cursorIndexOfIsFavourite = CursorUtil.getColumnIndexOrThrow(_cursor, "is_favourite");
      final CompanyFundamentals _result;
      if(_cursor.moveToFirst()) {
        _result = new CompanyFundamentals();
        _result.id = _cursor.getInt(_cursorIndexOfId);
        if (_cursor.isNull(_cursorIndexOfName)) {
          _result.name = null;
        } else {
          _result.name = _cursor.getString(_cursorIndexOfName);
        }
        if (_cursor.isNull(_cursorIndexOfTicker)) {
          _result.ticker = null;
        } else {
          _result.ticker = _cursor.getString(_cursorIndexOfTicker);
        }
        if (_cursor.isNull(_cursorIndexOfSector)) {
          _result.sector = null;
        } else {
          _result.sector = _cursor.getString(_cursorIndexOfSector);
        }
        _result.incomeYear1 = _cursor.getDouble(_cursorIndexOfIncomeYear1);
        _result.incomeYear2 = _cursor.getDouble(_cursorIndexOfIncomeYear2);
        _result.incomeYear3 = _cursor.getDouble(_cursorIndexOfIncomeYear3);
        _result.incomeYear4 = _cursor.getDouble(_cursorIndexOfIncomeYear4);
        _result.incomeYear5 = _cursor.getDouble(_cursorIndexOfIncomeYear5);
        _result.deRatioYear1 = _cursor.getDouble(_cursorIndexOfDeRatioYear1);
        _result.deRatioYear2 = _cursor.getDouble(_cursorIndexOfDeRatioYear2);
        _result.deRatioYear3 = _cursor.getDouble(_cursorIndexOfDeRatioYear3);
        _result.deRatioYear4 = _cursor.getDouble(_cursorIndexOfDeRatioYear4);
        _result.deRatioYear5 = _cursor.getDouble(_cursorIndexOfDeRatioYear5);
        _result.dividendYear1 = _cursor.getDouble(_cursorIndexOfDividendYear1);
        _result.dividendYear2 = _cursor.getDouble(_cursorIndexOfDividendYear2);
        _result.dividendYear3 = _cursor.getDouble(_cursorIndexOfDividendYear3);
        _result.dividendYear4 = _cursor.getDouble(_cursorIndexOfDividendYear4);
        _result.dividendYear5 = _cursor.getDouble(_cursorIndexOfDividendYear5);
        _result.peRatio = _cursor.getDouble(_cursorIndexOfPeRatio);
        _result.promoterHolding = _cursor.getDouble(_cursorIndexOfPromoterHolding);
        _result.epsYear1 = _cursor.getDouble(_cursorIndexOfEpsYear1);
        _result.epsYear2 = _cursor.getDouble(_cursorIndexOfEpsYear2);
        _result.epsYear3 = _cursor.getDouble(_cursorIndexOfEpsYear3);
        _result.epsYear4 = _cursor.getDouble(_cursorIndexOfEpsYear4);
        _result.epsYear5 = _cursor.getDouble(_cursorIndexOfEpsYear5);
        _result.priceMonth1 = _cursor.getDouble(_cursorIndexOfPriceMonth1);
        _result.priceMonth2 = _cursor.getDouble(_cursorIndexOfPriceMonth2);
        _result.priceMonth3 = _cursor.getDouble(_cursorIndexOfPriceMonth3);
        _result.priceMonth4 = _cursor.getDouble(_cursorIndexOfPriceMonth4);
        _result.priceMonth5 = _cursor.getDouble(_cursorIndexOfPriceMonth5);
        _result.priceMonth6 = _cursor.getDouble(_cursorIndexOfPriceMonth6);
        _result.priceMonth7 = _cursor.getDouble(_cursorIndexOfPriceMonth7);
        _result.priceMonth8 = _cursor.getDouble(_cursorIndexOfPriceMonth8);
        _result.priceMonth9 = _cursor.getDouble(_cursorIndexOfPriceMonth9);
        _result.priceMonth10 = _cursor.getDouble(_cursorIndexOfPriceMonth10);
        _result.priceMonth11 = _cursor.getDouble(_cursorIndexOfPriceMonth11);
        _result.priceMonth12 = _cursor.getDouble(_cursorIndexOfPriceMonth12);
        final int _tmp;
        _tmp = _cursor.getInt(_cursorIndexOfIsFavourite);
        _result.isFavourite = _tmp != 0;
      } else {
        _result = null;
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @Override
  public int getCompanyCount() {
    final String _sql = "SELECT COUNT(*) FROM company_fundamentals";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _result;
      if(_cursor.moveToFirst()) {
        _result = _cursor.getInt(0);
      } else {
        _result = 0;
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  public static List<Class<?>> getRequiredConverters() {
    return Collections.emptyList();
  }
}
