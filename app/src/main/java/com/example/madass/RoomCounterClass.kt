package com.example.madass

data class RoomCounterClass
(val roomNo : String,
 val custIC: String,
 val type :String)

data class notificationView(
 val notificationTitle : String,
 val notificationContent : String,
 val notificationDate : String
)

data class myTaskView(
 val taskTitle : String,
 val taskContent : String,
 val taskDeadline : String,
 val taskId:String,
 val taskStatus:String
)

data class assignedTaskView(
 val assignedTaskTitle : String,
 val assignedTaskContent : String,
 val assignedTaskDeadline : String,
 val assignedTaskStatus:String,
 val checked :String,
 val taskId :String
)

data class roomCleaningView
(val roomNo : String,
        //val roomFloor :Int,
        //val roomType :String,
        //val roomOccupant: Int,
 val roomCleanStatus:String)