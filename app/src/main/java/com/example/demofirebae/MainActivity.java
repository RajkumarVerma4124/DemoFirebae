package com.example.demofirebae;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    EditText mEdtEmail,mEdtPassword;
    Button mBtnLogin;
    TextView mTxtRegister;
    FirebaseAuth mFireAuth;
    FirebaseUser mFireUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mEdtEmail = findViewById(R.id.edt_email);
        mEdtPassword = findViewById(R.id.edt_password);
        mBtnLogin = findViewById(R.id.btn_Login);
        mTxtRegister = findViewById(R.id.txt_register);
        mFireAuth = FirebaseAuth.getInstance();       // initialization of firebase authentication

        if(mFireAuth.getCurrentUser() != null)
        {
            Toast.makeText(MainActivity.this,"Login success",Toast.LENGTH_LONG).show();
            Intent i = new Intent(MainActivity.this,MainPage.class);
            startActivity(i);
            finish();
        }



        mBtnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(TextUtils.isEmpty(mEdtEmail.getText().toString()))
                {
                    mEdtEmail.setError("Please Enter Email...");
                    return;
                }

                if(TextUtils.isEmpty(mEdtPassword.getText().toString()))
                {
                    mEdtPassword.setError("Please Enter Password...");
                    return;
                }

                String mStrEmail = mEdtEmail.getText().toString();
                String mStrPassword = mEdtPassword.getText().toString();

                mFireAuth.signInWithEmailAndPassword(mStrEmail,mStrPassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful())
                        {
                            Toast.makeText(MainActivity.this,"Login Successfull",Toast.LENGTH_LONG).show();
                            Intent i = new Intent(MainActivity.this,MainPage.class);
                            startActivity(i);
                        }
                        else
                        {
                            Toast.makeText(MainActivity.this,"Login UnSuccessfull",Toast.LENGTH_LONG).show();
                        }
                    }
                });
            }
        });

        mTxtRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(MainActivity.this,RegistrationActivity.class);
                startActivity(i);

            }
        });


    }
}
