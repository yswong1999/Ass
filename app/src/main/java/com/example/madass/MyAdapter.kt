package com.example.madass
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class MyAdapter(private val roomList :List<roomCleaningView>) :RecyclerView.Adapter<MyAdapter.MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val roomStateView = LayoutInflater.from(parent.context).inflate(R.layout.room_status_view,parent,false)
        return MyViewHolder(roomStateView)
    }
    override fun getItemCount(): Int {
        return roomList.size
    }
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem =roomList[position]

        holder.roomIdTV .setText(roomList[position].roomNo)

        holder.acceptBtn .setOnClickListener() {
            //roomList[position].roomCleanStatus.set
            CleanerRoomManagement().myRef.child(roomList[position].roomNo).child("cleanStatus").setValue("Cleaning")
            //holder.roomIdTV.setBackgroundResource(R.color.yellow)
            holder.roomIdTV.setBackgroundResource(R.color.yellow)
            //MainActivity().myRoomNoList.remove(roomList[position])
        }

        holder.cancelBtn .setOnClickListener() {
            //roomList[position].roomCleanStatus.set
            CleanerRoomManagement().myRef.child(roomList[position].roomNo).child("cleanStatus").setValue("Uncleaned")
            //MainActivity().myRoomNoList.remove(roomList[position])
            holder.roomIdTV.setBackgroundResource(R.color.red)
        }

        holder.finishBtn .setOnClickListener() {
            //roomList[position].roomCleanStatus.set
            CleanerRoomManagement().myRef.child(roomList[position].roomNo).child("cleanStatus").setValue("Cleaned")
            //MainActivity().myRoomNoList.remove(roomList[position])

        }

        //if(MainActivity().myRef.child(roomList[position].roomNo).child("cleanStatus").equals("Unclean")){
        if(roomList[position].roomCleanStatus == "Uncleaned"){
            holder.acceptBtn.visibility = View.VISIBLE
            holder.cancelBtn.visibility = View.INVISIBLE
            holder.finishBtn.visibility = View.INVISIBLE
        }
        else{
            holder.acceptBtn.visibility = View.INVISIBLE
            holder.cancelBtn.visibility = View.VISIBLE
            holder.finishBtn.visibility = View.VISIBLE
        }
    }

    class MyViewHolder(roomState:View):RecyclerView.ViewHolder(roomState) {
        val roomIdTV: TextView = roomState.findViewById(R.id.roomIdTV)
        val acceptBtn: Button = roomState.findViewById(R.id.acceptBtn)
        val cancelBtn: Button = roomState.findViewById(R.id.cancelBtn)
        val finishBtn: Button = roomState.findViewById(R.id.finishBtn)

    }
}
