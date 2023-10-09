package com.example.intentosraros

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.intentosraros.network.Serie

class PeliculaAdapter (var peliculas: MutableList<Serie>, var context: Context): RecyclerView.Adapter<PeliculaAdapter.PeliculaViewHolder>() {


    override fun onBindViewHolder(holder: PeliculaViewHolder, position: Int) {
        val item = peliculas.get(position)
        holder.txNombre.text = item.title
        holder.txFecha.text = item.startYear.toString()
        holder.txFechaEnd.text = item.endYear.toString()
        holder.itemView.setOnClickListener(
            View.OnClickListener {
                Toast.makeText(context, item.title, Toast.LENGTH_SHORT).show()
            }
        )
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PeliculaViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_pelicula, parent, false)

        return PeliculaViewHolder(view);
    }

    override fun getItemCount() = peliculas.size

    class PeliculaViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val txNombre: TextView
        val txFecha: TextView
        val txFechaEnd: TextView

        init {
            txNombre = view.findViewById(R.id.tvPelicula)
            txFecha = view.findViewById(R.id.tvFecha)
            txFechaEnd = view.findViewById(R.id.tvFechaEnd)
        }
    }


}