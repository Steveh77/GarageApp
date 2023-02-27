package it.academy.android.garageappfinal20.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.fragment.findNavController
import it.academy.android.garageappfinal20.R


class MenuPageFragment : Fragment() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_menu_page, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //per andare ai clienti
        view.findViewById<Button>(R.id.clients_button).setOnClickListener {
            findNavController().navigate(R.id.action_menuPageFragment_to_clientListFragment)
        }
        //per andare ai veicoli
        view.findViewById<Button>(R.id.vehicles_button).setOnClickListener {
            findNavController().navigate(R.id.action_menuPageFragment_to_vehicleListFragment)
        }
        //per andare agli interventi
        view.findViewById<Button>(R.id.interventions_button).setOnClickListener {
            findNavController().navigate(R.id.action_menuPageFragment_to_interventionListFragment)
        }

    }

}