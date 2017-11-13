package com.example.jibba_000.recycleforum;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class PostedActivity extends AppCompatActivity {

    ImageButton imagebutton1;
    ImageButton imagebutton2;
    ImageButton imagebutton3;
    String str;
    int height;
    int width;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_posted);

        height = getWindowManager().getDefaultDisplay().getHeight();
        width = getWindowManager().getDefaultDisplay().getWidth();

        imagebutton1 = (ImageButton) findViewById(R.id.btn1);
        imagebutton1.setOnClickListener(buttonClickListener);

        imagebutton2 = (ImageButton) findViewById(R.id.btn2);
        imagebutton2.setOnClickListener(buttonClickListener);

        imagebutton3 = (ImageButton) findViewById(R.id.btn3);
        imagebutton3.setOnClickListener(buttonClickListener);
    }
    private View.OnClickListener buttonClickListener = new View.OnClickListener()
    {
        @Override
        public void onClick(View view)
        {

            height = getWindowManager().getDefaultDisplay().getHeight();
            width = getWindowManager().getDefaultDisplay().getWidth();
            str = "Height: " + height + ", Width: " + width;

            switch(view.getId()) {
                case R.id.btn1:
                    //Toast.makeText(MainActivity.this, str, Toast.LENGTH_SHORT).show();
                    //finish();
                    Intent sofabordIntent = new Intent(PostedActivity.this, ShowSofabordActivity.class);
                    startActivity(sofabordIntent);
                    //toastMessage("Height: " +height + "\nWidht: " + width);

                    break;
                case R.id.btn2:
                    //Toast.makeText(MainActivity.this, str, Toast.LENGTH_SHORT).show();
                    //finish();
                    Intent stovsugerIntent = new Intent(PostedActivity.this, CreateUserActivity.class);
                    startActivity(stovsugerIntent);

                    break;
                case R.id.btn3:
                    //Toast.makeText(MainActivity.this, str, Toast.LENGTH_SHORT).show();
                    //finish();
                    Intent hamsterIntent = new Intent(PostedActivity.this, CreateUserActivity.class);
                    startActivity(hamsterIntent);

                    break;
            }
        }
    };
}
