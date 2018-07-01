package com.example.android.worldfacts;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
// Evaluate button set
    public void startEvaluation(View view) {
        String[] answers = evaluateGui();

        int result = evaluateQuiz(answers);

        toastResult(result);
    }

    // compilation for thte evaluate button set
    // @EditText
    //@two check boxes
    //@two radio button
    public String[] evaluateGui() {
        String[] ret = new String[5];
        EditText editTextQuestion1 = findViewById(R.id.question_1);

        CheckBox checkBoxQuestion2 = findViewById(R.id.question_2);
        CheckBox checkBoxQuestion2B = findViewById(R.id.question_2_B);
        CheckBox checkBoxQuestion2c = findViewById(R.id.question_2_c);

        Boolean answerQuestion2 = false;

        if (checkBoxQuestion2.isChecked() == true && checkBoxQuestion2B.isChecked() == false && checkBoxQuestion2c.isChecked() == true) {
            answerQuestion2 = true;
        }

        CheckBox checkBoxQuestion4a = findViewById(R.id.question_4a);
        CheckBox checkBoxQuestion4b = findViewById(R.id.question_4b);
        CheckBox checkBoxQuestion4c = findViewById(R.id.question_4c);

        Boolean answerQuestion4 = false;

        Boolean rook = checkBoxQuestion4a.isChecked();
        Boolean bishop = checkBoxQuestion4b.isChecked();
        Boolean knight = checkBoxQuestion4c.isChecked();


        if (rook == true && bishop == false && knight == false) {
            answerQuestion4 = true;
        }

        ret[0] = editTextQuestion1.getText().toString().toLowerCase();
        ret[1] = Boolean.toString(answerQuestion2);
        ret[2] = evaluateRadioGroup(R.id.radio_group_question_3).toLowerCase();
        ret[3] = Boolean.toString(answerQuestion4);
        ret[4] = evaluateRadioGroup(R.id.radio_group_question_5).toLowerCase();

        return ret;
    }

    // toast message
    public void toastResult(int result) {
        String message = "Hello Lad," + result + " out of 5. ";

        if (result == 0) {
            message += "Poor luck.";
        } else if (result == 1) {
            message += "You could do better.";
        } else if (result == 2) {
            message += "Quite nice.";
        } else if (result == 3) {
            message += "Really nice.";
        } else if (result == 4) {
            message += "Great!";
        } else if (result == 5) {
            message += "Absolutely awesome!";
        }

        Toast reportResult = Toast.makeText(getApplicationContext(), message,
                Toast.LENGTH_SHORT);
        reportResult.show();
    }

    private String evaluateRadioGroup(int question_3) {
        RadioGroup radioGroupQuestion;
        RadioButton radioButtonQuestion;

        radioGroupQuestion = findViewById(question_3);

        int radioButtonId = radioGroupQuestion.getCheckedRadioButtonId();
        radioButtonQuestion = findViewById(radioButtonId);

        if (radioButtonQuestion == null) {
            return "";
        }

        return (String)radioButtonQuestion.getText();
    }

    public int evaluateQuiz(String[] answers) {
        int result = 0;
        String[] correctAnswers = {"paris", "true", "Madrid", "true", "answer1"};

        for (int i = 0; i < correctAnswers.length; i++) {
            if (answers[i].equals(correctAnswers[i])) {
                result++;
            }
        }

        return result;
    }
    // Reset button set
    public void reset(View view) {
        EditText editText = findViewById(R.id.question_1);
        editText.setText("");

        CheckBox checkBox = findViewById(R.id.question_2);
        checkBox.setChecked(false);

        checkBox = findViewById(R.id.question_2_B);
        checkBox.setChecked(false);

        checkBox = findViewById(R.id.question_2_c);
        checkBox.setChecked(false);

        RadioGroup radioGroup = findViewById(R.id.radio_group_question_3);
        radioGroup.clearCheck();

        checkBox = findViewById(R.id.question_4a);
        checkBox.setChecked(false);

        checkBox = findViewById(R.id.question_4b);
        checkBox.setChecked(false);

        checkBox = findViewById(R.id.question_4c);
        checkBox.setChecked(false);

        radioGroup = findViewById(R.id.radio_group_question_5);
        radioGroup.clearCheck();
    }
}
