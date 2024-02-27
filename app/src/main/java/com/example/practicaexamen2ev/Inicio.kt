package com.example.practicaexamen2ev

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText

class Inicio : AppCompatActivity() {

    private lateinit var nombre: EditText
    private lateinit var apellido: EditText
    private lateinit var edad: EditText
    private lateinit var botonContinuar: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.layout_inicio)

        nombre=findViewById(R.id.editTextNombre)
        apellido=findViewById(R.id.editTextApellido)
        edad=findViewById(R.id.editTextEdad)
        botonContinuar = findViewById(R.id.botonContinuar)

        botonContinuar.setOnClickListener{

            val nombre = nombre.text.toString()
            val apellido = apellido.text.toString()
            val edad = edad.text.toString()

            val intent = Intent(this, Informacion::class.java).apply {
                putExtra("intentExtraNombre", nombre)
                putExtra("intentExtraApellido", apellido)
                putExtra("intentExtraEdad", edad)
            }
            startActivity(intent)
        }

    }

    override fun onResume() {
        super.onResume()
        nombre.setText("")
        apellido.setText("")
        edad.setText("")
    }
}
