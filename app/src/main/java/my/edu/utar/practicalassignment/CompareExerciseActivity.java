package my.edu.utar.practicalassignment;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

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

    //Generate question
    private void generateQuestion(){
        Random random = new Random();
        isGreaterThanQuestion = random.nextBoolean();
        //TRUE - isGreaterQuestion //FALSE - isSmallerQuestion
        questionTextView.setText(isGreaterThanQuestion ? "Which one is greater?" : "Which one is smaller?");
    }

    //Generate random two numbers for the users
    private void generateNumbers(){
        Random random = new Random();
        do {
            num1 = random.nextInt(1000);
            num2 = random.nextInt(1000);
        } while (num1 == num2);

        num1Button.setText(String.valueOf(num1));
        num2Button.setText(String.valueOf(num2));
    }

    //Check the correct answer by compare user select number with other number
    private void checkAnswer(int selectedNum, int otherNum) {
        boolean isCorrect;
        String comparison;
        if (isGreaterThanQuestion) {
            isCorrect = selectedNum > otherNum;
            comparison = "greater than";
        } else {
            isCorrect = selectedNum < otherNum;
            comparison = "smaller than";
        }
        String message = selectedNum + " is " + (isCorrect ? "" : "not ") + comparison + " " + otherNum;
        String answer = isCorrect ? "Correct" : "Wrong";
        displayDialogBox(answer, message);
    }

    //Display the dialog box to show user the answer is correct or not
    private void displayDialogBox(String answer, String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.alert_dialog, null);

        ImageView img = dialogView.findViewById(R.id.alertImage);
        TextView alertMessage = dialogView.findViewById(R.id.alertMessage);
        Button nextButton = dialogView.findViewById(R.id.nextButton);
        Button tryAgainButton = dialogView.findViewById(R.id.tryAgainButton);
        tryAgainButton.setVisibility(View.INVISIBLE);

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

        alertMessage.setText(message);
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alert.dismiss();
                generateQuestion();
                generateNumbers();
            }
        });
    }
}