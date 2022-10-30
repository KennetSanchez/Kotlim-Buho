package com.example.buho.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.buho.databinding.ActivityRegisterBinding
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
            if(validations()){
                Toast.makeText(applicationContext, "Usuario registrado exitosamente", Toast.LENGTH_LONG).show()
            }
            super.finish()
        }
    }
    private fun validations(): Boolean {
        var temp:Boolean = true
        val validation = arrayOf<String>(
            binding.RAUserIfName.text.toString(),
            binding.RAUserIfInstitutionalEmail.text.toString(),
            binding.RAUserIfPassword.text.toString(),
            binding.RAUserIfRepeatPassword.text.toString()
        )
        var aux=binding.RAUserIfInstitutionalEmail.text.toString().split("@")

        for (i in validation.indices) {
            if(validation[i]=="" || validation[i]==null){
                Toast.makeText(applicationContext, "No se permiten espacios vacios", Toast.LENGTH_LONG).show()
                temp=false
            }
            if(!(i==0 && nameValidator(validation[i]))){
                Toast.makeText(applicationContext, "El nombre solo debe contener letras y espacios", Toast.LENGTH_LONG).show()
                temp=false
            }
            if(!(i==1 && aux.size==2 && aux[aux.size-1].contains("u.icesi.edu.co"))){
                Toast.makeText(applicationContext, "El correo institucional debe ser un correo valido como: ejemplo@u.icesi.edu.co", Toast.LENGTH_LONG).show()
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
