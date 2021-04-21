package com.example.madass

import android.content.Intent
import android.os.Bundle
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class CleanerRoomManagement : AppCompatActivity() {
    var myRoomNoList = ArrayList<roomCleaningView>()
    val database = FirebaseDatabase.getInstance()
    val myRef = database.getReference("Room")

    companion object{
        var currentlyCleaning = false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cleaner_room_management)

        val recycleView: RecyclerView = findViewById(R.id.recyclerView2)
        recycleView.adapter = MyAdapter(myRoomNoList)
        recycleView.layoutManager = LinearLayoutManager(this)
        recycleView.setHasFixedSize(true)

        val NotificationButton : ImageButton = findViewById(R.id.notificationBtn)
        NotificationButton.setOnClickListener()
        {
            startActivity(Intent(this,Notification::class.java))
        }

        val HomeButton : ImageButton = findViewById(R.id.homeBtn)
        HomeButton.setOnClickListener()
        {
            startActivity(Intent(this,CleanerHome::class.java))
        }

        //MyAdapterCleaner = RecyclerAdapter(posts, applicationContext)
        var getData = object: ValueEventListener {

            override fun onCancelled(error: DatabaseError) {

            }
            override fun onDataChange(snapshot: DataSnapshot) {
                myRoomNoList.clear()
                currentlyCleaning = false;
                for(s in snapshot.children){
                    val roomNo:String = s.key.toString()
                    val roomCleanStatus:String = s.child("cleanStatus").getValue().toString()
                    addData(roomNo, roomCleanStatus)
                    //deleteData(roomNo,roomCleanStatus)
                }
                recycleView.adapter = MyAdapter(myRoomNoList)

            }
        }

        //myRef.addListenerForSingleValueEvent(getData)
        myRef.addValueEventListener(getData)
    }

    fun addData(roomNo:String,roomCleanStatus:String) {

        val room = roomCleaningView(roomNo, roomCleanStatus)

        if (room.roomCleanStatus == "Uncleaned") {
            myRoomNoList.add(room)
        } else if (room.roomCleanStatus == Login.staffName) {
            myRoomNoList.add(room)
            currentlyCleaning = true
        }
    }
}