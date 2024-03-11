package my.edu.utar.practicalassignment;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class ComposingExerciseActivity extends AppCompatActivity {
    private int num1;
    private int num2;
    private int num3;
    private int num4;
    private int question;
    private TextView questionTextView;
    private TextView statementTextView;
    private Button button1;
    private Button button2;
    private Button button3;
    private Button button4;
    private Button checkAnswerButton;
    private Button resetAnswerButton;
    private ArrayList<Button> clickedButtons = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_composing_exercise);

        statementTextView = findViewById(R.id.statementTextView);
        questionTextView = findViewById(R.id.questionTextView);
        button1 = findViewById(R.id.firstButton);
        button2 = findViewById(R.id.secondButton);
        button3 = findViewById(R.id.thirdButton);
        button4 = findViewById(R.id.fourthButton);
        checkAnswerButton = findViewById(R.id.checkAnsButton);
        resetAnswerButton = findViewById(R.id.resetAnsButton);

        generateQuestionAndStatement();
        generateNumbers();

        setButtonOnClickListener(button1);
        setButtonOnClickListener(button2);
        setButtonOnClickListener(button3);
        setButtonOnClickListener(button4);
        checkAnswerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(clickedButtons.isEmpty()){
                    statementTextView.setText("Select Two Numbers Before Check Answer");
                }else{
                    boolean allCorrect = true;
                    for (Button button : clickedButtons) {
                        int number = Integer.parseInt(button.getText().toString());
                        if (number != num1 && number != num2) {
                            allCorrect = false;
                            break;
                        }
                    }
                    if (allCorrect) {
                        displayDialogBox("Correct");
                    } else {
                        displayDialogBox("Wrong");
                    }
                }
            }
        });

        resetAnswerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clickedButtons.clear();
                enableAllButtons();
            }
        });
    }

    private void generateQuestionAndStatement() {
        Random random = new Random();
        question = random.nextInt(1000);
        questionTextView.setText(String.valueOf(question));
        statementTextView.setText("Select two numbers to compose " + String.valueOf(question));
    }

    private void generateNumbers() {
        Random random = new Random();
        num1 = random.nextInt(question);
        num2 = question - num1;

        do {
            num3 = random.nextInt(1000);
            num4 = random.nextInt(1000);
        } while (num3 + num4 == question);

        ArrayList<Integer> numberList = new ArrayList<>();
        numberList.add(num1);
        numberList.add(num2);
        numberList.add(num3);
        numberList.add(num4);

        Collections.shuffle(numberList);

        button1.setText(String.valueOf(numberList.get(0)));
        button2.setText(String.valueOf(numberList.get(1)));
        button3.setText(String.valueOf(numberList.get(2)));
        button4.setText(String.valueOf(numberList.get(3)));
    }

    private void setButtonOnClickListener(Button button) {
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (clickedButtons.size() < 2) {
                    clickedButtons.add(button);
                    button.setEnabled(false);
                    button.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#A020F0")));
                    if (clickedButtons.size() == 2) {
                        disableOtherButtons();
                    }
                }
            }
        });
    }

    private void enableAllButtons() {
        Button[] buttons = {button1, button2, button3, button4};
        for (Button button : buttons) {
            button.setEnabled(true);
            button.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#FF69B4")));
        }
    }

    private void disableOtherButtons() {
        Button[] buttons = {button1, button2, button3, button4};
        for (Button button : buttons) {
            if (!clickedButtons.contains(button)) {
                button.setEnabled(false);
            }
        }
    }

    private void displayDialogBox(String answer) {
        ImageView image = new ImageView(this);
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        if (answer.equals("Correct")) {
            builder.setTitle("You are correct!");
            builder.setIcon(R.drawable.correct);
            builder.setPositiveButton("Next Question", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    clickedButtons.clear();
                    generateQuestionAndStatement();
                    generateNumbers();
                    enableAllButtons();
                }
            });
        } else if (answer.equals("Wrong")) {
            builder.setTitle("You are wrong!");
            builder.setIcon(R.drawable.wrong);
            builder.setNegativeButton("Try Again", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    clickedButtons.clear();
                    enableAllButtons();
                }
            });
        }
        builder.setCancelable(false);

        AlertDialog alert = builder.create();
        alert.show();
    }
}
