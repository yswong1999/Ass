package com.example.madass

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity

class CleanerHome : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cleaner_home)

        val NotificationButton : ImageButton = findViewById(R.id.notificationBtn7)
        NotificationButton.setOnClickListener()
        {
            startActivity(Intent(this,Notification::class.java))
        }

        val cleanerHomeProfile :ImageButton = findViewById(R.id.cleanerHomeProfile)
        cleanerHomeProfile.setOnClickListener()
        {
            startActivity(Intent(this,Profile::class.java))
        }

        val btnCleanerHome : ImageButton = findViewById(R.id.btnCleanerHome)
        btnCleanerHome.setOnClickListener()
        {
            startActivity(Intent(this,Profile::class.java))
        }

        val btnMyTasks : Button = findViewById(R.id.btnMyTasks)
        btnMyTasks.setOnClickListener()
        {
            startActivity(Intent(this,MyTasks::class.java))
        }

        val btnCleanerRoomManagement : Button = findViewById(R.id.btnCleanerRoomManagement)
        btnCleanerRoomManagement.setOnClickListener()
        {
            startActivity(Intent(this,CleanerRoomManagement::class.java))
        }
    }
}
