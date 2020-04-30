package com.example.data.source

interface LocationDataSource {

    suspend fun getLastLocation() : String?

}