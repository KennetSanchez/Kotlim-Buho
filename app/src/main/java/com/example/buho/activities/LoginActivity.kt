package com.example.buho.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import android.widget.Toast
import com.example.buho.R
import com.example.buho.databinding.LoginPageBinding
import com.example.buho.models.User
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.gson.Gson

class LoginActivity : AppCompatActivity() {
    private val binding:LoginPageBinding by lazy{
        LoginPageBinding.inflate(layoutInflater)
    }

    private var shown : Boolean = false;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        setContentView(binding.root)

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
            val email = binding.LAEmail.text.toString()
            val password = binding.LAPassword.text.toString()

            val usernameAndPassEmpty = "Mi loco, complicado iniciar sesión si no metés datos"
            val usernameEmpty = "¿Quién chota sos?"
            val passwordEmpty = "¿Y la contraseña?"
            val wrongFields = "Te confundiste de personalidad o esa no es la contraseña"

            var user:User?
            Firebase.auth.signInWithEmailAndPassword(email, password).addOnSuccessListener {
                val fbUser = Firebase.auth.currentUser
                if(fbUser!!.isEmailVerified){
                    Firebase.firestore.collection("users").document(fbUser.uid).get().addOnSuccessListener {
                        user = it.toObject(User::class.java)
                        val intent = Intent(this, MainActivity::class.java)
                        startActivity(intent)
                        saveUser(user!!)
                    }
                }else{
                    Toast.makeText(applicationContext, "El correo no ha sido verificado aún.", Toast.LENGTH_LONG).show()
                }
            }.addOnFailureListener {
                val toastMsg = when {
                    email.isEmpty() -> when {
                        password.isEmpty() -> usernameAndPassEmpty
                        else -> usernameEmpty
                    }
                    password.isEmpty() -> passwordEmpty
                    else -> wrongFields
                }
               Toast.makeText(applicationContext, toastMsg, Toast.LENGTH_LONG).show()
            }
    }

    private fun saveUser(user: User){
        val sp = getSharedPreferences("Buho", MODE_PRIVATE)
        val json = Gson().toJson(user)
        sp.edit().putString("user", json).apply()
    }
    private fun toggle() {

        val newRes = when {
            shown -> R.drawable.eye
            else -> R.drawable.eye_slash
        }

        findViewById<TextInputEditText>(R.id.RA_password).inputType

        binding.LAPassword.transformationMethod = when {
            shown -> PasswordTransformationMethod.getInstance()
            else -> HideReturnsTransformationMethod.getInstance()
        }

        binding.passwordToggle.setImageResource(newRes)
        shown = !shown
    }
}