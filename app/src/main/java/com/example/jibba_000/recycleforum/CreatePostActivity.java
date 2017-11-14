package com.example.jibba_000.recycleforum;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.ByteArrayOutputStream;
import java.util.UUID;

public class CreatePostActivity extends AppCompatActivity {

    private Button addpictureBtn;
    private Button cpBtn;
    private EditText topic;
    private EditText description;
    private ImageView iv1;
    private ImageView iv2;
    private int height, width;

    //camera code
    private static int camReqCode =1;
    //camera Storage reference
    private StorageReference picStorage;
    //picture1 progressDialog
    private ProgressDialog picDialog;
    private TextView downloadUrl;

    //Firebase
    private DatabaseReference myChildRef;
    private FirebaseStorage mStorage = FirebaseStorage.getInstance();
    private String userID;
    private FirebaseDatabase mFirebaseDatabase;
    private FirebaseAuth firebaseAuth;
    private FirebaseUser fbUser;
    private DatabaseReference myRootRef;
    private FirebaseAuth.AuthStateListener mAuthListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_post);

        height = getWindowManager().getDefaultDisplay().getHeight();
        width = getWindowManager().getDefaultDisplay().getWidth();

        // Firebase declaration
        firebaseAuth = FirebaseAuth.getInstance();
        // Check if Firebase is already logged in
        if (firebaseAuth.getCurrentUser() != null)
        {
            // The Firebase is logged in
        }
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        FirebaseUser user = firebaseAuth.getCurrentUser();
        fbUser = user;
        userID = user.getUid();

        myRootRef = mFirebaseDatabase.getReference();
        myChildRef = mFirebaseDatabase.getReference(userID);

        addpictureBtn = (Button) findViewById(R.id.billedeBtn);
        cpBtn = (Button) findViewById(R.id.createPostBtn);
        cpBtn.setOnClickListener(buttonClickListener);
        topic = (EditText) findViewById(R.id.editTopic1);
        description = (EditText) findViewById(R.id.editDescription1);
        iv1 = (ImageView) findViewById(R.id.billedIV1);
        iv2 = (ImageView) findViewById(R.id.billedIV2);

        //progress dialog uploading image
        picDialog = new ProgressDialog(this);


        //open camera
        addpictureBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent camIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);


                startActivityForResult(camIntent,camReqCode);

            }
        });

        // Fierbase declaration stuff
                firebaseAuth = FirebaseAuth.getInstance();
        // Check if Firebase is already logged in to
        if (firebaseAuth.getCurrentUser() != null)
        {
            // The Firebase is logged in to
        }
        mAuthListener = new FirebaseAuth.AuthStateListener()
        {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth)
            {
                if (firebaseAuth.getCurrentUser() != null)
                {
                    // The Firebase is logged in to
                }
                else
                {
                    // Could display not signed in. But might cause toasting issues...
                }
            }
        };
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Bitmap bitmap = (Bitmap)data.getExtras().get("data");
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG,100,baos);
        byte[] data1 = baos.toByteArray();
        String path = "Photos/"+ UUID.randomUUID()+".png";
        final StorageReference picstorage = mStorage.getReference(path);
        UploadTask uploadTask = picstorage.putBytes(data1);
        iv1.setImageBitmap(bitmap);
        uploadTask.addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                downloadUrl.setText(picstorage.toString());
            }

        });


    }

    private void createTask(){

        //lav metode der tjekker at felter ikke er tomme, undgå crash


        String taskTopic = topic.getText().toString().trim();
        String taskDescription = description.getText().toString().trim();
        String url = downloadUrl.getText().toString().trim();


        Post post = new Post();



        ;

        myChildRef.child("Tasks").child(myChildRef.push().getKey()).setValue(post);

        finish();
        startActivity(new Intent(CreatePostActivity.this, MyPageActivity.class));

        //toastMessage(cName, true);

    }

    private View.OnClickListener buttonClickListener = new View.OnClickListener()
    {
        @Override
        public void onClick(View view)
        {
            switch(view.getId()){
                case R.id.createPostBtn:
                    createTask();
                    break;
            }

        }
    };
}
