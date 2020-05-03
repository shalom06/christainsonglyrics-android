package com.thorandzeus.shalom.lyrics.views.favorites

import android.annotation.SuppressLint
import android.content.Intent
import android.content.SharedPreferences
import android.os.Build
import android.os.Bundle
import android.preference.PreferenceManager
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
import com.thorandzeus.shalom.lyrics.LyricsAdapter
import com.thorandzeus.shalom.lyrics.MainActivity
import com.thorandzeus.shalom.lyrics.R
import com.thorandzeus.shalom.lyrics.ShowLyrics
import com.thorandzeus.shalom.lyrics.models.ListItem
import com.thorandzeus.shalom.lyrics.views.AllSongsFragment
import kotlinx.android.synthetic.main.first_row_toolbar.*
import org.apache.commons.lang3.StringUtils

class Favorites : Fragment(), LyricsAdapter.OnSongClickedListener {

    companion object {
        fun newInstance() = Favorites()
    }

    private lateinit var viewModel: FavoritesViewModel

    lateinit var prefs: SharedPreferences
    private lateinit var favoritesList: MutableSet<String>
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
        prefs = PreferenceManager.getDefaultSharedPreferences(activity?.applicationContext)
        favoritesList = prefs.getStringSet(MainActivity.FAVOURITES, mutableSetOf())!!

        recyclerView = view.findViewById(R.id.songs_recycler_view)
        val searchView: SearchView = view.findViewById(R.id.search_songs)
        searchView.imeOptions = EditorInfo.IME_ACTION_DONE
        searchView.setOnQueryTextListener(searchViewListener)
        songList = favoritesList.map { ListItem(StringUtils.capitalize(it.toLowerCase().replace("_", " "))) }

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

        i.putExtra(AllSongsFragment.SONG_SELECTED, song)
        startActivity(i)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        app_name.text = "Favorites"
    }

    override fun onResume() {
        super.onResume()
        favoritesList = prefs.getStringSet(MainActivity.FAVOURITES, mutableSetOf())!!
        songList = favoritesList.map { ListItem(StringUtils.capitalize(it.toLowerCase().replace("_", " "))) }
        adapter?.updateList(songList)
    }

}
