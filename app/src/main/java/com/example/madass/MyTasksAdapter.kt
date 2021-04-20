package com.example.madass
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import java.text.SimpleDateFormat
import java.util.*


class MyTasksAdapter (private val myTaskList :List<myTaskView>) :RecyclerView.Adapter<MyTasksAdapter.MyViewHolder>() {

    var expandMyTask :Boolean=false
    var sdf = SimpleDateFormat("yyyy.MM.dd HH:mm aa",Locale.getDefault())
    var a = TimeZone.setDefault(TimeZone.getTimeZone("GMT+8"));

    //sdf.setTimeZone(TimeZone.getTimeZone("GMT"))
   // val currentTime = Calendar.getInstance().getTime();

    var currentDateandTime = sdf.format(Date())

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        var myTaskView = LayoutInflater.from(parent.context).inflate(R.layout.my_task_view,parent,false)
        return MyViewHolder(myTaskView)
    }
    override fun getItemCount(): Int {
        return myTaskList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = myTaskList[position]
        holder.myTaskTitleIdTV.setText(myTaskList[position].taskTitle)
        holder.myTaskDateIdTV.setText("by :"+currentDateandTime)
        //holder.myTaskDateIdTV.setText(myTaskList[position].taskDeadline)
        holder.myTaskContentIdTV.setText(myTaskList[position].taskContent)

        holder.myTaskTitleIdTV.setOnClickListener() {
            //  Log.d("a","a")
            if (expandMyTask == false) {
                // Log.d("a","b")
                holder.myTaskExpandableLayoutId.visibility = View.VISIBLE
                expandMyTask = true

            } else {
                // Log.d("a","c")
                holder.myTaskExpandableLayoutId.visibility = View.GONE
                expandMyTask = false
            }
        }

        holder.myTaskDoneBtnId.setOnClickListener() {
            // MainActivity().myRef.child(myTaskList[position].roomNo).child("cleanStatus").setValue("Done")

            if (currentDateandTime < myTaskList[position].taskDeadline) {
                MyTasks().myRef.child(myTaskList[position].taskId).child("taskStatus")
                    .setValue("done")
            } else {
                MyTasks().myRef.child(myTaskList[position].taskId).child("taskStatus")
                    .setValue("late")
            }
            //Log.d("a",myTaskList[position].taskStatus)

        }

        holder.myTaskDoneUndoBtnId.setOnClickListener() {

            MyTasks().myRef.child(myTaskList[position].taskId).child("taskStatus")
                .setValue("pending")
           // Log.d("a",status)
        }



        if(myTaskList[position].taskStatus == "done"|| myTaskList[position].taskStatus == "late"){

            holder.myTaskDoneBtnId.visibility = View.INVISIBLE
            holder.myTaskDoneUndoBtnId.visibility = View.VISIBLE

        }else{
            holder.myTaskDoneBtnId.visibility = View.VISIBLE
            holder.myTaskDoneUndoBtnId.visibility = View.INVISIBLE
        }


    }
    class MyViewHolder(myTask:View):RecyclerView.ViewHolder(myTask) {
        val myTaskTitleIdTV: TextView = myTask.findViewById(R.id.myTaskTitleIdTV)
        val myTaskDateIdTV: TextView = myTask.findViewById(R.id.myTaskDateIdTV)
        val myTaskContentIdTV: TextView = myTask.findViewById(R.id.myTaskContentIdTV)
        val myTaskExpandableLayoutId: ConstraintLayout = myTask.findViewById(R.id.myTaskExpandableLayoutId);
        val myTaskDoneBtnId :Button =myTask.findViewById(R.id.myTaskDoneBtnId)
        val myTaskDoneUndoBtnId :Button =myTask.findViewById(R.id.myTaskDoneUndoBtnId)
    }
}
