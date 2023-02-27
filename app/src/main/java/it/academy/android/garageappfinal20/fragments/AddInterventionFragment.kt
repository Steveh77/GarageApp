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
import it.academy.android.garageappfinal20.entities.Intervention
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


class AddInterventionFragment : Fragment() {


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

        // Imposto un onClickListener per il pulsante
        view.findViewById<Button>(R.id.save_intervention_button).setOnClickListener {

            // Ottieni i valori di input per descrizione,ore di lavoro, data di inizio e data di fine
            val descrizione =
                view.findViewById<EditText>(R.id.new_description_intervention_editText).text.toString()
            val oreDiLavoro =
                view.findViewById<EditText>(R.id.new_workHours_intervention_editText).text.toString()
                    .toFloat()
            val dataDiInizio =
                view.findViewById<EditText>(R.id.new_startDate_intervention_editText).text.toString()
            val dataDiFine =
                view.findViewById<EditText>(R.id.new_endDate_intervention_editText).text.toString()

            // Crea un nuovo oggetto Intervento
            val intervento = Intervention(0, descrizione, oreDiLavoro, dataDiInizio, dataDiFine, 1)

            // Inserisco il nuovo oggetto Cliente nel database
            GlobalScope.launch {
                database.interventionDao().save(intervento)

            }
            findNavController().navigate(R.id.action_addInterventionFragment_to_interventionListFragment)


        }
        return view
    }
}


