package it.academy.android.garageappfinal20.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import it.academy.android.garageappfinal20.entities.Client

@Dao
interface ClientDao {
    @Query("SELECT * FROM client_table")
    fun getAll(): List<Client>

    @Insert
    fun save(client: Client)

    @Delete
    fun delete(client: Client)

    @Query("SELECT * FROM client_table WHERE id = :id")
    fun getClient(id: Long): Client
}