package com.example.cleanarchme.views.main

interface ContractLogin {

    interface ContractLoginView {
        fun getUser(): String
        fun getPassword(): String

        fun nextActivity()
    }

    interface ContractPresenter {
        fun onLoginClick()
    }

}