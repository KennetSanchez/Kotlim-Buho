package com.example.buho.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
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

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        setContentView(binding.root)

        binding.backButton.setOnClickListener{
            super.finish()
        }

        binding.RARegisterBtn.setOnClickListener {
            registerUser()
        }
    }

    private fun registerUser(){
        val email =  binding.RAUserIfInstitutionalEmail.text.toString()
        val password = binding.RAPassword.text.toString()

        Firebase.auth.createUserWithEmailAndPassword(email, password).addOnSuccessListener {
            val id = Firebase.auth.currentUser?.uid
            val user = User(id!!, binding.RAUserIfName.text.toString(), email, password)

            Firebase.firestore.collection("users").document(id).set(user).addOnSuccessListener {
                Toast.makeText(applicationContext, "Usuario registrado exitosamente", Toast.LENGTH_LONG).show()
                sendVerificationEmail()
            }.addOnFailureListener{
                Toast.makeText(applicationContext, it.message.toString(), Toast.LENGTH_LONG).show()
            }
        }.addOnFailureListener{
            Toast.makeText(applicationContext, it.message, Toast.LENGTH_LONG).show()
        }
    }

    private fun sendVerificationEmail(){
        Firebase.auth.currentUser?.sendEmailVerification()?.addOnSuccessListener {
            Toast.makeText(this, "Para completar el registro verifique su correo", Toast.LENGTH_LONG).show()
        }?.addOnFailureListener {
            Toast.makeText(this, it.message, Toast.LENGTH_LONG).show()
        }

    }

    private fun validations(): Boolean {
        var temp:Boolean = true
        val validation = arrayOf<String>(
            binding.RAUserIfName.text.toString(),
            binding.RAUserIfInstitutionalEmail.text.toString(),
            binding.RAPassword.text.toString(),
            binding.RAUserIfRepeatPassword.text.toString()
        )
        val aux=binding.RAUserIfInstitutionalEmail.text.toString().split("@")

        for (i in validation.indices) {
            if(validation[i]=="" || validation[i]==null ){
                Toast.makeText(applicationContext, "No se permiten espacios vacios", Toast.LENGTH_LONG).show()
                temp=false
            }
            if(!(i==0 && nameValidator(validation[i]))){
                Toast.makeText(applicationContext, "El nombre solo debe contener letras y espacios", Toast.LENGTH_LONG).show()
                temp=false
            }
            if(!(i==1 && aux.size==2 && aux[aux.size-1].contains("u.icesi.edu.co"))){
                Toast.makeText(applicationContext, "El correo institucional debe pertenecer a la universidad icesi como: ejemplo@u.icesi.edu.co", Toast.LENGTH_LONG).show()
                temp=false
            }
            if(validation[2] != validation[3]){
                Toast.makeText(applicationContext, "Las contraseñas no coinciden", Toast.LENGTH_LONG).show()
                temp=false
            }
        }
        return temp
    }
    private fun nameValidator(text: String?): Boolean {
        val p = Pattern.compile("/^[\\pL\\s]*\$/u")
        val m = p.matcher(text)
        return m.matches()
    }
}
