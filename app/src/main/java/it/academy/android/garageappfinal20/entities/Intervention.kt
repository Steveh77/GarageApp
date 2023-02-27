package it.academy.android.garageappfinal20.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey


/*
- Descrizione
- Durata oraria
- Data di arrivo
- Data di consegna
 */

@Entity(
    tableName = "intervention_table",
    foreignKeys = [ForeignKey(
        entity = Vehicle::class,
        parentColumns = ["id"],
        childColumns = ["vehicle_id"],
        onDelete = ForeignKey.SET_NULL
    )]
)
data class Intervention(
    @PrimaryKey(autoGenerate = true) val id: Long,
    val description: String,
    val workHours: Float,
    val startDate: String,
    val endDate: String,
    @ColumnInfo(name = "vehicle_id") val carId: Long?
)
