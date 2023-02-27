package it.academy.android.garageappfinal20.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import it.academy.android.garageappfinal20.R
import it.academy.android.garageappfinal20.adapters.InterventionAdapter
import it.academy.android.garageappfinal20.adapters.VehicleAdapter
import it.academy.android.garageappfinal20.dao.InterventionDao
import it.academy.android.garageappfinal20.dao.VehicleDao
import it.academy.android.garageappfinal20.database.GarageDatabase
import it.academy.android.garageappfinal20.entities.Intervention
import it.academy.android.garageappfinal20.entities.Vehicle


class InterventionListFragment : Fragment() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var interventionAdapter: InterventionAdapter
    private var interventionList = listOf<Intervention>()

    private lateinit var interventionDao: InterventionDao


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val database = GarageDatabase.getDatabase(requireContext())
        interventionDao = database.interventionDao()

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_intervention_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //per andare ad aggiungi cliente
        view.findViewById<Button>(R.id.add_intervention).setOnClickListener {
            findNavController().navigate(R.id.action_interventionListFragment_to_addInterventionFragment)
        }
        Thread {
            interventionList = interventionDao.getAll()

            // Aggiorniamo l'UI sulla UI thread
            activity?.runOnUiThread {
                recyclerView = view.findViewById(R.id.intervention_recyclerView)
                // Configuriamo la RecyclerView come prima
                recyclerView.layoutManager = LinearLayoutManager(requireContext())
                interventionAdapter = InterventionAdapter(interventionList)
                recyclerView.adapter = interventionAdapter
                interventionAdapter.notifyDataSetChanged()
            }
        }.start()


    }
}