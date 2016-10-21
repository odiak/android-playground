package net.odiak.playground

import android.os.Bundle
import android.os.Handler
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.app.AppCompatActivity
import android.view.View

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val v1 = findViewById(R.id.v1)!!
        val v2 = findViewById(R.id.v2)!!
        val swipeRefresh = findViewById(R.id.swipeRefresh) as SwipeRefreshLayout

        v2.hideAway()

        v1.setOnClickListener {
            v1.hideAway()
            v2.show()
        }

        v2.setOnClickListener {
            v2.hideAway()
            v1.show()
        }

        val handler = Handler()
        swipeRefresh.setOnRefreshListener {
            handler.postDelayed({
                swipeRefresh.isRefreshing = false
            }, 1500)
        }
    }

    private fun View.show() {
        visibility = View.VISIBLE
    }

    private fun View.hideAway() {
        visibility = View.GONE
    }
}
