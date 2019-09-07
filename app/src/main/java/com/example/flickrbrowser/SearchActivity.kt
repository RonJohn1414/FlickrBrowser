package com.example.flickrbrowser

import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.annotation.RequiresApi


class SearchActivity : BaseActivity() {
    private val TAG = "SearchActivity"

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onCreate(savedInstanceState: Bundle?) {
        Log.d(TAG,".onCreate for search: starts **")
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)
        activateToolbar(true)
        Log.d(TAG,".onCreate for search: ends ****")

    }

}
