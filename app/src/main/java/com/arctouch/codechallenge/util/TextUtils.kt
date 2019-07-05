package com.arctouch.codechallenge.util

import com.arctouch.codechallenge.model.Genre

class TextUtils {

    companion object {

        fun organizeGenres(listGenres: List<Genre>?): String {
            var genres = ""
            listGenres?.forEach {
                genres += it.name + " | "
            }
            return genres.substring(0, genres.length - 2)
        }
    }


}