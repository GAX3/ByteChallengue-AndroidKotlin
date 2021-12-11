package com.example.bytechallengue.database;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.bytechallengue.R;
import com.example.bytechallengue.RegisterActivity;

public class test extends AppCompatActivity {

    EditText userId, password;
    Button register;
    protected void onCreate(Bundle savedInstancesState) {
        super.onCreate(savedInstancesState);
        setContentView(R.layout.activity_main);
        userId = findViewById(R.id.edtUserId);
        password = findViewById(R.id.edtPassword);
        register = findViewById(R.id.btnRegister);


        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String userIdText = userId.getText().toString();
                String passwordText =password.getText().toString();

                if(userIdText.isEmpty() || passwordText.isEmpty()){
                    Toast.makeText(getApplicationContext(), "fill fields", Toast.LENGTH_LONG).show();
                }else{
                    UserDatabase userDatabase = UserDatabase.getUserDatabase(getApplicationContext());
                    final UserDao userDao = userDatabase.userDao();
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                                UserEntity userEntity = userDao.login(userIdText, passwordText);
                                if(userEntity == null){
                                    runOnUiThread(new Runnable() {
                                        @Override
                                        public void run() {
                                            Toast.makeText(getApplicationContext(), "Invalid Credentials", Toast.LENGTH_SHORT).show();
                                        }
                                    });
                                }else{
                                    String name = userEntity.name;
                                    startActivity(new Intent(test.this, RegisterActivity.class).putExtra("name", name));
                                }
                        }
                    }).start();
                }
            }
        });

        register.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                UserEntity userEntity = new UserEntity();
                userEntity.setUserId(userId.getText().toString());

                if(validateInput(userEntity)){
                    //Do insert operation
                    UserDatabase  userDatabase = UserDatabase.getUserDatabase(getApplicationContext());
                    final UserDao userDao = userDatabase.userDao();
                    new Thread(new Runnable() {
                        @Override
                        public void run() {

                            //Register User
                            userDao.registerUser(userEntity);
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    Toast.makeText(getApplicationContext(), "User Register", Toast.LENGTH_LONG).show();
                                }
                            });
                        }
                    }).start();
                }else{
                    Toast.makeText(getApplicationContext(), "Fill all field", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    private Boolean validateInput (UserEntity userEntity){
        if(userEntity.getUserId().isEmpty()){
            return false;
        }
        return true;
    }



}
