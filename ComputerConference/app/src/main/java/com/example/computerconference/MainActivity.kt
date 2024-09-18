package com.example.computerconference

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.computerconference.databinding.ActivityMainBinding
import java.io.BufferedReader
import java.io.FileInputStream
import java.io.InputStreamReader
import java.io.FileOutputStream

class MainActivity : AppCompatActivity() {
    private val TAG = "MainActivity"
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view: View = binding.root
        setContentView(view)

        val edtFirstname: EditText = binding.edtFisrtname
        val edtLastname: EditText = binding.edtLastname
        val edtEmail: EditText = binding.edtEmail
        val edtPhone: EditText = binding.edtPhone
        val edtBloodgroup: EditText = binding.edtBloodgroup
        val edtDni: EditText = binding.edtDni
        val btnLogin: Button = binding.btnLogin
        val btnShowData: Button = binding.btnShowData

        btnLogin.setOnClickListener {
            saveDataToFile("${edtFirstname.text}, ${edtLastname.text}, " +
                    "${edtEmail.text}, ${edtPhone.text}, ${edtBloodgroup.text}, ${edtDni.text}")
        }

        btnShowData.setOnClickListener {
            showDataFromFile()
        }
    }

    // Funcion para registrar los datos en un archivo de texto
    private fun saveDataToFile(data: String) {
        val filename = "datos_guardados.txt"
        try {
            val fos: FileOutputStream = openFileOutput(filename, MODE_APPEND)
            fos.write((data + "\n").toByteArray())
            fos.close()
            Toast.makeText(this, "Datos guardados", Toast.LENGTH_SHORT).show()
        } catch (e: Exception) {
            Log.e(TAG, "Error al guardar datos: ${e.message}")
            Toast.makeText(this, "Error al guardar datos", Toast.LENGTH_SHORT).show()
        }
    }

    // Funcion para leer los datos en un archivo de texto
    private fun showDataFromFile() {
        val filename = "datos_guardados.txt"
        try {
            val fis: FileInputStream = openFileInput(filename)
            val inputStreamReader = InputStreamReader(fis)
            val bufferedReader = BufferedReader(inputStreamReader)
            var line: String?
            Log.d(TAG, "Datos guardados:")
            while (bufferedReader.readLine().also { line = it } != null) {
                Log.d(TAG, line ?: "")
            }
            bufferedReader.close()
        } catch (e: Exception) {
            Log.e(TAG, "Error al leer datos: ${e.message}")
            Toast.makeText(this, "Error al leer datos", Toast.LENGTH_SHORT).show()
        }
    }
}
