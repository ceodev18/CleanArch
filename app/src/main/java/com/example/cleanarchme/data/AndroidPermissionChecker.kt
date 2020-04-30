package com.example.cleanarchme.data

import android.Manifest
import android.app.Application
import android.content.pm.PackageManager
import androidx.core.content.ContextCompat
import com.example.data.Permission
import com.example.data.PermissionChecker

class AndroidPermissionChecker(private val app: Application) : PermissionChecker {

    override fun check(permission: Permission): Boolean =
        ContextCompat.checkSelfPermission(
            app,
            permission.toAndroidPermission()
        ) == PackageManager.PERMISSION_GRANTED


    private fun Permission.toAndroidPermission() = when (this) {

        Permission.COARSE_LOCATION -> {
            Manifest.permission.ACCESS_COARSE_LOCATION
        }

    }
}