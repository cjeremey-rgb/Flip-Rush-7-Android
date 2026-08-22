package com.fliprush7.app;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.CookieManager;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URI;

public class MainActivity extends Activity {
    private static final String PREFS = "flip_rush_7";
    private static final String PREF_URL = "game_url";
    private WebView webView;
    private String gameUrl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setNavigationBarColor(Color.parseColor("#07101D"));

        String bundled = readBundledUrl();
        SharedPreferences prefs = getSharedPreferences(PREFS, MODE_PRIVATE);
        String saved = prefs.getString(PREF_URL, "");
        gameUrl = isValidGameUrl(bundled) ? normalizeUrl(bundled) : (isValidGameUrl(saved) ? normalizeUrl(saved) : "");

        if (gameUrl.isEmpty()) {
            showOneTimeSetup();
        } else {
            showGame();
        }
    }

    private String readBundledUrl() {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(getAssets().open("live-url.txt")))) {
            String line = reader.readLine();
            return line == null ? "" : line.trim();
        } catch (Exception ignored) {
            return "";
        }
    }

    private boolean isValidGameUrl(String value) {
        if (value == null) return false;
        String v = value.trim();
        if (!v.toLowerCase().startsWith("https://")) return false;
        if (v.contains("SET_LIVE_URL_HERE")) return false;
        try {
            URI uri = new URI(v);
            return uri.getHost() != null && !uri.getHost().isEmpty();
        } catch (Exception ignored) {
            return false;
        }
    }

    private String normalizeUrl(String value) {
        String v = value.trim();
        return v.endsWith("/") ? v : v + "/";
    }

    private int dp(int value) {
        return Math.round(value * getResources().getDisplayMetrics().density);
    }

    private void showOneTimeSetup() {
        LinearLayout root = new LinearLayout(this);
        root.setOrientation(LinearLayout.VERTICAL);
        root.setGravity(Gravity.CENTER);
        root.setPadding(dp(24), dp(24), dp(24), dp(24));
        root.setBackgroundColor(Color.parseColor("#07101D"));

        TextView title = new TextView(this);
        title.setText("FLIP RUSH 7");
        title.setTextColor(Color.parseColor("#FFDD42"));
        title.setTextSize(32);
        title.setGravity(Gravity.CENTER);
        title.setTypeface(android.graphics.Typeface.DEFAULT_BOLD);

        TextView note = new TextView(this);
        note.setText("One-time setup: enter the HTTPS address where Flip Rush 7 is hosted. Once saved, the app will open the live game directly.");
        note.setTextColor(Color.parseColor("#DCEBFA"));
        note.setTextSize(15);
        note.setGravity(Gravity.CENTER);
        LinearLayout.LayoutParams noteParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        noteParams.topMargin = dp(18);
        note.setLayoutParams(noteParams);

        EditText url = new EditText(this);
        url.setSingleLine(true);
        url.setHint("https://your-flip-rush-7-site.com/");
        url.setTextColor(Color.WHITE);
        url.setHintTextColor(Color.parseColor("#71889A"));
        url.setBackgroundColor(Color.parseColor("#102238"));
        url.setPadding(dp(14), dp(14), dp(14), dp(14));
        LinearLayout.LayoutParams urlParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        urlParams.topMargin = dp(22);
        url.setLayoutParams(urlParams);

        Button save = new Button(this);
        save.setText("SAVE & OPEN FLIP RUSH 7");
        save.setTextColor(Color.parseColor("#101B27"));
        save.setBackgroundColor(Color.parseColor("#FFDD42"));
        LinearLayout.LayoutParams buttonParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, dp(58));
        buttonParams.topMargin = dp(14);
        save.setLayoutParams(buttonParams);
        save.setOnClickListener(v -> {
            String entered = url.getText().toString().trim();
            if (!isValidGameUrl(entered)) {
                Toast.makeText(this, "Enter the full HTTPS website address.", Toast.LENGTH_LONG).show();
                return;
            }
            gameUrl = normalizeUrl(entered);
            getSharedPreferences(PREFS, MODE_PRIVATE).edit().putString(PREF_URL, gameUrl).apply();
            showGame();
        });

        root.addView(title);
        root.addView(note);
        root.addView(url);
        root.addView(save);
        setContentView(root);
    }

    private void showGame() {
        webView = new WebView(this);
        webView.setBackgroundColor(Color.parseColor("#07101D"));
        webView.setOverScrollMode(View.OVER_SCROLL_NEVER);
        webView.setVerticalScrollBarEnabled(false);
        webView.setHorizontalScrollBarEnabled(false);

        WebSettings settings = webView.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setDomStorageEnabled(true);
        settings.setDatabaseEnabled(true);
        settings.setLoadsImagesAutomatically(true);
        settings.setSupportZoom(false);
        settings.setBuiltInZoomControls(false);
        settings.setDisplayZoomControls(false);
        settings.setMediaPlaybackRequiresUserGesture(false);
        settings.setCacheMode(WebSettings.LOAD_DEFAULT);
        settings.setAllowFileAccess(false);
        settings.setAllowContentAccess(false);

        CookieManager.getInstance().setAcceptCookie(true);
        CookieManager.getInstance().setAcceptThirdPartyCookies(webView, true);

        final String gameHost = Uri.parse(gameUrl).getHost();
        webView.setWebChromeClient(new WebChromeClient());
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                Uri uri = request.getUrl();
                String host = uri.getHost();
                if (host != null && host.equalsIgnoreCase(gameHost)) return false;
                try {
                    startActivity(new Intent(Intent.ACTION_VIEW, uri));
                } catch (Exception ignored) {}
                return true;
            }
        });

        setContentView(webView);
        webView.loadUrl(gameUrl);
    }

    @Override
    public void onBackPressed() {
        if (webView != null && webView.canGoBack()) {
            webView.goBack();
        } else {
            super.onBackPressed();
        }
    }

    @Override
    protected void onDestroy() {
        if (webView != null) {
            webView.stopLoading();
            webView.destroy();
        }
        super.onDestroy();
    }
}
