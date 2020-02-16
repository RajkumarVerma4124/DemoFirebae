package com.example.demofirebae;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainPage extends AppCompatActivity {

    Button mbtns_logout;
    TextView mtxtuser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page);


        mbtns_logout = findViewById(R.id.btn_logout);
        mtxtuser = findViewById(R.id.txt_user);

        FirebaseUser mUser = FirebaseAuth.getInstance().getCurrentUser();
        mtxtuser.setText(mUser.getEmail());

        mbtns_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                Intent i = new Intent(MainPage.this,MainActivity.class);
                startActivity(i);
            }
        });
    }
}
