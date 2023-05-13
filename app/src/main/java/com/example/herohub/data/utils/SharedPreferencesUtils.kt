package com.example.herohub.data.utils

import android.content.Context
import android.content.SharedPreferences

object SharedPreferencesUtils {
    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var editor :SharedPreferences.Editor

    fun initShared(context: Context) {
        sharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        editor = sharedPreferences.edit()
    }

    fun saveItems(items: String?) {
        deleteItems()
        editor.putString(ITEMS_KEY, items)
        editor.apply()
    }

    fun getItems(): String? {
        return sharedPreferences.getString(ITEMS_KEY, "")
    }

    private fun deleteItems() {
        editor.remove(ITEMS_KEY)
        editor.apply()
    }

    private const val PREF_NAME = "pref"
    private const val ITEMS_KEY = "items"

}