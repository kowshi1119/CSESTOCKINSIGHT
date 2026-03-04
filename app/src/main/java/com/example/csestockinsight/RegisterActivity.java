package com.example.csestockinsight;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.card.MaterialCardView;
import com.google.android.material.checkbox.MaterialCheckBox;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * RegisterActivity - New user registration screen.
 *
 * Features:
 * - Full name, username, email, password, and confirm password inputs
 * - Comprehensive client-side validation
 * - Password strength requirements (minimum 8 characters, at least one number)
 * - Password confirmation matching
 * - Terms & Conditions checkbox
 * - Duplicate email/username checking
 * - User creation in local Room database
 * - Clear error messages for each validation rule
 * - Loading indicator during registration
 * - Success feedback and automatic navigation to LoginActivity
 *
 * Design: Material Design 3 with card-based layout on gradient background
 */
// refer: GitHub/Gemini
public class RegisterActivity extends AppCompatActivity {

    // UI Components
    private MaterialCardView cardRegister;
    private TextInputLayout tilFullName, tilUsername, tilEmail, tilPassword, tilConfirmPassword;
    private TextInputEditText etFullName, etUsername, etEmail, etPassword, etConfirmPassword;
    private MaterialCheckBox cbTerms;
    private MaterialButton btnSignUp;
    private ProgressBar progressBar;
    private TextView tvLogin;

    // refer: GitHub/Gemini
    // Database
    private UserDatabase userDatabase;
    private UserDao userDao;
    private ExecutorService executorService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        // Initialize database
        userDatabase = UserDatabase.getInstance(this);
        userDao = userDatabase.userDao();
        executorService = Executors.newSingleThreadExecutor();

        // Initialize views
        initializeViews();

        // Set up listeners
        setupClickListeners();

        // Add card entrance animation
        animateCard();
    }

    /**
     * Initialize all view references.
     */
    private void initializeViews() {
        cardRegister = findViewById(R.id.cardRegister);
        tilFullName = findViewById(R.id.tilFullName);
        tilUsername = findViewById(R.id.tilUsername);
        tilEmail = findViewById(R.id.tilEmail);
        tilPassword = findViewById(R.id.tilPassword);
        tilConfirmPassword = findViewById(R.id.tilConfirmPassword);
        etFullName = findViewById(R.id.etFullName);
        etUsername = findViewById(R.id.etUsername);
        etEmail = findViewById(R.id.etEmail);
        etPassword = findViewById(R.id.etPassword);
        etConfirmPassword = findViewById(R.id.etConfirmPassword);
        cbTerms = findViewById(R.id.cbTerms);
        btnSignUp = findViewById(R.id.btnSignUp);
        progressBar = findViewById(R.id.progressBar);
        tvLogin = findViewById(R.id.tvLogin);
    }

    /**
     * Set up click listeners for all interactive elements.
     */
    private void setupClickListeners() {
        // Sign up button click
        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                attemptRegistration();
            }
        });

        // Navigate to LoginActivity
        tvLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish(); // Go back to login
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
            }
        });
    }

    /**
     * Attempt to register a new user.
     * Performs comprehensive validation before creating user in database.
     */
    private void attemptRegistration() {
        // Clear all previous errors
        clearErrors();

        // Get input values
        String fullName = etFullName.getText().toString().trim();
        String username = etUsername.getText().toString().trim();
        String email = etEmail.getText().toString().trim();
        String password = etPassword.getText().toString();
        String confirmPassword = etConfirmPassword.getText().toString();
        boolean termsAccepted = cbTerms.isChecked();

        // Validate all inputs
        boolean isValid = true;

        // 1. Validate full name (cannot be empty)
        if (!AuthValidator.isNotEmpty(fullName)) {
            tilFullName.setError(getString(R.string.error_empty_full_name));
            isValid = false;
        }

        // 2. Validate username (not empty and minimum length)
        if (!AuthValidator.isNotEmpty(username)) {
            tilUsername.setError(getString(R.string.error_empty_username));
            isValid = false;
        } else if (!AuthValidator.isValidUsername(username)) {
            tilUsername.setError(getString(R.string.error_username_too_short));
            isValid = false;
        }

        // 3. Validate email (proper email format)
        if (!AuthValidator.isNotEmpty(email)) {
            tilEmail.setError(getString(R.string.error_empty_email));
            isValid = false;
        } else if (!AuthValidator.isValidEmail(email)) {
            tilEmail.setError(getString(R.string.error_invalid_email));
            isValid = false;
        }

        // 4. Validate password (minimum length and strength)
        if (!AuthValidator.isNotEmpty(password)) {
            tilPassword.setError(getString(R.string.error_empty_password));
            isValid = false;
        } else if (!AuthValidator.isPasswordLengthValid(password)) {
            tilPassword.setError(getString(R.string.error_password_too_short));
            isValid = false;
        } else if (!AuthValidator.isPasswordStrong(password)) {
            tilPassword.setError(getString(R.string.error_password_weak));
            isValid = false;
        }

        // 5. Validate password confirmation (must match password)
        if (!AuthValidator.doPasswordsMatch(password, confirmPassword)) {
            tilConfirmPassword.setError(getString(R.string.error_password_mismatch));
            isValid = false;
        }

        // 6. Validate terms and conditions acceptance
        if (!termsAccepted) {
            Snackbar.make(cardRegister, R.string.error_terms_not_accepted, Snackbar.LENGTH_LONG)
                    .setBackgroundTint(getResources().getColor(R.color.auth_error))
                    .show();
            isValid = false;
        }

        // If any validation fails, don't proceed
        if (!isValid) {
            return;
        }

        // Show loading state
        setLoadingState(true);

        // Perform registration in background thread
        final String finalFullName = fullName;
        final String finalUsername = username;
        final String finalEmail = email;
        final String finalPassword = password;

        executorService.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    // Check if username already exists
                    User existingUsername = userDao.findByUsername(finalUsername);
                    if (existingUsername != null) {
                        // Username already taken
                        new Handler(Looper.getMainLooper()).post(new Runnable() {
                            @Override
                            public void run() {
                                setLoadingState(false);
                                tilUsername.setError("Username already taken");
                                Snackbar.make(cardRegister, "Username already exists. Please choose another.",
                                        Snackbar.LENGTH_LONG)
                                        .setBackgroundTint(getResources().getColor(R.color.auth_error))
                                        .show();
                            }
                        });
                        return;
                    }

                    // Check if email already exists
                    User existingEmail = userDao.findByEmail(finalEmail);
                    if (existingEmail != null) {
                        // Email already registered
                        new Handler(Looper.getMainLooper()).post(new Runnable() {
                            @Override
                            public void run() {
                                setLoadingState(false);
                                tilEmail.setError("Email already registered");
                                Snackbar.make(cardRegister, "Email already exists. Please login instead.",
                                        Snackbar.LENGTH_LONG)
                                        .setBackgroundTint(getResources().getColor(R.color.auth_error))
                                        .show();
                            }
                        });
                        return;
                    }

                    // Create new user
                    // NOTE: In production, password should be hashed with salt (e.g., BCrypt, Argon2)
                    User newUser = new User(finalFullName, finalUsername, finalEmail, finalPassword);

                    // Insert into database
                    long userId = userDao.insertUser(newUser);

                    // Switch back to main thread for UI updates
                    new Handler(Looper.getMainLooper()).post(new Runnable() {
                        @Override
                        public void run() {
                            setLoadingState(false);

                            if (userId > 0) {
                                // Registration successful
                                onRegistrationSuccess();
                            } else {
                                // Registration failed
                                onRegistrationFailure();
                            }
                        }
                    });
                } catch (Exception e) {
                    // Handle any database errors
                    new Handler(Looper.getMainLooper()).post(new Runnable() {
                        @Override
                        public void run() {
                            setLoadingState(false);
                            Snackbar.make(cardRegister, "An error occurred. Please try again.",
                                    Snackbar.LENGTH_SHORT)
                                    .setBackgroundTint(getResources().getColor(R.color.auth_error))
                                    .show();
                        }
                    });
                }
            }
        });
    }

    /**
     * Handle successful registration.
     * Shows success message and navigates back to LoginActivity.
     */
    private void onRegistrationSuccess() {
        // Show success snackbar
        Snackbar.make(cardRegister, R.string.success_registration, Snackbar.LENGTH_LONG)
                .setBackgroundTint(getResources().getColor(R.color.auth_success))
                .show();

        // Navigate to LoginActivity after a short delay
        new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                // Add the registered email to intent so user can see it pre-filled (optional)
                intent.putExtra("registered_email", etEmail.getText().toString());
                startActivity(intent);
                finish();
            }
        }, 2000); // 2 second delay to show success message
    }

    /**
     * Handle failed registration.
     * Shows error message to the user.
     */
    private void onRegistrationFailure() {
        Snackbar.make(cardRegister, "Registration failed. Please try again.", Snackbar.LENGTH_LONG)
                .setBackgroundTint(getResources().getColor(R.color.auth_error))
                .show();
    }

    /**
     * Clear all error messages from input fields.
     */
    private void clearErrors() {
        tilFullName.setError(null);
        tilUsername.setError(null);
        tilEmail.setError(null);
        tilPassword.setError(null);
        tilConfirmPassword.setError(null);
    }

    /**
     * Toggle loading state (show/hide progress bar, disable/enable button).
     *
     * @param isLoading true to show loading state, false to hide
     */
    private void setLoadingState(boolean isLoading) {
        if (isLoading) {
            progressBar.setVisibility(View.VISIBLE);
            btnSignUp.setEnabled(false);
            btnSignUp.setAlpha(0.5f);
        } else {
            progressBar.setVisibility(View.GONE);
            btnSignUp.setEnabled(true);
            btnSignUp.setAlpha(1.0f);
        }
    }

    /**
     * Animate card entrance with scale effect.
     */
    private void animateCard() {
        cardRegister.setAlpha(0f);
        cardRegister.setScaleX(0.9f);
        cardRegister.setScaleY(0.9f);

        cardRegister.animate()
                .alpha(1f)
                .scaleX(1f)
                .scaleY(1f)
                .setDuration(400)
                .setStartDelay(100)
                .start();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // Shutdown executor service
        if (executorService != null && !executorService.isShutdown()) {
            executorService.shutdown();
        }
    }
}
