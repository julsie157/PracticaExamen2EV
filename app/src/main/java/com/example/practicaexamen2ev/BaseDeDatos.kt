package com.example.practicaexamen2ev

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class BaseDeDatos(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {
    companion object {
        private const val DATABASE_VERSION = 1
        private const val DATABASE_NAME = "db"

        private const val TABLA_PERSONAS = "Personas"
        private const val COLUMN_ID_PERSONA = "idPersona"
        private const val COLUMN_NOMBRE = "Nombre"
        private const val COLUMN_APELLIDO = "Apellido"
        private const val COLUMN_EDAD = "Edad"
        private const val COLUMN_CALLE = "Calle"
        private const val COLUMN_SEXO = "Sexo"


    }

    override fun onCreate(db: SQLiteDatabase?) {
        val createTablePersonas = "CREATE TABLE $TABLA_PERSONAS (" +
                "$COLUMN_ID_PERSONA INTEGER PRIMARY KEY, " +
                "$COLUMN_NOMBRE TEXT, " +
                "$COLUMN_APELLIDO TEXT, " +
                "$COLUMN_EDAD TEXT, " +
                "$COLUMN_CALLE TEXT, " +
                "$COLUMN_SEXO INTEGER)"

        db?.execSQL(createTablePersonas)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL("DROP TABLE IF EXISTS $TABLA_PERSONAS")
        onCreate(db)
    }

    fun insertarPersona(persona: Persona) {
        val db = this.writableDatabase

        val values = ContentValues()
        values.put(COLUMN_NOMBRE, persona.getNombre())
        values.put(COLUMN_APELLIDO, persona.getApellido())
        values.put(COLUMN_EDAD, persona.getEdad())
        values.put(COLUMN_CALLE, persona.getCalle())
        values.put(COLUMN_SEXO, persona.getSexo().ordinal)
        db.insert(TABLA_PERSONAS, null, values)
        db.close()
    }

    @SuppressLint("Range")
    fun leerPersonas(): List<Persona> {
        val listaPersonas = mutableListOf<Persona>()
        val db = this.readableDatabase
        val selectQuery = "SELECT * FROM $TABLA_PERSONAS"
        val cursor = db.rawQuery(selectQuery, null)

        if (cursor.moveToFirst()) {
            do {
                val persona = Persona(
                    cursor.getString(cursor.getColumnIndex(COLUMN_NOMBRE)),
                    cursor.getString(cursor.getColumnIndex(COLUMN_APELLIDO)),
                    cursor.getString(cursor.getColumnIndex(COLUMN_EDAD))
                )
                persona.setCalle(cursor.getString(cursor.getColumnIndex(COLUMN_CALLE)))
                persona.setSexo(Persona.Sexo.values()[cursor.getInt(cursor.getColumnIndex(COLUMN_SEXO))])

                listaPersonas.add(persona)
            } while (cursor.moveToNext())
        }
        cursor.close()
        db.close()
        return listaPersonas
    }

}