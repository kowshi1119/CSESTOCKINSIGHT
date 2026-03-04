package com.example.csestockinsight;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import androidx.appcompat.app.AppCompatActivity;

import com.example.csestockinsight.data.CompanyFundamentals;
import com.example.csestockinsight.data.CompanyRepository;
import com.example.csestockinsight.network.StockPriceApiService;
import com.example.csestockinsight.network.StockPriceResponse;

import java.text.DecimalFormat;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Displays detailed fundamental information about a single company. Provides
 * actions for viewing charts and saving the company to a watchlist. Also
 * demonstrates a Retrofit API call to fetch a live (or mocked) stock price.
 */
public class CompanyDetailActivity extends AppCompatActivity {
    private CompanyRepository repository;
    private CompanyFundamentals company;

    private TextView tvIncome;
    private TextView tvDebtRatio;
    private TextView tvDividend;
    private TextView tvPe;
    private TextView tvPromoter;
    private TextView tvEps;
    private TextView tvLatestPrice;
    private Button btnSaveWatchlist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_company_detail);

        int companyId = getIntent().getIntExtra("company_id", -1);
        repository = new CompanyRepository(getApplicationContext());

        tvIncome = findViewById(R.id.tv_income);
        tvDebtRatio = findViewById(R.id.tv_debt_ratio);
        tvDividend = findViewById(R.id.tv_dividend);
        tvPe = findViewById(R.id.tv_pe_ratio);
        tvPromoter = findViewById(R.id.tv_promoter);
        tvEps = findViewById(R.id.tv_eps);
        tvLatestPrice = findViewById(R.id.tv_latest_price);
        btnSaveWatchlist = findViewById(R.id.btn_save_watchlist);

        // refer: GitHub/Gemini
        ExecutorService executor = Executors.newSingleThreadExecutor();
        Handler mainHandler = new Handler(Looper.getMainLooper());

        executor.execute(() -> {
            company = repository.getById(companyId);
            mainHandler.post(() -> {
                if (company != null) {
                    populateDetails(company);
                }
            });
        });

        findViewById(R.id.btn_show_charts).setOnClickListener(v -> {
            // Launch charts activity
            if (company != null) {
                startActivity(new android.content.Intent(this, FundamentalChartsActivity.class)
                        .putExtra("company_id", company.id));
            }
        });

        btnSaveWatchlist.setOnClickListener(v -> {
            if (company != null) {
                company.isFavourite = !company.isFavourite;
                repository.update(company);
                updateWatchlistButton();
                Toast.makeText(this,
                        company.isFavourite ? "Added to watchlist" : "Removed from watchlist",
                        Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void populateDetails(CompanyFundamentals c) {
        DecimalFormat df = new DecimalFormat("0.00");
        // Income line summarised as comma separated values
        String incomeStr = df.format(c.incomeYear1) + ", " + df.format(c.incomeYear2) + ", " + df.format(c.incomeYear3)
                + ", " + df.format(c.incomeYear4) + ", " + df.format(c.incomeYear5);
        tvIncome.setText(incomeStr);

        // Debt-to-equity ratio classification based on year5
        double lastDe = c.deRatioYear5;
        String deLabel = lastDe > 1.5 ? "High" : "Low";
        String deStr = df.format(c.deRatioYear1) + ", " + df.format(c.deRatioYear2) + ", " + df.format(c.deRatioYear3)
                + ", " + df.format(c.deRatioYear4) + ", " + df.format(c.deRatioYear5) + " (" + deLabel + ")";
        tvDebtRatio.setText(deStr);

        // Dividend history
        String dividendStr = df.format(c.dividendYear1) + ", " + df.format(c.dividendYear2) + ", " + df.format(c.dividendYear3)
                + ", " + df.format(c.dividendYear4) + ", " + df.format(c.dividendYear5);
        tvDividend.setText(dividendStr);

        // P/E ratio evaluation
        String peRange = c.peRatio >= 10 && c.peRatio <= 20 ? "Within ideal range" : "Outside ideal range";
        tvPe.setText(df.format(c.peRatio) + " (" + peRange + ")");

        // Promoter holding evaluation
        String promoterRange = c.promoterHolding >= 50 && c.promoterHolding <= 60 ? "Ideal" : "Out of range";
        tvPromoter.setText(df.format(c.promoterHolding) + "% (" + promoterRange + ")");

        // EPS growth rate over five years
        double growth = ((c.epsYear5 - c.epsYear1) / c.epsYear1) * 100.0;
        String epsStr = df.format(c.epsYear1) + ", " + df.format(c.epsYear2) + ", " + df.format(c.epsYear3)
                + ", " + df.format(c.epsYear4) + ", " + df.format(c.epsYear5) + "\nGrowth: " + df.format(growth) + "%";
        tvEps.setText(epsStr);

        // Set company summary views
        TextView tvName = findViewById(R.id.tv_company_name);
        TextView tvSector = findViewById(R.id.tv_company_sector);
        tvName.setText(c.name + " (" + c.ticker + ")");
        tvSector.setText(c.sector);

        updateWatchlistButton();

        // Trigger fetching latest price from API
        fetchLatestPrice(c.ticker);
    }

    /**
     * Update the watchlist button text based on favourite state.
     */
    private void updateWatchlistButton() {
        btnSaveWatchlist.setText(company != null && company.isFavourite ? "Remove from Watchlist" : "Save to Watchlist");
    }

    /**
     * Demonstrates a Retrofit call to fetch the latest price for a ticker. If the
     * call fails (because the endpoint is a placeholder or there's no network),
     * a dummy price is shown. Replace the base URL and endpoint with a real
     * service if available.
     */
    private void fetchLatestPrice(String ticker) {
        // Base URL is a placeholder; in a real app use the API provider's URL
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.example.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        StockPriceApiService service = retrofit.create(StockPriceApiService.class);
        Call<StockPriceResponse> call = service.getLatestPrice(ticker);
        call.enqueue(new Callback<StockPriceResponse>() {
            @Override
            public void onResponse(Call<StockPriceResponse> call, Response<StockPriceResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    tvLatestPrice.setText("LKR " + new DecimalFormat("0.00").format(response.body().price));
                } else {
                    // Use dummy value if API fails
                    tvLatestPrice.setText("LKR 0.00");
                }
            }

            @Override
            public void onFailure(Call<StockPriceResponse> call, Throwable t) {
                // Fallback dummy price on failure
                tvLatestPrice.setText("LKR 0.00");
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (repository != null) {
            repository.shutdown();
        }
    }
}