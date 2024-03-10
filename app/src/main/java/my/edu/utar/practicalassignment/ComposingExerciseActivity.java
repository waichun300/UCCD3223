package my.edu.utar.practicalassignment;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Random;

public class ComposingExerciseActivity extends AppCompatActivity {
    private int num1;
    private int num2;
    private int num3;
    private int num4;
    private int question;
    private int correctIndex;
    private boolean isCorrect;
    private TextView questionTextView;
    private Button button1;
    private Button button2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_composing_exercise);

        questionTextView = findViewById(R.id.questionTextView);
        button1 = findViewById(R.id.firstButton);
        button2 = findViewById(R.id.secondButton);

        Random random = new Random();
        question = random.nextInt(1000);

        num1 = random.nextInt(question);
        num2 = question - num1;

        do {
            num3 = random.nextInt(1000);
            num4 = random.nextInt(1000);
        } while (num3 + num4 == question);

        questionTextView.setText(String.valueOf(question));

        correctIndex = random.nextInt(2);

        if (correctIndex == 0){
            button1.setText(num1 + " + " + num2);
            button2.setText(num3 + " + " + num4);
        }else{
            button1.setText(num3 + " + " + num4);
            button2.setText(num1 + " + " + num1);
        }
    }
}