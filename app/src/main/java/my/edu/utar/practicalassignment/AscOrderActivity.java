package my.edu.utar.practicalassignment;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class AscOrderActivity extends AppCompatActivity {
    private int[] orderNum = new int[5];

    private int currentIndex=0;
    private int num1;
    private int num2;
    private int num3;
    private int num4;
    private int num5;
    private TextView num1TextView;
    private TextView num2TextView;
    private TextView num3TextView;
    private TextView num4TextView;
    private TextView num5TextView;
    private TextView answerTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_asc_order);

        num1TextView = findViewById(R.id.number1textView);
        num2TextView = findViewById(R.id.number2textView);
        num3TextView = findViewById(R.id.number3textView);
        num4TextView = findViewById(R.id.number4textView);
        num5TextView = findViewById(R.id.number5textView);
        answerTextView = findViewById(R.id.answerTextView);

        generateNumbers();

        num1TextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                orderNum[currentIndex]=num1;
                currentIndex++;
                num1TextView.setOnClickListener(null);
                updateAnswerTextView();
            }
        });

        num2TextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                orderNum[currentIndex]=num2;
                currentIndex++;
                num2TextView.setOnClickListener(null);
                updateAnswerTextView();
            }
        });

        num3TextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                orderNum[currentIndex]=num3;
                currentIndex++;
                num3TextView.setOnClickListener(null);
                updateAnswerTextView();
            }
        });

        num4TextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                orderNum[currentIndex]=num4;
                currentIndex++;
                num4TextView.setOnClickListener(null);
                updateAnswerTextView();
            }
        });

        num5TextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                orderNum[currentIndex]=num5;
                currentIndex++;
                num5TextView.setOnClickListener(null);
                updateAnswerTextView();
            }
        });

    }

    private void generateNumbers(){
        Random random = new Random();

        HashSet<Integer> numbers = new HashSet<>();

        while (numbers.size() < 5) {
            int randomNum = random.nextInt(100);
            numbers.add(randomNum);
        }

        ArrayList<Integer> numbers_array = new ArrayList<>(numbers);
        num1 = numbers_array.get(0);
        num2= numbers_array.get(1);
        num3 = numbers_array.get(2);
        num4 = numbers_array.get(3);
        num5 = numbers_array.get(4);

        num1TextView.setText(String.valueOf(num1));
        num2TextView.setText(String.valueOf(num2));
        num3TextView.setText(String.valueOf(num3));
        num4TextView.setText(String.valueOf(num4));
        num5TextView.setText(String.valueOf(num5));
    }

    private void updateAnswerTextView() {
        StringBuilder answerText = new StringBuilder();
        for (int i = 0; i < orderNum.length; i++) {
            if (orderNum[i] != 0) {
                answerText.append(orderNum[i]).append(" ");
            }
        }
        answerTextView.setText(answerText.toString().trim());
    }

}