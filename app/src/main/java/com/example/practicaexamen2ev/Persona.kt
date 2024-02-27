package com.example.practicaexamen2ev

class Persona(
    private var nombre: String,
    private var apellido: String,
    private var edad: String
) {
    private lateinit var calle: String
    private lateinit var sexo: Sexo

    enum class Sexo { Mujer, Hombre }

    fun getNombre() = nombre
    fun getApellido() = apellido
    fun getEdad() = edad
    fun getCalle() = calle
    fun getSexo() = sexo

    fun setCalle(calle: String) {
        this.calle = calle
    }

    fun setSexo(sexo: Sexo) {
        this.sexo = sexo
    }
}
