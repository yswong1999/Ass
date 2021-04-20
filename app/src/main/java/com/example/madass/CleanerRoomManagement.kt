package com.example.madass

import android.content.Intent
import android.os.Bundle
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity

class CleanerRoomManagement : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cleaner_room_management)

        val NotificationButton : ImageButton = findViewById(R.id.notificationBtn6)
        NotificationButton.setOnClickListener()
        {
            val intent = Intent(this,Notification::class.java)


            startActivity(intent)
        }
        val HomeButton : ImageButton = findViewById(R.id.homeBtn3)
        HomeButton.setOnClickListener()
        {
            val intent = Intent(this,CleanerHome::class.java)


            startActivity(intent)
        }
        val profilePicButton : ImageButton = findViewById(R.id.profileBtn7)
        profilePicButton.setOnClickListener()
        {
            val intent = Intent(this,Profile::class.java)
            startActivity(intent)
        }

    }
}
