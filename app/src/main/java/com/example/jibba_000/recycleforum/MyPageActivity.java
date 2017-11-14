package com.example.jibba_000.recycleforum;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MyPageActivity extends AppCompatActivity {

    TextView textView;
    ListView listView;
    Button createPostBtn;
    Button seePostBtn;
    ImageButton imageButton;
    private int height, width;
    String str;

    //Firebase
    private FirebaseDatabase firebaseDatabase;
    private FirebaseAuth firebaseAuth;
    private DatabaseReference myUserIDRef;
    private DatabaseReference myTaskRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_page);

        height = getWindowManager().getDefaultDisplay().getHeight();
        width = getWindowManager().getDefaultDisplay().getWidth();

        //textView = (TextView) findViewById(R.id.postView);
        //listView = (ListView) findViewById(R.id.post_listView);
        textView = (TextView) findViewById(R.id.postView);
        imageButton = (ImageButton) findViewById(R.id.iBtn);
        createPostBtn = (Button) findViewById(R.id.createPost);
        seePostBtn = (Button) findViewById(R.id.seePosts);
        seePostBtn.setOnClickListener(buttonClickListener);
        createPostBtn.setOnClickListener(buttonClickListener);
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
                case R.id.seePosts:
                    //Toast.makeText(MainActivity.this, str, Toast.LENGTH_SHORT).show();
                    //finish();
                    Intent seePostIntent = new Intent(MyPageActivity.this, PostedActivity.class);
                    startActivity(seePostIntent);
                    //toastMessage("Height: " +height + "\nWidht: " + width);

                    break;
                case R.id.createPost:
                    //Toast.makeText(MainActivity.this, str, Toast.LENGTH_SHORT).show();
                    //finish();
                    Intent createIntent = new Intent(MyPageActivity.this, CreatePostActivity.class);
                    startActivity(createIntent);

                    break;
            }
        }
    };
}
