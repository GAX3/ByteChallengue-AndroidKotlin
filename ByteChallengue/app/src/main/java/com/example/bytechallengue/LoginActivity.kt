package com.example.bytechallengue

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText;
import android.widget.TextView
import android.widget.Toast
import com.example.bytechallengue.database.UserDao
import com.example.bytechallengue.database.UserDatabase
import com.example.bytechallengue.database.UserEntity
import com.google.android.material.textfield.TextInputEditText

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        Toast.makeText(applicationContext, "LOGIN", Toast.LENGTH_SHORT).show();

        var userId = findViewById<EditText>(R.id.edtUserIdLogin)
        val password = findViewById<EditText>(R.id.edtPasswordLogin)
        val login = findViewById<Button>(R.id.btnLogin)
        val create   = findViewById<TextView>(R.id.create)
        val enter   = findViewById<TextView>(R.id.enter)


        login.setOnClickListener(View.OnClickListener {
            val userIdText = userId.text.toString()
            val passwordText = password.text.toString()

            if (userIdText.isEmpty() || passwordText.isEmpty()) {
                Toast.makeText(applicationContext, "fill fields", Toast.LENGTH_LONG).show()
            } else {
                val userDatabase = UserDatabase.getUserDatabase(
                    applicationContext
                )
                val userDao = userDatabase.userDao()
                Thread {
                    val userEntity = userDao.login(userIdText, passwordText)
                    if (userEntity == null) {
                        runOnUiThread {
                            Toast.makeText(applicationContext,"Invalid Credentials",Toast.LENGTH_SHORT).show()
                        }
                    } else {
                        val name = userEntity.name
                        startActivity(
                            Intent(this@LoginActivity, MainActivity::class.java).putExtra(
                                "name",
                                name
                            )
                        )
                    }
                }.start()
            }
});



        create.setOnClickListener {
            startActivity(
                Intent(
                    this@LoginActivity,
                    RegisterActivity::class.java
                )
            )
        }

        //Test

        enter.setOnClickListener {
            startActivity(
                Intent(
                    this@LoginActivity,
                    MainActivity::class.java
                )
            )
        }


    }


}