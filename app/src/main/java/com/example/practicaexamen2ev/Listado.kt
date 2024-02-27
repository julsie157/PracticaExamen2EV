package com.example.practicaexamen2ev

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity


class Listado : AppCompatActivity() {

    private lateinit var textViewPersonas: TextView

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.layout_todo)

        textViewPersonas = findViewById(R.id.textViewPersonas)

        val baseDatos = BaseDeDatos(this)
        val listaPersonas = baseDatos.leerPersonas()
        val personasTexto = listaPersonas.joinToString("\n") { persona ->
            "${persona.getNombre()} ${persona.getApellido()} - Edad: ${persona.getEdad()}, Calle: ${persona.getCalle()}, Sexo: ${persona.getSexo()}"
        }
        textViewPersonas.text = personasTexto
    }
}

