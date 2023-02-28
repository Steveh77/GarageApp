package it.academy.android.garageappfinal20.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import androidx.navigation.fragment.findNavController
import it.academy.android.garageappfinal20.R
import it.academy.android.garageappfinal20.database.GarageDatabase
import it.academy.android.garageappfinal20.entities.Client
import it.academy.android.garageappfinal20.entities.Vehicle
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class AddVehicleFragment : Fragment() {
    //variabili spinner
    private lateinit var clientSpinner: Spinner
    private lateinit var adapter: ArrayAdapter<Client>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        // istanza del database
        val view = inflater.inflate(R.layout.fragment_add_vehicle, container, false)
        val database = GarageDatabase.getDatabase(requireContext())


        //spinner
        clientSpinner = view.findViewById(R.id.client_spinner)
        adapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        clientSpinner.adapter = adapter

        //problema thread
        GlobalScope.launch {
            val clients = database.clienteDao().getAll()
            withContext(Dispatchers.Main) {
                adapter.addAll(clients)
                adapter.notifyDataSetChanged()
            }
        }


        // Imposto un onClickListener per il pulsante

        view.findViewById<Button>(R.id.save_vehicle_button).setOnClickListener {
            // Ottieni i valori di input per marca, modello,targa e telaio e IDcliente
            val marca = view.findViewById<EditText>(R.id.new_brand_vehicle_editText).text.toString()
            val modello =
                view.findViewById<EditText>(R.id.new_model_vehicle_editText).text.toString()
            val targa = view.findViewById<EditText>(R.id.new_plate_vehicle_editText).text.toString()
            val telaio =
                view.findViewById<EditText>(R.id.new_chassis_vehicle_editText).text.toString()

            // Prendo l'id del cliente selezionato nello spinner
            val clientId = (clientSpinner.selectedItem as Client).id

            val vehicle = Vehicle(0, marca, modello, targa, telaio, clientId)

            GlobalScope.launch {
                database.vehicleDao().save(vehicle)
            }
            findNavController().navigate(R.id.action_addVehicleFragment_to_vehicleListFragment)
        }

        return view
    }
}


