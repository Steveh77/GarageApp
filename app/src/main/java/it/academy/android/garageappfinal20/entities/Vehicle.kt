package it.academy.android.garageappfinal20.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

/*
- Marca
- Modello
- Targa
- Numero di telaio
Per ogni auto sono possibili più interventi
 */
@Entity(
    tableName = "vehicle_table",
    foreignKeys = [ForeignKey(
        entity = Client::class,
        parentColumns = ["id"],
        childColumns = ["client_id"],
        onDelete = ForeignKey.SET_NULL
    )]
)
data class Vehicle(
    @PrimaryKey(autoGenerate = true) val id: Long,
    val brand: String,
    val model: String,
    val plateNumber: String,
    val chassisNumber: String,
    @ColumnInfo(name = "client_id") val clientId: Long?
)
