package com.example.csestockinsight;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import androidx.appcompat.app.AppCompatActivity;

import com.example.csestockinsight.data.CompanyFundamentals;
import com.example.csestockinsight.data.CompanyRepository;
import com.example.csestockinsight.network.CurrencyApiService;
import com.example.csestockinsight.network.CurrencyResponse;
import com.example.csestockinsight.network.StockPriceApiService;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

// refer: GitHub/Gemini
public class TechnicalForecastActivity extends AppCompatActivity {
    private CompanyRepository repository;
    private List<CompanyFundamentals> companies;
    private CompanyFundamentals selectedCompany;
    private LineChart lineChart;
    private TextView tvCurrentPrice;
    private TextView tvCurrentPriceUsd;
    private TextView tvMovingAverage;
    private TextView tvSignal;
    private TextView tvForecast;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_technical_forecast);
        repository = new CompanyRepository(getApplicationContext());
        lineChart = findViewById(R.id.line_chart);
        tvCurrentPrice = findViewById(R.id.tv_current_price);
        tvCurrentPriceUsd = findViewById(R.id.tv_current_price_usd);
        tvMovingAverage = findViewById(R.id.tv_moving_average);
        tvSignal = findViewById(R.id.tv_signal);
        tvForecast = findViewById(R.id.tv_forecast);

        Spinner spinner = findViewById(R.id.spinner_companies);
        companies = new ArrayList<>();

        // refer: GitHub/Gemini
        // Load companies from DB asynchronously
        ExecutorService executor = Executors.newSingleThreadExecutor();
        Handler mainHandler = new Handler(Looper.getMainLooper());

        executor.execute(() -> {
            companies.addAll(repository.getAllCompaniesSync());
            List<String> names = new ArrayList<>();
            for (CompanyFundamentals c : companies) {
                names.add(c.name + " (" + c.ticker + ")");
            }
            mainHandler.post(() -> {
                ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                        android.R.layout.simple_spinner_dropdown_item, names);
                spinner.setAdapter(adapter);
            });
        });

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedCompany = companies.get(position);
                displayTechnicalData();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Do nothing
            }
        });
    }

    /**
     * Loads chart, calculates moving average and forecast, and performs API
     * calls to retrieve current price and currency conversion. All heavy work
     * happens on background threads.
     */
    private void displayTechnicalData() {
        if (selectedCompany == null) return;
        loadLineChart();
        calculateMovingAverageAndSignal();
        fetchCurrentPriceAndConversion();
        calculateForecast();
    }

    private void loadLineChart() {
        List<Entry> entries = new ArrayList<>();
        // There are 12 monthly price fields; map them sequentially on x-axis
        entries.add(new Entry(1, (float) selectedCompany.priceMonth1));
        entries.add(new Entry(2, (float) selectedCompany.priceMonth2));
        entries.add(new Entry(3, (float) selectedCompany.priceMonth3));
        entries.add(new Entry(4, (float) selectedCompany.priceMonth4));
        entries.add(new Entry(5, (float) selectedCompany.priceMonth5));
        entries.add(new Entry(6, (float) selectedCompany.priceMonth6));
        entries.add(new Entry(7, (float) selectedCompany.priceMonth7));
        entries.add(new Entry(8, (float) selectedCompany.priceMonth8));
        entries.add(new Entry(9, (float) selectedCompany.priceMonth9));
        entries.add(new Entry(10, (float) selectedCompany.priceMonth10));
        entries.add(new Entry(11, (float) selectedCompany.priceMonth11));
        entries.add(new Entry(12, (float) selectedCompany.priceMonth12));
        LineDataSet dataSet = new LineDataSet(entries, "Price History");
        dataSet.setLineWidth(2f);
        dataSet.setCircleRadius(4f);
        // Without specifying colours MPAndroidChart uses default theme colours.
        LineData lineData = new LineData(dataSet);
        lineChart.setData(lineData);
        Description desc = new Description();
        desc.setText("Monthly Price (last 12 months)");
        lineChart.setDescription(desc);
        lineChart.invalidate();
    }

    private void calculateMovingAverageAndSignal() {
        // Simple moving average of the last 6 months (or all 12 if desired)
        double[] prices = new double[]{
                selectedCompany.priceMonth7, selectedCompany.priceMonth8, selectedCompany.priceMonth9,
                selectedCompany.priceMonth10, selectedCompany.priceMonth11, selectedCompany.priceMonth12
        };
        double sum = 0;
        for (double p : prices) sum += p;
        double movingAvg = sum / prices.length;
        double currentPrice = selectedCompany.priceMonth12;
        DecimalFormat df = new DecimalFormat("0.00");
        tvMovingAverage.setText("LKR " + df.format(movingAvg));
        // Determine signal: bullish if current price > moving average by >5%, bearish if < -5%
        double diffPct = (currentPrice - movingAvg) / movingAvg * 100.0;
        String signal;
        if (diffPct > 5) signal = getString(R.string.signal_bullish);
        else if (diffPct < -5) signal = getString(R.string.signal_bearish);
        else signal = getString(R.string.signal_neutral);
        tvSignal.setText(signal);
    }

    /**
     * Calls stock price API and currency conversion API. If either call fails
     * dummy values are displayed instead. Both calls run asynchronously.
     */
    private void fetchCurrentPriceAndConversion() {
        // refer: GitHub/Gemini
        // Configure Retrofit instance
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.example.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        StockPriceApiService stockService = retrofit.create(StockPriceApiService.class);
        CurrencyApiService currencyService = retrofit.create(CurrencyApiService.class);

        // Fetch latest price
        stockService.getLatestPrice(selectedCompany.ticker).enqueue(new Callback<com.example.csestockinsight.network.StockPriceResponse>() {
            @Override
            public void onResponse(Call<com.example.csestockinsight.network.StockPriceResponse> call, Response<com.example.csestockinsight.network.StockPriceResponse> response) {
                double price;
                if (response.isSuccessful() && response.body() != null) {
                    price = response.body().price;
                } else {
                    price = selectedCompany.priceMonth12; // fallback to last month price
                }
                updatePriceDisplays(price, currencyService);
            }

            @Override
            public void onFailure(Call<com.example.csestockinsight.network.StockPriceResponse> call, Throwable t) {
                // Use last price on failure
                double price = selectedCompany.priceMonth12;
                updatePriceDisplays(price, currencyService);
            }
        });
    }

    private void updatePriceDisplays(double priceLkr, CurrencyApiService currencyService) {
        DecimalFormat df = new DecimalFormat("0.00");
        tvCurrentPrice.setText("LKR " + df.format(priceLkr));
        // Fetch conversion to USD; use dummy conversion if fails
        currencyService.convert("LKR", "USD", priceLkr).enqueue(new Callback<CurrencyResponse>() {
            @Override
            public void onResponse(Call<CurrencyResponse> call, Response<CurrencyResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    double result = response.body().result;
                    tvCurrentPriceUsd.setText("USD " + df.format(result));
                } else {
                    tvCurrentPriceUsd.setText("USD " + df.format(priceLkr * 0.003));
                }
            }
            @Override
            public void onFailure(Call<CurrencyResponse> call, Throwable t) {
                // Dummy conversion: assume 1 USD = 330 LKR
                double usd = priceLkr / 330.0;
                tvCurrentPriceUsd.setText("USD " + df.format(usd));
            }
        });
    }

    /**
     * Calculates a naive forecast for the next year based on the average growth
     * over the past 12 months. Displays a range of ±10% around the forecast
     * value to acknowledge uncertainty.
     */
    private void calculateForecast() {
        double firstPrice = selectedCompany.priceMonth1;
        double lastPrice = selectedCompany.priceMonth12;
        double monthlyGrowth = (lastPrice - firstPrice) / 11.0; // 11 intervals
        double forecastPrice = lastPrice + (12 * monthlyGrowth);
        double lower = forecastPrice * 0.9;
        double upper = forecastPrice * 1.1;
        DecimalFormat df = new DecimalFormat("0.00");
        tvForecast.setText(df.format(lower) + " – " + df.format(upper) + " LKR");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (repository != null) {
            repository.shutdown();
        }
    }
}

