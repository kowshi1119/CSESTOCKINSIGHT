package com.example.csestockinsight.data;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

// refer: GitHub/Gemini
@Dao
public interface CompanyFundamentalsDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(CompanyFundamentals company);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(List<CompanyFundamentals> companies);

    @Update
    void update(CompanyFundamentals company);

    @Query("SELECT * FROM company_fundamentals ORDER BY name ASC")
    List<CompanyFundamentals> getAllCompanies();

    @Query("SELECT * FROM company_fundamentals WHERE id = :id LIMIT 1")
    CompanyFundamentals getById(int id);

    @Query("SELECT * FROM company_fundamentals WHERE ticker = :ticker LIMIT 1")
    CompanyFundamentals getByTicker(String ticker);

    @Query("SELECT COUNT(*) FROM company_fundamentals")
    int getCompanyCount();
}