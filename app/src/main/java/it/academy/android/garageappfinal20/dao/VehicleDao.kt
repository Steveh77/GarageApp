package it.academy.android.garageappfinal20.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import it.academy.android.garageappfinal20.entities.Vehicle

@Dao
interface VehicleDao {

    @Query("SELECT * FROM vehicle_table")
    fun getAll(): List<Vehicle>

    @Insert
    fun save(vehicle: Vehicle)

    @Delete
    fun delete(vehicle: Vehicle)

    @Query("SELECT * FROM vehicle_table WHERE id = :id")
    fun getVehicle(id: Long): Vehicle
}