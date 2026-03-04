package com.example.csestockinsight;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.button.MaterialButton;

// refer: GitHub/Gemini
public class AuthEntryActivity extends AppCompatActivity {

    private MaterialButton btnLogin;
    private MaterialButton btnCreateAccount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auth_entry);

        // Initialize views
        initializeViews();

        // Set up button click listeners
        setupClickListeners();

        // Add entrance animations for a polished look
        animateButtons();
    }

    /**
     * Initialize all view references.
     */
    private void initializeViews() {
        btnLogin = findViewById(R.id.btnLogin);
        btnCreateAccount = findViewById(R.id.btnCreateAccount);
    }

    /**
     * Set up click listeners for navigation buttons.
     */
    private void setupClickListeners() {
        // Navigate to LoginActivity
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AuthEntryActivity.this, LoginActivity.class);
                startActivity(intent);
                // Add smooth transition animation
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
            }
        });

        // Navigate to RegisterActivity
        btnCreateAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AuthEntryActivity.this, RegisterActivity.class);
                startActivity(intent);
                // Add smooth transition animation
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
            }
        });
    }

    /**
     * Add subtle scale-in animations to buttons for micro-interaction polish.
     * Buttons scale from 0.8 to 1.0 with a slight delay between them.
     */
    private void animateButtons() {
        // Scale animation for login button
        ScaleAnimation scaleLogin = new ScaleAnimation(
                0.8f, 1.0f,  // Start and end X scale
                0.8f, 1.0f,  // Start and end Y scale
                Animation.RELATIVE_TO_SELF, 0.5f,  // Pivot X
                Animation.RELATIVE_TO_SELF, 0.5f   // Pivot Y
        );
        scaleLogin.setDuration(400);
        scaleLogin.setStartOffset(200);  // Delay 200ms
        scaleLogin.setFillAfter(true);
        btnLogin.startAnimation(scaleLogin);

        // Scale animation for create account button with slight delay
        ScaleAnimation scaleCreate = new ScaleAnimation(
                0.8f, 1.0f,
                0.8f, 1.0f,
                Animation.RELATIVE_TO_SELF, 0.5f,
                Animation.RELATIVE_TO_SELF, 0.5f
        );
        scaleCreate.setDuration(400);
        scaleCreate.setStartOffset(350);  // Delay 350ms for staggered effect
        scaleCreate.setFillAfter(true);
        btnCreateAccount.startAnimation(scaleCreate);
    }
}
