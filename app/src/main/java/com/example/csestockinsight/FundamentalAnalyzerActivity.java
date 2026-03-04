package com.example.csestockinsight;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.example.csestockinsight.adapter.CompanyAdapter;
import com.example.csestockinsight.data.CompanyFundamentals;
import com.example.csestockinsight.data.CompanyRepository;

import java.util.ArrayList;
import java.util.List;

// refer: GitHub/Gemini
public class FundamentalAnalyzerActivity extends AppCompatActivity implements CompanyAdapter.OnCompanyClickListener {
    private CompanyAdapter adapter;
    private final List<CompanyFundamentals> companies = new ArrayList<>();
    private CompanyRepository repository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fundamental_analyzer);

        repository = new CompanyRepository(getApplicationContext());
        RecyclerView recyclerView = findViewById(R.id.recycler_companies);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new CompanyAdapter(companies, this);
        recyclerView.setAdapter(adapter);

        EditText searchBox = findViewById(R.id.edit_search);
        searchBox.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                adapter.filter(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {}
        });

        // refer: GitHub/Gemini
        ExecutorService executor = Executors.newSingleThreadExecutor();
        Handler mainHandler = new Handler(Looper.getMainLooper());

        executor.execute(() -> {
            List<CompanyFundamentals> list = repository.getAllCompaniesSync();
            companies.clear();
            companies.addAll(list);
            mainHandler.post(() -> {
                adapter = new CompanyAdapter(companies, FundamentalAnalyzerActivity.this);
                recyclerView.setAdapter(adapter);
            });
        });
    }

    @Override
    public void onCompanyClick(CompanyFundamentals company) {
        // Launch detail screen for selected company
        Intent intent = new Intent(this, CompanyDetailActivity.class);
        intent.putExtra("company_id", company.id);
        startActivity(intent);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (repository != null) {
            repository.shutdown();
        }
    }
}