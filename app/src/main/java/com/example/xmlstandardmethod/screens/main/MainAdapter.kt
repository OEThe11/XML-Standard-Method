package com.example.xmlstandardmethod.screens.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.xmlstandardmethod.databinding.ContainerMainBinding
import com.example.xmlstandardmethod.models.ui.StandardUIModel

class MainAdapter(
    private val standardList: List<StandardUIModel>,
    private val listener: OnItemClickListener
): RecyclerView.Adapter<MainAdapter.MainViewHolder>() {

        interface OnItemClickListener {
            fun onItemClick(id: Int)
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainAdapter.MainViewHolder {
        val binding = ContainerMainBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MainViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MainAdapter.MainViewHolder, position: Int) {
        holder.bind(standardList[position])
    }

    override fun getItemCount(): Int = standardList.size



    inner class MainViewHolder(private val binding: ContainerMainBinding):
        RecyclerView.ViewHolder(binding.root){
            fun bind(item: StandardUIModel){
                binding.userIdView.text = item.userId.toString()
                binding.titleView.text = item.title
                binding.completedView.text = item.completed.toString()

                // Setting ClickListener
                binding.root.setOnClickListener {
                    listener.onItemClick(item.id)
                }

            }

        }
}