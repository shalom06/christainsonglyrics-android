package com.thorandzeus.shalom.lyrics

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import android.widget.TextView
import com.thorandzeus.shalom.lyrics.LyricsAdapter.SongNamesHolder
import com.thorandzeus.shalom.lyrics.models.ListItem
import java.util.*

class LyricsAdapter(songList: List<ListItem>, private val onSongClickedListener: OnSongClickedListener) : RecyclerView.Adapter<SongNamesHolder>(), Filterable {
    private var songList: List<ListItem>
    private val songListFull: List<ListItem>
    private var layout: View? = null

    interface OnSongClickedListener {
        fun onSongItemClicked(song: String, no: Int)
    }

    inner class SongNamesHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var lyricsName: TextView

        init {
            layout = itemView
            lyricsName = itemView.findViewById(R.id.song_name)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SongNamesHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.list_item,
                parent, false)
        return SongNamesHolder(v)
    }

    override fun onBindViewHolder(holder: SongNamesHolder, position: Int) {
        val currentItem = songList[position]
        holder.lyricsName.text = currentItem.songName
        holder.itemView.setOnClickListener { view ->
            onSongClickedListener.onSongItemClicked(currentItem.songName, position)

        }
    }

    override fun getItemCount(): Int {
        return songList.size
    }

    override fun getFilter(): Filter {
        return songFilter
    }

    fun updateList(songList: List<ListItem>) {
        this.songList=songList
        notifyDataSetChanged()
    }

    private val songFilter: Filter = object : Filter() {
        val results = FilterResults()
        override fun performFiltering(constraint: CharSequence): FilterResults {
            val filteredList: MutableList<ListItem> = mutableListOf()
            if (constraint.isEmpty()) {
                results.values = songList
                return results
            } else {
                val filterPattern = constraint.toString().toLowerCase()
                for (item in songList) {
                    if (item.songName.toLowerCase().contains(filterPattern)) {
                        filteredList.add(item)
                    }
                }
            }

            results.values = filteredList
            return results
        }

        override fun publishResults(constraint: CharSequence, results: FilterResults) {
            if (constraint.isNotEmpty()) {
                this@LyricsAdapter.songList = results.values as List<ListItem>
                notifyDataSetChanged()
            }
        }
    }

    init {
        this.songList = songList
        songListFull = ArrayList(songList)
    }
}