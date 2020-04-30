package com.example.data

interface PermissionChecker {

    fun check(permission: Permission) : Boolean

}