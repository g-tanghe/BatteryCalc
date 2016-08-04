package com.tanghe.garben.batterycalc;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import com.google.android.gms.ads.MobileAds;

public class MainActivity extends AppCompatActivity {

    protected int capacityA = 0;
    protected double energyA = 0.0;
    protected double voltageA = 0.0;
    protected int capacityB = 0;
    protected double voltageB = 0.0;

    protected int last = 0;
    protected int secondLast = 0;

    protected EditText editText;
    protected EditText editText2;
    protected EditText editText3;
    protected EditText editText4;
    protected EditText editText5;
    protected TextView textView13;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MobileAds.initialize(getApplicationContext(), "ca-app-pub-4741956938194475~8108055942");

        AdView mAdView = (AdView) findViewById(R.id.adView);
        //AdRequest adRequest = new AdRequest.Builder().addTestDevice("62DB073E067B8944A650AE9CC2BD9368").build();
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        editText = (EditText) findViewById(R.id.editText);
        editText2 = (EditText) findViewById(R.id.editText2);
        editText3 = (EditText) findViewById(R.id.editText3);
        editText4 = (EditText) findViewById(R.id.editText4);
        editText5 = (EditText) findViewById(R.id.editText5);
        textView13 = (TextView) findViewById(R.id.textView13);

        editText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                capacityA = 0;
                editText.setText("");
            }
        });

        editText2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                energyA = 0.0;
                editText2.setText("");
            }
        });

        editText3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                voltageA = 0.0;
                editText3.setText("");
            }
        });

        editText4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                capacityB = 0;
                editText4.setText("");
            }
        });

        editText5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                voltageB = 0.0;
                editText5.setText("");
            }
        });

        editText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) throws NumberFormatException {
                try {
                    capacityA = Integer.parseInt(editText.getText().toString());
                    if (capacityA > 0 && capacityA < 100000) {
                        editText.setText(Integer.toString(capacityA));
                        last = 1;
                        calc3rd();
                        secondLast = 1;
                    }
                    else {
                        capacityA = 0;
                        editText.setText("");
                    }
                }
                catch (NumberFormatException nfe) {
                    capacityA = 0;
                    editText.setText("");
                }
                return false;
            }
        });

        editText2.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) throws NumberFormatException {
                try {
                    energyA = round2decimals(Double.parseDouble(editText2.getText().toString()));
                    if (energyA > 0 && energyA < 100) {
                        editText2.setText(Double.toString(energyA));
                        last = 2;
                        calc3rd();
                        secondLast = 2;
                    }
                    else {
                        energyA = 0.0;
                        editText2.setText("");
                    }
                }
                catch (NumberFormatException nfe) {
                    energyA = 0.0;
                    editText2.setText("");
                }
                return false;
            }
        });

        editText3.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) throws NumberFormatException {
                try {
                    voltageA = round2decimals(Double.parseDouble(editText3.getText().toString()));
                    if (voltageA > 0 && voltageA < 100) {
                        editText3.setText(Double.toString(voltageA));
                        last = 3;
                        calc3rd();
                        secondLast = 3;
                    }
                    else {
                        voltageA = 0.0;
                        editText3.setText("");
                    }
                }
                catch (NumberFormatException nfe) {
                    voltageA = 0.0;
                    editText3.setText("");
                }
                return false;
            }
        });

        editText4.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) throws NumberFormatException {
                try {
                    capacityB = Integer.parseInt(editText4.getText().toString());
                    if (capacityB > 0 && capacityB < 100000) {
                        editText4.setText(Integer.toString(capacityB));
                        if (voltageB != 0.0 && capacityA != 0 && energyA != 0.0 && voltageA != 0.0) {
                            calcResult();
                        }
                    }
                    else {
                        capacityB = 0;
                        editText4.setText("");
                    }
                }
                catch (NumberFormatException nfe) {
                    capacityB = 0;
                    editText4.setText("");
                }
                return false;
            }
        });

        editText5.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) throws NumberFormatException {
                try {
                    voltageB = round2decimals(Double.parseDouble(editText5.getText().toString()));
                    if (voltageB > 0 && voltageB < 100) {
                        editText5.setText(Double.toString(voltageB));
                        if (capacityB != 0.0 && capacityA != 0 && energyA != 0.0 && voltageA != 0.0) {
                            calcResult();
                        }
                    }
                    else {
                        voltageB = 0.0;
                        editText5.setText("");
                    }
                }
                catch (NumberFormatException nfe) {
                    voltageB = 0.0;
                    editText5.setText("");

                }
                return false;
            }
        });
    }

    protected double round2decimals(double d) {
        return Math.round(d*100)/100.0;
    }

    protected void calc3rd() {
        if (secondLast != last && secondLast != 0) {
            if ((last == 1 && secondLast == 2) || (last == 2 && secondLast == 1)) {
                calcVoltageA();
            }
            else if ((last == 1 && secondLast == 3) || (last == 3 && secondLast == 1)) {
                calcEnergyA();
            }
            else {
                calcCapacityA();
            }
        }
    }

    protected void calcCapacityA() {
        capacityA = (int)Math.round(energyA/voltageA*1000);
        editText.setText(Integer.toString(capacityA));
        if (capacityB != 0 && voltageB != 0.0) {
            calcResult();
        }
    }

    protected void calcEnergyA() {
        energyA = round2decimals(capacityA/1000*voltageA);
        editText2.setText(Double.toString(energyA));
        if (capacityB != 0 && voltageB != 0.0) {
            calcResult();
        }
    }

    protected void calcVoltageA() {
        voltageA = round2decimals(energyA/capacityA*1000);
        editText3.setText(Double.toString(voltageA));
        if (capacityB != 0 && voltageB != 0.0) {
            calcResult();
        }
    }

    protected void calcResult() {
        int capacityC = (int)Math.round(capacityA*voltageA/voltageB*Math.pow(0.875,2));
        textView13.setText("Real charging capacity: " + Integer.toString(capacityC) + " mAh\nPercentage you can charge: " + Integer.toString((int)Math.round((double)capacityC/capacityB*100)) + " %\nYou can charge your phone " + Double.toString(round2decimals((double)capacityC/capacityB)) + " times!");
    }
}
