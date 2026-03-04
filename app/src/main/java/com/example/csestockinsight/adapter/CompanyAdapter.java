package com.example.csestockinsight.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.csestockinsight.R;
import com.example.csestockinsight.data.CompanyFundamentals;

import java.util.ArrayList;
import java.util.List;

/**
 * Adapter for displaying a list of companies in a RecyclerView. Provides a
 * simple filterable list and click callback.
 */
public class CompanyAdapter extends RecyclerView.Adapter<CompanyAdapter.ViewHolder> {
    private List<CompanyFundamentals> allCompanies;
    private List<CompanyFundamentals> filteredCompanies;
    private final OnCompanyClickListener listener;

    public interface OnCompanyClickListener {
        void onCompanyClick(CompanyFundamentals company);
    }

    public CompanyAdapter(List<CompanyFundamentals> companies, OnCompanyClickListener listener) {
        this.allCompanies = new ArrayList<>(companies);
        this.filteredCompanies = new ArrayList<>(companies);
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_company, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        CompanyFundamentals company = filteredCompanies.get(position);
        holder.nameText.setText(company.name);
        holder.tickerText.setText(company.ticker);
        holder.itemView.setOnClickListener(v -> listener.onCompanyClick(company));
    }

    @Override
    public int getItemCount() {
        return filteredCompanies.size();
    }

    /**
     * Filters the list based on the query string. This is a simple case-insensitive
     * search over the company name and ticker symbol.
     *
     * @param query user search query
     */
    public void filter(String query) {
        filteredCompanies.clear();
        if (query == null || query.trim().isEmpty()) {
            filteredCompanies.addAll(allCompanies);
        } else {
            String lower = query.toLowerCase();
            for (CompanyFundamentals c : allCompanies) {
                if (c.name.toLowerCase().contains(lower) || c.ticker.toLowerCase().contains(lower)) {
                    filteredCompanies.add(c);
                }
            }
        }
        notifyDataSetChanged();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView nameText;
        TextView tickerText;
        ViewHolder(@NonNull View itemView) {
            super(itemView);
            nameText = itemView.findViewById(R.id.tv_company_name);
            tickerText = itemView.findViewById(R.id.tv_company_ticker);
        }
    }
}