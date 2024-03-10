package my.edu.utar.practicalassignment;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
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
    private boolean isGreaterThanQuestion;
    private TextView questionTextView;
    private Button num1Button;
    private Button num2Button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compare_exercise);

        questionTextView = findViewById(R.id.questionTextView);
        num1Button= findViewById(R.id.number1button);
        num2Button = findViewById(R.id.number2button);

        generateQuestion();
        generateNumbers();
        num1Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkAnswer(num1, num2);
            }
        });
        num2Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkAnswer(num2, num1);
            }
        });
    }

    private void generateQuestion(){
        Random random = new Random();
        isGreaterThanQuestion = random.nextBoolean();
        questionTextView.setText(isGreaterThanQuestion ? "Which one is greater?" : "Which one is smaller?");
    }

    private void generateNumbers(){
        Random random = new Random();
        do {
            num1 = random.nextInt(1000);
            num2 = random.nextInt(1000);
        } while (num1 == num2);

        num1Button.setText(String.valueOf(num1));
        num2Button.setText(String.valueOf(num2));
    }

    private void checkAnswer(int selectedNum, int otherNum) {
        String answer;
        String message;
        if(isGreaterThanQuestion){
            if (selectedNum > otherNum) {
                answer = "Correct";
                message =  selectedNum +" is greater than " + otherNum;
            } else {
                answer = "Wrong";
                message = selectedNum +" is smaller than " + otherNum;
            }
        }else{
            if (selectedNum < otherNum) {
                answer = "Correct";
                message = selectedNum +" is smaller than " + otherNum;
            } else {
                answer = "Wrong";
                message =  selectedNum +" is greater than " + otherNum;
            }
        }
        displayDialogBox(answer,message);
    }

    private void displayDialogBox(String answer,String message) {
        ImageView image = new ImageView(this);
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        if(answer.equals("Correct")){
            builder.setTitle("You are correct!");
            builder.setIcon(R.drawable.correct);
        }else if(answer.equals("Wrong")){
            builder.setTitle("You are wrong!");
            builder.setIcon(R.drawable.wrong);
        }
        builder.setMessage(message);
        builder.setCancelable(false);
        builder.setPositiveButton("Next Question", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                generateQuestion();
                generateNumbers();
            }
        });

        AlertDialog alert = builder.create();
        alert.show();
    }
}