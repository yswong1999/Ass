package com.example.madass

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity

class ManagerHome : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_manager_home)

        val btnAnnouncement :Button = findViewById(R.id.btnAnnouncement)
        btnAnnouncement.setOnClickListener()
        {
            startActivity(Intent(this,Announcement::class.java))
        }

        val taskAllocationButton :Button = findViewById(R.id.btnTaskAllocation)
        taskAllocationButton.setOnClickListener()
        {
            startActivity(Intent(this,TaskAllocation::class.java))
        }

        val NotificationButton :ImageButton = findViewById(R.id.notificationBtn2)
        NotificationButton.setOnClickListener()
        {
            startActivity(Intent(this,Notification::class.java))
        }

        val profilePicButton :ImageButton = findViewById(R.id.profileBtn)
        profilePicButton.setOnClickListener()
        {
            startActivity(Intent(this,Profile::class.java))
        }

    }
}
