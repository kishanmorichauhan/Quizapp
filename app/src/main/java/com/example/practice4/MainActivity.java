package com.example.practice4;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import javax.security.auth.Subject;

public class MainActivity extends AppCompatActivity {

    private TextView quction, call;
    TextView editTextTo, editTextSubject, editTextMessage;
    Button yes;
    Button no;
    String[] quctions = {"1.Default value of Boolean variable is False", "2.Constructor made private", "3.Double variable size is 32 bit", "4. Instance variables are variables within a class but outside any method.", "5.Static binding occurs during runtime.","class is a special data type"};
    Boolean[] ans = {true, true, false, true, false,false};
    private int score = 0;
    private int index = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        yes = findViewById(R.id.button);
        no = findViewById(R.id.button2);
        quction = findViewById(R.id.quction);
        call = findViewById(R.id.call);
        editTextTo = (TextView) findViewById(R.id.email);
        editTextSubject = (TextView) findViewById(R.id.email);
        editTextMessage = (TextView) findViewById(R.id.email);


        yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (index <= quctions.length - 1) {
                    if (ans[index] == true) {
                        score++;
                    }
                    index++;
                    if (index <= quctions.length - 1) {
                        quction.setText(quctions[index]);
                    } else {
                        Toast.makeText(MainActivity.this, "Your score is: " + score, Toast.LENGTH_LONG).show();
                    }
                }
            }
        });
        no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (index <= quctions.length - 1) {
                    if (ans[index] == false) {
                        score++;
                    }
                    index++;
                    if (index <= quctions.length - 1) {
                        quction.setText(quctions[index]);
                    } else {
                        Toast.makeText(MainActivity.this, "Your score is: " + score, Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
        call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:7434922744"));
                if (intent.resolveActivity(getPackageManager()) != null) {
                    startActivity(intent);
                }
            }
        });
    }
    public void onClick(View view){
        String to = editTextTo.getText().toString();
        String subject = editTextSubject.getText().toString();
        String message = editTextMessage.getText().toString();


        Intent email = new Intent(Intent.ACTION_SEND);
        email.putExtra(Intent.EXTRA_EMAIL, new String[]{to});
        email.putExtra(Intent.EXTRA_SUBJECT, subject);
        email.putExtra(Intent.EXTRA_TEXT, message);

        //need this to prompts email client only
        email.setType("message/rfc822");

        startActivity(Intent.createChooser(email, "Choose an Email client :"));
    }

}