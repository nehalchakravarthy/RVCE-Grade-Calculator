package com.example.android.rvcegradecalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    ProgressDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dialog = new ProgressDialog(MainActivity.this);
        dialog.setMessage("Calculating grade");
        dialog.setCanceledOnTouchOutside(false);

    }

    public void calculateGrade(View view) {

        EditText cieScore = (EditText) findViewById(R.id.cie_score);
        EditText seeScore = (EditText) findViewById(R.id.see_score);

        CheckBox labCheckbox = (CheckBox) findViewById(R.id.lab_checkbox);
        Button gradeButton = (Button) findViewById(R.id.button_grade);

        TextView gradeText = (TextView) findViewById(R.id.grade_text);

        int cieMarks, seeMarks, lowerBound=0, upperBound;

        Runnable progressRunnable = new Runnable() {
            @Override
            public void run() {
                dialog.cancel();
            }
        };
        Handler progressDialogCanceller = new Handler();

        if (labCheckbox.isChecked())
            upperBound = 150;
        else
            upperBound = 100;

        if(cieScore.length()==0) {
            Toast toast = Toast.makeText(getApplicationContext(), "Please enter CIE score", Toast.LENGTH_SHORT);
            toast.show();
        }
        else {
            cieMarks = Integer.parseInt(cieScore.getText().toString());

            if(cieMarks<lowerBound || cieMarks>upperBound) {
                Toast toast = Toast.makeText(getApplicationContext(), "Enter CIE score between " + Integer.toString(lowerBound) + " and " + Integer.toString(upperBound), Toast.LENGTH_SHORT);
                toast.show();
            }

            else if(seeScore.length()==0) {
                dialog.show();
                progressDialogCanceller.postDelayed(progressRunnable, 10000);

                seeMarks=0;
                if(cieMarks>=(upperBound*0.9)) {
                    gradeText.setText("Grade: S");
                    seeMarks = Math.round((float) (2*upperBound*0.9-cieMarks));
                }
                else if(cieMarks>=(upperBound*0.8)) {
                    gradeText.setText("Grade: A");
                    seeMarks = Math.round((float) (2*upperBound*0.8-cieMarks));
                }
                else if(cieMarks>=(upperBound*0.7)) {
                    gradeText.setText("Grade: B");
                    seeMarks = Math.round((float) (2*upperBound*0.7-cieMarks));
                }
                else if(cieMarks>=(upperBound*0.6)) {
                    gradeText.setText("Grade: C");
                    seeMarks = Math.round((float) (2*upperBound*0.6-cieMarks));
                }
                else if(cieMarks>=(upperBound*0.5)) {
                    gradeText.setText("Grade: D");
                    seeMarks = Math.round((float) (2*upperBound*0.5-cieMarks));
                }
                else if(cieMarks>=(upperBound*0.4)) {
                    gradeText.setText("Grade: E");
                    seeMarks = Math.round((float) (2*upperBound*0.4-cieMarks));
                }
                else
                    gradeText.setText("Grade: F");

                seeScore.setText(Integer.toString(seeMarks));
            }
            else {
                seeMarks = Integer.parseInt(seeScore.getText().toString());

                if(seeMarks<lowerBound || seeMarks>upperBound) {
                    Toast toast = Toast.makeText(getApplicationContext(), "Enter SEE score between " + Integer.toString(lowerBound) + " and " + Integer.toString(upperBound), Toast.LENGTH_SHORT);
                    toast.show();
                }
                else {
                    dialog.show();
                    progressDialogCanceller.postDelayed(progressRunnable, 10000);

                    int totalMarks = cieMarks + seeMarks;

                    if(totalMarks>=(2*upperBound*0.9))
                        gradeText.setText("Grade: S");
                    else if(totalMarks>=(2*upperBound*0.8))
                        gradeText.setText("Grade: A");
                    else if(totalMarks>=(2*upperBound*0.7))
                        gradeText.setText("Grade: B");
                    else if(totalMarks>=(2*upperBound*0.6))
                        gradeText.setText("Grade: C");
                    else if(totalMarks>=(2*upperBound*0.5))
                        gradeText.setText("Grade: D");
                    else if(totalMarks>=(2*upperBound*0.4))
                        gradeText.setText("Grade: E");
                    else
                        gradeText.setText("Grade: F");
                }

            }
        }
    }

}