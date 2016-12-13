package net.odiak.playground

import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.support.v4.view.ViewCompat
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.helper.ItemTouchHelper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView = findViewById(R.id.recyclerView) as RecyclerView
        val adapter = Adapter(this)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        val dragDir = ItemTouchHelper.UP or ItemTouchHelper.DOWN
        val touchHelper = ItemTouchHelper(object : ItemTouchHelper.SimpleCallback(dragDir, 0) {
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder?, direction: Int) {
            }

            override fun onMove(recyclerView: RecyclerView?, viewHolder: RecyclerView.ViewHolder,
                                target: RecyclerView.ViewHolder): Boolean {
                val originalPosition = viewHolder.adapterPosition
                val targetPosition = target.adapterPosition
                adapter.move(originalPosition, targetPosition)
                return true
            }

            override fun onSelectedChanged(viewHolder: RecyclerView.ViewHolder?, actionState: Int) {
                super.onSelectedChanged(viewHolder, actionState)

                when (actionState) {
                    ItemTouchHelper.ACTION_STATE_DRAG -> {
                        viewHolder?.let {
                            ViewCompat.setElevation(it.itemView, 13f)
//                            it.itemView.alpha = 0.7f
                        }
                    }
                    ItemTouchHelper.ACTION_STATE_IDLE -> {
                        println("@@ idle")
                        viewHolder?.let {
                            ViewCompat.setElevation(it.itemView, 0f)
                        }
                    }
                }
            }

            override fun clearView(recyclerView: RecyclerView?, viewHolder: RecyclerView.ViewHolder) {
                super.clearView(recyclerView, viewHolder)

//                viewHolder.itemView.alpha = 1.0f
                ViewCompat.setElevation(viewHolder.itemView, 0f)
                println("@@ clearView")
            }
        })
        touchHelper.attachToRecyclerView(recyclerView)
    }

    class Adapter(context: Context) : RecyclerView.Adapter<ViewHolder>() {

        private val inflater = LayoutInflater.from(context)

        private val items = (0..100).forEachWithObject(mutableListOf<Int>()) { n, l -> l.add(n) }

        override fun getItemCount(): Int = items.size

        override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {
            return ViewHolder(inflater.inflate(R.layout.item, parent, false))
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            val tv = holder.itemView as TextView
            val n = items[position]
            tv.text = n.toString()
            tv.setBackgroundColor(if (n % 2 == 0) Color.GRAY else Color.LTGRAY)
        }

        fun move(originalPosition: Int, targetPosition: Int) {
            val item = items.removeAt(originalPosition)
            items.add(targetPosition, item)
            notifyItemMoved(originalPosition, targetPosition)
        }
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view)
}
