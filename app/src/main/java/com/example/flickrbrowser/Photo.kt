package com.example.flickrbrowser

import android.os.Parcel
import android.os.Parcelable


class Photo(
    var title: String?, var author: String?,
    var authorID: String?, var links: String?,
    var tags: String?, var image: String?) : Parcelable {

    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString())

    override fun toString(): String {
        return "Photo(title='$title', author='$author', authorID='$authorID', links='$links', tags='$tags', image='$image')"
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(title)
        parcel.writeString(author)
        parcel.writeString(authorID)
        parcel.writeString(links)
        parcel.writeString(tags)
        parcel.writeString(image)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Photo> {
        override fun createFromParcel(parcel: Parcel): Photo {
            return Photo(parcel)
        }

        override fun newArray(size: Int): Array<Photo?> {
            return arrayOfNulls(size)
        }
    }

}