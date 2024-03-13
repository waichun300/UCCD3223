package my.edu.utar.practicalassignment;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;

public class OrderingExerciseActivity extends AppCompatActivity {
    private ArrayList<Integer> clickedNumbersIndex = new ArrayList<>();

    private ArrayList<Integer> clickedNumbers = new ArrayList<>();
    private int num1;
    private int num2;
    private int num3;
    private int num4;
    private int num5;
    private TextView questionTextView;
    private Button num1Button;
    private Button num2Button;
    private Button num3Button;
    private Button num4Button;
    private Button num5Button;
    private Button ans1Button;
    private Button ans2Button;
    private Button ans3Button;
    private Button ans4Button;
    private Button ans5Button;
    private Button checkAnswerButton;

    private Button resetAnswerButton;
    private boolean isAscendingOrderQuestion;
    private boolean ascending;
    private boolean descending;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ordering_exercise);

        questionTextView = findViewById(R.id.questionTextView);
        num1Button = findViewById(R.id.ordNum1Button);
        num2Button = findViewById(R.id.ordNum2Button);
        num3Button = findViewById(R.id.ordNum3Button);
        num4Button = findViewById(R.id.ordNum4Button);
        num5Button = findViewById(R.id.ordNum5Button);
        ans1Button = findViewById(R.id.ansNum1Button);
        ans2Button = findViewById(R.id.ansNum2Button);
        ans3Button = findViewById(R.id.ansNum3Button);
        ans4Button = findViewById(R.id.ansNum4Button);
        ans5Button = findViewById(R.id.ansNum5Button);
        checkAnswerButton = findViewById(R.id.checkAnswerButton);
        resetAnswerButton = findViewById(R.id.resetAnswerButton);

        ans1Button.setEnabled(false);
        ans2Button.setEnabled(false);
        ans3Button.setEnabled(false);
        ans4Button.setEnabled(false);
        ans5Button.setEnabled(false);

        checkAnswerButton.setEnabled(false);

        generateQuestion();
        generateNumbers();
        setButtonClickListener(num1Button,0);
        setButtonClickListener(num2Button,1);
        setButtonClickListener(num3Button,2);
        setButtonClickListener(num4Button,3);
        setButtonClickListener(num5Button,4);

        setAnswerButtonClickListener(ans1Button,0);
        setAnswerButtonClickListener(ans2Button,1);
        setAnswerButtonClickListener(ans3Button,2);
        setAnswerButtonClickListener(ans4Button,3);
        setAnswerButtonClickListener(ans5Button,4);

        checkAnswerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isAscendingOrderQuestion){
                    ascending = checkAscOrder(clickedNumbers);
                    if(ascending){
                        displayDialogBox("Correct","The numbers are in ascending order.");
                    } else{
                        displayDialogBox("Wrong","The numbers are not in ascending order.");
                    }
                } else{
                    descending = checkDescOrder(clickedNumbers);
                    if(descending){
                        displayDialogBox("Correct","The numbers are in descending order.");
                    } else{
                        displayDialogBox("Wrong","The numbers are not in descending order.");
                    }
                }

            }
        });

        resetAnswerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reset();
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

        num1Button.setText(String.valueOf(num1));
        num2Button.setText(String.valueOf(num2));
        num3Button.setText(String.valueOf(num3));
        num4Button.setText(String.valueOf(num4));
        num5Button.setText(String.valueOf(num5));
    }

    private void setButtonClickListener(Button button, int currentIndex) {
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int num = Integer.parseInt(button.getText().toString());
                button.setVisibility(View.INVISIBLE);
                switch (clickedNumbersIndex.size()) {
                    case 0:
                        ans1Button.setEnabled(true);
                        break;
                    case 1:
                        ans2Button.setEnabled(true);
                        break;
                    case 2:
                        ans3Button.setEnabled(true);
                        break;
                    case 3:
                        ans4Button.setEnabled(true);
                        break;
                    case 4:
                        ans5Button.setEnabled(true);
                        checkAnswerButton.setEnabled(true);
                        break;
                }
                clickedNumbersIndex.add(currentIndex);
                clickedNumbers.add(num);
                updateAnswerButtonsText();
            }
        });
    }

    private void setAnswerButtonClickListener(Button button, int currentIndex) {
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int numIndex = clickedNumbersIndex.get(currentIndex);
                clickedNumbers.remove(currentIndex);
                clickedNumbersIndex.remove(currentIndex);
                updateAnswerButtonsText();

                switch (clickedNumbersIndex.size()) {
                    case 0:
                        ans1Button.setEnabled(false);
                        break;
                    case 1:
                        ans2Button.setEnabled(false);
                        break;
                    case 2:
                        ans3Button.setEnabled(false);
                        break;
                    case 3:
                        ans4Button.setEnabled(false);
                        break;
                    case 4:
                        ans5Button.setEnabled(false);
                        checkAnswerButton.setEnabled(false);
                        break;
                }

                switch (numIndex) {
                    case 0:
                        num1Button.setVisibility(View.VISIBLE);
                        break;
                    case 1:
                        num2Button.setVisibility(View.VISIBLE);
                        break;
                    case 2:
                        num3Button.setVisibility(View.VISIBLE);
                        break;
                    case 3:
                        num4Button.setVisibility(View.VISIBLE);
                        break;
                    case 4:
                        num5Button.setVisibility(View.VISIBLE);
                        break;
                }
            }
        });
    }
    private void updateAnswerButtonsText() {
        ans1Button.setText("");
        ans2Button.setText("");
        ans3Button.setText("");
        ans4Button.setText("");
        ans5Button.setText("");

        for (int i = 0; i < clickedNumbers.size(); i++) {
            switch (i) {
                case 0:
                    ans1Button.setText(String.valueOf(clickedNumbers.get(i)));
                    break;
                case 1:
                    ans2Button.setText(String.valueOf(clickedNumbers.get(i)));
                    break;
                case 2:
                    ans3Button.setText(String.valueOf(clickedNumbers.get(i)));
                    break;
                case 3:
                    ans4Button.setText(String.valueOf(clickedNumbers.get(i)));
                    break;
                case 4:
                    ans5Button.setText(String.valueOf(clickedNumbers.get(i)));
                    break;
            }
        }
    }
    private boolean checkAscOrder(ArrayList<Integer> answer){
        for (int i = 0; i < answer.size() - 1; i++) {
            if (answer.get(i) > answer.get(i + 1)) {
                return false;
            }
        }
        return true;
    }
    private boolean checkDescOrder(ArrayList<Integer> answer) {
        for (int i = 0; i < answer.size() - 1; i++) {
            if (answer.get(i) < answer.get(i + 1)) {
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
                    nextQuestion();
                }
            });
        }else if(answer.equals("Wrong")){
            builder.setTitle("You are wrong!");
            builder.setIcon(R.drawable.wrong);
            builder.setNegativeButton("Try Again", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    reset();
                }
            });
            builder.setPositiveButton("Next Question", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    nextQuestion();
                }
            });
        }
        builder.setMessage(message);
        builder.setCancelable(false);

        AlertDialog alert = builder.create();
        alert.show();
    }

    private void nextQuestion(){
        generateQuestion();
        generateNumbers();
        clickedNumbers.clear();
        clickedNumbersIndex.clear();
        updateAnswerButtonsText();
        num1Button.setVisibility(View.VISIBLE);
        num2Button.setVisibility(View.VISIBLE);
        num3Button.setVisibility(View.VISIBLE);
        num4Button.setVisibility(View.VISIBLE);
        num5Button.setVisibility(View.VISIBLE);
        ans1Button.setEnabled(false);
        ans2Button.setEnabled(false);
        ans3Button.setEnabled(false);
        ans4Button.setEnabled(false);
        ans5Button.setEnabled(false);
        checkAnswerButton.setEnabled(false);
    }

    private void reset(){
        clickedNumbers.clear();
        clickedNumbersIndex.clear();
        updateAnswerButtonsText();
        num1Button.setVisibility(View.VISIBLE);
        num2Button.setVisibility(View.VISIBLE);
        num3Button.setVisibility(View.VISIBLE);
        num4Button.setVisibility(View.VISIBLE);
        num5Button.setVisibility(View.VISIBLE);
        ans1Button.setEnabled(false);
        ans2Button.setEnabled(false);
        ans3Button.setEnabled(false);
        ans4Button.setEnabled(false);
        ans5Button.setEnabled(false);
        checkAnswerButton.setEnabled(false);
    }
}