// ==========================================
//  Title:  RatesActivity
//  Author: James Kelsey
//  Date:   03/05/2020
// ==========================================
package com.example.calconverter;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ListView;
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

public class RatesActivity extends AppCompatActivity {

    // Stores list of exchange rates
    private ArrayList<ExchangeRate> ratesList;

    // Constructs activity
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rates);

        // API calls run on main thread
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        // Calls search function and stores results
        String response = search();
        // Parse the response and store the results
        ratesList = parse(response);

        // Sets results list view adapter
        ListView resultsList = findViewById(R.id.ratesListView);
        RatesListAdapter adapter = new RatesListAdapter(getApplicationContext(), R.layout.rates_list_adapter, ratesList);
        resultsList.setAdapter(adapter);
    }


    // Constructs menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.rates_menu, menu);

        MenuItem calculatorItem = menu.findItem(R.id.ratesMenuCalculator);
        Drawable calculatorDrawable = calculatorItem.getIcon();
        calculatorDrawable.mutate();
        calculatorDrawable.setColorFilter(getResources().getColor(R.color.customAccent, getTheme()), PorterDuff.Mode.SRC_ATOP);
        calculatorDrawable.setAlpha(255);

        MenuItem converterItem = menu.findItem(R.id.ratesMenuConverter);
        Drawable converterDrawable = converterItem.getIcon();
        converterDrawable.mutate();
        converterDrawable.setColorFilter(getResources().getColor(R.color.customAccent, getTheme()), PorterDuff.Mode.SRC_ATOP);
        converterDrawable.setAlpha(255);

        MenuItem aboutItem = menu.findItem(R.id.ratesMenuAbout);
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
            case R.id.ratesMenuCalculator:
                Intent calculatorIntent = new Intent(RatesActivity.this, CalculatorActivity.class);
                startActivity(calculatorIntent);
                break;
            case R.id.ratesMenuConverter:
                Intent converterIntent = new Intent(RatesActivity.this, ConverterActivity.class);
                startActivity(converterIntent);
                break;
            case R.id.ratesMenuAbout:
                Intent aboutIntent = new Intent(RatesActivity.this, AboutActivity.class);
                startActivity(aboutIntent);
                break;
            default:
                return super.onOptionsItemSelected(item);
        }
        return true;
    }

    // Method to make API call
    public String search() {

        // Instance of OkHttpClient to make HTTP request
        OkHttpClient client = new OkHttpClient();
        String resp = "";

        // Construct request url
        StringBuilder sb = new StringBuilder(getResources().getString(R.string.api_base_url));
        sb.append("?base=GBP");

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
    public ArrayList<ExchangeRate> parse(String response) {
        ArrayList<ExchangeRate> resultsList = new ArrayList<ExchangeRate>();

        try{
            // Convert to JSON and get rates object
            JSONObject firstJsonObj = new JSONObject(response);
            JSONObject rates = firstJsonObj.getJSONObject("rates");
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
        } catch (JSONException e) {
            e.printStackTrace();
        }
        // Return formatted results
        return formatResults(resultsList);
    }

    // Method to order results
    private ArrayList<ExchangeRate> formatResults(ArrayList<ExchangeRate> resultsList) {

        // Compares each exchange rate and sorts
        Collections.sort(resultsList, new Comparator<ExchangeRate>() {
            @Override
            public int compare(ExchangeRate o1, ExchangeRate o2) {
                return o1.getCode().compareTo(o2.getCode());
            }
        });
        return resultsList;
    }
}
