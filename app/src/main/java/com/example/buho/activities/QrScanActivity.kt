package com.example.buho.activities

import android.icu.util.Calendar
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.budiyev.android.codescanner.*
import com.example.buho.R
import com.example.buho.models.Assistance
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class QrScanActivity : AppCompatActivity() {
    private lateinit var codeScanner: CodeScanner

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_qr_scan)
        val scannerView = findViewById<CodeScannerView>(R.id.scanner_view)
        val ACT_PREFIX="ACTBUHO: "
        val EVENT_PREFIX="EVTBUHO: "

        codeScanner = CodeScanner(this, scannerView)

        // Parameters (default values)
        codeScanner.camera = CodeScanner.CAMERA_BACK // or CAMERA_FRONT or specific camera id
        codeScanner.formats = CodeScanner.ALL_FORMATS // list of type BarcodeFormat,
        // ex. listOf(BarcodeFormat.QR_CODE)
        codeScanner.autoFocusMode = AutoFocusMode.SAFE // or CONTINUOUS
        codeScanner.scanMode = ScanMode.SINGLE // or CONTINUOUS or PREVIEW
        codeScanner.isAutoFocusEnabled = true // Whether to enable auto focus or not
        codeScanner.isFlashEnabled = false // Whether to enable flash or not

        // Callbacks
        codeScanner.decodeCallback = DecodeCallback {

            /*runOnUiThread {
                Toast.makeText(this, "Scan result: ${it.text}, hoy: $current", Toast.LENGTH_LONG).show()
            }*/
            val formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy")
            val current = LocalDateTime.now().format(formatter)
            var txt = it.text.toString()
            val id = Firebase.auth.currentUser?.uid

                if(txt.startsWith(ACT_PREFIX)){
                    txt=txt.removePrefix(ACT_PREFIX)
                    val assistance = Assistance(txt, current)
                    Firebase.firestore.collection("users").document(id!!).collection("assistanceActivities").document(txt).set(assistance).addOnSuccessListener {
                        runOnUiThread {
                            Toast.makeText(this, "Asistencia registrada", Toast.LENGTH_LONG).show()
                        }
                    }
                }else if(txt.startsWith(EVENT_PREFIX)){
                    txt=txt.removePrefix(EVENT_PREFIX)
                    val assistance = Assistance(txt, current)
                    Firebase.firestore.collection("users").document(id!!).collection("assistanceEvents").document(txt).set(assistance).addOnSuccessListener {
                        runOnUiThread {
                            Toast.makeText(this, "Asistencia registrada", Toast.LENGTH_LONG).show()
                        }
                    }
                }else{
                    runOnUiThread {
                        Toast.makeText(this, "QR Inválido",
                            Toast.LENGTH_LONG).show()
                    }
                }
                finish()

        }
        codeScanner.errorCallback = ErrorCallback { // or ErrorCallback.SUPPRESS
            runOnUiThread {
                Toast.makeText(this, "Camera initialization error: ${it.message}",
                    Toast.LENGTH_LONG).show()
            }
        }

        scannerView.setOnClickListener {
            codeScanner.startPreview()
        }
    }

    override fun onResume() {
        super.onResume()
        codeScanner.startPreview()
    }

    override fun onPause() {
        codeScanner.releaseResources()
        super.onPause()
    }
}