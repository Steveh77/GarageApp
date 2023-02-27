package it.academy.android.garageappfinal20.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.navigation.fragment.findNavController
import it.academy.android.garageappfinal20.R
import it.academy.android.garageappfinal20.database.GarageDatabase
import it.academy.android.garageappfinal20.entities.Client
import it.academy.android.garageappfinal20.entities.Vehicle
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class AddVehicleFragment : Fragment() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        // Inflate the layout for this fragment
        // istanza del database
        val view = inflater.inflate(R.layout.fragment_add_vehicle, container, false)
        val database = GarageDatabase.getDatabase(requireContext())

        // Imposto un onClickListener per il pulsante
        view.findViewById<Button>(R.id.save_vehicle_button).setOnClickListener {

            // Ottieni i valori di input per marca, modello,targa e telaio
            val marca = view.findViewById<EditText>(R.id.new_brand_vehicle_editText).text.toString()
            val modello =
                view.findViewById<EditText>(R.id.new_model_vehicle_editText).text.toString()
            val targa = view.findViewById<EditText>(R.id.new_plate_vehicle_editText).text.toString()
            val telaio =
                view.findViewById<EditText>(R.id.new_chassis_vehicle_editText).text.toString()

            // Crea un nuovo oggetto veicolo
            val vehicle = Vehicle(0, marca, modello, targa, telaio, 1)

            // Inserisco il nuovo oggetto Cliente nel database
            GlobalScope.launch {
                database.vehicleDao().save(vehicle)

            }
            findNavController().navigate(R.id.action_addVehicleFragment_to_vehicleListFragment)


        }
        return view

    }


}