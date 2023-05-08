package com.example.herohub.utills.shared

import android.content.Context
import android.content.SharedPreferences

object SharedPreferencesUtils {
    private var sharedPreferences: SharedPreferences? = null
    private var editor :SharedPreferences.Editor? = null

    fun initShared(context: Context) {
        sharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        editor = sharedPreferences?.edit()
    }

    fun saveItems(items: String) {
        deleteItems()
        editor?.putString(ITEMS, items)
        editor?.apply()
    }

    fun getItems(): String? {
        return sharedPreferences?.getString(ITEMS, "data")
    }

    fun deleteItems() {
        editor?.remove(ITEMS)
        editor?.apply()
    }

    private const val PREF_NAME = "pref"
    private const val ITEMS = "items"

}