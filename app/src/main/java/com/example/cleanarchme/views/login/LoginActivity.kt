package com.example.cleanarchme.views

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.biometric.BiometricManager
import com.example.cleanarchme.R
import com.example.cleanarchme.views.main.ContractLogin
import com.example.cleanarchme.views.main.MainActivity
import kotlinx.android.synthetic.main.activity_login.*
import org.koin.android.ext.android.inject

class LoginActivity : AppCompatActivity(), ContractLogin.ContractLoginView {

    private val presenter: ContractLogin.ContractPresenter by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        btnLogin.setOnClickListener {
            presenter.onLoginClick()
        }

    }

    override fun getUser(): String {
        return tilUser.text.toString()
    }

    override fun getPassword(): String {
        return tilPass.text.toString()
    }

    override fun nextActivity() {
        startActivity(Intent(this, MainActivity::class.java))
    }
}
