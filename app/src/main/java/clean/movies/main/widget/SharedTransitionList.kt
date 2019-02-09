package clean.movies.main.widget

import android.support.v4.util.Pair
import android.view.View

class SharedTransitionList {
    private var transitions: MutableList<Pair<View, String>>? = null

    fun add(view: View, transitionName: String) {
        transitions?.add(Pair(view, transitionName)) ?: kotlin.run {
            transitions = mutableListOf(Pair(view, transitionName))
        }
    }

    fun get(index: Int): Pair<View, String> = transitions?.get(index)!!

    fun size(): Int = transitions?.size ?: 0
    
    fun asVarargsArray(): Array<Pair<View, String>> = transitions?.let { Array(size()) { transitions!![it] } } ?: emptyArray()
}