package com.sarwoedhi.gitapp.ui.search

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.sarwoedhi.gitapp.R
import com.sarwoedhi.gitapp.data.models.SearchEntity
import kotlinx.android.synthetic.main.item_results_search.view.*

class SearchAdapter(private val context: Context) :
    RecyclerView.Adapter<SearchAdapter.ViewHolder>() {
    private var resultList = ArrayList<SearchEntity>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view: View =
            LayoutInflater.from(context).inflate(R.layout.item_results_search, parent, false)
        return ViewHolder(view)
    }

    fun setResults(user: List<SearchEntity>) {
        resultList.clear()
        resultList.addAll(user)
        notifyDataSetChanged()
    }

    fun resetResults() {
        resultList.clear()
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return resultList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(resultList[position])
        holder.itemView.setOnClickListener {
            Toast.makeText(
                context,
                "You choose : ${resultList[position].name}",
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(results: SearchEntity) {
            itemView.tvName.text = "Name : ${results.name}"
            itemView.tvHtmlUrlSearch.text = "Url : ${results.owner?.html_url}"
            itemView.tvLoginName.text = "Owner : ${results.owner?.login}"
        }
    }
}