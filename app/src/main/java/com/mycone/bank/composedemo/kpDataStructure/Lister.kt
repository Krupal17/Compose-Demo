package com.mycone.bank.composedemo.kpDataStructure

import androidx.lifecycle.MutableLiveData

class Lister<L> : ArrayList<L>() {
//    var collection = MutableLiveData<List<L>>()
    var reCoverCollection = this
    var listener: ListerListener? = null

    override fun add(value: L): Boolean {
        var find = reCoverCollection.filter { value.hashCode() == it.hashCode() }
        if (find.isEmpty()) {
            reCoverCollection.add(value)
//            collection.postValue(reCoverCollection)
            listener?.onAdd()
            return true
        } else {
            listener?.onAddError()
            return true
        }
    }


    interface ListerListener {
        fun onAdd()
        fun onAddError()
        fun onUpdate()
    }

}