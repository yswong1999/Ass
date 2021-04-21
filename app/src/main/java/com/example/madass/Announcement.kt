package com.example.madass

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import java.text.SimpleDateFormat
import java.util.*

class Announcement : AppCompatActivity() {
//    companion object{
//        var annID = 1;
//    }

    val database = FirebaseDatabase.getInstance()
    val myAnnouncementRef = database.getReference("Announcement")
    var currentIndex = "A0"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_annoucement)

        val btnAnnPost :Button = findViewById(R.id.btnApply)

        var getData = object: ValueEventListener {

            override fun onCancelled(error: DatabaseError) {

            }

            override fun onDataChange(snapshot: DataSnapshot) {
                for (s in snapshot.children) {
                    val index: String = s.key.toString()
                    if (currentIndex.substring(1, currentIndex.length).toInt() < index.substring(1, index.length).toInt()) {
                        currentIndex = index
                    }
                }
                currentIndex = "A" + (currentIndex.substring(1, currentIndex.length).toInt() + 1).toString().padStart(5, '0')
            }
        }

        myAnnouncementRef.addValueEventListener(getData)

        btnAnnPost.setOnClickListener()
        {
            val id:String = currentIndex;
            val title:String = findViewById<TextView>(R.id.tfTaskTitle).text.toString()
            val content:String = findViewById<TextView>(R.id.tfTaskDescription).text.toString()

            var time = SimpleDateFormat("yyyy.MM.dd HH:mm aa", Locale.getDefault())

            TimeZone.setDefault(TimeZone.getTimeZone("GMT+8"));

            var currentDateandTime = time.format(Date())

            myAnnouncementRef.child(id).child("Title").setValue(title)
            myAnnouncementRef.child(id).child("Content").setValue(content)
            myAnnouncementRef.child(id).child("Time").setValue(time)
        }

        val notificationButton :ImageButton = findViewById(R.id.notificationBtn3)
        notificationButton.setOnClickListener()
        {
            startActivity(Intent(this,Notification::class.java))
        }

        val homeButton :ImageButton = findViewById(R.id.homeBtn4)
        homeButton.setOnClickListener()
        {
            startActivity(Intent(this,ManagerHome::class.java))
        }

        val profilePicButton : ImageButton = findViewById(R.id.profileBtn2)
        profilePicButton.setOnClickListener()
        {
            startActivity(Intent(this,Profile::class.java))
        }

    }
}
