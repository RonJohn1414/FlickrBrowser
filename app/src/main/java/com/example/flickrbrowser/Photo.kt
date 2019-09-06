package com.example.flickrbrowser

class Photo(val title: String, val author: String, val authorID: String, val links: String, val tags: String, val image: String) {
    override fun toString(): String {
        return "Photo(title='$title', author='$author', authorID='$authorID', links='$links', tags='$tags', image='$image')"
    }
}