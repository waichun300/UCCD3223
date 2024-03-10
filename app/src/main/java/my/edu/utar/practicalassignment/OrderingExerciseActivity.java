package my.edu.utar.practicalassignment;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.HashSet;
import java.util.Random;

public class OrderingExerciseActivity extends AppCompatActivity {
    private int[] numbers = new int[5];
    private int currentIndex=0;
    private int num1;
    private int num2;
    private int num3;
    private int num4;
    private int num5;
    private TextView questionTextView;
    private TextView num1TextView;
    private TextView num2TextView;
    private TextView num3TextView;
    private TextView num4TextView;
    private TextView num5TextView;
    private TextView answerTextView;
    private Button checkAnswerButton;
    private boolean isAscendingOrderQuestion;
    private boolean ascending;
    private boolean descending;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ordering_exercise);

        questionTextView = findViewById(R.id.questionTextView);
        num1TextView = findViewById(R.id.number1textView);
        num2TextView = findViewById(R.id.number2textView);
        num3TextView = findViewById(R.id.number3textView);
        num4TextView = findViewById(R.id.number4textView);
        num5TextView = findViewById(R.id.number5textView);
        answerTextView = findViewById(R.id.answerTextView);
        checkAnswerButton = findViewById(R.id.checkAnswerButton);

        generateQuestion();

        generateNumbers();

        checkAnswerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isAscendingOrderQuestion){
                    ascending = checkAscOrder(numbers);
                    if(ascending){
                        displayDialogBox("Correct","The numbers are in ascending order.");
                    } else{
                        displayDialogBox("Wrong","The numbers are not in ascending order.");
                    }
                } else{
                    descending = checkDescOrder(numbers);
                    if(descending){
                        displayDialogBox("Correct","The numbers are in descending order.");
                    } else{
                        displayDialogBox("Wrong","The numbers are not in descending order.");
                    }
                }

            }
        });
    }

    private void generateQuestion(){
        Random random = new Random();
        isAscendingOrderQuestion = random.nextBoolean();
        questionTextView.setText("Order Numbers in " + (isAscendingOrderQuestion ? "Ascending Order" : "Descending Order"));
    }

    private void generateNumbers(){
        Random random = new Random();
        HashSet<Integer> numbers = new HashSet<>();

        while (numbers.size() < 5) {
            int randomNum = random.nextInt(1000);
            numbers.add(randomNum);
        }

        Integer[] randomNumber = numbers.toArray(new Integer[numbers.size()]);
        num1 = randomNumber[0];
        num2= randomNumber[1];
        num3 = randomNumber[2];
        num4 = randomNumber[3];
        num5 = randomNumber[4];

        num1TextView.setText(String.valueOf(num1));
        num2TextView.setText(String.valueOf(num2));
        num3TextView.setText(String.valueOf(num3));
        num4TextView.setText(String.valueOf(num4));
        num5TextView.setText(String.valueOf(num5));

        setNumberClickListener(num1TextView, num1);
        setNumberClickListener(num2TextView, num2);
        setNumberClickListener(num3TextView, num3);
        setNumberClickListener(num4TextView, num4);
        setNumberClickListener(num5TextView, num5);
    }

    private void setNumberClickListener(TextView textView, int number) {
        textView.setVisibility(View.VISIBLE);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                numbers[currentIndex] = number;
                currentIndex++;
                textView.setOnClickListener(null);
                textView.setVisibility(View.INVISIBLE);
                updateAnswerTextView();
            }
        });
    }
    private void updateAnswerTextView() {
        StringBuilder answerText = new StringBuilder();
        for (int i = 0; i < currentIndex; i++) {
                answerText.append(numbers[i]).append(" ");
        }
        answerTextView.setText(answerText.toString().trim());
        checkAnswerButton.setEnabled(currentIndex == 5);
    }

    private boolean checkAscOrder(int[] num){
        for (int i = 0; i < num.length - 1; i++) {
            if (num[i] > num[i + 1]) {
                return false;
            }
        }
        return true;
    }

    private boolean checkDescOrder(int[] num) {
        for (int i = 0; i < num.length - 1; i++) {
            if (num[i] < num[i + 1]) {
                return false;
            }
        }
        return true;
    }

    private void displayDialogBox(String answer,String message) {
        ImageView image = new ImageView(this);
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        if(answer.equals("Correct")){
            builder.setTitle("You are correct!");
            builder.setIcon(R.drawable.correct);
            builder.setPositiveButton("Next Question", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    answerTextView.setText("");
                    numbers = new int[5];
                    currentIndex = 0;
                    generateQuestion();
                    generateNumbers();
                    checkAnswerButton.setEnabled(false);
                }
            });
        }else if(answer.equals("Wrong")){
            builder.setTitle("You are wrong!");
            builder.setIcon(R.drawable.wrong);
            builder.setNegativeButton("Try Again", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    answerTextView.setText("");
                    numbers = new int[5];
                    currentIndex = 0;
                    setNumberClickListener(num1TextView, num1);
                    setNumberClickListener(num2TextView, num2);
                    setNumberClickListener(num3TextView, num3);
                    setNumberClickListener(num4TextView, num4);
                    setNumberClickListener(num5TextView, num5);
                    checkAnswerButton.setEnabled(false);
                }
            });
        }
        builder.setMessage(message);
        builder.setCancelable(false);

        AlertDialog alert = builder.create();
        alert.show();
    }

}