package com.example.flickrbrowser

import android.net.Uri
import android.nfc.NdefRecord.createUri
import android.os.Bundle
import android.util.Log
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager

import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*
import java.lang.Exception

private const val TAG = "MainActivity"
private val flickrRecyclerViewAdapter = FlickrRecyclerViewAdapter(ArrayList())

class MainActivity : AppCompatActivity(), GetRawData.OnDownloadComplete,
    GetFlickrJsonData.OnDataAvailable,
    RecyclerItemClickListener.OnRecyclerClickListener{


    override fun onCreate(savedInstanceState: Bundle?) {
        Log.d(TAG,"onCreate called ++++++++++++++++++")
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        recycler_view.layoutManager = LinearLayoutManager(this)
        recycler_view.addOnItemTouchListener(RecyclerItemClickListener(this, recycler_view,this))
        recycler_view.adapter = flickrRecyclerViewAdapter

        val url = createUri("https://www.flickr.com/services/feeds/photos_public.gne","space,planets,stars", "en-us", true)
        val getRawData = GetRawData(this)
    //    getRawData.setDownloadCompleteListener(this)
        getRawData.execute(url)
//        fab.setOnClickListener { view ->
//            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                .setAction("Action", null).show()
//        }
        Log.d(TAG,"onCreate ends ------------")
    }

    override fun onItemClick(view: View, position: Int) {
        Log.d(TAG,".onItemClick: starts =======")
        Toast.makeText(this,"Normal tap at position $position", Toast.LENGTH_SHORT).show()
    }

    override fun onItemLongClick(view: View, position: Int) {
       Log.d(TAG,".onItemLongClick: starts +++++++")
        Toast.makeText(this,"Long tap at position $position", Toast.LENGTH_SHORT).show()
    }

    private fun createUri(baseURL: String, searchCriteria: String, lang: String, matchAll: Boolean): String{
        Log.d(TAG, ".createUri starts ############")
//        var uri = Uri.parse(baseURL)
//        var builder = uri.buildUpon()
//        builder = builder.appendQueryParameter("tags", searchCriteria)
//        builder = builder.appendQueryParameter("tagmode", if (matchAll) "ALL" else "ANY")
//        builder = builder.appendQueryParameter("lang", lang)
//        builder = builder.appendQueryParameter("format", "json")
//        builder = builder.appendQueryParameter("nojsoncallback", "1")
//        uri = builder.build()                                        //this is the same as below but with a builder variable
        return Uri.parse(baseURL)
        .buildUpon()
        .appendQueryParameter("tags", searchCriteria)
        .appendQueryParameter("tagmode", if (matchAll) "ALL" else "ANY")
        .appendQueryParameter("lang", lang)
        .appendQueryParameter("format", "json")
        .appendQueryParameter("nojsoncallback", "1")
        .build().toString()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        Log.d(TAG,"onCreateOptionsMenu called ----------------")
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        Log.d(TAG,"onCreateOptionsItemSelected called ***************")
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

//    companion object {
//        private const val TAG = "MainActivity"
//    }

    override fun onDownloadComplete(data: String, status:DownloadStatus) {
        if (status == DownloadStatus.OK){
            Log.d(TAG,"onDownloadComplete called------")

           val getFlickrJsonData = GetFlickrJsonData(this)
            getFlickrJsonData.execute(data)

        }else {
            // download failed
            Log.d(TAG,"onDownloadComplete failed with status $status. Error message is: $data")
        }
    }

    override fun onDataAvailable(data: List<Photo>) {
        Log.d(TAG,".onDataAvailable called ####")

        flickrRecyclerViewAdapter.loadNewData(data)

        Log.d(TAG,".onDataAvailable ends----------")
    }

    override fun onError(exception: Exception) {
        Log.e(TAG,"onError called with ${exception.message}")
    }
}