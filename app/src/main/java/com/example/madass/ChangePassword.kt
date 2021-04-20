package com.example.madass

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.EmailAuthProvider
import com.google.firebase.auth.FirebaseAuth


class ChangePassword : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_change_password)

        val user = FirebaseAuth.getInstance().getCurrentUser()
        val email = user.getEmail()

        val tfCurrentPassword = findViewById<TextView>(R.id.tfCurrentPassword).text.toString()

        val credential = EmailAuthProvider.getCredential(email, tfCurrentPassword)

        val tfNewPassword = findViewById<TextView>(R.id.tfNewPassword).text.toString()
        val tfConfirmPassword = findViewById<TextView>(R.id.tfConfirmNewPassword).text.toString()
        var newPassword = ""

        if(tfNewPassword.equals(tfConfirmPassword)){
            newPassword = tfConfirmPassword
        }

        user.reauthenticate(credential).addOnCompleteListener(
            OnCompleteListener {
            if(it.isSuccessful()){
                user.updatePassword(newPassword).addOnCompleteListener(
                    OnCompleteListener {
                        if(it.isSuccessful()){
                            Toast.makeText(this, "Password updated", Toast.LENGTH_LONG).show()
                        }
                        else{
                            Toast.makeText(this, "Error! Password not updated", Toast.LENGTH_LONG).show()
                        }
                    }
                )
            }
            })

        val BackButton : Button = findViewById(R.id.homeBtn7)
        BackButton.setOnClickListener()
        {
            val intent = Intent(this,Profile::class.java)
            startActivity(intent)
        }
    }
}
