package com.example.quiz;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomsheet.BottomSheetDialog;

import java.text.MessageFormat;
public class MainActivity extends Activity implements View.OnClickListener {
    TextView question,attempt;
    Button ans1,ans2,ans3,ans4,submit;
    int score,totque,current=0,attempted=0;
    String selctedAns="";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        totque=QuestionAnswer.questions.length;
        question=findViewById(R.id.que);
        attempt=findViewById(R.id.attempted);
        ans1=findViewById(R.id.ans1);
        ans2=findViewById(R.id.ans2);
        ans3=findViewById(R.id.ans3);
        ans4=findViewById(R.id.ans4);
        submit = findViewById(R.id.submit);

        ans1.setOnClickListener(this);
        ans2.setOnClickListener(this);
        ans3.setOnClickListener(this);
        ans4.setOnClickListener(this);
        submit.setOnClickListener(this);

        loadNewQuestion();
    }

    private void loadNewQuestion() {
        attempt.setText(MessageFormat.format("Questions Attempted : {0}/10", attempted)); // Update the attempted count

        if (current == 9) {
            submit.setText(R.string.submit);
        }

        if (current > 9) {
            showBottomSheet();
            return;
        }

        question.setText(QuestionAnswer.questions[current]);
        ans1.setText(QuestionAnswer.choices[current][0]);
        ans2.setText(QuestionAnswer.choices[current][1]);
        ans3.setText(QuestionAnswer.choices[current][2]);
        ans4.setText(QuestionAnswer.choices[current][3]);
    }


    private void finishQuiz(){
        String passStatus="";
        if(score> 9*0.60){
            passStatus="Passed";
        }
        else{
            passStatus="Failed";
        }

        new AlertDialog.Builder(this)
                .setTitle(passStatus)
                .setMessage("score is "+score+" out of "+totque)
                .setPositiveButton("Restart",(dialogInterface,i) -> restartQuiz())
                .show();
    }


    private void restartQuiz() {
        submit.setText(R.string.submit_and_next);
        score = 0;
        current = 0;
        attempted = 0;
        loadNewQuestion();
    }


    private void showBottomSheet() {
        BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(MainActivity.this);
        View bottomSheetView = LayoutInflater.from(getApplicationContext()).inflate(R.layout.bottom_sheet, findViewById(R.id.ll_bottomsheet),false);
        TextView s =bottomSheetView.findViewById(R.id.id_score);
        Button restart = bottomSheetView.findViewById(R.id.restart_btn);
        restart.setOnClickListener(v -> {
            restartQuiz();
            bottomSheetDialog.dismiss();
        });
        s.setText(MessageFormat.format("Your Score is :\n{0}/10", score));
        bottomSheetDialog.setCancelable(false);
        bottomSheetDialog.setContentView(bottomSheetView);
        bottomSheetDialog.show();
    }

    @Override
    public void onClick(View v) {
        ans1.setBackgroundColor(Color.rgb(191,149,251));
        ans2.setBackgroundColor(Color.rgb(191,149,251));
        ans3.setBackgroundColor(Color.rgb(191,149,251));
        ans4.setBackgroundColor(Color.rgb(191,149,251));

        Button clickedBtn =(Button) v;

        if(clickedBtn.getId()==R.id.submit){
            if(selctedAns.equals(QuestionAnswer.Answers[current])){
                score++;
            }
            current++;
            loadNewQuestion();
        }
        else{
            selctedAns=clickedBtn.getText().toString();
            clickedBtn.setBackgroundColor(Color.rgb(0, 255, 0));
            attempted++;
        }
    }




}
