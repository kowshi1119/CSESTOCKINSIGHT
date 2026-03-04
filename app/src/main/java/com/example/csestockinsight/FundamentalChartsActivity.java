package com.example.csestockinsight;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import androidx.appcompat.app.AppCompatActivity;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.example.csestockinsight.data.CompanyFundamentals;
import com.example.csestockinsight.data.CompanyRepository;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;

import android.graphics.Color;

import java.util.ArrayList;
import java.util.List;

/**
 * Activity showing the bar chart and pie chart for a company's fundamentals.
 */
public class FundamentalChartsActivity extends AppCompatActivity {
    private CompanyRepository repository;
    private CompanyFundamentals company;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fundamental_charts);

        int companyId = getIntent().getIntExtra("company_id", -1);
        repository = new CompanyRepository(getApplicationContext());
        BarChart barChart = findViewById(R.id.bar_chart);
        PieChart pieChart = findViewById(R.id.pie_chart);

        // refer: GitHub/Gemini
        ExecutorService executor = Executors.newSingleThreadExecutor();
        Handler mainHandler = new Handler(Looper.getMainLooper());

        executor.execute(() -> {
            company = repository.getById(companyId);
            mainHandler.post(() -> {
                if (company != null) {
                    setupBarChart(barChart);
                    setupPieChart(pieChart);
                }
            });
        });
    }

    private void setupBarChart(BarChart barChart) {
        // Entries for Income and EPS across five years
        List<BarEntry> incomeEntries = new ArrayList<>();
        incomeEntries.add(new BarEntry(1, (float) company.incomeYear1));
        incomeEntries.add(new BarEntry(2, (float) company.incomeYear2));
        incomeEntries.add(new BarEntry(3, (float) company.incomeYear3));
        incomeEntries.add(new BarEntry(4, (float) company.incomeYear4));
        incomeEntries.add(new BarEntry(5, (float) company.incomeYear5));

        List<BarEntry> epsEntries = new ArrayList<>();
        epsEntries.add(new BarEntry(1, (float) company.epsYear1));
        epsEntries.add(new BarEntry(2, (float) company.epsYear2));
        epsEntries.add(new BarEntry(3, (float) company.epsYear3));
        epsEntries.add(new BarEntry(4, (float) company.epsYear4));
        epsEntries.add(new BarEntry(5, (float) company.epsYear5));

        BarDataSet incomeDataSet = new BarDataSet(incomeEntries, "Income");
        BarDataSet epsDataSet = new BarDataSet(epsEntries, "EPS");

        // Use vibrant colours but avoid specifying too bright/neon colours. Without
        // specifying colours MPAndroidChart will auto assign, but we add some
        // custom colours for clarity.
        incomeDataSet.setColor(Color.parseColor("#0D47A1"));
        epsDataSet.setColor(Color.parseColor("#FB8C00"));

        BarData data = new BarData(incomeDataSet, epsDataSet);
        data.setBarWidth(0.4f);
        barChart.setData(data);
        barChart.groupBars(0.5f, 0.2f, 0.05f);
        Description description = new Description();
        description.setText("Income vs EPS over 5 years");
        barChart.setDescription(description);
        barChart.invalidate();
    }

    private void setupPieChart(PieChart pieChart) {
        // Promoter vs Public vs Institutional shareholding
        float promoter = (float) company.promoterHolding;
        float institutional = 10f; // fixed dummy value
        float publicHolding = 100f - promoter - institutional;
        List<PieEntry> entries = new ArrayList<>();
        entries.add(new PieEntry(promoter, "Promoter"));
        entries.add(new PieEntry(institutional, "Institutional"));
        entries.add(new PieEntry(publicHolding, "Public"));
        PieDataSet dataSet = new PieDataSet(entries, "Shareholding Breakdown");
        dataSet.setColors(new int[]{Color.parseColor("#0D47A1"), Color.parseColor("#00897B"), Color.parseColor("#FB8C00")});
        PieData data = new PieData(dataSet);
        pieChart.setData(data);
        Description description = new Description();
        description.setText("Shareholding Structure");
        pieChart.setDescription(description);
        pieChart.invalidate();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (repository != null) {
            repository.shutdown();
        }
    }
}