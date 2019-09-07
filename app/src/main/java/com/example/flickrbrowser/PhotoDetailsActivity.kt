package com.example.flickrbrowser

import android.os.Build
import android.os.Bundle
import androidx.annotation.RequiresApi
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.content_photo_details.*


class PhotoDetailsActivity : BaseActivity() {

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_photo_details)

        activateToolbar(true)

        val photo = intent.getParcelableExtra<Photo>(PHOTO_TRANSFER) as Photo

        photo_title.text = photo.title
        photo_tags.text = photo.tags
        photo_author.text = photo.author
        Picasso.get()
            .load(photo.links)
            .error(R.drawable.placeholder)
            .placeholder(R.drawable.placeholder)
            .into(photo_image)
    }

}
