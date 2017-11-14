package com.example.jibba_000.recycleforum;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {

    Button loginBtn2;
    EditText mailText;
    EditText passText;
    TextView forgotText;
    int height;
    int width;

    ViewGroup.MarginLayoutParams marginParams;

    // Firebase stuff
    private FirebaseAuth firebaseAuth;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // Fierbase declaration stuff
        firebaseAuth = FirebaseAuth.getInstance();

        progressDialog = new ProgressDialog(this);

        height = getWindowManager().getDefaultDisplay().getHeight();
        width = getWindowManager().getDefaultDisplay().getWidth();

        loginBtn2 = (Button) findViewById(R.id.loginBtn2);
        loginBtn2.setOnClickListener(buttonClickListener);
        marginParams = (ViewGroup.MarginLayoutParams) loginBtn2.getLayoutParams();

        mailText = (EditText) findViewById(R.id.editEmail1);
        passText = (EditText) findViewById(R.id.editPassword);

        //mailText.setOnFocusChangeListener(focusOnMail);
        //passText.setOnFocusChangeListener(focusOnPass);

        forgotText = (TextView) findViewById(R.id.forgotPassText);
        forgotText.setOnClickListener(buttonClickListener);

    }


    private View.OnClickListener buttonClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {

            switch (view.getId()) {
                case R.id.loginBtn2:
                    userLogin();
                    //Toast.makeText(LoginActivity.this, "You are trying to Login...", Toast.LENGTH_SHORT).show();
                    //Intent myMenuIntent = new Intent(LoginActivity.this, PostedActivity.class);
                    //startActivity(myMenuIntent);
                    break;
                case R.id.forgotPassText:
                    Toast.makeText(LoginActivity.this, "Du har glemt din adgangskode", Toast.LENGTH_SHORT).show();
                    break;

            }
        }
    };

    private void userLogin() {
        String email = mailText.getText().toString().trim();
        String pass = passText.getText().toString().trim();

        if ((TextUtils.isEmpty(email)) || (TextUtils.isEmpty(pass))) {
            // Email or Password empty
            Toast.makeText(LoginActivity.this, "Please enter valid values in text fields", Toast.LENGTH_SHORT).show();
            return;
        }
        // If EditText not empty it comes to here
        progressDialog.setMessage("Checker bruger information...");
        progressDialog.show();

        firebaseAuth.signInWithEmailAndPassword(email, pass)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        progressDialog.dismiss();
                        if (task.isSuccessful())
                        {
                            Intent myPageIntent = new Intent(LoginActivity.this, MyPageActivity.class);
                            startActivity(myPageIntent);
                            // The login is successful
                            //toastMessage("User login successful");

                            // Finish the LoginActivity and start up the MyFrontPageActivity (Not made yet) instead of main
                            // This is done in the firebase auth listener. Because we need to check the logged in users UserType, stored in the Database.
                        }
                        else
                        {
                            // Not successful
                            Toast.makeText(LoginActivity.this, "Brugernavn eller password er fokert! - Prøv igen!", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

}

