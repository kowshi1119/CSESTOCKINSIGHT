package com.example.csestockinsight.network;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Retrofit interface for converting currency values. In a real application
 * this might call an API like exchangerate-api.com or openexchangerates.org.
 * Here we define a simple endpoint that converts from LKR to USD for a
 * specified amount. The response is mapped to CurrencyResponse.
 */
public interface CurrencyApiService {
    @GET("/convert")
    Call<CurrencyResponse> convert(@Query("from") String from,
                                   @Query("to") String to,
                                   @Query("amount") double amount);
}