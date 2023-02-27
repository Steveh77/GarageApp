package it.academy.android.garageappfinal20.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import it.academy.android.garageappfinal20.R
import it.academy.android.garageappfinal20.database.GarageDatabase
import it.academy.android.garageappfinal20.entities.Client
import androidx.navigation.fragment.findNavController
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


class AddClientFragment : Fragment() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        // istanza del database
        val view = inflater.inflate(R.layout.fragment_add_client, container, false)
        val database = GarageDatabase.getDatabase(requireContext())

        // Imposto un onClickListener per il pulsante
        view.findViewById<Button>(R.id.save_client_button).setOnClickListener {

            // Ottieni i valori di input per nome, cognome ed email
            val nome = view.findViewById<EditText>(R.id.new_name_client_editText).text.toString()
            val cognome =
                view.findViewById<EditText>(R.id.new_surname_client_editText).text.toString()
            val email = view.findViewById<EditText>(R.id.new_email_client_editText).text.toString()

            // Crea un nuovo oggetto Cliente
            val cliente = Client(0, nome, cognome, email)

            // Inserisco il nuovo oggetto Cliente nel database
            GlobalScope.launch {
                database.clienteDao().save(cliente)

            }
            findNavController().navigate(R.id.action_addClientFragment_to_clientListFragment)


        }
        return view

    }
}