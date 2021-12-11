package com.example.bytechallengue

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.example.bytechallengue.database.UserDatabase
import com.example.bytechallengue.database.UserEntity

class RegisterActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        Toast.makeText(applicationContext, "Register", Toast.LENGTH_SHORT).show();

        var userId = findViewById<EditText>(R.id.edtUserId)
        val name = findViewById<EditText>(R.id.edtName)
        val password = findViewById<EditText>(R.id.edtPassword)
        val register = findViewById<Button>(R.id.btnRegister)
        val back = findViewById<TextView>(R.id.back)

        back.setOnClickListener {
            startActivity(
                Intent(
                    this@RegisterActivity,
                    LoginActivity::class.java
                )
            )
        }

        register.setOnClickListener(object : View.OnClickListener{
            override fun onClick(v: View?) {
                //Creating User Entity
                var userEntity = UserEntity()

                val userIdSet = userId.text.toString();
                val userNameSet = name.text.toString();
                val userPasswordSet = password.text.toString();

                userEntity.setUserId(userIdSet);
                userEntity.setName(userNameSet);
                userEntity.setPassword(userPasswordSet);
                if (validateInput(userEntity)) {
                    //Do insert operation
                    val userDatabase = UserDatabase.getUserDatabase(applicationContext)
                    val userDao = userDatabase.userDao()
                    Thread { //Register User
                        userDao.registerUser(userEntity)
                        runOnUiThread {
                            Toast.makeText(applicationContext, "User Register", Toast.LENGTH_LONG)
                                .show()
                        }
                        startActivity(
                            Intent(
                                this@RegisterActivity,
                                LoginActivity::class.java
                            )
                        )
                    }.start()
                } else {
                    Toast.makeText(applicationContext, "Fill all field", Toast.LENGTH_LONG).show()
                }

            }
        });
    }
    private fun validateInput(userEntity: UserEntity): Boolean {
        return if (userEntity.getUserId().isEmpty() ||
            userEntity.getPassword().isEmpty() ||
            userEntity.getName().isEmpty()) {
            return false
        } else true
    }
}