package com.example.buho.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.transition.Visibility
import android.util.Log
import android.view.View.GONE
import android.view.View.VISIBLE
import android.widget.Toast
import com.example.buho.databinding.ActivityRegisterBinding
import com.example.buho.models.User
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import java.util.regex.Pattern

class RegisterActivity : AppCompatActivity() {

    private val binding: ActivityRegisterBinding by lazy{
        ActivityRegisterBinding.inflate(layoutInflater)
    }

    private var registerStarted = false

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        setContentView(binding.root)

        binding.backButton.setOnClickListener{
            super.finish()
        }

        binding.RARegisterBtn.setOnClickListener {
            if(validateName() && validateEmail() && validatePassword() && validateEqualPasswords())
                registerUser()
        }
    }

    private fun registerUser(){
        val email =  binding.RAUserIfInstitutionalEmail.text.toString()
        val password = binding.RAPassword.text.toString()

        toggleRegisterState()

        Firebase.auth.createUserWithEmailAndPassword(email, password).addOnSuccessListener {
            val id = Firebase.auth.currentUser?.uid
            val user = User(id!!, binding.RAUserIfName.text.toString(), email, password)


            Firebase.firestore.collection("users").document(id).set(user).addOnSuccessListener {
                Toast.makeText(applicationContext, "Usuario registrado exitosamente", Toast.LENGTH_LONG).show()
                finish()
                sendVerificationEmail()
            }.addOnFailureListener{
                Toast.makeText(applicationContext, it.message.toString(), Toast.LENGTH_LONG).show()
            }
        }.addOnFailureListener{
            Toast.makeText(applicationContext, it.message, Toast.LENGTH_LONG).show()
        }
        toggleRegisterState()
    }

    private fun toggleRegisterState(){
        when (registerStarted){
            false -> binding.registerLoadingPane.visibility = VISIBLE
            true -> binding.registerLoadingPane.visibility = GONE
        }
    }

    private fun sendVerificationEmail(){
        Firebase.auth.currentUser?.sendEmailVerification()?.addOnSuccessListener {
            Toast.makeText(this, "Para completar el registro verifique su correo", Toast.LENGTH_LONG).show()
        }?.addOnFailureListener {
            Toast.makeText(this, it.message, Toast.LENGTH_LONG).show()
        }

    }

    private fun validateName(): Boolean{
        val str =  binding.RAUserIfName.text.toString()
        val valid = Pattern.compile("[a-zA-z ]").matcher(str).find() && str.isNotEmpty()
        if(!valid)
            Toast.makeText(this, "El campo de nombre no está diligenciado correctamente", Toast.LENGTH_LONG).show()
        return valid
    }

    private fun validateEmail(): Boolean{
        val str =  binding.RAUserIfInstitutionalEmail.text.toString()
        val valid = Pattern.compile(".*(@U\\.ICESI\\.EDU\\.CO)$|.*(@u\\.icesi\\.edu\\.co)\$").matcher(str).find() && str.isNotEmpty()
        if(!valid)
            Toast.makeText(this, "El campo de email no está diligenciado correctamente", Toast.LENGTH_LONG).show()
        return valid
    }

    private fun validatePassword(): Boolean{
        val str =  binding.RAPassword.text.toString()
        val valid = Pattern.compile("[a-zA-z0-9 ]").matcher(str).find() && str.isNotEmpty()
        if(!valid)
            Toast.makeText(this, "El campo de contraseña no está diligenciado correctamente", Toast.LENGTH_LONG).show()
        return valid
    }

    private fun validateEqualPasswords(): Boolean{
        val password =  binding.RAPassword.text.toString()
        val repeatedPassword =  binding.RAUserIfRepeatPassword.text.toString()
        val valid = password == repeatedPassword
        if(!valid)
            Toast.makeText(this, "Las contraseñas son distintas", Toast.LENGTH_LONG).show()
        return valid
    }
}
