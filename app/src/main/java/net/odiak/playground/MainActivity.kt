package net.odiak.playground

import android.graphics.Color
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.PagerSnapHelper
import android.support.v7.widget.RecyclerView
import android.view.Gravity
import android.view.ViewGroup
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView = findViewById(R.id.recyclerView) as RecyclerView
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(
                this, LinearLayoutManager.HORIZONTAL, false)
        recyclerView.adapter = MyPagerAdapter()
        val snapHelper = PagerSnapHelper()
        snapHelper.attachToRecyclerView(recyclerView)
    }

    private class MyPagerAdapter() : RecyclerView.Adapter<ViewHolder>() {
        private val colors = (0..10).map {
            val c = 255 - it * 10
            Color.rgb(c, c, c)
        }

        override fun getItemCount(): Int = colors.size

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder = run {
            val tv = TextView(parent.context)
            tv.textSize = 32f
            tv.gravity = Gravity.CENTER
            tv.layoutParams = RecyclerView.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.MATCH_PARENT
            )
            ViewHolder(tv)
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            holder.textView.text = "$position"
            holder.textView.setBackgroundColor(colors[position])
        }
    }

    private class ViewHolder(val textView: TextView) : RecyclerView.ViewHolder(textView)
}
