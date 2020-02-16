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

public class RegistrationActivity extends AppCompatActivity {

    EditText mEdtEmailRegister,mEdtPasswordRegister,mEdtPasswordconfirmregister;
    Button mBtnResgister;
    TextView mTxtLogin;
    FirebaseAuth mFireAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        mEdtEmailRegister = findViewById(R.id.edt_email_register);
        mEdtPasswordRegister = findViewById(R.id.edt_password_register);
        mBtnResgister = findViewById(R.id.btn_register);
        mTxtLogin = findViewById(R.id.txt_login);
        mEdtPasswordconfirmregister = findViewById(R.id.edt_password_register1);
        mFireAuth = FirebaseAuth.getInstance();

        mBtnResgister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(TextUtils.isEmpty(mEdtEmailRegister.getText().toString()))
                {
                    mEdtEmailRegister.setError("Please Enter Email...");
                    return;
                }

                if(TextUtils.isEmpty(mEdtPasswordRegister.getText().toString()))
                {
                    mEdtPasswordRegister.setError("Please Enter Password...");
                    return;
                }

                if(TextUtils.isEmpty(mEdtPasswordconfirmregister.getText().toString()))
                {
                    mEdtPasswordconfirmregister.setError("Please Enter To Password to Confirm..");
                    return;
                }

                if(mEdtPasswordRegister.getText().toString() != mEdtPasswordconfirmregister.getText().toString())
                {
                    Toast.makeText(RegistrationActivity.this,"Password Matched",Toast.LENGTH_LONG).show();
                }

                String mStrEmailRegister = mEdtEmailRegister.getText().toString();
                String mStrPasswordRegister = mEdtPasswordRegister.getText().toString();

                mFireAuth.createUserWithEmailAndPassword(mStrEmailRegister,mStrPasswordRegister).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful())
                        {
                            finish(); // it will take directly to main page
                            Toast.makeText(RegistrationActivity.this,"Registration Successfull",Toast.LENGTH_LONG).show();
                        }
                        else
                        {
                            Toast.makeText(RegistrationActivity.this,"Registration UnSuccessfull",Toast.LENGTH_LONG).show();
                        }
                    }
                });
            }
        });

        mTxtLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(RegistrationActivity.this,MainActivity.class);
                startActivity(i);

            }
        });

    }
}
