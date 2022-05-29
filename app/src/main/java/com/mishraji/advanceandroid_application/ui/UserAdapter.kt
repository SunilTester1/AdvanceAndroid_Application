package com.mishraji.advanceandroid_application.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.mishraji.advanceandroid_application.R
import com.mishraji.advanceandroid_application.callback.IOnRecyclerItemClickListener
import com.mishraji.advanceandroid_application.data.User
import com.mishraji.advanceandroid_application.databinding.ListItemBinding

class UserAdapter(val callback: IOnRecyclerItemClickListener) :
    RecyclerView.Adapter<UserAdapter.UserViewHolder>() {

    var mList = ArrayList<User>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserAdapter.UserViewHolder {
        val binding =
            DataBindingUtil.inflate<ListItemBinding>(
                LayoutInflater.from(parent.context),
                R.layout.list_item,
                parent,
                false
            )
        return UserViewHolder(binding)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val currentItem = mList[position]
        holder.binding.apply {
            Glide.with(holder.itemView.context)
                .load(currentItem.owner?.avatarUrl)
                .into(avatarImg)
            textViewNo.text = currentItem.name
            textViewName.text = currentItem.fullName
            root.setOnClickListener { callback.onItemClick(currentItem) }
        }
    }

    class UserViewHolder(val binding: ListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(user: User) {}
    }

    override fun getItemCount(): Int {
        return mList.size
    }

    fun setData(mList: ArrayList<User>) {
        this.mList = mList
        notifyDataSetChanged()
    }
}