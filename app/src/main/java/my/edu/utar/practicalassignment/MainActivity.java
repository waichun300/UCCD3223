package my.edu.utar.practicalassignment;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button compareNumbersButton = findViewById(R.id.compareButton);
        Button orderingNumbersButton = findViewById(R.id.orderingButton);
        Button composeNumbersButton = findViewById(R.id.composeButton);
        compareNumbersButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, CompareExerciseActivity.class);
                startActivity(intent);
            }
        });

        orderingNumbersButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, OrderingExerciseActivity.class);
                startActivity(intent);
            }
        });
        composeNumbersButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, ComposingExerciseActivity.class);
                startActivity(intent);
            }
        });
    }
}