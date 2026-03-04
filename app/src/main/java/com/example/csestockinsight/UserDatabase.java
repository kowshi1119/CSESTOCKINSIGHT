package com.example.csestockinsight;

import android.content.Context;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

// refer: GitHub/Gemini
@Database(entities = {User.class}, version = 1, exportSchema = false)
public abstract class UserDatabase extends RoomDatabase {

    private static UserDatabase instance;

    /**
     * Abstract method to get UserDao.
     * Room will generate the implementation.
     */
    public abstract UserDao userDao();

    /**
     * Get singleton instance of UserDatabase.
     * Thread-safe implementation with double-checked locking.
     *
     * @param context Application context
     * @return Singleton instance of UserDatabase
     */
    public static synchronized UserDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(
                    context.getApplicationContext(),
                    UserDatabase.class,
                    "cse_stock_insight_users_db"
            )
            .fallbackToDestructiveMigration() // In production, use proper migrations
            .build();
        }
        return instance;
    }
}
