package com.example.csestockinsight.network;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

// refer: GitHub/Gemini
public interface StockPriceApiService {
    @GET("/stock/{ticker}/price")
    Call<StockPriceResponse> getLatestPrice(@Path("ticker") String ticker);
}