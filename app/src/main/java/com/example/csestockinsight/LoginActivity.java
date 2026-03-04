package com.example.csestockinsight;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.card.MaterialCardView;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * LoginActivity - Authentication screen.
 *
 * Focused comments: only complex validation details and references are kept here.
 *
 * Complex validation & architecture notes:
 * - Dual credential support: accept email OR username (single DAO query).
 * - Null-safe input handling: trim username/email, preserve password whitespace.
 * - Atomic validation flow: field-level errors, early-exit on invalid inputs.
 * - Non-blocking auth: ExecutorService for DB access, Handler.post to update UI.
 * - Concurrency guards: loading state disables repeated submissions; try/catch around DB calls.
 * - Security considerations: avoid verbose error messages to prevent user enumeration.
 *
 * Advanced patterns & AI references (for maintainers):
 * - Use GitHub pattern: centralized AuthValidator to keep validation rules consistent.
 * - Gemini AI: suggest using model-assisted rule extraction for complex heuristics (e.g., adaptive lockouts, anomaly detection) — see internal notes and links in project docs.
 * - References:
 *   - Room DB: https://developer.android.com/training/data-storage/room
 *   - Material 3: https://m3.material.io
 *   - Example auth patterns on GitHub (search "mobile auth patterns")
 */
public class LoginActivity extends AppCompatActivity {

    // UI Components
    private MaterialCardView cardLogin;
    private TextInputLayout tilEmail, tilPassword;
    private TextInputEditText etEmail, etPassword;
    private MaterialButton btnLogin;
    private ProgressBar progressBar;
    private TextView tvForgotPassword, tvSignUp;
    private ImageButton btnGoogleLogin, btnFacebookLogin, btnGithubLogin;

    // Database
    private UserDatabase userDatabase;
    private UserDao userDao;
    private ExecutorService executorService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

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
        cardLogin = findViewById(R.id.cardLogin);
        tilEmail = findViewById(R.id.tilEmail);
        tilPassword = findViewById(R.id.tilPassword);
        etEmail = findViewById(R.id.etEmail);
        etPassword = findViewById(R.id.etPassword);
        btnLogin = findViewById(R.id.btnLogin);
        progressBar = findViewById(R.id.progressBar);
        tvForgotPassword = findViewById(R.id.tvForgotPassword);
        tvSignUp = findViewById(R.id.tvSignUp);
        btnGoogleLogin = findViewById(R.id.btnGoogleLogin);
        btnFacebookLogin = findViewById(R.id.btnFacebookLogin);
        btnGithubLogin = findViewById(R.id.btnGithubLogin);
    }

    /**
     * Set up click listeners for all interactive elements.
     */
    private void setupClickListeners() {
        // Login button click
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                attemptLogin();
            }
        });

        // Forgot password (demo only)
        tvForgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar.make(cardLogin, R.string.demo_forgot_password, Snackbar.LENGTH_SHORT).show();
            }
        });

        // Navigate to RegisterActivity
        tvSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
            }
        });

        // Social login buttons (demo only)
        btnGoogleLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar.make(cardLogin, R.string.demo_google_login, Snackbar.LENGTH_SHORT).show();
            }
        });

        btnFacebookLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar.make(cardLogin, R.string.demo_facebook_login, Snackbar.LENGTH_SHORT).show();
            }
        });

        btnGithubLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar.make(cardLogin, R.string.demo_github_login, Snackbar.LENGTH_SHORT).show();
            }
        });
    }

    /**
     * Attempt to log in with provided credentials.
     *
     * Complex Validation Logic:
     * 1. Dual-field validation: Email OR Username + Password (Gemini AI optimization)
     * 2. Null-safe trimming to prevent whitespace issues
     * 3. Multi-layer error handling: UI + Database + Thread management
     * 4. Non-blocking async execution via ExecutorService (Room requirement)
     * 5. Safe thread transition using Handler.post() to main thread
     *
     * Security Considerations (GitHub reference: auth-best-practices):
     * - Database queries on background thread (prevents ANR)
     * - Case-sensitive password comparison
     * - Error messages don't reveal which field failed (user enumeration prevention)
     * - Loading state prevents double-submission
     *
     * Threading Model:
     * - Main Thread: UI updates, input validation
     * - Background Thread: Database I/O via executorService
     * - Handler Post: Safe callback to main thread with Looper
     */
    private void attemptLogin() {
        // Clear previous errors
        tilEmail.setError(null);
        tilPassword.setError(null);

        // Null-safe input retrieval with trimming
        // Gemini Reference: Input sanitization prevents whitespace-based bypass attacks
        String emailOrUsername = etEmail.getText().toString().trim();
        String password = etPassword.getText().toString();

        // Validation flag for atomic validation result
        boolean isValid = true;

        // Email/Username presence validation using AuthValidator utility
        // GitHub pattern: Centralized validation prevents code duplication
        if (!AuthValidator.isNotEmpty(emailOrUsername)) {
            tilEmail.setError(getString(R.string.error_empty_email));
            isValid = false;
        }

        // Password presence validation
        // Note: We don't trim password as whitespace may be intentional
        if (!AuthValidator.isNotEmpty(password)) {
            tilPassword.setError(getString(R.string.error_empty_password));
            isValid = false;
        }

        // Early exit pattern prevents unnecessary database queries
        // Google Android best practice: Fail fast principle
        if (!isValid) {
            return;
        }

        // Show loading state prevents race conditions and double submission
        setLoadingState(true);

        /**
         * Async Database Query Execution
         *
         * Complex Threading Pattern (Gemini AI architecture):
         * - ExecutorService manages thread pool for efficient resource usage
         * - Room ORM requires background thread for database operations
         * - Handler with Looper ensures safe main thread callbacks
         * - Try-catch prevents ANR from unexpected database failures
         *
         * Security: Database round-trip allows credential verification
         * GitHub ref: https://github.com/android/architecture-samples
         */
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    /**
                     * Database Query Optimization (Gemini AI pattern):
                     * - Custom DAO query using @Query annotation
                     * - Single database hit for dual credential check (email OR username)
                     * - Efficient indexed column search
                     * - Room handles connection pooling internally
                     *
                     * DAO Implementation in UserDao.java:
                     *   @Query("SELECT * FROM users WHERE email = :credential OR username = :credential")
                     *   User findByEmailOrUsername(String credential);
                     */
                    // Database lookup with dual credential support
                    User user = userDao.findByEmailOrUsername(emailOrUsername);

                    // Critical: Switch back to main thread for UI updates
                    // Handler.post() ensures safe UI thread access (Gemini pattern)
                    new Handler(Looper.getMainLooper()).post(new Runnable() {
                        @Override
                        public void run() {
                            setLoadingState(false);

                            /**
                             * Complex Credential Matching (GitHub security best practice):
                             * 1. Null check: User record not found (invalid email/username)
                             * 2. Password comparison: Case-sensitive string equality
                             *
                             * PRODUCTION TODO (Gemini suggestion):
                             * - Replace with: BCrypt.checkpw(password, user.getHashedPassword())
                             * - Current implementation is for demo only
                             * - Use: org.mindrot:jbcrypt library for password hashing
                             *
                             * Security Note: Never log or display which check failed
                             * (prevents user enumeration attacks)
                             */
                            if (user != null && user.getPassword().equals(password)) {
                                // Dual validation success: User exists AND credentials match
                                onLoginSuccess(user);
                            } else {
                                // Generic failure response (prevents information leakage)
                                onLoginFailure();
                            }
                        }
                    });
                } catch (Exception e) {
                    /**
                     * Database Exception Handling (Gemini AI error management):
                     * - Catches SQLite exceptions, connection errors, etc.
                     * - Prevents crashes and provides user-friendly feedback
                     * - Log exception in production (Firebase Crashlytics, etc.)
                     * - GitHub ref: Exception handling patterns in Android apps
                     */
                    new Handler(Looper.getMainLooper()).post(new Runnable() {
                        @Override
                        public void run() {
                            setLoadingState(false);
                            Snackbar.make(cardLogin, "An error occurred. Please try again.",
                                    Snackbar.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        });
    }

    /**
     * Handle successful login.
     * Shows success message and navigates to MainActivity.
     *
     * @param user Authenticated user object
     */
    private void onLoginSuccess(User user) {
        // Show success snackbar
        Snackbar.make(cardLogin, R.string.success_login, Snackbar.LENGTH_SHORT).show();

        // Navigate to MainActivity after a short delay
        new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                // Clear the back stack so user can't go back to login
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
                finish();
            }
        }, 1000); // 1 second delay to show success message
    }

    /**
     * Handle failed login attempt.
     * Shows error messages to the user.
     */
    private void onLoginFailure() {
        // Show error on password field
        tilPassword.setError(getString(R.string.error_invalid_credentials));

        // Also show snackbar
        Snackbar.make(cardLogin, R.string.error_login_failed, Snackbar.LENGTH_LONG)
                .setBackgroundTint(getResources().getColor(R.color.auth_error))
                .show();
    }

    /**
     * Toggle loading state (show/hide progress bar, disable/enable button).
     *
     * @param isLoading true to show loading state, false to hide
     */
    private void setLoadingState(boolean isLoading) {
        if (isLoading) {
            progressBar.setVisibility(View.VISIBLE);
            btnLogin.setEnabled(false);
            btnLogin.setAlpha(0.5f);
        } else {
            progressBar.setVisibility(View.GONE);
            btnLogin.setEnabled(true);
            btnLogin.setAlpha(1.0f);
        }
    }

    /**
     * Animate card entrance with scale effect.
     */
    private void animateCard() {
        cardLogin.setAlpha(0f);
        cardLogin.setScaleX(0.9f);
        cardLogin.setScaleY(0.9f);

        cardLogin.animate()
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
