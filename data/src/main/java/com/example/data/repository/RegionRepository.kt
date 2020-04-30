package com.example.data.repository

import com.example.data.Permission
import com.example.data.PermissionChecker
import com.example.data.source.LocationDataSource

class RegionRepository(
    private val locationDataSource: LocationDataSource,
    private val permissionChecker: PermissionChecker) {

    companion object{
       const val DEFAULT_LOCATION = "US"
    }

    suspend fun findLastRegion(): String {
         return if (permissionChecker.check(Permission.COARSE_LOCATION)){
             locationDataSource.getLastLocation() ?: DEFAULT_LOCATION
         } else {
             DEFAULT_LOCATION
         }
    }

}