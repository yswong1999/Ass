package com.example.madass

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class Notification : AppCompatActivity() {
    var myNotificationList = ArrayList<notificationView>()
    val database = FirebaseDatabase.getInstance()
    val myRef = database.getReference("Announcement")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notification)

        val recycleView: RecyclerView = findViewById(R.id.notificationRecyclerViewId)
        recycleView.adapter = MyAdapterNotification(myNotificationList)
        recycleView.layoutManager = LinearLayoutManager(this)
        recycleView.setHasFixedSize(true)

        var getData = object: ValueEventListener {

            override fun onCancelled(error: DatabaseError) {

            }
            override fun onDataChange(snapshot: DataSnapshot) {
                myNotificationList.clear()
                for(s in snapshot.children){

                   val notificationTitle:String = s.child("annTitle").getValue().toString()
                    val notificationContent:String = s.child("annContent").getValue().toString()
                    val notificationDate:String = s.child("announcedTime").getValue().toString()
                    addData(notificationTitle, notificationContent,notificationDate)
                }
                recycleView.adapter = MyAdapterNotification(myNotificationList)

            }
        }

        myRef.addValueEventListener(getData)
    }
    fun addData(notificationTitle:String,notificationContent:String,notificationDate:String){

        val notification = notificationView(notificationTitle, notificationContent,notificationDate)
        //if (room.roomCleanStatus=="Cleaned"){
        myNotificationList.add(notification)
        // }
    }
}