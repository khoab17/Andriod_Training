package com.syedabdullah.roomdb.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.syedabdullah.roomdb.R
import com.syedabdullah.roomdb.model.User
import com.syedabdullah.roomdb.viewmodel.UserViewModel


class UserAdapter(
    viewModel: UserViewModel,
     private val users:List<User>
):RecyclerView.Adapter<UserAdapter.ItemViewHolder>() {

    class ItemViewHolder(view: View):RecyclerView.ViewHolder(view){
        val image: ImageView =view.findViewById(R.id.image_view_user)
        val id: TextView =view.findViewById(R.id.tv_user_id)
        val name: TextView =view.findViewById(R.id.tv_user_name)
        val age:TextView=view.findViewById(R.id.tv_user_age)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val layout= LayoutInflater.from(parent.context).inflate(R.layout.user_view,parent,false)
        return ItemViewHolder(layout)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.image.setImageResource(R.drawable.ic_baseline_person_24)
        holder.id.text=users[position].userId.toString()
        "${users[position].firstName} ${users[position].lastName}".also { holder.name.text = it }
        holder.age.text=users[position].age.toString()

    }

    override fun getItemCount(): Int {
        return users.size
    }
}