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

class TaskAllocation : AppCompatActivity() {

    val database = FirebaseDatabase.getInstance()
    val myTaskRef = database.getReference("Task")
    var currentIndex = "T0"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_task_allocation)

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
                currentIndex = "T" + (currentIndex.substring(1, currentIndex.length).toInt() + 1).toString().padStart(5, '0')
            }
        }

        myTaskRef.addValueEventListener(getData)

        val btnTaskAssign :Button = findViewById(R.id.btnTaskAssign)
        btnTaskAssign.setOnClickListener()
        {
            val id:String = currentIndex
            val target:String = findViewById<TextView>(R.id.tfTaskTarget).text.toString()
            val title:String = findViewById<TextView>(R.id.tfTaskTitle).text.toString()
            val description:String = findViewById<TextView>(R.id.tfTaskDescription).text.toString()
            val dueDate:String = findViewById<TextView>(R.id.tfTaskDueDate).text.toString()

            myTaskRef.child(id).child("target").setValue(target)
            myTaskRef.child(id).child("title").setValue(title)
            myTaskRef.child(id).child("description").setValue(description)
            myTaskRef.child(id).child("dueDate").setValue(dueDate)
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


    }
}
