package com.example.madass

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.FirebaseDatabase
import java.util.*

class Announcement : AppCompatActivity() {
    companion object{
        var annID = 1;
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_annoucement)

        val database = FirebaseDatabase.getInstance()
        val myRef = database.getReference("Announcement")
        val btnAnnPost :Button = findViewById(R.id.btnApply)

        btnAnnPost.setOnClickListener()
        {
            val id:String = "A00" + annID.toString();
            val title:String = findViewById<TextView>(R.id.tfTaskTitle).text.toString()
            val content:String = findViewById<TextView>(R.id.tfTaskDescription).text.toString()
            val time = Calendar.getInstance().getTime().toString();

            myRef.child(id).child("Title").setValue(title)
            myRef.child(id).child("Content").setValue(content)
            myRef.child(id).child("Time").setValue(time)

            annID++;
        }

        val notificationButton :ImageButton = findViewById(R.id.notificationBtn3)
        notificationButton.setOnClickListener()
        {
            val intent = Intent(this,Notification::class.java)
            startActivity(intent)
        }

        val homeButton :ImageButton = findViewById(R.id.homeBtn4)
        homeButton.setOnClickListener()
        {
            val intent = Intent(this,ManagerHome::class.java)
            startActivity(intent)
        }

        val profilePicButton : ImageButton = findViewById(R.id.profileBtn2)
        profilePicButton.setOnClickListener()
        {
            val intent = Intent(this,Profile::class.java)
            startActivity(intent)
        }
    }
}
