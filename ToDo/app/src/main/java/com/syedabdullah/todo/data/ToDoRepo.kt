package com.syedabdullah.todo.data

import com.syedabdullah.todo.model.ToDo

class ToDoRepo {
    companion object{
        var listofTask: MutableList<ToDo> = mutableListOf<ToDo>(
            ToDo("work","need to got to office at 8.","Mon Dec 26 18:25:26 BDT 2022",false),
            ToDo("work","need to got to office at 8.","Mon Dec 26 18:25:26 BDT 2022",false),
            ToDo("work","need to got to office at 8.","Mon Dec 26 18:25:26 BDT 2022",true),
            ToDo("work","need to got to office at 8.","Mon Dec 26 18:25:26 BDT 2022",false),
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
        listofTask.forEach {
            if(!it.status)
                listTodo.add(it)
        }
        return listTodo
    }

    fun getHistory():List<ToDo>{
        val history= mutableListOf<ToDo>()
        listofTask.forEach {
            if (it.status)
                history.add(it)
        }
        return history
    }

    fun addTodo(todo:ToDo){
        listofTask.add(todo)
    }
}