package my.edu.utar.practicalassignment;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Random;

public class CompareExerciseActivity extends AppCompatActivity {
    private int num1;
    private int num2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compare_exercise);

        TextView num1textView = findViewById(R.id.number1textView);
        TextView num2textView = findViewById(R.id.number2textView);
        Button num1Button = findViewById(R.id.number1button);
        Button num2Button = findViewById(R.id.number2button);

        Random random = new Random();


        do {
            num1 = random.nextInt(100);
            num2 = random.nextInt(100);
        } while (num1 == num2);


        num1textView.setText(String.valueOf(num1));
        num2textView.setText(String.valueOf(num2));

        num1Button.setText(String.valueOf(num1) + " is greater");
        num2Button.setText(String.valueOf(num2) + " is greater");


        num1Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (num1 > num2) {
                    displayDialogBox("Correct", num1, num2);
                } else {
                    displayDialogBox("Incorrect", num2, num1);
                }
            }
        });
        num2Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (num2 > num1) {
                    displayDialogBox("Correct", num2, num1);
                } else {
                    displayDialogBox("Incorrect", num1, num2);
                }
            }
        });
    }

    private void displayDialogBox(String message, int greaterNum, int smallerNum) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Answer");
        builder.setMessage(message + ", " + greaterNum + " is greater than " + smallerNum);
        builder.setCancelable(false);
        builder.setPositiveButton("Next Question", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                recreate();
            }
        });

        AlertDialog alert = builder.create();
        alert.show();
    }
}