package com.reift.githubuser.data.local

class LocalDataSource{
    companion object {
        private var INSTANCE: LocalDataSource? = null

        fun getInstance(): LocalDataSource =
            INSTANCE ?: synchronized(this) {
                INSTANCE ?: LocalDataSource()
            }
    }
}