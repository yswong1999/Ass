package com.example.madass

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class MyTasks : AppCompatActivity() {

    var myTaskList = ArrayList<myTaskView>()
    val database = FirebaseDatabase.getInstance()
    val myRef = database.getReference("Task")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_tasks)

        val recycleView: RecyclerView = findViewById(R.id.myTaskRecyclerViewId)
        recycleView.adapter = MyTasksAdapter(myTaskList)
        recycleView.layoutManager = LinearLayoutManager(this)
        recycleView.setHasFixedSize(true)

        var getData = object: ValueEventListener {

            override fun onCancelled(error: DatabaseError) {

            }
            override fun onDataChange(snapshot: DataSnapshot) {
                myTaskList.clear()
                for(s in snapshot.children){

                    val myTaskTitle:String = s.child("title").getValue().toString()
                    val myTaskContent:String = s.child("description").getValue().toString()
                    val myTaskDate:String = s.child("dueDate").getValue().toString()
                    val taskId:String = s.key.toString()
                    val taskStatus:String  = s.child("taskStatus").getValue().toString()

                    addData(myTaskTitle, myTaskContent,myTaskDate,taskId,taskStatus)
                }
                recycleView.adapter = MyTasksAdapter(myTaskList)
            }
        }

        myRef.addValueEventListener(getData)

    }
    fun addData(myTaskTitle:String,myTaskContent:String,myTaskDate:String,taskId:String, taskStatus:String){

        val myTask = myTaskView(myTaskTitle, myTaskContent,myTaskDate,taskId,taskStatus)
        //if (room.roomCleanStatus=="Cleaned"){
        myTaskList.add(myTask)
        // }
    }
}