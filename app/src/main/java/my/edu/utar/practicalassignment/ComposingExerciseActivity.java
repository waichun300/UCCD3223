package my.edu.utar.practicalassignment;

import android.app.AlertDialog;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Arrays;
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

        generateQuestion();
        generateNumbers();

        // Set click listeners for buttons
        setButtonOnClickListener(button1);
        setButtonOnClickListener(button2);
        setButtonOnClickListener(button3);
        setButtonOnClickListener(button4);
        checkAnswerButton.setEnabled(false);

        checkAnswerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*
                Check if the selected numbers(the number of button in list) can be composed to get the same number as question.
                If one of the selected number is not equal to num 1 or num2 then it is wrong answer.
                Else it is correct answer.
                 */
                boolean allCorrect = true;
                for (Button button : clickedButtons) {
                    int number = Integer.parseInt(button.getText().toString());
                    if (number != num1 && number != num2) {
                        allCorrect = false;
                        break;
                    }
                }
                if (allCorrect) {
                    displayDialogBox("Correct", clickedButtons.get(0).getText() + " and " + clickedButtons.get(1).getText() + " can be composed into " + question);
                } else {
                    displayDialogBox("Wrong", clickedButtons.get(0).getText() + " and " + clickedButtons.get(1).getText() + " cannot be composed into " + question);
                }
            }

        });
        //remove the selected number and make all button visible
        resetAnswerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clickedButtons.clear();
                checkAnswerButton.setEnabled(false);
                enableAllButtons();
            }
        });
    }

    private void generateQuestion() {
        Random random = new Random();
        question = random.nextInt(1000);
        questionTextView.setText(String.valueOf(question));
    }

    private void generateNumbers() {
        Random random = new Random();
        //Generate 2 numbers which can compose into the question(number)
        num1 = random.nextInt(question);
        num2 = question - num1;

        //Generate two randoms numbers for num3 and num4 which cannot compose into question(number)
        do {
            num3 = random.nextInt(1000);
            num4 = random.nextInt(1000);
        } while (num3 + num4 == question || num3 == num1 || num3 == num2 || num3 == num4 || num4 == num1 || num4 == num2 || num3 > question || num4 > question);

        // Shuffle the numbers and random put into the button
        ArrayList<Integer> numberList = new ArrayList<>(Arrays.asList(num1, num2, num3, num4));
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
                /*
                Add or remove button from clickedButtons list based on click
                If the button currently clicked by the user is already in the list, remove it from the list
                ,reset its attribute (backgroundTint) and update other unclicked button visibility.
                If the button currently clicked by the user is not in the list, add it into the list.
                If the clickedButtons list size is 2, mean it is full and set other unclicked button invisible
                as well as set checkAnswerButton enabled.
                */
                if (clickedButtons.contains(button)) {
                    clickedButtons.remove(button);
                    button.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#FF69B4")));
                    checkAnswerButton.setEnabled(false);
                    updateOtherButtonStatus(View.VISIBLE);
                } else if (clickedButtons.size() < 2) {
                    clickedButtons.add(button);
                    button.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#A020F0")));
                    if (clickedButtons.size() == 2) {
                        checkAnswerButton.setEnabled(true);
                        updateOtherButtonStatus(View.INVISIBLE);
                    }
                }
            }
        });
    }

    //Make all button visible
    private void enableAllButtons() {
        Button[] buttons = {button1, button2, button3, button4};
        for (Button button : buttons) {
            button.setEnabled(true);
            button.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#FF69B4")));
            button.setVisibility(View.VISIBLE);
        }
    }

    //Make unclicked buttons become invisible
    private void updateOtherButtonStatus(int status) {
        Button[] buttons = {button1, button2, button3, button4};
        for (Button button : buttons) {
            if (!clickedButtons.contains(button)) {
                button.setVisibility(status);
            }
        }
    }


    //Display the dialog box to show user the answer is correct or not.
    private void displayDialogBox(String answer, String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.alert_dialog, null);

        builder.setView(dialogView);
        ImageView img = dialogView.findViewById(R.id.alertImage);
        if(answer.equals("Correct")){
            img.setImageResource(R.drawable.correct);
        }else if (answer.equals("Wrong")) {
            img.setImageResource(R.drawable.wrong);
        }
        builder.setTitle("Answer:")
                .setView(dialogView)
                .setCancelable(false);

        AlertDialog alert = builder.create();
        alert.show();

        TextView alertMessage = dialogView.findViewById(R.id.alertMessage );
        Button nextButton = dialogView.findViewById(R.id.nextButton);
        Button tryAgainButton = dialogView.findViewById(R.id.tryAgainButton);
        tryAgainButton.setVisibility(View.INVISIBLE);
        alertMessage.setText(message);

        //Close the alert and recall the functions for new question
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alert.dismiss();
                clickedButtons.clear();
                checkAnswerButton.setEnabled(false);
                generateQuestion();
                generateNumbers();
                enableAllButtons();
            }
        });

        //Close the alert and provide an option to allow user to try the same question
        if (answer.equals("Wrong")) {
            tryAgainButton.setVisibility(View.VISIBLE);
            tryAgainButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    alert.dismiss();
                    clickedButtons.clear();
                    checkAnswerButton.setEnabled(false);
                    enableAllButtons();
                }
            });
        }
    }
}
