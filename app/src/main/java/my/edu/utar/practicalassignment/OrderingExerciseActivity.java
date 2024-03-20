package my.edu.utar.practicalassignment;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;

public class OrderingExerciseActivity extends AppCompatActivity {
    private ArrayList<Integer> clickedNumbersButtonIndex = new ArrayList<>();
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

        // Set click listeners for numberButtons
        setNumButtonClickListener(num1Button,0);
        setNumButtonClickListener(num2Button,1);
        setNumButtonClickListener(num3Button,2);
        setNumButtonClickListener(num4Button,3);
        setNumButtonClickListener(num5Button,4);

        // Set click listeners for answerButtons
        setAnswerButtonClickListener(ans1Button,0);
        setAnswerButtonClickListener(ans2Button,1);
        setAnswerButtonClickListener(ans3Button,2);
        setAnswerButtonClickListener(ans4Button,3);
        setAnswerButtonClickListener(ans5Button,4);

        checkAnswerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean isCorrect = (isAscendingOrderQuestion) ? checkAscOrder(clickedNumbers): checkDescOrder(clickedNumbers);

                String order = (isAscendingOrderQuestion) ? "ascending" : "descending";

                if(isCorrect){
                    displayDialogBox("Correct","The numbers are in " + order + " order.");
                }else{
                    displayDialogBox("Wrong","The numbers are not in " + order + " order.");
                }
            }
        });

        resetAnswerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                reset();
            }
        });
    }

    // Generate question (ascending or descending)
    private void generateQuestion(){
        Random random = new Random();
        isAscendingOrderQuestion = random.nextBoolean();
        //TRUE - Ascending  //FALSE - Descending
        questionTextView.setText("Order Numbers in " + (isAscendingOrderQuestion ? "Ascending Order" : "Descending Order"));
    }

    private void generateNumbers(){
        Random random = new Random();
        // Ensure uniqueness of numbers by hashset
        HashSet<Integer> numbers = new HashSet<>();
        while (numbers.size() < 5) {
            int randomNum = random.nextInt(1000);
            numbers.add(randomNum);
        }
        // Assign numbers to variables and set button text
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

    private void setNumButtonClickListener(Button button, int currentIndex) {
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int num = Integer.parseInt(button.getText().toString());
                //set the button currently clicked by the user become INVISIBLE
                button.setVisibility(View.INVISIBLE);
                /*
                 Enable corresponding answer button
                 If size = 0, enable ans1button
                 If size = 1, enable ans2button
                 etc.
                 If size =4, enable all answerButtons and checkAnswerButton
                 */
                switch (clickedNumbersButtonIndex.size()) {
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
                /*
                Store the index and the no of numbers currently selected by the user into different list
                Example for Index:
                Num1 - 0(Index)
                 */
                clickedNumbersButtonIndex.add(currentIndex);
                clickedNumbers.add(num);
                updateAnswerButtonsText();
            }
        });
    }

    private void setAnswerButtonClickListener(Button button, int currentIndex) {
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*
                When the user click answerButton, the clickedNumbers list will remove the number
                and clickedNumbersButtonIndex list will remove the index of clicked button
                For example
                if user click ans1 button (0 - index)
                clickedNumbersButtonIndex list will remove the first element from the list
                clickNumbers list will remove the first number from the list
                 */
                int clickedBtnIndex = clickedNumbersButtonIndex.get(currentIndex);
                clickedNumbers.remove(currentIndex);
                clickedNumbersButtonIndex.remove(currentIndex);
                updateAnswerButtonsText();
                //Disable current last button in selection list
                switch (clickedNumbersButtonIndex.size()) {
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
                //Get the index of the clickedNumberButton and make the corresponding numberButton VISIBLE
                switch (clickedBtnIndex) {
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

        //Move all the numbers located after removed number ahead
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

    //Reset all the ArrayLists and the attribute of the buttons
    private void reset(){
        clickedNumbers.clear();
        clickedNumbersButtonIndex.clear();
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
    private void nextQuestion(){
        generateQuestion();
        generateNumbers();
        reset();
    }

    //Display the dialog box to show user the answer is correct or not
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
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alert.dismiss();
                nextQuestion();
            }
        });

        if (answer.equals("Wrong")) {
            tryAgainButton.setVisibility(View.VISIBLE);
            tryAgainButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    alert.dismiss();
                    reset();
                }
            });
        }
    }
}