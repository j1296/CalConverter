// ==========================================
//  Title:  AboutActivity
//  Author: James Kelsey
//  Date:   03/05/2020
// ==========================================
package com.example.calconverter;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.style.UnderlineSpan;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class AboutActivity extends AppCompatActivity {

    // Constructs activity
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        // Back button returns to previous activity
        Button backButton = findViewById(R.id.backButton);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        // Sets text, underline and icon link
        TextView iconsLink = findViewById(R.id.icons8Link);
        SpannableString content = new SpannableString(getResources().getString(R.string.icons8_text));
        content.setSpan(new UnderlineSpan(), 0, content.length(), 0);
        iconsLink.setText(content);

        iconsLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent websiteIntent = new Intent(Intent.ACTION_VIEW);
                websiteIntent.setData(Uri.parse(getResources().getString(R.string.icons8_url)));
                startActivity(websiteIntent);
            }
        });
    }

    // Constructs menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.about_menu, menu);

        // Change colour of calculator icon
        MenuItem calculatorItem = menu.findItem(R.id.aboutMenuCalculator);
        Drawable calculatorDrawable = calculatorItem.getIcon();
        calculatorDrawable.mutate();
        calculatorDrawable.setColorFilter(getResources().getColor(R.color.customAccent, getTheme()), PorterDuff.Mode.SRC_ATOP);
        calculatorDrawable.setAlpha(255);

        // Change colour of converter icon
        MenuItem converterItem = menu.findItem(R.id.aboutMenuConverter);
        Drawable aboutDrawable = converterItem.getIcon();
        aboutDrawable.mutate();
        aboutDrawable.setColorFilter(getResources().getColor(R.color.customAccent, getTheme()), PorterDuff.Mode.SRC_ATOP);
        aboutDrawable.setAlpha(255);

        // Change colour of rates icon
        MenuItem ratesItem = menu.findItem(R.id.aboutMenuRates);
        Drawable ratesDrawable = ratesItem.getIcon();
        ratesDrawable.mutate();
        ratesDrawable.setColorFilter(getResources().getColor(R.color.customAccent, getTheme()), PorterDuff.Mode.SRC_ATOP);
        ratesDrawable.setAlpha(255);

        return true;
    }

    // Handles menu item selection
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.aboutMenuCalculator:
                Intent calculatorIntent = new Intent(AboutActivity.this, CalculatorActivity.class);
                startActivity(calculatorIntent);
                break;
            case R.id.aboutMenuConverter:
                Intent converterIntent = new Intent(AboutActivity.this, ConverterActivity.class);
                startActivity(converterIntent);
                break;
            case R.id.aboutMenuRates:
                Intent ratesIntent = new Intent(AboutActivity.this, RatesActivity.class);
                startActivity(ratesIntent);
                break;
            default:
                return super.onOptionsItemSelected(item);
        }
        return true;
    }
}
