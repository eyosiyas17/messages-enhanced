package com.google.messages.clone;

import android.os.Bundle;
import android.view.KeyEvent;
import com.getcapacitor.BridgeActivity;

public class MainActivity extends BridgeActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Register SMS plugin
        try {
            registerPlugin(com.google.messages.clone.SMSPlugin.class);
        } catch (Exception e) {
            // Plugin registration failed, but continue with app startup
            System.err.println("Failed to register SMSPlugin: " + e.getMessage());
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            // Handle back button press
            getBridge().getWebView().evaluateJavascript(
                "window.handleBackButton && window.handleBackButton();",
                null
            );
            return true; // Prevent default behavior (app closing)
        }
        return super.onKeyDown(keyCode, event);
    }
}
