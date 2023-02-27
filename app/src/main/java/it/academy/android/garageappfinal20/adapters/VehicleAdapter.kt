package it.academy.android.garageappfinal20.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import it.academy.android.garageappfinal20.R
import it.academy.android.garageappfinal20.entities.Vehicle


//Adapter il cui compito è quello di gestire la visualizzazione dei dati nella RecyclerView

class VehicleAdapter(private val vehicles: List<Vehicle>) :
    RecyclerView.Adapter<VehicleAdapter.VehicleViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VehicleViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.vehicle_list_item, parent, false)
        return VehicleViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: VehicleViewHolder, position: Int) {
        val vehicle = vehicles[position]
        holder.bind(vehicle)
    }

    override fun getItemCount(): Int {
        return vehicles.size
    }

    inner class VehicleViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val brandTextView: TextView = itemView.findViewById(R.id.brand_textView)
        private val modelTextView: TextView = itemView.findViewById(R.id.model_textView)
        private val plateTextView: TextView = itemView.findViewById(R.id.plate_textView)
        private val chassisNumberTextView: TextView =
            itemView.findViewById(R.id.chassisNumber_textView)

        fun bind(vehicle: Vehicle) {
            brandTextView.text = vehicle.brand
            modelTextView.text = vehicle.model
            plateTextView.text = vehicle.plateNumber
            chassisNumberTextView.text = vehicle.chassisNumber
        }
    }
}
