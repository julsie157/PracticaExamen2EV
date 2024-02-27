package com.example.practicaexamen2ev

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class Informacion : AppCompatActivity() {

    private lateinit var dbGeneral: BaseDeDatos
    private lateinit var nombreTextView: TextView
    private lateinit var apellidoTextView: TextView
    private lateinit var edadTextView: TextView
    private lateinit var calleEditText: EditText
    private lateinit var sexoEditText: EditText
    private lateinit var botonGuardar: Button
    private lateinit var botonSiguiente: Button
    private lateinit var botonAtras: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.layout_informacion)

        dbGeneral = BaseDeDatos(this)

        nombreTextView = findViewById(R.id.textViewNombre)
        apellidoTextView = findViewById(R.id.textViewApellido)
        edadTextView = findViewById(R.id.textViewEdad)
        calleEditText = findViewById(R.id.editTextCalle)
        sexoEditText = findViewById(R.id.editTextSexo)
        botonGuardar = findViewById(R.id.botonGuardar)
        botonSiguiente = findViewById(R.id.botonSiguiente)
        botonAtras = findViewById(R.id.botonAtras)

        nombreTextView.text = intent.getStringExtra("intentExtraNombre")
        apellidoTextView.text = intent.getStringExtra("intentExtraApellido")
        edadTextView.text = intent.getStringExtra("intentExtraEdad")

        botonGuardar.setOnClickListener {
            val nombreString = nombreTextView.text.toString()
            val apellidoString = apellidoTextView.text.toString()
            val edadString = edadTextView.text.toString()
            val calleString = calleEditText.text.toString()
            val sexoString = sexoEditText.text.toString().uppercase()

            if (nombreString.isEmpty() || apellidoString.isEmpty() || edadString.isEmpty() || calleString.isEmpty() || sexoString.isEmpty()) {
                Toast.makeText(this, "Todos los campos son obligatorios", Toast.LENGTH_LONG).show()
                return@setOnClickListener
            }

            if (sexoString != "HOMBRE" && sexoString != "MUJER") {
                Toast.makeText(this, "El sexo debe ser 'Hombre' o 'Mujer'", Toast.LENGTH_LONG).show()
                return@setOnClickListener
            }

            val persona = Persona(nombreString, apellidoString, edadString).apply {
                setCalle(calleString)
                setSexo(if (sexoString == "HOMBRE") Persona.Sexo.Hombre else Persona.Sexo.Mujer)
            }

            dbGeneral.insertarPersona(persona)
            Toast.makeText(this, "Persona guardada correctamente", Toast.LENGTH_SHORT).show()
            finish()
        }

        botonSiguiente.setOnClickListener {
            val intent = Intent(this, Listado::class.java)
            startActivity(intent)
        }

        botonAtras.setOnClickListener {
            finish()
        }
    }
}
