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

        val announcementButton :Button = findViewById(R.id.btnAnnouncement)
        announcementButton.setOnClickListener()
        {
            val intent = Intent(this,Announcement::class.java)
            startActivity(intent)
        }

        val taskAllocationButton :Button = findViewById(R.id.btnTaskAllocation)
        taskAllocationButton.setOnClickListener()
        {
            val intent = Intent(this,TaskAllocation::class.java)
            startActivity(intent)
        }

        val NotificationButton :ImageButton = findViewById(R.id.notificationBtn2)
        NotificationButton.setOnClickListener()
        {
            val intent = Intent(this,Notification::class.java)
            startActivity(intent)
        }

        val profilePicButton :ImageButton = findViewById(R.id.profileBtn)
        profilePicButton.setOnClickListener()
        {
            val intent = Intent(this,Profile::class.java)
            startActivity(intent)
        }




    }
}
