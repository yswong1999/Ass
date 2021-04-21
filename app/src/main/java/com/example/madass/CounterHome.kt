package com.example.madass

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity

class CounterHome : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_counter_home)

        val NotificationButton : ImageButton = findViewById(R.id.notificationBtn10)
        NotificationButton.setOnClickListener()
        {
            startActivity(Intent(this,Notification::class.java))
        }

        val counterHomeProfile :ImageButton = findViewById(R.id.btnCounterHomeProfile)
        counterHomeProfile.setOnClickListener()
        {
            startActivity(Intent(this,Profile::class.java))
        }

        val btnCounterRoomManagement : Button = findViewById(R.id.btnCounterRoomManagement)
        btnCounterRoomManagement.setOnClickListener()
        {
            startActivity(Intent(this,CounterRoomManagement::class.java))
        }

//        val btnCustomerList : Button = findViewById(R.id.btnCustomerList)
//        btnCustomerList.setOnClickListener()
//        {
//           startActivity(Intent(this,CustomerList::class.java))
//        }

        val btnCounterMyTasks : Button = findViewById(R.id.btnCounterMyTasks)
        btnCounterMyTasks.setOnClickListener()
        {
            startActivity(Intent(this,MyTasks::class.java))
        }
    }
}
