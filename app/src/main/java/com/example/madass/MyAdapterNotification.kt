package com.example.madass
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView

class MyAdapterNotification (private val notificationList :List<notificationView>) :RecyclerView.Adapter<MyAdapterNotification.MyViewHolder>() {

    var expandNotification :Boolean=false

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        var notificationView = LayoutInflater.from(parent.context).inflate(R.layout.activity_notification_view,parent,false)
        return MyViewHolder(notificationView)
    }
    override fun getItemCount(): Int {
        return notificationList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem =notificationList[position]
        holder.notificationTitleIdTV.setText(notificationList[position].notificationTitle)

        holder.notificationTitleIdTV.setOnClickListener(){
          //  Log.d("a","a")
            if(expandNotification==false){
               // Log.d("a","b")
                holder.expandableLayout.visibility = View.VISIBLE
                expandNotification = true

            }else{
               // Log.d("a","c")
                holder.expandableLayout.visibility = View.GONE
                expandNotification = false
            }
        }
        holder.notificationDateIdTV.setText(notificationList[position].notificationDate)

        holder.notificationContentIdTV .setText(notificationList[position].notificationContent)
    }

    class MyViewHolder(notification:View):RecyclerView.ViewHolder(notification) {
        val notificationTitleIdTV: TextView = notification.findViewById(R.id.notificationTitleIdTV)
        val notificationDateIdTV: TextView = notification.findViewById(R.id.notificationDateIdTV)
        val notificationContentIdTV: TextView = notification.findViewById(R.id.notificationContentIdTV)
       val expandableLayout: ConstraintLayout = notification.findViewById(R.id.expandableLayout);

    }
}
