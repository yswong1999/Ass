package com.example.madass

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.util.Patterns
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class Login : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

    companion object{
        var staffName = ""
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        auth = FirebaseAuth.getInstance()

        val tfLoginEmail = findViewById<TextView>(R.id.tfLoginEmail)
        val tfLoginPassword = findViewById<TextView>(R.id.tfLoginPassword)
        val btnLogin: Button = findViewById(R.id.btnLogin)

        val database = FirebaseDatabase.getInstance()
        val myRef = database.getReference("Staff")
        var type = ""

        var getData = object: ValueEventListener {
            override fun onCancelled(error: DatabaseError) {

            }

            override fun onDataChange(snapshot: DataSnapshot) {

                type = snapshot.child(auth.getCurrentUser().getUid()).child("type").getValue().toString()
                staffName = snapshot.child(auth.getCurrentUser().getUid()).child("name").getValue().toString()
            }

        }

        btnLogin.setOnClickListener(){

            Log.d("mingyea",type+"dfasddfdfas")

            val email:String = findViewById<TextView>(R.id.tfLoginEmail).text.toString()
            val password:String = findViewById<TextView>(R.id.tfLoginPassword).text.toString().trim()

            if(email.isEmpty()){
                tfLoginEmail.setError("Email is required!")
                tfLoginEmail.requestFocus();
            }

            if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
                tfLoginEmail.setError("Please enter a valid email")
                tfLoginEmail.requestFocus();
            }

            if(password.isEmpty()){
                tfLoginPassword.setError("Password is required!")
                tfLoginPassword.requestFocus();
            }

            if(password.length < 6){
                tfLoginPassword.setError("Min password length is 6 characters!")
                tfLoginPassword.requestFocus();
            }

            if(!email.isEmpty() && !password.isEmpty()){
                auth.signInWithEmailAndPassword(email, password).addOnCompleteListener(
                    OnCompleteListener {

                        myRef.addValueEventListener(getData)

                        if(it.isSuccessful()){

                            if(type.equals("Manager"))
                                startActivity(Intent(this,ManagerHome::class.java))

                            if(type.equals("Cleaner"))
                                startActivity(Intent(this,CleanerHome::class.java))

                            if(type.equals("Counter"))
                                startActivity(Intent(this,CounterHome::class.java))
                        }
                        else{
                            Toast.makeText(this, "Failed to login! Please check your credentials", Toast.LENGTH_LONG).show()
                        }
                    })
            }
        }
    }


}
