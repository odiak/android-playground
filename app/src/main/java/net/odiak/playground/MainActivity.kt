package net.odiak.playground

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val fm = supportFragmentManager
        if (fm.findFragmentById(R.id.container) == null) {
            fm.beginTransaction().replace(R.id.container, FirstFragment()).commit()
        }
    }

    private fun next() {
        supportFragmentManager.beginTransaction().replace(R.id.container, SecondFragment()).addToBackStack(null).commit()
    }

    class FirstFragment() : Fragment() {

        companion object {
            private val STATE_COUNT = "count"
        }

        private var count = 0

        override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
            return inflater.inflate(R.layout.fragment_first, container, false)
        }

        override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
            super.onViewCreated(view, savedInstanceState)

            view ?: return

            savedInstanceState?.let {
                println("@@ restore")
                count = it.getInt(STATE_COUNT, 0)
            }

            val countUp = view.findViewById(R.id.countUp)
            val textView = view.findViewById(R.id.count) as TextView
            val next = view.findViewById(R.id.next)

            countUp.setOnClickListener {
                count += 1
                textView.text = "$count"
            }

            textView.text = "$count"

            next.setOnClickListener {
                (activity as? MainActivity)?.next()
            }
        }

        override fun onSaveInstanceState(outState: Bundle) {
            super.onSaveInstanceState(outState)
            println("@@ saveInstanceState")
            outState.putInt(STATE_COUNT, count)
        }
    }

    class SecondFragment() : Fragment() {
        override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
            return inflater.inflate(R.layout.fragment_second, container, false)
        }

        override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
            super.onViewCreated(view, savedInstanceState)

            view ?: return

            val button = view.findViewById(R.id.button) as TextView
            button.text = "${(Math.random() * 100).toInt()}"
            button.setOnClickListener {
                (activity as? MainActivity)?.next()
            }
        }
    }
}
