package com.example.buho.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import android.util.Log
import android.widget.Toast
import com.example.buho.R
import com.example.buho.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {
    private val binding:ActivityLoginBinding by lazy{
        ActivityLoginBinding.inflate(layoutInflater)
    }

    private var shown : Boolean = false;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        setContentView(binding.root)

        Log.i(">>>", "${binding.passET.inputType}")

        binding.passwordToggle.setOnClickListener {
            toggle()
        }

        binding.LARegisterBtn.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }

        binding.LALoginBtn.setOnClickListener{
            login()
        }
    }

    private fun login() {
            val username = binding.usernameET.text.toString()
            val pass = binding.passET.text.toString()

            val usernameAndPassEmpty = "Mi loco, complicado iniciar sesión si no metés datos"
            val usernameEmpty = "¿Quién chota sos?"
            val passwordEmpty = "¿Y la contraseña?"
            val wrongFields = "Te confundiste de personalidad o esa no es la contraseña"

            if(username == getString(R.string.LA_dummy_un) && pass == getString(R.string.LA_dummy_pass)){
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
            } else {
                val toastMsg = when {
                    username.isEmpty() -> when {
                        pass.isEmpty() -> usernameAndPassEmpty
                        else -> usernameEmpty
                    }
                    pass.isEmpty() -> passwordEmpty
                    else -> wrongFields
                }
                val toast = Toast.makeText(applicationContext, toastMsg, Toast.LENGTH_LONG)

                toast.show()
            }
    }

    private fun toggle() {

        val newRes = when {
            shown -> R.drawable.eye
            else -> R.drawable.eye_slash
        }

//        findViewById<TextInputEditText>(R.id.passET).inputType

        binding.passET.transformationMethod = when {
            shown -> PasswordTransformationMethod.getInstance()
            else -> HideReturnsTransformationMethod.getInstance()
        }

        binding.passwordToggle.setImageResource(newRes)
        shown = !shown
    }
}