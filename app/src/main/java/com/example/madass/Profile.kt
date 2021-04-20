package com.example.madass

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth

class Profile : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile_activity)

        val NotificationButton : ImageButton = findViewById(R.id.notificationBtn8)
        NotificationButton.setOnClickListener()
        {
            startActivity(Intent(this,Notification::class.java))
        }

        val HomeButton : ImageButton = findViewById(R.id.homeBtn6)
        HomeButton.setOnClickListener()
        {
            startActivity(Intent(this,ManagerHome::class.java))
        }

        val changePasswordButton : Button = findViewById(R.id.btnChangePassword)
        changePasswordButton.setOnClickListener()
        {
            startActivity(Intent(this,ChangePassword::class.java))
        }

        val LogOutButton : Button = findViewById(R.id.btnLogOut)
        LogOutButton.setOnClickListener()
        {
            FirebaseAuth.getInstance().signOut()
            startActivity(Intent(this,Login::class.java))
        }
    }
}
