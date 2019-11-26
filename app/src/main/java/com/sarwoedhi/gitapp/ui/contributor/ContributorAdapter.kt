package com.sarwoedhi.gitapp.ui.contributor

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sarwoedhi.gitapp.R
import com.sarwoedhi.gitapp.data.models.GithubEntity
import com.sarwoedhi.gitapp.util.GlideApp
import kotlinx.android.synthetic.main.item_users.view.*

class ContributorAdapter(private val context: Context):RecyclerView.Adapter<ContributorAdapter.ViewHolder>(){

    private var userList = ArrayList<GithubEntity>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view: View = LayoutInflater.from(context).inflate(R.layout.item_users, parent, false)
        return ViewHolder(view)
    }

    fun setUsersData(user: List<GithubEntity>) {
        userList.clear()
        userList.addAll(user)
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return userList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(userList[position])
        holder.itemView.setOnClickListener {

        }
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(users: GithubEntity) {
            itemView.tvOwnerName.text = users.ownersName
            itemView.tvHtmlUrl.text = users.htmlUrl
            itemView.tvUserType.text = users.typeUser
            GlideApp.with(itemView.context).load(users.avatarUrl).into(itemView.imgPhotoOwner)
        }
    }
}