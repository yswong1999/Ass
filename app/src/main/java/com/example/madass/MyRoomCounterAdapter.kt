package com.example.madass

import android.content.DialogInterface
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import java.util.*

class MyRoomCounterAdapter(private val roomList :List<RoomCounterClass>) :RecyclerView.Adapter<MyRoomCounterAdapter.MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val roomStateView = LayoutInflater.from(parent.context).inflate(R.layout.counter_item,parent,false)
        return MyViewHolder(roomStateView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.roomBtn.setText(roomList[position].roomNo)

        var reg = ""
        var getData = object: ValueEventListener {
            override fun onCancelled(error: DatabaseError) {
            }
            override fun onDataChange(snapshot: DataSnapshot) {
                for(s in snapshot.children) {
                    if(s.child("ic").getValue() == roomList[position].custIC && s.child("room").getValue() == roomList[position].roomNo && s.child("outDate").getValue() == "0"){
                        reg = s.key.toString()
                    }
                }
            }
        }

        CounterRoomManagement().myRegRef.addListenerForSingleValueEvent(getData)

        if (roomList[position].custIC == "0"){
            holder.roomBtn .setOnClickListener() {
                //Log.i("TestRun", "Wut")
                val intent = Intent(holder.roomBtn.getContext(),RoomRegister::class.java)
                intent.putExtra("roomNo", roomList[position].roomNo)
                holder.roomBtn.getContext().startActivity(intent)
            }
        }else{
            holder.roomBtn .setOnClickListener() {

                val builderSuccess: AlertDialog.Builder = this.let {
                    AlertDialog.Builder(holder.roomBtn.getContext())
                }
                builderSuccess.apply {
                    setMessage(
                        "Check-out Successful"
                    )
                    //setTitle(
                    //    "Registration Successful"
                    //)
                    setPositiveButton("Okay",
                        DialogInterface.OnClickListener { dialog, id ->
                        })
                }

                val builderConfirm: AlertDialog.Builder = this.let {
                    AlertDialog.Builder(holder.roomBtn.getContext())
                }
                builderConfirm.apply {
                    setMessage(
                        "Customer IC: " + roomList[position].custIC + "\n" +
                                "Check-out from room " + roomList[position].roomNo + "?"
                    )
                    setTitle(
                        "Confirm Check-out"
                    )
                    setPositiveButton("Yes",
                        DialogInterface.OnClickListener { dialog, id ->
                            CounterRoomManagement().myRoomRef.child(roomList[position].roomNo).child("custIC").setValue("0")



                            val timeyr = Calendar.getInstance().get(Calendar.YEAR).toString()
                            val timemn = (Calendar.getInstance().get(Calendar.MONTH)+1).toString()
                            val timedy = Calendar.getInstance().get(Calendar.DAY_OF_MONTH).toString()
                            val time = timedy + '/' + timemn + '/' + timeyr
                            Log.i("TestRun", "reg:"+reg)
                            CounterRoomManagement().myRegRef.child(reg).child("outDate").setValue(time)

                            val success: AlertDialog = builderSuccess.create()
                            success.show();
                        })
                    setNegativeButton("No",
                        DialogInterface.OnClickListener { dialog, id ->
                            // User cancelled the dialog
                        })
                }
                val confirm: AlertDialog = builderConfirm.create()
                confirm.show();
            }
        }

    }

    override fun getItemCount(): Int {
        return roomList.size
    }

    class MyViewHolder(roomState:View):RecyclerView.ViewHolder(roomState) {
        val roomBtn: Button = roomState.findViewById(R.id.roomBtn)
    }
}