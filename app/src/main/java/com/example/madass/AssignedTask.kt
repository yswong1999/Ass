package com.example.madass

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class AssignedTask : AppCompatActivity() {

    var assignedTaskList = ArrayList<assignedTaskView>()
    val database = FirebaseDatabase.getInstance()
    val myRef = database.getReference("Task")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_assigned_task)

        val recycleView: RecyclerView = findViewById(R.id.assignedTaskRecyclerViewId)
        recycleView.adapter = AssignedTaskAdapter(assignedTaskList)
        recycleView.layoutManager = LinearLayoutManager(this)
        recycleView.setHasFixedSize(true)

        var getData = object: ValueEventListener {

            override fun onCancelled(error: DatabaseError) {

            }
            override fun onDataChange(snapshot: DataSnapshot) {
                assignedTaskList.clear()
                for(s in snapshot.children){

                    val assignedTaskTitle:String = s.child("taskTitle").getValue().toString()
                    val assignedTaskContent:String = s.child("taskContent").getValue().toString()
                    val assignedTaskDate:String = s.child("taskDeadline").getValue().toString()
                    val assignedTaskStatus:String  = s.child("taskStatus").getValue().toString()
                    val checked:String = s.child("checked").getValue().toString()
                    val taskId = s.key.toString()

                    addData(assignedTaskTitle, assignedTaskContent,assignedTaskDate,assignedTaskStatus,checked,taskId)
                }
                recycleView.adapter = AssignedTaskAdapter(assignedTaskList)
            }
        }

        myRef.addValueEventListener(getData)

    }
    fun addData(assignedTaskTitle:String,assignedTaskContent:String,assignedTaskDate:String, assignedTaskStatus:String,checked:String,taskId:String){

        val assignedTask = assignedTaskView(assignedTaskTitle, assignedTaskContent,assignedTaskDate,assignedTaskStatus,checked,taskId)
        //if (room.roomCleanStatus=="Cleaned"){
        assignedTaskList.add(assignedTask)
        // }
    }
}