package com.example.csestockinsight;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

/**
 * Data Access Object (DAO) for User entity.
 * Defines database operations for user authentication.
 */
@Dao
public interface UserDao {

    /**
     * Insert a new user into the database.
     * @param user User object to insert
     * @return The row ID of the newly inserted user
     */
    @Insert
    long insertUser(User user);

    /**
     * Find a user by email or username for login authentication.
     * @param emailOrUsername Email or username to search for
     * @return User object if found, null otherwise
     */
    @Query("SELECT * FROM users WHERE email = :emailOrUsername OR username = :emailOrUsername LIMIT 1")
    User findByEmailOrUsername(String emailOrUsername);

    /**
     * Check if a username already exists (for registration validation).
     * @param username Username to check
     * @return User object if username exists, null otherwise
     */
    @Query("SELECT * FROM users WHERE username = :username LIMIT 1")
    User findByUsername(String username);

    /**
     * Check if an email already exists (for registration validation).
     * @param email Email to check
     * @return User object if email exists, null otherwise
     */
    @Query("SELECT * FROM users WHERE email = :email LIMIT 1")
    User findByEmail(String email);

    /**
     * Get all users (for debugging/admin purposes).
     * @return List of all users
     */
    @Query("SELECT * FROM users")
    java.util.List<User> getAllUsers();

    /**
     * Delete all users (for testing/reset purposes).
     */
    @Query("DELETE FROM users")
    void deleteAll();
}
