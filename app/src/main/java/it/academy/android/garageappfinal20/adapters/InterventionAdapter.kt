package it.academy.android.garageappfinal20.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import it.academy.android.garageappfinal20.R
import it.academy.android.garageappfinal20.entities.Intervention

//Adapter il cui compito è quello di gestire la visualizzazione dei dati nella RecyclerView

class InterventionAdapter(private val interventions: List<Intervention>) :
    RecyclerView.Adapter<InterventionAdapter.InterventionViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): InterventionViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.intervention_list_item, parent, false)
        return InterventionViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: InterventionViewHolder, position: Int) {
        val intervention = interventions[position]
        holder.bind(intervention)
    }

    override fun getItemCount(): Int {
        return interventions.size
    }

    inner class InterventionViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val descriptionTextView: TextView = itemView.findViewById(R.id.description_textView)
        private val workHoursTextView: TextView = itemView.findViewById(R.id.workHours_textView)
        private val startDateTextView: TextView = itemView.findViewById(R.id.startDate_textView)
        private val endDateTextView: TextView = itemView.findViewById(R.id.endDate_textView)

        fun bind(intervention: Intervention) {
            descriptionTextView.text = intervention.description
            workHoursTextView.text = intervention.workHours.toString()
            startDateTextView.text = intervention.startDate
            endDateTextView.text = intervention.endDate
        }
    }
}
