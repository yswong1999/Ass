package com.example.madass

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener


class MainActivity : AppCompatActivity() {

    var myRoomNoList = ArrayList<roomCleaningView>()
    val database = FirebaseDatabase.getInstance()
    val myRef = database.getReference("Room")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.room_management_cleaner)

        val recycleView: RecyclerView = findViewById(R.id.recyclerView2)
        recycleView.adapter = MyAdapter(myRoomNoList)
        recycleView.layoutManager = LinearLayoutManager(this)
        recycleView.setHasFixedSize(true)

        var adapter = MyAdapter(myRoomNoList)
        recycleView.setAdapter(adapter)

        //MyAdapterCleaner = RecyclerAdapter(posts, applicationContext)



        var getData = object: ValueEventListener {

            override fun onCancelled(error: DatabaseError) {


            }
            override fun onDataChange(snapshot: DataSnapshot) {
                myRoomNoList.clear()
                for(s in snapshot.children){
                    val roomNo:String = s.key.toString()
                    val roomCleanStatus:String = s.child("cleanStatus").getValue().toString()
                    addData(roomNo, roomCleanStatus)
                    //deleteData(roomNo,roomCleanStatus)
                }
                recycleView.adapter = MyAdapter(myRoomNoList)

            }
        }

        myRef.addListenerForSingleValueEvent(getData)
        //myRef.addValueEventListener(getData)

    }
    fun addData(roomNo:String,roomCleanStatus:String){

        val room = roomCleaningView(roomNo,roomCleanStatus)

        //if (room.roomCleanStatus=="Cleaned"){
        myRoomNoList.add(room)
        // }
    }
}
