package com.arctouch.codechallenge.presentation.home

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import com.arctouch.codechallenge.R
import com.arctouch.codechallenge.model.Movie
import com.arctouch.codechallenge.util.ViewUtil
import kotlinx.android.synthetic.main.movie_item.view.*

class HomeAdapter(private val movies: MutableList<Movie>) : RecyclerView.Adapter<HomeAdapter.ViewHolder>(), Filterable {

    lateinit var listener: OnMovieClickItemListener
    private var movieList: List<Movie>? = null

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(movie: Movie) {
            itemView.titleTextView.text = movie.title
            itemView.genresTextView.text = movie.genres?.joinToString(separator = ", ") { it.name }
            itemView.releaseDateTextView.text = movie.releaseDate
            itemView.setOnClickListener { listener.onClick(movie.id) }

            ViewUtil.showPoster(itemView,movie.posterPath,itemView.imageViewPoster)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.movie_item, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount() = movies.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(movies[position])

    fun updateList(listArticle: List<Movie>) {
        this.movies.addAll(listArticle)
        this.notifyDataSetChanged()
    }

    interface OnMovieClickItemListener {
        fun onClick(id: Int) = Unit
    }

    inline fun setMovieClickItemListener(crossinline listener: (Int) -> Unit) {
        this.listener = object : OnMovieClickItemListener {
            override fun onClick(id: Int) = listener(id)
        }
    }

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(charSequence: CharSequence): FilterResults {
                val charString = charSequence.toString()
                if (charString.isEmpty()) {
                    movieList = movies
                } else {
                    val filteredList = ArrayList<Movie>()
                    for (row in movies) {
                        if (row.title!!.toLowerCase().contains(charString.toLowerCase())) {
                            filteredList.add(row)
                        }
                    }
                    movieList = filteredList
                }
                val filterResults = FilterResults()
                filterResults.values = movieList
                return filterResults
            }
            override fun publishResults(charSequence: CharSequence, filterResults: FilterResults) {
                movieList = filterResults.values as ArrayList<Movie>
                notifyDataSetChanged()
            }
        }
    }
}
