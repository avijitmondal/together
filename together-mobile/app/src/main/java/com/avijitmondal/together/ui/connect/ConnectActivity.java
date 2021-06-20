package com.avijitmondal.together.ui.connect;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Switch;

import com.avijitmondal.together.R;
import com.avijitmondal.together.data.Constants;
import com.avijitmondal.together.ui.login.LoginActivity;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ConnectActivity extends AppCompatActivity {
    private final Pattern ipRegexPattern = Pattern.compile(Constants.IPV4_REGEX);
    private final Pattern portRegexPattern = Pattern.compile(Constants.REGEX_PORT);
    private final Pattern port80RegexPattern = Pattern.compile(Constants.REGEX_PORT_80);
    private final Pattern port443RegexPattern = Pattern.compile(Constants.REGEX_PORT_443);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_connect);

        final EditText ipEditText = findViewById(R.id.ip);
        final EditText portEditText = findViewById(R.id.port);
        final Button connectButton = findViewById(R.id.connect);
        final ProgressBar connectingProgressBar = findViewById(R.id.connecting);
        final Switch httpsSwitch = findViewById(R.id.https);

        ipEditText.addTextChangedListener(new TextWatcher() {

            @Override
            public void afterTextChanged(Editable s) {
                String ipText = s.toString();
                Matcher ipRegexMatcher = ipRegexPattern.matcher(ipText);

                connectButton.setEnabled(ipRegexMatcher.matches());
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start,
                                          int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start,
                                      int before, int count) {

            }
        });

        portEditText.addTextChangedListener(new TextWatcher() {

            @Override
            public void afterTextChanged(Editable s) {
                String portText = s.toString();
                Matcher portRegexMatcher = portRegexPattern.matcher(portText);
                Matcher port80RegexMatcher = port80RegexPattern.matcher(portText);
                Matcher port443RegexMatcher = port443RegexPattern.matcher(portText);

                connectButton.setEnabled(portRegexMatcher.matches());
                httpsSwitch.setChecked(!port80RegexMatcher.matches() && port443RegexMatcher.matches());
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start,
                                          int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start,
                                      int before, int count) {

            }
        });

        connectButton.setOnClickListener(v -> {
            connectingProgressBar.setVisibility(View.VISIBLE);
            String ip = ipEditText.getText().toString();
            String port = portEditText.getText().toString();
            boolean switchState = httpsSwitch.isChecked();

            System.out.println(ip);
            System.out.println(port);
            System.out.println(switchState);

            Intent connectIntent = new Intent(ConnectActivity.this, LoginActivity.class);
            connectingProgressBar.setVisibility(View.GONE);
            ConnectActivity.this.startActivity(connectIntent);
        });
    }
}