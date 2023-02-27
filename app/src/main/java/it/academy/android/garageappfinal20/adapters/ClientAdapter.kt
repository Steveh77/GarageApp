package it.academy.android.garageappfinal20.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import it.academy.android.garageappfinal20.R
import it.academy.android.garageappfinal20.entities.Client


//Adapter il cui compito è quello di gestire la visualizzazione dei dati nella RecyclerView

class ClientAdapter(private val clients: List<Client>) :
    RecyclerView.Adapter<ClientAdapter.ClientViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ClientViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.client_list_item, parent, false)
        return ClientViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ClientViewHolder, position: Int) {
        val client = clients[position]
        holder.bind(client)
    }

    override fun getItemCount(): Int {
        return clients.size
    }

    inner class ClientViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val nameTextView: TextView = itemView.findViewById(R.id.name_textView)
        private val surnameTextView: TextView = itemView.findViewById(R.id.surname_textView)
        private val emailTextView: TextView = itemView.findViewById(R.id.email_textView)

        fun bind(client: Client) {
            nameTextView.text = client.name
            surnameTextView.text = client.surname
            emailTextView.text = client.email
        }
    }
}
