package com.example.madass
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView

class AssignedTaskAdapter (private val assignedTaskList :List<assignedTaskView>) :RecyclerView.Adapter<AssignedTaskAdapter.MyViewHolder>() {

    var expandAssignTask = false

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        var assignedTaskView = LayoutInflater.from(parent.context).inflate(R.layout.assigned_task_view,parent,false)
        return MyViewHolder(assignedTaskView)
    }
    override fun getItemCount(): Int {
        return assignedTaskList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.assignedTaskTitleIdTV.setText(assignedTaskList[position].assignedTaskTitle)
        holder.assignedTaskDateIdTV.setText("by :" + assignedTaskList[position].assignedTaskDeadline)
        holder.assignedTaskContentIdTV.setText(assignedTaskList[position].assignedTaskContent)
        holder.assignedTaskStatusIdTV.setText(assignedTaskList[position].assignedTaskStatus)

        holder.assignedTaskTitleIdTV.setOnClickListener() {
            //  Log.d("a","a")
            if (expandAssignTask == false) {
                // Log.d("a","b")
                holder.assignedTaskExpandableLayoutId.visibility = View.VISIBLE
                expandAssignTask = true

            } else {
                // Log.d("a","c")
                holder.assignedTaskExpandableLayoutId.visibility = View.GONE
                expandAssignTask = false
            }
        }

        holder.assignedTaskCompleteBtnId.setOnClickListener() {
            if(assignedTaskList[position].checked == "false" )
            {
                AssignedTask().myRef.child(assignedTaskList[position].taskId).child("checked").setValue("true")


            }else{

                AssignedTask().myRef.child(assignedTaskList[position].taskId).child("checked").setValue("false")

            }
        }

        if(assignedTaskList[position].checked == "true" )
        {
            //Log.d("a","a")
            holder.assignedTaskCompleteBtnId.setBackgroundResource(R.color.lightgreen)
        }else{
            //Log.d("a","a")
            holder.assignedTaskCompleteBtnId.setBackgroundResource(R.color.brown)//brown 4E2A04
        }



        //holder.assignedTaskStatusIdTV.setBackgroundResource(R.color.red)

        if(assignedTaskList[position].assignedTaskStatus == "pending"){

            holder.assignedTaskStatusIdTV.setBackgroundResource(R.color.blue)

        }else if(assignedTaskList[position].assignedTaskStatus == "done"){
            holder.assignedTaskStatusIdTV.setBackgroundResource(R.color.lightgreen)

        }else if(assignedTaskList[position].assignedTaskStatus == "late"){

            holder.assignedTaskStatusIdTV.setBackgroundResource(R.color.red)
        }


    }
    class MyViewHolder(assignedTask:View):RecyclerView.ViewHolder(assignedTask) {

        val assignedTaskStatusIdTV: TextView = assignedTask.findViewById(R.id.assignedTaskStatusIdTV)
        val assignedTaskTitleIdTV: TextView = assignedTask.findViewById(R.id.assignedTaskTitleIdTV)
        val assignedTaskDateIdTV: TextView = assignedTask.findViewById(R.id.assignedTaskDateIdTV)
        val assignedTaskContentIdTV: TextView = assignedTask.findViewById(R.id.assignedTaskContentIdTV)
        val assignedTaskExpandableLayoutId: ConstraintLayout = assignedTask.findViewById(R.id.assignedTaskExpandableLayoutId);
        val assignedTaskCompleteBtnId :TextView =assignedTask.findViewById(R.id.assignedTaskCompleteBtnId)

    }
}
