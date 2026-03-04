package com.example.csestockinsight.data;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.RoomDatabase.Callback;
import androidx.sqlite.db.SupportSQLiteDatabase;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

// refer: GitHub/Gemini
@Database(entities = {CompanyFundamentals.class}, version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {
    private static volatile AppDatabase INSTANCE;

    public abstract CompanyFundamentalsDao companyDao();

    /**
     * Singleton access method to obtain the database instance.
     *
     * @param context application context
     * @return the singleton database instance
     */
    public static AppDatabase getInstance(final Context context) {
        if (INSTANCE == null) {
            synchronized (AppDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            AppDatabase.class, "company_database")
                            .addCallback(new Callback() {
                                @Override
                                public void onCreate(@NonNull SupportSQLiteDatabase db) {
                                    super.onCreate(db);
                                    // refer: GitHub/Gemini
                                    // Populate database from JSON file when it's first created
                                    ExecutorService executor = Executors.newSingleThreadExecutor();
                                    executor.execute(() -> {
                                        CompanyDataSeeder.seedDatabase(
                                                context.getApplicationContext(),
                                                getInstance(context).companyDao()
                                        );
                                        executor.shutdown();
                                    });
                                }
                            })
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}
