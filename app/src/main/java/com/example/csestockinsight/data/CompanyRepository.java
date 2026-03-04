package com.example.csestockinsight.data;

import android.content.Context;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

// refer: GitHub/Gemini
public class CompanyRepository {
    private final CompanyFundamentalsDao companyDao;
    // refer: GitHub/Gemini
    private final ExecutorService executor;

    public CompanyRepository(Context context) {
        AppDatabase db = AppDatabase.getInstance(context);
        companyDao = db.companyDao();
        executor = Executors.newFixedThreadPool(4);
    }

    /**
     * Retrieve a list of all companies in the database. Executes on the
     * calling thread (should be a background thread). For simplicity, this
     * method returns the list synchronously; you can wrap it in an ExecutorService
     * when calling from the UI thread.
     */
    public List<CompanyFundamentals> getAllCompaniesSync() {
        return companyDao.getAllCompanies();
    }

    /**
     * Retrieve a company by its ID synchronously.
     */
    public CompanyFundamentals getById(int id) {
        return companyDao.getById(id);
    }

    /**
     * Update a company record in the database asynchronously.
     */
    public void update(CompanyFundamentals company) {
        executor.execute(() -> companyDao.update(company));
    }

    /**
     * Insert a company asynchronously (used if adding new companies or
     * favourites). Not used in this assignment since dummy data is preloaded.
     */
    public void insert(CompanyFundamentals company) {
        executor.execute(() -> companyDao.insert(company));
    }

    /**
     * Shutdown the executor service when done.
     */
    public void shutdown() {
        if (executor != null && !executor.isShutdown()) {
            executor.shutdown();
        }
    }
}