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
import it.academy.android.garageappfinal20.entities.Intervention
import it.academy.android.garageappfinal20.entities.Vehicle
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class AddInterventionFragment : Fragment() {
    private lateinit var vehicleSpinner: Spinner
    private lateinit var adapter: ArrayAdapter<Vehicle>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        // istanza del database
        val view = inflater.inflate(R.layout.fragment_add_intervention, container, false)
        val database = GarageDatabase.getDatabase(requireContext())


        //spinner
        vehicleSpinner = view.findViewById(R.id.vehicle_spinner)
        adapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        vehicleSpinner.adapter = adapter

        //problema thread
        GlobalScope.launch {
            val vehicles = database.vehicleDao().getAll()
            withContext(Dispatchers.Main) {
                adapter.addAll(vehicles)
                adapter.notifyDataSetChanged()
            }
        }

        // Imposto un onClickListener per il pulsante
        view.findViewById<Button>(R.id.save_intervention_button).setOnClickListener {

            // Ottieni i valori di input per descrizione,ore di lavoro, data di inizio , data di fine e ID veicolo
            val descrizione =
                view.findViewById<EditText>(R.id.new_description_intervention_editText).text.toString()
            val oreDiLavoro =
                view.findViewById<EditText>(R.id.new_workHours_intervention_editText).text.toString()
                    .toFloat()
            val dataDiInizio =
                view.findViewById<EditText>(R.id.new_startDate_intervention_editText).text.toString()
            val dataDiFine =
                view.findViewById<EditText>(R.id.new_endDate_intervention_editText).text.toString()

            // Prendo l'id del veicolo selezionato nello spinner
            val vehicleId = (vehicleSpinner.selectedItem as Vehicle).id

            // Crea un nuovo oggetto Intervento
            val intervento =
                Intervention(0, descrizione, oreDiLavoro, dataDiInizio, dataDiFine, vehicleId)

            // Inserisco il nuovo oggetto Veicolo nel database
            GlobalScope.launch {
                database.interventionDao().save(intervento)

            }
            findNavController().navigate(R.id.action_addInterventionFragment_to_interventionListFragment)


        }
        return view
    }
}


