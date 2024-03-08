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
    private int question;
    private TextView compareTextView;
    private TextView num1textView;
    private  TextView num2textView;

    private Button num1Button;

    private Button num2Button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compare_exercise);

        compareTextView = findViewById(R.id.textView2);
        num1textView = findViewById(R.id.number1textView);
        num2textView = findViewById(R.id.number2textView);
        num1Button= findViewById(R.id.number1button);
        num2Button = findViewById(R.id.number2button);

        Random random = new Random();
        question = random.nextInt(2);
        compareTextView.setText(question == 0 ? "Which one is greater?" : "Which one is smaller?");
        generateNumbers();


        num1Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (num1 > num2) {
                    displayDialogBox("Correct. " + num1 +" is greater than " + num2);
                } else {
                    displayDialogBox("Incorrect. " + num1 +" is smaller than " + num2);
                }
            }
        });
        num2Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (num2 > num1) {
                    displayDialogBox("Correct. " + num2 +" is greater than " + num1);
                } else {
                    displayDialogBox("Incorrect. " + num2 +" is smaller than " + num1);
                }
            }
        });
    }

    private void generateNumbers(){
        Random random = new Random();
        do {
            num1 = random.nextInt(100);
            num2 = random.nextInt(100);
        } while (num1 == num2);

        num1textView.setText(String.valueOf(num1));
        num2textView.setText(String.valueOf(num2));

        num1Button.setText(question == 0 ? String.valueOf(num1) + " is greater" : String.valueOf(num1) + " is smaller");
        num2Button.setText(question == 0 ? String.valueOf(num2) + " is greater" : String.valueOf(num2) + " is smaller");
    }

    private void displayDialogBox(String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Answer");
        builder.setMessage(message);
        builder.setCancelable(false);
        builder.setPositiveButton("Next Question", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                generateNumbers();
            }
        });

        AlertDialog alert = builder.create();
        alert.show();
    }
}