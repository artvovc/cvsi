package android.cvsi.models

import android.cvsi.models.error.ServerResponseStatus
import java.io.Serializable
import java.util.*

class ListDto<T> : ServerResponseStatus(), Serializable {
    private val list: MutableList<T>

    init {
        list = ArrayList<T>()
    }

    fun getList(): List<T> {
        return list
    }

    fun setList(set: Set<T>): ListDto<T> {
        this.list.addAll(set)
        return this
    }

    fun sortBy(comparator: Comparator<T>): ListDto<T> {
        Collections.sort(this.list, comparator)
        return this
    }
}
