package net.odiak.playground

import android.content.Context
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView = findViewById(R.id.recyclerView) as RecyclerView

        recyclerView.adapter = Adapter(this)
        recyclerView.layoutManager = GridLayoutManager(this, 3)
    }

    private class Adapter(private val context: Context) : RecyclerView.Adapter<ViewHolder>() {

        private val inflater = LayoutInflater.from(context)

        private val items = (0..200).map { it.toString() }

        override fun getItemCount(): Int {
            return items.size
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            return ViewHolder(inflater.inflate(R.layout.item, parent, false))
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            holder.textView.text = items[position]
        }
    }

    private class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val textView = view.findViewById(R.id.textView) as TextView
    }
}
