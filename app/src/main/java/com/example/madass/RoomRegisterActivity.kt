package com.example.madass

import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import java.util.*


class RoomRegisterActivity  : AppCompatActivity(){

    val database = FirebaseDatabase.getInstance()
    val myRegRef = database.getReference("Reg")
    val myRoomRef = database.getReference("Room")
    var currentIndex = "R0"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_room_register)

        val extras = intent.extras

        var getData = object: ValueEventListener {

            override fun onCancelled(error: DatabaseError) {

            }
            override fun onDataChange(snapshot: DataSnapshot) {
                for(s in snapshot.children) {
                    val index: String = s.key.toString()
                    if (currentIndex.substring(1, currentIndex.length).toInt() < index.substring(1, index.length).toInt()){
                        currentIndex = index
                    }
                }
                currentIndex = "R" + (currentIndex.substring(1, currentIndex.length).toInt() + 1).toString().padStart(5,'0')
            }
        }



        //myRef.addListenerForSingleValueEvent(getData)
        myRegRef.addValueEventListener(getData)

        val regTitle:TextView = findViewById(R.id.regTitleTv)
        if (extras != null){
            regTitle.setText(extras.getString("roomNo"))
        }

        val backBtn: Button = findViewById(R.id.backBtn)
        backBtn.setOnClickListener{
            //Log.i("TestRun", "regactivity: " + savedInstanceState!!.getString("search", ""))
            val intent = Intent(this,RoomCounterActivity::class.java)
            startActivity(intent)
        }

        val inDate: TextView = findViewById(R.id.inDateTv)
        val name: TextInputEditText = findViewById(R.id.nameET)
        val ic: TextInputEditText = findViewById(R.id.icET)
        val contact: TextInputEditText = findViewById(R.id.contactET)

        val timeyr = Calendar.getInstance().get(Calendar.YEAR).toString()
        val timemn = (Calendar.getInstance().get(Calendar.MONTH)+1).toString()
        val timedy = Calendar.getInstance().get(Calendar.DAY_OF_MONTH).toString()
        val time = timedy + '/' + timemn + '/' + timeyr
        inDate.setText("Check-in Date: " + time)


        val applyBtn :Button = findViewById(R.id.applyBtn)
        applyBtn.setOnClickListener() {
            //myCustRef.child(currentIndex).child("inDate").setValue(inDate.getText().toString())
            //myCustRef.child(currentIndex).child("outDate").setValue(outDate.getText().toString())
            //myCustRef.child(currentIndex).child("name").setValue(name.getText().toString())
            //myCustRef.child(currentIndex).child("ic").setValue(ic.getText().toString())
            //myCustRef.child(currentIndex).child("contact").setValue(contact.getText().toString())
            //myCustRef.child(currentIndex).child("room").setValue(extras!!.getString("roomNo"))
            //myRoomRef.child(extras!!.getString("roomNo").toString()).child("custIC").setValue(ic.getText().toString())

            val intent = Intent(this,RoomCounterActivity::class.java)

            val builderSuccess: AlertDialog.Builder = this.let {
                AlertDialog.Builder(it)
            }
            builderSuccess.apply {
                setMessage(
                    "Registration Successful"
                )
                //setTitle(
                //    "Registration Successful"
                //)
                setPositiveButton("Okay",
                    DialogInterface.OnClickListener { dialog, id ->
                        startActivity(intent)
                    })
            }

            val builderConfirm: AlertDialog.Builder = this.let {
                AlertDialog.Builder(it)
            }
            builderConfirm.apply {
                setMessage(
                    "Check-in Date: " + time + "\n" +
                            "Name: " + name.getText().toString() + "\n" +
                            "IC/Passport No: " + ic.getText().toString() + "\n" +
                            "Contact: " + contact.getText().toString() + "\n" +
                            "Room: " + extras!!.getString("roomNo") + "\n"
                )
                setTitle(
                    "Confirm Registration"
                )
                setPositiveButton("Yes",
                    DialogInterface.OnClickListener { dialog, id ->
                        myRegRef.child(currentIndex).child("inDate").setValue(time)
                        myRegRef.child(currentIndex).child("outDate").setValue("0")
                        myRegRef.child(currentIndex).child("name").setValue(name.getText().toString())
                        myRegRef.child(currentIndex).child("ic").setValue(ic.getText().toString())
                        myRegRef.child(currentIndex).child("contact").setValue(contact.getText().toString())
                        myRegRef.child(currentIndex).child("room").setValue(extras!!.getString("roomNo"))
                        myRoomRef.child(extras.getString("roomNo").toString()).child("custIC").setValue(ic.getText().toString())

                        val success: AlertDialog = builderSuccess.create()
                        success.show();
                    })
                setNegativeButton("No",
                    DialogInterface.OnClickListener { dialog, id ->
                        // User cancelled the dialog
                    })
            }

            if(!(name.getText().toString().matches("^[a-zA-Z ]+$".toRegex())))
            {
                Toast.makeText(this, "Name is empty or contains special characters", Toast.LENGTH_LONG).show();
            }
            else if(!(ic.getText().toString().matches("^[a-zA-Z0-9 ]+$".toRegex())))
            {
            Toast.makeText(this, "IC/Passport No is empty or contains special characters", Toast.LENGTH_LONG).show();
            }
            else if(!(contact.getText().toString().matches("^[0-9 ]+$".toRegex())))
            {
                Toast.makeText(this, "Contact is empty or contains alphabets", Toast.LENGTH_LONG).show();
            }
            else{
                val confirm: AlertDialog = builderConfirm.create()
                confirm.show()
            }
        }

    }
}