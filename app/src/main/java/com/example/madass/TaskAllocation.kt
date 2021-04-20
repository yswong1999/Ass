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

        val postButton :Button = findViewById(R.id.btnApply)
        postButton.setOnClickListener()
        {

        }
        val NotificationButton :ImageButton = findViewById(R.id.notificationBtn9)
        NotificationButton.setOnClickListener()
        {
            val intent = Intent(this,Notification::class.java)


            startActivity(intent)
        }
        val HomeButton :ImageButton = findViewById(R.id.homeBtn8)
        HomeButton.setOnClickListener()
        {
            val intent = Intent(this,ManagerHome::class.java)


            startActivity(intent)
        }
        val profilePicButton : ImageButton = findViewById(R.id.profileBtn7)
        profilePicButton.setOnClickListener()
        {
            val intent = Intent(this,Profile::class.java)
            startActivity(intent)
        }

        val assignedButton :Button = findViewById(R.id.btnApply)
        assignedButton.setOnClickListener()
        {
            val intent = Intent(this,AssignedTask::class.java)


            startActivity(intent)
        }


    }
}
