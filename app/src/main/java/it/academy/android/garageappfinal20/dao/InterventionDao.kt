package it.academy.android.garageappfinal20.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import it.academy.android.garageappfinal20.entities.Intervention

@Dao
interface InterventionDao {
    @Query("SELECT * FROM intervention_table")
    fun getAll(): List<Intervention>

    @Insert
    fun save(intervention: Intervention)

    @Delete
    fun delete(intervention: Intervention)
}