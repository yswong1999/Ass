package com.example.madass

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity

class TaskAllocation : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_task_allocation)

        val btnTaskAssign :Button = findViewById(R.id.btnTaskAssign)
        btnTaskAssign.setOnClickListener()
        {

        }

//        val NotificationButton :ImageButton = findViewById(R.id.notificationBtn9)
//        NotificationButton.setOnClickListener()
//        {
//            startActivity(Intent(this,Notification::class.java))
//        }
//
//        val HomeButton :ImageButton = findViewById(R.id.homeBtn8)
//        HomeButton.setOnClickListener()
//        {
//            startActivity(Intent(this,ManagerHome::class.java))
//        }

        val profilePicButton : ImageButton = findViewById(R.id.profileBtn7)
        profilePicButton.setOnClickListener()
        {
            startActivity(Intent(this,Profile::class.java))
        }

        val assignedButton :Button = findViewById(R.id.btnApply)
        assignedButton.setOnClickListener()
        {
            startActivity(Intent(this,AssignedTask::class.java))
        }

    }
}
