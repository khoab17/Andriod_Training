package com.syedabdullah.roomdb.ui.adapter

import android.content.Context
import android.view.AttachedSurfaceControl
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.MediaController
import android.widget.TextView
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.syedabdullah.roomdb.R
import com.syedabdullah.roomdb.model.User
import com.syedabdullah.roomdb.ui.UsersFragmentDirections
import com.syedabdullah.roomdb.viewmodel.UserViewModel


class UserAdapter(
   val context: Context,
   val viewModel: UserViewModel
):RecyclerView.Adapter<UserAdapter.ItemViewHolder>() {
    private var listOfUsers= emptyList<User>()


    class ItemViewHolder(view: View):RecyclerView.ViewHolder(view){
        val image: ImageView =view.findViewById(R.id.image_view_user)
        val firstName: TextView =view.findViewById(R.id.tv_first_name)
        val lastName: TextView =view.findViewById(R.id.tv_last_name)
        val age:TextView=view.findViewById(R.id.tv_age)

        val updateButton:Button=view.findViewById(R.id.button_update)
        val deleteButton:Button=view.findViewById(R.id.button_delete)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val layout= LayoutInflater.from(parent.context).inflate(R.layout.user_view,parent,false)
        return ItemViewHolder(layout)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.apply {
            image.setImageResource(R.drawable.ic_baseline_person_24)
            firstName.text=listOfUsers[position].firstName
            lastName.text=listOfUsers[position].lastName
            age.text=listOfUsers[position].age.toString()

            updateButton.setOnClickListener {
                val user=User(
                    listOfUsers[position].userId,
                    firstName.text.toString(),
                    lastName.text.toString(),
                    age.text.toString().toInt()
                )
                val action=UsersFragmentDirections.actionUsersFragmentToUpdateFragment(user)
                holder.itemView.findNavController().navigate(action)
            }

            deleteButton.setOnClickListener {
                viewModel.deleteUser(listOfUsers[position])
            }
        }
    }

    override fun getItemCount(): Int {
        return listOfUsers.size
    }

    fun setData(users:List<User>){
        listOfUsers=users
        notifyDataSetChanged()
    }
}