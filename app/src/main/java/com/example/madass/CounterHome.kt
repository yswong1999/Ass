package com.example.madass

import android.content.Intent
import android.os.Bundle
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity

class CounterHome : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_counter_home)

        val NotificationButton : ImageButton = findViewById(R.id.notificationBtn10)
        NotificationButton.setOnClickListener()
        {
            val intent = Intent(this,Notification::class.java)
            startActivity(intent)
        }

        val counterHomeProfile :ImageButton = findViewById(R.id.counterHomeProfile)
        counterHomeProfile.setOnClickListener()
        {
            val intent = Intent(this,Profile::class.java)
            startActivity(intent)
        }
    }
}
