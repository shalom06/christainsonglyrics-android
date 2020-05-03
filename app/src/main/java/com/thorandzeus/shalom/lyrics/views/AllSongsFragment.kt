package com.thorandzeus.shalom.lyrics.views

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.support.annotation.RequiresApi
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.SearchView
import android.support.v7.widget.Toolbar
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.widget.ImageButton
import com.thorandzeus.shalom.lyrics.LyricsAdapter
import com.thorandzeus.shalom.lyrics.R
import com.thorandzeus.shalom.lyrics.ShowLyrics
import com.thorandzeus.shalom.lyrics.models.ListItem
import org.apache.commons.lang3.StringUtils


class AllSongsFragment : Fragment(), LyricsAdapter.OnSongClickedListener {
    companion object {
        const val SONG_SELECTED = "SONG_SELECTED"
    }

    var favorites: ImageButton? = null
    var adapter: LyricsAdapter? = null
    var recyclerView: RecyclerView? = null
    var songList: List<ListItem> = emptyList()
    @SuppressLint("DefaultLocale")
    @RequiresApi(api = Build.VERSION_CODES.N)
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.all_songs, container, false)
        val window = activity!!.window
        val toolbar: Toolbar = view.findViewById(R.id.toolbar)
        (activity as AppCompatActivity?)!!.setSupportActionBar(toolbar)
        recyclerView = view.findViewById(R.id.songs_recycler_view)
        val searchView: SearchView = view.findViewById(R.id.search_songs)
        searchView.imeOptions = EditorInfo.IME_ACTION_DONE
        searchView.setOnQueryTextListener(searchViewListener)
        val songNames = resources.getStringArray(R.array.song_names)
        songList = songNames.map { ListItem(StringUtils.capitalize(it.toLowerCase())) }

        setUpRecyclerView()
        return view
    }

    private val searchViewListener: SearchView.OnQueryTextListener = object : SearchView.OnQueryTextListener {
        override fun onQueryTextSubmit(query: String): Boolean {
            return false
        }

        override fun onQueryTextChange(newText: String): Boolean {
            adapter!!.filter.filter(newText)
            return false
        }
    }

    private fun setUpRecyclerView() {
        recyclerView!!.setHasFixedSize(true)
        val layoutManager: RecyclerView.LayoutManager = LinearLayoutManager(activity)
        adapter = LyricsAdapter(songList, this)
        recyclerView!!.layoutManager = layoutManager
        recyclerView!!.adapter = adapter
    }

    override fun onSongItemClicked(song: String, no: Int) {
        val i = Intent(activity, ShowLyrics::class.java)

        i.putExtra(SONG_SELECTED, song)
        startActivity(i)

    }

}