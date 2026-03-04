package com.example.csestockinsight.data;

import android.content.Context;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

// refer: GitHub/Gemini
public class CompanyDataSeeder {

    private static final String TAG = "CompanyDataSeeder";
    private static final String JSON_FILE_NAME = "companies.json";

    /**
     * Seeds the database with company data from the JSON file in assets.
     * This method should be called on a background thread.
     *
     * @param context Application context to access assets
     * @param dao CompanyFundamentalsDao for database insertion
     */
    public static void seedDatabase(Context context, CompanyFundamentalsDao dao) {
        try {
            // Check if database already has data
            int count = dao.getCompanyCount();
            if (count > 0) {
                Log.d(TAG, "Database already seeded with " + count + " companies. Skipping.");
                return;
            }

            Log.d(TAG, "Starting database seeding...");

            // Read JSON file from assets
            String jsonString = loadJSONFromAssets(context);

            if (jsonString == null || jsonString.isEmpty()) {
                Log.e(TAG, "Failed to load JSON file from assets");
                return;
            }

            // Parse JSON and create company list
            List<CompanyFundamentals> companies = parseCompaniesFromJSON(jsonString);

            if (companies.isEmpty()) {
                Log.e(TAG, "No companies parsed from JSON");
                return;
            }

            // Insert all companies into database
            dao.insertAll(companies);

            Log.d(TAG, "Successfully seeded database with " + companies.size() + " companies");

        } catch (Exception e) {
            Log.e(TAG, "Error seeding database: " + e.getMessage(), e);
        }
    }

    /**
     * Loads the JSON file from the assets folder and returns it as a String.
     *
     * @param context Application context
     * @return JSON string or null if error occurs
     */
    private static String loadJSONFromAssets(Context context) {
        StringBuilder json = new StringBuilder();

        try {
            InputStream inputStream = context.getAssets().open(JSON_FILE_NAME);
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

            String line;
            while ((line = reader.readLine()) != null) {
                json.append(line);
            }

            reader.close();
            inputStream.close();

            return json.toString();

        } catch (IOException e) {
            Log.e(TAG, "Error reading JSON file from assets: " + e.getMessage(), e);
            return null;
        }
    }

    /**
     * Parses the JSON string and creates a list of CompanyFundamentals entities.
     *
     * JSON Structure:
     * [
     *   {
     *     "ticker": "JKH.N000",
     *     "name": "John Keells Holdings",
     *     "sector": "Conglomerate",
     *     "income5Years": [120.5, 135.2, 148.7, 162.3, 175.8],
     *     "debtEquity5Years": [0.75, 0.82, 0.95, 1.08, 1.15],
     *     "dividend5Years": [5.0, 5.5, 6.0, 6.5, 7.2],
     *     "eps5Years": [9.8, 11.2, 12.5, 13.8, 15.3],
     *     "peRatio": 16.5,
     *     "promoterHolding": 54.8,
     *     "monthlyPrices": [98.5, 102.3, 105.7, ...]
     *   },
     *   ...
     * ]
     *
     * @param jsonString JSON string containing company data
     * @return List of CompanyFundamentals entities
     */
    private static List<CompanyFundamentals> parseCompaniesFromJSON(String jsonString) {
        List<CompanyFundamentals> companies = new ArrayList<>();

        try {
            JSONArray jsonArray = new JSONArray(jsonString);

            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject companyJson = jsonArray.getJSONObject(i);

                CompanyFundamentals company = new CompanyFundamentals();

                // Basic information
                company.ticker = companyJson.getString("ticker");
                company.name = companyJson.getString("name");
                company.sector = companyJson.getString("sector");

                // Income for 5 years
                JSONArray incomeArray = companyJson.getJSONArray("income5Years");
                company.incomeYear1 = incomeArray.getDouble(0);
                company.incomeYear2 = incomeArray.getDouble(1);
                company.incomeYear3 = incomeArray.getDouble(2);
                company.incomeYear4 = incomeArray.getDouble(3);
                company.incomeYear5 = incomeArray.getDouble(4);

                // Debt-to-Equity ratio for 5 years
                JSONArray debtEquityArray = companyJson.getJSONArray("debtEquity5Years");
                company.deRatioYear1 = debtEquityArray.getDouble(0);
                company.deRatioYear2 = debtEquityArray.getDouble(1);
                company.deRatioYear3 = debtEquityArray.getDouble(2);
                company.deRatioYear4 = debtEquityArray.getDouble(3);
                company.deRatioYear5 = debtEquityArray.getDouble(4);

                // Dividend per share for 5 years
                JSONArray dividendArray = companyJson.getJSONArray("dividend5Years");
                company.dividendYear1 = dividendArray.getDouble(0);
                company.dividendYear2 = dividendArray.getDouble(1);
                company.dividendYear3 = dividendArray.getDouble(2);
                company.dividendYear4 = dividendArray.getDouble(3);
                company.dividendYear5 = dividendArray.getDouble(4);

                // EPS for 5 years
                JSONArray epsArray = companyJson.getJSONArray("eps5Years");
                company.epsYear1 = epsArray.getDouble(0);
                company.epsYear2 = epsArray.getDouble(1);
                company.epsYear3 = epsArray.getDouble(2);
                company.epsYear4 = epsArray.getDouble(3);
                company.epsYear5 = epsArray.getDouble(4);

                // P/E ratio and promoter holding
                company.peRatio = companyJson.getDouble("peRatio");
                company.promoterHolding = companyJson.getDouble("promoterHolding");

                // Monthly prices for last 12 months
                JSONArray pricesArray = companyJson.getJSONArray("monthlyPrices");
                company.priceMonth1 = pricesArray.getDouble(0);
                company.priceMonth2 = pricesArray.getDouble(1);
                company.priceMonth3 = pricesArray.getDouble(2);
                company.priceMonth4 = pricesArray.getDouble(3);
                company.priceMonth5 = pricesArray.getDouble(4);
                company.priceMonth6 = pricesArray.getDouble(5);
                company.priceMonth7 = pricesArray.getDouble(6);
                company.priceMonth8 = pricesArray.getDouble(7);
                company.priceMonth9 = pricesArray.getDouble(8);
                company.priceMonth10 = pricesArray.getDouble(9);
                company.priceMonth11 = pricesArray.getDouble(10);
                company.priceMonth12 = pricesArray.getDouble(11);

                // Default isFavourite to false
                company.isFavourite = false;

                companies.add(company);

                Log.d(TAG, "Parsed company: " + company.name + " (" + company.ticker + ")");
            }

        } catch (JSONException e) {
            Log.e(TAG, "Error parsing JSON: " + e.getMessage(), e);
        }

        return companies;
    }
}
