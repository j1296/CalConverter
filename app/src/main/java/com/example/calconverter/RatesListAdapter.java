// ==========================================
//  Title:  RatesListAdapter
//  Author: James Kelsey
//  Date:   03/05/2020
// ==========================================
package com.example.calconverter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import java.text.DecimalFormat;
import java.util.List;

public class RatesListAdapter extends ArrayAdapter<ExchangeRate> {

    private int resourceLayout;
    private Context context;
    // 2DP format
    private DecimalFormat decimalFormat = new DecimalFormat("#.##");

    // Creates adapter for list item
    public RatesListAdapter(Context context, int resource, List<ExchangeRate> items) {
        super(context, resource, items);
        this.resourceLayout = resource;
        this.context = context;
    }

    // Constructs view
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View view = convertView;

        if (view == null) {
            LayoutInflater vi = LayoutInflater.from(context);
            view = vi.inflate(resourceLayout, null);
        }

        // Get the relevant exchange rate
        ExchangeRate ex = getItem(position);

        // If exchange rate not null, set UI elements
        if (ex != null) {

            // Alternates list element background color
            if (position % 2 == 1) {
                view.setBackgroundColor(getContext().getResources().getColor(R.color.customPrimary, context.getTheme()));
            } else {
                view.setBackgroundColor(Color.BLACK);
            }

            TextView converterListCode = view.findViewById(R.id.converterListCode);
            TextView converterListValue = view.findViewById(R.id.converterListValue);

            if (converterListCode != null) {
                converterListCode.setText(ex.getCode());
            }

            if (converterListValue != null) {
                converterListValue.setText(String.valueOf(decimalFormat.format(ex.getValue())));
            }
        }
        return view;
    }
}
