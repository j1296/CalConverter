// ==========================================
//  Title:  ConverterActivity
//  Author: James Kelsey
//  Date:   03/05/2020
// ==========================================
package com.example.calconverter;

import androidx.appcompat.app.AppCompatActivity;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.Objects;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class ConverterActivity extends AppCompatActivity {

    // Stores list of exchange rates
    private ArrayList<ExchangeRate> ratesList;

    // Stores currency from user input
    private String fromCurrency, toCurrency;
    private double fromCurrencyValue;

    // UI element for currency
    EditText currencyValue;

    // Set if user chooses all currencies
    private boolean showAllCurrencies;

    // Forces keyboard to hide
    private void hideKeyboard(View view) {
        InputMethodManager inputMethodManager =(InputMethodManager)getSystemService(Activity.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    // Constructs activity
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_converter);

        // API calls run on main thread
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        // fromCurrency dropdown and adapter
        final Spinner fromSpinner = findViewById(R.id.fromSpinner);
        ArrayAdapter<CharSequence> fromSpinnerAdapter = ArrayAdapter.createFromResource(this,
                R.array.from_currency_array, android.R.layout.simple_spinner_item);
        fromSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        fromSpinner.setAdapter(fromSpinnerAdapter);

        // On item selection, set text colour and store string
        fromSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                ((TextView) parent.getSelectedView()).setTextColor(Color.WHITE);
                // Get starting currency
                fromCurrency = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) { }});


        // toCurrency dropdown and adapter
        Spinner toSpinner = findViewById(R.id.toSpinner);

        ArrayAdapter<CharSequence> toSpinnerAdapter = ArrayAdapter.createFromResource(this,
                R.array.to_currency_array, android.R.layout.simple_spinner_item);
        toSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        toSpinner.setAdapter(toSpinnerAdapter);

        // On item selection, set text colour and store string
        toSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                ((TextView) parent.getSelectedView()).setTextColor(Color.WHITE);
                // Get desired currency
                toCurrency = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) { }});


        // Hide keyboard when focus is lost
        currencyValue = findViewById(R.id.currencyValueText);
        currencyValue.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasFocus) {
                if (!hasFocus) {
                    hideKeyboard(view);
                }
            }
        });

        // Convert button
        Button convertButton = findViewById(R.id.convertButton);
        convertButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String value = currencyValue.getText().toString();

                // Show toast if no value entered
                if (value.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Please enter a value.", Toast.LENGTH_LONG).show();
                }
                else {
                    // Convert to double, call search function and store results
                    fromCurrencyValue = Double.parseDouble(value);
                    String response = search(fromCurrency, toCurrency);

                    // Parse the response and store results
                    ratesList = parse(response, toCurrency);

                    // Do the currency conversion
                    ratesList = convert(ratesList, fromCurrencyValue);

                    if (ratesList != null) {
                        // Populate list view with adapter
                        ListView resultsList = findViewById(R.id.converterListView);
                        ConverterListAdapter adapter = new ConverterListAdapter(getApplicationContext(), R.layout.rates_list_adapter, ratesList);
                        resultsList.setAdapter(adapter);
                        // Add footer line at bottom of list
                        resultsList.setFooterDividersEnabled(true);
                        resultsList.addFooterView(new View(getApplicationContext()), null, true);
                        // Hide keyboard
                        hideKeyboard(v);
                    }
                }
            }
        });
    }


    // Constructs menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.converter_menu, menu);

        // Change colour of calculator icon
        MenuItem calculatorItem = menu.findItem(R.id.converterMenuCalculator);
        Drawable calculatorDrawable = calculatorItem.getIcon();
        calculatorDrawable.mutate();
        calculatorDrawable.setColorFilter(getResources().getColor(R.color.customAccent, getTheme()), PorterDuff.Mode.SRC_ATOP);
        calculatorDrawable.setAlpha(255);

        // Change colour of rates icon
        MenuItem ratesItem = menu.findItem(R.id.converterMenuRates);
        Drawable ratesDrawable = ratesItem.getIcon();
        ratesDrawable.mutate();
        ratesDrawable.setColorFilter(getResources().getColor(R.color.customAccent, getTheme()), PorterDuff.Mode.SRC_ATOP);
        ratesDrawable.setAlpha(255);

        // Change colour of about icon
        MenuItem aboutItem = menu.findItem(R.id.converterMenuAbout);
        Drawable aboutDrawable = aboutItem.getIcon();
        aboutDrawable.mutate();
        aboutDrawable.setColorFilter(getResources().getColor(R.color.customAccent, getTheme()), PorterDuff.Mode.SRC_ATOP);
        aboutDrawable.setAlpha(255);

        return true;
    }

    // Handles menu item selection
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.converterMenuCalculator:
                Intent calculatorIntent = new Intent(ConverterActivity.this, CalculatorActivity.class);
                startActivity(calculatorIntent);
                break;
            case R.id.converterMenuRates:
                Intent ratesIntent = new Intent(ConverterActivity.this, RatesActivity.class);
                startActivity(ratesIntent);
                break;
            case R.id.converterMenuAbout:
                Intent aboutIntent = new Intent(ConverterActivity.this, AboutActivity.class);
                startActivity(aboutIntent);
                break;
            default:
                return super.onOptionsItemSelected(item);
        }
        return true;
    }


    // Method to make API call
    public String search(String fromCurrency, String toCurrency) {

        // Instance of OkHttpClient to make HTTP request
        OkHttpClient client = new OkHttpClient();
        String resp = "";

        // Construct request url
        StringBuilder sb = new StringBuilder(getResources().getString(R.string.api_base_url));
        sb.append("?base=" + fromCurrency);

        // If converting to all currencies, set bool
        if (!toCurrency.contains("All")) {
            showAllCurrencies = false;
        } else {
            showAllCurrencies = true;
        }

        // Construct request
        Request get = new Request.Builder()
                .url(sb.toString())
                .build();

        // Attempt to make request and store results
        try {
            Response r = client.newCall(get).execute();
            resp = Objects.requireNonNull(r.body()).string();

        } catch (Exception e) {
            e.printStackTrace();
        }

    return resp;

    }

    // Method to parse data
    public ArrayList<ExchangeRate> parse(String response, String toCurrency) {
        ArrayList<ExchangeRate> resultsList = new ArrayList<ExchangeRate>();

        try {
            // Convert to JSON and get rates object
            JSONObject firstJsonObj = new JSONObject(response);
            JSONObject rates = firstJsonObj.getJSONObject("rates");

            if (showAllCurrencies) {
                // Gets all names of currencies
                Iterator<String> keys = rates.keys();

                while (keys.hasNext()) {
                    // Get current key
                    String key = keys.next();
                    // Convert to double
                    double value = rates.getDouble(key);

                    // New object to store code and value
                    ExchangeRate ex = new ExchangeRate();
                    ex.setCode(key);
                    ex.setValue(value);
                    // Add to list of results
                    resultsList.add(ex);
                }
            } else {
                // Get requested currency value
                double value = rates.getDouble(toCurrency);
                // New object to store code and value
                ExchangeRate ex = new ExchangeRate();
                ex.setCode(toCurrency);
                ex.setValue(value);
                // Add to list of results
                resultsList.add(ex);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return formatResults(resultsList);
    }

    // Method to order results
    private ArrayList<ExchangeRate> formatResults(ArrayList<ExchangeRate> resultsList) {
        ArrayList list = resultsList;

        // Compares each exchange rate and sorts
        Collections.sort(list, new Comparator<ExchangeRate>() {
            @Override
            public int compare(ExchangeRate o1, ExchangeRate o2) {
                return o1.getCode().compareTo(o2.getCode());
            }
        });

        return list;
    }

    // Method to make conversion
    private ArrayList<ExchangeRate> convert(ArrayList<ExchangeRate> ratesList, double fromCurrencyValue) {
        ArrayList<ExchangeRate> list = ratesList;

        // Multiply each currency value by user value
        for (ExchangeRate e : list) {
            e.setValue(e.getValue() * fromCurrencyValue);
        }

        return list;
    }
}
