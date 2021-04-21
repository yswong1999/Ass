package com.example.madass

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.button.MaterialButtonToggleGroup
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener


class CounterRoomManagement  : AppCompatActivity(){
    var myRoomNoList = ArrayList<RoomCounterClass>()
    val database = FirebaseDatabase.getInstance()
    val myRoomRef = database.getReference("Room")
    val myRegRef = database.getReference("Reg")

    var check = "In"
    var type = "Single"
    var floor = "FG"
    var search = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.room_management_cleaner)
        setContentView(R.layout.activity_counter_room_management)

        val recycleView: RecyclerView = findViewById(R.id.roomRecyclerView)
        //recycleView.layoutManager = LinearLayoutManager(this)
        val manager = GridLayoutManager(this, 3, GridLayoutManager.VERTICAL, false)
        recycleView.setLayoutManager(manager);
        recycleView.setHasFixedSize(true)
        recycleView.setAdapter(MyRoomCounterAdapter(myRoomNoList));

        var getData = object: ValueEventListener {

            override fun onCancelled(error: DatabaseError) {

            }
            override fun onDataChange(snapshot: DataSnapshot) {
                myRoomNoList.clear()
                for(s in snapshot.children) {
                    val roomNo: String = s.key.toString()
                    val custIC: String = s.child("custIC").getValue().toString()
                    val roomType: String = s.child("type").getValue().toString()
                    addData(roomNo, custIC, roomType)
                }
                //Log.i("TestRun", "AccessFirebase")
                recycleView.adapter = MyRoomCounterAdapter(myRoomNoList)
            }
        }

        //myRef.addListenerForSingleValueEvent(getData)
        myRoomRef.addValueEventListener(getData)

        val checkBtnGrp: MaterialButtonToggleGroup = findViewById(R.id.checkBtnGrp)
        val typeBtnGrp: MaterialButtonToggleGroup = findViewById(R.id.typeBtnGrp)
        val floorBtnGrp: MaterialButtonToggleGroup = findViewById(R.id.floorBtnGrp)

        checkBtnGrp.addOnButtonCheckedListener { _, checkedId, _ ->
            when (checkedId) {
                R.id.checkInBtn -> check = "In"
                R.id.checkOutBtn -> check = "Out"
            }
            myRoomRef.addListenerForSingleValueEvent(getData)
        }

        typeBtnGrp.addOnButtonCheckedListener { _, checkedId, _ ->
            when (checkedId) {
                R.id.singleBtn -> type = "Single"
                R.id.doubleBtn -> type = "Double"
            }
            myRoomRef.addListenerForSingleValueEvent(getData)
        }

        floorBtnGrp.addOnButtonCheckedListener { _, checkedId, _ ->
            when (checkedId) {
                R.id.f1Btn -> floor = "FG"
                R.id.f2Btn -> floor = "F1"
                R.id.f3Btn -> floor = "F2"
            }
            //Log.i("TestRun", "FloorBtn")
            myRoomRef.addListenerForSingleValueEvent(getData)
        }

        val searchTf:EditText = findViewById(R.id.rcSearchTf)
        val searchBtn:Button = findViewById(R.id.rcSearchBtn)
        searchBtn .setOnClickListener() {
            search = searchTf.getText().toString()
            myRoomRef.addListenerForSingleValueEvent(getData)
        }

        if (savedInstanceState != null) {
            Log.i("TestRun", "counteractivity: " + savedInstanceState.getString("search", ""))
            check = savedInstanceState.getString("check", "")
            type = savedInstanceState.getString("type", "")
            floor = savedInstanceState.getString("floor", "")
            search = savedInstanceState.getString("search", "")
            searchTf.setText(search)

            when (check) {
                "In" -> checkBtnGrp.check(R.id.checkInBtn)
                "Out" -> checkBtnGrp.check(R.id.checkOutBtn)
            }

            when (type) {
                "Single" -> typeBtnGrp.check(R.id.singleBtn)
                "Double" -> typeBtnGrp.check(R.id.doubleBtn)
            }

            when (floor) {
                "FG" -> floorBtnGrp.check(R.id.f1Btn)
                "F1" -> floorBtnGrp.check(R.id.f2Btn)
                "F2" -> floorBtnGrp.check(R.id.f3Btn)
            }

        }

    }

    fun addData(roomNo:String,custIC:String, roomType:String){

        val room = RoomCounterClass(roomNo,custIC, roomType)
        var matchingCheck = false
        var matchingType = false
        var matchingFloor = false

        when (check) {
            "In" -> {
                if (room.custIC == "0"){
                    matchingCheck = true
                }
            }
            "Out" -> {
                if (room.custIC != "0"){
                    matchingCheck = true
                }
            }
        }

        when (type) {
            "Single" -> {
                if (room.type == "Single"){
                    matchingType = true
                }
            }
            "Double" -> {
                if (room.type == "Double"){
                    matchingType = true
                }
            }
        }

        when (floor) {
            "FG" -> {
                if (room.roomNo.compareTo("R200") < 0){
                    matchingFloor = true
                }
            }
            "F1" -> {
                if (room.roomNo.compareTo("R200") > 0 && room.roomNo.compareTo("R300") < 0){
                    matchingFloor = true
                }
            }
            "F2" -> {
                if (room.roomNo.compareTo("R300") > 0){
                    matchingFloor = true
                }
            }
        }

        if (matchingCheck && matchingType && matchingFloor && room.roomNo.contains(search, true)){
            //Log.i("TestRun", "RoomAdded")
            myRoomNoList.add(room)
        }

    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)

        outState.putString("check", check)
        outState.putString("type", type)
        outState.putString("floor", floor)
        outState.putString("search", search)
        Log.i("TestRun", "saved")
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)

        Log.i("TestRun", "loaded")
    }

}