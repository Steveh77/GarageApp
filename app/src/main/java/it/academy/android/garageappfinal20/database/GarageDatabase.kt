package it.academy.android.garageappfinal20.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import it.academy.android.garageappfinal20.dao.ClientDao
import it.academy.android.garageappfinal20.dao.InterventionDao
import it.academy.android.garageappfinal20.dao.VehicleDao
import it.academy.android.garageappfinal20.entities.Client
import it.academy.android.garageappfinal20.entities.Intervention
import it.academy.android.garageappfinal20.entities.Vehicle

@Database(
    entities = [Client::class, Vehicle::class, Intervention::class],
    version = 2,
    exportSchema = false


)
abstract class GarageDatabase : RoomDatabase() {

    abstract fun clienteDao(): ClientDao
    abstract fun vehicleDao(): VehicleDao
    abstract fun interventionDao(): InterventionDao

    companion object {

        @Volatile
        private var INSTANCE: GarageDatabase? = null

        fun getDatabase(context: Context): GarageDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    GarageDatabase::class.java,
                    "client_database"
                ).fallbackToDestructiveMigration().build()
                INSTANCE = instance
                return instance
            }
        }
    }


}