package it.academy.android.garageappfinal20.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

/*
- nome
- cognome
- email
Ogni cliente può avere più auto
 */
@Entity(tableName = "client_table")
data class Client(
    @PrimaryKey(autoGenerate = true) val id: Long,
    val name: String,
    val surname: String,
    val email: String
)
