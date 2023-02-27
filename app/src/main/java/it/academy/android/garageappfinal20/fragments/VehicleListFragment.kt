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
import it.academy.android.garageappfinal20.adapters.ClientAdapter
import it.academy.android.garageappfinal20.adapters.VehicleAdapter
import it.academy.android.garageappfinal20.dao.ClientDao
import it.academy.android.garageappfinal20.dao.VehicleDao
import it.academy.android.garageappfinal20.database.GarageDatabase
import it.academy.android.garageappfinal20.entities.Client
import it.academy.android.garageappfinal20.entities.Vehicle


class VehicleListFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var vehicleAdapter: VehicleAdapter
    private var vehicleList = listOf<Vehicle>()

    private lateinit var vehicleDao: VehicleDao
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val database = GarageDatabase.getDatabase(requireContext())
        vehicleDao = database.vehicleDao()

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_vehicle_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //per andare ad aggiungi cliente
        view.findViewById<Button>(R.id.add_vehicle).setOnClickListener {
            findNavController().navigate(R.id.action_vehicleListFragment_to_addVehicleFragment)
        }
        Thread {
            vehicleList = vehicleDao.getAll()

            // Aggiorniamo l'UI sulla UI thread
            activity?.runOnUiThread {
                recyclerView = view.findViewById(R.id.vehicle_recyclerView)
                // Configuriamo la RecyclerView come prima
                recyclerView.layoutManager = LinearLayoutManager(requireContext())
                vehicleAdapter = VehicleAdapter(vehicleList)
                recyclerView.adapter = vehicleAdapter
                vehicleAdapter.notifyDataSetChanged()
            }
        }.start()

    }

}