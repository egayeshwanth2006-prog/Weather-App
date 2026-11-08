package com.example.weatherapptutorial;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.TextView;

import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;

public class cityFinder extends AppCompatActivity {

    private TextInputEditText cityInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_city_finder);
        cityInput = findViewById(R.id.searchCity);
        MaterialButton searchButton = findViewById(R.id.searchCityButton);
        MaterialToolbar toolbar = findViewById(R.id.cityFinderToolbar);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                submitCity();
            }
        });

        cityInput.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_GO || actionId == EditorInfo.IME_ACTION_DONE) {
                    submitCity();
                    return true;
                }
                return false;
            }
        });
    }

    private void submitCity() {
        String newCity = cityInput.getText() != null ? cityInput.getText().toString().trim() : "";
        if (TextUtils.isEmpty(newCity)) {
            cityInput.setError(getString(R.string.city_finder_error));
            return;
        }
        Intent intent=new Intent(cityFinder.this,MainActivity.class);
        intent.putExtra("City",newCity);
        startActivity(intent);
        hideKeyboard();
        finish();
    }

    private void hideKeyboard() {
        View view = getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
            if (imm != null) {
                imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
            }
        }
    }
}