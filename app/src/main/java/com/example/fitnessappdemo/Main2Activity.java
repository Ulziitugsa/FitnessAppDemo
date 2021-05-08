package com.example.fitnessappdemo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.auth.AuthUI;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.jar.Attributes;

public class Main2Activity extends AppCompatActivity {

    TextView NameTxt;
    TextView EmailTxt;
    EditText AgeTxt;
    EditText HeightTxt;
    EditText WeightTxt;
    EditText DOBTxt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            // Name, email address, and profile photo Url

            String name = user.getDisplayName();
            String email = user.getEmail();
            NameTxt = (TextView)findViewById(R.id.textViewName);
            EmailTxt = (TextView)findViewById(R.id.textViewEmail);
            AgeTxt = (EditText)findViewById(R.id.ageEditText);
            HeightTxt = (EditText)findViewById(R.id.HeightEditText);
            WeightTxt = (EditText)findViewById(R.id.WeightEditText);
            DOBTxt = (EditText)findViewById(R.id.DateTextDate);
            NameTxt.setText( "Hello " + name);
            EmailTxt.setText(email);



            // Check if user's email is verified
            boolean emailVerified = user.isEmailVerified();

            // The user's ID, unique to the Firebase project. Do NOT use this value to
            // authenticate with your backend server, if you have one. Use
            // FirebaseUser.getIdToken() instead.
            String uid = user.getUid();
        }
    }


    public void onPressLogOut(View view) {
        AuthUI.getInstance()
                .signOut(this)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    public void onComplete(@NonNull Task<Void> task) {
                        Log.i("ssuppp", "Successfully logged out");
                        Intent intent = new Intent(Main2Activity.this, LoginActivity.class);
                        startActivity(intent);
                    }
                });
    }

    public void ContinueClick(View view) {
        Date date1 = null;
        try {
            date1 =new SimpleDateFormat("dd/MM/yyyy").parse(String.valueOf(DOBTxt.getText()));
        } catch (ParseException e) {
            Log.e("DAAAMN", e.getMessage());
        }
        UserClass user = new UserClass(Integer.parseInt(String.valueOf(AgeTxt.getText())), Integer.parseInt(String.valueOf(WeightTxt.getText())), Integer.parseInt(String.valueOf(HeightTxt.getText())), date1);
        user.push();

    }
}