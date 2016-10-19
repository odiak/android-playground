package net.odiak.playground

import android.content.Context
import android.graphics.Color
import android.graphics.Rect
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView = findViewById(R.id.recyclerView) as RecyclerView

        recyclerView.adapter = Adapter(this)

        recyclerView.layoutManager = GridLayoutManager(this, 2)
    }

    class Adapter(val context: Context) : RecyclerView.Adapter<ViewHolder>() {

        private val nums = (0..100).map { it.toString() }.toList()

        private val padding = Math.round(context.resources.displayMetrics.density * 10)

        override fun getItemCount() = nums.size

        override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {
            return ViewHolder(TextView(context).apply {
                setPadding(padding, padding, padding, padding)
                setBackgroundColor(Color.LTGRAY)
            })
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            holder.view.text = nums[position]
        }
    }

    class ViewHolder(val view: TextView) : RecyclerView.ViewHolder(view)

    class SpaceDecoration(val space: Int, val spanCount: Int) : RecyclerView.ItemDecoration() {

        override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView,
                                    state: RecyclerView.State) {
            val position = parent.getChildAdapterPosition(view)
            if (position < 0) return

            val column = position % spanCount


        }
    }
}
