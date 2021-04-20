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
            val intent = Intent(this,Notification::class.java)
            startActivity(intent)
        }

        val cleanerHomeProfile :ImageButton = findViewById(R.id.cleanerHomeProfile)
        cleanerHomeProfile.setOnClickListener()
        {
            val intent = Intent(this,Profile::class.java)
            startActivity(intent)
        }

        val btnCleanerHome : ImageButton = findViewById(R.id.btnCleanerHome)
        btnCleanerHome.setOnClickListener()
        {
            val intent = Intent(this,Profile::class.java)
            startActivity(intent)
        }

        val btnMyTasks : Button = findViewById(R.id.btnMyTasks)
        btnMyTasks.setOnClickListener()
        {
            val intent = Intent(this,MyTasks::class.java)
            startActivity(intent)
        }

        val btnCleanerRoomManagement : Button = findViewById(R.id.btnCleanerRoomManagement)
        btnCleanerRoomManagement.setOnClickListener()
        {
            val intent = Intent(this,CleanerRoomManagement::class.java)
            startActivity(intent)
        }
    }
}
