// ==========================================
//  Title:  CalculatorActivity
//  Author: James Kelsey
//  Date:   03/05/2020
// ==========================================
package com.example.calconverter;

import androidx.appcompat.app.AppCompatActivity;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import java.text.DecimalFormat;

public class CalculatorActivity extends AppCompatActivity {

    // Views for calculation and result
    private TextView input, output;
    // Stores input text
    private CharSequence result;

    private double firstNumber = 0;
    private double secondNumber = 0;
    private boolean hasDecimal, equalsClicked, hasClickedOperator, anyButtonClicked;
    // Stores current operator
    private char CURRENT_ACTION;
    // Decimal format used
    private DecimalFormat decimalFormat = new DecimalFormat("#.########");

    // Constructs activity
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator);

        // Gets all numerical buttons
        final Button bZero = findViewById(R.id.buttonZero);
        Button bOne = findViewById(R.id.buttonOne);
        Button bTwo = findViewById(R.id.buttonTwo);
        Button bThree = findViewById(R.id.buttonThree);
        Button bFour = findViewById(R.id.buttonFour);
        Button bFive = findViewById(R.id.buttonFive);
        Button bSix = findViewById(R.id.buttonSix);
        Button bSeven = findViewById(R.id.buttonSeven);
        Button bEight = findViewById(R.id.buttonEight);
        Button bNine = findViewById(R.id.buttonNine);

        // Gets all functional buttons
        Button bClear = findViewById(R.id.buttonClear);
        Button bDot = findViewById(R.id.buttonDot);
        Button bEquals = findViewById(R.id.buttonEquals);
        Button bAdd = findViewById(R.id.buttonAdd);
        Button bMinus = findViewById(R.id.buttonSubtract);
        Button bDivide = findViewById(R.id.buttonDivide);
        Button bMultiply = findViewById(R.id.buttonMultiply);
        Button bModulus = findViewById(R.id.buttonModulus);
        Button bDelete = findViewById(R.id.buttonDelete);
        Button bOperator = findViewById(R.id.buttonPlusMinus);

        // Gets text view for calculation and result
        input = findViewById(R.id.resultInput);
        output = findViewById(R.id.resultOutput);

        // Resets relevant properties
        bClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                input.setText(null);
                output.setText(null);
                firstNumber = 0;
                secondNumber = 0;
                CURRENT_ACTION = '0';
                hasDecimal = false;
            }
        });

        // On click for delete
        bDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                result = input.getText();

                if (result.length() != 0) {
                    // Get last character
                    String lastCharacter = result.toString().substring(result.length() - 1, result.length());

                    // Check if last character is decimal
                    if (lastCharacter.contains(".")) {
                        hasDecimal = false;
                        // Remove last character
                        input.setText(result.toString().substring(0, result.length() - 1));
                    } else {
                        input.setText(result.toString().substring(0, result.length() - 1));
                    }
                }
            }
        });

        // On click for numerical buttons
        bZero.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View v) {
                bZero.setPressed(true);

                result = input.getText();
                input.setText(result + "0");
                // Reset result view after calculation
                if (equalsClicked) {
                    output.setText(null);
                    equalsClicked = false;
                }
            }
        });

        bOne.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View v) {
                result = input.getText();
                input.setText(result + "1");
                // Reset result view after calculation
                if (equalsClicked) {
                    output.setText(null);
                    equalsClicked = false;
                }
            }
        });

        bTwo.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View v) {
                result = input.getText();
                input.setText(result + "2");
                // Reset result view after calculation
                if (equalsClicked) {
                    output.setText(null);
                    equalsClicked = false;
                }
            }
        });

        bThree.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View v) {
                result = input.getText();
                input.setText(result + "3");
                // Reset result view after calculation
                if (equalsClicked) {
                    output.setText(null);
                    equalsClicked = false;

                }
            }
        });

        bFour.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View v) {
                result = input.getText();
                input.setText(result + "4");
                // Reset result view after calculation
                if (equalsClicked) {
                    output.setText(null);
                    equalsClicked = false;
                }
            }
        });

        bFive.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View v) {
                result = input.getText();
                input.setText(result + "5");
                // Reset result view after calculation
                if (equalsClicked) {
                    output.setText(null);
                    equalsClicked = false;
                }
            }
        });

        bSix.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View v) {
                result = input.getText();
                input.setText(result + "6");
                // Reset result view after calculation
                if (equalsClicked) {
                    output.setText(null);
                    equalsClicked = false;
                }
            }
        });

        bSeven.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View v) {
                result = input.getText();
                input.setText(result + "7");
                // Reset result view after calculation
                if (equalsClicked) {
                    output.setText(null);
                    equalsClicked = false;
                }
            }
        });

        bEight.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View v) {
                result = input.getText();
                input.setText(result + "8");

                // Reset result view after calculation
                if (equalsClicked) {
                    output.setText(null);
                    equalsClicked = false;
                }
            }
        });

        bNine.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View v) {
                result = input.getText();
                input.setText(result + "9");
                // Reset result view after calculation
                if (equalsClicked) {
                    output.setText(null);
                    equalsClicked = false;
                }
            }
        });

        // On click for decimal point
        bDot.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View v) {
                result = input.getText();

                // Add decimal if not one already
                if (!hasDecimal) {
                    input.setText(result + ".");
                    hasDecimal = true;
                }

                // Reset result view after calculation
                if (equalsClicked) {
                    output.setText(null);
                    equalsClicked = false;
                }
            }
        });

        // On click for addition
        bAdd.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View v) {
                result = input.getText();

                if (result.length() != 0 && !hasClickedOperator) {

                    // Get the first number and set relevant properties
                    firstNumber = Double.parseDouble(result + "");
                    CURRENT_ACTION = '+';
                    hasDecimal = false;
                    hasClickedOperator = true;
                    input.setText(null);
                }
            }
        });

        // On click for subtraction
        bMinus.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View v) {
                result = input.getText();

                if (result.length() != 0 && !hasClickedOperator) {
                    // Get first number and set relevant properties
                    firstNumber = Double.parseDouble(result + "");
                    CURRENT_ACTION = '-';
                    hasDecimal = false;
                    hasClickedOperator = true;
                    input.setText(null);
                }
            }
        });

        // On click for divide
        bDivide.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View v) {
                result = input.getText();

                if (result.length() != 0 && !hasClickedOperator) {
                    // Get first number and set relevant properties
                    firstNumber = Double.parseDouble(result + "");
                    CURRENT_ACTION = '/';
                    hasDecimal = false;
                    hasClickedOperator = true;
                    input.setText(null);
                }
            }
        });

        // On click for multiply
        bMultiply.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View v) {
                result = input.getText();

                if (result.length() != 0 && !hasClickedOperator) {
                    // Get first number and set relevant properties
                    firstNumber = Double.parseDouble(result + "");
                    CURRENT_ACTION = '*';
                    hasDecimal = false;
                    hasClickedOperator = true;
                    input.setText(null);
                }
            }
        });

        // On click for remainder
        bModulus.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View v) {
                result = input.getText();

                if (result.length() != 0 && !hasClickedOperator) {
                    // Get first number and set relevant properties
                    firstNumber = Double.parseDouble(result + "");
                    CURRENT_ACTION = '%';
                    hasDecimal = false;
                    hasClickedOperator = true;
                    input.setText(null);
                }
            }
        });

        // On click for negate
        bOperator.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View v) {
                result = input.getText();

                if (result.length() != 0) {
                    // Get last character
                    String lastCharacter = result.toString().substring(result.length() - 1, result.length());

                    // If not operator character
                    if (!lastCharacter.contains("+") && !lastCharacter.contains("-") &
                            !lastCharacter.contains("*") && !lastCharacter.contains("/") && !lastCharacter.contains("%")) {
                        // Get value and negate
                        double value = Double.parseDouble(result.toString());
                        value = value * -1;

                        int intVal = (int) value;

                        // If original result had decimal, use double
                        if (result.toString().contains(".")) {
                            input.setText(String.valueOf(value));
                        } else {
                            // Return int
                            input.setText(String.valueOf(intVal));
                        }
                    }
                }
            }
        });

        // On click for equals
        bEquals.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View v) {
                result = input.getText();

                if (result.length() != 0) {

                    // If valid action
                    if (CURRENT_ACTION == '+' || CURRENT_ACTION == '-' || CURRENT_ACTION == '*' ||
                            CURRENT_ACTION == '/' || CURRENT_ACTION == '%') {
                        // Get second number
                        secondNumber = Double.parseDouble(result + "");
                    }

                    // Make calculation and set relevant properties
                    switch (CURRENT_ACTION) {
                        case '+':
                            input.setText("");
                            output.setText(decimalFormat.format(firstNumber + secondNumber) + "");
                            CURRENT_ACTION = '0';
                            equalsClicked = true;
                            hasClickedOperator = false;
                            break;
                        case '-':
                            input.setText("");
                            output.setText(decimalFormat.format(firstNumber - secondNumber) + "");
                            CURRENT_ACTION = '0';
                            equalsClicked = true;
                            hasClickedOperator = false;
                            break;
                        case '*':
                            input.setText("");
                            output.setText(decimalFormat.format(firstNumber * secondNumber) + "");
                            CURRENT_ACTION = '0';
                            equalsClicked = true;
                            hasClickedOperator = false;
                            break;
                        case '/':
                            input.setText("");
                            output.setText(decimalFormat.format(firstNumber / secondNumber) + "");
                            CURRENT_ACTION = '0';
                            equalsClicked = true;
                            hasClickedOperator = false;
                            break;
                        case '%':
                            input.setText("");
                            output.setText(decimalFormat.format(firstNumber % secondNumber) + "");
                            CURRENT_ACTION = '0';
                            equalsClicked = true;
                            hasClickedOperator = false;
                            break;
                        default:
                            break;
                    }

                }
            }
        });
    }

    // Constructs menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.calculator_menu, menu);

        // Change colour of converter icon
        MenuItem converterItem = menu.findItem(R.id.calculatorMenuConverter);
        Drawable calculatorDrawable = converterItem.getIcon();
        calculatorDrawable.mutate();
        calculatorDrawable.setColorFilter(getResources().getColor(R.color.customAccent, getTheme()), PorterDuff.Mode.SRC_ATOP);
        calculatorDrawable.setAlpha(255);

        // Change colour of rates icon
        MenuItem ratesItem = menu.findItem(R.id.calculatorMenuRates);
        Drawable ratesDrawable = ratesItem.getIcon();
        ratesDrawable.mutate();
        ratesDrawable.setColorFilter(getResources().getColor(R.color.customAccent, getTheme()), PorterDuff.Mode.SRC_ATOP);
        ratesDrawable.setAlpha(255);

        // Change colour of about icon
        MenuItem aboutItem = menu.findItem(R.id.calculatorMenuAbout);
        Drawable aboutDrawable = aboutItem.getIcon();
        aboutDrawable.mutate();
        aboutDrawable.setColorFilter(getResources().getColor(R.color.customAccent, getTheme()), PorterDuff.Mode.SRC_ATOP);
        aboutDrawable.setAlpha(255);

        return true;
    }

    // Handles menu item selection
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.calculatorMenuConverter:
                Intent converterIntent = new Intent(CalculatorActivity.this, ConverterActivity.class);
                startActivity(converterIntent);
                break;
            case R.id.calculatorMenuRates:
                Intent ratesIntent = new Intent(CalculatorActivity.this, RatesActivity.class);
                startActivity(ratesIntent);
                break;
            case R.id.calculatorMenuAbout:
                Intent aboutIntent = new Intent(CalculatorActivity.this, AboutActivity.class);
                startActivity(aboutIntent);
                break;
            default:
                return super.onOptionsItemSelected(item);
        }
        return true;
    }
}
