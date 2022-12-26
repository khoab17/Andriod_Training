package com.syedabdullah.todo.data

import com.syedabdullah.todo.model.ToDo

class ToDoRepo {
    companion object{
        var listOfTask: MutableList<ToDo> = mutableListOf<ToDo>(
            ToDo("work","Life seems very rough.","Mon Dec 26 18:25:26 BDT 2022",false),
            ToDo("work","need to got to office at 8.","Mon Dec 26 18:25:26 BDT 2022",true),
            ToDo("work","need to got to office at 8.","Mon Dec 26 18:25:26 BDT 2022",true),
            ToDo("work","need to got to office at 8.","Mon Dec 26 18:25:26 BDT 2022",true),
            ToDo("work","need to got to office at 8.","Mon Dec 26 18:25:26 BDT 2022",true),
            ToDo("work","need to got to office at 8.","Mon Dec 26 18:25:26 BDT 2022",false),
            ToDo("work","need to got to office at 8.","Mon Dec 26 18:25:26 BDT 2022",true),
            ToDo("work","need to got to office at 8.","Mon Dec 26 18:25:26 BDT 2022",false),
            ToDo("work","need to got to office at 8.","Mon Dec 26 18:25:26 BDT 2022",true),
            ToDo("work","need to got to office at 8.","Mon Dec 26 18:25:26 BDT 2022",false),
            ToDo("work","need to got to office at 8.","Mon Dec 26 18:25:26 BDT 2022",true),
            ToDo("work","need to got to office at 8.","Mon Dec 26 18:25:26 BDT 2022",false)
        )
    }

    fun getToDo():List<ToDo>{
        val listTodo= mutableListOf<ToDo>()
        listOfTask.forEach {
            if(!it.status)
                listTodo.add(it)
        }
        return listTodo
    }

    fun getHistory():List<ToDo>{
        val history= mutableListOf<ToDo>()
        listOfTask.forEach {
            if (it.status)
                history.add(it)
        }
        return history
    }

    fun addTodo(todo:ToDo){
        listOfTask.add(todo)
    }
}