package com.syedabdullah.todo.data

import com.syedabdullah.todo.model.ToDo

class ToDoRepo {
    companion object{
        var listofTask: MutableList<ToDo> = mutableListOf<ToDo>(
            ToDo("work","need to got to office at 8.","12-43-22","10:20",false),
            ToDo("work","need to got to office at 8.","12-43-22","10:20",false),
            ToDo("work","need to got to office at 8.","12-43-22","10:20",false),
            ToDo("work","need to got to office at 8.","12-43-22","10:20",false),
            ToDo("work","need to got to office at 8.","12-43-22","10:20",false),
            ToDo("work","need to got to office at 8.","12-43-22","10:20",false),
            ToDo("work","need to got to office at 8.","12-43-22","10:20",false),
            ToDo("work","need to got to office at 8.","12-43-22","10:20",false),
            ToDo("work","need to got to office at 8.","12-43-22","10:20",false),
            ToDo("work","need to got to office at 8.","12-43-22","10:20",false),
            ToDo("work","need to got to office at 8.","12-43-22","10:20",false)
        )
    }

    fun getToDo():List<ToDo>{
        return listofTask
    }

    fun addTodo(todo:ToDo){
        listofTask.add(todo)
    }
}