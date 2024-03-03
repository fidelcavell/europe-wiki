package id.project.europecountrywiki

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import id.project.europecountrywiki.databinding.ItemRowEuropeCountryBinding

class ListEuropeCountryAdapter(private val listEuropeCountry: ArrayList<EuropeCountry>): RecyclerView.Adapter<ListEuropeCountryAdapter.ListViewHolder>() {
    private lateinit var onItemClickCallback: OnItemClickCallback

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    class ListViewHolder(var binding: ItemRowEuropeCountryBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val binding = ItemRowEuropeCountryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ListViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return listEuropeCountry.size
    }

    // To put data source to view component :
    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val (name, description, flag) = listEuropeCountry[position]
        Glide.with(holder.itemView.context).load(flag).into(holder.binding.imgItemPhoto)
        holder.binding.tvItemName.text = name
        holder.binding.tvItemDescription.text = description

        // set onClick Function to every single item :
        holder.itemView.setOnClickListener {
            onItemClickCallback.onItemClicked(listEuropeCountry[holder.adapterPosition])
        }
    }

    interface OnItemClickCallback {
        fun onItemClicked(data: EuropeCountry)
    }
}