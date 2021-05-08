package com.example.fitnessappdemo;

import android.net.Uri;

import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class UserClass {


    final FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference ref = database.getReference("server/saving-data/fireblog");
    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

    public String uid = user.getUid();
    public String name = user.getDisplayName();
    public String email = user.getEmail();;
    public Date date_of_birth;
    public int age;
    public int weight;
    public int height;


    public UserClass(int age, int weight, int height, Date date_of_birth) {
        this.date_of_birth = date_of_birth;
        this.age = age;
        this.weight = weight;
        this.height = height;
    }

    public UserClass() {
        this.date_of_birth = null;
        this.age = 0;
        this.weight = 0;
        this.height = 0;
    }


    public void push()
    {
        DatabaseReference usersRef = ref.child("userDetails");

        Map<String, UserClass> users = new HashMap<>();
        UserClass temp = new UserClass(age, weight, height, date_of_birth);
        users.put(uid, temp);
        usersRef.setValue(users);

    }
}
