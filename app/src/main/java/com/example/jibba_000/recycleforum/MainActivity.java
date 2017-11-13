package com.example.jibba_000.recycleforum;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.InputType;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {


    Button loginButton;
    Button registerButton;
    int height;
    int width;
    String str;

//Firebase stuff

    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;


        @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

            //firebase decleration
            mAuth = FirebaseAuth.getInstance();

           /* mAuthListener = new FirebaseAuth.AuthStateListener() {
                @Override
                public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                    FirebaseUser user = firebaseAuth.getCurrentUser();

                    if (user != null) {
                        // User is signed in
                        Log.d(TAG, "onAuthStateChanged:signed_in:" + user.getUid());
                    } else {
                        // User is signed out
                        Log.d(TAG, "onAuthStateChanged:signed_out");
                    }
                    // ...
                }
            };*/
            mAuthListener = new FirebaseAuth.AuthStateListener()
            {
                @Override
                public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth)
                {
                    FirebaseUser fbUser = firebaseAuth.getCurrentUser();
                    if (fbUser != null)
                    {
                       // toastMessage("Successfully signed in with " + fbUser.getEmail());
                        Toast.makeText(MainActivity.this, "You are logged in: " + fbUser.getEmail(), Toast.LENGTH_SHORT).show();
                    }
                    else
                    {
                        // Could display not signed in. But might cause toasting issues...
                    }
                }
            };



            loginButton = (Button) findViewById(R.id.loginBtn);
            registerButton = (Button) findViewById(R.id.regBtn);

            loginButton.setOnClickListener(buttonClickListener);
            registerButton.setOnClickListener(buttonClickListener);



    }

    // This is the ButtonClickListener, it listens for multiple buttons. just give the case, the button id from xml.
    private View.OnClickListener buttonClickListener = new View.OnClickListener()
    {
        @Override
        public void onClick(View view)
        {

            height = getWindowManager().getDefaultDisplay().getHeight();
            width = getWindowManager().getDefaultDisplay().getWidth();
            str = "Height: " + height + ", Width: " + width;

            switch(view.getId()) {
                case R.id.loginBtn:
                    //Toast.makeText(MainActivity.this, str, Toast.LENGTH_SHORT).show();
                    //finish();
                    Intent loginIntent = new Intent(MainActivity.this, LoginActivity.class);
                    startActivity(loginIntent);
                    //toastMessage("Height: " +height + "\nWidht: " + width);

                    break;
                case R.id.regBtn:
                    //Toast.makeText(MainActivity.this, str, Toast.LENGTH_SHORT).show();
                    //finish();
                    Intent registerIntent = new Intent(MainActivity.this, CreateUserActivity.class);
                    startActivity(registerIntent);

                    break;
            }
        }
    };

}
