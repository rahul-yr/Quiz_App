package com.example.andriod.funquiz;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
        /*
        * @param nameOfUser is the name of the participant
        * */
        int score=0;
        String nameOfUser="";
        String emailId="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
   /*
   * function startQuizfunction to start quiz*/
    public void startQuizFunction(View view) {
        //Button termsCheck = (Button) findViewById(R.id.namesubmit_button);
        EditText et=(EditText)findViewById(R.id.name_editText);
        EditText et1=(EditText)findViewById(R.id.email_editText);
        emailId=et1.getText().toString();
        nameOfUser=et.getText().toString();
        if(nameOfUser.length()>0 && et1.length()>0){
            Toast welcomeMessage=Toast.makeText(getApplicationContext(),"Starting Examination",Toast.LENGTH_SHORT);
            welcomeMessage.show();
            setContentView(R.layout.questions_activity);
            TextView nameOfParicipant=(TextView) findViewById(R.id.name_of_paricipant);
            nameOfParicipant.setText("Name : "+nameOfUser);
        }
        else{
            Toast acceptTermsToast=Toast.makeText(getApplicationContext(),"You must have to enter Name and Email id to Start Quiz",Toast.LENGTH_LONG);
            acceptTermsToast.show();
        }
    }
    /*
       * function enterNamefunction to start quiz*/
    public void enterNameFunction(View view) {
        CheckBox termsCheck = (CheckBox) findViewById(R.id.terms_and_conditions_id);
        if(termsCheck.isChecked()){
            Toast welcomeMessage=Toast.makeText(getApplicationContext(),"Just one step away to start Quiz",Toast.LENGTH_SHORT);
            welcomeMessage.show();
            setContentView(R.layout.name_activity);
        }
        else{
            Toast acceptTermsToast=Toast.makeText(getApplicationContext(),"You must have to Agree to the terms and conditions to Start Quiz",Toast.LENGTH_LONG);
            acceptTermsToast.show();
        }
    }

    public void question1_submit_function(View view) {
        RadioGroup r1=(RadioGroup) findViewById(R.id.Question1);
        int selectedId=r1.getCheckedRadioButtonId();

        RadioButton q1=(RadioButton) findViewById(selectedId);
        // used to disable the linear layout
        LinearLayout myLayout = (LinearLayout) findViewById(R.id.linearLayout_question_1);
        TextView setScoreOnScreen =(TextView)findViewById(R.id.update_score_textView);
        if(q1.equals((RadioButton)findViewById(R.id.q1_a_button))){
            score=score+3;

           setScoreOnScreen.setText("Score : "+score);

        }else{
            score=score-2;
            setScoreOnScreen.setText("Score : "+score);
        }
        disableAll(myLayout);
    }

    public void question2_submit_function(View view) {
        RadioGroup r1=(RadioGroup) findViewById(R.id.Question2);
        int selectedId=r1.getCheckedRadioButtonId();

        RadioButton q1=(RadioButton) findViewById(selectedId);

        // used to disable the linear layout
        LinearLayout myLayout = (LinearLayout) findViewById(R.id.linearLayout_question_2);
        TextView setScoreOnScreen =(TextView)findViewById(R.id.update_score_textView);

        if(q1.equals((RadioButton)findViewById(R.id.q2_d_button))){
            score=score+3;

            setScoreOnScreen.setText("Score : "+score);

        }else{
            score=score-2;
            setScoreOnScreen.setText("Score : "+score);
        }
        disableAll(myLayout);
    }
    public void question3_submit_function(View view) {
        LinearLayout myLayout = (LinearLayout) findViewById(R.id.linearLayout_question_3);
        TextView setScoreOnScreen =(TextView)findViewById(R.id.update_score_textView);

        EditText et3=(EditText) findViewById(R.id.Question3);
        if(et3.getText().toString().equalsIgnoreCase("Extensible Markup Language")){
            score=score+3;

            setScoreOnScreen.setText("Score : "+score);
        }
        else{
            score=score-2;
            setScoreOnScreen.setText("Score : "+score);
        }
        disableAll(myLayout);

    }

    public void disableAll(LinearLayout li){
          for ( int i = 0; i < li.getChildCount();  i++ ){
                View local = li.getChildAt(i);
                local.setEnabled(false);
            }
        Toast welcomeMessage=Toast.makeText(getApplicationContext(),"Answer Submitted..Can't Change Answer ant more",Toast.LENGTH_SHORT);
        welcomeMessage.show();
    }


    public void all_questions_submit_function(View view) {
        Toast welcomeMessage=Toast.makeText(getApplicationContext(),"Quiz Ended",Toast.LENGTH_SHORT);
        welcomeMessage.show();
        setContentView(R.layout.result_activity);
        TextView resultTextView=(TextView) findViewById(R.id.result_of_paricipant);
        TextView emailTextView=(TextView) findViewById(R.id.email_of_paricipant);
        resultTextView.setText("Name : "+nameOfUser+"\nScore : "+score);
        emailTextView.setText("Email Id : "+emailId);

    }

    public void share_function(View view) {
            Intent intent = new Intent(Intent.ACTION_SENDTO);
            intent.setData(Uri.parse("mailto:")); // only email apps should handle this
            intent.putExtra(Intent.EXTRA_EMAIL, emailId);
            intent.putExtra(Intent.EXTRA_SUBJECT, "Quiz Results..");
            intent.putExtra(Intent.EXTRA_TEXT,"Name of Participant : "+nameOfUser+"\nScore of participant : "+score);
            if (intent.resolveActivity(getPackageManager()) != null) {
                startActivity(intent);
            }

    }
}
